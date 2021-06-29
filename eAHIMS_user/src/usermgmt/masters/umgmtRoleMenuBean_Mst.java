package usermgmt.masters;
import usermgmt.FuncLib;
import HisGlobal.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletRequest;


public class umgmtRoleMenuBean_Mst extends FuncLib
{
	List selectedMenus = new ArrayList();
	List unSelectedMenus = new ArrayList();
	List newSelectedMenus = new ArrayList();
	List newunSelectedMenus = new ArrayList();
	private String	menuSNo	=	""; 
	private String	title	=	"";  
	private String	hospital_code	=	"";  
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
	private String menuNameUnSelected	= "";
	private String newMenuNameSelected	= "";
	private String newMenuNameUnSelected	= "";
	private String rootMenu 	= "";
	
	private String moduleName 	= "";
	private String roleName 	= "";
	private String seatName 	= "";
	private String menuName 	= "";
	private String menuId 		= "";
	private String menuId_selected 		= "";
	private String previousMenuList	= "";
	private String previousIsValid = "";
	private String seatSelected = "";
	private String levelTwo		= "";
	private String levelThree		= "";
	private String levelFour		= "";
	private String slNo		= "";
	
	
	public String getMenuSNo() {
		return menuSNo;
	}
	public void setMenuSNo(String menuSNo) {
		this.menuSNo = menuSNo;
	}
	
