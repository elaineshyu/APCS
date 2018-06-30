/*
 * Elaine Shyu, P4, 2/1/17

 * This lab took me approximately an hour and ten minutes. It was
 * actually quite fun coding for it (though I'm not sure why). I
 * most likely did it in an inefficient way, but I'm pretty
 * happy that is actually works (or that it appears to works
 * ...). The instructions were a bit confusing, but this allowed
 * me to have more freedom as to what I did in what method. I 
 * think the more challenging part was remembering what variable
 * represents what, since with columns and rows it was hard
 * keeping track of them. Other than that, I had no big issues
 * doing this, and a majority of the time was spent on those
 * god-awful if-else statements (which I do know that could have
 * been done more efficiently as taught in class, but I forgot
 * and I had already written all the if-else statements in
 * my notebook so it was easily referenced).
 * 
 * Edit: Resubmitted since I realized the printBoard() method
 * was incorrect.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P4_Shyu_Elaine_Life extends GridModel <Boolean>{
	private static Boolean [][] grid;
	private GridModel<Boolean> model;
	private BooleanGridPane gridPane = new BooleanGridPane();
	
	public P4_Shyu_Elaine_Life (String fileName) {
		super(grid);
		
		File read = new File (fileName);
		int row = 0;
		int col = 0;
		
		ArrayList <Integer> rows = new ArrayList<Integer>();
		ArrayList <Integer> cols = new ArrayList<Integer>(); 
		
		try {
			Scanner in = new Scanner (read);
			row = in.nextInt();
			col = in.nextInt();
			int i = 1;
			while (in.hasNext()){
				if (i%2 == 0) {
					cols.add(in.nextInt());
				} else {
					rows.add(in.nextInt());
				}
				i++;
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		grid = new Boolean [row][col];
		insertionSort2(rows, cols);
		
		//rows
		int current = 0;
		for (int i = 0; i < row; i++) {
			Boolean [] currRow = new Boolean[col];
			boolean stop = false;
			while (current < rows.size() && stop == false) {
				if (rows.get(current) == i) {
					currRow[cols.get(current)] = true;
				} else {
					stop = true;
					current--;
				}
				current ++;
			}
			grid[i] = currRow;
			
		}
		
		model = new GridModel<Boolean>(grid);
		gridPane.setModel(model);
		
	}
	
	public static void insertionSort2(ArrayList<Integer> b, ArrayList <Integer> a) {
		for (int i = b.size()-1; i >= 0; i--) {
			int position = i;
			while (position < b.size()-1 && b.get(i).compareTo(b.get(position+1)) > 0) {
				position++;
			}
			b.add(position, b.remove(i));
			a.add(position, a.remove(i));
		}
	}
	
	// Method that runs the Life simulation through the given generation
	// Generation 0 is the initial position, Generation 1 is the position after one round of Life, etc...
	public void runLife(int numGenerations) {
		for (int i = 0; i < numGenerations; i++) {
			nextGeneration();
		}
	}
	// Method that returns the number of living cells in the given row
	// or returns -1 if row is out of bounds.  The first row is row 0.
	public int rowCount(int row) {
		int count = 0;
		if (row < model.getNumRows()) {
			for (int i = 0; i < model.getNumCols(); i++) {
				if (model.getValueAt(row, i) == true) {
					count++;
				}
			}
			return count;
		} else {
			return -1;
		}
	}
	// Method that returns the number of living cells in the given column
	// or returns -1 if column is out of bounds.  The first column is column 0.
	public int colCount(int col) {
		if (col < model.getNumCols()) {
			int count = 0;
			for (int i = 0; i < model.getNumRows(); i++) {
				if (model.getValueAt(i,  col) == true) {
					count++;
				}
			}
			return count;
		} else {
			return -1;
		}
	}
	// Method that returns the total number of living cells on the board
	public int totalCount() {
		int count = 0;
		for (int i = 0; i < model.getNumRows(); i++) {
			count += rowCount(i);
		}
		
		return count;
	}
	// Prints out the Life array with row and column headers as shown in the example below.
	public void printBoard() {
		System.out.print("   ");
		for (int i = 0; i < model.getNumRows(); i++) {
			System.out.print(i%10);
		}
		System.out.println();
		
		//row tracking
		for (int f = 0; f < model.getNumRows(); f++) {
			System.out.printf("%2d ", f);
			//column tracking
			for (int g = 0; g < model.getNumCols(); g++) {
				if (model.getValueAt(f, g) == true) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println("Number of living cells in row 9 --> " + rowCount(9));
		System.out.println("Number of living cells in col 9 --> " + colCount(9));
		System.out.println("Number of living cells total --> " + totalCount());
	}
	// Advances Life forward one generation
	public void nextGeneration() {
		int rows = model.getNumRows();
		int cols = model.getNumCols();
		Boolean [][] tracker2 = new Boolean[rows][cols];
		
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				int counter = 0;
				if (row-1 >= 0) {
					if (col-1 >= 0 && model.getValueAt(row-1, col-1) == true) {
						counter++;
					}
					if (model.getValueAt(row-1, col) == true) {
						counter++;
					}
					if (col+1 < cols && model.getValueAt(row-1, col+1)== true) {
						counter++;
					}
				}
				
				if (col-1 >= 0 && model.getValueAt(row,  col-1) == true) {
					counter++;
				}
				if (col+1 < cols && model.getValueAt(row, col+1) == true) {
					counter++;
				}
				if(row+1 < rows) {
					if (col-1 >= 0 && model.getValueAt(row+1,  col-1) == true) {
						counter++;
					}
					if (model.getValueAt(row+1, col) == true) {
						counter++;
					}
					if (col+1 < cols && model.getValueAt(row+1, col+1)== true) {
						counter++;
					}
				}
				
				if (model.getValueAt(row, col) == true) {
					if (counter == 0 || counter == 1) {
						tracker2[row][col] = false;
					} else if (counter == 2 || counter == 3) {
						tracker2[row][col] = true;
					} else {
						tracker2[row][col] = false;
					}
				} else {
					if (counter == 3) {
						tracker2[row][col] = true;
					} else {
						tracker2[row][col] = false;
					}
				}
				
				
			}//for loop
		}//for loop
		for (int j = 0; j < rows; j++) {
			for (int p = 0; p < cols; p++) {
				model.setValueAt(j, p, tracker2[j][p]);
			}
		}
	}
	
	
	public static void main(String[] args) {
		///Users/elaineshyu/Desktop/
		P4_Shyu_Elaine_Life test = new P4_Shyu_Elaine_Life("/Users/elaineshyu/Desktop/life100.txt");
		//P4_Shyu_Elaine_Life test = new P4_Shyu_Elaine_Life("x.txt");
		test.runLife(1);
		test.printBoard();
	}

}
