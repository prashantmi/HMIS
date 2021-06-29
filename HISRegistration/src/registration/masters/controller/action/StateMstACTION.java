/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.StateMstUTIL;
import vo.registration.StateVO;

/**
 * @author s.singaravelan
 *
 */
public class StateMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private StateVO stateModel=new StateVO();
	public HttpServletRequest request = null;
	
	
	 public StateMstACTION() 
	 {
		 super(new StateMstUTIL(),"State","registration");
	 }
	 
	 public String execute(){
		super.LIST();
		return SUCCESS;
	 }
	
	 public String report(){
		super.REPORT("State");
		return "report";
	 } 
	 
	public String getMessage() 
	{
		return message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public StateVO getStateModel() 
	{
		return stateModel;
	}

	public void setStateModel(StateVO stateModel)
	{
		this.stateModel = stateModel;
	}

}
