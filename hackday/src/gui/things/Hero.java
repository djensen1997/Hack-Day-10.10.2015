package gui.things;

import java.awt.Image;
import java.util.ArrayList;

public class Hero extends Person{
	Image projectileImage;
	private final static String name = "hero";
	private int startX;
	private int startY;
	private int score,cash;
	private boolean scatterShotEnabled = false;
	public Hero(int x, int y){
		super(x,y);
		startX = x;
		startY = y;
	}
	@Override
	public void size(int x, int y) {
		sizeX = x;
		sizeY = y;
		
	}
	public void resetPos(){
		loc.x = startX;
		loc.y = startY;
	}
	public Projectile shoot(){
		Projectile output = new Projectile(getLoc().x + sizeX, getLoc().y + sizeY/2);
		output.size(30, 10);
		output.setImage(projectileImage);
		output.setSpeed(10, 0);
		output.setDir(1, 0);
		output.setTarget("enemy");
		return output;
	}
	
	public ArrayList<Projectile> scatterShot(){
		ArrayList<Projectile> output = new ArrayList<>();
		for(int i = 0; i < 3; i++){
			output.add(new Projectile(getLoc().x + sizeX, getLoc().y + sizeY/2));
			output.get(i).size(30,10);
			output.get(i).setImage(projectileImage);
			output.get(i).setSpeed(10, 2 - (i*2));
			output.get(i).setTarget("enemy");
			output.get(i).setDir(1, 1);
		}
		return output;
	}
	public void changeProjectileImage(Image image){
		projectileImage = image;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void addToScore(int amount){
		score+=amount;
	}
	
	public int getScore(){
		return score;
	}
	
	public void addCash(int amount){
		cash+=amount;
	}
	
	public int getCash(){
		return cash;
	}
	
	public void spentCash(int amount){
		cash-=amount;
	}
	public boolean isScatterShotEnabled(){
		return scatterShotEnabled;
	}
	public void enableScaterShot(){
		scatterShotEnabled = true;
	}
}
