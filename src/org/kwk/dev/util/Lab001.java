package org.kwk.dev.util;

import java.io.File;

public class Lab001 {

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
