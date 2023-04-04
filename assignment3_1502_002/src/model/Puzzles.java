package model;

/**
 * The Puzzles class is a subclass of the Toys class and represents a type of
 * toy that is a puzzle. It contains information such as the serial number,
 * name, brand, price, stock count, minimum age, and type.
 * 
 * @author Steven and Caesar
 * @version 6.9
 * 
 */
public class Puzzles extends Toys {

	private String type; // Type of puzzle

	/**
	 * Constructor of Puzzle
	 * 
	 * @param serialNum  initializes serial number
	 * @param name       initializes name
	 * @param brand      initializes brand
	 * @param price      initializes price
	 * @param stockCount initializes availible stock
	 * @param minAge     initializes minimum age
	 * @param type       initializes type of puzzle
	 */
	public Puzzles(long serialNum, String name, String brand, float price, int stockCount, int minAge, String type) {
//		super(serialNum, name, brand, price, stockCount, minAge);
		super(serialNum, name, brand, price, stockCount, minAge);
		this.type = type;
	}

	/**
	 * Sets type of puzzle to user input
	 * 
	 * @param type sets setType
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the type
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	@Override
	/**
	 * Formats string in format of txt file
	 */
	public String format() {
		return serialNum + ";" + name + ";" + brand + ";" + price + ";" + stockCount + ";" + minAge + ";" + type;
	}

	@Override
	/**
	 * Formats fields in a leagible format
	 */
	public String toString() {
		return "Category: Puzzles, " + "Serial Number: " + serialNum + ", Name: " + name + ", Brand: " + brand
				+ ", Price: " + price + ", Availible Stock: " + stockCount + ", Minimum Age: " + minAge + ", Type: "
				+ type;
	}

}