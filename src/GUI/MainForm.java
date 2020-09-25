package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import GUI.Control.ArrowButton;
import GUI.Control.IconLoader;
import GUI.Control.TreeDisplay;
import GUI.Control.addressPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Scrollbar;
import java.io.File;
import java.util.TreeSet;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MainForm extends JFrame{
	private ArrowButton backbtn,fwbtn,upbtn;
	public MainForm() {
		setSize(1204, 768);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
		
		
		JScrollPane treePanel = new JScrollPane();
		treePanel.setBackground(Color.WHITE);
		getContentPane().add(treePanel,BorderLayout.WEST);

		
		backbtn = new ArrowButton("ico\\back.png");
		backbtn.setPreferredSize(new Dimension(50, 30));
		fwbtn = new ArrowButton("ico\\right.png");
		fwbtn.setPreferredSize(new Dimension(50, 35));
		upbtn = new ArrowButton("ico\\up.png");
		upbtn.setPreferredSize(new Dimension(50, 35));
		
		addressPanel address = new addressPanel();
		address.setPreferredSize(new Dimension(80,27));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		panel.add(backbtn,BorderLayout.WEST);
		panel.add(fwbtn,BorderLayout.WEST);
		panel.add(upbtn,BorderLayout.WEST);
		panel.add(address,BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane split = new JSplitPane();
		mainPanel.add(split,BorderLayout.CENTER);
		
		JScrollPane treeScroll = new JScrollPane();
		treeScroll.setPreferredSize(new Dimension(250,1));
		split.setLeftComponent(treeScroll);
		
		File d =new File("C:\\");
		FileSystemView sys = FileSystemView.getFileSystemView();
		File[] root = File.listRoots();
		TreeSet<File> lstRoot = new TreeSet<File>();
		for (File file : root) {
			if(!file.equals(d)){ 
				lstRoot.add(file);
				System.out.println(file);
			}
		}
		
		JTree tree = new JTree();
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		Icon open = IconLoader.loadIco("ico\\open.png", 10, 10);
		Icon close = IconLoader.loadIco("ico\\close.png", 10, 10);
		
		renderer.setOpenIcon(open);
		renderer.setClosedIcon(close);
		renderer.setLeafIcon(close);
		
		
		DefaultMutableTreeNode thispc =new DefaultMutableTreeNode("ThisPC");
		DefaultTreeModel model = new DefaultTreeModel(thispc);
		for (File file : lstRoot) {
			DefaultMutableTreeNode node = TreeDisplay.displayTreeFolder(file);
			node.setUserObject(file);
			thispc.add(node);
		}
		tree.setModel(model);
//		tree.setModel(new DefaultTreeModel(
//			new DefaultMutableTreeNode("ThisPC") {
//				{
//					
//					for (File file : root) {
//						System.out.println(file);
//						add(TreeDisplay.displayTreeFolder(d));
//					}
//					
//				}
//			}
//		));
		
		treeScroll.setViewportView(tree);
		
		JScrollPane mainScroll = new JScrollPane();
		split.setRightComponent(mainScroll);
		
		JPanel listFolder = new JPanel();
		mainScroll.setViewportView(listFolder);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainForm();

	}

}
