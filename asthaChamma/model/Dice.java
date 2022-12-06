package asthaChamma.model;
import java.util.ArrayList;
import asthaChamma.DiceInterface;

public class Dice implements DiceInterface
{
	protected ArrayList<Integer> arrList;
	
	public Dice(String level)
	{
		if(level.equals("Level-1"))
		{
			int[] valuesArray = {1,2,3,4,8};
			this.initDiceList(valuesArray);
		}
		else if(level.equals("Level-2"))
		{
			int[] valuesArray = {1,1,2,2,3,3,4,8};
			this.initDiceList(valuesArray);
		}
	}
	
    public int getRandomDiceValue()
    {
    	int index = (int)(Math.random() * arrList.size());  
        return arrList.get(index);   
    }
    
    public void initDiceList(int[] values) 
    {
    	this.arrList  = new ArrayList<Integer>();

		for (int element : values)
		{
			this.arrList.add(element);
		}
    }
	
	
}
