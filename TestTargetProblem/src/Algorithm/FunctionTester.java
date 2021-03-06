package Algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FunctionTester {

	//empty constructor(this object's only purpose is to call its indepenent functions)
	public FunctionTester(){};
	
	/** @return a 2 elemeent int array where [0] = targets covered and [1] = total number of targets in set
	 */
	public int [] totalTargetsCovered(String inputFileName)
	{
		BufferedReader in = null;
		Target target = null;
		int targetRange = -1;
		try {
			String newLine  = System.getProperty("line.separator");
			in = new BufferedReader(new FileReader(inputFileName));
		
			String firstLine = in.readLine();
			int space = firstLine.indexOf(' ');
			int numTests = Integer.parseInt(firstLine.substring(0,space));
			targetRange = Integer.parseInt(firstLine.substring(space+1));
			target = new Target(targetRange);
			for(int i = 0;i<numTests;i++)
			{
				String [] temp = in.readLine().split(" ");
				int [] splitArray = new int[temp.length];
				for(int ip = 0;ip<temp.length;ip++)
					splitArray[ip] = Integer.parseInt(temp[ip]); 
				for(int ip = 0;ip<splitArray[0];ip++)
					target.mark(splitArray[ip+1]);
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
		return new int[]{target.getTargetsMarked(),targetRange};
	}

	public void writeToFile(String fileName, String data){
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileName);
			out.write(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			out.close();
		}
	}
	
	public void writeToFile(String fileName, StringBuilder data){
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new PrintWriter(fileName));
			for(int i = 0;i<data.length();i++)
				out.write(data.charAt(i));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(out != null)
				out.close();
			} catch (IOException e) {	
				e.printStackTrace();
			}
		}
	}
}
