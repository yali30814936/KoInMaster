package GUI.Block;

import javax.swing.*;
import java.awt.*;

public class BlockTextArea extends JTextArea {

	public BlockTextArea(String text) {
		super(text);
//		setMaximumSize(new Dimension(1500, Integer.MAX_VALUE));
		setEditable(false);
		setWrapStyleWord(true);
		setLineWrap(true);
		setBorder(null);
		setBackground(Color.white);
	}
}
