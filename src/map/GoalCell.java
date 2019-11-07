package map;

import java.awt.Color;
import java.awt.Graphics2D;

public class GoalCell extends Cell{

	public GoalCell(int column, int row, int size, CellType cellType) {
		super(column, row, size, cellType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(getColumn()*size, getRow()*size, size, size);
		
	}
	

}
