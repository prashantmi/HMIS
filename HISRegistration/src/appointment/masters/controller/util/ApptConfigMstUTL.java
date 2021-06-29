

package appointment.masters.controller.util;

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
public class ApptConfigMstUTL implements MasterInterface 
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
		//String strColHeader[] = { "Appointment Name","Para 1","Para 2","Para 3","Para 4","Para 5","Para 6","Para 7"};
		String strColHeader[] = { "Appointment Name","Para 1","Para 2","Para 3","Para 4","Para 5"};
		return strColHeader;
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
		String files = "../appointment/masters/js/AppointmentConfiguration.js";
		return files;
	}
	public String getMainQuery(String[] strCmb) 
	{
		StringBuffer brMainQuery = new StringBuffer();
		String hosp_code=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		//brMainQuery.append("SELECT HAPNUM_ACTUAL_PARA_REFID||'@'||GNUM_HOSPITAL_CODE||'|'||(SELECT count(1) FROM hapt_appointment_dtl WHERE HAPNUM_ACTUAL_PARA_REFID= A.HAPNUM_ACTUAL_PARA_REFID AND gnum_hospital_code=A.GNUM_HOSPITAL_CODE AND HAPNUM_APT_STATUS IN ('1','2'))||'^'||");
		brMainQuery.append("SELECT HAPNUM_ACTUAL_PARA_REFID||'@'||GNUM_HOSPITAL_CODE||'^'||");
		brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_APPT_NAME(HAPNUM_APT_ID)||'^'||");
		brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 1, HAPSTR_ACTUAL_PARA_ID_1, GNUM_HOSPITAL_CODE) ||'^'||");
		brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 2, HAPSTR_ACTUAL_PARA_ID_2, GNUM_HOSPITAL_CODE) ||'^'||");
		brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 3, HAPSTR_ACTUAL_PARA_ID_3, GNUM_HOSPITAL_CODE) ||'^'||");
		brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 4, HAPSTR_ACTUAL_PARA_ID_4, GNUM_HOSPITAL_CODE) ||'^'||");
		brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 5, HAPSTR_ACTUAL_PARA_ID_5, GNUM_HOSPITAL_CODE) AS DATA,");
		//brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 6, HAPSTR_ACTUAL_PARA_ID_6, GNUM_HOSPITAL_CODE) ||'^'||");
		//brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 7, HAPSTR_ACTUAL_PARA_ID_7, GNUM_HOSPITAL_CODE)	AS DATA,");
		brMainQuery.append("PKG_APPOINTMENT_MASTERS.HAPT_FUNC_APPT_NAME(HAPNUM_APT_ID) AS APPNAME FROM HAPT_ACTUAL_PARA_MAP_MST WHERE 1=1");
		
		if(hosp_code!=null)
			brMainQuery.append("AND  GNUM_HOSPITAL_CODE="+hosp_code+"");
		
		if (strCmb == null) 
		{
			strCmb=new String[1];
			strCmb[0]="1";
		}
		
		if (strCmb != null) 
		{
			 if (strCmb[0].equals("1")) // ACTIVE
			 {
				 brMainQuery.append(" AND GNUM_ISVALID=1");
			 }
			 
			 if (strCmb[0].equals("0")) // INACTIVE
			 {
				 brMainQuery.append(" AND GNUM_ISVALID=0");
			 }
		}
		
		System.out.println("MAINQUERY APPOINTMENT CONFIG MST UTIL-->"+brMainQuery.toString());
		
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{
		String strMasterName = "Appointment Configuration Master";
		return strMasterName;
	}

	public String[] getOrderBy() 
	{
		String strOrderBy[] = { "1", "PKG_APPOINTMENT_MASTERS.HAPT_FUNC_APPT_NAME(HAPNUM_APT_ID)" };
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
		String strSearchBy[] = { "1", "PKG_APPOINTMENT_MASTERS.hapt_func_appt_name(hapnum_apt_id)"};
		return strSearchBy;
	}

	public List<String> getViewHeader() 
	{

		List<String> listViewHdr = new ArrayList<String>();
		listViewHdr.add("Appointment Name");
		listViewHdr.add("D");
		listViewHdr.add("Para1");
		listViewHdr.add("D");
		listViewHdr.add("Para2");
		listViewHdr.add("D");
		listViewHdr.add("Para3");
		listViewHdr.add("D");
		listViewHdr.add("Para4");
		listViewHdr.add("D");
		listViewHdr.add("Para5");
		listViewHdr.add("D");
		//listViewHdr.add("Para6");
		//listViewHdr.add("D");
		//listViewHdr.add("Para7");
		//listViewHdr.add("D");
				
		return listViewHdr;

	}

	public String getViewQuery() 
	{

		String strViewQuery = "SELECT PKG_APPOINTMENT_MASTERS.HAPT_FUNC_APPT_NAME(HAPNUM_APT_ID),"+
		"PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 1, HAPSTR_ACTUAL_PARA_ID_1, GNUM_HOSPITAL_CODE),"+
		"PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 2, HAPSTR_ACTUAL_PARA_ID_2, GNUM_HOSPITAL_CODE) ,"+
		"PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 3, HAPSTR_ACTUAL_PARA_ID_3, GNUM_HOSPITAL_CODE) ,"+
		"PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 4, HAPSTR_ACTUAL_PARA_ID_4, GNUM_HOSPITAL_CODE) ,"+
		"PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 5, HAPSTR_ACTUAL_PARA_ID_5, GNUM_HOSPITAL_CODE)"+
		//",PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 6, HAPSTR_ACTUAL_PARA_ID_6, GNUM_HOSPITAL_CODE),"+
		//"PKG_APPOINTMENT_MASTERS.HAPT_FUNC_PARA_ACTUAL_NAME(HAPNUM_APT_ID, 7, HAPSTR_ACTUAL_PARA_ID_7, GNUM_HOSPITAL_CODE) "+
		"FROM HAPT_ACTUAL_PARA_MAP_MST WHERE HAPNUM_ACTUAL_PARA_REFID=? AND GNUM_HOSPITAL_CODE=? ";
		
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
