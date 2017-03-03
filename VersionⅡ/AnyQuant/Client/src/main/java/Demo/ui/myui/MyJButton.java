package Demo.ui.myui;

import javax.swing.*;
import java.awt.*;

public class MyJButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	public MyJButton(String str){
		
		Color foreColor = Color.WHITE;
		Color backColor = MyColor.getColor();
		this.setText(str);
		this.setVisible(true);
		this.setBackground(backColor);
		this.setForeground(foreColor);
	}
}
