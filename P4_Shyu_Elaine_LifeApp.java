/*
 * Elaine Shyu, P4, 3/10/17
 * This lab took around 6 hours? (not sure). It was definitely managable and some days 
 * I finished the homework in class. The previous homework was helpful in that I explored 
 * most of the feature utilized in this project, such as event handling and how to use toggle
 * buttons. The demo on how to use AnimationTimer was also very helpful, and after getting 
 * help during lunch on what the requirements were, they were pretty easy to modify. Another 
 * issue I had was figuring out how to fix the error where dragging outside of the gridpane 
 * would cause it to attempt to change the value at that x and y location. After experimenting
 * by printing out the x and y coordinates outside of the gridpane, I realized that the top
 * left corner was (0,0), so checking if it was in bounds only entailed checking (0,0) to the
 * number of rows/cols times the tile size. 
 */


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class P4_Shyu_Elaine_LifeApp extends Application {
	private P4_Shyu_Elaine_LifeModel model;
	private BooleanGridPane gridPane = new BooleanGridPane();
	private Button clear;
	private Stage stageChoose;
	private Button nextGen;
	private Label genCount;
	private MenuItem menuLoad;
	private int delay = 0;
	private boolean timerOn = false;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		stageChoose = primaryStage;

		MyButtonHandler buttonHandler = new MyButtonHandler();
		MenuBar menu = new MenuBar();
		Menu menuOptions = new Menu("File");
		menu.getMenus().addAll(menuOptions);
		menuLoad = new MenuItem("Open");
		menuLoad.setOnAction(buttonHandler);
		MenuItem menuSave = new MenuItem("Save");
		menuSave.setOnAction(buttonHandler);
		menuOptions.getItems().addAll(menuLoad, menuSave);
		
		ToggleButton tb = new ToggleButton("Play"); //Control 4
	    ToggleGroup group = new ToggleGroup();
	    tb.setToggleGroup(group);
	    tb.setPrefSize(110, 20);
	    tb.setPadding(new Insets(10, 10, 10, 10));
	    
	    AnimationTimer timer = new AnimationTimer() {
			private long oldTime = 0;
			@Override
			public void handle(long now) {// TODO Auto-generated method stub
				if (now - oldTime > delay && timerOn) {
					model.nextGeneration();
					gridPane.setModel(model);
					model.setGeneration(model.getGeneration() +1);
					oldTime = now;
				}
			}
		};;
		
	    group.selectedToggleProperty().addListener(new ChangeListener <Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue != null) {
					tb.setText("Pause");
					timer.start();
					timerOn = true;
				} else if (newValue == null){
					tb.setText("Play");
					timer.stop();
					timerOn = false;
				}
				
			}
	    });
		
		BorderPane root = new BorderPane();
		VBox stuff = new VBox();
		FlowPane bottomH = new FlowPane();
		root.setTop(menu);
		
		bottomH.setPadding(new Insets(40, 12, 20, 12));
		bottomH.setHgap(20);
		bottomH.setStyle("-fx-background-color: orange;");
		clear = new Button("Clear");
		clear.setPadding(new Insets(10, 10, 10, 10));
		clear.setOnAction(buttonHandler);
		
		nextGen = new Button("Next Generation");
		nextGen.setPadding(new Insets(10, 10, 10, 10));
		nextGen.setOnAction(buttonHandler);
		
		Slider size = new Slider();
		size.setMin(0);
		size.setMax(100);
		size.setShowTickMarks(true);
		//size.setShowTickLabels(true);
		size.setPadding(new Insets(10, 0, 10, 0));
		size.setMajorTickUnit(50);
		size.setMinorTickCount(4);
		size.setShowTickLabels(true);
		size.valueProperty().addListener(new SliderHandler());
		
		Slider speed = new Slider();
		speed.setMin(0);
		speed.setMax(1000);
		speed.setMinorTickCount(4);
		speed.setShowTickLabels(true);
		speed.setShowTickMarks(true);
		//size.setShowTickLabels(true);
		speed.setPadding(new Insets(10, 0, 10, 0));
		speed.setMajorTickUnit(500);
		speed.valueProperty().addListener(new ChangeListener <Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				delay = (int) ((double) newValue*1000000);
			}
			
		});
			
		Boolean [][] database = new Boolean[20][20];
		for (int i = 0; i < database.length; i++) {
			for (int j = 0; j < database[0].length; j++) {
				database[i][j] = false;
			}
		}
		
		model = new P4_Shyu_Elaine_LifeModel(database);
		genCount = new Label("Generation #" + model.getGeneration());
		gridPane.setModel(model);
		model.addGenerationListener(new GenerationListener() {
			@Override
			public void generationChanged(int oldVal, int newVal){
				genCount.setText("Generation #" + newVal);
			}
		}); 
		
		MyEventHandler mouseHandler = new MyEventHandler();
		gridPane.setOnMousePressed(mouseHandler);
		gridPane.addEventHandler(MouseEvent.MOUSE_DRAGGED,mouseHandler);
		gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					if (event.getClickCount() == 2) {
						if (model.getValueAt(gridPane.rowForYPos(event.getY()), gridPane.colForXPos(event.getX()))== true) {
							backTracking(gridPane.rowForYPos(event.getY()), gridPane.colForXPos(event.getX()));
						}
						
					}
				}
				
			}
			
		});

		stuff.getChildren().add(gridPane);
		stuff.setAlignment(Pos.CENTER);
		
		genCount.setStyle("-fx-text-fill: white");
		genCount.setPadding(new Insets(10, 0, 10, 0));
		
		Label sizes = new Label("Tile Size");
		Label speeds = new Label("Delay (ms)");
		speeds.setStyle("-fx-text-fill: white");
		sizes.setStyle("-fx-text-fill: white");
		bottomH.getChildren().addAll(clear, nextGen, tb, sizes, size, speeds, speed, genCount);
		root.setCenter(stuff);
		root.setBottom(bottomH);
		
		Scene scene = new Scene(root, 580, 510);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private class SliderHandler implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			 gridPane.setTileSize((double)newValue);
			
		}
		
	}

	
	private class MyButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == clear) {
				for (int i = 0; i < model.getNumRows(); i++) {
					for (int j = 0; j < model.getNumCols(); j++) {
						model.setValueAt(i, j,  false);	
					}
					
				}
				model.setGeneration(0);
			} else if (event.getSource() == menuLoad) {
				 FileChooser fileChooser = new FileChooser();
				 File file = fileChooser.showOpenDialog(stageChoose);
	             try {
					Scanner in = new Scanner(file);
					int rows = in.nextInt();
					int cols = in.nextInt();
					Boolean [][] database = new Boolean[rows][cols];
					int i = 0;
					int j = 0;
					while (in.hasNext()) {
						String next = in.next();
						if (next.equals("X")) {
							database[i%rows][j%cols] = false;
						} else {
							database[i%rows][j%cols] = true;
						}
						
						j++;
						if (j%cols == 0 && j!= 0) {
							i++;
						}
						
					}
					in.close();	
					model = new P4_Shyu_Elaine_LifeModel(database);
					gridPane.setModel(model);
					model.addGenerationListener(new GenerationListener() {
						@Override
						public void generationChanged(int oldVal, int newVal){
							genCount.setText("Generation #" + newVal);
						}
					}); 
					
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
	             
	             model.setGeneration(0);
	             
			} else if (event.getSource() == nextGen){
				model.nextGeneration();
				gridPane.setModel(model);
				model.setGeneration(model.getGeneration() + 1);
			} else {
				 FileChooser fileChooser = new FileChooser();
				 File file = fileChooser.showSaveDialog(stageChoose);
				 try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(model.getNumRows() + " " + model.getNumCols());
					fileWriter.write("\r\n");
					for (int i= 0; i < model.getNumRows(); i++) {
						for (int j = 0; j <model.getNumCols(); j++) {
							boolean next = model.getValueAt(i, j);
							if (next == true) {
								fileWriter.write("O ");
							} else {
								fileWriter.write("X ");
							}
							
						}
						
						fileWriter.write("\r\n");
					}
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			
			
		}
		
	}
	
	public void backTracking(int row, int col) {
		
		if (row >= 0 && row < model.getNumRows() && col >= 0 && col < model.getNumCols()) {
			if (model.getValueAt(row, col) == true) {
				model.setValueAt(row,  col, false);
				backTracking(row, col-1);
				backTracking(row-1, col);
				backTracking(row, col+1);
				backTracking(row+1, col);
			}
		} else {
			
		}
	}
	
	private class MyEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			if (event.getSource() == gridPane) {
				if (event.getButton() == MouseButton.PRIMARY && isInBounds(event.getX(), event.getY())) {
					model.setValueAt(gridPane.rowForYPos(event.getY()), gridPane.colForXPos(event.getX()), true);
				} else if (isInBounds(event.getX(), event.getY())) {
					model.setValueAt(gridPane.rowForYPos(event.getY()), gridPane.colForXPos(event.getX()), false);
				}
				
				
				/*
				int row = gridPane.rowForYPos(event.getY());
				int col = gridPane.colForXPos(event.getX());
				
				for (int i = row-1; i <= row+1; i++) {
					for (int j = col-1; j <= col+1; j++) {
						if (i >= 0 && j >= 0 && j < model.getNumCols() && i < model.getNumRows()) {
							if (i !=row || j!=col) {
								model.setValueAt(i, j,  !model.getValueAt(i,  j));
							}
						} else if (i < 0 || j < 0 || j >= model.getNumCols() || i >= model.getNumRows()) {
							
						}
					}
				}
				*/
				
			} else {	
				
			}			
		}
	}
	
	public boolean isInBounds(double x, double y) {
		if (x > 0 && x < 0 + gridPane.getTileSize()*model.getNumCols() && y > 0 && y < gridPane.getTileSize()*model.getNumRows()) {
			return true;
		} else {
			return false;
		}
	}
}
