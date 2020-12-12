import java.util.*;
import java.io.*;


/**
 * This program creates an array of words from a external text file
 * and picks a random word from the array and scrambles it in order
 * for the player to guess what the words is
 * 
 * The player has different options -- hint, new word, guess, quit.
 * the player gets points based on how many guesses it takes to get the word
 * and the number of attempts by the player. the total score is 
 * printed at the end of each word.
 * 
 *
 * @Victor_Valdez
 * @version1
 */

public class Project4 {
	//method that gives a hint to the user
	public static String giveHint(String word) {
		Random r = new Random();
		
		int index = r.nextInt(word.length());
		char letter = word.charAt(index);
		//gives hint of random index and the character at that index
		String hint = ("The letter at spot " + (index+1) + " is " + letter);
		
		return hint;
	}
	//method that checks to see if the word and what the user guess is the same
	public static boolean checkWord(String word, String guess) {
		word.toLowerCase();
		guess.toLowerCase();
		if(word.equals(guess)) {
			return true;
		} else {
			return false;
		}
		
	}
	//a methad that takes in a word and scrambles the word
	public static String scrambleWord(String word) {
		StringBuilder scrambledWord = new StringBuilder("");
		Random r = new Random();
		//creates a boolean array to keep track if all the characters
		//in the word have been used
		boolean[] scrambled = new boolean[word.length()];
		// loops until  all of the characters have  been used
		while((scrambledWord.toString()).length() < word.length()){
			//gets a random index
			int index = r.nextInt(word.length());
			//gets the character at the index
			char character = word.charAt(index);
			//if the index of the boolean array is false then  
			//append the word to scrambled word
			if(scrambled[index] == false) {
				scrambledWord.append(character);
				scrambled[index] = true;
			}
		}
		//returns the scrambled word
		return scrambledWord.toString();
	}
	//gets a random word from the array of words recieved from the file
	public static String getWord(int length, String[] word) {
		Random r = new Random();
		//gets a random word from an array of words
		String puzzleWord = word[r.nextInt(length)];
		return puzzleWord;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		//opens the text file "words56
		Scanner inFile = new Scanner(new File("words56.txt"));
		
		//the first line of the file is the number of words in the file
		//save that into the array
		int wordAmount = Integer.parseInt(inFile.nextLine());
		
		//creates an array of words equal to the amount of words indicated
		//by the file
		String[] words = new String[wordAmount];
		//loop while the file has another line
		for(int i = 0; inFile.hasNextLine(); i++) {
			//every line has a new word
			String wordToAdd = inFile.nextLine();
			//add the word on the line to the words array
			words[i] = wordToAdd;
		}
		//starting value for a new word is 10
		int points = 10;
		//starts the player off with 0 points
		int totalPoints = 0;
		//gets a random word
		String word = getWord(wordAmount, words);
		//scrambles the word
		String scrambledWord = scrambleWord(word);
		
		//starting value for result
		boolean result = false;
		//initializes input
		char input;
		do {
			//prints some stuff like the scrambled word and points for the word
			System.out.println("Your current word is: " + scrambledWord);
			System.out.println("Current points for word: " + points);
			//urges the user to input something
			System.out.print("Enter (g)uess, (h)int, (n)ew word, or (q)uit: ");
			input = (s.nextLine().toLowerCase()).charAt(0);
			System.out.println();
			
			//a switch loop to do something for a given input
			switch(input) {
			//if the user chooses to guess, they enter a guess
			case 'g':
				System.out.print("Enter your guess: ");
				String guess = s.nextLine();
				System.out.println();
				//if they're right runs this
				if(checkWord(word, guess) == true) {
					System.out.println("You guessed it!");
					System.out.println("Score for word: " + points);
					totalPoints = totalPoints + points;
					System.out.println("Total points: " + totalPoints);
					result = true;
				//if they're wrong they lose points and get a 
				//a degrading statement from their computer :(
				} else if(checkWord(word, guess) == false) {
					System.out.println("Incorrect!");
					points -= 1;
				}
				break;
			//if they input h they get a hint but their potential points for 
			// the word gets divided by 2
			case 'h':
				System.out.println(giveHint(word));
				System.out.println();
				points = points/2;
				break;
			//if they input n they get a new word and the potential points
			//for the word resets to 10
			case 'n':
				word = getWord(wordAmount, words);
				scrambledWord = scrambleWord(word);
				points = 10;
				break;
			//if they decide to end then the get a nice statement and 
			//we print out their final score
			case 'q':
				System.out.println("Thanks for playing!");
				System.out.println("Final score: " + totalPoints);
				input = 'q';
				break;
			//if they input something else than p, q, n the do while loop resets
			default:
				System.out.println("Invalid option.");
				System.out.println();
				break;
			}
			//if they get the word right the loop resets and they get a new word
			if(result == true) {
				word = getWord(wordAmount, words);
				scrambledWord = scrambleWord(word);
				System.out.println();
				result = false;
			} else if(input == 'q') break;
			
		}while(result == false);
		//closes the files and system.in
		inFile.close();
		s.close();
	}
}