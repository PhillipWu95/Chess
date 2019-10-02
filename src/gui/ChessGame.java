package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
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
		frame.setBounds(100, 100, 711, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel background = new JPanel();
		frame.getContentPane().add(background);
		background.setLayout(null);
		
		JPanel options = new JPanel();
		options.setBounds(12, 12, 114, 434);
		background.add(options);
		
		JPanel boardView = new JPanel();
		boardView.setBounds(138, 12, 561, 434);
		background.add(boardView);
		boardView.setLayout(new GridLayout(9, 9, 0, 0));
		
		JLabel lbH = new JLabel("h");
		GridBagConstraints cH = new GridBagConstraints();
		cH.fill = GridBagConstraints.BOTH;
		cH.gridx = 0;
		cH.gridy = 0;
		boardView.add(lbH, cH);
		
		JLabel lbG = new JLabel("g");
		GridBagConstraints cG = new GridBagConstraints();
		cG.fill = GridBagConstraints.BOTH;
		cG.gridx = 0;
		cG.gridy = 1;
		boardView.add(lbG, cG);
		
		JLabel lbF = new JLabel("f");
		GridBagConstraints cF = new GridBagConstraints();
		cF.fill = GridBagConstraints.BOTH;
		cF.gridx = 0;
		cF.gridy = 2;
		boardView.add(lbF, cF);
		
		JLabel lbE = new JLabel("e");
		GridBagConstraints cE = new GridBagConstraints();
		cE.fill = GridBagConstraints.BOTH;
		cE.gridx = 0;
		cE.gridy = 3;
		boardView.add(lbE, cE);
		
		JLabel lbD = new JLabel("d");
		GridBagConstraints cD = new GridBagConstraints();
		cD.fill = GridBagConstraints.BOTH;
		cD.gridx = 0;
		cD.gridy = 4;
		boardView.add(lbD, cD);
		
		JLabel lbC = new JLabel("c");
		GridBagConstraints cC = new GridBagConstraints();
		cC.fill = GridBagConstraints.BOTH;
		cC.gridx = 0;
		cC.gridy = 5;
		boardView.add(lbC, cC);
		
		JLabel lbB = new JLabel("b");
		GridBagConstraints cB = new GridBagConstraints();
		cB.fill = GridBagConstraints.BOTH;
		cB.gridx = 0;
		cB.gridy = 6;
		boardView.add(lbB, cB);
		
		JLabel lbA = new JLabel("a");
		GridBagConstraints cA = new GridBagConstraints();
		cA.fill = GridBagConstraints.BOTH;
		cA.gridx = 0;
		cA.gridy = 7;
		boardView.add(lbA, cA);
		
		JLabel lbEmpty = new JLabel("/");
		GridBagConstraints cEmpty = new GridBagConstraints();
		cEmpty.fill = GridBagConstraints.BOTH;
		cEmpty.gridx = 0;
		cEmpty.gridy = 1;
		boardView.add(lbEmpty, cEmpty);
		
		
		
		
	}
}
