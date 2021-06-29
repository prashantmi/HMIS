	
	package usermgmt.masters;
	import usermgmt.*;
	import java.sql.*;
	import java.util.StringTokenizer;
	
	import HisGlobal.*;
	
	
	public class UmgmtSeatMenuMstBean extends FuncLib
	{
		String html			=	"";
		String module		=	""; 
		String role			=	"";
		String newSeat		=	"";
		String assignSeat	=	"";
		String query		=	"";
		String menuList	=	"";
		
		public void setMenuList(String menuList)
		{
			this.menuList = menuList;
		}
		public String getAssignSeat() 
		{
			return assignSeat;
		}
		public void setAssignSeat(String assignSeat) 
		{
			this.assignSeat = assignSeat;
		}
		public String getModule() 
		{
			return module;
		}
		public void setModule(String module) 
		{
			this.module = module;
		}
		public String getNewSeat() 
		{
			return newSeat;
		}
		public void setNewSeat(String newSeat) 
		{
			this.newSeat = newSeat;
		}
		public String getRole() 
		{
			return role;
		}
		public void setRole(String role) 
		{
			this.role = role;
		}
		
		public String getModuleName()
		{
			 query=	"Select GNUM_MODULE_ID,GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST where GBL_ISVALID='1'";
			 try
			 {
				 html= populateCombo(query,this.module);
			 }
			 catch(Exception e)
			 {
				 System.out.println("Exception is"+e);
			 }
			 return html;
		}
		
		
		public String getRoleName()
		{
			
			if(this.module.equals(""))
			this.module="0";
			query=	"select GNUM_ROLE_ID , GSTR_ROLE_NAME FROM GBLT_ROLE_MST WHERE GNUM_MODULE_ID="+this.module;
			
			try
			 {
				 html= populateCombo(query,this.role);
			 }
			 catch(Exception e)
			 {
				 System.out.println("Exception is"+e);
			 }
			 return html;
		}
		
		public String getNewSeatName()
		{
			query=	"	select GNUM_SEATID,GSTR_SEAT_NAME from GBLT_SEAT_MST where GNUM_SEATID " +
					"	not in (select GNUM_SEATID from GBLT_ROLE_SEAT_MENU_DTL where GNUM_ISVALID='1')";
			try
			 {
				 html= populateCombo(query,this.newSeat);
			 }
			 catch(Exception e)
			 {
				 System.out.println("Exception is"+e);
			 }
			 return html;
		}
		
		public String getAssignSeatName()
		{
			query=	"	select GNUM_SEATID,GSTR_SEAT_NAME from GBLT_SEAT_MST where GNUM_SEATID " +
					"	 in (select GNUM_SEATID from GBLT_ROLE_SEAT_MENU_DTL where GNUM_ISVALID='1')";
			try
			{
				html= populateCombo(query,this.assignSeat);
			}
			catch(Exception e)
			{
				System.out.println("Exception is"+e);
			}
			return html;
		}
		
		public String getMenuListWise()
		{
			html="";
			query	=	"	select distinct GNUM_MENU_ID,(select GSTR_MENU_NAME from GBLT_MENU_MST a where a.GNUM_MENU_ID=b.GNUM_MENU_ID)"+
		  				"	from GBLT_ROLE_SEAT_MENU_DTL b where GNUM_MENU_ID in (select GNUM_MENU_ID from " +
		  				"	GBLT_ROLE_SEAT_MENU_DTL"+
	 			 		"	where GNUM_SEATID='"+this.assignSeat+"')";
			
			try
			{
				HisResultSet rs=getRecord(query);
				if (rs.next())
				{
					rs.previous();
					while(rs.next())
					{
						html +="<option value='"+rs.getString(1)+"^'>"+rs.getString(2)+"</option>";
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception is"+e);		
			}
			return html;
		}
		
		public boolean insertRecord()
		{
			StringTokenizer 	st = null;
			int menuListVal;
			boolean retvalue=true;
			st=new StringTokenizer(menuList,"^"); //String Tokenizer gives all the records separeated from this carret sign
			int dipOrder = 1;
			
			while(st.hasMoreElements())
			{
				menuListVal=Integer.parseInt(st.nextToken());
				
				String query = 	" INSERT INTO GBLT_ROLE_SEAT_MENU_DTL "+
								" (GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEATID, GNUM_MENU_ID, GNUM_PERMISSION, GNUM_DISPLAY_ORDER, GNUM_ISVALID)"+
								" VALUES "+
								" ('"+this.role+"','"+this.module+"','"+this.newSeat+"','"+menuListVal+"','11111',"+dipOrder+",'1')";
				dipOrder++;
				try
				{
					super.updateRecord(query);
				}
				catch(Exception e)
				{
					retvalue=false;
					System.out.println("Exception is"+e);
				}
			}
			return retvalue;
		}
	
		public  void initializeNewMode()
		{
			this.module			=	"0"; 
			this.role			=	"0";
			this.newSeat		=	"0";
			this.assignSeat		=	"0";
			this.menuList		=	"";
		}
	
	
	}
