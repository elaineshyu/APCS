/*
 * Elaine Shyu, P4, 3/16/17
 * This lab took about 4 hours outside of school, so along with
 * about 4 hours in school, took a grand total of 8 hours. It was
 * pretty straightforward, with the replacing of the Rectangle[][] 
 * in GridPane with ImageView[][] the part with the most 
 * comprehension necessary. Otherwise, most of the aspects could be
 * found on Google, such as using Alerts and TextInputDialogs. 
 * The tutorial on how to use WebView was also very helpful. Another
 * part I was confused about was how to use a generalized path with
 * Engines, which Mr. Mcleod helped me figure out (using URL, which
 * likely takes the path from bin, unlike Engine which starts god 
 * knows where). Otherwise, a fun and creative project to play around
 * with.
 */

import java.net.URL;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class P4_Shyu_Elaine_MSGUI extends Application {
	private P4_Shyu_Elaine_MSGridPane grid = new P4_Shyu_Elaine_MSGridPane();
	private P4_Shyu_Elaine_MinesweeperModel model = new P4_Shyu_Elaine_MinesweeperModel();
	private int numRows = 8;
	private int numCols = 8;
	private int reveal = 0;
	private int numBombs = 10;
	private int flagCount = 0;
	private ImageView pepe = new ImageView();
	private Label remainingMines;
	private Label elapsed;
	private StackPane games;
	private Stage stage;
	private long time = 0;
	private AnimationTimer timer;
	private Image happyPepe = new Image("pepe.png");
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		timer = new AnimationTimer() {
			private long oldTime = 0;
			@Override
			public void handle(long now) {// TODO Auto-generated method stub
				if (now - oldTime > 1000000000) {
					elapsed.setText("Time Elapsed: " + time++);
					oldTime = now;
				}
			}
		};;
		timer.start();
		
		BorderPane root = new BorderPane();
		
		BorderPane top = top();
		root.setTop(top);
		
		GridPane game = new GridPane();
		game.setStyle("-fx-background-color: gainsboro;");
		
		model.setSize(numRows, numCols);
		grid.setModel(model);
		Text lost = new Text("BOMBOOZLED!"); //creds to Kinjal
		lost.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
	    lost.setFill(Color.WHITE);
	    lost.setStroke(Color.web("#7080A0"));
	    Text won = new Text("YOU WON!");
		won.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
	    won.setFill(Color.WHITE);
	    won.setStroke(Color.web("#7080A0"));
	    Scene scene = new Scene(root, 500, 500);
	    
		
		games = new StackPane();
		games.getChildren().add(grid);
		root.setCenter(games);
		
		Rectangle lostR = new Rectangle(grid.getTileSize()*model.getNumCols(), grid.getTileSize()*model.getNumRows());
		lostR.setFill(Color.GRAY);
		lostR.setOpacity(0.5);
		
		grid.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					int revealRow = (int)grid.rowForYPos(event.getY());
					int revealCol = (int)grid.colForXPos(event.getX());
					if (reveal == 0) {
						model.setNumBombs(numBombs);
						model.plantBombs(revealRow, revealCol);
						model.setBombGrid();
						model.reveal(revealRow, revealCol);
						reveal ++;
					} else {
						if (!model.isFlagged(revealRow,  revealCol)) {
							model.reveal(revealRow, revealCol);
						} 
						
					}
					//viewBoard();
					grid.setModel(model);
				} else {
					int flagRow = (int)grid.rowForYPos(event.getY());
					int flagCol = (int)grid.colForXPos(event.getX());
					if(model.isFlagged(flagRow, flagCol)) {
						flagCount--;
						model.setVisibility(flagRow, flagCol, false);
					} else {
						flagCount++;
					}
					model.setFlagged(flagRow,  flagCol);
					grid.setModel(model);
				}
				if (model.gameOver()) {
					
					timer.stop();
					if (model.died()) {
						games.getChildren().addAll(lostR, lost);
						Image newPepe = new Image("screamingpepe.png");
						pepe.setImage(newPepe);
						pepe.setFitWidth(50);
						pepe.setFitHeight(50);
						pepe.setPreserveRatio(true);
						pepe.setSmooth(true);
					} else {
						games.getChildren().addAll(lostR, won);
					}	
				}
				 remainingMines.setText("Mines Remaining: "+ (numBombs - flagCount));	
			}
		});
		primaryStage.setScene(scene);
		primaryStage.setTitle("Memesweeper");
		primaryStage.show();
		
	}
	
	public BorderPane top() {
		MenuBar menu = new MenuBar();
		Menu file = new Menu("Game");
		MenuItem newBeginnerGame = new MenuItem("New Beginner Game");
		
		newBeginnerGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pepe.setImage(happyPepe);
				pepe.setFitWidth(50);
				pepe.setFitHeight(50);
				pepe.setPreserveRatio(true);
				pepe.setSmooth(true);
				time = 0;
				numBombs = 10;
				numRows = 8;
				numCols = 8;
				elapsed.setText("Time Elapsed: " + time);
				timer.start();
				flagCount = 0;
				remainingMines.setText("Mines Remaining: "+ (numBombs - flagCount));
				if (model.gameOver()) {
					games.getChildren().remove(1);
					games.getChildren().remove(1);
				}
				model = new P4_Shyu_Elaine_MinesweeperModel();
				model.setSize(numRows, numCols);
				reveal = 0;
				grid.setModel(model);
			}
			
		});
		
		MenuItem exit = new MenuItem("Exit");
		
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
			
		});
		
		Menu options = new Menu("Options");
		MenuItem setNumMines = new MenuItem("Set Number of Mines");
		setNumMines.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextInputDialog askBombs = new TextInputDialog();
				askBombs.setHeaderText("Number of Mines");
				askBombs.setContentText("Please enter the number of mines desired: ");
				askBombs.showAndWait();
				String numberBombs = askBombs.getResult();
				int tempNumBombs = Integer.parseInt(numberBombs);
				if (tempNumBombs < 0 || tempNumBombs >= model.getNumCols()*model.getNumRows()) {
					Alert numBombsOut = new Alert(AlertType.ERROR);
					numBombsOut.setTitle("Error");
					numBombsOut.setHeaderText("There was an error with your request.");
					numBombsOut.setContentText("Please enter a valid number.");
					numBombsOut.showAndWait();
				} else {
					if (reveal == 0) {
						numBombs = tempNumBombs;
						
						remainingMines.setText("Mines Remaining: "+ (numBombs - flagCount));
					} else {
						Alert numBombsSet = new Alert(AlertType.ERROR);
						numBombsSet.setTitle("Error");
						numBombsSet.setHeaderText("There was an error with your request.");
						numBombsSet.setContentText("Game has already started.");
						numBombsSet.showAndWait();
					}
				}
				
			}
			
		});
		
		Menu instructions = new Menu("Help");
		MenuItem howToPlay = new MenuItem("How to Play");
		howToPlay.setOnAction(new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage infoStage = new Stage();
				
				Button close = new Button("Ok");
				close.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						infoStage.close();
					}
				});
				
				VBox box = new VBox();
				
				WebView view = helpWindow();
				box.setAlignment(Pos.CENTER);
				box.getChildren().addAll(view, close);
				infoStage.setScene(new Scene(box, 500, 500));
				infoStage.setTitle("How to Play");
				infoStage.show();
			}
			
		});
		
		MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage infoStage = new Stage();
				
				Button close = new Button("Ok");
				close.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						infoStage.close();
					}
				});
				
				VBox box = new VBox();
				WebView view = aboutWindow();
				box.setAlignment(Pos.CENTER);
				box.getChildren().addAll(view, close);
				infoStage.setScene(new Scene(box, 250, 350));
				infoStage.setTitle("About");
				infoStage.show();
			}
			
		});
		
		file.getItems().addAll(newBeginnerGame, exit);
		options.getItems().addAll(setNumMines);
		instructions.getItems().addAll(howToPlay, about);
		menu.getMenus().addAll(file, options, instructions);
		
		elapsed = new Label("Time Elapsed: 0");
		elapsed.setPrefWidth(110);
		remainingMines = new Label("Mines Remaining: " +numBombs);
		
		
		pepe.setImage(happyPepe);
		pepe.setFitWidth(50);
		pepe.setFitHeight(50);
		pepe.setPreserveRatio(true);
		pepe.setSmooth(true);
		
		BorderPane top = new BorderPane();
		
		top.setTop(menu);
		top.setRight(remainingMines);
		top.setLeft(elapsed);
		top.setCenter(pepe);
		
		return top;
	}
	
	public WebView helpWindow() {
		WebView view = new WebView();
		WebEngine engine = view.getEngine();
		URL url = getClass().getResource("howtoplay.html");
		engine.load(url.toExternalForm());
		return view;
	}
	
	public WebView aboutWindow() {
		WebView view = new WebView();
		WebEngine engine = view.getEngine();
		URL url = getClass().getResource("about.html");
		engine.load(url.toExternalForm());
		return view;
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


}
