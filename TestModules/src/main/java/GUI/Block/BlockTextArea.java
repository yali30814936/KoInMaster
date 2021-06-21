package GUI.Block;

import javax.swing.*;
import java.awt.*;

public class BlockTextArea extends JTextArea {

	public BlockTextArea(String text) {
		super(text);
		setEditable(false);
		setWrapStyleWord(true);
		setLineWrap(true);
		setBorder(null);
		setBackground(Color.white);
	}
}
