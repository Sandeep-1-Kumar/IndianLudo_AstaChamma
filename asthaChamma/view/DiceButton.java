package asthaChamma.view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DiceButton extends JButton
{
	
	public DiceButton(String name) 
	{
		super(name);
	}
	
	public void setEnable() 
	{
		this.setEnabled(true);
	}
	public void setDisable() 
	{
		this.setEnabled(false);
	}

}