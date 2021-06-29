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
import vo.registration.EmgMlcCaseTypeVO;

public class EmgMlcCaseTypeMstDATA 
{

	//To Save the Mlc Case Type Details 
	public static boolean saveEmgMlcCaseTypeDtl(EmgMlcCaseTypeVO emgMlcCaseTypeModel,String strMode_p) 
	{

		String strmsgText = "";
		EmgMlcCaseTypeVO objEmgMlcCaseTypeModel;
		boolean bExistStatus=false;

		try 
		{
			objEmgMlcCaseTypeModel= new EmgMlcCaseTypeVO();			
			objEmgMlcCaseTypeModel=emgMlcCaseTypeModel;
			RegMasterBO bo = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			//objEmgMlcCaseTypeModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.saveEmgMlcCaseTypeDtl(objEmgMlcCaseTypeModel,strMode_p,uservo);

			if (objEmgMlcCaseTypeModel.getStrMsgType()!=null && objEmgMlcCaseTypeModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objEmgMlcCaseTypeModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objEmgMlcCaseTypeModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objEmgMlcCaseTypeModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "Mlc Case Type Mst.saveEmgMlcCaseTypeDtl(vo) --> "
					+ e.getMessage();
			emgMlcCaseTypeModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} finally 
		{
			objEmgMlcCaseTypeModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static EmgMlcCaseTypeVO modifyRecord( HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		EmgMlcCaseTypeVO emgMlcCaseTypeModel =new EmgMlcCaseTypeVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try 
		{

			RegMasterBO bo = new RegMasterBO();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			emgMlcCaseTypeModel.setStrEmgMlcCaseTypeCode(strTemp2[0]);
			//emgMlcCaseTypeModel.setStrHospitalCode(strTemp2[1]);
			EmgMlcCaseTypeVO EmgMlcCaseTypeVO_p=bo.modifyRecordEmgMlcCaseTypeMst(emgMlcCaseTypeModel);

			if (emgMlcCaseTypeModel.getStrMsgType().equals("1"))
			{
				throw new Exception(emgMlcCaseTypeModel.getStrMsgString());
			}

			emgMlcCaseTypeModel.setStrEmgMlcCaseTypeCode(EmgMlcCaseTypeVO_p.getStrEmgMlcCaseTypeCode());
			emgMlcCaseTypeModel.setStrEmgMlcCaseTypeDesc(EmgMlcCaseTypeVO_p.getStrEmgMlcCaseTypeDesc());			
			emgMlcCaseTypeModel.setStrIsValid(EmgMlcCaseTypeVO_p.getStrIsValid());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_EMG_MLC_CASE_TYPE_MASTER;
			mp.put("save_1", emgMlcCaseTypeModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);


		} 
		catch (Exception e) 
		{
			strMsgText = "ShiftMstData.modifyRecord(fb,request) --> "
					+ e.getMessage();
			emgMlcCaseTypeModel.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return emgMlcCaseTypeModel;

	}

	//To update the EmgMlcCaseType Details
	public static boolean updateEmgMlcCaseTypeDtl(EmgMlcCaseTypeVO emgMlcCaseTypeModel,String strMode_p)
	{

		String strmsgText = "";
		EmgMlcCaseTypeVO objEmgMlcCaseTypeModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try
		{
			objEmgMlcCaseTypeModel= new EmgMlcCaseTypeVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			objEmgMlcCaseTypeModel=emgMlcCaseTypeModel;
			objEmgMlcCaseTypeModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateEmgMlcCaseTypeDtl(objEmgMlcCaseTypeModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objEmgMlcCaseTypeModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objEmgMlcCaseTypeModel.getStrEmgMlcCaseTypeCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objEmgMlcCaseTypeModel.getStrMsgType()!=null && objEmgMlcCaseTypeModel.getStrMsgType().equals("1")) {
				throw new Exception(objEmgMlcCaseTypeModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objEmgMlcCaseTypeModel.setStrWarning("Data already exist");
			} else {
				objEmgMlcCaseTypeModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "EmgMlcCaseTypeMst.updateEmgMlcCaseTypeDtl(vo) --> "
					+ e.getMessage();
			emgMlcCaseTypeModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objEmgMlcCaseTypeModel = null;
		}
		return bExistStatus;
	}
}
