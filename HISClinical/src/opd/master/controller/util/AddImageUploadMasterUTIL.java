package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.UserVO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.itextpdf.text.pdf.codec.Base64.InputStream;
//import org.omg.CORBA.portable.InputStream;








import opd.OpdConfig;
import opd.master.controller.data.AddImageUploadMasterDATA;
import opd.master.controller.fb.AddImageUploadMasterFB;
import opd.transaction.controller.data.OpdImageExamTabDATA;
import opd.transaction.controller.fb.OpdImageExamTabFB;

import javax.servlet.*;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

public class AddImageUploadMasterUTIL extends ControllerUTIL
{
	// Adding Image
	public static void addImage(AddImageUploadMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		byte[] fileImageAsByte=null; // Added by Saurabh
		try
		{
			String image=(String)session.getAttribute(OpdConfig.UPLOADED_IMAGE_NAME);
			String imageName=image.substring(0,image.lastIndexOf("."));
			String imageExt=image.substring(image.lastIndexOf("."));
			/*Added by Saurabh on 6-sept-2017 for:image upload master*/
			fileImageAsByte=(byte[])session.getAttribute(OpdConfig.UPLOADED_IMAGE_AS_ARRAY);	
			ByteArrayInputStream in = new ByteArrayInputStream(fileImageAsByte);
			
			BufferedImage bImageFromConvert = ImageIO.read(in);

			//ImageIO.write(bImageFromConvert, "jpg", new File("c:/new-darksouls.jpg"));
			int height = bImageFromConvert.getHeight();
			int width  = bImageFromConvert.getWidth();
			
			 System.out.println("Width:" + width);
			 System.out.println("Height:" + height);
			 
		
			List list=new ArrayList();
			list.add(0,imageName);
			list.add(1,imageExt);
			fb.setExt(imageExt);
			fb.setImageHeight(Integer.toString(height));
			fb.setImageWidth(Integer.toString(width));
			
			String imageHeight = fb.getImageHeight();
			String imageWidth = fb.getImageWidth();
			
			 if(image.toLowerCase().endsWith(".jpg") || image.toLowerCase().endsWith(".jpeg"))
			 {
			//if(Integer.parseInt(imageHeight)>=768 && Integer.parseInt(imageWidth)>=1024)
			//{
				fb.setUploadImageName(image);
			
		//}
		}
			 else
			 {
				 objStatus.add(Status.IMAGEUPLOADERROR,"","Falied To Upload Image Please ensure that you are uploading JPG/JPEG image");
				 
			 }
			// WebUTIL.setAttributeInSession(request, OpdConfig.UPLOADED_IMAGE_AS_ARRAY, in);
		}
		//End Suarabh
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
	
	// Saving Image Record
	public static boolean saveImage(AddImageUploadMasterFB fb,HttpServletRequest request)
	{
		boolean flg = true;
		Status objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		byte[] fileImageAsByte=null;
		
		ImageMasterVO imageMasterVO=new ImageMasterVO();
		UserVO userVO = getUserVO(request);
		
		try
		{
			String image=(String)session.getAttribute(OpdConfig.UPLOADED_IMAGE_NAME);
			String imageFirstName=image.substring(0,image.lastIndexOf("."));
			String imageExt=image.substring(image.lastIndexOf("."));
			fileImageAsByte=(byte[])session.getAttribute(OpdConfig.UPLOADED_IMAGE_AS_ARRAY);
			if((session.getAttribute(OpdConfig.UPLOADED_IMAGE_AS_ARRAY))!=null)
			{
			ByteArrayInputStream in = new ByteArrayInputStream(fileImageAsByte);
			
			//BufferedImage bImageFromConvert = ImageIO.read(in);
			ByteArrayOutputStream in1 = new ByteArrayOutputStream();
			//byte[] buf=new byte[1024];
			for(int readnum;(readnum=in.read(fileImageAsByte))!=-1;)
			{
				in1.write(fileImageAsByte,0,readnum);
			}
			byte[] bytes= in1.toByteArray();
			
				//byte[] filContent=IOUtils.toByteArray((InputStream) WebUTIL.getSession(request).getAttribute(OpdConfig.UPLOADED_IMAGE_AS_ARRAY));
				imageMasterVO.setImageName(fb.getImageName());
				imageMasterVO.setImageFirstName(imageFirstName);
				imageMasterVO.setImageFile(bytes);
				imageMasterVO.setExt(imageExt);
				imageMasterVO.setUploadImageName(fb.getUploadImageName());
				imageMasterVO.setIsDefault(fb.getIsDefault());
			}
			/*imageMasterVO.setImageName(fb.getImageName());
			imageMasterVO.setImageFirstName(imageFirstName);
			imageMasterVO.setImageFile(fileImageAsByte);
			imageMasterVO.setExt(imageExt);
			imageMasterVO.setUploadImageName(fb.getUploadImageName());
			imageMasterVO.setIsDefault(fb.getIsDefault());*/
			
			/*Added by Saurabh on 7-Sept-2017 to store Image on HIS war*/
			//ByteArrayInputStream in = new ByteArrayInputStream(fileImageAsByte);
			//BufferedImage bImageFromConvert = ImageIO.read(in);
			
			// String Path = "E:/WORKSPACES/NIMS_03_08/HIS/WebContent/hisglobal/images/ImgForImgEdtr/testSave.jpg";
			//ServletContext context = request.getServletContext();
			//String Path = context.getRealPath("/../HIS.war/hisglobal/images/ImgForImgEdtr/"+".jpg");
			//String Path = context.getRealPath("/hisglobal/ImgForImgEdtr/");
			//String Path = context.getRealPath("/../HIS/hisglobal/images/ImgForImgEdtr/");
			//String Path = "C:/PHDM/HISClinical/imageMaster/testsave.jpg";
			 
			//ImageIO.write(bImageFromConvert, "jpg", new File(Path));
			
			//End Saurabh
			//imageMasterVO.setImageStoragePath(Path);
			AddImageUploadMasterDATA.saveImage(imageMasterVO,userVO);
			objStatus.add(Status.DONE,"Image Uploaded Successfully","");
		}
		catch(HisRecordNotFoundException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		return flg;
	}
	
	// Getting Image Records
	public static void getImageForModify(AddImageUploadMasterFB fb,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		String imageCode=code[0];
		String imageSlno=code[2];
		InputStream inputStream = null;
		UserVO userVO = getUserVO(request);
		
		try
		{
			ImageMasterVO imageMasterVO=AddImageUploadMasterDATA.getImageForModify(imageCode,imageSlno,userVO);
			HelperMethods.populate(fb,imageMasterVO);
			fb.setUploadImageName(imageMasterVO.getFileName());
			/*byte[] getImage=imageMasterVO.getImageFile();
			
			response.setContentType("image/jpg");
			OutputStream os = response.getOutputStream();
			inputStream = new ByteArrayInputStream(getImage);
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}  */
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
	
	// Modifying Image Record 
	public static boolean saveModifyImage(AddImageUploadMasterFB fb,HttpServletRequest request)
	{
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		String imageCode=code[0];
		String slNo=code[2];
		Status  objStatus=new Status();
		HttpSession session=WebUTIL.getSession(request);
		byte[] fileImageAsByte=null;
		boolean flag=false;
		
		ImageMasterVO imageMasterVO=new ImageMasterVO();
		UserVO userVO = getUserVO(request);
		try
		{
			String image=(String)session.getAttribute(OpdConfig.UPLOADED_IMAGE_NAME);
			if(image!=null)
			{
				fileImageAsByte=(byte[])session.getAttribute(OpdConfig.UPLOADED_IMAGE_AS_ARRAY);
				String imageExt=image.substring(image.lastIndexOf("."));
				imageMasterVO.setImageFile(fileImageAsByte);
				imageMasterVO.setExt(imageExt);
				
			}
			
			imageMasterVO.setImageName(fb.getImageName());
			
			imageMasterVO.setFileName(fb.getUploadImageName());
			imageMasterVO.setImageCode(imageCode);
			imageMasterVO.setIsDefault(fb.getIsDefault());
			imageMasterVO.setSlNo(slNo);
			imageMasterVO.setIsValid(fb.getIsValid());
			AddImageUploadMasterDATA.saveModifyImage(imageMasterVO,userVO);
			flag=true;
			objStatus.add(Status.DONE,"Image Modified Successfully","");
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
	
	
	public static void setImageLog(AddImageUploadMasterFB fb, HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		String imageCode=code[0];
		String imageSlno=code[2];
		InputStream inputStream = null;
		UserVO userVO = getUserVO(request);
		try
		{
			byte[] getImage=AddImageUploadMasterDATA.fetchimageFromPostgres(imageCode);
			/*HelperMethods.populate(fb,imageMasterVO);
			fb.setUploadImageName(imageMasterVO.getFileName());
			byte[] getImage=imageMasterVO.getImageFile();*/
			
			response.setContentType("image/jpg");
			OutputStream os = response.getOutputStream();
			inputStream = new ByteArrayInputStream(getImage);
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}  
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
}
