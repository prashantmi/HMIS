package registration.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 31-Dec-2013
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

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.DepartmentVO;
import vo.registration.UnitConsultantVO;
import vo.registration.UnitVO;


public class UnitMstDATA 
{
	public static UnitVO getUnitEssentials(UnitVO unitmodel)
	{
		UnitVO objUnitModel_p = null;
		DepartmentVO objDeptModel_p = null;

		try{
			
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			
			objDeptModel_p=new DepartmentVO();
			RegMasterBO objRegMasterBO = new RegMasterBO();
			
			/*if(unitmodel==null)
			{*/
				System.out.println("------+On Load+-------");
				//objUnitModel_p= new UnitVO();
				objUnitModel_p= unitmodel;	
				
				String strCombos[] = request.getParameterValues("combo");
				objDeptModel_p.setStrDeptCode(strCombos[0]);
				objDeptModel_p.setStrHospitalCode(uservo.getHospitalCode());				
				
				objDeptModel_p=objRegMasterBO.modifyRecordDepartmentMst(objDeptModel_p);
				
				objUnitModel_p.setStrDeptName(objDeptModel_p.getStrDeptName());
				objUnitModel_p.setStrDeptCode(objDeptModel_p.getStrDeptCode());
				objUnitModel_p.setStrIsUnit(RegistrationConfig.IS_DEPT_UNIT);
				
				objUnitModel_p.setStrLowerAgeLimit(objDeptModel_p.getStrLowerAgeLimit());
				objUnitModel_p.setStrMaxWalkinReg(objDeptModel_p.getStrMaxWalkinReg());
				objUnitModel_p.setStrMaxWalkinFolloUp(objDeptModel_p.getStrMaxWalkinFolloUp());
				objUnitModel_p.setStrMaxWalkinPortReg(objDeptModel_p.getStrMaxWalkinPortReg());
				objUnitModel_p.setStrMaxWalkinPortFollowUP(objDeptModel_p.getStrMaxWalkinPortFollowUP());
				
				
				
				
				
				
			/*}*/
			/*else
			{
				System.out.println("------+On ReLoad+-------");
				objUnitModel_p= unitmodel;
				objDeptModel_p.setStrDeptCode(unitmodel.getStrDeptCode());
				objDeptModel_p.setStrHospitalCode(uservo.getHospitalCode());	
				
				objDeptModel_p=objRegMasterBO.modifyRecordDepartmentMst(objDeptModel_p);
				objUnitModel_p.setStrDeptName(objDeptModel_p.getStrDeptName());
			}*/
					
				
			if(objUnitModel_p.getStrIsGeneral()!=null && objUnitModel_p.getStrIsGeneral()!="" && objUnitModel_p.getStrIsGeneral()!="-1" && objUnitModel_p.getStrIsGeneral()!="0")
				objUnitModel_p=getRenewalRecord(objUnitModel_p);
			
			List hodList=objRegMasterBO.getHeadOfDept("1",uservo);
			List locationList=objRegMasterBO.getLocation(uservo);
			request.getSession().setAttribute("hodList",hodList);
			request.getSession().setAttribute("locationList",locationList);
			objUnitModel_p.setStrIsRefer(RegistrationConfig.IS_DEPT_UNIT);
			objUnitModel_p.setStrCardPrintSetup(RegistrationConfig.IS_DOC_UNIT);
			
			}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return objUnitModel_p;
	}
	
