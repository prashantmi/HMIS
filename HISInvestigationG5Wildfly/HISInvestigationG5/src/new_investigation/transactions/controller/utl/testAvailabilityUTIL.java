package new_investigation.transactions.controller.utl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.testAvailabilityDATA;
import new_investigation.transactions.controller.fb.testAvailabilityFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
import new_investigation.vo.testAvailabilityVO;

public class testAvailabilityUTIL extends ControllerUTIL
{
	public static void getEssential(testAvailabilityFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		testAvailabilityVO invtestAvailabilityVO=new testAvailabilityVO();
		 	 
	try{	
		Map mp=new HashMap(); 
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		mp=testAvailabilityDATA.LabComboForMachineResultEntry(invtestAvailabilityVO, userVO);
		WebUTIL.setMapInSession(mp, request);
	
		//HelperMethods.populate(fb, invtestAvailabilityVO);
	}catch(HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
	}
	catch(HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR,"","Error");
	}			
	
	
	
	}
	
	
	
	public static boolean setPatientEssentials(testAvailabilityFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		testAvailabilityVO invtestAvailabilityVO = new testAvailabilityVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
					
		
			invtestAvailabilityVO.setLabCode(fb.getLabCode());
			invtestAvailabilityVO.setTestStatus(fb.getTestStatus());
			invtestAvailabilityVO.setSearchTest(fb.getSearchTest());
					
			mp=testAvailabilityDATA.getPatientMachineResultEntry(invtestAvailabilityVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			
			//HelperMethods.populate(fb, invtestAvailabilityVO);
			  
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	


	// Save Logic
		public static void saveMachineResultEntry(testAvailabilityFB _fb,HttpServletRequest _request)
		{
			Status objStatus=new Status();	
			HttpSession session=_request.getSession();
			Map mp=new HashMap();
			int SizeMapOne=0;
			HashMap<String,String> fetchMap=new HashMap();
			try
			{
				Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
				UserVO _userVO=getUserVO(_request);

				List<testAvailabilityVO> invtestAvailabilityVO=new ArrayList<testAvailabilityVO>();

				
				
				String[] selectedLabTestCodeArray=_fb.getChkTest();
							
				int labRowCount=selectedLabTestCodeArray.length;
				
			
							
							
				for(int i=0;i<labRowCount;i++)
				{
							
					String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
					
				
					String labCode=testCodeArray[0];
					String testCode=testCodeArray[1];
					
				
					testAvailabilityVO voResultEntry=new testAvailabilityVO();
					
				
					voResultEntry.setTestCode(testCode);
					voResultEntry.setLabCode(labCode);
					voResultEntry.setRemarks(_fb.getRemarks());
					voResultEntry.setIsAvailable(_fb.getIsAvailable());
					voResultEntry.setFromDate(_fb.getFromDate());
					voResultEntry.setToDate(_fb.getToDate());
					
					
					invtestAvailabilityVO.add(voResultEntry);
				
		}
			// end for loop
				
				
				System.out.println("size of the list is "+invtestAvailabilityVO.size());
		mp=testAvailabilityDATA.saveMachineResultEntry(invtestAvailabilityVO, _userVO, _request);
			
				
		
		StringBuilder str = new StringBuilder();	
		 objStatus.add(Status.DONE, str.toString(),
				 "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
									+ "Updation Successfull" + "</font>");
				       


					objStatus.add(Status.NEW, "", "");
					objStatus.add(Status.TRANSINPROCESS, "", "");
				   
			}
			catch (HisRecordNotFoundException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				System.out.println(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				System.out.println(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
				System.out.println(e.getMessage());
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, "", "Error");
				System.out.println(e.getMessage());
			}
			finally
			{
				WebUTIL.setStatus(_request, objStatus);
			}
		}
	
		 
		
		
		
		
	
		
		
		
}