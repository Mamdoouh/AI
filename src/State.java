
public class State {

	private int i, j;
	private Orientation orientation;
	
	public State(int i, int j, Orientation orientation){
		this.i = i;
		this.j = j;
		this.orientation = orientation;
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