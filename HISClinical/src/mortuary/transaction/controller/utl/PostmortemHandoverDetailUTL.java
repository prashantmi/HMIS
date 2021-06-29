package mortuary.transaction.controller.utl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedStorageDATA;
import mortuary.transaction.controller.data.PostmortemHandoverDetailDATA;
import mortuary.transaction.controller.fb.PostmortemHandoverDetailFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.PostmortemHandoverDtlVO;

public class PostmortemHandoverDetailUTL extends ControllerUTIL
{
	public static void getPostmortemReadyToHandover(PostmortemHandoverDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			deceasedDtlVO=PostmortemHandoverDetailDATA.getPostmortemReadyToHandover(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_POSTMORTEM_READY_TO_HANDOVER_VO, deceasedDtlVO);
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
	
	public static void getEssentialForPostmortemHandover(PostmortemHandoverDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			Map mp=PostmortemHandoverDetailDATA.getEssentialForPostmortemHandover(getUserVO(request));
			WebUTIL.setMapInSession(mp, request);
			fb.setPostmortemId(fb.getSelectedPostmortemId());
			
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
	
	public static void savePostmortemHandoverDetail(PostmortemHandoverDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		PostmortemHandoverDtlVO postmortemHandoverDtlVO=new PostmortemHandoverDtlVO();
		
		try
		{
			postmortemHandoverDtlVO.setPostmortemId(fb.getPostmortemId());
			postmortemHandoverDtlVO.setIsHandoverTo(fb.getIsHandoverTo());
			
			if(fb.getIsHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_POLICE))
			{
				postmortemHandoverDtlVO.setHandoverToName(fb.getOfficerName());
				postmortemHandoverDtlVO.setOfficerDesignation(fb.getOfficerDesignation());
				postmortemHandoverDtlVO.setOfficerBadgeNo(fb.getOfficerBadgeNo());
				
			}
			else
			{
				postmortemHandoverDtlVO.setHandoverToName(fb.getRelativeName());
				postmortemHandoverDtlVO.setHandoverToAddress(fb.getRelativeAddress());
				postmortemHandoverDtlVO.setHandoverToPhone(fb.getRelativePhone());
				postmortemHandoverDtlVO.setRelativeCode(fb.getRelativeCode());
			}
			
			PostmortemHandoverDetailDATA.savePostmortemHandoverDetail(postmortemHandoverDtlVO,getUserVO(request));
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
	
}
