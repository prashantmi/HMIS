package mrd.transaction.controller.utl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.InsuranceClaimReceiveDATA;
import mrd.transaction.controller.fb.InsuranceClaimReceiveFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.UserVO;

public class InsuranceClaimReceiveUTL extends ControllerUTIL
{
	public static boolean getEssenForInsuranceClaimReceive(InsuranceClaimReceiveFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			setSysdate(request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			
			String sys=(String)session.getAttribute(Config.SYSDATE);

			String time=sys.split(" ")[1];

			fb.setSysHour(time.split(":")[0]);
			fb.setSysMinute(time.split(":")[1]);
						
			fb.setSysDate(sysadteString);
			
			if(fb.getCidNoFlag().equals(MrdConfig.DISABLE))
			{
				fb.setRecevingDate(sysadteString);
				fb.setRecevingTimeHr(time.split(":")[0]);
				fb.setRecevingTimeMin(time.split(":")[1]);
			}
			
			
			mpEssential=InsuranceClaimReceiveDATA.getEssenForInsuranceClaimReceive(getUserVO(request));
			
						
			WebUTIL.setMapInSession(mpEssential, request);
			
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		
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
	
	public static boolean getPatInformation(InsuranceClaimReceiveFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		List patInfoList=null;
		try
		{
			fb.setPatAdmNoArray(null);
			fb.setPatNameArray(null);
			fb.setPatGenderAndAgeArray(null);
			fb.setPatCrNoArray(null);
			fb.setPatDeptUnitArray(null);
			fb.setPatAdmDateArray(null);
			fb.setEpisodeCodeArray(null);
			fb.setEpisodeVisitNoArray(null);
			fb.setDischargeDateArray(null);
			
			patInfoList=InsuranceClaimReceiveDATA.getPatInfoList(fb.getStr_patCrNo(), fb.getStr_patAdmNo(), fb.getStr_firstName().toUpperCase(), fb.getStr_middleName().toUpperCase(), fb.getStr_lastName().toUpperCase(), getUserVO(request));
			
			session.setAttribute(MrdConfig.ALL_PAT_INFO_LIST, patInfoList);			
			objStatus.add(Status.INPROCESS);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		
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
	
	public static boolean saveInsuranceDetail(InsuranceClaimReceiveFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		InsuranceDetailVO insuranceDetailVO=new InsuranceDetailVO();
		try
		{
			UserVO userVO = getUserVO(request);
					
			HelperMethods.populate(insuranceDetailVO, _fb);
			insuranceDetailVO.setReceiveDateTime(_fb.getRecevingDate()+" "+_fb.getRecevingTimeHr()+":"+_fb.getRecevingTimeMin());
			insuranceDetailVO.setStatus(MrdConfig.INSURENCE_STATUS_REQUEST_RECEIVE);			
					
			InsuranceClaimReceiveDATA.saveInsuranceDetail(insuranceDetailVO, userVO);
			
			objStatus.add(Status.DONE,"Details Saved","");
		}
		
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
}
