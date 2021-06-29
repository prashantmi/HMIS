package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.DoctorWardRoundDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DoctorWardRoundDetailDATA;
import inpatient.transaction.controller.fb.DoctorWardRoundDetailFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoctorWardRoundDetailUTL  extends ControllerUTIL
{

	
	public static boolean getDeptUnitList(DoctorWardRoundDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session=request.getSession();
		
		try
		{
			List lstUnit=DoctorWardRoundDetailDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.DEPT_UNIT_LIST ,lstUnit);
			if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_YES))
			{
				String unitCode=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
				fb.setUnitCode(unitCode);
			}
			else
				fb.setUnitCode(InpatientConfig.ALL_DEPARTMENTS);
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
	
	public static void getRoundByEssentials(DoctorWardRoundDetailFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		List lstEmployee =null;
		try
		{
			String wardCode= (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);
			fb.setWardCode(wardCode);
		    setSysdate(request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysdateString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			String sysTime=WebUTIL.getCustomisedSysDate(sysdate,"HH:mm");
			fb.setSysDate(sysdateString);
			String systemTime[]=sysTime.split(":");
			
			fb.setSysHr(systemTime[0]);
			fb.setSysMin(systemTime[1]);

			String unitCode=fb.getUnitCode().split("#")[0];/* Nilesh Gupta 9-Nov-2017*/
			
			if(unitCode.equals(InpatientConfig.ALL_DEPARTMENTS))
			{
				lstEmployee=DoctorWardRoundDetailDATA.getAllConsultantDetails(unitCode, getUserVO(request));
			}
			else
			{
				// Employee List should be on the basis of unitCode
				lstEmployee = DoctorWardRoundDetailDATA.getRoundByEssentials(unitCode, getUserVO(request));
			}
			WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE, lstEmployee);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Data Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
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
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void saveDoctorWardRoundDtl(DoctorWardRoundDetailFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		//List drugReactionVOLst=new ArrayList();
		List callBookList=new ArrayList();
		
		try
		{	
			setSysdate(_request);
			//Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			//String sysdateString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");			
			
			callBookList=(List)session.getAttribute(InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_LIST);
			
			DoctorWardRoundDtlVO _doctorWardRoundDtlVO=new DoctorWardRoundDtlVO();
			
			_doctorWardRoundDtlVO.setRoundDate(_fb.getRoundDate());
			_doctorWardRoundDtlVO.setWardCode(_fb.getWardCode());
			_doctorWardRoundDtlVO.setRoundType(_fb.getRoundType());
			_doctorWardRoundDtlVO.setRoundInTime(_fb.getRoundDate()+" "+_fb.getRoundInHr()+":"+_fb.getRoundInMin());
			if(_fb.getNextRoundDate()!=null && !_fb.getNextRoundDate().equals(""))
			{
			_doctorWardRoundDtlVO.setNextRoundDate(_fb.getNextRoundDate()+""+_fb.getNextRoundInHr()+":"+_fb.getNextRoundInMin());
			}
			_doctorWardRoundDtlVO.setEmpNo(_fb.getRoundBy());
			_doctorWardRoundDtlVO.setEnterBy(InpatientConfig.ENTER_BY_NURSE);
				
			DoctorWardRoundDetailDATA.saveDoctorWardRoundDtl(_doctorWardRoundDtlVO,callBookList,getUserVO(_request));	
			// Nursing Desk Data Set
			PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(_request, _fb.getDeskMenuId());

			objStatus.add(Status.DONE,"","Doctor Ward Round Detail Saved Successfully");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void getOnCallDetails(DoctorWardRoundDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session = _request.getSession();
		DoctorCallBookVO[] doctorCallBookDtlVOArray = null;
		DoctorCallBookVO doctorCallBookVO = new DoctorCallBookVO();
		try
		{
			setSysdate(_request);
			UserVO userVO = getUserVO(_request);
			
			
			doctorCallBookVO.setPatCrNo(_fb.getPatCrNo());
			doctorCallBookVO.setWardCode(_fb.getWardCode());
			doctorCallBookVO.setEmpNo(_fb.getRoundBy());
			
			doctorCallBookDtlVOArray = DoctorWardRoundDetailDATA.getOnCallDetails(doctorCallBookVO, userVO);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_VOARRAY, doctorCallBookDtlVOArray);
			
			if(doctorCallBookDtlVOArray==null)
		    {
		    	_fb.setOnCallDetailFlag(InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_FLAG_NO);
		    	objStatus.add(Status.DONE,"","No Call List Found");
		    }
		    else
		    {
		    	_fb.setOnCallDetailFlag(InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_FLAG_YES);
		    }
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST);
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
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static void saveCallDetails(DoctorWardRoundDetailFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		DoctorCallBookVO[] doctorCallBookDtlVOArray = null;
		List callBookList=new ArrayList();
		try
		{	
			setSysdate(_request);
			//Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		//	String sysdateString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");			
			
			doctorCallBookDtlVOArray=(DoctorCallBookVO[])session.getAttribute(InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_VOARRAY);
			
			//for(int i=0;i<doctorCallBookDtlVOArray.length;i++)
			//{
				if(_fb.getSelectedPatCrNo()!=null)
				{
					int countCheck=_fb.getSelectedPatCrNo().length;
					//String[] chk=_fb.getSelectedPatCrNo();
					for(int j=0;j<countCheck;j++)
			    	{		
			    		//int k=Integer.parseInt(chk[j]);
			    		
			    		callBookList.add(doctorCallBookDtlVOArray[j]);
			    		
			    	}
				}
				//String index=_fb.getSelectedPatCrNo();
		//	}
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_LIST, callBookList);
			
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
}
