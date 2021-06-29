package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DoctorCallAcknowledgeDATA;
import inpatient.transaction.controller.data.DoctorCallBookDATA;
import inpatient.transaction.controller.fb.DoctorCallBookFB;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoctorCallAcknowledgeUTL extends ControllerUTIL

{
	
	public static void getCallAckowledgeDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session = _request.getSession();
		DoctorCallBookVO[] doctorCallAcknowledgeDtlVOArray = null;
		DoctorCallBookVO doctorCallBookVO = new DoctorCallBookVO();
		try
		{
			setSysdate(_request);
			UserVO userVO = getUserVO(_request);
			
			doctorCallAcknowledgeDtlVOArray = DoctorCallAcknowledgeDATA.getCallAcknowledgeDetails(doctorCallBookVO, userVO);
			String status="";
			for(int i=0;i<doctorCallAcknowledgeDtlVOArray.length;i++)
			{
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus()==null && doctorCallAcknowledgeDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_MANUAL) )
				{
					//doctorCallAcknowledgeDtlVOArray[i].setStatus(InpatientConfig.CALL_PENDING);
					status=InpatientConfig.CALL_PENDING;
				}
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus()==null && doctorCallAcknowledgeDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_AUTOMATIC))
				{
					doctorCallAcknowledgeDtlVOArray[i].setCallPriority(InpatientConfig.CALL_PRIORITY_NAME_LOW);
					status=InpatientConfig.CALL_AUTOMATIC;
				}
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus()!=null)
					//doctorCallAcknowledgeDtlVOArray[i].setStatus(InpatientConfig.CALL_AUTOMATIC);
					status=InpatientConfig.CALL_ACKNOLWEDGED;
				
				doctorCallAcknowledgeDtlVOArray[i].setStatus(status);
			}
			//int len=doctorCallAcknowledgeDtlVOArray.length;
			
		/*	for(int i=0,j=len-1,k=0;i<doctorCallAcknowledgeDtlVOArray.length;i++)
			{
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_PENDING))
				{
					 
					doctorCallAcknowledgeDtlVOArray[k]=doctorCallAcknowledgeDtlVOArray[i];
					k=k+1;
					
				}
				
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_AUTOMATIC) || doctorCallAcknowledgeDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED))
				{
					doctorCallAcknowledgeDtlVOArray[j]=doctorCallAcknowledgeDtlVOArray[i];
					j=j-1;
					
				}
			}*/
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY, doctorCallAcknowledgeDtlVOArray);
				
			if (doctorCallAcknowledgeDtlVOArray != null)
			{
				objStatus.add(Status.NEW);
				objStatus.add(Status.INPROCESS);
			}

			else
				objStatus.add(Status.INPROCESS);
				objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW);
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

	public static void getAllCalls(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session = _request.getSession();
		DoctorCallBookVO[] doctorCallAcknowledgeDtlVOArray = null;
		DoctorCallBookVO doctorCallBookVO = new DoctorCallBookVO();
		try
		{
			setSysdate(_request);
			UserVO userVO = getUserVO(_request);
			
			doctorCallAcknowledgeDtlVOArray = DoctorCallAcknowledgeDATA.getAllCalls(doctorCallBookVO, userVO);
			String status="";
			for(int i=0;i<doctorCallAcknowledgeDtlVOArray.length;i++)
			{
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus()==null && doctorCallAcknowledgeDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_MANUAL) )
				{
					//doctorCallAcknowledgeDtlVOArray[i].setStatus(InpatientConfig.CALL_PENDING);
					status=InpatientConfig.CALL_PENDING;
				}
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus()==null && doctorCallAcknowledgeDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_AUTOMATIC))
				{
					//doctorCallAcknowledgeDtlVOArray[i].setStatus(InpatientConfig.CALL_ACKNOLWEDGED);
					status=InpatientConfig.CALL_AUTOMATIC;
				}
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus()!=null)
					//doctorCallAcknowledgeDtlVOArray[i].setStatus(InpatientConfig.CALL_AUTOMATIC);
					status=InpatientConfig.CALL_ACKNOLWEDGED;
				
				doctorCallAcknowledgeDtlVOArray[i].setStatus(status);
			}
			//int len=doctorCallAcknowledgeDtlVOArray.length;
			
			/*for(int i=0,j=len-1,k=0;i<doctorCallAcknowledgeDtlVOArray.length;i++)
			{
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_PENDING))
				{
					 
					doctorCallAcknowledgeDtlVOArray[k]=doctorCallAcknowledgeDtlVOArray[i];
					k=k+1;
					
				}
				
				if(doctorCallAcknowledgeDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_AUTOMATIC) || doctorCallAcknowledgeDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED))
				{
					doctorCallAcknowledgeDtlVOArray[j]=doctorCallAcknowledgeDtlVOArray[i];
					j=j-1;
					
				}
			}*/
			
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY, doctorCallAcknowledgeDtlVOArray);
				
			if (doctorCallAcknowledgeDtlVOArray != null)
			{
				objStatus.add(Status.NEW);
				objStatus.add(Status.LIST);
				
			}

			else
				objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW);
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

	
	
	public static boolean saveDoctorCallAcknowledgeDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session=_request.getSession();
		boolean hasFlag = true;
		
		try
		{
			UserVO userVO = getUserVO(_request);
			//String wardCode=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);
			DoctorCallBookVO _callBookVO = new DoctorCallBookVO();
			
			
			_callBookVO.setDoctorRemarks(_fb.getDoctorRemarks());
			_callBookVO.setEmpNo(_fb.getEmpNo());
			_callBookVO.setCallNo(_fb.getCallNo());
			_callBookVO.setCallDate(_fb.getCallDateFormat());
			
			hasFlag=DoctorCallAcknowledgeDATA.saveDoctorCallAcknowledgeDetails(_callBookVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Call Acknowledged  Successfully", "");
			 }else
			 {		System.out.println("in false");
				 objStatus.add(Status.NEW, "", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static void getSpecificCallDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		DoctorCallBookVO doctorCallBookVO=new DoctorCallBookVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			int index=Integer.parseInt(_fb.getSelectedPatCrNo());
			setSysdate(_request);
			DoctorCallBookVO[] doctorCallBookVOArray = (DoctorCallBookVO[]) session.getAttribute(InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY);
			if(doctorCallBookVOArray!=null)
			{
					doctorCallBookVO.setCallNo(doctorCallBookVOArray[index].getCallNo());
					doctorCallBookVO.setCallDate(doctorCallBookVOArray[index].getCallDate());
					doctorCallBookVO.setEmpNo(doctorCallBookVOArray[index].getEmpNo());
					doctorCallBookVO.setCallDateFormat(doctorCallBookVOArray[index].getCallDateFormat());
			}
			
			doctorCallBookVO=DoctorCallBookDATA.getCallDetails(doctorCallBookVO, userVO);
			_fb.setConsultantName(doctorCallBookVO.getConsultantName());
			_fb.setEmpNo(doctorCallBookVO.getEmpNo());
			_fb.setCallRemarks(doctorCallBookVO.getCallRemarks());
			_fb.setCallPriority(doctorCallBookVO.getCallPriority());
			_fb.setDocCallByPeonStatus(doctorCallBookVO.getDocCallByPeonStatus());
			_fb.setDocCallByPeonRemarks(doctorCallBookVO.getDocCallByPeonRemarks());
			_fb.setDocCallByPhoneStatus(doctorCallBookVO.getDocCallByPhoneStatus());
			_fb.setDocCallByPhoneRemarks(doctorCallBookVO.getDocCallByPhoneRemarks());
			_fb.setCallNo(doctorCallBookVO.getCallNo());
			_fb.setDoctorRemarks(doctorCallBookVO.getDoctorRemarks());
			_fb.setStatus(doctorCallBookVO.getStatus());
			_fb.setPatAdmnNo(doctorCallBookVO.getPatAdmnNo());
			_fb.setPatCrNo(doctorCallBookVO.getPatCrNo());
			_fb.setRaiseByEmpNo(doctorCallBookVO.getRaiseByEmpNo());
			
			
			if(_fb.getStatus()==null)
				_fb.setStatus(InpatientConfig.CALL_PENDING);
			else
				_fb.setStatus(InpatientConfig.CALL_ACKNOLWEDGED);
				
			
			if(_fb.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_LOW))
				_fb.setCallPriorityName(InpatientConfig.CALL_PRIORITY_NAME_LOW);
			if(_fb.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_MEDIUM))
				_fb.setCallPriorityName(InpatientConfig.CALL_PRIORITY_NAME_MEDIUM);
			if(_fb.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_HIGH))
				_fb.setCallPriorityName(InpatientConfig.CALL_PRIORITY_NAME_HIGH);
			
			objStatus.add(Status.DONE);
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
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	
	public static void getCallRemarks(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		DoctorCallBookVO doctorCallBookVO=new DoctorCallBookVO();
		try
		{
			//UserVO userVO = getUserVO(_request);
			int index=Integer.parseInt(_fb.getIndex());
			
			setSysdate(_request);
			DoctorCallBookVO[] doctorCallBookVOArray = (DoctorCallBookVO[]) session.getAttribute(InpatientConfig.CALL_ACKNOWLEDGE_VO_ARRAY);
		
			doctorCallBookVO.setDoctorRemarks(doctorCallBookVOArray[index].getDoctorRemarks());
			doctorCallBookVO.setCallRemarks(doctorCallBookVOArray[index].getCallRemarks());
			_fb.setDoctorRemarks(doctorCallBookVO.getDoctorRemarks());
			_fb.setCallRemarks(doctorCallBookVO.getCallRemarks());
			
			objStatus.add(Status.LIST);
			objStatus.add(Status.NEW);
			objStatus.add(Status.RECORDFOUND);
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
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	public static void getCallRemarksNNotes(DoctorCallBookFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			List lst=DoctorCallAcknowledgeDATA.getCallRemarksNNotes(fb.getProcessId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.PROGRESS_NOTES_LIST , lst);
			if(lst.size()>0)
				objStatus.add(Status.NEW, "", "");
			else
				objStatus.add(Status.NEW, "", "No Acknowledgment Remarks Found");
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
		
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}	


}
