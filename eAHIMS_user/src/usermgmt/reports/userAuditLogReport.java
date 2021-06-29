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

public class userAuditLogReport extends FuncLib {
	

	private String hmode 	= "";
	private String seat 	= "";

	private String hospitalcode="";
	
	private String group="";
	private String userId="";
	private String seatId="";
	private String currentPasswd="";
	
	
	
	public String getCurrentPasswd() {
		return currentPasswd;
	}
	public void setCurrentPasswd(String currentPasswd) {
		this.currentPasswd = currentPasswd;
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
	public String getUserCombo()
	{	
		String html = "";
		if(this.group.length()>0)
		{
		if(!this.group.equalsIgnoreCase("0"))
		{
			String query =" SELECT gnum_userid,gstr_user_name   FROM gblt_user_mst a  WHERE gnum_hospital_code = '"+this.hospitalcode+"'    AND gnum_user_seatid IN  "+
						  "( SELECT gnum_seatid  FROM gblt_seat_mst  WHERE gnum_group_code = '"+this.group+"'  AND gnum_hospital_code = a.gnum_hospital_code  AND gnum_isvalid = 1)"; 
			
			System.out.println(" getUserCombo query---------"+query);
			
			try
			{
				html = super.getCombo("",query,this.userId,"",0);
			}
			catch(Exception ex)
			{   ex.printStackTrace();
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
		
	//System.out.println("getIsValid query-----"+Query);
		try
		{
			rs = super.getRecord(Query,true);
			rs.next();
			isValid = rs.getString(1);		
		}catch(Exception e){System.out.println("Exception Occur:"+ e);}
		return isValid;
	}
	
	public String getDetailsOfNewUsers(HttpServletRequest request)
	{
		String html = "";
		int i=0;
		if(!(this.userId==null) && !this.userId.equals("") && !this.userId.equals("0")) 
		{
			
			
			
String query =" SELECT decode(psrstr_emp_no,'','Non-Employee','Employee(' || psrstr_emp_no ||'/'|| gnum_designation || ')') AS empid, gstr_usr_name AS username,gnum_user_seatid AS seatid,(SELECT gstr_seat_name FROM gblt_seat_mst WHERE gnum_seatid = a.gnum_user_seatid   AND gnum_hospital_code = a.gnum_hospital_code) AS seatname, "+
			" (SELECT gstr_group_name  FROM gblt_group_mst WHERE gnum_hospital_code = a.gnum_hospital_code AND gnum_group_code = "+
			" (SELECT gnum_group_code  FROM gblt_seat_mst  WHERE gnum_seatid = a.gnum_user_seatid  AND gnum_hospital_code = a.gnum_hospital_code)) AS groupname, "+
			" TO_CHAR (gdt_effect_date, 'dd-Mon-yyyy') AS effectivedate,TO_CHAR (gdt_expiry_date, 'dd-Mon-yyyy') AS expirydate,    TO_CHAR (GDT_LSTMOD_DATE, 'dd-Mon-yyyy') AS lastmoddate, DECODE (gnum_isvalid, '1', 'Active', 'InActive') AS status,gnum_userlevel AS userlevel, gnum_designation AS desigid,DECODE (gnum_islock, '0', 'Unlocked', '1', 'Locked') as lockedStatus,  "+
			" gstr_email_id AS emailid, gnum_mobile_number AS mobileno,gnum_swapcard_number AS smartcardno,GSTR_PASSWORD as password,DECODE (LENGTH (GNUM_LSTMOD_SEATID),4, 'Admin',5, (SELECT gstr_usr_name FROM gblt_user_mst WHERE gnum_userid = a.GNUM_LSTMOD_SEATID AND gnum_hospital_code = a.GNUM_HOSPITAL_CODE),'') AS modifyBy,GDT_LSTMOD_DATE as ByModifyDate " +
			" FROM gblt_user_mst a   WHERE gnum_userid = '"+this.userId+"' AND gnum_hospital_code = '"+this.hospitalcode+"'  ORDER BY ByModifyDate DESC "; 





			HisResultSet rs = null;
			
						
			System.out.println("getDetailsOfUsers query------"+query);
			
			try
			{
				rs = super.getRecord(query,true);
				String str1 = "";
				String str2 = "";
				String str3 = "";
				String str4 = "";
				String str5 = "";
				
							
				
				//System.out.println("number of rows"+rs.size());
		  if(rs!=null)
		      {	
			   
			  int count=0;
			  String passwCheck="";
			  
				while(rs.next())
			    	{
					
				
							
					System.out.println("this pwd "+rs.getString(16));
				
					this.currentPasswd=rs.getString(16);
		
				
					html+="<tr>";	
					
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(17)+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(8)+"</td>";

					html+="<td class='adddatavalueNew' width='20%'>"+ rs.getString(1)+"</td>";          
					html+="<td class='adddatavalueNew' width='10%'>"+ rs.getString(2)+"</td>";
					
					html+="<td class='adddatavalueNew' width='10%'>"+ rs.getString(11)+"</td>";
					
					html+="<td class='adddatavalueNew' width='10%'>"+ rs.getString(4)+"</td>";          
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(5)+"</td>";
					
					html+="<td class='adddatavalueNew' width='5%'>"+ passwCheck+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(6)+"</td>";          
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(7)+"</td>";
					
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(10)+"</td>";          
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(9)+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(12)+"</td>";          
					html+="<td class='adddatavalueNew' width='15%'>"+ rs.getString(13)+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(14)+"</td>"; 
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(15)+"</td>"; 
					

					html+="</tr>";
									
			
					count++;	
					
				   }
			
			   }
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			
		}
		
		return html;
		
	}

	public String getDetailsOfOldUsers(HttpServletRequest request)
	{
		String html = "";
		int i=0;
		if(!(this.userId==null) && !this.userId.equals("") && !this.userId.equals("0")) 
		{
			
			
String query =" SELECT decode(psrstr_emp_no,'','Non-Employee','Employee(' || psrstr_emp_no ||'/'|| gnum_designation || ')') as empDetail, gstr_usr_name AS username,gnum_user_seatid AS seatid,(SELECT gstr_seat_name FROM gblt_seat_mst WHERE gnum_seatid = a.gnum_user_seatid   AND gnum_hospital_code = a.gnum_hospital_code) AS seatname, "+
			  " (SELECT gstr_group_name  FROM gblt_group_mst WHERE gnum_hospital_code = a.gnum_hospital_code AND gnum_group_code = "+
			  " (SELECT gnum_group_code  FROM gblt_seat_mst  WHERE gnum_seatid = a.gnum_user_seatid  AND gnum_hospital_code = a.gnum_hospital_code)) AS groupname, "+
			  " TO_CHAR (gdt_effect_date, 'dd-Mon-yyyy') AS effectivedate,TO_CHAR (gdt_expiry_date, 'dd-Mon-yyyy') AS expirydate, to_char(GDT_LSTMOD_DATE,'dd-Mon-yyyy') AS lastmoddate, DECODE (gnum_isvalid, '1', 'Active', 'InActive') AS status,gnum_userlevel AS userlevel, gnum_designation AS desigid,DECODE (gnum_islock, '0', 'Unlocked', '1', 'Locked') as lockedStatus,  "+
			  " gstr_email_id AS emailid, gnum_mobile_number AS mobileno,gnum_swapcard_number AS smartcardno,GSTR_PASSWORD as password, DECODE (LENGTH (GNUM_LSTMOD_SEATID),4, 'Admin',5, (SELECT gstr_usr_name FROM gblt_user_mst WHERE gnum_userid = a.GNUM_LSTMOD_SEATID AND gnum_hospital_code = a.GNUM_HOSPITAL_CODE),'') AS modifyBy,GDT_LSTMOD_DATE as ByModifyDate  FROM GBLT_USER_AUDITLOG a  " +
			  " WHERE gnum_userid = '"+this.userId+"' AND gnum_hospital_code = '"+this.hospitalcode+"' ORDER BY ByModifyDate DESC "; 
			




			HisResultSet rs = null;
			
			
		
			System.out.println("getDetailsOfUsers query------"+query);
			
			try
			{
				rs = super.getRecord(query,true);
				
							
				
				//System.out.println("number of rows"+rs.size());
		  if(rs!=null)
		      {	
			  ArrayList passwList=new ArrayList(); 
			  int count=0;
			  String passwChange="";
			  
				while(rs.next())
			    	{
					
					passwList.add(rs.getString(16));
							
					System.out.println("this pwd "+rs.getString(16));
				
				if(count!=0)
					System.out.println("last Pwd pwd "+passwList.get(count-1));
					
				
				
if(count==0)
				{	
					
			if(rs.getString(16).equals(this.currentPasswd) )
							passwChange="No";
						else
							passwChange="Yes";
				}	
				else
if(count!=0)
				{	
				
				if(rs.getString(16).equals(passwList.get(count-1)) )
						passwChange="No";
					else
						passwChange="Yes";
				}
				
		
				
if(count==0)
{
				html+="<tr >"; 
				html+="<td width='100%' class='ShadedSubTitleTagImage' colspan='16' align='left' style='width: 588px'>Old Record </td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";
//				html+="<td width=\"100%\" class=\"addheader\"></td>";				
				html+="</tr>"; 	
			    	
				html+="<tr>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Modified By</b></td>";   
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Modified Date</b></td>";          						            
				html+="<td class=\"adddatalabelNewRight\" width='15%'><b>Employee/Non-Employee</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='10%'><b>Name</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Designation</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='10%'><b>Seat Name</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Group Name</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Password Changed</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Effective Date</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Expiry Date</b></td>";      												
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>User Level</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Status</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Locked Status</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='15%'><b>Email Id</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Mobile No.</b></td>";
				html+="<td class=\"adddatalabelNewRight\" width='5%'><b>Smart Card No.</b></td>";      							
				html+="</tr>";
}				
				
				
				
					html+="<tr>";	
					
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(17)+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(8)+"</td>";

					html+="<td class='adddatavalueNew' width='20%'>"+ rs.getString(1)+"</td>";          
					html+="<td class='adddatavalueNew' width='10%'>"+ rs.getString(2)+"</td>";
					
					html+="<td class='adddatavalueNew' width='10%'>"+ rs.getString(11)+"</td>";
					
					html+="<td class='adddatavalueNew' width='10%'>"+ rs.getString(4)+"</td>";          
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(5)+"</td>";
					
					html+="<td class='adddatavalueNew' width='5%'>"+ passwChange+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(6)+"</td>";          
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(7)+"</td>";
					
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(10)+"</td>";          
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(9)+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(12)+"</td>";          
					html+="<td class='adddatavalueNew' width='15%'>"+ rs.getString(13)+"</td>";
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(14)+"</td>"; 
					html+="<td class='adddatavalueNew' width='5%'>"+ rs.getString(15)+"</td>"; 
					

					html+="</tr>";
									
			
					count++;	
					
				   }
			
			   }
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			
		}
		
		return html;
		
	}

	
}
