package game;
import board.Board;
import pieces.King.KingCapturedException;
import pieces.Side;

public class Player {
	
	private Side side;
	private String userName;
	private Board board;
	
	
	
	public Player(String userName, Side side, Board board) {
		this.userName = userName;
		this.side = side;
		this.board = board;
	}
	
	public void move(String from, String to) throws KingCapturedException {
		int fromFile;
		int fromRank;
		int toFile;
		int toRank;
		fromFile = Character.getNumericValue(from.charAt(0)) - Character.getNumericValue('a');
		fromRank = Character.getNumericValue(from.charAt(1)) - Character.getNumericValue('1');
		toFile = Character.getNumericValue(to.charAt(0)) - Character.getNumericValue('a');
		toRank = Character.getNumericValue(to.charAt(1)) - Character.getNumericValue('1');
		this.board.squares[fromFile][fromRank].getPiece().moveTo(toFile, toRank);
	}

}
