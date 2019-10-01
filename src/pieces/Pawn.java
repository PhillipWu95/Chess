package pieces;

import java.util.ArrayList;
import java.util.Scanner;
import board.Board;
import board.Square;
import pieces.King.KingCapturedException;

public class Pawn extends ChessPiece {
	
	private ArrayList<Square> enPassantMove;
	private ArrayList<Square> doubleAdvance;
	
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
	
	
	
	public Pawn(int file, int rank, Type type, Side side, Board board) {
//		super(file, rank, type, side, board);
		this.enPassantMove = new ArrayList<Square>();
		this.doubleAdvance = new ArrayList<Square>();
		this.board = board;
		this.square = this.board.squares[file][rank];
		this.hasMoved = false;
		this.canBeEnPassant = false;
		this.attacking = new ArrayList<Square>();
		this.canMoveTo = new ArrayList<Square>();
		this.side = side;
		this.type = type;
		this.board.squares[file][rank].setPiece(this);
		this.setAttacking();
		this.setCanMoveTo();
	}
	
	@Override
	public boolean moveTo(int file, int rank) throws KingCapturedException {
		
		boolean validMove = false;
		Square square = null;
		
		this.setAttacking();
		this.setCanMoveTo();
		
		ArrayList<Square> allCanMoveTo = new ArrayList<Square>();
		allCanMoveTo.addAll(canMoveTo);
		allCanMoveTo.addAll(enPassantMove);
		allCanMoveTo.addAll(doubleAdvance);
		
		
		
		
		for(Square targetSquare : allCanMoveTo) {
			System.out.println("Can Move to:");
			System.out.println(targetSquare.file+" "+targetSquare.rank);
		}
		
		for(Square targetSquare : allCanMoveTo) {
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
			
			//double advancement check
			if(doubleAdvance.contains(square)) {
				this.canBeEnPassant = true;
			} else {
				this.canBeEnPassant = false;
			}
			
			//enpassant attack check
			if(enPassantMove.contains(square)) {
					(this.side==Side.white ? square.downN(1) : square.upN(1))
				.getPiece().captured();
			}
			
//			if(attacking.contains(square)) {
//				square.getPiece().captured();
//			}
			
			
			this.square.removePiece();
			this.square.reCalculateUnderAttack();
			
			
			square.setPiece(this);
			this.square = square;
			this.square.reCalculateUnderAttack();
			this.setAttacking();
			this.setCanMoveTo();
			this.hasMoved = true;
			
			//TODO: pawn promotion
			if(this.endOfLine()) {
				this.pawnPromotion();
			}

			return true;
		}
		
		
		return false;
	}

	@Override
	public void setAttacking() {
		Square square;
		this.resetAttacking();
		int thisFile = this.square.getFile();
		int thisRank = this.square.getRank();
		if(this.side==Side.white) {
			try {
				square = this.board.squares[thisFile-1][thisRank+1];
//				if(square.isOccupied()) {
//					if(square.getPiece().side==this.side) {
//						throw new AttackingSameSideException();
//					}
//				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			try {
				square = this.board.squares[thisFile+1][thisRank+1];
//				if(square.isOccupied()) {
//					if(square.getPiece().side==this.side) {
//						throw new AttackingSameSideException();
//					}
//				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
		} else if(this.side==Side.black) {
			try {
				square = this.board.squares[thisFile-1][thisRank-1];
//				if(square.isOccupied()) {
//					if(square.getPiece().side==this.side) {
//						throw new AttackingSameSideException();
//					}
//				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			try {
				square = this.board.squares[thisFile+1][thisRank-1];
//				if(square.isOccupied()) {
//					if(square.getPiece().side==this.side) {
//						throw new AttackingSameSideException();
//					}
//				}
				square.setUnderAttack(this);
				this.attacking.add(square);
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	} // end setAttacking()
	
	public void setCanMoveTo() {
		this.resetCanMoveTo();
		for(Square square : attacking) {
			if(square.isOccupied() && square.getPiece().side!=this.side) {
				canMoveTo.add(square);
			}
		}
		addAdvance();
		addDoubleAdvance();
		addEnPassantMove();
	}
	
	public void addAdvance() {
		if(this.side==Side.white && !this.square.upN(1).isOccupied()) {
			this.canMoveTo.add(this.square.upN(1));
		}
		else if(this.side==Side.black && !this.square.downN(1).isOccupied()) {
			this.canMoveTo.add(this.square.downN(1));
		}
	}
	
	public void addDoubleAdvance() {
		doubleAdvance.clear();
		if(this.hasMoved) {
			return;
		}
		
		if(this.side == Side.white) {
			if(!(this.square.upN(1).isOccupied() || this.square.upN(2).isOccupied())) {
				if(!this.hasMoved) {
					this.doubleAdvance.add(this.square.upN(2));
				} else {
					this.doubleAdvance.clear();
				}
			}
		} else if(this.side == Side.black) {
			if(!(this.square.downN(1).isOccupied() || this.square.downN(2).isOccupied())) {
				if(!this.hasMoved) {
					this.doubleAdvance.add(this.square.downN(2));
				} else {
					this.doubleAdvance.clear();
				}
			}
		}
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
	
	public boolean endOfLine() {
		return 
			this.side == Side.white ? (this.square.getRank()==7) : (this.square.getRank()==0);  
	}
	
	public void pawnPromotion() {
		Type type = getUserTypeInput();
		this.type = type;
		this.resetAttacking();
		this.setAttacking();
	}
	
	public Type getUserTypeInput() {
		// TODO: todo
		Scanner scanner = new Scanner(System.in);
		String typeStr = scanner.nextLine();
		Type type;
		while(true) {
			try {
				type = Type.valueOf(typeStr.toUpperCase());
				break;
			} catch (Exception e) {
				continue;
			}
		}
		scanner.close();
		return type;
	}
	
	public String toString() {
		return "P";
	}
	
}
