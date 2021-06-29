package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.IpdPatDocDeskDATA;
import inpatient.transaction.controller.fb.IpdPatDocDeskFB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdPatientDeskDATA;

import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONValue;

public class IpdPatDocDeskUTL extends ControllerUTIL
{
	/** Getting the List of Admitted Patient List on the basis of unitCode & wardCode
	 * @param fb
	 * @param request
	 */
	public static void getAdmittedPatientList(IpdPatDocDeskFB fb,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		PatientDetailVO[] patDtlVO=null;
		Status objStatus = new Status();
		
		try
		{
			fb.setDepartmentUnitCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			fb.setWardCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
			fb.setRoomCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE));
			fb.setSrchCriteria("0");
			String deptUnitCode=fb.getDepartmentUnitCode();
			String wardCode=fb.getWardCode();
			String roomCode=fb.getRoomCode();
			
			if(InpatientConfig.IPD_DOCTOR_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
				deptUnitCode=InpatientConfig.UNIT_ALL_CODE;
			
			patDtlVO=IpdPatDocDeskDATA.getAdmittedPatientList(roomCode,deptUnitCode,wardCode,getUserVO(request));
			
			DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DESK_PATIENT_LIST, patDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	//Get Order By Ascending
	public static void orderByAscending(IpdPatDocDeskFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		PatientDetailVO[] patDtlVO;
		patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		List list=new ArrayList();
		
		for(int i=0;i<patDtlVO.length;i++)
		{
			patDtlVO[i].setSortType(Config.SORT_TYPE_ASC);
			list.add(patDtlVO[i]);
		}
		Collections.sort(list);
		
		patDtlVO=(PatientDetailVO[])list.toArray(patDtlVO);
		session.setAttribute(DynamicDeskConfig.DESK_PATIENT_LIST,patDtlVO);
	}
	
	//Get Order By Descending 
	public static void orderByDescending(IpdPatDocDeskFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		PatientDetailVO[] patDtlVO;
		patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		List list=new ArrayList();
		
		for(int i=0;i<patDtlVO.length;i++)
		{
			patDtlVO[i].setSortType(Config.SORT_TYPE_DSC);
			list.add(patDtlVO[i]);
		}
		Collections.sort(list);
		
		patDtlVO=(PatientDetailVO[])list.toArray(patDtlVO);
		session.setAttribute(DynamicDeskConfig.DESK_PATIENT_LIST,patDtlVO);
	}

	public static void getOrderByAdmissionNo(IpdPatDocDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.ORDER_BY_ADMISSION_NO);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getOrderByPatName(IpdPatDocDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.IPD_PATIENT_OREDRE_BY_NAME);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getOrderByCrNo(IpdPatDocDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getOrderByAdmissionDate(IpdPatDocDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.ORDER_BY_ADMISSION_DATE);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void AJX_G_PATIENTLIST(IpdPatDocDeskFB ipdPatDocDeskFB,HttpServletRequest objRequest, HttpServletResponse objResponse,ActionMapping objMapping_p) 
	{		
		Status objStatus=new Status();
		String outputString="";
		String deskType ="";
		HttpSession session = WebUTIL.getSession(objRequest);	
		Map<String, Object> map1 = new HashMap<String, Object>();
		PatientDetailVO patientDetailVO = new PatientDetailVO(); 
		 
		try
		{
			
			UserVO userVO=getUserVO(objRequest);
			patientDetailVO.setDepartmentUnitCode(ipdPatDocDeskFB.getDepartmentUnitCode());
			patientDetailVO.setWardCode(ipdPatDocDeskFB.getWardCode());
			patientDetailVO.setEpisodeVisitType(ipdPatDocDeskFB.getVisitType());
			patientDetailVO.setRoomCode(ipdPatDocDeskFB.getRoomCode());
			if(ipdPatDocDeskFB.getSrchCriteria()!=null)
			{
				if(ipdPatDocDeskFB.getSrchCriteria().equals("2"))
					patientDetailVO.setPatAdmNo(ipdPatDocDeskFB.getSrchValue());
				if(ipdPatDocDeskFB.getSrchCriteria().equals("1"))
					patientDetailVO.setPatCrNo(ipdPatDocDeskFB.getSrchValue());
				if(ipdPatDocDeskFB.getSrchCriteria().equals("3"))
					patientDetailVO.setPatName(ipdPatDocDeskFB.getSrchValue());
			}
			deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			if(!(deskType.equals("4")))
			{
				if(ipdPatDocDeskFB.getServices().equals("1"))
				{
					DynamicDeskUTIL.setAttributeInSession(objRequest, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK);
					//deskType = DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK;
					deskType = DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK; //Modified by Vasu on 11.Sept.2018 to get IPD Nursing desk patient list i.e. DeskType-3
				}
				else if(ipdPatDocDeskFB.getServices().equals("2"))
				{
					DynamicDeskUTIL.setAttributeInSession(objRequest, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_IPD_NON_ACPT_DOCTOR_DESK);
					deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
				}
				else if(ipdPatDocDeskFB.getServices().equals("3"))
				{
					DynamicDeskUTIL.setAttributeInSession(objRequest, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_IPD_ON_LEAVE_DOCTOR_DESK);
					deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
				}
				else if(ipdPatDocDeskFB.getServices().equals("4"))
				{
					DynamicDeskUTIL.setAttributeInSession(objRequest, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_IPD_IN_TRANSIT_DOCTOR_DESK);
					deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
				}	
			}
			//System.out.println("Desk Type is :-"+deskType+"Desk Type in Session is :-"+(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			//userVO.setModuleId(PisConfig.MODULE_ID_PIS);	
			
			//System.out.println("Employee Number = "+objRequest.getParameter("selectedEmpNo"));
			//System.out.println("Employee Number Attribute= "+objRequest.getAttribute("selectedEmpNo"));
			
			//String _selecedEmpNo = objRequest.getParameter("selectedEmpNo");
			
			String _page = objRequest.getParameter("page"), _limit = objRequest.getParameter("rows"), 
				   _sidx = objRequest.getParameter("sidx"), _sord = objRequest.getParameter("sord"), _where = "";
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			int i_page=Integer.parseInt(_page),i_limit=Integer.parseInt(_limit);			
			Map<String, Object> mapObj=IpdPatDocDeskDATA.getTodayAllPatientList_AJAX(patientDetailVO, userVO,i_page,i_limit,_sidx,_sord,_where,deskType);
			String r_count =(String)mapObj.get("count"),r_total_page =(String)mapObj.get("total_pages"),r_page =(String)mapObj.get("page");
			List<List<String>> lstDtl= (List<List<String>>) mapObj.get("listObj");;
			
			if(lstDtl!=null && lstDtl.size()>0){
				//if(!$sidx) $sidx =1;
				 List<Map<String, Object>> ListObj2 = new ArrayList<Map<String, Object>>();
				 for (List<String> list2 : lstDtl) {Map<String, Object> map2 = new HashMap<String, Object>();
				 List<String> ListObj1 = new ArrayList<String>();for (String temp : list2) {ListObj1.add(temp);}
				 map2.put("id", list2.get(0));map2.put("cell", ListObj1);ListObj2.add(map2);}
				 map1.put("page", r_page);map1.put("total", r_total_page);map1.put("records", r_count);map1.put("rows", ListObj2);
			}else{System.out.println("List is Null or Empty.");}
			
			outputString=JSONValue.toJSONString(map1);
			System.out.println("outputString "+outputString);
		}		
		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, outputString);
		}	
	}

	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public static boolean getIpdDeskEssentials(IpdPatDocDeskFB fb,	HttpServletRequest request) 
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{			
			List list = IpdPatDocDeskDATA.getIpdDeskEssentials(getUserVO(request));
			DynamicDeskUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST ,list);
			List listWard=IpdPatDocDeskDATA.getWardOnBasisOfUnitCode("%",getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT, listWard);

