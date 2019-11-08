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
	
	private boolean lookLeft=false;
	
	private int deathCount;


	private Hitbox hitbox;
	
	static BufferedImage playerLeftImg=ImageLoader.loadImage("playerLeft.png");
	static BufferedImage playerRightImg=ImageLoader.loadImage("playerRight.png");

	public Player(int x, int y, int size) {
		setDeathCount(0);
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Hitbox((int)x, (int)y, (int)size, (int)size);
	}
	//drawing my playing
	public void draw(Graphics2D g2) {
		
		
		if(left || lookLeft) {
			g2.drawImage(playerLeftImg,(int)x, (int)y, (int)size, (int)size, null);
			lookLeft=true;
		}
		else if(right || lookLeft==false) {
			lookLeft=false;
			g2.drawImage(playerRightImg,(int)x, (int)y, (int)size, (int)size, null);
		}
		else {
			
			g2.drawImage(playerRightImg,(int)x, (int)y, (int)size, (int)size, null);
		}
		
		
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
			lookLeft=true;
			angle=Math.PI;
		}
		if(right) {
			lookLeft=false;
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
				
				boolean stop=false;
				try {
					if(vec.getY()<0) {
					
						if(grid.getCell(grid.getCells().get(i).getColumn(), grid.getCells().get(i).getRow()-1).getCellType()==CellType.WALL) {
							stop=true;
						}
					}
					if(vec.getY()>0) {
						
						if(grid.getCell(grid.getCells().get(i).getColumn(), grid.getCells().get(i).getRow()+1).getCellType()==CellType.WALL) {
							stop=true;
						}
					}
					
				
				
				}
				
				catch (NullPointerException e) {
					// TODO: handle exception
				}
				
				
				if(!stop) {
					x+=vec.getX();
					y+=vec.getY();
					if(vec.getX()>0 || vec.getY()>0) {
						temp=true;
					}
					hitbox.setX((float)x);
					hitbox.setY((float)y);
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
	public boolean isLookLeft() {
		return lookLeft;
	}
	public void setLookLeft(boolean lookLeft) {
		this.lookLeft = lookLeft;
	}
	
	

	

}
