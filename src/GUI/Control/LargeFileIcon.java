package GUI.Control;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;

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
		
		JPanel titPane = new JPanel();
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
		
	}
	
}
