package registration.masters.controller.action;


import hisglobal.masterutil.GenericController;
//import hisglobal.presentation.WebUTIL;


import javax.servlet.http.HttpServletRequest;


import registration.masters.controller.data.OccupationMstDATA;
//import registration.masters.controller.util.QualityTestMstUTIL;
import registration.masters.controller.util.OccupationMstUTIL;
//import registration.masters.controller.fb.QualityTestMstFB;
import vo.registration.OccupationVO;


/**
 * @author   Deepika Gaba
 * Used For:-	
 * Team Lead By:- 

 * 
 */
public class OccupationMstACTION extends GenericController  
{
	private String message;

	private OccupationVO VOOccupation=new OccupationVO();

	private static final long serialVersionUID = 1L;
	/** The strTarget. */
	String strTarget = "";
	private String flagAddMod;
	public HttpServletRequest request = null;


	/**
	 * calls super class constructor.
	 */
	public OccupationMstACTION() 
	{
		super(new OccupationMstUTIL(), "Occupation","registration");
	}

	public String execute()
	{
		super.LIST();

		return SUCCESS;
	}

	public String list()
	{
		super.LISTPAGE();

		return null;
	}

	public String search()
	{
		super.SEARCH();

		return null;
	}


	public String report()
	{
		super.REPORT("Occupation");

		return "report";
	} 

	public String add()
	{
		flagAddMod="ADD";
		return "input";
	}

	public String save()
	{
		if(OccupationMstDATA.saveOccupationDtl(super.getRequest(), VOOccupation, "1")){
			try
			{
				super.LIST();
				VOOccupation.setStrOccupationName("");
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
			VOOccupation.setStrWarning(VOOccupation.getStrWarning());
			this.addActionMessage(VOOccupation.getStrWarning());
			flagAddMod="ADD";
			return "input";
		}
	}

	public String modify()
	{
		HttpServletRequest request= super.getRequest();
		VOOccupation=OccupationMstDATA.modifyRecord(request,VOOccupation);
		flagAddMod="MODIFY";

		return "input";
	}
	public String update()
	{
		if(OccupationMstDATA.updateOccupationDtl(super.getRequest(),VOOccupation, "2")){
			try{
				super.LIST();
				message="Data modified successfully";
			}catch(Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}
		else{
			message="Data cannot be modified";
			this.addActionMessage(VOOccupation.getStrWarning());
			flagAddMod="MODIFY";
			return "input";
		}

	}
	public OccupationVO getVOOccupation() 
	{
		return VOOccupation;
	}
	
	public void setVOOccupation(OccupationVO VOOccupation)
	{
		this.VOOccupation = VOOccupation;
	}
	
	public String getFlagAddMod()
	{
		return flagAddMod;
	}
	
	public void setFlagAddMod(String flagAddMod)
	{
		this.flagAddMod = flagAddMod;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public void validateSave() 
	{
		if ( VOOccupation.getStrOccupationName().length() == 0 ){
			addFieldError("strOccupationName", getText("error.tag.name"));
		}
	}
}
