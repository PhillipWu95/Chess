package pieces;

import java.util.ArrayList;

import board.Board;
import board.Square;

public class Pawn extends ChessPiece {
	
	public class AttackingSameSideException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 2676386449652593465L;

		public AttackingSameSideException() {
			super("Attacking same side");
		}
		
		public AttackingSameSideException(String str) {
			super(str);
		}
		
	}
	
	private ArrayList<Square> enPassantMove;
	
	public Pawn(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	boolean moveTo(int file, int rank) {
		ArrayList<Square> allCanMoveTo = new ArrayList<Square>();
		allCanMoveTo.addAll(canMoveTo);
		allCanMoveTo.addAll(enPassantMove);
		
		for(Square square : allCanMoveTo) {
			if(square.file == file && square.rank == rank) {
				// if valid move
				if(square.isOccupied()) {
					square.getPiece().captured();
				}
				this.board.square[this.position.getX()][this.position.getY()].removePiece();
				this.resetAttacking();
				this.resetCanMoveTo();
				
				this.position.set(file, rank);
				square.setPiece(this);
				this.setAttacking();
				this.setCanMoveTo();
				this.hasMoved = true;
				return true;
			}
		}
		return false;
	}

	@Override
	void setAttacking() {
		// TODO Auto-generated method stub
		Square square;
		int thisFile = this.position.getX();
		int thisRank = this.position.getY();
		if(this.side==Side.white) {
			try {
				square = this.board.square[thisFile-1][thisRank+1];
				if(square.isOccupied()) {
					if(square.getPiece().side==this.side) {
						throw new AttackingSameSideException();
					}
				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			try {
				square = this.board.square[thisFile+1][thisRank+1];
				if(square.isOccupied()) {
					if(square.getPiece().side==this.side) {
						throw new AttackingSameSideException();
					}
				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
		} else if(this.side==Side.black) {
			try {
				square = this.board.square[thisFile-1][thisRank-1];
				if(square.isOccupied()) {
					if(square.getPiece().side==this.side) {
						throw new AttackingSameSideException();
					}
				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			try {
				square = this.board.square[thisFile+1][thisRank-1];
				if(square.isOccupied()) {
					if(square.getPiece().side==this.side) {
						throw new AttackingSameSideException();
					}
				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	} // end setAttacking()
	
	void setCanMoveTo() {
		for(Square square : attacking) {
			canMoveTo.add(square);
		}
		
		setEnPassantMove();
	}
	
	void setEnPassantMove() {
		int thisFile = this.position.getX();
		int thisRank = this.position.getY();
		if(this.side==Side.white && this.position.getY()==4) {
			try {
//				if(this.board.leftNOf(this.square, n))
			}
		}
	}
	
	
}
