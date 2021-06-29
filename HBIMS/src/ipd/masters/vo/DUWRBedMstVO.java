package ipd.masters.vo;



import ipd.masters.dao.DUWRBedMstDAO;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class DUWRBedMstVO extends GenericFormBean {
	
	private static final long serialVersionUID = 02L;
	
	private String strDepartmentCode="";
	private String strDeptUnitCode="";
	private String strDeptUnitName="";
	private String strDeptUnitType="";
	private String strWardName="";
	private String strWardCode="";
	private String strWardType="";
	private String strRoomName="";
	private String strRoomType="";
	private String strRoomNo="";
	private String strLBedName="";
	private String strRBedName="";
	private String strBedCode="";
	private String strBedName="";
	
	private String[]strLRBed = null;
	private String[]strLBed = null;
	private String[]strRBed = null;
	
	private String strEffectiveTo="";
	private String strEffectiveFrom = null;
	private String strRemarks = null;
	private String strIsValid = "";
	
	private String strActive = "0";
	private String strSeatId = "";
	private String strLastModSeatId = "";
	
	private String strDeptUnitNameValues="0";
	private String strWardNameValues="0";
	private String strRoomNameValues="0";
	private String strLBedValues = "0";
	private String strRBedValues = "0";
	private String strWardTypeValues="0";
	private String strRoomTypeValues="0";
	
	
	private String strErr = "";
	private String strMsg = "";
	private String strWarning = "";
	private String strHospitalCode="";
	private String strCtDate = null;
	private String strBedView="";
	private WebRowSet wstemp=null;
	
	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() {
		HisUtil util = new HisUtil("DUWR Bed Master", "DUWRBedMstVO");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
		
		} catch (Exception e) {

			new HisException("DUWR Bed Master",
					"DUWRBedMstVO.getStrCtDate()", e.getMessage());
		}finally{
			util = null;
		}
		return strCtDate;
	}

	public String getStrDeptUnitName() {
		return strDeptUnitName;
	}
	public void setStrDeptUnitName(String strDeptUnitName) {
		this.strDeptUnitName = strDeptUnitName;
	}
	public String getStrWardName() {
		return strWardName;
	}
	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}
	public String getStrRoomName() {
		return strRoomName;
	}
	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrActive() {
		return strActive;
	}
	public void setStrActive(String strActive) {
		this.strActive = strActive;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}
	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}
	public String getStrErr() {
		return strErr;
	}
	public void setStrErr(String strErr) {
		this.strErr = strErr;
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
	public String[] getStrLBed() {
		return strLBed;
	}
	public void setStrLBed(String[] strLBed) {
		this.strLBed = strLBed;
	}
	public String[] getStrRBed() {
		return strRBed;
	}
	public void setStrRBed(String[] strRBed) {
		this.strRBed = strRBed;
	}
	public String getStrWardNameValues() {
		return strWardNameValues;
	}
	public void setStrWardNameValues(String strWardNameValues) {
		this.strWardNameValues = strWardNameValues;
	}
	public String getStrRoomNameValues() {
		return strRoomNameValues;
	}
	public void setStrRoomNameValues(String strRoomNameValues) {
		this.strRoomNameValues = strRoomNameValues;
	}
	
	
	public String getStrDeptUnitNameValues() {
		return strDeptUnitNameValues;
	}

	public void setStrDeptUnitNameValues(String strDeptUnitNameValues) {
		this.strDeptUnitNameValues = strDeptUnitNameValues;
	}

	public String getStrWardTypeValues() {
		return strWardTypeValues;
	}

	public void setStrWardTypeValues(String strWardTypeValues) {
		this.strWardTypeValues = strWardTypeValues;
	}

	public String getStrRoomTypeValues() {
		return strRoomTypeValues;
	}

	public void setStrRoomTypeValues(String strRoomTypeValues) {
		this.strRoomTypeValues = strRoomTypeValues;
	}
	
	/**
	 * By this user select the DeptUnit name from DeptUnit Combo.
	 * 
	 * @return name in String
	 */
	public String getDeptUnitAdd() {
			String cmbstr = "";
			WebRowSet ws = null;
			try {
				ws = DUWRBedMstDAO.getDeptUnitDtl(true,this.getStrHospitalCode());
				HisUtil hisutil = new HisUtil("ipd", "DUWRBedMstVO");
				cmbstr = hisutil.getOptionValue(ws, this.getStrDeptUnitName(),
					"0^Select Value", false);
			} catch (Exception e) {
					new HisException("IPD", "DUWRBedMstVO.getBuildingAdd", e.getMessage());
			}

		return cmbstr;

	}

	public String getStrDeptUnitType() {
		return strDeptUnitType;
	}

	public void setStrDeptUnitType(String strDeptUnitType) {
		this.strDeptUnitType = strDeptUnitType;
	}

	/**
	 * By this user select the Ward name from Ward Combo.
	 * 
	 * @return name in String
	 */
	public String getWardNameAdd(){
		
		String cmbstr = "";
		WebRowSet ws = null;
		try {
			ws = DUWRBedMstDAO.getWardNameDtl(true,this.getStrHospitalCode());
			HisUtil hisutil = new HisUtil("ipd", "DUWRBedMstVO");
			cmbstr = hisutil.getOptionValue(ws, this.getStrWardName(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("IPD", "DUWRBedMstVO.getWardNameAdd", e.getMessage());
		}

		return cmbstr;

	}


	public String getStrWardType() {
		return strWardType;
	}

	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}
	
	/**
	 * By this user get the Room name from selected Ward combo.<br>
	 * Through Ajax function onchange of ward combo user get rooms in room combo.
	 * 
	 * @return name in String
	 */
	public String getRoomNameAdd(){
		String cmbstr = "";
		WebRowSet ws = null;
		try {
			ws = DUWRBedMstDAO.getRoomNameDtl(true,this.getStrHospitalCode(),this.getStrWardName());
			HisUtil hisutil = new HisUtil("ipd", "DUWRBedMstVO");
			cmbstr = hisutil.getOptionValue(ws,this.getStrRoomName(),
					"Select Value", false);

		} catch (Exception e) {
			new HisException("IPD", "DUWRBedMstVO.getRoomNameAdd", e.getMessage());
		}
		return cmbstr;

	}

	public String getStrRoomType() {
		return strRoomType;
	}

	public void setStrRoomType(String strRoomType) {
		this.strRoomType = strRoomType;
	}

	/**
	 * By this user get the left bed from selected Ward combo and Room combo<br>
	 * Through Ajax function onchange of room combo user get left beds.<br>
	 * On the selection of ward and room combo user get left beds
	 * 
	 * @return name in String
	 */
	public String LBedAdd(){
		String cmbstr = "";
		WebRowSet ws = null;
		try {
			ws = DUWRBedMstDAO.getLBedDtl(true,this.getStrDeptUnitName(),this.getStrHospitalCode(),this.getStrWardName(),this.getStrRoomName());
			HisUtil hisutil = new HisUtil("ipd", "DUWRBedMstVO");
			cmbstr = hisutil.getOptionValue(ws, "", "", false);
		
		} catch (Exception e) {
			new HisException("IPD", "DUWRBedMstVO.LBedAdd", e.getMessage());
		}
		return cmbstr;
	}
	
	/**
	 * By this user get the right bed from selected Department combo,Ward combo and Room combo<br>
	 * Through Ajax function onchange of room combo user get selected right beds.<br>
	 * On the selection of department,ward and room combo user get selected right beds
	 * 
	 * @return name in String
	 */
	public String RBedAdd(){
		String cmbstr = "";
		WebRowSet ws = null;
		try {
			ws = DUWRBedMstDAO.getRBedDtl(true,this.getStrHospitalCode(),this.getStrDeptUnitName(),this.getStrWardName(),this.getStrRoomName());
			HisUtil hisutil = new HisUtil("ipd", "DUWRBedMstVO");
			cmbstr = hisutil.getOptionValue(ws, "","", true);
		} catch (Exception e) {
			new HisException("IPD", "DUWRBedMstVO.RBedAdd", e.getMessage());
		}
		return cmbstr;
	
		
	}

	public String getStrLBedValues() {
		return strLBedValues;
	}

	public void setStrLBedValues(String strLBedValues) {
		this.strLBedValues = strLBedValues;
	}

	public String getStrRBedValues() {
		return strRBedValues;
	}

	public void setStrRBedValues(String strRBedValues) {
		this.strRBedValues = strRBedValues;
	}

	public String getStrLBedName() {
		return strLBedName;
	}

	public void setStrLBedName(String strLBedName) {
		this.strLBedName = strLBedName;
	}

	public String getStrDepartmentCode() {
		return strDepartmentCode;
	}

	public void setStrDepartmentCode(String strDepartmentCode) {
		this.strDepartmentCode = strDepartmentCode;
	}

	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}

	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}

	public String getStrRoomNo() {
		return strRoomNo;
	}

	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}

	public String getStrBedCode() {
		return strBedCode;
	}

	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public String getStrRBedName() {
		return strRBedName;
	}

	public void setStrRBedName(String strRBedName) {
		this.strRBedName = strRBedName;
	}

	public String getStrBedName() {
		return strBedName;
	}

	public void setStrBedName(String strBedName) {
		this.strBedName = strBedName;
	}

	public String getStrBedView() {
				
		return strBedView;
	}

	public void setStrBedView(String strBedView) {
		this.strBedView = strBedView;
	}

	public String[] getStrLRBed() {
		return strLRBed;
	}

	public void setStrLRBed(String[] strLRBed) {
		this.strLRBed = strLRBed;
	}

	public WebRowSet getWstemp() {
		return wstemp;
	}

	public void setWstemp(WebRowSet wstemp) {
		this.wstemp = wstemp;
	}

	
}
