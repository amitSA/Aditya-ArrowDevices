
public class main {

	public static void main(String[] args) {
		ProblemMaker probMaker = new ProblemMaker();
		FunctionTester tester = new FunctionTester();
		ProblemSolver solver = new ProblemSolver();
		String fileName1 = "InputRand.txt", fileName2 = "InputPerc.txt";
		///////////////////////
		
		//probMaker.generateProblem(10,10,3,1.0,fileName2);
		
		int [] targ = tester.totalTargetsCovered(fileName2);
		System.out.println(targ[0] + "/" + targ[1] + " targets covered");
		
		solver.solveProblem(fileName2,1.0);
	}
}
