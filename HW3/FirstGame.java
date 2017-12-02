import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 2017-2 ���α׷��� �ǽ� �߰���� (�Ǳ�)
// �й� : 
// �̸� : 

// TODO �� �˻��ϸ� ������ ���� ã�� �� �ֽ��ϴ�.
// ���� 1 : �÷��̾��� ���� ���� ���� ���� (10)
//���� 2 : ���� �̻����� �߻��ϴ� ������ �����ϴ� ���� (30)
//���� 3, 3.1 : PhysicsEngine�� CollideListener�� �����ϰ� ����ϴ� ���� (20 + 10)
//���� 4 : ���� ������ ��Ƽ�����忡�� �����ϵ��� �����ϴ� ���� (30)
// �̹����� ������ ������ ���� �ʽ��ϴ�. �� ���, �ڵ� ��ü�� �ּ��� �� �޾ƾ� �մϴ�.
// Domjudge�� ������ ��, .java ���Ͽ��� ��Ŭ�� - Properties - Text file encoding - other ���� - UTF-8 ���� ������ ���� �ѱ��� �ν��� �� �ִ� ���·� ������ �� �ֽ��ϴ�.
// ���ڵ��� �ٲ��� �ʾƵ� ������ �����ϰ� ä�������� Ȯ���� ���� ������, ���Ǹ� ���� ���� ���� ���ڵ��� �����ϱ� �ٶ��ϴ�.

// ���� ���� Ŭ����
// TODO 4 : ���� �����尡 ������ �� ������ �߻����� �ʵ��� ����
// ���� : 30
// Hint : �� ���� ������ drawList�� ���� �����尡 ������ �� �ֽ��ϴ�. 
// ���� ��� ȭ�� ǥ�� �����尡 drawList�� ��â �а� �ִµ� �ٸ� �����尡 drawList�� ��ü�� �����ϰų� �ϸ� ���ܰ� �߻��� �� �ֽ��ϴ�.
// ���� �ڵ忡���� try-catch�� ���� �� ���ܸ� ó���ϴµ�, �̸� ������ �����ؼ� ���ܰ� �߻����� �ʵ��� �����ϴ� �����Դϴ�.
class DisplayEngine extends JPanel {
	
	private int refreshInterval = 50;
	private List<GraphicObject> drawList = new ArrayList<GraphicObject>();
	
	// drawList�� ������ �ִ� ��츦 ����Ͽ�, synchronized keyword�� ����ؼ� ����ȭ�� �մϴ�.
	public synchronized void addGraphicObject(GraphicObject graphicObject) {
		drawList.add(graphicObject);
	}
	
	// drawList�� ������ �ִ� ��츦 ����Ͽ�, synchronized keyword�� ����ؼ� ����ȭ�� �մϴ�.
	public synchronized void removeGraphicObject(GraphicObject graphicObject) {
		drawList.remove(graphicObject);
	}
	
	public void clearGraphicObjects() {drawList.clear();}
	
	public DisplayEngine(KeyListener keyListener) {
		super();
		if(keyListener != null) this.addKeyListener(keyListener);
		this.requestFocus();
		setFocusable(true);
		
		class DispThread extends Thread{
			public void run(){
				while(true){
					repaint();
					try{Thread.sleep(refreshInterval);} catch(InterruptedException e){}
				}
			}
		}
		
		Thread t = new DispThread();
		t.start();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		// TODO 4 : ������ �� �浹�� ���� ���ܰ� �߻����� �ʵ��� ����
		try {
			// drawList�� ������ �ִ� ��츦 ����Ͽ�, synchronized keyword�� ����ؼ� ����ȭ�� �մϴ�.
			synchronized (this) {
				for(GraphicObject drawObj : drawList) drawObj.draw(g);
			}	
		} catch (ConcurrentModificationException e) {e.printStackTrace();}
	}
	
	static class GraphicObject {
		public int x = 0, y = 0;
		
		BufferedImage img = null;
		
		public GraphicObject(String name){
			try {
				img = ImageIO.read(new File(name));
			} catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
		
		private void draw(Graphics g){
			g.drawImage(img, x, y, null);
		}
	}
}
// ���� ���� Ŭ������ ��

// ���� ���� Ŭ����
// ������ �ʿ� �����ϴ�.
class PhysicsEngine {
	private int refreshInterval = 50;
	private List<Collidable> collideList = new ArrayList<Collidable>();
	
	public void addCollidable(Collidable graphicObject) {
		collideList.add(graphicObject);
	}
	
	public void removeCollidable(Collidable graphicObject) {
		collideList.remove(graphicObject);
	}
	
	public void clearCollidable() {collideList.clear();}
	
