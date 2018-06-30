package p4_Shyu_Elaine_myEngine;

import javafx.scene.image.Image;

public class RunningMan extends Actor{
	Image pepe = new Image("pepe.png");
	@Override
	void act(long now) {
		setImage(pepe);
	    
	    if (World.class.isAssignableFrom(getWorld().getClass())) {
	    	BallsWorld b = (BallsWorld)getWorld();
	    	if (b.isLost()) {
	    		setImage(new Image("sadpepe.png"));
	    	}
	    	
	    	if (b.isWon()) {
	    		setImage(new Image("won.png"));
	    	}
	    }
	    
	    setFitWidth(100);
		setFitHeight(100);
		setPreserveRatio(true);
	    setSmooth(true);
		
	}

}
