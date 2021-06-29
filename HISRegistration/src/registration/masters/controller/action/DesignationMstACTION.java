/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.DesignationMstUTIL;
import vo.registration.DesignationVO;

/**
 * @author s.singaravelan
 *
 */
public class DesignationMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private DesignationVO designationModel=new DesignationVO();
	public HttpServletRequest request = null;
	
	
	 public DesignationMstACTION() 
	 {
		 super(new DesignationMstUTIL(),"Designation","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }

	 public String report()
	 {
		super.REPORT("Designation");
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

	public DesignationVO getDesignationModel() 
	{
		return designationModel;
	}

	public void setDesignationModel(DesignationVO designationModel) 
	{
		this.designationModel = designationModel;
	}

}
