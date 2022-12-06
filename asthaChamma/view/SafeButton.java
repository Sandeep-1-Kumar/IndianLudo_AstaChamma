package asthaChamma.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

class SafeButton extends JButton
{	
	boolean flag = false;
	int num;
	boolean redPAwnAsMajor;
	Color clr;
	boolean opp;
	public SafeButton(String val) 
	{	
		this.setActionCommand(val);
		setBackground(Color.GREEN);	
		flag = false;
	}
	
	public SafeButton(String val,Color clr,int num) 
	{		
		this.setActionCommand(val);
		flag = true;
		this.clr = clr;
		this.num=num;
		setOpaque(false);
	}
	public SafeButton(String val,int num,boolean opp) 
	{	
		this.setActionCommand(val);
		flag = true;
		this.num = num;
		this.opp = true;
		setOpaque(false);
	}
	
	public SafeButton(String val,boolean redPAwnAsMajor,int num) 
	{	
		this.setActionCommand(val);
		flag = true;
		this.num=num;
		this.redPAwnAsMajor = redPAwnAsMajor;
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{	
		
		if(flag)
		{
			if(num==1) 
			{
				int xAlign =15;
				int yAlign=15;
				g.setColor(Color.GREEN);
		    	g.fillRect(0, 0, getWidth(), getHeight());
		    	for (int i = 0 ; i < num ; i++) 
		    	{
		    		g.setColor(this.clr);
		    		g.fillOval(xAlign-8,yAlign, getWidth()/2, getHeight()/2);
		    		xAlign +=20;
		    	}
			}
			else if(num ==2)
			{
				
				if(opp)
				{
					g.setColor(Color.GREEN);
			    	g.fillRect(0, 0, getWidth(), getHeight());
			    	g.setColor(Color.RED);
			    	g.fillOval(0,15, getWidth()/2, getHeight()/2);
			    	g.setColor(Color.BLUE);
			    	g.fillOval(30,15, getWidth()/2, getHeight()/2);	
				}
				else 
				{
					int xAlign =15;
					int yAlign=15;
					g.setColor(Color.GREEN);
			    	g.fillRect(0, 0, getWidth(), getHeight());
			    	for (int i = 0 ; i < num ; i++) 
			    	{
			    		g.setColor(this.clr);
			    		g.fillOval(xAlign-8,yAlign, getWidth()/2, getHeight()/2);
			    		xAlign +=20;
			    	}			    	
				}					
	
			}
			else if(this.num ==3) 
			{
				int xAlign =0;
				int yAlign=15;
				g.setColor(Color.GREEN);
		    	g.fillRect(0, 0, getWidth(), getHeight());
		    	if(redPAwnAsMajor) 
	    		{
		    		for (int i = 0 ; i < 3 ; i++) 
			    	{
		    			if(i==0 || i==1) 
		    			{
		    				clr = Color.RED;
		    			}
		    			else 
		    			{
		    				clr = Color.BLUE;
		    			}
		    			g.setColor(this.clr);
		    			g.fillOval(xAlign,yAlign, getWidth()/3, getHeight()/3);
			    		xAlign +=20;
			    	}
	    		}
		    	else 
		    	{
		    		for (int i = 0 ; i < 3 ; i++) 
			    	{
		    			if(i==0 || i==1) 
		    			{
		    				clr = Color.BLUE;
		    			}
		    			else 
		    			{
		    				clr = Color.RED;
		    			}
		    			g.setColor(this.clr);
		    			g.fillOval(xAlign,yAlign, getWidth()/3, getHeight()/3);
			    		xAlign +=20;
			    	}
		    	}
	    			
		    	
			}
			else if(this.num ==4)
			{
				int xAlign =0;
				int yAlign=15;
				g.setColor(Color.GREEN);
		    	g.fillRect(0, 0, getWidth(), getHeight());
		    	for (int i = 0 ; i < num ; i++) 
		    	{
		    		if(i <=1)
		    		{
		    			g.setColor(Color.RED);
		    		}
		    		else 
		    		{
		    			g.setColor(Color.BLUE);
		    		}
		    		
		    		g.fillOval(xAlign,yAlign, getWidth()/4, getHeight()/4);
		    		xAlign +=15;
				}
			}

		}
		else 
		{
			g.setColor(Color.GREEN);
	    	g.fillRect(0, 0, getWidth(), getHeight());
		}

	 }
	
}