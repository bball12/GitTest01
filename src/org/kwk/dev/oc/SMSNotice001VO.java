package org.kwk.dev.oc;

import java.io.Serializable;

public class SMSNotice001VO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2912901442736210477L;
	
	int notiRecvSn       = 0;	// 각 구청 CCTV 시스템으로 부터 받은 txt 파일의 등록 순번 
    String admiCd        = null; //  행정코드 청주시
    String sectCd        = null; //  시군구코드 시의구청코드

    String fileNm         = null; //  단속 알림 수신 파일명   
	String fileSize      = null; //  단속 알림 수신 파일크기  
    
	// 파일명 정보
	String equpIp         = null; //  단속장비 IP   
	String createDtm      = null; //  파일 생성 일시  
	
	// 파일 내용 정보 
	String vehiNo        = null; //  차량번호        
	String trEqup 		 = null; //  단속장비    (Equp_type:=1)
	String chkDtm        = null; //  단속 일시
	String notiType      = null; //  단속유형 : 단속예정알리미 txt는 1, 단속확정알리미 txt는 2 ( 2014.03.21.by hshan 추가 )
	
	// 연계 정보 
	String drivNm        = null; //  운전자성명 
	String birtDt        = null; //  생년월일 
    int cctvSmsNotiSn    = 0; // SMS 알리미 설정 연계정보 key

    String regId	               = "";
    String regDt	               = "";
    String updId	               = "";
    String updDt	               = "";
    
	/**
	 * 각 구청 CCTV 시스템으로 부터 받은 txt 파일의 등록 순번 
	 * @return the notiRecvSn
	 */
	public int getNotiRecvSn() {
		return notiRecvSn;
	}
	/**
	 * 각 구청 CCTV 시스템으로 부터 받은 txt 파일의 등록 순번 
	 * @param notiRecvSn the notiRecvSn to set
	 */
	public void setNotiRecvSn(int notiRecvSn) {
		this.notiRecvSn = notiRecvSn;
	}
	
	
	/**
	 * 행정코드
	 * @return the admiCd
	 */
	public String getAdmiCd() {
		return admiCd;
	}
	/**
	 * 행정코드
	 * @param admiCd the admiCd to set
	 */
	public void setAdmiCd(String admiCd) {
		this.admiCd = admiCd;
	}
	/**
	 * 시군구코드
	 * @return the sectCd
	 */
	public String getSectCd() {
		return sectCd;
	}
	/**
	 * 시군구코드
	 * @param sectCd the sectCd to set
	 */
	public void setSectCd(String sectCd) {
		this.sectCd = sectCd;
	}
	/**
	 * 단속 알림 수신 파일명
	 * @return the fileNm
	 */
	public String getFileNm() {
		return fileNm;
	}
	/**
	 * 단속 알림 수신 파일명
	 * @param fileNm the fileNm to set
	 */
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	/**
	 * 단속 알림 수신 파일크기
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * 단속 알림 수신 파일크기
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	/**
	 * 단속장비 IP 
	 * @return the equpIp
	 */
	public String getEqupIp() {
		return equpIp;
	}
	/**
	 * 단속장비 IP 
	 * @param equpIp the equpIp to set
	 */
	public void setEqupIp(String equpIp) {
		this.equpIp = equpIp;
	}
	/**
	 * 파일 생성 일시
	 * @return the createDtm
	 */
	public String getCreateDtm() {
		return createDtm;
	}
	/**
	 * 파일 생성 일시
	 * @param createDtm the createDtm to set
	 */
	public void setCreateDtm(String createDtm) {
		this.createDtm = createDtm;
	}

	
	/**
	 * 차량번호
	 * @return the vehiNo
	 */
	public String getVehiNo() {
		return vehiNo;
	}
	/**
	 * 차량번호
	 * @param vehiNo the vehiNo to set
	 */
	public void setVehiNo(String vehiNo) {
		this.vehiNo = vehiNo;
	}
	/**
	 * 단속장비
	 * @return the trEqup
	 */
	public String getTrEqup() {
		return trEqup;
	}
	/**
	 * 단속장비
	 * @param trEqup the trEqup to set
	 */
	public void setTrEqup(String trEqup) {
		this.trEqup = trEqup;
	}
	
	/**
	 * 단속 일시
	 * @return the chkDtm
	 */
	public String getChkDtm() {
		return chkDtm;
	}
	/**
	 * 단속 일시
	 * @param chkDtm the chkDtm to set
	 */
	public void setChkDtm(String chkDtm) {
		this.chkDtm = chkDtm;
	}

	/**
	 * 단속유형 : 단속예정알리미 txt는 1, 단속확정알리미 txt는 2 ( 2014.03.21.by hshan 추가 )
	 * @return the notiType
	 */
	public String getNotiType() {
		return notiType;
	}
	/**
	 * 단속유형 : 단속예정알리미 txt는 1, 단속확정알리미 txt는 2 ( 2014.03.21.by hshan 추가 )
	 * @param notiType the notiType to set
	 */
	public void setNotiType(String notiType) {
		this.notiType = notiType;
	}
	
	/**
	 * 운전자성명
	 * @return the drivNm
	 */
	public String getDrivNm() {
		return drivNm;
	}
	/**
	 * 운전자성명
	 * @param drivNm the drivNm to set
	 */
	public void setDrivNm(String drivNm) {
		this.drivNm = drivNm;
	}
	/**
	 * 생년월일
	 * @return the birtDt
	 */
	public String getBirtDt() {
		return birtDt;
	}
	/**
	 * 생년월일
	 * @param birtDt the birtDt to set
	 */
	public void setBirtDt(String birtDt) {
		this.birtDt = birtDt;
	}
	/**
	 * 
	 * @return the cctvSmsNotiSn
	 */
	public int getCctvSmsNotiSn() {
		return cctvSmsNotiSn;
	}
	/**
	 * 
	 * @param cctvSmsNotiSn the cctvSmsNotiSn to set
	 */
	public void setCctvSmsNotiSn(int cctvSmsNotiSn) {
		this.cctvSmsNotiSn = cctvSmsNotiSn;
	}
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}
	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	/**
	 * @return the updId
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * @param updId the updId to set
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	/**
	 * @return the updDt
	 */
	public String getUpdDt() {
		return updDt;
	}
	/**
	 * @param updDt the updDt to set
	 */
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	
}
