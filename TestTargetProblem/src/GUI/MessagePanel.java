package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;


public class MessagePanel extends JPanel implements ActionListener
{
	private Tabable [] tabArray;
	private Timer timer;
	
	public MessagePanel(){
		super(new BorderLayout());
		
		JTabbedPane tabs = new JTabbedPane();
		
		
		JPanel genPanel = new GeneratePanel();
		JPanel solvePanel = new SolvePanel();
		tabs.addTab("<html><body marginwidth=15 marginheight=5><h2>Generate</h2></body></html>",genPanel);
		tabs.addTab("<html><body marginwidth=15 marginheight=5><h2>Solve</h2></body></html>",solvePanel);
	
		tabArray = new Tabable[]{(Tabable)genPanel,(Tabable)solvePanel};
		
		tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.add(tabs);
		this.setPreferredSize(new Dimension(500,500));
		this.setVisible(true);
		
		timer = new Timer(700,this);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		for(Tabable t: tabArray)
			t.refresh();
	}
	
}
