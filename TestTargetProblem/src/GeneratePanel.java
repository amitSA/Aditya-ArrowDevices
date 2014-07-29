import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GeneratePanel extends JPanel
{	
  //JPanel [] panelGroups;
	JTextField [] textFields;
	JLabel [] labels;
	JCheckBox cBox;
	JButton genButton;
	JButton openButton;
	JTextField fileNameField;
	
	public GeneratePanel(){
		super(new GridBagLayout());
	
	/*SHOULD I ADD PANELGROUPS INTO THIS PANEL(because of the effects this panels LayoutManager can cause)	
	 * OR SHOULD I ADD THE JLABELS AND TEXTFIELDS SEPARATELY
	 */
		
		JLabel [] tempLab = {new JLabel("Target Range: "),new JLabel("Number of Tests: "),new JLabel("Connects Per Tests: ")};
		labels = tempLab;
		
		JTextField [] tempField = {new JTextField("1000"),new JTextField("100"),new JTextField("10")};
		textFields = tempField;
		
		cBox = new JCheckBox("Set Minimum Percentage");
		
		genButton = new JButton("Generate");
		openButton = new JButton("Open File");
		openButton.setEnabled(false);
		
		fileNameField = new JTextField("input.txt");
		fileNameField.setEditable(false);
		fileNameField.setBackground(Color.WHITE);
		
		JLabel outputTo = new JLabel("Output to  ",JLabel.RIGHT);
		outputTo.setForeground(Color.GRAY);
		
		for(int i = 0;i<3;i++){
			textFields[i].setColumns(7);
			labels[i].setToolTipText("Sdfsdf");
			tempField[i].setToolTipText("Sdfsdf");
		}
		
		
		
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
		//the numberline will be at row 4 and can span 2 rows
		
		c.gridy = 7;
		c.gridwidth = 1;
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
