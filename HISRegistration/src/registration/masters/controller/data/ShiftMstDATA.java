package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 21-Jan-2014
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
import vo.registration.ShiftVO;

public class ShiftMstDATA 
{

	//To Save the Shift Details 
	public static boolean saveShiftDtl(ShiftVO shiftModel,	String strMode_p) 
	{

		String strmsgText = "";
		ShiftVO objshiftModel;
		boolean bExistStatus=false;

		try 
		{
			objshiftModel= new ShiftVO();			
			objshiftModel=shiftModel;
			RegMasterBO bo = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			objshiftModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.saveShiftDtl(objshiftModel,strMode_p,uservo);

			if (objshiftModel.getStrMsgType()!=null && objshiftModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objshiftModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objshiftModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objshiftModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "ShiftMst.saveShiftDtl(vo) --> "
					+ e.getMessage();
			shiftModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} finally 
		{
			objshiftModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static ShiftVO modifyRecord( HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		ShiftVO shiftModel =new ShiftVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try 
		{

			RegMasterBO bo = new RegMasterBO();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			shiftModel.setStrShiftCode(strTemp2[0]);
			shiftModel.setStrHospitalCode(strTemp2[1]);
			ShiftVO ShiftVO_p=bo.modifyRecordShiftMst(shiftModel);

			if (shiftModel.getStrMsgType().equals("1"))
			{
				throw new Exception(shiftModel.getStrMsgString());
			}

			shiftModel.setStrShiftCode(ShiftVO_p.getStrShiftCode());
			shiftModel.setStrShiftDesc(ShiftVO_p.getStrShiftDesc());			
			shiftModel.setStrShiftType(ShiftVO_p.getStrShiftType());
			shiftModel.setStrShiftStartTime(ShiftVO_p.getStrShiftStartTime());
			shiftModel.setStrShiftEndTime(ShiftVO_p.getStrShiftEndTime());	
			shiftModel.setStrIsValid(ShiftVO_p.getStrIsValid());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_SHIFT_MASTER;
			mp.put("save_1", shiftModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);
		} 
		catch (Exception e) 
		{
			strMsgText = "ShiftMstData.modifyRecord(fb,request) --> "
					+ e.getMessage();
			shiftModel.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return shiftModel;

	}

	//To update the Shift Details
	public static boolean updateShiftDtl(ShiftVO shiftModel,String strMode_p)
	{

		String strmsgText = "";
		ShiftVO objShiftModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try
		{
			objShiftModel= new ShiftVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			objShiftModel=shiftModel;
			objShiftModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateShiftDtl(objShiftModel,strMode_p,uservo);
			
			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objShiftModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objShiftModel.getStrShiftCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}


			if (objShiftModel.getStrMsgType()!=null && objShiftModel.getStrMsgType().equals("1")) {
				throw new Exception(objShiftModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objShiftModel.setStrWarning("Data already exist");
			} else {
				objShiftModel.setStrMsg("Data Saved Successfully");
			}
			

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "ShiftMst.updateShiftDtl(vo) --> "
					+ e.getMessage();
			shiftModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objShiftModel = null;
		}
		return bExistStatus;
	}
}
