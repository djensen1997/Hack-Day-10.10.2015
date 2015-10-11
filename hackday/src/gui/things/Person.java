package gui.things;

import java.awt.Point;

public abstract class  Person extends Thing{
	private int health;
	private int shield;
	private int maxHealth;
	private int maxShield;
	public Person(int x, int y){
		super(x,y);
	}
	@Override
	public void moveLeft() {
		loc.x -= 1;
	}
	@Override
	public void moveRight() {
		loc.x += 1;
	}
	@Override
	public void moveUp() {
		loc.y -= 1;
	}
	@Override
	public void moveDown() {
		loc.y += 1;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health){
		this.health = health;
		maxHealth = health;
	}
	public void damage(double damage){
		if(shield != 0){
			health-=(int) (damage*.2);
			shield-=(int) (damage*.8);
		}else{
			health-=damage;
		}
	}
	public void setShield(int amount){
		shield = amount;
		maxShield = amount;
	}
	public int getShield(){
		return shield;
	}
	public Point getLoc(){
		Point out = new Point(loc.x * sizeX, loc.y*sizeY);
		return out;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public int getMaxShield(){
		return maxShield;
	}
	@Override
	public abstract void size(int x, int y);
}
