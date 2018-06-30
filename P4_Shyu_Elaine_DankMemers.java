
import java.io.File;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class P4_Shyu_Elaine_DankMemers extends Application {
	private Group root;
	private Scene test;
	private final Color HEAD_COLOR = Color.rgb(87, 135, 61);
	private static final Integer STARTTIME = 2;
	private IntegerProperty timeSeconds;
	private Timeline timeline;
    private Label timerLabel = new Label();
    private StackPane stuff = new StackPane();
    private ChoiceBox cb = new ChoiceBox();
    private ChoiceBox cbd = new ChoiceBox();
    private Button button1 = new Button("Throw Spicy Meme");
    private Text title = new Text("\t\t GODLY JESUS PEPE");
    private Group attacks = new Group();
    private Text win = new Text("YOU ARE A PEPE MURDERER!");
    private Rectangle winR = new Rectangle(800,600);
    private Text captured = new Text("CAPTURED NEW MEMEMON!");
    private Text lost = new Text("YOU DIED!");
    private Rectangle hpBar = new Rectangle();//weak pepe
    private Rectangle hpBarStrong = new Rectangle();
    private int currentPosLayersPepe = 0;
    private int memeElixir10 = 5;
    private int memeElixir50 = 2;
    private Label user = null;
    private Text current = new Text("Welcome to Me Me Land.");
    private VBox rightBox = null;
    private Text enemy = new Text("Enemy: Hi There Friend");
    private int getBigPepe = 0;
    private Rectangle crying = new Rectangle(0, 0);
    private Rectangle crying2 = new Rectangle(0, 0);
    private int numPokeBalls = 5;
    private MediaPlayer mediaPlayer = null;
    private boolean soundOn = true;
    private MediaPlayer winSong = null;
    private HBox userInfo;
    private TextField userName = null;
    private MediaPlayer ballSound = null;
    private int tears = 0;
    private int currentHPPepe= 0;
    private int currentHPSanic=0;
    
	public static void main(String[] args) {
		launch();
	}
	
	public Scene getScene() {
		return test;
	}
	
	public void start(Stage primaryStage) throws Exception {
		root = new Group();
		test = new Scene(root, 800, 600);
		timeSeconds = new SimpleIntegerProperty(STARTTIME*100);
		
		
		Media sounds = new Media(new File("Victory.mp3").toURI().toString());
	    winSong = new MediaPlayer(sounds);
	    
	    
	    user = new Label("Hello");
	    user.setTextFill(Color.BLACK);
	    userName = new TextField();
	    Name sure = new Name();
	    userName.setPromptText("Enter Your Name!");
	    Button submit = new Button("Submit");
	    submit.setOnAction(sure);
	    userInfo = new HBox();
	    userInfo.getChildren().addAll(userName, submit);
	    userInfo.setLayoutX(500);
	    userInfo.setLayoutY(10);
	    
	    
		crying.setFill(Color.AZURE);
		crying2.setFill(Color.AZURE);
		crying.setX(130);
		crying.setY(158);
		crying2.setX(210);
		crying2.setY(158);
		crying.setWidth(20);
		crying2.setWidth(20);
		primaryStage.setTitle("pitiful battle");
		BorderPane border = new BorderPane(); // Layout 1
		VBox vbox = addTopVBox(); // Layout 2
		HBox hbox = addBottomHBox();
		
		//end game
		winR.setX(0);
		winR.setY(0);
		winR.setFill(Color.GRAY);
		winR.setOpacity(0.5);
		
		win.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
	    win.setFill(Color.WHITE);
	    win.setStroke(Color.web("#7080A0"));
	    
	    lost.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
	    lost.setFill(Color.WHITE);
	    lost.setStroke(Color.web("#7080A0"));
	    
	    captured.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
	    captured.setFill(Color.BLACK);
	    captured.setStroke(Color.web("#7080A0"));
		//end game
	    
		Text title = new Text("LOADING...");
	    title.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
	    title.setFill(Color.WHITE);
	    title.setStroke(Color.web("#7080A0"));
	   
	    ProgressBar pb = new ProgressBar(); //Control1
	   
	    //experimental stuff start
	    timerLabel.textProperty().bind(timeSeconds.divide(100).asString());
	    pb.progressProperty().bind(timeSeconds.divide(STARTTIME*100.0).subtract(1).multiply(-1));
	    timeSeconds.set((STARTTIME)*100);
	    timeline = new Timeline();
	    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(STARTTIME),new KeyValue(timeSeconds, 0)));
	    timeline.playFromStart();
	    MyEventHandler bob = new MyEventHandler();
	    timeline.onFinishedProperty().set(bob);
	    //experimental stuff end
	    
	    border.setLeft(vbox);
	    border.setBottom(hbox);
	    rightBox = addBottomVBox();
	    rightBox.getChildren().addAll(current, enemy);
	    border.setRight(rightBox);
		
		stuff.getChildren().addAll(border, pb, title);
		
		Circle head = new Circle(200);
		head.setCenterX(500);
		head.setCenterY(400);
		head.setFill(HEAD_COLOR);
		head.setStroke(Color.BLACK);
		Circle bump = new Circle(200 / 2.1);
		bump.setCenterX(430);
		bump.setCenterY(250);
		bump.setFill(HEAD_COLOR);
		bump.setStroke(Color.BLACK);
		Circle bump2 = new Circle(200 / 2.1);
		bump2.setCenterX(550);
		bump2.setCenterY(250);
		bump2.setFill(HEAD_COLOR);
		bump2.setStroke(Color.BLACK);
		Rectangle loading = new Rectangle(800, 600);
		loading.setX(0);
		loading.setY(0);
		loading.setFill(Color.GRAY);
		loading.setOpacity(0.3);
		getBigPepe = root.getChildren().size();
		tears = root.getChildren().size();
		tears += 5;
		root.getChildren().addAll(bump2, bump, head, stuff, userInfo, loading, crying, crying2, attacks);
		primaryStage.setScene(test);
		String musicFile = "PokemonCS.mp3";     
	    Media sound = new Media(new File(musicFile).toURI().toString());
	    mediaPlayer = new MediaPlayer(sound);
	    
        primaryStage.show();
       
	}
	
	public VBox addBottomVBox() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		Rectangle white = new Rectangle(100, 350);
		white.setOpacity(0);
	    vbox.setSpacing(3);
	    title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	    title.setFill(Color.BLACK);
	    title.setStroke(Color.WHITE);
	    
	    hpBarStrong.setWidth(300);
	    hpBarStrong.setHeight(10);
	    hpBarStrong.setFill(Color.GREEN);
	    hpBarStrong.setStroke(Color.WHITE);
	    hpBarStrong.setX(0);
	    hpBarStrong.setY(0);    
	    vbox.getChildren().addAll(white, title, hpBarStrong, user);
	    return vbox;
	}
	
	public VBox addTopVBox() {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    Text title = new Text("WEAK PEPE");
	    title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	    title.setFill(Color.BLACK);
	    title.setStroke(Color.WHITE);
	    
	    hpBar.setWidth(200);
	    hpBar.setHeight(10);
	    hpBar.setFill(Color.GREEN);
	    hpBar.setX(0);
	    hpBar.setY(0);  
	    hpBar.setStroke(Color.WHITE);
	    vbox.getChildren().addAll(title, hpBar);
	    currentPosLayersPepe = root.getChildren().size();
	    P4_Shyu_Elaine_DankMemes pepez = new P4_Shyu_Elaine_DankMemes();
		pepez.drawPepe(290, 350, root);
	    return vbox;
	}
	
	public HBox addBottomHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(0));
		hbox.setSpacing(8);
		FlowPane flow = new FlowPane();
	    flow.setPadding(new Insets(20, 12, 15, 12));
	    flow.setVgap(7);
	    flow.setHgap(4);
	    flow.setPrefWrapLength(510);
	    flow.setStyle("-fx-background-color: #336699;");
	     //Control 2
	    button1.setPrefSize(250, 20);
	    Button button2 = new Button("Eat Enemy");
	    button2.setPrefSize(250, 20);
	    Button button3 = new Button("Fart Bomb");
	    button3.setPrefSize(250, 20);
	    Button button4 = new Button("Enlightenment");
	    button4.setPrefSize(250, 20);  
	    MyButtonEventHandler butt = new MyButtonEventHandler();
	    button1.setOnAction(butt);
	    button2.setOnAction(butt);
	    button3.setOnAction(butt);
	    button4.setOnAction(butt);
	    
	    cb.setItems(FXCollections.observableArrayList(
	        "Change Mememon", new Separator(), "None"));
	    
	    //MyPokemonChanger dank1 = new MyPokemonChanger();
	    //cb.getSelectionModel().selectedIndexProperty().addListener(dank1);

	    cbd.setItems(FXCollections.observableArrayList(
	        "Bag Items", new Separator(),  
	         "Meme Elixir (10)", "Meme Elixir (50)"));
	    MyCBEventHandler dank = new MyCBEventHandler();
	    cbd.getSelectionModel().selectedIndexProperty().addListener(dank);
	    
	    ToggleHandler ok = new ToggleHandler();
	    ToggleButton tb = new ToggleButton("Sound"); //Control 4
	    ToggleGroup group = new ToggleGroup();
	    tb.setToggleGroup(group);
	    tb.setPrefSize(78, 20);
	    group.selectedToggleProperty().addListener(ok);
	    
	    Button button5 = new Button("Memeball");
	    button5.setPrefSize(120, 20);
	    ThrowBall get = new ThrowBall();
	    button5.setOnAction(get);
	    
	    flow.getChildren().addAll(button1, button2, button3, button4, cb, 
	    		cbd, tb,button5);
	    StackPane stack = new StackPane();
	    Rectangle back = new Rectangle(250, 140);
	    back.setFill(Color.TEAL);
	    back.setStroke(Color.BLACK);
	    Rectangle helpIcon = new Rectangle(220, 25.0);
	    helpIcon.setFill(Color.CORNFLOWERBLUE);
	    helpIcon.setStroke(Color.DARKBLUE);
	    helpIcon.setArcHeight(3.5);
	    helpIcon.setArcWidth(3.5);
	    Text helpText = new Text("MEME 9001/9000");
	    helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	    helpText.setFill(Color.WHITE);
	    helpText.setStroke(Color.BLACK); 
	    Text type = new Text("TYPE / MEMELORD");
	    type.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	    type.setFill(Color.WHITE);
	    type.setStroke(Color.BLACK);
	    stack.getChildren().addAll(back, helpIcon, helpText, type);
	    stack.setAlignment(Pos.CENTER_RIGHT);  
	    StackPane.setMargin(helpText, new Insets(0, 10, 100, 0));
	    StackPane.setMargin(type, new Insets(0, 10, 0, 0));	   
	    HBox.setHgrow(stack, Priority.ALWAYS); 
	    hbox.getChildren().addAll(flow, stack);
	    return hbox;
	}
	
	private class ToggleHandler implements ChangeListener<Toggle> {

		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			if (newValue != null) {
				mediaPlayer.pause();
				soundOn = false;
			} else if (newValue == null){
			    mediaPlayer.play();
			    soundOn = true;
			}
			
		}
		
	}
	
	private class MyEventHandler implements EventHandler <ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			mediaPlayer.setStartTime(new Duration(2000));
			mediaPlayer.play();
			stuff.getChildren().remove(2);
			stuff.getChildren().remove(1);
			root.getChildren().remove(17);
		}
	}
	
	
	private class Name implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if ((userName.getText() != null && !userName.getText().isEmpty() && userName.getText().length() < 15)) {
				user.setText("Hello " + userName.getText() + "!");
	            root.getChildren().remove(16);
	        } else {
	            user.setText("Please enter your name!");
	        }
			
		}
		
	}
	
	private class ThrowBall implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Circle pokeball2 = new Circle(50);
			pokeball2.setFill(Color.RED);
			pokeball2.setCenterX(150);
			pokeball2.setCenterY(195);
			pokeball2.setStroke(Color.BLACK);
			pokeball2.setStrokeWidth(4);
			Circle pokeball3 = new Circle(25);
			pokeball3.setFill(Color.BLACK);
			pokeball3.setCenterX(150);
			pokeball3.setCenterY(195);
			Circle pokeball4 = new Circle(15);
			pokeball4.setFill(Color.WHITE);
			pokeball4.setCenterX(150);
			pokeball4.setCenterY(195);
			Arc pokeball = new Arc(150, 190, 47, 
					5, 180, 180);
			pokeball.setFill(null);
			pokeball.setStroke(Color.BLACK);
			pokeball.setStrokeWidth(5);
			Circle pokeball5 = new Circle(2);
			pokeball5.setFill(Color.BLACK);
			pokeball5.setCenterX(150);
			pokeball5.setCenterY(195);
			Ellipse pokeball6 = new Ellipse();
			pokeball6.setCenterX(150);
			pokeball6.setCenterY(205);
			pokeball6.setFill(Color.WHITE);
			pokeball6.setRadiusX(45);
			pokeball6.setRadiusY(35);
			
			Ellipse pokeball7 = new Ellipse();
			pokeball7.setCenterX(150);
			pokeball7.setCenterY(180);
			pokeball7.setFill(Color.RED);
			pokeball7.setRadiusX(45);
			pokeball7.setRadiusY(21);
			
			
			
			if (numPokeBalls > 0) {
				int currentPos = root.getChildren().size();
				root.getChildren().addAll(pokeball2, pokeball6, pokeball7, pokeball, pokeball3, pokeball4, pokeball5);
				numPokeBalls--;
				if (Captured() == true) {
					removePepe();
					StackPane captureds = new StackPane();
					captureds.getChildren().addAll(winR, captured);
					root.getChildren().add(captureds);
					if (soundOn == true) {
						crying.setHeight(0);
						crying2.setHeight(0);
						Media captureSuccess = new Media(new File("CatchSuccess.m4a").toURI().toString());
					    ballSound = new MediaPlayer(captureSuccess);
						ballSound.play();
					    mediaPlayer.stop();
					}
				} else {
					if (soundOn == true) {
						Media captureBall = new Media(new File("Captured.m4a").toURI().toString());
					    ballSound = new MediaPlayer(captureBall);
					    ballSound.setStartTime(new Duration(1000));
						ballSound.play();
					}
					current = new Text("Failed to capture!");
					rightBox.getChildren().set(4, current);
					enemy = new Text("You have " + numPokeBalls + " Memeballs left.");
					for (int i = 0; i < 7; i ++) {
						root.getChildren().remove(currentPos);
						crying.setHeight(0);
						crying2.setHeight(0);
					}
					rightBox.getChildren().set(5, enemy);
				}
			} else {
				current = new Text("You have no Memeballs left.");
				rightBox.getChildren().set(4, current);
				enemy = new Text("");
				rightBox.getChildren().set(5, enemy);
			}
			
			
			
		}
		
	}
	
	private class MyButtonEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			EffectiveAttacking();
 
		}
		
	}
	
	private class MyPokemonChanger implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (newValue.equals(2)) {
				Circle head = new Circle(200);
				head.setCenterX(500);
				head.setCenterY(400);
				head.setFill(Color.BLUE);
				head.setStroke(Color.BLACK);
				Polygon polygon = new Polygon();
				polygon.getPoints().addAll(new Double[]{
				    500.0, 200.0,
				    620.0, 180.0,
				    500.0, 400.0 });
				polygon.setFill(Color.BLUE);
				polygon.setStroke(Color.BLACK);
				
				Polygon polygon2 = new Polygon();
				polygon2.getPoints().addAll(new Double[]{
						500.0, 400.0,
					    620.0, 380.0,
					    500.0, 600.0 });
				polygon2.setFill(Color.BLUE);
				polygon2.setStroke(Color.BLACK);
				
				root.getChildren().set(getBigPepe, head);
				root.getChildren().set(getBigPepe+1, polygon);
				root.getChildren().set(getBigPepe+2, polygon2);
				
				
				
			}
			
		}
		
	}
	
	private class MyCBEventHandler implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.equals(2) && memeElixir10 > 0) {
					int currentl = (int) hpBarStrong.getWidth();
					if (currentl < 290) {
						hpBarStrong.setWidth(currentl+10);
					} else {
						hpBarStrong.setWidth(300);
					}
					
					memeElixir10 --;
					if (memeElixir10 > 0) {
						if (soundOn == true) {
							Media elixir = new Media(new File("Elixir.m4a").toURI().toString());
						    ballSound = new MediaPlayer(elixir);
							ballSound.play();
						}
						
						current = new Text("You have " + memeElixir10 + " Elixirs (10) Left");
						rightBox.getChildren().set(4, current);
						enemy = new Text("");
						rightBox.getChildren().set(5, enemy);
					} else {
						current = new Text("You have 0" + " Elixirs (10) Left");
						rightBox.getChildren().set(4, current);
						enemy = new Text("");
						rightBox.getChildren().set(5, enemy);
					}
					
				} else if (newValue.equals(3) && memeElixir50 > 0) {
					if (soundOn == true) {
						Media elixir = new Media(new File("Elixir.m4a").toURI().toString());
					    ballSound = new MediaPlayer(elixir);
						ballSound.play();
					}
					int currentl = (int) hpBarStrong.getWidth();
					if (currentl < 250) {
						hpBarStrong.setWidth(currentl+50);
					} else {
						hpBarStrong.setWidth(300);
					}
					
					
					memeElixir50--;
					if (memeElixir50 > 0) {
						current = new Text("You have " + memeElixir50 + " Elixirs (50) Left");
						rightBox.getChildren().set(4, current);
						enemy = new Text("");
						rightBox.getChildren().set(5, enemy);
					} else {
						current = new Text("You have 0" + " Elixirs (50) Left");
						rightBox.getChildren().set(4, current);
						enemy = new Text("");
						rightBox.getChildren().set(5, enemy);
					}
				}
				checkColorHPBig();

			}
			
		
	}
	
	public boolean Captured() {
		Random rand = new Random();
		int chance = rand.nextInt(10);
		if (chance < 6) {
			return false;
		} else{
			return true;
		}
	}
	
	public int Effective() {
		Random rand = new Random();
		int chance = rand.nextInt(10);
		if (chance == 0) {
			return 100;
		} else if (chance < 7) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void removePepe() {
		for (int i = 0; i < 12; i++) {
			root.getChildren().remove(currentPosLayersPepe);
		}
		
	}
	
	public void move() {
		 TranslateTransition translateTransition = new TranslateTransition(); 
		 TranslateTransition translateTransition3 = new TranslateTransition(); 
		 TranslateTransition translateTransition2 = new TranslateTransition(); 

		 translateTransition3.setAutoReverse(true);
		 translateTransition2.setAutoReverse(true);
		 translateTransition.setAutoReverse(true);
		 
		 translateTransition.setDuration(Duration.millis(100)); 
		 translateTransition.setNode(root.getChildren().get(getBigPepe));
		 translateTransition.setByX(-50); 
		 translateTransition.setCycleCount(2);
		 translateTransition.play(); 
		 translateTransition2.setDuration(Duration.millis(100)); 
		 translateTransition2.setNode(root.getChildren().get(getBigPepe+1));
		 translateTransition2.setByX(-50); 
		 translateTransition2.setCycleCount(2);
		 translateTransition2.play(); 
		 translateTransition3.setDuration(Duration.millis(100)); 
		 translateTransition3.setNode(root.getChildren().get(getBigPepe+2));
		 translateTransition3.setByX(-50); 
		 translateTransition3.setCycleCount(2);
		 translateTransition3.play(); 
		
		 
	}
	
	public void moveSmol() {
		for (int i = 0; i < 12; i++) {
			translateSmol(currentPosLayersPepe + i);
		}
		
		translateSmol(tears);
		translateSmol(tears +1);

	}
	
	public void translateSmol(int index) {
		TranslateTransition translateTransition = new TranslateTransition();
		translateTransition.setAutoReverse(true);
		 translateTransition.setDuration(Duration.millis(100)); 
		 translateTransition.setNode(root.getChildren().get(index));
		 translateTransition.setByX(50); 
		 translateTransition.setCycleCount(2);
		 translateTransition.play(); 
	}
	
	public void checkColorHPSmol() {
		int check = (int) hpBar.getWidth();
		if (check < 100 && check >= 30) {
			hpBar.setFill(Color.YELLOW);
		} else if (check < 30) {
			hpBar.setFill(Color.RED);
		}
		
		if (check >= 100 && !hpBar.getFill().equals(Color.GREEN)) {
			hpBar.setFill(Color.GREEN);
		}
	}
	
	public void checkColorHPBig() {
		int check = (int) hpBarStrong.getWidth();
		if (check < 150 && check >= 50) {
			hpBarStrong.setFill(Color.YELLOW);
		} else if (check < 50) {
			hpBarStrong.setFill(Color.RED);
		}
		
		if (check >= 150 && !hpBarStrong.getFill().equals(Color.GREEN)) {
			hpBarStrong.setFill(Color.GREEN);
		}
	}


	
	public void EffectiveAttacking() {
		if (Effective() == 1) {
			if (hpBarStrong.getWidth() != 0) {
				move();
			}
			
			 
			current = new Text("Attacked Enemy!");
			rightBox.getChildren().set(4, current);
			int currentWidth = (int) hpBar.getWidth();
			if (currentWidth > 10) {
				if (soundOn == true) {
					Media attack = new Media(new File("Attack.m4a").toURI().toString());
				    ballSound = new MediaPlayer(attack);
					ballSound.play();
				}
				hpBar.setWidth(currentWidth-10);
				crying.setHeight(90);
				crying2.setHeight(90);
				
			} else {
				hpBar.setWidth(0);
				removePepe();
				crying.setHeight(0);
				crying2.setHeight(0);
				StackPane wins = new StackPane();
				wins.getChildren().addAll(winR, win);
				root.getChildren().addAll(wins);
				
				if (soundOn == true) {
					winSong.play();
				    mediaPlayer.stop();
				}
			    
			}
		} else if (Effective() == 100){
			if (hpBarStrong.getWidth() != 0) {
				move();
			}
			int currentWidth = (int) hpBar.getWidth();
			current = new Text("Attacked Enemy! Super Effective!");
			rightBox.getChildren().set(4, current);
			if (currentWidth > 50) {
				if (soundOn == true) {
					Media attack = new Media(new File("Attack.m4a").toURI().toString());
				    ballSound = new MediaPlayer(attack);
					ballSound.play();
				}
				hpBar.setWidth(currentWidth-50);
				crying.setHeight(90);
				crying2.setHeight(90);
			} else {
				hpBar.setWidth(0);
				removePepe();
				StackPane wins = new StackPane();
				wins.getChildren().addAll(winR, win);
				root.getChildren().addAll(wins);
				crying.setHeight(0);
				crying2.setHeight(0);

				if (soundOn == true) {
					winSong.play();
				    mediaPlayer.stop();
				}
			}
		} else {
			if (soundOn == true) {
				Media attack = new Media(new File("Attack.m4a").toURI().toString());
			    ballSound = new MediaPlayer(attack);
				ballSound.play();
			}
			current = new Text("Missed Enemy!");
			rightBox.getChildren().set(4, current);
			crying.setHeight(0);
			crying2.setHeight(0);
		}
		 
		 checkColorHPBig();
		
		// for being attacked
		if (Effective() == 1) {
			if (hpBar.getWidth() != 0) {
				moveSmol();
			}
			
			int currentWidth = (int) hpBarStrong.getWidth();
			rightBox.getChildren().remove(5);
			enemy = new Text("Enemy Attacked!");
			rightBox.getChildren().add(enemy);
			if (currentWidth > 2) {
				hpBarStrong.setWidth(currentWidth-2);
			} else {
				hpBarStrong.setWidth(0);
				root.getChildren().remove(getBigPepe);
				root.getChildren().remove(getBigPepe);
				root.getChildren().remove(getBigPepe);
				StackPane wins = new StackPane();
				wins.getChildren().addAll(winR, lost);
				root.getChildren().addAll(wins);
			}
		} else if (Effective() == 100){
			if (hpBar.getWidth() != 0) {
				moveSmol();
			}
			rightBox.getChildren().remove(5);
			enemy = new Text("Enemy Attacked! Super Effective!");
			rightBox.getChildren().add(enemy);
			int currentWidth = (int) hpBarStrong.getWidth();
			if (currentWidth > 10) {
				hpBarStrong.setWidth(currentWidth-10);
			} else {
				hpBarStrong.setWidth(0);
				root.getChildren().remove(getBigPepe);
				root.getChildren().remove(getBigPepe);
				root.getChildren().remove(getBigPepe);
				StackPane wins = new StackPane();
				wins.getChildren().addAll(winR, lost);
				root.getChildren().addAll(wins);
			}
		} else {
			rightBox.getChildren().remove(5);
			enemy = new Text("Enemy Missed!");
			rightBox.getChildren().add(enemy);
		}
		
		checkColorHPSmol();
	}
	
	//NEEDS WORK
	public Group attackMoves(int num) {
		Group attackStuff = new Group();
		//if (num == 1) {
			Random rand = new Random();
			Image image = null;
			int pick = rand.nextInt(2);
			if (pick == 0) {
				image = new Image("Doggo.png");
			} else if (pick == 1) {
				image = new Image("Sanic.png");
			} else {
				image = new Image("Sanic2.png");
			}
			
			ImageView iv2 = new ImageView();
	         iv2.setImage(image);
	         iv2.setFitWidth(100);
	         iv2.setPreserveRatio(true);
	         iv2.setSmooth(true);
	         iv2.setCache(true);
	         
	         attackStuff.getChildren().add(iv2);
	         
	         return attackStuff;
		/*	
		} else if (num == 2) {
			
		} else if (num == 3) {
			
		} else if (num == 4) {
			
		}
		*/
	}
	
}
