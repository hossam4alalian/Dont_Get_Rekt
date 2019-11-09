package hilsDontGetRekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import library.Hitbox;
import library.PVector2D;
import library.Vector2D;
import map.CellType;
import map.Grid;
import map.Wall;

public class Trap{
	private double x;
	private double y;
	private double size;
	
	private double angle;
	private double speed;
	
	
	private int deathCount;


	private Hitbox hitbox;
	
	static BufferedImage trapImg=ImageLoader.loadImage("trap.png");
	

	public Trap(int x, int y, int size, double angle) {
		setDeathCount(0);
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Hitbox((int)x, (int)y, (int)size, (int)size);
		
		speed=4;
		this.angle=angle;
	}
	//drawing my playing
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.PINK);
		//g2.fillRect((int)x, (int)y, (int)size, (int)size);
		
		g2.drawImage(trapImg,(int)x, (int)y, (int)size, (int)size, null);
			
		
		
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
	}
	
	//when the player moves.
	public void move(Grid grid) {
		
		double xvel;
		double yvel;
		
		
		
		
		
		
		yvel=speed*Math.sin(angle);
		xvel=speed*Math.cos(angle);
		
		x+=xvel;
		y+=yvel;
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
		
		boolean move=intersect2(grid);
		
	}
	
	
	public boolean intersect2(Grid grid) {
		boolean temp=false;
		for(int i=0;i<grid.getCells().size();i++){
			if(grid.getCells().get(i).getCellType()==CellType.WALL) {
				PVector2D vec=grid.getCells().get(i).getHitbox().intersectFixOverlap(hitbox);
				x+=vec.getX();
				y+=vec.getY();
				if(vec.getX()!=0 || vec.getY()!=0) {
					temp=true;
					speed*=-1;
					
				}
				
				
			}
		}
		
		return temp;
	}
	
	
	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Hitbox getHitbox() {
		return hitbox;
	}
	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	public int getDeathCount() {
		return deathCount;
	}
	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}
	
	

	

}
