package hr.pis.empReg.bo.transactions;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.UserVO;
import hr.pis.config.PisConfig;
import hr.pis.config.PisEssentialsDAO;
import hr.pis.empReg.config.PrConfig;
import hr.pis.empReg.dao.transactions.EmpRegistrationDAO;
import hr.pis.empReg.dao.transactions.EmployeeEssentialDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;


/**
 * PatientBO is a class which specifies the business logic for all the transactions. PatientBO class provides a standard
 * interface between Controller and Data Access Objects.
 * 
 * @author AHIS*
 */

public class EmpRegistrationBO 
{
	
	
	public Map getRegistrationFormEssentials_AJAX(UserVO _userVO) 
	{
		Map essentialMap = new HashMap();
		
		List lstDepartment = null;
		List lstNatureOfJOb = null;
		List lstAppellation1 = null;
		List lstAppellation2 = null;
		List lstSuffix = null;
		List lstNationality = null;
		List lstGender = null;
		List lstServiceGroup = null;
		List lstDesignation = null;
		List lstStatus = null;
		List lstFinalStatus = null;
		List lstLastEmploymentType = null;
		List lstEventName = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			PisEssentialsDAO objJoiningEssentialDAO = new PisEssentialsDAO(tx);
			
			//String natureOfJob=NatureOfJobId;
			
			//System.out.println("Nature of job in BO->"+natureOfJob);
			
			
			/*try
			{

				if(natureOfJob.equals("5"))
				{
					lstDepartment = objJoiningEssentialDAO.getDepartment("1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				}
				else{
				
				lstDepartment = objJoiningEssentialDAO.getDepartment("2","",_userVO,"","");
				essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				}
				
				
				
			}
			
			catch(Exception e)
			
			{
				
				lstDepartment = objJoiningEssentialDAO.getDepartment("2","",_userVO,"","");
				essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				
			}*/
			
			lstDepartment = objJoiningEssentialDAO.getDepartment("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			
			
			lstNatureOfJOb = objJoiningEssentialDAO.getNatureOfJob("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB,lstNatureOfJOb);
			
			lstAppellation1 = objJoiningEssentialDAO.getAppellation("2","",_userVO,"1","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION1,lstAppellation1);
			
			lstAppellation2 = objJoiningEssentialDAO.getAppellation("2","",_userVO,"2","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_APPELLATION2,lstAppellation2);
			
			lstSuffix = objJoiningEssentialDAO.getSuffix("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_SUFFIX,lstSuffix);
			
			lstNationality = objJoiningEssentialDAO.getNationality("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_NATIONALITY,lstNationality);
			
			lstGender = objJoiningEssentialDAO.getGender("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_GENDER,lstGender);
			
			lstServiceGroup = objJoiningEssentialDAO.getSerGrp("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_SERVICE_GROUP,lstServiceGroup);
			
			lstDesignation = objJoiningEssentialDAO.getDesignation("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
			
			lstStatus = objJoiningEssentialDAO.getStatus("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_STATUS,lstStatus);
			
			lstFinalStatus = objJoiningEssentialDAO.getFinalStatus("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS,lstFinalStatus);
			
			lstLastEmploymentType = objJoiningEssentialDAO.getLastEmploymentType("1","",_userVO,"","");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_LAST_EMPLOYMENT_TYPE,lstLastEmploymentType);
			
			//lstEventName = objJoiningEssentialDAO.getEvent("1","",_userVO,"","");
			//essentialMap.put(PisConfig.OPTIONS_EVENT_LIST,lstEventName);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	
	public Map getDeptDesigFinalStatus_AJAX( UserVO _userVO, String NatureOfJobId_p) 
	{		
		List lstDepartment = null;
		List lstDesignation=null;
		List lstFinalStatus = null;
		Map essentialMap = new HashMap();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			/*EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			lstState = objEmpDAO.getNatureOfJob(mode,"",_userVO,NatureOfJobId_p);*/
			
			PisEssentialsDAO objJoiningEssentialDAO = new PisEssentialsDAO(tx);
			String natureOfJob =NatureOfJobId_p;
		
				if(natureOfJob.equalsIgnoreCase("Outsource"))
				{
					lstDepartment = objJoiningEssentialDAO.getDepartment("1_1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
					
					lstDesignation = objJoiningEssentialDAO.getDesignation("1_1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
					
					lstFinalStatus = objJoiningEssentialDAO.getFinalStatus("1_1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS,lstFinalStatus);
				}
				else if(natureOfJob.equals("Adhoc"))
				{
					lstDepartment = objJoiningEssentialDAO.getDepartment("1_2","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
					
					lstDesignation = objJoiningEssentialDAO.getDesignation("1_2","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
					
					lstFinalStatus = objJoiningEssentialDAO.getFinalStatus("1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS,lstFinalStatus);
				}
				
				else if (natureOfJob.equals("Student"))
				{
					lstDepartment = objJoiningEssentialDAO.getDepartment("1_3","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);				
					
					
					lstDesignation = objJoiningEssentialDAO.getDesignation("1_3","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
					
					lstFinalStatus = objJoiningEssentialDAO.getFinalStatus("1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS,lstFinalStatus);
				}
				
				else{
				
				lstDepartment = objJoiningEssentialDAO.getDepartment("1","",_userVO,"","");
				essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				
				lstDesignation = objJoiningEssentialDAO.getDesignation("1","",_userVO,"","");
				essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
				
				lstFinalStatus = objJoiningEssentialDAO.getFinalStatus("1","",_userVO,"","");
				essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS,lstFinalStatus);
				}
		
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	/*public List getDesig_AJAX( UserVO _userVO, String NatureOfJobId_p) 
	{		
		List lstDepartment = null;
		List lstDesignation=null;
		Map essentialMap = new HashMap();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			lstState = objEmpDAO.getNatureOfJob(mode,"",_userVO,NatureOfJobId_p);
			
			PisEssentialsDAO objJoiningEssentialDAO = new PisEssentialsDAO(tx);
			String natureOfJob =NatureOfJobId_p;
		
				if(natureOfJob.equals("5"))
				{
					
					lstDesignation = objJoiningEssentialDAO.getDesignation("1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
				}
				
			   else if(natureOfJob.equals("3"))
			     {
					lstDesignation = objJoiningEssentialDAO.getDesignation("1_1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
				}
				
			   else if(natureOfJob.equals("6"))
			     {
					lstDesignation = objJoiningEssentialDAO.getDesignation("1_2","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
				}
			   else{
				
				lstDesignation = objJoiningEssentialDAO.getDesignation("6","",_userVO,"","");
				essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION,lstDesignation);
				}
		
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstDepartment;
	}
	
	public List getfinalStatus_AJAX( UserVO _userVO, String NatureOfJobId_p) 
	{		
		List lstDepartment = null;
		List lstDesignation=null;
		List lstFinalStatus=null;
		Map essentialMap = new HashMap();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			lstState = objEmpDAO.getNatureOfJob(mode,"",_userVO,NatureOfJobId_p);
			
			PisEssentialsDAO objJoiningEssentialDAO = new PisEssentialsDAO(tx);
			String natureOfJob =NatureOfJobId_p;
		
				if(natureOfJob.equals("5"))
				{
					
					lstFinalStatus = objJoiningEssentialDAO.getFinalStatus("1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS,lstFinalStatus);
				}
				else{
				
					lstFinalStatus = objJoiningEssentialDAO.getFinalStatus("1_1","",_userVO,"","");
					essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS,lstFinalStatus);
				}
		
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstDepartment;
	}
	
	*/
	
	
	
	
	public void newEmployeeRegistration(EmpRegistrationVO objEmployeeVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p) throws HisDuplicateRecordException
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		boolean isDuplicateReg=false;
		String[] empNoArr = null;
		String duplicasyCheckMode = "1";
		//synchronized (PatientDAO.class)
		//{
		try
		{
			objHisDAO = new HisDAO("Pis","EmployeeBO");
			
			tx.begin();
			Map essentialMap = new HashMap();

			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			EmployeeEssentialDAO objEmpRegEssentialDAO = new EmployeeEssentialDAO(tx);
			
			//generating the Employee Number
			String employeeNumber=objEmpDAO.generateEmpNumber(objEmployeeVO_p, objUserVO_p);
			objEmployeeVO_p.setStrEmployeeNumber(employeeNumber);
			
			// Checking duplicate employee number
			if(objEmpRegEssentialDAO.checkDuplicateEmployeeNumber(objEmployeeVO_p, objUserVO_p))
			{	
				WebUTIL.setMapInSession(essentialMap, objRequest_p);
				throw new HisDuplicateRecordException("Duplicate Emp. No. Exist");
			}
			
			// Checking duplicate old employee number
			if(objEmpRegEssentialDAO.checkDuplicateOldEmployeeNumber(objEmployeeVO_p, objUserVO_p))
			{	
				WebUTIL.setMapInSession(essentialMap, objRequest_p);
				throw new HisDuplicateRecordException("Duplicate Old Emp. No. Exist");
			}
			
			// Checking duplicate PAN card number only if PAN Card Number is not 'NA'
			System.out.println("PAN No - "+objEmployeeVO_p.getStrPANNumber());
			if(objEmployeeVO_p.getStrPANNumber().equalsIgnoreCase("NA"))
			{
				// Do nothing
			}
			else
			{
				if(objEmpRegEssentialDAO.checkDuplicatePANCardNumber(objEmployeeVO_p, objUserVO_p, duplicasyCheckMode))
				{	
					WebUTIL.setMapInSession(essentialMap, objRequest_p);
					throw new HisDuplicateRecordException("Duplicate PAN Card No. Exist");
				}
			}
			
			//insert into employee
			objEmpDAO.saveEmployeeDtl(objHisDAO,objEmployeeVO_p, objUserVO_p,"1");
			
			/***********************************************/	
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
			
		}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			System.out.println("Message in Duplicate Record = "+e.getMessage());	
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
			
		}
		//}
		
	}

	public void updateEmpDtl(EmpRegistrationVO objEmployeeVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p) throws HisDuplicateRecordException
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		boolean isDuplicateReg=false;
		String[] empNoArr = null;
		String duplicasyCheckMode = "2";
		try
		{
			objHisDAO = new HisDAO("Pis","EmployeeBO");
			tx.begin();
			Map essentialMap = new HashMap();

			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			EmployeeEssentialDAO objEmpRegEssentialDAO = new EmployeeEssentialDAO(tx);
			
			//generating the Employee Number
			//String employeeNumber=objEmpDAO.generateEmpNumber(objEmployeeVO_p, objUserVO_p);
			//objEmployeeVO_p.setStrEmployeeNumber(employeeNumber);
			
			// Checking duplicate employee number
			//if(objEmpRegEssentialDAO.checkDuplicateEmployeeNumber(objEmployeeVO_p, objUserVO_p))
			//{	
				//WebUTIL.setMapInSession(essentialMap, objRequest_p);
				//throw new HisDuplicateRecordException("Duplicate Employee Number Exist");
			//}
			
			// Checking duplicate old employee number
			//if(objEmpRegEssentialDAO.checkDuplicateOldEmployeeNumber(objEmployeeVO_p, objUserVO_p))
			//{	
				//WebUTIL.setMapInSession(essentialMap, objRequest_p);
				//throw new HisDuplicateRecordException("Duplicate Old Employee Number Exist");
			//}
			
			// Checking duplicate PAN card number only if PAN Card Number is not 'NA'
			System.out.println("PAN No - "+objEmployeeVO_p.getStrPANNumber());
			if(objEmployeeVO_p.getStrPANNumber().equalsIgnoreCase("NA"))
			{
				// Do nothing
			}
			else
			{
				if(objEmpRegEssentialDAO.checkDuplicatePANCardNumber(objEmployeeVO_p, objUserVO_p, duplicasyCheckMode))
				{	
					WebUTIL.setMapInSession(essentialMap, objRequest_p);
					throw new HisDuplicateRecordException("Duplicate PAN Card Number Exist");
				}
			}
			
			//Update Employee Details
			objEmpDAO.saveEmployeeDtl(objHisDAO,objEmployeeVO_p, objUserVO_p,"2");
			
			/***********************************************/	
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
			
		}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			System.out.println("Message in Duplicate Record = "+e.getMessage());	
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
			
		}
		//}
		
	}
	
	public void validateEmployeeRegistrationDetails(EmpRegistrationVO objEmployeeVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p) throws HisDuplicateRecordException
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		boolean isDuplicateReg=false;
		String[] empNoArr = null;
		try
		{
			objHisDAO = new HisDAO("Pis","EmployeeBO");
			tx.begin();
			Map essentialMap = new HashMap();

			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			EmployeeEssentialDAO objEmpRegEssentialDAO = new EmployeeEssentialDAO(tx);
			
			//Update Employee Details
			boolean bExistStatus = objEmpDAO.validateEmpRegDtl(objEmployeeVO_p, objHisDAO, objUserVO_p,"1");
			
			/***********************************************/	
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
			
		}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			System.out.println("Message in Duplicate Record = "+e.getMessage());	
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
			
		}
		//}
		
	}
	
	public void deleteEmpDtl(EmpRegistrationVO objEmployeeVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p) throws HisDuplicateRecordException
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		boolean isDuplicateReg=false;
		String[] empNoArr = null;
		try
		{
			objHisDAO = new HisDAO("Pis","EmployeeBO");
			tx.begin();
			Map essentialMap = new HashMap();

			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			EmployeeEssentialDAO objEmpRegEssentialDAO = new EmployeeEssentialDAO(tx);
			
			//Delete Employee Details
			objEmpDAO.saveEmployeeDtl(objHisDAO,objEmployeeVO_p, objUserVO_p,"3");
			
			/***********************************************/	
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
			
		}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			System.out.println("Message in Duplicate Record = "+e.getMessage());	
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
			
		}
		//}
		
	}
	
	public List getEmpDtl(UserVO userVO_p, String searchEmpName, String searchEmpNo, String strMode_p)
	{
		
		List lstEmployeeJsonObjString=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objDAO = new EmpRegistrationDAO(tx);			
			lstEmployeeJsonObjString = objDAO.getEmpDtl(userVO_p, searchEmpName, searchEmpNo, strMode_p);
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstEmployeeJsonObjString;
	}
	
	public List validateGuestLoginDtl(UserVO userVO_p, String guestModifyEmpNo, String guestModifyDoB, String strMode_p)
	{
		
		List lstEmployeeJsonObjString=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmployeeEssentialDAO objEssentialDAO = new EmployeeEssentialDAO(tx);
			
			lstEmployeeJsonObjString = objEssentialDAO.validateGuestLoginDtl(userVO_p, guestModifyEmpNo, guestModifyDoB, strMode_p);
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstEmployeeJsonObjString;
	}
	
	
	
	public EmpRegistrationVO modifyRecord(EmpRegistrationVO vo, UserVO userVO_p) {
		System.out.println("EmployeeRegistrationBO>> EmployeeRegistrationVO");
		EmpRegistrationVO objVO_p = null;
		try 
		{
			HisDAO hisDAO = new HisDAO("Pis", "EmployeeRegistrationBO");
			objVO_p = EmpRegistrationDAO.modifyRecord(vo, hisDAO, userVO_p);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("EmployeeRegEssentialBO.modifyRecord(vo) --> "
						+ vo.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return objVO_p;
	}
	
	
	public boolean validateEmpRegDtl(EmpRegistrationVO objVOEmpReg_p, String strMode_p, UserVO uservo) 
	{
		HisDAO hisDAO = new HisDAO("Pis", "EmployeeRegEssentialBO");

		hisDAO = new HisDAO("Pis", "EmployeeRegEssentialBO");
		// boolean bExistStatus = true;
		boolean bExistStatus = EmpRegistrationDAO.validateEmpRegDtl(objVOEmpReg_p, hisDAO, uservo, strMode_p);

		if (objVOEmpReg_p.getStrMsgType() != null && objVOEmpReg_p.getStrMsgType().equals("1")) 
		{
				String strErr = objVOEmpReg_p.getStrMsgString();
				objVOEmpReg_p.setStrMsgString("EmployeeRegEssentialBO.validateEmpRegDtl() --> "+ strErr);
		}
		
		return bExistStatus;
	}

	public Map<String, Object> getPendingEmployeeList_AJAX(UserVO _userVO,int p_page,int p_limit,String p_sidx, String p_sord, String p_where) 
	{		
		Map<String, Object> mapObj = null;		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			mapObj = objEmpDAO.getPendingEmployeeList_AJAX("1",_userVO, p_page, p_limit, p_sidx, p_sord, p_where);			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapObj;
	}
	
	public Map<String, Object> getValidatedEmployeeList_AJAX(UserVO _userVO,int p_page,int p_limit,String p_sidx, String p_sord, String p_where) 
	{		
		Map<String, Object> mapObj = null;		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			mapObj = objEmpDAO.getValidatedEmployeeList_AJAX("1",_userVO, p_page, p_limit, p_sidx, p_sord, p_where);			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapObj;
	}
	
	public Map<String, Object> getRejectedEmployeeList_AJAX(UserVO _userVO,int p_page,int p_limit,String p_sidx, String p_sord, String p_where) 
	{		
		Map<String, Object> mapObj = null;		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objEmpDAO = new EmpRegistrationDAO(tx);
			mapObj = objEmpDAO.getRejectedEmployeeList_AJAX("1",_userVO, p_page, p_limit, p_sidx, p_sord, p_where);			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapObj;
	}
	
	
	/*public List getValidatorDetails(UserVO userVO_p, String strMode_p)
	{
		
		List lstEmployeeJsonObjString=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmployeeEssentialDAO objEssentialDAO = new EmployeeEssentialDAO(tx);
			
			lstEmployeeJsonObjString = objEssentialDAO.getValidatorDetails(userVO_p, strMode_p);
			
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstEmployeeJsonObjString;
	}
	*/
	
	public List getEmpDtlForValidation(UserVO userVO_p, String empValidationStatus, String empNumber, String strMode_p)
	{
		
		List lstEmployeeJsonObjString=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objDAO = new EmpRegistrationDAO(tx);				
			lstEmployeeJsonObjString = objDAO.getEmpDtlForValidation(userVO_p, empValidationStatus, empNumber, strMode_p);
			//voPatient.setPatAddress(patAddress);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstEmployeeJsonObjString;
	}
	
	public List getReportData(UserVO userVO_p, String empValidationStatus, String strMode_p , String filterCondition)
	{
		
		List lstEmployeeJsonObjString=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EmpRegistrationDAO objDAO = new EmpRegistrationDAO(tx);			
			lstEmployeeJsonObjString = objDAO.getReportData(userVO_p, empValidationStatus, strMode_p, filterCondition);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstEmployeeJsonObjString;
	}
	
}//end of the class