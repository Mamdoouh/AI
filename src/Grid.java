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
	private static ArrayList<rockLocation> initialRocksLocation;
    static GridCell [][] grid = null;
	
	// Save robot and teleportal chosen locations
	private static int teleI;
	private static int teleJ;
	private static int robotI;
	private static int robotJ;

	double rocksMaxPercentage = 0.3;
	double obstaclesMaxPercentage = 0.1;
	
	public Grid(int m, int n){
		// We reserve 2 cells for the robot and the teleportal.
		Grid.m = m;
		Grid.n = n;
		int gridSize = m*n - 2;
		
		rocks = genRandom(1, (int)(gridSize*rocksMaxPercentage));
		obstacles = genRandom(0, (int)(gridSize*obstaclesMaxPercentage));
		gaps = gridSize - (rocks*2 + obstacles);
		initialRocksLocation = new ArrayList<rockLocation>();

		grid = new GridCell[m][n];
		gridObjs = new ArrayList<>();
		
		//fillObjectsList();
		//generateGrid();
		
		generateSolveableGrid();
		printGrid();
	}
	
	public void generateSolveableGrid(){
		GridCell gap = new GridCell(CellType.GAP);
		GridCell rock = new GridCell(CellType.ROCK);
		GridCell pad = new GridCell(CellType.PAD);
		GridCell robot = new GridCell(CellType.ROBOT);
		GridCell tele = new GridCell(CellType.TELEPORTAL);
		
		grid[0][0] = gap;
		grid[0][1] = robot;
		grid[0][2] = gap;
		grid[1][0] = gap;
		grid[1][1] = rock;
		grid[1][2] = pad;
		grid[2][0] = gap;
		grid[2][1] = tele;
		grid[2][2] = gap;
		
		robotI = 0;
		robotJ = 1;
		
		teleI = 2;
		teleJ = 1;
		
		initialRocksLocation.add(new rockLocation(1, 1));
	}
	
	// Constructs the grid by inserting all objects (including gaps) in random positions.
	public void generateGrid(){
		Collections.shuffle(gridObjs);
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				int randIndex = genRandom(0, gridObjs.size()-1);
				
				CellType chosenType = gridObjs.get(randIndex).getCellType();
				
				if(chosenType == CellType.ROBOT){
					robotI = i;
					robotJ = j;
				}
				
				if(chosenType == CellType.TELEPORTAL){
					teleI = i;
					teleJ = j;
				}
				
				if(chosenType == CellType.ROCK){
					rockLocation location = new rockLocation(i,j);
					initialRocksLocation.add(location);
				}
				grid[i][j] = new GridCell(chosenType);
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
	
	public static void printGrid(){
		System.out.print("\n Grid now :- \n\n");
		
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
		
		System.out.println("-------------------------------------------------");
		
	}
	
	public static GridCell[][] getGridCopy(){
		GridCell[][] copy = new GridCell[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				copy[i][j] = grid[i][j];
			}
		}
		
		return copy;
	}

	public int genRandom(int min, int max){
		return min + (int) (Math.random()*max);
	}
	
	public static boolean compareGrids(GridCell[][] g1, GridCell[][] g2) {
		for (int i = 0; i < Grid.getM(); i++) {
			for (int j = 0; j < Grid.getN(); j++) {
				if(g1[i][j] != g2[i][j]){
					return false;
				}
			}
		}

		return true;
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
		Grid.grid = grid;
	}
	
	public static int getTeleI() {
		return teleI;
	}

	public void setTeleI(int teleI) {
		Grid.teleI = teleI;
	}

	public static int getTeleJ() {
		return teleJ;
	}

	public void setTeleJ(int teleJ) {
		Grid.teleJ = teleJ;
	}

	public static int getRobotI() {
		return robotI;
	}

	public static void setRobotI(int robotI) {
		Grid.robotI = robotI;
	}

	public static int getRobotJ() {
		return robotJ;
	}

	public static void setRobotJ(int robotJ) {
		Grid.robotJ = robotJ;
	}
	public static ArrayList<rockLocation> getRocksLocation() {
		return Grid.initialRocksLocation;
	}


}