
public class Node {

	private State state;
	private Node parent;
	private String operator;
	private int depth;
	private int costFromRoot;
	
	public Node(State state, Node parent, String operator, int depth, int costFromRoot){
		this.costFromRoot = costFromRoot;
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
	}
	
	
	// Getters and setters
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getCostFromRoot() {
		return costFromRoot;
	}

	public void setCostFromRoot(int costFromRoot) {
		this.costFromRoot = costFromRoot;
	}
	
}
