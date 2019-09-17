package board;
import pieces.ChessPiece;

public class Square {
	public final int file; //column
	public final int rank; //row
	private ChessPiece piece;
	private boolean underAttack;
	
	public Square(int file, int rank) {
		// TODO Auto-generated constructor stub
		this.file = file;
		this.rank = rank;
		piece = null;
		underAttack = false;
	}
	
	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}
	
	public void removePiece() {
		this.piece = null;
	}
	
	public void setUnderAttack() {
		this.underAttack = true;
	}
	
	public void resetUnderAttack() {
		this.underAttack = false;
	}
	
	public boolean isOccupied() {
		return (this.piece!=null);
	}
	
	public boolean getUnderAttack() {
		return this.underAttack;
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
