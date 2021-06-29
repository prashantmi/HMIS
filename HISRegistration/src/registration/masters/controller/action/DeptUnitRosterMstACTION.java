/**
 * 
 */
package registration.masters.controller.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import registration.masters.controller.actionsupport.MasterSUP;
import registration.masters.controller.util.DepartmentMstUTIL;
import registration.masters.controller.util.DeptUnitRoomMstUTIL;
import registration.masters.controller.util.DeptUnitRosterMstUTIL;
import registration.masters.controller.util.StateMstUTIL;
import registration.transactions.controller.util.PatientModificationUTIL;
import vo.registration.DeptUnitRoomVO;
import vo.registration.DeptUnitRosterVO;
import vo.registration.StateVO;

/**
 * @author s.singaravelan
 *
 */
public class DeptUnitRosterMstACTION extends MasterSUP
{
	private String message;
	private DeptUnitRosterVO rosterModel=new DeptUnitRosterVO();
	private String flag;
	
	public String execute() throws Exception
	{
		
		return init();

	}	
	public String init()
	{
		WebUTIL.refreshTransState(super.getRequest(),"DeptUnitRosterMstACTION");		
		//Status status = new Status();
		//status.add(Status.NEW);
		//rosterModel=new DeptUnitRosterVO();
		DeptUnitRosterMstUTIL.getDeptUnitRosterEssentials(super.getRequest(),rosterModel);
		flag="ADD";
		return "add";
	}
	public String putUnit()
	{			
		DeptUnitRosterMstUTIL.getRosterUnitEssentials(super.getRequest(),rosterModel);
		flag="ADD";
		return "add";
	}
	public String putRoom()
	{			
		DeptUnitRosterMstUTIL.getRosterRoomEssentials(super.getRequest(),rosterModel);
		rosterModel.setStrDeptUnitCode(rosterModel.getStrDeptCode()+rosterModel.getStrUnitCode());
		flag="ADD";
		return "add";
	}
	public String putRoster()
	{			
		rosterModel=DeptUnitRosterMstUTIL.getDeptUnitRoomRosterEssentials(super.getRequest(),rosterModel);
		rosterModel.setStrUnitCode(rosterModel.getStrDeptUnitCode().substring(3, 5));
		flag="ROSTER";
		return "add";
	}
	
	public String addRosterRow()
	{			
		DeptUnitRosterMstUTIL.addRosterRow(rosterModel,super.getRequest());
		flag="ROSTER";
		return "add";
	}
	public String removeRosterRow()
	{			
		DeptUnitRosterMstUTIL.removeRosterRow(rosterModel, super.getRequest());
		flag="ROSTER";
		return "add";
	}
	
	public String executeRoster()
	{	
		try{
		DeptUnitRosterMstUTIL.executeRosterForAll(rosterModel, super.getRequest());
		rosterModel.reset();
		flag="ADD";
		message="Roster Generated Successfully";
		}catch(Exception e){
			 e.printStackTrace();
			 message="Application Excecution Error";
		}
		return "add";
	}
	
	public String save()
	{			
		try{
			DeptUnitRosterMstUTIL.save(super.getRequest(),rosterModel, "1");
			rosterModel.reset();
			flag="ADD";
			message="Roster Saved Successfully";
		}catch(Exception e){
			 e.printStackTrace();
			 message="Application Excecution Error";
		}
		return "add";
		 
	}	
	
	public String cancelList()
	{
		return init();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public DeptUnitRosterVO getRosterModel() {
		return rosterModel;
	}

	public void setRosterModel(DeptUnitRosterVO rosterModel) {
		this.rosterModel = rosterModel;
	}
	@Override
	public void setSession(Map arg0) {
		// TODO Auto-generated method stub
		
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	//by Mukund
	public String delete()
	{			
		try{
			DeptUnitRosterMstUTIL.deleteUnitRoster(super.getRequest(),rosterModel, "1");
			rosterModel.reset();
			flag="ADD";
			message="Roster Deleted Successfully";
		}catch(Exception e){
			 e.printStackTrace();
			 message="Application Excecution Error";
		}
		return "add";
		 
	}
		


}
