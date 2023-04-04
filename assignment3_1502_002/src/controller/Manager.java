package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.MinPlayerException;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;
import model.Toys;
import view.AppMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
//Manages and runs the application

/**
* 
* Responsible for managing the whole program
* 
* @author Steven and Caesar
* @version 6.9
* 
*/
public class Manager {
	private ArrayList<Toys> toy;//ArrayList for toys object
	private AppMenu menu;//AppMenu Object
	private final String FILE_PATH = "res/toys.txt";//File Path for database
	@FXML
    private Button btnBuy;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSearch;

    @FXML
    private ListView<Toys> listSearch;

    @FXML
    private RadioButton radioName;

    @FXML
    private RadioButton radioSN;

    @FXML
    private RadioButton radioType;

    @FXML
    private ToggleGroup searchInv;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSN;
    

    @FXML
    private TextField textType;

    @FXML
    private Label nameLabel;
    
    @FXML
    private Label snLabel;
    
    @FXML
    private Label typeLabel;

	
	
public Manager() throws MinPlayerException  {
	toy = new ArrayList<>();
	menu = new AppMenu();
	loadData();
//	menuOptions();

}

@FXML
void btnHandler(ActionEvent event) {
	if (event.getSource().equals(btnSearch)) {
		if (radioSN.isSelected()) {
			String serialNumberString = textSN.getText();
			serialNumberString.trim();
			long serialNum = Long.parseLong(serialNumberString);
			searchSerial(serialNum);

			
			if (event.getSource().equals(btnBuy)) {
				for (Toys item : toy) {
					if (item.getSerialNumber()==serialNum) {
						int count = item.getAvalibleCount() ;
						count -=1 ;
						item.setAvalibleCount(count);
						}
				}
				saveExit();
			}
			//send to SN search
		}
		else if (radioName.isSelected()) {
			String name = textName.getText();
			name.trim().toLowerCase();
			searchName(name);
			//send to name search
			
		}
		else if (radioType.isSelected()) {
			String type = textType.getText();
			type.trim();
			//send to Type search
		}
		
	}
	else if (event.getSource().equals(btnClear)) {
		
	}

}

@FXML
void radioAction(ActionEvent event) {
	if(radioSN.isSelected()) {
		snLabel.setTextFill(Color.RED);
		
	}
	else if(radioName.isSelected()) {
		nameLabel.setTextFill(Color.RED);
	}

	else if (radioType.isSelected()) {
		typeLabel.setTextFill(Color.RED);
	}
}

@FXML
void txtSearchHandler(ActionEvent event) {
	

}




/**
 * This Method is resposible for displaying and running the main menu
 * 
 * @throws MinPlayerException responsible for making sure min player is not
 *                            larger than max player when adding a toy
 * 
 */

//public void menuOptions() throws MinPlayerException {
//	boolean flag = true;
//	int choice = 0;
////	boolean exceptionLoop = true;
////	while (exceptionLoop) {
//
//	while (flag) {
//		try {
//			choice = menu.showMainMenu(); // Displays Main Menu
//			switch (choice) {
//			case 1: // Search the store and purchase a toy
//				findAndPurchase();
//				break;
//			case 2: // Adds a toy
//				addToy();
//				break;
//			case 3: // Removes a toy
//				removeToy();
//				break;
//			case 4: // Saves and Exists
//				saveExit();
//				flag = false;
//				break;
//			default:
//				menu.validateOptionNotValid();
//				break;
//			}
//		} catch (InputMismatchException e) {
//			menu.validateNumNotValid();
//			menu.promptEnterKeyMainMenu();
//
//		}
//	}
//}

/**
 * This Method is responsible for displaying and running the search menu
 */

//private void findAndPurchase() {
//	boolean flag = true;
//	// use case to
//	while (flag) {
//		try {
//			int choice = menu.searchMenu();
//			switch (choice) {
//			case 1: // Search by Serial Number
//				searchSerial();
//				break;
//
//			case 2: // Search by name
//				searchName();
//				break;
//			case 3: // Search by type
//				searchType();
//				break;
//			case 4: // Exits the search menu
//				flag = false;
//				break;
//
//			}
//		} catch (InputMismatchException mismatch) {
//			menu.validateNumNotValid();
//			menu.promptEnterKey();
//
//		}
//	}
//
//}

/**
 * This Method is responsible for searching the database for a matching serial
 * number and prompting user to purchase item
 */
public void searchSerial(long serialNum) {
	boolean found = false; // Becomes true if item is found
	boolean enter = false; // Becomes true when user presses enter
	boolean exceptionLoop = true; // Used to keep looping try/catch until exception is cleared

	while (exceptionLoop) {
		try {
			for (Toys item : toy) // iterates through file array
				if (item.getSerialNumber() == serialNum && item.getAvalibleCount() > 0) {
					found = true;
					serialNum = item.getSerialNumber();
					ObservableList<Toys> t = FXCollections.observableArrayList(item);
					listSearch.getItems().addAll(t);
					
				} else if (item.getSerialNumber() == serialNum && item.getAvalibleCount() == 0) {
					menu.noStock(); // if the specific item has a stock cound of 0, then displays out of stock
					found = true;
					menu.promptEnterKey();
					break;
				}
			
			if (found != true) { // If item serial num is not in arraylist, displays item doesn't exist
				menu.doesntExist();
				menu.promptEnterKey();
			}
		
			exceptionLoop = false;
		} catch (InputMismatchException mismatch) {
			menu.validateNumNotValid();
		}
	}
}


/**
 * This Method is responsible for searching the database for a matching name and
 * prompting the user to purchase item
 */
private void searchName(String name) {
	boolean found = false; // Becomes true once item is found
	boolean enter = false; // Becomes true once user presses enter
	ArrayList<Toys> nameArray = new ArrayList<>();
	int itemCount = 0;
	int listSize = 0;
	int choice = 0;

	for (Toys item : toy) { // This for loop is responsible for iterating through the list and adding items
							// that contain the users input
		if (item.getName().toLowerCase().trim().contains(name) && item.getAvalibleCount() > 0) {
			nameArray.add(item);
			itemCount++;
			found = true;
		}

	}

	if (found != true) { // This If statement is responsible for telling user that the item they were
							// looking for was not found and going back to the search menu
		menu.doesntExist();
		enter = true;
		menu.promptEnterKey();
	}

	while (enter != true) { // This loops is responsible for dealing with purchase of item and validating
							// proper inputs when purchasing
		listSize = menu.nameSearchResults(nameArray, itemCount);
		try {

			choice = menu.promptPurchase(); // Prompts purchase
			choice -= 1;
			listSize -= 1;
			if (choice == listSize) { // If user input is equal to list size, goes back to main menu
				break;
			} else if (choice > listSize || choice < 0) { // If user input is larger than list size ot smaller than
															// the # of choices, displays error
				menu.validateOptionNotValid();
				menu.promptEnterKey();
			}

			else { // If user chooses a toy to purchase, it will -1 from stock and prints purchase
					// was successful
				Toys item = nameArray.get(choice);
				int stock = item.getAvalibleCount();
				stock -= 1;
				item.setAvalibleCount(stock);
				menu.purchaseSuccessful();

				menu.promptEnterKey(); // Asks user to press enter and breaks the while loop once done
				enter = true;
			}
		} catch (InputMismatchException mismatch) {
			menu.validateNumNotValid();
			menu.promptEnterKey();
		}
	}
}

/**
 * This Method is responsible for searching the database for a matching toy type
 * and prompting the user to purchase item
 */
private void searchType() {
	String type = menu.promptType().trim().toLowerCase(); // Prompts user to enter a type of toy
	ArrayList<Toys> nameArray = new ArrayList<>();
	int listSize = 0;
	int itemCount = 0;
	int choice = 0;
	boolean found = false;
	boolean enter = false; // Becomes true once user presses enter
	for (Toys item : toy) { // Iterates through lists and uses if statements to deterine which item shows
		if (type.equals("animals") || type.equals("animal")) { // Adds Animal toys to list and item count (Will be
																// sent to AppMenu for display later)
			if (item instanceof Animals) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					itemCount++;
					found = true;
				}
			}
		} else if (type.equals("figures") || type.equals("figure")) { // Adds Figure toys to list and item count
																		// (Will be sent to AppMenu for display
																		// later)
			if (item instanceof Figures) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					itemCount++;
					found = true;
				}
			}
		} else if (type.equals("puzzles") || type.equals("puzzle")) { // Adds Puzzle toys to list and item count
																		// (Will be sent to AppMenu for display
																		// later)
			if (item instanceof Puzzles) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					itemCount++;
					found = true;
				}
			}
		} else if (type.equals("board") || type.equals("boards") || type.equals("boardgame")
				|| type.equals("boardgames")) { // Adds Board Games toys to list and item count (Will be sent to
												// AppMenu for display later)
			if (item instanceof BoardGames) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					itemCount++;
					found = true;
				}
			}
		}
	}
	if (found != true) { // If User enters a wrong input, sends back to search menu
		menu.validateOptionNotValid();
		enter = true;
		menu.promptEnterKey();
	}

	while (enter != true) { // This loops is responsible for dealing with purchase of item and validating
							// proper inputs when purhcasing
		listSize = menu.nameSearchResults(nameArray, itemCount); // Sends array and item count to AppMenu to be
																	// displayed
		try {
			choice = menu.promptPurchase();// Prompts user which toy they want to purchase
			choice -= 1;
			listSize -= 1;
			if (choice == listSize) { // If choice is equal to the list size, goes back to menu
				break;
			} else if (choice > listSize || choice < 0) { // if input was larger than list size or smaller than 0,
															// displays invalid imput
				menu.validateOptionNotValid();
				menu.promptEnterKey();
			} else {// If user chooses a toy to purchase, it will -1 from stock and prints purchase
					// was successful

				Toys item = nameArray.get(choice);
				int stock = item.getAvalibleCount();
				stock -= 1;
				item.setAvalibleCount(stock);
				menu.purchaseSuccessful();

				menu.promptEnterKey();// Asks user to press enter and breaks the while loop once done
				enter = true;
			}

		} catch (InputMismatchException mismatch) {
			menu.validateNumNotValid();
			menu.promptEnterKey();
		}

	}
}

