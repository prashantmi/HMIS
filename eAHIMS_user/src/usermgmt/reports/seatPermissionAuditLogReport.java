package usermgmt.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import HisGlobal.HisResultSet;
import usermgmt.FuncLib;

public class seatPermissionAuditLogReport extends FuncLib {
	

	private String hmode 	= "";
	private String seat 	= "";
	private String module 	= "";

	private String hospitalcode="";
	
	private String group="";
	private String userId="";
	private String seatId="";
	private String table="";
	
	
	
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
	
	
	public String getGroupCombo()
	{		
		System.out.println("hospital codeaaaaaaaaaaaaaaaaaaa"+this.getHospitalcode());
		String html = "";
		
	
		String query = "select GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST where GNUM_ISVALID = '1' and GNUM_HOSPITAL_CODE ='"+this.hospitalcode+"' order by initcap(GSTR_GROUP_NAME) ";
		
		System.out.println("getGroupCombo---------"+query);
		try
		{
			html = super.getCombo("",query,this.group,"",0);
			//System.out.println("group codeeeeeeeeee"+this.group);
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in getGroupCombo() "+ex);
		}
		return html;
	}

public String getSeatCombo()
	{	
	
		String html = "";
	if(this.group.length()>0){
		if(!this.group.equals("0"))
		{
			String query = 	" select"+ 
			" GNUM_SEATID, initcap(GSTR_SEAT_NAME)"+
			" from GBLT_SEAT_MST"+
			" where GNUM_GROUP_CODE = '"+this.group+"'"+
			" and GNUM_ISVALID = '1'"+
			" and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+							
			" order by initcap(GSTR_SEAT_NAME)";

			System.out.println(" getSeatCombo query---------"+query);
			
			try
			{
				html = super.getCombo("",query,this.seat,"",0);
			}
			catch(Exception ex)
			{   ex.printStackTrace();
				System.out.println("Exception there in getRoleCombo() "+ex);
			}
		}
		else
			html = "<option value='0'>Select</option>";
		}

		else
			html = "<option value='0'>Select</option>";
		return html;
	} 

public String getModuleCombo()
{		
	//System.out.println("hospital codeaaaaaaaaaaaaaaaaaaa"+this.getHospitalcode());
	String html = "";
	//String query = "select GNUM_MODULE_ID, initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GBL_ISVALID = '1' and GNUM_HOSPITAL_CODE ='"+this.hospitalcode+"' order by initcap(GSTR_MODULE_NAME)";
	String query = "select GNUM_MODULE_ID, initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GBL_ISVALID = '1' order by initcap(GSTR_MODULE_NAME)";
	System.out.println("aaaaaaaaaa"+query);
	try
	{
		html = super.getCombo("",query,this.module,"",0);
	}
	catch(Exception ex)
	{
		System.out.println("Exception there in getModuleCombo() "+ex);
	}
	return html;
}
	

public String getTableCombo()
	{	
	String html = "";
	if(this.group.length()>0)
	{
	if(this.seat.length()>0)
	{
		
		
		if(!this.seat.equals("0"))
		{
String query =" SELECT      gnum_metatable_id || '#' || ( SELECT COUNT (*) FROM gblt_effetivefrom_table   WHERE gbl_isvalid = 1 AND gstr_table_name = a.gstr_table_name) "+
              " || '#' || nvl((SELECT gstr_query  FROM gblt_effetivefrom_table  WHERE gbl_isvalid = 1 AND gstr_table_name = a.gstr_table_name),'NA'),GSTR_DATASET_NAME " +
	          //" FROM gblt_metatable_column_mst a    WHERE gbl_isvalid = '1' AND gnum_hospital_code = '"+this.hospitalcode+"' " +
	          " FROM gblt_metatable_column_mst a    WHERE gbl_isvalid = '1'  " +
	          " AND gnum_metatable_id IN ( SELECT DISTINCT gnum_metatable_id  FROM gblt_role_seat_table_dtl  WHERE gnum_hospital_code = '"+this.hospitalcode+"'  AND gnum_seatid = '"+this.seat+"')" + 
	          "	ORDER BY INITCAP (gstr_table_name) ";

System.out.println("table combo query"+query);

			System.out.println(" getTableCombo query---------"+query);
			
			try
			{
				html = super.getCombo("",query,this.table,"",0);
			}
			catch(Exception ex)
			{   ex.printStackTrace();
				System.out.println("Exception there in getTableCombo() "+ex);
			}
		}
		}
	else
	{	html = "<option value='0'>Select</option>";}
	}
		else
		{	html = "<option value='0'>Select</option>";}
		return html;
	} 
	

	
	
