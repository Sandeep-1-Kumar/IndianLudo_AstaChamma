package asthaChamma.model;

import java.util.ArrayList;

public abstract class Player
{
   protected int numberOfPawnsOnBoard;
   protected Pawn pawnA;
   protected Pawn pawnB;
   protected String name;
   protected int diceCount;
   public Player(Pawn pawnA,Pawn pawnB,String name)
   {
	   this.name = name;
	   this.pawnA = pawnA;
	   this.pawnB = pawnB;
   }
   public abstract String getPlayerName();
   public abstract Pawn getPawnA();
   public abstract Pawn getPawnB();
   public abstract ArrayList<Pawn> getPawns();
   public abstract void substractNumberOfPawnsOnBoard();
   public abstract void addNumberOfPawnsOnBoard();
   public abstract void increaseDiceCount();
   public abstract int  getDiceCount();
   public abstract void setKillingSpirit(boolean killingSpirit);
   public abstract int getNumberOfPawnsOnBoard();
   public abstract boolean getKillingSpirit();

}
