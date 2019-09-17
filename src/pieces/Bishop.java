package pieces;

import java.util.ArrayList;

import board.Board;
import board.Square;

public class Bishop extends ChessPiece {

	public Bishop(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	void setAttacking() {
		// 
		
		Square square;
		int check = 0;
		
		while (check<4) {
			int file = this.position.getX();
			int rank = this.position.getY();
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
						square.setUnderAttack();
						attacking.add(square);
					}
					break;
				}
				square.setUnderAttack();
				attacking.add(square);
			}
			check++;
		}


	}


}
