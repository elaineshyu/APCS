/*
 * Elaine Shyu, P4, 2/27/17
 * This lab took about 40 minutes, mostly because I was set on
 * doing Pepe for my image. It was pretty easy to learn after
 * getting help in class, so when I got home, I understood
 * the gist of it. The APIs for all the shapes helped me
 * further my understanding as well, although I'm a little
 * confused about arcs still and how the X and Y coordinates
 * work, but after experimenting, I have gained some basic 
 * idea regarding those problems. Most of the time spent was
 * experimenting with the X and Y coordinates and changing the
 * scaling of certain body parts to make them as close to 
 * proportional as possible. The template helped a lot with 
 * figuring out how exactly the coordinate system functioned as 
 * well.
 * 
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class P4_Shyu_Elaine_DankMemes extends Application {
	// measurements
	private final double HEAD_RADIUS = 90; 
	
	private final double BULGE_RADIUS = HEAD_RADIUS/2.1;
	private final double BULGE1_OFF_X = HEAD_RADIUS/5;
	private final double BULGE2_OFF_X = HEAD_RADIUS/3;
	private final double BULGE_OFF_Y = HEAD_RADIUS/1.6;
	
	private final double EYE_RADIUS = HEAD_RADIUS / 10;
	private final double EYE1_OFF_X = HEAD_RADIUS / 1.1;
	private final double EYE2_OFF_X = HEAD_RADIUS / 15;
	private final double EYE_OFF_Y = HEAD_RADIUS / 4;
	
	private final double EYEBULGE_XRADIUS = HEAD_RADIUS/2.2;
	private final double EYEBULGE_YRADIUS =  HEAD_RADIUS/2.7;
	private final double EYEBULGE1_OFF_X = HEAD_RADIUS/1.5;
	private final double EYEBULGE2_OFF_X = HEAD_RADIUS/10;
	private final double EYEBULGE_OFF_Y = HEAD_RADIUS/3;
	
	private final double WHITE_XRADIUS = HEAD_RADIUS / 3;
	private final double WHITE_YRADIUS = HEAD_RADIUS/6;
	private final double WHITE1_OFF_X = HEAD_RADIUS / 1.3;
	private final double WHITE2_OFF_X = HEAD_RADIUS / 7;
	private final double WHITE_OFF_Y = HEAD_RADIUS / 3;
	
	private final double LIPS_XRADIUS = HEAD_RADIUS/1.3;
	private final double LIPS_YRADIUS = HEAD_RADIUS/4;
	private final double LIPS_OFF_X = HEAD_RADIUS/2.5;
	private final double LIPS_OFF_Y = HEAD_RADIUS/2.3;
	
	private final double MOUTH_RADIUS_X = HEAD_RADIUS / 2;
	private final double MOUTH_RADIUS_Y = HEAD_RADIUS / 20;
	private final double MOUTH_OFF_X = HEAD_RADIUS/2;
	private final double MOUTH_OFF_Y = HEAD_RADIUS / 2.2;
	
	
	private final double BODY_LENGTH = HEAD_RADIUS/1.2;
	private final double BODY_WIDTH = HEAD_RADIUS*2;
	private final double BODY_OFF_X = HEAD_RADIUS;
	private final double BODY_OFF_Y  = HEAD_RADIUS/3.5;
	
	// colors

	private final Color HEAD_COLOR = Color.rgb(87, 135, 61);
	private final Color WHITE_COLOR = Color.WHITE;
	private final Color EYE_COLOR = Color.BLACK;
	private final Color LIPS_COLOR = Color.rgb(164, 94, 42);
	private final Color BODY_COLOR = Color.rgb(44, 45, 234);

	public static void main(String[] args) {
		launch(args);
		
	}
	
	void drawPepe(int x, int y, Group root){
		Rectangle body = new Rectangle();
		body.setX(x / 2 - BODY_OFF_X);
		body.setY(y / 2 + BODY_OFF_Y);
		body.setWidth(BODY_WIDTH);
		body.setHeight(BODY_LENGTH);
		body.setArcWidth(20);
		body.setArcHeight(20);
		body.setFill(BODY_COLOR);
		root.getChildren().add(body);
		
		//head
		Circle head = new Circle(HEAD_RADIUS);
		head.setCenterX(x/2);
		head.setCenterY(y/ 2);
		head.setFill(HEAD_COLOR);
		head.setStroke(Color.BLACK);
		root.getChildren().add(head);
		
		//bulges
		Circle rightBulge = new Circle(BULGE_RADIUS);
		rightBulge.setCenterX(head.getCenterX() + BULGE2_OFF_X);
		rightBulge.setCenterY(head.getCenterY() - BULGE_OFF_Y);
		rightBulge.setFill(HEAD_COLOR);
		rightBulge.setStroke(Color.BLACK);
		root.getChildren().add(rightBulge);
		
		
		Circle leftBulge = new Circle(BULGE_RADIUS);
		leftBulge.setCenterX(head.getCenterX() - BULGE1_OFF_X);
		leftBulge.setCenterY(head.getCenterY() - BULGE_OFF_Y);
		leftBulge.setFill(HEAD_COLOR);
		leftBulge.setStroke(Color.BLACK);
		root.getChildren().add(leftBulge);
		
		//eye bulges
		Ellipse rightEBulge = new Ellipse();
		rightEBulge.setRadiusX(EYEBULGE_XRADIUS);
		rightEBulge.setRadiusY(EYEBULGE_YRADIUS);
		rightEBulge.setCenterX(head.getCenterX() + EYEBULGE1_OFF_X);
		rightEBulge.setCenterY(head.getCenterY() - EYEBULGE_OFF_Y);
		rightEBulge.setFill(HEAD_COLOR);
		rightEBulge.setStroke(Color.BLACK);
		root.getChildren().add(rightEBulge);
		
		Ellipse leftEBulge = new Ellipse();
		leftEBulge.setRadiusX(EYEBULGE_XRADIUS);
		leftEBulge.setRadiusY(EYEBULGE_YRADIUS);
		leftEBulge.setCenterX(head.getCenterX() - EYEBULGE2_OFF_X);
		leftEBulge.setCenterY(head.getCenterY() - EYEBULGE_OFF_Y);
		leftEBulge.setFill(HEAD_COLOR);
		leftEBulge.setStroke(Color.BLACK);
		root.getChildren().add(leftEBulge);
		
		
		//whites
		Ellipse rightWhite = new Ellipse();
		rightWhite.setRadiusX(WHITE_XRADIUS);
		rightWhite.setRadiusY(WHITE_YRADIUS);
		rightWhite.setCenterX(head.getCenterX() + WHITE1_OFF_X);
		rightWhite.setCenterY(head.getCenterY() - WHITE_OFF_Y);
		rightWhite.setFill(WHITE_COLOR);
		root.getChildren().add(rightWhite);
		
		Ellipse leftWhite = new Ellipse();
		leftWhite.setRadiusX(WHITE_XRADIUS);
		leftWhite.setRadiusY(WHITE_YRADIUS);
		leftWhite.setCenterX(head.getCenterX() - WHITE2_OFF_X);
		leftWhite.setCenterY(head.getCenterY() - WHITE_OFF_Y);
		leftWhite.setFill(WHITE_COLOR);
		root.getChildren().add(leftWhite);
		
		//eyes
		Circle leftEye = new Circle(EYE_RADIUS);
		leftEye.setCenterX(head.getCenterX() - EYE2_OFF_X);
		leftEye.setCenterY(head.getCenterY() - EYE_OFF_Y);
		leftEye.setFill(EYE_COLOR);
		root.getChildren().add(leftEye);
		
		Circle rightEye = new Circle(EYE_RADIUS);
		rightEye.setCenterX(head.getCenterX() + EYE1_OFF_X);
		rightEye.setCenterY(head.getCenterY() - EYE_OFF_Y);
		rightEye.setFill(EYE_COLOR);
		root.getChildren().add(rightEye);
		
		//lips
		Ellipse lips = new Ellipse();
		lips.setRadiusX(LIPS_XRADIUS);
		lips.setRadiusY(LIPS_YRADIUS);
		lips.setCenterX(head.getCenterX() + LIPS_OFF_X);
		lips.setCenterY(head.getCenterY() + LIPS_OFF_Y);
		lips.setFill(LIPS_COLOR);
		lips.setStroke(Color.BLACK);
		root.getChildren().add(lips);
		
		Arc mouth = new Arc(head.getCenterX() + MOUTH_OFF_X, 
				head.getCenterY() + MOUTH_OFF_Y, MOUTH_RADIUS_X, 
				MOUTH_RADIUS_Y, -180, -180);
		mouth.setFill(null);
		mouth.setStroke(Color.BLACK);
		mouth.setStrokeWidth(3);
		root.getChildren().add(mouth);
	}

	
	public void start(Stage stage) {
		stage.setTitle("miserable pepe");
		stage.setResizable(false);
		stage.sizeToScene();
		
		Group root = new Group();
		Scene scene = new Scene(root, 800, 800);
		stage.setScene(scene);
		
		//body
		Rectangle body = new Rectangle();
		body.setX(scene.getWidth() / 2 - BODY_OFF_X);
		body.setY(scene.getHeight() / 2 + BODY_OFF_Y);
		body.setWidth(BODY_WIDTH);
		body.setHeight(BODY_LENGTH);
		body.setArcWidth(20);
		body.setArcHeight(20);
		body.setFill(BODY_COLOR);
		root.getChildren().add(body);
		
		//head
		Circle head = new Circle(HEAD_RADIUS);
		head.setCenterX(scene.getWidth()/2);
		head.setCenterY(scene.getHeight() / 2);
		head.setFill(HEAD_COLOR);
		head.setStroke(Color.BLACK);
		root.getChildren().add(head);
		
		//bulges
		Circle rightBulge = new Circle(BULGE_RADIUS);
		rightBulge.setCenterX(head.getCenterX() + BULGE2_OFF_X);
		rightBulge.setCenterY(head.getCenterY() - BULGE_OFF_Y);
		rightBulge.setFill(HEAD_COLOR);
		rightBulge.setStroke(Color.BLACK);
		root.getChildren().add(rightBulge);
		
		Circle leftBulge = new Circle(BULGE_RADIUS);
		leftBulge.setCenterX(head.getCenterX() - BULGE1_OFF_X);
		leftBulge.setCenterY(head.getCenterY() - BULGE_OFF_Y);
		leftBulge.setFill(HEAD_COLOR);
		leftBulge.setStroke(Color.BLACK);
		root.getChildren().add(leftBulge);
		
		//eye bulges
		Ellipse rightEBulge = new Ellipse();
		rightEBulge.setRadiusX(EYEBULGE_XRADIUS);
		rightEBulge.setRadiusY(EYEBULGE_YRADIUS);
		rightEBulge.setCenterX(head.getCenterX() + EYEBULGE1_OFF_X);
		rightEBulge.setCenterY(head.getCenterY() - EYEBULGE_OFF_Y);
		rightEBulge.setFill(HEAD_COLOR);
		rightEBulge.setStroke(Color.BLACK);
		root.getChildren().add(rightEBulge);
		
		Ellipse leftEBulge = new Ellipse();
		leftEBulge.setRadiusX(EYEBULGE_XRADIUS);
		leftEBulge.setRadiusY(EYEBULGE_YRADIUS);
		leftEBulge.setCenterX(head.getCenterX() - EYEBULGE2_OFF_X);
		leftEBulge.setCenterY(head.getCenterY() - EYEBULGE_OFF_Y);
		leftEBulge.setFill(HEAD_COLOR);
		leftEBulge.setStroke(Color.BLACK);
		root.getChildren().add(leftEBulge);
		
		//whites
		Ellipse rightWhite = new Ellipse();
		rightWhite.setRadiusX(WHITE_XRADIUS);
		rightWhite.setRadiusY(WHITE_YRADIUS);
		rightWhite.setCenterX(head.getCenterX() + WHITE1_OFF_X);
		rightWhite.setCenterY(head.getCenterY() - WHITE_OFF_Y);
		rightWhite.setFill(WHITE_COLOR);
		root.getChildren().add(rightWhite);
		
		Ellipse leftWhite = new Ellipse();
		leftWhite.setRadiusX(WHITE_XRADIUS);
		leftWhite.setRadiusY(WHITE_YRADIUS);
		leftWhite.setCenterX(head.getCenterX() - WHITE2_OFF_X);
		leftWhite.setCenterY(head.getCenterY() - WHITE_OFF_Y);
		leftWhite.setFill(WHITE_COLOR);
		root.getChildren().add(leftWhite);
		
		//eyes
		Circle leftEye = new Circle(EYE_RADIUS);
		leftEye.setCenterX(head.getCenterX() - EYE2_OFF_X);
		leftEye.setCenterY(head.getCenterY() - EYE_OFF_Y);
		leftEye.setFill(EYE_COLOR);
		root.getChildren().add(leftEye);
		
		Circle rightEye = new Circle(EYE_RADIUS);
		rightEye.setCenterX(head.getCenterX() + EYE1_OFF_X);
		rightEye.setCenterY(head.getCenterY() - EYE_OFF_Y);
		rightEye.setFill(EYE_COLOR);
		root.getChildren().add(rightEye);
		
		//lips
		Ellipse lips = new Ellipse();
		lips.setRadiusX(LIPS_XRADIUS);
		lips.setRadiusY(LIPS_YRADIUS);
		lips.setCenterX(head.getCenterX() + LIPS_OFF_X);
		lips.setCenterY(head.getCenterY() + LIPS_OFF_Y);
		lips.setFill(LIPS_COLOR);
		lips.setStroke(Color.BLACK);
		root.getChildren().add(lips);
		
		Arc mouth = new Arc(head.getCenterX() + MOUTH_OFF_X, 
				head.getCenterY() + MOUTH_OFF_Y, MOUTH_RADIUS_X, 
				MOUTH_RADIUS_Y, -180, -180);
		mouth.setFill(null);
		mouth.setStroke(Color.BLACK);
		mouth.setStrokeWidth(3);
		root.getChildren().add(mouth);
		
		stage.show();
	}
	

}
