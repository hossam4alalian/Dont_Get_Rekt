package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import hilsDontGetRekt.ImageLoader;

public class Floor extends Cell{

	
	static BufferedImage floor=ImageLoader.loadImage("floor.png");
	
	public Floor(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.lightGray);
		//g.fillRect(getColumn()*size, getRow()*size, size, size);
		
		g.drawImage(floor,getColumn()*size, getRow()*size, size, size, null);
	}

}
