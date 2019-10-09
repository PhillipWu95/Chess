package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import board.Board;
import board.Square;
import pieces.ChessPiece;
import pieces.King.KingCapturedException;

public class ChessGame {

	private JFrame frame;
	private boolean pieceSelected;
	private Board board;
	private Square selectedSquare;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessGame window = new ChessGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChessGame() {
		pieceSelected = false;
		board = new Board();
		board.classicalInit();
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 875+4*12, 675+4*12);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel background = new JPanel();
		frame.getContentPane().add(background);
		background.setLayout(null);
		
		JPanel options = new JPanel();
		options.setBounds(12, 12, 200, 675);
		background.add(options);
		options.setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(31, 421, 139, 55);
		options.add(btnNewButton);
		
		JPanel boardView = new JPanel();
		boardView.setBounds(12+200, 12, 675, 675);
		background.add(boardView);
		GridBagLayout gbl_boardView = new GridBagLayout();
		gbl_boardView.columnWidths = new int[]{0};
		gbl_boardView.rowHeights = new int[]{0};
		gbl_boardView.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_boardView.rowWeights = new double[]{Double.MIN_VALUE};
		boardView.setLayout(gbl_boardView);
		
		

		for(int column = 1; column <= 8; column++) {
			// file label
			String str = "" + (char)(((int)'a') + column - 1);
			JLabel lb = new JLabel(str); 
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.NONE;
			c.gridx = column;
			c.gridy = 9;
			c.weightx = 1.0/9;
			c.weighty = 1.0/9;
			boardView.add(lb, c);
		}
		
		for(int row = 0; row <= 7; row++) {
			// rank label
			String str = "" + (8 - row);
			JLabel lb = new JLabel(str);
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.NONE;
			c.gridx = 0;
			c.gridy = row;
			c.weightx = 1.0/9;
			c.weighty = 1.0/9;
			boardView.add(lb, c);
		}
		
		for(int row = 0; row <= 7; row++) {
			for(int column = 1; column <= 8; column++) {
				int file = column - 1;
				int rank = 8 - row - 1;
				
				
//				JLabel lb = new JLabel(imageIcon);
				JLabel lb = new JLabel();
				Square square = this.board.squares[file][rank];
		        square.setLabel(lb);
		        square.reDraw();
		        lb.setPreferredSize(new Dimension(75,75));
		        
				//icon
				
				
				// board
				
				
				GridBagConstraints c = new GridBagConstraints();
				lb.setOpaque(true);
				square.setBackground();
				lb.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
				    	if(!pieceSelected && square.isOccupied()) {
				    		pieceSelected = true;
				    		selectedSquare = square;
				    		square.setSelectedBackground();
				    	}
				    	else if(pieceSelected){
				    		try {
								boolean moved = selectedSquare.moveTo(square);
//								if(moved || (selectedSquare.equals(square))) {
								pieceSelected = false;
								selectedSquare.setBackground();
//								}
							} catch (KingCapturedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    		
				    	}

				    }  
				});
				c.fill = GridBagConstraints.BOTH;
				c.gridx = column;
				c.gridy = row;
				c.weightx = 1.0/9;
				c.weighty = 1.0/9;
				boardView.add(lb, c);
				
//				if(square.isOccupied()) {
////					ImageIcon imageIcon = new ImageIcon("./resource/chess_piece_black_bishop_T.png"); // load the image to a imageIcon
//					ImageIcon imageIcon = new ImageIcon(square.getImage()); // load the image to a imageIcon
//					Image image = imageIcon.getImage(); // transform it 
//					Image newimg = image.getScaledInstance(82, 82,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
//					imageIcon = new ImageIcon(newimg);  // transform it back
//					lb.setIcon(imageIcon);
//				}
			}
		}
		
		
		
	}
}
