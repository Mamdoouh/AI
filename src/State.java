import java.util.ArrayList;

public class State {

	private int remainingPads;
	private ArrayList<GridCell> gridCells;
	private int i, j;
	
	// Constructor
	public State(int i, int j, int remainingPads, ArrayList<GridCell> gridCells){
		this.i = i;
		this.j = j;
		this.remainingPads = remainingPads;
		this.gridCells = gridCells;
	}
	
	// Helper methods
	public State copyState(){
		ArrayList<GridCell> gridCellsCopy = new ArrayList<>();
		
		for (GridCell cell : gridCells) {
			gridCellsCopy.add(cell);
		}
		
		return new State(i, j, remainingPads, gridCellsCopy);
	}
	
	@Override
	public boolean equals(Object obj) {
		State s = (State) obj;
		ArrayList<GridCell> otherList = s.getGridCells();
		boolean listsEqual = true;
		
		for (int i = 0; i < otherList.size(); i++) {
			if(!gridCells.get(i).equals(otherList.get(i))){
				listsEqual = false;
				break;
			}
		}
		
		return i == s.i && j == s.j && remainingPads == s.remainingPads && listsEqual;
	}
	
	@Override
	public String toString() {
		return "<" + i + ", " + j + ", Rem: " + remainingPads + "> \n" + (Grid.listToString(gridCells));
	}
	
	public void updateCell(int i, int j, CellType newType){
		for (int k = 0; k < gridCells.size(); k++) {
			GridCell c = gridCells.get(k);
			
			if(c.getI() == i && c.getJ() == j){
				GridCell newCell = new GridCell(newType);
				newCell.setI(i);
				newCell.setJ(j);
				
				gridCells.set(k, newCell);
			}
		}
	}
	
	public GridCell getCell(int i, int j){
		for (GridCell c : gridCells) {
			if(c.getI() == i && c.getJ() == j){
				return c;
			}
		}
		
		return null;
	}

	// Getters and setters
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
	
	public int getRemainingPads() {
		return remainingPads;
	}

	public void setRemainingPads(int remainingPads) {
		this.remainingPads = remainingPads;
	}

	public ArrayList<GridCell> getGridCells() {
		return gridCells;
	}

	public void setGridCells(ArrayList<GridCell> gridCells) {
		this.gridCells = gridCells;
	}
	
}
