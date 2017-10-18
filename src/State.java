
public class State {

	private int i, j;
	private Orientation orientation;
	private int remainingPads;
	
	// Whether transition to this state results in moving or not
	private boolean willMove;
	
	// Whether transition to this state results in pushing a rock or not
	private boolean willPushRock;
	
	
	// Constructor
	public State(int i, int j, Orientation orientation, int remainingPads, boolean willPushRock, boolean willMove){
		this.i = i;
		this.j = j;
		this.orientation = orientation;
		this.remainingPads = remainingPads;
		this.willPushRock = willPushRock;
		this.willMove = willMove;
	}
	
	// Helper methods
	public State copyState(){
		return new State(getI(), getJ(), getOrientation(), getRemainingPads(), isWillPushRock(), isWillMove());
	}
	
	@Override
	public boolean equals(Object obj) {
		State s = (State) obj;
		return i == s.i && j == s.j && orientation == s.orientation && remainingPads == s.remainingPads 
				&& willPushRock == s.isWillPushRock() && willMove == s.isWillMove();
	}
	
	@Override
	public String toString() {
		return "<" + i + ", " + j + ", " + orientation + ", " + remainingPads + ", " + willPushRock + ", " + willMove + ">";
	}
	

	// Getters and setters
	public int getRemainingPads() {
		return remainingPads;
	}

	public boolean isWillMove() {
		return willMove;
	}

	public void setWillMove(boolean willMove) {
		this.willMove = willMove;
	}

	public boolean isWillPushRock() {
		return willPushRock;
	}

	public void setWillPushRock(boolean willPushRock) {
		this.willPushRock = willPushRock;
	}

	public void setRemainingPads(int remainingPads) {
		this.remainingPads = remainingPads;
	}

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
