
public class Main {
	
	public static void main(String [] args) {		

		Help_R2D2 problem = new Help_R2D2();
		
		GeneralSearch solver = new GeneralSearch(problem, SearchStrategy.BF);
		solver.search();
		
	}
	
}
