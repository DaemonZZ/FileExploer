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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.MouseAdapter;

public class MainForm extends JFrame{
	private ArrowButton backbtn,fwbtn,upbtn;
	private  HashMap<DefaultMutableTreeNode,File> listNode = new HashMap<DefaultMutableTreeNode, File>();
	private JTree tree;
	private addressPanel address;
	private String path="";
	JPanel largeView1;
	JScrollPane mainScroll;
	JPopupMenu panelPop;
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
				
				for (File file : root) {
					if(!file.equals(d)){ 
						largeView1.add(new LargeFileIcon(file, file.toString()));
					}
				}
				addPopup(largeView1, panelPop);
				
			}
			else {
				File selected = listNode.get(temp);
				path=(selected.getAbsolutePath());
				File[] childList = selected.listFiles();
				TreeSet<File> lst = new TreeSet<File>();
				for (File file : childList) {
					lst.add(file);
				}
				address.getTxtAdd().setText(path);
				
				 largeView1 = new JPanel();
				 largeView1.setBackground(Color.WHITE);
				 mainScroll.setViewportView(largeView1);
				 int w = mainScroll.getWidth();
				 int h =(childList.length/(w/150))*150;
				 largeView1.setPreferredSize(new Dimension(w,h));
				 largeView1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
				 
				 mainScroll.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							int w = mainScroll.getWidth();
							 int h =(childList.length/((w)/135)+1)*135;
							 largeView1.setPreferredSize(new Dimension(w,h));
						}
					});
				 
				for (File file : lst) {
					
					LargeFileIcon icon = new LargeFileIcon(file, file.getName());
					icon.setPreferredSize(new Dimension(120,120));
					largeView1.add(icon);
					icon.addMouseListener(doubleClicked);
					addPopup(largeView1, panelPop);
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
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHepl = new JMenu("Help");
		menuBar.add(mnHepl);
		
		
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
		treeScroll.setMinimumSize(new Dimension(150,300));
		split.setLeftComponent(treeScroll);
		mainScroll = new JScrollPane();
		mainScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		split.setRightComponent(mainScroll);

		largeView1 = new JPanel();
		largeView1.setBackground(Color.WHITE);
		mainScroll.setViewportView(largeView1);
		largeView1.setPreferredSize(new Dimension(920,656));
		
		 panelPop = new JPopupMenu();
		addPopup(largeView1, panelPop);
		
		JMenu menuView = new JMenu("View");
		panelPop.add(menuView);
		
		JRadioButtonMenuItem titView = new JRadioButtonMenuItem("Title");
		buttonGroup.add(titView);
		titView.setSelected(true);
		menuView.add(titView);
		
		JRadioButtonMenuItem largeView = new JRadioButtonMenuItem("Large Icon");
		buttonGroup.add(largeView);
		menuView.add(largeView);
		
		JRadioButtonMenuItem detailView = new JRadioButtonMenuItem("Details");
		buttonGroup.add(detailView);
		menuView.add(detailView);
		
		JMenuItem menuRefresh = new JMenuItem("Refresh");
		panelPop.add(menuRefresh);
		
		panelPop.addSeparator();
		
		JMenuItem menuPaste = new JMenuItem("Paste");
		panelPop.add(menuPaste);
		largeView1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
		
		JMenu menuNew = new JMenu("New");
		panelPop.add(menuNew);
		
		JMenuItem menuFile = new JMenuItem("file");
		menuNew.add(menuFile);
		
		panelPop.addSeparator();
		
		JMenuItem menuPro = new JMenuItem("Properties");
		panelPop.add(menuPro);
		
		
		 
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
				LargeFileIcon ico = new LargeFileIcon(file, file.toString());
				largeView1.add(ico);
				ico.addMouseListener(doubleClicked);
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
				try {
					root.add(displayTreeFolder(file));
				}
				catch(NullPointerException e) {
					System.out.println(file + " : Không thể truy cập");
				}
			}
		}
		
		return root;
	}
	private MouseListener doubleClicked = new MouseListener() {
		
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
			if (e.getClickCount() == 2 && !e.isConsumed()) {
			     e.consume();
			     System.out.println("OK");
			     LargeFileIcon selectedIco = (LargeFileIcon) e.getComponent();
			     File selected = selectedIco.getFile();
			     System.out.println(selected);
			     path=(selected.getAbsolutePath());
					File[] childList = selected.listFiles();
					TreeSet<File> lst = new TreeSet<File>();
					for (File file : childList) {
						lst.add(file);
					}
					address.getTxtAdd().setText(path);
					
					 largeView1 = new JPanel();
					 largeView1.setBackground(Color.WHITE);
					 mainScroll.setViewportView(largeView1);
					 int w = mainScroll.getWidth();
					 int h =(childList.length/(w/150))*150;
					 largeView1.setPreferredSize(new Dimension(w,h));
					 largeView1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
					 
					 mainScroll.addComponentListener(new ComponentAdapter() {
							@Override
							public void componentResized(ComponentEvent e) {
								int w = mainScroll.getWidth();
								 int h =(childList.length/((w)/135)+1)*135;
								 largeView1.setPreferredSize(new Dimension(w,h));
							}
						});
					 
					for (File file : lst) {
						
						LargeFileIcon icon = new LargeFileIcon(file, file.getName());
						icon.setPreferredSize(new Dimension(120,120));
						largeView1.add(icon);
						icon.addMouseListener(doubleClicked);
					}
					addPopup(largeView1, panelPop);
				
			}
			
		}
	};
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainForm();

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
}
