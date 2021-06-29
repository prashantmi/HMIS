package opd.bo;

import hisglobal.business.GlobalEssentialBO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisEpisodeClosedException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.ChartCellData;
import hisglobal.utility.generictemplate.ChartColumnHead;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.dao.TemplateMasterDAO;
import hisglobal.utility.generictemplate.dao.TemplateParameterMasterDAO;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeAlertDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeAllergySitedDtlVO;
import hisglobal.vo.EpisodeAllergySymptomDtlVO;
import hisglobal.vo.EpisodeAttendantDetailVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientFamilyDtlVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileAlertsDtlVO;
import hisglobal.vo.ProfileAllergyDtlVO;
import hisglobal.vo.ProfileChartViewDtlVO;
import hisglobal.vo.ProfileClinicalDtlVO;
import hisglobal.vo.ProfileDiagnosisDtlVO;
import hisglobal.vo.ProfileDietAdviceDtlVO;
import hisglobal.vo.ProfileDrugAdviceDtlVO;
import hisglobal.vo.ProfileDrugScheduleDtlVO;
import hisglobal.vo.ProfileExtExamDtlVO;
import hisglobal.vo.ProfileExtTreatmentDtlVO;
import hisglobal.vo.ProfileFooterDtlVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileImageDtlVO;
import hisglobal.vo.ProfileInvDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.ProfileOTDtlVO;
import hisglobal.vo.ProfileProgressNotesDtlVO;
import hisglobal.vo.ProfileRestAdviceDtlVO;
import hisglobal.vo.ProfileReviewDtlVO;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.dao.DoctorRoundDtlDAO;
import inpatient.transaction.dao.DoctorRoundDtlDAOi;
import inpatient.transaction.dao.DrugAdminDtlDAO;
import inpatient.transaction.dao.DrugAdminDtlDAOi;
import inpatient.transaction.dao.DrugReactionDAO;
import inpatient.transaction.dao.DrugReactionDtlDAOi;
import inpatient.transaction.dao.ExtAdminDtlDAO;
import inpatient.transaction.dao.ExtAdminDtlDAOi;
import inpatient.transaction.dao.PatIntakeOutDtlDAO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import mrd.transaction.dao.EMRPatientDetailDAO;
import mrd.transaction.dao.MRDDocumentUploadDAO;
import mrd.transaction.dao.PatientFamilyDtlDAO;
import mrd.transaction.dao.PatientFamilyDtlDAOi;
import opd.OpdConfig;
import opd.dao.ChartDetailDAO;
import opd.dao.ChartDetailDAOi;
import opd.dao.ChartMasterDAO;
import opd.dao.ChartMasterDAOi;
import opd.dao.ConsultationDtlDAO;
import opd.dao.ConsultationProfileDtlDAO;
import opd.dao.ConsultationProfileDtlDAOi;
import opd.dao.CosultationDtlDAOi;
import opd.dao.DocumentUploadDtlDAO;
import opd.dao.DocumentUploadDtlDAOi;
import opd.dao.DrugScheduleDtlDAO;
import opd.dao.EpisodeAlertDtlDAO;
import opd.dao.EpisodeAlertDtlDAOi;
import opd.dao.EpisodeAllergiesDAO;
import opd.dao.EpisodeAllergiesNewDAO;
import opd.dao.EpisodeAllergySiteDtlDAO;
import opd.dao.EpisodeAllergySymptomDtlDAO;
import opd.dao.EpisodeAttendantDtlDAO;
import opd.dao.EpisodeAttendantDtlDAOi;
import opd.dao.EpisodeConsentDtlDAO;
import opd.dao.EpisodeExtInvDtlDAO;
import opd.dao.EpisodeExtInvDtlDAOi;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.OpdPatientImageDtlDAO;
import opd.dao.OpdPatientImageDtlDAOi;
import opd.dao.PatAlertsDetailDAO;
import opd.dao.PatAlertsDetailDAOi;
import opd.dao.PatAllergyDtlDAO;
import opd.dao.PatDietAdviceDetailDAO;
import opd.dao.PatDietAdviceDetailDAOi;
import opd.dao.PatDrugTreatmentDetailDAO;
import opd.dao.PatDrugTreatmentDetailDAOi;
import opd.dao.PatExtTreatmentDtlDAO;
import opd.dao.PatExtTreatmentDtlDAOi;
import opd.dao.PatientClinicalDetailDAO;
import opd.dao.PatientClinicalDetailDAOi;
import opd.dao.PatientProfileDetailDAO;
import opd.dao.PatientProfileDetailDAOi;
import opd.dao.PatietnBelongingDAO;
import opd.dao.ProfileAccessDetailDAO;
import opd.dao.ProfileAccessDetailDAOi;
import opd.dao.ProfileAlertsDtlDAO;
import opd.dao.ProfileAlertsDtlDAOi;
import opd.dao.ProfileAllergyDtlDAO;
import opd.dao.ProfileAllergyDtlDAOi;
import opd.dao.ProfileChartViewDetailDAO;
import opd.dao.ProfileChartViewDetailDAOi;
import opd.dao.ProfileClinicalDtlDAO;
import opd.dao.ProfileClinicalDtlDAOi;
import opd.dao.ProfileDiagnosisDtlDAO;
import opd.dao.ProfileDiagnosisDtlDAOi;
import opd.dao.ProfileDietAdviceDtlDAO;
import opd.dao.ProfileDietAdviceDtlDAOi;
import opd.dao.ProfileDrugAdviceDtlDAO;
import opd.dao.ProfileDrugAdviceDtlDAOi;
import opd.dao.ProfileDrugScheduleDtlDAO;
import opd.dao.ProfileDrugScheduleDtlDAOi;
import opd.dao.ProfileExtExamDtlDAO;
import opd.dao.ProfileExtTreatmentDtlDAO;
import opd.dao.ProfileExtTreatmentDtlDAOi;
import opd.dao.ProfileFooterDtlDAO;
import opd.dao.ProfileFooterDtlDAOi;
import opd.dao.ProfileImageDtlDAO;
import opd.dao.ProfileImageDtlDAOi;
import opd.dao.ProfileInvDtlDAO;
import opd.dao.ProfileInvDtlDAOi;
import opd.dao.ProfileOTDtlDAO;
import opd.dao.ProfileOTDtlDAOi;
import opd.dao.ProfileProgressNotesDtlDAO;
import opd.dao.ProfileRestAdviceDtlDAO;
import opd.dao.ProfileRestAdviceDtlDAOi;
import opd.dao.ProfileReviewDtlDAO;
import opd.dao.ProfileReviewDtlDAOi;
import opd.dao.ProfileTypeDAO;
import opd.dao.RestAdviceDtlDAO;
import opd.dao.RestAdviceDtlDAOi;
import opd.master.dao.OPDClinicalDetailDAO;
import registration.RegistrationConfig;
import registration.dao.DailyPatientDAO;
import registration.dao.EpisodeActionDtlDAO;
import registration.dao.EpisodeCloseDAO;
import registration.dao.EpisodeCloseDAOi;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeDiagnosisDAO;
import registration.dao.EpisodeDiagnosisDAOi;
import registration.dao.EpisodeRefDtlDAO;
import registration.dao.EpisodeSummaryDAO;
import registration.dao.EpisodeSummaryDAOi;
import registration.dao.PatientImageDtlDAO;
import emr.vo.EHR_PatientProfileDtlVO;
//import appointment.dao.Apt_appointmentDtlDAO;
//import appointment.dao.Apt_slotDtlDAO;

public class OpdPatientBO implements OpdPatientBOi
{

	public EpisodeCloseVO[] getPatientEpisodeDtl(String crNo, UserVO _userVO, PatientVO _patientVO)
	{
		EpisodeCloseVO[] episodeClosevo;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeCloseDAO objEpisodeCloseDAO = new EpisodeCloseDAO(tx);
			episodeClosevo = objEpisodeCloseDAO.getPatientEpisodeDtl(crNo, _userVO, _patientVO);
		}
		catch (HisEpisodeClosedException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return episodeClosevo;
	}

