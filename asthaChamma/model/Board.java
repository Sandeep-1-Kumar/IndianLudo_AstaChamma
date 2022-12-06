package asthaChamma.model;

import java.util.ArrayList;


public class Board
{
	private int size;
	protected int[] safePositions = {13, 22, 24, 31, 33, 35, 42, 44, 53};
	protected Player[] player;
	public Board()
	{
		this.initBoard();
		this.size = 5;
	}	
	
	public Player[] getPlayers() 
	{
		return this.player;
	}
	

	public Player getFirstPlayer() 
	{
		return this.player[0];
	}
	
	public Player getSecondPlayer() 
	{
		return this.player[1];
	}
	
	public void initBoard()
	{
		Pawn player1PawnA = new Pawn("player1PawnA");
		Pawn player1PawnB = new Pawn("player1PawnB");
		Pawn player2PawnA = new Pawn("player2PawnA");
		Pawn player2PawnB = new Pawn("player2PawnB");
		Player playersArray[] = new Player[2];
		Player player1 = new HumanPlayer(player1PawnA,player1PawnB,"player1");
		Player player2 = new HumanPlayer(player2PawnA,player2PawnB,"player2");
		playersArray[0] = player1;
		playersArray[1] = player2;
		this.player = playersArray;
	}
	
	
	public int getSize()
	{
		return this.size;
	}

