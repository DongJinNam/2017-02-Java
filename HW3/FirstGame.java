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

// 2017-2 프로그래밍 실습 중간고사 (실기)
// 학번 : 
// 이름 : 

// TODO 를 검색하면 문제를 쉽게 찾을 수 있습니다.
// 문제 1 : 플레이어의 조작 범위 제한 문제 (10)
//문제 2 : 적이 미사일을 발사하는 개수를 제어하는 문제 (30)
//문제 3, 3.1 : PhysicsEngine의 CollideListener를 구현하고 사용하는 문제 (20 + 10)
//문제 4 : 영상 엔진을 멀티스레드에도 안전하도록 수정하는 문제 (30)
// 이번에는 보고서를 별도로 받지 않습니다. 그 대신, 코드 자체에 주석을 잘 달아야 합니다.
// Domjudge에 제출할 때, .java 파일에서 우클릭 - Properties - Text file encoding - other 선택 - UTF-8 선택 과정을 통해 한글을 인식할 수 있는 형태로 제출할 수 있습니다.
// 인코딩을 바꾸지 않아도 제출은 가능하고 채점에서도 확인할 수는 있지만, 편의를 위해 위와 같이 인코디을 수정하기 바랍니다.

// 영상 엔진 클래스
// TODO 4 : 여러 스레드가 접근할 때 에러가 발생하지 않도록 수정
// 배점 : 30
// Hint : 이 영상 엔진은 drawList에 여러 스레드가 접근할 수 있습니다. 
// 예를 들어 화면 표시 스레드가 drawList를 한창 읽고 있는데 다른 스레드가 drawList의 객체를 제거하거나 하면 예외가 발생할 수 있습니다.
// 기존 코드에서는 try-catch를 통해 이 예외를 처리하는데, 이를 적절히 수정해서 예외가 발생하지 않도록 수정하는 문제입니다.
class DisplayEngine extends JPanel {
	
	private int refreshInterval = 50;
	private List<GraphicObject> drawList = new ArrayList<GraphicObject>();
	
	// drawList에 변경이 있는 경우를 대비하여, synchronized keyword를 사용해서 동기화를 합니다.
	public synchronized void addGraphicObject(GraphicObject graphicObject) {
		drawList.add(graphicObject);
	}
	
	// drawList에 변경이 있는 경우를 대비하여, synchronized keyword를 사용해서 동기화를 합니다.
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
		// TODO 4 : 스레드 간 충돌로 인한 예외가 발생하지 않도록 수정
		try {
			// drawList에 변경이 있는 경우를 대비하여, synchronized keyword를 사용해서 동기화를 합니다.
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
// 영상 엔진 클래스의 끝

// 물리 엔진 클래스
// 수정할 필요 없습니다.
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
// 물리 엔진 클래스의 끝

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
		
		// TODO 3 : 물체 간 충돌을 처리하는 인터페이스 구현
		// 배점 : 30
		// Hint : PhysicsEngine 클래스의 생성자는 PhysicsEngine.CollideListener 인터페이스의 객체를 매개변수로 받습니다.
		// 문제 문서를 참고해서 이 리스너 인터페이스를 구현하고 아래 코드의 null 대신 매개변수로 입력해야 합니다.
		physicsEngine = new PhysicsEngine(new PhysicsEngine.CollideListener() {			
			//Override
			public void CollideOccured(PhysicsEngine.Collidable collider, PhysicsEngine.Collidable target) {
				// 3-1 Enemy와 Enemy가 충돌하는 경우에는 remove 연산을 하지 않고 넘어갑니다.
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
	
	// TODO 2 : 적이 미사일을 생산된 수량만큼만 발사하도록 수정
	// 배점 : 20
	// Hint : 미사일 발사 빈도를 조절하는 방법을 생산자/소비자 문제의 형태로 동기화해서 구현해야 합니다.
	// 아래의 MissileLauncher가 소비자가 되며, 그 직후 나오는 MissileContainer가 생산자가 됩니다.
	// 문제를 풀지 않았거나 제대로 구현되지 않은 경우, 가끔 적들이 미사일을 두 발을 동시에 발사하게 됩니다.
	class MissileLauncher extends Thread {
		private int refreshInterval = 50;
		
		@Override
		public void run() {
			while(true) {
				
				int selected = (int) (Math.random() * enemyList.size());
				if(selected == enemyList.size()) selected--;
				if(selected >= 0) {
					
					Enemy enemy = enemyList.get(selected);					
					// missile 변수에 접근할 때 동기화를 하도록 합니다.
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
				// missile 변수에 접근할 때 동기화를 하도록 합니다.
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
			
			// TODO 1 : 플레이어가 화면 밖으로 벗어나지 않도록 처리
			// 배점 : 10
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