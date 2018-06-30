
public interface P4_Shyu_Elaine_MSModel <T>{
	
	int getNumRows(); 
	
	int getNumCols();
	
	void resetGrid();
	
	void setVisibility(int row, int col, boolean val);
	
	int countAdjNumBombs(int row, int col);
	
	boolean gameOver();
	
	boolean allExposed();
	
	boolean died();
	
	void plantBombs(int rowClicked, int colClicked);
	
	void setFlagged(int row, int col);
	
	boolean isFlagged(int row, int col);
	
	void setSize(int row, int col);
	
	void setNumBombs(int numBombs);
	
	boolean isMine(int row, int col);
	
	void reveal(int row, int col);
	
	boolean isVisible(int row, int col);
	
	void setBombGrid();
	
	
	
}
