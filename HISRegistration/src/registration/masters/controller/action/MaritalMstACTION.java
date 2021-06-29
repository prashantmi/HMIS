/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.MaritalMstUTIL;
import vo.registration.MaritalVO;


/**
 * @author s.singaravelan
 *
 */
public class MaritalMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private MaritalVO maritalModel=new MaritalVO();
	public HttpServletRequest request = null;
	
	
	 public MaritalMstACTION() 
	 {
		 super(new MaritalMstUTIL(),"MaritalStatus","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		try{			
		}catch(Exception e){
		e.printStackTrace();
		}		 
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("MaritalStatus");
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
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

	public MaritalVO getMaritalModel()
	{
		return maritalModel;
	}

	public void setMaritalModel(MaritalVO maritalModel)
	{
		this.maritalModel = maritalModel;
	}

	




}
