/**
 * 
 */
package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import registration.config.RegistrationConfig;
import registration.masters.controller.data.DepartmentMstDATA;
import registration.masters.controller.data.DeptUnitRoomMstDATA;
import registration.masters.controller.data.UnitConsultantMstDATA;
import registration.masters.controller.data.UnitMstDATA;
import vo.registration.DepartmentVO;
import vo.registration.DeptUnitRoomVO;
import vo.registration.RoomVO;
import vo.registration.UnitConsultantVO;
import vo.registration.UnitVO;
import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

/**
 @author s.singaravelan
 * Creation Date:- 16-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 * 
 */
public class UnitConsultantMstUTIL implements MasterInterface {
	
	
	
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
		String[] columnHdr = { "Employee"};
		return columnHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {		
		String[] strComboHdr = { "0", "Department","0","Unit","1", "Record Status"  };
		return strComboHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {
		String[] strComboQuery = new String[3];
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);
		String strCombo[]=request.getParameterValues("combo");
		
		strComboQuery[0] = registration.qryHandler_master
						   .getQuery(1, "select.deptCombo.deptUnitRoomMst").replace("?",uservo.getHospitalCode());

		if (strCombo != null)
		{
			strComboQuery[1]=registration.qryHandler_master
						   .getQuery(1, "select.unitCombo.deptUnitRoomMst").replace("?",uservo.getHospitalCode());
			strComboQuery[1]=strComboQuery[1]+registration.qryHandler_master
					   .getQuery(1, "select.unitCombo.deptUnitRoomMst.cond.0").replace("?",strCombo[0]);
		}
		else
		strComboQuery[1] = registration.qryHandler_master
			   .getQuery(1, "select.unitCombo.deptUnitRoomMst").replace("?",uservo.getHospitalCode());
		
		strComboQuery[2] = "1^Active";//#2^Inactive";
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
				"unitConsultantMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				if (cmb[0].length()<2)
					cmb[0]="0"+cmb[0];
				
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.unitConsultantMst.cond.0")
						.replace("?", "1"));
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);

			}
			else
			{
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.unitConsultantMst.cond.0")
						.replace("?", ""));
			}
			if (!cmb[1].equals("0")) {
				//if (cmb[1].length()<2)
				//	cmb[1]="0"+cmb[1];
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.unitConsultantMst.cond.1")
						.replace("?", "1"));
				
				if (cmb[1].length()<2)
					brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]+"0"+cmb[1]);
				else
					brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
							.lastIndexOf("1") + 1, cmb[0]+cmb[1]);	

			}
			else
			{
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.unitConsultantMst.cond.1")
						.replace("?", ""));
			}
			if (!cmb[2].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[2]);
			}
		}
		else{
			brMainQuery.append(" AND "
					+ registration.qryHandler_master.getQuery(1,
					"select.unitConsultantMst.cond.0")
					.replace("?", "9"));
		}
		
		return brMainQuery.toString();
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {		
		String strMasterName = "Unit Consultant Master";
		return strMasterName;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {		
		//String strOrderBy[] = { "1", "UPPER(HGNUM_SLNO)"};
		String strOrderBy[] = { "1", "UPPER(C.STR_EMP_FULL_NAME)"};
		//String strOrderBy[] = {};
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
		//String strSearchField[] = {"1", "UPPER(HGNUM_SLNO)"};
		String strSearchField[] = { "1", "UPPER(C.STR_EMP_FULL_NAME)"};
		//String strSearchField[] = {};
		return strSearchField;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Employee");
		viewHdr.add("D");
		viewHdr.add("Level");
		viewHdr.add("D");		
		return viewHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"unitConsultantMst.view");
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
	
	//To populate the Unit,Department and Consultant values in the UnitConsultant Master Screen 
	public static UnitConsultantVO getUnitConsultantEssentials(UnitConsultantVO unitconsultantmodel)
	{
		UnitConsultantMstDATA objUnitConsultantData_p = null;
		UnitVO objUnitModel_p = null;
		DepartmentVO objDeptModel_p = null;

		String strTemp[] = null;
		String strChk = "";
		String deptUnitCode="";

		try{
			
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			objDeptModel_p=new DepartmentVO();
			objUnitModel_p= new UnitVO();		
			objUnitConsultantData_p = new UnitConsultantMstDATA();						
			
			/*if(unitconsultantmodel==null)
			{*/
				System.out.println("------+On Load+-------");
				
				unitconsultantmodel=new UnitConsultantVO();
				
				String strCombos[] = request.getParameterValues("combo");
				objDeptModel_p.setStrDeptCode(strCombos[0]);
				objUnitModel_p.setStrUnitCode(strCombos[1]);
				
				if(strCombos[1].length()<2)
					deptUnitCode=strCombos[0]+"0"+strCombos[1];
				else
					deptUnitCode=strCombos[0]+strCombos[1];
				
				objUnitModel_p.setStrDeptUnitCode(deptUnitCode);				
				objDeptModel_p.setStrHospitalCode(uservo.getHospitalCode());	
				objUnitModel_p.setStrHospitalCode(uservo.getHospitalCode());

				
				objDeptModel_p=objUnitConsultantData_p.getDeptDtl(objDeptModel_p);
				objUnitModel_p=objUnitConsultantData_p.getUnitDtl(objUnitModel_p);
				
				unitconsultantmodel.setStrDeptName(objDeptModel_p.getStrDeptName());
				unitconsultantmodel.setStrUnitName(objUnitModel_p.getStrUnitName());
				unitconsultantmodel.setStrDeptUnitCode(objUnitModel_p.getStrDeptUnitCode());
				unitconsultantmodel.setStrHospitalCode(uservo.getHospitalCode());

			//}	
			/*else
			{
				System.out.println("------+On ReLoad+-------");
				unitconsultantmodel.setStrHospitalCode(uservo.getHospitalCode());
			}*/			
					
			List consultantList=objUnitConsultantData_p.getConsultantList(unitconsultantmodel, uservo);
			request.getSession().setAttribute("consultantList",consultantList);	
			
			request.getSession().setAttribute("newConsultants",new ArrayList());

		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return unitconsultantmodel;
	}
	
	//To Show Already allocated Consultant Details in the UnitConsultant Master Mst
	public static void getAlreadyAllotedConsultants(UnitConsultantVO unitconsultantmodel)
	{
		String deptUnitCode=unitconsultantmodel.getStrDeptUnitCode();
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);
		
		try{
			
			UnitConsultantVO[] unitConsultanyMasterVO=UnitConsultantMstDATA.getAllotedConsultantsToUnit(deptUnitCode,uservo);			
			String [] tempid =new String[unitConsultanyMasterVO.length];
			String [] tempName =new String[unitConsultanyMasterVO.length];			
			String [] tempisHou =new String[unitConsultanyMasterVO.length];
			
			for(int i=0;i<tempid.length;i++){
				tempid[i]=unitConsultanyMasterVO[i].getStrEmpCode();
				tempName[i]=unitConsultanyMasterVO[i].getStrEmpName();
				tempisHou[i]=unitConsultanyMasterVO[i].getStrIsHeadOfUnit();					
			}	
			
			unitconsultantmodel.setEmployeeIdExisting(tempid);
			unitconsultantmodel.setEmployeeNameExisting(tempName);
			unitconsultantmodel.setEmployeeHOU(tempisHou);			
			
			//remove from all consultant list already alloted consultant
			for(int i=0;i<unitConsultanyMasterVO.length;i++){				
				unitconsultantmodel.setStrEmpCode(unitConsultanyMasterVO[i].getStrEmpCode());
				setConsultantOptions(request,unitconsultantmodel);			
			}	
			//WebUTIL.setAttributeInSession(request,"existingRooms",new ArrayList());
			WebUTIL.setAttributeInSession(request,"existing",tempName);

		}catch (Exception e) 
		{
			unitconsultantmodel.setStrErrorMsg("Application Error ,Contact System Administrator!  ");
			e.printStackTrace();
		}finally{
			
		}

	}
	
	//To Show All allocated Consultant Details in the UnitConsultant Master Mst
	public static UnitConsultantVO[] getAllConsultants(UnitConsultantVO unitconsultantmodel)
	{
		UnitConsultantVO[] unitConsultanyMasterVO = null;		
			
		try{		
			
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);				
					
			unitConsultanyMasterVO=UnitConsultantMstDATA.getAllotedConsultantsToUnit(unitconsultantmodel.getStrDeptUnitCode(),uservo);
			WebUTIL.setAttributeInSession(request,"existingConsultants",unitConsultanyMasterVO);

		}catch (Exception e) 
		{
			e.printStackTrace();
		}finally{
			
		}		
		return unitConsultanyMasterVO;

	}
	
	//To Add or Remove the Consultants from the List
	public static void setConsultantOptions(HttpServletRequest request,UnitConsultantVO unitconsultantmodel){	
		
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			
			Collection colRooms = (Collection)request.getSession().getAttribute("consultantList");				
			Collection newCol = new ArrayList(colRooms);
			newCol=WebUTIL.removeEntriesfromOptions(newCol, unitconsultantmodel.getStrEmpCode());
			WebUTIL.setAttributeInSession(request,"consultantList", newCol);			
			
	}
	
	//To put the Consultant Data for Add Page
	public static UnitConsultantVO addConsultantUnitRecord(UnitConsultantVO unitconsultantmodel) 
	{
		UnitConsultantMstDATA UnitConsultantMstDATA_obj = null;
		String strMsgText = "";
		String empName="";
		
		try {
			
			UnitConsultantMstDATA_obj = new UnitConsultantMstDATA();
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			List newConsultants=(List)request.getSession().getAttribute("newConsultants");			
			if(newConsultants==null){
				newConsultants=new LinkedList();
			}				
						
			Collection colRooms = (Collection)request.getSession().getAttribute("consultantList");
			Collection newCol = new ArrayList(colRooms);
			for(int i=0;i<newCol.size();i++){
				Entry obj=(Entry)((List)newCol).get(i);							
				if(obj.getValue().equalsIgnoreCase(unitconsultantmodel.getStrEmpCode())){
					empName=obj.getLabel();			
				}			
			}
			
			//getAlreadyAllotedRooms(deptModel_p);
			newConsultants.add(new UnitConsultantVO(unitconsultantmodel.getStrEmpCode(),empName));
		
			request.getSession().setAttribute("newConsultants",newConsultants);	
			
			newCol=WebUTIL.removeEntriesfromOptions(newCol, unitconsultantmodel.getStrEmpCode());	
			request.getSession().setAttribute("consultantList",newCol);
		   		    
		} 
		catch (Exception e) 
		{
			strMsgText = "QualityTestMstDATA.modifyRecord(fb,request) --> "
					+ e.getMessage();
			e.printStackTrace();
			//HisException eObj = new HisException("cssd","QualityTestMstDATA->modifyRecord()", strMsgText);
			//objOccupationVO.setStrErrMsg("Application Error [ERROR ID : "
			//		+ eObj.getErrorID() + "],Contact System Administrator! ");

			//eObj = null;
		}
		finally 
		{
			UnitConsultantMstDATA_obj = null;
			strMsgText = null;
		}
		return unitconsultantmodel;

	}
	
	//To Remove the Added Consultants in the Unit Consultant Mst
	public static void removeUnitConsultantRecord(UnitConsultantVO unitconsultantmodel)
	{
		
		try{
		String index = unitconsultantmodel.getRemoveConsultant();
		System.out.println("index"+index);
		index=index.replace(',', ' ').trim();	
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		List newConsultants=(List)request.getSession().getAttribute("newConsultants");
		int size=newConsultants.size();
		newConsultants.remove(index);
		UnitConsultantVO obj=(UnitConsultantVO)newConsultants.get(Integer.parseInt(index));
		List consultantList=(List)request.getSession().getAttribute("consultantList");
		consultantList.add(new Entry(obj.getStrEmpName(),obj.getStrEmpCode()));
		if(size==0){
			newConsultants=null;
		}	
		
		else{
			System.out.println("inside else");
			for(int i=Integer.parseInt(index)+1;i<size;i++){
				obj=(UnitConsultantVO)newConsultants.get(i);
			}
			newConsultants.remove(Integer.parseInt(index));	
		}		
		
		
		request.getSession().setAttribute("newConsultants",newConsultants);	
		request.getSession().setAttribute("consultantList",consultantList);	
		
		getAlreadyAllotedConsultants(unitconsultantmodel);
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{			
		}
	}
	
	//To Save the Unit Consultant Details in the Unit Consultant Mst
	public static boolean saveUnitConsultantDtl(UnitConsultantVO unitconsultantmodel,String strMode_p){
		
		boolean bExistStatus=false;
		String strmsgText="";
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	

		List newConsultants=(List)request.getSession().getAttribute("newConsultants");
		
		UnitConsultantVO[] UnitConsultantMasterVO=new UnitConsultantVO[newConsultants.size()];		
		List<String> isHOU=new ArrayList<String>();		
		String[] arrayOfCapacityMode =unitconsultantmodel.getEmployeeHOU();
	
		if(arrayOfCapacityMode!=null){			
		for(int i=0; i < arrayOfCapacityMode.length;i++)
			isHOU.add(arrayOfCapacityMode[i]);
		}
					
		for(int i=0;i<newConsultants.size();i++){
			
			UnitConsultantMasterVO[i]=new UnitConsultantVO();											
			UnitConsultantVO obj=(UnitConsultantVO)newConsultants.get(i);
			 
			UnitConsultantMasterVO[i].setStrDeptUnitCode(unitconsultantmodel.getStrDeptUnitCode());
			UnitConsultantMasterVO[i].setStrEmpCode(obj.getStrEmpCode());
			UnitConsultantMasterVO[i].setStrHierarchyLevel(unitconsultantmodel.getHierarchyLevel()[i]);
			//UnitConsultantMasterVO[i].setStrIsHeadOfUnit(unitconsultantmodel.getEmployeeHOU()[i]);
			
			//if(capacityModeList.contains(Integer.toString(i)))
			if(isHOU.get(i).contains("true"))
				UnitConsultantMasterVO[i].setStrIsHeadOfUnit(RegistrationConfig.IS_VALID_ACTIVE);
			else
				UnitConsultantMasterVO[i].setStrIsHeadOfUnit("");
					
		}	
		
		try{		

			bExistStatus=UnitConsultantMstDATA.saveUnitConsultantDtl(UnitConsultantMasterVO,strMode_p,uservo);
			if (unitconsultantmodel.getStrMsgType()!=null && unitconsultantmodel.getStrMsgType().equals("1")) 
			{
				throw new Exception(unitconsultantmodel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				unitconsultantmodel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				unitconsultantmodel.setStrMsg("Data Saved Successfully");
				getUnitConsultantEssentials(unitconsultantmodel);
				getAlreadyAllotedConsultants(unitconsultantmodel);
			}
			
		}catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "UnitConsultantMstAction.UnitConsultantMstDATA.saveRoomToUnit(vo) --> "+ e.getMessage();			
			unitconsultantmodel.setStrErrorMsg("Application Error ,Contact System Administrator!  ");

			//eObj = null;
		} finally 
		{
			unitconsultantmodel = null;
		}
		return bExistStatus;
		
	}
	
	//To get the Data for Modify Page
	public static UnitConsultantVO modifyRecord( HttpServletRequest request) 
	{
		UnitConsultantMstDATA UnitConsultantMstDATA_obj = null;
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		UnitConsultantVO unitconsultantmodel =new UnitConsultantVO();
		String strChk = "";
		UnitVO objUnitModel_p = null;
		DepartmentVO objDeptModel_p = null;
		Map mp= new LinkedHashMap();
		
		try {
			
			UnitConsultantMstDATA_obj = new UnitConsultantMstDATA();
			objDeptModel_p=new DepartmentVO();
			objUnitModel_p= new UnitVO();		
            String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			unitconsultantmodel.setStrDeptUnitCode(strTemp2[0]);
			unitconsultantmodel.setStrEmpCode(strTemp2[1]);
			unitconsultantmodel.setStrHospitalCode(strTemp2[2]);
			
			System.out.println("---strTemp2[0].substring(0, 3)---"+strTemp2[0].substring(0, 3)+"----");
			System.out.println("---strTemp2[0].substring(3, 5)---"+strTemp2[0].substring(3, 5)+"----");

			
			objUnitModel_p.setStrDeptUnitCode(strTemp2[0]);	
			objDeptModel_p.setStrDeptCode(strTemp2[0].substring(0, 3));
			objUnitModel_p.setStrUnitCode(strTemp2[0].substring(3, 5));
			objDeptModel_p.setStrHospitalCode(strTemp2[2]);	
			objUnitModel_p.setStrHospitalCode(strTemp2[2]);
			
			//UnitConsultantVO UnitConsultantVO_p=UnitConsultantMstDATA_obj.modifyRecordUnitConsultantMst(unitconsultantmodel);
			objDeptModel_p=UnitConsultantMstDATA_obj.getDeptDtl(objDeptModel_p);
			objUnitModel_p=UnitConsultantMstDATA_obj.getUnitDtl(objUnitModel_p);			
		
			//unitconsultantmodel.setStrEmpName(UnitConsultantVO_p.getStrEmpName());		
			unitconsultantmodel.setStrDeptName(objDeptModel_p.getStrDeptName());
			unitconsultantmodel.setStrUnitName(objUnitModel_p.getStrUnitName());
			unitconsultantmodel.setStrDeptUnitCode(objUnitModel_p.getStrDeptUnitCode());
			
			//Audit Log Initiation
			/*String auditlogProcessId=RegistrationConfig.AUDIT_LOG_UNIT_CONSULTANT_MASTER;
			mp.put("save_1", unitconsultantmodel);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);*/
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strMsgText = "QualityTestMstDATA.modifyRecord(fb,request) --> "
					+ e.getMessage();
			HisException eObj = new HisException("cssd","QualityTestMstDATA->modifyRecord()", strMsgText);
			//objOccupationVO.setStrErrMsg("Application Error [ERROR ID : "
			//		+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			UnitConsultantMstDATA_obj = null;
			strMsgText = null;
		}
		return unitconsultantmodel;

	}
	
	//To update the Unit Consultant Details
	public static boolean updateUnitConsultantMstDtl(UnitConsultantVO unitConsultantModel,String strMode_p)
	{

		String strmsgText = "";
		UnitConsultantMstDATA UnitConsultantMstDATA_obj = null;
		UnitConsultantVO objUnitConsultantModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();
		
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		
		UnitConsultantVO[] existingsConsultants=(UnitConsultantVO[])request.getSession().getAttribute("existingConsultants");
		//UnitConsultantVO[] UnitConsultantMasterVO=new UnitConsultantVO[existingsConsultants.size()];	
		
		String[] arrayOfHierarchyLevel =unitConsultantModel.getStrHierarchyLevel().split(",");
		String[] arrayOfHOU =unitConsultantModel.getStrIsHeadOfUnit().split(",");
		
		for(int i=0;i<existingsConsultants.length;i++){
			existingsConsultants[i].setStrHierarchyLevel(arrayOfHierarchyLevel[i]);
			existingsConsultants[i].setStrIsHeadOfUnit(arrayOfHOU[i]);
			
		}

		try {
			UnitConsultantMstDATA_obj = new UnitConsultantMstDATA();
			objUnitConsultantModel= new UnitConsultantVO();
			
			
			objUnitConsultantModel=unitConsultantModel;
			objUnitConsultantModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=UnitConsultantMstDATA_obj.updateUnitConsultantDtl(existingsConsultants,strMode_p,uservo);
			
			/*if(bExistStatus)
			{
				mp.put("save_1"  , objUnitConsultantModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objUnitConsultantModel.getStrDeptUnitCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}*/

			if (objUnitConsultantModel.getStrMsgType()!=null && objUnitConsultantModel.getStrMsgType().equals("1")) {
				throw new Exception(objUnitConsultantModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objUnitConsultantModel.setStrWarning("Data already exist");
			} else {
				objUnitConsultantModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "OccupationAction.OccupationDATA.saveOccupationDtl(vo) --> "
					+ e.getMessage();
			//HisException eObj = new HisException("mms","CurrencyMstDATA->insertRecord()", strmsgText);
			unitConsultantModel.setStrErrorMsg("Application Error [ERROR ID : ");
					//+ eObj.getErrorID() + "],Contact System Administrator! ");

			//eObj = null;
		} finally {

			objUnitConsultantModel = null;
			UnitConsultantMstDATA_obj = null;
		}
		return bExistStatus;
	}
	
		

}
