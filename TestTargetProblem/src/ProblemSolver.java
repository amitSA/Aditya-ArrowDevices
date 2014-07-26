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
				String line = in.readLine();
				line.
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
		while(ind<=list.size()-1)
			list.add(new ArrayList<int[]>());
	}
	
	public static void main(String[] args) 
	{
		
	}
}