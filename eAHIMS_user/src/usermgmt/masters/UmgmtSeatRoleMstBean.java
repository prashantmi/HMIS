package usermgmt.masters;

import usermgmt.FuncLib;
import usermgmt.umgmtGlobal;
import HisGlobal.*;

import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletRequest;



public class UmgmtSeatRoleMstBean extends FuncLib
{
	private String hmode 	= 	"";
	private String group    =   "";
	private String module 	= 	"";
	private String moduleId 	= 	"";
	private String role	 	= 	"";
	private String seat 	= 	"";
	private String seatId 	= 	"";
	private String seatIdUser 	= 	"";
	private String hospitalCode = "";
	private String status  = "";	
	private String menuSelected	= "";
	private String seatNameSelected	= "";
	private String menuNameSelected	= "";
	private String list2 = "";
	
	private String rootMenu 	= "";
	private String firstMenuNamesAdd = "";
	private String secondMenuNamesAdd = "";
	
	private String groupName    = "";
	private String moduleName 	= "";
	private String roleName 	= "";
	private String seatName 	= "";
	private String menuName 	= "";
	private String menuId 		= "";
	private String previousMenuList	= "";
	
	private String seatSelected = "";
	
	private String levelTwo		= "";
	private String levelThree		= "";
	private String levelFour		= "";
	private String levelFive		= "";
	