/**
 * This Method is responsible for adding toys to the ArrayList based on user
 * input
 * @throws MinPlayerException makes sure minplayer is not bigger than maxplayer
 */
private void addToy() throws MinPlayerException {
	boolean error = true;
	int minPlayers = 0;
	int maxPlayers = 0;
	long serialNum = getSerialNum();
	String toyName = menu.prompToyName();
	String toyBrand = menu.prompBrandName();
	float toyPrice = getToyPrice();
	int availability = getAvailability();
	int age = getAge();
	String serialNumString = Long.toString(serialNum);
	char firstVal = serialNumString.charAt(0);
	int firstNum = Character.getNumericValue(firstVal);

	if (firstNum <= 1) { // If serial number starts with 0 or 1, toy becomes a figure
		String figureClass = menu.promptFigureClass();
		Toys newFigures = new Figures(serialNum, toyName, toyBrand, toyPrice, availability, age, figureClass);
		toy.add(newFigures);
	} else if (firstNum <= 3) {// If serial number starts with 2 or 3, toy becomes a animal and prompts for any
								// extra characteristics of the type
		String material = menu.promptAnimalMaterial();
		String size = menu.promptAnimalSize();
		Toys newAnimals = new Animals(serialNum, toyName, toyBrand, toyPrice, availability, age, material, size);
		toy.add(newAnimals);
	} else if (firstNum <= 6) {// If serial number starts with 4, 5 or 6, toy becomes a puzzle and prompts for
								// any extra characteristics of the type
		String puzzleType = menu.promptPuzzleType();
		Toys newPuzzles = new Puzzles(serialNum, toyName, toyBrand, toyPrice, availability, age, puzzleType);
		toy.add(newPuzzles);
	} else if (firstNum <= 9) {// If serial number starts with 7,8 or 9, toy becomes a board game and prompts
								// for any extra characteristics of the type
		while (error) {
			minPlayers = menu.promptBoardGameMinPlayers();
			maxPlayers = menu.promptBoardGameMaxPlayers();
			if (minPlayers > maxPlayers) {
				throw new MinPlayerException("Min Player cannot be larger than Max Players");
			} else {
				error = false;
			}

		}
		String designers = menu.promptBoardGameDesigners();
		Toys newBoardGames = new BoardGames(serialNum, toyName, toyBrand, toyPrice, availability, age, minPlayers,
				maxPlayers, designers);
		toy.add(newBoardGames);
	}

	menu.toyAddedMessage();
	menu.promptEnterKey();
}

