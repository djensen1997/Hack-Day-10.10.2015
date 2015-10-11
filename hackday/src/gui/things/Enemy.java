package gui.things;

import java.awt.*;

public class Enemy extends Person{
	private static Image projectileImage;
	private static Image image;
	private final static String name = "enemy";
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	public static void setImage(Image image){
		Enemy.image = image;
	}
	public Image getImage(){
		return image;
	}
	public void size(int x, int y) {
		sizeX = x;
		sizeY = y;
	}
	public static void setProjectileImage(Image image){
		projectileImage = image;
	}
	public Projectile shoot(){
		int rng =(int)( Math.random() * 199 + 1);
		if(rng == 2){
			Projectile out = new Projectile(getLoc().x - 10,getLoc().y+ sizeY/2);
			out.size(30, 10);
			out.setImage(projectileImage);
			out.setDir(-1, 0);
			out.setSpeed(10, 0);
			out.setTarget("hero");
			out.setDamage(5);
			return out;
		}else{
			return null;
		}
	}
	public void feed(){
		
	}
	public String getName(){
		return name;
	}
}
