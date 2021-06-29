package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 07-May-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;
import registration.bo.RegEssentialBO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import registration.reports.controller.data.PatientListReportDATA;
import vo.registration.PoliceStationMstVO;

public class PoliceStationMstDATA 
{

	public static PoliceStationMstVO getEssentials(PoliceStationMstVO policeStationmodel)
	{
		
		try{			
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
		
			List stateList=getStateList(uservo);
			request.getSession().setAttribute("stateList",stateList);
			if(policeStationmodel==null)
			{
				policeStationmodel=new PoliceStationMstVO();			
				List districtList=getDistrictList(hospitalVO.getState(), uservo);
				request.getSession().setAttribute("districtList",districtList);	
				policeStationmodel.setStrStateCode(hospitalVO.getState());
				policeStationmodel.setStrDistrictCode(hospitalVO.getDistrictCode());	
				policeStationmodel.setStrIsDefault("1");
			}
			else
			{
				List districtList=getDistrictList(policeStationmodel.getStrStateCode(), uservo);
				request.getSession().setAttribute("districtList",districtList);	
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return policeStationmodel;
	}
	//To Save the Police Station Details 
	public static boolean savePoliceStationDtl(PoliceStationMstVO PoliceStationModel,String strMode_p) 
	{

		String strmsgText = "";
		PoliceStationMstVO objPoliceStationModel;
		boolean bExistStatus=false;

		try 
		{
			objPoliceStationModel= new PoliceStationMstVO();			
			objPoliceStationModel=PoliceStationModel;
			RegMasterBO bo = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			//objPoliceStationModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.savePoliceStationDtl(objPoliceStationModel,strMode_p,uservo);

			if (objPoliceStationModel.getStrMsgType()!=null && objPoliceStationModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objPoliceStationModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objPoliceStationModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objPoliceStationModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "Police Station Mst.savePoliceStationDtl(vo) --> "
					+ e.getMessage();
			PoliceStationModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} finally 
		{
			objPoliceStationModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static PoliceStationMstVO modifyRecord( HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		PoliceStationMstVO PoliceStationModel =new PoliceStationMstVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try 
		{

			RegMasterBO bo = new RegMasterBO();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			PoliceStationModel.setStrPSCode(strTemp2[0]);
			PoliceStationModel.setStrHospitalCode(strTemp2[1]);
			PoliceStationMstVO PoliceStationMstVO_p=bo.modifyRecordPoliceStationMst(PoliceStationModel);

			if (PoliceStationModel.getStrMsgType().equals("1"))
			{
				throw new Exception(PoliceStationModel.getStrMsgString());
			}

			PoliceStationModel.setStrPSCode(PoliceStationMstVO_p.getStrPSCode());
			PoliceStationModel.setStrPSShortName(PoliceStationMstVO_p.getStrPSShortName());	
			PoliceStationModel.setStrPSFullName(PoliceStationMstVO_p.getStrPSFullName());			
			PoliceStationModel.setStrAddressLine1(PoliceStationMstVO_p.getStrAddressLine1());			
			PoliceStationModel.setStrSubLocality1(PoliceStationMstVO_p.getStrSubLocality1());		
			PoliceStationModel.setStrSubLocality2(PoliceStationMstVO_p.getStrSubLocality2());		
			PoliceStationModel.setStrCity(PoliceStationMstVO_p.getStrCity());		
			PoliceStationModel.setStrDistrict(PoliceStationMstVO_p.getStrDistrict());	
			PoliceStationModel.setStrDistrictCode(PoliceStationMstVO_p.getStrDistrictCode());		
			PoliceStationModel.setStrStateCode(PoliceStationMstVO_p.getStrStateCode());		
			PoliceStationModel.setStrPinCode(PoliceStationMstVO_p.getStrPinCode());		
			PoliceStationModel.setStrPhoneNo(PoliceStationMstVO_p.getStrPhoneNo());		
			PoliceStationModel.setStrEmailId(PoliceStationMstVO_p.getStrEmailId());		
			PoliceStationModel.setStrPSInchargeName(PoliceStationMstVO_p.getStrPSInchargeName());		
			PoliceStationModel.setStrPSConstableDesignation(PoliceStationMstVO_p.getStrPSConstableDesignation());		
			PoliceStationModel.setStrPSConstableBadgeNo(PoliceStationMstVO_p.getStrPSConstableBadgeNo());		
			PoliceStationModel.setStrMobileNo(PoliceStationMstVO_p.getStrMobileNo());		
			PoliceStationModel.setStrIsDefault(PoliceStationMstVO_p.getStrIsDefault());		

			PoliceStationModel.setStrIsValid(PoliceStationMstVO_p.getStrIsValid());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_EMG_POLICE_STATION_MASTER;
			mp.put("save_1", PoliceStationModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);


		} 
		catch (Exception e) 
		{
			strMsgText = "Police StationMstData.modifyRecord(fb,request) --> "
					+ e.getMessage();
			PoliceStationModel.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return PoliceStationModel;

	}

	//To update the PoliceStation Details
	public static boolean updatePoliceStationDtl(PoliceStationMstVO PoliceStationModel,String strMode_p)
	{

		String strmsgText = "";
		PoliceStationMstVO objPoliceStationModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try
		{
			objPoliceStationModel= new PoliceStationMstVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			objPoliceStationModel=PoliceStationModel;
			objPoliceStationModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updatePoliceStationDtl(objPoliceStationModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objPoliceStationModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objPoliceStationModel.getStrPSCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objPoliceStationModel.getStrMsgType()!=null && objPoliceStationModel.getStrMsgType().equals("1")) {
				throw new Exception(objPoliceStationModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objPoliceStationModel.setStrWarning("Data already exist");
			} else {
				objPoliceStationModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "PoliceStationMst.updatePoliceStationDtl(vo) --> "
					+ e.getMessage();
			PoliceStationModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objPoliceStationModel = null;
		}
		return bExistStatus;
	}
	
	public static void getDistrict_AJAX(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		String outputString="";
		try{
			String strStateCode= (String)objRequest.getParameter("stateCode");
			UserVO userVO=ControllerUTIL.getUserVO(objRequest);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			List districtList=getDistrictList(strStateCode,userVO);			
			createOptionObject((List<Entry>)districtList,responseDocument,"policeStationModel.strDistrictCode");						
			//System.out.println("outputString "+outputString);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			try {
				trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputString=baos.toString();
			writeResponse(objResponse, outputString);
		}	
		
	}
	
	public static List getDistrictList(String strStateCode,UserVO uservo){
		String strCountryCode="IND";
		RegEssentialBO bo=new RegEssentialBO();
		return bo.getDistrictBasedOnState_AJAX(uservo, strStateCode, strCountryCode);	
	}
	
	public static List getStateList(UserVO uservo){
		String strCountryCode="IND";
		RegEssentialBO bo=new RegEssentialBO();
		return bo.getStateBasedOnCountry_AJAX(uservo, strCountryCode);
	}
	
	private static void createOptionObject(List<Entry> list,Document responseDocument, String fieldName) {
		 
		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			
			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}
	
	public static void writeResponse(HttpServletResponse resp, String output){
		
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			//System.out.println(output);
			resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
