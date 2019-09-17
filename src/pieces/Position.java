package pieces;

public class Position {
	private int X;
	private int Y;
	
	public Position(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public Position(Position pos) {
		this.X = pos.getX();
		this.Y = pos.getY();
	}
	
	public void set(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public void set(Position pos) {
		this.X = pos.getX();
		this.Y = pos.getY();
	}
	
	public void setX(int x) {
		this.X = x;
	}
	
	public void setY(int y) {
		this.Y = y;
	}	
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public int getLinfNorm(Position pos) {
		int xNorm = Math.abs(pos.getX()-this.X);
		int yNorm = Math.abs(pos.getY()-this.Y);
		return Math.max(xNorm, yNorm);
	}
	
	public int getLinfNorm(int x, int y) {
		int xNorm = Math.abs(x-this.X);
		int yNorm = Math.abs(y-this.Y);
		return Math.max(xNorm, yNorm);
	}
}
