package org.kwk.dev.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileControl {
	
	public static boolean copy(File sourceFile, String target) {
		boolean ret 		= true; // 기본값을 true. 중간에 에러 발생시 false 셋팅.

		//스트림, 채널 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel fcin = null;
		FileChannel fcout = null;

		try {
			//스트림 생성
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(target);
			//채널 생성
			fcin = inputStream.getChannel();
			fcout = outputStream.getChannel();

			//채널을 통한 스트림 전송
			long size = fcin.size();
			long copySize = fcin.transferTo(0, size, fcout);
			if(copySize < 1) ret 		= false; // 복사된 크기가 1보다 작을때 false 반환
		} catch (Exception e) {
			ret 		= false; // 예외 발생시 false 반환
			e.printStackTrace();
		} finally {
			//자원 해제
			try{
				fcout.close();
			}catch(IOException ioe){ ioe.printStackTrace() ;}
			try{
				fcin.close();
			}catch(IOException ioe){ ioe.printStackTrace() ;}
			try{
				outputStream.close();
			}catch(IOException ioe){ ioe.printStackTrace() ;}
			try{
				inputStream.close();
			}catch(IOException ioe){ ioe.printStackTrace() ;}
		}
		return ret;
	}
}
