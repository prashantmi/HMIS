/**
 * 
 */
package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import registration.config.RegistrationConfig;
import registration.masters.controller.data.ExtInstituteMstDATA;
import vo.registration.ExtInstituteVO;
import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

/**
 * @author s.singaravelan
 * Creation Date:- 22-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class ExtInstituteMstUTIL implements MasterInterface {
	
	
	
	HttpSession httpSession = null;
	public static HttpServletRequest request = null;

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() {
		
		StringBuilder strButtons = new StringBuilder();
		
		strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' edit(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='modify'>Modify</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='report'>Report</span></a>");
		strButtons.append("<a href='#' class='button' onclick='  cancelList(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='cancel'>Cancel</span></a>");
	
		return strButtons.toString();
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getColsWidth()
	 */
	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {
		String[] columnHdr = { "Institute Name","Institute Short Name","City"};
		return columnHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {		
		String[] strComboHdr = { "1", "Record Status"  };
		return strComboHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {
		String[] strComboQuery = new String[1];
		strComboQuery[0] = "1^Active";//#2^Inactive";
		return strComboQuery;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb) {
		
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		StringBuffer brMainQuery = new StringBuffer();
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);
		list.add(uservo.getHospitalCode());

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"extInstituteMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}
		
		return brMainQuery.toString();
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {		
		String strMasterName = "External Institute Master";
		return strMasterName;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {		
		String strOrderBy[] = { "1", "UPPER(GSTR_INSTITUTE_NAME)","2"," UPPER(GSTR_SHORT_NAME)","3","UPPER(GSTR_CITY)"};
		return strOrderBy;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getPage_per_block()
	 */
	public int getPage_per_block() {
		return 10;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getRecord_per_page()
	 */
	public int getRecord_per_page() {
		return 10;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */
	public String[] getSearchField() {		
		String strSearchField[] = { "1", " UPPER(GSTR_INSTITUTE_NAME)","2","UPPER(GSTR_SHORT_NAME)","3","UPPER(GSTR_CITY)"};
		return strSearchField;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Hospital Type :");
		viewHdr.add("D");
		viewHdr.add("Institute Name :");
		viewHdr.add("D");
		viewHdr.add("Institute Short Name:");
		viewHdr.add("D");
		viewHdr.add("Institute Type :");
		viewHdr.add("D");
		viewHdr.add("Address :");
		viewHdr.add("D");
		viewHdr.add("City :");
		viewHdr.add("D");
		viewHdr.add("State :");
		viewHdr.add("D");
		viewHdr.add("Contact Person :");
		viewHdr.add("D");
		viewHdr.add("Phone :");
		viewHdr.add("D");
		viewHdr.add("Fax :");
		viewHdr.add("D");
		viewHdr.add("Email :");
		viewHdr.add("D");
		return viewHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"extInstituteMst.view");
		return strViewQuery;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#isGlobal()
	 */
	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#reportInterFaceRequired()
	 */
	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http.HttpSession)
	 */
	public void setHttpSession(HttpSession session) {
		httpSession = session;

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#setHttpSessionMap(java.util.Map)
	 */
	public void setHttpSessionMap(Map session) {
		// TODO Auto-generated method stub

	}
	
	public void setHttpRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	//To populate the values in the ExtInstitute Master Screen Combos
	public static ExtInstituteVO getInstituteEssentials()
	{
		ExtInstituteMstDATA objDeptExt_p = null;
		ExtInstituteVO objExtInst_p=null;
		try{
			
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);
			objExtInst_p=new ExtInstituteVO();
			objExtInst_p.setStrIsAssociated("0");
			objDeptExt_p = new ExtInstituteMstDATA();				
			List stateList=objDeptExt_p.getStateList(uservo);			
			request.getSession().setAttribute("stateList",stateList);
			
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return objExtInst_p;
		
	}
	
	//To Save the ExtInstitute Details 
	public static boolean saveExtInstituteDtl(ExtInstituteVO extInstituteModel,	String strMode_p) 
	{

		String strmsgText = "";
		ExtInstituteMstDATA objExtInstituteData_p = null;
		ExtInstituteVO objExtInstituteModel;
		boolean bExistStatus=false;

		try {
			objExtInstituteData_p = new ExtInstituteMstDATA();
			objExtInstituteModel= new ExtInstituteVO();			
			objExtInstituteModel=extInstituteModel;
			
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			objExtInstituteModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=objExtInstituteData_p.saveExtInstituteDtl(objExtInstituteModel,strMode_p,uservo);

			if (objExtInstituteModel.getStrMsgType()!=null && objExtInstituteModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objExtInstituteModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objExtInstituteModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objExtInstituteModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "OccupationAction.OccupationDATA.saveOccupationDtl(vo) --> "
					+ e.getMessage();
			//HisException eObj = new HisException("mms","CurrencyMstDATA->insertRecord()", strmsgText);
			extInstituteModel.setStrErrorMsg("Application Error ,Contact System Administrator!  ");
					//+ eObj.getErrorID() + "],Contact System Administrator! ");

			//eObj = null;
		} finally 
		{
			objExtInstituteModel = null;
			objExtInstituteData_p = null;
		}
		return bExistStatus;
	}
	
	//To get the Data for Modify Page
	public static ExtInstituteVO modifyRecord( HttpServletRequest request) 
	{
		ExtInstituteMstDATA ExtInstituteMstDATA_obj = null;
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		ExtInstituteVO extInstituteModel =new ExtInstituteVO();
		String strChk = "";
		Map mp= new LinkedHashMap();
		
		try {
			
			ExtInstituteMstDATA_obj = new ExtInstituteMstDATA();
            String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			extInstituteModel.setStrInstituteCode(strTemp2[0]);
			extInstituteModel.setStrHospitalCode(strTemp2[1]);
			ExtInstituteVO ExtInstituteVO_p=ExtInstituteMstDATA_obj.modifyRecordExtInstituteMst(extInstituteModel);
			
			if (extInstituteModel.getStrMsgType().equals("1")) {
				throw new Exception(extInstituteModel.getStrMsgString());
			}
			
			HelperMethods.populate(extInstituteModel, ExtInstituteVO_p);
			
			//Audit Log Initiation
			/*String auditlogProcessId=RegistrationConfig.AUDIT_LOG_EXT_INSTITUTE_MASTER;
			mp.put("save_1", extInstituteModel);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);*/

		} 
		catch (Exception e) 
		{
			strMsgText = "QualityTestMstDATA.modifyRecord(fb,request) --> "
					+ e.getMessage();
			HisException eObj = new HisException("cssd","QualityTestMstDATA->modifyRecord()", strMsgText);
			//objOccupationVO.setStrErrMsg("Application Error [ERROR ID : "
			//		+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			ExtInstituteMstDATA_obj = null;
			strMsgText = null;
		}
		return extInstituteModel;

	}
	
	//To update the ExtInstitute Details
	public static boolean updateExtInstituteDtl(ExtInstituteVO extInstituteModel,String strMode_p)
	{

		String strmsgText = "";
		ExtInstituteMstDATA ExtInstituteMstDATA_obj = null;
		ExtInstituteVO objExtInstituteModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try {
			ExtInstituteMstDATA_obj = new ExtInstituteMstDATA();
			objExtInstituteModel= new ExtInstituteVO();
			
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			objExtInstituteModel=extInstituteModel;
			objExtInstituteModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=ExtInstituteMstDATA_obj.updateExtInstituteDtl(objExtInstituteModel,strMode_p,uservo);

			//Audit Log on update
			/*if(bExistStatus)
			{
				mp.put("save_1"  , objExtInstituteModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objExtInstituteModel.getStrInstituteCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}*/
			
			if (objExtInstituteModel.getStrMsgType()!=null && objExtInstituteModel.getStrMsgType().equals("1")) {
				throw new Exception(objExtInstituteModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objExtInstituteModel.setStrWarning("Data already exist");
			} else {
				objExtInstituteModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "OccupationAction.OccupationDATA.saveOccupationDtl(vo) --> "
					+ e.getMessage();
			//HisException eObj = new HisException("mms","CurrencyMstDATA->insertRecord()", strmsgText);
			extInstituteModel.setStrErrorMsg("Application Error [ERROR ID : ");
					//+ eObj.getErrorID() + "],Contact System Administrator! ");

			//eObj = null;
		} finally {

			objExtInstituteModel = null;
			ExtInstituteMstDATA_obj = null;
		}
		return bExistStatus;
	}

}
