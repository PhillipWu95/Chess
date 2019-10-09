package pieces;

import board.Board;
import board.Square;

public class King extends ChessPiece{
	
	public class KingCapturedException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Side side;
		
		KingCapturedException(Side side) {
			this.side = side;
		}
		
	}

	public King(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAttacking() {
		// TODO Auto-generated method stub
		Square square;
		this.resetAttacking();
		
		int thisFile = this.square.getFile();
		int thisRank = this.square.getRank();

		for(int file = 0; file < 8; file++) {
			for(int rank = 0; rank < 8; rank++) {
				int fileDis = Math.abs(thisFile-file);
				int rankDis = Math.abs(thisRank-rank);
				

				if(Math.max(fileDis, rankDis)==1) {
					square = this.board.squares[file][rank];
//					if(square.isOccupied()) {
//						if(square.getPiece().side==this.side) {
//							continue;
//						}
//					}
					for(ChessPiece piece : square.getUnderAttack()) {
						if (piece.side != this.side) {
//							System.out.println("Square under attack");
							continue;
							
						}
					}
//					if(square.getUnderAttack().contains(this.side.other())) {
//						continue;
//					}
					square.setUnderAttack(this);
					this.attacking.add(square);
				}
				
			}
			
		}

	}
	
	@Override
	public void captured() throws KingCapturedException{
		this.square.removePiece();
		throw new KingCapturedException(this.side);
	}
	
	@Override
	public boolean moveTo(int file, int rank) throws KingCapturedException {
		
		boolean validMove = false;
		Square square = null;
		this.setAttacking();
		this.setCanMoveTo();
		
		
		for(Square targetSquare : canMoveTo) {
			if(targetSquare.file == file && targetSquare.rank == rank) {
				// if valid move
				validMove = true;
				square = targetSquare;
			}
		}
		
		if(validMove) {

			if(!this.hasMoved) {
				// castle check
				if(this.side==Side.white) {
					if(file==2 && rank==0) {
						this.board.squares[0][0].getPiece().moveTo(3,0);
						this.board.squares[0][0].reDraw();
						this.board.squares[3][0].reDraw();
					}
					if(file==6 && rank==0) {
						this.board.squares[7][0].getPiece().moveTo(5,0);
						this.board.squares[7][0].reDraw();
						this.board.squares[5][0].reDraw();
					}
				} else if(this.side==Side.black) {
					if(file==2 && rank==7) {
						this.board.squares[0][7].getPiece().moveTo(3,7);
						this.board.squares[0][7].reDraw();
						this.board.squares[3][7].reDraw();
					}
					if(file==6 && rank==0) {
						this.board.squares[7][7].getPiece().moveTo(5,7);
						this.board.squares[7][7].reDraw();
						this.board.squares[5][7].reDraw();
					}
				}
			}
			
			if(square.isOccupied()) {
				square.getPiece().captured();
			}
//			this.square.removePiece();
//			this.resetAttacking();
//			this.resetCanMoveTo();
//			
//			
//			square.setPiece(this);
//			this.square = square;
//			this.setAttacking();
//			this.setCanMoveTo();
//			this.hasMoved = true;
//			return true;
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
	
	public void setCanMoveTo() {
		for(Square square : attacking) {
			boolean canMove = true;
			for(ChessPiece piece : square.getUnderAttack()) {
				if(piece.side != this.side) {
					canMove = false;
				}
			}
			if(square.isOccupied() && square.getPiece().side == this.side) {
				canMove = false;
			}
			if(canMove) {
				this.canMoveTo.add(square);
			}
			
		}
		if(!this.hasMoved) {
			this.addCastleMove();
		}		
	}
	
	void addCastleMove() {
		if(this.hasMoved) {
			return;
		}
		if(this.side == Side.white) {
			Square leftRookSquare = this.board.squares[0][0];
			Square rightRookSquare = this.board.squares[7][0];
			if(leftRookSquare.isOccupied() 
			&& leftRookSquare.getPiece().type == Type.ROOK
			&& (!leftRookSquare.getPiece().hasMoved)
			&& (!this.board.squares[1][0].isOccupied())
			&& (!this.board.squares[2][0].isOccupied())
			&& (!this.board.squares[2][0].isUnderAttack(this))
			&& (!this.board.squares[3][0].isOccupied())
			&& (!this.board.squares[3][0].isUnderAttack(this))) {
				canMoveTo.add(this.board.squares[2][0]);
			}
			if(rightRookSquare.isOccupied() 
			&& rightRookSquare.getPiece().type == Type.ROOK
			&& (!rightRookSquare.getPiece().hasMoved)
			&& (!this.board.squares[5][0].isOccupied())
			&& (!this.board.squares[5][0].isUnderAttack(this))
			&& (!this.board.squares[6][0].isOccupied())
			&& (!this.board.squares[6][0].isUnderAttack(this))) {
				canMoveTo.add(this.board.squares[6][0]);
			}
		} else if(this.side == Side.white) {
			Square leftRookSquare = this.board.squares[0][7];
			Square rightRookSquare = this.board.squares[7][7];
			if(leftRookSquare.isOccupied() 
			&& leftRookSquare.getPiece().type == Type.ROOK
			&& (!leftRookSquare.getPiece().hasMoved)
			&& (!this.board.squares[1][7].isOccupied())
			&& (!this.board.squares[2][7].isOccupied())
			&& (!this.board.squares[2][7].isUnderAttack(this))
			&& (!this.board.squares[3][7].isOccupied())
			&& (!this.board.squares[3][7].isUnderAttack(this))) {
				canMoveTo.add(this.board.squares[1][7]);
			}
			if(rightRookSquare.isOccupied() 
			&& rightRookSquare.getPiece().type == Type.ROOK
			&& (!rightRookSquare.getPiece().hasMoved)
			&& (!this.board.squares[5][7].isOccupied())
			&& (!this.board.squares[5][7].isUnderAttack(this))
			&& (!this.board.squares[6][7].isOccupied())
			&& (!this.board.squares[6][7].isUnderAttack(this))) {
				canMoveTo.add(this.board.squares[6][7]);
			}
		} 
	}
	
	public String toString() {
		return "+";
	}
	
	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		if(this.side == Side.black) {
			return "./resource/chess_piece_black_king_T.png";
		}
		else {
			return "./resource/chess_piece_white_king_T.png";
		}

	}
	
}
