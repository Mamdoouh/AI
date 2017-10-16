
public class State {

	private int i, j;
	private Orientation orientation;
	private int remainingPads;
	
	public int getRemainingPads() {
		return remainingPads;
	}

	public void setRemainingPads(int remainingPads) {
		this.remainingPads = remainingPads;
	}

	public State(int i, int j, Orientation orientation, int reminaingPads){
		this.i = i;
		this.j = j;
		this.orientation = orientation;
		this.remainingPads = reminaingPads;
	}
	
	// Getters and setters
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
}
