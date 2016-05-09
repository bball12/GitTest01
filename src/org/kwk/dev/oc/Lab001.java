package org.kwk.dev.oc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.lab.LabEffector;

public class Lab001 extends LabEffector {
	
	@Override
	public void doTest() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 파일 갯수를 체크한다.
	 */
	@SuppressWarnings("unused")
	public void lab001() {
//		String sourceFolderName = "C:/SMSImage/SGS_DATA";
//		File sourceFolder = new File(sourceFolderName);
//		
//		String[] sourceDayFolderNames = sourceFolder.list();
//		for (int i = 0; i < sourceDayFolderNames.length; i++) {
//			System.out.println("[" + sourceDayFolderNames[i] + "]");
//		}
//		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String currentDay = formatter.format(cal.getTime());
//		System.out.println("[" + regDate + "]");
		
		String cctvStorePath = "";
		
		int allFileCnt = 0;
		int txtFileCnt = 0;
		int allLockFileCnt = 0;
		int txtLockFileCnt = 0;
		String folderCheck = "NOT";
		FixedCCTVSystemVO vo = new FixedCCTVSystemVO();
		FixedCCTVSystemVO fixedCCTVSystemVO = getFixedCctvDriveChk("43730");
		
		if (fixedCCTVSystemVO != null) {
			cctvStorePath = fixedCCTVSystemVO.getRegltPhotoStorePath();
		} else {
			System.out.println("NO");
		}
		
		File cctvStoreFolder = new File(cctvStorePath);
		FilenameFilter filter = new FilenameFilter(){
			public boolean accept(File dir, String name){
				String lowerCase = name.toLowerCase();
				return lowerCase.endsWith(".txt");
			}
		};
		
		String[] dayFolderNames = cctvStoreFolder.list();
		File dayFolder = null;
		boolean normalFolder = false;
		boolean lockFolder = false;
		for (int i = 0; i < dayFolderNames.length; i++) {
			// 작업완료 폴더 제외
			if (dayFolderNames[i].indexOf("_F") >= 0)
				continue;
			
			// 금일날자 폴더 제외
			if (currentDay.equals(dayFolderNames[i]))
				continue;

			dayFolder = new File(cctvStorePath + "/" + dayFolderNames[i]);
			
			if (dayFolderNames[i].indexOf("_lock") < 0) {
				allFileCnt += dayFolder.list().length;
				txtFileCnt += dayFolder.list(filter).length;
				normalFolder = true;
			} else {
				allFileCnt += dayFolder.list().length;
				txtFileCnt += dayFolder.list(filter).length;
				allLockFileCnt += dayFolder.list().length;
				txtLockFileCnt += dayFolder.list(filter).length;
				lockFolder = true;
			}
			
			if (normalFolder && lockFolder)
				folderCheck = "oo"; 
			else if(!normalFolder && lockFolder)
				folderCheck = "xo";
			else if(normalFolder && !lockFolder)
				folderCheck = "ox";
			else if(!normalFolder && !lockFolder)
				folderCheck = "xx";

		}
		
		System.out.println("[" + allFileCnt + "][" + txtFileCnt + "][" + allLockFileCnt + "][" + txtLockFileCnt + "]");
		System.out.println("[" + folderCheck + "]");

	}
	
	
	@SuppressWarnings("unused")
	private void lab002() {
		String testFolerName = "C:/SMSImage/SGS_DATA";
		String destFolerName = "C:/SMSImage/SGS_DATA/Dest";
		File testFolder = new File(testFolerName);
		File[] testFiles = testFolder.listFiles();

		boolean isDirectory;
		boolean isFile;

		File destFolder = new File(destFolerName);
		if (!destFolder.exists()) destFolder.mkdirs();

		String showFileType = "";
		String extensionName = "";

		String crackDownTime = "";				// 단속일시
		String vehicleNumber = "";				// 차량번호
		String siteName = "";					// 현장명
		String cameraNumber = "";				// 카메라 번호
		String parkingGb = "";					// 주정차 구분
		String imageIndex = "";					// 이미지 인덱스
		String fileSequence = "";				// 파일순번
		String specialProtectionArea = "";		// 특별보호구역

		FileInputStream fileInputStream = null;
		
		for (int i = 0 ; i < testFiles.length; i++) {
			
			if (testFiles[i].isFile()) {
				extensionName = testFiles[i].getName().substring(testFiles[i].getName().lastIndexOf("."));
//				System.out.println("[" + i + "]:[" + extensionName + "]");
			} 
					
			if (testFiles[i].isDirectory()) showFileType = "D";
			if (testFiles[i].isFile()) showFileType = "F";
			
			if (showFileType.equals("F") && extensionName.equals(".txt")) {
				System.out.println("-------------------");
				System.out.println("[" + i + "][" + showFileType + "][" + testFiles[i].getName() + "]");
				byte[] fileNameBytes = testFiles[i].getName().getBytes();
			
				crackDownTime = new String(fileNameBytes, 0, 14);
				cameraNumber = new String(fileNameBytes, 15, 3);
				fileSequence = new String(fileNameBytes, 21, 1);
				
				try {
					fileInputStream = new FileInputStream(testFiles[i]);
					int bbSize = fileInputStream.read();
					byte[] bb = new byte[bbSize];
					fileInputStream.read(bb);
					
					String[] fileContents = new String(bb, 0, bbSize, "EUC-KR").trim().split("_");
					vehicleNumber = fileContents[0];
					specialProtectionArea = fileContents[1];


				} catch (FileNotFoundException ex) {
					
				} catch (IOException ex) {
					
				}

				System.out.println("[단속일시][" + crackDownTime + "]");
				System.out.println("[카메라 고유번호][" + cameraNumber + "]");
				System.out.println("[파일순번][" + fileSequence + "]");
				System.out.println("[차량번호][" + vehicleNumber + "]");
				System.out.println("[특별보호구역][" + specialProtectionArea + "]");
			}
		
		}
	}
	
	@SuppressWarnings("unused")
	private void lab003() {
		String testFolerName = "C:/SMSImage/SGS_DATA/ABC/";
		File testFolder = new File(testFolerName);
		File newFolder = new File(testFolder.getAbsolutePath() + "_lock");
		
		boolean isRenamed = testFolder.renameTo(newFolder);
		
		System.out.println("[" + isRenamed + "][" + testFolder.getName() + "]");
		System.out.println("[" + isRenamed + "][" + newFolder.getName() + "]");
		
//		System.out.println("[" + testFolder.getAbsolutePath() + "]");
		
		String[] fileNames = newFolder.list();
		for (int i = 0; i < fileNames.length; i++) {
			System.out.println("[" + i + "][" + fileNames[i] + "]");
		}
	}
	private FixedCCTVSystemVO getFixedCctvDriveChk (String sectCd ) {
    	FixedCCTVSystemVO vo = new FixedCCTVSystemVO();
    	if (sectCd.equals("43730")) {
    		vo.setOfficeCd("43730");
			vo.setOfficeNm("옥천군청");
			vo.setRegltPhotoStorePath("C:/SMSImage/" + "SGS_DATA" ); //서버환경테스트
    	}
    	
    	return vo;
	}
    
    public boolean copy(File sourceFile, String target) {
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
