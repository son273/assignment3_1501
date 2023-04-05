package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
public class Manager implements Initializable{
	private ArrayList<Toys> toy;//ArrayList for toys object
	private AppMenu menu;//AppMenu Object
	private final String FILE_PATH = "res/toys.txt";//File Path for database
	String [] items = {"Figure", "Animals", "Puzzles", "Board Games"};

	//Propeties in Home Page:
	@FXML
    private Button btnBuy;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnClearAdd;

    @FXML
    private Button btnClearR;
    

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSaveAdd;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSearchRemove;

    @FXML
    private ComboBox combobox;

    @FXML
    private Label invalidInputs;
    
    @FXML
    private Label invalidSerialR;

    @FXML
    private ListView<Toys> listRemove;

    @FXML
    private ListView<Toys> listSearch;

    @FXML
    private Label nameLabel;

    @FXML
    private Label notExist;

    @FXML
    private RadioButton radioName;

    @FXML
    private RadioButton radioSN;

    @FXML
    private RadioButton radioType;

    @FXML
    private ToggleGroup searchInv;

    @FXML
    private Label snLabel;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSN;

    @FXML
    private TextField textType;

    @FXML
    private Label toyAdd;

    @FXML
    private Label toyRemoved;

    @FXML
    private TextField txtAgeAdd;

    @FXML
    private TextField txtBrndAdd;

    @FXML
    private TextField txtClassAdd;

    @FXML
    private TextField txtCountAdd;

    @FXML
    private TextField txtDesignersAdd;

    @FXML
    private TextField txtMaterialAdd;

    @FXML
    private TextField txtMaxPAdd;

    @FXML
    private TextField txtMinPAdd;

    @FXML
    private TextField txtNameAdd;

    @FXML
    private TextField txtPriceAdd;

    @FXML
    private TextField txtSerialAdd;

    @FXML
    private TextField txtSerialRemove;

    @FXML
    private TextField txtSizeAdd;

    @FXML
    private TextField txtTypeAdd;