	public boolean checkSafePositions(int row, int col) 
	{
		boolean flag = false;
		for (int element : safePositions)
		{
			if (element == Integer.parseInt(String.valueOf(row) + String.valueOf(col)))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}

	public ArrayList<String> getPlayersPawnsPositions() {
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add(((String.valueOf(this.player[0].getPawnA().getPawnPosition()[0][0]))
				+ (String.valueOf(this.player[0].getPawnA().getPawnPosition()[0][1]))));
		arrList.add(((String.valueOf(this.player[0].getPawnB().getPawnPosition()[0][0]))
				+ (String.valueOf(this.player[0].getPawnB().getPawnPosition()[0][1]))));
		arrList.add(((String.valueOf(this.player[1].getPawnA().getPawnPosition()[0][0]))
				+ (String.valueOf(this.player[1].getPawnA().getPawnPosition()[0][1]))));
		arrList.add(((String.valueOf(this.player[1].getPawnB().getPawnPosition()[0][0]))
				+ (String.valueOf(this.player[1].getPawnB().getPawnPosition()[0][1]))));
		return arrList;
	}
	
	public void placePawn(int row, int col, Player player, Pawn pawn) 
	{
		String position = String.valueOf(row) + String.valueOf(col);
		if(player.getPlayerName().equals("player1"))
		{
			if(pawn.getPawnName().equals("player1PawnA"))
			{	
				if(!checkSafePositions(row,col))
				{ 
					KnockOutCheck(pawn,position);
				}	
				this.player[0].getPawnA().updatePawnPosition(row, col);
			}
			else if(pawn.getPawnName().equals("player1PawnB"))
			{
				if(!checkSafePositions(row,col))
				{ 
					KnockOutCheck(pawn,position);
				}	
				this.player[0].getPawnB().updatePawnPosition(row, col);
			}
		}
		else if(player.getPlayerName().equals("player2"))
		{
			
			if(pawn.getPawnName().equals("player2PawnA"))
			{	
				if(!checkSafePositions(row,col))
				{ 
					KnockOutCheck(pawn,position);
				}	
				this.player[1].getPawnA().updatePawnPosition(row, col);
			}
			else if(pawn.getPawnName().equals("player2PawnB"))
			{
				if(!checkSafePositions(row,col))
				{ 
					KnockOutCheck(pawn,position);
				}	
				this.player[1].getPawnB().updatePawnPosition(row, col);
			}
		}
				  
	}
	
	public void KnockOutCheck(Pawn pawn,String position)
	{
		  
		  if(pawn.getPawnName().equals("player1PawnA") || pawn.getPawnName().equals("player1PawnB"))
		  {
			  String pawnPosition = position;
			  String oppositePlayerPawnAPosition =  (String.valueOf(this.player[1].getPawnA().getPawnPosition()[0][0]) + String.valueOf(this.player[1].getPawnA().getPawnPosition()[0][1])); 
			  String oppositePlayerPawnBPosition = 	(String.valueOf(this.player[1].getPawnB().getPawnPosition()[0][0]) + String.valueOf(this.player[1].getPawnB().getPawnPosition()[0][1]));
			  if(pawnPosition.equals(oppositePlayerPawnAPosition) && oppositePlayerPawnAPosition.equals(oppositePlayerPawnBPosition))
			  {
				  //double knock out
				  this.player[1].getPawnA().updatePawnPosition(0,0);
				  this.player[1].getPawnB().updatePawnPosition(0,0);
				  this.player[1].substractNumberOfPawnsOnBoard();
				  this.player[1].substractNumberOfPawnsOnBoard();
				  this.player[0].setKillingSpirit(true);
			  }
			  else if(pawnPosition.equals(oppositePlayerPawnAPosition))
			  {
				  this.player[1].getPawnA().updatePawnPosition(0,0);
				  this.player[1].substractNumberOfPawnsOnBoard();
				  this.player[0].setKillingSpirit(true);
			  } 
			  else if(pawnPosition.equals(oppositePlayerPawnBPosition)) 
			  {
				  this.player[1].getPawnB().updatePawnPosition(0,0);
				  this.player[1].substractNumberOfPawnsOnBoard();
				  this.player[0].setKillingSpirit(true);
			  }
			  
			  
		  }
		  else if(pawn.getPawnName().equals("player2PawnA") || pawn.getPawnName().equals("player2PawnB"))
		  {
			  String pawnPosition = position;
			  String oppositePlayerPawnAPosition =  (String.valueOf(this.player[0].getPawnA().getPawnPosition()[0][0]) + String.valueOf(this.player[0].getPawnA().getPawnPosition()[0][1])); 
			  String oppositePlayerPawnBPosition = 	(String.valueOf(this.player[0].getPawnB().getPawnPosition()[0][0]) + String.valueOf(this.player[0].getPawnB().getPawnPosition()[0][1]));
			  if(pawnPosition.equals(oppositePlayerPawnAPosition) && oppositePlayerPawnAPosition.equals(oppositePlayerPawnBPosition))
			  {
				  //double knock out
				  this.player[0].getPawnA().updatePawnPosition(0,0);
				  this.player[0].getPawnB().updatePawnPosition(0,0);
				  this.player[0].substractNumberOfPawnsOnBoard();
				  this.player[0].substractNumberOfPawnsOnBoard();
				  this.player[1].setKillingSpirit(true);
			  }
			  else if(pawnPosition.equals(oppositePlayerPawnAPosition))
			  {
				  this.player[0].getPawnA().updatePawnPosition(0,0);
				  this.player[0].substractNumberOfPawnsOnBoard();
				  this.player[1].setKillingSpirit(true);
			  } 
			  else if(pawnPosition.equals(oppositePlayerPawnBPosition))
			  {
				  this.player[0].getPawnB().updatePawnPosition(0,0);
				  this.player[0].substractNumberOfPawnsOnBoard();
				  this.player[1].setKillingSpirit(true);
			  }
		  }
		  
	}
	
	public boolean isCompleted()
	{
		boolean flag = false;
		ArrayList<String> playerPositions = this.getPlayersPawnsPositions();
		if(playerPositions.get(0).equals("33") && playerPositions.get(1).equals("33"))
		{
			flag = true;
		}
		else if (playerPositions.get(2).equals("33") && playerPositions.get(3).equals("33"))
		{
			flag = true;
		}
		
		return flag;
	}

}
