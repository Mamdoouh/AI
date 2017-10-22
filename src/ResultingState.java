
public class ResultingState {

	private State state;
	private String operator;
	
	public ResultingState(State state, String operator){
		this.state = state;
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return state.toString();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
}
