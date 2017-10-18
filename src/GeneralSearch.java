import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class GeneralSearch {

	Problem problem;
	SearchStrategy strategy;
	ArrayList<Node> nodes;
	ArrayList<Node> expandedNode;
	Hashtable<State, Boolean> visitedStates;
	int IterativeLimit = 0; // For testing

	public GeneralSearch(Problem problem, SearchStrategy strategy) {
		this.problem = problem;
		this.strategy = strategy;
		this.visitedStates = new Hashtable<>();
		this.nodes = new ArrayList<>();
		this.expandedNode = new ArrayList<>();
	}

	public Node search() {
		Node root = new Node(problem.getInitialState(), null, null, 0, 0);
		nodes.add(root);

		while (true) {
			if (nodes.isEmpty()) {
				// No solution.
				return null;
			}

			Node targetNode = nodes.get(0);
			State targetState = targetNode.getState();

			if (problem.goalTest(targetState)) {
				return targetNode;
			}

			if (!strategy.equals(SearchStrategy.ID)
					|| (strategy.equals(SearchStrategy.ID) && (IterativeLimit > targetNode.getDepth()
							|| (IterativeLimit == targetNode.getDepth() && nodes.size() > 1)))) {
				nodes.remove(0);
			} 
			else {
				nodes.clear();
				IterativeLimit++;
			}

			// Handle repeated states.
//			if (visitedStates.get(targetState) != null) {
//				continue;
//			} 
//			else {
//				visitedStates.put(targetState, true);
//			}

			if (!strategy.equals(SearchStrategy.ID) ||  (strategy.equals(SearchStrategy.ID) && IterativeLimit != targetNode.getDepth())) {
				Hashtable<State, String> possibleNext = problem.transition(targetNode.getState());

				for (State state : possibleNext.keySet()) {
					expandedNode.clear();
					// Create new node from the given state.
					String operatorName = possibleNext.get(state);
					int operatorCost = problem.getOperators().get(operatorName);
					int totalCost = targetNode.getCostFromRoot() + operatorCost;
					int parentDepth = targetNode.getDepth();
					Node newNode = new Node(state, targetNode, operatorName, parentDepth + 1, totalCost);

					switch (strategy) {
						case BF:
							nodes.add(newNode);
							break;
	
						case DF:
							nodes.add(0, newNode);
							break;
	
						case UC:
							nodes.add(newNode);
							break;
	
						case GR:
							expandedNode.add(newNode);
	
							break;
	
						case ID:
							nodes.add(0, newNode);
							break;
	
						case AS:
							nodes.add(0, newNode);
							break;
					}
				}
			}
			
			switch (strategy) {
				case UC:
					uniformSort();
					break;
	
				case GR:
					GreedyheuristicSort();
					addExpanded();
					break;
	
				case AS:
					AStarSort();
					break;
	
				default:
					break;
			}
		}
	}

	public ArrayList<String> backtrack(Node goal) {
		ArrayList<String> actions = new ArrayList<>();
		Node current = goal;

		while (current.getParent() != null) {
			actions.add(current.getOperator());
			current = current.getParent();
		}

		Collections.reverse(actions);
		return actions;
	}

	public void IterativeDeepening() {
		for (int i = 0; i < expandedNode.size(); i++) {
			Node en = expandedNode.get(i);
			if (en.getDepth() < IterativeLimit) {
				nodes.add(0, expandedNode.remove(i));
			}
		}

	}

	public void uniformSort() {
		for (int i = 0; i < nodes.size(); i++) {
			int lowest = nodes.get(i).getCostFromRoot();
			int index = i;
			for (int j = i; j < nodes.size(); j++) {
				if (nodes.get(j).getCostFromRoot() < lowest) {
					lowest = nodes.get(j).getCostFromRoot();
					index = j;
				}
			}
			nodes.add(0, nodes.remove(index));
		}
	}

	public void GreedyheuristicSort() {

		for (int i = 0; i < expandedNode.size(); i++) {
			int lowest = getHeuristic0(expandedNode.get(i));
			int index = i;
			for (int j = i; j < expandedNode.size(); j++) {
				int heuristic = getHeuristic0(expandedNode.get(j));
				if (heuristic < lowest) {
					lowest = heuristic;
					index = j;
				}
			}
			expandedNode.add(0, expandedNode.remove(index));
		}
	}

	public void AStarSort() {
		for (int i = 0; i < nodes.size(); i++) {
			int lowest = getHeuristic0(nodes.get(i)) + nodes.get(i).getCostFromRoot();
			int index = i;
			for (int j = i; j < nodes.size(); j++) {
				int heuristic = getHeuristic0(nodes.get(j)) + nodes.get(j).getCostFromRoot();
				if (heuristic < lowest) {
					lowest = heuristic;
					index = j;
				}
			}
			nodes.add(0, nodes.remove(index));
		}
	}

	public int getHeuristic0(Node node) {
		int cost = 0;
		State s = node.getState();

		int i = s.getI();
		int j = s.getJ();

		for (int j2 = 0; j2 < Grid.getRocksLocation().size(); j2++) {
			int rocki = Grid.getRocksLocation().get(j2).getI();
			int rockj = Grid.getRocksLocation().get(j2).getJ();

			int costi = Math.abs(rocki - i);
			int costj = Math.abs(rockj - j);

			cost += costi + costj;
		}
		int costTeli = Math.abs(Grid.getTeleI() - i);
		int costTelJ = Math.abs(Grid.getTeleJ() - j);

		cost += costTeli + costTelJ;

		return cost;
	}

	public int getHeuristic1(Node node) {
		int cost = 0;
		State s = node.getState();

		int i = s.getI();
		int j = s.getJ();

		for (int j2 = 0; j2 < Grid.getRocksLocation().size(); j2++) {
			int rocki = Grid.getRocksLocation().get(j2).getI();
			int rockj = Grid.getRocksLocation().get(j2).getJ();

			int costi = Math.abs(rocki - i);
			int costj = Math.abs(rockj - j);

			int costE = (int) Math.sqrt(Math.pow(costi, 2) + Math.pow(costj, 2));

			cost += costE;
		}
		return cost;
	}

	public void addExpanded() {
		for (int i = 0; i < expandedNode.size(); i++) {
			nodes.add(0, expandedNode.remove(i));
		}
	}

}
