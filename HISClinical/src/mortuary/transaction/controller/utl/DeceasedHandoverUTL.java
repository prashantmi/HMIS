package mortuary.transaction.controller.utl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedHandoverDATA;
import mortuary.transaction.controller.fb.DeceasedHandoverFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

public class DeceasedHandoverUTL extends ControllerUTIL
{
	public static void getDeceasedListToHandover(DeceasedHandoverFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			deceasedDtlVO=DeceasedHandoverDATA.getDeceasedListToHandover(getUserVO(request));
			
			for(int i=0;i<deceasedDtlVO.length;i++)
			{
				if(deceasedDtlVO[i].getBodyStatus().equals(MortuaryConfig.BODY_STATUS_IN_CHAMBER))
					deceasedDtlVO[i].setBodyStatusName("Chamber");
				if(deceasedDtlVO[i].getBodyStatus().equals(MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER))
					deceasedDtlVO[i].setBodyStatusName("Stretcher");
				
				if(deceasedDtlVO[i].getUnidentifiedBody()==null || deceasedDtlVO[i].getUnidentifiedBody().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_UNKNOWN);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_UNKNOWN);
			
				else if(deceasedDtlVO[i].getIsBroughtDead().equals(RegistrationConfig.IS_BROUGHT_DEAD_TRUE))
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_BROUGHT_DEAD);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_BROUGHT_DEAD);
				
				else if(deceasedDtlVO[i].getIsMlcCase().equals(MortuaryConfig.IS_MLC_YES))
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_MLC);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_MLC);
				
				else
					deceasedDtlVO[i].setDeceasedType(MortuaryConfig.DECEASED_TYPE_NORMAL);
					//deceasedDtlVO[i].setColor(MortuaryConfig.DECEASED_COLOR_NORMAL);
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.DECEASED_LIST_TO_HANDOVER, deceasedDtlVO);
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
	
	
	public static void getDeceasedEssentialDetailForHandover(DeceasedHandoverFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String chamberRackName="";
		String postmortemReq="";
		String isHandover="";
		String postmortemStatus="";
		
		try
		{
			DeceasedDetailVO[] deceasedDtlVO=(DeceasedDetailVO[])session.getAttribute(MortuaryConfig.DECEASED_LIST_TO_HANDOVER);
			for(int i=0;i<deceasedDtlVO.length;i++)
			{
				if(fb.getDeceasedNo().equals(deceasedDtlVO[i].getDeceasedNo()))
				{
					fb.setIsMlcCase(deceasedDtlVO[i].getIsMlcCase());
					fb.setBodyStatus(deceasedDtlVO[i].getBodyStatus());
					postmortemReq=deceasedDtlVO[i].getPostmortemReq();
					break;
				}
			}
			
			if(fb.getBodyStatus().equals(MortuaryConfig.BODY_STATUS_IN_CHAMBER))
			{
				chamberRackName=DeceasedHandoverDATA.getChamberRackName(fb.getDeceasedNo(),getUserVO(request));
				fb.setChamberName(chamberRackName.split("@")[0]);
				fb.setRackName(chamberRackName.split("@")[1]);
			}
			
			List lstRelation=DeceasedHandoverDATA.getAllRelationship(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION, lstRelation);
			
			DeceasedRelativeDtlVO[] deceasedRelativeVO=DeceasedHandoverDATA.getExistingRelative(fb.getDeceasedNo(),getUserVO(request));
			if(deceasedRelativeVO!=null)
			{
				fb.setExistingRelativeFlag(MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING);
				fb.setRelativeExist(MortuaryConfig.RELATIVE_EXIST_TO_HANDOVER_YES);
			}
			else
			{
				fb.setExistingRelativeFlag(MortuaryConfig.DEAD_BODY_HANDOVER_NEW);
				fb.setRelativeExist(MortuaryConfig.RELATIVE_EXIST_TO_HANDOVER_NO);
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_RELATIVE_VO, deceasedRelativeVO);
			
			Map map=DeceasedHandoverDATA.checkForDeceasedHandover(fb.getDeceasedNo(),postmortemReq,getUserVO(request));
			
			isHandover=(String)map.get(MortuaryConfig.IS_DECEASED_HANDOVER_FLAG);
			postmortemStatus=(String)map.get(MortuaryConfig.DECEASED_POSTMORTEM_STATUS);
			
			fb.setIsHandover(isHandover);
			fb.setPostmortemStatus(postmortemStatus);
			/*if(deceasedRelativeVO!=null)
			{
				fb.setHandoverToName(deceasedRelativeVO.getRelativeName());
				fb.setHandoverToAddress(deceasedRelativeVO.getRelativeAddress());
				fb.setHandoverToPhone(deceasedRelativeVO.getRelativeContactNo());
				fb.setRelativeCode(deceasedRelativeVO.getRelativeCode());
				fb.setExistingRelativeFlag(MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING);
				fb.setRelativeExist(MortuaryConfig.RELATIVE_EXIST_TO_HANDOVER_YES);
			}
			else
			{
				fb.setExistingRelativeFlag(MortuaryConfig.DEAD_BODY_HANDOVER_NEW);
				fb.setRelativeExist(MortuaryConfig.RELATIVE_EXIST_TO_HANDOVER_NO);
			}*/
			if(fb.getIsHandover().equals(MortuaryConfig.NO))
				objStatus.add(Status.TRANSINPROCESS,"","Deceased Cannot Be Handover Until The Postmortem is Completed");
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
	
	public static void saveDeceasedHandoverDetail(DeceasedHandoverFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		
		DeceasedHandoverDtlVO deceasedHandoverVO=new DeceasedHandoverDtlVO();
		DeceasedRelativeDtlVO deceasedRelativeVO=new DeceasedRelativeDtlVO();
		String printFlag="1";
		String deceasedNo="";
		try
		{
			//int index=Integer.parseInt(fb.getRelativeExistRadioBtn());
			DeceasedRelativeDtlVO[] deceasedExistingRelativeVO=(DeceasedRelativeDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_RELATIVE_VO);
			deceasedHandoverVO.setIsHandoverTo(fb.getIsHandoverTo());
			deceasedHandoverVO.setDeceasedNo(fb.getDeceasedNo());
			String bodyStatus=fb.getBodyStatus();
			deceasedNo = fb.getDeceasedNo();
			//HandoverTo Police
			if(fb.getIsHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_POLICE))
			{
				deceasedHandoverVO.setHandoverToName(fb.getOfficerName());
				deceasedHandoverVO.setOfficerDesignation(fb.getOfficerDesignation());
				deceasedHandoverVO.setOfficerBadgeNo(fb.getOfficerBadgeNo());
				deceasedHandoverVO.setPoliceContactNo(fb.getPoliceContactNo());
				deceasedHandoverVO.setPoliceStation(fb.getPoliceStnHandOver());
				deceasedRelativeVO=null;
			}
			
			//HandoverTo Relative
			if(fb.getIsHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_RELATIVE))
			{
				if(fb.getExistingRelativeFlag().equals(MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING))
				{
					int index=Integer.parseInt(fb.getRelativeExistRadioBtn());
					deceasedHandoverVO.setHandoverToName(deceasedExistingRelativeVO[index].getRelativeName());
					deceasedHandoverVO.setHandoverToAddress(deceasedExistingRelativeVO[index].getRelativeAddress());
					deceasedHandoverVO.setHandoverToPhone(deceasedExistingRelativeVO[index].getRelativeContactNo());
					deceasedHandoverVO.setRelativeCode(deceasedExistingRelativeVO[index].getRelativeCode());
					
					deceasedRelativeVO.setDeceasedNo(fb.getDeceasedNo());
					deceasedRelativeVO.setRelativeName(deceasedExistingRelativeVO[index].getRelativeName());
					deceasedRelativeVO.setRelativeAddress(deceasedExistingRelativeVO[index].getRelativeAddress());
					deceasedRelativeVO.setRelativeCode(deceasedExistingRelativeVO[index].getRelativeCode());
					deceasedRelativeVO.setRelativeContactNo(deceasedExistingRelativeVO[index].getRelativeContactNo());
					deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_HANDOVER);
				}
				if(fb.getExistingRelativeFlag().equals(MortuaryConfig.DEAD_BODY_HANDOVER_NEW))
				{
					deceasedHandoverVO.setHandoverToName(fb.getNewHandoverToName());
					deceasedHandoverVO.setHandoverToAddress(fb.getNewHandoverToAddress());
					deceasedHandoverVO.setHandoverToPhone(fb.getNewHandoverToPhone());
					deceasedHandoverVO.setRelativeCode(fb.getNewRelativeCode());
					
					deceasedRelativeVO.setDeceasedNo(fb.getDeceasedNo());
					deceasedRelativeVO.setRelativeName(fb.getNewHandoverToName());
					deceasedRelativeVO.setRelativeAddress(fb.getNewHandoverToAddress());
					deceasedRelativeVO.setRelativeCode(fb.getNewRelativeCode());
					deceasedRelativeVO.setRelativeContactNo(fb.getNewHandoverToPhone());
					deceasedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_HANDOVER);
				}
			}
			
			//Handover To Hospital Staff
			if(fb.getIsHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF))
			{
				deceasedRelativeVO=null;
			}
			
			DeceasedHandoverDATA.saveDeceasedHandoverDetail(bodyStatus,deceasedRelativeVO,deceasedHandoverVO,getUserVO(request));
			fb.setPrintFlag(printFlag);
			fb.setDeceasedNo(deceasedNo);
			objStatus.add(Status.DONE,"","Handover Details Saved Successfully");
			
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
	
	public static void getDeceasedHandoverDetail(String deceasedNo,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedHandoverDtlVO deceasedHandOverDtlVO=null;
		HospitalMstVO hospVO=getHospitalVO(request);
		try
		{
			deceasedHandOverDtlVO=DeceasedHandoverDATA.getDeceasedDtlAfterHandover(deceasedNo,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.DECEASED_HANDOVER_DTL_VO, deceasedHandOverDtlVO);
			WebUTIL.setAttributeInSession(request, MortuaryConfig.HOSPITAL_MST_VO , hospVO);
			
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
