package registration.config;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import registration.config.Exceptions.HisAppointmentNotAvailableException;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.dao.BeneficiaryPatientDAO;
import registration.dao.CreditAvailDtlDAO;
import registration.dao.DailyPatientDAO;
import registration.dao.DirectChargeDetailDAO;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeRefDtlDAO;
import registration.dao.RenewalDetailDAO;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import vo.registration.BeneficiaryPatientVO;
import vo.registration.CreditAvailDetailVO;
import vo.registration.DailyPatientVO;
import vo.registration.DirectChageDetailVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RenewalDetailVO;

public class PatientBOHelper {
	public static void setNewPatRegEpisodeVO(EpisodeVO _episode)
	{
		_episode.setIsValid(Config.IS_VALID_ACTIVE);
		//_episode.setEpisodeDate("");
		_episode.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);		
		//_episode.setEpisodeTime("12:06:04");
		_episode.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		//_episode.setEntryDate("TO_CHAR(SYSDATE, 'DD/MON/YYYY')");
		//_episode.setSpecifyExpiry(RegistrationConfig.SPECIFY_EXPIRY_TRUE);
	}

	public static void setDailyPatientDetails(DailyPatientVO _dailyPatient)
	{
	}
	
	public static void oldDeptVisitStamp(HisDAO objHisDAO_p,EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, JDBCTransactionContext tx,EpisodeRefDtlVO objEpisodeRefDtlVO_p, String strExpiryDate_p)
	{
		DailyPatientDAO objDailyPatientDAO=new DailyPatientDAO(tx);
		EpisodeDAO objEpisodeDAO=new EpisodeDAO(tx);
		EpisodeRefDtlDAO objEpisodeRefDtlDAO=new EpisodeRefDtlDAO(tx);
		
		String strToVisitNo="";
		String strPatVisitReasonForOldDeptVisit="";
		String patPrimaryCatGrp="";
		
		try{
				System.out.println("PatientBOHelper :: oldDeptVisitStamp()");
				DirectChageDetailVO[] objDirectChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
				RenewalDetailVO[] objRenewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
				BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
				CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
				
				DirectChargeDetailDAO objDirectChargeDAO=new DirectChargeDetailDAO(tx);
				RenewalDetailDAO objRenewDetailDAO = new RenewalDetailDAO(tx);
				BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
				CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
				
				 String strReferMlcNo=objEpisodeRefDtlVO_p.getMlcNo();
				 
				 for(int i=0; i<objArrEpisodeVO_p.length; i++)
				 {
					 strPatVisitReasonForOldDeptVisit=objArrEpisodeVO_p[i].getPatVisitReason();
					 
					 ///value of is olfd ==1
					 objArrEpisodeVO_p[i].setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 //objArrEpisodeVO_p[i].setTariffId(objUserVO_p.getTariffID());
					 
					 DailyPatientVO objDailyPatVO=new DailyPatientVO();
					 DailyPatientVO objTmpdailyPatVO=new DailyPatientVO();
					 
					 if (objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE)){
						 strToVisitNo="1";
					 }
					 else{
						 if(objArrEpisodeVO_p[i].getEpisodeVisitNo()!=null)
							 strToVisitNo=Integer.toString(Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeVisitNo())+1);
					 }
					 //setting visit number instead of firing query//
					 objArrEpisodeVO_p[i].setEpisodeVisitNo(strToVisitNo);
					 
					
					 String patCatName=objPatientVO_p.getPatPrimaryCat();
					 //Create DailyPatientVO from patientVO:  Populate it
					 HelperMethods.populate(objDailyPatVO, objPatientVO_p);
					  //populate dailyPatient VO with objArrEpisodeVO_p[i]
					 HelperMethods.populate(objDailyPatVO, objArrEpisodeVO_p[i]);
					 
					//Code for setting MLC Flag Referred if Patient is refered from MLC Episode
						if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
						{
							if(objDailyPatVO.getMlcFlag()==null)
							{
							if (objEpisodeRefDtlVO_p.getMlcFlag()!=null && (objEpisodeRefDtlVO_p.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_PROVISIONAL) || objEpisodeRefDtlVO_p.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_CONFIRMED)))
								objDailyPatVO.setMlcFlag(RegistrationConfig.MLC_FLAG_REFFERED);
							}
						}
					 objDailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
					
					 //no need to generate file number from old patient
					 objDailyPatVO.setIsNewFileRequired(RegistrationConfig.FILE_NO_GENRATION_FALSE);
					 objDailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 objDailyPatVO.setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 objDailyPatVO.setIsConfirmed("1");
					 objDailyPatVO.setPatPrimaryCat(patCatName);
					 
					//By Mukund On 07.09.2016 for expiry date manipulation
					 //setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
					 if (objArrEpisodeVO_p[i].getRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE))	
						 setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
					 else
						 objDailyPatVO.setEpisodeExpiryDate(objDailyPatVO.getExpiryDate());
					 //end:Mukund
					 
					 if(objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_TRUE))
					 {
						 //objArrEpisodeVO_p[i].setSpecifyExpiry(RegistrationConfig.SPECIFY_EXPIRY_FALSE);
						 if(objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_TRUE) || 
								 objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_NOT_EXIST) || 
								 objDailyPatVO.getDepartmentUnitCode()==null || objDailyPatVO.getDepartmentUnitCode().equals("") ||
								 objArrEpisodeVO_p[i].getDeptUnitIsOnDuty().equals(RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE)){
							 
							
							 System.out.println("PatientBOHelper :: oldDeptVisitStamp :: EPISODE_ISOPEN_TRUE :: Foreceful_Visit");
							 /*if(objArrEpisodeVO_p[i].getOnRequestVisit().equals("1"))
								objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_FORCEFUL);
							 else
								objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);*/
							 
							 ///New episode is being created hence visit number 1
							 objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
							 objDailyPatVO.setEpisodeVisitNo("1");
							 
							 objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
							 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
								
							 objDailyPatVO.setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
							 objDailyPatVO.setIsForceful("1");
							 objArrEpisodeVO_p[i].setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
								
							 // Calling DailyPatientDAO
							 objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(),"ODOPD");
								
							 objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
							 objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
							 objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
							 objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
							 objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
							 objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
								
							 // Calling EpisodeDAO for generating Episode Code
							 String strEpisodeCode =objEpisodeDAO.generateEpisodeCode(objPatientVO_p.getPatCrNo(), objUserVO_p, objDailyPatVO.getDepartmentUnitCode());
							 objDailyPatVO.setEpisodeCode(strEpisodeCode);
							 objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
							 
						 }
						 else{
							 
							 objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p, RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(), "ODOPD");
							
							 objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
							 objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
							 objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
							 objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
							 objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
							 objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
							
							 if((strReferMlcNo!=null && !strReferMlcNo.equals("")) || (objArrEpisodeVO_p[i].getMlcNo()!=null && !objArrEpisodeVO_p[i].getMlcNo().equals(""))){
								 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
							 }/*else{
								 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
							 }*/
							 
							 objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
							 
						 }
					 }
					/////new episode
						/////creating new episode
					 else if (objArrEpisodeVO_p[i].getEpisodeIsOpen().trim().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE))
					 {
						PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
						objDailyPatVO.setEpisodeIsOpen(objArrEpisodeVO_p[i].getEpisodeIsOpen());
						
						
						// if else add on 20-04-2010 for handling force ful visit in case of episode close
							if(objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_TRUE) || 
									objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_NOT_EXIST) || 
									objDailyPatVO.getDepartmentUnitCode()==null || objDailyPatVO.getDepartmentUnitCode().equals("") ||
									objArrEpisodeVO_p[i].getDeptUnitIsOnDuty().equals(RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE))
							{
							 
								////////////////*******create old episode *********///////////////////////
								////forceful stamping
								
								System.out.println("PatientBOHelper :: oldDeptVisitStamp :: EPISODE_ISOPEN_FALSE :: Foreceful_Visit");
								if(objArrEpisodeVO_p[i].getOnRequestVisit().equals("1"))
									objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_FORCEFUL);
								else
									objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
							
								///New episode is being created hence visit number 1
								objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
								objDailyPatVO.setEpisodeVisitNo("1");
								
								objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
								objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
								
								objDailyPatVO.setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
								objArrEpisodeVO_p[i].setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
								objDailyPatVO.setIsForceful("1");
								
								// Calling DailyPatientDAO
								objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(),"ODOPD");
								
								objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
								objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
								objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
								objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
								objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
								
								// Calling EpisodeDAO for generating Episode Code
								String strEpisodeCode =objEpisodeDAO.generateEpisodeCode(objPatientVO_p.getPatCrNo(), objUserVO_p, objDailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setEpisodeCode(strEpisodeCode);
								
								objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
								
								//objDailyPatientDAO.createForceFulVisit(objHisDAO_p,objDailyPatVO, objUserVO_p,strReferMlcNo);
							}
							else
							{
								////////////////*******create old episode *********///////////////////////
								
								////generating episode code 
								//String episode=Integer.toString((Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeCode())+1));
								////////
								//objDailyPatVO.setEpisodeCode(episode);
								//objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
								
								objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(), "ODOPD");
								
								objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
								objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
								objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
								objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
								objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
								
								// Calling EpisodeDAO for generating Episode Code
								String episode =objEpisodeDAO.generateEpisodeCode(objPatientVO_p.getPatCrNo(), objUserVO_p, objDailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setEpisodeCode(episode);
						 
								if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_GENERAL))
								{
									objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
								}
								else if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_SPECIALITY))
						 		{
									objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						 		}
								if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_CASUALITY))
								{
									objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
									
								}
						 
								if((strReferMlcNo!=null && !strReferMlcNo.equals("")) || (objArrEpisodeVO_p[i].getMlcNo()!=null && !objArrEpisodeVO_p[i].getMlcNo().equals("")))
								{
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
								}/*else{
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
								}*/
								objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
								//objDailyPatVO=objDailyPatientDAO.stampOldDeptVisit(objHisDAO_p,objDailyPatVO, objUserVO_p);
							}//end esle
						
					 }
					 
					 //EpisodeCode is assigned to objDailyPatVO
					 //populate episodeVO from DailyPatVO
					 HelperMethods.populate(objArrEpisodeVO_p[i], objDailyPatVO);
					 //objArrEpisodeVO_p[i].setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 if(strReferMlcNo!=null && !strReferMlcNo.equals(""))
					 {
						 objArrEpisodeVO_p[i].setMlcNo(strReferMlcNo);
					 }
					 objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_OLD_PATIENT);
					 
					 ////////if episode is closed then no need to continue treatment category 
					 if(objArrEpisodeVO_p[i].getEpisodeVisitNo().equals("1")){
						 objArrEpisodeVO_p[i].setSecCatExpDate("");
						 objArrEpisodeVO_p[i].setPatSecondaryCatCode("");
					 }
					 else {
						 if(objArrEpisodeVO_p[i].getPatSecondaryCatCode()==null || objArrEpisodeVO_p[i].getPatSecondaryCatCode().equals(""))
							 objArrEpisodeVO_p[i].setSecCatExpDate("");
						
					 }
					 
					 //Create new entry in Episode detail
					 
					 //if(objArrEpisodeVO_p[i].getSpecifyExpiry().equals(RegistrationConfig.SPECIFY_EXPIRY_FALSE))
					 if((objPatientVO_p.getIsReferred()!=null) && (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)))
						 objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						 
					 
					 System.out.println("objArrEpisodeVO_p[i]"+objArrEpisodeVO_p[i].getExpiryDate());
					 objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_OLD);
					
					 objEpisodeDAO.saveEpisodeDtl(objHisDAO_p, objArrEpisodeVO_p[i], objUserVO_p, "1");
						
						 
					 if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					 {
						/////////episode referDTL////////////////
						String refEpisode=objEpisodeRefDtlVO_p.getEpisodeCode();
						
						////check this part
						//String[] array=objEpisodeDAO.getEpisdoeCode(objHisDAO_p,objArrEpisodeVO_p[i].getPatCrNo(),objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p);
						//objArrEpisodeVO_p[i].setEpisodeVisitNo(array[2]);
						
						String refEpisodeVisit=objEpisodeRefDtlVO_p.getEpisodeVisitNo();
						HelperMethods.populate(objEpisodeRefDtlVO_p, objArrEpisodeVO_p[i]);
						objEpisodeRefDtlVO_p.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)){
							objEpisodeRefDtlVO_p.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
						//	objEpisodeRefDtlVO_p.setEpisodeVisitNo(objArrEpisodeVO_p[i].getEpisodeVisitNo());
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(objArrEpisodeVO_p[i].getEpisodeVisitNo());
							
						}
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)){
							objEpisodeRefDtlVO_p.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objEpisodeRefDtlVO_p.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objEpisodeRefDtlVO_p.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							objEpisodeRefDtlVO_p.setToEpisodeVisitNo(strToVisitNo);
							objEpisodeRefDtlVO_p.setEpisodeCode(refEpisode);
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(refEpisodeVisit);
							objEpisodeRefDtlVO_p.setExternalHospitalCode("");
						}
						
						if(objPatientVO_p.getIsPatReferByList()!=null && objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							objEpisodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO_p, objEpisodeRefDtlVO_p, objUserVO_p,"2");
						}
						else
						{
							objEpisodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO_p, objEpisodeRefDtlVO_p, objUserVO_p,"1");
						}
						
					 }
					 
					 //Credit Category wise Renewal Change Added by Singaravelan on 09-Dec-2014 
					 boolean flagBeneficiaryRenewal=false;
					 HelperMethods.setNullToEmpty(objPatientVO_p);
					 if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
								(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
								objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)))
					 {	
						 //if (objPatientVO_p.getPatRenewalActualAmount() != null && !objPatientVO_p.getPatRenewalActualAmount().equals("") && !objPatientVO_p.getPatRenewalActualAmount().equals("0") &&					 !objPatientVO_p.getPatRenewalActualAmount().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						 if (objArrEpisodeVO_p[i].getRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE))	 
								 flagBeneficiaryRenewal=true;					 					 
						 //By Mukund on 25.Jul.17
						 if(objArrEpisodeVO_p[i].getRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_FOURTEEN_DAYS))
							 flagBeneficiaryRenewal=true;
						 //End 25.Jul.17
					 }
					 else{
						 if (objArrEpisodeVO_p[i].getPatAmountCollected() != null && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("") && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("0") && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						 {
							 flagBeneficiaryRenewal=true;
						 }
					 }					 
					 
						 
					 if (flagBeneficiaryRenewal)
					 {
							objDirectChargeVO[i]= new DirectChageDetailVO();
							objDirectChargeVO[i].setTariffId(objUserVO_p.getTariffID());
							objDirectChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
							objDirectChargeVO[i].setModuleId(objUserVO_p.getModuleId());
							HelperMethods.populate(objDirectChargeVO[i], objArrEpisodeVO_p[i]);
							objDirectChargeVO[i].setPatAmountCollected(objArrEpisodeVO_p[i].getPatAmountCollected());
							objDirectChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
							objDirectChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
							objDirectChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
							
							objDirectChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
							objDirectChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
							objDirectChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
							objDirectChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
							objDirectChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
							objDirectChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
							objDirectChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
							//objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
							objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatRenewalActualAmount());
							objDirectChargeVO[i].setPatRenewalActualAmount(objPatientVO_p.getPatRenewalActualAmount());
							objDirectChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());
							
							objDirectChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
							if(null!=objPatientVO_p.getAgsNo() && !objPatientVO_p.getAgsNo().equals(""))
								objDirectChargeVO[i].setCardNo(objPatientVO_p.getAgsNo());
							else if(null!=objPatientVO_p.getStaffCardNo() && !objPatientVO_p.getStaffCardNo().equals(""))
							{
								objDirectChargeVO[i].setCardNo(objPatientVO_p.getStaffCardNo());
								objDirectChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
							}
							
							
							//if (!(objDirectChargeVO[i].getPatAmountCollected() == null || objDirectChargeVO[i].getPatAmountCollected().equals("0") || objDirectChargeVO[i].getPatAmountCollected().equals(""))) 
							//{
								objDirectChargeDAO.create(objHisDAO_p,objDirectChargeVO[i], objUserVO_p);
							//}
							///Billing module integration
							//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
							// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
							
							
							objRenewDetailVO[i] = new RenewalDetailVO();
							objRenewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
							objRenewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
							objRenewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							objRenewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
							objRenewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
							objRenewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objRenewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objRenewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
							objRenewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
							objRenewDetailVO[i].setNewExpiryDate(strExpiryDate_p);
							objRenewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());
							// added by Surabhi for change in the expiry date for renewal
							objArrEpisodeVO_p[i].setExpiryDate(strExpiryDate_p);
							//end

							objRenewDetailDAO.saveRenewalDtl(objHisDAO_p,objRenewDetailVO[i], objUserVO_p,"1");
							
							
							//For the Details Insertion in the Beneficiary Patient Dtl Table On the Renewal case Added by Singaravelan on 30-Sep-2014
							if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
									(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) 
											|| objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
											)){
								
								patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
								//Need to remove this cond once the staff no constraints from the table is removed
								if((null!=objPatientVO_p.getStaffCardNo()|| null!=objPatientVO_p.getAgsNo())&&(!objPatientVO_p.getStaffCardNo().equals("")||!objPatientVO_p.getAgsNo().equals("")))
								{								
								HelperMethods.populate(objBenPatVO, objPatientVO_p);					
								if(null!=objBenPatVO.getAgsNo() && !objBenPatVO.getAgsNo().equals(""))
									objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
								else if(null!=objBenPatVO.getStaffCardNo() && !objBenPatVO.getStaffCardNo().equals(""))
									objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
								
								if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
									objBenPatVO.setIsDependent("1");
									objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
								}else{
									objBenPatVO.setIsDependent("0");
									objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
								}
								objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
								objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
								
								//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO_p, objBenPatVO, objUserVO_p, "1");
								benPatientDAO.savePatientCreditDtl(objHisDAO_p, objBenPatVO, objUserVO_p, "1");
								}
													
							}
							
							//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL Added by Singaravelan on 30-Sep-2014
							if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
								HelperMethods.populate(objCreditAvailDtlVO, objDirectChargeVO[i]);
								HelperMethods.setNullToEmpty(objCreditAvailDtlVO);
								objCreditAvailDtlVO.setGroupId(RegistrationConfig.BILL_GROUP_ID);
								objCreditAvailDtlVO.setRecieptNo("1");
								objCreditAvailDtlVO.setRecieptType("1");
								objCreditAvailDtlVO.setBillQty("1");
								objCreditAvailDtlVO.setProcessedQty("1");
								objCreditAvailDtlVO.setRemainedQty("0");
								objCreditAvailDtlVO.setQtyUnitId(RegistrationConfig.BILL_QTY_UNIT_ID);
								objCreditAvailDtlVO.setRateUnitCode(RegistrationConfig.BILL_QTY_UNIT_ID);
								objCreditAvailDtlVO.setBillServiceId("10");
								objCreditAvailDtlVO.setIsPackage("0");
								
								//objCreditAvailDtlVO.setTariffActualRate(objDirectChargeVO[i].getPatActualAmount());
								//objCreditAvailDtlVO.setPaidByClientAmt(objDirectChargeVO[i].getPatActualAmount());
								objCreditAvailDtlVO.setTariffActualRate(objDirectChargeVO[i].getPatRenewalActualAmount());
								objCreditAvailDtlVO.setPaidByClientAmt(objDirectChargeVO[i].getPatRenewalActualAmount());
								
								objCreditAvailDtlVO.setPaidByPatAmt("0");
								//objCreditAvailDtlVO.setNetAmount(objDirectChargeVO[i].getPatActualAmount());
								objCreditAvailDtlVO.setNetAmount(objDirectChargeVO[i].getPatRenewalActualAmount());
								objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());

								//Need to include approved by here
								
								creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO_p, objCreditAvailDtlVO, objUserVO_p, "1");
							}
							
										
						
						}
					 
				 }
			 
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(""+e);
			else if(e.getClass()==HisDataAccessException.class)
				throw new HisDataAccessException(""+e);
			else if(e.getClass()==HisUpdateUnsuccesfullException.class)
				throw new HisUpdateUnsuccesfullException(""+e);
			else if(e.getClass()==HisInvalidTokenNumberException.class)
				throw new HisInvalidTokenNumberException(""+e);
			else if(e.getClass()==HisAppointmentNotAvailableException.class)
				throw new HisAppointmentNotAvailableException(""+e);
			else
				throw new HisApplicationExecutionException("PatientBOSupport oldDeptVisitStamp "+e);
		}
		
	}
	
	/*Added by Garima for Unit Wise Visit*/
	public static void oldDeptVisitStampNew(PatientVisitSUP objPatVisitSUP_p,HisDAO objHisDAO_p,EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, JDBCTransactionContext tx,EpisodeRefDtlVO objEpisodeRefDtlVO_p, String strExpiryDate_p)
	{
		DailyPatientDAO objDailyPatientDAO=new DailyPatientDAO(tx);
		EpisodeDAO objEpisodeDAO=new EpisodeDAO(tx);
		EpisodeRefDtlDAO objEpisodeRefDtlDAO=new EpisodeRefDtlDAO(tx);
		String strToVisitNo="";
		String strPatVisitReasonForOldDeptVisit="";
		String patPrimaryCatGrp="";
		
		try{
				System.out.println("PatientBOHelper :: oldDeptVisitStamp()");
				DirectChageDetailVO[] objDirectChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
				RenewalDetailVO[] objRenewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
				BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
				CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
				
				DirectChargeDetailDAO objDirectChargeDAO=new DirectChargeDetailDAO(tx);
				RenewalDetailDAO objRenewDetailDAO = new RenewalDetailDAO(tx);
				BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
				CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
				
				 String strReferMlcNo=objEpisodeRefDtlVO_p.getMlcNo();
				 
				 for(int i=0; i<objArrEpisodeVO_p.length; i++)
				 {
					 strPatVisitReasonForOldDeptVisit=objArrEpisodeVO_p[i].getPatVisitReason();
					 
					 ///value of is olfd ==1
					 objArrEpisodeVO_p[i].setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 //objArrEpisodeVO_p[i].setTariffId(objUserVO_p.getTariffID());
					 
					 DailyPatientVO objDailyPatVO=new DailyPatientVO();
					 DailyPatientVO objTmpdailyPatVO=new DailyPatientVO();
					 
					 if (objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE)){
						 strToVisitNo="1";
					 }
					 else{
						 if(objArrEpisodeVO_p[i].getEpisodeVisitNo()!=null)
							 strToVisitNo=Integer.toString(Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeVisitNo())+1);
					 }
					 //setting visit number instead of firing query//
					 objArrEpisodeVO_p[i].setEpisodeVisitNo(strToVisitNo);
					 
					
					 String patCatName=objPatientVO_p.getPatPrimaryCat();
					 //Create DailyPatientVO from patientVO:  Populate it
					 HelperMethods.populate(objDailyPatVO, objPatientVO_p);
					  //populate dailyPatient VO with objArrEpisodeVO_p[i]
					 HelperMethods.populate(objDailyPatVO, objArrEpisodeVO_p[i]);
					 
					//Code for setting MLC Flag Referred if Patient is refered from MLC Episode
						if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
						{
							if(objDailyPatVO.getMlcFlag()==null)
							{
							if (objEpisodeRefDtlVO_p.getMlcFlag()!=null && (objEpisodeRefDtlVO_p.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_PROVISIONAL) || objEpisodeRefDtlVO_p.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_CONFIRMED)))
								objDailyPatVO.setMlcFlag(RegistrationConfig.MLC_FLAG_REFFERED);
							}
						}
					 objDailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
					
					 //no need to generate file number from old patient
					 objDailyPatVO.setIsNewFileRequired(RegistrationConfig.FILE_NO_GENRATION_FALSE);
					 objDailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 objDailyPatVO.setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 objDailyPatVO.setIsConfirmed("1");
					 objDailyPatVO.setPatPrimaryCat(patCatName);
					 
					 setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
					 
					 if(objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_TRUE))
					 {
						 //objArrEpisodeVO_p[i].setSpecifyExpiry(RegistrationConfig.SPECIFY_EXPIRY_FALSE);
						 if(objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_TRUE) || 
								 objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_NOT_EXIST) || 
								 objDailyPatVO.getDepartmentUnitCode()==null || objDailyPatVO.getDepartmentUnitCode().equals("") ||
								 objArrEpisodeVO_p[i].getDeptUnitIsOnDuty().equals(RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE)){
							 
							
							 System.out.println("PatientBOHelper :: oldDeptVisitStamp :: EPISODE_ISOPEN_TRUE :: Foreceful_Visit");
							 /*if(objArrEpisodeVO_p[i].getOnRequestVisit().equals("1"))
								objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_FORCEFUL);
							 else
								objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);*/
							 
							 ///New episode is being created hence visit number 1
							 objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
							 objDailyPatVO.setEpisodeVisitNo("1");
							 
							 objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
							 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
								
							 objDailyPatVO.setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
							 objDailyPatVO.setIsForceful("1");
							 objArrEpisodeVO_p[i].setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
								
							 // Calling DailyPatientDAO
							 objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(),"ODOPDN");
								
							 objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
							 objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
							 objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
							 objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
							 objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
							 objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
								
							 // Calling EpisodeDAO for generating Episode Code
							 String strEpisodeCode =objEpisodeDAO.generateEpisodeCode(objPatientVO_p.getPatCrNo(), objUserVO_p, objDailyPatVO.getDepartmentUnitCode());
							 objDailyPatVO.setEpisodeCode(strEpisodeCode);
							 objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
							 
						 }
						 else{
							 
							 objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p, RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(),"ODOPDN");
							
							 objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
							 objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
							 objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
							 objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
							 objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
							 objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
							
							 if((strReferMlcNo!=null && !strReferMlcNo.equals("")) || (objArrEpisodeVO_p[i].getMlcNo()!=null && !objArrEpisodeVO_p[i].getMlcNo().equals(""))){
								 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
							 }/*else{
								 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
							 }*/
							 
							 objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
							 
						 }
					 }
					/////new episode
						/////creating new episode
					 else if (objArrEpisodeVO_p[i].getEpisodeIsOpen().trim().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE))
					 {
						PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
						objDailyPatVO.setEpisodeIsOpen(objArrEpisodeVO_p[i].getEpisodeIsOpen());
						
						
						// if else add on 20-04-2010 for handling force ful visit in case of episode close
							if(objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_TRUE) || 
									objArrEpisodeVO_p[i].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_NOT_EXIST) || 
									objDailyPatVO.getDepartmentUnitCode()==null || objDailyPatVO.getDepartmentUnitCode().equals("") ||
									objArrEpisodeVO_p[i].getDeptUnitIsOnDuty().equals(RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE))
							{
							 
								////////////////*******create old episode *********///////////////////////
								////forceful stamping
								
								System.out.println("PatientBOHelper :: oldDeptVisitStamp :: EPISODE_ISOPEN_FALSE :: Foreceful_Visit");
								if(objArrEpisodeVO_p[i].getOnRequestVisit().equals("1"))
									objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_FORCEFUL);
								else
									objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
							
								///New episode is being created hence visit number 1
								objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
								objDailyPatVO.setEpisodeVisitNo("1");
								
								objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
								objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
								
								objDailyPatVO.setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
								objArrEpisodeVO_p[i].setEpisodeReferCode(objArrEpisodeVO_p[i].getEpisodeCode());
								objDailyPatVO.setIsForceful("1");
								
								// Calling DailyPatientDAO
								objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(),"ODOPDN");
								
								objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
								objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
								objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
								objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
								objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
								
								// Calling EpisodeDAO for generating Episode Code
								String strEpisodeCode =objEpisodeDAO.generateEpisodeCode(objPatientVO_p.getPatCrNo(), objUserVO_p, objDailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setEpisodeCode(strEpisodeCode);
								
								objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
								
								//objDailyPatientDAO.createForceFulVisit(objHisDAO_p,objDailyPatVO, objUserVO_p,strReferMlcNo);
							}
							else
							{
								////////////////*******create old episode *********///////////////////////
								
								////generating episode code 
								//String episode=Integer.toString((Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeCode())+1));
								////////
								//objDailyPatVO.setEpisodeCode(episode);
								//objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
								
								objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD, objArrEpisodeVO_p[i].getIsGeneral(),"ODOPDN");
								
								objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
								objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
								objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
								objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
								objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
								
								// Calling EpisodeDAO for generating Episode Code
								String episode =objEpisodeDAO.generateEpisodeCode(objPatientVO_p.getPatCrNo(), objUserVO_p, objDailyPatVO.getDepartmentUnitCode());
								objDailyPatVO.setEpisodeCode(episode);
						 
								if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_GENERAL))
								{
									objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
								}
								else if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_SPECIALITY))
						 		{
									objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						 		}
								if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_CASUALITY))
								{
									objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
									
								}
						 
								if((strReferMlcNo!=null && !strReferMlcNo.equals("")) || (objArrEpisodeVO_p[i].getMlcNo()!=null && !objArrEpisodeVO_p[i].getMlcNo().equals("")))
								{
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
								}/*else{
									objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
								}*/
								objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
								//objDailyPatVO=objDailyPatientDAO.stampOldDeptVisit(objHisDAO_p,objDailyPatVO, objUserVO_p);
							}//end esle
						
					 }
					 
					 //EpisodeCode is assigned to objDailyPatVO
					 //populate episodeVO from DailyPatVO
					 HelperMethods.populate(objArrEpisodeVO_p[i], objDailyPatVO);
					 //objArrEpisodeVO_p[i].setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 if(strReferMlcNo!=null && !strReferMlcNo.equals(""))
					 {
						 objArrEpisodeVO_p[i].setMlcNo(strReferMlcNo);
					 }
					 objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_OLD_PATIENT);
					 
					 ////////if episode is closed then no need to continue treatment category 
					 if(objArrEpisodeVO_p[i].getEpisodeVisitNo().equals("1")){
						 objArrEpisodeVO_p[i].setSecCatExpDate("");
						 objArrEpisodeVO_p[i].setPatSecondaryCatCode("");
					 }
					 else {
						 if(objArrEpisodeVO_p[i].getPatSecondaryCatCode()==null || objArrEpisodeVO_p[i].getPatSecondaryCatCode().equals(""))
							 objArrEpisodeVO_p[i].setSecCatExpDate("");
						
					 }
					 
					 //Create new entry in Episode detail
					 
					 //if(objArrEpisodeVO_p[i].getSpecifyExpiry().equals(RegistrationConfig.SPECIFY_EXPIRY_FALSE))
					 if((objPatientVO_p.getIsReferred()!=null) && (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)))
						 objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						 
					 
					 System.out.println("objArrEpisodeVO_p[i]"+objArrEpisodeVO_p[i].getExpiryDate());
					 objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_OLD);
					/*if(objArrEpisodeVO_p[i].getArrsnomdPTVisitReason().equals(objPatVisitSUP_p.getArrsnomdPTVisitReason()))
					 objEpisodeDAO.saveEpisodeDtl(objHisDAO_p, objArrEpisodeVO_p[i], objUserVO_p, "1");
					 else
					 {*/
						// objArrEpisodeVO_p[i].setArrPatVisitReason(objPatVisitSUP_p.getArrPatVisitReason());
						 objArrEpisodeVO_p[i].setArrsnomdCIdVisitReason(objPatVisitSUP_p.getArrsnomdCIdVisitReason());
						 objArrEpisodeVO_p[i].setArrsnomdPTVisitReason(objPatVisitSUP_p.getArrsnomdPTVisitReason());
						 objEpisodeDAO.saveEpisodeDtl(objHisDAO_p, objArrEpisodeVO_p[i], objUserVO_p, "1");
					 /*}*/	
						 
					 if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					 {
						/////////episode referDTL////////////////
						String refEpisode=objEpisodeRefDtlVO_p.getEpisodeCode();
						
						////check this part
						//String[] array=objEpisodeDAO.getEpisdoeCode(objHisDAO_p,objArrEpisodeVO_p[i].getPatCrNo(),objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p);
						//objArrEpisodeVO_p[i].setEpisodeVisitNo(array[2]);
						
						String refEpisodeVisit=objEpisodeRefDtlVO_p.getEpisodeVisitNo();
						HelperMethods.populate(objEpisodeRefDtlVO_p, objArrEpisodeVO_p[i]);
						objEpisodeRefDtlVO_p.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)){
							objEpisodeRefDtlVO_p.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
						//	objEpisodeRefDtlVO_p.setEpisodeVisitNo(objArrEpisodeVO_p[i].getEpisodeVisitNo());
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(objArrEpisodeVO_p[i].getEpisodeVisitNo());
							
						}
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)){
							objEpisodeRefDtlVO_p.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objEpisodeRefDtlVO_p.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objEpisodeRefDtlVO_p.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							objEpisodeRefDtlVO_p.setToEpisodeVisitNo(strToVisitNo);
							objEpisodeRefDtlVO_p.setEpisodeCode(refEpisode);
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(refEpisodeVisit);
							objEpisodeRefDtlVO_p.setExternalHospitalCode("");
						}
						
						if(objPatientVO_p.getIsPatReferByList()!=null && objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							objEpisodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO_p, objEpisodeRefDtlVO_p, objUserVO_p,"2");
						}
						else
						{
							objEpisodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO_p, objEpisodeRefDtlVO_p, objUserVO_p,"1");
						}
						
					 }
					 
					 //Credit Category wise Renewal Change Added by Singaravelan on 09-Dec-2014 
					 boolean flagBeneficiaryRenewal=false;
					 HelperMethods.setNullToEmpty(objPatientVO_p);
					 if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
								(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
								objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)))
					 {	
						 //if (objPatientVO_p.getPatRenewalActualAmount() != null && !objPatientVO_p.getPatRenewalActualAmount().equals("") && !objPatientVO_p.getPatRenewalActualAmount().equals("0") &&					 !objPatientVO_p.getPatRenewalActualAmount().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						 if (objPatientVO_p.getRegRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE))	 
								 flagBeneficiaryRenewal=true;					 					 
						 	
					 }
					 else{
						 if (objArrEpisodeVO_p[i].getPatAmountCollected() != null && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("") && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("0") && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						 {
							 flagBeneficiaryRenewal=true;
						 }
					 }					 
					 
						 
					 if (flagBeneficiaryRenewal)
					 {
							objDirectChargeVO[i]= new DirectChageDetailVO();
							objDirectChargeVO[i].setTariffId(objUserVO_p.getTariffID());
							objDirectChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
							objDirectChargeVO[i].setModuleId(objUserVO_p.getModuleId());
							HelperMethods.populate(objDirectChargeVO[i], objArrEpisodeVO_p[i]);
							objDirectChargeVO[i].setPatAmountCollected(objArrEpisodeVO_p[i].getPatAmountCollected());
							objDirectChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
							objDirectChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
							objDirectChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
							
							objDirectChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
							objDirectChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
							objDirectChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
							objDirectChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
							objDirectChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
							objDirectChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
							objDirectChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
							//objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
							objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatRenewalActualAmount());
							objDirectChargeVO[i].setDirectChargeType(RegistrationConfig.DIRECT_CHARGE_TYPE_OPD);
							
							//if (!(objDirectChargeVO[i].getPatAmountCollected() == null || objDirectChargeVO[i].getPatAmountCollected().equals("0") || objDirectChargeVO[i].getPatAmountCollected().equals(""))) 
							//{
								objDirectChargeDAO.create(objHisDAO_p,objDirectChargeVO[i], objUserVO_p);
							//}
							///Billing module integration
							//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
							// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
							
							
							objRenewDetailVO[i] = new RenewalDetailVO();
							objRenewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
							objRenewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
							objRenewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							objRenewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
							objRenewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
							objRenewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objRenewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objRenewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
							objRenewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
							objRenewDetailVO[i].setNewExpiryDate(strExpiryDate_p);
							objRenewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

							objRenewDetailDAO.saveRenewalDtl(objHisDAO_p,objRenewDetailVO[i], objUserVO_p,"1");
							
							
							//For the Details Insertion in the Beneficiary Patient Dtl Table On the Renewal case Added by Singaravelan on 30-Sep-2014
							if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
									(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
									objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))){
								
								patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
								//Need to remove this cond once the staff no constraints from the table is removed
								if((null!=objPatientVO_p.getStaffCardNo()&& null!=objPatientVO_p.getAgsNo())&&(!objPatientVO_p.getStaffCardNo().equals("")||!objPatientVO_p.getAgsNo().equals("")))
								{								
								HelperMethods.populate(objBenPatVO, objPatientVO_p);					
								if(null!=objBenPatVO.getAgsNo() && !objBenPatVO.getAgsNo().equals(""))
									objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
								else if(null!=objBenPatVO.getStaffCardNo() && !objBenPatVO.getStaffCardNo().equals(""))
									objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
								
								if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
									objBenPatVO.setIsDependent("1");
									objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
								}else{
									objBenPatVO.setIsDependent("0");
									objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
								}
								objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
								objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
								
								benPatientDAO.savePatientBeneficiaryDtl(objHisDAO_p, objBenPatVO, objUserVO_p, "1");
								}
													
							}
							
							//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL Added by Singaravelan on 30-Sep-2014
							if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
								HelperMethods.populate(objCreditAvailDtlVO, objDirectChargeVO[i]);
								HelperMethods.setNullToEmpty(objCreditAvailDtlVO);
								objCreditAvailDtlVO.setGroupId(RegistrationConfig.BILL_GROUP_ID);
								objCreditAvailDtlVO.setRecieptNo("1");
								objCreditAvailDtlVO.setRecieptType("1");
								objCreditAvailDtlVO.setBillQty("1");
								objCreditAvailDtlVO.setProcessedQty("1");
								objCreditAvailDtlVO.setRemainedQty("0");
								objCreditAvailDtlVO.setQtyUnitId(RegistrationConfig.BILL_QTY_UNIT_ID);
								objCreditAvailDtlVO.setRateUnitCode(RegistrationConfig.BILL_QTY_UNIT_ID);
								objCreditAvailDtlVO.setBillServiceId("10");
								objCreditAvailDtlVO.setIsPackage("0");
								
								//objCreditAvailDtlVO.setTariffActualRate(objDirectChargeVO[i].getPatActualAmount());
								//objCreditAvailDtlVO.setPaidByClientAmt(objDirectChargeVO[i].getPatActualAmount());
								objCreditAvailDtlVO.setTariffActualRate(objDirectChargeVO[i].getPatRenewalActualAmount());
								objCreditAvailDtlVO.setPaidByClientAmt(objDirectChargeVO[i].getPatRenewalActualAmount());
								
								objCreditAvailDtlVO.setPaidByPatAmt("0");
								//objCreditAvailDtlVO.setNetAmount(objDirectChargeVO[i].getPatActualAmount());
								objCreditAvailDtlVO.setNetAmount(objDirectChargeVO[i].getPatRenewalActualAmount());

								//Need to include approved by here
								
								creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO_p, objCreditAvailDtlVO, objUserVO_p, "1");
							}
							
										
						
						}
					 
				 }
			 
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(""+e);
			else if(e.getClass()==HisDataAccessException.class)
				throw new HisDataAccessException(""+e);
			else if(e.getClass()==HisUpdateUnsuccesfullException.class)
				throw new HisUpdateUnsuccesfullException(""+e);
			else if(e.getClass()==HisInvalidTokenNumberException.class)
				throw new HisInvalidTokenNumberException(""+e);
			else if(e.getClass()==HisAppointmentNotAvailableException.class)
				throw new HisAppointmentNotAvailableException(""+e);
			else
				throw new HisApplicationExecutionException("PatientBOSupport oldDeptVisitStamp "+e);
		}
		
	}
	
	public static void setExpiryInPatientVO(PatientVO objPatientVO_p,String strExpiryDate_p){
		
		System.out.println("PatientBOHelper :: setExpiryInPatientVO()");
		
		String strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
		
		if(strRenewalType!=null && strRenewalType.equals("1"))	//strRenewalType = 1 --> Hospital Wise Common to All
		{
			objPatientVO_p.setPatGeneralExpDate(strExpiryDate_p);
			objPatientVO_p.setPatSpecialExpDate(strExpiryDate_p);
			objPatientVO_p.setPatCasualityExpDate(strExpiryDate_p);
			
		}
		else if(strRenewalType!=null && strRenewalType.equals("2"))	//strRenewalType = 2 --> Hospital Wise For Particular Group
		{
			System.out.println("strRenewalGroup : "+objPatientVO_p.getRenewalConfig().getStrRenewalGroup());
			if(objPatientVO_p.getRenewalConfig().getStrRenewalGroup().equals(RegistrationConfig.RENEWAL_CONFIG_GROUP_OPD)){
				objPatientVO_p.setPatGeneralExpDate(strExpiryDate_p);
				
			}
			if(objPatientVO_p.getRenewalConfig().getStrRenewalGroup().equals(RegistrationConfig.RENEWAL_CONFIG_GROUP_SPEACIAL)){
				objPatientVO_p.setPatSpecialExpDate(strExpiryDate_p);
				
			}
			if(objPatientVO_p.getRenewalConfig().getStrRenewalGroup().equals(RegistrationConfig.RENEWAL_CONFIG_GROUP_CASUAL)){
				objPatientVO_p.setPatCasualityExpDate(strExpiryDate_p);
			}
		}
		else
		{
			objPatientVO_p.setPatGeneralExpDate("");
			objPatientVO_p.setPatSpecialExpDate("");
			objPatientVO_p.setPatCasualityExpDate("");
		}
	}
	public static void setExpiryInPatientVoNDailyPatVoNEpisodeVo(PatientVO objPatientVO_p, DailyPatientVO objDailyPatientVO_p, EpisodeVO objEpisodeVO_p, String strExpiryDate_p){
		
		System.out.println("PatientBOHelper :: setExpiryInPatientVoNDailyPatVoNEpisodeVo()");
		
		String strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
		String strOldExpiryDate="";
		
		if(strRenewalType!=null && strRenewalType.equals("1"))	//strRenewalType = 1 --> Hospital Wise Common to All
		{
			strOldExpiryDate=objPatientVO_p.getExpiryDate();
			
			objPatientVO_p.setPatGeneralExpDate(strExpiryDate_p);
			objPatientVO_p.setPatSpecialExpDate(strExpiryDate_p);
			objPatientVO_p.setPatCasualityExpDate(strExpiryDate_p);
			
			objDailyPatientVO_p.setPatGeneralExpDate(strExpiryDate_p);
			objDailyPatientVO_p.setPatSpecialExpDate(strExpiryDate_p);
			objDailyPatientVO_p.setPatCasualityExpDate(strExpiryDate_p);
			objDailyPatientVO_p.setEpisodeExpiryDate("");
			objEpisodeVO_p.setEpisodeExpiryDate("");
		}
		else if(strRenewalType!=null && strRenewalType.equals("2"))	//strRenewalType = 2 --> Hospital Wise For Particular Group
		{
			strOldExpiryDate=objPatientVO_p.getExpiryDate();
			
			if(objPatientVO_p.getRenewalConfig().getStrRenewalGroup().equals(RegistrationConfig.RENEWAL_CONFIG_GROUP_OPD)){
				objPatientVO_p.setPatGeneralExpDate(strExpiryDate_p);
				objDailyPatientVO_p.setPatGeneralExpDate(strExpiryDate_p);
				
			}
			if(objPatientVO_p.getRenewalConfig().getStrRenewalGroup().equals(RegistrationConfig.RENEWAL_CONFIG_GROUP_SPEACIAL)){
				objPatientVO_p.setPatSpecialExpDate(strExpiryDate_p);
				objDailyPatientVO_p.setPatSpecialExpDate(strExpiryDate_p);
				
			}
			if(objPatientVO_p.getRenewalConfig().getStrRenewalGroup().equals(RegistrationConfig.RENEWAL_CONFIG_GROUP_CASUAL)){
				objPatientVO_p.setPatCasualityExpDate(strExpiryDate_p);
				objDailyPatientVO_p.setPatCasualityExpDate(strExpiryDate_p);
			}
			
			objDailyPatientVO_p.setEpisodeExpiryDate("");
			objEpisodeVO_p.setEpisodeExpiryDate("");
		}
		else if(strRenewalType!=null && (strRenewalType.equals("3") || strRenewalType.equals("4")))
		{
			//RenewalType = 3 --> Dept Unit Wise Common To All
			//RenewalType = 4 --> Dept Unit Wise Unit Specific From Unit Master
			
			strOldExpiryDate=objEpisodeVO_p.getExpiryDate();
			objDailyPatientVO_p.setEpisodeExpiryDate(strExpiryDate_p);
			objEpisodeVO_p.setEpisodeExpiryDate(strExpiryDate_p);
		}
		else
		{
			objEpisodeVO_p.setEpisodeExpiryDate("");
			
			objPatientVO_p.setPatGeneralExpDate("");
			objPatientVO_p.setPatSpecialExpDate("");
			objPatientVO_p.setPatCasualityExpDate("");
			
			objDailyPatientVO_p.setPatGeneralExpDate("");
			objDailyPatientVO_p.setPatSpecialExpDate("");
			objDailyPatientVO_p.setPatCasualityExpDate("");
			objDailyPatientVO_p.setEpisodeExpiryDate("");
		}
		objEpisodeVO_p.setOldExpiryDate(strOldExpiryDate);
	}
	
	
	public static void emgOldDeptVisitStamp(HisDAO objHisDAO_p,EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, JDBCTransactionContext tx,EpisodeRefDtlVO objEpisodeRefDtlVO_p, String strExpiryDate_p)
	{
		DailyPatientDAO objDailyPatientDAO=new DailyPatientDAO(tx);
		EpisodeDAO objEpisodeDAO=new EpisodeDAO(tx);
		EpisodeRefDtlDAO objEpisodeRefDtlDAO=new EpisodeRefDtlDAO(tx);
		String strToVisitNo="";
		String strPatVisitReasonForOldDeptVisit="";
		String strRenewalTypeHospOrEpisode="";
		String patPrimaryCatGrp="";
		//String strRenewalTypeHospOrEpisode="";
		//String strNewExpiryDate="",strOldExpiryDate="";
		try{
				System.out.println("PatientBOHelper :: emgOldDeptVisitStamp()");
				
				
				if(objPatientVO_p.getRenewalConfig().getStrRenewalType()!=null && 
						(objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("3") || objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("4"))){
					strRenewalTypeHospOrEpisode="E";
				}else{
					strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
				}
				DirectChageDetailVO[] objDirectChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
				RenewalDetailVO[] objRenewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
				BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
				CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
				
				DirectChargeDetailDAO objDirectChargeDAO=new DirectChargeDetailDAO(tx);
				RenewalDetailDAO objRenewDetailDAO = new RenewalDetailDAO(tx);
				BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
				CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
				
				String strReferMlcNo=objEpisodeRefDtlVO_p.getMlcNo();
				 
				 for(int i=0; i<objArrEpisodeVO_p.length; i++)
				 {
					 strPatVisitReasonForOldDeptVisit=objArrEpisodeVO_p[i].getPatVisitReason();
					 ///value of is olfd ==1
					 objArrEpisodeVO_p[i].setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 
					 DailyPatientVO objDailyPatVO=new DailyPatientVO();
					 DailyPatientVO objTmpdailyPatVO=new DailyPatientVO();
					 
					 if (objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE)){
						 strToVisitNo="1";
					 }
					 else{
						 if(objArrEpisodeVO_p[i].getEpisodeVisitNo()!=null)
							 strToVisitNo=Integer.toString(Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeVisitNo())+1);
					 }
					 //setting visit number instead of firing query//
					 objArrEpisodeVO_p[i].setEpisodeVisitNo(strToVisitNo);
					 
					
					 //Create DailyPatientVO from patientVO:  Populate it
					 HelperMethods.populate(objDailyPatVO, objPatientVO_p);
					  //populate dailyPatient VO with objArrEpisodeVO_p[i]
					 HelperMethods.populate(objDailyPatVO, objArrEpisodeVO_p[i]);
					 //objDailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
					 
					 
					if(strRenewalTypeHospOrEpisode.equals("E") && objArrEpisodeVO_p[i].getRenewalRequired()!=null 
							&& objArrEpisodeVO_p[i].getRenewalRequired().equalsIgnoreCase("0")){
						objDailyPatVO.setPatPrimaryCatCode(objArrEpisodeVO_p[i].getPatPrimaryCatCode());
					}else{
						objDailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
					}
					 
					
					 //no need to generate file number from old patient
					 objDailyPatVO.setIsNewFileRequired(RegistrationConfig.FILE_NO_GENRATION_FALSE);
					 objDailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 objDailyPatVO.setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 objDailyPatVO.setRoundRobinUnitFlag("0");
					 
					//By Mukund On 07.09.2016 for expiry date manipulation
					 //setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
					 if (objArrEpisodeVO_p[i].getRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE))	
						 setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
					 else
						 objDailyPatVO.setEpisodeExpiryDate(objDailyPatVO.getExpiryDate());
					 //end:Mukund
					 
					 if(objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_TRUE))
					 {
						 //ROSTERTYPE_OPD should be replaced with ROSTERTYPE_EMG
						 objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p, RegistrationConfig.ROSTERTYPE_EMG, objArrEpisodeVO_p[i].getIsGeneral(),"ODEMG");
						
						 objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
						 objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
						 objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
						 objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
						 objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
						 objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
						
						 if(RegistrationConfig.IS_MLC_TRUE.equals(objArrEpisodeVO_p[i].getMlcFlag())){
							 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
						 }else{
							 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
						 }
						 
						 
						 
						 objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
							 
					 }
					/////new episode
						/////creating new episode
					 else if (objArrEpisodeVO_p[i].getEpisodeIsOpen().trim().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE))
					 {
						PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
						
						
						////////////////*******create old episode *********///////////////////////
						
						////generating episode code 
						String episode=Integer.toString((Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeCode())+1));
						////////
						objDailyPatVO.setEpisodeCode(episode);
						
						objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_EMG, objArrEpisodeVO_p[i].getIsGeneral(),"ODEMG");
						
						objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
						objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
						objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
						objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
						objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
						objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
				 
						System.out.println("IsGeneral :"+ objArrEpisodeVO_p[i].getIsGeneral());
						System.out.println("strReferMlcNo (episodeRef):"+ strReferMlcNo);
						System.out.println("episode MlcNo :"+ objArrEpisodeVO_p[i].getMlcNo());
						if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_CASUALITY))
						{
							objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
							
							if(RegistrationConfig.IS_MLC_TRUE.equals(objArrEpisodeVO_p[i].getMlcFlag())){
								 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
							}else{
								 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
							}
						}
						else if(objArrEpisodeVO_p[i].getIsGeneral().equals(RegistrationConfig.UNIT_TYPE_SPECIALITY))
				 		{
							objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
							objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				 		}
				 
						//setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
						
						objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
						//objDailyPatVO=objDailyPatientDAO.stampOldDeptVisit(objHisDAO_p,objDailyPatVO, objUserVO_p);
						
					 }
					 
					 //EpisodeCode is assigned to objDailyPatVO
					 //populate episodeVO from DailyPatVO
					 HelperMethods.populate(objArrEpisodeVO_p[i], objDailyPatVO);
					 //objArrEpisodeVO_p[i].setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 if(strReferMlcNo!=null && !strReferMlcNo.equals(""))
					 {
						 objArrEpisodeVO_p[i].setMlcNo(strReferMlcNo);
					 }
					 objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_OLD_PATIENT);
					 
					 ////////if episode is closed then no need to continue treatment category 
					 if(objArrEpisodeVO_p[i].getEpisodeVisitNo().equals("1")){
						 objArrEpisodeVO_p[i].setSecCatExpDate("");
						 objArrEpisodeVO_p[i].setPatSecondaryCatCode("");
					 }
					 else {
						 if(objArrEpisodeVO_p[i].getPatSecondaryCatCode()==null || objArrEpisodeVO_p[i].getPatSecondaryCatCode().equals(""))
							 objArrEpisodeVO_p[i].setSecCatExpDate("");
						
					 }
					 
					 //Create new entry in Episode detail
					 
					 //if(objArrEpisodeVO_p[i].getSpecifyExpiry().equals(RegistrationConfig.SPECIFY_EXPIRY_FALSE))
					 if((objPatientVO_p.getIsReferred()!=null) && (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)))
						 objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						 
					 
					 System.out.println("objArrEpisodeVO_p[i]"+objArrEpisodeVO_p[i].getExpiryDate());
					 
					 objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_OLD);
					 //objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
					// objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
					 objEpisodeDAO.saveEpisodeDtl(objHisDAO_p, objArrEpisodeVO_p[i], objUserVO_p, "1");
						
						 
					 if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					 {
						/////////episode referDTL////////////////
						String refEpisode=objEpisodeRefDtlVO_p.getEpisodeCode();
						
						String refEpisodeVisit=objEpisodeRefDtlVO_p.getEpisodeVisitNo();
						HelperMethods.populate(objEpisodeRefDtlVO_p, objArrEpisodeVO_p[i]);
						objEpisodeRefDtlVO_p.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
						
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							objEpisodeRefDtlVO_p.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(objArrEpisodeVO_p[i].getEpisodeVisitNo());
						}
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							objEpisodeRefDtlVO_p.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objEpisodeRefDtlVO_p.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objEpisodeRefDtlVO_p.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							objEpisodeRefDtlVO_p.setToEpisodeVisitNo(strToVisitNo);
							objEpisodeRefDtlVO_p.setEpisodeCode(refEpisode);
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(refEpisodeVisit);
							objEpisodeRefDtlVO_p.setExternalHospitalCode("");
						}
						
						// Calling EpisodeRefDtlDAO
						objEpisodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO_p, objEpisodeRefDtlVO_p, objUserVO_p,"1");
						
					 }
					 
					 //Credit Category wise Renewal Change Added by Singaravelan on 09-Dec-2014 
					 boolean flagBeneficiaryRenewal=false;
					 HelperMethods.setNullToEmpty(objPatientVO_p);
					 if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
								(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
								objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)))
					 {	
						 //if (objPatientVO_p.getPatRenewalActualAmount() != null && !objPatientVO_p.getPatRenewalActualAmount().equals("") && !objPatientVO_p.getPatRenewalActualAmount().equals("0") &&					 !objPatientVO_p.getPatRenewalActualAmount().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						 if (objArrEpisodeVO_p[i].getRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE))	 
								 flagBeneficiaryRenewal=true;					 					 
						 	
					 }
					 else{
						 if (objArrEpisodeVO_p[i].getPatAmountCollected() != null && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("") && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("0") && 
								 !objArrEpisodeVO_p[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						 {
							 flagBeneficiaryRenewal=true;
						 }
					 }	
					 
					 
					/* Code Added BY Garima on 27th July 2016
					 */
					 
					 if (flagBeneficiaryRenewal)
					 {
							objDirectChargeVO[i]= new DirectChageDetailVO();
							objDirectChargeVO[i].setTariffId(objUserVO_p.getTariffID());
							objDirectChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
							objDirectChargeVO[i].setModuleId(objUserVO_p.getModuleId());
							HelperMethods.populate(objDirectChargeVO[i], objArrEpisodeVO_p[i]);
							objDirectChargeVO[i].setPatAmountCollected(objArrEpisodeVO_p[i].getPatAmountCollected());
							objDirectChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
							objDirectChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
							objDirectChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
							
							objDirectChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
							objDirectChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
							objDirectChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
							objDirectChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
							objDirectChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
							objDirectChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
							objDirectChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
							//objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
							objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatRenewalActualAmount());
							objDirectChargeVO[i].setPatRenewalActualAmount(objPatientVO_p.getPatRenewalActualAmount());
							objDirectChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());

							objDirectChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
							if(null!=objPatientVO_p.getAgsNo() && !objPatientVO_p.getAgsNo().equals(""))
								objDirectChargeVO[i].setCardNo(objPatientVO_p.getAgsNo());
							else if(null!=objPatientVO_p.getStaffCardNo() && !objPatientVO_p.getStaffCardNo().equals(""))
							{
								objDirectChargeVO[i].setCardNo(objPatientVO_p.getStaffCardNo());
								objDirectChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
							}
							
							
							//if (!(objDirectChargeVO[i].getPatAmountCollected() == null || objDirectChargeVO[i].getPatAmountCollected().equals("0") || objDirectChargeVO[i].getPatAmountCollected().equals(""))) 
							//{
								objDirectChargeDAO.create(objHisDAO_p,objDirectChargeVO[i], objUserVO_p);
							//}
							///Billing module integration
							//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
							// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
							
							
							objRenewDetailVO[i] = new RenewalDetailVO();
							objRenewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
							objRenewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
							objRenewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							objRenewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
							objRenewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
							objRenewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objRenewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objRenewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
							objRenewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
							objRenewDetailVO[i].setNewExpiryDate(strExpiryDate_p);
							// added by Surabhi for change in the expiry date for renewal
							objArrEpisodeVO_p[i].setExpiryDate(strExpiryDate_p);
							//end
							objRenewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

							objRenewDetailDAO.saveRenewalDtl(objHisDAO_p,objRenewDetailVO[i], objUserVO_p,"1");
							
							
							//For the Details Insertion in the Beneficiary Patient Dtl Table On the Renewal case Added by Singaravelan on 30-Sep-2014
							if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
									(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) 
											//|| objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
											)){
								
								patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
								//Need to remove this cond once the staff no constraints from the table is removed
								if((null!=objPatientVO_p.getStaffCardNo()|| null!=objPatientVO_p.getAgsNo())&&(!objPatientVO_p.getStaffCardNo().equals("")||!objPatientVO_p.getAgsNo().equals("")))
								{								
								HelperMethods.populate(objBenPatVO, objPatientVO_p);					
								if(null!=objBenPatVO.getAgsNo() && !objBenPatVO.getAgsNo().equals(""))
									objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
								else if(null!=objBenPatVO.getStaffCardNo() && !objBenPatVO.getStaffCardNo().equals(""))
									objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
								
								if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
									objBenPatVO.setIsDependent("1");
									objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
								}else{
									objBenPatVO.setIsDependent("0");
									objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
								}
								objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
								objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
								
								//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO_p, objBenPatVO, objUserVO_p, "1");
								benPatientDAO.savePatientCreditDtl(objHisDAO_p, objBenPatVO, objUserVO_p, "1");
								}
													
							}
							
							//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL Added by Singaravelan on 30-Sep-2014
							if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
								HelperMethods.populate(objCreditAvailDtlVO, objDirectChargeVO[i]);
								HelperMethods.setNullToEmpty(objCreditAvailDtlVO);
								objCreditAvailDtlVO.setGroupId(RegistrationConfig.BILL_GROUP_ID);
								objCreditAvailDtlVO.setRecieptNo("1");
								objCreditAvailDtlVO.setRecieptType("1");
								objCreditAvailDtlVO.setBillQty("1");
								objCreditAvailDtlVO.setProcessedQty("1");
								objCreditAvailDtlVO.setRemainedQty("0");
								objCreditAvailDtlVO.setQtyUnitId(RegistrationConfig.BILL_QTY_UNIT_ID);
								objCreditAvailDtlVO.setRateUnitCode(RegistrationConfig.BILL_QTY_UNIT_ID);
								objCreditAvailDtlVO.setBillServiceId("10");
								objCreditAvailDtlVO.setIsPackage("0");
								
								//objCreditAvailDtlVO.setTariffActualRate(objDirectChargeVO[i].getPatActualAmount());
								//objCreditAvailDtlVO.setPaidByClientAmt(objDirectChargeVO[i].getPatActualAmount());
								objCreditAvailDtlVO.setTariffActualRate(objDirectChargeVO[i].getPatRenewalActualAmount());
								objCreditAvailDtlVO.setPaidByClientAmt(objDirectChargeVO[i].getPatRenewalActualAmount());
								
								objCreditAvailDtlVO.setPaidByPatAmt("0");
								//objCreditAvailDtlVO.setNetAmount(objDirectChargeVO[i].getPatActualAmount());
								objCreditAvailDtlVO.setNetAmount(objDirectChargeVO[i].getPatRenewalActualAmount());
								objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());

								//Need to include approved by here
								
								creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO_p, objCreditAvailDtlVO, objUserVO_p, "1");
							}
							
										
						
						}
					 
					 /* End of code Added by Garima*/
						 
					/* if (objArrEpisodeVO_p[i].getPatAmountCollected() != null && 
							 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("") && 
							 !objArrEpisodeVO_p[i].getPatAmountCollected().equals("0") && 
							 !objArrEpisodeVO_p[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						{
							objDirectChargeVO[i]= new DirectChageDetailVO();
							objDirectChargeVO[i].setTariffId(objUserVO_p.getTariffID());
							objDirectChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
							objDirectChargeVO[i].setModuleId(objUserVO_p.getModuleId());
							HelperMethods.populate(objDirectChargeVO[i], objArrEpisodeVO_p[i]);
							objDirectChargeVO[i].setPatAmountCollected(objArrEpisodeVO_p[i].getPatAmountCollected());
							objDirectChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
							objDirectChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
							objDirectChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
							
							
							if (!(objDirectChargeVO[i].getPatAmountCollected().equals("0") || objDirectChargeVO[i].getPatAmountCollected().equals("") || objDirectChargeVO[i]
									.getPatAmountCollected() == null)) 
							{
								objDirectChargeDAO.create(objHisDAO_p,objDirectChargeVO[i], objUserVO_p);
							}
							///Billing module integration
							//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
							// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
							
							
							objRenewDetailVO[i] = new RenewalDetailVO();
							objRenewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
							objRenewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
							objRenewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							objRenewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
							objRenewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
							objRenewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objRenewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objRenewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
							objRenewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
							objRenewDetailVO[i].setNewExpiryDate(strExpiryDate_p);
							objRenewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

							objRenewDetailDAO.saveRenewalDtl(objHisDAO_p,objRenewDetailVO[i], objUserVO_p,"1");
							
										
						
						}*/
					 
				 }
			 
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(""+e);
			else if(e.getClass()==HisDataAccessException.class)
				throw new HisDataAccessException(""+e);
			else if(e.getClass()==HisUpdateUnsuccesfullException.class)
				throw new HisUpdateUnsuccesfullException(""+e);
			else if(e.getClass()==HisInvalidTokenNumberException.class)
				throw new HisInvalidTokenNumberException(""+e);
			else if(e.getClass()==HisAppointmentNotAvailableException.class)
				throw new HisAppointmentNotAvailableException(""+e);
			else
				throw new HisApplicationExecutionException("PatientBOSupport emgOldDeptVisitStamp "+e);
		}
		
	}	
	public static void spclOldDeptVisitStamp(HisDAO objHisDAO_p,EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, JDBCTransactionContext tx,EpisodeRefDtlVO objEpisodeRefDtlVO_p, String strExpiryDate_p)
	{
		DailyPatientDAO objDailyPatientDAO=new DailyPatientDAO(tx);
		EpisodeDAO objEpisodeDAO=new EpisodeDAO(tx);
		EpisodeRefDtlDAO objEpisodeRefDtlDAO=new EpisodeRefDtlDAO(tx);
		String strToVisitNo="";
		String strPatVisitReasonForOldDeptVisit="";
		String patPrimaryCatGrp="";
		
		try{
				System.out.println("PatientBOHelper :: spclOldDeptVisitStamp()");
				DirectChageDetailVO[] objDirectChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
				RenewalDetailVO[] objRenewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
				BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
				CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
				
				DirectChargeDetailDAO objDirectChargeDAO=new DirectChargeDetailDAO(tx);
				RenewalDetailDAO objRenewDetailDAO = new RenewalDetailDAO(tx);
				BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
				CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
				
				 String strReferMlcNo=objEpisodeRefDtlVO_p.getMlcNo();
				 
				 for(int i=0; i<objArrEpisodeVO_p.length; i++)
				 {
					 strPatVisitReasonForOldDeptVisit=objArrEpisodeVO_p[i].getPatVisitReason();
					 
					 ///value of is olfd ==1
					 objArrEpisodeVO_p[i].setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 //objArrEpisodeVO_p[i].setTariffId(objUserVO_p.getTariffID());
					 
					 DailyPatientVO objDailyPatVO=new DailyPatientVO();
					 DailyPatientVO objTmpdailyPatVO=new DailyPatientVO();
					 
					 if (objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE)){
						 strToVisitNo="1";
					 }
					 else{
						 if(objArrEpisodeVO_p[i].getEpisodeVisitNo()!=null)
							 strToVisitNo=Integer.toString(Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeVisitNo())+1);
					 }
					 //setting visit number instead of firing query//
					 objArrEpisodeVO_p[i].setEpisodeVisitNo(strToVisitNo);
					 
					
					 //Create DailyPatientVO from patientVO:  Populate it
					 HelperMethods.populate(objDailyPatVO, objPatientVO_p);
					 objArrEpisodeVO_p[i].setPatPrimaryCat(objPatientVO_p.getPatPrimaryCat());
					 //populate dailyPatient VO with objArrEpisodeVO_p[i]
					 HelperMethods.populate(objDailyPatVO, objArrEpisodeVO_p[i]);
					 objDailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
					
					 //no need to generate file number from old patient
					 objDailyPatVO.setIsNewFileRequired(RegistrationConfig.FILE_NO_GENRATION_FALSE);
					 objDailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					 objDailyPatVO.setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 
					//setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
					//By Mukund On 07.09.2016 UAT issue to set expiry date
					if (objArrEpisodeVO_p[i].getRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE))	
						 setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, objDailyPatVO, objArrEpisodeVO_p[i], strExpiryDate_p);
					 else
						 objDailyPatVO.setEpisodeExpiryDate(objDailyPatVO.getExpiryDate());
					 //end:Mukund
					 
					 if(objArrEpisodeVO_p[i].getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_TRUE))
					 {
						 objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p, RegistrationConfig.ROSTERTYPE_SPECIAL, RegistrationConfig.UNIT_TYPE_SPECIALITY,"ODSPL");
						
						 objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
						 objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
						 objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
						 objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
						 objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
						 objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
						 objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						 objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						 
						 // Calling DAO
						 objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
							 
					 }
					/////new episode
						/////creating new episode
					 else if (objArrEpisodeVO_p[i].getEpisodeIsOpen().trim().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE))
					 {
						PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
						
							
						////////////////*******create old episode *********///////////////////////
						
						////generating episode code 
						String episode=Integer.toString((Integer.parseInt(objArrEpisodeVO_p[i].getEpisodeCode())+1));
						
						objDailyPatVO.setEpisodeCode(episode);
						//objDailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
						
						objTmpdailyPatVO=objDailyPatientDAO.generateQueueNumber(objDailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_SPECIAL, RegistrationConfig.UNIT_TYPE_SPECIALITY,"ODSPL");
						
						objDailyPatVO.setBillNo(objDailyPatientDAO.generateBillNo(objUserVO_p, "1"));
						objDailyPatVO.setDepartmentCode(objTmpdailyPatVO.getDepartmentCode());
						objDailyPatVO.setDepartmentUnitCode(objTmpdailyPatVO.getDepartmentUnitCode());
						objDailyPatVO.setRoomCode(objTmpdailyPatVO.getRoomCode());
						objDailyPatVO.setQueNo(objTmpdailyPatVO.getQueNo());
						objDailyPatVO.setPatientAllowed(objTmpdailyPatVO.getPatientAllowed());
				 
						objDailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						objDailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				 		
						//Calling DAO
				 		objDailyPatientDAO.saveDailyPatientDtl(objHisDAO_p, objDailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_OLD);
						
					 }
					 
					 //EpisodeCode is assigned to objDailyPatVO
					 //populate episodeVO from DailyPatVO
					 HelperMethods.populate(objArrEpisodeVO_p[i], objDailyPatVO);
					 
					//To Populate the Appointment Details
					if(objPatientVO_p.getPatAptNo()!=null&&objPatientVO_p.getPatAptNo()!="")
					{
						objArrEpisodeVO_p[i].setPatAptNo(objPatientVO_p.getPatAptNo());
						objArrEpisodeVO_p[i].setPatAptSlot(objPatientVO_p.getPatAptSlot());
						objArrEpisodeVO_p[i].setPatAptQueueNO(objPatientVO_p.getPatAptQueueNO());
						objArrEpisodeVO_p[i].setIsAppointment("1");
					}
					 
					 //objArrEpisodeVO_p[i].setPatVisitReason(strPatVisitReasonForOldDeptVisit);
					 if(strReferMlcNo!=null && !strReferMlcNo.equals(""))
					 {
						 objArrEpisodeVO_p[i].setMlcNo(strReferMlcNo);
					 }
					 objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_OLD_PATIENT);
					 
					 ////////if episode is closed then no need to continue treatment category 
					 if(objArrEpisodeVO_p[i].getEpisodeVisitNo().equals("1")){
						 objArrEpisodeVO_p[i].setSecCatExpDate("");
						 objArrEpisodeVO_p[i].setPatSecondaryCatCode("");
					 }
					 else {
						 if(objArrEpisodeVO_p[i].getPatSecondaryCatCode()==null || objArrEpisodeVO_p[i].getPatSecondaryCatCode().equals(""))
							 objArrEpisodeVO_p[i].setSecCatExpDate("");
						
					 }
					 
					 //Create new entry in Episode detail
					 
					 //if(objArrEpisodeVO_p[i].getSpecifyExpiry().equals(RegistrationConfig.SPECIFY_EXPIRY_FALSE))
					 if((objPatientVO_p.getIsReferred()!=null) && (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)))
						 objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						 
					 
					 System.out.println("objArrEpisodeVO_p[i]"+objArrEpisodeVO_p[i].getExpiryDate());
					 objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_OLD);
					 //added by Mukund on 02.08.2016
					 if((objArrEpisodeVO_p[i].getCreditLetterRefNo()==null) || objArrEpisodeVO_p[i].getCreditLetterRefNo().equals(""))
						 objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
					 if((objArrEpisodeVO_p[i].getCreditLetterDate()==null) || objArrEpisodeVO_p[i].getCreditLetterDate().equals(""))					
						 objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
					 objEpisodeDAO.saveEpisodeDtl(objHisDAO_p, objArrEpisodeVO_p[i], objUserVO_p, "1");
						
						 
					 if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					 {
						/////////episode referDTL////////////////
						String refEpisode=objEpisodeRefDtlVO_p.getEpisodeCode();
						
						////check this part
						//String[] array=objEpisodeDAO.getEpisdoeCode(objHisDAO_p,objArrEpisodeVO_p[i].getPatCrNo(),objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p);
						//objArrEpisodeVO_p[i].setEpisodeVisitNo(array[2]);
						
						String refEpisodeVisit=objEpisodeRefDtlVO_p.getEpisodeVisitNo();
						HelperMethods.populate(objEpisodeRefDtlVO_p, objArrEpisodeVO_p[i]);
						objEpisodeRefDtlVO_p.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)){
							objEpisodeRefDtlVO_p.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
						//	objEpisodeRefDtlVO_p.setEpisodeVisitNo(objArrEpisodeVO_p[i].getEpisodeVisitNo());
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(objArrEpisodeVO_p[i].getEpisodeVisitNo());
							
						}
						if(objEpisodeRefDtlVO_p.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)){
							objEpisodeRefDtlVO_p.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objEpisodeRefDtlVO_p.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objEpisodeRefDtlVO_p.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							objEpisodeRefDtlVO_p.setToEpisodeVisitNo(strToVisitNo);
							objEpisodeRefDtlVO_p.setEpisodeCode(refEpisode);
							objEpisodeRefDtlVO_p.setEpisodeVisitNo(refEpisodeVisit);
							objEpisodeRefDtlVO_p.setExternalHospitalCode("");
						}
						
						if((objPatientVO_p.getIsPatReferByList()!=null && objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))||
										(objPatientVO_p.getIsPatReferByList()!=null && objPatientVO_p.getPatIsCrossConsultantWithReferal().equals("1") && objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)))
						{
							objEpisodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO_p, objEpisodeRefDtlVO_p, objUserVO_p,"2");
						}
						else
						{
							objEpisodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO_p, objEpisodeRefDtlVO_p, objUserVO_p,"1");
						}
						
					 }
					/*By Mukund on 01.08.2016 for saving data for splPatVstWthAppmnt oldDeptVisit inside credit patient dtl*/
					/*if (objArrEpisodeVO_p[i].getPatAmountCollected() != null && !objArrEpisodeVO_p[i].getPatAmountCollected().equals("") && 
							!objArrEpisodeVO_p[i].getPatAmountCollected().equals("0") && !objArrEpisodeVO_p[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
					{*/
					 boolean flagBeneficiaryRenewal=false;
					 HelperMethods.setNullToEmpty(objPatientVO_p);
					 if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && (objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
						objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)))
					 {	
						if (objArrEpisodeVO_p[i].getRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE))	 
								 flagBeneficiaryRenewal=true;					 					 
					 }
					 else{
						 if (objArrEpisodeVO_p[i].getPatAmountCollected() != null && !objArrEpisodeVO_p[i].getPatAmountCollected().equals("") && 
							!objArrEpisodeVO_p[i].getPatAmountCollected().equals("0") && !objArrEpisodeVO_p[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						 {
							 flagBeneficiaryRenewal=true;
						 }
					 }					 
					 
						 
					 if (flagBeneficiaryRenewal)
						{//end:Mukund
							objDirectChargeVO[i]= new DirectChageDetailVO();
							objDirectChargeVO[i].setTariffId(objUserVO_p.getTariffID());
							objDirectChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
							objDirectChargeVO[i].setModuleId(objUserVO_p.getModuleId());
							HelperMethods.populate(objDirectChargeVO[i], objArrEpisodeVO_p[i]);
							objDirectChargeVO[i].setPatAmountCollected(objArrEpisodeVO_p[i].getPatAmountCollected());
							objDirectChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
							objDirectChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
							objDirectChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
							
							objDirectChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
							objDirectChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
							objDirectChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
							objDirectChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
							objDirectChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
							objDirectChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
							objDirectChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
							objDirectChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());

							//by mukund on 03.08.2016 
							//objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
							if(objPatientVO_p.getPatActualAmount()!=null && !objPatientVO_p.getPatActualAmount().equals(""))
								objDirectChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
							else
								objDirectChargeVO[i].setPatActualAmount(objArrEpisodeVO_p[i].getPatActualAmount());
							
							//if (!(objDirectChargeVO[i].getPatAmountCollected() == null || objDirectChargeVO[i].getPatAmountCollected().equals("0") || objDirectChargeVO[i].getPatAmountCollected().equals(""))) 
							//{
								objDirectChargeDAO.create(objHisDAO_p,objDirectChargeVO[i], objUserVO_p);
							//}
							///Billing module integration
							//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
							// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
							
							objRenewDetailVO[i] = new RenewalDetailVO();
							objRenewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
							objRenewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
							objRenewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							objRenewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
							objRenewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
							objRenewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							objRenewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							objRenewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
							objRenewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
							objRenewDetailVO[i].setNewExpiryDate(strExpiryDate_p);
							// added by Surabhi for change in the expiry date for renewal
							objArrEpisodeVO_p[i].setExpiryDate(strExpiryDate_p);
							//end
							objRenewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

							// Calling DAO
							objRenewDetailDAO.saveRenewalDtl(objHisDAO_p,objRenewDetailVO[i], objUserVO_p,"1");
					 
					 	//For the Details Insertion in the Beneficiary Patient Dtl Table On the Renewal case Added by Singaravelan on 30-Sep-2014
						if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
								(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
								objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))){
							
							patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
							//Need to remove this cond once the staff no constraints from the table is removed
							if(!objPatientVO_p.getStaffCardNo().equals("")||!objPatientVO_p.getAgsNo().equals("")){
							
							HelperMethods.populate(objBenPatVO, objPatientVO_p);					
							if(!objBenPatVO.getAgsNo().equals(""))
								objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
							else if(!objBenPatVO.getStaffCardNo().equals(""))
								objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
							
							if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
								objBenPatVO.setIsDependent("1");
								objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
							}else{
								objBenPatVO.setIsDependent("0");
								objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
							}
							objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
							objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
							//By Mukund On 29.07.2016
							//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO_p, objBenPatVO, objUserVO_p, "1");
							benPatientDAO.savePatientCreditDtl(objHisDAO_p, objBenPatVO, objUserVO_p, "1");
							}
												
						}
						
						//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL Added by Singaravelan on 30-Sep-2014
						if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
							HelperMethods.populate(objCreditAvailDtlVO, objDirectChargeVO[i]);
							HelperMethods.setNullToEmpty(objCreditAvailDtlVO);
							objCreditAvailDtlVO.setGroupId(RegistrationConfig.BILL_GROUP_ID);
							objCreditAvailDtlVO.setRecieptNo("1");
							objCreditAvailDtlVO.setRecieptType("1");
							objCreditAvailDtlVO.setBillQty("1");
							objCreditAvailDtlVO.setProcessedQty("1");
							objCreditAvailDtlVO.setRemainedQty("0");
							objCreditAvailDtlVO.setQtyUnitId(RegistrationConfig.BILL_QTY_UNIT_ID);
							objCreditAvailDtlVO.setRateUnitCode(RegistrationConfig.BILL_QTY_UNIT_ID);
							objCreditAvailDtlVO.setBillServiceId("10");
							objCreditAvailDtlVO.setIsPackage("0");
							
							objCreditAvailDtlVO.setTariffActualRate(objDirectChargeVO[i].getPatActualAmount());
							objCreditAvailDtlVO.setPaidByClientAmt(objDirectChargeVO[i].getPatActualAmount());
							objCreditAvailDtlVO.setPaidByPatAmt("0");
							objCreditAvailDtlVO.setNetAmount(objDirectChargeVO[i].getPatActualAmount());
							objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());

							//Need to include approved by here
							
							creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO_p, objCreditAvailDtlVO, objUserVO_p, "1");
						}
				 }
			}
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(""+e);
			else if(e.getClass()==HisDataAccessException.class)
				throw new HisDataAccessException(""+e);
			else if(e.getClass()==HisUpdateUnsuccesfullException.class)
				throw new HisUpdateUnsuccesfullException(""+e);
			else if(e.getClass()==HisInvalidTokenNumberException.class)
				throw new HisInvalidTokenNumberException(""+e);
			else if(e.getClass()==HisAppointmentNotAvailableException.class)
				throw new HisAppointmentNotAvailableException(""+e);
			else
				throw new HisApplicationExecutionException("PatientBOSupport spclOldDeptVisitStamp "+e);
		}
		
	}
}