    @FXML
    private Label typeLabel;
	
public Manager() throws MinPlayerException  {
	toy = new ArrayList<>();
	menu = new AppMenu();
	loadData();
}



@FXML
public Toys listViewSelected() {
	Toys item= listSearch.getSelectionModel().getSelectedItem();
	return item;

}

@FXML
public Toys listViewSelectedR() {
	Toys item= listRemove.getSelectionModel().getSelectedItem();
	return item;

}

@FXML
void btnHandler(ActionEvent event){ //Handles btn on home page
	if (event.getSource().equals(btnSearch)) { //If User presses search
		if (radioSN.isSelected()) { // If Radio button serial number is selected
			
			boolean found = false;
			String serialNumberString = textSN.getText();
			if(!serialNumberString.matches("[a-zA-Z]+")) {//Makes sure no letters are in the textfield
				if(serialNumberString != "") { // Checks the box is not empty
					serialNumberString.trim();
					long serialNum = Long.parseLong(serialNumberString);
					found = searchSerial(serialNum); //Sends to method to search and display on List view
				}else { //If not found, displays item isn't found
					notExist.setVisible(true);
				}
			}else {
				notExist.setVisible(true);
			}
		}
		else if (radioName.isSelected()) { // If Radio button name is selected
			boolean found = false;
			try {
				String name = textName.getText();
				name.trim().toLowerCase();
				if (name != "") { //Checks to make sure box isn't empty
					found = searchName(name);//sends to method to find and display on listview
				}
				else { //if empty, displays item not found
					notExist.setVisible(true);
					}	
				}
			catch(RuntimeException e) {
				System.out.println("Please enter a correct name");
			}
			
		}
		else if (radioType.isSelected()) {// If Radio button type is selected
			String type = textType.getText();
			type.trim();
			if (type != "") { //checks to make sure type isn't empty
				searchType(type); //sends to method to find and display on listview
			}
			else {
				notExist.setVisible(true);
			}
		}
	}
	if (event.getSource().equals(btnBuy)) { // If buy is pressed
		Toys choice = listViewSelected();	//Detects what object user pressed
		if (choice != null) { //Validates user pressed a item
			for (Toys item : toy) {//Goes through array
				if (item.getSerialNumber()==choice.getSerialNumber()) {//Gets item
					int count = item.getAvalibleCount(); 
					count -=1;
					item.setAvalibleCount(count);
					}
				else {
	
				}
				
				}
			}
		saveExit(); //Saves to txtfile
		}
	 if (event.getSource().equals(btnClear)) { //If Clear is pressed, clears all fields
		textSN.clear();
		textName.clear();
		textType.clear();
		listSearch.getItems().clear();
		notExist.setVisible(false);
	}
	 
	
	
}

@FXML
void btnHandlerAdd(ActionEvent event) throws MinPlayerException { //Handles btn's on Add page
	toyAdd.setVisible(false);
	String serialNumS = txtSerialAdd.getText();
	String name = txtNameAdd.getText();
	String brand = txtBrndAdd.getText();
	String priceS = txtPriceAdd.getText();
	String countS = txtCountAdd.getText();
	String ageS = txtAgeAdd.getText();
	String classification = txtClassAdd.getText();
	String material = txtMaterialAdd.getText();
	String size = txtSizeAdd.getText();
	String type = txtTypeAdd.getText();
	String minPS = txtMinPAdd.getText();
	String maxPS = txtMaxPAdd.getText();
	String designers = txtDesignersAdd.getText();
	if (serialNumS.matches("[a-zA-Z]+") || priceS.matches("[a-zA-Z]+") || countS.matches("[a-zA-Z]+") || ageS.matches("[a-zA-Z]+") || minPS.matches("[a-zA-Z]+") || maxPS.matches("[a-zA-Z]+")) //Validates user didn't enter any numbers
	{

	}
	else {
		if (serialNumS != "" && name != "" && brand !="" && priceS !="" && countS !="" && ageS !="") { //Makes sure fields aren't empty

			Long serialNum = Long.parseLong(serialNumS);
			Float price= Float.parseFloat(priceS);
			int count = Integer.parseInt(countS);
			int age = Integer.parseInt(ageS);
			
			boolean added = addToy(serialNum,name,brand,price,count,age,classification,material,size,type,minPS,maxPS,designers); //sends to method to add to txt
			if(added == true) { //Displays item added if it is added
				toyAdd.setVisible(true);
			}
		}
		else {

		}
	}
}

@FXML
void btnHandlerClearAdd(ActionEvent event) { //Handles the clear button in Add Page(Added After finishing everything)
	txtSerialAdd.clear();
	txtNameAdd.clear();
	txtBrndAdd.clear();
	txtPriceAdd.clear();
	txtCountAdd.clear();
	txtAgeAdd.clear();
	txtClassAdd.clear();
	txtMaterialAdd.clear();
	txtSizeAdd.clear();
	txtTypeAdd.clear();
	txtMinPAdd.clear();
	txtMaxPAdd.clear();
	txtDesignersAdd.clear();
}

@FXML
void btnHandlerRemove(ActionEvent event) { //Handles events in remove page
	if (event.getSource().equals(btnSearchRemove)) { //If search is pressed
		invalidSerialR.setVisible(false);
		toyRemoved.setVisible(false);
		String serialNumberString = txtSerialRemove.getText();
		boolean found  = false;
		if(!serialNumberString.matches("[a-zA-Z]+")) {//Makes sure no letters are in the textfield
			if(serialNumberString != "") { //Makes sure serial txt field isn't empty
				serialNumberString.trim();
				long serialNum = Long.parseLong(serialNumberString);
				found = removeToy(serialNum); //Sends to method to find item and display on listview
			}else {
				invalidSerialR.setVisible(true);
			}
			if (found != true) {
				invalidSerialR.setVisible(true);
			}
		
		}else {
			invalidSerialR.setVisible(true);
		}
	}
	if (event.getSource().equals(btnClearR)) { //If clear is pressed, clears all fields
		txtSerialRemove.clear();
		listRemove.getItems().clear();
		invalidSerialR.setVisible(false);
		toyRemoved.setVisible(false);
	}
	if (event.getSource().equals(btnRemove)) { //If remove is pressed
		int arrayNum = 0;
		Toys choice = listViewSelectedR();	//Detects what object user pressed
		if (choice != null) { //validates user has pressed a item
			for (Toys item : toy) {
				if (item.getSerialNumber()==choice.getSerialNumber()) {
					arrayNum = toy.indexOf(item); //gets index of item user selected
					break;
					}
				else {
	
				}
				
			}
			toy.remove(arrayNum); //removes item
			toyRemoved.setVisible(true);
		}
		saveExit(); //saves to txt file
	}

}

@FXML
void radioAction(ActionEvent event) { //handles what fields are availible when using radio buttons in home page and any physical aspects of it
	if(radioSN.isSelected()) {
		notExist.setVisible(false);
		textSN.setPromptText("Enter Type Here");
		textName.setPromptText("");
		textSN.setDisable(false);
		textName.setDisable(true);
		textType.setDisable(true);
		textType.setPromptText("");
		snLabel.setTextFill(Color.RED);
		nameLabel.setTextFill(Color.BLACK);
		typeLabel.setTextFill(Color.BLACK);
		
	}
	else if(radioName.isSelected()) {
		notExist.setVisible(false);
		textName.setPromptText("Enter Type Here");
		textType.setPromptText("");
		textSN.setPromptText("");
		textSN.setDisable(true);
		textName.setDisable(false);
		textType.setDisable(true);
		nameLabel.setTextFill(Color.RED);
		typeLabel.setTextFill(Color.BLACK);
		snLabel.setTextFill(Color.BLACK);
	}

	else if (radioType.isSelected()) {
		notExist.setVisible(false);
		textType.setPromptText("Enter Type Here");
		textName.setPromptText("");
		textSN.setPromptText("");
		textSN.setDisable(true);
		textName.setDisable(true);
		textType.setDisable(false);
		typeLabel.setTextFill(Color.RED);
		snLabel.setTextFill(Color.BLACK);
		nameLabel.setTextFill(Color.BLACK);
	}
}
@FXML
void comboHandler(ActionEvent event) { //Basically handles what fields are availible when using combo box in add toys page
	
	if (combobox.getValue() == items[0]) { //Figure
		txtSizeAdd.setDisable(true);
		txtMaterialAdd.setDisable(true);
		txtMinPAdd.setDisable(true);
		txtMaxPAdd.setDisable(true);
		txtDesignersAdd.setDisable(true);
		txtTypeAdd.setDisable(true);
		txtClassAdd.setDisable(false);
	}
	else if (combobox.getValue() == items[1]) {//Animals
		txtSizeAdd.setDisable(false);
		txtMaterialAdd.setDisable(false);
		txtMinPAdd.setDisable(true);
		txtMaxPAdd.setDisable(true);
		txtDesignersAdd.setDisable(true);
		txtTypeAdd.setDisable(true);
		txtClassAdd.setDisable(true);
	}
	else if (combobox.getValue() == items[2]) {//Puzzle
		txtSizeAdd.setDisable(true);
		txtMaterialAdd.setDisable(true);
		txtMinPAdd.setDisable(true);
		txtMaxPAdd.setDisable(true);
		txtDesignersAdd.setDisable(true);
		txtTypeAdd.setDisable(false);
		txtClassAdd.setDisable(true);
	}
	else if (combobox.getValue() == items[3]) {//Board Games
		txtSizeAdd.setDisable(true);
		txtMaterialAdd.setDisable(true);
		txtMinPAdd.setDisable(false);
		txtMaxPAdd.setDisable(false);
		txtDesignersAdd.setDisable(false);
		txtTypeAdd.setDisable(true);
		txtClassAdd.setDisable(true);
	}
}




/**
 * This Method is responsible for searching the database for a matching serial
 * number and displaying on list view
 * @return returns true if found
 */
public boolean searchSerial(long serialNum) {
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
					notExist.setVisible(false);
					return found;
					
				} else if (item.getSerialNumber() == serialNum && item.getAvalibleCount() == 0) {
					break;
				}
			
