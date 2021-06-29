package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedHandoverDATA;
import mortuary.transaction.controller.data.PostmortemWaveoffDetailDATA;
import mortuary.transaction.controller.fb.DeceasedHandoverFB;
import mortuary.transaction.controller.fb.PostmortemWaveoffDetailFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;

public class PostmortemWaveoffDetailUTL extends ControllerUTIL
{

	public static void getWaveoffDetails(PostmortemWaveoffDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//PostmortemTeamDetailVO[] addedTeamVO=null;
		
		try
		{
			DeceasedDetailVO[] deceasedDetailVO=PostmortemWaveoffDetailDATA.getWaveoffDetails(getUserVO(request));
						
			if(deceasedDetailVO!=null)
			{
				for(int i=0;i<deceasedDetailVO.length;i++)
				{
					if(deceasedDetailVO[i].getPostmortemId()==null)
					{
						deceasedDetailVO[i].setPostmortemIdLabel("Not Raised");
						deceasedDetailVO[i].setPostmortemId("");
					}
					else
					{
						deceasedDetailVO[i].setPostmortemIdLabel(deceasedDetailVO[i].getPostmortemId());
					}
					if(deceasedDetailVO[i].getPostmortemType()==null)
					{
						deceasedDetailVO[i].setPostmortemType("-");
					}
					if(deceasedDetailVO[i].getPostmortemStatus()==null)
					{
						deceasedDetailVO[i].setPostmortemStatus("-");
					}
				}
			}
			
			WebUTIL.setAttributeInSession(request, MortuaryConfig.POSTMORTEM_WAVEOFF_DETAIL_VO_ARRAY, deceasedDetailVO);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDuplicateRecordException e)
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
	
	public static void getDeceasedEssentialDetailForWaveoff(PostmortemWaveoffDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String chamberRackName="";
		PostmortemRequestDetailVO postmortemReqVO=new PostmortemRequestDetailVO();
		MortuaryPoliceVerificationVO policeVerificationDtlVO=new MortuaryPoliceVerificationVO();
		Map map=new HashMap();
		try
		{
			
			postmortemReqVO=PostmortemWaveoffDetailDATA.getPostmortemRequestDetail(fb.getPostmortemId(),getUserVO(request));			
			WebUTIL.setAttributeInSession(request, MortuaryConfig.POSTMORTEM_REQUEST_DETAIL_VO, postmortemReqVO);
			
			map=PostmortemWaveoffDetailDATA.fetchPoliceVeriDetails(fb.getPostmortemId(),fb.getDeceasedNo(),getUserVO(request));
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
	
	public static void savePostMortemWaveoffDetail(PostmortemWaveoffDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PostmortemWaveoffDtlVO postmortemWaveoffDtlVO=new PostmortemWaveoffDtlVO();
		MortuaryPoliceVerificationVO policeVerificationDtlVO=new MortuaryPoliceVerificationVO();
		try
		{
				
			HelperMethods.populate(postmortemWaveoffDtlVO, fb);
			policeVerificationDtlVO=(MortuaryPoliceVerificationVO)session.getAttribute(MortuaryConfig.POSTMORTEM_WAVEOFF_POLICE_VERIFICATION_DETAIL_VO);
			
			postmortemWaveoffDtlVO.setOfficerIncharge(policeVerificationDtlVO.getOfficerIncharge());
			postmortemWaveoffDtlVO.setIoDesignation(policeVerificationDtlVO.getIoDesignation());
			postmortemWaveoffDtlVO.setIoBatchNo(policeVerificationDtlVO.getIoBatchNo());
			postmortemWaveoffDtlVO.setDutyOffName(policeVerificationDtlVO.getDutyOffName());
			postmortemWaveoffDtlVO.setDutyOffDesignation(policeVerificationDtlVO.getDutyOffDesignation());
			postmortemWaveoffDtlVO.setDutyOffBatchNo(policeVerificationDtlVO.getDutyOffBatchNo());
			
			if(!postmortemWaveoffDtlVO.getPostmortemId().equals(""))
			{
				postmortemWaveoffDtlVO.setWaveoffMode(MortuaryConfig.POSTMORTEM_WAVEOFF_MODE_AFTER_POSTMORTEM);
			}
			else
			{
				postmortemWaveoffDtlVO.setWaveoffMode(MortuaryConfig.POSTMORTEM_WAVEOFF_MODE_BEFORE_POSTMORTEM);
			}
			
			PostmortemWaveoffDetailDATA.savePostMortemWaveoffDetail(postmortemWaveoffDtlVO,getUserVO(request));
			
		
			objStatus.add(Status.DONE,"","Postmortem Waveoff Details Saved Successfully");
			
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
