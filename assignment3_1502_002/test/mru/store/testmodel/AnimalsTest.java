package mru.store.testmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import model.Animals;

/**
 * This test case tests the constructor and getters, setters, format() method,
 * and toString() method of the Animals class. The assertEquals() method is used
 * to compare the expected values with the actual values returned by the methods
 * being tested.
 * 
 * @author caesar and steven
 * @version 1.0
 *
 */
public class AnimalsTest {
	
	
	
	@Test
	/**
	 * Tests the getter of the Animal Class
	 */
	public void testConstructorAndGetters() {
		Animals animalToy = new Animals(1234567899, "GenericName", "GenericBrand", 19.99f, 10, 3, "Plastic", "Medium");

		assertEquals(1234567899, animalToy.getSerialNumber());
		assertEquals("GenericName", animalToy.getName());
		assertEquals("GenericBrand", animalToy.getBrand());
		assertEquals(19.99f, animalToy.getPrice(), 0.01f);
		assertEquals(10, animalToy.getAvalibleCount());
		assertEquals(3, animalToy.getAgeAppropriate());
		assertEquals("Plastic", animalToy.getMaterial());
		assertEquals("Medium", animalToy.getSize());
	}

	@Test
	/**
	 * Test the setters of Animals class
	 */
	public void testSetters() {
		Animals animalToy = new Animals(0, null, null, 0, 0, 0, null, null);

		animalToy.setSerialNumber(987654321);
		assertEquals(987654321, animalToy.getSerialNumber());

		animalToy.setName("Dog");
		assertEquals("Dog", animalToy.getName());

		animalToy.setBrand("BlahBlah");
		assertEquals("BlahBlah", animalToy.getBrand());

		animalToy.setPrice(24.99f);
		assertEquals(24.99f, animalToy.getPrice(), 0.01f);

		animalToy.setAvalibleCount(5);
		assertEquals(5, animalToy.getAvalibleCount());

		animalToy.setAgeAppropriate(4);
		assertEquals(4, animalToy.getAgeAppropriate());

		animalToy.setMaterial("Wood");
		assertEquals("Wood", animalToy.getMaterial());

		animalToy.setSize("Large");
		assertEquals("Large", animalToy.getSize());
	}

	@Test
	/**
	 * Test the format of animals class
	 */
	public void testFormat() {
		Animals animalToy = new Animals(1234567899, "GenericName", "GenericBrand", 19.99f, 10, 3, "Plastic", "Medium");

		String expected = "1234567899;GenericName;GenericBrand;19.99;10;3;Plastic;Medium";
		assertEquals(expected, animalToy.format());
	}

	@Test
	/**
	 * Test the toString of Animals class
	 */
	public void testToString() {
		Animals animalToy = new Animals(1234567899, "GenericName", "GenericBrand", 19.99f, 10, 3, "Plastic", "Medium");

		String expected = "Category: Animals, Serial Number: 1234567899, Name: GenericName, Brand: GenericBrand, Price: 19.99, Availible Stock: 10, Minimum Age: 3, Material: Plastic, Size: Medium";
		assertEquals(expected, animalToy.toString());
	}
}
