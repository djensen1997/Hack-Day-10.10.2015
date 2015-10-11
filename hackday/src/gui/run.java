package gui;
import java.awt.*;

import javax.swing.*;

import utilities.*;
public class run {

	public static void main(String[] args){
		JFrame f = new JFrame("Hack Day Game");
		Container c = f.getContentPane();
		MyKeyListener m = new MyKeyListener();
		Canvas canvas = new Canvas();
		m.setCanvasRefrence(canvas);
		c.add(canvas, BorderLayout.CENTER);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setSize(screenSize);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(m);
		f.setVisible(true);
	}
	
}
