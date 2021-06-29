/**
 * 
 */
package hisglobal.utility.generictemplate.masters.utl;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.generictemplate.qryHandler_master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author s.singaravelan
 * Creation Date:- 19-Dec-2013 
 * Module:- Registration(HIS Project)
 *
 */
public class ParameterMasterUTL implements MasterInterface 
{
	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	public HttpServletRequest request = null;

	public String getButtons() 
	{
		
		StringBuilder strButtons = new StringBuilder();
		strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		//strButtons.append("<a href='#' class='button' onclick=' modify(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='modify'>Modify</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='report'>Report</span></a>");
		
		return strButtons.toString();
	}

	public String[] getColsWidth()
	{
		return null;
	}

	public String[] getColumnHeader() 
	{
		String[] columnHdr = { "Parameter Name","Parameter Group","Para Bound"};
		System.out.println("para_mst_utl....col header.....");
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strColumnHdr = { "1", "Record Status" };
		System.out.println("para_mst_utl....combo header.....");
		return strColumnHdr;
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
		return null;
	}

	public String getMainQuery(String[] cmb)
	{System.out.println("para_mst_utl....main query.....");
		StringBuffer brMainQuery = new StringBuffer();
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);

		brMainQuery.append(qryHandler_master.getQuery(1,
				"parameterMst.main.0"));
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
		String strMasterName = "Parameter Master";
		System.out.println("get Parameter Master..........."+strMasterName);
		return strMasterName;
				
	}

	public String[] getOrderBy()
	{		
		String strOrderBy[] = { "1", "UPPER(A.HGSTR_PARA_NAME)","2", "UPPER(B.HGSTR_TEMPLATE_GROUP_NAME)","3","A.HGNUM_PARA_ID"};
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
		String strSearchField[] = { "1", "UPPER(A.HGSTR_PARA_NAME)","2", "UPPER(B.HGSTR_TEMPLATE_GROUP_NAME)","3","A.HGNUM_PARA_ID" };
		return strSearchField;
	}

	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Parameter Name");
		viewHdr.add("D");
		viewHdr.add("Parameter Group");
		viewHdr.add("D");
		viewHdr.add("Para Bound");
		viewHdr.add("D");
		System.out.println("get view header...........");
		return viewHdr;
	}

	public String getViewQuery() 
	{
		String strViewQuery = qryHandler_master.getQuery(1,"parameterMst.view");
		System.out.println("get view query...........");
		return strViewQuery;
	}

	public String isGlobal() 
	{
		return null;
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
