/* VORoomConfigMst Use For Getter & Setter Method*/
package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;
import ipd.IpdConfigUtil;
import ipd.masters.dao.RoomTypeMstDAO;

import javax.sql.rowset.WebRowSet;

public class RoomConfigMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strRoomCode = "";
	private String strRoomNo = "";
	private String strRoomTypeCode = "";
	private String strSLNo = "";
	private String strRoomStatus = "";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;

	private String strLastModifSeatId = "";
	private String strRemarks = "";
	private String strEntryDate = null;
	private String strSeatId = "";
	private String strIsValid = "0";
	private String strerrorMsg = "";
	private String strChk1 = "";
    
	private String strAddOrModifyMode ="";
	private String cmbstr1 ="";
	@SuppressWarnings("unused")
	private String strBuildingDetailModi ="";
	@SuppressWarnings("unused")
	private String strBuildingDetailAdd ="";
	@SuppressWarnings("unused")
	private String strBlockDetailAdd ="";
	@SuppressWarnings("unused")
	private String strBlockDetailModi ="";
	@SuppressWarnings("unused")
	private String strFloorDetailAdd ="";
	@SuppressWarnings("unused")
	private String strFloorDetailModi ="";
	private String strBuildingId = "";
	@SuppressWarnings("unused")
	private String getBuildingIDCombo = "";
	@SuppressWarnings("unused")
	private String getRoomTypeCombo = "";
	private String strBlockId = "";
	private String strFloorId = "";
	private String strRoomId = "";
	private String strNoOfBeds="";
	
	private String strHidRoomNo = "";

	private String strBlockName = "";
	private String strFloorName = "";
	private String strBuildingName = "";
	private String strRoomTypeId = "";
	private String strRoomTypeId1 = "";
