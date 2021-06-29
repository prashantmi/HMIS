/**
 * 
 */
package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;



import com.opensymphony.xwork2.ActionContext;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

/**
 * @author s.singaravelan
 * Creation Date:- 30-Dec-2013 
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class UnitMstUTIL implements MasterInterface {
	
	
	
	HttpSession httpSession = null;
	public static HttpServletRequest request = null;
	String strDeptCode = "";
	
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
		String[] columnHdr = { "Unit Name","Unit Type"};
		return columnHdr;
	}

	public String[] getComboHeader()
	{		
		String[] strComboHdr = { "0", "Department" ,"1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery() 
	{
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		
		String[] strComboQuery = new String[2];
		strComboQuery[0] = registration.qryHandler_master.getQuery(1,
				"select.deptCombo.unitMst").replace("?", uservo.getHospitalCode());
		strComboQuery[1] = "1^Active";//#2^Inactive";
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
		
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);	
		StringBuffer brMainQuery = new StringBuffer();
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);
		list.add(uservo.getHospitalCode());

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"unitMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.unitMst.cond.0")
						.replace("?", "1"));
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("strDeptCode", cmb[0]);

			}
			else
			{
				brMainQuery.append(" AND "
						+ registration.qryHandler_master.getQuery(1,
						"select.unitMst.cond.0")
						.replace("?", ""));
			}
			if (!cmb[1].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[1]);
			}
		}
		else
		{
			brMainQuery.append(" AND "
					+ registration.qryHandler_master.getQuery(1,
					"select.unitMst.cond.0")
					.replace("?", ""));
		}
		
		System.out.println("Main Query;;;;;;;;;"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{		
		String strMasterName = "Unit Master";
		return strMasterName;
	}

	public String[] getOrderBy() 
	{		
		String strOrderBy[] = { "1", "UPPER(HGSTR_UNITNAME)"};
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
		String strSearchField[] = { "1", " UPPER(HGSTR_UNITNAME) " };
		return strSearchField;
	}

	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Department :");
		viewHdr.add("D");
		viewHdr.add("Unit Name :");
		viewHdr.add("D");
		viewHdr.add("Unit Type :");
		viewHdr.add("D");
		viewHdr.add("Referal Required :");
		viewHdr.add("D");
		viewHdr.add("Is Unit :");
		viewHdr.add("D");
		viewHdr.add("Unit Head :");
		viewHdr.add("D");
		viewHdr.add("Diagnosis Code Set :");
		viewHdr.add("D");
		viewHdr.add("Location :");
		viewHdr.add("D");
		viewHdr.add("Episode Close Days :");
		viewHdr.add("D");
		viewHdr.add("Card Print Type :");
		viewHdr.add("D");
		viewHdr.add("MobileNo :");      //Added by warish 23-aug-2017
		viewHdr.add("D");
		viewHdr.add("Expiry Mode :");
		viewHdr.add("D");
		viewHdr.add("Renewal Days :");
		viewHdr.add("D");
		viewHdr.add("Renewal Month :");
		viewHdr.add("D");
		return viewHdr;
	}

	public String getViewQuery() 
	{
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"unitMst.view.0");
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
	
	public void setHttpRequest(HttpServletRequest request)
	{
		this.request = request;
	}

}
