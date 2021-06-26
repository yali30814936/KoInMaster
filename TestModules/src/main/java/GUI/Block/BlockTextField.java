package GUI.Block;

import javax.swing.*;
import java.awt.*;

public class BlockTextField extends JTextField {

	public BlockTextField(String text) {
		super(text);
		setEditable(false);
		setBorder(null);
		setBackground(Color.white);
	}
}
