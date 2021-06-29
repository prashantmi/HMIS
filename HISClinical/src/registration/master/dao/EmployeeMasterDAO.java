package registration.master.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.RegistrationConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EmpMasterVO;
import hisglobal.vo.EmployeeMasterVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class EmployeeMasterDAO extends DataAccessObject{
	
	public EmployeeMasterDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	public EmpMasterVO validateEmployeeAgainstID(String _empId,UserVO _userVO){		
		Connection conn=transactionContext.getConnection();		
		EmpMasterVO empMasterVO=new EmpMasterVO();
		Map _populateMap = new HashMap();		
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.EMPNO_PSRT_EMPLOYEE_MST";		
		 Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _empId);
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		try {
			     query = HelperMethodsDAO.getQuery(filename, queryKey);		
			} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
				throw new HisRecordNotFoundException();
			else				
				HelperMethods.populateVOfrmRS(empMasterVO, rs);
		
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		
		return  empMasterVO;
		 
	}
	
	public EmpMasterVO[] getEmployeeWithDependents(String _empId,UserVO _userVO){		
		Connection conn=transactionContext.getConnection();
		ValueObject[] VO;
		EmpMasterVO empMasterVO[];
		Map _populateMap = new HashMap();		
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.EMPDEPENDENT_DTL_PSRT_EMPLOYEE_MST";		
		 Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _empId);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _empId);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		try {
			     query = HelperMethodsDAO.getQuery(filename, queryKey);		
			} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
				throw new HisRecordNotFoundException();
			else
				rs.beforeFirst();
			 VO=HelperMethods.populateVOfrmRS(EmpMasterVO.class, rs);
			empMasterVO =new EmpMasterVO[VO.length];
			for(int i=0;i<empMasterVO.length;i++){
				empMasterVO[i]=(EmpMasterVO)VO[i];
				}			
		}
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("Employee Mst:retrieveByCrNo:HelperMethods :: " + e);
		}
		
		return  empMasterVO;
		 
	}
	
	public EmpMasterVO[] getEmployeeWithDependentsByName(String searchName,UserVO _userVO){		
		Connection conn=transactionContext.getConnection();
		ValueObject[] VO;
		EmpMasterVO empMasterVO[];
		Map _populateMap = new HashMap();		
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.BYNAME.EMPDEPENDENT_DTL_PSRT_EMPLOYEE_MST";		
		 Sequence sq = new Sequence();
		 String FName="";
		 String MName="";
		 String LName="";
		int index=searchName.indexOf(" ");
		if(index!=-1){
		 FName=searchName.substring(0,index).trim(); 
		 searchName=searchName.substring(index).trim();
			index=searchName.indexOf(" ");
		}
		else{
			FName=searchName;
			searchName="";
		}
		
		if(index!=-1){
		 MName=searchName.substring(0,index).trim();
		 searchName=searchName.substring(index).trim();
		}
		else {
			MName=searchName; 
			searchName="";
		}
		 LName=searchName.trim();
		if(!MName.equals("") && LName.equals("")){
			LName=MName; 
		}
		_populateMap.put(sq.next(), FName+"%");
		_populateMap.put(sq.next(), MName+"%"); 
		_populateMap.put(sq.next(), LName+"%");
		_populateMap.put(sq.next(), FName+"%");
		_populateMap.put(sq.next(), MName+"%");
		_populateMap.put(sq.next(), FName+"%");
		_populateMap.put(sq.next(), LName+"%");
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), FName+"%");
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		try {
			     query = HelperMethodsDAO.getQuery(filename, queryKey);		
			} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
				throw new HisRecordNotFoundException();
			else
				rs.beforeFirst();
			 VO=HelperMethods.populateVOfrmRS(EmpMasterVO.class, rs);
			empMasterVO =new EmpMasterVO[VO.length];
			for(int i=0;i<empMasterVO.length;i++){
				empMasterVO[i]=(EmpMasterVO)VO[i];
				}			
		}
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		
		return  empMasterVO;
		 
	}

	public EmpMasterVO[] getEmployeeWithDependentsForStaffClinic(String _empId, UserVO _userVO) 
	{		
		ValueObject[] VO;
		EmpMasterVO empMasterVO[]=null;
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call PKG_REG_VIEW.PROC_EMP_DATA_STAFF_CLINIC(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_empid", _empId,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals(""))
			{
				VO=HelperMethods.populateVOfrmRS(EmpMasterVO.class, webRs);
				empMasterVO =new EmpMasterVO[VO.length];
				for(int i=0;i<empMasterVO.length;i++)
				{
					empMasterVO[i]=(EmpMasterVO)VO[i];
				}	
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			//empMasterVO.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
			e.printStackTrace();
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return empMasterVO;		 
	}

	public EmpMasterVO[] getEmployeeWithDependentsByNameForStaffClinic(String searchName, UserVO _userVO) {		
		Connection conn=transactionContext.getConnection();
		ValueObject[] VO;
		EmpMasterVO empMasterVO[];
		Map _populateMap = new HashMap();		
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.BYNAME.EMPDEPENDENT_DTL_PSRT_EMPLOYEE_MST_STAFF_CLINIC";		
		 Sequence sq = new Sequence();
		 String FName="";
		 String MName="";
		 String LName="";
		int index=searchName.indexOf(" ");
		if(index!=-1){
		 FName=searchName.substring(0,index).trim(); 
		 searchName=searchName.substring(index).trim();
			index=searchName.indexOf(" ");
		}
		else{
			FName=searchName;
			searchName="";
		}
		
		if(index!=-1){
		 MName=searchName.substring(0,index).trim();
		 searchName=searchName.substring(index).trim();
		}
		else {
			MName=searchName; 
			searchName="";
		}
		 LName=searchName.trim();
		if(!MName.equals("") && LName.equals("")){
			LName=MName; 
		}
		_populateMap.put(sq.next(),  ""+FName+"%");
		_populateMap.put(sq.next(), ""+MName+"%"); 
		_populateMap.put(sq.next(), ""+LName+"%");
		_populateMap.put(sq.next(), ""+FName+"%");
		_populateMap.put(sq.next(), ""+MName+"%");
		_populateMap.put(sq.next(), ""+FName+"%");
		_populateMap.put(sq.next(), ""+LName+"%");
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), ""+FName+"%");
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		try {
			     query = HelperMethodsDAO.getQuery(filename, queryKey);		
			} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
				throw new HisRecordNotFoundException();
			else
				rs.beforeFirst();
			 VO=HelperMethods.populateVOfrmRS(EmpMasterVO.class, rs);
			empMasterVO =new EmpMasterVO[VO.length];
			for(int i=0;i<empMasterVO.length;i++){
				empMasterVO[i]=(EmpMasterVO)VO[i];
				}			
		}
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		
		return  empMasterVO;
		 
	}
	
	public EmployeeMasterVO saveEmployeeDetail(HisDAO daoObj,EmployeeMasterVO _empMstVO, UserVO _userVO) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			strProcName2 = "{call pkg_reg_dml.proc_pist_emp_entry_dtl(?,?::character varying,?,?,?::character varying, ?,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying,?::character varying,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_empMstVO);
		
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex2, "p_str_emp_no",_empMstVO.getEmpNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_salutation_id", _empMstVO.getSalutationId(),3);
			daoObj.setProcInValue(nProcIndex2, "p_str_first_name", _empMstVO.getFirstName(),4);
			daoObj.setProcInValue(nProcIndex2, "p_str_middle_name", _empMstVO.getMiddleName(),5);
			daoObj.setProcInValue(nProcIndex2, "p_str_last_name", _empMstVO.getLastName(),6);
			daoObj.setProcInValue(nProcIndex2, "p_num_gender_code", _empMstVO.getGenderCode(),7);
			daoObj.setProcInValue(nProcIndex2, "p_dt_birth_date", _empMstVO.getBirthDate(),8);
			daoObj.setProcInValue(nProcIndex2, "p_num_country_id", _empMstVO.getCountryCode(),9);
			daoObj.setProcInValue(nProcIndex2, "p_num_hospital_id", _userVO.getHospitalCode(),10);
			daoObj.setProcInValue(nProcIndex2, "p_num_desig_id", _empMstVO.getDesignationCode(),11);
			daoObj.setProcInValue(nProcIndex2, "p_num_dept_id", _empMstVO.getDeptCode(),12);
			daoObj.setProcInValue(nProcIndex2, "p_str_status", _empMstVO.getStatus(),13);
			daoObj.setProcInValue(nProcIndex2, "p_str_final_status", _empMstVO.getFinalstatus(),14);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_isvalid", _empMstVO.getIsValid(),15);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_seat_id", _userVO.getSeatId(),16);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code", _userVO.getHospitalCode(),17);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_contact_no", _empMstVO.getContactNo(),18);
			daoObj.setProcInValue(nProcIndex2, "p_str_validate", "Y",19);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,20);
			
			daoObj.executeProcedureByPosition(nProcIndex2);
			//daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		finally{
			if(daoObj!=null)
				daoObj.free();
			daoObj=null;
		}
		return _empMstVO;				
	}
	
	public EmployeeMasterVO updateEmployeeDetail(HisDAO daoObj,EmployeeMasterVO _empMstVO, UserVO _userVO) {

		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{
			strProcName2 = "{call pkg_reg_dml.proc_pist_emp_entry_dtl(?,?,?,?,?::character varying, ?,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying,?::character varying,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_empMstVO);
		
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex2, "p_str_emp_no",_empMstVO.getEmpNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_salutation_id", _empMstVO.getSalutationId(),3);
			daoObj.setProcInValue(nProcIndex2, "p_str_first_name", _empMstVO.getFirstName(),4);
			daoObj.setProcInValue(nProcIndex2, "p_str_middle_name", _empMstVO.getMiddleName(),5);
			daoObj.setProcInValue(nProcIndex2, "p_str_last_name", _empMstVO.getLastName(),6);
			daoObj.setProcInValue(nProcIndex2, "p_num_gender_code", _empMstVO.getGenderCode(),7);
			daoObj.setProcInValue(nProcIndex2, "p_dt_birth_date", _empMstVO.getBirthDate(),8);
			daoObj.setProcInValue(nProcIndex2, "p_num_country_id", _empMstVO.getCountryCode(),9);
			daoObj.setProcInValue(nProcIndex2, "p_num_hospital_id", _userVO.getHospitalCode(),10);
			daoObj.setProcInValue(nProcIndex2, "p_num_desig_id", _empMstVO.getDesignationCode(),11);
			daoObj.setProcInValue(nProcIndex2, "p_num_dept_id", _empMstVO.getDeptCode(),12);
			daoObj.setProcInValue(nProcIndex2, "p_str_status", _empMstVO.getStatus(),13);
			daoObj.setProcInValue(nProcIndex2, "p_str_final_status", _empMstVO.getFinalstatus(),14);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_isvalid", _empMstVO.getIsValid(),15);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_seat_id", _userVO.getSeatId(),16);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hospital_code", _userVO.getHospitalCode(),17);
			daoObj.setProcInValue(nProcIndex2, "p_hrgstr_contact_no", _empMstVO.getContactNo(),18);
			daoObj.setProcInValue(nProcIndex2, "p_str_validate", "Y",19);

			daoObj.setProcOutValue(nProcIndex2, "err", 1,20);
			
			daoObj.executeProcedureByPosition(nProcIndex2);
			//daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally{
			if(daoObj!=null)
				daoObj.free();
			daoObj=null;
		}
		return _empMstVO;				
	}
	
	public EmployeeMasterVO getEmployeeMasterDetails(String code,UserVO _uservo) {	
		
		EmployeeMasterVO _empMasterMasterVO=new EmployeeMasterVO();
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.EMPLOYEEMASTERDETAIL.PIST_EMP_ENTRY_DTL";
		
		Sequence sq=new Sequence();
		populateMap.put(sq.next(),code);	
		populateMap.put(sq.next(),_uservo.getHospitalCode());		
		
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}			 	 
		
		try{
			
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);					
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else{
	 	    	rs.beforeFirst();	 	    	
	 	    	while(rs.next()){	 	    		
	 	 	    	HelperMethods.populateVOfrmRS(_empMasterMasterVO,rs); 		 	 	    	
	 	    	} 	    	
	 	    }
	 	   HelperMethods.setNullToEmpty(_empMasterMasterVO); 	
		}
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }			 	 	
		return _empMasterMasterVO;

	}
	
	
	
}//end of class
