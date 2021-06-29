package registration.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mrd.MrdConfig;
import registration.RegistrationConfig;
import registration.controller.data.HandoverToDeadBodyDATA;
import registration.controller.data.PatientDeathDetailDATA;
import registration.controller.fb.CRNoFB;
import registration.controller.fb.HandoverToDeadBodyFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;


public class HandoverToDeadBodyUTIL extends ControllerUTIL{

	
	
	public static void getDeadPatientList(HandoverToDeadBodyFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatientDeathDetailVO[] pDetailVO=null;
		try
		{
			pDetailVO=HandoverToDeadBodyDATA.getDeadPatientList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,RegistrationConfig.LIST_OF_DEAD_PATIENT, pDetailVO);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}


/**
## 		Modification Log		: saveHandoverToDetail					
##		Modify Date				: 07-01-2015	
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh 
*/	
	
	
	public static void getEssentialDetail(HandoverToDeadBodyFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatientDeathDetailVO pDetailVO=new PatientDeathDetailVO();
		Map handoverDetail=new HashMap();
		String flag="1";
		List lstRelation=new ArrayList();
		try
		{
			setSysdate(request);
			handoverDetail=HandoverToDeadBodyDATA.getHandoverToDetail(fb.getPatCrNo(),getUserVO(request));
			pDetailVO=(PatientDeathDetailVO)handoverDetail.get(RegistrationConfig.DEADBODY_HANDOVER_DETAIL);
			
			
		 	 if(pDetailVO.getBodyHandoverTo()!=null){ 
	    		if(pDetailVO.getDeathCertificateId()!=null)
	    		 {
	    			throw new HisRecordNotFoundException("Handover Details Already Added");
	    		 }else { flag="0"; }
	    	  }
		 	if(pDetailVO.getDeathCertificateId()!=null)
	   		 {
	   			fb.setDeathCertificateId(pDetailVO.getDeathCertificateId());
	   		 }
		 	   fb.setFlagForPrint(flag);
			   fb.setDepartmentUnitCode(pDetailVO.getDepartmentUnitCode());
			
			    InpatientDetailUTL.getInpatientDetailByCrNo(fb, request);
				PatientDetailVO patVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				if(patVO.getMlcNo()==null || patVO.getMlcNo()=="")
				{
					fb.setIsMlc("0");
				}
				else
				{
					fb.setIsMlc("1");
				}
				
				//Checking For Inpatient
				if(patVO.getPatAdmNo()==null)
					fb.setIsInpatient(RegistrationConfig.NO);
				else
					fb.setIsInpatient(RegistrationConfig.YES);

				WebUTIL.setMapInSession(handoverDetail, request);
				
			if(flag.equals("1")){	
				String sys=(String)session.getAttribute(Config.SYSDATE);
				String time=sys.split(" ")[1];
				fb.setHiddenTimeHr(time.split(":")[0]);
				fb.setHiddenTimeMin(time.split(":")[1]); }
			if(flag.equals("0"))
			{
				lstRelation=(List)handoverDetail.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL);
				   Iterator itr=  lstRelation.iterator();
				   while(itr.hasNext())
				   {
					   Entry entry=(Entry)itr.next();
					   if(pDetailVO.getRelativeCode().equals(entry.getValue()))
					   {
						   fb.setRelationName(entry.getLabel());
					   }
					   
				   }
				
			  String handoverDate=pDetailVO.getBodyHandoverDateTime();	
				String date=handoverDate.split(" ")[0];
				String time=handoverDate.split(" ")[1];
				
				fb.setHandoverDate(date);
				fb.setHiddenTimeHr(time.split(":")[0]);
				fb.setHiddenTimeMin(time.split(":")[1]);
				 HelperMethods.populatetToNullOrEmpty(fb,pDetailVO);
			}
				
				
			fb.setHandoverTimeHr(fb.getHiddenTimeHr());
			fb.setHandoverTimeMin(fb.getHiddenTimeMin());
			//fb.setPrintFlag(RegistrationConfig.PRINT_FLAG_NO);
			
			
			
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW);
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	
	public static void saveHandoverToDetail(HandoverToDeadBodyFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatientDeathDetailVO patDeathDtlVO=new PatientDeathDetailVO();
		
		try
		{
			
			fb.setBodyHandoverDateTime(fb.getSysDate()+" "+fb.getHiddenTimeHr()+":"+fb.getHiddenTimeMin());
		    
			 if(fb.getFlagForPrint().equals("1")){
				 
			  if(fb.getIsHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY))
					    fb.setCertificateHandoverTo("Mortuary");
				if(fb.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE))
						fb.setCertificateHandoverTo(fb.getOfficerName());
				if(fb.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES))
						fb.setCertificateHandoverTo(fb.getRelativeName());
			 }
		
			//PatientDetailVO patVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
	//		fb.setPatMlcNo(patVO.getMlcNo());
			if(fb.getIsPrintCertificate()==null)
			{
				fb.setIsPrintCertificate(RegistrationConfig.PRINT_DEATH_CERTIFICATE_NO);
				fb.setPrintFlag(RegistrationConfig.PRINT_FLAG_NO);
			}	
			else
			{
				fb.setIsPrintCertificate(RegistrationConfig.PRINT_DEATH_CERTIFICATE_YES);
				fb.setPrintFlag(RegistrationConfig.PRINT_FLAG_YES);
			}
			
			if(fb.getIsReceiptTaken()==null)
			{
				fb.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
				fb.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
			}	
			else
			{
				fb.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
				fb.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
			}
				
			  HelperMethods.populate(patDeathDtlVO, fb);
		
			  String flagForPrint=fb.getFlagForPrint();
			  
			HandoverToDeadBodyDATA.saveHandoverToDetail(patDeathDtlVO,flagForPrint,getUserVO(request));
			objStatus.add(Status.UNSUCESSFULL,"","Record Added Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getDeathDetailByCrNo(HandoverToDeadBodyFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatientDeathDetailVO patDeathDtlVO=new PatientDeathDetailVO();
		
		try
		{
			 CRNoFB crnoFB = new CRNoFB();
				crnoFB.setPatCrNo(fb.getPatCrNo());
				InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, request);
				PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				PatientDetailVO selectedPatientVO=null;
					selectedPatientVO=patDtlVO;
				
			
			//AddressVO patAddressVO=DeceasedAcceptanceDATA.getPatAddress(fb.getPatCrNo(),getUserVO(request));
			AddressVO patAddressVO=PatientDeathDetailDATA.getPatAddress(fb.getPatCrNo(),getUserVO(request));
			String add1="";
			String hNo=patAddressVO.getPatAddHNo();
			String cityLoc=patAddressVO.getPatAddCityLoc();
			String city=patAddressVO.getPatAddCity();
			String district=patAddressVO.getPatAddDistrict();
			
			if(hNo!=null)
				add1=hNo+",";
			if(cityLoc!=null)
				add1+=cityLoc+",";
			if(city!=null)
				add1+=city+",";
			if(district!=null)
				add1+=district;
				
			
			String add2=patAddressVO.getPatAddState()+","+patAddressVO.getPatAddCountry();
			fb.setDeceasedAddress(add1+", "+add2);
			patDeathDtlVO=PatientDeathDetailDATA.getDeathDetailByCrNo(fb.getPatCrNo(),getUserVO(request));
			fb.setPatAgeUnits(patDeathDtlVO.getPatAge().split(" ")[1]);
			fb.setPatAdmDate(selectedPatientVO.getAdmDateTime());
			HelperMethods.populatetToNullOrEmpty(fb, patDeathDtlVO);
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	
}
