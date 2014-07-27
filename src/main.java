import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class main {

	public static void main(String[] args) {
		ProblemMaker probMaker = new ProblemMaker();
		FunctionTester tester = new FunctionTester();
		ProblemSolver solver = new ProblemSolver();
		String fileName1 = "InputRand.txt", fileName2 = "InputPerc.txt";
		
		//////MODIFY THIS VALUE/////
		double perc = 0.8;
		////////////////////////////
		
		probMaker.generateProblem(1000,10000,10,perc,fileName2);
		
		int [] targ = tester.totalTargetsCovered(fileName2);
		System.out.println(targ[0] + "/" + targ[1] + " targets covered");
		
		
		ArrayList<int[]> answerSet = solver.solveProblem(fileName2,perc);	
		
		System.out.println("");
		if(answerSet == null)
			System.out.println("The data set did not have enough elements to fill " + (perc*100) + "% of targets");
		else{
			System.out.println("The tests which map to at least " + (perc*100) + "% targets are:");
			for(int [] a : answerSet)
				System.out.println(Arrays.toString(a));
		}
			
		System.out.println("\nTotal of " + answerSet.size() + " sets printed");
		
	}
	
	
	 
}

/*
 * HashMap<Integer,StringBuffer> map = new HashMap<Integer,StringBuffer>();
		StringBuffer buf = new StringBuffer("Trollololol");
		map.put(1,buf);
		System.out.println(map.get(1));*/
