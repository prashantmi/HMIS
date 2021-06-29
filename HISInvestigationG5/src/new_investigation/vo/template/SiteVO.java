package new_investigation.vo.template;

import java.util.List;

import hisglobal.vo.ValueObject;

public class SiteVO extends ValueObject{
	private String siteName;
	private String siteCode;
	private String inSiteCode;
	private String trNo;
	private String sMode;
	private List<DiagnosisVO> diagnosisDtl;
	public String getTrNo() {
		return trNo;
	}
	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}
	
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getInSiteCode() {
		return inSiteCode;
	}
	public void setInSiteCode(String inSiteCode) {
		this.inSiteCode = inSiteCode;
	}
	public List<DiagnosisVO> getDiagnosisDtl() {
		return diagnosisDtl;
	}
	public void setDiagnosisDtl(List<DiagnosisVO> diagnosisDtl) {
		this.diagnosisDtl = diagnosisDtl;
	}
	public String getSMode() {
		return sMode;
	}
	public void setSMode(String mode) {
		sMode = mode;
	}

}
