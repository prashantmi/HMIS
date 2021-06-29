package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.ExternalDeceasedAcceptanceDATA;
import mortuary.transaction.controller.fb.ExternalDeceasedAcceptanceFB;

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
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;

public class ExternalDeceasedAcceptanceUTL extends ControllerUTIL
{
	public static void getEssentialForExternalBodyAcceptance(ExternalDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setHiddenTimeHr(time.split(":")[0]);
			fb.setHiddenTimeMin(time.split(":")[1]);
			
			
			Map map=ExternalDeceasedAcceptanceDATA.getEssentialForExternalBodyAcceptance(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
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
	
	public static void getRackBasedOnChamber(ExternalDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session= request.getSession();
		List lstRack=new ArrayList();
		
		try
		{
			if(fb.getDeceasedFrom().equals(MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_HOSPITAL))
			{
				if(!fb.getUnidentifiedBody().equals(""))
					fb.setUnknownChkValue(MortuaryConfig.YES);
				else
					fb.setUnknownChkValue(MortuaryConfig.NO);
				
				if(!fb.getIsMlcCase().equals(""))
					fb.setMlcChkValue(MortuaryConfig.YES);
				else
					fb.setMlcChkValue(MortuaryConfig.NO);
			}
				
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
	
	public static void saveExternalDeceasedAcceptance(ExternalDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		DeceasedRelativeDtlVO deceasedRelativeVO=new DeceasedRelativeDtlVO();
		DeceasedStorageDtlVO deceasedStorageVO=new DeceasedStorageDtlVO();
		DeceasedBroughtByDtlVO deceasedBroughtVO=new DeceasedBroughtByDtlVO();
		MortuaryPoliceVerificationVO policeVerVO= new MortuaryPoliceVerificationVO();
		Boolean mlcChk = true;
		try
		{
			//////OTHER HOSPITAL/////START/////////
				HelperMethods.populate(deceasedDtlVO, fb);
				deceasedDtlVO.setDeathDate(fb.getDeathDate()+" "+fb.getDeathTimeHr()+":"+fb.getDeathTimeMin());
				deceasedDtlVO.setEntryMode(MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_HOSPITAL);
				deceasedDtlVO.setPatIdMark1(fb.getPatientIdMark1());
				deceasedDtlVO.setPatIdMark2(fb.getPatientIdMark2());
				
				if(fb.getIsAssociated().equals(MortuaryConfig.YES))
				{
					String hosName="";
					List lstAssoHospital=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_ASSOCIATED_HOSPITAL_LIST);
					for(int i=0;i<lstAssoHospital.size();i++)
					{
						Entry ent=(Entry)lstAssoHospital.get(i);
						if(fb.getAssoHospitalCode().equals(ent.getValue()))
						{	
							hosName=ent.getLabel();
							break;
						}	
					}
					deceasedDtlVO.setExtHospitalName(hosName);
				}
				
				if(fb.getIsClaimed()!=null)
					deceasedDtlVO.setIsClaimed(MortuaryConfig.YES);
				else
					deceasedDtlVO.setIsClaimed(MortuaryConfig.NO);
				
				if(fb.getUnidentifiedBody()!=null && !fb.getUnidentifiedBody().equals(""))
				{
					deceasedDtlVO.setUnidentifiedBody(MortuaryConfig.YES);
					deceasedDtlVO.setIsMlcCase(MortuaryConfig.YES);
					deceasedDtlVO.setPostmortemReq(MortuaryConfig.POSTMORTEM_REQUEST_COMPULSORY);
					deceasedDtlVO.setIsClaimed(MortuaryConfig.NO);
				}
				else
				{
					if(fb.getIsMlcCase()!=null && !fb.getIsMlcCase().equals(""))
					{
						deceasedDtlVO.setUnidentifiedBody(MortuaryConfig.NO);
						deceasedDtlVO.setIsMlcCase(MortuaryConfig.YES);
						deceasedDtlVO.setPostmortemReq(MortuaryConfig.POSTMORTEM_REQUEST_COMPULSORY);
						
						if(fb.getIsAccompained()!=null)
							deceasedDtlVO.setIsClaimed(MortuaryConfig.YES);
						else
							deceasedDtlVO.setIsClaimed(MortuaryConfig.NO);
					}
					else
					{
						deceasedDtlVO.setUnidentifiedBody(MortuaryConfig.NO);
						deceasedDtlVO.setIsMlcCase(MortuaryConfig.NO);
						deceasedDtlVO.setPostmortemReq(MortuaryConfig.POSTMORTEM_REQUEST_OPTIONAL);
						
						if(fb.getIsClaimed().equals(MortuaryConfig.YES))
							deceasedDtlVO.setIsClaimed(MortuaryConfig.YES);
						else
							deceasedDtlVO.setIsClaimed(MortuaryConfig.NO);
					}
				}
				
				
				if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER))
				{
					deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
					deceasedStorageVO=null;
				}
				
				//Storing The Deceased Body
				if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE))
				{
					deceasedStorageVO.setChamberRackId(fb.getChamberRackId());
					deceasedStorageVO.setBodyPutBy(fb.getBodyPutBy());
					deceasedStorageVO.setStorageReason(fb.getStorageReason());
					deceasedStorageVO.setStorageUpto(fb.getStorageUpto());
					
					deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_CHAMBER);
				}
				