			//WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST ,list);
		/*	if (list.size() == 1)
			{
				Entry entry = (Entry) list.get(0);
				String unitCode = entry.getValue();
				opdPatientDeskFB.setDepartmentUnitCode(unitCode);
				flag = false;
			}
			else
			{
				flag = true;
				opdPatientDeskFB.setShowRommList("0");
			}*/
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
		return flag;
	}

	public static StringBuffer getWardOnBasisOfUnitCode(IpdPatDocDeskFB fb,HttpServletRequest request) 
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		try
		{
			System.out.println(fb.getDepartmentUnitCode()+"  DeptUnitCode");
			String unitCodeWithDiagCodeType = fb.getDepartmentUnitCode();
			String unitCode = (unitCodeWithDiagCodeType.equals("0"))?"%":(unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^")));
			//String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
			System.out.println("DeptUnit:-"+unitCodeWithDiagCodeType+"  UnitCode:-"+unitCode);
			//fb.setWardCode("-1");
			//fb.setRoomCode("-1");
			
			listWard=IpdPatDocDeskDATA.getWardOnBasisOfUnitCode(unitCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT, listWard);
			System.out.println("listWard 0 :"+listWard.get(0));
			if(listWard!=null && listWard.size()>0)
			{
				sbAjaxRes.append("[");
				for(int i=0; i<listWard.size(); i++)
				{
					String ward=listWard.get(i).toString();
					String [] wardDetail= ward.split(",");
					String wardId= wardDetail[0].substring(1, wardDetail[0].length());
					String wardName= wardDetail[1].substring(0, (wardDetail[1].length()-1));
					System.out.println("Ward id :"+wardId+"  WardName :"+wardName);
					sbAjaxRes.append("{");
					sbAjaxRes.append("wardCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(wardId);sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("wardName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(wardName);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");					
				}
				if(listWard.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
				System.out.println("Result of Ward :- "+sbAjaxRes);
			}				
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return sbAjaxRes;
	}

	public static StringBuffer getRoomOnBasisOfWardCode(IpdPatDocDeskFB fb,HttpServletRequest request) 
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		boolean flag = true;
		List lstRoom = new ArrayList();
		String unitCode="0";
		try
		{
			if(fb.getDepartmentUnitCode()!=null && !(fb.getDepartmentUnitCode().equals("0"))){
				String unitCodeWithDiagCodeType = fb.getDepartmentUnitCode();
				 unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
			}
				
				// Here we are setting ward code in session , This is used by Investigation [PACKING KIST GENERATION] process, Done By Akash Singh, dated on 16-07-2015
				//WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_SELECTED_WARD_CODE, fb.getWardCode());
				
				
				//fb.setRoomCode("-1");
				lstRoom=IpdPatDocDeskDATA.getRoomOnBasisOfWardCode(unitCode,fb.getWardCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_ROOM_LIST_ON_BASIS_WARD, lstRoom);
			//System.out.println("lstRoom 0 :"+lstRoom.size());
			if(lstRoom!=null && lstRoom.size()>0)
			{
				sbAjaxRes.append("[");
				for(int i=0; i<lstRoom.size(); i++)
				{
					String room=lstRoom.get(i).toString();
					String [] roomDetail= room.split(",");
					String roomId= roomDetail[0].substring(1, roomDetail[0].length());
					String roomName= roomDetail[1].substring(0, (roomDetail[1].length()-1));
					System.out.println("Room id :"+roomId+"  RoomName :"+roomName);
					sbAjaxRes.append("{");
					sbAjaxRes.append("roomCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(roomId);sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("roomName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(roomName);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");					
				}
				if(lstRoom.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
				System.out.println("Result of Room :- "+sbAjaxRes);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return sbAjaxRes;
	}
	
	//Added by Vasu on 15.sept.2018
	
	public static boolean getIpdNursingDeskEssentials(IpdPatDocDeskFB fb,	HttpServletRequest request) 
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{			
			//List list = IpdPatDocDeskDATA.getIpdDeskEssentials(getUserVO(request));
			UserVO userVO = getUserVO(request);
			List list = IpdPatDocDeskDATA.getIpdNursingDeskEssentials(userVO);
			DynamicDeskUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST ,list);
			List listWard=IpdPatDocDeskDATA.getWardOnBasisOfUnitCodeForIPDNursing("%",userVO);
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT, listWard);
			//WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST ,list);
		/*	if (list.size() == 1)
			{
				Entry entry = (Entry) list.get(0);
				String unitCode = entry.getValue();
				opdPatientDeskFB.setDepartmentUnitCode(unitCode);
				flag = false;
			}
			else
			{
				flag = true;
				opdPatientDeskFB.setShowRommList("0");
			}*/
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
		return flag;
	}
	
//Added by Vasu on 15.Sept.2018
	public static StringBuffer getWardOnBasisOfUnitCodeForIPDNursing(IpdPatDocDeskFB fb,HttpServletRequest request) 
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		try
		{
			System.out.println(fb.getDepartmentUnitCode()+"  DeptUnitCode");
			String unitCodeWithDiagCodeType = fb.getDepartmentUnitCode();
			String unitCode = (unitCodeWithDiagCodeType.equals("0"))?"%":(unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^")));
			System.out.println("DeptUnit:-"+unitCodeWithDiagCodeType+"  UnitCode:-"+unitCode);
			//fb.setWardCode("-1");
			//fb.setRoomCode("-1");
			
			//listWard=IpdPatDocDeskDATA.getWardOnBasisOfUnitCode(unitCode,getUserVO(request));
			
				
			listWard=IpdPatDocDeskDATA.getWardOnBasisOfUnitCodeForIPDNursing(unitCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT, listWard);
			System.out.println("listWard 0 :"+listWard.get(0));
			if(listWard!=null && listWard.size()>0)
			{
				sbAjaxRes.append("[");
				for(int i=0; i<listWard.size(); i++)
				{
					String ward=listWard.get(i).toString();
					String [] wardDetail= ward.split(",");
					String wardId= wardDetail[0].substring(1, wardDetail[0].length());
					String wardName= wardDetail[1].substring(0, (wardDetail[1].length()-1));
					System.out.println("Ward id :"+wardId+"  WardName :"+wardName);
					sbAjaxRes.append("{");
					sbAjaxRes.append("wardCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(wardId);sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("wardName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(wardName);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");					
				}
				if(listWard.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
				System.out.println("Result of Ward :- "+sbAjaxRes);
			}				
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return sbAjaxRes;
	}

}//End of Class
