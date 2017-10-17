import java.util.Hashtable;

public class Help_R2D2 extends Problem {

	private Grid grid;
	private int gridM, gridN;

	public Help_R2D2(Hashtable<String, Integer> operators, State initialState) {
		super(operators, initialState);

		gridM = 3;
		gridN = 3;
		grid = new Grid(gridM, gridN);
	}

	public boolean goalTest(State currentState) {
		return currentState.getRemainingPads() == 0
				&& currentState.getI() == grid.getTeleI()
				&& currentState.getJ() == grid.getTeleJ();
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
		
		switch(currentState.getOrientation()){
			case EAST:
				GridCell current = grid.getGrid()[currentState.getI()][currentState.getJ()];
				//get next cell
				if(currentState.getI()+1 < Grid.getM()){
					GridCell nextE = grid.getGrid()[currentState.getI()+1][currentState.getJ()];
					if(nextE.getCellType().equals(CellType.GAP)){
						newState.setI(currentState.getI()+1);
					}
					else{
						if(nextE.getCellType().equals(CellType.ROCK) && (currentState.getI()+2 < Grid.getM())){
							GridCell nextE2 = grid.getGrid()[currentState.getI()+2][currentState.getJ()];
							if(nextE2.getCellType().equals(CellType.GAP)){
								current.setCellType(CellType.GAP);
								nextE.setCellType(CellType.ROBOT);
								nextE2.setCellType(CellType.ROCK);
								newState.setI(currentState.getI()+1);
							}
						}
					}
				}
				break;
			
			case NORTH:
				if(currentState.getJ()-1 > 0){
					CellType nextN = grid.getGrid()[currentState.getJ()-1][currentState.getJ()].getCellType();
					newState.setJ(currentState.getJ()-1);
				}
				break;
			
			case SOUTH:
				if((currentState.getJ()+1 < Grid.getN())){
					CellType nextS = grid.getGrid()[currentState.getJ()+1][currentState.getJ()].getCellType();
					newState.setJ(currentState.getJ()+1);
				}
				break;
			
			case WEST:
				if((currentState.getI()-1 > 0)){
					CellType nextW = grid.getGrid()[currentState.getI()-1][currentState.getJ()].getCellType();
					newState.setI(currentState.getI()-1);
				}
				break;
		}
		
		return newState;
	}

	public State pushRock(State currentState) {
		State newState = currentState.copyState();

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

		return newState;
	}

}
