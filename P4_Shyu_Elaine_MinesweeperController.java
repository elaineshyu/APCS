/*
 * Elaine Shyu, P4, 3/16/17
 * This lab took me around 40 minutes. It was pretty 
 * straightforward, just a couple of methods that I needed 
 * to add to the MinesweeperModel to increase functionality
 * and ease of usage. Also, I made edits to the interface so
 * it has more useful methods that don't require the user
 * to know specific values etc. A main point of confusion was
 * whether the number of mines left to be flagged actually told
 * the user when only mines were flagged, or if it simply counted
 * how many flags they placed, regardless if they were actually
 * on mines. 
 * 
 * Just a side note as well, the program generates the board after
 * the user picks a location so that a mine is not placed there.
 * 
 * Edit: Made it so that if you flag something that is already 
 * flagged, it gets unflagged.
 */

/*
import java.util.Scanner;

public class P4_Shyu_Elaine_MinesweeperController {
	private P4_Shyu_Elaine_MinesweeperModel model = new P4_Shyu_Elaine_MinesweeperModel();
	private int flagCount = 0;
	
	public static void main (String[] args) {
		P4_Shyu_Elaine_MinesweeperController control = new P4_Shyu_Elaine_MinesweeperController(10, 10, 98);
	}
	
	public P4_Shyu_Elaine_MinesweeperController(int row, int col, int numMines) {
		int reveal = 0;
		model.setSize(row, col);
		model.setNumBombs(numMines);
		
		Scanner in = new Scanner(System.in);
		while (!model.gameOver()) {
			System.out.println("Minesweeper Board");
			viewBoard();
			playerBoard();
			System.out.println("You have " + (numMines - flagCount) + " \"mines\" left.");
			
			System.out.println("'f' for flag, 'r' for reveal");
			String determine = in.next();
			determine.trim();
			System.out.println("Where?");
			System.out.print("row: ");
			if (determine.equals("f")) {
				int flagRow = in.nextInt();
				System.out.print("col: ");
				int flagCol = in.nextInt();
				if(model.isFlagged(flagRow, flagCol)) {
					flagCount--;
				} else {
					flagCount++;
				}
				model.setFlagged(flagRow,  flagCol);
				
			} else {
				int revealRow = in.nextInt();
				System.out.print("col: ");
				int revealCol = in.nextInt();
				if (reveal == 0) {
					model.plantBombs(revealRow, revealCol);
					model.setBombGrid();
					model.reveal(revealRow, revealCol);
					reveal ++;
				} else {
					model.reveal(revealRow, revealCol);
				}
				
			}
		}
		
		viewBoard();
		playerBoard();
		if (model.died()) {
			System.out.println("You lost!");
		} else {
			System.out.println("Congratulations! You won!");
		}
		
		System.out.print("Play again? Y or N:");
		String answer = in.next();
		answer.trim();
		if (answer.equals("Y")) {
			P4_Shyu_Elaine_MinesweeperController controller = new P4_Shyu_Elaine_MinesweeperController(10, 10, 5);
		} 
	
		
	}
	public void viewBoard() {
		System.out.println("Everything revealed");
		
		System.out.print("   ");
		for (int columnBorder = 0; columnBorder < model.getNumRows(); columnBorder++) {
			System.out.print(columnBorder%10+ " ");
		}
		System.out.println();
		for (int rows = 0; rows < model.getNumRows(); rows++) {
			System.out.printf("%-3s", ""+rows+ " ");
			for (int cols = 0; cols < model.getNumCols(); cols++) {
				if (model.isMine(rows, cols)) {
					System.out.print("* ");
				} else {
					System.out.print(model.countAdjNumBombs(rows, cols)+" ");
				}
			}
			System.out.println();
		}
	}
	
	public void playerBoard() {
		System.out.println("What the player sees:");
		System.out.print("   ");
		for (int columnBorder = 0; columnBorder < model.getNumRows(); columnBorder++) {
			System.out.print(columnBorder%10+ " ");
		}
		System.out.println();
		for (int rows = 0; rows < model.getNumRows(); rows++) {
			System.out.printf("%-3s", ""+rows+ " ");
			for (int cols = 0; cols < model.getNumCols(); cols++) {
				if (!model.isVisible(rows, cols) && !model.isFlagged(rows, cols)) {
					System.out.print("_ ");
				} else {
					if (model.isFlagged(rows, cols)) {
						System.out.print("! ");
					} else if (model.isMine(rows, cols)) {
						System.out.print("* ");
					} else if (model.countAdjNumBombs(rows, cols) == 0) {
						System.out.print("  ");
					} else {
						System.out.print(model.countAdjNumBombs(rows, cols)+" ");
					}
				}
			}
			System.out.println();
		}
	}

	
	
	
}*/
