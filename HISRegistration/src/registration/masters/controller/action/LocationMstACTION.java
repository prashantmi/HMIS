/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;


import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.LocationMstDATA;
import registration.masters.controller.util.LocationMstUTIL;
import vo.registration.LocationVO;


/**
 * @author s.singaravelan
 *
 */
public class LocationMstACTION extends GenericController 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private LocationVO locModel=new LocationVO();
	public HttpServletRequest request = null;
	private String flagAddMod;

	
	 public LocationMstACTION() 
	 {
		 super(new LocationMstUTIL(),"Location","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	

	 public String report()
	 {
		super.REPORT("Location");
		return "report";
	 } 
	 
	
	 
	 public String add()
	 {
		 try
		 {	 
			 HttpServletRequest request= super.getRequest();	 
			 LocationMstDATA.getLocationEssentials(request);			
		 }
		 catch (Exception e) 
		 {
			e.printStackTrace();
		 }
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(LocationMstDATA.saveLocationDtl(locModel, "1"))
		 {
			 try
			 {
				 locModel.reset();
				 flagAddMod="ADD";
				 message="Data added successfully";
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 return "input";
		  }
		 else
		 {
			 this.addActionMessage(locModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 LocationMstDATA.getLocationEssentials(request);
		 locModel=LocationMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";	 
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(LocationMstDATA.updateLocationDtl(locModel, "2"))
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
			 this.addActionMessage(locModel.getStrWarning());
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

	public LocationVO getLocModel() 
	{
		return locModel;
	}

	public void setLocModel(LocationVO locModel)
	{
		this.locModel = locModel;
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
