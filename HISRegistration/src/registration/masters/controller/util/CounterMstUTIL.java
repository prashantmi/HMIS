/**
 * 
 */
package registration.masters.controller.util;


import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import registration.config.RegistrationConfig;

import com.opensymphony.xwork2.ActionContext;




/**
 * @author s.singaravelan
 * Creation Date:- 17-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class CounterMstUTIL implements MasterInterface
{
	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	public HttpServletRequest request = null;

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
		return null;
	}

	public String[] getColumnHeader()
	{
		String[] columnHdr = { "Counter Name","Counter Type"};
		return columnHdr;
	}

	public String[] getComboHeader()
	{		
		String[] strComboHeader = { "1", "Record Status" };
		return strComboHeader;
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
	{
		
		StringBuffer brMainQuery = new StringBuffer();
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);
		list.add(uservo.getHospitalCode());

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"counterMst.main.0"));
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
		String strMasterName = "Counter Master";
		return strMasterName;
	}

	public String[] getOrderBy()
	{		
		String strOrderBy[] = { "1","UPPER(GBLSTR_COUNTER_NAME)","2","UPPER(GBLSTR_COUNTER_TYPE)"};
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
		String strSearchField[] = { "1","UPPER(GBLSTR_COUNTER_NAME)","2","UPPER(GBLSTR_COUNTER_TYPE)"};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Counter Name :");
		viewHdr.add("D");
		viewHdr.add("Counter Type :");
		viewHdr.add("D");
		viewHdr.add("Location :");
		viewHdr.add("D");
		viewHdr.add("IP Address :");
		viewHdr.add("D");
		viewHdr.add("Remarks :");
		viewHdr.add("D");
		return viewHdr;
	}

	public String getViewQuery()
	{
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"counterMst.view");
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

	}
	
	public void setHttpRequest(HttpServletRequest request)
	{
		this.request = request;
	}


}
