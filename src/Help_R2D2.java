import java.util.Hashtable;

public class Help_R2D2 extends Problem {

	private Grid grid;
	private int gridM, gridN;

	
	public Help_R2D2(Hashtable<String, Integer> operators) {
		super(operators);
		
		gridM = 5;
		gridN = 5;
		grid = new Grid(gridM, gridN);
		
		State initial = new State(Grid.getRobotI(), Grid.getRobotJ(), Orientation.NORTH, Grid.getRocksLocation().size());
		super.setInitialState(initial);
	}
	
	public boolean goalTest(State currentState) {
		return currentState.getRemainingPads() == 0
				&& currentState.getI() == Grid.getTeleI()
				&& currentState.getJ() == Grid.getTeleJ();
	}

	
	public Hashtable<State, String> transition(State currentState) {
		// Map every possible next state to the operator that generated it.
		Hashtable<State, String> possibleNextStates = new Hashtable<>();

		possibleNextStates.put(moveForward(currentState), "MoveForward");
		possibleNextStates.put(rotateRight(currentState), "RotateRight");
		possibleNextStates.put(rotateLeft(currentState), "RotateLeft");

		return possibleNextStates;
	}

	
	public State moveForward (State currentState){
		State newState = currentState.copyState();
		int currentI = currentState.getI();
		int currentJ = currentState.getJ();
		
		switch(currentState.getOrientation()){
			case EAST:
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
							canMove = true;
							canPush = true;
						}
						else if(afterRock.getCellType().equals(CellType.PAD)){
							canMove = true;
							canPush = true;
							canCoverPad = true;
						}
					}
					
				}

				if(canMove){
					newState.setJ(currentState.getJ()+1);
					grid.getGrid()[currentI][currentJ].setCellType(CellType.GAP);
					grid.getGrid()[currentI][currentJ+1].setCellType(CellType.ROBOT);
				}
				
				if(canPush){
					grid.getGrid()[currentI][currentJ+2].setCellType(CellType.ROCK);
				}
				
				if(canCoverPad){
					newState.setRemainingPads(currentState.getRemainingPads() - 1);
				}
				
				break;
			
			case SOUTH:
				canMove = false;
				canPush = false;
				canCoverPad = false;
				
				if(currentI+1 < Grid.getM()){
					GridCell nextE = grid.getGrid()[currentI+1][currentJ];
					
					if(nextE.getCellType().equals(CellType.GAP)){
						// Next cell is a gap
						canMove = true;
					}
					
					else if(nextE.getCellType().equals(CellType.ROCK) && (currentI+2 < Grid.getM())){
						// Next cell is a rock and there is a cell after it
						GridCell afterRock = grid.getGrid()[currentI+2][currentJ];
						if(afterRock.getCellType().equals(CellType.GAP)){
							canMove = true;
							canPush = true;
						}
						else if(afterRock.getCellType().equals(CellType.PAD)){
							canMove = true;
							canPush = true;
							canCoverPad = true;
						}
					}
					
				}

				if(canMove){
					newState.setI(currentState.getI()+1);
					grid.getGrid()[currentI][currentJ].setCellType(CellType.GAP);
					grid.getGrid()[currentI+1][currentJ].setCellType(CellType.ROBOT);
				}
				
				if(canPush){
					grid.getGrid()[currentI+2][currentJ].setCellType(CellType.ROCK);
				}
				
				if(canCoverPad){
					newState.setRemainingPads(currentState.getRemainingPads() - 1);
				}
				break;
			
			case NORTH:
				canMove = false;
				canPush = false;
				canCoverPad = false;
				
				if(currentI-1 > 0){
					GridCell nextE = grid.getGrid()[currentI-1][currentJ];
					
					if(nextE.getCellType().equals(CellType.GAP)){
						// Next cell is a gap
						canMove = true;
					}
					
					else if(nextE.getCellType().equals(CellType.ROCK) && (currentI-2 > 0)){
						// Next cell is a rock and there is a cell after it
						GridCell afterRock = grid.getGrid()[currentI-2][currentJ];
						if(afterRock.getCellType().equals(CellType.GAP)){
							canMove = true;
							canPush = true;
						}
						else if(afterRock.getCellType().equals(CellType.PAD)){
							canMove = true;
							canPush = true;
							canCoverPad = true;
						}
					}
					
				}

				if(canMove){
					newState.setI(currentState.getI()-1);
					grid.getGrid()[currentI][currentJ].setCellType(CellType.GAP);
					grid.getGrid()[currentI-1][currentJ].setCellType(CellType.ROBOT);
				}
				
				if(canPush){
					grid.getGrid()[currentI-2][currentJ].setCellType(CellType.ROCK);
				}
				
				if(canCoverPad){
					newState.setRemainingPads(currentState.getRemainingPads() - 1);
				}
				break;
			
			case WEST:
				canMove = false;
				canPush = false;
				canCoverPad = false;
				
				if(currentJ-1 > 0){
					GridCell next = grid.getGrid()[currentI][currentJ-1];
					
					if(next.getCellType().equals(CellType.GAP)){
						// Next cell is a gap
						canMove = true;
					}
					
					else if(next.getCellType().equals(CellType.ROCK) && (currentJ-2 > 0)){
						// Next cell is a rock and there is a cell after it
						GridCell afterRock = grid.getGrid()[currentI][currentJ-2];
						if(afterRock.getCellType().equals(CellType.GAP)){
							canMove = true;
							canPush = true;
						}
						else if(afterRock.getCellType().equals(CellType.PAD)){
							canMove = true;
							canPush = true;
							canCoverPad = true;
						}
					}
					
				}

				if(canMove){
					newState.setJ(currentState.getJ()-1);
					grid.getGrid()[currentI][currentJ].setCellType(CellType.GAP);
					grid.getGrid()[currentI][currentJ-1].setCellType(CellType.ROBOT);
				}
				
				if(canPush){
					grid.getGrid()[currentI][currentJ-2].setCellType(CellType.ROCK);
				}
				
				if(canCoverPad){
					newState.setRemainingPads(currentState.getRemainingPads() - 1);
				}
				break;
		}
		
		//grid.printGrid();
		System.out.println("Action: MoveForward");
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

		System.out.println("Action: RotateRight");
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

		System.out.println("Action: RotateLeft");
		return newState;
	}

}
