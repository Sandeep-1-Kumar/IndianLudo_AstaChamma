package asthaChamma.model;

public class Pawn
{
	protected int[][] position;
	private final String type;
	
	public Pawn(String name)
	{	
		this.type = name;
		this.position = new int[1][2];
		this.position[0][0] = 0;
		this.position[0][1] = 0;
	}
	public int[][] getPawnPosition()
	{
		return this.position;
	}
	
	public void setRow(int num) 
	{
		this.position[0][0] = num;
	}
	
	public void setCol(int num)
	{
		
		this.position[0][1] = num;
	}
	
	public void updatePawnPosition(int row,int col)
	{
		this.position[0][0] = row;
		this.position[0][1] = col;
	}
	
	public String getPawnName()
	{
		return this.type;
	}
	
}
