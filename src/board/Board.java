package board;

public class Board {
	
	public final Square[][] square = new Square[8][8];
	
	public Board() {
		// TODO Auto-generated constructor stub
		for(int file = 0; file < 8; file++) {
			for(int rank = 0; rank < 8; rank++) {
				square[file][rank] = new Square(file, rank);
			}
		}
	}
	
	

}
