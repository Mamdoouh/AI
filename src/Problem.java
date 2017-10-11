import java.util.ArrayList;

public class Problem{
	
	private ArrayList<String> operators;
	private ArrayList<Integer> costs;
	private State initialState;
	private int teleI;
	private int teleJ;
	
	
	public ArrayList<String> getOperators() {
		return operators;
	}

	public State getInitialState() {
		return initialState;
	}

	public Problem(ArrayList<String> operators, ArrayList<Integer> costs, 
				   State initialState, int teleI, int teleJ){
		
		this.operators = operators;
		this.costs = costs;
		this.initialState = initialState;
		this.teleI = teleI;
		this.teleJ = teleJ;
		
	}
	
	public boolean goalTest(State currentState){
		return currentState.getRemainingPads() == 0 && 
			   currentState.getI() == teleI && 
			   currentState.getJ() == teleJ;
	}
	
	public int pathCost(ArrayList<String> actions){
		int totalCost = 0;
		for (int i = 0; i < actions.size(); i++) {
			totalCost += costs.get(i);
		}
		
		return totalCost;
	}

}
