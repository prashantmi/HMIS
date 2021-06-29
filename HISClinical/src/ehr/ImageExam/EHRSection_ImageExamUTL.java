package ehr.ImageExam;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.tools.imageeditor.HisImageEditorConfig;
import hisglobal.tools.imageeditor.util.ImageSerialObject;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.Patient1VO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdDocumentArchivalDATA;
import opd.transaction.controller.data.OpdImageExamTabDATA;
import opd.transaction.controller.fb.OpdImageEditorFB;
import opd.transaction.controller.fb.OpdImageExamTabFB;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ehr.vo.EHRVOUtility;

import java.util.LinkedHashMap;

public class EHRSection_ImageExamUTL extends ControllerUTIL
{
	
	public static void AJAX_GETIMAGELIST(EHRSection_ImageExamFB _fb, HttpServletRequest _request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		UserVO userVO = null;
		HttpSession session = null;
		EHRSection_ImageExamBO bo=new EHRSection_ImageExamBO();
		Map mpEssentials = new HashMap();
		EHRSection_ImageExamVO vo=new EHRSection_ImageExamVO();
		List ImageVO=new ArrayList();
		try
		{
			userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
			
			_fb.setUserSeatId(userVO.getUserSeatId());
			
			HelperMethods.populate(vo, _fb);
			
			ImageVO=bo.AJAX_GETIMAGELIST(vo, userVO);
			
			session.setAttribute(OpdConfig.IMAGE_EXAM_LIST,ImageVO);
			
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			String JSONObject_ImageList=gson.toJson(ImageVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(JSONObject_ImageList.toString());

			//System.out.println(JSONObject_ImageList);
			
			//WebUTIL.setMapInSession(mpEssentials, _request);
			
			/*if(lstImages==null || lstImages.size()==0)
				throw new HisRecordNotFoundException("No Image Exists for Examination");*/
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Getting Editor Essentials e.g. Color-Description List 
	public static void getEditorEssentials(OpdImageExamTabFB _fb, HttpServletRequest _request,HttpServletResponse response_p)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		List lstImages = new ArrayList();
		
		List byteArray=null;
		byte[] getdoc=null;
		try
		{
			ImageMasterVO vo=new ImageMasterVO();
			//OpdPatientImageDtlVO vo1=new OpdPatientImageDtlVO();
			session = WebUTIL.getSession(_request);
			setSysdate(_request);

			// Selected Image File Name
			lstImages = (ArrayList) session.getAttribute(OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT);
			for (Object o : lstImages)
			{
				vo= (ImageMasterVO) o;
				if (_fb.getImageCode().equals(vo.getImageCode()))
				{
					_fb.setTitle(vo.getImageName());
					_fb.setImageFileName(vo.getFileName());
					break;
				}
			}
			
			// Setting Color Pointers
			String colorDesc = (String) session.getAttribute(OpdConfig.OPD_ESSENTIAL_IMAGE_POINTERS_OF_DEPTUNIT);
			if (!colorDesc.equals("")) _fb.setColorDesc(colorDesc);

			// Setting Editor Essentials  FB
			Map mp = new HashMap();
			OpdImageEditorFB imgFB = new OpdImageEditorFB();
			imgFB.setInFileName(_fb.getImageFileName());				
			imgFB.setOutFileName(OpdConfig.OPD_IMAGE_EDITOR_FB_DEFAULT_OUT_FILE_NAME);// Default OutFile Name
			imgFB.setInFilePath(OpdConfig.OPD_EXAMINATION_IMAGE_PATH_VAR);
			imgFB.setOutFilePath(OpdConfig.OPD_EXAMINATION_PAT_IMAGE_PATH_VAR);
			imgFB.setTitle(_fb.getTitle());
			imgFB.setColorDesc(_fb.getColorDesc());

			mp.put(OpdConfig.OPD_IMAGE_EDITOR_FB, imgFB);
			WebUTIL.setMapInSession(mp, _request);

			session.removeAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM);	
			byte[] getDoc = OpdImageExamTabDATA.fetchImageFromPostgres(vo.getImageCode());
			//byte[] getDoc=MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_PATIENT_IMAGE_UPLOAD).latestFetchFile(File);
			String s = new sun.misc.BASE64Encoder().encode(getDoc);
			_fb.setImageOutFileName(s);
			//String s = Base64.getEncoder().encodeToString(getDoc);
			
			/*byte[] readBytes = null;
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
			}*/
			/*response_p.setContentType("image/jpg");
			inputStream = new ByteArrayInputStream(getDoc);
			OutputStream os = response_p.getOutputStream();
			bos = new BufferedOutputStream(os);
			
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}    */
			
			//ServletContext context = _request.getServletContext();
			//String Path = context.getRealPath("/hisglobal/ImgForImgEdtr/");

			//_fb.setDirOutPath(Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS);
			//_fb.setDirOutPath(Path);
			_fb.setEditingStatus("Image is Editing .... Please Enter Remark ...");
			session.setAttribute(OpdConfig.IMAGE_EXAM_SOURCE,s);

			objStatus.add(Status.TRANSINPROCESS);
		
		}
		/*catch(IOException e){
			e.printStackTrace();
		}*/
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Creating Entry For Image --Not Used 
	public static void createEntryForImage(OpdImageExamTabFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_rq);

		try
		{
			OpdPatientImageDtlVO vo = new OpdPatientImageDtlVO();
			vo.setPatCrNo(_fb.getPatCrNo());
			vo = OpdImageExamTabDATA.createEntryForImage(vo, userVO);
			_fb.setPatSerialNo(vo.getSerialNo());
			_fb.setImageOutFileName(vo.getImageFileName());
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Saving OPD Patient Image
	//public static boolean saveOpdPatientImage(OpdImageExamTabFB _fb, HttpServletRequest _request)	
	public static void SAVEIMAGE(String img64,EHRSection_ImageExamFB _fb, HttpServletRequest _request,HttpServletResponse response)
	{
		
		Status objStatus = new Status();
		BufferedImage bi=null;
		List byteArrayList=null;
		EHRSection_ImageExamVO patimageVO=null;
		String filepath="";
		String fileName="";
		UserVO userVO = null;
		HttpSession session = null;
		//PatientDetailVO[] dailyPatientVOs = null;
		
		try{
			InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(img64.getBytes()));  
			session = WebUTIL.getSession(_request);
			userVO = getUserVO(_request);
			setSysdate(_request);
			String entryDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			HelperMethods.populate(_fb, voDP);
			_fb.setPatCrNo(voDP.getPatCrNo());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			/*FileInputStream fis = new FileInputStream(img64);  
		    bi = ImageIO.read(fis);*/
			bi=ImageIO.read(stream);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( bi, "png", baos );
			baos.flush();
			//byte[] imgbt=new sun.misc.BASE64Decoder().decodeBuffer(img64);
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			fileName = _fb.getImageCode()+"-"+_fb.getPatCrNo()+"-"+_fb.getEpisodeCode()+".png";
			//fileName = _fb.getPatCrNo()+"-"+_fb.getEpisodeCode()+"-"+entryDate+".png";
			patimageVO=new EHRSection_ImageExamVO();
			HelperMethods.populate(patimageVO, _fb);
			patimageVO.setFileNo(fileName);
			patimageVO.setIsValid(Config.IS_VALID_ACTIVE);
			patimageVO.setDirPath(filepath);
			patimageVO.setImageFile(imageInByte);
			patimageVO.setEntryDate(entryDate);
			patimageVO.setRemarks(_fb.getRemark());
			patimageVO.setImageName(_fb.getImageName());
			patimageVO.setSeatId(userVO.getSeatId());
			patimageVO.setHospitalCode(userVO.getHospitalCode());
			
			EHRSection_ImageExamBO.SAVEIMAGE(patimageVO,getUserVO(_request));
			HelperMethods.populate(patimageVO, _fb);
/*			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			String JSONObject_SaveImageList=gson.toJson(_fb);
			
			//response.setContentType("application/text");
			
			response.getWriter().write(JSONObject_SaveImageList.toString());
			//System.out.println(JSONObject_SaveImageList);
			*/
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
			WebUTIL.setStatus(_request, objStatus);
		}
		
		
 		
 /*END Vasu*/
		
	}
	public static void AJAX_GETPREVIMGEXM(EHRSection_ImageExamFB _fb, HttpServletRequest _request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		UserVO userVO = null;
		HttpSession session = null;
		EHRSection_ImageExamBO bo=new EHRSection_ImageExamBO();
	
		EHRSection_ImageExamVO vo=new EHRSection_ImageExamVO();
		List<EHRSection_ImageExamVO> ImageVO=new ArrayList<EHRSection_ImageExamVO>();
		ResteasyClient client = new ResteasyClientBuilder().build();
	
		try
		{
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			
			userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
		
			_fb.setUserSeatId(userVO.getUserSeatId());
			
			HelperMethods.populate(vo, _fb);
			/*String urlPath="http://"+_request.getLocalAddr()+":"+_request.getLocalPort()+"/HISClinicalServices/rest/ImageExam/getPrevImgList";
			//System.out.println(urlPath);
			String JSONObject_SaveImageList=gson.toJson(vo);
			//System.out.println(JSONObject_SaveImageList);
			 ResteasyWebTarget target = client.target(urlPath);
			 //System.out.println("target::::"+target);*/
			 //Response response1 = target.request().acceptEncoding("gzip, deflate").post(Entity.entity(JSONObject_SaveImageList, "application/json"));
			 
			// String ws_status = response1.readEntity(String.class);
		        //System.out.println("Output from Server .... ");
		     //System.out.println(ws_status + "\n");/*
		       // String crNo=ws.getString(1);
		       //System.out.println(crNo);*/
		        //String[] getStatusFromOutput = ws_status.split(":");
		        //String status = getStatusFromOutput[2];
		       // status = (String)status.substring(0, (status.length()-1));
//		        int outputStatus = Integer.parseInt(status);
		       
		        //ImageVO= (List) mapper.readValue( ws_status,ImageVO );
			
			ImageVO=bo.AJAX_GETPREVIMGEXM(vo, userVO);
			
			//ImageVO.add(base64DataString);
			session.setAttribute(OpdConfig.IMAGE_EXAM_PREV_LIST,ImageVO);
			
			
			String JSONObject_ImageList=gson.toJson(ImageVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(JSONObject_ImageList.toString());
			EHRVOUtility.setImageExamDetailVO(_request, _fb.getPatCrNo(),ImageVO );
			

			//System.out.println(JSONObject_ImageList);
			
			//WebUTIL.setMapInSession(mpEssentials, _request);
			
			/*if(lstImages==null || lstImages.size()==0)
				throw new HisRecordNotFoundException("No Image Exists for Examination");*/
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	
	public static void Image(EHRSection_ImageExamFB _fb, HttpServletRequest _request, HttpServletResponse response)
	{
		HttpSession session = null;
		String ImageFile=(String)_request.getSession().getAttribute("Base64Image");
		String ImageFileName=(String)_request.getSession().getAttribute(OpdConfig.UPLOADED_IMAGE_NAME);
		List ImageFileSrc=new ArrayList();
		ImageFileSrc.add( ImageFile);
		ImageFileSrc.add(ImageFileName);
		
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			String JSONObject_ImageList=gson.toJson(ImageFileSrc);
			
			response.setContentType("application/Json");
			
			try {
				
				response.getWriter().write(JSONObject_ImageList.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//System.out.println(JSONObject_ImageList);
			
			//WebUTIL.setMapInSession(mpEssentials, _request);
			
			/*if(lstImages==null || lstImages.size()==0)
				throw new HisRecordNotFoundException("No Image Exists for Examination");*/
			//objStatus.add(Status.TRANSINPROCESS);
		}
		
	
	public static void AJAX_GETOTHERIMAGELIST(EHRSection_ImageExamFB _fb, HttpServletRequest _request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		UserVO userVO = null;
		HttpSession session = null;
		EHRSection_ImageExamBO bo=new EHRSection_ImageExamBO();
	
		EHRSection_ImageExamVO vo=new EHRSection_ImageExamVO();
		List ImageVO=new ArrayList();
		try
		{
			userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
			
			
			_fb.setUserSeatId(userVO.getUserSeatId());
			
			HelperMethods.populate(vo, _fb);
			
			ImageVO=bo.AJAX_GETOTHERIMAGELIST(vo, userVO);
			
			session.setAttribute(OpdConfig.IMAGE_EXAM_LIST,ImageVO);
			
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			String JSONObject_ImageList=gson.toJson(ImageVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(JSONObject_ImageList.toString());

			//System.out.println(JSONObject_ImageList);
			
			//WebUTIL.setMapInSession(mpEssentials, _request);
			
			/*if(lstImages==null || lstImages.size()==0)
				throw new HisRecordNotFoundException("No Image Exists for Examination");*/
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	
	public static void GetOtherImageSrc(EHRSection_ImageExamFB _fb, HttpServletRequest _request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		UserVO userVO = null;
		HttpSession session = null;
		EHRSection_ImageExamDelegate de=new EHRSection_ImageExamDelegate();
		EHRSection_ImageExamBO bo=new EHRSection_ImageExamBO();
		EHRSection_ImageExamVO vo=new EHRSection_ImageExamVO();
		List ImageVO=new ArrayList();
		try
		{
			userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
			
			
			_fb.setUserSeatId(userVO.getUserSeatId());
			
			HelperMethods.populate(vo, _fb);
			
			//de.GetOtherImageSrc(vo, _request,response);
			ImageVO=bo.GetOtherImageSrc(vo,userVO);
			
			//session.setAttribute(OpdConfig.IMAGE_EXAM_LIST,ImageVO);
			
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			String JSONObject_ImageList=gson.toJson(ImageVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(JSONObject_ImageList.toString());

			//System.out.println(JSONObject_ImageList);
			
			//WebUTIL.setMapInSession(mpEssentials, _request);
			
			/*if(lstImages==null || lstImages.size()==0)
				throw new HisRecordNotFoundException("No Image Exists for Examination");*/
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static void AJAX_REVOKEIMAGE(EHRSection_ImageExamFB _fb, HttpServletRequest _request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		UserVO userVO = null;
		HttpSession session = null;
		EHRSection_ImageExamDelegate de=new EHRSection_ImageExamDelegate();
		EHRSection_ImageExamBO bo=new EHRSection_ImageExamBO();
		EHRSection_ImageExamVO vo=new EHRSection_ImageExamVO();
		List ImageVO=new ArrayList();
		try
		{
			userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
			
			
			_fb.setUserSeatId(userVO.getUserSeatId());
			
			HelperMethods.populate(vo, _fb);
			
			//de.GetOtherImageSrc(vo, _request,response);
			bo.AJAX_REVOKEIMAGE(vo,userVO);
			
			//session.setAttribute(OpdConfig.IMAGE_EXAM_LIST,ImageVO);
			
			/*GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			String JSONObject_ImageList=gson.toJson(ImageVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(JSONObject_ImageList.toString());

			//System.out.println(JSONObject_ImageList);
			 */
			
			//WebUTIL.setMapInSession(mpEssentials, _request);
			
			/*if(lstImages==null || lstImages.size()==0)
				throw new HisRecordNotFoundException("No Image Exists for Examination");*/
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

}
