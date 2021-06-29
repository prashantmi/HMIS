package usermgmt.masters;

import usermgmt.FuncLib;
import HisGlobal.*;
import usermgmt.masters.UserFunc;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author cdac
 * Modified by : Adil Wasi for pagination on 05-Apr-2013
 *
 */

public class UmgmtRoleSeatTableMstBean extends FuncLib
{
	private String selectedNames ="";
	private String selectedValues ="";
	private String hmode 	= "";
	private String seat 	= "";
	private String module 	= "";
	private String role	 	= "";
	private String newseat 	= "";
	private String table 	= "";
	private String column 	= "";
	private String effect_date	="";
	private String entry_date	="";
	private String columnValue 	= "";
	private String seatId 	= "";
	private String status 	= "";
	private String isvalid 	= "";
	private String[] chk 	= null; 
	private String hospitalcode="";
	
	private String menuSelected	= "";	
	private String moduleName 	= "";
	private String roleName 	= "";
	private String seatName 	= "";
	private String menuName 	= "";
	private String menuId 		= "";
	private String previousMenuList = "";
	private String previousIsValid = "";
	private String previousMenuList1="";
	
	private String group 	= "";
	private String strAlphbet="";
	private String strPrevAlphbet="";
	private String tablelength="";
	
	
	
	
	
	
	public String getSelectedNames() {
		return selectedNames;
	}
	public void setSelectedNames(String selectedNames) {
		this.selectedNames = selectedNames;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	public String getEffect_date()
	{
		return ((effect_date==null)?" ":effect_date);
	}


	public String getEntry_date()
	{
		return ((entry_date==null)?" ":entry_date);
    }
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuSelected() {
		return menuSelected;
	}
	public void setMenuSelected(String menuSelected) {
		this.menuSelected = menuSelected;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getPreviousIsValid() {
		return previousIsValid;
	}
	public void setPreviousIsValid(String previousIsValid) {
		this.previousIsValid = previousIsValid;
	}
	public String getPreviousMenuList() {
		return previousMenuList;
	}
	public void setPreviousMenuList(String previousMenuList) {
		this.previousMenuList = previousMenuList;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getNewseat() {
		return newseat;
	}
	public void setNewseat(String newseat) {
		this.newseat = newseat;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	} 
	public void setEffect_date(String EFFECT_DATE)
	{
		this.effect_date=EFFECT_DATE;
	}

	public void setEntry_date(String ENTRY_DATE)
	{
		this.entry_date=ENTRY_DATE;
	}
	
	public void initializeNewMode()
	{
		this.menuSelected 	= "";
		this.module 		= "";
		this.newseat 		= "";		
		this.effect_date  ="";
		this.entry_date	  ="";
		this.isvalid 	  ="1";
		this.table	="";
		this.column="";
		this.columnValue="";
	}//end of initilize mode
	
	public String getSelectedValues() {
		return selectedValues;
	}
	public void setSelectedValues(String selectedValues) {
		this.selectedValues = selectedValues;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getPreviousMenuList1() {
		return previousMenuList1;
	}
	public void setPreviousMenuList1(String previousMenuList1) {
		this.previousMenuList1 = previousMenuList1;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	} 

	public String getGroupCombo()
	{		
		System.out.println("hospital codeaaaaaaaaaaaaaaaaaaa"+this.getHospitalcode());
		String html = "";
	
	
		String query = "select GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST"+
		" where  GNUM_ISVALID ='1'  and TRUNC(GDT_EFFECT_DATE) <= TRUNC(SYSDATE) and " +
		" GNUM_HOSPITAL_CODE='" +this.hospitalcode +"' " +
		"order by initcap(GSTR_GROUP_NAME)";
		
		
		
		System.out.println("Group Query -----------"+query);
		try
		{
			html = super.getCombo("",query,this.group,"",0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Exception there in getModuleCombo() "+ex);
		}
		return html;
	}
	
	public String getModuleCombo()
	{		
		System.out.println("hospital codeaaaaaaaaaaaaaaaaaaa"+this.getHospitalcode());
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
	public String getRoleCombo()
	{	
		String html = "";
		if(!this.module.equals("0"))
		{
			String query = 	" select"+ 
							" GNUM_ROLE_ID, initcap(GSTR_ROLE_NAME)"+
							" from GBLT_ROLE_MST"+
							" where GNUM_MODULE_ID = '"+this.module+"'"+
							" and GBL_ISVALID = '1'"+
							" and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+							
							" order by initcap(GSTR_ROLE_NAME)";
			System.out.println("Role combo query"+query);
			try
			{
				html = super.getCombo("",query,this.role,"",0);
			}
			catch(Exception ex)
			{
				System.out.println("Exception there in getRoleCombo() "+ex);
			}
		}
		else
			html = "<option value='0'>Select</option>";
		return html;
	} 
	
	public String getSeatCombo()
	{		
		String html = "";
		if(!this.role.equals("0"))
		{
		if(group=="")
			group="NULL";
String query = 	" select"+ 
			" GNUM_SEATID, initcap(GSTR_SEAT_NAME)"+
			" from GBLT_SEAT_MST"+
			" where GNUM_GROUP_CODE = "+this.group+""+
			" and GNUM_ISVALID = '1'"+
			" and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+							
			" order by initcap(GSTR_SEAT_NAME)";

			
			
			System.out.println("seat queryyyyyyyyyyyyy"+query);
			try
			{
				html = super.getCombo("",query,this.seat,"",0);
			}
			catch(Exception ex)
			{
				System.out.println("Exception there in getSeatCombo() "+ex);
			}
		}
		else
			html = "<option value='0'>Select</option>";
		return html;
	} 
	//For data set combobox data
	public String getTableCombo()
	{
		
		String html = "";
		if(!this.newseat.equals("0"))
		{
			//		String query = 	" select GNUM_METATABLE_ID,GSTR_TABLE_NAME from GBLT_METATABLE_COLUMN_MST where GBL_ISVALID = '1' and GNUM_HOSPITAL_CODE ='"+this.hospitalcode+"' order by initcap(GSTR_TABLE_NAME)";
			
			/*Query Changed on 14th December 2009 Due to implementation of Data Set
			 * 
			 		String query =" SELECT      gnum_metatable_id || '#' || ( SELECT COUNT (*) FROM gblt_effetivefrom_table   WHERE gbl_isvalid = 1 AND gstr_table_name = a.gstr_table_name) "+
			              " || '#' || nvl((SELECT gstr_query  FROM gblt_effetivefrom_table  WHERE gbl_isvalid = 1 AND gstr_table_name = a.gstr_table_name),'NA'),gstr_table_name " +
					" FROM gblt_metatable_column_mst a    WHERE gbl_isvalid = '1' AND gnum_hospital_code = '"+this.hospitalcode+"' 	ORDER BY INITCAP (gstr_table_name) ";
			 */			
		/*--------------kanti---------------------*/
		//String query =" SELECT      gnum_metatable_id || '#' || ( SELECT COUNT (*) FROM gblt_effetivefrom_table   WHERE gbl_isvalid = 1 AND gstr_table_name = a.gstr_table_name) "+
        //    " || '#' || nvl((SELECT gstr_query  FROM gblt_effetivefrom_table  WHERE gbl_isvalid = 1 AND gstr_table_name = a.gstr_table_name),'NA') || '#' || gstr_table_name,NVL(INITCAP (GSTR_DATASET_NAME),gstr_table_name)  as dataSetName " +
		//" FROM gblt_metatable_column_mst a    WHERE gbl_isvalid = '1' AND gnum_hospital_code = '"+this.hospitalcode+"' 	ORDER BY dataSetName ";

			String query =" SELECT      gnum_metatable_id   || '#' || ( SELECT COUNT (*) FROM gblt_effetivefrom_table   WHERE gbl_isvalid = 1 AND upper(gstr_table_name) = upper(a.gstr_table_name)) "+
           " || '#' || nvl((SELECT gstr_query  FROM gblt_effetivefrom_table  WHERE gbl_isvalid = 1 AND upper(gstr_table_name) = upper(a.gstr_table_name)),'NA') || '#' || gstr_table_name"+
           "|| '#' || NVL ((SELECT gbl_isglobal  FROM gblt_effetivefrom_table  WHERE gbl_isvalid = 1   AND upper(gstr_table_name) = upper(a.gstr_table_name)), 0)|| '#' || gstr_column_name"+
          ",(NVL(INITCAP (GSTR_DATASET_NAME),gstr_table_name))  as dataSetName " +
	//" FROM gblt_metatable_column_mst a    WHERE gbl_isvalid = '1' AND gnum_hospital_code = '100' ORDER BY dataSetName ";
			" FROM gblt_metatable_column_mst a    WHERE gbl_isvalid = '1' ORDER BY dataSetName ";
			
			/*--------------kanti---------------------*/
			System.out.println("table combo query"+query);
			try
			{
				html = super.getCombo("",query,this.table,"",0);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println("Exception there in getTableCombo() "+ex);
			}
		}
		else
			html = "<option value='0'>Select</option>";
		
		
		return html;
	} 
	// for selection of particular dataset
	public String getColumnCombo()
	{
		String html = "";
		html = "<select name='firstSeat' multiple size='6' >";
		String query2="";
		String query1="";
		String hosCode="100";
		if(!(this.table==null) && !this.table.equals("") && !this.table.equals("0")) 
		{ 
			
			String[] tableArray=this.table.split("#");
			System.out.println(this.table);
			String query =" select GSTR_COLUMN_NAME , "+
						  " GSTR_DISPLAY_COLUMN , "+
						  " GSTR_TABLE_NAME  "+ 
						  " from GBLT_METATABLE_COLUMN_MST "+
						  " where GNUM_METATABLE_ID = '"+tableArray[0]+"'"+
			//" and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
			//" and GNUM_HOSPITAL_CODE = '100'"+
			" and GBL_ISVALID='1'";
			System.out.println("cloumn query---- "+query);
			HisResultSet rs = null;
			try
			{
				rs = super.getRecord(query,true);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();	
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			rs.next();
			
			String isGlobal=tableArray[4];
			System.out.println("igGlobal-------------------------------------->"+isGlobal);
			System.out.println("tableArray[2]---------------->"+tableArray[2]);
//if we don't get any query from the table GBLT_EFFETIVEFROM_TABLE
//hence we are making a dynamic query 			
			if(tableArray[2].equals("NA") )
			{
				
				query1 ="  select count(distinct "+rs.getString(1)+") from "+rs.getString(3)+" where " +
				" GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"' and GNUM_ISVALID=1 " +
				" and "+rs.getString(1)+			
			   " not in ( select GNUM_COLUMN_VALUE from GBLT_ROLE_SEAT_TABLE_DTL "+
			   " where  "+
			   " GNUM_SEATID='"+this.seat+"' "+
			   " and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
			   " and GNUM_MODULE_ID = '"+this.module+"'"+
			   " and GBL_ISVALID='1'"+
			   " and gnum_metatable_id = '"+tableArray[0]+"' )";
				HisResultSet rs1 = null;	
				try
				{
				rs1 = super.getRecord(query1,true);
				while(rs1.next())
				{					
					this.tablelength = rs1.getString(1);
					
					System.out.println(tablelength);
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Exception in getsecondSeatNames "+e);
				}	
				
				query2 ="  select distinct "+rs.getString(1)+","+rs.getString(2)+" from "+rs.getString(3)+" where " +
							" GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"' and GNUM_ISVALID=1 " +
							" and "+rs.getString(1)+			
						   " not in ( select GNUM_COLUMN_VALUE from GBLT_ROLE_SEAT_TABLE_DTL "+
						   " where  "+
						   " GNUM_SEATID='"+this.seat+"' "+
						   " and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
						   " and GNUM_MODULE_ID = '"+this.module+"'"+
						   " and GBL_ISVALID='1'"+
						   " and gnum_metatable_id = '"+tableArray[0]+"' )";
						if(Integer.parseInt(this.tablelength)>200)
						{
							
							query2=query2 + " and UPPER("+rs.getString(2)+") like 'A%'";
							this.strAlphbet="A";
						}
						else{
							query2=query2 + " and UPPER("+rs.getString(2)+") like '%'";
							this.strAlphbet="%";
						}
						query2=query2 + " order by "+rs.getString(2);
				
				
				System.out.println("dynamic Query  for left combo is-----"+query2);
				
				HisResultSet rs2 = null;	
				try
				{
					rs2 = super.getRecord(query2,true);
					while(rs2.next())
					{					
						html += "<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>";
						previousMenuList += rs2.getString(1).toString()+"^";
					}
					
				
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Exception in getsecondSeatNames "+e);
				}	
		
			 	}
		//else we will get the query from the table    GBLT_EFFETIVEFROM_TABLE
			else if(tableArray[1].equals("1") && !tableArray[2].equals(""))
			{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet newRs = null;
					
					query1 ="  select count(distinct "+rs.getString(1)+") from "+rs.getString(3)+" where " +
					" GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"' " +
					" and "+rs.getString(1)+			
				   " not in ( select GNUM_COLUMN_VALUE from GBLT_ROLE_SEAT_TABLE_DTL "+
				   " where  "+
				   " GNUM_SEATID='"+this.seat+"' "+
				   " and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
				   " and GBL_ISVALID='1'"+
				  " AND GNUM_MODULE_ID ='"+this.module+"'"+
				   " and gnum_metatable_id = '"+tableArray[0]+"' )";
					HisResultSet rs1 = null;	
					try
					{	
					rs1 = super.getRecord(query1,true);
					while(rs1.next())
					{					
						this.tablelength = rs1.getString(1);
						
						System.out.println(tablelength);
					}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						e.printStackTrace();
					}
					
					try
					{	
						String queryFromTable=tableArray[2];	
						queryFromTable+=" NOT IN (SELECT gnum_column_value FROM gblt_role_seat_table_dtl WHERE "+
						" gnum_seatid = '"+ this.seat+"' AND gnum_hospital_code = '"+this.hospitalcode+"' AND gbl_isvalid = 1  AND GNUM_MODULE_ID ='"+this.module+"'"+
						" AND gnum_metatable_id = '"+tableArray[0]+"') "+						
						"ORDER BY orderByName";
						System.out.println("queryFromTable for left combo is-----"+queryFromTable);
						conn = super.getConnection();
						conn.setAutoCommit(false);
						ps = conn.prepareStatement(queryFromTable,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					    //ps.setString(1,this.hospitalcode);
					    if(isGlobal.equals("1"))
						{
							ps.setString(1,hosCode);
							
						}
						else
						{
							ps.setString(1,this.hospitalcode);
						}
						if(Integer.parseInt(this.tablelength)<200)
						{
							this.strAlphbet="A";
							ps.setString(2,"%");
						}
						else
						{
							this.strAlphbet="%";
							ps.setString(2,"A%");
						}
						newRs=ps.executeQuery();
						conn.commit();
						conn.setAutoCommit(true);
					}
					catch(Exception e)
					{
						e.printStackTrace();
						e.printStackTrace();
					}
					try
					{	newRs.beforeFirst();
					   
						while(newRs.next())
						{
							html += "<option value='"+newRs.getString(1)+"'>"+newRs.getString(2)+"</option>";
							previousMenuList += newRs.getString(1).toString()+"^";
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
		}//inner if else closed
	
 }
		html += "</select>";
    	html += "<input type='hidden' name='previousMenuList1' value='"+previousMenuList1+"'>";
		return html;
}		
	
	
	public String getsecondColumnCombo()
	{
		String html = "";
		String previousMenuList = "";
		html = "<select name='secondSeat'  multiple size='6'>";
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
						  //" and GNUM_HOSPITAL_CODE = '100'"+
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
			String isGlobal=tableArray[4];

if(tableArray[2].equals("NA"))
			{
	query2 =" select distinct "+rs.getString(1)+","+rs.getString(2)+" from "+rs.getString(3)+" where " +
				   " GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"' and GNUM_ISVALID=1 " +
				   " and  "+rs.getString(1)+			
				   " in ( select GNUM_COLUMN_VALUE from GBLT_ROLE_SEAT_TABLE_DTL "+
				   " where  "+
				   " GNUM_SEATID='"+this.seat+"' "+
				   " and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
				   " and GBL_ISVALID='1' "+					
				   " AND GNUM_MODULE_ID='"+this.module+"' "+
				   " and gnum_metatable_id = '"+tableArray[0]+"' )"+	
				   " order by "+rs.getString(2);
	
	System.out.println("dynamic Query  for right combo is----"+query2);
	
	HisResultSet rs2 = null;	
	try
	{
		rs2 = super.getRecord(query2,true);
		while(rs2.next())
		{					
			html += "<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>";
			previousMenuList += rs2.getString(1).toString()+"^";
		}
	}
	catch(Exception e)
	{
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
		
		queryFromTable+=" IN (SELECT gnum_column_value FROM gblt_role_seat_table_dtl WHERE "+
		"  gnum_seatid = '"+ this.seat+"' AND gnum_hospital_code = '"+this.hospitalcode+"' AND gbl_isvalid = 1 " +
		"  AND  GNUM_MODULE_ID ='"+this.module+"' AND gnum_metatable_id = '"+tableArray[0]+"') ORDER BY orderByName";
		
		
		System.out.println("queryFromTable for right combo is-----"+queryFromTable);
		conn = super.getConnection();
		conn.setAutoCommit(false);
		ps = conn.prepareStatement(queryFromTable, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	
		
		
		if(isGlobal.equals("1"))
		{
			ps.setString(1,"100");
			
		}
		else
			ps.setString(1,this.hospitalcode);
			ps.setString(2,"%");
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
				html += "<option value='"+newRs.getString(1)+"'>"+newRs.getString(2)+"</option>";
				previousMenuList += newRs.getString(1).toString()+"^";
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in Making the combo "+e);
		}
		finally
		{
			super.closeConnection(conn);
		}
		
	
	   }
	
	}
		html += "</select>";
		html += "<input type='hidden' name='previousMenuList' value='"+previousMenuList+"'>";
		return html;	
 }
	
	
	public boolean  insertRecord_old() throws Exception
	{
		status	=	"Record Successfully inserted !";	
		Connection conn = null;
		PreparedStatement ps = null;
		boolean retVal = true;
		try
		{	String[] tableArray=this.table.split("#");
		
			String query= 	" insert into GBLT_ROLE_SEAT_TABLE_DTL "+
							" (GNUM_ROLE_ID, "+
							" GNUM_MODULE_ID, "+ 
							" GNUM_SEATID, "+
							" GNUM_METATABLE_ID,"+
							" GNUM_COLUMN_VALUE, "+
							" GDT_ENTRY_DATE, "+
							" GBL_ISVALID, "+
							" GDT_EFFECT_DATE,"+
							" GNUM_SEAT_ID )"+
							" values ('"+this.role+"','"+this.module+"','"+this.seat+"','"+tableArray[0]+"',?,'"+this.getSysdate()+"','1','"+this.getSysdate()+"','10001')";
			System.out.println("query is"+query);
			conn = super.getConnection();
			conn.setAutoCommit(false);
			System.out.println("hi");
			ps = conn.prepareStatement(query);
			StringTokenizer st = new StringTokenizer(this.selectedValues,"^");
			while(st.hasMoreElements())
			{		
				String colVal =st.nextElement().toString();
				
				ps.setString(1,colVal);
				ps.addBatch();	
			
			}
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			retVal = true;
			this.status="Data Successfully inserted";
			System.out.println(status);
		}
		catch(Exception e)
		{
			retVal = false;
			this.status = "Error while Inserting Records";
			System.out.println(status);
			
		}
		finally
		{
			try
			{
				ps.close();
				conn.close();				
			}
			catch(Exception e2)
			{
				
			}			
		}
		return retVal;
	}//end of insert query	
	
	
	public boolean insertRecord(HttpServletRequest req)
	{
		System.out.println("inside ----------------");
		status	=	"Record Successfully inserted";
		boolean retVal = true;
		List lstOne = new ArrayList();
		List lstTwo = new ArrayList();
		List lstName = new ArrayList();
		
		System.out.println("previousMenuList+++++++++"+previousMenuList);
		
		String[] tableArray=this.table.split("#");
		
		
		
		StringTokenizer stOne = new StringTokenizer(this.previousMenuList,"^");
		
		while(stOne.hasMoreElements())
		{
			System.out.println("check 1");
			lstOne.add(stOne.nextToken().toString());		
		
		}
		
		
		StringTokenizer stTwo = new StringTokenizer(this.selectedValues,"^");
		while(stTwo.hasMoreElements())
		{
			System.out.println("check2");
			lstTwo.add(stTwo.nextToken().toString());		
		}		
		
		
		StringTokenizer stName = new StringTokenizer(this.selectedNames,"^");
		while(stName.hasMoreElements())
		{
			System.out.println("check2");
			lstName.add(stName.nextToken().toString());		
		}
		
		
		List recToInsert = new ArrayList();
		List recToDelete = new ArrayList();
		
		int i = 0;
		
		//FINDING  THE COLUMN VALUES TO BE DELETED
		for( i=0;i<lstOne.size();i++)
		{
			boolean found = false;
			for(int t=0;t<lstTwo.size();t++)			
			{
				if(lstOne.get(i).toString().equals(lstTwo.get(t).toString()))
				{
					found = true;
					break;
				}
				
			}
			if(!found)
				recToDelete.add(lstOne.get(i));	
		}
		
		
		//FINDING THE COLUMN VALUES TO BE INSERTED
		for( i=0;i<lstTwo.size();i++)
		{
			
			boolean found = false;
			for(int t=0;t<lstOne.size();t++)			
			{
				if(lstTwo.get(i).toString().equals(lstOne.get(t).toString()))
				{
					found = true;
					break;
				}
				
			}
			if(!found)
				{
				recToInsert.add(lstTwo.get(i));  	//adding the values to insert 
				recToInsert.add(lstName.get(i));    //adding the names to insert
				}
		}
		String strToDelete = "";
		Connection conn = null;
		Statement stmt = null;
		
		try
		{
			conn = super.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			if(recToDelete.size()!=0)
			{
				
				for(i=0;i<recToDelete.size();i++)
				strToDelete += recToDelete.get(i).toString()+",";
				strToDelete = strToDelete.substring(0,strToDelete.length()-1);
				String query1 = "";
							query1 = 	" DELETE FROM GBLT_ROLE_SEAT_TABLE_DTL "+
										" WHERE " +
										" GNUM_SEATID ='"+this.seat+"'AND" +
										" GNUM_MODULE_ID = '"+this.module+"' AND "+
										" GNUM_METATABLE_ID ='"+tableArray[0]+"'AND" +
										" GNUM_COLUMN_VALUE IN ("+strToDelete+") and GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' ";
							stmt.addBatch(query1);
						
							
			}
					
			if(recToInsert.size()!=0)
			{
				String query2 = "";
				for(i=0;i<recToInsert.size();i=i+2)
				{
					query2=  " insert into GBLT_ROLE_SEAT_TABLE_DTL "+
								 
						"( GNUM_SEATID, "+
						" GNUM_MODULE_ID,"+
						" GNUM_METATABLE_ID,"+
						" GNUM_COLUMN_VALUE, "+
						" GDT_ENTRY_DATE, "+
						" GBL_ISVALID, "+
						" GDT_EFFECT_DATE,"+
						" GNUM_SEAT_ID, "+
						" GNUM_HOSPITAL_CODE ,GSTR_DISPLAY_VALUE,GSTR_TABLE_NAME,GSTR_COLUMN_NAME)"+
						" values ('"+this.seat+"','"+this.module+"','"+tableArray[0]+"','"+recToInsert.get(i).toString()+"',sysdate,'1',sysdate,'"+this.seatId+"','"+this.hospitalcode+"','"+recToInsert.get(i+1).toString()+"','"+tableArray[3]+"','"+tableArray[5]+"')";	
				System.out.println("Insert Query"+query2);
					stmt.addBatch(query2);
					
				}
			}

			stmt.executeBatch();
					conn.commit();
					conn.setAutoCommit(true);
			
		}
		catch
		(Exception ex)
		{
			retVal = false;
			this.status = "Exception while Modifying Record :"+ex;
			
			try
			{
				conn.rollback();
			}
			catch(Exception e2)
			{
				System.out.println("Exception in rollback "+e2);
			}
		
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				
				if(conn!=null)
					conn.close();
			}
			catch(Exception e2)
			{
				System.out.println("Exception in rollback "+e2);
			}
		
		}		
		return retVal;
	}	//end of update record function
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}


	public String getLeftColumnComboForAjax(String strGroup,String seat,String table,String hospitalcode,String strRightColumnList,String strAlpha,String strModule)
	{
		if(strAlpha==null||strAlpha.equals("null")){
			strAlpha="%";
		}
		String html = "";
		html = "<select name='firstSeat' multiple size='6' >";
		String query2="";
		String ghospitalcode ="100";
		if(!(table==null) && !table.equals("") && !table.equals("0")) 
		{ 
			
			String[] tableArray=table.split("\\$");
			System.out.println(table);
//			System.out.println(tableArray[1]);
			String isGlobal=tableArray[4];
			String query =" select GSTR_COLUMN_NAME , "+
						  " GSTR_DISPLAY_COLUMN , "+
						  " GSTR_TABLE_NAME  "+ 
						  " from GBLT_METATABLE_COLUMN_MST "+
						  " where GNUM_METATABLE_ID = '"+tableArray[0]+"'"+
			//" and GNUM_HOSPITAL_CODE = '"+hospitalcode+"'"+
			//" and GNUM_HOSPITAL_CODE = '100'"+
			" and GBL_ISVALID='1'";
			
			System.out.println("cloumn query---- "+query);
			HisResultSet rs = null;
			try
			{
				rs = super.getRecord(query,true);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();	
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			rs.next();
			
			
//if we don't get any query from the table GBLT_EFFETIVEFROM_TABLE
//hence we are making a dynamic query 			
	if(tableArray[2].equals("NA") )
			 	{
		query2 ="  select distinct "+rs.getString(1)+","+rs.getString(2)+" from "+rs.getString(3)+" where " +
					" GNUM_HOSPITAL_CODE = '"+hospitalcode+"' and GNUM_ISVALID=1 " +
					" and "+rs.getString(1)+			
				   " not in ( select GNUM_COLUMN_VALUE from GBLT_ROLE_SEAT_TABLE_DTL "+
				   " where  "+
				   " GNUM_SEATID='"+seat+"' "+
				   " and GNUM_HOSPITAL_CODE = '"+hospitalcode+"'"+
				   " and GNUM_MODULE_ID = '"+strModule+"'"+
				   " and GBL_ISVALID='1'"+
				   " and gnum_metatable_id = '"+tableArray[0]+"' )" +
				   " and UPPER("+rs.getString(2)+") like '"+strAlpha+"%'";
		if(strRightColumnList!=null && !strRightColumnList.trim().equals(""))
			query2+=   " and "+rs.getString(1)+" not in ("+strRightColumnList+")" ;
		
		query2+= " order by "+rs.getString(2);
		
		
		
		
		System.out.println("dynamic Query  for left combo is-----"+query2);
		
		HisResultSet rs2 = null;	
		try
		{
			rs2 = super.getRecord(query2,true);
			while(rs2.next())
			{					
				html += "<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>";
				previousMenuList += rs2.getString(1).toString()+"^";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in getsecondSeatNames "+e);
		}	
		
			 	}
			else//else we will get the query from the table    GBLT_EFFETIVEFROM_TABLE
	if(tableArray[1].equals("1") && !tableArray[2].equals(""))
{
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet newRs = null;
		
		try
		{	
		
			String queryFromTable=tableArray[2];	
			
			queryFromTable+=" NOT IN (SELECT gnum_column_value FROM gblt_role_seat_table_dtl WHERE "+
			" gnum_seatid = '"+ seat+"' AND gnum_hospital_code = '"+hospitalcode+"' AND gbl_isvalid = 1 " +
			" AND gnum_metatable_id = '"+tableArray[0]+"') " ;
			
			
			if(strRightColumnList!=null && !strRightColumnList.trim().equals(""))
				queryFromTable+="AND (select GSTR_COLUMN_NAME from gblt_metatable_column_mst where GNUM_METATABLE_ID ='"+tableArray[0]+"'  AND GBL_ISVALID=1) "+ "not in ("+strRightColumnList+")";
			
			queryFromTable+=" ORDER BY orderByName";
			
			
			System.out.println("queryFromTable for left combo is-----"+queryFromTable);
			conn = super.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(queryFromTable,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		
			if(isGlobal.equals("1")){
				ps.setString(1,ghospitalcode);
			}
			else
				ps.setString(1,hospitalcode);
					
				
				ps.setString(2,strAlpha+"%");
				newRs=ps.executeQuery();
			
			conn.commit();
			conn.setAutoCommit(true);
			}
		catch(Exception e)
		{
			e.printStackTrace();
			e.printStackTrace();
		}
			
					
			
			
	
			try
			{	
				
				//System.out.println("---------"+newRs.first()+"------------");
				newRs.beforeFirst();
			   
				while(newRs.next())
				{
					html += "<option value='"+newRs.getString(1)+"'>"+newRs.getString(2)+"</option>";
					previousMenuList += newRs.getString(1).toString()+"^";
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
   }//inner if else closed
	
	 }
			html += "</select>";
	    	html += "<input type='hidden' name='previousMenuList1' value='"+previousMenuList1+"'>";
			return html;
	}	
	
	///////////////////////////////////////
	public String getRightColumnComboForAjax()
	{
		String html = "";
		String previousMenuList = "";
		html = "<select name='secondSeat'  multiple size='6'>";
		String query2="";
		String ghospitalcode="100";
				
		if(!(table==null) && !table.equals("") && !table.equals("0")) 
		{		
			String[] tableArray=table.split("#");
			String isGlobal=tableArray[4];
			String query =" select GSTR_COLUMN_NAME , "+
						  " GSTR_DISPLAY_COLUMN , "+
						  " GSTR_TABLE_NAME  "+ 
						  " from GBLT_METATABLE_COLUMN_MST "+
						  " where GNUM_METATABLE_ID = '"+tableArray[0]+"'"+
						  //" and GNUM_HOSPITAL_CODE = '"+hospitalcode+"'"+
						 // " and GNUM_HOSPITAL_CODE = '100'"+
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
				   " GNUM_HOSPITAL_CODE = '"+hospitalcode+"' and GNUM_ISVALID=1 " +
				   " and  "+rs.getString(1)+			
				   " in ( select GNUM_COLUMN_VALUE from GBLT_ROLE_SEAT_TABLE_DTL "+
				   " where  "+
				   " GNUM_SEATID='"+seat+"' "+
				   " and GNUM_HOSPITAL_CODE = '"+hospitalcode+"'"+
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
			html += "<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>";
			previousMenuList += rs2.getString(1).toString()+"^";
		}
	}
	catch(Exception e)
	{
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
		
		queryFromTable+=" IN (SELECT gnum_column_value FROM gblt_role_seat_table_dtl WHERE "+
		"  gnum_seatid = '"+ seat+"' AND gnum_hospital_code = '"+hospitalcode+"' AND gbl_isvalid = 1 " +
		" AND gnum_metatable_id = '"+tableArray[0]+"') ORDER BY orderByName";
		
		
		System.out.println("queryFromTable for right combo is-----"+queryFromTable);
		conn = super.getConnection();
		conn.setAutoCommit(false);
		ps = conn.prepareStatement(queryFromTable, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	
		
		if(isGlobal.equals("1")){
			ps.setString(1,ghospitalcode);
		}
		else
			ps.setString(1,hospitalcode);
			//ps.setString(1,hospitalcode);
			
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
				html += "<option value='"+newRs.getString(1)+"'>"+newRs.getString(2)+"</option>";
				previousMenuList += newRs.getString(1).toString()+"^";
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in Making the combo "+e);
		}
		finally
		{
			super.closeConnection(conn);
		}
		
	
	   }
	
	}
		html += "</select>";
		html += "<input type='hidden' name='previousMenuList' value='"+previousMenuList+"'>";
		return html;	
 }
	public String getTablelength() {
		return tablelength;
	}
	public void setTablelength(String tablelength) {
		this.tablelength = tablelength;
	}
}//end of main class
