package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall extends Cell {
	
	
	
	public Wall(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(getColumn()*size, getRow()*size, size, size);
	}
	
	
	/*public Rectangle boundsLeft() {
		return new Rectangle(getColumn()*size, getRow()*size, size/4, size);
	}
	public Rectangle boundsRight() {
		return new Rectangle(getColumn()*size+size-size/4, getRow()*size, size/4, size);
	}
	public Rectangle boundsUp() {
		return new Rectangle(getColumn()*size+(size/10), getRow()*size, size-(size/10)*2, size/4);
	}
	public Rectangle boundsDown() {
		return new Rectangle(getColumn()*size+(size/10), getRow()*size+size-size/4, size-(size/10)*2, size/4);
	}*/

}
