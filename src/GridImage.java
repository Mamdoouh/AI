
public class GridImage {

	GridCell [][] currentGrid;
	
	public GridImage(GridCell [][] currentGrid){
		this.currentGrid = currentGrid;
	}
	
	@Override
	public boolean equals(Object obj) {
		GridCell [][] g = (GridCell [][]) obj;
		
		if(currentGrid.length != g.length || currentGrid[0].length != g[0].length){
			System.out.println("Different sizes");
			return false;
		}
		
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				if(g[i][j] != currentGrid[i][j]){
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		for (int i = 0; i < currentGrid.length; i++) {
			for (int j = 0; j < currentGrid[0].length; j++) {
				result += (currentGrid[i][j].getCellType().toString().substring(0, 3) + "  ");
			}
			result += "\n\n";
		}
		
		return result;
	}

	public GridCell[][] copy(){
		GridCell[][] copy = new GridCell[currentGrid.length][currentGrid[0].length];
		
		for (int i = 0; i < currentGrid.length; i++) {
			for (int j = 0; j < currentGrid[0].length; j++) {
				copy[i][j] = currentGrid[i][j];
			}
		}
		
		return copy;
	}
	
	public void updateCell(int i, int j, CellType newType){
		currentGrid[i][j].setCellType(newType);
	}
	
	
	// Getters and setters
	public GridCell[][] getCurrentGrid() {
		return currentGrid;
	}

	public void setCurrentGrid(GridCell[][] currentGrid) {
		this.currentGrid = currentGrid;
	}

}
