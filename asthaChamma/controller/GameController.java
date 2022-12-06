package asthaChamma.controller;

import java.util.ArrayList;
import asthaChamma.model.Board;
import asthaChamma.model.Dice;
import asthaChamma.model.FileReader;
import asthaChamma.view.ProjectUI;


public class GameController
{
	protected Board board;
	protected Dice dice;
	protected FileReader fileReader;
	protected int nextPlayer = 0;
	int[][] pathofplayer = {{3,1},{4,1},{5,1},{5,2},{5,3},{5,4},{5,5},{4,5}, 
			{3,5},{2,5},{1,5},{1,4},{1,3},{1,2},{1,1},{2,1},{3,1},{4,1},{5,1},{5,2},{5,3},{5,4},{5,5},{4,5}, 
			{3,5},{2,5},{1,5},{1,4},{1,3},{1,2},{1,1},{2,1}};
	
	int[][] gatewaypathofplayer1 = {{3,1},{4,1},{5,1},{5,2},{5,3},{5,4},{5,5},{4,5}, 
			{3,5},{2,5},{1,5},{1,4},{1,3},{1,2},{1,1},{2,1},{2,2},{2,3},{2,4},{3,4},{4,4},{4,3},{4,2},{3,2},{3,3}};
	
	int[][] gatewaypathofplayer2 = {{3,5},{2,5},{1,5},{1,4},{1,3},{1,2},{1,1},{2,1},{3,1},{4,1},{5,1},{5,2},
		{5,3},{5,4},{5,5},{4,5},{4,4},{4,3},{4,2},{3,2},{2,2},{2,3},{2,4},{3,4},{3,3}};
	
	public GameController(Board board)
	{
		this.board = board;
	}
	
	public static void main(String[] args) 
	{
		Board board = new Board();
		GameController gameController = new GameController(board);
		ProjectUI projectUI = new ProjectUI(gameController);
	}

	public Board getBoard()
	{
		return this.board;
	}
	
	public int[][] getPathOfPlayer()
	{
		return pathofplayer;
	}
	
	public int[][] getGatewaypathofplayer1()
	{
		return gatewaypathofplayer1;
	}
	
	public int[][] getGatewaypathofplayer2()
	{
		return gatewaypathofplayer2;
	}

	public String[] getGameStatus() 
	{
		ArrayList<String> positions = board.getPlayersPawnsPositions();
		String[] c = new String[] { positions.get(0), positions.get(1),
		positions.get(2), positions.get(3)};
		return c;
	}
	
	public Dice getDice() 
	{
		return this.dice;
	}
	
	public void setDice(String level) 
	{
		 this.dice = new Dice(level);
	}
	
	public void setFileReader() 
	{
		 this.fileReader = new FileReader();
	}
	
	public FileReader getFileReader()
	{
		return this.fileReader;
	}
	
}