package medicalboard.transactions.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.filetransfer.FileTransfer;
import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.BoardTeamDetailVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.HandOverDetailVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.UserVO;
import investigation.InvestigationStaticConfigurator;

import java.util.ArrayList;
import java.util.List;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.dao.BoardDetailDAO;
import medicalboard.transactions.dao.BoardDetailDAOi;
import medicalboard.transactions.dao.BoardTeamDetailDAO;
import medicalboard.transactions.dao.BoardTeamDetailDAOi;
import medicalboard.transactions.dao.HandOverDetailDAO;
import medicalboard.transactions.dao.HandOverDetailDAOi;
import medicalboard.transactions.dao.MbChecklistDetailDAO;
import medicalboard.transactions.dao.MbChecklistDetailDAOi;
import medicalboard.transactions.dao.MbRequisitionDetailDAO;
import medicalboard.transactions.dao.MbRequisitionDetailDAOi;
import registration.RegistrationConfig;
import registration.bo.helper.PatientBOHelper;
import registration.dao.AddressDAO;
import registration.dao.DailyPatientDAO;
import registration.dao.EpisodeDAO;
import registration.dao.EssentialDAO;
import registration.dao.PatientDAO;
import registration.dao.PatientImageDtlDAO;
import registration.dao.PrimaryCategoryChangeDAO;


public class MedicalBoardBO implements MedicalBoardBOi{

	
	/**
	 * Registers a patient when he/she visits the hospital for the first time. Generates the CR No of the Patient. Calculates
	 * age of the patient if DOB is provided and DOB if age is provided. Saves data in Patient Dtl, Address dtl, Daily
	 * Patient dtl, and Episode dtl tables.
	 * 
	 * @param _arrEpisodeVO[] Provides the departments in which patient has been registered.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO newPatRegistration(EpisodeVO _arrEpisodeVO, PatientVO _patientVO,List lstCheckList,MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			MbRequisitionDetailDAOi mDetailDAO=new MbRequisitionDetailDAO(tx);
			MbChecklistDetailDAOi mDetailDAOi=new MbChecklistDetailDAO(tx); 
			//RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			//RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			//EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			PrimaryCategoryChangeDAO primaryCategoryChangeDAO = new PrimaryCategoryChangeDAO(tx);
			PrimaryCategoryChangeVO primaryCategoryChangeVO = new PrimaryCategoryChangeVO();
			synchronized (patDao.getLock())
			{
				//EpisodeReferVO _episodeReferVO = new EpisodeReferVO();
				//EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
				//List printData = new ArrayList();

				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				_patientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED);
				_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				_patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);
				_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
				//_patientVO = patDao.create(_patientVO, _userVO);
				_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());

				// Create a new Address
				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
				//addDao.createNewAddress(_patientVO.getPatAddress(), _userVO);

			/*	printData.add(_patientVO.getPatCrNo());
				printData.add(_patientVO.getPatFirstName());
				printData.add(_patientVO.getPatMiddleName());
				printData.add(_patientVO.getPatLastName());
				printData.add(_patientVO.getPatAge());
				printData.add(_patientVO.getPatAgeUnit());
				printData.add(_patientVO.getPatGenderCode());*/
				
				///temp code for trapping error
			
