import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class main {

	public static void main(String[] args) {
		ProblemMaker probMaker = new ProblemMaker();
		FunctionTester tester = new FunctionTester();
		ProblemSolver solver = new ProblemSolver();
		String fileName1 = "InputRand.txt", fileName2 = "InputPerc.txt";
		
		//////MODIFY THESE VALUES /////
		double percInDataSet = 1.0;   // this is the minimum percentage of targets that will be covered by the tests 
		double percToFind = 0.8;      // the minimum amount of tests needed to cover *this percentage* of targets  
		int targetRange = 10000;      // the target range will be 1 to THISVARIABLE
		int numTests = 1000;
		int maxEntriesPerTest = 10;   // the tests can map to 1 to THISVARIABLE targets apiece
		////////////////////////////
		
		probMaker.generateProblem(numTests,targetRange,maxEntriesPerTest,percInDataSet,fileName2);
		
		int [] targ = tester.totalTargetsCovered(fileName2);
		System.out.println(targ[0] + "/" + targ[1] + " targets covered");
		
		
		ArrayList<int[]> answerSet = solver.solveProblem(fileName2,percToFind);	
		
		System.out.println("");
		String s = "";
		if(answerSet == null)
			s += "The data set did not have enough elements to fill " + (percToFind*100) + "% of targets\n";
		else{
			s += "The tests which map to at least " + (percToFind*100) + "% of the targets are:\n\n";
			for(int [] a : answerSet)
				s += Arrays.toString(a) + "\n";
			s+="\nTotal of " + answerSet.size() + " sets printed";
		}	
		
		tester.writeToFile("solution.txt", s);
	}
	
	
	 
}

/*
 * HashMap<Integer,StringBuffer> map = new HashMap<Integer,StringBuffer>();
		StringBuffer buf = new StringBuffer("Trollololol");
		map.put(1,buf);
		System.out.println(map.get(1));*/