import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Problem{
	
	private Hashtable<String, Integer> operators; //Name -> Cost
	private State initialState;

	public Problem(Hashtable<String, Integer> operators, State initialState){
		this.operators = operators;
		this.initialState = initialState;
	}
	
	public State getInitialState() {
		return initialState;
	}
	
	public Hashtable<String, Integer> getOperators() {
		return operators;
	}
	
	public int pathCost(ArrayList<String> actions){
		int totalCost = 0;
		for (String action : actions) {
			totalCost += operators.get(action);
		}
		
		return totalCost;
	}
	
	// Abstract functions to be implemented by Help_R2D2.
	public abstract boolean goalTest(State currentState);
	public abstract Hashtable<State, String> transition(State currentState);
	
}
