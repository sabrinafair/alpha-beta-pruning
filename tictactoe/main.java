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
	
	public static int setMove(String[] gameboard, Place move, boolean xTurn) {
		String rowTitle = "ABCDEFGH";
		int rowNum = rowTitle.indexOf(move.row);
		int colNum = move.col - 1; //change to 0 index 
		int placeIndex = (rowNum * 8) + colNum;
		boolean validMove = checkSpot(gameboard, placeIndex);
		if(!validMove) return -1;
		gameboard[placeIndex] = xTurn ? "X" : "O";
		return placeIndex;
	}
	
	public static boolean checkSpot(String[] gameboard, int index) {
		if(index >= gameboard.length || index < 0) return false;
		String val = gameboard[index];
		
		if(!val.equals("-")) return false;
		else return true;
	}
	
	public static int evaluation(String[] gameboard, int index) {
		int numOfThreats = 0;
		int n = 8;
		String val = gameboard[index];

		//check col
		int curr = index - 3*n;
		int max = index + 3*n;
		while(curr < gameboard.length && curr <= max) {
			if(curr < 0 || curr == index) {
				curr+=8;
				continue;
			}
			if(val == gameboard[curr]) numOfThreats++;
			curr+=8;
		}
		
		//check row
		curr = index - 3;
		max = index + 3;
		int row = (int) Math.floor(index / n);
		while(curr < gameboard.length && curr <= max) {
			int currRow = (int) Math.floor(index / n);
			if(curr < 0 || curr == index || currRow != row) {
				curr+=1;
				continue;
			}
			if(val == gameboard[curr]) numOfThreats++;
			curr+=1;
		}		

		
		return numOfThreats;
	}
	
	public static int getXsMove(String[] gameboard) {
		Random rand = new Random();
		boolean foundEmptySpot = false;
		while(!foundEmptySpot) {
			int randNum = rand.nextInt(gameboard.length);
			String val = gameboard[randNum];
			if(!val.equals("-")) foundEmptySpot = false;
			else {
				foundEmptySpot = true;
				gameboard[randNum] = "X";
				return randNum;
			}
		}
		return -2;
	}
	
	public static String checkStatus(String[] gameboard) {
		String winner = "";
		int n = 8;
//		String prevVal = "";
//		String currVal = "";
//		boolean checkComplete = false;
//		int curRow = 0;
//		int curCol = 0;
//		int place = 0;
//		
//		//check cols
//		while(!checkComplete) {
//			currVal = gameboard[(curRow * 8) + curCol];
//			if(prevVal != currVal) {
//				
//			}else {
//				
//				prevVal = currVal;
//				curRow +=
//			}
//			
//		}
		
//		checkComplete = false;
		//check columns
		for(int i = 0; i + 4*n <= gameboard.length; i+=n) {
			for(int j = 0; j < n; j++) {
				if((!gameboard[i + j].equals("-")) && 
					(gameboard[i + j] == gameboard[i + j + 8*1] 
					&& gameboard[i + j] == gameboard[i + j + 8*2] 
					&& gameboard[i + j] == gameboard[i + j + 8*3])) 
				{
					winner = gameboard[i + j];
					return winner;
				}
			}
		}
		//check rows
		for(int i = 0; i < gameboard.length; i+=n) {
			for(int j = i; j <= i + 4; j++) {
			if((!gameboard[j].equals("-")) && 
					(gameboard[j] == gameboard[j + 1] 
					&& gameboard[j] == gameboard[j + 2] 
					&& gameboard[j] == gameboard[j + 3])) 
				{
					winner = gameboard[j];
					return winner;
				}
			}
		}
		
		return winner;
	}
	
	public static String checkForWinner(String[] gameboard) {
		String winner = "";
		int n = 8;
		
		//check columns
		for(int i = 0; i + 4*n <= gameboard.length; i+=n) {
			for(int j = 0; j < n; j++) {
				if((!gameboard[i + j].equals("-")) && 
					(gameboard[i + j] == gameboard[i + j + 8*1] 
					&& gameboard[i + j] == gameboard[i + j + 8*2] 
					&& gameboard[i + j] == gameboard[i + j + 8*3])) 
				{
					winner = gameboard[i + j];
					return winner;
				}
			}
		}
		//check rows
		for(int i = 0; i < gameboard.length; i+=n) {
			for(int j = i; j <= i + 4; j++) {
			if((!gameboard[j].equals("-")) && 
					(gameboard[j] == gameboard[j + 1] 
					&& gameboard[j] == gameboard[j + 2] 
					&& gameboard[j] == gameboard[j + 3])) 
				{
					winner = gameboard[j];
					return winner;
				}
			}
		}
		
		return winner;
	}
	
	public static int move(String[] gameboard, Scanner scan, boolean xTurn) {
		if(!xTurn) {
			String moveInput = scan.next(); 
			scan.nextLine();
			moveInput = moveInput.replaceAll("\\s", "");
			String moveCol = (moveInput.charAt(0) + "").toUpperCase();
			Place move = new Place(moveCol, moveInput.charAt(1) - '0');
			return setMove(gameboard, move, xTurn);
		}else {
			//for initial testing of setup
//			Random rand = new Random();
//			boolean foundEmptySpot = false;
//			while(!foundEmptySpot) {
//				int randNum = rand.nextInt(gameboard.length + 1);
//				String val = gameboard[randNum];
//				if(!val.equals("-")) foundEmptySpot = false;
//				else {
//					foundEmptySpot = true;
//					gameboard[randNum] = "O";
//					break;
//				}
//			}
			return getXsMove(gameboard);
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Tic-Tac-Toe Game with Alpha-Beta Pruning");
		System.out.println("----------------------------------------");

		System.out.println("You will be O's, would you like to go first? (y/n)");
		String turnInput = scanner.next(); 
		scanner.nextLine();
		
		String[] gameboard = new String[64];
		Arrays.fill(gameboard, "-");
		displayGame(gameboard);
		
		boolean playGame = true;
		boolean xTurn = !turnInput.toLowerCase().equals("y");
		
		int currEval_X = 0;
		int currEval_O = 0;
		
		while(playGame) {
			if(!xTurn) {				
				System.out.println("O's Turn, enter the place you would like to go in format -> (ex: c4)");
				int O_move = move(gameboard, scanner, xTurn);
				
				while(O_move == -1) {
					System.out.println("Invalid Move.");
					System.out.println("Enter the place you would like to go in format -> (ex: c4)");
					O_move = move(gameboard, scanner, xTurn);
				}
				currEval_O += evaluation(gameboard, O_move);
				displayGame(gameboard);
			}else {
				System.out.println("X's Turn");
				int X_move = move(gameboard, scanner, xTurn);
				currEval_X += evaluation(gameboard, X_move);
				displayGame(gameboard);
				
				
				System.out.print("Continue Game? (y/n): ");
				String playInput = scanner.next(); 
				scanner.nextLine();
				if(playInput.equals("n")) {
					playGame = false;
					break;
				}
			}

			System.out.println();
			System.out.println("Eval for X: " + currEval_X);
			System.out.println("Eval for O: " + currEval_O);
			System.out.println();

			String winner = checkForWinner(gameboard);
			if(!winner.equals("")) {
				System.out.println("Congrats to our Winner : " + winner);
				playGame = false;
				break;	
			}
			xTurn = !xTurn;
		}
		
	}

}
