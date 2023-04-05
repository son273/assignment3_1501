package mru.store.testmodel;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import model.BoardGames;

/**
 * This test case tests the constructor and getters, setters, format() method,
 * and toString() method of the BoardGames class. The assertEquals() method is
 * used to compare the expected values with the actual values returned by the
 * methods being tested.
 * 
 * @author caesar and steven
 * @version 1.0
 *
 */
public class BoardGamesTest {

	@Test
	/**
	 * Test the getters of the BoardGames Class
	 */
	public void testConstructorAndGetters() {
		BoardGames game = new BoardGames(1234567899, "GenericName", "GenericBrand", 19.99f, 10, 3, 2, 6, "Bobby");

		assertEquals(1234567899, game.getSerialNumber());
		assertEquals("GenericName", game.getName());
		assertEquals("GenericBrand", game.getBrand());
		assertEquals(19.99f, game.getPrice(), 0.01f);
		assertEquals(10, game.getAvalibleCount());
		assertEquals(3, game.getAgeAppropriate());
		assertEquals(2, game.getMinNumOfPlayers());
		assertEquals(6, game.getMaxNumOfPlayers());
		assertEquals("Bobby", game.getDesigners());

	}

	@Test
	/**
	 * Tests setters of BoardGame Class
	 */
	public void testSetters() {
		BoardGames game = new BoardGames(0, null, null, 0, 0, 0, 0, 0, null);

		game.setSerialNumber(123);
		assertEquals(123, game.getSerialNumber());

		game.setName("Monopoly");
		assertEquals("Monopoly", game.getName());

		game.setBrand("myBrand");
		assertEquals("myBrand", game.getBrand());

		game.setPrice(29.99f);
		assertEquals(29.99f, game.getPrice(), 0.01f);

		game.setAvalibleCount(10);
		assertEquals(10, game.getAvalibleCount());

		game.setAgeAppropriate(8);
		assertEquals(8, game.getAgeAppropriate());

		game.setMinNumOfPlayers(2);
		assertEquals(2, game.getMinNumOfPlayers());

		game.setMaxNumOfPlayers(4);
		assertEquals(4, game.getMaxNumOfPlayers());

		game.setDesigners("IDK");
		assertEquals("IDK", game.getDesigners());
	}

	@Test
	/**
	 * Tests the format of BoardGame Class
	 */
	public void testFormat() {
		BoardGames game = new BoardGames(1234567899, "GenericName", "GenericBrand", 19.99f, 10, 3, 2, 6, "Bobby");

		String expected = "1234567899;GenericName;GenericBrand;19.99;10;3;2-6;Bobby";
		assertEquals(expected, game.format());
	}

	@Test
	/**
	 * Tests toString Method of BoardGame Class
	 */
	public void testToString() {
		BoardGames game = new BoardGames(1234567899, "GenericName", "GenericBrand", 19.99f, 10, 3, 2, 6, "Bobby");

		String expected = "Category: Board Games, Serial Number: 1234567899, Name: GenericName, Brand: GenericBrand, Price: 19.99, Availible Stock: 10, Minimum Age: 3, Player Count: 2-6, Designers: Bobby";
		assertEquals(expected, game.toString());
	}
}
