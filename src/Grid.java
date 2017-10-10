import java.util.ArrayList;
import java.util.Collections;

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
		
		addObjects();
	
		System.out.println("Grid dimentions: " + m + " * " + n);
		System.out.println("Robot: " + 1);
		System.out.println("Teleportal: " + 1);
		System.out.println("Obstacles: " + obstacles);
		System.out.println("Rocks: " + rocks);
		System.out.println("Pads: " + rocks);
		System.out.println("Gaps: " + gaps);
		
		generateGrid();
		printGrid();
	}
	
	// Constructs the grid by inserting all objects (including gaps) in random positions.
	public void generateGrid(){
		Collections.shuffle(gridObjs);
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				int randIndex = genRandom(0, gridObjs.size()-1);
				grid[i][j] = new GridCell(gridObjs.get(randIndex).getCellType());
				gridObjs.remove(randIndex);
			}
		}
	}
	
	// Inserts all our objects (robot, rocks, ...) as grid cells in objects' list.
	public void addObjects(){
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
	
	public void printGrid(){
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				CellType type = grid[i][j].getCellType();
				
				switch(type){
		            case ROBOT: 
		                System.out.print(" RT  ");
		                break;
		               
		            case TELEPORTAL: 
		                System.out.print("  T  ");
		                break;
		                
		            case OBSTACLE: 
		                System.out.print("  O  ");
		                break;
		                
		            case ROCK: 
		                System.out.print(" RK  ");
		                break;
		            
		            case PAD: 
		                System.out.print("  P  ");
		                break;
		                
		            case GAP: 
		                System.out.print("  G  ");
		                break;
				}
			}
			System.out.println();
		}
	}
	
	public int genRandom(int lower, int upper){
		return lower + (int) (Math.random()*upper);
	}

	public static int getM() {
		return m;
	}

	public void setM(int m) {
		Grid.m = m;
	}

	public static int getN() {
		return n;
	}

	public void setN(int n) {
		Grid.n = n;
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
	
	public static void main(String[] args) {
		new Grid();
	}

}