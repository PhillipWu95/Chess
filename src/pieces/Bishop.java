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
	public void setAttacking() {
		// 
		this.resetAttacking();
		Square square;
		int check = 0;
		
		while (check<4) {
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
				default:
					break;
				}
				try {
					square = board.squares[file][rank];
				} catch(Exception e) {
					break;
				}
				
				if(square.isOccupied()) {
//					if(square.getPiece().side!=this.side) {
					square.setUnderAttack(this);
					attacking.add(square);
//					}
					break;
				}
				square.setUnderAttack(this);
				attacking.add(square);
			}
			check++;
		}

	}
	
	
	public String toString() {
		return "A";
	}
	
	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		if(this.side == Side.black) {
			return "resource/chess_piece_black_bishop_T.png";
		}
		else {
			return "resource/chess_piece_white_bishop_T.png";
		}

	}
	
	

}
