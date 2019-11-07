package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import hilsDontGetRekt.Enemy;
import hilsDontGetRekt.Goal;
import hilsDontGetRekt.ImageLoader;
import hilsDontGetRekt.Key;
import hilsDontGetRekt.Player;
import library.PVector2D;

public class Grid {
	
	private int width;
	private int height;
	private int size;
	private ArrayList<Cell>cells=new ArrayList<Cell>();
	
	
	
	private BufferedImage map=ImageLoader.loadImage("map1.png");
	
	
	private Player player;
	private PVector2D playerPos;
	
	private Enemy enemy;
	private PVector2D enemyPos;

	private Goal goal;
	private PVector2D goalPos;
	
	private ArrayList<Key> key= new ArrayList<Key>();
	
	
	private PVector2D keyPos;
	
	public Grid(int width,int height, int size) {
		this.width=width;
		this.height=height;
		this.size=size;
		

		for(int i=0;i<width/size;i++) {
			for(int ii=0;ii<height/size;ii++) {
				
				Color color=new Color(map.getRGB(i, ii));
				if(color.getRGB()==new Color(0, 0, 0).getRGB()) {
					cells.add(new Wall(i, ii,size,CellType.WALL));
				}
				if(color.getRGB()==new Color(255, 255, 255).getRGB()) {
					cells.add(new Floor(i, ii,size,CellType.FLOOR));
				}
				if(color.getRGB()==new Color(0, 255, 0).getRGB()) {
					cells.add(new GoalCell(i, ii,size,CellType.GOAL));
					goal=new Goal(i*size, ii*size, size);
					setGoalPos(new PVector2D(i*size, ii*size) );
				}
				if(color.getRGB()==new Color(255, 0, 0).getRGB()) {
					cells.add(new Floor(i, ii,size,CellType.FLOOR));
					setPlayerPos(new PVector2D(i*size, ii*size));
					player=new Player(i*size,ii*size, 30);
					
				}
				if(color.getRGB()==new Color(0, 0, 255).getRGB()) {
					cells.add(new EnemySpawn(i, ii,size,CellType.ENEMYSPAWN));
					setEnemyPos(new PVector2D(i*size, ii*size));
					enemy=new Enemy(i*size, ii*size, 30);
				}
				
				if(color.getRGB()==new Color(255, 255, 0).getRGB()) {
					cells.add(new Floor(i, ii,size,CellType.FLOOR));
					setKeyPos(new PVector2D(i*size, ii*size));
					key.add(new Key(i*size, ii*size, size));
				}
			}
		}
		
		
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<cells.size();i++) {
			cells.get(i).draw(g);
		}
	}
	
	
	
	
	
	
	
	

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public PVector2D getEnemyPos() {
		return enemyPos;
	}

	public void setEnemyPos(PVector2D enemyPos) {
		this.enemyPos = enemyPos;
	}

	public PVector2D getKeyPos() {
		return keyPos;
	}

	public void setKeyPos(PVector2D keyPos) {
		this.keyPos = keyPos;
	}

	public PVector2D getGoalPos() {
		return goalPos;
	}

	public void setGoalPos(PVector2D goalPos) {
		this.goalPos = goalPos;
	}

	
	
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public PVector2D getPlayerPos() {
		return playerPos;
	}

	public void setPlayerPos(PVector2D playerPos) {
		this.playerPos = playerPos;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public ArrayList<Key> getKey() {
		return key;
	}

	public void setKey(ArrayList<Key> key) {
		this.key = key;
	}
	
	
	
	
	
	
	
	
	
}
	
