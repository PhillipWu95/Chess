package pieces;

import java.util.ArrayList;

import board.Board;
import board.Square;

public class Rook extends ChessPiece {

	
	public Rook(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
	}


	@Override
	void setAttacking() {
		
		Square square;
		int check = 0;
		
		while (check<4) {
			int file = this.square.getFile();
			int rank = this.square.getRank();
			while(rank>=0 && rank<8 && file>=0 & file<8) {
				switch(check) {
				case 0: file--; // check left
					break;
				case 1: file++; // check right
					break;
				case 2: rank--; // check down
					break;
				case 3: rank++; // check up
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
		
	

	public static void main(String args[]) {
		Board newBoard = new Board();
		ChessPiece newPiece = new Rook(0,0,Type.Rook,Side.white,newBoard);
		for(Square square:newPiece.attacking) {
			System.out.println(square.toString());
		}
		
		System.out.println(newPiece.moveTo(0,7));
		
		for(Square square:newPiece.attacking) {
			System.out.println(square.toString());
		}
	}

	
}