				if(fb.getUnidentifiedBody()!=null && !fb.getUnidentifiedBody().equals(""))
				{
					if(fb.getDutyOfficeFlag().equals(MortuaryConfig.DUTY_OFFICER_IS_IO))
					{
						fb.setDutyOffName(fb.getOfficerIncharge());
						fb.setDutyOffDesignation(fb.getIoDesignation());
						fb.setDutyOffBatchNo(fb.getIoBatchNo());
					}
					HelperMethods.populate(policeVerVO, fb);
					policeVerVO.setDeathDate(fb.getDeathDate()+" "+fb.getDeathTimeHr()+":"+fb.getDeathTimeMin());
					policeVerVO.setEntryMode(MortuaryConfig.POLICE_VERIFICATION_ENTRY_MODE_NEW);
					policeVerVO.setPatMlcNo(fb.getMlcNo());
					
					deceasedRelativeVO=null;
					deceasedBroughtVO.setBroughtByName(fb.getOfficerIncharge());
					deceasedBroughtVO.setOfficerDesignation(fb.getIoDesignation());
					deceasedBroughtVO.setOfficerBadgeNo(fb.getIoBatchNo());
					deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_POLICE);
					deceasedBroughtVO.setPoliceStnAccmponiedBy(fb.getPoliceStnAccmponiedBy());
					deceasedBroughtVO.setPoliceContactNo(fb.getPoliceContactNo());
				}
				else
				{
					if(fb.getIsMlcCase()!=null && !fb.getIsMlcCase().equals(""))
					{
						if(fb.getDutyOfficeFlag().equals(MortuaryConfig.DUTY_OFFICER_IS_IO))
						{
							fb.setDutyOffName(fb.getOfficerIncharge());
							fb.setDutyOffDesignation(fb.getIoDesignation());
							fb.setDutyOffBatchNo(fb.getIoBatchNo());
						}
						HelperMethods.populate(policeVerVO, fb);
						policeVerVO.setDeathDate(fb.getDeathDate()+" "+fb.getDeathTimeHr()+":"+fb.getDeathTimeMin());
						policeVerVO.setEntryMode(MortuaryConfig.POLICE_VERIFICATION_ENTRY_MODE_NEW);
						policeVerVO.setPatMlcNo(fb.getMlcNo());
						
						// To Prevent Same MLC No entry at the External Deceased Acceptance By Shruti Shail on 11-May-2017
						mlcChk = ExternalDeceasedAcceptanceDATA.chkDuplicateMLC(policeVerVO.getPatMlcNo(),getUserVO(request).getHospitalCode());
						
						
						
						if(fb.getIsAccompained()!=null)
						{
							deceasedRelativeVO.setRelativeAddress(fb.getAccRelativeAddress());
							deceasedRelativeVO.setRelativeCode(fb.getAccRelativeCode());
							deceasedRelativeVO.setRelativeContactNo(fb.getAccRelativeContactNo());
							deceasedRelativeVO.setRelativeName(fb.getAccRelativeName());
							deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
						}
						else
							deceasedRelativeVO=null;
						
						deceasedBroughtVO.setBroughtByName(fb.getOfficerIncharge());
						deceasedBroughtVO.setOfficerDesignation(fb.getIoDesignation());
						deceasedBroughtVO.setOfficerBadgeNo(fb.getIoBatchNo());
						deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_POLICE);
						deceasedBroughtVO.setPoliceStnAccmponiedBy(fb.getPoliceStnAccmponiedBy());
						deceasedBroughtVO.setPoliceContactNo(fb.getPoliceContactNo());
					}
					else
					{
						policeVerVO=null;
						if(fb.getIsClaimed().equals(MortuaryConfig.YES))
						{
							HelperMethods.populate(deceasedRelativeVO, fb);
							deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
							
							deceasedBroughtVO.setBroughtByName(fb.getRelativeName());
							deceasedBroughtVO.setBroughtByAddress(fb.getRelativeAddress());
							deceasedBroughtVO.setBroughtByPhone(fb.getRelativeContactNo());
							deceasedBroughtVO.setRelativeCode(fb.getRelativeCode());
							deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_RELATIVE);
						}
						else
						{
							deceasedRelativeVO=null;
							deceasedBroughtVO.setBroughtByName(fb.getBrtOffName());
							deceasedBroughtVO.setOfficerDesignation(fb.getBrtOffDesignation());
							deceasedBroughtVO.setOfficerBadgeNo(fb.getBrtOffBatchNo());
							deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_POLICE);
							deceasedBroughtVO.setPoliceStnAccmponiedBy(fb.getPoliceStnAccmponiedBy());
							deceasedBroughtVO.setPoliceContactNo(fb.getPoliceContactNo());
						}
					}
				}
				
				deceasedDtlVO.setIsBroughtDead(MortuaryConfig.YES);
				String deceasedNo="";
				if(mlcChk)
				{
					deceasedNo = ExternalDeceasedAcceptanceDATA.saveExternalDeceasedAcceptance(deceasedDtlVO,deceasedRelativeVO,deceasedStorageVO,deceasedBroughtVO,policeVerVO,getUserVO(request));
					objStatus.add(Status.DONE,"","Deceased Registered Successfully with Deceased No. :: " + deceasedNo);
				}
				else
					objStatus.add(Status.NEW,"","Deceased With Given MLC No already exists :: " + policeVerVO.getPatMlcNo());
			
			//////OTHER HOSPITAL/////END/////////
			
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
	
	public static void saveOnSpotDeceasedAcceptance(ExternalDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		DeceasedStorageDtlVO deceasedStorageVO=new DeceasedStorageDtlVO();
		DeceasedBroughtByDtlVO deceasedBroughtVO=new DeceasedBroughtByDtlVO();
		MortuaryPoliceVerificationVO policeVerVO= new MortuaryPoliceVerificationVO();
		DeceasedRelativeDtlVO deceasedRelativeVO=new DeceasedRelativeDtlVO();
		
		
		try
		{
			HelperMethods.populate(deceasedDtlVO, fb);
			deceasedDtlVO.setDeathDate(fb.getDeathDate()+" "+fb.getDeathTimeHr()+":"+fb.getDeathTimeMin());
			deceasedDtlVO.setEntryMode(MortuaryConfig.ENTRY_MODE_BROUGHT_DEAD_FROM_SPOT_BY_POLICE);
		//	deceasedDtlVO.setUnidentifiedBody(MortuaryConfig.YES);
			deceasedDtlVO.setIsMlcCase(MortuaryConfig.NO);
			deceasedDtlVO.setPostmortemReq(MortuaryConfig.POSTMORTEM_REQUEST_OPTIONAL);
			deceasedDtlVO.setPatIdMark1(fb.getPatientIdMark1());
			deceasedDtlVO.setPatIdMark2(fb.getPatientIdMark2());
			if(fb.getUnidentifiedBody()!=null && !fb.getUnidentifiedBody().equals(""))
				deceasedDtlVO.setUnidentifiedBody(MortuaryConfig.YES);
			else
				deceasedDtlVO.setUnidentifiedBody(MortuaryConfig.NO);
				
			if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER))
			{
				deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
				deceasedStorageVO=null;
			}
			
			//Storing The Deceased Body
			if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE))
			{
				deceasedStorageVO.setChamberRackId(fb.getChamberRackId());
				deceasedStorageVO.setBodyPutBy(fb.getBodyPutBy());
				deceasedStorageVO.setStorageReason(fb.getStorageReason());
				deceasedStorageVO.setStorageUpto(fb.getStorageUpto());
				
				deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_CHAMBER);
			}
			
			if(fb.getDutyOfficeFlag().equals(MortuaryConfig.DUTY_OFFICER_IS_IO))
			{
				fb.setDutyOffName(fb.getOfficerIncharge());
				fb.setDutyOffDesignation(fb.getIoDesignation());
				fb.setDutyOffBatchNo(fb.getIoBatchNo());
			}
			HelperMethods.populate(policeVerVO, fb);
			policeVerVO.setDeathDate(fb.getDeathDate()+" "+fb.getDeathTimeHr()+":"+fb.getDeathTimeMin());
			policeVerVO.setEntryMode(MortuaryConfig.POLICE_VERIFICATION_ENTRY_MODE_NEW);
			
		
			deceasedBroughtVO.setBroughtByName(fb.getOfficerIncharge());
			deceasedBroughtVO.setOfficerDesignation(fb.getIoDesignation());
			deceasedBroughtVO.setOfficerBadgeNo(fb.getIoBatchNo());
			deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_POLICE);
			deceasedBroughtVO.setBroughtLocation(fb.getBroughtLocation());
			deceasedBroughtVO.setPoliceStnAccmponiedBy(fb.getPoliceStnAccmponiedBy());
			deceasedBroughtVO.setPoliceContactNo(fb.getPoliceContactNo());
		//	deceasedDtlVO.setPatFirstName("Unknown");
			deceasedDtlVO.setIsBroughtDead(MortuaryConfig.YES);
		//	deceasedDtlVO.setIsActualDob(MortuaryConfig.NO);
			
			if(!fb.getRelativeName().equals(""))
			{
				HelperMethods.populate(deceasedRelativeVO, fb);
				deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
				deceasedDtlVO.setIsClaimed(MortuaryConfig.YES);
			}
			else
			{
				deceasedRelativeVO=null;
				deceasedDtlVO.setIsClaimed(MortuaryConfig.NO);
			}
			
			String deceasedNo = ExternalDeceasedAcceptanceDATA.saveOnSpotDeceasedAcceptance(deceasedRelativeVO,deceasedDtlVO,deceasedStorageVO,deceasedBroughtVO,policeVerVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Deceased Registered Successfully with Deceased No. :: " + deceasedNo);
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
	
	public static void saveExternalDeceasedOtherPlaceAcceptance(ExternalDeceasedAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		DeceasedRelativeDtlVO deceasedRelativeVO=new DeceasedRelativeDtlVO();
		DeceasedStorageDtlVO deceasedStorageVO=new DeceasedStorageDtlVO();
		DeceasedBroughtByDtlVO deceasedBroughtVO=new DeceasedBroughtByDtlVO();
		MortuaryPoliceVerificationVO policeVerVO= new MortuaryPoliceVerificationVO();
		
		try
		{
			//////OTHER HOSPITAL/////START/////////
				HelperMethods.populate(deceasedDtlVO, fb);
				deceasedDtlVO.setDeathDate(fb.getDeathDate()+" "+fb.getDeathTimeHr()+":"+fb.getDeathTimeMin());
				deceasedDtlVO.setEntryMode(MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_PLACES);
				deceasedDtlVO.setPatIdMark1(fb.getPatientIdMark1());
				deceasedDtlVO.setPatIdMark2(fb.getPatientIdMark2());
				deceasedDtlVO.setRemarks(fb.getRemarks());
				deceasedDtlVO.setExtHospitalName(fb.getPlaceType());
				deceasedDtlVO.setExtPatCrNo(fb.getPatID());
				deceasedDtlVO.setExtDeptName(fb.getPlcAddress());
				deceasedDtlVO.setExtHospitalContactNo(fb.getPlcContact());
				
				if(fb.getIsClaimed()!=null)
					deceasedDtlVO.setIsClaimed(MortuaryConfig.YES);
				else
					deceasedDtlVO.setIsClaimed(MortuaryConfig.NO);
				
				
				
				
				if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER))
				{
					deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
					deceasedStorageVO=null;
				}
				
				//Storing The Deceased Body
				if(fb.getStorageFlag().equals(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE))
				{
					deceasedStorageVO.setChamberRackId(fb.getChamberRackId());
					deceasedStorageVO.setBodyPutBy(fb.getBodyPutBy());
					deceasedStorageVO.setStorageReason(fb.getStorageReason());
					deceasedStorageVO.setStorageUpto(fb.getStorageUpto());
					
					deceasedDtlVO.setBodyStatus(MortuaryConfig.BODY_STATUS_IN_CHAMBER);
				}
				
							HelperMethods.populate(deceasedRelativeVO, fb);
							deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
							
							deceasedBroughtVO.setBroughtByName(fb.getRelativeName());
							deceasedBroughtVO.setBroughtByAddress(fb.getRelativeAddress());
							deceasedBroughtVO.setBroughtByPhone(fb.getRelativeContactNo());
							deceasedBroughtVO.setRelativeCode(fb.getRelativeCode());
							deceasedBroughtVO.setIsBroughtBy(MortuaryConfig.BODY_BROUGHT_BY_HOSPITAL_RELATIVE);
						
					
				
				
				deceasedDtlVO.setIsBroughtDead(MortuaryConfig.YES);
				policeVerVO = null;
				String deceasedNo = ExternalDeceasedAcceptanceDATA.saveExternalDeceasedAcceptance(deceasedDtlVO,deceasedRelativeVO,deceasedStorageVO,deceasedBroughtVO,policeVerVO,getUserVO(request));
				objStatus.add(Status.DONE,"","Deceased Registered Successfully with Deceased No. :: " + deceasedNo);
			
			//////OTHER HOSPITAL/////END/////////
			
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
