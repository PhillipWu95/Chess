package pieces;

import board.Board;
import board.Square;

public class Knight extends ChessPiece {

	public Knight(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	void setAttacking() {
		// TODO Auto-generated method stub
		Square square;
		
		int thisFile = this.square.getFile();
		int thisRank = this.square.getRank();

		for(int file = 0; file < 8; file++) {
			for(int rank = 0; rank < 8; rank++) {
				int fileDis = Math.abs(thisFile-file);
				int rankDis = Math.abs(thisRank-rank);
				

				if((fileDis==1 && rankDis==2) || (fileDis==2 && rankDis==1)) {
					square = this.board.square[file][rank];
					if(square.isOccupied()) {
						if(square.getPiece().side==this.side) {
							continue;
						}
					}
					square.setUnderAttack(this);
					this.attacking.add(square);
				}
				
			}
			
		}
		
	}
	
	public String toString() {
		return "K";
	}

}
