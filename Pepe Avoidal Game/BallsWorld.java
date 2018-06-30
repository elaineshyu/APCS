package p4_Shyu_Elaine_myEngine;

import java.util.List;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BallsWorld extends World {
	private Random rand = new Random();
	private int man = 0;
	private int ballsCaught = 0;
	private boolean over = false;
	private RunningMan b = new RunningMan();
	private final int BALLSTOCATCH = 50;
	private Text ok;
	private boolean textSet = false;
	private int bird = 0;
	private boolean lost = false;
	private boolean won = false;
	
	
	@Override
	void act(long now) {
		if (bird%10 == 0 && bird <= 20) {
			Bird birb = new Bird();
			add(birb);
			birb.setX(rand.nextInt((int) getWidth()));
		}
		if (textSet) {
			ok.setText("You have caught " + ballCount() + " balls.");
		}
		if (ballsCaught >= BALLSTOCATCH) {
			won = true;
			stop();
			setOver(true);
		} else {
			Balls a;
			if (man == 0) {
				add(b);
				b.setX(200);
				b.setY(410);
				man++;
			}
			int oldTime = 0;
			if (now - oldTime > 1000000000) {
				a = new Balls();
				add(a);
				a.setX(rand.nextInt((int) getWidth()));
				a.setY(0);
			}
			
			List list = b.getIntersectingObjects(Balls.class);
			for (int i = 0; i < list.size(); i++) {
				remove((Actor)list.get(i));
				ballsCaught++;
			}
			
			List list2 = b.getIntersectingObjects(Poop.class);
			if (list2.size() > 0) {
				lost = true;
				stop();
				setOver(true);
			}
		}
		bird++;
		
	}
	
	void setOver(boolean ov) {
		if (lost) {
			Rectangle lostR = new Rectangle(500, 500);
			lostR.setFill(Color.GRAY);
			lostR.setOpacity(0.5); 
			Text won = new Text("YOU LOST!");
			 won.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
			 won.setFill(Color.WHITE);
			 won.setStroke(Color.web("#7080A0"));
			StackPane win = new StackPane();
			win.getChildren().addAll(lostR, won);
			getChildren().add(win);
		} else {
			Rectangle lostR = new Rectangle(500, 500);
			lostR.setFill(Color.GRAY);
			lostR.setOpacity(0.5); 
			Text won = new Text("YOU WON!");
			 won.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
			 won.setFill(Color.WHITE);
			 won.setStroke(Color.web("#7080A0"));
			StackPane win = new StackPane();
			win.getChildren().addAll(lostR, won);
			getChildren().add(win);
		}
		
		over = ov;

	}
	
	
	boolean isOver() {
		return over;
	}
	
	boolean isLost() {
		return lost;
	}
	
	boolean isWon() {
		return won;
	}
	void setText(Text k) {
		ok = k;
		textSet = true;
	}
	
	int ballCount() {
		return ballsCaught;
	}
	

}
