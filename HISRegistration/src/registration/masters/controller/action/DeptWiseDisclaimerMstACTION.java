/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.config.RegistrationConfig;
import registration.masters.controller.data.DepartmentMstDATA;
import registration.masters.controller.data.DisclaimerMstDATA;
import registration.masters.controller.util.DeptWiseDisclaimerMstUTIL;
import vo.registration.DisclaimerVO;

/**
 * @author s.singaravelan
 *
 */
public class DeptWiseDisclaimerMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private DisclaimerVO disclaimerModel=new DisclaimerVO();
	private String flagAddMod;
	private String flagDept;
	public HttpServletRequest request = null;
	
	
	 public DeptWiseDisclaimerMstACTION() 
	 {
		 super(new DeptWiseDisclaimerMstUTIL(),"DeptWiseDisclaimerMst","registration");
	 }
	 
	 public String execute(){
		disclaimerModel=new DisclaimerVO();
		disclaimerModel.setStrSelectDisclaimer("DEPT");
		super.LIST();
		try{			
		}catch(Exception e){
		e.printStackTrace();
		}		 
		return SUCCESS;
	 }
	 
	 public String report(){
		super.REPORT("DeptWiseDisclaimerMst");
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return "report";
	 } 
	 
	 public String cancel(){
		 disclaimerModel=new DisclaimerVO();
		 disclaimerModel.setStrSelectDisclaimer("DEPT");
		 return super.LIST();
	 }
	 
	 public String add()
	 {
		 try
		 {
			 disclaimerModel=DisclaimerMstDATA.getDeptDisclaimerEssentials(super.getRequest());
			 disclaimerModel.setStrIsHeader(RegistrationConfig.IS_REFERRED_FALSE);
			 disclaimerModel.setStrSelectDisclaimer("DEPT");
		 }
		 catch (Exception e)
		 {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 flagDept="YES";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(DisclaimerMstDATA.saveDisclaimerDtl(disclaimerModel, "1",super.getRequest()))
		 {
			 try
			 {
				disclaimerModel.reset();
				DisclaimerMstDATA.getDeptDisclaimerEssentials(super.getRequest());
				flagAddMod="ADD";
				flagDept="YES";
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
			 this.addActionMessage(disclaimerModel.getStrWarning());
			 flagAddMod="ADD";
			 flagDept="YES";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 DisclaimerMstDATA.getDeptDisclaimerEssentials(request);
		 disclaimerModel=DisclaimerMstDATA.modifyRecord(request);
		 disclaimerModel.setStrSelectDisclaimer("DEPT");
		 flagAddMod="MODIFY";
		 flagDept="YES";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(DisclaimerMstDATA.updateDisclaimerDtl(disclaimerModel, "2",super.getRequest()))
		 {
			 try
			 {
				 disclaimerModel.setStrSelectDisclaimer("DEPT");
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
			 this.addActionMessage(disclaimerModel.getStrWarning());
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
