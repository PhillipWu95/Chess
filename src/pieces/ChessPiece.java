package pieces;
import java.util.ArrayList;
import board.Board;
import board.Square;
import pieces.King.KingCapturedException;

public abstract class ChessPiece {
	
//	protected Position position;
	protected Square square;
	protected Type type;
	public Side side;
	protected Board board;
	protected ArrayList<Square> attacking;
	protected ArrayList<Square> canMoveTo;
	protected boolean hasMoved;
	protected boolean canBeEnPassant;
	
	public ChessPiece() {
		
	}

	public ChessPiece(int file, int rank, Type type, Side side, Board board) {
		this.board = board;
		this.square = this.board.squares[file][rank];
		this.hasMoved = false;
		this.canBeEnPassant = false;
		this.attacking = new ArrayList<Square>();
		this.canMoveTo = new ArrayList<Square>();
		this.side = side;
		this.type = type;
		this.board.squares[file][rank].setPiece(this);
//		this.setAttacking();
//		this.setCanMoveTo();
		
	}
	
//	
//	
//	abstract boolean isValid();
//	
//	abstract boolean isBlocked();
//	
	abstract public void setAttacking();
	
	void resetAttacking() {
		for(Square square : this.attacking) {
			square.resetUnderAttack(this);
		}
		attacking.clear();
	}
	
	ArrayList<Square> getAttacking() {
		return attacking;
	}
	
	public boolean moveTo(int file, int rank) throws KingCapturedException {
		
		Square square = null;
		boolean validMove = false;
		
		for(Square targetSquare : canMoveTo) {
			if(targetSquare.file == file && targetSquare.rank == rank) {
				// if valid move
				validMove = true;
				square = targetSquare;
				
			}
		}
		
		if(validMove) {
			if(square.isOccupied()) {
				square.getPiece().captured();
			}
			this.square.removePiece();
			this.square.reCalculateUnderAttack();
//			this.resetAttacking();
//			this.resetCanMoveTo();
			
			square.setPiece(this);
			square.reCalculateUnderAttack();
			this.square = square;
			this.setAttacking();
			this.setCanMoveTo();
			this.hasMoved = true;
			return true;
		}
		
		return false;
	}
	
	void captured() throws KingCapturedException {
		this.resetAttacking();
		this.resetCanMoveTo();
		this.square.removePiece();
	}
	
	public void setCanMoveTo() {
		resetCanMoveTo();
		for(Square square : attacking) {
			if(square.isOccupied() && square.getPiece().side==this.side) {
				continue;
			}
			canMoveTo.add(square);
		}
	}
	
	void resetCanMoveTo() {
		canMoveTo.clear();
	}
	
	abstract public String getImage();
}
