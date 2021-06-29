/**
 * 
 */
package registration.masters.controller.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.interceptor.annotations.*;

import hisglobal.masterutil.GenericController;
import registration.config.RegistrationConfig;
import registration.masters.controller.data.UnitMstDATA;
import registration.masters.controller.util.UnitMstUTIL;
import vo.registration.UnitVO;

/**
 * @author s.singaravelan
 *
 */
public class UnitMstACTION extends GenericController 
{
	protected static final long serialVersionUID = 1L;

	private String message;
	private UnitVO unitModel=new UnitVO();
	private String flagAddMod;
	private String flagRenewal;

	private HttpServletRequest request;
	private HttpServletResponse objResponse;
	private ServletContext objContext;
	private Map mapSesion;


	public UnitMstACTION() 
	{
		super(new UnitMstUTIL(),"Unit","registration");
	}

	public String execute()
	{
		super.LIST();
		return SUCCESS;
	}

	public String report()
	{
		super.REPORT("Unit");
		return "report";
	} 
	
	@InputConfig(methodName="input")
	public String add()
	{
		try
		{
			unitModel=UnitMstDATA.getUnitEssentials(unitModel);
			System.out.println("-----"+unitModel.getStrRenewalType()+"-----");
			System.out.println("-----"+unitModel.getStrIsUnit()+"-----");

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		flagAddMod="ADD";
		if(unitModel.getStrRenewalType()!=null && unitModel.getStrRenewalType().equalsIgnoreCase(RegistrationConfig.IS_RENEWAL_TYPE))
		{
			flagRenewal="YES";
		}
		else
		{
			flagRenewal="NO";
			unitModel.setStrIsExpiry(RegistrationConfig.IS_DEPT_UNIT);
		}
		System.out.println("------"+flagRenewal+"-------");

		return "input";
	}

	public String save()
	{
		if(UnitMstDATA.saveUnitDtl(unitModel, "1"))
		{
			try
			{
				unitModel.reset();
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
			this.addActionMessage(unitModel.getStrWarning());
			flagAddMod="ADD";
			return "input";			 
		}
	}

	public String modify()
	{
		HttpServletRequest request= super.getRequest();
		//if(unitModel==null)
			unitModel=UnitMstDATA.modifyRecord(request);
		/*else
			unitModel=UnitMstDATA.getRenewalRecord(unitModel);*/
		flagAddMod="MODIFY";

		if(unitModel.getStrRenewalType()!=null && unitModel.getStrRenewalType().equalsIgnoreCase(RegistrationConfig.IS_RENEWAL_TYPE))
			flagRenewal="YES";
		else
		{
			flagRenewal="NO";
			unitModel.setStrIsExpiry(RegistrationConfig.IS_DEPT_UNIT);
		}

		System.out.println("------"+flagRenewal+"-------");

		return "input";
	}
	
	public String update()
	{
		if(UnitMstDATA.updateUnitDtl(unitModel, "2"))
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
			this.addActionMessage(unitModel.getStrWarning());
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

	public UnitVO getUnitModel() 
	{
		return unitModel;
	}

	public void setUnitModel(UnitVO unitModel)
	{
		this.unitModel = unitModel;
	}

	public String getFlagRenewal() 
	{
		return flagRenewal;
	}

	public void setFlagRenewal(String flagRenewal) 
	{
		this.flagRenewal = flagRenewal;
	}



}
