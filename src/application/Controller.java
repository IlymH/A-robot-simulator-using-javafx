package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Controller implements Initializable {
	@FXML
	Pane drawPane;
	@FXML
	private Button start,init;
	@FXML
    Label Cordinates,RoboTC,RobotR,RobotS,SensorDR,SensorDL;
	private Circle c;
	private Animation t;
	private boolean Simulation=false;
	public Algorithm Algorithm ;
	private double w;
	private double h;
	private robot robot;
	private boolean Init;
	private Line mvl;
	private Line mhl;
	private Circle TargetPoint;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		w = drawPane.getPrefWidth();
		h = drawPane.getPrefHeight();
		
		Algorithm = new Algorithm(w,h);
		init();
		loop();
		event();
}

	
	



/**************************************************/
public void init(){
	 mvl = new Line(0, 0, 0, h);
	mvl.setStrokeWidth(1);
	mvl.setStroke(Color.RED);
	 mhl = new Line(0, 0, w, 0);
	mhl.setStrokeWidth(1);
	mhl.setStroke(Color.BLUE);
	TargetPoint= new Circle(5);
	TargetPoint.setFill(Color.CADETBLUE);
	drawPane.getChildren().add(TargetPoint);
	
	
	
	Line vl = new Line(w/2, 0, w/2, h);
	vl.setStrokeWidth(0.2);
	Line hl = new Line(0, h/2, w, h/2);
	vl.setStrokeWidth(0.2);
robot =new robot(35, 45);
robot.setSpeed(0);
robot.get().setFill(Color.BROWN);
robot.get().setStroke(Color.BLACK);
robot.setTranslateX((w-35)/2);
robot.setTranslateY(h-90);
drawPane.getChildren().addAll(mhl,mvl,hl,vl,robot);
Algorithm.setRobot(robot);
Algorithm.setSpeed(0);
Static.RoboTC = RoboTC;
Static.RobotS = RobotS;
Static.RobotR = RobotR;
Static.SensorDR = SensorDR;
Static.SensorDL = SensorDL;

	}
	

/***************************************/
	private void loop() {
		
		
		
		Algorithm.Start();	
	}

/***************************************/
	
	
	
	
	
/*********************************/	
	
	private void event() {

start.setOnAction(e->{
	if(Static.timeline.getStatus()==Status.PAUSED||Static.timeline.getStatus()==Status.STOPPED)
		Static.timeline.play();  
	else Static.timeline.pause(); 
	
	});
init.setOnAction(e->{
	Init = !Init;
	
	});
drawPane.setOnMouseMoved(e->{
Cordinates.setText("X "+e.getX()+" Y"+e.getY());
mvl.setStartX(e.getX());
mvl.setEndX(e.getX());
mhl.setStartY(e.getY());
mhl.setEndY(e.getY());

});


setObstacles();
	}

	
	
	
/*************************************************************/	
public void setObstacles(){
		drawPane.setOnMouseClicked(e->{
			
if(Init){
			object obj= new  object(33,33);
	   obj.get().setX(e.getX());
	   obj.get().setY(e.getY());
	    obj.get().setFill(new Color(0.1, 0.1, 0.7, 0.5));
	    obj.setSpeed(3);
	    drawPane.getChildren().add(obj);
	    Algorithm.add(obj);
}else{
	TargetPoint.setCenterX(e.getX());
	TargetPoint.setCenterY(e.getY());
	
	Algorithm.setTarget(e.getX(),e.getY());
	
}
		});	
	}
}
