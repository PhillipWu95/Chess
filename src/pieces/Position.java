package pieces;

public class Position {
	private int X;
	private int Y;
	
	public Position(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public void set(int x, int y) {
		this.X = x;
		this.Y = y;
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
}
