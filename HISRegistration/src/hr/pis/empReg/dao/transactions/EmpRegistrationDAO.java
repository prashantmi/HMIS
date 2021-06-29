package hr.pis.empReg.dao.transactions;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hr.pis.config.PisConfig;
import hr.pis.config.PisDaoConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;

/**
 * PatientDAO is a class which describes all the methods required for database interaction for HRGT_PATIENT_DTL table, for example, insert, update, select
 * and delete. PatientDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class EmpRegistrationDAO extends PrDAO {

	Logger log;
	/**
	 * Creates a new PatientDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public EmpRegistrationDAO(TransactionContext _transactionContext) 
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}
	

	public String generateEmpNumber(EmpRegistrationVO objEmployeeVO_p, UserVO objUserVO_p)
	{
		String strEmpNo=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		String existingEmployeeFlag="";
		try 
		{
			System.out.println("inside generateEmpNumber function");
			System.out.println("Existing Employee Flag Value"+objEmployeeVO_p.getStrIsExistingEmployee());
			if(objEmployeeVO_p.getStrIsExistingEmployee().equalsIgnoreCase("on"))
			{
				existingEmployeeFlag="Y";
			}
			else
			{
				existingEmployeeFlag="N";
			}
			
			daoObj = new HisDAO("Pis","EmployeeRegistrationDAO.generateEmpNumber");
			funcIndex = daoObj.setFunction("{? = call pkg_pis_emp_registration_dtl.fun_generateempno(?,?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, objUserVO_p.getHospitalCode());
			daoObj.setFuncInValue(funcIndex, 3, PisConfig.EMP_NO_FORMAT_SIZE);
			daoObj.setFuncInValue(funcIndex, 4, objEmployeeVO_p.getDtEmployeeDOB());
			daoObj.setFuncInValue(funcIndex, 5, existingEmployeeFlag);
			daoObj.setFuncInValue(funcIndex, 6, objEmployeeVO_p.getStrOldEmployeeNumber());
			daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strEmpNo = daoObj.getFuncNumeric(funcIndex);
			System.out.println("Generated Employee Number = "+strEmpNo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EmployeeRegistrationDAO.generateEmpNumber()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return strEmpNo;
	}
	
	/**
	 * Creates a new employee entry in DB for a employee. Generates a 12-digit Employee
	 * No using a SQL Procedure. 
	 * @param objPatientVO_p	Provides patient details to be entered.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	
	public void saveEmployeeDtl(HisDAO daoObj,EmpRegistrationVO objEmployeeVO_p, UserVO objUserVO_p,String strMode_p) {

		String strErr = "";
		int nProcIndex = 0;
		String strProcName="";
		
		try 
		{
			
			//strProcName = "{call pkg_pis_dml.dml_pist_emp_registration_dtl(?::numeric, ?::numeric, ?::numeric, ?, ?::numeric, ?::numeric, ?, ?, ?, ?, ?::numeric, ?::numeric, ?, ?::timestamp, ?::numeric, ?::numeric, ?, ?::numeric, ?::numeric, ?::numeric,?)}";
			strProcName = "{call pkg_pis_emp_registration_dtl.dml_pist_emp_registration_dtl(?::numeric, ?::numeric, ?::numeric, ?, ?::numeric, ?::numeric, ?, ?, ?, ?, ?::numeric, ?::numeric, ?, ?::timestamp, ?::numeric, ?::numeric, ?, ?::numeric, ?::numeric, ?::numeric, ?::numeric, ?::numeric, ?, ?, ?)}";


			nProcIndex = daoObj.setProcedure(strProcName);			
			HelperMethods.setNullToEmpty(objEmployeeVO_p);
			
			
			///////////////
			System.out.println("EmployeeDAO                   :: saveEmployeeDtl()");
			System.out.println("p_modeVal                     :"+ strMode_p);
			System.out.println("p_nature_of_job_code          :"+ objEmployeeVO_p.getIntNatureOfJobId());
			System.out.println("p_emp_no                      :"+ objEmployeeVO_p.getStrEmployeeNumber());
			System.out.println("p_old_emp_no                  :"+ objEmployeeVO_p.getStrOldEmployeeNumber());
			System.out.println("p_appellation_code_1          :"+ objEmployeeVO_p.getIntAppellationCode1());
			System.out.println("p_appellation_code_2          :"+ objEmployeeVO_p.getIntAppellationCode2());
			System.out.println("p_emp_full_name               :"+ objEmployeeVO_p.getStrEmployeeFullName());
			System.out.println("p_emp_full_other_lang_name    :"+ objEmployeeVO_p.getStrEmployeeFullRegionalLangName());
			System.out.println("p_emp_short_name              :"+ objEmployeeVO_p.getStrEmployeeShortName());
			System.out.println("p_emp_short_other_lang_name   :"+ objEmployeeVO_p.getStrEmployeeShortRegionalLangName());
			System.out.println("p_suffix_code                 :"+ objEmployeeVO_p.getIntSuffixCode());
			System.out.println("p_nationality_code            :"+ objEmployeeVO_p.getIntNationalityId());
			System.out.println("p_gender_code                 :"+ objEmployeeVO_p.getStrGenderCode()); 
			System.out.println("p_emp_dob                     :"+ objEmployeeVO_p.getDtEmployeeDOB()); 
			System.out.println("p_department_code             :"+ objEmployeeVO_p.getIntDepartmentCode()); 
			System.out.println("p_designaton_code             :"+ objEmployeeVO_p.getIntDesignationCode()); 
			System.out.println("p_final_status                :"+ objEmployeeVO_p.getStrEmployeeFinalStatus());
			System.out.println("p_is_valid                    :"+ objEmployeeVO_p.getIntIsValid()==null?"1":objEmployeeVO_p.getIntIsValid());
			System.out.println("p_seat_id                     :"+ objUserVO_p.getSeatId()); 
			System.out.println("p_hospital_code               :"+ objUserVO_p.getHospitalCode());
			System.out.println("p_last_employment_type        :"+ objEmployeeVO_p.getStrLastEmploymentType());
			System.out.println("p_mobile_number               :"+ objEmployeeVO_p.getStrLastEmploymentType());
			System.out.println("p_email_id                    :"+ objEmployeeVO_p.getStrLastEmploymentType());
			System.out.println("p_pan_number                  :"+ objEmployeeVO_p.getStrLastEmploymentType());
			////////////////
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_nature_of_job_code",objEmployeeVO_p.getIntNatureOfJobId(),2);
			daoObj.setProcInValue(nProcIndex, "p_emp_no", objEmployeeVO_p.getStrEmployeeNumber(),3);
			daoObj.setProcInValue(nProcIndex, "p_old_emp_no", objEmployeeVO_p.getStrOldEmployeeNumber(),4);
			daoObj.setProcInValue(nProcIndex, "p_appellation_code_1", objEmployeeVO_p.getIntAppellationCode1(),5);
			daoObj.setProcInValue(nProcIndex, "p_appellation_code_2", objEmployeeVO_p.getIntAppellationCode2(),6);
			daoObj.setProcInValue(nProcIndex, "p_emp_full_name", objEmployeeVO_p.getStrEmployeeFullName(),7);
			daoObj.setProcInValue(nProcIndex, "p_emp_full_other_lang_name", objEmployeeVO_p.getStrEmployeeFullRegionalLangName(),8);
			daoObj.setProcInValue(nProcIndex, "p_emp_short_name", objEmployeeVO_p.getStrEmployeeShortName(),9);
			daoObj.setProcInValue(nProcIndex, "p_emp_short_other_lang_name", objEmployeeVO_p.getStrEmployeeShortRegionalLangName(),10);
			daoObj.setProcInValue(nProcIndex, "p_suffix_code", objEmployeeVO_p.getIntSuffixCode(),11);
			daoObj.setProcInValue(nProcIndex, "p_nationality_code",objEmployeeVO_p.getIntNationalityId(),12);
			daoObj.setProcInValue(nProcIndex, "p_gender_code", objEmployeeVO_p.getStrGenderCode(),13); 
			daoObj.setProcInValue(nProcIndex, "p_emp_dob", objEmployeeVO_p.getDtEmployeeDOB(),14); 
			daoObj.setProcInValue(nProcIndex, "p_department_code", objEmployeeVO_p.getIntDepartmentCode(),15); 
			daoObj.setProcInValue(nProcIndex, "p_designaton_code", objEmployeeVO_p.getIntDesignationCode(),16); 
			daoObj.setProcInValue(nProcIndex, "p_final_status", objEmployeeVO_p.getStrEmployeeFinalStatus(),17);
			daoObj.setProcInValue(nProcIndex, "p_is_valid", "1",18);
			daoObj.setProcInValue(nProcIndex, "p_seat_id", objUserVO_p.getSeatId(),19); 
			daoObj.setProcInValue(nProcIndex, "p_hospital_code", objUserVO_p.getHospitalCode(),20);
			daoObj.setProcInValue(nProcIndex, "p_last_employment_type", objEmployeeVO_p.getStrLastEmploymentType(),21);
			daoObj.setProcInValue(nProcIndex, "p_mobile_number", objEmployeeVO_p.getIntMobileNumber(),22);
			daoObj.setProcInValue(nProcIndex, "p_email_id", objEmployeeVO_p.getStrPersonalEmailId(),23);
			daoObj.setProcInValue(nProcIndex, "p_pan_number", objEmployeeVO_p.getStrPANNumber(),24);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,25);

			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
				
	}
	
	
	public List getEmpDtl(UserVO userVO_p, String searchEmpName_p,String searchEmpNo_p,String strMode_p) 
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = PisDaoConfig.PROCEDURE_GET_EMP_DETAIL_BY_POPUP;
		int nProcIndex = 0;
		String strErr = "";
		List lstEmployeeJsonObjString=new ArrayList();

		try 
		{
			System.out.println("EmployeeRegistrationDAO :: getEmpDtl()");
			daoObj = new HisDAO("Pis","EmployeeRegistrationDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_search_emp_no ", searchEmpNo_p==null?"":searchEmpNo_p,2);
			daoObj.setProcInValue(nProcIndex, "p_search_emp_name ", searchEmpName_p==null?"":searchEmpName_p,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code  ", userVO_p.getHospitalCode(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				while(webRs.next()){
					lstEmployeeJsonObjString.add(webRs.getString(1));
					System.out.println("webRs.getString(1) :" + webRs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EmployeeRegistrationDAO :: getEmpDtl" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return lstEmployeeJsonObjString;
	}
	
	public static EmpRegistrationVO modifyRecord(EmpRegistrationVO vo,HisDAO hisDAO_p, UserVO userVO_p) 
	{
		System.out.println("EmployeeRegistrationDAO>> modifyRecord");
		
		final String strProcName = "{call pkg_pis_emp_registration_dtl.proc_get_emp_dtl_for_validation (?,?,?,?,?,?)}";
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(vo);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_no",vo.getStrEmployeeNumber(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hosp_code",userVO_p.getHospitalCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_validation_status_category",vo.getStrEmployeeValidationStatusCategory(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5); // 1 for
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,6); // 2 for
			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("EmployeeRegistrationDAO.modifyRecord() --> "	+ e.getMessage());
			vo.setStrMsgType("1");
		}

		try
		{
			//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
			webRowSet.beforeFirst();
			webRowSet.next();
			
			vo.setStrIsExistingEmployee(webRowSet.getString(1));
			vo.setStrEmployeeNumber(webRowSet.getString(2));
			vo.setStrEmployeeFullName(webRowSet.getString(3));
			vo.setStrEmployeeFullRegionalLangName(webRowSet.getString(4));
			vo.setDtEmployeeDOB(webRowSet.getString(5));
			vo.setIntNatureOfJobId(webRowSet.getString(6));
			vo.setIntAppellationCode1(webRowSet.getString(7));
			vo.setIntAppellationCode2(webRowSet.getString(8));
			vo.setStrEmployeeShortName(webRowSet.getString(9));
			vo.setStrEmployeeShortRegionalLangName(webRowSet.getString(10));
			vo.setIntSuffixCode(webRowSet.getString(11));
			vo.setStrGenderCode(webRowSet.getString(12));
			vo.setIntNationalityId(webRowSet.getString(13));
			vo.setIntDepartmentCode(webRowSet.getString(14));
			vo.setIntDesignationCode(webRowSet.getString(15));
			vo.setStrOldEmployeeNumber(webRowSet.getString(16));
			vo.setStrEmployeeFinalStatus(webRowSet.getString(17));
			vo.setStrLastEmploymentType(webRowSet.getString(18));
			vo.setIntMobileNumber(webRowSet.getString(19));
			vo.setStrPersonalEmailId(webRowSet.getString(20));
			vo.setStrPANNumber(webRowSet.getString(21));
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmployeeRegistrationDAO:modifyRecord():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return vo;

	}
	
	public static boolean validateEmpRegDtl(EmpRegistrationVO vo,HisDAO hisDAO_p,UserVO uservo, String strMode_p)
	{
		System.out.println("EmployeeRegistrationDAO>> validateEmpRegDtl");
		final String strProcName = "{call pkg_pis_emp_registration_dtl.dml_pist_emp_registration_validation_dtl(?::numeric,?,?,?,?,?::timestamp,?,?::numeric,?::numeric,?::numeric,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		String strErr = "";
		boolean bExistStatus = false;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(vo);

			System.out.println("p_modeval          "+strMode_p);
			System.out.println("p_emp_no           "+vo.getStrEmployeeNumber());
			System.out.println("p_validate_status  "+vo.getStrValidateStatus());
			System.out.println("p_validate_id      "+vo.getStrValidateId());
			System.out.println("p_validate_name    "+vo.getStrValidatorName());
			System.out.println("p_validate_date    "+vo.getDtValidateDate());
			System.out.println("p_validate_remarks "+vo.getStrValidatorRemarks());
			System.out.println("p_seat_id          "+uservo.getSeatId());
			System.out.println("p_hospital_code    "+uservo.getHospitalCode());
			
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval",strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_no",vo.getStrEmployeeNumber(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_validate_status",vo.getStrValidateStatus(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_validate_id",vo.getStrValidateId(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_validate_name",vo.getStrValidatorName(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_validate_date",vo.getDtValidateDate(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_validate_remarks",vo.getStrValidatorRemarks(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_valid","1",8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id",uservo.getSeatId(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",uservo.getHospitalCode(),10);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 11); // 1 for
			
			hisDAO_p.execute(nProcedureIndex,1);
			
			if (strErr == null)
				strErr = "";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException(e.getMessage());
		}
		
		return bExistStatus;
		
	}
	
	public Map<String, Object> getPendingEmployeeList_AJAX(String _calMode, UserVO _userVO, int p_page,int p_limit,String p_sidx, String p_sord, String p_where)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		Map<String, Object> populateMAP = new HashMap<String, Object>();
		
		String errorMsg = null;
		
		int $total_pages = 0;
		int $count=0;
		
		String count="";
		
		List<List<String>> alRecord = new ArrayList<List<String>>();
		try
		{
			Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_PENDING_EMPLOYEE_REGISTRATION_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, "");
			strProc.addInParameter(4, Types.VARCHAR, "");
			strProc.addInParameter(5, Types.VARCHAR, "");
			strProc.addInParameter(6, Types.VARCHAR, "");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			//String count = (String) strProc.getParameterAt(12);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			try
			{
				if(rs.next()) count=rs.getString(1); else count="0"; 
			}
			catch (SQLException e)
			{
				throw new HisDataAccessException("EmployeeRegistrationDAO.getPendingEmployeeList_AJAX()" + e);
			}
			
			System.out.println("count:"+count);
			/*
			System.out.println("p_page"+p_page);
			System.out.println("p_limit:"+p_limit);
			System.out.println("p_sidx:"+p_sidx);
			System.out.println("p_sord:"+p_sord);
			*/						
			
			/*$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			*/
			$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			if($start<0)$start=0;
			
		
			strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_PENDING_EMPLOYEE_REGISTRATION_LIST);
			strProc.addInParameter(1, Types.VARCHAR, "2");
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, p_sidx);
			strProc.addInParameter(4, Types.VARCHAR, p_sord);
			strProc.addInParameter(5, Types.VARCHAR, $start+"");
			strProc.addInParameter(6, Types.VARCHAR, p_limit+"");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			count = (String) strProc.getParameterAt(11);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			System.out.println("getPendingEmployeeList_AJAX end");
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);	
			//alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWiseWithColumn(rs);	
			/*
			System.out.println("$count"+$count);
			System.out.println("$total_pages:"+$total_pages);
			System.out.println("p_page:"+p_page);
			*/			
			
			populateMAP.put("count", $count+"");
			populateMAP.put("total_pages", $total_pages+"");
			populateMAP.put("page", p_page+"");
			populateMAP.put("listObj", alRecord);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmployeeRegistrationDAO.getPendingEmployeeList_AJAX()" + e);
		}
		
		return populateMAP;
	}
	
	public Map<String, Object> getValidatedEmployeeList_AJAX(String _calMode, UserVO _userVO, int p_page,int p_limit,String p_sidx, String p_sord, String p_where)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		Map<String, Object> populateMAP = new HashMap<String, Object>();
		
		String errorMsg = null;
		
		int $total_pages = 0;
		int $count=0;
		
		String count="";
		
		List<List<String>> alRecord = new ArrayList<List<String>>();
		try
		{
			Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_VALIDATED_EMPLOYEE_REGISTRATION_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, "");
			strProc.addInParameter(4, Types.VARCHAR, "");
			strProc.addInParameter(5, Types.VARCHAR, "");
			strProc.addInParameter(6, Types.VARCHAR, "");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			//String count = (String) strProc.getParameterAt(12);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			try
			{
				if(rs.next()) count=rs.getString(1); else count="0"; 
			}
			catch (SQLException e)
			{
				throw new HisDataAccessException("EmployeeRegistrationDAO.getValidatedEmployeeList_AJAX()" + e);
			}
			
			System.out.println("count:"+count);
			/*
			System.out.println("p_page"+p_page);
			System.out.println("p_limit:"+p_limit);
			System.out.println("p_sidx:"+p_sidx);
			System.out.println("p_sord:"+p_sord);
			*/						
			
			/*$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			*/
			$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			if($start<0)$start=0;
			
		
			strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_VALIDATED_EMPLOYEE_REGISTRATION_LIST);
			strProc.addInParameter(1, Types.VARCHAR, "2");
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, p_sidx);
			strProc.addInParameter(4, Types.VARCHAR, p_sord);
			strProc.addInParameter(5, Types.VARCHAR, $start+"");
			strProc.addInParameter(6, Types.VARCHAR, p_limit+"");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			count = (String) strProc.getParameterAt(11);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			System.out.println("getValidatedEmployeeList_AJAX end");
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);	
			//alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWiseWithColumn(rs);	
			/*
			System.out.println("$count"+$count);
			System.out.println("$total_pages:"+$total_pages);
			System.out.println("p_page:"+p_page);
			*/			
			
			populateMAP.put("count", $count+"");
			populateMAP.put("total_pages", $total_pages+"");
			populateMAP.put("page", p_page+"");
			populateMAP.put("listObj", alRecord);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmployeeRegistrationDAO.getValidatedEmployeeList_AJAX()" + e);
		}
		
		return populateMAP;
	}
	
	public Map<String, Object> getRejectedEmployeeList_AJAX(String _calMode, UserVO _userVO, int p_page,int p_limit,String p_sidx, String p_sord, String p_where)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		Map<String, Object> populateMAP = new HashMap<String, Object>();
		
		String errorMsg = null;
		
		int $total_pages = 0;
		int $count=0;
		
		String count="";
		
		List<List<String>> alRecord = new ArrayList<List<String>>();
		try
		{
			Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_REJECTED_EMPLOYEE_REGISTRATION_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, "");
			strProc.addInParameter(4, Types.VARCHAR, "");
			strProc.addInParameter(5, Types.VARCHAR, "");
			strProc.addInParameter(6, Types.VARCHAR, "");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			//String count = (String) strProc.getParameterAt(12);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			try
			{
				if(rs.next()) count=rs.getString(1); else count="0"; 
			}
			catch (SQLException e)
			{
				throw new HisDataAccessException("EmployeeRegistrationDAO.getRejectedEmployeeList_AJAX()" + e);
			}
			
			System.out.println("count:"+count);
			/*
			System.out.println("p_page"+p_page);
			System.out.println("p_limit:"+p_limit);
			System.out.println("p_sidx:"+p_sidx);
			System.out.println("p_sord:"+p_sord);
			*/						
			
			/*$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			*/
			$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			if($start<0)$start=0;
			
		
			strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_REJECTED_EMPLOYEE_REGISTRATION_LIST);
			strProc.addInParameter(1, Types.VARCHAR, "2");
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, p_sidx);
			strProc.addInParameter(4, Types.VARCHAR, p_sord);
			strProc.addInParameter(5, Types.VARCHAR, $start+"");
			strProc.addInParameter(6, Types.VARCHAR, p_limit+"");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			count = (String) strProc.getParameterAt(11);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			System.out.println("getRejectedEmployeeList_AJAX end");
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);	
			//alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWiseWithColumn(rs);	
			/*
			System.out.println("$count"+$count);
			System.out.println("$total_pages:"+$total_pages);
			System.out.println("p_page:"+p_page);
			*/			
			
			populateMAP.put("count", $count+"");
			populateMAP.put("total_pages", $total_pages+"");
			populateMAP.put("page", p_page+"");
			populateMAP.put("listObj", alRecord);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EmployeeRegistrationDAO.getRejectedEmployeeList_AJAX()" + e);
		}
		
		return populateMAP;
	}
	
	public List getEmpDtlForValidation(UserVO userVO_p, String empValidationStatus,String empNumber,String strMode_p) 
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = PisDaoConfig.PROCEDURE_GET_EMP_DETAIL_FOR_VALIDATION;
		int nProcIndex = 0;
		String strErr = "";
		List lstEmployeeJsonObjString=new ArrayList();

		try 
		{
			System.out.println("EmployeeRegistrationDAO :: getEmpDtlForValidation()");
			daoObj = new HisDAO("Pis","EmployeeRegistrationDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_emp_validation_status ", empValidationStatus==null?"":empValidationStatus,2);
			daoObj.setProcInValue(nProcIndex, "p_emp_no ", empNumber==null?"":empNumber,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code  ", userVO_p.getHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_filter_condition  ", "",5);		
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				while(webRs.next()){
					lstEmployeeJsonObjString.add(webRs.getString(1));
					System.out.println("webRs.getString(1) :" + webRs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EmployeeRegistrationDAO :: getEmpDtlForValidation" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return lstEmployeeJsonObjString;
	}
	
	public List getReportData(UserVO userVO_p, String empValidationStatus, String strMode_p , String filterCondition) 
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = PisDaoConfig.PROCEDURE_GET_EMP_DETAIL_FOR_VALIDATION;
		int nProcIndex = 0;
		String strErr = "";
		List lstEmployeeJsonObjString=new ArrayList();
		String empNumber="";
		try 
		{
			System.out.println("EmployeeRegistrationDAO :: getReportData()");
			daoObj = new HisDAO("Pis","EmployeeRegistrationDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_emp_validation_status ", empValidationStatus==null?"":empValidationStatus,2);
			daoObj.setProcInValue(nProcIndex, "p_emp_no ", empNumber==null?"":empNumber,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code  ", userVO_p.getHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_filter_condition  ", filterCondition,5);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				while(webRs.next()){
					lstEmployeeJsonObjString.add(webRs.getString(1));
					System.out.println("webRs.getString(1) :" + webRs.getString(1));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EmployeeRegistrationDAO :: getReportData" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return lstEmployeeJsonObjString;
	}
}//end of class
