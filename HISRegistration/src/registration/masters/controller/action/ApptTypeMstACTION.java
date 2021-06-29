package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.ApptTypeMstDATA;
import registration.masters.controller.util.ApptTypeMstUTIL;
import vo.registration.ApptTypeVO;



public class ApptTypeMstACTION extends GenericController{
	private static final long serialVersionUID = 1L;
	private String message;
	private ApptTypeVO apptTypeModel=new ApptTypeVO();
	public HttpServletRequest request = null;
	private String flagAddMod;


	public ApptTypeMstACTION() 
	{
		super(new ApptTypeMstUTIL(), "AppointmentType", "registration" );	
	}

	public String execute()
	{
		super.LIST();
		return SUCCESS;
	}

	public String report()
	{
		super.REPORT("AppointmentType");
		return "report";
	} 


	public String save()
	{
		if(ApptTypeMstDATA.saveApptTypeDtl(apptTypeModel, "1"))
		{
			try
			{
				apptTypeModel.reset();
				flagAddMod="ADD";
				message="Data added successfully";
			}catch(Exception e){
				e.printStackTrace();
			}
			return "input";
		}
		else
		{
			this.addActionMessage(apptTypeModel.getStrWarning());
			flagAddMod="ADD";
			return "input";
		}
	}

	public String add()
	{
		try
		{	
			/*As we dont need any prerequisite for the Appointment Type Master the following line of src code is of no use as on Feb'19
			 * By Monika
			 * */
			ApptTypeMstDATA.getApptTypeEssentials();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		flagAddMod="ADD";
		return "input";
	}

	public String modify()
	{
		HttpServletRequest request= super.getRequest();
		ApptTypeMstDATA.getApptTypeEssentials();
		apptTypeModel=ApptTypeMstDATA.modifyRecord(request);
		flagAddMod="MODIFY";	 
		return "input";
	}

	public String update()
	{
		if(ApptTypeMstDATA.updateApptTypeDtl(apptTypeModel, "2"))
		{
			try
			{
				super.LIST();
				message="Data modified successfully";
			}catch(Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}
		else
		{
			this.addActionMessage(apptTypeModel.getStrWarning());
			flagAddMod="MODIFY";
			return "input";
		}

	}


	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public ApptTypeVO getApptTypeModel() 
	{
		return apptTypeModel;
	}

	public void setApptTypeModel(ApptTypeVO apptTypeModel) 
	{
		this.apptTypeModel = apptTypeModel;
	}

	public String getFlagAddMod() 
	{
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) 
	{
		this.flagAddMod = flagAddMod;
	}



}
