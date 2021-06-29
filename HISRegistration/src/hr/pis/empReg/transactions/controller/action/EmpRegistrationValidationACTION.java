package hr.pis.empReg.transactions.controller.action;

import hisglobal.masterutil.GenericController;
import hr.pis.empReg.transactions.controller.data.EmpRegistrationValidationDATA;
import hr.pis.empReg.transactions.controller.util.EmpRegistrationValidationUTIL;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;
//import registration.masters.controller.util.QualityTestMstUTIL;
//import registration.masters.controller.fb.QualityTestMstFB;
//import hisglobal.presentation.WebUTIL;

public class EmpRegistrationValidationACTION extends GenericController {
	
	private String message;

	private EmpRegistrationVO VOEmpReg;

	public EmpRegistrationVO getVOEmpReg() {
		return VOEmpReg;
	}

	public void setVOEmpReg(EmpRegistrationVO vOEmpReg) {
		VOEmpReg = vOEmpReg;
	}

	private static final long serialVersionUID = 1L;
	/** The strTarget. */
	String strTarget = "";
	private String flagAddMod;
	//public HttpServletRequest request = null;

	/**
	 * calls super class constructor.
	 */
	public EmpRegistrationValidationACTION() {
		super(new EmpRegistrationValidationUTIL(), "EmployeeRegistrationValidation", "pis");
	}

	public String execute() {
		setCharacterEncoding();
		super.LIST();

		return SUCCESS;
	}

	public String list() {
		setCharacterEncoding();
		super.LISTPAGE();

		return null;
	}

	public String search() {
		super.SEARCH();

		return null;
	}

	public String report() {
		super.REPORT("EmployeeRegistrationValidation");

		return "report";
	}

	public String add() {
		flagAddMod = "ADD";
		return "input";
	}

	public String modify() {
		
		HttpServletRequest request = super.getRequest();
		//request.setAttribute("combo", request.getParameterValues("combo"));
		VOEmpReg = EmpRegistrationValidationDATA.modifyRecord(super.getRequest(), VOEmpReg);
		flagAddMod = "MODIFY";
		return "input";
	}

	
	public String save() {
		
		if (EmpRegistrationValidationDATA.validateEmpRegDtl(super.getRequest(), VOEmpReg, "1")) {
			try {
				super.LIST();
				message = "Employee details validated successfully";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		} else {
			message = "Employee details cannot be validated";
			this.addActionMessage(VOEmpReg.getStrWarning());
			flagAddMod = "MODIFY";
			return "input";
		}

	}
	
	public void setCharacterEncoding() 
	{
		System.out.println("Inside EmployeeRegistrationSUP::setCharacterEncoding function");
		try
		{
			HttpServletRequest objRequest = super.getRequest();
			objRequest.setCharacterEncoding("UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("Error in Reset -> Character Encoding Try Block = ");
			e.printStackTrace();
		}
			
	}
	
	public String getFlagAddMod() {
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
