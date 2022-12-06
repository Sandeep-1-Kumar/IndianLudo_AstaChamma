package asthaChamma.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

class RoundButton extends JButton
{	
	Color color;
	int num;
	public RoundButton(Color color,int num,String name) 
	{	
		this.setActionCommand(name);
		this.num=num;
		this.color = color;
		setOpaque(false);
	}
    public void paintComponent(Graphics g)
    {
    	g.setColor(this.color);
    	int xAlign = 15;
    	int yAlign = 15;
    	for (int i = 0 ; i < num ; i++) 
    	{
    		g.fillOval(xAlign-8,yAlign, getWidth()/2, getHeight()/2);
    		xAlign +=20;
    	}        
    }
}