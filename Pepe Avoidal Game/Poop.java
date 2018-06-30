package p4_Shyu_Elaine_myEngine;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Poop extends Actor {
	Image poop = new Image("poop.png");
	@Override
	void act(long now) {
		setImage(poop);
		setFitWidth(50);
		setFitHeight(50);
		setPreserveRatio(true);
	    setSmooth(true);
	    move(0, 5);
	    
	    if (getY() > 500) {
	    	List list = getWorld().getObjects(Bird.class);
	    	Bird b = (Bird)list.get(0);
	    	this.setX(b.getX());
	    	this.setY(0);
	    }
	    
	    List list = this.getIntersectingObjects(RunningMan.class);
	    if (list.size() > 0) {
	    	rotatePoop();
	    }
	    
	    
	}
	
	void rotatePoop() {
		AnimationTimer time = new AnimationTimer() {
			long oldTime = 0;
			@Override
			public void handle(long now) {
				if (now - oldTime > 1000000000) {
					setRotate(getRotate() + 90);
					oldTime = now;
				}
				
			}
    		
    	};
    	time.start();
	}
}
