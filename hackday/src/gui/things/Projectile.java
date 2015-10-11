package gui.things;

import java.awt.*;

public class Projectile extends Thing {
	Image image;
	protected int xDir, yDir, xSpeed, ySpeed;
	private String target = "";
	private int damage;
	public Projectile(int x, int y) {
		super(x, y);
	}

	@Override
	public void moveLeft() {
	}

	@Override
	public void moveRight() {
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}

	public void move() {
		loc.x += xDir * xSpeed;
		loc.y += yDir * ySpeed;
	}

	@Override
	public void size(int x, int y) {
		sizeX = x;
		sizeY = y;
	}

	public void setSpeed(int xSpeed, int ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public void setDir(int xDir, int yDir) {
		this.xDir = xDir;
		if (xDir < 0) {
			flipX(image);
		}
		this.yDir = yDir;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public Point getLoc() {
		return loc;
	}

	public boolean intersects(Thing obj) {
		if (target.equals(obj.getName())) {
			Rectangle pr = new Rectangle();
			pr.setBounds(loc.x, loc.y, sizeX, sizeY);
			Rectangle objR = new Rectangle();
			objR.setBounds(obj.getLoc().x, obj.getLoc().y, obj.sizeX, obj.sizeY);
			return pr.intersects(objR);
		}else{
			return false;
		}
	}

	public void flipX(Image image) {

	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setDamage(int damage){
		this.damage = damage;
	}
	public int getDamage(){
		return damage;
	}

}
