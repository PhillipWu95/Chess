package pieces;

import java.util.ArrayList;

import board.Board;
import board.Square;

public class Joker extends ChessPiece {



public Joker(int file, int rank, Type type, Side side, Board board) {
		super(file, rank, type, side, board);
		// TODO Auto-generated constructor stub
	}

//	public Joker(int file, int rank, Side side, Type type) {
//		super(file, rank, type, side);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	boolean moveTo(int rank, int file) {
		// TODO Auto-generated method stub
		if(isValid() && !isBlocked()) {
			return true;
		}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	ArrayList<Square> getAttacking() {
		// TODO Auto-generated method stub
		return null;
	}

}
