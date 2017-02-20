package model;

import java.text.ParseException;

public abstract class IPAddress {

	protected int blocks[];
	protected int cidr;

	protected IPAddress(int blockSize) {
		blocks = new int[blockSize];
	}



	public abstract boolean parse(String ip) throws ParseException;

	public abstract boolean isValid();

	public abstract String toString();

	protected void reset() {
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = 0;
		}
	}

	public int[] getBlocks() {
		return blocks;
	}

	public int getCidr() {
		return cidr;
	}

}