			if (found != true) { // If item serial num is not in arraylist, displays item doesn't exist
				notExist.setVisible(true);
			}
			
			exceptionLoop = false;

		} catch (InputMismatchException mismatch) {
			menu.validateNumNotValid();
		}
	}
	return found;
}


/**
 * This Method is responsible for searching the database for a matching name and
 * displaying on list view
 * @return returns true if found
 */
private boolean searchName(String name) {
	boolean found = false; // Becomes true once item is found
	boolean enter = false; // Becomes true once user presses enter
	ArrayList<Toys> nameArray = new ArrayList<>();

	for (Toys item : toy) { // This for loop is responsible for iterating through the list and adding items
							// that contain the users input
		if (item.getName().toLowerCase().trim().contains(name) && item.getAvalibleCount() > 0) {
			nameArray.add(item);
			found = true;
		}
	}
	ObservableList<Toys> t = FXCollections.observableArrayList(nameArray);
	listSearch.getItems().addAll(t);
	
	if (found != true) { //If not found, displays not found
		notExist.setVisible(true);
	}
		return found;
	}


/**
 * This Method is responsible for searching the database for a matching toy type
 * and displaying on listview
 */
private void searchType(String type) {
	ArrayList<Toys> nameArray = new ArrayList<>();
	boolean found = false;
	boolean enter = false; // Becomes true once user presses enter
	for (Toys item : toy) { // Iterates through lists and uses if statements to deterine which item shows
		if (type.equals("animals") || type.equals("animal")) { // Adds Animal toys to list and item count (Will be
																// sent to AppMenu for display later)
			if (item instanceof Animals) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					found = true;
				}
			}
		} else if (type.equals("figures") || type.equals("figure")) { // Adds Figure toys to list and item count
																		// (Will be sent to AppMenu for display
																		// later)
			if (item instanceof Figures) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					found = true;
				}
			}
		} else if (type.equals("puzzles") || type.equals("puzzle")) { // Adds Puzzle toys to list and item count
																		// (Will be sent to AppMenu for display
																		// later)
			if (item instanceof Puzzles) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					found = true;
				}
			}
		} else if (type.equals("board") || type.equals("boards") || type.equals("boardgame")
				|| type.equals("boardgames")) { // Adds Board Games toys to list and item count (Will be sent to
												// AppMenu for display later)
			if (item instanceof BoardGames) {
				if (item.getAvalibleCount() > 0) {
					nameArray.add(item);
					found = true;
				}
			}
		}
	}
	if (found != true) { // If User enters a wrong input, sends back to search menu
		notExist.setVisible(true);
	}
	ObservableList<Toys> t = FXCollections.observableArrayList(nameArray);
	listSearch.getItems().addAll(t);
}

