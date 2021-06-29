package usermgmt.masters;

import org.apache.struts.action.*;
import org.apache.struts.actions.*;

public class umgmtSessionMgtConfigActionForm extends ActionForm{
	
	private String hmode = "";
    private String timeOutTime = "";
	private String loginCount = "";
    private String blockAfter="";
	private String auditLogPathWindow="";
    private String auditLogPathLinux="";
    private String modificationTime="";
    private String passwModificationTime="";
    private String passwStrength="";
    private String hosCode="";
    
    
    
	public String getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getTimeOutTime() {
		return timeOutTime;
	}
	public void setTimeOutTime(String timeOutTime) {
		this.timeOutTime = timeOutTime;
	}
	public String getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}
	public String getBlockAfter() {
		return blockAfter;
	}
	public void setBlockAfter(String blockAfter) {
		this.blockAfter = blockAfter;
	}
	public String getAuditLogPathWindow() {
		return auditLogPathWindow;
	}
	public void setAuditLogPathWindow(String auditLogPathWindow) {
		this.auditLogPathWindow = auditLogPathWindow;
	}
	public String getAuditLogPathLinux() {
		return auditLogPathLinux;
	}
	public void setAuditLogPathLinux(String auditLogPathLinux) {
		this.auditLogPathLinux = auditLogPathLinux;
	}
	public String getPasswModificationTime() {
		return passwModificationTime;
	}
	public void setPasswModificationTime(String passwModificationTime) {
		this.passwModificationTime = passwModificationTime;
	}
	public String getPasswStrength() {
		return passwStrength;
	}
	public void setPasswStrength(String passwStrength) {
		this.passwStrength = passwStrength;
	}
	public String getHosCode() {
		return hosCode;
	}
	public void setHosCode(String hosCode) {
		this.hosCode = hosCode;
	}
    
    
}