	public void updatePatientEpisode(String crNo, UserVO _userVO, EpisodeCloseVO[] selectedEpisodeCloseVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// EpisodeVO episodeVO=new EpisodeVO();
		// EpisodeCloseVO episodeCloseVO=new EpisodeCloseVO();
		
		// System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyy"+_patientVO.getEpisodeCode());
		// selectedEpisodeCloseVO[i].getEpisodeCode();
		try
		{
			tx.begin();
			// EpisodeCloseDAO episodeCloseDAO=new EpisodeCloseDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			for (int i = 0; i < selectedEpisodeCloseVO.length; i++)
			{
				episodeDAO.updateIsEpisodeOpen(crNo, selectedEpisodeCloseVO[i].getEpisodeCode());
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void saveOpdDiagnosisDetails(EpisodeDiagnosisVO[] episodeDiagnosisVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			tx.begin();

			for (int i = 0; i < episodeDiagnosisVO.length; i++)
			{
				episodeDiagnosisDAO.create(episodeDiagnosisVO[i], userVO);
				//Added by Vasu on 26.March.2019 for PHRMS Integration 
				//episodeDiagnosisDAO.pushDiagnosisDataIntoPHRMS(episodeDiagnosisVO[i], userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	/**
	 * Modifies patient details at Registration Desk. Modification at registration can be done with in 10 minutes of
	 * registration. Updates the record in Patient dtl and Address dtl tables. Also updates patient's demographic details in
	 * Daily Patient Detail table.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated successfully otherwise <code>false</code>.
	 */
	// *
	public void updateOpdPatientEpisode(String patCrNo, String SerialNo, String visitNo, String episodeCode, String isConfirmed,
			String episodeIsOpen, String nextVisitDate, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			tx.begin();
			if (episodeIsOpen.equals(RegistrationConfig.EPISODE_ISOPEN_FALSE))
			{
				dailyPatientDAO.updateIsconfirmed(patCrNo, SerialNo, visitNo, episodeCode, isConfirmed, _userVO);
				episodeDAO.updateEpisodeIsOpenAndIsConfirm(patCrNo, visitNo, episodeCode, isConfirmed, episodeIsOpen, _userVO);
			}
			else
			{
				dailyPatientDAO.updateIsconfirmed(patCrNo, SerialNo, visitNo, episodeCode, isConfirmed, _userVO);
				episodeDAO.updateNextVisitDateAndIsConfirm(patCrNo, visitNo, episodeCode, isConfirmed, nextVisitDate, _userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public void saveOpdPatientReferDetail(EpisodeRefDtlVO[] episodeRefDtlVO, UserVO userVO, String _deskType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);

			for (int i = 0; i < episodeRefDtlVO.length; i++)
			{
				episodeRefDtlVO[i].setIsValid(Config.IS_VALID_ACTIVE);
				episodeRefDtlDAO.create(episodeRefDtlVO[i], userVO);

				// ///only for CMO Desk////////
				if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_CMO_DESK))
				{
					EpisodeVO _episodeVO = new EpisodeVO();
					HelperMethods.populate(_episodeVO, episodeRefDtlVO[i]);
					_episodeVO.setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_ATTENDED_REF_OTHER_HOSPITAL);
					_episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
					_episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
					episodeDAO.updateEpisodeDtlForCMO(_episodeVO, userVO);

					EpisodeActionDtlVO _episodeActDtlVO = new EpisodeActionDtlVO();
					HelperMethods.populate(_episodeActDtlVO, _episodeVO);
					episodeActionDtlDAO.create(_episodeActDtlVO, userVO);
				}
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	/*
	 * public EpisodeAllergiesVO[] getEpisodeAllergiesByPatient(DailyPatientVO _dailyPatientVO, UserVO _userVO) {
	 * 
	 * JDBCTransactionContext tx = new JDBCTransactionContext(); EpisodeAllergiesVO[] arrayEpisodeAlleryVO = null;
	 * EpisodeAllergiesVO episodeAllergyVO = new EpisodeAllergiesVO(); try { tx.begin(); EpisodeAllergiesDAO episodeAlleryDAO =
	 * new EpisodeAllergiesDAO(tx);
	 * 
	 * episodeAllergyVO.setPatCrNo(_dailyPatientVO.getPatCrNo());
	 * episodeAllergyVO.setEpisodeCode(_dailyPatientVO.getEpisodeCode());
	 * episodeAllergyVO.setEpisodeVisitNo(_dailyPatientVO.getEpisodeVisitNo());
	 * 
	 * arrayEpisodeAlleryVO = episodeAlleryDAO.getEpisodeAllergiesByPatient(episodeAllergyVO, _userVO);
	 *  } catch (HisRecordNotFoundException e) { tx.rollback(); throw new HisRecordNotFoundException(e.getMessage()); } catch
	 * (HisApplicationExecutionException e) { tx.rollback(); e.printStackTrace(); throw new
	 * HisApplicationExecutionException(); }
	 * 
	 * catch (HisDataAccessException e) { tx.rollback(); e.printStackTrace(); throw new HisDataAccessException(); } catch
	 * (Exception e) { e.printStackTrace();  tx.rollback(); throw new
	 * HisApplicationExecutionException(); } finally { tx.close(); } return arrayEpisodeAlleryVO;
	 *  }
	 */
	public PatAllergyDtlVO[] getEpisodeAllergiesByPatient(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatAllergyDtlVO[] arrayPatAlleryDtlVO = null;
		// EpisodeAllergiesVO episodeAllergyVO = new EpisodeAllergiesVO();
		PatAllergyDtlVO patAllergyVO = new PatAllergyDtlVO();
		try
		{
			tx.begin();
			// EpisodeAllergiesDAO episodeAlleryDAO = new EpisodeAllergiesDAO(tx);
			PatAllergyDtlDAO patAllergyDtlDAO = new PatAllergyDtlDAO(tx);
			patAllergyVO.setPatCrNo(_dailyPatientVO.getPatCrNo());

			arrayPatAlleryDtlVO = patAllergyDtlDAO.getEpisodeAllergiesByPatient(patAllergyVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrayPatAlleryDtlVO;
	}

	public void sendOpdConsultantData(ConsultationDtlVO consultationDtlVO, List<ConsultationProfileDtlVO> consultationProfileDtlVOList, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			CosultationDtlDAOi consultationDtlDAOi = new ConsultationDtlDAO(tx);
			ConsultationProfileDtlDAOi consultationProfileDtlDAOi = new ConsultationProfileDtlDAO(tx);

			consultationDtlVO.setMailRequestId("10001");
			tx.begin();
			GlobalEssentialBO globalEssentialBO = new GlobalEssentialBO();
			String[] sysdate = globalEssentialBO.getSysDate(userVO);

			consultationDtlVO.setSentDate(sysdate[0]);
			String oldMaxRequestId = consultationDtlDAOi.getMaxRequestId(userVO);
			String requestId = OpdPatientBOSupport.genrateOpdConsultationNewRequestId(oldMaxRequestId, sysdate[0], userVO);
			// consultationDtlVO.setAckStatus(OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW);
			consultationDtlVO.setMailRequestId(requestId);
			consultationDtlDAOi.create(consultationDtlVO, userVO);

			if (consultationProfileDtlVOList != null) for (int i = 0; i < consultationProfileDtlVOList.size(); i++)
			{
				consultationProfileDtlVOList.get(i).setMailRequestId(consultationDtlVO.getMailRequestId());
				consultationProfileDtlDAOi.create(consultationProfileDtlVOList.get(i), userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}
	

	public PatAllergyDtlVO[] getEpisodeAllergiesByPatientNew(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatAllergyDtlVO[] arrayPatAlleryDtlVO = null;
		// EpisodeAllergiesVO episodeAllergyVO = new EpisodeAllergiesVO();
		PatAllergyDtlVO patAllergyVO = new PatAllergyDtlVO();
		try
		{
			tx.begin();
			// EpisodeAllergiesDAO episodeAlleryDAO = new EpisodeAllergiesDAO(tx);
			EpisodeAllergiesNewDAO patAllergyDtlDAO = new EpisodeAllergiesNewDAO(tx);
			patAllergyVO.setPatCrNo(_dailyPatientVO.getPatCrNo());

			arrayPatAlleryDtlVO = patAllergyDtlDAO.getEpisodeAllergiesByPatient(patAllergyVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrayPatAlleryDtlVO;
	}
	

	// * Saving Template Parameter Values in Clinical Table
	public void saveOrReplaceOpdTempParaValues(OpdClinicalDetailVO clinicalDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			OPDClinicalDetailDAO daoObj = new OPDClinicalDetailDAO(tx);
			tx.begin();
			if (daoObj.hasRecord(clinicalDtlVO, userVO)) daoObj.update(clinicalDtlVO, userVO);
			else daoObj.create(clinicalDtlVO, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// * Removing Template Parameter Values in Clinical Table
	public void removeOpdTempParaValues(OpdClinicalDetailVO clinicalDtlVO, List tempIds, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			OPDClinicalDetailDAO daoObj = new OPDClinicalDetailDAO(tx);
			tx.begin();
			String ids = "";
			for (Object o : tempIds)
			{
				ids += "'" + ((Entry) o).getValue() + "',";
			}
			if (ids.length() > 0) ids = ids.substring(0, ids.length() - 1);
			daoObj.deleteAll(clinicalDtlVO, ids, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving Template Parameter Values in Clinical Table from List
	public void saveOpdTempParaValues(List<OpdClinicalDetailVO> lst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			OPDClinicalDetailDAO daoObj = new OPDClinicalDetailDAO(tx);
			tx.begin();
			for (OpdClinicalDetailVO vo : lst)
			{
				daoObj.create(vo, userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void updateMailAckStatus(ConsultationDtlVO consultationDtlVO, String mailId, String ackStatus, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			CosultationDtlDAOi consultationDtlDAOi = new ConsultationDtlDAO(tx);
			ConsultationDtlVO consultationVO = new ConsultationDtlVO();
			GlobalEssentialDAO globalEssentialDAO = new GlobalEssentialDAO(tx);
			tx.begin();
			String oldMailType = consultationDtlVO.getMailType();
			WebUTIL.populate(consultationVO, consultationDtlVO);
			String fromDoctorCode = consultationDtlVO.getToDoctorCode();
			String fromDoctorSeatId = consultationDtlVO.getToDoctorSeatId();
			consultationVO.setToDoctorCode(consultationDtlVO.getFromDoctorCode());
			consultationVO.setToDoctorSeatId(consultationDtlVO.getFromDoctorSeatId());
			consultationVO.setFromDoctorCode(fromDoctorCode);
			consultationVO.setFromDoctorSeatId(fromDoctorSeatId);
			consultationVO.setMailType(OpdConfig.OPD_COSULTANT_ACKNOWLEDGEMENT_MAIL);
			consultationVO.setAckStatus(OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW);
			consultationVO.setSubject(OpdConfig.OPD_ACK_SUBJECT);
			consultationVO.setContent(OpdConfig.OPD_ACK_CONTENT);
			consultationVO.setMailParentRequestId(consultationDtlVO.getMailRequestId());
			String[] sysdate = globalEssentialDAO.getSystemDate(userVO);
			String oldMaxRequestId = consultationDtlDAOi.getMaxRequestId(userVO);
			String requestId = OpdPatientBOSupport.genrateOpdConsultationNewRequestId(oldMaxRequestId, sysdate[0], userVO);
			consultationVO.setMailRequestId(requestId);
			if (!oldMailType.equals(OpdConfig.OPD_COSULTANT_ACKNOWLEDGEMENT_MAIL))
			{
				consultationDtlDAOi.create(consultationVO, userVO);
			}
			consultationDtlDAOi.updateConsultationAckStatus(mailId, ackStatus, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public String getNoOfNewMails(String seatId, String ackStatus)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count;
		try
		{
			CosultationDtlDAOi consultationDtlDAOi = new ConsultationDtlDAO(tx);
			tx.begin();
			count = consultationDtlDAOi.getNoOfNewMailsBySeatId(seatId, ackStatus);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return count;

	}

	public void deleteMails(String[] mailIdArray, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int i = 0;
		try
		{
			CosultationDtlDAOi consultationDtlDAOi = new ConsultationDtlDAO(tx);
			tx.begin();
			for (; i < mailIdArray.length; i++)
			{
				consultationDtlDAOi.updateConsultationAckStatus(mailIdArray[i], OpdConfig.OPD_CONSULTANT_ACK_STATUS_DELETED, userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	/*
	 * public Apt_appointmentDtlVO SaveNextVisitAppiontemntData(Apt_appointmentDtlVO _appointmentDtlVO, Apt_slotDtlVO
	 * _slotDtlVO, PatientVO _patientVO, UserVO userVO) {
	 * 
	 * JDBCTransactionContext tx = new JDBCTransactionContext(); try { tx.begin(); Apt_appointmentDtlDAO _appointmentDtlDAO =
	 * new Apt_appointmentDtlDAO(tx); _appointmentDtlVO = _appointmentDtlDAO.SaveData(_appointmentDtlVO, _slotDtlVO,
	 * _patientVO, userVO); UpdateSlotAptPersonDtl(_appointmentDtlVO, _slotDtlVO, userVO); } catch
	 * (HisRecordNotFoundException e) { tx.rollback(); } catch (HisApplicationExecutionException e) { tx.rollback();
	 * e.printStackTrace(); throw new HisApplicationExecutionException(); } catch (HisDataAccessException e) { tx.rollback();
	 * e.printStackTrace(); throw new HisDataAccessException(); } catch (Exception e) { e.printStackTrace();
	 * 
	 * tx.rollback(); throw new HisApplicationExecutionException(); } finally { tx.close(); } return _appointmentDtlVO; }
	 * 
	 * public void UpdateSlotAptPersonDtl(Apt_appointmentDtlVO _appointmentDtlVO, Apt_slotDtlVO _slotDtlVO, UserVO userVO) {
	 * JDBCTransactionContext tx = new JDBCTransactionContext(); try { tx.begin(); Apt_slotDtlDAO _slotDtlDAO = new
	 * Apt_slotDtlDAO(tx); _slotDtlDAO.UpdateSlotAptPersonDtl(_appointmentDtlVO, _slotDtlVO, userVO); } catch
	 * (HisRecordNotFoundException e) { tx.rollback(); } catch (HisApplicationExecutionException e) { tx.rollback();
	 * e.printStackTrace(); throw new HisApplicationExecutionException(); } catch (HisDataAccessException e) { tx.rollback();
	 * e.printStackTrace(); throw new HisDataAccessException(); } catch (Exception e) { e.printStackTrace();
	 * 
	 * tx.rollback(); throw new HisApplicationExecutionException(); } finally { tx.close(); } }
	 */

	public void saveDocumentDetail(DocumentUploadDtlVO[] docUploadVos, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int i = 0;
		try
		{
			DocumentUploadDtlDAOi docUploadDaoi = new DocumentUploadDtlDAO(tx);
			tx.begin();
			for (; i < docUploadVos.length; i++)
			{
				// HelperMethods.storeImageInFileSystem(docUploadVos[i].getDocFile(), docUploadVos[i].getDocumentName(),
				// docUploadVos[i]
				// .getDocumentName(), Config.OPD_DOCUMENT_FILE_STORAGE_PATH);
				HisFileControlUtil fileUtil = new HisFileControlUtil();
				fileUtil.setFileName(docUploadVos[i].getDocumentName());
				fileUtil.setWindowsFilePath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS);
				fileUtil.setLinuxFilePath(Config.OPD_DOCUMENT_FILE_STORAGE_PATH_LINUX);
				fileUtil.setFileContent(docUploadVos[i].getDocFile());
				fileUtil.saveFile();
				docUploadDaoi.create(docUploadVos[i], userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void saveAllergyDetails(EpisodeAllergiesVO[] _episodeAllergiesVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		PatAllergyDtlVO patAllergyDtlVO = new PatAllergyDtlVO();
		EpisodeAllergySitedDtlVO epiAllergySiteVO = new EpisodeAllergySitedDtlVO();
		EpisodeAllergySymptomDtlVO epiAllergySymptomVO = new EpisodeAllergySymptomDtlVO();
		boolean exist = false;
		String count = "";

		try
		{

			EpisodeAllergiesDAO episodeAllergyDAO = new EpisodeAllergiesDAO(tx);
			PatAllergyDtlDAO patAllergyDtlDAO = new PatAllergyDtlDAO(tx);
			EpisodeAllergySymptomDtlDAO epiAllergySymptomDAO = new EpisodeAllergySymptomDtlDAO(tx);
			EpisodeAllergySiteDtlDAO epiAllergySiteDAO = new EpisodeAllergySiteDtlDAO(tx);
			tx.begin();

			for (int i = 0; i < _episodeAllergiesVO.length; i++)
			{
				_episodeAllergiesVO[i].setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_NO);
				
				/*if(!episodeAllergyDAO.checkAlreadyExistCode(patAllergyDtlVO, _userVO))
				{*/
					episodeAllergyDAO.create(_episodeAllergiesVO[i], _userVO);
				/*}
*/
				// Inserting into HRGT_EPISODE_ALLERGY_SYMDTL
				if (_episodeAllergiesVO[i].getAllergySiteCode() != null && !_episodeAllergiesVO[i].getAllergySiteCode().equals(""))
				{
					String siteCode = _episodeAllergiesVO[i].getAllergySiteCode();
					String[] allergySiteCode = siteCode.split("#");
					for (int j = 0; j < allergySiteCode.length; j++)
					{
						HelperMethods.populate(epiAllergySiteVO, _episodeAllergiesVO[i]);
						epiAllergySiteVO.setAllergySiteID(allergySiteCode[j]);
						epiAllergySiteDAO.create(epiAllergySiteVO, _userVO);
					}
				}
				// Inserting into HRGT_EPISODE_ALLERGY_SITEDTL
				if (_episodeAllergiesVO[i].getAllergySymtomsCode() != null && !_episodeAllergiesVO[i].getAllergySymtomsCode().equals(""))
				{
					String symptomCode = _episodeAllergiesVO[i].getAllergySymtomsCode();
					String[] allergySymptomCode = symptomCode.split("#");
					for (int j = 0; j < allergySymptomCode.length; j++)
					{
						HelperMethods.populate(epiAllergySymptomVO, _episodeAllergiesVO[i]);
						epiAllergySymptomVO.setAllergySymptomID(allergySymptomCode[j]);
						epiAllergySymptomDAO.create(epiAllergySymptomVO, _userVO);
					}
				}
				patAllergyDtlVO.setAllergyTypeCode(_episodeAllergiesVO[i].getAllergiesCode());
				patAllergyDtlVO.setAllergyTypeName(_episodeAllergiesVO[i].getAllergyTypeName());
				patAllergyDtlVO.setAllergyID(_episodeAllergiesVO[i].getReasonCode());
				patAllergyDtlVO.setAllergyName(_episodeAllergiesVO[i].getReasonName());
				patAllergyDtlVO.setPatCrNo(_episodeAllergiesVO[i].getPatCrNo());
				patAllergyDtlVO.setDuration(_episodeAllergiesVO[i].getDuration());

				exist = patAllergyDtlDAO.checkAlreadyExistCode(patAllergyDtlVO, _userVO);
				if (!exist)
				{
					count = patAllergyDtlDAO.getCount(patAllergyDtlVO);
					patAllergyDtlVO.setSerialNo(count);
					patAllergyDtlDAO.create(patAllergyDtlVO, _userVO);
				}
				else
					patAllergyDtlDAO.updateNewIfExists(patAllergyDtlVO, _userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}
	
	
	// Added on Date: 26.9.2016 for sonmed ct integration
		public void saveAllergyDetailsNew(List<EpisodeAllergiesVO> _episodeAllergiesVO, EpisodeAllergiesVO voDP,  UserVO userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			EpisodeAllergiesNewDAO episodeAllergyDAO = new EpisodeAllergiesNewDAO(tx);
			
			EpisodeAllergiesDAO episodeAllergiesdao = new EpisodeAllergiesDAO(tx);
			EpisodeAllergySiteDtlDAO episodeAllergySiteDtldao = new EpisodeAllergySiteDtlDAO(tx);
			EpisodeAllergySymptomDtlDAO episodeAllergSymDtldao = new EpisodeAllergySymptomDtlDAO(tx);
			PatAllergyDtlDAO  patAllergyDtlDAO = new PatAllergyDtlDAO(tx);
			
			EpisodeAllergySymptomDtlVO allergysymdtlvo= new EpisodeAllergySymptomDtlVO();
			PatAllergyDtlVO patAllergyDtlVO = new PatAllergyDtlVO();
			EpisodeAllergySitedDtlVO siteDtlVO= new EpisodeAllergySitedDtlVO();
			String count = "";
			
			boolean exist = false;
			try
			{
				tx.begin();
			
				for (EpisodeAllergiesVO vo : _episodeAllergiesVO)
				{
					//HelperMethods.populate(epiAlertDtlVO, vo);
					//epiAlertDtlVO.setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_NO);
					episodeAllergyDAO.create(vo, voDP, userVO);
					/**Added by Vasu on 26.March.2019 for PHRMS Integration*/
					vo.setPatCrNo(voDP.getPatCrNo());
					if(voDP.getPatAdmNo()!=null && !voDP.getPatAdmNo().equals(""))
						vo.setPatAdmNo(voDP.getPatAdmNo());
					//episodeAllergyDAO.uploadAllergyDetailsLogPHRMS(vo, userVO);
					
					count = patAllergyDtlDAO.getCount(patAllergyDtlVO);
					vo.setSerialNo(count);
					/*exist = patAllergyDtlDAO.checkAlreadyExistCode(patAllergyDtlVO, userVO);
					if (!exist)
					{
						count = patAllergyDtlDAO.getCount(patAllergyDtlVO);
						patAllergyDtlVO.setSerialNo(count);
						//patAllergyDtlDAO.create(patAllergyDtlVO, _userVO);
					}*/
					// Changes done for saving 'PATIENT ALLERGIES' NEW PROCESS 'DATA' to OLD 'ALLERGIES' PROCESS 'TABLES'
					//DATE: 12.01.2017
					HelperMethods.populatetToNullOrEmpty(vo, voDP);
					episodeAllergyDAO.saveinEpisodeAllergy_dtl(vo, userVO);
					episodeAllergyDAO.saveinPatAllergydtl(vo,  userVO);
					
				}
				

				/*// Adding Patient Allergies
				for (PatAllergyDtlVO vo : _episodeAllergiesVO)
				{
					//patAllergyDtlDAO.createNew(vo, userVO);

					//HelperMethods.populate(epiAlertDtlVO, vo);
					//epiAlertDtlVO.setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_NO);
					//episodeAllergyDAO.createNew(epiAlertDtlVO, userVO);
					
				
				
				patAllergyDtlVO.setAllergyTypeCode(vo.getAllergiesCode());
				patAllergyDtlVO.setAllergyTypeName(vo.getAllergyTypeName());
				patAllergyDtlVO.setAllergyID(vo.getReasonCode());
				patAllergyDtlVO.setAllergyName(vo.getReasonName());
				patAllergyDtlVO.setPatCrNo(vo.getPatCrNo());
				patAllergyDtlVO.setDuration(vo.getDuration());
				
				exist = patAllergyDtlDAO.checkAlreadyExistCode(patAllergyDtlVO, userVO);
				if (!exist)
					patAllergyDtlDAO.createNew(vo, userVO);
				else
					patAllergyDtlDAO.updateNewIfExists(patAllergyDtlVO, userVO);
				episodeAllergyDAO.createNew(_episodeAllergiesVO, userVO);
				}*/
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException();
			}
			catch (Exception e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}

		}
	

	public EpisodeVO[] getOpdProfilePatientEpisodes(String _crNo, String _departmentUnitCode, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] episodeVOs = null;
		try
		{
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			tx.begin();
			episodeVOs = episodeDAO.retrieveEpisodeForProfileByCrNo(_crNo, _departmentUnitCode, _userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVOs;

	}

	/**
	 * Getting Episode Diagnosis Detail for Patient Profile
	 * 
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public EpisodeDiagnosisVO[] getOpdDiagnosisDetailProfilePatientEpisodes(String _crNo, PatientDetailVO voDp, String _deskType,
			UserVO _userVO, String profileGenerationMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeDiagnosisVO[] episodeDiagnosisVOs = null;
		try
		{
			EpisodeDiagnosisDAOi episodeDiagnosisiDAOi = new EpisodeDiagnosisDAO(tx);
			tx.begin();

			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				episodeDiagnosisVOs = episodeDiagnosisiDAOi.retrieveAdmissionDiagnosisForProfile(_crNo, voDp.getPatAdmNo(), _userVO);
			}
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				episodeDiagnosisVOs = episodeDiagnosisiDAOi.retrieveEpisodeDiagnosisForProfile(_crNo, voDp, _userVO, profileGenerationMode);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeDiagnosisVOs;
	}

	/**
	 * Saving Patient Profile
	 * 
	 * @param _patProfileDtlVO Patient Profile Detail
	 * @param _userVO User VO
	 */
	public String savePatientProfile(PatientProfileDetailVO _patProfileDtlVO, Map inAllMap, Map inAllPreviousMap,
			UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO, UserVO _userVO)
	{
		String profileId = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			ProfileDiagnosisDtlDAOi profileDiagnosisDetailDAO = new ProfileDiagnosisDtlDAO(tx);
			ProfileAllergyDtlDAOi profileAllergyDetailDAO = new ProfileAllergyDtlDAO(tx);
			ProfileInvDtlDAOi profileInvDtlDAO = new ProfileInvDtlDAO(tx);
			ProfileOTDtlDAOi profileOTDetailDAO = new ProfileOTDtlDAO(tx);
			ProfileDrugAdviceDtlDAOi profileDrugAdviceDtlDAO = new ProfileDrugAdviceDtlDAO(tx);
			ProfileAlertsDtlDAOi profileAlertsDetailDAO = new ProfileAlertsDtlDAO(tx);
			ProfileFooterDtlDAOi profileFooterDtlDAO = new ProfileFooterDtlDAO(tx);
			ProfileDietAdviceDtlDAOi profileDietAdviceDtlDAOi = new ProfileDietAdviceDtlDAO(tx);
			ProfileRestAdviceDtlDAOi profileRestAdviceDtlDAOi = new ProfileRestAdviceDtlDAO(tx);
			ProfileExtTreatmentDtlDAOi profileExtTreatmentDtlDAOi = new ProfileExtTreatmentDtlDAO(tx);
			ProfileClinicalDtlDAOi profileClinicalDtlDAOi = new ProfileClinicalDtlDAO(tx);
			ProfileImageDtlDAOi profileImageDtlDAOi = new ProfileImageDtlDAO(tx);
			ProfileProgressNotesDtlDAO profileProgNotesDtlDAOi = new ProfileProgressNotesDtlDAO(tx);
			ProfileExtExamDtlDAO profileExtExamDtlDAOi = new ProfileExtExamDtlDAO(tx);
			ProfileDrugScheduleDtlDAOi profileDrugScheduleDAOi = new ProfileDrugScheduleDtlDAO(tx);
			ProfileChartViewDetailDAOi profileChartViewDetailDAOi = new ProfileChartViewDetailDAO(tx);
			
			
			tx.begin();

			// to invalid the data in profile diagnosis detail

			if (inAllPreviousMap.size() != 0)
			{

				Set set = inAllPreviousMap.keySet();

				Iterator itr = set.iterator();
				while (itr.hasNext())
				{
					String str = (String) itr.next();

					if (str.equals("DESKDIAGNOSIS"))
					{
						ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = (ProfileDiagnosisDtlVO[]) inAllPreviousMap.get("DESKDIAGNOSIS");
						if (profileDiagnosisDtlVO != null)
						{
							for (int j = 0; j < profileDiagnosisDtlVO.length; j++)
							{
								profileDiagnosisDetailDAO.updateIsValidStatus(profileDiagnosisDtlVO[j], _userVO);
							}
						}
					}
					if (str.equals("DESKALLERGIES"))
					{
						ProfileAllergyDtlVO[] profileAllegyDtlVO = (ProfileAllergyDtlVO[]) inAllPreviousMap.get("DESKALLERGIES");
						if (profileAllegyDtlVO != null)
						{
							for (int j = 0; j < profileAllegyDtlVO.length; j++)
							{
								profileAllergyDetailDAO.updateIsValidStatus(profileAllegyDtlVO[j], _userVO);
							}
						}
					}

					if (str.equals("RESULTVIEWING"))
					{
						ProfileInvDtlVO[] _profileInvDtlVO = (ProfileInvDtlVO[]) inAllPreviousMap.get("RESULTVIEWING");
						if (_profileInvDtlVO != null)
						{
							for (int j = 0; j < _profileInvDtlVO.length; j++)
							{
								profileInvDtlDAO.updateIsValidStatus(_profileInvDtlVO[j], _userVO);
							}
						}
					}

					if (str.equals("DESKTREATMENTDETAIL"))
					{
						ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) inAllPreviousMap.get("DESKTREATMENTDETAIL");
						if (_profileDrugAdviceDtlVO != null)
						{
							for (int j = 0; j < _profileDrugAdviceDtlVO.length; j++)
							{
								profileDrugAdviceDtlDAO.updateIsValidStatus(_profileDrugAdviceDtlVO[j], _userVO);
							}
						}
					}

					if (str.equals("GENERICPATIENTALERTS"))
					{
						ProfileAlertsDtlVO[] profileAlertsDtlVO = (ProfileAlertsDtlVO[]) inAllPreviousMap.get("GENERICPATIENTALERTS");
						if (profileAlertsDtlVO != null)
						{
							for (int j = 0; j < profileAlertsDtlVO.length; j++)
							{
								profileAlertsDetailDAO.updateIsValidStatus(profileAlertsDtlVO[j], _userVO);
							}
						}
					}

					if (str.equals("PROFILEFOOTER"))
					{
						ProfileFooterDtlVO[] profileFooterDetailVO = (ProfileFooterDtlVO[]) inAllPreviousMap.get("PROFILEFOOTER");
						if (profileFooterDetailVO != null)
						{
							for (int j = 0; j < profileFooterDetailVO.length; j++)
							{
								profileFooterDtlDAO.updateIsValidStatus(profileFooterDetailVO[j], _userVO);
							}
						}
					}

					if (str.equals("DISCHARGEPROFILEFOOTER"))
					{
						ProfileFooterDtlVO[] profileFooterDetailVO = (ProfileFooterDtlVO[]) inAllPreviousMap.get("DISCHARGEPROFILEFOOTER");
						if (profileFooterDetailVO != null)
						{
							for (int j = 0; j < profileFooterDetailVO.length; j++)
							{
								profileFooterDtlDAO.updateIsValidStatus(profileFooterDetailVO[j], _userVO);
							}
						}
					}

					if (str.equals("DRUGADVICE"))
					{
						ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = (ProfileDrugAdviceDtlVO[]) inAllPreviousMap.get("DRUGADVICE");
						if (_profileDrugAdviceDtlVO != null)
						{
							for (int j = 0; j < _profileDrugAdviceDtlVO.length; j++)
							{
								profileDrugAdviceDtlDAO.updateIsValidStatusDischarge(_profileDrugAdviceDtlVO[j], _userVO);
							}
						}
					}

					if (str.equals("DIETADVICE"))
					{
						ProfileDietAdviceDtlVO profileDietAdviceDtlVO = (ProfileDietAdviceDtlVO) inAllPreviousMap.get("DIETADVICE");
						if (profileDietAdviceDtlVO != null)
						{
							profileDietAdviceDtlDAOi.updateIsValidStatus(profileDietAdviceDtlVO, _userVO);
						}
					}

					if (str.equals("RESTADVICE"))
					{
						ProfileRestAdviceDtlVO profileRestAdviceDtlVO = (ProfileRestAdviceDtlVO) inAllPreviousMap.get("RESTADVICE");
						if (profileRestAdviceDtlVO != null)
						{
							profileRestAdviceDtlDAOi.updateIsValidStatus(profileRestAdviceDtlVO, _userVO);
						}
					}

					if (str.equals("OTHERADVICE"))
					{
						ProfileExtTreatmentDtlVO[] _profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) inAllPreviousMap.get("OTHERADVICE");
						if (_profileExtTreatmentDtlVO != null)
						{
							for (int j = 0; j < _profileExtTreatmentDtlVO.length; j++)
							{
								profileExtTreatmentDtlDAOi.updateIsValidStatus(_profileExtTreatmentDtlVO[j], _userVO);
							}
						}
					}

					if (str.equals("OTHERINSTRUCTIONS"))
					{
						ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVO = (ProfileExtTreatmentDtlVO[]) inAllPreviousMap.get("OTHERINSTRUCTIONS");
						if (profileExtTreatmentDtlVO != null)
						{
							for (int j = 0; j < profileExtTreatmentDtlVO.length; j++)
							{
								profileExtTreatmentDtlDAOi.updateIsValidStatus(profileExtTreatmentDtlVO[j], _userVO);
							}
						}
					}

					if (str.equals("OTHERACTIVITY"))
					{
						ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVOs = (ProfileExtTreatmentDtlVO[]) inAllPreviousMap.get("OTHERACTIVITY");
						if (profileExtTreatmentDtlVOs != null)
						{
							for (int j = 0; j < profileExtTreatmentDtlVOs.length; j++)
							{
								profileExtTreatmentDtlDAOi.updateIsValidStatus(profileExtTreatmentDtlVOs[j], _userVO);
							}
						}
					}

					if (str.equals("GENERICTEMPLATE"))
					{
						String profileId1 = "";
						ProfileClinicalDtlVO[] profileClinicalDetailVO = (ProfileClinicalDtlVO[]) inAllPreviousMap.get("GENERICTEMPLATE");
						if (profileClinicalDetailVO != null)
						{
							for (int j = 0; j < profileClinicalDetailVO.length; j++)
							{
								profileId1 = profileClinicalDetailVO[j].getProfileId();
								if (profileId1 != "")
								{
									break;
								}
							}
							profileClinicalDtlDAOi.updateIsValidStatus(profileId1, _userVO);
						}
					}

					if (str.equals("OTVIEWING"))
					{
						ProfileOTDtlVO[] profileOTDtlVOs = (ProfileOTDtlVO[]) inAllPreviousMap.get("OTVIEWING");
						if (profileOTDtlVOs != null)
						{
							for (int j = 0; j < profileOTDtlVOs.length; j++)
							{
								profileOTDetailDAO.updateIsValidStatus(profileOTDtlVOs[j], _userVO);
							}
						}
					}

					if (str.equals("IMAGEEXAMINATION"))
					{
						ProfileImageDtlVO[] profileImageDtlVOs = (ProfileImageDtlVO[]) inAllPreviousMap.get("IMAGEEXAMINATION");
						if (profileImageDtlVOs != null)
						{
							for (int j = 0; j < profileImageDtlVOs.length; j++)
							{
								profileImageDtlDAOi.updateIsValidStatus(profileImageDtlVOs[j], _userVO);
							}
						}
					}
					if (str.equals("DRUGSCHEDULE"))
					{
						List<ProfileDrugScheduleDtlVO> profileDrugScheduleDtlVOList = (List) inAllPreviousMap.get("DRUGSCHEDULE");
						if (profileDrugScheduleDtlVOList != null)
						{
							for (int j = 0; j < profileDrugScheduleDtlVOList.size(); j++)
							{
								profileDrugScheduleDAOi.updateIsValidStatus(profileDrugScheduleDtlVOList.get(j), _userVO);
							}
						}
					}
					if (str.equals("DOCTORROUND"))
					{
						ProfileProgressNotesDtlVO[] profileEpiProgNtesDtlVOs = (ProfileProgressNotesDtlVO[]) inAllPreviousMap.get("DOCTORROUND");
						if (profileEpiProgNtesDtlVOs != null)
						{
							for (int j = 0; j < profileEpiProgNtesDtlVOs.length; j++)
							{
								profileProgNotesDtlDAOi.updateIsValidStatus(profileEpiProgNtesDtlVOs[j], _userVO);
							}
						}
					}
					if (str.equals("EXTERNALEXAMINATION"))
					{
						ProfileExtExamDtlVO[] profileExtInvDtlVOs = (ProfileExtExamDtlVO[]) inAllPreviousMap.get("EXTERNALEXAMINATION");
						if (profileExtInvDtlVOs != null)
						{
							for (int j = 0; j < profileExtInvDtlVOs.length; j++)
							{
								profileExtExamDtlDAOi.updateIsValidStatus(profileExtInvDtlVOs[j], _userVO);
							}
						}
					}
					
					
					if (str.equals("CHARTDETAIL"))
					{
						/*ProfileChartViewDtlVO[] profileChartViewDtlVO = (ProfileChartViewDtlVO[]) inAllPreviousMap.get("CHARTDETAIL");
												
						if (profileChartViewDtlVO != null)
						{ //
							for (int j = 0; j < profileChartViewDtlVO.length; j++)
							{
								profileChartViewDetailDAOi.updateIsValidStatus(profileChartViewDtlVO[j],_userVO);
							}
						}*/
						
						
						List<ProfileChartViewDtlVO> profileChartViewDtlVO = (List<ProfileChartViewDtlVO>) inAllPreviousMap.get("CHARTDETAIL");
						if (profileChartViewDtlVO != null)
						{
						for (int i = 0; i < profileChartViewDtlVO.size(); i++)
						{
							ProfileChartViewDtlVO _episodeChartViewDetailVO = (ProfileChartViewDtlVO) profileChartViewDtlVO.get(i);
							profileChartViewDetailDAOi.updateIsValidStatus(_episodeChartViewDetailVO,_userVO);
										
						}
						}
					}
					

				}

				dao.updateOld(_patProfileDtlVO, _userVO);
			}

			// to insert data in pat profile detail
			profileId = dao.create(_patProfileDtlVO, _userVO);
			
			// to insert data in profile diagnosis detail
			List diagnosisList = (List) inAllMap.get("DESKDIAGNOSIS");

			int _diagnosisTypeList = diagnosisList.size();
			for (int i = 0; i < _diagnosisTypeList; i++)
			{
				EpisodeDiagnosisVO _episodeDiagnosisTypeVO = (EpisodeDiagnosisVO) diagnosisList.get(i);

				ProfileDiagnosisDtlVO _profileDiagnosisDtlVO = new ProfileDiagnosisDtlVO();
				HelperMethods.populate(_profileDiagnosisDtlVO, _episodeDiagnosisTypeVO);
				_profileDiagnosisDtlVO.setProfileId(profileId);

				profileDiagnosisDetailDAO.create(_profileDiagnosisDtlVO, _userVO);

			}

			// to insert data in profile allergy detail
			List allergyList = (List) inAllMap.get("DESKALLERGIES");

			int _allergyTypeList = allergyList.size();
			for (int i = 0; i < _allergyTypeList; i++)
			{
				PatAllergyDtlVO _patAllergyTypeVO = (PatAllergyDtlVO) allergyList.get(i);

				ProfileAllergyDtlVO _profileAllergyDtlVO = new ProfileAllergyDtlVO();
				HelperMethods.populate(_profileAllergyDtlVO, _patAllergyTypeVO);
				_profileAllergyDtlVO.setProfileId(profileId);

				profileAllergyDetailDAO.create(_profileAllergyDtlVO, _userVO);

			}

			// to insert data in profile investigation detail
			List investigationList = (List) inAllMap.get("RESULTVIEWING");

			int _investigationTypeList = investigationList.size();
			for (int i = 0; i < _investigationTypeList; i++)
			{
				ProfileInvestigationVO _profileInvestigationVO = (ProfileInvestigationVO) investigationList.get(i);

				ProfileInvDtlVO _profileInvDtlVO = new ProfileInvDtlVO();
				HelperMethods.populate(_profileInvDtlVO, _profileInvestigationVO);
				_profileInvDtlVO.setProfileId(profileId);

				profileInvDtlDAO.create(_profileInvDtlVO, _userVO);
			}

			// to insert data in profile drug advice detail
			List treatmentList = (List) inAllMap.get("DESKTREATMENTDETAIL");

			int _treatmentTypeList = treatmentList.size();
			for (int i = 0; i < _treatmentTypeList; i++)
			{
				PatDrugTreatmentDetailVO _patDrugTreatmentDtlVO = (PatDrugTreatmentDetailVO) treatmentList.get(i);

				ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO = new ProfileDrugAdviceDtlVO();
				HelperMethods.populate(_profileDrugAdviceDtlVO, _patDrugTreatmentDtlVO);
				_profileDrugAdviceDtlVO.setProfileId(profileId);
				_profileDrugAdviceDtlVO.setEntryMode(OpdConfig.PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DRUG_DETAIL);
				profileDrugAdviceDtlDAO.create(_profileDrugAdviceDtlVO, _userVO);

			}

			// to insert data in profile alerts detail
			List alertsList = (List) inAllMap.get("GENERICPATIENTALERTS");

			int _alertsTypeList = alertsList.size();
			for (int i = 0; i < _alertsTypeList; i++)
			{
				PatientAlertsDetailVO _patAlertsDetailVO = (PatientAlertsDetailVO) alertsList.get(i);

				ProfileAlertsDtlVO profileAlertsDtlVO = new ProfileAlertsDtlVO();

				HelperMethods.populate(profileAlertsDtlVO, _patAlertsDetailVO);
				profileAlertsDtlVO.setProfileId(profileId);
				profileAlertsDetailDAO.create(profileAlertsDtlVO, _userVO);

			}

			// to insert data in profile footer detail
			List footerTypeList = (List) inAllMap.get("PROFILEFOOTER");

			int _footerTypeList = footerTypeList.size();
			for (int i = 0; i < _footerTypeList; i++)
			{
				ProfileFooterDtlVO _profileFooterDtlVO = (ProfileFooterDtlVO) footerTypeList.get(i);
				_profileFooterDtlVO.setProfileId(profileId);
				profileFooterDtlDAO.create(_profileFooterDtlVO, _userVO);

			}

			// to insert data in profile footer detail for Discharge profile footer
			footerTypeList = (List) inAllMap.get("DISCHARGEPROFILEFOOTER");

			_footerTypeList = footerTypeList.size();
			for (int i = 0; i < _footerTypeList; i++)
			{
				ProfileFooterDtlVO _profileFooterDtlVO = (ProfileFooterDtlVO) footerTypeList.get(i);
				_profileFooterDtlVO.setProfileId(profileId);
				profileFooterDtlDAO.create(_profileFooterDtlVO, _userVO);

			}

			// to insert data in profile drug advice detail in case of discharge
			List lstDrugAdvice = (List) inAllMap.get("DRUGADVICE");

			int _lstDrugAdvice = lstDrugAdvice.size();
			for (int i = 0; i < _lstDrugAdvice; i++)
			{
				PatDrugTreatmentDetailVO _patDrugTreatmentDtlVO = (PatDrugTreatmentDetailVO) lstDrugAdvice.get(i);

				ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO = new ProfileDrugAdviceDtlVO();
				HelperMethods.populate(_profileDrugAdviceDtlVO, _patDrugTreatmentDtlVO);
				_profileDrugAdviceDtlVO.setProfileId(profileId);
				// get the max serial no
				int maxSerialNo = Integer.parseInt(profileDrugAdviceDtlDAO.getMaxSerialNo(_profileDrugAdviceDtlVO, _userVO));
				_profileDrugAdviceDtlVO.setEntryMode(OpdConfig.PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DISCHARGE);
				_profileDrugAdviceDtlVO.setRxContinue(OpdConfig.NEW_RECORD);
				profileDrugAdviceDtlDAO.create(_profileDrugAdviceDtlVO, _userVO);

				// /save the drug schedule detail for each drug//////////////////////////////////
				Map drugScheduleMap = (Map) inAllMap.get("DRUGSCHEDULE");
				String mapKey = _profileDrugAdviceDtlVO.getDrugId() + "#" + _profileDrugAdviceDtlVO.getFrequencyId() + "#"
						+ _profileDrugAdviceDtlVO.getDoseId();
				if(drugScheduleMap!=null)
				{
				List scheduleLst = (List) drugScheduleMap.get(mapKey);
				drugScheduleMap.remove(mapKey);
				if (scheduleLst != null)
				{
					for (int j = 0; j < scheduleLst.size(); j++)
					{
						DrugSheduleDtlVO drugSheduleDtlVO = (DrugSheduleDtlVO) scheduleLst.get(j);
						ProfileDrugScheduleDtlVO profileDrugScheduleDtlVO = new ProfileDrugScheduleDtlVO();
						HelperMethods.populate(profileDrugScheduleDtlVO, drugSheduleDtlVO);
						profileDrugScheduleDtlVO.setItemId(_profileDrugAdviceDtlVO.getDrugId());
						profileDrugScheduleDtlVO.setProfileId(_profileDrugAdviceDtlVO.getProfileId());
						profileDrugScheduleDtlVO.setSerialNo(String.valueOf(maxSerialNo));
						profileDrugScheduleDtlVO.setSheduleSerialNo(new Integer(j).toString());
						profileDrugScheduleDtlVO.setIsEmptyStomach(_profileDrugAdviceDtlVO.getIsEmptyStomach());
						profileDrugScheduleDAOi.create(profileDrugScheduleDtlVO, _userVO);

					}
				}

				maxSerialNo++;

				// ///////////////////////////////////////////////////////////////////////////

				}}

			// to insert data in profile diet advice detail in case of discharge
			List lstDietAdvice = (List) inAllMap.get("DIETADVICE");

			if (lstDietAdvice.size() != 0)
			{
				int _lstDietAdvice = lstDietAdvice.size();
				for (int i = 0; i < _lstDietAdvice; i++)
				{
					PatDietAdviceDetailVO _patDietAdviceDetailVO = (PatDietAdviceDetailVO) lstDietAdvice.get(i);

					ProfileDietAdviceDtlVO _profileDietAdviceDtlVO = new ProfileDietAdviceDtlVO();
					HelperMethods.populate(_profileDietAdviceDtlVO, _patDietAdviceDetailVO);
					_profileDietAdviceDtlVO.setProfileId(profileId);
					// _profileDrugAdviceDtlVO.setEntryMode(OpdConfig.PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DISCHARGE);

					profileDietAdviceDtlDAOi.create(_profileDietAdviceDtlVO, _userVO);
				}
			}

			// to insert data in profile diet advice detail in case of discharge
			List lstRestAdvice = (List) inAllMap.get("RESTADVICE");

			if (lstRestAdvice.size() != 0)
			{
				int _lstRestAdvice = lstRestAdvice.size();
				for (int i = 0; i < _lstRestAdvice; i++)
				{
					RestAdviceDtlVO _restAdviceDtlVO = (RestAdviceDtlVO) lstRestAdvice.get(i);

					ProfileRestAdviceDtlVO _profileRestAdviceDtlVO = new ProfileRestAdviceDtlVO();
					HelperMethods.populate(_profileRestAdviceDtlVO, _restAdviceDtlVO);
					_profileRestAdviceDtlVO.setProfileId(profileId);

					profileRestAdviceDtlDAOi.create(_profileRestAdviceDtlVO, _userVO);
				}
			}

			// to insert data in profile other advice detail in case of discharge
			List lstOtherAdvice = (List) inAllMap.get("OTHERADVICE");

			if (lstOtherAdvice.size() != 0)
			{
				int _lstOtherAdvice = lstOtherAdvice.size();
				for (int i = 0; i < _lstOtherAdvice; i++)
				{
					PatExtTreatmentDetailVO _patExtTreatmentDetailVO = (PatExtTreatmentDetailVO) lstOtherAdvice.get(i);

					ProfileExtTreatmentDtlVO _profileExtTreatmentDtlVO = new ProfileExtTreatmentDtlVO();
					HelperMethods.populate(_profileExtTreatmentDtlVO, _patExtTreatmentDetailVO);
					_profileExtTreatmentDtlVO.setProfileId(profileId);
					_profileExtTreatmentDtlVO.setTreatmentType(OpdConfig.EXTERNAL_TREATMENT);
					profileExtTreatmentDtlDAOi.create(_profileExtTreatmentDtlVO, _userVO);
				}
			}

			// to insert data in profile other advice detail in case of discharge
			List lstOtherInstructions = (List) inAllMap.get("OTHERINSTRUCTIONS");

			if (lstOtherInstructions.size() != 0)
			{
				int _lstOtherInstructions = lstOtherInstructions.size();
				for (int i = 0; i < _lstOtherInstructions; i++)
				{
					PatExtTreatmentDetailVO _patExtTreatmentDetailVO = (PatExtTreatmentDetailVO) lstOtherInstructions.get(i);

					ProfileExtTreatmentDtlVO _profileExtTreatmentDtlVO = new ProfileExtTreatmentDtlVO();
					HelperMethods.populate(_profileExtTreatmentDtlVO, _patExtTreatmentDetailVO);
					_profileExtTreatmentDtlVO.setProfileId(profileId);
					_profileExtTreatmentDtlVO.setTreatmentType(OpdConfig.OTHER_INSTRUCTION);
					profileExtTreatmentDtlDAOi.create(_profileExtTreatmentDtlVO, _userVO);
				}
			}

			// to insert data in profile other advice detail in case of discharge
			List lstOtherActivity = (List) inAllMap.get("OTHERACTIVITY");

			if (lstOtherActivity.size() != 0)
			{
				int _lstOtherActivity = lstOtherActivity.size();
				for (int i = 0; i < _lstOtherActivity; i++)
				{
					PatExtTreatmentDetailVO _patExtTreatmentDetailVO = (PatExtTreatmentDetailVO) lstOtherActivity.get(i);

					ProfileExtTreatmentDtlVO _profileExtTreatmentDtlVO = new ProfileExtTreatmentDtlVO();
					HelperMethods.populate(_profileExtTreatmentDtlVO, _patExtTreatmentDetailVO);
					_profileExtTreatmentDtlVO.setProfileId(profileId);
					_profileExtTreatmentDtlVO.setTreatmentType(OpdConfig.ONE_TIME_ACTIVITY);
					profileExtTreatmentDtlDAOi.create(_profileExtTreatmentDtlVO, _userVO);
				}
			}

			// to insert data in profile clinical detail
			Map mpDeskMenuReportMode = (Map) inAllMap.get("REPORTMODES");
			//Map mpTempParaValues = (Map) inAllMap.get("GENERICTEMPLATE");
			Map mpDeskMenuTempParaValues = (Map) inAllMap.get("GENERICTEMPLATE");
			//String templateDeskMenuId = (String) inAllMap.get("GENERICTEMPLATE_DESKMENUID"); 
			//List<Entry> lstVisitDates = (List) inAllMap.get("SELECTEDDATES");
			Map mpDeskMenuVisitDates = (Map) inAllMap.get("SELECTEDDATES");
			//Map mpTempParas = (Map) inAllMap.get("PARACOMPLAINTS");
			Map mpDeskMenuTempParas = (Map) inAllMap.get("PARACOMPLAINTS");

			
			//if (mpTempParaValues != null)
			if (mpDeskMenuTempParaValues != null)
			{
				Iterator mpDeskMenuKeyItr = mpDeskMenuTempParaValues.keySet().iterator();
				while (mpDeskMenuKeyItr.hasNext())
				{
					String deskMenuId = (String) mpDeskMenuKeyItr.next();
					//String reportMode = (String)mpDeskMenuReportMode.get(deskMenuId);  //commented by manisha gangwar date: 24.8.2017 //OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE;
					String reportMode = OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE; //Added by Vasu on 19.Aug.2018
					if (reportMode!=null && !reportMode.equals(""))
					{
						if (reportMode.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE))
						{
							Map mpTempParaValues = (Map) mpDeskMenuTempParaValues.get(deskMenuId);
							Iterator mpVisitsKeyItr = mpTempParaValues.keySet().iterator();
							List<Entry> lstVisitDates = (List<Entry>) mpDeskMenuVisitDates.get(deskMenuId);
							if(lstVisitDates != null)
							{
								while (mpVisitsKeyItr.hasNext())							
								{
									String _visit = (String) mpVisitsKeyItr.next();
									int _lstVisitDate = lstVisitDates.size();
									for (int i = 0; i < _lstVisitDate; i++)
									{
										Entry entry = (Entry) lstVisitDates.get(i);
										if (_visit.equals(entry.getLabel()))
											{
												Map mpTempIdValues = (Map) mpTempParaValues.get(_visit);
												Iterator mpTempIdValuesItr = mpTempIdValues.keySet().iterator();
												while (mpTempIdValuesItr.hasNext())
												{
													String tempId = (String) mpTempIdValuesItr.next();
													Map mpParaValues = (Map) mpTempIdValues.get(tempId);
													Set s = mpParaValues.entrySet();
	
													Iterator mpParaValuesItr = s.iterator();
													while (mpParaValuesItr.hasNext())
														{
															Map.Entry paraMap = (Map.Entry) mpParaValuesItr.next();
															String paraId = (String) paraMap.getKey();
															String paraValue = (String) paraMap.getValue();
															ProfileClinicalDtlVO profileClinicalDtlVO = new ProfileClinicalDtlVO();
															profileClinicalDtlVO.setRecordDate(entry.getLabel());
															profileClinicalDtlVO.setTemplateId(tempId);
															profileClinicalDtlVO.setParaId(paraId);
															profileClinicalDtlVO.setParaValue(paraValue);
															profileClinicalDtlVO.setProfileId(profileId);
															profileClinicalDtlVO.setRecordView(reportMode);
															profileClinicalDtlVO.setDeskMenuId(deskMenuId);
															profileClinicalDtlDAOi.create(profileClinicalDtlVO, _userVO);
														}
												}
											}
									}
								}
							}// end Visits
						}// end Report Modes Block
						else if (_patProfileDtlVO.getReportMode().equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
						{
							Map mpTempParaValues = (Map) mpDeskMenuTempParaValues.get(deskMenuId);
							Map mpTempParas = (Map) mpDeskMenuTempParas.get(deskMenuId);
							Iterator mpVisitsKeyItr = mpTempParaValues.keySet().iterator();
							List<Entry> lstVisitDates = (List) mpDeskMenuVisitDates.get(deskMenuId);
							while (mpVisitsKeyItr.hasNext())
							{
								String _visit = (String) mpVisitsKeyItr.next();
								String _visitDate = "";
								for(Entry ent : lstVisitDates)
									if(ent.getLabel().equals(_visit))
									{
										_visitDate = ent.getLabel();
										break;
									}
								Map mpTempIdValues = (Map) mpTempParaValues.get(_visit);
								Iterator mpTempIdValuesItr = mpTempIdValues.keySet().iterator();
								while (mpTempIdValuesItr.hasNext())
								{
									String tempId = (String) mpTempIdValuesItr.next();
									Map mpParaValues = (Map) mpTempIdValues.get(tempId);
									Set s = mpParaValues.entrySet();
	
									Iterator mpParaValuesItr = s.iterator();
									while (mpParaValuesItr.hasNext())
									{
										Map.Entry paraMap = (Map.Entry) mpParaValuesItr.next();
	
										String paraId = (String) paraMap.getKey();
										String paraValue = (String) paraMap.getValue();
	
										Iterator mpTempParasItr = mpTempParas.keySet().iterator();
										while (mpTempParasItr.hasNext())
										{
											String tempIds = (String) mpTempParasItr.next();
											if (tempId.equals(tempIds))
											{
												List lstParaIds = (List) mpTempParas.get(tempIds);
												int lst = lstParaIds.size();
												for (int i = 0; i < lst; i++)
												{
													Entry entry = (Entry) lstParaIds.get(i);
													if (paraId.equals(entry.getValue()))
													{
														ProfileClinicalDtlVO profileClinicalDtlVO = new ProfileClinicalDtlVO();
														profileClinicalDtlVO.setRecordDate(_visitDate);
														profileClinicalDtlVO.setTemplateId(tempId);
														profileClinicalDtlVO.setParaId(paraId);
														profileClinicalDtlVO.setParaValue(paraValue);
														profileClinicalDtlVO.setProfileId(profileId);
														profileClinicalDtlVO.setRecordView(_patProfileDtlVO.getReportMode());
														profileClinicalDtlVO.setDeskMenuId(deskMenuId);
														profileClinicalDtlDAOi.create(profileClinicalDtlVO, _userVO);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			// to insert data in profile OT detail
			List otList = (List) inAllMap.get("OTVIEWING");

			int _otList = otList.size();
			for (int i = 0; i < _otList; i++)
			{
				ProfileOTDetailVO _profileOTDetailVO = (ProfileOTDetailVO) otList.get(i);

				ProfileOTDtlVO _profileOTDtlVO = new ProfileOTDtlVO();
				HelperMethods.populate(_profileOTDtlVO, _profileOTDetailVO);
				_profileOTDtlVO.setProfileId(profileId);

				profileOTDetailDAO.create(_profileOTDtlVO, _userVO);
			}

			// to insert data in profile image detail
			List imageList = (List) inAllMap.get("IMAGEEXAMINATION");

			int _imageTypeList = imageList.size();
			for (int i = 0; i < _imageTypeList; i++)
			{
				OpdPatientImageDtlVO _patImageDtlVO = (OpdPatientImageDtlVO) imageList.get(i);

				ProfileImageDtlVO _profileImageDtlVO = new ProfileImageDtlVO();
				HelperMethods.populate(_profileImageDtlVO, _patImageDtlVO);
				_profileImageDtlVO.setProfileId(profileId);

				profileImageDtlDAOi.create(_profileImageDtlVO, _userVO);

			}

			// to insert data in profile Progress Notes Detail
			List lstProgressNotes = (List) inAllMap.get("DOCTORROUND");

			for (int i = 0; i < lstProgressNotes.size(); i++)
			{
				DoctorRoundDtlVO _progNotesVO = (DoctorRoundDtlVO) lstProgressNotes.get(i);

				ProfileProgressNotesDtlVO _profileProgNotesDtlVO = new ProfileProgressNotesDtlVO();
				HelperMethods.populate(_profileProgNotesDtlVO, _progNotesVO);
				_profileProgNotesDtlVO.setProfileId(profileId);

				profileProgNotesDtlDAOi.create(_profileProgNotesDtlVO, _userVO);

			}

			// to insert data in profile External Investifation detail
			List lstExtInv = (List) inAllMap.get("EXTERNALEXAMINATION");

			for (int i = 0; i < lstExtInv.size(); i++)
			{
				EpisodeExtInvDtlVO _extInvDtlVO = (EpisodeExtInvDtlVO) lstExtInv.get(i);

				ProfileExtExamDtlVO _profileExtInvDtlVO = new ProfileExtExamDtlVO();
				HelperMethods.populate(_profileExtInvDtlVO, _extInvDtlVO);
				_profileExtInvDtlVO.setProfileId(profileId);

				profileExtExamDtlDAOi.create(_profileExtInvDtlVO, _userVO);

			}
			
			
			
			// to insert data in profile  Chart View Detail
			List chartViewList = (List) inAllMap.get("CHARTDETAIL");
			if(chartViewList!=null)
			{
			int _chartViewList = chartViewList.size();
			for (int i = 0; i < _chartViewList; i++)
			{
				ProfileChartViewDtlVO _episodeChartViewDetailVO = (ProfileChartViewDtlVO) chartViewList.get(i);

				ProfileChartViewDtlVO _profileChartViewDtlVO = new ProfileChartViewDtlVO();
				HelperMethods.populate(_profileChartViewDtlVO, _episodeChartViewDetailVO);
				_profileChartViewDtlVO.setProfileId(profileId);

				profileChartViewDetailDAOi.create(_profileChartViewDtlVO, _userVO);

			}
			}
			
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
			
		}
		return profileId;
	}

	/* Not in Use */
	/*
	 * public PatProfileDtlVO[] getPreviousProfileDtlByCrNo(String _crNo, String _departmentUnitCode, UserVO _userVO) {
	 * 
	 * JDBCTransactionContext tx = new JDBCTransactionContext(); PatProfileDtlVO[] patProfileVOs = null;
	 * 
	 * try { PatProfileDtlDAOi patProfileDtlDAOi = new PatProfileDtlDAO(tx); tx.begin(); patProfileVOs =
	 * patProfileDtlDAOi.getProfileDtlByCrNo(_crNo, _departmentUnitCode, _userVO);
	 *  } catch (HisRecordNotFoundException e) { e.printStackTrace(); tx.rollback(); throw new
	 * HisRecordNotFoundException(e.getMessage()); } catch (HisApplicationExecutionException e) { e.printStackTrace();
	 * tx.rollback(); throw new HisApplicationExecutionException(e.getMessage()); } catch (HisDataAccessException e) {
	 * e.printStackTrace(); tx.rollback(); throw new HisDataAccessException(e.getMessage()); } catch (Exception e) {
	 * e.printStackTrace(); tx.rollback(); throw new HisException(e.getMessage()); } finally { tx.close(); } return
	 * patProfileVOs; }
	 */

	/**
	 * Modifying Patient Profile
	 * 
	 * @param _lstPatProfileDtlVO List of Patient Profile Detail
	 * @param _userVO User Detail
	 */
	public void modifyPatientProfile(List<PatientProfileDetailVO> _lstPatProfileDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			PatientProfileDetailDAOi daoPatProfile = new PatientProfileDetailDAO(tx);
			ProfileAccessDetailDAOi daoProfileAccess = new ProfileAccessDetailDAO(tx);

			tx.begin();
			for (PatientProfileDetailVO patProfileDtlVO : _lstPatProfileDtlVO)
			{
				daoPatProfile.updateOld(patProfileDtlVO, _userVO);
				daoPatProfile.create(patProfileDtlVO, _userVO);

				daoProfileAccess.delete(patProfileDtlVO.getProfileId(), _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Removing Patient Profile
	 * 
	 * @param _lstProfileDtlVO List of Patient Profile Detail
	 * @param _userVO User Detail
	 */
	public void removePatientProfile(List<PatientProfileDetailVO> _lstProfileDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			ProfileAccessDetailDAOi daoProfileAccess = new ProfileAccessDetailDAO(tx);

			tx.begin();

			for (PatientProfileDetailVO patProfileDtlVO : _lstProfileDtlVO)
			{
				dao.delete(patProfileDtlVO, _userVO);

				daoProfileAccess.delete(patProfileDtlVO.getProfileId(), _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	/*
	 * public Service_Req_dtlVO SaveServiceRequisition(Service_Req_dtlVO _service_Req_dtlVO, UserVO _userVO) {
	 * JDBCTransactionContext tx = new JDBCTransactionContext(); try { ServiceRequisitionDtlDAOi _serviceRequisitionDtlDAOi =
	 * new ServiceRequisitionDtlDAO(tx); tx.begin(); _service_Req_dtlVO =
	 * _serviceRequisitionDtlDAOi.SaveServiceRequisition(_service_Req_dtlVO, _userVO); } catch
	 * (HisApplicationExecutionException e) { e.printStackTrace(); tx.rollback(); throw new
	 * HisApplicationExecutionException(e.getMessage()); } catch (HisDataAccessException e) { e.printStackTrace();
	 * tx.rollback(); throw new HisDataAccessException(e.getMessage()); } catch (Exception e) { e.printStackTrace();
	 * tx.rollback(); throw new HisException(e.getMessage()); } finally { tx.close(); } return _service_Req_dtlVO; }
	 */

	public void saveBelongingDetails(PatientBelongingVO[] _patBelongingVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			PatietnBelongingDAO patBelongingDAO = new PatietnBelongingDAO(tx);
			tx.begin();
			for (int i = 0; i < _patBelongingVO.length; i++)
			{
				patBelongingDAO.create(_patBelongingVO[i], _userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void saveBelongingHandoverDetails(PatientBelongingVO[] _patBelongingVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			PatietnBelongingDAO patBelongingDAO = new PatietnBelongingDAO(tx);
			tx.begin();

			for (int i = 0; i < _patBelongingVO.length; i++)
			{
				patBelongingDAO.UpdateBelongingHandoverDetails(_patBelongingVO[i], _userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// * Modifying Belonging Detail
	public PatientBelongingVO[] modifyBelongingDetails(PatientBelongingVO _patBelongingVO, String _oldItemCode, String _collectionDate, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientBelongingVO[] patBelongingVO = null;
		try
		{
			PatietnBelongingDAO patBelongingDAO = new PatietnBelongingDAO(tx);
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			tx.begin();

			patBelongingDAO.modifyBelongingDetails(_patBelongingVO, _oldItemCode, _collectionDate, _userVO);

			patBelongingVO = opdEssentialDAO.getPatientBelongingDetails(_patBelongingVO.getPatCrNo(), _userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patBelongingVO;
	}

	/**
	 * Getting Episode Allergies Detail for Patient Profile
	 * 
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Episode Allery Detail VOs
	 */
	public PatAllergyDtlVO[] getPatientAllergiesDetail(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatAllergyDtlVO[] patAllergyDtlVO = null;
		try
		{
			tx.begin();
			PatAllergyDtlDAO patAllergyDtlDAO = new PatAllergyDtlDAO(tx);
			patAllergyDtlVO = patAllergyDtlDAO.getPatientAllergiesDetail(_crNo, _userVO, voDP, profileGenerationMode);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return patAllergyDtlVO;

	}

	/**
	 * Getting Episode Exam Images for Patient Profile
	 * 
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Patient Image Detail VOs
	 */
	public OpdPatientImageDtlVO[] getPatientProfileEpisodeExamImages(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		OpdPatientImageDtlVO[] arrayExamImagesVO = null;
		try
		{
			tx.begin();
			OpdPatientImageDtlDAO dao = new OpdPatientImageDtlDAO(tx);
			OpdPatientImageDtlVO vo = new OpdPatientImageDtlVO();
			voDP.setPatCrNo(_crNo);
			List<OpdPatientImageDtlVO> lst = dao.getOPDPatOldImagesList(_userVO, voDP, profileGenerationMode);
			arrayExamImagesVO = new OpdPatientImageDtlVO[lst.size()];
			int i = 0;
			for (OpdPatientImageDtlVO v : lst)
				arrayExamImagesVO[i++] = v;
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrayExamImagesVO;
	}

	/**
	 * Getting Episode External Investigation for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Patient External Investigation Detail VOs
	 */
	public EpisodeExtInvDtlVO[] getPatientProfileEpisodeExtInvestigation(String _crNo, PatientDetailVO voDP, String profileGenerationMode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeExtInvDtlVO[] arr = null;
		try
		{
			tx.begin();
			EpisodeExtInvDtlDAO dao = new EpisodeExtInvDtlDAO(tx);
			EpisodeExtInvDtlVO vo = new EpisodeExtInvDtlVO();
			arr = dao.getEpisodeExtInvList(_crNo, voDP, profileGenerationMode, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arr;
	}

	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWise(String selAllergyID, String selAllergyCode, DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		try
		{
			tx.begin();
			EpisodeAllergiesDAO episodeAlleryDAO = new EpisodeAllergiesDAO(tx);
			arrEpisodeAllergyDtlVO = episodeAlleryDAO.getAllergyDtlEpisodeWise(selAllergyID, selAllergyCode, _dailyPatientVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrEpisodeAllergyDtlVO;
	}
	
	
	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWiseNew(String selAllergyID,  DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		try
		{
			tx.begin();
			EpisodeAllergiesNewDAO episodeAlleryDAO = new EpisodeAllergiesNewDAO(tx);
			arrEpisodeAllergyDtlVO = episodeAlleryDAO.getAllergyDtlEpisodeWiseNew(selAllergyID,  _dailyPatientVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrEpisodeAllergyDtlVO;
	}
	
	
	public void revokeAllergyNew(EpisodeAllergiesVO epiAllergyVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// PatAllergyDtlVO patAllergyDtlVO=new PatAllergyDtlVO();

		try
		{
			EpisodeAllergiesNewDAO episodeAllergyDAO = new EpisodeAllergiesNewDAO(tx);
			PatAllergyDtlDAO patAllergyDtlDAO = new PatAllergyDtlDAO(tx);
			tx.begin();

			//patAllergyDtlDAO.updateAllergyId(epiAllergyVO, userVO);

			epiAllergyVO.setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_YES);
			episodeAllergyDAO.updateAllergyId(epiAllergyVO, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Saving Next Visit Detail
	 * 
	 * @param _episodeVO Episode VO
	 * @param _userVO UserVO
	 */
	public void saveNextVisitDetail(EpisodeVO _episodeVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO, String _deskType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeDAO dao = new EpisodeDAO(tx);
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			dao.updateNextVisitDetail(_episodeVO, _userVO);

			DailyPatientDAO patDao = new DailyPatientDAO(tx);
			patDao.updateIsconfirmed(_episodeVO.getPatCrNo(), _episodeVO.getQueNo(), _episodeVO.getEpisodeVisitNo(), _episodeVO.getEpisodeCode(),
					_episodeVO.getIsConfirmed(), _userVO);

			// ///only for CMO Desk////////
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_CMO_DESK))
			{
				_episodeVO.setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_CODE_ATTENDED_DISPOSED);
				_episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				_episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);

				dao.updateEpisodeDtlForCMO(_episodeVO, _userVO);
				_episodeActDtlVO.setComplainDtl(_episodeVO.getComplainDetail());
				_episodeActDtlVO.setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_CODE_ATTENDED_DISPOSED);
				episodeActionDtlDAO.create(_episodeActDtlVO, _userVO);
			}

			// ////////////////////////////
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Retrieving Episode Detail
	 * 
	 * @param _episodeVO
	 * @param _userVO
	 * @return Episode VO
	 */
	public EpisodeVO retrieveEpisodeDetail(EpisodeVO _episodeVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO vo = null;
		try
		{
			tx.begin();
			EpisodeDAO dao = new EpisodeDAO(tx);
			vo = dao.retrieveByEpisodeVisit(_episodeVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;
	}

	// update consent status
	public void updateStatus(List consentRequestVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EpisodeConsentDtlDAO episodeConsentDtlDAO = new EpisodeConsentDtlDAO(tx);
			for (int i = 0; i < consentRequestVO.size(); i++)
			{
				ConsentRequestVO vo = (ConsentRequestVO) consentRequestVO.get(i);
				episodeConsentDtlDAO.updateStatus(vo, _userVO);
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return;
	}

	public String getNoOfNewConsentRequest(ConsentRequestVO _consentVO, String hospitalCode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count;
		try
		{
			EpisodeConsentDtlDAO episodeConsentDtlDAO = new EpisodeConsentDtlDAO(tx);
			tx.begin();
			count = episodeConsentDtlDAO.getNoOfNewConsentRequest(_consentVO, hospitalCode);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return count;

	}

	/**
	 * Getting Patient-Centric & Episode-Centric Clinical Template Data
	 * 
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by List of Entry Object with paraId/Value at 0 Patient-Centric Data
	 */
	public Map<String, List<Entry>> getPatientClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		Map mpTempParaVals = null;
		List lsPatCentricVals = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientClinicalDetailDAOi dao = new PatientClinicalDetailDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK)) mpTempParaVals = dao
					.getPatientEpisodeClinicalData(_patClinicalVO, _userVO);

			// lsPatCentricVals = dao.getPatientCentricClinicalData(_patClinicalVO,_userVO);
			mpTempParaVals.put("0", lsPatCentricVals);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}

	/**
	 * Getting Patien Clinical Template Data
	 * 
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by Map of paraId/Value
	 */
	public Map<String, Map<String, String>> getPatientFinalClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		Map<String, Map<String, String>> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientClinicalDetailDAOi dao = new PatientClinicalDetailDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK)) mpTempParaVals = dao.getPatientClinicalData(_patClinicalVO, _userVO);
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_BAY_DESK)) mpTempParaVals = dao.getPatientClinicalData(_patClinicalVO, _userVO); //Added by Vasu on 26.Feb.2019 to get Template Essentials at OPD Bay DESK
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK)) mpTempParaVals = dao.getPatientClinicalData(_patClinicalVO, _userVO);//dao.getPatientCentricClinicalData(_patClinicalVO,	_userVO);
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK)) mpTempParaVals = dao.getPatientCentricClinicalData(
					_patClinicalVO, _userVO);
			else if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) mpTempParaVals = dao.getPatientCentricClinicalData(
					_patClinicalVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}

	/**
	 * Saving Patient Clinical Data
	 * 
	 * @param _deskType Desk Type
	 * @param _lstPatCliDtl List of PatientClinicalDetailVO to save
	 * @param _userVO User VO
	 */
	public void savePatientClinicalDetail(String _deskType, List<PatientClinicalDetailVO> _lstPatCliDtl, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientClinicalDetailDAO dao = new PatientClinicalDetailDAO(tx);
			for (PatientClinicalDetailVO vo : _lstPatCliDtl)
				dao.savePtientClinicalDetail(_deskType, vo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Get All Template Details
	 * 
	 * @param _lstTemps list of Entry object in tempId/tempName
	 * @param _userVO User VO
	 * @return Map of tempId by tempMst VO
	 */
	public Map<String, TemplateMasterVO> getAllTemplateDetails(List<Entry> _lstTemps, UserVO _userVO)
	{
		Map<String, TemplateMasterVO> mapTempDtl = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			TemplateMasterDAO dao = new TemplateMasterDAO(tx);
			mapTempDtl = new HashMap<String, TemplateMasterVO>();
			for (Entry e : _lstTemps)
			{
				TemplateMasterVO vo = new TemplateMasterVO();
				vo.setTemplateId(e.getValue());
				vo = dao.getTemplateDataById(vo, _userVO);
				mapTempDtl.put(e.getValue(), vo);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mapTempDtl;
	}

	/**
	 * Get All Template Parameters Detail
	 * 
	 * @param _lstTemps list of Entry object in tempId/tempName
	 * @param _userVO User VO
	 * @return List of all Template Parameters Detail
	 */
	public List<TemplateParameterMasterVO> getAllTemplateParametersDetail(List<Entry> _lstTemps, UserVO _userVO)
	{
		List<TemplateParameterMasterVO> lstTempPara = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			TemplateParameterMasterDAO dao = new TemplateParameterMasterDAO(tx);
			lstTempPara = dao.getAllTempsParasList(_lstTemps, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstTempPara;
	}

	/**
	 * Getting Report Date List
	 * 
	 * @param _deskType Desk Type
	 * @param _patCliDtlVO Patient Detail Data
	 * @param _userVO User VO
	 * @return list of Entry object as Visit Date by Detail to Display
	 */
	public List<Entry> getReportDateList(String _deskType, PatientClinicalDetailVO _patCliDtlVO, UserVO _userVO)
	{
		List<Entry> lstReportDates = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi daoOpdEss = new OpdEssentialDAO(tx);
			PatientClinicalDetailDAOi daoPatCliDtl = new PatientClinicalDetailDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) lstReportDates = daoPatCliDtl
					.getClinicalRecordDateList(_patCliDtlVO.getAdmissionNo(), _patCliDtlVO.getDeskMenuId(), _userVO);  //added deskmenu by manisha gangwar 
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK)) 
					lstReportDates = daoOpdEss.getPrevVisitReportDates(_patCliDtlVO.getPatCrNo(), _patCliDtlVO.getEpisodeCode(), _patCliDtlVO.getDeskMenuId(),_userVO);  ////added deskmenu  by manisha gangwar 
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstReportDates;
	}

	/**
	 * Getting Patient Chart Clinical Template Data
	 * 
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of paraId/Value
	 */
	public Map<String, Map<String, Map<String, String>>> getPatientChartClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO,
			List<Entry> _lstReportDates, UserVO _userVO)
	{
		Map<String, Map<String, Map<String, String>>> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientClinicalDetailDAOi dao = new PatientClinicalDetailDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, String>>>();
				for (Entry e : _lstReportDates)
				{
					PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setRecordDate(e.getValue());
					Map<String, Map<String, String>> map = dao.getPatientClinicalRecordData(patCliVO, _userVO);
					mpTempParaVals.put(e.getValue(), map);
				}
			}
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, String>>>();
				for (Entry e : _lstReportDates)
				{
					PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setEpisodeVisitNo(e.getValue());
					Map<String, Map<String, String>> map = dao.getPatientEpisodeClinicalData(patCliVO, _userVO);
					mpTempParaVals.put(e.getValue(), map);
				}
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}

	/**
	 * Getting Patient Chart Clinical Template Data
	 * 
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _lstTemps
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of paraId/Value
	 */
	public Map<String, Map<String, Map<String, String>>> getPatientChartClinicalDataTempWise(String _deskType,
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
	{
		Map<String, Map<String, Map<String, String>>> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientClinicalDetailDAOi dao = new PatientClinicalDetailDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, String>>>();
				for (Entry e : _lstReportDates)
				{
					PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setRecordDate(e.getValue());

					GenericTemplateUtility.TempParameter[] tempParas = dao.getPatientClinicalRecordDataTempWise(patCliVO, _lstTemps, _userVO);

					Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
					for (GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, String> mpTemporary = null;
						if (map.get(paraValVO.getTemplateId()) != null) mpTemporary = map.get(paraValVO.getTemplateId());
						else mpTemporary = new HashMap<String, String>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO.getParaValue());
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}

					mpTempParaVals.put(e.getValue(), map);
				}
			}
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, String>>>();
				for (Entry e : _lstReportDates)
				{
					PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setEpisodeVisitNo(e.getValue());

					GenericTemplateUtility.TempParameter[] tempParas = dao.getPatientEpisodeClinicalDataTempWise(patCliVO, _lstTemps, _userVO);
					Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

					for (GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, String> mpTemporary = null;
						if (map.get(paraValVO.getTemplateId()) != null) mpTemporary = map.get(paraValVO.getTemplateId());
						else mpTemporary = new HashMap<String, String>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO.getParaValue());
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}

					mpTempParaVals.put(e.getLabel(), map);
				}
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}

	/**
	 * Getting Patient Chart Clinical Template Data for Pateint Profiles
	 * 
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _lstTemps
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of paraId/Value
	 */
	public Map<String, Map<String, Map<String, String>>> getPatientChartClinicalDataTempWiseforProfile(String _deskType, String profileGenerationMode, 
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstTemps, UserVO _userVO)
	{
		Map<String, Map<String, Map<String, String>>> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientClinicalDetailDAO dao = new PatientClinicalDetailDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				mpTempParaVals = dao.getPatientClinicalDataDateWiseforProfile(_patClinicalVO, false, _userVO);
			}
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				mpTempParaVals = dao.getPatientClinicalDataDateWiseforProfile(_patClinicalVO, true, _userVO);
				if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
				{
					Map<String, Map<String, Map<String, String>>> mpTemp = mpTempParaVals;
					mpTempParaVals = new HashMap<String, Map<String,Map<String,String>>>();
					mpTempParaVals.put(_patClinicalVO.getEpisodeVisitNo(), mpTemp.get(_patClinicalVO.getEpisodeVisitNo()));
				}
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}

	
	
	/**
	 * Getting Patient Chart Clinical Template Data
	 * 
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _lstTemps
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of paraId/GenericTemplateUtility.TempParameter Object
	 */
	public Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> getPatientChartClinicalDataTempWiseWithParaDtl(
			String _deskType, PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
	{
		Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientClinicalDetailDAOi dao = new PatientClinicalDetailDAO(tx);
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>>();
				for (Entry e : _lstReportDates)
				{
					PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setRecordDate(e.getValue());

					GenericTemplateUtility.TempParameter[] tempParas = dao.getPatientClinicalRecordDataTempWise(patCliVO, _lstTemps, _userVO);

					Map<String, Map<String, GenericTemplateUtility.TempParameter>> map = new HashMap<String, Map<String, GenericTemplateUtility.TempParameter>>();
					for (GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, GenericTemplateUtility.TempParameter> mpTemporary = null;
						if (map.get(paraValVO.getTemplateId()) != null) mpTemporary = map.get(paraValVO.getTemplateId());
						else mpTemporary = new HashMap<String, GenericTemplateUtility.TempParameter>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO);
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}

					mpTempParaVals.put(e.getValue(), map);
				}
			}
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				mpTempParaVals = new HashMap<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>>();
				for (Entry e : _lstReportDates)
				{
					PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
					HelperMethods.populate(patCliVO, _patClinicalVO);
					patCliVO.setEpisodeVisitNo(e.getValue());

					// Map<String, Map<String, String>> map = dao.getPatientEpisodeClinicalDataTempWise(patCliVO, _lstTemps,
					// _userVO);
					GenericTemplateUtility.TempParameter[] tempParas = dao.getPatientEpisodeClinicalDataTempWise(patCliVO, _lstTemps, _userVO);
					Map<String, Map<String, GenericTemplateUtility.TempParameter>> map = new HashMap<String, Map<String, GenericTemplateUtility.TempParameter>>();

					for (GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, GenericTemplateUtility.TempParameter> mpTemporary = null;
						if (map.get(paraValVO.getTemplateId()) != null) mpTemporary = map.get(paraValVO.getTemplateId());
						else mpTemporary = new HashMap<String, GenericTemplateUtility.TempParameter>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO);
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}

					mpTempParaVals.put(e.getValue(), map);
				}
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpTempParaVals;
	}

	/**
	 * Getting Patient Episode Profiles List
	 * 
	 * @param _patProfileVO Patient Profile Detail VO
	 * @param _userVO User VO
	 * @return List of Patient Profile VO of Previous Profiles
	 */
	public Map<String, Object> getPatientProfileEssentails(PatientProfileDetailVO _patProfileVO, String _deskType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientProfileDetailVO> lstPatProfiles = null;
		List<ProfileTypeMstVO> profileTypeVOList = null;
		String profileBound = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			OpdEssentialDAOi essentialDAOi = new OpdEssentialDAO(tx);
			ProfileTypeDAO profileTypeDao = new ProfileTypeDAO(tx);
			tx.begin();
			lstPatProfiles = dao.getEpisodePatientProfiles(_patProfileVO, _userVO);
			profileBound = essentialDAOi.getProfileBound(_deskType, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstPatProfiles);
			map.put(OpdConfig.OPD_DESK_PROFILE_BOUND, profileBound);
			String usablity = OpdConfig.PROFILE_TYPE_USABLITY_OPD;
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) usablity = OpdConfig.PROFILE_TYPE_USABLITY_IPD;
			profileTypeVOList = profileTypeDao.getProfileTypes(usablity, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED, _userVO);
			map.put(OpdConfig.PROFILE_TYPE_VO_LIST, profileTypeVOList);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return map;
	}

	/**
	 * Saving Patient Treatment Detail
	 * 
	 * @param _lstDrugDtl List of Patient Drug Treatment Detail
	 * @param _userVO User VO
	 */
	public void savePatTreatmentDetail(List<PatDrugTreatmentDetailVO> _lstDrugDtl, List<PatExtTreatmentDetailVO> _lstExtDtl,
			PatDietAdviceDetailVO _patDietDetailVO, Map drugScheduleMap, RestAdviceDtlVO restAdviceDtlVO, List drugAdminLst, List extAdminList,
			UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatDrugTreatmentDetailDAOi dao = new PatDrugTreatmentDetailDAO(tx);
			PatExtTreatmentDtlDAOi extDao = new PatExtTreatmentDtlDAO(tx);
			PatDietAdviceDetailDAOi dietDao = new PatDietAdviceDetailDAO(tx);
			RestAdviceDtlDAOi restAdviceDtlDAO = new RestAdviceDtlDAO(tx);
			DrugScheduleDtlDAO drugScheduleDtlDAO = new DrugScheduleDtlDAO(tx);
			DrugAdminDtlDAOi drugAdminDtlDAO = new DrugAdminDtlDAO(tx);
			ExtAdminDtlDAOi extAdminDtlDAO = new ExtAdminDtlDAO(tx);

			String maxSlNoTreat = "";
			Integer maxSlNoTreatInt = 0;

			if (_lstDrugDtl != null && _lstDrugDtl.size() != 0)
			{
				PatDrugTreatmentDetailVO patTreatVO = (PatDrugTreatmentDetailVO) _lstDrugDtl.get(0);

				maxSlNoTreat = dao.getMaxSlNo(patTreatVO, _userVO);
				maxSlNoTreatInt = new Integer(maxSlNoTreat);

				// DrugSheduleDtlVO sheduleDtlVO=new DrugSheduleDtlVO();
				// sheduleDtlVO.setPatCrNo(patTreatVO.getPatCrNo());
				// sheduleDtlVO.setEpisodeCode(patTreatVO.getEpisodeCode());
				// sheduleDtlVO.setEpisodeVisitNo(patTreatVO.getEpisodeVisitNo());
				// drugScheduleDtlDAO.updateSchedule(sheduleDtlVO, _userVO);
				//				
				// drugScheduleDtlDAO.updateSchedule(sheduleDtlVO, _userVO);
			}

			for (PatDrugTreatmentDetailVO vo : _lstDrugDtl)
			{

				if (vo.getRxContinue().equals(OpdConfig.REVOKE))
				{

					if (vo.getTodayVisitFlag().equals("yes"))
					{
						dao.updateLastTodayVisitRecord(vo, _userVO);
					}
					dao.saveRevokePatDrugTreatmentDetail(vo, _userVO);

					String mapKey = vo.getDrugId() + "#" + vo.getFrequencyId() + "#" + vo.getDoseId();
					List scheduleLst = (List) drugScheduleMap.get(mapKey);
					drugScheduleMap.remove(mapKey);
					if (scheduleLst != null)
					{
						for (int j = 0; j < scheduleLst.size(); j++)
						{
							DrugSheduleDtlVO drugSheduleDtlVO = (DrugSheduleDtlVO) scheduleLst.get(j);

							drugSheduleDtlVO.setSerialNo(maxSlNoTreatInt.toString());
							drugSheduleDtlVO.setSheduleSerialNo(new Integer(j).toString());
							drugSheduleDtlVO.setEpisodeCode(vo.getEpisodeCode());
							drugSheduleDtlVO.setEpisodeVisitNo(vo.getEpisodeVisitNo());
							drugSheduleDtlVO.setPatCrNo(vo.getPatCrNo());

							// drugScheduleDtlDAO.save(drugSheduleDtlVO, _userVO);

						}
					}

					maxSlNoTreatInt++;
				}
				/*
				 * //this is for RxExtension if(vo.getRxContinue().equals(OpdConfig.RX_EXTENSION)) {
				 * 
				 * if(vo.getTodayVisitFlag().equals("yes")) { dao.updateLastTodayVisitRecord(vo, _userVO); }
				 * dao.saveRevokePatDrugTreatmentDetail(vo, _userVO);
				 * 
				 * String mapKey=vo.getDrugId()+"#"+vo.getFrequencyId()+"#"+vo.getDoseId(); List
				 * scheduleLst=(List)drugScheduleMap.get(mapKey); drugScheduleMap.remove(mapKey); if(scheduleLst!=null) {
				 * for(int j=0;j<scheduleLst.size();j++) { DrugSheduleDtlVO
				 * drugSheduleDtlVO=(DrugSheduleDtlVO)scheduleLst.get(j);
				 * 
				 * drugSheduleDtlVO.setSerialNo(maxSlNoTreatInt.toString()); drugSheduleDtlVO.setSheduleSerialNo(new
				 * Integer(j).toString()); drugSheduleDtlVO.setEpisodeCode(vo.getEpisodeCode());
				 * drugSheduleDtlVO.setEpisodeVisitNo(vo.getEpisodeVisitNo()); drugSheduleDtlVO.setPatCrNo(vo.getPatCrNo());
				 * 
				 * drugScheduleDtlDAO.save(drugSheduleDtlVO, _userVO);
				 *  } }
				 * 
				 * maxSlNoTreatInt++; }
				 */
				if (vo.getRxContinue().equals(OpdConfig.NEW_RECORD))
				{
					String consentStatus = dao.getConsentStatus(vo, _userVO);
					if (!consentStatus.equals(OpdConfig.CONSNET_REQUEST_STATUS))
					{
						dao.generatConsent(vo, _userVO);
					}

					vo.setRequirmentTime(vo.getSysDate());
					dao.savePatDrugTreatmentDetail(vo, _userVO);
					dao.savePatDrugTreatmentDetailForPHRMS(vo, _userVO); //Added by Vasu on 27.03.2019

					String mapKey = vo.getDrugId() + "#" + vo.getFrequencyId() + "#" + vo.getDoseId();
					List scheduleLst = (List) drugScheduleMap.get(mapKey);
					drugScheduleMap.remove(mapKey);
					if (scheduleLst != null)
					{
						for (int j = 0; j < scheduleLst.size(); j++)
						{
							DrugSheduleDtlVO drugSheduleDtlVO = (DrugSheduleDtlVO) scheduleLst.get(j);

							drugSheduleDtlVO.setSerialNo(maxSlNoTreatInt.toString());
							drugSheduleDtlVO.setSheduleSerialNo(new Integer(j).toString());
							drugSheduleDtlVO.setEpisodeCode(vo.getEpisodeCode());
							drugSheduleDtlVO.setEpisodeVisitNo(vo.getEpisodeVisitNo());
							drugSheduleDtlVO.setPatCrNo(vo.getPatCrNo());

							drugScheduleDtlDAO.save(drugSheduleDtlVO, _userVO);

						}
					}

					maxSlNoTreatInt++;
				}
				if (vo.getRxContinue().equals(OpdConfig.CHANGE_IN_PREV))
				{
					if (vo.getTodayVisitFlag().equals("yes"))
					{
						dao.updateLastTodayVisitRecord(vo, _userVO);
					}
					vo.setRequirmentTime(vo.getSysDate());
					dao.savePatDrugTreatmentDetail(vo, _userVO);
					// dao.saveRevokePatDrugTreatmentDetail(vo, _userVO);

					String mapKey = vo.getDrugId() + "#" + vo.getFrequencyId() + "#" + vo.getDoseId();
					List scheduleLst = (List) drugScheduleMap.get(mapKey);
					drugScheduleMap.remove(mapKey);
					if (scheduleLst != null)
					{
						for (int j = 0; j < scheduleLst.size(); j++)
						{
							DrugSheduleDtlVO drugSheduleDtlVO = (DrugSheduleDtlVO) scheduleLst.get(j);

							drugSheduleDtlVO.setSerialNo(maxSlNoTreatInt.toString());
							drugSheduleDtlVO.setSheduleSerialNo(new Integer(j).toString());
							drugSheduleDtlVO.setEpisodeCode(vo.getEpisodeCode());
							drugSheduleDtlVO.setEpisodeVisitNo(vo.getEpisodeVisitNo());
							drugSheduleDtlVO.setPatCrNo(vo.getPatCrNo());

							drugScheduleDtlDAO.save(drugSheduleDtlVO, _userVO);

						}
					}

					maxSlNoTreatInt++;
				}

				if (vo.getRxContinue().equals(OpdConfig.SIMPLE_RX))
				{
					if (vo.getTodayVisitFlag().equals("yes"))
					{
						dao.updateLastTodayVisitRecord(vo, _userVO);
					}
					dao.saveRevokePatDrugTreatmentDetail(vo, _userVO);

					String mapKey = vo.getDrugId() + "#" + vo.getFrequencyId() + "#" + vo.getDoseId();
					List scheduleLst = (List) drugScheduleMap.get(mapKey);
					drugScheduleMap.remove(mapKey);
					if (scheduleLst != null)
					{
						for (int j = 0; j < scheduleLst.size(); j++)
						{
							DrugSheduleDtlVO drugSheduleDtlVO = (DrugSheduleDtlVO) scheduleLst.get(j);

							drugSheduleDtlVO.setSerialNo(maxSlNoTreatInt.toString());
							drugSheduleDtlVO.setSheduleSerialNo(new Integer(j).toString());
							drugSheduleDtlVO.setEpisodeCode(vo.getEpisodeCode());
							drugSheduleDtlVO.setEpisodeVisitNo(vo.getEpisodeVisitNo());
							drugSheduleDtlVO.setPatCrNo(vo.getPatCrNo());

							drugScheduleDtlDAO.save(drugSheduleDtlVO, _userVO);

						}
					}

					maxSlNoTreatInt++;
				}

			}

			// saving rest advice
			if (restAdviceDtlVO.getDays() != null)
			{
				if (restAdviceDtlVO.getSerialNo().equals(""))
				{
					restAdviceDtlDAO.savePatRestAdviceTreatmentDetail(restAdviceDtlVO, _userVO);
				}
				else
				{
					restAdviceDtlDAO.updatePatRestAdviceDetail(restAdviceDtlVO, _userVO);
					restAdviceDtlDAO.savePatRestAdviceTreatmentDetail(restAdviceDtlVO, _userVO);
				}
			}

			// saving External Treatment
			if (_lstExtDtl.size() != 0 || _lstExtDtl != null)
			{

				for (PatExtTreatmentDetailVO vo : _lstExtDtl)
				{
					extDao.updateLastTodayVisitRecord(vo, _userVO);
					if (vo.getTodayVisitFlag().equals("yes"))
					{
						// extDao.updateLastTodayVisitRecord(vo, _userVO);
					}
				}

				for (PatExtTreatmentDetailVO vo : _lstExtDtl)
				{

					if (vo.getRxContinue().equals(OpdConfig.REVOKE))
					{
						/*
						 * if(vo.getTodayVisitFlag().equals("yes")) { extDao.updateLastTodayVisitRecord(vo, _userVO); }
						 */
						extDao.saveRxandRevoke(vo, _userVO);
						// extDao.updateRevoke(vo,_userVO);
					}
					if (vo.getRxContinue().equals(OpdConfig.NEW_RECORD))
					{
						extDao.savePatExtTreatmentDetail(vo, _userVO);
					}
					if (vo.getRxContinue().equals(OpdConfig.SIMPLE_RX))
					{
						/*
						 * if(vo.getTodayVisitFlag().equals("yes")) { extDao.updateLastTodayVisitRecord(vo, _userVO); }
						 */
						extDao.saveRxandRevoke(vo, _userVO);
					}

				}
			}
			// saving diet Advice
			if (_patDietDetailVO.getDays() != null)
			{
				if (_patDietDetailVO.getSerialNo().equals(""))
				{
					dietDao.savePatDietAdviceDetail(_patDietDetailVO, _userVO);
				}
				else
				{
					dietDao.updatePatDietAdviceDetail(_patDietDetailVO, _userVO);
					dietDao.savePatDietAdviceDetail(_patDietDetailVO, _userVO);
				}
			}

			// update drug administration

			for (int i = 0; i < _lstDrugDtl.size(); i++)
			{
				PatDrugTreatmentDetailVO treatVo = _lstDrugDtl.get(i);

				DrugAdminDtlVO vo = new DrugAdminDtlVO();

				vo.setPatCrNo(treatVo.getPatCrNo());
				vo.setAdviceDate(treatVo.getEntryDate());
				vo.setItemId(treatVo.getDrugId());
				vo.setPatAdmNo(treatVo.getAdmissionNo());
				vo.setAdminFlag(OpdConfig.SCHEDULE);
				vo.setDrugBrandName(treatVo.getDrugBrandId());
				vo.setBeforeTimeLimit(treatVo.getCutOffBefore());
				vo.setAfterTimeLimit(treatVo.getCutOffAfter());

				drugAdminDtlDAO.updateDrugAdminDetail(vo, _userVO);
			}

			// saving administration detail
			for (int i = 0; i < drugAdminLst.size(); i++)
			{
				DrugAdminDtlVO vo = (DrugAdminDtlVO) drugAdminLst.get(i);
				int days = 0;
				if (vo.getEndDate()==null || vo.getEndDate().equals(""))
				{
					days = Integer.parseInt(vo.getNoOfDays());
				}
				else
				{
					DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
					Calendar calendar1 = Calendar.getInstance();
					Calendar calendar2 = Calendar.getInstance();
					calendar1.setTime(sinmpledf.parse(vo.getSysdate()));
					calendar2.setTime(sinmpledf.parse(vo.getEndDate()));
					long noOfDays = ((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000)) + 1;
					String advDays = String.valueOf(noOfDays);
					days = Integer.parseInt(advDays);
				}

				// Integer noOfDays=Integer.parseInt(vo.getNoOfDays());
				for (int j = 0; j < days; j++)
				{
					// setting schedule date
					String startdate = vo.getStartDate();
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
					
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(startdate));
					c.add(Calendar.DATE, j);
					String actualStartDate = sdf.format(c.getTime());
					vo.setScheduleDate(actualStartDate);
					String[] doseTime=vo.getDoseTime().split(":");
					if(vo.getBeforeTimeLimit()!=null)
					{
					String[] cutOffBefore=vo.getBeforeTimeLimit().split(":");
					String[] cutOffAfter=vo.getAfterTimeLimit().split(":");
					String doseBefHr,doseBefMin,doseAftHr,doseAftMin,doseTimeBefore,doseTimeAfter;
					int doseTimeBefHr=Integer.parseInt(doseTime[0])-Integer.parseInt(cutOffBefore[0]);
					int dosetimeBefMin=Integer.parseInt(doseTime[1])-Integer.parseInt(cutOffBefore[1]);
					int doseTimeAftHr=Integer.parseInt(doseTime[0])+Integer.parseInt(cutOffAfter[0]);
					int dosetimeAftMin=Integer.parseInt(doseTime[1])+Integer.parseInt(cutOffAfter[1]);
					if(doseTimeBefHr<10)
						doseBefHr= "0"+Integer.toString(doseTimeBefHr);
					else
						doseBefHr=Integer.toString(doseTimeBefHr);
					if(dosetimeBefMin<10)
						doseBefMin= "0"+Integer.toString(dosetimeBefMin);
					else
						doseBefMin=Integer.toString(dosetimeBefMin);
					doseTimeBefore = doseBefHr+":"+doseBefMin;
					if(doseTimeAftHr<10)
						doseAftHr= "0"+Integer.toString(doseTimeAftHr);
					else
						doseAftHr=Integer.toString(doseTimeAftHr);
					if(dosetimeAftMin<10)
						doseAftMin= "0"+Integer.toString(dosetimeAftMin);
					else
						doseAftMin=Integer.toString(dosetimeAftMin);
					doseTimeAfter = doseAftHr+":"+doseAftMin;
					c.setTime(sdfDateTime.parse(actualStartDate+" "+doseTimeAfter));
					}
					Calendar cDatTime = Calendar.getInstance();
					cDatTime.setTime(sdfDateTime.parse(vo.getAdviceDate()));
					
					if(!c.before(cDatTime))
					{
						String drugStatus = drugAdminDtlDAO.getdrugStatus(vo, _userVO);
						if (drugStatus.equals("0"))
						{
							drugAdminDtlDAO.save(vo, _userVO);
						}
					}

				}
			}

			// update extAdminDtl
			for (int i = 0; i < extAdminList.size(); i++)
			{
				ExtAdminDtlVO vo = (ExtAdminDtlVO) extAdminList.get(i);
				extAdminDtlDAO.updateAllNotExtDetail(vo, _userVO);
			}

			// saving extAdmin Detail
			for (int i = 0; i < extAdminList.size(); i++)
			{
				ExtAdminDtlVO vo = (ExtAdminDtlVO) extAdminList.get(i);
				Integer noOfDays = Integer.parseInt(vo.getDays());
				for (int j = 0; j < noOfDays; j++)
				{
					// setting schedule date
					String startdate = vo.getStartDate();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(startdate));
					c.add(Calendar.DATE, j);
					String actualStartDate = sdf.format(c.getTime());

					vo.setScheduleDate(actualStartDate);

					String extTreatStatus = extAdminDtlDAO.getExtTreatmentStatus(vo, _userVO);
					if (extTreatStatus.equals("0"))
					{
						if (!vo.getRevokeStatus().equals("revoke"))
						{
							extAdminDtlDAO.save(vo, _userVO);
						}

					}

				}

			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public String getDrugAllergyAllerts(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status;
		try
		{
			PatDrugTreatmentDetailDAOi dao = new PatDrugTreatmentDetailDAO(tx);
			tx.begin();
			status = dao.getDrugAllergyAllerts(patDrugDtlVO, _userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return status;

	}

	public String getPrevDrugStatus(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status;
		try
		{
			PatDrugTreatmentDetailDAOi dao = new PatDrugTreatmentDetailDAO(tx);
			tx.begin();
			status = dao.getPrevDrugStatus(patDrugDtlVO, _userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return status;

	}

	public String getdrugReactionStatus(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status;
		try
		{
			DrugReactionDtlDAOi dao = new DrugReactionDAO(tx);
			tx.begin();
			status = dao.getdrugReactionStatus(patDrugDtlVO, _userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return status;

	}
	
	public String getdrugAdviceAlerts(PatDrugTreatmentDetailVO patDrugDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status;
		HisDAO hisDAO = new HisDAO("OPD", "OpdEssentialBO");
		try
		{
			String pmode="2";
			
			DrugReactionDtlDAOi dao = new DrugReactionDAO(tx);
			tx.begin();
			status = dao.getdrugAdviceAlerts(pmode,hisDAO,patDrugDtlVO, _userVO);
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			if(hisDAO!=null){
				hisDAO.free();
			}
			hisDAO=null;
			tx.close();
		}
		return status;

	}

	public void revokeDiagnosis(String revokeCode, EpisodeDiagnosisVO episodeDiaVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			episodeDiagnosisDAO.revokeDiagnosis(revokeCode, episodeDiaVO, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Getting All The Assigned Alerts of the Patient
	public List<PatientAlertsDetailVO> getPatientAssignedAlert(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientAlertsDetailVO> lstAssignedPatAlertVO = null;

		try
		{
			tx.begin();
			PatAlertsDetailDAOi patAlertDAO = new PatAlertsDetailDAO(tx);
			lstAssignedPatAlertVO = patAlertDAO.getPatientAssignedAlert(crNo, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstAssignedPatAlertVO;
	}

	// Saving the Patient Alerts & Revoking
	public void savePatientAlerts(List<PatientAlertsDetailVO> lstAddedPatAlerts, List<PatientAlertsDetailVO> lstRevokedPatAlerts, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeAlertDtlVO epiAlertDtlVO = new EpisodeAlertDtlVO();

		try
		{
			tx.begin();
			PatAlertsDetailDAOi patAlertDAO = new PatAlertsDetailDAO(tx);
			EpisodeAlertDtlDAOi epiAlertDAO = new EpisodeAlertDtlDAO(tx);

			// Revoking Patient Alerts
			for (PatientAlertsDetailVO vo : lstRevokedPatAlerts)
			{
				patAlertDAO.updateAlert(vo, userVO); // Updating the Row

				HelperMethods.populate(epiAlertDtlVO, vo);
				epiAlertDtlVO.setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_YES);
				epiAlertDAO.create(epiAlertDtlVO, userVO); // Inserting a New Row for Patient Alert Revoking
			}

			// Adding Patient Alerts
			for (PatientAlertsDetailVO vo : lstAddedPatAlerts)
			{
				patAlertDAO.create(vo, userVO);

				HelperMethods.populate(epiAlertDtlVO, vo);
				epiAlertDtlVO.setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_NO);
				epiAlertDAO.create(epiAlertDtlVO, userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Revoking the Alert of the Patient
	public void revokeAlerts(PatientAlertsDetailVO patAlertDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeAlertDtlVO epiAlertDtlVO = new EpisodeAlertDtlVO();

		try
		{
			tx.begin();
			PatAlertsDetailDAOi patAlertDAO = new PatAlertsDetailDAO(tx);
			EpisodeAlertDtlDAOi epiAlertDAO = new EpisodeAlertDtlDAO(tx);
			patAlertDAO.updateAlert(patAlertDetailVO, userVO); // Updating the Row

			HelperMethods.populate(epiAlertDtlVO, patAlertDetailVO);
			epiAlertDtlVO.setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_YES);
			epiAlertDAO.create(epiAlertDtlVO, userVO); // Inserting a New Row for Patient Alert Revoking
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Getting All The Alert of The patient
	public PatientAlertsDetailVO[] getAllPatientAlert(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientAlertsDetailVO[] arrAllPatAlertVO = null;

		try
		{
			tx.begin();
			PatAlertsDetailDAOi patAlertDAO = new PatAlertsDetailDAO(tx);
			arrAllPatAlertVO = patAlertDAO.getAllPatientAlert(crNo, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrAllPatAlertVO;
	}

	/**
	 * Saving Patient Profile Access Priviledges
	 * 
	 * @param _lstProfileAccessDtlVO List of Patient Profile Access Detail
	 * @param _userVO User VO
	 */
	public void saveProfileAccessPriviledges(List<ProfileAccessDetailVO> _lstProfileAccessDtlVO, PatientProfileDetailVO _patientProfileDtlVO,
			UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			ProfileAccessDetailDAOi dao = new ProfileAccessDetailDAO(tx);
			PatientProfileDetailDAOi patientProfileDtlDAO = new PatientProfileDetailDAO(tx);
			tx.begin();

			// if(_patientProfileDtlVO.getAccessTypeFlag().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
			// {
			patientProfileDtlDAO.updateIsValid(_patientProfileDtlVO, _userVO);
			patientProfileDtlDAO.create(_patientProfileDtlVO, _userVO);
			// }

			// patientProfileDtlDAO

			dao.delete(_patientProfileDtlVO.getProfileId(), _userVO);
			for (ProfileAccessDetailVO vo : _lstProfileAccessDtlVO)
			{
				dao.create(vo, _userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Getting Patient Episode Profiles List For Inbox
	 * 
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<ConsultationProfileDtlVO> getPatientProfilesForInbox(String _mailRequestId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<ConsultationProfileDtlVO> consultationProfileList = null;

		try
		{
			// PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			ConsultationProfileDtlDAOi dao = new ConsultationProfileDtlDAO(tx);
			tx.begin();
			consultationProfileList = dao.getPatientProfile(_userVO, _mailRequestId);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return consultationProfileList;
	}

	public void saveExtInvestigationDtl(List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EpisodeExtInvDtlDAOi dao = new EpisodeExtInvDtlDAO(tx);
			for (int i = 0; i < lstEpiExtInvDtlVO.size(); i++)
			{
				dao.saveExtInvestigationDtl(lstEpiExtInvDtlVO.get(i), userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public EpisodeExtInvDtlVO[] getAddedExternalInvDetail(String patCrNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeExtInvDtlVO[] arrAddedExtInvVO = null;

		try
		{
			tx.begin();
			EpisodeExtInvDtlDAOi dao = new EpisodeExtInvDtlDAO(tx);
			arrAddedExtInvVO = dao.getAddedExternalInvDetail(patCrNo, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrAddedExtInvVO;
	}

	public ConsultationDtlVO getMailDetailByMailId(UserVO _userVO, String _mailRequestId)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ConsultationDtlVO consultationDtlVO = null;

		try
		{
			tx.begin();
			CosultationDtlDAOi cosultationDtlDAOi = new ConsultationDtlDAO(tx);
			consultationDtlVO = cosultationDtlDAOi.getMailDetailsByMailId(_userVO, _mailRequestId);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return consultationDtlVO;
	}

	public Map fetchProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, String genderType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// List<PatientProfileDetailVO> lstPatProfiles = null;
		// String profileBound="";
		List<Entry> lstDietType = null;
		List<Entry> lstDrugRoutes = null;
		// DrugFrequencyMstVO[] drugFrequencyMstVO;
		ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = null;
		ProfileAllergyDtlVO[] profileAllergyDtlVO = null;
		ProfileInvDtlVO[] profileInvDtlVO = null;
		ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO = null;
		ProfileAlertsDtlVO[] profileAlertsDtlVO = null;
		ProfileFooterDtlVO[] profileFooterDetailVO = null;
		ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = null;
		ProfileDietAdviceDtlVO profileDietAdviceDtlVO = null;
		ProfileRestAdviceDtlVO profileRestAdviceDtlVO = null;
		ProfileExtTreatmentDtlVO[] _profileExtTreatmentDtlVO = null;
		ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVO = null;
		ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVOs = null;
		ProfileClinicalDtlVO[] _profileClinicalDetailVOs = null;
		ProfileImageDtlVO[] _profileImageDetailVOs = null;
		ProfileOTDtlVO[] profileOTDetailVOs = null;
		ProfileProgressNotesDtlVO[] _profileProgNotesDtlVOs = null;
		ProfileExtExamDtlVO[] _profileExtExamDtVOs = null;
		List profileDrugScheduleDtlVOList = null;
		// List otherInstructionList=null;
		// List oneTimeActivityList=null;
		List otherInstListByGender = null;
		List activityListByGender = null;
		Map map = new HashMap();
		try
		{
			// PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			OpdEssentialDAOi essentialDAO = new OpdEssentialDAO(tx);
			ProfileDiagnosisDtlDAOi profileDiagnosisDtlDao = new ProfileDiagnosisDtlDAO(tx);
			ProfileAllergyDtlDAOi profileAllergyDetailDAO = new ProfileAllergyDtlDAO(tx);
			ProfileInvDtlDAOi profileInvDtlDAO = new ProfileInvDtlDAO(tx);
			ProfileDrugAdviceDtlDAOi profileDrugAdviceDtlDAO = new ProfileDrugAdviceDtlDAO(tx);
			ProfileAlertsDtlDAOi profileAlertsDetailDAO = new ProfileAlertsDtlDAO(tx);
			ProfileFooterDtlDAOi profileFooterDetailDAO = new ProfileFooterDtlDAO(tx);
			ProfileDietAdviceDtlDAOi profileDietAdviceDtlDAOi = new ProfileDietAdviceDtlDAO(tx);
			ProfileRestAdviceDtlDAOi profileRestAdviceDtlDAOi = new ProfileRestAdviceDtlDAO(tx);
			ProfileExtTreatmentDtlDAOi profileExtTreatmentDtlDAOi = new ProfileExtTreatmentDtlDAO(tx);
			ProfileClinicalDtlDAOi profileClinicalDetailDAOi = new ProfileClinicalDtlDAO(tx);
			ProfileOTDtlDAOi profileOTDetailDAOi = new ProfileOTDtlDAO(tx);
			ProfileImageDtlDAOi profileImageDtlDAOi = new ProfileImageDtlDAO(tx);
			ProfileProgressNotesDtlDAO profileProgNotesDtlDAOi = new ProfileProgressNotesDtlDAO(tx);
			ProfileExtExamDtlDAO profileExtExamDtlDAOi = new ProfileExtExamDtlDAO(tx);
			ProfileDrugScheduleDtlDAOi profileDrugScheduleDtlDAOi = new ProfileDrugScheduleDtlDAO(tx);
			tx.begin();
			// ProfileAccessPolicyVO profileAccessPolicy=new ProfileAccessPolicyVO();
			// ProfileGroupDtlVO[] profileGroupDtlVO=null;

			profileDiagnosisDtlVO = profileDiagnosisDtlDao.fetchDiagProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY, profileDiagnosisDtlVO);

			profileAllergyDtlVO = profileAllergyDetailDAO.fetchAllergyProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_VO_ARRAY, profileAllergyDtlVO);

			profileInvDtlVO = profileInvDtlDAO.fetchInvestigationProfileDetails(_patientProfileDtlVO, _userVO);
			for (int i = 0; i < profileInvDtlVO.length; i++)
			{
				profileInvDtlVO[i].setProfileId(_patientProfileDtlVO.getProfileId());
			}
			profileInvDtlVO = profileInvDtlDAO.fetchInvestigationProfileDetailstoGenerate(profileInvDtlVO, _userVO); 
			map.put(OpdConfig.PATIENT_PROFILE_INVESTIGATION_DTL_VO_ARRAY, profileInvDtlVO);

			profileDrugAdviceDtlVO = profileDrugAdviceDtlDAO.fetchTreatProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_TREATMENT_DTL_VO_ARRAY, profileDrugAdviceDtlVO);

			profileAlertsDtlVO = profileAlertsDetailDAO.fetchPatAlertsDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_VO_ARRAY, profileAlertsDtlVO);

			profileFooterDetailVO = profileFooterDetailDAO.fetchProfileFooterDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY, profileFooterDetailVO);

			lstDrugRoutes = essentialDAO.getDrugRouteList(_userVO);
			map.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE, lstDrugRoutes);

			lstDietType = essentialDAO.getAllDietTypeList(_userVO);
			map.put(OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST, lstDietType);

			_profileDrugAdviceDtlVO = profileDrugAdviceDtlDAO.fetchDischargeTreatProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_DRUG_DTL_VO_ARRAY, _profileDrugAdviceDtlVO);

			profileDrugScheduleDtlVOList = profileDrugScheduleDtlDAOi.fetchDrugSchedule(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DRUG_SCHEDULE_DTL_VO_LIST, profileDrugScheduleDtlVOList);

			profileDietAdviceDtlVO = profileDietAdviceDtlDAOi.fetchDischargeDietAdviceDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_DIET_ADVICE_DTL_VO, profileDietAdviceDtlVO);

			profileRestAdviceDtlVO = profileRestAdviceDtlDAOi.fetchDischargeRestAdviceDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_REST_ADVICE_DTL_VO, profileRestAdviceDtlVO);

			_patientProfileDtlVO.setTreatmentType(OpdConfig.EXTERNAL_TREATMENT);
			_profileExtTreatmentDtlVO = profileExtTreatmentDtlDAOi.fetchDischargeExtTreatmentDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_EXT_TREATMENT_DTL_VO_ARRAY, _profileExtTreatmentDtlVO);

			_patientProfileDtlVO.setTreatmentType(OpdConfig.OTHER_INSTRUCTION);
			profileExtTreatmentDtlVO = profileExtTreatmentDtlDAOi.fetchDischargeExtTreatmentDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_INSTRUCTIONS_DTL_VO_ARRAY, profileExtTreatmentDtlVO);

			_patientProfileDtlVO.setTreatmentType(OpdConfig.ONE_TIME_ACTIVITY);
			profileExtTreatmentDtlVOs = profileExtTreatmentDtlDAOi.fetchDischargeExtTreatmentDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_OTHER_ACTIVITY_DTL_VO_ARRAY, profileExtTreatmentDtlVOs);

			otherInstListByGender = essentialDAO.getAllOtherInstructionList(genderType, _patientProfileDtlVO.getDepartmentUnitCode(), _userVO);
			map.put(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER, otherInstListByGender);

			activityListByGender = essentialDAO.getAllOneTimeActivityList(genderType, _patientProfileDtlVO.getDepartmentUnitCode(), _userVO);
			map.put(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER, activityListByGender);

			String recordView = "";
			recordView = profileClinicalDetailDAOi.fetchRecordView(_patientProfileDtlVO, _userVO);

			if (!recordView.equals(""))
			{
				_profileClinicalDetailVOs = profileClinicalDetailDAOi.fetchProfileComplaintsDetailsDateWise(_patientProfileDtlVO, _userVO);
				map.put(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY, _profileClinicalDetailVOs);

//				if (recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
//				{
//					_profileClinicalDetailVOs = profileClinicalDetailDAOi.fetchProfileComplaintsDetails(_patientProfileDtlVO, _userVO);
//					map.put(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY, _profileClinicalDetailVOs);
//				}
//
//				if (recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE))
//				{
//					profileClinicalDetailVO = profileClinicalDetailDAOi.fetchProfileComplaintsDetailsDateWise(_patientProfileDtlVO, _userVO);
//					map.put(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_DATE_WISE_VO_ARRAY, profileClinicalDetailVO);
//				}
			}

			profileOTDetailVOs = profileOTDetailDAOi.fetchOTProfileDetails(_patientProfileDtlVO, _userVO);
			for (int i = 0; i < profileOTDetailVOs.length; i++)
			{
				profileOTDetailVOs[i].setProfileId(_patientProfileDtlVO.getProfileId());
			}
			map.put(OpdConfig.PATIENT_PROFILE_OT_DTL_VO_ARRAY, profileOTDetailVOs);

			_profileImageDetailVOs = profileImageDtlDAOi.fetchImageProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_IMAGE_DTL_VO_ARRAY, _profileImageDetailVOs);

			_profileProgNotesDtlVOs = profileProgNotesDtlDAOi.fetchProfileProgNotesDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_PROGRESS_NOTES_DTL_VO_ARRAY, _profileProgNotesDtlVOs);

			_profileExtExamDtVOs = profileExtExamDtlDAOi.fetchExtExamProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_EXT_INV_DTL_VO_ARRAY, _profileExtExamDtVOs);

			/*
			 * otherInstructionList=essentialDAO.getOtherInstructionList(genderType,
			 * _patientProfileDtlVO.getDepartmentUnitCode(), _userVO);
			 * map.put(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER, otherInstructionList);
			 * 
			 * oneTimeActivityList=essentialDAO.getOneTimeActivityList(genderType,
			 * _patientProfileDtlVO.getDepartmentUnitCode(), _userVO);
			 * map.put(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER, oneTimeActivityList);
			 */
			// map.put(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstPatProfiles);
			// map.put(OpdConfig.OPD_DESK_PROFILE_BOUND, profileBound);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return map;
	}

	public void updateProfileHeaderDetail(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// String fileName = "";
		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			// ProfileDiagnosisDtlDAOi profileDiagnosisDetailDAO=new ProfileDiagnosisDtlDAO(tx);
			tx.begin();

			dao.updateProfileHeaderDetail(_patProfileDtlVO, _userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}


	/**
	 * Changed By :- Manisha Gangwar
	 * Reason:- change file upload process from ftp to mongoDb 
	 * Changed Date :- 29-03-2016
	 * @author AHIS*
	 */
	
	/**
	 * generate the profile, the profile is created on the disk, the profile dtl table is updated with the profile status
	 * flag as generated, The default access policy is saved if found in the previous method
	 */
	public void profileGeneration(PatientProfileDetailVO _patProfileDtlVO, ProfileGroupDtlVO[] profileGroupDtlVO,
			ProfileAccessPolicyVO profileAccessPolicy, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String fileName = "";
		byte[] profileData = null; //added by Dheeraj on 05-Dec-2018
		try
		{
			
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			// ProfileDiagnosisDtlDAOi profileDiagnosisDetailDAO=new ProfileDiagnosisDtlDAO(tx);
			ProfileAccessDetailDAOi profileAccessDetailDAO = new ProfileAccessDetailDAO(tx);
			ProfileAccessDetailVO profileAccessDetailVO = new ProfileAccessDetailVO();

			tx.begin();

			String profileId = _patProfileDtlVO.getProfileId();

			fileName = profileId + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
					
			//HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS,
					//Config.PATIENT_PROFILE_STORAGE_PATH_LINUX);
			//fileUtil.setFileContent(_patProfileDtlVO.getProfileData().getBytes());
			//fileUtil.saveFile();
			
			// Commented by Dheeraj on 05-Dec-2018
            //Calling MongoDb from Here					
			/*MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_GENERATED_PATIENT_PROFILE_UPLOAD).savePDFFile(fileName,_patProfileDtlVO.getProfileData().getBytes());
			
			System.out.println("lookout tag Vasu: file1"+fileName);
			fileName = profileId + ".pdf";
			
            //Calling MongoDb from Here			
			//String getDoc = "";
			MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_GENERATED_PATIENT_PROFILE_UPLOAD).savePDFFile(fileName,_patProfileDtlVO.getProfileDataPdf());
			System.out.println("lookout tag Vasu: file2"+fileName);*/
			
			
			
			dao.updateProfileStatus(_patProfileDtlVO, _userVO);
			//added by Dheeraj on 05-Dec-2018
			dao.savePatientProfile(_patProfileDtlVO,_userVO);
			
			profileAccessDetailDAO.delete(_patProfileDtlVO.getProfileId(), _userVO);

			if(_patProfileDtlVO.getProfileHeader().equals("Customized Profile") || _patProfileDtlVO.getProfileHeader().equals("General Profile") )
			{
				
			if (profileAccessPolicy.getAccessType() != null)
			{
				if (profileAccessPolicy.getAccessType().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
				{
					_patProfileDtlVO.setAccessType(profileAccessPolicy.getAccessType());
					_patProfileDtlVO.setUserLevel(profileAccessPolicy.getUserLevel());
					dao.updateProfileStatus(_patProfileDtlVO, _userVO);
				}
				else
				{

					if (profileGroupDtlVO != null)
					{
						for (int i = 0; i < profileGroupDtlVO.length; i++)
						{
							if (profileGroupDtlVO[i].getRecordMode().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC))
							{
								profileAccessDetailVO.setDepartmentUnitCode(profileGroupDtlVO[i].getAccessRecordId());
								profileAccessDetailVO.setUserId("");
							}
							else if (profileGroupDtlVO[i].getRecordMode().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND))
							{
								profileAccessDetailVO.setUserId(profileGroupDtlVO[i].getAccessRecordId());
								profileAccessDetailVO.setDepartmentUnitCode("");
							}
							profileAccessDetailVO.setAccessType(profileGroupDtlVO[i].getRecordMode());
							profileAccessDetailVO.setProfileId(_patProfileDtlVO.getProfileId());
							profileAccessDetailDAO.create(profileAccessDetailVO, _userVO);

							_patProfileDtlVO.setAccessType(profileAccessPolicy.getAccessType());
							_patProfileDtlVO.setUserLevel(profileAccessPolicy.getUserLevel());

							dao.updateProfileStatus(_patProfileDtlVO, _userVO);

						}
					}
				}
			}
			
		}

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	
	
	/**
	 * generate the profile, the profile is created on the disk, the profile dtl table is updated with the profile status
	 * flag as generated, The default access policy is saved if found in the previous method
	 *//*
	public void profileGeneration(PatientProfileDetailVO _patProfileDtlVO, ProfileGroupDtlVO[] profileGroupDtlVO,
			ProfileAccessPolicyVO profileAccessPolicy, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String fileName = "";
		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			// ProfileDiagnosisDtlDAOi profileDiagnosisDetailDAO=new ProfileDiagnosisDtlDAO(tx);
			ProfileAccessDetailDAOi profileAccessDetailDAO = new ProfileAccessDetailDAO(tx);
			ProfileAccessDetailVO profileAccessDetailVO = new ProfileAccessDetailVO();

			tx.begin();

			String profileId = _patProfileDtlVO.getProfileId();

			fileName = profileId + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
			HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS,
					Config.PATIENT_PROFILE_STORAGE_PATH_LINUX);
			fileUtil.setFileContent(_patProfileDtlVO.getProfileData().getBytes());
			fileUtil.saveFile();
			dao.updateProfileStatus(_patProfileDtlVO, _userVO);
			profileAccessDetailDAO.delete(_patProfileDtlVO.getProfileId(), _userVO);

			if (profileAccessPolicy.getAccessType() != null)
			{
				if (profileAccessPolicy.getAccessType().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
				{
					_patProfileDtlVO.setAccessType(profileAccessPolicy.getAccessType());
					_patProfileDtlVO.setUserLevel(profileAccessPolicy.getUserLevel());
					dao.updateProfileStatus(_patProfileDtlVO, _userVO);
				}
				else
				{

					if (profileGroupDtlVO != null)
					{
						for (int i = 0; i < profileGroupDtlVO.length; i++)
						{
							if (profileGroupDtlVO[i].getRecordMode().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC))
							{
								profileAccessDetailVO.setDepartmentUnitCode(profileGroupDtlVO[i].getAccessRecordId());
								profileAccessDetailVO.setUserId("");
							}
							else if (profileGroupDtlVO[i].getRecordMode().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND))
							{
								profileAccessDetailVO.setUserId(profileGroupDtlVO[i].getAccessRecordId());
								profileAccessDetailVO.setDepartmentUnitCode("");
							}
							profileAccessDetailVO.setAccessType(profileGroupDtlVO[i].getRecordMode());
							profileAccessDetailVO.setProfileId(_patProfileDtlVO.getProfileId());
							profileAccessDetailDAO.create(profileAccessDetailVO, _userVO);

							_patProfileDtlVO.setAccessType(profileAccessPolicy.getAccessType());
							_patProfileDtlVO.setUserLevel(profileAccessPolicy.getUserLevel());

							dao.updateProfileStatus(_patProfileDtlVO, _userVO);

						}
					}
				}
			}

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}
*/
	/**
	 * Getting Episode Diagnosis Detail for Patient Profile
	 * 
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public ProfileInvestigationVO[] getOpdInvestigationDetailProfilePatientEpisodes(String _crNo, String _deskType, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ProfileInvestigationVO[] profileInvestigationVOs = null;
		try
		{
			PatientProfileDetailDAOi daoPatProfile = new PatientProfileDetailDAO(tx);
			// EpisodeDiagnosisDAOi episodeDiagnosisiDAOi = new EpisodeDiagnosisDAO(tx);
			tx.begin();
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				profileInvestigationVOs = daoPatProfile.retrieveAdmissionInvestigationForProfile(_crNo, voDP, _userVO);
			}
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				profileInvestigationVOs = daoPatProfile.retrieveEpisodeInvestigationForProfile(_crNo, _userVO, voDP, profileGenerationMode);
			}

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return profileInvestigationVOs;
	}

	public Map fetchDetailsForGenerate(PatientProfileDetailVO _patientProfileDtlVO,String _deskType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// List<PatientProfileDetailVO> lstPatProfiles = null;
		// String profileBound="";
		List<Entry> lstDietType = null;
		List<Entry> lstDrugRoutes = null;
		// DrugFrequencyMstVO[] drugFrequencyMstVO;
		ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = null;
		ProfileAllergyDtlVO[] profileAllergyDtlVO = null;
		ProfileInvDtlVO[] profileInvDtlVO = null;
		ProfileInvestigationVO[] profileInvestigationVO = null; //Added by Vasu on 13-Nov-2017
		ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO = null;
		ProfileAlertsDtlVO[] profileAlertDtlVO = null;
		ProfileFooterDtlVO[] profileFooterDetailVO = null;
		ProfileDrugAdviceDtlVO[] _profileDrugAdviceDtlVO = null;
		ProfileDietAdviceDtlVO profileDietAdviceDtlVO = null;
		ProfileRestAdviceDtlVO profileRestAdviceDtlVO = null;
		ProfileExtTreatmentDtlVO[] _profileExtTreatmentDtlVO = null;
		ProfileClinicalDtlVO[] _profileClinicalDetailVOs = null;
		ProfileOTDtlVO[] profileOTDetailVOs = null;
		ProfileImageDtlVO[] _profileImageDetailVOs = null;
		ProfileProgressNotesDtlVO[] _profileProgNotesDtlVOs = null;
		ProfileExtExamDtlVO[] _profileExtExamDtVOs = null;
		List<ProfileChartViewDtlVO> _profileChartViewDtlVO=null;
		Map map = new HashMap();
		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			OpdEssentialDAOi essentialDAO = new OpdEssentialDAO(tx);
			ProfileDiagnosisDtlDAOi profileDiagnosisDtlDao = new ProfileDiagnosisDtlDAO(tx);
			ProfileAllergyDtlDAOi profileAllergyDetailDAO = new ProfileAllergyDtlDAO(tx);
			ProfileInvDtlDAOi profileInvDtlDAO = new ProfileInvDtlDAO(tx);
			ProfileDrugAdviceDtlDAOi profileDrugAdviceDtlDAO = new ProfileDrugAdviceDtlDAO(tx);
			ProfileAlertsDtlDAOi profileAlertsDetailDAO = new ProfileAlertsDtlDAO(tx);
			ProfileFooterDtlDAOi profileFooterDetailDAO = new ProfileFooterDtlDAO(tx);
			ProfileDietAdviceDtlDAOi profileDietAdviceDtlDAOi = new ProfileDietAdviceDtlDAO(tx);
			ProfileRestAdviceDtlDAOi profileRestAdviceDtlDAOi = new ProfileRestAdviceDtlDAO(tx);
			ProfileExtTreatmentDtlDAOi profileExtTreatmentDtlDAOi = new ProfileExtTreatmentDtlDAO(tx);
			ProfileClinicalDtlDAOi profileClinicalDetailDAOi = new ProfileClinicalDtlDAO(tx);
			ProfileOTDtlDAOi profileOTDetailDAOi = new ProfileOTDtlDAO(tx);
			ProfileImageDtlDAOi profileImageDtlDAOi = new ProfileImageDtlDAO(tx);
			ProfileProgressNotesDtlDAO profileProgNotesDtlDAOi = new ProfileProgressNotesDtlDAO(tx);
			ProfileExtExamDtlDAO profileExtExamDtlDAOi = new ProfileExtExamDtlDAO(tx);
			ProfileChartViewDetailDAO profileChartViewDetailDAOi = new ProfileChartViewDetailDAO(tx);
			tx.begin();
			ProfileAccessPolicyVO profileAccessPolicy = new ProfileAccessPolicyVO();
			ProfileGroupDtlVO[] profileGroupDtlVO = null;

			String count = dao.fetchProfileRestrictedCapCount(_patientProfileDtlVO, _userVO);

			if (count.equals("1"))
			{
				_patientProfileDtlVO.setPolicyType(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_RESTRICTED);
			}
			else
			{
				_patientProfileDtlVO.setPolicyType(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_NORMAL);
			}

			profileAccessPolicy = dao.fetchAccessTypeProfileAccessPolicy(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_DETAIL_VO, profileAccessPolicy);

			if (profileAccessPolicy.getAccessType() != null)
			{
				if (!profileAccessPolicy.getAccessType().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
				{
					profileGroupDtlVO = dao.fetchGroupIdProfileGroupDtl(profileAccessPolicy, _userVO);
					map.put(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_VO_ARRAY, profileGroupDtlVO);
				}
			}

			profileDiagnosisDtlVO = profileDiagnosisDtlDao.fetchDiagProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY, profileDiagnosisDtlVO);

			profileAllergyDtlVO = profileAllergyDetailDAO.fetchAllergyProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_VO_ARRAY, profileAllergyDtlVO);

			profileInvDtlVO = profileInvDtlDAO.fetchInvestigationProfileDetails(_patientProfileDtlVO, _userVO);
			for (int i = 0; i < profileInvDtlVO.length; i++)
			{
				profileInvDtlVO[i].setProfileId(_patientProfileDtlVO.getProfileId());

			}
			profileInvDtlVO = profileInvDtlDAO.fetchInvestigationProfileDetailstoGenerate(profileInvDtlVO, _userVO); //Added by Vasu on 13-Nov-2017
			map.put(OpdConfig.PATIENT_PROFILE_INVESTIGATION_DTL_VO_ARRAY, profileInvDtlVO);

			profileDrugAdviceDtlVO = profileDrugAdviceDtlDAO.fetchTreatProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_TREATMENT_DTL_VO_ARRAY, profileDrugAdviceDtlVO);

			profileAlertDtlVO = profileAlertsDetailDAO.fetchPatAlertsDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_VO_ARRAY, profileAlertDtlVO);

			profileFooterDetailVO = profileFooterDetailDAO.fetchProfileFooterDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY, profileFooterDetailVO);

			lstDrugRoutes = essentialDAO.getDrugRouteList(_userVO);
			map.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE, lstDrugRoutes);

			lstDietType = essentialDAO.getAllDietTypeList(_userVO);
			map.put(OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST, lstDietType);

			_profileDrugAdviceDtlVO = profileDrugAdviceDtlDAO.fetchDischargeTreatProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_DRUG_DTL_VO_ARRAY, _profileDrugAdviceDtlVO);

			profileDietAdviceDtlVO = profileDietAdviceDtlDAOi.fetchDischargeDietAdviceDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_DIET_ADVICE_DTL_VO, profileDietAdviceDtlVO);

			profileRestAdviceDtlVO = profileRestAdviceDtlDAOi.fetchDischargeRestAdviceDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_REST_ADVICE_DTL_VO, profileRestAdviceDtlVO);

			_patientProfileDtlVO.setTreatmentType(OpdConfig.EXTERNAL_TREATMENT);
			_profileExtTreatmentDtlVO = profileExtTreatmentDtlDAOi.fetchDischargeExtTreatmentDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_EXT_TREATMENT_DTL_VO_ARRAY, _profileExtTreatmentDtlVO);

			// Clinical Template Data
			
			String recordView = "";
			 recordView = profileClinicalDetailDAOi.fetchRecordView(_patientProfileDtlVO, _userVO);  //commented by manisha gangwar 

			if (!recordView.equals(""))
			{
				_profileClinicalDetailVOs = profileClinicalDetailDAOi.fetchProfileComplaintsDetails(_patientProfileDtlVO,_deskType, recordView,_userVO);
				map.put(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY, _profileClinicalDetailVOs);

				//if (recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE))
				//{
				//	_profileClinicalDetailVOs = profileClinicalDetailDAOi.fetchProfileComplaintsDetails(_patientProfileDtlVO, _userVO);
				//	map.put(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY, _profileClinicalDetailVOs);
				//}

				//if (recordView.equals(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE))
				//{
				//	profileClinicalDetailVO = profileClinicalDetailDAOi.fetchProfileComplaintsDetailsDateWise(_patientProfileDtlVO, _userVO);
				//	map.put(OpdConfig.PATIENT_PROFILE_COMPLAINTS_DTL_DATE_WISE_VO_ARRAY, profileClinicalDetailVO);
				//}
			}

			profileOTDetailVOs = profileOTDetailDAOi.fetchOTProfileDetails(_patientProfileDtlVO, _userVO);
			for (int i = 0; i < profileOTDetailVOs.length; i++)
			{
				profileOTDetailVOs[i].setProfileId(_patientProfileDtlVO.getProfileId());
			}
			map.put(OpdConfig.PATIENT_PROFILE_OT_DTL_VO_ARRAY, profileOTDetailVOs);

			// Image Detail
			_profileImageDetailVOs = profileImageDtlDAOi.fetchImageProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_IMAGE_DTL_VO_ARRAY, _profileImageDetailVOs);

			// Progress Notes Detail
			_profileProgNotesDtlVOs = profileProgNotesDtlDAOi.fetchProfileProgNotesDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_PROGRESS_NOTES_DTL_VO_ARRAY, _profileProgNotesDtlVOs);

			// External Examination Detail
			_profileExtExamDtVOs = profileExtExamDtlDAOi.fetchExtExamProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_EXT_INV_DTL_VO_ARRAY, _profileExtExamDtVOs);

			//Chart View Detail
			_profileChartViewDtlVO = profileChartViewDetailDAOi.fetchChartViewProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_CHART_VIEW_DTL_VO_ARRAY, _profileChartViewDtlVO);

			
			// map.put(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstPatProfiles);
			// map.put(OpdConfig.OPD_DESK_PROFILE_BOUND, profileBound);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return map;
	}

	public void removeUserPriv(ProfileAccessDetailVO _profileAccessDetailVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			ProfileAccessDetailDAOi daoProfileAccess = new ProfileAccessDetailDAO(tx);
			tx.begin();

			daoProfileAccess.removeUserPriv(_profileAccessDetailVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public PatDrugTreatmentDetailVO[] getPatientTreatmentDetail(String _crNo, PatientDetailVO voDP, String profileGenerationMode, String _deskType,
			UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = null;
		try
		{
			tx.begin();
			PatDrugTreatmentDetailDAOi patDrugTreatmentDtlDAO = new PatDrugTreatmentDetailDAO(tx);

			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				patDrugTreatmentDtlVO = patDrugTreatmentDtlDAO.getIpdPatientTreatmentDetail(_crNo, voDP.getPatAdmNo(), _userVO);
			}
			else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
			{
				patDrugTreatmentDtlVO = patDrugTreatmentDtlDAO.getPatientTreatmentDetail(_crNo, voDP, profileGenerationMode, _userVO);
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return patDrugTreatmentDtlVO;

	}

	public void revokeAllergy(EpisodeAllergiesVO epiAllergyVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// PatAllergyDtlVO patAllergyDtlVO=new PatAllergyDtlVO();

		try
		{
			EpisodeAllergiesDAO episodeAllergyDAO = new EpisodeAllergiesDAO(tx);
			PatAllergyDtlDAO patAllergyDtlDAO = new PatAllergyDtlDAO(tx);
			tx.begin();

			patAllergyDtlDAO.updateAllergyId(epiAllergyVO, userVO);

			epiAllergyVO.setIsRevoked(OpdConfig.PATIENT_ALERT_REVOKED_YES);
			//episodeAllergyDAO.create(epiAllergyVO, userVO); //commented by Dheeraj to avoid duplicate entry on 10-Dec-2018

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public PatientAlertsDetailVO[] getPatientAlertsDetail(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientAlertsDetailVO[] patientAlertsDetailVO = null;
		try
		{
			tx.begin();
			PatAlertsDetailDAOi patAlertsDetailDAO = new PatAlertsDetailDAO(tx);
			patientAlertsDetailVO = patAlertsDetailDAO.getPatientAlertsDetail(_crNo, _userVO, voDP, profileGenerationMode);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return patientAlertsDetailVO;

	}

	public Map getDrugProperties(String itemId, UserVO _userVO)
	{
		Map mapDrugProperty = new HashMap();
		List drugDoseIndicationList = null;
		List drugSaftyAlertList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			PatDrugTreatmentDetailDAOi dao = new PatDrugTreatmentDetailDAO(tx);

			drugDoseIndicationList = dao.getDrugDoseIndicationList(itemId, _userVO);
			mapDrugProperty.put(OpdConfig.DRUG_DOSE_INDICATION_LIST, drugDoseIndicationList);

			drugSaftyAlertList = dao.getDrugSaftyAlertList(itemId, _userVO);
			mapDrugProperty.put(OpdConfig.DRUG_SAFTY_ALERT_LIST, drugSaftyAlertList);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mapDrugProperty;
	}

	/**
	 * Getting Patient All Profiles List For Inbox
	 * 
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForAll(String _patCrNo, String _unitCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientProfileDetailVO> lstPatProfiles = null;

		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			tx.begin();
			lstPatProfiles = dao.getPatientProfilesForAll(_patCrNo, _unitCode, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstPatProfiles;
	}

	public DoctorRoundDtlVO[] getPatientProgressNotes(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorRoundDtlVO[] doctorRoundDtlVO = null;
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDtlDAOi = new DoctorRoundDtlDAO(tx);
			doctorRoundDtlVO = doctorRoundDtlDAOi.getPatientProgressNotes(_crNo, _userVO, voDP, profileGenerationMode);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return doctorRoundDtlVO;

	}

	// Saving Image to Patient in Image Examination
	/*public void savePatientExaminationImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			HisFileControlUtil fileUtil = new HisFileControlUtil();
			fileUtil.setWindowsFilePath(Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS);
			fileUtil.setLinuxFilePath(Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX);

			final String OS_NAME = System.getProperties().getProperty("os.name");
			String path = "";
			if (OS_NAME.startsWith("Linux")) path =  fileUtil.getLinuxFilePath();
			else path = fileUtil.getWindowsFilePath();
			_vo.setDirPath(path);
			
			OpdPatientImageDtlDAOi daoobj = new OpdPatientImageDtlDAO(tx);
			String imageFileName = daoobj.save(_vo, _userVO);

			String imageFormat = imageFileName.substring(imageFileName.indexOf(".") + 1).toLowerCase();
			File file = new File(fileUtil.getPath(), imageFileName);
			System.out.println("Buff Img : " + _image);
			System.out.println("Img Format : " + imageFormat);
			System.out.println("Img  Path & file: " + file);
			//WatermarkUtility.watermark(_image, "CDAC");
			ImageIO.write(_image, imageFormat, file);
			// fileUtil.setFileName(imageFileName);
			// fileUtil.setFileContent(audioVideoMasterVO.getFile());
			// fileUtil.saveFile();
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}*/
	/* Added by Vasu on 21-AUG-2017  */
	public void savePatientExaminationImage(OpdPatientImageDtlVO patImageVO, UserVO userVO) {
	  JDBCTransactionContext tx = new JDBCTransactionContext();
	 
	 // HisDAO hisDAO = new HisDAO("HISClinical", "OpdPatientBO");
	  try
	  {
		  tx.begin();
		  
		  OpdPatientImageDtlDAO patImageDAO=new OpdPatientImageDtlDAO(tx);
				String docId="";
				//docId=patImageVO.getImageCode()+"-"+patImageVO.getPatCrNo()+"-"+patImageVO.getEpisodeCode();
				docId=patImageVO.getPatCrNo()+"-"+patImageVO.getEpisodeCode()+"-"+patImageVO.getEntryDate();
				
				//FileInputStream fileInFTPStream = new FileInputStream(this.uploadedFile);
				
				patImageVO.setImageFileName(docId);
			
				//MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_PATIENT_IMAGE_UPLOAD).savePDFFile(docId, patImageVO.getImageFile());
				
				//patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, objUserVO_p);
				
				String imageFileName = patImageDAO.save(patImageVO, userVO);
				patImageDAO.saveImageToPostgres(patImageVO,imageFileName);
			
	  }
		
	  catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
	// End VASU

	// Modifying Image to Patient in Image Examination
	public void modifyPatientExaminationImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			HisFileControlUtil fileUtil = new HisFileControlUtil();
			fileUtil.setWindowsFilePath(Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS);
			fileUtil.setLinuxFilePath(Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX);
			_vo.setDirPath(fileUtil.getPath());

			OpdPatientImageDtlDAOi daoobj = new OpdPatientImageDtlDAO(tx);
			String imageFileName = daoobj.save(_vo, _userVO);

			String imageFormat = imageFileName.substring(imageFileName.indexOf(".") + 1).toLowerCase();
			File file = new File(fileUtil.getPath(), imageFileName);
			ImageIO.write(_image, imageFormat, file);

			_vo.setNewFileName(imageFileName);
			daoobj.updateOld(_vo, _userVO);

			// fileUtil.setFileName(imageFileName);
			// fileUtil.setFileContent(audioVideoMasterVO.getFile());
			// fileUtil.saveFile();
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public PatientProfileDetailVO[] getPreviousProfileDetails(String _crNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientProfileDetailVO[] patProfileDtlVO = null;
		try
		{
			tx.begin();
			PatientProfileDetailDAOi patientProfileDetailDAO = new PatientProfileDetailDAO(tx);
			patProfileDtlVO = patientProfileDetailDAO.getPreviousProfileDetails(_crNo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patProfileDtlVO;

	}

	public void savePatientProfileReviewDetail(ProfileReviewDtlVO profileReviewDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			ProfileReviewDtlDAOi profileReviewDtlDAO = new ProfileReviewDtlDAO(tx);

			profileReviewDtlDAO.create(profileReviewDtlVO, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public ProfileReviewDtlVO[] fetchProfileReviewDetails(String _crNo, String profileId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ProfileReviewDtlVO[] profileReviewDtlVO = null;
		try
		{
			tx.begin();
			ProfileReviewDtlDAOi profileReviewDtlDAO = new ProfileReviewDtlDAO(tx);
			profileReviewDtlVO = profileReviewDtlDAO.fetchProfileReviewDetails(_crNo, profileId, _userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return profileReviewDtlVO;

	}

	// patient profile OT

	public ProfileOTDetailVO[] getPatientOperationDetail(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ProfileOTDetailVO[] patientProfileOTDetailVO = null;
		try
		{
			PatientProfileDetailDAOi daoPatProfile = new PatientProfileDetailDAO(tx);
			tx.begin();

			patientProfileOTDetailVO = daoPatProfile.getPatientOperationDetail(_crNo, _userVO, voDP, profileGenerationMode);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patientProfileOTDetailVO;
	}

	public Map getPatientProfileOperationTemplateDetails(List profileOTList, String patCrNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map templateAndParaDetails = new HashMap();
		List listOperationTemplate = null;
		List listOperationTemplateAll = new ArrayList();
		List templateParaIdValue = null;
		List templateParaIdValueAll = new ArrayList();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);

			int profileOTListSize = profileOTList.size();
			for (int i = 0; i < profileOTListSize; i++)
			{
				ProfileOTDetailVO profileDetailVO = (ProfileOTDetailVO) profileOTList.get(i);
				listOperationTemplate = opdEssentialDAOi.getOTTemplateListEMR(profileDetailVO.getDeptCode(), _userVO);
				listOperationTemplateAll.add(listOperationTemplate);
				templateParaIdValue = opdEssentialDAOi.getOTTemplateParaValueEMR(profileDetailVO.getDeptCode(), patCrNo,
						profileDetailVO.getOtReqNo(), _userVO);
				templateParaIdValueAll.add(templateParaIdValue);
			}

			templateAndParaDetails.put(OpdConfig.OPERATION_TEMPLATE_LIST_ALL, listOperationTemplateAll);
			templateAndParaDetails.put(OpdConfig.OPERATION_TEMPLATE_PARA_ID_VALUE_ALL, templateParaIdValueAll);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return templateAndParaDetails;
	}

	/**
	 * Saving Visit Summary Detail
	 * 
	 * @param _episodeSummaryVO Episode Summary Detail
	 * @param _userVO User DEtail
	 * @param _episodeActDtlVO Episode Action Detail for CMO Desk
	 * @param _deskType Desk Type
	 */

	/**
	## 		Modification Log		: episodeVO.setEpisodeCloseDate(_episodeActDtlVO.getEpisodeCloseDate());		
	##		Modify Date				: 19-02-2015
	##		Reason	(CR/PRS)		: Data model changed in table , So Episode Closed Date passed
	##		Modify By				: Akash Singh
	*/
	public void saveVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO,
			String _deskType, EpisodeCloseVO _episodeCloseVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			EpisodeVO episodeVO = new EpisodeVO();
			HelperMethods.populate(episodeVO, _episodeSummaryVO);
			episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			episodeVO.setEpisodeName(_episodeSummaryVO.getEpisodeKeywords());
			episodeVO.setComplainDetail(_episodeSummaryVO.getVisitNotes());
			episodeVO.setEpisodeSummary(_episodeSummaryVO.getEpisodeSummary());
			episodeVO.setQueNo(_episodeSummaryVO.getEpisodeVisitSlNo());
			episodeVO.setEpisodeKeywords(_episodeSummaryVO.getEpisodeKeywords());
			EpisodeDAO dao = new EpisodeDAO(tx);
			
			
			DailyPatientDAO patDao = new DailyPatientDAO(tx);
			patDao.updateIsconfirmed(episodeVO.getPatCrNo(), episodeVO.getQueNo(), episodeVO.getEpisodeVisitNo(), episodeVO.getEpisodeCode(),
					episodeVO.getIsConfirmed(), _userVO);
			
			EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
			
			// modified for: keywords autocomplete is no longer in use  date: 09.07.2016
			/*String keyWordsVal=_episodeSummaryVO.getEpisodeKeywords(); 
			int len=keyWordsVal.length();
			if(len>0)
				keyWordsVal = keyWordsVal.substring(0, keyWordsVal.length() - 1);
				
			String keyWordIds="";			
			keyWordIds=episodeSummaryDAO.select(keyWordsVal, _userVO);
			String keyWordData=keyWordsVal.replaceAll(",", "#");
			System.out.println("keyWordData:::"+keyWordData);
		
			episodeVO.setEpisodeKeywords(keyWordData);
			_episodeSummaryVO.setEpisodeKeywords(keyWordsVal);
			_episodeSummaryVO.setEpisodeKeywordID(keyWordIds);
			episodeVO.setEpisodeKeywords(keyWordsVal);
			episodeVO.setEpisodeKeywordID(keyWordIds);  */	
			
			
			episodeVO.setVisitReason(_episodeSummaryVO.getVisitReason());
			episodeVO.setEpisodeCloseDate(_episodeActDtlVO.getEpisodeCloseDate());
			//update daily patient detail table
			dao.updateDailyPatientDetail(episodeVO, _userVO);
			//update in episode table
			dao.updateNextVisitDetail(episodeVO, _userVO);
			//episode summar dtl insert
			//episodeSummaryDAO.create(_episodeSummaryVO, _userVO);

			// When Episode is closed
			if (_episodeCloseVO != null)
			{
				EpisodeCloseDAOi episodeeCloseDAO = new EpisodeCloseDAO(tx);
				episodeeCloseDAO.create(_episodeCloseVO, _userVO);
			}

			// //////// Only for CMO Desk ///////
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_CMO_DESK))
			{
				EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
				episodeVO.setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_CODE_ATTENDED_DISPOSED);
				episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);

				dao.updateEpisodeDtlForCMO(episodeVO, _userVO);
				_episodeActDtlVO.setComplainDtl(episodeVO.getComplainDetail());
				_episodeActDtlVO.setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_CODE_ATTENDED_DISPOSED);
				episodeActionDtlDAO.create(_episodeActDtlVO, _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Modifying Visit Summary Detail
	 * 
	 * @param _episodeSummaryVO Episode Summary Detail
	 * @param _userVO User DEtail
	 * @param _episodeActDtlVO Episode Action Detail for CMO Desk
	 * @param _deskType Desk Type
	 */
	/**
	## 		Modification Log		: episodeVO.setEpisodeCloseDate(_episodeActDtlVO.getEpisodeCloseDate());		
	##		Modify Date				: 19-02-2015
	##		Reason	(CR/PRS)		: Data model changed in table , So Episode Closed Date passed
	##		Modify By				: Akash Singh
	*/
	public void modifyVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO,
			String _deskType, EpisodeCloseVO _episodeCloseVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			EpisodeVO episodeVO = new EpisodeVO();
			HelperMethods.populate(episodeVO, _episodeSummaryVO);
			episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			episodeVO.setEpisodeName(_episodeSummaryVO.getEpisodeKeywords());
			episodeVO.setComplainDetail(_episodeSummaryVO.getVisitNotes());
			episodeVO.setEpisodeSummary(_episodeSummaryVO.getEpisodeSummary());
			episodeVO.setQueNo(_episodeSummaryVO.getEpisodeVisitSlNo());
			episodeVO.setEpisodeCloseDate(_episodeActDtlVO.getEpisodeCloseDate());
			episodeVO.setEpisodeKeywords(_episodeSummaryVO.getEpisodeKeywords());
			EpisodeDAO dao = new EpisodeDAO(tx);

			EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
			//episodeSummaryDAO.updateOld(_episodeSummaryVO, _userVO);
			/*String keyWordsVal=_episodeSummaryVO.getEpisodeKeywords(); //commented as autocomplete is not in use
			int len=keyWordsVal.length();
			*/
				//keyWordsVal = keyWordsVal.substring(0, keyWordsVal.length() - 1);
				
			/*String keyWordIds="";			  //commented as autocomplete is not in use
			keyWordIds=episodeSummaryDAO.select(keyWordsVal, _userVO);
			String keyWordData=keyWordsVal.replaceAll(",", "#");
			System.out.println("keyWordData:::"+keyWordData);
			_episodeSummaryVO.setEpisodeKeywords(keyWordsVal);
			_episodeSummaryVO.setEpisodeKeywordID(keyWordIds);
			episodeVO.setEpisodeKeywords(keyWordsVal);
			episodeVO.setEpisodeKeywordID(keyWordIds);  
		*/  //commented as autocomplete is not in use  end
			//update in episode table
			dao.updateDailyPatientDetail(episodeVO, _userVO);
			dao.updateNextVisitDetail(episodeVO, _userVO);
			//episodeSummaryDAO.create(_episodeSummaryVO, _userVO);
			//episodeSummaryDAO.updateEpisodeSummaryDtl(_episodeSummaryVO, _userVO);
			// When Episode is closed
			if (_episodeCloseVO != null)
			{
				EpisodeCloseDAOi episodeeCloseDAO = new EpisodeCloseDAO(tx);
				episodeeCloseDAO.create(_episodeCloseVO, _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void savePatientAttendantDtl(String isExisting, PatientFamilyDtlVO patFamilyVO, EpisodeAttendantDetailVO epiAttendantVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EpisodeAttendantDtlDAOi epiAttendantDAO = new EpisodeAttendantDtlDAO(tx);
			PatientFamilyDtlDAOi patFamilyDAO = new PatientFamilyDtlDAO(tx);

			if (isExisting.equals(OpdConfig.NO))
			{
				boolean isRelativeExist = patFamilyDAO.isExist(patFamilyVO, userVO);
				if(isRelativeExist)
					throw new HisDuplicateRecordException("Patient Relative already Exists..");
					
				String relativeId = "";
				relativeId = patFamilyDAO.generatePatientRelativeId(patFamilyVO.getPatCrNo());
				if (relativeId.equals("1"))
				{
					relativeId = patFamilyVO.getPatCrNo() + "01";
				}

				epiAttendantVO.setPatRelativeId(relativeId);
				patFamilyVO.setPatRelativeId(relativeId);
			}

			if (patFamilyVO != null)
			{
				patFamilyDAO.create(patFamilyVO, userVO);
			}

			epiAttendantDAO.create(epiAttendantVO, userVO);

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Getting Generic Chart Detail
	 * 
	 * @param _deskType Desk Type
	 * @param _patDtlVO Patient Detail
	 * @param _chartVO Chart Detail
	 * @param _lstChartParas List Chart Parameters
	 * @param _criteria Criteria
	 * @param _fromDate From Date
	 * @param _toDate To Date
	 * @param _userVO User Detail
	 * @return Map of Data
	 */
	public Map<String, Object> getChartReportingData(String _deskType, PatientDetailVO _patDtlVO, ChartMasterVO _chartVO,
			List<ChartParameterMappingVO> _lstChartParas, String _criteria, String _fromDate, String _toDate, UserVO _userVO)
	{
		Map<String, Object> mp = new HashMap();

		LinkedHashMap<String, ChartColumnHead> mpColHead = new LinkedHashMap<String, ChartColumnHead>();
		LinkedHashMap<String, Map<String, ChartCellData>> mpChartData = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ChartMasterDAOi chartMstDAO = new ChartMasterDAO(tx);
			ChartDetailDAOi chartDtlDAO = new ChartDetailDAO(tx);
			if (_chartVO.getGenerationType().equals(OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE))
			{
				List<ChartParameterMappingVO> lstChartParas = null;
				if (_lstChartParas != null && _lstChartParas.size() > 0) lstChartParas = _lstChartParas;
				else lstChartParas = chartMstDAO.getChartTempParasWithRange(_patDtlVO, _chartVO.getChartId(), _userVO);

				String strParasTemp = "", strParasInv = "", strParasInOut = "";
				for (ChartParameterMappingVO vo : lstChartParas)
				{
					if (vo.getParaType().equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_CLINICAL)) strParasTemp += vo.getParaId() + ",";
					else if (vo.getParaType().equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_INVESTIGATION)) strParasInv += vo.getParaId() + ",";
					else if (vo.getParaType().equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_INTAKE_OUTPUT)) strParasInOut += vo.getParaId() + ",";
					ChartColumnHead objHead = new ChartColumnHead();
					objHead.setId(vo.getParaId());
					objHead.setName(vo.getParaName());
					objHead.setOrder(vo.getDisplayOrder());
					mpColHead.put(objHead.getId(), objHead);
				}
				if (!strParasTemp.equals("")) strParasTemp = strParasTemp.substring(0, strParasTemp.length() - 1);
				if (!strParasInv.equals("")) strParasInv = strParasInv.substring(0, strParasInv.length() - 1);
				if (!strParasInOut.equals("")) strParasInOut = strParasInOut.substring(0, strParasInOut.length() - 1);

				if (!strParasTemp.equals("")) // Clinical Parameters
				{
					if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)
							|| _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK)) mpChartData = chartDtlDAO
							.getDynamicChartDataTemplateIPD(_patDtlVO, strParasTemp, _criteria, _fromDate, _toDate, _userVO);
					else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK)) 
						mpChartData = chartDtlDAO.getDynamicChartDataTemplateOPD(_patDtlVO, strParasTemp, _criteria, _fromDate, _toDate, _userVO);
				}
				if (!strParasInv.equals("")) // Investigation Parameters
				{
					mpChartData = chartDtlDAO.getDynamicChartDataInvestigation(mpChartData, _patDtlVO, strParasInv, _criteria, _fromDate, _toDate,
							_userVO);
				}
				if (!strParasInOut.equals("")) // Intake Output Parameters
				{
					mpChartData = chartDtlDAO.getDynamicChartDataIntakeOutput(mpChartData, _patDtlVO, strParasInOut, _criteria, _fromDate, _toDate,
							_userVO);
				}
			}
			else if (_chartVO.getGenerationType().equals(OpdConfig.CHART_GENERATION_TYPE_ROW_WISE)) // Row Wise Mode
			{
				Map<Object, Object> mpTemp = chartDtlDAO.getDynamicChartRowWiseData(_chartVO.getBodyQuery(), _patDtlVO, _fromDate, _toDate, _userVO);
				mpColHead = (LinkedHashMap<String, ChartColumnHead>) mpTemp.get("Header");
				mpChartData = (LinkedHashMap<String, Map<String, ChartCellData>>) mpTemp.get("Data");
			}

			mp.put(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL, mpColHead);
			mp.put(OpdConfig.OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL, mpChartData);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}

	/**
	 * Getting Column Chart Paramter List
	 * 
	 * @param _chartVO Chart Detail
	 * @param _userVO User Detail
	 * @return List Chart Paras
	 */
	public List<ChartParameterMappingVO> getChartParamtersList(ChartMasterVO _chartVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<ChartParameterMappingVO> lstChartParas = new ArrayList<ChartParameterMappingVO>();
		try
		{
			tx.begin();
			if (_chartVO.getGenerationType().equals(OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE))
			{
				ChartMasterDAOi chartMstDAO = new ChartMasterDAO(tx);
				lstChartParas = chartMstDAO.getChartTempParas(_chartVO.getChartId(), _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstChartParas;
	}

	public EpisodeSummaryDetailVO[] getPatientOPDProgressNotes(EpisodeVO episodeVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeSummaryDetailVO[] episodeSummaryDtlVOs = null;
		try
		{
			tx.begin();
			EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
			List list = episodeSummaryDAO.getAllVisitSummaryByCrNo(episodeVO, null, "", userVO);
			if (list != null)
			{
				episodeSummaryDtlVOs = new EpisodeSummaryDetailVO[list.size()];
				episodeSummaryDtlVOs = (EpisodeSummaryDetailVO[]) list.toArray(episodeSummaryDtlVOs);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return episodeSummaryDtlVOs;
	}

	public void saveOPDPatsDiagnosisByICDEntryFormy(Map<String,List<EpisodeDiagnosisVO>> mapPatDiag_p, Set<String> setSeenPats_p, UserVO voUser_p)
	{
		/*JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			tx.begin();
			
			for(String patCrNo :  mapPatDiag_p.keySet())
			{
				List<EpisodeDiagnosisVO> lst = mapPatDiag_p.get(patCrNo);
				episodeDiagnosisDAO.removeAllEpiDiagnosis(lst.get(0), voUser_p);
				
				for (EpisodeDiagnosisVO voEpiDiag : lst)
				{
					voEpiDiag.setSeatId(voUser_p.getSeatId());
					episodeDiagnosisDAO.create(voEpiDiag, voUser_p);
					
					DocumentMasterVO voDoc = new DocumentMasterVO();
					HelperMethods.populate(voDoc, voEpiDiag);
					voDoc.setStrICDStatusFlag(OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_READ_AND_ICD);
					opdEssentialDAO.updateScanDocICDStatusFlag(voDoc, voUser_p);
				}
			}
			for(String value :  setSeenPats_p)
			{
				DocumentMasterVO voDoc = new DocumentMasterVO();
				voDoc.setPatCrNo(value.split("#")[0]);
				voDoc.setEpisodeCode(value.split("#")[1]);
				voDoc.setEpisodeVisitNo(value.split("#")[2]);
				voDoc.setStrICDStatusFlag(OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_READ_NOT_ICD);
				opdEssentialDAO.updateScanDocICDStatusFlag(voDoc, voUser_p);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}*/

	}
	public Map getDischargeProfileEssentials(String _deptUnitCode, String patCrNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List listDischargeType = null;
		List listAdviceBy = null;
		List listDepartemnts = null;
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);

			
			listDischargeType = opdEssentialDAO.getDischargeTypeList(_userVO);
			mp.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_TYPE_LIST, listDischargeType);
			
			listAdviceBy = opdEssentialDAO.getConsultantList(_deptUnitCode, _userVO);
			mp.put(OpdConfig.PATIENT_PROFILE_DISCHARGE_ADVICED_BY_LIST, listAdviceBy);
			
			listDepartemnts = opdEssentialDAO.getAllClinicalDepartmentList(_userVO);
			mp.put(OpdConfig.OPD_ESSENTIALBO_OPTION_DEPARTMENT, listDepartemnts);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	/*Functions Added By Pawan Kumar B N*/
	
	public void savePatientComplaintsDtl(List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EpisodeExtInvDtlDAOi dao = new EpisodeExtInvDtlDAO(tx);
			for (int i = 0; i < lstEpiExtInvDtlVO.size(); i++)
			{
				dao.savePatientComplaintsDtl(lstEpiExtInvDtlVO.get(i), userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}
	
	public EpisodeExtInvDtlVO[] getAddedPatientComplaintDetail(String patCrNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeExtInvDtlVO[] arrAddedExtInvVO = null;

		try
		{
			tx.begin();
			EpisodeExtInvDtlDAOi dao = new EpisodeExtInvDtlDAO(tx);
			arrAddedExtInvVO = dao.getAddedPatientComplaintsDetail(patCrNo, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrAddedExtInvVO;
	}
	
	public  List getEssentialDiagnosisDetail(EpisodeDiagnosisVO previousDiagVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstParameter = null;
		try
		{

			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			tx.begin();
			lstParameter=	episodeDiagnosisDAO.getEssentialDiagnosisDetail(previousDiagVO, userVO);
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstParameter;
	}

	/**
	 * Getting Episode External Investigation for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Patient External Investigation Detail VOs
	 */
	/**
## 		Modification Log		: added getPatientOutTakeDetail				
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/
	public PatIntakeOutDtlVO[] getPatientOutTakeDetail(String _crNo, PatientDetailVO voDP, String profileGenerationMode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatIntakeOutDtlVO[] arr = null;
		HisDAO hisDAO = new HisDAO("Opd", "OpdPatientBO");	
		try
		{
			EMRPatientDetailDAO dao = new EMRPatientDetailDAO();
			EpisodeExtInvDtlVO vo = new EpisodeExtInvDtlVO();
			arr = dao.getPatientOutTakeDetail(hisDAO,_crNo, voDP, profileGenerationMode, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if(hisDAO!=null){
				hisDAO.free();
			}
			hisDAO=null;
			tx.close();
		}
		return arr;
	}
	
	//AddedBy: NehaRajgariya Date:28July2016
	public  String getIcdHospCode(String deptUnitCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String deptUnitCodes = null;
		try
		{

			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			tx.begin();
			deptUnitCodes =	episodeDiagnosisDAO.getIcdHospCode(deptUnitCode,userVO);
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return deptUnitCodes;
	}

	// added by VASU on 06-Nov-2017
		public ProfileInvestigationVO[] getTestInvestigationDetailProfilePatientEpisodes(String _crNo, String _episodeCode, String _admissionNo,
				String _deskType, UserVO _userVO,String age,String _reqDno)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			ProfileInvestigationVO[] profileInvestigationVOs = null;
			try
			{
				PatientProfileDetailDAOi daoPatProfile = new PatientProfileDetailDAO(tx);
				// EpisodeDiagnosisDAOi episodeDiagnosisiDAOi = new EpisodeDiagnosisDAO(tx);
				tx.begin();
				if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
				{
					profileInvestigationVOs = daoPatProfile.retrieveTestIPDInvestigationForProfile(_crNo, _episodeCode, _userVO,age,_reqDno);
					
				}
				else //if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK))
				{
					profileInvestigationVOs = daoPatProfile.retrieveTestIPDInvestigationForProfile(_crNo, _episodeCode, _userVO,age,_reqDno);
					//profileInvestigationVOs = daoPatProfile.retrieveTestOPDInvestigationForProfile(_crNo, _episodeCode, _userVO,age,_reqDno);
				}

			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				tx.close();
			}
			return profileInvestigationVOs;
		}
	
		//Added by Vasu on 05.Nov.2018
		
		public static String savePatientProfileDtl(EHR_PatientProfileDtlVO _patProfileDtlVO, UserVO _userVO)
		{
			String profileId = null;
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO objHisDAO = null;
			String existingPatProfileId = null;
			try
			{
				objHisDAO = new HisDAO("OPD","OPDPatientBO");
				PatientProfileDetailDAO dao = new PatientProfileDetailDAO(tx);
				//PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
				OpdEssentialDAO essentialDAO = new OpdEssentialDAO(tx);
				tx.begin();
				
				existingPatProfileId = dao.getExistingPatientProfileId(_patProfileDtlVO, _userVO);
				//existingPatProfileId = Integer.toString(existingProfileId);
				
				_patProfileDtlVO.setProfileId(existingPatProfileId);
                
				// to insert data in pat profile detail
				
				profileId = dao.savePatientProfileDtl(_patProfileDtlVO, _userVO);
				
				
				if(existingPatProfileId!=null)
				{
					dao.updateProfileDetail(_patProfileDtlVO, _userVO);
				}
				
				/*synchronized (objHisDAO) {
					objHisDAO.fire();
				}*/
				
                //patientImageDtlDAO.savePatProfileToPostgreS(objHisDAO,_patProfileDtlVO,_patProfileDtlVO.getProfileDataPdf(),_userVO);
				essentialDAO.savePatProfileToPostgreS(objHisDAO,_patProfileDtlVO,_patProfileDtlVO.getProfileDataPdf(),_userVO);
				
			}
			catch (HisApplicationExecutionException e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				if(objHisDAO!=null){
					objHisDAO.free();
				}
				objHisDAO=null;
				tx.close();
				
			}
			return profileId;
		}

		
		// Added by Dheeraj on 05-Dec-2018 to fetch Profile from Postgres
		public static byte[] fetchPatientProfile(PatientProfileDetailVO _patProfileDtlVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO objHisDAO = null;
			byte[] getDoc =null;
			try
			{
				objHisDAO = new HisDAO("OPD","PatientBO");
				PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
				tx.begin();
				
				 getDoc= dao.fetchPatientProfile(_patProfileDtlVO);
				

			}
			catch (HisApplicationExecutionException e)
			{
				
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				if(objHisDAO!=null){
					objHisDAO.free();
				}
				objHisDAO=null;
				tx.close();
			}
		return getDoc;
		}
		
		
		public static String fetchHtmlPatientProfile(PatientProfileDetailVO _patProfileDtlVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO objHisDAO = null;
			String htmlData = "";
			try
			{
				objHisDAO = new HisDAO("OPD","PatientBO");
				PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
				tx.begin();
				
				htmlData= dao.fetchHtmlPatientProfile(_patProfileDtlVO);
				

			}
			catch (HisApplicationExecutionException e)
			{
				
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				if(objHisDAO!=null){
					objHisDAO.free();
				}
				objHisDAO=null;
				tx.close();
			}
		return htmlData;
		}
		
		//Added by Vasu on 16.Apr.2019
		public Map getPatientProfileFooterEssentials(PatientDetailVO voDp, UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp = new HashMap();
			List listDischargeType = null;
			List listAdviceBy = null;
			List listDepartemnts = null;
			List<EpisodeVO> lstEpisodeDtl = new ArrayList<EpisodeVO>();
			try
			{
				tx.begin();
				OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);

                lstEpisodeDtl = opdEssentialDAO.retrieveAllVisitsByEpisodeNo(voDp, _userVO);
                mp.put(OpdConfig.PATIENT_PROFILE_FOOTER_ESSENTIALS, lstEpisodeDtl);
							
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return mp;
		}

		//Added by Vasu on 30.May.2019
		public static byte[] fetchPDFFromPostgres(PatientProfileDetailVO patProfileDtlVO)
		{
			
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO objHisDAO = null;
			byte[] getDoc =null;
			try
			{
				objHisDAO = new HisDAO("OPD","PatientBO");
				OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
				tx.begin();
				
				 getDoc= opdEssentialDAO.getLatestPDFFromPostgres(patProfileDtlVO);
				

			}
			catch (HisApplicationExecutionException e)
			{
				
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				if(objHisDAO!=null){
					objHisDAO.free();
				}
				objHisDAO=null;
				tx.close();
			}
		return getDoc;
		}
		
		public byte[] fetchImageFromPostgres(String imgCode)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO objHisDAO = null;
			byte[] getDoc =null;
			try
			{
				objHisDAO = new HisDAO("OPD","PatientBO");
				OpdPatientImageDtlDAO opdPatientImageDtlDAO = new OpdPatientImageDtlDAO(tx);
				tx.begin();
				
				 getDoc= opdPatientImageDtlDAO.latestFetchImagePostgres(imgCode);
				

			}
			catch (HisApplicationExecutionException e)
			{
				
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				if(objHisDAO!=null){
					objHisDAO.free();
				}
				objHisDAO=null;
				tx.close();
			}
		return getDoc;
			
		}
		
		
		public byte[] viewImageFromPostgres(OpdPatientImageDtlVO vo)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO objHisDAO = null;
			byte[] getDoc =null;
			try
			{
				objHisDAO = new HisDAO("OPD","PatientBO");
				OpdPatientImageDtlDAO opdPatientImageDtlDAO = new OpdPatientImageDtlDAO(tx);
				tx.begin();
				
				 getDoc= opdPatientImageDtlDAO.viewImageFromPostgres(vo);
				

			}
			catch (HisApplicationExecutionException e)
			{
				
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				if(objHisDAO!=null){
					objHisDAO.free();
				}
				objHisDAO=null;
				tx.close();
			}
		return getDoc;
			
		}
}
