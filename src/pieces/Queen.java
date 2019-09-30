package pieces;

import board.Board;
import board.Square;

public class Queen extends ChessPiece {

	public Queen(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	void setAttacking() {
		// 
		
		Square square;
		int check = 0;
		
		while (check<8) {
			int file = this.square.getFile();
			int rank = this.square.getRank();
			while(rank>=0 && rank<8 && file>=0 & file<8) {
				switch(check) {
				case 0: 
					file--; // check left up
					rank++;
					break;
				case 1: 
					file++; // check right up
					rank++;
					break;
				case 2: 
					file--;
					rank--; // check left down
					break;
				case 3:
					file++;
					rank--; // check right down
					break;
				case 4: file--; // check left
					break;
				case 5: file++; // check right
					break;
				case 6: rank--; // check down
					break;
				case 7: rank++; // check up
					break;
				default:
					break;
				}
				try {
					square = board.square[file][rank];
				} catch(Exception e) {
					break;
				}
				
				if(square.isOccupied()) {
					if(square.getPiece().side!=this.side) {
						square.setUnderAttack(this);
						attacking.add(square);
					}
					break;
				}
				square.setUnderAttack(this);
				attacking.add(square);
			}
			check++;
		}

	}
	
	public String toString() {
		return "Q";
	}

}
