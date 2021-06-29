package enquiry.transaction.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.SearchDeceasedEnquiryDATA;
import enquiry.transaction.controller.fb.SearchDeceasedEnquiryFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;

public class SearchDeceasedEnquiryUTL extends ControllerUTIL
{
	public static void getEssentialForDeceasedEnquiry(SearchDeceasedEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			Map map=SearchDeceasedEnquiryDATA.getEssentialForDeceasedEnquiry(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			objStatus.add(Status.DONE);
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
	
	public static void searchDeceased(SearchDeceasedEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			/*if(fb.getChkUnclaimed()!=null)
				fb.setChkUnclaimed(MortuaryConfig.NO);
			else
				fb.setChkUnclaimed(MortuaryConfig.YES);
			
			if(fb.getChkUnknown()!=null)
				fb.setChkUnknown(MortuaryConfig.YES);
			else
				fb.setChkUnknown(MortuaryConfig.NO);*/
			
			/*if(fb.getChkUnclaimed().equals("on"))
				fb.setChkUnclaimed(MortuaryConfig.NO);
			else
				fb.setChkUnclaimed(MortuaryConfig.YES);
			
			if(fb.getChkUnknown().equals("on"))
				fb.setChkUnknown(MortuaryConfig.YES);
			else
				fb.setChkUnknown(MortuaryConfig.NO);*/
			
			deceasedDtlVO=SearchDeceasedEnquiryDATA.searchDeceased(fb.getFirstName(),fb.getMiddleName(),fb.getLastName(),fb.getGenderCode(),fb.getFromDate(),fb.getToDate(),fb.getChkUnknown(),fb.getChkUnclaimed(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, enquiryConfig.ENQUIRY_DECEASED_DETAIL_VO, deceasedDtlVO);
			
			objStatus.add(Status.DONE);
			objStatus.add(Status.LIST);
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
	
	public static void getDeceasedDtlByDeceaseNo(SearchDeceasedEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status(); 
		DeceasedDetailVO genAppVO=new DeceasedDetailVO();
		MortuaryDeceasedImageDtlVO deceasedImageVO=new MortuaryDeceasedImageDtlVO();
		List byteArray=new ArrayList();
		
		try
		{
			Map map=SearchDeceasedEnquiryDATA.getSearchDeceasedDtlByDeceaseNo(fb.getDeceasedNo(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			deceasedImageVO=(MortuaryDeceasedImageDtlVO)map.get(enquiryConfig.DECEASED_DEFAULT_IMAGE_VO);
			if(deceasedImageVO!=null)
			{
				String filePath=deceasedImageVO.getFilePath().replace("\\","/")+"/" + deceasedImageVO.getFileNo();
				byteArray.add(HelperMethods.getByteArrayOfImage(filePath));		//Getting Deceased Default Image
				WebUTIL.setAttributeInSession(request,Config.IMAGE_BYTE_ARRAY , byteArray);
			}
			
			genAppVO=(DeceasedDetailVO)map.get(enquiryConfig.DECEASED_GENERAL_APPEARANCE_VO);
			
			if(genAppVO.getWeight()!=null)
			{
				genAppVO.setWeight(genAppVO.getWeight().replace(".", "@"));
				if(genAppVO.getWeight().split("@").length!=2)
				{
					if(genAppVO.getWeight().split("@")[0].length()==1)
					{	
						genAppVO.setWeight("00"+genAppVO.getWeight()+".0");
					}
					if(genAppVO.getWeight().split("@")[0].length()==2)
					{	
						genAppVO.setWeight("0"+genAppVO.getWeight()+".0");
					}
					if(genAppVO.getWeight().split("@")[0].length()==3)
					{	
						genAppVO.setWeight(genAppVO.getWeight()+".0");
					}
				}	
				else
				{
					if(genAppVO.getWeight().split("@")[0].length()==1)
					{
						genAppVO.setWeight("00"+genAppVO.getWeight());
					}
					if(genAppVO.getWeight().split("@")[0].length()==2)
					{
						genAppVO.setWeight("0"+genAppVO.getWeight());
					}
				}
			}
			WebUTIL.setAttributeInSession(request, enquiryConfig.DECEASED_GENERAL_APPEARANCE_VO, genAppVO);
			
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
