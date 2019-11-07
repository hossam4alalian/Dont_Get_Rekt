package hilsDontGetRekt;

import library.Hitbox;

public class Goal {
	
	private int x;
	private int y;
	private int size;
	private Hitbox hitbox;
	
	public Goal(int x,int y,int size) {
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Hitbox(x, y, size, size);
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	
	

}
