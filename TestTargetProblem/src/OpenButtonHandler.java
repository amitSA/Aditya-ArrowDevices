import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.text.JTextComponent;

class OpenButtonHandler implements ActionListener{
	
	private JTextComponent text;
	
	public OpenButtonHandler(JTextComponent t){
		this.text = t;
	}
	
	//this method is called every time the "Open File" button is clicked
	public void actionPerformed(ActionEvent e) {
		if(Desktop.isDesktopSupported()){
			File f = new File(text.getText());
			Desktop dt = Desktop.getDesktop();
			try {
				dt.open(f);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
		
	}