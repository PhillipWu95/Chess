package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class ChessGame {

	private JFrame frame;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800+4*12, 600+4*12);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel background = new JPanel();
		frame.getContentPane().add(background);
		background.setLayout(null);
		
		JPanel options = new JPanel();
		options.setBounds(12, 12, 200, 600);
		background.add(options);
		
		JPanel boardView = new JPanel();
		boardView.setBounds(12+200, 12, 600, 600);
		background.add(boardView);
		GridBagLayout gbl_boardView = new GridBagLayout();
		gbl_boardView.columnWidths = new int[]{0};
		gbl_boardView.rowHeights = new int[]{0};
		gbl_boardView.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_boardView.rowWeights = new double[]{Double.MIN_VALUE};
		boardView.setLayout(gbl_boardView);
		
		
//		JPanel board = new JPanel();
//		
//		GridBagConstraints boardC = new GridBagConstraints();
//		boardC.fill = GridBagConstraints.NONE;
//		boardC.gridx = 1;
//		boardC.gridy = 0;
//		boardC.gridwidth = 8;
//		boardC.gridheight = 8;
//		boardC.weightx = 0;
//		boardC.weighty = 0;
//		
//		boardView.add(board, boardC);
//		
//		JLabel boardLabel = new JLabel();
//		Image newImage = 
//		ImageIcon boardImg = new ImageIcon("./resource/chessboard_T.png");
//		boardLabel.setIcon(boardImg);
//		board.add(boardLabel);
//
//		
		for(int column = 1; column <= 8; column++) {
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
				int rank = 8 - row;
				JLabel lb = new JLabel();
				GridBagConstraints c = new GridBagConstraints();
				lb.setOpaque(true);
				if((file + rank) % 2 == 1) {
					lb.setBackground(Color.BLACK);
				}
				else {
					lb.setBackground(Color.white);
				}
				c.fill = GridBagConstraints.BOTH;
				c.gridx = column;
				c.gridy = row;
				c.weightx = 1.0/9;
				c.weighty = 1.0/9;
				boardView.add(lb, c);
			}
		}
		/*
		JLabel lbH = new JLabel("h");
		GridBagConstraints cH = new GridBagConstraints();
		cH.fill = GridBagConstraints.VERTICAL;
		cH.gridx = 0;
		cH.gridy = 0;
		boardView.add(lbH, cH);
		
		JLabel lbG = new JLabel("g");
		GridBagConstraints cG = new GridBagConstraints();
		cG.fill = GridBagConstraints.VERTICAL;
		cG.gridx = 0;
		cG.gridy = 1;
		boardView.add(lbG, cG);
		
		JLabel lbF = new JLabel("f");
		GridBagConstraints cF = new GridBagConstraints();
		cF.fill = GridBagConstraints.VERTICAL;
		cF.gridx = 0;
		cF.gridy = 2;
		boardView.add(lbF, cF);
		
		JLabel lbE = new JLabel("e");
		GridBagConstraints cE = new GridBagConstraints();
		cE.fill = GridBagConstraints.VERTICAL;
		cE.gridx = 0;
		cE.gridy = 3;
		boardView.add(lbE, cE);
		
		JLabel lbD = new JLabel("d");
		GridBagConstraints cD = new GridBagConstraints();
		cD.fill = GridBagConstraints.VERTICAL;
		cD.gridx = 0;
		cD.gridy = 4;
		boardView.add(lbD, cD);
		
		JLabel lbC = new JLabel("c");
		GridBagConstraints cC = new GridBagConstraints();
		cC.fill = GridBagConstraints.VERTICAL;
		cC.gridx = 0;
		cC.gridy = 5;
		boardView.add(lbC, cC);
		
		JLabel lbB = new JLabel("b");
		GridBagConstraints cB = new GridBagConstraints();
		cB.fill = GridBagConstraints.VERTICAL;
		cB.gridx = 0;
		cB.gridy = 6;
		boardView.add(lbB, cB);
		
		JLabel lbA = new JLabel("a");
		GridBagConstraints cA = new GridBagConstraints();
		cA.fill = GridBagConstraints.VERTICAL;
		cA.gridx = 0;
		cA.gridy = 7;
		boardView.add(lbA, cA);
		
		JLabel lbEmpty = new JLabel("/");
		GridBagConstraints cEmpty = new GridBagConstraints();
		cEmpty.fill = GridBagConstraints.VERTICAL;
		cEmpty.gridx = 0;
		cEmpty.gridy = 8;
		boardView.add(lbEmpty, cEmpty);
		*/
		
		
		
	}
}
