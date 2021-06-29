/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.PatCategoryMstDATA;
import registration.masters.controller.util.PatCategoryGlobalMstUTIL;
import vo.registration.PatCategoryVO;


/**
 * @author s.singaravelan
 *
 */
public class PatCategoryGlobalMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private PatCategoryVO patCategoryModel=new PatCategoryVO();
	public HttpServletRequest request = null;
	private String flagAddMod="";
	
	
	 public PatCategoryGlobalMstACTION() 
	 {
		 super(new PatCategoryGlobalMstUTIL(),"GlobalPatCategory","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	
	 public String report()
	 {
		super.REPORT("GlobalPatCategory");
		return "report";
	 } 
	 
	 public String add()
	 {
		 PatCategoryMstDATA.getPatCategoryEssentials();
		 flagAddMod="ADD";
		 return "input";
	 }
	
	 public String save()
	 {
		 if(PatCategoryMstDATA.savePatCategoryDtl(patCategoryModel, "1"))
		 {
			 patCategoryModel.reset();
			 message="Data added successfully";
			 flagAddMod="ADD";
			 return "input";
		 }
		 else
		 {
			 this.addActionMessage(patCategoryModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 @SuppressWarnings("unused")
	public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 PatCategoryMstDATA.getPatCategoryEssentials();
		 PatCategoryGlobalMstUTIL obj_DeptMst= new PatCategoryGlobalMstUTIL();
		 patCategoryModel=PatCategoryMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";	 
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(PatCategoryMstDATA.updatePatCategoryDtl(patCategoryModel, "2"))
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
			 this.addActionMessage(patCategoryModel.getStrWarning());
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

	public PatCategoryVO getPatCategoryModel() 
	{
		return patCategoryModel;
	}

	public void setPatCategoryModel(PatCategoryVO patCategoryModel) 
	{
		this.patCategoryModel = patCategoryModel;
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
