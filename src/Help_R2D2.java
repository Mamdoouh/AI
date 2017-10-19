import java.util.ArrayList;
import java.util.Hashtable;

public class Help_R2D2 extends Problem {

	private Grid grid;
	private int gridM, gridN;
	
	
	public Help_R2D2() {
		super(generateOperators());
		
		gridM = 3;
		gridN = 3;
		grid = new Grid(gridM, gridN);
		
		State initial = new State(Grid.getRobotI(), Grid.getRobotJ(), Orientation.NORTH, Grid.getRocksLocation().size(), false, false,Grid.getRocksLocation());
		super.setInitialState(initial);
	}
	
	public static Hashtable<String, Integer> generateOperators(){
		Hashtable<String, Integer> operators = new Hashtable<>();
		operators.put("MoveForward", 2);
		operators.put("RotateLeft", 1);
		operators.put("RotateRight", 1);
		
		return operators;
	}
	
	public boolean goalTest(State currentState) {
		return currentState.getRemainingPads() == 0
				&& currentState.getI() == Grid.getTeleI()
				&& currentState.getJ() == Grid.getTeleJ();
	}
	
	
	public ArrayList<ResultingState> transition(State currentState) {
		// Returns every possible next state along with the operator associated with it.
		ArrayList<ResultingState> possibleNextStates = new ArrayList<>();

		possibleNextStates.add(new ResultingState(moveForward(currentState), "MoveForward"));
		possibleNextStates.add(new ResultingState(rotateRight(currentState), "RotateRight"));
		possibleNextStates.add(new ResultingState(rotateLeft(currentState), "RotateLeft"));

		return possibleNextStates;
	}
	
	
	public State moveForward (State currentState){
		State newState = null;
		
		switch(currentState.getOrientation()){				
			case NORTH:
				newState = (moveNorth(currentState)).copyState();
				break;
			
			case SOUTH:
				newState = (moveSouth(currentState)).copyState();
				break;
				
			case EAST:
				newState = (moveEast(currentState)).copyState();
				break;
			
			case WEST:
				newState = (moveWest(currentState)).copyState();
				break;
		}
		
		//grid.printGrid();
		//System.out.println(currentState + " ---> " + newState);
		
		return newState;
	}

	// moveForward relies on these 4 methods
	public State moveNorth(State currentState){
		State newState = currentState.copyState();
		int currentI = currentState.getI();
		int currentJ = currentState.getJ();
		
		newState.setWillMove(false);
		newState.setWillPushRock(false);
		
		boolean canMove = false;
		boolean canPush = false;
		boolean canCoverPad = false;
		
		if(currentI-1 >= 0){
			GridCell next = grid.getGrid()[currentI-1][currentJ];
			
			if(next.getCellType().equals(CellType.GAP)){
				// Next cell is a gap
				canMove = true;
			}
			
			else if(next.getCellType().equals(CellType.ROCK) && (currentI-2 >= 0)){
				// Next cell is a rock and there is a cell after it
				GridCell afterRock = grid.getGrid()[currentI-2][currentJ];
				if(afterRock.getCellType().equals(CellType.GAP)){
					System.out.println("1_N");
					canMove = true;
					canPush = true;
					newState.updateRockLocation(currentI, currentJ, currentI-1, currentJ);	
				}
				else if(afterRock.getCellType().equals(CellType.PAD)){
					System.out.println("2_N");
					canMove = true;
					canPush = true;
					canCoverPad = true;
					newState.updateRockLocation(currentI, currentJ, currentI-1, currentJ);	

				}
			}
			
		}
		
		if(canMove || canPush || canCoverPad){
			newState.setWillMove(true);
		}
		
		if(canMove){
			newState.setI(currentState.getI()-1);
		}
		
		if(canPush){
			newState.setWillPushRock(true);
		}
		
		if(canCoverPad){
			newState.setRemainingPads(currentState.getRemainingPads() - 1);
		}
		
		return newState;
	}
	
	public State moveSouth(State currentState){
		State newState = currentState.copyState();
		int currentI = currentState.getI();
		int currentJ = currentState.getJ();
		
		newState.setWillMove(false);
		newState.setWillPushRock(false);
		
		boolean canMove = false;
		boolean canPush = false;
		boolean canCoverPad = false;
		
		if(currentI+1 < Grid.getM()){
			GridCell next = grid.getGrid()[currentI+1][currentJ];
			
			if(next.getCellType().equals(CellType.GAP)){
				// Next cell is a gap
				canMove = true;
			}
			
			else if(next.getCellType().equals(CellType.ROCK) && (currentI+2 < Grid.getM())){
				// Next cell is a rock and there is a cell after it
				GridCell afterRock = grid.getGrid()[currentI+2][currentJ];
				if(afterRock.getCellType().equals(CellType.GAP)){
					System.out.println("1_S");
					canMove = true;
					canPush = true;
					newState.updateRockLocation(currentI, currentJ, currentI+1, currentJ);	
				}
				else if(afterRock.getCellType().equals(CellType.PAD)){
					System.out.println("2_S");
					canMove = true;
					canPush = true;
					canCoverPad = true;
					newState.updateRockLocation(currentI, currentJ, currentI+1, currentJ);	

				}
			}
			
		}
		
		if(canMove || canPush || canCoverPad){
			newState.setWillMove(true);
		}
		
		if(canMove){
			newState.setI(currentState.getI()+1);
		}
		
		if(canPush){
			newState.setWillPushRock(true);
		}
		
		if(canCoverPad){
			newState.setRemainingPads(currentState.getRemainingPads() - 1);
		}
		
		return newState;
	}
		
