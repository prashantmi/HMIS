package registration.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 07-Jan-2014
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

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.PatCategoryVO;


public class PatCategoryMstDATA 
{



	//To Save the Patient Category Details 
	public static boolean savePatCategoryDtl(PatCategoryVO patCatModel,String strMode_p) 
	{

		String strmsgText = "";
		PatCategoryVO objpatCatModel;
		boolean bExistStatus=false;

		try {
			objpatCatModel= new PatCategoryVO();			
			objpatCatModel=patCatModel;

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			objpatCatModel.setStrHospitalCode(Config.SUPER_HOSPITAL_CODE);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.savePatCategoryDtl(objpatCatModel,strMode_p,uservo);

			if (objpatCatModel.getStrMsgType()!=null && objpatCatModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objpatCatModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objpatCatModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objpatCatModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "PatientCtegoryAction.PatientCtegoryDATA.savePatientCtegoryDtl(vo) --> "
					+ e.getMessage();
			patCatModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		} finally 
		{
			objpatCatModel = null;
		}
		return bExistStatus;
	}

	//To update the Patient Category Details
	public static boolean updatePatCategoryDtl(PatCategoryVO patCatModel,String strMode_p)
	{

		String strmsgText = "";
		PatCategoryVO objpatCatModel;
		boolean bExistStatus=false;

		try {
			objpatCatModel= new PatCategoryVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			objpatCatModel=patCatModel;
			objpatCatModel.setStrHospitalCode(Config.SUPER_HOSPITAL_CODE);
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.updatePatCategoryDtl(objpatCatModel,strMode_p,uservo);

			if (objpatCatModel.getStrMsgType()!=null && objpatCatModel.getStrMsgType().equals("1")) {
				throw new Exception(objpatCatModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objpatCatModel.setStrWarning("Duplicate Name Exist..!");
			} else {
				objpatCatModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "PatientCtegoryAction.PatientCtegoryDATA.updatePatCategoryDtl(vo) --> "
					+ e.getMessage();
			patCatModel.setStrErrorMsg("Application Error [ERROR ID : " +strmsgText);
		} finally {

			objpatCatModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static PatCategoryVO modifyRecord(HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		String strTemp3[] = null;

		PatCategoryVO patCatModel =new PatCategoryVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try {

			String strCombos[] = request.getParameterValues("combo");

			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			strTemp3=strTemp2[1].replace('|','#').split("#");
			
			patCatModel.setStrPatCategoryCode(strTemp2[0]);
			//patCatModel.setStrHospitalCode(strTemp2[1]);
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
		 	*/
			patCatModel.setStrHospitalCode(strTemp3[0]);
			//End:Sheeldarshi
			RegMasterBO bo = new RegMasterBO();
			PatCategoryVO PatCategoryVO_p=bo.modifyRecordPatCategoryMst(patCatModel);

			if (patCatModel.getStrMsgType().equals("1")) {
				throw new Exception(patCatModel.getStrMsgString());
			}

			HelperMethods.populate(patCatModel, PatCategoryVO_p);
			patCatModel.setStrIdVerificationDoc(patCatModel.getStrIdVerificationDoc()+"#"+patCatModel.getStrIdSize()+"#"+patCatModel.getStrIdValidationType());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_LOCAL_PATIENT_CATEGORY_MASTER;
			mp.put("save_1", patCatModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);

		} 
		catch (Exception e) 
		{
			strMsgText = "PatientCtegoryAction.modifyRecord(fb,request) --> "
					+ e.getMessage();
			patCatModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);

		}
		finally 
		{
			strMsgText = null;
		}
		return patCatModel;

	}


	//To Save the Patient Category Details 
	public static boolean savePatCategoryDtlLocal(PatCategoryVO patCatModel,String strMode_p) 
	{

		String strmsgText = "";
		PatCategoryVO objpatCatModel;
		boolean bExistStatus=false;

		try {
			objpatCatModel= new PatCategoryVO();			
			objpatCatModel=patCatModel;

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			objpatCatModel.setStrHospitalCode(uservo.getHospitalCode());
			RegMasterBO objBo = new RegMasterBO();
			bExistStatus=objBo.savePatCategoryDtl(objpatCatModel,strMode_p,uservo);

			if (objpatCatModel.getStrMsgType()!=null && objpatCatModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objpatCatModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objpatCatModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objpatCatModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "PatientCtegoryAction.PatientCtegoryDATA.savePatCategoryDtlLocal(vo) --> "
					+ e.getMessage();
			patCatModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);
		} finally 
		{
			objpatCatModel = null;
		}
		return bExistStatus;
	}

	//To update the Patient Category Details
	public static boolean updatePatCategoryDtlLocal(PatCategoryVO patCatModel,String strMode_p)
	{

		String strmsgText = "";
		PatCategoryVO objpatCatModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try {
			objpatCatModel= new PatCategoryVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO objBo = new RegMasterBO();
			objpatCatModel=patCatModel;
			objpatCatModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=objBo.updatePatCategoryDtl(objpatCatModel,strMode_p,uservo);

			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objpatCatModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objpatCatModel.getStrPatCategoryCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}
			
			if (objpatCatModel.getStrMsgType()!=null && objpatCatModel.getStrMsgType().equals("1")) {
				throw new Exception(objpatCatModel.getStrMsgString());
			}

			if (bExistStatus == false) {
				objpatCatModel.setStrWarning("Duplicate Name Exist..!");
			} else {
				objpatCatModel.setStrMsg("Data Saved Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "PatientCtegoryAction.PatientCtegoryDATA.updatePatCategoryDtlLocal(vo) --> "
					+ e.getMessage();
			patCatModel.setStrErrorMsg("Application Error [ERROR ID : "+ strmsgText);
		} finally {

			objpatCatModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static PatCategoryVO modifyRecordLocal(HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		String strTemp3[] = null;

		PatCategoryVO patCatModel =new PatCategoryVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try {

			String strCombos[] = request.getParameterValues("combo");
			RegMasterBO objBo = new RegMasterBO();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");	//[60, 101|0]
			strTemp3=strTemp2[1].replace('|','#').split("#");
			
			patCatModel.setStrPatCategoryCode(strTemp2[0]);
			/*Start: Surabhi
			 * reason: for adding the organisation combo in modification process of local patient category
			 * date : 29-7-2016*/
			patCatModel.setStrPatClient(strTemp2[1]);
			//End
			patCatModel.setStrHospitalCode(strTemp3[0]);
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
		 	*/
			patCatModel.setStrIsApprovalRequired(strTemp3[1]);
			//End:Sheeldarshi
			PatCategoryVO PatCategoryVO_p=objBo.modifyRecordPatCategoryMst(patCatModel);

			if (patCatModel.getStrMsgType().equals("1")) {
				throw new Exception(patCatModel.getStrMsgString());
			}

			HelperMethods.populate(patCatModel, PatCategoryVO_p);
			patCatModel.setStrIdVerificationDoc(patCatModel.getStrIdVerificationDoc()+"#"+patCatModel.getStrIdSize()+"#"+patCatModel.getStrIdValidationType());

			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_LOCAL_PATIENT_CATEGORY_MASTER;
			mp.put("save_1", patCatModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);

		} 
		catch (Exception e) 
		{
			strMsgText = "PatientCtegoryAction.PatientCtegoryDATA.modifyRecordLocal(fb,request) --> "
					+ e.getMessage();
			patCatModel.setStrErrorMsg("Application Error [ERROR ID : "+ strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return patCatModel;

	}

	//To put the Data for Add Page
	public static PatCategoryVO addPatCatRecord(PatCategoryVO patcatmodel) 
	{
		String strMsgText = "";
		PatCategoryVO patcatmodel_p =new PatCategoryVO();
		try {

			RegMasterBO objBo = new RegMasterBO();
			patcatmodel_p.setStrPatCategoryCode(patcatmodel.getStrPatCategoryCode());
			patcatmodel_p.setStrPatClient(patcatmodel.getStrPatClient());/*Changes done for Client combo by Surabhi on 26th july 2016*/
			patcatmodel_p.setStrHospitalCode(Config.SUPER_HOSPITAL_CODE);
			PatCategoryVO PatCategoryVO_p=objBo.modifyRecordPatCategoryMst(patcatmodel_p);

			if (patcatmodel_p.getStrMsgType().equals("1")) {
				throw new Exception(patcatmodel_p.getStrMsgString());
			}

			patcatmodel_p.setStrPatCategoryCode(PatCategoryVO_p.getStrPatCategoryCode());
			patcatmodel_p.setStrPatCategoryName(PatCategoryVO_p.getStrPatCategoryName());			
			patcatmodel_p.setStrPatCategoryType(PatCategoryVO_p.getStrPatCategoryType());
			patcatmodel_p.setStrPatClient(PatCategoryVO_p.getStrPatClient());/*Changes done for Client combo by Surabhi on 26th july 2016*/
			patcatmodel_p.setStrIdVerificationDoc(PatCategoryVO_p.getStrIdVerificationDoc()+"#"+PatCategoryVO_p.getStrIdSize()+"#"+PatCategoryVO_p.getStrIdValidationType());
		} 
		catch (Exception e) 
		{
			strMsgText = "PatientCtegoryAction.addPatCatRecord(fb,request) --> "
					+ e.getMessage();
			patcatmodel_p.setStrErrorMsg("Application Error [ERROR ID : "+ strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return patcatmodel_p;

	}
	//To populate the values in the Local Patient Category Master Screen Combos
	public static void getPatCategoryEssentials()
	{
		try{

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO objBo = new RegMasterBO();
			List globalPatCategoryList=objBo.getGlobalPatCategory(uservo,"1");
			List globalVerificationDocList = objBo.getVerificationDocuments(uservo);
			List globalClientList = objBo.getClients(uservo);
			List strBoundDeptCode = objBo.getDeptList(uservo);/**By Mukund on 03.11.2017*/
			List applicableServices = objBo.getApplicableServices(uservo);/** By Vasu on 14.05.2018*/
			request.getSession().setAttribute("globalPatCategoryList",globalPatCategoryList);
			request.getSession().setAttribute("globalVerificationDocList",globalVerificationDocList);
			request.getSession().setAttribute("globalClientList",globalClientList);
			request.getSession().setAttribute("globalDepartmentList", strBoundDeptCode);
			request.getSession().setAttribute("applicableServicesList",applicableServices);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
