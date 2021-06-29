/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.config.RegistrationConfig;
import registration.masters.controller.data.DisclaimerMstDATA;
import registration.masters.controller.util.DistrictMstUTIL;
import registration.masters.controller.util.UnitWiseDisclaimerMstUTIL;
import vo.registration.DisclaimerVO;
import vo.registration.DistrictVO;

/**
 * @author s.singaravelan
 *
 */
public class UnitWiseDisclaimerMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private DisclaimerVO disclaimerModel=new DisclaimerVO();
	public HttpServletRequest request = null;
	private String flagDept;
	private String flagAddMod;	
	
	 public UnitWiseDisclaimerMstACTION() 
	 {
		 super(new UnitWiseDisclaimerMstUTIL(),"UnitWiseDisclaimerMst","registration");
	 }
	 
	 public String execute(){
		disclaimerModel=new DisclaimerVO();
		disclaimerModel.setStrSelectDisclaimer("DEPTUNIT");
		super.LIST();
		try{			
		}catch(Exception e){
		e.printStackTrace();
		}		 
		return SUCCESS;
	 }
	 
	 public String report(){
		super.REPORT("UnitWiseDisclaimerMst");
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
			 disclaimerModel= DisclaimerMstDATA.getUnitDisclaimerEssentials(super.getRequest());
			 disclaimerModel.setStrIsHeader(RegistrationConfig.IS_REFERRED_FALSE);
			 disclaimerModel.setStrSelectDisclaimer("DEPTUNIT");
		 }
		 catch (Exception e)
		 {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 flagDept="NO";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(DisclaimerMstDATA.saveDisclaimerDtl(disclaimerModel, "1",super.getRequest()))
		 {
			 try
			 {
				disclaimerModel.reset();
				DisclaimerMstDATA.getUnitDisclaimerEssentials(super.getRequest());
				flagAddMod="ADD";
				flagDept="NO";
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
			 flagDept="NO";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 DisclaimerMstDATA.getUnitDisclaimerEssentials(request);
		 disclaimerModel=DisclaimerMstDATA.modifyRecord(request);
		 disclaimerModel.setStrSelectDisclaimer("DEPTUNIT");
		 flagAddMod="MODIFY";
		 flagDept="NO";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(DisclaimerMstDATA.updateDisclaimerDtl(disclaimerModel, "2",super.getRequest()))
		 {
			 try
			 {
    			 disclaimerModel.setStrSelectDisclaimer("DEPTUNIT");
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

	public String getFlagDept() {
		return flagDept;
	}

	public void setFlagDept(String flagDept) {
		this.flagDept = flagDept;
	}

	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	

}