	public State moveEast(State currentState){
		State newState = currentState.copyState();
		int currentI = currentState.getI();
		int currentJ = currentState.getJ();
		
		newState.setWillMove(false);
		newState.setWillPushRock(false);
		
		boolean canMove = false;
		boolean canPush = false;
		boolean canCoverPad = false;
		
		if(currentJ+1 < Grid.getN()){
			GridCell next = grid.getGrid()[currentI][currentJ+1];
			
			if(next.getCellType().equals(CellType.GAP)){
				// Next cell is a gap
				canMove = true;
			}
			
			else if(next.getCellType().equals(CellType.ROCK) && (currentJ+2 < Grid.getN())){
				// Next cell is a rock and there is a cell after it
				GridCell afterRock = grid.getGrid()[currentI][currentJ+2];
				
				if(afterRock.getCellType().equals(CellType.GAP)){
					System.out.println("1_E");
					canMove = true;
					canPush = true;
					newState.updateRockLocation(currentI, currentJ, currentI, currentJ+1);
					

				}
				else if(afterRock.getCellType().equals(CellType.PAD)){
					System.out.println("2_E");
					canMove = true;
					canPush = true;
					canCoverPad = true;
					newState.updateRockLocation(currentI, currentJ, currentI, currentJ+1);

				}
			}
			
		}
		
		if(canMove || canPush || canCoverPad){
			newState.setWillMove(true);
		}
		
		if(canMove){
			newState.setJ(currentState.getJ()+1);
		}
		
		if(canPush){
			newState.setWillPushRock(true);
		}
		
		if(canCoverPad){
			newState.setRemainingPads(currentState.getRemainingPads() - 1);
		}
		
		return newState;
	}
	
	public State moveWest(State currentState){
		State newState = currentState.copyState();
		int currentI = currentState.getI();
		int currentJ = currentState.getJ();
		
		newState.setWillMove(false);
		newState.setWillPushRock(false);
		
		boolean canMove = false;
		boolean canPush = false;
		boolean canCoverPad = false;
		
		if(currentJ-1 >= 0){
			GridCell next = grid.getGrid()[currentI][currentJ-1];
			
			if(next.getCellType().equals(CellType.GAP)){
				// Next cell is a gap
				canMove = true;
			}
			
			else if(next.getCellType().equals(CellType.ROCK) && (currentJ-2 >= 0)){
				// Next cell is a rock and there is a cell after it
				GridCell afterRock = grid.getGrid()[currentI][currentJ-2];
				if(afterRock.getCellType().equals(CellType.GAP)){
					System.out.println("1_W");
					canMove = true;
					canPush = true;
					newState.updateRockLocation(currentI, currentJ, currentI, currentJ-1);

				}
				else if(afterRock.getCellType().equals(CellType.PAD)){
					System.out.println("2_W");
					canMove = true;
					canPush = true;
					canCoverPad = true;
					newState.updateRockLocation(currentI, currentJ, currentI, currentJ-1);

				}
			}
			
		}
		
		if(canMove || canPush || canCoverPad){
			newState.setWillMove(true);
		}
		
		if(canMove){
			newState.setJ(currentState.getJ()-1);
		}
		
		if(canPush){
			newState.setWillPushRock(true);
		}
		
		if(canCoverPad){
			newState.setRemainingPads(currentState.getRemainingPads() - 1);
		}
		
		return newState;
	}


	public State rotateRight(State currentState) {
		State newState = currentState.copyState();

		switch (currentState.getOrientation()) {
		case EAST:
			newState.setOrientation(Orientation.SOUTH);
			break;

		case NORTH:
			newState.setOrientation(Orientation.EAST);
			break;

		case SOUTH:
			newState.setOrientation(Orientation.WEST);
			break;

		case WEST:
			newState.setOrientation(Orientation.NORTH);
			break;
		}

		//System.out.print("Action: RotateRight ->  ");
		newState.setWillMove(false);
		return newState;
	}

	
	public State rotateLeft(State currentState) {
		State newState = currentState.copyState();

		switch (currentState.getOrientation()) {
		case EAST:
			newState.setOrientation(Orientation.NORTH);
			break;

		case NORTH:
			newState.setOrientation(Orientation.WEST);
			break;

		case SOUTH:
			newState.setOrientation(Orientation.EAST);
			break;

		case WEST:
			newState.setOrientation(Orientation.SOUTH);
			break;
		}
		
		newState.setWillMove(false);
		return newState;
	}

}
