package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 26-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;
import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.DepartmentVO;
import vo.registration.UOMVO;


public class UOMMstDATA 
{

	public static boolean saveUOMDtl(UOMVO UomModel,String strMode_p,HttpServletRequest request) 
	{

		UOMVO objUomModel;
		boolean bExistStatus=false;

		try {
			objUomModel= new UOMVO();			
			objUomModel=UomModel;

			UserVO uservo = ControllerUTIL.getUserVO(request);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.saveUOMDtl(objUomModel,strMode_p,uservo);

			if (objUomModel.getStrMsgType()!=null && objUomModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objUomModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objUomModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objUomModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "UomMstDATA.saveUomDtl --> "+ e.getMessage();
			UomModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			objUomModel = null;
		}
		return bExistStatus;
	}
	
	public static boolean updateUomDtl(UOMVO UomModel,String strMode_p,HttpServletRequest request) 
	{

		UOMVO objUomModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try {
			objUomModel= new UOMVO();			
			objUomModel=UomModel;

			UserVO uservo = ControllerUTIL.getUserVO(request);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.updateUOMDtl(objUomModel,strMode_p,uservo);
			
			//Audit Log on update
			if(bExistStatus&&objUomModel.getStrUOMId()!=null)
			{
				mp.put("save_1"  , objUomModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objUomModel.getStrUOMId();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}

			if (objUomModel.getStrMsgType()!=null && objUomModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objUomModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objUomModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objUomModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "UomMstDATA.modifyUomDtl --> "+ e.getMessage();
			UomModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			objUomModel = null;
		}
		return bExistStatus;
	}
	
	//To get the Data for Modify Page
	public static UOMVO modifyRecord( HttpServletRequest request) 
	{
		UOMMstDATA UomMstDATA_obj = null;
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		UOMVO UomModel =new UOMVO();
		DepartmentVO deptModel=new DepartmentVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try {

			UomMstDATA_obj = new UOMMstDATA();
			RegMasterBO bo = new RegMasterBO();

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			UomModel.setStrUOMId(strTemp2[0]);
			UOMVO UOMVO_p=bo.modifyRecordUOMMst(UomModel);
			
			if (UomModel.getStrMsgType().equals("1")) {
				throw new Exception(UomModel.getStrMsgString());
			}
			HelperMethods.populate(UomModel, UOMVO_p);
		
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_UNIT_OF_MEASUREMENT;
			mp.put("save_1", UomModel);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);
			
		} 
		catch (Exception e) 
		{
			strMsgText = "UomMstDATA.modifyRecord(fb,request) --> "			+ e.getMessage();
			UomModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
			HisException eObj = new HisException("registration","UomMstDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			UomMstDATA_obj = null;
			strMsgText = null;
		}
		return UomModel;

	}
	
	

	
		

}
