/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;




import javax.servlet.http.HttpServletResponse;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.PoliceStationMstDATA;
import registration.masters.controller.data.UnitMstDATA;
import registration.masters.controller.util.PoliceStationMstUTIL;
import registration.reports.controller.util.PatientListReportUTIL;
import vo.registration.PoliceStationMstVO;


/**
 * @author s.singaravelan
 *
 */
public class PoliceStationMstACTION extends GenericController 
{
	private String message;
	private PoliceStationMstVO policeStationModel=new PoliceStationMstVO();
	public HttpServletRequest request = null;
	public HttpServletResponse response = null;

	private String flagAddMod;
	
	 public PoliceStationMstACTION() 
	 {
		 super(new PoliceStationMstUTIL(),"PoliceStationMst","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("PoliceStationMst");
		return "report";
	 } 	 

	 
	 public String add()
	 {
		 policeStationModel=PoliceStationMstDATA.getEssentials(policeStationModel);	 
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(PoliceStationMstDATA.savePoliceStationDtl(policeStationModel, "1"))
		 {
			 try
			 {
				policeStationModel.reset();
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
			 this.addActionMessage(policeStationModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 policeStationModel=PoliceStationMstDATA.modifyRecord(request);
		 policeStationModel=PoliceStationMstDATA.getEssentials(policeStationModel);	
		 flagAddMod="MODIFY";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(PoliceStationMstDATA.updatePoliceStationDtl(policeStationModel, "2"))
		 {
			 try
			 {
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
			 this.addActionMessage(policeStationModel.getStrWarning());
			 flagAddMod="MODIFY";
			 return "input";
		 }
		 
	}
	
	 public void getDistrict() {
		   
			System.out.println("Police Station Mst :: getDistrict()");
			message = "Inside getDistrict() method";			
			PoliceStationMstDATA.getDistrict_AJAX(super.getRequest(),super.getResponse());
	}	
		
	public String getMessage() 
	{
		return message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public PoliceStationMstVO getPoliceStationModel() 
	{
		return policeStationModel;
	}

	public void setPoliceStationModel(PoliceStationMstVO policeStationModel) 
	{
		this.policeStationModel = policeStationModel;
	}

	public String getFlagAddMod()
	{
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod)
	{
		this.flagAddMod = flagAddMod;
	}

}
