package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.OpinionApprovalDATA;
import mortuary.transaction.controller.fb.OpinionApprovalFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.GenericTemplateUtility.TempParameter;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemExamDtlVO;
import hisglobal.vo.PostmortemOpinionDetailVO;

public class OpinionApprovalUTL extends ControllerUTIL
{
	public static void getFinalOpinionToBeApproved(OpinionApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			deceasedDtlVO=OpinionApprovalDATA.getFinalOpinionToBeApproved(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_FINAL_OPINION_TO_BE_APPROVED_VO, deceasedDtlVO);
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
	
	public static void getFinalOpinionToBeApprovedDetails(OpinionApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String patCrNo="";
		String postmortemId="";
		String dob="";
		PostmortemOpinionDetailVO[] addedOpinionVO=null;
		Map<String, Map<String, String>> mpTempParaValues = null;
		
		try
		{
			setSysdate(request);
			DeceasedDetailVO[] deceasedVO=(DeceasedDetailVO[])session.getAttribute(MortuaryConfig.ARR_FINAL_OPINION_TO_BE_APPROVED_VO);
			for(int i=0;i<deceasedVO.length;i++)
			{
				if(fb.getDeceasedNo().equalsIgnoreCase(deceasedVO[i].getDeceasedNo()))
				{
					postmortemId=deceasedVO[i].getPostmortemId();
					patCrNo=deceasedVO[i].getPatCrNo();
					dob=deceasedVO[i].getPatDOB();
					break;
				}	
			}
			fb.setPostmortemId(postmortemId);
			fb.setPatCrNo(patCrNo);
			
			PostmortemDetailVO generalAppearanceVO=OpinionApprovalDATA.getExistingGeneralAppearance(fb.getPostmortemId(),getUserVO(request));
			Map map=OpinionApprovalDATA.getPostmortemConductedEssential(dob, fb.getPostmortemId(),getUserVO(request)); 
			WebUTIL.setMapInSession(map, request);
			
			mpTempParaValues = new HashMap<String, Map<String, String>>();
			mpTempParaValues=OpinionApprovalDATA.getTemplateDetail(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP, mpTempParaValues);
			
			HelperMethods.populatetToNullOrEmpty(fb,generalAppearanceVO);
			
			fb.setStartDate(generalAppearanceVO.getStartDateTime().split(" ")[0]);
			fb.setStartDateTimeHr(generalAppearanceVO.getStartDateTime().split(" ")[1].split(":")[0]);
			fb.setStartDateTimeMin(generalAppearanceVO.getStartDateTime().split(" ")[1].split(":")[1]);
			
			fb.setEndDate(generalAppearanceVO.getEndDateTime().split(" ")[0]);
			fb.setEndDateTimeHr(generalAppearanceVO.getEndDateTime().split(" ")[1].split(":")[0]);
			fb.setEndDateTimeMin(generalAppearanceVO.getEndDateTime().split(" ")[1].split(":")[1]);
			
			fb.setDeathDate(generalAppearanceVO.getDeathDateTime().split(" ")[0]);
			fb.setDeathTimeHr(generalAppearanceVO.getDeathDateTime().split(" ")[1].split(":")[0]);
			fb.setDeathTimeMin(generalAppearanceVO.getDeathDateTime().split(" ")[1].split(":")[1]);
			
			fb.setWeaponUsedValue(generalAppearanceVO.getWeaponUsed());
			fb.setInjuryPhotoValue(generalAppearanceVO.getInjuryPhoto());
			
			if(generalAppearanceVO.getInjuryDateTime()!=null)
			{
				fb.setInjuryDate(generalAppearanceVO.getInjuryDateTime().split(" ")[0]);
				fb.setInjuryTimeHr(generalAppearanceVO.getInjuryDateTime().split(" ")[1].split(":")[0]);
				fb.setInjuryTimeMin(generalAppearanceVO.getInjuryDateTime().split(" ")[1].split(":")[1]);
			}
			
			/*if(generalAppearanceVO.getLength()!=null)
			{
				generalAppearanceVO.setLength(generalAppearanceVO.getLength().replace(".", "@"));
				fb.setLength1(generalAppearanceVO.getLength().split("@")[0]);
				fb.setLength2(generalAppearanceVO.getLength().split("@")[1]);
			}
			if(generalAppearanceVO.getWeight()!=null)
			{
				generalAppearanceVO.setWeight(generalAppearanceVO.getWeight().replace(".", "@"));
				fb.setWeight1(generalAppearanceVO.getWeight().split("@")[0]);
				fb.setWeight2(generalAppearanceVO.getWeight().split("@")[1]);
			}*/
			
			if(generalAppearanceVO.getWeight()!=null)
			{
				generalAppearanceVO.setWeight(generalAppearanceVO.getWeight().replace(".", "@"));
				if(generalAppearanceVO.getWeight().split("@").length!=2)
				{
					if(generalAppearanceVO.getWeight().split("@")[0].length()==1)
					{	
						fb.setWeight("00"+fb.getWeight()+".0");
					}
					if(generalAppearanceVO.getWeight().split("@")[0].length()==2)
					{	
						fb.setWeight("0"+fb.getWeight()+".0");
					}
					if(generalAppearanceVO.getWeight().split("@")[0].length()==3)
					{	
						fb.setWeight(fb.getWeight()+".0");
					}
				}	
				else
				{
					if(generalAppearanceVO.getWeight().split("@")[0].length()==1)
					{
						fb.setWeight("00"+fb.getWeight());
					}
					if(generalAppearanceVO.getWeight().split("@")[0].length()==2)
					{
						fb.setWeight("0"+fb.getWeight());
					}
				}
			}
			
			
			addedOpinionVO=OpinionApprovalDATA.getAddedOpinion(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ADDED_OPINION_VO, addedOpinionVO);
		
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
	
	public static void addRow(OpinionApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemOpinionDetailVO[] opinionReqVO1=null;
		PostmortemOpinionDetailVO[] opinionReqVO2=null;
		PostmortemOpinionDetailVO[] opinionReqVO3=null;
		List lstOpinion=null;
		String opinionName="";
		
		try
		{
			lstOpinion=(List)session.getAttribute(MortuaryConfig.OPINION_LIST_NOT_ADDED);
			for(int i=0;i<lstOpinion.size();i++)
			{
				Entry ent=(Entry)lstOpinion.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getExtraOpinionCode()))
			    {
			    	opinionName=ent.getLabel();
			    	break;
			    }
			}
			
			List newList = new ArrayList(lstOpinion);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getExtraOpinionCode()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.OPINION_LIST_NOT_ADDED,newList);
			
			opinionReqVO1=(PostmortemOpinionDetailVO[])session.getAttribute(MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO);
			
			if(opinionReqVO1==null)
			{
				opinionReqVO2=new PostmortemOpinionDetailVO[1];
				opinionReqVO2[0]=new PostmortemOpinionDetailVO();
				opinionReqVO2[0].setOpinionCode(fb.getExtraOpinionCode());
				opinionReqVO2[0].setOpinionName(opinionName);
				opinionReqVO2[0].setOpinion(fb.getExtraOpinionRemarks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO, opinionReqVO2);
			}
			else
			{
				opinionReqVO3=new PostmortemOpinionDetailVO[opinionReqVO1.length+1];
				int i=0;
				for(;i<opinionReqVO1.length;i++)
				{
					opinionReqVO3[i]=opinionReqVO1[i];
				}
				opinionReqVO3[i]=new PostmortemOpinionDetailVO();
				
				opinionReqVO3[i].setOpinionCode(fb.getExtraOpinionCode());
				opinionReqVO3[i].setOpinionName(opinionName);
				opinionReqVO3[i].setOpinion(fb.getExtraOpinionRemarks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO, opinionReqVO3);
			}
			
			if(session.getAttribute(MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO)!=null)
				fb.setAddOpinionFlag(MortuaryConfig.YES);
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
	
	public static void deleteRow(OpinionApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemOpinionDetailVO[] opinionReqVO1=null;
		PostmortemOpinionDetailVO[] opinionReqVO2=null;
		
		try
		{
			List lstOpinion=(List)session.getAttribute(MortuaryConfig.OPINION_LIST_NOT_ADDED);
			List newList = new ArrayList(lstOpinion);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenExtraOpinionCode(),fb.getHiddenExtraOpinionName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.OPINION_LIST_NOT_ADDED,newList);
			
			opinionReqVO1=(PostmortemOpinionDetailVO[])session.getAttribute(MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO);
			opinionReqVO2=new PostmortemOpinionDetailVO[opinionReqVO1.length-1];
			
			int j=0;
			for(int i=0;i<opinionReqVO1.length;i++)
			{
				if(!fb.getHiddenExtraOpinionCode().equals(opinionReqVO1[i].getOpinionCode()))
				{
					opinionReqVO2[j]=opinionReqVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO, opinionReqVO2);
			
			if(opinionReqVO2.length==0)
				fb.setAddOpinionFlag(MortuaryConfig.NO);
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
	
	public static void saveApprovedFinalOpinion(OpinionApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PostmortemDetailVO postmortemDtlVO= new PostmortemDetailVO();
		PostmortemOpinionDetailVO[] opinionDtlVO=null;
		List<PostmortemOpinionDetailVO> lstOpinionDtl=null;
		List templateParaList=null;
		List postMortemExamVOList=new ArrayList();
		
		try
		{
			HelperMethods.populate(postmortemDtlVO, fb);
			/*if(!fb.getLength1().equals("") || !fb.getLength2().equals(""))
			{
				String l1=fb.getLength1().equals("")?"0":fb.getLength1();
				String l2=fb.getLength2().equals("")?"0":fb.getLength2();
				postmortemDtlVO.setLength(l1+"."+l2);
			}
			if(!fb.getWeight1().equals("") || !fb.getWeight2().equals(""))
			{
				String w1=fb.getWeight1().equals("")?"0":fb.getWeight1();
				String w2=fb.getWeight2().equals("")?"0":fb.getWeight2();
				postmortemDtlVO.setWeight(w1+"."+w2);
			}*/
			postmortemDtlVO.setStartDateTime(fb.getStartDate()+" "+fb.getStartDateTimeHr()+":"+fb.getStartDateTimeMin());
			postmortemDtlVO.setEndDateTime(fb.getEndDate()+" "+fb.getEndDateTimeHr()+":"+fb.getEndDateTimeMin());
			
			postmortemDtlVO.setDeathDateTime(fb.getDeathDate()+" "+fb.getDeathTimeHr()+":"+fb.getDeathTimeMin());
			if(!fb.getInjuryDate().equals(""))
			{
				String hour=fb.getInjuryTimeHr().equals("")?"00":fb.getInjuryTimeHr();
				String min=fb.getInjuryTimeMin().equals("")?"00":fb.getInjuryTimeMin();
				postmortemDtlVO.setInjuryDateTime(fb.getInjuryDate()+" "+hour+":"+min);
			}
			
			if(fb.getWeaponUsed()==null)
			{
				postmortemDtlVO.setWeaponUsed(MortuaryConfig.NO);
				postmortemDtlVO.setWeaponRemarks("");
			}
			else
				postmortemDtlVO.setWeaponUsed(MortuaryConfig.YES);
			if(fb.getInjuryPhoto()==null)
				postmortemDtlVO.setInjuryPhoto(MortuaryConfig.NO);
			else
				postmortemDtlVO.setInjuryPhoto(MortuaryConfig.YES);
			
			lstOpinionDtl=new ArrayList<PostmortemOpinionDetailVO>();
			opinionDtlVO=(PostmortemOpinionDetailVO[])session.getAttribute(MortuaryConfig.ARR_EXTRA_OPINION_REQUESTED_VO);
						
			if(fb.getExtraOpinionCode()!=null)
			{
				if(!fb.getExtraOpinionCode().equalsIgnoreCase("-1"))
				{
					PostmortemOpinionDetailVO postmortemOpinionVO=new PostmortemOpinionDetailVO();
					postmortemOpinionVO.setOpinionCode(fb.getExtraOpinionCode());
					postmortemOpinionVO.setOpinion(fb.getExtraOpinionRemarks());
					postmortemOpinionVO.setPostmortemId(fb.getPostmortemId());
							
					lstOpinionDtl.add(postmortemOpinionVO);
				}
			}	
			if(opinionDtlVO!=null)
			{
				int len=opinionDtlVO.length;
				for(int i=0;i<len;i++)
				{
					PostmortemOpinionDetailVO postmortemOpinionVO=new PostmortemOpinionDetailVO();
					postmortemOpinionVO.setOpinionCode(opinionDtlVO[i].getOpinionCode());
					postmortemOpinionVO.setOpinion(opinionDtlVO[i].getOpinion());
					postmortemOpinionVO.setPostmortemId(fb.getPostmortemId());
							
					lstOpinionDtl.add(postmortemOpinionVO);
				}
			}
			
			for(int i=0;i<fb.getOpinionCode().length;i++)
			{
				PostmortemOpinionDetailVO postmortemOpinionVO=new PostmortemOpinionDetailVO();
				postmortemOpinionVO.setOpinionCode(fb.getOpinionCode()[i]);
				postmortemOpinionVO.setOpinion(fb.getOpinionRemarks()[i]);
				postmortemOpinionVO.setPostmortemId(fb.getPostmortemId());
				
				lstOpinionDtl.add(postmortemOpinionVO);
			}
			
			///Template Data
			templateParaList=GenericTemplateUtility.getTempParameterValueList(request);
			for(int i=0;i<templateParaList.size();i++)
			{
				TempParameter obj=(TempParameter)templateParaList.get(i);
				PostmortemExamDtlVO postmortemExamDtlVO=new PostmortemExamDtlVO();
				postmortemExamDtlVO.setTemplateId(obj.getTemplateId());
				postmortemExamDtlVO.setParaId(obj.getParaId());
				postmortemExamDtlVO.setParaValue(obj.getParaValue());
				postmortemExamDtlVO.setPostmortemId(fb.getPostmortemId());
				
				postMortemExamVOList.add(postmortemExamDtlVO);
			}
			
			OpinionApprovalDATA.saveApprovedFinalOpinion(postMortemExamVOList,lstOpinionDtl,postmortemDtlVO,getUserVO(request));
			
			
			objStatus.add(Status.UNSUCESSFULL,"","Final Opinion Approved");
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
	
	
	public static void getSampleResult(OpinionApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		MortuaryExtLabRequestDtlVO[] arrReqDtlVO=null;
		
		try
		{
		//	fb.setPostmortemId("1080900002");
			arrReqDtlVO=OpinionApprovalDATA.getSampleResult(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_RECEIVED_REPORT_BY_POSTMORTEM_NO_VO, arrReqDtlVO);
			
			if(arrReqDtlVO!=null)
			{
				Map<String, MortuaryExtLabRequestDtlVO> mapReqId =new HashMap<String, MortuaryExtLabRequestDtlVO>();
				Map<String, List<MortuaryExtLabRequestDtlVO>> mapLabTest =new HashMap<String, List<MortuaryExtLabRequestDtlVO>>();
				//List<MortuaryExtLabRequestDtlVO> lstExtLabTest=new ArrayList<MortuaryExtLabRequestDtlVO>();
				for(int i=0;i<arrReqDtlVO.length;i++)
				{
					mapReqId.put(arrReqDtlVO[i].getRequestId(), arrReqDtlVO[i]);
					
					List<MortuaryExtLabRequestDtlVO> lstExtLabTest = null;
					if(mapLabTest.get(arrReqDtlVO[i].getRequestId())==null)
						lstExtLabTest = new ArrayList<MortuaryExtLabRequestDtlVO>();
					else
						lstExtLabTest = mapLabTest.get(arrReqDtlVO[i].getRequestId());
					lstExtLabTest.add(arrReqDtlVO[i]);
					mapLabTest.put(arrReqDtlVO[i].getRequestId(), lstExtLabTest);
				}
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.MAP_REQUEST_ID_N_FINAL_RESULT_BY_POSTMORTEM_NO, mapReqId);
				WebUTIL.setAttributeInSession(request, MortuaryConfig.MAP_REQUEST_ID_N_LAB_TEST_BY_POSTMORTEM_NO, mapLabTest);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.DONE, e.getMessage(), "");
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
