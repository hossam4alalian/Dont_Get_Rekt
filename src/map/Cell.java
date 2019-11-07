package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import library.Hitbox;

public abstract class Cell {
	private Hitbox hitbox;
	
	private CellType cellType;
	private int column;
	private int row;
	static int size;
	public Cell(int column,int row,int size,CellType cellType) {
		this.column=column;
		this.row=row;
		this.size=size;
		this.cellType=cellType;
		
		
		hitbox=new Hitbox((int)column*size, (int)row*size, (int)size, (int)size);
	}
	
	
	public abstract void draw(Graphics2D g);
	
	
	public Rectangle hitbox() {
		return new Rectangle(column*size,row*size,size,size);
	}
	
	
	
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}


	public CellType getCellType() {
		return cellType;
	}


	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}


	public Hitbox getHitbox() {
		return hitbox;
	}


	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	
	
	

}
