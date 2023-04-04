package model;

/**
 * The Animals class is a subclass of the Toys class and represents a type of
 * toy that is an animal. It contains information such as the serial number,
 * name, brand, price, stock count, minimum age, material, and size.
 * 
 * @author Caesar and Steven
 * @version 6.9
 */

public class Animals extends Toys {

	private String material; // Material of toy
	private String size; // Size of toy

	/**
	 * Creates a new animal toy with the specified attributes.
	 * 
	 * @param serialNum  the serial number of the animal toy
	 * @param name       the name of the animal toy
	 * @param brand      the brand of the animal toy
	 * @param price      the price of the animal toy
	 * @param stockCount the number of animal toys in stock
	 * @param minAge     the minimum age that the animal toy is appropriate for
	 * @param material   the material that the animal toy is made of
	 * @param size       the size of the animal toy
	 */

	public Animals(long serialNum, String name, String brand, float price, int stockCount, int minAge, String material,
			String size) {
//		super(serialNum, name, brand, price, stockCount, minAge);
		super(serialNum, name, brand, price, stockCount, minAge);
		this.material = material;
		this.size = size;
	}

	/**
	 * returns material of animal toy
	 * 
	 * @return material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * sets the material of animal toy base on user input
	 * 
	 * @param material sets material
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * returns size of animal toy
	 * 
	 * @return size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * sets the size of animal toy based on user input
	 * 
	 * @param size sets size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Formats string in format of .txt file
	 */
	@Override
	public String format() {
		return serialNum + ";" + name + ";" + brand + ";" + price + ";" + stockCount + ";" + minAge + ";" + material
				+ ";" + size;
	}

	/**
	 * Formats fields in a legible format
	 */
	@Override
	public String toString() {
		return "Category: Animals, " + "Serial Number: " + serialNum + ", Name: " + name + ", Brand: " + brand
				+ ", Price: " + price + ", Availible Stock: " + stockCount + ", Minimum Age: " + minAge + ", Material: "
				+ material + ", Size: " + size;
	}

}