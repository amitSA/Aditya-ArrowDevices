package GUI;

import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import obsolete.ProblemMaker;
import Algorithm.FunctionTester;
import Algorithm.Problem;
import Algorithm.Test;

public class GeneratePanel extends JPanel implements Tabable
{	
  //JPanel [] panelGroups;
	JTextField [] textFields;
	JLabel [] labels;
	JCheckBox cBox;
	JButton genButton;
	JButton openButton;
	JTextField fileNameField;
	JSlider numberLine;
	
	ProblemMaker probMaker;
	FunctionTester tester;
	
	public GeneratePanel(){
		super(new GridBagLayout());
	
		probMaker = new ProblemMaker();
		tester = new FunctionTester();
		
	/*SHOULD I ADD PANELGROUPS INTO THIS PANEL(because of the effects this panels LayoutManager can cause)	
	 * OR SHOULD I ADD THE JLABELS AND TEXTFIELDS SEPARATELY
	 */
		
		JLabel [] tempLab = {new JLabel("Target Range: "),new JLabel("Number of Tests: "),new JLabel("Connects Per Tests: ")};
		labels = tempLab;
		
		JTextField [] tempField = {new JTextField("1000"),new JTextField("100"),new JTextField("10")};
		textFields = tempField;
		
		cBox = new JCheckBox("Set Minimum Percentage");
		cBox.addItemListener(new CheckedButtonHandler());
		
		fileNameField = new JTextField("input.txt");
		fileNameField.setEditable(false);
		fileNameField.setBackground(Color.WHITE);
		
		genButton = new JButton("Generate");
		genButton.addActionListener(new GenerateButtonHandler());
		openButton = new JButton("Open File");
		openButton.addActionListener(new OpenButtonHandler(fileNameField));
		
		JLabel outputTo = new JLabel("Output to  ",JLabel.RIGHT);
		outputTo.setForeground(Color.GRAY);
		
		numberLine = new JSlider(0,100);
		numberLine.setMajorTickSpacing(10);
		numberLine.setPaintLabels(true);
		numberLine.setPaintTicks(true);
		numberLine.setPreferredSize(new Dimension(100,43));
		numberLine.setVisible(false);
		
		for(int i = 0;i<3;i++){
			textFields[i].setColumns(7);
			labels[i].setToolTipText("Sdfsdf");
			tempField[i].setToolTipText("Sdfsdf");
		}
		
		
		
		JLabel lab = new JLabel("                                                  ");
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,0,5,0);
		c.gridx = 0;
		c.gridy = -1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		for(int i = 0;i<labels.length;i++){
			c.gridy += 1;
			this.add(labels[i],c);
		}
		c.gridy = -1;
		c.gridx = 2;
		for(int i = 0;i<labels.length;i++){
			c.gridy += 1;
			this.add(textFields[i],c);
		}
		c.gridy = 3;
		c.gridx = 0;
		this.add(cBox,c);
		c.gridx = 3;
		c.gridwidth = 3;
		this.add(lab,c);
		//the numberline will be at row 4 and can span 2 rows
		c.gridy = 4;
		c.gridheight = 2;
		c.gridwidth = 6;
		c.gridx = 0;
		this.add(numberLine,c);
		
		
		c.gridheight = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridx = 0;
		this.add(genButton,c);
		c.gridx = 2;
		Insets oldIns = c.insets;
		c.gridwidth = 2;
		//c.insets = new Insets(0,10,0,0);
		this.add(fileNameField,c);
		c.insets = oldIns;
		c.gridx = 1;
		c.gridwidth = 2;
		oldIns =  c.insets;
		//c.insets = new Insets(0,30,0,0);
		this.add(outputTo,c);
		//c.insets = oldIns;
		
		
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridx = 0;
		this.add(openButton,c);
		
	}

	
	
	
	class GenerateButtonHandler implements ActionListener{
		//the "Generate" button has been clicked
		public void actionPerformed(ActionEvent e) {
			String [] array = {textFields[0].getText(),textFields[1].getText(),textFields[2].getText()};
			for(String s:array){
				for(int ip = 0;ip<s.length();ip++){
					if(!Character.isDigit(s.charAt(ip))){
						String mess = "Make sure only numbers are inputed into the text fields";
						JOptionPane.showMessageDialog(null, mess,"Invalid Input",JOptionPane.ERROR_MESSAGE,null);
						return;
					}	
				}
			}
			int targetRange = Integer.parseInt(array[0]);
			int numTests = Integer.parseInt(array[1]);
			int numConn = Integer.parseInt(array[2]);
			double perc = numberLine.getValue()/100.0;
			String fileName = fileNameField.getText();
			
			//ArrayList<Test> list;
			Problem problem;
			genButton.setEnabled(false);
			if(!cBox.isSelected()){
				problem = new Problem(numTests, targetRange, numConn);
			}else{
			    String err = "Not enough tests to cover the specified percentage of targets.  \n"
			    		   + "Make sure that    NumberofTests*ConnPerTests >= TargetRange";
				if((problem = new Problem(numTests, targetRange, numConn, perc)).getProblemList() == null)
					JOptionPane.showMessageDialog(null, err,"Invalid Input",JOptionPane.ERROR_MESSAGE,null);
		    }
			if(problem.getProblemList() != null){
				StringBuilder build = new StringBuilder();
				String newLine = System.getProperty("line.separator");
				build.append(numTests + " " + targetRange);
				for(Test t: problem.getProblemList()){
					build.append(newLine);
					build.append(t.toStringBuilder());
				}
				tester.writeToFile(fileName, build);
				openButton.setEnabled(true);
		    }
			genButton.setEnabled(true);	
		}
	}
	class CheckedButtonHandler implements ItemListener{
		//every time the CheckedBox is toggled
		public void itemStateChanged(ItemEvent e) {
			numberLine.setVisible(!numberLine.isVisible());
		}
	}
	
	public void refresh() {
		openButton.setEnabled(doesFileExist(fileNameField.getText()));
	}

	/** This method checks if a file of the fileName inputed exists
	 * @return if  the file exists then the metod returns true, if it does not then the method returns false 
	 */
	private boolean doesFileExist(String fileName){
		File file = new File(fileName);
		return file.exists();
	}
}

/*JButton b = new JButton("Dont Click me");
c.gridy = 0;
c.gridx = 4;
this.add(b,c);
*/

/*c.gridx = 1;
Insets oldIns = c.insets;
c.insets = new Insets(0,10,0,0);
this.add(fileNameField,c);
c.insets = oldIns;*/
