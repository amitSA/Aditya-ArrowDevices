import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProblemSolver {

	public ProblemSolver(){};
																			
	public ArrayList<int[]> solveProblem(String fileName,double perc)
	{
		BufferedReader in = null;
		ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
		HashMap <int[],int[]> map = new HashMap<int[],int[]>();
		Target target = null;
		try {
			String newLine  = System.getProperty("line.separator");
			in = new BufferedReader(new FileReader(fileName));
			String firstLn = in.readLine();
			int ind = firstLn.indexOf(' ');
			
			//These are KEY variables
			int numTests = Integer.parseInt(firstLn.substring(0, ind));
			int targetRange = Integer.parseInt(firstLn.substring(ind+1));
			target = new Target(targetRange);
			//technically I only have to use an int[] in an arrayList, but an extra ArrayList makes removes more efficien
			for(int i = 0;i<numTests;i++)
			{
				String [] split = in.readLine().split(" ");
				int [] splitLn = new int[split.length];
				for(int ip = 0;ip<split.length;ip++)
					splitLn[ip] = Integer.parseInt(split[ip]);
				int numEntries = splitLn[0];
				checkToExpandList(list,numEntries);
				int [] temp = new int [numEntries];      //ALSO MAP THIS INT ARRAY WITH COPIED INT ARRAY AS WELL
				for(int ip = 0;ip<splitLn.length-1;ip++) //JUST CHANGE THIS TO IP<NUMENTRIES
					temp[ip] = splitLn[ip+1];
				map.put(temp,Arrays.copyOf(temp,temp.length));
			    compress(temp,-1);
				//add the int[] to the sub arrraylist in reverse order, not at the end
				list.get(numEntries).add(temp);
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
		
	
		ArrayList<int[]> ret = new ArrayList<int[]>();
		for(int i = list.size()-1;i>=supInd;i--){
			ArrayList<int[]> temp = list.get(i);
			int max = temp.size()-1;
			if(i==supInd) max = subInd;
			for(int ip = 0;ip<=max;ip++)
				ret.add(map.get(temp.get(ip)));
		}
		return ret;
	}
	private void checkToExpandList(ArrayList<ArrayList<int[]>> list,int ind){
		while(ind>list.size()-1)
			list.add(new ArrayList<int[]>());
	}
	
	//method to help me debug
	private void printArray(ArrayList<ArrayList<int[]>> list){
		System.out.println("");
		for(int ip = 0;ip<list.size();ip++){
			ArrayList<int[]> l = list.get(ip);
			System.out.print(ip + " Entries: ");
			for(int op = 0;op<l.size();op++){
				System.out.print(Arrays.toString(l.get(op))+" . ");
			}
			System.out.println("");
		}
	}
	/**
	 * $param rep is the variable to replace identical elements in the array
	 */
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
}


/*
/*System.out.println("");
for(int ip = 0;ip<list.size();ip++){
	ArrayList<int[]> l = list.get(ip);
	System.out.print(ip + " Entries: ");
	for(int op = 0;op<l.size();op++){
		System.out.print(Arrays.toString(l.get(op))+" . ");
	}
	System.out.println("");
}*/


/*
if(supInd<2){
	printArray(list);
	System.exit(-1);
}
*/