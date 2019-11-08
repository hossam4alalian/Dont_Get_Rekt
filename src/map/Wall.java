package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import hilsDontGetRekt.ImageLoader;

public class Wall extends Cell {
	
	static BufferedImage wall=ImageLoader.loadImage("wall.png");
	
	public Wall(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g) {
		//g.setColor(Color.black);
		//g.fillRect(getColumn()*size, getRow()*size, size, size);
		
		g.drawImage(wall,getColumn()*size, getRow()*size, size, size, null);
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