/**
 * Adds a toy to the array list.
 *
 * @param serialNum      The serial number of the toy.
 * @param name           The name of the toy.
 * @param brand          The brand of the toy.
 * @param price          The price of the toy.
 * @param count          The count of the toy.
 * @param age            The age requirement of the toy.
 * @param classification The classification of the toy (for Figures).
 * @param material       The material of the toy (for Animals).
 * @param size           The size of the toy (for Animals).
 * @param type           The type of the toy (for Puzzles).
 * @param minPS          The minimum number of players (for BoardGames).
 * @param maxPS          The maximum number of players (for BoardGames).
 * @param designers      The designers of the toy (for BoardGames).
 * @return               true if the toy is added successfully
 * @throws MinPlayerException If the minimum number of players is larger than the maximum number of players.
 */
private boolean addToy(Long serialNum, String name, String brand, float price, int count, int age, String classification, String material, String size, String type, String minPS, String maxPS, String designers ) throws MinPlayerException {
	boolean error = true;
	boolean allCorrect = false;
	boolean addedToy = false;
	int minP = 0;
	int maxP = 0;
	int minPlayers = 0;
	int maxPlayers = 0;
	boolean serialNumC =validateSerialNum(serialNum);
	boolean priceC = validateToyPrice (price);
	boolean countC = validateCount(count);
	boolean ageC = validateAge(age);
	allCorrect = validateAll(serialNumC,priceC,countC,ageC); //Makes sure all the above are validated
	
	
	if (allCorrect!=false) {
		String serialNumString = Long.toString(serialNum);
		char firstVal = serialNumString.charAt(0);
		int firstNum = Character.getNumericValue(firstVal);
	
		if (firstNum <= 1) { // If serial number starts with 0 or 1, toy becomes a figure
			if (classification != "");{
				Toys newFigures = new Figures(serialNum, name, brand, price, count, age, classification);
				toy.add(newFigures);
				addedToy = true;
		}
		} else if (firstNum <= 3) {// If serial number starts with 2 or 3, toy becomes a animal and prompts for any
									// extra characteristics of the type
			if (material != ""&& size !="") {
				Toys newAnimals = new Animals(serialNum, name, brand, price, count, age, material, size);
				toy.add(newAnimals);
				addedToy = true;
			}
		} else if (firstNum <= 6) {// If serial number starts with 4, 5 or 6, toy becomes a puzzle and prompts for
									// any extra characteristics of the type
			if(type != "") {
				Toys newPuzzles = new Puzzles(serialNum, name, brand, price, count, age, type);
				toy.add(newPuzzles);
				addedToy = true;
			}
		} else if (firstNum <= 9) {// If serial number starts with 7,8 or 9, toy becomes a board game and prompts
								// for any extra characteristics of the type
			if(minPS != "" && maxPS != "" && designers !="") {
				while (error) {
					minP = Integer.parseInt(minPS);
					maxP = Integer.parseInt(maxPS);
					if (minPlayers > maxPlayers) {
						throw new MinPlayerException("Min Player cannot be larger than Max Players");
					} else {
						error = false;
					}
		
				}
				
				Toys newBoardGames = new BoardGames(serialNum, name, brand, price, count, age, minP,
						maxP, designers);
				toy.add(newBoardGames);
				addedToy = true;
			}
		}
	}
	toyAdd.setVisible(false);
	saveExit();
	return addedToy;
}

