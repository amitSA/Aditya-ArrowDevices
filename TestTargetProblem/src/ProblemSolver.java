import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProblemSolver {

	public ProblemSolver(){};
																			
	public void solveProblem(String fileName)
	{
		BufferedReader in = null;
		
		try {
			String newLine  = System.getProperty("line.separator");
			in = new BufferedReader(new FileReader(fileName));
			String firstLn = in.readLine();
			int ind = firstLn.indexOf(' ');
			
			//These are KEY variables
			int numTests = Integer.parseInt(firstLn.substring(0, ind));
			int targetRange = Integer.parseInt(firstLn.substring(ind+1));
			
			//technically I only have to use an int[] in an arrayList, but an extra ArrayList makes removes more efficient
			ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
			for(int i = 0;i<numTests;i++)
			{
				String [] split = in.readLine().split(" ");
				int [] splitLn = new int[split.length];
				for(int ip = 0;ip<split.length;ip++)
					splitLn[ip] = Integer.parseInt(split[ip]);
				int numEntries = splitLn[0];
				checkToExpandList(list,numEntries);
				int [] temp = new int [numEntries];
				for(int ip = 0;ip<splitLn.length-1;ip++) //JUST CHANGE THIS TO IP<NUMENTRIES
					temp[ip] = splitLn[ip+1];
				
				//add the int[] to the sub arrraylist in reverse order, not at the end
				list.get(list.size()-1-numEntries).add(temp);
			}
			
			System.out.println("");
			for(int ip = 0;ip<list.size();ip++){
				ArrayList<int[]> l = list.get(ip);
				System.out.print(list.size()-ip + " Entries: ");
				for(int op = 0;op<l.size();op++){
					int [] temp = l.get(op);
					for(int up = 0;up<temp.length;up++)
						System.out.print(temp[up] + " ");
				}
				System.out.println("");
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
	
	private void checkToExpandList(ArrayList<ArrayList<int[]>> list,int ind){
		while(ind>list.size()-1)
			list.add(new ArrayList<int[]>());
	}
}