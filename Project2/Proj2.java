import java.util.*;
import java.text.*;
/**
 * This program simulates a roullette roll at a casino
 * the user inputs a starting amount
 * the user then selects a bet type
 * the user will then make a bet
 * if the user wins (the ball falls in the designated area) the user wins money
 * if they lose then they will lose money.
 * @author (Victor Valdez)
 * @version 1
 */
public class Proj2{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		//formats numbers when printed
		DecimalFormat df = new DecimalFormat("#0.00");
		//asks the user how much money the user has available
		//rolls a random number between 0-37
		System.out.print("How much money do you have(in whole dollars)? $");
		//allows the user to input an amount.
		int startAmount = Integer.parseInt(s.nextLine());
		//sets the first total amount = to the starting amount
		int totalAmount = startAmount;
		//holds the play again choice for the player
		char playAgain;
		do{	
			//place holder value for an even roll
			boolean evenRoll;
			//place holder value for the color of the hole
			String color;
			//randomly generates a number between 0-37
			int roll = r.nextInt(38);
			//if the number is even, even roll is true, else false
			if (roll % 2 == 0){
				evenRoll = true;
			}
			else{ 
				evenRoll = false;
			}
			//if roll is 1-10 or 19-28, evens are black and odds are black
			if ((roll >= 1 &&  roll <=10) || (roll >= 19 && roll <= 28)){
				if(evenRoll){
					color = "black";
				}
				else{
					color = "red";
				}
			}
			//if roll is 11-18 or 29-36, evens are red and odds are black
			else if ((roll >= 11 && roll <= 18)|| (roll >= 29 && roll <= 36)){
				if(evenRoll){
					color = "red";
				}
				else{
					color = "black";
				}
			}
			//if the roll is 0 or 37(00) the color is green
			else{
				color = "green";
				evenRoll = false;
			}

			//gets the user input and changes it into an int
			int betType;
			//prints the different options the user has to vote from
			do{
				System.out.println("Choose a type of bet: ");
				System.out.println("  (1) Single number\n  (2) Dozen bet");
				System.out.println("  (3) Red or black\n  (4) Even or odd");
				System.out.println("  (5) High or low");
				//prompts the user to input a choice from 1-5
				System.out.print("Enter your number choice 1-5: ");
				betType = Integer.parseInt(s.nextLine());
				if (betType < 1 || betType > 5){
					System.out.println("Invalid Choice!");
				} 
			//will repeat until the player enters a valid number
			}while(betType < 1 || betType > 5);
			//creates a boolean value for winning
			boolean winning = false;
			//creates a int value for payout
			int payout = 0;
			//different scenarios for each case
			if (betType == 1){
				int numberChoice;
				payout = (36/18) - 1;
				do{
					System.out.print("\nWhich number? (1-36): ");
					numberChoice = Integer.parseInt(s.nextLine());
					if (roll == numberChoice){
						winning = true;
					} else {
						winning = false;
					}
					if (numberChoice > 36 || numberChoice < 1){
						System.out.println("Invalid choice!");
					}
				}while(numberChoice > 36 || numberChoice < 1);
			} //if choice is 2 do the 2nd type of game
			else if (betType == 2){
				int dozenChoice;
				payout = (36/12) - 1;
				do{
					System.out.print("\nEnter (1)1-12, (2)13-24, or (3)25-36: ");
					dozenChoice = Integer.parseInt(s.nextLine());
					if (dozenChoice == 1){
						if (roll >= 1 && roll <=12){
							winning = true;
						} else{
							winning = false;
						}
					} else if (dozenChoice == 2){
						if(roll >= 13 && roll <= 24){
							winning = true;
						} else{
							winning = false;
						}
					} else if (dozenChoice == 3){
						if(roll >= 25 && roll <= 36){
							winning = true;
						} else {
							winning = false;
						}
					} else {
						System.out.println("Invalid choice!");
					}
				}while(dozenChoice > 3 || dozenChoice < 1);
			} //if choice is 3 do the third type of game
			else if (betType == 3){
				char colorChoice;
				payout = (36/18) - 1;
				do{
					System.out.print("\nEnter (r)ed or (b)lack: ");
					colorChoice = (s.nextLine().toLowerCase()).charAt(0);
					if (colorChoice == 'r'){
						if (color == "red"){
							winning = true;
						} else {
							winning = false;
						}
						break;
					}else if (colorChoice == 'b') {
						if (color == "black"){
							winning = true;
						} else {
							winning = false;
						}
						break;
					} else {
						System.out.println("Invalid choice!");
					}
				}while(colorChoice != 'r' || colorChoice != 'b');
			}//if choice is 4 do the fourth type of game
			else if (betType == 4){
				char evenOddChoice;
				payout = (36/18) - 1;
				do{
					System.out.print("\nEnter (e)ven or (o)dd: ");
					evenOddChoice = (s.nextLine().toLowerCase()).charAt(0);
					if (evenOddChoice == 'e'){
						if (evenRoll){
							winning = true;
						} else {
							winning = false;
						}
						break;
					}else if (evenOddChoice == 'o') {
						if (!evenRoll){
							winning = true;
						} else {
							winning = false;
						}
						break;
					} else {
						System.out.println("Invalid choice!");
					}
				}while(evenOddChoice != 'e' || evenOddChoice != 'o');
			}//if choice is 5 do the fifth type of game
			else if (betType == 5){
				char highLowChoice;
				do{
					System.out.print("\nEnter (h)igh(numbers 19-36) or (l)ow(numbers 1-18): ");
					highLowChoice = (s.nextLine().toLowerCase()).charAt(0);
					payout = (36/18) - 1;
					if (highLowChoice == 'h'){
						if (roll >= 19 && roll <= 36){
							winning = true;
						} else {
							winning = false;
						}
						break;
					}else if (highLowChoice == 'l') {
						if(roll >= 1 && roll <= 18){
							winning = true;
						} else {
							winning = false;
						}
						break;
					} else {
						System.out.println("Invalid choice!");
					}
				}while(highLowChoice != 'h' || highLowChoice !='l');
			}
			//if the roll is 0 or 00(37) set winning to false
			if(roll == 0 || roll == 37) winning = false;
			//creates a betamount that will be checked in the do while loop
			int betAmount;
			//while the user inputs a bet amount greater than total, repeat
			do{
				System.out.print("\nEnter your bet amount in dollars: ");
				betAmount = Integer.parseInt(s.nextLine());
			}while(betAmount > totalAmount);
			//creates a bet total that is stored to caclulate the new total amount
			int betTotal = payout * betAmount;
			//prints where the ball lands if it lands on 37 print 00 instead of 37
			if (roll == 37){
				System.out.println("\nBall lands on: 00" + " (" + color + ")");
			}
			else{
				System.out.println("\nBall lands on: " + roll + " (" + color + ")");
			}
			//prints a statement depending on wether the player wins or not
			if (winning){
				System.out.println("\nYou win! You earn " + payout + " * $" + df.format(betAmount) + " = $" + df.format(betTotal) + ", and keep your bet of $" + df.format(betAmount));
				totalAmount = totalAmount + betTotal;
			} else {
				System.out.println("\nYou lose.");
				totalAmount = totalAmount - betTotal;
			}
			//prints the total money
			System.out.println("\nTotal money: " + df.format(totalAmount));
			//exits the do-while loop if the user falls under 1 dollar
			if(totalAmount <= 0){
				System.out.println("\nGame Over");
				break;
			//prompts the user to continue playing
			}else{
				//prompts the user if they want to play again
				System.out.print("\nDo you want to play another round? (y/n): ");
				//saves their response as a char
				playAgain = (s.nextLine().toLowerCase()).charAt(0);
			}
		}while(playAgain == 'y');
	}
}
