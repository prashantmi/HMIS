/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.GenderMstUTIL;
import vo.registration.GenderVO;


/**
 * @author s.singaravelan
 *
 */
public class GenderMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private GenderVO genderModel=new GenderVO();
	public HttpServletRequest request = null;
	
	
	 public GenderMstACTION() 
	 {
		 super(new GenderMstUTIL(),"Gender","registration");
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
		super.REPORT("Gender");
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return "report";
	 } 
		
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public GenderVO getGenderModel() {
		return genderModel;
	}

	public void setGenderModel(GenderVO genderModel) {
		this.genderModel = genderModel;
	}

	




}
