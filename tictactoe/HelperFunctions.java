package tictactoe;


public class HelperFunctions {
	public record Place(String row, int col) {}
	public static String replaceChar(String str, int i, char c) {
		String newStr = str.substring(0, i) + c + str.substring(i + 1);
		return newStr;
	}
	
	public static boolean checkSpot(String[] gameboard, int index) {
		if(index >= gameboard[0].length() || index < 0) return false;
		String val = gameboard[0].charAt(index) + "";
		
		if(!val.equals("-")) return false;
		else return true;
	}
	
	public static int setMove(String[] gameboard, Place move, boolean xTurn) {
		String rowTitle = "ABCDEFGH";
		int rowNum = rowTitle.indexOf(move.row);
		int colNum = move.col - 1; //change to 0 index 
		int placeIndex = (rowNum * 8) + colNum;
		boolean validMove = checkSpot(gameboard, placeIndex);
		if(!validMove) return -1;
		gameboard[0] = replaceChar(gameboard[0], placeIndex, xTurn ? 'X' : 'O');
		return placeIndex;
	}
	
	public static int evaluation(String[] gameboard, int index) {
		//this evaluation function looks around the recently played X or O
		//it gives a score of 10 for 2 in a row
		// gives a score 100 for 3 in a row
		// and a score of 1000 for 4 in a row
		
		int n = 8;
		String val = "X";
		int currRow = (index - 3)/8;

		int score = 1;

		
		
		int maxInRow = 1;
		int currMax = 0;
		int currStart = 0;
		int startRow = (int) Math.floor((index)/8);
		int startCol = (index - 3) % 8;
		int endCol = startCol + 3;
		int currIndex = 0;
			for(int j = startCol; j < 7; j++) {
				currStart = startRow*n + j;
				int currIndexRow1 = (int) Math.floor((currStart)/8);
				int currIndexRow2 = (int) Math.floor((currStart + 1)/8);
				int currIndexRow3 = (int) Math.floor((currStart + 2)/8);
				int currIndexRow4 = (int) Math.floor((currStart + 3)/8);
				if(j < 0 || currRow < startRow) continue;
				if(currRow > startRow || currStart >= gameboard[0].length()) break;
				if(maxInRow != 4) {
				if(currIndex < gameboard[0].length() && maxInRow < 4 && (currIndexRow1 == currIndexRow2  && currIndexRow2 == currIndexRow3 && currIndexRow3 == currIndexRow4)){
					if(val.equals(gameboard[0].charAt(currStart) + "") 
						&& val.equals(gameboard[0].charAt(currStart + 1) + "")
						&& val.equals(gameboard[0].charAt(currStart + 2) + "")
						&& val.equals(gameboard[0].charAt(currStart + 3) + "")){ 
							currMax = 4;
							if(currMax > maxInRow) maxInRow = currMax;
						}
				}
				if(currIndex < gameboard[0].length() && maxInRow < 3 && (currIndexRow1 == currIndexRow2  && currIndexRow2 == currIndexRow3)){
					if(val.equals(gameboard[0].charAt(currStart) + "") 
						&& val.equals(gameboard[0].charAt(currStart + 1) + "")
						&& val.equals(gameboard[0].charAt(currStart + 2) + "")){ 
							currMax = 3;
							if(currMax > maxInRow) maxInRow = currMax;
						}
				}
				if(currIndex < gameboard[0].length() && maxInRow < 2 && (currIndexRow1 == currIndexRow2)){
					if(val.equals(gameboard[0].charAt(currStart) + "") 
						&& val.equals(gameboard[0].charAt(currStart + 1) + "")){ 
						currMax = 2;
						if(currMax > maxInRow) maxInRow = currMax;
					}
				}
				}else {break;}
			}
			
			if(maxInRow == 4) return 1000; //already found winner
			
			int maxInCol = 1;
			currMax = 0;
			currStart = 0;
			startRow = (int) Math.floor((index - 3)/8);
			currRow = (int) Math.floor((index)/8);
			startCol = (index - 3) % 8;
			int endRow = (int) Math.floor((index + 3)/8);;
			currIndex = 0;
				for(int j = startRow; j < 7; j+=8) {
					currStart = startRow*n + j;
					int currIndexRow1 = (int) Math.floor((currStart)/8);
					int currIndexRow2 = (int) Math.floor((currStart + 1)/8);
					int currIndexRow3 = (int) Math.floor((currStart + 2)/8);
					int currIndexRow4 = (int) Math.floor((currStart + 3)/8);
					if(j < 0 || currRow < startRow) continue;
					if(currRow > endRow || currStart >= gameboard[0].length()) break;
					if(maxInCol != 4) {
					if(currIndex < gameboard[0].length() && maxInCol < 4 && (currIndexRow1 == currIndexRow2 - 1  && currIndexRow2 == currIndexRow3 - 1 && currIndexRow3 == currIndexRow4 - 1)){
						if(val.equals(gameboard[0].charAt(currStart) + "") 
							&& val.equals(gameboard[0].charAt(currStart + 8*1) + "")
							&& val.equals(gameboard[0].charAt(currStart + 8*2) + "")
							&& val.equals(gameboard[0].charAt(currStart + 8*3) + "")){ 
								currMax = 4;
								if(currMax > maxInCol) maxInCol = currMax;
							}
					}
					if(currIndex < gameboard[0].length() && maxInCol < 3 && (currIndexRow1 == currIndexRow2 - 1  && currIndexRow2 == currIndexRow3 - 1)){
						if(val.equals(gameboard[0].charAt(currStart) + "") 
							&& val.equals(gameboard[0].charAt(currStart + 8*1) + "")
							&& val.equals(gameboard[0].charAt(currStart + 8*2) + "")){ 
								currMax = 3;
								if(currMax > maxInCol) maxInCol = currMax;
							}
					}
					if(currIndex < gameboard[0].length() && maxInCol < 2 && (currIndexRow1 == currIndexRow2 - 1)){
						if(val.equals(gameboard[0].charAt(currStart) + "") 
							&& val.equals(gameboard[0].charAt(currStart + 8*1) + "")){ 
							currMax = 2;
							if(currMax > maxInCol) maxInCol = currMax;
						}
					}
					}else {break;}
				}
				
				int max = maxInCol > maxInRow ? maxInCol: maxInRow;
		
		if(max == 1) score = 1;
		else if(max == 2) score = 10;
		else if(max == 3) score = 100;
		else score = 1000;
			

		
		return score;
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
	
	public static int checkForWinner(String[] gameboard) {
		int winner;
		int n = 8;
		
		//check columns
		for(int i = 0; i + 4*n <= gameboard[0].length(); i+=n) {
			for(int j = 0; j < n; j++) {
				if((i + j + 8*3) >= 64) { System.out.println("1 ERROR HERE"); break;}
				if((gameboard[0].charAt(i + j) != '-') && 
					(gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*1) 
					&& gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*2) 
					&& gameboard[0].charAt(i + j) == gameboard[0].charAt(i + j + 8*3))) 
				{
					winner = (gameboard[0].charAt(i + j) + "").equals("X") ? 5000: -5000;
					return winner;
				}
			}
		}
		//check rows
		for(int i = 0; i < gameboard[0].length(); i+=n) {
			for(int j = i; j <= i + 4; j++) {
				if(j+3 >= 64) { System.out.println("2 ERROR HERE"); break;}
			if((gameboard[0].charAt(j) != '-') && 
					(gameboard[0].charAt(j) == gameboard[0].charAt(j + 1) 
					&& gameboard[0].charAt(j) == gameboard[0].charAt(j + 2)
					&& gameboard[0].charAt(j) == gameboard[0].charAt(j + 3))) 
				{
					winner = (gameboard[0].charAt(j) + "").equals("X") ? 5000: -5000;
					return winner;
				}
			}
		}
		
		//check for empty spaces
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++){

				if(((i * 8) + j) >= 64) { System.out.println("3 ERROR HERE"); break;}
				if (gameboard[0].charAt((i * 8) + j) == '-') return 0; //no winner yet
			}
		}
		
		return -1; //draw
	}

}
