package Demo.ui.myui;

import javax.swing.*;
import java.awt.*;

public class EmptyTextField extends JTextField{

	private static final long serialVersionUID = 1L;
	
	public EmptyTextField(int x, int y, int width, int height){
		this.setBounds(x, y, width, height);
		this.setVisible(true);
		this.setOpaque(false);	//控件透明
		this.setForeground(Color.WHITE);//前景色
		this.setFont(new Font("Arail", Font.BOLD, 14));
		this.setBorder(BorderFactory.createEmptyBorder());
	}
}
