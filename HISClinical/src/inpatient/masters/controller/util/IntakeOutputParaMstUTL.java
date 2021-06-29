package inpatient.masters.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import inpatient.InpatientConfig;
import inpatient.masters.controller.data.AnomalyTypeMstDATA;
import inpatient.masters.controller.data.IntakeOutputParaMstDATA;
import inpatient.masters.controller.fb.AnomalyTypeMasterFB;
import inpatient.masters.controller.fb.IntakeOutputParaMasterFB;
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
import hisglobal.vo.AbortionTypeMasterVO;
import hisglobal.vo.AnomalyTypeMasterVO;
import hisglobal.vo.IntakeOutputParaMasterVO;
import hisglobal.vo.UserVO;

public class IntakeOutputParaMstUTL extends ControllerUTIL
{
	
	
	//Saving the Data
	public static boolean saveIntakeOutputParaMaster(IntakeOutputParaMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		IntakeOutputParaMasterVO inoutparaMasterVO=new IntakeOutputParaMasterVO();
		try
		{
			HelperMethods.populate(inoutparaMasterVO, fb);
			fb.setHmode(fb.getTempMode());
			exist=false;
			IntakeOutputParaMstDATA.saveIntakeOutputParaMaster(inoutparaMasterVO,getUserVO(request));
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch (HisDuplicateRecordException e)
		{
			e.printStackTrace();
			fb.setHmode(fb.getTempMode());
			exist=true;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			exist=true;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
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
		return exist;
	}
	

	
	public static boolean saveModAnomalyTypeMaster(AnomalyTypeMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			AnomalyTypeMasterVO _AnomalyTypeMasterVO = new AnomalyTypeMasterVO();

			HelperMethods.populate(_AnomalyTypeMasterVO , fb);
			fb.setHmode(fb.getTempMode());
			flag=AnomalyTypeMstDATA.saveModAnomalyTypeMaster(_AnomalyTypeMasterVO, userVO);
		
			if(flag){
				objStatus.add(Status.DONE,"","Record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Anomaly Type already exists");
				
			}
			
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Anomaly Type already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}

	public static void getDataForModify(IntakeOutputParaMasterFB fb,
			HttpServletRequest _request) {
		// TODO Auto-generated method stub
		Status objStatus = new Status();
		IntakeOutputParaMasterVO inoutparaMasterVO = new IntakeOutputParaMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sParaID = concatid[0];
			String shospitalCode = concatid[1];
			String sSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setInTakeOutParaId(sParaID);
			fb.setSlNo(sSlno);

			inoutparaMasterVO.setSlNo(sSlno);
			inoutparaMasterVO.setInTakeOutParaId(sParaID);
		//	_request.getSession().setAttribute(InpatientConfig.Para_id, inoutparaMasterVO);
			inoutparaMasterVO = IntakeOutputParaMstDATA.getDataForModify(inoutparaMasterVO, userVO);
		    HelperMethods.populate(fb, inoutparaMasterVO);
		    fb.setInTakeOutParaId(sParaID);
			fb.setSlNo(sSlno);
			//setEssentials(fb, _request);
		//	_request.getSession().setAttribute(InpatientConfig.Para_id, inoutparaMasterVO);
			//WebUTIL.setAttributeInSession(_request, InpatientConfig.Para_id, inoutparaMasterVO);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
		
	}



	public static boolean saveModInOutParaMaster(
			IntakeOutputParaMasterFB fb, HttpServletRequest _request) {
		// TODO Auto-generated method stub
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			//HttpSession ses = HttpSession
		//	IntakeOutputParaMasterVO inoutparaMasterVO = (IntakeOutputParaMasterVO) _request.getSession().getAttribute(InpatientConfig.Para_id);
			IntakeOutputParaMasterVO inoutparaMasterVO = new IntakeOutputParaMasterVO();
			String id = fb.getInTakeOutParaId();
			HelperMethods.populate(inoutparaMasterVO , fb);
			fb.setHmode(fb.getTempMode());
			flag=IntakeOutputParaMstDATA.saveModInOutParaMaster(inoutparaMasterVO, userVO);
		
			if(flag){
				objStatus.add(Status.DONE,"","Record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Anomaly Type already exists");
				
			}
			
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Anomaly Type already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
	
}
