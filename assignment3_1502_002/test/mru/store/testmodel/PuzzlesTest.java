package mru.store.testmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import model.Puzzles;

/**
 * This test case tests the constructor and getters, setters, format() method,
 * and toString() method of the Puzzles class. The assertEquals() method is used
 * to compare the expected values with the actual values returned by the methods
 * being tested.
 * 
 * @author caesar and steven
 * @version 1.0
 *
 */
public class PuzzlesTest {

	@Test
	/**
	 * Test getters of puzzle class
	 */
	public void testConstructorAndGetters() {
		Puzzles puzzleToy = new Puzzles(1111111111, "GenericName", "GenericBrand", 19.99f, 10, 3, "Cryptic");

		assertEquals(1111111111, puzzleToy.getSerialNumber());
		assertEquals("GenericName", puzzleToy.getName());
		assertEquals("GenericBrand", puzzleToy.getBrand());
		assertEquals(19.99f, puzzleToy.getPrice(), 0.01f);
		assertEquals(10, puzzleToy.getAvalibleCount());
		assertEquals(3, puzzleToy.getAgeAppropriate());
		assertEquals("Cryptic", puzzleToy.getType());
	}

	@Test
	/**
	 * Test setters of puzzle class
	 */
	public void testSetters() {
		Puzzles puzzleToy = new Puzzles(0, null, null, 0, 0, 0, null);
		puzzleToy.setSerialNumber(1987654321);
		assertEquals(1987654321, puzzleToy.getSerialNumber());
		puzzleToy.setName("New Name");
		assertEquals("New Name", puzzleToy.getName());
		puzzleToy.setBrand("New Brand");
		assertEquals("New Brand", puzzleToy.getBrand());
		puzzleToy.setPrice(19.99f);
		assertEquals(19.99f, puzzleToy.getPrice(), 0.001f);
		puzzleToy.setAvalibleCount(20);
		assertEquals(20, puzzleToy.getAvalibleCount());
		puzzleToy.setAgeAppropriate(10);
		assertEquals(10, puzzleToy.getAgeAppropriate());
		puzzleToy.setType("Riddle");
		assertEquals("Riddle", puzzleToy.getType());
	}

	@Test
	/**
	 * Test format of puzzle class
	 */
	public void testFormat() {
		Puzzles puzzleToy = new Puzzles(1111111111, "GenericName", "GenericBrand", 19.99f, 10, 3, "Cryptic");

		String expected = "1111111111;GenericName;GenericBrand;19.99;10;3;Cryptic";
		assertEquals(expected, puzzleToy.format());
	}

	@Test
	/**
	 * Tests toString of puzzle class
	 */
	public void testToString() {
		Puzzles puzzleToy = new Puzzles(1111111111, "GenericName", "GenericBrand", 19.99f, 10, 3, "Cryptic");

		String expected = "Category: Puzzles, Serial Number: 1111111111, Name: GenericName, Brand: GenericBrand, Price: 19.99, Availible Stock: 10, Minimum Age: 3, Type: Cryptic";
		assertEquals(expected, puzzleToy.toString());
	}
}