//	private String getBuildingDtl = "";
	//private String getBlockDtl1 = "";
	@SuppressWarnings("unused")
	private String roomTypeComboAdd ="";
	@SuppressWarnings("unused")
	private String roomTypeComboModi ="";
	
	private String strErr = "";
	private String strWarning = "";
	private String strMsg = "";
	private String strCtDate = null;
	private String strHospitalCode ="";
	private String strRoomDescription ="";
	@SuppressWarnings("unused")
    private String roomModi ="";
    
    private String strPropertyId[] = null;
    private String strPropertyComboValues ="";
    private WebRowSet PropertyComboWs = null;
    private String strPersistProperty ="";
	
    // Getter Method for Variables

	
	/**
	 * @return the strPropertyComboValues
	 */
	public String getStrPropertyComboValues() {
		return strPropertyComboValues;
	}

	/**
	 * @param strPropertyComboValues the strPropertyComboValues to set
	 */
	public void setStrPropertyComboValues(String strPropertyComboValues) {
		this.strPropertyComboValues = strPropertyComboValues;
	}

	/**
	 * @return the propertyComboWs
	 */
	public WebRowSet getPropertyComboWs() {
		return PropertyComboWs;
	}

	/**
	 * @param propertyComboWs the propertyComboWs to set
	 */
	public void setPropertyComboWs(WebRowSet propertyComboWs) {
		PropertyComboWs = propertyComboWs;
	}

	public String getStrRoomDescription() {
		return strRoomDescription;
	}

	public void setStrRoomDescription(String strRoomDescription) {
		this.strRoomDescription = strRoomDescription;
	}

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() { // function for gettin SYSDATE
		HisUtil util = new HisUtil("Room Config Master", "VORoomConfigMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "ComplientTypeMaster",
					"VORoomConfigMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

	
	public String getStrBlockName() {
		return strBlockName;
	}

	public String getStrFloorName() {
		return strFloorName;
	}

	public String getStrBuildingName() {
		return strBuildingName;
	}

	public String getStrRoomTypeId() {
		return strRoomTypeId;
	}

	public String getStrRoomTypeId1() {
		return strRoomTypeId1;
	}

	public String getStrRoomId() {
		return strRoomId;
	}

	public String getStrFloorId() {
		return strFloorId;
	}

	public String getStrBlockId() {
		return strBlockId;
	}

	public String getStrBuildingId() {
		return strBuildingId;
	}

	public String getChk1() {
		return strChk1;
	}
	

	public String getStrNoOfBeds() {
		return strNoOfBeds;
	}

	public void setStrNoOfBeds(String strNoOfBeds) {
		this.strNoOfBeds = strNoOfBeds;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	

	public String getStrRoomNo() {
		return strRoomNo;
	}

	public String getStrLastModifSeatId() {
		return strLastModifSeatId;
	}

	public void setStrLastModifSeatId(String strLastModifSeatId) {
		this.strLastModifSeatId = strLastModifSeatId;
	}

	public String getStrRoomCode() {
		return strRoomCode;
	}

	public String getStrRoomTypeCode() {
		return strRoomTypeCode;
	}

	public String getStrerrorMsg() {
		return strerrorMsg;
	}

	public String getStrSLNo() {
		return strSLNo;
	}

	public String getStrRoomStatus() {
		return strRoomStatus;
	}

	

	public String getStrSeatId() {
		return strSeatId;
	}

	// Setter Method for Variables

	public void setStrBuildingName(String strBuildingName) {
		this.strBuildingName = strBuildingName;
	}

	public void setStrFloorName(String strFloorName) {
		this.strFloorName = strFloorName;
	}

	public void setStrBlockName(String strBlockName) {
		this.strBlockName = strBlockName;
	}

	public void setStrRoomTypeId(String strRoomTypeId) {
		this.strRoomTypeId = strRoomTypeId;
	}

	public void setStrRoomTypeId1(String strRoomTypeId1) {
		this.strRoomTypeId1 = strRoomTypeId1;
	}

	public void setStrRoomId(String strRoomId) {
		this.strRoomId = strRoomId;
	}

	public void setStrFloorIdCombo(String strFloorId) {
		this.strFloorId = strFloorId;
	}

	public void setStrBlockIdCombo(String strBlockId) {
		this.strBlockId = strBlockId;
	}

	public void setStrFloorId(String strFloorId) {
		this.strFloorId = strFloorId;
	}

	public void setStrBlockId(String strBlockId) {
		this.strBlockId = strBlockId;
	}

	public void setStrBuildingId(String strBuildingId) {
		this.strBuildingId = strBuildingId;
	}

	public void setChk1(String strChk1) {
		this.strChk1 = strChk1;
	}

	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * retrieves and returns the Application Server Current Date if the Existing
	 * Date is Null
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEntryDate() {
		if (this.strEntryDate == null) {
			HisUtil util = new HisUtil("Room Config Type Master",
					"VORoomConfigMst");
			try {
				strEntryDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strEntryDate;
	}

	/**
	 * retrieves and returns the Application Server Current Date 
	 * in a Format of Effective to
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffectiveTo() {
		if (this.strEffectiveTo == null) {
			HisUtil util = new HisUtil("Room Config Type Master",
					"VORoomConfigMst");
			try {
				strEffectiveTo = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strEffectiveTo;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * retrieves and returns the Application Server Current Date 
	 * in a Format of Effective From
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Room Config Type Master",
					"VORoomConfigMst");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strEffectiveFrom;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}

	public void setStrRoomTypeCode(String strRoomTypeCode) {
		this.strRoomTypeCode = strRoomTypeCode;
	}

	public void setStrerrorMsg(String strerrorMsg) {
		this.strerrorMsg = strerrorMsg;
	}

	public void setStrSLNo(String strSLNo) {
		this.strSLNo = strSLNo;
	}

	public void setStrRoomStatus(String strRoomStatus) {
		this.strRoomStatus = strRoomStatus;
	}

	
	//Get-Set Method of Building Combo//
	
	public void setBuildingIDCombo(String getBuildingIDCombo) {
		this.getBuildingIDCombo = getBuildingIDCombo;
	}

	public String getBuildingIDCombo() throws Exception {
		String cmbstr = "";
		String errMsg = "";
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.1");
		try {
			cmbstr = hisutil.getOptionValue(qry, "0", "0^Select Value");
		} catch (Exception e) {
			errMsg = "VORoomConfigMst.getBuildingIDCombo() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
		}
		return cmbstr;
	}

	//Get-Set Method of RoomType Combo//
	public void setRoomTypeCombo(String getRoomTypeCombo) {
		this.getRoomTypeCombo = getRoomTypeCombo;
	}

	public String getRoomTypeComboModi() {
		
		
		String cmbstr1 = "";
		String errMsg = "";
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		RoomTypeMstDAO rtmst = new RoomTypeMstDAO(); // RoomType Combo Write
		// in DAORoomTypeMst
		//VORoomConfigMst vo= new VORoomConfigMst();

		try {
				//int i = Integer.parseInt(getStrRoomTypeId());
				cmbstr1 = hisutil.getOptionValue(rtmst.getRoomTypeDtl(-1, true,IpdConfigUtil.SUPER_HOSPITAL_CODE),
					this.getStrRoomTypeId(), "0^Select Value", true);
		} catch (Exception e) {
			errMsg = "VORoomConfigMst.getBuildingIDCombo() -->"
					+ e.getMessage();
			new Exception(errMsg);
		} finally {
			hisutil = null;
			rtmst = null;
		}
		return cmbstr1;
	}

	
	/**
	 * Testing for DAORoomType Combo
	 */
	public String getRoomTypeComboAdd() throws Exception {
		String cmbstr2 = "";

	
		
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		RoomTypeMstDAO rtmst = new RoomTypeMstDAO(); // RoomType Combo Write
		// in DAORoomTypeMst
		//VORoomConfigMst vo = new VORoomConfigMst();
		try {
			cmbstr2 = hisutil.getOptionValue(rtmst.getRoomTypeDtl(-1, true,IpdConfigUtil.SUPER_HOSPITAL_CODE),
					"0", "0^Select Value", true);

		} catch (Exception e) {

		} finally {
			hisutil = null;
			rtmst = null;
		}
		return cmbstr2;
	}

	/*------------------------------------------------------------------------------------*/
	/*------------------------Test IPDDAO(getBuildingDtl()Combo)---------------------------------*/
	
		
	
	/*------------------------------------------------------------------------------------*/
	/*------------------------Test IPDDAO(getBlockDtl()Combo)---------------------------------*/


	/**
	 * Testing for DAORoom Combo
	 */
	public String getRoomAdd(String val,String strBuild, String strBlock) {

		String cmbstr = "";
		RoomConfigMstVO vo = new RoomConfigMstVO();
		WebRowSet ws = null;
		DAOIpd daoipd = new DAOIpd("ipd", "VOWardMst");
		int nval = Integer.parseInt(val);
		
		//int buildingid = Integer.parseInt(this.getStrBuilding());
		//int blockid = Integer.parseInt(this.getStrBlock());
		try {
			if(strBuild.equals("0") || strBlock.equals("0") || val.equals("0"))
			{
				cmbstr = "<option value='0'>Select Value</option>";
			}else{
				if(this.strAddOrModifyMode.trim().equals("MODIFY"))
				{
					ws = daoipd.getRoomDtl(this.strRoomNo,Integer.parseInt(strBuild),Integer.parseInt(strBlock),nval, true,false,this.getStrHospitalCode());
				}
				else{
					ws = daoipd.getRoomDtl("ADD",Integer.parseInt(strBuild),Integer.parseInt(strBlock),nval, true,false,this.getStrHospitalCode());
				}
				
				 
				
				HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
				cmbstr = hisutil.getOptionValue(ws, "0", "0^Select Value", false);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			new HisException("IPD", "VORoomConfigMst.getRoomAdd", e.getMessage());
		}
		return cmbstr;

	}
	
	public String getRoomModi() {

		String cmbstr = "";
		//VORoomConfigMst vo = new VORoomConfigMst();
		WebRowSet ws = null;
		DAOIpd daoipd = new DAOIpd("ipd", "VORoomConfigMst");
			   
		String strFloor =this.strFloorId;
		//String strroom = this.strRoomId;
		try {
			
			if(this.strFloorId.equals("0")|| this.strFloorId.equals(""))
			{
				cmbstr = "<option value='0'>Select Value</option>";
			}
			else{
				if(this.strRoomId==null)
				{
					this.strRoomId="0";
				}
				int nfloor = Integer.parseInt(strFloor);
				//int nroom = Integer.parseInt(strroom);
				 
				
		//COMMENTED BY ANSHUL ON 26-08-09
				//	ws = daoipd.getRoomDtl(-1,-1,nfloor, true,false,this.getStrHospitalCode());
				if(this.strRoomNo.equals(""))
				{
					ws = daoipd.getRoomDtl(this.strHidRoomNo,Integer.parseInt(this.strBuildingId),Integer.parseInt(this.strBlockId),nfloor, true,false,this.getStrHospitalCode());
				}else{
					ws = daoipd.getRoomDtl(this.strRoomNo,Integer.parseInt(this.strBuildingId),Integer.parseInt(this.strBlockId),nfloor, true,false,this.getStrHospitalCode());
				}
				
				HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
				cmbstr = hisutil.getOptionValue(ws, this.strRoomId, "0^Select Value",false);//to be
			}
		
		}
			  catch (Exception e) {
			new HisException("IPD", "VORoomConfigMst.getRoomModi", e.getMessage());
		}
		return cmbstr;

	}
	/*------------------------------------------------------------------------------------*/

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

	public String getStrErr() {
		return strErr;
	}

	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getCmbstr1() {
		return cmbstr1;
	}

	public void setCmbstr1(String cmbstr1) {
		this.cmbstr1 = cmbstr1;
	}

	public void setRoomModi(String roomModi) {
		this.roomModi = roomModi;
	}

	public void setRoomTypeComboAdd(String roomTypeComboAdd) {
		this.roomTypeComboAdd = roomTypeComboAdd;
	}

	/**
	 * Test IPDDAO(getBuildingDtl()Combo)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getStrBuildingDetailModi() throws Exception {
		String cmbstr2 = "";

		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		DAOIpd dipd = new DAOIpd("IPD", "VORoomConfigMst"); //building
		WebRowSet web = null;
		web = dipd.getBuildingDtl(false,this.getStrHospitalCode()); // O.V true
		try {
			cmbstr2 = hisutil.getOptionValue(web,this.strBuildingId,
					"0^Select Value", false);
		} catch (Exception e) {

		} finally {
			if (web != null) {
				web.close();
				web = null;
			}
			hisutil = null;
			dipd = null;
		}
		return cmbstr2;
	}


	
	
	public void setStrBuildingDetailModi(String strBuildingDetailModi) {
		this.strBuildingDetailModi = strBuildingDetailModi;
	}

	

	/**
	 * Execute Combo Query for Building Detail
	 * @return
	 * @throws Exception
	 */
	public String getStrBuildingDetailAdd() throws Exception {
		String cmbstr2 = "";

		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		DAOIpd dipd = new DAOIpd("IPD", "VORoomConfigMst"); // RoomType Combo
		WebRowSet web = null;
		web = dipd.getBuildingDtl(false,this.getStrHospitalCode()); // O.V true

		try {
			cmbstr2 = hisutil.getOptionValue(dipd.getBuildingDtl(false,this.getStrHospitalCode()), "0",
					"0^Select Value", true);
		} catch (Exception e) {

		} finally {
			if (web != null) {
				web.close();
				web = null;
			}
			hisutil = null;
			dipd = null;
		}
		return cmbstr2;
	}
	

	public void setStrBuildingDetailAdd(String strBuildingDetailAdd) {
		this.strBuildingDetailAdd = strBuildingDetailAdd;
	}

	
/**
 * Combo Query for Block Detail
 * 
 * @param buildingCode return integer value
 * @return
 * @throws Exception
 */
	public String getStrBlockDetailAdd(String buildingCode) throws Exception {
		String cmbstr2 = "";
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		DAOIpd dipd = new DAOIpd("IPD", "VORoomConfigMst"); // RoomType Combo
		try {
			int i = Integer.parseInt(buildingCode);

			cmbstr2 = hisutil.getOptionValue(dipd.getBlockDtl(i, true,this.getStrHospitalCode()), "0",
					"0^Select Value", false);
		} catch (Exception e) {

		} finally {
			hisutil = null;
			dipd = null;
		}
		return cmbstr2;
	}

	
	
	
	
	public void setStrBlockDetailAdd(String strBlockDetailAdd) {
		this.strBlockDetailAdd = strBlockDetailAdd;
	}

	/**
	 * get Data from database of Block Detail
	 * @return
	 * @throws Exception
	 */
	public String getStrBlockDetailModi() throws Exception {
		String cmbstr2 = "";

		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		DAOIpd dipd = new DAOIpd("IPD", "VORoomConfigMst"); // RoomType Combo
		try {
			if(this.strBuildingId.equals("0")||this.strBuildingId.equals(""))
			{
				cmbstr2 = "<option value='0'>Select Value</option>";
			}
			else{
				int i = Integer.parseInt(this.strBuildingId);
				cmbstr2 = hisutil.getOptionValue(dipd.getBlockDtl(i, true,this.getStrHospitalCode()),this.strBlockId,
						"0^Select Value", false);
			}
			
			
		} catch (Exception e) {

		} finally {
			hisutil = null;
			dipd = null;
		}
		return cmbstr2;
	}
	
	
	
	
	public void setStrBlockDetailModi(String strBlockDetailModi) {
		this.strBlockDetailModi = strBlockDetailModi;
	}

	
/**
 * 
 * @param building - integer value
 * @param block - integer value
 * @return String in combo with the value of Building n Block 
 * @throws Exception
 */
	public String getStrFloorDetailAdd(String building,String block) throws Exception {
		String cmbstr2 = "";

		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		DAOIpd dipd = new DAOIpd("IPD", "VORoomConfigMst"); // RoomType Combo
		// Write in
		// DAORoomTypeMst
		try {
			
            if(building.equals("0")|| block.equals("0")){
            	cmbstr2 = "<option value='0'>Select Value</option>";
            }else{
            	int nbuilding = Integer.parseInt(building);
                int nblock = Integer.parseInt(block); 
            	cmbstr2 = hisutil.getOptionValue(dipd.getFloorDtl2(nbuilding,nblock, true,this.getStrHospitalCode()), "0",
						"0^Select Value", false);
            }
				
			
			
		} catch (Exception e) {

		} finally {
			hisutil = null;
			dipd = null;
		}
		return cmbstr2;
	}
	
	public void setStrFloorDetailAdd(String strFloorDetailAdd) {
		this.strFloorDetailAdd = strFloorDetailAdd;
	}

	
/**
 * Get Floor Details from database.
 * 
 * @return String in Combo
 * @throws Exception
 */
	public String getStrFloorDetailModi() throws Exception {
		String cmbstr2 = "";	
		HisUtil hisutil = new HisUtil("ipd", "VORoomConfigMst");
		DAOIpd dipd = new DAOIpd("IPD", "VORoomConfigMst"); // RoomType Combo
		try {
			
            if(this.getStrBlockId().equals("0")||this.getStrBlockId().equals(""))
			{
				cmbstr2 = "<option value='0'>Select Value</option>";
			}else{
				int i = Integer.parseInt(this.getStrBuildingId());
	            int j = Integer.parseInt(this.getStrBlockId()); 
			cmbstr2 = hisutil.getOptionValue(dipd.getFloorDtl2(i,j, true,this.getStrHospitalCode()),this.strFloorId,
					"0^Select Value", false);
			}
		} catch (Exception e) {

		} finally {
			hisutil = null;
			dipd = null;
		}
		return cmbstr2;
	}
	

	
	
	public void setStrFloorDetailModi(String strFloorDetailModi) {
		this.strFloorDetailModi = strFloorDetailModi;
	}

	public void setRoomTypeComboModi(String roomTypeComboModi) {
		this.roomTypeComboModi = roomTypeComboModi;
	}

	/**
	 * @return the strPropertyId
	 */
	public String[] getStrPropertyId() {
		return strPropertyId;
	}

	/**
	 * @param strPropertyId the strPropertyId to set
	 */
	public void setStrPropertyId(String[] strPropertyId) {
		this.strPropertyId = strPropertyId;
	}

	/**
	 * @return the strPersistProperty
	 */
	public String getStrPersistProperty() {
		return strPersistProperty;
	}

	/**
	 * @param strPersistProperty the strPersistProperty to set
	 */
	public void setStrPersistProperty(String strPersistProperty) {
		this.strPersistProperty = strPersistProperty;
	}

	public String getStrAddOrModifyMode() {
		return strAddOrModifyMode;
	}

	public void setStrAddOrModifyMode(String strAddOrModifyMode) {
		this.strAddOrModifyMode = strAddOrModifyMode;
	}

	public String getStrHidRoomNo() {
		return strHidRoomNo;
	}

	public void setStrHidRoomNo(String strHidRoomNo) {
		this.strHidRoomNo = strHidRoomNo;
	}

}
