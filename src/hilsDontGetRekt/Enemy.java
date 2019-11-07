package hilsDontGetRekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import library.Hitbox;
import library.PVector2D;
import library.SamMath;
import library.Vector2D;
import map.CellType;
import map.Grid;
import map.Wall;

public class Enemy{
	private double x;
	private double y;
	private double size;
	
	private double angle;
	private double speed;
	private boolean up=false;
	private boolean down=false;
	private boolean left=false;
	private boolean right=false;

	private Hitbox hitbox;

	public Enemy(int x, int y, int size) {
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Hitbox((int)x, (int)y, (int)size, (int)size);
	}
	//drawing my playing
	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		g2.fillOval((int)x, (int)y, (int)size, (int)size);
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
	}
	
	//when the player moves.
	public void move(Grid grid, Player p) {
		double xvel;
		double yvel;
		
		angle=SamMath.angle(x, y, p.getX(), p.getY());
		
		speed=3;
		/*if(p.getY()>y) {
			angle=Math.PI*1.5;
		}
		if(p.getY()<y) {
			angle=Math.PI/2;
		}
		if(p.getX()<x ) {
			angle=Math.PI;
		}
		if(p.getX()>x) {
			angle=0;
		}*/
		
		
		yvel=speed*Math.sin(angle);
		xvel=speed*Math.cos(angle);
		
		x-=xvel;
		y+=yvel;
		
		
		//System.out.println(x+" l");
		
		boolean move=intersect2(grid);
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
	}
	
	
	
	public boolean intersect2(Grid grid) {
		
		for(int i=0;i<grid.getCells().size();i++){
			if(grid.getCells().get(i).getCellType()==CellType.WALL) {
				if(hitbox().intersects(((Wall) grid.getCells().get(i)).hitbox())){
					PVector2D wall=new PVector2D(grid.getCells().get(i).getColumn()*grid.getSize()+grid.getSize()/2, grid.getCells().get(i).getRow()*grid.getSize()+grid.getSize()/2);
					PVector2D player=new PVector2D(x+size/2, y+size/2);
					Vector2D vec=new Vector2D(wall, player);
					//System.out.println(Math.PI+Math.PI/2+Math.PI/4+"     "+Math.PI/4);
					if(vec.getAngle()<Math.PI/4 || vec.getAngle()>Math.PI+Math.PI/2+Math.PI/4) {
						//System.out.println("right");
						//speed=0;
						x=wall.getX()+grid.getSize()/2;
						//return false;
					}
					else if(vec.getAngle()>Math.PI+Math.PI/4 && vec.getAngle()<Math.PI*2-Math.PI/4) {
						//System.out.println("up");
						//speed=0;
						y=wall.getY()-grid.getSize()/2-size;
						//return false;
					}
					else if(vec.getAngle()<Math.PI+Math.PI/4 && vec.getAngle()>Math.PI/2+Math.PI/4) {
						//System.out.println("left");
						//speed=0;
						//-speed*Math.cos(angle)
						x=wall.getX()-grid.getSize()/2-size;
						
						//return false;
					}
					else if(vec.getAngle()<Math.PI-Math.PI/4 && vec.getAngle()>Math.PI/4) {
						//System.out.println("down");
						//speed=0;
						y=wall.getY()+grid.getSize()/2;
						//return false;
					}
					
					
					
				}
				
			}
		}
		
		return true;
		
	}
	
	public Rectangle hitbox() {
		return new Rectangle((int)x, (int)y, (int)size, (int)size);
	}
	
	public Rectangle hitboxUpDown() {
		return new Rectangle((int)x, (int)y, (int)size, (int)size);
	}
	public Rectangle hitboxLeftRight() {
		return new Rectangle((int)x, (int)y, (int)size, (int)size);
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
		hitbox.setX((float)x);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		hitbox.setY((float)y);
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
	
	

	

}
