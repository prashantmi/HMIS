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
 * Creation Date:- 08-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class PatCategoryMstUTIL implements MasterInterface
{
	
	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	public static HttpServletRequest request = null;

	public String getButtons() {
		
		StringBuilder strButtons = new StringBuilder();

		strButtons.append("<a href='#' class='button' onclick=' add(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='add'>Add</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' edit(\""+(String)request.getSession().getAttribute("cnt_page")+ "\");'/><span class='modify'>Modify</span></a>" );
		strButtons.append("<a href='#' class='button' onclick=' view(\"VIEWDATA\");'/><span class='view'>View</span></a>" );
		strButtons.append("<a href='#' class='button' onclick='  report(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='report'>Report</span></a>");
		strButtons.append("<a href='#' class='button' onclick='  cancelList(\""+(String)request.getSession().getAttribute("cnt_page")+"\");' /><span class='cancel'>Cancel</span></a>");
	
		return strButtons.toString();
	}

	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getColumnHeader() {
		String[] columnHdr = { "Patient Category Name","Patient Category Type"};
		return columnHdr;
	}

	public String[] getComboHeader() {		
		String[] strComboHdr = { "1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery() {
		String[] strComboQuery = new String[1];
		strComboQuery[0] = "1^Active";//#2^Inactive";
		return strComboQuery;
	}

	public String[] getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getJsFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMainQuery(String[] cmb) {
		
		StringBuffer brMainQuery = new StringBuffer();
		
		ActionContext acx=ActionContext.getContext();
		request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
		UserVO uservo = ControllerUTIL.getUserVO(request);
		
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);
		list.add(uservo.getHospitalCode());

		brMainQuery.append(registration.qryHandler_master.getQuery(1,
				"patCategoryMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}
		
		return brMainQuery.toString();
	}

	public String getMasterName() {		
		String strMasterName = "Local Patient Category Master";
		return strMasterName;
	}

	public String[] getOrderBy() {		
		String strOrderBy[] = { "1", "UPPER(GSTR_PATIENT_CAT_NAME)","2", " UPPER(GNUM_PATIENT_CAT_TYPE) "};
		return strOrderBy;
	}

	public int getPage_per_block() {
		return 10;
	}

	public int getRecord_per_page() {
		return 10;
	}

	public String[] getSearchField() {		
		String strSearchField[] = { "1", "UPPER(GSTR_PATIENT_CAT_NAME)","2", " UPPER(DECODE(GNUM_PATIENT_CAT_TYPE,'0','Primary','1','Treatment','2','Other')) " };
		return strSearchField;
	}

	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Global Category :");
		viewHdr.add("D");
		viewHdr.add("Patient Category Name :");
		viewHdr.add("D");
		viewHdr.add("Category Type :");
		viewHdr.add("D");
		viewHdr.add("Category Short Name :");
		viewHdr.add("D");
		viewHdr.add("Patient Category Group :");
		viewHdr.add("D");
		viewHdr.add("Economy Status :");
		viewHdr.add("D");
		viewHdr.add("Priority :");
		viewHdr.add("D");
		viewHdr.add("Is Paid :");
		viewHdr.add("D");
		viewHdr.add("Is ID Require :");
		viewHdr.add("D");
		viewHdr.add("Verification Document :");
		viewHdr.add("D");
		viewHdr.add("ID Size :");
		viewHdr.add("D");
		viewHdr.add("Validation Type :");
		viewHdr.add("D");
		viewHdr.add("Is Expiry :");
		viewHdr.add("D");
		viewHdr.add("Expiry Month :");
		viewHdr.add("D");
		viewHdr.add("Expiry Days :");
		viewHdr.add("D");
		//By Mukund on 06.Nov.17
		viewHdr.add("Is Special Category :");
		viewHdr.add("D");
		viewHdr.add("Department(s) Bound :");
		viewHdr.add("D");
		//End on 06.Nov.17
		viewHdr.add("Benefit :");
		viewHdr.add("D");
		viewHdr.add("Criteria :");
		viewHdr.add("D");
		viewHdr.add("Remarks :");
		viewHdr.add("D");
		/*Start: Surabhi
		 * reason: to view the organisation name*/
		viewHdr.add("Default Organization :");
		viewHdr.add("D");
		//End
		return viewHdr;
	}

	public String getViewQuery() {
		String strViewQuery = registration.qryHandler_master.getQuery(1,
		"patCategoryMst.view");
		return strViewQuery;
	}

	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setHttpSession(HttpSession session) {
		httpSession = session;

	}

	public void setHttpSessionMap(Map session) {
		// TODO Auto-generated method stub

	}
	
	public void setHttpRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
