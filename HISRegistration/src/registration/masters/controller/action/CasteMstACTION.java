/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.CasteMstUTIL;
import vo.registration.CasteVO;


/**
 * @author s.singaravelan
 *
 */
public class CasteMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private CasteVO casteModel=new CasteVO();
	public HttpServletRequest request = null;
	
	
	 public CasteMstACTION() 
	 {
		 super(new CasteMstUTIL(),"Caste","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	
	 public String report(){
		super.REPORT("Caste");
		return "report";
	 } 
		
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public CasteVO getCasteModel() {
		return casteModel;
	}

	public void setCasteModel(CasteVO casteModel) {
		this.casteModel = casteModel;
	}
}
