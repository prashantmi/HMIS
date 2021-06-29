package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.PatientImageDtlVO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import mrd.MrdConfig;
import mrd.transaction.controller.data.PatImageUploadDATA;
import mrd.transaction.controller.fb.PatImageUploadFB;
import registration.RegistrationConfig;


public class PatImageUploadUTL extends ControllerUTIL
{
	
	/** Getting The List of Images uploaded By Cr No 
	 * @param fb
	 * @param request
	 */
	public static void getPatientImageDtlByCrNo(PatImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List patientImageDtlVOList=null;
		PatientImageDtlVO patimageVO=null;
		//byte array[]=null;
		List byteArray=new ArrayList();
		try
		{
			patientImageDtlVOList=PatImageUploadDATA.getPatientImageDtlByCrNo(fb.getPatCrNo(),getUserVO(request));
			for(int i=0;i<patientImageDtlVOList.size();i++){
				patimageVO=new PatientImageDtlVO(); 
				patimageVO=(PatientImageDtlVO)patientImageDtlVOList.get(i);
				//String filepath=patimageVO.getFilePath().replace("\\","/")+"/" + patimageVO.getFileNo();
				//InputStream is = FTPFileTransfer.retrieveFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patimageVO.getFileNo(), patimageVO.getPatCrNo());
				
				
				/*// Previously used for mongodb connection
				  byte[] getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(patimageVO.getFileNo());
				 // end of mongodb */
				
				
				// Added by Manisha Gangwar Date: 22.3.2018 for saving image to Postgres
				 byte[] getDoc= PatImageUploadDATA.latestFetchImagePostgres(patimageVO.getFileNo());
				// byte[] getDoc = Base64.decodeBase64(decodedData);
				
				//byteArray.add(HelperMethods.getByteArrayOfImage(filepath));
				byte[] readBytes = null;
				try
				{
					//readBytes = new byte[is.available()];
					//is.read(readBytes);

					byteArray.add(getDoc);
				}
				catch (Exception e)
				{
					readBytes = null;
					e.printStackTrace();
				}
				//byteArray.add(readBytes);
			}
			fb.setPatCrNo(((PatientImageDtlVO)patientImageDtlVOList.get(0)).getPatCrNo());
			fb.setSerialNo(((PatientImageDtlVO)patientImageDtlVOList.get(0)).getSerialNo());
			fb.setMaxSerialNo(((PatientImageDtlVO)patientImageDtlVOList.get(patientImageDtlVOList.size()-1)).getSerialNo());
			WebUTIL.setAttributeInSession(request,Config.IMAGE_BYTE_ARRAY , byteArray);
			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_IMAGE_DTL_VO_LIST , patientImageDtlVOList);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST, e.getMessage(), "");
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
	
	public static void uploadImage(PatImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		byte array[]=null;
		Map byteArrayMap=null;
		//String filepath=fb.getFilePath();
		List byteArray=null;
		int fileNo=Integer.parseInt(fb.getNoOfImages());
		try
		{
			byteArrayMap=(LinkedHashMap)request.getSession().getAttribute(MrdConfig.PATIENT_IMAGE_MAP);
			byteArray=(List)request.getSession().getAttribute(MrdConfig.PATIENT_IMAGE_UPLOADED_IN_SESSION);
			if(byteArrayMap==null){
				byteArrayMap=new LinkedHashMap();
			}
			if(byteArray==null){
				byteArray=new ArrayList();
				//byteArray.add("");
			}
		
			array=(byte[])request.getSession().getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
			byteArrayMap.put(fileNo, array);
			byteArray.add(fileNo,array);
			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_IMAGE_MAP, byteArrayMap);
			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_IMAGE_UPLOADED_IN_SESSION, byteArray);
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
	
	public static void removeImage(PatImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//byte array[]=null;
		Map byteArrayMap=new LinkedHashMap();
		//String filepath=fb.getFilePath();
		List byteArray=null;
		//int fileNo=Integer.parseInt(fb.getNoOfImages());
		int fileNo=Integer.parseInt(fb.getRemoveImageIndex());
		try
		{
			//if(byteArrayMap.containsKey(fileNo)){
			byteArray=(List)request.getSession().getAttribute(MrdConfig.PATIENT_IMAGE_UPLOADED_IN_SESSION);
			byteArrayMap=(LinkedHashMap)request.getSession().getAttribute(MrdConfig.PATIENT_IMAGE_MAP);
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
			
			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_IMAGE_MAP, byteArrayMap);
			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_IMAGE_UPLOADED_IN_SESSION, byteArray);
			//}
			objStatus.add(Status.LIST);
			objStatus.add(Status.TRANSINPROCESS);
			System.out.println("byteArrayMap.size():"+byteArrayMap.size());
			fb.setNoOfImages(byteArrayMap.size()+"");
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
	
