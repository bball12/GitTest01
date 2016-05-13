package org.kwk.dev.util;

import java.io.File;

public class Lab001 {

	private String name = null;
	
	public Lab001() {
		this.name = "lab001~ ok?";
	}
	
	public Lab001(String message) {
		this();
		System.out.println(message);
	}
	
	public void showName() {
		System.out.println("[" + this.name + "]");
	}
	
	public boolean isDriveCheck() {
		File[] files = File.listRoots();
		String drive = "";
		boolean ret = false;
		for(File file : files){
			drive = file.getAbsolutePath().replaceAll("\\\\", "/");
			System.out.println("[" + drive + "]");
			if("C:/SMSImage/".equals(drive)) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
