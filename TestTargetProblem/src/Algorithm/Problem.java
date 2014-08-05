package Algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Problem {
	
	
	private int targetRange;		
	private int numOfTests;		
	private int connectsPerTest;   
	
	private ArrayList<Test> probList;
	
	private HashMap<Integer,String> dict;
	private int increment;
	
								 // range is 0 to targetRange
	public Problem(int numTests,int targetRange,int maxEntriesPerTarget){
		init();
		probList = new ArrayList<Test>();
		for(int i = 0;i<numTests;i++)
		{
			int numConnect = (int)(Math.random() * maxEntriesPerTarget) + 1;
			int [] array = new int[numConnect];
			for(int ip = 0;ip<numConnect;ip++)
				array[ip] = map(((int)(Math.random()*targetRange)+1)+"");
			int ID = i + 1;
			Test t = new Test(ID,array);
			probList.add(t);
		}
		this.targetRange = targetRange;
		this.numOfTests = numTests;
		this.connectsPerTest = maxEntriesPerTarget;
	}
	
	public Problem(int numTests,int targRange,int maxEntriesPerTarget,double perc){
		init();
		this.targetRange = targRange;
		this.numOfTests = numTests;
		this.connectsPerTest = maxEntriesPerTarget;
		
		if(maxEntriesPerTarget*numTests < (targRange*perc)){
			probList = null;
			return;
		}
		//constants  CAPITALIZE THEM IF YOU WANT TO
  		float middAvg = (maxEntriesPerTarget+1)/2.0f;
  		int entriesNeeded = (int)(targetRange*perc + 0.5);   //rename this to unique targetsNeeded
  		//
		//Making and filling the int[] ArrayList
  		probList = new ArrayList<Test>();
  		int targFilled = 0; 
		for(int i = 0;i<numTests;i++)
		{
			float avg = (float)(entriesNeeded-targFilled)/(numTests-i);
			float offset = (avg-middAvg); //SHOULD WE ROUND?
			int min = (int)(offset+middAvg+0.5), max = maxEntriesPerTarget;
			min = Math.max(1,min);
			//System.out.println("avg: " + avg + "  offset: " + offset + "   min: " + min + "  max: " + max);
			int [] bar = new int[(int)(Math.random()*(max-min+1))+min];
			targFilled+=bar.length;
			probList.add(new Test(i+1,bar));
		}
		ArrayList<int[]> temp = new ArrayList<int[]>();
		ArrayList<Integer> numberLine = new ArrayList<Integer>(); 
		for(int i = 1;i<=targetRange;i++)
			numberLine.add(i);
		for(Test t:probList)
			temp.add(t.getArray());    // this adds the reference of the int[] in bGraph to the temp arrayList
		                    /* this way we can modify indices inside the int[] arrays in temp and they will be reflected in bGraph
		                      although when we remove a int[] from temp, it will not be removed from bGraph */
		while(temp.size() > 0){
			int ranIndex = (int)(Math.random() * temp.size());
			int ranTarg;
			if(numberLine.size() <= 0)
				ranTarg = (int)(Math.random() * targetRange) + 1;
			else{
				int ind = (int)Math.random()*numberLine.size();
				ranTarg = numberLine.get(ind); //this should be removing the index
				numberLine.remove(ind);
			}
			int [] tArray = temp.get(ranIndex);
			int i = 0;
			for(;i<tArray.length;i++)
				if(tArray[i]==0){
					tArray[i] = map(ranTarg+"");
					break;
				}
			if(i==tArray.length-1)
				temp.remove(ranIndex);
		}
    }
	/** In order to use this constructor, the file which the original Problem was 
	 * stored in has to be in a specific order
	*/
	public Problem(String fileName){
		init();
		BufferedReader in = null;
		probList = new ArrayList<Test>();
		try{
			System.out.println(fileName);
			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String [] splitLn = in.readLine().split(" ");
			this.numOfTests = Integer.parseInt(splitLn[0]);
			this.targetRange = Integer.parseInt(splitLn[1]);
			for(int i = 0;i<numOfTests;i++){
				splitLn = in.readLine().split(" ");
				int ID = Integer.parseInt(splitLn[0].substring(0,splitLn[0].indexOf(')')));
				int numConns = Integer.parseInt(splitLn[1]);
				int [] array = new int[numConns];
				for(int ip = 3;ip<splitLn.length;ip++){
					array[ip-3] = map(splitLn[ip]);
				}
				probList.add(new Test(ID,array));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(in!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Test> solve(double perc){
		ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
		HashMap <int[],Test> map = new HashMap<int[],Test>();
		Target target = new Target(targetRange);
		for(int i = 0;i<probList.size();i++)
		{
			Test t = probList.get(i);
			int [] temp = Arrays.copyOf(t.getArray(),t.getArray().length);
			checkToExpandList(list,temp.length);
			map.put(temp,t);
		    compress(temp,-1);
			list.get(temp.length).add(temp);
		}
		
		int supInd;
		int subInd = 0;/*there has to be atleast one element in list.size()-1 because the list would only have 
						 been increased to the last element if there was an element in that index to hold */
		firstLoop:
		for(supInd = list.size()-1;supInd>0;supInd--){
			
			ArrayList<int[]> subList = list.get(supInd);
			for(subInd = 0;subInd<subList.size();subInd++){
				int [] temp = subList.get(subInd);
				int count = 0;
				for(int i = 0;i<temp.length;i++){
					if(temp[i]== -1 || target.isMarked(temp[i])){
						temp[i] = -1;
						count++;
					}
				}
				if(temp.length-count < supInd){
					subList.remove(subInd); // remove temp
					list.get(temp.length-count).add(temp);
					subInd--;
					//CONTINUE??
				} else {
					for(int i = 0;i<temp.length;i++){
						target.mark(temp[i]);
					}
					if(target.getTargetsMarked() >= target.getTargetRange()*perc){
						break firstLoop;
					}
				}
			}
		}
		if(supInd == 0) // the entire list has been traversed and not perc*totalTargets have been marked
			return null;
		
	
		ArrayList<Test> ret = new ArrayList<Test>();
		for(int i = list.size()-1;i>=supInd;i--){
			ArrayList<int[]> temp = list.get(i);
			int max = temp.size()-1;
			if(i==supInd) max = subInd;
			for(int ip = 0;ip<=max;ip++)
				ret.add(map.get(temp.get(ip)));
		}
		return ret;
	}

	
	//getter methods
	public ArrayList<Test> getProblemList(){
		return probList;
	}
	public int getTargetRange(){
		return targetRange;
	}
	public int getNumberOfTests(){
		return numOfTests;
	}
	public int getMaxConnectionsPerTest(){
		return connectsPerTest;
	}
	public String getMapping(int index){
		return dict.get(index);
	}
	
	//private (helper) methods
	private void checkToExpandList(ArrayList<ArrayList<int[]>> list,int ind){
		while(ind>list.size()-1)
			list.add(new ArrayList<int[]>());
	}
	private void compress(int [] array,int rep){
		for(int i = 0;i<array.length;i++){
			if(array[i]==rep)continue;
			for(int ip = i+1;ip<array.length;ip++){
				if(array[ip]!=rep && array[ip] == array[i]){
					array[ip] = rep;
				}
			}
		}
	}
	private void init(){
		dict = new HashMap<Integer,String>();
		increment = 1;
	}
	private int map(String value){
		Set<Entry<Integer,String>>set = dict.entrySet();
		Iterator<Entry<Integer,String>>iter = set.iterator();
		while(iter.hasNext()){
			Entry<Integer,String>e;
			if((e = iter.next()).getValue().equals(value))
				return e.getKey();
		}
		//return -1;
		//If the code reaches here then...
		dict.put(increment,value);
		increment++;
		return increment-1;  //INVESTIGATE IN increment++
	}
}
