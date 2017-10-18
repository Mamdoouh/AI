import java.util.Hashtable;

public class Main {
	
	public static void main(String [] args) {
		Hashtable<String, Integer> operators = new Hashtable<>();
		operators.put("MoveForward", 2);
		operators.put("RotateLeft", 1);
		operators.put("RotateRight", 1);
		
		Help_R2D2 problem = new Help_R2D2(operators);
		
		GeneralSearch solver = new GeneralSearch(problem, SearchStrategy.BF);
		solver.search();
		
		//ArrayList<String> solution = solver.backtrack(solver.search());
		//System.out.println(solution.toString());
	}
	
}
