import java.util.ArrayList;

public class Grid {

	private static int m;
	private static int n;
	private int obstacles;
	private int rocks;
	private int gaps;
	private ArrayList<GridCell> gridObjs;
	private GridCell [][] grid = null;
	
	public Grid(){
		m = genRandom(2, 10);
		n = genRandom(2, 10);
		int gridSize = m * n;
		
		obstacles = genRandom(0, gridSize-4);
		rocks = genRandom(1, (gridSize - obstacles - 2)/2);
		gaps = gridSize - (obstacles + rocks*2 + 2);
		
		grid = new GridCell[m][n];
		gridObjs = new ArrayList<>();
		
		fillObjects();
	
		System.out.println("Grid dimentions: " + m + " * " + n);
		System.out.println("Robot: " + 1);
		System.out.println("Teleportal: " + 1);
		System.out.println("Obstacles: " + obstacles);
		System.out.println("Rocks: " + rocks);
		System.out.println("Pads: " + rocks);
		System.out.println("Gaps: " + gaps);
	}
	
	public void generateGrid(){
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				int randIndex = genRandom(0, gridObjs.size()-1);
				grid[i][j] = new GridCell(gridObjs.get(randIndex).getCellType());
			}
		}
	}
	
	public void fillObjects(){
		for (int i = 0; i < gridObjs.size(); i++) {
			gridObjs.add(new GridCell(CellType.ROBOT));
			gridObjs.add(new GridCell(CellType.TELEPORTAL));
			
			for (int j = 0; j < obstacles; j++) {
				gridObjs.add(new GridCell(CellType.OBSTACLE));
			}
			for (int j = 0; j < rocks; j++) {
				gridObjs.add(new GridCell(CellType.ROCK));
			}
			for (int j = 0; j < rocks; j++) {
				gridObjs.add(new GridCell(CellType.PAD));
			}
			for (int j = 0; j < gaps; j++) {
				gridObjs.add(new GridCell(CellType.GAP));
			}
		}
	}
	
	public int genRandom(int lower, int upper){
		return lower + (int) (Math.random()*upper);
	}
	
	public static void main(String[] args) {
		Grid g = new Grid();
	}

	public static int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public static int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public ArrayList<GridCell> getGridObjs() {
		return gridObjs;
	}

	public void setGridObjs(ArrayList<GridCell> gridObjs) {
		this.gridObjs = gridObjs;
	}

	public GridCell[][] getGrid() {
		return grid;
	}

	public void setGrid(GridCell[][] grid) {
		this.grid = grid;
	}

}