package mrd.transaction.controller.utl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;

import opd.OpdConfig;
import mrd.transaction.controller.data.EstimateRequestDATA;
import mrd.transaction.controller.fb.EstimateRequestFB;

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
import mrd.vo.EstimateCertificateReqVO;


public class EstimateRequestUTL extends ControllerUTIL{

	public static void getEstimateRequestEssentials(EstimateRequestFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession(); 
		EstimateCertificateReqVO estReqDtlVO=new EstimateCertificateReqVO();
		try
		{
			
			//------- DEsk List
			// VO
			
			
			HelperMethods.populate(estReqDtlVO, fb);
			Map map=EstimateRequestDATA.getEstimateRequestEssentials(estReqDtlVO, getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			/*List prevestimateCertificateReqtDtl=(List)session.getAttribute(MrdConfig.PREVIOUS_ESTIMATE_CERTIFIATE_DTL);			
			

			EstimateCertificateReqVO preEstimateVo=(EstimateCertificateReqVO)prevestimateCertificateReqtDtl;
			for(int i=0;i<prevestimateCertificateReqtDtl.size();i++)
			{
				fb.setTariffName(preEstimateVo.getTariffName());
				
			}*/
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
			WebUTIL.populate(estReqVO, fb);
			EstimateRequestDATA.saveEstimateCertificateReqDtl(estReqVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
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
	