	public PhysicsEngine(CollideListener collideListener) {
				
		class PhysicsThread extends Thread{
			public void run(){
				while(true){
					if(collideListener != null) {
						for (int i = collideList.size()-1; i >= 0; i--) {
							for(int j = i+1; j < collideList.size(); j++) {
								if(collideList.get(i).isCollide(collideList.get(j))) collideListener.CollideOccured(collideList.get(i), collideList.get(j));
							}
						}
					}
					try{Thread.sleep(refreshInterval);} catch(InterruptedException e){}
				}
			}
		}
		
		Thread t = new PhysicsThread();
		t.start();
	}
	
	interface Collidable {
		int getX();
		int getY();
		boolean isCollide(Collidable target);
	}
	
	interface CollideListener {
		void CollideOccured(Collidable collider, Collidable target);
	}
}
// ���� ���� Ŭ������ ��

public class FirstGame extends JFrame{
	
	private static final int GAME_SIZE_X = 500;
	private static final int GAME_SIZE_Y = 500;
	
	private DisplayEngine displayEngine;
	private PhysicsEngine physicsEngine;
	
	private NPCController npcController;
	private MissileContainer missileContainer;
	private MissileLauncher missileLauncher;
	private MissileLauncher missileLauncher2;
	private Integer missiles = 0;
	
	private SpaceShip mySpaceShip;
	private List<Enemy> enemyList = new ArrayList<Enemy>();
	private List<Missile> missileList = new ArrayList<Missile>();
	
	public void launch(GameObject launcher) {
		if(launcher == null) return;
		
		Missile missile = new Missile();
		missile.x = launcher.x;
		
		if(launcher instanceof SpaceShip) {
			missile.y = launcher.y - 32;
		}
		else {
			missile.y = launcher.y + 32;
			missile.direction = Missile.DIRECTION_DOWN;
		}
		
		missileList.add(missile);
		displayEngine.addGraphicObject(missile);
		physicsEngine.addCollidable(missile);
	}
	
	public FirstGame() {
		displayEngine = new DisplayEngine(new KeyController());
		
		setTitle("First game");
		add(displayEngine);
		setSize(GAME_SIZE_X + 100, GAME_SIZE_Y + 100);
		setVisible(true);
		
		// TODO 3 : ��ü �� �浹�� ó���ϴ� �������̽� ����
		// ���� : 30
		// Hint : PhysicsEngine Ŭ������ �����ڴ� PhysicsEngine.CollideListener �������̽��� ��ü�� �Ű������� �޽��ϴ�.
		// ���� ������ �����ؼ� �� ������ �������̽��� �����ϰ� �Ʒ� �ڵ��� null ��� �Ű������� �Է��ؾ� �մϴ�.
		physicsEngine = new PhysicsEngine(new PhysicsEngine.CollideListener() {			
			//Override
			public void CollideOccured(PhysicsEngine.Collidable collider, PhysicsEngine.Collidable target) {
				// 3-1 Enemy�� Enemy�� �浹�ϴ� ��쿡�� remove ������ ���� �ʰ� �Ѿ�ϴ�.
				if(collider instanceof Enemy && target instanceof Enemy) return;
				
				if(collider instanceof GameObject) displayEngine.removeGraphicObject((GameObject) collider);
				if(target instanceof GameObject) displayEngine.removeGraphicObject((GameObject) target);
						
				missileList.remove(collider);
				missileList.remove(target);
				enemyList.remove(collider);
				enemyList.remove(target);

				physicsEngine.removeCollidable(collider);
				physicsEngine.removeCollidable(target);

				if(collider instanceof SpaceShip || target instanceof SpaceShip) mySpaceShip = null;				
			}
		});
		
		mySpaceShip = new SpaceShip();
		mySpaceShip.x = 250; mySpaceShip.y = 450;
		displayEngine.addGraphicObject(mySpaceShip);
		physicsEngine.addCollidable(mySpaceShip);
		
		for(int cnt = 0; cnt <= 500; cnt += 50) {
			Enemy enemy = new Enemy();
			enemy.x = cnt;
			enemyList.add(enemy);
			displayEngine.addGraphicObject(enemy);
			physicsEngine.addCollidable(enemy);
		}
		
		npcController = new NPCController();
		npcController.start();
		
		missileContainer = new MissileContainer();
		missileLauncher = new MissileLauncher();
		missileLauncher2 = new MissileLauncher();
		
		missileContainer.start();
		missileLauncher.start();
		missileLauncher2.start();
		
	}
	
	public static void main(String[] args) {
		new FirstGame();
	}
	
	class NPCController extends Thread {
		private int refreshInterval = 50;
		
