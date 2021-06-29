/*Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:22-Nov-2014 

*/

package mrd.transaction.controller.utl;

import inpatient.InpatientConfig;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;



//import com.sun.xml.xsom.impl.scd.Iterators.Map;
import java.util.Map;

import mrd.MrdConfig;
import mrd.transaction.controller.data.EstimateCertificateIssueDATA;
import mrd.transaction.controller.fb.EstimateCertificateIssuetFB;
import mrd.vo.EstimateCertificateIssueVO;
import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientDetailVO;

public class EstimateCertificateIssueUTL extends ControllerUTIL{

	public static boolean getEstimateCertificateReqDtl(EstimateCertificateIssuetFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			setSysdate(request);
			
			Map map=EstimateCertificateIssueDATA.getEstimateCertificateReqDtl(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
	
	
	
	
	
	
	
	public static boolean getEstimateCertificateIssuePatDtl(EstimateCertificateIssuetFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = request.getSession();
		
		try
		{
			EstimateCertificateIssueVO estimateCertificateIssueVO=new EstimateCertificateIssueVO(); 
			setSysdate(request);
			String patDtl[]=fb.getSelectRecord().split("@");
			fb.setPatCrNo(patDtl[0]);
			fb.setPatFullName(patDtl[1]);
			fb.setCertificateId(patDtl[2]);
			//fb.setTariffName(patDtl[2]);
			fb.setRequestedDate(patDtl[3]);
			fb.setAdvisedBy(patDtl[4]);
			fb.setEpisodeCode(patDtl[5]);
			fb.setEpisodeVisitNo(patDtl[6]);
			fb.setTarrifId(patDtl[7]);
			
			
			HelperMethods.populate(estimateCertificateIssueVO, fb);
			//PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			//PatientDetailVO	ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_CERTIFICATE_PATIENT_DETAILS, estimateCertificateIssueVO);
			Map map=EstimateCertificateIssueDATA.getEstimateCertificateIssueDtl(estimateCertificateIssueVO,getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			//objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
	public static boolean saveEstimateCertificateIssueDtl(EstimateCertificateIssuetFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = request.getSession();
		
		try
		{
			EstimateCertificateIssueVO estimateCertificateIssueVO=new EstimateCertificateIssueVO(); 
			HelperMethods.populate(estimateCertificateIssueVO, fb);
			setSysdate(request);
			EstimateCertificateIssueDATA.saveEstimateCertificateIssueDtl(estimateCertificateIssueVO,getUserVO(request));
			//objStatus.add(Status.NEW);\
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
	public static boolean generateEstimateCertificate(EstimateCertificateIssuetFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		HospitalMstVO hospVO = getHospitalVO(request);
		try
		{
			EstimateCertificateIssueVO estimateCertificateIssueVO=  (EstimateCertificateIssueVO) session.getAttribute(MrdConfig.SELECTED_CERTIFICATE_PATIENT_DETAILS); 
			setSysdate(request);
			
			//fb.setDocName(userVO.getUsrName());
			//fb.setDocDesig(userVO.getDesignation());
			String patDtl[]=estimateCertificateIssueVO.getSelectRecord().split("@");
			fb.setPatCrNo(patDtl[0]);
			fb.setPatFullName(patDtl[1]);
			fb.setCertificateId(patDtl[2]);
			//fb.setTariffName(patDtl[2]);
			fb.setRequestedDate(patDtl[3]);
			fb.setAdvisedBy(patDtl[4]);
			fb.setEpisodeCode(patDtl[5]);
			fb.setEpisodeVisitNo(patDtl[6]);
			fb.setTarrifId(patDtl[7]);
			fb.setDocDesig(patDtl[8]);
			fb.setDocDept(patDtl[9]);
			fb.setEpisodeStartDate(patDtl[10]);
			if(patDtl[11].trim().equals("null"))
			{
				fb.setAdmNo(" ");
			}
			else
			{
				fb.setAdmNo(patDtl[11]);
			}
			
			if(patDtl[12].trim().equals("null"))
			{
				fb.setAdmDate(" ");
			}
			else
			{
				fb.setAdmDate(patDtl[12]);
			}
			if(patDtl[13].trim().equals("null"))
			{
				fb.setDisDate(" ");
			}
			else
			{
				fb.setDisDate(patDtl[13]);
			}
			fb.setHospName(hospVO.getHospitalName());
			fb.setHospAdd(hospVO.getAddress1());
			fb.setHospAdd1(hospVO.getCity() + "," + hospVO.getDistrictName() + "," + hospVO.getPinCode() + "," + hospVO.getStateName());
			System.out.println("Hospaddress:-"+fb.getHospAdd1());
			
			String patRelativeDtl[]=fb.getStrHiddenPatDtl().replace("^","#").split("#");
			String PatrelDtl[]=patRelativeDtl[1].replace("/", "#").split("#");
			System.out.println("pat father name::"+PatrelDtl[0]);
			fb.setPatFatherName(PatrelDtl[0]);
			HelperMethods.populate(estimateCertificateIssueVO, fb);
			Map map=EstimateCertificateIssueDATA.generateEstimateCertificate(estimateCertificateIssueVO,getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			//objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
}
