package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AddressVO;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import opd.OpdConfig;

import registration.RegistrationConfig;

/**
 * EpisodeDAO is a class which describes all the methods required for database interaction
 * for HRGT_EPISODE_DTL table, for example, insert, update, select and delete.
 * EpisodeDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class EpisodeDAO extends RegistrationDAO implements EpisodeDAOi
{

	/**
	 * Creates a new EpisodeDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public EpisodeDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	/**
	 * Creates an entry in DB for an episode of a patient.
	 * @param	_episodeVO	Provides address details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	EpisodeVO with values stored in DB.
	 */
	public EpisodeVO create(EpisodeVO _episodeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_DTL";

		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_episodeVO.setSeatId(_userVO.getSeatId());

		try
		{
			populateMap(_episodeVO, _userVO, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}
		return _episodeVO;
	}
	public EpisodeVO create(HisDAO daoObj, EpisodeVO _episodeVO, UserVO _userVO)
	{
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_episodeVO);
			_episodeVO.setSeatId(_userVO.getSeatId());
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _episodeVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _episodeVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _episodeVO.getEpisodeVisitNo(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", _episodeVO.getEpisodeVisitType(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _episodeVO.getEpisodeTypeCode(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_episode_action_code", _episodeVO.getEpisodeActionCode().equals("")?"0":_episodeVO.getEpisodeActionCode(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", _episodeVO.getAdmissionNo().equals("")?"0":_episodeVO.getAdmissionNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", _episodeVO.getMlcNo(),9);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _episodeVO.getDepartmentUnitCode().substring(0,3),10);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _episodeVO.getDepartmentUnitCode(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _episodeVO.getQueNo(),12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _episodeVO.getPatIsOld(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date","",14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgtm_episode_time","",15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_next_visit_date", _episodeVO.getEpisodeNextVisitDate(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_next_visit_dept_code", _episodeVO.getEpisodeNextVisitDeptCode().equals("")?"0":_episodeVO.getEpisodeNextVisitDeptCode(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", _episodeVO.getEpisodeIsOpen(),18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_complained_dtl", _episodeVO.getComplainDetail(),19);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_no", _episodeVO.getEmpNo(),20);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_name", _episodeVO.getEmpName(),21);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _episodeVO.getSeatId(),22);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date", "",23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", _episodeVO.getIsConfirmed(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", "1",25);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _episodeVO.getRoomCode(),26);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_ward_code", _episodeVO.getWardCode().equals("")?"0":_episodeVO.getWardCode(),27);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_next_visit_type", _episodeVO.getEpisodeNextVisitType().equals("")?"0":_episodeVO.getEpisodeNextVisitType(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_nxtvisit_deptunitcode", _episodeVO.getEpisodeNextVisitDeptUnitCode().equals("")?"0":_episodeVO.getEpisodeNextVisitDeptUnitCode(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_referral_acceptance", _episodeVO.getEpisodeReferAccept(),30);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_referral_acceptance_date", _episodeVO.getEpisodeReferAcceptDate(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _episodeVO.getPatSecondaryCatCode().equals("")?"0":_episodeVO.getPatSecondaryCatCode(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _episodeVO.getPatPrimaryCatCode(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _episodeVO.getIsReferred().equals("")?"0":_episodeVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _episodeVO.getFileNo(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_exp_date",_episodeVO.getExpiryDate(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),37);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),38);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint", _episodeVO.getIsCardPrint(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _episodeVO.getDisasterId().equals("")?"0":_episodeVO.getDisasterId(),40);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _episodeVO.getOldCrNo(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _episodeVO.getOldRegDate(),42);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,43);
			
			
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
						/*Date dt=new Date();
						dt=WebUTIL.getDateFromString(_episodeVO.getEpisodeDate(),"");
						Calendar cal = Calendar.getInstance();  
						cal.setTime(dt);  
						cal.add(Calendar.DATE, Integer.parseInt(Config.HOSPITAL_RENEWAL_EXPIRY_DAY)); // add 10 days  
						  
						_episodeVO.setExpiryDate(cal.getTime().toString());*/
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
		return _episodeVO;
	}
	public EpisodeVO create(EpisodeVO _episodeVO, UserVO _userVO, String daysOrMonthForRenewalExpiry)
	{
		System.out.println("inside create of EpisodeDAO");
		String query = "";
		String queryKey = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		if (daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_DAY)) queryKey = "INSERT.HRGT_EPISODE_DTL_DAY";
		else
		{
			if (daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_MONTH)) queryKey = "INSERT.HRGT_EPISODE_DTL_MONTH";
		}
		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		_episodeVO.setSeatId(_userVO.getSeatId());

		try
		{
			populateMapWithDaysOrMonthRenewalExp(_episodeVO, _userVO, populateMAP,daysOrMonthForRenewalExpiry);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}
		return _episodeVO;
	}
	
	public EpisodeVO createNewRegistration(EpisodeVO _episodeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_DTL_NEW_REGISTRATION";

		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_episodeVO.setSeatId(_userVO.getSeatId());

		try
		{
			populateMapNewRegistration(_episodeVO, _userVO, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}
		return _episodeVO;
	}
	
	public EpisodeVO createNewRegistration(HisDAO daoObj,EpisodeVO _episodeVO, UserVO _userVO)
	{
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			/*strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			method.setNullToEmpty(_episodeVO);
			_episodeVO.setSeatId(_userVO.getSeatId());
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _episodeVO.getPatCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _episodeVO.getEpisodeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _episodeVO.getEpisodeVisitNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", _episodeVO.getEpisodeVisitType());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _episodeVO.getEpisodeTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_episode_action_code", _episodeVO.getEpisodeActionCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", _episodeVO.getAdmissionNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", _episodeVO.getMlcNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _episodeVO.getDepartmentUnitCode().substring(0,3));
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _episodeVO.getDepartmentUnitCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _episodeVO.getQueNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _episodeVO.getPatIsOld());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgtm_episode_time","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_next_visit_date", _episodeVO.getEpisodeNextVisitDate());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_next_visit_dept_code", _episodeVO.getEpisodeNextVisitDeptCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", _episodeVO.getEpisodeIsOpen());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_complained_dtl", _episodeVO.getComplainDetail());
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_no", _episodeVO.getEmpNo());
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_name", _episodeVO.getEmpName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _episodeVO.getSeatId());
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date", "");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", _episodeVO.getIsConfirmed());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", "1");
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _episodeVO.getRoomCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_ward_code", _episodeVO.getWardCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_next_visit_type", _episodeVO.getEpisodeNextVisitType());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_nxtvisit_deptunitcode", _episodeVO.getEpisodeNextVisitDeptUnitCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_referral_acceptance", _episodeVO.getEpisodeReferAccept());
			daoObj.setProcInValue(nProcIndex1, "p_gdt_referral_acceptance_date", _episodeVO.getEpisodeReferAcceptDate());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _episodeVO.getPatSecondaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _episodeVO.getPatPrimaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _episodeVO.getIsReferred());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _episodeVO.getFileNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_exp_date",_episodeVO.getExpiryDate());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint", _episodeVO.getIsCardPrint());
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _episodeVO.getDisasterId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _episodeVO.getOldCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _episodeVO.getOldRegDate());
			daoObj.setProcOutValue(nProcIndex1, "err", 1);
			
			
			daoObj.execute(nProcIndex1,1);			
			*/
			

			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_episodeVO);
			_episodeVO.setSeatId(_userVO.getSeatId());
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _episodeVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _episodeVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _episodeVO.getEpisodeVisitNo(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", _episodeVO.getEpisodeVisitType(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _episodeVO.getEpisodeTypeCode(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_episode_action_code", _episodeVO.getEpisodeActionCode().equals("")?"0":_episodeVO.getEpisodeActionCode(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", _episodeVO.getAdmissionNo().equals("")?"0":_episodeVO.getAdmissionNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", _episodeVO.getMlcNo(),9);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _episodeVO.getDepartmentUnitCode().substring(0,3),10);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _episodeVO.getDepartmentUnitCode(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _episodeVO.getQueNo(),12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _episodeVO.getPatIsOld(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date","",14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgtm_episode_time","",15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_next_visit_date", _episodeVO.getEpisodeNextVisitDate(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_next_visit_dept_code", _episodeVO.getEpisodeNextVisitDeptCode().equals("")?"0":_episodeVO.getEpisodeNextVisitDeptCode(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", _episodeVO.getEpisodeIsOpen(),18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_complained_dtl", _episodeVO.getComplainDetail(),19);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_no", _episodeVO.getEmpNo(),20);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_name", _episodeVO.getEmpName(),21);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _episodeVO.getSeatId(),22);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date", "",23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", _episodeVO.getIsConfirmed(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", "1",25);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _episodeVO.getRoomCode(),26);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_ward_code", _episodeVO.getWardCode().equals("")?"0":_episodeVO.getWardCode(),27);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_next_visit_type", _episodeVO.getEpisodeNextVisitType().equals("")?"0":_episodeVO.getEpisodeNextVisitType(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_nxtvisit_deptunitcode", _episodeVO.getEpisodeNextVisitDeptUnitCode().equals("")?"0":_episodeVO.getEpisodeNextVisitDeptUnitCode(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_referral_acceptance", _episodeVO.getEpisodeReferAccept(),30);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_referral_acceptance_date", _episodeVO.getEpisodeReferAcceptDate(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _episodeVO.getPatSecondaryCatCode().equals("")?"0":_episodeVO.getPatSecondaryCatCode(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _episodeVO.getPatPrimaryCatCode(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _episodeVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _episodeVO.getFileNo(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_exp_date",_episodeVO.getExpiryDate(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),37);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),38);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint", _episodeVO.getIsCardPrint(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _episodeVO.getDisasterId().equals("")?"0":_episodeVO.getDisasterId(),40);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _episodeVO.getOldCrNo(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _episodeVO.getOldRegDate(),42);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,43);
			
			
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
		return _episodeVO;
	}

	public EpisodeVO createNewRegistration(EpisodeVO _episodeVO, UserVO _userVO, String daysOrMonthForRenewalExpiry)
	{
		String query = "";
		String queryKey = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		if (daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_DAY)) queryKey = "INSERT.HRGT_EPISODE_DTL_DAY_NEW_REGISTRATION";
		else
		{
			if (daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_MONTH)) queryKey = "INSERT.HRGT_EPISODE_DTL_MONTH_NEW_REGISTRATION";
		}
		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		_episodeVO.setSeatId(_userVO.getSeatId());

		try
		{
			populateMapWithDaysOrMonthRenewalExpNewRegistration(_episodeVO, _userVO, populateMAP,daysOrMonthForRenewalExpiry);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}
		return _episodeVO;
	}


	/**
	 * Populates the map with the episode details to be entered in the DB.
	 * @param	_episodeVO	Provides episode details to be entered.
	 * @param	_populateMAP	Map containig values which will be used for insert query.
	 */
	public void populateMap(EpisodeVO _episodeVO, UserVO _userVO, Map _populateMap) throws SQLException
	{

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		/*_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());//commented 
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo()); //on
		_populateMap.put(sq.next(), _userVO.getHospitalCode());*/ //24 jun 09
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());//added on 24 jun 09 change reverted
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeTypeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeActionCode());
		_populateMap.put(sq.next(), _episodeVO.getAdmissionNo());
		_populateMap.put(sq.next(), _episodeVO.getMlcNo());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentCode());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getQueNo());
		_populateMap.put(sq.next(), _episodeVO.getPatIsOld());
		//_populateMap.put(sq.next(),_episodeVO.getEpisodeDate());
		//_populateMap.put(sq.next(),_episodeVO.getEpisodeTime());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDate());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeIsOpen());
		_populateMap.put(sq.next(), _episodeVO.getComplainDetail());
		_populateMap.put(sq.next(), _episodeVO.getEmpNo());
		_populateMap.put(sq.next(), _episodeVO.getEmpName());
		_populateMap.put(sq.next(), _episodeVO.getSeatId());
		//_populateMap.put(sq.next(),_episodeVO.getEntryDate());
		_populateMap.put(sq.next(), _episodeVO.getIsConfirmed());
		_populateMap.put(sq.next(), _episodeVO.getIsValid());
		_populateMap.put(sq.next(), _episodeVO.getRoomCode());
		_populateMap.put(sq.next(), _episodeVO.getWardCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAccept());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAcceptDate());
		_populateMap.put(sq.next(), _episodeVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getPatPrimaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getIsReferred());
		_populateMap.put(sq.next(), _episodeVO.getFileNo());
		_populateMap.put(sq.next(), _episodeVO.getExpiryDate());
		_populateMap.put(sq.next(), _userVO.getIpAddress());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _episodeVO.getIsCardPrint());
		_populateMap.put(sq.next(), _episodeVO.getSecCatExpDate());
		_populateMap.put(sq.next(), _episodeVO.getDisasterId());
		_populateMap.put(sq.next(), _episodeVO.getOldCrNo());
		_populateMap.put(sq.next(), _episodeVO.getOldRegDate());

	}

	public void populateMapWithDaysOrMonthRenewalExp(EpisodeVO _episodeVO, UserVO _userVO, Map _populateMap,String daysOrMonthForRenewalExpiry)
			throws SQLException
	{

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());//comment on 24 jun 09
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		//_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());//added on 24 jun09 change reverted
		/*_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		
		_populateMap.put(sq.next(), _userVO.getHospitalCode());*/
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeTypeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeActionCode());
		_populateMap.put(sq.next(), _episodeVO.getAdmissionNo());
		_populateMap.put(sq.next(), _episodeVO.getMlcNo());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentCode());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getQueNo());
		_populateMap.put(sq.next(), _episodeVO.getPatIsOld());
		//_populateMap.put(sq.next(),_episodeVO.getEpisodeDate());
		//_populateMap.put(sq.next(),_episodeVO.getEpisodeTime());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDate());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeIsOpen());
		_populateMap.put(sq.next(), _episodeVO.getComplainDetail());
		_populateMap.put(sq.next(), _episodeVO.getEmpNo());
		_populateMap.put(sq.next(), _episodeVO.getEmpName());
		_populateMap.put(sq.next(), _episodeVO.getSeatId());
		//_populateMap.put(sq.next(),_episodeVO.getEntryDate());
		_populateMap.put(sq.next(), _episodeVO.getIsConfirmed());
		_populateMap.put(sq.next(), _episodeVO.getIsValid());
		_populateMap.put(sq.next(), _episodeVO.getRoomCode());
		_populateMap.put(sq.next(), _episodeVO.getWardCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAccept());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAcceptDate());
		_populateMap.put(sq.next(), _episodeVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getPatPrimaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getIsReferred());
		_populateMap.put(sq.next(), _episodeVO.getFileNo());
		if (daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_MONTH))
		_populateMap.put(sq.next(), _episodeVO.getExpiryDate());
		_populateMap.put(sq.next(), _episodeVO.getExpiryDate());
		_populateMap.put(sq.next(), _userVO.getIpAddress());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _episodeVO.getIsCardPrint());
		_populateMap.put(sq.next(), _episodeVO.getDisasterId());
	}
	
	public void populateMapNewRegistration(EpisodeVO _episodeVO, UserVO _userVO, Map _populateMap) throws SQLException
	{

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeTypeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeActionCode());
		_populateMap.put(sq.next(), _episodeVO.getAdmissionNo());
		_populateMap.put(sq.next(), _episodeVO.getMlcNo());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentCode());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getQueNo());
		_populateMap.put(sq.next(), _episodeVO.getPatIsOld());
		//_populateMap.put(sq.next(),_episodeVO.getEpisodeDate());
		//_populateMap.put(sq.next(),_episodeVO.getEpisodeTime());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDate());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeIsOpen());
		_populateMap.put(sq.next(), _episodeVO.getComplainDetail());
		_populateMap.put(sq.next(), _episodeVO.getEmpNo());
		_populateMap.put(sq.next(), _episodeVO.getEmpName());
		_populateMap.put(sq.next(), _episodeVO.getSeatId());
		//_populateMap.put(sq.next(),_episodeVO.getEntryDate());
		_populateMap.put(sq.next(), _episodeVO.getIsConfirmed());
		_populateMap.put(sq.next(), _episodeVO.getIsValid());
		_populateMap.put(sq.next(), _episodeVO.getRoomCode());
		_populateMap.put(sq.next(), _episodeVO.getWardCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAccept());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAcceptDate());
		_populateMap.put(sq.next(), _episodeVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getPatPrimaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getIsReferred());
		_populateMap.put(sq.next(), _episodeVO.getFileNo());
		_populateMap.put(sq.next(), _episodeVO.getExpiryDate());
		_populateMap.put(sq.next(), _userVO.getIpAddress());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _episodeVO.getIsCardPrint());
		_populateMap.put(sq.next(), _episodeVO.getDisasterId());
		_populateMap.put(sq.next(), _episodeVO.getOldCrNo());
		_populateMap.put(sq.next(), _episodeVO.getOldRegDate());

	}
	
	public void populateMapWithDaysOrMonthRenewalExpNewRegistration(EpisodeVO _episodeVO, UserVO _userVO, Map _populateMap,String daysOrMonthForRenewalExpiry)
	throws SQLException
			{
			
			Sequence sq = new Sequence();
			_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitType());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeTypeCode());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeActionCode());
			_populateMap.put(sq.next(), _episodeVO.getAdmissionNo());
			_populateMap.put(sq.next(), _episodeVO.getMlcNo());
			_populateMap.put(sq.next(), _episodeVO.getDepartmentUnitCode().substring(0,3));
			_populateMap.put(sq.next(), _episodeVO.getDepartmentUnitCode());
			_populateMap.put(sq.next(), _episodeVO.getQueNo());
			_populateMap.put(sq.next(), _episodeVO.getPatIsOld());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDate());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptCode());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeIsOpen());
			_populateMap.put(sq.next(), _episodeVO.getComplainDetail());
			_populateMap.put(sq.next(), _episodeVO.getEmpNo());
			_populateMap.put(sq.next(), _episodeVO.getEmpName());
			_populateMap.put(sq.next(), _episodeVO.getSeatId());
			_populateMap.put(sq.next(), _episodeVO.getIsConfirmed());
			_populateMap.put(sq.next(), _episodeVO.getIsValid());
			_populateMap.put(sq.next(), _episodeVO.getRoomCode());
			_populateMap.put(sq.next(), _episodeVO.getWardCode());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitType());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptUnitCode());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAccept());
			_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAcceptDate());
			_populateMap.put(sq.next(), _episodeVO.getPatSecondaryCatCode());
			_populateMap.put(sq.next(), _episodeVO.getPatPrimaryCatCode());
			_populateMap.put(sq.next(), _episodeVO.getIsReferred());
			_populateMap.put(sq.next(), _episodeVO.getFileNo());
			if (daysOrMonthForRenewalExpiry.equals(RegistrationConfig.ISEXPIRY_TYPE_MONTH))
			_populateMap.put(sq.next(), _episodeVO.getExpiryDate());
			_populateMap.put(sq.next(), _episodeVO.getExpiryDate());
			_populateMap.put(sq.next(), _userVO.getIpAddress());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _episodeVO.getIsCardPrint());
			_populateMap.put(sq.next(), _episodeVO.getDisasterId());
			_populateMap.put(sq.next(), _episodeVO.getOldCrNo());
			_populateMap.put(sq.next(), _episodeVO.getOldRegDate());
			}


	/**
	 * Retrieves all the episodes of a patient which are still open on the basis of CR No.
	 * Also checks that the episodes should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO[] retrieveByCrNo(String crNo, UserVO _userVO)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_OP_EPISODE_DTL_WITH_CRNO(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String amount="";
		EpisodeVO[] _episodeVO=null;		
		ValueObject[] vo =
		{};
		
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			//daoObj.setProcInValue(nProcIndex, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "p_crno", crNo,2);
			daoObj.setProcInValue(nProcIndex, "p_isvalid", Config.IS_VALID_ACTIVE,3);
			daoObj.setProcInValue(nProcIndex, "p_isepisodeopen",RegistrationConfig.EPISODE_ISOPEN_TRUE,4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println(strErr);
			if (strErr == null)
				strErr = "";

			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (!rs.next())
			{

				//throw new HisRecordNotFoundException("No Record Found Against This CrNo.");	 	    

				throw new HisRecordNotFoundException("No open episode found");

			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			//System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				//System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				//System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				//System.out.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			//System.out.println("after populating _episodeVO");
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:getAllOpenEpisodesVisitedToday():: " + e);
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}
	
		return _episodeVO;
	}

	
	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String crNo, UserVO _userVO)
	{

		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.OPEN.EPISODE.VISITED.TODAY.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();
		
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);

		
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{

				throw new HisRecordNotFoundException("Patient did not visit today");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:getAllOpenEpisodesVisitedToday():: " + e);
		}
		return _episodeVO;
	}

	public void updateEpisodeDtlForCMO(EpisodeVO _episodeVO, UserVO _userVO)
	{
		System.out.println("updateForEmergecyVisit");

		String query = "";
		Map populateMapforepisodeupdate = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HRGT_EPISODE_DTL_FOR_CMO";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			Sequence sq = new Sequence();
			//populateMapforepisodeupdate.put(sq.next(), _episodeVO.getEpisodeActionCode());
			populateMapforepisodeupdate.put(sq.next(), _episodeVO.getEpisodeIsOpen());
			populateMapforepisodeupdate.put(sq.next(), _episodeVO.getIsConfirmed());
			populateMapforepisodeupdate.put(sq.next(), _episodeVO.getPatCrNo());
			populateMapforepisodeupdate.put(sq.next(), _episodeVO.getEpisodeCode());
			populateMapforepisodeupdate.put(sq.next(), _episodeVO.getEpisodeVisitNo());
			populateMapforepisodeupdate.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, query, populateMapforepisodeupdate);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
	}

	public void updateEpisodeDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{
		System.out.println("updateForEmergecyVisit");

		String query = "";
		Map populateMapforepisodeupdate = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMapforUpdate(_episodeVO, populateMapforepisodeupdate);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, query, populateMapforepisodeupdate);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
	}

	/**
	 * Populates the map with the episode details to be updated in the DB.
	 * @param	_episodeVO	Provides episode details to be entered.
	 * @param	_populateMap	Map containig values which will be used for update query.
	 */
	public void populateMapforUpdate(EpisodeVO _episodeVO, Map _populateMap) throws HisApplicationExecutionException
	{

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeTypeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeActionCode());
		_populateMap.put(sq.next(), _episodeVO.getAdmissionNo());
		_populateMap.put(sq.next(), _episodeVO.getMlcNo());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentCode());
		_populateMap.put(sq.next(), _episodeVO.getDepartmentUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getQueNo());
		_populateMap.put(sq.next(), _episodeVO.getPatIsOld());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeDate());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeTime());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDate());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeIsOpen());
		_populateMap.put(sq.next(), _episodeVO.getComplainDetail());
		_populateMap.put(sq.next(), _episodeVO.getEmpNo());
		_populateMap.put(sq.next(), _episodeVO.getEmpName());
		_populateMap.put(sq.next(), _episodeVO.getSeatId());
		_populateMap.put(sq.next(), _episodeVO.getEntryDate());
		_populateMap.put(sq.next(), _episodeVO.getIsConfirmed());
		_populateMap.put(sq.next(), _episodeVO.getIsValid());
		_populateMap.put(sq.next(), _episodeVO.getRoomCode());
		_populateMap.put(sq.next(), _episodeVO.getWardCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitType());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeNextVisitDeptUnitCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAccept());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeReferAcceptDate());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());

	}

	/**
	 * Updates an episode record with the MLC number in the DB.
	 * @param	_mlcVO		Provides MLC details.
	 * @param	_userVO		Provides User details.
	 */
	public void episodeUpdateMlcNo(MlcVO _mlcVO, UserVO _userVO)
	{
		System.out.println("inside episodeUpdateMlcNo");
		System.out.println("_userVO" + _userVO.getSeatId());
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		Connection conn = super.getTransactionContext().getConnection();

		//--------*****----------Updating Episode Dtl to set MLc no
		Sequence sq = new Sequence();
		String queryEpisode = "";
		Map populateMAPEpisode = new HashMap();
		String queryEpisodeKey = "UPDATE.MLC_N0.HRGT_EPISODE_DTL";

		//populateMAPEpisode.put(sq.next(), _mlcVO.getPatMlcNo());   //#Changed by Akash Singh, Due to table changed in G5, dated on 20-07-2015
		populateMAPEpisode.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
		populateMAPEpisode.put(sq.next(), _mlcVO.getPatCrNo());
		populateMAPEpisode.put(sq.next(), _mlcVO.getEpisodeCode());
		populateMAPEpisode.put(sq.next(), _userVO.getHospitalCode());
		populateMAPEpisode.put(sq.next(), _mlcVO.getEpisodeVisitNo());

		try
		{
			queryEpisode = HelperMethodsDAO.getQuery(filename, queryEpisodeKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("queryEpisode.." + queryEpisode);

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, queryEpisode, populateMAPEpisode);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO:episodeUpdateMlcNo::Episode Details:: " + e);
		}
	}

	//#Added by Akash Singh, Due to table changed in G5, dated on 20-07-2015
	public void episodeDetailUpdateMlcFlag(MlcVO _mlcVO, UserVO _userVO)
	{
		System.out.println("inside episodeUpdateMlcNo");
		System.out.println("_userVO" + _userVO.getSeatId());
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		Connection conn = super.getTransactionContext().getConnection();

		//--------*****----------Updating Episode Dtl to set MLc no
		Sequence sq = new Sequence();
		String queryEpisode = "";
		Map populateMAPEpisode = new HashMap();
		String queryMlcKey ="UPDATE_MLC_N0.HRGT_EPISODE_DTL";
		populateMAPEpisode.put(sq.next(), "2");
		populateMAPEpisode.put(sq.next(), _mlcVO.getPatCrNo());
		populateMAPEpisode.put(sq.next(), _mlcVO.getEpisodeCode());
		populateMAPEpisode.put(sq.next(), _userVO.getHospitalCode());
		populateMAPEpisode.put(sq.next(), _mlcVO.getEpisodeVisitNo());
		

		try
		{
			queryEpisode = HelperMethodsDAO.getQuery(filename, queryMlcKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("queryEpisode.." + queryEpisode);

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, queryEpisode, populateMAPEpisode);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO:episodeUpdateMlcNo::Episode Details:: " + e);
		}
	}


	//#Added by Akash Singh, Due to table changed in G5, dated on 20-07-2015
	public void patientDetailUpdateMlcFlag(MlcVO _mlcVO, UserVO _userVO)
	{
		System.out.println("inside episodeUpdateMlcNo");
		System.out.println("_userVO" + _userVO.getSeatId());
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		Connection conn = super.getTransactionContext().getConnection();

		//--------*****----------Updating Episode Dtl to set MLc no
		Sequence sq = new Sequence();
		String queryEpisode = "";
		Map populateMAPEpisode = new HashMap();
		String queryMlcKey ="UPDATE_MLC_N0.HRGT_PATIENT_DTL";
		populateMAPEpisode.put(sq.next(), "1");
		populateMAPEpisode.put(sq.next(), _mlcVO.getPatCrNo());

		try
		{
			queryEpisode = HelperMethodsDAO.getQuery(filename, queryMlcKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("queryEpisode.." + queryEpisode);

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, queryEpisode, populateMAPEpisode);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO:episodeUpdateMlcNo::Episode Details:: " + e);
		}
	}
	
	//#Added by Akash Singh, Due to table changed in G5, dated on 20-07-2015
	public void dailyPatientDetailUpdateMlcFlag(MlcVO _mlcVO, UserVO _userVO)
	{
		System.out.println("inside episodeUpdateMlcNo");
		System.out.println("_userVO" + _userVO.getSeatId());
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		Connection conn = super.getTransactionContext().getConnection();

		//--------*****----------Updating Episode Dtl to set MLc no
		Sequence sq = new Sequence();
		String queryEpisode = "";
		Map populateMAPEpisode = new HashMap();
		String queryMlcKey ="UPDATE_MLC_N0.HRGT_DAILY_PATIENT_DTL";
		populateMAPEpisode.put(sq.next(), "2");
		populateMAPEpisode.put(sq.next(), _mlcVO.getPatCrNo());
		populateMAPEpisode.put(sq.next(), _mlcVO.getEpisodeCode());
		populateMAPEpisode.put(sq.next(), _userVO.getHospitalCode());
		populateMAPEpisode.put(sq.next(), _mlcVO.getEpisodeVisitNo());
		

		try
		{
			queryEpisode = HelperMethodsDAO.getQuery(filename, queryMlcKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("queryEpisode.." + queryEpisode);

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, queryEpisode, populateMAPEpisode);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO:episodeUpdateMlcNo::Episode Details:: " + e);
		}
	}

	/**
	 * Retrieves the episode of a patient which was opened in emergency on the basis of CR No.
	 * Also checks that the episodes should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient whose address is to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	EpisodeVO containing emergency episode details of a patient.
	 * @deprecated	No replacement. Catered in PatientBO.
	 * @see	registration.bo.PatientBO#searchPatientEpiosdeByCrNo(PatientVO, UserVO, Status, String, String)
	 */
	public EpisodeVO retrieveEmgEpisodeByCrNo(PatientVO _patientVO)
	{

		System.out.println("inside EpisodeVO[] retrieveByCrNo(String _crNo)");
		EpisodeVO _episodeVO = new EpisodeVO();
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DTL.EMG_EPISODE.RETRIEVE_BY_CRNO";

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_FALSE);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"
					+ e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Emergency Episode Found Against This CrNo.");
			}
			else
			{
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_episodeVO, rs);
				System.out.println("after populating _episodeVO");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			throw new HisException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	/**
	 * Updates an episode record with the Referral Acceptance Date of a patient 
	 * if the patient is referred from one department to some other department.
	 * If a ptient is referred internally, his previous episode detail record is updated for referral acceptance date.
	 * @param	_episodeReferVO		Provides episode refer details.
	 * @param	_userVO		Provides User details.
	 */
	public void episodeUpdateReferralAcceptanceDate(EpisodeReferVO _episodeReferVO, UserVO _userVO)
	{
		System.out.println("inside episodeUpdateReferralAcceptanceDate()");
		System.out.println("_userVO" + _userVO.getSeatId());
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		Connection conn = super.getTransactionContext().getConnection();

		Sequence sq = new Sequence();
		String queryEpisode = "";
		Map populateMAPEpisode = new HashMap();
		String queryEpisodeKey = "UPDATE.REFERRAL_ACCEPTANCE_DATE.HRGT_EPISODE_DTL";

		populateMAPEpisode.put(sq.next(), _episodeReferVO.getPatCrNo());
		//populateMAPEpisode.put(sq.next(),RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
		populateMAPEpisode.put(sq.next(), _episodeReferVO.getPrevEpisodeCode());
		populateMAPEpisode.put(sq.next(), _episodeReferVO.getPrevEpisodeVisitNo());

		try
		{
			queryEpisode = HelperMethodsDAO.getQuery(filename, queryEpisodeKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("queryEpisode.." + queryEpisode);

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, queryEpisode, populateMAPEpisode);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO:episodeUpdateMlcNo::Episode Details:: " + e);
		}
	}

	/**
	 * Retrieves the episode of a patient.
	 * @param	_episodeVO Provides Episode No of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO retrieveByEpisodeNo(EpisodeVO _episodeVO, UserVO _userVO)
	{

		System.out.println("inside retrieveByEpisodeNo()");
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.BY_EPISODE_NO.HRGT_EPISODE_DTL";
		//String queryKey="SELECT.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO"; 

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found For This CrNo. And Specified Episode Code");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_episodeVO, rs);
				System.out.println("_episodeVO.getEpisodeVisitType" + _episodeVO.getEpisodeVisitType());
				System.out.println("_episodeVO.getEpisodeVisitTypeCode" + _episodeVO.getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	/**
	 * Updates an entry in DB for referral record of an episode of a patient.
	 * Used at Registration desk.
	 * @param	_episodeVO	Provides episode details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	number of rows updated.
	 */
	public int updateEpisodeDtlAtREG(HisDAO daoObj, EpisodeVO _episodeVO, UserVO _userVO)
	{
		//String queryKey = "UPDATE.REF_DTL_REG.HRGT_EPISODE_DTL";
		int i=0;
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			/*strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_episodeVO);
			_episodeVO.setSeatId(_userVO.getSeatId());
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _episodeVO.getPatCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _episodeVO.getEpisodeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _episodeVO.getEpisodeVisitNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", _episodeVO.getEpisodeVisitType());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _episodeVO.getEpisodeTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_episode_action_code", _episodeVO.getEpisodeActionCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", _episodeVO.getAdmissionNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", _episodeVO.getMlcNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", "");
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _episodeVO.getDepartmentUnitCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _episodeVO.getQueNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _episodeVO.getPatIsOld());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgtm_episode_time","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_next_visit_date", _episodeVO.getEpisodeNextVisitDate());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_next_visit_dept_code", _episodeVO.getEpisodeNextVisitDeptCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", _episodeVO.getEpisodeIsOpen());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_complained_dtl", _episodeVO.getComplainDetail());
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_no", _episodeVO.getEmpNo());
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_name", _episodeVO.getEmpName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _episodeVO.getSeatId());
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date", "");
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", _episodeVO.getIsConfirmed());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", "1");
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _episodeVO.getRoomCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_ward_code", _episodeVO.getWardCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_next_visit_type", _episodeVO.getEpisodeNextVisitType());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_nxtvisit_deptunitcode", _episodeVO.getEpisodeNextVisitDeptUnitCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_referral_acceptance", _episodeVO.getEpisodeReferAccept());
			daoObj.setProcInValue(nProcIndex1, "p_gdt_referral_acceptance_date", _episodeVO.getEpisodeReferAcceptDate());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _episodeVO.getPatSecondaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _episodeVO.getPatPrimaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _episodeVO.getIsReferred());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _episodeVO.getFileNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_exp_date","");
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint", _episodeVO.getIsCardPrint());
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _episodeVO.getDisasterId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _episodeVO.getOldCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _episodeVO.getOldRegDate());
			daoObj.setProcOutValue(nProcIndex1, "err", 1);
			
			daoObj.execute(nProcIndex1,1);	*/
			
			
strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_episodeVO);
			_episodeVO.setSeatId(_userVO.getSeatId());
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _episodeVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _episodeVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _episodeVO.getEpisodeVisitNo(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", _episodeVO.getEpisodeVisitType(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _episodeVO.getEpisodeTypeCode(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_episode_action_code", _episodeVO.getEpisodeActionCode().equals("")?"0":_episodeVO.getEpisodeActionCode(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", _episodeVO.getAdmissionNo().equals("")?"0":_episodeVO.getAdmissionNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", _episodeVO.getMlcNo(),9);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code","",10);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _episodeVO.getDepartmentUnitCode(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _episodeVO.getQueNo(),12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _episodeVO.getPatIsOld(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date","",14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgtm_episode_time","",15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_next_visit_date", _episodeVO.getEpisodeNextVisitDate(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_next_visit_dept_code", _episodeVO.getEpisodeNextVisitDeptCode().equals("")?"0":_episodeVO.getEpisodeNextVisitDeptCode(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", _episodeVO.getEpisodeIsOpen(),18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_complained_dtl", _episodeVO.getComplainDetail(),19);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_no", _episodeVO.getEmpNo(),20);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_name", _episodeVO.getEmpName(),21);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _episodeVO.getSeatId(),22);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date", "",23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", _episodeVO.getIsConfirmed(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", "1",25);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _episodeVO.getRoomCode(),26);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_ward_code", _episodeVO.getWardCode().equals("")?"0":_episodeVO.getWardCode(),27);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_next_visit_type", _episodeVO.getEpisodeNextVisitType().equals("")?"0":_episodeVO.getEpisodeNextVisitType(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_nxtvisit_deptunitcode", _episodeVO.getEpisodeNextVisitDeptUnitCode().equals("")?"0":_episodeVO.getEpisodeNextVisitDeptUnitCode(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_referral_acceptance", _episodeVO.getEpisodeReferAccept(),30);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_referral_acceptance_date", _episodeVO.getEpisodeReferAcceptDate(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _episodeVO.getPatSecondaryCatCode().equals("")?"0":_episodeVO.getPatSecondaryCatCode(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _episodeVO.getPatPrimaryCatCode(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _episodeVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _episodeVO.getFileNo(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_exp_date","",36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),37);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),38);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint", _episodeVO.getIsCardPrint(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _episodeVO.getDisasterId().equals("")?"0":_episodeVO.getDisasterId(),40);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _episodeVO.getOldCrNo(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _episodeVO.getOldRegDate(),42);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,43);
			
			
			daoObj.execute(nProcIndex1,1);	
			
			if (strErr == null)
				strErr = "";

			if (!strErr.equals("")) 
			{
				throw new Exception(strErr);
			}
			else
			{
				i=0;
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

	/**
	 * Retrieves the departments in which a patient's episode is alreay opened.
	 * @param	_episodeVO	provides CR No and episode no.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getOpenEpisodeDepartment(EpisodeVO _episodeVO, UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.LIST.OPEN_EPISODE_DEPT.HRGT_EPISODE_DTL";

		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query::" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _episodeVO.getPatCrNo());
		populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _episodeVO.getPatCrNo());
		populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_FALSE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _episodeVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Open Episode Found");
			}
			rs.beforeFirst();
			System.out.println("after executing query");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		/*catch(HisRecordNotFoundException e){
			throw new HisRecordNotFoundException("HelperMethodsDAO.executeQuery"+e);
		}
		catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery"+e);
		}*/
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getOpenEpisodeDepartment:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	/**
	 * Updates an entry in DB for change secondary category of a patient during an episode.
	 * @param	_episodeVO	Provides episode details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	number of rows updated.
	 */
	public int updateSecondaryCategoryEpisodeDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{

		System.out.println("inside updateSecondaryCategoryEpisodeDtl() in Episode dao");
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.SECONDARY_CATEGORY.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode().trim());
		System.out.println("_episodeVO.getEntryDate()   " + _episodeVO.getEntryDate());

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
			System.out.println("");

			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return i;
	}

	public int updateFileNoEpisodeDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{

		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getFileNo());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());
		
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.FILENO.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
			System.out.println("");

			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return i;
	}

	/**
	 * Updates all entries in DB for change of primary category of a patient for all open episodes.
	 * @param	_episodeVO	Provides episode details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	number of rows updated.
	 */
	public int updatePrimaryCategoryEpisodeDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{

		System.out.println("inside updatePrimaryCategoryEpisodeDtl() in Episode dao");
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE_ALL.PRIMARY_CATEGORY.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getPatPrimaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		System.out.println("_episodeVO.getEntryDate()   " + _episodeVO.getEntryDate());

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);

			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO.updatePrimaryCategoryEpisodeDtl()::while updating data " + e);
		}
		return i;
	}

	/**
	 * Retrieves all the episodes of a patient.
	 * Also checks that the episodes should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO[] retrieveAllByCrNo(PatientVO _patientVO, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveAllByCrNo(PatientVO _patientVO, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.RETRIEVE_ALL_BY_CRNO.HRGT_EPISODE_DTL";
		//String queryKey="SELECT.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO"; 

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.IS_EPISODE_OPEN);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.IS_EPISODE_OPEN);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found For This CrNo.");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	public String isPatientMLC(PatientVO _patientVO, UserVO _userVO)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_MLC_DTL(?,?,?,?::character varying,?::character varying,?::character varying,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patientVO.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_episode_type_code", RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC,4);
			daoObj.setProcInValue(nProcIndex, "p_episode_code", "",5);
			daoObj.setProcInValue(nProcIndex, "p_episode_is_open", RegistrationConfig.EPISODE_ISOPEN_TRUE,6);
			/*daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid", Config.IS_VALID_ACTIVE,7);*/
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e
					+ strErr);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
    	String mlcNumber = "";
		try
		{
	 	    if(rs.next()){
	 	    	rs.beforeFirst();
	 	    	for(int i=0;rs.next();i++)
	 	    	{
	 	    		if(i>0)
	 	    			mlcNumber=mlcNumber+" & "+rs.getString(4);
	 	    		else
	 	    			mlcNumber=rs.getString(4);
	 	    	
	 	    	}
	 	    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return mlcNumber;
	}

	/**
	 * Checks whether any episode for a patient which was opened in emergency on the basis of CR No.
	 * Also checks that the episodes should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient whose address is to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Number of episodes currently opened in Emergency.
	 */
	public int checkOpenEmgEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside EpisodeVO[] retrieveByCrNo(String _crNo)");
		EpisodeVO _episodeVO = new EpisodeVO();
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DTL.CHECK_OPEN_EMG_EPISODE.BY_CRNO";
		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_FALSE);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);

		Connection conn = super.getTransactionContext().getConnection();
		int x = 0;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"
					+ e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next()) x = 0;
			rs.beforeFirst();
			while (rs.next())
				x++;
			System.out.println("x..... " + x);
		}
		catch (Exception e)
		{
			throw new HisException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}

		return x;
	}

	public EpisodeVO getLastEmergencyEpisode(PatientVO _patVO, UserVO _userVO)
	{
		EpisodeVO episodeVO = new EpisodeVO();
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.LASTEPISODE.EMERGENCY.HRGT_EPISODE_DTL";
		//String queryKey="SELECT.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO";													  														

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _patVO.getPatCrNo());
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
		//_populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC); //#Changed by Akash Singh, Due to table changed in G5, dated on 20-07-2015 	
		_populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
		//_populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC); //#Changed by Akash Singh, Due to table changed in G5, dated on 20-07-2015 	
		/*_populateMap.put(sq.next(), _patVO.getPatCrNo());
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
		_populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);
		_populateMap.put(sq.next(), _patVO.getPatCrNo());
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);*/
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Emergency Episode Found");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(episodeVO, rs);
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return episodeVO;
	}

	/**
	 * Retrieves all the episodes of a patient where patient has been referred.
	 * Also checks that the episodes should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO[] retrieveAllReferredEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveAllByCrNo(PatientVO _patientVO, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.RETRIEVE_REFERRED_ALL_BY_CRNO.HRGT_EPISODE_DTL";
		//String queryKey="SELECT.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO"; 

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _patientVO.getPatCrNo());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.IS_REFERRED_TRUE);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Referred Episode Found For This CrNo.");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	/**
	 * Retrieves all episodes of a patient currently opened in OPD.
	 * Also checks that the episodes should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO[] retrieveAllOpenOPDByCrNo(String crNo, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveAllOpenOPDByCrNo(String crNo, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.RETRIEVE_ALL_OPEN_OPD_EPISODES_BY_CRNO.HRGT_EPISODE_DTL";
		//String queryKey="SELECT.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO"; 

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Open OPD Found Against This CrNo.");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllOpenOPDByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	public EpisodeVO[] retrieveAllSpecialEpisodes(String crNo, UserVO _userVO)
	{
		System.out.println("inside EpisodeVO[] retrieveAllOpenOPDByCrNo(String crNo, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.RETRIEVE_ALL_OPEN_OPD_EPISODES_BY_CRNO.HRGT_EPISODE_DTL";
		//String queryKey="SELECT.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO"; 

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Special Episode Found Against This CrNo.");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllOpenOPDByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	/**
	 * Retrieves file number of the patient for each deaprtment.
	 * Also checks that the episodes should be valid.
	 * @param	crNo	Provides CRNo of the patient.
	 * @param	_userVO	Provides User details.
	 * @return	Map of department code and file number as key-value pair.
	 */
	public Map getDeptWiseFileNo(String crNo, UserVO _userVO)
	{
		System.out.println("inside getDeptWiseFileNo()");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.GET_FILE_NO.HRGT_EPISODE_DTL";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Departmentt Wise FileNo Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		//List alRecord = new ArrayList();
		Map mapRecord = new HashMap();

		try
		{
			rs.beforeFirst();
			while (rs.next())
			{
				mapRecord.put(rs.getString(1), rs.getString(2));
			}
			//alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		System.out.println("mapRecord dept wise file no" + mapRecord);
		return mapRecord;
	}

	public EpisodeVO[] getDeptWiseFileNoChange(String crNo, UserVO _userVO)
	{
		System.out.println("inside getDeptWiseFileNo()");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		ValueObject[] vo =
		{};
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.GET_FILE_NO_CHANGE.HRGT_EPISODE_DTL";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(),RegistrationConfig.EPISODE_ISOPEN_TRUE);
		//populateMAP.put(sq.next(),RegistrationConfig.IS_FILE_REQUIRED_TRUE); flag not required, now 1,2 is checked

		int count;

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
			if (!rs.next()) 
				//throw new HisRecordNotFoundException("Patient Did Not Visit any Unit Today");
				throw new HisRecordNotFoundException("Either Patient Did Not Visit any Unit Today or FileNo is not Required");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());

			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		//List alRecord = new ArrayList();
		EpisodeVO[] _episodeVO;

		try
		{

			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
			
				_episodeVO[i] = (EpisodeVO) vo[i];
				}
			

			//alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return _episodeVO;
	}

	public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, UserVO _userVO)
	{

		//public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, String primCatCode, UserVO _userVO){

		System.out.println("inside getDeptWiseRenewalOfRegistration()");
		String charges;
		ResultSet rs = null;
		String query = "";
		//String queryForrenewalCharges="";
		Map populateMAP = new HashMap();
		ValueObject[] vo =
		{};
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.GET_RENEWAL_DTL.HRGT_EPISODE_DTL.GBLT_DEPARTMENT_MST_NEW";

		//String queryKeyForrenewalCharges="SELECT.GET_RENEWAL_CHARGES.HBLT_CHARGE_MST";
		EpisodeVO[] _episodeVO;
		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			//queryForrenewalCharges=HelperMethodsDAO.getQuery(filename,queryKeyForrenewalCharges);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), crNo);

			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			// rs.last();
			// count=rs.getRow();
			//  rs.beforeFirst();
			if (!rs.next())

			throw new HisRenewalRequiredException("Renewal Not required");
		}
		catch (HisRenewalRequiredException e)
		{
			throw new HisRenewalRequiredException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{

			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];

			}
			System.out.println("after populating _episodeVO");

			//alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		/*  try{
		=======
		  try{
		>>>>>>> 1.49
			  
			  Sequence sq=new Sequence();
			  populateMAP.put(sq.next(),RegistrationConfig.REGISTRATION_SERVICE_ID);
			  populateMAP.put(sq.next(),RegistrationConfig.RENEWAL_TARIFF_ID);
			  populateMAP.put(sq.next(),primCatCode);
			  populateMAP.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE);
			  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),queryForrenewalCharges,populateMAP);
			  System.out.println("");
		  }
		  catch(Exception e){
			  throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
		  }
		  try{
			  if(!rs.next()){
				 charges="0.00";
				 }
			  else{
			  rs.beforeFirst();
			  rs.next();
			  charges=rs.getString(1);
			 // rs.last();
			  rs.beforeFirst();
			  }
			  for(int i=0;i<_episodeVO.length;i++){
				  _episodeVO[i].setAmount(charges);
			  }
			
		  }
		  catch(Exception e){
			  throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
		  }
		 */
		return _episodeVO;
	}

	public String[] getDeptForRenewal(String crNo, UserVO _userVO)
	{
		System.out.println("inside getDeptForRenewal()");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.GET_RENEWAL_DTL.HRGT_EPISODE_DTL.GBLT_DEPARTMENT_MST";
		String[] dept;
		System.out.println("query" + query);
		int count = 0;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), crNo);
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			count = rs.getRow();
			/*rs.beforeFirst();
			
			if(!rs.next())    			  
				  throw new HisRecordNotFoundException("getDeptWiseFileNo");*/
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{
			if (count > 0)
			{
				dept = new String[count];
				int i = 0;
				rs.beforeFirst();
				while (rs.next())
				{
					dept[i] = rs.getString(1);
					i++;
				}
			}
			else dept = null;
			//System.out.println("dept.length....."+dept.length);    		     	
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return dept;
	}

	public String[] getDeptNoCollectionAtNewDeptVisit(String crNo, UserVO _userVO)
	{
		System.out.println("inside getDeptForCollectionAtNewDeptVisit()");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.DEPT_NO_AMOUNT_COLLECTION_FOR_NEW_DEPT_VISIT";
		String[] dept;
		System.out.println("query" + query);
		int count = 0;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), crNo);
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			count = rs.getRow();
			/*rs.beforeFirst();
			
			if(!rs.next())    			  
				  throw new HisRecordNotFoundException("getDeptWiseFileNo");*/
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{
			if (count > 0)
			{
				dept = new String[count];
				int i = 0;
				rs.beforeFirst();
				while (rs.next())
				{
					dept[i] = rs.getString(1);
					i++;
				}
			}
			else dept = null;
			//System.out.println("dept.length....."+dept.length);    		     	
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return dept;
	}

	public void saveDeptWiseRenewalDtl(String patCrNo, String episodeCode, String visitNo, String expiryDate)
	{
		System.out.println("inside create of EpisodeDAO");
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "";

		queryKey = "Update.EXPIRYDATE.HRGT_EPISODE_DTL";

		int check;

		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);

		try
		{
			Sequence sq = new Sequence();

			populateMAP.put(sq.next(), expiryDate);
			populateMAP.put(sq.next(), patCrNo);
			populateMAP.put(sq.next(), episodeCode);
			populateMAP.put(sq.next(), visitNo);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			check = HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}

	}

	public String[] getEpisdoeCode(String crNo, String deptUnitCode)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.EPICODE.HRGT_EPISODE_DTL";
		String[] epicodeAndVisitNo = null;
		ResultSet rs = null;
		String dept[]=null;
		int count;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("query" + query);
		}

		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), deptUnitCode);
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();

			count=rs.getRow();
			/*rs.beforeFirst();
			
			if(!rs.next())    			  
    			  throw new HisRecordNotFoundException("getDeptWiseFileNo");*/
    	  }catch(Exception e){
    		  throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	  }
    	  
    	  try{
    		  if(count>0){
    			  dept=new String[count];
    	    	  int i=0;
    	    	  rs.beforeFirst();
        		  while(rs.next()){
        			  dept[i]=rs.getString(1);
        			  i++;
        		  }
    		  }
    		  else
    			  dept=null;
    		 //System.out.println("dept.length....."+dept.length);    		     	
    	  }catch(Exception e){
    		  throw new HisDataAccessException("getDeptWiseFileNo:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
    	  }    	     	  
    	  return dept;
      }

	public String saveDeptWiseRenewalDtl(HisDAO daoObj,String patCrNo,String episodeCode,String visitNo,String expiryDate,UserVO _userVO) {
		//System.out.println("inside create of EpisodeDAO");
		
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		String nextExpiaryDate="";
		try 
		{
		//strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_episode_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			
								
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2",1);//---------------
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", patCrNo,2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", episodeCode,3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no",visitNo,4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", "",5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code","",6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_episode_action_code", "",7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", "",8);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mlc_no", "",9);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", "",10);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode","",11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", "",12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old","",13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date","",14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgtm_episode_time","",15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_next_visit_date", "",16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_next_visit_dept_code","",17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", "",18);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_complained_dtl", "",19);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_no", "",20);
			daoObj.setProcInValue(nProcIndex1, "p_psrstr_emp_name", "",21);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", "",22);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date", "",23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", "",24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", "1",25);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code","",26);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_ward_code", "",27);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_next_visit_type", "",28);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_nxtvisit_deptunitcode","",29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_referral_acceptance", "",30);
			daoObj.setProcInValue(nProcIndex1, "p_gdt_referral_acceptance_date", "",31);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", "",32);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", "",33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", "",34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", "",35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_exp_date",expiryDate,36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),37);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),38);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint", "",39);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", "",40);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", "",41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate","",42);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,43);
			//daoObj.setProcOutValue(nProcIndex1, "nextexpiraydate", 1);			
			
			daoObj.executeProcedureByPosition(nProcIndex1);		
			
			
			
			
			
			
			
			
			strErr = daoObj.getString(nProcIndex1, "err");
			//nextExpiaryDate = daoObj.getString(nProcIndex1, "nextexpiraydate");
			
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
			 		
	 	return nextExpiaryDate;
	}
	
	public void saveDeptWiseRenewalDtl(String patCrNo,String episodeCode,String visitNo,String expiryDate,UserVO _userVO) {
		System.out.println("inside create of EpisodeDAO");
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	 	String queryKey="";
	 	
	 	queryKey="Update.EXPIRYDATE.HRGT_EPISODE_DTL";
	 	
	 	int check;
	 	
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	System.out.println("query"+query);		 	 
	 	
		
	 	try{
	 		Sequence sq=new Sequence();
	 		
	 		populateMAP.put(sq.next(),expiryDate);
	 		populateMAP.put(sq.next(),expiryDate);
	 		populateMAP.put(sq.next(),patCrNo);
	 		populateMAP.put(sq.next(),episodeCode);
	 		populateMAP.put(sq.next(),visitNo);
	 		populateMAP.put(sq.next(),_userVO.getHospitalCode());
	 	
	 		
	 	}catch(Exception e){
			 throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		check=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	
	 	}catch(Exception e){
	 		 throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	
	 		
	 	
	}

public String[] getEpisdoeCode(HisDAO daoObj,String crNo, String deptUnitCode,UserVO _userVO){
		
		WebRowSet webRs = null;
		String strProcName = "{call PKG_REG_VIEW.PROC_EPICODE_HRGT_EPISODE_DTL(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String[] epicodeAndVisitNo = null;
	 
		
	 	try{
			
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_crno",crNo,3);
			daoObj.setProcInValue(nProcIndex, "p_deptunit", deptUnitCode,4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			webRs.last();
			webRs.beforeFirst();

			ResultSetMetaData resultSetMetaData = webRs.getMetaData();
			int count = resultSetMetaData.getColumnCount();
			webRs.next();
			epicodeAndVisitNo = new String[count];
			System.out.println(webRs.getString(1));
			epicodeAndVisitNo[0] = webRs.getString(1);
			epicodeAndVisitNo[1] = webRs.getString(2);
			epicodeAndVisitNo[2] = webRs.getString(3);
			System.out.println("");
			/*rs.beforeFirst();
			
			if(!rs.next())    			  
				  throw new HisRecordNotFoundException("getDeptWiseFileNo");*/
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	 	finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return epicodeAndVisitNo;
	}
	public String[] getEpisdoeCode(String crNo, String deptUnitCode,UserVO _userVO){
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	 	String queryKey="SELECT.EPICODE.HRGT_EPISODE_DTL";	 	 
	 	String[] epicodeAndVisitNo = null;
	 	ResultSet rs=null;
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
			System.out.println("query"+query);
		}
		
		catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		System.out.println("query"+query);
		try{
			Sequence sq=new Sequence();
			populateMAP.put(sq.next(),crNo);
			populateMAP.put(sq.next(),deptUnitCode);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			rs.last();
			rs.beforeFirst();

			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			int count = resultSetMetaData.getColumnCount();
			rs.next();
			epicodeAndVisitNo = new String[count];
			System.out.println(rs.getString(1));
			epicodeAndVisitNo[0] = rs.getString(1);
			epicodeAndVisitNo[1] = rs.getString(2);
			epicodeAndVisitNo[2] = rs.getString(3);
			System.out.println("");
			/*rs.beforeFirst();
			
			if(!rs.next())    			  
				  throw new HisRecordNotFoundException("getDeptWiseFileNo");*/
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return epicodeAndVisitNo;
	}

	/**
	 * Retrieves all the episodes of a patient who is already registered in the OPD.
	 * Also checks that the episodes should be valid and active.
	 * @param	crNo	Provides CR No of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO[] retrieveOldPatientEpisodes(String crNo, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveOldPatientEpisodes(String _crNo, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.OLD_PATIENT_EPISODES.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(),crNo);
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),_userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(),crNo);
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),crNo);
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),_userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(),_userVO.getSeatId());
		//_populateMapVisitedDept.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE);

	/*	_populateMapVisitedDept.put(sq.next(),crNo);
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),crNo);	
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),crNo);	
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),_userVO.getHospitalCode());
    	_populateMapVisitedDept.put(sq.next(),crNo);
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),crNo);	
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);*/
		//_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
	//	_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
	//	_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);
		/*_populateMapVisitedDept.put(sq.next(),_userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(),_userVO.getSeatId());*/
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Either No open episode found Or No Department Unit Assigned To this Seat ID");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveOldPatientEpisodes::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	/**
	 * Retrieves all the episodes of a patient who is already registered in the OPD.
	 * Also checks that the episodes should be valid and active.
	 * @param	crNo	Provides CR No of the patient.
	 * @param	_userVO		Provides User details.
	 * @param serviceId 
	 * @param tariffId 
	 * @param patCatagoryCode 
	 * @param isRenewal 
	 * @return	Array of EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO[] retrieveOldPatientEpisodes(String crNo, UserVO _userVO, String isRenewal, String patCatagoryCode, String tariffId, String serviceId,String episodeType) throws Exception
	{
		//PROC_OLD_PATIENT_EPISODE_CRNO
		EpisodeVO[] _episodeVO=null;
		ValueObject[] vo =
		{};
		WebRowSet rs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_OLD_PATIENT_EPISODE_CRNO(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			if(episodeType.equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL))
				daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			if(episodeType.equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL))
				daoObj.setProcInValue(nProcIndex, "p_modeVal", "3",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_crno",crNo,3);
			daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
			daoObj.setProcInValue(nProcIndex, "p_seatid",_userVO.getSeatId(),5);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			daoObj.setProcInValue(nProcIndex, "p_isrenewal", isRenewal,8);
			
			daoObj.setProcInValue(nProcIndex, "p_patcatagory", patCatagoryCode,9);
			daoObj.setProcInValue(nProcIndex, "p_tariffcode", tariffId,10);
			daoObj.setProcInValue(nProcIndex, "p_serviceid", serviceId,11);
			

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			System.out.println("strErr----------->"+strErr);
			if (strErr == null)
				strErr = "";

			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("Either No open episode found Or No Department Unit Assigned To this Seat ID");
				}
				rs.beforeFirst();
				//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				System.out.println("length" + vo.length);
				_episodeVO = new EpisodeVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					System.out.println("before casting");
					_episodeVO[i] = (EpisodeVO) vo[i];
					System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
					System.out
							.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
				}
				System.out.println("after populating _episodeVO");
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}	
		
		return _episodeVO;
	}
	
	public EpisodeVO[] retrieveOldEmgPatientEpisodes(String crNo, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveOldPatientEpisodes(String _crNo, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.OLDEMR_PATIENT_EPISODES.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();
		//_populateMapVisitedDept.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE);

		_populateMapVisitedDept.put(sq.next(),crNo);
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),crNo);	
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),crNo);	
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),_userVO.getHospitalCode());
    	_populateMapVisitedDept.put(sq.next(),crNo);
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(),crNo);	
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
	//	_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
	//	_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);
		_populateMapVisitedDept.put(sq.next(),_userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(),_userVO.getSeatId());
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Either No open episode found Or No Department Unit Assigned To this Seat ID");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveOldPatientEpisodes::Episode Details:: " + e);
		}
		return _episodeVO;
	}


	public EpisodeVO[] retrieveAllEpisodes(String crNo, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveOldPatientEpisodes(String _crNo, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.ALL_OPEN_EPISODES.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();
		//_populateMapVisitedDept.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveOldPatientEpisodes::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	public boolean saveRenewalDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public EpisodeVO getEpisodeVisitByCrno(String _patCrNo, String _visitDate, String _unitCode,UserVO userVO)
	{

		EpisodeVO episodeVO = new EpisodeVO();
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.VISIT.BY.DATE.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();

		_populateMapVisitedDept.put(sq.next(), _patCrNo);
		_populateMapVisitedDept.put(sq.next(), _unitCode);
		_populateMapVisitedDept.put(sq.next(), _visitDate);
		_populateMapVisitedDept.put(sq.next(), userVO.getHospitalCode());

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (rs.next()) HelperMethods.populateVOfrmRS(episodeVO, rs);
			else throw new HisRecordNotFoundException("Patient did not visit the unit on specified date");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveOldPatientEpisodes::Episode Details:: " + e);
		}
		return episodeVO;
	}

	public void saveDiagnosisDetails(EpisodeVO episodeVO, UserVO userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "";

		queryKey = "Update.DIAGNOSISDETAILS.HRGT_EPISODE_DTL";

		int check;

		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);

		try
		{
			Sequence sq = new Sequence();

			populateMAP.put(sq.next(), episodeVO.getComplainDetail());
			populateMAP.put(sq.next(), episodeVO.getEpisodeIsOpen());
			populateMAP.put(sq.next(), episodeVO.getEpisodeNextVisitDate());
			populateMAP.put(sq.next(), episodeVO.getEpisodeNextVisitDeptUnitCode());
			populateMAP.put(sq.next(), episodeVO.getEmpNo());
			populateMAP.put(sq.next(), episodeVO.getEmpName());
			populateMAP.put(sq.next(), episodeVO.getPatCrNo());
			populateMAP.put(sq.next(), episodeVO.getEpisodeCode());
			populateMAP.put(sq.next(), episodeVO.getEpisodeVisitNo());

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			check = HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}

	}

	/**
	 * Updates the HRGNUM_IS_CONFIRMED and HRGNUM_IS_EPISODE_OPEN records of a Patient.  
	 * @param	patCrNo of the Patient.
	 * @param	visitNo of the Patient.
	 * @param	episodeCode of the Patient.
	 * @param	isConfirmed value that has to be updated.
	 * @param	episodeIsOpen of the Patient.
	 * @return	Number of records updated.
	 */

	public int updateEpisodeIsOpenAndIsConfirm(String patCrNo, String visitNo, String episodeCode, String isConfirmed,
			String episodeIsOpen, UserVO _userVO)
	{
		int x = 0;
		System.out.println("updateForEmergecyVisit");

		String query = "";
		Map populateMapforepisodeupdate = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.HRGT_EPISODE_DTL.EPISODE_IS_OPEN";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			Sequence sq = new Sequence();
			populateMapforepisodeupdate.put(sq.next(), isConfirmed);
			populateMapforepisodeupdate.put(sq.next(), episodeIsOpen);
			populateMapforepisodeupdate.put(sq.next(), _userVO.getSeatId());
			populateMapforepisodeupdate.put(sq.next(), patCrNo);
			populateMapforepisodeupdate.put(sq.next(), visitNo);
			populateMapforepisodeupdate.put(sq.next(), episodeCode);
			populateMapforepisodeupdate.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, query, populateMapforepisodeupdate);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
			else x = i;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return x;
	}

	/**
	 * Updates the HRGNUM_IS_CONFIRMED and HRGDT_NEXT_VISIT_DATE records of a Patient.  
	 * @param	patCrNo of the Patient.
	 * @param	visitNo of the Patient.
	 * @param	episodeCode of the Patient.
	 * @param	isConfirmed value that has to be updated.
	 * @param	nextVisitDate of the Patient.
	 * @return	Number of records updated.
	 */

	public int updateNextVisitDateAndIsConfirm(String patCrNo, String visitNo, String episodeCode, String isConfirmed,
			String nextVisitDate, UserVO _userVO)
	{
		int x = 0;
		System.out.println("updateForEmergecyVisit");

		String query = "";
		Map populateMapforepisodeupdate = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.HRGT_EPISODE_DTL.NEXT_VISIT_DATE";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			Sequence sq = new Sequence();
			populateMapforepisodeupdate.put(sq.next(), isConfirmed);
			populateMapforepisodeupdate.put(sq.next(), _userVO.getSeatId());
			populateMapforepisodeupdate.put(sq.next(), nextVisitDate);
			populateMapforepisodeupdate.put(sq.next(), patCrNo);
			populateMapforepisodeupdate.put(sq.next(), visitNo);
			populateMapforepisodeupdate.put(sq.next(), episodeCode);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(conn, query, populateMapforepisodeupdate);
			//if(i!=1){  
			if (i < 1)
			{ //-----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
			else x = i;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return x;
	}

	public void updateIsEpisodeOpen(String _patCrNo, String _episodeCode)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String fileName = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.IS_EPISODE_OPEN.HRGT_EPISODE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(fileName, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), _episodeCode);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeDAO:updateIsEpisodeOpen::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultSet" + e);
		}
	}

	public EpisodeVO[] retrieveEpisodeForProfileByCrNo(String _crNo, String _departmenttUnitCode, UserVO _userVO)
	{

		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.EPISODE_BY_CRNO_PAT_PROFILE.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();

		_populateMap.put(sq.next(), _crNo);
		_populateMap.put(sq.next(), _departmenttUnitCode);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No episode found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	public EpisodeVO[] reprintRetrieveByCrNo(String crNo, UserVO _userVO,String _reprintMode)
	{

	
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.REPRINT_CARD.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO";
		//

		Sequence sq = new Sequence();
		
		
		
		_populateMapVisitedDept.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		//_populateMapVisitedDept.put(sq.next(), _reprintMode);
		_populateMapVisitedDept.put(sq.next(), crNo);
		
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{

				//throw new HisRecordNotFoundException("No Record Found Against This CrNo.");	 	    

				throw new HisRecordNotFoundException("No Record For Reprint Found.Card Cannot Be Reprinted");

			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
			}
			}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	public EpisodeVO[] reprintRenewalRetrieveByCrNo(String crNo, UserVO _userVO)
	{

	
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "";
		//
		String renewalType=Config.RENEWAL_TYPE;
		Sequence sq = new Sequence();
		Sequence seq=new Sequence();
		
		//in aarogya renewal is of only one type
		/*if(renewalType.equals("1") || renewalType.equals("2"))
		{
			queryKey = "SELECT.RENEWAL_CARD.HOSPITAL.WISE.HRGT_RENEW_DTL";
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), crNo);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		if(renewalType.equals("3") || renewalType.equals("4") || renewalType.equals("5"))
		{*/
			queryKey = "SELECT.RENEWAL_CARD.UNIT.WISE.HRGT_RENEW_DTL";
			_populateMap.put(seq.next(), crNo);
			_populateMap.put(seq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(seq.next(), _userVO.getHospitalCode());
		//}

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
			{

				//throw new HisRecordNotFoundException("No Record Found Against This CrNo.");	 	    

				throw new HisRecordNotFoundException("No Record For Renewal Found. Card Cannot Be Printed");

			}
			rs.beforeFirst();
		
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	public EpisodeVO[] DuplicateRetrieveByCrNo(String crNo,UserVO userVO)
	{
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.DUPLICATE_CARD.HRGT_EPISODE_DTL.RETRIEVE_BY_CRNO";
	
		Sequence sq = new Sequence();
		

		_populateMapVisitedDept.put(sq.next(), userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), crNo);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		//System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{

				//throw new HisRecordNotFoundException("No Record Found Against This CrNo.");	 	    

				throw new HisRecordNotFoundException("No open episode found");

			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
	
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
	
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	
	}
	
	public EpisodeVO[] retrieveOldPatientEpisodesByDept(String crNo, String dept, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveOldPatientEpisodes(String _crNo, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		int nProcIndex = 0;
		HisDAO daoObj = null;
		try
		{
		
		String strProcName = "{call PKG_REG_VIEW.PROC_OLDPAT_EPISODE_BYDEPT(?,?,?,?,?,?,?,?,?)}";

	

		String strErr = "";		

		daoObj = new HisDAO("Registration","EssentialDAO");

		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_crno", crNo,2);
		daoObj.setProcInValue(nProcIndex, "p_moduleId", Config.MODULE_ID_REGISTRATION,3);
		daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId(),4);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),5);
		daoObj.setProcInValue(nProcIndex, "p_dept", dept,6);
		daoObj.setProcInValue(nProcIndex, "p_isvalid", Config.IS_VALID_ACTIVE,7);
		daoObj.setProcOutValue(nProcIndex, "err", 1,8);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
		
		//daoObj.executeProcedure(nProcIndex);
		daoObj.executeProcedureByPosition(nProcIndex);	
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		try
		{
			ResultSet rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Either No Open Episode  found Or Department Unit Not Assigned To this Seat ID");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveOldPatientEpisodes::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	public EpisodeVO[] retrieveOldPatientEpisodesByUnit(String crNo, String unit, UserVO _userVO)
	{

		System.out.println("inside EpisodeVO[] retrieveOldPatientEpisodes(String _crNo, UserVO _userVO)");
		EpisodeVO[] _episodeVO;
		ValueObject[] vo =
		{};
		int nProcIndex = 0;
		HisDAO daoObj = null;
		try
		{
		
		String strProcName = "{call PKG_REG_VIEW.PROC_OLDPAT_EPISODE_BYUNIT(?,?,?,?,?,?,?,?,?)}";

	

		String strErr = "";		

		daoObj = new HisDAO("Registration","EssentialDAO");

		nProcIndex = daoObj.setProcedure(strProcName);

		daoObj.setProcInValue(nProcIndex, "p_modeVal", "1");

		daoObj.setProcInValue(nProcIndex, "p_crno", crNo);
		daoObj.setProcInValue(nProcIndex, "p_moduleId", Config.MODULE_ID_REGISTRATION);
		daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId());
		daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode());
		daoObj.setProcInValue(nProcIndex, "p_deptunit", unit);
		daoObj.setProcInValue(nProcIndex, "p_isvalid", Config.IS_VALID_ACTIVE);
		daoObj.setProcOutValue(nProcIndex, "err", 1);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2);
		

		daoObj.executeProcedure(nProcIndex);


		
		}
		
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		

		try
		{
			ResultSet rs =daoObj.getWebRowSet(nProcIndex, "resultset");
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Either No open Episode found Or Department Unit Not Assigned To this Seat ID");
			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			System.out.println("length" + vo.length);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				_episodeVO[i] = (EpisodeVO) vo[i];
				System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
				System.out
						.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
			}
			System.out.println("after populating _episodeVO");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveOldPatientEpisodes::Episode Details:: " + e);
		}
		return _episodeVO;
	}

	/** Retrieving All Open MLC Episodes 
	 * @param _patCrNo Patient CR Number
	 * @param _userVO User VO
	 * @return Array of Episode VOs of Open MLC Episode
	 */
	public EpisodeVO[] retrieveAllOpenTodayMLCEpisodes(String _patCrNo, UserVO _userVO)
	{
		EpisodeVO[] _openMLCEpisodeVOs;
		ValueObject[] vo = {};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();

		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.RETRIEVE_ALL_OPEN_MLC_EPISODES_BY_CRNO.HRGT_EPISODE_DTL";		 

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		System.out.println("query" + query);

		Sequence sq = new Sequence();		
		try
		{
			_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMapVisitedDept.put(sq.next(), _patCrNo);
			_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
			//_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
			_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Either No MLC Episode For Conversion Exists Or Patient Did Not Visit Today");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_openMLCEpisodeVOs = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
				_openMLCEpisodeVOs[i] = (EpisodeVO) vo[i];
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllOpenMLCEpisodes::Episode Details:: " + e);
		}
		return _openMLCEpisodeVOs;
	}

	/**
	 * Updates an entry in DB for change of MLC episode to Non MLC
	 * @param	_episodeVO	Provides episode details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	number of rows updated.
	 */
	public int updateRevokedMLCEpisodeDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.REVOKE_MLC_EPISODE.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return i;
	}

	/**
	 * Updating Next Visit Detail
	 * @param _episodeVO Episode VO
	 * @param _userVO UserVO
	 */
	/**
	## 		Modification Log		: populateMAP.put(sq.next(), episodeVO.getEpisodeCloseDate());		
	##		Modify Date				: 19-02-2015
	##		Reason	(CR/PRS)		: Data model changed in table , So Episode Closed Date passed
	##		Modify By				: Akash Singh
	*/
	public void updateNextVisitDetail(EpisodeVO episodeVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.NEXT_VISIT_DETAIL.HRGT_EPISODE_DTL";
	//	String episodeKeyword="'"+episodeVO.getEpisodeKeywords()+"'";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			Sequence sq = new Sequence();
			String strEpisodeVisitNo= episodeVO.getEpisodeVisitNo().replace("^","@");
			System.out.println("strEpisodeVisitNo="+strEpisodeVisitNo);
			String strConsentNo=strEpisodeVisitNo.split("\\@")[0];
			System.out.println("strConsentNo="+strConsentNo);
			/*populateMAP.put(sq.next(), episodeVO.getEpisodeName());
			populateMAP.put(sq.next(), episodeVO.getComplainDetail());*/
			populateMAP.put(sq.next(), episodeVO.getEpisodeIsOpen());
			/*populateMAP.put(sq.next(), episodeVO.getEpisodeNextVisitDate());*/
			populateMAP.put(sq.next(), episodeVO.getIsConfirmed());
			populateMAP.put(sq.next(), episodeVO.getVisitReason());
			populateMAP.put(sq.next(), episodeVO.getVisitNotes());
			populateMAP.put(sq.next(), episodeVO.getEpisodeNextVisitDate());
			populateMAP.put(sq.next(), episodeVO.getNextVisitCriteria());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), episodeVO.getNextVisitDuration());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), episodeVO.getEpisodeKeywords());  
			//populateMAP.put(sq.next(), episodeVO.getEpisodeKeywordID());   //commented on 9.7.2016 autocomplete not in use
			populateMAP.put(sq.next(), episodeVO.getEpisodeSummary());
			populateMAP.put(sq.next(), episodeVO.getEpisodeCloseDate());
			
			//Added by manisha gangwar date :01.dec.2015 for updating appointment detail, when selected patient unit is apppointment based
			populateMAP.put(sq.next(), episodeVO.getPatNextAptNo());
			populateMAP.put(sq.next(), episodeVO.getPatNextAptQueueNo());
			populateMAP.put(sq.next(), episodeVO.getPatNextAptSlot());
			
			
			//snomd-ct integration date: 09.07.2016
			populateMAP.put(sq.next(), episodeVO.getSnomdCIdVisitReason());
			populateMAP.put(sq.next(), episodeVO.getSnomdPTVisitReason());
			populateMAP.put(sq.next(), episodeVO.getSnomdCIdKeywords());
			populateMAP.put(sq.next(), episodeVO.getSnomdPTKeywords());
			populateMAP.put(sq.next(), episodeVO.getSnomdCIdVisitNotes());
			populateMAP.put(sq.next(), episodeVO.getSnomdPTVisitNotes());
			populateMAP.put(sq.next(), episodeVO.getSnomdCIdEpisodeSummary());
			populateMAP.put(sq.next(), episodeVO.getSnomdPTEpisodeSummary());
		
			populateMAP.put(sq.next(), episodeVO.getPatCrNo());
			populateMAP.put(sq.next(), episodeVO.getEpisodeCode());
			populateMAP.put(sq.next(), episodeVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}
	}

	/**
	 * Retrieves the episode detail of a patient by visit No
	 * @param	_episodeVO Provides Episode No of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	EpisodeVO containing all the episode details of a patient.
	 */
	public EpisodeVO retrieveByEpisodeVisit(EpisodeVO _episodeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.BY_EPISODE_VISIT.HRGT_EPISODE_DTL";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		
		try
		{
			populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
			populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found For This CrNo. And Specified Episode Code");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_episodeVO, rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByEpisodeVisit::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	
	public void changeEpisodeDetails(EpisodeVO _episodeVO,UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
			String queryKey = "UPDATE.QUE_NO_ROOM_NO_HRGT_EPISODE_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();
		
			//populateMAP.put(sq.next(), _episodeVO.getQueNo());
			populateMAP.put(sq.next(), _episodeVO.getRoomCode());
			populateMAP.put(sq.next(), _episodeVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeVO.getEpisodeVisitNo());

			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			
			catch (Exception e)
			{
				throw new HisDataAccessException("HisDataAccessException :Change Episode details" + e);
			}
		}

	
	
	/** Retrieving All Episodes 
	 * @param _patCrNo Patient CR Number
	 * @param _userVO User VO
	 * @return Array of Episode VOs of Open Episode
	 */
	public EpisodeVO[] retrieveAllOpenEpisodesEMR(String _patCrNo, UserVO _userVO)
	{
		EpisodeVO[] openEpisodesEPR;
		ValueObject[] vo = {};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();

		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DTL.EMR";		 

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		System.out.println("query" + query);

		Sequence sq = new Sequence();		
		try
		{
			_populateMapVisitedDept.put(sq.next(), _patCrNo);
			_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
			//_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
			_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
			_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISDOE_VISIT_TYPE_NMLC);
			_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
			_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMapVisitedDept);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Episode Found Against This CrNo.");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			openEpisodesEPR=new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
				openEpisodesEPR[i] = (EpisodeVO) vo[i];
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllEpisodesEMR::Episode Details:: " + e);
		}
		return openEpisodesEPR;
	}

	
	/** Retrieving Next Visit Details
	 * @param _patCrNo Patient CR Number
	 * @param _userVO User VO
	 * @return Array of Episode VOs containing next visit details
	 */
	public EpisodeVO[] retrieveNextVsitDetails(String _patCrNo, UserVO _userVO)
	{
		EpisodeVO[] episodeVOs=null;
		ValueObject[] vo = {};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();

		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DTL.NEXT_VISIT_DETAIL_EMR";		 

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		System.out.println("query" + query);

		Sequence sq = new Sequence();		
		try
		{
			_populateMapVisitedDept.put(sq.next(), _patCrNo);
			_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMapVisitedDept);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No MLC Episode Found Against This CrNo.");
			}
			else
			{
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			episodeVOs=new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
				episodeVOs[i] = (EpisodeVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllOpenMLCEpisodes::Episode Details:: " + e);
		}
		return episodeVOs;
	}

	
	public String isDeadPatientMLC(PatientVO _patientVO, UserVO _userVO)
	{
		String mlcNumber = "";
		EpisodeVO[] _episodeVO;

		ValueObject[] vo= {};
		String query =  "" ;
		Map _populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.PATIENT_IS_MLC_FOR_DEAD";		
		Sequence sq=new Sequence();
				  
					  
			
		
		_populateMap.put(sq.next(),RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());	
	//	_populateMap.put(sq.next(),RegistrationConfig.EPISODE_ISOPEN_FALSE);		
		_populateMap.put(sq.next(),_patientVO.getPatCrNo());
		
		Connection conn =super.getTransactionContext().getConnection();
		
    	try{
	 	     query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}catch(Exception e){
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    	}
    	
    	System.out.println("query"+query);		 	 
	 	
		try{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);	 
	 	    if(rs.next()){
	 	    	rs.beforeFirst();
	 	    	for(int i=0;rs.next();i++)
	 	    	{
	 	    		if(i>0)
	 	    			mlcNumber=mlcNumber+" & "+rs.getString(1);
	 	    		else
	 	    			mlcNumber=rs.getString(1);
	 	    	
	 	    	}
	 	    }
	 	   	 	    	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		return mlcNumber;
	}
	
	public EpisodeVO[] getPatientAdmittedEpisodes(String crNo, UserVO _userVO)
	{
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT_ADMITTED_EPISODE.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();
		
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), OpdConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
		
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{

				throw new HisRecordNotFoundException("Patient did not visit today");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:getAllOpenEpisodesVisitedToday():: " + e);
		}
		return _episodeVO;
	}
	
	public int updateSecondaryCatNValidUpTo(EpisodeVO _episodeVO, UserVO _userVO)
	{
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.SECONDARY_CAT_N_VALIDUPTO.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
//added by anshul for postgres compatibility
		if( _episodeVO.getValidUpto().equalsIgnoreCase("")||
				_episodeVO.getValidUpto().equalsIgnoreCase(null)||
				_episodeVO.getValidUpto().equalsIgnoreCase(" "))
		{
			_episodeVO.setValidUpto("0");
			
		}
		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(),_episodeVO.getValidUpto());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo().trim());
		
		

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
			System.out.println("");

			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return i;
	}
	
	public int updateSecondaryCatNExpiryDate(EpisodeVO _episodeVO, UserVO _userVO)
	{
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.SECONDARY_CAT_N_EXPIRY_DATE.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		
		_populateMap.put(sq.next(), _episodeVO.getSecCatExpDate());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo().trim());
		
		

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
			System.out.println("");

			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return i;
	}
	
	public int updateRevokeSecondaryCat(EpisodeVO _episodeVO, UserVO _userVO)
	{
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.SECONDARY_CAT_REVOKE.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		
		_populateMap.put(sq.next(), _episodeVO.getSecCatExpDate());
		_populateMap.put(sq.next(), _episodeVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo().trim());
		
		

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
			System.out.println("");

			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return i;
	}
	
	public void updatePatCategory(EpisodeVO _episodeVO,UserVO _userVO)
	{
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.PAT_PRIMARY_CAT.HRGT_EPISODE_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		
		_populateMap.put(sq.next(), _episodeVO.getPatPrimaryCatCode());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim());
		//_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim().substring(0, 3));		

		
		

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, _populateMap);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
	}

	/**
	 * Retrieves all visit details of an episode of patient
	 * @param	_episodeVO Epispde Detail
	 * @param	_userVO	User Detail
	 * @return	List of EpisodeVO
	 */
	public List<EpisodeVO> retrieveAllVisitsByEpisodeNo(EpisodeVO _episodeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.ALL_VISITS_BY_EPISODE_NO.HRGT_EPISODE_DTL";
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			Sequence sq = new Sequence();
			
			populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.populateMap::retrieveAllVisitsByEpisodeNo::" + e);
		}

		ValueObject[] vo ={};
		List<EpisodeVO> lstEpisodeDetail = new ArrayList<EpisodeVO>();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				for(ValueObject o : vo)
					lstEpisodeDetail.add((EpisodeVO)o);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllVisitsByEpisodeNo::Episode Visit Details:: " + e);
		}
		return lstEpisodeDetail;
	}

	public int checkGeneralEpisodeInDepartment(EpisodeVO _episodeVO,UserVO _uservo) {
		String query = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.EXISTING_EPISODE_IN_DEPARTMENT.HRGT_ESPIDOE_DTL";
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			Sequence sq = new Sequence();
			
			populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			populateMap.put(sq.next(), _episodeVO.getDepartmentCode());
			populateMap.put(sq.next(), _uservo.getHospitalCode());
			populateMap.put(sq.next(), RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.populateMap::retrieveAllVisitsByEpisodeNo::" + e);
		}

	int count=0;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			
			if (rs.next())
			{
				//rs.beforeFirst();
				count=rs.getInt(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllVisitsByEpisodeNo::Episode Visit Details:: " + e);
		}
		return count;
		
	}
	
	public int checkUnitWiseEpisodeInDepartment(EpisodeVO _episodeVO,UserVO _uservo) {
		String query = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.EXISTING_EPISODE_IN_DEPARTMENT_UNIT.HRGT_ESPIDOE_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		
		try
		{
			Sequence sq = new Sequence();
			
			populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			populateMap.put(sq.next(), _episodeVO.getDepartmentCode());
			populateMap.put(sq.next(), _uservo.getHospitalCode());
			populateMap.put(sq.next(), _episodeVO.getDepartmentUnitCode());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.populateMap::retrieveAllVisitsByEpisodeNo::" + e);
		}
		
		int count=0;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			
			if (rs.next())
			{
				//rs.beforeFirst();
				count=rs.getInt(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllVisitsByEpisodeNo::Episode Visit Details:: " + e);
		}
		return count;
		
	}

	public EpisodeVO[] retrieveOldEmgPatientEpisodes(String crNo,
			UserVO _userVO, String renewalTariffId, String patPrimaryCatCode,
			String registrationServiceId,String isRenewal) {
		//PROC_OLD_PATIENT_EPISODE_CRNO
		EpisodeVO[] _episodeVO=null;
		ValueObject[] vo =
		{};
		WebRowSet rs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_OLD_PATIENT_EPISODE_CRNO(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_crno",crNo,3);
			daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,4);
			daoObj.setProcInValue(nProcIndex, "p_seatid",_userVO.getSeatId(),5);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			daoObj.setProcInValue(nProcIndex, "p_isrenewal", isRenewal,8);
			
			daoObj.setProcInValue(nProcIndex, "p_patcatagory", patPrimaryCatCode,9);
			daoObj.setProcInValue(nProcIndex, "p_tariffcode", renewalTariffId,10);
			daoObj.setProcInValue(nProcIndex, "p_serviceid", registrationServiceId,11);
			

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			//System.out.println("strErr----------->"+strErr);
			if (strErr == null)
				strErr = "";

			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("Either No open episode found Or No Department Unit Assigned To this Seat ID");
				}
				rs.beforeFirst();
				//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				//System.out.println("length" + vo.length);
				_episodeVO = new EpisodeVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					//System.out.println("before casting");
					_episodeVO[i] = (EpisodeVO) vo[i];
					//System.out.println("_episodeVO[" + i + "].getEpisodeVisitType" + _episodeVO[i].getEpisodeVisitType());
					//System.out
							//.println("_episodeVO[" + i + "].getEpisodeVisitTypeCode" + _episodeVO[i].getEpisodeVisitTypeCode());
				}
				//System.out.println("after populating _episodeVO");
			} 
			else 
			{
				
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
			e.printStackTrace();
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}
		return _episodeVO;
	}

	public String getExpiryDate(EpisodeVO episodeVO, UserVO _uservo) {
		String strExpiryDate=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			daoObj = new HisDAO("Registration","EpisodeDAO");
			funcIndex = daoObj.setFunction("{? = call REG_FUNCTION.FUN_GET_EXPIRY(?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, _uservo.getHospitalCode());
			daoObj.setFuncOutValue(funcIndex,1);
			
			daoObj.executeFunction(funcIndex);
			strExpiryDate = daoObj.getFuncString(funcIndex);
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("PatientDAO.generateCRno()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}
		return strExpiryDate;
	}
	
	public int updateMCTSRegNo(EpisodeVO _episodeVO, UserVO _userVO)
	{
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.MCTS.HRGT_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _episodeVO.getStrMctsNo());

		_populateMap.put(sq.next(), _episodeVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());		
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
			System.out.println("");

			if (i < 1)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeDAO::while updating data " + e);
		}
		return i;
	}
	
	public EpisodeVO[] getAllLatestEpisodesVisited(String crNo, UserVO _userVO)
	{

		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		String query = "";
		Map _populateMapVisitedDept = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.OPEN.EPISODE.VISITED.LATEST.HRGT_EPISODE_DTL";

		Sequence sq = new Sequence();
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);

		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		_populateMapVisitedDept.put(sq.next(), crNo);
		_populateMapVisitedDept.put(sq.next(), _userVO.getHospitalCode());
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapVisitedDept.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		_populateMapVisitedDept.put(sq.next(), Config.IS_VALID_ACTIVE);

		
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapVisitedDept);
			if (!rs.next())
			{

				throw new HisRecordNotFoundException("Patient did not visit today");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:getAllOpenEpisodesVisitedToday():: " + e);
		}
		return _episodeVO;
	}
	
	public void updateDailyPatientDetail(EpisodeVO episodeVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.NEXT_VISIT_DETAIL.HRGT_DAILY_PATIENT_DTL";
		/*String episodeKeyword="'"+episodeVO.getEpisodeKeywords()+"'";*/
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			Sequence sq = new Sequence();
			String strEpisodeVisitNo= episodeVO.getEpisodeVisitNo().replace("^","@");
			String strConsentNo=strEpisodeVisitNo.split("\\@")[0];
			
			/*populateMAP.put(sq.next(), episodeVO.getEpisodeName());
			populateMAP.put(sq.next(), episodeVO.getComplainDetail());*/
			populateMAP.put(sq.next(), episodeVO.getEpisodeIsOpen());
			/*populateMAP.put(sq.next(), episodeVO.getEpisodeNextVisitDate());*/
			populateMAP.put(sq.next(), episodeVO.getIsConfirmed());
			populateMAP.put(sq.next(), episodeVO.getVisitReason());
			populateMAP.put(sq.next(), episodeVO.getVisitNotes());
			populateMAP.put(sq.next(), episodeVO.getEpisodeNextVisitDate());
			populateMAP.put(sq.next(), episodeVO.getNextVisitCriteria());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), episodeVO.getNextVisitDuration());
			populateMAP.put(sq.next(), userVO.getSeatId());			
			populateMAP.put(sq.next(), episodeVO.getEpisodeKeywords());
			populateMAP.put(sq.next(), "");
		//	populateMAP.put(sq.next(), episodeVO.getEpisodeKeywordID());
			populateMAP.put(sq.next(), episodeVO.getEpisodeSummary());
			
			//Added by manisha gangwar date :01.dec.2015 for updating appointment detail, when selected patient unit is apppointment based
			populateMAP.put(sq.next(), episodeVO.getPatNextAptNo());
			populateMAP.put(sq.next(), episodeVO.getPatNextAptQueueNo());
			populateMAP.put(sq.next(), episodeVO.getPatNextAptSlot());
			

			populateMAP.put(sq.next(), episodeVO.getPatCrNo());
			populateMAP.put(sq.next(), episodeVO.getEpisodeCode());
			populateMAP.put(sq.next(), episodeVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EpisodeDAO:populateMap(_episodeVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}
	}
	
	
	/**Added by Vasu on 27.Nov.2018*/
	public List<EpisodeVO> retrieveAllEpisodeVisits(EpisodeVO _episodeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.ALL_EPISODE_VISITS.HRGT_EPISODE_DTL";
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			Sequence sq = new Sequence();
			
			populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			//populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			//populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.populateMap::retrieveAllVisitsByEpisodeNo::" + e);
		}

		ValueObject[] vo ={};
		List<EpisodeVO> lstEpisodeDetail = new ArrayList<EpisodeVO>();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				for(ValueObject o : vo)
					lstEpisodeDetail.add((EpisodeVO)o);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllVisitsByEpisodeNo::Episode Visit Details:: " + e);
		}
		return lstEpisodeDetail;
	}
}