	public String getMenuId_selected() {
		return menuId_selected;
	}
	public void setMenuId_selected(String menuId_selected) {
		this.menuId_selected = menuId_selected;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHospital_code() {
		return hospital_code;
	}
	public void setHospital_code(String hospital_code) {
		this.hospital_code = hospital_code;
	}
	
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
	public String getLevelThree() {
		return levelThree;
	}
	public void setLevelThree(String levelThree) {
		this.levelThree = levelThree;
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
	public String getLevelFour() {
		return levelFour;
	}
	public void setLevelFour(String levelFour) {
		this.levelFour = levelFour;
	}
	public String getMenuNameUnSelected() {
		return menuNameUnSelected;
	}
	public void setMenuNameUnSelected(String menuNameUnSelected) {
		this.menuNameUnSelected = menuNameUnSelected;
	}
	public String getNewMenuNameSelected() {
		return newMenuNameSelected;
	}
	public void setNewMenuNameSelected(String newMenuNameSelected) {
		this.newMenuNameSelected = newMenuNameSelected;
	}
	public String getNewMenuNameUnSelected() {
		return newMenuNameUnSelected;
	}
	public void setNewMenuNameUnSelected(String newMenuNameUnSelected) {
		this.newMenuNameUnSelected = newMenuNameUnSelected;
	}
	
	
	
	
	//Function for getting Modules
	public String getModuleCombo()
	{
		System.out.println("hi---->");	
		String html = "";
		String query = "select GNUM_MODULE_ID, initcap(GSTR_MODULE_NAME) from " +
				" GBLT_METATABLE_TYPE_MST where GBL_ISVALID = '1' " +
				//" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' "+
				//" and GNUM_HOSPITAL_CODE='100' "+
				"order by initcap(GSTR_MODULE_NAME)";
		System.out.println("queryModule---->"+query);		
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
	
	//Function for getting Roles
	public String getRoleCombo()
	{
		
		String html = "";
		if(this.module.length()>0)
		{
		if(!this.module.equals("0") || !this.module.equals(""))
		{
			String query = 	" select"+ 
							" GNUM_ROLE_ID, initcap(GSTR_ROLE_NAME)"+
							" from GBLT_ROLE_MST"+
							" where GNUM_MODULE_ID = '"+this.module+"'"+
							" and GBL_ISVALID = '1'"+
							"and GNUM_HOSPITAL_CODE='"+this.hospital_code+"'"+
							" order by initcap(GSTR_ROLE_NAME)";
			System.out.println("queryRole----"+query);

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
	
	
	//Function for getting Root Menus
	
	public String getRootMenuCombo()
	{		
		String html = "";
		
		 
	
			//String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+
							//" where GNUM_MENU_LEVEL='0' and gnum_isvalid='1' and GSTR_MENUCLASS_ID='R' and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' order by initcap(GSTR_MENU_NAME)";
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+
			" where GNUM_MENU_LEVEL='0' and gnum_isvalid='1' and GSTR_MENUCLASS_ID='R' order by initcap(GSTR_MENU_NAME)";

			System.out.println("queryRootMenuCombo----"+query);
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
	
	
	//Function for getting Level one options
	
	public String getLevelTwoOptions()
	{
		//System.out.println("query_ankur111...."+this.rootMenu);
		String html = "";
		
		
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					
				try
				{					
					
					
					String query = "";				
						
			

//This is a new query 
					
/*query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
" where GNUM_MENU_LEVEL ='1' and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' "+
" and GNUM_PARENT_ID='"+this.menuId+"' and GNUM_ISVALID ='1' and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"' "+
" order by GSTR_MENU_NAME";*/

query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
//" where GNUM_MENU_LEVEL ='1' and GNUM_HOSPITAL_CODE='100' "+
" where GNUM_MENU_LEVEL ='1' "+
" and GNUM_PARENT_ID='"+this.menuId+"' and GNUM_ISVALID ='1' and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"' "+
" order by GSTR_MENU_NAME";
					
					
				System.out.println("query_LevelTwo..."+query);
					
					html += super.getCombo("",query,this.levelTwo,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		}

		else
		{
			
			html += "<option value='0'>Select Value</option>";
		}
		return html;
		
	}
	
	
	//Function for getting Level Two options
 	public String getLevelThreeOptions()
	{
		String html = "";
		
		
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals("") &&
				!this.levelTwo.equals("0") && !this.levelTwo.equals(""))
		{					
				try
				{					
					
					
					String query = "";
					
			
					
					/*query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
					" where  GNUM_MENU_LEVEL ='2' and GNUM_PARENT_ID='"+this.levelTwo+"'"+
					" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' and  GNUM_ISVALID ='1' "+
					"and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"'"+
					" order by GSTR_MENU_NAME";*/
					
					query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
					" where  GNUM_MENU_LEVEL ='2' and GNUM_PARENT_ID='"+this.levelTwo+"'"+
					//" and GNUM_HOSPITAL_CODE='100' and  GNUM_ISVALID ='1' "+
					" and  GNUM_ISVALID ='1' "+
					"and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"'"+
					" order by GSTR_MENU_NAME";
					
					
					System.out.println("query_LevelThree...."+query);
					
					html += super.getCombo("",query,this.levelThree,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		}
		else
		{
			
			html += "<option value='0'>Select Value</option>";
		}
		
		return html;
		
	}
 	
 	
 	
 	//Function for getting Level Three options
 	public String getLevelFourOptions()
	{
		String html = "";
		
		
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals("") &&
				!this.levelTwo.equals("0") && !this.levelTwo.equals("") &&
				!this.levelThree.equals("0") && !this.levelThree.equals(""))
		{					
				try
				{					
					
					
					String query = "";
					
			
					
					/*query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
					" where  GNUM_MENU_LEVEL ='3' and GNUM_PARENT_ID='"+this.levelThree+"'"+
					" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' and  GNUM_ISVALID ='1' "+
					"and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"'"+
					" order by GSTR_MENU_NAME";*/
					
					query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
					" where  GNUM_MENU_LEVEL ='3' and GNUM_PARENT_ID='"+this.levelThree+"'"+
					//" and GNUM_HOSPITAL_CODE='100' and  GNUM_ISVALID ='1' "+
					" and  GNUM_ISVALID ='1' "+
					"and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"'"+
					" order by GSTR_MENU_NAME";
					
					
					System.out.println("query_LevelFour...."+query);
					
					html += super.getCombo("",query,this.levelFour,"",0);				
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		}

		else
		{
			
			html += "<option value='0'>Select Value</option>";
		}
		
		return html;
		
	}

 
 	//Function for getting Left List Box
	public String getFirstMenuNamesAdd()
	{
		
		String html = "";
		String concateMenu="";
		
		html = "<select name='firstMenu' multiple size='6'>";
		
		
		List selectedMenus = new ArrayList();
		
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{
		
			
				try
				{			
					
					
					String query1 = "";
					String query2 = "";
					String query3 = "";
					String query4 = "";
					String Finalquery = "";
					String menuLevelCond = "";
					String levelTwoCond = "";
					
					
String conditionQuery="";

  conditionQuery=" (select distinct gnum_menu_id from  gblt_role_menu_mst " +
		         " where GNUM_ROLE_ID='"+this.role+"' and " +
		         " GNUM_HOSPITAL_CODE='"+this.hospital_code+"'" +
				 " and GNUM_ISVALID='"+this.isvalid+"' " +
				 " AND gdt_effective_frm <= SYSDATE "+
                " AND (TO_CHAR(NVL (gdt_effective_to, SYSDATE),'DD-MM-YY') >= TO_CHAR (SYSDATE, 'DD-MM-YY')))";
                                     
                            
			
System.out.println("conditionQuery----->"+conditionQuery);	

					
						
query2=" select GNUM_MENU_ID||'#'||GNUM_PARENT_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
	" where GNUM_MENU_LEVEL='2' and  GNUM_PARENT_ID='"+this.levelTwo+"' and "+
	//" GNUM_HOSPITAL_CODE='100' and  GNUM_ISVALID ='1' "+
	" GNUM_ISVALID ='1' "+
	" and  gstr_url is not null and GNUM_MODULE_ID= '"+this.module+"' ";
	
						
						
					//	System.out.println("Leaf query2...."+query2);					
						
						
						query3=" select GNUM_MENU_ID||'#'||GNUM_PARENT_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
						" where  GNUM_MENU_LEVEL='3' and  GNUM_PARENT_ID='"+this.levelThree+"' and "+
						//" GNUM_HOSPITAL_CODE='100' and  GNUM_ISVALID ='1' "+
						" GNUM_ISVALID ='1' "+
						" and  gstr_url is not null and GNUM_MODULE_ID= '"+this.module+"' ";
						
						
						query4=" select GNUM_MENU_ID||'#'||GNUM_PARENT_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
						" where  GNUM_MENU_LEVEL='4' and  GNUM_PARENT_ID='"+this.levelFour+"' and "+
						//" GNUM_HOSPITAL_CODE='100' and  GNUM_ISVALID ='1' "+
						" GNUM_ISVALID ='1' "+
						" and  gstr_url is not null and GNUM_MODULE_ID= '"+this.module+"' ";
						
						//System.out.println("Leaf query3..."+query3);			
						
		
					//	System.out.println("root..."+this.rootMenu);
					//	System.out.println("menuid..."+this.menuId);
					//	System.out.println("levelTwo..."+this.levelTwo);
					//	System.out.println("levelThree..."+this.levelThree);
					//	System.out.println("levelFour..."+this.levelFour);
						
   if(!this.levelFour.equals("0") && !this.levelFour.equals(""))						
	   Finalquery =query4+" and GNUM_MENU_ID not in "+conditionQuery+" ORDER BY gstr_menu_name ";
      else
	   	if(!this.levelThree.equals("0") && !this.levelThree.equals(""))						
	   		Finalquery =query3+"and GNUM_MENU_ID not in "+conditionQuery+" ORDER BY gstr_menu_name ";
	       else
      	   	if(!this.levelTwo.equals("0") && !this.levelTwo.equals(""))						
      	   	   Finalquery =query2+"and GNUM_MENU_ID not in "+conditionQuery+" ORDER BY gstr_menu_name ";
      	    
      	
      	
	    	    	    
	    	
						
            System.out.println("Final Leaf query Left side......>"+Finalquery);
						
						HisResultSet rs = null;
						if(Finalquery!="")
						rs = super.getRecord(Finalquery,true);
						String str1 = "";
						String str2 = "";
						
						while(rs.next())
						{

							str1 = rs.getString(1);
							str2 = rs.getString(2);
							concateMenu+=str1+"^";  							
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
		this.menuNameUnSelected=concateMenu;
		return html;
	
	}
	
	
	//Function for getting Right List Box
	public String getSecondMenuNamesAdd()
	{
		
		String html = "";
		String concateMenu="";
		String previousMenuList = "";
		html += "<select name='secondMenu' size='6' multiple>";
		
		
		
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{
		
			
				try
				{			
					
					
					
					String query2 = "";
					String query3 = "";
					String query4 = "";
					String Finalquery = "";
					String menuLevelCond = "";
					String levelTwoCond = "";
					
					
				
						
query2=" select a.GNUM_MENU_ID||'#'||a.GNUM_PARENT_ID,initcap(a.GSTR_MENU_NAME) " +
	   "  FROM gblt_menu_mst a,gblt_role_menu_mst b"+ 
	   "  where " +
	   "  a.GNUM_MENU_ID=b.GNUM_MENU_ID and " +
	   "  a.GNUM_MENU_LEVEL='2' and  " +
	   "  a.GNUM_PARENT_ID='"+this.levelTwo+"' and  " +
	   "  b.GNUM_HOSPITAL_CODE='"+this.hospital_code+"'  and  "+
	  // "  a.GNUM_HOSPITAL_CODE='100' and  a.GNUM_ISVALID ='1' "+
	   "  a.GNUM_ISVALID ='1' "+
	   "  and b.GNUM_ISVALID='1' "+
	   "  and  a.gstr_url is not null and a.GNUM_MODULE_ID= '"+this.module+"' "+
	   "  and b.gnum_role_id = '"+this.role+"' "+
	   " AND gdt_effective_frm <= SYSDATE "+
       " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE),'DD-MM-YY' ) >= TO_CHAR (SYSDATE, 'DD-MM-YY'))"+           
	   " ORDER BY b.gnum_display_order";
	
						
						
					System.out.println("Leaf query2...."+query2);					
						
						
query3=" select a.GNUM_MENU_ID||'#'||a.GNUM_PARENT_ID,initcap(a.GSTR_MENU_NAME) " +
	   "  FROM gblt_menu_mst a,gblt_role_menu_mst b"+ 
	   "  where " +
	   "  a.GNUM_MENU_ID=b.GNUM_MENU_ID and " +
	   "  a.GNUM_MENU_LEVEL='3' and  " +
	   "  a.GNUM_PARENT_ID='"+this.levelThree+"' and  " +
	   "  b.GNUM_HOSPITAL_CODE='"+this.hospital_code+"'  and  "+
	  // "  a.GNUM_HOSPITAL_CODE='100' and  a.GNUM_ISVALID ='1' "+
	   "  a.GNUM_ISVALID ='1' "+
	   "  and b.GNUM_ISVALID='1' "+
	   "  and  a.gstr_url is not null and a.GNUM_MODULE_ID= '"+this.module+"' "+
	   "  and b.gnum_role_id = '"+this.role+"' "+
	   " AND gdt_effective_frm <= SYSDATE "+
	   " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE),'DD-MM-YY' ) >= TO_CHAR (SYSDATE, 'DD-MM-YY'))"+           
	   " ORDER BY b.gnum_display_order";				
						
				System.out.println("Leaf query3..."+query3);	
						
						
query4=" select a.GNUM_MENU_ID||'#'||a.GNUM_PARENT_ID,initcap(a.GSTR_MENU_NAME) " +
	   "  FROM gblt_menu_mst a,gblt_role_menu_mst b"+ 
	   "  where " +
	   "  a.GNUM_MENU_ID=b.GNUM_MENU_ID and " +
	   "  a.GNUM_MENU_LEVEL='4' and  " +
	   "  a.GNUM_PARENT_ID='"+this.levelFour+"' and  " +
	   "  b.GNUM_HOSPITAL_CODE='"+this.hospital_code+"'  and  "+
	 //  "  a.GNUM_HOSPITAL_CODE='100' and  a.GNUM_ISVALID ='1' "+
	   "  a.GNUM_ISVALID ='1' "+
	   "  and b.GNUM_ISVALID='1' "+
	   "  and  a.gstr_url is not null and a.GNUM_MODULE_ID= '"+this.module+"' "+
	   "  and b.gnum_role_id = '"+this.role+"' "+
	   " AND gdt_effective_frm <= SYSDATE "+
	   " AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE),'DD-MM-YY' ) >= TO_CHAR (SYSDATE, 'DD-MM-YY'))"+           
	   " ORDER BY b.gnum_display_order";					
						
				System.out.println("Leaf query4..."+query4);			
						
		
				//		System.out.println("root..."+this.rootMenu);
				//		System.out.println("menuid..."+this.menuId);
				//		System.out.println("levelTwo..."+this.levelTwo);
				//		System.out.println("levelThree..."+this.levelThree);
				//		System.out.println("levelFour..."+this.levelFour);
						
   if(!this.levelFour.equals("0") && !this.levelFour.equals(""))						
	   Finalquery =query4;
      else
	   	if(!this.levelThree.equals("0") && !this.levelThree.equals(""))						
	   		Finalquery =query3;
	       else
      	   	if(!this.levelTwo.equals("0") && !this.levelTwo.equals(""))						
      	   	   Finalquery =query2;
      	    
      	
      	
	    	    	    
	    	
						
            System.out.println("Final Leaf query right side.....>"+Finalquery);
						
						HisResultSet rs = null;
						if(Finalquery!="")
						rs = super.getRecord(Finalquery,true);
						String str1 = "";
						String str2 = "";
						
						while(rs.next())
						{
							str1 = rs.getString(1);
							str2 = rs.getString(2);
							concateMenu+=str1+"^";
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
	
	///	System.out.println("values---"+selectedMenus.get(1).toString());
		
		html += "</select>";
		
		this.menuNameSelected=concateMenu;
		
		return html;
	
	}
	
//For inserting record for Role Menu master
	

	public boolean newInsertRecord()
	{
		boolean retvalue = true;
		Connection conn = null;
		ResultSet rs3 = null;
		Statement stmt =  null;
		
		
		
		

		
		
		
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



String strMenuId = "";

StringTokenizer st = new StringTokenizer(this.menuSelected,"^");

//System.out.println("menuNameSelected"+this.menuNameSelected);
//System.out.println("menuNameUnSelected"+this.menuNameUnSelected);
//System.out.println("NewmenuNameSelected"+this.newMenuNameSelected);
//System.out.println("NewmenuNameUnSelected"+this.newMenuNameUnSelected);
//System.out.println("previousMenuList"+this.previousMenuList);	


List lstOne = new ArrayList();////contains old selected menu
List lstTwo = new ArrayList();//contains old unelected menu
List lstThree = new ArrayList();////contains new selected menu
List lstFour = new ArrayList();//contains new unselected menu

StringTokenizer stOne = new StringTokenizer(this.menuNameSelected,"^");
while(stOne.hasMoreElements())
{
	lstOne.add(stOne.nextToken().toString());		

}


StringTokenizer stTwo = new StringTokenizer(this.menuNameUnSelected,"^");
while(stTwo.hasMoreElements())
{
	lstTwo.add(stTwo.nextToken().toString());		

}


StringTokenizer stThree = new StringTokenizer(this.newMenuNameSelected,"^");
while(stThree.hasMoreElements())
{
	lstThree.add(stThree.nextToken().toString());		

}



StringTokenizer stFour = new StringTokenizer(this.newMenuNameUnSelected,"^");
while(stFour.hasMoreElements())
{
	lstFour.add(stFour.nextToken().toString());		

}


List recToInsert = new ArrayList();
List recToDelete = new ArrayList();
List recToModify = new ArrayList();

int i = 0;

//FINDING  THE MENU IDS TO BE DELETED
for( i=0;i<lstFour.size();i++)
{
	
	boolean found = false;
	for(int t=0;t<lstOne.size();t++)			
	{
		if(lstFour.get(i).toString().equals(lstOne.get(t).toString()))
		{
			found = true;
			break;
		}
		
	}
	//System.out.println("value to delete---->"+ lstTwo.get(i));
	if(found)
		recToDelete.add(lstFour.get(i));	
}


//FINDING THE MENU IDS to be inserted 
for( i=0;i<lstTwo.size();i++)
{
	
	boolean found = false;
	for(int t=0;t<lstThree.size();t++)			
	{
		if(lstTwo.get(i).toString().equals(lstThree.get(t).toString()))
		{
			found = true;
			break;
		}
		
	}
	
	if(found)
	 { recToInsert.add(lstTwo.get(i));	
	// System.out.println("value to insert---->"+ lstTwo.get(i));
	 }
}

//FINDING THE MENU IDS to be modifed only according to there display order
for( i=0;i<lstOne.size();i++)
{
	
	boolean found = false;
	for(int t=0;t<lstThree.size();t++)			
	{
		if(lstOne.get(i).toString().equals(lstThree.get(t).toString()))
		{
			found = true;
			break;
		}
		
	}
	
	if(found)
		{  recToModify.add(lstOne.get(i));	
		   System.out.println("value to modify---->"+ lstOne.get(i));
		}
		
}


	
		try
		{
			conn = super.getConnection();	
			conn.setAutoCommit(false);			
			stmt = conn.createStatement();
		
			
			
			//For executing delete query
			if(recToDelete.size()!=0)
			{
				for(i=0;i<recToDelete.size();i++)
						{
					String strToDelete="";
				//strToDelete = recToDelete.get(i).toString();
				String strToDelete1[] = recToDelete.get(i).toString().split("#");
				strToDelete=strToDelete1[0];
				String deleteQuery = "";
			  deleteQuery = " UPDATE GBLT_ROLE_MENU_MST "+
							"set GNUM_ISVALID='0',GDT_EFFECTIVE_TO=to_date('"+strDate+"','dd-mon-yyyy') "+
							"where GNUM_ISVALID='"+this.isvalid+"' and " +
							" GNUM_HOSPITAL_CODE='"+this.hospital_code+"' and "+
							" GNUM_ROLE_ID='"+this.role+"'and " +					
							" GNUM_MENU_ID=("+strToDelete+")";
			System.out.println("delete_query----->"+ deleteQuery);
							stmt.addBatch( deleteQuery);
						}
				}
				
			
			//For executing insert query
			if(recToInsert.size()!=0)
			{
				for(i=0;i<recToInsert.size();i++)
						{
					int displayorder=0;
					String strToInsert="";
					String strpIdToInsert="";
					 String insertQuery = "";
					 
					//strToInsert = recToInsert.get(i).toString();	Modified by Singaravelan to add parent_id in GBLT_ROLE_MENU_MST on 10-05-14
					String strToInsert1[] = recToInsert.get(i).toString().split("#");
					strToInsert=strToInsert1[0];
					strpIdToInsert=strToInsert1[1];
					
					//displayorder=lstThree.indexOf(strToInsert)+1;
					displayorder=lstThree.indexOf(recToInsert.get(i).toString())+1;
					
					//System.out.println("strToInsert---->"+strToInsert);
					//System.out.println("value---->"+lstThree.get(0).toString());
					//System.out.println("displayorder----->"+displayorder);
				   
				
				
				String query_fetchslno=	"select max(a.GNUM_ROLE_MENU_SLNO) from "+
		        " gblt_role_menu_mst a "+
		        " where "+
		        " a.GNUM_HOSPITAL_CODE='"+this.hospital_code+"'  and "+       
		        " a.GNUM_MENU_ID='"+strToInsert+"' and"+
		        " a.GNUM_ROLE_ID='"+this.role+"' "; 
		 

		//	System.out.println("query_fetchslno----->"+query_fetchslno);
				HisResultSet rs1 = null;
				String slno = "";
				int iSlno=0;
				

				try
				{		
				rs = super.getRecord(query_fetchslno,true);



				while(rs.next())
				{
					slno = rs.getString(1);
						
				}
						

				}
				catch(Exception ex)
				{
				System.out.println("Exception there in getting data from database "+ex);
				}
				
			//	System.out.println("String slno1----->"+slno);
				
				if(slno==null || slno=="")
					iSlno=1;
				else
					iSlno=Integer.parseInt(slno)+1;
			//	System.out.println("Integer slno2----->"+iSlno);
				
			 
	insertQuery="insert into gblt_role_menu_mst "+
		"( GNUM_MODULE_ID, GNUM_ROLE_ID, GNUM_MENU_ID, GNUM_PARENT_ID, GNUM_ROLE_MENU_SLNO, GNUM_HOSPITAL_CODE," +
		" GDT_EFFECTIVE_FRM,GNUM_SEATID, GNUM_ISVALID,GNUM_DISPLAY_ORDER)"+
		" values ('"+this.module+"','"+this.role+"','"+strToInsert+"','"+strpIdToInsert+"','"+iSlno+"','"+this.hospital_code+"'," +
		"to_date('"+strDate+"','dd-mon-yyyy'),'"+this.seatId+"','"+this.isvalid+"','"+displayorder+"' )";

				System.out.println("insertQuery----->"+ insertQuery);
							stmt.addBatch( insertQuery);
							
						}
				}
			
			
			
			//For executing modify query
			System.out.println("---recToModify.size()-----"+recToModify.size()+"---------");
			if(recToModify.size()!=0)
			{
				for(i=0;i<recToModify.size();i++)
						{
					String strToInsert="";
					String updateQuery = "";
					int displayorder=0;
					
					//strToInsert = recToModify.get(i).toString();	Modified by Singaravelan to add parent_id in GBLT_ROLE_MENU_MST on 10-05-14
					String strToInsert1[] = recToModify.get(i).toString().split("#");
					strToInsert=strToInsert1[0];
					
					//displayorder=lstThree.indexOf(strToInsert)+1;
					displayorder=lstThree.indexOf(recToModify.get(i).toString())+1;
			 
				updateQuery= " UPDATE GBLT_ROLE_MENU_MST "+
				"set GNUM_DISPLAY_ORDER='"+displayorder+"' "+
				"where GNUM_ISVALID='"+this.isvalid+"' and " +
				" GNUM_HOSPITAL_CODE='"+this.hospital_code+"' and "+
				" GNUM_ROLE_ID='"+this.role+"'and " +					
				" GNUM_MENU_ID=("+strToInsert+")";
				System.out.println("updateQuery----->"+ updateQuery);
							stmt.addBatch( updateQuery);
							}
				}
			
			
							
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			this.status = "Record Successfully Inserted !";
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
			this.status = "Error while Inserting Record! "+e;
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
	public void initializeNewMode()
	{
		
		this.menuSelected 	= "";
	}
	public List initializeUpdateMode()
	{
		
		List lst = null;
		List lstlevel1=null;
		List lstroot=null;
		try
		{
			StringTokenizer st = new StringTokenizer(this.chk[0],"^");
			String t_role 	= "";
			String t_module = "";
			String t_menu	= "";
			String t_menuSNo	= "";
			
			t_module 		= st.nextElement().toString();
			t_role 			= st.nextElement().toString();				
			t_menu 			= st.nextElement().toString();
			t_menuSNo       = st.nextElement().toString();
				
			this.role = t_role;
			this.module = t_module;
			this.menuId_selected = t_menu;
			this.menuSNo=t_menuSNo;
		
			
			String query =" select"+
				" ("+
				" 		select GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST x"+
				" 		where e.GNUM_MODULE_ID = x.GNUM_MODULE_ID"+
				//commented for Maharshtra" 		where e.GNUM_HOSPITAL_CODE = x.GNUM_HOSPITAL_CODE"+//Changed by Garima for Ayush
				" ) module_name,"+
				" ("+
				" 		select GSTR_ROLE_NAME from GBLT_ROLE_MST x"+
				" 		where e.GNUM_ROLE_ID = x.GNUM_ROLE_ID"+
				" 		and  x.GNUM_MODULE_ID = e.gnum_module_id"+
				" )role_name,"+
				" ("+
				" 		select GSTR_MENU_NAME from GBLT_MENU_MST x"+ 
				" 		where"+ 
				" 		e.GNUM_MENU_ID= x.GNUM_MENU_ID"+
				" )user_name," +
				"( 		select GNUM_PARENT_ID from GBLT_MENU_MST x 	" +
				"	where 		e.GNUM_MENU_ID= x.GNUM_MENU_ID )parent_id_3,"+
				" e.GNUM_ISVALID,e.GNUM_MENU_ID"+
				" from"+
				" ("+
				"		select"+ 
				"		distinct GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_MENU_ID,GNUM_ISVALID"+
				"		from GBLT_ROLE_MENU_MST "+
				" 		WHERE GNUM_MODULE_ID = '"+t_module+"'"+
				" 		AND GNUM_ROLE_ID = '"+t_role+"'"+
				" 		AND GNUM_MENU_ID = '"+t_menu+"'"+
				"and GNUM_ROLE_MENU_SLNO='"+t_menuSNo+"' and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' "+
				" )e";
				
			System.out.println("query_UPDATE---"+query);
				 
				try
				{
				lst = super.getDetails(query,6);
				}
				catch(Exception e)
				{
					System.out.println("Exception there in initialize update mode "+e);
				}
				
				
				
				this.moduleName = lst.get(0).toString();
				this.roleName   = lst.get(1).toString();
				this.menuSelected	   = lst.get(2).toString();
				this.levelThree=lst.get(3).toString();
				this.isvalid    = lst.get(4).toString();
				this.previousIsValid = lst.get(4).toString();
				this.menuId=lst.get(5).toString();
			//	System.out.println("moduleName---"+this.moduleName+"---roleName---"+this.roleName+"--Menu name---"+menuSelected+"---MenuId---"+this.menuId+"menuId_selected"+menuId_selected);
				
			
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
				
		
		
		
	//getting the SyStem date
		
		String query_date=	"select to_char(sysdate,'dd-mon-yyyy') from dual";

	//	System.out.println("query_fetchData----->"+query_date);
		HisResultSet rs1 = null;
		String strDate = "";

		try
		{		
		rs1 = super.getRecord(query_date,true);



		while(rs1.next())
		{
			strDate = rs1.getString(1);
				
		}
				

		}
		catch(Exception ex)
		{
		System.out.println("Exception there in getting data from database "+ex);
		}
		
		
		
			
		
		
		
		
//updating the status of isValid to 0 and GDT_EFFECTIVE_TO ,with system date for the records that exist in the database 
//		but are supposed to be removed while updating
		
		
		
		
		String menuToDelete = "";
		
		
		Connection conn = null;
		Statement stmt = null;
		
		try
		{
			conn = super.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			if(lstOne.size()!=0)
			{
				for(int i=0;i<lstOne.size();i++)
						{
				menuToDelete = lstOne.get(i).toString();
				String query1 = "";
				query1 = 	" UPDATE GBLT_ROLE_MENU_MST "+
							"set GNUM_ISVALID='0'," +
							"GDT_EFFECTIVE_TO=to_date('"+strDate+"','dd-mon-yyyy') "+
							"where " +
							" GNUM_HOSPITAL_CODE='"+this.hospital_code+"' and "+
							" GNUM_ROLE_ID='"+this.role+"'and " +					
							" GNUM_MENU_ID='"+menuToDelete+"' and "+
							"GNUM_ISVALID='"+this.isvalid+"'";
		//		System.out.println("update_query----->"+query1);
							stmt.addBatch(query1);
						}
				}
					
	//getting the serial no.	
			
			try {
				slNo = getNextNo("GNUM_ROLE_MENU_SLNO","GBLT_ROLE_MENU_MST","101",this.hospital_code);
				System.out.println("slNo is "+slNo);
				} catch (Exception e1) {
				
				e1.printStackTrace();
								    	}	
				int iSlno=Integer.parseInt(slNo);
			
		//		System.out.println("iSlno is "+iSlno);
					
//inserting the new enteries into the  table that
//	are supposed to be added  
					
							if(lstTwo.size()!=0)
							{
								
								for(int i=0;i<lstTwo.size();i++)
								{
						String query3 = "";
						String menuToInsert = "";
						menuToInsert=lstTwo.get(i).toString();
query3 = 	" insert into GBLT_ROLE_MENU_MST "+
"(GNUM_MODULE_ID, GNUM_ROLE_ID, GNUM_MENU_ID, GNUM_ROLE_MENU_SLNO,  GNUM_HOSPITAL_CODE,GDT_EFFECTIVE_FRM, GNUM_SEATID,GNUM_ISVALID)"+
"VALUES  ('"+this.module+"','"+this.role+"','"+menuToInsert+"','"+iSlno+"','"+this.hospital_code+"',to_date('"+strDate+"','dd-mon-yyyy'),'"+this.seatId+"','"+this.isvalid+"')";
						
				//		System.out.println("insert_query3----->"+query3);
									stmt.addBatch(query3);
									
									iSlno++;	
								}
							}
					
					
					
					stmt.executeBatch();
					conn.commit();
					conn.setAutoCommit(true);
					this.status = "Record Successfully Updated !";
					
		}
		catch(Exception ex)
		{
			retVal = false;
			this.status = "Exception while Modifying Record !"+ex;
			
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
		html += "<select name='firstMenu' size='6' multiple>";
		
	
			
			
		
		String t_role 	= "";
		String t_module = "";
		String t_menuId 	= "";
		String t_menuSNo 	= "";
			
		if(this.chk!=null)
		{
			System.out.println("Ankurthis.>..."+this.chk[0]);
			
			StringTokenizer st = new StringTokenizer(this.chk[0],"^");					
							
			t_module 	= st.nextElement().toString();
			t_role 		= st.nextElement().toString();				
			t_menuId 	= st.nextElement().toString();
			t_menuSNo   = st.nextElement().toString();
			
			this.module = t_module;
			this.role 	= t_role;
this.menuId_selected 	= t_menuId;
		   this.menuSNo = t_menuSNo;
			
		}
		else
		{
			
			t_module 	= this.module;
			t_role 		= this.role;				
			t_menuId 		= this.menuId_selected;
			t_menuSNo=this.menuSNo;
			
		}
		if(t_module.length()==1)
			t_module = "0"+t_module;
		
		if(!(this.rootMenu==null) && !(this.rootMenu.equals("")))
		{
			
String menuQuery =" select 	e.GNUM_MENU_ID "+
				  " from GBLT_ROLE_MENU_MST e"+
				  "  WHERE" +
				  "  e.GNUM_ROLE_ID = '"+t_role+"' "+ 
				  " and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' " +
				  " and GNUM_ISVALID='"+this.isvalid+"' ";
					
		//	System.out.println("menuQuery..."+menuQuery);	
					
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
							" from GBLT_MENU_MST"+
							" where GNUM_ISVALID ='1' "+
							" AND GNUM_MENU_ID  not IN ("+menuQuery+") "+
							" and GNUM_PARENT_ID='"+this.levelThree+"'  "+
						//	" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' " +
							" order by GSTR_MENU_NAME";
		
	//		System.out.println("viewQuery..."+query);	
				
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
					
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in getSecondMenuNameModify "+ex);
				}
				
		}
		
		return html;
		
	}
		
	

	
	public String getSecondMenuNameModify()
	{
		String previousSelected="";
		String html = "";			
		html += "<select name='secondMenu' size='6' multiple>";
		
	
			
			
		
		String t_role 	= "";
		String t_module = "";
		String t_menuId 	= "";
		String t_menuSNo 	= "";
			
		if(this.chk!=null)
		{
		//	System.out.println("Ankurthis.>..."+this.chk[0]);
			
			StringTokenizer st = new StringTokenizer(this.chk[0],"^");					
							
			t_module 	= st.nextElement().toString();
			t_role 		= st.nextElement().toString();				
			t_menuId 	= st.nextElement().toString();
			t_menuSNo   = st.nextElement().toString();
			
			this.module = t_module;
			this.role 	= t_role;
this.menuId_selected 	= t_menuId;
		   this.menuSNo = t_menuSNo;
			
		}
		else
		{
			
			t_module 	= this.module;
			t_role 		= this.role;				
			t_menuId 		= this.menuId_selected;
			t_menuSNo=this.menuSNo;
			
		}
		if(t_module.length()==1)
			t_module = "0"+t_module;
		
		if(!(this.rootMenu==null) && !(this.rootMenu.equals("")))
		{
			
String menuQuery =" select 	e.GNUM_MENU_ID "+
				" from GBLT_ROLE_MENU_MST e"+
				" WHERE" +
				"   e.GNUM_ROLE_ID = '"+t_role+"' "+ 
				" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' " +
				" and GNUM_ISVALID='"+this.isvalid+"' ";
					
		//	System.out.println("menuQuery..."+menuQuery);	
					
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
							" from GBLT_MENU_MST"+
							" where GNUM_ISVALID ='1' "+
							" AND GNUM_MENU_ID  IN ("+menuQuery+")"+
							" and GNUM_PARENT_ID='"+this.levelThree+"' "+
						//	" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' " +
							" order by GSTR_MENU_NAME";
		
		//	System.out.println("viewQuery..."+query);	
				
				HisResultSet rs = null;
				try
				{		
						rs = super.getRecord(query,true);
						String str1 = "";
						String str2 = "";
						
						while(rs.next())
						{
							previousSelected+=rs.getString(1)+"^";
							
							str1 = rs.getString(1);
							str2 = rs.getString(2);
							html += "<option value='"+str1+"'>"+str2+"</option>";	
							
							
						}
						
						html += "</select>";
						
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in getSecondMenuNameModify "+ex);
				}
				
		}
		
		this.previousMenuList=previousSelected;
		return html;
		
	}
		
	
	
	
	
	public String getLevelTwoOptionsModify()
	{
	//	System.out.println("modify111...."+this.rootMenu);
		String html = "";
		
		
		if(!this.rootMenu.equals("0") && !this.rootMenu.equals(""))
		{					
				try
				{					
					String tModule = "";				
					
					if(this.module.length()==1)
						tModule = "0"+this.module;				
					
					String query = "";				
						
									
					query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
					" where GNUM_MENU_LEVEL ='1' "+
					//" where GNUM_MENU_LEVEL ='1' and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' "+
					" and GNUM_PARENT_ID='"+this.menuId+"' and GNUM_ISVALID ='1' and  gstr_url is null" +
					" and GNUM_MODULE_ID= '"+this.module+"' "+
					" order by GSTR_MENU_NAME";
					
					System.out.println("query_modi2...."+query);					
					
					
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
		
		return html;
		
	}
	
	public String getLevelThreeOptionsModify()
	{
		String html = "";
		
	
		if(!this.levelTwo.equals("0") && !this.levelTwo.equals(""))
		{					
				try
				{					
					String tModule = "";				
					
					if(this.module.length()==1)
						tModule = "0"+this.module;				
					
					String query = "";				
						
					
					query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
					" where  GNUM_MENU_LEVEL ='2' and GNUM_PARENT_ID='"+this.levelTwo+"'"+
					//" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' and  GNUM_ISVALID ='1' "+
					" and  GNUM_ISVALID ='1' "+
					"and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"'"+
					" order by GSTR_MENU_NAME";
				System.out.println("query_modi2...."+query);		
				
					
					
					
					html += super.getCombo("",query,this.levelThree,"",0);				
						
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
		
		return html;
		
	}
	public String getLevelFourOptionsModify()
	{
		String html = "";
		
	
		if(!this.levelTwo.equals("0") && !this.levelTwo.equals(""))
		{					
				try
				{					
					String tModule = "";				
					
					if(this.module.length()==1)
						tModule = "0"+this.module;				
					
					String query = "";				
						
					
					
					query=" select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+ 
					" where  GNUM_MENU_LEVEL ='3' and GNUM_PARENT_ID='"+this.levelThree+"'"+
					//" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' and  GNUM_ISVALID ='1' "+
					" and  GNUM_ISVALID ='1' "+
					"and  gstr_url is null and GNUM_MODULE_ID= '"+this.module+"'"+
					" order by GSTR_MENU_NAME";
					System.out.println("query_modi_3...."+query);		
					
					
					html += super.getCombo("",query,this.levelFour,"",0);				
						
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
		
		return html;
		
	}
	
	public String deleteRecord()
	{
		String status = "";
		Connection conn = null;		
		PreparedStatement ps = null;
		
		
		
//getting the SyStem date
		
		String query_date=	"select to_char(sysdate,'dd-mon-yyyy') from dual";

	//	System.out.println("query_fetchData----->"+query_date);
		HisResultSet rs1 = null;
		String strDate = "";

		try
		{		
		rs1 = super.getRecord(query_date,true);



		while(rs1.next())
		{
			strDate = rs1.getString(1);
				
		}
				

		}
		catch(Exception ex)
		{
		System.out.println("Exception there in getting data from database "+ex);
		}
		
		
		
		
		
		
		
		
		
		String query="update gblt_role_menu_mst set gnum_isvalid='0',GDT_EFFECTIVE_TO='"+strDate+"' " +
				"where GNUM_ROLE_ID=? and " +
				" GNUM_MENU_ID=? and " +
				" GNUM_ROLE_MENU_SLNO=?" +
				"and  GNUM_HOSPITAL_CODE='"+this.hospital_code+"' ";
	
		
	//	System.out.println("delete_Query---->"+query);
		
		String t_role 	= "";
		String t_module = "";
		String t_moduleId 	= "";
		String t_menuSNo	= "";
		
		int i = 0;
		try
		{
			conn = super.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query);
				
				
				
				for(i=0;i<this.chk.length;i++)
				{
					System.out.println("value of chk---->"+chk[i]);
				   
					StringTokenizer st = new StringTokenizer(this.chk[i],"^");
					
					
					while (st.hasMoreTokens())
				  
					        {
				
					t_module 	= st.nextElement().toString();
					t_role 		= st.nextElement().toString();					
					t_moduleId 	= st.nextElement().toString();
					t_menuSNo   = st.nextElement().toString();			
					        }
					
					ps.setString(1,t_role);
					ps.setString(2,t_moduleId);
					ps.setString(3,t_menuSNo);
					
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
	
	public String getRootMenuComboModify()
	{		
		String html = "";	 
	
		String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST"+
						" where GNUM_MENU_LEVEL='0'  and GSTR_MENUCLASS_ID='R'  " +
						//" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"'" +
						
						" order by initcap(GSTR_MENU_NAME)";
		//System.out.println("root query is"+query);
		System.out.println("root value"+this.rootMenu);
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
	
	public String	getSecondMenuNameView()
	{
		
		String html = "";			
		html += "<select name='secondMenu' size='6' multiple>";
		
	
			
			
		
		String t_role 	= "";
		String t_module = "";
		String t_menuId 	= "";
		String t_menuSNo 	= "";
			
		if(this.chk!=null)
		{
			//System.out.println("Ankurthis.>..."+this.chk[0]);
			
			StringTokenizer st = new StringTokenizer(this.chk[0],"^");					
							
			t_module 	= st.nextElement().toString();
			t_role 		= st.nextElement().toString();				
			t_menuId 	= st.nextElement().toString();
			t_menuSNo   = st.nextElement().toString();
			
			this.module = t_module;
			this.role 	= t_role;
this.menuId_selected 	= t_menuId;
		   this.menuSNo = t_menuSNo;
			
		}
		else
		{
			
			t_module 	= this.module;
			t_role 		= this.role;				
			t_menuId 		= this.menuId_selected;
			t_menuSNo=this.menuSNo;
			
		}
		if(t_module.length()==1)
			t_module = "0"+t_module;
		
		if(!(this.rootMenu==null) && !(this.rootMenu.equals("")))
		{
			
String menuQuery =" select 	e.GNUM_MENU_ID "+
"from GBLT_ROLE_MENU_MST e"+
" WHERE" +
"   e.GNUM_ROLE_ID = '"+t_role+"' "+ 
 " and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' " +
 		"and GNUM_ISVALID='"+this.isvalid+"' ";
					
	System.out.println("menuQuery..."+menuQuery);	
					
			String query =  " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+
							" from GBLT_MENU_MST"+
							" where GNUM_ISVALID ='1' "+
							" AND GNUM_MENU_ID  IN ("+menuQuery+") "+
							" and GNUM_PARENT_ID='"+this.levelThree+"'  "+
							//" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' " +
							" order by GSTR_MENU_NAME";
		
		System.out.println("viewQuery..."+query);	
				
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
					
						
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in getSecondMenuNameModify "+ex);
				}
				
		}
		
		return html;
		
	}
	
	//Function for getting Modules
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
							" and GNUM_MENU_LEVEL >1  "+
							//" and GNUM_HOSPITAL_CODE='"+this.hospital_code+"' " +
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
		Connection conn = null;
		ResultSet rs3 = null;
		PreparedStatement ps =  null;
		
		
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
	slNo = getNextNo("GNUM_ROLE_MENU_SLNO","GBLT_ROLE_MENU_MST","101",this.hospital_code);
	//System.out.println("slNo is "+slNo);
	} catch (Exception e1) {
	
	e1.printStackTrace();
					    	}	
	int iSlno=Integer.parseInt(slNo);


String str = "";

StringTokenizer st = new StringTokenizer(this.menuSelected,"^");

	
	


String insertQuery="insert into gblt_role_menu_mst "+
            "( GNUM_MODULE_ID, GNUM_ROLE_ID, GNUM_MENU_ID, GNUM_ROLE_MENU_SLNO, GNUM_HOSPITAL_CODE, GDT_EFFECTIVE_FRM, GDT_EFFECTIVE_TO,GNUM_SEATID, GNUM_ISVALID)"+
            " values "+
            "('"+this.module+"','"+this.role+"',?,?,'"+this.hospital_code+"',to_date('"+strDate+"','dd-mon-yyyy'),'','"+this.seatId+"','"+this.isvalid+"' )";
		
		
	//	System.out.println("save_query----"+insertQuery);
		
			
		
	
		try
		{
			conn = super.getConnection();	
			conn.setAutoCommit(false);			
			ps = conn.prepareStatement(insertQuery);
			while(st.hasMoreElements())
			{
				str = (String)st.nextElement();		
			//	System.out.println("STR----"+str);		
				ps.setString(1,str);
				ps.setInt(2,iSlno);
				iSlno++;//cannot use this
				ps.addBatch();
				
			}//while closed	
							
			ps.executeBatch();
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
	
	
}