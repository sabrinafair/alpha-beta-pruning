package tictactoe;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {
	public record Place(String row, int col) {}
	public static void displayGame(String[] gameboard) {
		System.out.println("  1 2 3 4 5 6 7 8");
		String rowTitle = "ABCDEFGH";
		int rowPlace = 0;
		for(int i = 0; i < gameboard.length; i++) {
			if(i % 8 == 0) {
				if(i >= 8) System.out.println();
				System.out.print(rowTitle.charAt(rowPlace) + " ");
				rowPlace++;
			}
			System.out.print(gameboard[i] + " ");
			
		}
		System.out.println();
	}
	
	public static boolean setMove(String[] gameboard, Place move, boolean xTurn) {
		String rowTitle = "ABCDEFGH";
		int rowNum = rowTitle.indexOf(move.row);
		int colNum = move.col - 1; //change to 0 index 
		int placeIndex = (rowNum * 8) + colNum;
		boolean validMove = checkSpot(gameboard, placeIndex);
		if(!validMove) return false;
		gameboard[placeIndex] = xTurn ? "X" : "O";
		return true;
	}
	
	public static boolean checkSpot(String[] gameboard, int index) {
		String val = gameboard[index];
		
		if(!val.equals("-") || index > gameboard.length || index < 0) return false;
		else return true;
	}
	
	public static boolean move(String[] gameboard, Scanner scan, boolean xTurn) {
		if(xTurn) {
			String moveInput = scan.next(); 
			scan.nextLine();
			moveInput = moveInput.replaceAll("\\s", "");
			String moveCol = (moveInput.charAt(1) + "").toUpperCase();
			Place move = new Place(moveCol, moveInput.charAt(3) - '0');
			return setMove(gameboard, move, xTurn);
		}else {
			Random rand = new Random();
			boolean foundEmptySpot = false;
			while(!foundEmptySpot) {
				int randNum = rand.nextInt(gameboard.length + 1);
				String val = gameboard[randNum];
				if(!val.equals("-")) foundEmptySpot = false;
				else {
					foundEmptySpot = true;
					gameboard[randNum] = "O";
					break;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {

		System.out.println("Tic-Tac-Toe Game with Alpha-Beta Pruning");
		System.out.println("----------------------------------------");

		System.out.println("You will be 'X' and get to go first.");
		Scanner scanner = new Scanner(System.in);
		
		String[] gameboard = new String[64];
		Arrays.fill(gameboard, "-");
		displayGame(gameboard);
		
		boolean playGame = true;
		boolean xTurn = true;
		
		while(playGame) {
			System.out.println("Enter the place you would like to go in format -> (C,4):");
			boolean validMove = move(gameboard, scanner, xTurn);
			while(!validMove) {
				System.out.println("Invalid Move.");
				System.out.println("Enter the place you would like to go in format -> (C,4):");
				validMove = move(gameboard, scanner, xTurn);
			}
			displayGame(gameboard);

			System.out.println("O's Turn");
			xTurn = false;
			move(gameboard, scanner, xTurn);
			displayGame(gameboard);
			System.out.print("Continue Game? (y/n): ");
			String playInput = scanner.next(); 
			scanner.nextLine();
			if(playInput.equals("n")) {
				playGame = false;
				break;
			}
			xTurn = true;
		}
		
	}

}
