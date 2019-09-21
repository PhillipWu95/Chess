package pieces;
import java.util.ArrayList;
import board.Board;
import board.Square;

public abstract class ChessPiece {
	
//	protected Position position;
	protected Square square;
	protected Type type;
	public final Side side;
	protected Board board;
	protected ArrayList<Square> attacking;
	protected ArrayList<Square> canMoveTo;
	protected boolean hasMoved;
	protected boolean canBeEnPassant;
	

	public ChessPiece(int file, int rank, Type type, Side side, Board board) {
		this.square = this.board.square[file][rank];
		this.hasMoved = false;
		this.canBeEnPassant = false;
		this.attacking = new ArrayList<Square>();
		this.canMoveTo = new ArrayList<Square>();
		this.side = side;
		this.type = type;
		this.board = board;
		this.board.square[file][rank].setPiece(this);
		this.setAttacking();
		this.setCanMoveTo();
		
	}
	
//	
//	
//	abstract boolean isValid();
//	
//	abstract boolean isBlocked();
//	
	abstract void setAttacking();
	
	void resetAttacking() {
		attacking.clear();
	}
	
	ArrayList<Square> getAttacking() {
		return attacking;
	}
	
	boolean moveTo(int file, int rank) {
		for(Square square : canMoveTo) {
			if(square.file == file && square.rank == rank) {
				// if valid move
				if(square.isOccupied()) {
					square.getPiece().captured();
				}
				this.square.removePiece();
				this.resetAttacking();
				this.resetCanMoveTo();
				
				square.setPiece(this);
				this.square = square;
				this.setAttacking();
				this.setCanMoveTo();
				this.hasMoved = true;
				return true;
			}
		}
		return false;
	}
	
	void captured() {
		this.square.removePiece();
	}
	
	void setCanMoveTo() {
		for(Square square : attacking) {
			canMoveTo.add(square);
		}
	}
	
	void resetCanMoveTo() {
		canMoveTo.clear();
	}
//	
//	boolean isHorizontalMove(int x, int y) {
//		if(y-this.position.getY() == 0) {
//			return true;
//		}
//		return false;
//	}
//	
//	boolean isHorizontalMove(Position pos) {
//		if(pos.getY()-this.position.getY() == 0) {
//			return true;
//		}
//		return false;
//	}
//	
//	boolean isVerticalMove(int x, int y) {
//		if(x-this.position.getX() == 0) {
//			return true;
//		}
//		return false;
//	}
//	
//	boolean isVerticalMove(Position pos) {
//		if(pos.getX()-this.position.getX() == 0) {
//			return true;
//		}
//		return false;
//	}
//	
//	boolean isDiagonalMove(int x, int y) {
//		return Math.abs(x-this.position.getX()) == Math.abs(y-this.position.getY()); 
//	}
//	
//	boolean isDiagonalMove(Position pos) {
//		return Math.abs(pos.getX()-this.position.getX()) == Math.abs(pos.getY()-this.position.getY());
//	}
//	
//	int moveDistance(int x, int y) {
//		return Math.max(Math.abs(x-this.position.getX()), Math.abs(y-this.position.getY()));
//	}
//	
//	int moveDistance(Position pos) {
//		return Math.max(Math.abs(pos.getX()-this.position.getX()), Math.abs(pos.getY()-this.position.getY()));
//	}
//	
}