/**
 * This method is responsible for prompting and validating serial number when
 * adding toys
 * 
 * @return returns serial number user enterd
 */
private long getSerialNum() {

	long serialNum = 0;

	try {
		// prompt to enter a serial number
		serialNum = menu.promptSerialNum();

		// validate length of the serial number
		while (String.valueOf(serialNum).length() != 10) { // Validates that serial num is 10 digits
			menu.validateSNLength();
			serialNum = menu.promptSerialNum();
		}

		// check if the serial number exists
		boolean serialNumExists = true;
		while (serialNumExists) {
			serialNumExists = false;
			for (Toys item : toy) { // Validates that Serial number doesn't exist already
				if (item.getSerialNumber() == serialNum) {
					menu.validateExistingSN();
					serialNum = menu.promptSerialNum();
					serialNumExists = true;
					break;
				}
			}
		}
	} catch (InputMismatchException e) {
		menu.validateSN();
		return getSerialNum();
	}

	return serialNum;
}

/**
 * This method is responsoble for prompting and validating toy prices based on
 * user input
 * 
 * @return returns toy price user entered
 */
private float getToyPrice() {
	float toyPrice = 0;
	try {
		toyPrice = menu.promptToyPrice();
		if (toyPrice <= 0) { // If user enters toy price less or equal to 0, prints error message
			menu.validateNegativeNum();
			return getToyPrice();
		}
	} catch (InputMismatchException e) {
		menu.validateEnterNum();
		return getToyPrice();
	}
	return toyPrice;
}

