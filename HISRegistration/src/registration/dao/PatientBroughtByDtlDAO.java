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
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import vo.registration.AddressVO;
import vo.registration.MlcVO;
import vo.registration.PatientBroughtByDtlVO;

public class PatientBroughtByDtlDAO extends DataAccessObject {
	
	public PatientBroughtByDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}

public PatientBroughtByDtlVO create(HisDAO daoObj,PatientBroughtByDtlVO _patientBroughtByDtlVO, UserVO _userVO,String strMode){
	
	String strErr = "";
	int nProcIndex1 = 0;
	String strProcName1="";
	
	
	
	/*
	 * 
	 * 
	 * p_modeval character varying DEFAULT '1'::character varying, 
p_hrgnum_puk numeric, 
 p_hrgnum_episode_code numeric, 
 _hrgnum_visit_no numeric, 
 p_hrgstr_brought_by_name character varying,
  p_hrgstr_brought_by_address character varying,
   p_hrgstr_brought_by_phone character varying,  
   _gnum_isrelative character varying,  
   p_hrgstr_constable_desig character varying,
    p_gnum_relation_code character varying, 
    _hrgstr_ip_add character varying,
     p_hrgstr_constable_badgeno character varying,
      p_gnum_seat_id numeric,
        p_gnum_isvalid  numeric,
       p_gnum_hospital_code numeric,  
         p_hrgstr_brought_location character varying,
         OUT err character varying);
	 * */
	
	
	try 
	{
		strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_broughtby_dtl(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,?,?, ?)}";
		
		nProcIndex1 = daoObj.setProcedure(strProcName1);
		
		HelperMethods.setNullToEmpty(_patientBroughtByDtlVO);
		_patientBroughtByDtlVO.setSeatId(_userVO.getSeatId());
	    
		daoObj.setProcInValue(nProcIndex1, "p_modeVal", strMode,1);		
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_patientBroughtByDtlVO.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",_patientBroughtByDtlVO.getEpisodeCode(),3);
		daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",_patientBroughtByDtlVO.getEpisodeVisitNo(),4);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_name",_patientBroughtByDtlVO.getBroughtByName(),5);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_address",_patientBroughtByDtlVO.getBroughtByAddress(),6);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_by_phone",_patientBroughtByDtlVO.getBroughtByPhone(),7);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_isrelative",_patientBroughtByDtlVO.getIsRelative(),8);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig",_patientBroughtByDtlVO.getConstableDesig(),9);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_relation_code",_patientBroughtByDtlVO.getBroughtByRelationCode(),10);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress(),11);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno",_patientBroughtByDtlVO.getConstableBadgeNo(),12);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id",_userVO.getSeatId(),13);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,14);
		daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode(),15);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_brought_location",_patientBroughtByDtlVO.getBroughtByLocation(),16);
		daoObj.setProcInValue(nProcIndex1, "p_hrgstr_police_station",_patientBroughtByDtlVO.getPoliceStation(),17);
		daoObj.setProcOutValue(nProcIndex1, "err", 1,18);
		
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
	return _patientBroughtByDtlVO;	
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
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_BROUGHTBY_DTL(?,?,?, ?,?)}";
	int nProcIndex = 0;
	String strErr = "";

	try
	{
		daoObj = new HisDAO("Registration", "EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_crno", _patBroughtByDtlVO.getPatCrNo(),3);		
		daoObj.setProcOutValue(nProcIndex, "err", 1,4);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e
				+ strErr);
	}

	try
	{
		if (!rs.next())
		{
			System.out.println("no records");
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
	
	finally
	{
		if (daoObj != null)
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return _patBroughtByDtlVO;
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
            strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_broughtby_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_police_station",_patientBroughtByDtlVO.getPoliceStation(),22);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,23);
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
		return i;	
	}

	public PatientBroughtByDtlVO searchPatientBroughtByDetailCrNoNew(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_BROUGHTBY_DTL(?,?,?, ?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patBroughtByDtlVO.getPatCrNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			
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
	
	public List<PatientBroughtByDtlVO> searchPatientBroughtByDetail(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_BROUGHTBY_DTL(?,?,?, ?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		List<PatientBroughtByDtlVO> lstBroughtByDtlVO=new ArrayList<PatientBroughtByDtlVO>();
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patBroughtByDtlVO.getPatCrNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(e.getMessage());
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
				while(rs.next()){
					PatientBroughtByDtlVO vo = new PatientBroughtByDtlVO();
					HelperMethods.populateVOfrmRS(vo, rs);
					lstBroughtByDtlVO.add(vo);
				}
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
		return lstBroughtByDtlVO;
	}
	
	public PatientBroughtByDtlVO getPatBroughtByDtlsEpisodeWise(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_patient_broughtby_dtl_by_episodewise(?,?,?,?,?, ?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		PatientBroughtByDtlVO objPatientBroughtByDtlVO=null;
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patBroughtByDtlVO.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_episode_code", _patBroughtByDtlVO.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_visit_no", _patBroughtByDtlVO.getEpisodeVisitNo(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(e.getMessage());
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
				objPatientBroughtByDtlVO= new PatientBroughtByDtlVO();
				HelperMethods.populateVOfrmRS(objPatientBroughtByDtlVO, rs);
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
		return objPatientBroughtByDtlVO;
	}
	//Added by Vasu on 09.May.18 to get patient maximum visit for old dept visit 
	public String getPatientMaxVisitNum(HisDAO daoObj,PatientBroughtByDtlVO _patientBroughtByDtlVO, UserVO _userVO,String strMode)
	{
		WebRowSet rs = null;
		//HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_BROUGHTBY_DTL_MAX_VISIT(?,?,?, ?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String visitNum = "";
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patientBroughtByDtlVO.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_episode_code", _patientBroughtByDtlVO.getEpisodeCode(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			//strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(e.getMessage());
		}

		try
		{
			 
			if (!rs.next())
			{
				System.out.println("no records");
				throw new HisRecordNotFoundException("No Brought By Detail For This CrNo Found");
			}
			else
			{
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				visitNum = rs.getString(1);
				rs.beforeFirst();
				//HelperMethods.populateVOfrmRS(_patBroughtByDtlVO, rs);
				
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
	return visitNum;
	}
	
	//End Vasu
}
