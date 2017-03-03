package MyDemo.ui.myui;

import javax.swing.*;
import java.awt.*;

public class MyPasswordField extends JPasswordField {

	private static final long serialVersionUID = 1L;

	public MyPasswordField(int x, int y, int width, int height){
		this.setBounds(x, y, width, height);
		this.setVisible(true);
		this.setOpaque(false);
		this.setForeground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder());
	}
}
