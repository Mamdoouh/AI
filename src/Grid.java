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
	private static ArrayList<rockLocation> rocksLocation;
    private static GridCell [][] grid = null;
	
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
		rocksLocation = new ArrayList<rockLocation>();

		grid = new GridCell[m][n];
		gridObjs = new ArrayList<>();
		
		//fillObjectsList();
		//generateGrid();
		
		generateSolveableGrid();
		//System.out.println(gridToString(grid));
	}
	
	public ArrayList<GridCell> cellsAsList(){
		ArrayList<GridCell> result = new ArrayList<>();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				GridCell cell = new GridCell(grid[i][j].getCellType());
				cell.setI(i);
				cell.setJ(j);
				
				result.add(cell);
			}
		}	
		
		return result;
	}
	
	// Converts a cell type to a string representation in the grid
	public static String typeToString(CellType type){
		switch(type){
	        case ROBOT: 
	            return "   ROB   ";
	           
	        case TELEPORTAL:
	        	return "   TEL   ";
	            
	        case OBSTACLE: 
	        	return "   OBS   ";
	            
	        case ROCK:
	        	return "   ROC   ";
	        
	        case PAD:
	        	return "   PAD   ";
	            
	        case GAP: 
	        	return "   GAP   ";
			
	        case ROBOT_ON_PAD:
	        	return " RT_PAD  ";
			
	        case ROBOT_ON_TELE:
	        	return " RT_TEL  ";
	        	
	        case ROCK_ON_PAD:
	        	return " RK_PAD  ";
		}
		
		return null;
	}
	
	// Converts a list of cells to a grid format then to string format
	public static String listToString(ArrayList<GridCell> cells){
		GridCell [][] map = new GridCell[m][n];
		
		for(GridCell curCell : cells){
			CellType type = curCell.getCellType();
			int i = curCell.getI();
			int j = curCell.getJ();
			
			map[i][j] = new GridCell(type);
		}
		
		return gridToString(map);
		
	}

	// Converts a grid to string
	public static String gridToString(GridCell [][] map){
		String output = "";
		output += "\n Grid now :- \n\n";
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				CellType type = map[i][j].getCellType();
				output += typeToString(type);
			}
			output += "\n\n";
		}
		
		output += "------------------------------------------------- \n";
		return output;
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
					rocksLocation.add(location);
				}
				grid[i][j] = new GridCell(chosenType);
				gridObjs.remove(randIndex);
			}
		}
	}

	// Constructs a custom grid where a solution is guaranteed
	public void generateSolveableGrid(){
		GridCell gap = new GridCell(CellType.GAP);
		GridCell rock = new GridCell(CellType.ROCK);
		GridCell pad = new GridCell(CellType.PAD);
		GridCell robot = new GridCell(CellType.ROBOT);
		GridCell tele = new GridCell(CellType.TELEPORTAL);
		
		grid[0][0] = robot;
		grid[0][1] = rock;
		grid[0][2] = pad;
		
		grid[1][0] = rock;
		grid[1][1] = gap;
		grid[1][2] = gap;
		
		grid[2][0] = pad;
		grid[2][1] = gap;
		grid[2][2] = tele;
		
		robotI = 0;
		robotJ = 0;
		
		teleI = 2;
		teleJ = 2;
		
		rocksLocation.add(new rockLocation(1, 1));
		rocksLocation.add(new rockLocation(1, 0));
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
	
	public int genRandom(int min, int max){
		return min + (int) (Math.random()*max);
	}
		
	public GridCell[][] copyGrid(){
		GridCell[][] copy = new GridCell[m][n];
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				copy[i][j] = grid[i][j];
			}
		}
		
		return copy;
	}

	// Getters and setters.
	public int getM() {
		return m;
	}

	public void setM(int m) {
		Grid.m = m;
	}

	public int getN() {
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
		return rocksLocation;
	}


}