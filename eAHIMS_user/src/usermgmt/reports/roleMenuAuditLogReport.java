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

public class roleMenuAuditLogReport extends FuncLib {
	
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
	private String group="";
	private String noOfSelectedRoles="";
	private String noOfUnSelectedRoles="";
	
	
	
	
	
	public String getNoOfSelectedRoles() {
		return noOfSelectedRoles;
	}
	public void setNoOfSelectedRoles(String noOfSelectedRoles) {
		this.noOfSelectedRoles = noOfSelectedRoles;
	}
	public String getNoOfUnSelectedRoles() {
		return noOfUnSelectedRoles;
	}
	public void setNoOfUnSelectedRoles(String noOfUnSelectedRoles) {
		this.noOfUnSelectedRoles = noOfUnSelectedRoles;
	}
	public String getSelectedValues() {
		return selectedValues;
	}
	public void setSelectedValues(String selectedValues) {
		this.selectedValues = selectedValues;
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
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getNewseat() {
		return newseat;
	}
	public void setNewseat(String newseat) {
		this.newseat = newseat;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getEffect_date() {
		return effect_date;
	}
	public void setEffect_date(String effect_date) {
		this.effect_date = effect_date;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getMenuSelected() {
		return menuSelected;
	}
	public void setMenuSelected(String menuSelected) {
		this.menuSelected = menuSelected;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	public void initializeNewMode()
	{
		this.seat 	= "";
		this.group 		= "";
				
		
	}//end of initilize mode
	
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	
	
	public String getModuleCombo()
	{		
		System.out.println("hospital codeaaaaaaaaaaaaaaaaaaa"+this.getHospitalcode());
		String html = "";
		
	
		String query=" SELECT   gnum_module_id, INITCAP (gstr_module_name) "+
	    			 " FROM gblt_metatable_type_mst m "+
					// " WHERE m.gnum_hospital_code = '"+this.hospitalcode+"' AND gbl_isvalid = 1 "+
					// " WHERE m.gnum_hospital_code = '100' AND gbl_isvalid = 1 "+
					 " WHERE gbl_isvalid = 1 "+
					 " ORDER BY INITCAP (gstr_module_name) ";
		
		/*String query=" SELECT   gnum_module_id, INITCAP (gstr_module_name) "+
		 " FROM gblt_metatable_type_mst m "+
		 " WHERE m.gnum_hospital_code = '100' AND gbl_isvalid = 1 "+
		 " ORDER BY INITCAP (gstr_module_name) ";*/
		
		System.out.println("getGroupCombo---------"+query);
		try
		{
			html = super.getCombo("",query,this.module,"",0);
			//System.out.println("group codeeeeeeeeee"+this.group);
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in getGroupCombo() "+ex);
		}
		return html;
	}
	public String getRoleCombo()
	{
	String html = "";
		
		if(this.module.length()>0)
	{
		
		if(!this.module.equals("0"))
		{
			String query =" SELECT   gnum_role_id, INITCAP (gstr_role_name)  FROM gblt_role_mst  " +
					      " WHERE gnum_module_id = '"+this.module+"'  AND gbl_isvalid = '1'  AND gnum_hospital_code = '"+this.hospitalcode+"' "+
						  "	ORDER BY INITCAP (gstr_role_name) ";

			try
			{
				html = super.getCombo("",query,this.role,"",0);
			}
			catch(Exception ex)
			{
				System.out.println("Exception there in getRoleCombo() "+ex);
			}
		}
	}
		else
			html = "<option value='0'>Select</option>";
		return html;
	} 
	

	
	
	public String getSerialNumber(String roleid)
	{
		String slno="0";
		String Query=" SELECT nvl(max(GNUM_SEAT_ROLE_SLNO),0) FROM GBLT_SEAT_ROLE_MST where GNUM_role_id="+roleid+" and GNUM_SEATID='"+this.seat+"'"+
						" AND gnum_hospital_code = '"+this.hospitalcode+"' ";			
						/*" AND GDT_EFFECTIVE_FRM <= SYSDATE " +
						" AND (TO_DATE (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
						" TO_DATE (SYSDATE, 'DD-MM-YY')"+
						" )"; */
					
		HisResultSet rs = null;
		
		
		System.out.println("getSerialNumber query------"+Query);
		try
		{
			rs = super.getRecord(Query,true);
			rs.next();
			slno = rs.getString(1);
			if(slno.equals("0")) slno="0";
		}catch(Exception e){System.out.println("Exception Occur:"+ e);}
		return slno;
	}
	
	public String getIsValid(String roleid)
	{
		String serial = getSerialNumber(roleid);
		String isValid="";
		String Query="SELECT GBL_ISVALID FROM GBLT_SEAT_ROLE_MST where " +
				" GNUM_role_id="+roleid+" and GNUM_SEAT_ROLE_SLNO="+serial+" and GNUM_SEATID='"+this.seat+"' "+
				" AND gnum_hospital_code = '"+this.hospitalcode+"' ";
		HisResultSet rs = null;
		
	System.out.println("getIsValid query-----"+Query);
		try
		{
			rs = super.getRecord(Query,true);
			rs.next();
			isValid = rs.getString(1);		
		}catch(Exception e){System.out.println("Exception Occur:"+ e);}
		return isValid;
	}
	
	public String getListOfUnSelectedMenus(HttpServletRequest request)
	{
		String html = "";
		int i=0;
		if(!(this.role==null) && !this.role.equals("") && !this.role.equals("0")) 
		{
			
			
//String query1 ="SELECT gnum_menu_id AS menuid,(SELECT gstr_menu_name  FROM gblt_menu_mst WHERE gnum_menu_id = a.gnum_menu_id    AND gnum_hospital_code = a.gnum_hospital_code "+
			   //" AND gnum_slno =(SELECT MAX (gnum_slno) FROM gblt_menu_mst  WHERE gnum_menu_id = a.gnum_menu_id  AND gnum_hospital_code = a.gnum_hospital_code  AND gnum_isvalid = 1) "+
			  // " AND gnum_isvalid = 1) as menuName,TO_CHAR (gdt_effective_frm, 'dd-Mon-yyyy') AS effectivefrom,TO_CHAR (gdt_effective_to, 'dd-Mon-yyyy') AS effectiveto, 'InActive' "+
			  // " FROM gblt_role_menu_mst a  WHERE gnum_role_id = '"+this.role+"' AND gnum_hospital_code ='"+this.hospitalcode+"' AND gnum_isvalid = 0 ORDER BY effectiveFrom desc,effectiveTo desc "; 

			
String query1 ="SELECT gnum_menu_id AS menuid,(SELECT gstr_menu_name  FROM gblt_menu_mst WHERE gnum_menu_id = a.gnum_menu_id    AND gnum_hospital_code = 100 "+
" AND gnum_slno =(SELECT MAX (gnum_slno) FROM gblt_menu_mst  WHERE gnum_menu_id = a.gnum_menu_id  AND gnum_hospital_code = 100  AND gnum_isvalid = 1) "+
" AND gnum_isvalid = 1) as menuName,TO_CHAR (gdt_effective_frm, 'dd-Mon-yyyy') AS effectivefrom,TO_CHAR (gdt_effective_to, 'dd-Mon-yyyy') AS effectiveto, 'InActive' "+
" FROM gblt_role_menu_mst a  WHERE gnum_role_id = '"+this.role+"' AND gnum_hospital_code ='"+this.hospitalcode+"' AND gnum_isvalid = 0 ORDER BY effectiveFrom desc,effectiveTo desc ";	
			
			HisResultSet rs = null;
			
			
			System.out.println("getListOfUnSelectedMenus query------"+query1);
			try
			{
				rs = super.getRecord(query1,true);
				String str1 = "";
				String str2 = "";
				String str3 = "";
				String str4 = "";
				String str5 = "";
				String superMenu="";
				
							
				
				//System.out.println("number of rows"+rs.size());
		  if(rs!=null)
		      {	
			   
				while(rs.next())
			    	{
					
					
					str1 = rs.getString(1);
					str2 = rs.getString(2);
					str3 = rs.getString(3);
					str4 = rs.getString(4);
					str5 = rs.getString(5);
			
					
					if(str1.substring(0,1).equals("1"))
						superMenu="(Services)";
						else
					if(str1.substring(0,1).equals("2"))
						superMenu="(Setup)";
						else
					if(str1.substring(0,1).equals("3"))
						superMenu="(Reports)";
						else
					if(str1.substring(0,1).equals("4"))
						superMenu="(Help)";


					
					
					
					html+="<tr>";		

					html+="<td class='adddatalabel' width='60%'>"+str2+"<strong>"+superMenu+"</strong></td>";          
					html+="<td class='adddatalabel' width='15%'>"+str3+"</td>";
					html+="<td class='adddatalabel' width='15%'>"+str4+"</td>";       
					html+="<td class='adddatalabel' width='10%'>"+str5+"</td>";

					html+="</tr>";
									
			

					
				   }
			
			   }
				
			}
			catch(Exception e)
			{
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			
		}
		
		return html;
		
	}

	public String getListOfMenus(HttpServletRequest request)
	{
		 String servicesBuffer = "<tr><td class='adddatalabelNewRight' width='50%'><b>Services</b></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='10%'></td></tr>";
		 String setupBuffer = "<tr><td class='adddatalabelNewRight' width='50%'><b>Setup</b></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='10%'></td></tr>";
		 String reportsBuffer="<tr><td class='adddatalabelNewRight' width='50%'><b>Reports</b></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='10%'></td></tr>";
		 String helpBuffer="<tr><td class='adddatalabelNewRight' width='50%'><b>Help</b></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='15%'></td><td class='adddatalabelNewRight' width='10%'></td></tr>";
		 
		 
		 
		 int serviceCount=0;
		 int setupCount=0;
		 int reportsCount=0;
		 int helpCount=0;
		
		String html = "";
		int i=0;
		if(!(this.role==null) && !this.role.equals("") && !this.role.equals("0")) 
		{
		

//String query ="SELECT gnum_menu_id AS menuid,(SELECT gstr_menu_name  FROM gblt_menu_mst WHERE gnum_menu_id = a.gnum_menu_id    AND gnum_hospital_code = a.gnum_hospital_code "+
			//" AND gnum_slno =(SELECT MAX (gnum_slno) FROM gblt_menu_mst  WHERE gnum_menu_id = a.gnum_menu_id  AND gnum_hospital_code = a.gnum_hospital_code  AND gnum_isvalid = 1) "+
			//" AND gnum_isvalid = 1) as menuName,TO_CHAR (gdt_effective_frm, 'dd-Mon-yyyy') AS effectivefrom,TO_CHAR (gdt_effective_to, 'dd-Mon-yyyy') AS effectiveto,  decode(gnum_isvalid,1,'Active','InActive' ) as status "+
			//" FROM gblt_role_menu_mst a  WHERE gnum_role_id = '"+this.role+"' AND gnum_hospital_code ='"+this.hospitalcode+"' ORDER BY menuName "; 

String query ="SELECT gnum_menu_id AS menuid,(SELECT gstr_menu_name  FROM gblt_menu_mst WHERE gnum_menu_id = a.gnum_menu_id   "+
//" AND gnum_slno =(SELECT MAX (gnum_slno) FROM gblt_menu_mst  WHERE gnum_menu_id = a.gnum_menu_id  AND gnum_hospital_code = 100  AND gnum_isvalid = 1) "+
" AND gnum_isvalid = 1) as menuName,TO_CHAR (gdt_effective_frm, 'dd-Mon-yyyy') AS effectivefrom,TO_CHAR (gdt_effective_to, 'dd-Mon-yyyy') AS effectiveto,  decode(gnum_isvalid,1,'Active','InActive' ) as status "+
" FROM gblt_role_menu_mst a  WHERE " +
"exists(select 'x' from gblt_menu_mst where gnum_menu_id = a.gnum_menu_id and gnum_isvalid=1) and "+
//"exists(select 'x' from gblt_menu_mst where gnum_menu_id = a.gnum_menu_id and gnum_hospital_code = 100 and gnum_isvalid=1) and "+
"gnum_role_id = '"+this.role+"' AND gnum_hospital_code ='"+this.hospitalcode+"' ORDER BY menuName "; 

			

			HisResultSet rs = null;
			
			
			System.out.println("getListOfMenus query---->"+query);
try
{
				rs = super.getRecord(query,true);
				String str1 = "";
				String str2 = "";
				String str3 = "";
				String str4 = "";
				String str5 = "";
				String superMenu="";
			if(rs!=null)
			{	
				
				while(rs.next())
				{
							
					
					str1 = rs.getString(1);
					str2 = rs.getString(2);
					str3 = rs.getString(3);
					str4 = rs.getString(4);
					str5=rs.getString(5);
					
					



					
					
					
			
if(str1.substring(0,1).equals("1"))
			{
				serviceCount++;
				
				
				servicesBuffer+="<tr>";
				servicesBuffer+="<td class='adddatavalueNew' width='50%'>"+str2+"</td>"; 
				servicesBuffer+="<td class='adddatavalueNew' width='20%'>"+str3+"</td>";
				servicesBuffer+="<td class='adddatavalueNew' width='20%'>"+str4+"</td>";       
				servicesBuffer+="<td class='adddatavalueNew' width='10%'>"+str5+"</td>";
				servicesBuffer+="</tr>";		
			
			}
			else
if(str1.substring(0,1).equals("2"))
				{
					setupCount++;
					
					setupBuffer+="<tr>";		
					setupBuffer+="<td class='adddatavalueNew' width='50%'>"+str2+"</td>"; 
					setupBuffer+="<td class='adddatavalueNew' width='20%'>"+str3+"</td>";
					setupBuffer+="<td class='adddatavalueNew' width='20%'>"+str4+"</td>";       
					setupBuffer+="<td class='adddatavalueNew' width='10%'>"+str5+"</td>";
					setupBuffer+="</tr>";		
				
				}
 			else
if(str1.substring(0,1).equals("3"))
			{
				reportsCount++;
				
				
				reportsBuffer+="<tr>";	
				reportsBuffer+="<td class='adddatavalueNew' width='50%'>"+str2+"</td>"; 
				reportsBuffer+="<td class='adddatavalueNew' width='20%'>"+str3+"</td>";
				reportsBuffer+="<td class='adddatavalueNew' width='20%'>"+str4+"</td>";       
				reportsBuffer+="<td class='adddatavalueNew' width='10%'>"+str5+"</td>";
				reportsBuffer+="</tr>";		
			
			}
			else
if(str1.substring(0,1).equals("4"))
			{
	
				helpCount++;
				
				
				helpBuffer+="<tr>";
				helpBuffer+="<td class='adddatavalueNew' width='50%'>"+str2+"</td>"; 
				helpBuffer+="<td class='adddatavalueNew' width='20%'>"+str3+"</td>";
				helpBuffer+="<td class='adddatavalueNew' width='20%'>"+str4+"</td>";       
				helpBuffer+="<td class='adddatavalueNew' width='10%'>"+str5+"</td>";
				helpBuffer+="</tr>";		
			
			}

			
					
                  
		}//while closed
				
				
     }	//if closed
			


	if(serviceCount > 0)
		html+=servicesBuffer;
	
	if(setupCount > 0)
		html+=setupBuffer;
	
	if(reportsCount > 0)
		html+=reportsBuffer;
	
	if(helpCount > 0)
		html+=helpBuffer;
	
	//if(serviceCount==0 && serviceCount==0 && reportsCount==0 && helpCount==0)
	
	
			
}//try closed
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			
		}
	
		
	
	
		return html;	
		
		
	}
}
