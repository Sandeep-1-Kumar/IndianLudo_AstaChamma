package asthaChamma.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel implements ActionListener
{
	private JLabel label;
	private JButton button;
	protected String name;
	protected String score;
	public ScorePanel(String name,int score) 
	{
		label = new JLabel("Click to know High scorer details");
		button = new JButton("Click");
		this.add(label);
		this.add(button);
		button.addActionListener(this);
		this.name=name;
		this.score = String.valueOf(score);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.remove(button);
		label.setText("highest scorer "  + name + " won in " + score + " moves" );
		this.repaint();
	}

	
}