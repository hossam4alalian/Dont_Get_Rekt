package map;

import java.awt.Color;
import java.awt.Graphics2D;

public class Spawn extends Cell{
	
	public Spawn(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(getColumn()*size, getRow()*size, size, size);
	}
	
	
}
