import java.util.*;
import java.io.*;

/**
 * This project is able to find the most popular names for children born in a certain year
 * The program allows the user to input a year and find the rank of a certain name
 * They can find a name by rank
 * or they can find the up to the top 20 names.
 * 
 * This works for both genders and each gender is ranked separately
 * @author Victor Valdez
 * @version project 5
 * Your lab section Friday 7:30am
 */
public class Project5 {
	public static String[] mnames;//male names
	public static String[] fnames;//female names
	public static Scanner s; //for getting input
	
	/**
	 * This method reads through and input file and checks to see how many lines there are
	 * for each gender, assuming the input file has the syntax of (name, gender, rank);
	 *
	 * @param the first parameter is a string which is the file name to open
	 * @param the second parameter is a character which should be the sex to find(male or female)
	 * @return the number of names with a certain gender is the output
	 */
	public static int numNames(String filename, char sex) throws IOException {
		//creates a new scanner that takes a file name
		Scanner file = new Scanner(new File(filename));
		//starting value for numNames 
		int numNames = 0;
		//while loop that looks for a gender and adds the number of naems with that gender
		//to the numNames int
		while (file.hasNextLine()) {
			//splits each line into a String array, where the second value in the array is the sex
			String[] name = (file.nextLine()).split(",");
			//if the second value in the array is equal to the sex incriment numNames
			if(name[1].equalsIgnoreCase(Character.toString(sex))) {
				numNames++;
				
			}
			
		}
		
		//close the file and return num names
		file.close();
		return numNames;
	}
	
	/**
	 * This program goes through each line of a file and reads the number of male and female
	 * names, and allocates space for an array of male names and an array of female names
	 * then the file fills the array with the data from the file which it is reading.
	 *
	 * @param the first parameter is a string of the filename that the method should open
	 * @return nothing is returned, the method only allocates space and gives data to an array.
	 */
	public static void readNamesData(String filename) throws IOException {
		Scanner file = new Scanner(new File(filename));
		//allocates space for male and female arrays
		mnames = new String[numNames(filename, 'M')];
		fnames = new String[numNames(filename, 'F')];
		
		
		int male = 0;
		int female = 0;
		
		//fills the array of each gender 
		while(file.hasNextLine()) {
			String[] nameData = file.nextLine().split(",");
			if(nameData[1].equalsIgnoreCase("M")) {
				mnames[male] = nameData[0];
				male++;
			}
			else if(nameData[1].equalsIgnoreCase("F")) {
				fnames[female] = nameData[0];
				female++;
			}
		}
		
		file.close();
	}
	
	/**
	 * this method gets the rank of a certain name for the gender given to the array
	 *
	 * @param the first parameter is a string which is the name to look for in the array
	 * @param the second parameter is the sex which tells the method which array to look in
	 * @return an integer of the rank is the output for this method
	 */
	public static int getRank(String name, char sex) {
		int rank = 0;
		//switch statement depending on what the sex we want to look for
		switch(sex) {
		//if the sex is female, find the rank of the name and add one
		case 'F':
			for(int i = 0; i < fnames.length; i++) {
				if(fnames[i].equalsIgnoreCase(name)) rank = i+1;
			}
			break;
		case 'M':
			for(int i = 0; i < mnames.length; i++) {
				if(mnames[i].equalsIgnoreCase(name)) rank = i+1;
				}
			break;	
		}
		//returns the rank for the given name
		return rank;
	} 
	
	/**
	 * this is the name option for when a user wants to search the file by name
	 *
	 *
	 * @return what is printed is the rank for the name in both male and female categories
	 */
	public static void nameOption() {
		//prompts the user to give a name
		System.out.print("Enter a name: ");
		String name = (s.nextLine());
		//gets the rank of the name using the getRank method for each gender
		int maleRank = getRank(name, 'M');
		int femaleRank = getRank(name, 'F');
		
		//if the name is ranked, print the name and its rank, otherwise say it is not ranked
		if(maleRank != 0) {
			System.out.println(name + " is ranked " + maleRank + " for males");
		}
		else {
			System.out.println(name + " is not ranked for males");
		}
		if(femaleRank != 0) {
			System.out.println(name + " is ranked " + femaleRank + " for females");
		}
		else {
			System.out.println(name + " is not ranked for females");
		}
	}
	
	/**
	 * this is the rank option for when a user wants to search the file by rank
	 *
	 *
	 * @return what is printed is the name for the given rank in both male and female categories
	 */
	public static void rankOption() {
		//takes a rank
		int rank;
		//if the rank is not out of range of the length of either array then find the name
		//and print the name and its rank.
		do {
			System.out.print("Enter a rank: ");
			rank = Integer.parseInt(s.nextLine());
			if(rank <= 0 || rank > mnames.length || rank > fnames.length) {
				System.out.println("Invalid input, Try again");
				continue;
			} else {
				System.out.println(mnames[rank-1] + " is the #" + rank + " male name");
				System.out.println(fnames[rank-1] + " is the #" + rank + " female name");
				break;
			}
		}while(rank < 1 || rank > mnames.length || rank >fnames.length);
		
		
	}
	
	/**
	 * this is the top option for when a user wants to search the file by the top x names
	 *
	 *
	 * @return what is printed is the names in the given range for both male and female categories
	 */
	public static void topOption() {
		//if the range typed in by the user is within the ranges sepicifed this program will run.
		int top;
		do {
			System.out.print("Enter size of top list(1-20): ");
			top = Integer.parseInt(s.nextLine());
			
			if(top < 1 || top > 20) {
				System.out.println("Invalid range, try again");
				continue;
			}
			//this will print out the names in the each array for females and males in the given range
			else {
				System.out.println("Top " + top + " female names: ");
				for(int i = 0; i < top; i++) {
					System.out.println("  (" + (i+1) + ")" + fnames[i]); 
				}
				System.out.println("Top " + top + " male names: ");
				for(int i = 0; i < top; i++) {
					System.out.println("  (" + (i+1) + ")" + mnames[i]); 
				}
				break;
			}
		}while(top < 1 || top > 20);
		
	}
	
	
	public static void main(String[] args) throws IOException{
		s = new Scanner(System.in);
		
		//the user enters a year, if it is within the range 1880-2016 the program continues
		int year;
		do {
			System.out.print("Enter a birth year(1880-2016): ");
			year = Integer.parseInt(s.nextLine());
			if(year < 1880 || year > 2016) {
				System.out.println("Invalid Range, try again.");
				continue;
			}else { 
				break;
			}
		}while(year < 1880 || year > 2016);
		
		String filename = "names/yob" + year + ".txt";
		
		readNamesData(filename);
		
		//holds the options that the user can choose from
		String options = "Enter (n)ame, (r)ank, (t)op, or (q)uit: ";
			
		//holds the case for when the user wants to quit the program.
		boolean quit = false;
		//allows the user to input a letter that will be used as the option to run for each case.
		do {
			System.out.print(options);
			char input = (((s.nextLine()).toLowerCase()).charAt(0));
			switch(input) {
				//runs the name options
				case 'n': 
					nameOption();
					break;
				case 'r':
					//runs the rank option
					rankOption();
					break;
				case 't':
					//runs the top option
					topOption();
					break;
				//runs the quit option, boolean quit is equal to true, and the user leaves the program
				case 'q':
					quit = true;
					break;
				//input checking, if the user inputs an illegal value the do loop starts over. 
				default:
					System.out.println("Invalid input");
					System.out.print(options);
					input = (((s.nextLine()).toLowerCase()).charAt(0));
			}
			
		}while(!quit);
		
	}

}
