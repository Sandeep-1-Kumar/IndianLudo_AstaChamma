package asthaChamma.view;
import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import asthaChamma.DiceInterface;
import asthaChamma.controller.GameController;
import asthaChamma.model.Player;

public class LevelPanel extends JPanel  implements ActionListener
{
	private JButton button1,button2;
	private JLabel initialLabel,leftLabel,rightLabel;	
	private boolean turn = false;
	protected BoardPanel boardPanel;
	protected DiceButton diceButton;
	protected GameController gameController;
	public LevelPanel(GameController gameController) 
	{		
		this.gameController = gameController;
		setPreferredSize(new Dimension(300,70));
		button1 = new JButton("Level-1");
		button1.setEnabled(true);
		button1.addActionListener(this);
		button2 = new JButton();
		button2.setEnabled(true);
		button2.setText("Level-2");
		button2.addActionListener(this);
		initialLabel = new JLabel("Please select a level");
		this.add(initialLabel);
		JSplitPane splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneH.setLeftComponent(button1);
		splitPaneH.setRightComponent(button2);
		this.add(splitPaneH, BorderLayout.CENTER);
		setPreferredSize(new Dimension(300,70));
	}	
	public void showGameBoard(String level)
	{	
		Container parentContainer = this.getParent();	  
	    JFrame frame = (JFrame)SwingUtilities.getRoot(this);
	    this.removeAll();
	    parentContainer.removeAll();
	    Player player1 = this.gameController.getBoard().getFirstPlayer();
	    Player player2 = this.gameController.getBoard().getSecondPlayer();
	    leftLabel = new JLabel("Player1 Click");
		rightLabel = new JLabel("Player2");
		diceButton = new DiceButton("DICE");
		initialLabel = new JLabel("");
		this.gameController.setDice(level);
		DiceInterface dice = this.gameController.getDice();
		BoardPanel display = new BoardPanel(this.gameController);
		display.setDiceButton(diceButton);
		String[] PlayerPositions = new String[4];
		PlayerPositions = gameController.getGameStatus();
		display.notifyBoard(PlayerPositions);
		JSplitPane splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneV.setTopComponent(this);
		splitPaneV.setBottomComponent(display);
	    parentContainer.add(splitPaneV);
	    frame.pack();
		diceButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent click_event) 
			{	
				
				initialLabel.setText("");
				initialLabel.repaint();
				int diceNumber = dice.getRandomDiceValue();
				String diceValue = String.valueOf(diceNumber);
				diceButton.setText("value : " + diceValue);
				if(turn == false)
				{
					//This is player 1 turn
					player1.increaseDiceCount();
					if(diceNumber == 4)
					{
						//This is possible  dice value 4 condition						
						if(player1.getNumberOfPawnsOnBoard() == 0)
						{
							gameController.getBoard().placePawn(3,1, player1, player1.getPawnA());
							player1.addNumberOfPawnsOnBoard();
						}
						else if(player1.getNumberOfPawnsOnBoard() == 1)
						{
							String[] PlayerPositions = gameController.getGameStatus();
							if(PlayerPositions[0].equals("00"))
							{
								gameController.getBoard().placePawn(3,1, player1, player1.getPawnA());
								player1.addNumberOfPawnsOnBoard();
							}
							else if(PlayerPositions[1].equals("00"))
							{
								gameController.getBoard().placePawn(3,1, player1, player1.getPawnB());
								player1.addNumberOfPawnsOnBoard();
							}
								
						
						}
						//need to implement with buttons
						else if(player1.getNumberOfPawnsOnBoard() == 2)
						{
							initialLabel.setText("please move pawn");
							display.setDiceValue(diceNumber);
							display.setPlayerTurn(turn);
							display.setGameController(gameController);
							diceButton.setDisable();
							diceButton.repaint();
						}
					}
					else if (diceNumber ==8)
					{
						
						//Possible dice value 8 condition
						if(player1.getNumberOfPawnsOnBoard() == 0)
						{
							gameController.getBoard().placePawn(3,1, player1, player1.getPawnA());
							gameController.getBoard().placePawn(3,1, player1, player1.getPawnB());
							player1.addNumberOfPawnsOnBoard();
							player1.addNumberOfPawnsOnBoard();
			
						}
						else if(player1.getNumberOfPawnsOnBoard() == 1)
						{
							String[] PlayerPositions = gameController.getGameStatus();
							if(PlayerPositions[0].equals("00"))
							{
								gameController.getBoard().placePawn(5,3, player1, player1.getPawnA());
								player1.addNumberOfPawnsOnBoard();
							}
							else if(PlayerPositions[1].equals("00"))
							{
								gameController.getBoard().placePawn(5,3, player1, player1.getPawnB());
								player1.addNumberOfPawnsOnBoard();
							}
							
						}
						
						else if(player1.getNumberOfPawnsOnBoard() == 2)
						{
							
							initialLabel.setText("please move pawn");	
							display.setDiceValue(diceNumber);
							display.setPlayerTurn(turn);
							display.setGameController(gameController);	
							diceButton.setDisable();
							diceButton.repaint();			
						}
					}
					else
					{
						if(player1.getNumberOfPawnsOnBoard() == 1)
						{
							String[] PlayerPositions = gameController.getGameStatus();
							if(PlayerPositions[1].equals("00"))
							{
							if(player1.getKillingSpirit() == true)
							{
							for(int i=0;i<gameController.getGatewaypathofplayer1().length;i++)
							{
							
								if(player1.getPawnA().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer1()[i][0]
										&& player1.getPawnA().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer1()[i][1])
								{
									int row = gameController.getGatewaypathofplayer1()[i+diceNumber][0];
									int coulmn = gameController.getGatewaypathofplayer1()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn,player1, player1.getPawnA());
									break;
								}
							}
							}
							else if(player1.getKillingSpirit() == false)
							{
								
								for(int i=0;i<gameController.getPathOfPlayer().length;i++)
								{
							
									if(player1.getPawnA().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0]
										&& player1.getPawnA().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
								{
									int row = gameController.getPathOfPlayer()[i+diceNumber][0];
									int coulmn = gameController.getPathOfPlayer()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn,player1, player1.getPawnA());
									break;
								}
							}
						}
						}
							else if(PlayerPositions[0].equals("00"))
							{
								if(player1.getKillingSpirit() == true)
							{
							for(int i=0;i<gameController.getGatewaypathofplayer1().length;i++)
							{
							
								if(player1.getPawnB().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer1()[i][0]
										&& player1.getPawnB().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer1()[i][1])
								{
									int row = gameController.getGatewaypathofplayer1()[i+diceNumber][0];
									int coulmn = gameController.getGatewaypathofplayer1()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn, player1, player1.getPawnB());
									break;
								}
							}
							}
							else if(player1.getKillingSpirit() == false)
							{
								
								for(int i=0;i<gameController.getPathOfPlayer().length;i++)
								{
							
									if(player1.getPawnB().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0]
										&& player1.getPawnB().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
								{
									int row = gameController.getPathOfPlayer()[i+diceNumber][0];
									int coulmn = gameController.getPathOfPlayer()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn,player1, player1.getPawnB());
									break;
								}
							}
						}
								
								
								
							}
					}
					
						else if(player1.getNumberOfPawnsOnBoard() == 2)
						{
							initialLabel.setText("please move pawn");
							display.setDiceValue(diceNumber);
							display.setPlayerTurn(turn);	
							display.setGameController(gameController);
							diceButton.setDisable();
							diceButton.repaint();
						}
					}
					rightLabel.setText("Player2 Click");
					leftLabel.setText("Player1 : " + diceValue );
					String[] PlayerPositions = gameController.getGameStatus();
					//This is our trouble shooter checkpoint of the code
					//System.out.println(PlayerPositions[0]+ " " + " " + PlayerPositions[1]+ " " + PlayerPositions[2]+" " + PlayerPositions[3] );
					display.notifyBoard(PlayerPositions);
					parentContainer.repaint();
					parentContainer.revalidate();
					frame.repaint();
					turn = true;
				}
				else if (turn == true)
				{
					//This is player 1 turn
					player2.increaseDiceCount();
					if(diceNumber == 4)
					{
						//This is possible  dice value 4 condition						
						if(player2.getNumberOfPawnsOnBoard() == 0)
						{
							
							gameController.getBoard().placePawn(3,5, player2, player2.getPawnA());
							player2.addNumberOfPawnsOnBoard();
						}
						else if(player2.getNumberOfPawnsOnBoard() == 1)
						{
							String[] PlayerPositions = gameController.getGameStatus();
							if(PlayerPositions[2].equals("00"))
							{
								gameController.getBoard().placePawn(3,5, player2,player2.getPawnA());
								player2.addNumberOfPawnsOnBoard();
							}
							else if(PlayerPositions[3].equals("00"))
							{
								
								gameController.getBoard().placePawn(3,5,player2,player2.getPawnB());
								player2.addNumberOfPawnsOnBoard();
						}
							
							
						}
						//need to implement with buttons
						else if(player2.getNumberOfPawnsOnBoard() == 2)
						{	
							initialLabel.setText("please move pawn");
							display.setDiceValue(diceNumber);
							display.setPlayerTurn(turn);
							display.setGameController(gameController);
							diceButton.setDisable();
							diceButton.repaint();
							
						}
					}
					else if (diceNumber ==8)
					{
						//Possible dice value 8 condition
						if(player2.getNumberOfPawnsOnBoard() == 0)
						{
							
							gameController.getBoard().placePawn(3,5, player2, player2.getPawnA());
							gameController.getBoard().placePawn(3,5, player2, player2.getPawnB());
							player2.addNumberOfPawnsOnBoard();
							player2.addNumberOfPawnsOnBoard();
						
						}
						else if(player2.getNumberOfPawnsOnBoard() == 1)
						{
							String[] PlayerPositions = gameController.getGameStatus();
							if(PlayerPositions[2].equals("00"))
							{
								gameController.getBoard().placePawn(1,3, player2,player2.getPawnA());
								player2.addNumberOfPawnsOnBoard();
							}
							else if(PlayerPositions[3].equals("00"))
							{
							gameController.getBoard().placePawn(1,3, player2, player2.getPawnB());
							player2.addNumberOfPawnsOnBoard();
							}
						}
						//need to implement with buttons moment
						else if(player2.getNumberOfPawnsOnBoard() == 2)
						{
						
							initialLabel.setText("please move pawn");
							display.setDiceValue(diceNumber);
							display.setPlayerTurn(turn);
							display.setGameController(gameController);
							diceButton.setDisable();
							diceButton.repaint();
							
						}
					}
					else
					{
						if(player2.getNumberOfPawnsOnBoard() == 1)
						{	
							
							
							String[] PlayerPositions = gameController.getGameStatus();
							if(PlayerPositions[3].equals("00"))
							{
							if(player2.getKillingSpirit() == true)
							{
	
							for(int i=0;i<gameController.getGatewaypathofplayer2().length;i++)
							{
							
								if(player2.getPawnA().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer2()[i][0]
										&& player2.getPawnA().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer2()[i][1])
								{
									int row = gameController.getGatewaypathofplayer2()[i+diceNumber][0];
									int coulmn = gameController.getGatewaypathofplayer2()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn, player2, player2.getPawnA());
									break;
								}
							}
							}
							else if(player1.getKillingSpirit() == false)
							{
				
							for(int i=0;i<gameController.getPathOfPlayer().length;i++)
							{
							
								if(player2.getPawnA().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0]
										&& player2.getPawnA().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
								{
									int row = gameController.getPathOfPlayer()[i+diceNumber][0];
									int coulmn = gameController.getPathOfPlayer()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn, player2, player2.getPawnA());
									break;
								}
							}

						}
					}
					else if(PlayerPositions[2].equals("00"))
					{
							if(player2.getKillingSpirit() == true)
							{
	
							for(int i=0;i<gameController.getGatewaypathofplayer2().length;i++)
							{
							
								if(player2.getPawnB().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer2()[i][0]
										&& player2.getPawnB().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer2()[i][1])
								{
									int row = gameController.getGatewaypathofplayer2()[i+diceNumber][0];
									int coulmn = gameController.getGatewaypathofplayer2()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn, player2, player2.getPawnB());
									break;
								}
							}
							}
							else if(player1.getKillingSpirit() == false)
							{
				
							for(int i=0;i<gameController.getPathOfPlayer().length;i++)
							{
							
								if(player2.getPawnB().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0]
										&& player2.getPawnB().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
								{
									int row = gameController.getPathOfPlayer()[i+diceNumber][0];
									int coulmn = gameController.getPathOfPlayer()[i+diceNumber][1];
									gameController.getBoard().placePawn(row,coulmn, player2, player2.getPawnB());
									break;
								}
							}

						}	
						
					}
				}
						else if(player2.getNumberOfPawnsOnBoard() == 2)
						{
							initialLabel.setText("please move pawn");
							display.setDiceValue(diceNumber);
							display.setPlayerTurn(turn);
							display.setGameController(gameController);
							diceButton.setDisable();
							diceButton.repaint();
						}
					}
					rightLabel.setText("Player2 : " + diceValue ); 
					leftLabel.setText("Player1 Click"); 
					String[] PlayerPositions = gameController.getGameStatus();
					
					//This is our trouble shooter checkpoint of the code
					//System.out.println(PlayerPositions[0]+ " " + " " + PlayerPositions[1]+ " " + PlayerPositions[2]+" " + PlayerPositions[3] );
					
					display.notifyBoard(PlayerPositions);
					parentContainer.repaint();
					parentContainer.revalidate();
					frame.repaint();
					turn = false;
				}
			}	});
		
		this.add(leftLabel,BorderLayout.EAST);
		this.add(diceButton,BorderLayout.CENTER);
		this.add(rightLabel,BorderLayout.WEST);
		this.add(initialLabel);
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{	   
		JButton button = (JButton) e.getSource();
		String mode = button.getText();
		if(mode.equals("Level-1"))
	    {	  
			this.showGameBoard("Level-1");
	    }
	   	else if (mode.equals("Level-2"))
	    {
			this.showGameBoard("Level-2");
		}   
	}
}