	public static void deletePatientImage(PatImageUploadFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		String chk[]=fb.getChk();
		PatientImageDtlVO patimageVO[]=null;
		//List byteArrayList=null;
		try
		{
			//int maxSerialNo=Integer.parseInt(fb.getMaxSerialNo()==""?"0":fb.getMaxSerialNo());
			//byteArrayList=(List)request.getSession().getAttribute(Config.IMAGE_BYTE_ARRAY);
			//if(byteArrayList!=null){
				patimageVO=new PatientImageDtlVO[chk.length];
				for(int i=0;i<patimageVO.length;i++){
					patimageVO[i]=new PatientImageDtlVO();
					patimageVO[i].setPatCrNo(chk[i].split("#")[0]);
					patimageVO[i].setSerialNo(chk[i].split("#")[1]);
					//byteArrayList.remove(Integer.parseInt(chk[i].split("#")[1])-1);
				}
			//}
			PatImageUploadDATA.deletePatientImage(patimageVO,getUserVO(request));
			String filepath=patimageVO[0].getFilePath();
			File f=null;
			for(int i=0;i<patimageVO.length;i++){
				//patimageVO[i]=new PatientImageDtlVO();
				String fileName=patimageVO[i].getFileNo();
				f=new File(filepath+"/"+fileName);
				f.delete();
			}
			
			//fb.setMaxSerialNo(String.valueOf(--maxSerialNo));
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

	public static void savePatImage(PatImageUploadFB fb,HttpServletRequest request) {
			Status objStatus = new Status();
			//String chk[]=fb.getChk();
			PatientImageDtlVO patimageVO[]=null;
			List byteArrayList=null;
			String filepath="";
			String fileName="";
			int maxSerialNo=Integer.parseInt(fb.getMaxSerialNo()==""?"0":fb.getMaxSerialNo());
			try
			{
				//String osName=System.getProperties().getProperty("os.name")
				if(System.getProperty("os.name").startsWith("Linux")){
					filepath="/root" + Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX;
				}
				else{
					filepath=Config.PATIENT_IMAGE_FILE_STORAGE_PATH;
				}
				byteArrayList=(List)request.getSession().getAttribute(MrdConfig.PATIENT_IMAGE_UPLOADED_IN_SESSION);
				//byteArrayList.remove(0);
				if(byteArrayList!=null && byteArrayList.size()>0){
					patimageVO=new PatientImageDtlVO[byteArrayList.size()];
					for(int i=0;i<byteArrayList.size();i++){
						/* Chnaged By ANANT PATEL for FTP file SAVE folder structure
						 * on 19th Dec 2014
						 */
						 
						//fileName=fb.getPatCrNo()+"#"+ ++maxSerialNo;//---- Change File name logic here
						fileName=FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+String.format("%02d" , maxSerialNo)+".png";  
						//patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+"01");
						/*File file=new File(filepath+"/"+fileName);
						System.out.println(file.exists());
						if(!file.exists()){
							file=new File(filepath);
							System.out.println(file.mkdir());
							file=new File(filepath+"/"+fileName);
						}
						FileOutputStream fos=new FileOutputStream(file);
						fos.write((byte[])byteArrayList.get(i));
						
						/* Chnaged By ANANT PATEL for FTP file SAVE
						 * on 19th Dec 2014
						 */
						 
						patimageVO[i]=new PatientImageDtlVO();
						HelperMethods.populate(patimageVO[i], fb);
						patimageVO[i].setFileNo(fileName);
						patimageVO[i].setIsValid(Config.IS_VALID_ACTIVE);
						patimageVO[i].setFilePath(filepath);
						//patimageVO[i].set Setting Image Content in VO here
						patimageVO[i].setImageFile((byte[])byteArrayList.get(i));
						if((List)request.getSession().getAttribute(Config.IMAGE_BYTE_ARRAY)==null){
							if(fb.getIsDefault().equals(String.valueOf(i))){
								patimageVO[i].setIsImageDefault(MrdConfig.YES);
							}
							else {
								patimageVO[i].setIsImageDefault(MrdConfig.NO);
							}
					   }
						/*---------------Added by warish 26-10-2017 using default set images-----------*/
						else if((List)request.getSession().getAttribute(Config.IMAGE_BYTE_ARRAY)!=null){
							if(fb.getIsDefault().equals(String.valueOf(i))){
							patimageVO[i].setIsImageDefault(MrdConfig.YES);
						}
						else{
							patimageVO[i].setIsImageDefault(MrdConfig.YES);
						}
							//patimageVO[i].setIsImageDefault(MrdConfig.NO);
						
						}
					}
					
				}	
				PatImageUploadDATA.savePatientImage(patimageVO,getUserVO(request));
				
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
	 * Modifying the patient default Image....
	 * @param fb
	 * @param request
	 */
	public static void modifyPatImage(PatImageUploadFB fb,HttpServletRequest request) {
		Status objStatus = new Status();
		//String chk[]=fb.getChk();
		PatientImageDtlVO patimageVO[]=null;
		List patImageVOList=null;
		try
		{
			patImageVOList=(List)request.getSession().getAttribute(MrdConfig.PATIENT_IMAGE_DTL_VO_LIST);
			
			if(patImageVOList!=null){
				patimageVO=new PatientImageDtlVO[patImageVOList.size()];
				for(int i=0;i<patImageVOList.size();i++){
					patimageVO[i]=(PatientImageDtlVO)patImageVOList.get(i);
									
					if(fb.getIsDefault().equals(String.valueOf(i))){
						patimageVO[i].setIsImageDefault("1");
					}
					else{
						patimageVO[i].setIsImageDefault("0");
					}
				}
				
			}	
			PatImageUploadDATA.modifyPatientImage(patimageVO,getUserVO(request));
			
			objStatus.add(Status.LIST,"","Images Modified Successfully");
			
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
