package board;

import java.util.ArrayList;

import pieces.ChessPiece;

public class Square {
	public final int file; //column
	public final int rank; //row
	private ChessPiece piece;
	private ArrayList<pieces.Side> underAttack;
	
	public Square(int file, int rank) {
		// TODO Auto-generated constructor stub
		this.file = file;
		this.rank = rank;
		piece = null;
		underAttack = new ArrayList<pieces.Side>();
	}
	
	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}
	
	public void removePiece() {
		this.piece = null;
	}
	
	public void setUnderAttack(pieces.ChessPiece piece) {
		this.underAttack.add(piece.side);
	}
	
	public void resetUnderAttack(pieces.ChessPiece piece) {
		this.underAttack.remove(piece.side);
	}
	
	public boolean isOccupied() {
		return (this.piece!=null);
	}
	
	public ArrayList<pieces.Side> getUnderAttack() {
		return this.underAttack;
	}
	
	public boolean isUnderAttack(pieces.ChessPiece piece) {
		if(underAttack.contains(piece.side.other())) {
			return true;
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

}
