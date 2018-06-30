package p4_Shyu_Elaine_myEngine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private AnimationTimer timer;

	public World() {
		timer = new AnimationTimer() {
			long oldTime = 0;
			@Override
			public void handle(long now) {
				if (now-oldTime > 100000000) {
					act(now);
					List list = new ArrayList<>();
					for (int i = 0; i < getChildren().size(); i++) {
						if (Actor.class.isAssignableFrom(getChildren().get(i).getClass())) {
							list.add(getChildren().get(i));
						}
					}
					for (int i = 0; i < list.size(); i++) {
						if (Balls.class.isAssignableFrom(list.get(i).getClass())) {
							Balls a = (Balls)list.get(i);
							a.act(now);
						}
						
						if (RunningMan.class.isAssignableFrom(list.get(i).getClass())) {
							RunningMan a = (RunningMan)list.get(i);
							a.act(now);
						}
						
						if (Bird.class.isAssignableFrom(list.get(i).getClass())) {
							Bird a = (Bird)list.get(i);
							a.act(now);
						}
						
						if (Poop.class.isAssignableFrom(list.get(i).getClass())) {
							Poop a = (Poop)list.get(i);
							a.act(now);
						}
						
						
					}
					oldTime = now;
				}
				
				
				
			}
			
		};
	}
	abstract void act(long now);
	
	void add(Actor actor) {
		getChildren().add(actor);
	}
	
	<A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> clas) {
		List list = new ArrayList<>();
		for (int i = 0; i < getChildren().size(); i++) {
			if (getChildren().get(i).getClass().equals(clas)) {
				list.add(getChildren().get(i));
			}
		}
		return list;
	}
	
	void remove(Actor actor) {
		for (int i = 0; i < getChildren().size(); i++) {
			if (getChildren().get(i) == actor) {
				getChildren().remove(i);
				break;
			}
		}
	}
	
	void start() {
		timer.start();
	}
	
	void stop() {
		timer.stop();
	}
	
	
}
