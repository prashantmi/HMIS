package dutyroster.masters.controller.utl;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig; 
import dutyroster.masters.controller.data.RosterPrintMstDATA; 
import dutyroster.masters.controller.fb.RosterPrintMstFB; 

public class RosterPrintMstUTL extends ControllerUTIL
{

	
// Getting the RosterCategory from the table HDRT_ROSTER_CAT_MST  


	public static boolean getRosterCategory(HttpServletRequest _request,RosterPrintMstFB _fb)
	{
		Status objStatus = new Status();
		
		List rosterCategory=new ArrayList();
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
						
			
			rosterCategory=RosterPrintMstDATA.getRosterCategory(_userVO);
			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, rosterCategory);
			
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
		return true;
	}	
	
	


	// Getting the roster Type on the Basis of Roster Category

		
		public static boolean getRosterTypeBasedOnRosterCategory(HttpServletRequest _request,RosterPrintMstFB _fb)
		{
			Status objStatus = new Status();
			
			List rosterTypeList=new ArrayList();
			
			try
			{
				UserVO _userVO = getUserVO(_request);
				setSysdate(_request);
	 
						
				rosterTypeList=RosterPrintMstDATA.getRosterTypeBasedOnRosterCategory(_fb.getRosterCategory(),_userVO);
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, rosterTypeList);
				
						
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
								
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
								
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "");
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
			return true;
		}	
		


