
public class GridCell {
	
	private CellType cellType;
	
	public GridCell(CellType type){
		cellType = type;
	}
	
	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	
}
