/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.VerificationDocMstDATA;
import registration.masters.controller.util.VerificationDocMstUTIL;
import vo.registration.VerificationDocVO;

/**
 * @author s.singaravelan
 *
 */
public class VerificationDocMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private VerificationDocVO veriDocModel=new VerificationDocVO();
	private String flagAddMod;

	public HttpServletRequest request;


	public VerificationDocMstACTION() 
	{
		super(new VerificationDocMstUTIL(),"VerificationDoc","registration");
	}

	public String execute()
	{
		super.LIST();
		try{			
		}catch(Exception e){
			e.printStackTrace();
		}		 
		return SUCCESS;
	}

	public String report()
	{
		super.REPORT("VerificationDoc");
		try{
		}catch(Exception e){
			e.printStackTrace();
		}
		return "report";
	} 

	public String add()
	{
		flagAddMod="ADD";
		return "input";
	}

	public String save()
	{
		if(VerificationDocMstDATA.saveVerificationDocDtl(super.getRequest(),veriDocModel, "1"))
		{
			try
			{
				//super.LIST();
				veriDocModel.reset();
				flagAddMod="ADD";
				message="Data added successfully";
				veriDocModel.setStrMsg("abc");
			}catch(Exception e){
				e.printStackTrace();
			}
			return "input";
		}
		else
		{
			veriDocModel.setStrWarning(veriDocModel.getStrWarning());
			this.addActionMessage(veriDocModel.getStrWarning());
			flagAddMod="ADD";
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


	public VerificationDocVO getVeriDocModel() 
	{
		return veriDocModel;
	}

	public void setVeriDocModel(VerificationDocVO veriDocModel) 
	{
		this.veriDocModel = veriDocModel;
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