/**
 * This method is responsible for  validating serial number when
 * adding toys
 * 
 * @return returns boolean if validated
 */
private Boolean validateSerialNum(Long serialNum) {
	boolean correct = true;
	try {
		

		// validate length of the serial number
		while (String.valueOf(serialNum).length() != 10) { // Validates that serial num is 10 digits
		}

		// check if the serial number exists
		boolean serialNumExists = true;
		while (serialNumExists) {
			serialNumExists = false;
			for (Toys item : toy) { // Validates that Serial number doesn't exist already
				if (item.getSerialNumber() == serialNum) {
					break;
				}
			}
		}
	} catch (InputMismatchException e) {
		return validateSerialNum(serialNum);
	}

	return correct;
}

/**
 * This method is responsoble for validating toy prices based on
 * user input
 * 
 * @return returns boolean if validated
 */
private boolean validateToyPrice(float price) {
	boolean correct = true;
	try {
		if (price <= 0) { // If user enters toy price less or equal to 0, prints error message
			correct = false;
			return correct;
		}
	} catch (InputMismatchException e) {
		correct = false;
		return validateToyPrice(price);
	}
	return correct;
}

/**
 * This method is responsoble for   validating how much avalible
 * stock a toy has based on user input
 * 
 * @return returns boolean if validated
 */
private boolean validateCount(int count) {
	boolean correct = true;
	try {
		if (count <= 0) { // If user enters availible stock less or equal to 0, prints out error message
			correct = false;
			return correct;
		}
	} catch (InputMismatchException e) {
		correct = false;
		return validateCount(count);
	}
	return correct;
}

/**
 * This method is responsible for validating minimum age of toy
 * based on user input
 * 
 * @return returns boolean if it is validated
 */
private boolean validateAge(int age) {
	boolean correct = true;
	try {
		if (age <= 0) { // If user enters min age less than 0, prints out error message
			correct = false;
			return correct;
		}	
		
	} catch (InputMismatchException e) {
		correct = false;
		return validateAge(age);
	}
	return correct;
}
/**
 * Validates all the given boolean conditions and returns true if all are true.
 * 
 * @param serialNumC a boolean representing the serial number condition
 * @param priceC a boolean representing the price condition
 * @param countC a boolean representing the count condition
 * @param ageC a boolean representing the age condition
 * @return true if all the conditions are true, otherwise false
 */
private boolean validateAll(boolean serialNumC, boolean priceC, boolean countC, boolean ageC) { 
	boolean correct = false;
	if (serialNumC == true && priceC == true && countC ==true && ageC ==true) {
		correct = true;
	}
	return correct;
	
}

/**
 * This method is responsible for displaying item in arrayList
 */
private boolean removeToy(long serialNum) {
	boolean found = false; // Becomes true if item is found

	for (Toys item : toy) {
		if (item.getSerialNumber() == serialNum) {
			found = true;
			ObservableList<Toys> e = FXCollections.observableArrayList(item); //Displays item
			listRemove.getItems().addAll(e);
			}
		}
	
	
	if (found != true) {
		invalidSerialR.setVisible(true);
	}
	return found;
}



/**
 * This Method is responsible for writing the arraylist to the txt file (saving)
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



@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	combobox.getItems().addAll(items);
	combobox.getSelectionModel().select(0);
	txtSizeAdd.setDisable(true);
	txtMaterialAdd.setDisable(true);
	txtMinPAdd.setDisable(true);
	txtMaxPAdd.setDisable(true);
	txtDesignersAdd.setDisable(true);
	txtTypeAdd.setDisable(true);
	txtClassAdd.setDisable(false);
	toyAdd.setVisible(false);

	
}



}