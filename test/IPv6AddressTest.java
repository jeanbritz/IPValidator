import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.IPv6Address;

public class IPv6AddressTest {

	static IPv6Address ip;

	@BeforeClass
	public static void beforeClass() {
		ip = new IPv6Address();
	}

	@Before
	public void before() {

	}

	@Test
	public void testShouldParseValidIP() throws ParseException {
		assertTrue(ip.parse("::"));
		assertTrue(ip.parse("ffff:ffff::ffff:ffff:ffff:ffff"));
		assertTrue(ip.parse("2001:db8::"));
		assertTrue(ip.parse("::1"));
		assertTrue(ip.parse("2001:db8:a0b:12f0::1"));
		assertTrue(ip.parse("2001:db8:a0b:12f0::1/128"));
		assertTrue(ip.parse("2001:db8:a0b:12f0::1/1"));
		assertTrue(ip.parse("2001:db8:a0b:12f0::1/16"));

	}

	@Test(expected = ParseException.class)
	public void testInvalidIP() throws ParseException {
		ip.parse("2001:db8::543:4343:4343:2323:a0b:12f0::1");
		ip.parse(":12::22::1");
		assertTrue(ip.parse("2001:db8:a0b:12f0::1/129"));
		assertTrue(ip.parse("2001:db8:a0b:12f0::1/-55"));
	}

	@After
	public void after() {

	}


	@AfterClass
	public static void afterClass() {
		ip = null;
	}

}
