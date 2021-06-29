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

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class EHRSection_ImageExamDelegate extends ControllerUTIL
{
	public static void AJAX_GETPREVIMGEXM(EHRSection_ImageExamVO vo, HttpServletRequest _request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		//UserVO userVO = null;
		HttpSession session = null;
		EHRSection_ImageExamBO bo=new EHRSection_ImageExamBO();
	
		//EHRSection_ImageExamVO vo=new EHRSection_ImageExamVO();
	//	List ImageVO=new ArrayList();
		ResteasyClient client = new ResteasyClientBuilder().build();
	
		try
		{
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			
			//userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
		
			//_fb.setUserSeatId(userVO.getUserSeatId());
			
			//HelperMethods.populate(vo, _fb);
			String urlPath="http://"+_request.getLocalAddr()+":"+_request.getLocalPort()+"/HISClinicalServices/rest/ImageExam/getPrevImgList";
			//System.out.println(urlPath);
			String JSONObject_SaveImageList=gson.toJson(vo);
			//System.out.println(JSONObject_SaveImageList);
			 ResteasyWebTarget target = client.target(urlPath);
			 //System.out.println("target::::"+target);
			 Response response1 = target.request().acceptEncoding("gzip, deflate").post(Entity.entity(JSONObject_SaveImageList, "application/json"));
			 
			 String ws_status = response1.readEntity(String.class);
		        //System.out.println("Output from Server .... ");
		        //System.out.println(ws_status + "\n");
		        /*
		        String crNo=ws.getString(1);
		        //System.out.println(crNo);
		        */
		        String[] getStatusFromOutput = ws_status.split(":");
		        String status = getStatusFromOutput[2];
		        status = (String)status.substring(0, (status.length()-1));
//		        int outputStatus = Integer.parseInt(status);
		       
		        //ImageVO= (List) mapper.readValue( ws_status,ImageVO );
			
			//ImageVO=bo.AJAX_GETPREVIMGEXM(vo, userVO);
			
			//ImageVO.add(base64DataString);
			//session.setAttribute(OpdConfig.IMAGE_EXAM_PREV_LIST,ImageVO);
			
			
			//String JSONObject_ImageList=gson.toJson(ImageVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(ws_status.toString());

			//System.out.println(ws_status);
			
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
	
	public static void GetOtherImageSrc(EHRSection_ImageExamVO vo, HttpServletRequest _request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		//UserVO userVO = null;
		HttpSession session = null;
		//EHRSection_ImageExamBO bo=new EHRSection_ImageExamBO();
	
		//EHRSection_ImageExamVO vo=new EHRSection_ImageExamVO();
	//	List ImageVO=new ArrayList();
		ResteasyClient client = new ResteasyClientBuilder().build();
	
		try
		{
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			
			
			//userVO = getUserVO(_request);
			session = WebUTIL.getSession(_request);
			
			setSysdate(_request);
		
			//_fb.setUserSeatId(userVO.getUserSeatId());
			
			//HelperMethods.populate(vo, _fb);
			String urlPath="http://"+_request.getLocalAddr()+":"+_request.getLocalPort()+"/HISClinicalServices/rest/ImageExam/getOtherImgList";
			//System.out.println(urlPath);
			String JSONObject_SaveImageList=gson.toJson(vo);
			//System.out.println(JSONObject_SaveImageList);
			 ResteasyWebTarget target = client.target(urlPath);
			 //System.out.println("target::::"+target);
			 Response response1 = target.request().acceptEncoding("gzip, deflate").post(Entity.entity(JSONObject_SaveImageList, "application/json"));
			 
			 String ws_status = response1.readEntity(String.class);
		        //System.out.println("Output from Server .... ");
		        //System.out.println(ws_status + "\n");
			 /*
		        String crNo=ws.getString(1);
		        //System.out.println(crNo);
		        */
		        String[] getStatusFromOutput = ws_status.split(":");
		        String status = getStatusFromOutput[2];
		        status = (String)status.substring(0, (status.length()-1));
//		        int outputStatus = Integer.parseInt(status);
		       
		        //ImageVO= (List) mapper.readValue( ws_status,ImageVO );
			
			//ImageVO=bo.AJAX_GETPREVIMGEXM(vo, userVO);
			
			//ImageVO.add(base64DataString);
			//session.setAttribute(OpdConfig.IMAGE_EXAM_PREV_LIST,ImageVO);
			
			
			//String JSONObject_ImageList=gson.toJson(ImageVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(ws_status.toString());

			//System.out.println(ws_status);
			
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