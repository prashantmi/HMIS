package mortuary.transaction.controller.utl;

import inpatient.InpatientConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedAcceptanceDATA;
import mortuary.transaction.controller.fb.DeceasedAcceptanceFB;
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
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.AddressVO;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;

public class DeceasedAcceptanceUTL extends ControllerUTIL
{
	 
	/** Getting The List of Dead Patient Send To Mortuary
	 * @param fb
	 * @param request
	 */
	public static void getInHouseDeadPatientList(DeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PatientDeathDetailVO[] deadPatListVO=null;
		
		try
		{
			deadPatListVO=DeceasedAcceptanceDATA.getInHouseDeadPatientList(getUserVO(request));
			
			if(deadPatListVO!=null)
			{
				for (int i = 0; i < deadPatListVO.length; i++) 
				{
					System.out.println("/////////////****Start***"+i+"/////////////");
					System.out.println("deadPatListVO[i].getIsUnknown()="+deadPatListVO[i].getIsUnknown());
					System.out.println("deadPatListVO[i].getUnknownCrNo()="+deadPatListVO[i].getUnknownCrNo());
					System.out.println("deadPatListVO[i].getIsBroughtDead()="+deadPatListVO[i].getIsBroughtDead());
					System.out.println("deadPatListVO[i].getPatMlcNo()="+deadPatListVO[i].getPatMlcNo());
					System.out.println("deadPatListVO[i].getPatMlcNo()="+deadPatListVO[i].getDeathEnteredBy());
					System.out.println("/////////////****End***"+i+"/////////////");
					if(deadPatListVO[i].getIsUnknown()!=null)
					{
					if(deadPatListVO[i].getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
					{
						if(deadPatListVO[i].getUnknownCrNo()!=null)
							deadPatListVO[i].setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
						else
							deadPatListVO[i].setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE);
					}
					else
						deadPatListVO[i].setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
					
					
					if(deadPatListVO[i].getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
						deadPatListVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_UNKNOWN);
						//deadPatListVO[i].setColor(MortuaryConfig.DECEASED_COLOR_UNKNOWN);
				
					else if(deadPatListVO[i].getIsBroughtDead().equals(RegistrationConfig.IS_BROUGHT_DEAD_TRUE))
						deadPatListVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_BROUGHT_DEAD);
						//deadPatListVO[i].setColor(MortuaryConfig.DECEASED_COLOR_BROUGHT_DEAD);
					
					else if(deadPatListVO[i].getPatMlcNo()!=null && !deadPatListVO[i].getPatMlcNo().equals(""))
						deadPatListVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_MLC);
						//deadPatListVO[i].setColor(MortuaryConfig.DECEASED_COLOR_MLC);
				
					else
						deadPatListVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_NORMAL);
						//deadPatListVO[i].setColor(MortuaryConfig.DECEASED_COLOR_NORMAL);
					}
				}
			}
			
			WebUTIL.setAttributeInSession(request, MortuaryConfig.DEAD_PATIENT_LIST_SEND_TO_MORTUARY, deadPatListVO);
			objStatus.add(Status.LIST);
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
	
	
	/** Getting The Deceased Detail
	 * @param fb
	 * @param request
	 */
	public static void getDeceasdDetail(DeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session= request.getSession();
		List lstPeon=new ArrayList();
		PoliceVerificationDtlVO policeVerificationDtlVO= null;
		PatientImageDtlVO[] patientImageDtlVO = null;
		List byteArray=new ArrayList();
		String deptCode="";
		String patIdMark="";
		String deathDuration="";
		String fromUnit="";
		
		try
		{
			//fb.setPatCrNo(fb.getDeadPatRadio());
			fb.setNewAddImageExistFlag("0");
			fb.setIsMlcCase(MortuaryConfig.IS_MLC_NO);
			PatientDeathDetailVO[] deadPatListVO=(PatientDeathDetailVO[])session.getAttribute(MortuaryConfig.DEAD_PATIENT_LIST_SEND_TO_MORTUARY);
			for(int i=0;i<deadPatListVO.length;i++)
			{
				if(fb.getPatCrNo().equalsIgnoreCase(deadPatListVO[i].getPatCrNo()))
				{
					deptCode=deadPatListVO[i].getDepartmentCode();
					deathDuration=deadPatListVO[i].getDeathDuration();
					fromUnit=deadPatListVO[i].getDepartmentName()+"/"+deadPatListVO[i].getDepartmentUnitName()+(deadPatListVO[i].getWardName()==null?"":"/"+deadPatListVO[i].getWardName());
					if(deadPatListVO[i].getPatMlcNo()!=null && !deadPatListVO[i].getPatMlcNo().equals(""))
					{
						fb.setIsMlcCase(MortuaryConfig.IS_MLC_YES);		//Setting the MLC Case
						fb.setPatMlcNo(deadPatListVO[i].getPatMlcNo());		//Setting MLC No
						
					}	
					fb.setUnidentifiedBody(deadPatListVO[i].getIsUnknown());
					fb.setDeathDuration(deathDuration);
					fb.setFromUnit(fromUnit);
					fb.setDeathEnteredBy(deadPatListVO[i].getDeathEnteredBy());
					break;
				}	
			}
			
			//Getting The List of Peon on The Basis of Department Code Who Brought The Dead Body
			lstPeon=DeceasedAcceptanceDATA.getPeonList(deptCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_PEON_LIST, lstPeon);
			
			//Getting Essential Data
			Map mp=DeceasedAcceptanceDATA.getDeceasedEssential(deptCode,getUserVO(request));
			WebUTIL.setMapInSession(mp, request);
			if(fb.getIsMlcCase().equals(MortuaryConfig.IS_MLC_YES))
			{
				//Getting Deceased Police verification Detail in MLC case
				policeVerificationDtlVO=DeceasedAcceptanceDATA.getPoliceVerificationDetail(fb.getPatMlcNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL, policeVerificationDtlVO);
				if(policeVerificationDtlVO!=null)
				{
					if(policeVerificationDtlVO.getCaseNo()!=null)
					{
						fb.setPoliceVerificationFlag(MortuaryConfig.POLICE_VERIFICATION_EXISTING);
						if(policeVerificationDtlVO.getIoBatchNo().equals(policeVerificationDtlVO.getDutyOffBatchNo()))
							fb.setDutyOfficeFlag(MortuaryConfig.DUTY_OFFICER_IS_IO);
						else	
							fb.setDutyOfficeFlag(MortuaryConfig.DUTY_OFFICER_IS_OTHER);
						HelperMethods.populatetToNullOrEmpty(fb,policeVerificationDtlVO);
					}
					else
					{
						fb.setPoliceVerificationFlag(MortuaryConfig.POLICE_VERIFICATION_NEW);
						//fb.setDutyOfficeFlag(MortuaryConfig.DUTY_OFFICER_IS_OTHER);
					}
				}
				else
					fb.setPoliceVerificationFlag(MortuaryConfig.POLICE_VERIFICATION_NEW);
			}
			
			if(fb.getUnidentifiedBody()!=null)
			{
				///Unknown Body Identification Mark
				if(fb.getUnidentifiedBody().equals(MortuaryConfig.UNIDENTIFIED_BODE_YES))
				{
					patIdMark=DeceasedAcceptanceDATA.getPatientIdMark(fb.getPatCrNo(),getUserVO(request));
					fb.setIdMark1(patIdMark.split("#")[0]);
					fb.setIdMark2(patIdMark.split("#")[1]);
				}
			}
			
			///Get Deceased Existing Image
			patientImageDtlVO=DeceasedAcceptanceDATA.getDeceasedExistingImage(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO, patientImageDtlVO);
			if(patientImageDtlVO==null)
				fb.setImageExistFlag(MortuaryConfig.IMAGE_EXIST_NO);
			else
			{
				fb.setImageExistFlag(MortuaryConfig.IMAGE_EXIST_YES);
				
				
				byte[] getDoc;
				
				for(int i=0;i<patientImageDtlVO.length;i++)
				{
					String filepath= patientImageDtlVO[i].getFileNo();
					
					//byteArray.add(HelperMethods.getByteArrayOfImage(filepath));		//Getting Deceased Existing Images
					getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_MORTUARY_PATIENT_IMAGE_UPLOAD).latestFetchFile(filepath);
					byteArray.add(getDoc);
				}
				WebUTIL.setAttributeInSession(request,Config.IMAGE_BYTE_ARRAY , byteArray);
			}
			
			List lstChamber=(List)mp.get(MortuaryConfig.ESENTIAL_ALL_CHAMBER);
			if(lstChamber.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Chamber Found ");
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
	
	
	/** Getting Rack Based On The Chamber
	 * @param fb
	 * @param request
	 */
	public static void getRackBasedOnChamber(DeceasedAcceptanceFB fb,HttpServletRequest request)
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
	
	
	/** Saving Deceased Detail
	 * @param fb
	 * @param request
	 */
	public static String saveDeceasedDetail(DeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		String deceasedNo = null;
		Status objStatus = new Status();
		List byteArray=new ArrayList();
		HttpSession session= request.getSession();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		DeceasedHandoverDtlVO deceasedHandoverVO=new DeceasedHandoverDtlVO();
		DeceasedRelativeDtlVO deceasedRelativeVO=new DeceasedRelativeDtlVO();
		DeceasedStorageDtlVO deceasedStorageVO=new DeceasedStorageDtlVO();
		PatientDeathDetailVO deadPatVO=new PatientDeathDetailVO();
		DeceasedBroughtByDtlVO deceasedBroughtVO=new DeceasedBroughtByDtlVO();
		MortuaryPoliceVerificationVO policeVerVO= new MortuaryPoliceVerificationVO();
		MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO=null;
		String add1="";
		String add2="";
		
		try
		{
			PatientDetailVO patientDetailVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			PatientDeathDetailVO[] deadPatListVO=(PatientDeathDetailVO[])session.getAttribute(MortuaryConfig.DEAD_PATIENT_LIST_SEND_TO_MORTUARY);
			for(int i=0;i<deadPatListVO.length;i++)
			{
				if(fb.getPatCrNo().equals(deadPatListVO[i].getPatCrNo()))
				{
					deadPatVO=deadPatListVO[i];
					break;
				}
					
			}
			
			AddressVO patAddressVO=DeceasedAcceptanceDATA.getPatAddress(fb.getPatCrNo(),getUserVO(request));
			if(patAddressVO!=null)
			{	
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
				
				add2=patAddressVO.getPatAddState()+","+patAddressVO.getPatAddCountry();
			}
			HelperMethods.populate(deceasedDtlVO, fb);
			HelperMethods.populatetToNullOrEmpty(deceasedDtlVO, patientDetailVO);
			HelperMethods.populatetToNullOrEmpty(deceasedDtlVO, deadPatVO);
			deceasedDtlVO.setEntryMode(MortuaryConfig.ENTRY_MODE_INHOUSE_SEND_ONLINE);
		//	deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
			deceasedDtlVO.setUnidentifiedBody(patientDetailVO.getIsUnknown());
			if(deceasedDtlVO.getUnidentifiedBody().equals(MortuaryConfig.YES))
				deceasedDtlVO.setPatFirstName(deceasedDtlVO.getPatFirstName().substring(9));
			
			if(patientDetailVO.getIsMLC()==null || patientDetailVO.getIsMLC().equals("0"))
				deceasedDtlVO.setIsMlcCase("0");
			else
				deceasedDtlVO.setIsMlcCase("1");
			//deceasedDtlVO.setIsMlcCase(patientDetailVO.getIsMLC());
			deceasedDtlVO.setDeathHistory(deadPatVO.getImmediateCause1());
			deceasedDtlVO.setAdd1(add1);
			deceasedDtlVO.setAdd2(add2);
			
				
			//Handovering The Deceased
			if(fb.getHandoverStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_HANDOVER))
			{
				//Handover To Police
				if(fb.getBodyHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_POLICE))
				{
					deceasedHandoverVO.setHandoverToName(fb.getOfficerName());
					deceasedHandoverVO.setOfficerBadgeNo(fb.getOfficerBadgeNo());
					deceasedHandoverVO.setOfficerDesignation(fb.getOfficerDesignation());
					deceasedHandoverVO.setIsHandoverTo(MortuaryConfig.BODY_HANDOVER_TO_POLICE);
					deceasedHandoverVO.setPoliceStation(fb.getPoliceStnHandOver());
					deceasedHandoverVO.setPoliceContactNo(fb.getPoliceContactNo());
					deceasedRelativeVO=null;
				}
				//Handover To Relative
				if(fb.getBodyHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_RELATIVE))
				{
					deceasedHandoverVO.setIsHandoverTo(MortuaryConfig.BODY_HANDOVER_TO_RELATIVE);
					deceasedHandoverVO.setHandoverToName(fb.getRelativeName());
					deceasedHandoverVO.setHandoverToAddress(fb.getRelativeAddress());
					deceasedHandoverVO.setHandoverToPhone(fb.getRelativeContactNo());
					deceasedHandoverVO.setRelativeCode(fb.getRelativeCode());
					
					deceasedRelativeVO.setRelativeName(fb.getRelativeName());
					deceasedRelativeVO.setRelativeAddress(fb.getRelativeAddress());
					deceasedRelativeVO.setRelativeContactNo(fb.getRelativeContactNo());
					deceasedRelativeVO.setRelativeCode(fb.getRelativeCode());
					deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_HANDOVER);
				}
				//Handover To Hospital Staff
				if(fb.getBodyHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF))
				{
					deceasedHandoverVO.setIsHandoverTo(MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF);
					deceasedRelativeVO=null;
				}
				deceasedStorageVO=null;
				deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_HANDOVER);
				//deceasedDtlVO.setIsClaimed(MortuaryConfig.IS_BODY_CLAIMED_YES);
			}
			
			//Deceased is In Streacher
			if(fb.getHandoverStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER))
			{
				if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER))
				{
					deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
					deceasedHandoverVO=null;
				//	deceasedRelativeVO=null;
					deceasedStorageVO=null;
				}	
			
				
			//Storing The Deceased Body
				if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE))
				{
					//Storage
					deceasedStorageVO.setChamberRackId(fb.getChamberRackId());
					deceasedStorageVO.setBodyPutBy(fb.getBodyPutBy());
					deceasedStorageVO.setStorageReason(fb.getStorageReason());
					deceasedStorageVO.setStorageUpto(fb.getStorageUpto());
					
					deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_CHAMBER);
					
					deceasedHandoverVO=null;
				}
				
				if(fb.getIsClaimed()!=null)
				{
					if(fb.getIsClaimed().equals(MortuaryConfig.IS_BODY_CLAIMED_YES))
					{
						deceasedRelativeVO.setRelativeName(fb.getStorageRelativeName());
						deceasedRelativeVO.setRelativeAddress(fb.getStorageRelativeAddress());
						deceasedRelativeVO.setRelativeCode(fb.getStorageRelativeCode());
						deceasedRelativeVO.setRelativeContactNo(fb.getStorageRelativeContactNo());
						deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
						deceasedDtlVO.setIsClaimed(MortuaryConfig.IS_BODY_CLAIMED_YES);
					}
					else
					{
						deceasedRelativeVO=null;
						deceasedDtlVO.setIsClaimed(MortuaryConfig.IS_BODY_CLAIMED_NO);
					}
				}
				else
				{
					deceasedRelativeVO=null;
					deceasedDtlVO.setIsClaimed(MortuaryConfig.IS_BODY_CLAIMED_NO);
				}	
			}
			
			if(fb.getIsMlcCase().equals(MortuaryConfig.IS_MLC_YES))
			{
				if(fb.getPoliceVerificationFlag().equals(MortuaryConfig.POLICE_VERIFICATION_NEW))	//New Police Verification
				{
					if(fb.getNewDutyOfficeFlag().equals(MortuaryConfig.DUTY_OFFICER_IS_IO))
					{
						fb.setNewDutyOffName(fb.getNewOfficerIncharge());
						fb.setNewDutyOffDesignation(fb.getNewIoDesignation());
						fb.setNewDutyOffBatchNo(fb.getNewIoBatchNo());;
					}
					policeVerVO.setCaseNo(fb.getNewCaseNo());
					policeVerVO.setPoliceStation(fb.getNewPoliceStation());
					policeVerVO.setDocketNo(fb.getNewDocketNo());
					policeVerVO.setOfficerIncharge(fb.getNewOfficerIncharge());
					policeVerVO.setIoDesignation(fb.getNewIoDesignation());
					policeVerVO.setIoBatchNo(fb.getNewIoBatchNo());
					policeVerVO.setDutyOffName(fb.getNewDutyOffName());
					policeVerVO.setDutyOffDesignation(fb.getNewDutyOffDesignation());
					policeVerVO.setDutyOffBatchNo(fb.getNewDutyOffBatchNo());
					policeVerVO.setCaseRemarks(fb.getNewCaseRemarks());
					policeVerVO.setDeathDate(deadPatVO.getDeathDate());
					policeVerVO.setPatMlcNo(fb.getPatMlcNo());
					policeVerVO.setEntryMode(MortuaryConfig.POLICE_VERIFICATION_ENTRY_MODE_NEW);
				}
				else		//Existing Police Verification
				{
					PoliceVerificationDtlVO policeVerificationDtlVO=(PoliceVerificationDtlVO)session.getAttribute(MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL);
					HelperMethods.populate(policeVerVO, policeVerificationDtlVO);
					policeVerVO.setDeathDate(deadPatVO.getDeathDate());
					policeVerVO.setEntryMode(MortuaryConfig.POLICE_VERIFICATION_ENTRY_MODE_MLC);
				}
				deceasedDtlVO.setPostmortemReq(MortuaryConfig.POSTMORTEM_REQUEST_COMPULSORY);
			}
			else
			{
				policeVerVO=null;
				deceasedDtlVO.setPostmortemReq(MortuaryConfig.POSTMORTEM_REQUEST_OPTIONAL);
			}
			
			deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_STAFF);
			deceasedBroughtVO.setEmpId(fb.getBroughtByName());
			
			////////////////////SAVING IMAGE/////////////
			int docLen=0,existImgLen=0;
			DocumentUploadDtlVO[] docUploadVO=(DocumentUploadDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			if(fb.getNewImage()!=null)
			{
				if(docUploadVO!=null)
					docLen=docUploadVO.length;
				else
					docLen=0;
			}
			if(fb.getExistingImage()!=null)
			{
				if(fb.getSelectedExistingImage()!=null)
					existImgLen=fb.getSelectedExistingImage().length;
				else
					existImgLen=0;
			}
			int count=existImgLen+docLen;
			if(count!=0)
			{
				deceasedImageDtlVO=new MortuaryDeceasedImageDtlVO[count]; 
				
				byteArray=(List)session.getAttribute(Config.IMAGE_BYTE_ARRAY);
			//	PatientImageDtlVO[] patientImageDtlVO=(PatientImageDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO);
				String defaultImage=fb.getIsDefaultImage();
				String existFlag=defaultImage.split("@")[0];
				String idx=defaultImage.split("@")[1];
				int i=0;
				
				boolean flag =HisFileControlUtil.isWindowsOS();
				String path="";
				if(flag)
					path=Config.MORTUARY_PATIENT_IMAGE_PATH_WINDOWS;
				else
					path=Config.MORTUARY_PATIENT_IMAGE_PATH_LINUX;
				
				if(fb.getSelectedExistingImage()!=null)		//Saving Existig Image
				{
					
					for(;i<fb.getSelectedExistingImage().length;i++)
					{
						int index=Integer.parseInt(fb.getSelectedExistingImage()[i].split("@")[1]);
						deceasedImageDtlVO[i]=new MortuaryDeceasedImageDtlVO();
						
						deceasedImageDtlVO[i].setImageFile((byte[])byteArray.get(index));
						deceasedImageDtlVO[i].setEpisodeCode(deadPatVO.getEpisodeCode());
						deceasedImageDtlVO[i].setPatCrNo(deadPatVO.getPatCrNo());
						deceasedImageDtlVO[i].setUploadMode(MortuaryConfig.IMAGE_UPLOAD_FROM_EMERGENCY);
						deceasedImageDtlVO[i].setFilePath(path);
						if(existFlag.equals(MortuaryConfig.IMAGE_EXIST_YES) && i==Integer.parseInt(idx))
							deceasedImageDtlVO[i].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_YES);
						else
							deceasedImageDtlVO[i].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_NO);
					}
				}
				
				if(docLen!=0)		//Saving New Image
				{
					for(int j=0;j<docUploadVO.length;j++)
					{
						deceasedImageDtlVO[i]=new MortuaryDeceasedImageDtlVO();
						deceasedImageDtlVO[i].setImageFile(docUploadVO[j].getDocFile());
						deceasedImageDtlVO[i].setEpisodeCode(deadPatVO.getEpisodeCode());
						deceasedImageDtlVO[i].setPatCrNo(deadPatVO.getPatCrNo());
						deceasedImageDtlVO[i].setUploadMode(MortuaryConfig.IMAGE_UPLOAD_FROM_MORTUARY);
						deceasedImageDtlVO[i].setFilePath(path);
						if(existFlag.equals(MortuaryConfig.IMAGE_EXIST_NO) && j==Integer.parseInt(idx))
							deceasedImageDtlVO[i].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_YES);
						else
							deceasedImageDtlVO[i].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_NO);
					}
				}
			}
			else
			{
				deceasedImageDtlVO=null;
			}
			deceasedNo = DeceasedAcceptanceDATA.saveDeceasedDetail(deceasedImageDtlVO,policeVerVO,deceasedBroughtVO,deceasedDtlVO,deceasedHandoverVO,deceasedRelativeVO,deceasedStorageVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Body Accepted Successfully");
			
			System.out.println("dcd");
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
		return deceasedNo;
	}
	
	/** Getting Police Verification Detail
	 * @param fb
	 * @param request
	 */
	public static void getPoliceVerificationDetail(DeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PoliceVerificationDtlVO policeVerificationDtlVO= new PoliceVerificationDtlVO();
		
		try
		{
			policeVerificationDtlVO=DeceasedAcceptanceDATA.getPoliceVerificationDetail(fb.getPatMlcNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL, policeVerificationDtlVO);
			fb.setPoliceVerificationExist(MortuaryConfig.POLICE_VERIFICATION_EXIST_YES);
			objStatus.add(Status.NEW, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			fb.setPoliceVerificationExist(MortuaryConfig.POLICE_VERIFICATION_EXIST_NO);
			objStatus.add(Status.NEW, "", "No Police Verification Exist.");
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
	
	/** Setting Police Verification Detail
	 * @param fb
	 * @param request
	 */
	public static void setExistingPoliceVerification(DeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session= request.getSession();
		
		try
		{
			PoliceVerificationDtlVO policeVerificationDtlVO=(PoliceVerificationDtlVO)session.getAttribute(MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL);
			//HelperMethods.populate(fb,policeVerificationDtlVO);
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
	
	
	/** Adding New Image
	 * @param fb
	 * @param request
	 */
	public static void addNewImage(DeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		String fileName="";
	//	String fileExt="";
		byte[] fileDataAsByte=null;
		DocumentUploadDtlVO[] docUploadVO1=null;
		DocumentUploadDtlVO[] docUploadVO2=null;
		DocumentUploadDtlVO[] docUploadVO3=null;
		DocumentUploadDtlVO[] docUploadVO=null;
		List byteArray=new ArrayList();
		
		
		try
		{
			fileDataAsByte=(byte[])session.getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
			fileName=(String)session.getAttribute(RegistrationConfig.UPLOADED_FILE_NAME);
			//fileExt=fileName.substring(fileName.lastIndexOf("."));
			
			docUploadVO1=(DocumentUploadDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			
			if(docUploadVO1==null)
			{
				docUploadVO2=new DocumentUploadDtlVO[1];
				docUploadVO2[0]=new DocumentUploadDtlVO();
				
				docUploadVO2[0].setDocFile(fileDataAsByte);
				docUploadVO2[0].setDocumentName(fileName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_NEW_ADDED_IMAGE, docUploadVO2);
				
				for(int k=0;k<docUploadVO2.length;k++)
				{
					byteArray.add(docUploadVO2[k].getDocFile());
				}
				WebUTIL.setAttributeInSession(request,MortuaryConfig.UPLOADED_IMAGE_IN_SESSION , byteArray);
			}
			else
			{
				docUploadVO3=new DocumentUploadDtlVO[docUploadVO1.length+1];
				int i=0;
				for(;i<docUploadVO1.length;i++)
				{
					docUploadVO3[i]=docUploadVO1[i];
				}
				
				docUploadVO3[i]=new DocumentUploadDtlVO();
				docUploadVO3[i].setDocFile(fileDataAsByte);
				docUploadVO3[i].setDocumentName(fileName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_NEW_ADDED_IMAGE, docUploadVO3);
				
				
				for(int k=0;k<docUploadVO3.length;k++)
				{
					byteArray.add(docUploadVO3[k].getDocFile());
				}
				WebUTIL.setAttributeInSession(request,MortuaryConfig.UPLOADED_IMAGE_IN_SESSION , byteArray);
			}
			
			String temp="";
			if(fb.getSelectedExistingImage()!=null)
			{
				for(int i=0;i<fb.getSelectedExistingImage().length;i++)
				{
					temp=temp+fb.getSelectedExistingImage()[i]+"#";
				}
				if(temp.length()>0)
					temp=temp.substring(0, temp.length()-1);
				
				fb.setTempChkValue(temp);
			}
			
			docUploadVO=(DocumentUploadDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			if(docUploadVO.length>0)
				fb.setNewAddImageExistFlag("1");
			
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
	
	
	/** Removing The Added Image
	 * @param fb
	 * @param request
	 */
	public static void removeNewImage(DeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		DocumentUploadDtlVO[] docUploadVO1=null;
		DocumentUploadDtlVO[] docUploadVO2=null;
		DocumentUploadDtlVO[] docUploadVO=null;
		List byteArray=new ArrayList();
		
		try
		{
			docUploadVO1=(DocumentUploadDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			docUploadVO2=new DocumentUploadDtlVO[docUploadVO1.length-1];
			
			int j=0;
			for(int i=0;i<docUploadVO1.length;i++)
			{
				if(Integer.parseInt(fb.getRemoveImageIndex())!=i)
				{
					docUploadVO2[j]=docUploadVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_NEW_ADDED_IMAGE,docUploadVO2);
			
			for(int k=0;k<docUploadVO2.length;k++)
			{
				byteArray.add(docUploadVO2[k].getDocFile());
			}
			WebUTIL.setAttributeInSession(request,MortuaryConfig.UPLOADED_IMAGE_IN_SESSION , byteArray);
			
			String temp="";
			if(fb.getSelectedExistingImage()!=null)
			{
				for(int i=0;i<fb.getSelectedExistingImage().length;i++)
				{
					temp=temp+fb.getSelectedExistingImage()[i]+"#";
				}
				if(temp.length()>0)
					temp=temp.substring(0, temp.length()-1);
				
				fb.setTempChkValue(temp);
			}
			
			docUploadVO=(DocumentUploadDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			
			if(docUploadVO.length==0)
				fb.setNewAddImageExistFlag("0");
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
}
