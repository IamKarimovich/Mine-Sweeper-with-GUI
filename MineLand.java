import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MineLand{
	int openedButton =0;
	JFrame frame;
	Button[][] board = new Button[10][10];
	
	public MineLand() {
		frame = new JFrame("MINE SWEEPER!");
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		frame.setLayout(new GridLayout(10,10));
		
		for(int row = 0;row<10;row++)
		{
			for(int col = 0;col<10;col++)
			{
				Button b = new Button(row, col);
				board[row][col] = b;
				b.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						Button b = (Button) e.getComponent();
						if(e.getButton()==1)
						{
							System.out.println("Sol");
							if(b.isMine())
							{
								JOptionPane.showMessageDialog(frame, "GAME OVER!");
								openAll();
							}else {
								open(b.getRow(),b.getCol());
								if(openedButton == (board.length*board[0].length - 10)){
									JOptionPane.showMessageDialog(frame, "YOU WIN!");
									openAll();
								}
							}
							
						}else if(e.getButton()==3){
							System.out.println("Sag");
							if (!b.isFlag()) {
								b.setIcon(new ImageIcon("Flag.png"));
								b.setFlag(true);
							} else {
								b.setIcon(null);
								b.setFlag(false);
							}
						}
						
					}
				});
				frame.add(b);
			}
		}
		
		putMine();
		updateCount();
		//printMine();
		//printCount();
		frame.setVisible(true);
		
	}
	
	public void putMine()
	{
		int i = 0;
		while (i < 10) {
			int randRow = (int) (Math.random() * board.length);
			int randCol = (int) (Math.random() * board[0].length);

			while (board[randRow][randCol].isMine()) {
				randRow = (int) (Math.random() * board.length);
				randCol = (int) (Math.random() * board[0].length);
				
			}
			board[randRow][randCol].setMine(true);
			i++;
		}
		
	}
	
	public void printMine() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("Mine.png"));
				}
			}
		}
	}

	public void updateCount()
	{
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					toCount(row,col);
				}
			}
		}
	}
	
	public void toCount(int row,int col) {
		for(int i = row-1 ; i <= row + 1; i++)
		{
			for(int j = col-1 ; j<=col+1 ; j++)
			{
				try
				{
					int value = board[i][j].getCount();
					board[i][j].setCount(++value);
				}catch(Exception e)
				{
						
				}
				
			}
		}
	}
	
	public void printCount()
	{
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				board[row][col].setEnabled(false);
				board[row][col].setText(Integer.toString(board[row][col].getCount()));
			}
		}
	}

	public void open(int row,int col)
	{
	if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col].getText().length() > 0
				|| board[row][col].isEnabled() == false) {
				return;
	} else if (board[row][col].getCount() != 0) {
		board[row][col].setText(board[row][col].getCount() + "");
		board[row][col].setEnabled(false);
		openedButton++;
	} else {
		openedButton++;
		board[row][col].setEnabled(false);
		open(row - 1, col);
		open(row + 1, col);
		open(row, col - 1);
		open(row, col + 1);
	}

	}
	
	public void openAll()
	{
		
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[0].length; col++) {
					if (board[row][col].isMine()) {
						board[row][col].setIcon(new ImageIcon("Mine.png"));
					} else {
						board[row][col].setText(board[row][col].getCount() + "");
						board[row][col].setEnabled(false);
					}
				}
			}
		
	}
	
	
	
	
	
	}
