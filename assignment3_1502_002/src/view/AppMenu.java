package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Toys;
/**
 * 
 * The AppMenu class is used to show the menus and submenus to the user * It
 * also prompts the user for their inputs
 * 
 * @author Caesar and Steven
 * @version 6.9
 *
 */
public class AppMenu {

	Scanner input; // Creates a scanner object for imputs

	/*
	 * this method initializes scanner and calls welcomeMessage
	 */
	public AppMenu() { // Constructor of AppMenu, initializes scanner and calls welcomeMessage
		input = new Scanner(System.in);
 
	}

	/**
	 * displays welcome message
	 */
	public void welcomeMessage() {
		System.out.println("***********************************************");
		System.out.println("*         WELCOME TO TOYSTORE COMPANY         *");
		System.out.println("***********************************************\n");
	}

	/**
	 * displays main menu and prompts user for their choice
	 * 
	 * @return the choice user inputs as an int
	 */
	public int showMainMenu() {
		int option = 0;
		System.out.println("How May We Help You?\n");
		System.out.println("\t (1) Search Inventory and Purchase Toy");
		System.out.println("\t (2) Add New Toy");
		System.out.println("\t (3) Remove Existing Toy");
		System.out.println("\t (4) Save and Exit\n");
		System.out.println("Enter Option:");
		option = input.nextInt();

		return option;
	}

	/**
	 * displays search for toy menu
	 * 
	 * @return the choice user inputs as an int
	 */
	public int searchMenu() {
		int option = 0;
		input.nextLine();
		System.out.println("Find Toys With:\n");
		System.out.println("\t (1) Serial Number (SN)");
		System.out.println("\t (2) Toy Name");
		System.out.println("\t (3) Type");
		System.out.println("\t (4) Back to Main Menu\n");
		System.out.println("Enter Option:");
		option = input.nextInt();

		return option;
	}

	/**
	 * generic prompt for user option
	 * 
	 * @return option as an int
	 */
	public int promptOption() {
		System.out.println("Enter Option:\n");
		int option = input.nextInt();
		return option;
	}

	/**
	 * prompts user for enter key to continue
	 */
	public void promptEnterKey() {
		Scanner input = new Scanner(System.in);
		System.out.println("Press Enter to Continue...");
		input.nextLine();
	}

	/**
	 * prompts user for enter key to continue used to clear input in main menu
	 */
	public void promptEnterKeyMainMenu() { // Used to clear input in Main Menu
		input.nextLine();
		Scanner input = new Scanner(System.in);
		System.out.println("Press Enter to Continue...");
		input.nextLine();
	}

	/**
	 * prompts toy type from user used in search
	 * 
	 * @return type of toy as a String
	 */
	public String promptType() {
		System.out.println("Enter Type: ");
		String toyType = input.next().trim();
		return toyType;
	}

	/**
	 * prompts user for serial number
	 * 
	 * @return serial number as long
	 */
	public long promptSerialNum() {
		input.nextLine();
		System.out.println("Enter Serial Number: ");
		long serialNum = input.nextLong();
		return serialNum;
	}

	/**
	 * prompts toy name
	 * 
	 * @return toy name as String
	 */
	public String prompToyName() {
		System.out.println("Enter Toy Name: ");
		String toyName = input.next();
		return toyName;
	}

	/**
	 * prompts brand name
	 * 
	 * @return brand name as String
	 */
	public String prompBrandName() {
		System.out.println("Enter Brand Name: ");
		String brandName = input.next();
		return brandName;
	}

	/**
	 * prompts toy price
	 * 
	 * @return toy price as float
	 */
	public float promptToyPrice() {
		input.nextLine();
		System.out.println("Enter Toy Price: ");
		float toyPrice = input.nextFloat();
		return toyPrice;
	}

	/**
	 * prompts stock count/availability
	 * 
	 * @return stock count as int
	 */
	public int promptAvailability() {
		input.nextLine();
		System.out.println("Enter Available Counts: ");
		int availability = input.nextInt();
		return availability;
	}

	/**
	 * prompts min age
	 * 
	 * @return min age as int
	 */
	public int promptAge() {
		input.nextLine();
		System.out.println("Enter Appropriate Age: ");
		int minAge = input.nextInt();
		return minAge;
	}

	/**
	 * prompts for classification of figure
	 * 
	 * @return classification as String
	 */
	public String promptFigureClass() {
		System.out.println("Enter Figure Classification (Action, Doll, or Historic): ");
		String figureClass = input.next().toLowerCase();
		return figureClass;
	}

	/**
	 * prompts material of animal
	 * 
	 * @return material as String
	 */
	public String promptAnimalMaterial() {
		System.out.println("Enter Animal Material: ");
		String animalMaterial = input.next().toLowerCase();
		return animalMaterial;
	}

	/**
	 * prompts size of animal
	 * 
	 * @return size as String
	 */
	public String promptAnimalSize() {
		System.out.println("Enter Animal Size (Small, Medium, or Large): ");
		String animalSize = input.next().toLowerCase();
		return animalSize;
	}

