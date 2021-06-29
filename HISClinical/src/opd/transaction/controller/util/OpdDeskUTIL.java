package opd.transaction.controller.util;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.UserDeskMenuMasterVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.transaction.controller.data.OpdDeskDATA;
import opd.transaction.controller.fb.DoctorDeskFB;
import hisglobal.presentation.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.json.simple.JSONValue;

import hisglobal.vo.UserVO;

import org.apache.struts.action.ActionMapping;

public class OpdDeskUTIL extends ControllerUTIL {

	public static StringBuffer getPatientsCount(DoctorDeskFB objFB, HttpServletRequest request, HttpServletResponse response) 
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		System.out.println("BackgroundQuery.doGet()");
		OpdDeskDATA opdDeskDATA = new OpdDeskDATA();
		HttpSession session = request.getSession();
		UserDeskMenuMasterVO userDeskVO = new UserDeskMenuMasterVO();	
		try
		{
			/*IMP Note Take UNITCODE & ROOMCODE from session*****************&  change madi  &******************/
			String unitCode = (String) request.getParameter("UNITCODE");
			String roomCode = (String) request.getParameter("ROOMCODE");
			userDeskVO.setDeskType(objFB.getDeskType());// objFB.getDeskType());
			sbAjaxRes = opdDeskDATA.getPatientCount(userDeskVO, ControllerUTIL.getUserVO(request));
			//System.out.println("JSON :-"+patientCountObj);
			//sbAjaxRes.append(patientCountObj);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sbAjaxRes;
	}
	
	public static boolean getSetDoctorDashboardEssentials(DoctorDeskFB objFB, HttpServletRequest request, HttpServletResponse response) 
	{
		boolean flg = true;
		StringBuffer sbAjaxRes = new StringBuffer();
		System.out.println("BackgroundQuery.doGet()");
		OpdDeskDATA opdDeskDATA = new OpdDeskDATA();
		HttpSession session = request.getSession();
		UserDeskMenuMasterVO userDeskVO = new UserDeskMenuMasterVO();	
		try
		{
			/*IMP Note Take UNITCODE & ROOMCODE from session*****************&  change madi  &******************/
			String unitCode = (String) request.getParameter("UNITCODE");
			String roomCode = (String) request.getParameter("ROOMCODE");
			userDeskVO.setDeskType(objFB.getDeskType());	
			sbAjaxRes = opdDeskDATA.getPatientCount(userDeskVO, ControllerUTIL.getUserVO(request));
			//System.out.println("JSON :-"+patientCountObj);
			//sbAjaxRes.append(patientCountObj);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return flg;
	}

	public static void AJX_G_PATIENTS_COUNT_NEW(DoctorDeskFB opdPatientDeskFB,	HttpServletRequest objRequest, HttpServletResponse objResponse,ActionMapping objMapping_p) 
	{		
		Status objStatus=new Status();
		String outputString="";
		Map<String, Object> map1 = new HashMap<String, Object>();
		  		
		try
		{
			
			UserVO userVO=getUserVO(objRequest);
			//userVO.setModuleId(PisConfig.MODULE_ID_PIS);	
			
			//System.out.println("Employee Number = "+objRequest.getParameter("selectedEmpNo"));
			//System.out.println("Employee Number Attribute= "+objRequest.getAttribute("selectedEmpNo"));
			
			//String _selecedEmpNo = objRequest.getParameter("selectedEmpNo");
			
			String _page = objRequest.getParameter("page"), _limit = objRequest.getParameter("rows"), 
				   _sidx = objRequest.getParameter("sidx"), _sord = objRequest.getParameter("sord"), _where = "";
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			int i_page=Integer.parseInt(_page),i_limit=Integer.parseInt(_limit);			
			Map<String, Object> mapObj=OpdDeskDATA.AJX_G_PATIENTS_COUNT_NEW(userVO,i_page,i_limit,_sidx,_sord,_where);
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
	
}
