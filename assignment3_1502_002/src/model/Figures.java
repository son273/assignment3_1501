package model;

/**
 * The Figures class is a subclass of the Toys class and represents a type of
 * toy that is a figure. It contains information such as the serial number,
 * name, brand, price, stock count, minimum age, material, and size.
 * 
 * @author caesar and steven
 * @version 6.9
 * 
 */
public class Figures extends Toys {

	private String classification; // Classification of Figure toy

	/**
	 * 
	 * Creates a new animal toy with the specified attributes.
	 * 
	 * @param serialNum,      the serial number of the figure
	 * @param name,           the name of the figure
	 * @param brand,          the brand of the figure
	 * @param price,          the price of the figure
	 * @param stockCount,     the number of figure in stock
	 * @param minAge,         the minimum age that the figure is appropriate for
	 * @param classification, the classification of figure
	 * 
	 */
	public Figures(long serialNum, String name, String brand, float price, int stockCount, int minAge,
			String classification) {
//		super(serialNum, name, brand, price, stockCount, minAge);
		super(serialNum, name, brand, price, stockCount, minAge);
		this.classification = classification;
	}

	/**
	 * returns the classification of figure
	 * 
	 * @return classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * sets classification based on user input
	 * 
	 * @param classification sets the classification of the figure
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * Formats string in format of .txt file
	 */
	@Override
	public String format() {
		return "0" + serialNum + ";" + name + ";" + brand + ";" + price + ";" + stockCount + ";" + minAge + ";"
				+ classification;
	}

	/**
	 * Formats fields in a legible format
	 */
	@Override
	public String toString() {
		return "Category: Figures, " + "Serial Number: 0" + serialNum + ", Name: " + name + ", Brand: " + brand
				+ ", Price: " + price + ", Availible Stock: " + stockCount + ", Minimum Age: " + minAge
				+ ", Classification: " + classification;
	}

}
