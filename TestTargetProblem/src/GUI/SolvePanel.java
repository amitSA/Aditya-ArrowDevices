package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import obsolete.ProblemSolver;
import Algorithm.FunctionTester;
import Algorithm.Problem;
import Algorithm.Test;

public class SolvePanel extends JPanel implements Tabable {

	private JLabel [] labels;
	private JTextField [] textFields;
	private JButton solveButt;
	private JButton openButt;  // equivalent to GeneratePanel's openButton
	private JSlider numberLine;
	private JLabel numLineCaption;
	
	private ProblemSolver solver;
	private FunctionTester tester;
	
	
	
	public SolvePanel() {
		super(new GridBagLayout());
		
		solver = new ProblemSolver();
		tester = new FunctionTester();
		
		labels = new JLabel[]{new JLabel("Input From: "),new JLabel("Output to: ")};
		for(JLabel l: labels)
			l.setForeground(Color.GRAY);
		
		textFields = new JTextField[]{new JTextField("input.txt"),new JTextField("solution.txt")};
		for(int i = 0;i<textFields.length;i++){
			textFields[i].setEditable(false);
			textFields[i].setBackground(Color.WHITE);
			textFields[i].setColumns(6);
		}
		
		solveButt = new JButton("Solve");
		solveButt.addActionListener(new SolveButtonHandler());
		openButt = new JButton("Open File");
		openButt.addActionListener(new OpenButtonHandler(textFields[1]));
		
		numberLine = new JSlider(0,100);
		numberLine.setMajorTickSpacing(10);
		numberLine.setPaintLabels(true);
		numberLine.setPaintTicks(true);
		numberLine.setPreferredSize(new Dimension(100,43));
		
		numLineCaption = new JLabel("Percentage of Targets to Cover");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,0,5,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		
		//this is kind of hacky
		JLabel lab = new JLabel("                                                  ");
		
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 3;
		this.add(numLineCaption,c);
		c.gridx = 3;
		c.gridwidth = 1;
		this.add(lab,c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		this.add(numberLine,c);
		
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1; //reseted
		for(int i = 0;i<labels.length;i++){
			c.gridy++;
			this.add(labels[i],c);
		}
		c.gridy = 1; //reseted
		c.gridx = 2;
		c.gridwidth = 1;
		for(int i = 0;i<labels.length;i++){
			c.gridy++;
			this.add(textFields[i],c);
		}
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4; //reseted
		this.add(solveButt,c);
		c.gridy++;
		this.add(openButt,c);
		
		
	}
	
	/** This method checks if a file of the fileName inputed exists
	 * @return if  the file exists then the metod returns true, if it does not then the method returns false 
	 */
	private boolean doesFileExist(String fileName){
		File file = new File(fileName);
		return file.exists();
	}
	
	class SolveButtonHandler implements ActionListener{

		//this method is called when the "Solve" Button is clicked
		public void actionPerformed(ActionEvent e) {
			
			/*//debugging purposes
			int [] targ = tester.totalTargetsCovered(textFields[0].getText());
			System.out.println(targ[0] + "/" + targ[1] + " targets covered");
			//debugging purposes
			 */
			Problem problem = new Problem(textFields[0].getText());
			String newLine = System.getProperty("line.separator");
			double perc = numberLine.getValue()/100.0;
			ArrayList<Test> solList = problem.solve(perc);
			//ArrayList<int[]> answerSet = solver.solveProblem(textFields[0].getText(), perc);
			StringBuilder s = new StringBuilder();
			if(solList == null)
				s.append("The data set did not have enough elements to fill " + (perc*100) + "% of targets"+newLine);
			else{
				s.append("The tests which map to at least " + (perc*100) + "% of the targets are:"+newLine+newLine);
				for(Test t : solList){
					s.append(t.getID() + ") ");
					int [] array = t.getArray();
					s.append(array.length + " -");
					for(int opp : array)
						s.append(" " + problem.getMapping(opp));
					s.append(newLine);
				}
				s.append(newLine+"Total of " + solList.size() + "/" + problem.getNumberOfTests() + " sets printed");
			}	
			tester.writeToFile("solution.txt", s);
		}
		
	}

	@Override
	public void refresh() {
		solveButt.setEnabled(doesFileExist(textFields[0].getText()));;
		openButt.setEnabled(doesFileExist(textFields[1].getText()));
		}
	
	
}

/*
 * GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,0,5,0);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		//adding the labels
		this.add(labels[0],c);
		c.gridy = 1;
		this.add(labels[1],c);
		
		//adding the TextAreas
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridx = 3;
		this.add(textFields[0]);
		c.gridy = 1;
		
		this.add(textFields[1],c);
 * */
