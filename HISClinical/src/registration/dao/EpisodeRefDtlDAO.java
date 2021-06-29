package registration.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import registration.RegistrationConfig;

public class EpisodeRefDtlDAO extends RegistrationDAO implements EpisodeRefDtlDAOi{

	public EpisodeRefDtlDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	public EpisodeRefDtlVO create(EpisodeRefDtlVO _episodeRefDtlVO, UserVO _userVO) {
		//System.out.println("inside create of EpisodeRefDtlVO");
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	 	String queryKey="INSERT.REFER_DTL.HRGT_EPISODE_REF_DTL";	 	  
	 	
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	//System.out.println("query"+query);		 	 
	 	_episodeRefDtlVO.setSeatId(_userVO.getSeatId());
		
	 	try{
			populateMap(_episodeRefDtlVO,_userVO,populateMAP);
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	return _episodeRefDtlVO;
		
		
	}
public EpisodeRefDtlVO create(HisDAO daoObj, EpisodeRefDtlVO _episodeRefDtlVO, UserVO _userVO) {
		
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_refer_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(_episodeRefDtlVO);
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_episodeRefDtlVO.getPatCrNo().equals("")?"0":_episodeRefDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _episodeRefDtlVO.getEpisodeCode().equals("")?"0": _episodeRefDtlVO.getEpisodeCode() ,3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _episodeRefDtlVO.getEpisodeVisitNo().equals("")?"0": _episodeRefDtlVO.getEpisodeVisitNo(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no","1",5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no",_episodeRefDtlVO.getPatAdmNo().equals("")?"0":_episodeRefDtlVO.getPatAdmNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code",_episodeRefDtlVO.getFromDepartmentCode().equals("")?"0":_episodeRefDtlVO.getFromDepartmentCode(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode",_episodeRefDtlVO.getFromDepartmentUnitCode().equals("")?"0":_episodeRefDtlVO.getFromDepartmentUnitCode(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code",_episodeRefDtlVO.getFromDoctorCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code",_episodeRefDtlVO.getFromWardCode().equals("")?"0":_episodeRefDtlVO.getFromWardCode(),10);
			if(_episodeRefDtlVO.getToDepartmentCode().length()>3)
			{
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",_episodeRefDtlVO.getToDepartmentCode().substring(0,3).equals("")?"0":_episodeRefDtlVO.getToDepartmentCode().substring(0,3),11);
			}else{
				daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",_episodeRefDtlVO.getToDepartmentCode().equals("")?"0":_episodeRefDtlVO.getToDepartmentCode(),11);	
			}
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",_episodeRefDtlVO.getToDepartmentUnitCode().equals("")?"0":_episodeRefDtlVO.getToDepartmentUnitCode() ,12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code",_episodeRefDtlVO.getToDoctorCode(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code",_episodeRefDtlVO.getToWardCode().equals("")?"0":_episodeRefDtlVO.getToWardCode(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",_episodeRefDtlVO.getToEpisodeCode().equals("")?"0":_episodeRefDtlVO.getToEpisodeCode(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",_episodeRefDtlVO.getToEpisodeVisitNo().equals("")?"0":_episodeRefDtlVO.getToEpisodeVisitNo(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code",_episodeRefDtlVO.getExternalHospitalCode().equals("")?"0":_episodeRefDtlVO.getExternalHospitalCode(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name",_episodeRefDtlVO.getExternalHospitalName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name",_episodeRefDtlVO.getExternalHospitalDoctorName(),19);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno",_episodeRefDtlVO.getExternalHospitalPatCrNo().equals("")?"0":_episodeRefDtlVO.getExternalHospitalPatCrNo(),20);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept",_episodeRefDtlVO.getExternalHospitalDepartment(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit",_episodeRefDtlVO.getExternalHospitalDepartmentUnit(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out",_episodeRefDtlVO.getIsRefferInOut().equals("")?"0":_episodeRefDtlVO.getIsRefferInOut(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL,24);	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL,25);	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL,26);	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL,27);	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","",28);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","",29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress(),30);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id",_episodeRefDtlVO.getSeatId().equals("")?"0":_episodeRefDtlVO.getSeatId(),31);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","",32);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",33);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_lstmod_date",_episodeRefDtlVO.getLastModifiedDate(),34);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid",_episodeRefDtlVO.getLastModifiedSeatId().equals("")?"0":_episodeRefDtlVO.getLastModifiedSeatId(),35);
			daoObj.setProcInValue(nProcIndex1, "p_gstr_remarks",_episodeRefDtlVO.getRemarks(),36);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode().equals("")?"0":_userVO.getHospitalCode(),37);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,38);
			
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}	
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
	 	return _episodeRefDtlVO;
		
		
	}
	
	public EpisodeRefDtlVO createNewRegistration(HisDAO daoObj,EpisodeRefDtlVO _episodeRefDtlVO, UserVO _userVO) {
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_refer_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(_episodeRefDtlVO);
			_episodeRefDtlVO.setSeatId(_userVO.getSeatId());
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_episodeRefDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",_episodeRefDtlVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",_episodeRefDtlVO.getEpisodeVisitNo(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no","1",5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no",_episodeRefDtlVO.getPatAdmNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code",_episodeRefDtlVO.getFromDepartmentCode(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode",_episodeRefDtlVO.getFromDepartmentUnitCode(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code",_episodeRefDtlVO.getFromDoctorCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code",_episodeRefDtlVO.getFromWardCode(),10);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",_episodeRefDtlVO.getToDepartmentCode(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",_episodeRefDtlVO.getToDepartmentUnitCode(),12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code",_episodeRefDtlVO.getToDoctorCode(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code",_episodeRefDtlVO.getToWardCode(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",_episodeRefDtlVO.getToEpisodeCode(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",_episodeRefDtlVO.getToEpisodeVisitNo(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code",_episodeRefDtlVO.getExternalHospitalCode(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name",_episodeRefDtlVO.getExternalHospitalName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name",_episodeRefDtlVO.getExternalHospitalDoctorName(),19);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno",_episodeRefDtlVO.getExternalHospitalPatCrNo(),20);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept",_episodeRefDtlVO.getExternalHospitalDepartment(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit",_episodeRefDtlVO.getExternalHospitalDepartmentUnit(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out",_episodeRefDtlVO.getIsRefferInOut(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL,24);	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int",RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL,25);	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL,26);	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int",RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL,27);	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","",28);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","",29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress(),30);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id",_episodeRefDtlVO.getSeatId(),31);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","",32);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",33);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_lstmod_date",_episodeRefDtlVO.getLastModifiedDate(),34);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid",_episodeRefDtlVO.getLastModifiedSeatId(),35);
			daoObj.setProcInValue(nProcIndex1, "p_gstr_remarks",_episodeRefDtlVO.getRemarks(),36);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode(),37);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,38);
			
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
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
		return _episodeRefDtlVO;
		
		
	}
	
	
	
		public void populateMap(EpisodeRefDtlVO _episodeRefDtlVO,UserVO _userVO,Map _populateMap)throws SQLException{
			Sequence sequence=new Sequence();
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getPatCrNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getEpisodeCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getEpisodeVisitNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getPatCrNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getEpisodeCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getEpisodeVisitNo());
			_populateMap.put(sequence.next(),_userVO.getHospitalCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getPatAdmNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromDepartmentCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromDepartmentUnitCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromDoctorCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromWardCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToDepartmentCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToDepartmentUnitCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToDoctorCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToWardCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToEpisodeCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToEpisodeVisitNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalName());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalDoctorName());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalPatCrNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalDepartment());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalDepartmentUnit());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsRefferInOut());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsRefferInOut());	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsRefferInOut());	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),_userVO.getIpAddress());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getSeatId());
			//_populateMap.put(sequence.next(),_episodeRefDtlVO.getEntryDate());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsValid());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getLastModifiedDate());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getLastModifiedSeatId());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getRemarks());
			_populateMap.put(sequence.next(),_userVO.getHospitalCode());
			}

		public void populateMapNewRegistration(EpisodeRefDtlVO _episodeRefDtlVO,UserVO _userVO,Map _populateMap)throws SQLException{
			Sequence sequence=new Sequence();
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getPatCrNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getEpisodeCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getEpisodeVisitNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getPatAdmNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromDepartmentCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromDepartmentUnitCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromDoctorCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getFromWardCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToDepartmentCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToDepartmentUnitCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToDoctorCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToWardCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToEpisodeCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getToEpisodeVisitNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalCode());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalName());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalDoctorName());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalPatCrNo());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalDepartment());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getExternalHospitalDepartmentUnit());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsRefferInOut());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsRefferInOut());	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);	// for reffer date
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsRefferInOut());	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL);	// for acceptance date
			_populateMap.put(sequence.next(),_userVO.getIpAddress());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getSeatId());
			//_populateMap.put(sequence.next(),_episodeRefDtlVO.getEntryDate());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getIsValid());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getLastModifiedDate());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getLastModifiedSeatId());
			_populateMap.put(sequence.next(),_episodeRefDtlVO.getRemarks());
			_populateMap.put(sequence.next(),_userVO.getHospitalCode());
			}

		
	public int updateRefEpisodeDtlAtREG(HisDAO daoObj, EpisodeRefDtlVO _episodeRefDtlVO, UserVO _userVO)
	{
		//String queryKey = "Update.HRGT_EPISODE_REF_DTL";
		int i=0;
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_refer_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(_episodeRefDtlVO);
			
			/*UPDATE  HRGT_EPISODE_REF_DTL SET 
			 * GDT_ACCEPTANCE_DATE = SYSDATE, 
			 * HGNUM_TO_DEPTUNITCODE = hgnum_to_deptunitcode,
			 * HRGSTR_IS_REFF_IN_OUT = hrgstr_is_reff_in_out,
			 * HRGSTR_TO_EPISODE_CODE=hrgstr_to_episode_code,
			 * HRGNUM_TO_VISIT_NO=hrgnum_to_visit_no 
			WHERE HRGNUM_PUK = hrgnum_puk 
			AND HRGNUM_EPISODE_CODE = hrgnum_episode_code 
			AND HRGNUM_VISIT_NO = hrgnum_visit_no 
			AND HRGNUM_S_NO = hrgnum_s_no 
			AND GNUM_HOSPITAL_CODE=gnum_hospital_code;*/
			
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "3");
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_episodeRefDtlVO.getPatCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",_episodeRefDtlVO.getEpisodeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",_episodeRefDtlVO.getEpisodeVisitNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no", _episodeRefDtlVO.getSerialNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code","");
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",_episodeRefDtlVO.getToDepartmentCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",_episodeRefDtlVO.getToDepartmentUnitCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",_episodeRefDtlVO.getToEpisodeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",_episodeRefDtlVO.getToEpisodeVisitNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code",_episodeRefDtlVO.getExternalHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name",_episodeRefDtlVO.getExternalHospitalName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name",_episodeRefDtlVO.getExternalHospitalDoctorName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno",_episodeRefDtlVO.getExternalHospitalPatCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept",_episodeRefDtlVO.getExternalHospitalDepartment());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit",_episodeRefDtlVO.getExternalHospitalDepartmentUnit());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext","");	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int","");	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext","");	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int","");	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","");
			daoObj.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id","");
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","");
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid","1");
			daoObj.setProcInValue(nProcIndex1, "p_gdt_lstmod_date","");
			daoObj.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid","");
			daoObj.setProcInValue(nProcIndex1, "p_gstr_remarks","");
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode());			
			daoObj.setProcOutValue(nProcIndex1, "err", 1);
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						i=0;
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
    	return i;
	}
	
	// getting patient for online department visit
	public EpisodeRefDtlVO[] getReferPat(UserVO _userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeRefDtlVO;
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getSeatId());
		_populateMap.put(sq.next(),Config.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS);
		
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("");
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeRefDtlVO=new EpisodeRefDtlVO[vo.length];
		
			for(int i=0;i<vo.length;i++)
			{
				_episodeRefDtlVO[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeRefDtlVO;
	}

	//update patient  for online department visit	
	public int updateAcceptanceDate(EpisodeRefDtlVO _episodeRefDtlVO,UserVO _userVO)
	{
		int i=0;
		String query="";
		Map _populateMap=new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.ACCEPTANCEDATE.HRGT_EPISODE_REF_DTL";		
    	Connection conn =super.getTransactionContext().getConnection();
    	
    	try
    	{
    		query=HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	Sequence sq=new Sequence();
    	
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToDepartmentUnitCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getIsRefferInOut());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToEpisodeCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToEpisodeVisitNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getPatCrNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getEpisodeCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getEpisodeVisitNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getSerialNo());
    	_populateMap.put(sq.next(),_userVO.getHospitalCode());
    	
    	try
    	{
    		i=HelperMethodsDAO.excecuteUpdate(conn,query,_populateMap);
    		if(i<1)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisUpdateUnsuccesfullException.class)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    		else
    		{
    			throw new HisDataAccessException("EpisodeReferDtlDAO::while updating data "+e);
    		}
    	}
    	return i;
		
	}
	public int updateAcceptanceDate(HisDAO daoObj,EpisodeRefDtlVO _episodeRefDtlVO,UserVO _userVO)
	{
		int i=0;
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_refer_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(_episodeRefDtlVO);
			
			/*UPDATE  HRGT_EPISODE_REF_DTL SET 
			 * GDT_ACCEPTANCE_DATE = SYSDATE, 
			 * HGNUM_TO_DEPTUNITCODE = hgnum_to_deptunitcode,
			 * HRGSTR_IS_REFF_IN_OUT = hrgstr_is_reff_in_out,
			 * HRGSTR_TO_EPISODE_CODE=hrgstr_to_episode_code,
			 * HRGNUM_TO_VISIT_NO=hrgnum_to_visit_no 
			WHERE HRGNUM_PUK = hrgnum_puk 
			AND HRGNUM_EPISODE_CODE = hrgnum_episode_code 
			AND HRGNUM_VISIT_NO = hrgnum_visit_no 
			AND HRGNUM_S_NO = hrgnum_s_no 
			AND GNUM_HOSPITAL_CODE=gnum_hospital_code;*/
			
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2",1);
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_episodeRefDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code",_episodeRefDtlVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",_episodeRefDtlVO.getEpisodeVisitNo(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no", _episodeRefDtlVO.getSerialNo(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no","",6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_dept_code","",7);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_from_deptunitcode","",8);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_from_doct_code","",9);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_from_ward_code","",10);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_dept_code",_episodeRefDtlVO.getToDepartmentCode(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_to_deptunitcode",_episodeRefDtlVO.getToDepartmentUnitCode(),12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_doct_code","",13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_ward_code","",14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_to_episode_code",_episodeRefDtlVO.getToEpisodeCode(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_to_visit_no",_episodeRefDtlVO.getToEpisodeVisitNo(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code","",17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name","",18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name","",19);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno","",20);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dept","",21);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit","",22);
			//daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out","",23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out",_episodeRefDtlVO.getIsRefferInOut().equals("")?"0":_episodeRefDtlVO.getIsRefferInOut(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_ext","",24);	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_in_out_int","",25);	// for reffer date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_ext","",26);	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_is_reff_acc_int","",27);	// for acceptance date
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_reff_date_time","",28);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_acceptance_date","",29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add","",30);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id","",31);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","",32);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid","1",33);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_lstmod_date","",34);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_lstmod_seatid","",35);
			daoObj.setProcInValue(nProcIndex1, "p_gstr_remarks","",36);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode(),37);			
			daoObj.setProcOutValue(nProcIndex1, "err", 1,38);
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						i=0;
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
    	return i;
		
	}
