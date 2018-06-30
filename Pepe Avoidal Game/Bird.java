package p4_Shyu_Elaine_myEngine;

import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Bird extends Actor {
	Image bird = new Image("bird.png");
	private boolean direction = true;
	private int poops = 0;
	@Override
	void act(long now) {
		setImage(bird);
		setFitWidth(50);
		setFitHeight(50);
		setPreserveRatio(true);
	    setSmooth(true);
	    if (this.getX() > 500) {
	    	setRotationAxis(Rotate.Y_AXIS);
	    	setRotate(180);
	    	direction = false;
	    }
	    
	    if (poops%50 == 0 && poops <=200) {
	    	Poop poop = new Poop();
		    poop.setX(this.getX());
		    getWorld().add(poop);
	    }
	    
	    
	    if (this.getX() < 0) {
	    	setRotationAxis(Rotate.Y_AXIS);
	    	setRotate(0);
	    	direction = true;
	    }
	    if (direction) {
	    	 move(10, 0);
	    } else {
	    	move (-10, 0);
	    }
	   
		poops++;
	}

}
