package asthaChamma.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

import asthaChamma.FileReaderInterface;
import asthaChamma.controller.GameController;
import asthaChamma.model.Player;

public class BoardPanel extends JPanel implements ActionListener
{
	private JButton button;
	private int boardSize =5;
	private int[] safeButtons = {13,22,24,31,33,35,42,44,53};
	private int diceValue =0;
	private GameController gameController;
	private int playerTurn;
	private Color color;
	private boolean opp;
	private DiceButton diceButton;
	
	public BoardPanel(GameController gameController)
	{	
		this.gameController = gameController;
		setPreferredSize(new Dimension(300,300));
		setLayout(new GridLayout(boardSize,boardSize));
		setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		
	}
	
	public void setDiceButton(DiceButton diceButton )
	{
		this.diceButton = diceButton;
	}
	
	public DiceButton getDiceButton()
	{
		return this.diceButton;
	}
	
	public void setDiceValue(int val) 
	{
		this.diceValue = val;
	}
	
	public int getDiceValue() 
	{
		return this.diceValue;
	}
	
	public  void notifyBoard(String[] PlayerPositions) 
	{
		this.removeAll();
		ArrayList<Integer> playerPositionsArrayList = new ArrayList<Integer>(4);
		for(int l = 0;l < PlayerPositions.length ;l++)
		{
			playerPositionsArrayList.add(Integer.parseInt(PlayerPositions[l]));	
		}		
		placePawnOnBoard(playerPositionsArrayList);
	}
	
	public boolean checkSafeButton(int toCheckValue)
    {
        boolean flag = false;
        for (int element : safeButtons)
        {
        	if (element == toCheckValue)
        	{
            	flag = true;
                break;
            }
        }
        return flag;
    }
	
	public void placePawnOnBoard(ArrayList<Integer> playerPositions) 
	{	
		ArrayList<Integer> numericalPlayerPositions = playerPositions;
        ArrayList<Integer> alreadyPositioned = new ArrayList<Integer>();
		for (int i = 1;i <= this.boardSize ; i++)
		{	
			for (int j=1;j<= this.boardSize; j++ ) 
			{
				int blockNumber = Integer.parseInt(String.valueOf(i) + String.valueOf(j));
				if(numericalPlayerPositions.contains(blockNumber) &&  ! alreadyPositioned.contains(blockNumber))
				{
					int occurrences = Collections.frequency(numericalPlayerPositions, blockNumber);
					alreadyPositioned.add(blockNumber);
					if(occurrences == 1) 
					{	
						if(numericalPlayerPositions.indexOf(blockNumber) == 0 || numericalPlayerPositions.indexOf(blockNumber) == 1) 
						{
							color = Color.RED;
						}
						else if (numericalPlayerPositions.indexOf(blockNumber) == 2 || numericalPlayerPositions.indexOf(blockNumber) == 3)
						{
							color = Color.BLUE;
						}
						if(checkSafeButton(blockNumber)) 
						{
							button = new SafeButton(String.valueOf(blockNumber),color,1);
							
						}
						else
						{
							button = new RoundButton(color,1,String.valueOf(blockNumber));
						}
						button.addActionListener(this);
			
						this.add(button);
					}
					if(occurrences == 2) 
					{	
						opp= false;
					
						if(blockNumber == numericalPlayerPositions.get(0) && blockNumber == numericalPlayerPositions.get(1)
							&& 	blockNumber !=  numericalPlayerPositions.get(2))
						{
							color = Color.RED;
						}
						else if(blockNumber == numericalPlayerPositions.get(2) && blockNumber == numericalPlayerPositions.get(3)
								&& 	blockNumber !=  numericalPlayerPositions.get(0))
						{
								color = Color.BLUE;
						}
						else 
						{
							
							opp =true;
						}
												
						if(checkSafeButton(blockNumber)) 
						{
							if(opp) 
							{
								
								button = new SafeButton(String.valueOf(blockNumber),2,opp);
							}
							else
							{
								button = new SafeButton(String.valueOf(blockNumber),color,2);
							}
							
						}
						else
						{
							button = new RoundButton(color,2,String.valueOf(blockNumber));
						}
						button.addActionListener(this);
						this.add(button);
					}
					if(occurrences == 3) 
					{
						boolean flag = false;
						if(numericalPlayerPositions.get(0) == numericalPlayerPositions.get(1)) 
						{
							flag = true;
						}
						button = new SafeButton(String.valueOf(blockNumber),flag,3);
						button.addActionListener(this);
						this.add(button);
					}
					if(occurrences == 4)
					{
						button = new SafeButton(String.valueOf(blockNumber),true,4);
						button.addActionListener(this);
						this.add(button);	
					}
				}	
				else 
				{
					
					if(checkSafeButton(blockNumber)) 
					{	
						button = new SafeButton(String.valueOf(blockNumber));
						button.setEnabled(false);
					}
					else
					{
						button = new JButton();
						button.setActionCommand(String.valueOf(i) + String.valueOf(j));
						button.setEnabled(false);
					}
					button.addActionListener(this);
					this.add(button);
				}
			}
		}		
	}
	

