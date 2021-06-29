package appointment.masters.controller.action;

/**
 * @author nehasharma developed on 10-Feb-2014
 *
 */

import javax.servlet.http.HttpServletRequest;

import appointment.masters.controller.data.ApptConfigMstDATA;
import appointment.masters.controller.util.ApptConfigMstUTL;
import vo.appointment.*;


public class ApptConfigMstACTION extends ApptConfigMstVO 
{

	private static final long serialVersionUID = 1L;

	String target = null;
	
	// judges whether jsp is to be open in Add or Modify mode..
	private String flagToAddMod;
	private String message;
	public ApptConfigMstACTION() 
	{
		super(new ApptConfigMstUTL(), "ApptConfigMst", "appointment");
	}
	
	public String execute() 
	{
		super.LIST();
		return SUCCESS;
	}

	public String add() 
	{
		try
		 {
			ApptConfigMstDATA.getApptTypeList(super.getRequest());
			ApptConfigMstDATA.getApptDurationList(super.getRequest());
			ApptConfigMstDATA.getApptShiftList(super.getRequest());			
		 }
		catch(Exception e)
		 {
			 e.printStackTrace();
		 }
				 
		flagToAddMod = "ADD";
		return "add";
	}
	
	public String save() 
	{
		 try
		 {
			 if(ApptConfigMstDATA.saveConfigDtl(super.getRequest(), this))
			 {
				 super.LIST();
				 message="Data added successfully";
				 return SUCCESS;
			 }
			 else
			 {
				 message="Duplicate data cannot be added";
				 super.execute();
				 return SUCCESS;
			 }
				 
		 }
		 catch(Exception e)
		 {
			 	e.printStackTrace();
			 	return SUCCESS;
		 }			

	}
	
	// to fetch details of the record selected while modifying..
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 
		 ApptConfigMstDATA.getApptDurationList(super.getRequest());
		 ApptConfigMstDATA.getApptShiftList(super.getRequest());
		 ApptConfigMstDATA.getApptTypeModList(super.getRequest());
		 ApptConfigMstDATA.getApptTypeSelModList(super.getRequest());
		 ApptConfigMstDATA.getDefaultApptTypeModList(super.getRequest());
		 
		 ApptConfigMstDATA.modifyRecord(request,this);
		
		 	 
		 return "modify";
	 }
	 
	 // to update details modified..
	 public String update()
	 {
		
		 if(ApptConfigMstDATA.update(super.getRequest(),this))
		 {
			 try
			 {
					super.LIST();
					message="Data modified successfully";
			 }
			 catch(Exception e)
			 {
					e.printStackTrace();
			 }
			 return SUCCESS;
		 }
		 else
		 {
			 message="Data cannot be modified";
			 flagToAddMod="MODIFY";
			 return "modify";
		 }
		 }

	 
	 // to get parameter wise essensial details..
	 public String getOPDRosterSchedule()
	 {
		 ApptConfigMstDATA.getOPDRosterSchedule(super.getRequest(),super.getResponse(), this);
		 return null;
	 }
	public String getFlagToAddMod() 
	{
		return flagToAddMod;
	}
	public void setFlagToAddMod(String flagToAddMod) 
	{
		this.flagToAddMod = flagToAddMod;
	}
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}

	 public String report()
	 {
		super.REPORT("ApptConfigMst");
		return "report";
	 } 

	}
