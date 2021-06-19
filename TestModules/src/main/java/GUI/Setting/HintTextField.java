package GUI.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintTextField extends JTextField {
	Font gainFont = new Font(Font.SERIF, Font.PLAIN, 12);
	Font lostFont = new Font(Font.SERIF, Font.ITALIC, 12);
	private boolean valid = false;


	public HintTextField(final String hint) {

		setText(hint);
		setFont(lostFont);
		setForeground(Color.GRAY);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));

		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setForeground(Color.BLACK);
					setFont(gainFont);
					valid = false;
				} else {
					setText(getText());
					setForeground(Color.BLACK);
					setFont(gainFont);
					valid = true;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint)|| getText().length()==0) {
					setText(hint);
					setFont(lostFont);
					setForeground(Color.GRAY);
					valid = false;
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(Color.BLACK);
					valid = true;
				}
			}
		});

	}

	@Override
	public boolean isValid() {
		return valid;
	}
}