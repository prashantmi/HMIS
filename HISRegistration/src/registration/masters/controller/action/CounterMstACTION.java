/**
 * 
 */
package registration.masters.controller.action;

import java.util.Map;

import hisglobal.masterutil.GenericController;

import javax.servlet.http.HttpServletRequest;

import registration.config.RegistrationConfig;
import registration.masters.controller.data.CounterMstDATA;
import registration.masters.controller.util.CounterMstUTIL;
import vo.registration.CounterVO;


/**
 * @author s.singaravelan
 *
 */
public class CounterMstACTION extends GenericController 
{
	protected static final long serialVersionUID = 1L;

	private String message;
	private CounterVO counterModel=new CounterVO();
	private String flagAddMod;
	public HttpServletRequest request = null;	

	
	 public CounterMstACTION() 
	 {
		 super(new CounterMstUTIL(),"CounterMst","registration");
	 }
	 
	 public String execute(){
		super.LIST();
		try{			
		}catch(Exception e){
		e.printStackTrace();
		}		 
		return SUCCESS;
	 }

	 
	 public String report(){
		super.REPORT("CounterMst");
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return "report";
	 } 
	 
	 public String add()
	 {
		 try
		 {
			CounterMstDATA.getCounterMstEssentials(super.getRequest());
			
		 }
		 catch (Exception e)
		 {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(CounterMstDATA.saveCounterDtl(counterModel, "1",super.getRequest()))
		 {
			 try
			 {
				counterModel.reset();
				CounterMstDATA.getCounterMstEssentials(super.getRequest());
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
			 this.addActionMessage(counterModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		try
		{
			HttpServletRequest request= super.getRequest();
			CounterMstDATA.getCounterMstEssentials(super.getRequest());
			counterModel=CounterMstDATA.modifyRecord(request);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		flagAddMod="MODIFY";
		return "input";
	 }
	 
	  
	 public String update()
	 {
		 if(CounterMstDATA.updateCounterDtl(counterModel, "2",super.getRequest()))
		 {
			 try
			 {
				//CounterMstDATA.getCounterMstEssentials(super.getRequest());
				//flagAddMod="MODIFY";
				//message="Data modified successfully";
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
			 this.addActionMessage(counterModel.getStrWarning());
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

	
	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	
	public CounterVO getCounterModel() {
		return counterModel;
	}

	public void setCounterModel(CounterVO counterModel) {
		this.counterModel = counterModel;
	}

	


}
