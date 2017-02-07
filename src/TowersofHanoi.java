import java.util.*;

public class TowersofHanoi {
	
	public static final int towers = 3;
	public static int numDisks;
	public static int[][] board;
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int colFrom = 0;
		int colTo = 0;
		int valFrom = 0;
		int valTo = 0;
		int rowFrom = 0;
		int rowTo = 0;
		int count = 0;
		boolean validMove = false;
		
		while(!validMove) {
			System.out.print("How many disks in this game? (2-5) "); 
			numDisks = in.nextInt();
			if(numDisks < 2 || numDisks > 5) {
				System.out.println("Invalid number of disks.");
			}
			
			else {
				validMove = true;
			}
		}
		
		System.out.println();
		System.out.println("END ON TOWER " + (towers - 1));
		System.out.println();
		
		board = new int[numDisks][towers];
		
		init();
		
		while(!hasWon()) {
			
			printBoard();
			System.out.println();
			validMove = false;
			
			while(!validMove){
				
				validMove = true; 
				
				System.out.print("Do you want to move FROM "); 
				for(int x = 1; x <= towers; x++) {
					if(x != towers) {
						System.out.print(x + ", ");
					}
					
					else {
						System.out.print("or " + x + "? ");
					}
				}
				
				colFrom = in.nextInt();
				colFrom--;
				
				System.out.print("Do you want to move TO "); 
				for(int y = 1; y <= towers; y++) {
					if(y != towers) {
						System.out.print(y + ", ");
					}
					
					else {
						System.out.print("or " + y + "? ");
					}
				}

				colTo = in.nextInt();
				colTo--;
				
				/* Checks for valid positions */
				
				if(colFrom < 0 || colFrom > numDisks - 1) {
					validMove = false; 
				}
				
				if(colTo < 0 || colTo > numDisks - 1) {
					validMove = false;
				}
				
				if(colFrom == colTo) {
					validMove = false; 
				}
				
				if(validMove) {
				
					for(int j = 0; j < numDisks; j++) { //Checks to see if there is something to move
						if(board[j][colFrom] != 0) {
							valFrom = board[j][colFrom];
							rowFrom = j;
							break;
						}
						
						if(board[j][colFrom] == 0 && j == numDisks - 1) {
							validMove = false;
							break;
						}
					}
					
					
					for(int k = 0; k < numDisks; k++) { //Checks to see if there is somewhere valid to move to
						if(board[k][colTo] != 0) {
							valTo = board[k][colTo];
							rowTo = k - 1;
							break;
						}
						
						if(board[k][colTo] == 0 && k == numDisks - 1) {
							valTo = 0;
							rowTo = k;
						}
					}
				}
				
				
				if(valFrom > valTo && valTo != 0) { //Checks to see if move puts a larger block on top of a smaller block
					validMove = false;
				}
				
				if(!validMove) {
					System.out.println("Invalid move");
				}
			}
	
				/* Change board to reflect move */
			
				board[rowFrom][colFrom] = 0; 
				board[rowTo][colTo] = valFrom; 
				count++;
		}
		
		printBoard();
		System.out.println();
		System.out.println("CONGRATULATIONS! VICTORY!");
		System.out.println("You took " + count + " steps!");
		in.close();
	}
	
	/* initializes Gameboard */
	public static void init() { 
		for(int i = 0; i < numDisks; i++) {
			board[i][0] = i + 1;
		}
	}
	
	/* prints a disk of size n */
	public static void printDisk(int n) { 
		if(n > 0) {
			for(int i = 0, spaces = numDisks - n; i < spaces; i++) {
				System.out.print(" ");
			}
			
			for(int j = 0; j < n; j++) {
				System.out.print("_");
			}
			
			System.out.print("|");
			
			for(int k = 0; k < n; k++) {
				System.out.print("_");
			} 
			
			for(int l = 0, spaces = numDisks - n; l < spaces; l++) {
				System.out.print(" ");
			}
		}
		
		else {
			for(int m = 0, spaces = numDisks * 2 + 1; m < spaces; m++) {
				System.out.print(" ");
			}
		}
	}
	
	/* prints the gameboard in its current state */
	public static void printBoard() { 
		for(int i = 0; i < numDisks; i++) {
			for(int j = 0; j < towers; j++) {
				printDisk(board[i][j]);
				space();
			}
			
			System.out.println();
		}
		
		printNums();
	}
	
	/* prints tower numbers */
	public static void printNums() { 
		
		for(int i = 0; i < towers; i++) {
			for(int j = 0; j < numDisks; j++) {
				System.out.print(" ");
			}
			
			System.out.print(i + 1);
			
			for(int k = 0; k < numDisks; k++) {
				System.out.print(" ");
			}
			
			space();
		}
			
	}
	
	/* prints a numDisks number of spaces */
	public static void space() { 
		for(int i = 0; i < numDisks; i++) {
			System.out.print(" ");
		}
	}
	
	/* checks to see if game has been won */
	public static boolean hasWon() { 
		for(int i = 0; i < numDisks; i++) {
			if(board[i][1] != i + 1) {
				return false;
			}
		}
		
		return true; 
	}
}