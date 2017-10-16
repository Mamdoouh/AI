import java.util.ArrayList;
import java.util.Hashtable;

public class GeneralSearch{

	Problem problem;
	SearchStrategy strategy;
	ArrayList<Node> nodes;
	Hashtable<State, Boolean> visitedStates; 
	
	public GeneralSearch(Problem problem, SearchStrategy strategy){
		this.problem = problem;
		this.strategy = strategy;
	}
	
	public Node search(){		
		Node root = new Node(problem.getInitialState(), null, null, 0, 0);
		nodes.add(root);
		
		while(true){
			if(nodes.isEmpty()){
				// No solution.
				return null;
			}
			
			Node targetNode = nodes.get(0);
			State targetState = targetNode.getState();
			nodes.remove(0);
			
			if(problem.goalTest(targetState)){
				return targetNode;
			}
			
			// Handle repeated states.
			if(visitedStates.get(targetState) != null){
				continue;
			}
			else{
				visitedStates.put(targetState, true);
			}
			
			Hashtable<State, String> possibleNext = problem.transition(targetNode.getState());

			for (State state : possibleNext.keySet()) {
				// Create new node from the given state.
				String operatorName = possibleNext.get(state);
				int operatorCost = problem.getOperators().get(operatorName);
				int totalCost = targetNode.getCostFromRoot() + operatorCost;
				int parentDepth = targetNode.getDepth();
				Node newNode = new Node(state, targetNode, operatorName, parentDepth+1, totalCost);
				
				switch(strategy){
					case BF:
						nodes.add(newNode);
						break;
					
					case DF:
						break;
					
					case UC:
						break;
					
					case GR:
						break;
					
					case ID:
						break;
					
					case AS:
						break;
				}
				
			}
			
		}

	}

}
