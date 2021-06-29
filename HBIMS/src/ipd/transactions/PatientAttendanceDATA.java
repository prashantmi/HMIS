package ipd.transactions;
/*
 * Patient Attendance DATA
 * 
 * author: Debashis Sardar
 * 
 * dated: 16/02/2012 
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class PatientAttendanceDATA {

/**
 * to get Service type combo
 * @param formBean - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void getServiceType(PatientAttendanceFB formBean,HttpServletRequest request, HttpServletResponse response) {
			
	PatientAttendanceVO voObj= null;
	PatientAttendanceBO bo= null;
		HisUtil util = null;
		String temp="";
		try 
		 {
			voObj =  new PatientAttendanceVO();
			bo    =  new PatientAttendanceBO();
			
			voObj.setStrHospCode(formBean.getStrHospCode());
			bo.getServiceType(voObj);
			
			{
			util = new HisUtil("Patient Attendance Transaction",
								"PatientAttendanceDATA");
				temp = util.getOptionValue(voObj.getStrServiceTypeWS(), "0",
						"0^Select Value", false);
				formBean.setStrServiceType(temp);
			
				if(voObj.getStrMsgType().equals("1"))
				{
					throw new Exception(voObj.getStrMsgString());
				}
			}
						
		} 
		catch (Exception e) {
				e.printStackTrace();
				String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAttendanceDATA->getServiceType()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
			     
		}
	}

/**
 * to get Service name combo
 * @param formBean - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void getServiceName(PatientAttendanceFB formBean,HttpServletRequest request, HttpServletResponse response) {
	
	PatientAttendanceVO voObj= null;
	PatientAttendanceBO bo= null;
		HisUtil util = null;
		String temp="";
		try 
		 {
			voObj =  new PatientAttendanceVO();
			bo    =  new PatientAttendanceBO();
			
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrServiceTypeId(formBean.getStrServiceTypeId());
			bo.getServiceName(voObj);
			String strStatus=voObj.getStrStatus();
			
			util = new HisUtil("Patient Attendance Transaction",
								"PatientAttendanceDATA");
				temp = util.getOptionValue(voObj.getStrServiceNameWS(), "0",
						"0^Select Value", false);
				temp=temp.concat("@").concat(strStatus);
				if(voObj.getStrMsgType().equals("1"))
				{
					throw new Exception(voObj.getStrMsgString());
				}
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
						
		} 
		catch (Exception e) {
				e.printStackTrace();
				String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientAttendanceDATA->getServiceName()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
			     
		}
	}

/**
 * to get patient list
 * @param formBean - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void getPatientList(HttpServletRequest request,PatientAttendanceFB beanObj,HttpServletResponse response)
{

	PatientAttendanceVO VO = null;
	PatientAttendanceBO bo = null;
		String serviceTypeId = request.getParameter("serviceTypeId");
		String strServiceName = request.getParameter("strServiceName");
		String status = request.getParameter("status");
		String strPatList="";
		/*String strServiceVal=strServiceName.replace("^", "@").split("@")[0];
		String strServiceDept=strServiceName.replace("^", "@").split("@")[1];
		String strServiceUnit=strServiceName.replace("^", "@").split("@")[2];*/
		
		
		String strServiceVal=strServiceName.split("@")[0];
		String strServiceDept=strServiceName.split("@")[1];
		String strServiceUnit=strServiceName.split("@")[2];
		try {
			VO = new PatientAttendanceVO();
			bo = new PatientAttendanceBO();
			VO.setStrHospCode(beanObj.getStrHospCode());
			VO.setStrServiceTypeId(serviceTypeId);
			VO.setStrServiceName(strServiceVal);
			VO.setStrServiceDeptCode(strServiceDept);
			VO.setStrServiceUnitCode(strServiceUnit);
			VO.setStrStatus(status);
			
			bo.getPatientList(VO);
			if (beanObj.getStrMsgType().equals("1")) { 
				throw new Exception(VO.getStrMsgString());
			}
			VO.setStrDeptProperty(beanObj.getStrDeptProperty());
			if(status.equals("1"))
			 strPatList = PatientAttendanceHLP.getPatientListPending(VO);
			else
				strPatList = PatientAttendanceHLP.getPatientList(VO);	

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strPatList);
			
			if (VO.getStrMsgType().equals("1")) { 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException(
					"IPD->Transactions",
					"PatientAttendanceDATA.getPatientList() --> -->", VO
							.getStrMsgString());
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	
}
public static void getPatientList_BS(HttpServletRequest request,PatientAttendanceFB beanObj,HttpServletResponse response)
{

	PatientAttendanceVO VO = null;
	PatientAttendanceBO bo = null;
		String serviceTypeId = request.getParameter("serviceTypeId");
		String strServiceName = request.getParameter("strServiceName");
		String status = request.getParameter("status");
		String strPatList="";
		/*String strServiceVal=strServiceName.replace("^", "@").split("@")[0];
		String strServiceDept=strServiceName.replace("^", "@").split("@")[1];
		String strServiceUnit=strServiceName.replace("^", "@").split("@")[2];*/
		
		
		String strServiceVal=strServiceName.split("@")[0];
		String strServiceDept=strServiceName.split("@")[1];
		String strServiceUnit=strServiceName.split("@")[2];
		try {
			VO = new PatientAttendanceVO();
			bo = new PatientAttendanceBO();
			VO.setStrHospCode(beanObj.getStrHospCode());
			VO.setStrServiceTypeId(serviceTypeId);
			VO.setStrServiceName(strServiceVal);
			VO.setStrServiceDeptCode(strServiceDept);
			VO.setStrServiceUnitCode(strServiceUnit);
			VO.setStrStatus(status);
			
			bo.getPatientList(VO);
			if (beanObj.getStrMsgType().equals("1")) { 
				throw new Exception(VO.getStrMsgString());
			}
			VO.setStrDeptProperty(beanObj.getStrDeptProperty());
			if(status.equals("1"))
			 strPatList = PatientAttendanceHLP.getPatientListPending_BS(VO);
			else
				strPatList = PatientAttendanceHLP.getPatientList_BS(VO);	

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strPatList);
			
			if (VO.getStrMsgType().equals("1")) { 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException(
					"IPD->Transactions",
					"PatientAttendanceDATA.getPatientList() --> -->", VO
							.getStrMsgString());
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	
}

/**
 * to get patient status list for View Process
 * @param formBean - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void getPatientStatusView(HttpServletRequest request,PatientAttendanceFB beanObj,HttpServletResponse response)
{

		PatientAttendanceVO VO = null;
		PatientAttendanceBO bo = null;
		String serviceTypeId = request.getParameter("serviceTypeId");
		String strServiceName = request.getParameter("strServiceName");
		String strEffectiveFrom = request.getParameter("strEffectiveFrom");
		String strEffectiveTo = request.getParameter("strEffectiveTo");
		String strPatList="";
		String strServiceVal=strServiceName.replace("^", "@").split("@")[0];
		String strServiceDept=strServiceName.replace("^", "@").split("@")[1];
		String strServiceUnit=strServiceName.replace("^", "@").split("@")[2];
		
		try {
			VO = new PatientAttendanceVO();
			bo = new PatientAttendanceBO();
			VO.setStrHospCode(beanObj.getStrHospCode());
			VO.setStrServiceTypeId(serviceTypeId);
			VO.setStrServiceName(strServiceVal);
			VO.setStrServiceDeptCode(strServiceDept);
			VO.setStrServiceUnitCode(strServiceUnit);
			VO.setStrEffectiveFrom(strEffectiveFrom);
			VO.setStrEffectiveTo(strEffectiveTo);
			
			bo.getPatientStatusView(VO);
			if (beanObj.getStrMsgType().equals("1")) { 
				throw new Exception(VO.getStrMsgString());
			}
			
				strPatList = PatientAttendanceHLP.getPatientStatusView(VO);	
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strPatList);
			
			if (VO.getStrMsgType().equals("1")) { 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException(
					"IPD->Transactions",
					"PatientAttendanceDATA.getPatientStatusView() --> -->", VO
							.getStrMsgString());
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	
}

public static void getPatientStatusView_BS(HttpServletRequest request,PatientAttendanceFB beanObj,HttpServletResponse response)
{

		PatientAttendanceVO VO = null;
		PatientAttendanceBO bo = null;
		String serviceTypeId = request.getParameter("serviceTypeId");
		String strServiceName = request.getParameter("strServiceName");
		String strEffectiveFrom = request.getParameter("strEffectiveFrom");
		String strEffectiveTo = request.getParameter("strEffectiveTo");
		String strPatList="";
		String strServiceVal=strServiceName.replace("^", "@").split("@")[0];
		String strServiceDept=strServiceName.replace("^", "@").split("@")[1];
		String strServiceUnit=strServiceName.replace("^", "@").split("@")[2];
		
		try {
			VO = new PatientAttendanceVO();
			bo = new PatientAttendanceBO();
			VO.setStrHospCode(beanObj.getStrHospCode());
			VO.setStrServiceTypeId(serviceTypeId);
			VO.setStrServiceName(strServiceVal);
			VO.setStrServiceDeptCode(strServiceDept);
			VO.setStrServiceUnitCode(strServiceUnit);
			VO.setStrEffectiveFrom(strEffectiveFrom);
			VO.setStrEffectiveTo(strEffectiveTo);
			
			bo.getPatientStatusView(VO);
			if (beanObj.getStrMsgType().equals("1")) { 
				throw new Exception(VO.getStrMsgString());
			}
			
				strPatList = PatientAttendanceHLP.getPatientStatusView_BS(VO);	
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strPatList);
			
			if (VO.getStrMsgType().equals("1")) { 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());

		} catch (Exception e) {
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException(
					"IPD->Transactions",
					"PatientAttendanceDATA.getPatientStatusView() --> -->", VO
							.getStrMsgString());
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	
}

/**
 * to get department combo
 * @param beanObj - object of  ActionForm
 * 
 */
public static void department(PatientAttendanceFB beanObj) {

	PatientAttendanceVO VO = null;
	HisUtil util = null;
	try {
		VO = new PatientAttendanceVO();
		VO = (PatientAttendanceVO) TransferObjectFactory.populateData(
				"ipd.transactions.PatientAttendanceVO", beanObj);
		PatientAttendanceBO bo = new PatientAttendanceBO();
		VO.setStrHospCode(beanObj.getStrHospCode());
		VO.setStrSeatId(beanObj.getStrSeatId());
		bo.department(VO);
		beanObj.setStrMsgType(VO.getStrMsgType());
		if (beanObj.getStrMsgType().equals("1")) {
			beanObj.setStrErrMsgString(VO.getStrErrMsgString());
			throw new Exception(beanObj.getStrErrMsgString());   
		} else {
			util = new HisUtil("ipd", "PatientAttendanceDATA");
			String strdept = util.getOptionValue(VO.getStrDepartmentWs(),
					"0", "0^Select Value", false);
			beanObj.setStrDeptProperty(strdept);
		}
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		HisException eObj = new HisException("IPD",
				"PatientAttendanceDATA->department()", strmsgText);
		beanObj.setStrErrMsgString("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "], Contact System Administrator! ");
		eObj = null;
	} finally {
		if (VO != null)
			VO = null;
		if (beanObj != null)
			beanObj = null;
	}
}

/**
 * to get unit combo
 * @param beanObj - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void unit(PatientAttendanceFB beanObj,
		HttpServletRequest request, HttpServletResponse response) {
	PatientAttendanceVO VO = null;

	HisUtil util = null;
	String strunit = "";
	try {
		
		String modDept = request.getParameter("modDept");
		VO = new PatientAttendanceVO();
		VO = (PatientAttendanceVO) TransferObjectFactory.populateData(
				"ipd.transactions.PatientAttendanceVO", beanObj);
		PatientAttendanceBO bo = new PatientAttendanceBO();
		VO.setStrDepartment(modDept);
		bo.unit(VO);
		beanObj.setStrMsgType(VO.getStrMsgType());
		util = new HisUtil("ipd", "PatientAttendanceDATA");
		strunit = util.getOptionValue(VO.getStrUnitWs(), "0",
				"0^Select Value", false);
		if (beanObj.getStrMsgType().equals("1")) {
			beanObj.setStrErrMsgString(VO.getStrErrMsgString());
			throw new Exception(beanObj.getStrErrMsgString());
		} else {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strunit);
			
		}
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		HisException eObj = new HisException("IPD",
				"PatientAttendanceDATA->unit()", strmsgText);
		beanObj.setStrErrMsgString("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "], Contact System Administrator! ");
		eObj = null;
	} finally {
		if (VO != null)
			VO = null;
		if (beanObj != null)
			beanObj = null;
	}
}

/**
 * to get ward combo
 * @param beanObj - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void ward(PatientAttendanceFB beanObj,
		HttpServletRequest request, HttpServletResponse response) {

	PatientAttendanceVO VO = new PatientAttendanceVO();
	String modWardTpe = "";
	String temp[] = null;
	try {

		modWardTpe = request.getParameter("modWard");
		temp = modWardTpe.replace('^', '#').split("#");
		VO = (PatientAttendanceVO) TransferObjectFactory.populateData(
				"ipd.transactions.PatientAttendanceVO", beanObj);
		PatientAttendanceBO bo = new PatientAttendanceBO();
		VO.setStrDeptCode(temp[0]);
		VO.setStrDeptUnitCode(temp[1]);
		VO.setStrAge(request.getParameter("age"));
		VO.setStrAgeCode(request.getParameter("ageCode"));
		VO.setStrGenderCode(request.getParameter("sexCode"));
		VO.setStrTreatmentCat(request.getParameter("treatmentCategCode"));
		VO.setStrCrNo(request.getParameter("crNo"));
		bo.ward(VO);
		beanObj.setStrMsgType(VO.getStrMsgType());
		if (beanObj.getStrMsgType().equals("1")) {
			beanObj.setStrErrMsgString(VO.getStrErrMsgString()); 
			throw new Exception(beanObj.getStrErrMsgString());
		} else {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(VO.getStrWard());
		}
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		HisException eObj = new HisException("IPD",
				"PatientAttendanceDATA->ward()", strmsgText);
		beanObj.setStrErrMsgString("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "], Contact System Administrator! ");
		eObj = null;
	} finally {
		if (VO != null)
			VO = null;
		if (beanObj != null)
			beanObj = null;
	}
}

/**
 * to get room combo
 * @param beanObj - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void room(PatientAttendanceFB beanObj,
		HttpServletRequest request, HttpServletResponse response) {

	PatientAttendanceVO VO = new PatientAttendanceVO();
	String modWardTpe = "";
	String temp[] = null;
	try {

		modWardTpe = request.getParameter("modWard");
		temp = modWardTpe.replace('^', '#').split("#");
		VO = (PatientAttendanceVO) TransferObjectFactory.populateData("ipd.transactions.PatientAttendanceVO", beanObj);
		PatientAttendanceBO bo = new PatientAttendanceBO();
		VO.setStrDeptCode(temp[0]);
		VO.setStrDeptUnitCode(temp[1]);
		VO.setStrWard(temp[2]);
		VO.setStrAge(request.getParameter("age"));
		VO.setStrAgeCode(request.getParameter("ageCode"));
		VO.setStrGenderCode(request.getParameter("sexCode"));
		VO.setStrTreatmentCat(request.getParameter("treatmentCategCode"));
		VO.setStrCrNo(request.getParameter("crNo"));
		bo.room(VO);
		beanObj.setStrMsgType(VO.getStrMsgType());
		if (beanObj.getStrMsgType().equals("1")) {
			beanObj.setStrErrMsgString(VO.getStrErrMsgString()); 
			throw new Exception(beanObj.getStrErrMsgString());
		} else {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(VO.getStrRoom());
		}
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		HisException eObj = new HisException("IPD",
				"PatientAttendanceDATA->room()", strmsgText);
		beanObj.setStrErrMsgString("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "], Contact System Administrator! ");
		eObj = null;
	} finally {
		if (VO != null)
			VO = null;
		if (beanObj != null)
			beanObj = null;
	}
}

/**
 * to transfer patient back to ADT
 * @param beanObj - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void transfer(PatientAttendanceFB beanObj,
		HttpServletRequest request, HttpServletResponse response) {
	PatientAttendanceVO VO = null;

	String chk =beanObj.getStrChk();
	String crNo=chk.replace("^", "@").split("@")[0];
	String admNo=chk.replace("^", "@").split("@")[1];
	String movNo=chk.replace("^", "@").split("@")[2];
	try {
		

		VO = new PatientAttendanceVO();
		VO = (PatientAttendanceVO) TransferObjectFactory.populateData("ipd.transactions.PatientAttendanceVO", beanObj);
		PatientAttendanceBO bo = new PatientAttendanceBO();
		VO.setStrAdmNo(admNo);
		VO.setStrCrNo(crNo);
		VO.setStrMovNo(movNo);
		bo.transfer(VO);
		beanObj.setStrMsgType(VO.getStrMsgType());
		
		if (beanObj.getStrMsgType().equals("1")) {
			beanObj.setStrErrMsgString(VO.getStrErrMsgString());
			throw new Exception(beanObj.getStrErrMsgString());
		} 
		else{
			System.out.println("TRANSFERRED SUCCESSFULLY.");
			beanObj.setStrErrMsgString("Patient Transferred Successfully.");
		}
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		HisException eObj = new HisException("IPD",
				"PatientAttendanceDATA->transfer()", strmsgText);
		beanObj.setStrErrMsgString("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "], Contact System Administrator! ");
		eObj = null;
	} finally {
		if (VO != null)
			VO = null;
		if (beanObj != null)
			beanObj = null;
	}
}

/**
 * to accept patient 
 * @param beanObj - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void accept(HttpServletRequest request,PatientAttendanceFB beanObj,HttpServletResponse response)
{
    String strRetVal="0";
	PatientAttendanceVO VO = null;
	PatientAttendanceBO bo = null;
		String chk = request.getParameter("chk");
		String control = request.getParameter("control");
		/*String crNo=chk.replace("^", "@").split("@")[0];
		String admNo=chk.replace("^", "@").split("@")[1];
		String movNo=chk.replace("^", "@").split("@")[2];*/
		
		String crNo=chk.split("@")[0];
		String admNo=chk.split("@")[1];
		String movNo=chk.split("@")[2];
		
		try {
			VO = new PatientAttendanceVO();
			bo = new PatientAttendanceBO();
			VO.setStrHospCode(beanObj.getStrHospCode());
			VO.setStrSeatId(beanObj.getStrSeatId());
			VO.setStrCrNo(crNo);
			VO.setStrAdmNo(admNo);
			VO.setStrMovNo(movNo);
			
			bo.accept(VO);
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
			
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());
				throw new Exception(beanObj.getStrErrMsgString());
			} 
			else{
				if(control.equals("0"))
				{
				System.out.println("ACCEPTED SUCCESSFULLY.");
				strRetVal="1";
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strRetVal);
				}
				else
				{
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strRetVal);
				}
			}

		} catch (Exception e) {	
			String strmsgText = e.getMessage();
			strRetVal="ERROR";
			HisException eObj = new HisException("IPD",
					"PatientAttendanceDATA->accept()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().print(strRetVal);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (beanObj != null)
				beanObj = null;
		}
	
}	

