package pieces;

import java.util.ArrayList;

import board.Board;
import board.Square;

public class Rook extends ChessPiece {

	

//	public Rook(int file, int rank, Type type, Side side) {
//		super(file, rank, type, side);
////		this.setAttacking();
//		// TODO Auto-generated constructor stub
//	}
	
	public Rook(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
//		this.setAttacking();
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean moveTo(int rank, int file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isBlocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void setAttacking() {
		
		Square square;
		int check = 0;
		
		while (check<4) {
			int file = this.position.getX();
			int rank = this.position.getY();
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

	@Override
	ArrayList<Square> getAttacking() {
		// TODO Auto-generated method stub
		return attacking;
	}

	public static void main(String args[]) {
		Board newBoard = new Board();
		ChessPiece newPiece = new Rook(3,5,Type.Rook,Side.white,newBoard);
		for(Square square:newPiece.attacking) {
			System.out.println(square.toString());
		}
	}
}
