import java.util.ArrayList;
import java.util.Queue;

public class GeneralSearch{

	private Problem problem;
	private SearchStrategy strategy;
	private Queue<Node> nodes;
	
	public GeneralSearch(Problem problem, SearchStrategy strategy){
		this.problem = problem;
		this.strategy = strategy;
	}
	
	public ArrayList<Node> expand(Node n){
		
		ArrayList<Node> results = new ArrayList<>();
		for (String operator : this.problem.getOperators()) {
			
			State s = null;
			switch (operator) {
				case "MF" : 
					 s = move(n);
					break;
				
				case "RR": 
					 s = rotateR(n);
					break;
					
				case "RL":
					 s = rotateL(n);
					break;
			
			}
			
			Node child = new Node(s, n, operator, n.getDepth()+1, n.getCostFromRoot());
			results.add(child);
		}	
		return results;
	}
	
	public void insert(ArrayList<Node> children){
		
	}
	
	public State move (Node previousNode){
		
		State newState = new State(previousNode.getState().getI(), previousNode.getState().getJ(), previousNode.getState().getOrientation(), previousNode.getState().getRemainingPads());
		
		if (previousNode.getState().getOrientation().equals(Orientation.EAST) && (previousNode.getState().getI() < Grid.getM())){
			newState.setI(previousNode.getState().getI()+1);
		}else
			if (previousNode.getState().getOrientation().equals(Orientation.WEST) && (previousNode.getState().getI() > 0)){
				newState.setI(previousNode.getState().getI() -1);

			}else
				if (previousNode.getState().getOrientation().equals(Orientation.NORTH) && (previousNode.getState().getJ() > 0)){
					newState.setJ(previousNode.getState().getJ() -1);

			}else
				if (previousNode.getState().getOrientation().equals(Orientation.SOUTH) && (previousNode.getState().getJ() < Grid.getN())){
					newState.setJ(previousNode.getState().getJ() +1);
			}
		return newState;
	}
	public State rotateR (Node previousNode){
		
		State newState = new State(previousNode.getState().getI(), previousNode.getState().getJ(), previousNode.getState().getOrientation(), previousNode.getState().getRemainingPads());

		if (previousNode.getState().getOrientation().equals(Orientation.NORTH)){
			newState.setOrientation(Orientation.EAST);
		}else
			if (previousNode.getState().getOrientation().equals(Orientation.EAST)){
				newState.setOrientation(Orientation.SOUTH);
		}else
			if (previousNode.getState().getOrientation().equals(Orientation.SOUTH)){
				newState.setOrientation(Orientation.WEST);
			}else
				if (previousNode.getState().getOrientation().equals(Orientation.WEST)){
					newState.setOrientation(Orientation.NORTH);
				}
		return newState;
	 }
	
	public State rotateL (Node previousNode){
		
		State newState = new State(previousNode.getState().getI(), previousNode.getState().getJ(), previousNode.getState().getOrientation(), previousNode.getState().getRemainingPads());
			
		if (previousNode.getState().getOrientation().equals(Orientation.NORTH)){
			newState.setOrientation(Orientation.WEST);
		}else
			if (previousNode.getState().getOrientation().equals(Orientation.WEST)){
				newState.setOrientation(Orientation.SOUTH);
		}else
			if (previousNode.getState().getOrientation().equals(Orientation.SOUTH)){
				newState.setOrientation(Orientation.EAST);
			}else
				if (previousNode.getState().getOrientation().equals(Orientation.EAST)){
					newState.setOrientation(Orientation.NORTH);
				}
		return newState;
	}
	
	
	
	
	
	
	
	

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public SearchStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategy strategy) {
		this.strategy = strategy;
	}

	public Queue<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Queue<Node> nodes) {
		this.nodes = nodes;
	}
	

}
