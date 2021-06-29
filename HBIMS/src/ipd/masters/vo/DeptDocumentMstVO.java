package ipd.masters.vo;

import ipd.masters.controller.hlp.DeptDocumentMstHLP;
import ipd.masters.dao.DeptDocumentMstDAO;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

public class DeptDocumentMstVO extends GenericFormBean {
	
	private static final long serialVersionUID = 02L;
	
	private String strDeptName = "";
	private String strDeptCode = "";
	private String strDocumentName = "";
	private String strDocumentCode = "";
	private String strComponentName ="";
	private String[] strComponentCode = null;
	private String[] strNewComponent=null;
	private String[] strComponentRemark = null;
	private String[] strComponentFileName = null;
	private String strComponentFile = "";
	private String strEffectiveFrom  = null;
	private String strSeatId = "";
	private String strLastModifiedSeatId = "";
	private String strIsValid = "";
	private String strIsDefault = "0";
	private String strErrorMsg = "";
	
	private String strRemarks = "";
	private String strMsg = "";
	private String strCtDate = null;
	private String strWarning = "";
	private String strHospitalCode="";
	private String strCFSRView="";
	private String AddedComponent="";
	private WebRowSet wsComponent=null;
	private WebRowSet Component=null;
	private String hlpData="";
	@SuppressWarnings("unused")
	private String deptarmentAdd="";
	@SuppressWarnings("unused")
	private String documentAdd="";
	@SuppressWarnings("unused")
	private String componentAdd="";
	
	
	/**
	 * @param strCtDate the strCtDate to set
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	
	/**
	 * @param deptarmentAdd the deptarmentAdd to set
	 */
	public void setDeptarmentAdd(String deptarmentAdd) {
		this.deptarmentAdd = deptarmentAdd;
	}

	/**
	 * @param documentAdd the documentAdd to set
	 */
	public void setDocumentAdd(String documentAdd) {
		this.documentAdd = documentAdd;
	}

	/**
	 * @param componentAdd the componentAdd to set
	 */
	public void setComponentAdd(String componentAdd) {
		this.componentAdd = componentAdd;
	}

	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrDocumentName() {
		return strDocumentName;
	}
	public void setStrDocumentName(String strDocumentName) {
		this.strDocumentName = strDocumentName;
	}
	public String[] getStrComponentRemark() {
		return strComponentRemark;
	}
	public void setStrComponentRemark(String[] strComponentRemark) {
		this.strComponentRemark = strComponentRemark;
	}
	public String[] getStrComponentFileName() {
		return strComponentFileName;
	}
	public void setStrComponentFileName(String[] strComponentFileName) {
		this.strComponentFileName = strComponentFileName;
	}
	
	/**
	 * retrieves and returns the Application Server Current Date if the Existing
	 * Date is Null
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Department Document Master", "VODeptDocumentMst");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "Department Document Master",
						"VODeptDocumentMst.getStrEffectiveFrom()-->" + e.getMessage());
			}
		}
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}
	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	/*public String getStrChk1() {
		return strChk1;
	}
	public void setStrChk1(String strChk1) {
		this.strChk1 = strChk1;
	}*/
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	
	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() {
		HisUtil util = new HisUtil("Department Document Master", "VODeptDocumentMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Department Document Master",
					"VODeptDocumentMst.getStrCtDate()-->" + e.getMessage());
		}
		return strCtDate;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrDocumentCode() {
		return strDocumentCode;
	}
	public void setStrDocumentCode(String strDocumentCode) {
		this.strDocumentCode = strDocumentCode;
	}
	public String[] getStrComponentCode() {
		return strComponentCode;
	}
	public void setStrComponentCode(String[] strComponentCode) {
		this.strComponentCode = strComponentCode;
	}
	/**
	 * By this user select the Department name from Department Combo.
	 * 
	 * @return name in String
	 */
	public String getDeptarmentAdd() {
		String cmbstr = "";
		WebRowSet ws = null;
		try {
			ws = DeptDocumentMstDAO.getDeptartmentDtl(true,this.getStrHospitalCode());
			HisUtil hisutil = new HisUtil("ipd", "VODUWRBedMst");
			cmbstr = hisutil.getOptionValue(ws, this.getStrDeptName(),
				"0^Select Value", false);
		} catch (Exception e) {
				new HisException("IPD", "VODeptDocumentMst.getDeptarmentAdd", e.getMessage());
		}

	return cmbstr;
	}
	
