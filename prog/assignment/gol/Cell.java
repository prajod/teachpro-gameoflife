package prog.assignment.gol;

import java.util.LinkedList;
import java.util.List;

public class Cell {
	
	private Point position;
	private boolean state = false;
	
	private List<Cell> neighbours = new LinkedList<Cell>();
	
	public Cell(int x, int y){
		position = new Point(x, y);
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setState(boolean state){
		this.state = state;
	}
	
	public void switchState(){
		if (state == false) state = true;
		else if (state == true) state = false;
	}
	
	public boolean getState(){
		return state;
	}
	
	public boolean isAlive(){
		return state;
	}
	
	public boolean isDead(){
		return !state;
	}
	
	public void addNeighbour(Cell cell){
		neighbours.add(cell);
	}
	
	public int getNeighbourCount(){
		int nCount = 0;
		for(Cell neighbour: neighbours){
			if(neighbour.getState()){
				nCount++;
			}
		}
		return nCount;
	}
	
}
