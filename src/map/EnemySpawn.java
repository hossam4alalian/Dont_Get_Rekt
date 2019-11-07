package map;

import java.awt.Color;
import java.awt.Graphics2D;

public class EnemySpawn extends Cell{

	public EnemySpawn(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(getColumn()*size, getRow()*size, size, size);
	}
	
	
}

