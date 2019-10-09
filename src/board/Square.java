package board;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import pieces.ChessPiece;
import pieces.King.KingCapturedException;

public class Square {
	public final int file; //column
	public final int rank; //row
	private ChessPiece piece;
	private ArrayList<ChessPiece> underAttack;
	private Board board;
	private JLabel label;
	
	public Square(int file, int rank) {
		// TODO Auto-generated constructor stub
		this.file = file;
		this.rank = rank;
		piece = null;
		underAttack = new ArrayList<ChessPiece>();
	}
	
	public Square(Board board, int file, int rank) {
		this(file, rank);
		this.board = board;
	}
	
	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}
	
	public void removePiece() {
		this.piece = null;
	}
	
	public void setUnderAttack(ChessPiece piece) {
		this.underAttack.add(piece);
	}
	
	public void resetUnderAttack(ChessPiece piece) {
		this.underAttack.remove(piece);
	}
	
	public void reCalculateUnderAttack() {
//		for(ChessPiece piece : underAttack) {
//			piece.setAttacking();
//			piece.setCanMoveTo();
//		}
		ArrayList<ChessPiece> tempUnderAttack = new ArrayList<ChessPiece>();
		for(ChessPiece piece : underAttack) {
			tempUnderAttack.add(piece);
		}
		underAttack.clear();
		for(ChessPiece piece : tempUnderAttack) {
			piece.setAttacking();
			piece.setCanMoveTo();
		}
	}
	
	public boolean isOccupied() {
		return (this.piece!=null);
	}
	
	public ArrayList<ChessPiece> getUnderAttack() {
		return this.underAttack;
	}
	
	public boolean isUnderAttack(ChessPiece thisPiece) {
		for(ChessPiece otherPiece : underAttack) {
			if(otherPiece.side != thisPiece.side) {
				return true;
			}
		}
		return false;
	}
	
	public ChessPiece getPiece() {
		return this.piece;
	}
	
	public String toString() {
		return "" + this.file + " " + this.rank;
	}
	
	public static void main(String[] args) {
		Square square = new Square(0,0);
		System.out.println(square.toString());
	}
	
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	public JLabel getLabel() {
		return this.label;
	}
	
	public int getFile() {
		return file;
	}
	
	public int getRank() {
		return rank;
	}
	
	public Square leftN(int n) throws ArrayIndexOutOfBoundsException {
		return this.board.squares[this.getFile()-n][this.getRank()];
	}
	
	public Square rightN(int n) throws ArrayIndexOutOfBoundsException {
		return this.board.squares[this.getFile()+n][this.getRank()];
	}
	
	public Square upN(int n) throws ArrayIndexOutOfBoundsException {
		return this.board.squares[this.getFile()][this.getRank()+n];
	}
	
	public Square downN(int n) throws ArrayIndexOutOfBoundsException {
		return this.board.squares[this.getFile()][this.getRank()-n];
	}
	
	public String getImage() {
		return this.piece.getImage();
	}
	
	public boolean moveTo(Square square) throws KingCapturedException {
		boolean result;
		result = this.getPiece().moveTo(square.file, square.rank);
		this.reDraw();
		square.reDraw();
		return result;
	}
	
	public void reDraw() {
		if(this.isOccupied()) {
			ImageIcon imageIcon = new ImageIcon(this.getImage()); // load the image to a imageIcon
			
//			ImageIcon imageIcon = new ImageIcon(getClass().getResource(this.getImage()));
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(82, 82,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			this.label.setIcon(imageIcon);
		}
		else {
			this.label.setIcon(null);
		}
		
	}
	
	public void setBackground() {
		if((file + rank) % 2 == 0) {
			label.setBackground(Color.green);
		}
		else {
			label.setBackground(Color.white);
		}
	}
	
	public void setSelectedBackground() {
		label.setBackground(Color.yellow);
	}

}