// Getting the roster Type on the Basis of Roster Category

		
		public static boolean getRosterPrintDetailsBasedOnRosterType(HttpServletRequest _request,RosterPrintMstFB _fb)
		{
			Status objStatus = new Status();
			
			Map instMap=null;
			Map copyToMap=null;
		
			
			try
			{
				UserVO _userVO = getUserVO(_request);
				setSysdate(_request);
				DutyRosterPrintPropertiesVO[] _rosterPrintVO=null;
				String[] arrayOfRosterDetails=_fb.getRosterType().replace("^", "@").split("@");
				Map rosterPrintMap=new LinkedHashMap();
				Map displayOrderMap=new LinkedHashMap();
				
				
				
				//First we are getting already saved details of instructions
				//roster by & copy to from roster print properties vo
				
				_rosterPrintVO=RosterPrintMstDATA.getRosterPrintDetailsBasedOnRosterType(arrayOfRosterDetails[0],_userVO);
			
				
				//Now we are creating a Map from the array of VO's in case of modification is needed 
				
			if(_rosterPrintVO!=null)
				rosterPrintMap=RosterPrintMstUTL.getMapFromArrayOfVO(_rosterPrintVO,_fb);			
				
				
				//Now we are creating a Map from the array of VO's in case of modification of Display order 
				//is needed
			if(_rosterPrintVO!=null)	
				displayOrderMap=RosterPrintMstUTL.getDisplayOrderMapFromArrayOfVO(_rosterPrintVO, _fb);
				
				
				instMap=(Map)rosterPrintMap.get("1");
				copyToMap=(Map)rosterPrintMap.get("3");
				
			if(instMap!=null)
				_fb.setNoOfInstructionRow(Integer.toString(instMap.size()));
			
			if(copyToMap!=null)	
				_fb.setNoOfCopyToRow(Integer.toString(copyToMap.size()));
				
				
				WebUTIL.setAttributeInSession(_request,DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS, rosterPrintMap);
					
				WebUTIL.setAttributeInSession(_request,DutyRosterConfig.MAP_FOR_ORDER_ROSTER_PRINT_DETAILS, displayOrderMap);
				
				
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
							
				WebUTIL.setAttributeInSession(_request,DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS, new LinkedHashMap());
				WebUTIL.setAttributeInSession(_request,DutyRosterConfig.MAP_FOR_ORDER_ROSTER_PRINT_DETAILS, new LinkedHashMap());
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			}
			catch (HisDataAccessException e)
			{
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "");
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
			return true;
		}	
		
			
			
	// On Add Page saving Values into Database
	public static boolean saveAndModifyRosterPrintMstInfo(RosterPrintMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
	
		try
		{
			UserVO userVO = getUserVO(_request);
			DutyRosterPrintPropertiesVO[] _rosterPrintVO=RosterPrintMstUTL.getVOsToBeSaved(_fb, _request);
			Map rosterPrintMap=new LinkedHashMap();
			
			rosterPrintMap=(Map)_request.getSession().getAttribute(DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS);
			
			
		if(rosterPrintMap.size()==0)
			RosterPrintMstDATA.saveRosterPrintMstInfo(_rosterPrintVO, userVO);
		else
			{
			RosterPrintMstDATA.saveAndModifyRosterPrintMstInfo(_rosterPrintVO, userVO);
			
			}
		
			objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "","Data Access Error");
			hasFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
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

	
	// On Add Page saving Values into Database
	public static boolean changeDisplayOrder(RosterPrintMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
	
		try
		{
			UserVO userVO = getUserVO(_request);
			DutyRosterPrintPropertiesVO[] _rosterPrintVO=RosterPrintMstUTL.getVOsToBeChangedDisplayOrder(_fb, _request);
			Map rosterPrintMap=new LinkedHashMap();
			
			
			RosterPrintMstDATA.changeDisplayOrder(_rosterPrintVO, userVO);
		
			objStatus.add(Status.INPROCESS, "Display Order Changed Successfully", "");
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "","Data Access Error");
			hasFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
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
	
	// Making the Map from the array of Vos 
	public static Map getMapFromArrayOfVO(DutyRosterPrintPropertiesVO[] _rosterPrintVO,RosterPrintMstFB _fb)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		Map rosterPrintMap=new LinkedHashMap();
	
		try
		{
				for(int i=0 ; i < _rosterPrintVO.length ; i++)
				{
					Map rosterOrderMap=new LinkedHashMap();
					
			
		
				if(_rosterPrintVO[i].getPropertyType().equals("1"))
						_fb.setAlignInstruction(_rosterPrintVO[i].getAlign());
			
				
				if(_rosterPrintVO[i].getPropertyType().equals("2"))
					_fb.setAlignRosterBy(_rosterPrintVO[i].getAlign());
		
				
				if(_rosterPrintVO[i].getPropertyType().equals("3"))
					_fb.setAlignCopyTo(_rosterPrintVO[i].getAlign());
		
					
					if(rosterPrintMap.containsKey(_rosterPrintVO[i].getPropertyType()))
					{
						rosterOrderMap=(LinkedHashMap)rosterPrintMap.get(_rosterPrintVO[i].getPropertyType());
						rosterOrderMap.put(_rosterPrintVO[i].getDisplayOrder(), _rosterPrintVO[i]);
						rosterPrintMap.put(_rosterPrintVO[i].getPropertyType(), rosterOrderMap);
						
						
						
						
					}else
					{
											
							rosterOrderMap.put(_rosterPrintVO[i].getDisplayOrder(), _rosterPrintVO[i]);
							rosterPrintMap.put(_rosterPrintVO[i].getPropertyType(), rosterOrderMap);
							
												
					}	
					
					
					
					
					
					
				}
			
			
		}
		
		catch (HisException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		
		return rosterPrintMap;
	}
	
	
	// Making the Map from the array of Vos  in order to change the Display Order 
	public static Map getDisplayOrderMapFromArrayOfVO(DutyRosterPrintPropertiesVO[] _rosterPrintVO,RosterPrintMstFB _fb)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		Map rosterPrintMap=new LinkedHashMap();
	
		try
		{
				for(int i=0 ; i < _rosterPrintVO.length ; i++)
				{
					Map rosterOrderMap=new LinkedHashMap();
					
			
					

					if(rosterPrintMap.containsKey(_rosterPrintVO[i].getPropertyType()))
					{
						rosterOrderMap=(LinkedHashMap)rosterPrintMap.get(_rosterPrintVO[i].getPropertyType());
						
						String key=_rosterPrintVO[i].getDisplayOrder()+"@"+_rosterPrintVO[i].getSerialNo();	
						
						rosterOrderMap.put(key, _rosterPrintVO[i].getDisplayValue());
						rosterPrintMap.put(_rosterPrintVO[i].getPropertyType(), rosterOrderMap);
								
						
					}else
					{
						String key=_rosterPrintVO[i].getDisplayOrder()+"@"+_rosterPrintVO[i].getSerialNo();	
						
						rosterOrderMap.put(key, _rosterPrintVO[i].getDisplayValue());
						rosterPrintMap.put(_rosterPrintVO[i].getPropertyType(), rosterOrderMap);
							
												
					}	
					
					
				}
			
			
		}
		
		catch (HisException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		
		return rosterPrintMap;
	}
	
	
	
	
	// On Add Page saving Values into Database
	public static DutyRosterPrintPropertiesVO[] getVOsToBeSaved(RosterPrintMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		DutyRosterPrintPropertiesVO[] _rosterPrintVO=null;
		
		try
		{
			String[] arrayOfRosterDetails=_fb.getRosterType().replace("^", "@").split("@");
			
			String[] arrayOfInstructions=null;
			String[] arrayOfRosterBy=null;
			String[] arrayOfCopyTo=null;
			
			int length1=0;
			int length2=0;
			
			
			if(!_fb.getConcatedValueOfInstruction().contains("NA"))
						arrayOfInstructions=_fb.getConcatedValueOfInstruction().split("#");
					 
						arrayOfRosterBy=_fb.getConcatedValueOfRosterBy().split("@");
			
						
			if(!_fb.getConcatedValueOfCopyTo().contains("NA"))			 
						arrayOfCopyTo=_fb.getConcatedValueOfCopyTo().split("#");
						
			
			if(arrayOfInstructions!=null)
				length1=arrayOfInstructions.length;
			
			
			if(arrayOfCopyTo!=null)
				length2=arrayOfCopyTo.length;
			
			
			//Calculating the size of the VO
			int size=length1+length2+1;
			
			
///index of the VO 
			int index=0;
			
			
//making an array of VO's			
			_rosterPrintVO=new DutyRosterPrintPropertiesVO[size];
			
			
			
if(arrayOfInstructions!=null)
{		
			for(int i=0 ; i < arrayOfInstructions.length ; i++)
			{
				String[] arrayOfVoDetails=arrayOfInstructions[i].split("@");
				
				_rosterPrintVO[index]=new DutyRosterPrintPropertiesVO();
				
				_rosterPrintVO[index].setRosterType(arrayOfRosterDetails[0]);
				_rosterPrintVO[index].setAlign(_fb.getAlignInstruction());
				_rosterPrintVO[index].setPropertyType(arrayOfVoDetails[0]);
				_rosterPrintVO[index].setDisplayOrder(arrayOfVoDetails[1]);
				
		if(!arrayOfVoDetails[2].equals("NA"))
				_rosterPrintVO[index].setDisplayValue(arrayOfVoDetails[2]);
				
				                                                           
				index++;
			}
}


if(length1!=0)
	_fb.setNoOfInstructionRow(Integer.toString(length1));
else
	_fb.setNoOfInstructionRow("1");


if(arrayOfCopyTo!=null)
{	

			for(int i=0 ; i < arrayOfCopyTo.length ; i++)
			{
				String[] arrayOfVoDetails=arrayOfCopyTo[i].split("@");
				
				_rosterPrintVO[index]=new DutyRosterPrintPropertiesVO();
				
				_rosterPrintVO[index].setRosterType(arrayOfRosterDetails[0]);
				_rosterPrintVO[index].setAlign(_fb.getAlignCopyTo());
				_rosterPrintVO[index].setPropertyType(arrayOfVoDetails[0]);
				_rosterPrintVO[index].setDisplayOrder(arrayOfVoDetails[1]);
				
		if(!arrayOfVoDetails[2].equals("NA"))	
				_rosterPrintVO[index].setDisplayValue(arrayOfVoDetails[2]);
				
				                                                           
				index++;
			}
}


if(length2!=0)
	_fb.setNoOfCopyToRow(Integer.toString(length2));
else
	_fb.setNoOfInstructionRow("1");

			
			
			_rosterPrintVO[index]=new DutyRosterPrintPropertiesVO();

			_rosterPrintVO[index].setRosterType(arrayOfRosterDetails[0]);
			_rosterPrintVO[index].setAlign(_fb.getAlignRosterBy());
			_rosterPrintVO[index].setPropertyType(arrayOfRosterBy[0]);
			_rosterPrintVO[index].setDisplayOrder(arrayOfRosterBy[1]);
			_rosterPrintVO[index].setDisplayValue(arrayOfRosterBy[2]);
			
			
			
			
		}
		
		catch (HisException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return _rosterPrintVO;
	}
	
	
	// On Changing the Display Order getting the VO's
	public static DutyRosterPrintPropertiesVO[] getVOsToBeChangedDisplayOrder(RosterPrintMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		DutyRosterPrintPropertiesVO[] _rosterPrintVO=null;
		
		try
		{
			String[] arrayOfRosterDetails=_fb.getRosterType().replace("^", "@").split("@");
			
			String[] arrayOfInstructions=null;
			String[] arrayOfCopyTo=null;
			
			int length1=0;
			int length2=0;
			
			
			
						arrayOfInstructions=_fb.getConcatedValueOfInstruction().split("#");							
									 
						arrayOfCopyTo=_fb.getConcatedValueOfCopyTo().split("#");
						
			
			if(arrayOfInstructions!=null)
				length1=arrayOfInstructions.length;
			
			
			if(arrayOfCopyTo!=null)
				length2=arrayOfCopyTo.length;
			
			
			//Calculating the size of the VO
			int size=length1+length2;
			
			
///index of the VO 
			int index=0;
			
			
//making an array of VO's			
			_rosterPrintVO=new DutyRosterPrintPropertiesVO[size];
			
			
			
if(arrayOfInstructions!=null)
{		
			for(int i=0 ; i < arrayOfInstructions.length ; i++)
			{
				String[] arrayOfVoDetails=arrayOfInstructions[i].split("@");
				
				_rosterPrintVO[index]=new DutyRosterPrintPropertiesVO();
				
				_rosterPrintVO[index].setRosterType(arrayOfRosterDetails[0]);				
				_rosterPrintVO[index].setDisplayOrder(arrayOfVoDetails[0]);
				_rosterPrintVO[index].setSerialNo(arrayOfVoDetails[2]);				
				                                                           
				index++;
			}
}



if(arrayOfCopyTo!=null)
  {	

			for(int i=0 ; i < arrayOfCopyTo.length ; i++)
			{
				String[] arrayOfVoDetails=arrayOfCopyTo[i].split("@");
				
				_rosterPrintVO[index]=new DutyRosterPrintPropertiesVO();
				
				_rosterPrintVO[index].setRosterType(arrayOfRosterDetails[0]);
				_rosterPrintVO[index].setDisplayOrder(arrayOfVoDetails[0]);
				_rosterPrintVO[index].setSerialNo(arrayOfVoDetails[2]);
				                                                           
				index++;
			}
  }


}
		
		catch (HisException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return _rosterPrintVO;
}
	
	
}
