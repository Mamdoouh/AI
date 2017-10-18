
public class State {

	private int i, j;
	private Orientation orientation;
	private int remainingPads;
	
	@Override
	public String toString() {
		return "<" + i + ", " + j + ", " + orientation + ", " + remainingPads + ">";
	}
	
	public int getRemainingPads() {
		return remainingPads;
	}

	public void setRemainingPads(int remainingPads) {
		this.remainingPads = remainingPads;
	}

	public State(int i, int j, Orientation orientation, int remainingPads){
		this.i = i;
		this.j = j;
		this.orientation = orientation;
		this.remainingPads = remainingPads;
	}
	
	public State copyState(){
		return new State(getI(), getJ(), getOrientation(), getRemainingPads());
	}
	
	@Override
	public boolean equals(Object obj) {
		State s = (State) obj;
		return i == s.i && j == s.j && orientation == s.orientation && remainingPads == s.remainingPads;
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
