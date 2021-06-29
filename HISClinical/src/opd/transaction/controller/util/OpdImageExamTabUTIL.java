package opd.transaction.controller.util;

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

import org.apache.commons.lang.ArrayUtils;

import java.util.LinkedHashMap;

public class OpdImageExamTabUTIL extends ControllerUTIL
{
	// Getting Essentials for Image Examination Tab
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(OpdImageExamTabFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		UserVO userVO = null;
		HttpSession session = null;

		Map mpEssentials = new HashMap();
		try
		{
			userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
			PatientDetailVO[] dailyPatientVOs = null;
			// Set For Bean Essentials
			// User Seat, Unit, Ward
			_fb.setUserSeatId(userVO.getUserSeatId());
			//_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			// Episode, Visit, Admission No
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}*/
			_fb.setPatCrNo(voDP.getPatCrNo());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
			if(voDP.getPatAdmNo()!=null)
				_fb.setAdmissionNo(voDP.getPatAdmNo());
			else
				_fb.setAdmissionNo("");

			
			// Set Image Examination Essentials
			OpdPatientImageDtlVO vo = new OpdPatientImageDtlVO();
			HelperMethods.populate(vo, _fb);
			mpEssentials = OpdImageExamTabDATA.getEssentials(_fb.getDepartmentUnitCode(), vo, userVO);
			
				// Getting Images List of The Unit
			List<ImageMasterVO> lstImages = (List<ImageMasterVO>) mpEssentials.get(OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT);
			
			//List<OpdPatientImageDtlVO> lstOldImages = null;
			//lstImages = OpdImageExamTabDATA.getOPDImagesListOfUnit(_fb.getDepartmentUnitCode(), userVO);
			//mpEssentials.put(OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT, lstImages);
				// Getting Previous Patient Exam Images
			//lstOldImages = OpdImageExamTabDATA.getOPDPatOldImagesList(vo, userVO);
			//mpEssentials.put(OpdConfig.OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT, lstOldImages);
				// Color-Description List
				// Getting Description List of Colors in String and Format Color1:Desc1#Color2:Desc2.....
			
			WebUTIL.setMapInSession(mpEssentials, _request);
			
			if(lstImages==null || lstImages.size()==0)
				throw new HisRecordNotFoundException("No Image Exists for Examination");
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
	public static void saveOpdPatientImage(OpdImageExamTabFB _fb, HttpServletRequest _request)
	{
		/*boolean flag = true;
		
		Status objStatus = new Status();
		HttpSession session = null;
		UserVO userVO = null;
		BufferedImage bfi = null;
		try
		{
			session = WebUTIL.getSession(_request);
			userVO = getUserVO(_request);
			
			setSysdate(_request);
			String entryDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			
			OpdPatientImageDtlVO vo = new OpdPatientImageDtlVO();
			HelperMethods.populate(vo, _fb);
			vo.setImageName(_fb.getTitle());
			vo.setRemarks(_fb.getRemark());
			vo.setEntryDate(entryDate);
			
			//--vo.setSerialNo(_fb.getPatSerialNo());
			//--vo.setImageFileName(_fb.getImageOutFileName());
			//--vo.setDirPath(_fb.getDirOutPath());
			
			 BufferedImage image = (BufferedImage) session.getAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM);
			//bfi=(BufferedImage)_request.getSession().getAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM);
			//OpdImageExamTabDATA.saveOpdPatientImage(vo, image, userVO);
			OpdImageExamTabDATA.saveOpdPatientImage(vo, image, userVO);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			flag=false;
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag=false;
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag=false;
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag=false;
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag=false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;*/
		
		
/* Start by Vasu on 21-AUG-2017 For save image editor image into database*/
		Status objStatus = new Status();
		BufferedImage bi=null;
		List byteArrayList=null;
		OpdPatientImageDtlVO patimageVO=null;
		String filepath="";
		String fileName="";
		UserVO userVO = null;
		HttpSession session = null;
		PatientDetailVO[] dailyPatientVOs = null;
		
		try{
			
			
			/*if(System.getProperty("os.name").startsWith("Linux")){
				filepath="/root" + Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX;	
			}
			else{
				//filepath=Config.PATIENT_IMAGE_FILE_STORAGE_PATH;
				 //filepath = "E://VASU/WORKSPACES_VASU/NIMS_23_AUG_2017/.metadata/.plugins/org.jboss.ide.eclipse.as.core/JBoss_6.x_Runtime_Server1501131007827/deploy/HIS.war/hisglobal/images/testSave/"+_fb.getImageCode()+"-"+_fb.getPatCrNo()+"-"+_fb.getEpisodeCode()+".png";
				
				 ServletContext context = _request.getServletContext();
				 filepath =  context.getRealPath("/hisglobal/ImgForImgEdtr");
				}*/
			session = WebUTIL.getSession(_request);
			userVO = getUserVO(_request);
			setSysdate(_request);
			String entryDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			HelperMethods.populate(_fb, voDP);
			_fb.setPatCrNo(voDP.getPatCrNo());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			bi=(BufferedImage)_request.getSession().getAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( bi, "png", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			fileName = _fb.getImageCode()+"-"+_fb.getPatCrNo()+"-"+_fb.getEpisodeCode()+".png";
			//fileName = _fb.getPatCrNo()+"-"+_fb.getEpisodeCode()+"-"+entryDate+".png";
			patimageVO=new OpdPatientImageDtlVO();
			HelperMethods.populate(patimageVO, _fb);
			patimageVO.setFileNo(fileName);
			patimageVO.setIsValid(Config.IS_VALID_ACTIVE);
			patimageVO.setDirPath(filepath);
			patimageVO.setImageFile(imageInByte);
			patimageVO.setEntryDate(entryDate);
			patimageVO.setRemarks(_fb.getRemark());
			patimageVO.setImageName(_fb.getTitle());
			patimageVO.setSeatId(userVO.getSeatId());
			patimageVO.setHospitalCode(userVO.getHospitalCode());
			
			OpdImageExamTabDATA.saveOpdPatientImage(patimageVO,getUserVO(_request));
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
		

	// Getting Image Editor Essentials for Old Image
	public static void getSetOldEditorEssentials(OpdImageExamTabFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		
		List<OpdPatientImageDtlVO> lstImages = new ArrayList<OpdPatientImageDtlVO>();
		try
		{
			session = WebUTIL.getSession(_request);
			setSysdate(_request);

			lstImages = (ArrayList) session.getAttribute(OpdConfig.OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT);
			OpdPatientImageDtlVO vo = null;
			for (OpdPatientImageDtlVO o : lstImages)
				if (_fb.getSelectedSNo().equals(o.getImageFileName()))
				{
					vo = o;
					break;					
				}			
			if (vo != null)
			{
				// Setting Color Pointers
				String colorDesc = (String) session.getAttribute(OpdConfig.OPD_ESSENTIAL_IMAGE_POINTERS_OF_DEPTUNIT);
				if (!colorDesc.equals("")) _fb.setColorDesc(colorDesc);
	
				// Setting Editor Essentials  FB
				Map mp = new HashMap();
				OpdImageEditorFB imgFB = new OpdImageEditorFB();
				imgFB.setInFileName(vo.getImageFileName());
				imgFB.setOutFileName(OpdConfig.OPD_IMAGE_EDITOR_FB_DEFAULT_OUT_FILE_NAME);//vo.getImageFileName());

				imgFB.setInFilePath(OpdConfig.OPD_EXAMINATION_PAT_IMAGE_PATH_VAR);
				imgFB.setOutFilePath(OpdConfig.OPD_EXAMINATION_PAT_IMAGE_PATH_VAR);

				imgFB.setTitle(vo.getImageName());
				imgFB.setColorDesc(_fb.getColorDesc());
				mp.put(OpdConfig.OPD_IMAGE_EDITOR_FB, imgFB);
				WebUTIL.setMapInSession(mp, _request);

				session.removeAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM);///----			
			}

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
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Modifying OPD Patient Image
	public static boolean modifyOpdPatientImage(OpdImageExamTabFB _fb, HttpServletRequest _request)
	{
		boolean flag = true;
		
		Status objStatus = new Status();
		HttpSession session = null;
		UserVO userVO = null;

		List<OpdPatientImageDtlVO> lstImages = new ArrayList<OpdPatientImageDtlVO>();
		try
		{
			session = WebUTIL.getSession(_request);
			userVO = getUserVO(_request);
			
			setSysdate(_request);
			String entryDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			
			lstImages = (ArrayList) session.getAttribute(OpdConfig.OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT);
			OpdPatientImageDtlVO selVO = null;
			for (OpdPatientImageDtlVO o : lstImages)
				if (_fb.getSelectedSNo().equals(o.getImageFileName()))
				{
					selVO = o;
					break;					
				}			

			OpdPatientImageDtlVO vo = new OpdPatientImageDtlVO();
			HelperMethods.populate(vo, selVO);
			vo.setEntryDate(entryDate);
			
			BufferedImage image = (BufferedImage) session.getAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM);			
			OpdImageExamTabDATA.modifyOpdPatientImage(vo, image, userVO);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
			flag=false;
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag=false;
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag=false;
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag=false;
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag=false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;
	}

	// Viewing the Image
	public static void setImageLog(OpdImageExamTabFB _fb, HttpServletRequest _request,HttpServletResponse response_p)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		
		List<OpdPatientImageDtlVO> lstImages = null;
		
		BufferedImage bi=null;
		InputStream inputStream = null;
		String File = null;
		List byteArray=new ArrayList();
		try
		{
			session = WebUTIL.getSession(_request);
			setSysdate(_request);

			lstImages = (ArrayList) session.getAttribute(OpdConfig.OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT);
			OpdPatientImageDtlVO vo = lstImages.get(Integer.parseInt(_fb.getSelectedSNo()));
			//String entryDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			
           //Added by VASU on 25-August-2017
			/*bi=(BufferedImage)_request.getSession().getAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( bi, "png", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			
			vo.setImageFile(imageInByte);*/

			//End VASU
			
			File = vo.getPatCrNo()+"-"+vo.getEpisodeCode()+"-"+vo.getEntryDate();
			//File=vo.getImageCode()+"-"+vo.getPatCrNo()+"-"+vo.getEpisodeCode();
			byte[] getDoc = OpdImageExamTabDATA.viewImageFromPostgres(vo);
			//byte[] getDoc=MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_PATIENT_IMAGE_UPLOAD).latestFetchFile(File);
			
			response_p.setContentType("image/jpg");
			OutputStream os = response_p.getOutputStream();
			inputStream = new ByteArrayInputStream(getDoc);
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}    
			/*vo.setImageFileName(File);
			
			Map mp = new HashMap();
			List<OpdPatientImageDtlVO> lstImageLog = new ArrayList<OpdPatientImageDtlVO>();
			lstImageLog.add(vo);
			_fb.setIsNextLogPresent(OpdConfig.NO);
			_fb.setSelectedLogIndex("0");
			
			mp.put(OpdConfig.OPD_IMAGE_EXAM_IMAGES_VIEW_LOG_LIST_OF_IMAGE_DTL_VO, lstImageLog);
			WebUTIL.setMapInSession(mp, _request);*/
			WebUTIL.setAttributeInSession(_request,Config.IMAGE_BYTE_ARRAY , byteArray);
			//WebUTIL.setAttributeInSession(_request,Config.IMAGE_BYTE , getDoc);

			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_request, objStatus);
		}
		
		
		//by VASU 
				/*Status objStatus = new Status();
				List patientImageDtlVOList=null;
				OpdPatientImageDtlVO patimageVO=null;
				//byte array[]=null;
				List byteArray=new ArrayList();
				try
				{
					
					for(int i=0;i<patientImageDtlVOList.size();i++){
						patimageVO=new OpdPatientImageDtlVO(); 
						patimageVO=(OpdPatientImageDtlVO)patientImageDtlVOList.get(i);
						//String filepath=patimageVO.getFilePath().replace("\\","/")+"/" + patimageVO.getFileNo();
						//InputStream is = FTPFileTransfer.retrieveFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patimageVO.getFileNo(), patimageVO.getPatCrNo());
						byte[] getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(patimageVO.getFileNo());
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
					_fb.setPatCrNo(((OpdPatientImageDtlVO)patientImageDtlVOList.get(0)).getPatCrNo());
					//_fb.setSerialNo(((OpdPatientImageDtlVO)patientImageDtlVOList.get(0)).getSerialNo());
					_fb.setMaxSerialNo(((OpdPatientImageDtlVO)patientImageDtlVOList.get(patientImageDtlVOList.size()-1)).getSerialNo());
					WebUTIL.setAttributeInSession(_request,Config.IMAGE_BYTE_ARRAY , byteArray);
					WebUTIL.setAttributeInSession(_request,OpdConfig.OPD_IMAGE_EXAM_IMAGES_VIEW_LOG_LIST_OF_IMAGE_DTL_VO , patientImageDtlVOList);
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
					WebUTIL.setStatus(_request, objStatus);
				}
				*/
				//End VASU
	}

	// Viewing the Previous Log Image
	public static void setPreviousImageLog(OpdImageExamTabFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		UserVO userVO = null;
		HttpSession session = null;

		List<OpdPatientImageDtlVO> lstImageLog = null;
		try
		{
			userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			setSysdate(_request);

			lstImageLog = (ArrayList) session.getAttribute(OpdConfig.OPD_IMAGE_EXAM_IMAGES_VIEW_LOG_LIST_OF_IMAGE_DTL_VO);
			int logIndex = Integer.parseInt(_fb.getSelectedLogIndex());
			OpdPatientImageDtlVO vo = lstImageLog.get(logIndex), prevVO=null;
			
			if(lstImageLog.size()>=logIndex+2)	prevVO = lstImageLog.get(logIndex+1);
			
			if(prevVO==null && !vo.getIsLogPresent().equals(OpdConfig.NO))
			{
				prevVO = OpdImageExamTabDATA.getImageLogDetail(vo.getImageFileName(), userVO);
				if(prevVO!=null)
					lstImageLog.add(prevVO);
			}
			
			_fb.setIsNextLogPresent(OpdConfig.YES);
			_fb.setSelectedLogIndex(Integer.toString(logIndex+1));
			
			Map mp = new HashMap();
			mp.put(OpdConfig.OPD_IMAGE_EXAM_IMAGES_VIEW_LOG_LIST_OF_IMAGE_DTL_VO, lstImageLog);
			WebUTIL.setMapInSession(mp, _request);

			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_request, objStatus);
		}
		
		
	}

	// Viewing the Next Log Image
	public static void setNextImageLog(OpdImageExamTabFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			setSysdate(_request);

			int logIndex = Integer.parseInt(_fb.getSelectedLogIndex());
			
			if((logIndex-1)==0)	_fb.setIsNextLogPresent(OpdConfig.NO);	
			else _fb.setIsNextLogPresent(OpdConfig.YES);
			_fb.setSelectedLogIndex(Integer.toString(logIndex-1));
			
			objStatus.add(Status.NEW);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	public static void getImage(OpdImageEditorFB fb,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// String cMode; 
		// String inFileName;
		String flag1="0";
		 String outFileName = request.getParameter("name");
		// String inFilePath;
		 String outFilePath = request.getParameter("path");
		//outFileName=request.getParameter("outFile");
		// outFileName=fb.getOutFileName();
		//String key=request.getParameter("outFilePath");
		 String key=outFilePath;
		outFilePath=getPathFromConfig(key);
		if(outFileName!=null)
		{
			
				try {
					saveImage(fb,request, response,outFileName,outFilePath);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag1="1";
			 
		}
		try {
			writeResponse(response, flag1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static String getPathFromConfig(String key)
	{
		String path="";
		
		char ch=(char) (key.charAt(0));
		ch-=32;
		key=key.substring(1,key.length());
		key=String.valueOf(ch)+key;
		HisImageEditorConfig cofigObject=new HisImageEditorConfig();
		Method method[]= cofigObject.getClass().getMethods();
		try
		{
			for(int j=0;j<method.length;j++)
				if(method[j].getName().equals("get"+key))
					path=(String)method[j].invoke(null,null);
		}
		catch(Exception e)
		{
			System.out.println("Error : Getting path From Config of Key "+key+":: "+e);
		}
		return path;
	}
	
	private static void saveImage(OpdImageEditorFB fb, HttpServletRequest request, HttpServletResponse response, String outFileName2, String outFilePath2) throws ServletException, IOException
	{
		
		 String outFileName = outFileName2;
		
		 String outFilePath = outFilePath2;
		ObjectInputStream inputFromApplet = null;
		//BufferedImage image=null;
		PrintWriter out = null;
		String dataUrl = request.getParameter("dataurl");
		 /*File file = new File(outFileName2);
		 
	        FileInputStream fis = new FileInputStream(file);
	        //create FileInputStream which obtains input bytes from a file in a file system
	        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
	 
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = DatatypeConverter.parseBase64Binary(dataUrl.substring(dataUrl.indexOf(",") + 1));
	       // byte[] buf = new byte[1024];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                //Writes to this byte array output stream
	                bos.write(buf, 0, readNum); 
	                System.out.println("read " + readNum + " bytes,");
	            }
	        } catch (IOException ex) {
	           // Logger.getLogger(ConvertImage.class.getName()).log(Level.SEVERE, null, ex);
	        }
	 
	        byte[] bytes = bos.toByteArray();
	 
	        
	         * 2. How to convert byte array back to an image file?
	         
	 
	        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	       // Iterator readers = (Iterator) ImageIO.getImageReadersByFormatName("jpg");
	 
	        //ImageIO is a class containing static methods for locating ImageReaders
	        //and ImageWriters, and performing simple encoding and decoding. 
	        
	        ImageReader rdr=null;  
	        try{
	       rdr = ImageIO.getImageReadersByFormatName("PNG").next();
	        }
	        catch(Exception e){
	        	System.out.println(e.getMessage());
	        	e.printStackTrace();
	        }
	        ImageInputStream imageInput = ImageIO.createImageInputStream(bis);
	        rdr.setInput(imageInput);
	        BufferedImage bufferedImage = rdr.read(0);
	        
	 */
	        /*Iterator<?> readers = ImageIO.getImageReadersByFormatName("png");
	        
	        
	        ImageReader reader = (ImageReader) readers.next();
	        Object source = bis; 
	        ImageInputStream iis = ImageIO.createImageInputStream(source); 
	        reader.setInput(iis, true);
	        ImageReadParam param = reader.getDefaultReadParam();
	 
	        Image image = reader.read(0, param); 	
	        //got an image file
*/	 
	    //    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
	        //bufferedImage is the RenderedImage to be written
	 
	     /*   Graphics2D g2 = bufferedImage.createGraphics();
	        g2.drawImage(image, null, null);*/
	 
	       /* File imageFile = new File("C:\\newrose2.jpg");
	        ImageIO.write(bufferedImage, "jpg", imageFile);
	 
	        System.out.println(imageFile.getPath());*/
		
		
	}
	
	@SuppressWarnings("unused")
	private static BufferedImage createImageFromBytes(byte[] imageData) {
	    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
	    try {
	        return ImageIO.read(bais);
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();

		}
//		File in = new File(dataUrl);
//		URL url = in.toURI().toURL();
//		//URL url = new URL(dataUrl);
//		BufferedImage inputImage = ImageIO.read(url);
		
		
		//byte[] imagedata = DatatypeConverter.parseBase64Binary(dataUrl.substring(dataUrl.indexOf(",") + 1));
		
		//InputStream In = new ByteArrayInputStream(imagedata);
		//BufferedImage inputImage  = createImageFromBytes(imagedata);
		
		//BufferedImage inputImage = ImageIO.read(In);  
		
		
		/*InputStream in = new FileInputStream(new File(outFileName));
		BufferedImage inputImage = ImageIO.read(in);*/
		
			/*BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(imagedata));
			ImageIO.write(inputImage, "png", new File("img.png"));*/
		
//		final int dataStartIndex = dataUrl.indexOf(",") + 1;
//		final String data = dataUrl.substring(dataStartIndex);
//		byte[] imageData = Base64.decode(data);
		
		
		
//		String encodingPrefix = "base64,";
//		int contentStartIndex = dataUrl.indexOf(encodingPrefix) + encodingPrefix.length();
//		byte[] imageData = Base64.decode(dataUrl.substring(contentStartIndex));
		
		//BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(imageData));

		// If the picture is null, then throw an unsupported image exception.
		/*if (inputImage == null) {
		    try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		// request.getSession().setAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM, inputImage);
	}
}
