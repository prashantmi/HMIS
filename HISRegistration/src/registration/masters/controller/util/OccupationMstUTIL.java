package registration.masters.controller.util;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author Deepika Gaba Creation Date:- 12-Jan-2013 Modifying Date:-
 *         Used For:- Team Lead By:- Module:- Reg(HIS Project)
 * 
 */
public class OccupationMstUTIL  implements MasterInterface 
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
		strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' edit(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='modify'>Modify</span></a>" );
		//strButtons.append("<a href='#' class='button' onclick=' deleteRecords(\"DELETE\");'/><span class='delete'>Delete</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='reports'>Report</span></a>");
		strButtons.append("<a href='#' class='button' onclick='  cancelList(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='cancel'>Cancel</span></a>");

		return strButtons.toString();
	}

	
	public String[] getColumnHeader()
	{
		String[] columnHdr = { "Occupation Name" };
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
		String[] strDeleteQuery = new String[1];
		StringBuffer strTemp = null;
		List<String> list = new ArrayList<String>();

		strTemp = new StringBuffer(registration.qryHandler_master.getQuery(1,
		"occupationTestMst.delete.0"));

		strDeleteQuery[0] = HelperMethodsDAO.populateQuery(strTemp, list)
		.toString();

		strDeleteQuery[0] = strDeleteQuery[0].concat(" WHERE "
				+ registration.qryHandler_master.getQuery(1,
				"occupationTestMst.delete.cond.0"));

		return strDeleteQuery;
	}


	public String getJsFiles() 
	{
		String jsFile = new String(
		"../registration/masters/js/occupationTestMst.js");
		return jsFile;
	}

	
	public String getMainQuery(String[] cmb)
	{
		StringBuffer brMainQuery = new StringBuffer();

		// UserVO userVO = ControllerUTIL.getUserVO(request);
		List<String> list = new ArrayList<String>();

		//list.add("101");
		list.add(Config.IS_VALID_ACTIVE);

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
		"occupationTestMst.main.0"));

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
		String strMasterName = "Occupation Master";
		return strMasterName;
	}


	public String[] getOrderBy() 
	{
		String strOrderBy[] = { "1", " UPPER(GSTR_OCCUPATION_NAME) " };
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
		String strSearchField[] = { "1", "GSTR_OCCUPATION_NAME" };
		return strSearchField;
	}


	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();

		viewHdr.add("Occupation Name");
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
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"occupationTestMst.view");

		return strViewQuery;
	}

	public void setHttpSessionMap(Map session) 
	{
		// TODO Auto-generated method stub

	}



	





}
