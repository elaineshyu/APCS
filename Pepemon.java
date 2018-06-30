import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pepemon extends Application {

private Scene scene1, sceneBattle;
private Stage total;
private ImageView image;
private static int start = 0;
private IntegerProperty timeSeconds;
private Timeline timeline;
private Label timerLabel = new Label();
private Timeline timeline1 = new Timeline();
private Text encounter;
private Rectangle encounterR;
private Group layout1; 
private StackPane encounterStack;
private boolean isEncounter;

    
@Override
	public void start(Stage primaryStage) {
		encounterR = new Rectangle(800, 600);
		encounterStack = new StackPane();
		layout1 = new Group(); 
		encounter = new Text("PEPEMON ENCOUNTERED!");
		scene1= new Scene(layout1, 800, 600);
        total = primaryStage;
		primaryStage.setTitle("Pepemon");
		MyKeyboardHandler keys = new MyKeyboardHandler();
		
		encounterR.setX(0);
		encounterR.setY(0);
		encounterR.setFill(Color.BLACK);
		encounterR.setOpacity(0.5);
		
		encounter.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
	    encounter.setFill(Color.WHITE);
	    encounter.setStroke(Color.web("#7080A0"));
	    
	    encounterStack.getChildren().addAll(encounterR, encounter);
		
		//Scene 1
		
		Image playerImage = new Image(getClass().getResource("sprite.png").toExternalForm());
		image = new ImageView(playerImage);
		image.setFitHeight(100);
		image.setFitWidth(100);
		image.setX(scene1.getWidth()/2);
		image.setY(scene1.getHeight()/2);
		
		
		layout1.getChildren().addAll(image);
		
		scene1.addEventHandler(KeyEvent.KEY_PRESSED, keys);
		scene1.setFill(Color.PALETURQUOISE);
		        
		primaryStage.setScene(scene1);
		primaryStage.show();
		MyTimer bob = new MyTimer();
		
		start = randomTime();
		timeSeconds= new SimpleIntegerProperty(start);
		timerLabel.textProperty().bind(timeSeconds.divide(100).asString());
	    timeSeconds.set(start);
	    timeline = new Timeline();
	    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(start/100), new KeyValue(timeSeconds, 0)));
	    timeline.playFromStart();
		timeline.onFinishedProperty().set(bob);
		
	}

	private class MyTimer implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
				layout1.getChildren().addAll(encounterStack);
				isEncounter = true;
				int start1 = 1;
				IntegerProperty timeSeconds1= new SimpleIntegerProperty(start);
				Label timerLabel1 = new Label();
				timerLabel1.textProperty().bind(timeSeconds.divide(100).asString());
			    timeSeconds1.set(start);
			    timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(start/100),new KeyValue(timeSeconds, 0)));
			    timeline1.playFromStart();
			    MyTimeEncounter bob = new MyTimeEncounter();
				timeline1.onFinishedProperty().set(bob);
				
			
		}
		
	}
	
	private class MyTimeEncounter implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			layout1.getChildren().remove(1);
			total.setScene(sceneBattle);
			P4_Shyu_Elaine_DankMemers battle = new P4_Shyu_Elaine_DankMemers();
			sceneBattle = battle.getScene();
			try {
				battle.start(total);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isEncounter = false;
		}
		
	}
	
	public int randomTime() {
		Random rand = new Random();
		return (rand.nextInt(10) +5) * 100;
	}
	
	private class MyKeyboardHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent e) {
			if (!isEncounter) {
				if (e.getCode() == KeyCode.UP) {
					image.setY(image.getY()-10);
			    } else if (e.getCode() == KeyCode.DOWN) {
			    	image.setY(image.getY()+10);
			    } else if (e.getCode() == KeyCode.LEFT) {
			    	image.setX(image.getX()-10);
			    } else if (e.getCode() == KeyCode.RIGHT) {
			    	image.setX(image.getX()+10);
			    } else {
			    	
			    }
			}
			
			
		}
		
	}
/*
	private class MyButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == button1) {
				total.setScene(sceneBattle);
				P4_Shyu_Elaine_DankMemers battle = new P4_Shyu_Elaine_DankMemers();
				sceneBattle = battle.getScene();
				try {
					battle.start(total);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				total.setScene(scene1);
			}
			
		}
		
	}
	*/

	public static void main(String[] args) {
		launch(args);
	}

	
    
}