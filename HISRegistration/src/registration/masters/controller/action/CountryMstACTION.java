/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.CountryMstUTIL;
import vo.registration.CountryVO;

/**
 * Country Master. Only View and Report is enabled
 * @author s.singaravelan
 */
public class CountryMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private CountryVO countryModel=new CountryVO();
	public HttpServletRequest request = null;
	
	
	 public CountryMstACTION() 
	 {
		 super(new CountryMstUTIL(),"Country","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("Country");
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

	public CountryVO getCountryModel() 
	{
		return countryModel;
	}

	public void setCountryModel(CountryVO countryModel) 
	{
		this.countryModel = countryModel;
	}

}
