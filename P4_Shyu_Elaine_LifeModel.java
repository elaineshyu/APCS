

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P4_Shyu_Elaine_LifeModel extends GridModel <Boolean>{
	private ArrayList <GenerationListener> listeners = new ArrayList <GenerationListener>();
	private static Boolean [][] grid;
	private GridModel<Boolean> model;
	private BooleanGridPane gridPane = new BooleanGridPane();
	private int generation = 0;
	
	public P4_Shyu_Elaine_LifeModel (Boolean[][] grids) {
		super(grids);
		grid = grids;
		model = new GridModel<Boolean>(grid);
		gridPane.setModel(model);
		
	}
	
	public Boolean[][] getGrid() {
		return grid;
	}
	
	public void addGenerationListener(GenerationListener I) {
		listeners.add(I);
	}
	
	public void removeGenerationListener(GenerationListener I) {
		listeners.remove(I);	
	}
	
	public void setGeneration(int gen) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).generationChanged(generation, gen);
		}
		generation = gen;
	}
	
	public int getGeneration() {
		return generation;
	}
	
	public int getNumRows(){
		return grid.length;
	}
	
	public int getNumCols() {
		return grid[0].length;
	}
	
	public Boolean getValueAt(int row, int col) {
		return grid[row][col];
	}
	
	public void setValueAt(int row, int col, Boolean x) {
		super.setValueAt(row, col, x);
	}
	
	public void setGrid(Boolean[][] grids) {
		grid = grids;
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
	
}
