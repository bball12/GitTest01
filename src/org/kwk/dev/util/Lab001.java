package org.kwk.dev.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Lab001 {
	
	public Lab001() {
	}
	
	public Lab001(String message) {
		this();
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
	
	public void lab001() {
		String temp = "";
		File fileName = new File("C:/SMSImage/SGS_DATA/20160419/20160419000050_105TV_0.txt");
		FileInputStream fin = null;
		Reader reader = null;
		try {
			fin = new FileInputStream(fileName);
			reader = new InputStreamReader(fin, "euc-kr");
			BufferedReader br = new BufferedReader(reader);
			while( (temp = br.readLine()) != null ) {
				String[] bufferJun = temp.split("_");
				System.out.println("[" + bufferJun[0] + "][" + bufferJun[1] + "]");
			}
		} catch(Exception ex) {
			System.out.println("[" + ex.toString() + "]");
		}
	}
	
	public void lab002() {
		
	}
}
