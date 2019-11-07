package hilsDontGetRekt;

import java.awt.Color;
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

import map.Grid;

public class Gui extends JPanel implements KeyListener {
	
	
	
	
	Grid grid;
	
	public Gui() {
		setFocusable(true);
		addKeyListener(this);
	}

	BufferedImage image=new BufferedImage(1600, 1000, BufferedImage.TYPE_INT_RGB);
	Graphics2D g2d=(Graphics2D) image.getGraphics();
	
	boolean setup=true;
	boolean dead=false;
	
	//här ritar vi gui
	public void paint(Graphics g) {
		long first = System.nanoTime() /1000000;
		//game
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		
		if(setup) {
			grid=new Grid(800, 800, 40);
			//player=new Player((int)grid.getPlayerPos().getX(),(int) grid.getPlayerPos().getY(), 30);
			
			//enemy=new Enemy((int)grid.getEnemyPos().getX(),(int) grid.getEnemyPos().getY(), 30);
			
			//key=new Key((int)grid.getKeyPos().getX(),(int) grid.getKeyPos().getY(), 30);
			
			//goal=new Goal((int)grid.getGoalPos().getX(), (int)grid.getGoalPos().getY(), 30, 0);
			
			setup=false;
		}
		if(dead) {
			grid.getPlayer().setX(grid.getPlayerPos().getX());
			grid.getPlayer().setY(grid.getPlayerPos().getY());
			
			grid.getEnemy().setX(grid.getEnemyPos().getX());
			grid.getEnemy().setY(grid.getEnemyPos().getY());
			
			//key.setX(grid.getKeyPos().getX());
			//key.setY(grid.getKeyPos().getY());
			
			dead=false;
		}
		grid.draw(g2d);
		
		enemyUpdate();
		playerUpdate();
		
		for(int i=0;i<grid.getKey().size();i++) {
			grid.getKey().get(i).draw(g2d);
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
		grid.getPlayer().move(grid);
		
		if(grid.getPlayer().getHitbox().intersect(grid.getEnemy().getHitbox())) {
			dead=true;
			grid.getPlayer().setDeathCount(grid.getPlayer().getDeathCount()+1);
			System.out.println(grid.getPlayer().getDeathCount());
		}
		
		
		if(grid.getPlayer().getHitbox().intersect(grid.getGoal().getHitbox()) && grid.getKey().size()==0) {
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
		grid.getEnemy().move(grid, grid.getPlayer());
		
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
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
