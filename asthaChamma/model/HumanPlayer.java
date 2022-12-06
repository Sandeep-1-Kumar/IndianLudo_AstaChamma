package asthaChamma.model;

import java.util.ArrayList;



public class HumanPlayer extends Player
{
	protected boolean killingSpirit;
	private boolean gateWayAccess;  
	
	public HumanPlayer(Pawn pawnA, Pawn pawnB,String name)
	{
		super(pawnA,pawnB,name);
		this.gateWayAccess = false;
		this.numberOfPawnsOnBoard =0;
		this.killingSpirit = false;
	}
	public String getPlayerName()
	   { 
		   return this.name;
	   }
	   public Pawn getPawnA()
	   {
		   return this.pawnA;
	   }
	   public Pawn getPawnB()
	   {
		   return this.pawnB;
	   }
	   
	   public ArrayList<Pawn> getPawns()
	   {
		   ArrayList<Pawn> pawnsArray = new ArrayList<Pawn>();
		   pawnsArray.add(pawnA);
		   pawnsArray.add(pawnB);
		   return pawnsArray;  
	   }
	
	public int getNumberOfPawnsOnBoard()
	   {
		   return numberOfPawnsOnBoard;
	   }
	   public boolean getKillingSpirit()
		   {
			   return this.killingSpirit;
		   }
		 public void setKillingSpirit(boolean killingSpirit)
		   {
			   this.killingSpirit = killingSpirit;
		   }
	   public void addNumberOfPawnsOnBoard()
	   {
		   this.numberOfPawnsOnBoard+=1;
	   }
	   public void substractNumberOfPawnsOnBoard()
	   {
		   this.numberOfPawnsOnBoard-=1;
	   } 
	   public void increaseDiceCount()
	   {
		   this.diceCount+=1;
	   }
	   public int getDiceCount()
	   {
		   return this.diceCount;
	   }
	   public void giveAccessToPlayer()
	   {
		   this.gateWayAccess = true;
	   }
	   public boolean isGateWayAccess() 
	   {
			return gateWayAccess;
	   }
}