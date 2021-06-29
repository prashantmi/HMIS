package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.PhotoUploadDATA;
import mortuary.transaction.controller.fb.PhotoUploadFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;

public class PhotoUploadUTL extends ControllerUTIL
{
	public static void getExistingPhoto(PhotoUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		MortuaryDeceasedImageDtlVO[] imageDtlVO=null;
		List byteArray=new ArrayList();
		String deceasedNo="";
		String patCrNo="";
		String index="";
		try
		{
			fb.setImageAddedFlag("0");		//0-->Image Added 1-->Image Not Added
			DeceasedDetailVO[] deceasedVO=(DeceasedDetailVO[])session.getAttribute(DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST);
			for(int i=0;i<deceasedVO.length;i++)
			{
				if(fb.getPostmortemId().equalsIgnoreCase(deceasedVO[i].getPostmortemId()))
				{
					deceasedNo=deceasedVO[i].getDeceasedNo();
					patCrNo=deceasedVO[i].getPatCrNo();
					break;
				}	
			}
			fb.setDeceasedNo(deceasedNo);
			fb.setPatCrNo(patCrNo);
			DeceasedTileUTL.getDeceasedDetailByDeceasedNo(fb, request);
			imageDtlVO=PhotoUploadDATA.getExistingPhoto(deceasedNo,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_ADDED_PHOTO_VO, imageDtlVO);
			byte[] getDoc;
			if(imageDtlVO!=null)
			{
				for(int i=0;i<imageDtlVO.length;i++)
				{
					String filepath= imageDtlVO[i].getFileNo();
					//byteArray.add(HelperMethods.getByteArrayOfImage(filepath));		//Getting Deceased Existing Images
					getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_MORTUARY_PATIENT_IMAGE_UPLOAD).latestFetchFile(filepath);
					byteArray.add(getDoc);
					
					if(imageDtlVO[i].getIsDefaultImage().equals(MortuaryConfig.IS_DEFAULT_IMAGE_YES))
					{
						index=String.valueOf(i);
						fb.setIndex(index);
					}	
				}
				WebUTIL.setAttributeInSession(request,Config.IMAGE_BYTE_ARRAY , byteArray);
			}
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
	
	public static void addNewImage(PhotoUploadFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		String fileName="";
	//	String fileExt="";
		byte[] fileDataAsByte=null;
		MortuaryDeceasedImageDtlVO[] docUploadVO1=null;
		MortuaryDeceasedImageDtlVO[] docUploadVO2=null;
		MortuaryDeceasedImageDtlVO[] docUploadVO3=null;
		MortuaryDeceasedImageDtlVO[] docUploadVO=null;
		List byteArray=new ArrayList();
		
		
		try
		{
			fileDataAsByte=(byte[])session.getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
			fileName=(String)session.getAttribute(RegistrationConfig.UPLOADED_FILE_NAME);
			//fileExt=fileName.substring(fileName.lastIndexOf("."));
			
			docUploadVO1=(MortuaryDeceasedImageDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			
			if(docUploadVO1==null)
			{
				docUploadVO2=new MortuaryDeceasedImageDtlVO[1];
				docUploadVO2[0]=new MortuaryDeceasedImageDtlVO();
				
				docUploadVO2[0].setImageFile(fileDataAsByte);
				docUploadVO2[0].setImageHeader(fb.getImageHeader());
				docUploadVO2[0].setUploadRemarks(fb.getUploadRemarks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_NEW_ADDED_IMAGE, docUploadVO2);
				
				for(int k=0;k<docUploadVO2.length;k++)
				{
					byteArray.add(docUploadVO2[k].getImageFile());
				}
				WebUTIL.setAttributeInSession(request,MortuaryConfig.UPLOADED_IMAGE_IN_SESSION , byteArray);
			}
			else
			{
				docUploadVO3=new MortuaryDeceasedImageDtlVO[docUploadVO1.length+1];
				int i=0;
				for(;i<docUploadVO1.length;i++)
				{
					docUploadVO3[i]=docUploadVO1[i];
				}
				
				docUploadVO3[i]=new MortuaryDeceasedImageDtlVO();
				docUploadVO3[i].setImageFile(fileDataAsByte);
				docUploadVO3[i].setImageHeader(fb.getImageHeader());
				docUploadVO3[i].setUploadRemarks(fb.getUploadRemarks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_NEW_ADDED_IMAGE, docUploadVO3);
				
				
				for(int k=0;k<docUploadVO3.length;k++)
				{
					byteArray.add(docUploadVO3[k].getImageFile());
				}
				WebUTIL.setAttributeInSession(request,MortuaryConfig.UPLOADED_IMAGE_IN_SESSION , byteArray);
			}
			
			
			docUploadVO=(MortuaryDeceasedImageDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			if(docUploadVO.length>0)
				fb.setImageAddedFlag("1");
			
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
	
	public static void removeNewImage(PhotoUploadFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		MortuaryDeceasedImageDtlVO[] docUploadVO1=null;
		MortuaryDeceasedImageDtlVO[] docUploadVO2=null;
		MortuaryDeceasedImageDtlVO[] docUploadVO=null;
		List byteArray=new ArrayList();
		
		try
		{
			docUploadVO1=(MortuaryDeceasedImageDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			docUploadVO2=new MortuaryDeceasedImageDtlVO[docUploadVO1.length-1];
			
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
				byteArray.add(docUploadVO2[k].getImageFile());
			}
			WebUTIL.setAttributeInSession(request,MortuaryConfig.UPLOADED_IMAGE_IN_SESSION , byteArray);
			
			docUploadVO=(MortuaryDeceasedImageDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			if(docUploadVO.length==0)
				fb.setImageAddedFlag("0");
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
	
	public static void saveImage(PhotoUploadFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		MortuaryDeceasedImageDtlVO[] deceasedImageDtlVO=null;
		String defaultImageDeceasedNo="";
	//	int len=0;
		String deceasedNo="";
		
		try
		{
			/*MortuaryDeceasedImageDtlVO[] imageDtlVO=(MortuaryDeceasedImageDtlVO[])session.getAttribute(MortuaryConfig.ARR_DECEASED_ADDED_PHOTO_VO);
			if(imageDtlVO!=null)
				len=imageDtlVO.length;*/
			
			boolean flag =HisFileControlUtil.isWindowsOS();
			String path="";
			if(flag)
				path=Config.MORTUARY_PATIENT_IMAGE_PATH_WINDOWS;
			else
				path="/root" + Config.MORTUARY_PATIENT_IMAGE_PATH_LINUX;
			
		/*	String defaultImage=fb.getIsDefaultImage();
			String existFlag=defaultImage.split("@")[0];
			String idx=defaultImage.split("@")[1];
			if(existFlag.equals(MortuaryConfig.IMAGE_EXIST_YES))
			{
				defaultImageDeceasedNo=String.valueOf(Integer.parseInt(idx)+1);
			}
			else
			{
				defaultImageDeceasedNo=String.valueOf(Integer.parseInt(idx)+1+len);
			}*/
			
			
			MortuaryDeceasedImageDtlVO[] docUploadVO=(MortuaryDeceasedImageDtlVO[])session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE);
			if(docUploadVO!=null)
			{
				deceasedImageDtlVO=new MortuaryDeceasedImageDtlVO[docUploadVO.length];
				for(int j=0;j<docUploadVO.length;j++)
				{
					deceasedImageDtlVO[j]=new MortuaryDeceasedImageDtlVO();
					deceasedImageDtlVO[j].setImageFile(docUploadVO[j].getImageFile());
					deceasedImageDtlVO[j].setUploadMode(MortuaryConfig.IMAGE_UPLOAD_FROM_MORTUARY);
					deceasedImageDtlVO[j].setFilePath(path);
					deceasedImageDtlVO[j].setDeceasedNo(fb.getDeceasedNo());
					deceasedImageDtlVO[j].setPatCrNo(fb.getPatCrNo());
					deceasedImageDtlVO[j].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_NO);
					deceasedImageDtlVO[j].setImageHeader(docUploadVO[j].getImageHeader());
					deceasedImageDtlVO[j].setUploadRemarks(docUploadVO[j].getUploadRemarks());
				}
			}	
			deceasedNo=fb.getDeceasedNo();
			PhotoUploadDATA.saveUploadedPhoto(deceasedNo,defaultImageDeceasedNo,deceasedImageDtlVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Added Successfully");
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
