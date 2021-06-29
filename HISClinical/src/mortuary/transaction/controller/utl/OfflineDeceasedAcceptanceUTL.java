package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.ExternalDeceasedAcceptanceDATA;
import mortuary.transaction.controller.data.OfflineDeceasedAcceptanceDATA;
import mortuary.transaction.controller.fb.OfflineDeceasedAcceptanceFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDeadPatientException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.AddressVO;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;

public class OfflineDeceasedAcceptanceUTL extends ControllerUTIL
{
	public static void getessentialForAcceptance(OfflineDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		
		try
		{
			InpatientDetailUTL.getInpatientDetailByCrNo(fb, request);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus=(String)patientVO.getPatStatusCode();
			System.out.println(patDeathStatus);
			if(patDeathStatus.equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD))
			{
				PatientDeathDetailVO patDeathDtlVO=OfflineDeceasedAcceptanceDATA.getDeceasedHandoverDetail(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, MortuaryConfig.PATIENT_DEATH_DETAIL_VO, patDeathDtlVO);
				if(patDeathDtlVO!=null)
				{
					Map mp=OfflineDeceasedAcceptanceDATA.getDeceasedEssentialStorageDetail(getUserVO(request));
					WebUTIL.setMapInSession(mp, request);
					
					fb.setBodyHandoverTo(patDeathDtlVO.getBodyHandoverTo());
					fb.setDeathDateTime(patDeathDtlVO.getDeathDate());
					fb.setBodyHandoverDateTime(patDeathDtlVO.getBodyHandoverDateTime());
					if(patDeathDtlVO.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES))
					{
						/*List lstRelation=(List)mp.get(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION);
						for(int i=0;i<lstRelation.size();i++)
						{
							Entry ent=(Entry)lstRelation.get(i);
							if(patDeathDtlVO.getRelativeCode().equals(ent.getValue()))
							{
								str=ent.getLabel();
								break;
							}
						}
						fb.setRelativeCodeName(str);*/
						fb.setRelativeName(patDeathDtlVO.getRelativeName());
						fb.setRelativeAddress(patDeathDtlVO.getRelativeAddress());
						fb.setRelativeCode(patDeathDtlVO.getRelativeCode());
					}
					if(patDeathDtlVO.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE))
					{
						fb.setOfficerName(patDeathDtlVO.getOfficerName());
						fb.setOfficerDesignation(patDeathDtlVO.getOfficerDesignation());
						fb.setOfficerBadgeNo(patDeathDtlVO.getOfficerBadgeNo());
					}
					
					
				}
				else
					throw new HisDeadPatientException("Either Patient is In Mortuary or Handover");
					
			}
			else
			{
				throw new HisDeadPatientException("Patient is Not Dead");
			}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDeadPatientException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	/** Getting Rack Based On The Chamber
	 * @param fb
	 * @param request
	 */
	public static void getRackBasedOnChamber(OfflineDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session= request.getSession();
		List lstRack=new ArrayList();
		
		try
		{
			String chamber=fb.getChamberId();
			ChamberRackMasterVO[] chamberRackVO=(ChamberRackMasterVO[])session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_VO);
			for(int i=0;i<chamberRackVO.length;i++)
			{
				if(chamber.equals(chamberRackVO[i].getChamberId()))
				{
					Entry ent =new Entry();
					ent.setValue(chamberRackVO[i].getChamberRackId());
					ent.setLabel(chamberRackVO[i].getRackName());
					lstRack.add(ent);
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST, lstRack);
			if(lstRack.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Rack Found");
			else
				objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	
	public static void saveOfflineStorageDetail(OfflineDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session= request.getSession();
		DeceasedStorageDtlVO deceasedStorageVO=new DeceasedStorageDtlVO();
		DeceasedRelativeDtlVO deceasedRelativeVO=new DeceasedRelativeDtlVO();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		DeceasedBroughtByDtlVO deceasedBroughtVO=new DeceasedBroughtByDtlVO();
		
		try
		{
			HelperMethods.populate(deceasedStorageVO, fb);
			
			PatientDetailVO patientDetailVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			PatientDeathDetailVO deadPatVO=(PatientDeathDetailVO)session.getAttribute(MortuaryConfig.PATIENT_DEATH_DETAIL_VO);
			
			AddressVO patAddressVO=OfflineDeceasedAcceptanceDATA.getPatAddress(fb.getPatCrNo(),getUserVO(request));
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
			
			HelperMethods.populatetToNullOrEmpty(deceasedDtlVO, patientDetailVO);
			deceasedDtlVO.setEntryMode(MortuaryConfig.ENTRY_MODE_INHOUSE_SEND_OFFLINE);
			deceasedDtlVO.setUnidentifiedBody(patientDetailVO.getIsUnknown());
			deceasedDtlVO.setIsMlcCase(patientDetailVO.getIsMLC());
			deceasedDtlVO.setAdd1(add1);
			deceasedDtlVO.setAdd2(add2);
			HelperMethods.populatetToNullOrEmpty(deceasedDtlVO, deadPatVO);
			deceasedDtlVO.setDeathHistory(deadPatVO.getImmediateCause1());
			deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_CHAMBER);
			deceasedDtlVO.setPostmortemReq(MortuaryConfig.POSTMORTEM_REQUEST_OPTIONAL);
			deceasedDtlVO.setIsClaimed(MortuaryConfig.IS_BODY_CLAIMED_YES);
			
			
			
			if(fb.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_RELATIVES))
			{
				if(fb.getHandoverFlag().equals(MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING))
				{
					deceasedRelativeVO.setRelativeName(fb.getRelativeName());
					deceasedRelativeVO.setRelativeAddress(fb.getRelativeAddress());
					deceasedRelativeVO.setRelativeCode(fb.getRelativeCode());
					deceasedRelativeVO.setRelativeContactNo(fb.getRelativeContactNo());
					deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
					
					deceasedBroughtVO.setBroughtByName(fb.getRelativeName());
					deceasedBroughtVO.setBroughtByAddress(fb.getRelativeAddress());
					deceasedBroughtVO.setBroughtByPhone(fb.getRelativeContactNo());
					deceasedBroughtVO.setRelativeCode(fb.getRelativeCode());
					deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_RELATIVE);
				}
				
				if(fb.getHandoverFlag().equals(MortuaryConfig.DEAD_BODY_HANDOVER_NEW))
				{
					deceasedRelativeVO.setRelativeName(fb.getStorageRelativeName());
					deceasedRelativeVO.setRelativeAddress(fb.getStorageRelativeAddress());
					deceasedRelativeVO.setRelativeCode(fb.getStorageRelativeCode());
					deceasedRelativeVO.setRelativeContactNo(fb.getStorageRelativeContactNo());
					deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
					
					deceasedBroughtVO.setBroughtByName(fb.getStorageRelativeName());
					deceasedBroughtVO.setBroughtByAddress(fb.getStorageRelativeAddress());
					deceasedBroughtVO.setBroughtByPhone(fb.getStorageRelativeContactNo());
					deceasedBroughtVO.setRelativeCode(fb.getStorageRelativeCode());
					deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_RELATIVE);
				}
			}
			
			if(fb.getBodyHandoverTo().equals(RegistrationConfig.DEAD_BODY_HANDOVER_TO_POLICE))
			{
				deceasedBroughtVO.setBroughtByName(fb.getOfficerName());
				deceasedBroughtVO.setOfficerDesignation(fb.getOfficerDesignation());
				deceasedBroughtVO.setOfficerBadgeNo(fb.getOfficerBadgeNo());
				deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_POLICE);
				
				deceasedRelativeVO=null;
			}
			
			String deceasedNo = OfflineDeceasedAcceptanceDATA.saveOfflineDeceasedStorageDetail(deceasedDtlVO,deceasedBroughtVO,deceasedRelativeVO,deceasedStorageVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Deceased Storage Detail Saved Successfully with Deceased No. :: " + deceasedNo);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
}
