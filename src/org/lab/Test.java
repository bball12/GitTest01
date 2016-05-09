package org.lab;
import java.io.Serializable;

public class Test {
	public static void main(String[] args) {
		Lab001 lab = new Lab001();
		lab.run();
	}
}

abstract class Lab {
	public abstract void doTest();
	
	public void run() {
		doTest();
	}
}

class FixedCCTVSystemVO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7079980244922243271L;
	
	private String officeCd = ""; // 43111-상당구청 , 43112-서원구청 , 43113-흥덕구청 , 43710-청원군청
	private String officeNm = ""; 
	private String smsStorePath = ""; // SMSNotice 저장 위치
	private String regltPhotoStorePath = "";
	
	/**
	 * @return the officeCd
	 */
	public String getOfficeCd() {
		return officeCd;
	}
	/**
	 * @param officeCd the officeCd to set
	 */
	public void setOfficeCd(String officeCd) {
		this.officeCd = officeCd;
	}
	/**
	 * @return the officeNm
	 */
	public String getOfficeNm() {
		return officeNm;
	}
	/**
	 * @param officeNm the officeNm to set
	 */
	public void setOfficeNm(String officeNm) {
		this.officeNm = officeNm;
	}
	/**
	 * @return the smsStorePath
	 */
	public String getSmsStorePath() {
		return smsStorePath;
	}
	/**
	 * @param smsStorePath the smsStorePath to set
	 */
	public void setSmsStorePath(String smsStorePath) {
		this.smsStorePath = smsStorePath;
	}
	
	/**
	 * @return the regltPhotoStorePath
	 */
	public String getRegltPhotoStorePath() {
		return regltPhotoStorePath;
	}
	/**
	 * @param regltPhotoStorePath the regltPhotoStorePath to set
	 */
	public void setRegltPhotoStorePath(String regltPhotoStorePath) {
		this.regltPhotoStorePath = regltPhotoStorePath;
	}
	
}