/**
 * This method is responsoble for prompting and validating how much avalible
 * stock a toy has based on user input
 * 
 * @return returns availible stock user entered
 */
private int getAvailability() {
	int toyAvailability = 0;
	try {
		toyAvailability = menu.promptAvailability();
		if (toyAvailability <= 0) { // If user enters availible stock less or equal to 0, prints out error message
			menu.validateNegativeNum();
			return getAvailability();
		}
	} catch (InputMismatchException e) {
		menu.validateEnterNum();
		return getAvailability();
	}
	return toyAvailability;
}

/**
 * This method is responsible for prompting and validating minimum age of toy
 * based on user input
 * 
 * @return returns min age based on user input
 */
private int getAge() {
	int minAge = 0;
	try {
		minAge = menu.promptAge();
		if (minAge <= 0) { // If user enters min age less than 0, prints out error message
			menu.validateNegativeNum();
			return getAvailability();
		}
	} catch (InputMismatchException e) {
		menu.validateEnterNum();
		minAge = menu.promptAge();
	}
	return minAge;
}

/**
 * This method is responsible for removing toy from ArrayList
 */
private void removeToy() {
	long serialNum = getSerialNumRemoval();
	boolean found = false; // Becomes true if item is found
	boolean remove = false; // Becomes true if user decides to remove toy
	int arrayNum = 0;

	for (Toys item : toy) {
		if (item.getSerialNumber() == serialNum) {
			found = true;
			menu.serialSearchRemoval(item.toString());
			char choice = menu.promptRemoval();
			switch (choice) {
			case 'y': // Gets index of item to be removed later
				arrayNum = toy.indexOf(item);
				remove = true;
				menu.toyRemovedMessage();

			case 'n': // Goes back to main Menu
				break;
			default:
				menu.validateOptionNotValid();
				break;
			}
		}
	}
	if (remove) { // Removes item from ArrayList
		toy.remove(arrayNum);
	}
	if (found != true) {
		menu.doesntExist();
	}
}

