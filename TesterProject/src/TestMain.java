import java.awt.*;
import java.io.*;
import javax.swing.*;


public class TestMain {

	public static void main(String[] args) {
		
		
		String [] options = {};
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JButton("kkkkkllllllllllllllllllllllllllkkkkkllllllllllllllllllllllllllkkkkkllllllllllllllllllllllllll"));
		panel.setVisible(true);
		
		JOptionPane.showOptionDialog(null, 
				                      panel, 
				                     "This is the title param", 
				                     JOptionPane.DEFAULT_OPTION,
				                     JOptionPane.QUESTION_MESSAGE,
				             /*Icon*/null, 
				                     options,
				                     0);
	}

}




/*public static void main(String[] args) {
	File file = new File("res/InputPerc.txt");
	JFileChooser fileChooser = new JFileChooser(file);
	int uInput = fileChooser.showOpenDialog(null);
	if(uInput == 0 && Desktop.isDesktopSupported()){
		File f = fileChooser.getSelectedFile();
		Desktop dt = Desktop.getDesktop();
		try {
			dt.open(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}*/