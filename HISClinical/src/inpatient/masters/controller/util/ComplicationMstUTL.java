package inpatient.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ComplicationMasterVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.masters.controller.data.ComplicationMstDATA;
import inpatient.masters.controller.fb.ComplicationMasterFB;

import javax.servlet.http.HttpServletRequest;

public class ComplicationMstUTL extends ControllerUTIL
{

	// Saving the Data
	public static boolean saveCompMaster(ComplicationMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flg = true;
		ComplicationMasterVO complicationMasterVO = new ComplicationMasterVO();
		try
		{
			HelperMethods.populate(complicationMasterVO, fb);
			fb.setHmode(fb.getTempMode());
			ComplicationMstDATA.saveCompMaster(complicationMasterVO, getUserVO(request));
			objStatus.add(Status.DONE, "", "Record Added Successfully");
		}
		catch (HisDuplicateRecordException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			flg = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flg;
	}

	public static void getCompType(ComplicationMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			_fb.setComplicationTypeId(_fb.getControls()[0]);
			for (int i = 0; i < InpatientConfig.COMPLICATION_TYPE_ARR.length; i++)
			{
				String var = String.valueOf(i);

				if (_fb.getComplicationTypeId().equals(var)) _fb.setComplicationTypeName(InpatientConfig.COMPLICATION_TYPE_ARR[i]);

			}

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	public static boolean getDataForModify(ComplicationMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		ComplicationMasterVO _ComplicationMasterVO = new ComplicationMasterVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String sCompId = concatid[0];
			String sSlno = concatid[2];

			// putting the selected Record Primary Key into Vo
			fb.setComplicationId(sCompId);
			fb.setSlNo(sSlno);
			
			_ComplicationMasterVO.setSlNo(sSlno);
			_ComplicationMasterVO.setComplicationId(sCompId);
			_ComplicationMasterVO = ComplicationMstDATA.getDataForModify(_ComplicationMasterVO, userVO);
			System.out.println("Complication = "+_ComplicationMasterVO.getComplication()); 
			//System.out.println("Complication Type Name= "+_ComplicationMasterVO.getComplicationTypeName()); 
			HelperMethods.populate(fb, _ComplicationMasterVO);
			fb.setSlNo(sSlno);
			fb.setComplicationId(sCompId);

			getCompType(fb, _request);			

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static boolean saveModCompMaster(ComplicationMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			ComplicationMasterVO complicationMasterVO = new ComplicationMasterVO();

			HelperMethods.populate(complicationMasterVO, fb);
			fb.setHmode(fb.getTempMode());
			ComplicationMstDATA.saveModCompMaster(complicationMasterVO, userVO);

			objStatus.add(Status.DONE, "", "Record Modified Successfully");
		}
		catch (HisDuplicateRecordException e)
		{
			flag = false;
			objStatus.add(Status.DONE, "", e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flag = false;
			objStatus.add(Status.ERROR, "", "Complication Name already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;
	}

}
