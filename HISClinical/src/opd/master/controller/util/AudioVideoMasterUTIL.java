package opd.master.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.AudioVideoMasterDATA;
import opd.master.controller.fb.AudioVideoMasterFB;

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
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.UserVO;

public class AudioVideoMasterUTIL extends ControllerUTIL
{
	public static void addFile(AudioVideoMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		
		try
		{
			String file=(String)session.getAttribute(OpdConfig.UPLOADED_AUDIO_VIDEO_FILE_NAME);
			//String fileName=file.substring(0,file.lastIndexOf("."));
			//String ext=file.substring(file.lastIndexOf("."));
			
			fb.setUploadFileName(file);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void saveAudioVideoFile(AudioVideoMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		
		byte[] fileAsByte=null;
		
		AudioVideoMasterVO audioVideoMasterVO=new AudioVideoMasterVO();
		UserVO userVO = getUserVO(request);
		
		try
		{
			String file=(String)session.getAttribute(OpdConfig.UPLOADED_AUDIO_VIDEO_FILE_NAME);
			//String fileName=file.substring(0,file.lastIndexOf("."));
			String ext=file.substring(file.lastIndexOf("."));
			fileAsByte=(byte[])session.getAttribute(OpdConfig.UPLOADED_AUDIO_VIDEO_FILE_AS_ARRAY);
			
			audioVideoMasterVO.setFile(fileAsByte);
			audioVideoMasterVO.setExt(ext);
			audioVideoMasterVO.setFileName(file);
			audioVideoMasterVO.setFileHeader(fb.getFileHeader());
			audioVideoMasterVO.setIsDefault(fb.getIsDefault());
			
			AudioVideoMasterDATA.saveAudioVideoFile(audioVideoMasterVO,userVO);
			objStatus.add(Status.DONE,"Audio Video Uploaded Successfully","");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void getAudioVideoForModify(AudioVideoMasterFB fb,HttpServletRequest request)
	{
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@"); 
		Status objStatus=new Status();
		String fileCode=code[0];
		String slNo=code[2];
		UserVO userVO=getUserVO(request);
		try
		{
			AudioVideoMasterVO avMasterVO=AudioVideoMasterDATA.getAudioVideoForModify(fileCode,slNo,userVO);
			HelperMethods.populate(fb,avMasterVO);
			fb.setUploadFileName(avMasterVO.getFileName());
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static boolean saveModifyAudioVideo(AudioVideoMasterFB fb,HttpServletRequest request)
	{
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@"); 
		String fileCode=code[0];
		String slNo=code[2];
		Status  objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		byte[] fileAsByte=null;
		boolean flag=false;
		
		AudioVideoMasterVO audioVideoMasterVO=new AudioVideoMasterVO();
		UserVO userVO = getUserVO(request);
		
		try
		{
			String file=(String)session.getAttribute(OpdConfig.UPLOADED_AUDIO_VIDEO_FILE_NAME);
			if(file!=null)
			{
				//String fileName=file.substring(0,file.lastIndexOf("."));
				String ext=file.substring(file.lastIndexOf("."));
				fileAsByte=(byte[])session.getAttribute(OpdConfig.UPLOADED_AUDIO_VIDEO_FILE_AS_ARRAY);
				
				audioVideoMasterVO.setFile(fileAsByte);
				audioVideoMasterVO.setExt(ext);
				audioVideoMasterVO.setFileName(file);
				audioVideoMasterVO.setFileCode(fileCode);
				audioVideoMasterVO.setFileHeader(fb.getFileHeader());
				audioVideoMasterVO.setSlNo(slNo);
				audioVideoMasterVO.setIsDefault(fb.getIsDefault());
				
				AudioVideoMasterDATA.saveModifyAudioVideo(audioVideoMasterVO,userVO);
				flag=true;
			}
			else
			{
				HisFileControlUtil fileUtil = new HisFileControlUtil(fb.getUploadFileName(),Config.OPD_AUDIO_VIDEO_STORAGE_PATH_WINDOWS,Config.OPD_AUDIO_VIDEO_STORAGE_PATH_LINUX);
				
				file=fb.getUploadFileName();
				//String fileName=file.substring(0,file.lastIndexOf("."));
				String ext=file.substring(file.lastIndexOf("."));
				fileUtil.readFile();
				fileAsByte=fileUtil.getFileContent();
				
				audioVideoMasterVO.setFile(fileAsByte);
				audioVideoMasterVO.setExt(ext);
				audioVideoMasterVO.setFileName(file);
				audioVideoMasterVO.setFileCode(fileCode);
				audioVideoMasterVO.setFileHeader(fb.getFileHeader());
				audioVideoMasterVO.setSlNo(slNo);
				audioVideoMasterVO.setIsDefault(fb.getIsDefault());
				
				AudioVideoMasterDATA.saveModifyAudioVideo(audioVideoMasterVO,userVO);
				flag=true;
			}
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			return flag;
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		return flag;
	}

}
