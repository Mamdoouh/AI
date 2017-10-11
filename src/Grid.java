import java.util.ArrayList;
import java.util.Collections;

// Assumptions :-
// (1) Assume that either rocks or pads cannot exceed 30% of the grid.
// (2) Assume that obstacles cannot exceed 10% of the grid.

public class Grid {

	private static int m;
	private static int n;
	private int obstacles;
	private int rocks;
	private int gaps;
	private ArrayList<GridCell> gridObjs;
	private GridCell [][] grid = null;
	
	double rocksMaxPercentage = 0.3;
	double obstaclesMaxPercentage = 0.1;
	
	public Grid(int m, int n){
		// We reserve 2 cells for the robot and the teleportal.
		int gridSize = m*n - 2;
		
		rocks = genRandom(1, (int)(gridSize*rocksMaxPercentage));
		obstacles = genRandom(0, (int)(gridSize*obstaclesMaxPercentage));
		gaps = gridSize - (rocks*2 + obstacles);

		grid = new GridCell[m][n];
		gridObjs = new ArrayList<>();
		
		fillObjectsList();
		
		System.out.println("Grid dimentions: " + m + " X " + n);
		System.out.println("Robot: " + 1);
		System.out.println("Teleportal: " + 1);
		System.out.println("Obstacles: " + obstacles);
		System.out.println("Rocks: " + rocks);
		System.out.println("Pads: " + rocks);
		System.out.println("Gaps: " + gaps + "\n");

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
	public void fillObjectsList(){
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
	}
	
	public int genRandom(int min, int max){
		return min + (int) (Math.random()*max);
	}

	// Getters and setters
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
		new Grid(3, 3);
	}

}