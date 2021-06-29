/* Module Name: Common List Page
*  Name of Process: List Generation 
*  Name of Developer: Sh. Ashwini Mishra
*  Date of Creation: 26-Mar-2015
*/
package hr.pis.common.transactions.controller.util;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;
import hr.pis.common.transactions.controller.actionsupport.EmployeeInfoSUP;
import hr.pis.common.transactions.controller.data.EmployeeInfoDATA;
import hr.pis.config.PisConfig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import vo.hr.pis.common.transactions.EmployeeInfoVO;



public class EmployeeInfoUTIL extends ControllerUTIL {


	public static void getEmpList_AJAX(EmployeeInfoSUP objSup, HttpServletRequest objRequest, HttpServletResponse objResponse, Map<Object, Object> mapSession)
	{		
		Status objStatus=new Status();
		String outputString="";
		//String year="";
		Map<String, Object> map1 = new HashMap<String, Object>();
		  		
		try
		{
			UserVO userVO=getUserVO(objRequest);
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);	
			
			//year=objRequest.getParameter("year");
		//	System.out.println("Year   ::::"+year);
			
			System.out.println("Filters = "+objRequest.getParameter("filters"));
			System.out.println("Search = "+objRequest.getParameter("_search"));
			
			String _page = objRequest.getParameter("page"), _limit = objRequest.getParameter("rows"), 
				   _sidx = objRequest.getParameter("sidx"), _sord = objRequest.getParameter("sord"), _where = "";
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			
			System.out.println("Where Condition = "+_where);
			// Setting Filter Conditino in session for further use
			objRequest.getSession().setAttribute("filterCondition", _where);
			
			int i_page=Integer.parseInt(_page),i_limit=Integer.parseInt(_limit);
			
			EmployeeInfoVO voObj = new EmployeeInfoVO();	
			HelperMethods.populate(voObj, objSup);
			
			Map<String, Object> mapObj=EmployeeInfoDATA.getEmpList_AJAX(userVO, voObj,  i_page, i_limit, _sidx, _sord, _where);
			
			String r_count =(String)mapObj.get("count"),r_total_page =(String)mapObj.get("total_pages"),r_page =(String)mapObj.get("page");
			List<List<String>> lstDtl= (List<List<String>>) mapObj.get("listObj");;
			
			if(lstDtl!=null && lstDtl.size()>0)
			{
				 List<Map<String, Object>> ListObj2 = new ArrayList<Map<String, Object>>();
				 for (List<String> list2 : lstDtl) {Map<String, Object> map2 = new HashMap<String, Object>();
				 List<String> ListObj1 = new ArrayList<String>();for (String temp : list2) {ListObj1.add(temp);}
				 map2.put("id", list2.get(0));map2.put("cell", ListObj1);ListObj2.add(map2);}
				 map1.put("page", r_page);map1.put("total", r_total_page);map1.put("records", r_count);map1.put("rows", ListObj2);
			}
			else
			{
				System.out.println("List is Null or Empty.");
			}
			
			outputString=JSONValue.toJSONString(map1);
			System.out.println("outputString "+outputString);
		}		
		catch(Exception e)
		{
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, outputString);
		}	
	}	

	
	

	 

	
	
	public static void getEmpData(EmployeeInfoSUP objSup, HttpServletRequest objRequest, HttpServletResponse objResponse) 
	{
		
		System.out.println("EmployeeInfoUTIL :: getEmpData");
		List lstRecordJsonObjString = new ArrayList();
		List lstRecordJsonObjStringWithReportMsgFlag = new ArrayList();
		StringBuilder strResponse = new StringBuilder();
		try{
		
			    //String patPrimaryCatCode= (String)objRequest.getParameter("patPrimaryCatCode");
				//String searchEmpNo	=	(String)objRequest.getParameter("searchEmpNo");
				UserVO userVO=getUserVO(objRequest);
				
				EmployeeInfoVO voObj = new EmployeeInfoVO();	
				HelperMethods.populate(voObj, objSup);
				
				lstRecordJsonObjStringWithReportMsgFlag=EmployeeInfoDATA.getEmpData(userVO, voObj);
				
				String reportMsgFlag=(String)lstRecordJsonObjStringWithReportMsgFlag.get(0);
				
				lstRecordJsonObjString.add(lstRecordJsonObjStringWithReportMsgFlag.get(1));
				
				System.out.println("lstRecordJsonObjStringWithReportMsgFlag.size()"+lstRecordJsonObjStringWithReportMsgFlag.size());
				System.out.println("lstRecordJsonObjStringWithReportMsgFlag.size()"+lstRecordJsonObjStringWithReportMsgFlag.get(0));
				System.out.println("lstRecordJsonObjStringWithReportMsgFlag.size()"+lstRecordJsonObjString);
				
				
				
				setSysdate(objRequest);
				
				Date dateObj = null;
				if((objRequest.getSession().getAttribute(Config.SYSDATEOBJECT))!=null)
					dateObj = (Date)objRequest.getSession().getAttribute(Config.SYSDATEOBJECT);
				else
					dateObj=null;
				
				SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
				sf.applyPattern("dd-MMM-yyyy"); String date = sf.format(dateObj);
				sf.applyPattern("HH:mm:ss"); String time = sf.format(dateObj);
				//System.out.println("date:"+date); System.out.println("time:"+time);
				strResponse.append("{\"Date\":\""+date+"\",\"Time\":\""+time+"\",\"RptMsgFlag\":\""+reportMsgFlag+"\",\"Data\":");
				
				//strResponse.append("[");
				//strResponse.append("{");
				if(lstRecordJsonObjString!=null && lstRecordJsonObjString.size()>0){
					for(int i=0; i<lstRecordJsonObjString.size(); i++){
						if(i==0)
							strResponse.append(lstRecordJsonObjString.get(i));
						else
							strResponse.append(","+lstRecordJsonObjString.get(i));
					}
				}

				//strResponse.append("]");
				strResponse.append("}");
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, strResponse.toString());
		}	
		
	}
	
	
	public static void findEmp(EmployeeInfoSUP objSup,HttpServletRequest objRequest,HttpServletResponse objResponse)
	{		
		System.out.println("EmployeeInfoUTIL :: findEmp()");
			
			
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			TransformerFactory trf= TransformerFactory.newInstance();
			Document responseDocument=null;
			
			UserVO userVO=getUserVO(objRequest);
			
			userVO.setModuleId(PisConfig.MODULE_ID_PIS);
			Status objStatus =new Status();	
			
			try
			{
				responseDocument=dbf.newDocumentBuilder().newDocument();
				Element rootElement=responseDocument.createElement("root");
				responseDocument.appendChild(rootElement);				
				
				setSysdate(objRequest);				
				String _searchStr = objRequest.getParameter("searchStr");				
				
				EmployeeInfoVO voObj = new EmployeeInfoVO();	
				HelperMethods.populate(voObj, objSup);
				
				List<List<String>> alRecord = EmployeeInfoDATA.findEmp(userVO, voObj, _searchStr);
				
				JSONArray arrayObj=new JSONArray();
				if(alRecord!=null)
				{
					for(int i=0; i<alRecord.size(); i++) {
				    	System.out.println("alRecord:"+alRecord.get(i).get(0).toString());				      
				         arrayObj.add(alRecord.get(i).get(0).toString());				      
				    }				   
				}	
				
				System.out.println(arrayObj.toString());
				writeResponse(objResponse, arrayObj.toString());
						        
			}		
			catch(Exception e){
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			
		}
	
	
	/* 

private static boolean createSaveMsgObject(Document responseDocument, String strIsSavedSuccussfulFlag,String strSavedMsg, String strIsFormFieldRestFlag, String strPrintDivContent, String strPrintFlag) 
{
	
	Element elementSaveMsg =responseDocument.createElement("savedMsgDtl");
	elementSaveMsg.setAttribute("isSavedSuccussful", strIsSavedSuccussfulFlag);
	elementSaveMsg.setAttribute("savedMessage", strSavedMsg);
	elementSaveMsg.setAttribute("isFormFieldTobeReset", strIsFormFieldRestFlag);
	elementSaveMsg.setAttribute("isPrintFlag", strPrintFlag);
	elementSaveMsg.setAttribute("printDivContent", strPrintDivContent);
			
	responseDocument.getFirstChild().appendChild(elementSaveMsg);
	
	return true;
}


*/

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
