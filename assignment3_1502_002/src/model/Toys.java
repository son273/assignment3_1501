package model;

/**
 * 
 * The Toys class is an abstract class that provides getters and setters for
 * various attributes of a toy. this class provides methods to format and
 * represent the object as a string.
 * 
 * @author Caesar and Steven
 * @version 6.9
 * 
 */
public abstract class Toys {
	protected long serialNum; // Serial Number of Toy
	protected String name; // Name of Toy
	protected String brand; // Brand Name
	protected float price; // Price of Toy
	protected int stockCount; // Availible Stock
	protected int minAge; // Minimum Age
	

	public Toys(long serialNum, String name, String brand, float price, int stockCount, int minAge) {
		this.serialNum = serialNum;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stockCount = stockCount;
		this.minAge = minAge;
	}

	public void setSerialNumber(long serialNum) {
		this.serialNum = serialNum;
	}

	public long getSerialNumber() {
		return serialNum;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setAvalibleCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public int getAvalibleCount() {
		return stockCount;
	}

	public void setAgeAppropriate(int minAge) {
		this.minAge = minAge;
	}

	public int getAgeAppropriate() {
		return minAge;

	}

	public abstract String format();

	/**
	 * Returns a string representation of the toy object.
	 * 
	 * @return a string representation of the toy object.
	 */
	public abstract String toString();

}
