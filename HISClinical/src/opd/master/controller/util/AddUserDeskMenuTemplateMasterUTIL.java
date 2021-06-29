package opd.master.controller.util;

/**
 * @author  CDAC
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.AddUserDeskMenuTemplateMasterDATA;
import opd.master.controller.fb.UserDeskMenuTemplateMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class AddUserDeskMenuTemplateMasterUTIL extends ControllerUTIL 
{
	//* Setting Essentials for User Desk Menu Template Master
	public static void setEssential(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
				
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))    //For Desk Wise Mode
				mp=AddUserDeskMenuTemplateMasterDATA.getAllDesk(userVO);	
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE)) //For Unit wise & Unit-Seat Wise mode
				mp=AddUserDeskMenuTemplateMasterDATA.getEssentials(userVO);
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE)) //For Unit wise & Unit-Seat Wise mode
				mp=AddUserDeskMenuTemplateMasterDATA.getEssentials(userVO);
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE)) //For Ward Wise mode
				mp=AddUserDeskMenuTemplateMasterDATA.getEssentials(userVO);
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE)) //For WardSeat Wise mode
				mp=AddUserDeskMenuTemplateMasterDATA.getEssentials(userVO);
			
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Selected Units data 
	public static void setSelectedUnitsData(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session = _request.getSession();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USERDESKUNITS_NOT_UNITTEMPLATE);
			ArrayList alUnits=new ArrayList();
			String[] selunits=_fb.getSelectedUnits();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selunits.length;j++ )
					if(selunits[j].equals(entobj.getValue()))
						alUnits.add(entobj);
			}
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS,alUnits);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void setSelectedUnitsDataForWard(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session = _request.getSession();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDWSISE);
			ArrayList alUnits=new ArrayList();
			String[] selunits=_fb.getSelectedUnits();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selunits.length;j++ )
					if(selunits[j].equals(entobj.getValue()))
						alUnits.add(entobj);
			}
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS,alUnits);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Selected Units Data for unit seat wise
	public static void setSelectedUnitsDataForSeatWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session = _request.getSession();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USERDESKUNITS_SEAT_NOTNULL);
			_fb.setIsGoPressed(OpdConfig.STEP1);
			ArrayList alUnits=new ArrayList();
			String[] selunits=_fb.getSelectedUnits();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selunits.length;j++ )
					if(selunits[j].equals(entobj.getValue()))
						alUnits.add(entobj);
			}
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE);
			
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS,alUnits);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void setSelectedUnitsDataForWardSeat(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session = _request.getSession();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDSEATWSISE);
			_fb.setIsGoPressed(OpdConfig.STEP1);
			ArrayList alUnits=new ArrayList();
			String[] selunits=_fb.getSelectedUnits();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selunits.length;j++ )
					if(selunits[j].equals(entobj.getValue()))
						alUnits.add(entobj);
			}
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE);
			
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS,alUnits);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Selected Seats Data i.e. Selected Seats 
	public static void setSelectedSeatsData(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			HttpSession session = _request.getSession();

			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			String[] selunits=new String[lst.size()];
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				selunits[i]=entobj.getValue();
			}
			_fb.setSelectedUnits(selunits);
			_fb.setGotSeats("1");
			_fb.setDeskId("-1");
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE);
			_fb.setIsGoPressed(OpdConfig.STEP2);

			// Setting Selected Seat Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESK_SEATS);
			ArrayList alSeats=new ArrayList();
			String[] selSeats=_fb.getSelectedSeats();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selSeats.length;j++ )
					if(selSeats[j].equals(entobj.getValue()))
						alSeats.add(entobj);
			}
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS,alSeats);
			WebUTIL.setMapInSession(mp,_request);
			
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void setSelectedSeat(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			HttpSession session = _request.getSession();

			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			String[] selunits=new String[lst.size()];
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				selunits[i]=entobj.getValue();
			}
			
			_fb.setSelectedUnits(selunits);
			_fb.setDeskId("-1");
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE);
			_fb.setIsGoPressed(OpdConfig.STEP3);

			// Setting Selected Ward Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS);
			System.out.println("list"+OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS);
					
			String[] selWards=new String[lst.size()];
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				selWards[i]=entobj.getValue();
			}
			
			_fb.setSelectedWards(selWards);
			_fb.setGotWards("1");
			
			// Setting Selected Seat Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESK_SEATS);
			
			ArrayList alSeats=new ArrayList();
			
			String[] selSeats=_fb.getSelectedSeats();
			
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selSeats.length;j++ )
					if(selSeats[j].equals(entobj.getValue()))
						alSeats.add(entobj);
			}
			_fb.setGotSeats("1");
			_fb.setSelectedSeats(selSeats);
			
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS,alSeats);
			WebUTIL.setMapInSession(mp,_request);
			
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	public static void setSelectedWardsData(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			HttpSession session = _request.getSession();

			// Setting Selected Unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			String[] selunits=new String[lst.size()];
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				selunits[i]=entobj.getValue();
			}
			_fb.setSelectedUnits(selunits);
			_fb.setGotWards("1");
			_fb.setDeskId("-1");
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE);
			_fb.setIsGoPressed(OpdConfig.STEP2);

			// Setting Selected Ward Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_WARDS_UNITWARDWISE);
			ArrayList alWards=new ArrayList();
		
			String[] selWards=_fb.getSelectedWards();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selWards.length;j++ )
					if(selWards[j].equals(entobj.getValue()))
						alWards.add(entobj);
			}
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS,alWards);
			WebUTIL.setMapInSession(mp,_request);
			
			
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void setSelectedWard(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		Map mp=new HashMap();
		try
		{
			HttpSession session = _request.getSession();

			// Setting Selected unit Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			String[] selunits=new String[lst.size()];
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				selunits[i]=entobj.getValue();
			}
			_fb.setSelectedUnits(selunits);
			
			_fb.setDeskId("-1");
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE);
			_fb.setIsGoPressed(OpdConfig.STEP2);

			// Setting Selected Ward Name List
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_WARDS_UNITWARDSEATWISE);
			ArrayList alWards=new ArrayList();
		
			String[] selWards=_fb.getSelectedWards();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selWards.length;j++ )
					if(selWards[j].equals(entobj.getValue()))
						alWards.add(entobj);
			}
			_fb.setGotWards("1");
			mp.put(OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS,alWards);
			WebUTIL.setMapInSession(mp,_request);
			
			
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	
	//* Setting Selected Desks Data i.e. Selected Desk in Unit Wise mode
	public static void setSelectedDeskData(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		try
		{
			HttpSession session = _request.getSession();
			//Setting Desk Name
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT);
				
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				if(entobj.getValue().equals(_fb.getDeskId()))
				{
					_fb.setDeskName(entobj.getLabel());
					break;
				}
			}
			_fb.setIsGoPressed(OpdConfig.STEP2);
		
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Selected Desks Data i.e. Selected Desk (DESKWISE MODE)
	public static void setSelectedDeskDataForDeskWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		try
		{
			HttpSession session = _request.getSession();
			//Setting Desk Name
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_DESK);
				
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				if(entobj.getValue().equals(_fb.getDeskId()))
				{
					_fb.setDeskName(entobj.getLabel());
					break;
				}
			}
			_fb.setIsGoPressed(OpdConfig.STEP1);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Selected Desks Data i.e. Selected Desk (UNITSEAT WISE)
	public static void setSelectedDeskDataWithSeat(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		try
		{
			HttpSession session = _request.getSession();
			//Setting Desk Name
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS);
						
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				if(entobj.getValue().equals(_fb.getDeskId()))
				{
					_fb.setDeskName(entobj.getLabel());
					break;
				}
			}
			_fb.setIsGoPressed(OpdConfig.STEP3);
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void setSelectedDeskDataWithWard(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		try
		{
			HttpSession session = _request.getSession();
			//Setting Desk Name
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS);
						
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				if(entobj.getValue().equals(_fb.getDeskId()))
				{
					_fb.setDeskName(entobj.getLabel());
					break;
				}
			}
			_fb.setIsGoPressed(OpdConfig.STEP3);
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void setSelectedDeskDataWithWardSeat(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		try
		{
			HttpSession session = _request.getSession();
			//Setting Desk Name
			lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS);
						
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				if(entobj.getValue().equals(_fb.getDeskId()))
				{
					_fb.setDeskName(entobj.getLabel());
					break;
				}
			}
			_fb.setIsGoPressed(OpdConfig.STEP4);
			_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Source Seats List not already added a Template for Selected Units
	public static void setSourceSeats(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List Seat=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			String units[]=_fb.getSelectedUnits();

			setSysdate(_request);

			Seat=AddUserDeskMenuTemplateMasterDATA.getSeatsByInAllUnits(units,userVO);
			
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESK_SEATS,Seat);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void setSeats(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List Seat=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			String units[]=_fb.getSelectedUnits();
			String wards[]= _fb.getSelectedWards();

			setSysdate(_request);

			Seat=AddUserDeskMenuTemplateMasterDATA.getSeats(units,wards,userVO);
			
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESK_SEATS,Seat);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	//* Getting Desk List By Seats and Units
	public static void getDeskListBySeatsnUnits(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			String units[]=_fb.getSelectedUnits();
			String seats[]=_fb.getSelectedSeats();

			lstDesks=AddUserDeskMenuTemplateMasterDATA.getDesksByInAllUnitsnAllSeats(units,seats,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS,lstDesks);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	public static void getDeskListByWards(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			String units[]=_fb.getSelectedUnits();
			String wards[]=_fb.getSelectedWards();

			lstDesks=AddUserDeskMenuTemplateMasterDATA.getDeskListByUnitsnWards(units,wards,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS,lstDesks);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void getDeskListByWardsSeat(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			String units[]=_fb.getSelectedUnits();
			String wards[]=_fb.getSelectedWards();
			System.out.println("wards="+wards);
			String seats[]=_fb.getSelectedSeats();
			
			lstDesks=AddUserDeskMenuTemplateMasterDATA.getDeskListByUnitsnWardsSeat(units,wards,seats,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS,lstDesks);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	
	//* Getting Desk List By Seats 
	public static void getDeskListBySeats(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			_fb.setIsGoPressed(OpdConfig.STEP1);
			String units[]=_fb.getSelectedUnits();
			//String seats[]=_fb.getSelectedSeats();

			lstDesks=AddUserDeskMenuTemplateMasterDATA.getDesksByInAllUnits(units,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT,lstDesks);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Getting Desk List By Units with seat is null
	public static void getDeskListByUnits(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			_fb.setIsGoPressed(OpdConfig.STEP1);
			String units[]=_fb.getSelectedUnits();
			
			lstDesks=AddUserDeskMenuTemplateMasterDATA.getDesksByInAllUnits(units,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT,lstDesks);
			
			WebUTIL.setMapInSession(mp,_request);
			if(lstDesks.size()==0)
			{
				objStatus.add(Status.DONE,"No Desk Exist For Selected Unit","");
			}
			else
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	
	public static void getWardListByUnits(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstWards=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			_fb.setIsGoPressed(OpdConfig.STEP1);
			String units[]=_fb.getSelectedUnits();
			
			lstWards=AddUserDeskMenuTemplateMasterDATA.getWardsByInAllUnits(units,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_WARDS_UNITWARDWISE,lstWards);
			
			WebUTIL.setMapInSession(mp,_request);
			if(lstWards.size()==0)
			{
				objStatus.add(Status.DONE,"No Ward Exist For Selected Unit","");
			}
			else
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	public static void getWardListForWardSeatWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstWards=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			_fb.setIsGoPressed(OpdConfig.STEP1);
			String units[]=_fb.getSelectedUnits();
			
			lstWards=AddUserDeskMenuTemplateMasterDATA.getWardsByForWardSeatWise(units,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_WARDS_UNITWARDSEATWISE,lstWards);
			
			WebUTIL.setMapInSession(mp,_request);
			if(lstWards.size()==0)
			{
				objStatus.add(Status.DONE,"No Ward Exist For Selected Unit","");
			}
			else
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	
	//* Setting Menu templates based on selected desk (used in all three modes)
	public static void getDeskMenuTemplates(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lst=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
		
					
			lst=AddUserDeskMenuTemplateMasterDATA.getAllTemplateBasedDeskMenusByDeskId(_fb.getDeskId(),userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST,lst);

			lst=AddUserDeskMenuTemplateMasterDATA.getAllTemplates(userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES,lst);
			
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Saving Templates To Desks By Seat and Units-Wise(MODIFYSAVE)
	public static boolean AddTemplateToMenusDesk(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		//List lstSelUnits=new ArrayList();
		//List lstSelSeats=new ArrayList();
		//List lstSelWards= new ArrayList();
		
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			UserDeskMenuTemplateMasterVO voUDMT =new UserDeskMenuTemplateMasterVO();
			
			//HttpSession session = _request.getSession();
			
			String unitName= _fb.getUnitName();
			String seatName= _fb.getSeatName();
			String wardName= _fb.getWardName();
		
			if(wardName==null)
			{
				if(unitName!=null && seatName==null)
					_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE);
			
				if(unitName!=null && seatName!=null)
					_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE);
				
				if(unitName==null && seatName==null)
					_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE);
			}
			if(wardName!=null)
			{
				if(seatName==null)
					_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE);
				if(seatName!=null)
					_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE);
			}
			voUDMT.setAdditionMode(_fb.getAdditionMode());
								
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE)
			{	
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
					voUDMT.setDeskId(_fb.getDeskId());
					voUDMT.setIsGoPressed(_fb.getIsGoPressed());
					AddUserDeskMenuTemplateMasterDATA.deleteTemplateToDeskMenuTemplateMasterUnitWise(voUDMT,userVO);
					
					String tempData= _fb.getDataTemplateList();
					String[] tempDataRows=tempData.split("@");
					for(int k=0; k < tempDataRows.length;k++)
					{
						String[] tempRowAdd= tempDataRows[k].split("#");
						voUDMT =new UserDeskMenuTemplateMasterVO();
						voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
						voUDMT.setDeskId(_fb.getDeskId());
						voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
						voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
						voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
					
						AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenu(voUDMT,userVO);//UnitWise
					}
			}
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE)
			{	
				voUDMT =new UserDeskMenuTemplateMasterVO();
				voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
				voUDMT.setUserSeatId(_fb.getUserSeatId());
				voUDMT.setDeskId(_fb.getDeskId());
				voUDMT.setIsGoPressed(_fb.getIsGoPressed());
				AddUserDeskMenuTemplateMasterDATA.deleteTemplateToDeskMenuTemplateMasterUnitSeatWise(voUDMT,userVO);
						
				String tempData= _fb.getDataTemplateList();
				String[] tempDataRows=tempData.split("@");
				for(int k=0; k < tempDataRows.length;k++)
				{
					String[] tempRowAdd= tempDataRows[k].split("#");
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
					voUDMT.setUserSeatId(_fb.getUserSeatId());
					voUDMT.setDeskId(_fb.getDeskId());
					voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
					voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
					voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
					
					AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuSeatWise(voUDMT,userVO);
				}
			}//end of if
			
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE)
			{
				UserDeskMenuTemplateMasterVO VOUDMT =new UserDeskMenuTemplateMasterVO();
				VOUDMT.setDeskId(_fb.getDeskId());
				
				AddUserDeskMenuTemplateMasterDATA.deleteTemplateToDeskMenuTemplateMasterDeskWise(VOUDMT,userVO);
					
				String tempData= _fb.getDataTemplateList();
				String[] tempDataRows=tempData.split("@");
				for(int k=0; k < tempDataRows.length;k++)
				{
					String[] tempRowAdd= tempDataRows[k].split("#");
					VOUDMT =new UserDeskMenuTemplateMasterVO();
					VOUDMT.setDeskId(_fb.getDeskId());
					VOUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
					VOUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
					VOUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
							
					AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuDeskWise(VOUDMT,userVO);
				}
			}//end of if
			
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE)
			{
				voUDMT =new UserDeskMenuTemplateMasterVO();
				voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
				voUDMT.setWardCode(_fb.getWardId());
				voUDMT.setDeskId(_fb.getDeskId());
				voUDMT.setIsGoPressed(_fb.getIsGoPressed());
				voUDMT.setSlNo(_fb.getSlNo());
				AddUserDeskMenuTemplateMasterDATA.deleteTemplateToDeskMenuTemplateMasterUnitWardWise(voUDMT,userVO);
						
				String tempData= _fb.getDataTemplateList();
				String[] tempDataRows=tempData.split("@");
				for(int k=0; k < tempDataRows.length;k++)
				{
					String[] tempRowAdd= tempDataRows[k].split("#");
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
					voUDMT.setWardCode(_fb.getWardId());
					voUDMT.setDeskId(_fb.getDeskId());
					voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
					voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
					voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
					
					AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuWardWise(voUDMT,userVO);
				}
			}
			
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE)
			{
				voUDMT =new UserDeskMenuTemplateMasterVO();
				voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
				voUDMT.setWardCode(_fb.getWardId());
				voUDMT.setDeskId(_fb.getDeskId());
				voUDMT.setIsGoPressed(_fb.getIsGoPressed());
				voUDMT.setSlNo(_fb.getSlNo());
				AddUserDeskMenuTemplateMasterDATA.deleteTemplateToDeskMenuTemplateMasterUnitWardWise(voUDMT,userVO);
						
				String tempData= _fb.getDataTemplateList();
				String[] tempDataRows=tempData.split("@");
				for(int k=0; k < tempDataRows.length;k++)
				{
					String[] tempRowAdd= tempDataRows[k].split("#");
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
					voUDMT.setWardCode(_fb.getWardId());
					voUDMT.setUserSeatId(_fb.getUserSeatId());
					voUDMT.setDeskId(_fb.getDeskId());
					voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
					voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
					voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
					AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuWardSeatWise(voUDMT,userVO);
				}
			}
			
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return saveFlag;
	}
	
	//* Saving Templates To Desks Units-Wise
	public static boolean AddTemplateToMenusDeskUnitWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
	//	List lstSelSeats=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);

			HttpSession session = _request.getSession();
			//Setting Desk Name
			lstSelUnits=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
										
			UserDeskMenuTemplateMasterVO voUDMT;
			int countUnit=lstSelUnits.size();
			System.out.println("sel units...."+countUnit);
			
			for(int i=0;i<countUnit;i++)
			{
				voUDMT =new UserDeskMenuTemplateMasterVO();
				voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
				voUDMT.setDeskId(_fb.getDeskId());
				AddUserDeskMenuTemplateMasterDATA.deleteTemplateToDeskMenuTemplateMasterUnitWise(voUDMT,userVO);
					
				String tempData= _fb.getDataTemplateList();
				String[] tempDataRows=tempData.split("@");
				for(int k=0; k < tempDataRows.length;k++)
				{
					String[] tempRowAdd= tempDataRows[k].split("#");
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
					//	voUDMT.setUserSeatId(((Entry)lstSelSeats.get(j)).getValue());
					voUDMT.setDeskId(_fb.getDeskId());
					voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
					voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
					voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
						
					AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenu(voUDMT,userVO);
					}
				}
				for(int j=0;j<countUnit;j++)
				{
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(j)).getValue());
					//voUDMT.setUserSeatId(((Entry)lstSelSeats.get(j)).getValue());
					voUDMT.setDeskId(_fb.getDeskId());
					 AddUserDeskMenuTemplateMasterDATA.deleteTemplateToDeskMenuTemplateMasterUnitWise(voUDMT,userVO);
					
					String tempData= _fb.getDataTemplateList();
					String[] tempDataRows=tempData.split("@");
					for(int k=0; k < tempDataRows.length;k++)
					{
						String[] tempRowAdd= tempDataRows[k].split("#");
						voUDMT =new UserDeskMenuTemplateMasterVO();
						voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(j)).getValue());
						//	voUDMT.setUserSeatId(((Entry)lstSelSeats.get(j)).getValue());
						voUDMT.setDeskId(_fb.getDeskId());
						voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
						voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
						voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
							
						AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenu(voUDMT,userVO);
					}
				}
				objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return saveFlag;
	}

	//Save data in Unit mode(SAVE MODE)
	public static boolean addTemplateToMenusDeskUnitsWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
		
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);

			HttpSession session = _request.getSession();
			//Setting Desk Name
			lstSelUnits=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			System.out.println("sel units are..."+lstSelUnits);
	
			UserDeskMenuTemplateMasterVO voUDMT;
			
			int countUnit=lstSelUnits.size();
			for(int i=0;i<countUnit;i++)
			{
				voUDMT =new UserDeskMenuTemplateMasterVO();
				voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
				voUDMT.setDeskId(_fb.getDeskId());
						
				String tempData= _fb.getDataTemplateList();
				String[] tempDataRows=tempData.split("@");
				for(int k=0; k < tempDataRows.length;k++)
				{
					String[] tempRowAdd= tempDataRows[k].split("#");
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
					voUDMT.setDeskId(_fb.getDeskId());
					voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
					voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
					voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
					
					AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuUnitWise(voUDMT,userVO);
				}
			}
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return saveFlag;
	}
	
	// Save data in UnitSeat Wise mode(SAVE MODE)
	public static boolean addTemplateToMenusDeskSeatWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
		List lstSelSeats=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);

			HttpSession session = _request.getSession();
			//Setting Desk Name
			lstSelUnits=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			lstSelSeats=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS);

			UserDeskMenuTemplateMasterVO voUDMT;
			int countUnit=lstSelUnits.size();
			for(int i=0;i<countUnit;i++)
			{
				int	countSeat=lstSelSeats.size();
				for(int j=0;j<countSeat;j++)
				{
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
					voUDMT.setUserSeatId(((Entry)lstSelSeats.get(j)).getValue());
					voUDMT.setDeskId(_fb.getDeskId());
										
					String tempData= _fb.getDataTemplateList();
					String[] tempDataRows=tempData.split("@");
					for(int k=0; k < tempDataRows.length;k++)
					{
						String[] tempRowAdd= tempDataRows[k].split("#");
						voUDMT =new UserDeskMenuTemplateMasterVO();
						voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
						voUDMT.setUserSeatId(((Entry)lstSelSeats.get(j)).getValue());
						voUDMT.setDeskId(_fb.getDeskId());
						voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
						voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
						voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
						
						AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuSeatWise(voUDMT,userVO);
					}
				}
			}
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return saveFlag;
	}

	public static boolean addTemplateToMenusDeskWardSeatWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
		List lstSelSeats=new ArrayList();
		List lstSelWards=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);

			HttpSession session = _request.getSession();
			//Setting Desk Name
			lstSelUnits=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			lstSelSeats=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_SEATS);
			lstSelWards=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS);

			UserDeskMenuTemplateMasterVO voUDMT;
			int countUnit=lstSelUnits.size();
			for(int i=0;i<countUnit;i++)
			{
				//int	countSeat=lstSelSeats.size();
				int countWard=lstSelWards.size();
				for(int a=0;a<countWard;a++)
					//for(int j=0;j<countSeat;j++)
					{
						voUDMT =new UserDeskMenuTemplateMasterVO();
						voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
						voUDMT.setUserSeatId(((Entry)lstSelSeats.get(a)).getValue());
						voUDMT.setWardCode(((Entry)lstSelWards.get(a)).getValue());
						voUDMT.setDeskId(_fb.getDeskId());
										
						String tempData= _fb.getDataTemplateList();
						String[] tempDataRows=tempData.split("@");
						for(int k=0; k < tempDataRows.length;k++)
						{
							String[] tempRowAdd= tempDataRows[k].split("#");
							voUDMT =new UserDeskMenuTemplateMasterVO();
							voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
							voUDMT.setUserSeatId(((Entry)lstSelSeats.get(a)).getValue());
							voUDMT.setWardCode(((Entry)lstSelWards.get(a)).getValue());
							voUDMT.setDeskId(_fb.getDeskId());
							voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
							voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
							voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
						
							AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuWardSeatWise(voUDMT,userVO);
						}
					//}
					}
				objStatus.add(Status.INPROCESS);
			}
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return saveFlag;
	}

	
	
	public static boolean addTemplateToMenusDeskWardWise(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
		List lstSelWards=new ArrayList();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);

			HttpSession session = _request.getSession();
			//Setting Desk Name
			lstSelUnits=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			lstSelWards=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_WARDS);

			UserDeskMenuTemplateMasterVO voUDMT;
			int countUnit=lstSelUnits.size();
			for(int i=0;i<countUnit;i++)
			{
				int	countWard=lstSelWards.size();
				for(int j=0;j<countWard;j++)
				{
					voUDMT =new UserDeskMenuTemplateMasterVO();
					voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
					voUDMT.setWardCode(((Entry)lstSelWards.get(j)).getValue());
					voUDMT.setDeskId(_fb.getDeskId());
					
										
					String tempData= _fb.getDataTemplateList();
					String[] tempDataRows=tempData.split("@");
					for(int k=0; k < tempDataRows.length;k++)
					{
						String[] tempRowAdd= tempDataRows[k].split("#");
						voUDMT =new UserDeskMenuTemplateMasterVO();
						voUDMT.setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
						voUDMT.setWardCode(((Entry)lstSelWards.get(j)).getValue());
						voUDMT.setDeskId(_fb.getDeskId());
						voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
						voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
					
						voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
						
						AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuWardWise(voUDMT,userVO);
					}
				}
			}
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return saveFlag;
	}

	
	// Save data in desk mode(SAVE MODE)
	public static boolean addTemplateToMenusDesk(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			//HttpSession session = _request.getSession();

			UserDeskMenuTemplateMasterVO voUDMT;

			voUDMT =new UserDeskMenuTemplateMasterVO();
			voUDMT.setDeskId(_fb.getDeskId());
			String tempData= _fb.getDataTemplateList();
			String[] tempDataRows=tempData.split("@");
			for(int k=0; k < tempDataRows.length;k++)
			{
				String[] tempRowAdd= tempDataRows[k].split("#");
				voUDMT =new UserDeskMenuTemplateMasterVO();
				voUDMT.setDeskId(_fb.getDeskId());
				voUDMT.setDeskMenuId(tempRowAdd[0].split("&")[0]);
				voUDMT.setTemplateId(tempRowAdd[1].split("&")[0]);
				voUDMT.setIsDefault(tempRowAdd[2].split("&")[0]);
				
				AddUserDeskMenuTemplateMasterDATA.SaveTemplateToDeskMenuDeskWise(voUDMT,userVO);
			}
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return saveFlag;
	}

	
	//* Fetching Data for Modification/View
	public static boolean fetchUserDeskMenuTemplateRecordData(UserDeskMenuTemplateMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		//List lstSelUnits=new ArrayList();
		//List lstSelSeats=new ArrayList();
		//List lstSelWards=new ArrayList();
		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			Entry entObj=new Entry();
			UserDeskMenuTemplateMasterVO voUDMT = new UserDeskMenuTemplateMasterVO();
			//List Seats = new ArrayList();
			List al = new ArrayList();
			// Fetching Selected Record Primary Key
			String chk=_fb.getChk()[0].replace("^","@");
			String[] concatid=chk.split("@");
			
			//String SLNO=concatid[0];
					
			_fb.setSlNo(concatid[0]);
			_fb.setDeskMenuId(concatid[1]);
			_fb.setTemplateId(concatid[2]);
			_fb.setDeskId(concatid[3]);
			_fb.setHospCode(concatid[4]);
			
			voUDMT.setSlNo(_fb.getSlNo());
			voUDMT.setDeskMenuId(_fb.getDeskMenuId());
			voUDMT.setTemplateId(_fb.getTemplateId());
			voUDMT.setHospCode(_fb.getHospCode());
			voUDMT.setDeskId(_fb.getDeskId());
			
			voUDMT = AddUserDeskMenuTemplateMasterDATA.getRecords(voUDMT,userVO);
			
			String selUnit=voUDMT.getDeptUnitCode();
			//String selSeat=voUDMT.getUserSeatId();
			//String selWard=voUDMT.getWardCode();
			
			_fb.setUserSeatId(voUDMT.getUserSeatId());
			_fb.setSlNo(voUDMT.getSlNo());
			_fb.setDeskId(voUDMT.getDeskId());
			_fb.setDeptUnitCode(voUDMT.getDeptUnitCode());
			_fb.setWardId(voUDMT.getWardCode());
			
			if(_fb.getWardId()==null)
			{
				if(_fb.getDeptUnitCode()==null && _fb.getUserSeatId()==null)	
				_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE);
				if(_fb.getDeptUnitCode()!=null && _fb.getUserSeatId()==null)	
				_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE);
				if(_fb.getDeptUnitCode()!=null && _fb.getUserSeatId()!=null)	
				_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE);
			}
			if(_fb.getWardId()!=null)
			{	
				if(_fb.getUserSeatId()==null)// for ward-unit mode
					_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE);
				if(_fb.getUserSeatId()!=null)// for ward-seat mode
					_fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE);
			}
			
			// Setting Selected Unit
			if(_fb.getDeptUnitCode()!=null)
			{
				//int unit=Integer.parseInt(selUnit);
				UserDeskMenuTemplateMasterVO unitName=AddUserDeskMenuTemplateMasterDATA.getUnitName(selUnit,userVO);
				_fb.setUnitName(unitName.getUnitName());
				voUDMT.setUnitName(_fb.getUnitName());
				_fb.setDeptUnitCode(unitName.getDeptUnitCode());
				voUDMT.setDeptUnitCode(_fb.getDeptUnitCode());
			}
			
			
			
			// Setting Selected Seat
			if(_fb.getUserSeatId()!=null )
			{
				//int seat=Integer.parseInt(selSeat);
				UserDeskMenuTemplateMasterVO Seat=AddUserDeskMenuTemplateMasterDATA.getSeats(voUDMT,userVO);
				_fb.setGotSeats("1");
				_fb.setSeatName(Seat.getSeatName());
				_fb.setUserSeatId(Seat.getUserSeatId());
			}
			
						
			if(_fb.getWardId()!=null)
			{
				//int ward=Integer.parseInt(selWard);
				UserDeskMenuTemplateMasterVO Ward=AddUserDeskMenuTemplateMasterDATA.getWards(voUDMT,userVO);
				_fb.setGotWards("1");
				_fb.setWardName(Ward.getWardName());
				_fb.setWardId(Ward.getWardCode());
			}
			
			
			// Set Selected Desk Name
			DeskMasterVO voDesk=AddUserDeskMenuTemplateMasterDATA.fetchDeskVOByDeskId(voUDMT.getDeskId(),userVO);
			_fb.setDeskName(voDesk.getDeskName());
			voUDMT.setAdditionMode(_fb.getAdditionMode());
			
			al = AddUserDeskMenuTemplateMasterDATA.getTemplatesByUnitDesk(voUDMT,userVO);
			
			String data="";
			for(int i=0;i< al.size();i++)
			{
				entObj=(Entry)al.get(i);
				data+=entObj.getLabel();
				if(entObj.getValue().equals("0"))
					data+="#"+entObj.getValue()+"&"+"False";
				else
					data+="#"+entObj.getValue()+"&"+"True";
				data+="@";
			}
			if(!data.equals("")) data=data.substring(0,data.length()-1);
			_fb.setDataTemplateList(data);
			
			// Setting already Set Template List HTML
			String h=getListTempHtml(data);
			_fb.setHtmlTemplateList(h);
			
			//Setting All Desk Menu & Templates for Modification  
			List lst=AddUserDeskMenuTemplateMasterDATA.getAllTemplateBasedDeskMenusByDeskId(_fb.getDeskId(),userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST,lst);

			lst=AddUserDeskMenuTemplateMasterDATA.getAllTemplates(userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES,lst);

			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}	
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return true;
	}
	
	
	
	//* Getting List of Template in HTML
	private static String getListTempHtml(String data)
	{
		String h="";
		System.out.println("Data is..."+data);
		if(!data.equals(""))
		{
			h+="<table  width='100%' border='0' cellspacing='0' cellpadding='0'>";
			String [] rows=data.split("@");
			for(int i=0;i<rows.length;i++)
			{
				String[] rowdata=rows[i].split("#");
				h+="<tr>";
				
				h+="<td width='30%' class='tdfont'>";
				h+="<div align='center'>";
				h+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
				h+=rowdata[0].split("&")[1];
				h+="</font>";
				h+="</div>";
				h+="</td>";
				h+="<td width='40%' class='tdfont'>";
				h+="<div align='center'>";
				h+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
				h+=rowdata[1].split("&")[1];
				h+="</font>";
				h+="</div>";
				h+="</td>";
				h+="<td width='20%' class='tdfont'>";
				h+="<div align='center'>";
				h+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
				h+=rowdata[2].split("&")[1];
				h+="</font>";
				h+="</div>";
				h+="</td>";
				h+="<td width='10%' class='tdfont'>";
				h+="<div align='center'>";
				h+="<img class='button' src='/HIS/hisglobal/images/Mi_Green_Sqr.png' style='cursor:pointer' onclick ='subTemplateToMenuRow("+(i+1)+")' onkeypress='if(event.keyCode==13) subTemplateToMenuRow("+(i+1)+");'>";
				h+="</div>";
				h+="</td>";
				
				h+="</tr>";
			}
			h+="</table>";
		}
		return h;
	}
}