		@Override
		public void run() {
			while(true) {
				for(int cnt = missileList.size()-1; cnt >= 0; cnt--) {
					Missile missile = missileList.get(cnt);
					missile.y += missile.direction;
					if(missile.y < 0) {
						displayEngine.removeGraphicObject(missile);
						physicsEngine.removeCollidable(missile);
						missileList.remove(missile);
					}
				}
				for(int cnt = enemyList.size()-1; cnt >= 0; cnt--) {
					Enemy enemy = enemyList.get(cnt);
					enemy.x += Math.random()*20 - 10;
					enemy.y += Math.random()*20 - 10;
					
					if(enemy.x > GAME_SIZE_X) enemy.x = GAME_SIZE_X;
					if(enemy.x < 0) enemy.x = 0;
					if(enemy.y > GAME_SIZE_Y) enemy.y = GAME_SIZE_Y;
					if(enemy.y < 0) enemy.y = 0;
				}
				try{Thread.sleep(refreshInterval);} catch(InterruptedException e){}
			}
		}
	}
	
	// TODO 2 : ���� �̻����� ����� ������ŭ�� �߻��ϵ��� ����
	// ���� : 20
	// Hint : �̻��� �߻� �󵵸� �����ϴ� ����� ������/�Һ��� ������ ���·� ����ȭ�ؼ� �����ؾ� �մϴ�.
	// �Ʒ��� MissileLauncher�� �Һ��ڰ� �Ǹ�, �� ���� ������ MissileContainer�� �����ڰ� �˴ϴ�.
	// ������ Ǯ�� �ʾҰų� ����� �������� ���� ���, ���� ������ �̻����� �� ���� ���ÿ� �߻��ϰ� �˴ϴ�.
	class MissileLauncher extends Thread {
		private int refreshInterval = 50;
		
		@Override
		public void run() {
			while(true) {
				
				int selected = (int) (Math.random() * enemyList.size());
				if(selected == enemyList.size()) selected--;
				if(selected >= 0) {
					
					Enemy enemy = enemyList.get(selected);					
					// missile ������ ������ �� ����ȭ�� �ϵ��� �մϴ�.
					synchronized(missiles) {
						if(missiles > 0) {
							launch(enemy);
							missiles--;
						}						
					}
				}	
				try{Thread.sleep(refreshInterval);} catch(InterruptedException e){}
			}
		}
	}
	
	class MissileContainer extends Thread {
		private int refreshInterval = 500;
		
		@Override
		public void run() {
			while(true) {
				// missile ������ ������ �� ����ȭ�� �ϵ��� �մϴ�.
				synchronized(missiles) {
					missiles++;
				}				
				try{Thread.sleep(refreshInterval);} catch(InterruptedException e){}
			}
		}
	}
	
	class GameObject extends DisplayEngine.GraphicObject implements PhysicsEngine.Collidable {
		public GameObject(String name) {
			super(name);
		}
		
		@Override
		public int getX() {return x;}
		
		@Override
		public int getY() {return y;}
		
		@Override
		public boolean isCollide(PhysicsEngine.Collidable target) {
			return (this.getX() - target.getX() > -16 && this.getX() - target.getX() < 16 &&
					this.getY() - target.getY() > -32 && this.getY() - target.getY() < 32 );
		}
	}
	
	class Missile extends GameObject {
		public static final int DIRECTION_UP = -20;
		public static final int DIRECTION_DOWN = 20;
		
		public int direction = DIRECTION_UP;
		
		public Missile(){
			super("missile.png");			
		}
	}

	class Enemy extends GameObject {
		public Enemy(){
			super("enemy.png");
		}	
	}

	class SpaceShip extends GameObject {
		public SpaceShip(){
			super("spaceship.png");
		}
	}
	
	class KeyController implements KeyListener {

		private static final int SPEED = 10;
		@Override
		public void keyPressed(KeyEvent event) {
			if(mySpaceShip == null) return;
			
			if(event.getKeyCode() == KeyEvent.VK_UP) mySpaceShip.y -= SPEED;
			if(event.getKeyCode() == KeyEvent.VK_DOWN) mySpaceShip.y += SPEED;
			if(event.getKeyCode() == KeyEvent.VK_LEFT) mySpaceShip.x -= SPEED;
			if(event.getKeyCode() == KeyEvent.VK_RIGHT) mySpaceShip.x += SPEED;	
			if(event.getKeyCode() == KeyEvent.VK_SPACE) launch(mySpaceShip);
			
			// TODO 1 : �÷��̾ ȭ�� ������ ����� �ʵ��� ó��
			// ���� : 10
			if (mySpaceShip.y < 0) mySpaceShip.y = 0;
			if (mySpaceShip.x < 0) mySpaceShip.x = 0;
			if (mySpaceShip.y > GAME_SIZE_Y) mySpaceShip.y = GAME_SIZE_Y;
			if (mySpaceShip.x > GAME_SIZE_X) mySpaceShip.x = GAME_SIZE_X;
		}

		@Override
		public void keyReleased(KeyEvent event) {
			
		}

		@Override
		public void keyTyped(KeyEvent event) {
			
		}
		
	}	
}