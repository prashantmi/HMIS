/**
 * 
 */
package registration.masters.controller.action;

import java.util.Map;

import hisglobal.masterutil.GenericController;

import javax.servlet.http.HttpServletRequest;

import registration.config.RegistrationConfig;
import registration.masters.controller.data.DisclaimerMstDATA;
import registration.masters.controller.util.DefaultDisclaimerMstUTIL;
import vo.registration.DisclaimerVO;


/**
 * @author s.singaravelan
 *
 */
public class DefaultDisclaimerMstACTION extends GenericController 
{
	protected static final long serialVersionUID = 1L;

	private String message;
	private DisclaimerVO disclaimerModel=new DisclaimerVO();
	private String flagAddMod;
	private String flagDept;
	public HttpServletRequest request = null;	

	
	 public DefaultDisclaimerMstACTION() 
	 {
		 super(new DefaultDisclaimerMstUTIL(),"DefaultDisclaimerMst","registration");
	 }
	 
	 public String execute(){
		disclaimerModel=new DisclaimerVO();
		disclaimerModel.setStrSelectDisclaimer("DEFAULT");
		super.LIST();
		try{			
		}catch(Exception e){
		e.printStackTrace();
		}		 
		return SUCCESS;
	 }
	 
	 public String report(){
		super.REPORT("DefaultDisclaimerMst");
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return "report";
	 } 
	 
	 public String modify()
	 {
		try
		{
			HttpServletRequest request= super.getRequest();
			disclaimerModel=DisclaimerMstDATA.getDefaultDisclaimerList(request);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		flagAddMod="MODIFY";
		return "input";
	 }
	 
	  
	 public String update()
	 {
		 if(DisclaimerMstDATA.updateDefaultDisclaimerDtl(disclaimerModel, "1",super.getRequest()))
		 {
			 try
			 {
				DisclaimerMstDATA.getDefaultDisclaimerList(super.getRequest());
				flagAddMod="MODIFY";
				message="Data modified successfully";
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 return "input";
		  }
		 else
		 {
			 this.addActionMessage(disclaimerModel.getStrWarning());
			 flagAddMod="MODIFY";
			 return "input";
		 }
	 }
	 
	public String DEFAULT()
	{
		return "default";
	}
	public String DEPT()
	{
		return "dept";
	}
	public String DEPTUNIT()
	{
		return "unit";
	}
		
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public DisclaimerVO getDisclaimerModel() {
		return disclaimerModel;
	}

	public void setDisclaimerModel(DisclaimerVO disclaimerModel) {
		this.disclaimerModel = disclaimerModel;
	}

	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	public String getFlagDept() {
		return flagDept;
	}

	public void setFlagDept(String flagDept) {
		this.flagDept = flagDept;
	}

	


}
