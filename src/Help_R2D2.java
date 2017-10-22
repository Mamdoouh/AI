import java.util.ArrayList;
import java.util.Hashtable;

public class Help_R2D2 extends Problem {

	private Grid myGrid;
	private State currentState;
	
	public Help_R2D2() {
		super(generateOperators());

		this.myGrid = new Grid(3, 3);

		this.currentState = new State(Grid.getRobotI(), Grid.getRobotJ(), Grid.getRocksLocation().size(), myGrid.cellsAsList());
		
		super.setInitialState(currentState);
		System.out.println("Initial State: " + currentState);
		
		
		//State down = move(currentState, "DOWN");
		//System.out.println(down);
		
		//State right = move(down, "RIGHT");
		//System.out.println(right);

	}
	 
	// Goal test function
	public boolean goalTest(State currentState) {
		return currentState.getRemainingPads() == 0
				&& currentState.getI() == Grid.getTeleI()
				&& currentState.getJ() == Grid.getTeleJ();
	}
	
	
	// Transition function
	public ArrayList<ResultingState> transition(State currentState) {
		ArrayList<ResultingState> possibleNextStates = new ArrayList<>();

		possibleNextStates.add(new ResultingState(move(currentState, "UP"), "MoveUp"));
		possibleNextStates.add(new ResultingState(move(currentState, "DOWN"), "MoveDown"));
		possibleNextStates.add(new ResultingState(move(currentState, "RIGHT"), "MoveRight"));
		possibleNextStates.add(new ResultingState(move(currentState, "LEFT"), "MoveLeft"));
 
		return possibleNextStates;
	}
	
	
	// If possible, move forward in a specified direction
	public State move(State curState, String dir){
		int curI = curState.getI();
		int curJ = curState.getJ();
		
		boolean canMove = false;
		boolean canCoverGap = false;
		boolean canCoverPad = false;
		
		State newState = null;
		GridCell nextCell = null;
		GridCell nextOfNextCell = null;
		
		switch(dir){
			case "UP":
				nextCell = curState.getCell(curI-1, curJ);
				nextOfNextCell = curState.getCell(curI-2, curJ);
				break;
			
			case "DOWN":
				nextCell = curState.getCell(curI+1, curJ);
				nextOfNextCell = curState.getCell(curI+2, curJ);
				break;
				
			case "RIGHT":
				nextCell = curState.getCell(curI, curJ+1);
				nextOfNextCell = curState.getCell(curI, curJ+2);
				break;
				
			case "LEFT":
				nextCell = curState.getCell(curI, curJ-1);
				nextOfNextCell = curState.getCell(curI, curJ-2);
				break;
		}
		
		if(nextCell != null){
			CellType next = nextCell.getCellType();
			
			if(next == CellType.GAP || next == CellType.PAD || next == CellType.TELEPORTAL){
				canMove = true;
			}
			
			else {
				if(next == CellType.ROCK){
					if(nextOfNextCell != null){
						CellType nextOfNext = nextOfNextCell.getCellType();
						
						if(nextOfNext == CellType.GAP){
							canMove = true;
							canCoverGap = true;
						}
						
						if(nextOfNext == CellType.PAD){
							canMove = true;
							canCoverPad = true;
						}
					}
				
				}
			}	
			
			if(canMove){
				CellType current = curState.getCell(curI, curJ).getCellType();
				
				int nextI = nextCell.getI();
				int nextJ = nextCell.getJ();
				
				newState = curState.copyState();
				
				switch (current) {
					case ROBOT:
						newState.updateCell(curI, curJ, CellType.GAP);
						break;
						
					case ROBOT_ON_PAD:
						newState.updateCell(curI, curJ, CellType.PAD);
						break;
						
					case ROBOT_ON_TELE:
						newState.updateCell(curI, curJ, CellType.TELEPORTAL);
						break;
						
					default:
						break;
				}
				
				switch (next) {
					case GAP:
						newState.updateCell(nextI, nextJ, CellType.ROBOT);
						break;
						
					case PAD:
						newState.updateCell(nextI, nextJ, CellType.ROBOT_ON_PAD);
						break;
						
					case TELEPORTAL:
						newState.updateCell(nextI, nextJ, CellType.ROBOT_ON_TELE);
						break;

					default:
						break;
				}

				newState.setI(nextI);
				newState.setJ(nextJ);

				if(canCoverGap){
					int afterNextI = nextOfNextCell.getI();
					int afterNextJ = nextOfNextCell.getJ();
					
					newState.updateCell(nextI, nextJ, CellType.ROBOT);
					newState.updateCell(afterNextI, afterNextJ, CellType.ROCK);
				}
				
				if(canCoverPad){
					int afterNextI = nextOfNextCell.getI();
					int afterNextJ = nextOfNextCell.getJ();
					
					newState.updateCell(nextI, nextJ, CellType.ROBOT);
					newState.updateCell(afterNextI, afterNextJ, CellType.ROCK_ON_PAD);
					newState.setRemainingPads(curState.getRemainingPads() - 1);
				}
			}
		}
		
		return newState;
	}
	
	
	// Helper functions
	public static Hashtable<String, Integer> generateOperators(){
		Hashtable<String, Integer> operators = new Hashtable<>();
		operators.put("MoveUp", 1);
		operators.put("MoveDown", 1);
		operators.put("MoveRight", 1);
		operators.put("MoveLeft", 1);
		
		return operators;
	}

}
