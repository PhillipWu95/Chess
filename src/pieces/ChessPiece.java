package pieces;
import java.util.ArrayList;
import board.Board;
import board.Square;

public abstract class ChessPiece {
	
	protected Position position;
	protected Type type;
	public final Side side;
	protected Board board;
	protected ArrayList<Square> attacking;
	
//	public ChessPiece(int file, int rank, Type type, Side side) {
//		this.position = new Position(file, rank);
//		this.attacking = new ArrayList<Square>();
//		this.side = side;
//		this.type = type;
//		this.setAttacking();
//	}
	
	public ChessPiece(int file, int rank, Type type, Side side, Board board) {
		this.position = new Position(file, rank);
		this.attacking = new ArrayList<Square>();
		this.side = side;
		this.type = type;
		this.board = board;
		this.setAttacking();
		
	}
	
	abstract boolean moveTo(int rank, int file);
	
	abstract boolean isValid();
	
	abstract boolean isBlocked();
	
	abstract void setAttacking();
	
	abstract ArrayList<Square> getAttacking();
	
	boolean isHorizontalMove(int x, int y) {
		if(y-this.position.getY() == 0) {
			return true;
		}
		return false;
	}
	
	boolean isHorizontalMove(Position pos) {
		if(pos.getY()-this.position.getY() == 0) {
			return true;
		}
		return false;
	}
	
	boolean isVerticalMove(int x, int y) {
		if(x-this.position.getX() == 0) {
			return true;
		}
		return false;
	}
	
	boolean isVerticalMove(Position pos) {
		if(pos.getX()-this.position.getX() == 0) {
			return true;
		}
		return false;
	}
	
	boolean isDiagonalMove(int x, int y) {
		return Math.abs(x-this.position.getX()) == Math.abs(y-this.position.getY()); 
	}
	
	boolean isDiagonalMove(Position pos) {
		return Math.abs(pos.getX()-this.position.getX()) == Math.abs(pos.getY()-this.position.getY());
	}
	
	int moveDistance(int x, int y) {
		return Math.max(Math.abs(x-this.position.getX()), Math.abs(y-this.position.getY()));
	}
	
	int moveDistance(Position pos) {
		return Math.max(Math.abs(pos.getX()-this.position.getX()), Math.abs(pos.getY()-this.position.getY()));
	}
	
}
