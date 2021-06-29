package hr.pis.empReg.transactions.controller.util;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmpRegistrationValidationUTIL extends ControllerUTIL implements MasterInterface 
{

	private static final long serialVersionUID = 02L;

	
	HttpSession httpSession = null;
	public HttpServletRequest request = null;


	public void setHttpSession(HttpSession session) 
	{
		httpSession = session;
	}


	public void setHttpRequest(HttpServletRequest request) 
	{
		this.request = request;
	}


	public String getButtons()
	{
		StringBuilder strButtons = new StringBuilder();
		//strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' edit(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='view'>View</span></a>" );
		//strButtons.append("<a href='#' class='button' onclick=' deleteRecords(\"DELETE\");'/><span class='delete'>Delete</span></a>" );
		//strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='reports'>Report</span></a>");
		return strButtons.toString();
	}

	
	public String[] getColumnHeader()
	{
		String[] columnHdr = { "Employee Number", "Employee Name", "Employee Name in Regional Language", "Employee DoB" };
		//String[] columnHdr = { "Employee Number", "Employee Name", "Employee Name in Regional Language", "Employee DoB" };
		//String[] columnHdr = { "Employee Name" };
		return columnHdr;
	}

	
	public String[] getComboHeader()
	{
		String[] strColumnHdr = { "1", "Employee Validate Status" };
		//String[] strColumnHdr = {};
		return strColumnHdr;
	}


	public String[] getComboQuery() 
	{
		String[] strComboQuery = new String[1];

		strComboQuery[0] = "1^Pending#2^Validated#3^Rejected";//#2^Inactive";
		//strComboQuery[0] = "1^Pending#2^Validated";//#2^Inactive";
		//strComboQuery[0] = "";

		return strComboQuery;
	}


	public String[] getDeleteQuery() 
	{
		String[] strDeleteQuery = new String[1];
		StringBuffer strTemp = null;
		List<String> list = new ArrayList<String>();

		strTemp = new StringBuffer(hr.pis.common.qryHandler_master.getQuery(1,
		"cadreTestMst.delete.0"));

		strDeleteQuery[0] = HelperMethodsDAO.populateQuery(strTemp, list)
		.toString();

		strDeleteQuery[0] = strDeleteQuery[0].concat(" WHERE "
				+ hr.pis.common.qryHandler_master.getQuery(1,
				"cadreTestMst.delete.cond.0"));

		return strDeleteQuery;
	}


	public String getJsFiles() 
	{
		String jsFile = new String(
		"/HISPis/pis/transactions/js/employeeRegistration.js");
		return jsFile;
	}

	/*
	public String getMainQuery(String[] cmb)
	{
		StringBuffer brMainQuery = new StringBuffer();

		// UserVO userVO = ControllerUTIL.getUserVO(request);
		List<String> list = new ArrayList<String>();

		//list.add("101");
		list.add(Config.IS_VALID_ACTIVE);

		brMainQuery.append(pis.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0"));

		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		//System.out.println("Combo Value = "+cmb[0]);
		if (cmb != null) 
		{
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);

			}
		}
		
		System.out.println("Main Query : After Adding Combo = "+brMainQuery.toString());
		return brMainQuery.toString();
	}
	*/
	
	public String getMainQuery(String[] cmb)
	{
		StringBuffer brMainQuery = new StringBuffer();

		// UserVO userVO = ControllerUTIL.getUserVO(request);
		List<String> list = new ArrayList<String>();

		//list.add("101");
		list.add(Config.IS_VALID_ACTIVE);

		brMainQuery.append(hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.1"));

		System.out.println("Main Query : Before Adding List = "+brMainQuery.toString());
		
		System.out.println("Main Query : Before Adding Combo = "+brMainQuery.toString());
		
		//System.out.println("Combo Value = "+cmb[0]);
		
		
		if (cmb != null) 
		{
			if (cmb[0].equals("1")) 
			{
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.2"));
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.5"));
			}
			else if (cmb[0].equals("2")) 
			{
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.3"));
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.5"));
			}
			else if (cmb[0].equals("3")) 
			{
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.4"));
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.5"));
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.6"));
			}
			else
			{
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.2"));
				brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.5"));
			}

		}
		else
		{
			brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.2"));
			brMainQuery.append(" "+hr.pis.common.qryHandler_master.getQuery(2,"Employee.Registration.Validation.List.0.5"));
		}
		
		
		
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);
		
		System.out.println("Main Query : After Adding Combo = "+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{
		String strMasterName = "Employee Registration Validation";
		return strMasterName;
	}


	public String[] getOrderBy() 
	{
		String strOrderBy[] = { "2", " UPPER(str_emp_full_name) " };
		return strOrderBy;
	}


	public int getPage_per_block() 
	{
		return 10;
	}


	public int getRecord_per_page()
	{
		return 10;
	}

	
	public String[] getSearchField() 
	{
		String strSearchField[] = { "1", " str_emp_no ", "2", " str_emp_full_name "};
		return strSearchField;
	}


	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();

		//viewHdr.add("Employee Number");
		viewHdr.add("Employee Name");
		//viewHdr.add("Employee Name in Regional Language");
		//viewHdr.add("Employee DoB");
		viewHdr.add("D");
		
		return viewHdr;
	}

	public String[] getColsWidth() 
	{

		return null;
	}

	public boolean reportInterFaceRequired() 
	{

		return false;
	}

	public String isGlobal() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	public String getViewQuery()
	{
		String strViewQuery = hr.pis.common.qryHandler_master.getQuery(1,
		"cadreTestMst.view");

		return strViewQuery;
	}

	public void setHttpSessionMap(Map session) 
	{
		// TODO Auto-generated method stub

	}

	/*
	public static EmployeeRegistrationVO modifyRecord(HttpServletRequest request, EmployeeRegistrationVO objEmpRegValVO) 
	{
		System.out.println("EmployeeRegistrationValidationUTIL :: modifyRecord");
		EmployeeRegistrationVO VOEmpReg =new EmployeeRegistrationVO();
		try
		{
			UserVO userVO=getUserVO(request);
			VOEmpReg = EmployeeRegistrationValidationDATA.modifyRecord(request, VOEmpReg, userVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return VOEmpReg;
	}
	*/
	


}
