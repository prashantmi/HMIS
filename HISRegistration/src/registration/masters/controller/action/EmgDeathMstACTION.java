/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;



import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.EmgDeathMstDATA;
import registration.masters.controller.util.EmgDeathMstUTIL;
import vo.registration.EmgDeathMstVO;


/**
 * @author s.singaravelan
 *
 */
public class EmgDeathMstACTION extends GenericController 
{
	private String message;
	private EmgDeathMstVO emgDeathModel=null;
	//public HttpServletRequest request = null;
	
	private String flagAddMod;
	
	 public EmgDeathMstACTION() 
	 {
		 super(new EmgDeathMstUTIL(),"EmgDeathMst","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("EmgDeathMst");
		return "report";
	 } 
	 

	 
	 public String add()
	 {
		 emgDeathModel=new EmgDeathMstVO();
		 emgDeathModel.setStrIsPostMortem("0");
		 emgDeathModel.setStrIsAccidental("0");
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(EmgDeathMstDATA.saveEmgDeathDtl(emgDeathModel, "1"))
		 {
			 try
			 {
				//emgDeathModel.reset();
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
			 this.addActionMessage(emgDeathModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 emgDeathModel=EmgDeathMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(EmgDeathMstDATA.updateEmgDeathDtl(emgDeathModel, "2"))
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
			 this.addActionMessage(emgDeathModel.getStrWarning());
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

	public EmgDeathMstVO getEmgDeathModel() 
	{
		return emgDeathModel;
	}

	public void setEmgDeathModel(EmgDeathMstVO emgDeathModel) 
	{
		this.emgDeathModel = emgDeathModel;
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
