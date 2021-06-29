/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;



import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.EmgMlcCaseTypeMstDATA;
import registration.masters.controller.util.EmgMlcCaseTypeMstUTIL;
import vo.registration.EmgMlcCaseTypeVO;


/**
 * @author s.singaravelan
 *
 */
public class EmgMlcCaseTypeMstACTION extends GenericController 
{
	private String message;
	private EmgMlcCaseTypeVO emgMlcCaseTypeModel=new EmgMlcCaseTypeVO();
	public HttpServletRequest request = null;
	
	private String flagAddMod;
	
	 public EmgMlcCaseTypeMstACTION() 
	 {
		 super(new EmgMlcCaseTypeMstUTIL(),"EmgMlcCaseTypeMst","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("EmgMlcCaseTypeMst");
		return "report";
	 } 
	 

	 
	 public String add()
	 {
		 emgMlcCaseTypeModel=new EmgMlcCaseTypeVO();
		 emgMlcCaseTypeModel.setStrIsMlcBound("1");
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(EmgMlcCaseTypeMstDATA.saveEmgMlcCaseTypeDtl(emgMlcCaseTypeModel, "1"))
		 {
			 try
			 {
				emgMlcCaseTypeModel.reset();
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
			 this.addActionMessage(emgMlcCaseTypeModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 emgMlcCaseTypeModel=EmgMlcCaseTypeMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(EmgMlcCaseTypeMstDATA.updateEmgMlcCaseTypeDtl(emgMlcCaseTypeModel, "2"))
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
			 this.addActionMessage(emgMlcCaseTypeModel.getStrWarning());
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

	public EmgMlcCaseTypeVO getEmgMlcCaseTypeModel() 
	{
		return emgMlcCaseTypeModel;
	}

	public void setEmgMlcCaseTypeModel(EmgMlcCaseTypeVO emgMlcCaseTypeModel) 
	{
		this.emgMlcCaseTypeModel = emgMlcCaseTypeModel;
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
