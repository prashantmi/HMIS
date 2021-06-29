/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.ReligionMstUTIL;
import vo.registration.ReligionVO;

/**
 * @author s.singaravelan
 *
 */
public class ReligionMstACTION extends GenericController 
{
	
	private static final long serialVersionUID = 1L;
	private String message;
	private ReligionVO religionModel=new ReligionVO();
	public HttpServletRequest request=null;


	public ReligionMstACTION() 
	{
		super(new ReligionMstUTIL(),"Religion","registration");
	}

	public String execute()
	{
		super.LIST();
		return SUCCESS;
	}

	public String report()
	{
		super.REPORT("Religion");
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

	public ReligionVO getReligionModel() 
	{
		return religionModel;
	}

	public void setReligionModel(ReligionVO religionModel) 
	{
		this.religionModel = religionModel;
	}

	public HttpServletRequest getRequest() 
	{
		return request;
	}

	public void setRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

}
