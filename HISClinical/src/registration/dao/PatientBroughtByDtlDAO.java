package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientBroughtByDtlVO;
import hisglobal.vo.UserVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.RegistrationConfig;

public class PatientBroughtByDtlDAO extends DataAccessObject {
	
	public PatientBroughtByDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	
public PatientBroughtByDtlVO create(PatientBroughtByDtlVO _patientBroughtByDtlVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="INSERT.HRGT_PATIENT_BROUGHTBY_DTL";                       
        //Properties properties = new Properties();        
        //call the getQueryMethod with arguments filename,querykey from prop file
       
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	populateMap(_patientBroughtByDtlVO,_userVO,populateMAP);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("PatientBroughtByDtlDAO:populateMap(_patientBroughtByDtlVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return _patientBroughtByDtlVO;
	}

public PatientBroughtByDtlVO create(HisDAO daoObj,PatientBroughtByDtlVO _patientBroughtByDtlVO, UserVO _userVO){
	
	String strErr = "";
	int nProcIndex1 = 0;
	String strProcName1="";
	
	try 
	{
		strProcName1 = "{call pkg_reg_dml.PROC_BROUGHT_BY_SAVE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		nProcIndex1 = daoObj.setProcedure(strProcName1);
		
		HelperMethods.setNullToEmpty(_patientBroughtByDtlVO);
		_patientBroughtByDtlVO.setSeatId(_userVO.getSeatId());
	    
		daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);		
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_patientBroughtByDtlVO.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_slno","",3);
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",_patientBroughtByDtlVO.getEpisodeCode(),4);
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_visitno",_patientBroughtByDtlVO.getEpisodeVisitNo(),5);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_name",_patientBroughtByDtlVO.getBroughtByName(),6);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_address",_patientBroughtByDtlVO.getBroughtByAddress(),7);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_phone",_patientBroughtByDtlVO.getBroughtByPhone(),8);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code",_patientBroughtByDtlVO.getBroughtByGenderCode(),9);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_isrelative",_patientBroughtByDtlVO.getIsRelative(),10);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_relation_code",_patientBroughtByDtlVO.getBroughtByRelationCode(),11);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress(),12);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id",_userVO.getSeatId(),13);
		daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","",14);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,15);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode(),16);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_location",_patientBroughtByDtlVO.getBroughtByLocation(),17);
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",_patientBroughtByDtlVO.getEpisodeVisitNo(),18);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig",_patientBroughtByDtlVO.getConstableDesig(),19);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno",_patientBroughtByDtlVO.getConstableBadgeNo(),20);
	// added to save vehicle no(ambulance no) with effect to cr-06(raol) to add vehicle information in case of emergency
		daoObj.setProcInValue(nProcIndex1, "p_vehicle_no",_patientBroughtByDtlVO.getPatVehicleNo(),21);

		daoObj.setProcOutValue(nProcIndex1, "err", 1,22);
		
		daoObj.execute(nProcIndex1,1);			
		
		//strErr = daoObj.getString(nProcIndex1, "err");
			if (strErr == null)
				strErr = "";

				if (!strErr.equals("")) 
				{
					throw new Exception(strErr);
				}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	finally{
		if(daoObj!=null){
			daoObj.free();
		}
		daoObj=null;
	}
	return _patientBroughtByDtlVO;	
}
	

   
public void populateMap(PatientBroughtByDtlVO _patientBroughtByDtlVO,UserVO _userVO, Map _populateMAP)throws SQLException
	{
			Sequence sq=new Sequence();
				
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getPatCrNo());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getPatCrNo());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getEpisodeCode());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getEpisodeVisitNo());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getEpisodeCode());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getBroughtByName());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getBroughtByAddress());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getBroughtByPhone());
			//_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getBroughtByGenderCode());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getIsRelative());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getBroughtByRelationCode());
			_populateMAP.put(sq.next(),_userVO.getIpAddress());
			_populateMAP.put(sq.next(),_userVO.getSeatId());
			_populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMAP.put(sq.next(),_userVO.getHospitalCode());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getBroughtByLocation());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getEpisodeVisitNo());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getConstableDesig());
			_populateMAP.put(sq.next(),_patientBroughtByDtlVO.getConstableBadgeNo());
	}

/**
 * Retrieves the Patient Brought By details for a particular CrNo.
 * 
 * @param _patBroughtByDtlVO Provides brought by details.
 * @param _userVO Provides User details.
 * @return PatientBroughtByDtlVO Brought By detail.
 */
