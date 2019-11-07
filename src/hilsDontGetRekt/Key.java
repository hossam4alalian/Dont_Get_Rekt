package hilsDontGetRekt;

import java.awt.Color;
import java.awt.Graphics2D;

import library.Hitbox;
import library.PVector2D;

import map.CellType;
import map.Grid;


public class Key{
	private double x;
	private double y;
	private double size;
	
	private Hitbox hitbox;

	public Key(int x, int y, int size) {
		
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Hitbox((int)x, (int)y, (int)size, (int)size);
	}
	//drawing my playing
	public void draw(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.fillOval((int)x, (int)y, (int)size, (int)size);
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
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
	
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	
	

	

}
