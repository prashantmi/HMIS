/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;



import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.PatCategoryMstDATA;
import registration.masters.controller.util.PatCategoryMstUTIL;
import vo.registration.PatCategoryVO;


/**
 * @author s.singaravelan
 *
 */
public class PatCategoryMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private PatCategoryVO patCategoryModel=new PatCategoryVO();
	public HttpServletRequest request = null;
	private String flagAddMod="";


	public PatCategoryMstACTION() 
	{
		super(new PatCategoryMstUTIL(),"LocalPatCategory","registration");
	}

	public String execute()
	{
		super.LIST();
		return SUCCESS;
	}

	public String report()
	{
		super.REPORT("LocalPatCategory");
		return "report";
	} 

	public String add()
	{
		PatCategoryMstDATA.getPatCategoryEssentials();
		flagAddMod="ADD";
		return "input";
	}

	public String put()
	{
		try{
			PatCategoryMstDATA.getPatCategoryEssentials();	 
			patCategoryModel=PatCategoryMstDATA.addPatCatRecord(patCategoryModel);			 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		flagAddMod="ADD";
		return "input";
	}

	public String save()
	{
		if(PatCategoryMstDATA.savePatCategoryDtlLocal(patCategoryModel, "1"))
		{
			add();
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

	public String modify()
	{
		HttpServletRequest request= super.getRequest();
		PatCategoryMstDATA.getPatCategoryEssentials();	 
		patCategoryModel=PatCategoryMstDATA.modifyRecordLocal(request);
		flagAddMod="MODIFY";	 
		return "input";
	}

	public String update()
	{
		if(PatCategoryMstDATA.updatePatCategoryDtlLocal(patCategoryModel, "2"))
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



	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public PatCategoryVO getPatCategoryModel() {
		return patCategoryModel;
	}

	public void setPatCategoryModel(PatCategoryVO patCategoryModel) {
		this.patCategoryModel = patCategoryModel;
	}

	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}


}
