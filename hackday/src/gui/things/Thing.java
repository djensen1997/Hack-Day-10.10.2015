package gui.things;

import java.awt.Point;

public abstract class Thing {
	protected Point loc;
	protected int sizeX,sizeY;
	public Thing(){
		loc = new Point(0,0);
	}
	
	public Thing(int x, int y){
		loc = new Point(x,y);
	}
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
	
	public abstract void moveUp();
	
	public abstract void moveDown();
	
	public abstract Point getLoc();
	
	public abstract void size(int x, int y);
	public int getSizeX(){
		return sizeX;
	}
	public int getSizeY(){
		return sizeY;
	}
	public abstract String getName();
}
