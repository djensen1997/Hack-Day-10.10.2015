package utilities;

import java.awt.event.*;
import java.util.*;
import gui.*;
public class MyKeyListener implements KeyListener{

	private TreeMap<Integer, String> keyMap = new TreeMap<>();
	private FileInput reader = new FileInput();
	private Canvas canvas;
	public MyKeyListener(){
		getKeyMap();
	}
	
	public void getKeyMap(){
		String[] input = reader.readFile("Key Map.txt");
		for(int i = 0; i < input.length; i++){
			String[] parts = input[i].split(" = ");
			keyMap.put(Integer.parseInt(parts[0]), parts[1]);
		}
		
	}
	
	public void setCanvasRefrence(Canvas canvas){
		this.canvas = canvas;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(keyMap.containsKey(e.getKeyCode())){
			canvas.doThis(keyMap.get(e.getKeyCode()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(keyMap.containsKey(e.getKeyCode())){
			canvas.stopThis(keyMap.get(e.getKeyCode()));
		}
		
	}
	

	
}
