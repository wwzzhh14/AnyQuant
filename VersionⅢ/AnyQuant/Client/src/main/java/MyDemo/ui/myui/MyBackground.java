package MyDemo.ui.myui;

import javax.swing.*;

public class MyBackground extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public MyBackground(String str){
		this.setBounds(0, 0, 1280, 720);
		this.setIcon(new ImageIcon(str));
		this.setVisible(true);
	}
}
