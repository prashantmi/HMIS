/*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
  ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	Emergency Case
 ## Process/Database Object Name		:   Emergency Case Master
 ## Purpose								:	This master is used to capture the Emergency Case used for investigation Process
 ## Date of Creation					:   
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;





import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.EmgCaseMstDATA;
import registration.masters.controller.data.LocationMstDATA;
import registration.masters.controller.util.EmgCaseMstUTIL;
import vo.registration.EmgCaseVO;


/**
 * @author s.singaravelan
 *
 */
public class EmgCaseMstACTION extends GenericController 
{
	private String message;
	private EmgCaseVO emgCaseModel=new EmgCaseVO();
	public HttpServletRequest request = null;
	
	private String flagAddMod;
	
	 public EmgCaseMstACTION() 
	 {
		 super(new EmgCaseMstUTIL(),"EmgCaseMst","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("EmgCaseMst");
		return "report";
	 } 
	 
	 public String cancel()
	 {
		 super.LIST();
		 return SUCCESS;
	 } 
	 
	 public String cancelList()
	 {
		 //super.LIST();
		 return "cancel";
	 } 
	 
	 public String add()
	 {
		
		 emgCaseModel=new EmgCaseVO();
		 emgCaseModel.setStrIsMlcRequired("1");
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(EmgCaseMstDATA.saveEmgCaseDtl(emgCaseModel, "1"))
		 {
			 try
			 {
				emgCaseModel.reset();
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
			 this.addActionMessage(emgCaseModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 emgCaseModel=EmgCaseMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(EmgCaseMstDATA.updateEmgCaseDtl(emgCaseModel, "2"))
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
			 return "success" ;
		 }
		 else
		 {
			 this.addActionMessage(emgCaseModel.getStrWarning());
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

	public EmgCaseVO getemgCaseModel() 
	{
		return emgCaseModel;
	}

	public void setemgCaseModel(EmgCaseVO emgCaseModel) 
	{
		this.emgCaseModel = emgCaseModel;
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
