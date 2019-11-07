package library;

public class NilesMath {
	
	
	public static double getDistance(PVector2D p1,PVector2D p2) {
		Vector2D vec=new Vector2D(p1, p2);
		return vec.getMagnitude();
	}
	
	
	public static Vector2D addGravity(PVector2D p1,PVector2D p2, double g,double m1,double m2) {
		Vector2D vec=new Vector2D(p1, p2);
		double f=(g*m1*m2)/(vec.getMagnitude()*vec.getMagnitude());
		
		
		
		return new Vector2D(vec.getAngle(), f);
		
	}
	
	public static Vector2D addReaction(PVector2D p1,Vector2D vec1,double m1,PVector2D p2,Vector2D vec2,double m2) {
		Vector2D attackAngle;
		attackAngle=new Vector2D(p1, p2);
		attackAngle.setAngle(attackAngle.getAngle());
		
		
		
		
		
		attackAngle.setMagnitude(attackAngle.getMagnitude());
		
		
		//normal
		double nx=attackAngle.getX()/attackAngle.getMagnitude();
		double ny=attackAngle.getY()/attackAngle.getMagnitude();
		
		//tangent
		double tx=-ny;
		double ty=nx;
		
		double dpTan1=vec1.getX()*tx+vec1.getY()*ty;
		
		
		double dpNorm1=vec1.getX()*nx+vec1.getY()*ny;
		double dpNorm2=vec2.getX()*nx+vec2.getY()*ny;
		
		//conservation of momentum in 1d.
		double momentum=(dpNorm1*(m1-m2)+(2*m2*dpNorm2))/(m1+m2);
		//System.out.println("first "+dpNorm1+"  "+"x:"+vec1.getX()+"  "+"y:"+vec1.getY()+" nx:"+nx+" ny:"+ny);
		//System.out.println("sec "+dpNorm2+"  "+"x:"+vec2.getX()+"  "+"y:"+vec2.getY());
		
		Vector2D send=new Vector2D(new PVector2D(0, 0), new PVector2D(tx*dpTan1, ty*dpTan1));
		send.addVector(new Vector2D(new PVector2D(0, 0), new PVector2D(nx*momentum, ny*momentum)));
		
		return send;
		
	}
	
	public static PVector2D staticCollision(PVector2D p1,double size1,PVector2D p2,double size2) {
		size1/=2;
		size2/=2;
		
		Vector2D tempVec;
		tempVec=new Vector2D(p1, p2);
		
		
		double dif=tempVec.getMagnitude()-(size1+size2);
		dif/=2;
		tempVec.setMagnitude(dif-1);
		
		PVector2D send=new PVector2D(p1.getX(), p1.getY());
		send.setX(send.getX()+tempVec.getX());
		send.setY(send.getY()+tempVec.getY());
		
		
		return send;
	}
	
	
}
