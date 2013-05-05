package prog.assignment.gol;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import prog.assignment.gol.GameOfLife;
import prog.assignment.gol.Logger;
import prog.assignment.gol.board.Board;

public class GameOfLifeTest {

	@BeforeClass
	public static void testSetup() {
	}

	@AfterClass
	public static void testCleanup() {
	    // Teardown for objects created
	}
	
	@Test
	public void testProcessGensWithBlock() {
		GameOfLife game = new GameOfLife();
		int xSize= 2; int ySize = 2;
		int gen = 1;
		String inputDataArray[]=  {"X X",
								   "X X"};
		String expectedDataStr =  "X X \n" +
		   						  "X X \n";
		List<String> inputDataList = Arrays.asList(inputDataArray);
		Board board = game.processGens(xSize, ySize, gen, inputDataList);
		//Logger.logLine(Logger.DEBUG,"Generation " + gen + " board");
		String result = board.display();
		//Logger.logLine(Logger.DEBUG, "Result : " + result);
		Assert.assertEquals(expectedDataStr, result);
		
	}
	
	@Test
	public void testProcessGensWithBoat() {
		GameOfLife game = new GameOfLife();
		int xSize= 3; int ySize = 3;
		int gen = 1;
		List<String> inputDataList = Arrays.asList(	"X X -",
								   	"X - X",
								   	"- X -");
		String expectedDataStr =  "X X - \n" +
				   			      "X - X \n" +
				   			      "- X - \n";
 
		Board result = game.processGens(xSize, ySize, gen, inputDataList);
		//Logger.logLine(Logger.DEBUG,"Generation " + gen + " board");
		String resultStr = result.display();
		//Logger.logLine(Logger.DEBUG, "Actual result : " + resultStr);
		Assert.assertEquals(expectedDataStr, resultStr);
		
	}
	
	@Test
	public void testProcessGensWithBlinker() {
		GameOfLife game = new GameOfLife();
		int xSize= 3; int ySize = 3;
		int gen = 1;
		List<String> inputDataList=  Arrays.asList(	"- X -",
								   	"- X -",
								   	"- X -");
		Logger.log(Logger.DEBUG, inputDataList.toString());
		String expectedDataStr =  "- - - \n" +
		                          "X X X \n" + 
		                          "- - - \n" ;
 
		Board board = game.processGens(xSize, ySize, gen, inputDataList);
		//Logger.logLine(Logger.DEBUG,"Generation " + gen + " board");
		String result = board.display();
		//Logger.logLine(Logger.DEBUG, "Actual result : " + result);
		Assert.assertEquals(expectedDataStr, result);
		
	}
	@Test
	public void testProcessGensWithToad() {
		GameOfLife game = new GameOfLife();
		int xSize= 4; int ySize = 2;
		int gen = 1;
		String inputDataArray[]=  {	"- X X X",
								   	"X X X -"};								   	
		String expectedDataStr =  "- - X - \n" +
				   				  "X - - X \n" +
				   				  "X - - X \n" +
				   				  "- X - - \n" ;
 
		List<String> inputDataList = Arrays.asList(inputDataArray);
		Board result = game.processGens(xSize, ySize, gen, inputDataList);
		//Logger.logLine(Logger.DEBUG,"Generation " + gen + " board");
		String resultStr = result.display();
		//Logger.logLine(Logger.DEBUG, "Actual result : " + resultStr);
		Assert.assertEquals(expectedDataStr, resultStr);
		
	}
	
	/**
	 * Run a test to generate 1 million generation of the Toad pattern
	 * Change @Ignore annotation to @Test to run this test
	 * Takes around 60 seconds to run.
	 */
	@Ignore
	public void testProcessGensWithToad1M() {
		GameOfLife game = new GameOfLife();
		int xSize= 4; int ySize = 2;
		int gen = 999999;
		String inputDataArray[]=  {	"- X X X",
								   	"X X X -"};								   	
		String expectedDataStr =  "- - X - \n" +
				   				  "X - - X \n" +
				   				  "X - - X \n" +
				   				  "- X - - \n" ;
 
		List<String> inputDataList = Arrays.asList(inputDataArray);
		Board result = game.processGens(xSize, ySize, gen, inputDataList);
		//Logger.logLine(Logger.DEBUG,"Generation " + gen + " board");
		String resultStr = result.display();
		//Logger.logLine(Logger.DEBUG, "Actual result : " + resultStr);
		Assert.assertEquals(expectedDataStr, resultStr);
		
	}

}
