/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.WebUTIL;
import registration.masters.controller.util.DepartmentMstUTIL;
import registration.masters.controller.util.DeptUnitRoomMstUTIL;
import registration.masters.controller.util.StateMstUTIL;
import registration.masters.controller.util.UnitConsultantMstUTIL;
import vo.registration.DeptUnitRoomVO;
import vo.registration.StateVO;
import vo.registration.UnitConsultantVO;

/**
 * @author s.singaravelan
 *
 */
public class UnitConsultantMstACTION extends GenericController 
{
	private String message;
	private UnitConsultantVO unitConsultantModel=new UnitConsultantVO();
	private String flagAddMod;
	public HttpServletRequest request = null;
	
	
	 public UnitConsultantMstACTION() 
	 {
		 super(new UnitConsultantMstUTIL(),"UnitConsultantMst","registration");
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
		super.REPORT("UnitConsultantMst");
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
			 unitConsultantModel=UnitConsultantMstUTIL.getUnitConsultantEssentials(unitConsultantModel);
			 UnitConsultantMstUTIL.getAlreadyAllotedConsultants(unitConsultantModel);
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
			 unitConsultantModel=UnitConsultantMstUTIL.addConsultantUnitRecord(unitConsultantModel);
			 UnitConsultantMstUTIL.getAlreadyAllotedConsultants(unitConsultantModel);
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
			 UnitConsultantMstUTIL.removeUnitConsultantRecord(unitConsultantModel);			 
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(UnitConsultantMstUTIL.saveUnitConsultantDtl(unitConsultantModel, "1"))
		 {
			 try
			 {
				unitConsultantModel.reset();
				flagAddMod="ADD";
				message="Data added successfully";
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 return "input";
		  }
		 else
		 {
			 this.addActionMessage(unitConsultantModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	} 
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 unitConsultantModel=UnitConsultantMstUTIL.modifyRecord(request);
		 UnitConsultantMstUTIL.getAllConsultants(unitConsultantModel);
		 flagAddMod="MODIFY";
		 
		 return "input";
	} 
	 
	 public String update()
	 {
		 if(UnitConsultantMstUTIL.updateUnitConsultantMstDtl(unitConsultantModel, "1"))
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
			 this.addActionMessage(unitConsultantModel.getStrWarning());
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

	public UnitConsultantVO getUnitConsultantModel() {
		return unitConsultantModel;
	}

	public void setUnitConsultantModel(UnitConsultantVO unitConsultantModel) {
		this.unitConsultantModel = unitConsultantModel;
	}
	public void validateSave()
	{
		if(unitConsultantModel.getHierarchyLevel() == null)
		{
				addFieldError("hierarchyLevel", "Hierarchy Level Is Required.");
		}	
		else
		{
			for(int i=0; i<unitConsultantModel.getHierarchyLevel().length;i++)
			{
				if(unitConsultantModel.getHierarchyLevel()[i]==null || unitConsultantModel.getHierarchyLevel()[i].trim().equals(""))
				{
					addFieldError("hierarchyLevel", "Hierarchy Level Is Required.");
					break;
				}
				else
				{
					if(unitConsultantModel.getHierarchyLevel()[i].equals("-1"))
					{
						addFieldError("hierarchyLevel", "Hierarchy Level Is Required.");
						break;
					}					
				}
			}
		}
		UnitConsultantMstUTIL.getAlreadyAllotedConsultants(unitConsultantModel);			
	}
	public void validateUpdate()
	{
		String[] arrayOfHierarchyLevel =unitConsultantModel.getStrHierarchyLevel().split(",");
		for(int i=0; i<arrayOfHierarchyLevel.length;i++)
		{
			if(arrayOfHierarchyLevel[i].trim().equals("-1"))
			{
				addFieldError("strHierarchyLevel", "Hierarchy Level Is Required.");
				break;
			}
		}
	}
}

