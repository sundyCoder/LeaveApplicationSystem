package com.util;

public class WorkingDir {
	String workingDir;
	String imageDir;
	String docDir;
	String libDir;

	public WorkingDir() {
		workingDir = System.getProperty("user.dir");
		if (!workingDir.endsWith("\\")) {
			workingDir += "\\";
		}
	}
	
	public String getWorkDir(){		
		return workingDir;
	}
	
	public String getImageDir() {
		imageDir = workingDir + "imgs\\";
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public String getDocDir() {
		docDir = workingDir + "doc\\";
		return docDir;
	}

	public void setDocDir(String docDir) {
		this.docDir = docDir;
	}

	public String getLibDir() {
		libDir = workingDir + "lib\\";
		return libDir;
	}

	public void setLibDir(String libDir) {
		this.libDir = libDir;
	}
}
