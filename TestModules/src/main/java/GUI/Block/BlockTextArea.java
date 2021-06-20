package GUI.Block;

import javax.swing.*;
import java.awt.*;

public class BlockTextArea extends JTextArea {
	public BlockTextArea() {
		this("");
	}

	public BlockTextArea(String text) {
		super(text);
		setEditable(false);
		setBorder(null);
		setBackground(Color.white);
	}
}
