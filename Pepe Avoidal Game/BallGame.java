/*
 * Elaine Shyu, P4, 3/31/17
 * This lab took about 4 hours. Most of the time was spent doing excess stuff,
 * since I was somewhat bored. Overall, it was a nice extension of what I had
 * already done for the JavaFX introduction project, so it wasn't too hard. 
 * Some code, like keyboard pressing, was taken from this project.
 * The review we had today (Friday) was also very helpful since I wasn't too 
 * sure about how to tell each of the Actors to act, but afterwards, it was 
 * alright. I also wasn't sure what type of demo was expected to be created,
 * but I made a simple game, although some items ignore collisions (i.e. balls
 * ignore poops and birds, but only collide with pepe). I had some trouble with
 * formatting (i.e. how to get the text to overlap and show at the top), but 
 * just drawing it on paper helped me organize it how I wanted it to appear.
 */

package p4_Shyu_Elaine_myEngine;

import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class BallGame extends Application {
	private BallsWorld world = new BallsWorld();
	
	public static void main(String[] args) {
		launch();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Image beach = new Image("beach.jpg");
		ImageView image = new ImageView(beach);
		image.setFitWidth(500);
		image.setFitHeight(500);
		StackPane root = new StackPane();
		world.setPrefHeight(500);
		world.setPrefWidth(500);
		
		BorderPane pane = new BorderPane();
		
		Text ballsLeft = new Text("You have caught " + world.ballCount() + " balls.");
		world.setText(ballsLeft);
		ballsLeft.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
		ballsLeft.setFill(Color.WHITE);
		ballsLeft.setStroke(Color.web("#7080A0"));
		pane.setTop(ballsLeft);
		pane.setAlignment(ballsLeft, Pos.TOP_CENTER);
		
		root.getChildren().addAll(image, world, pane);
		Scene scene = new Scene(root, 500, 500);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new MyKeyboardHandler());
		
		world.start();
	
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	private class MyKeyboardHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent e) {
			if (!world.isOver()) {
				List list = world.getObjects(RunningMan.class);
				for (int i = 0; i < list.size(); i++) {
					RunningMan b = (RunningMan)list.get(i);
					if (e.getCode() == KeyCode.UP) {
						//b.move(0, -10);
				    } else if (e.getCode() == KeyCode.DOWN) {
				    	//b.move(0,10);
				    } else if (e.getCode() == KeyCode.LEFT) {
				    	b.setRotationAxis(Rotate.Y_AXIS);
				    	b.setRotate(180);
				    	b.move(-10, 0);
				    } else if (e.getCode() == KeyCode.RIGHT) {
				    	b.setRotationAxis(Rotate.Y_AXIS);
				    	b.setRotate(0);
				    	b.move(10,  0);
				    } else {
				    	
				    }
				}
			}
			
			
		}
			
	}

}
