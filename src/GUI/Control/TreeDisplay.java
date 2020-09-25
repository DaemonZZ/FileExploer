package GUI.Control;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeDisplay {
	
	
	public TreeDisplay() {


	}

	public static DefaultMutableTreeNode displayTreeFolder(File path) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(path);
		root.setUserObject(path.getName());
		File[] list = path.listFiles();
		
		for (File file : list) {
			if(!file.isHidden()&& file.isDirectory()) {
				root.add(displayTreeFolder(file));
			}
		}
		
		return root;
	}
}
