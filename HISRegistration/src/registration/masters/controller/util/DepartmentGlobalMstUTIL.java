/**
 * 
 */
package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;

/**
 * @author s.singaravelan
 *
 */
public class DepartmentGlobalMstUTIL implements MasterInterface {
	
	
	
	private static final long serialVersionUID = 1L;
	public static HttpSession httpSession = null;
	public static HttpServletRequest request = null;

	public String getButtons() {
		
		StringBuilder strButtons = new StringBuilder();
			
		strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='report'>Report</span></a>");
		strButtons.append("<a href='#' class='button' onclick='  cancelList(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='cancel'>Cancel</span></a>");
	
		return strButtons.toString();
	}

	public String[] getColsWidth() 
	{
		return null;
	}

	public String[] getColumnHeader() 
	{
		String[] columnHdr = { "Department Name","Department Type"};
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strColumnHdr = { "1", "Record Status" };
		return strColumnHdr;
	}

	public String[] getComboQuery() 
	{
		String[] strComboQuery = new String[1];
		strComboQuery[0] = "1^Active";
		return strComboQuery;
	}

	public String[] getDeleteQuery()
	{
		return null;
	}

	public String getJsFiles()
	{
		return null;
	}

	public String getMainQuery(String[] cmb)
	{
		
		StringBuffer brMainQuery = new StringBuffer();
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);
		list.add(Config.SUPER_HOSPITAL_CODE);

		brMainQuery.append(registration.qryHandler_master.getQuery(1,"departmentMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) 
		{
			if (!cmb[0].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}
		
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{		
		String strMasterName = "Department Master";
		return strMasterName;
	}

	public String[] getOrderBy() 
	{		
		String strOrderBy[] = { "1", "UPPER(GSTR_DEPT_NAME)","2", "DECODE(gnum_dept_type,1,'Clinical',2,'Administrative',3,'Ancillary',4,'Support')"};
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
		String strSearchField[] =  { "1", "UPPER(GSTR_DEPT_NAME)","2", "UPPER(DECODE(GNUM_DEPT_TYPE,'1','Clinical','2','Administrative','3','Ancillary','4','Support'))"} ;
		return strSearchField;
	}

	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Department Name :");
		viewHdr.add("D");
		viewHdr.add("Department Type :");
		viewHdr.add("D");
		viewHdr.add("Age Limit :");
		viewHdr.add("D");
		viewHdr.add("Gender Bound :");
		viewHdr.add("D");
		return viewHdr;
	}

	public String getViewQuery() 
	{		
		
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"departmentGlobalMst.view");
		return strViewQuery;
	}

	public String isGlobal()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reportInterFaceRequired() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void setHttpSession(HttpSession session)
	{
		httpSession = session;

	}

	public void setHttpSessionMap(Map session)
	{
		// TODO Auto-generated method stub

	}
	
	public void setHttpRequest(HttpServletRequest request) 
	{
		this.request = request;
	}
	

}
