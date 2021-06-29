package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 06-May-2014
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
import vo.registration.EmgDeathMstVO;

public class EmgDeathMstDATA 
{

	//To Save the Death Details 
	public static boolean saveEmgDeathDtl(EmgDeathMstVO emgDeathModel,String strMode_p) 
	{

		String strmsgText = "";
		EmgDeathMstVO objEmgDeathModel;
		boolean bExistStatus=false;

		try 
		{
			objEmgDeathModel= new EmgDeathMstVO();			
			objEmgDeathModel=emgDeathModel;
			RegMasterBO bo = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			//objEmgDeathModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.saveEmgDeathDtl(objEmgDeathModel,strMode_p,uservo);

			if (objEmgDeathModel.getStrMsgType()!=null && objEmgDeathModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objEmgDeathModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objEmgDeathModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objEmgDeathModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "Death Mst.saveEmgDeathDtl(vo) --> "
					+ e.getMessage();
			emgDeathModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} finally 
		{
			objEmgDeathModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static EmgDeathMstVO modifyRecord( HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		EmgDeathMstVO emgDeathModel =new EmgDeathMstVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try 
		{

			RegMasterBO bo = new RegMasterBO();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			emgDeathModel.setStrEmgDeathMannerCode(strTemp2[0]);
			//emgDeathModel.setStrHospitalCode(strTemp2[1]);
			EmgDeathMstVO EmgDeathMstVO_p=bo.modifyRecordEmgDeathMst(emgDeathModel);

			if (emgDeathModel.getStrMsgType().equals("1"))
			{
				throw new Exception(emgDeathModel.getStrMsgString());
			}

			emgDeathModel.setStrEmgDeathMannerCode(EmgDeathMstVO_p.getStrEmgDeathMannerCode());
			emgDeathModel.setStrEmgDeathManner(EmgDeathMstVO_p.getStrEmgDeathManner());	
			emgDeathModel.setStrEmgDeathMannerDesc(EmgDeathMstVO_p.getStrEmgDeathMannerDesc());			
			emgDeathModel.setStrIsPostMortem(EmgDeathMstVO_p.getStrIsPostMortem());			
			emgDeathModel.setStrIsAccidental(EmgDeathMstVO_p.getStrIsAccidental());		

			emgDeathModel.setStrIsValid(EmgDeathMstVO_p.getStrIsValid());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_EMG_DEATH_MASTER;
			mp.put("save_1", emgDeathModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);


		} 
		catch (Exception e) 
		{
			strMsgText = "DeathMstData.modifyRecord(fb,request) --> "
					+ e.getMessage();
			emgDeathModel.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return emgDeathModel;

	}

	//To update the EmgDeath Details
	public static boolean updateEmgDeathDtl(EmgDeathMstVO emgDeathModel,String strMode_p)
	{

		String strmsgText = "";
		EmgDeathMstVO objEmgDeathModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try
		{
			objEmgDeathModel= new EmgDeathMstVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			objEmgDeathModel=emgDeathModel;
			objEmgDeathModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateEmgDeathDtl(objEmgDeathModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objEmgDeathModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objEmgDeathModel.getStrEmgDeathMannerCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objEmgDeathModel.getStrMsgType()!=null && objEmgDeathModel.getStrMsgType().equals("1")) {
				throw new Exception(objEmgDeathModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objEmgDeathModel.setStrWarning("Data already exist");
			} else {
				objEmgDeathModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "EmgDeathMst.updateEmgDeathDtl(vo) --> "
					+ e.getMessage();
			emgDeathModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objEmgDeathModel = null;
		}
		return bExistStatus;
	}
}
