package usermgmt.masters;

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

public class UmgmtSeatRoleAddMstBean extends FuncLib {
	
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
	private String groupName="";
	private String group="";
	
	
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
	public String getPreviousMenuList() {
		return previousMenuList;
	}
	public void setPreviousMenuList(String previousMenuList) {
		this.previousMenuList = previousMenuList;
	}
	public String getPreviousIsValid() {
		return previousIsValid;
	}
	public void setPreviousIsValid(String previousIsValid) {
		this.previousIsValid = previousIsValid;
	}
	public String getPreviousMenuList1() {
		return previousMenuList1;
	}
	public void setPreviousMenuList1(String previousMenuList1) {
		this.previousMenuList1 = previousMenuList1;
	}
	public void initializeNewMode()
	{
		this.seat 	= "";
		this.group 		= "";
				
		
	}//end of initilize mode
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	
	
	public String getGroupCombo()
	{		
		System.out.println("hospital codeaaaaaaaaaaaaaaaaaaa"+this.getHospitalcode());
		String html = "";
		String query = "select GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST where GNUM_ISVALID = '1' and GNUM_HOSPITAL_CODE ='"+this.hospitalcode+"' order by initcap(GSTR_GROUP_NAME) ";
		//System.out.println("aaaaaaaaaa"+query);
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
		if(this.group.length()>0)
		{
		if(!this.module.equals("0"))
		{
			String query = 	" select"+ 
							" GNUM_SEATID, initcap(GSTR_SEAT_NAME)"+
							" from GBLT_SEAT_MST"+
							" where GNUM_GROUP_CODE = '"+this.group+"'"+
							" and GNUM_ISVALID = '1'"+
							" and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+							
							" order by initcap(GSTR_SEAT_NAME)";
			//System.out.println("newSeat combo query"+query);
			try
			{
				html = super.getCombo("",query,this.seat,"",0);
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
	public String getColumnCombo(){
		String html = "";
		html = "<select name='firstmenu' multiple size='5' >";
		if(!(this.seat==null) && !this.seat.equals("") && !this.seat.equals("0")) 
		{
			/*String query1 ="SELECT a.gnum_role_id, (SELECT gstr_role_name"+
                        " FROM gblt_role_mst where gnum_role_id=a.gnum_role_id and gnum_hospital_code=a.gnum_hospital_code) AS role"+
                        " FROM gblt_group_role_mst a"+
                        " WHERE gnum_group_code ='"+this.group+"'"+
                        " AND a.gnum_hospital_code = '"+this.hospitalcode+"'"+
                        " AND gnum_isvalid = '1'"+
                        " AND gdt_effective_frm <= SYSDATE"+
                        " AND (TO_DATE (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
                        " TO_DATE (SYSDATE, 'DD-MM-YY')"+
                        " )"+
                        " AND a.gnum_role_id not in"+
                        " ("+
                        " SELECT gnum_role_id"+
                        " FROM gblt_seat_role_mst"+
                        " WHERE gnum_seatid = '"+this.seat+"'"+
                        " AND gbl_isvalid = '1'"+
                        " AND gnum_hospital_code = '"+this.hospitalcode+"'"+
                        " AND gdt_effective_frm <= SYSDATE"+
                        " AND (TO_DATE (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
                        " TO_DATE (SYSDATE, 'DD-MM-YY')"+
                        " ))";*/
			
String query1 ="SELECT a.gnum_role_id||'#'||GNUM_MODULE_ID, (SELECT gstr_role_name"+
            " FROM gblt_role_mst where gnum_role_id=a.gnum_role_id and gnum_hospital_code=a.gnum_hospital_code) "+
            " ||'('|| (select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID=a.GNUM_MODULE_ID and GBL_ISVALID=1)||')' as Role " +            
           // " ||'('|| (select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID=a.GNUM_MODULE_ID and GNUM_HOSPITAL_CODE=100 and GBL_ISVALID=1)||')' as Role " +
            " FROM gblt_group_role_mst a"+
            " WHERE gnum_group_code ='"+this.group+"'"+
            " AND a.gnum_hospital_code = '"+this.hospitalcode+"'"+
            " AND gnum_isvalid = '1'"+
            " AND gdt_effective_frm <= SYSDATE"+
            " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
            " TO_CHAR (SYSDATE, 'DD-MM-YY')"+
            " )"+
            " AND a.gnum_role_id not in"+
            " ("+
            " SELECT gnum_role_id"+
            " FROM gblt_seat_role_mst"+
            " WHERE gnum_seatid = '"+this.seat+"'"+
            " AND gbl_isvalid = '1'"+
            " AND gnum_hospital_code = '"+this.hospitalcode+"'"+
            " AND gdt_effective_frm <= SYSDATE"+
            " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
            " TO_CHAR (SYSDATE, 'DD-MM-YY'))"+
            " ) order by  GNUM_MODULE_ID ";
			HisResultSet rs = null;
			
			
			System.out.println("left listbox-------"+query1);
			try
			{
				rs = super.getRecord(query1,true);
				String str1 = "";
				String str2 = "";
				//System.out.println("number of rows"+rs.size());
				while(rs.next())
				{
					
					str1 = rs.getString(1);
					//lstOne.add(str1);
					str2 = rs.getString(2);
					System.out.println("str1------"+str1+"---str2---"+str2);
					html += "<option value='"+str1+"'>"+str2+"</option>";
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			
		}
		
		else
			html += "<option value='0'></option>";
		
		html += "</select>";
		//System.out.println("htm--------"+html);
		return html;
	}

	public String getsecondColumnCombo(HttpServletRequest req){
		ArrayList old_roleid=new ArrayList();
		HttpSession session=req.getSession();
		String html = "";
		html = "<select name='secondmenu' multiple size='5' >";
		if(!(this.seat==null) && !this.seat.equals("") && !this.seat.equals("0")) 
		{
		/*	String query =" SELECT a.gnum_role_id, " +
						"(SELECT gstr_role_name FROM gblt_role_mst where gnum_role_id=a.gnum_role_id and gnum_hospital_code =a.gnum_hospital_code) AS role "+
						 				   
					  " from gblt_seat_role_mst a "+
					  " where gnum_seatid = '"+this.seat+"'"+
					  " and GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"'"+
					  " and gbl_isvalid='1'"+
			 " AND gdt_effective_frm <= SYSDATE"+
             " AND (TO_DATE (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
             " TO_DATE (SYSDATE, 'DD-MM-YY')"+
             " )";*/
			
			String query ="SELECT a.gnum_role_id||'#'||GNUM_MODULE_ID, (SELECT gstr_role_name"+
            " FROM gblt_role_mst where gnum_role_id=a.gnum_role_id and gnum_hospital_code=a.gnum_hospital_code) "+
           // " ||'('|| (select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID=a.GNUM_MODULE_ID and GNUM_HOSPITAL_CODE=100 and GBL_ISVALID=1)||')' as Role " +            
            " ||'('|| (select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID=a.GNUM_MODULE_ID and GBL_ISVALID=1)||')' as Role " +
            " FROM gblt_group_role_mst a"+
            " WHERE gnum_group_code ='"+this.group+"'"+
            " AND a.gnum_hospital_code = '"+this.hospitalcode+"'"+
            " AND gnum_isvalid = '1'"+
            " AND gdt_effective_frm <= SYSDATE"+
            " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
            " TO_CHAR (SYSDATE, 'DD-MM-YY')"+
            " )"+
            " AND a.gnum_role_id  in"+
            " ("+
            " SELECT gnum_role_id"+
            " FROM gblt_seat_role_mst"+
            " WHERE gnum_seatid = '"+this.seat+"'"+
            " AND gbl_isvalid = '1'"+
            " AND gnum_hospital_code = '"+this.hospitalcode+"'"+
            " AND gdt_effective_frm <= SYSDATE"+
            " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
            " TO_CHAR(SYSDATE, 'DD-MM-YY'))"+
            " ) order by  GNUM_MODULE_ID ";
			
			HisResultSet rs = null;
			
			
			System.out.println("righ list box---->"+query);
			try
			{
				rs = super.getRecord(query,true);
				String str1 = "";
				String str2 = "";
				String tempRoleId[]=null;
				while(rs.next())
				{
					str1 = rs.getString(1);
					tempRoleId=str1.split("#");
					old_roleid.add(tempRoleId[0]);
					
					str2 = rs.getString(2);
					html += "<option value='"+str1+"'>"+str2+"</option>";
				}
				session.setAttribute("old_list", old_roleid);
			}
			catch(Exception e)
			{
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			
		}
		
		else
			html += "<option value='0'></option>";
		
		html += "</select>";
		
		return html;
	}
	
	public boolean insertRecord(HttpServletRequest req)
	{
		String seatid="";
		int it=0;
		seatid=this.seat;
		System.out.println("inside ----------------");
		
		
		
		boolean retVal = true;
		Connection conn = null;
		ResultSet rs3 = null;
		PreparedStatement ps =  null;
		PreparedStatement ps1 =  null;
		PreparedStatement ps2=  null;
		PreparedStatement ps3=  null;
		
		List old_roleid=new ArrayList();
		List new_roleid=new ArrayList();
		HttpSession session=req.getSession();
		old_roleid=(List)session.getAttribute("old_list");
		//System.out.println("selectedValues+++++++++"+selectedValues);
		
		StringTokenizer stOne = new StringTokenizer(this.selectedValues,"^");
		
		
		String insertQuery="insert into GBLT_SEAT_ROLE_MST "+
        "( GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEATID, GBL_ISVALID, GNUM_SEAT_ID,GNUM_HOSPITAL_CODE,GNUM_SEAT_ROLE_SLNO,GDT_ENTRY_DATE,GDT_EFFECTIVE_FRM,GDT_EFFECTIVE_TO)"+
        " values "+
        "(?,(select GNUM_MODULE_ID from GBLT_GROUP_ROLE_MST where GNUM_GROUP_CODE='"+this.group+"' and GNUM_ROLE_ID=? and gnum_isvalid ='1'"+
             " AND gnum_hospital_code = '"+this.hospitalcode+"' AND gdt_effective_frm <= SYSDATE "+
             " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >= TO_CHAR (SYSDATE, 'DD-MM-YY')"+
             " )),'"+this.seat+"','1','"+this.seatId+"','"+this.hospitalcode+"',?,to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'),to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'),NULL::timestamp )";
		
		String updateQuery ="UPDATE GBLT_SEAT_ROLE_MST set GBL_ISVALID='2',GDT_EFFECTIVE_TO =to_date(to_char(sysdate,'dd-mon-yyyy')) where gnum_role_id=? and GNUM_SEAT_ROLE_SLNO=? and GNUM_SEATID='"+this.seat+"'" +
							"AND gnum_hospital_code = '"+this.hospitalcode+"'"+
							" AND GDT_EFFECTIVE_FRM <= SYSDATE " +
							" AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
							" TO_CHAR (SYSDATE, 'DD-MM-YY')"+
							" )";
		
		System.out.println("insert query"+insertQuery);
		try
		{	
			conn = super.getConnection();	
			conn.setAutoCommit(false);	
			ps = conn.prepareStatement(insertQuery);
			ps1 = conn.prepareStatement(updateQuery);
			ps2 = conn.prepareStatement(insertQuery);
					
			
			while(stOne.hasMoreElements())
			{
				String nextElement = stOne.nextToken();
				new_roleid.add(nextElement);
				boolean flag = old_roleid.contains(nextElement);
				if(!(flag))
				{
					System.out.println("------ new  Insertion of a role--------");
					String slno = getSerialNumber(nextElement);
					
					ps.setInt(1,Integer.parseInt(nextElement));
					ps.setInt(2,Integer.parseInt(nextElement) );
					ps.setInt(3,(Integer.parseInt(slno))+1);
					ps.addBatch();
					
				}
			}//while closed
			ps.executeBatch();
			if(old_roleid.size()>=1)
			{
			for(int i=0; i<old_roleid.size();i++)
			{
				
				boolean flag = new_roleid.contains(old_roleid.get(i));
				if(!(flag))
				{
					String isValid= getIsValid((String)old_roleid.get(i));
					if(isValid.equals("1"))
					{	System.out.println("deleting the roles  -------");
						ps1 = conn.prepareStatement(updateQuery);
						String slno = getSerialNumber((String)old_roleid.get(i));
						ps1.setInt(1,Integer.parseInt((String)old_roleid.get(i)));
						ps1.setInt(2,Integer.parseInt(slno));
						ps1.addBatch();
						ps1.executeBatch();
					}
					else
					{
						System.out.println("update slno-------");
						ps2 = conn.prepareStatement(insertQuery);
						String slno = getSerialNumber((String)old_roleid.get(i));
						ps2.setInt(1,Integer.parseInt((String)old_roleid.get(i)));
						ps2.setInt(2,Integer.parseInt((String)old_roleid.get(i)));
						ps2.setInt(3,(Integer.parseInt(slno)+1));
						ps1.addBatch();
						ps2.executeBatch();
					}
				}
			}
			
			}
							
			
			
			conn.commit();
			conn.setAutoCommit(true);
			this.status = "Record Successfully Inserted !";
			//System.out.println("saveeeeeeeeeeeeee");
		}
		catch(Exception e)
		{
			System.out.println("Exception in insert Record "+e);
			retVal = false;
			try
			{
				conn.rollback();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in rollback "+ex);
				
			}
			this.status = "Error while Inserting Record ! "+e;
		}
		finally
		{
			try
			{
				if(rs3!=null)
					rs3.close();
				if(conn!=null)
					conn.close();
				if(ps!=null)
					ps.close();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in Closing connection "+ex);
				
			}			
		}		
		
		return retVal;
		
		
	}
	
	public String getSerialNumber(String roleid)
	{
		String slno="0";
		String Query=" SELECT nvl(max(GNUM_SEAT_ROLE_SLNO),0) FROM GBLT_SEAT_ROLE_MST where GNUM_role_id="+roleid+" and GNUM_SEATID='"+this.seat+"'"+
						" AND gnum_hospital_code = '"+this.hospitalcode+"' "+			
						" AND GDT_EFFECTIVE_FRM <= SYSDATE" ;
		              /* Code commented by Garima on 09-Feb-2016 for Effective to field value insertion in seat role master table when a record is unmapped which was done to fix bug related to Seat Role Audit Report*/
						/*" AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE), 'DD-MM-YY') >="+                                    
						" TO_CHAR (SYSDATE, 'DD-MM-YY')"+*/
						//" )";
					
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
	
	

}
