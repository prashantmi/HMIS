package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 17-Feb-2014
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
import vo.registration.CounterVO;
import vo.registration.CounterVO;
import vo.registration.UnitVO;


public class CounterMstDATA 
{

	//To populate the values in the Counter Master Screen Combos
	@SuppressWarnings("rawtypes")
	public static void getCounterMstEssentials(HttpServletRequest request)
	{
		RegMasterBO bo = new RegMasterBO();
		try
		{
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			List counterTypeList=bo.getCounterTypeList(uservo);
			List locationList=bo.getLocation(uservo);
			request.getSession().setAttribute("counterTypeList",counterTypeList);
			request.getSession().setAttribute("locationList",locationList);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	
	public static boolean saveCounterDtl(CounterVO counterModel,String strMode_p,HttpServletRequest request) 
	{

		CounterVO objCounterModel;
		boolean bExistStatus=false;

		try {
			objCounterModel= new CounterVO();			
			objCounterModel=counterModel;

			UserVO uservo = ControllerUTIL.getUserVO(request);
			RegMasterBO bo = new RegMasterBO();
			objCounterModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.saveCounterDtl(objCounterModel,strMode_p,uservo);

			if (objCounterModel.getStrMsgType()!=null && objCounterModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objCounterModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objCounterModel.setStrWarning("Duplicate Name or IP Address Already Exist..!");
			} else 
			{
				objCounterModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "CounterMstDATA.saveCounterDtl --> "+ e.getMessage();
			counterModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			objCounterModel = null;
		}
		return bExistStatus;
	}
	
	public static boolean updateCounterDtl(CounterVO CounterModel,String strMode_p,HttpServletRequest request) 
	{

		CounterVO objCounterModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try {
			objCounterModel= new CounterVO();			
			objCounterModel=CounterModel;

			UserVO uservo = ControllerUTIL.getUserVO(request);
			RegMasterBO bo = new RegMasterBO();
			objCounterModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateCounterDtl(objCounterModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objCounterModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objCounterModel.getStrCounterCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objCounterModel.getStrMsgType()!=null && objCounterModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objCounterModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objCounterModel.setStrWarning("Duplicate Name or IP Address Already Exist..!");
			} else 
			{
				objCounterModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "CounterMstDATA.modifyCounterDtl --> "+ e.getMessage();
			CounterModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			objCounterModel = null;
		}
		return bExistStatus;
	}
	
	//To get the Data for Modify Page
	public static CounterVO modifyRecord( HttpServletRequest request) 
	{
		CounterMstDATA CounterMstDATA_obj = null;
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		CounterVO counterModel =new CounterVO();
		DepartmentVO deptModel=new DepartmentVO();
		UnitVO unitModel=new UnitVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try {

			CounterMstDATA_obj = new CounterMstDATA();
			RegMasterBO bo = new RegMasterBO();

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			counterModel.setStrCounterCode(strTemp2[0]);
			counterModel.setStrHospitalCode(strTemp2[1]);
			CounterVO CounterVO_p=bo.modifyRecordCounterMst(counterModel);
			
			if (counterModel.getStrMsgType().equals("1")) {
				throw new Exception(counterModel.getStrMsgString());
			}
			HelperMethods.populate(counterModel, CounterVO_p);
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_COUNTER_MASTER;
			mp.put("save_1", counterModel);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);
		
		} 
		catch (Exception e) 
		{
			strMsgText = "CounterMstDATA.modifyRecord(fb,request) --> "			+ e.getMessage();
			counterModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
			HisException eObj = new HisException("registration","CounterMstDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			CounterMstDATA_obj = null;
			strMsgText = null;
		}
		return counterModel;

	}
	
	

	
		

}
