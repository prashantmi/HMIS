package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 05-May-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */


import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.EmgPatStatusVO;

public class EmgPatStatusMstDATA 
{

	//To Save the Patient Status Details 
	public static boolean saveEmgPatStatusDtl(EmgPatStatusVO emgPatStatusModel,String strMode_p) 
	{

		String strmsgText = "";
		EmgPatStatusVO objEmgPatStatusModel;
		boolean bExistStatus=false;

		try 
		{
			objEmgPatStatusModel= new EmgPatStatusVO();			
			objEmgPatStatusModel=emgPatStatusModel;
			RegMasterBO bo = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			//objEmgPatStatusModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.saveEmgPatStatusDtl(objEmgPatStatusModel,strMode_p,uservo);

			if (objEmgPatStatusModel.getStrMsgType()!=null && objEmgPatStatusModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objEmgPatStatusModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objEmgPatStatusModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objEmgPatStatusModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "Patient Status Mst.saveEmgPatStatusDtl(vo) --> "
					+ e.getMessage();
			emgPatStatusModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} finally 
		{
			objEmgPatStatusModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static EmgPatStatusVO modifyRecord( HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		EmgPatStatusVO emgPatStatusModel =new EmgPatStatusVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try 
		{

			RegMasterBO bo = new RegMasterBO();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			emgPatStatusModel.setStrEmgPatStatusCode(strTemp2[0]);
			//emgPatStatusModel.setStrHospitalCode(strTemp2[1]);
			EmgPatStatusVO EmgPatStatusVO_p=bo.modifyRecordEmgPatStatusMst(emgPatStatusModel);

			if (emgPatStatusModel.getStrMsgType().equals("1"))
			{
				throw new Exception(emgPatStatusModel.getStrMsgString());
			}

			emgPatStatusModel.setStrEmgPatStatusCode(EmgPatStatusVO_p.getStrEmgPatStatusCode());
			emgPatStatusModel.setStrEmgPatStatusDesc(EmgPatStatusVO_p.getStrEmgPatStatusDesc());			
			emgPatStatusModel.setStrIsValid(EmgPatStatusVO_p.getStrIsValid());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_EMG_PATIENT_STATUS_MASTER;
			mp.put("save_1", emgPatStatusModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);


		} 
		catch (Exception e) 
		{
			strMsgText = "ShiftMstData.modifyRecord(fb,request) --> "
					+ e.getMessage();
			emgPatStatusModel.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return emgPatStatusModel;

	}

	//To update the EmgPatStatus Details
	public static boolean updateEmgPatStatusDtl(EmgPatStatusVO emgPatStatusModel,String strMode_p)
	{

		String strmsgText = "";
		EmgPatStatusVO objEmgPatStatusModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try
		{
			objEmgPatStatusModel= new EmgPatStatusVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			objEmgPatStatusModel=emgPatStatusModel;
			objEmgPatStatusModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateEmgPatStatusDtl(objEmgPatStatusModel,strMode_p,uservo);
			
			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objEmgPatStatusModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objEmgPatStatusModel.getStrEmgPatStatusCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}

			if (objEmgPatStatusModel.getStrMsgType()!=null && objEmgPatStatusModel.getStrMsgType().equals("1")) {
				throw new Exception(objEmgPatStatusModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objEmgPatStatusModel.setStrWarning("Data already exist");
			} else {
				objEmgPatStatusModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "EmgPatStatusMst.updateEmgPatStatusDtl(vo) --> "
					+ e.getMessage();
			emgPatStatusModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objEmgPatStatusModel = null;
		}
		return bExistStatus;
	}
}
