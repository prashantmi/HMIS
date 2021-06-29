package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import vo.registration.AddressVO;
import vo.registration.ChangeTreatmentCategoryVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationCancellationVO;
import vo.registration.DailyPatientVO;

/**
 * EpisodeDAO is a class which describes all the methods required for database interaction
 * for HRGT_EPISODE_DTL table, for example, insert, update, select and delete.
 * EpisodeDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class ChangeTreatmentCategoryDAO extends RegistrationDAO
{

	/**
	 * Creates a new EpisodeDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public ChangeTreatmentCategoryDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	//Start:Sheeldarshi:Registration Cancellation
	
	public List getTreatmentCatWithExpiryDays(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		List alRecord = new ArrayList();
		HisDAO daoObj = null;
		Map populateMAP = new HashMap();
		String strProcName = "{call Pkg_Reg_View.proc_gblt_pat_category_mst(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";		//first call the getQueryMethod with arguments filename,querykey from prop file
		try 
		{
		daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_pat_cat_type_secondary", RegistrationConfig.PATIENT_CAT_TYPE_SECONDARY,1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_super_hospital_code", RegistrationConfig.SUPER_USER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "p_isvalid_active", Config.IS_VALID_ACTIVE,3);
		daoObj.setProcInValue(nProcIndex, "p_isvalid_inactive", Config.IS_VALID_INACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "p_seatid",  _userVO.getSeatId(),5);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code",  _userVO.getHospitalCode(),6);
		daoObj.setProcInValue(nProcIndex, "p_module_id",  Config.MODULE_ID_REGISTRATION,7);
		daoObj.setProcOutValue(nProcIndex, "err", 1,8);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");

		strErr = daoObj.getString(nProcIndex, "err");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try
		{
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Records For Treatment Category Found. Either There Are No Records In Database Or No Records Against Your Seat Id Exist  ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		try {
			if (strErr.equals("")) 
			{
				//while(rs.next()){
				//	alRecord.add(rs.getString(1));
				//	System.out.println("webRs.getString(1) :" + rs.getString(1));
				alRecord=	HelperMethodsDAO.getAlOfEntryObjects(rs);
				//}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientIdDetailDAO:searchPatientDetailByUniqueIdForSearchTile:HelperMethods :: " + e);
		}	
		
		

		
		
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return alRecord;
	}

	public int updateTreatmentCatNExpiryDate(EpisodeVO _episodeVO, UserVO _userVO)
	{
		int i=0;
		String strErr = "";
		HisDAO daoObj = null;
		int nProcIndex2 = 0;
		String strProcName2="";
		strProcName2 = "{call pkg_reg_dml.dml_update_treatment_cat_exp_date(?,?::character varying,?::character varying,?::character varying,?)}";
		try
		{
			daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			daoObj.setProcInValue(nProcIndex2, "p_exp_date", _episodeVO.getSecCatExpDate(),1);
			daoObj.setProcInValue(nProcIndex2, "p_crno", _episodeVO.getPatCrNo().trim(),2);
			daoObj.setProcInValue(nProcIndex2, "p_episode_code", _episodeVO.getEpisodeCode().trim(),3);
			daoObj.setProcInValue(nProcIndex2, "p_episode_visit_no", _episodeVO.getEpisodeVisitNo().trim(),4);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,5);

			
			daoObj.executeProcedureByPosition(nProcIndex2);
			//daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				{
				strErr = "";
				 i=1;
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		
		return i;
	}
	public int updateTreatmentCatNValidUpTo(EpisodeVO _episodeVO, UserVO _userVO)
	{
		int i=0;
		String strErr = "";
		HisDAO daoObj = null;
		int nProcIndex2 = 0;
		String strProcName2="";
		strProcName2 = "{call pkg_reg_dml.dml_update_treatment_cat_and_valid_upto(?::character varying,?,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?)}";
		try
		{
			daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			daoObj.setProcInValue(nProcIndex2, "p_cat_code", _episodeVO.getPatSecondaryCatCode(),1);
			daoObj.setProcInValue(nProcIndex2, "p_valid_upto", _episodeVO.getValidUpto(),2);
			daoObj.setProcInValue(nProcIndex2, "p_crno", _episodeVO.getPatCrNo().trim(),3);
			daoObj.setProcInValue(nProcIndex2, "p_episode_code", _episodeVO.getEpisodeCode().trim(),4);
			daoObj.setProcInValue(nProcIndex2, "p_episode_visit_no", _episodeVO.getEpisodeVisitNo().trim(),5);
			daoObj.setProcInValue(nProcIndex2, "p_seatid", _userVO.getSeatId(),6);
			daoObj.setProcInValue(nProcIndex2, "p_hospcode",_userVO.getHospitalCode(),7);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,8);

			
			daoObj.executeProcedureByPosition(nProcIndex2);
			//daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				{
				strErr = "";
				 i=1;
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		
		return i;
	}	
	public void updateTreatmentCategory(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		int i=0;
		String strErr = "";
		HisDAO daoObj = null;
		int nProcIndex2 = 0;
		String strProcName2="";
		strProcName2 = "{call pkg_reg_dml.dml_update_daily_pat_treatment_cat(?::character varying,?,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?)}";
		try
		{
			daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			daoObj.setProcInValue(nProcIndex2, "p_cat_code",  _dailyPatientVO.getPatSecondaryCatCode(),1);
			daoObj.setProcInValue(nProcIndex2, "p_valid_upto", _dailyPatientVO.getTreatmentValidUpTo()==null?"1":_dailyPatientVO.getTreatmentValidUpTo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_crno",  _dailyPatientVO.getPatCrNo().trim(),3);
			daoObj.setProcInValue(nProcIndex2, "p_episode_code", _dailyPatientVO.getEpisodeCode().trim(),4);
			daoObj.setProcInValue(nProcIndex2, "p_episode_visit_no", _dailyPatientVO.getEpisodeVisitNo().trim(),5);
			daoObj.setProcInValue(nProcIndex2, "p_seatid", _userVO.getSeatId(),6);
			daoObj.setProcInValue(nProcIndex2, "p_hospcode",_userVO.getHospitalCode(),7);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,8);

			
			daoObj.executeProcedureByPosition(nProcIndex2);
			//daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
		}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		
		
		
	}
	
		public void updateAdmissionDetail(ChangeTreatmentCategoryVO _admittedPatientVO, UserVO _userVO)
		{
		int i=0;
		String strErr = "";
		HisDAO daoObj = null;
		int nProcIndex2 = 0;
		String strProcName2="";
		strProcName2 = "{call pkg_reg_dml.dml_update_hipt_patadmdisc_dtl(?::character varying,?::character varying,?::character varying,?::character varying,?)}";
		try
		{
			daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			
			daoObj.setProcInValue(nProcIndex2, "p_cat_code",  _admittedPatientVO.getPatNewSecondaryCatCode(),1);
			daoObj.setProcInValue(nProcIndex2, "p_crno",  _admittedPatientVO.getPatCrNo().trim(),2);
			daoObj.setProcInValue(nProcIndex2, "p_admission_no", _admittedPatientVO.getAdmissionNo(),3);
			daoObj.setProcInValue(nProcIndex2, "p_sl_no", _admittedPatientVO.getSerialNo().trim(),4);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,5);

			
			daoObj.executeProcedureByPosition(nProcIndex2);
			//daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
		}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		
		
		
	}
public ChangeTreatmentCategoryVO createExtendValidUpto(ChangeTreatmentCategoryVO _treatmentCatChangeVO, UserVO _userVO,String strMode_p){
		
	String strErr = "";
	HisDAO daoObj = null;
	int nProcIndex2 = 0;
	int seq=0;
	String strProcName2="";
	strProcName2 = "{call pkg_reg_dml.dml_hrgt_seccat_change_dtl(?,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?,?,?,?,?, ?,?,?,?,?, ?)}";
	try
	{
		daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
		nProcIndex2 = daoObj.setProcedure(strProcName2);	
		daoObj.setProcInValue(nProcIndex2, "p_modeVal", strMode_p,++seq);
		daoObj.setProcInValue(nProcIndex2, "p_puk", _treatmentCatChangeVO.getPatCrNo().trim(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_episode_code",  _treatmentCatChangeVO.getEpisodeCode().trim(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_s_no", _treatmentCatChangeVO.getPatCrNo().trim(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_hrgnum_puk", _treatmentCatChangeVO.getPatCrNo().trim(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_hrgnum_episode_code",  _treatmentCatChangeVO.getEpisodeCode().trim(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_cat_code",  _treatmentCatChangeVO.getPatPrimaryCatCode().trim(),++seq);
		//daoObj.setProcInValue(nProcIndex2, "p_previous_cat_code",   _treatmentCatChangeVO.getPatPrevSecondaryCatCode()==""?_treatmentCatChangeVO.getPatPrimaryCatCode().trim(): _treatmentCatChangeVO.getPatPrevSecondaryCatCode(),++seq);
		//daoObj.setProcInValue(nProcIndex2, "p_cat_code","",++seq);
		daoObj.setProcInValue(nProcIndex2, "p_previous_cat_code","",++seq);
		daoObj.setProcInValue(nProcIndex2, "p_new_cat_code",   _treatmentCatChangeVO.getPatNewSecondaryCatCode(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_remarks",  _treatmentCatChangeVO.getRemarks(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_ip_add",  _userVO.getIpAddress(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_is_valid", Config.IS_VALID_ACTIVE,++seq);
		daoObj.setProcInValue(nProcIndex2, "p_seat_id",   _userVO.getSeatId(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_hos_code",   _userVO.getHospitalCode(),++seq);
		
		daoObj.setProcInValue(nProcIndex2, "p_letter_ref_no",_treatmentCatChangeVO.getLetterReferenceNo(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_letter_date",_treatmentCatChangeVO.getLetterDate(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_credit_limit",_treatmentCatChangeVO.getCreditLimit(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_applicable_service",_treatmentCatChangeVO.getApplicableServicesCode(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_applicable_services_name",_treatmentCatChangeVO.getApplicableServicesName(),++seq);
		daoObj.setProcInValue(nProcIndex2, "p_selected_category_name",_treatmentCatChangeVO.getSelectedCategoryName(),++seq);
		
		if(strMode_p.equals("1"))
		{
		daoObj.setProcInValue(nProcIndex2, "p_valid_upto",   "",++seq);
		}
		else if(strMode_p.equals("2"))
		{
		daoObj.setProcInValue(nProcIndex2, "p_valid_upto",   _treatmentCatChangeVO.getExpiryDate(),++seq);
		}	
		else
		{
			daoObj.setProcInValue(nProcIndex2, "p_valid_upto", "",++seq);
		}
		daoObj.setProcInValue(nProcIndex2, "p_admission_no", _treatmentCatChangeVO.getAdmissionNo()==null?"":_treatmentCatChangeVO.getAdmissionNo()  ,++seq);
		daoObj.setProcInValue(nProcIndex2, "p_ipd_opd", _treatmentCatChangeVO.getIsIpdFlag() ,++seq);
		
		daoObj.setProcOutValue(nProcIndex2, "err", 1,++seq);

		
		daoObj.executeProcedureByPosition(nProcIndex2);
		//daoObj.execute(nProcIndex2,1);	
		//strErr = daoObj.getString(nProcIndex2, "err");
	}	
	catch (Exception e) 
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.executeProcedureByPosition()::" + e);
	}
       
        return _treatmentCatChangeVO;
	}
public int updateRevokeTreatmentCat(EpisodeVO _episodeVO, UserVO _userVO)
{
	int i=0;
	String strErr = "";
	HisDAO daoObj = null;
	int nProcIndex2 = 0;
	String strProcName2="";
	strProcName2 = "{call pkg_reg_dml.dml_update_revoke_treatment_cat(?,?::character varying,?::character varying,?::character varying,?::character varying,?)}";
	try
	{
		daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
		nProcIndex2 = daoObj.setProcedure(strProcName2);
		daoObj.setProcInValue(nProcIndex2, "p_exp_date", _episodeVO.getSecCatExpDate(),1);
		daoObj.setProcInValue(nProcIndex2, "p_cat_code", _episodeVO.getPatSecondaryCatCode(),2);
		daoObj.setProcInValue(nProcIndex2, "p_crno", _episodeVO.getPatCrNo().trim(),3);
		daoObj.setProcInValue(nProcIndex2, "p_episode_code", _episodeVO.getEpisodeCode().trim(),4);
		daoObj.setProcInValue(nProcIndex2, "p_episode_visit_no", _episodeVO.getEpisodeVisitNo().trim(),5);
		daoObj.setProcOutValue(nProcIndex2, "err", 1,6);

		
		daoObj.executeProcedureByPosition(nProcIndex2);
		//daoObj.execute(nProcIndex2,1);	
		//strErr = daoObj.getString(nProcIndex2, "err");
		if (strErr == null)
			{
			strErr = "";
			 i=1;
			}
	} 
	catch (Exception e)
	{
		e.printStackTrace();
	}	
	
	return i;
}
//End:Sheeldarshi
public EpisodeVO getPatientAdmittedEpisodes(String crNo,UserVO objUserVO_p,String strMode)
{

	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl(?,?,?,?,?, ?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	EpisodeVO _episodeVO;
	ValueObject[] vo ={};
	try
	{
				
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_modeval", strMode,1);

		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crNo,3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),4);
		daoObj.setProcInValue(nProcIndex, "p_dept", "",5);
		daoObj.setProcInValue(nProcIndex, "p_deptunitcode", "",6);
		daoObj.setProcInValue(nProcIndex, "p_visittype", "",7);
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,8);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
		
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.executeProcedureByPosition()::" + e);
	}
	try
	{			
		if (!rs.next())
		{
			throw new HisRecordNotFoundException("Patient not admitted");

		}
		rs.beforeFirst();
		vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
		//_episodeVO = new EpisodeVO[vo.length];
		//for (int i = 0; i < vo.length; i++)
		//{
			_episodeVO = (EpisodeVO) vo[0];

//		}
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

public List getApplicableServices(UserVO _userVO)
{

	ResultSet rs = null;
	String query = "";
	List alRecord = new ArrayList();
	HisDAO daoObj = null;
	Map populateMAP = new HashMap();
	String strProcName = "{call Pkg_Reg_View.proc_ghmis_service_type_mst(?,?,?,?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";		//first call the getQueryMethod with arguments filename,querykey from prop file
	try 
	{
	daoObj = new HisDAO("Registration","ChangeTreatmentCategoryDAO");
	nProcIndex = daoObj.setProcedure(strProcName);
	daoObj.setProcInValue(nProcIndex, "p_pat_cat_type_secondary", RegistrationConfig.PATIENT_CAT_TYPE_SECONDARY,1);
	daoObj.setProcInValue(nProcIndex, "p_gnum_super_hospital_code", RegistrationConfig.SUPER_USER_HOSPITAL_CODE,2);
	daoObj.setProcInValue(nProcIndex, "p_isvalid_active", Config.IS_VALID_ACTIVE,3);
	daoObj.setProcInValue(nProcIndex, "p_isvalid_inactive", Config.IS_VALID_INACTIVE,4);
	daoObj.setProcInValue(nProcIndex, "p_seatid",  _userVO.getSeatId(),5);
	daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code",  _userVO.getHospitalCode(),6);
	daoObj.setProcInValue(nProcIndex, "p_module_id",  Config.MODULE_ID_REGISTRATION,7);
	daoObj.setProcOutValue(nProcIndex, "err", 1,8);
	daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
	daoObj.executeProcedureByPosition(nProcIndex);

	strErr = daoObj.getString(nProcIndex, "err");
	rs = daoObj.getWebRowSet(nProcIndex, "resultset");

	strErr = daoObj.getString(nProcIndex, "err");
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try
	{
		if (!rs.next()) throw new HisRecordNotFoundException(
				"No Records For Treatment Category Found. Either There Are No Records In Database Or No Records Against Your Seat Id Exist  ");
	}
	catch (Exception e)
	{

		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	try {
		if (strErr.equals("")) 
		{
			//while(rs.next()){
			//	alRecord.add(rs.getString(1));
			//	System.out.println("webRs.getString(1) :" + rs.getString(1));
			alRecord=	HelperMethodsDAO.getAlOfEntryObjects(rs);
			//}
		} 
		else 
		{
			throw new Exception(strErr);
		}
		
		
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("PatientIdDetailDAO:searchPatientDetailByUniqueIdForSearchTile:HelperMethods :: " + e);
	}	
	
	

	
	
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return alRecord;
}
	
}
