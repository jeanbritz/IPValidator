package model;

import java.text.ParseException;

public class IPv4Address extends IPAddress {

	public IPv4Address() {
		super(4);

	}

	@Override
	public boolean isValid() {
		boolean valid = false;
		int cnt = 0;
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] >= 0 && blocks[i] <= 255) {
				cnt++;
			}
		}
		if (cnt == 4) {
			valid = true;
		}
		if (cidr > 0) {
			if (cidr <= 32 | cidr >= 8) {
				valid = true;
			} else {
				valid = false;
			}
		}
		return valid;
	}

	@Override
	public boolean parse(String ip) throws ParseException {
		String s[] = ip.split("\\/");
		String tmp[] = s[0].split("[.]");
		boolean success = true;
		if (tmp.length == 4) {
			try {
				for (int i = 0; i < tmp.length; i++) {
					this.blocks[i] = Integer.parseInt(tmp[i]);
				}
				if (s.length > 1 && !s[1].isEmpty()) {
					cidr = Integer.parseInt(s[1]);
				}
			} catch (NumberFormatException e) {
				success = false;
				throw e;
			}
		} else {
			success = false;
			throw new ParseException("Invalid IP : " + ip, 0);
		}
		return success;
	}

	@Override
	public String toString() {
		String ipString = "";
		for (int i = 0; i < blocks.length; i++) {
			ipString += String.valueOf(blocks[i]);
			if (i < blocks.length - 1) {
				ipString += '.';
			}
		}
		if (cidr > 0) {
			ipString += ("/" + cidr);
		}
		return ipString;
	}

}
