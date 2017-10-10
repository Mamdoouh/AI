
public class Robot {
	
	private int i,j;
	private Orientation orientation;
	
	public Robot(){
		this.orientation = orientation.NORTH;
	}
	public Robot(int i , int j){
		this.orientation = orientation.NORTH;
		this.i =i;
		this.j =j;
	}
	public void move (Orientation o){
		
		if (o.equals(Orientation.EAST) && (i < Grid.getM())){
			this.i ++;
		}else
			if (o.equals(Orientation.WEST) && (i > Grid.getM())){
				this.i --;
			}else
				if (o.equals(Orientation.NORTH) && (i > Grid.getN())){
				this.j --;
			}else
				if (o.equals(Orientation.SOUTH) && (i < Grid.getN())){
					this.j ++;
			}
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
	
	

}
