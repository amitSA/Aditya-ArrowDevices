package main;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import GUI.MessagePanel;


public class main {
	
	/*SEARCH THROUGH SOURCE CODE AND REPLACE INSTANCES OF STRINGBUFFER WITH STRINGBUILDER
	 * SEARCH THROUGH SOURCE CODE AND REPLACE INSTANCES OF STRINGBUFFER WITH STRINGBUILDER
	 * SEARCH THROUGH SOURCE CODE AND REPLACE INSTANCES OF STRINGBUFFER WITH STRINGBUILDER
	 * SEARCH THROUGH SOURCE CODE AND REPLACE INSTANCES OF STRINGBUFFER WITH STRINGBUILDER
	 * SEARCH THROUGH SOURCE CODE AND REPLACE INSTANCES OF STRINGBUFFER WITH STRINGBUILDER
	 * SEARCH THROUGH SOURCE CODE AND REPLACE INSTANCES OF STRINGBUFFER WITH STRINGBUILDER
	 */

	public static void main(String[] args) {
		UIManager.put("ToolTip.font",
				new FontUIResource("SansSerif", Font.BOLD, 18));
				
		MessagePanel messPanel = new MessagePanel();
		
		JFrame frame = new JFrame("TestTargetProblem");
		frame.add(messPanel);
		frame.setPreferredSize(new Dimension(450,450));
		frame.setResizable(true);
		frame.setVisible(true);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
		
	 
}
/*
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
		
		USE STRING BUFFER
		
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
	}*/

/*JFrame frame = new JFrame("Particle System");
frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
frame.setResizable(true);

GamePanel gamePanel = new GamePanel();
frame.add(gamePanel);

frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/