	//To Save the Department Details 
	public static boolean saveUnitDtl(UnitVO unitModel,	String strMode_p) 
	{

		String strmsgText = "";
		UnitVO objUnitModel;
		boolean bExistStatus=false;
		UnitConsultantVO[] unitConsultantVO = new UnitConsultantVO[]{new UnitConsultantVO()};
		String deptUnitCode=null;
		String unitCode=null;


		try 
		{
			RegMasterBO objRegMasterBO = new RegMasterBO();
			objUnitModel= new UnitVO();			
			objUnitModel=unitModel;
			
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);
			
			deptUnitCode=objRegMasterBO.getDeptUnitCode(objUnitModel,uservo);
			unitCode=deptUnitCode.substring(3, 5);
			
			objUnitModel.setStrHospitalCode(uservo.getHospitalCode());
			objUnitModel.setStrDeptUnitCode(deptUnitCode);
			objUnitModel.setStrUnitCode(unitCode);
			
			bExistStatus=objRegMasterBO.saveUnitDtl(objUnitModel,strMode_p,uservo);
			
			if(objUnitModel.getStrIsUnit().equals(RegistrationConfig.IS_UNIT_DOCTOR_UNIT))
			{				
				unitConsultantVO[0]=new UnitConsultantVO();
				unitConsultantVO[0].setStrDeptUnitCode(deptUnitCode);
				unitConsultantVO[0].setStrEmpCode(objUnitModel.getStrEmpCode());
				unitConsultantVO[0].setStrLocCode(objUnitModel.getStrLocCode());
				unitConsultantVO[0].setStrIsHeadOfUnit(RegistrationConfig.IS_HOU_TRUE);
				objRegMasterBO.saveUnitConsultantDtl(unitConsultantVO, strMode_p, uservo);
			}

			if (objUnitModel.getStrMsgType()!=null && objUnitModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objUnitModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objUnitModel.setStrWarning("Data already exist");
			} else 
			{
				objUnitModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "UnitUTIL.saveUnitDtl(vo) --> "		+ e.getMessage();
			unitModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} finally 
		{
			objUnitModel = null;
		}
		return bExistStatus;
	}
	
	
	//To get the Data for Modify Page
	public static UnitVO modifyRecord( HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		UnitVO unitModel =new UnitVO();
		String strChk = "";
		Map mp= new LinkedHashMap();
		
		try 
		{
			RegMasterBO objRegMasterBO = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);
			
            String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			unitModel.setStrDeptUnitCode(strTemp2[0]);
			unitModel.setStrUnitCode(strTemp2[1]);
			unitModel.setStrHospitalCode(strTemp2[2]);
			unitModel.setStrDeptName(strTemp2[3]);
			UnitVO UnitVO_p=objRegMasterBO.modifyRecordUnitMst(unitModel);
			List hodList=objRegMasterBO.getHeadOfDept("1",uservo);
			List locationList=objRegMasterBO.getLocation(uservo);
			request.getSession().setAttribute("hodList",hodList);
			request.getSession().setAttribute("locationList",locationList);
			
			if (unitModel.getStrMsgType().equals("1"))
			{
				throw new Exception(unitModel.getStrMsgString());
			}
			unitModel.setStrDeptUnitCode(UnitVO_p.getStrDeptUnitCode());
			unitModel.setStrDeptCode(UnitVO_p.getStrDeptCode());
			unitModel.setStrUnitName(UnitVO_p.getStrUnitName());
			unitModel.setStrUnitLocCode(UnitVO_p.getStrUnitLocCode());
			unitModel.setStrMobileNo(UnitVO_p.getStrMobileNo());     //Added by warish 22-aug-2017
			unitModel.setStrEmpCode(UnitVO_p.getStrEmpCode());
			unitModel.setStrIsGeneral(UnitVO_p.getStrIsGeneral());
			unitModel.setStrUnitCode(UnitVO_p.getStrUnitCode());
			unitModel.setStrIsExpiry(UnitVO_p.getStrIsExpiry());
			unitModel.setStrExpiryDay(UnitVO_p.getStrExpiryDay());
			unitModel.setStrExpiryMonth(UnitVO_p.getStrExpiryMonth());
			unitModel.setStrDiagnosisType(UnitVO_p.getStrDiagnosisType());
			unitModel.setStrEpiDefCloseDays(UnitVO_p.getStrEpiDefCloseDays());
			unitModel.setStrIsRefer(UnitVO_p.getStrIsRefer());
			unitModel.setStrHospitalCode(UnitVO_p.getStrHospitalCode());
			unitModel.setStrRemarks(UnitVO_p.getStrRemarks());
			unitModel.setStrIsValid(UnitVO_p.getStrIsValid());
			unitModel.setStrIsAppointment(UnitVO_p.getStrIsAppointment());
			//Below Fields Added To Enhance  Functinality of Maximum Walkins and Lower Age Limit On 25/09/2018 By Raj Kumar
			unitModel.setStrLowerAgeLimit(UnitVO_p.getStrLowerAgeLimit());
			unitModel.setStrMaxWalkinReg(UnitVO_p.getStrMaxWalkinReg());
			unitModel.setStrMaxWalkinFolloUp(UnitVO_p.getStrMaxWalkinFolloUp());
			unitModel.setStrMaxWalkinPortReg(UnitVO_p.getStrMaxWalkinPortReg());
			unitModel.setStrMaxWalkinPortFollowUP(UnitVO_p.getStrMaxWalkinPortFollowUP());
			
			
			
			
			
			if(unitModel.getStrIsGeneral()!=null && unitModel.getStrIsGeneral()!="" && unitModel.getStrIsGeneral()!="-1")
				unitModel=getRenewalRecord(unitModel);

			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_UNIT_MASTER;
			mp.put("save_1", unitModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);
			
		} 
		catch (Exception e) 
		{
			strMsgText = "UnitUTIL.modifyRecord(fb,request) --> "		+ e.getMessage();
			HisException eObj = new HisException("cssd","QualityTestMstDATA->modifyRecord()", strMsgText);
			unitModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strMsgText);
			//objOccupationVO.setStrErrMsg("Application Error [ERROR ID : "
			//		+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally 
		{
			strMsgText = null;
		}
		return unitModel;

	}
		
	//To update the Unit Details
	public static boolean updateUnitDtl(UnitVO unitModel,String strMode_p)
	{

		String strmsgText = "";
		UnitVO objUnitModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try {
			objUnitModel= new UnitVO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO objRegMasterBO = new RegMasterBO();
			objUnitModel=unitModel;
			objUnitModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=objRegMasterBO.updateUnitDtl(objUnitModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objUnitModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objUnitModel.getStrDeptCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objUnitModel.getStrMsgType()!=null && objUnitModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objUnitModel.getStrMsgString());
			}

			if (bExistStatus == false)
			{
				objUnitModel.setStrWarning("Duplicate Name Exist..!");
			} else
			{
				objUnitModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "UnitUTIL.updateUnitDtl(vo) --> "	+ e.getMessage();
			unitModel.setStrErrorMsg("Application Error [ERROR ID : " +strmsgText);
		} 
		finally
		{

			objUnitModel = null;
		}
		return bExistStatus;
	}
	
	//To get the Renewal type Details
	public static UnitVO getRenewalRecord(UnitVO unitmodel) 
	{
		String strMsgText = "";
		try 
		{
			RegMasterBO objRegMasterBO = new RegMasterBO();
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			//unitModel_p.setStrIsGeneral(unitmodel.getStrIsGeneral());
            unitmodel.setStrHospitalCode(uservo.getHospitalCode());
            unitmodel=objRegMasterBO.modifyRenewalUnitMst(unitmodel);
				           
            if (unitmodel.getStrMsgType().equals("1")) 
            {
				throw new Exception(unitmodel.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			strMsgText = "UnitUTIL.renewalRecord(fb,request) --> "	+ e.getMessage();
			unitmodel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return unitmodel;

	}
	
}
