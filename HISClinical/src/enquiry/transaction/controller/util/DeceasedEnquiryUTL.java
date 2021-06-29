package enquiry.transaction.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.DeceasedEnquiryDATA;
import enquiry.transaction.controller.fb.DeceasedEnquiryFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;

public class DeceasedEnquiryUTL extends ControllerUTIL
{
	public static void getAllDeceasedListInMortuary(DeceasedEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status(); 
		List<DeceasedDetailVO> lstDeceasedVO=null;
		
		try
		{
			lstDeceasedVO=DeceasedEnquiryDATA.getAllDeceasedListInMortuary(getUserVO(request));
			WebUTIL.setAttributeInSession(request, enquiryConfig.ENQUIRY_DECEASED_DETAIL_VO, lstDeceasedVO);
			WebUTIL.setAttributeInSession(request, enquiryConfig.ENQUIRY_FILTER_DECEASED_DETAIL_VO, lstDeceasedVO);
			
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
	
	
	public static void getDeceasedDtlByDeceaseType(DeceasedEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status(); 
		HttpSession session=request.getSession();
		List<DeceasedDetailVO> lstDeceasedVO=null;
		
		try
		{
			lstDeceasedVO=(List<DeceasedDetailVO>)session.getAttribute(enquiryConfig.ENQUIRY_DECEASED_DETAIL_VO);
			if(lstDeceasedVO.size()>0)
			{
				if(fb.getChkRadioBtn().equals(enquiryConfig.DECEASED_TYPE_ALL))
				{
					WebUTIL.setAttributeInSession(request, enquiryConfig.ENQUIRY_FILTER_DECEASED_DETAIL_VO, lstDeceasedVO);
				}
				
				if(fb.getChkRadioBtn().equals(enquiryConfig.DECEASED_TYPE_NORMAL))
				{
					List<DeceasedDetailVO> lstNormal=new ArrayList<DeceasedDetailVO>();
					for(int i=0;i<lstDeceasedVO.size();i++)
					{
						if(lstDeceasedVO.get(i).getIsClaimed().equals(MortuaryConfig.IS_BODY_CLAIMED_YES) && lstDeceasedVO.get(i).getUnidentifiedBody().equals(MortuaryConfig.NO))
						{
							lstNormal.add(lstDeceasedVO.get(i));
						}
					}
					WebUTIL.setAttributeInSession(request, enquiryConfig.ENQUIRY_FILTER_DECEASED_DETAIL_VO, lstNormal);
				}
				
				if(fb.getChkRadioBtn().equals(enquiryConfig.DECEASED_TYPE_UNKNOWN))
				{
					List<DeceasedDetailVO> lstUnknown=new ArrayList<DeceasedDetailVO>();
					for(int i=0;i<lstDeceasedVO.size();i++)
					{
						if(lstDeceasedVO.get(i).getUnidentifiedBody().equals(MortuaryConfig.YES))
						{
							lstUnknown.add(lstDeceasedVO.get(i));
						}
					}
					WebUTIL.setAttributeInSession(request, enquiryConfig.ENQUIRY_FILTER_DECEASED_DETAIL_VO, lstUnknown);
				}
				
				if(fb.getChkRadioBtn().equals(enquiryConfig.DECEASED_TYPE_UNCLAIMED))
				{
					List<DeceasedDetailVO> lstUnclaimed=new ArrayList<DeceasedDetailVO>();
					for(int i=0;i<lstDeceasedVO.size();i++)
					{
						if(lstDeceasedVO.get(i).getIsClaimed().equals(MortuaryConfig.IS_BODY_CLAIMED_NO))
						{
							lstUnclaimed.add(lstDeceasedVO.get(i));
						}
					}
					WebUTIL.setAttributeInSession(request, enquiryConfig.ENQUIRY_FILTER_DECEASED_DETAIL_VO, lstUnclaimed);
				}
			}	
			
			session.removeAttribute(Config.IMAGE_BYTE_ARRAY);
			session.removeAttribute(enquiryConfig.DECEASED_STORAGE_DETAIL);
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
	
	public static void getDeceasedDtlByDeceaseNo(DeceasedEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status(); 
		DeceasedDetailVO genAppVO=new DeceasedDetailVO();
		MortuaryDeceasedImageDtlVO deceasedImageVO=new MortuaryDeceasedImageDtlVO();
		List byteArray=new ArrayList();
		
		try
		{
			Map map=DeceasedEnquiryDATA.getDeceasedDtlByDeceaseNo(fb.getDeceasedNo(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			byte[] getDoc;
			deceasedImageVO=(MortuaryDeceasedImageDtlVO)map.get(enquiryConfig.DECEASED_DEFAULT_IMAGE_VO);
			if(deceasedImageVO!=null)
			{
				String filePath= deceasedImageVO.getFileNo();
				//byteArray.add(HelperMethods.getByteArrayOfImage(filePath));		//Getting Deceased Default Image
				getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_MORTUARY_PATIENT_IMAGE_UPLOAD).latestFetchFile(filePath);
				byteArray.add(getDoc);
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