//	getting patient  for online Special Clinic Department visit
	
	public EpisodeRefDtlVO[] getSCReferPat(UserVO _userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.SPECIAL_CLINIC.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeRefDtlVO;
		
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getSeatId());
		_populateMap.put(sq.next(),Config.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("");
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeRefDtlVO=new EpisodeRefDtlVO[vo.length];
		
			for(int i=0;i<vo.length;i++)
			{
				_episodeRefDtlVO[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeRefDtlVO;
	}
	
//	update patient  for online special clinic department visit	
	public int updateSCAcceptanceDate(EpisodeRefDtlVO _episodeRefDtlVO,UserVO _userVO)
	{
		int i=0;
		String query="";
		Map _populateMap=new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.SC_ACCEPTANCEDATE.HRGT_EPISODE_REF_DTL";		
    	Connection conn =super.getTransactionContext().getConnection();
    	
    	try
    	{
    		query=HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	Sequence sq=new Sequence();
    	
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToDepartmentCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getIsRefferInOut());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToEpisodeCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToEpisodeVisitNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getPatCrNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getEpisodeCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getEpisodeVisitNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getSerialNo());
    	_populateMap.put(sq.next(),_userVO.getHospitalCode());
    	
    	try
    	{
    		i=HelperMethodsDAO.excecuteUpdate(conn,query,_populateMap);
    		if(i<1)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisUpdateUnsuccesfullException.class)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    		else
    		{
    			throw new HisDataAccessException("EpisodeReferDtlDAO::while updating data "+e);
    		}
    	}
    	return i;
		
	}
	
	public EpisodeRefDtlVO[] getOldPatReferDtl(UserVO userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.OLD_PAT_REFER.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeOldPatRefDtlVO;
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),userVO.getSeatId());
		_populateMap.put(sq.next(),Config.REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("");
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeOldPatRefDtlVO=new EpisodeRefDtlVO[vo.length];
		
			for(int i=0;i<vo.length;i++)
			{
				_episodeOldPatRefDtlVO[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeOldPatRefDtlVO;
	}

	
	public int updateOldPatVisitAcceptance(EpisodeRefDtlVO _episodeRefDtlVO,UserVO _userVO)
	{
		int i=0;
		String query="";
		Map _populateMap=new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="UPDATE.OLD_PAT_ACCEPTANCEDATE.HRGT_EPISODE_REF_DTL";		
    	Connection conn =super.getTransactionContext().getConnection();
    	
    	try
    	{
    		query=HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	Sequence sq=new Sequence();
    	
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToDepartmentCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToDepartmentUnitCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getToEpisodeCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getIsRefferInOut());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getPatCrNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getEpisodeCode());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getEpisodeVisitNo());
    	_populateMap.put(sq.next(),_episodeRefDtlVO.getSerialNo());
    	_populateMap.put(sq.next(),_userVO.getHospitalCode());
    	
    	try
    	{
    		i=HelperMethodsDAO.excecuteUpdate(conn,query,_populateMap);
    		if(i<1)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisUpdateUnsuccesfullException.class)
    		{
    			throw new HisUpdateUnsuccesfullException();
    		}
    		else
    		{
    			throw new HisDataAccessException("EpisodeReferDtlDAO::while updating data "+e);
    		}
    	}
    	return i;
		
	}
	
	/**
	 * get detail of episode refer of patient by crno and specific episode.
	 * @param userVO
	 * @return
	 */
	public EpisodeRefDtlVO[] getEpisodeReferDtlByCrNoEpisodeCode(EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.EPISODE_REF_DTL.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeRefDtlVOArray;
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),episodeRefDtlVO.getPatCrNo());
		_populateMap.put(sq.next(),episodeRefDtlVO.getEpisodeCode());
		_populateMap.put(sq.next(),episodeRefDtlVO.getEpisodeVisitNo());
		_populateMap.put(sq.next(),userVO.getHospitalCode());
				
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			/*if(!rs.next())
				throw new HisRecordNotFoundException("");*/
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeRefDtlVOArray=new EpisodeRefDtlVO[vo.length];
		
			for(int i=0;i<vo.length;i++)
			{
				_episodeRefDtlVOArray[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeRefDtlVOArray;
	}

	
	/**
	 * get detail of episode refer of patient by crno
	 * @param userVO
	 * @return
	 */
	public EpisodeRefDtlVO[] getEpisodeReferDtlByCrNoEmr(EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO)
	{
		ResultSet rs=null;
		String query="";
		Map _populateMap=new HashMap();
		ValueObject[] vo={};
		String fileName=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.BY_CR_NO.HRGT_EPISODE_REF_DTL";
		
		EpisodeRefDtlVO[] _episodeRefDtlVOArray;
		try
		{
			query=HelperMethodsDAO.getQuery(fileName,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),episodeRefDtlVO.getPatCrNo());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);
		_populateMap.put(sq.next(),RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
		
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,_populateMap);
			/*if(!rs.next())
				throw new HisRecordNotFoundException("");*/
		}
		catch(HisRecordNotFoundException e)
		{
			String msg=e.getMessage();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException(""+e);
		}
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(EpisodeRefDtlVO.class,rs);
			_episodeRefDtlVOArray=new EpisodeRefDtlVO[vo.length];
			
			for(int i=0;i<vo.length;i++)
			{
				_episodeRefDtlVOArray[i]=(EpisodeRefDtlVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("EpisodeRefDtlDAO::getReferPat"+e);
		}
		return _episodeRefDtlVOArray;
	}
	
	
}
