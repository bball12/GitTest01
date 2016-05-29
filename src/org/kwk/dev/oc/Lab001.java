package org.kwk.dev.oc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.lab.LabEffector;

public class Lab001 extends LabEffector {
	
	@Override
	public void doRun() {
		// TODO Auto-generated method stub
		lab004();
	}
	
	@SuppressWarnings("unused")
	private void lab000() {
		System.out.println("lab000().....");
	}
	
	/**
	 * 파일 갯수를 체크한다.
	 */
	@SuppressWarnings("unused")
	private void lab001() {
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
				System.out.println("[파일명][" + testFiles[i].getName() + "]");
				System.out.println("[파일명 사이즈][" + testFiles[i].getName().length() + "]");
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void lab003() {
		String testFolerName = "C:/SMSImage/ABC/";
		File testFolder = new File(testFolerName);
		File lockFolder = new File(testFolder.getAbsolutePath() + "_lock");
		File finalFolder = new File(testFolder.getAbsolutePath() + "_F");
		
		boolean isRenamed = testFolder.renameTo(lockFolder);
		
		System.out.println("[" + isRenamed + "][" + testFolder.getName() + "]");
		System.out.println("[" + isRenamed + "][" + lockFolder.getName() + "]");
		
//		System.out.println("[" + testFolder.getAbsolutePath() + "]");
		
		String[] fileNames = lockFolder.list();
		for (int i = 0; i < fileNames.length; i++) {
			System.out.println("[" + i + "][" + fileNames[i] + "]");
		}
		
		isRenamed = lockFolder.renameTo(finalFolder);
		System.out.println("[" + isRenamed + "][" + lockFolder.getName() + "]");
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
	
	/**
	 * 옥천군 사전알리미 파일 분석 테스트
	 */
	private void lab004() {
		
		List<FixedCCTVSystemVO> cctvSytmList = new ArrayList<FixedCCTVSystemVO>();
		
		FixedCCTVSystemVO ocFixedCCTVSystemVO = new FixedCCTVSystemVO();
		ocFixedCCTVSystemVO.setOfficeCd("43730");
		ocFixedCCTVSystemVO.setOfficeNm("옥천군청");
		ocFixedCCTVSystemVO.setSmsStorePath("C:/SMSImage/SMSnotice");
		
		cctvSytmList.add(ocFixedCCTVSystemVO);
		
		for (FixedCCTVSystemVO fixedCCTVSystemVO : cctvSytmList) {
			String smsStorePath = fixedCCTVSystemVO.getSmsStorePath();
			String officeCd = fixedCCTVSystemVO.getOfficeCd();
			File smsStoreFolder = new File(smsStorePath);
			
			String[] fileList = smsStoreFolder.list();
			File fileItm = null;
			String movePath = null;

			for (int i = 0; i < fileList.length; i++) {
				fileItm = null;
				fileItm = new File(smsStorePath + File.separatorChar + fileList[i]);
				
				SMSNotice001VO recvNotice001VO = new SMSNotice001VO();
				recvNotice001VO.setAdmiCd("43730");
				recvNotice001VO.setSectCd(fixedCCTVSystemVO.getOfficeCd());
				
				boolean ret = false;
				try {
					ret = makeSMSNoticeByFile(fileItm, recvNotice001VO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}

	}
	
	private boolean makeSMSNoticeByFile(File srcFile ,SMSNotice001VO tgtVO) throws Exception {
		boolean ret = true; // 기본값을 true. 중간에 에러 발생시 false 셋팅.
		if ( srcFile == null || tgtVO == null ){ // 파라미터가 null 이면 return false 하여 종료.
			ret = false;
			return ret;
		}
		
		try {
			String fileNm = srcFile.getName();

			tgtVO.setFileNm(fileNm);
			tgtVO.setFileSize(""+srcFile.length());

			byte[] bb_fileName = fileNm.getBytes();
			
			System.out.println("[bb_fileName.length]:[" + bb_fileName.length + "]");
			System.out.println("[" + new String(bb_fileName) + "]");
			System.out.println("[" + new String(bb_fileName, 0, 14) + "]");
			System.out.println("[" + new String(bb_fileName, 14, 12) + "]");
			System.out.println("[" + new String(bb_fileName, 26, 3) + "]");
			System.out.println("[" + new String(bb_fileName, 29, 30) + "]");
			System.out.println("[" + new String(bb_fileName, 59, 1) + "]");
			System.out.println("[" + new String(bb_fileName, 60, 1) + "]");
			System.out.println("[" + new String(bb_fileName, 61, 4) + "]");

			if (bb_fileName.length != 65) return false;

			String chkDtm = new String(bb_fileName, 0, 14);					// 단속일시
			String vehiNo = new String(bb_fileName, 14, 12);				// 차량번호
			String trEqup = new String(bb_fileName, 26, 3);					// 단속장비(CCTV) 고유번호
			String equpIp = new String("아이피");								// 단속장비(CCTV) IP

			tgtVO.setChkDtm(chkDtm);
			tgtVO.setVehiNo(vehiNo);
			tgtVO.setTrEqup(trEqup);
			tgtVO.setEqupIp(equpIp);
			tgtVO.setNotiType("1");
			tgtVO.setCreateDtm(chkDtm);

			System.out.println("[" + tgtVO.getChkDtm() + "]"
					+ "[" + tgtVO.getVehiNo() + "]"
					+ "[" + tgtVO.getTrEqup() + "]"
					+ "[" + tgtVO.getEqupIp()+ "]"
					+ "[" + tgtVO.getNotiType() + "]"
					+ "[" + tgtVO.getCreateDtm()+ "]");

			/////////////////////////////////////////////////////////////////////////
			// 
			/////////////////////////////////////////////////////////////////////////

		} catch (Exception e) {
			System.out.println("[" + e.toString() + "]");
			e.printStackTrace();	ret=false;	throw e;
		}

		return ret;
	}
	
}
