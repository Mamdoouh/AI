import java.util.ArrayList;
import java.util.Hashtable;

public class GeneralSearch{

	Problem problem;
	SearchStrategy strategy;
	ArrayList<Node> nodes;
	Hashtable<State, Boolean> visitedStates; 
	int uniformLimit;
	
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
						nodes.add(0,newNode);
						break;
					
					case UC:
						nodes.add(newNode);
						uniformSort();
						break;
					
					case GR:
						break;
					
					case ID:
						nodes.add(0,newNode);
						break;
					
					case AS:
						break;
				}
			}
			switch(strategy){
			case UC:
				uniformSort();
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
	
	public void uniformSort(){
		for (int i = 0; i < nodes.size(); i++) {
			int lowest = nodes.get(i).getCostFromRoot();
			int index = i;
			for (int j = i; j < nodes.size(); j++) {
				if(nodes.get(j).getCostFromRoot()<lowest){
					lowest = nodes.get(j).getCostFromRoot();
					index = j;
				}
			}
			nodes.add(0,nodes.remove(index));
		}
	}
	
	public int getHeuristic0(Node node){
		int cost =0;
		State s = node.getState();
		
		int i=s.getI();
		int j =s.getJ();
		
		for (int j2 = 0; j2 < Grid.getRocksLocation().size(); j2++) {
			int rocki = Grid.getRocksLocation().get(j2).getI();
			int rockj = Grid.getRocksLocation().get(j2).getJ();
			
			int costi = Math.abs(rocki-i);
			int costj = Math.abs(rockj-j);
			
			cost += costi+costj;
		}	
		int costTeli = Math.abs(Grid.getTeleI()-i);
		int costTelJ = Math.abs(Grid.getTeleJ()-j);
		
		cost += costTeli + costTelJ;
		
		return cost;
	}
	public int getHeuristic1(Node node){
		int cost =0;
		State s = node.getState();
		
		int i=s.getI();
		int j =s.getJ();
		
		for (int j2 = 0; j2 < Grid.getRocksLocation().size(); j2++) {
			int rocki = Grid.getRocksLocation().get(j2).getI();
			int rockj = Grid.getRocksLocation().get(j2).getJ();
			
			int costi = Math.abs(rocki-i);
			int costj = Math.abs(rockj-j);
			
			cost += costi+costj;
		}	
		return cost;
	}
	
	

}
