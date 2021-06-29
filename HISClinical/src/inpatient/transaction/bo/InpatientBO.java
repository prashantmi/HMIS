package inpatient.transaction.bo;


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
import inpatient.transaction.dao.InpatientDAO;
import inpatient.transaction.dao.InpatientDAOi;
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

public class InpatientBO implements InpatientBOi
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
			InpatientDAOi inpatientDAO=new InpatientDAO(tx);
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
				System.out.println("patTyprCode  before populating"+patientDetailVO.getPatAddTypeCode());
				PatientVO patientVO = new PatientVO();
				HelperMethods.populate(patientVO, patientDetailVO);
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				System.out.println("patTyprCode after populating"+patientVO.getPatAddTypeCode());
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
	
	
	
	
	public PatientDetailVO getMRDPatientDtlByCrNo(String deskType, PatientDetailVO patDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDetailVO patientDetailVO = null;
		AddressVO addressVO = null;
		boolean check=false;	
		try
		{
			tx.begin();
			InpatientDAOi inpatientDAO=new InpatientDAO(tx);
			AddressDAO addressDAO = new AddressDAO(tx);
			
			deskType=null;// added on 2011.03.09 By Pragya
			if(deskType==null)
			{
				
						patientDetailVO=inpatientDAO.getMRDPatientDtlByCrNo(patDtlVO, userVO);
						patientDetailVO.setIsIpd(InpatientConfig.DESK_IPD_TILE);
						
					
						//OPD Patient Tile
						//patientDetailVO=inpatientDAO.getOpdPatientDtlByCrNo(patDtlVO,userVO);
						//patientDetailVO.setIsIpd(InpatientConfig.DESK_OPD_TILE);
					
				
			}
			
			if(patientDetailVO!=null && patientDetailVO.getPatCrNo()!=null && !patientDetailVO.getPatCrNo().equals(""))
			{
				patientDetailVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);		
				System.out.println("patTyprCode  before populating"+patientDetailVO.getPatAddTypeCode());
				PatientVO patientVO = new PatientVO();
				HelperMethods.populate(patientVO, patientDetailVO);
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				System.out.println("patTyprCode after populating"+patientVO.getPatAddTypeCode());
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
	
	public void saveNurseProgNotes(NurseRoundDtlVO nurseRoundDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			NurseRoundDtlDAOi nurseRoundDAO= new NurseRoundDtlDAO(tx);
			nurseRoundDAO.create(nurseRoundDtlVO, userVO);
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
	}
	
	public void saveDoctorVisitNotes(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			doctorRoundDAO.create(docRoundDtlVO, userVO);
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
	}
	
	public DoctorRoundDtlVO[] getDoctorInstruction(String patAdmNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorRoundDtlVO[] arrDocInstrVO=null;
		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			arrDocInstrVO=doctorRoundDAO.getDoctorInstruction(patAdmNo,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrDocInstrVO;
	}
	
	public DoctorRoundDtlVO[] getUnverifiedEntryByNurse(String patAdmNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorRoundDtlVO[] arrunverifiedRecordVO=null;
		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			arrunverifiedRecordVO=doctorRoundDAO.getUnverifiedEntryByNurse(patAdmNo,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrunverifiedRecordVO;
	}
	
	public DoctorRoundDtlVO getRecordDetail(DoctorRoundDtlVO unverifiedRecordVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorRoundDtlVO recordDtlVO;
		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			recordDtlVO=doctorRoundDAO.getRecordDetail(unverifiedRecordVO,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return recordDtlVO;
		
		
	}

	
	
	
	public void saveNVerifyNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			doctorRoundDAO.updateVerifiedData(docRoundDtlVO, userVO);
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
	}
	
	public void saveNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			doctorRoundDAO.saveNotesByDoctor(docRoundDtlVO, userVO);
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
	}
	
	public void saveOutParameter(List<PatIntakeOutDtlVO> listPatIntakeOutTake,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
			for(int i=0;i<listPatIntakeOutTake.size();i++)
			{
				patIntakeOutDAO.saveOutParameter(listPatIntakeOutTake.get(i),userVO);
			}
			
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
	}
	
	public PatIntakeOutDtlVO[] getOutParaDetail(String mode,PatientDetailVO dailyPatVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatIntakeOutDtlVO[] arrPatOuttakeDtlVO=null;
		
		try
		{
			tx.begin();
			PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
			arrPatOuttakeDtlVO=patIntakeOutDAO.getOutParaDetail(mode,dailyPatVO,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrPatOuttakeDtlVO;
	}



	public Map getBulletinDetails(PatientDetailVO patDtlVO, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List patientStatusList=new ArrayList();
		PatientBulletinDetailVO[] patientBulletinVO=null;
		try
		{
			tx.begin();
			
			InpatientDAOi inpatientDAO=new InpatientDAO(tx);
			
			patientStatusList=inpatientDAO.getPatientStatus( userVO);
			essentialMap.put(InpatientConfig.INPATIENT_STATUS_LIST,patientStatusList);
			
			patientBulletinVO=inpatientDAO.getEssentialBulletinDetails(patDtlVO, userVO);
		    essentialMap.put(InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO, patientBulletinVO);	
			  
			  
		}
		catch(HisRecordNotFoundException e)
		{
			
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
		
		return essentialMap;
	}

	public void saveAndUpdatePatientBulletinDetails(PatientBulletinDetailVO _oldPatientBulletinVO,PatientBulletinDetailVO _newPatientBulletinVO, UserVO userVO,
			String revoke) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			InpatientDAOi inpatientDAO=new InpatientDAO(tx);
			
			
	if(_oldPatientBulletinVO.getBulletinSerialNo()==null || _oldPatientBulletinVO.getBulletinSerialNo().equals("") || _oldPatientBulletinVO.getEffectiveToDate()!=null)
			{	
		inpatientDAO.savePatientBulletinDetails(_newPatientBulletinVO, userVO);
			
			} 
			else			
	if(revoke.equals("1") && _oldPatientBulletinVO.getEffectiveToDate()==null)
	{	
		inpatientDAO.updatePatientBulletinDetails(_oldPatientBulletinVO, userVO);
	
	} 
	else		
    if(_oldPatientBulletinVO.getEffectiveToDate()==null)
    {
    	inpatientDAO.savePatientBulletinDetails(_newPatientBulletinVO, userVO);		
		inpatientDAO.updatePatientBulletinDetails(_oldPatientBulletinVO, userVO);
     }
			
	
			  
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
	}
	
	//saving inpatient detail
	
	public  void saveVitalsDetail(PatientMonitoringMstVO[] patMonitringMstVO,PatientMonitoringMstVO[] modPatMonitringMstVO,UserVO userVO)
	{
			JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MonitorVitalsDtlDAO monitorVitalsDtlDAO=new MonitorVitalsDtlDAO(tx);
			
			if(patMonitringMstVO!=null)
			{
				for(int i=0;i<patMonitringMstVO.length;i++)
				{
					monitorVitalsDtlDAO.saveVitalsDetail(patMonitringMstVO[i],userVO);
				}
			}
			
			
			if(modPatMonitringMstVO!=null)
			{
				for(int i=0;i<modPatMonitringMstVO.length;i++)
				{
					monitorVitalsDtlDAO.updateVitalsDetail(modPatMonitringMstVO[i],userVO);
				}
			}
			
			
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
	}
	
	public PatientMonitoringMstVO[] getVitalDetail(PatientMonitoringMstVO patMonitringMstVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientMonitoringMstVO[] _patMonitringMstVO=null;
		
		try
		{
			tx.begin();
			MonitorVitalsDtlDAO monitorVitalsDtlDAO=new MonitorVitalsDtlDAO(tx);
			_patMonitringMstVO=monitorVitalsDtlDAO.getVitalDetail(patMonitringMstVO,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return _patMonitringMstVO;
	}

	
	public NurseRoundDtlVO[] getPreviousProgressNotes(String patAdmNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		NurseRoundDtlVO[] arrNursePatPrevProgressNotes=null;
		
		try
		{
			tx.begin();
			NurseRoundDtlDAOi nurseRoundDAO= new NurseRoundDtlDAO(tx);
			arrNursePatPrevProgressNotes=nurseRoundDAO.getPreviousProgressNotes(patAdmNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrNursePatPrevProgressNotes;
	}
	


	/** Getting Doctor Prev Round Dtl on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getDoctorPrevRoundDetail(String patAdmNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorRoundDtlVO[] arrDocInstrVO=null;		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			arrDocInstrVO=doctorRoundDAO.getDoctorPrevRoundDetail(patAdmNo,userVO);			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrDocInstrVO;
	}
	
	public void revokeVitals(String paraId,PatientMonitoringMstVO patMonitoringVitalVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MonitorVitalsDtlDAO monitorVitalsDtlDAO=new MonitorVitalsDtlDAO(tx);
			monitorVitalsDtlDAO.revokeVitals(paraId,patMonitoringVitalVO,userVO);
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
	 * Getting Essentials for Monitoring Vitals
	 * @param _deskType Desk Type
	 * @param _userDeskMenuTempVO User Desk Menu Detail
	 * @param _patClinicalVO Patient Clinical Data VO
	 * @param patMonitringMstVO Pat Monitoring VO
	 * @param _userVO User VO
	 * @return Map of Essentials
	 */
	public Map<String, Object> getEssentailsForMonitoringVital(String _deskType, UserDeskMenuTemplateMasterVO _userDeskMenuTempVO, PatientClinicalDetailVO _patClinicalVO, PatientMonitoringMstVO _patMonitringMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> mpEssentials = new HashMap<String, Object>();
		try
		{
			tx.begin();
			
			PatientMonitoringMstVO[] patMonitringMstVOs = null;
			MonitorVitalsDtlDAO monitorVitalsDtlDAO = new MonitorVitalsDtlDAO(tx);
			patMonitringMstVOs = monitorVitalsDtlDAO.getVitalDetail(_patMonitringMstVO, _userVO);
			mpEssentials.put(InpatientConfig.SELECTED_PARAMETER_LIST, patMonitringMstVOs);
			
			MonitoringModeMstVO[] monitorModeMstVOs = null;			
			InPatientEssentialDAOi essentialDAO = new InPatientEssentialDAO(tx);
			monitorModeMstVOs = essentialDAO.getMonitorMode(_userVO);
			mpEssentials.put(InpatientConfig.ARR_MONITOR_MODE_VO, monitorModeMstVOs);
			
			// For Vital Chart			
			GenericTemplateEssentialDAOi dao =new GenericTemplateEssentialDAO(tx);
			List<Entry> lstTemplates = dao.getDeskMenuTemplateList(_deskType,_patClinicalVO.getPatCrNo(), _userDeskMenuTempVO, _userVO);

			List<Entry> lstReportDates = new ArrayList<Entry>();
			PatientClinicalDetailDAOi daoPatCliDtl = new PatientClinicalDetailDAO(tx);
			lstReportDates = daoPatCliDtl.getClinicalRecordDateList(_patMonitringMstVO.getAdmissionNo(),"" ,_userVO);
			if(lstReportDates== null) lstReportDates = new ArrayList<Entry>();

			Map<String, Map<String, Map<String, String>>> mpTempParaVals = new HashMap<String, Map<String,Map<String,String>>>();
			
			for(Entry e : lstReportDates)
			{
				PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliVO, _patClinicalVO);
				patCliVO.setRecordDate(e.getValue());
				Map<String, Map<String, String>> map = null;
				if(lstTemplates!=null && lstTemplates.size()>0)
				{
					//map = daoPatCliDtl.getPatientClinicalRecordDataTempWise(patCliVO, lstTemplates, _userVO);
					GenericTemplateUtility.TempParameter[] tempParas = daoPatCliDtl.getPatientClinicalRecordDataTempWise(patCliVO, lstTemplates, _userVO);
					
					map = new HashMap<String, Map<String,String>>();
					for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String,String> mpTemporary = null;
						if(map.get(paraValVO.getTemplateId())!=null)
							mpTemporary = map.get(paraValVO.getTemplateId());
						else
							mpTemporary = new HashMap<String, String>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO.getParaValue());
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}
				}
				if(map!=null && map.keySet().size()>0)	mpTempParaVals.put(e.getValue(), map);
			}			
			//Changed by Akash for New Desk on date 12-Feb-2015
			mpEssentials.put(GenericTemplateConfig.TEMPLATE_TILE_ALL_TEMPLATE_LIST+_patClinicalVO.getDeskMenuId(), lstTemplates);
			mpEssentials.put(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_RECORD_DATE_LIST, lstReportDates);
			mpEssentials.put(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_VALUES_MAP, mpTempParaVals);	
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
		return mpEssentials;
	}

	public void saveDischargeRequest(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status="";
		Boolean BillFlag;
		try
		{
			tx.begin();
			PatDischargeRequestDAOi patDischargeReqDAO=new PatDischargeRequestDAO(tx);
			InpatientDAOi inpatientDAO=new InpatientDAO(tx);
			
			patDischargeReqDAO.create(patDischargeReqVO,userVO);
			
			
			
			patDischargeReqVO.setAdviceBy(userVO.getUserEmpID());
			System.out.println("patstatus....."+patStatus);
			if(patStatus.equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED))
			{
				status=InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL;
				System.out.println("patstatus22....."+patStatus);
				inpatientDAO.updatePatStatusForDischargeReq(status,patDischargeReqVO,userVO);
				inpatientDAO.updatePatStatusForDischargeReqInPatADMDiscTable(status,patDischargeReqVO,userVO); //Added by Vasu on 27.Feb.2019
				
				//ADDED ON Date: 08.06.2017 pkg_bloodbank new function called 
				//patDischargeReqDAO.crossmatched_bag_revert(patDischargeReqVO,userVO); //Commented by Vasu on 29.Jan.2018
			}
			else
			{
				status=InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED;
				patDischargeReqVO.setIsDead("0");
				patDischargeReqVO.setNextVisitDate("");
				patDischargeReqVO.setDischargeStatus("");
				//patDischargeReqVO.setRemarks("");
				//patDischargeReqVO.setAdviceBy("");
				
				inpatientDAO.updatePatStatusForDischargeRevoke(status,patDischargeReqVO,userVO);				//patient Status =18				
				//BillFlag = inpatientDAO.GetBillAccStatus(patDischargeReqVO,userVO);
				//System.out.println(BillFlag);
				//if(BillFlag==true)
					//inpatientDAO.reopenPatBillingForDischargeRevoke(patDischargeReqVO,userVO);
				
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
	
	public boolean getPatientStatus(String admNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		
		try
		{
			tx.begin();
			InpatientDAOi inpatientDAO=new InpatientDAO(tx);
			flag=inpatientDAO.checkPatientStatus(admNo,userVO);
			
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
		return flag;
	}
	
	public PatDischargeReqDtlVO getDischargeRemarks(String admNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatDischargeReqDtlVO patDischargeReqVO;
		
		try
		{
			tx.begin();
			PatDischargeRequestDAOi patDischargeReqDAO=new PatDischargeRequestDAO(tx);
			patDischargeReqVO=patDischargeReqDAO.getDischargeRemarks(admNo,userVO);
			//added by swati sagar
			String dischargeStatus=patDischargeReqDAO.getDischargeStatus(admNo,userVO);
			System.out.println("dischargeStatus....."+dischargeStatus);
			patDischargeReqVO.setDischargeStatus(dischargeStatus);
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
		return patDischargeReqVO;
	}
	
	public void saveDrugTreatmentExe(List drugAdminDtlVOList,List ivFluidDrugAdminVOList,List sosDrugList,List patIntakeOutDtlVOList,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DrugAdminDtlDAOi drugAdminDtlDAO= new DrugAdminDtlDAO(tx);
			//PatIntakeOutDtlDAOi daoIntakeOutObj=new PatIntakeOutDtlDAO(tx);
			
			for(int i=0;i<drugAdminDtlVOList.size();i++)
			{
				DrugAdminDtlVO vo=(DrugAdminDtlVO)drugAdminDtlVOList.get(i);
				drugAdminDtlDAO.updateDrugExec(vo, _userVO);
			}
			
			for(int i=0;i<ivFluidDrugAdminVOList.size();i++)
			{
				DrugAdminDtlVO vo=(DrugAdminDtlVO)ivFluidDrugAdminVOList.get(i);
				drugAdminDtlDAO.updateIvFluidDrugExec(vo, _userVO);
			}
			
			if(sosDrugList!=null)
			{
				for(int i=0;i<sosDrugList.size();i++)
				{
					DrugAdminDtlVO vo=(DrugAdminDtlVO)sosDrugList.get(i);
					drugAdminDtlDAO.saveDrugAdminDetail(vo, _userVO);
				}
			}
//			// this is for insert data in in take out take dtl table
//			for(int i=0;i<patIntakeOutDtlVOList.size();i++)
//			{
//				PatIntakeOutDtlVO vo=(PatIntakeOutDtlVO)patIntakeOutDtlVOList.get(i);
//				daoIntakeOutObj.saveOutParameter(vo, _userVO);
//			}
			
			
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
	}
	
	public void saveExtTreatmentExe(List actvityList,List extAdminVoList ,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			
			ExtAdminDtlDAOi extAdminDtlDAO=new ExtAdminDtlDAO(tx);
			
			if(actvityList!=null)
			{
				for(int i=0;i<actvityList.size();i++)
				{
					ExtAdminDtlVO vo=(ExtAdminDtlVO)actvityList.get(i);
					extAdminDtlDAO.save(vo, _userVO);
				}
			}
			
			for(int i=0;i<extAdminVoList.size();i++)
			{
				ExtAdminDtlVO vo=(ExtAdminDtlVO)extAdminVoList.get(i);
				extAdminDtlDAO.updateTodayExtDetail(vo, _userVO);
			}
			
			
			
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
	}
	
	public void saveDrugReactionDtl(DrugReactionVO drugReactionVO,List templateParaList ,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DrugReactionDtlDAOi drugReactionDtlDAO= new DrugReactionDAO(tx);
			DrugAdminDtlDAOi drugAdminDtlDAO=new DrugAdminDtlDAO(tx);
			ReactionParaDetailDAOi reacitonParaDtlDAO=new ReactionParaDetailDAO(tx);
			
			drugAdminDtlDAO.updateIsReactionStatus(drugReactionVO, _userVO);
			Integer maxSlNo=new Integer(drugReactionDtlDAO.getMaxSlNo(drugReactionVO.getPatCrNo(), _userVO));
			maxSlNo++;
			drugReactionVO.setSerialNo(maxSlNo.toString());
			drugReactionDtlDAO.save(drugReactionVO, _userVO);
			
			Integer paraSerialNo=0;
			for(int i=0;i<templateParaList.size();i++)
			{
				ReactionParaDtlVO vo=(ReactionParaDtlVO)templateParaList.get(i);
				vo.setSerialNo(maxSlNo.toString());
				vo.setParaSerialNo(paraSerialNo.toString());
				reacitonParaDtlDAO.creat(vo, _userVO);
				paraSerialNo++;
			}
			
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
	}
	
	public PatIntakeOutDtlVO[] getIntakeSummary(String patCrNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatIntakeOutDtlVO[] arrPatIntakeSummaryVO=null;
		
		try
		{
			tx.begin();
			PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
			arrPatIntakeSummaryVO=patIntakeOutDAO.getIntakeSummary(patCrNo,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrPatIntakeSummaryVO;
	}
	
	public PatIntakeOutDtlVO[] getOuttakeSummary(String patCrNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatIntakeOutDtlVO[] arrPatOuttakeSummaryVO=null;
		
		try
		{
			tx.begin();
			PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
			arrPatOuttakeSummaryVO=patIntakeOutDAO.getOuttakeSummary(patCrNo,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrPatOuttakeSummaryVO;
	}

	
	/******************** getting all Bulletins ******************************************/
	
	public PatientBulletinDetailVO[] getAllAdmittedPatientListBulletin(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientBulletinDetailVO [] patientBulletinDtlVO=null;
		
		try
		{
			tx.begin();
			InPatientEssentialDAOi daoObj=new InPatientEssentialDAO(tx);
			patientBulletinDtlVO=daoObj.getAllAdmittedPatientListBulletin(userVO);
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
		return patientBulletinDtlVO;
	}

	public void saveExternalBloodStockDtl(PatBloodStockDtlVO[] patBloodStockDtlVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			StockEntryOfBloodDtlDAOi dao= new StockEntryOfBloodDtlDAO(tx);
			
			for(int i=0;i<patBloodStockDtlVO.length;i++)
			{
				dao.creat(patBloodStockDtlVO[i], _userVO);
			}
			
			
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
	}

	
	public boolean saveDoctorCallDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		try
		{
			tx.begin();

			DoctorCallBookDAO daoobj = new DoctorCallBookDAO(tx);
			
			
			//String macroID=daoobj.checkDuplicateMacroInfo(_macroMstVO, _UserVO);
		//	if(macroID==null)
			//{
				daoobj.saveDoctorCallDetails(_callBookVO, _UserVO);
			  hasFlag=true;
		//	}else{
			//	hasFlag=false;
			//} 
			
			
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
			
		}
		return hasFlag;
	}
	
	public boolean ModifyDoctorCallDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		try
		{
			tx.begin();

			DoctorCallBookDAO daoobj = new DoctorCallBookDAO(tx);
			
			
			//String macroID=daoobj.checkDuplicateMacroInfo(_macroMstVO, _UserVO);
		//	if(macroID==null)
			//{
				daoobj.ModifyDoctorCallDetails(_callBookVO, _UserVO);
			  hasFlag=true;
		//	}else{
			//	hasFlag=false;
			//} 
			
			
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
			
		}
		return hasFlag;
	}
	
	public List getDeptUnitList(UserVO userVO)
	{
		List lstUnit=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstUnit=essentialDAO.getAllUnitList(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstUnit;
	}
	
	public DoctorCallBookVO[] getCallBookDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO)
	{
		DoctorCallBookVO[] docCallBookVOArray=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorCallBookDAO daoi = new DoctorCallBookDAO(tx);

		try
		{ 
			tx.begin();
			docCallBookVOArray = daoi.getCallBookDetails(doctorCallBookVO,_userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
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

		return docCallBookVOArray;
	}

		public DoctorCallBookVO[] getAllCalls(DoctorCallBookVO doctorCallBookVO,UserVO _userVO)
	{
		DoctorCallBookVO[] docCallBookVOArray=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorCallBookDAO daoi = new DoctorCallBookDAO(tx);

		try
		{ 
			tx.begin();
			docCallBookVOArray = daoi.getAllCalls(doctorCallBookVO,_userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
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

		return docCallBookVOArray;
	}

	
	public DoctorCallBookVO[] getCallAcknowledgeDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO)
	{
		DoctorCallBookVO[] docCallBookVOArray=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorCallAcknowledgeDAOi daoi = new DoctorCallAcknowledgeDAO(tx);

		try
		{ 
			tx.begin();
			docCallBookVOArray = daoi.getCallAcknowledgeDetails(doctorCallBookVO,_userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
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

		return docCallBookVOArray;
	}	

	public DoctorCallBookVO[] getAllCallsAcknowledge(DoctorCallBookVO doctorCallBookVO,UserVO _userVO)
	{
		DoctorCallBookVO[] docCallBookVOArray=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorCallAcknowledgeDAOi daoi = new DoctorCallAcknowledgeDAO(tx);

		try
		{ 
			tx.begin();
			docCallBookVOArray = daoi.getAllCallsAcknowledge(doctorCallBookVO,_userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
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

		return docCallBookVOArray;
	}	

	
	public DoctorCallBookVO getCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO)
	{
		DoctorCallBookVO CallBookVO=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorCallBookDAO daoi = new DoctorCallBookDAO(tx);

		try
		{ 
			tx.begin();
			CallBookVO = daoi.getCallDetails(doctorCallBookVO,_userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
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

		return CallBookVO;
	}
	
	public boolean saveDoctorCallAcknowledgeDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag=true;
		try
		{
			tx.begin();

			DoctorCallAcknowledgeDAOi daoobj = new DoctorCallAcknowledgeDAO(tx);
			
			
			//String macroID=daoobj.checkDuplicateMacroInfo(_macroMstVO, _UserVO);
		//	if(macroID==null)
			//{
				daoobj.saveDoctorCallAcknowledgeDetails(_callBookVO, _UserVO);
			  hasFlag=true;
		//	}else{
			//	hasFlag=false;
			//} 
			
			
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
			
		}
		return hasFlag;
	}


	
	public void saveDoctorWardRoundDtl(DoctorWardRoundDtlVO _doctorWardRoundDtlVO,List callBookList, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DoctorCallBookDAO daoi = new DoctorCallBookDAO(tx);
			DoctorWardRoundDtlDAOi _doctorWardRoundDtlDAO=new DoctorWardRoundDtlDAO(tx);
			_doctorWardRoundDtlDAO.create(_doctorWardRoundDtlVO, _userVO);
			
			if(callBookList!=null)
			{
				int listCallBook=callBookList.size();
				for(int i=0;i<listCallBook;i++)
				{
					DoctorCallBookVO doctorCallBookVO = (DoctorCallBookVO)callBookList.get(i);
					
					doctorCallBookVO.setWardCode(_doctorWardRoundDtlVO.getWardCode());
					doctorCallBookVO.setRoundDate(_doctorWardRoundDtlVO.getRoundDate());
					daoi.updateRoundNoDetail(doctorCallBookVO, _userVO);
					
				}
			}
			
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
	}


	public void saveBloodTransfusionDtl(Map<String, TransfusionReactionDtlVO> mapTrasReaction,Map<String, List<TransfusionReactionParaDtlVO>> mapTrasReactionPara,List bloodTrasVOList,List patIntakeOutDtlVOList,String[] selectedBag,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			BloodTransfusionDAOi dao= new BloodTransfusionDAO(tx);
			StockEntryOfBloodDtlDAOi stockEntryOfBloodDtlDAO=new StockEntryOfBloodDtlDAO(tx);
			PatIntakeOutDtlDAOi intakeDao=new PatIntakeOutDtlDAO(tx);
			
			for(int i=0;i<bloodTrasVOList.size();i++)
			{
				stockEntryOfBloodDtlDAO.updateStockStatus((BloodTransfusionDtlVO)bloodTrasVOList.get(i), _userVO);
				dao.creat((BloodTransfusionDtlVO)bloodTrasVOList.get(i), _userVO);
			}
			
			for(int i=0;i<patIntakeOutDtlVOList.size();i++)
			{
				PatIntakeOutDtlVO patIntakeOutDtlVO=(PatIntakeOutDtlVO)patIntakeOutDtlVOList.get(i);
				intakeDao.saveOutParameter(patIntakeOutDtlVO, _userVO);
			}
			
			for(int i=0;i<selectedBag.length;i++)
			{
				if(mapTrasReaction != null && mapTrasReactionPara != null)
				{
				TransfusionReactionDtlVO voTrReaction = mapTrasReaction.get(selectedBag[i]);
				List<TransfusionReactionParaDtlVO> lstTrnReaPara = mapTrasReactionPara.get(selectedBag[i]);
									
					if(voTrReaction!=null) 
					{
						TransfusionReactionDtlDAOi transfusionReactionDtlDAO=new TransfusionReactionDtlDAO(tx);
						TransfusionReactionParaDtlDAOi transfusionReactionParaDtlDAO=new TransfusionReactionParaDtlDAO(tx);
						
						Integer maxReactionID=new Integer(transfusionReactionDtlDAO.getMaxSlNo(_userVO));
						maxReactionID++;
						voTrReaction.setReactionID(maxReactionID.toString());
						System.out.println("transfusionReactionDtlDAO:1");
						transfusionReactionDtlDAO.creat(voTrReaction, _userVO);
						
						System.out.println("transfusionReactionDtlDAO:2");
						Integer paraSerialNo=1;
						if(lstTrnReaPara!=null)
						for(int j=0;j<lstTrnReaPara.size();j++)
						{
							TransfusionReactionParaDtlVO vo=(TransfusionReactionParaDtlVO)lstTrnReaPara.get(j);
							vo.setReactionID(maxReactionID.toString());
							vo.setParaSerealNo(paraSerialNo.toString());
							transfusionReactionParaDtlDAO.creat(vo, _userVO);
							paraSerialNo++;
						}
						System.out.println("transfusionReactionDtlDAO:3");
					
					}
				}
			}
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
	}
	
	
	
	public void saveBloodTransReactionDtl(TransfusionReactionDtlVO transReactionDtlVO,List bloodTrasReactionParaVOList,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			TransfusionReactionDtlDAOi transReactionDtlDAO= new TransfusionReactionDtlDAO(tx);
			StockEntryOfBloodDtlDAOi stockEntryOfBloodDtlDAO=new StockEntryOfBloodDtlDAO(tx);
			TransfusionReactionParaDtlDAOi reacitonParaDtlDAO=new TransfusionReactionParaDtlDAO(tx);
			
			stockEntryOfBloodDtlDAO.updateIsReactionStatus(transReactionDtlVO, _userVO); 
			Integer maxReactionID=new Integer(transReactionDtlDAO.getMaxSlNo(_userVO));
			maxReactionID++;
			transReactionDtlVO.setReactionID(maxReactionID.toString());
			transReactionDtlDAO.creat(transReactionDtlVO, _userVO);
			
			
			Integer paraSerialNo=1;
			for(int i=0;i<bloodTrasReactionParaVOList.size();i++)
			{
				TransfusionReactionParaDtlVO vo=(TransfusionReactionParaDtlVO)bloodTrasReactionParaVOList.get(i);
				vo.setReactionID(maxReactionID.toString());
				vo.setParaSerealNo(paraSerialNo.toString());
				reacitonParaDtlDAO.creat(vo, _userVO);
				paraSerialNo++;
			}
			
			
			
			
			
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
	}

	/**
	 * Getting ANC Detail 
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map getANCDetails(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCDetailVO detailVO=null;
		ANCVisitDetailVO ancVisitDetailVO = new ANCVisitDetailVO();
		PatientDetailVO patDtlVO = null;
		List<ANCHistoryDetailVO> lstANCHist = null;
		List<ANCHistoryDeliveryDetailVO> lstANCHistDelivery = null;
		
		Map ancDetails = new HashMap();
		
		try
		{
			tx.begin();
			ANCDtlDAOi dao=new ANCDtlDAO(tx);
			ANCVisitDtlDAOi ancVisitDAO=new ANCVisitDtlDAO(tx);
			PatientDAOi patDao =  new PatientDAO(tx);
			ANCHistoryDtlDAOi ancHistDAO =  new ANCHistoryDtlDAO(tx);
			ANCHistoryDeliveryDtlDAOi ancHistDeliveryDAO =  new ANCHistoryDeliveryDtlDAO(tx);
			
			detailVO=dao.getANCDetail(_ancDetailVO, _userVO);
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL_FLAG, OpdConfig.YES);
			if(detailVO==null)
			{				
				ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL_FLAG, OpdConfig.NO);
				detailVO=dao.getANCBasicHistory(_ancDetailVO, _userVO);				
			}
			else
			{
				HelperMethods.populate(ancVisitDetailVO, _ancDetailVO);
				ancVisitDetailVO = ancVisitDAO.getANCVisitDetail(ancVisitDetailVO, _userVO);
			}
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL, detailVO);
			
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_VISIT_DETAIL, ancVisitDetailVO);
			
			patDtlVO = patDao.getPatientAdditionalInformation(_ancDetailVO.getPatCrNo(), _userVO);
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_DETAIL_FROM_HRGT, patDtlVO);
						
			lstANCHist = ancHistDAO.getANCHistoryDetail(_ancDetailVO.getPatCrNo(), _userVO);
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_HISTORY_DETAIL_LIST, lstANCHist);			

			lstANCHistDelivery = ancHistDeliveryDAO.getANCHistoryDeliveryDetail(_ancDetailVO.getPatCrNo(), _userVO);
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_HISTORY_DELIVERY_DETAIL_LIST, lstANCHistDelivery);			
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
		return ancDetails;
	}
	
	/**
	 * Saving ANC Detail
	 * @param _ancDetailVO
	 * @param _userVO
	 */
	public void saveANCDetail(ANCDetailVO _ancDetailVO, UserVO _userVO, JDBCTransactionContext tx)
	{
		//JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			//tx.begin();
			ANCDtlDAOi dao=new ANCDtlDAO(tx);
			dao.create(_ancDetailVO, _userVO);
			
			ANCLogDetailVO ancLogVO = new ANCLogDetailVO();
			HelperMethods.populate(ancLogVO, _ancDetailVO);			
			ANCLogDtlDAOi logDao = new ANCLogDtlDAO(tx);
			logDao.create(ancLogVO, _userVO);
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
			//tx.close();
		}
	}

	/**
	 * Updating ANC Detail
	 * @param _ancDetailVO
	 * @param _userVO
	 */
	public void updateANCDetail(ANCDetailVO _ancDetailVO, UserVO _userVO, JDBCTransactionContext tx)
	{
		//JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			//tx.begin();
			ANCDtlDAOi dao=new ANCDtlDAO(tx);
			dao.update(_ancDetailVO, _userVO);
			
			ANCLogDetailVO ancLogVO = new ANCLogDetailVO();
			HelperMethods.populate(ancLogVO, _ancDetailVO);			
			ANCLogDtlDAOi logDao = new ANCLogDtlDAO(tx);
			logDao.create(ancLogVO, _userVO);
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
			//tx.close();
		}
	}

	/**
	 * Saving ANC Visit Detail
	 * @param _ancVisitDetailVO ANc Visit Detail VO
	 * @param _userVO User Detail
	 * @param tx Transaction Context Object
	 */
	public void saveANCVisitDetail(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO, JDBCTransactionContext tx)
	{
		//JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			//tx.begin();
			ANCVisitDtlDAOi ancVisitDao=new ANCVisitDtlDAO(tx);
			ANCVisitDetailVO vo = ancVisitDao.getANCVisitDetail(_ancVisitDetailVO, _userVO);
			if(vo==null)
				ancVisitDao.create(_ancVisitDetailVO, _userVO);
			else
				ancVisitDao.update(_ancVisitDetailVO, _userVO);
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
			//tx.close();
		}
	}

	/**
	 * Getting ANC History Detail 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return List of ANC History
	 */
	public List<ANCHistoryDetailVO> getANCHistoryDetail(String _patCrNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<ANCHistoryDetailVO> lstANCHist=null;
		try
		{
			tx.begin();
			ANCHistoryDtlDAOi dao=new ANCHistoryDtlDAO(tx);
			lstANCHist = dao.getANCHistoryDetail(_patCrNo, _userVO);			
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
		return lstANCHist;
	}

	/**
	 * Saving Complete ANC Detail
	 * @param _ancDtlFlag ANC Detail Flag
	 * @param _patDtlVO Patient Other Demographic Detail
	 * @param _ancDetailVO ANC Detail
	 * @param _ancVisitDtlVO ANC Visit Detail
	 * @param _lstHistVOs ANC History Detail
	 * @param _lstHistChildVOs ANC History Delivery Detail
	 * @param _userVO User Detail
	 */
	public void saveCompleteANCDetail(String _ancDtlFlag, PatientDetailVO _patDtlVO, ANCDetailVO _ancDetailVO,
			ANCVisitDetailVO _ancVisitDtlVO, List<ANCHistoryDetailVO> _lstHistVOs, List<ANCHistoryDeliveryDetailVO> _lstHistChildVOs, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
						
			PatientDAOi patDao =  new PatientDAO(tx);
			patDao.updatePatientAdditionalInformation(_patDtlVO, _userVO);
						
			if(_ancDtlFlag.equals(OpdConfig.NO))
				saveANCDetail(_ancDetailVO, _userVO, tx);
			else if(_ancDtlFlag.equals(OpdConfig.YES))
				updateANCDetail(_ancDetailVO, _userVO, tx);
						
			saveANCVisitDetail(_ancVisitDtlVO, _userVO, tx);

			ANCHistoryDtlDAOi ancHistDao = new ANCHistoryDtlDAO(tx);
			for(ANCHistoryDetailVO anchistVO : _lstHistVOs)
			{
				if(ancHistDao.get(anchistVO.getPatCrNo(), anchistVO.getGravidaNo(), _userVO)==null)
					ancHistDao.create(anchistVO, _userVO);
				else
					ancHistDao.update(anchistVO, _userVO);
			}			

			ANCHistoryDeliveryDtlDAOi ancHistDeliveryDao = new ANCHistoryDeliveryDtlDAO(tx);
			for(ANCHistoryDeliveryDetailVO anchistChildVO : _lstHistChildVOs)
			{				
				if(ancHistDeliveryDao.get(anchistChildVO.getPatCrNo(),anchistChildVO.getGravidaNo(), _userVO)==null)
					ancHistDeliveryDao.create(anchistChildVO, _userVO);
				else
					ancHistDeliveryDao.update(anchistChildVO, _userVO);
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
	

	/**
	 * Getting Patient Detail for JSY Registration 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return Map for ANC History
	 */
	public Map getPatDetailForJSYRegistration(String _patCrNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCDetailVO ancdetailVO =new ANCDetailVO();
		PatientDetailVO pDetailVO=new PatientDetailVO();
		JsyRuleMasterVO jRuleMasterVO=new JsyRuleMasterVO(); 
		//String isVisited="";
		//String deliveryStatus=null;
		//String gestationFlag="";
		List casteList=new ArrayList();
		List areaType=new ArrayList();
		Map patientAncDetail=new HashMap();
		HealthWorkerDetailVO hDetailVO=null;
		try
		{
			tx.begin();
			DailyPatientDAOi dailyPatientDAOi=new DailyPatientDAO(tx);
			ANCDtlDAOi aDtlDAOi=new ANCDtlDAO(tx);
			InPatientEssentialDAOi inPatientEssentialDAOi=new InPatientEssentialDAO(tx);
			JSYRegitrationDAOi jRegitrationDAO=new JSYRegitrationDAO(tx);
			//isVisited=dailyPatientDAOi.getPatientsVisitCount(_userVO, _patCrNo);
		//	deliveryStatus=aDtlDAOi.getDeliveryStatus(_userVO, _patCrNo);
		//	gestationFlag=aDtlDAOi.getCompareGestationDate(_userVO, _patCrNo);
		  
		//	if(!(isVisited.equals("0")))
			//  {
					 ancdetailVO=aDtlDAOi.getANCDetailForJSYRegistration(_patCrNo, _userVO);
					 patientAncDetail.put(InpatientConfig.PAT_ANC_DETAIL_FOR_JSY_REGISTRATION, ancdetailVO);
					 
					 pDetailVO=dailyPatientDAOi.getPatDetailForJSYRegistration(_patCrNo, _userVO);
					 patientAncDetail.put(InpatientConfig.PAT_DETAIL_FOR_JSY_REGISTRATION, pDetailVO);
					 
					 jRuleMasterVO=inPatientEssentialDAOi.getJsyRule(_userVO);
					 patientAncDetail.put(InpatientConfig.JSY_RULE_DETAIL, jRuleMasterVO);
					 
					 casteList=inPatientEssentialDAOi.getCasteList(_userVO);
					 patientAncDetail.put(InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST, casteList);
					 
					 areaType=inPatientEssentialDAOi.getAreaCategory();
					 patientAncDetail.put(InpatientConfig.AREA_CATEGORY_LIST, areaType);
					 
					 hDetailVO=jRegitrationDAO.getHealthworkerDetailForJSYRegistration(_patCrNo, _userVO);
					 patientAncDetail.put(InpatientConfig.HEALTHWORKER_DETAIL, hDetailVO);
					 
			//  }else{ throw new HisRecordNotFoundException("Not Visited")}
			
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
		return patientAncDetail;
	}

	
	// save jsy detail
	
	public void saveJSYDetail(JSYRegitrationVO jRegitrationVO,PatientDetailVO pDetailVO,String jsyCategoryCode, SecondaryCategoryChangeVO sChangeVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String patCrNo="";
		String gravidaNo="";
		try
		{
			tx.begin();
			JSYRegitrationDAOi jRegitrationDAO=new JSYRegitrationDAO(tx);
			PatientDAOi patientDAOi=new PatientDAO(tx);
			EpisodeDeathDAOi episodeDeathDAOi=new EpisodeDeathDAO(tx); 
			ANCDtlDAOi aDtlDAOi=new ANCDtlDAO(tx);
			SecondaryCategoryChangeDAO secCatChangeDAO = new SecondaryCategoryChangeDAO(tx);
			
			jRegitrationDAO.create(jRegitrationVO, _userVO);
		  
			if((!pDetailVO.getPatCaste().equals("")) || (!pDetailVO.getPatIsUrban().equals(""))){
				//patientDAOi.updatePatientCasteAreaType(pDetailVO, _userVO); 
			}
			
			
			episodeDeathDAOi.updateTreatementCatCode(jRegitrationVO, jsyCategoryCode, _userVO);
			
			patCrNo= jRegitrationVO.getPatCrNo();
			gravidaNo= jRegitrationVO.getGravidaNo();
			aDtlDAOi.updateJsyFlag(patCrNo, gravidaNo, _userVO);
			
			
			secCatChangeDAO.createExtendValidUpto(sChangeVO ,_userVO);
			
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
	}

	/**
	 * Getting ANC Detail for Delivery
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map getANCDetailsForDelivery(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCDetailVO detailVO=null;
		Map ancDetails = new HashMap();

		try
		{
			tx.begin();
			ANCDtlDAOi dao=new ANCDtlDAO(tx);
			
			detailVO=dao.getANCDetail(_ancDetailVO, _userVO);
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL_FLAG, OpdConfig.YES);
			if(detailVO==null)
			{				
				ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL_FLAG, OpdConfig.NO);
			}
			ancDetails.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL, detailVO);
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
		return ancDetails;
	}

	/**
	 * Saving ANC Delivery Detail
	 * @param _ancDeliveryDetailVO
	 * @param _userVO User Detail
	 */
	public void saveANCDeliveryDetail(ANCDeliveryDetailVO _ancDeliveryDetailVO, ANCDetailVO _ancDetailVO, ANCHistoryDetailVO _ancHistVO,
			List<ANCNeonatalDetailVO> _lstANCChildren, List<ANCHistoryDeliveryDetailVO> _lstANCHistChildren, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			// Save Delivery Detail			
			ANCDeliveryDtlDAOi dao=new ANCDeliveryDtlDAO(tx);
			dao.create(_ancDeliveryDetailVO, _userVO);
			
			// Update ANC Detail
			updateANCDetail(_ancDetailVO, _userVO, tx);
			
			// Save ANC History Detail
			ANCHistoryDtlDAOi ancHistDao = new ANCHistoryDtlDAO(tx);
			ancHistDao.create(_ancHistVO, _userVO);
						
			// Save ANC neonatal Initial Detail
			ANCNeonatalDtlDAOi ancNeonatDao = new ANCNeonatalDtlDAO(tx);
			for(ANCNeonatalDetailVO ancNeoNatVO : _lstANCChildren)
				ancNeonatDao.create(ancNeoNatVO, _userVO);
			
			// Save ANC History Child Detail 
			ANCHistoryDeliveryDtlDAOi ancHistDeliveryDao = new ANCHistoryDeliveryDtlDAO(tx);
			for(ANCHistoryDeliveryDetailVO anchistChildVO : _lstANCHistChildren)
				ancHistDeliveryDao.create(anchistChildVO, _userVO);
		
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
	 * Saving Complete ANC New Natal Detail & Apgar Detail
	 * @param _ancNewNatalVO ANc New Natal Detail VO
	 * @param _lstApgars List of Apgar Details
	 * @param _userVO User Detail
	 */
	public void saveANCNewNatalDetail(ANCNeonatalDetailVO _ancNewNatalVO, List<ANCNeonatalApgarVO> _lstApgars, 
			UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			// Update New Natal Detail			
			ANCNeonatalDtlDAOi daoANCNeoNat=new ANCNeonatalDtlDAO(tx);
			daoANCNeoNat.update(_ancNewNatalVO, _userVO);
			
			ANCNeonatalApgarDtlDAOi daoANCNeoNatApgar=new ANCNeonatalApgarDtlDAO(tx);
			for(ANCNeonatalApgarVO vo : _lstApgars)
				daoANCNeoNatApgar.create(vo, _userVO);
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

	
	public PatIntakeOutDtlVO[] getViewSummaryDetail(String admNo,String fromDate,String toDate,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatIntakeOutDtlVO[] intakeOuttakeVO=null;
		
		try
		{
			tx.begin();
			PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
			intakeOuttakeVO=patIntakeOutDAO.getViewSummaryDetail(admNo,fromDate,toDate,userVO);
			
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
		return intakeOuttakeVO;
	}

	
	public DoctorCallBookVO[] getOnCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO)
	{
		DoctorCallBookVO[] docCallBookVOArray=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorCallBookDAO daoi = new DoctorCallBookDAO(tx);

		try
		{ 
			tx.begin();
			docCallBookVOArray = daoi.getOnCallDetails(doctorCallBookVO,_userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
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

		return docCallBookVOArray;
	}
	
	
	public List getDrugReactionDtl(DrugReactionVO drugReactionVO,UserVO _userVO)
	{
		List drugReactionList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DrugReactionDtlDAOi daoi = new DrugReactionDAO(tx);

		try
		{ 
			tx.begin();
			drugReactionList = daoi.getDrugReactionDetail(drugReactionVO, _userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
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

		return drugReactionList;
	}
	

	/**
	 * Saving ANC Child Handover Detail
	 * @param _ancChildHandoverVO Child Handover Detail
	 * @param _userVO User Detail
	 */
	public void saveANCChildHandoverDetail(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			// Insert Child Handover Detail			
			ANCChildHandoverDtlDAOi daoANCChildHandover=new ANCChildHandoverDtlDAO(tx);
			daoANCChildHandover.create(_ancChildHandoverVO, _userVO);
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
	 * Saving ANC Trimester Checklist Detail
	 * @param _lstDrugChkLst List of Drug CheckList
	 * @param _lstInvstChkLst List of Invest CheckList
	 * @param _userVO User Detail
	 */
	public void saveANCTrimesterChecklistDetail(List<ANCChecklistDetailVO> _lstDrugChkLst, List<ANCChecklistDetailVO> _lstInvstChkLst, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			// Insert CheckList Detail			
			ANCTrimesterChklistDtlDAOi daoANCChkListDtl = new ANCTrimesterChklistDtlDAO(tx);
			for(ANCChecklistDetailVO vo : _lstDrugChkLst)
				daoANCChkListDtl.createDrugChklst(vo, _userVO);
			for(ANCChecklistDetailVO vo : _lstInvstChkLst)
				daoANCChkListDtl.createInvstChklst(vo, _userVO);
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
	
	public Map getExtPatTransReaction(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List templateIdList=null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			BloodTransfusionDAOi dao= new BloodTransfusionDAO(tx);
			
			templateIdList=dao.getTemplateIdList(_userVO);
			essentialMap.put(InpatientConfig.TEMPLATE_ID_LIST_FOR_TRANSFUSION_REACTION, templateIdList);
			
		}

		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public void saveExtBloodTransReactionDtl(TransfusionReactionDtlVO transReactionDtlVO,List bloodTrasReactionParaVOList,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
	   
		try
		{
			tx.begin();
			TransfusionReactionDtlDAOi transfusionReactionDtlDAO=new TransfusionReactionDtlDAO(tx);
			TransfusionReactionParaDtlDAOi transfusionReactionParaDtlDAO=new TransfusionReactionParaDtlDAO(tx);
			
			Integer maxReactionID=new Integer(transfusionReactionDtlDAO.getMaxSlNo(_userVO));
			maxReactionID++;
			transReactionDtlVO.setReactionID(maxReactionID.toString());
			System.out.println("transfusionReactionDtlDAO:1");
			transfusionReactionDtlDAO.creat(transReactionDtlVO, _userVO);
			
			System.out.println("transfusionReactionDtlDAO:2");
			Integer paraSerialNo=1;
			if(bloodTrasReactionParaVOList!=null)
			for(int i=0;i<bloodTrasReactionParaVOList.size();i++)
			{
				TransfusionReactionParaDtlVO vo=(TransfusionReactionParaDtlVO)bloodTrasReactionParaVOList.get(i);
				vo.setReactionID(maxReactionID.toString());
				vo.setParaSerealNo(paraSerialNo.toString());
				transfusionReactionParaDtlDAO.creat(vo, _userVO);
				paraSerialNo++;
			}
			System.out.println("transfusionReactionDtlDAO:3");
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	//addedBy:NehaRajgariya Date:1sept2016
	public String getSmsFlagDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String smsFlag=null;
		try
		{
			tx.begin();

			DoctorCallBookDAO daoobj = new DoctorCallBookDAO(tx);
			smsFlag=daoobj.getSmsFlagDetails(_callBookVO, _UserVO); 
			
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
			
		}
		return smsFlag;
	}
	
	public void getSnomedIdTerm(PatIntakeOutDtlVO patIntakeOutDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
			
				patIntakeOutDAO.getSnomedIdTerm(patIntakeOutDtlVO,userVO);
			
			
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
	}
	//Added by Vasu on 26.Sept.2018 to update doctor Notes
	public void updateNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDAO= new DoctorRoundDtlDAO(tx);
			doctorRoundDAO.updateNotesByDoctor(docRoundDtlVO, userVO);
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
	}
}