package hilsDontGetRekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import library.Hitbox;
import library.PVector2D;
import library.Vector2D;
import map.CellType;
import map.Grid;
import map.Wall;

public class Player{
	private double x;
	private double y;
	private double size;
	
	private double angle;
	private double speed;
	private boolean up=false;
	private boolean down=false;
	private boolean left=false;
	private boolean right=false;
	
	private int deathCount;


	private Hitbox hitbox;

	public Player(int x, int y, int size) {
		setDeathCount(0);
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Hitbox((int)x, (int)y, (int)size, (int)size);
	}
	//drawing my playing
	public void draw(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillOval((int)x, (int)y, (int)size, (int)size);
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
	}
	
	//when the player moves.
	public void move(Grid grid) {
		double xvel;
		double yvel;
		
		speed=4;
		if(up) {
			angle=Math.PI*1.5;
		}
		if(down) {
			angle=Math.PI/2;
		}
		if(left) {
			angle=Math.PI;
		}
		if(right) {
			angle=0;
		}
		if(up&right) {
			angle=Math.PI*1.8;
		}
		if(up&left) {
			angle=Math.PI*1.25;
		}
		if(down&left) {
			angle=Math.PI/1.33;
		}
		if(down&right) {
			angle=Math.PI/4;
		}
		if(up&down) {
			speed=0;
			angle=0;
		}
		if(right&left) {
			speed=0;
			angle=0;
		}
		if(up==false&&down==false&&right==false&&left==false){
			speed=0;
		}
		
		
		
		
		
		
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
				if(vec.getX()>0 || vec.getY()>0) {
					temp=true;
				}
			}
		}
		
		return temp;
	}
	
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
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
