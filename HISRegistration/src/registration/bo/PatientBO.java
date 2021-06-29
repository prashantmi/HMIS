package registration.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisSQLManualException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Printer;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;
import in.cdacnoida.idgenerator.GenerateID;
import in.cdacnoida.idgenerator.HealthId;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.apache.commons.io.FileUtils;

//import org.codehaus.jettison.json.*;
//import org.codehaus.jackson.*;
//import com.google.gson.*;


import org.apache.commons.lang3.ArrayUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import registration.config.PatientBOHelper;
import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import registration.config.Exceptions.HisAppointmentNotAvailableException;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisUpdateUnsuccesfullException;
import registration.dao.AddressDAO;
import registration.dao.BeneficiaryPatientDAO;
import registration.dao.CertificateIssueDtlDAO;
import registration.dao.CrNoMergeDAO;
import registration.dao.CreditAvailDtlDAO;
import registration.dao.DailyPatientDAO;
import registration.dao.DirectChargeDetailDAO;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeRefDtlDAO;
import registration.dao.EssentialDAO;
import registration.dao.MLCRevokeEpisodeDetailDAO;
import registration.dao.MlcDAO;
import registration.dao.MlcParameterDAO;
import registration.dao.PatientAuditDAO;
import registration.dao.PatientBroughtByDtlDAO;
import registration.dao.PatientDAO;
import registration.dao.PatientDeathDetailDAO;
import registration.dao.PatientHospitalDtlDAO;
import registration.dao.PatientIdDetailDAO;
import registration.dao.PatientImageDtlDAO;
import registration.dao.PatientModificationDAO;
import registration.dao.PatientTempDetailDAO;
import registration.dao.PrimaryCategoryChangeDAO;
import registration.dao.RegCardPrintDtlDAO;
import registration.dao.RegEssentialDAO;
import registration.dao.RenewalDetailDAO;
import registration.dao.UnknownChangeDtlDAO;
import registration.dao.UnknownVerificationDtlDAO;
//import registration.dao.UnknownChangeDtlDAO;
//import registration.dao.UnknownVerificationDtlDAO;
import vo.registration.AddressVO;
import vo.registration.BeneficiaryPatientVO;
import vo.registration.CertificateIssueDtlVO;
import vo.registration.CrNoMergeDtlVO;
import vo.registration.CreditAvailDetailVO;
import vo.registration.DailyPatientVO;
import vo.registration.DirectChageDetailVO;
import vo.registration.EmpMasterVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeReferVO;
import vo.registration.EpisodeVO;
import vo.registration.MlcParameterDtlVO;
import vo.registration.MlcToNonMlcVO;
import vo.registration.MlcVO;
import vo.registration.PatientBroughtByDtlVO;
import vo.registration.PatientDeathDetailVO;
import vo.registration.PatientHospitalDtlVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientImageDtlVO;
import vo.registration.PatientModificationVO;
import vo.registration.PatientVO;
import vo.registration.PoliceVerificationDtlVO;
import vo.registration.PrimaryCategoryChangeVO;
import vo.registration.RegCardPrintVO;
import vo.registration.RegistrationCancellationVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import vo.registration.RenewalDetailVO;
import vo.registration.UnknownChangeDtlVO;
import vo.registration.UnknownVerificationDtlVO;
import vo.registration.VerificationDocumentVO;
import registration.dao.RegistrationCancellationDAO;
import registration.dao.ChangeTreatmentCategoryDAO;
import registration.mongodb.MongoXmlHandler;
import registration.transactions.controller.data.EmgRegistrationDATA;
import registration.transactions.controller.data.NewRegistrationDATA;
import vo.registration.ChangeTreatmentCategoryVO;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import vo.registration.BarcodeSlipVO;
import registration.dao.BarcodeGenerationDAO;
import vo.registration.PatientBroughtByDtlVO;
import registration.QRCodeTest;
/**
 * PatientBO is a class which specifies the business logic for all the transactions. PatientBO class provides a standard
 * interface between Controller and Data Access Objects.
 * 
 * @author AHIS*
 */

public class PatientBO 
{
	
	public EpisodeVO[] newPatRegistration(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p) throws HisDuplicateRecordException
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		boolean isDuplicateReg=false;
		String[] crNoGrpArr = null, crNoArr = null;
		
		String strPatIdErrMsg="";
		String patPrimaryCatGrp="";
		String patUID = "";
		//synchronized (PatientDAO.class)
		//{
		try
		{
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			Map essentialMap = new HashMap();

			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			DirectChargeDetailDAO directChargeDtlDao=new DirectChargeDetailDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RegEssentialDAO regEssentialDAO = new RegEssentialDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
			//create object of patient temp DAO
			PatientTempDetailDAO patTempDAO = new PatientTempDetailDAO(tx);
			BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
			CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);

			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			List allPatientVoList=new ArrayList();

			
			int arrEpisodeVOLength=objArrEpisodeVO_p.length;
			
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[arrEpisodeVOLength];
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			PatientIdVO objPatientIdVO = new PatientIdVO();
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();

			for (int i = 0; i < arrEpisodeVOLength; i++)
			{
				EpisodeReferVO objEpisodeReferVO = new EpisodeReferVO();
				EpisodeRefDtlVO objEpisodeRefDtlVO = new EpisodeRefDtlVO();
				
				objPatientVO_p.setIsValid(Config.IS_VALID_ACTIVE);
				objPatientVO_p.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
				objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				objPatientVO_p.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				objPatientVO_p.setIsBroughtDead("0");
			
				objPatientVO_p.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				objPatientVO_p.getPatAddress().setSeatId(objPatientVO_p.getSeatId());
				AddressVO addressVO=objPatientVO_p.getPatAddress();
				addressVO.setPatIsUrban(objPatientVO_p.getPatIsUrban());
				
				
				///temp code for trapping error	(Has to delete)
				if(objArrEpisodeVO_p==null || arrEpisodeVOLength==0)
						throw new HisApplicationExecutionException("Episode Array is Null");

				
				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
				PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
				
				if (objPatientVO_p.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
				{
					objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);						
				}
				else
				{
					objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				}
				
				dailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
				objArrEpisodeVO_p[i].setPatCrNo(dailyPatVO.getPatCrNo());
				objArrEpisodeVO_p[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
				objArrEpisodeVO_p[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				objArrEpisodeVO_p[i].setIsValid(Config.IS_VALID_ACTIVE);
				objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
				objPatientVO_p.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				
				//HelperMethods.setNullToEmpty(objPatientVO_p.getPatAddress());
				HelperMethods.populate(dailyPatVO, objArrEpisodeVO_p[i]);
				HelperMethods.populate(dailyPatVO, objPatientVO_p);
				dailyPatVO.setEpisodeTypeCode(objArrEpisodeVO_p[i].getEpisodeTypeCode());
				dailyPatVO.setEpisodeVisitType(objArrEpisodeVO_p[i].getEpisodeVisitType());	

				dailyPatVO.setIsReferred(objPatientVO_p.getIsReferred());
				
				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				dailyPatVO.setEpisodeVisitNo("1");
				
				//dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				
				//Generation of Unique Health ID Added by Singaravelan on 09-Oct-2014
				String[] dob={"1","Jan","1900"};
				String tempDob="";
				if(objPatientVO_p.getPatDOB()!=null&&!objPatientVO_p.getPatDOB().equals("")){
					dob=objPatientVO_p.getPatDOB().split("-");
				}
				else{
					tempDob=regEssentialDAO.getBirthYear(objPatientVO_p, objUserVO_p);
					if(tempDob!=null){
						dob=tempDob.split("-");
					}
				}
				//HealthId hid=new HealthId("Ramesh","B/o Kumar","A S Kumar","Namita Kumar","Male",1984,20,260);
				String strPatFatherHusband = "";
				if(objPatientVO_p.getPatGuardianName()!=null && !objPatientVO_p.getPatGuardianName().equals(""))
				{
					strPatFatherHusband = objPatientVO_p.getPatGuardianName();
				}
				else
				{
					strPatFatherHusband = objPatientVO_p.getPatHusbandName();
				}
					
				HealthId hid=new HealthId(objPatientVO_p.getPatFirstName(),objPatientVO_p.getPatLastName(),strPatFatherHusband,objPatientVO_p.getPatMotherName(),objPatientVO_p.getPatGender(),Integer.parseInt(dob[2]),Integer.parseInt(objPatientVO_p.getPatAddStateCode()),Integer.parseInt(objPatientVO_p.getPatAddDistrictCode()), objPatientVO_p.getPatAddMobileNo());
				GenerateID gid=new GenerateID(hid);
				System.out.println("------HealthId----"+gid.generateSecondaryHealthID()+"------------");	
				objPatientVO_p.setPatSecUHID(gid.generateSecondaryHealthID());

				if(objPatientVO_p.getAsNewPatient()!=null && objPatientVO_p.getAsNewPatient()!="1"){//To Bypass the Secondary UHID Check to Save As New Patient Added by Singaravelan on 13-Oct-2014
					if(objPatientVO_p.getIsDuplicate()!=null && objPatientVO_p.getIsDuplicate().equals("1")){
						// Do Nothing
						System.out.println("Duplicate CR No Length is"+allPatientVoList.size());
					}else{
						//searchDuplicateforMultipleCriteria(objRequest_p, regEssentialDAO, objPatientVO_p, objUserVO_p, allPatientVoList, essentialMap);
						
							//Check Duplicacy using Unique Health ID and return CRNO list having the same UHID Added by Singaravelan on 09-Oct-2014
						crNoGrpArr=regEssentialDAO.checkDuplicateRegistrationAllIdsPossible(objPatientVO_p, objUserVO_p);//
						//crNoGrpArr=regEssentialDAO.checkDuplicateRegistrationUHID(objPatientVO_p, objUserVO_p);//checkDuplicateRegistrationAllIdsPossible
						//A1--- Aadhaar
						//B2--- Other Id
						//C3--- Demographics
						//D4--- Mobile No
						if(crNoGrpArr!=null){
						for (String subCrNoGrp : crNoGrpArr) {
							if(subCrNoGrp.length()>=1)
							{
								String[] tempArry = subCrNoGrp.split("\\~\\$\\~");//due to symbols used in regular expression creation we used the escape characters to get ~$~
								String[] tempArry2 = (String[])ArrayUtils.addAll(crNoArr, tempArry);
								crNoArr = tempArry2;
							}
							
						}
							if(crNoArr!=null)
							{
								PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
								//By Mukund on 09.09.2016
								//regEssentialDAO.updateDuplicateUHIDLog(objPatientVO_p, objUserVO_p, crNoArr, "1");
								
								for(i=0;i<crNoArr.length;i++)
								{
									PatientVO vo=new PatientVO();
									vo.setPatCrNo(crNoArr[i].split("&&")[1]);
									vo.setMatchCriteria(crNoArr[i].split("&&")[0]);
									
									switch(crNoArr[i].split("&&")[0]){
									case "A1":
										vo.setMatchCriteriaStr("Aadhaar");
									break;
									case "B2":
										vo.setMatchCriteriaStr("Other Id");
									break;
									case "C3":
										vo.setMatchCriteriaStr("Demographics");
									break;
									case "D4":
										vo.setMatchCriteriaStr("MobileNo");
									break;
									}
									patientVOArr[i]=vo	;
								}
								for(i=0;i<crNoArr.length;i++)
								{
									allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], objUserVO_p));	
								}
								essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
								essentialMap.put("DUP_CR_NO_ARR",crNoArr);
							}
							if(crNoArr!=null)
							{	
								/***/
								/*List<PatientVO> allPatVOLst = allPatientVoList;
								for(PatientVO objPatVo : allPatVOLst)
								{
									System.out.println("objPatVo: "+objPatVo.getPatCrNo());
								}*/
								String dupSearchSlno = regEssentialDAO.updateDuplicateSearchLog(objPatientVO_p, objUserVO_p, crNoArr, "1", "", "");//updateDuplicateSearchLog(PatientVO, UserVO, crNoArr, modeVal, dupSrchSno, actionTaken);//mode 1 need not given dupSrchSno and action taken values
								essentialMap.put("duplicate_search_sno_frm_proc", dupSearchSlno);
								WebUTIL.setAttributeInSession(objRequest_p, "uhid_for_duplicate_search_frm_proc", objPatientVO_p.getPatSecUHID());
								WebUTIL.setMapInSession(essentialMap, objRequest_p,objPatientVO_p.getCallerName());
								throw new HisDuplicateRecordException("Duplicate Registration");
							}
							
						}
					
					}
				}
				/*else if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient().equals("1"))
				{
				  //By Mukund on 09.09.2016
				  String[] crNoArrFrmSession= (String[])objRequest_p.getSession().getAttribute("DUP_CR_NO_ARR");
				  String dupSrchsno   = (String)objRequest_p.getSession().getAttribute("duplicate_search_sno_frm_proc");

				  String dupSearchSlno = regEssentialDAO.updateDuplicateSearchLog(objPatientVO_p, objUserVO_p, crNoArrFrmSession, "2", dupSrchsno, "SaveAsNew");//
				  objRequest_p.getSession().setAttribute("duplicate_search_sno_frm_proc", null);
				  //regEssentialDAO.updateDuplicateUHIDLog(objPatientVO_p, objUserVO_p, crNoArrFrmSession, "1");
				}*/
						
						
				dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
				/* Code Added by Garima for sometimes all issue in BarCode Slip  STart*/
				dailyPatVO.setRoundRobinRoomFlag(objPatientVO_p.getRoundRobinRoomFlag());
				dailyPatVO.setRoundRobinUnitFlag(objPatientVO_p.getRoundRobinUnitFlag());
				dailyPatVO.setRoomCode(objPatientVO_p.getRoomCode());
				/* Code Added by Garima for sometimes all issue in BarCode Slip  End*/
				dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD,RegistrationConfig.UNIT_TYPE_GENERAL, "NROPD");
				//Start:sheeldarshi
				//objPatientVO_p.setUnitConsultant(dailyPatVO.getUnitConsultant());
				//End
				//generating the Cr Number
				String CrNO=patDao.generateCrNumber(objUserVO_p);
				objPatientVO_p.setPatCrNo(CrNO);
				objPatientVO_p.getPatAddress().setPatCrNo(objPatientVO_p.getPatCrNo());
				objPatientVO_p.setSeatId(objUserVO_p.getSeatId());
				objPatientVO_p.setIpAddress(objUserVO_p.getIpAddress());
				
				if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient().equals("1"))
				{
				  //By Mukund on 09.09.2016
				  String[] crNoArrFrmSession= (String[])objRequest_p.getSession().getAttribute("DUP_CR_NO_ARR");
				  String dupSrchsno   = (String)objRequest_p.getSession().getAttribute("duplicate_search_sno_frm_proc");

				  String dupSearchSlno = regEssentialDAO.updateDuplicateSearchLog(objPatientVO_p, objUserVO_p, crNoArrFrmSession, "2", dupSrchsno, "1");//
				  objRequest_p.getSession().setAttribute("duplicate_search_sno_frm_proc", null);
				  //regEssentialDAO.updateDuplicateUHIDLog(objPatientVO_p, objUserVO_p, crNoArrFrmSession, "1");
				}
				
				/**By Mukund to create Aadhaar UUid using crno and session salt*/
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals(""))
				{
					/*
					-- Pre-requisite
					-- Cr No generated and patinet dtl has been saved in HRGT_patient-Dtl table
					-- a UUID has been generated as a Refernce Key and has been inserted in place of Adhaar no in HRGT_Patient_dtl and Id_dtl table
					-- Aadhaar no is not getting inserted in any table.
					-- Calling this method immediately after New Patient Insert, Adhaar no Detail Modi and Patient record delete (not a case right now)
					*/
					patUID = objPatientVO_p.getPatNationalId();
					String strHashedAadhaarUUID = "";
					strHashedAadhaarUUID = objPatientIdDetailDAO.getAadhaarReferenceUUID(CrNO, objPatientVO_p.getPatNationalId(), "1");//hissso.security.SecureHashAlgorithm.SHA1(objPatientVO_p.getPatCrNo()+ strSessionSalt);
					
					System.out.println("\nstrHashedAadhaarUUID: "+strHashedAadhaarUUID);
					//	
					//code for replacing uuid with national id and calling procedure to encrypt and store the aadhaar
					//
					objPatientIdDetailDAO.saveAadhaarDatainADV(CrNO, strHashedAadhaarUUID, objPatientVO_p.getPatNationalId(), objUserVO_p.getHospitalCode(), "1", objHisDAO);
					objPatientVO_p.setPatNationalId(strHashedAadhaarUUID);
					dailyPatVO.setPatNationalId(objPatientVO_p.getPatNationalId());
				}
				/**End on 11.Jan.18*/
				
				/**By Mukund on 13.04.2017 */
				String strExpiryDate ="";
				if(objPatientVO_p.getPatPrimaryCatGrp()!=null && objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
				{
					Date dt1,dNowP14=null; 
					String dNowP14Str="";
					Calendar cldr = Calendar.getInstance();
					cldr.add(Calendar.DATE, 14);
					SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
					dNowP14Str =dateFormat.format(cldr.getTime());
					dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
					dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
					//compare two dates
					int judge = dt1.compareTo(dNowP14);
					if(judge<=0)// dt1 is earlier than dNowP14
					{
						strExpiryDate = dateFormat.format(dt1);
					}
					else if(judge>0)//dt1 is later than dNowP14
					{
						strExpiryDate = dateFormat.format(dNowP14);
					}
					
				}
				else
				{
					strExpiryDate = patDao.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), dailyPatVO.getDepartmentUnitCode());
				}
				/**End on 13.04.2017 */
				
				/*String strExpiryDate = patDao.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), dailyPatVO.getDepartmentUnitCode());*/
				//objPatientVO_p.setExpiryDate(strExpiryDate);
				
				// Setting Expiry Date(General,Special & Casuality) in PatientVo and so DailyPatientVO
				PatientBOHelper.setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, dailyPatVO, objArrEpisodeVO_p[i], strExpiryDate);
				
				
				dailyPatVO.setPatAge(objPatientVO_p.getPatAge());
				dailyPatVO.setPatDOB(objPatientVO_p.getPatDOB());
				dailyPatVO.setPatAgeUnit(objPatientVO_p.getPatAgeUnit());
				dailyPatVO.setIsActualDob(objPatientVO_p.getIsActualDob());
				
				// Generating Bill No
				dailyPatVO.setBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
				
				///get age or DOB
				//objPatientVO_p=patDao.getDOBAndAge(objPatientVO_p);
				
				//seting dialypatientvo details
				dailyPatVO.setPatCrNo(CrNO);
				
				//insert into daily
				dailyPatVO = dailyPatDao.saveDailyPatientDtl(objHisDAO,dailyPatVO, objUserVO_p,"1",RegistrationConfig.DAILYPATIENT_REG_NEW);

				
//				String strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
//				//Setting Expiry(Episode Exiry) to make it available in episode while saving
//				 if(strRenewalType!=null && (strRenewalType.equals("3") || strRenewalType.equals("4")))
//					dailyPatVO.setExpiryDate(strExpiryDate);

				if(dailyPatVO.getPatAgeWithUnit()!=null && dailyPatVO.getPatAgeWithUnit().equalsIgnoreCase("0 Min"))
					objPatientVO_p.setPatAgeWithUnit("0 Day");
				else
					objPatientVO_p.setPatAgeWithUnit(dailyPatVO.getPatAgeWithUnit());
				//objPatientVO_p.setPatAge(dailyPatVO.getPatAge());
				//objPatientVO_p.setPatDOB(dailyPatVO.getPatDOB());
				
				//For the Details Insertion in the Beneficiary Patient Dtl Table 
				if(objPatientVO_p.getPatPrimaryCatGrp()!=null && 
						(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
						objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))){
					
					patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrp();
					//Need to remove this cond once the staff no constraints from the table is removed
					//if(!objPatientVO_p.getStaffCardNo().equals("")||!objPatientVO_p.getAgsNo().equals("")){
					//By mukund on 12.Apr.2017 for client code = 2000 for Arogyashri recommended by Ateria sir
					/*if(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
						objPatientVO_p.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);*/
					if(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
						objPatientVO_p.setClientCode("-1");
					HelperMethods.populate(objBenPatVO, objPatientVO_p);					
					if(!objBenPatVO.getAgsNo().equals(""))
						objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
					else if(!objBenPatVO.getStaffCardNo().equals(""))
						objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
					else if(objBenPatVO.getStaffCardNo().equals("") && !objPatientVO_p.getPatIdNo().equals(""))
						objBenPatVO.setCardNo(objPatientVO_p.getPatIdNo());
					
					if(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
					{
						if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
						objBenPatVO.setIsDependent("1");
						objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
						}else{
						objBenPatVO.setIsDependent("0");
						objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
						}
					objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
					objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
					}
					
					//by mukund on 26.07.2016
					objBenPatVO.setPatActualAmount(objPatientVO_p.getPatActualAmount());
					
					//commented by sheel darshi on 14th June to save patient credit details--start
					//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
					
					benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
					//end
					
					//}
										
				}
				
				//insert into patient
				objPatientVO_p = patDao.savePatientDtl(objHisDAO,objPatientVO_p, objUserVO_p,"1");
				/**
				 * Create the json & 
				 * mark entry into minimal table 
				 **/
				String jsonStr= "{\"crno\":\""+objPatientVO_p.getPatCrNo()+"\", \"uid\":\""+patUID+"\", \"uhid\":\""+objPatientVO_p.getPatSecUHID()+"\", \"mobileNo\":\""+objPatientVO_p.getPatAddMobileNo()+"\", "
						+ "\"patCategoryCode\":\""+objPatientVO_p.getPatPrimaryCatCode()+"\", \"name\":\""+objPatientVO_p.getPatFirstName()+" "+objPatientVO_p.getPatMiddleName()+" "+objPatientVO_p.getPatLastName()+"\", \"nameArray\":[\""+objPatientVO_p.getPatFirstName()+"\",\" "+objPatientVO_p.getPatMiddleName()+"\",\" "+objPatientVO_p.getPatLastName()+"\"], "
						+ "\"gender\":\""+objPatientVO_p.getPatGender().substring(0, 1)+"\", \"dob\":\""+objPatientVO_p.getPatDOB()+"\", \"ageInYears\":\""+objPatientVO_p.getPatAgeWithUnit()+"\", \"yob\":\""+Integer.parseInt(dob[2])+"\", \"fatherName\":\""+objPatientVO_p.getPatGuardianName()+"\", "
						+ "\"motherName\":\""+objPatientVO_p.getPatMotherName()+"\", \"spouseName\":\""+(objPatientVO_p.getPatHusbandName().equals("")?"N/A":objPatientVO_p.getPatHusbandName())+"\", \"house\":\""+objPatientVO_p.getPatAddHNo()+"\", \"loc\":\""+objPatientVO_p.getPatAddStreet()+" "+objPatientVO_p.getPatAddCityLoc()+" "+objPatientVO_p.getPatAddCity()+"\", "
						+ "\"vtc\":\""+objPatientVO_p.getPatAddCity()+"\", \"dist\":\""+objPatientVO_p.getPatAddDistrict()+"\", \"distCode\":\""+objPatientVO_p.getPatAddDistrictCode()+"\", "
						+ "\"state\":\""+objPatientVO_p.getPatAddState()+"\", \"stateCode\":\""+objPatientVO_p.getPatAddStateCode()+"\", \"pc\":\""+objPatientVO_p.getPatAddPIN()+"\"}";
				

				QRCodeTest objQRC = new QRCodeTest();
				objQRC.createQRCode(jsonStr, objRequest_p);
				
				patDao.savePatientMinimalDtl(objHisDAO,objPatientVO_p, jsonStr, objUserVO_p, "1");
				//CreateQREntry(objPatientVO_p, jsonStr, objUserVO_p);
				
				//Added by Garima on 29 March 2019 for PHRMS HealthRecordIntegration
				objPatientVO_p = patDao.savePatDetailForMyHealthRecordIntegration(objHisDAO,objPatientVO_p, objUserVO_p,"1","1");
				/***/
				//insert into mhrgt_mobreg_dtl
				if(objPatientVO_p.getSearchUsingMobile().equals("1"))
				objPatientVO_p = patDao.savePreRegisteredPatDtl(objHisDAO,objPatientVO_p, objUserVO_p,"1");
				
				
				
				if(objPatientVO_p.getStrAreadyRegisteredFlag()!=null && objPatientVO_p.getStrAreadyRegisteredFlag()!="")
				{
					//insert into Portal Registration Detail Table
				 if(objPatientVO_p.getStrAreadyRegisteredFlag().equals("2"))
				 objPatientVO_p = patDao.savePortalRegPatDtl(objHisDAO,objPatientVO_p, objUserVO_p,"2");
				
				 //insert into Patient Old Table
				 if(objPatientVO_p.getStrAreadyRegisteredFlag().equals("1"))
					objPatientVO_p = patDao.savePortalRegPatDtl(objHisDAO,objPatientVO_p, objUserVO_p,"3");

				
				}
				
				//insert into address dteial
				addDao.saveAddressDtl(objHisDAO,addressVO, objUserVO_p,"1");
				
				String deptName = dailyPatVO.getDepartment();
				if(deptName.contains("(")){
					dailyPatVO.setDepartment(deptName.substring(0,deptName.indexOf("(")));
					dailyPatVO.setDepartmentUnit(deptName.substring(deptName.indexOf("(")+1,deptName.length()-1));
				}
				HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
				
				
				// Create new entry in Episode detail
				if (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					objEpisodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					objEpisodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				else 
					objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
				
				objArrEpisodeVO_p[i].setEntryDate(objPatientVO_p.getSystemDate());
				//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(objHisDAO,objArrEpisodeVO_p[i], objUserVO_p, tx);
				
				objArrEpisodeVO_p[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				
				objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_NEW);			
				objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
				objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());

				episodeDAO.saveEpisodeDtl(objHisDAO, objArrEpisodeVO_p[i], objUserVO_p,"1");
			
				/**Call to save episode details in 'hrgt_seccat_change_dtl'*/
				secondaryCategoryChangesForEveryEpisodeCreated( tx, objUserVO_p, objArrEpisodeVO_p[i], objPatientVO_p);
				/***/

					
				if (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					HelperMethods.populate(objEpisodeReferVO, objPatientVO_p);
					HelperMethods.populate(objEpisodeReferVO, objArrEpisodeVO_p[i]);
					HelperMethods.populate(objEpisodeRefDtlVO, objArrEpisodeVO_p[i]);
					
					objEpisodeRefDtlVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
					objEpisodeRefDtlVO.setExternalHospitalCode(objPatientVO_p.getPatRefGnctdHospitalCode());
					objEpisodeRefDtlVO.setExternalHospitalDepartment(objPatientVO_p.getPatRefGnctdHospitalDept());
					objEpisodeRefDtlVO.setExternalHospitalDepartmentUnit(objPatientVO_p.getPatRefGnctdHospitalDeptUnit());
					objEpisodeRefDtlVO.setExternalHospitalDoctorName(objPatientVO_p.getPatRefDoctor());
					objEpisodeRefDtlVO.setExternalHospitalName(objPatientVO_p.getPatRefHospitalName());
					objEpisodeRefDtlVO.setExternalHospitalPatCrNo(objPatientVO_p.getPatRefGnctdHospitalCrno());
					objEpisodeRefDtlVO.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
					objEpisodeRefDtlVO.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
					
					episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO,objEpisodeRefDtlVO, objUserVO_p,"1");

				}
				
				directChargeVO[i]= new DirectChageDetailVO();
				directChargeVO[i].setTariffId(objUserVO_p.getTariffID());
				directChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				directChargeVO[i].setModuleId(objUserVO_p.getModuleId());
				HelperMethods.populate(directChargeVO[i], objArrEpisodeVO_p[i]);
				directChargeVO[i].setPatAmountCollected(objPatientVO_p.getPatAmountCollected());
				directChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
				directChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
				directChargeVO[i].setCreditBillFlag(objPatientVO_p.getCreditBillFlag());
				directChargeVO[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
				directChargeVO[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
				directChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_REGISTRATION);
				directChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
				directChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
				directChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
				directChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
				directChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
				directChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
				directChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
				directChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
				directChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());
				//directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
				directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);
				System.out.println(" get payment code "+objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);
				
				if(!(directChargeVO[i].getPaymentMode().equals("1") || directChargeVO[i].getPaymentMode().equals("10") || directChargeVO[i].getPaymentMode().equals("13")))
					directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
				else
					directChargeVO[i].setPaymentModeRefId("");//for cash
				//All Cases need to put entries in the Direct Charge DTL table
				
				//if (!(directChargeVO[i].getPatAmountCollected().equals("0") || directChargeVO[i].getPatAmountCollected().equals("0.00") || directChargeVO[i].getPatAmountCollected().equals("") || directChargeVO[i]
				//		.getPatAmountCollected() == null)) 
				//{
					///registration module billing
					directChargeDtlDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
					////Billing module billing///
					//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
				//}
					/**Added by Vasu*/
					//Map mp=new HashMap();
					//HospitalMstVO objHospitalMstVO=ControllerUTIL.getHospitalVO(objRequest_p);
					//mp=EmgRegistrationDATA.getRegistrationFormEssentials_AJAX(objUserVO_p,objHospitalMstVO.getState(),objRequest_p);
					//RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
					/**End*/
				//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL 
				if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
					HelperMethods.populate(objCreditAvailDtlVO, directChargeVO[i]);
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
					
					objCreditAvailDtlVO.setTariffActualRate(directChargeVO[i].getPatActualAmount());
					objCreditAvailDtlVO.setPaidByClientAmt(directChargeVO[i].getPatActualAmount());
					objCreditAvailDtlVO.setPaidByPatAmt("0");
					objCreditAvailDtlVO.setNetAmount(directChargeVO[i].getPatActualAmount());
					objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());
					//Need to include approved by here
					if(Double.valueOf(objCreditAvailDtlVO.getNetAmount())>0)
					creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO, objCreditAvailDtlVO, objUserVO_p, "1");
				}	
				
				HelperMethods.populate(objPatientIdVO, objPatientVO_p);
				
				// For Patient Primary Category Id (like Employee Id etc)
				//if(objPatientVO_p.getIsIdRequired()!=null && objPatientVO_p.getIsIdRequired().equals("2")
						//&& 
				if(objPatientVO_p.getPatIdNo()!=null && !objPatientVO_p.getPatIdNo().equals("") ){
					
					strPatIdErrMsg="Patient Id is not defined";
					if(objPatientVO_p.getPatIdNo().equals("undefined"))
						throw new HisApplicationExecutionException(strPatIdErrMsg);
					
					if(objPatientVO_p.getPatPrimaryCatGrp().equals("1"))
					{
						objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
						objPatientIdVO.setPatDocType("99");
						objPatientIdVO.setPatIsAlternateId("0");
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
					}					
					else
					{
						if(objPatientVO_p.getPatCatDocIsAlternateId()!=null && objPatientVO_p.getPatCatDocIsAlternateId().equals("1"))
						{
							objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
							objPatientIdVO.setPatDocType(objPatientVO_p.getPatCatDocCode());
							objPatientIdVO.setPatIsAlternateId(objPatientVO_p.getPatCatDocIsAlternateId());
							objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "10");
						}
					}					
					
					System.out.println("HiddenCatOrRegstrdPopupFlag :"+objPatientVO_p.getHiddenCatOrRegstrdPopupFlag());		
					if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag()!=null && !objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("")){
						objPatientIdVO.setPatIsAlternateId("0");
//						if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("C")){
//							objPatientIdVO.setPatDocType("99");
//							objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
//						}
						
						/*else if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A")){
							//System.out.println("AlreadyRegisteredFlag() :"+objPatientVO_p.getAlreadyRegisteredFlag());
							//System.out.println("PrevCrNo :"+objPatientVO_p.getPrevCrNo());
							objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
							if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1")){
								objPatientIdVO.setPatDocType("97");	// For Previous Registered
								objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "6");
							}else if(objPatientVO_p.getAlreadyRegisteredFlag().equals("2")){
								objPatientIdVO.setPatDocType("96");	// For Portal
								objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "5");
							}
						}*/
					}
					
				}
				/*else if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A"))
				{
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
					if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1"))
					{
						objPatientIdVO.setPatDocType("97");	// For Previous Registered
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "6");
					}
					else if(objPatientVO_p.getAlreadyRegisteredFlag().equals("2"))
					{
						objPatientIdVO.setPatDocType("96");	// For Portal
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "5");
					}
					
				}*/
				
				//Start:Sheeldarshi
			if(objPatientVO_p.getStrEmployeePatCatGroup().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_STAFF))
				PatientDAO.updateCategoryFlag("1",objPatientVO_p,objUserVO_p);
				//End
				objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
				//Save Data in DAO.
				if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A"))
				{
					if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1"))
					{
						PatientTempDetailDAO.updateStatus("2",objPatientVO_p.getPatCrNo(), objPatientIdVO.getPatIdNo(), "2", objUserVO_p.getHospitalCode(), objHisDAO);
					}
					else
					{
						PatientTempDetailDAO.updateStatus("1",objPatientVO_p.getPatCrNo(), objPatientIdVO.getPatIdNo(), "2", objUserVO_p.getHospitalCode(), objHisDAO);
					}
				}
				
				/*else if(objPatientVO_p.getIsIdRequired()!=null && objPatientVO_p.getIsIdRequired().equals("2")){
					objPatientIdVO.setPatDocType("99");
					objPatientIdVO.setPatIsAlternateId("0");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objUserVO_p, "1");
				}*/
				
				// For AlterNate Id
				if(objPatientVO_p.getPatCardNo()!=null && !objPatientVO_p.getPatCardNo().equals("")){
					System.out.println("For ALterNate ID -->> PatCardNo :"+objPatientVO_p.getPatCardNo());
					
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatCardNo());
					objPatientIdVO.setPatIsAlternateId("1");
					objPatientIdVO.setPatDocType(objPatientVO_p.getPatDocType());
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO,objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "1");
				}
				
				// For NationalId/ Aadhar No
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals("")){
					System.out.println("For NationalId/ Aadhar No -->> PatCardNo :"+objPatientVO_p.getPatNationalId());
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatNationalId());
					objPatientIdVO.setPatDocType("98");
					objPatientIdVO.setPatIsAlternateId("0");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "3");
				}
//To Upload the Patient Image in Emergency Registration 
				
				if (objPatientVO_p.getImageFile() != null)
				{
					HelperMethods.populate(patientImageDtlVO, objArrEpisodeVO_p[i]);
					//patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#1");
					//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+"01");
					patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+ objPatientVO_p.getPatCrNo()+"_01");
					//HelperMethods.storeImageInCorrectFileSystem(objPatientVO_p.getImageFile(), objPatientVO_p.getImageFileName(), patientImageDtlVO
					//		.getFileNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					/*if(null!=objRegistrationConfigVO.getIsImageStoredFTP() && !objRegistrationConfigVO.getIsImageStoredFTP().equals(""))
					{
					if(objRegistrationConfigVO.getIsImageStoredFTP().equals("1"))
					{
					System.out.println("Inside FTP");
					//FileInputStream fileInFTPStream = new FileInputStream(this.uploadedFile);
					File file=new File(objPatientVO_p.getImageFileName());
					FileUtils.writeByteArrayToFile(file, objPatientVO_p.getImageFile());
					FileInputStream fileInFTPStream = new FileInputStream(file);
					//FTPFileTransfer.uploadFile(strProcessId, strFileName, fileInFTPStream, patientImageDtlVO.getPatCrNo());
					FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patientImageDtlVO.getFileNo(), fileInFTPStream, patientImageDtlVO.getPatCrNo());
					System.out.println("FTP Saved");
					}
					
					/*commented by Manisha Gangwar date: 23.3.2018 Saving Images to MongoDb */
					/*else
					{
					System.out.println("Inside MONGO");
					//String docId = patientImageDtlVO.getPatCrNo() + "_"  + patientImageDtlVO.getFileNo();
					String docId = patientImageDtlVO.getFileNo();
				    MongoXmlHandler.getInstance().savePDFFile(docId, objPatientVO_p.getImageFile());
					System.out.println("Mongo Saved");
					}*/
					/*end of Mongodb*/
					
					
					//else{
						
					//}
					//}
					//boolean flag =HisFileControlUtil.isWindowsOS();
					boolean flag =true;
					if(flag)
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					}
					else
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					}
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, objUserVO_p);
				
				}
				
				
			/***********************************************/	
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
				
				patientImageDtlDAO.saveImageToPostgres(objHisDAO,patientImageDtlVO,objPatientVO_p.getImageFile(),objUserVO_p);
				
				objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
				objArrEpisodeVO_p[i].setExpiryDate(strExpiryDate);
				//objArrEpisodeVO_p[i].setDisclaimer("Test Disclaimer1@Test Disclaimer2@Test Disclaimer1@0@center");
			}
					
			

		}
catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException("Error, "+strPatIdErrMsg);
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException("Data Access Error, Contact System Administrator");
		}
		catch(HisSQLManualException e){	
			tx.rollback();
			throw new HisSQLManualException("Department-Unit Limit Exhausted");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
			
		}
		//}
		return objArrEpisodeVO_p; // <<<
	}


	public String getDisclamer(String usablityFlag,String deptUnitCode,UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String disclaimer=""; 
		try
		{
			tx.begin();
			RegEssentialDAO objEssentialDao = new RegEssentialDAO(tx);
			
			disclaimer = objEssentialDao.getDisclaimer(deptUnitCode, objUserVO_p,"1");
			if(disclaimer==null)
				//disclaimer = objEssentialDao.getDisclaimer(deptUnitCode.substring(0,3), objUserVO_p,"2");
				disclaimer = objEssentialDao.getDisclaimer((deptUnitCode.length()>1)?deptUnitCode.substring(0,3):"0", objUserVO_p,"2");
			if(disclaimer==null)
				disclaimer = objEssentialDao.getDisclaimer(usablityFlag, objUserVO_p, "3");
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return (disclaimer==null?"":disclaimer);
	}

	/**
	 * Creates the template for printing of OPD Slip
	 */
	public void createSlip(String remoteAddress, List dataList)
	{
		StringBuffer fileContents = new StringBuffer();
		for (int i = 0; i < dataList.size(); i += 9)
		{
			String testStr = "";
			String spaceOne = "";
			String spaceTwo = "";
			String spaceThree = "";
			// String spaceFour = "";

			for (int j = 0; j < (10 + (30 - ((String) dataList.get(i + 0)).length())); j++)
				spaceOne += " ";
			for (int j = 0; j < (10 + (30 - ((String) dataList.get(i + 2)).length())); j++)
				spaceTwo += " ";
			for (int j = 0; j < (10 + (30 - ((String) dataList.get(i + 4)).length())); j++)
				spaceThree += " ";

			StringTokenizer st = new StringTokenizer((String) dataList.get(i + 6), ",");
			int k = 0;
			while (st.hasMoreElements())
			{
				testStr += (String) st.nextElement() + ",";
				k++;
				if (k % 2 == 0)
				{
					testStr += " \n                 ";
				}
			}
			try
			{
				fileContents.append("    CR Number  : " + (String) dataList.get(i + 0) + "		Name       : " + (String) dataList.get(i + 1) + spaceOne
						+ (String) dataList.get(i + 2) + spaceOne + (String) dataList.get(i + 3) + "\n");
				fileContents.append("    Age        : " + (String) dataList.get(i + 4) + "/" + (String) dataList.get(i + 5) + "	Gender  : "
						+ (String) dataList.get(i + 5) + "\n");
				fileContents.append("\n\n\n\n\n");
				System.out.println("hello....  print...");
			}
			catch (Exception e)
			{
				//System.out.println("Exception in createSlip() " + e);
				System.out.println(e.getMessage());
				//e.printStackTrace();
				
				// tx.rollback();
				throw new HisApplicationExecutionException("Error, Contact System Administrator");
			}
		}// END FOR

		Printer print = new Printer();
		print.createTempFile(fileContents.toString(), remoteAddress);
		System.out.println("Print hello....  ");
	}
	
	/**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table. Provides age of the patient according to
	 * the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param objPatientVO_p Provides CR No to be searched.
	 * @param objUserVO_p Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	public PatientVO searchPatientByCrNo(PatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO objPatientDAO = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientImageDtlDAO patImageDtlDao = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patImageDtlVO = new PatientImageDtlVO();
			objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			objPatientVO_p = objPatientDAO.retrieveByCrNo(objPatientVO_p, objUserVO_p);
			String fname = "(Unknown)" + objPatientVO_p.getPatFirstName();
			
			if(objPatientVO_p.getClientCode()!=null && objPatientVO_p.getClientCode().split("#").length>0){
				String temp[] =objPatientVO_p.getClientCode().split("#");
				objPatientVO_p.setClientCode(temp[0].equals("-")?"":temp[0]);
				objPatientVO_p.setClientName(temp[1].equals("-")?"":temp[1]);
			}
			if(objPatientVO_p.getPatStaffDetails()!=null && objPatientVO_p.getPatStaffDetails().replace("^", "#").split("#").length>0){
				String temp[] =objPatientVO_p.getPatStaffDetails().replace("^", "#").split("#");
				objPatientVO_p.setPatStaffNo(temp[0]==null?"":temp[0]);
				objPatientVO_p.setPatStaffName(temp[1]==null?"":temp[1]);
				objPatientVO_p.setPatStaffDeptName(temp[2]==null?"":temp[2]);
				objPatientVO_p.setPatStaffRelationName(temp[3]==null?"":temp[3]);
			}
			
			if (objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) objPatientVO_p.setPatFirstName(fname);
			if (objPatientVO_p.getPatAge() != null)
			{
				String patAgeWithUnit = objPatientVO_p.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				objPatientVO_p.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) objPatientVO_p.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) objPatientVO_p.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) objPatientVO_p.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) objPatientVO_p.setPatAgeUnit("Wk");
				if (ageunit.equalsIgnoreCase("min")) objPatientVO_p.setPatAgeUnit("D");//by Mukund for dob conversion to Days from minutes
			}
			//To fetch the Uploaded Image
			try
			{
			if (objPatientVO_p.getIsImageUploaded() != null && objPatientVO_p.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				patImageDtlVO=patImageDtlDao.getPatientDefaultImageByCrNo(objPatientVO_p.getPatCrNo(),objUserVO_p);
				//added by manisha gangwar date:28.3.2018
				 byte[] getImageDoc= patImageDtlDao.latestFetchImagePostgres(patImageDtlVO.getFileNo());
				 objPatientVO_p.setImageFile(getImageDoc);
				 
				
				 
				//boolean flag =HisFileControlUtil.isWindowsOS();
				boolean flag =true;
				if(!flag)
				{
					patImageDtlVO.setFilePath("/root"+patImageDtlVO.getFilePath()); 
				}

				//objPatientVO_p.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
				//objPatientVO_p.setImageFileName(patImageDtlVO.getFilePath() + patImageDtlVO.getFileNo());
				objPatientVO_p.setImageFileName(patImageDtlVO.getFileNo());
			}
			
			
			/*if (objPatientVO_p.getPatDocType() != null)
			{
				objPatientVO_p=patImageDtlDao.fetchVerificationDoc(objPatientVO_p,objUserVO_p);
				//added by manisha gangwar date:28.3.2018
				 byte[] getDoc= patImageDtlDao.latestFetchDocPostgres(objPatientVO_p.getVerificationDocumentId());
				 objPatientVO_p.setVerificationDocumentFile(getDoc);
				
					
				 
				
				 
				//boolean flag =HisFileControlUtil.isWindowsOS();
				boolean flag =true;
				if(!flag)
				{
					patImageDtlVO.setFilePath("/root"+patImageDtlVO.getFilePath()); 
				}

				//objPatientVO_p.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
				//objPatientVO_p.setImageFileName(patImageDtlVO.getFilePath() + patImageDtlVO.getFileNo());
				objPatientVO_p.setImageFileName(patImageDtlVO.getFileNo());
			}
			*/
			
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
			//to decrypt the aadhaar number
			if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals(""))
			{
				if(objPatientVO_p.getPatNationalId().length()>12)
				{
					String tempAadhaar = getAadhaarDecrypted(objPatientVO_p.getPatNationalId(), objPatientVO_p.getPatCrNo());
					objPatientVO_p.setPatNationalId(tempAadhaar.split("#@#")[0]);
				}
			}

			//if (objPatientVO_p.getPatCatCode() == null || objPatientVO_p.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
			if (objPatientVO_p.getOtherHospitalFlag()!=null && !objPatientVO_p.getOtherHospitalFlag().equals("1") && (objPatientVO_p.getPatCatCode() == null || objPatientVO_p.getPatCatCode().equals(""))) throw new HisRecordNotFoundException(
					"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return objPatientVO_p;
	}
	
	/**
	 * Retrieves patient details on the basis of CR No and visit type from the Patient Dtl Table. Provides age of the patient
	 * according to the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param objPatientVO_p Provides CR No to be searched.
	 * @param objUserVO_p Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	public PatientVO searchPatientByCrNo(PatientVO objPatientVO_p, UserVO objUserVO_p, String _visitType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			objPatientVO_p = patientDao.retrieveByCrNoDailyPatient(objPatientVO_p, objUserVO_p, _visitType);
			String fname = "(Unknown)" + objPatientVO_p.getPatFirstName();

			if (objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) objPatientVO_p.setPatFirstName(fname);
			if (objPatientVO_p.getPatAge() != null)
			{
				String patAgeWithUnit = objPatientVO_p.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				objPatientVO_p.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) objPatientVO_p.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) objPatientVO_p.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) objPatientVO_p.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) objPatientVO_p.setPatAgeUnit("Wk");
			}
		
			if (objPatientVO_p.getPatCatCode() == null || objPatientVO_p.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return objPatientVO_p;
	}
	

	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 * @param	objPatientVO_p	Provides Patient details.
	 * @param	objUserVO_p		Provides User details.
	 * @param	visitType	Specifies the visit type, whether its OPD, IPD or Emergency. 
	 * @return	<code>true</code> if patient status is according to the visit type otherwise <code>false</code>.
	 */
	public static boolean checkPatientStatus(PatientVO objPatientVO_p, UserVO objUserVO_p, String strVisitType_p){
		System.out.println("inside checkPatientStatus() of PatientBOSupport");
		boolean value=false;
		try{
			if(objPatientVO_p.getPatStatusCode() != null){
				
				if("1".equals(objPatientVO_p.getPatIsDead())){
					System.out.println("Patient is Dead");
					throw new HisDeadPatientException("Patient is Dead");
				}
				else
					value=true;
		}			
		}
		catch(Exception e){
			System.out.println("inside  checkPatientStatus:::PatientBOSupport");
			if(e.getClass()==HisNotAnOPDcaseException.class)
				throw new HisNotAnOPDcaseException();
			else if(e.getClass()==HisNotAnIPDcaseException.class)
				throw new HisNotAnIPDcaseException();	
			else if(e.getClass()==HisDeadPatientException.class)
				throw new HisDeadPatientException();	 
			else
				throw new HisApplicationExecutionException("PatientBOSupport:::::checkPatientStatus "+e);
		}
		return value;
	}
	

	/**
	 * This Method return the MlcVO for the patient against the cr no. Used for patient detail tile to retrieve the mlc no.
	 * Before retrieving it checks for patients visit type in Episode dtl table for the last episode(open/close). if it
	 * returns a record,the method proceeds to check weather the mlc details for the patients have been recorded or not.
	 * However if patient visit type is found to be MLC and records haven,t been entered into MLC Table(This will be shown as
	 * ::MLC NO yet to be alloted)(set in controller-UTIL)
	 * 
	 * @see
	 */
	public MlcVO isPatientMlc(PatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		MlcVO mlcVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		EpisodeDAO episodeDao = new EpisodeDAO(tx);
		try
		{
			String mlcNumber = episodeDao.isPatientMLC(objPatientVO_p, objUserVO_p);
			if (mlcNumber != null || !(mlcNumber.equals("")))
			{
				mlcVO = new MlcVO();
				mlcVO.setPatCrNo(objPatientVO_p.getPatCrNo());
				mlcVO.setMlcNo(mlcNumber);
			}
		}
		finally
		{
			tx.close();
		}

		return mlcVO;
	}// end of isPatientMlc
	
	/**
	 * Retrieves all the addresses of a patient from Address Dtl Table.
	 * 
	 * @param objPatientVO_p Provides CR No of the patient for which episode details are to be searched.
	 * @param objUserVO_p Provides User details.
	 * @return Array of AddressVO with all the addresses of the patient.
	 */
	public AddressVO[] getPatAddressAll(PatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		//System.out.println("inside getPatAddressAll() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AddressVO[] _arrAddressVO = {};
		try
		{
			tx.begin();
			AddressDAO addDao = new AddressDAO(tx);
			String crNo = objPatientVO_p.getPatCrNo();
			_arrAddressVO = addDao.retrieveByCrNoAll(crNo, objUserVO_p);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrAddressVO;
	}
	
	
	
	/**
	 * Retrieves all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 * 
	 * @param objUserVO_p Provides User details.
	 * @return Array of PatientVO populated with CR No.
	 */
	public PatientVO[] getCrNoForModification(UserVO objUserVO_p, String episodeVisitType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] _arrPatientVO =
		{};

		try
		{
			tx.begin();
			PatientDAO objPatientDAO = new PatientDAO(tx);

			_arrPatientVO = objPatientDAO.retrieveCrNoForModification(objUserVO_p, episodeVisitType);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			//System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		//System.out.println("hi!!!!!!!!!!!!");
		return _arrPatientVO;
	}
	/**
	 * Modifies patient+address details. Also more addresses can be added. Updates the record in Patient & Address dtl
	 * table.
	 * 
	 * @param _arrAddressVO[] Provides addresses to be added or modified.
	 * @param objPatientVO_p Provides Patient details.
	 * @param objUserVO_p Provides User details.
	 * @return Number of records updated. 
	 * 
	 * Created For CDAC Noida
	 */
	public int patientDetailModification(PatientVO objPatientVO_p, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			PatientModificationVO objPatientModificationVO, UserVO objUserVO_p,PatientIdVO objPatientIdVO_p,HttpServletRequest objRequest_p )
	{
		int x = 0;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
		HisDAO objHisDAO = null;
		try
		{
			
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			
			PatientAuditDAO patAuditDao=new PatientAuditDAO(tx);
			PatientDAO objPatientDAO = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);

			PatientIdDetailDAO objPatientIdDetailDAO_p = new PatientIdDetailDAO(tx);
			
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
		//	PatientModificationVO objPatientModificationVO = new PatientModificationVO();
			PatientModificationDAO objPatientModificationDAO = new PatientModificationDAO(tx);
			RegEssentialDAO regEssentialDAO = new RegEssentialDAO(tx);
			List allPatientVoList=new ArrayList();
			Map essentialMap = new HashMap();
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
		//	PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			//VerificationDocumentUsedDAO verificationDocUsedDao = new VerificationDocumentUsedDAO(tx);

			//PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			String[] crNoArr = null, crNoGrpArr=null;
			
			synchronized (objHisDAO)
	        {
				// Verifcation Doc Sl No.
			//	String verficationSNo = verificationDocUsedDao.selectNextSerialNoForPUK(_verDoc, objUserVO_p);

				
				//objPatientVO_p.setVerificationDocumentId(verficationSNo);
				
				// Update Patient Detail
				/*Modify Date			: 5thDec'14
				  Reason	(CR/PRS)	: Secondary UHID check incorporation
				  Modify By				: Sheeldarshi */
				
				//Generation of Unique Health ID Added by Singaravelan on 09-Oct-2014
				String[] dob={"1","Jan","1900"};
				String tempDob="";
							
				if(objPatientVO_p.getIsActualDob().equals("1") && objPatientVO_p.getPatDOB()!=null&&!objPatientVO_p.getPatDOB().equals("") ){
					dob=objPatientVO_p.getPatDOB().split("-");
				}
				else{
					tempDob=regEssentialDAO.getBirthYear(objPatientVO_p, objUserVO_p);
					if(tempDob!=null){
						objPatientVO_p.setPatDOB(tempDob); 
						dob=tempDob.split("-");
					}
				}
				String strPatFatherHusband = "";
				if(objPatientVO_p.getPatGuardianName()!=null && !objPatientVO_p.getPatGuardianName().equals(""))
				{
					strPatFatherHusband = objPatientVO_p.getPatGuardianName();
				}
				else
				{
					strPatFatherHusband = objPatientVO_p.getPatHusbandName();
				}
				
				if(!objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)){
					//HealthId hid=new HealthId("Ramesh","B/o Kumar","A S Kumar","Namita Kumar","Male",1984,20,260);
					HealthId hid=new HealthId(objPatientVO_p.getPatFirstName(),objPatientVO_p.getPatLastName(),strPatFatherHusband,objPatientVO_p.getPatMotherName(),objPatientVO_p.getPatGender(),Integer.parseInt(dob[2]),Integer.parseInt(objPatientVO_p.getPatAddStateCode()),Integer.parseInt(objPatientVO_p.getPatAddDistrictCode()),objPatientVO_p.getPatAddMobileNo());
					GenerateID gid=new GenerateID(hid);
					System.out.println("------HealthId----"+gid.generateSecondaryHealthID()+"------------");	
					objPatientVO_p.setPatSecUHID(gid.generateSecondaryHealthID());
				}
				if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient()!="1"){//To Bypass the Secondary UHID Check to Save As New Patient Added by Singaravelan on 13-Oct-2014
				
				if(objPatientVO_p.getIsDuplicate()!=null && objPatientVO_p.getIsDuplicate().equals("1")){
					// Do Nothing
				}else{
						//crNoArr=regEssentialDAO.checkDuplicateRegistration(objPatientVO_p, objUserVO_p);
						
						//Check Duplicacy using Unique Health ID and return CRNO list having the same UHID Added by Singaravelan on 09-Oct-2014
					crNoGrpArr=regEssentialDAO.checkDuplicateRegistrationAllIdsPossible(objPatientVO_p, objUserVO_p);//
					//crNoArr=regEssentialDAO.checkDuplicateRegistrationUHID(objPatientVO_p, objUserVO_p);
					//A1--- Aadhaar
					//B2--- Other Id
					//C3--- Demographics
					//D4--- Mobile No
					if(crNoGrpArr!=null){
					for (String subCrNoGrp : crNoGrpArr) {
						if(subCrNoGrp.length()>=1)
						{
							String[] tempArry = subCrNoGrp.split("\\~\\$\\~");//due to symbols used in regular expression creation we used the escape characters to get ~$~
							String[] tempArry2 = (String[])ArrayUtils.addAll(crNoArr, tempArry);
							crNoArr = tempArry2;
						}
						
					}
					
					if(crNoArr!=null)
					{
						PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
						//By Mukund on 09.09.2016
						//regEssentialDAO.updateDuplicateUHIDLog(objPatientVO_p, objUserVO_p, crNoArr, "1");
						
						for(int i=0;i<crNoArr.length;i++)
						{
							PatientVO vo=new PatientVO();
							vo.setPatCrNo(crNoArr[i].split("&&")[1]);
							vo.setMatchCriteria(crNoArr[i].split("&&")[0]);
							
							switch(crNoArr[i].split("&&")[0]){
							case "A1":
								vo.setMatchCriteriaStr("Aadhaar");
							break;
							case "B2":
								vo.setMatchCriteriaStr("Other Id");
							break;
							case "C3":
								vo.setMatchCriteriaStr("Demographics");
							break;
							case "D4":
								vo.setMatchCriteriaStr("MobileNo");
							break;
							}
							patientVOArr[i]=vo	;
						}
						for(int i=0;i<crNoArr.length;i++)
						{
							allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], objUserVO_p));	
						}
						essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
						essentialMap.put("DUP_CR_NO_ARR",crNoArr);
					}
					if(crNoArr!=null && crNoArr.length >0)
					{	
						if((crNoArr.length==1 && !crNoArr[0].equals(objPatientVO_p.getPatCrNo()))|| crNoArr.length>1)
						{
							//WebUTIL.setMapInSession(essentialMap, objRequest_p,"PatientDetailModificationAction");
							//throw new HisDuplicateRecordException("Duplicate Registration");
						}
					}
				}
				}
				}
				//End:Sheeldarshi
				
				/**By Mukund to create Aadhaar UUid using crno and session salt*/
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals(""))
				{
					/*
					-- Pre-requisite
					-- Cr No generated and patinet dtl has been saved in HRGT_patient-Dtl table
					-- a UUID has been generated as a Refernce Key and has been inserted in place of Adhaar no in HRGT_Patient_dtl and Id_dtl table
					-- Aadhaar no is not getting inserted in any table.
					-- Calling this method immediately after New Patient Insert, Adhaar no Detail Modi and Patient record delete (not a case right now)
					*/
					String patUID = objPatientVO_p.getPatNationalId();
					String strHashedAadhaarUUID = "";
					strHashedAadhaarUUID = objPatientIdDetailDAO_p.getAadhaarReferenceUUID(objPatientVO_p.getPatCrNo(), objPatientVO_p.getPatNationalId(), "1");//hissso.security.SecureHashAlgorithm.SHA1(objPatientVO_p.getPatCrNo()+ strSessionSalt);
					
					System.out.println("\nstrHashedAadhaarUUID: "+strHashedAadhaarUUID);
					//	
					//code for replacing uuid with national id and calling procedure to encrypt and store the aadhaar
					//
					objPatientIdDetailDAO_p.saveAadhaarDatainADV(objPatientVO_p.getPatCrNo(), strHashedAadhaarUUID, objPatientVO_p.getPatNationalId(), objUserVO_p.getHospitalCode(), "2", objHisDAO);
					objPatientVO_p.setPatNationalId(strHashedAadhaarUUID);
					dailyPatVO.setPatNationalId(objPatientVO_p.getPatNationalId());
				}
				/**End on 11.Jan.18*/
				
				
				if(objPatientVO_p.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit())&& objPatientVO_p.getAsNewPatient()!=null&& objPatientVO_p.getAsNewPatient()!="1") 
					{
						objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else
					{
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
					}
				}
				else
				{
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else  objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
				}
			
				// Update Address Detail
				if (_arrAddressVO!=null) //x >= 1)
				{
					if (_arrAddressVO.getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT)) 
						_arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_TRUE);
					else _arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_FALSE);

					if (addModify.equals(RegistrationConfig.IS_ADDRESS_ADDED))
					{
					//	_arrAddressVO.setVerificationDocumentId(verficationSNo);
						_arrAddressVO.setIsValid(Config.IS_VALID_ACTIVE);
						//addDao.createNewAddress(objHisDAO, _arrAddressVO, objUserVO_p);
						
						addDao.saveAddressDtl(objHisDAO, _arrAddressVO, objUserVO_p,"1");
					}
					if (addModify.equals(RegistrationConfig.IS_ADDRESS_MODIFIED))
					{
					//	_arrAddressVO.setVerificationDocumentId(verficationSNo);
						//_arrAddressVO.setIsValid(Config.IS_VALID_DELETED);
						//addDao.updatePreviousRow(objHisDAO,_arrAddressVO, objUserVO_p);
						addDao.saveAddressDtl(objHisDAO,_arrAddressVO, objUserVO_p,"2");
					//_arrAddressVO.setIsValid(Config.IS_VALID_ACTIVE);
						//addDao.insertNewRowAddress(objHisDAO,_arrAddressVO, objUserVO_p);
					}
				}
				//inserting modifiction dtl
				objPatientModificationDAO.saveModifictaionDtl(objHisDAO, objPatientModificationVO, objUserVO_p, "1");
				///uPDATING DAILY PATIENT VO
				HelperMethods.populate(dailyPatVO, objPatientVO_p);
				dailyPatVO = dailyPatDao.saveDailyPatientDtl(objHisDAO,dailyPatVO, objUserVO_p,"2","");
				
				//Updating patient id detail..
				
				objPatientIdDetailDAO_p.savePatientIdDtl(objHisDAO, objPatientIdVO_p, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "2");
				
				// For NationalId/ Aadhar No
				//if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals("")){
					System.out.println("For NationalId/ Aadhar No -->> PatCardNo :"+objPatientVO_p.getPatNationalId());
					objPatientIdVO_p.setPatIdNo(objPatientVO_p.getPatNationalId());
					objPatientIdVO_p.setPatDocType("98");
					objPatientIdVO_p.setPatIsAlternateId("0");
					objPatientIdDetailDAO_p.savePatientIdDtl(objHisDAO, objPatientIdVO_p, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "7");
				//}
				
				// Audit Log Of Patient Detail
				/*
				 * 
				 * commented for now
				 * 
				 * 
				 */
					//By Mukund for PatDtlMod audit log register date
				if(dailyPatVO.getEntryDate()==null || dailyPatVO.getEntryDate().equals("")){
					dailyPatVO.setRegisterDate(_patientVOOldData.getEntryDate());
				}else{
					dailyPatVO.setRegisterDate(dailyPatVO.getEntryDate());
				}
					/*Start : Surabhi
					 * Date : 9th Nov 2016
					 * Reason : Saving the audit details in audit log*/
					patAuditDao.findRow(objPatientVO_p,objUserVO_p);
					
					if(objPatientVO_p.getFindRow().equals("1"))
					{
						patAuditDao.create(objHisDAO,dailyPatVO,objPatientModificationVO, _patientVOOldData, objUserVO_p,"2");
					}
					else
					{
						patAuditDao.create(objHisDAO,dailyPatVO,objPatientModificationVO, _patientVOOldData, objUserVO_p,"1");
					}

				// Patient Image File
				/*if (_patientVOOldData.getImageFile() != null && objPatientVO_p.getImageFile() != null)
				{
					String sno = patientImageDtlDAO.selctMaxSerialNo(objPatientVO_p.getPatCrNo(), objUserVO_p);
					HelperMethods.populate(patientImageDtlVO, objPatientVO_p);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
					HelperMethods.storeImageInCorrectFileSystem(objPatientVO_p.getImageFile(), objPatientVO_p.getImageFileName(), patientImageDtlVO.getFileNo(),
							Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					if(flag)
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					}
					else
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					}
					// patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					// update the is default flag of old image
					patientImageDtlVO.setSerialNo(_patientVOOldData.getImageFileName().split("#")[1]);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_FALSE);
					patientImageDtlDAO.modifyPatientImage(objHisDAO, patientImageDtlVO, objUserVO_p);
					// insert new default image
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(objHisDAO, patientImageDtlVO, objUserVO_p);
				}
				else if (objPatientVO_p.getImageFile() != null)
				{
					String sno = patientImageDtlDAO.selctMaxSerialNo(objPatientVO_p.getPatCrNo(), objUserVO_p);
					HelperMethods.populate(patientImageDtlVO, objPatientVO_p);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
					HelperMethods.storeImageInCorrectFileSystem(objPatientVO_p.getImageFile(), objPatientVO_p.getImageFileName(), patientImageDtlVO.getFileNo(),
							Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					  if(flag)
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					  }
					  else
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					  }
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(objHisDAO, patientImageDtlVO, objUserVO_p);
				}
				*/
					// Update Verification Document
					//_verDoc.setIsValid(Config.IS_VALID_ACTIVE);
					//verificationDocUsedDao.create(objHisDAO, _verDoc, objUserVO_p);
					
						/*  ## 		Modification Log							
				 		##		Modify Date				:29thJan'15 
				 		##		Reason	(CR/PRS)		:Image upload integration in patient detail module
				 		##		Modify By				:Sheeldarshi */					
						//To Upload the Patient Image in Emergency Registration 
						if (objPatientVO_p.getImageFile() != null)
						{
							String docName=objPatientModificationVO.getStrDocumentCode().split("#")[0].replace("|", "#").split("#")[1];
							String docCode=objPatientModificationVO.getStrDocumentCode().split("#")[0].replace("|", "#").split("#")[0];
							String docCount= regEssentialDAO.getVerificationDocCount(objPatientVO_p.getPatCrNo(),docName , objUserVO_p);
							int count=Integer.parseInt(docCount)+1;
							 HelperMethods.populate(patientImageDtlVO, objPatientVO_p);
							//patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#1");
							Map mp=new HashMap();
							HospitalMstVO objHospitalMstVO=ControllerUTIL.getHospitalVO(objRequest_p);
							mp=NewRegistrationDATA.getRegistrationFormEssentials_AJAX(objUserVO_p,objHospitalMstVO.getState(),objRequest_p);
							RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
							patientImageDtlVO.setFileType("4");
							if(objRegistrationConfigVO.getIsImageStoredFTP().equals("1"))
							{
							
								//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+"01");
								//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+count+"_01");
								patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+docCode+"_"+count);
								
							
							//HelperMethods.storeImageInCorrectFileSystem(objPatientVO_p.getImageFile(), objPatientVO_p.getImageFileName(), patientImageDtlVO
							//		.getFileNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
							System.out.println("Inside FTP");
							//FileInputStream fileInFTPStream = new FileInputStream(this.uploadedFile);
							File file=new File(objPatientVO_p.getImageFileName());
							FileUtils.writeByteArrayToFile(file, objPatientVO_p.getImageFile());
							FileInputStream fileInFTPStream = new FileInputStream(file);
							//FTPFileTransfer.uploadFile(strProcessId, strFileName, fileInFTPStream, patientImageDtlVO.getPatCrNo());
							FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS, patientImageDtlVO.getFileNo(), fileInFTPStream, patientImageDtlVO.getPatCrNo());
							System.out.println("FTP Saved");
							patientImageDtlDAO.uploadVerificationDoc(objHisDAO,patientImageDtlVO, objUserVO_p,docName,patientImageDtlVO.getFileNo());
							//boolean flag =HisFileControlUtil.isWindowsOS();
							}
							else
							{
								//String docCount= regEssentialDAO.getVerificationDocCount(objPatientVO_p.getPatCrNo(),docName , objUserVO_p);
								//int count=Integer.parseInt(docCount)+1;
								//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+count+"_01");
								patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+docCode+"_"+count);
								String docId=objPatientVO_p.getPatCrNo()+"_"+count;
								String fileNo = patientImageDtlVO.getFileNo();
							
							   // commented by manisha gangwar ... code for saving document to mongo
							   // MongoXmlHandler.getInstance().savePDFFile(fileNo, objPatientVO_p.getImageFile());
							   
							    patientImageDtlDAO.uploadVerificationDoc(objHisDAO,patientImageDtlVO, objUserVO_p,docName,docId);
							   
							    // added by manisha gangwar  for saving documents to postgres
								patientImageDtlDAO.saveDocToPostgres(objHisDAO,patientImageDtlVO, objPatientVO_p.getImageFile());
								
							    
							    
							}
							boolean flag =true;
							if(flag)
							{
								patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
							}
							else
							{
								patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
							}
							//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
							patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
							patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
							//patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, objUserVO_p);
						}
						//End:Sheeldarshi
				objHisDAO.fire();
			}
		}
		/*Modify Date			: 5thDec'14
		  Reason	(CR/PRS)	: Secondary UHID check incorporation
		  Modify By				: Sheeldarshi */
		catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}
		//End:Sheeldarshi
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return x;
	}
	/**
	 * Modifies patient+address details. Also more addresses can be added. Updates the record in Patient & Address dtl
	 * table.
	 * 
	 * @param _arrAddressVO[] Provides addresses to be added or modified.
	 * @param objPatientVO_p Provides Patient details.
	 * @param objUserVO_p Provides User details.
	 * @return Number of records updated. 
	 * 
	 * Cretaed By Pragya at 08-Aug-2011
	 */
	public int patientDetailModificationDsk(PatientVO objPatientVO_p, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			 UserVO objUserVO_p,PatientIdVO objPatientIdVO_p )
	{
		int x = 0;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		try
		{
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			
			//PatientAuditDAO patAuditDao=new PatientAuditDAO(tx);
			PatientDAO objPatientDAO = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientAuditDAO patAuditDao=new PatientAuditDAO(tx);

			PatientIdDetailDAO objPatientIdDetailDAO_p = new PatientIdDetailDAO(tx);
			
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			PatientModificationVO objPatientModificationVO = new PatientModificationVO();
			PatientModificationDAO objPatientModificationDAO = new PatientModificationDAO(tx);
			_arrAddressVO.setPatAddTypeCode("1");
		//	PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			//VerificationDocumentUsedDAO verificationDocUsedDao = new VerificationDocumentUsedDAO(tx);

			//PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
		
			
			synchronized (objHisDAO)
	        {
			// Update Patient Detail
				if(objPatientVO_p.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) )
					{
						objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else
					{
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
					}
				}
				else
				{
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else  objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
				}
			
				// Update Address Detail
				if (_arrAddressVO!=null) //x >= 1)
				{
					if (_arrAddressVO.getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT)) 
						_arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_TRUE);
					else _arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_FALSE);

				
					//	_arrAddressVO.setVerificationDocumentId(verficationSNo);
						//_arrAddressVO.setIsValid(Config.IS_VALID_DELETED);
						//addDao.updatePreviousRow(objHisDAO,_arrAddressVO, objUserVO_p);
						addDao.saveAddressDtl(objHisDAO,_arrAddressVO, objUserVO_p,"2");
					//_arrAddressVO.setIsValid(Config.IS_VALID_ACTIVE);
						//addDao.insertNewRowAddress(objHisDAO,_arrAddressVO, objUserVO_p);
					
				}
				///uPDATING DAILY PATIENT VO
				HelperMethods.populate(dailyPatVO, objPatientVO_p);
				dailyPatVO = dailyPatDao.saveDailyPatientDtl(objHisDAO,dailyPatVO, objUserVO_p,"2","");
				
				//Updating patient id detail..
				
				// For NationalId/ Aadhar No
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals("")){
					System.out.println("For NationalId/ Aadhar No -->> PatCardNo :"+objPatientVO_p.getPatNationalId());
					objPatientIdVO_p.setPatIdNo(objPatientVO_p.getPatNationalId());
					objPatientIdVO_p.setPatDocType("98");
					objPatientIdVO_p.setPatIsAlternateId("0");
					objPatientIdDetailDAO_p.savePatientIdDtl(objHisDAO, objPatientIdVO_p, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "7");
				}
				//By Mukund for PatDtlMod audit log register date
				//if(dailyPatVO.getSeatId()==null || dailyPatVO.getSeatId().equals(""))
					dailyPatVO.setSeatId(objUserVO_p.getSeatId());
				
				if(dailyPatVO.getEntryDate()==null || dailyPatVO.getEntryDate().equals("")){
					dailyPatVO.setRegisterDate(_patientVOOldData.getEntryDate());
				}else{
					dailyPatVO.setRegisterDate(dailyPatVO.getEntryDate());
				}
				
				patAuditDao.findRow(objPatientVO_p,objUserVO_p);
				
				if(objPatientVO_p.getFindRow().equals("1"))
				{
					patAuditDao.create(objHisDAO,dailyPatVO,objPatientModificationVO, _patientVOOldData, objUserVO_p,"2");
				}
				else
				{
					patAuditDao.create(objHisDAO,dailyPatVO,objPatientModificationVO, _patientVOOldData, objUserVO_p,"1");
				}
				
				objHisDAO.fire();
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return x;
	}
	
	

	public DailyPatientVO searchDailyPatientByCrNo(DailyPatientVO dailyPatientVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			dailyPatientVO = dailyPatientDAO.searchPatientByCrNo(dailyPatientVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisNotAnOPDcaseException e)
		{
			System.out.println("inside HisNotAnOPDcaseException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			System.out.println("inside HisNotAnIPDcaseException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			System.out.println("inside HisDeadPatientException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return dailyPatientVO;
	}

	public Map getAllEpisodesForDuplicateReprint(String crNo, UserVO userVO, String _choice,String _isReprint)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeDupVO;
		EpisodeVO[] _arrEpisodeReprintVO;
		EpisodeVO[] _arrEpisodePrintCardVO;
		String reprintMode = "";

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			if (_choice.equals(RegistrationConfig.DUPLICATE_CARD))
			{
				_arrEpisodeDupVO = episodeDao.DuplicateRetrieveByCrNo(crNo, userVO,"1");
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE, _arrEpisodeDupVO);
			}
			if (_choice.equals(RegistrationConfig.REPRINT_REGISTRATION))
			{
				reprintMode = RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT;
				_arrEpisodeReprintVO = episodeDao.DuplicateRetrieveByCrNo(crNo, userVO, "2");
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION, _arrEpisodeReprintVO);
			}
			if (_choice.equals(RegistrationConfig.PRINT_CARD))
			{				
				_arrEpisodePrintCardVO = episodeDao.DuplicateRetrieveByCrNo(crNo, userVO, "1");
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_PRINT_CARD, _arrEpisodePrintCardVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	//Start:Sheeldarshi:Registration Cancellation Changes
	public Map getAllEpisodesForCancellation(String crNo, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO;
		try
		{
			tx.begin();
			RegistrationCancellationDAO registrationCancellationDao = new RegistrationCancellationDAO(tx);
			
				/*_arrEpisodeDupVO = episodeDao.DuplicateRetrieveByCrNo(crNo, userVO,"12");
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_REG_CANCELLATION, _arrEpisodeDupVO);*/
				
				_arrEpisodeVO = registrationCancellationDao.getAllEpisodesForCancellation(crNo, userVO,"12");
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_REG_CANCELLATION, _arrEpisodeVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	//End:Sheeldarshi: Registration Cancellation Changes
	

	/**
	 * Facilitates issue of duplicate card for an OPD to a patient. Also enters the details of duplicate card in the DB.
	 * 
	 * @param _episodeVO Provides all the episodes which are opened for a patient.
	 * @param _duplicateRenewVO Provides duplicate card details.
	 * @param objUserVO_p Provides User details.
	 * @return DuplicateRenewVO with values stored in DB.
	 */
	public EpisodeVO duplicateCardPrinting(DirectChageDetailVO directChargeVO, RegCardPrintVO _regCardPrintVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String disclaimer=new String();
		String workingDays=new String();
		EpisodeVO episodeVO=new EpisodeVO();
		HisDAO daoObj= new HisDAO("Registration","PatientBO");
		RegCardPrintDtlDAO regCardPrintDAO = new RegCardPrintDtlDAO(tx);
		DailyPatientDAO dailyPatDao= new DailyPatientDAO(tx);
		try
		{
			tx.begin();
			
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			_regCardPrintVO.setStrBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
			regCardPrintDAO.saveReprintCard(daoObj,_regCardPrintVO, objUserVO_p,"1");
			if (!(directChargeVO.getPatAmountCollected().equals("0") || directChargeVO.getPatAmountCollected().equals("0.00") || 
						directChargeVO.getPatAmountCollected().equals("") || 
						directChargeVO.getPatAmountCollected() == null)) 
				{
					directChargeVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
					directChargeVO.setTariffId(RegistrationConfig.DUPLICATE_RENEW_CARD_TARIFF_ID);
					directChargeVO.setModuleId(objUserVO_p.getModuleId());
					directChargeVO.setBillNo(_regCardPrintVO.getStrBillNo());
					directChargeVO.setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_DUPLICATE);
				///registration module billing
				directChargeDao.create(daoObj,directChargeVO, objUserVO_p);
				
				daoObj.fire();
				}
				episodeVO=new EpisodeVO();
				
				//******** Query to fecth Disclaimer for printing *********************/
				String usablityFlag="";
				if(_regCardPrintVO.getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
					usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
				else if(_regCardPrintVO.getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL))
					usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
				else if(_regCardPrintVO.getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY))
					usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY;
				episodeVO.setDisclaimer(getDisclamer(usablityFlag, _regCardPrintVO.getDepartmentUnitCode(), objUserVO_p));
							
				//****************************************/
			
				//query to fetch working days for printing
			//	------------------for now
				//episodeVO.setUnitWorkingDays(essentialDAO.getUnitWorkingDays(episodeVO.getDepartmentUnitCode(), objUserVO_p));
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO;
	}
	
	

	public EpisodeVO saveReprintCard(RegCardPrintVO regCardPrintVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO=new EpisodeVO();
		try
		{
			tx.begin();
			HisDAO daoObj= new HisDAO("Registration","PatientBO");

			RegCardPrintDtlDAO regCardPrintDAO = new RegCardPrintDtlDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			{
				regCardPrintDAO.saveReprintCard(daoObj,regCardPrintVO, userVO,"1");
			
				//******** Query to fecth Disclaimer for printing *********************/
					String usablityFlag="";
					if(regCardPrintVO.getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
					else if(regCardPrintVO.getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
					else if(regCardPrintVO.getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY;
					episodeVO.setDisclaimer(getDisclamer(usablityFlag, regCardPrintVO.getDepartmentUnitCode(), userVO));
								
					//****************************************/
					daoObj.fire();
				//query to fetch working days for printing
			//	episodeVO.setUnitWorkingDays(essentialDAO.getUnitWorkingDays(regCardPrintVO.getDepartmentUnitCode(), userVO));
				//***************************************//
			}
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO;
	}
	/*** Added For New Dept Visit --> Start **/
	public EpisodeRefDtlVO[] getReferPat(UserVO objUserVO_p, String strMode_p)
	{
		EpisodeRefDtlVO[] objEpisodeRefDtlVO_p;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		System.out.println("PatientBO :: getReferPat()");
		try
		{
			tx.begin();
			EpisodeRefDtlDAO objEpisodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			objEpisodeRefDtlVO_p = objEpisodeRefDtlDAO.getReferPat(objUserVO_p,strMode_p);
			System.out.println("objEpisodeRefDtlVO_p.size :"+objEpisodeRefDtlVO_p!=null?objEpisodeRefDtlVO_p.length:0);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objEpisodeRefDtlVO_p;
	}
	
	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 * 
	 * @param objPatientVO_p Provides Patient details.
	 * @param objUserVO_p Provides User details.
	 * @return patStatus. Sets patStatus=11 if Patient status if IPD, patStatus=12 is Patient status is OPD, patStatus=13 if
	 *         Patient is dead.
	 */

	
	public EpisodeVO[] newDepartmentVisitStamp(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, EpisodeRefDtlVO episodeRefVO, UserVO objUserVO_p,PatientVO _oldPatientVO )
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
			tx.begin();
	
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			PatientDAO objPatientDAO = new PatientDAO(tx);
			
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			DirectChageDetailVO[] directChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
			RenewalDetailVO[] renewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
			PatientIdVO objPatientIdVO = new PatientIdVO();
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(tx);
			PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
			BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
			CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			
			String amount = objArrEpisodeVO_p[0].getPatAmountCollected();
			//String amount = objArrEpisodeVO_p[0].getPatActualAmount();
			String strExpiryDate="";	//,strOldExpiryDate="",strNewExpiryDate="";
			boolean flagPatSaveStatus = false;
			String patPrimaryCatGrp="";
			
			System.out.println("PatientBO :: newDepartmentVisitStamp()");
			objHisDAO = new HisDAO("Registration","PatientBO");
			
				//if(!objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT)){
			if(objPatientVO_p.getPatStatusCode()!=null && !objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT)){
					flagPatSaveStatus=true;
					objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
				}
			
				// EpisodeRefDtlVO objEpisodeRefDtlVO_p=new EpisodeRefDtlVO();

				////updating patient detail if department wise mandatory fields are captured
				
				for (int i = 0; i < objArrEpisodeVO_p.length; i++)
				{
					
					DailyPatientVO dailyPatVO = new DailyPatientVO();
					
					/**By Mukund on 13.04.2017 */
					/*if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
					{
						Date dt1,dNowP14=null; 
						String dNowP14Str="";
						Calendar cldr = Calendar.getInstance();
						cldr.add(Calendar.DATE, 14);
						SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
						dNowP14Str =dateFormat.format(cldr.getTime());
						dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
						dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
						//compare two dates
						int judge = dt1.compareTo(dNowP14);
						if(judge<=0)// dt1 is earlier than dNowP14
						{
							strExpiryDate = dateFormat.format(dt1);
						}
						else if(judge>0)//dt1 is later than dNowP14
						{
							strExpiryDate = dateFormat.format(dNowP14);
						}
					}
					else
					{
						strExpiryDate = objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
					}*/
					strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
					/**End on 13.04.2017 */
					
					if(objPatientVO_p.getPatNationalId()!= null && !objPatientVO_p.getPatNationalId().equals("")){
						//PatientIdDetailDAO objPatientIdDetailDAO=new PatientIdDetailDAO(tx);
						String aadharUUID = objPatientIdDetailDAO.getAadhaarReferenceUUID(objPatientVO_p.getPatCrNo(), objPatientVO_p.getPatNationalId(), "2");
						objPatientVO_p.setPatNationalId(aadharUUID);
					}
					
					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, objPatientVO_p);
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
					objArrEpisodeVO_p[i].setTariffId(objUserVO_p.getTariffID());
					String referMlcNo="";
					referMlcNo=episodeRefVO.getMlcNo();
					
					objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					if(objPatientVO_p.getUnitRosterType().equals(RegistrationConfig.ROSTERTYPE_SPECIAL))
						objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					else
						objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
					
					objArrEpisodeVO_p[i].setPatCrNo(dailyPatVO.getPatCrNo());
					objArrEpisodeVO_p[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					objArrEpisodeVO_p[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
					
					objArrEpisodeVO_p[i].setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate this dailyPatient VO with objArrEpisodeVO_p[i]
					HelperMethods.populatetToNullOrEmpty(dailyPatVO, objArrEpisodeVO_p[i]);
					//System.out.println("objPatientVO_p.getDepartmentCode() [In PatientBO]:"+objPatientVO_p.getDepartmentCode());
					//System.out.println("objArrEpisodeVO_p[i].getDepartmentCode()  [In PatientBO]:"+objArrEpisodeVO_p[i].getDepartmentCode());
					objPatientVO_p.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
					dailyPatVO.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
					dailyPatVO.setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
					
					dailyPatVO.setIsReferred(objPatientVO_p.getIsReferred());
					
					//Code for setting MLC Flag Referred if Patient is refered from MLC Episode
					if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						strExpiryDate = episodeRefVO.getEpisodeExpiryDate();//By Mukund on 01.05.2017
						if (episodeRefVO.getMlcFlag()!=null && (episodeRefVO.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_PROVISIONAL) || episodeRefVO.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_CONFIRMED)))
						 dailyPatVO.setMlcFlag(RegistrationConfig.MLC_FLAG_REFFERED);
					}
					
					///In case of new dept visit is old is false
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					
					dailyPatVO.setEpisodeVisitNo("1");
					
					//dailyPatVO.setDepartmentCode(objPatientVO_p.getDepartmentCode());
					//dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
					
					dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD,RegistrationConfig.UNIT_TYPE_GENERAL,"NDOPD");
					
					dailyPatVO.setBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
					
					dailyPatVO.setEpisodeCode(dailyPatDao.generateEpisodeCode(objUserVO_p, dailyPatVO.getPatCrNo(),dailyPatVO.getDepartmentUnitCode()));
					
					// Setting Expiry Date(General,Special & Casuality) in PatientVo and so DailyPatientVO
					PatientBOHelper.setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, dailyPatVO, objArrEpisodeVO_p[i], strExpiryDate);
					
					
					dailyPatDao.saveDailyPatientDtl(objHisDAO, dailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
					HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
					if(dailyPatVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG))
						objArrEpisodeVO_p[i].setMlcNo(referMlcNo);
					
					//Create new entry in Episode detail
					
					if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
						}

					}
					else
					{
						objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					}

					objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
					
					////setting visit number//
					
					objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
					objArrEpisodeVO_p[i].setEntryDate(objPatientVO_p.getSystemDate());
					
					
					
					System.out.println("----Pat Ren Req--------"+objPatientVO_p.getOpdRenewalRequired()+"------------");
					System.out.println("----Date--------"+objPatientVO_p.getExpiryDate()+"------------");
					System.out.println("objPatientVO_p.getDepartmentUnitCode() :"+objPatientVO_p.getDepartmentUnitCode());
					System.out.println("objArrEpisodeVO_p[i].getDepartmentUnitCode() :"+objArrEpisodeVO_p[i].getDepartmentUnitCode());
					
					
					// if patient is registered from some other hospital and visit another hospital
					// Data is saved to Patient Hospital table including expiry..
					if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1"))
					{
						PatientHospitalDtlVO objPatientHospitalDtlVO = new PatientHospitalDtlVO();
						HelperMethods.populate(objPatientHospitalDtlVO, objPatientVO_p);
						PatientHospitalDtlDAO objPatientHospitalDtlDAO = new PatientHospitalDtlDAO(tx);
						
						//if data is not found in hospital table then new record is inserted for that patient
						if(objPatientVO_p.getOtherHospitalDataFound()==null || !objPatientVO_p.getOtherHospitalDataFound().equals("1")){
							//Calling DAO
							objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "1");
							
							if(!objPatientVO_p.getPatIdNo().equals("")){//isEmpty modified
								HelperMethods.populate(objPatientIdVO, objPatientVO_p);
								objPatientIdVO.setPatDocType("99");
								//Calling DAO
								objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
							}
						}
						//else only expiry updation is done
						else{
							if(objPatientVO_p.getRenewalConfig()!=null && objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("1")|| objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("2"))
							{
								if(objPatientVO_p.getOpdRenewalRequired()!=null && 
										(objPatientVO_p.getOpdRenewalRequired().equals("1")|| objPatientVO_p.getOpdRenewalRequired().equals("2")))
								{
									//objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
									//PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
									//Calling PatientDAO
									objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "2");
								}else{
									if(flagPatSaveStatus)
										objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "3");
								}
							}
						}
					}
					else
					{
						if(objPatientVO_p.getRenewalConfig()!=null && objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("1")|| objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("2"))
						{
							if(objPatientVO_p.getOpdRenewalRequired()!=null && 
									(objPatientVO_p.getOpdRenewalRequired().equals("1")|| objPatientVO_p.getOpdRenewalRequired().equals("2")))
							{
								//objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
								//PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
								//Calling PatientDAO
								objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
							}else{
								if(flagPatSaveStatus)
									objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "6");
							}
						}
					}
					
					 
					//Calling EpisodeDAO
					objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
					objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
					objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate()); 
					episodeDao.saveEpisodeDtl(objHisDAO, objArrEpisodeVO_p[i], objUserVO_p, "1");
					/**Call to save episode details in 'hrgt_seccat_change_dtl'*/
					secondaryCategoryChangesForEveryEpisodeCreated( tx, objUserVO_p, objArrEpisodeVO_p[i], objPatientVO_p);
					/***/

					if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						
						String refEpisode = episodeRefVO.getEpisodeCode();
						String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
						String serialNo = episodeRefVO.getSerialNo();
						HelperMethods.populate(episodeRefVO, objArrEpisodeVO_p[i]);
						episodeRefVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());

						if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							episodeRefVO.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							episodeRefVO.setEpisodeVisitNo("1");

						}
						if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							episodeRefVO.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							episodeRefVO.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							episodeRefVO.setToEpisodeVisitNo("1");
							episodeRefVO.setEpisodeCode(refEpisode);
							episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
							episodeRefVO.setExternalHospitalCode("");
							episodeRefVO.setSerialNo(serialNo);
						}

						//Calling EpisodeRefDtlDAO
						if (objPatientVO_p.getIsPatReferByList() != null
								&& objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							
							episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO, episodeRefVO, objUserVO_p,"2");	// for updating Acceptance Date
						}
						else
						{
							if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals("1"))
								episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO, episodeRefVO, objUserVO_p, "1");
						}
						
					}
					//Modified to add the details on renewal table while new department visit cum renewal by Singaravelan on 08-10-13
					
					//////////////////////////////////////////
					
					//////////////////////////////////////////
					System.out.println("----Amount-----"+amount+"-----------");
					//if (amount != null && !amount.equals("") && !amount.equals("0") && !amount.equals(RegistrationConfig.PAT_CAT_FREE_FEES))
					if (amount != null)
					{
						directChargeVO[i]= new DirectChageDetailVO();
						directChargeVO[i].setTariffId(objUserVO_p.getTariffID());
						directChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						directChargeVO[i].setModuleId(objUserVO_p.getModuleId());
						HelperMethods.populate(directChargeVO[i], objArrEpisodeVO_p[i]);
						directChargeVO[i].setPatAmountCollected(objPatientVO_p.getPatAmountCollected());
						directChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
						directChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
						directChargeVO[i].setCreditBillFlag(objPatientVO_p.getCreditBillFlag());
						directChargeVO[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
						directChargeVO[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
						directChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
						
						directChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
						directChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
						directChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
						directChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
						/*Start: Surabhi
						 * reason: for adding the organisation name */
						directChargeVO[i].setClientName(objPatientVO_p.getClientName());
						//End
						directChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
						directChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
						directChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
						directChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
						directChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());
						//directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
						directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);

						
				if(!(directChargeVO[i].getPaymentMode().equals("1") || directChargeVO[i].getPaymentMode().equals("10") || directChargeVO[i].getPaymentMode().equals("13")))
				     directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
				else
				     directChargeVO[i].setPaymentModeRefId("");//for cash
						
						/*if (!(directChargeVO[i].getPatAmountCollected().equals("0") || directChargeVO[i].getPatAmountCollected().equals("") || directChargeVO[i]
								.getPatAmountCollected() == null)) 
						{
							directChargeDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
						}*/
						
						//if (!(directChargeVO[i].getPatAmountCollected().equals("0") 
						//		&& !(directChargeVO[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
						//		&& !directChargeVO[i].getPatAmountCollected().equals("") 
						//		&& directChargeVO[i].getPatAmountCollected() != null)) 
						//{
							directChargeDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
						//}
						///Billing module integration
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
						// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
						
						if((objArrEpisodeVO_p[i].getOpdRenewalRequired()!=null && objArrEpisodeVO_p[i].getOpdRenewalRequired().equals("1"))|| 
								(objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equalsIgnoreCase("1")))
						{
							renewDetailVO[i] = new RenewalDetailVO();
							renewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
							renewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
							renewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							renewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
							renewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
							renewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							renewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							renewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
							renewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
							renewDetailVO[i].setNewExpiryDate(strExpiryDate);
							renewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

							renewDetailDAO.saveRenewalDtl(objHisDAO,renewDetailVO[i], objUserVO_p,"1");
						
						}	
						
						//This code commented by Garima on 09 Jan 2019 as For CM Relief Fund in AIIMS Letter will be entered only through separaye process--Start
						
						//For the Details Insertion in the Beneficiary Patient Dtl Table
						/*
						if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
								(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) 
										|| objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
								)){
							
							patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
							//Need to remove this cond once the staff no constraints from the table is removed
							//if((objPatientVO_p.getStaffCardNo()!=null&&!objPatientVO_p.getStaffCardNo().equals(""))||
							//		(objPatientVO_p.getAgsNo()!=null&&!objPatientVO_p.getAgsNo().equals(""))){
							if(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
							{
								objPatientVO_p.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);
								objPatientVO_p.setClientName(RegistrationConfig.Client_Name_Aarogya_Cat);
								}
							
							HelperMethods.populate(objBenPatVO, objPatientVO_p);
							HelperMethods.setNullToEmpty(objBenPatVO);
							
							if(!objBenPatVO.getAgsNo().equals(""))
								objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
							else if(!objBenPatVO.getStaffCardNo().equals(""))
								objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
							else
								objBenPatVO.setCardNo("NA");
							
							if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
								objBenPatVO.setIsDependent("1");
								objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
							}else{
								objBenPatVO.setIsDependent("0");
								objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
							}
							objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
							objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
							objBenPatVO.setClientName(objPatientVO_p.getClientName());
							
							
							
							
							//commented by Garima on 19th July 2016 to save patient credit details--start
							//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
							//by mukund on 26.07.2016
							objBenPatVO.setPatActualAmount(objPatientVO_p.getPatActualAmount());
							
							if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
							{
								if(Double.valueOf(objBenPatVO.getPatActualAmount())>0)
								{
								if(!objPatientVO_p.getOldCreditLetterRefNo().equals(objBenPatVO.getCreditLetterRefNo()))//By Mukund to call for insert records in creditPatDtl table only when the CreditLetterNo not equals newCreditLetterNo
									benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
								else
									benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "2");//to update the balance credit limit
								}
							}
							else if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
							{
								if(Double.valueOf(objBenPatVO.getPatActualAmount())>0)
								{
								if(!objPatientVO_p.getOldAgsNo().equals(objBenPatVO.getAgsNo()))//By Mukund to call for insert records in creditPatDtl table only when the CreditLetterNo not equals newCreditLetterNo
									benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
								else
									benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "2");//to update the balance credit limit
								}
							}
							//end
							//}
												
						}
						*/
						//This code commented by Garima on 09 Jan 2019 as For CM Relief Fund in AIIMS Letter will be entered only through separaye process--End
						
						


						//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL 
						if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
							HelperMethods.populate(objCreditAvailDtlVO, directChargeVO[i]);
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
							
							objCreditAvailDtlVO.setTariffActualRate(directChargeVO[i].getPatActualAmount());
							objCreditAvailDtlVO.setPaidByClientAmt(directChargeVO[i].getPatActualAmount());
							objCreditAvailDtlVO.setPaidByPatAmt("0");
							objCreditAvailDtlVO.setNetAmount(directChargeVO[i].getPatActualAmount());
							objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());
							objCreditAvailDtlVO.setClientName(objPatientVO_p.getClientName());

							//Need to include approved by here
							if(Double.valueOf(objCreditAvailDtlVO.getNetAmount())>0)
							creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO, objCreditAvailDtlVO, objUserVO_p, "1");
						}
					
					}
					
					//****** query to fetch disclaimer for printing ************//*
						objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, objArrEpisodeVO_p[i].getDepartmentUnitCode(), objUserVO_p));
						
					//**************************************************//*	
				
				
			}
			synchronized (objHisDAO) 
			{
			   objHisDAO.fire();
			}		
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch(HisSQLManualException e){	
			tx.rollback();
			throw new HisSQLManualException("Department-Unit Limit Exhausted");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
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
			System.out.println("Close the transaction...");
			// System.out.println("objPatientVO_p.getDepartment()::::"+objPatientVO_p.getDepartment());
			System.out.println("objArrEpisodeVO_p.getDepartment()::::" + objArrEpisodeVO_p[0].getDepartment());
			if (objHisDAO != null) {
				objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	/**
	 * Checks whether currently any episode is opened for a patient in emergency on the basis of CR No. Also checks that the
	 * episodes should be valid and active.
	 * 
	 * @param objPatientVO_p Provides CR No of the patient whose address is to be searched.
	 * @param objUserVO_p Provides User details.
	 * @return <code>true</code> if patient's any episode is currently opened in Emergency otherwise <code>false</code>.
	 */
	/*public boolean checkOpenEmgEpisodeByCrNo(PatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		System.out.println("inside searchPatientByCrNo() of PatientBO");
		// System.out.println("_crNo::"+ _crNo);
		String str = objPatientVO_p.getPatCrNo();
		int x = 0;
		System.out.println("objPatientVO_p.getPatCrNo()::str:: " + str);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of PatientDAO");
			System.out.println("objPatientVO_p.getPatCrNo()::str " + str);
			System.out.println("inside newDeptVisitStamp()");
			// boolean value=false;

			x = episodeDao.checkOpenEmgEpisodeByCrNo(objPatientVO_p, objUserVO_p);
			
			 * if(x>0) throw new HisEpisodeOpenInEmergencyException();
			 

		}
		
		
		 * catch(HisEpisodeOpenInEmergencyException e){ System.out.println("inside BO HisEpisodeOpenInEmergencyException");
		 * System.out.println(e.getMessage()); tx.rollback(); throw new HisEpisodeOpenInEmergencyException(e.getMessage()); }
		 
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside BO HisRecordNotFoundException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside BO Exception");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}
		if (x > 0) return true;
		else return false;
	}*/
	
	public Map getMapDeptWiseFileNo(String crNo, UserVO objUserVO_p)
	{

		Map mapFielNo = new HashMap();
		// List lstFileNo=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);

			//mapFielNo = objEpisodeDAO.getDeptWiseFileNo(crNo, objUserVO_p);
			// mapFielNo.put(RegistrationConfig.LIST_DEPT_WISE_FILE_NO,lstFileNo);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		System.out.println("mapFielNo... " + mapFielNo);
		return mapFielNo;
	}
	
	
	public EpisodeRefDtlVO[] retrieveAllOpenOPDEpisodes(String strCrNo_p, UserVO objUserVO_p, String strMode_p,String visitType_p)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] objArrEpisodeVO;
		EpisodeRefDtlVO[] objArrOpenOPDEpisode;

		try
		{
			System.out.println("PatientBO :: retrieveAllOpenOPDEpisodes()");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			// populate the addressVO with required details
			objArrEpisodeVO = episodeDao.getEpisodeDtlByCrNoByDeptByUnit(strCrNo_p,"","","", objUserVO_p,strMode_p,visitType_p);// .retrieveOldPatientEpisodes(crNo,objUserVO_p);
			System.out.println("objArrEpisodeVO.length :"+objArrEpisodeVO!=null?objArrEpisodeVO.length:0);
			objArrOpenOPDEpisode = new EpisodeRefDtlVO[objArrEpisodeVO.length];
			for (int i = 0; i < objArrEpisodeVO.length; i++)
			{
				objArrOpenOPDEpisode[i] = new EpisodeRefDtlVO();
				HelperMethods.populate(objArrOpenOPDEpisode[i], objArrEpisodeVO[i]);
				objArrOpenOPDEpisode[i].setFromDepartmentCode(objArrEpisodeVO[i].getDepartmentCode());

				objArrOpenOPDEpisode[i].setFromDepartment(objArrEpisodeVO[i].getDepartment());
				objArrOpenOPDEpisode[i].setFromDepartmentUnit(objArrEpisodeVO[i].getDepartmentUnit());
				objArrOpenOPDEpisode[i].setFromDepartmentUnitCode(objArrEpisodeVO[i].getDepartmentUnitCode());
				//By Mukund on 01.05.2017
				objArrOpenOPDEpisode[i].setEpisodeExpiryDate(objArrEpisodeVO[i].getEpisodeExpiryDate());
				objArrOpenOPDEpisode[i].setIsReferred(objArrEpisodeVO[i].getIsReferred());
				//End Mukund on 01.05.2017
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrOpenOPDEpisode;
	}
	
	/*public void saveRenewalDetail(PatientVO objPatientVO_p, String _amount, UserVO objUserVO_p, String _newExpiryDate)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		HisDAO objHisDAO=null;
		try
		{
			tx.begin();
			PatientDAO patientDAO = new PatientDAO(tx);
			RenewalDetailDAO renewalDetailDAO = new RenewalDetailDAO(tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(tx);
			objHisDAO = new HisDAO("Registration","PatientBO");
			// //update hrgt_patient_dtl//////
			patientDAO.updateExpiryDate(objPatientVO_p.getPatCrNo(), _newExpiryDate, objUserVO_p);
			// ///update hrgt_renew_dtl////
			renewDetailVO.setPatCrNo(objPatientVO_p.getPatCrNo());
			renewDetailVO.setSeatId(objUserVO_p.getSeatId());
			renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
			renewDetailVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
			renewDetailVO.setEntryDate(objPatientVO_p.getEntryDate());
			renewDetailVO.setDepartmentCode(objPatientVO_p.getDepartmentCode());
			renewDetailVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
			renewDetailVO.setHospitalCode(objUserVO_p.getHospitalCode());
			renewDetailVO.setOldExpiryDate(objPatientVO_p.getExpiryDate());
			renewDetailVO.setNewExpiryDate(_newExpiryDate);
			renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

			renewalDetailDAO.create(renewDetailVO, objUserVO_p);

			// ///update hrgt_reg_charge_dtl//////
			regChargeDtlVO.setPatAmountCollected(_amount);
			regChargeDtlVO.setPatCrNo(objPatientVO_p.getPatCrNo());
			regChargeDtlVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
			regChargeDtlVO.setPatSecondaryCatCode(objPatientVO_p.getPatSecondaryCatCode());
			regChargeDtlVO.setSeatId(objUserVO_p.getSeatId());
			regChargeDtlVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
			regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
			regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
			regChargeDtlVO.setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
			if (!_amount.equals("0"))
			{
				regChargeDtlDAO.create(objHisDAO,regChargeDtlVO, objUserVO_p);
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{

			tx.close();
		}

	}*/
	
	/*** Added For New Dept Visit --> Start **/
	
	/* Garima Patient Referral Starts*/
	public Map getAllOpenEpisodesVisitedToday(String crNo, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeDupVO;
		EpisodeVO[] objArrEpisodeVO_p;
		String reprintMode = "";

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			objArrEpisodeVO_p = episodeDao.getAllOpenEpisodesVisitedToday(crNo, userVO, "11");
			essentialMap.put(RegistrationConfig.PATIENT_REFERRAL_EPISODEVO_ARR_REGISTRATION, objArrEpisodeVO_p);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	

		
	/* Garima Patient Referral Starts*/

	public EpisodeVO[] newSplRegistration(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p) throws HisDuplicateRecordException
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		boolean isDuplicateReg=false;
		String[] crNoArr = null;
		String strPatIdErrMsg="";
		String patPrimaryCatGrp="";
		String patUID= "";
		//synchronized (PatientDAO.class)
		//{
		try
		{
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			Map essentialMap = new HashMap();

			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			DirectChargeDetailDAO directChargeDtlDao=new DirectChargeDetailDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RegEssentialDAO regEssentialDAO = new RegEssentialDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
			BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
			CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
			
			List allPatientVoList=new ArrayList();

			
			int arrEpisodeVOLength=objArrEpisodeVO_p.length;
			
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[arrEpisodeVOLength];
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			PatientIdVO objPatientIdVO = new PatientIdVO();
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			
			for (int i = 0; i < arrEpisodeVOLength; i++)
			{
				EpisodeReferVO objEpisodeReferVO = new EpisodeReferVO();
				EpisodeRefDtlVO objEpisodeRefDtlVO = new EpisodeRefDtlVO();
				
				objPatientVO_p.setIsValid(Config.IS_VALID_ACTIVE);
				objPatientVO_p.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
				objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				objPatientVO_p.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				objPatientVO_p.setIsBroughtDead("0");
			
				objPatientVO_p.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				objPatientVO_p.getPatAddress().setSeatId(objPatientVO_p.getSeatId());
				AddressVO addressVO=objPatientVO_p.getPatAddress();
				addressVO.setPatIsUrban(objPatientVO_p.getPatIsUrban());
				addressVO.setPatAddPhoneOwner(objPatientVO_p.getPatAddPhoneOwner());
				addressVO.setPatAddPhoneNo(objPatientVO_p.getPatAddPhoneNo());
				
				
				///temp code for trapping error	(Has to delete)
				if(objArrEpisodeVO_p==null || arrEpisodeVOLength==0)
						throw new HisApplicationExecutionException("Episode Array is Null");
				
				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
				PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
				
			
				
				dailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
				objArrEpisodeVO_p[i].setPatCrNo(dailyPatVO.getPatCrNo());
				objArrEpisodeVO_p[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
				objArrEpisodeVO_p[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				objArrEpisodeVO_p[i].setIsValid(Config.IS_VALID_ACTIVE);
				objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
				objPatientVO_p.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				
				//HelperMethods.setNullToEmpty(objPatientVO_p.getPatAddress());
				HelperMethods.populate(dailyPatVO, objArrEpisodeVO_p[i]);
				HelperMethods.populate(dailyPatVO, objPatientVO_p);

				if (objPatientVO_p.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
				{
					dailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					dailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
					objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);						
				}
				else
				{
					dailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					dailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				}
				dailyPatVO.setIsReferred(objPatientVO_p.getIsReferred());
				
				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				dailyPatVO.setEpisodeVisitNo("1");
				
				//dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				//Start:Sheeldarshi:17Oct'14
				//Generation of Unique Health ID Added by Singaravelan on 09-Oct-2014
				String[] dob={"1","Jan","1900"};
				String tempDob="";
				if(objPatientVO_p.getPatDOB()!=null&&!objPatientVO_p.getPatDOB().equals("")){
					dob=objPatientVO_p.getPatDOB().split("-");
				}
				else{
					tempDob=regEssentialDAO.getBirthYear(objPatientVO_p, objUserVO_p);
					if(tempDob!=null){
						dob=tempDob.split("-");
					}
				}
				HealthId hid = null;
				//HealthId hid=new HealthId("Ramesh","B/o Kumar","A S Kumar","Namita Kumar","Male",1984,20,260);
				hid=new HealthId(objPatientVO_p.getPatFirstName(),objPatientVO_p.getPatLastName(),objPatientVO_p.getPatGuardianName(),objPatientVO_p.getPatMotherName(),objPatientVO_p.getPatGenderCode(),Integer.parseInt(dob[2]),Integer.parseInt(objPatientVO_p.getPatAddStateCode()),Integer.parseInt(objPatientVO_p.getPatAddDistrictCode()), objPatientVO_p.getPatAddMobileNo());				
				
				GenerateID gid=new GenerateID(hid);
				System.out.println("------HealthId----"+gid.generateSecondaryHealthID()+"------------");	
				objPatientVO_p.setPatSecUHID(gid.generateSecondaryHealthID());

				if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient()!="1"){//To Bypass the Secondary UHID Check to Save As New Patient Added by Singaravelan on 13-Oct-2014
				
				
				if(objPatientVO_p.getIsDuplicate()!=null && objPatientVO_p.getIsDuplicate().equals("1")){
					// Do Nothing
				}
				else{
						//crNoArr=regEssentialDAO.checkDuplicateRegistration(objPatientVO_p, objUserVO_p);
						crNoArr=regEssentialDAO.checkDuplicateRegistrationUHID(objPatientVO_p, objUserVO_p);
						//End:Sheeldarshi:17Oct'14
						if(crNoArr!=null)
						{
							PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
							
							for(i=0;i<crNoArr.length;i++)
							{
								PatientVO vo=new PatientVO();
								vo.setPatCrNo(crNoArr[i]);
								patientVOArr[i]=vo	;
							}
							for(i=0;i<crNoArr.length;i++)
							{
								allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], objUserVO_p));	
							}
							essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
						}
						if(crNoArr!=null)
						{	
							WebUTIL.setMapInSession(essentialMap, objRequest_p,"NewSplRegwithAptmntACTION");
							throw new HisDuplicateRecordException("Duplicate Registration");
						}
				}
						
				}		
				dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
				
							
				dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_SPECIAL,RegistrationConfig.UNIT_TYPE_SPECIALITY,"NRSPL");
				
				//generating the Cr Number
				String CrNO=patDao.generateCrNumber(objUserVO_p);
				objPatientVO_p.setPatCrNo(CrNO);
				objPatientVO_p.getPatAddress().setPatCrNo(objPatientVO_p.getPatCrNo());
				objPatientVO_p.setSeatId(objUserVO_p.getSeatId());
				//objPatientVO_p.setDepartmentUnitCode(dailyPatVO.getDepartmentUnitCode());
				//objPatientVO_p.setRoomCode(dailyPatVO.getRoomCode());
				/**By Mukund to create Aadhaar UUid using crno and session salt*/
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals(""))
				{
					/*
					-- Pre-requisite
					-- Cr No generated and patinet dtl has been saved in HRGT_patient-Dtl table
					-- a UUID has been generated as a Refernce Key and has been inserted in place of Adhaar no in HRGT_Patient_dtl and Id_dtl table
					-- Aadhaar no is not getting inserted in any table.
					-- Calling this method immediately after New Patient Insert, Adhaar no Detail Modi and Patient record delete (not a case right now)
					*/
					patUID=objPatientVO_p.getPatNationalId();
					String strHashedAadhaarUUID = "";
					strHashedAadhaarUUID = objPatientIdDetailDAO.getAadhaarReferenceUUID(CrNO, objPatientVO_p.getPatNationalId(), "1");//hissso.security.SecureHashAlgorithm.SHA1(objPatientVO_p.getPatCrNo()+ strSessionSalt);
					
					System.out.println("\nstrHashedAadhaarUUID: "+strHashedAadhaarUUID);
					//	
					//code for replacing uuid with national id and calling procedure to encrypt and store the aadhaar
					//
					objPatientIdDetailDAO.saveAadhaarDatainADV(CrNO, strHashedAadhaarUUID, objPatientVO_p.getPatNationalId(), objUserVO_p.getHospitalCode(), "1", objHisDAO);
					objPatientVO_p.setPatNationalId(strHashedAadhaarUUID);
					dailyPatVO.setPatNationalId(objPatientVO_p.getPatNationalId());
				}
				/**End on 11.Jan.18*/
				/**By Mukund on 13.04.2017 */
				String strExpiryDate ="";
				if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
				{
					Date dt1,dNowP14=null; 
					String dNowP14Str="";
					Calendar cldr = Calendar.getInstance();
					cldr.add(Calendar.DATE, 14);
					SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
					dNowP14Str =dateFormat.format(cldr.getTime());
					dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
					dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
					//compare two dates
					int judge = dt1.compareTo(dNowP14);
					if(judge<=0)// dt1 is earlier than dNowP14
					{
						strExpiryDate = dateFormat.format(dt1);
					}
					else if(judge>0)//dt1 is later than dNowP14
					{
						strExpiryDate = dateFormat.format(dNowP14);
					}
				}
				else
				{
					strExpiryDate = patDao.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), dailyPatVO.getDepartmentUnitCode());
				}
				//String strExpiryDate = patDao.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), dailyPatVO.getDepartmentUnitCode());
				/**End on 13.04.2017 */
				//String strExpiryDate = "21-Feb-2014";
			
				//objPatientVO_p.setExpiryDate(strExpiryDate);
				PatientBOHelper.setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, dailyPatVO, objArrEpisodeVO_p[i], strExpiryDate);
				//if(objPatientVO_p.getRenewalConfig().getStrRenewalType())
				
				dailyPatVO.setPatAge(objPatientVO_p.getPatAge());
				dailyPatVO.setPatDOB(objPatientVO_p.getPatDOB());
				dailyPatVO.setPatAgeUnit(objPatientVO_p.getPatAgeUnit());
				dailyPatVO.setIsActualDob(objPatientVO_p.getIsActualDob());
			//	dailyPatVO.setExpiryDate(strExpiryDate);
				
				// Generating Bill No
				dailyPatVO.setBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
				
				
				///get age or DOB
				//objPatientVO_p=patDao.getDOBAndAge(objPatientVO_p);
				
				//seting dialypatientvo details
				dailyPatVO.setPatCrNo(CrNO);
				
				dailyPatVO.setTariffId(objUserVO_p.getTariffID());
				
				//insert into daily
				dailyPatVO = dailyPatDao.saveDailyPatientDtl(objHisDAO,dailyPatVO, objUserVO_p,"1",RegistrationConfig.DAILYPATIENT_REG_NEW);
				
				if(dailyPatVO.getPatAgeWithUnit()!=null && dailyPatVO.getPatAgeWithUnit().equalsIgnoreCase("0 Min"))
					objPatientVO_p.setPatAgeWithUnit("0 Day");
				else
					objPatientVO_p.setPatAgeWithUnit(dailyPatVO.getPatAgeWithUnit());
//				String strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
//				//Setting Expiry(Episode Exiry) to make it available in episode while saving
//				 if(strRenewalType!=null && (strRenewalType.equals("3") || strRenewalType.equals("4")))
//					dailyPatVO.setExpiryDate(strExpiryDate);
				
				//objPatientVO_p.setPatAge(dailyPatVO.getPatAge());
				//objPatientVO_p.setPatDOB(dailyPatVO.getPatDOB());

				//For the Details Insertion in the Beneficiary Patient Dtl Table 
				
				//Start:Sheeldarshi
				if(objPatientVO_p.getPatPrimaryCatGrp()!=null && 
						(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
						objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))){
					
					patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrp();
					//Need to remove this cond once the staff no constraints from the table is removed
					if(!objPatientVO_p.getStaffCardNo().equals("")||!objPatientVO_p.getAgsNo().equals("")){
						//By mukund on 12.Apr.2017 for client code = 2000 for Arogyashri demanded by ateria sir	
						/*if(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
							objPatientVO_p.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);*/
							if(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
								objPatientVO_p.setClientCode("-1");
					HelperMethods.populate(objBenPatVO, objPatientVO_p);					
					if(!objBenPatVO.getAgsNo().equals(""))
						objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
					else if(!objBenPatVO.getStaffCardNo().equals(""))
						objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
					else if(objBenPatVO.getStaffCardNo().equals("") && !objPatientVO_p.getPatIdNo().equals(""))
						objBenPatVO.setCardNo(objPatientVO_p.getPatIdNo());
					
					if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
					
						objBenPatVO.setIsDependent("1");
						objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
					}else{
						objBenPatVO.setIsDependent("0");
						objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
					}
					objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
					objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
					
					//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
					//by mukund on 26.07.2016
					objBenPatVO.setPatActualAmount(objPatientVO_p.getPatActualAmount());
					//By Mukund on 25.07.2016
					//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
					benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
					}
										
				}
				//End:Sheeldarshi
				
				//insert into patient
				objPatientVO_p = patDao.savePatientDtl(objHisDAO,objPatientVO_p, objUserVO_p,"1");
				/**
				 * Create the json & 
				 * mark entry into minimal table 
				 **/
				String jsonStr= "{\"crno\":\""+objPatientVO_p.getPatCrNo()+"\", \"uid\":\""+patUID+"\", \"uhid\":\""+objPatientVO_p.getPatSecUHID()+"\", \"mobileNo\":\""+objPatientVO_p.getPatAddMobileNo()+"\", "
						+ "\"patCategoryCode\":\""+objPatientVO_p.getPatPrimaryCatCode()+"\", \"name\":\""+objPatientVO_p.getPatFirstName()+" "+objPatientVO_p.getPatMiddleName()+" "+objPatientVO_p.getPatLastName()+"\", \"nameArray\":[\""+objPatientVO_p.getPatFirstName()+"\",\" "+objPatientVO_p.getPatMiddleName()+"\",\" "+objPatientVO_p.getPatLastName()+"\"], "
						+ "\"gender\":\""+objPatientVO_p.getPatGenderCode()+"\", \"dob\":\""+objPatientVO_p.getPatDOB()+"\", \"ageInYears\":\""+objPatientVO_p.getPatAgeWithUnit()+"\", \"yob\":\""+Integer.parseInt(dob[2])+"\", \"fatherName\":\""+objPatientVO_p.getPatGuardianName()+"\", "
						+ "\"motherName\":\""+objPatientVO_p.getPatMotherName()+"\", \"spouseName\":\""+(objPatientVO_p.getPatHusbandName().equals("")?"N/A":objPatientVO_p.getPatHusbandName())+"\", \"house\":\""+objPatientVO_p.getPatAddHNo()+"\", \"loc\":\""+objPatientVO_p.getPatAddStreet()+" "+objPatientVO_p.getPatAddCityLoc()+" "+objPatientVO_p.getPatAddCity()+"\", "
						+ "\"vtc\":\""+objPatientVO_p.getPatAddCity()+"\", \"dist\":\""+objPatientVO_p.getPatAddDistrict()+"\", \"distCode\":\""+objPatientVO_p.getPatAddDistrictCode()+"\", "
						+ "\"state\":\""+objPatientVO_p.getPatAddState()+"\", \"stateCode\":\""+objPatientVO_p.getPatAddStateCode()+"\", \"pc\":\""+objPatientVO_p.getPatAddPIN()+"\"}";
				

				QRCodeTest objQRC = new QRCodeTest();
				objQRC.createQRCode(jsonStr, objRequest_p);
				
				patDao.savePatientMinimalDtl(objHisDAO,objPatientVO_p, jsonStr, objUserVO_p, "1");
				
				//Added by Garima on 29 March 2019 for PHRMS HealthRecordIntegration
				objPatientVO_p = patDao.savePatDetailForMyHealthRecordIntegration(objHisDAO,objPatientVO_p, objUserVO_p,"1","1");
				
				//CreateQREntry(objPatientVO_p, jsonStr, objUserVO_p);
				/***/
				//insert into address dteial
				addDao.saveAddressDtl(objHisDAO,addressVO, objUserVO_p,"1");
				
				String deptName = dailyPatVO.getDepartment();
				dailyPatVO.setDepartment(deptName.substring(0,deptName.indexOf("(")));
				dailyPatVO.setDepartmentUnit(deptName.substring(deptName.indexOf("(")+1,deptName.length()-1));
			
				HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
			
				
				// Create new entry in Episode detail
				if (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					objEpisodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					objEpisodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				else 
					objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
				
				objArrEpisodeVO_p[i].setEntryDate(objPatientVO_p.getSystemDate());
				//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(objHisDAO,objArrEpisodeVO_p[i], objUserVO_p, tx);
				
				objArrEpisodeVO_p[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				
				objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_NEW);
				objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
				objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
				episodeDAO.saveEpisodeDtl(objHisDAO, objArrEpisodeVO_p[i], objUserVO_p,"1");
				/**Call to save episode details in 'hrgt_seccat_change_dtl'*/
				secondaryCategoryChangesForEveryEpisodeCreated( tx, objUserVO_p, objArrEpisodeVO_p[i], objPatientVO_p);
				/***/	

					
				if (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					HelperMethods.populate(objEpisodeReferVO, objPatientVO_p);
					HelperMethods.populate(objEpisodeReferVO, objArrEpisodeVO_p[i]);
					HelperMethods.populate(objEpisodeRefDtlVO, objArrEpisodeVO_p[i]);
					
					objEpisodeRefDtlVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
					objEpisodeRefDtlVO.setExternalHospitalCode(objPatientVO_p.getPatRefGnctdHospitalCode());
					objEpisodeRefDtlVO.setExternalHospitalDepartment(objPatientVO_p.getPatRefGnctdHospitalDept());
					objEpisodeRefDtlVO.setExternalHospitalDepartmentUnit(objPatientVO_p.getPatRefGnctdHospitalDeptUnit());
					objEpisodeRefDtlVO.setExternalHospitalDoctorName(objPatientVO_p.getPatRefDoctor());
					objEpisodeRefDtlVO.setExternalHospitalName(objPatientVO_p.getPatRefHospitalName());
					objEpisodeRefDtlVO.setExternalHospitalPatCrNo(objPatientVO_p.getPatRefGnctdHospitalCrno());
					objEpisodeRefDtlVO.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
					objEpisodeRefDtlVO.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
					
					episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO,objEpisodeRefDtlVO, objUserVO_p,"1");

				}
				
				directChargeVO[i]= new DirectChageDetailVO();
				HelperMethods.populate(directChargeVO[i], objArrEpisodeVO_p[i]);
				directChargeVO[i].setTariffId(objUserVO_p.getTariffID());
				directChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				directChargeVO[i].setModuleId(objUserVO_p.getModuleId());
				directChargeVO[i].setPatAmountCollected(objPatientVO_p.getPatAmountCollected());
				directChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
				directChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
				directChargeVO[i].setCreditBillFlag(objPatientVO_p.getCreditBillFlag());
				directChargeVO[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
				directChargeVO[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
				directChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_REGISTRATION);
				//Start:Sheeldarshi
				directChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
				directChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
				directChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
				directChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
				directChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
				directChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
				directChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
				directChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
				directChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());
				directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
				directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
				//directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
				directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);
				System.out.println(" get payment code "+objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);

				//By Warish for payment mode refid save for Aug'18
				if(!(directChargeVO[i].getPaymentMode().equals("1") || directChargeVO[i].getPaymentMode().equals("10") || directChargeVO[i].getPaymentMode().equals("13")))
		                directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
	              else
		               directChargeVO[i].setPaymentModeRefId("");//for cash
				
				//End:Sheeldarshi
				
				//if (!(directChargeVO[i].getPatAmountCollected().equals("0") || directChargeVO[i].getPatAmountCollected().equals("0.00") || directChargeVO[i].getPatAmountCollected().equals("") || directChargeVO[i]
				//		.getPatAmountCollected() == null)) 
				//{
					///registration module billing
					directChargeDtlDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
					////Billing module billing///
					//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
				//}
					
				//Start:Sheeldarshi	
					//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL 
					if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
						HelperMethods.populate(objCreditAvailDtlVO, directChargeVO[i]);
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
						
						objCreditAvailDtlVO.setTariffActualRate(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setPaidByClientAmt(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setPaidByPatAmt("0");
						objCreditAvailDtlVO.setNetAmount(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());

						//Need to include approved by here
						if(Double.valueOf(objCreditAvailDtlVO.getNetAmount())>0)
						creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO, objCreditAvailDtlVO, objUserVO_p, "1");
					}	
				//End:Sheeldarshi	
				HelperMethods.populate(objPatientIdVO, objPatientVO_p);
				
				// For Patient Primary Category Id (like Employee Id etc)
				//if(objPatientVO_p.getIsIdRequired()!=null && objPatientVO_p.getIsIdRequired().equals("2")
						//&& 
				if(objPatientVO_p.getPatIdNo()!=null && !objPatientVO_p.getPatIdNo().equals("") ){
					
					strPatIdErrMsg="Patient Id is not defined";
					if(objPatientVO_p.getPatIdNo().equals("undefined"))
						throw new HisApplicationExecutionException(strPatIdErrMsg);
					
					if(objPatientVO_p.getPatPrimaryCatGrp().equals("1"))
					{
						objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
						objPatientIdVO.setPatDocType("99");
						objPatientIdVO.setPatIsAlternateId("0");
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
					}					
					else
					{
						if(objPatientVO_p.getPatCatDocIsAlternateId()!=null && objPatientVO_p.getPatCatDocIsAlternateId().equals("1"))
						{
							objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
							objPatientIdVO.setPatDocType(objPatientVO_p.getPatCatDocCode());
							objPatientIdVO.setPatIsAlternateId(objPatientVO_p.getPatCatDocIsAlternateId());
							objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "10");
						}
					}
					
					System.out.println("HiddenCatOrRegstrdPopupFlag :"+objPatientVO_p.getHiddenCatOrRegstrdPopupFlag());
					if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag()!=null && !objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("")){
						/*objPatientIdVO.setPatIsAlternateId("0");
						if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("C")){
							objPatientIdVO.setPatDocType("99");
							objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
						}*/
						/*else if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A")){
							//System.out.println("AlreadyRegisteredFlag() :"+objPatientVO_p.getAlreadyRegisteredFlag());
							//System.out.println("PrevCrNo :"+objPatientVO_p.getPrevCrNo());
							objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
							if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1")){
								objPatientIdVO.setPatDocType("97");	// For Previous Registered
								objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "6");
							}else if(objPatientVO_p.getAlreadyRegisteredFlag().equals("2")){
								objPatientIdVO.setPatDocType("96");	// For Portal
								objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "5");
							}
						}*/
					}
					
				}
				/*else if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A"))
				{
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
					if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1"))
					{
						objPatientIdVO.setPatDocType("97");	// For Previous Registered
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "6");
					}
					else if(objPatientVO_p.getAlreadyRegisteredFlag().equals("2"))
					{
						objPatientIdVO.setPatDocType("96");	// For Portal
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "5");
					}
					
				}*/
				/*else if(objPatientVO_p.getIsIdRequired()!=null && objPatientVO_p.getIsIdRequired().equals("2")){
					objPatientIdVO.setPatDocType("99");
					objPatientIdVO.setPatIsAlternateId("0");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objUserVO_p, "1");
				}*/
				
//				PatientTempDetailDAO.updateStatus(objPatientVO_p.getPatCrNo(), objPatientIdVO.getPatIdNo(), "2", objUserVO_p.getHospitalCode(), objHisDAO);
				
				objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
				//Save Data in DAO.
				if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A"))
				{
					if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1"))
					{
						PatientTempDetailDAO.updateStatus("2",objPatientVO_p.getPatCrNo(), objPatientIdVO.getPatIdNo(), "2", objUserVO_p.getHospitalCode(), objHisDAO);
					}
					else
					{
						PatientTempDetailDAO.updateStatus("1",objPatientVO_p.getPatCrNo(), objPatientIdVO.getPatIdNo(), "2", objUserVO_p.getHospitalCode(), objHisDAO);
					}
				}
				// For AlterNate Id
				if(objPatientVO_p.getPatCardNo()!=null && !objPatientVO_p.getPatCardNo().equals("")){
					System.out.println("For ALterNate ID -->> PatCardNo :"+objPatientVO_p.getPatCardNo());
					
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatCardNo());
					objPatientIdVO.setPatIsAlternateId("1");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO,objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "1");
				}
				
				// For NationalId/ Aadhar No
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals("")){
					System.out.println("For NationalId/ Aadhar No -->> PatCardNo :"+objPatientVO_p.getPatNationalId());
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatNationalId());
					objPatientIdVO.setPatDocType("98");
					objPatientIdVO.setPatIsAlternateId("0");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "3");
				}
				
				

				//To update the CR No. and the Appointment Status in Appointment Detail Table in Special Clinic Registration with Appointment Added by Singaravealn on 28-Aug-2014
				
				if(objPatientVO_p.getPatAptNo()!=null && objPatientVO_p.getPatAptNo()!="")
				{
					patDao.updateAppointmentinSplClinicReg(objHisDAO, objPatientVO_p, objUserVO_p);
				}
				
			/***********************************************/	
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
				objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
				//objArrEpisodeVO_p[i].setDisclaimer("Test Disclaimer1@Test Disclaimer2@Test Disclaimer1@0@center");
					
			}

		}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch(HisSQLManualException e){	
			tx.rollback();
			throw new HisSQLManualException("Department-Unit Limit Exhausted");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException("Error, "+strPatIdErrMsg);
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException("Data Access Error, Contact System Administrator");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
			
		}
		//}
		return objArrEpisodeVO_p; // <<<
	}
	public void savePatientReferDetail(EpisodeRefDtlVO objEpisodeRefDtlVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		try
		{
			objHisDAO = new HisDAO("Registration","PatientBO");
			tx.begin();
			//EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);

			//for (int i = 0; i < objEpisodeRefDtlVO.length; i++)
			//{
				objEpisodeRefDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO,objEpisodeRefDtlVO, objUserVO_p,"1");
				
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
				
			//}
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
			throw new HisApplicationExecutionException("Error, Contact System Administrator"+e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}


	public EpisodeVO[] newEmgRegistration(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p,PatientBroughtByDtlVO _patBroughtByDtlVO) throws HisDuplicateRecordException
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		boolean isDuplicateReg=false;
		String[] crNoArr = null;
		String strPatIdErrMsg="";
		String patPrimaryCatGrp="";
		String patUID="";
		//synchronized (PatientDAO.class)
		//{
		try
		{
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			Map essentialMap = new HashMap();

			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			DirectChargeDetailDAO directChargeDtlDao=new DirectChargeDetailDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RegEssentialDAO regEssentialDAO = new RegEssentialDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
			CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			
			List allPatientVoList=new ArrayList();

			if(objPatientVO_p.getIsBroughtDead() != null && objPatientVO_p.getIsBroughtDead().equalsIgnoreCase("1"))
				objPatientVO_p.setPatIsDead("1");
			else
				objPatientVO_p.setIsBroughtDead("0");
			int arrEpisodeVOLength=objArrEpisodeVO_p.length;
			
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[arrEpisodeVOLength];
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			PatientIdVO objPatientIdVO = new PatientIdVO();
			
			if(objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)){
				if(objPatientVO_p.getPatPrimaryCatGrp().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)
						|| objPatientVO_p.getPatPrimaryCatGrp().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
					objPatientVO_p.setPatAmountCollected(RegistrationConfig.PAT_CAT_FREE_FEES);
				else
					objPatientVO_p.setPatAmountCollected(objPatientVO_p.getPatActualAmount());				
			}
				
			
			for (int i = 0; i < arrEpisodeVOLength; i++)
			{
				EpisodeReferVO objEpisodeReferVO = new EpisodeReferVO();
				EpisodeRefDtlVO objEpisodeRefDtlVO = new EpisodeRefDtlVO();
				
				objPatientVO_p.setIsValid(Config.IS_VALID_ACTIVE);
				objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_EMERGENCY);
				objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				//objPatientVO_p.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			
				objPatientVO_p.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				objPatientVO_p.getPatAddress().setSeatId(objPatientVO_p.getSeatId());
				AddressVO addressVO=objPatientVO_p.getPatAddress();
				addressVO.setPatIsUrban(objPatientVO_p.getPatIsUrban());
				
				
				///temp code for trapping error	(Has to delete)
				if(objArrEpisodeVO_p==null || arrEpisodeVOLength==0)
						throw new HisApplicationExecutionException("Episode Array is Null");

				
				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
				PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
				
				dailyPatVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
				objArrEpisodeVO_p[i].setPatCrNo(dailyPatVO.getPatCrNo());
				objArrEpisodeVO_p[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
				objArrEpisodeVO_p[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				objArrEpisodeVO_p[i].setIsValid(Config.IS_VALID_ACTIVE);
				objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
				objPatientVO_p.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				
				//HelperMethods.setNullToEmpty(objPatientVO_p.getPatAddress());
				HelperMethods.populate(dailyPatVO, objArrEpisodeVO_p[i]);
				HelperMethods.populate(dailyPatVO, objPatientVO_p);
				
				if (objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					dailyPatVO.setPatAddCityLoc("");
					dailyPatVO.setPatAddCityLocCode("");
					dailyPatVO.setPatAddStateCode("");
					dailyPatVO.setPatAddCountryCode("");
					dailyPatVO.setIsAddressDelhi("");
					dailyPatVO.setPatAddCity("");
					dailyPatVO.setPatAddContactNo("");
					dailyPatVO.setPatAddCountry("");
					dailyPatVO.setPatAddDistrict("");
					dailyPatVO.setPatAddDistrictCode("");
					dailyPatVO.setPatAddHNo("");
					dailyPatVO.setPatAddPIN("");
					dailyPatVO.setPatAddState("");
					dailyPatVO.setPatAddStateCode("");
					dailyPatVO.setPatAddStateCode("");
					dailyPatVO.setPatAddStateName("");
					dailyPatVO.setPatAddStreet("");
					dailyPatVO.setPatAddType("");
					dailyPatVO.setPatAddTypeCode("");
					
					//dailyPatVO = dailyPatDao.create(dailyPatVO, objUserVO_p);
				}
				else
				{
					dailyPatVO.setIsUnknown("0");
					objPatientVO_p.setIsUnknown("0");
				}
				dailyPatVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
				objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
				if(dailyPatVO.getIsMLC()!=null && !dailyPatVO.getIsMLC().equalsIgnoreCase(""))
				{
					if (dailyPatVO.getIsMLC().equals(RegistrationConfig.IS_MLC_TRUE))
					{
						dailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
						dailyPatVO.setMlcFlag(RegistrationConfig.MLC_FLAG_PROVISIONAL);
						dailyPatVO.setIsMLC(RegistrationConfig.IS_MLC_FALSE);
						objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
						objArrEpisodeVO_p[i].setMlcFlag(RegistrationConfig.MLC_FLAG_PROVISIONAL);
					}
				
				}
				else
				{
					dailyPatVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
					dailyPatVO.setMlcFlag(RegistrationConfig.MLC_FLAG_NOMLC);
					objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
					objArrEpisodeVO_p[i].setMlcFlag(RegistrationConfig.MLC_FLAG_NOMLC);
				}

				dailyPatVO.setIsReferred(objPatientVO_p.getIsReferred());
				
				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				dailyPatVO.setEpisodeVisitNo("1");
				
				//dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				//Start:Sheeldarshi:17Oct'14
				//Generation of Unique Health ID Added by Singaravelan on 09-Oct-2014
				String[] dob={"1","Jan","1900"};
				String tempDob="";
				if(objPatientVO_p.getPatDOB()!=null&&!objPatientVO_p.getPatDOB().equals("")){
					dob=objPatientVO_p.getPatDOB().split("-");
				}
				else{
					tempDob=regEssentialDAO.getBirthYear(objPatientVO_p, objUserVO_p);
					if(tempDob!=null){
						dob=tempDob.split("-");
					}
				}
				
				if(!objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)){
					//HealthId hid=new HealthId("Ramesh","B/o Kumar","A S Kumar","Namita Kumar","Male",1984,20,260);
					//HealthId hid=new HealthId(objPatientVO_p.getPatFirstName(),objPatientVO_p.getPatLastName(),objPatientVO_p.getPatGuardianName(),objPatientVO_p.getPatMotherName(),objPatientVO_p.getPatGender(),Integer.parseInt(dob[2]),Integer.parseInt(objPatientVO_p.getPatAddStateCode()),Integer.parseInt(objPatientVO_p.getPatAddDistrictCode()));
					String strPatFatherHusband = "";
					if(objPatientVO_p.getPatGuardianName()!=null && !objPatientVO_p.getPatGuardianName().equals(""))
					{
						strPatFatherHusband = objPatientVO_p.getPatGuardianName();
					}
					else
					{
						strPatFatherHusband = objPatientVO_p.getPatHusbandName();
					}
					HealthId hid=new HealthId(objPatientVO_p.getPatFirstName(),objPatientVO_p.getPatLastName(),strPatFatherHusband,objPatientVO_p.getPatMotherName(),objPatientVO_p.getPatGender(),Integer.parseInt(dob[2]),Integer.parseInt(objPatientVO_p.getPatAddStateCode()),Integer.parseInt(objPatientVO_p.getPatAddDistrictCode()), objPatientVO_p.getPatAddMobileNo());

					GenerateID gid=new GenerateID(hid);
					System.out.println("------HealthId----"+gid.generateSecondaryHealthID()+"------------");	
					objPatientVO_p.setPatSecUHID(gid.generateSecondaryHealthID());
				}
				if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient()!="1"){//To Bypass the Secondary UHID Check to Save As New Patient Added by Singaravelan on 13-Oct-2014
				
				if(objPatientVO_p.getIsDuplicate()!=null && objPatientVO_p.getIsDuplicate().equals("1")){
					// Do Nothing
				}else{	String[] crNoGrpArr=null;
						//crNoArr=regEssentialDAO.checkDuplicateRegistration(objPatientVO_p, objUserVO_p);
						
						//Check Duplicacy using Unique Health ID and return CRNO list having the same UHID Added by Singaravelan on 09-Oct-2014
						if(!objPatientVO_p.getIsUnknown().equals("1"))
						{	//crNoArr=regEssentialDAO.checkDuplicateRegistrationUHID(objPatientVO_p, objUserVO_p);
							crNoGrpArr=regEssentialDAO.checkDuplicateRegistrationAllIdsPossible(objPatientVO_p, objUserVO_p);//

						}	
						//A1--- Aadhaar
						//B2--- Other Id
						//C3--- Demographics
						//D4--- Mobile No
						if(crNoGrpArr!=null){
						for (String subCrNoGrp : crNoGrpArr) {
							if(subCrNoGrp.length()>=1)
							{
								String[] tempArry = subCrNoGrp.split("\\~\\$\\~");//due to symbols used in regular expression creation we used the escape characters to get ~$~
								String[] tempArry2 = (String[])ArrayUtils.addAll(crNoArr, tempArry);
								crNoArr = tempArry2;
							}
							
						}
						if(crNoArr!=null)
						{
							PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
							
							for(i=0;i<crNoArr.length;i++)
							{
								PatientVO vo=new PatientVO();
								vo.setPatCrNo(crNoArr[i].split("&&")[1]);
								vo.setMatchCriteria(crNoArr[i].split("&&")[0]);
								
								switch(crNoArr[i].split("&&")[0]){
								case "A1":
									vo.setMatchCriteriaStr("Aadhaar");
								break;
								case "B2":
									vo.setMatchCriteriaStr("Other Id");
								break;
								case "C3":
									vo.setMatchCriteriaStr("Demographics");
								break;
								case "D4":
									vo.setMatchCriteriaStr("MobileNo");
								break;
								}
								patientVOArr[i]=vo	;
							}
							for(i=0;i<crNoArr.length;i++)
							{
								allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], objUserVO_p));	
							}
							essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
							essentialMap.put("DUP_CR_NO_ARR_EMR",crNoArr);
						}
						if(crNoArr!=null)
						{	
							
							String dupSearchSlno = regEssentialDAO.updateDuplicateSearchLog(objPatientVO_p, objUserVO_p, crNoArr, "1", "", "");//updateDuplicateSearchLog(PatientVO, UserVO, crNoArr, modeVal, dupSrchSno, actionTaken);//mode 1 need not given dupSrchSno and action taken values
							essentialMap.put("duplicate_search_sno_frm_proc", dupSearchSlno);
							essentialMap.put("uhid_for_duplicate_search_frm_proc", objPatientVO_p.getPatSecUHID());
							
							WebUTIL.setMapInSession(essentialMap, objRequest_p,"EmgRegistrationAction");
							throw new HisDuplicateRecordException("Duplicate Registration");
						}
						}
					}
				}		
			
				dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
				
				dailyPatVO.setIsAmbulatory(objPatientVO_p.getIsAmbulatory());//Added by Vasu on 03.May.18
				//HelperMethods.populate(dailyPatVO, objArrEpisodeVO_p[i]);
				//HelperMethods.populatetToNullOrEmpty(dailyPatVO, objPatientVO_p);
				dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_EMG,RegistrationConfig.UNIT_TYPE_CASUALITY, "NREMG");
				
				//generating the Cr Number
				String CrNO=patDao.generateCrNumber(objUserVO_p);
				objPatientVO_p.setPatCrNo(CrNO);
				objPatientVO_p.getPatAddress().setPatCrNo(objPatientVO_p.getPatCrNo());
				objPatientVO_p.setSeatId(objUserVO_p.getSeatId());
				//objPatientVO_p.setDepartmentUnitCode(dailyPatVO.getDepartmentUnitCode());
				//objPatientVO_p.setRoomCode(dailyPatVO.getRoomCode());
				if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient().equals("1"))
				{
				  //By Mukund on 09.09.2016
				  String[] crNoArrFrmSession= (String[])objRequest_p.getSession().getAttribute("DUP_CR_NO_ARR_EMR");
				  String dupSrchsno   = (String)objRequest_p.getSession().getAttribute("duplicate_search_sno_frm_proc");

				  String dupSearchSlno = regEssentialDAO.updateDuplicateSearchLog(objPatientVO_p, objUserVO_p, crNoArrFrmSession, "2", dupSrchsno, "1");//
				  objRequest_p.getSession().setAttribute("duplicate_search_sno_frm_proc", null);
				  //regEssentialDAO.updateDuplicateUHIDLog(objPatientVO_p, objUserVO_p, crNoArrFrmSession, "1");
				}

				/**By Mukund to create Aadhaar UUid using crno and session salt*/
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals(""))//if(!objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)){
				{
					/*
					-- Pre-requisite
					-- Cr No generated and patinet dtl has been saved in HRGT_patient-Dtl table
					-- a UUID has been generated as a Refernce Key and has been inserted in place of Adhaar no in HRGT_Patient_dtl and Id_dtl table
					-- Aadhaar no is not getting inserted in any table.
					-- Calling this method immediately after New Patient Insert, Adhaar no Detail Modi and Patient record delete (not a case right now)
					*/
					patUID = objPatientVO_p.getPatNationalId();
					String strHashedAadhaarUUID = "";
					strHashedAadhaarUUID = objPatientIdDetailDAO.getAadhaarReferenceUUID(CrNO, objPatientVO_p.getPatNationalId(), "1");//hissso.security.SecureHashAlgorithm.SHA1(objPatientVO_p.getPatCrNo()+ strSessionSalt);
					
					System.out.println("\nstrHashedAadhaarUUID: "+strHashedAadhaarUUID);
					//	
					//code for replacing uuid with national id and calling procedure to encrypt and store the aadhaar
					//
					objPatientIdDetailDAO.saveAadhaarDatainADV(CrNO, strHashedAadhaarUUID, objPatientVO_p.getPatNationalId(), objUserVO_p.getHospitalCode(), "1", objHisDAO);
					objPatientVO_p.setPatNationalId(strHashedAadhaarUUID);
					dailyPatVO.setPatNationalId(objPatientVO_p.getPatNationalId());
				}
				/**End on 11.Jan.18*/
			/*  ## 		Modification Log							
		 		##		Modify Date				:12thFeb'15 
		 		##		Reason	(CR/PRS)		:Hour based expiry date calculation in case of emgergency
		 		##		Modify By				:Sheeldarshi */
				Map mp=new HashMap();
				HospitalMstVO objHospitalMstVO=ControllerUTIL.getHospitalVO(objRequest_p);
				mp=EmgRegistrationDATA.getRegistrationFormEssentials_AJAX(objUserVO_p,objHospitalMstVO.getState(),objRequest_p);
				RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
				String strExpiryDate = patDao.getRegExpiryHoursBased(objUserVO_p, objPatientVO_p.getRenewalConfig(), dailyPatVO.getDepartmentUnitCode(),objRegistrationConfigVO);
				//End:Sheeldarshi
				//String strExpiryDate = "21-Feb-2014";
				//objPatientVO_p.setExpiryDate(strExpiryDate);
				
				// Setting Expiry Date(General,Special & Casuality) in PatientVo and so DailyPatientVO
				PatientBOHelper.setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, dailyPatVO, objArrEpisodeVO_p[i], strExpiryDate);
				//if(objPatientVO_p.getRenewalConfig().getStrRenewalType())
				
				dailyPatVO.setPatAge(objPatientVO_p.getPatAge());
				dailyPatVO.setPatDOB(objPatientVO_p.getPatDOB());
				dailyPatVO.setPatAgeUnit(objPatientVO_p.getPatAgeUnit());
				dailyPatVO.setIsActualDob(objPatientVO_p.getIsActualDob());
			//	dailyPatVO.setExpiryDate(strExpiryDate);
				
				// Generating Bill No
				dailyPatVO.setBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
				
				///get age or DOB
				//objPatientVO_p=patDao.getDOBAndAge(objPatientVO_p);
				
				//seting dialypatientvo details
				dailyPatVO.setPatCrNo(CrNO);
				
//				String strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
				//Setting Expiry(Episode Exiry) to make it available in episode while saving
				
				if(dailyPatVO.getMlcFlag()!=null && !dailyPatVO.getMlcFlag().equalsIgnoreCase(""))
				{
					
					if (dailyPatVO.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_PROVISIONAL) || dailyPatVO.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_NOMLC))
					{
						dailyPatVO.setIsMLC(RegistrationConfig.IS_MLC_FALSE);
						objPatientVO_p.setIsMLC(RegistrationConfig.IS_MLC_FALSE);
						
						
				    }
				}
				
				//insert into daily
				dailyPatVO = dailyPatDao.saveDailyPatientDtl(objHisDAO,dailyPatVO, objUserVO_p,"1",RegistrationConfig.DAILYPATIENT_REG_NEW);
				if(dailyPatVO.getPatAgeWithUnit()!=null && dailyPatVO.getPatAgeWithUnit().equalsIgnoreCase("0 Min"))
					objPatientVO_p.setPatAgeWithUnit("0 Day");
				else
					objPatientVO_p.setPatAgeWithUnit(dailyPatVO.getPatAgeWithUnit());
//				 if(strRenewalType!=null && (strRenewalType.equals("3") || strRenewalType.equals("4")))
//						dailyPatVO.setExpiryDate(strExpiryDate);
				
				//objPatientVO_p.setPatAge(dailyPatVO.getPatAge());
				//objPatientVO_p.setPatDOB(dailyPatVO.getPatDOB());
				
				//For the Details Insertion in the Beneficiary Patient Dtl Table 
				if(objPatientVO_p.getPatPrimaryCatGrp()!=null && 
						(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
						objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))){
					
					patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrp();
					//Need to remove this cond once the staff no constraints from the table is removed
					if(!objPatientVO_p.getStaffCardNo().equals("")||!objPatientVO_p.getAgsNo().equals("")){
					if(objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
						objPatientVO_p.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);	
					
					HelperMethods.populate(objBenPatVO, objPatientVO_p);					
					if(!objBenPatVO.getAgsNo().equals(""))
						objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
					else if(!objBenPatVO.getStaffCardNo().equals(""))
						objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
					else if(objBenPatVO.getStaffCardNo().equals("") && !objPatientVO_p.getPatIdNo().equals(""))
						objBenPatVO.setCardNo(objPatientVO_p.getPatIdNo());
					
					if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
						objBenPatVO.setIsDependent("1");
						objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
					}else{
						objBenPatVO.setIsDependent("0");
						objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
					}
					objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
					objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
					
					//By mukund on 23.07.2016
					//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
					//by mukund on 26.07.2016
					objBenPatVO.setPatActualAmount(objPatientVO_p.getPatActualAmount());
					benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
					}
										
				}
				
				//insert into patient
				objPatientVO_p = patDao.savePatientDtl(objHisDAO,objPatientVO_p, objUserVO_p,"1");
				
				/**
				 * Create the json & 
				 * mark entry into minimal table 
				 **/
				String jsonStr= "{\"crno\":\""+objPatientVO_p.getPatCrNo()+"\", \"uid\":\""+patUID+"\", \"uhid\":\""+objPatientVO_p.getPatSecUHID()+"\", \"mobileNo\":\""+objPatientVO_p.getPatAddMobileNo()+"\", "
						+ "\"patCategoryCode\":\""+objPatientVO_p.getPatPrimaryCatCode()+"\", \"name\":\""+objPatientVO_p.getPatFirstName()+" "+objPatientVO_p.getPatMiddleName()+" "+objPatientVO_p.getPatLastName()+"\", \"nameArray\":[\""+objPatientVO_p.getPatFirstName()+"\",\" "+objPatientVO_p.getPatMiddleName()+"\",\" "+objPatientVO_p.getPatLastName()+"\"], "
						+ "\"gender\":\""+objPatientVO_p.getPatGender().substring(0, 1)+"\", \"dob\":\""+objPatientVO_p.getPatDOB()+"\", \"ageInYears\":\""+objPatientVO_p.getPatAgeWithUnit()+"\", \"yob\":\""+Integer.parseInt(dob[2])+"\", \"fatherName\":\""+objPatientVO_p.getPatGuardianName()+"\", "
						+ "\"motherName\":\""+objPatientVO_p.getPatMotherName()+"\", \"spouseName\":\""+(objPatientVO_p.getPatHusbandName().equals("")?"N/A":objPatientVO_p.getPatHusbandName())+"\", \"house\":\""+objPatientVO_p.getPatAddHNo()+"\", \"loc\":\""+objPatientVO_p.getPatAddStreet()+" "+objPatientVO_p.getPatAddCityLoc()+" "+objPatientVO_p.getPatAddCity()+"\", "
						+ "\"vtc\":\""+objPatientVO_p.getPatAddCity()+"\", \"dist\":\""+objPatientVO_p.getPatAddDistrict()+"\", \"distCode\":\""+objPatientVO_p.getPatAddDistrictCode()+"\", "
						+ "\"state\":\""+objPatientVO_p.getPatAddState()+"\", \"stateCode\":\""+objPatientVO_p.getPatAddStateCode()+"\", \"pc\":\""+objPatientVO_p.getPatAddPIN()+"\"}";
				

				QRCodeTest objQRC = new QRCodeTest();
				objQRC.createQRCode(jsonStr, objRequest_p);
				
				patDao.savePatientMinimalDtl(objHisDAO,objPatientVO_p, jsonStr, objUserVO_p, "1");
				
				//Added by Garima on 29 March 2019 for PHRMS HealthRecordIntegration
				objPatientVO_p = patDao.savePatDetailForMyHealthRecordIntegration(objHisDAO,objPatientVO_p, objUserVO_p,"1","1");
				
				//CreateQREntry(objPatientVO_p, jsonStr, objUserVO_p);
				/***/
				
				
				//insert into address detail
				if (objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE))
				{
					addDao.saveAddressDtl(objHisDAO,addressVO, objUserVO_p,"1");
				}//insert into address dteial
				
				String deptName = dailyPatVO.getDepartment();
				if(deptName.contains("(")){
					dailyPatVO.setDepartment(deptName.substring(0,deptName.indexOf("(")));
					dailyPatVO.setDepartmentUnit(deptName.substring(deptName.indexOf("(")+1,deptName.length()-1));
				}
				HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
				
				
				// Create new entry in Episode detail
				if (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					objEpisodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					objEpisodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				else 
					objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
				
				objArrEpisodeVO_p[i].setEntryDate(objPatientVO_p.getSystemDate());
				//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(objHisDAO,objArrEpisodeVO_p[i], objUserVO_p, tx);
				
				objArrEpisodeVO_p[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				
				/*if(dailyPatVO.getIsMLC()!=null && !dailyPatVO.getIsMLC().equalsIgnoreCase("")){
					if (dailyPatVO.getIsMLC().equals(RegistrationConfig.IS_MLC_TRUE))
						objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
					    objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
					    objArrEpisodeVO_p[i].setMlcFlag(RegistrationConfig.MLC_FLAG_PROVISIONAL);
					}
					else {
						objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG);
				objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
				 objArrEpisodeVO_p[i].setMlcFlag(RegistrationConfig.MLC_FLAG_NOMLC);;
					}*/
				objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_NEW);
				objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
				objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
				episodeDAO.saveEpisodeDtl(objHisDAO, objArrEpisodeVO_p[i], objUserVO_p,"1");
				/**Call to save episode details in 'hrgt_seccat_change_dtl'*/
				secondaryCategoryChangesForEveryEpisodeCreated( tx, objUserVO_p, objArrEpisodeVO_p[i], objPatientVO_p);
				/***/	

					
				if (objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					HelperMethods.populate(objEpisodeReferVO, objPatientVO_p);
					HelperMethods.populate(objEpisodeReferVO, objArrEpisodeVO_p[i]);
					HelperMethods.populate(objEpisodeRefDtlVO, objArrEpisodeVO_p[i]);
					
					objEpisodeRefDtlVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
					objEpisodeRefDtlVO.setExternalHospitalCode(objPatientVO_p.getPatRefGnctdHospitalCode());
					objEpisodeRefDtlVO.setExternalHospitalDepartment(objPatientVO_p.getPatRefGnctdHospitalDept());
					objEpisodeRefDtlVO.setExternalHospitalDepartmentUnit(objPatientVO_p.getPatRefGnctdHospitalDeptUnit());
					objEpisodeRefDtlVO.setExternalHospitalDoctorName(objPatientVO_p.getPatRefDoctor());
					objEpisodeRefDtlVO.setExternalHospitalName(objPatientVO_p.getPatRefHospitalName());
					objEpisodeRefDtlVO.setExternalHospitalPatCrNo(objPatientVO_p.getPatRefGnctdHospitalCrno());
					objEpisodeRefDtlVO.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
					objEpisodeRefDtlVO.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
					
					episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO,objEpisodeRefDtlVO, objUserVO_p,"1");

				}
				
				directChargeVO[i]= new DirectChageDetailVO();
				directChargeVO[i].setTariffId(objUserVO_p.getTariffID());
				directChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				directChargeVO[i].setModuleId(objUserVO_p.getModuleId());
				HelperMethods.populate(directChargeVO[i], objArrEpisodeVO_p[i]);
				directChargeVO[i].setPatAmountCollected(objPatientVO_p.getPatAmountCollected());
				directChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
				directChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
				directChargeVO[i].setCreditBillFlag(objPatientVO_p.getCreditBillFlag());
				directChargeVO[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
				directChargeVO[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
				directChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_REGISTRATION);
				directChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
				directChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
				directChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
				directChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
				directChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
				directChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
				directChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
				directChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
				directChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());
				directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
				//directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
				directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);
				System.out.println(" get payment code "+objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);

				//All Cases need to put entries in the Direct Charge DTL table
				if(!(directChargeVO[i].getPaymentMode().equals("1") || directChargeVO[i].getPaymentMode().equals("10") || directChargeVO[i].getPaymentMode().equals("13")))
					directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
				else
					directChargeVO[i].setPaymentModeRefId("");//for cash
				//if (!(directChargeVO[i].getPatAmountCollected().equals("0") ||  directChargeVO[i].getPatAmountCollected().equals("0.00") || directChargeVO[i].getPatAmountCollected().equals("") || directChargeVO[i]
				//		.getPatAmountCollected() == null)) 
				//{
					///registration module billing
					directChargeDtlDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
					////Billing module billing///
					//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
				//}
					
				//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL 
				if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
					HelperMethods.populate(objCreditAvailDtlVO, directChargeVO[i]);
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
					
					objCreditAvailDtlVO.setTariffActualRate(directChargeVO[i].getPatActualAmount());
					objCreditAvailDtlVO.setPaidByClientAmt(directChargeVO[i].getPatActualAmount());
					objCreditAvailDtlVO.setPaidByPatAmt("0");
					objCreditAvailDtlVO.setNetAmount(directChargeVO[i].getPatActualAmount());
					objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());

					//Need to include approved by here
					if(Double.valueOf(objCreditAvailDtlVO.getNetAmount())>0)
					creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO, objCreditAvailDtlVO, objUserVO_p, "1");
				}	
				
				
				HelperMethods.populate(objPatientIdVO, objPatientVO_p);
				
				// For Patient Primary Category Id (like Employee Id etc)
				//if(objPatientVO_p.getIsIdRequired()!=null && objPatientVO_p.getIsIdRequired().equals("2")
						//&& 
				if(objPatientVO_p.getPatIdNo()!=null && !objPatientVO_p.getPatIdNo().equals("") ){
					
					strPatIdErrMsg="Patient Id is not defined";
					if(objPatientVO_p.getPatIdNo().equals("undefined"))
						throw new HisApplicationExecutionException(strPatIdErrMsg);
					
					if(objPatientVO_p.getPatPrimaryCatGrp().equals("1"))
					{
						objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
						objPatientIdVO.setPatDocType("99");
						objPatientIdVO.setPatIsAlternateId("0");
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
					}					
					else
					{
						if(objPatientVO_p.getPatCatDocIsAlternateId()!=null && objPatientVO_p.getPatCatDocIsAlternateId().equals("1"))
						{
							objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
							objPatientIdVO.setPatDocType(objPatientVO_p.getPatCatDocCode());
							objPatientIdVO.setPatIsAlternateId(objPatientVO_p.getPatCatDocIsAlternateId());
							objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "10");
						}
					}
					
					System.out.println("HiddenCatOrRegstrdPopupFlag :"+objPatientVO_p.getHiddenCatOrRegstrdPopupFlag());
					if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag()!=null && !objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("")){
						/*objPatientIdVO.setPatIsAlternateId("0");
						if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("C")){
							objPatientIdVO.setPatDocType("99");
							objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
						}
						else if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A")){
							//System.out.println("AlreadyRegisteredFlag() :"+objPatientVO_p.getAlreadyRegisteredFlag());
							//System.out.println("PrevCrNo :"+objPatientVO_p.getPrevCrNo());
							objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
							if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1")){
								objPatientIdVO.setPatDocType("97");	// For Previous Registered
								objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "6");
							}else if(objPatientVO_p.getAlreadyRegisteredFlag().equals("2")){
								objPatientIdVO.setPatDocType("96");	// For Portal
								objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "5");
							}
						}*/
					}
					
				}
				else if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A"))
				{
					/*objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
					if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1"))
					{
						objPatientIdVO.setPatDocType("97");	// For Previous Registered
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "6");
					}
					else if(objPatientVO_p.getAlreadyRegisteredFlag().equals("2"))
					{
						objPatientIdVO.setPatDocType("96");	// For Portal
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "5");
					}*/
					
				}
				/*else if(objPatientVO_p.getIsIdRequired()!=null && objPatientVO_p.getIsIdRequired().equals("2")){
					objPatientIdVO.setPatDocType("99");
					objPatientIdVO.setPatIsAlternateId("0");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objUserVO_p, "1");
				}*/
				
				// For AlterNate Id
				if(objPatientVO_p.getPatCardNo()!=null && !objPatientVO_p.getPatCardNo().equals("")){
					System.out.println("For ALterNate ID -->> PatCardNo :"+objPatientVO_p.getPatCardNo());
					
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatCardNo());
					objPatientIdVO.setPatIsAlternateId("1");
					objPatientIdVO.setPatDocType(objPatientVO_p.getPatDocType());
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO,objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "1");
				}
				
				// For NationalId/ Aadhar No
				if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals("")){
					System.out.println("For NationalId/ Aadhar No -->> PatCardNo :"+objPatientVO_p.getPatNationalId());
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatNationalId());
					objPatientIdVO.setPatDocType("98");
					objPatientIdVO.setPatIsAlternateId("0");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "3");
				}
				
				
				if ((RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (_patBroughtByDtlVO != null))
				{
					HelperMethods.populate(_patBroughtByDtlVO, objArrEpisodeVO_p[i]);
					_patBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					_patBroughtByDtlVO.setEpisodeVisitNo("1");
					patientBroughtByDtlDAO.create(objHisDAO,_patBroughtByDtlVO, objUserVO_p,"1");
				}
				
				//To Upload the Patient Image in Emergency Registration 
				
				if (objPatientVO_p.getImageFile() != null)
				{
					HelperMethods.populate(patientImageDtlVO, objArrEpisodeVO_p[i]);
					//patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#1");
					//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+"01");
					patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+ objPatientVO_p.getPatCrNo()+"_01");
					//HelperMethods.storeImageInCorrectFileSystem(objPatientVO_p.getImageFile(), objPatientVO_p.getImageFileName(), patientImageDtlVO
					//		.getFileNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					if(null!=objRegistrationConfigVO.getIsImageStoredFTP() && !objRegistrationConfigVO.getIsImageStoredFTP().equals(""))
					{
					if(objRegistrationConfigVO.getIsImageStoredFTP().equals("1"))
					{
					System.out.println("Inside FTP");
					//FileInputStream fileInFTPStream = new FileInputStream(this.uploadedFile);
					File file=new File(objPatientVO_p.getImageFileName());
					FileUtils.writeByteArrayToFile(file, objPatientVO_p.getImageFile());
					FileInputStream fileInFTPStream = new FileInputStream(file);
					//FTPFileTransfer.uploadFile(strProcessId, strFileName, fileInFTPStream, patientImageDtlVO.getPatCrNo());
					FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patientImageDtlVO.getFileNo(), fileInFTPStream, patientImageDtlVO.getPatCrNo());
					System.out.println("FTP Saved");
					}
					
					/*commented by Manisha Gangwar date: 23.3.2018 Saving Images to MongoDb */
					/*else
					{
					System.out.println("Inside MONGO");
					//String docId = patientImageDtlVO.getPatCrNo() + "_"  + patientImageDtlVO.getFileNo();
					String docId = patientImageDtlVO.getFileNo();
				    MongoXmlHandler.getInstance().savePDFFile(docId, objPatientVO_p.getImageFile());
					System.out.println("Mongo Saved");
					}*/
					/*end of Mongodb*/
					
					
					else{
						
					}
					}
					//boolean flag =HisFileControlUtil.isWindowsOS();
					boolean flag =true;
					if(flag)
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					}
					else
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					}
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, objUserVO_p);
				}

			/***********************************************/	
				synchronized (objHisDAO) {
					objHisDAO.fire();
				}
				
				patientImageDtlDAO.saveImageToPostgres(objHisDAO,patientImageDtlVO,objPatientVO_p.getImageFile(),objUserVO_p);
				
				objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
				objArrEpisodeVO_p[i].setExpiryDate(strExpiryDate);
				//objArrEpisodeVO_p[i].setDisclaimer("Test Disclaimer1@Test Disclaimer2@Test Disclaimer1@0@center");
					
			}

		}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException("Error, "+strPatIdErrMsg);
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException("Data Access Error, Contact System Administrator");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
			
		}
		//}
		return objArrEpisodeVO_p; // <<<
	}
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String strCrNo_p,String strPatCatCode_p,String strRenewaltype_p, UserVO objUserVO_p, String visitType_p, String strMode_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] objArrEpisodeVO =
		{};
		
		EpisodeVO[] arrVO =
		{};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			objArrEpisodeVO = episodeDao.getEpisodeDtlByCrNoByDeptByUnit(strCrNo_p,strPatCatCode_p,"","", objUserVO_p,strMode_p,visitType_p);
			EpisodeVO[] objArrTmpEpisodeVO = new EpisodeVO[objArrEpisodeVO.length];
			boolean flagTrue=false;
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < objArrEpisodeVO.length; i++)
			{
				objArrEpisodeVO[i].setRenewalType(strRenewaltype_p);
				
				if(strMode_p!=null && strMode_p.equals("7")){
					if((objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC) ||
							objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC)))
								flagTrue= true;
				}else{
					if(objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
							objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
								flagTrue=true;
				}
				if (flagTrue)
				{
					objArrTmpEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						objArrTmpEpisodeVO[k] = arrVO[k];
					}
					objArrTmpEpisodeVO[len - 1] = objArrEpisodeVO[i];
					arrVO = objArrTmpEpisodeVO;

					objArrTmpEpisodeVO[j] = objArrEpisodeVO[i];
					j++;
					len++;
				}
			}
			
			int m, n;
			for (m = 0, n = 0; m < objArrTmpEpisodeVO.length; m++, n++){
				objArrEpisodeVO[n] = objArrTmpEpisodeVO[m];
			}
			if (arrVO.length == 0){
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
		}//end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}
	
	public EpisodeVO[] searchOldPatientEpisodesByCrNoByDept(String strCrNo_p, String strDeptCode_p, String strRenewaltype_p, UserVO objUserVO_p, String visitType_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] objArrEpisodeVO =	{};

		EpisodeVO[] arrVO =	{};
		try
		{
			System.out.println("PatientBO :: searchOldPatientEpisodesByCrNoByDept()");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			objArrEpisodeVO = episodeDao.getEpisodeDtlByCrNoByDeptByUnit(strCrNo_p, "",strDeptCode_p,"", objUserVO_p,"4",visitType_p);
			EpisodeVO[] objArrTmpEpisodeVO = new EpisodeVO[objArrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < objArrEpisodeVO.length; i++)
			{
				objArrEpisodeVO[i].setRenewalType(strRenewaltype_p);
			
				if (objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
						objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
				{
					objArrTmpEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++){
						objArrTmpEpisodeVO[k] = arrVO[k];
					}
					objArrTmpEpisodeVO[len - 1] = objArrEpisodeVO[i];
					arrVO = objArrTmpEpisodeVO;

					objArrTmpEpisodeVO[j] = objArrEpisodeVO[i];
					j++;
					len++;
				}
			}
			int m, n;
			for (m = 0, n = 0; m < objArrTmpEpisodeVO.length; m++, n++){
				objArrEpisodeVO[n] = objArrTmpEpisodeVO[m];
			}
			if (arrVO.length == 0){
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}

	public EpisodeVO[] searchOldPatientEpisodesByCrNoByUnit(String strCrNo_p, String strDeptUnitCode_p, String strRenewalType_p, UserVO objUserVO_p, String visitType_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] objArrEpisodeVO ={};
		
		EpisodeVO[] arrVO ={};
		try
		{
			System.out.println("PatientBO :: searchOldPatientEpisodesByCrNoByUnit()");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			objArrEpisodeVO = episodeDao.getEpisodeDtlByCrNoByDeptByUnit(strCrNo_p,"","",strDeptUnitCode_p, objUserVO_p,"5",visitType_p);
			EpisodeVO[] objArrTmpEpisodeVO = new EpisodeVO[objArrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < objArrEpisodeVO.length; i++)
			{
				objArrEpisodeVO[i].setRenewalType(strRenewalType_p);
			
				if (objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
						objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
				{
					objArrTmpEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++){
						objArrTmpEpisodeVO[k] = arrVO[k];
					}
					objArrTmpEpisodeVO[len - 1] = objArrEpisodeVO[i];
					arrVO = objArrTmpEpisodeVO;

					
					objArrTmpEpisodeVO[j] = objArrEpisodeVO[i];
					j++;
					len++;
				}
			}
			int m, n;
			for (m = 0, n = 0; m < objArrTmpEpisodeVO.length; m++, n++){
				objArrEpisodeVO[n] = objArrTmpEpisodeVO[m];
			}
			if (arrVO.length == 0){
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			// }
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}
	
	/**
	 * Stamps the visit of a patient when he/she re-visits a department. Saves data in Daily Patient dtl, and Episode dtl
	 * tables.
	 * 
	 * @param objArrEpisodeVO_p[] Provides the departments in which patient has been registered.
	 * @param objPatientVO_p Provides Patient details.
	 * @param objUserVO_p Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] oldDeptVisitStamp(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
			System.out.println("PatientBO :: oldDeptVisitStamp()");
			
			PatientDAO objPatientDAO = new PatientDAO(tx);
			tx.begin();
			objHisDAO = new HisDAO(null, null);
			String strExpiryDate="",strOldExpiry="";
			String strRenewalTypeHospOrEpisode="";
			boolean flagPatSaveStatus = false;
			
			if(objPatientVO_p.getRenewalConfig().getStrRenewalType()!=null && 
					(objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("3") || objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			
			if(objPatientVO_p.getPatStatusCode()!=null && !objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT)){
				flagPatSaveStatus=true;
				objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
			}
			
			/**By Mukund on 13.04.2017 */
			if(objPatientVO_p.getPatPrimaryCatGrp()!=null && objPatientVO_p.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
			{
				Date dt1,dNowP14=null; 
				String dNowP14Str="";
				Calendar cldr = Calendar.getInstance();
				cldr.add(Calendar.DATE, 14);
				SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
				dNowP14Str =dateFormat.format(cldr.getTime());
				dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
				dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
				//compare two dates
				int judge = dt1.compareTo(dNowP14);
				if(judge<=0)// dt1 is earlier than dNowP14
				{
					strExpiryDate = dateFormat.format(dt1);
				}
				else if(judge>0)//dt1 is later than dNowP14
				{
					strExpiryDate = dateFormat.format(dNowP14);
				}
			}
			else
			{
				strExpiryDate = objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
			}
			//strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
			/**End on 13.04.2017 */
			if(objPatientVO_p.getPatNationalId()!= null && !objPatientVO_p.getPatNationalId().equals("")){
				PatientIdDetailDAO objPatientIdDetailDAO=new PatientIdDetailDAO(tx);
				String aadharUUID = objPatientIdDetailDAO.getAadhaarReferenceUUID(objPatientVO_p.getPatCrNo(), objPatientVO_p.getPatNationalId(), "2");
				objPatientVO_p.setPatNationalId(aadharUUID);
			}
			if(objArrEpisodeVO_p!=null && objArrEpisodeVO_p.length>0)
			{
				// if patient is registered from some other hospital and visit another hospital
				// Data is saved to Patient Hospital table including expiry..
				if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1"))
				{
					PatientHospitalDtlVO objPatientHospitalDtlVO = new PatientHospitalDtlVO();
					HelperMethods.populate(objPatientHospitalDtlVO, objPatientVO_p);
					PatientHospitalDtlDAO objPatientHospitalDtlDAO = new PatientHospitalDtlDAO(tx);
					
					// We are assuming Other Hospital's Data is Found. (i.e OtherHospitalDataFound = 1). 
					// Hence we are not taking actions for OtherHospitalDataFound =0
					
					
					if(strRenewalTypeHospOrEpisode.equals("H") && 
							(objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equals("1")))
					{
						objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
						PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
						//Calling PatientDAO
						objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "2");
						
					}else{
						if(flagPatSaveStatus)
							objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "3");
					}
				}
				else
				{
					if(strRenewalTypeHospOrEpisode.equals("H") && objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equalsIgnoreCase("1"))
					{
						PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
						System.out.println("strExpiryDate :"+objPatientVO_p.getExpiryDate());
						//Calling PatientDAO
						objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
					}
				}
			}
			
			//objPatientVO_p.setExpiryDate(strOldExpiry);
			PatientBOHelper.oldDeptVisitStamp(objHisDAO,objArrEpisodeVO_p, objPatientVO_p, objUserVO_p, tx, objEpisodeRefDtlVO_p,strExpiryDate);
			
			for(int i=0;i<objArrEpisodeVO_p.length;i++)
			{
				/*** Query to fetch Disclaimer*********************/
				String usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
					
				objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(usablityFlag,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
			}
			
			synchronized (objHisDAO) {
				 objHisDAO.fire();
			}			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch(HisAppointmentNotAvailableException e){
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());	
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	/* Start : Surabhi
	 * Reason : Changes done for adding Snomed CT functionality
	 * */
	public Map savePatientVisit(PatientVisitSUP objPatVisitSUP_p, EpisodeVO[] objArrOldDeptEpisodeVO_p, PatientVO objPatientVO_p,UserVO objUserVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p,EpisodeVO[] objArrNewDeptEpisodeVO)
	{
		
		System.out.println("----------New Dept VO------------"+objArrNewDeptEpisodeVO+"-----------------------");
		System.out.println("----------Old Dept VO------------"+objArrOldDeptEpisodeVO_p+"-----------------------");

		Map episodeMap=new HashMap();
		if(objArrNewDeptEpisodeVO!=null)//Modified to check both old and new dept visit by singaravelan on 26-09-13
		{
			objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
			if (objArrNewDeptEpisodeVO!=null && objArrOldDeptEpisodeVO_p!=null)
			{
				System.out.println("----------In old and New Dept Vist----------------------");
				objArrNewDeptEpisodeVO=newDepartmentVisitStamp(objArrNewDeptEpisodeVO, objPatientVO_p, objEpisodeRefDtlVO_p, objUserVO_p, null);
				
				objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
				objArrOldDeptEpisodeVO_p=oldDeptVisitStampNew(objPatVisitSUP_p,objArrOldDeptEpisodeVO_p, objPatientVO_p, objUserVO_p, objEpisodeRefDtlVO_p);
				episodeMap.put("NEWEPISODELIST", objArrNewDeptEpisodeVO);
				episodeMap.put("OLDEPISODELIST", objArrOldDeptEpisodeVO_p);
			}
			else
			{
				System.out.println("----------In New Dept Vist----------------------");
				String strRenewalType ="";
				if(objPatientVO_p.getRenewalConfig()!=null)
					strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
				
				if(strRenewalType.equals("1") || strRenewalType.equals("2")){
					if(objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equals("1")){
						objUserVO_p.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
					}else{
						objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
					}
				}
				objArrNewDeptEpisodeVO=newDepartmentVisitStamp(objArrNewDeptEpisodeVO, objPatientVO_p, objEpisodeRefDtlVO_p, objUserVO_p, null);		
				episodeMap.put("NEWEPISODELIST", objArrNewDeptEpisodeVO);
			}
		}
		else
		{
			System.out.println("----------In old Dept Vist----------------------");
			objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
			objArrOldDeptEpisodeVO_p=oldDeptVisitStampNew(objPatVisitSUP_p,objArrOldDeptEpisodeVO_p, objPatientVO_p, objUserVO_p, objEpisodeRefDtlVO_p);		
			episodeMap.put("OLDEPISODELIST", objArrOldDeptEpisodeVO_p);
		}
		
		return episodeMap;
	}
	
	/* Start : Surabhi
	 * Reason : Changes done for adding Snomed CT functionality
	 * */
	public EpisodeVO[] oldDeptVisitStampNew(PatientVisitSUP objPatVisitSUP_p,EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
			System.out.println("PatientBO :: oldDeptVisitStamp()");
			
			PatientDAO objPatientDAO = new PatientDAO(tx);
			tx.begin();
			objHisDAO = new HisDAO(null, null);
			String strExpiryDate="",strOldExpiry="";
			String strRenewalTypeHospOrEpisode="";
			boolean flagPatSaveStatus = false;
			
			if(objPatientVO_p.getRenewalConfig().getStrRenewalType()!=null && 
					(objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("3") || objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			
			if(!objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT)){
				flagPatSaveStatus=true;
				objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
			}
			
			strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
			
			if(objArrEpisodeVO_p!=null && objArrEpisodeVO_p.length>0)
			{
				// if patient is registered from some other hospital and visit another hospital
				// Data is saved to Patient Hospital table including expiry..
				if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1"))
				{
					PatientHospitalDtlVO objPatientHospitalDtlVO = new PatientHospitalDtlVO();
					HelperMethods.populate(objPatientHospitalDtlVO, objPatientVO_p);
					PatientHospitalDtlDAO objPatientHospitalDtlDAO = new PatientHospitalDtlDAO(tx);
					
					// We are assuming Other Hospital's Data is Found. (i.e OtherHospitalDataFound = 1). 
					// Hence we are not taking actions for OtherHospitalDataFound =0
					
					
					if(strRenewalTypeHospOrEpisode.equals("H") && 
							(objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equals("1")))
					{
						objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
						PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
						//Calling PatientDAO
						objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "2");
						
					}else{
						if(flagPatSaveStatus)
							objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "3");
					}
				}
				else
				{
					if(strRenewalTypeHospOrEpisode.equals("H") && objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equalsIgnoreCase("1"))
					{
						PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
						System.out.println("strExpiryDate :"+objPatientVO_p.getExpiryDate());
						//Calling PatientDAO
						objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
					}
				}
			}
			
			//objPatientVO_p.setExpiryDate(strOldExpiry);
			PatientBOHelper.oldDeptVisitStampNew(objPatVisitSUP_p,objHisDAO,objArrEpisodeVO_p, objPatientVO_p, objUserVO_p, tx, objEpisodeRefDtlVO_p,strExpiryDate);
			
			for(int i=0;i<objArrEpisodeVO_p.length;i++)
			{
				/*** Query to fetch Disclaimer*********************/
				String usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
					
				objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(usablityFlag,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
			}
			
			synchronized (objHisDAO) {
				 objHisDAO.fire();
			}			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch(HisAppointmentNotAvailableException e){
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());	
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	// End
	
	/**
	 * Retrieves all the addresses of a patient from Address Dtl Table.
	 * 
	 * @param objPatientVO_p Provides CR No of the patient for which episode details are to be searched.
	 * @param objUserVO_p Provides User details.
	 * @return Array of AddressVO with all the addresses of the patient.
	 */
	public PatientBroughtByDtlVO getPatBroughtByDetials(PatientBroughtByDtlVO objPatientBroughtByDtlVO_p, UserVO objUserVO_p)
	{
		//System.out.println("inside getPatAddressAll() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientBroughtByDtlVO _patBroughtByDtlVO =new PatientBroughtByDtlVO();
		try
		{
			tx.begin();
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			_patBroughtByDtlVO = patientBroughtByDtlDAO.searchPatientBroughtByDetailCrNoNew(objPatientBroughtByDtlVO_p, objUserVO_p);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _patBroughtByDtlVO;
	}
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByCRNoForSearchTile(PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByCRNoForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByUniqueIdForSearchTile(PatientIdVO objPatientIdVO_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientIdDetailDAO patientIdDetailDao=new PatientIdDetailDAO(tx);
						
			lstPatientJsonObjString = patientIdDetailDao.searchPatientDetailByUniqueIdForSearchTile(objPatientIdVO_p, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}

	

	
	/**
	 * Modifies patient+address details. Also more addresses can be added. Updates the record in Patient & Address dtl
	 * table.
	 * 
	 * @param _arrAddressVO[] Provides addresses to be added or modified.
	 * @param objPatientVO_p Provides Patient details.
	 * @param objUserVO_p Provides User details.
	 * @return Number of records updated. 
	 * 
	 * Cretaed By Pragya at 08-Aug-2011
	 */
	public int emgPatDtlModification(PatientVO objPatientVO_p, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			PatientBroughtByDtlVO patientBroughtByDtlVO, UserVO objUserVO_p,PatientIdVO objPatientIdVO_p)
	{
		int x = 0;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		try
		{
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			
			PatientDAO objPatientDAO = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientIdDetailDAO objPatientIdDetailDAO_p = new PatientIdDetailDAO(tx);
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			PatientModificationDAO objPatientModificationDAO = new PatientModificationDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			synchronized (objHisDAO)
	        {
			// Update Patient Detail
				if(objPatientVO_p.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) )
					{
						objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else
					{
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
					}
				}
				else
				{
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else  objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
				}
			
				// Update Address Detail
				if (_arrAddressVO!=null) //x >= 1)
				{
					_arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_TRUE);
					{
						addDao.saveAddressDtl(objHisDAO,_arrAddressVO, objUserVO_p,"2");
					}
				}
				///uPDATING DAILY PATIENT VO
				HelperMethods.populate(dailyPatVO, objPatientVO_p);
				dailyPatVO = dailyPatDao.saveDailyPatientDtl(objHisDAO,dailyPatVO, objUserVO_p,"2","");
				//Updating patient id detail..
				objPatientIdDetailDAO_p.savePatientIdDtl(objHisDAO, objPatientIdVO_p, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "2");
				// For NationalId/ Aadhar No
					objPatientIdVO_p.setPatIdNo(objPatientVO_p.getPatNationalId());
					objPatientIdVO_p.setPatDocType("98");
					objPatientIdVO_p.setPatIsAlternateId("0");
					objPatientIdDetailDAO_p.savePatientIdDtl(objHisDAO, objPatientIdVO_p, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "7");
					
					if(patientBroughtByDtlVO.getIsBroughtBy()!=null&&patientBroughtByDtlVO.getIsBroughtBy().equals(RegistrationConfig.IS_BROUGHT_BY_TRUE)){
					if ((RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (patientBroughtByDtlVO != null))
					{
						patientBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
						patientBroughtByDtlDAO.create(objHisDAO,patientBroughtByDtlVO, objUserVO_p,"2");
					}
					}
					else{
						patientBroughtByDtlDAO.create(objHisDAO,patientBroughtByDtlVO, objUserVO_p,"3");
					}

					
					
				objHisDAO.fire();
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return x;
	}
	
	
	
	
	/**
	 * Modifies patient+address details. Also more addresses can be added. Updates the record in Patient & Address dtl
	 * table.
	 * 
	 * @param _arrAddressVO[] Provides addresses to be added or modified.
	 * @param objPatientVO_p Provides Patient details.
	 * @param objUserVO_p Provides User details.
	 * @return Number of records updated. 
	 * 
	 * For CDAC Noida  
	 */
	public int unknownToKnown(PatientVO objPatientVO_p, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			UnknownVerificationDtlVO UnknownVerificationDtlVO_p,UnknownChangeDtlVO objUnknownChangeDtlVO_p, UserVO objUserVO_p,PatientIdVO objPatientIdVO_p , HttpServletRequest objRequest_p)
	{
		int x = 0;
		String[] crNoArr = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		try
		{
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			
			PatientDAO objPatientDAO = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientIdDetailDAO objPatientIdDetailDAO_p = new PatientIdDetailDAO(tx);
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			RegEssentialDAO regEssentialDAO = new RegEssentialDAO(tx);
			PatientModificationDAO objPatientModificationDAO = new PatientModificationDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			UnknownChangeDtlDAO UnknownChangeDtlDAO_p= new UnknownChangeDtlDAO(tx);
			UnknownVerificationDtlDAO UnknownVerificationDtlDAO_p= new UnknownVerificationDtlDAO(tx);
			
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			
			List allPatientVoList=new ArrayList();
			Map essentialMap = new HashMap();
			synchronized (objHisDAO)
	        {
			// Update Patient Detail
				
				//Changed from unknown to known
				//objPatientVO_p.setIsUnknown("3");
				
				/*Modify Date			: 24thNov'14
				  Reason	(CR/PRS)	: Secondary UHID check incorporation
				  Modify By				: Sheeldarshi */
				
				//Generation of Unique Health ID Added by Singaravelan on 09-Oct-2014
				String[] dob={"1","Jan","1900"};
				String tempDob="";
							
				if(objPatientVO_p.getPatAge()!=null&&!objPatientVO_p.getPatAge().equals("")){
					tempDob=regEssentialDAO.getBirthYear(objPatientVO_p, objUserVO_p);
					if(tempDob!=null){
						dob=tempDob.split("-");
						objPatientVO_p.setPatDOB(tempDob);
						objUnknownChangeDtlVO_p.setPatDOB(tempDob);
					}
				
				}
				else{
					dob=objPatientVO_p.getPatDOB().split("-");
				}
				
				if(!objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)){
					//HealthId hid=new HealthId("Ramesh","B/o Kumar","A S Kumar","Namita Kumar","Male",1984,20,260);
					HealthId hid=new HealthId(objPatientVO_p.getPatFirstName(),objPatientVO_p.getPatLastName(),objPatientVO_p.getPatGuardianName(),objPatientVO_p.getPatMotherName(),objPatientVO_p.getPatGender(),Integer.parseInt(dob[2]),Integer.parseInt(objPatientVO_p.getPatAddStateCode()),Integer.parseInt(objPatientVO_p.getPatAddDistrictCode()),objPatientVO_p.getPatAddMobileNo());
					GenerateID gid=new GenerateID(hid);
					System.out.println("------HealthId----"+gid.generateSecondaryHealthID()+"------------");	
					objPatientVO_p.setPatSecUHID(gid.generateSecondaryHealthID());
				}
				if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient()!="1"){//To Bypass the Secondary UHID Check to Save As New Patient Added by Singaravelan on 13-Oct-2014
				
				if(objPatientVO_p.getIsDuplicate()!=null && objPatientVO_p.getIsDuplicate().equals("1")){
					// Do Nothing
				}else{
						//crNoArr=regEssentialDAO.checkDuplicateRegistration(objPatientVO_p, objUserVO_p);
						
						//Check Duplicacy using Unique Health ID and return CRNO list having the same UHID Added by Singaravelan on 09-Oct-2014
						if(!objPatientVO_p.getIsUnknown().equals("1"))
							crNoArr=regEssentialDAO.checkDuplicateRegistrationUHID(objPatientVO_p, objUserVO_p);
						
						if(crNoArr!=null)
						{
							PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
							
							for(int i=0;i<crNoArr.length;i++)
							{
								PatientVO vo=new PatientVO();
								vo.setPatCrNo(crNoArr[i]);
								patientVOArr[i]=vo	;
							}
							for(int j=0;j<crNoArr.length;j++)
							{
								allPatientVoList.add(searchPatientByCrNo(patientVOArr[j], objUserVO_p));	
							}
							essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
						}
						if(crNoArr!=null)
						{	
							WebUTIL.setMapInSession(essentialMap, objRequest_p,"UnknownToKnownAction");
							throw new HisDuplicateRecordException("Duplicate Registration");
						}
				}
			}
				//End:Sheeldarshi
				objPatientVO_p.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				if(objPatientVO_p.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) )
					{
						objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else
					{
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
					}
				}
				else
				{
					if(objPatientVO_p.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& objPatientVO_p.getPatAge().equals(_patientVOOldData.getPatAge())
							&& objPatientVO_p.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						 objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"2");
					}
					else  objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p,"3");
				}
			
				/*if (_arrAddressVO!=null) //x >= 1)
				{
					//insert into address dteial
					addDao.saveAddressDtl(objHisDAO,_arrAddressVO, objUserVO_p,"1");
				}*/
				
				/*Start : Surabhi
				 * Date : 9th Nov 16
				 * Reason : For modifying the address part*/
				addDao.saveAddressDtl(objHisDAO,_arrAddressVO, objUserVO_p,"1");
				///uPDATING DAILY PATIENT VO
				HelperMethods.populate(dailyPatVO, objPatientVO_p);
				dailyPatVO = dailyPatDao.saveDailyPatientDtl(objHisDAO,dailyPatVO, objUserVO_p,"5","");
				//Updating patient id detail..
				objPatientIdDetailDAO_p.savePatientIdDtl(objHisDAO, objPatientIdVO_p, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "2");
				// For NationalId/ Aadhar No
				objPatientIdVO_p.setPatIdNo(objPatientVO_p.getPatNationalId());
				objPatientIdVO_p.setPatDocType("98");
				objPatientIdVO_p.setPatIsAlternateId("0");
				objPatientIdDetailDAO_p.savePatientIdDtl(objHisDAO, objPatientIdVO_p, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "7");
				//objUnknownChangeDtlVO_p.setIsUnknown("3");
				objUnknownChangeDtlVO_p.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				UnknownChangeDtlDAO_p.saveUnknownChangeDtl(objHisDAO, objUnknownChangeDtlVO_p, objUserVO_p, "1");				
				String arrDoc=UnknownVerificationDtlVO_p.getStrDocumentCode();
				String arrDocId=UnknownVerificationDtlVO_p.getStrDocumentId();

				if(arrDoc!=null){
					String[] arrDocCode=arrDoc.split("&&");

					for(int i=0;i<arrDocCode.length;i++){
						UnknownVerificationDtlVO_p.setStrDocumentCode(arrDocCode[i]);
						UnknownVerificationDtlDAO_p.saveModifictaionDtl(objHisDAO, UnknownVerificationDtlVO_p, objUserVO_p, "1");
					}	
				}
				
				
				// Update Verification Document
				//_verDoc.setIsValid(Config.IS_VALID_ACTIVE);
				//verificationDocUsedDao.create(objHisDAO, _verDoc, objUserVO_p);
				
					/*  ## 		Modification Log							
			 		##		Modify Date				:29thJan'15 
			 		##		Reason	(CR/PRS)		:Image upload integration in patient detail module
			 		##		Modify By				:Sheeldarshi */					
					//To Upload the Patient Image in Emergency Registration 
					if (objPatientVO_p.getImageFile() != null)
					{
						String docName=UnknownVerificationDtlVO_p.getStrDocumentId().split("#")[0].replace("|", "#").split("#")[1];
						String docCode=UnknownVerificationDtlVO_p.getStrDocumentId().split("#")[0].replace("|", "#").split("#")[0];
						String docCount= regEssentialDAO.getVerificationDocCount(objPatientVO_p.getPatCrNo(),docName , objUserVO_p);
						int count=Integer.parseInt(docCount)+1;
						 HelperMethods.populate(patientImageDtlVO, objPatientVO_p);
						//patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#1");
						Map mp=new HashMap();
						HospitalMstVO objHospitalMstVO=ControllerUTIL.getHospitalVO(objRequest_p);
						mp=NewRegistrationDATA.getRegistrationFormEssentials_AJAX(objUserVO_p,objHospitalMstVO.getState(),objRequest_p);
						RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
						
						patientImageDtlVO.setFileType(UnknownVerificationDtlVO_p.getFileType());
						
						if(objRegistrationConfigVO.getIsImageStoredFTP().equals("1"))
						{
						
							//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+"01");
							//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+count+"_01");
							patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+docCode+"_"+count);
							
						
						//HelperMethods.storeImageInCorrectFileSystem(objPatientVO_p.getImageFile(), objPatientVO_p.getImageFileName(), patientImageDtlVO
						//		.getFileNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
						System.out.println("Inside FTP");
						//FileInputStream fileInFTPStream = new FileInputStream(this.uploadedFile);
						File file=new File(objPatientVO_p.getImageFileName());
						FileUtils.writeByteArrayToFile(file, objPatientVO_p.getImageFile());
						FileInputStream fileInFTPStream = new FileInputStream(file);
						//FTPFileTransfer.uploadFile(strProcessId, strFileName, fileInFTPStream, patientImageDtlVO.getPatCrNo());
						FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS, patientImageDtlVO.getFileNo(), fileInFTPStream, patientImageDtlVO.getPatCrNo());
						System.out.println("FTP Saved");
						patientImageDtlDAO.uploadVerificationDoc(objHisDAO,patientImageDtlVO, objUserVO_p,docName,patientImageDtlVO.getFileNo());
						//boolean flag =HisFileControlUtil.isWindowsOS();
						}
						else
						{
							//String docCount= regEssentialDAO.getVerificationDocCount(objPatientVO_p.getPatCrNo(),docName , objUserVO_p);
							//int count=Integer.parseInt(docCount)+1;
							//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+count+"_01");
							patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_DOCUMENTS+"_Doc_"+ objPatientVO_p.getPatCrNo()+"_"+docCode+"_"+count);
							String docId=objPatientVO_p.getPatCrNo()+"_"+count;
							String fileNo = patientImageDtlVO.getFileNo();
						    MongoXmlHandler.getInstance().savePDFFile(fileNo, objPatientVO_p.getImageFile());
						    patientImageDtlDAO.uploadVerificationDoc(objHisDAO,patientImageDtlVO, objUserVO_p,docName,docId);
						}
						boolean flag =true;
						if(flag)
						{
							patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
						}
						else
						{
							patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
						}
						//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
						patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
						patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
						//patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, objUserVO_p);
					}
					//End:Sheeldarshi
				
				
				
				objHisDAO.fire();
			}
		}
		/*Modify Date			: 24thNov'14
		  Reason	(CR/PRS)	: Secondary UHID check incorporation
		  Modify By				: Sheeldarshi */
		catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}
		//End:Sheeldarshi
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return x;
	}
	

	
	
	//Emergency New Dept Visit -- Aadil
	public EpisodeVO[] emgNewDepartmentVisitStamp(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, EpisodeRefDtlVO episodeRefVO, UserVO objUserVO_p,PatientVO _oldPatientVO,HttpServletRequest objRequest_p,PatientBroughtByDtlVO patientBroughtByDtlVO)//Modified by Vasu on 08.May.18
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
			System.out.println("-----------------------------------------");
			System.out.println("PatientBO :: emgNewDepartmentVisitStamp()");
			tx.begin();
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			PatientDAO objPatientDAO = new PatientDAO(tx);
			
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			DirectChageDetailVO[] directChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
			RenewalDetailVO[] renewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(tx);
			RegEssentialDAO objRegEssentialDAO= new RegEssentialDAO(tx);
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
			CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();

			String amount = objArrEpisodeVO_p[0].getPatAmountCollected();
			String strExpiryDate="";	//,strOldExpiryDate="",strNewExpiryDate="";
			boolean flagPatSaveStatus = false;
			String patPrimaryCatGrp="";

			objHisDAO = new HisDAO("Registration","PatientBO");
			
			if(!objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_EMERGENCY)){
				flagPatSaveStatus=true;
				objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_EMERGENCY);
			}
			
			for (int i = 0; i < objArrEpisodeVO_p.length; i++)
			{
				
				DailyPatientVO dailyPatVO = new DailyPatientVO();
				  /*      ## 		Modification Log							
				##		Modify Date				:13thMar'15 
				##		Reason	(CR/PRS)		:Display hours in valid upto field in bill print
				##		Modify By				:Sheeldarshi 
			   */
			//	strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
				Map mp=new HashMap();
				HospitalMstVO objHospitalMstVO=ControllerUTIL.getHospitalVO(objRequest_p);
				//mp=EmgRegistrationDATA.getRegistrationFormEssentials_AJAX(objUserVO_p,objHospitalMstVO.getState(),objRequest_p);
				
				//Registration Configuration Initialzation from session done by Singaravelan on 19-Mar-2015
				RegistrationConfigVO objRegistrationConfigVO=null;
				if(null!=objRequest_p.getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG))
					objRegistrationConfigVO = (RegistrationConfigVO)objRequest_p.getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
				else
					objRegistrationConfigVO=objRegEssentialDAO.getRegistrationConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
				
				strExpiryDate=objPatientDAO.getRegExpiryHoursBased(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode(), objRegistrationConfigVO);
				objArrEpisodeVO_p[i].setExpiryDate(strExpiryDate);
				//End
				// Create DailyPatientVO from patientVO: Populate it
				HelperMethods.populate(dailyPatVO, objPatientVO_p);
				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
				//dailyPatVO.setMlcFlag(objArrEpisodeVO_p[i].getMlcFlag());
				
				// populate the episodeVO with the general details
				PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
				objArrEpisodeVO_p[i].setTariffId(objUserVO_p.getTariffID());
				String referMlcNo="";
				referMlcNo=episodeRefVO.getMlcNo();
				
				
				
				if (objPatientVO_p.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_EMERGENCY_CLINIC))
				{
					objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG); 
					if(RegistrationConfig.IS_MLC_TRUE.equals(objArrEpisodeVO_p[i].getMlcFlag())){
						objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
					}
					else{
						objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
					}
				}
				
				objArrEpisodeVO_p[i].setPatCrNo(dailyPatVO.getPatCrNo());
				objArrEpisodeVO_p[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
				objArrEpisodeVO_p[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				
				objArrEpisodeVO_p[i].setIsValid(Config.IS_VALID_ACTIVE);
				
				// populate this dailyPatient VO with objArrEpisodeVO_p[i]
				HelperMethods.populatetToNullOrEmpty(dailyPatVO, objArrEpisodeVO_p[i]);
				objPatientVO_p.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				dailyPatVO.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
				dailyPatVO.setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
				
				//HelperMethods.populatetToNullOrEmpty(dailyPatVO, objPatientVO_p);
				dailyPatVO.setIsReferred(objPatientVO_p.getIsReferred());
				
				///In case of new dept visit is old is false
				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				
				dailyPatVO.setEpisodeVisitNo("1");
				
				//dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
				dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_EMG,RegistrationConfig.UNIT_TYPE_CASUALITY, "NDEMG");
				
				dailyPatVO.setBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
				
				dailyPatVO.setEpisodeCode(dailyPatDao.generateEpisodeCode(objUserVO_p, dailyPatVO.getPatCrNo(),dailyPatVO.getDepartmentUnitCode()));
				
				// Setting Expiry Date(General,Special & Casuality) in PatientVo and so DailyPatientVO
				PatientBOHelper.setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, dailyPatVO, objArrEpisodeVO_p[i], strExpiryDate);
				
				dailyPatDao.saveDailyPatientDtl(objHisDAO, dailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
				String deptName = dailyPatVO.getDepartment();
				if(deptName.contains("(")){
					dailyPatVO.setDepartment(deptName.substring(0,deptName.indexOf("(")));
					dailyPatVO.setDepartmentUnit(deptName.substring(deptName.indexOf("(")+1,deptName.length()-1));
				}
				HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
				if(dailyPatVO.getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC))
					objArrEpisodeVO_p[i].setMlcNo(referMlcNo);
				
				//Create new entry in Episode detail
				
				if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
					{
						objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					}
					else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
					{
						objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
					}
				}
				else
				{
					objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
					objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
				}

				objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
				////setting visit number//
				
				objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
				objArrEpisodeVO_p[i].setEntryDate(objPatientVO_p.getSystemDate());
				
				System.out.println("----Pat Ren Req--------"+objPatientVO_p.getEmgRenewalRequired()+"------------");
				System.out.println("----Date--------"+objPatientVO_p.getExpiryDate()+"------------");
				System.out.println("objPatientVO_p.getDepartmentUnitCode() :"+objPatientVO_p.getDepartmentUnitCode());
				System.out.println("objArrEpisodeVO_p[i].getDepartmentUnitCode() :"+objArrEpisodeVO_p[i].getDepartmentUnitCode());
				
				if(objPatientVO_p.getRenewalConfig()!=null){
					if(objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("1")|| objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("2")){
						if(objPatientVO_p.getEmgRenewalRequired()!=null && 
								(objPatientVO_p.getEmgRenewalRequired().equals("1") || objPatientVO_p.getEmgRenewalRequired().equals("2")))
						{
							/**By Mukund on 13.04.2017 */
							/*if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
							{
								Date dt1,dNowP14=null; 
								String dNowP14Str="";
								Calendar cldr = Calendar.getInstance();
								cldr.add(Calendar.DATE, 14);
								SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
								dNowP14Str =dateFormat.format(cldr.getTime());
								dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
								dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
								//compare two dates
								int judge = dt1.compareTo(dNowP14);
								if(judge<=0)// dt1 is earlier than dNowP14
								{
									strExpiryDate = dateFormat.format(dt1);
								}
								else if(judge>0)//dt1 is later than dNowP14
								{
									strExpiryDate = dateFormat.format(dNowP14);
								}
							}
							else
							{
								strExpiryDate = objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
							}*/
							strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
							/**End on 13.04.2017 */
							objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
							PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
							//Calling PatientDAO
							objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
						}else{
							if(flagPatSaveStatus)
								objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "6");
						}
					}
				}
				/*To set the qrcode for printing over the OPD card*/
				String jsonStr = objPatientVO_p.getStrQRCode();
				QRCodeTest objQRC = new QRCodeTest();
				if(jsonStr!=null && !(jsonStr.equals("")))
					objQRC.createQRCode(jsonStr, objRequest_p);
				else
					objQRC.createQRCode(objPatientVO_p.getPatCrNo(), objRequest_p);
				/**/
				//Calling EpisodeDAO
				objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
				objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
				objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
				episodeDao.saveEpisodeDtl(objHisDAO, objArrEpisodeVO_p[i], objUserVO_p, "1");
				/**Call to save episode details in 'hrgt_seccat_change_dtl'*/
				secondaryCategoryChangesForEveryEpisodeCreated( tx, objUserVO_p, objArrEpisodeVO_p[i], objPatientVO_p);
				/***/

				if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					
					String refEpisode = episodeRefVO.getEpisodeCode();
					String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
					String serialNo = episodeRefVO.getSerialNo();
					HelperMethods.populate(episodeRefVO, objArrEpisodeVO_p[i]);
					episodeRefVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());

					if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
					{
						episodeRefVO.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
						episodeRefVO.setEpisodeVisitNo("1");

					}
					if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
					{
						episodeRefVO.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
						episodeRefVO.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
						episodeRefVO.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
						episodeRefVO.setToEpisodeVisitNo("1");
						episodeRefVO.setEpisodeCode(refEpisode);
						episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
						episodeRefVO.setExternalHospitalCode("");
						episodeRefVO.setSerialNo(serialNo);
					}

					//Calling EpisodeRefDtlDAO
					if (objPatientVO_p.getIsPatReferByList() != null
							&& objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						
						episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO, episodeRefVO, objUserVO_p,"2");	// for updating Acceptance Date
					}
					else
					{
						if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals("1"))
							episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO, episodeRefVO, objUserVO_p, "1");
					}
					
				}

				System.out.println("----Amount in PatientBO-----"+amount+"-----------");
				//if (amount != null && !amount.equals("") && !amount.equals("0") && !amount.equals(RegistrationConfig.PAT_CAT_FREE_FEES))
				if(amount!=null)
				{
					directChargeVO[i]= new DirectChageDetailVO();
					directChargeVO[i].setTariffId(objUserVO_p.getTariffID());
					directChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
					directChargeVO[i].setModuleId(objUserVO_p.getModuleId());
					HelperMethods.populate(directChargeVO[i], objArrEpisodeVO_p[i]);
					directChargeVO[i].setPatAmountCollected(objPatientVO_p.getPatAmountCollected());
					directChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
					directChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
					directChargeVO[i].setCreditBillFlag(objPatientVO_p.getCreditBillFlag());
					directChargeVO[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
					directChargeVO[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
					directChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
												
					directChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
					directChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
					directChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
					directChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
					directChargeVO[i].setClientName(objPatientVO_p.getClientName());
					directChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
					directChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
					directChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
					directChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
					directChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());
					//directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
					//directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
					directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);
					System.out.println(" get payment code "+objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);

					
					
					if (!(directChargeVO[i].getPatAmountCollected().equals("0") 
							&& !(directChargeVO[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
							&& !directChargeVO[i].getPatAmountCollected().equals("") 
							&& directChargeVO[i].getPatAmountCollected() != null)) 
						{
						//By Warish for payment mode refid save for Aug'18
						if(!(directChargeVO[i].getPaymentMode().equals("1") || directChargeVO[i].getPaymentMode().equals("10") || directChargeVO[i].getPaymentMode().equals("13")))
					         directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
				        else
					         directChargeVO[i].setPaymentModeRefId("");//for cash
						directChargeDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
					}
					
					if((objArrEpisodeVO_p[i].getEmgRenewalRequired()!=null && objArrEpisodeVO_p[i].getEmgRenewalRequired().equals("1"))|| 
							(objPatientVO_p.getEmgRenewalRequired()!=null && objPatientVO_p.getEmgRenewalRequired().equalsIgnoreCase("1")))
					{
						renewDetailVO[i] = new RenewalDetailVO();
						renewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
						renewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
						renewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
						renewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
						renewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
						renewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
						renewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
						renewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
						renewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
						renewDetailVO[i].setNewExpiryDate(strExpiryDate);
						renewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

						renewDetailDAO.saveRenewalDtl(objHisDAO,renewDetailVO[i], objUserVO_p,"1");
					
					}
					
					//This code commented by Garima as in AIIMS Letter details will be added by separate process for CM Relief Fund Category--STart
					
					//For the Details Insertion in the Beneficiary Patient Dtl Table 
					/*if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
							(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) 
									//||objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
							)
							){
						
						patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
						//Need to remove this cond once the staff no constraints from the table is removed
						//if((objPatientVO_p.getStaffCardNo()!=null&&!objPatientVO_p.getStaffCardNo().equals(""))||
								//(objPatientVO_p.getAgsNo()!=null&&!objPatientVO_p.getAgsNo().equals(""))){
						if(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
						{
							objPatientVO_p.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);
							objPatientVO_p.setClientName(RegistrationConfig.Client_Name_Aarogya_Cat);
							
							}
						HelperMethods.populate(objBenPatVO, objPatientVO_p);
						HelperMethods.setNullToEmpty(objBenPatVO);
						
						if(!objBenPatVO.getAgsNo().equals(""))
							objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
						else if(!objBenPatVO.getStaffCardNo().equals(""))
							objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
						else
							objBenPatVO.setCardNo("NA");
						
						if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
							objBenPatVO.setIsDependent("1");
							objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
						}else{
							objBenPatVO.setIsDependent("0");
							objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
						}
						objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
						objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
						objBenPatVO.setClientName(objPatientVO_p.getClientName());
						
						//commented by Garima on 19th July 2016 to save patient credit details--start
						//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
						//by mukund on 26.07.2016
						objBenPatVO.setPatActualAmount(objPatientVO_p.getPatActualAmount());
						benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
						//}
											
					}
					*/
					//This code commented by Garima as in AIIMS Letter details will be added by separate process for CM Relief Fund Category--End
					
					


					//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL 
					if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
						HelperMethods.populate(objCreditAvailDtlVO, directChargeVO[i]);
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
						
						objCreditAvailDtlVO.setTariffActualRate(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setPaidByClientAmt(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setPaidByPatAmt("0");
						objCreditAvailDtlVO.setNetAmount(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());

						//Need to include approved by here
						if(Double.valueOf(objCreditAvailDtlVO.getNetAmount())>0)
						creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO, objCreditAvailDtlVO, objUserVO_p, "1");
					}
				
				}
			
				
				//****** query to fetch disclaimer for printing ************//*
					objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, objArrEpisodeVO_p[i].getDepartmentUnitCode(), objUserVO_p));
					
				//**************************************************//*	
					/*Added by Vasu on 08.May.18*/
					if ((RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (patientBroughtByDtlVO != null))
					{
						HelperMethods.populate(patientBroughtByDtlVO, objArrEpisodeVO_p[i]);
						patientBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
						patientBroughtByDtlVO.setEpisodeVisitNo("1");
						patientBroughtByDtlDAO.create(objHisDAO,patientBroughtByDtlVO, objUserVO_p,"1");
					}
					
					//End Vasu
			
			
		}
			synchronized (objHisDAO) 
			{
			   objHisDAO.fire();
			}		
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
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
			System.out.println("Close the transaction...");
			if (objHisDAO != null) {
				objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	public EpisodeVO[] emgOldDeptVisitStamp(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p,PatientBroughtByDtlVO patientBroughtByDtlVO)//Modified by Vasu on 08.May.18
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
			System.out.println("PatientBO :: emgOldDeptVisitStamp()");
			
			PatientDAO objPatientDAO = new PatientDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			tx.begin();
			objHisDAO = new HisDAO(null, null);
			String strExpiryDate="",strOldExpiry="";
			String strRenewalTypeHospOrEpisode="";
			if(objPatientVO_p.getRenewalConfig().getStrRenewalType()!=null && 
					(objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("3") || objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			
			/**By Mukund on 13.04.2017 */
			/*if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
			{
				Date dt1,dNowP14=null; 
				String dNowP14Str="";
				Calendar cldr = Calendar.getInstance();
				cldr.add(Calendar.DATE, 14);
				SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
				dNowP14Str =dateFormat.format(cldr.getTime());
				dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
				//to by pass the error in aiims patna
				if(objPatientVO_p.getCardvalidityDate()!=null && !objPatientVO_p.getCardvalidityDate().equals(""))
					dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
				else
					dt1= dNowP14;
					//compare two dates
				int judge = dt1.compareTo(dNowP14);
				if(judge<=0)// dt1 is earlier than dNowP14
				{
					strExpiryDate = dateFormat.format(dt1);
				}
				else if(judge>0)//dt1 is later than dNowP14
				{
					strExpiryDate = dateFormat.format(dNowP14);
				}
			}
			else
			{
				strExpiryDate = objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
			}*/
			strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
			/**End on 13.04.2017 */
			if(objArrEpisodeVO_p!=null && objArrEpisodeVO_p.length>0)
			{
				System.out.println("----Pat Ren Req in PatientBO --------"+objPatientVO_p.getEmgRenewalRequired()+"------------");
				strOldExpiry=objPatientVO_p.getExpiryDate();
				if(strRenewalTypeHospOrEpisode.equals("H") && objPatientVO_p.getEmgRenewalRequired()!=null && objPatientVO_p.getEmgRenewalRequired().equalsIgnoreCase("1"))
				{
					PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
					System.out.println("strExpiryDate :"+objPatientVO_p.getExpiryDate());
					//Calling PatientDAO
					objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
				}
			}
			// Setting Expiry in PatientVO so that required ExpiryDate of EpisodeVo can be resetted in PatientBOHelper.oldDeptVisitStamp()
			//objPatientVO_p.setExpiryDate(strOldExpiry);
			PatientBOHelper.emgOldDeptVisitStamp(objHisDAO,objArrEpisodeVO_p, objPatientVO_p, objUserVO_p, tx, objEpisodeRefDtlVO_p,strExpiryDate);
			
			for(int i=0;i<objArrEpisodeVO_p.length;i++)
			{
				/*** Query to fetch Disclaimer*********************/
				String usablityFlag= RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY;
					
				objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(usablityFlag,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
				/*Added by Vasu on 08.May.18*/
				if ((RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (patientBroughtByDtlVO != null))
				{
					HelperMethods.populate(patientBroughtByDtlVO, objArrEpisodeVO_p[i]);
					patientBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					String maxVisit = patientBroughtByDtlDAO.getPatientMaxVisitNum(objHisDAO,patientBroughtByDtlVO, objUserVO_p,"1");
					if (maxVisit == null || maxVisit.equals(""))
					{
						patientBroughtByDtlVO.setEpisodeVisitNo("1");
					}
					else
					{
						int max = Integer.parseInt(maxVisit);
						max = max+1;
						maxVisit = Integer.toString(max);
					patientBroughtByDtlVO.setEpisodeVisitNo(maxVisit);
					}
					patientBroughtByDtlDAO.create(objHisDAO,patientBroughtByDtlVO, objUserVO_p,"1");
				}
				
				//End Vasu
			}
			
			synchronized (objHisDAO) {
				 objHisDAO.fire();
			}			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch(HisAppointmentNotAvailableException e){
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());	
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	public Map saveEmgPatientVisit(EpisodeVO[] objArrOldDeptEpisodeVO_p, PatientVO objPatientVO_p,UserVO objUserVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p,EpisodeVO[] objArrNewDeptEpisodeVO,HttpServletRequest objRequest_p,PatientBroughtByDtlVO patientBroughtByDtlVO)//Modified by Vasu on 08.May.18
	{
		System.out.println("PatientBO :: saveEmgPatientVisit()");
		System.out.println("----------New Dept VO------------"+objArrNewDeptEpisodeVO+"-----------------------");
		System.out.println("----------Old Dept VO------------"+objArrOldDeptEpisodeVO_p+"-----------------------");

		Map episodeMap=new HashMap();
		if(objArrNewDeptEpisodeVO!=null)//Modified to check both old and new dept visit by singaravelan on 26-09-13
		{
			objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
			if (objArrNewDeptEpisodeVO!=null && objArrOldDeptEpisodeVO_p!=null)
			{
				System.out.println("----------In Emg old and New Dept Vist----------------------");
				objArrNewDeptEpisodeVO=emgNewDepartmentVisitStamp(objArrNewDeptEpisodeVO, objPatientVO_p, objEpisodeRefDtlVO_p, objUserVO_p, null, objRequest_p,patientBroughtByDtlVO);//Modified by Vasu on 08.May.18
				
				objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
				objArrOldDeptEpisodeVO_p=emgOldDeptVisitStamp(objArrOldDeptEpisodeVO_p, objPatientVO_p, objUserVO_p, objEpisodeRefDtlVO_p,patientBroughtByDtlVO);//Modified by Vasu on 08.May.18
				episodeMap.put("EMGNEWEPISODELIST", objArrNewDeptEpisodeVO);
				episodeMap.put("EMGOLDEPISODELIST", objArrOldDeptEpisodeVO_p);
			}
			else
			{
				System.out.println("----------In Emg New Dept Vist----------------------");
				String strRenewalType ="";
				if(objPatientVO_p.getRenewalConfig()!=null)
					strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
				
				if(strRenewalType.equals("1") || strRenewalType.equals("2")){
					if(objPatientVO_p.getEmgRenewalRequired()!=null && objPatientVO_p.getEmgRenewalRequired().equals("1")){
						objUserVO_p.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
					}else{
						objUserVO_p.setTariffID(RegistrationConfig.EMERGENCY_NEW_DEPT_VISIT_TARIFF_ID);
					}
				}
				objArrNewDeptEpisodeVO=emgNewDepartmentVisitStamp(objArrNewDeptEpisodeVO, objPatientVO_p, objEpisodeRefDtlVO_p, objUserVO_p, null,objRequest_p,patientBroughtByDtlVO);	//Modified by Vasu on 08.May.18 	
				episodeMap.put("EMGNEWEPISODELIST", objArrNewDeptEpisodeVO);
			}
		}
		else
		{
			System.out.println("----------In old Dept Vist----------------------");
			objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
			objArrOldDeptEpisodeVO_p=emgOldDeptVisitStamp(objArrOldDeptEpisodeVO_p, objPatientVO_p, objUserVO_p, objEpisodeRefDtlVO_p,patientBroughtByDtlVO);//Modified by Vasu on 08.May.18	
			episodeMap.put("EMGOLDEPISODELIST", objArrOldDeptEpisodeVO_p);
		}
		
		return episodeMap;
	}
	
	
	
	/**
	 * Retrieves patient details on the basis of CR No and visit type from the Patient Dtl Table. Provides age of the patient
	 * according to the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param objPatientVO_p Provides CR No to be searched.
	 * @param objUserVO_p Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	public PatientVO searchPatientByCrNoDaily(PatientVO objPatientVO_p, UserVO objUserVO_p, String _visitType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO DailyPatientDAO_p = new DailyPatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			PatientImageDtlVO patImageDtlVO=new PatientImageDtlVO();
			PatientImageDtlDAO patImageDtlDao=new PatientImageDtlDAO(tx);

			objPatientVO_p = DailyPatientDAO_p.retrieveByCrNoDailyPatient(objPatientVO_p, objUserVO_p, _visitType);
			String fname = "(Unknown)" + objPatientVO_p.getPatFirstName();

			if (objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) objPatientVO_p.setPatFirstName(fname);
			if (objPatientVO_p.getPatAge() != null)
			{
				String patAgeWithUnit = objPatientVO_p.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				objPatientVO_p.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) objPatientVO_p.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) objPatientVO_p.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) objPatientVO_p.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) objPatientVO_p.setPatAgeUnit("Wk");
			}
			
			//To fetch the Uploaded Image
			if (objPatientVO_p.getIsImageUploaded() != null && objPatientVO_p.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				patImageDtlVO=patImageDtlDao.getPatientDefaultImageByCrNo(objPatientVO_p.getPatCrNo(),objUserVO_p);
				//boolean flag =HisFileControlUtil.isWindowsOS();
				boolean flag =true;
				if(!flag)
				{
					patImageDtlVO.setFilePath("/root"+patImageDtlVO.getFilePath()); 
				}

				//objPatientVO_p.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
				//objPatientVO_p.setImageFileName(patImageDtlVO.getFilePath() + patImageDtlVO.getFileNo());
				objPatientVO_p.setImageFileName(patImageDtlVO.getFileNo());
				
				//Added by manisha gangwar date: 26.3.2018 fetching image from postgres
				byte[] getDoc= patImageDtlDao.latestFetchImagePostgres(patImageDtlVO.getFileNo());
				 objPatientVO_p.setImageFile(getDoc);
			}

		
			if (objPatientVO_p.getPatCatCode() == null || objPatientVO_p.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					"Current Patient Category is invalid please change the Patient Category first");
		
			/**to decrypt the aadhaar number*/
			if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals(""))
			{
				String strPatAadhaarNo =NewRegistrationDATA.getAadhaarDecrypted(objPatientVO_p.getPatNationalId(), objPatientVO_p.getPatCrNo());
				objPatientVO_p.setPatNationalId(strPatAadhaarNo.split("#@#")[0]);
				System.out.println(strPatAadhaarNo.split("#@#")[0]+"\n"+strPatAadhaarNo.split("#@#")[1]+"\n"+strPatAadhaarNo.split("#@#")[2]);
			}
			/***/
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return objPatientVO_p;
	}
	
	/**
	 * To List the Category Except the Present Category in the Primary Category Change Combo.
	 * 
	 * @param patCat Provides current category.
	 * @param objUserVO_p Provides User details.
	 * @return List of Category.
	 */
	
	@SuppressWarnings("rawtypes")
	public List getPrimaryCatListExceptPatientCat(String patCat, UserVO userVO)
	{
		List patCatList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDAO patientDao = new PatientDAO(tx);

		try
		{
			tx.begin();
			patCatList = patientDao.getPrimaryCatListExceptPatientCat(patCat, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException("Record Not found");
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patCatList;
	}
	//Start:Sheeldarshi
	public List getDistrictBasedOnState(UserVO _userVO, String strStateCode_p,String strCountryCode) 
	{
		
		List lstDistrict = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstDistrict = objEssentialDAO.getDistrictList(_userVO,strStateCode_p);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstDistrict;
	}
	
	public List getRelationsList(UserVO _userVO)
	{
		List lstRelation = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstRelation = objEssentialDAO.getRelationsList(_userVO);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstRelation;
	}
	public List getCompanyList(UserVO _userVO)
	{
		List lstCompany = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstCompany = objEssentialDAO.getClientList(_userVO);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstCompany;
	}
	
	//End
	/**
	 * Changes primary category of a patient. Updates the record in Patient Dtl and Episode Dtl table.Also, in case of credit and aarogyashree category insert record in Benficiary detail table
	 * 
	 * @param _primCatChangeVO Provides change of category details.
	 * @param objUserVO_p Provides User details.
	 * @return <code>true</code> if records are updated successfully otherwise <code>false</code>.
	 */
	public boolean changePrimaryCategory(PrimaryCategoryChangeVO _primCatChangeVO, UserVO objUserVO_p, VerificationDocumentVO _veriDocVO,EmpMasterVO _empMstVO)
	{
		System.out.println("inside changePrimaryCategory(PrimaryCategoryChangeVO _primCatChangeVO, UserVO objUserVO_p) of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String nextSerialNo = "";
		int x = 0, y = 1;
		PatientVO _patientVO = new PatientVO();
		DailyPatientVO dailyPatVo = new DailyPatientVO();
		EpisodeVO episodeVO=new EpisodeVO();
		PatientIdVO objPatientIdVO = new PatientIdVO();
		
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			PrimaryCategoryChangeDAO primCatChangeDAO = new PrimaryCategoryChangeDAO(tx);
			PatientDAO patDtlDAO = new PatientDAO(tx);
			PatientIdDetailDAO patIdDAO=new PatientIdDetailDAO(tx);
			BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
			CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			//////////Insert into hrgt_pricat_change_dtl

			String newValidUpto=primCatChangeDAO.generateNewValidUpto(_primCatChangeVO,objUserVO_p);
			primCatChangeDAO.create(hisDAO, _primCatChangeVO, objUserVO_p, "1");

			//////////update hrgt_patient_detail

			_patientVO.setPatCrNo(_primCatChangeVO.getPatCrNo());
			_patientVO.setPatPrimaryCatCode(_primCatChangeVO.getPatNewPrimaryCatCode());
			_patientVO.setPatCatShortName(_primCatChangeVO.getPatNewPrimaryCat());
			if(_primCatChangeVO.getPatIdNo()!=null && !_primCatChangeVO.getPatIdNo().equals(""))
				_patientVO.setPatIdNo(_primCatChangeVO.getPatIdNo());
			if(_primCatChangeVO.getMemSlNo()!=null && !_primCatChangeVO.getMemSlNo().equals("") && !_primCatChangeVO.getMemSlNo().equals("undefined"))
			{
				_patientVO.setMemSlNo(_primCatChangeVO.getMemSlNo());
			}
			else _patientVO.setMemSlNo("0");
			_patientVO.setStrHospCode(objUserVO_p.getHospitalCode());
			
			_patientVO.setApprovedBy(_primCatChangeVO.getApprovedBy());
			//primCatChangeDAO.changePatientPrimaryCat(_patientVO, objUserVO_p, _patIdNo);
		
			String	patPrimaryCatGrp=_primCatChangeVO.getPatPrimaryCatGrp();
			///////Insert into hrgt_patient_Id Detail if Pat Id is not null
			if(_primCatChangeVO.getPatIdNo()!=null && !_primCatChangeVO.getPatIdNo().equals("") && _primCatChangeVO.getIsAlternateId().equals("1"))
			{
				HelperMethods.populate(objPatientIdVO, _primCatChangeVO);
				objPatientIdVO.setPatIsAlternateId("1");
				if(_primCatChangeVO.getPatPrimaryCatGrp()!=null && 
						_primCatChangeVO.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_STAFF)){
					objPatientIdVO.setPatDocType("99");
				}
				else
				{
				
				objPatientIdVO.setPatDocType(_primCatChangeVO.getVerificationDocumentId());
				}
				patIdDAO.savePatientIdDtl(hisDAO, objPatientIdVO, _primCatChangeVO.getPatNewPrimaryCatCode(), null, objUserVO_p, "8");
			}
			if(_primCatChangeVO.getIsIdRequired().equals(RegistrationConfig.PAT_CAT_ID_REQUIRED_NO))
			{
				HelperMethods.populate(objPatientIdVO, _primCatChangeVO);
				_patientVO.setPatIdNo("");
				objPatientIdVO.setPatIdNo(_primCatChangeVO.getPrevPatIdNo());
				objPatientIdVO.setPatDocType(_primCatChangeVO.getPrevVerificationDocument());
				patIdDAO.savePatientIdDtl(hisDAO, objPatientIdVO, _primCatChangeVO.getPatNewPrimaryCatCode(), null, objUserVO_p, "9");
			}
			/*  ## 		Modification Log							
	 		    ##		Modify Date				:10thNov'15 
	 		    ##		Reason	(CR/PRS)		:Insertion in Beneficiary Detail Table for Aarogya Shree and Credit Categories
	 		    ##		Modify By				:Sheeldarshi 
			 */
			
			
			
			objBenPatVO.setPatPrimaryCatCode(_primCatChangeVO.getPatNewPrimaryCatCode());
			//For the Details Insertion in the Beneficiary Patient Dtl Table 
			/*if(_primCatChangeVO.getPatPrimaryCatGrp()!=null && 
					(_primCatChangeVO.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
					_primCatChangeVO.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))){*/
			
			if(_primCatChangeVO.getPatPrimaryCatGrp()!=null && 
					_primCatChangeVO.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)){
				
					
				//Need to remove this cond once the staff no constraints from the table is removed
				//if(!_primCatChangeVO.getStaffCardNo().equals("")||!_primCatChangeVO.getAgsNo().equals("")){
				//if(_primCatChangeVO.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))//arogya
				//Commented By Sandip On 30.05.2016
				//_primCatChangeVO.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);
				_primCatChangeVO.setClientCode("-1");
				_primCatChangeVO.setAgsCreditLimit(_primCatChangeVO.getAgsCreditLimit());
				_primCatChangeVO.setClientName(RegistrationConfig.Client_Name_Aarogya_Cat);
				objBenPatVO.setCreditLimit(_primCatChangeVO.getAgsCreditLimit());
				
				
				HelperMethods.populate(objBenPatVO, _primCatChangeVO);					
				if(!objBenPatVO.getAgsNo().equals(""))
					objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
				else if(!objBenPatVO.getStaffCardNo().equals(""))
					objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
				else if(objBenPatVO.getStaffCardNo().equals("") && !_primCatChangeVO.getPatIdNo().equals(""))
					objBenPatVO.setCardNo(_primCatChangeVO.getPatIdNo());
				
				/*if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
					objBenPatVO.setIsDependent("1");
					objBenPatVO.setDependentName(_primCatChangeVO.getPatFirstName());
				}else{
					objBenPatVO.setIsDependent("0");
					objBenPatVO.setDependentName(_primCatChangeVO.getStaffCardName());
				}
				objBenPatVO.setDependentRelationCode(_primCatChangeVO.getRelationWithStaff());
				objBenPatVO.setDependentRelation(_primCatChangeVO.getRelationNameWithStaff());*/
				
				//benPatientDAO.savePatientBeneficiaryDtl(hisDAO, objBenPatVO, objUserVO_p, "1");
				//}
				benPatientDAO.savePatientCreditDtl(hisDAO, objBenPatVO, objUserVO_p, "1");
				
			}
					
									
						
			//End
			patDtlDAO.savePatientDtl(hisDAO, _patientVO, objUserVO_p, "5");
			
			synchronized(hisDAO){
				hisDAO.fire();
			}
			
			System.out.println("after primCatChangeDAO.create(_primCatChangeVO, objUserVO_p)");
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());y=0;			
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());y=0;
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());y=0;
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());y=0;
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			System.out.println("y = " + y);
			tx.close();
		}
		if (y >= 1) return true;
		else return false;
	}
	
	public EpisodeVO[] spclNewDepartmentVisitStamp(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, EpisodeRefDtlVO episodeRefVO, UserVO objUserVO_p,PatientVO _oldPatientVO )
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
			tx.begin();
	
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			PatientDAO objPatientDAO = new PatientDAO(tx);
			
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			DirectChageDetailVO[] directChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
			RenewalDetailVO[] renewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(tx);
			
			BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
			CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
			BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
			CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
			
			String amount = objArrEpisodeVO_p[0].getPatAmountCollected();
			String strExpiryDate="";	//,strOldExpiryDate="",strNewExpiryDate="";
			boolean flagPatSaveStatus = false;
			String patPrimaryCatGrp="";

			System.out.println("------------------------------------------");
			System.out.println("PatientBO :: spclNewDepartmentVisitStamp()");
			objHisDAO = new HisDAO("Registration","PatientBO");
			
			if(!objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT)){
				flagPatSaveStatus=true;
				objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
			}
			
				// EpisodeRefDtlVO objEpisodeRefDtlVO_p=new EpisodeRefDtlVO();

				////updating patient detail if department wise mandatory fields are captured
				
				for (int i = 0; i < objArrEpisodeVO_p.length; i++)
				{
					
					DailyPatientVO dailyPatVO = new DailyPatientVO();
					
					/**By Mukund on 13.04.2017 */
					/*if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
					{
						Date dt1,dNowP14=null; 
						String dNowP14Str="";
						Calendar cldr = Calendar.getInstance();
						cldr.add(Calendar.DATE, 14);
						SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
						dNowP14Str =dateFormat.format(cldr.getTime());
						dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
						dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
						//compare two dates
						int judge = dt1.compareTo(dNowP14);
						if(judge<=0)// dt1 is earlier than dNowP14
						{
							strExpiryDate = dateFormat.format(dt1);
						}
						else if(judge>0)//dt1 is later than dNowP14
						{
							strExpiryDate = dateFormat.format(dNowP14);
						}
					}
					else
					{
						strExpiryDate = objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
					}*/
					strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
					/**End on 13.04.2017 */
					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, objPatientVO_p);
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
					objArrEpisodeVO_p[i].setTariffId(objUserVO_p.getTariffID());
					String referMlcNo="";
					referMlcNo=episodeRefVO.getMlcNo();
					
					objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					
					objArrEpisodeVO_p[i].setPatCrNo(dailyPatVO.getPatCrNo());
					objArrEpisodeVO_p[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					objArrEpisodeVO_p[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
					
					objArrEpisodeVO_p[i].setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate this dailyPatient VO with objArrEpisodeVO_p[i]
					HelperMethods.populatetToNullOrEmpty(dailyPatVO, objArrEpisodeVO_p[i]);
					//System.out.println("objPatientVO_p.getDepartmentCode() [In PatientBO]:"+objPatientVO_p.getDepartmentCode());
					//System.out.println("objArrEpisodeVO_p[i].getDepartmentCode()  [In PatientBO]:"+objArrEpisodeVO_p[i].getDepartmentCode());
					objPatientVO_p.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
					dailyPatVO.setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
					dailyPatVO.setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
					
					dailyPatVO.setIsReferred(objPatientVO_p.getIsReferred());
					
					///In case of new dept visit is old is false
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					
					dailyPatVO.setEpisodeVisitNo("1");
					
					//dailyPatVO.setDepartmentCode(objPatientVO_p.getDepartmentCode());
					//dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
					
					dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_SPECIAL,RegistrationConfig.UNIT_TYPE_SPECIALITY, "NDSPL");
					
					dailyPatVO.setBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
					
					dailyPatVO.setEpisodeCode(dailyPatDao.generateEpisodeCode(objUserVO_p, dailyPatVO.getPatCrNo(),dailyPatVO.getDepartmentUnitCode()));
					
					// Setting Expiry Date(General,Special & Casuality) in PatientVo and so DailyPatientVO
					PatientBOHelper.setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, dailyPatVO, objArrEpisodeVO_p[i], strExpiryDate);
					
					
					dailyPatDao.saveDailyPatientDtl(objHisDAO, dailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
					HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
					
					//Create new entry in Episode detail
					
					if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
						}

					}
					else
					{
						objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					}

					objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
					
					////setting visit number//
					
					objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
					objArrEpisodeVO_p[i].setEntryDate(objPatientVO_p.getSystemDate());
					
					
					
					System.out.println("----Pat Ren Req--------"+objPatientVO_p.getSplRenewalRequired()+"------------");
					System.out.println("----Date--------"+objPatientVO_p.getExpiryDate()+"------------");
					System.out.println("objPatientVO_p.getDepartmentUnitCode() :"+objPatientVO_p.getDepartmentUnitCode());
					System.out.println("objArrEpisodeVO_p[i].getDepartmentUnitCode() :"+objArrEpisodeVO_p[i].getDepartmentUnitCode());
					
					if(objPatientVO_p.getRenewalConfig()!=null){
						if(objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("1")|| objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("2")){
							
							
							if(objPatientVO_p.getSplRenewalRequired()!=null && 
									(objPatientVO_p.getSplRenewalRequired().equals("1") || objPatientVO_p.getSplRenewalRequired().equals("2")))
							{
								objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
								PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
								//Calling PatientDAO
								objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
							}else{
								if(flagPatSaveStatus)
									objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "6");
							}
						}
					}
					
					 
					//Calling EpisodeDAO
					objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
					objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
					objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
					episodeDao.saveEpisodeDtl(objHisDAO, objArrEpisodeVO_p[i], objUserVO_p, "1");
				 /**Call to save episode details in 'hrgt_seccat_change_dtl'*/
					secondaryCategoryChangesForEveryEpisodeCreated( tx, objUserVO_p, objArrEpisodeVO_p[i], objPatientVO_p);
					/***/

					if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						
						String refEpisode = episodeRefVO.getEpisodeCode();
						String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
						String serialNo = episodeRefVO.getSerialNo();
						HelperMethods.populate(episodeRefVO, objArrEpisodeVO_p[i]);
						episodeRefVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());

						if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							episodeRefVO.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							episodeRefVO.setEpisodeVisitNo("1");

						}
						if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							episodeRefVO.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							episodeRefVO.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
							episodeRefVO.setToEpisodeVisitNo("1");
							episodeRefVO.setEpisodeCode(refEpisode);
							episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
							episodeRefVO.setExternalHospitalCode("");
							episodeRefVO.setSerialNo(serialNo);
						}

						//Calling EpisodeRefDtlDAO
						if((objPatientVO_p.getIsPatReferByList()!=null && objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))||
								(objPatientVO_p.getIsPatReferByList()!=null && objPatientVO_p.getPatIsCrossConsultantWithReferal().equals("1") && objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)))
						{
							
							episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO, episodeRefVO, objUserVO_p,"2");	// for updating Acceptance Date
						}
						else
						{
							if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals("1"))
								episodeRefDtlDAO.saveEpisodeRefDtl(objHisDAO, episodeRefVO, objUserVO_p, "1");
						}
						
					}
					//Modified to add the details on renewal table while new department visit cum renewal by Singaravelan on 08-10-13
					
					//////////////////////////////////////////
					
					//////////////////////////////////////////
					System.out.println("----Amount-----"+amount+"-----------");
					//if (amount != null && !amount.equals("") && !amount.equals("0") && !amount.equals(RegistrationConfig.PAT_CAT_FREE_FEES))
					//{
						directChargeVO[i]= new DirectChageDetailVO();
						directChargeVO[i].setTariffId(objUserVO_p.getTariffID());
						directChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						directChargeVO[i].setModuleId(objUserVO_p.getModuleId());
						HelperMethods.populate(directChargeVO[i], objArrEpisodeVO_p[i]);
						directChargeVO[i].setPatAmountCollected(objPatientVO_p.getPatAmountCollected());
						directChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
						directChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
						directChargeVO[i].setCreditBillFlag(objPatientVO_p.getCreditBillFlag());
						directChargeVO[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
						directChargeVO[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
						directChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
						
						directChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
						directChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
						directChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
						directChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
						directChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
						directChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
						directChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
						directChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
						directChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());
						//directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode());
						directChargeVO[i].setPaymentMode(objArrEpisodeVO_p[i].getPaymentModeCode().split("#")[0]);

					//Added by warish for cash collection combo save
						if(!(directChargeVO[i].getPaymentMode().equals("1") || directChargeVO[i].getPaymentMode().equals("10") || directChargeVO[i].getPaymentMode().equals("13")))
							directChargeVO[i].setPaymentModeRefId(objArrEpisodeVO_p[i].getPaymentModeCodeRefId());
						else
							directChargeVO[i].setPaymentModeRefId("");//for cash
						/*if (!(directChargeVO[i].getPatAmountCollected().equals("0") 
								&& !(directChargeVO[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
								&& !directChargeVO[i].getPatAmountCollected().equals("") 
								&& directChargeVO[i].getPatAmountCollected() != null)) 
						{*/
							directChargeDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
						/*}*/
						///Billing module integration
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
						// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
						
						if((objArrEpisodeVO_p[i].getSplRenewalRequired()!=null && objArrEpisodeVO_p[i].getSplRenewalRequired().equals("1"))|| 
								(objPatientVO_p.getSplRenewalRequired()!=null && objPatientVO_p.getSplRenewalRequired().equals("1")))
						{
							renewDetailVO[i] = new RenewalDetailVO();
							renewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
							renewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
							renewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							renewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
							renewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
							renewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
							renewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
							renewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
							renewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
							renewDetailVO[i].setNewExpiryDate(strExpiryDate);
							renewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

							renewDetailDAO.saveRenewalDtl(objHisDAO,renewDetailVO[i], objUserVO_p,"1");
						
						}					
					
					//}
					
					
					//To update the CR No. and the Appointment Status in Appointment Detail Table in Special Clinic Registration with Appointment Added by Singaravealn on 28-Aug-2014
					
					if(objPatientVO_p.getPatAptNo()!=null && objPatientVO_p.getPatAptNo()!="")
					{
						objPatientDAO.updateAppointmentinSplClinicReg(objHisDAO, objPatientVO_p, objUserVO_p);
					}	
					
					//Code Commented by Garima as in AIIMS Letter details will be added through separate process for CM Fund Ctaegory
					//For the Details Insertion in the Beneficiary Patient Dtl Table 
					/*if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
							(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) ||
							objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))){
						
						patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
						//Need to remove this cond once the staff no constraints from the table is removed
						if((objPatientVO_p.getStaffCardNo()!=null&&!objPatientVO_p.getStaffCardNo().equals(""))||
								(objPatientVO_p.getAgsNo()!=null&&!objPatientVO_p.getAgsNo().equals(""))){
						if(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
							objPatientVO_p.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);
						HelperMethods.populate(objBenPatVO, objPatientVO_p);
						HelperMethods.setNullToEmpty(objBenPatVO);
						
						if(!objBenPatVO.getAgsNo().equals(""))
							objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
						else if(!objBenPatVO.getStaffCardNo().equals(""))
							objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
						else
							objBenPatVO.setCardNo("NA");
						
						if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
							objBenPatVO.setIsDependent("1");
							objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
						}else{
							objBenPatVO.setIsDependent("0");
							objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
						}
						objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
						objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
						
						//added by Mukund on 27.07.2016
						//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
						benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
						}
											
					}
					*/
					//Code Commented by Garima as in AIIMS Letter details will be added through separate process for CM Fund Ctaegory End


					//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL 
					if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
						HelperMethods.populate(objCreditAvailDtlVO, directChargeVO[i]);
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
						
						objCreditAvailDtlVO.setTariffActualRate(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setPaidByClientAmt(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setPaidByPatAmt("0");
						objCreditAvailDtlVO.setNetAmount(directChargeVO[i].getPatActualAmount());
						objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());

						//Need to include approved by here
						if(Double.valueOf(objCreditAvailDtlVO.getNetAmount())>0)
						creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO, objCreditAvailDtlVO, objUserVO_p, "1");
					}				
				
					
					/////Query to fetch Unit working days for printing////
					
					//	objArrEpisodeVO_p[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
					
					/////////////////////////////////////////////////////
					
					
					//****** query to fetch disclaimer for printing ************//*
						objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, objArrEpisodeVO_p[i].getDepartmentUnitCode(), objUserVO_p));
						
					//**************************************************//*	
				
				
			}
			synchronized (objHisDAO) 
			{
			   objHisDAO.fire();
			}		
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch(HisSQLManualException e){	
			tx.rollback();
			throw new HisSQLManualException("Department-Unit Limit Exhausted");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
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
			System.out.println("Close the transaction...");
			// System.out.println("objPatientVO_p.getDepartment()::::"+objPatientVO_p.getDepartment());
			System.out.println("objArrEpisodeVO_p.getDepartment()::::" + objArrEpisodeVO_p[0].getDepartment());
			if (objHisDAO != null) {
				objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	public EpisodeVO[] spclOldDeptVisitStamp(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, UserVO objUserVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
			System.out.println("------------------------------------");
			System.out.println("PatientBO :: spclOldDeptVisitStamp()");
			
			PatientDAO objPatientDAO = new PatientDAO(tx);
			tx.begin();
			objHisDAO = new HisDAO(null, null);
			String strExpiryDate="",strOldExpiry="";
			String strRenewalTypeHospOrEpisode="";
			if(objPatientVO_p.getRenewalConfig().getStrRenewalType()!=null && 
					(objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("3") || objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("4"))){
				strRenewalTypeHospOrEpisode="E";
			}else{
				strRenewalTypeHospOrEpisode="H";	//i.e for 1 or 2
			}
			
			/**By Mukund on 13.04.2017 */
			if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY))
			{
				Date dt1,dNowP14=null; 
				String dNowP14Str="";
				Calendar cldr = Calendar.getInstance();
				cldr.add(Calendar.DATE, 14);
				SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
				dNowP14Str =dateFormat.format(cldr.getTime());
				dNowP14 = dateFormat.parse(dNowP14Str);//dNowP14 = sysdate + 14 days in dd-MM-yyyy format
				dt1=dateFormat.parse(objPatientVO_p.getCardvalidityDate());//dt1 = card valid upto date in dd-MM-yyyy format
				//compare two dates
				int judge = dt1.compareTo(dNowP14);
				if(judge<=0)// dt1 is earlier than dNowP14
				{
					strExpiryDate = dateFormat.format(dt1);
				}
				else if(judge>0)//dt1 is later than dNowP14
				{
					strExpiryDate = dateFormat.format(dNowP14);
				}
			}
			else
			{
				strExpiryDate = objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
			}
			//strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
			/**End on 13.04.2017 */
			if(objArrEpisodeVO_p!=null && objArrEpisodeVO_p.length>0)
			{
				System.out.println("----Pat Ren Req in PatientBO --------"+objPatientVO_p.getSplRenewalRequired()+"------------");
				strOldExpiry=objPatientVO_p.getExpiryDate();
				if(strRenewalTypeHospOrEpisode.equals("H") && objPatientVO_p.getSplRenewalRequired()!=null && objPatientVO_p.getSplRenewalRequired().equalsIgnoreCase("1"))
				{
					PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
					System.out.println("strExpiryDate :"+objPatientVO_p.getExpiryDate());
					//Calling PatientDAO
					objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
				}
			}
			// Setting Expiry in PatientVO so that required ExpiryDate of EpisodeVo can be resetted in PatientBOHelper.oldDeptVisitStamp()
			//objPatientVO_p.setExpiryDate(strOldExpiry);
			PatientBOHelper.spclOldDeptVisitStamp(objHisDAO,objArrEpisodeVO_p, objPatientVO_p, objUserVO_p, tx, objEpisodeRefDtlVO_p,strExpiryDate);
			
			for(int i=0;i<objArrEpisodeVO_p.length;i++)
			{
				/*** Query to fetch Disclaimer*********************/
				String usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
	
				objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(usablityFlag,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
			}
			
			if(objPatientVO_p.getPatAptNo()!=null && objPatientVO_p.getPatAptNo()!="")
			{
				objPatientDAO.updateAppointmentinSplClinicReg(objHisDAO, objPatientVO_p, objUserVO_p);
			}
			
			synchronized (objHisDAO) {
				 objHisDAO.fire();
			}			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch(HisAppointmentNotAvailableException e){
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());	
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	public Map saveSpclPatientVisit(EpisodeVO[] objArrOldDeptEpisodeVO_p, PatientVO objPatientVO_p,UserVO objUserVO_p, EpisodeRefDtlVO objEpisodeRefDtlVO_p,EpisodeVO[] objArrNewDeptEpisodeVO)
	{
		System.out.println("----------------------");
		System.out.println("PatientBO :: saveSpclPatientVisit");
		System.out.println("----------New Dept VO------------"+objArrNewDeptEpisodeVO+"-----------------------");
		System.out.println("----------Old Dept VO------------"+objArrOldDeptEpisodeVO_p+"-----------------------");

		Map episodeMap=new HashMap();
		if(objArrNewDeptEpisodeVO!=null)//Modified to check both old and new dept visit by singaravelan on 26-09-13
		{
			objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
			if (objArrNewDeptEpisodeVO!=null && objArrOldDeptEpisodeVO_p!=null)
			{
				System.out.println("----------In old and New Dept Vist----------------------");
				objArrNewDeptEpisodeVO=spclNewDepartmentVisitStamp(objArrNewDeptEpisodeVO, objPatientVO_p, objEpisodeRefDtlVO_p, objUserVO_p, null);
				
				objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
				objArrOldDeptEpisodeVO_p=spclOldDeptVisitStamp(objArrOldDeptEpisodeVO_p, objPatientVO_p, objUserVO_p, objEpisodeRefDtlVO_p);
				episodeMap.put("SPCLNEWEPISODELIST", objArrNewDeptEpisodeVO);
				episodeMap.put("SPCLOLDEPISODELIST", objArrOldDeptEpisodeVO_p);
			}
			else
			{
				System.out.println("----------In New Dept Vist----------------------");
				String strRenewalType ="";
				if(objPatientVO_p.getRenewalConfig()!=null)
					strRenewalType=objPatientVO_p.getRenewalConfig().getStrRenewalType();
				
				if(strRenewalType.equals("1") || strRenewalType.equals("2")){
					if(objPatientVO_p.getSplRenewalRequired()!=null && objPatientVO_p.getSplRenewalRequired().equals("1")){
						objUserVO_p.setTariffID(RegistrationConfig.RENEWAL_TARIFF_ID);
					}else{
						objUserVO_p.setTariffID(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
					}
				}
				objArrNewDeptEpisodeVO=spclNewDepartmentVisitStamp(objArrNewDeptEpisodeVO, objPatientVO_p, objEpisodeRefDtlVO_p, objUserVO_p, null);		
				episodeMap.put("SPCLNEWEPISODELIST", objArrNewDeptEpisodeVO);
			}
		}
		else
		{
			System.out.println("----------In old Dept Vist----------------------");
			objUserVO_p.setTariffID(RegistrationConfig.OLD_DEPT_VISIT_TARIFF_ID);
			objArrOldDeptEpisodeVO_p=spclOldDeptVisitStamp(objArrOldDeptEpisodeVO_p, objPatientVO_p, objUserVO_p, objEpisodeRefDtlVO_p);		
			episodeMap.put("SPCLOLDEPISODELIST", objArrOldDeptEpisodeVO_p);
		}
		
		return episodeMap;
	}
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByMobileNOForSearchTile(PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByMobileNoForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByEmailForSearchTile(PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByEmailForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	
	/**
	 * Retrieves the details of a patient for Patient Search Tile 
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public List searchPatientDetailByDemographicDetailForSearchTile(PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByDemographicDetailForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}


	
	public boolean saveMlcToNonMlcEpisodes(MlcToNonMlcVO[] objArrMlcToNonMlcVO_p, UserVO objUserVO_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flagSave=false;
		DailyPatientVO objDailyPatientVO = new DailyPatientVO();
		EpisodeVO objEpisodeVO = new EpisodeVO();
		HisDAO objHisDAO = null;
		try
		{
			tx.begin();
			objHisDAO = new HisDAO("Registration","PatientBO");
			DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
			MLCRevokeEpisodeDetailDAO objMlcRevEpiDAO = new MLCRevokeEpisodeDetailDAO(tx);
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			for (int i = 0; i < objArrMlcToNonMlcVO_p.length; i++)
			{
				objEpisodeVO.setPatCrNo(objArrMlcToNonMlcVO_p[i].getPatCrNo());
				objEpisodeVO.setEpisodeCode(objArrMlcToNonMlcVO_p[i].getEpisodeCode());
				objEpisodeVO.setEpisodeVisitNo(objArrMlcToNonMlcVO_p[i].getEpisodeVisitNo());
				objEpisodeVO.setMlcNo(objArrMlcToNonMlcVO_p[i].getMlcNo());
				objEpisodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
				objEpisodeVO.setMlcFlag(RegistrationConfig.IS_MLC_FALSE);

				objEpisodeDAO.saveEpisodeDtl(objHisDAO, objEpisodeVO, objUserVO_p, "2");

				objDailyPatientVO.setPatCrNo(objArrMlcToNonMlcVO_p[i].getPatCrNo());
				objDailyPatientVO.setEpisodeCode(objArrMlcToNonMlcVO_p[i].getEpisodeCode());
				objDailyPatientVO.setEpisodeVisitNo(objArrMlcToNonMlcVO_p[i].getEpisodeVisitNo());
				objDailyPatientVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC);
				objDailyPatientVO.setMlcFlag(RegistrationConfig.IS_MLC_FALSE);

				dailyPatientDao.saveDailyPatientDtl(objHisDAO, objDailyPatientVO, objUserVO_p, "3", "");

				objMlcRevEpiDAO.saveMlcRevokeDtl(objHisDAO, objArrMlcToNonMlcVO_p[i], objUserVO_p, "1");
				flagSave=true;
			}
			
			/***********************************************/	
			synchronized (objHisDAO) {
				objHisDAO.fire();
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
			return flagSave;
		}
		
	}
	
	public List<PatientBroughtByDtlVO> getPatBroughtByDetialsList(PatientBroughtByDtlVO objPatientBroughtByDtlVO_p, UserVO objUserVO_p)
	{
		//System.out.println("inside getPatAddressAll() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientBroughtByDtlVO> lstPatientBroughtByDtlVO =new ArrayList<PatientBroughtByDtlVO>();
		try
		{
			tx.begin();
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			lstPatientBroughtByDtlVO = patientBroughtByDtlDAO.searchPatientBroughtByDetail(objPatientBroughtByDtlVO_p, objUserVO_p);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstPatientBroughtByDtlVO;
	}
	public PatientBroughtByDtlVO getPatBroughtByDtlsEpisodeWise(PatientBroughtByDtlVO objPatientBroughtByDtlVO_p, UserVO objUserVO_p)
	{
		//System.out.println("inside getPatAddressAll() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientBroughtByDtlVO objPatientBroughtByDtlVO =new PatientBroughtByDtlVO();
		try
		{
			tx.begin();
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			objPatientBroughtByDtlVO = patientBroughtByDtlDAO.getPatBroughtByDtlsEpisodeWise(objPatientBroughtByDtlVO_p, objUserVO_p);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objPatientBroughtByDtlVO;
	}
	
	public MlcVO savePatMlcDtl(MlcVO objMlcVO_p, List<MlcParameterDtlVO> lstMlcParameterDtlVO_p, UserVO objUserVO_p, PatientBroughtByDtlVO objPatBroughtByDtlVO_p, PoliceVerificationDtlVO objPoliceVerDtlVO_p, String strflagMlcOnlineOffline_p, boolean isTodayOfflineVisit_p)
	{

		HisDAO objHisDAO = new HisDAO("MLC Detail", "PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean isTodayVisit=false;

		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			MlcDAO objMlcDAO = new MlcDAO(tx);
			MlcParameterDAO objMlcParameterDAO = new MlcParameterDAO(tx);
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			PatientDAO objPatientDAO = new PatientDAO(tx);	// isMLc flag update
			//PoliceVerificationDAO policeVerDAO=new PoliceVerificationDAO(tx);
			
			
			
			
			
			objMlcVO_p.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			
			// Calling MlcDAO
			objMlcDAO.saveMlcPatientDtl("1", objHisDAO, objMlcVO_p, objUserVO_p, "1");
			
			for(MlcParameterDtlVO voMlcParameterDtl: lstMlcParameterDtlVO_p)
			{
				// Calling MlcParameterDAO
				objMlcParameterDAO.saveMlcParameterDtl("1", objHisDAO, voMlcParameterDtl, objUserVO_p, "1");
			}
			
			
			/****  Start--> Updating Episode Dtl with Mlc Flag *****/
			EpisodeVO objEpisodeVO = new EpisodeVO();
			HelperMethods.populate(objEpisodeVO, objMlcVO_p);
			objEpisodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
			objEpisodeVO.setMlcFlag(RegistrationConfig.IS_MLC_CONFIRMED);
			
			// Calling EpisodeDAO
			if(strflagMlcOnlineOffline_p.equals("1") || (strflagMlcOnlineOffline_p.equals("2") && isTodayOfflineVisit_p)){
				objEpisodeDAO.saveEpisodeDtl(objHisDAO, objEpisodeVO, objUserVO_p, "2");
			}else{
				objEpisodeDAO.saveEpisodeDtl(objHisDAO, objEpisodeVO, objUserVO_p, "3");
			}
			/****  End--> Updating Episode Dtl with Mlc Flag *****/
			
			
			if(strflagMlcOnlineOffline_p.equals("1") || (strflagMlcOnlineOffline_p.equals("2") && isTodayOfflineVisit_p)){	
				/****  Start--> Updating DailyPatient Detail with Mlc Flag *****/
				DailyPatientVO objDailyPatientVO = new DailyPatientVO();
				HelperMethods.populate(objDailyPatientVO, objMlcVO_p);
				objDailyPatientVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
				objDailyPatientVO.setMlcFlag(RegistrationConfig.IS_MLC_CONFIRMED);
				// Calling DailyPatientDAO
				dailyPatientDao.saveDailyPatientDtl(objHisDAO, objDailyPatientVO, objUserVO_p, "3", "");
				/****  End--> Updating DailyPatient Dtl with Mlc Flag *****/
			}

			
			/****  Start--> Updating Patient Dtl with Mlc Flag=1 *****/
			PatientVO objPatientVO = new PatientVO();
			HelperMethods.populate(objPatientVO, objMlcVO_p);
			objPatientVO.setIsMLC(RegistrationConfig.IS_MLC_TRUE);
			// Calling PatientDAO
			objPatientDAO.savePatientDtl(objHisDAO, objPatientVO, objUserVO_p, "7");
			/****  End--> Updating Patient Dtl with Mlc Flag=1 *****/
			
			if ((RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) 
					&& (objPatBroughtByDtlVO_p != null))
			{
				HelperMethods.populate(objPatBroughtByDtlVO_p, objMlcVO_p);
				objPatBroughtByDtlVO_p.setIsValid(RegistrationConfig.IS_VALID_ACTIVE);
				patientBroughtByDtlDAO.create(objHisDAO, objPatBroughtByDtlVO_p, objUserVO_p, "4");
			}
			
			synchronized (objHisDAO) {
				objHisDAO.fire();
			}
			
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException();
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
			if(e.getMessage().indexOf("Duplicate MLC NO")!=-1)
				throw new HisDuplicateRecordException("This MLC having MLC No "+objMlcVO_p.getPatMlcNo()+" Already exisit");
			else
				throw new HisException(e.getMessage());

		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			
			tx.close();
		}
		return objMlcVO_p;
	}
	
	public MlcVO savePatMlcModificationDtl(MlcVO objMlcVO_p, List<MlcParameterDtlVO> lstMlcParameterDtlVO_p, UserVO objUserVO_p, PatientBroughtByDtlVO objPatBroughtByDtlVO_p, PoliceVerificationDtlVO objPoliceVerDtlVO_p)
	{

		HisDAO objHisDAO = new HisDAO("MLC Modification Detail", "PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean isTodayVisit=false;

		try
		{
			System.out.println("PatientBO :: savePatMlcModificationDtl()");
			tx.begin();
			MlcDAO objMlcDAO = new MlcDAO(tx);
			MlcParameterDAO objMlcParameterDAO = new MlcParameterDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			
			objMlcVO_p.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			
			// Calling MlcDAO
			objMlcDAO.saveMlcPatientDtl("2", objHisDAO, objMlcVO_p, objUserVO_p, "1");
			
			// Calling MlcParameterDAO for Inserting New Records
			for(int i=0; i<lstMlcParameterDtlVO_p.size(); i++){
				
				if(i==0)
					objMlcParameterDAO.saveMlcParameterDtl("2", objHisDAO, lstMlcParameterDtlVO_p.get(i), objUserVO_p, "1");

				lstMlcParameterDtlVO_p.get(i).setIsValid("1");		// isValid is using as a counter to update old record only once.
				objMlcParameterDAO.saveMlcParameterDtl("1", objHisDAO, lstMlcParameterDtlVO_p.get(i), objUserVO_p, "1");
			}
			
			if ((RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(RegistrationConfig.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) 
					&& (objPatBroughtByDtlVO_p != null))
			{
				HelperMethods.populate(objPatBroughtByDtlVO_p, objMlcVO_p);
				objPatBroughtByDtlVO_p.setIsValid(RegistrationConfig.IS_VALID_ACTIVE);
				patientBroughtByDtlDAO.create(objHisDAO, objPatBroughtByDtlVO_p, objUserVO_p, "4");
			}
			
			synchronized (objHisDAO) {
				objHisDAO.fire();
			}
			
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException();
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
			throw new HisException(e.getMessage());

		}
		finally
		{
			if (objHisDAO != null) {
				//objHisDAO.free();
				objHisDAO = null;
			}
			
			tx.close();
		}
		return objMlcVO_p;
	}
	
	public boolean isPatientDeathDtlAdded(String strCrNo_p,UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean exist=false;
		
		try
		{
			tx.begin();
			PatientDeathDetailDAO objPatDeathDtlDAO=new PatientDeathDetailDAO(tx);
			exist=objPatDeathDtlDAO.isPatientDeathDtlAdded(strCrNo_p,objUserVO_p);
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return exist;
	}
	
	public PatientDeathDetailVO getDeathDetailByCrNo(String strCrNo_p,UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDeathDetailVO patDeathVO=null;
		
		try
		{
			tx.begin();
			PatientDeathDetailDAO objPatDeathDtlDAO=new PatientDeathDetailDAO(tx);
			patDeathVO=objPatDeathDtlDAO.getDeathDetailByCrNo(strCrNo_p, objUserVO_p);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException("");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patDeathVO;
	}
	
	
	public Map getHandoverToDetail(PatientDeathDetailVO objPatientDeathDetailVO_p,UserVO objUserVO_p)
	{
	    JDBCTransactionContext tx =new JDBCTransactionContext();
	    Map essentialMap=new HashMap();
	    List lstRelationships=new ArrayList();
	   
	    PatientDeathDetailVO objPatDeathDtlVO=new PatientDeathDetailVO();
	    try
	    {
	    	tx.begin();
	    	PatientDeathDetailDAO objPatDeathDtlDAO=new PatientDeathDetailDAO(tx);
	    	EssentialDAO objEssentialDAO=new EssentialDAO(tx);
	    	
	    	objPatDeathDtlVO=objPatDeathDtlDAO.getPatientDeathDetail(objPatientDeathDetailVO_p, objUserVO_p);
	    	essentialMap.put(RegistrationConfig.DEADBODY_HANDOVER_DETAIL, objPatDeathDtlVO);
	    	
	    	/*lstRelationships=objEssentialDAO.getRelationsList(objUserVO_p);
	    	essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, lstRelationships);*/
	    	
    }
	    catch(HisRecordNotFoundException e)
	    {
			tx.rollback();
			e.getEssentialMap();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
	   		 tx.rollback();
	   		 System.out.println(e.getMessage());   		 
	   		 throw new HisApplicationExecutionException();
	   	}
	   	catch(HisDataAccessException e)
	   	{		
	   		 tx.rollback();
	   		 System.out.println(e.getMessage());   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e)
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
	
	public void savePatientDeathDetail(PatientDeathDetailVO objPatDeathDtlVO_p,PatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = new HisDAO("Patient Death Detail", "PatientBO");
		//EpisodeVO objEpisodeVO = new EpisodeVO();
		CertificateIssueDtlVO objCertificateIssueDtlVO = new CertificateIssueDtlVO();
		String deathCertificateId="";
		String genMode="";
		
		try
		{
			tx.begin();
			System.out.println("PatientBO :: savePatientDeathDetail()");
			DailyPatientDAO objDailyPatientDAO = new DailyPatientDAO(tx);
			PatientDeathDetailDAO objPatDeathDAO=new PatientDeathDetailDAO(tx);
			PatientDAO objPatientDAO=new PatientDAO(tx);
			
			
			///Start Generating Certificate////////
			//if(objPatDeathDtlVO_p.getIsPrintCertificate()!=null && objPatDeathDtlVO_p.getIsPrintCertificate().equals("1"))
			//{
				CertificateIssueDtlDAO objCertificateIssueDAO=new CertificateIssueDtlDAO(tx); 
				//genMode="1";
				deathCertificateId=objPatDeathDAO.generateDeathCertificateId(objUserVO_p, objPatDeathDtlVO_p.getPatCrNo());
				
				objCertificateIssueDtlVO.setCertificateId(deathCertificateId);
				objCertificateIssueDtlVO.setCertificateDesc(deathCertificateId);
				objCertificateIssueDtlVO.setRecordType(RegistrationConfig.RECORD_TYPE_DEATH_CERTIFICATE);
				objCertificateIssueDtlVO.setHandoverTo(objPatDeathDtlVO_p.getCertificateHandoverTo());
				objCertificateIssueDtlVO.setIsDuplicate(RegistrationConfig.IS_DUPLICATE_CERTIFICATE_NO);
				objCertificateIssueDtlVO.setIsReceiptTaken(objPatDeathDtlVO_p.getIsReceiptTaken());
				objCertificateIssueDtlVO.setRecordStatus(objPatDeathDtlVO_p.getRecordStatus());
				objCertificateIssueDtlVO.setDeptUnitCode(objPatDeathDtlVO_p.getDepartmentUnitCode());
				
				objCertificateIssueDAO.saveCertificateIssueDtl("1", objHisDAO, objCertificateIssueDtlVO, objUserVO_p, "1");
			//}
			///End Generating Certificate////////
			
			// Inserting Episode Death Detail Record	
			objPatDeathDtlVO_p.setDeathCertificateId(deathCertificateId);
			objPatDeathDtlVO_p.setIsDeathDetailEntered("1");
			objPatientVO_p.setPatIsDead(RegistrationConfig.PATIENT_IS_DEAD);
			objPatDeathDAO.savePatientDeathDtl("1", objHisDAO, objPatDeathDtlVO_p, objPatientVO_p, objUserVO_p, "1");
			
			//updating Patient Status in HRGT_DAILY_PATIENT_DTL (updatePatStatusIsconfirmed)
			DailyPatientVO objDailyPatientVO = new DailyPatientVO();
			HelperMethods.populate(objDailyPatientVO, objPatDeathDtlVO_p);
			objDailyPatientVO.setPatIsDead(RegistrationConfig.PATIENT_IS_DEAD);
			objDailyPatientDAO.saveDailyPatientDtl(objHisDAO, objDailyPatientVO, objUserVO_p, "4", "");
			
			//updating Patient Status in HRGT_PATIENT_DTL (updatePatStatus)
			objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "8");
			
			synchronized (objHisDAO) {
				objHisDAO.fire();
			}
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}
	
	public BeneficiaryPatientVO getCreditBeneficiaryEssentials(String _crNo, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		BeneficiaryPatientVO  objBenPatientVO_p=new BeneficiaryPatientVO();

		try
		{
			tx.begin();
			BeneficiaryPatientDAO objBenPatientDAO = new BeneficiaryPatientDAO(tx);		
			
			objBenPatientVO_p = objBenPatientDAO.retrieveBeneficiaryDtlsByCrNo(_crNo, objUserVO_p);

		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return objBenPatientVO_p;
	}
	
	public EpisodeRefDtlVO[] getReferEpisodeDtl(String strCrNo_p, String strDeptUnitCode_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeRefDtlVO[] objArrRefEpisodeVO ={};
		
		EpisodeRefDtlVO[] arrVO ={};
		try
		{
			System.out.println("PatientBO :: getReferEpisodeDtl()");
			tx.begin();
			EpisodeRefDtlDAO episodeRefDao = new EpisodeRefDtlDAO(tx);
			objArrRefEpisodeVO = episodeRefDao.getRefEpisodeDtlByCrNoByDeptByUnit(strCrNo_p,strDeptUnitCode_p, objUserVO_p,"1");
								
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrRefEpisodeVO;
	}
	
	public String checkPatientIsAdmitted(String _crNo, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String isAdmitted="";
		try
		{
			tx.begin();
			PatientDAO patDao = new PatientDAO(tx);
			isAdmitted = patDao.checkPatientIsAdmitted(_crNo, objUserVO_p);

		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return isAdmitted;
	}
	
	
	//Start:Sheeldarshi:Registration Cancellation Changes
public List<RenewalConfigVO> getMapOfRenewalConfigDtl(UserVO objUserVO_p, String strRenewelGrp_p) {
		
		List<RenewalConfigVO> lstRenewalConfigVO=null;
		Map<String, RenewalConfigVO> mapOfRenewalVo=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(objUserVO_p,strRenewelGrp_p,"1");
			//mapOfRenewalVo=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return lstRenewalConfigVO;
	}
	public RegistrationCancellationVO saveRegistrationCancellationDtl(RegistrationCancellationVO _regCancellationVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String disclaimer=new String();
		String workingDays=new String();
		RegistrationCancellationVO regCancellationVO=new RegistrationCancellationVO();
		HisDAO daoObj= new HisDAO("Registration","PatientBO");
		RegistrationCancellationDAO regCancellationDAO = new RegistrationCancellationDAO(tx);
		DailyPatientDAO dailyPatDao= new DailyPatientDAO(tx);
		try
		{
			tx.begin();
			
			//DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			//_regCancellationVO.setStrBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
			//new mode is added to support the provision of refund and cancellation in registration cancellation process by Mukund on 15.07.2016
			if(_regCancellationVO.getIsCancellationFlag()!=null && _regCancellationVO.getIsCancellationFlag().equals("1") && _regCancellationVO.getRefundAllowed().equals("1"))
				regCancellationDAO.saveRegistrationCancellationDtl(daoObj,_regCancellationVO, objUserVO_p,"1");//refund and cancellation both
			else if(_regCancellationVO.getIsCancellationFlag()!=null && _regCancellationVO.getIsCancellationFlag().equals("1") && _regCancellationVO.getRefundAllowed().equals("0"))
				regCancellationDAO.saveRegistrationCancellationDtl(daoObj,_regCancellationVO, objUserVO_p,"7");//cancellation only
			else if(_regCancellationVO.getIsCancellationFlag()!=null && _regCancellationVO.getIsCancellationFlag().equals("0") && _regCancellationVO.getRefundAllowed().equals("1"))
				regCancellationDAO.saveRegistrationCancellationDtl(daoObj,_regCancellationVO, objUserVO_p,"6");//refund only
			//regCancellationVO=new RegistrationCancellationVO();
				
			
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			//tx.rollback();  // need to comment this line after issue has been resolved
			tx.close();
		}
		return regCancellationVO;
	}
	public RegistrationCancellationVO updateExpiryDates(RegistrationCancellationVO _regCancellationVO, UserVO objUserVO_p,String strMode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RegistrationCancellationVO regCancellationVO=new RegistrationCancellationVO();
		HisDAO daoObj= new HisDAO("Registration","PatientBO");
		RegistrationCancellationDAO regCancellationDAO = new RegistrationCancellationDAO(tx);
		try
		{
			tx.begin();
			
			//DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			//_regCancellationVO.setStrBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
			regCancellationDAO.saveRegistrationCancellationDtl(daoObj,_regCancellationVO, objUserVO_p,strMode);
			regCancellationVO=new RegistrationCancellationVO();
							
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return regCancellationVO;
	}
	
	
	//End:Sheeldarshi:Registration Cancellation Changes
	
	//To Fetch the CRNo Details to be Merged, Added by Singaravelan on 11-Dec-2014
	public PatientVO getNotUsedCrNo(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO patVO=null;
		String countMain="";
		String count="";
		
		try
		{
			tx.begin();
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			CrNoMergeDAO crNoMergeDAO=new CrNoMergeDAO(tx);
			patVO=new PatientVO();
			patVO.setPatCrNo(crNo);
			count=crNoMergeDAO.countMergedOrMainCrNo(crNo,userVO,"1");
			countMain=crNoMergeDAO.countMergedOrMainCrNo(crNo,userVO,"2");
			if(countMain.equals("0"))
			{	
				if(count.equals("0"))
					patVO=searchPatientByCrNo(patVO,userVO);
				else
					throw new HisApplicationExecutionException("CR Number is Already Merged");
			}
			else
			{
				throw new HisApplicationExecutionException("CR Number is Main CR Number. You Cannot Merged This CR Number.");
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
			throw new HisApplicationExecutionException(e.getMessage());
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
		return patVO;
	}
	
	//To Merge the CRNos, Added by Singaravelan on 11-Dec-2014
	public void saveNotUsedCrNo(List<CrNoMergeDtlVO> lstNotUsedCRNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO daoObj= new HisDAO("Registration","PatientBO");
		try
		{
			tx.begin();
			CrNoMergeDAO mergeDAO=new CrNoMergeDAO(tx);
			PatientDAO patDAO=new PatientDAO(tx);
			String str=Config.IS_VALID_DELETED;
			for(int i=0;i<lstNotUsedCRNo.size();i++)
			{
				mergeDAO.saveNotUsedCrNo(daoObj,lstNotUsedCRNo.get(i),userVO,"1");
			}
			
			synchronized (daoObj) {
				daoObj.fire();
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
	
	//To Get the List Of already Merged CRNos, Added by Singaravelan on 11-Dec-2014
	public List<PatientVO> getMergedCrNo(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lst=null;
		List mergedPatVOList=new ArrayList<PatientVO>();
		
		try
		{
			tx.begin();
			CrNoMergeDAO mergeDAO=new CrNoMergeDAO(tx);
			lst=mergeDAO.getMergedCrNo(crNo,userVO);
			for(int i=0;i<lst.size();i++)
			{
				Entry obj=(Entry)((List)lst).get(i);
				PatientVO patVO=new PatientVO();
				patVO.setPatCrNo((String)obj.getValue());
				patVO=searchPatientByCrNo(patVO, userVO);
				mergedPatVOList.add(patVO);
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
		return mergedPatVOList;
	}
	
	
	public void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO daoObj= new HisDAO("Registration","PatientBO");

		try
		{
			tx.begin();
			CrNoMergeDAO mergeDAO=new CrNoMergeDAO(tx);
			PatientDAO patDAO=new PatientDAO(tx);
			String str=Config.IS_VALID_ACTIVE;
			mergeDAO.revokeMergedCRNo(daoObj,reason,mainCrNo,crNo,userVO,"2");
			
			synchronized (daoObj) {
				daoObj.fire();
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
	/*  ## 		Modification Log							
	##		Modify Date				:12thJan'15 
	##		Reason	(CR/PRS)		:Change Treatment Category
	##		Modify By				:Sheeldarshi */
public Map getTreatmentCategoryEssential(UserVO _userVO) {

	Map essentialMap = new HashMap();
	List lstSecondaryCat = null;
    List lstApplicableServices = null;
	JDBCTransactionContext tx = new JDBCTransactionContext();
	try {
		tx.begin();

		ChangeTreatmentCategoryDAO objChangeTreatmentCategoryDAO = new ChangeTreatmentCategoryDAO(tx);

		lstSecondaryCat = objChangeTreatmentCategoryDAO.getTreatmentCatWithExpiryDays(_userVO);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS,lstSecondaryCat);
		
		/*Added by Vasu Dated on 16.April.18 to get list of applicable services for treatment categories*/
		lstApplicableServices = objChangeTreatmentCategoryDAO.getApplicableServices(_userVO);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_APPLICABLE_SERVICES,lstApplicableServices);
		//End Vasu

	} catch (HisRecordNotFoundException e) {
		tx.rollback();
		//e.printStackTrace();
		throw new HisRecordNotFoundException(e.getMessage());
	}

	catch (HisDataAccessException e) {
		tx.rollback();
		e.printStackTrace();
		throw new HisDataAccessException();
	} catch (HisApplicationExecutionException e) {
		tx.rollback();
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	} catch (HisException e) {
		tx.rollback();
		e.printStackTrace();
		throw new HisException();
	} catch (Exception e) {
		tx.rollback();
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
	return essentialMap;
}
public EpisodeVO[]  getAllOpenEpisodesVisitedTodayForTreatment(String crNo, UserVO userVO)
{
	Map essentialMap = new HashMap();
	JDBCTransactionContext tx = new JDBCTransactionContext();
	EpisodeVO[] _arrEpisodeDupVO;
	EpisodeVO[] objArrEpisodeVO_p;
	String reprintMode = "";

	try
	{
		tx.begin();
		EpisodeDAO episodeDao = new EpisodeDAO(tx);
		objArrEpisodeVO_p = episodeDao.getAllOpenEpisodesVisitedToday(crNo, userVO, "13");
		//essentialMap.put(RegistrationConfig.CHANGE_TR, objArrEpisodeVO_p);
		
	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisRecordNotFoundException(e.getMessage());
		
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException(e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisDataAccessException(e.getMessage());
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException(e.getMessage());
	}
	finally
	{
		tx.close();
	}
	return objArrEpisodeVO_p;
}
public EpisodeVO  getPatientAdmittedEpisodes(String crNo, UserVO userVO)
{
	Map essentialMap = new HashMap();
	JDBCTransactionContext tx = new JDBCTransactionContext();
	EpisodeVO _arrEpisodeDupVO;
	EpisodeVO objArrEpisodeVO_p;
	String reprintMode = "";

	try
	{
		tx.begin();
		ChangeTreatmentCategoryDAO objChangeTreatmentCategoryDAO = new ChangeTreatmentCategoryDAO(tx);
		objArrEpisodeVO_p = objChangeTreatmentCategoryDAO.getPatientAdmittedEpisodes(crNo, userVO, "14");
		//essentialMap.put(RegistrationConfig.CHANGE_TR, objArrEpisodeVO_p);
		
	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisRecordNotFoundException(e.getMessage());
		
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException(e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisDataAccessException(e.getMessage());
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException(e.getMessage());
	}
	finally
	{
		tx.close();
	}
	return objArrEpisodeVO_p;
}
public boolean changeTreatmentCategory(ChangeTreatmentCategoryVO[] _TreatmentCategoryChangeVO,ChangeTreatmentCategoryVO[] TreatmentCategoryRevokeVO, UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	int x = 0, y = 0;
	DailyPatientVO _dailyPatientVO = new DailyPatientVO();
	EpisodeVO _episodeVO = new EpisodeVO();

	try
	{
		System.out.println("Begining transaction");
		tx.begin();

		// ///////////////Check if the secondary category is revoked/////////
		/*for (int z = 0; z < _secCatChangeVO.length; z++)
		{
			if (_secCatChangeVO[z].getPatNewSecondaryCatCode().equals(RegistrationConfig.REVOKE_TREATMENT_CATEGORY_VALUE))
			{
				_secCatChangeVO[z].setPatNewSecondaryCatCode("");

			}

		}*/
		// /////////////////////////////////////////////////////

		DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
		ChangeTreatmentCategoryDAO _TreatmentCatChangeDAO = new ChangeTreatmentCategoryDAO(tx);
		EpisodeDAO episodeDAO = new EpisodeDAO(tx);
		if(_TreatmentCategoryChangeVO!=null)
		{
			for (int i = 0; i < _TreatmentCategoryChangeVO.length; i++)
			{
				_episodeVO.setPatCrNo(_TreatmentCategoryChangeVO[i].getPatCrNo());
				_episodeVO.setEpisodeCode(_TreatmentCategoryChangeVO[i].getEpisodeCode());
				_episodeVO.setPatSecondaryCatCode(_TreatmentCategoryChangeVO[i].getPatNewSecondaryCatCode());
				_episodeVO.setEpisodeVisitNo(_TreatmentCategoryChangeVO[i].getEpisodeVisitNo());
				_episodeVO.setValidUpto(_TreatmentCategoryChangeVO[i].getValidUpto());
				_episodeVO.setSecCatExpDate(_TreatmentCategoryChangeVO[i].getExpiryDate());

				// ///////updating episode dtl////////
			//	x = episodeDAO.updateSecondaryCategoryEpisodeDtl(_episodeVO, _userVO);
				if(_TreatmentCategoryChangeVO[i].getExpiryDate()!=null && !_TreatmentCategoryChangeVO[i].getExpiryDate().equals(""))
					x = _TreatmentCatChangeDAO.updateTreatmentCatNExpiryDate(_episodeVO, _userVO);
				else
					x = _TreatmentCatChangeDAO.updateTreatmentCatNValidUpTo(_episodeVO, _userVO);

				_dailyPatientVO.setPatCrNo(_TreatmentCategoryChangeVO[i].getPatCrNo());
				_dailyPatientVO.setEpisodeCode(_TreatmentCategoryChangeVO[i].getEpisodeCode());
				_dailyPatientVO.setPatSecondaryCatCode(_TreatmentCategoryChangeVO[i].getPatNewSecondaryCatCode());
				_dailyPatientVO.setEpisodeVisitNo(_TreatmentCategoryChangeVO[i].getEpisodeVisitNo());

				// ////////updating daily patient dtl////////
			//OLD//		dailyPatientDao.updateSecondaryCategory(_dailyPatientVO, _userVO);
				_dailyPatientVO.setTreatmentValidUpTo(_episodeVO.getValidUpto());
				//if(_TreatmentCategoryChangeVO[i].getIsIpdFlag().equals(RegistrationConfig.NO))
					_TreatmentCatChangeDAO.updateTreatmentCategory(_dailyPatientVO, _userVO);

				// /////////inserting into secondary category change dtl////////
			//	if (x >= 1)
				//{

					// /////////check if category expiry is extended or category changed////////

					/*if ((_TreatmentCategoryChangeVO[i].getPatNewSecondaryCatCode().equals(_TreatmentCategoryChangeVO[i].getPatPrevSecondaryCatCode()))
							&& (!_TreatmentCategoryChangeVO[i].getExpiryDate().equals("")))
					{
						_TreatmentCatChangeDAO.createExtendValidUpto(_TreatmentCategoryChangeVO[i], _userVO,"1");
					}
					else
					{*/
						_TreatmentCategoryChangeVO[i].setExpiryDate(_dailyPatientVO.getTreatmentValidUpTo());
						_TreatmentCatChangeDAO.createExtendValidUpto(_TreatmentCategoryChangeVO[i], _userVO,"2");
					//}

					y++;
				//}
			}
		}
		
		if(TreatmentCategoryRevokeVO!=null)
		{
			for(int i=0;i<TreatmentCategoryRevokeVO.length;i++)
			{
				/*if(TreatmentCategoryRevokeVO[i].getIsIpdFlag().equals(RegistrationConfig.NO))
				{*/
					_dailyPatientVO.setPatCrNo(TreatmentCategoryRevokeVO[i].getPatCrNo());
					_dailyPatientVO.setEpisodeCode(TreatmentCategoryRevokeVO[i].getEpisodeCode());
					_dailyPatientVO.setPatSecondaryCatCode(TreatmentCategoryRevokeVO[i].getPatNewSecondaryCatCode());
					_dailyPatientVO.setEpisodeVisitNo(TreatmentCategoryRevokeVO[i].getEpisodeVisitNo());
					
					_TreatmentCatChangeDAO.updateTreatmentCategory(_dailyPatientVO, _userVO);
					
				//}
					
				
				_episodeVO.setPatCrNo(TreatmentCategoryRevokeVO[i].getPatCrNo());
				_episodeVO.setEpisodeCode(TreatmentCategoryRevokeVO[i].getEpisodeCode());
				_episodeVO.setPatSecondaryCatCode(TreatmentCategoryRevokeVO[i].getPatNewSecondaryCatCode());
				_episodeVO.setEpisodeVisitNo(TreatmentCategoryRevokeVO[i].getEpisodeVisitNo());
				_episodeVO.setSecCatExpDate("");
				
				_TreatmentCatChangeDAO.updateRevokeTreatmentCat(_episodeVO, _userVO);
				
				_TreatmentCatChangeDAO.createExtendValidUpto(TreatmentCategoryRevokeVO[i], _userVO,"3");
				
			}
		}

	}
	catch (HisUpdateUnsuccesfullException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisUpdateUnsuccesfullException();
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException(e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisDataAccessException(e.getMessage());
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisException(e.getMessage());
	}
	finally
	{
		System.out.println("y = " + y);
		tx.close();
	}
	if (y >= 1) return true;
	else return false;
}
public boolean changeIPDTreatmentCategory(ChangeTreatmentCategoryVO[] _admittedPatientVO, UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	int x = 0, y = 0;
	DailyPatientVO _dailyPatientVO = new DailyPatientVO();
	EpisodeVO _episodeVO = new EpisodeVO();

	try
	{
		System.out.println("Begining transaction");
		tx.begin();
		ChangeTreatmentCategoryDAO _TreatmentCatChangeDAO = new ChangeTreatmentCategoryDAO(tx);
		for (int i = 0; i < _admittedPatientVO.length; i++)
		{
		_TreatmentCatChangeDAO.createExtendValidUpto(_admittedPatientVO[i], _userVO,"1");
		//_TreatmentCatChangeDAO.updateAdmissionDetail(_admittedPatientVO[i], _userVO);
		}
	}
	
	catch (HisUpdateUnsuccesfullException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisUpdateUnsuccesfullException();
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException(e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisDataAccessException(e.getMessage());
	}
	catch (Exception e)
	{
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisException(e.getMessage());
	}
	finally
	{
		System.out.println("y = " + y);
		tx.close();
	}
	if (y >= 1) return true;
	else return false;
	}
//End:Sheeldarshi
/*  ## 		Modification Log							
	##		Modify Date				:29thJan'15 
	##		Reason	(CR/PRS)		:Image upload integration in patient detail module
	##		Modify By				:Sheeldarshi */
public PatientVO ExternalPatRegistration( PatientVO objPatientVO_p, UserVO objUserVO_p,  HttpServletRequest objRequest_p) throws HisDuplicateRecordException
{

	JDBCTransactionContext tx = new JDBCTransactionContext();
	HisDAO objHisDAO = null;
	boolean isDuplicateReg=false;
	String[] crNoArr = null;
	String strPatIdErrMsg="";
	String patPrimaryCatGrp="";
	//synchronized (PatientDAO.class)
	//{
	try
	{
		objHisDAO = new HisDAO("Registration","PatientBO");
		
		tx.begin();
		Map essentialMap = new HashMap();

		PatientDAO patDao = new PatientDAO(tx);
		AddressDAO addDao = new AddressDAO(tx);
			
		RegEssentialDAO regEssentialDAO = new RegEssentialDAO(tx);
		
		PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
		//create object of patient temp DAO
		PatientTempDetailDAO patTempDAO = new PatientTempDetailDAO(tx);
		DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);

		List allPatientVoList=new ArrayList();

		
		//int arrEpisodeVOLength=objArrEpisodeVO_p.length;
		
		
		
		PatientIdVO objPatientIdVO = new PatientIdVO();
		BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
		CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();

		/*for (int i = 0; i < arrEpisodeVOLength; i++)
		{*/
			EpisodeReferVO objEpisodeReferVO = new EpisodeReferVO();
			EpisodeRefDtlVO objEpisodeRefDtlVO = new EpisodeRefDtlVO();
			
			objPatientVO_p.setIsValid(Config.IS_VALID_ACTIVE);
			objPatientVO_p.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
			objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
			objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			objPatientVO_p.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			objPatientVO_p.setIsBroughtDead("0");
		
			objPatientVO_p.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
			objPatientVO_p.getPatAddress().setSeatId(objPatientVO_p.getSeatId());
			AddressVO addressVO=objPatientVO_p.getPatAddress();
			addressVO.setPatIsUrban(objPatientVO_p.getPatIsUrban());
			
			
			///temp code for trapping error	(Has to delete)
			

			
			//dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
			//PatientBOHelper.setNewPatRegEpisodeVO(objPatientVO_p);
			objPatientVO_p.setIsValid(Config.IS_VALID_ACTIVE);
			//objPatientVO_p.setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
			
			
			//Generation of Unique Health ID Added by Singaravelan on 09-Oct-2014
			String[] dob={"1","Jan","1900"};
			String tempDob="";
			if(objPatientVO_p.getPatDOB()!=null&&!objPatientVO_p.getPatDOB().equals("")){
				dob=objPatientVO_p.getPatDOB().split("-");
			}
			else{
				tempDob=regEssentialDAO.getBirthYear(objPatientVO_p, objUserVO_p);
				if(tempDob!=null){
					dob=tempDob.split("-");
				}
			}
			//HealthId hid=new HealthId("Ramesh","B/o Kumar","A S Kumar","Namita Kumar","Male",1984,20,260);
			HealthId hid=new HealthId(objPatientVO_p.getPatFirstName(),objPatientVO_p.getPatLastName(),objPatientVO_p.getPatGuardianName(),objPatientVO_p.getPatMotherName(),objPatientVO_p.getPatGender(),Integer.parseInt(dob[2]),Integer.parseInt(objPatientVO_p.getPatAddStateCode()),Integer.parseInt(objPatientVO_p.getPatAddDistrictCode()),objPatientVO_p.getPatAddMobileNo());
			GenerateID gid=new GenerateID(hid);
			System.out.println("------HealthId----"+gid.generateSecondaryHealthID()+"------------");	
			objPatientVO_p.setPatSecUHID(gid.generateSecondaryHealthID());

			if(objPatientVO_p.getAsNewPatient()!=null&&objPatientVO_p.getAsNewPatient()!="1"){//To Bypass the Secondary UHID Check to Save As New Patient Added by Singaravelan on 13-Oct-2014
			if(objPatientVO_p.getIsDuplicate()!=null && objPatientVO_p.getIsDuplicate().equals("1")){
				// Do Nothing
			}else{
					//Check Duplicacy using Unique Health ID and return CRNO list having the same UHID Added by Singaravelan on 09-Oct-2014
					crNoArr=regEssentialDAO.checkDuplicateRegistrationUHID(objPatientVO_p, objUserVO_p);
					if(crNoArr!=null)
					{
						PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
						
						for(int i=0;i<crNoArr.length;i++)
						{
							PatientVO vo=new PatientVO();
							vo.setPatCrNo(crNoArr[i]);
							patientVOArr[i]=vo	;
						}
						for(int i=0;i<crNoArr.length;i++)
						{
							allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], objUserVO_p));	
						}
						essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
					}
					if(crNoArr!=null)
					{	
						WebUTIL.setMapInSession(essentialMap, objRequest_p,"ExternalPatientRegistrationAction");
						throw new HisDuplicateRecordException("Duplicate Registration");
					}
			}
			}
					
					
			//dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
				
			//generating the Cr Number
			String CrNO=patDao.generateCrNumber(objUserVO_p);
			objPatientVO_p.setPatCrNo(CrNO);
			objPatientVO_p.getPatAddress().setPatCrNo(objPatientVO_p.getPatCrNo());
			objPatientVO_p.setSeatId(objUserVO_p.getSeatId());
			objPatientVO_p.setIpAddress(objUserVO_p.getIpAddress());
			//objArrEpisodeVO_p[i].setPatCrNo(objPatientVO_p.getPatCrNo());
										
		
			if(objPatientVO_p.getIsActualDob().equals("1")){
				objPatientVO_p.setPatAgeWithUnit(dailyPatDao.getAgeFromDOB(objPatientVO_p.getPatDOB()));
			}else{
				objPatientVO_p.setPatAgeWithUnit(objPatientVO_p.getPatAge()+" "+objPatientVO_p.getPatAgeUnit());
			}
			objPatientVO_p.setPatAgeWithUnit(objPatientVO_p.getPatAgeWithUnit());
			
			//insert into patient
			objPatientVO_p = patDao.savePatientDtl(objHisDAO,objPatientVO_p, objUserVO_p,"1");
			
			//Added by Garima on 29 March 2019 for PHRMS HealthRecordIntegration
			objPatientVO_p = patDao.savePatDetailForMyHealthRecordIntegration(objHisDAO,objPatientVO_p, objUserVO_p,"1","1");
			
			//insert into address dteial
			addDao.saveAddressDtl(objHisDAO,addressVO, objUserVO_p,"1");
			
			
			//HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
	
			HelperMethods.populate(objPatientIdVO, objPatientVO_p);
			
			// For Patient Primary Category Id (like Employee Id etc)
			//if(objPatientVO_p.getIsIdRequired()!=null && objPatientVO_p.getIsIdRequired().equals("2")
					//&& 
			if(objPatientVO_p.getPatIdNo()!=null && !objPatientVO_p.getPatIdNo().equals("") ){
				
				strPatIdErrMsg="Patient Id is not defined";
				if(objPatientVO_p.getPatIdNo().equals("undefined"))
					throw new HisApplicationExecutionException(strPatIdErrMsg);
				
				if(objPatientVO_p.getPatPrimaryCatGrp().equals("1"))
				{
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
					objPatientIdVO.setPatDocType("99");
					objPatientIdVO.setPatIsAlternateId("0");
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
				}					
				else
				{
					if(objPatientVO_p.getPatCatDocIsAlternateId()!=null && objPatientVO_p.getPatCatDocIsAlternateId().equals("1"))
					{
						objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
						objPatientIdVO.setPatDocType(objPatientVO_p.getPatCatDocCode());
						objPatientIdVO.setPatIsAlternateId(objPatientVO_p.getPatCatDocIsAlternateId());
						objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "10");
					}
				}
				
				if(objPatientVO_p.getPatCatDocIsAlternateId()!=null && objPatientVO_p.getPatCatDocIsAlternateId().equals("1"))
				{
					objPatientIdVO.setPatIdNo(objPatientVO_p.getPatIdNo());
					objPatientIdVO.setPatDocType(objPatientVO_p.getPatCatDocCode());
					objPatientIdVO.setPatIsAlternateId(objPatientVO_p.getPatCatDocIsAlternateId());
					objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "10");
				}
				
				
				System.out.println("HiddenCatOrRegstrdPopupFlag :"+objPatientVO_p.getHiddenCatOrRegstrdPopupFlag());		
				if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag()!=null && !objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("")){
					objPatientIdVO.setPatIsAlternateId("0");

				}
				
			}
		
			
			objPatientIdVO.setPatIdNo(objPatientVO_p.getPrevCrNo());
			//Save Data in DAO.
			if(objPatientVO_p.getHiddenCatOrRegstrdPopupFlag().equals("A"))
			{
				if(objPatientVO_p.getAlreadyRegisteredFlag().equals("1"))
				{
					PatientTempDetailDAO.updateStatus("2",objPatientVO_p.getPatCrNo(), objPatientIdVO.getPatIdNo(), "2", objUserVO_p.getHospitalCode(), objHisDAO);
				}
				else
				{
					PatientTempDetailDAO.updateStatus("1",objPatientVO_p.getPatCrNo(), objPatientIdVO.getPatIdNo(), "2", objUserVO_p.getHospitalCode(), objHisDAO);
				}
			}
			
		
			
			// For AlterNate Id
			if(objPatientVO_p.getPatCardNo()!=null && !objPatientVO_p.getPatCardNo().equals("")){
				System.out.println("For ALterNate ID -->> PatCardNo :"+objPatientVO_p.getPatCardNo());
				
				objPatientIdVO.setPatIdNo(objPatientVO_p.getPatCardNo());
				objPatientIdVO.setPatIsAlternateId("1");
				objPatientIdVO.setPatDocType(objPatientVO_p.getPatDocType());
				objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO,objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "1");
			}
			
			// For NationalId/ Aadhar No
			if(objPatientVO_p.getPatNationalId()!=null && !objPatientVO_p.getPatNationalId().equals("")){
				System.out.println("For NationalId/ Aadhar No -->> PatCardNo :"+objPatientVO_p.getPatNationalId());
				objPatientIdVO.setPatIdNo(objPatientVO_p.getPatNationalId());
				objPatientIdVO.setPatDocType("98");
				objPatientIdVO.setPatIsAlternateId("0");
				objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(),objUserVO_p, "3");
			}
			
			
			
		/***********************************************/	
			synchronized (objHisDAO) {
				objHisDAO.fire();
			}
			//objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL,objArrEpisodeVO_p[i].getDepartmentUnitCode(),objUserVO_p));
			//objArrEpisodeVO_p[i].setDisclaimer("Test Disclaimer1@Test Disclaimer2@Test Disclaimer1@0@center");
				
		//}

	}
	catch(HisDuplicateRecordException e){
		tx.rollback();
		throw new HisDuplicateRecordException("Duplicate Registration");
	}
	catch (HisInvalidTokenNumberException e)
	{
		tx.rollback();
		//e.printStackTrace();
		throw new HisInvalidTokenNumberException(e.getMessage());
	}
	catch (HisAppointmentNotAvailableException e)
	{
		tx.rollback();
		//e.printStackTrace();
		
		throw new HisAppointmentNotAvailableException(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		//e.printStackTrace();
		
		throw new HisApplicationExecutionException("Error, "+strPatIdErrMsg);
	}
	catch (HisDataAccessException e)
	{
		//e.printStackTrace();
		
		tx.rollback();
		throw new HisDataAccessException("Data Access Error, Contact System Administrator");
	}
	catch (Exception e)
	{
		e.printStackTrace();
		
		tx.rollback();
		throw new HisApplicationExecutionException("Error, Contact System Administrator");
	}
	finally
	{
		if (objHisDAO != null) {
			//objHisDAO.free();
			objHisDAO = null;
		}
		tx.close();
		
	}
	//}
	return objPatientVO_p; // <<<
}
//End:Sheeldarshi


	//To Get the List Patient Category Change Log Added by Pushpa on 29_Jan_2015	
	
	public PrimaryCategoryChangeVO[] getpatcatchangeLog (String crNo,UserVO objUserVO_p,PrimaryCategoryChangeVO catchangevo)
	{
		PrimaryCategoryChangeVO[] objPrimaryCategoryChangeVO_p;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		System.out.println("PatientBO :: getpatcatchangeLog()");
		try
		{
			tx.begin();
			PrimaryCategoryChangeDAO objPatcatchangelogDtlDAO = new PrimaryCategoryChangeDAO(tx);
			objPrimaryCategoryChangeVO_p = objPatcatchangelogDtlDAO.getpatcatchangeLog(crNo,objUserVO_p ,catchangevo);
			System.out.println("objPrimaryCategoryChangeVO_p.size :"+objPrimaryCategoryChangeVO_p!=null?objPrimaryCategoryChangeVO_p.length:0);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objPrimaryCategoryChangeVO_p;
	}
	
	/* #Start				:Sheeldarshi 
	#Modify Date(CR/PRS)	:28thMay'15 
	#Reason					: UAT Test Report Requirements:Requirement 8
	 */
	public EpisodeRefDtlVO[] getOpdReferEpisodeDtl(String strCrNo_p, String strDeptUnitCode_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeRefDtlVO[] objArrRefEpisodeVO ={};
		
		EpisodeRefDtlVO[] arrVO ={};
		try
		{
			System.out.println("PatientBO :: getReferEpisodeDtl()");
			tx.begin();
			EpisodeRefDtlDAO episodeRefDao = new EpisodeRefDtlDAO(tx);
			objArrRefEpisodeVO = episodeRefDao.getRefEpisodeDtlByCrNoByDeptByUnit(strCrNo_p,strDeptUnitCode_p, objUserVO_p,"2");
								
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrRefEpisodeVO;
	}
	
//End
	
	public BeneficiaryPatientVO[] getCreditBenDtl(String strCrNo_p, UserVO objUserVO_p, String strMode_p)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		BeneficiaryPatientVO[] _beneficiaryPatientVO;
		try
		{
			System.out.println("PatientBO :: retrieveAllOpenOPDEpisodes()");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			// populate the addressVO with required details
			_beneficiaryPatientVO = BeneficiaryPatientDAO.retrieveCreditBeneficiaryDtlsByCrNo(strCrNo_p,objUserVO_p,strMode_p);// .retrieveOldPatientEpisodes(crNo,objUserVO_p);
			//System.out.println("objArrEpisodeVO.length :"+objArrEpisodeVO!=null?objArrEpisodeVO.length:0);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _beneficiaryPatientVO;
	}
	
	//By Mukund on 20.09.2016 for Audit log
			public List getauditLog (String crNo,UserVO objUserVO_p,PrimaryCategoryChangeVO catchangevo)
			{
				PrimaryCategoryChangeVO[] objPrimaryCategoryChangeVO_p;
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List alRecord = new ArrayList();
				System.out.println("PatientBO :: getpatcatchangeLog()");
				try
				{
					tx.begin();
					PrimaryCategoryChangeDAO objPatcatchangelogDtlDAO = new PrimaryCategoryChangeDAO(tx);
					alRecord = objPatcatchangelogDtlDAO.getauditLog(crNo,objUserVO_p ,catchangevo);
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return alRecord;
			}
			//End
			
			public MlcVO getMlcDetailByCrNo(MlcVO objMlcVO_p,String strCrNo_p,UserVO objUserVO_p)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				MlcVO objMlcVO=null;
				
				try
				{
					tx.begin();
					MlcDAO objPatMlcDtlDAO=new MlcDAO(tx);
					objMlcVO=objPatMlcDtlDAO.getMlcDetailByCrNo(objMlcVO_p,strCrNo_p, objUserVO_p,"4");
				}
				catch (HisRecordNotFoundException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisRecordNotFoundException("");
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return objMlcVO;
			}
			
			public EpisodeRefDtlVO[] getOpdExtReferEpisodeDtl(PatientVisitSUP objPatVisitSUP_p, String strDeptUnitCode_p, UserVO objUserVO_p)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeRefDtlVO[] objArrRefEpisodeVO ={};
				
				EpisodeRefDtlVO[] arrVO ={};
				try
				{
					System.out.println("PatientBO :: getReferEpisodeDtl()");
					tx.begin();
					EpisodeRefDtlDAO episodeRefDao = new EpisodeRefDtlDAO(tx);
					objArrRefEpisodeVO = episodeRefDao.getExtRefEpisodeDtlByCrNoByDeptByUnit(objPatVisitSUP_p,strDeptUnitCode_p, objUserVO_p,"3");
										
				}// end of try
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisNotAnOPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnOPDcaseException();
				}
				catch (HisNotAnIPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnIPDcaseException();
				}
				catch (HisDeadPatientException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDeadPatientException();
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return objArrRefEpisodeVO;
			}
			
		//End
			
			public EpisodeVO[] newExtDepartmentVisitStamp(EpisodeVO[] objArrEpisodeVO_p, PatientVO objPatientVO_p, EpisodeRefDtlVO episodeRefVO, UserVO objUserVO_p,PatientVO _oldPatientVO )
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				HisDAO objHisDAO=null;
				try
				{
					tx.begin();
			
					DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
					EpisodeDAO episodeDao = new EpisodeDAO(tx);
					PatientDAO objPatientDAO = new PatientDAO(tx);
					
					DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
					DirectChageDetailVO[] directChargeVO = new DirectChageDetailVO[objArrEpisodeVO_p.length];
					RenewalDetailVO[] renewDetailVO = new RenewalDetailVO[objArrEpisodeVO_p.length];
					PatientIdVO objPatientIdVO = new PatientIdVO();
					EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
					RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(tx);
					PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
					BeneficiaryPatientDAO benPatientDAO=new BeneficiaryPatientDAO(tx);
					CreditAvailDtlDAO creditAvailDtlDAO=new CreditAvailDtlDAO(tx);
					BeneficiaryPatientVO objBenPatVO=new BeneficiaryPatientVO();
					CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();
					
					String amount = objArrEpisodeVO_p[0].getPatAmountCollected();
					//String amount = objArrEpisodeVO_p[0].getPatActualAmount();
					String strExpiryDate="";	//,strOldExpiryDate="",strNewExpiryDate="";
					boolean flagPatSaveStatus = false;
					String patPrimaryCatGrp="";
					
					System.out.println("PatientBO :: newDepartmentVisitStamp()");
					objHisDAO = new HisDAO("Registration","PatientBO");
					
						//if(!objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT)){
					if(objPatientVO_p.getPatStatusCode()!=null && !objPatientVO_p.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT)){
							flagPatSaveStatus=true;
							objPatientVO_p.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_OUTPATIENT);
						}
					
						// EpisodeRefDtlVO objEpisodeRefDtlVO_p=new EpisodeRefDtlVO();
					if(objPatientVO_p.getDepartmentCode().length()!=3)
					{
						objPatientVO_p.setDepartmentCode(objPatientVO_p.getDepartmentCode().split("#")[0]);
					}

						////updating patient detail if department wise mandatory fields are captured
						
						for (int i = 0; i < objArrEpisodeVO_p.length; i++)
						{
							
							DailyPatientVO dailyPatVO = new DailyPatientVO();
							
							strExpiryDate=objPatientDAO.getRegExpiry(objUserVO_p, objPatientVO_p.getRenewalConfig(), objPatientVO_p.getDepartmentUnitCode());
							
							// Create DailyPatientVO from patientVO: Populate it
							HelperMethods.populate(dailyPatVO, objPatientVO_p);
							dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
							
							// populate the episodeVO with the general details
							PatientBOHelper.setNewPatRegEpisodeVO(objArrEpisodeVO_p[i]);
							objArrEpisodeVO_p[i].setTariffId(objUserVO_p.getTariffID());
							String referMlcNo="";
							referMlcNo=episodeRefVO.getMlcNo();
							
							objArrEpisodeVO_p[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
							objArrEpisodeVO_p[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
							
							objArrEpisodeVO_p[i].setPatCrNo(dailyPatVO.getPatCrNo());
							objArrEpisodeVO_p[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
							objArrEpisodeVO_p[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
							
							objArrEpisodeVO_p[i].setIsValid(Config.IS_VALID_ACTIVE);
							
							// populate this dailyPatient VO with objArrEpisodeVO_p[i]
							HelperMethods.populatetToNullOrEmpty(dailyPatVO, objArrEpisodeVO_p[i]);
							//System.out.println("objPatientVO_p.getDepartmentCode() [In PatientBO]:"+objPatientVO_p.getDepartmentCode());
							//System.out.println("objArrEpisodeVO_p[i].getDepartmentCode()  [In PatientBO]:"+objArrEpisodeVO_p[i].getDepartmentCode());
							/*objPatientVO_p.setDepartmentCode(objPatientVO_p.getDepartmentUnitCode().substring(0,3));*/
							/*dailyPatVO.setDepartmentCode(objPatientVO_p.getDepartmentUnitCode().substring(0,3));
							dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());*/
							
							dailyPatVO.setIsReferred(objPatientVO_p.getIsReferred());
							
							//Code for setting MLC Flag Referred if Patient is refered from MLC Episode
							if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
							{
								if (episodeRefVO.getMlcFlag()!=null && (episodeRefVO.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_PROVISIONAL) || episodeRefVO.getMlcFlag().equals(RegistrationConfig.MLC_FLAG_CONFIRMED)))
								 dailyPatVO.setMlcFlag(RegistrationConfig.MLC_FLAG_REFFERED);
							}
							
							///In case of new dept visit is old is false
							dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
							dailyPatVO.setEpisodeVisitNo("1");
							
							//dailyPatVO.setDepartmentCode(objPatientVO_p.getDepartmentCode());
							//dailyPatVO.setDepartmentUnitCode(objPatientVO_p.getDepartmentUnitCode());
							
							dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, objUserVO_p,RegistrationConfig.ROSTERTYPE_OPD,RegistrationConfig.UNIT_TYPE_GENERAL, "NewExtDeptVisit");
							
							dailyPatVO.setBillNo(dailyPatDao.generateBillNo(objUserVO_p, "1"));
							episodeRefVO.setToDepartmentUnit(dailyPatVO.getDepartmentUnit());
							episodeRefVO.setToDepartmentUnitCode(dailyPatVO.getDepartmentUnitCode());
							episodeRefVO.setToWard(dailyPatVO.getRoom());
							episodeRefVO.setToWardCode(dailyPatVO.getRoomCode());
							
							
							dailyPatVO.setEpisodeCode(dailyPatDao.generateEpisodeCode(objUserVO_p, dailyPatVO.getPatCrNo(),dailyPatVO.getDepartmentUnitCode()));
							
							// Setting Expiry Date(General,Special & Casuality) in PatientVo and so DailyPatientVO
							PatientBOHelper.setExpiryInPatientVoNDailyPatVoNEpisodeVo(objPatientVO_p, dailyPatVO, objArrEpisodeVO_p[i], strExpiryDate);
							
							
							dailyPatDao.saveDailyPatientDtl(objHisDAO, dailyPatVO, objUserVO_p, "1",RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
							HelperMethods.populate(objArrEpisodeVO_p[i], dailyPatVO);
							if(dailyPatVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG))
								objArrEpisodeVO_p[i].setMlcNo(referMlcNo);
							
							//Create new entry in Episode detail
							
							if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
							{
								if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
								{
									objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
									objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
								}
								else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
								{
									objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
									objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
								}

							}
							else
							{
								objArrEpisodeVO_p[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
								objArrEpisodeVO_p[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
							}

							objArrEpisodeVO_p[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
							
							////setting visit number//
							
							objArrEpisodeVO_p[i].setEpisodeVisitNo("1");
							objArrEpisodeVO_p[i].setEntryDate(objPatientVO_p.getSystemDate());
							
							
							
							System.out.println("----Pat Ren Req--------"+objPatientVO_p.getOpdRenewalRequired()+"------------");
							System.out.println("----Date--------"+objPatientVO_p.getExpiryDate()+"------------");
							System.out.println("objPatientVO_p.getDepartmentUnitCode() :"+objPatientVO_p.getDepartmentUnitCode());
							System.out.println("objArrEpisodeVO_p[i].getDepartmentUnitCode() :"+objArrEpisodeVO_p[i].getDepartmentUnitCode());
							
							
							// if patient is registered from some other hospital and visit another hospital
							// Data is saved to Patient Hospital table including expiry..
							if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1"))
							{
								PatientHospitalDtlVO objPatientHospitalDtlVO = new PatientHospitalDtlVO();
								HelperMethods.populate(objPatientHospitalDtlVO, objPatientVO_p);
								PatientHospitalDtlDAO objPatientHospitalDtlDAO = new PatientHospitalDtlDAO(tx);
								
								//if data is not found in hospital table then new record is inserted for that patient
								if(objPatientVO_p.getOtherHospitalDataFound()==null || !objPatientVO_p.getOtherHospitalDataFound().equals("1")){
									//Calling DAO
									objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "1");
									
									if(!objPatientVO_p.getPatIdNo().equals("")){//isEmpty modified
										HelperMethods.populate(objPatientIdVO, objPatientVO_p);
										objPatientIdVO.setPatDocType("99");
										//Calling DAO
										objPatientIdDetailDAO.savePatientIdDtl(objHisDAO, objPatientIdVO, objPatientVO_p.getPatPrimaryCatCode(), objPatientVO_p.getPatPrimaryCatGrp(), objUserVO_p, "4");
									}
								}
								//else only expiry updation is done
								else{
									if(objPatientVO_p.getRenewalConfig()!=null && objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("1")|| objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("2"))
									{
										if(objPatientVO_p.getOpdRenewalRequired()!=null && 
												(objPatientVO_p.getOpdRenewalRequired().equals("1")|| objPatientVO_p.getOpdRenewalRequired().equals("2")))
										{
											//objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
											//PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
											//Calling PatientDAO
											objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "2");
										}else{
											if(flagPatSaveStatus)
												objPatientHospitalDtlDAO.savePatientHospitalDtlDAO(objHisDAO, objPatientHospitalDtlVO, objUserVO_p, "3");
										}
									}
								}
							}
							else
							{
								if(objPatientVO_p.getRenewalConfig()!=null && objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("1")|| objPatientVO_p.getRenewalConfig().getStrRenewalType().equals("2"))
								{
									if(objPatientVO_p.getOpdRenewalRequired()!=null && 
											(objPatientVO_p.getOpdRenewalRequired().equals("1")|| objPatientVO_p.getOpdRenewalRequired().equals("2")))
									{
										//objPatientVO_p.setExpiryDate(strExpiryDate);	// Check Whether this required
										//PatientBOHelper.setExpiryInPatientVO(objPatientVO_p, strExpiryDate);
										//Calling PatientDAO
										objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "4");
									}else{
										if(flagPatSaveStatus)
											objPatientDAO.savePatientDtl(objHisDAO, objPatientVO_p, objUserVO_p, "6");
									}
								}
							}
							
							 
							//Calling EpisodeDAO
							objArrEpisodeVO_p[i].setStrRegFlag(RegistrationConfig.DAILYPATIENT_REG_NEWVISIT);
							objArrEpisodeVO_p[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
							objArrEpisodeVO_p[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate()); 
							episodeDao.saveEpisodeDtl(objHisDAO, objArrEpisodeVO_p[i], objUserVO_p, "1");
							/**Call to save episode details in 'hrgt_seccat_change_dtl'*/
							secondaryCategoryChangesForEveryEpisodeCreated( tx, objUserVO_p, objArrEpisodeVO_p[i], objPatientVO_p);
							/***/

							if (objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
							{
								
								String refEpisode = episodeRefVO.getEpisodeCode();
								String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
								String serialNo = episodeRefVO.getSerialNo();
								HelperMethods.populate(episodeRefVO, objArrEpisodeVO_p[i]);
								episodeRefVO.setSystemIPAddress(objPatientVO_p.getSystemIPAddress());

								if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
								{
									episodeRefVO.setEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
									episodeRefVO.setEpisodeVisitNo("1");

								}
								if (episodeRefVO.getIsRefferInOut()!=null && episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
								{
									episodeRefVO.setToDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
									episodeRefVO.setToDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
									episodeRefVO.setToEpisodeCode(objArrEpisodeVO_p[i].getEpisodeCode());
									episodeRefVO.setToEpisodeVisitNo("1");
									episodeRefVO.setEpisodeCode(refEpisode);
									episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
									episodeRefVO.setExternalHospitalCode("");
									episodeRefVO.setSerialNo(serialNo);
								}

								//Calling EpisodeRefDtlDAO
								if (objPatientVO_p.getIsPatReferByList() != null
										&& objPatientVO_p.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
								{
									
									episodeRefDtlDAO.saveExtEpisodeRefDtl(objHisDAO,objPatientVO_p, episodeRefVO, objUserVO_p,"3");	// for updating Acceptance Date
								}
								else
								{
									if(objPatientVO_p.getIsReferred()!=null && objPatientVO_p.getIsReferred().equals("1"))
										episodeRefDtlDAO.saveExtEpisodeRefDtl(objHisDAO,objPatientVO_p, episodeRefVO, objUserVO_p, "1");
								}
								
							}
							//Modified to add the details on renewal table while new department visit cum renewal by Singaravelan on 08-10-13
							
							//////////////////////////////////////////
							
							//////////////////////////////////////////
							System.out.println("----Amount-----"+amount+"-----------");
							//if (amount != null && !amount.equals("") && !amount.equals("0") && !amount.equals(RegistrationConfig.PAT_CAT_FREE_FEES))
							if (amount != null)
							{
								directChargeVO[i]= new DirectChageDetailVO();
								directChargeVO[i].setTariffId(objUserVO_p.getTariffID());
								directChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
								directChargeVO[i].setModuleId(objUserVO_p.getModuleId());
								HelperMethods.populate(directChargeVO[i], objArrEpisodeVO_p[i]);
								directChargeVO[i].setPatAmountCollected(objPatientVO_p.getPatAmountCollected());
								directChargeVO[i].setSystemIPAddress(objPatientVO_p.getSystemIPAddress());
								directChargeVO[i].setBillNo(objArrEpisodeVO_p[i].getBillNo());
								directChargeVO[i].setCreditBillFlag(objPatientVO_p.getCreditBillFlag());
								directChargeVO[i].setCreditLetterRefNo(objPatientVO_p.getCreditLetterRefNo());
								directChargeVO[i].setCreditLetterDate(objPatientVO_p.getCreditLetterDate());
								directChargeVO[i].setRecieptType(RegistrationConfig.DIRECT_CHARGE_DTL_RECEIPT_TYPE_PATIENT_VISIT);
								
								directChargeVO[i].setStaffCardName(objPatientVO_p.getStaffCardName());
								directChargeVO[i].setCardNo(objPatientVO_p.getAgsNo()!=""?objPatientVO_p.getAgsNo():objPatientVO_p.getStaffCardNo());
								directChargeVO[i].setRelationWithStaff(objPatientVO_p.getRelationWithStaff());
								directChargeVO[i].setClientCode(objPatientVO_p.getClientCode());
								/*Start: Surabhi
								 * reason: for adding the organisation name */
								directChargeVO[i].setClientName(objPatientVO_p.getClientName());
								//End
								directChargeVO[i].setCardvalidityDate(objPatientVO_p.getCardvalidityDate());
								directChargeVO[i].setAgsDistrictCode(objPatientVO_p.getAgsDistrictCode());
								directChargeVO[i].setAgsCounterNo(objPatientVO_p.getAgsCounterNo());
								directChargeVO[i].setPatActualAmount(objPatientVO_p.getPatActualAmount());
								directChargeVO[i].setChargeType(objArrEpisodeVO_p[i].getEpisodeVisitType());

								
								/*if (!(directChargeVO[i].getPatAmountCollected().equals("0") || directChargeVO[i].getPatAmountCollected().equals("") || directChargeVO[i]
										.getPatAmountCollected() == null)) 
								{
									directChargeDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
								}*/
								
								//if (!(directChargeVO[i].getPatAmountCollected().equals("0") 
								//		&& !(directChargeVO[i].getPatAmountCollected().equals(RegistrationConfig.PAT_CAT_FREE_FEES))
								//		&& !directChargeVO[i].getPatAmountCollected().equals("") 
								//		&& directChargeVO[i].getPatAmountCollected() != null)) 
								//{
									directChargeDao.create(objHisDAO,directChargeVO[i], objUserVO_p);
								//}
								///Billing module integration
								//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], objUserVO_p);
								// createSlip(objPatientVO_p.getSystemIPAddress(),printData);
								
								if((objArrEpisodeVO_p[i].getOpdRenewalRequired()!=null && objArrEpisodeVO_p[i].getOpdRenewalRequired().equals("1"))|| 
										(objPatientVO_p.getOpdRenewalRequired()!=null && objPatientVO_p.getOpdRenewalRequired().equalsIgnoreCase("1")))
								{
									renewDetailVO[i] = new RenewalDetailVO();
									renewDetailVO[i].setPatCrNo(objArrEpisodeVO_p[i].getPatCrNo());
									renewDetailVO[i].setSeatId(objUserVO_p.getSeatId());
									renewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
									renewDetailVO[i].setSystemIPAddress(objUserVO_p.getIpAddress());
									renewDetailVO[i].setEntryDate(objArrEpisodeVO_p[i].getEntryDate());
									renewDetailVO[i].setDepartmentCode(objArrEpisodeVO_p[i].getDepartmentCode());
									renewDetailVO[i].setDepartmentUnitCode(objArrEpisodeVO_p[i].getDepartmentUnitCode());
									renewDetailVO[i].setHospitalCode(objUserVO_p.getHospitalCode());
									renewDetailVO[i].setOldExpiryDate(objArrEpisodeVO_p[i].getOldExpiryDate());
									renewDetailVO[i].setNewExpiryDate(strExpiryDate);
									renewDetailVO[i].setRenewalType(objPatientVO_p.getRenewalConfig().getStrRenewalType());

									renewDetailDAO.saveRenewalDtl(objHisDAO,renewDetailVO[i], objUserVO_p,"1");
								
								}	
								
								//Commented by Garima as in AIIMS Letter details will be entered through separate process for CM Reelief Fund Ctaegory--Start
								//For the Details Insertion in the Beneficiary Patient Dtl Table 
								/*if(objPatientVO_p.getPatPrimaryCatGrpCode()!=null && 
										(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) 
												//|| objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE)
										)){
									
									patPrimaryCatGrp=objPatientVO_p.getPatPrimaryCatGrpCode();
									//Need to remove this cond once the staff no constraints from the table is removed
									//if((objPatientVO_p.getStaffCardNo()!=null&&!objPatientVO_p.getStaffCardNo().equals(""))||
									//		(objPatientVO_p.getAgsNo()!=null&&!objPatientVO_p.getAgsNo().equals(""))){
									if(objPatientVO_p.getPatPrimaryCatGrpCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
										{
										objPatientVO_p.setClientCode(RegistrationConfig.Client_Code_Aarogya_Cat);
										objPatientVO_p.setClientName(RegistrationConfig.Client_Name_Aarogya_Cat);
										}
									
									
									HelperMethods.populate(objBenPatVO, objPatientVO_p);
									HelperMethods.setNullToEmpty(objBenPatVO);
									
									if(!objBenPatVO.getAgsNo().equals(""))
										objBenPatVO.setCardNo(objBenPatVO.getAgsNo());
									else if(!objBenPatVO.getStaffCardNo().equals(""))
										objBenPatVO.setCardNo(objBenPatVO.getStaffCardNo());
									else
										objBenPatVO.setCardNo("NA");
									
									if(objBenPatVO.getRelationWithStaff()!="1"){//Not self case
										objBenPatVO.setIsDependent("1");
										objBenPatVO.setDependentName(objPatientVO_p.getPatFirstName());
									}else{
										objBenPatVO.setIsDependent("0");
										objBenPatVO.setDependentName(objPatientVO_p.getStaffCardName());
									}
									objBenPatVO.setDependentRelationCode(objPatientVO_p.getRelationWithStaff());
									objBenPatVO.setDependentRelation(objPatientVO_p.getRelationNameWithStaff());
									objBenPatVO.setClientName(objPatientVO_p.getClientName());
								
									
									
									
									//commented by Garima on 19th July 2016 to save patient credit details--start
									//benPatientDAO.savePatientBeneficiaryDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
									//by mukund on 26.07.2016
									objBenPatVO.setPatActualAmount(objPatientVO_p.getPatActualAmount());
									benPatientDAO.savePatientCreditDtl(objHisDAO, objBenPatVO, objUserVO_p, "1");
									//end
									//}
														
								}
								*/
								//Commented by Garima as in AIIMS Letter details will be entered through separate process for CM Reelief Fund Ctaegory--End


								//To Insert the Details in the HBLT_CREDIT_TARIFF_AVAIL_DTL 
								if(patPrimaryCatGrp.equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY)){
									HelperMethods.populate(objCreditAvailDtlVO, directChargeVO[i]);
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
									
									objCreditAvailDtlVO.setTariffActualRate(directChargeVO[i].getPatActualAmount());
									objCreditAvailDtlVO.setPaidByClientAmt(directChargeVO[i].getPatActualAmount());
									objCreditAvailDtlVO.setPaidByPatAmt("0");
									objCreditAvailDtlVO.setNetAmount(directChargeVO[i].getPatActualAmount());
									objCreditAvailDtlVO.setClientCode(objPatientVO_p.getClientCode());
									objCreditAvailDtlVO.setClientName(objPatientVO_p.getClientName());

									//Need to include approved by here
									if(Double.valueOf(objCreditAvailDtlVO.getNetAmount())>0)
									creditAvailDtlDAO.saveCreditTarriffAvailDtl(objHisDAO, objCreditAvailDtlVO, objUserVO_p, "1");
								}
							
							}
							
							//****** query to fetch disclaimer for printing ************//*
								//objArrEpisodeVO_p[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, objArrEpisodeVO_p[i].getDepartmentUnitCode(), objUserVO_p));
								
							//**************************************************//*	
						
						
					}
					synchronized (objHisDAO) 
					{
					   objHisDAO.fire();
					}		
				}
				catch (HisInvalidTokenNumberException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisInvalidTokenNumberException(e.getMessage());
				}
				catch (HisAppointmentNotAvailableException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisAppointmentNotAvailableException(e.getMessage());
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
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
					System.out.println("Close the transaction...");
					// System.out.println("objPatientVO_p.getDepartment()::::"+objPatientVO_p.getDepartment());
					System.out.println("objArrEpisodeVO_p.getDepartment()::::" + objArrEpisodeVO_p[0].getDepartment());
					if (objHisDAO != null) {
						objHisDAO.free();
						objHisDAO = null;
					}
					tx.close();
				}
				return objArrEpisodeVO_p;
			}
			

			public EpisodeRefDtlVO[] retrieveAllExtOpenOPDEpisodes(PatientVisitSUP objPatVisitSUP_p,UserVO objUserVO_p, String strMode_p)
			{

				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeVO[] objArrEpisodeVO;
				EpisodeRefDtlVO[] objArrOpenOPDEpisode;

				try
				{
					System.out.println("PatientBO :: retrieveAllOpenOPDEpisodes()");
					tx.begin();
					EpisodeDAO episodeDao = new EpisodeDAO(tx);
					// populate the addressVO with required details
					objArrEpisodeVO = episodeDao.getExtEpisodeDtlByCrNoByDeptByUnit(objPatVisitSUP_p,objUserVO_p,strMode_p);// .retrieveOldPatientEpisodes(crNo,objUserVO_p);
					System.out.println("objArrEpisodeVO.length :"+objArrEpisodeVO!=null?objArrEpisodeVO.length:0);
					objArrOpenOPDEpisode = new EpisodeRefDtlVO[objArrEpisodeVO.length];
					for (int i = 0; i < objArrEpisodeVO.length; i++)
					{
						objArrOpenOPDEpisode[i] = new EpisodeRefDtlVO();
						HelperMethods.populate(objArrOpenOPDEpisode[i], objArrEpisodeVO[i]);
						objArrOpenOPDEpisode[i].setFromDepartmentCode(objArrEpisodeVO[i].getDepartmentCode());

						objArrOpenOPDEpisode[i].setFromDepartment(objArrEpisodeVO[i].getDepartment());
						objArrOpenOPDEpisode[i].setFromDepartmentUnit(objArrEpisodeVO[i].getDepartmentUnit());
						objArrOpenOPDEpisode[i].setFromDepartmentUnitCode(objArrEpisodeVO[i].getDepartmentUnitCode());
					}
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return objArrOpenOPDEpisode;
			}
	
			public EpisodeVO[] searchExtOldPatientEpisodesByCrNo(PatientVisitSUP objPatVisitSUP_p,String strCrNo_p,String strPatCatCode_p,String strRenewaltype_p, UserVO objUserVO_p, String visitType_p, String strMode_p)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeVO[] objArrEpisodeVO =
				{};
				
				EpisodeVO[] arrVO =
				{};
				try
				{
					tx.begin();
					EpisodeDAO episodeDao = new EpisodeDAO(tx);
					objArrEpisodeVO = episodeDao.getExtEpisodeDtlByCrNoByDeptByUnit(objPatVisitSUP_p,objUserVO_p,strMode_p);
					EpisodeVO[] objArrTmpEpisodeVO = new EpisodeVO[objArrEpisodeVO.length];
					boolean flagTrue=false;
					int j = 0;
					int len = 1;
					arrVO = new EpisodeVO[]
					{};

					for (int i = 0; i < objArrEpisodeVO.length; i++)
					{
						objArrEpisodeVO[i].setRenewalType(strRenewaltype_p);
						
						if(strMode_p!=null && strMode_p.equals("7")){
							if((objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC) ||
									objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_NON_MLC)))
										flagTrue= true;
						}else{
							if(objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
									objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
										flagTrue=true;
						}
						if (flagTrue)
						{
							objArrTmpEpisodeVO = new EpisodeVO[len];
							for (int k = 0; k < arrVO.length; k++)
							{
								objArrTmpEpisodeVO[k] = arrVO[k];
							}
							objArrTmpEpisodeVO[len - 1] = objArrEpisodeVO[i];
							arrVO = objArrTmpEpisodeVO;

							objArrTmpEpisodeVO[j] = objArrEpisodeVO[i];
							j++;
							len++;
						}
					}
					
					int m, n;
					for (m = 0, n = 0; m < objArrTmpEpisodeVO.length; m++, n++){
						objArrEpisodeVO[n] = objArrTmpEpisodeVO[m];
					}
					if (arrVO.length == 0){
						throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
					}
				}//end of try
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisNotAnOPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnOPDcaseException();
				}
				catch (HisNotAnIPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnIPDcaseException();
				}
				catch (HisDeadPatientException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDeadPatientException();
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return arrVO;
			}
			
			public EpisodeVO[] searchExtOldPatientEpisodesByCrNoByDept(PatientVisitSUP objPatVisitSUP_p,String strCrNo_p, String strDeptCode_p, String strRenewaltype_p, UserVO objUserVO_p, String visitType_p)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeVO[] objArrEpisodeVO =	{};

				EpisodeVO[] arrVO =	{};
				try
				{
					System.out.println("PatientBO :: searchOldPatientEpisodesByCrNoByDept()");
					tx.begin();
					EpisodeDAO episodeDao = new EpisodeDAO(tx);
					objArrEpisodeVO = episodeDao.getExtEpisodeDtlByCrNoByDeptByUnit(objPatVisitSUP_p,objUserVO_p,"4");
					EpisodeVO[] objArrTmpEpisodeVO = new EpisodeVO[objArrEpisodeVO.length];
					int j = 0;
					int len = 1;
					arrVO = new EpisodeVO[]
					{};

					for (int i = 0; i < objArrEpisodeVO.length; i++)
					{
						objArrEpisodeVO[i].setRenewalType(strRenewaltype_p);
					
						if (objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
								objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
						{
							objArrTmpEpisodeVO = new EpisodeVO[len];
							for (int k = 0; k < arrVO.length; k++){
								objArrTmpEpisodeVO[k] = arrVO[k];
							}
							objArrTmpEpisodeVO[len - 1] = objArrEpisodeVO[i];
							arrVO = objArrTmpEpisodeVO;

							objArrTmpEpisodeVO[j] = objArrEpisodeVO[i];
							j++;
							len++;
						}
					}
					int m, n;
					for (m = 0, n = 0; m < objArrTmpEpisodeVO.length; m++, n++){
						objArrEpisodeVO[n] = objArrTmpEpisodeVO[m];
					}
					if (arrVO.length == 0){
						throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
					}
				}// end of try
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisNotAnOPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnOPDcaseException();
				}
				catch (HisNotAnIPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnIPDcaseException();
				}
				catch (HisDeadPatientException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDeadPatientException();
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return arrVO;
			}
			

			
			public EpisodeVO[] searchExtOldPatientEpisodesByCrNoByUnit(PatientVisitSUP objPatVisitSUP_p,String strCrNo_p, String strDeptUnitCode_p, String strRenewalType_p, UserVO objUserVO_p, String visitType_p)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeVO[] objArrEpisodeVO ={};
				
				EpisodeVO[] arrVO ={};
				try
				{
					System.out.println("PatientBO :: searchOldPatientEpisodesByCrNoByUnit()");
					tx.begin();
					EpisodeDAO episodeDao = new EpisodeDAO(tx);
					objArrEpisodeVO = episodeDao.getExtEpisodeDtlByCrNoByDeptByUnit(objPatVisitSUP_p,objUserVO_p,"5");
					EpisodeVO[] objArrTmpEpisodeVO = new EpisodeVO[objArrEpisodeVO.length];
					int j = 0;
					int len = 1;
					arrVO = new EpisodeVO[]
					{};

					for (int i = 0; i < objArrEpisodeVO.length; i++)
					{
						objArrEpisodeVO[i].setRenewalType(strRenewalType_p);
					
						if (objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
								objArrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
						{
							objArrTmpEpisodeVO = new EpisodeVO[len];
							for (int k = 0; k < arrVO.length; k++){
								objArrTmpEpisodeVO[k] = arrVO[k];
							}
							objArrTmpEpisodeVO[len - 1] = objArrEpisodeVO[i];
							arrVO = objArrTmpEpisodeVO;

							
							objArrTmpEpisodeVO[j] = objArrEpisodeVO[i];
							j++;
							len++;
						}
					}
					int m, n;
					for (m = 0, n = 0; m < objArrTmpEpisodeVO.length; m++, n++){
						objArrEpisodeVO[n] = objArrTmpEpisodeVO[m];
					}
					if (arrVO.length == 0){
						throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
					}
					// }
				}// end of try
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisNotAnOPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnOPDcaseException();
				}
				catch (HisNotAnIPDcaseException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisNotAnIPDcaseException();
				}
				catch (HisDeadPatientException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDeadPatientException();
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return arrVO;
			}
			/**By Mukund on 21.09.2017*/
			public Map getAllEpisodesForBarcodePrint(String crNo, UserVO userVO, String _choice,String _isReprint)
			{
				Map essentialMap = new HashMap();
				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeVO[] _arrEpisodeDupVO;
				EpisodeVO[] _arrEpisodeReprintVO;
				EpisodeVO[] _arrEpisodePrintCardVO;
				String reprintMode = "";

				try
				{
					tx.begin();
					EpisodeDAO episodeDao = new EpisodeDAO(tx);
					_arrEpisodeDupVO = episodeDao.DuplicateRetrieveByCrNo(crNo, userVO,"15");
					essentialMap.put("episodesForBarcodePrint", _arrEpisodeDupVO);
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return essentialMap;
			}
			
			public EpisodeVO saveBarcodePrintDtl(BarcodeSlipVO barcodeSlip_p, UserVO userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeVO episodeVO=new EpisodeVO();
				try
				{
					tx.begin();
					HisDAO daoObj= new HisDAO("Registration","PatientBO");

					BarcodeGenerationDAO objBarcodeGenerationDAO_p = new BarcodeGenerationDAO(tx);
					EssentialDAO essentialDAO = new EssentialDAO(tx);
					{
						objBarcodeGenerationDAO_p.saveBarcodeSlipDtl(daoObj, barcodeSlip_p, userVO, "1");
						daoObj.fire();
					}
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return episodeVO;
			}
			/**End on 21.09.2017*/
			public String getAadhaarDecrypted(String uuid, String CrNo)
			{

				JDBCTransactionContext tx = new JDBCTransactionContext();
				HisDAO objHisDAO = null;
				String decryptedAadhaar = "";
				try
				{
					objHisDAO = new HisDAO("DecryptAadhaar","PatientBO");
					
					tx.begin();
					PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
					decryptedAadhaar = objPatientIdDetailDAO.getAadhaarDecrypted(CrNo, uuid);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					throw new HisApplicationExecutionException("error, Contact System Administrator");
				}
				finally
				{
					if (objHisDAO != null) {
						//objHisDAO.free();
						objHisDAO = null;
					}
					tx.close();
					
				}
				return decryptedAadhaar; // <<<
			}
			
			//Added by Vasu dated on 9-April-2018
			public boolean changePreviousTreatmentCategory(ChangeTreatmentCategoryVO[] _TreatmentCategoryChangeVO, UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				int x = 0, y = 0;
				DailyPatientVO _dailyPatientVO = new DailyPatientVO();
				EpisodeVO _episodeVO = new EpisodeVO();

				try
				{
					System.out.println("Begining transaction");
					tx.begin();

					DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
					ChangeTreatmentCategoryDAO _TreatmentCatChangeDAO = new ChangeTreatmentCategoryDAO(tx);
					EpisodeDAO episodeDAO = new EpisodeDAO(tx);
					if(_TreatmentCategoryChangeVO!=null)
					{
						for (int i = 0; i < _TreatmentCategoryChangeVO.length; i++)
						{
							_episodeVO.setPatCrNo(_TreatmentCategoryChangeVO[i].getPatCrNo());
							_episodeVO.setEpisodeCode(_TreatmentCategoryChangeVO[i].getEpisodeCode());
							_episodeVO.setPatSecondaryCatCode(_TreatmentCategoryChangeVO[i].getPatNewSecondaryCatCode());
							_episodeVO.setEpisodeVisitNo(_TreatmentCategoryChangeVO[i].getEpisodeVisitNo());
							_episodeVO.setValidUpto(_TreatmentCategoryChangeVO[i].getValidUpto());
							_episodeVO.setSecCatExpDate(_TreatmentCategoryChangeVO[i].getExpiryDate());

							if(_TreatmentCategoryChangeVO[i].getExpiryDate()!=null && !_TreatmentCategoryChangeVO[i].getExpiryDate().equals(""))
								x = _TreatmentCatChangeDAO.updateTreatmentCatNExpiryDate(_episodeVO, _userVO);
							else
								x = _TreatmentCatChangeDAO.updateTreatmentCatNValidUpTo(_episodeVO, _userVO);

							_dailyPatientVO.setPatCrNo(_TreatmentCategoryChangeVO[i].getPatCrNo());
							_dailyPatientVO.setEpisodeCode(_TreatmentCategoryChangeVO[i].getEpisodeCode());
							_dailyPatientVO.setPatSecondaryCatCode(_TreatmentCategoryChangeVO[i].getPatNewSecondaryCatCode());
							_dailyPatientVO.setEpisodeVisitNo(_TreatmentCategoryChangeVO[i].getEpisodeVisitNo());

							_dailyPatientVO.setTreatmentValidUpTo(_episodeVO.getValidUpto());
							
							_TreatmentCatChangeDAO.updateTreatmentCategory(_dailyPatientVO, _userVO);

									_TreatmentCategoryChangeVO[i].setExpiryDate(_dailyPatientVO.getTreatmentValidUpTo());
									_TreatmentCatChangeDAO.createExtendValidUpto(_TreatmentCategoryChangeVO[i], _userVO,"2");

								y++;
						}
					}

				}
				catch (HisUpdateUnsuccesfullException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisUpdateUnsuccesfullException();
				}
				catch (HisApplicationExecutionException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisException(e.getMessage());
				}
				finally
				{
					System.out.println("y = " + y);
					tx.close();
				}
				if (y >= 1) return true;
				else return false;
			}
			
	//	Added by Vasu Dated on 23.April.18 to get Previous treatment categories details for selected episodes	
			public EpisodeVO[]  getPreviousTreatmentCategoriesForSelectedEpisode(String crNo, String episodeCode, UserVO userVO)
			{
				Map essentialMap = new HashMap();
				JDBCTransactionContext tx = new JDBCTransactionContext();
				EpisodeVO[] _arrEpisodeDupVO;
				EpisodeVO[] objArrEpisodeVO_p;
				String reprintMode = "";

				try
				{
					tx.begin();
					EpisodeDAO episodeDao = new EpisodeDAO(tx);
					objArrEpisodeVO_p = episodeDao.getTreatmentCategoriesForSelectedEpisodes(crNo, episodeCode,userVO, "1");
					//essentialMap.put(RegistrationConfig.CHANGE_TR, objArrEpisodeVO_p);
					
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisRecordNotFoundException(e.getMessage());
					
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				return objArrEpisodeVO_p;
			}
			
			public static void secondaryCategoryChangesForEveryEpisodeCreated(JDBCTransactionContext tx, UserVO _objUserVO, EpisodeVO objEpisodeVO_p, PatientVO objPatientVO_p){
				
				ChangeTreatmentCategoryVO _treatmentCatChangeVO = new ChangeTreatmentCategoryVO();
			
				try
				{
					ChangeTreatmentCategoryDAO _TreatmentCatChangeDAO = new ChangeTreatmentCategoryDAO(tx);

					_treatmentCatChangeVO.setPatCrNo(objEpisodeVO_p.getPatCrNo());
					_treatmentCatChangeVO.setEpisodeCode(objEpisodeVO_p.getEpisodeCode());
					_treatmentCatChangeVO.setPatNewSecondaryCatCode(objEpisodeVO_p.getPatSecondaryCatCode());
					_treatmentCatChangeVO.setRemarks("");
					_treatmentCatChangeVO.setLetterReferenceNo(objEpisodeVO_p.getCreditLetterRefNo());
					_treatmentCatChangeVO.setLetterDate(objEpisodeVO_p.getCreditLetterDate());
					_treatmentCatChangeVO.setCreditLimit(objEpisodeVO_p.getCreditLimit());
					_treatmentCatChangeVO.setApplicableServicesCode("");
					_treatmentCatChangeVO.setApplicableServicesName("");
					_treatmentCatChangeVO.setSelectedCategoryName("");
					_treatmentCatChangeVO.setAdmissionNo("");
					_treatmentCatChangeVO.setIsIpdFlag("0");
					_treatmentCatChangeVO.setPatPrimaryCatCode(objPatientVO_p.getPatPrimaryCatCode());
					_treatmentCatChangeVO.setPatPrimaryCat(objPatientVO_p.getPatPrimaryCat());
					
					_TreatmentCatChangeDAO.createExtendValidUpto(_treatmentCatChangeVO, _objUserVO,"4");
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
			
			}
			//Obsolete as on may'18
			/*public void CreateQREntry(PatientVO objPatVO, String _QRData, UserVO _objUserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				HisDAO objHisDAO = null;
				String mode = "1";
				try
				{
					objHisDAO = new HisDAO("CreateQREntry","PatientBO");
					
					tx.begin();
					//Converting String object to json object
					JSONObject json_QR = new JSONObject();
					JSONParser parser = new JSONParser();
					json_QR = (JSONObject)parser.parse(_QRData);
					
					//Coverting the jsonString to JavaBean
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					gson.fromJson(jsonString, JavaBean.class);
					
					//Coverting the jsonString to JavaBean
					int one = gson.fromJson("1", int.class);
					Integer one = gson.fromJson("1", Integer.class);
					Long one = gson.fromJson("1", Long.class);
					Boolean false = gson.fromJson("false", Boolean.class);
					String str = gson.fromJson("\"abc\"", String.class);
					String anotherStr = gson.fromJson("[\"abc\"]", String.class)
					
					PatientIdDetailDAO objPatientIdDetailDAO = new PatientIdDetailDAO(tx);
					objPatientIdDetailDAO.createQRDataEntry(mode, objPatVO, _QRData, _objUserVO);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					throw new HisApplicationExecutionException("error, Contact System Administrator");
				}
				finally
				{
					if (objHisDAO != null) {
						//objHisDAO.free();
						objHisDAO = null;
					}
					tx.close();
				}
			}*/
			
}//end of the class