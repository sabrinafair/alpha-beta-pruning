package tictactoe;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import tictactoe.HelperFunctions;

public class main {
	
	public static void displayGame(String[] gameboard) {
		System.out.println("  1 2 3 4 5 6 7 8"); //for the columns display
		String rowTitle = "ABCDEFGH"; //for the rows display
		int rowPlace = 0;
		for(int i = 0; i < gameboard[0].length(); i++) {
			if(i % 8 == 0) {
				if(i >= 8) System.out.println();
				System.out.print(rowTitle.charAt(rowPlace) + " ");
				rowPlace++;
			}
			System.out.print(gameboard[0].charAt(i) + " ");
			
		}
		System.out.println();
	}
	
	public static int evaluate(String[] gameboard) { 
		int score = 0;
		//find all X and check each one for score
		for(int i = 0; i < gameboard[0].length(); i++) {
			if((gameboard[0].charAt(i) + "").equals("X")) {
				score += HelperFunctions.evaluation(gameboard, i); //get score for current index
			}
		}
		return score;
	}
	
	//min function based off the code given by professor as well as improved upon by passing alpha beta
	public static int min(String[] gameboard, int depth, double alpha, double beta) { 
		int best=20000,score,n=8;
		if (HelperFunctions.checkForWinner(gameboard) != 0) return (HelperFunctions.checkForWinner(gameboard));
	  if (depth == 0) return (evaluate(gameboard)); //implemented evaluate function
//		if (depth == 0) return 0;
		for (int i=0; i<n; i++){ 
			for (int j=0; j<n; j++){ 
				if (gameboard[0].charAt((i * 8) + j)== '-'){
					gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, 'X'); // make move on board
					score = max(gameboard, depth-1, alpha, beta);
					if (score < best) best=score; //check if current score is less than current best
					gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, '-'); // undo move
					if (score <= alpha) {return score;} //PRUNING: check if score is less than alpha
					beta = score < beta ? score : beta; //set min to beta
				} 
			} 
	  	}
		
		return(best);
	}
	


	public static int max(String[] gameboard, int depth, double alpha, double beta) { 
		int best=-20000,score, n=8;
		if (HelperFunctions.checkForWinner(gameboard) != 0) return (HelperFunctions.checkForWinner(gameboard));
		if (depth == 0) return (evaluate(gameboard));
//		if (depth == 0) return 0;
		for (int i=0; i < n; i++){ 
			for (int j=0; j < n; j++){
				if (gameboard[0].charAt((i * 8) + j)== '-'){
					gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, 'X'); // make move on board
					score = min(gameboard, depth-1, alpha, beta);
					if (score > best) best=score; //check if current score is better than current best
					gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, '-'); // undo move
					if (score >= beta) {return score;} //PRUNING: check if score is greater than beta
					alpha = score > alpha ? score : alpha; //set max to alpha
				} 
			} 
		}
	  return(best);
	}

	
	public static int getXsMove(String[] gameboard, int maxDepth, double alpha, double beta) {
		//FOR TESTING WITH RANDOM PLACEMENT
//		Random rand = new Random();
//		boolean foundEmptySpot = false;
//		while(!foundEmptySpot) {
//			int randNum = rand.nextInt(gameboard[0].length());
//			String val = gameboard[0].charAt(randNum) + "";
//			if(!val.equals("-")) foundEmptySpot = false;
//			else {
//				foundEmptySpot = true;
//				gameboard[0] = replaceChar(gameboard[0], randNum, 'X');
//				return randNum;
//			}
//		}
		int best=-20000,depth=maxDepth,n=8,score,mi = 0,mj = 0;

		//loop through looking at options
		for(int i = 0; i < n; i++){ 
			for (int j=0; j< n; j++){ 
			  if (gameboard[0].charAt((i * 8) + j)== '-'){ 
				  gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, 'X'); // make move on board
		          score = min(gameboard, depth-1, alpha, beta); //start recursion
		          if (score > best) { 
		        	  mi=i; 
		        	  mj=j; 
		        	  best=score; 
		          }
		        
		          gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, '-'); // undo move
			  } 
			} 
		  }
		
		  gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (mi * 8) + mj, 'X');

		
		return (mi * 8) + mj; //return index for placement
	}
	
	
	public static int move(String[] gameboard, Scanner scan, boolean xTurn, int maxDepth, double alpha, double beta) {
		if(!xTurn) {
			//USER MOVE
			String moveInput = scan.next(); 
			scan.nextLine();
			moveInput = moveInput.replaceAll("\\s", "");
			String moveCol = (moveInput.charAt(0) + "").toUpperCase();
			HelperFunctions.Place move = new HelperFunctions.Place(moveCol, moveInput.charAt(1) - '0');
			return HelperFunctions.setMove(gameboard, move, xTurn);
		}else {
			//COMPUTER MOVE
			return getXsMove(gameboard, maxDepth, alpha, beta);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Tic-Tac-Toe Game with Alpha-Beta Pruning");
		System.out.println("----------------------------------------");

		System.out.println("What max depth would you like to set?");
		int maxDepth = scanner.nextInt(); 
		scanner.nextLine();
		
		System.out.println("You will be O's, would you like to go first? (y/n)");
		String turnInput = scanner.next(); 
		scanner.nextLine();
		
		String[] gameboard = new String[1];
		gameboard[0] = "";
		for(int i = 0; i < 64; i++) {
			gameboard[0] += "-";
		} 
		displayGame(gameboard);
		
		boolean playGame = true;
		boolean xTurn = !turnInput.toLowerCase().equals("y");
		
		int currEval_X = 0;
		int currEval_O = 0;
		
		while(playGame) {
			if(!xTurn) {				
				System.out.println("O's Turn, enter the place you would like to go in format -> (ex: c4)");
				int O_move = move(gameboard, scanner, xTurn, maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
				
				while(O_move == -1) {//check if move is already taken
					System.out.println("Invalid Move.");
					System.out.println("Enter the place you would like to go in format -> (ex: c4)");
					O_move = move(gameboard, scanner, xTurn, maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
				}
				currEval_O += HelperFunctions.evaluation(gameboard, O_move);
				displayGame(gameboard);
			}else {
				System.out.println("X's Turn");
				int X_move = move(gameboard, scanner, xTurn, maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
				currEval_X += HelperFunctions.evaluation(gameboard, X_move);
				displayGame(gameboard);
			}

			//final evaluations
			System.out.println();
			System.out.println("Eval for X: " + currEval_X);
			System.out.println("Eval for O: " + currEval_O);
			System.out.println();

			int winner = HelperFunctions.checkForWinner(gameboard);
			if(winner != 0) { //check for winner or tie
				if(winner != -1) {
					if(winner == 5000) System.out.println("X is our Winner!");
					else System.out.println("O is our Winner!");
					playGame = false;
					break;	
				}else {
					System.out.println("Cat's Game! It was a draw.");
					playGame = false;
					break;
				}
			}
			xTurn = !xTurn;
		}
		
	}

}