/**
 * to reject patient
 * @param beanObj - object of  ActionForm
 * @param request - HttpServletRequest
 * @param response - HttpServletResponse
 * 
 */
public static void reject(HttpServletRequest request,PatientAttendanceFB beanObj,HttpServletResponse response)
{
    String strRetVal="0";
	PatientAttendanceVO VO = null;
	PatientAttendanceBO bo = null;
		String chk = request.getParameter("chk");
		String control = request.getParameter("control");
		String crNo=chk.replace("^", "@").split("@")[0];
		String admNo=chk.replace("^", "@").split("@")[1];
		String movNo=chk.replace("^", "@").split("@")[2];
		String strTransferredFrom=chk.replace("^", "@").split("@")[4];
		String strDept=strTransferredFrom.replace("$", "#").split("#")[0];
		String strUnit=strTransferredFrom.replace("$", "#").split("#")[1];
		String strWard=strTransferredFrom.replace("$", "#").split("#")[2];
		String strRoom=strTransferredFrom.replace("$", "#").split("#")[3];
		String strBed=strTransferredFrom.replace("$", "#").split("#")[4];
		try {
			VO = new PatientAttendanceVO();
			bo = new PatientAttendanceBO();
			VO.setStrHospCode(beanObj.getStrHospCode());
			VO.setStrSeatId(beanObj.getStrSeatId());
			VO.setStrCrNo(crNo);
			VO.setStrAdmNo(admNo);
			VO.setStrMovNo(movNo);
			VO.setStrDepartment(strDept);
			VO.setStrUnit(strUnit);
			VO.setStrWard(strWard);
			VO.setStrRoom(strRoom);
			VO.setStrBed(strBed);
			
			bo.reject(VO);
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
			
			if (beanObj.getStrMsgType().equals("1")) {
				beanObj.setStrErrMsgString(VO.getStrErrMsgString());
				throw new Exception(beanObj.getStrErrMsgString());
			} 
			else{
				if(control.equals("0"))
				{
				System.out.println("REJECTED SUCCESSFULLY.");
				strRetVal="1";
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strRetVal);
				}
				else
				{
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strRetVal);
				}
				
			}

		} catch (Exception e) {	
			String strmsgText = e.getMessage();
			strRetVal="ERROR";
			HisException eObj = new HisException("IPD",
					"PatientAttendanceDATA->reject()", strmsgText);
			beanObj.setStrErrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().print(strRetVal);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			eObj = null;
		} finally {
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
			if (beanObj != null)
				beanObj = null;
		}
	
}

public static void getBedBlkHrs(HttpServletRequest req,HttpServletResponse res) {
	// TODO Auto-generated method stub
	//PatBelongingTransBO bo=null;
	PatientAttendanceBO bo=null;
	try{
		String obj=req.getParameter("obj");
		String temp=obj;
		String wardvals=obj.replace("^", "#").split("#")[4];
		String i=req.getParameter("index");
		obj=obj.replace("^", "#").split("#")[0]+"^"+obj.replace("^", "#").split("#")[1]+"^"+req.getSession().getAttribute("HOSPITAL_CODE").toString()+"^"+obj.replace("^", "#").split("#")[2]+"^"+wardvals.replace("$","#").split("#")[2];
		
	    bo=new PatientAttendanceBO();
	    
		String blkhrs= bo.getBedBlkHrs(obj);
		res.setHeader("Cache-Control", "no-cache");
		res.getWriter().print(blkhrs+"-"+temp+"-"+i+"");
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
}
