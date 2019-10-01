package board;

//import pieces.ChessPiece;
import pieces.*;
//import pieces.Side;
//import pieces.Type;

public class Board {
	
	public final Square[][] squares = new Square[8][8];
	
	public Board() {
		// TODO Auto-generated constructor stub
		for(int file = 0; file < 8; file++) {
			for(int rank = 0; rank < 8; rank++) {
				squares[file][rank] = new Square(this, file, rank);
			}
		}
	}
	
	public void classicalInit() {
		// TODO: do this
		int file = 0;
		int rank = 0;
		Side side;
		Type type;
		for(rank = 0; rank < 8; rank++) {
			for(file = 0; file < 8; file++) {
				if(rank < 2) {
					side = Side.white;
				}
				else if(rank>5){
					side = Side.black;
				}
				else {
					continue;
				}
				if((rank==0 || rank == 7) && (file==0 || file == 7)) {
					type = Type.ROOK;
					this.squares[file][rank].setPiece(new Rook(file, rank, type, side, this));
					continue;
				}
				if((rank==0 || rank == 7) && (file==1 || file == 6)) {
					type = Type.KNIGHT;
					this.squares[file][rank].setPiece(new Knight(file, rank, type, side, this));
					continue;
				}
				if((rank==0 || rank == 7) && (file==2 || file == 5)) {
					type = Type.BISHOP;
					this.squares[file][rank].setPiece(new Bishop(file, rank, type, side, this));
					continue;
				}
				if((rank==0 || rank == 7) && (file==3)) {
					type = Type.QUEEN;
					this.squares[file][rank].setPiece(new Queen(file, rank, type, side, this));
					continue;
				}
				if((rank==0 || rank == 7) && (file==4)) {
					type = Type.KING;
					this.squares[file][rank].setPiece(new King(file, rank, type, side, this));
					continue;
				}
				if((rank==1 || rank == 6)) {
					type = Type.PAWN;
					this.squares[file][rank].setPiece(new Pawn(file, rank, type, side, this));
					continue;
				}
				
			}		
		}
		for(file = 0; file<8; file++) {
			for(rank = 0; rank<8; rank++) {
				 
				if(squares[file][rank].getPiece()!=null) {
					ChessPiece piece = squares[file][rank].getPiece();
					piece.setAttacking();
					piece.setCanMoveTo();
				}
			}
		}
		
	}
	
	public void draw() {
		int file = 0;
		int rank = 0;
		for(rank = 7; rank >= -1; rank--) {
			System.out.printf("%s  |\t", rank+1);
			for(file = 0; file < 8; file++) {
				if(rank==-1) {
					System.out.printf("%s\t", (char)(file + (int)('a')));
					continue;
				}
				if(this.squares[file][rank].getPiece()==null) {
					System.out.printf("#");
				}
				else {
					System.out.printf("%s",this.squares[file][rank].getPiece().toString());
				}
				System.out.printf("\t");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public static void main(String [] args) {
		Board board = new Board();
		board.classicalInit();
		board.draw();
	}
			

}
