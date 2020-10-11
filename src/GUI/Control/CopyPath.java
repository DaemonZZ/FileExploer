package GUI.Control;

import java.io.File;

public class CopyPath {
	private File f;
	private int cmd;
	public static int COPY =1, CUT=0; 
	public File getF() {
		return f;
	}
	public void setF(File f) {
		this.f = f;
	}
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public CopyPath(File f, int cmd) {
		super();
		this.f = f;
		this.cmd = cmd;
	}
	
}
