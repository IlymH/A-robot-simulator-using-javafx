package application;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;


public abstract class scenaroManager {

public	List<object> obstacles = new ArrayList<>();
private double envWith;
protected Vector v;
private double envHeight;
protected application.robot robot;
protected int FPS=30;
	
	public scenaroManager(double With, double Height) {
	// TODO Auto-generated constructor stub
		this.envWith=With;
		this.envHeight=Height;
		System.out.println(envHeight);
        v = new Vector();
	
	
        
        
	         }
		
protected double getMinLeftDistance(){
	double dist=10000,Tdist=0,x = 0,y = 0;
	for(int i =0; i<obstacles.size();i++){
		  y = robot.getCenterY()-v.toCartY(robot.getRadius(), 180-50 -robot.getRotation());
			 x = robot.getCenterX()+v.toCartX(robot.getRadius(),180- 50 -robot.getRotation());
			 Tdist = v.getMag(obstacles.get(i).getCenterX()-x,
			         obstacles.get(i).getCenterY()-y);		if(Tdist<dist){
			dist= Tdist-obstacles.get(i).getRadius();
			
			
		}
		
		}
	//if(dist<2*robot.getRadius())dist=1;
	robot.distL = dist;
	return dist;
	}
protected double getMinRightDistance(){
	double dist=10000,Tdist=0,y=0,x=0;
	for(int i =0; i<obstacles.size();i++){
		 y =robot.getCenterY()-v.toCartY(robot.getRadius(), 45 -robot.getRotation());
		x = robot.getCenterX()+v.toCartX(robot.getRadius(), 45 -robot.getRotation());
Tdist = v.getMag(obstacles.get(i).getCenterX()-x,
		         obstacles.get(i).getCenterY()-y);
		if(Tdist<dist){
			dist=  Tdist-obstacles.get(i).getRadius();
			
		}
		
		}
	
	//if(dist<2*robot.getRadius())dist=1;
	robot.distR = dist;
	return dist;
	}	
	public double getSpeed(){
		return robot.getSpeed();
		}
protected  void move(){
		robot.setTranslateX(robot.getTranslateX()-v.toCartX(robot.getSpeed(),-robot.getRotation()-90));
		robot.setTranslateY(robot.getTranslateY()+v.toCartY(robot.getSpeed(),-robot.getRotation()-90));
		}	
protected  void brake(){
robot.setSpeed(0);;
}
protected void rotate(double rotation) {

	robot.rotate(rotation);
}
public double  getvX(){
	return robot.vX;
	}
public double  getvY(){
	return robot.vX;
	}
public double  getRotation(){
	return robot.getRotation();
	}

public void setSpeed(double speed){
robot.setSpeed(speed);
}

public void setRotation(double rotation){
	robot.setRotation(rotation);
	}
public void setRotation(double x, double y){
	setRotation(-(Math.atan2(robot.getCenterX() -x ,robot.getCenterY() -y))*360/(2*Math.PI));
	}

public boolean checWallX() {
	boolean bool = getMaxX(robot)>envWith || getMinX(robot)<0 ;
	//if(bool)handleWallXCollision();
	return (bool);
}
protected void handleWallYCollision() {
	// TODO Auto-generated method stub
	setRotation(180-getRotation());
	setSpeed(getSpeed()*0.867);
}
protected void handleWallXCollision() {
	// TODO Auto-generated method stub
	setRotation(-getRotation());
	setSpeed(getSpeed()*0.867);
}

protected void invet() {
	// TODO Auto-generated method stub
	robot.invet();
}

public boolean checWallY() {
	boolean bool = getMaxY(robot)>envHeight || getMinY(robot)<0 ;
	//if(bool)handleWallYCollision();
	return bool;
}
public double getMaxX(object obj){
	
 return   obj.getBoundsInParent().getMaxX();

	
}
public double getMinX(object obj){
	return  obj.getBoundsInParent().getMinX();
		}
public double getMaxY(object obj){
	return   obj.getBoundsInParent().getMaxY();
	}
public double getMinY(object obj){
	return   obj.getBoundsInParent().getMinY();
	}
public void add(object r){
	obstacles.add(r);
		}
public void setRobot(application.robot robot) {
	this.robot = robot;
	
}
protected void checCollision(){
	
	for(int i = 0;i<obstacles.size();i++){
		
		if(robot.getBoundsInParent().intersects(obstacles.get(i).getBoundsInParent())){
			
			handleCollision();
		}
		
	}
	
}
protected  void handleCollision() {
	// TODO Auto-generated method stub
	robot.blink();
	invet();
	
	setSpeed(0);
}

protected void Start(){
	
	displaysStatus();
	getMinRightDistance();
	getMinLeftDistance();
	if(checWallX())
		handleWallXCollision();;
		if(checWallY())
			handleWallYCollision();;
	checCollision();
}

private void displaysStatus()
{
	Static.RoboTC.setText("Robot Cordinates: "+"X "+(int)robot.getCenterX()+" Y "+(int)robot.getCenterY());
	Static.RobotS.setText("Robot speed: " + robot.getSpeed());
	Static.RobotR.setText("Robot rotation: "+ robot.getRotation());
	Static.SensorDR.setText("Left distance: "+ (int)robot.distR);
	Static.SensorDL.setText("Right distance: "+ (int) robot.distL);
}
protected void setTarget(double d, double e){
	robot.Xtarget=d;
	robot.Ytarget=e;
}
protected double getXTarget() {
	// TODO Auto-generated method stub
	return robot.Xtarget;
}

protected double getYTarget() {
	// TODO Auto-generated method stub
	return robot.Ytarget;
}


protected double fromTarget(){
	return fromPoint(getXTarget(),getYTarget());
}
protected Vector getVectorToObstavle(int index){
	double x = (robot.getCenterX()-obstacles.get(index).getCenterX())*(robot.getCenterX()-obstacles.get(index).getCenterX());
	double y = ((robot.getCenterY()-obstacles.get(index).getCenterY()))*((robot.getCenterY()-obstacles.get(index).getCenterY()));
	return new Vector(-10/(x), -10/(y));
}
protected double fromPoint(double xtarget2,double ytarget2) {
	// TODO Auto-generated method stub
	return v.getMag(robot.getCenterX()-getXTarget(),robot.getCenterY()-getYTarget());
}
}
