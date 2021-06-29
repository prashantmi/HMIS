package vo.registration;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public  class UnknownVerificationDtlVO
{
	protected static final long serialVersionUID = 1L;
	protected String strDocumentCode;
	protected String patCrNo;
	protected String strDocumentId;
	protected String fileType;
	
	
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getStrDocumentCode() {
		return strDocumentCode;
	}
	public void setStrDocumentCode(String strDocumentCode) {
		this.strDocumentCode = strDocumentCode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getStrDocumentId() {
		return strDocumentId;
	}
	public void setStrDocumentId(String strDocumentId) {
		this.strDocumentId = strDocumentId;
	}

	
	
}
