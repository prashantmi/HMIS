/**
 * 
 */
package registration.masters.controller.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.DepartmentMstDATA;
import registration.masters.controller.util.DepartmentMstUTIL;
import vo.registration.DepartmentVO;

/**
 * @author s.singaravelan
 *
 */
public class DepartmentMstACTION extends GenericController 
{
	protected static final long serialVersionUID = 1L;

	private String message;
	private DepartmentVO deptModel=new DepartmentVO();
	private String flagAddMod;
	private String flagGlobal;
	
	private HttpServletRequest request;
	private HttpServletResponse objResponse;
	private ServletContext objContext;
	private Map mapSesion;
	
	
	 public DepartmentMstACTION() 
	 {
		 super(new DepartmentMstUTIL(),"LocalDepartment","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	
	 
	 public String report()
	 {
		super.REPORT("LocalDepartment");
		return "report";
	 } 

	 public String add()
	 {
		 try
		 {
			 DepartmentMstDATA.getDepartmentEssentialsLocal();
		 }
		 catch (HisRecordNotFoundException e)
		 {
			e.printStackTrace();
			this.addActionError(e.getMessage());

		 }
		 catch (Exception e)
		 {
			e.printStackTrace();
			this.addActionError(e.getMessage());
		 }
		 flagAddMod="ADD";
		 flagGlobal="NO";
		 return "input";
	 }
	 
	 public String put()
	 {
		 try
		 {
			 HttpServletRequest request= super.getRequest();
			 deptModel=DepartmentMstDATA.addDeptRecord(deptModel,request);			 
		 }
		 catch (Exception e)
		 {
			e.printStackTrace();
		}
		 flagAddMod="ADD";
		 flagGlobal="NO";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(DepartmentMstDATA.saveDepartmentDtlLocal(deptModel, "1"))
		 {
			 try
			 {
				deptModel.reset();
				DepartmentMstDATA.getDepartmentEssentialsLocal();
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
			 this.addActionMessage(deptModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 DepartmentMstDATA.getDepartmentEssentialsLocal();	 
		 deptModel=DepartmentMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";
		 flagGlobal="NO";
		 
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(DepartmentMstDATA.updateDepartmentDtl(deptModel, "2"))
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
			 this.addActionMessage(deptModel.getStrWarning());
			 flagAddMod="MODIFY";
			 return "input";
		 }
		 
	}
		
	public String getMessage() 
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}

	

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}


	public String getFlagAddMod() 
	{
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod)
	{
		this.flagAddMod = flagAddMod;
	}

	

	public String getFlagGlobal() 
	{
		return flagGlobal;
	}

	public void setFlagGlobal(String flagGlobalHospital)
	{
		this.flagGlobal = flagGlobalHospital;
	}

	public HttpServletResponse getObjResponse()
	{
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse)
	{
		this.objResponse = objResponse;
	}

	public ServletContext getObjContext() 
	{
		return objContext;
	}

	public void setObjContext(ServletContext objContext) 
	{
		this.objContext = objContext;
	}

	public Map getMapSesion() 
	{
		return mapSesion;
	}

	public void setMapSesion(Map mapSesion) 
	{
		this.mapSesion = mapSesion;
	}

	public DepartmentVO getDeptModel()
	{
		return deptModel;
	}

	public void setDeptModel(DepartmentVO deptModel) 
	{
		this.deptModel = deptModel;
	}


}
