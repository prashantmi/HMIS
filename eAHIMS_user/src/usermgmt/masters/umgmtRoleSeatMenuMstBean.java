package usermgmt.masters;
import usermgmt.FuncLib;
import HisGlobal.*;
import HisGlobal.HisMethods;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletRequest;


public class umgmtRoleSeatMenuMstBean extends FuncLib
{
	private String	combo1	=	""; 
	private String hmode 	= 	"";
	private String module 	= 	"";
	private String role	 	= 	"";
	private String seat 	= 	"";
	private String seatId 	= 	"";
	private String status 	= 	"";
	private String isvalid 	= 	"";
	private String[] chk 	= 	null; 
	
	private String menuSelected	= "";
	private String seatNameSelected	= "";
	private String menuNameSelected	= "";
	
	private String rootMenu 	= "";
	
	private String moduleName 	= "";
	private String roleName 	= "";
	private String seatName 	= "";
	private String menuName 	= "";
	private String menuId 		= "";
	private String previousMenuList	= "";
	private String previousIsValid = "";
	private String seatSelected = "";
	private String levelTwo		= "";
	
	
	
	public String getCombo1() {
		return combo1;
	}
	public void setCombo1(String combo1) {
		this.combo1 = combo1;
	}
	public String getPreviousIsValid() {
		return previousIsValid;
	}
	public void setPreviousIsValid(String previousIsValid) {
		this.previousIsValid = previousIsValid;
	}
	public java.lang.String getMenuId( )
	{
		return menuId;
	}
	public void setMenuId( java.lang.String menuId )
	{
		this.menuId = menuId;
	}	
	public java.lang.String[] getChk( )
	{
		return chk;
	}
	public void setChk( java.lang.String[] chk )
	{
		this.chk = chk;
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
	public void setMenuSelected( java.lang.String menuSelected )
	{
		this.menuSelected = menuSelected;
	}
	public java.lang.String getMenuSelected( )
	{
		return menuSelected;
	}	
	public void setIsvalid( java.lang.String isvalid )
	{
		this.isvalid = isvalid;
	}
	public java.lang.String getIsvalid( )
	{
		return isvalid;
	}
	public void setStatus( java.lang.String status )
	{
		this.status = status;
	}
	public java.lang.String getStatus( )
	{
		return status;
	}
	public void setSeatId( java.lang.String seatId )
	{
		this.seatId = seatId;
	}
	public java.lang.String getSeatId( )
	{
		return seatId;
	}
	public void setSeat( java.lang.String seat )
	{
		this.seat = seat;
	}
	public java.lang.String getSeat( )
	{
		return seat;
	}
	public void setRole( java.lang.String role )
	{
		this.role = role;
	}
	public java.lang.String getRole( )
	{
		return role;
	}
	public void setModule( java.lang.String module )
	{		
		this.module = module;
	}
	public java.lang.String getModule( )
	{
		return module;
	}
	public void setHmode( java.lang.String hmode )
	{
		this.hmode = hmode;
	}
	public java.lang.String getHmode( )
	{
		return hmode;
	}
	public String getPreviousMenuList() {
		return previousMenuList;
	}
	public void setPreviousMenuList(String previousMenuList) {
		this.previousMenuList = previousMenuList;
	}
	public String getRootMenu() {
		return rootMenu;
	}
	public void setRootMenu(String rootMenu) {
		this.rootMenu = rootMenu;
	}
	public String getSeatSelected() {
		return seatSelected;
	}
	public void setSeatSelected(String seatSelected) {
		this.seatSelected = seatSelected;
	}
	public String getLevelTwo() {
		return levelTwo;
	}
	public void setLevelTwo(String levelTwo) {
		this.levelTwo = levelTwo;
	}
	public String getMenuNameSelected() {
		return menuNameSelected;
	}
	public void setMenuNameSelected(String menuNameSelected) {
		this.menuNameSelected = menuNameSelected;
	}
	public String getSeatNameSelected() {
		return seatNameSelected;
	}
	public void setSeatNameSelected(String seatNameSelected) {
		this.seatNameSelected = seatNameSelected;
	}
	public String getModuleCombo()
	{
		String html = "";
		String query = "select GNUM_MODULE_ID, initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GBL_ISVALID = '1' order by initcap(GSTR_MODULE_NAME)";
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
		if(!this.module.equals("0") || !this.module.equals(""))
		{
			String query = 	" select"+ 
							" GNUM_ROLE_ID, initcap(GSTR_ROLE_NAME)"+
							" from GBLT_ROLE_MST"+
							" where GNUM_MODULE_ID = '"+this.module+"'"+
							" and GBL_ISVALID = '1'"+
							" order by initcap(GSTR_ROLE_NAME)";

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
		 
		if((!this.role.equals(""))||(!this.role.equals("0"))|| (!this.module.equals(""))|| (!this.module.equals("0")))
		{
			String query =  " select a.GNUM_SEATID,"+
							" nvl(("+
							" 		select x.GSTR_SEAT_NAME from GBLT_SEAT_MST x"+
							" 		where x.GNUM_SEATID = a.GNUM_SEATID"+
							" 		and x.GNUM_ISVALID = '1'"+
							" ),'****')seat_name"+
							" from GBLT_SEAT_ROLE_MST a"+
							" where GNUM_ROLE_ID='"+this.role+"'"+
							" and  GNUM_MODULE_ID='"+this.module+"'"+
							" and GBL_ISVALID='1'";
		
			
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
	public String getMenuNames()
	{
		

		
		String html = "";
		
		html = "<select name='firstMenu' multiple size='6'>";
		
		if(!this.rootMenu.equals("")&&!this.rootMenu.equals("0"))
		{
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
							" from GBLT_MENU_MST"+
							" where GNUM_ISVALID ='1'"+
							" and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
							" and GNUM_MENU_LEVEL >1"+
							" order by GSTR_MENU_NAME";
			
			
			HisResultSet rs = null;
			try
			{
				rs = super.getRecord(query,true);
				String str1 = "";
				String str2 = "";
				while(rs.next())
				{
					str1 = rs.getString(1);
					str2 = rs.getString(2);
					html += "<option value='"+str1+"'>"+str2+"</option>";					
					
				}
			
			}
			catch(Exception ex)
			{
				System.out.println("Exception there in getMenuNames() "+ex);
			}
		}
		else
			html += "<option value='0'></option>";
		
		html += "</select>";
		return html;
	} 
	public boolean insertRecord()
	{
		boolean retvalue = true;
		String displayOrderQuery = 	" SELECT NVL(MAX(GNUM_DISPLAY_ORDER),'1') FROM GBLT_ROLE_SEAT_MENU_DTL "+
									" WHERE GNUM_ROLE_ID = '"+this.role+"'"+
									" AND GNUM_MODULE_ID ='"+this.module+"'"+
									" AND GNUM_SEATID = ?";
		
		
		
		String query = 	" INSERT INTO GBLT_ROLE_SEAT_MENU_DTL "+
						" (GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEATID, GNUM_MENU_ID, GNUM_PERMISSION, GNUM_DISPLAY_ORDER, GNUM_ISVALID) "+
						" VALUES "+
						" ('"+this.role+"','"+this.module+"',?,?,'11111',?,'"+this.isvalid+"')";
		
		
		
		StringTokenizer st2 = new StringTokenizer(this.seatSelected,"^");	
		
		Connection conn = null;
		ResultSet rs3 = null;
		PreparedStatement ps =  null;
		PreparedStatement ps2 =  null;
		
		
		try
		{
			conn = super.getConnection();	
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(query);
			ps2 = conn.prepareStatement(displayOrderQuery);
			
			while(st2.hasMoreElements())
			{
				
				String nextSeat = st2.nextElement().toString();				
				ps2.setString(1,nextSeat);
				rs3 = ps2.executeQuery();			
				rs3.next();				
				
				int displayOrder = Integer.parseInt(rs3.getString(1).toString());
				
				String str = "";
				StringTokenizer st = new StringTokenizer(this.menuSelected,"^");
				while(st.hasMoreElements())
				{
					str = (String)st.nextElement();					
					ps.setString(1,nextSeat);
					ps.setString(2,str);					
					ps.setInt(3,displayOrder);
					ps.addBatch();
					displayOrder++;
					
				}
			}
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(Exception e)
		{
			System.out.println("Exception in insert Record "+e);
			retvalue = false;
			try
			{
				conn.rollback();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in rollback "+ex);
				
			}
			this.status = "Error while Inserting : "+e;
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
		return retvalue;
	}
	public void initializeNewMode()
	{
		
		this.menuSelected 	= "";
	}
	public List initializeUpdateMode()
	{
		List lst = null;
		try
		{
			StringTokenizer st = new StringTokenizer(this.chk[0],"^");
			String t_role 	= "";
			String t_module = "";
			String t_seat 	= "";
			t_module 		= st.nextElement().toString();
			t_role 			= st.nextElement().toString();				
			t_seat 			= st.nextElement().toString();
			
				
			this.role = t_role;
			this.module = t_module;
			this.seat = t_seat;
			String query =" select"+
				" ("+
				" 		select GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST x"+
				" 		where e.GNUM_MODULE_ID = x.GNUM_MODULE_ID"+
				" ) module_name,"+
				" ("+
				" 		select GSTR_ROLE_NAME from GBLT_ROLE_MST x"+
				" 		where e.GNUM_ROLE_ID = x.GNUM_ROLE_ID"+
				" 		and  x.GNUM_MODULE_ID = e.gnum_module_id"+
				" )role_name,"+
				" ("+
				" 		select GSTR_SEAT_NAME from GBLT_SEAT_MST x"+ 
				" 		where"+ 
				" 		e.GNUM_SEATID = x.GNUM_SEATID"+
				" )user_name, e.GNUM_ISVALID"+
				" from"+
				" ("+
				"		select"+ 
				"		distinct GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEATID,GNUM_ISVALID"+
				"		from GBLT_ROLE_SEAT_MENU_DTL"+
				" 		WHERE GNUM_MODULE_ID = '"+t_module+"'"+
				" 		AND GNUM_ROLE_ID = '"+t_role+"'"+
				" 		AND GNUM_SEATID = '"+t_seat+"'"+
				" )e";
				
				
				 
				try
				{
				lst = super.getDetails(query,4);
				}
				catch(Exception e)
				{
					System.out.println("Exception there in initialize update mode "+e);
				}
				
				
				
				this.moduleName = lst.get(0).toString();
				this.roleName   = lst.get(1).toString();
				this.seatName   = lst.get(2).toString();
				this.isvalid    = lst.get(3).toString();
				this.previousIsValid = lst.get(3).toString();
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in initizlize update mode "+ex);
		}
		
		return lst;
	}
	public boolean updateRecord(HttpServletRequest req)
	{
		boolean retVal = true;
		
		List lstOne = new ArrayList();
		List lstTwo = new ArrayList();
		
		StringTokenizer stOne = new StringTokenizer(this.previousMenuList,"^");
		
		
		while(stOne.hasMoreElements())
		{
			lstOne.add(stOne.nextToken().toString());		
		
		}
		StringTokenizer stTwo = new StringTokenizer(this.menuSelected,"^");
		while(stTwo.hasMoreElements())
		{
			lstTwo.add(stTwo.nextToken().toString());		
		
		}
		
		
		
		
		List recToInsert = new ArrayList();
		List recToDelete = new ArrayList();
		
		int i = 0;
		
		//FINDING  THE MENU IDS TO BE DELETED
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
		
		
		//FINDING THE MENU IDS TO BE INSERTED
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
				recToInsert.add(lstTwo.get(i));	
		
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
				query1 = 	" DELETE FROM GBLT_ROLE_SEAT_MENU_DTL "+
							" WHERE GNUM_MODULE_ID ='"+this.module+"'AND" +
							" GNUM_ROLE_ID='"+this.role+"'AND " +					
							" GNUM_SEATID ='"+this.seat+"'AND" +
							" GNUM_MENU_ID IN ("+strToDelete+")";
							stmt.addBatch(query1);
				}
					
					if(recToInsert.size()!=0)
					{
						String query2 = "";
						for(i=0;i<recToInsert.size();i++)
						{
							query2 = 	" INSERT INTO GBLT_ROLE_SEAT_MENU_DTL "+
										" (GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEATID, GNUM_MENU_ID, GNUM_PERMISSION, GNUM_DISPLAY_ORDER, GNUM_ISVALID) "+
										" VALUES "+
										" ('"+this.role+"','"+this.module+"','"+this.seat+"','"+recToInsert.get(i).toString()+"','A1M1','2','"+this.isvalid+"')";
							stmt.addBatch(query2);
							
							
						}
					}
					
					
					if(!this.previousIsValid.equals(this.isvalid))
					{
						String query3 = "";
						query3 = 	" UPDATE GBLT_ROLE_SEAT_MENU_DTL SET GNUM_ISVALID = '"+this.isvalid+"'"+
									" WHERE GNUM_ROLE_ID ='"+this.role+"' "+
									" AND GNUM_MODULE_ID='"+this.module+"' "+
									" AND GNUM_SEATID='"+this.seat+"'";
						stmt.addBatch(query3);
						
					
					}
					
					
					stmt.executeBatch();
					conn.commit();
					conn.setAutoCommit(true);
					
		}
		catch(Exception ex)
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
	}	
	public String getFirstMenuNameModify()
	{
		String html = "";
		
		try
		{			
			
			html = "<select name='firstMenu' multiple size='6'>";
			String menuLevelCond = "";
			menuLevelCond = " AND GNUM_MENU_LEVEL = 2";
			
			String levelTwoCond = "";
			if(this.levelTwo!=null)
			{
				if(!this.levelTwo.equals("0"))
				{
					levelTwoCond = " AND GNUM_PARENT_ID = "+this.levelTwo+"";
					menuLevelCond = " AND GNUM_MENU_LEVEL = 3";
				}
			}
			
				
			String t_role 	= "";
			String t_module = "";
			String t_seat 	= "";
			
			if(this.chk!=null)
			{
			
				StringTokenizer st = new StringTokenizer(this.chk[0],"^");					
				
				
				t_module 	= st.nextElement().toString();
				t_role 		= st.nextElement().toString();				
				t_seat 		= st.nextElement().toString();
				
				this.module = t_module;
				this.role 	= t_role;
				this.seat 	= t_seat;
			}
			else
			{
				t_module 	= this.module;
				t_role 		= this.role;				
				t_seat 		= this.seat;
				
			}
			
			if(t_module.length()==1)
				t_module = "0"+t_module;
			
			
			String menuQuery =" select 	e.GNUM_MENU_ID"+
							" from"+
							" GBLT_ROLE_SEAT_MENU_DTL e"+
							" WHERE e.GNUM_MODULE_ID = '"+t_module+"'"+
							" AND e.GNUM_ROLE_ID = '"+t_role+"'"+
							" AND e.GNUM_SEATID = '"+t_seat+"'";
			
			
			
			
			if(!(this.rootMenu==null) && !(this.rootMenu.equals("")))
			{
			 
				String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
								" from GBLT_MENU_MST"+
								" where GNUM_ISVALID ='1'"+
								" AND GNUM_MENU_ID NOT IN ("+menuQuery+")"+
								" and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
								" AND substr(gnum_menu_id,2,2) = '"+t_module+"'"+ menuLevelCond + levelTwoCond +
								" order by GSTR_MENU_NAME";
			
					
					
					
					HisResultSet rs = null;
					rs = super.getRecord(query,true);
					String str1 = "";
					String str2 = "";
					while(rs.next())
					{
						str1 = rs.getString(1);
						str2 = rs.getString(2);
						html += "<option value='"+str1+"'>"+str2+"</option>";					
					}
			}
			
	
			html += "</select>";
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in getFirstMenuNameModify "+ex);
		}
		return html;
	} 
	public String getSecondMenuNameModify()
	{
			
		String html = "";	
		String prevMenu = "";
		html += "<select name='secondMenu' size='6' multiple>";
		
		String menuLevelCond = "";
		menuLevelCond = " AND GNUM_MENU_LEVEL = 2";
		
		String levelTwoCond = "";
		if(!this.levelTwo.equals("0"))
		{
			levelTwoCond = " AND GNUM_PARENT_ID = "+this.levelTwo+"";
			menuLevelCond = " AND GNUM_MENU_LEVEL = 3";
		}
						
		
		String t_role 	= "";
		String t_module = "";
		String t_seat 	= "";
			
		if(this.chk!=null)
		{
		
			StringTokenizer st = new StringTokenizer(this.chk[0],"^");					
							
			t_module 	= st.nextElement().toString();
			t_role 		= st.nextElement().toString();				
			t_seat 		= st.nextElement().toString();
			
			this.module = t_module;
			this.role 	= t_role;
			this.seat 	= t_seat;
		}
		else
		{
			t_module 	= this.module;
			t_role 		= this.role;				
			t_seat 		= this.seat;
			
		}
		if(t_module.length()==1)
			t_module = "0"+t_module;
		
		if(!(this.rootMenu==null) && !(this.rootMenu.equals("")))
		{
			
			String menuQuery =" select 	e.GNUM_MENU_ID"+
							" from"+
							" GBLT_ROLE_SEAT_MENU_DTL e"+
							" WHERE e.GNUM_MODULE_ID = '"+t_module+"'"+
							" AND e.GNUM_ROLE_ID = '"+t_role+"'"+
							" AND e.GNUM_SEATID = '"+t_seat+"'";
					
					
					
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
							" from GBLT_MENU_MST"+
							" where GNUM_ISVALID ='1'"+
							" AND GNUM_MENU_ID  IN ("+menuQuery+")"+
							" and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
							" AND substr(gnum_menu_id,2,2) = '"+t_module+"'"+ menuLevelCond + levelTwoCond +
							" order by GSTR_MENU_NAME";
		
				
				
				HisResultSet rs = null;
				try
				{		
						rs = super.getRecord(query,true);
						String str1 = "";
						String str2 = "";
						
						while(rs.next())
						{
							str1 = rs.getString(1);
							str2 = rs.getString(2);
							html += "<option value='"+str1+"'>"+str2+"</option>";	
							prevMenu += str1+"^";
							
						}
						
						html += "</select>";
						//html += "<input type='hidden' name='previousMenuList' value='"+previousMenuList+"'>";
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in getSecondMenuNameModify "+ex);
				}
				html += "<input type='hidden' name='previousMenuList' value='"+prevMenu+"'>";
		}
		
		return html;
	}
	public String getFirstMenuNamesAdd()
	{
		
		String html = "";
		
		html = "<select name='firstMenu' multiple size='6'>";
		
		
		List selectedMenus = new ArrayList();
		if(!(this.menuNameSelected==null) &&  !(this.menuNameSelected.equals("")))
		{
			StringTokenizer st = new StringTokenizer(this.menuNameSelected,"^");
			while(st.hasMoreElements())
			{
				selectedMenus.add(st.nextElement().toString());
				st.nextElement();				
			}
		}
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{
		
			
				try
				{			
					
					String tModule = "";
					String query = "";
					String menuLevelCond = "";
					String levelTwoCond = "";
					
					if(this.module.length()==1)
						tModule = "0"+this.module;				
					else if(this.module.length()==2)
						tModule=this.module;
					
					
					
					menuLevelCond = " AND GNUM_MENU_LEVEL = 2";
					
					
					if(!this.levelTwo.equals("0"))
					{
						levelTwoCond = " AND GNUM_PARENT_ID = "+this.levelTwo+"";
						menuLevelCond = " AND GNUM_MENU_LEVEL = 3";
					}
					
						query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
						" from GBLT_MENU_MST"+
						" where GNUM_ISVALID ='1'"+
						" and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
					    " AND substr(gnum_menu_id,2,2) = '"+tModule+"'" + levelTwoCond + menuLevelCond +						
						" order by GSTR_MENU_NAME";
					
						System.out.println("query is"+query);			
						HisResultSet rs = null;
						rs = super.getRecord(query,true);
						String str1 = "";
						String str2 = "";
						while(rs.next())
						{
							str1 = rs.getString(1);
							str2 = rs.getString(2);
							if(!selectedMenus.contains(str1))
								html += "<option value='"+str1+"'>"+str2+"</option>";					
						}					
				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		}
		else
		{
			
			html += "<option value='0'></option>";
		}
		html += "</select>";
		return html;
	
	}
	public String getLevelTwoOptions()
	{
		String html = "";
		
		//html = "<select name='levelTwo' onChange='persist_submit(\"TEMP_PERSIST\");'>";
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					
				try
				{					
					String tModule = "";				
					
					if(this.module.length()==1)
						tModule = "0"+this.module;		
					else if(this.module.length()==2)
						tModule = this.module;
					
					String query = "";				
						
					query = " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
							" from GBLT_MENU_MST"+
							" where"+ 
							" GSTR_MENUCLASS_ID = 'I'"+
							" and GNUM_ISVALID ='1'"+
							" and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
							" AND substr(gnum_menu_id,2,2) = '"+tModule+"'"+
							" AND GNUM_MENU_LEVEL = 2"+
							" order by GSTR_MENU_NAME";	
					
					
					
					html += super.getCombo("",query,this.levelTwo,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		}
		else
		{
			
			html += "<option value='0'>Select</option>";
		}
		//html += "</select>";
		return html;
		
	}
	public String getLevelTwoOptionsModify()
	{
		String html = "";
		
		//html = "<select name='levelTwo' onChange='submitMode(\"TEMP_MODIFY\");'>";
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					
				try
				{					
					String tModule = "";				
					
					if(this.module.length()==1)
						tModule = "0"+this.module;				
					
					String query = "";				
						
					query = " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) "+
				       " from GBLT_MENU_MST "+
				       " where"+ 
				       " GSTR_MENUCLASS_ID = 'I' "+
				       " and GNUM_ISVALID ='1' "+
				       " and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
				       " AND substr(gnum_menu_id,2,2) = '"+tModule+"' "+
				       " AND GNUM_MENU_LEVEL = 2 "+
				       " and gnum_menu_id in (select distinct GNUM_PARENT_ID from  gblt_menu_MST where gnum_menu_level = 3  AND gnum_isvalid = '1' "+
				       " AND gnum_menu_id LIKE '"+this.rootMenu.substring(0,1)+"%'"+
				       " AND SUBSTR (gnum_menu_id, 2, 2) = '"+tModule+"')"+
				       " order by GSTR_MENU_NAME"; 
					
					html += super.getCombo("",query,this.levelTwo,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		}
		else
		{
			
			html += "<option value='0'>Select</option>";
		}
		//html += "</select>";
		return html;
		
	}
	public String getSecondMenuNamesAdd()
	{
		
		String html = "";
		String previousMenuList = "";
		html += "<select name='secondMenu' size='6' multiple>";
		
		if(!(this.menuNameSelected==null) &&  !(this.menuNameSelected.equals("")))
		{
			StringTokenizer st = new StringTokenizer(this.menuNameSelected,"^");
			while(st.hasMoreElements())
			{
				html += "<option value='"+st.nextElement().toString()+"'>"+st.nextElement().toString()+"</option>";
			}
		}
		/*if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{
		
				//String previousMenuList = "";
				
				try
				{			
						List lst = getExistingMenuList();			
						
						String menuIdList = "";		
						
																
						if(lst!=null && lst.size()!=0)
						{
							
							for(int i=0;i<lst.size();i++)
							{
								menuIdList += lst.get(i).toString()+",";					
								
							}
							menuIdList = menuIdList.substring(0,menuIdList.length()-1);
							
							String tModule = "";
							
							if(this.module.length()==1)
								tModule = "0"+this.module;					
							
							
							
							String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
											" from GBLT_MENU_MST"+
											" where GNUM_ISVALID ='1'"+
											" AND substr(gnum_menu_id,2,2) = '"+tModule+"'"+
											" and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
											" AND GNUM_MENU_ID IN ("+menuIdList+")"+
											" AND GNUM_MENU_LEVEL > 1"+
											" order by GSTR_MENU_NAME";
							
							
							HisResultSet rs = null;
							
							rs = super.getRecord(query,true);
							String str1 = "";
							String str2 = "";
							
							while(rs.next())
							{
								str1 = rs.getString(1);
								str2 = rs.getString(2);
								html += "<option value='"+str1+"'>"+str2+"</option>";	
								previousMenuList += str1+"^";
								
							}
							
							
						}
						else
						{
							//html += "<option value='0'></option>";
							
						}

						
						
						//html += "<input type='hidden' name='previousMenuList' value='"+previousMenuList+"'>";
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		}	*/	
		/*else
			html += "<option value='0'></option>";*/
		
		
		
		html += "</select>";
		html += "<input type='hidden' name='previousMenuList' value='"+previousMenuList+"'>";
		return html;
	
	}
	public String deleteRecord()
	{
		String status = "";
		Connection conn = null;		
		PreparedStatement ps = null;
		String query = 	" DELETE FROM GBLT_ROLE_SEAT_MENU_DTL "+
		" WHERE GNUM_ROLE_ID = ?"+
		" AND GNUM_MODULE_ID = ?"+
		" AND GNUM_SEATID= ?";
	
		
		
		
		String t_role 	= "";
		String t_module = "";
		String t_seat 	= "";
		
		int i = 0;
		try
		{
			conn = super.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query);
				
				
				
				for(i=0;i<this.chk.length;i++)
				{
					
				
					StringTokenizer st = new StringTokenizer(this.chk[i],"^");
					
					
					
				
				
					t_module 	= st.nextElement().toString();
					t_role 		= st.nextElement().toString();					
					t_seat 		= st.nextElement().toString();
								
					
					ps.setString(1,t_role);
					ps.setString(2,t_module);
					ps.setString(3,t_seat);
				
					
					ps.execute();
				
				
			}
			conn.commit();
			conn.setAutoCommit(true);
			status = i + "  record(s) deleted";
		}
		catch(Exception e)
		{
			status = "Error while deleting record(s) "+e;
			try
			{
				conn.rollback();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in rollback "+ex);
			}
		}
		finally
		{
			try
			{
				ps.close();
				conn.close();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in closing connection "+ex);
			}
			
		
		}
		return status;
}
	public String getRootMenuCombo()
	{		
		String html = "";
		
		 
		if(!this.role.equals("")&& !this.role.equals("0"))
		{
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+
							" where GNUM_MENU_LEVEL='0' order by initcap(GSTR_MENU_NAME)";

			try
			{
				html = super.getCombo("",query,this.rootMenu,"",0);
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
	public String getRootMenuComboModify()
	{		
		String html = "";	 
	
		String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+
						" where GNUM_MENU_LEVEL='0' order by initcap(GSTR_MENU_NAME)";
		System.out.println("root query is"+query);
		try
		{
			html = super.getCombo("",query,this.rootMenu,"",0);
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in getSeatCombo() "+ex);
		}
		
		return html;
	} 
	public String getFirstSeatNames()
	{
		String html = "";
		
		String t_module = "";
		t_module = this.module;
		if(this.module.length()==1)
			t_module = "0"+this.module;
		
		List selectedSeats = new ArrayList();
		if(!(this.seatNameSelected==null) &&  !(this.seatNameSelected.equals("")))
		{
			StringTokenizer st = new StringTokenizer(this.seatNameSelected,"^");
			while(st.hasMoreElements())
			{
				selectedSeats.add(st.nextElement().toString());
				st.nextElement();				
			}
		}
		
		
		String query =  " select a.GNUM_SEATID,"+
						" nvl(("+
						" 		select x.GSTR_SEAT_NAME from GBLT_SEAT_MST x"+
						" 		where x.GNUM_SEATID = a.GNUM_SEATID"+						
						" ),'****')seat_name"+
						" from GBLT_SEAT_ROLE_MST a"+
						" where GNUM_ROLE_ID='"+this.role+"'"+
						" and  GNUM_MODULE_ID='"+t_module+"'"+
						" and not exists"+
						" ("+
						" 	select 'x' from GBLT_ROLE_SEAT_MENU_DTL r"+ 
						" 	where r.GNUM_SEATID =  a.GNUM_SEATID"+
						"	and substr(GNUM_MENU_ID,2,2) = "+t_module+""+
						" )"+
						" and GBL_ISVALID='1'";
						
		html = "<select name='firstSeat' multiple size='6'>";
		if(!this.role.equals("0") && !this.role.equals(""))
		{
			try
			{
				HisResultSet rs = null;
				rs = super.getRecord(query,true);
				while(rs.next())
				{
					if(!selectedSeats.contains(rs.getString(1).toString()))
						html += "<option value = "+rs.getString(1)+">"+rs.getString(2)+"</option>";
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception in getFirstSeatNames "+e);
			}	
		}
		html += "</select>";
		return html;
	}
	public String getSecondSeatNames()
	{
		String html = "";
		
		
		html = "<select name='secondSeat' multiple size='6'>";
		if(!(this.seatNameSelected==null) &&  !(this.seatNameSelected.equals("")))
		{
			StringTokenizer st = new StringTokenizer(this.seatNameSelected,"^");
			while(st.hasMoreElements())
			{
				html += "<option value='"+st.nextElement().toString()+"'>"+st.nextElement().toString()+"</option>";
			}
		}
	
		
		html += "</select>";
		return html;
	}
	
	public String	getSecondMenuNameView()
	{
		String html = "";			
		html += "<select name='secondMenu' size='6' multiple>";
		
		String menuLevelCond = "";
		menuLevelCond = " AND GNUM_MENU_LEVEL = 2";
		
		String levelTwoCond = "";
		if(!this.levelTwo.equals("0"))
		{
			levelTwoCond = " AND GNUM_PARENT_ID = "+this.levelTwo+"";
			menuLevelCond = " AND GNUM_MENU_LEVEL = 3";
		}
						
		
		String t_role 	= "";
		String t_module = "";
		String t_seat 	= "";
			
		if(this.chk!=null)
		{
		
			StringTokenizer st = new StringTokenizer(this.chk[0],"^");					
							
			t_module 	= st.nextElement().toString();
			t_role 		= st.nextElement().toString();				
			t_seat 		= st.nextElement().toString();
			
			this.module = t_module;
			this.role 	= t_role;
			this.seat 	= t_seat;
		}
		else
		{
			t_module 	= this.module;
			t_role 		= this.role;				
			t_seat 		= this.seat;
			
		}
		if(t_module.length()==1)
			t_module = "0"+t_module;
		
		if(!(this.rootMenu==null) && !(this.rootMenu.equals("")))
		{
			
			String menuQuery =" select 	e.GNUM_MENU_ID"+
							" from"+
							" GBLT_ROLE_SEAT_MENU_DTL e"+
							" WHERE e.GNUM_MODULE_ID = '"+t_module+"'"+
							" AND e.GNUM_ROLE_ID = '"+t_role+"'"+
							" AND e.GNUM_SEATID = '"+t_seat+"'";
					
					
					
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
							" from GBLT_MENU_MST"+
							" where GNUM_ISVALID ='1'"+
							" AND GNUM_MENU_ID  IN ("+menuQuery+")"+
							" and GNUM_MENU_ID like '"+this.rootMenu.substring(0,1)+"%'"+
							" AND substr(gnum_menu_id,2,2) = '"+t_module+"'"+ menuLevelCond + levelTwoCond +
							" order by GSTR_MENU_NAME";
		
				
				
				HisResultSet rs = null;
				try
				{		
						rs = super.getRecord(query,true);
						String str1 = "";
						String str2 = "";
						
						while(rs.next())
						{
							str1 = rs.getString(1);
							str2 = rs.getString(2);
							html += "<option value='"+str1+"'>"+str2+"</option>";	
						
							
						}
						
						html += "</select>";
						//html += "<input type='hidden' name='previousMenuList' value='"+previousMenuList+"'>";
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in getSecondMenuNameModify "+ex);
				}
				
		}
		
		return html;
		
	}
	
}