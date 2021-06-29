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
 * Creation Date:- 26-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class UOMMstUTIL implements MasterInterface 
{
	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	public static HttpServletRequest request = null;

	public String getButtons() 
	{
		StringBuilder strButtons = new StringBuilder();
		strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' edit(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='modify'>Modify</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='report'>Report</span></a>");
		strButtons.append("<a href='#' class='button' onclick='  cancelList(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='cancel'>Cancel</span></a>");
	
		return strButtons.toString();
	}

	public String[] getColsWidth()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getColumnHeader() 
	{
		String[] columnHdr = { "Unit of Measurement Name","Unit of Measurement Short Name"};
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String[] strComboQuery = new String[1];
		strComboQuery[0] = "1^Active";//#2^Inactive";
		return strComboQuery;
	}

	public String[] getDeleteQuery() 
	{
		return null;
	}

	public String getJsFiles()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getMainQuery(String[] cmb) 
	{
		
		StringBuffer brMainQuery = new StringBuffer();
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"uomMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}
		
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{		
		String strMasterName = "Unit of Measurement Master";
		return strMasterName;
	}

	public String[] getOrderBy() 
	{		
		String strOrderBy[] = { "1", "UPPER(GSTR_UOM_NAME)","2", " UPPER(GSTR_UOM_SHORTNAME)"};
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
		String strSearchField[] = { "1", "UPPER(GSTR_UOM_NAME)","2", " UPPER(GSTR_UOM_SHORTNAME)" };
		return strSearchField;
	}

	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Unit of Measurement Name :");
		viewHdr.add("D");
		viewHdr.add("Unit of Measurement Short Name :");
		viewHdr.add("D");
		return viewHdr;
	}

	public String getViewQuery() 
	{
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"uomMst.view");
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

	}
	
	public void setHttpRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

}
