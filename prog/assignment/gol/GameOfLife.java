package prog.assignment.gol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameOfLife {
	
	public static void main(String[] args){
		if(args.length < 3){
			printUsage();
			return;
		}
		
		try {
			int xSize = Integer.parseInt(args[0]);
			int ySize = Integer.parseInt(args[1]);
			String fileName = args[2];
			int gen = 1;
			if(args.length > 3) gen = Integer.parseInt(args[3]);
			
			BufferedReader inputReader = new BufferedReader(new FileReader(fileName));
			List<String> inputDataList = new ArrayList<String>();
			
			String line = inputReader.readLine();
			while(line != null){
				inputDataList.add(line);
				line = inputReader.readLine();
			}
			
			inputReader.close();
			
			Logger.log(inputDataList.toArray().toString());
			GameOfLife game = new GameOfLife();
			Board result = game.start(xSize, ySize, gen, inputDataList);
			Logger.logLine("Generation " + gen + " board");
			result.display();
		
		} catch (NumberFormatException e){
			Logger.logLine("Incorrect inputs");
			printUsage();
		} catch (IOException e) {
			Logger.logLine("Unable to read file");
			printUsage();
		} 
	
	}
	
	public Board start(int xSize, int ySize, int noOfGen, List<String> input){
		Logger.logLine("Inputs - x:" + xSize + " y: " + ySize + " No. of Gen:" + noOfGen);
		Board board = new Board(xSize, ySize);
		board.initialize(input);
		
		Logger.logLine("Initial board : ");
		board.display();
		
		List<Point> modifiedCells = new LinkedList<Point>();
		Board newBoard = null;
		
		for(int i = 0; i < noOfGen; i++){
			//Increase board size by one row on all sides
			xSize += 2;
			ySize += 2;
			newBoard = new Board(xSize, ySize);
			newBoard.initializeFromPrevGen(board);
			
			Logger.logLine("Generation " + i + " board");
			//newBoard.display();
			
			for(int x = 0; x < xSize; x++){
				for(int y = 0; y < ySize; y++){
					Cell cell = newBoard.getCell(x, y);
					int nCount = cell.getNeighbourCount();
					//Logger.logLine("No. of neighbours of [" + x + "," + y + "] : " + nCount);
					
					//No change for n=2 or n=3 and alive
					if(nCount == 2 || 
					   (nCount == 3 && cell.isAlive())) continue;
					
					//Alive if n=3 
					if(nCount == 3 && cell.isDead()) {
						modifiedCells.add(cell.getPosition());
						continue;
					}
					
					//All others should be dead
					if(cell.isAlive()) modifiedCells.add(cell.getPosition());
				}
			}
			
			Logger.logLine("Modified cells : " + modifiedCells.toString());
			for(Point mPosition : modifiedCells){
				Cell mCell = newBoard.getCell(mPosition.x, mPosition.y);
				mCell.switchState();
			}
			
			modifiedCells.clear();
			board = newBoard;
		}
		
		return newBoard;
	}
	

	
	public static void printUsage(){
		Logger.logLine("Usage : ");
	}
	


}
