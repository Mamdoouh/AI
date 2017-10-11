
public class Robot {
	
	private State currentState;
	
	public Robot(){
		this.currentState.setOrientation(Orientation.NORTH); 
	}
	public Robot(int i , int j){
		this.currentState.setI(i);
		this.currentState.setJ(j);
		this.currentState.setOrientation(Orientation.NORTH); 
	}
	public void move (){
		
		if (this.currentState.getOrientation().equals(Orientation.EAST) && (this.currentState.getI() < Grid.getM())){
			this.currentState.setI(this.currentState.getI()+1);
		}else
			if (this.currentState.getOrientation().equals(Orientation.WEST) && (this.currentState.getI() > 0)){
				this.currentState.setI(this.currentState.getI() -1);

			}else
				if (this.currentState.getOrientation().equals(Orientation.NORTH) && (this.currentState.getJ() > 0)){
					this.currentState.setJ(this.currentState.getJ() -1);

			}else
				if (this.currentState.getOrientation().equals(Orientation.SOUTH) && (this.currentState.getJ() < Grid.getN())){
					this.currentState.setJ(this.currentState.getJ() +1);
			}
	}
	public void rotateR (){
		
		if (this.currentState.getOrientation().equals(Orientation.NORTH)){
			this.currentState.setOrientation(Orientation.EAST);
		}else
			if (this.currentState.getOrientation().equals(Orientation.EAST)){
				this.currentState.setOrientation(Orientation.SOUTH);
		}else
			if (this.currentState.getOrientation().equals(Orientation.SOUTH)){
				this.currentState.setOrientation(Orientation.WEST);
			}else
				if (this.currentState.getOrientation().equals(Orientation.WEST)){
					this.currentState.setOrientation(Orientation.NORTH);
				}
	 }
	
	public void rotateL (Orientation o){
			
			if (this.currentState.getOrientation().equals(Orientation.NORTH)){
				this.currentState.setOrientation(Orientation.WEST);
			}else
				if (this.currentState.getOrientation().equals(Orientation.WEST)){
					this.currentState.setOrientation(Orientation.SOUTH);
			}else
				if (this.currentState.getOrientation().equals(Orientation.SOUTH)){
					this.currentState.setOrientation(Orientation.EAST);
				}else
					if (this.currentState.getOrientation().equals(Orientation.EAST)){
						this.currentState.setOrientation(Orientation.NORTH);
					}
		
	}
	public State getCurrent() {
		return currentState;
	}
	public void setCurrent(State current) {
		this.currentState = current;
	}

	
	
}
