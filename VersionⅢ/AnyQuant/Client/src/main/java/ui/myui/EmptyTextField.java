package ui.myui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EmptyTextField extends JTextField implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	public EmptyTextField(int x, int y, int width, int height) {
		this.setBounds(x, y, width, height);
		this.setVisible(true);
		this.setOpaque(false);	//控件透明
		this.setForeground(Color.WHITE);//前景色
		this.setFont(new Font("Arail", Font.BOLD, 14));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.addKeyListener(this);
	}

	public void keyTyped(KeyEvent e) {
		if (!(isOK(this.getText().toString()))) {
				this.setText("");
		}
	}

	public void keyPressed(KeyEvent e) {
			if (!isOK(this.getText().toString())) {
				this.setText("");
		}
	}

	public void keyReleased(KeyEvent e) {
			if (!isOK(this.getText().toString())) {
				this.setText("");
		}
	}
	private boolean isOK(String s) {

		boolean i = false;
		if (s.matches("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$")) {
			i = true;
		}
		if (s.matches("^[1-9]\\d*$")) {
			i = true;
		}
		if(s.equals("0")){
			i=true;
		}
		if(s.equals("0.")){
			i=true;
		}
		return i;

	}

}
