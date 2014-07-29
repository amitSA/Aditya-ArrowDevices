import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GeneratePanel extends JPanel
{	
	JPanel [] panelGroups;
	JTextField [] textFields;
	JLabel [] labels;
	
	public GeneratePanel(){
		super(new GridBagLayout());
	
	/*SHOULD I ADD PANELGROUPS INTO THIS PANEL(because of the effects this panels LayoutManager can cause)	
	 * OR SHOULD I ADD THE JLABELS AND TEXTFIELDS SEPARATELY
	 */
		
		JLabel [] tempLab = {new JLabel("Target Range"),new JLabel("Number of Tests"),new JLabel("Connects Per Tests")};
		labels = tempLab;
		
		JTextField [] tempField = {new JTextField("1000"),new JTextField("100"),new JTextField("10")};
		textFields = tempField;
		
		
		
		panelGroups = new JPanel[3];
		for(int i = 0;i<panelGroups.length;i++){
			JPanel p = new JPanel(new FlowLayout());
			//add the JLabels then the JTextArea
			p.add(labels[i]);
			p.add(textFields[i]);
			panelGroups[i] = p;
		}
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = -1;
		c.gridwidth = 2;
		for(int i = 0;i<panelGroups.length;i++){
			c.gridy += 1;
			this.add(panelGroups[i],c);
		}
		
	}
}
