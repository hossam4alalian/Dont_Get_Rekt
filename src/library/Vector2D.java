package library;

public class Vector2D {
	
	private double angle;
	private double magnitude;
	
	
	public Vector2D(double angle, double magnitude) {
		this.angle = angle;
		this.magnitude = magnitude;
	}
	public Vector2D(PVector2D p1,PVector2D p2) {
		createVector(p1,p2);
	}
	
	private void  createVector(PVector2D p1,PVector2D p2) {
		double dX=p2.getX()-p1.getX();
		double dY=p2.getY()-p1.getY();
		double dis=Math.sqrt((dX*dX)+(dY*dY));
		double angle=0;
		if(dis!=0) {
			angle=Math.asin(dY/dis);
		}
		if(dX<0) {
			angle=Math.PI+Math.asin(-dY/dis);
		}
		if(angle<0) {
			angle=(Math.PI*2)+angle;
		}	
		
		this.angle=angle;
		this.magnitude=dis;
	}
	
	
	
	
	
	
	
	public static double findAngle(PVector2D p1,PVector2D p2) {
		double dX=p2.getX()-p1.getX();
		double dY=p2.getY()-p1.getY();
		double dis=Math.sqrt((dX*dX)+(dY*dY));
		
		double angle=Math.asin(-dY/dis);
		if(dX<0) {
			angle=Math.PI+Math.asin(-dY/dis);
		}
		if(angle<0) {
			angle=(Math.PI*2)+angle;
		}
		
		return angle;
	}
	public static double findAngle(double sX,double sY,double eX,double eY) {
		double dX=eX-sX;
		double dY=eY-sY;
		double dis=Math.sqrt((dX*dX)+(dY*dY));
		
		double angle=Math.asin(-dY/dis);
		if(dX<0) {
			angle=Math.PI+Math.asin(-dY/dis);
		}
		if(angle<0) {
			angle=(Math.PI*2)+angle;
		}
		return angle;
	}
	
	
	
	
	public void addVector(Vector2D vector) {
		double x=vector.getX()+getX();
		double y=vector.getY()+getY();
		
		createVector(new PVector2D(0, 0), new PVector2D(x, y));
	}
	
	
	
	public double getX() {
		return Math.cos(angle)*magnitude;
	}
	public void setX(double x) {
		double dX=x;
		double dY=getY();
		double dis=Math.sqrt((dX*dX)+(dY*dY));
		double angle=0;
		if(dis!=0) {
			angle=Math.acos(dX/dis);
		}
		
		if(dY<0) {
			angle=(Math.PI*2)-Math.acos(dX/dis);
		}
		//needs to be fixed.
		
		if(angle<0) {
			angle=(Math.PI*2)+angle;
		}
		
		this.angle=angle;
		this.magnitude=dis;
	}
	
	public double getY() {
		return Math.sin(angle)*magnitude;
	}
	public void setY(double y) {
		double dX=getX();
		double dY=y;
		double dis=Math.sqrt((dX*dX)+(dY*dY));
		double angle=0;
		if(dis!=0) {
			angle=Math.asin(dY/dis);
		}
		if(dX<0) {
			angle=Math.PI+Math.asin(-dY/dis);
		}
		
		if(angle<0) {
			angle=(Math.PI*2)+angle;
		}	
		this.angle=angle;
		this.magnitude=dis;
	}
	
	
	
	
	
	
	public void printValues() {
		System.out.println("Vector: angle:"+angle+" magnitude:"+magnitude+" x:"+getX()+" y:"+getY());
	}
	
	
	
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	
	
	
	

}
