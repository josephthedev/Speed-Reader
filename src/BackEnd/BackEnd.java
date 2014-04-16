package BackEnd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Character;

public class BackEnd {
	String newWord;
	ArrayList<String> textList = new ArrayList<String>();
	public File speedReadMe = new File(
			"C:\\Users\\josde_000\\workspace\\MyFirstApp\\SpeedReadGUI\\src\\BackEnd\\Input");

	Scanner scan = null;

	public void takeInputToArrayList() {

		try {
			scan = new Scanner(new BufferedReader(new FileReader(speedReadMe)));
			while (scan.hasNext()) {
				String activeWord = scan.next();
				textList.add(activeWord);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}

	public void readWordFromArrayList() {

		// Identify the specific WORD
		for (String word : textList) {
			// Identify Red Letter INDEX
			int redLetterIndex = determineRedLetterIndex(word);

			// Convert specific word to iterable Char array
			char[] wordCharArray = word.toCharArray();

			// Test to see if element is a Letter
			ArrayList<Character> moddedCharArray = new ArrayList<Character>();
			for (char letter : wordCharArray) {
				boolean isLetter = Character.isLetter(letter);
				if (isLetter == true) {
					moddedCharArray.add(letter);
				}
			}
			int abc = 0;
			for (char element : moddedCharArray) {
				String newElement = Character.toString(element);
				if (abc == redLetterIndex) {					
				} else {
					System.out.print(newElement.toLowerCase());
				}
				abc += 1;
			}
			System.out.println(" ");
		}
	}

	// Point"
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

		return index;

	}

	public static void main(String[] args) {

	}

	public BackEnd() {
		// Constructor
	}

}
