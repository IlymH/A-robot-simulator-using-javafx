package application;

import java.io.ObjectInputStream.GetField;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class Algorithm extends scenaroManager {

	

	private Vector forceVector;


	public Algorithm(double With, double Height) {
		super(With, Height);
		 forceVector = new Vector(0,0);
		
	}
	
	public void Start(){
setSpeed(5);

		Static.timeline = new Timeline(new KeyFrame(Duration.millis(1000/FPS), e->{
					
		
	test2();
			
		
		
			super.Start();
		})) ;
		Static.timeline.setCycleCount(-1);
}

	
private void test2() {
		// TODO Auto-generated method stub
	//if(checWallY())
	
	repForce();
	System.out.println(" rep : " +forceVector);
	
	forceVector.add(atForce());
	
setRotation2(forceVector.x, forceVector.y);		
	    move();
	}

private void setRotation2(double x, double y) {
	// TODO Auto-generated method stub
	setRotation(-(Math.atan2(x ,y))*360/(2*Math.PI));
}

private void test(){
	
setSpeed(fromTarget());

move();

}


private void repForce(){
	forceVector = new Vector(0,0);
	for(int i=0;i<obstacles.size();i++){
		
		forceVector.add(getVectorToObstavle(i));
		
	
	}
	
}
private Vector atForce(){
	return new Vector(100000/((robot.getCenterX()-getXTarget())),
			100000/((robot.getCenterY()-getYTarget())));
	
}
	

		

	


}
