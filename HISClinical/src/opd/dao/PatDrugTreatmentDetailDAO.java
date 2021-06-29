package opd.dao;

/**
 * @author  CDAC
 */

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import opd.OpdConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugDoseIndicationMstVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import hisglobal.vo.DrugSaftyAlertMstVO;

public class PatDrugTreatmentDetailDAO extends DataAccessObject implements PatDrugTreatmentDetailDAOi
{
	public PatDrugTreatmentDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Saving Drug Trteatment Detail Data
	 * 
	 * @param _patDrugDtlVO Detail
	 * @param _userVO User Detail
	 */
	public void savePatDrugTreatmentDetail(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.NEW.OPD.HRGT_EPISODE_DRUG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			if (_patDrugDtlVO.getAdmissionNo() == null) _patDrugDtlVO.setAdmissionNo("");
			populateMAP.put(sq.next(), _patDrugDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugName());
			if(_patDrugDtlVO.getDrugBrandId().indexOf("#") < 0)	//By nilesh on 28.11.17	 for seprating drug id and drug band id	
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugBrandId());
			else
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugBrandId().split("#")[1]);
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseName());
			populateMAP.put(sq.next(), _patDrugDtlVO.getFrequencyId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDays());

			populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTime());
			populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeHrs());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeMin());

			populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDays());
			populateMAP.put(sq.next(), _patDrugDtlVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			// populateMAP.put(sq.next(), OpdConfig.TREATMENT_DETAIL_RX_CONTINUE_NEW);
			populateMAP.put(sq.next(), _patDrugDtlVO.getRxContinue());
			populateMAP.put(sq.next(), _userVO.getSeatId());

			populateMAP.put(sq.next(), _patDrugDtlVO.getEntryDate());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugAdminId()); 
			//populateMAP.put(sq.next(), _patDrugDtlVO.getIsEmptyStomach());  // is empty stomach field is replaced by drug administration (after meal, before meal, with meal) 
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugRouteId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseQty());
			populateMAP.put(sq.next(), _patDrugDtlVO.getIssueQty());
			populateMAP.put(sq.next(), _patDrugDtlVO.getRequiredQty());
			populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugTypeID());
			

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public void saveRevokePatDrugTreatmentDetail(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.REVOKED_TREATMENT.HRGT_EPISODE_DRUG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			// sereal No
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());

			if (_patDrugDtlVO.getAdmissionNo() == null) _patDrugDtlVO.setAdmissionNo("");
			populateMAP.put(sq.next(), _patDrugDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugName());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugBrandId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseName());
			populateMAP.put(sq.next(), _patDrugDtlVO.getFrequencyId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDays());

			// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTime());
			populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeHrs());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeMin());

			populateMAP.put(sq.next(), _patDrugDtlVO.getEndDate());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getDays());
			populateMAP.put(sq.next(), _patDrugDtlVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			// populateMAP.put(sq.next(), OpdConfig.TREATMENT_DETAIL_RX_CONTINUE_NEW);
			populateMAP.put(sq.next(), _patDrugDtlVO.getRxContinue());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEntryDate());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getIsEmptyStomach());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugRouteId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseQty());
			populateMAP.put(sq.next(), _patDrugDtlVO.getIssueQty());
			populateMAP.put(sq.next(), _patDrugDtlVO.getRequiredQty());
			populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
			populateMAP.put(sq.next(), _patDrugDtlVO.getRevokeRemarks());   //Added  By Pawan Kumar B N on 19-Feb-2013
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugTypeID());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public String getDrugAllergyAllerts(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "PATIENT_DRUG_ALLERGY_STATUS";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), patDrugDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), patDrugDtlVO.getDrugId());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Drug Allergy Is Here");
			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}

	}

	public String getConsentStatus(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_DRUG_CONSENT_STATUS.HGBT_CONSENT_MAPPING";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), patDrugDtlVO.getDrugId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), OpdConfig.SERVICE_TYPE_FOR_CONSENT);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				return "0";
				// throw new HisRecordNotFoundException("No Drug Allergy Is Here");

			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}

	}

	public void generatConsent(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		String errorMsg = null;
		
		/////////////
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HGNUM_TEMPLATE_ID.HGBT_CONSENT_MAPPING";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), OpdConfig.SERVICE_TYPE_FOR_CONSENT); // service Type
		populateMAP.put(sq.next(), patDrugDtlVO.getDrugId()); 			// service Id
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Template Exist");

			}else{
				rs.beforeFirst();
				String templateId="";
				while(rs.next()){
					templateId=rs.getString(1);
					
					Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GENERATE_CONSENT);
					strProc.addInParameter(1, Types.NUMERIC, _userVO.getHospitalCode());
					strProc.addInParameter(2, Types.NUMERIC, _userVO.getSeatId());
					strProc.addInParameter(3, Types.VARCHAR, _userVO.getUserEmpID());
					strProc.addInParameter(4, Types.NUMERIC, OpdConfig.SERVICE_TYPE_FOR_CONSENT);// service Type
					strProc.addInParameter(5, Types.VARCHAR, patDrugDtlVO.getDrugId());// service Id
					strProc.addInParameter(6, Types.VARCHAR, patDrugDtlVO.getEpisodeVisitNo());
					strProc.addInParameter(7, Types.NUMERIC, patDrugDtlVO.getPatCrNo());
					strProc.addInParameter(8, Types.NUMERIC, patDrugDtlVO.getEpisodeCode());
					strProc.addInParameter(9, Types.NUMERIC, patDrugDtlVO.getEpisodeVisitNo());
					strProc.addInParameter(10, Types.NUMERIC, templateId);

					strProc.execute(super.getTransactionContext().getConnection());
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

		/*try
		{
			Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GENERATE_CONSENT);
			strProc.addInParameter(1, Types.NUMERIC, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.NUMERIC, _userVO.getSeatId());
			strProc.addInParameter(3, Types.VARCHAR, _userVO.getUserEmpID());
			strProc.addInParameter(4, Types.NUMERIC, OpdConfig.SERVICE_TYPE_FOR_CONSENT);// service Type
			strProc.addInParameter(5, Types.VARCHAR, patDrugDtlVO.getDrugId());// service Id
			strProc.addInParameter(6, Types.VARCHAR, patDrugDtlVO.getEpisodeVisitNo());
			strProc.addInParameter(7, Types.NUMERIC, patDrugDtlVO.getPatCrNo());
			strProc.addInParameter(8, Types.NUMERIC, patDrugDtlVO.getEpisodeCode());
			strProc.addInParameter(9, Types.NUMERIC, patDrugDtlVO.getEpisodeVisitNo());

			strProc.execute(super.getTransactionContext().getConnection());

		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}*/
	}

	public String getPrevDrugStatus(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PREV_DRUG_STATUS.HRGT_EPISODE_DRUG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);//Commented By Pragya as Query Change
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);//Commented By Pragya as Query Change
		populateMAP.put(sq.next(), _userVO.getHospitalCode());//Moved Up By Pragya as Query Change
		populateMAP.put(sq.next(), patDrugDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), OpdConfig.IS_EPISODE_OPEN);
		populateMAP.put(sq.next(), patDrugDtlVO.getDepartmentUnitCode());
		populateMAP.put(sq.next(), patDrugDtlVO.getDrugId());
		populateMAP.put(sq.next(), OpdConfig.REVOKE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				return "prevNoStatus";
			}
			return rs.getString(2);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
	}

	public void updateRevoke(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.OPD.HRGT_EPISODE_DRUG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _patDrugDtlVO.getRxContinue());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getEndDate());
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public void updateLastTodayVisitRecord(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.TODAY_VISIT_RECORD.HRGT_EPISODE_DRUG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			// populateMAP.put(sq.next(), _patDrugDtlVO.getEndDate());
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _patDrugDtlVO.getEntryDate());

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public PatDrugTreatmentDetailVO[] getPatientTreatmentDetail(String _crNo, PatientDetailVO voDP, String profileGenerationMode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DRUG_DTL.PAT_PROFILE_BY_CRNO";
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				System.out.println("All VISIT"+"  QUERY= "+query);
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				query= query+"AND HRGNUM_VISIT_NO=?";
				System.out.println("Current VISIT"+"  QUERY= "+query);
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{

			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				//Paramters Added BY Ranjit for Union (Fetching Other treatment Also) on 24042019 
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
			/*	populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			uncommented on date 10.01.2017*/	
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
				
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				//Paramters Added BY Ranjit for Union (Fetching Other treatment Also) on 24042019
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				
				populateMAP.put(sq.next(), voDP.getEpisodeVisitNo());
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatDrugTreatmentDetailDAO.populateMAP::" + e);
		}

		PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = null;
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Treatment Drug Record Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(PatDrugTreatmentDetailVO.class, rs);
			patDrugTreatmentDtlVO = new PatDrugTreatmentDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				patDrugTreatmentDtlVO[i] = (PatDrugTreatmentDetailVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return patDrugTreatmentDtlVO;
	}

	public PatDrugTreatmentDetailVO[] getIpdPatientTreatmentDetail(String _crNo, String _admissionNo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_IPD.HRGT_EPISODE_DRUG_DTL.PAT_PROFILE_BY_CRNO";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _crNo);
			populateMAP.put(sq.next(), _admissionNo);
			//populateMAP.put(sq.next(), _crNo);
			//populateMAP.put(sq.next(), _admissionNo);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			//Added by Vasu on 23.April.2019 to get External Treatment Details at Patient Profile
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _crNo);
			populateMAP.put(sq.next(), _admissionNo);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatDrugTreatmentDetailDAO.populateMAP::" + e);
		}

		PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = null;
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Treatment Drug Record Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(PatDrugTreatmentDetailVO.class, rs);
			patDrugTreatmentDtlVO = new PatDrugTreatmentDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				patDrugTreatmentDtlVO[i] = (PatDrugTreatmentDetailVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return patDrugTreatmentDtlVO;
	}

	/**
	 * get the treatment detail of the patient for EMR
	 * 
	 * @param _crNo
	 * @param departmentUnitArray
	 * @param accessType
	 * @param _userVO
	 * @return
	 */
	public PatDrugTreatmentDetailVO[] getTreatmentAdviceDetailsEMR(String _crNo, String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DRUG_DTL.ALL_DETAIL_EMR";
		String orderBy = " ORDER BY startdate desc,drugname	";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if (accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO))
			{
				String inArg = "";
				for (int i = 0; i < departmentUnitArray.length; i++)
				{
					inArg += "," + departmentUnitArray[i];
				}
				inArg = inArg.replaceFirst(",", "");
				query += "  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = b.hrgnum_episode_code "
						+ "and HRGNUM_PUK=b.HRGNUM_PUK and HRGNUM_VISIT_NO=b.HRGNUM_VISIT_NO and gnum_isvalid=b.gnum_isvalid "
						+ " AND gnum_hospital_code = b.gnum_hospital_code) in (" + inArg + ") ";
			}
			
			query +="union select hstnum_item_id AS drugid, hststr_item_name AS drugname, hgnum_dose_id AS doseid, hgstr_dose_name AS dosename, hgnum_frequency_id AS frequencyid,"
		+ "  (SELECT hgstr_frequency_name FROM hgbt_drug_frequency_mst  WHERE hgnum_frequency_id = a.hgnum_frequency_id  AND gnum_isvalid = 1  AND gnum_hospital_code = 100) AS frequencyname,"
        + "  TO_CHAR (hrgdt_start_date, 'dd-Mon-yyyy') AS startdate,  TO_CHAR (hrgdt_end_date, 'dd-Mon-yyyy') AS enddate,  hrgnum_admission_no AS admissionno,  hrgnum_visit_no AS episodevisitno,"
		+ "  hgnum_days AS days,hrgstr_remarks AS remarks, psrstr_emp_no AS empno, hbnum_rx_continue AS rxcontinue, hgnum_isempty_stomach AS isemptystomach, hgnum_drug_route_id AS drugrouteid,"
		+ " hrgnum_episode_code AS episodecode,hrgnum_visit_no AS episodevisitno, (SELECT hgnum_deptunitcode  FROM hrgt_episode_dtl  WHERE hrgnum_puk = b.hrgnum_puk"
        + " AND hrgnum_episode_code = b.hrgnum_episode_code  AND hrgnum_visit_no = b.hrgnum_visit_no AND gnum_isvalid = b.gnum_isvalid AND gnum_hospital_code = b.gnum_hospital_code) AS departmentunitcode,"
		+ " b.gnum_hospital_code AS hospitalcode FROM hpmrt_profile_drug_advice_dtl a, hpmrt_pat_profile_dtl b WHERE a.hpmrnum_profile_id = b.hpmrnum_profile_id "
		+ " AND a.gnum_isvalid = b.gnum_isvalid AND a.gnum_hospital_code = b.gnum_hospital_code AND hrgnum_puk = ?   AND a.gnum_hospital_code = ?  AND b.gnum_isvalid = ?  AND a.hrgnum_entry_mode = ?";
		    
		query += orderBy;
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{

			populateMAP.put(sq.next(), _crNo);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _crNo);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatDrugTreatmentDetailDAO.populateMAP::" + e);
		}

		PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = null;
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatDrugTreatmentDetailVO.class, rs);
				patDrugTreatmentDtlVO = new PatDrugTreatmentDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					patDrugTreatmentDtlVO[i] = (PatDrugTreatmentDetailVO) vo[i];
				}
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return patDrugTreatmentDtlVO;
	}
	
	
	// Added By Shweta for fetching External Treatment Detail on 15-May-2019
		public PatDrugTreatmentDetailVO[] getPateintExtTreatmentDetails(String _crNo, String[] departmentUnitArray, String accessType, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT_OTHER_TREATMENT_DETAIL";
			String orderBy = " ORDER BY startdate desc";

			try
			{
				    
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				
			query += orderBy;
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			try
			{
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("PatDrugTreatmentDetailDAO.populateMAP::" + e);
			}

			PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = null;
			ValueObject vo[] = null;
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (rs.next())
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(PatDrugTreatmentDetailVO.class, rs);
					patDrugTreatmentDtlVO = new PatDrugTreatmentDetailVO[vo.length];
					for (int i = 0; i < vo.length; i++)
					{
						patDrugTreatmentDtlVO[i] = (PatDrugTreatmentDetailVO) vo[i];
					}
				}

			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return patDrugTreatmentDtlVO;
		}
		
		
		

	public List getDrugSaftyAlertList(String itemId, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ValueObject vo[] = null;

		List drugSaftyVOList = new ArrayList();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HSTT_DRUG_SAFTY_ALERT_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{

			// populateMAP.put(sq.next(), admissionNo);

			populateMAP.put(sq.next(), itemId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getPendingConsentList::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No Record Found"); }
			 */
			if (rs.next())
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(DrugSaftyAlertMstVO.class, rs);
				for (ValueObject v : vo)
					drugSaftyVOList.add((DrugSaftyAlertMstVO) v);
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				// throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return drugSaftyVOList;
	}

	public List getDrugDoseIndicationList(String itemId, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HSTT_DRUG_DOS_INDICATION_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), itemId);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		List drugDoseIndicationVOList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(DrugDoseIndicationMstVO.class, rs);
				for (ValueObject v : vo)
					drugDoseIndicationVOList.add((DrugDoseIndicationMstVO) v);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return drugDoseIndicationVOList;
	}

	public String getMaxSlNo(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_MAX_SLNO.HRGT_EPISODE_DRUG_SCHEDULE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
		populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
		// populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}
			return rs.getString(1);
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
	
	//Added by Vasu on 27.March.2019
	public void savePatDrugTreatmentDetailForPHRMS(PatDrugTreatmentDetailVO _patDrugDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.hint_phrms_upload_drug_dtl_log";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			if (_patDrugDtlVO.getAdmissionNo() == null) _patDrugDtlVO.setAdmissionNo("");
			populateMAP.put(sq.next(), _patDrugDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugName());
			if(_patDrugDtlVO.getDrugBrandId().indexOf("#") < 0)	
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugBrandId());
			else
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugBrandId().split("#")[1]);
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseName());
			populateMAP.put(sq.next(), _patDrugDtlVO.getFrequencyId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDays());

			populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTime());
			populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeHrs());
			// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeMin());

			populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDays());
			populateMAP.put(sq.next(), _patDrugDtlVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			// populateMAP.put(sq.next(), OpdConfig.TREATMENT_DETAIL_RX_CONTINUE_NEW);
			populateMAP.put(sq.next(), _patDrugDtlVO.getRxContinue());
			populateMAP.put(sq.next(), _userVO.getSeatId());

			populateMAP.put(sq.next(), _patDrugDtlVO.getEntryDate());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugAdminId()); 
			//populateMAP.put(sq.next(), _patDrugDtlVO.getIsEmptyStomach());  // is empty stomach field is replaced by drug administration (after meal, before meal, with meal) 
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugRouteId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDoseQty());
			populateMAP.put(sq.next(), _patDrugDtlVO.getIssueQty());
			populateMAP.put(sq.next(), _patDrugDtlVO.getRequiredQty());
			populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
			populateMAP.put(sq.next(), _patDrugDtlVO.getDrugTypeID());
			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

}
