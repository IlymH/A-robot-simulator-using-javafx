package application;

public class Vector {
	
	
	public double x;
	public double y;
	public double angle;
	public Vector(){
		
	}
public Vector(double x, double y){
	this.x = x;	
	this.y = y;	
	
	}
public double getAngle(Vector v1, Vector v2) {
	
	
	
	
	return 0;
}
	public double toCartX(double magnitude, double angle) {
		return magnitude*Math.cos(angle/360*2*Math.PI);
	}
	public double toCartY(double magnitude, double angle) {
		
		return magnitude*Math.sin(angle/360*2*Math.PI);
		}
	public   double getDist(double x1,double y1,double x2,double y2){
		return 0;
	}
	
	public   double getMag(double x,double y){
		return Math.sqrt(x*x+y*y);
	}
	public   void  add(Vector v){
		this.x = v.x;
		this.y = v.y;
	}
	
@Override
public String toString() {
	// TODO Auto-generated method stub

	return new String(x+"  "+y);
}
}
