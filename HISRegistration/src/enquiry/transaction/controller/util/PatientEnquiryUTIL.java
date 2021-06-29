package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
//import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import registration.config.RegistrationConfig;
import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.InPatientEnquiryDATA;
import enquiry.transaction.controller.data.PatientEnquiryDATA;
import enquiry.transaction.controller.fb.PatientEnquiryFB;

public class PatientEnquiryUTIL extends ControllerUTIL{
	
	public static void getEssentials(PatientEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			essentialMap=PatientEnquiryDATA.getPatientEnquiryEssentials(_userVO);
			WebUTIL.setMapInSession(essentialMap,_rq);
			_fb.setGnum_country_code(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			_fb.setGnum_state_code(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		
	}
	
	public static void searchPatient(PatientEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		UserVO userVO=getUserVO(_rq);
		Status objStatus=new Status();
		PatientEnquiryVO patEnquiryVO=new PatientEnquiryVO();
		AddressEnqiryVO addEnquiryVO=new AddressEnqiryVO();
		EpisodeEnquiryVO episodeEnquiryVO= new EpisodeEnquiryVO();
		CommonEnquiryVO[] commonEnquiryVOs=null;
		try{
			HelperMethods.populate(patEnquiryVO, _fb);
			HelperMethods.populate(addEnquiryVO, _fb);
			HelperMethods.populate(episodeEnquiryVO, _fb);
			commonEnquiryVOs=PatientEnquiryDATA.searchPatientDetails(patEnquiryVO,addEnquiryVO, episodeEnquiryVO,userVO);
			
			CommonEnquiryVO[] arrayCommonEnquiryVOs=new CommonEnquiryVO[commonEnquiryVOs.length];
			for(int i=0;i<commonEnquiryVOs.length;i++)
			{
				arrayCommonEnquiryVOs[i]=new CommonEnquiryVO();
				HelperMethods.populatetToNullOrEmpty(commonEnquiryVOs[i], arrayCommonEnquiryVOs[i]);
				HelperMethods.setNullToEmpty(commonEnquiryVOs[i]);
			}
			_fb.setFromDateHidden(_fb.getFromDate());
			_fb.setToDateHidden(_fb.getToDate());
			WebUTIL.setAttributeInSession(_rq,enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO, commonEnquiryVOs);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally
		{
			
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static void getOrderByCrNo(PatientEnquiryFB patientEnquiryFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		CommonEnquiryVO[] commonEnqVOs;
		try
		{
			commonEnqVOs = (CommonEnquiryVO[]) session.getAttribute(enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO);
			for (int i = 0; i < commonEnqVOs.length; i++)
			{
				commonEnqVOs[i].setOrderBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO);
			}
			if (patientEnquiryFB.getOrder().equals(Config.SORT_TYPE_ASC)) getInOrder(patientEnquiryFB, request);
			else if (patientEnquiryFB.getOrder().equals(Config.SORT_TYPE_DSC)) getInDscOrder(patientEnquiryFB, request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// *
	public static void getOrderByName(PatientEnquiryFB patientEnquiryFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		CommonEnquiryVO[] commonEnqVOs;
		try
		{
			commonEnqVOs = (CommonEnquiryVO[]) session.getAttribute(enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO);
			for (int i = 0; i < commonEnqVOs.length; i++)
			{
				commonEnqVOs[i].setOrderBy(Config.OPD_PATIENT_OREDRE_BY_NAME);
			}
			if (patientEnquiryFB.getOrder().equals(Config.SORT_TYPE_ASC)) getInOrder(patientEnquiryFB, request);
			else if (patientEnquiryFB.getOrder().equals(Config.SORT_TYPE_DSC)) getInDscOrder(patientEnquiryFB, request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	public static void getInOrder(PatientEnquiryFB patientEnquiryFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		CommonEnquiryVO[] commonEnqVOs;
		commonEnqVOs = (CommonEnquiryVO[]) session.getAttribute(enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO);
		List list = new ArrayList();
		for (int i = 0; i < commonEnqVOs.length; i++)
		{
			commonEnqVOs[i].setSortType(Config.SORT_TYPE_ASC);
			list.add(commonEnqVOs[i]);
		}
		Collections.sort(list);
		System.out.println(list);

		commonEnqVOs = (CommonEnquiryVO[]) list.toArray(commonEnqVOs);
		session.setAttribute(enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO, commonEnqVOs);
		System.out.println("");
	}

	// *
	public static void getInDscOrder(PatientEnquiryFB patientEnquiryFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		CommonEnquiryVO[] commonEnqVOs;
		commonEnqVOs = (CommonEnquiryVO[]) session.getAttribute(enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO);
		List list = new ArrayList();
		for (int i = 0; i < commonEnqVOs.length; i++)
		{
			commonEnqVOs[i].setSortType(Config.SORT_TYPE_DSC);
			list.add(commonEnqVOs[i]);
		}
		Collections.sort(list);
		System.out.println(list);

		commonEnqVOs = (CommonEnquiryVO[]) list.toArray(commonEnqVOs);
		session.setAttribute(enquiryConfig.PATIENT_ENQUIRY_COMMON_ENQUIRY_VO, commonEnqVOs);
		System.out.println("");
	}

}