	/**
	 * prompts type of puzzle
	 * 
	 * @return puzzle type as String
	 */
	public String promptPuzzleType() {
		System.out.println("Enter Puzzle Type (Mechanical, Cryptic, Logic, Trivia, or Riddle): ");
		String puzzleType = input.next().toLowerCase();
		return puzzleType;
	}

	/**
	 * prompts minimum amount of players for board games
	 * 
	 * @return min amount as int
	 */
	public int promptBoardGameMinPlayers() {
		System.out.println("Enter Minimum Number of Players: ");
		int minPlayers = input.nextInt();
		return minPlayers;
	}

	/**
	 * prompts maximum amount of players for board games
	 * 
	 * @return max amount as int
	 */
	public int promptBoardGameMaxPlayers() {
		System.out.println("Enter Maximum Number of Players: ");
		int maxPlayers = input.nextInt();
		return maxPlayers;
	}

	/**
	 * prompts for board game designers
	 * 
	 * @return names of board game designers as String
	 */
	public String promptBoardGameDesigners() {
		System.out.println("Enter Designer Names (Seperate Names Using ','): ");
		String designerName = input.next();
		return designerName;
	}

	/**
	 * this method displays the search results from name search
	 * 
	 * @param nameArray  takes arrayList to be displayed
	 * @param arrayCount used to display item count
	 * @return the options of the search
	 */
	public int nameSearchResults(ArrayList<Toys> nameArray, int arrayCount) {
		int count = 0;
		int listNum = 1;
		System.out.println("Here are the search results:\n");

		while (arrayCount > count) {
			for (Toys item : nameArray) {
				System.out.println(" (" + listNum + ") " + item.toString());
				listNum++;
				count++;
			}
			System.out.println(" (" + listNum + ") Back to Search Menu\n");

		}
		return listNum;
	}

	/**
	 * displays search results from a serial number search
	 * 
	 * @param itemString takes in the string format of matching toy to be displayed
	 */
	public void serialSearchResults(String itemString) {
		System.out.println("Here are the search results:\n");
		System.out.println(" (1) " + itemString);
		System.out.println(" (2) Back to Search Menu\n");
	}

	/**
	 * prompts user for purchase
	 * 
	 * @return the choice for purchase as int
	 */
	public int promptPurchase() {
		int option = 0;
		input.nextLine();
		System.out.println("Enter Option Number to purchase: ");
		option = input.nextInt();
		return option;
	}

	/**
	 * displays purchase successful message
	 */
	public void purchaseSuccessful() {
		System.out.println("The Transaction Successfully Terminated!\n");
	}

	/**
	 * displays no stock message
	 */
	public void noStock() {
		System.out.println("Sorry, the product you wanted to purchase is out of Stock");
	}


	/**
	 * displays new toy added message
	 */
	public void toyAddedMessage() {
		System.out.println("New Toy Added!\n");
	}

	/**
	 * displays search results for toy removal
	 * 
	 * @param itemString takes in the string format of matching toy to be displayed
	 */
	public void serialSearchRemoval(String itemString) {
		System.out.println("This Item Found:\n");
		System.out.println(itemString + "\n");
	}

	/**
	 * prompts removal of toy
	 * 
	 * @return option as char
	 */
	public char promptRemoval() {
		System.out.println("Do you want to remove it? (Y/N)");
		char option = input.next().toLowerCase().charAt(0);
		return option;
	}

	/**
	 * displays toy removed message
	 */
	public void toyRemovedMessage() {
		System.out.println("Item Removed!\n");
	}

	/**
	 * displays save and exit message
	 */
	public void saveMessage() {
		System.out.println("Saving Data Into Database\n");
		System.out.println("***********************************************");
		System.out.println("           * THANKS FOR VISTING US! *          ");
		System.out.println("***********************************************");
	}

	/**
	 * displays user input not integer message used for validation
	 */
	public void validateNumNotValid() {
		System.out.println("This is Not an Integer Number! Try Again.\n");
	}

	/**
	 * displays user input not positive message used for validation
	 */
	public void validateNegativeNum() {
		System.out.println("This is Not a Positive Number! Try Again.\n");
	}

	/**
	 * displays user input not valid message used for validation
	 */
	public void validateOptionNotValid() {
		System.out.println("This is Not an Valid Option! Try Again.\n");
	}

	/**
	 * displays user input not valid number message used for validation
	 */
	public void validateEnterNum() {
		System.out.println("This is Not an Valid Number! Try Again.\n");
	}

	/**
	 * displays serial number already exists message used for validation
	 */
	public void validateExistingSN() {
		System.out.println("A Toy With This Serial Number Already Exists! Try Again.\n");

	}

	/**
	 * displays serial number can only contain letter message used for validation
	 */
	public void validateSN() {
		System.out.println("The Serial Number should only contain Digits! Try Again.\n");

	}

	/**
	 * displays serial number must be 10 digits message used for validation
	 */
	public void validateSNLength() {
		System.out.println("The Serial Number's legnth MUST be 10 digits! Try Again.\n");

	}

	/**
	 * displays item doesn't exist message used for validation
	 */
	public void doesntExist() {
		System.out.println("Item does not exist\n");
	}

}