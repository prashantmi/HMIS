/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import registration.masters.controller.data.UOMMstDATA;
import registration.masters.controller.util.UOMMstUTIL;
import hisglobal.masterutil.GenericController;
import vo.registration.CountryVO;
import vo.registration.UOMVO;

/**
 * Country Master. Only View and Report is enabled
 * @author s.singaravelan
 */
public class UOMMstACTION extends GenericController 
{
	private String message;
	private UOMVO uomModel=new UOMVO();
	public HttpServletRequest request = null;
	
	private String flagAddMod;
	
	 public UOMMstACTION() 
	 {
		 super(new UOMMstUTIL(),"UOMMst","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("UOMMst");
		return "report";
	 } 
	 	 
	 public String add()
	 {
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(UOMMstDATA.saveUOMDtl(uomModel, "1",super.getRequest()))
		 {
			 try
			 {
				uomModel.reset();
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
			 this.addActionMessage(uomModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 uomModel=UOMMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(UOMMstDATA.updateUomDtl(uomModel, "2",super.getRequest()))
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
			 this.addActionMessage(uomModel.getStrWarning());
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

	public String getFlagAddMod()
	{
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod)
	{
		this.flagAddMod = flagAddMod;
	}

	public UOMVO getUomModel() {
		return uomModel;
	}

	public void setUomModel(UOMVO uomModel) {
		this.uomModel = uomModel;
	}

	
}
