/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.LanguageMstUTIL;
import vo.registration.LanguageVO;

/**
 * @author s.singaravelan
 *
 */
public class LanguageMstACTION extends GenericController 
{
	private String message;
	private LanguageVO languageModel=new LanguageVO();
	public HttpServletRequest request = null;
	
	
	 public LanguageMstACTION() 
	 {
		 super(new LanguageMstUTIL(),"Language","registration");
	 }
	 
	 public String execute(){
		super.LIST();
		try{			
		}catch(Exception e){
		e.printStackTrace();
		}		 
		return SUCCESS;
	 }
	
	 
	 public String report()
	 {
		super.REPORT("Language");
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

	public HttpServletRequest getRequest() 
	{
		return request;
	}

	public void setRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public LanguageVO getLanguageModel() 
	{
		return languageModel;
	}

	public void setLanguageModel(LanguageVO languageModel) 
	{
		this.languageModel = languageModel;
	}

	


}
