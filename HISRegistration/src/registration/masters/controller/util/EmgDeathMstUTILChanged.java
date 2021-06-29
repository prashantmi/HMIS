

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
 * @author nehasharma dated 20-Jan-2014
 *
 */
public class EmgDeathMstUTILChanged implements MasterInterface 
{

	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	public HttpServletRequest request = null;
	
	public void setHttpRequest(HttpServletRequest request) 
	{
		this.request = request;
	}
	public String getButtons() 
	{
		StringBuilder br = new StringBuilder();

		br.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		br.append("<a href='#' class='button' onclick=' edit(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='modify'>Modify</span></a>" );
		br.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		//br.append("<a href='#' class='button' onclick=' CheckRecordsBeforeDelete(\"DELETE\");'/><span class='delete'>Delete</span></a>" );
		br.append("<a href='#' class='button' onclick=' deleteRecords(\"DELETE\");'/><span class='delete'>Delete</span></a>" );
		br.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='reports'>Report</span></a>");

		return br.toString();
	}
	public String[] getColumnHeader() 
	{
		String[] columnHdr = { "Death Manner"};
		return columnHdr;
	}
	
	public String[] getComboHeader() 
	{
		String comboHeader[] = {"1","Record Status" };
		return comboHeader;
	}
	public String[] getComboQuery() 
	{
		String[] comboQuery = new String[1];
		comboQuery[0] = "1^Active#0^InActive";
		return comboQuery;
	}
	public String[] getDeleteQuery() 
	{
		
			String[] strDeleteQuery = new String[3];
			StringBuffer strTemp = null;
			StringBuffer strTemp1 = null;
			StringBuffer strTemp2 = null;
			List<String> list = new ArrayList<String>();
			
			strTemp = new StringBuffer(appointment.qryHandler_master.getMasterQuery("appointment.delete.0"));
			strTemp1 = new StringBuffer(appointment.qryHandler_master.getMasterQuery("appointment.delete.1"));
			strTemp2 = new StringBuffer(appointment.qryHandler_master.getMasterQuery("appointment.delete.2"));
			
			strDeleteQuery[0] = HelperMethodsDAO.populateQuery(strTemp2, list)
			.toString();
			strDeleteQuery[1] = HelperMethodsDAO.populateQuery(strTemp1, list)
					.toString();
			strDeleteQuery[2] = HelperMethodsDAO.populateQuery(strTemp, list)
					.toString();
			
			
		return strDeleteQuery;
	}
	
	public String getJsFiles() 
	{
		
		return "";
	}
	
	public String getMainQuery(String[] cmb) 
	{
		
		StringBuffer brMainQuery = new StringBuffer();
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"emgDeathMst.main.0"));
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
		String strMasterName = "Appointment Configuration Master";
		return strMasterName;
	}

	public String[] getOrderBy() 
	{		
		String strOrderBy[] = { "1", "UPPER(HGSTR_DEATH_MANNER_NAME)"};
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
		String strSearchField[] = { "1", "UPPER(HGSTR_DEATH_MANNER_NAME)"};
		return strSearchField;
	}

	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Death Manner :");
		viewHdr.add("D");
		viewHdr.add("Death Manner Description :");
		viewHdr.add("D");
		viewHdr.add("Is PostMortem Requird :");
		viewHdr.add("D");
		viewHdr.add("Is Accidental :");
		viewHdr.add("D");
		
		return viewHdr;
	}

	public String getViewQuery() 
	{
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"emgDeathMst.view");
		return strViewQuery;
	}

	public void setHttpSession(HttpSession session) 
	{
		this.httpSession = session;
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
		// this is used for reporting..
		//if set to 1 then hospital header is not required cz its a global report..
		return "1";
	}
	
	@Override
	public void setHttpSessionMap(@SuppressWarnings("rawtypes") Map session) 
	{
	}
}
