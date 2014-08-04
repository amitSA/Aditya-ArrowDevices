package Algorithm;

public class Test {
	private int ID;
	private int [] conns;
	
	public Test(int ID, int [] conns){
		this.ID = ID;
		this.conns = conns;
	}
	

	public int getID(){
		return ID;
	}
	public void setID(int i){
		this.ID = i;
	}
	public int [] getArray(){
		return conns;
	}

}