	/**
	 * @return the hmode
	 */
	public String getHmode() {
		return hmode;
	}
	/**
	 * @param hmode the hmode to set
	 */
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the seat
	 */
	public String getSeat() {
		return seat;
	}
	/**
	 * @param seat the seat to set
	 */
	public void setSeat(String seat) {
		this.seat = seat;
	}
	/**
	 * @return the seatId
	 */
	public String getSeatId() {
		return seatId;
	}
	/**
	 * @param seatId the seatId to set
	 */
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	/**
	 * @return the menuSelected
	 */
	public String getMenuSelected() {
		return menuSelected;
	}
	/**
	 * @param menuSelected the menuSelected to set
	 */
	public void setMenuSelected(String menuSelected) {
		this.menuSelected = menuSelected;
	}
	/**
	 * @return the seatNameSelected
	 */
	public String getSeatNameSelected() {
		return seatNameSelected;
	}
	/**
	 * @param seatNameSelected the seatNameSelected to set
	 */
	public void setSeatNameSelected(String seatNameSelected) {
		this.seatNameSelected = seatNameSelected;
	}
	/**
	 * @return the menuNameSelected
	 */
	public String getMenuNameSelected() {
		return menuNameSelected;
	}
	/**
	 * @param menuNameSelected the menuNameSelected to set
	 */
	public void setMenuNameSelected(String menuNameSelected) {
		this.menuNameSelected = menuNameSelected;
	}
	/**
	 * @return the rootMenu
	 */
	public String getRootMenu() {
		return rootMenu;
	}
	/**
	 * @param rootMenu the rootMenu to set
	 */
	public void setRootMenu(String rootMenu) {
		this.rootMenu = rootMenu;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the seatName
	 */
	public String getSeatName() {
		return seatName;
	}
	/**
	 * @param seatName the seatName to set
	 */
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return the previousMenuList
	 */
	public String getPreviousMenuList() {
		return previousMenuList;
	}
	/**
	 * @param previousMenuList the previousMenuList to set
	 */
	public void setPreviousMenuList(String previousMenuList) {
		this.previousMenuList = previousMenuList;
	}
	/**
	 * @return the seatSelected
	 */
	public String getSeatSelected() {
		return seatSelected;
	}
	/**
	 * @param seatSelected the seatSelected to set
	 */
	public void setSeatSelected(String seatSelected) {
		this.seatSelected = seatSelected;
	}
	/**
	 * @return the levelTwo
	 */
	public String getLevelTwo() {
		return levelTwo;
	}
	/**
	 * @param levelTwo the levelTwo to set
	 */
	public void setLevelTwo(String levelTwo) {
		this.levelTwo = levelTwo;
	}
	/**
	 * @return the levelThree
	 */
	public String getLevelThree() {
		return levelThree;
	}
	/**
	 * @param levelThree the levelThree to set
	 */
	public void setLevelThree(String levelThree) {
		this.levelThree = levelThree;
	}
	/**
	 * @return the levelFour
	 */
	public String getLevelFour() {
		return levelFour;
	}
	/**
	 * @param levelFour the levelFour to set
	 */
	public void setLevelFour(String levelFour) {
		this.levelFour = levelFour;
	}
	/**
	 * @return the hospitalCode
	 */
	public String getHospitalCode() {
		return hospitalCode;
	}
	/**
	 * @param hospitalCode the hospitalCode to set
	 */
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	/**
	 * @param List2 the List2 to set
	 */
	public String getList2() {
		return list2;
	}
	public void setList2(String list2) {
		this.list2 = list2;
	}
	/**
	 * @param LevelFive the LevelFive to set
	 */
	public String getLevelFive() {
		return levelFive;
	}
	public void setLevelFive(String levelFive) {
		this.levelFive = levelFive;
	}     
	public String getSeatIdUser() {
		return seatIdUser;
	}
	public void setSeatIdUser(String seatIdUser) {
		this.seatIdUser = seatIdUser;
	}
	
	
	public String getGroupCombo(HttpServletRequest request)
	{
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		
		String query = "select GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST " +
				       "where GNUM_ISVALID = '1' and GNUM_HOSPITAL_CODE = '"+HospitalCode+"' " +
					   "and GDT_EFFECT_DATE <= sysdate order by initcap(GSTR_GROUP_NAME)";
		System.out.println("Group_query"+query);
		try
		{
			html = super.getCombo("",query,this.group,"",0);
		}
		catch(Exception ex)
		{
			System.out.println("Exception in getGroupCombo() "+ex);
		}
		return html;
	}
	
	public String getSeatCombo(HttpServletRequest request)
	{
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		
		if(!this.group.equals("0") || !this.group.equals(""))
		{
			String query = 	" select"+ 
							" GNUM_SEATID, initcap(GSTR_SEAT_NAME)"+
							" from GBLT_SEAT_MST"+
							" where GNUM_GROUP_CODE = '"+this.group+"'"+
							" and GNUM_HOSPITAL_CODE = '"+HospitalCode+"' and GNUM_ISVALID = '1'"+
							" and GDT_EFFECT_DATE <= sysdate";
							
			String conditionQuery="( select  distinct a.GNUM_SEATID from gblt_role_seat_menu_dtl a " +
					              "where a.GNUM_ROLE_ID='"+this.role+"' )";
			String finalQuery="";
			finalQuery=query+" and GNUM_SEATID not in "+conditionQuery+" order by initcap(GSTR_SEAT_NAME)";
			
			System.out.println("Seat_query ------>"+finalQuery);
			System.out.println("role "+this.role);
			try
			{
				html = super.getCombo("",finalQuery,this.seat,"",0);
				System.out.println("qqqqqqqqqqqqqqqqqq"+html);
			}
			catch(Exception ex)
			{
				System.out.println("Exception in getSeatCombo() "+ex);
			}
		}
		else
			html = "<option value='0'>Select Value</option>";
		return html;
	} 
	
	public String getRoleCombo(HttpServletRequest request)
	{		
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		if((!this.group.equals(""))||(!this.group.equals("0"))|| 
				(!this.seat.equals(""))|| (!this.seat.equals("0")))
		{
			String query =  " select a.GNUM_ROLE_ID,"+
							" (select GSTR_ROLE_NAME from GBLT_ROLE_MST where GNUM_ROLE_ID = a.GNUM_ROLE_ID)"+
							" from GBLT_GROUP_ROLE_MST a "+
							" where a.GNUM_GROUP_CODE ='"+this.group+"'"+
							" and a.GNUM_HOSPITAL_CODE = '"+HospitalCode+"' and a.GNUM_ISVALID = '1'";
							
			System.out.println("Role_query "+query);
			
			try
			{
				html = super.getCombo("",query,this.role,"",0);
			}
			catch(Exception ex)
			{
				System.out.println("Exception in getRoleCombo() "+ex);
			}
		}
		else
			html = "<option value='0'>Select Value</option>";
		return html;
	} 
	
	public String getModule(HttpServletRequest request)
	{		
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		if((!this.group.equals(""))||(!this.group.equals("0"))|| 
				(!this.seat.equals(""))|| (!this.seat.equals("0"))||
					(!this.role.equals(""))|| (!this.role.equals("0")))
		{
			String query=" SELECT "+
			             "(SELECT INITCAP (gstr_module_name)"+
                         " FROM gblt_metatable_type_mst "+
                         " WHERE gnum_module_id = a.gnum_module_id"+
                        // " AND gnum_hospital_code = a.gnum_hospital_code"+
                         " AND gbl_isvalid = 1) AS modulename"+
                         " FROM gblt_role_mst a"+
                         " WHERE a.gnum_role_id = '"+this.role+"' "+
                         " AND a.gnum_hospital_code ='"+this.hospitalCode+"' "+
                         " AND a.gbl_isvalid = 1 ";
			
			System.out.println("Module Query----->"+query);
						
			try
			{
				html = super.getValue(query);
				this.module=html;
			}
			catch(Exception ex)
			{
				System.out.println("Exception in getModule()"+ex);
			}
		}
		else
			this.module=html;
		return html;
	} 
	public String getModuleId()
	{		
		String html = "";
		if((!this.group.equals(""))||(!this.group.equals("0"))|| 
				(!this.seat.equals(""))|| (!this.seat.equals("0"))||
					(!this.role.equals(""))|| (!this.role.equals("0")))
		{
			String query=" SELECT "+
			             "(SELECT INITCAP (gnum_module_id)"+
                         " FROM gblt_metatable_type_mst "+
                         " WHERE gnum_module_id = a.gnum_module_id"+
                        // " AND gnum_hospital_code = a.gnum_hospital_code"+
                         " AND gbl_isvalid = 1) AS moduleid"+
                         " FROM gblt_role_mst a"+
                         " WHERE a.gnum_role_id = '"+this.role+"' "+
                         " AND a.gnum_hospital_code ='"+this.hospitalCode+"' "+
                         " AND a.gbl_isvalid = 1 ";
			
			System.out.println("Module Query Id----->"+query);
						
			try
			{
				html = super.getValue(query);
				this.module=html;
			}
			catch(Exception ex)
			{
				System.out.println("Exception in getModule()"+ex);
			}
		}
		else
			this.module=html;
		return html;
	} 
	public String getRootMenuCombo(HttpServletRequest request)
	{		
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString(); 


			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+
							//" where GNUM_MENU_LEVEL='0'  and GNUM_HOSPITAL_CODE = '"+HospitalCode+"' and GNUM_ISVALID = '1'"+
			" where GNUM_MENU_LEVEL='0' and GNUM_ISVALID = '1'"+			
			" order by initcap(GSTR_MENU_NAME)";
System.out.println("Root Menu----->"+query);
			try
			{
				html = super.getCombo("",query,this.rootMenu,"",0);
			}
			catch(Exception ex)
			{
				System.out.println("Exception in getMenuCombo() "+ex);
			}
         
        
		return html;
	} 
	
	public String getLevelTwoOptions(HttpServletRequest request)
	{
		
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString(); 
	
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					
					
					String query = "";				
						
					query=		" SELECT GNUM_MENU_ID,"+
								" GSTR_MENU_NAME from GBLT_MENU_MST"+
								" where  GNUM_PARENT_ID = '"+this.rootMenu+"'"+
								" AND GNUM_MODULE_ID = '"+this.getModuleId(request)+"'"+
								" AND GSTR_URL is NULL"+
								//" AND GNUM_HOSPITAL_CODE = '"+HospitalCode+"'"+
								" AND GNUM_ISVALID = '1'";	
						System.out.println("LevelOneQuery---->"+query);			
						
				try{	
					html += super.getCombo("",query,this.levelTwo,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception in getLevelTwoOptions "+ex);
				}
		}
		else
		{
			
			html += "<option value='0'>Select Value</option>";
		}
	
		 
         
		
         return html;
		
	}
	
	
	public String getLevelThreeOptions(HttpServletRequest request)
	{
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString(); 
		//html = "<select name='levelTwo' onChange='persist_submit(\"TEMP_PERSIST\");'>";
		if(!this.levelTwo.equals("0") && !this.levelTwo.equals("")&&
				!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					
					
					String query = "";				
				
					query=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                           " FROM"+
                           " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                           " WHERE "+
                           " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
                          // " B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                           " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                           " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                           " gnum_parent_id ='"+this.levelTwo+"' "+
                           " and GSTR_URL is null ";




					
					System.out.println("Leve2_query---->"+query);
					
				try{
					
					html += super.getCombo("",query,this.levelThree,"",0);				
					
				}
				catch(Exception ex)
				{
					System.out.println("Exception in getLevelThreeOptions"+ex);
				}
		}
		else
		{
			
			html += "<option value='0'>Select Value</option>";
		}
		
		
         
		
		return html;
		
	}
	
	public String getLevelFourOptions(HttpServletRequest request)
	{
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString(); 
		//html = "<select name='levelTwo' onChange='persist_submit(\"TEMP_PERSIST\");'>";
		if(!this.levelThree.equals("0") && !this.levelThree.equals("")&&
				!this.levelTwo.equals("0") && !this.levelTwo.equals("")&&
				    !this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					

					
					String query = "";				
					
					query=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                    " FROM"+
                    " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                    " WHERE "+
                    " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
                   // " B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                    " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                    " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                    " gnum_parent_id ='"+this.levelThree+"' "+
                    " and GSTR_URL is null ";
					
					System.out.println("Leve3_query---->"+query);	
				try{	
					
					html += super.getCombo("",query,this.levelFour,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception in getLevelFourOptions"+ex);
				}
		}
		else
		{
			
			html += "<option value='0'>Select Value</option>";
		}
		
      
        
		return html;
		
	}
	/**
	 * @return the LEVEL 4 options Menu
	 */
	public String getLevelFiveOptions(HttpServletRequest request)
	{
		String html = "";
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString(); 
		//html = "<select name='levelTwo' onChange='persist_submit(\"TEMP_PERSIST\");'>";
		if(	!this.levelThree.equals("0") && !this.levelThree.equals("")&&
		    	!this.levelTwo.equals("0") && !this.levelTwo.equals("")&&
				    !this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					
					
					String query = "";				
					
					query=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                    " FROM"+
                    " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                    " WHERE "+
                    " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
                  //  " B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                    " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                    " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                    " gnum_parent_id ='"+this.levelFour+"' "+
                    " and GSTR_URL is null ";
					
					System.out.println("Leve4_query---->"+query);	
				try{	
					
					html += super.getCombo("",query,this.levelFive,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception in getLevelFourOptions"+ex);
				}
		}
		else
		{
			
			html += "<option value='0'>Select Value</option>";
		}
		//html += "</select>";
		return html;
		
	}
	/**
	 * @return the firstMenuNamesAdd
	 */
	public String getFirstMenuNamesAdd(HttpServletRequest request) {
		
		
			String html = "";
			 html="<select name=\"list1\"    multiple size='6'>";
			
			if( 	!this.rootMenu.equals("0") && !this.rootMenu.equals("")&&
					    !this.levelTwo.equals("0") && !this.levelTwo.equals("")&&
						   !this.levelThree.equals("0") && !this.levelThree.equals("")&&
							  !this.levelFour.equals("0")&& !this.levelFour.equals(""))
							    
			{			
				String query1 = "";		
				String query2 = "";	
				String query3 = "";	
				String finalQuery = "";	
				
				query1=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                " FROM"+
                " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                " WHERE "+
                " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
               // " B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                " gnum_parent_id ='"+this.levelTwo+"' "+
                " and GSTR_URL is not null ";
				
				
				System.out.println("Leafquery1---->"+query1);
				
				
				query2=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                " FROM"+
                " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                " WHERE "+
                " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
                //" B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                " gnum_parent_id ='"+this.levelThree+"' "+
                " and GSTR_URL is not null ";

				System.out.println("Leafquery2---->"+query2);
				
				
				query3=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                " FROM"+
                " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                " WHERE "+
                " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
                //" B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                " gnum_parent_id ='"+this.levelFour+"' "+
                " and GSTR_URL is not null ";

				System.out.println("Leafquery3---->"+query3);
				
				finalQuery=query1+"union"+query2+"union"+query3+"order by  gstr_menu_name ";
				System.out.println("FinalLeaf_query---->"+finalQuery);
				
				try{
					
					HisResultSet rs = null;
					rs = super.getRecord(finalQuery,true);
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
					System.out.println("Exception there in firstMenuNamesAdd:  "+ex);
				}
		}
			
			else
			if(	 !this.rootMenu.equals("0") && !this.rootMenu.equals("")&&
				    !this.levelTwo.equals("0") && !this.levelTwo.equals("")&&
					   !this.levelThree.equals("0") && !this.levelThree.equals(""))
			
			{			
				String query1 = "";		
				String query2 = "";	
				String finalQuery = "";	
				
				query1=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                " FROM"+
                " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                " WHERE "+
                " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
             //   " B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                " gnum_parent_id ='"+this.levelTwo+"' "+
                " and GSTR_URL is not null ";
				
				
				System.out.println("Leafquery1---->"+query1);
				
				
				query2=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                " FROM"+
                " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                " WHERE "+
                " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
                //" B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                " gnum_parent_id ='"+this.levelThree+"' "+
                " and GSTR_URL is not null ";

				System.out.println("Leafquery2---->"+query2);
				
				
				
				finalQuery=query1+"union"+query2+"order by  gstr_menu_name ";
				System.out.println("FinalLeaf_query---->"+finalQuery);

				
		
				try{
					
					HisResultSet rs = null;
					rs = super.getRecord(finalQuery,true);
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
					System.out.println("Exception there in firstMenuNamesAdd"+ex);
				}
		}
		
						
			else 
			if (!this.levelTwo.equals("0") && !this.levelTwo.equals("")&&
					!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
			{			
						
				String finalQuery = "";	
				
				finalQuery=" SELECT A.gnum_menu_id,GSTR_MENU_NAME "+
                " FROM"+
                " GBLT_MENU_MST A,GBLT_ROLE_MENU_MST B "+
                " WHERE "+
                " A.GNUM_MENU_ID=B.GNUM_MENU_ID AND "+ 
               // " B.GNUM_HOSPITAL_CODE=A.GNUM_HOSPITAL_CODE AND"+
                " B.GNUM_ROLE_ID='"+this.role+"' AND"+
                " B.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND"+
                " gnum_parent_id ='"+this.levelTwo+"' "+
                " and GSTR_URL is not null "+
                "order by  gstr_menu_name ";
				
				
				
				System.out.println("FinalLeaf_query---->"+finalQuery);
		
		
				try{
					
					HisResultSet rs = null;
					rs = super.getRecord(finalQuery,true);
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
					System.out.println("Exception in firstMenuNamesAdd: "+ex);
				}
		}
		else
		{
			
			html +=  "<option value='0'></option>";
		}
			
			
			
			html += "</select>";
		return html;
	}
	/**
	 * @param firstMenuNamesAdd the firstMenuNamesAdd to set
	 */
	public void setFirstMenuNamesAdd(String firstMenuNamesAdd) {
		this.firstMenuNamesAdd = firstMenuNamesAdd;
	}
	/**
	 * @return the secondMenuNamesAdd
	 */
	public String getSecondMenuNamesAdd(HttpServletRequest request) {
		
		
		String html = "";
		
		
		html="<select name=\"list2\" multiple=\"multiple\" size=\"5\"> " ;
		html += "</select>";
       
		
		return html;
		
			}
	/**
	 * @param secondMenuNamesAdd the secondMenuNamesAdd to set
	 */
	public void setSecondMenuNamesAdd(String secondMenuNamesAdd) {
		this.secondMenuNamesAdd = secondMenuNamesAdd;
	}
	
	
	public void initializeNewMode()
	{
		
		this.menuSelected 	= "";
	}
	public boolean insertRecord()
	{
		boolean retvalue = true;
		Connection conn = null;
		ResultSet rs3 = null;
		Statement stmt =  null;
		String slNo		= "";
		String slNo2		= "";
		String insertQuery="";
		String insertQuery2="";
		int iSlno=0;
		int iSlno2=0;
		
		String query_fetchData=	"select to_char(sysdate,'dd-mon-yyyy') from dual";

		//System.out.println("query_fetchData----->"+query_fetchData);
		HisResultSet rs = null;
		String strDate = "";

		try
		{		
		rs = super.getRecord(query_fetchData,true);



		while(rs.next())
		{
			strDate = rs.getString(1);
				
		}
				

		}
		catch(Exception ex)
		{
		System.out.println("Exception there in getting data from database "+ex);
		}
		

try {
	
	slNo=getNextNo("GNUM_ROLE_SEAT_MENU_SLNO","GBLT_ROLE_SEAT_MENU_DTL","1",this.hospitalCode);
	System.out.println("slNo is "+slNo);
	} catch (Exception e1) {
	
	e1.printStackTrace();
	}
	
	if(slNo!=null )
	 iSlno=Integer.parseInt(slNo);
	System.out.println("iSlno is "+iSlno);
	
	
	try {
		
		slNo2=getNextNo("GNUM_SEAT_ROLE_SLNO","GBLT_SEAT_ROLE_MST","1",this.hospitalCode);
		System.out.println("slNo2 is "+slNo2);
		} catch (Exception e1) {
		
		e1.printStackTrace();
		}
		
		if(slNo2!=null )
		 iSlno2=Integer.parseInt(slNo2);
		System.out.println("iSlno2 is "+iSlno2);

String strMenuId = "";

System.out.println("menuSelectedis ----"+menuSelected);

StringTokenizer st = new StringTokenizer(this.menuSelected,"^");
	
		
	insertQuery2 =" insert into gblt_seat_role_mst" +
			"(GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEATID, GBL_ISVALID, GNUM_SEAT_ID, "+ 
    		" GDT_ENTRY_DATE, GNUM_HOSPITAL_CODE, GNUM_SEAT_ROLE_SLNO, GDT_EFFECTIVE_FRM)"+
			"values"+
			"('"+this.role+"','"+this.moduleId+"','"+this.seat+"','1','"+this.seatId+"'," +
			" to_date('"+strDate+"','dd-mon-yyyy'),'"+this.hospitalCode+"','"+iSlno2+"',to_date('"+strDate+"','dd-mon-yyyy'))";
		
	System.out.println("insertQuery2----"+insertQuery2);
		try
		{   int displayQrder=1; 
			conn = super.getConnection();	
			conn.setAutoCommit(false);			
			stmt = conn.createStatement();
			while(st.hasMoreElements())
			{
				
				strMenuId = (String)st.nextElement();		
			System.out.println("strMenuId---"+strMenuId);
			System.out.println("displayQrder---"+displayQrder);
			System.out.println("iSlno---"+iSlno);
			
			
			insertQuery =	" insert into GBLT_ROLE_SEAT_MENU_DTL (GNUM_ROLE_ID,GNUM_MODULE_ID,GNUM_SEATID," +
							"GNUM_MENU_ID,"+
							"GNUM_PERMISSION, GNUM_DISPLAY_ORDER,"+
							" GNUM_ISVALID,GNUM_ROLE_SEAT_MENU_SLNO,GNUM_HOSPITAL_CODE)" +
							" VALUES ('"+this.role+"','"+this.moduleId+"','"+this.seat+"','"+strMenuId+"'," +
							"'11111', '"+displayQrder+"','1','"+iSlno+"','"+this.hospitalCode+"' )" ;
			
			System.out.println("save_query----"+insertQuery);
			
						
				iSlno++;
				displayQrder++;
				stmt.addBatch(insertQuery);
				
			}//while closed	
			stmt.addBatch(insertQuery2);			
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			this.status = "Record Successfully Inserted ";
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
				if(stmt!=null)
					stmt.close();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in Closing connection "+ex);
				
			}			
		}		
		return retvalue;	
	       
	}
	
	
	public boolean updateRecord( HttpServletRequest request )throws Exception
	
	{
		String query			=	"";
		String oldRecord 		=	"";
		String oldVal			=	"";
		String newVal 			=	"";
		String pK 				=	"";	
		String slNo  			= 	"";
		String moduleId			=   "";
		
		Connection conn			=	null; 
		PreparedStatement ps1	=	null;
		Statement st			=	null;
		boolean flag			=	false;
		boolean isRun			=	false;
		
		List masterList = new ArrayList();
		List lst = new ArrayList();
		
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString(); 
		 
	try{	
		pK=this.role;
		
        oldRecord		=	"select 'GNUM_ISVALID'||'^'||GNUM_ISVALID||'#'||'GDT_EFFECT_TO_DATE'||'^'||GDT_EFFECT_TO_DATE from GBLT_ROLE_SEAT_MENU_DTL where GNUM_ROLE_ID='"+this.role+"' and GNUM_SEATID = '"+this.seatId+"' and GNUM_ISVALID = '1' and GNUM_HOSPITAL_CODE = '"+this.hospitalCode+"'";           
        oldVal			= 	super.getField(oldRecord); 
        newVal	 		= 	"'GNUM_ISVALID^"+"1"+"#GDT_EFFECT_TO_DATE^sysdate";
        
       
      	query	=	"	UPDATE GBLT_ROLE_SEAT_MENU_DTL SET GNUM_ISVALID='"+0+"',GDT_EFFECT_TO_DATE = sysdate"+
	 					" where GNUM_ROLE_ID='"+this.role+"' and GNUM_SEATID = '"+this.seat+"' and GNUM_ISVALID = '1' " +
	 					" and GNUM_HOSPITAL_CODE = '"+HospitalCode+"'";
        	isRun	=		true;
       
        
        if(isRun)
        {
        	conn = new HisMethods().getConnection();
    		conn.setAutoCommit(false);
    		st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
   		   	try
   		   	{
   		   		st.addBatch(query);
   		   		st.executeBatch();
   		   		ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_ROLE_SEAT_MENU_DTL").updateAuditLog(request,conn);
   		   		conn.commit(); 
   		   		conn.setAutoCommit(true);
   		   		flag=true;
   		   	}
   		   	catch(Exception e)
   		   	{
   		   		this.status = "Error while Updating Record(s) "+e;
   		   		System.out.println("Exception in update query:  "+e);
   		   		
   		   	}
   		   	finally
   		   	{
   		   		try
   		   		{
   		   			if(conn!=null)
   		   				conn.close();
   		   			if(st!=null)
   		   				st.close();
   		   			if(ps1!=null)
   		   				ps1.close();
   		   			
   		   		}
   		   		catch(Exception e)
   		   		{
   		   		System.out.println("Exception in finally block:  "+e);
   		   		}
   		   	
   		   	}
        }
        
       	slNo = getNextNo("GNUM_ROLE_SEAT_MENU_SLNO","GBLT_ROLE_SEAT_MENU_DTL","01",this.hospitalCode);
        moduleId = this.getModuleId(request);
   		 
   		query =	" insert into GBLT_ROLE_SEAT_MENU_DTL (GNUM_ROLE_ID,GNUM_MODULE_ID,GNUM_SEATID,GNUM_MENU_ID,"+
   		 		" GNUM_ISVALID,GNUM_ROLE_SEAT_MENU_SLNO,GNUM_HOSPITAL_CODE,GDT_EFFECT_FROM_DATE)" +
   		 		" VALUES ('"+this.role+"','"+moduleId+"','"+this.seat+"',?,'1','"+slNo+"','"+HospitalCode+"'," +
   		 				"sysdate)";
   		   		 
   		StringTokenizer st2 = new StringTokenizer(this.list2,"^");	
   		Connection connt = null;
		PreparedStatement ps =  null;
	  try
		{
			connt = super.getConnection();	
			connt.setAutoCommit(false);
			
			ps = connt.prepareStatement(query);
			
			while(st2.hasMoreElements())
			{
				String nextSeat = st2.nextElement().toString();				
				ps.setString(1,nextSeat);
				ps.executeQuery();		
   			}
			conn.commit();
			conn.setAutoCommit(true);
			this.status = "Record Saved Successfully";
			flag= true;
		}
		catch(Exception e)
		{
			flag = false;
			this.status = "Record cannot be saved";
			System.out.println("Exception in insert query "+e);
		}
   	 finally
   	       {
	        try
		      {
	        	if(conn!=null)
					conn.close();
	        	if(connt!=null)
	        		connt.close();
	        	if(ps!=null)
	        		ps.close();
		      }
	        	catch(Exception ex)
	        	{
	        		System.out.println("Exception in Closing connection "+ex);
			}			
   	    }
	}
	catch (Exception e) {
		System.out.println("Exception in updateRecord "+e);
	}
	    return flag;
      
	
	}
	/**
	 * @return the moduleId
	 */
	public String getModuleId(HttpServletRequest request) {
		
		String HospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString(); 

		
		String html = "";
		if((!this.group.equals(""))||(!this.group.equals("0"))|| 
				(!this.seat.equals(""))|| (!this.seat.equals("0"))||
					(!this.role.equals(""))|| (!this.role.equals("0")))
		{
			String query =  " select GNUM_MODULE_ID"+
							" from GBLT_ROLE_MST where GNUM_HOSPITAL_CODE = '"+HospitalCode+"'"+
							" and GNUM_ROLE_ID='"+this.role+"'";						
			try
			{
				html = super.getValue(query);
				this.moduleId=html;
			}
			catch(Exception ex)
			{
				System.out.println("Exception there in getModuleId():  "+ex);
			}
		}
		else
			this.moduleId=html;
		return html;
	}
	
	public boolean reset(){
		
		this.group = "";
		this.seat = "";
		this.role = "";
		this.module = "";
		this.rootMenu = "";
		this.levelTwo =  "";
		this.levelThree = "";
		this.levelFour = "";
			
		return true;
	}
	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}	