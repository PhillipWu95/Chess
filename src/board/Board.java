package board;

public class Board {
	
	public final Square[][] square = new Square[8][8];
	
	public Board() {
		// TODO Auto-generated constructor stub
		for(int file = 0; file < 8; file++) {
			for(int rank = 0; rank < 8; rank++) {
				square[file][rank] = new Square(this, file, rank);
			}
		}
	}
	
//	public Square leftNOf(Square square, int n) {
//		return this.square[square.getFile()-n][square.getRank()];
//	}
//
//	public Square rightNOf(Square square, int n) {
//		return this.square[square.getFile()+n][square.getRank()];
//	}
//	
//	public Square upNOf(Square square, int n) {
//		return this.square[square.getFile()][square.getRank()+n];
//	}
//	
//	public Square downNOf(Square square, int n) {
//		return this.square[square.getFile()][square.getRank()-n];
//	}
//	

}