public PatientBroughtByDtlVO searchPatientBroughtByDetailCrNo(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
{
	Map _populateMapPatientDtl = new HashMap();
	String query = "";
	String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
	String queryKey = "SELECT.BROUGHT_DETAIL_BY_CRNO.HRGT_PATIENT_BROUGHTBY_DTL";

	Sequence sq = new Sequence();

	_populateMapPatientDtl.put(sq.next(), _patBroughtByDtlVO.getPatCrNo());
	_populateMapPatientDtl.put(sq.next(), _patBroughtByDtlVO.getPatCrNo());
	_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
	_populateMapPatientDtl.put(sq.next(),_userVO.getHospitalCode());
	
	Connection conn = super.getTransactionContext().getConnection();
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query " + query);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	System.out.println("query" + query);

	try
	{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
		if (!rs.next())
		{
			throw new HisRecordNotFoundException("No Brought By Detail For This CrNo Found");
		}
		else
		{
			System.out.println("Record found");
			System.out.println("rs" + rs.getString(1));
			rs.beforeFirst();
			HelperMethods.populateVOfrmRS(_patBroughtByDtlVO, rs);
			
		}
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
	}
	return _patBroughtByDtlVO;
}

	public int update(PatientBroughtByDtlVO patientBroughtByDtlVO, UserVO _uservo) {
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="UPDATE.HRGT_PATIENT_BROUGHTBY_DTL";  
        Sequence sequence=new Sequence();
        //Properties properties = new Properties();        
        //call the getQueryMethod with arguments filename,querykey from prop file
        int i=0;
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	populateMAP.put(sequence.next(),Config.IS_VALID_INACTIVE);
        	populateMAP.put(sequence.next(),patientBroughtByDtlVO.getPatCrNo());
        	populateMAP.put(sequence.next(),patientBroughtByDtlVO.getPatCrNo());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("PatientBroughtByDtlDAO:populateMap(_patientBroughtByDtlVO,populateMAP)::"+e);
        }
        try{
        	 i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
		
		return i;
	}

	public int update(HisDAO daoObj, PatientBroughtByDtlVO _patientBroughtByDtlVO, UserVO _userVO)
	{
		//String queryKey = "UPDATE.HRGT_PATIENT_BROUGHTBY_DTL";
		int i = 1;
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1 = "";

		try
		{
			//strProcName1 = "{call pkg_reg_dml.PROC_BROUGHT_BY_SAVE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            strProcName1 = "{call pkg_reg_dml.PROC_BROUGHT_BY_SAVE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			nProcIndex1 = daoObj.setProcedure(strProcName1);

			HelperMethods.setNullToEmpty(_patientBroughtByDtlVO);
			_patientBroughtByDtlVO.setSeatId(_userVO.getSeatId());
		    
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2",1);		
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_patientBroughtByDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_slno","",3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",_patientBroughtByDtlVO.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_visitno",_patientBroughtByDtlVO.getEpisodeVisitNo(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_name",_patientBroughtByDtlVO.getBroughtByName(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_address",_patientBroughtByDtlVO.getBroughtByAddress(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_phone",_patientBroughtByDtlVO.getBroughtByPhone(),8);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code",_patientBroughtByDtlVO.getBroughtByGenderCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isrelative",_patientBroughtByDtlVO.getIsRelative(),10);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_relation_code",_patientBroughtByDtlVO.getBroughtByRelationCode(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress(),12);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id",_userVO.getSeatId(),13);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","",14);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_INACTIVE,15);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_location",_patientBroughtByDtlVO.getBroughtByLocation(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",_patientBroughtByDtlVO.getEpisodeVisitNo(),18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig",_patientBroughtByDtlVO.getConstableDesig(),19);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno",_patientBroughtByDtlVO.getConstableBadgeNo(),20);
			daoObj.setProcInValue(nProcIndex1, "p_vehicle_no",_patientBroughtByDtlVO.getPatVehicleNo(),21);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,22);

			daoObj.execute(nProcIndex1, 1);

			// strErr = daoObj.getString(nProcIndex1, "err");
			if (strErr == null) strErr = "";

			if (!strErr.equals(""))
			{
				i=0;
				throw new Exception(strErr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return i;	
	}

	public PatientBroughtByDtlVO searchPatientBroughtByDetailCrNoNew(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{
		//String queryKey = "SELECT.BROUGHT_DETAIL_BY_CRNO.HRGT_PATIENT_BROUGHTBY_DTL_NEW";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_BROUGHTBY_DTL_FOR_MLC(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patBroughtByDtlVO.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_episode_code", _patBroughtByDtlVO.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_no", _patBroughtByDtlVO.getEpisodeVisitNo(),5);
			daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _userVO.getSeatId(),7);
			daoObj.setProcInValue(nProcIndex, "p_super_hospital_code", Config.SUPER_USER_HOSPITAL_CODE,8);
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Brought By Detail For This CrNo Found");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_patBroughtByDtlVO, rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return _patBroughtByDtlVO;
	}
	
	public PatientBroughtByDtlVO select(MlcVO mlcVO,UserVO userVO)
	{
		PatientBroughtByDtlVO patBroughtByVO=new PatientBroughtByDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.HRGT_PATIENT_BROUGHTBY_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),mlcVO.getPatCrNo());
		populateMap.put(sq.next(),mlcVO.getEpisodeCode());
		populateMap.put(sq.next(),mlcVO.getEpisodeVisitNo());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),mlcVO.getPatCrNo());
		populateMap.put(sq.next(),mlcVO.getEpisodeCode());
		populateMap.put(sq.next(),mlcVO.getEpisodeVisitNo());
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				patBroughtByVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(patBroughtByVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return patBroughtByVO;
	}
}