				///////////
					DailyPatientVO dailyPatVO = new DailyPatientVO();

					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, _patientVO);
					// set Other required detials in episodeVO
					// explicitly set setIsValid(RegistrationConfig.IS_VALID_ACTIVE)
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);

					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO);
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
						{
						_arrEpisodeVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						_arrEpisodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
						}
					else{
						_arrEpisodeVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					}
					_arrEpisodeVO.setPatCrNo(dailyPatVO.getPatCrNo());
				//	_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO.setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					_arrEpisodeVO.setIsValid(Config.IS_VALID_ACTIVE);
					// populate this dailyPatient VO with _arrEpisodeVO[i]
					_patientVO.setDepartmentCode(_arrEpisodeVO.getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO);
					HelperMethods.populate(dailyPatVO, _patientVO);

					//dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					// PatientBOHelper.setDailyPatientDetails(dailyPatVO);

					// Create a new entry in daily patient detail
					// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO

					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					dailyPatVO = dailyPatDao.insertDailyPatientInfo(dailyPatVO, _userVO);

					HelperMethods.populate(primaryCategoryChangeVO, dailyPatVO);
					primaryCategoryChangeVO.setPatNewPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					HelperMethods.populate(_arrEpisodeVO, dailyPatVO);
					//System.out.println("_arrEpisodeVO[i].getIsReferred()..::.. " + _arrEpisodeVO.getIsReferred());

					//Create new entry in Episode detail
					
					

					// Create new entry in Episode detail
					_arrEpisodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);

					///setting visit number of episode
					_arrEpisodeVO.setEpisodeVisitNo("1");
					_arrEpisodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					
					episodeDao.create(_arrEpisodeVO, _userVO);
					//_arrEpisodeVO.setDepartmentCode(_arrEpisodeVO.getDepartmentUnitCode().substring(0, 3));

					String deptCode = _arrEpisodeVO.getDepartmentCode();
					String roomCode = _arrEpisodeVO.getRoomCode();
					String deptUnitCode = _arrEpisodeVO.getDepartmentUnitCode();
					List al = new ArrayList();
					al = essentialDAO.getNameValues(deptCode, roomCode, deptUnitCode, _userVO);
					_arrEpisodeVO.setDepartment(al.get(0).toString());
					_arrEpisodeVO.setRoom(al.get(1).toString());
					_arrEpisodeVO.setDepartmentUnit(al.get(2).toString());
					//_arrEpisodeVO[i].setAdmissionNo(al.get(3).toString());

					// HelperMethods.populate(_arrEpisodeVO[i], al);
					
					
					if (_patientVO.getImageFile() != null)
					{
						HelperMethods.populate(patientImageDtlVO, _arrEpisodeVO);
						
						int maxSerialNo=1;
						byte[] byteArray =_patientVO.getImageFile();
						String fileName = patientImageDtlVO.getPatCrNo() + Config.PATIENT_IMAGE_FILE_STORAGE_SEPARATOR_NAME_N_SNO
							+ Integer.toString(maxSerialNo) + Config.PATIENT_IMAGE_FILE_STORAGE_EXT;
						String fileSubFolder = ""; // -------
						String filepath = Config.TARGET_FOLDER_PATIENT_IMAGE + fileSubFolder;

						FileTransfer fileTransferUtil = new FileTransfer(fileName, fileSubFolder, Config.TARGET_FOLDER_PATIENT_IMAGE, byteArray);
						fileTransferUtil.setAppPath(Config.PATH_APPLICATION_SERVER_FILE_STORAGE_WINDOWS, Config.PATH_APPLICATION_SERVER_FILE_STORAGE_LINUX);
						fileTransferUtil.setFTPPath(InvestigationStaticConfigurator.resultprintingftpaddress, InvestigationStaticConfigurator.patientfileftpusername, InvestigationStaticConfigurator.patientfileftppassword);		

						if(!fileTransferUtil.saveFile())
						{
							throw new HisApplicationExecutionException(fileTransferUtil.getErrorMessage());
						}

						patientImageDtlVO.setFileNo(fileName);	//patientImageDtlVO.getPatCrNo() + "#1");
						//HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO
							//	.getFileNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
						patientImageDtlVO.setFilePath(filepath);//Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
						patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
						patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
						//patientImageDtlDAO.create(patientImageDtlVO, _userVO);
					}
				   
					primaryCategoryChangeDAO.create(primaryCategoryChangeVO, _userVO);
					
					MedicalBoardRequisitionVO boardRequisitionVO=new MedicalBoardRequisitionVO();
					 mRequisitionVO.setPatCrNo(dailyPatVO.getPatCrNo());
					boardRequisitionVO=mDetailDAO.create(mRequisitionVO, _userVO);
					String reqID=boardRequisitionVO.getReqID();
					
				for(int i=0;i<lstCheckList.size();i++)
				{
				  MedicalBoardChecklistVO mChecklistVO=(MedicalBoardChecklistVO)lstCheckList.get(i);
				  mChecklistVO.setReqID(reqID);
				  mDetailDAOi.create(mChecklistVO, _userVO);
				}
			 }
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
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
		return _arrEpisodeVO; 
	}// each VisitStampVO keeps PatientVO

	public void saveBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
				
		try
		{
			tx.begin();
			BoardDetailDAOi boardDetailDAO=new BoardDetailDAO(tx); 
			BoardTeamDetailDAOi boardTeamDetailDAO=new BoardTeamDetailDAO(tx);
			
			String boardNo=boardDetailDAO.getBoardNumber(userVO);
			
			boardDetailVO.setBoardNo(boardNo);
			boardDetailVO.setBoardStatus(MedicalBoardConfig.BOARD_INPROCESS);
			boardDetailDAO.create(boardDetailVO, userVO);
			
			if(docRoleTeamDetailList!=null)
			{
				for(int i=0;i<docRoleTeamDetailList.size();i++)
				{
					BoardTeamDetailVO boardTeamDetailVO=(BoardTeamDetailVO)docRoleTeamDetailList.get(i);
					boardTeamDetailVO.setBoardNo(boardNo);
					boardTeamDetailVO.setDutyStatus(MedicalBoardConfig.DUTY_ASSIGNED);
					boardTeamDetailDAO.create(boardTeamDetailVO, userVO);
				}
			}
			if(escortedTeamDetailList!=null)
			{
				for(int i=0;i<escortedTeamDetailList.size();i++)
				{
					BoardTeamDetailVO boardTeamDetailVO=(BoardTeamDetailVO)escortedTeamDetailList.get(i);
					boardTeamDetailVO.setBoardNo(boardNo);
					boardTeamDetailVO.setDutyStatus(MedicalBoardConfig.DUTY_ASSIGNED);
					boardTeamDetailDAO.create(boardTeamDetailVO, userVO);
				}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	
	
	public void removeBoard(String boardNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
				
		try
		{
			tx.begin();
			BoardDetailDAOi boardDetailDAO=new BoardDetailDAO(tx); 
			BoardTeamDetailDAOi boardTeamDetailDAO=new BoardTeamDetailDAO(tx);
			
			boardDetailDAO.deleteBoardDetail(boardNo, userVO);
			boardTeamDetailDAO.deleteBoardTeamDetail(boardNo, userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void updateAssignBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
				
		try
		{
			tx.begin();
			BoardDetailDAOi boardDetailDAO=new BoardDetailDAO(tx); 
			BoardTeamDetailDAOi boardTeamDetailDAO=new BoardTeamDetailDAO(tx);
			
			//String boardNo=boardDetailDAO.getBoardNumber(userVO);
			
			//boardDetailVO.setBoardNo(boardNo);
			boardDetailDAO.updateBoardDetail(boardDetailVO.getBoardNo(), userVO);
			boardTeamDetailDAO.updateBoardTeamDetail(boardDetailVO.getBoardNo(), userVO);
			
			boardDetailVO.setBoardStatus(MedicalBoardConfig.BOARD_INPROCESS);
			boardDetailDAO.create(boardDetailVO, userVO);
			
			if(docRoleTeamDetailList!=null)
			{
				for(int i=0;i<docRoleTeamDetailList.size();i++)
				{
					BoardTeamDetailVO boardTeamDetailVO=(BoardTeamDetailVO)docRoleTeamDetailList.get(i);
					boardTeamDetailVO.setBoardNo(boardDetailVO.getBoardNo());
					boardTeamDetailVO.setDutyStatus(MedicalBoardConfig.DUTY_ASSIGNED);
					boardTeamDetailDAO.create(boardTeamDetailVO, userVO);
				}
			}
			if(escortedTeamDetailList!=null)
			{
				for(int i=0;i<escortedTeamDetailList.size();i++)
				{
					BoardTeamDetailVO boardTeamDetailVO=(BoardTeamDetailVO)escortedTeamDetailList.get(i);
					boardTeamDetailVO.setBoardNo(boardDetailVO.getBoardNo());
					boardTeamDetailVO.setDutyStatus(MedicalBoardConfig.DUTY_ASSIGNED);
					boardTeamDetailDAO.create(boardTeamDetailVO, userVO);
				}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	
	public void saveHandOverDetail(List handOverDetailVOList,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
				
		try
		{
			tx.begin();
			
			HandOverDetailDAOi handOverDetailDAO=new HandOverDetailDAO(tx);
			MbRequisitionDetailDAOi requisitionDtlDAO=new MbRequisitionDetailDAO(tx);
			
			if(handOverDetailVOList!=null)
			{
				for(int i=0;i<handOverDetailVOList.size();i++)
				{
					HandOverDetailVO handOverDtlVO=(HandOverDetailVO)handOverDetailVOList.get(i);
					handOverDetailDAO.create(handOverDtlVO, _UserVO);
					//update requisition dtl table also
					requisitionDtlDAO.updateReqStatusHandOver(handOverDtlVO, _UserVO);
					
				}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void saveCertVerificationDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
				
		try
		{
			tx.begin();
			MbRequisitionDetailDAOi requisitionDAO=new MbRequisitionDetailDAO(tx);
			requisitionDAO.updateApprovalDetail(requisitionVO, userVO);
						
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
			throw new HisDataAccessException(e.getMessage());
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
	


	public PatientImageDtlVO getPatientImageDtlByCrNo(String crNo, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientImageDtlVO patientImageDtlVO;
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			patientImageDtlVO=patImageDAO.getMedicalBoardPatientImage(crNo, userVO);
			
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
		return patientImageDtlVO;
	} 
	
	
	
	
	
	
	
	
}
