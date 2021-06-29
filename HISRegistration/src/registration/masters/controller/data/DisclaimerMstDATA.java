package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 11-Feb-2014
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
import vo.registration.DisclaimerVO;
import vo.registration.DisclaimerVO;
import vo.registration.UnitVO;


public class DisclaimerMstDATA 
{

	//To populate the values in the Disclaimer Disclaimer Master Screen Combos
	@SuppressWarnings("rawtypes")
	public static DisclaimerVO getDeptDisclaimerEssentials(HttpServletRequest request)
	{
		DisclaimerVO disclaimerModel=new DisclaimerVO();
		RegMasterBO bo = new RegMasterBO();
		try
		{
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			List disclaimerDeptList=bo.getDisclaimerDeptList(uservo);
			request.getSession().setAttribute("deptList",disclaimerDeptList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return disclaimerModel;

	}
	
	//To populate the values in the Unit Disclaimer Master Screen Combos
	@SuppressWarnings("rawtypes")
	public static DisclaimerVO getUnitDisclaimerEssentials(HttpServletRequest request)
	{
		RegMasterBO bo = new RegMasterBO();
		DisclaimerVO disclaimerModel=new DisclaimerVO();
		try
		{
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			List disclaimerDeptUnitList=bo.getDisclaimerDeptUnitList(uservo);
			request.getSession().setAttribute("deptUnitList",disclaimerDeptUnitList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return disclaimerModel;

	}
	
	public static boolean saveDisclaimerDtl(DisclaimerVO disclaimerModel,String strMode_p,HttpServletRequest request) 
	{

		DisclaimerVO objDiscModel;
		boolean bExistStatus=false;

		try {
			objDiscModel= new DisclaimerVO();			
			objDiscModel=disclaimerModel;

			UserVO uservo = ControllerUTIL.getUserVO(request);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.saveDisclaimerDtl(objDiscModel,strMode_p,uservo);

			if (objDiscModel.getStrMsgType()!=null && objDiscModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objDiscModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				throw new Exception();
			} else 
			{
				objDiscModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "DisclaimerMstDATA.saveDisclaimerDtl --> "+ e.getMessage();
			disclaimerModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			objDiscModel = null;
		}
		return bExistStatus;
	}
	
	public static boolean updateDisclaimerDtl(DisclaimerVO disclaimerModel,String strMode_p,HttpServletRequest request) 
	{

		DisclaimerVO objDiscModel;
		boolean bExistStatus=false;
		Map mp=new HashMap();

		try {
			objDiscModel= new DisclaimerVO();			
			objDiscModel=disclaimerModel;

			UserVO uservo = ControllerUTIL.getUserVO(request);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.updateDisclaimerDtl(objDiscModel,strMode_p,uservo);
			
			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objDiscModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objDiscModel.getStrDisclaimerCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objDiscModel.getStrMsgType()!=null && objDiscModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objDiscModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				throw new Exception();
			} else 
			{
				objDiscModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "DisclaimerMstDATA.modifyDisclaimerDtl --> "+ e.getMessage();
			disclaimerModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			objDiscModel = null;
		}
		return bExistStatus;
	}
	
	//To get the Data for Modify Page
	public static DisclaimerVO modifyRecord( HttpServletRequest request) 
	{
		DisclaimerMstDATA DisclaimerMstDATA_obj = null;
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		DisclaimerVO disclaimerModel =new DisclaimerVO();
		DepartmentVO deptModel=new DepartmentVO();
		UnitVO unitModel=new UnitVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try {

			DisclaimerMstDATA_obj = new DisclaimerMstDATA();
			RegMasterBO bo = new RegMasterBO();

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			disclaimerModel.setStrDisclaimerCode(strTemp2[0]);
			disclaimerModel.setStrHospitalCode(strTemp2[1]);
			DisclaimerVO DisclaimerVO_p=bo.modifyRecordDisclaimerMst(disclaimerModel);
			if(DisclaimerVO_p.getStrDeptCode()!=null&&DisclaimerVO_p.getStrDeptCode()!="")
			{
				deptModel.setStrDeptCode(DisclaimerVO_p.getStrDeptCode());
				deptModel.setStrHospitalCode(strTemp2[1]);
				deptModel=bo.modifyRecordDepartmentMst(deptModel);
				disclaimerModel.setStrDeptName(deptModel.getStrDeptName());
			}
			if(DisclaimerVO_p.getStrDeptUnitCode()!=null&&DisclaimerVO_p.getStrDeptUnitCode()!="")
			{
				unitModel.setStrDeptUnitCode(DisclaimerVO_p.getStrDeptUnitCode());
				unitModel.setStrUnitCode(DisclaimerVO_p.getStrDeptUnitCode().substring(3, 5));
				unitModel.setStrHospitalCode(strTemp2[1]);
				unitModel=bo.modifyRecordUnitMst(unitModel);
				disclaimerModel.setStrUnitName(unitModel.getStrUnitName());
			}

			if (disclaimerModel.getStrMsgType().equals("1")) {
				throw new Exception(disclaimerModel.getStrMsgString());
			}
			HelperMethods.populate(disclaimerModel, DisclaimerVO_p);
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_DISCLAIMER_MASTER;
			mp.put("save_1", disclaimerModel);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);
		
		} 
		catch (Exception e) 
		{
			strMsgText = "DisclaimerMstDATA.modifyRecord(fb,request) --> "			+ e.getMessage();
			disclaimerModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
			HisException eObj = new HisException("registration","DisclaimerMstDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			DisclaimerMstDATA_obj = null;
			strMsgText = null;
		}
		return disclaimerModel;

	}
	
	//Default Disclaimer Master Essentials
	public static DisclaimerVO getDefaultDisclaimerList(HttpServletRequest request)
	{
		Map essentialMap=new HashMap();
		DisclaimerVO disclaimerModel=new DisclaimerVO();
		DisclaimerVO disclMasterVO[]=new DisclaimerVO[3];
		Map mp= new LinkedHashMap();

		try{
		RegMasterBO bo = new RegMasterBO();
		UserVO uservo= ControllerUTIL.getUserVO(request);
		
		disclMasterVO=bo.getDefaultDisclaimerList(uservo);
		request.getSession().setAttribute(RegistrationConfig.ESSENTIAL_DISCLAIMER_MASTER_VO, disclMasterVO);
		
		if(disclMasterVO!=null)
		{
			disclaimerModel.setStrGeneralDisclaimerDesc1(disclMasterVO[0].getStrDisclaimerDesc1());
			disclaimerModel.setStrGeneralDisclaimerDesc2(disclMasterVO[0].getStrDisclaimerDesc2());
			disclaimerModel.setStrGeneralDisclaimerDesc3(disclMasterVO[0].getStrDisclaimerDesc3());
			disclaimerModel.setStrSpecialDisclaimerDesc1(disclMasterVO[1].getStrDisclaimerDesc1());
			disclaimerModel.setStrSpecialDisclaimerDesc2(disclMasterVO[1].getStrDisclaimerDesc2());
			disclaimerModel.setStrSpecialDisclaimerDesc3(disclMasterVO[1].getStrDisclaimerDesc3());
			disclaimerModel.setStrCasualityDisclaimerDesc1(disclMasterVO[2].getStrDisclaimerDesc1());
			disclaimerModel.setStrCasualityDisclaimerDesc2(disclMasterVO[2].getStrDisclaimerDesc2());
			disclaimerModel.setStrCasualityDisclaimerDesc3(disclMasterVO[2].getStrDisclaimerDesc3());
		
			disclaimerModel.setStrIsHeader(disclMasterVO[0].getStrIsHeader());
			disclaimerModel.setStrAlignment(disclMasterVO[0].getStrAlignment());
			//disclaimerModel.setDisclaimerId(disclMasterVO[0].getDisclaimerId());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_DISCLAIMER_MASTER;
			mp.put("save_1", disclaimerModel);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);
		}
		
		}catch(Exception e){	
			disclaimerModel.setStrWarning("Application Execution Error");
			e.printStackTrace();
		}
		finally{
		}
		return disclaimerModel;
	}
	
	//To Update the Default Disclaimer Details
	public static boolean updateDefaultDisclaimerDtl(DisclaimerVO disclaimerModel,String strMode_p,HttpServletRequest request) 
	{

		DisclaimerVO disclMasterVO[]=new DisclaimerVO[3];
		disclMasterVO[0]=new DisclaimerVO();
		disclMasterVO[1]=new DisclaimerVO();
		disclMasterVO[2]=new DisclaimerVO();

		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();
		String[] arrKeyVariables= new String[1];

		try {

			UserVO uservo = ControllerUTIL.getUserVO(request);
			RegMasterBO bo = new RegMasterBO();
			
			DisclaimerVO[] disclMstVO=(DisclaimerVO[])request.getSession().getAttribute(RegistrationConfig.ESSENTIAL_DISCLAIMER_MASTER_VO);
			
			HelperMethods.populate(disclMasterVO[0],disclaimerModel);
			HelperMethods.populate(disclMasterVO[1],disclaimerModel);
			HelperMethods.populate(disclMasterVO[2],disclaimerModel);
			
			String generalDisclaimerId=disclMstVO[0].getStrDisclaimerCode();
			String specialDisclaimerId=disclMstVO[1].getStrDisclaimerCode();
			String casualityDisclaimerId=disclMstVO[2].getStrDisclaimerCode();
			
			disclMasterVO[0].setStrDisclaimerCode(generalDisclaimerId);
			disclMasterVO[0].setStrDisclaimerDesc1(disclaimerModel.getStrGeneralDisclaimerDesc1());
			disclMasterVO[0].setStrDisclaimerDesc2(disclaimerModel.getStrGeneralDisclaimerDesc2());
			disclMasterVO[0].setStrDisclaimerDesc3(disclaimerModel.getStrGeneralDisclaimerDesc3());
			disclMasterVO[0].setStrUsabilityFlag(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL);
			
			disclMasterVO[1].setStrDisclaimerCode(specialDisclaimerId);
			disclMasterVO[1].setStrDisclaimerDesc1(disclaimerModel.getStrSpecialDisclaimerDesc1());
			disclMasterVO[1].setStrDisclaimerDesc2(disclaimerModel.getStrSpecialDisclaimerDesc2());
			disclMasterVO[1].setStrDisclaimerDesc3(disclaimerModel.getStrSpecialDisclaimerDesc3());
			disclMasterVO[1].setStrUsabilityFlag(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL);
			
			disclMasterVO[2].setStrDisclaimerCode(casualityDisclaimerId);
			disclMasterVO[2].setStrDisclaimerDesc1(disclaimerModel.getStrCasualityDisclaimerDesc1());
			disclMasterVO[2].setStrDisclaimerDesc2(disclaimerModel.getStrCasualityDisclaimerDesc2());
			disclMasterVO[2].setStrDisclaimerDesc3(disclaimerModel.getStrCasualityDisclaimerDesc3());
			disclMasterVO[2].setStrUsabilityFlag(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY);
		
			
			for(int i=0;i<disclMasterVO.length;i++){
				disclMasterVO[i].setStrAlignment(disclaimerModel.getStrAlignment());
				disclMasterVO[i].setStrIsHeader(disclaimerModel.getStrIsHeader());
				disclMasterVO[i].setStrIsValid(disclaimerModel.getStrIsValid());
				
				bExistStatus=bo.updateDisclaimerDtl(disclMasterVO[i],strMode_p,uservo);
				
				//Audit Log on update
				if(bExistStatus)
				{
					mp.put("save_1"  , disclMasterVO[i]);
					arrKeyVariables[0]=disclMasterVO[i].getStrDisclaimerCode();
					
				}
				
				if (disclMasterVO[i].getStrMsgType()!=null && disclMasterVO[i].getStrMsgType().equals("1")) 
				{
					throw new Exception(disclMasterVO[i].getStrMsgString());
				}	
				if (bExistStatus == false) 
				{
					throw new Exception();
				} else 
				{
					disclMasterVO[i].setStrMsg("Data Saved Successfully");
				}
			}	
			
			AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "DisclaimerMstDATA.saveDisclaimerDtl --> "+ e.getMessage();
			disclaimerModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			disclMasterVO = null;
		}
		return bExistStatus;
	}


	
		

}
