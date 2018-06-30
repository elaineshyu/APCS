import java.util.Random;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class P4_Shyu_Elaine_MSGridPane extends Group {
	private double tileSize;
	private ImageView[][] cells;
	private P4_Shyu_Elaine_MinesweeperModel model;
	
	public P4_Shyu_Elaine_MSGridPane() {
		this.model = null;
		this.cells = null;
		this.tileSize = 40;
	}
	
	
	public void setTileSize(double size) {
		this.tileSize = size;
		resetCells();
	}
	
	public double getTileSize() {
		return this.tileSize;
	}
	
	public void setModel(P4_Shyu_Elaine_MinesweeperModel model) {
		this.model = model;
		resetCells();
	}
	
	public void resetCells() {
		//Images
		Image blank = new Image("blank.gif");
		Image harmlessBomb = new Image("bomb_revealed.gif");
		Image failMarking = new Image("bomb_wrong.gif");
		Image bombRevealed = new Image("bomb_revealed2.jpg");
		Image bombFlagged = new Image("bomb_flagged.jpg");
		Image zero = new Image("num_0.gif");
		Image one = new Image("num_1.gif");
		Image two = new Image("num_2.gif");
		Image three = new Image("num_3.gif");
		Image four = new Image("num_4.gif");
		Image five = new Image("num_5.gif");
		Image six = new Image("num_6.gif");
		Image seven = new Image("num_7.gif");
		Image eight = new Image("num_8.gif");
		getChildren().remove(0, getChildren().size());
		cells = new ImageView[model.getNumRows()][model.getNumCols()];
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				ImageView toAdd = new ImageView();
				if (!model.isVisible(row, col) && !model.isFlagged(row, col)) {
					if (model.gameOver() && model.isMine(row, col)) {
						toAdd.setImage(harmlessBomb);
					} else {
						toAdd.setImage(blank);
					}
				} else {
					if (model.isMine(row, col) && model.isVisible(row, col)) {
						toAdd.setImage(bombRevealed);

					}  else if (model.isFlagged(row, col)) {
						if (model.gameOver() && !model.isMine(row, col)) {
							toAdd.setImage(failMarking);
						} else {
							toAdd.setImage(bombFlagged);
						}
					} else if (model.countAdjNumBombs(row, col) == 0) {
						toAdd.setImage(zero);
					} else {
						int adj = model.countAdjNumBombs(row, col);
						if (adj == 1) {
							toAdd.setImage(one);
						} else if (adj == 2) {
							toAdd.setImage(two);
						}else if (adj == 3) {
							toAdd.setImage(three);
						}else if (adj == 4) {
							toAdd.setImage(four);
						}else if (adj == 5) {
							toAdd.setImage(five);
						}else if (adj == 6) {
							toAdd.setImage(six);
						}else if (adj == 7) {
							toAdd.setImage(seven);
						}else if (adj == 8) {
							toAdd.setImage(eight);

						}
						
					}
				}
				toAdd.setFitWidth(tileSize);
				toAdd.setFitHeight(tileSize);
				toAdd.setPreserveRatio(true);
				toAdd.setSmooth(true);
				toAdd.setX(tileSize*col);
				toAdd.setY(tileSize * row);
				getChildren().add(toAdd);
				cells[row][col] = toAdd;
			}
		}
		
	}
	
	public ImageView cellAtGridCoords(int row, int col) {
		return cells[row][col];
	}
	
	public double xPosForCol(int col) {
		return col * tileSize;
	}
	
	public double yPosForRow(int row) {
		return row * tileSize;
	}
	
	public int colForXPos(double x) {
		return (int)(x / tileSize);
	}
	
	public int rowForYPos(double y) {
		return (int)(y / tileSize);
	}

}
