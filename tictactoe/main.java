package tictactoe;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import tictactoe.HelperFunctions;

public class main {
//	public record Place(String row, int col) {}
	
//	public static String replaceChar(String str, int i, char c) {
//		String newStr = str.substring(0, i) + c + str.substring(i + 1);
//		return newStr;
//	}
	
	public static void displayGame(String[] gameboard) {
		System.out.println("  1 2 3 4 5 6 7 8");
		String rowTitle = "ABCDEFGH";
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
	
//	public static int setMove(String[] gameboard, Place move, boolean xTurn) {
//		String rowTitle = "ABCDEFGH";
//		int rowNum = rowTitle.indexOf(move.row);
//		int colNum = move.col - 1; //change to 0 index 
//		int placeIndex = (rowNum * 8) + colNum;
//		boolean validMove = checkSpot(gameboard, placeIndex);
//		if(!validMove) return -1;
//		gameboard[0] = replaceChar(gameboard[0], placeIndex, xTurn ? 'X' : 'O');
//		return placeIndex;
//	}
	
//	public static boolean checkSpot(String[] gameboard, int index) {
//		if(index >= gameboard[0].length() || index < 0) return false;
//		String val = gameboard[0].charAt(index) + "";
//		
//		if(!val.equals("-")) return false;
//		else return true;
//	}
	
//	public static int evaluation(String[] gameboard, int index) {
//		//this evaluation function looks around the recently played X or O
//		//it adds 1 for any of the same in the vicinity and minuses 1 for any of the oposing
//		int numOfThreats = 0;
//		int n = 8;
//		String val = gameboard[0].charAt(index) + "";
//
//		//check col
//		int curr = index - 3*n;
//		int max = index + 3*n;
//		while(curr < gameboard[0].length() && curr <= max) {
//			if(curr < 0 || curr == index) {
//				curr+=8;
//				continue;
//			}
//			if(val.equals(gameboard[0].charAt(curr) + "")) numOfThreats++;
//			if((val == "X" ? "O" : "X").equals(gameboard[0].charAt(curr) + "")) numOfThreats--;
//			curr+=8;
//		}
//		
//		//check row
//		curr = index - 3;
//		max = index + 3;
//		int row = (int) Math.floor(index / n);
//		while(curr < gameboard[0].length() && curr <= max) {
//			int currRow = (int) Math.floor(index / n);
//			if(curr < 0 || curr == index || currRow != row) {
//				curr+=1;
//				continue;
//			}
//			if(val.equals(gameboard[0].charAt(curr) + "")) numOfThreats++;
//			if((val == "X" ? "O" : "X").equals(gameboard[0].charAt(curr) + "")) numOfThreats--;
//			curr+=1;
//		}		
//
//		
//		return numOfThreats;
//	}
	
//	public static int evaluation(String[] gameboard, int index) {
//		//this evaluation function looks around the recently played X or O
//		//it gives a score of 10 for 2 in a row
//		// gives a score 100 for 3 in a row
//		// and a score of 1000 for 4 in a row
//		
//		int n = 8;
//		String val = "X";
//		int currRow = (index - 3)/8;
//
//		int score = 1;
//
//		
//		
//		int maxInRow = 1;
//		int currMax = 0;
//		int currStart = 0;
//		int startRow = (int) Math.floor((index)/8);
//		int startCol = (index - 3) % 8;
//		int endCol = startCol + 3;
//		int currIndex = 0;
//			for(int j = startCol; j < 7; j++) {
//				currStart = startRow*n + j;
//				int currIndexRow1 = (int) Math.floor((currStart)/8);
//				int currIndexRow2 = (int) Math.floor((currStart + 1)/8);
//				int currIndexRow3 = (int) Math.floor((currStart + 2)/8);
//				int currIndexRow4 = (int) Math.floor((currStart + 3)/8);
//				if(j < 0 || currRow < startRow) continue;
//				if(currRow > startRow || currStart >= gameboard[0].length()) break;
//				if(maxInRow != 4) {
//				if(currIndex < gameboard[0].length() && maxInRow < 4 && (currIndexRow1 == currIndexRow2  && currIndexRow2 == currIndexRow3 && currIndexRow3 == currIndexRow4)){
//					if(val.equals(gameboard[0].charAt(currStart) + "") 
//						&& val.equals(gameboard[0].charAt(currStart + 1) + "")
//						&& val.equals(gameboard[0].charAt(currStart + 2) + "")
//						&& val.equals(gameboard[0].charAt(currStart + 3) + "")){ 
//							currMax = 4;
//							if(currMax > maxInRow) maxInRow = currMax;
//						}
//				}
//				if(currIndex < gameboard[0].length() && maxInRow < 3 && (currIndexRow1 == currIndexRow2  && currIndexRow2 == currIndexRow3)){
//					if(val.equals(gameboard[0].charAt(currStart) + "") 
//						&& val.equals(gameboard[0].charAt(currStart + 1) + "")
//						&& val.equals(gameboard[0].charAt(currStart + 2) + "")){ 
//							currMax = 3;
//							if(currMax > maxInRow) maxInRow = currMax;
//						}
//				}
//				if(currIndex < gameboard[0].length() && maxInRow < 2 && (currIndexRow1 == currIndexRow2)){
//					if(val.equals(gameboard[0].charAt(currStart) + "") 
//						&& val.equals(gameboard[0].charAt(currStart + 1) + "")){ 
//						currMax = 2;
//						if(currMax > maxInRow) maxInRow = currMax;
//					}
//				}
//				}else {break;}
//			}
//			
//			if(maxInRow == 4) return 1000; //already found winner
//			
//			int maxInCol = 1;
//			currMax = 0;
//			currStart = 0;
//			startRow = (int) Math.floor((index - 3)/8);
//			currRow = (int) Math.floor((index)/8);
//			startCol = (index - 3) % 8;
//			int endRow = (int) Math.floor((index + 3)/8);;
//			currIndex = 0;
//				for(int j = startRow; j < 7; j+=8) {
//					currStart = startRow*n + j;
//					int currIndexRow1 = (int) Math.floor((currStart)/8);
//					int currIndexRow2 = (int) Math.floor((currStart + 1)/8);
//					int currIndexRow3 = (int) Math.floor((currStart + 2)/8);
//					int currIndexRow4 = (int) Math.floor((currStart + 3)/8);
//					if(j < 0 || currRow < startRow) continue;
//					if(currRow > endRow || currStart >= gameboard[0].length()) break;
//					if(maxInCol != 4) {
//					if(currIndex < gameboard[0].length() && maxInCol < 4 && (currIndexRow1 == currIndexRow2 - 1  && currIndexRow2 == currIndexRow3 - 1 && currIndexRow3 == currIndexRow4 - 1)){
//						if(val.equals(gameboard[0].charAt(currStart) + "") 
//							&& val.equals(gameboard[0].charAt(currStart + 8*1) + "")
//							&& val.equals(gameboard[0].charAt(currStart + 8*2) + "")
//							&& val.equals(gameboard[0].charAt(currStart + 8*3) + "")){ 
//								currMax = 4;
//								if(currMax > maxInCol) maxInCol = currMax;
//							}
//					}
//					if(currIndex < gameboard[0].length() && maxInCol < 3 && (currIndexRow1 == currIndexRow2 - 1  && currIndexRow2 == currIndexRow3 - 1)){
//						if(val.equals(gameboard[0].charAt(currStart) + "") 
//							&& val.equals(gameboard[0].charAt(currStart + 8*1) + "")
//							&& val.equals(gameboard[0].charAt(currStart + 8*2) + "")){ 
//								currMax = 3;
//								if(currMax > maxInCol) maxInCol = currMax;
//							}
//					}
//					if(currIndex < gameboard[0].length() && maxInCol < 2 && (currIndexRow1 == currIndexRow2 - 1)){
//						if(val.equals(gameboard[0].charAt(currStart) + "") 
//							&& val.equals(gameboard[0].charAt(currStart + 8*1) + "")){ 
//							currMax = 2;
//							if(currMax > maxInCol) maxInCol = currMax;
//						}
//					}
//					}else {break;}
//				}
//				
//				int max = maxInCol > maxInRow ? maxInCol: maxInRow;
//		
//		if(max == 1) score = 1;
//		else if(max == 2) score = 10;
//		else if(max == 3) score = 100;
//		else score = 1000;
//			
//
//		
//		return score;
//	}
	
	public static int min(String[] gameboard, int depth, double alpha, double beta) { 
		int best=20000,score,n=8;
		if (HelperFunctions.checkForWinner(gameboard) != 0) return (HelperFunctions.checkForWinner(gameboard));
	  if (depth == 0) return (evaluate(gameboard));
//		if (depth == 0) return 0;
		for (int i=0; i<n; i++){ 
			for (int j=0; j<n; j++){ 
				if (gameboard[0].charAt((i * 8) + j)== '-'){
					gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, 'X'); // make move on board
					score = max(gameboard, depth-1, alpha, beta);
					if (score < best) best=score;
					gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, '-'); // undo move
					if (score <= alpha) {return score;}
					beta = score < beta ? score : beta;
				} 
			} 
	  	}
		
