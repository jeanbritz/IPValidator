import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.network.model.IPv4Address;

public class IPv4AddressTest {
	
	static IPv4Address ip;
	
	@BeforeClass
	public static void beforeClass() {
		ip = new IPv4Address();
	}

	@Before
	public void before() {

	}

	@Test
	public void testShouldParseValidIP() throws ParseException {
		ip.parse("0.0.0.0");
		assertTrue(ip.isValid());
		ip.parse("172.16.1.1");
		assertTrue(ip.isValid());
		ip.parse("255.255.255.255");
		assertTrue(ip.isValid());
		ip.parse("172.16.1.1/32");
		assertTrue(ip.isValid());
		ip.parse("172.16.1.1/8");
		assertTrue(ip.isValid());

	}

	@Test(expected = ParseException.class)
	public void testShouldBeInvalidIP() throws ParseException {
		ip.parse("123");
		ip.parse("255.255.255.256");
		ip.parse("127.1");
		ip.parse("127.0.0.1/1");
		ip.parse("127.0.0.1/64");
		ip.parse("255.255.2.323.22.55.256");
	}

	@Test
	public void testPublicClassAValidIpRange() throws ParseException {
		ip.parse("1.0.0.0");
		assertTrue(ip.isValid());
		ip.parse("126.255.255.254");
		assertTrue(ip.isValid());
	}

	@Test
	public void testPublicClassBValidIpRange() throws ParseException {
		ip.parse("128.0.0.0");
		assertTrue(ip.isValid());
		ip.parse("191.255.255.255");
		assertTrue(ip.isValid());
	}

	@Test
	public void testPublicClassCValidIpRange() throws ParseException {
		ip.parse("192.0.0.0");
		assertTrue(ip.isValid());
		ip.parse("223.255.255.255");
		assertTrue(ip.isValid());
	}

	@Test
	public void testPublicClassDValidIpRange() throws ParseException {
		ip.parse("224.0.0.0");
		assertTrue(ip.isValid());
		ip.parse("239.255.255.255");
		assertTrue(ip.isValid());
	}

	@Test
	public void testPublicClassEValidIpRange() throws ParseException {
		ip.parse("240.0.0.0");
		assertTrue(ip.isValid());
		ip.parse("255.255.255.255");
		assertTrue(ip.isValid());
	}

	@Test
	public void testPrivateClassAValidIpRange() throws ParseException {
		ip.parse("10.0.0.0");
		assertTrue(ip.isValid());
		ip.parse("10.255.255.255");
		assertTrue(ip.isValid());
	}

	@Test
	public void testPrivateClassBValidIpRange() throws ParseException {
		ip.parse("172.16.0.0");
		assertTrue(ip.isValid());
		ip.parse("172.31.255.255");
		assertTrue(ip.isValid());
	}

	@Test
	public void testPrivateClassCValidIpRange() throws ParseException {
		ip.parse("192.168.0.0");
		assertTrue(ip.isValid());
		ip.parse("192.168.255.255");
		assertTrue(ip.isValid());
	}

	@After
	public void after() {

	}

	@AfterClass
	public static void afterClass() {
		ip = null;
	}

}
