import java.util.Random;

public class P4_Shyu_Elaine_MinesweeperModel implements P4_Shyu_Elaine_MSModel{
	private int[][] bombMap;
	private boolean[][] exposed;
	private int numBombs;
	private boolean dead = false;
	private boolean[][] flagged;
	
	// -1: BOMB
	//  0-8: # ADJ BOMB COUNT
	// 9: flagged
	
	public void setFlagged(int row, int col) {
		if (flagged[row][col] == true) {
			flagged[row][col] = false;
		} else {
			flagged[row][col]  = true;
		}
		
	}
	
	public boolean isFlagged(int row, int col) {
		if (flagged[row][col] == true) {
			return true;
		}
		return false;
	}
	
	public void setSize(int row, int col) {
		bombMap = new int[row][col];
		exposed = new boolean[row][col];
		flagged = new boolean[row][col];
	}

	public void setNumBombs(int numBomb) {
		numBombs = numBomb;
	}
	
	public boolean isMine(int row, int col) {
		if (bombMap[row][col] == -1) {
			return true;
		}
		return false;
	}
	
	public boolean dead() {
		return dead;
	}
	
	public void reveal(int row, int col) {
		if (isMine(row, col)) {
			exposed[row][col] = true;
			dead = true;
		} else if (bombMap[row][col] > 0 && bombMap[row][col] <=8) {
			exposed[row][col] = true;
		} else if (exposed[row][col] == true) {
			
		} else if (bombMap[row][col] == 0){
			exposed[row][col] = true; 
			for (int i = row-1; i <= row+1; i++) {
				for (int j = col-1; j <= col+1; j++) {
					if (isInBounds(i, j) && (i!=row || j!=col)) {
						reveal(i, j);
					}
				}
			}
			
		}
	}
	
	public boolean isVisible(int row, int col) {
		return exposed[row][col];
	}
	
	public void plantBombs(int rowClicked, int colClicked) {
		Random rand = new Random();
		int countBomb = 0;
		while (countBomb < numBombs) {
			int row = rand.nextInt(bombMap.length);
			int col = rand.nextInt(bombMap[0].length);
			if ((row != rowClicked || col != colClicked) && !isMine(row, col)) {
				bombMap[row][col] = -1;
				countBomb++;
			}
		}
	}
	
	
	public boolean died() {
		return dead;
	}
	
	@Override
	public boolean gameOver() {
		
		if(allExposed()==true || dead == true) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean allExposed() {
		for (int row = 0; row < exposed.length; row++) {
			for (int col = 0; col < exposed[0].length; col++) {
				if (isMine(row, col)) {
					
				} else if (exposed[row][col] == true && !isMine(row, col)) {
					
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int countAdjNumBombs(int row, int col) {
		int count = 0;
		for (int i = row-1; i <= row+1; i++) {
			for (int j = col-1; j <= col+1; j++) {
				if (isInBounds(i,j) && bombMap[i][j] == -1) {
					count ++;
				}
			}
		}
		return count;
	}
	
	public boolean isInBounds(int row, int col) {
		int rowArray = bombMap.length;
		int colArray = bombMap[0].length;
		if (row < rowArray && row >= 0) {
			
		} else {
			return false;
		}
		
		if (col < colArray && col >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int getNumRows() {
		return bombMap.length;
	}

	@Override
	public int getNumCols() {
		return bombMap[0].length;
	}


	public Integer getBombValueAt(int row, int col) {
		return bombMap[row][col];
	}

	public void setBombValueAt(int row, int col, int val) {
		bombMap[row][col] = val;
		
	}
	
	@Override
	public void setVisibility(int row, int col, boolean val) {
		exposed[row][col] = val;
		
	}
	
	
	public void setBombGrid() {
		for (int row = 0; row < bombMap.length; row++) {
			for (int col = 0; col < bombMap[0].length;col++) {
				if (bombMap[row][col] != -1) {
					bombMap[row][col] = countAdjNumBombs(row, col);
				}
			}
		}
	}



	@Override
	public void resetGrid() {
		for (int row = 0; row < exposed.length; row++) {
			for (int col = 0; col < exposed[0].length; col++) {
				exposed[row][col] = false;
			}
		}
		
	}

}
