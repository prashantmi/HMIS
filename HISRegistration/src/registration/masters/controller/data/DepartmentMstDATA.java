package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 23-Dec-2013
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

import org.apache.struts2.ServletActionContext;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.DepartmentVO;

public class DepartmentMstDATA 
{



	private DepartmentVO modifyRecordDepartmentMst(DepartmentVO vo)
	{
		DepartmentVO DepartmentVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try 
		{
			DepartmentVO_p = bo.modifyRecordDepartmentMst(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("RegMasterBO.modifyRecord(vo) --> "	+ vo.getStrMsgString());
			}
		}
		catch (HisRecordNotFoundException e) 
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e) 
		{
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) 
		{
			throw new HisDataAccessException();
		} 
		catch (Exception e) 
		{
			throw new HisApplicationExecutionException();
		} 
		finally 
		{
		}
		return DepartmentVO_p;
	}


	public static void getDepartmentEssentials()
	{
		try{			

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			RegMasterBO bo = new RegMasterBO();
			List departmentType=bo.getDeptType(uservo);
			List genderList=bo.getGenderList(uservo);
			request.getSession().setAttribute("departmentType",departmentType);
			request.getSession().setAttribute("genderList",genderList);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}


	public static boolean saveDepartmentDtl(DepartmentVO deptModel,	String strMode_p) 
	{

		DepartmentVO objDeptModel;
		boolean bExistStatus=false;

		try {
			objDeptModel= new DepartmentVO();			
			objDeptModel=deptModel;

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			RegMasterBO bo = new RegMasterBO();
			objDeptModel.setStrHospitalCode(Config.SUPER_HOSPITAL_CODE);
			bExistStatus=bo.saveDepartmentDtl(objDeptModel,strMode_p,uservo);

			if (objDeptModel.getStrMsgType()!=null && objDeptModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objDeptModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objDeptModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objDeptModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "DepartmentMstDATA.saveDepartmentDtl --> "+ e.getMessage();
			deptModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		}
		finally 
		{
			objDeptModel = null;
		}
		return bExistStatus;
	}


	//To populate the values in the Department Master Screen Combos
	public static List getDepartmentEssentialsLocal()
	{
		List alList=null;
		ActionContext acx=ActionContext.getContext();
		HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		List hodList=new ArrayList();List genderList=new ArrayList();List locationList=new ArrayList();
		List departmentType=new ArrayList();List globalDeptList=new ArrayList();
		try
		{
			
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			hodList=bo.getHeadOfDept("1",uservo);
			Map globalDeptMap=bo.getGlobalDept(uservo);
			globalDeptList=(List)globalDeptMap.get("globalDeptList");
			DepartmentVO[] globalDeptArray=(DepartmentVO[])globalDeptMap.get("globalDeptArray");			
			locationList=bo.getLocation(uservo);
			departmentType=bo.getDeptType(uservo);
			genderList=bo.getGenderList(uservo);
			request.getSession().setAttribute("genderList",genderList);
			request.getSession().setAttribute("hodList",hodList);
			request.getSession().setAttribute("locationList",locationList);
			request.getSession().setAttribute("departmentType",departmentType);
			request.getSession().setAttribute("globalDeptList",globalDeptList);
			request.getSession().setAttribute("globalDeptArray",globalDeptArray);
		}
		catch(HisRecordNotFoundException e)
		{
			initializeList(request);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			initializeList(request);
			e.printStackTrace();
			throw new HisException(e.getMessage());			
		}
		return alList;

	}

	//To Save the Department Details 
	public static boolean saveDepartmentDtlLocal(DepartmentVO deptModel,	String strMode_p) 
	{

		boolean bExistStatus=false;

		try {

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			deptModel.setStrHospitalCode(uservo.getHospitalCode());
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.saveDepartmentDtl(deptModel,strMode_p,uservo);

			if (deptModel.getStrMsgType()!=null && deptModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(deptModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				deptModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				deptModel.setStrMsg("Data Saved Successfully");
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "DepartmentMstDATA..saveDepartmentDtlLocal(vo) --> "	+ e.getMessage();
			deptModel.setStrErrorMsg("Application Error ,Contact System Administrator!  "+strmsgText);

			//eObj = null;
		} finally 
		{
			deptModel = null;
		}
		return bExistStatus;
	}


	//To get the Data for Modify Page
	public static DepartmentVO modifyRecord( HttpServletRequest request) 
	{
		DepartmentMstDATA DepartmentMstDATA_obj = null;
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		DepartmentVO deptModel =new DepartmentVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try {

			DepartmentMstDATA_obj = new DepartmentMstDATA();

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			deptModel.setStrDeptCode(strTemp2[0]);
			deptModel.setStrHospitalCode(strTemp2[1]);
			DepartmentVO DepartmentVO_p=DepartmentMstDATA_obj.modifyRecordDepartmentMst(deptModel);

			if (deptModel.getStrMsgType().equals("1")) {
				throw new Exception(deptModel.getStrMsgString());
			}
			deptModel.setStrDeptCode(DepartmentVO_p.getStrDeptCode());
			deptModel.setStrDeptName(DepartmentVO_p.getStrDeptName());			
			deptModel.setStrDeptType(DepartmentVO_p.getStrDeptType());
			deptModel.setStrDeptLocCode(DepartmentVO_p.getStrDeptLocCode());
			deptModel.setStrHodCode(DepartmentVO_p.getStrHodCode());

			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_LOCAL_DEPARTMENT_MASTER;
			mp.put("save_1", deptModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);

		} 
		catch (Exception e) 
		{
			strMsgText = "DepartmentMstDATA.modifyRecord(fb,request) --> "			+ e.getMessage();
			deptModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
			HisException eObj = new HisException("registration","DepartmentMstDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			DepartmentMstDATA_obj = null;
			strMsgText = null;
		}
		return deptModel;

	}

	//To put the Data for Add Page
	public static DepartmentVO addDeptRecord(DepartmentVO deptmodel, HttpServletRequest request) 
	{
		String strMsgText = "";
		String added="N";
		try
		{
			DepartmentVO[] deptModel_p=null;


			if(request.getSession()!=null)
				deptModel_p=(DepartmentVO[])request.getSession().getAttribute("globalDeptArray");
			else
			{
				ActionContext acx=ActionContext.getContext();
				request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
				RegMasterBO bo = new RegMasterBO();
				UserVO uservo = ControllerUTIL.getUserVO(request);
				Map globalDeptMap=bo.getGlobalDept(uservo);
				deptModel_p=(DepartmentVO[])globalDeptMap.get("globalDeptArray");
			}

			for(int i=0;i<deptModel_p.length;i++)
			{
				if(deptModel_p[i].getStrDeptCode().equals(deptmodel.getStrDeptCode()))
				{
					deptmodel.setStrDeptName(deptModel_p[i].getStrDeptName());
					deptmodel.setStrDeptShortName(deptModel_p[i].getStrDeptShortName());
					deptmodel.setStrDeptType(deptModel_p[i].getStrDeptType());
					deptmodel.setStrAgeLimit(deptModel_p[i].getStrAgeLimit());
					deptmodel.setStrGenderCode(deptModel_p[i].getStrGenderCode());
					deptmodel.setStrLowerAgeLimit(deptModel_p[i].getStrLowerAgeLimit());
					
					deptmodel.setStrMaxWalkinReg(deptModel_p[i].getStrMaxWalkinReg());
					deptmodel.setStrMaxWalkinFolloUp(deptModel_p[i].getStrMaxWalkinFolloUp());
					deptmodel.setStrMaxWalkinPortReg(deptModel_p[i].getStrMaxWalkinPortReg());
					deptmodel.setStrMaxWalkinPortFollowUP(deptModel_p[i].getStrMaxWalkinPortFollowUP());
					
					deptmodel.setStrIsCappingAllowed(deptModel_p[i].getStrIsCappingAllowed());
					
					added="Y";

				}
				if(added!="Y")
				{
					deptmodel.setStrDeptType("-1");
					deptmodel.setStrAgeLimit("");
					deptmodel.setStrGenderCode("-1");

				}
			}			
		}
		catch (Exception e) 
		{
			strMsgText = "DepartmentMstDATA.addDeptRecord(fb,request) --> "		+ e.getMessage();
			deptmodel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
			e.printStackTrace();
		}
		finally 
		{
			strMsgText = null;
		}
		return deptmodel;

	}

	//To update the Department Details
	public static boolean updateDepartmentDtl(DepartmentVO deptModel,String strMode_p)
	{

		String strmsgText = "";
		DepartmentVO objDeptModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try {
			RegMasterBO bo = new RegMasterBO();
			objDeptModel= new DepartmentVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			objDeptModel=deptModel;
			objDeptModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateDepartmentDtl(objDeptModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objDeptModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objDeptModel.getStrDeptCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objDeptModel.getStrMsgType()!=null && objDeptModel.getStrMsgType().equals("1")) {
				throw new Exception(objDeptModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objDeptModel.setStrWarning("Data already exist");
			} else {
				objDeptModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "DepartmentMstDATA.updateDepartmentDtl --> "
					+ e.getMessage();
			deptModel.setStrErrorMsg("Application Error [ERROR ID : " +strmsgText);

			//eObj = null;
		} 
		finally 
		{
			objDeptModel = null;
		}
		return bExistStatus;
	}
	
	@SuppressWarnings("rawtypes")
	public static void initializeList(HttpServletRequest request)
	{
		if(null==request.getSession().getAttribute("genderList"))
			request.getSession().setAttribute("genderList",new ArrayList());
		if(null==request.getSession().getAttribute("hodList"))
			request.getSession().setAttribute("hodList",new ArrayList());
		if(null==request.getSession().getAttribute("locationList"))
			request.getSession().setAttribute("locationList",new ArrayList());
		if(null==request.getSession().getAttribute("departmentType"))
			request.getSession().setAttribute("departmentType",new ArrayList());
		if(null==request.getSession().getAttribute("globalDeptList"))
			request.getSession().setAttribute("globalDeptList",new ArrayList());
	}


}
