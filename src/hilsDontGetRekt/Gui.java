package hilsDontGetRekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import level.LevelList;
import map.Grid;

public class Gui extends JPanel implements KeyListener {
	
	public static Grid grid;
	
	static BufferedImage skull=ImageLoader.loadImage("skull.png");
	
	static BufferedImage key=	ImageLoader.loadImage("key.png");

	public Gui() {
		setFocusable(true);
		addKeyListener(this);
	}

	BufferedImage image=new BufferedImage(816, 838, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d=(Graphics2D) image.getGraphics();
	
	boolean setup=true;
	boolean dead=false;
	boolean paused=false;
	
	boolean menu=true;
	
	private int deathCount;
	
	
	int currentLevel=0;
	LevelList levels=new LevelList();
	
	//här ritar vi gui
	public void paint(Graphics g) {
		long first = System.nanoTime() /1000000;
		//game
		
		
		if(menu) {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			g2d.setColor(Color.YELLOW);
			g2d.drawString("Don't Get Rekt. ", 100, 60);
			
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
			g2d.setColor(Color.WHITE);
			g2d.drawString("Try to escape as a ghost from death before it gets to you, ", 100, 120);
			
			g2d.drawString("remember that you have to get all the keys before escaping. ", 100, 180);
			
			g2d.drawString("When you get all the keys a door will appear and you will, ", 100, 240);
			
			g2d.drawString("be able to reach the next level, if it gets to you then you  ", 100, 300);
			
			g2d.drawString("will reset to the start of the level. ", 100, 360);
			
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			g2d.setColor(Color.YELLOW);
			g2d.drawString("Press ENTER to start ", 220, 440);
			
			g2d.setColor(Color.WHITE);
			g2d.drawString("W = UP ", 100, 520);
			
			g2d.drawString("S = DOWN ", 100, 580);
			
			g2d.drawString("D = RIGHT ", 100, 640);
			
			g2d.drawString("A = LEFT ", 100, 700);
			
			g2d.drawString("P = PAUSE ", 500, 520);
			
			g2d.drawString("R = RESTART ", 500, 580);
			
			g2d.drawString("ENTER = MENU ", 500, 640);
		}
		if(menu==false) {
			if(setup) {
				System.out.println(currentLevel);
				grid=new Grid(800, 800, 40,levels.getLevels(currentLevel));
				
				
				setup=false;
			}
			if(dead) {
				grid.getPlayer().setX(grid.getPlayerPos().getX());
				grid.getPlayer().setY(grid.getPlayerPos().getY());
				
				grid.getEnemy().setX(grid.getEnemyPos().getX());
				grid.getEnemy().setY(grid.getEnemyPos().getY());
				
				deathCount+=1;
				setup=true;
				dead=false;
			}
			grid.draw(g2d);
			
			
			enemyUpdate();
			playerUpdate();
			
			for(int i=0;i<grid.getKey().size();i++) {
				grid.getKey().get(i).draw(g2d);
			}
			
			trapUpdate();
			
			
			
			
			userInterface();
			
		}
		g.drawImage(image, 0, 0,Main.width(),Main.height(), null);
		
		//game
		long last = System.nanoTime()/1000000;
		update(first,last);
		
		repaint();
	}
	
	//alla våra objects move function
	public void playerUpdate() {
		grid.getPlayer().draw(g2d);
		grid.getPlayer().move(grid, paused==false);
		
		if(grid.getPlayer().getHitbox().intersect(grid.getEnemy().getHitbox())) {
			dead=true;
			
			
		}
		
		
		if(grid.getPlayer().getHitbox().intersect(grid.getGoal().getHitbox()) && grid.getKey().size()==0) {
			currentLevel++;
			setup=true;
			System.out.println("you win");
		}
		
		
		for(int i=0;i<grid.getKey().size();i++) {
			if(grid.getPlayer().getHitbox().intersect(grid.getKey().get(i).getHitbox())) {
				grid.getKey().remove(i);
			}
		}
		
		
		
	}
	
	
	
	public void enemyUpdate() {
		grid.getEnemy().draw(g2d);
		if(paused==false) {
			grid.getEnemy().move(grid, grid.getPlayer(),g2d);
		}
		
		
	}
	
	public void trapUpdate() {
		for(int i=0;i<grid.getTrap().size();i++) {
			grid.getTrap().get(i).draw(g2d);
		}
		if(paused==false) {
			for(int i=0;i<grid.getTrap().size();i++) {
				grid.getTrap().get(i).move(grid);
			}
			
		}
		
		for(int i=0;i<grid.getTrap().size();i++){
			
			if(grid.getTrap().get(i).getHitbox().intersect(grid.getPlayer().getHitbox())) {
				dead=true;
				
				
				
			}
		}
	}
	
	
	public void userInterface() {
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
		g2d.drawImage(skull, 0, 0,40,40, null);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString(""+deathCount, 40, 32);
		
		g2d.setColor(Color.WHITE);
		g2d.drawImage(key, 90, 0,40,40, null);

		g2d.drawString(": "+(grid.getKey().size()), 130, 32);
		
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Level: "+(currentLevel+1), 620, 32);
		
		if(paused) {
			g2d.setColor(Color.YELLOW);
			g2d.drawString("---PAUSED---", 280, 400);
		}
	}
		
	
	
	
	//vår update function
	public void update(long f,long l) {
		int fps=120;
		//setting up the fps cap.
		double cap = (1.0/fps)*1000;
		
		//finding the difference in time. cap-difference. 
		double dif=l-f;
		//if the difference is to much then don't do more. if more then error in Thread.sleep.
		if(dif>=cap){
		cap-=dif;
		}
		if(cap<=0){
			cap=1;
		}
		try {
			Thread.sleep((long) cap);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// checking fps
		if(dif<(1.0/fps)*1000) {
			cap=(1000/cap);
			//System.out.println(cap);
		}
		else if(dif>=(1.0/fps)*1000) {
			dif=(1000/dif);
			//System.out.println(dif);
		}
		
		
	}
	//hämtar mouse kordinater
	public Point Mouse() {
		PointerInfo a=MouseInfo.getPointerInfo();
		Point b=a.getLocation();
		int x=(int) b.getX();
		int y=(int) b.getY();
		x-=Main.guiLocation().getX();
		y-=Main.guiLocation().getY();
		Point MousePoint=new Point(x, y);
		
		return MousePoint;
	}

	@Override
	public void keyPressed(KeyEvent e) {
			if(!menu) {
				if(e.getKeyCode()==KeyEvent.VK_W) {
					grid.getPlayer().setUp(true);
				}
				
				if(e.getKeyCode()==KeyEvent.VK_A) {
					grid.getPlayer().setLeft(true);
				}
				
				if(e.getKeyCode()==KeyEvent.VK_D) {
					grid.getPlayer().setRight(true);
				}
				
				if(e.getKeyCode()==KeyEvent.VK_S) {
					grid.getPlayer().setDown(true);
				}
				
				if(e.getKeyCode()==KeyEvent.VK_P) {
					if(paused==false) {
						paused=true;
					}
					else if(paused) {
						paused=false;
					}
					
				}
				if(e.getKeyCode()==KeyEvent.VK_DELETE) {
					currentLevel++;
					setup=true;
				}
			
		}
			
			if(e.getKeyCode()==KeyEvent.VK_R) {
				setup=true;
				deathCount=0;
				currentLevel=0;
			}
		
		
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				if(menu) {
					menu=false;
				}
				else if(menu==false) {
					menu=true;
				}
				
			}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!menu) {
			if(e.getKeyCode()==KeyEvent.VK_W) {
				grid.getPlayer().setUp(false);
				grid.getPlayer().setSpeed(0);
			}
			
			if(e.getKeyCode()==KeyEvent.VK_A) {
				grid.getPlayer().setLeft(false);
				grid.getPlayer().setSpeed(0);
			}
			
			if(e.getKeyCode()==KeyEvent.VK_D) {
				grid.getPlayer().setRight(false);
				grid.getPlayer().setSpeed(0);
			}
			
			if(e.getKeyCode()==KeyEvent.VK_S) {
				grid.getPlayer().setDown(false);
				grid.getPlayer().setSpeed(0);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}
	

}
