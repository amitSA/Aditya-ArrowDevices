package Algorithm;
import java.util.ArrayList;



public class Target 
{
	public static final int TYPE = 8;
	
	private int count;  //number of targets that have been hit, used a variable to hold it for efficiency
	private byte [] target;
	private int range; 
	public Target(int range){
		this.range = range;
		this.target = new byte[range/TYPE + 1];
		this.count = 0;
	}

	//@pre stands for post condition
	
	/**This method marks the target at targetNum if it has not been previously been marked.  If the 
	 * target was not previously marked, then the targetMarkedCounter(simple named count) is also incremented
	 * 
	 * @pre targetNum must be be a target in the range passed into this objects constructor  	
	 */
	public void mark(int targetNum){
		int byteIndex = targetNum/TYPE;
		int byteNum = (byte)Math.pow(2,targetNum%TYPE);
		byte byteAns = (byte)(target[byteIndex]|byteNum); // binary or
		if(target[byteIndex] != byteAns){
			target[byteIndex] = byteAns;
			count++;
		}
	}
	
	public boolean isMarked(int targetNum){
		int byteIndex = targetNum/TYPE;
		int byteNum = (byte)Math.pow(2,targetNum%TYPE);
		byte byteAns = (byte)(target[byteIndex]|byteNum); // binary or
		return (target[byteIndex] == byteAns);
	}
	
	
	public int getTargetsMarked(){
		return count;
	}
	
	public int getTargetRange(){
		return range;
	}
	
}
