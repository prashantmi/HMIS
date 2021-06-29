/* Module Name: Common List Page
*  Name of Process: List Generation 
*  Name of Developer: Sh. Ashwini Mishra
*  Date of Creation: 26-Mar-2015
*/

package hr.pis.common.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class EmployeeInfoSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware {

	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	
	private String strEmpNo;
	private String strPageFlag;
	private String intListType;
	private String intRoleMgtTypeId;
	private String intCallingSeq;
	private String intCalMode;
	
	private String intSalaryTypeId;
	private String intNojId;
	private String intCadreId;
	private String intEmpOffId;
	private String intEstbId;
	private String intDeptCode;
	private String intSerGrpId;
	private String intDesigCode;
	private String intStatusId;
	private String intFinalStatusId;
	private String intPlOfPostingId;
	private String intHospId;
	
	
	private String strCondition;
	private String strAdditionalData;
	

	private String strErrMsg;
	
	
	
	
	public String getStrAdditionalData() {
		return strAdditionalData;
	}
	public void setStrAdditionalData(String strAdditionalData) {
		this.strAdditionalData = strAdditionalData;
	}
	public String getIntPlOfPostingId() {
		return intPlOfPostingId;
	}
	public void setIntPlOfPostingId(String intPlOfPostingId) {
		this.intPlOfPostingId = intPlOfPostingId;
	}
	public String getIntHospId() {
		return intHospId;
	}
	public void setIntHospId(String intHospId) {
		this.intHospId = intHospId;
	}
	public String getIntNojId() {
		return intNojId;
	}
	public void setIntNojId(String intNojId) {
		this.intNojId = intNojId;
	}
	public String getIntCadreId() {
		return intCadreId;
	}
	public void setIntCadreId(String intCadreId) {
		this.intCadreId = intCadreId;
	}	
	public String getIntSerGrpId() {
		return intSerGrpId;
	}
	public void setIntSerGrpId(String intSerGrpId) {
		this.intSerGrpId = intSerGrpId;
	}
	public String getIntDesigCode() {
		return intDesigCode;
	}
	public void setIntDesigCode(String intDesigCode) {
		this.intDesigCode = intDesigCode;
	}
	public String getIntStatusId() {
		return intStatusId;
	}
	public void setIntStatusId(String intStatusId) {
		this.intStatusId = intStatusId;
	}
	public String getIntFinalStatusId() {
		return intFinalStatusId;
	}
	public void setIntFinalStatusId(String intFinalStatusId) {
		this.intFinalStatusId = intFinalStatusId;
	}
	public String getIntEmpOffId() {
		return intEmpOffId;
	}
	public void setIntEmpOffId(String intEmpOffId) {
		this.intEmpOffId = intEmpOffId;
	}
	public String getIntEstbId() {
		return intEstbId;
	}
	public void setIntEstbId(String intEstbId) {
		this.intEstbId = intEstbId;
	}
	public String getIntDeptCode() {
		return intDeptCode;
	}
	public void setIntDeptCode(String intDeptCode) {
		this.intDeptCode = intDeptCode;
	}
	public String getStrCondition() {
		return strCondition;
	}
	public void setStrCondition(String strCondition) {
		this.strCondition = strCondition;
	}
	public String getIntSalaryTypeId() {
		return intSalaryTypeId;
	}
	public void setIntSalaryTypeId(String intSalaryTypeId) {
		this.intSalaryTypeId = intSalaryTypeId;
	}
	public String getIntCalMode() {
		return intCalMode;
	}
	public void setIntCalMode(String intCalMode) {
		this.intCalMode = intCalMode;
	}
	public String getIntCallingSeq() {
		return intCallingSeq;
	}
	public void setIntCallingSeq(String intCallingSeq) {
		this.intCallingSeq = intCallingSeq;
	}
	public String getIntRoleMgtTypeId() {
		return intRoleMgtTypeId;
	}
	public void setIntRoleMgtTypeId(String intRoleMgtTypeId) {
		this.intRoleMgtTypeId = intRoleMgtTypeId;
	}
	public String getIntListType() {
		return intListType;
	}
	public void setIntListType(String intListType) {
		this.intListType = intListType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStrPageFlag() {
		return strPageFlag;
	}
	public void setStrPageFlag(String strPageFlag) {
		this.strPageFlag = strPageFlag;
	}
	public String getStrEmpNo() {
		return strEmpNo;
	}
	public void setStrEmpNo(String strEmpNo) {
		this.strEmpNo = strEmpNo;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public HttpServletRequest getObjRequest() {
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest) {
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse() {
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse) {
		this.objResponse = objResponse;
	}

	public ServletContext getObjContext() {
		return objContext;
	}

	public void setObjContext(ServletContext objContext) {
		this.objContext = objContext;
	}

	
	public Map getMapSesion() {
		return mapSesion;
	}

	public void setMapSesion(Map mapSesion) {
		this.mapSesion = mapSesion;
	}

	@Override 
	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	@Override 
	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}

	@Override 
	public void setSession(Map sessionMap)
	{
		this.mapSesion = sessionMap;
	}
	
	public void prepare() throws Exception {
		
		
	}
}
