package mortuary.transaction.controller.utl;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedGeneralAppearanceDATA;
import mortuary.transaction.controller.data.MortuaryImageUploadDATA;
import mortuary.transaction.controller.fb.MortuaryImageUploadFB;
import mrd.MrdConfig;
import registration.RegistrationConfig;

public class MortuaryImageUploadUTL extends ControllerUTIL
{
	
	/** Getting The List of Images uploaded By Deceased No 
	 * @param fb
	 * @param request
	 */
	public static void getPatientImageDtlByDeceasedNo(MortuaryImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List <MortuaryDeceasedImageDtlVO> mortuaryDeceasedImageDtlVOList=null;
		MortuaryDeceasedImageDtlVO deceasedImageVO=null;
		byte array[]=null;
		List byteArray=new ArrayList();
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		try
		{
			
			mortuaryDeceasedImageDtlVOList=MortuaryImageUploadDATA.getPatientImageDtlByDeceasedNo(fb.getDeceasedNo(),getUserVO(request));
			
					
			//commented for using mongodb,  date: 20.6.2016
			//code for retrieving from win/linux filesystem
			/*
			if(mortuaryDeceasedImageDtlVOList!=null)
			{
			for(int i=0;i<mortuaryDeceasedImageDtlVOList.size();i++){
				deceasedImageVO=new MortuaryDeceasedImageDtlVO(); 
				deceasedImageVO=(MortuaryDeceasedImageDtlVO)mortuaryDeceasedImageDtlVOList.get(i);
				String filepath=deceasedImageVO.getFilePath().replace("\\","/")+"/" + deceasedImageVO.getFileNo();
				System.out.println("filepath :"+filepath);
				byteArray.add(HelperMethods.getByteArrayOfImage(filepath));
				/*
				File f=new File(filepath);
				FileInputStream fis=new FileInputStream(f);
				array=new byte[fis.available()];
				fis.read(array);
				byteArray.add(array); */
			
			//}  
			
			
			//retrieving from mongodb
			byte[] getDoc;
			if(mortuaryDeceasedImageDtlVOList!=null)
			{
			for(int i=0;i<mortuaryDeceasedImageDtlVOList.size();i++){
				deceasedImageVO=new MortuaryDeceasedImageDtlVO(); 
				deceasedImageVO=(MortuaryDeceasedImageDtlVO)mortuaryDeceasedImageDtlVOList.get(i);
				String filepath=deceasedImageVO.getFileNo();
				
				System.out.println("fileName :"+filepath);
				//byteArray.add(HelperMethods.getByteArrayOfImage(filepath));
				getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_MORTUARY_PATIENT_IMAGE_UPLOAD).latestFetchFile(filepath);
				byteArray.add(getDoc);		
			}
			
			
			
			fb.setDeceasedNo(mortuaryDeceasedImageDtlVOList.get(0).getDeceasedNo());
			fb.setPatCrNo(mortuaryDeceasedImageDtlVOList.get(0).getPatCrNo());
			fb.setSlNo(mortuaryDeceasedImageDtlVOList.get(0).getSlNo());
			fb.setMaxSerialNo(String.valueOf(getMaxSerialNo(mortuaryDeceasedImageDtlVOList)));
			WebUTIL.setAttributeInSession(request,Config.IMAGE_BYTE_ARRAY , byteArray);
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO , mortuaryDeceasedImageDtlVOList);
			//objStatus.add(Status.LIST);
			//}
			}
				objStatus.add(Status.TRANSINPROCESS);
			
			
		} 
		//Anant Patel
		/*catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST, e.getMessage(), "");
		} */
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			fb.setDeceasedNo("");
			objStatus.add(Status.NEW, "", e.getMessage());
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
	
	/**
	 * Add the image to the array and save it in session
	 * @param fb
	 * @param request
	 */
	public static void uploadImage(MortuaryImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		byte array[]=null;
		Map byteArrayMap=null;
		String filepath=fb.getFilePath();
		List byteArray=null;
		int fileNo=Integer.parseInt(fb.getNoOfImages());
		try
		{
			byteArrayMap=(LinkedHashMap)request.getSession().getAttribute(MortuaryConfig.PATIENT_IMAGE_MAP);
			byteArray=(List)request.getSession().getAttribute(MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION);
			if(byteArrayMap==null){
				byteArrayMap=new LinkedHashMap();
			}
			if(byteArray==null){
				byteArray=new ArrayList();
			
				//byteArray.add("");
			}
			Map content=new HashMap();
			array=(byte[])request.getSession().getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
			byteArrayMap.put(fileNo, array);
			byteArray.add(fileNo,array);
			WebUTIL.setAttributeInSession(request,MortuaryConfig.PATIENT_IMAGE_MAP, byteArrayMap);
			WebUTIL.setAttributeInSession(request,MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION, byteArray);
			objStatus.add(Status.LIST);
			objStatus.add(Status.TRANSINPROCESS);
			fb.setNoOfImages(byteArrayMap.size()+"");
			System.out.println("byteArrayMap.size():"+byteArrayMap.size());
			
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
	
	/**
	 * Remove the selected image from the array and save it in session 
	 * @param fb
	 * @param request
	 */
	public static void removeImage(MortuaryImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		byte array[]=null;
		Map byteArrayMap=new LinkedHashMap();
		String filepath=fb.getFilePath();
		List byteArray=null;
		//get the key of the image which has remove
		int fileNo=Integer.parseInt(fb.getRemoveImageIndex());
		try
		{
			//if(byteArrayMap.containsKey(fileNo)){
			byteArray=(List)request.getSession().getAttribute(MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION);
			byteArrayMap=(LinkedHashMap)request.getSession().getAttribute(MortuaryConfig.PATIENT_IMAGE_MAP);
			if(byteArray!=null){
				byteArray.remove(fileNo);
			}
			if(byteArrayMap!=null){
				byteArrayMap.remove(fileNo);
				Set keySet=byteArrayMap.keySet();
				Iterator itr=keySet.iterator();
				while (itr.hasNext()) {
					int key=((Integer)itr.next()).intValue();
					byteArray.add(key, byteArrayMap.get(key));
					
				}
			}
			
			
			System.out.println("byteArrayMap.size():"+byteArrayMap.size());
			fb.setNoOfImages(byteArrayMap.size()+"");
			if(byteArrayMap.size()==0)
				byteArrayMap=null;

			WebUTIL.setAttributeInSession(request,MortuaryConfig.PATIENT_IMAGE_MAP, byteArrayMap);
			WebUTIL.setAttributeInSession(request,MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION, byteArray);
			//}
			objStatus.add(Status.LIST);
			objStatus.add(Status.TRANSINPROCESS);
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
	
	/**
	 * Delete the selected Deceased Image
	 * @param fb
	 * @param request
	 */
	public static void deleteDeceasedImage(MortuaryImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		String chk[]=fb.getChk();
		MortuaryDeceasedImageDtlVO deceasedImageVO[]=null;
		List byteArrayList=null;
		try
		{
			//int maxSerialNo=Integer.parseInt(fb.getMaxSerialNo().equals("")?"0":fb.getMaxSerialNo());
			//byteArrayList=(List)request.getSession().getAttribute(Config.IMAGE_BYTE_ARRAY);
			//if(byteArrayList!=null){
				deceasedImageVO=new MortuaryDeceasedImageDtlVO[chk.length];
				for(int i=0;i<deceasedImageVO.length;i++){
					deceasedImageVO[i]=new MortuaryDeceasedImageDtlVO();
					deceasedImageVO[i].setDeceasedNo(chk[i].split("#")[0]);
					deceasedImageVO[i].setSlNo(chk[i].split("#")[1]);
					//byteArrayList.remove(Integer.parseInt(chk[i].split("#")[1])-1);
				}
			//}
			MortuaryImageUploadDATA.deleteDeceasedImage(deceasedImageVO,getUserVO(request));
			
			
			
			//commented for using mongodb 
			/*
			//deleting the image from the win/linus filesystem
			String filepath=deceasedImageVO[0].getFilePath();
			File f=null;
			for(int i=0;i<deceasedImageVO.length;i++){
				//deceasedImageVO[i]=new MortuaryDeceasedImageDtlVO();
				String fileName=deceasedImageVO[i].getFileNo();
				f=new File(filepath+"/"+fileName);
				f.delete();
			}
			
			//fb.setMaxSerialNo(String.valueOf(--maxSerialNo));  */
			objStatus.add(Status.LIST,"","Images Deleted Successfully");
			
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

	
	/**
	 * save the Deceased Image
	 * @param fb
	 * @param request
	 */
	public static void saveDeceasedImage(MortuaryImageUploadFB fb,HttpServletRequest request) {
			Status objStatus = new Status();
			MortuaryDeceasedImageDtlVO deceasedImageVO[]=null;
			MortuaryDeceasedImageDtlVO deceasedImageUpdateVO[]=null;
			List<MortuaryDeceasedImageDtlVO> deceasedImageUpdateVOOld=null;
			List byteArrayList=null;
			String filepath="";
			String fileName="";
			int maxSerialNo=Integer.parseInt(fb.getMaxSerialNo().equals("")?"0":fb.getMaxSerialNo());
			try
			{
				deceasedImageUpdateVOOld=(List)request.getSession().getAttribute(MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO);
							
				
			// commented for saving to mongodb database  date: 20.6.2016
				/*			
			 //code for saving to win/linux filesystem 
			 	//String osName=System.getProperties().getProperty("os.name")
				 	if(System.getProperty("os.name").startsWith("Linux")){
					filepath="/root" + Config.MORTUARY_PATIENT_IMAGE_PATH_LINUX;
				}
				else{
					filepath=Config.MORTUARY_PATIENT_IMAGE_PATH_WINDOWS;
				}
				byteArrayList=(List)request.getSession().getAttribute(MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION);
				//if(byteArrayList!=null) 
					//byteArrayList.remove(0);
				//get the byte array of image from session and write them on file and save on disk
				//create the insert vo to save the detail in database
				if(byteArrayList!=null && byteArrayList.size()>0){
					deceasedImageVO=new MortuaryDeceasedImageDtlVO[byteArrayList.size()];
					for(int i=0;i<byteArrayList.size();i++){
						fileName=fb.getDeceasedNo()+"#"+ ++maxSerialNo;
						File file=new File(filepath+"/"+fileName);
						System.out.println(file.exists());
						if(!file.exists()){
							file=new File(filepath);
							System.out.println(file.mkdir());
							file=new File(filepath+"/"+fileName);
						}
						FileOutputStream fos=new FileOutputStream(file);
						fos.write((byte[])byteArrayList.get(i));	
						
				*/	
				
				
				
				
			//saving to mongodb datababse   added on 20.6.2016  by manisha Gangwar
			byteArrayList=(List)request.getSession().getAttribute(MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION);
			//if(byteArrayList!=null) 
				//byteArrayList.remove(0);
			//get the byte array of image from session and write them on file and save on disk
			//create the insert vo to save the detail in database
			if(byteArrayList!=null && byteArrayList.size()>0){
				deceasedImageVO=new MortuaryDeceasedImageDtlVO[byteArrayList.size()];
				
				
				for(int i=0;i<byteArrayList.size();i++){
					fileName=fb.getDeceasedNo()+"#"+ ++maxSerialNo;
					  MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_MORTUARY_PATIENT_IMAGE_UPLOAD).savePDFFile(fileName,(byte[])byteArrayList.get(i));
									
			        	deceasedImageVO[i]=new MortuaryDeceasedImageDtlVO();
						//HelperMethods.populate(deceasedImageVO[i], fb);
						deceasedImageVO[i].setDeceasedNo(fb.getDeceasedNo());
						deceasedImageVO[i].setPatCrNo(fb.getPatCrNo());
						deceasedImageVO[i].setFileNo(fileName);
						//deceasedImageVO[i].setIsValid(Config.IS_VALID_ACTIVE);
						deceasedImageVO[i].setFilePath(filepath);
						deceasedImageVO[i].setUploadMode(MortuaryConfig.IMAGE_UPLOAD_FROM_MORTUARY);
						deceasedImageVO[i].setUploadRemarks(fb.getUploadRemarks()[i]);
						deceasedImageVO[i].setImageHeader(fb.getImageHeader()[i]);
						
							
						
						
					}
					
				}
				
				if(deceasedImageUpdateVOOld!=null){
					deceasedImageUpdateVO=new MortuaryDeceasedImageDtlVO[deceasedImageUpdateVOOld.size()];
					for(int i=0;i<deceasedImageUpdateVOOld.size();i++){
						deceasedImageUpdateVO[i]=deceasedImageUpdateVOOld.get(i);
						//deceasedImageUpdateVO[i].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_NO);
					}
				}	
				if(deceasedImageVO!=null)
					setDefaultImage(deceasedImageVO, fb,MortuaryConfig.IMAGE_EXIST_NO);
				if(deceasedImageUpdateVO!=null)
					setDefaultImage(deceasedImageUpdateVO, fb,MortuaryConfig.IMAGE_EXIST_YES);
				
				
				
				MortuaryImageUploadDATA.saveDeceasedImage(deceasedImageVO,deceasedImageUpdateVO,getUserVO(request));
				
				objStatus.add(Status.LIST,"","Images Saved Successfully");
				
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

	
	/**
	 * returns the maximum serial no.
	 * @param deceasedImageVOList
	 * @return
	 */
	public static int getMaxSerialNo(List <MortuaryDeceasedImageDtlVO> deceasedImageVOList){
		int maxSerialNo=0;
		for(int i=0;i<deceasedImageVOList.size();i++){
			if(Integer.parseInt(deceasedImageVOList.get(i).getSlNo())>maxSerialNo){
				maxSerialNo=Integer.parseInt(deceasedImageVOList.get(i).getSlNo());
			}
		}
		return maxSerialNo;
	}
	
	
	/**
	 * set the default image 
	 * @param deceasedImageVO
	 * @param fb
	 * @param exists (Whether the image was previously added or not)
	 */
	public static void setDefaultImage(MortuaryDeceasedImageDtlVO [] deceasedImageVO,MortuaryImageUploadFB fb,String exists){
		
		for(int i=0;i<deceasedImageVO.length;i++){
			String value=fb.getIsDefault().split("#")[0];
			String index=fb.getIsDefault().split("#")[1];
			if(value.equals(exists) && index.equals(String.valueOf(i))){
				deceasedImageVO[i].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_YES);
			}
			else{
				deceasedImageVO[i].setIsDefaultImage(MortuaryConfig.IS_DEFAULT_IMAGE_NO);
			}
		}
	}
	
}
