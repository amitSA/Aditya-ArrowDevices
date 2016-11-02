package Algorithm;

import java.util.Arrays;

public class Test {
	private int ID;
	private int [] conns;
	
	public Test(int ID, int [] conns){
		this.ID = ID;
		this.conns = conns;
	}
	
	//copy constructor
	public Test(Test t){
		this.ID = t.ID;
		conns = Arrays.copyOf(t.conns,t.conns.length);
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
	public StringBuilder toStringBuilder(){
		StringBuilder buf = new StringBuilder();
		buf.append(ID + ")" + " " + conns.length + " -");
		for(int i : conns)
			buf.append(" " + i);
		return buf;
	}
	public String toString(){
		return toStringBuilder().toString();
	}
}
