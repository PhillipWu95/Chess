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
	}
	
	@Override
	public boolean moveTo(int file, int rank) {
		ArrayList<Square> allCanMoveTo = new ArrayList<Square>();
		allCanMoveTo.addAll(canMoveTo);
		allCanMoveTo.addAll(enPassantMove);
		
		for(Square square : canMoveTo) {
			if(square.file == file && square.rank == rank) {
				// if valid move
				if(square.isOccupied()) {
					square.getPiece().captured();
				}
				this.board.square[this.square.getFile()][this.square.getRank()].removePiece();
				this.resetAttacking();
				this.resetCanMoveTo();
				
				
				square.setPiece(this);
				this.square = square;
				this.setAttacking();
				this.setCanMoveTo();
				this.hasMoved = true;
				//TODO: pawn promotion
				return true;
			}
		}
		
		for(Square square : enPassantMove) {
			if(square.file == file && square.rank == rank) {
				// if valid move
				if(square.isOccupied()) {
					square.getPiece().captured();
				}
				this.board.square[this.square.getFile()][this.square.getRank()].removePiece();
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

	@Override
	public void setAttacking() {
		Square square;
		int thisFile = this.square.getFile();
		int thisRank = this.square.getRank();
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
	
	public void setCanMoveTo() {
		for(Square square : attacking) {
			if(square.isOccupied() && square.getPiece().side!=this.side) {
				canMoveTo.add(square);
			}
		}
	
		
		addEnPassantMove();
	}
	
	public void addEnPassantMove() {
		enPassantMove.clear();
		
		if(this.side==Side.white && this.square.getRank()==4) {
			try {
				Square leftSquare = this.square.leftN(1);
				if(leftSquare.getPiece().side!=this.side && leftSquare.getPiece().canBeEnPassant) {
					enPassantMove.add(this.square.leftN(1).upN(1));
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("boundary piece");
			} catch (NullPointerException e) {
				System.out.println("No neighbouring piece");
			}
			
			try {
				Square rightSquare = this.square.rightN(1);
				if(rightSquare.getPiece().side!=this.side && rightSquare.getPiece().canBeEnPassant) {
					enPassantMove.add(this.square.rightN(1).upN(1));
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("boundary piece");
			} catch (NullPointerException e) {
				System.out.println("No neighbouring piece");
			}
		}
		
		if(this.side==Side.black && this.square.getRank()==3) {
			try {
				Square leftSquare = this.square.leftN(1);
				if(leftSquare.getPiece().side!=this.side && leftSquare.getPiece().canBeEnPassant) {
					enPassantMove.add(this.square.leftN(1).downN(1));
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("boundary piece");
			} catch (NullPointerException e) {
				System.out.println("No neighbouring piece");
			}
			
			try {
				Square rightSquare = this.square.rightN(1);
				if(rightSquare.getPiece().side!=this.side && rightSquare.getPiece().canBeEnPassant) {
					enPassantMove.add(this.square.rightN(1).downN(1));
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("boundary piece");
			} catch (NullPointerException e) {
				System.out.println("No neighbouring piece");
			}
		}
	}
	

	
	
}