	/**
	 * By this user select the Document name from Document Combo.
	 * 
	 * @return name in String
	 */
	public String getDocumentAdd() {
		String cmbstr = "";
		WebRowSet ws = null;
		try {
			ws = DeptDocumentMstDAO.getDocumentDtl(true,this.getStrHospitalCode());
			HisUtil hisutil = new HisUtil("ipd", "VODUWRBedMst");
			cmbstr = hisutil.getOptionValue(ws, this.getStrDocumentName(),
				"0^Select Value", false);
		} catch (Exception e) {
				new HisException("IPD", "VODeptDocumentMst.getDocumentAdd", e.getMessage());
		}

	return cmbstr;
	}
	public String[] getStrNewComponent() {
		return strNewComponent;
	}
	public void setStrNewComponent(String[] strNewComponent) {
		this.strNewComponent = strNewComponent;
	}
	
	/**
	 * By this user select the Component name from Component Combo.
	 * 
	 * @return name in String
	 */
	public String getComponentAdd() {
		String cmbstr = "";
		WebRowSet ws = null;
		try {
			ws = DeptDocumentMstDAO.getComponentDtl(true,this.getStrHospitalCode());
			HisUtil hisutil = new HisUtil("ipd", "VODUWRBedMst");
			cmbstr = hisutil.getOptionValue(ws, this.getStrComponentName(),
				"0^Select Value", false);
		} catch (Exception e) {
				new HisException("IPD", "VODeptDocumentMst.getDocumentAdd", e.getMessage());
		}

	return cmbstr;
	}
	public String getStrComponentName() {
		return strComponentName;
	}
	public void setStrComponentName(String strComponentName) {
		this.strComponentName = strComponentName;
	}
	public String getStrComponentFile() {
		return strComponentFile;
	}
	public void setStrComponentFile(String strComponentFile) {
		this.strComponentFile = strComponentFile;
	}
	public String getStrCFSRView() {
		return strCFSRView;
	}
	public void setStrCFSRView(String strCFSRView) {
		this.strCFSRView = strCFSRView;
	}
	public WebRowSet getWsComponent() {
		return wsComponent;
	}
	public void setWsComponent(WebRowSet wsComponent) {
		this.wsComponent = wsComponent;
	}
	public String getAddedComponent() {
		return AddedComponent;
	}
	public void setAddedComponent(String addedComponent) {
		AddedComponent = addedComponent;
	}
	public WebRowSet getComponent() {
		return Component;
	}
	public void setComponent(WebRowSet Component) {
		this.Component = Component;
	}
	
	/**
	 * Through this method we get selected data's.
	 * 
	 * @return get selected value through HLP class
	 */
	public String getHlpData() {
		try{			
			DeptDocumentMstDAO.getComponentName(this);
			DeptDocumentMstHLP strhlp = new DeptDocumentMstHLP();
			hlpData = strhlp.getAddedComponent(this);
							
		}catch(Exception e)
		{
			new HisException("IPD", "VODeptDocumentMst.getHlpData", e.getMessage());
			//e.printStackTrace();
		}
		return hlpData;
	}
	public void setHlpData(String hlpData) {
		this.hlpData = hlpData;
	}
	public String getStrIsDefault() {
		return strIsDefault;
	}
	public void setStrIsDefault(String strIsDefault) {
		this.strIsDefault = strIsDefault;
	}

}
