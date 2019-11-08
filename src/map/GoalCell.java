package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import hilsDontGetRekt.Gui;
import hilsDontGetRekt.ImageLoader;

public class GoalCell extends Cell{

	static BufferedImage floor=ImageLoader.loadImage("floor.png");
	static BufferedImage door=ImageLoader.loadImage("door.png");
	public GoalCell(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		
		//g.setColor(Color.lightGray);
		//g.fillRect(getColumn()*size, getRow()*size, size, size);
		g.drawImage(floor, getColumn()*size, getRow()*size, size, size, null);
	
			if(Gui.grid.getKey().size()==0) {
				g.drawImage(door, getColumn()*size, getRow()*size, size, size, null);

			}
	}
	
}
