package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	ArrayList<String> textList = new ArrayList<String>();
	public File speedReadMe = new File(
			"C:\\Users\\Qs\\Desktop\\workspace\\SpeedReadGUI\\src\\application\\output");
	ArrayList<String> finishedWord;
	Scanner scan;
	String theWord;
	int redLetterIndex;
	char[] finishedWordChar;
	String finishedWordString;
	String theWords;
	@SuppressWarnings("rawtypes")
	ArrayList<ArrayList> superList = new ArrayList<ArrayList>();
	String bang;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("Joe's Spreeder");

		// ROOT NODE
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(15);
		grid.setPadding(new Insets(15, 15, 15, 15));

		// WIDGETS
		Text scenetitle = new Text("Speed Read 1.0");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Enter text you'd like to Speed Read:");
		grid.add(userName, 0, 1);

		final TextArea userTextField = new TextArea("Copy text to read here.");

		grid.add(userTextField, 0, 1, 4, 1);

		// SUBMIT BUTTON
		Button btn = new Button("Spreed!");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 0, 3, 2, 1);

		// SET BTN ACTION
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				theWords = userTextField.getText();
				bigBang(takeInputFromUser());
				try (Scanner outputScanner = new Scanner(new BufferedReader(
						new FileReader(speedReadMe)))) {
					readWindow();
					while (outputScanner.hasNext()) {

						String wordDone = outputScanner.next();
						bang = wordDone;

					}

				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}

			}
		});

		// SEE GRID LINES
		// grid.setGridLinesVisible(true);

		// SCENE
		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	void changeReadWindowLabel(Stage x){
		
		
		
	}

	Stage readWindow() {
		Label secondLabel = new Label("Start!");
		StackPane secondaryLayout = new StackPane();
		secondaryLayout.getChildren().add(secondLabel);
		Scene secondScene = new Scene(secondaryLayout, 200, 100);
		Stage secondStage = new Stage();
		secondStage.setTitle("READER");
		secondStage.setScene(secondScene);
		// Set position of second window, related to primary window.
		secondStage.setX(550);
		secondStage.setY(300);

		secondStage.show();
		return secondStage;
	}

	public Scanner takeInputFromUser() {
		// Put words into ArrayList
		scan = new Scanner(theWords);
		return scan;
	}

	public void bigBang(Scanner y) {

		while (scan.hasNext()) {
			String activeWord = scan.next();
			textList.add(activeWord);
		}

		// Identify the specific WORD
		for (String word : textList) {
			superList.add(fixWord(word));
		}
		try (BufferedWriter outPut = new BufferedWriter(new FileWriter(
				speedReadMe));) {
			for (int i = 0; i < superList.size(); i++) {

				// System.out.println(superList.get(i));

				for (int z = 0; z < superList.get(i).size(); z++) {
					// System.out.print(superList.get(i).get(z));
					outPut.write((String) superList.get(i).get(z));

				}

				// System.out.println(" ");
				outPut.newLine();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Remove Symbols; Capitalise REDLETTER INDEX; Save word to ArrayList
	public ArrayList<String> fixWord(String x) {

		// Identify Red Letter INDEX
		redLetterIndex = determineRedLetterIndex(x);
		// Convert specific word to iterable Char array
		char[] wordCharArray = x.toCharArray();
		// Test to see if element is a Letter
		ArrayList<Character> almostFinishedWord = new ArrayList<Character>();
		for (char letter : wordCharArray) {
			boolean isLetter = Character.isLetter(letter);
			if (isLetter == true)
				almostFinishedWord.add(letter);
		}
		int abc = 0;
		finishedWord = new ArrayList<String>();
		for (char letter1 : almostFinishedWord) {
			String newLetter = Character.toString(letter1);
			// System.out.print(" abc: " + " " + abc);
			if (abc == redLetterIndex) {
				newLetter = newLetter.toUpperCase();
			} else {
				newLetter = newLetter.toLowerCase();
			}
			finishedWord.add(newLetter);
			abc += 1;

		}
		return finishedWord;
	}

	int determineRedLetterIndex(String x) {
		int index = 0;
		char[] newX = x.toCharArray();
		int wordLength = newX.length;

		for (char element : newX) {
			boolean isLetter = Character.isLetter(element);
			if (isLetter == false) {
				wordLength -= 1;
			}
		}
		// System.out.print("Word length: " + wordLength + " ");
		if (wordLength == 1) {
			index = 0;
		} else if (wordLength == 2) {
			index = 1;
		} else if (wordLength == 3) {
			index = 1;
		} else if (wordLength > 3) {

			int a = wordLength / 2;
			if (wordLength <= 5) {
				index = (a - 1);
			} else if (wordLength <= 8) {
				index = (a - 1);
			} else if (wordLength > 8) {
				index = (a - 2);
			}
		}
		// System.out.print("final index: " + index + " ");
		return index;

	}

}
