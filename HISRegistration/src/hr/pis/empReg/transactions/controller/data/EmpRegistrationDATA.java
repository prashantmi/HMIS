package hr.pis.empReg.transactions.controller.data;


import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerDATA;
import hr.pis.empReg.bo.transactions.EmpRegistrationBO;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;




public class EmpRegistrationDATA extends ControllerDATA {

 /**
 * gets New Registration Essentials
 * calls getNewPatientRegEssential from EssentialDelegate
 * @param _UserVO Provides User details.
 * @return map containing new patient registration essentials
 */
	
	public static Map getRegistrationFormEssentials_AJAX(UserVO _UserVO)
	{
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		Map mp=objBO.getRegistrationFormEssentials_AJAX(_UserVO);
		
		return mp;		
	}
	
	/*public static List getDeptDesigFinalStatus_AJAX(UserVO _UserVO, String NatureOfJobId_p)
	{
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getDeptDesigFinalStatus_AJAX(_UserVO,NatureOfJobId_p);
	}*/
	
	public static Map getDeptDesigFinalStatus_AJAX(UserVO _UserVO, String NatureOfJobId_p)
	{
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getDeptDesigFinalStatus_AJAX(_UserVO,NatureOfJobId_p);
	}
	
	
	
	/*public static List getDesig_AJAX(UserVO _UserVO, String NatureOfJobId_p)
	{
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getDesig_AJAX(_UserVO,NatureOfJobId_p);
	}
	
	
	public static List getfinalStatus_AJAX(UserVO _UserVO, String NatureOfJobId_p)
	{
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getfinalStatus_AJAX(_UserVO,NatureOfJobId_p);
	}
	*/
	
	
	
	/**
	 * registers new employee 
	 * @param objPatVO_p Provides employee details.
	 * @param objUserVO_p Provides User details.
	 */
	public static void registerNewEmployee(EmpRegistrationVO objEmpVO_p, UserVO objUserVO_p, HttpServletRequest  objRequest_p) {
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		objBO.newEmployeeRegistration(objEmpVO_p, objUserVO_p, objRequest_p);
	}
	
	/**
	 * Update employee Details 
	 * @param objPatVO_p Provides employee details.
	 * @param objUserVO_p Provides User details.
	 */
	public static void updateEmpDtl(EmpRegistrationVO objEmpVO_p, UserVO objUserVO_p, HttpServletRequest  objRequest_p) {
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		objBO.updateEmpDtl(objEmpVO_p, objUserVO_p, objRequest_p);
	}
	
	/**
	 * Single Validation employee Details 
	 * @param objPatVO_p Provides employee details.
	 * @param objUserVO_p Provides User details.
	 */
	public static void validateEmployeeRegistrationDetails(EmpRegistrationVO objEmpVO_p, UserVO objUserVO_p, HttpServletRequest  objRequest_p) {
		EmpRegistrationBO objEmployeeBO = new EmpRegistrationBO(); 
		String tempEmpNos = objEmpVO_p.getStrEmployeeNumber();
		List<String> empList = Arrays.asList(objEmpVO_p.getStrEmployeeNumber().split(","));
		for(int i=0;i<empList.size();i++)
		{
			objEmpVO_p.setStrEmployeeNumber(empList.get(i).toString());
			objEmployeeBO.validateEmployeeRegistrationDetails(objEmpVO_p, objUserVO_p, objRequest_p);
		}
		objEmpVO_p.setStrEmployeeNumber(tempEmpNos);
		
	}
	
	/**
	 * Delete employee Details 
	 * @param objPatVO_p Provides employee details.
	 * @param objUserVO_p Provides User details.
	 */
	public static void deleteEmpDtl(EmpRegistrationVO objEmpVO_p, UserVO objUserVO_p, HttpServletRequest  objRequest_p) {
		EmpRegistrationBO objEmployeeBO = new EmpRegistrationBO(); 
		objEmployeeBO.deleteEmpDtl(objEmpVO_p, objUserVO_p, objRequest_p);
	}
	
	public static List getEmpDtl(UserVO userVO_p, String searchEmpName,String searchEmpNo, String strMode_p) {
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getEmpDtl(userVO_p, searchEmpName, searchEmpNo,strMode_p);
	}
	
	public static List validateGuestLoginDtl(UserVO userVO_p, String guestModifyEmpNo,String guestModifyDoB, String strMode_p) {
		EmpRegistrationBO  objBO=new EmpRegistrationBO();
		return objBO.validateGuestLoginDtl(userVO_p, guestModifyEmpNo, guestModifyDoB,strMode_p);
	}
	
	public static List getEmpDtlForValidation(UserVO userVO_p, String empValidationStatus,String empNumber, String strMode_p) {
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getEmpDtlForValidation(userVO_p, empValidationStatus, empNumber,strMode_p);
	}
	
	public static List getReportData(UserVO userVO_p, String empValidationStatus, String strMode_p , String filterCondition) {
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getReportData(userVO_p, empValidationStatus, strMode_p , filterCondition);
	}
	/*
	public static List getValidatorDetails(UserVO userVO_p, String strMode_p) {
		EmpRegistrationBO objBO = new EmpRegistrationBO(); 
		return objBO.getValidatorDetails(userVO_p, strMode_p);
	}
	*/
	public static Map<String, Object> getPendingEmployeeList_AJAX(UserVO _UserVO, int p_page,int p_limit,String p_sidx, String p_sord, String p_where)
	{
		EmpRegistrationBO  objEmployeeBO=new EmpRegistrationBO();
		return objEmployeeBO.getPendingEmployeeList_AJAX(_UserVO, p_page, p_limit, p_sidx, p_sord, p_where);
		
	}
	
	public static Map<String, Object> getValidatedEmployeeList_AJAX(UserVO _UserVO, int p_page,int p_limit,String p_sidx, String p_sord, String p_where)
	{
		EmpRegistrationBO  objEmployeeBO=new EmpRegistrationBO();
		return objEmployeeBO.getValidatedEmployeeList_AJAX(_UserVO, p_page, p_limit, p_sidx, p_sord, p_where);
		
	}
	
	public static Map<String, Object> getRejectedEmployeeList_AJAX(UserVO _UserVO, int p_page,int p_limit,String p_sidx, String p_sord, String p_where)
	{
		EmpRegistrationBO  objEmployeeBO=new EmpRegistrationBO();
		return objEmployeeBO.getRejectedEmployeeList_AJAX(_UserVO, p_page, p_limit, p_sidx, p_sord, p_where);
		
	}
	
	public static EmpRegistrationVO getEmployeeDataForPrint(EmpRegistrationVO vo, UserVO userVO_p)
	{
		System.out.println("EmployeeRegistrationDATA>>>  getEmployeeDataForPrint");
		EmpRegistrationVO EmpRegVO_p = null;
		EmpRegistrationBO  objBO=new EmpRegistrationBO();
		try
		{
			EmpRegVO_p=objBO.modifyRecord(vo, userVO_p);

			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("PisMasterBO.modifyRecord(vo) --> " + vo.getStrMsgString());
			}
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			throw new HisApplicationExecutionException();
		}
		finally
		{
		}
		return EmpRegVO_p;
	}
	
	
}