		return(best);
	}
	
	public static int evaluate(String[] gameboard) { 
		int score = 0;
		//find all X
		for(int i = 0; i < gameboard[0].length(); i++) {
			if((gameboard[0].charAt(i) + "").equals("X")) {
				score += HelperFunctions.evaluation(gameboard, i);
			}
		}
		return score;
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
					if (score > best) best=score;
					gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, '-'); // undo move
					if (score >= beta) {return score;}
					alpha = score > alpha ? score : alpha;
				} 
			} 
		}
	  return(best);
	}

	
	public static int getXsMove(String[] gameboard, int maxDepth, double alpha, double beta) {
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

		for(int i = 0; i < n; i++){ 
			for (int j=0; j< n; j++){ 
			  if (gameboard[0].charAt((i * 8) + j)== '-'){ 
				  gameboard[0] = HelperFunctions.replaceChar(gameboard[0], (i * 8) + j, 'X'); // make move on board
		          score = min(gameboard, depth-1, alpha, beta);
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

		
		return (mi * 8) + mj;
	}
	
//	public static String checkStatus(String[] gameboard) {
//		String winner = "draw";
//		int n = 8;
////		String prevVal = "";
////		String currVal = "";
////		boolean checkComplete = false;
////		int curRow = 0;
////		int curCol = 0;
////		int place = 0;
////		
////		//check cols
////		while(!checkComplete) {
////			currVal = gameboard[0][(curRow * 8) + curCol];
////			if(prevVal != currVal) {
////				
////			}else {
////				
////				prevVal = currVal;
////				curRow +=
////			}
////			
////		}
//		
////		checkComplete = false;
//		//check columns
//		for(int i = 0; i + 4*n < gameboard[0].length(); i+=n) {
//			for(int j = 0; j < n; j++) {
//				if((gameboard[0].charAt(i + j) != '-') && 
//					(gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*1)
//					&& gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*2) 
//					&& gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*3) )) 
//				{
//					winner = gameboard[0].charAt(i + j) + "";
//					return winner;
//				}
//			}
//		}
//		//check rows
//		for(int i = 0; i < gameboard[0].length(); i+=n) {
//			for(int j = i; j <= i + 4; j++) {
//			if((gameboard[0].charAt(j) != '-') && 
//					(gameboard[0].charAt(j) == gameboard[0].charAt(j + 1) 
//					&& gameboard[0].charAt(j) == gameboard[0].charAt(j + 2) 
//					&& gameboard[0].charAt(j) == gameboard[0].charAt(j + 3) )) 
//				{
//					winner = gameboard[0].charAt(j) + "";
//					return winner;
//				}
//			}
//		}
//		
//		//check for empty spaces
//		for (int i=0; i<n; i++) {
//			for (int j=0; j<n; j++){
//				if (gameboard[0].charAt((i * 8) + j) == '-') return "No Winner Yet";
//			}
//		}
//		
//		return winner;
//	}
	
//	public static int checkForWinner(String[] gameboard) {
//		int winner;
//		int n = 8;
//		
//		//check columns
//		for(int i = 0; i + 4*n <= gameboard[0].length(); i+=n) {
//			for(int j = 0; j < n; j++) {
//				if((i + j + 8*3) >= 64) { System.out.println("1 ERROR HERE"); break;}
//				if((gameboard[0].charAt(i + j) != '-') && 
//					(gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*1) 
//					&& gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*2) 
//					&& gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*3))) 
//				{
//					winner = (gameboard[0].charAt(i + j) + "").equals("X") ? 5000: -5000;
//					return winner;
//				}
//			}
//		}
//		//check rows
//		for(int i = 0; i < gameboard[0].length(); i+=n) {
//			for(int j = i; j <= i + 4; j++) {
//				if(j+3 >= 64) { System.out.println("2 ERROR HERE"); break;}
//			if((gameboard[0].charAt(j) != '-') && 
//					(gameboard[0].charAt(j) == gameboard[0].charAt(j + 1) 
//					&& gameboard[0].charAt(j) == gameboard[0].charAt(j + 2)
//					&& gameboard[0].charAt(j) == gameboard[0].charAt(j + 3))) 
//				{
//					winner = (gameboard[0].charAt(j) + "").equals("X") ? 5000: -5000;
//					return winner;
//				}
//			}
//		}
//		
//		//check for empty spaces
//		for (int i=0; i<n; i++) {
//			for (int j=0; j<n; j++){
//
//				if(((i * 8) + j) >= 64) { System.out.println("3 ERROR HERE"); break;}
//				if (gameboard[0].charAt((i * 8) + j) == '-') return 0; //no winner yet
//			}
//		}
//		
//		return -1; //draw
//	}
	
	public static int move(String[] gameboard, Scanner scan, boolean xTurn, int maxDepth, double alpha, double beta) {
		if(!xTurn) {
			String moveInput = scan.next(); 
			scan.nextLine();
			moveInput = moveInput.replaceAll("\\s", "");
			String moveCol = (moveInput.charAt(0) + "").toUpperCase();
			HelperFunctions.Place move = new HelperFunctions.Place(moveCol, moveInput.charAt(1) - '0');
			return HelperFunctions.setMove(gameboard, move, xTurn);
		}else {
			//for initial testing of setup
//			Random rand = new Random();
//			boolean foundEmptySpot = false;
//			while(!foundEmptySpot) {
//				int randNum = rand.nextInt(gameboard[0].length + 1);
//				String val = gameboard[0][randNum];
//				if(!val.equals("-")) foundEmptySpot = false;
//				else {
//					foundEmptySpot = true;
//					gameboard[0][randNum] = "O";
//					break;
//				}
//			}
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
				
				while(O_move == -1) {
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

			System.out.println();
			System.out.println("Eval for X: " + currEval_X);
			System.out.println("Eval for O: " + currEval_O);
			System.out.println();

			int winner = HelperFunctions.checkForWinner(gameboard);
			if(winner != 0) {
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
