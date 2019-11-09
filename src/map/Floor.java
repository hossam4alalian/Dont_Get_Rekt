package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import hilsDontGetRekt.ImageLoader;

public class Floor extends Cell{

	
	BufferedImage floor;
	
	public Floor(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		
		if((int)(Math.random()*2)==0) {
			floor=ImageLoader.loadImage("floor.png");
		}
		else if((int)(Math.random()*6)==0) {
			floor=ImageLoader.loadImage("floor3.png");
		}
		else {
			floor=ImageLoader.loadImage("floor2.png");
		}
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.lightGray);
		//g.fillRect(getColumn()*size, getRow()*size, size, size);
		
		g.drawImage(floor,getColumn()*size, getRow()*size, size, size, null);
	}

}