	public void setPlayerTurn(boolean val)
	{
		if(val == false )
			{this.playerTurn = 1;}
		else {
			this.playerTurn =2;
		}
	}
	
	public GameController getGameController() 
	{
		return this.gameController;
	}
	
	public void setGameController(GameController g) 
	{
		this.gameController =g;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{			
		try {
				Container parentContainer = this.getParent();
				JFrame frame = (JFrame)SwingUtilities.getRoot(this);
				JButton button = (JButton)e.getSource(); 
				String position = button.getActionCommand();
				gameController = this.getGameController();
				Player player1 = gameController.getBoard().getFirstPlayer();
				Player player2 = gameController.getBoard().getSecondPlayer();
				diceButton = this.getDiceButton();
				if(this.playerTurn == 1)			
				{  
					String[] gamePawnPositions = gameController.getGameStatus();
					if(gamePawnPositions[0].equals(position) && gamePawnPositions[1].equals(position)) 
					{
						if(player1.getKillingSpirit() == true)
						{
							for(int i=0;i<gameController.getGatewaypathofplayer1().length;i++)
							{
							
								if(player1.getPawnA().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer1()[i][0]
										&& player1.getPawnA().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer1()[i][1])
								{
									int row = gameController.getGatewaypathofplayer1()[i+this.getDiceValue()][0];
									int coulmn = gameController.getGatewaypathofplayer1()[i+this.getDiceValue()][1];
									gameController.getBoard().placePawn(row,coulmn, player1, player1.getPawnA());
									this.setDiceValue(0);
									diceButton.setEnable();
									diceButton.repaint();
									break;
								}
							}
						}
						else if(player1.getKillingSpirit() == false)
						{
							for(int i=0;i<gameController.getPathOfPlayer().length;i++)
							{
								 if(player1.getPawnA().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0] &&
										 player1.getPawnA().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
								 { 
									 int row = gameController.getPathOfPlayer()[i+this.getDiceValue()][0];
									 int coulmn = gameController.getPathOfPlayer()[i+this.getDiceValue()][1];
									 gameController.getBoard().placePawn(row,coulmn, player1, player1.getPawnA());
									 this.setDiceValue(0);
									 diceButton.setEnable();
									 diceButton.repaint();
									 break;
								 } 
							}	
				
						}
				}
				else if(gamePawnPositions[0].equals(position)) 
				{	
					if(player1.getKillingSpirit() == true)
					{
						for(int i=0;i<gameController.getGatewaypathofplayer1().length;i++)
						{
						
							if(player1.getPawnA().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer1()[i][0]
									&& player1.getPawnA().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer1()[i][1])
							{
								int row = gameController.getGatewaypathofplayer1()[i+this.getDiceValue()][0];
								int coulmn = gameController.getGatewaypathofplayer1()[i+this.getDiceValue()][1];
								gameController.getBoard().placePawn(row,coulmn, player1, player1.getPawnA());
								this.setDiceValue(0);
								diceButton.setEnable();
								diceButton.repaint();
								break;
							}
						}
					}
					else if(player1.getKillingSpirit() == false)
					{
						for(int i=0;i<gameController.getPathOfPlayer().length;i++)
						{
							if(player1.getPawnA().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0] &&
								 player1.getPawnA().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
							{ 
								int row = gameController.getPathOfPlayer()[i+this.getDiceValue()][0];
								int coulmn = gameController.getPathOfPlayer()[i+this.getDiceValue()][1];
								gameController.getBoard().placePawn(row,coulmn, player1, player1.getPawnA());
								this.setDiceValue(0);
								diceButton.setEnable();
								diceButton.repaint();
								break;
							} 
						}	
					}
				}
				else if(gamePawnPositions[1].equals(position)) 
				{
					if(player1.getKillingSpirit() == true)
					{
						for(int i=0;i<gameController.getGatewaypathofplayer1().length;i++)
						{
					
							if(player1.getPawnB().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer1()[i][0]
								&& player1.getPawnB().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer1()[i][1])
							{
								int row = gameController.getGatewaypathofplayer1()[i+this.getDiceValue()][0];
								int coulmn = gameController.getGatewaypathofplayer1()[i+this.getDiceValue()][1];
								gameController.getBoard().placePawn(row,coulmn, player1, player1.getPawnB());
							    this.setDiceValue(0);
							    diceButton.setEnable();
								diceButton.repaint();
							    break;
							}
						}
					}
					else if(player1.getKillingSpirit() == false)
					{
						for(int i=0;i<gameController.getPathOfPlayer().length;i++)
						{
							 if(player1.getPawnB().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0] &&
									 player1.getPawnB().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
							 { 
								 int row = gameController.getPathOfPlayer()[i+this.getDiceValue()][0];
								 int coulmn = gameController.getPathOfPlayer()[i+this.getDiceValue()][1];
								 gameController.getBoard().placePawn(row,coulmn, player1, player1.getPawnB());
								 this.setDiceValue(0);
								 diceButton.setEnable();
								 diceButton.repaint();
								 break;
							 } 
						}	
			
					}
				}
				this.notifyBoard(this.gameController.getGameStatus());
				parentContainer.repaint();
				parentContainer.revalidate();
				frame.repaint();
				} 
				else if(this.playerTurn == 2)
				{
					String[] gamePawnPositions = gameController.getGameStatus();
					if(gamePawnPositions[2].equals(position) && gamePawnPositions[3].equals(position)) 
					{
						if(player2.getKillingSpirit() == true)
									{
										System.out.println("Welcome player turn 2 second killing true");
									for(int i=0;i<gameController.getGatewaypathofplayer2().length;i++)
									{
									
										if(player2.getPawnA().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer2()[i][0]
												&& player2.getPawnA().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer2()[i][1])
										{
											int row = gameController.getGatewaypathofplayer2()[i+this.getDiceValue()][0];
											int coulmn = gameController.getGatewaypathofplayer2()[i+this.getDiceValue()][1];
											gameController.getBoard().placePawn(row,coulmn, player2, player2.getPawnA());
											this.setDiceValue(0);
											diceButton.setEnable();
											diceButton.repaint();
											break;
										}
									}
									}
							else if(player2.getKillingSpirit() == false)
									{
									
							for(int i=0;i<gameController.getPathOfPlayer().length;i++)
							 {
								 if(player2.getPawnA().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0] &&
										 player2.getPawnA().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
								 { 
									 int row = gameController.getPathOfPlayer()[i+this.getDiceValue()][0];
									 int coulmn = gameController.getPathOfPlayer()[i+this.getDiceValue()][1];
									 gameController.getBoard().placePawn(row,coulmn, player2,player2.getPawnA());
									 this.setDiceValue(0);
									 diceButton.setEnable();
									diceButton.repaint();
									 break;
								 } 
							 }	
					}
				}
				else if(gamePawnPositions[2].equals(position)) 
				{	
					if(player2.getKillingSpirit() == true)
					{
						for(int i=0;i<gameController.getGatewaypathofplayer2().length;i++)
						{
						
							if(player2.getPawnA().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer2()[i][0]
									&& player2.getPawnA().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer2()[i][1])
							{
								int row = gameController.getGatewaypathofplayer2()[i+this.getDiceValue()][0];
								int coulmn = gameController.getGatewaypathofplayer2()[i+this.getDiceValue()][1];
								gameController.getBoard().placePawn(row,coulmn, player2, player2.getPawnA());
								this.setDiceValue(0);
								diceButton.setEnable();
								diceButton.repaint();
								break;
							}
						}
					}
					else if(player2.getKillingSpirit() == false)
					{
						for(int i=0;i<gameController.getGatewaypathofplayer2().length;i++)
						{
							if(player2.getPawnA().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0] &&
									 player2.getPawnA().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
							{ 
								 int row = gameController.getPathOfPlayer()[i+this.getDiceValue()][0];
								 int coulmn = gameController.getPathOfPlayer()[i+this.getDiceValue()][1];
								 gameController.getBoard().placePawn(row,coulmn, player2,player2.getPawnA());
								 this.setDiceValue(0);
								 diceButton.setEnable();
								 diceButton.repaint();
								 break;
							} 
						}	
					}
				}
				else if(gamePawnPositions[3].equals(position)) 
				{
					if(player2.getKillingSpirit() == true)
					{
						for(int i=0;i<gameController.getGatewaypathofplayer2().length;i++)
						{
						
							if(player2.getPawnB().getPawnPosition()[0][0] == gameController.getGatewaypathofplayer2()[i][0]
									&& player2.getPawnB().getPawnPosition()[0][1] == gameController.getGatewaypathofplayer2()[i][1])
							{
								int row = gameController.getGatewaypathofplayer2()[i+this.getDiceValue()][0];
								int coulmn = gameController.getGatewaypathofplayer2()[i+this.getDiceValue()][1];
								gameController.getBoard().placePawn(row,coulmn, player2, player2.getPawnB());
		   					    this.setDiceValue(0);
		   					    diceButton.setEnable();
								diceButton.repaint();
								break;
							}
						}
					}
					else if(player2.getKillingSpirit() == false)
					{
						for(int i=0;i<gameController.getPathOfPlayer().length;i++)
						{
							if(player2.getPawnB().getPawnPosition()[0][0] == gameController.getPathOfPlayer()[i][0] &&
									 player2.getPawnB().getPawnPosition()[0][1] == gameController.getPathOfPlayer()[i][1])
							{ 
								 int row = gameController.getPathOfPlayer()[i+this.getDiceValue()][0];
								 int coulmn = gameController.getPathOfPlayer()[i+this.getDiceValue()][1];
								 gameController.getBoard().placePawn(row,coulmn, player2,player2.getPawnB());
								 this.setDiceValue(0);
								 diceButton.setEnable();
								 diceButton.repaint();
								 break;
							} 
						}				
					}
				}
				this.notifyBoard(this.gameController.getGameStatus());
				parentContainer.repaint();
				parentContainer.revalidate();
				frame.repaint();
			}
				
			if(gameController.getBoard().isCompleted())
			{
				this.removeAll();	
				this.setLayout(new FlowLayout());
				JLabel infoLabel = new JLabel();
				int scoreCount =0;				
				if(playerTurn==1)
				{		
					scoreCount = player1.getDiceCount();
					infoLabel.setText("Player 1 Won " + "with in " + String.valueOf(scoreCount) + " moves" );
				}
				else if(playerTurn==2) 
				{
					scoreCount = player2.getDiceCount();	
					infoLabel.setText("Player 2 Won " +"with in " + String.valueOf(scoreCount) + " moves" );				
				}
				this.add(infoLabel);
				this.gameController.setFileReader();
				FileReaderInterface fileReader =  this.getGameController().getFileReader();
				int pastScore = fileReader.getScore();
				System.out.println(pastScore);
				if(scoreCount < pastScore)
				{
					int score = scoreCount;
					JLabel jlabel2 = new JLabel("You are the new highest Scorer");
					this.add(jlabel2);
					JTextField textfield = new JTextField("Please enter your name here & submit");
					textfield.setBounds(0, 0, 150, 10);
					JButton b = new JButton("Submit");
					b.setBounds(50, 150, 100, 30);
					b.addActionListener(new ActionListener( )
					{
						   public void actionPerformed(ActionEvent ae)
						   {
							  String textFieldValue = textfield.getText();
							  fileReader.writeThisFile(textFieldValue + "," + String.valueOf(score)); 
						      frame.dispose();
						   }
					});
					this.add(textfield);
					this.add(b);
					this.repaint();
				}
				
				frame.repaint();
			}	
			
			
		}
		catch(Exception exce)
		{
			 diceButton.setEnable();
			 diceButton.repaint();
		}
		
				 
	}
	
}
