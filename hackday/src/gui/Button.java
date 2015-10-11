package gui;

import java.awt.*;

public class Button {
	private String name;
	private Rectangle border;
	private boolean active = false;
	public Button(String text){
		name = text;
	}
	public String getText(){
		return name;
	}
	public void setBounds(int x, int y, int width, int height){
		border = new Rectangle(x,y,width,height);
	}
	public Rectangle getRectangle(){
		return border;
	}
	public boolean containsPoint(Point p){
		return border.contains(p);
	}
	public boolean isActive(){
		return active;
	}
	public void setActive(){
		active = true;
	}
	public void setActive(boolean value){
		active = value;
	}
}
