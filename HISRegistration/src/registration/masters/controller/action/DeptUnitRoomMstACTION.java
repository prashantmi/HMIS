/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.DepartmentMstUTIL;
import registration.masters.controller.util.DeptUnitRoomMstUTIL;
import registration.masters.controller.util.StateMstUTIL;
import vo.registration.DeptUnitRoomVO;
import vo.registration.StateVO;

/**
 * @author s.singaravelan
 *
 */
public class DeptUnitRoomMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private DeptUnitRoomVO deptUnitRoomModel=new DeptUnitRoomVO();
	private String flagAddMod;
	public HttpServletRequest request = null;
	
	
	 public DeptUnitRoomMstACTION() 
	 {
		 super(new DeptUnitRoomMstUTIL(),"DeptUnitRoomMst","registration");
	 }
	 
	 public String execute(){
		super.LIST();
		return SUCCESS;
	 }
	
	 public String list(){
		super.LISTPAGE();
		return null;
	 }
	
	 public String search(){
		super.SEARCH();
		return null;
	 }
	 
	 public String defaultActive(){
		super.DEFAULT();
		return null;
	 }
	 
	 public String delete(){
		super.DELETE();
		return null;
	 }
	 
	 public String fetchView(){
		super.VIEWDATA();
		return null;
	 } 
	 
	 public String report(){
		super.REPORT("DeptUnitRoomMst");
		return "report";
	 } 
	 
	 public String reportData(){
		super.REPORTDATA();
		return null;
	 } 
	 public String reportInterface(){
		//super.REPORTINTERFACE();
		return null;
	 } 
	 
	 public String cancel(){
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String add()
	 {
		 try{
			 deptUnitRoomModel=DeptUnitRoomMstUTIL.getDeptUnitRoomEssentials(deptUnitRoomModel);
			 DeptUnitRoomMstUTIL.getAlreadyAllotedRooms(deptUnitRoomModel);
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 return "input";
	}
	 
	 public String put()
	 {
		 try{
			 deptUnitRoomModel=DeptUnitRoomMstUTIL.addRoomDeptUnitRecord(deptUnitRoomModel);
			 DeptUnitRoomMstUTIL.getAlreadyAllotedRooms(deptUnitRoomModel);
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String remove()
	 {
		 try{
			 DeptUnitRoomMstUTIL.removeRoomDeptUnitRecord(deptUnitRoomModel);			 
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(DeptUnitRoomMstUTIL.saveDeptUnitRoomDtl(deptUnitRoomModel, "1"))
		 {
			 try
			 {
				deptUnitRoomModel.reset();
				add();
				flagAddMod="ADD";
				message="Data added successfully";
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 return "input";
		  }
		 else
		 {
			 this.addActionMessage(deptUnitRoomModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	} 
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 deptUnitRoomModel=DeptUnitRoomMstUTIL.modifyRecord(request);
		 flagAddMod="MODIFY";
		 
		 return "input";
	} 
	 
	 public String update()
	 {
		 if(DeptUnitRoomMstUTIL.updateDeptUnitRoomMstDtl(deptUnitRoomModel, "1"))
		 {
			 try
			 {
				 super.LIST();
				 message="Data modified successfully";
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 return SUCCESS;
		 }
		 else
		 {
			 this.addActionMessage(deptUnitRoomModel.getStrWarning());
			 flagAddMod="MODIFY";
			 return "input";
		 }
		 
	}
		
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public DeptUnitRoomVO getDeptUnitRoomModel() {
		return deptUnitRoomModel;
	}

	public void setDeptUnitRoomModel(DeptUnitRoomVO deptUnitRoomModel) {
		this.deptUnitRoomModel = deptUnitRoomModel;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}
	public void validateSave() 
	{
		
		if(deptUnitRoomModel.getCapacity() != null)
		if (deptUnitRoomModel.getCapacity().length == 0 )
		{
			addFieldError("capacity", "Room/Room Capacity Is Required");
		}
		
		for(int i=0;i<deptUnitRoomModel.getCapacity().length;i++)
		{
			
			if(deptUnitRoomModel.getCapacity()[i]==null || deptUnitRoomModel.getCapacity()[i].trim().equals(""))
			{
				addFieldError("capacity", "Capacity Is Required");
				break;
			}
			else
			{
					if(!deptUnitRoomModel.getCapacity()[i].matches("^[1-9][0-9]{0,1}$"))
					{
						addFieldError("capacity", "Capacity Is Required. It Excepts Numbers Only");
					}					
			}
		}
	}	
}
