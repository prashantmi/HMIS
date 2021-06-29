package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.PostmortemRequestDATA;
import mortuary.transaction.controller.fb.PostmortemRequestFB;

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
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;


public class PostmortemRequestUTL extends ControllerUTIL
{
	public static void getDeceasedListForPostmortemRequest(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			setSysdate(request);
			deceasedDtlVO=PostmortemRequestDATA.getDeceasedListForPostmortemRequest(getUserVO(request));
			
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
			WebUTIL.setAttributeInSession(request, MortuaryConfig.DECEASED_LIST_FOR_POSTMORTEM_REQUEST, deceasedDtlVO);
			
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
	
	public static void getEssentialForPostmortemRequest(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setHiddenTimeHr(time.split(":")[0]);
			fb.setHiddenTimeMin(time.split(":")[1]);
			DeceasedDetailVO[] deceasedDtlVO=(DeceasedDetailVO[])session.getAttribute(MortuaryConfig.DECEASED_LIST_FOR_POSTMORTEM_REQUEST);
			for(int i=0;i<deceasedDtlVO.length;i++)
			{
				if(fb.getDeceasedNo().equals(deceasedDtlVO[i].getDeceasedNo()))
				{
					fb.setDeathDateTime(deceasedDtlVO[i].getDeathDate());
					fb.setReceiveDateTime(deceasedDtlVO[i].getReceivedDateTime());
					fb.setIsMlc(deceasedDtlVO[i].getIsMlcCase());
				}
			}
			
			Map map=PostmortemRequestDATA.getEssentialForPostmortemRequest(fb.getDeceasedNo(),getUserVO(request));
			
			ConsentMappingMasterVO[] consentMappingVO=(ConsentMappingMasterVO[])map.get(MortuaryConfig.ARR_CONSENT_MAPPING_DETAIL);
			if(consentMappingVO==null)
			{
				fb.setIsConsentRequired(MortuaryConfig.NO);
			}
			else
			{
				for(int i=0;i<consentMappingVO.length;i++)
				{
					if(consentMappingVO[i].getServiceId().equals(MortuaryConfig.POSTMORTEM_TYPE_CLINICAL))
					{
						fb.setIsConsentRequired(MortuaryConfig.YES);
						fb.setTemplateId(consentMappingVO[i].getTemplateId());
					}
					else
						fb.setIsConsentRequired(MortuaryConfig.NO);
				}
			}
			
			MortuaryPoliceVerificationVO policeVerVO=(MortuaryPoliceVerificationVO)map.get(MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL);
			if(policeVerVO!=null)
			{
				HelperMethods.populatetToNullOrEmpty(fb, policeVerVO);
				if(policeVerVO.getIoBatchNo().equalsIgnoreCase(policeVerVO.getDutyOffBatchNo()))
					fb.setDutyOfficeFlag(MortuaryConfig.DUTY_OFFICER_IS_IO);
				else
					fb.setDutyOfficeFlag(MortuaryConfig.DUTY_OFFICER_IS_OTHER);
				fb.setExistPoliceVerFlag(MortuaryConfig.POLICE_VERIFICATION_EXIST_YES);
				fb.setPoliceVerExistNew(MortuaryConfig.POLICE_VERIFICATION_EXISTING);
				
				fb.setExistingDeathDate(fb.getDeathDateTime().split(" ")[0]);
				fb.setExistingDeathTimeHr(fb.getDeathDateTime().split(" ")[1].split(":")[0]);
				fb.setExistingDeathTimeMin(fb.getDeathDateTime().split(" ")[1].split(":")[1]);
			}
			else
			{
				fb.setExistPoliceVerFlag(MortuaryConfig.POLICE_VERIFICATION_EXIST_NO);
				fb.setPoliceVerExistNew(MortuaryConfig.POLICE_VERIFICATION_NEW);
			}
			WebUTIL.setMapInSession(map, request);
			
			if(fb.getIsMlc().equals(MortuaryConfig.IS_MLC_NO))
				fb.setPostmortemType(MortuaryConfig.POSTMORTEM_TYPE_FORENSIC);
			List lstDeceasedItem=(List)map.get(MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM);
			if(lstDeceasedItem.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Deceased Item Found");
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
	
	public static boolean savePostmortemRequest(PostmortemRequestFB fb,HttpServletRequest request)
	{
		boolean flg = true;
		Status objStatus = new Status();
		HttpSession session= request.getSession();
		PostmortemItemReqDtlVO[] postmortemItemVO=null;
		PostmortemOpinionReqDtlVO[] postmortemOpinionVO=null;
		DeceasedRelativeDtlVO[] addedRelativeVO=null;
		PostmortemRequestDetailVO postmortemRequestDtlVO=new PostmortemRequestDetailVO();
		MortuaryPoliceVerificationVO postmortemPoliceVerVO=new MortuaryPoliceVerificationVO();
		List<PostmortemItemReqDtlVO> lstItemVO=new ArrayList<PostmortemItemReqDtlVO>();
		List<PostmortemOpinionReqDtlVO> lstOpinionVO=new ArrayList<PostmortemOpinionReqDtlVO>();
		List<DeceasedRelativeDtlVO> lstRelativeVO=new ArrayList<DeceasedRelativeDtlVO>();
		
		
		try
		{
			if(fb.getPostmortemType().equals(MortuaryConfig.POSTMORTEM_TYPE_FORENSIC))
			{
				MortuaryPoliceVerificationVO policeVerVO=(MortuaryPoliceVerificationVO)session.getAttribute(MortuaryConfig.EXISTING_POLICE_VERIFICATION_DETAIL);
				addedRelativeVO=(DeceasedRelativeDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO);
				
				if(fb.getPoliceVerExistNew().equals(MortuaryConfig.POLICE_VERIFICATION_NEW))
				{
					if(fb.getNewDutyOfficeFlag().equals(MortuaryConfig.DUTY_OFFICER_IS_IO))
					{
						fb.setNewDutyOffName(fb.getNewOfficerIncharge());
						fb.setNewDutyOffDesignation(fb.getNewIoDesignation());
						fb.setNewDutyOffBatchNo(fb.getNewIoBatchNo());
					}
			//Postmortem Request		
					postmortemRequestDtlVO.setCaseNo(fb.getNewCaseNo());
					postmortemRequestDtlVO.setCaseRemarks(fb.getNewCaseRemarks());
					postmortemRequestDtlVO.setPoliceStation(fb.getNewPoliceStation());
					postmortemRequestDtlVO.setDocketNo(fb.getNewDocketNo());
					postmortemRequestDtlVO.setOfficerIncharge(fb.getNewOfficerIncharge());
					postmortemRequestDtlVO.setIoDesignation(fb.getNewIoDesignation());
					postmortemRequestDtlVO.setIoBatchNo(fb.getNewIoBatchNo());
					postmortemRequestDtlVO.setDutyOffName(fb.getNewDutyOffName());
					postmortemRequestDtlVO.setDutyOffDesignation(fb.getNewDutyOffDesignation());
					postmortemRequestDtlVO.setDutyOffBatchNo(fb.getNewDutyOffBatchNo());
					postmortemRequestDtlVO.setDeathDate(fb.getNewDeathDate()+" "+fb.getNewDeathTimeHr()+":"+fb.getNewDeathTimeMin());
					postmortemRequestDtlVO.setIncidenceDate(fb.getNewIncicenceDate()+" "+fb.getNewIncicenceTimeHr()+":"+fb.getNewIncicenceTimeMin());
					postmortemRequestDtlVO.setDeathPlace(fb.getNewDeathPlace());
					if(policeVerVO!=null)
						postmortemRequestDtlVO.setPatMlcNo(policeVerVO.getPatMlcNo());
					
			//New Police Verification		
					postmortemPoliceVerVO.setCaseNo(fb.getNewCaseNo());
					postmortemPoliceVerVO.setCaseRemarks(fb.getNewCaseRemarks());
					postmortemPoliceVerVO.setPoliceStation(fb.getNewPoliceStation());
					postmortemPoliceVerVO.setDocketNo(fb.getNewDocketNo());
					postmortemPoliceVerVO.setOfficerIncharge(fb.getNewOfficerIncharge());
					postmortemPoliceVerVO.setIoDesignation(fb.getNewIoDesignation());
					postmortemPoliceVerVO.setIoBatchNo(fb.getNewIoBatchNo());
					postmortemPoliceVerVO.setDutyOffName(fb.getNewDutyOffName());
					postmortemPoliceVerVO.setDutyOffDesignation(fb.getNewDutyOffDesignation());
					postmortemPoliceVerVO.setDutyOffBatchNo(fb.getNewDutyOffBatchNo());
					postmortemPoliceVerVO.setDeathDate(fb.getNewDeathDate()+" "+fb.getNewDeathTimeHr()+":"+fb.getNewDeathTimeMin());
					postmortemPoliceVerVO.setIncidenceDate(fb.getNewIncicenceDate()+" "+fb.getNewIncicenceTimeHr()+":"+fb.getNewIncicenceTimeMin());
					postmortemPoliceVerVO.setDeathPlace(fb.getNewDeathPlace());
					postmortemPoliceVerVO.setDeceasedNo(fb.getDeceasedNo());
					postmortemPoliceVerVO.setEntryMode(MortuaryConfig.POLICE_VERIFICATION_ENTRY_MODE_POSTMORTEM);
					if(policeVerVO!=null)
						postmortemPoliceVerVO.setPatMlcNo(policeVerVO.getPatMlcNo());
				}
				if(fb.getPoliceVerExistNew().equals(MortuaryConfig.POLICE_VERIFICATION_EXISTING))
				{
					HelperMethods.populatetToNullOrEmpty(postmortemRequestDtlVO, policeVerVO);
					postmortemRequestDtlVO.setDeathDate(fb.getDeathDateTime());
					postmortemRequestDtlVO.setIncidenceDate(fb.getExistingIncicenceDate()+" "+fb.getExistingIncicenceTimeHr()+":"+fb.getExistingIncicenceTimeMin());
					postmortemRequestDtlVO.setDeathPlace(fb.getExistingDeathPlace());
					
					HelperMethods.populate(postmortemPoliceVerVO, policeVerVO);
					postmortemPoliceVerVO.setDeathDate(fb.getDeathDateTime());
					postmortemPoliceVerVO.setIncidenceDate(fb.getExistingIncicenceDate()+" "+fb.getExistingIncicenceTimeHr()+":"+fb.getExistingIncicenceTimeMin());
					postmortemPoliceVerVO.setDeathPlace(fb.getExistingDeathPlace());
					postmortemPoliceVerVO.setDeceasedNo(fb.getDeceasedNo());
					postmortemPoliceVerVO.setEntryMode(MortuaryConfig.POLICE_VERIFICATION_ENTRY_MODE_POSTMORTEM);
				}
				
				///For Relative
				if(!fb.getIdentifyRelativeName().equals(""))
				{
					DeceasedRelativeDtlVO identifyingRelativeVO=new DeceasedRelativeDtlVO();
					identifyingRelativeVO.setRelativeName(fb.getIdentifyRelativeName());
					identifyingRelativeVO.setRelativeAddress(fb.getIdentifyRelativeAddress());
					identifyingRelativeVO.setRelativeCode(fb.getIdentifyRelativeCode());
					identifyingRelativeVO.setRelativeContactNo(fb.getIdentifyRelativeContactNo());
					identifyingRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_POSTMORTEM_REQUEST_BY_POLICE);
					identifyingRelativeVO.setDeceasedNo(fb.getDeceasedNo());
					lstRelativeVO.add(identifyingRelativeVO);
				}
				if(addedRelativeVO!=null)
				{
					for(int i=0;i<addedRelativeVO.length;i++)
					{
						DeceasedRelativeDtlVO identifyingRelativeVO=new DeceasedRelativeDtlVO();
						identifyingRelativeVO.setRelativeName(addedRelativeVO[i].getRelativeName());
						identifyingRelativeVO.setRelativeAddress(addedRelativeVO[i].getRelativeAddress());
						identifyingRelativeVO.setRelativeCode(addedRelativeVO[i].getRelativeCode());
						identifyingRelativeVO.setRelativeContactNo(addedRelativeVO[i].getRelativeContactNo());
						identifyingRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_POSTMORTEM_REQUEST_BY_POLICE);
						identifyingRelativeVO.setDeceasedNo(fb.getDeceasedNo());
						lstRelativeVO.add(identifyingRelativeVO);
					}
				}
				if(fb.getChkRelative()!=null)
				{
					DeceasedRelativeDtlVO[] existingRelativeVO=(DeceasedRelativeDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_EXISTING_RELATIVE_VO);
					for(int i=0;i<fb.getChkRelative().length;i++)
					{
						DeceasedRelativeDtlVO identifyingRelativeVO=new DeceasedRelativeDtlVO();
						identifyingRelativeVO.setRelativeName(existingRelativeVO[Integer.parseInt(fb.getChkRelative()[i])].getRelativeName());
						identifyingRelativeVO.setRelativeAddress(existingRelativeVO[Integer.parseInt(fb.getChkRelative()[i])].getRelativeAddress());
						identifyingRelativeVO.setRelativeCode(existingRelativeVO[Integer.parseInt(fb.getChkRelative()[i])].getRelativeCode());
						identifyingRelativeVO.setRelativeContactNo(existingRelativeVO[Integer.parseInt(fb.getChkRelative()[i])].getRelativeContactNo());
						identifyingRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_POSTMORTEM_REQUEST_BY_POLICE);
						identifyingRelativeVO.setDeceasedNo(fb.getDeceasedNo());
						lstRelativeVO.add(identifyingRelativeVO);
					}
				}
			}
			else
			{
				DeceasedRelativeDtlVO requestedRelativeVO=new DeceasedRelativeDtlVO();
				requestedRelativeVO.setRelativeName(fb.getRequestRelativeName());
				requestedRelativeVO.setRelativeAddress(fb.getRequestRelativeAddress());
				requestedRelativeVO.setRelativeCode(fb.getRequestRelativeCode());
				requestedRelativeVO.setRelativeContactNo(fb.getRequestRelativeContactNo());
				requestedRelativeVO.setRelativeFlag(MortuaryConfig.DECEASED_RELATIVE_FLAG_POSTMORTEM_REQUEST_BY_POLICE);
				requestedRelativeVO.setDeceasedNo(fb.getDeceasedNo());
				lstRelativeVO.add(requestedRelativeVO);
				
				postmortemPoliceVerVO=null;
			}
			
			
			postmortemOpinionVO=(PostmortemOpinionReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO);
			postmortemItemVO=(PostmortemItemReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO);
			
			/////For Opinion
			if(fb.getRequestedOpinion()!=null)
			{
				PostmortemOpinionReqDtlVO reqOpinionVO=new PostmortemOpinionReqDtlVO();
				reqOpinionVO.setOpinionCode(fb.getRequestedOpinion());
				reqOpinionVO.setRemarks(fb.getOpinionRemark());
				lstOpinionVO.add(reqOpinionVO);
			}
			if(postmortemOpinionVO!=null)
			{
				for(int i=0;i<postmortemOpinionVO.length;i++)
				{
					PostmortemOpinionReqDtlVO reqOpinionVO=new PostmortemOpinionReqDtlVO();
					reqOpinionVO.setOpinionCode(postmortemOpinionVO[i].getOpinionCode());
					reqOpinionVO.setRemarks(postmortemOpinionVO[i].getRemarks());
					lstOpinionVO.add(reqOpinionVO);
				}
			}
			
			/////For Item
			if(!fb.getRequestedItem().equals("-1"))
			{
				PostmortemItemReqDtlVO reqItemVO=new PostmortemItemReqDtlVO();
				reqItemVO.setItemCode(fb.getRequestedItem());
				reqItemVO.setRemarks(fb.getDeceasedItemRemarks());
				lstItemVO.add(reqItemVO);
			}
			if(postmortemItemVO!=null)
			{
				for(int i=0;i<postmortemItemVO.length;i++)
				{
					PostmortemItemReqDtlVO reqItemVO=new PostmortemItemReqDtlVO();
					reqItemVO.setItemCode(postmortemItemVO[i].getItemCode());
					reqItemVO.setRemarks(postmortemItemVO[i].getRemarks());
					lstItemVO.add(reqItemVO);
				}
			}
			
			
			postmortemRequestDtlVO.setPostmortemStatus(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_RAISED);
			postmortemRequestDtlVO.setIsRepeat(MortuaryConfig.POSTMORTEM_IS_REPEAT_NO);
			postmortemRequestDtlVO.setPostmortemReqType(MortuaryConfig.POSTMORTEM_REQ_TYPE_BODY);
			postmortemRequestDtlVO.setPostmortemType(fb.getPostmortemType());
			postmortemRequestDtlVO.setDeceasedNo(fb.getDeceasedNo());
			
			if(fb.getPostmortemType().equals(MortuaryConfig.POSTMORTEM_TYPE_CLINICAL))
			{
				if(fb.getIsConsentRequired().equals(MortuaryConfig.YES))
				{	
					if(fb.getIsConsentTaken()!=null)
						fb.setIsConsentTaken(MortuaryConfig.CONSENT_RECEIVED);
					else
						fb.setIsConsentTaken(MortuaryConfig.CONSENT_RAISED);
				}
				else
					fb.setIsConsentTaken(MortuaryConfig.CONSENT_NOT_REQUIRED);
			}
			else
				fb.setIsConsentTaken(MortuaryConfig.CONSENT_NOT_REQUIRED);
				
			postmortemRequestDtlVO.setConsentStatus(fb.getIsConsentTaken());
			
			String postmortemId = PostmortemRequestDATA.savePostmortemRequest(postmortemPoliceVerVO,postmortemRequestDtlVO,lstItemVO,lstOpinionVO,lstRelativeVO,getUserVO(request));
			if(postmortemId!=null && !postmortemId.equals(""))
				objStatus.add(Status.DONE,"","Postmortem Request Generated Successfully with Postmortem No ::" + postmortemId);
			else
			{
				flg = false;
				objStatus.add(Status.TRANSINPROCESS,"","Postmortem Request Not Generated.");
			}
		}
		catch (HisDataAccessException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flg;
	}
	
	public static void addOpinionRow(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemOpinionReqDtlVO[] opinionReqVO1=null;
		PostmortemOpinionReqDtlVO[] opinionReqVO2=null;
		PostmortemOpinionReqDtlVO[] opinionReqVO3=null;
		List lstOpinion=null;
		String opinionName="";
		
		try
		{
			lstOpinion=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_OPINION);
			for(int i=0;i<lstOpinion.size();i++)
			{
				Entry ent=(Entry)lstOpinion.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getRequestedOpinion()))
			    {
			    	opinionName=ent.getLabel();
			    	break;
			    }
			}
			
			
			List newList = new ArrayList(lstOpinion);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getRequestedOpinion()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_OPINION,newList);
			
			opinionReqVO1=(PostmortemOpinionReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO);
			
			if(opinionReqVO1==null)
			{
				opinionReqVO2=new PostmortemOpinionReqDtlVO[1];
				opinionReqVO2[0]=new PostmortemOpinionReqDtlVO();
				opinionReqVO2[0].setOpinionCode(fb.getRequestedOpinion());
				opinionReqVO2[0].setOpinionName(opinionName);
				opinionReqVO2[0].setRemarks(fb.getOpinionRemark());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_OPINION_REQUEST_VO, opinionReqVO2);
			}
			else
			{
				opinionReqVO3=new PostmortemOpinionReqDtlVO[opinionReqVO1.length+1];
				int i=0;
				for(;i<opinionReqVO1.length;i++)
				{
					opinionReqVO3[i]=opinionReqVO1[i];
				}
				opinionReqVO3[i]=new PostmortemOpinionReqDtlVO();
				opinionReqVO3[i].setOpinionCode(fb.getRequestedOpinion());
				opinionReqVO3[i].setOpinionName(opinionName);
				opinionReqVO3[i].setRemarks(fb.getOpinionRemark());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_OPINION_REQUEST_VO, opinionReqVO3);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void deleteOpinionRow(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemOpinionReqDtlVO[] opinionReqVO1=null;
		PostmortemOpinionReqDtlVO[] opinionReqVO2=null;
		
		try
		{
			List lstOpinion=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_OPINION);
			List newList = new ArrayList(lstOpinion);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenOpinionCode(),fb.getHiddenOpinionName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_OPINION,newList);
			
			opinionReqVO1=(PostmortemOpinionReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO);
			opinionReqVO2=new PostmortemOpinionReqDtlVO[opinionReqVO1.length-1];
			
			int j=0;
			for(int i=0;i<opinionReqVO1.length;i++)
			{
				if(!fb.getHiddenOpinionCode().equals(opinionReqVO1[i].getOpinionCode()))
				{
					opinionReqVO2[j]=opinionReqVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_OPINION_REQUEST_VO, opinionReqVO2);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void addItemRow(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemItemReqDtlVO[] itemReqVO1=null;
		PostmortemItemReqDtlVO[] itemReqVO2=null;
		PostmortemItemReqDtlVO[] itemReqVO3=null;
		List lstItem=null;
		String itemName="";
		
		try
		{
			lstItem=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM);
			for(int i=0;i<lstItem.size();i++)
			{
				Entry ent=(Entry)lstItem.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getRequestedItem()))
			    {
			    	itemName=ent.getLabel();
			    	break;
			    }
			}
			
			
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getRequestedItem()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM,newList);
			
			itemReqVO1=(PostmortemItemReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO);
			
			if(itemReqVO1==null)
			{
				itemReqVO2=new PostmortemItemReqDtlVO[1];
				itemReqVO2[0]=new PostmortemItemReqDtlVO();
				itemReqVO2[0].setItemCode(fb.getRequestedItem());
				itemReqVO2[0].setItemName(itemName);
				itemReqVO2[0].setRemarks(fb.getDeceasedItemRemarks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ITEM_REQUEST_VO, itemReqVO2);
			}
			else
			{
				itemReqVO3=new PostmortemItemReqDtlVO[itemReqVO1.length+1];
				int i=0;
				for(;i<itemReqVO1.length;i++)
				{
					itemReqVO3[i]=itemReqVO1[i];
				}
				itemReqVO3[i]=new PostmortemItemReqDtlVO();
				itemReqVO3[i].setItemCode(fb.getRequestedItem());
				itemReqVO3[i].setItemName(itemName);
				itemReqVO3[i].setRemarks(fb.getDeceasedItemRemarks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ITEM_REQUEST_VO, itemReqVO3);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void deleteItemRow(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemItemReqDtlVO[] itemReqVO1=null;
		PostmortemItemReqDtlVO[] itemReqVO2=null;
		
		try
		{
			List lstItem=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM);
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenItemCode(),fb.getHiddenItemName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_DECEASED_ITEM,newList);
			
			itemReqVO1=(PostmortemItemReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO);
			itemReqVO2=new PostmortemItemReqDtlVO[itemReqVO1.length-1];
			
			int j=0;
			for(int i=0;i<itemReqVO1.length;i++)
			{
				if(!fb.getHiddenItemCode().equals(itemReqVO1[i].getItemCode()))
				{
					itemReqVO2[j]=itemReqVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ITEM_REQUEST_VO, itemReqVO2);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void addRelativeRow(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		DeceasedRelativeDtlVO[] relativeVO1=null;
		DeceasedRelativeDtlVO[] relativeVO2=null;
		DeceasedRelativeDtlVO[] relativeVO3=null;
		List lstRelation=null;
		String relationName="";
		
		try
		{
			lstRelation=(List)session.getAttribute(MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION);
			for(int i=0;i<lstRelation.size();i++)
			{
				Entry ent=(Entry)lstRelation.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getIdentifyRelativeCode()))
			    {
			    	relationName=ent.getLabel();
			    	break;
			    }
			}
			
			relativeVO1=(DeceasedRelativeDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO);
			
			if(relativeVO1==null)
			{
				relativeVO2=new DeceasedRelativeDtlVO[1];
				relativeVO2[0]=new DeceasedRelativeDtlVO();
				relativeVO2[0].setRelativeName(fb.getIdentifyRelativeName());
				relativeVO2[0].setRelativeAddress(fb.getIdentifyRelativeAddress());
				relativeVO2[0].setRelativeContactNo(fb.getIdentifyRelativeContactNo());
				relativeVO2[0].setRelativeCode(fb.getIdentifyRelativeCode());
				relativeVO2[0].setRelativeCodeName(relationName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO, relativeVO2);
			}
			else
			{
				relativeVO3=new DeceasedRelativeDtlVO[relativeVO1.length+1];
				int i=0;
				for(;i<relativeVO1.length;i++)
				{
					relativeVO3[i]=relativeVO1[i];
				}
				relativeVO3[i]=new DeceasedRelativeDtlVO();
				relativeVO3[i].setRelativeName(fb.getIdentifyRelativeName());
				relativeVO3[i].setRelativeAddress(fb.getIdentifyRelativeAddress());
				relativeVO3[i].setRelativeContactNo(fb.getIdentifyRelativeContactNo());
				relativeVO3[i].setRelativeCode(fb.getIdentifyRelativeCode());
				relativeVO3[i].setRelativeCodeName(relationName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO, relativeVO3);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void deleteRelativeRow(PostmortemRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		DeceasedRelativeDtlVO[] relativeVO1=null;
		DeceasedRelativeDtlVO[] relativeVO2=null;
		
		try
		{
			relativeVO1=(DeceasedRelativeDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO);
			relativeVO2=new DeceasedRelativeDtlVO[relativeVO1.length-1];
			
			int j=0;
			for(int i=0;i<relativeVO1.length;i++)
			{
				if(i!=Integer.parseInt(fb.getRelativeIndex()))
				{
					relativeVO2[j]=relativeVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_ADDED_RELATIVE_VO, relativeVO2);
			
			objStatus.add(Status.TRANSINPROCESS);

		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
}
