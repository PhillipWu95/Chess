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
		playerBlack = new Player("black", Side.black);
		playerWhite = new Player("white", Side.white);
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
				System.out.println("White move:");
				from = keyboard.next();
				to = keyboard.next();
				playerWhite.move(from, to);
				
				System.out.println("White move:");
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
	
	
}
