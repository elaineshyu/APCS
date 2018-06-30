package p4_Shyu_Elaine_myEngine;

import javafx.scene.image.Image;

public class Balls extends Actor {
	Image ball = new Image("ball.png");
	@Override
	void act(long now) {
		setImage(ball);
		setFitWidth(50);
		setFitHeight(50);
		setPreserveRatio(true);
	    setSmooth(true);
	    move(0, 10);
	    if (this.getY() > 500) {
	    	setY(0);
	    }
		
	}

}
