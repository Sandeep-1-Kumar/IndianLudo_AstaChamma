package asthaChamma.view;

import javax.swing.*;
import asthaChamma.controller.GameController;
import asthaChamma.model.FileReader;

public class ProjectUI
{	
	JLabel initialLabel;
	LevelPanel levelPanel;
	ScorePanel scorePanel;
	GameController gameController;
	public ProjectUI(GameController gameController) 
	{
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Astha Chama");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		levelPanel = new LevelPanel(gameController);
		FileReader filereader= new FileReader();
		scorePanel = new ScorePanel(filereader.getHighestScorerName(),filereader.getScore());
		JSplitPane splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneV.add(levelPanel);
		splitPaneV.add(scorePanel);
		mainPanel.add(splitPaneV);
		mainPanel.repaint();
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}	
}

	