	public String getOldDetailsOfTableData(HttpServletRequest request)
	{
		String html = "";
		String previousMenuList = "";
		
		String query2="";
				
		if(!(this.table==null) && !this.table.equals("") && !this.table.equals("0")) 
		{		
			String[] tableArray=this.table.split("#");
			
			String query =" select GSTR_COLUMN_NAME , "+
						  " GSTR_DISPLAY_COLUMN , "+
						  " GSTR_TABLE_NAME  "+ 
						  " from GBLT_METATABLE_COLUMN_MST "+
						  " where GNUM_METATABLE_ID = '"+tableArray[0]+"'"+
						 // " and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
						  //" and GNUM_HOSPITAL_CODE = '100'  "+
						  " and GBL_ISVALID='1'";
							
			
			HisResultSet rs = null;
			try
			{				
				rs = super.getRecord(query,true);
			}
			catch(Exception e)
			{
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			rs.next();			

if(tableArray[2].equals("NA"))
			{
	query2 =" select distinct "+rs.getString(1)+","+rs.getString(2)+" from "+rs.getString(3)+" where " +
				   " GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"' and GNUM_ISVALID=1 " +
				   " and  "+rs.getString(1)+			
				   " in ( select GNUM_COLUMN_VALUE from GBLT_ROLE_SEAT_AUDITLOG where "+
				   " GNUM_SEATID='"+this.seat+"' "+
				   " and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
				   " and GBL_ISVALID='1' "+					
				   " and gnum_metatable_id = '"+tableArray[0]+"' )"+	
				   " order by "+rs.getString(2);
	
	System.out.println("dynamic Query  for right combo is----"+query2);
	
	HisResultSet rs2 = null;	
	try
	{
		rs2 = super.getRecord(query2,true);
		while(rs2.next())
		{		
			html+="<tr>";	
			html+="<td class='adddatalabel' width='20%'>"+ rs.getString(2)+"</td>";
			html+="<td class='adddatalabel' width='20%'>Active</td>";
			html+="</tr>";	
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Exception in getsecondSeatNames "+e);
	}	
	
}
else
if(tableArray[1].equals("1")  && !tableArray[2].equals(""))
	{
	
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet newRs = null;
	
	try
	{	
	
		String queryFromTable=tableArray[2];	
		
		queryFromTable+=" IN (SELECT gnum_column_value FROM  GBLT_ROLE_SEAT_AUDITLOG  WHERE "+
		" gnum_seatid = '"+ this.seat+"' AND gnum_hospital_code = '"+this.hospitalcode+"' AND gbl_isvalid = 1 " +
		" AND gnum_metatable_id = '"+tableArray[0]+"') ORDER BY orderByName";
		
		
		System.out.println("getOldDetailsOfTableData query----"+queryFromTable);
		conn = super.getConnection();
		conn.setAutoCommit(false);
		ps = conn.prepareStatement(queryFromTable, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	
		
		ps.setString(1,this.seat);
		ps.setString(2,this.hospitalcode);
		ps.setString(3,tableArray[0]);
		ps.setString(4,this.seat);
		ps.setString(5,this.hospitalcode);
		ps.setString(6,tableArray[0]);
	
		ps.setString(7,this.hospitalcode);
			
			newRs=ps.executeQuery();
		
		conn.commit();
		conn.setAutoCommit(true);
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
				
		
		

		try
		{	newRs.beforeFirst();
		   
			while(newRs.next())
			{
				html+="<tr>";	
				html+="<td class='adddatalabel' width='25%'>"+ newRs.getString(2)+"</td>";
				html+="<td class='adddatalabel' width='25%'>InActive</td>";
				html+="<td class='adddatalabel' width='25%'>"+ newRs.getString(3)+"</td>";
				html+="<td class='adddatalabel' width='25%'>"+ newRs.getString(4)+"</td>";
				html+="</tr>";	
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in Making the combo "+e);
		}
		finally
		{
			super.closeConnection(conn);
		}
		
	
	   }
	
	}
		return html;	
		
	}

	public String getDetailsOfTableData(HttpServletRequest request)
	{
		String html = "";
	
		
	
				
		if(!(this.table==null) && !this.table.equals("") && !this.table.equals("0")) 
		{		
			String[] tableArray=this.table.split("#");
			
		


String queryActiveData=" SELECT gnum_column_value AS colmnvalue, gstr_display_value AS displayvalue, "+
			 " to_char(gdt_effect_date,'dd-Mon-yyyy') AS effectivedate, '' AS modifieddate,'Active' as isValid "+
			 " FROM gblt_role_seat_table_dtl WHERE gnum_seatid = '"+this.seat+"' AND gnum_metatable_id = '"+tableArray[0]+"' "+
			 " AND gnum_hospital_code ='"+this.hospitalcode+"' AND gnum_module_id='"+this.module+"'";


String queryInActiveData=" SELECT gnum_column_value AS colmnvalue, gstr_display_value AS displayvalue, "+
				" to_char(gdt_effect_date,'dd-Mon-yyyy') AS effectivedate,to_char(GDT_LSTMOD_DATE,'dd-Mon-yyyy') AS modifieddate,'InActive' as isValid "+
				" FROM GBLT_ROLE_SEAT_AUDITLOG WHERE gnum_seatid = '"+this.seat+"' AND gnum_metatable_id = '"+tableArray[0]+"' "+
				" AND gnum_hospital_code ='"+this.hospitalcode+"' AND gnum_module_id='"+this.module+"'";

String queryForData=queryActiveData+" union all "+queryInActiveData; 

	System.out.println("queryActiveData----"+queryActiveData);
	System.out.println("queryInActiveData----"+queryInActiveData);
	System.out.println("queryForData----"+queryForData);
	
	HisResultSet rs = null;	
	try
	{
		rs = super.getRecord(queryForData,true);
		while(rs.next())
		{		
			html+="<tr>";	
			html+="<td class='adddatavalueNew' width='25%'>"+ rs.getString(2)+"</td>";
			html+="<td class='adddatavalueNew' width='25%'>"+ rs.getString(5)+"</td>";
			html+="<td class='adddatavalueNew' width='25%'>"+ rs.getString(3)+"</td>";
			html+="<td class='adddatavalueNew' width='25%'>"+ rs.getString(4)+"</td>";
			html+="</tr>";	
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Exception in getsecondSeatNames "+e);
	}	
	
			}

		return html;	
		
}

	
}
