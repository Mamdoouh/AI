
public class GridCell {
	
	private CellType cellType;
	private int i, j;
	
	public GridCell(CellType type){
		cellType = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		GridCell cell = (GridCell) obj;
		return cell.getCellType() == cellType && cell.getI() == i && cell.getJ() == j;
	}
	
	@Override
	public String toString() {
		return "<" + cellType.toString().substring(0, 3) + ", " + i + ", " + j + ">"; 
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
	
	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	
}