/**
 * Prompts user for Serial Number and validates/catches exceptions for removal of toy
 * @return serialNum
 */
private long getSerialNumRemoval() {
	long serialNum = 0;
	try {
		// prompt to enter a serial number
		serialNum = menu.promptSerialNum();

		// validate length of the serial number
		while (String.valueOf(serialNum).length() != 10) { // Validates that serial num is 10 digits
			menu.validateSNLength();
			serialNum = menu.promptSerialNum();
		}
	} catch (InputMismatchException e) {
		menu.validateSN();
		return getSerialNumRemoval();
	}
	return serialNum;
}

/**
 * This Method is responsible for writing the arraylist to the txt file (save
 * and exitting)
 */
private void saveExit() {
	File txt = new File(FILE_PATH);
	try {
		PrintWriter pw = new PrintWriter(txt);
		for (int i = 0; i < toy.size(); i++) { // Iterates thorugh arrayList and writes to txt file
			pw.println(toy.get(i).format());

		}
		pw.close();

	} catch (FileNotFoundException c) {
		System.out.println("File Not Found");

	}
}

/**
 * This Method is responsible for reading the txt file and putting the elements
 * inside a arraylist
 */
private void loadData() {
	File txt = new File(FILE_PATH); // opens file
	String currentLine;
	String[] splittedLine;

	if (txt.exists()) { // checks if file exists
		// TRY HERE
		try {
			Scanner fileReader = new Scanner(txt); // Creates scanner class to read file

			while (fileReader.hasNextLine()) { // checks if the line exists

				currentLine = fileReader.nextLine(); // reads current line
				splittedLine = currentLine.split(";"); // splits line at ;
				String serialNum = splittedLine[0];
				char firstNum = serialNum.charAt(0);
				int firstNumber = Character.getNumericValue(firstNum);// Gets the first number of the serial number
				if (firstNumber <= 1 && splittedLine.length == 7) { // If serial number is 1 or 0, current line
																	// becomes a figure object
					Toys figures = new Figures(Long.parseLong(splittedLine[0]), splittedLine[1], splittedLine[2],
							Float.parseFloat(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), splittedLine[6]);
					toy.add(figures);

				} else if (firstNumber <= 3 && splittedLine.length == 8) { // If serial number is 2 or 3, current
																			// line becomes a animal object
					Toys animal = new Animals(Long.parseLong(splittedLine[0]), splittedLine[1], splittedLine[2],
							Float.parseFloat(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), splittedLine[6], splittedLine[7]);
					toy.add(animal);
				} else if (firstNumber <= 6 && splittedLine.length == 7) { // If serial number is 4, 5 or 6, current
																			// line becomes a puzzle object
					Toys puzzles = new Puzzles(Long.parseLong(splittedLine[0]), splittedLine[1], splittedLine[2],
							Float.parseFloat(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), splittedLine[6]);
					toy.add(puzzles);

				} else if (firstNumber <= 9) { // If serial number is 7, 8 or 9, current line becomes a board game
												// object
					String[] numPlayers = splittedLine[6].split("-"); // Splits the number of players into 2
																		// variabeles, min # of players and max # of
																		// players
					Toys boardGames = new BoardGames(Long.parseLong(splittedLine[0]), splittedLine[1],
							splittedLine[2], Float.parseFloat(splittedLine[3]), Integer.parseInt(splittedLine[4]),
							Integer.parseInt(splittedLine[5]), Integer.parseInt(numPlayers[0]),
							Integer.parseInt(numPlayers[1]), splittedLine[7]);
					toy.add(boardGames);
				}

			}
			fileReader.close();
		}

		// CATCH HERE
		catch (FileNotFoundException c) {
			System.out.println("File Was not Found");
			}

		}
	}
}