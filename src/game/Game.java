package game;

import board.Board;
import board.Square;
import pieces.King.KingCapturedException;
import pieces.Side;
import java.util.Scanner;

public class Game {
	
	private Board board;
	private Player playerWhite;
	private Player playerBlack;
	private Scanner keyboard = new Scanner(System.in);
	
	public Game() {
		this.board = new Board();
		playerBlack = new Player("black", Side.black, this.board);
		playerWhite = new Player("white", Side.white, this.board);
	}
	
	public Game(Player playerBlack, Player playerWhite) {
		this.board = new Board();
		this.playerBlack = playerBlack;
		this.playerWhite = playerWhite;
	}
	
	
	public void startClassical() {
		this.board.classicalInit();
		String from, to;
		
		while(true) {
			
			try {
				this.board.draw();
				System.out.println("White move:");
				from = keyboard.next();
				to = keyboard.next();
				playerWhite.move(from, to);
				
				this.board.draw();
				System.out.println("Black move:");
				from = keyboard.next();
				to = keyboard.next();
				playerBlack.move(from, to);
			} catch (KingCapturedException e) {
				System.out.println("Game over");
				break;
			} catch (Exception e) {
				System.out.println(e.toString());
				continue;
			}
			
		}
		
	}
	
	public static void main(String [] args) {
		Game newGame = new Game();
		newGame.startClassical();
	}
	// TODO: The problem right now is that a piece can move into a square
	// and change the square and some other squares' underattack status
	// Need to add a list for each square recording which piece is attacking it
	// once a piece move into a square, all the pieces that's attacking it 
	// must recalculate their attacking
	
}
