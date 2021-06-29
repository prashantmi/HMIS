/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.DistrictMstUTIL;
import vo.registration.DistrictVO;

/**
 * @author s.singaravelan
 *
 */
public class DistrictMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private DistrictVO districtModel=new DistrictVO();
	public HttpServletRequest request = null;
	
	
	 public DistrictMstACTION() 
	 {
		 super(new DistrictMstUTIL(),"District","registration");
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
		super.REPORT("District");
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

	public DistrictVO getDistrictModel() {
		return districtModel;
	}

	public void setDistrictModel(DistrictVO districtModel) {
		this.districtModel = districtModel;
	}

}
