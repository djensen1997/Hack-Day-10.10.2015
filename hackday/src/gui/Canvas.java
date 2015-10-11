package gui;

import java.awt.*;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

import gui.things.*;
import utilities.MyMouseListener;

//TODO upgrades
//TODO feed cats, not trolls
public class Canvas extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gridWidth = 50;
	private int gridHeight = 50;
	private Image heroImage, burritoImage, bulletImage, trollImage, catImage, hairBallImage;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<ArrayList<Enemy>> enemies = new ArrayList<>();
	private Hero hero = new Hero(0, 5);
	private int maxX;
	private int maxY;
	private int level = 0;
	private int frameCount = 0;
	private int framesToMoveEnemies = 100;
	private boolean gameOver = false;
	private boolean shoot = false;
	private int shotCount = 0;
	private int shotsToShoot = 25;
	private boolean upgrade = false;
	private Font title, reg;
	private Button[] buttons = new Button[4];
	private MyMouseListener myMouseListener = new MyMouseListener();

	public Canvas() {
		File heroImageFile = new File("Images/burger.png");
		File burritoImageFile = new File("Images/burrito.png");
		File bulletImageFile = new File("Images/lolo.png");
		File trollImageFile = new File("Images/troll.png");
		File catImageFile = new File("Images/cat.png");
		File hairBallImageFile = new File("Images/hair ball.png");
		try {
			heroImage = ImageIO.read(heroImageFile);
			burritoImage = ImageIO.read(burritoImageFile);
			bulletImage = ImageIO.read(bulletImageFile);
			trollImage = ImageIO.read(trollImageFile);
			catImage = ImageIO.read(catImageFile);
			hairBallImage = ImageIO.read(hairBallImageFile);
		} catch (Exception e) {
			e.printStackTrace(); // Print error
		}
		hero.changeProjectileImage(burritoImage);
		hero.size(gridWidth, gridHeight);

		Enemy.setProjectileImage(bulletImage);
		Enemy.setImage(trollImage);
		title = new Font("Impact", Font.BOLD, 40);
		reg = new Font("Impact", Font.PLAIN, 10);
		myMouseListener.setCanvasRefrense(this);
		addMouseListener(myMouseListener);
		buttons[0] = new Button("furry");
		buttons[1] = new Button("scatter");
		buttons[2] = new Button("continue");
		buttons[3] = new Button("always continue");

	}

	public void paint(Graphics g) {
		if (!upgrade) {
			if (enemies.size() == 0) {
				nextLevel();
			}
			if (shoot) {
				if (shotCount == shotsToShoot) {
					if (hero.isScatterShotEnabled()) {
						projectiles.addAll(0, hero.scatterShot());
					} else {
						projectiles.add(hero.shoot());
					}
					shotCount = 0;
				} else {
					shotCount += 1;
				}
			} else {
				shotCount = shotsToShoot;
			}
			g.drawImage(heroImage, hero.getLoc().x, hero.getLoc().y, null);
			g.setColor(Color.black);
			g.drawString("Health", 0, 15);
			if (((double) hero.getHealth() / (double) hero.getMaxHealth()) <= .35) {
				g.setColor(Color.red);
			}
			if (((double) hero.getHealth() / (double) hero.getMaxHealth()) > .35
					&& ((double) hero.getHealth() / (double) hero.getMaxHealth()) < .75) {
				g.setColor(Color.yellow);
			}
			if (((double) hero.getHealth() / (double) hero.getMaxHealth()) >= .75) {
				g.setColor(Color.green);
			}
			g.fillRoundRect(50, 0, (int) ((double) hero.getHealth() / (double) hero.getMaxHealth() * 100), 15, 15, 15);
			g.setColor(Color.black);
			g.drawString("Shield: ", 0, 45);
			g.setColor(Color.cyan);
			g.fillRoundRect(50, 30, (int) ((double) hero.getShield() / (double) hero.getMaxShield() * 100), 15, 15, 15);
			g.setColor(Color.BLACK);
			g.drawString("Score: " + hero.getScore(), 0, 60);
			g.drawString("Cash: " + hero.getCash(), 0, 75);
			for (int a = 0; a < enemies.size(); a += 1) {
				for (int i = 0; i < enemies.get(a).size(); i++) {
					if (frameCount == framesToMoveEnemies) {
						enemies.get(a).get(i).moveLeft();
					}
					g.drawImage(enemies.get(a).get(i).getImage(), enemies.get(a).get(i).getLoc().x,
							enemies.get(a).get(i).getLoc().y, enemies.get(a).get(i).getSizeX(),
							enemies.get(a).get(i).getSizeY(), null);
					Projectile temp;
					if (((temp = enemies.get(a).get(i).shoot()) != null)
							&& enemies.get(a).get(i).getLoc().x < getWidth()) {
						projectiles.add(temp);
					} else {

					}
				}
			}
			if (frameCount == framesToMoveEnemies) {
				frameCount = 0;
			}
			for (int i = 0; i < projectiles.size(); i++) {
				projectiles.get(i).move();
				if (projectiles.get(i).getLoc().x > getWidth() || projectiles.get(i).getLoc().y > getHeight()
						|| projectiles.get(i).getLoc().x < 0 || projectiles.get(i).getLoc().y < 0) {
					projectiles.remove(i);
					break;
				}
				g.drawImage(projectiles.get(i).getImage(), projectiles.get(i).getLoc().x, projectiles.get(i).getLoc().y,
						projectiles.get(i).getSizeX(), projectiles.get(i).getSizeY(), null);
				boolean stop = false;
				for (int a = 0; a < enemies.size(); a++) {
					for (int b = 0; b < enemies.get(a).size(); b++) {
						if (projectiles.get(i).intersects(enemies.get(a).get(b))) {
							enemies.get(a).remove(b);
							projectiles.remove(i);
							if (enemies.get(a).size() == 0) {
								enemies.remove(a);
							}
							hero.addToScore(100 + level * 50);
							hero.addCash(4 + level * 5);
							stop = true;
							break;
						}

					}
					if (stop) {
						break;
					}
				}
				if (stop) {
					break;
				}
				if (projectiles.get(i).intersects(hero)) {
					hero.damage(projectiles.get(i).getDamage());
					if (hero.getShield() > 0) {
						g.setColor(Color.blue);
					} else {
						g.setColor(Color.red);
					}
					for (int a = 0; a < 5; a++) {
						g.drawOval(hero.getLoc().x + a, hero.getLoc().y, gridWidth, gridHeight);
					}
					projectiles.remove(i);
					if (hero.getHealth() == 0) {
						gameOver = true;
					}
				}

			}
			if (!gameOver) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
				frameCount += 1;
				repaint();
			} else {
				g.setFont(title);
				g.setColor(Color.BLACK);
				g.drawString("Game Over", getWidth() / 2, getHeight() / 2);
			}
		} else {
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].setBounds(getWidth() * 1 / 3, 60 + i * 30, getWidth() * 1 / 3, 20);
			}
			g.setFont(title);
			g.setColor(Color.black);
			g.drawRect(getWidth() * 1 / 3, 0, getWidth() * 1 / 3, getHeight());
			int xloc = getWidth() * 1 / 3;
			g.drawString("Upgrade Your Stuff!", xloc + 80, 45);
			g.setFont(reg);
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].isActive()) {
					g.setColor(Color.red);
				} else {
					g.setColor(Color.white);
				}
				g.fillRect(buttons[i].getRectangle().x, buttons[i].getRectangle().y, buttons[i].getRectangle().width,
						buttons[i].getRectangle().height);
				g.setColor(Color.black);
				g.drawString(buttons[i].getText(), xloc + 80, buttons[i].getRectangle().y + 15);
			}
		}
	}

	public void doThis(String action) {
		if (action.equals("up") && !(hero.getLoc().y <= 0)) {
			hero.moveUp();
		}
		if (action.equals("down") && !(hero.getLoc().y >= getHeight())) {
			hero.moveDown();
		}
		if (action.equals("left") && !(hero.getLoc().x <= 0)) {
			hero.moveLeft();
		}
		if (action.equals("right") && !(hero.getLoc().x >= getWidth())) {
			hero.moveRight();
		}
		if (action.equals("hit")) {
			shoot = true;
		}
		if (action.equals("furry")) {
			enableFurry();
			repaint();
		}
		if (action.equals("scatter")) {
			hero.enableScaterShot();
			repaint();
		}
		if (action.equals("continue")) {
			upgrade = false;
			newLevel();
			buttons[2].setActive(false);
			repaint();
		}
		if (action.equals("always continue")) {
			upgrade = false;
			newLevel();
			repaint();
		}
	}

	public void newLevel() {
		maxX = getWidth() / gridWidth;
		maxY = getHeight() / gridHeight;
		System.out.println(level);
		for (int a = 0; a < level; a++) {
			ArrayList<Enemy> temp = new ArrayList<Enemy>();
			for (int i = 0; i < maxY - 2; i++) {
				temp.add(new Enemy(maxX - 1 + a, i));
				temp.get(i).size(gridWidth, gridHeight);
			}
			enemies.add(temp);
		}

	}

	public void nextLevel() {
		level += 1;
		projectiles.clear();
		hero.setHealth(100);
		hero.setShield(100);
		hero.resetPos();
		if (!buttons[3].isActive()) {
			upgrade = true;
		}else{
			newLevel();
		}
	}

	public void stopThis(String action) {
		if (action.equals("hit")) {
			shoot = false;
		}
	}

	public void click(Point p) {
		if (upgrade) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].containsPoint(p)) {
					buttons[i].setActive();
					doThis(buttons[i].getText());
				}
			}
		} else {

		}
	}

	private void enableFurry() {
		Enemy.setImage(catImage);
		Enemy.setProjectileImage(hairBallImage);
	}

}
