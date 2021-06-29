package ehr.patientTile.dataentry;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.dao.GenericTemplateEssentialDAO;
import hisglobal.utility.generictemplate.dao.GenericTemplateEssentialDAOi;
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.ANCLogDetailVO;
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.ANCVisitDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DoctorWardRoundDtlVO;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.HealthWorkerDetailVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.JsyRuleMasterVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.MonitoringModeMstVO;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ReactionParaDtlVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.dao.ANCChildHandoverDtlDAO;
import inpatient.transaction.dao.ANCChildHandoverDtlDAOi;
import inpatient.transaction.dao.ANCDeliveryDtlDAO;
import inpatient.transaction.dao.ANCDeliveryDtlDAOi;
import inpatient.transaction.dao.ANCDtlDAO;
import inpatient.transaction.dao.ANCDtlDAOi;
import inpatient.transaction.dao.ANCHistoryDeliveryDtlDAO;
import inpatient.transaction.dao.ANCHistoryDeliveryDtlDAOi;
import inpatient.transaction.dao.ANCHistoryDtlDAO;
import inpatient.transaction.dao.ANCHistoryDtlDAOi;
import inpatient.transaction.dao.ANCLogDtlDAO;
import inpatient.transaction.dao.ANCLogDtlDAOi;
import inpatient.transaction.dao.ANCNeonatalApgarDtlDAO;
import inpatient.transaction.dao.ANCNeonatalApgarDtlDAOi;
import inpatient.transaction.dao.ANCNeonatalDtlDAO;
import inpatient.transaction.dao.ANCNeonatalDtlDAOi;
import inpatient.transaction.dao.ANCTrimesterChklistDtlDAO;
import inpatient.transaction.dao.ANCTrimesterChklistDtlDAOi;
import inpatient.transaction.dao.ANCVisitDtlDAO;
import inpatient.transaction.dao.ANCVisitDtlDAOi;
import inpatient.transaction.dao.BloodTransfusionDAO;
import inpatient.transaction.dao.BloodTransfusionDAOi;
import inpatient.transaction.dao.DoctorCallAcknowledgeDAO;
import inpatient.transaction.dao.DoctorCallAcknowledgeDAOi;
import inpatient.transaction.dao.DoctorCallBookDAO;
import inpatient.transaction.dao.DoctorRoundDtlDAO;
import inpatient.transaction.dao.DoctorRoundDtlDAOi;
import inpatient.transaction.dao.DoctorWardRoundDtlDAO;
import inpatient.transaction.dao.DoctorWardRoundDtlDAOi;
import inpatient.transaction.dao.DrugAdminDtlDAO;
import inpatient.transaction.dao.DrugAdminDtlDAOi;
import inpatient.transaction.dao.DrugReactionDAO;
import inpatient.transaction.dao.DrugReactionDtlDAOi;
import inpatient.transaction.dao.ExtAdminDtlDAO;
import inpatient.transaction.dao.ExtAdminDtlDAOi;
import inpatient.transaction.dao.InPatientEssentialDAO;
import inpatient.transaction.dao.InPatientEssentialDAOi;
import inpatient.transaction.dao.JSYRegitrationDAO;
import inpatient.transaction.dao.JSYRegitrationDAOi;
import inpatient.transaction.dao.MonitorVitalsDtlDAO;
import inpatient.transaction.dao.NurseRoundDtlDAO;
import inpatient.transaction.dao.NurseRoundDtlDAOi;
import inpatient.transaction.dao.PatDischargeRequestDAO;
import inpatient.transaction.dao.PatDischargeRequestDAOi;
import inpatient.transaction.dao.PatIntakeOutDtlDAO;
import inpatient.transaction.dao.PatIntakeOutDtlDAOi;
import inpatient.transaction.dao.ReactionParaDetailDAO;
import inpatient.transaction.dao.ReactionParaDetailDAOi;
import inpatient.transaction.dao.StockEntryOfBloodDtlDAO;
import inpatient.transaction.dao.StockEntryOfBloodDtlDAOi;
import inpatient.transaction.dao.TransfusionReactionDtlDAO;
import inpatient.transaction.dao.TransfusionReactionDtlDAOi;
import inpatient.transaction.dao.TransfusionReactionParaDtlDAO;
import inpatient.transaction.dao.TransfusionReactionParaDtlDAOi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import opd.OpdConfig;
import opd.dao.PatientClinicalDetailDAO;
import opd.dao.PatientClinicalDetailDAOi;
import registration.RegistrationConfig;
import registration.dao.AddressDAO;
import registration.dao.DailyPatientDAO;
import registration.dao.DailyPatientDAOi;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeDeathDAO;
import registration.dao.EpisodeDeathDAOi;
import registration.dao.PatientDAO;
import registration.dao.PatientDAOi;
import registration.dao.SecondaryCategoryChangeDAO;

