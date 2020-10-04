package GUI.Control;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class LargeFileIcon extends JPanel {
	private File file;
	private String	name;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	JPanel titPane;
	public LargeFileIcon(File file,String name) {
		setBackground(Color.WHITE);
		this.file = file;
		setSize(120, 120);
		Icon iconFile = IconLoader.loadIco("ico\\file.png", 80, 80);
		Icon iconFolder = IconLoader.loadIco("ico\\open.png", 80, 80);
		Icon drive = IconLoader.loadIco("ico\\drive.png", 80, 80);
		setLayout(new BorderLayout());
		
		JLabel ico = new JLabel(iconFile);
		JLabel ico2 = new JLabel(iconFolder);
		JLabel ico3	=new JLabel(drive);
		
		 titPane = new JPanel();
		titPane.setBackground(Color.WHITE);
		titPane.setLayout(new FlowLayout());
		JLabel title = new JLabel(name);
		titPane.add(title);
		
		add(titPane,BorderLayout.SOUTH);
		if(file.getParent()==null) {
			add(ico3,BorderLayout.CENTER);
		}
		else if(file.isDirectory()) {
			add(ico2,BorderLayout.CENTER);
		}
		else {
			add(ico,BorderLayout.CENTER);
		}
		
		setVisible(true);
		this.addMouseListener(iconClicked);
		this.addFocusListener(otherIcoClicked);
	}
	private MouseListener iconClicked = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			requestFocus();
			
		}
	};
	private FocusListener otherIcoClicked = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent e) {
			setBackground(Color.WHITE);
			titPane.setBackground(Color.WHITE);
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			setBackground(new Color(179,211,234));
			titPane.setBackground(new Color(179,211,234));
			
		}
	};
}
