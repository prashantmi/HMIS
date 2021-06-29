package registration.transactions.controller.util;
/**
 * Developed By : Aadil Wasi
 * Date			: May 2013
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientDeathDetailSUP;
import registration.transactions.controller.data.MLCtoNonMLCDATA;
import registration.transactions.controller.data.PatientDeathDetailDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.registration.AddressVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientDeathDetailVO;
import vo.registration.PatientVO;
import vo.registration.UnitConsultantVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.tools.tag.GlobalPatientVO;
import hisglobal.utility.HelperMethods;
/*import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;*/
import hisglobal.vo.UserVO;

public class PatientDeathDetailUTIL extends ControllerUTIL{

	public static void getPatientdeathDetailEssential(PatientDeathDetailSUP objPatDeathDtlSUP_p, HttpServletRequest objRequest_p)
	{
		Status objStatus=new Status();
		HttpSession session=objRequest_p.getSession();
		PatientVO objPatientVO = new PatientVO();
		GlobalPatientVO objGlobalPatientVO = new GlobalPatientVO();
		List lstConsultant = new ArrayList();
		
		try
		{
			System.out.println("PatientDeathDetailUTIL :: getPatientdeathDetailEssential()");
			UserVO objUserVO =getUserVO(objRequest_p);		
			objPatientVO.setPatCrNo(objPatDeathDtlSUP_p.getPatCrNo());
			String hospitalCode=objUserVO.getHospitalCode();
			if(hospitalCode!=null){
				System.out.println(objPatientVO.getPatCrNo().substring(3)+":::::::::Hospital Code");
				//To Check the Hospital Code in CRNO on the Basis hosCode Length Added by Singaravelan on 09-Jan-2015
				if(hospitalCode.length()==5){
					if(!hospitalCode.equals(objPatientVO.getPatCrNo().substring(0,5))){
							objPatientVO.setOtherHospitalFlag("1");
							objPatDeathDtlSUP_p.setOtherHospitalFlag("1");
					}
					else{
						objPatientVO.setOtherHospitalFlag("0");
						objPatDeathDtlSUP_p.setOtherHospitalFlag("0");
					}
				}
				else{
					if(!hospitalCode.equals(objPatientVO.getPatCrNo().substring(0,3))){
						objPatientVO.setOtherHospitalFlag("1");
						objPatDeathDtlSUP_p.setOtherHospitalFlag("1");
					}
					else{
						objPatientVO.setOtherHospitalFlag("0");
						objPatDeathDtlSUP_p.setOtherHospitalFlag("0");
					}
					
				}
			}
			else{
				objPatientVO.setOtherHospitalFlag("0");
				objPatDeathDtlSUP_p.setOtherHospitalFlag("0");
			}
			objPatientVO=PatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, "");
			HelperMethods.populate(objGlobalPatientVO, objPatientVO);
			HelperMethods.populate(objGlobalPatientVO.getGlobalPatAddress(), objPatientVO.getPatAddress());
			WebUTIL.setAttributeInSession(objRequest_p,"keyPatientVO",objGlobalPatientVO);
			
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			objPatDeathDtlSUP_p.setHiddenTimeHr(time.split(":")[0]);
			objPatDeathDtlSUP_p.setHiddenTimeMin(time.split(":")[1]);
			
			
			boolean flagExistDeathRecord =PatientDeathDetailDATA.isPatientDeathDtlAdded(objPatDeathDtlSUP_p.getPatCrNo(),getUserVO(objRequest_p));
			if(!flagExistDeathRecord)
			{
				Map mp=PatientDeathDetailDATA.getPatientdeathDetailEssential(objPatDeathDtlSUP_p.getPatCrNo(),objPatDeathDtlSUP_p.getEpisodeCode(),getUserVO(objRequest_p));
				WebUTIL.setMapInSession(mp, objRequest_p,"PatientDeathDetailACTION");
				
				String dt=(String)session.getAttribute(RegistrationConfig.ESSENTIAL_DEATH_ON_SET_DATE_N_RECENT_VISIT_DATE);
				if(dt!=null && !dt.isEmpty()){
					String onSetdate=dt.split("@")[0];
					String recentVisitDt=dt.split("@")[1];
					objPatDeathDtlSUP_p.setOnSetDate(onSetdate);
					objPatDeathDtlSUP_p.setHiddenOnSetDate(onSetdate);
					objPatDeathDtlSUP_p.setHiddenRecentVisitDate(recentVisitDt);
				}
				objPatDeathDtlSUP_p.setVerificationTimeHr(objPatDeathDtlSUP_p.getHiddenTimeHr());
				objPatDeathDtlSUP_p.setVerificationTimeMin(objPatDeathDtlSUP_p.getHiddenTimeMin());
				objPatDeathDtlSUP_p.setHandoverTimeHr(objPatDeathDtlSUP_p.getHiddenTimeHr());
				objPatDeathDtlSUP_p.setHandoverTimeMin(objPatDeathDtlSUP_p.getHiddenTimeMin());
				
				objPatDeathDtlSUP_p.setPrintFlag(RegistrationConfig.PRINT_FLAG_NO);
				
				EpisodeVO[] arrOpenEpisodeVO = PatientDeathDetailDATA.getOpenEpisodes(objPatDeathDtlSUP_p.getPatCrNo(), objUserVO, "");
				WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ARR_OPEN_EPISODE_VO_FOR_PAT_DEATH_DTL, arrOpenEpisodeVO);
				
				if(arrOpenEpisodeVO==null || arrOpenEpisodeVO.length==0)
					throw new HisRecordNotFoundException("No Open Episode Found");
				UnitConsultantVO voUnitConsultant = new UnitConsultantVO();
				lstConsultant = PatientDeathDetailDATA.getConsultantList(voUnitConsultant, objUserVO);
				WebUTIL.setAttributeInSession(objRequest_p, "consultantList", lstConsultant);
			}
			else
			{
				objPatDeathDtlSUP_p.setAfterGo("0");
				objPatDeathDtlSUP_p.addActionError("Patient Death Details Already Added");
			}
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			//objPatDeathDtlSUP_p.setErrorMessage(e.getMessage());
	   		if(e.getMessage()!=null && e.getMessage().trim().equals("Patient Details Not Found")){
	   			objPatDeathDtlSUP_p.addActionError("Patient Details Not Found");
	   			objPatDeathDtlSUP_p.setAfterGo("0");
	   			if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   			{
	   				WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   			}
	   		}if(e.getMessage()!=null && e.getMessage().trim().equals("No Open Episode Found")){
	   			objPatDeathDtlSUP_p.addActionError(e.getMessage());
	   			objPatDeathDtlSUP_p.setAfterGo("0");
	   		}else{
	   			WebUTIL.setAttributeInSession(objRequest_p, "consultantList", lstConsultant);
	   		}
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.setAfterGo("0");
			objPatDeathDtlSUP_p.addActionError("Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.setAfterGo("0");
			objPatDeathDtlSUP_p.addActionError("Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.setAfterGo("0");
			objPatDeathDtlSUP_p.addActionError("Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.setAfterGo("0");
			objPatDeathDtlSUP_p.addActionError("Error");
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
	}
	
	public static boolean savePatientDeathDetail(PatientDeathDetailSUP objPatDeathDtlSUP_p,HttpServletRequest objRequest_p)
	{
		boolean flagSuccessfull = false;
		HttpSession session=objRequest_p.getSession();
		PatientDeathDetailVO objPatDeathDtlVO=new PatientDeathDetailVO();
		
		try
		{
			System.out.println("PatientDeathDetailUTIL :: savePatientDeathDetail() --> IsHandoverTo :"+objPatDeathDtlSUP_p.getIsHandoverTo());
			if(objPatDeathDtlSUP_p.getIsHandoverTo().equals("0"))
			{
				objPatDeathDtlSUP_p.setBodyHandoverTo("");
				objPatDeathDtlSUP_p.setBodyHandoverDateTime("");
				objPatDeathDtlSUP_p.setOfficerBadgeNo("");
				objPatDeathDtlSUP_p.setOfficerDesignation("");
				objPatDeathDtlSUP_p.setOfficerName("");
				objPatDeathDtlSUP_p.setRelativeAddress("");
				objPatDeathDtlSUP_p.setRelativeCode("");
				objPatDeathDtlSUP_p.setRelativeName("");
				objPatDeathDtlSUP_p.setCertificateHandoverTo("");
			}
			else
			{
				objPatDeathDtlSUP_p.setBodyHandoverDateTime(objPatDeathDtlSUP_p.getHandoverDate()+" "+objPatDeathDtlSUP_p.getHandoverTimeHr()+":"+objPatDeathDtlSUP_p.getHandoverTimeMin());
				
				if(objPatDeathDtlSUP_p.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY))
					objPatDeathDtlSUP_p.setCertificateHandoverTo("Mortuary");
				if(objPatDeathDtlSUP_p.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE))
					objPatDeathDtlSUP_p.setCertificateHandoverTo(objPatDeathDtlSUP_p.getOfficerName());
				if(objPatDeathDtlSUP_p.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES))
					objPatDeathDtlSUP_p.setCertificateHandoverTo(objPatDeathDtlSUP_p.getRelativeName());
			}
			//objPatDeathDtlSUP_p.setBodyHandoverDateTime(objPatDeathDtlSUP_p.getSysDate()+" "+objPatDeathDtlSUP_p.getHiddenTimeHr()+":"+objPatDeathDtlSUP_p.getHiddenTimeMin());
			
			//Added by Surabhi on 1st Feb'17 for free text if snomed field is disabled  
			String[] a=objPatDeathDtlSUP_p.getDeathMannerCode().split("@");
			if(a.length==3){
				objPatDeathDtlSUP_p.setDeathMannerCode(objPatDeathDtlSUP_p.getDeathMannerCode().split("@")[0]);
				objPatDeathDtlSUP_p.setSnomdCIdDeathManner(a[2]);
			}else {
				objPatDeathDtlSUP_p.setDeathMannerCode(a[0]);
			}
			//end
			
			objPatDeathDtlSUP_p.setDeathTime(objPatDeathDtlSUP_p.getDeathTimeHr()+":"+objPatDeathDtlSUP_p.getDeathTimeMin());
			objPatDeathDtlSUP_p.setDeathDate(objPatDeathDtlSUP_p.getDeathDate()+" "+objPatDeathDtlSUP_p.getDeathTime());
			objPatDeathDtlSUP_p.setVerificationTime(objPatDeathDtlSUP_p.getVerificationTimeHr()+":"+objPatDeathDtlSUP_p.getVerificationTimeMin());
			objPatDeathDtlSUP_p.setVerificationDate(objPatDeathDtlSUP_p.getVerificationDate()+" "+objPatDeathDtlSUP_p.getVerificationTime());
			/*PatientDetailVO objPatientDetailVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			//objPatDeathDtlSUP_p.setPatMlcNo(patVO.getMlcNo());
			objPatDeathDtlSUP_p.setRoomCode(objPatientDetailVO.getIpdRoomCode());
			objPatDeathDtlSUP_p.setBedCode(objPatientDetailVO.getBedCode());
			objPatDeathDtlSUP_p.setWardCode(objPatientDetailVO.getWardCode());
			objPatDeathDtlSUP_p.setPatAdmNo(objPatientDetailVO.getPatAdmNo());
			
			if(objPatientDetailVO.getPatAge().split(" ")[1].equalsIgnoreCase("Wk") && Integer.parseInt(objPatientDetailVO.getPatAge().split(" ")[0])<=4)
				objPatDeathDtlSUP_p.setIsNewNatal("1");
			else
				objPatDeathDtlSUP_p.setIsNewNatal("0");
			
			if(objPatientDetailVO.getIsBroughtDead()==null)
				objPatDeathDtlSUP_p.setIsBroughtDead(RegistrationConfig.PATIENT_BROUGHT_DEAD_FALSE);
			else
				objPatDeathDtlSUP_p.setIsBroughtDead(objPatientDetailVO.getIsBroughtDead());*/
			//PatientVO objPatientVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
			PatientVO objPatientVO=new PatientVO();
			//GlobalPatientVO objGlobalPatientVO = new GlobalPatientVO();
			GlobalPatientVO objGlobalPatientVO=(GlobalPatientVO)session.getAttribute("keyPatientVO");
			
			HelperMethods.populate(objPatientVO, objGlobalPatientVO);
			
			if(objPatientVO.getPatAge().equalsIgnoreCase("Wk") && Integer.parseInt(objPatientVO.getPatAgeUnit())<=4)
				objPatDeathDtlSUP_p.setIsNewNatal("1");
			else
				objPatDeathDtlSUP_p.setIsNewNatal("0");
			
			if(objPatientVO.getIsBroughtDead()==null)
				objPatDeathDtlSUP_p.setIsBroughtDead(RegistrationConfig.PATIENT_BROUGHT_DEAD_FALSE);
			else
				objPatDeathDtlSUP_p.setIsBroughtDead(objPatientVO.getIsBroughtDead());
			
			/*if(objPatDeathDtlSUP_p.getIsPrintCertificate()==null)
			{
				objPatDeathDtlSUP_p.setIsPrintCertificate(RegistrationConfig.PRINT_DEATH_CERTIFICATE_NO);
				objPatDeathDtlSUP_p.setPrintFlag(RegistrationConfig.PRINT_FLAG_NO);
			}	
			else
			{
				objPatDeathDtlSUP_p.setIsPrintCertificate(RegistrationConfig.PRINT_DEATH_CERTIFICATE_YES);
				objPatDeathDtlSUP_p.setPrintFlag(RegistrationConfig.PRINT_FLAG_YES);
			}
			
			if(objPatDeathDtlSUP_p.getIsReceiptTaken()==null)
			{
				objPatDeathDtlSUP_p.setIsReceiptTaken(RegistrationConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
				objPatDeathDtlSUP_p.setRecordStatus(RegistrationConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
			}	
			else
			{
				objPatDeathDtlSUP_p.setIsReceiptTaken(RegistrationConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
				objPatDeathDtlSUP_p.setRecordStatus(RegistrationConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
			}*/
				
			HelperMethods.populate(objPatDeathDtlVO, objPatDeathDtlSUP_p);
			
			/*ANCDetailVO ancDetailVO=(ANCDetailVO)session.getAttribute(RegistrationConfig.ANC_DETAIL_FOR_DEATH_PATIENT_VO);
			if(ancDetailVO!=null)
				ancDetailVO.setDeathCause(objPatDeathDtlSUP_p.getDeathMannerCode().split("@")[1]);*/
			
			PatientDeathDetailDATA.savePatientDeathDetail(objPatDeathDtlVO,objPatientVO, getUserVO(objRequest_p));
			objPatDeathDtlSUP_p.setStrNormalMsg("Record Added Successfully");
			flagSuccessfull=true;
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.addActionError("Data Access Error");
		}
		catch (HisException e)
		{
			objPatDeathDtlSUP_p.addActionError("Application Execution Error");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.addActionError("Application Execution Error!");
		}
		finally
		{
			return flagSuccessfull;
		}
	}
	
	public static void getDeathDetailByCrNo(PatientDeathDetailSUP objPatDeathDtlSUP_p,HttpServletRequest objRequest_p)
	{
		Status objStatus=new Status();
		HttpSession session=objRequest_p.getSession();
		PatientDeathDetailVO objPatDeathDtlVO=new PatientDeathDetailVO();
		
		try
		{
			/*PatientVO objPatientVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
			
			
			//AddressVO objAddressVO=DeceasedAcceptanceDATA.getPatAddress(objPatDeathDtlSUP_p.getPatCrNo(),getUserVO(objRequest_p));
			AddressVO objAddressVO = objPatientVO.getPatAddress();
			String add1="";
			String hNo=objAddressVO.getPatAddHNo();
			String cityLoc=objAddressVO.getPatAddCityLoc();
			String city=objAddressVO.getPatAddCity();
			String district=objAddressVO.getPatAddDistrict();
			
			if(hNo!=null)
				add1=hNo+",";
			if(cityLoc!=null)
				add1+=cityLoc+",";
			if(city!=null)
				add1+=city+",";
			if(district!=null)
				add1+=district;
				
			
			String add2=objAddressVO.getPatAddState()+","+objAddressVO.getPatAddCountry();
			objPatDeathDtlSUP_p.setDeceasedAddress(add1+", "+add2);
			//objPatDeathDtlVO=PatientDeathDetailDATA.getDeathDetailByCrNo(objPatDeathDtlSUP_p.getPatCrNo(),getUserVO(objRequest_p));
			objPatDeathDtlSUP_p.setPatAgeUnits(objPatDeathDtlVO.getPatAge().split(" ")[1]);
			//objPatDeathDtlSUP_p.setPatAdmDate(selectedPatientVO.getAdmDateTime());
			HelperMethods.populatetToNullOrEmpty(objPatDeathDtlSUP_p, objPatDeathDtlVO);
			*/
				
			/*Start : Surabhi
			 * Reason : to get the patient death details for the certificate
			 * date : 7th oct 2016 */
				objPatDeathDtlVO=PatientDeathDetailDATA.getDeathDetailByCrNo(objPatDeathDtlSUP_p.getPatCrNo(),getUserVO(objRequest_p));
				HelperMethods.populatetToNullOrEmpty(objPatDeathDtlSUP_p, objPatDeathDtlVO);
		// end
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.setAfterGo("0");
			objPatDeathDtlSUP_p.addActionError("Data Access Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objPatDeathDtlSUP_p.setAfterGo("0");
			objPatDeathDtlSUP_p.addActionError("Application Execution Error");
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
	}

}
