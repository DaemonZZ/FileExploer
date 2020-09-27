package GUI;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;

import GUI.Control.ArrowButton;
import GUI.Control.IconLoader;
import GUI.Control.LargeFileIcon;
import GUI.Control.addressPanel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;
import java.util.TreeSet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.tree.DefaultTreeModel;


import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainForm extends JFrame{
	private ArrowButton backbtn,fwbtn,upbtn;
	private  HashMap<DefaultMutableTreeNode,File> listNode = new HashMap<DefaultMutableTreeNode, File>();
	private JTree tree;
	private addressPanel address;
	private String path="";
	JPanel largeView1;
	JScrollPane mainScroll;
	TreeSelectionListener tsl = new TreeSelectionListener() {
		
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode temp = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(temp.toString()=="ThisPC") {
				largeView1 = new JPanel();
				largeView1.setBackground(Color.WHITE);
				mainScroll.setViewportView(largeView1);
				largeView1.setPreferredSize(new Dimension(920,656));
				largeView1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
				
				
				 
				mainScroll.addComponentListener(new ComponentAdapter() {
							@Override
							public void componentResized(ComponentEvent e) {
								largeView1.setPreferredSize(new Dimension(mainScroll.getSize().width,largeView1.getHeight()));
							}
						});
				
				File d =new File("C:\\");
				FileSystemView sys = FileSystemView.getFileSystemView();
				File[] root = File.listRoots();
				TreeSet<File> lstRoot = new TreeSet<File>();
				for (File file : root) {
					if(!file.equals(d)){ 
						largeView1.add(new LargeFileIcon(file, file.toString()));
					}
				}
				
			}
			else {
				File selected = listNode.get(temp);
				path=(selected.getAbsolutePath());
				address.getTxtAdd().setText(path);
				
				 largeView1 = new JPanel();
				 largeView1.setBackground(Color.WHITE);
				 mainScroll.setViewportView(largeView1);
				 largeView1.setPreferredSize(new Dimension(920,656));
				 largeView1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
				 
				 mainScroll.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							largeView1.setPreferredSize(new Dimension(mainScroll.getSize().width,largeView1.getHeight()));
						}
					});
				 
				File[] childList = selected.listFiles();
				for (File file : childList) {
					LargeFileIcon icon = new LargeFileIcon(file, file.getName());
					icon.setPreferredSize(new Dimension(120,120));
					largeView1.add(icon);
				}
			}
			
		}
	};
	public MainForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("ico\\pc.png"));
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
		
		 address = new addressPanel();
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
		
		mainScroll = new JScrollPane();
		mainScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		split.setRightComponent(mainScroll);

		largeView1 = new JPanel();
		largeView1.setBackground(Color.WHITE);
		mainScroll.setViewportView(largeView1);
		largeView1.setPreferredSize(new Dimension(920,656));
		largeView1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		
		
		 
		mainScroll.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						largeView1.setPreferredSize(new Dimension(mainScroll.getSize().width,largeView1.getHeight()));
					}
				});
		
		File d =new File("C:\\");
		FileSystemView sys = FileSystemView.getFileSystemView();
		File[] root = File.listRoots();
		TreeSet<File> lstRoot = new TreeSet<File>();
		for (File file : root) {
			if(!file.equals(d)){ 
				lstRoot.add(file);
				System.out.println("Loaded : "+file);
				largeView1.add(new LargeFileIcon(file, file.toString()));
			}
		}
		
		 tree = new JTree();
		 tree.addTreeSelectionListener(tsl);
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		Icon open = IconLoader.loadIco("ico\\open.png", 10, 10);
		Icon close = IconLoader.loadIco("ico\\close.png", 10, 10);
		
		
		renderer.setOpenIcon(open);
		renderer.setClosedIcon(close);
		renderer.setLeafIcon(close);
		
		
		DefaultMutableTreeNode thispc =new DefaultMutableTreeNode("ThisPC");
		DefaultTreeModel model = new DefaultTreeModel(thispc);
		for (File file : lstRoot) {
			DefaultMutableTreeNode node = displayTreeFolder(file);
			node.setUserObject(file);
			thispc.add(node);
		}
		tree.setModel(model);
//		
		
		treeScroll.setViewportView(tree);
		
		
		
		 
		 
		
		
		setVisible(true);
		
	}

	public  DefaultMutableTreeNode displayTreeFolder(File path) {
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(path);
		root.setUserObject(path.getName());
		listNode.put(root, path);
		File[] list = path.listFiles();
		
		for (File file : list) {
			if(!file.isHidden()&& file.isDirectory()) {
				
				root.add(displayTreeFolder(file));
				
			}
		}
		
		return root;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainForm();

	}

}
