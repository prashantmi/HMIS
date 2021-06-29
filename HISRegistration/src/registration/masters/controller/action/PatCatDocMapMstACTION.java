/**
 * 
 */
package registration.masters.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.config.RegistrationConfig;
import registration.masters.controller.actionsupport.MasterSUP;
import registration.masters.controller.data.DepartmentMstDATA;
import registration.masters.controller.data.PatCatDocMapMstDATA;
import registration.masters.controller.data.UnitMstDATA;
import registration.masters.controller.util.MaritalMstUTIL;
import registration.masters.controller.util.PatCatDocMapMstUTIL;
import vo.registration.MaritalVO;
import vo.registration.PatCatDocVO;


/**
 * @author s.singaravelan
 *
 */
public class PatCatDocMapMstACTION extends MasterSUP
{
	private static final long serialVersionUID = 1L;
	private String message;
	private PatCatDocVO patCatDocMapModel=new PatCatDocVO();
	public HttpServletRequest request = null;
	private String flagAddMod;


	public String execute()
	{
		add();
		try{			
		}catch(Exception e){
			e.printStackTrace();
		}		 
		return "input";
	}

	public String add()
	{
		try
		{
			HttpServletRequest request= super.getRequest();
			patCatDocMapModel=PatCatDocMapMstUTIL.getAddEssentials(request);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		flagAddMod="ADD";
		return "input";
	}

	public String viewData()
	{
		try
		{
			HttpServletRequest request= super.getRequest();
			patCatDocMapModel=PatCatDocMapMstUTIL.getModEssentials(patCatDocMapModel,request);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		flagAddMod="ADD";
		return "input";
	}

	public String save()
	{
		if(PatCatDocMapMstUTIL.saveCategoryVerificationDocument(patCatDocMapModel,super.getRequest(), "1"))
		{
			try
			{
				patCatDocMapModel=PatCatDocMapMstUTIL.getModEssentials(patCatDocMapModel, super.getRequest());
				flagAddMod="ADD";
				message="Document Mapped successfully";
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return "input";
		}
		else
		{
			this.addActionMessage(patCatDocMapModel.getStrWarning());
			flagAddMod="ADD";
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

	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	public PatCatDocVO getPatCatDocMapModel() {
		return patCatDocMapModel;
	}

	public void setPatCatDocMapModel(PatCatDocVO patCatDocMapModel) {
		this.patCatDocMapModel = patCatDocMapModel;
	}
	public void validateSave()
	{
		//String mapppedDocs = patCatDocMapModel.getStrMappedDocCodes()[0];
		//int totalMapppedElement = mapppedDocs.length();
		if(patCatDocMapModel.getStrPatCategoryCode().equals("-1"))
		{
			addFieldError("strPatCategoryCode", "Please Select Patient Primary Category.");
			//request.getSession().setAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY, new ArrayList());
		}
		if(patCatDocMapModel.getStrMappedDocCodes() !=null)
		{
			if(patCatDocMapModel.getStrMappedDocCodes().length <=0)
			{
				addFieldError("strMappedDocCodes", "Please Select Some Verification Document..!");
			}
		}
		else
		{
			addFieldError("strMappedDocCodes", "Please Select  Verification Document..!");
		}
	}
}
