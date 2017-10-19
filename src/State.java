

public class State {

	private int i, j;
	private Orientation orientation;
	private int remainingPads;
	private GridCell[][] myGrid;

	// Whether transition to this state results in moving or not
	private boolean willMove;
	
	// Whether transition to this state results in pushing a rock or not
	private boolean willPushRock;
	
	
	// Constructor
	public State(int i, int j, Orientation orientation, int remainingPads, boolean willPushRock, 
			boolean willMove, GridCell[][] parentGrid){
		
		this.i = i;
		this.j = j;
		this.orientation = orientation;
		this.remainingPads = remainingPads;
		this.willPushRock = willPushRock;
		this.willMove = willMove;
		this.myGrid = parentGrid;
	}
	
	
	public void updateCell(int i, int j, CellType newType){
		myGrid[i][j].setCellType(newType);
	}
	
	public GridCell[][] getGrid() {
		return myGrid;
	}

	public void setGrid(GridCell[][] myGrid) {
		this.myGrid = myGrid;
	}

	// Helper methods
	public State copyState(){
		return new State(getI(), getJ(), getOrientation(), getRemainingPads(), isWillPushRock(), 
				isWillMove(), getGrid());
	}
	
	@Override
	public boolean equals(Object obj) {
		State s = (State) obj;
		
		return  i == s.i && 
				j == s.j && 
			    orientation == s.orientation && 
			    remainingPads == s.remainingPads && 
			    willPushRock == s.isWillPushRock() && 
			    willMove == s.isWillMove() && 
			    Grid.compareGrids(myGrid, s.getGrid());
	}
	
//	public void updateRockLocation(int oldI, int oldJ, int newI, int newJ){
//		
//		rockLocation x = new rockLocation(oldI, oldJ);
//		
//		rocksLocation.remove(x);
//		x.setI(newI);
//		x.setJ(newJ);
//		rocksLocation.add(x);
//		
//	}
	
	@Override
	public String toString() {
		return "<" + i + ", " + j + ", " + orientation + ", " + remainingPads + ", " + willPushRock + ", " + willMove + ">";
	}
	

	// Getters and setters
	public int getRemainingPads() {
		return remainingPads;
	}

	public boolean isWillMove() {
		return willMove;
	}

	public void setWillMove(boolean willMove) {
		this.willMove = willMove;
	}

	public boolean isWillPushRock() {
		return willPushRock;
	}

	public void setWillPushRock(boolean willPushRock) {
		this.willPushRock = willPushRock;
	}

	public void setRemainingPads(int remainingPads) {
		this.remainingPads = remainingPads;
	}

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	public void printGrid(){
		System.out.print("\n Grid now :- \n\n");
		
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				CellType type = myGrid[i][j].getCellType();
				
				switch(type){
		            case ROBOT: 
		                System.out.print("  ROB  ");
		                break;
		               
		            case TELEPORTAL: 
		                System.out.print("  TEL  ");
		                break;
		                
		            case OBSTACLE: 
		                System.out.print("  OBS  ");
		                break;
		                
		            case ROCK: 
		                System.out.print("  ROC  ");
		                break;
		            
		            case PAD: 
		                System.out.print("  PAD  ");
		                break;
		                
		            case GAP: 
		                System.out.print("  GAP  ");
		                break;
				}
			}
			System.out.print("\n\n");
		}
		
		System.out.println("-------------------------------------------------");
		
	}
}
