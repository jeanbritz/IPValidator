package com.network.model;

import java.text.ParseException;

public class IPv6Address extends IPAddress {


	public IPv6Address() {
		super(8);
	}

	@Override
	public boolean parse(String ip) throws ParseException {
		String s[] = ip.split("\\/");
		if (s.length > 1) {
			cidr = Integer.parseInt(s[1]);
		}
		String tmp[] = s[0].split("[:]");
		reset();
		boolean success = true;
		if (tmp.length == 8) {
			try {
				for (int i = 0; i < tmp.length; i++) {
					this.blocks[i] = (byte) Integer.parseInt(tmp[i], 16);
				}
			} catch (NumberFormatException e) {
				success = false;
				throw e;
			}
		} else {
			if (s[0].contains("::")) {
				String chunks[] = s[0].split("::", -1);
				if (chunks.length > 0 && chunks.length <= 2) {
					try {
						if (!chunks[0].isEmpty()) {
							String left[] = chunks[0].split("[:]");
							for (int i = 0; i < left.length; i++) {
								blocks[i] = Integer.parseInt(left[i], 16);
							}
						}
						if (chunks.length > 1 && !chunks[1].isEmpty()) {
							String right[] = chunks[1].split("[:]");
							for (int i = 0; i < right.length; i++) {
								blocks[blocks.length - i - 1] = Integer.parseInt(right[right.length - i - 1], 16);
							}
						}
					} catch (NumberFormatException e) {
						success = false;
						throw e;
					}
				} else {
					success = false;
					throw new ParseException("Invalid IP : " + ip, 0);
				}
			}

		}
		return success;
	}

	@Override
	public boolean isValid() {
		int cnt = 0;
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] <= 0xffff && blocks[i] >= 0) {
				cnt++;
			}
		}
		if (cnt != 8) {
			return false;
		}
		if (cidr < 0) {
			return false;
		}
		if (cidr > 128) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String ipString = "";
		for (int i = 0; i < blocks.length; i++) {
			ipString += String.format("%04x", 0xffff & blocks[i]);
			if (i < blocks.length - 1) {
				ipString += ":";
			}
		}
		if (cidr > 0) {
			ipString += ("/" + cidr);
		}

		return ipString;
	}



}
