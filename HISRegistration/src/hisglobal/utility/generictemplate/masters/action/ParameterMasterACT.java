/**
 * 
 */
package hisglobal.utility.generictemplate.masters.action;

import hisglobal.masterutil.GenericController;
import hisglobal.utility.generictemplate.masters.data.ParameterMstDATA;
import hisglobal.utility.generictemplate.masters.utl.ParameterMasterUTL;
import hisglobal.vo.ParameterMasterVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Parameter Master Only View and Report is enabled
 * @author s.singaravelan
 */
public class ParameterMasterACT extends GenericController 
{
	
	private static final long serialVersionUID = 1L;
	private String message;
	private String flagAddMod;
	private ParameterMasterVO paraMasterVo;
	public HttpServletRequest request = null;
	
	
	 public ParameterMasterACT() 
	 {
		 super(new ParameterMasterUTL(),"ParameterMaster","generictemplate");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		System.out.println("para_mst_act list.......");
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("ParameterMaster");
		return "report";
	 } 
		
	 
	 	public String add()
		{
	 		System.out.println("inside add");
			this.flagAddMod="ADD";
			return "input";
		}

		public String save()
		{
			if(ParameterMstDATA.saveParameterMasterDtl(super.getRequest(), paraMasterVo, "1")){
				try
				{
					super.LIST();
					paraMasterVo.setParaName("");
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
				paraMasterVo.setStrWarning(paraMasterVo.getStrWarning());
				this.addActionMessage(paraMasterVo.getStrWarning());
				flagAddMod="ADD";
				return "input";
			}
		}
		public String modify()
		{
			HttpServletRequest request= super.getRequest();
			//paraMasterVo=ParameterMstDATA.modifyRecord(request,paraMasterVo);
			flagAddMod="MODIFY";
			//System.out.println("inside modify");

			return "input";
		}
		public String update()
		{
			if(ParameterMstDATA.updateParameterMasterDtl(super.getRequest(),paraMasterVo, "2")){
				try{
					super.LIST();
					message="Data modified successfully";
				}catch(Exception e){
					e.printStackTrace();
				}
				return SUCCESS;
			}
			else{
				message="Data cannot be modified";
				this.addActionMessage(paraMasterVo.getStrWarning());
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

	public ParameterMasterVO setparaMasterVo() 
	{
		return paraMasterVo;
	}

	public void getparaMasterVo(ParameterMasterVO paraMasterVo) 
	{
		this.paraMasterVo = paraMasterVo;
	}

	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	public ParameterMasterVO getParaMasterVo() {
		return paraMasterVo;
	}

	public void setParaMasterVo(ParameterMasterVO paraMasterVo) {
		this.paraMasterVo = paraMasterVo;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
