package GUI.Control;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.LineBorder;

public class addressPanel extends JPanel{
	private JTextField txtAdd;
	public addressPanel() {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setSize(652, 25);
		setLayout(new BorderLayout(0, 0));
		
		JPanel iconPanel = new JPanel();
		iconPanel.setBackground(Color.WHITE);
		add(iconPanel,BorderLayout.WEST);
		
		JLabel lbIcon = new JLabel("");
		iconPanel.add(lbIcon);
		lbIcon.setPreferredSize(new Dimension(20,20));
		
		txtAdd = new JTextField();
		add(txtAdd, BorderLayout.CENTER);
		txtAdd.setColumns(1000);
		txtAdd.setBorder(null);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.WHITE);
		add(btnPanel, BorderLayout.EAST);
		
		Icon folderIcon = IconLoader.loadIco("ico\\folder.png", 20, 20);
		lbIcon.setIcon(folderIcon);
		
		ArrowButton btnDown = new ArrowButton("ico\\down.png");
		btnPanel.add(btnDown);
		ArrowButton btnRefresh = new ArrowButton("ico\\refresh.png");
		btnPanel.add(btnRefresh);
		
		
		setVisible(true);
	}

	
}
