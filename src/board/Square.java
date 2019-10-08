package board;

import java.util.ArrayList;

import pieces.ChessPiece;

public class Square {
	public final int file; //column
	public final int rank; //row
	private ChessPiece piece;
	private ArrayList<ChessPiece> underAttack;
	private Board board;
	
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

}
