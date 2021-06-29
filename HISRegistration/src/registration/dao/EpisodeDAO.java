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

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import vo.registration.AddressVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;

/**
 * EpisodeDAO is a class which describes all the methods required for database interaction
 * for HRGT_EPISODE_DTL table, for example, insert, update, select and delete.
 * EpisodeDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class EpisodeDAO extends RegistrationDAO
{

	/**
	 * Creates a new EpisodeDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public EpisodeDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	public String isPatientMLC(PatientVO _patientVO, UserVO _userVO)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_MLC_DTL(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _patientVO.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_episode_type_code", RegistrationConfig.EPISODE_TYPE_CODE_EMG,4);
			daoObj.setProcInValue(nProcIndex, "p_episode_code", "",5);
			daoObj.setProcInValue(nProcIndex, "p_episode_is_open", RegistrationConfig.EPISODE_ISOPEN_TRUE,6);
			daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid", Config.IS_VALID_ACTIVE,7);
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e
					+ strErr);
		}

    	String mlcNumber = "";
		try
		{
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
	
	public void saveEpisodeDtl(HisDAO daoObj,EpisodeVO objEpisodeVO_p, UserVO objUserVO_p,String strMode_p) 
	{

		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			/**************************************************/   /****************** 1-5 ********************/	/****************** 5-10 ********************/	/************* 10-15 ***************/	 /**************** 15-20 ******************/	/***************** 20-25 *******************/	/****************** 25-30 ******************/	/***** 30-35 ****/  /**35-40*/  /**40-45*/  /**45-47*/
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_episode_dtl(?,?::numeric,?::numeric,?::numeric,?::numeric,   ?::numeric,?::numeric,?::numeric,?::numeric,?,   ?::numeric,?,?::numeric,?::numeric,?,   ?::numeric,?::numeric,?::numeric,?::numeric,?, ?::numeric,?,?::numeric,?::numeric,?::numeric, 	?::numeric,?::numeric,?::numeric,?,?::numeric, 	?,?::numeric,?,?,?,	?,?,?,?,?,	?,?,?,?,?,		?,?)}";
 //strProcName1="{call pkg_reg_dml.dml_hrgt_episode_dtl_odisha_dev(?,?::numeric,?::numeric,?::numeric,?::numeric,   ?::numeric,?::numeric,?::numeric,?::numeric,?,   ?::numeric,?,?::numeric,?::numeric,?,   ?::numeric,?::numeric,?::numeric,?::numeric,?, ?::numeric,?,?::numeric,?::numeric,?::numeric, 	?::numeric,?::numeric,?::numeric,?,?::numeric, 	?,?::numeric,?,?,?,	?,?,?,?,?,  ?,?,?,?,?)}";//Last two added by Vasu on 03.May.18


			nProcIndex1 = daoObj.setProcedure(strProcName1);			
			HelperMethods.setNullToEmpty(objEpisodeVO_p);
			
			System.out.println("----------------------------------------------------");
			System.out.println("EpisodeDAO :: saveEpisode()");
			
			daoObj.setProcInValue(nProcIndex1, "p_modeval", strMode_p,1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",objEpisodeVO_p.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", objEpisodeVO_p.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", objEpisodeVO_p.getEpisodeVisitNo(),4);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_type", objEpisodeVO_p.getEpisodeVisitType(),5);
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", objEpisodeVO_p.getEpisodeTypeCode(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_admission_no", objEpisodeVO_p.getAdmissionNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", objEpisodeVO_p.getDepartmentCode(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", objEpisodeVO_p.getDepartmentUnitCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_visit_reason", objEpisodeVO_p.getPatVisitReason(),10);
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", objEpisodeVO_p.getQueNo(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_primarycat_validupto","",12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_episode_open", objEpisodeVO_p.getEpisodeIsOpen(),13); 
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),14); 
			daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date", "",15); 
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_confirmed", objEpisodeVO_p.getIsConfirmed(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ref_episode_code", objEpisodeVO_p.getEpisodeReferCode(),17);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", objEpisodeVO_p.getIsValid(),18);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", objEpisodeVO_p.getRoomCode(),19); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_start_date", objEpisodeVO_p.getEpisodeStartDate(),20);
			
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_ward_code",objEpisodeVO_p.getWardCode(),21); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_lastvisit_date",objEpisodeVO_p.getLastVisitDate(),22); 
			daoObj.setProcInValue(nProcIndex1, "p_hblnum_tariff_id",objEpisodeVO_p.getTariffId(),23); 
			daoObj.setProcInValue(nProcIndex1, "p_hblnum_bill_no", objEpisodeVO_p.getBillNo(),24); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected",objEpisodeVO_p.getPatAmountCollected(),25); 
			
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code",objEpisodeVO_p.getPatSecondaryCatCode() ,26); 
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", objEpisodeVO_p.getPatPrimaryCatCode(),27); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", objEpisodeVO_p.getIsReferred(),28); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_exp_date",objEpisodeVO_p.getEpisodeExpiryDate(),29); 
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),30); 
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",(objUserVO_p.getIpAddress()==null||objUserVO_p.getIpAddress().equals("")?"127.0.0.1":objUserVO_p.getIpAddress()),31); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_iscardprint",objEpisodeVO_p.getIsCardPrint(),32); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_treatmentcat_validupto", objEpisodeVO_p.getTreatmentValidUpTo(),33); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isforceful", objEpisodeVO_p.getIsForceful().equals("")?"0":objEpisodeVO_p.getIsForceful(),34); 
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_reg_flag", objEpisodeVO_p.getStrRegFlag(),35);
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mlc_flag", objEpisodeVO_p.getMlcFlag(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isapt_flag", objEpisodeVO_p.getIsAppointment(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_apt_no", objEpisodeVO_p.getPatAptNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_apt_slot", objEpisodeVO_p.getPatAptSlot(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_apt_qno", objEpisodeVO_p.getPatAptQueueNO(),40);

			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_credit_refno", objEpisodeVO_p.getCreditLetterRefNo(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_credit_letter_date", objEpisodeVO_p.getCreditLetterDate(),42);
			daoObj.setProcInValue(nProcIndex1, "p_ambulatory_flg", objEpisodeVO_p.getIsAmbulatory(),43);
			daoObj.setProcInValue(nProcIndex1, "p_trauma_flg", objEpisodeVO_p.getIsTrauma(),44);
			daoObj.setProcInValue(nProcIndex1, "p_sctstr_ids_visit_reason", objEpisodeVO_p.getSnomdCIdVisitReason(),45);
			
			daoObj.setProcInValue(nProcIndex1, "p_sctstr_pterms_visit_reason", objEpisodeVO_p.getSnomdPTVisitReason(),46);
			
			daoObj.setProcOutValue(nProcIndex1, "err", 1,47);

			
			//daoObj.executeProcedureByPosition(nProcIndex1);
			daoObj.execute(nProcIndex1,1);	
			
			
			///////////////
			System.out.println("p_modeval :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+objEpisodeVO_p.getPatCrNo());
			System.out.println("p_hrgnum_episode_code :"+ objEpisodeVO_p.getEpisodeCode());
			System.out.println("p_hrgnum_visit_no :"+ objEpisodeVO_p.getEpisodeVisitNo());
			System.out.println("p_hrgnum_visit_type :"+ objEpisodeVO_p.getEpisodeVisitType());
			
			System.out.println("p_hrgnum_episode_type_code :"+ objEpisodeVO_p.getEpisodeTypeCode());
			System.out.println("p_hrgnum_admission_no :"+ objEpisodeVO_p.getAdmissionNo());
			System.out.println("p_gnum_dept_code :"+ objEpisodeVO_p.getDepartmentCode());
			System.out.println("p_hgnum_deptunitcode :"+ objEpisodeVO_p.getDepartmentUnitCode());
			System.out.println("p_hrgstr_visit_reason :"+ objEpisodeVO_p.getPatVisitReason());
			
			System.out.println("p_hrgnum_que_no :"+ objEpisodeVO_p.getQueNo());
			System.out.println("p_hrgdt_primarycat_validupto :"+" ");
			System.out.println("p_hrgnum_is_episode_open :"+ objEpisodeVO_p.getEpisodeIsOpen()); 
			System.out.println("p_gnum_seat_id :"+ objUserVO_p.getSeatId()); 
			System.out.println("p_gdt_entry_date :"+ " "); 
			
			System.out.println("p_hrgnum_is_confirmed :"+ objEpisodeVO_p.getIsConfirmed());
			System.out.println("p_hrgnum_ref_episode_code :"+ objEpisodeVO_p.getEpisodeReferCode());
			System.out.println("p_gnum_isvalid :"+ objEpisodeVO_p.getIsValid());
			System.out.println("p_hgnum_room_code :"+ objEpisodeVO_p.getRoomCode()); 
			System.out.println("p_hrgdt_episode_start_date :"+ objEpisodeVO_p.getEpisodeStartDate());
			
			System.out.println("p_hgnum_ward_code :"+objEpisodeVO_p.getWardCode()); 
			System.out.println("p_hrgdt_episode_lastvisit_date :"+objEpisodeVO_p.getLastVisitDate()); 
			System.out.println("p_hblnum_tariff_id :"+objEpisodeVO_p.getTariffId()); 
			System.out.println("p_hblnum_bill_no :"+ objEpisodeVO_p.getBillNo()); 
			System.out.println("p_hrgnum_amt_collected :"+objEpisodeVO_p.getPatAmountCollected()); 
			
			System.out.println("p_hgnum_treatment_cat_code :"+objEpisodeVO_p.getPatSecondaryCatCode() ); 
			System.out.println("p_hgnum_patient_cat_code :"+ objEpisodeVO_p.getPatPrimaryCatCode()); 
			System.out.println("p_hrgnum_is_ref :"+ objEpisodeVO_p.getIsReferred()); 
			System.out.println("p_hrgdt_exp_date :"+objEpisodeVO_p.getEpisodeExpiryDate()); 
			System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode()); 
			
			System.out.println("p_hrgstr_ip_add :"+objUserVO_p.getIpAddress()); 
			System.out.println("p_hrgnum_iscardprint :"+objEpisodeVO_p.getIsCardPrint()); 
			System.out.println("p_hrgdt_treatmentcat_validupto :"+ objEpisodeVO_p.getTreatmentValidUpTo()); 
			System.out.println("p_hrgnum_isforceful :"+ objEpisodeVO_p.getIsForceful()); 
			System.out.println("p_hrgnum_reg_flag :"+ objEpisodeVO_p.getStrRegFlag());
			System.out.println("p_hrgnum_mlc_flag :"+ objEpisodeVO_p.getMlcFlag());
			
			System.out.println("p_hrgstr_credit_refno :"+ objEpisodeVO_p.getCreditLetterRefNo());
			System.out.println("p_hrgnum_credit_letter_date :"+ objEpisodeVO_p.getCreditLetterDate());

			System.out.println("----------------------------------------------------");
			//strErr = daoObj.getString(nProcIndex1, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}

	/*used by duplicate card generation and barcode generation process*/
	public EpisodeVO[] DuplicateRetrieveByCrNo(String crNo,UserVO objUserVO_p,String strMode)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] _episodeVO;
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
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No open episode found");

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
	
	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String crNo,UserVO objUserVO_p,String strMode)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl(?,?,?,?,?, ?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] _episodeVO;
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
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	/**
	 * Retrieves all the episodes of a patient which are still open on the basis of CR No.
	 * Also checks that the episodes should be valid and active.
	 * @param	objUserVO_p		Provides User details.
	 * @return	Array of EpisodeVO containing all the episode details of a patient.
	 * retrieveByCrNo changed to this
	 */
	public EpisodeVO[] getEpisodeDtlByCrNoByDeptByUnit(String strCrNo_p,String strPatCatCode_p,String strDeptCode_p, 
										String strDeptUnitCode_p, UserVO objUserVO_p, String strMode_p,String visitType_p)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl_duplicate(?,?,?,?,?, ?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] objEpisodeVO;
		ValueObject[] vo ={};
		try
		{
			
			daoObj = new HisDAO("Registration","EpisodeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			System.out.println("---------------------------------------------");
			System.out.println("EpisodeDAO :: getEpisodeDtlByCrNoByDeptByUnit");
			System.out.println("strMode_p :"+strMode_p);
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode());
			System.out.println("strCrNo_p :"+strCrNo_p);
			System.out.println("strDeptCode_p :"+strDeptCode_p);
			System.out.println("p_visittype :"+visitType_p);
			
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_crno", strCrNo_p==null?"":strCrNo_p,3);
			daoObj.setProcInValue(nProcIndex, "p_dept", strDeptCode_p==null?"":strDeptCode_p,4);
			daoObj.setProcInValue(nProcIndex, "p_deptunitcode", strDeptUnitCode_p==null?"":strDeptUnitCode_p,5);
			daoObj.setProcInValue(nProcIndex, "p_seatid", objUserVO_p.getUserSeatId(),6);
			daoObj.setProcInValue(nProcIndex, "p_visittype", visitType_p,7);
			daoObj.setProcInValue(nProcIndex, "p_pat_cat", strPatCatCode_p,8);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeProcedureByPosition::" + e);
		}
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No open episode found");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			objEpisodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				objEpisodeVO[i] = (EpisodeVO) vo[i];
	
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO: getEpisodeDtlByCrNo() ::Episode Details:: " + e);
		}
		return objEpisodeVO;
	}
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the patients referred from other hospital
	 * date : 7th oct 2016 */
	public EpisodeVO[] getExtEpisodeDtlByCrNoByDeptByUnit(PatientVisitSUP objPatVisitSUP_p,UserVO objUserVO_p, String strMode_p)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl_duplicate(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] objEpisodeVO;
		ValueObject[] vo ={};
		try
		{

			daoObj = new HisDAO("Registration","EpisodeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			System.out.println("---------------------------------------------");
			System.out.println("EpisodeDAO :: getEpisodeDtlByCrNoByDeptByUnit");
			System.out.println("strMode_p :"+strMode_p);
			System.out.println("p_gnum_hospital_code :"+objUserVO_p.getHospitalCode());
			System.out.println("p_hrgnum_puk :"+objPatVisitSUP_p.getPatCrNo());
			System.out.println("strDeptCode_p :"+objPatVisitSUP_p.getFromDepartmentCode());
			System.out.println("strDeptUnitCode_p :"+objPatVisitSUP_p.getFromDepartmentUnitCode());
			

			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code",objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", objPatVisitSUP_p.getPatCrNo()==null?"":objPatVisitSUP_p.getPatCrNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeProcedureByPosition::" + e);
		}
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No open episode found");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			objEpisodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				objEpisodeVO[i] = (EpisodeVO) vo[i];

			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO: getEpisodeDtlByCrNo() ::Episode Details:: " + e);
		}
		return objEpisodeVO;
	}
	
	//End
	public EpisodeVO[] getEpisodeDtlForMLC(UserVO objUserVO_p, String strMode_p, String strCrNo_p, String strVisitDate_p)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl_for_mlc(?,?,?,?,?, ?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] objEpisodeVO;
		ValueObject[] vo ={};
		try
		{
			strVisitDate_p=strVisitDate_p==null?"":strVisitDate_p;
			
			daoObj = new HisDAO("Registration","EpisodeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			System.out.println("---------------------------------------------");
			System.out.println("EpisodeDAO :: getEpisodeDtlForMLC");
			System.out.println("strMode_p :"+strMode_p);
			System.out.println("p_hrgnum_puk :"+strCrNo_p);
			System.out.println("p_visit_date :"+strVisitDate_p);
			System.out.println("---------------------------------------------");
			
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode_p,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", strCrNo_p==null?"":strCrNo_p,3);
			daoObj.setProcInValue(nProcIndex, "p_seatid", objUserVO_p.getUserSeatId(),4);
			daoObj.setProcInValue(nProcIndex, "p_visit_date", strVisitDate_p,5);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeProcedureByPosition::" + e);
		}
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No open episode found For MLC");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			objEpisodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				objEpisodeVO[i] = (EpisodeVO) vo[i];
	
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO: getEpisodeDtlForMLC() ::Episode Details:: " + e);
		}
		return objEpisodeVO;
	}
	

	public EpisodeVO[] MLCNonMLCRetrieveByCrNo(String crNo,UserVO objUserVO_p,String strMode)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_episode_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		try
		{
			
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crNo,3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No open episode found");

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
	
	public String generateEpisodeCode(String strPatCrNo_p,UserVO objUserVO_p,String strDeptUnitCode_p)
	{
		String episodeNo="";
		int funcIndex=0;
		HisDAO objHisDAO_p=null;
		try 
		{
			objHisDAO_p = new HisDAO("Registration","DailyPatientDAO");
			funcIndex = objHisDAO_p.setFunction("{? = call pkg_reg_util.fun_gen_episodecode(?::numeric,?::numeric,?::numeric)}");
			objHisDAO_p.setFuncInValue(funcIndex, 2, objUserVO_p.getHospitalCode());
			objHisDAO_p.setFuncInValue(funcIndex, 3, strPatCrNo_p);
			objHisDAO_p.setFuncInValue(funcIndex, 4, strDeptUnitCode_p);
			
			objHisDAO_p.setFuncOutValue(funcIndex,3);
			
			objHisDAO_p.executeFuncForNumeric(funcIndex);
			episodeNo = objHisDAO_p.getFuncNumeric(funcIndex)+"";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("EpisodeDAO.generateEpisodeCode()" + e);
		}
		finally 
		{
			if (objHisDAO_p != null) 
			{
				objHisDAO_p.free();
				objHisDAO_p = null;
			}
		}
		return episodeNo;
	}
	//Added by Vasu dated on 23.April.18 to get previous saved treatment categories for a particular episode
	public EpisodeVO[] getTreatmentCategoriesForSelectedEpisodes(String crNo, String episodeCode,UserVO objUserVO_p,String strMode)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.proc_hrgt_seccat_change_dtl(?,?,?,?,?, ?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		try
		{
			
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crNo,3);
			daoObj.setProcInValue(nProcIndex,  "p_episodeCode", episodeCode,4);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),5);
			/*daoObj.setProcInValue(nProcIndex, "p_dept", "",5);
			daoObj.setProcInValue(nProcIndex, "p_deptunitcode", "",6);
			daoObj.setProcInValue(nProcIndex, "p_visittype", "",7);*/
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
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
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");

			}*/
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
}
