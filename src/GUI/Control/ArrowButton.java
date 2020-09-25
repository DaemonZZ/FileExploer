package GUI.Control;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class ArrowButton extends JPanel {
	public ArrowButton(String path) {
		setBackground(Color.WHITE);
		setSize(18, 18);
		Icon ico = IconLoader.loadIco(path, 18, 18);
		JLabel lb = new JLabel(ico);
		this.add(lb);
		setVisible(true);
	}

}
