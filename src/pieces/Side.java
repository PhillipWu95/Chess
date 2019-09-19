package pieces;

public enum Side {
	black,
	white;
	
	public Side other() {
		return (this==Side.white) ? Side.black : Side.white;
	}
}
