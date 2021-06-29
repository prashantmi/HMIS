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
import java.util.List;
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
import vo.registration.EmgCaseVO;

public class EmgCaseMstDATA 
{

	//To Save the Emg Case  Details 
	public static boolean saveEmgCaseDtl(EmgCaseVO emgCaseModel,String strMode_p) 
	{

		String strmsgText = "";
		EmgCaseVO objemgCaseModel;
		boolean bExistStatus=false;

		try 
		{
			objemgCaseModel= new EmgCaseVO();			
			objemgCaseModel=emgCaseModel;
			RegMasterBO bo = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			//objemgCaseModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.saveEmgCaseDtl(objemgCaseModel,strMode_p,uservo);

			if (objemgCaseModel.getStrMsgType()!=null && objemgCaseModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objemgCaseModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objemgCaseModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objemgCaseModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "Emg Case  Mst.saveEmgCaseDtl(vo) --> "
					+ e.getMessage();
			emgCaseModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} finally 
		{
			objemgCaseModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static EmgCaseVO modifyRecord( HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		EmgCaseVO emgCaseModel =new EmgCaseVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try 
		{

			RegMasterBO bo = new RegMasterBO();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			emgCaseModel.setStrEmgCaseCode(strTemp2[0]);
			//emgCaseModel.setStrHospitalCode(strTemp2[1]);
			EmgCaseVO EmgCaseVO_p=bo.modifyRecordEmgCaseMst(emgCaseModel);

			if (emgCaseModel.getStrMsgType().equals("1"))
			{
				throw new Exception(emgCaseModel.getStrMsgString());
			}

			emgCaseModel.setStrEmgCaseCode(EmgCaseVO_p.getStrEmgCaseCode());
			emgCaseModel.setStrEmgCaseDesc(EmgCaseVO_p.getStrEmgCaseDesc());			
			emgCaseModel.setCaseType(EmgCaseVO_p.getCaseType());
			emgCaseModel.setStrIsValid(EmgCaseVO_p.getStrIsValid());
			
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_EMG_MLC_CASE_TYPE_MASTER;
			mp.put("save_1", emgCaseModel);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);


		} 
		catch (Exception e) 
		{
			strMsgText = "ShiftMstData.modifyRecord(fb,request) --> "
					+ e.getMessage();
			emgCaseModel.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return emgCaseModel;

	}

	//To update the EmgCase Details
	public static boolean updateEmgCaseDtl(EmgCaseVO emgCaseModel,String strMode_p)
	{

		String strmsgText = "";
		EmgCaseVO objemgCaseModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try
		{
			objemgCaseModel= new EmgCaseVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			objemgCaseModel=emgCaseModel;
			objemgCaseModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateEmgCaseDtl(objemgCaseModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objemgCaseModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objemgCaseModel.getStrEmgCaseCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objemgCaseModel.getStrMsgType()!=null && objemgCaseModel.getStrMsgType().equals("1")) {
				throw new Exception(objemgCaseModel.getStrMsgString());
			}

			if (bExistStatus == false) {//
				objemgCaseModel.setStrWarning("Data already exist");
			} else {
				objemgCaseModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "EmgMlcCaseTypeMst.updateEmgCaseDtl(vo) --> "
					+ e.getMessage();
			emgCaseModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objemgCaseModel = null;
		}
		return bExistStatus;
	}

}
