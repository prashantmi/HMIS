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
 * Creation Date:- 05-May-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class EmgCaseMstUTIL implements MasterInterface 
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
		String[] columnHdr = { "Emergency Case","Is MLC Required","Case Type"};
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
				"emgCaseMst.main.0"));
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
		String strMasterName = "Emergency Case  Master";
		return strMasterName;
	}
	
    public String[] getOrderBy() 
	{		
		String strOrderBy[] = { "1", "UPPER(hrgnum_emrgency_case)","2","UPPER(hrgnum_ismlc_req)","3","UPPER(hrgnum_case_type)"};
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
		String strSearchField[] =  { "1", "UPPER(hrgnum_emrgency_case)","3", "UPPER(DECODE(hrgnum_case_type,'1','Normal','2','Trauma','3','Non Trauma','4','Ambulatory'))"} ;
		return strSearchField;
	}
	
	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Emergency Case  :");
		viewHdr.add("D");
		viewHdr.add("Is MLC Required :");
		viewHdr.add("D");
		viewHdr.add("Case Type :");
		viewHdr.add("D");
		
		
		return viewHdr;
	}

	public String getViewQuery() 
	{
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"emgCaseMst.view");
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
