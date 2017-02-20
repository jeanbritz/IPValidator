import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.IPAddress;
import model.IPv4Address;
import model.IPv6Address;

public class IPValidator {

	ArrayList<IPAddress> ipAddresses;
	ArrayList<IPv4Address> ipv4Addresses = new ArrayList<>();
	ArrayList<IPv6Address> ipv6Addresses = new ArrayList<>();

	IPValidator(String filename) {
		long start = System.currentTimeMillis();
		System.out.println("Start IP Validation");
		System.out.println("Cleaning output files");
		cleanFile("valid.txt");
		cleanFile("invalid.txt");
		System.out.println("Reading input file : " + filename);
		ipAddresses = readFile(filename);
		System.out.println("Sorting IP Addresses");
		sort(ipAddresses);
		System.out.println("Validating IP Addresses");
		validate(ipAddresses);
		System.out.println("Finished in " + (System.currentTimeMillis() - start) + " ms");
	}

	public Comparator<IPAddress> comparator = new Comparator<IPAddress>() {
		@Override
		public int compare(IPAddress o1, IPAddress o2) {
			for (int i = 0; i < o1.getBlocks().length; i++) {
				if (o1.getBlocks()[i] == o2.getBlocks()[i]) {
					continue;
				}
				if (o1.getBlocks()[i] < o2.getBlocks()[i]) {
					return 1;
				} else {
					return -1;
				}
			}
			return 0;
		}
	};

	private void sort(ArrayList<IPAddress> records) {
		for (IPAddress ip : records) {
			if (ip instanceof IPv4Address) {
				ipv4Addresses.add((IPv4Address) ip);
			} else {
				ipv6Addresses.add((IPv6Address) ip);
			}
		}
		Collections.sort(ipv4Addresses, comparator);
		Collections.sort(ipv6Addresses, comparator);
		ipAddresses.clear();
		ipAddresses.addAll(ipv4Addresses);
		ipAddresses.addAll(ipv6Addresses);
	}

	private void writeToFile(String filename, String data) {
		Path p = Paths.get(".", filename);
		try (OutputStream os = new BufferedOutputStream(
		    Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
			os.write(data.getBytes(), 0, data.length());
			os.write("\r\n".getBytes(), 0, 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cleanFile(String filename) {
		Path p = Paths.get(".", filename);
		try {
			Files.deleteIfExists(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public ArrayList<IPAddress> readFile(String filename) {
		File f = new File(filename);
		ArrayList<IPAddress> content = new ArrayList<IPAddress>(2);
		BufferedReader br;
		IPAddress addr = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String ipRaw;
			while ((ipRaw = br.readLine()) != null) {
				try {
					if (!ipRaw.contains(":")) {
						addr = new IPv4Address();
					} else {
						addr = new IPv6Address();
					}
				addr.parse(ipRaw);
				content.add(addr);
				} catch (ParseException | NumberFormatException e) {
					writeToFile("invalid.txt", ipRaw);
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	private void validate(ArrayList<IPAddress> ipAddr) {
		for (IPAddress ip : ipAddr) {
			if (ip.isValid()) {
				writeToFile("valid.txt", ip.toString());
			} else {
				writeToFile("invalid.txt", ip.toString());
			}
		}
	}

	public static void main(String args[]) {
		if (args.length > 0) {
		new IPValidator(args[0]);
		} else {
			throw new IllegalArgumentException("No file specified");
		}
	}

}
