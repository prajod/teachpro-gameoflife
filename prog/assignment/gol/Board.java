package prog.assignment.gol;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private Cell [][] cellMatrix;
	
	private int xSize;
	
	private int ySize;
	
	public Board(int xSize, int ySize){
		//Logger.logLine("Initializing new board of size " + xSize + "," + ySize);
		this.xSize = xSize;
		this.ySize = ySize;
		
		cellMatrix = new Cell[xSize][ySize];
		
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				cellMatrix[x][y] = new Cell(x, y);
			}
		}
		
		//add all neighbours for all cells		
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){		
				for(int i = x-1; i <= x+1; i++){
					for(int j = y-1; j <= y+1; j++){
						if(i == x && j==y) continue;
						if( i < 0 || j < 0 ||
							i >= xSize || j >= ySize) continue;
						//Logger.logLine("Neighbour of [" + x + ',' + y + ']' + '[' + i + ',' + j + ']');
						cellMatrix[x][y].addNeighbour(cellMatrix[i][j]);
					}
				}
			}
		}
	
	}
	
	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}
	
	public Cell getCell(int x, int y){
		if(x >= xSize || y >= ySize) return null;
		return cellMatrix[x][y];
	}
	
	public void initialize(List<String> dataList){
		for(int y = 0; y<ySize && y<dataList.size(); y++){
			String row = dataList.get(y);
			String [] rowElements = row.split(IConstants.SEPARATOR);
			for(int x = 0; x<xSize && x<rowElements.length; x++){
				if(rowElements[x].equals(IConstants.ALIVE_CHAR)){
					cellMatrix[x][y].setState(true);
				}
			}
		}
	}
	
	public void initializeFromPrevGen(Board board){
		//The prev generation board is 2 sizes smaller
		for(int i = 0; i < xSize-2; i++){
			for(int j = 0; j < ySize-2; j++){
				if(board.getCell(i, j).getState()){
					cellMatrix[i+1][j+1].setState(true);
				}
			}
		}
		
	}
	
	public List<String> display(){
		List<String> result = new ArrayList<String>();
		
		for(int y = 0; y < ySize; y++){
			StringBuffer line = new StringBuffer();
			for(int x = 0; x < xSize; x++){
				if(cellMatrix[x][y].isAlive()){
					line.append(IConstants.ALIVE_CHAR).append(' ');
					Logger.log(IConstants.ALIVE_CHAR + " ");
				} else {
					line.append(IConstants.DEAD_CHAR).append(' ');
					Logger.log(IConstants.DEAD_CHAR + " ");
				}
			}
			Logger.log("\n");
			result.add(line.toString());
		}
		
		return result;
	}
}