public class EHRSection_PatientTileBO implements EHRSection_PatientTileBOi
{
	public PatientDetailVO getInpatientDetailByCrNoNAdmNo(String deskType, PatientDetailVO patDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDetailVO patientDetailVO = null;
		AddressVO addressVO = null;
		boolean check=false;	
		try
		{
			tx.begin();
			EHRSection_PatientTileDAOi inpatientDAO=new EHRSection_PatientTileDAO(tx);
			AddressDAO addressDAO = new AddressDAO(tx);
			
			deskType=null;// added on 2011.03.09 By Pragya
			if(deskType==null)
			{
				//check Patient Status
				/*patStatus=inpatientDAO.checkPatientAdmStatus(patDtlVO,userVO);
				if(patStatus.equals(RegistrationConfig.PATIENT_STATUS_CODE_ADMITTED))
				{
					//IPD Patient Tile
					patDtlVO=inpatientDAO.getIpdPatientDtlByCrNo(patDtlVO, userVO);
					patDtlVO.setIsIpd(InpatientConfig.DESK_IPD_TILE);
				}
				else
				{
					//OPD Patient Tile
					patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
				}*/
				patDtlVO.setIsIpd(null);// added on 2011.03.09 By Pragya
				if(patDtlVO.getIsIpd()!=null && (patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE) || patDtlVO.getIsIpd().equals(InpatientConfig.DESK_OPD_TILE)))
				{
					if(patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))
						check = true;
					else
						check = false;
				}					
				else
					check=inpatientDAO.checkPatAdmitted(patDtlVO,userVO);
				if(check)
				{
					//IPD Patient Tile
					patientDetailVO=inpatientDAO.getIpdPatientDtlByCrNo(patDtlVO, userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_IPD_TILE);					
				}
				else
				{
					//OPD Patient Tile
					patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
				}
			}
			else
			{
				if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK) 
						|| deskType.equals(DynamicDeskConfig.DESK_TYPE_HELP_DESK))
				{
					if(patDtlVO.getIsIpd()!=null && (patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE) || patDtlVO.getIsIpd().equals(InpatientConfig.DESK_OPD_TILE)))
					{
						if(patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))
							check = true;
						else
							check = false;
					}					
					else
						check=inpatientDAO.checkPatAdmitted(patDtlVO,userVO);
					if(check)
					{
						//IPD Patient Tile
						patientDetailVO=inpatientDAO.getIpdPatientDtlByCrNo(patDtlVO, userVO);
						patientDetailVO.setIsIpd(InpatientConfig.DESK_IPD_TILE);
					}	
					else
					{
						//OPD Patient Tile
						patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
						patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
					}
				}
				else
				{
					//OPD Patient Tile
					patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
				}
			}
			
			if(patientDetailVO!=null && patientDetailVO.getPatCrNo()!=null && !patientDetailVO.getPatCrNo().equals(""))
			{
				patientDetailVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);		
				//System.out.println("patTyprCode  before populating"+patientDetailVO.getPatAddTypeCode());
				PatientVO patientVO = new PatientVO();
				HelperMethods.populate(patientVO, patientDetailVO);
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				//System.out.println("patTyprCode after populating"+patientVO.getPatAddTypeCode());
				try
				{
					addressVO = addressDAO.retrieveByCrNo(patientVO, userVO);
				}
				catch (HisRecordNotFoundException e)
				{
					addressVO = null;
				}
				if(addressVO != null)
				{
					patientDetailVO.setPatAddress(addressVO);
					HelperMethods.populatetToNullOrEmpty(patientDetailVO, addressVO);
				}
			}
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patientDetailVO;
	}
	
	public MlcVO getMlcNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		MlcVO mlcVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeDAO episodeDao = new EpisodeDAO(tx);
		PatientVO patVO=new PatientVO();
		
		try
		{
			tx.begin();
			HelperMethods.populate(patVO, patDtlVO);
			String mlcNumber="";
			if(patVO.getPatStatusCode() !=null && patVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
			{
				mlcNumber = episodeDao.isDeadPatientMLC(patVO, userVO);
			}
			else
			{
				mlcNumber = episodeDao.isPatientMLC(patVO, userVO);
			}
			if (mlcNumber != null || !(mlcNumber.equals("")))
			{
				mlcVO = new MlcVO();
				mlcVO.setPatCrNo(patDtlVO.getPatCrNo());
				mlcVO.setMlcNo(mlcNumber);
			}
		}
		finally
		{
			tx.close();
		}

		return mlcVO;
	}
	
	
	
	
	public PatientDetailVO getInpatientDiscDetailByCrNoNAdmNo(String deskType, PatientDetailVO patDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDetailVO patientDetailVO = null;
		AddressVO addressVO = null;
		boolean check=false;	
		try
		{
			tx.begin();
			EHRSection_PatientTileDAOi inpatientDAO=new EHRSection_PatientTileDAO(tx);
			AddressDAO addressDAO = new AddressDAO(tx);
			
			deskType=null;// added on 2011.03.09 By Pragya
			if(deskType==null)
			{
				//check Patient Status
				/*patStatus=inpatientDAO.checkPatientAdmStatus(patDtlVO,userVO);
				if(patStatus.equals(RegistrationConfig.PATIENT_STATUS_CODE_ADMITTED))
				{
					//IPD Patient Tile
					patDtlVO=inpatientDAO.getIpdPatientDtlByCrNo(patDtlVO, userVO);
					patDtlVO.setIsIpd(InpatientConfig.DESK_IPD_TILE);
				}
				else
				{
					//OPD Patient Tile
					patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
				}*/
				patDtlVO.setIsIpd(null);// added on 2011.03.09 By Pragya
				if(patDtlVO.getIsIpd()!=null && (patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE) || patDtlVO.getIsIpd().equals(InpatientConfig.DESK_OPD_TILE)))
				{
					if(patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))
						check = true;
					else
						check = false;
				}					
				else
					check=inpatientDAO.checkPatAdmitted(patDtlVO,userVO);
				if(check)
				{
					//IPD Patient Tile
					patientDetailVO=inpatientDAO.getIpdDiscPatientDtlByCrNo(patDtlVO, userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_IPD_TILE);					
				}
				else
				{
					//OPD Patient Tile
					patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
				}
			}
			else
			{
				if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK) 
						|| deskType.equals(DynamicDeskConfig.DESK_TYPE_HELP_DESK))
				{
					if(patDtlVO.getIsIpd()!=null && (patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE) || patDtlVO.getIsIpd().equals(InpatientConfig.DESK_OPD_TILE)))
					{
						if(patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE))
							check = true;
						else
							check = false;
					}					
					else
						check=inpatientDAO.checkPatAdmitted(patDtlVO,userVO);
					if(check)
					{
						//IPD Patient Tile
						patientDetailVO=inpatientDAO.getIpdPatientDtlByCrNo(patDtlVO, userVO);
						patientDetailVO.setIsIpd(InpatientConfig.DESK_IPD_TILE);
					}	
					else
					{
						//OPD Patient Tile
						patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
						patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
					}
				}
				else
				{
					//OPD Patient Tile
					patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
					patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
				}
			}
			
			if(patientDetailVO!=null && patientDetailVO.getPatCrNo()!=null && !patientDetailVO.getPatCrNo().equals(""))
			{
				patientDetailVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);		
				//System.out.println("patTyprCode  before populating"+patientDetailVO.getPatAddTypeCode());
				PatientVO patientVO = new PatientVO();
				HelperMethods.populate(patientVO, patientDetailVO);
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				//System.out.println("patTyprCode after populating"+patientVO.getPatAddTypeCode());
				try
				{
					addressVO = addressDAO.retrieveByCrNo(patientVO, userVO);
				}
				catch (HisRecordNotFoundException e)
				{
					addressVO = null;
				}
				if(addressVO != null)
				{
					patientDetailVO.setPatAddress(addressVO);
					HelperMethods.populatetToNullOrEmpty(patientDetailVO, addressVO);
				}
			}
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patientDetailVO;
	}
	
	
	
}