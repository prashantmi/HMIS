package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedGeneralAppearanceDATA;
import mortuary.transaction.controller.fb.DeceasedGeneralAppearanceFB;
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

public class DeceasedGeneralAppearanceUTL extends ControllerUTIL
{
	public static void getDeceasedGeneralAppearance(DeceasedGeneralAppearanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		MortuaryDeceasedImageDtlVO deceasedImageVO=new MortuaryDeceasedImageDtlVO();
		List byteArray=new ArrayList();
		String filePath="";
		
		try
		{
			deceasedImageVO=DeceasedGeneralAppearanceDATA.getDedeasedDefaultImage(fb.getDeceasedNo(),getUserVO(request));
			byte[] getDoc;
			if(deceasedImageVO!=null)
			{
				filePath=deceasedImageVO.getFileNo();
				//byteArray.add(HelperMethods.getByteArrayOfImage(filePath));		//Getting Deceased Default Image
				getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_MORTUARY_PATIENT_IMAGE_UPLOAD).latestFetchFile(filePath);
				byteArray.add(getDoc);
				WebUTIL.setAttributeInSession(request,Config.IMAGE_BYTE_ARRAY , byteArray);
			}
			
			fb.setImageFilePath(filePath);
			
			deceasedDtlVO=DeceasedGeneralAppearanceDATA.getDeceasedGeneralAppearance(fb.getDeceasedNo(),getUserVO(request));
			HelperMethods.populatetToNullOrEmpty(fb,deceasedDtlVO);
			/*if(deceasedDtlVO.getLength()!=null)
			{
				deceasedDtlVO.setLength(deceasedDtlVO.getLength().replace(".", "@"));
				if(deceasedDtlVO.getLength().split("@").length!=2)
					fb.setLength(deceasedDtlVO.getLength()+".00");
				else
				{
					if(deceasedDtlVO.getLength().split("@")[1].length()==1)
					{
						fb.setLength(deceasedDtlVO.getLength()+"0");
						fb.setLength(fb.getLength().replace("@", "."));
					}	
				}
				
			}*/
			if(deceasedDtlVO.getWeight()!=null)
			{
				deceasedDtlVO.setWeight(deceasedDtlVO.getWeight().replace(".", "@"));
				if(deceasedDtlVO.getWeight().split("@").length!=2)
				{
					if(deceasedDtlVO.getWeight().split("@")[0].length()==1)
					{	
						fb.setWeight("00"+fb.getWeight()+".0");
					}
					if(deceasedDtlVO.getWeight().split("@")[0].length()==2)
					{	
						fb.setWeight("0"+fb.getWeight()+".0");
					}
					if(deceasedDtlVO.getWeight().split("@")[0].length()==3)
					{	
						fb.setWeight(fb.getWeight()+".0");
					}
				}	
				else
				{
					if(deceasedDtlVO.getWeight().split("@")[0].length()==1)
					{
						fb.setWeight("00"+fb.getWeight());
					}
					if(deceasedDtlVO.getWeight().split("@")[0].length()==2)
					{
						fb.setWeight("0"+fb.getWeight());
					}
				}
			}
	
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			fb.setDeceasedNo("");
			objStatus.add(Status.DONE, "", "Invalid Deceased No");
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
	
	public static void saveDeceasedGeneralAppearance(DeceasedGeneralAppearanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		
		try
		{
			HelperMethods.populate(deceasedDtlVO, fb);
			/*if(!fb.getLength1().equals("") || !fb.getLength2().equals(""))
			{
				String l1=fb.getLength1().equals("")?"0":fb.getLength1();
				String l2=fb.getLength2().equals("")?"0":fb.getLength2();
				deceasedDtlVO.setLength(l1+"."+l2);
			}
			if(!fb.getWeight1().equals("") || !fb.getWeight2().equals(""))
			{
				String w1=fb.getWeight1().equals("")?"0":fb.getWeight1();
				String w2=fb.getWeight2().equals("")?"0":fb.getWeight2();
				deceasedDtlVO.setWeight(w1+"."+w2);
			}*/
		//	deceasedDtlVO.setLength(fb.getLength1());
		//	deceasedDtlVO.setWeight(fb.getWeight1());
			
			DeceasedGeneralAppearanceDATA.saveDeceasedGeneralAppearance(deceasedDtlVO,getUserVO(request));
			
			objStatus.add(Status.DONE,"","Record Save Successfully");
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
	
	public static void searchDeceasedNo(DeceasedGeneralAppearanceFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			deceasedDtlVO=DeceasedGeneralAppearanceDATA.searchDeceasedNo(fb.getSearchFName(),fb.getSearchMName(),fb.getSearchLName(),fb.getDeathDate(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_SEARCHED_DECEASED_VO ,deceasedDtlVO);
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
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
}
