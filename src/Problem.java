import java.util.ArrayList;

public class Problem{
	
	private ArrayList<String> operators;
	private State initialState;

	// Getters and setters
	public ArrayList<String> getOperators() {
		return operators;
	}

	public void setOperators(ArrayList<String> operators) {
		this.operators = operators;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

}
