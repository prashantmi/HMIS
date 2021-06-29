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
import registration.masters.controller.data.UnitMstDATA;
import vo.registration.DepartmentVO;
import vo.registration.DeptUnitRoomVO;
import vo.registration.RoomVO;
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
 * Creation Date:- 08-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 * 
 */
public class DeptUnitRoomMstUTIL implements MasterInterface {
	
	
	
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
		String[] columnHdr = { "Room Sequence","Room Name","Capacity","Capacity Mode"};
		return columnHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {		
		String[] strComboHdr = { "0", "Department","0","Unit" ,"1", "Record Status"  };
		//String[] strComboHdr = { "0", "Department","1", "Record Status"  };
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
				"deptUnitRoomMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.deptUnitRoomMst.cond.0")
						.replace("?", "1"));
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);

			}
			else{
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.deptUnitRoomMst.cond.0")
						.replace("?", ""));
			}
			if (!cmb[1].equals("0")) {
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.deptUnitRoomMst.cond.1")
						.replace("?", "1"));
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[1]);

			}
			else{
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.deptUnitRoomMst.cond.1")
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
					"select.deptUnitRoomMst.cond.0")
					.replace("?", ""));
		}
		
		System.out.println(brMainQuery.toString());
		return brMainQuery.toString();
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {		
		String strMasterName = "Department Unit Room Master";
		return strMasterName;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {		
		String strOrderBy[] = { "2", "UPPER(HGSTR_ROOM_NAME)"};
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
		String strSearchField[] = {"2", "UPPER(HGSTR_ROOM_NAME)"};
		return strSearchField;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Room Name :");
		viewHdr.add("D");
		viewHdr.add("Room Sequence :");
		viewHdr.add("D");
		viewHdr.add("Capacity :");
		viewHdr.add("D");
		viewHdr.add("Capacity Mode :");
		viewHdr.add("D");
		return viewHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"deptUnitRoomMst.view");
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
	
	//To populate the Unit,Room and Department values in the DepartmentUnitRoom Master Screen 
	public static DeptUnitRoomVO getDeptUnitRoomEssentials(DeptUnitRoomVO deptunitmodel)
	{
		DeptUnitRoomMstDATA objDeptUnitRoomData_p = null;
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
			objDeptUnitRoomData_p = new DeptUnitRoomMstDATA();
						
			
			/*if(deptunitmodel==null)
			{*/
				System.out.println("------+On Load+-------");
				
				//deptunitmodel=new DeptUnitRoomVO();
				
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

				
				objDeptModel_p=objDeptUnitRoomData_p.getDeptDtl(objDeptModel_p);
				objUnitModel_p=objDeptUnitRoomData_p.getUnitDtl(objUnitModel_p);
				
				deptunitmodel.setStrDeptName(objDeptModel_p.getStrDeptName());
				deptunitmodel.setStrDeptCode(objDeptModel_p.getStrDeptCode());
				deptunitmodel.setStrUnitName(objUnitModel_p.getStrUnitName());
				deptunitmodel.setStrUnitCode(objUnitModel_p.getStrUnitCode());
				deptunitmodel.setStrDeptUnitCode(objUnitModel_p.getStrDeptUnitCode());

			//}	
			/*else
			{*/
				//System.out.println("------+On ReLoad+-------");
				deptunitmodel.setStrHospitalCode(uservo.getHospitalCode());
			//}			
					
			List roomList=objDeptUnitRoomData_p.getRoomsList(deptunitmodel, uservo);
			//request.getSession().setAttribute("roomList",roomList);
			if(roomList.equals(null) || roomList.size()==0)
				deptunitmodel.setStrWarning("No Rooms Available for Mapping..!");
			request.getSession().setAttribute("roomList",roomList);			
			request.getSession().setAttribute("newRooms",new ArrayList());
			
			//getAlreadyAllotedRooms(deptunitmodel);
			
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return deptunitmodel;
	}
	
	//To Show Already allocated room Details in the Dept Unit Room Mst
	public static void getAlreadyAllotedRooms(DeptUnitRoomVO deptunitroommodel)
	{
		String deptUnitCode=deptunitroommodel.getStrDeptUnitCode();
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);
		
		try{
			
			DeptUnitRoomVO[] departmentUnitRoomMasterVO=DeptUnitRoomMstDATA.getAllotedRoomsToUnit(deptUnitCode,uservo);			
			String [] tempid =new String[departmentUnitRoomMasterVO.length];
			String [] tempName =new String[departmentUnitRoomMasterVO.length];			
			String [] tempseqNo =new String[departmentUnitRoomMasterVO.length];
			
			for(int i=0;i<tempid.length;i++){
				tempid[i]=departmentUnitRoomMasterVO[i].getStrRoomCode();
				tempName[i]=departmentUnitRoomMasterVO[i].getStrRoomName();
				tempseqNo[i]=departmentUnitRoomMasterVO[i].getStrRoomSequence();					
			}	
			
			deptunitroommodel.setExistingRoomCode(tempid);
			deptunitroommodel.setExistingRoomName(tempName);
			deptunitroommodel.setExistingsequenceNo(tempseqNo);			
			
			//remove from all room list already alloted rooms
			for(int i=0;i<departmentUnitRoomMasterVO.length;i++){				
				deptunitroommodel.setStrRoomCode(departmentUnitRoomMasterVO[i].getStrRoomCode());
				setRoomOptions(request,deptunitroommodel);			
			}	
			//WebUTIL.setAttributeInSession(request,"existingRooms",new ArrayList());
			WebUTIL.setAttributeInSession(request,"existingRooms",tempName);

		}catch (Exception e) 
		{
			deptunitroommodel.setStrErrorMsg("Application Error ,Contact System Administrator!  ");
			e.printStackTrace();
		}finally{
			
		}

	}
	
	//To put the Room Data for Add Page
	public static DeptUnitRoomVO addRoomDeptUnitRecord(DeptUnitRoomVO deptunitroommodel) 
	{
		DeptUnitRoomMstDATA DeptUnitRoomMstDATA_obj = null;
		String strMsgText = "";
		String roomName="";
		DeptUnitRoomVO deptModel_p =new DeptUnitRoomVO();
		RoomVO roommodel_p=new RoomVO();
		String[] arrSequenceNo=deptunitroommodel.getExistingsequenceNo();		
		int newSequenceNo;		
		
		try {
			
			DeptUnitRoomMstDATA_obj = new DeptUnitRoomMstDATA();
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);			
								
			deptModel_p=deptunitroommodel;			
		
			List newRooms=(List)request.getSession().getAttribute("newRooms");				
			if(newRooms==null){
				newRooms=new LinkedList();
				newSequenceNo=arrSequenceNo.length+1;	
			}else{
				if(arrSequenceNo.length==0)
					newSequenceNo=arrSequenceNo.length+newRooms.size()+1;	
				else
				{
					if(arrSequenceNo.length>1)
					{
					String list=arrSequenceNo[arrSequenceNo.length-1];
					String[] seqList=list.split(",");
					newSequenceNo=Integer.parseInt(seqList[arrSequenceNo.length-1].trim())+newRooms.size()+1;		
					//newSequenceNo=Integer.parseInt(arrSequenceNo[arrSequenceNo.length-1])+newRooms.size()+1;
					}
					else
					{
						String list=arrSequenceNo[arrSequenceNo.length-1];
						newSequenceNo=Integer.parseInt(list.trim())+newRooms.size()+1;		
					}
				}
			}	
			
			System.out.println("------"+arrSequenceNo.length+"-------");
			System.out.println("------"+newRooms.size()+"-------");
			
			Collection colRooms = (Collection)request.getSession().getAttribute("roomList");
			Collection newCol = new ArrayList(colRooms);
			for(int i=0;i<newCol.size();i++){
				Entry obj=(Entry)((List)newCol).get(i);							
				if(obj.getValue().equalsIgnoreCase(deptModel_p.getStrRoomCode())){
					roomName=obj.getLabel();			
				}			
			}
			
			String capcity[]=deptunitroommodel.getCapacity();
			String capcityMode[]=deptunitroommodel.getCapacityMode();			
			
			//getAlreadyAllotedRooms(deptModel_p);
			
			newRooms.add(new DeptUnitRoomVO(deptModel_p.getStrRoomCode(),roomName,Integer.toString(newSequenceNo),"",""));
			for(int i=0;i<capcity.length;i++)
			{
					DeptUnitRoomVO vo=(DeptUnitRoomVO)newRooms.get(i);
					newRooms.remove(i);
					newRooms.add(i,new DeptUnitRoomVO(vo.getStrRoomCode(),vo.getStrRoomName(),vo.getStrRoomSequence(),capcity[i],capcityMode[i]));
			}
			request.getSession().setAttribute("newRooms",newRooms);	
			
			newCol=WebUTIL.removeEntriesfromOptions(newCol, deptModel_p.getStrRoomCode());
			if(newCol.equals(null) || newCol.size()==0)
				deptModel_p.setStrWarning("No Rooms Available for Mapping..!");
			request.getSession().setAttribute("roomList",newCol);
			
			
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
			DeptUnitRoomMstDATA_obj = null;
			strMsgText = null;
		}
		return deptModel_p;

	}
	
	//To Remove the Added Rooms in the Dept Unit Room Mst
	public static void removeRoomDeptUnitRecord(DeptUnitRoomVO deptunitroommodel)
	{
		
		try{
		String index = deptunitroommodel.getStrRemoveRoom();
		System.out.println("index"+index);
		index=index.replace(',', ' ').trim();	
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		List newRooms=(List)request.getSession().getAttribute("newRooms");
		int size=newRooms.size();
		newRooms.remove(index);
		DeptUnitRoomVO obj=(DeptUnitRoomVO)newRooms.get(Integer.parseInt(index));
		List roomsLst=(List)request.getSession().getAttribute("roomList");
		roomsLst.add(new Entry(obj.getStrRoomName(),obj.getStrRoomCode()));
		if(size==0){
			newRooms=null;
		}	
		
		else{
			System.out.println("inside else");
			for(int i=Integer.parseInt(index)+1;i<size;i++){
				obj=(DeptUnitRoomVO)newRooms.get(i);
				obj.setStrRoomSequence(String.valueOf(Integer.parseInt(obj.getStrRoomSequence())-1));
			}
			newRooms.remove(Integer.parseInt(index));	
		}		
		
		String [] capacity= deptunitroommodel.getCapacity();			
		capacity = WebUTIL.removeFromArray(capacity, Integer.parseInt(index));				
		deptunitroommodel.setCapacity(capacity);
		
		request.getSession().setAttribute("newRooms",newRooms);	
		request.getSession().setAttribute("roomList",roomsLst);	
		
		getAlreadyAllotedRooms(deptunitroommodel);
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{			
		}
	}
	
	//To Save the Dept Unit room Details in the Dept Unit Room Mst
	public static boolean saveDeptUnitRoomDtl(DeptUnitRoomVO deptunitroommodel,String strMode_p){
		
		boolean bExistStatus=false;
		String strmsgText="";
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	

		List newRooms=(List)request.getSession().getAttribute("newRooms");
		
		DeptUnitRoomVO[] deptUnitRoomMasterVO=new DeptUnitRoomVO[newRooms.size()];		
		List<String> capacityModeList=new ArrayList<String>();		
		String[] arrayOfCapacityMode =deptunitroommodel.getCapacityMode();
	
		if(arrayOfCapacityMode!=null){			
		for(int i=0; i < arrayOfCapacityMode.length;i++)
			capacityModeList.add(arrayOfCapacityMode[i]);
		}
		
		//Map<Integer, Boolean> arrayOfCapacityMode=deptunitroommodel.getCapacityMode();
							
		for(int i=0;i<newRooms.size();i++){
			
			deptUnitRoomMasterVO[i]=new DeptUnitRoomVO();											
			DeptUnitRoomVO obj=(DeptUnitRoomVO)newRooms.get(i);
			 
			deptUnitRoomMasterVO[i].setStrRoomCode(obj.getStrRoomCode());
			deptUnitRoomMasterVO[i].setStrRoomSequence(obj.getStrRoomSequence());
			deptUnitRoomMasterVO[i].setStrCapacity(deptunitroommodel.getCapacity()[i]);
			deptUnitRoomMasterVO[i].setStrDeptCode(deptunitroommodel.getStrDeptCode());
			deptUnitRoomMasterVO[i].setStrUnitCode(deptunitroommodel.getStrUnitCode());
			deptUnitRoomMasterVO[i].setStrDeptUnitCode(deptunitroommodel.getStrDeptUnitCode());		
			
			System.out.println("-----"+capacityModeList+"----");
			System.out.println("-----"+Integer.toString(i)+"----");

			if(capacityModeList.get(i).contains("true"))
				deptUnitRoomMasterVO[i].setStrCapacityMode(RegistrationConfig.UNIT_ROOM_DAY_WISE_CAPACITY_MODE);
			else
				deptUnitRoomMasterVO[i].setStrCapacityMode(RegistrationConfig.UNIT_ROOM_WISE_CAPACITY_MODE);		
					
		}	
		
		try{		

			bExistStatus=DeptUnitRoomMstDATA.saveDeptUnitRoomDtl(deptUnitRoomMasterVO,strMode_p,uservo);
			if (deptunitroommodel.getStrMsgType()!=null && deptunitroommodel.getStrMsgType().equals("1")) 
			{
				throw new Exception(deptunitroommodel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				deptunitroommodel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				deptunitroommodel.setStrMsg("Data Saved Successfully");
				getDeptUnitRoomEssentials(deptunitroommodel);
				getAlreadyAllotedRooms(deptunitroommodel);
				request.getSession().setAttribute("newRooms",new ArrayList());
			}
			
		}catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "DeptUnitRoomMstAction.DeptUnitRoomMstDATA.saveRoomToUnit(vo) --> "+ e.getMessage();			
			deptunitroommodel.setStrErrorMsg("Application Error ,Contact System Administrator!  ");

			//eObj = null;
		} finally 
		{
			deptunitroommodel = null;
		}
		return bExistStatus;
		
	}
	
	//To Add or Remove the Rooms from the Rooms List
	public static void setRoomOptions(HttpServletRequest request,DeptUnitRoomVO deptunitroommodel){	
		
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			
			Collection colRooms = (Collection)request.getSession().getAttribute("roomList");				
			Collection newCol = new ArrayList(colRooms);
			newCol=WebUTIL.removeEntriesfromOptions(newCol, deptunitroommodel.getStrRoomCode());
			if(newCol.equals(null) || newCol.size()==0)
				deptunitroommodel.setStrWarning("No Rooms Available for Mapping..!");
			WebUTIL.setAttributeInSession(request,"roomList", newCol);			
			
	}
	
	//To get the Data for Modify Page
	public static DeptUnitRoomVO modifyRecord( HttpServletRequest request) 
	{
		DeptUnitRoomMstDATA DeptUnitRoomMstDATA_obj = null;
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		DeptUnitRoomVO deptunitroomModel =new DeptUnitRoomVO();
		String strChk = "";
		UnitVO objUnitModel_p = null;
		DepartmentVO objDeptModel_p = null;
		Map mp= new LinkedHashMap();
		
		try {
			
			DeptUnitRoomMstDATA_obj = new DeptUnitRoomMstDATA();
			objDeptModel_p=new DepartmentVO();
			objUnitModel_p= new UnitVO();		
            String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			deptunitroomModel.setStrDeptUnitCode(strTemp2[0]);
			deptunitroomModel.setStrRoomCode(strTemp2[1]);
			deptunitroomModel.setStrHospitalCode(strTemp2[2]);
			
			objUnitModel_p.setStrDeptUnitCode(strTemp2[0]);	
			objDeptModel_p.setStrDeptCode(strTemp2[0].substring(0, 3));
			objUnitModel_p.setStrUnitCode(strTemp2[0].substring(3, 5));
			objDeptModel_p.setStrHospitalCode(strTemp2[2]);	
			objUnitModel_p.setStrHospitalCode(strTemp2[2]);
			
			DeptUnitRoomVO DeptUnitRoomVO_p=DeptUnitRoomMstDATA_obj.modifyRecordDepartmentMst(deptunitroomModel);
			objDeptModel_p=DeptUnitRoomMstDATA_obj.getDeptDtl(objDeptModel_p);
			objUnitModel_p=DeptUnitRoomMstDATA_obj.getUnitDtl(objUnitModel_p);			
		
					
			deptunitroomModel.setStrDeptName(objDeptModel_p.getStrDeptName());
			deptunitroomModel.setStrUnitName(objUnitModel_p.getStrUnitName());
			deptunitroomModel.setStrDeptUnitCode(objUnitModel_p.getStrDeptUnitCode());
			deptunitroomModel.setStrDeptCode(DeptUnitRoomVO_p.getStrDeptCode());
			deptunitroomModel.setStrUnitCode(DeptUnitRoomVO_p.getStrUnitCode());	
			deptunitroomModel.setStrRoomCode(DeptUnitRoomVO_p.getStrRoomCode());
			deptunitroomModel.setStrRoomName(DeptUnitRoomVO_p.getStrRoomName());
			deptunitroomModel.setStrRoomSequence(DeptUnitRoomVO_p.getStrRoomSequence());
			deptunitroomModel.setStrCapacity(DeptUnitRoomVO_p.getStrCapacity());
			deptunitroomModel.setStrCapacityMode(DeptUnitRoomVO_p.getStrCapacityMode());

	
			//deptModel.setStrIsValid(strCombos[0]);
			
			//Audit Log Initiation
			/*String auditlogProcessId=RegistrationConfig.AUDIT_LOG_DEPT_UNIT_ROOM_MASTER;
			mp.put("save_1", deptunitroomModel);
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
			DeptUnitRoomMstDATA_obj = null;
			strMsgText = null;
		}
		return deptunitroomModel;

	}
	
	//To update the Department Unit Room Details
	public static boolean updateDeptUnitRoomMstDtl(DeptUnitRoomVO deptModel,String strMode_p)
	{

		String strmsgText = "";
		DeptUnitRoomMstDATA DeptUnitRoomMstDATA_obj = null;
		DeptUnitRoomVO objDeptModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try {
			DeptUnitRoomMstDATA_obj = new DeptUnitRoomMstDATA();
			objDeptModel= new DeptUnitRoomVO();
			
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			objDeptModel=deptModel;
			objDeptModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=DeptUnitRoomMstDATA_obj.updateDeptUnitRoomDtl(objDeptModel,strMode_p,uservo);

			//Audit Log on update
			/*if(bExistStatus)
			{
				mp.put("save_1"  , objDeptModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objDeptModel.getStrDeptUnitCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}*/
			
			if (objDeptModel.getStrMsgType()!=null && objDeptModel.getStrMsgType().equals("1")) {
				throw new Exception(objDeptModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objDeptModel.setStrWarning("Data already exist");
			} else {
				objDeptModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "OccupationAction.OccupationDATA.saveOccupationDtl(vo) --> "
					+ e.getMessage();
			//HisException eObj = new HisException("mms","CurrencyMstDATA->insertRecord()", strmsgText);
			deptModel.setStrErrorMsg("Application Error [ERROR ID : ");
					//+ eObj.getErrorID() + "],Contact System Administrator! ");

			//eObj = null;
		} finally {

			objDeptModel = null;
			DeptUnitRoomMstDATA_obj = null;
		}
		return bExistStatus;
	}
	
		

}
