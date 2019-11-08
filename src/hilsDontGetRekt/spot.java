package hilsDontGetRekt;

public class spot {
	
	private int column;
	private int row;
	private int parentColumn;
	private int parentRow;
	
	private float gCost;
	private float hCost;
	
	public spot(int column, int row, int parentColumn, int parentRow,float gCost,float hCost) {
		this.gCost=gCost;
		this.hCost=hCost;
		this.column = column;
		this.row = row;
		this.parentColumn = parentColumn;
		this.parentRow = parentRow;
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
	public int getParentColumn() {
		return parentColumn;
	}
	public void setParentColumn(int parentColumn) {
		this.parentColumn = parentColumn;
	}
	public int getParentRow() {
		return parentRow;
	}
	public void setParentRow(int parentRow) {
		this.parentRow = parentRow;
	}
	public float getgCost() {
		return gCost;
	}
	public void setgCost(float gCost) {
		this.gCost = gCost;
	}
	public float gethCost() {
		return hCost;
	}
	public void sethCost(float hCost) {
		this.hCost = hCost;
	}
	
	

}
