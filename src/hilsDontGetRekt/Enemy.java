package hilsDontGetRekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import library.Hitbox;
import library.PVector2D;
import library.SamMath;
import library.Vector2D;
import map.Cell;
import map.CellType;
import map.Grid;
import map.Wall;

public class Enemy{
	private double x;
	private double y;
	private double size;
	
	private double angle;
	private double speed;
	private boolean up=false;
	private boolean down=false;
	private boolean left=false;
	private boolean right=false;

	private Hitbox hitbox;

	static BufferedImage enemyImg=ImageLoader.loadImage("enemy2.png");
	
	
	
	public Enemy(int x, int y, int size) {
		this.x=x;
		this.y=y;
		this.size=size;
		hitbox=new Hitbox((int)x, (int)y, (int)size, (int)size);
		angle=0;
	}
	//drawing my playing
	public void draw(Graphics2D g2) {
		g2.drawImage(enemyImg,(int)x, (int)y, (int)size, (int)size, null);
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
		
		
		
	}
	
	//when the player moves.
	public void move(Grid grid, Player p,Graphics2D g2) {
		double xvel;
		double yvel;
		
		
		angle=SamMath.angle(x, y, p.getX(), p.getY());
		speed=2.5;
		/*if(p.getY()>y) {
			angle=Math.PI*1.5;
		}
		if(p.getY()<y) {
			angle=Math.PI/2;
		}
		if(p.getX()<x ) {
			angle=Math.PI;
		}
		if(p.getX()>x) {
			angle=0;
		}*/
		
		Vector2D vec=new Vector2D(new PVector2D(x, y), new PVector2D(p.getX(), p.getY()));
		int dis=(int)(vec.getMagnitude());
		
		if(dis<70) {
			
			
			
			yvel=speed*Math.sin(angle);
			xvel=-speed*Math.cos(angle);
			
			x+=xvel;
			y+=yvel;
		}
		else {
			AstarMove(grid, p,g2);
		}
		
		
		
		
		
		boolean move=intersect2(grid);
		
		hitbox.setX((float)x);
		hitbox.setY((float)y);
	}
	
	
	public void AstarMove(Grid grid, Player p,Graphics2D g) {
		ArrayList<spot>openList=new ArrayList<spot>();
		ArrayList<spot>closedList=new ArrayList<spot>();
		
		
		Vector2D vec=new Vector2D(new PVector2D(x, y), new PVector2D(p.getX(), p.getY()));
		int dis=(int)(vec.getMagnitude()/40);
		
		
		int colE=(int) ((x+10)/grid.getSize());
		int rowE=(int) ((y+10)/grid.getSize());
		
		openList.add(new spot(colE, rowE, colE, rowE,0,dis));
		
		
		
		while(openList.size()>0) {
			
			
			
			int index=0;
			float min=100000;
			for(int i=0;i<openList.size();i++) {
				
				
				if(openList.get(i).gethCost()+openList.get(i).getgCost()<min) {
					index=i;
					min=openList.get(i).gethCost()+openList.get(i).getgCost();
				}
			}
			spot current=openList.get(index);
			
			
			if(current.gethCost()<0.1) {
				spot temp=current;
				boolean done=false;
				int c=0;
				while(done==false) {
					
					
					boolean doneTemp=true;
					for(int i=0;i<closedList.size();i++) {
						Vector2D dis2=new Vector2D(new PVector2D(colE, rowE),new PVector2D(closedList.get(i).getColumn(), closedList.get(i).getRow()));
						if(closedList.get(i).getColumn()!=colE || closedList.get(i).getRow()!=rowE) {
							if(temp.getParentColumn()==closedList.get(i).getColumn() && temp.getParentRow()==closedList.get(i).getRow()) {
								temp=closedList.get(i);
								doneTemp=false;
								
							}
						}
					}
					
					if(doneTemp) {
						done=true;
					}
					
					//g.setColor(Color.black);
					//g.fillOval(temp.getColumn()*40, temp.getRow()*40, 40, 40);
					
				}
				
				
				
				Vector2D moveDir=new Vector2D(new PVector2D(x/40, y/40), new PVector2D(temp.getColumn(), temp.getRow()));
				
				x+=Math.cos(moveDir.getAngle())*speed;
				y+=Math.sin(moveDir.getAngle())*speed;
				
				return;
			}
			
			closedList.add(current);
			openList.remove(current);
			
			
			
			
			
				
			Cell leftCell=grid.getCell(current.getColumn()-1, current.getRow());
			
			try {
			vec=new Vector2D(new PVector2D(leftCell.getColumn(), leftCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(leftCell.getCellType()!=CellType.WALL && !isInArray(closedList, leftCell.getColumn(), leftCell.getRow())) {
				if(!isInArray(openList, leftCell.getColumn(), leftCell.getRow())){
					
					openList.add(new spot(leftCell.getColumn(), leftCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+10, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==leftCell.getColumn() && openList.get(i).getRow()==leftCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+10) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
			
			
			Cell rightCell=grid.getCell(current.getColumn()+1, current.getRow());
			
			try {
			vec=new Vector2D(new PVector2D(rightCell.getColumn(), rightCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(rightCell.getCellType()!=CellType.WALL && !isInArray(closedList, rightCell.getColumn(), rightCell.getRow())) {
				if(!isInArray(openList, rightCell.getColumn(), rightCell.getRow())){
					
					openList.add(new spot(rightCell.getColumn(), rightCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+10, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==rightCell.getColumn() && openList.get(i).getRow()==rightCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+10) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
			
			
			
			
			
			Cell upCell=grid.getCell(current.getColumn(), current.getRow()-1);
			
			
			try {
			vec=new Vector2D(new PVector2D(upCell.getColumn(), upCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(upCell.getCellType()!=CellType.WALL && !isInArray(closedList, upCell.getColumn(), upCell.getRow())) {
				if(!isInArray(openList, upCell.getColumn(), upCell.getRow())){
					
					openList.add(new spot(upCell.getColumn(), upCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+10, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==upCell.getColumn() && openList.get(i).getRow()==upCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+10) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			Cell downCell=grid.getCell(current.getColumn(), current.getRow()+1);
			
			try {
			vec=new Vector2D(new PVector2D(downCell.getColumn(), downCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(downCell.getCellType()!=CellType.WALL && !isInArray(closedList, downCell.getColumn(), downCell.getRow())) {
				if(!isInArray(openList, downCell.getColumn(), downCell.getRow())){
					
					openList.add(new spot(downCell.getColumn(), downCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+10, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==downCell.getColumn() && openList.get(i).getRow()==downCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+10) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			Cell leftupCell=grid.getCell(current.getColumn()-1, current.getRow()-1);

			try {
			vec=new Vector2D(new PVector2D(leftupCell.getColumn(), leftupCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(leftupCell.getCellType()!=CellType.WALL && upCell.getCellType()!=CellType.WALL && leftCell.getCellType()!=CellType.WALL && !isInArray(closedList, leftupCell.getColumn(), leftupCell.getRow())) {
				if(!isInArray(openList, leftupCell.getColumn(), leftupCell.getRow())){
					
					openList.add(new spot(leftupCell.getColumn(), leftupCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+14, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==leftupCell.getColumn() && openList.get(i).getRow()==leftupCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+14) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
			
			
			
			Cell leftdownCell=grid.getCell(current.getColumn()-1, current.getRow()+1);
			
			try {
			vec=new Vector2D(new PVector2D(leftdownCell.getColumn(), leftdownCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(leftdownCell.getCellType()!=CellType.WALL && downCell.getCellType()!=CellType.WALL && leftCell.getCellType()!=CellType.WALL  && !isInArray(closedList, leftdownCell.getColumn(), leftdownCell.getRow())) {
				if(!isInArray(openList, leftdownCell.getColumn(), leftdownCell.getRow())){
					
					openList.add(new spot(leftdownCell.getColumn(), leftdownCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+14, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==leftdownCell.getColumn() && openList.get(i).getRow()==leftdownCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+14) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
			
			
			
			
			
			Cell rightupCell=grid.getCell(current.getColumn()+1, current.getRow()-1);
			
			

			try {
			vec=new Vector2D(new PVector2D(rightupCell.getColumn(), rightupCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(rightupCell.getCellType()!=CellType.WALL && upCell.getCellType()!=CellType.WALL && rightCell.getCellType()!=CellType.WALL  && !isInArray(closedList, rightupCell.getColumn(), rightupCell.getRow())) {
				if(!isInArray(openList, rightupCell.getColumn(), rightupCell.getRow())){
					
					openList.add(new spot(rightupCell.getColumn(), rightupCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+14, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==rightupCell.getColumn() && openList.get(i).getRow()==rightupCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+14) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
			
			
			
			
			
			
			
			
			Cell rightdownCell=grid.getCell(current.getColumn()+1, current.getRow()+1);
			
			
			
			try {
			vec=new Vector2D(new PVector2D(rightdownCell.getColumn(), rightdownCell.getRow()), new PVector2D((p.getX()+20)/40, (p.getY()+20)/40));
			dis=(int)(vec.getMagnitude());
			
			if(rightdownCell.getCellType()!=CellType.WALL && downCell.getCellType()!=CellType.WALL && rightCell.getCellType()!=CellType.WALL  && !isInArray(closedList, rightdownCell.getColumn(), rightdownCell.getRow())) {
				if(!isInArray(openList, rightdownCell.getColumn(), rightdownCell.getRow())){
					
					openList.add(new spot(rightdownCell.getColumn(), rightdownCell.getRow(), current.getColumn(), current.getRow(), current.getgCost()+14, dis));
					
				}
				else {
					for(int i=0;i<openList.size();i++) {
						if(openList.get(i).getColumn()==rightdownCell.getColumn() && openList.get(i).getRow()==rightdownCell.getRow()) {
							float fCost=openList.get(i).getgCost()+openList.get(i).gethCost();
							
							if(openList.get(i).getgCost()>current.getgCost()+14) {
								openList.get(i).setParentColumn(current.getColumn());
								openList.get(i).setParentRow(current.getRow());
							}
							
						}
					}
				}
				
			}
			
			}
			catch (NullPointerException e) {
				// TODO: handle exception
			}
			
			
				
			
			
				
				
				
			
				
				
			
		
			
		
		}
		
		
	}
	
	public boolean isInArray(ArrayList<spot> spots ,int column,int row){
		for(int i=0;i<spots.size();i++) {
			if(spots.get(i).getColumn()==column && spots.get(i).getRow()==row) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean intersect2(Grid grid) {
		
		for(int i=0;i<grid.getCells().size();i++){
			if(grid.getCells().get(i).getCellType()==CellType.WALL) {
				if(hitbox().intersects(((Wall) grid.getCells().get(i)).hitbox())){
					PVector2D wall=new PVector2D(grid.getCells().get(i).getColumn()*grid.getSize()+grid.getSize()/2, grid.getCells().get(i).getRow()*grid.getSize()+grid.getSize()/2);
					PVector2D player=new PVector2D(x+size/2, y+size/2);
					Vector2D vec=new Vector2D(wall, player);
					//System.out.println(Math.PI+Math.PI/2+Math.PI/4+"     "+Math.PI/4);
					if(vec.getAngle()<Math.PI/4 || vec.getAngle()>Math.PI+Math.PI/2+Math.PI/4) {
						//System.out.println("right");
						//speed=0;
						x=wall.getX()+grid.getSize()/2;
						//return false;
					}
					else if(vec.getAngle()>Math.PI+Math.PI/4 && vec.getAngle()<Math.PI*2-Math.PI/4) {
						//System.out.println("up");
						//speed=0;
						y=wall.getY()-grid.getSize()/2-size;
						//return false;
					}
					else if(vec.getAngle()<Math.PI+Math.PI/4 && vec.getAngle()>Math.PI/2+Math.PI/4) {
						//System.out.println("left");
						//speed=0;
						//-speed*Math.cos(angle)
						x=wall.getX()-grid.getSize()/2-size;
						
						//return false;
					}
					else if(vec.getAngle()<Math.PI-Math.PI/4 && vec.getAngle()>Math.PI/4) {
						//System.out.println("down");
						//speed=0;
						y=wall.getY()+grid.getSize()/2;
						//return false;
					}
					
					
					
				}
				
			}
		}
		
		return true;
		
	}
	
	public Rectangle hitbox() {
		return new Rectangle((int)x, (int)y, (int)size, (int)size);
	}
	
	public Rectangle hitboxUpDown() {
		return new Rectangle((int)x, (int)y, (int)size, (int)size);
	}
	public Rectangle hitboxLeftRight() {
		return new Rectangle((int)x, (int)y, (int)size, (int)size);
	}
	
	
	

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		hitbox.setX((float)x);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		hitbox.setY((float)y);
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Hitbox getHitbox() {
		return hitbox;
	}
	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}
	
	

	

}
