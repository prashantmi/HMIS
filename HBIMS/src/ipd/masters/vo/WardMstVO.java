package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;
import ipd.masters.dao.WardMstDAO;
import ipd.IpdConfigUtil;
import javax.sql.rowset.WebRowSet;

public class WardMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strwardCode = "";
	private String strWardName = "";
	private String strWardType = "0";
	private String strNoOfBed = "0";
	private String strBuilding = "0";
	private String strBlock = "0";
	private String strBuildingName = "";
	private String strBlockName = "";
	private String strFloor = "";
	private String[] strFloors = null;
	private String[] strGender = null;
	private String[] strFromAge = null;
	private String[] strToAge = null;
	private String strLroom = "";
	private String[] strRroom = null;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strRemark = "";
	private String strSeatId = "";
	private String strLastModifySeatId = "";
	private String strErrorMsg = "";
	private String strbookingAllowed="0";
	private String  strmoreorless="0";
	private String strmsapproveRequired="0";
	private String strmaxNoWaitingList="0";
	private String strIsValid = "";
	private String updateMode = "0";
	private int ntotalRows;
	private String strtempGender = "0";
	private String strCtDate = null;
	private String[] strFunit = null;
	private String msg = "";
	private String warnings = "";
	@SuppressWarnings("unused")
	private String strFloorRoomView = "";
	@SuppressWarnings("unused")
	private String View = "";
	private String strEffectiveFromModi="";
	private String strHospitalCode="";
	@SuppressWarnings("unused")
	private String strRoom=""; 	                         // it will fetch the room in left room combo value during add page
	private String strLRoomModi="";                      // it wiil fetch the room in left combo value during modification
	private String strRRoomModi=""; 
	@SuppressWarnings("unused")
	private String strBedAdd="";
	@SuppressWarnings("unused")
	private String blockAdd="";
	@SuppressWarnings("unused")
	private String buildingAdd="";
	private String strChk="";
	private String strMaxPatPerBed="1";
	
	private String strPrivateWardType="";
	
	
	public String getStrPrivateWardType() {
		return strPrivateWardType;
	}

	public void setStrPrivateWardType(String strPrivateWardType) {
		this.strPrivateWardType = strPrivateWardType;
	}

	public String getStrMaxPatPerBed() {
		return strMaxPatPerBed;
	}

	public void setStrMaxPatPerBed(String strMaxPatPerBed) {
		this.strMaxPatPerBed = strMaxPatPerBed;
	}

	/**
	 * This function return current date
	 * @return
	 */
	public String getStrCtDate() { // function for gettin sysdate in date
		// picker
		HisUtil util = new HisUtil("Ward Master", "WardMstVO");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Ward Master",
					"WardMstVO.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

	public String getStrwardCode() {
		return strwardCode;
	}

	public void setStrwardCode(String strwardCode) {
		this.strwardCode = strwardCode;
	}

	public int getNtotalRows() {
		return ntotalRows;
	}

	public void setNtotalRows(int ntotalRows) {
		this.ntotalRows = ntotalRows;
	}

	public String getStrtempGender() {
		return strtempGender;
	}

	public void setStrtempGender(String strtempGender) {
		this.strtempGender = strtempGender;
	}

	public String getUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(String updateMode) {
		this.updateMode = updateMode;
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

	public String getStrRemark() {
		return strRemark;
	}

	public void setStrRemark(String strRemark) {
		this.strRemark = strRemark;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrBlock() {
		return strBlock;
	}

	public void setStrBlock(String strBlock) {
		this.strBlock = strBlock;
	}

	public void setStrNoOfBed(String strNoOfBed) {
		this.strNoOfBed = strNoOfBed;
	}

	public String getStrBuilding() {
		return strBuilding;
	}

	public void setStrBuilding(String strBuilding) {
		this.strBuilding = strBuilding;
	}

	public String[] getStrFromAge() {
		return strFromAge;
	}

	public void setStrFromAge(String[] strFromAge) {
		this.strFromAge = strFromAge;
	}

	public String[] getStrGender() {
		return strGender;
	}

	public void setStrGender(String[] strGender) {
		this.strGender = strGender;
	}

	public String[] getStrToAge() {
		return strToAge;
	}

	public void setStrToAge(String[] strToAge) {
		this.strToAge = strToAge;
	}

	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}

	public String getStrWardName() {
		return strWardName;
	}

	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}

	public String getStrLroom() {
		return strLroom;
	}

	public void setStrLroom(String strLroom) {
		this.strLroom = strLroom;
	}

	public String[] getStrRroom() {
		return strRroom;
	}

	public void setStrRroom(String[] strRroom) {
		this.strRroom = strRroom;
	}
/**
 * This function return current date in effective from date
 * @return
 */
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Ward Master", "WardMstVO");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("IPD", "WardMstVO.getStrEffectiveFrom", e
						.getMessage());
			}
		}
		return strEffectiveFrom;
	}

	
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public String getStrEffectiveTo() throws Exception {
		if (this.strEffectiveTo == null) {
			HisUtil util = new HisUtil("Ward Master", "WardMstVO");
			try {
				strEffectiveTo = util.getASDate("dd-MMM-yyyy");

				util = null;
			} catch (HisException e) {

				//e.printStackTrace();
			}
		}
		return strEffectiveTo;

	}

	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	public String getStrWardType() {
		return strWardType;
	}

	/**
	 * This function is used to bring data in ward type combo 
	 * @return
	 */
	public String getWardTypeAdd() {
		//
		String cmbstr = "";
		HisUtil hisutil = new HisUtil("ipd", "WardMstVO");
		String qry = ipd.qryHandler_ipd.getQuery(1, "gbl.wardtype.0");
		qry=qry+this.getStrHospitalCode();
		//System.out.println("qry1----->"+qry);
		

		try {
			cmbstr = hisutil.getOptionValue(qry, this.getStrWardType(),
					"0^Select Value");

		} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getWardTypeAdd", e.getMessage());
		}
		
		return cmbstr;
	}

	public String getStrNoOfBed() {
		return strNoOfBed;
	}

	/**
	 * 
	 * @return
	 */
	public String getStrBedAdd() {
		String cmbstr = this.getStrHospitalCode();
		IpdConfigUtil ipdUtil =new IpdConfigUtil(cmbstr);
		String strBedList=ipdUtil.getBedLimit();
		HisUtil hisutil = new HisUtil("ipd", "VOWardMst");
		String qry = ipd.qryHandler_ipd.getQuery(1, "gbl.bedno.0").replace("?", ""+strBedList+"");

		try {

			cmbstr = hisutil.getOptionValue(qry, this.getStrNoOfBed(),
					"0^Select Value");

		} catch (Exception e) {
			new HisException("IPD", "VOWardMst.getStrBedAdd", e.getMessage());
		}
		return cmbstr;
	}

	public String getStrNoOfBedModi() {
		return strNoOfBed;
	}

	/**
	 * This function invoke DAOIpd's getBuildingDtl to bring data in building combo on add page
	 * @return
	 */
	public String getBuildingAdd() {

		//System.out.print("monika building------------->" + getStrBuilding());
		String cmbstr = "";
		//System.out.println("inside VO BUILDING-->" + this.getStrBuilding());

		WebRowSet ws = null;
		DAOIpd daoipd = new DAOIpd("ipd", "WardMstVO");
		try {
				ws = daoipd.getBuildingDtl(true,this.getStrHospitalCode());
			HisUtil hisutil = new HisUtil("ipd", "WardMstVO");

		//	System.out.println("Building : " + this.getStrBuilding());

			cmbstr = hisutil.getOptionValue(ws, this.getStrBuilding(),
					"0^All", false);

		} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getBuildingAdd", e.getMessage());
		}
		return cmbstr;

	}

	/**
	 * This function is used to bring data in block combo
	 * @return
	 */
	public String getBlockAdd() {

		String cmbstr = "";
		
		WebRowSet ws = null;
		DAOIpd daoipd = new DAOIpd("ipd", "WardMstVO");
		int id = Integer.parseInt(this.getStrBuilding());
		//System.out.println("id---->"+id);
		try {
			ws = daoipd.getBlockDtl(id, true,this.getStrHospitalCode());
			HisUtil hisutil = new HisUtil("ipd", "WardMstVO");

			//System.out.println("Block : " + this.getStrBlock());

			cmbstr = hisutil.getOptionValue(ws, this.getStrBlock(),
					"0^All", false);

		} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getBlockAdd", e.getMessage());
		}
		return cmbstr;
	}

	/**
	 * Get room on add page
	 * @return
	 */
	public String getRoom() {

		String cmbstr = "";

		WebRowSet ws = null;
		//DAOIpd daoipd = new DAOIpd("ipd", "WardMstVO");
		//String hcode=this.getStrHospitalCode();
	try {
			//DAOWardMst dao=new DAOWardMst();
			//System.out.println("Welcome")
			//ws=dao.getLRoom(hcode);
			HisUtil hisutil = new HisUtil("ipd", "WardMstVO");

			cmbstr = hisutil.getOptionValue(ws, "0", "", true);
			} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getRoom", e.getMessage());
		}
		return cmbstr;
		

	}

	public String getComboGender() {

		String cmbstr = "";

		WebRowSet ws = null;
		DAOIpd daoipd = new DAOIpd("ipd", "WardMstVO");
		try {
			ws = daoipd.getGenderDtl(true);
			HisUtil hisutil = new HisUtil("ipd", "WardMstVO");
			cmbstr = hisutil.getOptionValue(ws, this.getStrtempGender(),
				"0^Select Value", false);
		} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getComboGender", e.getMessage());
		}

		return cmbstr;

	}

	/**
	 * This function is used to bring left room combo data during modify
	 * @return
	 */
	public String getLeftRoomModi() {
		String cmbstr = "";

		HisUtil hisutil = new HisUtil("ipd", "WardMstVO");
		StringBuffer qry = new StringBuffer(ipd.qryHandler_ipd.getQuery(1,
				"select.ward.5"));
		// System.out.println("left list query"+ipd.qryHandler_ipd.getQuery(1,
		 //"select.ward.5"));
		qry.replace(qry.indexOf("p"), (qry.indexOf("p") + 1), this
				.getStrwardCode());
		qry.replace(qry.indexOf("q"), (qry.indexOf("q") + 1), this
				.getStrBuilding());
		qry.replace(qry.indexOf("r"), (qry.indexOf("r") + 1), this
				.getStrBlock());
		// qry.replace(qry.indexOf("?"),(qry.indexOf("?")+1),this.getStrwardCode());
		try {

			cmbstr = hisutil.getOptionValue(qry.toString(), "", "");
		} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getLeftRoomModi", e.getMessage());
		}
		return cmbstr;
	}

	/**
	 * This function is used to bring right room combo during modify
	 * @return
	 */
	public String getRightRoomModi() {

		String cmbstr = "";

		HisUtil hisutil = new HisUtil("ipd", "WardMstVO");

		StringBuffer qry = new StringBuffer(ipd.qryHandler_ipd.getQuery(1,
				"select.ward.6"));
		// System.out.println("right list query"+ipd.qryHandler_ipd.getQuery(1,
		//"select.ward.6"));
		qry.replace(qry.indexOf("?"), (qry.indexOf("?") + 1), this
				.getStrwardCode());
		
		try {

			cmbstr = hisutil.getOptionValue(qry.toString(), "", "");
		} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getRightRoomModi", e
					.getMessage());
		}
		return cmbstr;
	}

	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getStrFloor() {
		return strFloor;
	}

	public void setStrFloor(String strFloor) {
		this.strFloor = strFloor;
	}

	public String[] getStrFloors() {
		return strFloors;
	}

	public void setStrFloors(String[] strFloors) {
		this.strFloors = strFloors;
	}

	public void setStrFloorRoomView(String strFloorRoomView) {
		this.strFloorRoomView = strFloorRoomView;
	}

	public void setView(String view) {
		View = view;
	}

	public String[] getStrFunit() {
		return strFunit;
	}

	public void setStrFunit(String[] strFunit) {
		this.strFunit = strFunit;
	}

	

	public String getStrLastModifySeatId() {
		return strLastModifySeatId;
	}

	public void setStrLastModifySeatId(String strLastModifySeatId) {
		this.strLastModifySeatId = strLastModifySeatId;
	}

	public String getStrBlockName() {
		//System.out.println("BLOCKNAMEVO-->" + strBlockName);
		return strBlockName;
	}

	public void setStrBlockName(String strBlockName) {
		this.strBlockName = strBlockName;
	}

	public String getStrBuildingName() {
		//System.out.println("BUILDINGNAMEVO-->" + strBuildingName);
		return strBuildingName;
	}

	public void setStrBuildingName(String strBuildingName) {
		this.strBuildingName = strBuildingName;
	}
	public String getStrbookingAllowed() {
		return strbookingAllowed;
	}

	public void setStrbookingAllowed(String strbookingAllowed) {
		this.strbookingAllowed = strbookingAllowed;
	}

	public String getStrmoreorless() {
		return strmoreorless;
	}

	public void setStrmoreorless(String strmoreorless) {
		this.strmoreorless = strmoreorless;
	}

	public String getStrmsapproveRequired() {
		return strmsapproveRequired;
	}

	public void setStrmsapproveRequired(String strmsapproveRequired) {
		this.strmsapproveRequired = strmsapproveRequired;
	}

	
	public String getStrmaxNoWaitingList() {
		return strmaxNoWaitingList;
	}

	public void setStrmaxNoWaitingList(String strmaxNoWaitingList) {
		this.strmaxNoWaitingList = strmaxNoWaitingList;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrRoom() {
		String cmbstr = "";

		WebRowSet ws = null;
		String hcode=this.getStrHospitalCode();
		
		try {
			WardMstDAO dao=new WardMstDAO();
			
			ws=dao.getLRoom(hcode,this.getStrBlock(),this.getStrBuilding());
			HisUtil hisutil = new HisUtil("ipd", "WardMstVO");
			if(ws!=null && ws.size()>0){
				cmbstr = hisutil.getOptionValue(ws, "0", "", true);
			}
		} catch (Exception e) {
			new HisException("IPD", "WardMstVO.getRoom", e.getMessage());
		}
		return cmbstr;
		

	}

	public void setStrRoom(String strRoom) {
		this.strRoom = strRoom;
	}

	public String getStrLRoomModi() {
		return strLRoomModi;
	}

	public void setStrLRoomModi(String strLRoomModi) {
		this.strLRoomModi = strLRoomModi;
	}

	public String getStrRRoomModi() {
		return strRRoomModi;
	}

	public void setStrRRoomModi(String strRRoomModi) {
		this.strRRoomModi = strRRoomModi;
	}

	public void setStrBedAdd(String strBedAdd) {
		this.strBedAdd = strBedAdd;
	}

	public void setBlockAdd(String blockAdd) {
		this.blockAdd = blockAdd;
	}

	public void setBuildingAdd(String buildingAdd) {
		this.buildingAdd = buildingAdd;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public String getStrEffectiveFromModi() {
		return strEffectiveFromModi;
	}

	public void setStrEffectiveFromModi(String strEffectiveFromModi) {
		this.strEffectiveFromModi = strEffectiveFromModi;
	}

}