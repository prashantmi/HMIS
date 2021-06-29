
package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisNotAnIPDcaseException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.InpatientBulletinDetailDATA;
import inpatient.transaction.controller.fb.InpatientBulletinDetailFB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InpatientBulletinDetailUTL extends ControllerUTIL
{
	/** Getting the List of Admitted Patient List on the basis of unitCode & wardCode
	 * @param fb
	 * @param request
	 */
	public static void getAdmittedPatientList(InpatientBulletinDetailFB fb,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		PatientDetailVO patDtlVO=new PatientDetailVO();
		Status objStatus = new Status();
		PatientBulletinDetailVO[] patientBulletinVO=null;
		//List patientStatusList=new ArrayList();
		Map essentialMap=new HashMap();
		PatientBulletinDetailVO latestPatientBulletinVO=new PatientBulletinDetailVO();
		
		try
		{
			UserVO userVO= getUserVO(request);
			
			InpatientDetailUTL.getInpatientDetailByCrNo(fb, request);
			patDtlVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			//System.out.println("Patient status="+patDtlVO.getPatStatusCode());
			//if(!patDtlVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_ADMITTED))
			if(patDtlVO!=null)
			{
				if(!patDtlVO.getIsIpd().equals(InpatientConfig.DESK_IPD_TILE)) // change By Pragya 2011-09-23
				{
					throw new HisNotAnIPDcaseException("Not An IPD Patient");
				}
				else
				{
					essentialMap=InpatientBulletinDetailDATA.getBulletinDetails(patDtlVO, userVO);
				}
			
				WebUTIL.setMapInSession(essentialMap, request);
				patientBulletinVO=(PatientBulletinDetailVO[])essentialMap.get(InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO);
			//patientStatusList=(ArrayList)essentialMap.get(InpatientConfig.INPATIENT_STATUS_LIST);
			}
		if(patientBulletinVO!=null)
		{
			latestPatientBulletinVO=patientBulletinVO[patientBulletinVO.length-1];
			if(fb.getTransactionMode().equals(InpatientConfig.BULLETIN_BOARD))
			{
				fb.setPatStatusName(latestPatientBulletinVO.getPatStatusName());
			}
		}	
		    System.out.println("pat status code"+latestPatientBulletinVO.getPatStatusCode());
		
			fb.setPatRemarks(latestPatientBulletinVO.getPatRemarks());
			fb.setPatStatusCode(latestPatientBulletinVO.getPatStatusCode());
			
			if(fb.getTransactionMode().equals(InpatientConfig.BULLETIN_BOARD))
			{
				
			}
			
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisNotAnIPDcaseException e)
		{
			
			objStatus.add(Status.NEW,"",e.getMessage() );
			fb.setPatCrNo("");
			e.printStackTrace();
		}
		catch (HisRecordNotFoundException e)
		{
			//objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.TRANSINPROCESS,e.getMessage(),"");
			essentialMap=e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, request);
			fb.setPatRemarks("");
			fb.setPatStatusCode("");			
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	public static void saveAndUpdatePatientBulletinDetails(InpatientBulletinDetailFB _fb,HttpServletRequest _request){
		Status objStatus=new Status();
	
		PatientBulletinDetailVO[] patientBulletinVO=null;
		PatientBulletinDetailVO _oldPatientBulletinVO=new PatientBulletinDetailVO();
		PatientBulletinDetailVO _newPatientBulletinVO=new PatientBulletinDetailVO();
		PatientDetailVO _patDtlVO=new PatientDetailVO();
		try
		{
		patientBulletinVO=(PatientBulletinDetailVO[])WebUTIL.getSession(_request).getAttribute(InpatientConfig.INPATIENT_BULLETIN_DETAIL_VO);
		
		if(patientBulletinVO!=null)
			_oldPatientBulletinVO=patientBulletinVO[patientBulletinVO.length-1];	
		
		_patDtlVO=(PatientDetailVO)WebUTIL.getSession(_request).getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			
		if(_oldPatientBulletinVO.getBulletinSerialNo()==null || _oldPatientBulletinVO.getBulletinSerialNo().equals(""))
			
			{
			HelperMethods.populate(_oldPatientBulletinVO,_patDtlVO);
			}
			
		if(_fb.getPatRevoke()==null)
			_fb.setPatRevoke("0");
			
			HelperMethods.populate(_newPatientBulletinVO,_oldPatientBulletinVO);
			
			_newPatientBulletinVO.setPatStatusCode(_fb.getPatStatusCode());	
			_newPatientBulletinVO.setPatRemarks(_fb.getPatRemarks());
			
			InpatientBulletinDetailDATA.saveAndUpdatePatientBulletinDetails(_oldPatientBulletinVO,_newPatientBulletinVO,getUserVO(_request),_fb.getPatRevoke());		
			objStatus.add(Status.NEW,"Patinet Bulletin Added Successfully","");	
			
		}
		catch(HisDataAccessException e){			
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){			
			objStatus.add(Status.ERROR_AE,"Exception","");
			}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in AddDeptMasterUTIL","");
		}
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}

 }	
	
	public static void getRemarks(InpatientBulletinDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			List lst=InpatientBulletinDetailDATA.getRemarks(fb.getProcessId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.PROGRESS_NOTES_LIST , lst);
			if(lst.size()>0)
				objStatus.add(Status.NEW, "", "");
			else
				objStatus.add(Status.NEW, "", "No Bulletin Remarks Found");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}	

 


	public static void getAllAdmittedPatientListBulletin(InpatientBulletinDetailFB fb, HttpServletRequest request) {
		
		Status objStatus = new Status();
		PatientBulletinDetailVO[] patientBulletinVO=null;
		
		try
		{
			UserVO userVO= getUserVO(request);
			patientBulletinVO=InpatientBulletinDetailDATA.getAllAdmittedPatientListBulletin(userVO);
			if(patientBulletinVO!=null)
				WebUTIL.setAttributeInSession(request, InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY, patientBulletinVO);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			//objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			System.out.println(e.getMessage());
			
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	
	public static void getOrderByName(InpatientBulletinDetailFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientBulletinDetailVO[] patientBulletinVO;
		try
		{
			
			patientBulletinVO = (PatientBulletinDetailVO[]) session.getAttribute(InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY);
			for (int i = 0; i < patientBulletinVO.length; i++)
			{
				patientBulletinVO[i].setOrderBy(InpatientConfig.ORDER_BY_NAME);
			}
			if (fb.getOrder().equals(Config.SORT_TYPE_ASC)) getInOrder(fb, request);
			else if (fb.getOrder().equals(Config.SORT_TYPE_DSC)) getInDscOrder(fb, request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.LIST);
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
	
	public static void getOrderByWardType(InpatientBulletinDetailFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientBulletinDetailVO[] patientBulletinVO;
		try
		{
			
			patientBulletinVO = (PatientBulletinDetailVO[]) session.getAttribute(InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY);
			for (int i = 0; i < patientBulletinVO.length; i++)
			{
				patientBulletinVO[i].setOrderBy(InpatientConfig.ORDER_BY_WARD_TYPE);
			}
			if (fb.getOrder().equals(Config.SORT_TYPE_ASC)) getInOrder(fb, request);
			else if (fb.getOrder().equals(Config.SORT_TYPE_DSC)) getInDscOrder(fb, request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.LIST);
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
	
	
	public static void getInOrder(InpatientBulletinDetailFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		PatientBulletinDetailVO[] patientBulletinVO;
		patientBulletinVO = (PatientBulletinDetailVO[]) session.getAttribute(InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY);
		List list = new ArrayList();
		for (int i = 0; i < patientBulletinVO.length; i++)
		{
			patientBulletinVO[i].setSortType(Config.SORT_TYPE_ASC);
			list.add(patientBulletinVO[i]);
		}
		Collections.sort(list);
		System.out.println(list);

		patientBulletinVO = (PatientBulletinDetailVO[]) list.toArray(patientBulletinVO);
		session.setAttribute(InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY, patientBulletinVO);
		System.out.println("");
	}
	
	public static void getInDscOrder(InpatientBulletinDetailFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		PatientBulletinDetailVO[] patientBulletinVO;
		patientBulletinVO = (PatientBulletinDetailVO[]) session.getAttribute(InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY);
		List list = new ArrayList();
		for (int i = 0; i < patientBulletinVO.length; i++)
		{
			patientBulletinVO[i].setSortType(Config.SORT_TYPE_DSC);
			list.add(patientBulletinVO[i]);
		}
		Collections.sort(list);
		System.out.println(list);
	
		patientBulletinVO = (PatientBulletinDetailVO[]) list.toArray(patientBulletinVO);
		session.setAttribute(InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY, patientBulletinVO);
		System.out.println("");
	}
/*
	public static void getPatientBulletinDetail(InpatientBulletinDetailFB fb,HttpServletRequest request) {
		Status objStatus = new Status();
		PatientBulletinDetailVO[] patientBulletinVO=null;
		HttpSession session=request.getSession();
		String patCrNo="";
		try
		{
			UserVO userVO= getUserVO(request);
			patientBulletinVO=(PatientBulletinDetailVO[])session.getAttribute(InpatientConfig.IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY);
			if(patientBulletinVO!=null)
			//	patCrNo=patientBulletinVO[Integer.parseInt(fb.getSelectedIndex())].getPatCrNo();
			fb.setPatCrNo(patCrNo);
			InpatientBulletinDetailUTL.getAdmittedPatientList(fb, request);	
		}
		catch (HisRecordNotFoundException e)
		{
			//objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		
	}	
*/
}
