/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;



import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.EmgPatStatusMstDATA;
import registration.masters.controller.util.EmgPatStatusMstUTIL;
import vo.registration.EmgPatStatusVO;


/**
 * @author s.singaravelan
 *
 */
public class EmgPatStatusMstACTION extends GenericController 
{
	private String message;
	private EmgPatStatusVO emgPatStatusModel=new EmgPatStatusVO();
	public HttpServletRequest request = null;
	
	private String flagAddMod;
	
	 public EmgPatStatusMstACTION() 
	 {
		 super(new EmgPatStatusMstUTIL(),"EmgPatStatusMst","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("EmgPatStatusMst");
		return "report";
	 } 
	 

	 
	 public String add()
	 {
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(EmgPatStatusMstDATA.saveEmgPatStatusDtl(emgPatStatusModel, "1"))
		 {
			 try
			 {
				emgPatStatusModel.reset();
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
			 this.addActionMessage(emgPatStatusModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 emgPatStatusModel=EmgPatStatusMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(EmgPatStatusMstDATA.updateEmgPatStatusDtl(emgPatStatusModel, "2"))
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
			 this.addActionMessage(emgPatStatusModel.getStrWarning());
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

	public EmgPatStatusVO getEmgPatStatusModel() 
	{
		return emgPatStatusModel;
	}

	public void setEmgPatStatusModel(EmgPatStatusVO emgPatStatusModel) 
	{
		this.emgPatStatusModel = emgPatStatusModel;
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
