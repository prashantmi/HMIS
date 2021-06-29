/**
 * 
 */
package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;

/**
 * @author s.singaravelan
 * Creation Date:- 18-Dec-2013
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class LanguageMstUTIL implements MasterInterface {
	
	
	
	HttpSession httpSession = null;
	public HttpServletRequest request = null;

	
	public String getButtons() {
		
		StringBuilder strButtons = new StringBuilder();
				
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
		String[] columnHdr = { "Language Name","Language Short Name",};
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
		strComboQuery[0] = "1^Active";//#2^Inactive";
		return strComboQuery;
	}


	public String[] getDeleteQuery() 
	{
		// TODO Auto-generated method stub
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
		// UserVO userVO = ControllerUTIL.getUserVO(request);
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"languageMst.main.0"));
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
		String strMasterName = "Language Master";
		return strMasterName;
	}

	public String[] getOrderBy()
	{		
		String strOrderBy[] = { "1", "UPPER(GSTR_LANGUAGE_NAME)","2", "UPPER(GSTR_LANGUAGE_CODE)"};
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
		String strSearchField[] = { "1", " UPPER(GSTR_LANGUAGE_NAME) ","2", "UPPER(GSTR_LANGUAGE_CODE)" };
		return strSearchField;
	}


	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Language Name");
		viewHdr.add("D");
		viewHdr.add("Language Short Name");
		viewHdr.add("D");
		return viewHdr;
	}


	public String getViewQuery()
	{
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"languageMst.view");
		return strViewQuery;
	}


	public String isGlobal() 
	{
		return null;
	}

	
	public boolean reportInterFaceRequired() 
	{
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
	
	public void setHttpRequest(HttpServletRequest request) {
		this.request = request;
	}

}
