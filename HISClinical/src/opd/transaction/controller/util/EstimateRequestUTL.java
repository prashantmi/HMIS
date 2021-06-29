/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: Amit Garg 	
	 ## Module Name					: MRD
	 ## Process/Database Object Name:Estimate Certificate  Request
	 ## Purpose						:Certificate Issue Request
	 ## Date of Creation			: 19-Nov-2014
	
 	 */



package opd.transaction.controller.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import opd.OpdConfig;
import opd.transaction.controller.data.EstimateRequestDATA;
import opd.transaction.controller.fb.EstimateRequestFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.EstimateCertificateReqVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.UserVO;


public class EstimateRequestUTL extends ControllerUTIL{

	public static void getEstimateRequestEssentials(EstimateRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		String prevReq="";
	//	Map essentialMap = new HashMap();
		HttpSession session=request.getSession(); 
		EstimateCertificateReqVO estReqDtlVO=new EstimateCertificateReqVO();
		UserVO userVO=getUserVO(request);
		try
		{
			//fb.setLoginUserEmpId(getUserVO(request).getUserEmpID());
			fb.setLoginUserEmpId(userVO.getUserId());
			HelperMethods.populate(estReqDtlVO, fb);
			Map map=EstimateRequestDATA.getEstimateRequestEssentials(estReqDtlVO, getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			List<Entry> lstAdvisedBy = (List<Entry>) map.get(MrdConfig.ESTIMATE_REQUEST_ADVISEDBY_DETAIL);
				for(Entry entry : lstAdvisedBy)
					if (fb.getLoginUserEmpId().equals(entry.getValue()))
					{
						fb.setAdvisedBy(entry.getValue());
						break;
					}
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	public static void getTariffsList(EstimateRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
	//	Map essentialMap = new HashMap();
		HttpSession session=request.getSession(); 
		EstimateCertificateReqVO estReqDtlVO=new EstimateCertificateReqVO();
		try
		{
			//fb.setLoginUserEmpId(getUserVO(request).getUserEmpID());
			HelperMethods.populate(estReqDtlVO, fb);
			Map map=EstimateRequestDATA.getTariffsList(estReqDtlVO, getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static boolean saveEstimateCertificateReqDtl(EstimateRequestFB fb,HttpServletRequest request)
	{
		boolean flgSave = true;
		Status objStatus=new Status();
		EstimateCertificateReqVO estReqVO=new EstimateCertificateReqVO();
		try
		{	
			estReqVO.setTariffId(fb.getTarrifId());
			String[] preTarrifID = fb.getPreTarrifId().split(",");
			int len = preTarrifID.length;
			for(int i=0; i< len;i++)
			{
				if(fb.getTarrifId().equals(preTarrifID[i]))
					flgSave = false;
					
			}
			WebUTIL.populate(estReqVO, fb);
			if(flgSave)
			{
				EstimateRequestDATA.saveEstimateCertificateReqDtl(estReqVO,getUserVO(request));
				objStatus.add(Status.DONE,"","Record Added Successfully");
			}
			else
				objStatus.add(Status.DONE,"","Request For Selected Procedure Already Raised");
			
		}
		catch (HisDuplicateRecordException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flgSave;
	}
}
	

