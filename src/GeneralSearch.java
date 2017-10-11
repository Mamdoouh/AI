import java.util.Queue;

public class GeneralSearch{

	Problem problem;
	SearchStrategy strategy;
	Queue<Node> nodes;
	
	public GeneralSearch(Problem problem, SearchStrategy strategy){
		this.problem = problem;
		this.strategy = strategy;
	}

}
