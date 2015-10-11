package utilities;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import gui.*;
public class MyMouseListener implements MouseListener{
	Canvas canvas;
	public void setCanvasRefrense(Canvas canvas){
		this.canvas = canvas;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		canvas.click(new Point(e.getX(), e.getY()));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
