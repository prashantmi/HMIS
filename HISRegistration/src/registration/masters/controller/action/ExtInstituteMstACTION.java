/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;


import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.ExtInstituteMstUTIL;
import vo.registration.ExtInstituteVO;


/**
 * @author s.singaravelan
 *
 */
public class ExtInstituteMstACTION extends GenericController 
{
	private String message;
	private ExtInstituteVO extInstituteModel=new ExtInstituteVO();
	public HttpServletRequest request = null;
	
	private String flagAddMod;
	
	 public ExtInstituteMstACTION() 
	 {
		 super(new ExtInstituteMstUTIL(),"ExtInstituteMst","registration");
	 }
	 
	 public String execute(){
		super.LIST();
		try{			
		}catch(Exception e){
		e.printStackTrace();
		}		 
		return SUCCESS;
	 }
	
	 public String list(){
		super.LISTPAGE();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}			 
		return null;
	 }
	
	 public String search(){
		super.SEARCH();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}			 
		return null;
	 }
	 
	 public String defaultActive(){
		super.DEFAULT();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return null;
	 }
	 
	 public String delete(){
		super.DELETE();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return null;
	 }
	 
	 public String fetchView(){
		super.VIEWDATA();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return null;
	 } 
	 
	 public String report(){
		super.REPORT("ExtInstituteMst");
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return "report";
	 } 
	 
	 public String reportData(){
		super.REPORTDATA();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return null;
	 } 
	 public String reportInterface(){
		//super.REPORTINTERFACE();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return null;
	 } 
	 
	 public String cancel(){
		super.LIST();
		try{
		}catch(Exception e){
		e.printStackTrace();
		}
		return SUCCESS;
	 }
	 
	 public String add()
	 {
		 extInstituteModel=ExtInstituteMstUTIL.getInstituteEssentials();
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(ExtInstituteMstUTIL.saveExtInstituteDtl(extInstituteModel, "1"))
		 {
			 try
			 {
				extInstituteModel.reset();
				flagAddMod="ADD";
				message="Data added successfully";
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 return "input";
		  }
		 else
		 {
			 this.addActionMessage(extInstituteModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 ExtInstituteMstUTIL obj_DeptMst= new ExtInstituteMstUTIL();
		 ExtInstituteMstUTIL.getInstituteEssentials();
		 extInstituteModel=obj_DeptMst.modifyRecord(request);
		 flagAddMod="MODIFY";
		 
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(ExtInstituteMstUTIL.updateExtInstituteDtl(extInstituteModel, "2"))
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
			 this.addActionMessage(extInstituteModel.getStrWarning());
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

	

	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	public ExtInstituteVO getExtInstituteModel() {
		return extInstituteModel;
	}

	public void setExtInstituteModel(ExtInstituteVO extInstituteModel) {
		this.extInstituteModel = extInstituteModel;
	}





}
