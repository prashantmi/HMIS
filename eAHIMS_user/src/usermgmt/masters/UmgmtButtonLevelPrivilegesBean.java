/**
 * 
 */
package usermgmt.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import HisGlobal.HisResultSet;
import usermgmt.FuncLib;

/**
 * @author Asha
 * Creation Date:18th July 2011
 *
 */
public class UmgmtButtonLevelPrivilegesBean extends FuncLib 
{
	List selectedMenus = new ArrayList();
	List unSelectedMenus = new ArrayList();
	List newSelectedMenus = new ArrayList();
	List newunSelectedMenus = new ArrayList();
	private String	title	=	"";  
	private String	hospitalcode	=	"";  
	private String hmode 	= 	"";
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
	
	private String roleName 	= "";
	private String seatName 	= "";
	private String menuId 		= "";
	private String menuId_selected 		= "";
	private String previousMenuList	= "";
	private String previousIsValid = "";
	private String seatSelected = "";
	private String buttonPerm		= "";
	
	
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
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospital_code) {
		this.hospitalcode = hospital_code;
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
	public String getSeatSelected() {
		return seatSelected;
	}
	public void setSeatSelected(String seatSelected) {
		this.seatSelected = seatSelected;
	}
	
	public String getButtonPerm() {
		return buttonPerm;
	}
	public void setButtonPerm(String buttonPerm) {
		this.buttonPerm = buttonPerm;
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
	public String getSeatCombo()
	{
		System.out.println("hi---->");	
		String html = "";
		String query = "select GNUM_SEATID, initcap(GSTR_SEAT_NAME) from " +
				" GBLT_SEAT_MST where GNUM_ISVALID = '1' and GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' order by initcap(GSTR_SEAT_NAME)";
		System.out.println("queryModule---->"+query);		
		try
		{
			html = super.getCombo("",query,this.seat,"",0);
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
		if(this.seat.length()>0)
		{
		if(!this.seat.equals("0") || !this.seat.equals(""))
		{
			String query = 	" select"+ 
							" gsrm.GNUM_ROLE_ID, (select initcap(grm.GSTR_ROLE_NAME)"+
							" from GBLT_ROLE_MST grm" +
							" where grm.GBL_ISVALID = '1'"+
							" and grm.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"'"+
							" and grm.GNUM_ROLE_ID=gsrm.GNUM_ROLE_ID) as gstr_role_name"+
							" from GBLT_SEAT_ROLE_MST gsrm"+
							" where gsrm.GNUM_SEATID = '"+this.seat+"'"+
							" and gsrm.GBL_ISVALID = '1'"+
							"and gsrm.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"'"+
							" order by GSTR_ROLE_NAME";
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
	
	
	public String getButtonType()
	{
		String buttonType = "";
		if(this.role.length()>0)
		{
		
		try
		{			
			String query = "";
			query=" select b.GSTR_BUTTON_PERMISSION " +
				"  FROM GBLT_BUTTON_PERMISSIONS b"+ 
				"  where " +
				"  b.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"'  and  "+
				"   b.GBL_ISVALID='1' "+
				"  and b.gnum_role_id = '"+this.role+"' "+
				" AND GDT_EFFECT_FRM <= SYSDATE "+
				" AND (TO_CHAR (NVL (GDT_EFFECT_TO, SYSDATE),'DD-MM-YY') ) >= TO_CHAR (SYSDATE, 'DD-MM-YY')";        
		
			HisResultSet rs = null;
			if(query!="")
				rs = super.getRecord(query,true);
			String str1 = "";
		
			while(rs.next())
			{
				buttonType=rs.getString(1);
			}	
	
		}
		
		catch(Exception ex)
		{
			System.out.println("Exception there in initizlize update mode "+ex);
		}
		
		
	}
		return buttonType;
	}
 	//Function for getting Left List Box
	public String getFirstMenuNamesAdd()
	{String html = "";
	String concateMenu="";
		if(this.role.length()>0)
		{
		
		
		html = "<select name='firstMenu' multiple size='6'>";
		List selectedMenus = new ArrayList();
			try
			{			
				String query="";
				query="select b.GNUM_MENU_ID,initcap(a.GSTR_MENU_NAME)"+
  					"FROM gblt_menu_mst a,gblt_role_menu_mst b  where  "+
  					" a.GNUM_MENU_ID=b.GNUM_MENU_ID"+
  					" AND  b.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"'"+
  					" AND  a.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' "+
  					" AND  a.GNUM_ISVALID ='1'" +
  					" AND b.GNUM_ISVALID='1' and  a.gstr_url is not null "+
  					" AND b.gnum_role_id = '"+this.role+"' "+
  					" AND gdt_effective_frm <= SYSDATE "+
  					" AND (TO_CHAR (NVL (gdt_effective_to, SYSDATE),'DD-MM-YY' ) >= TO_CHAR (SYSDATE, 'DD-MM-YY'))"+
  					" AND b.gnum_menu_id NOT IN ( SELECT gbp.gnum_menu_id "+
  				             " FROM gblt_button_permissions gbp "+
  				             " WHERE gbp.gnum_menu_id = b.gnum_menu_id "+
  				             " AND gbp.gnum_hospital_code = '"+this.hospitalcode+"'"+
  				             " AND gbp.gbl_isvalid = '1' "+
  				             " AND gbp.gnum_role_id = b.gnum_role_id "+
  				             " AND gbp.gdt_effect_frm <= SYSDATE "+
  				             " AND (TO_CHAR (NVL (gbp.gdt_effect_to::timestamp, SYSDATE), 'DD-MM-YY')) >= TO_CHAR (SYSDATE, 'DD-MM-YY'))"+
  				     " ORDER BY a.gstr_menu_name";
	 
				System.out.println("Leaf query Left side......>"+query);
						
				HisResultSet rs = null;
				if(query!="")
					rs = super.getRecord(query,true);
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
		html += "</select>";
		this.menuNameUnSelected=concateMenu;
		
		}
		return html;
	}
	
	
	//Function for getting Right List Box
	public String getSecondMenuNamesAdd()
	{
		String html = "";
		String concateMenu="";
		if(this.role.length()>0)
		{
		html += "<select name='secondMenu' size='6' multiple>";
				try
				{			
					String query = "";
					query=" select b.GNUM_MENU_ID,initcap(a.GSTR_MENU_NAME),b.GSTR_BUTTON_PERMISSION " +
							"  FROM gblt_menu_mst a,GBLT_BUTTON_PERMISSIONS b"+ 
							"  where " +
							"  a.GNUM_MENU_ID=b.GNUM_MENU_ID and " +
							"  b.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"'  and  "+
							"  a.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' and  a.GNUM_ISVALID ='1' "+
							"  and b.GBL_ISVALID='1' "+
							"  and  a.gstr_url is not null  "+
							"  and b.gnum_role_id = '"+this.role+"' "+
							" AND GDT_EFFECT_FRM <= SYSDATE "+
							" AND (TO_CHAR (NVL (GDT_EFFECT_TO, SYSDATE),'DD-MM-YY' ) >= TO_CHAR(SYSDATE, 'DD-MM-YY'))"+           
							" ORDER BY a.GSTR_MENU_NAME";
						
					System.out.println("Final Leaf query right side.....>"+query);
						
					HisResultSet rs = null;
					if(query!="")
					rs = super.getRecord(query,true);
					String str1 = "";
					String str2 = "";
					while(rs.next())
					{
						str1 = rs.getString(1);
						str2 = rs.getString(2);
						this.buttonPerm=rs.getString(3);
						concateMenu+=str1+"^";
						html += "<option value='"+str1+"'>"+str2+"</option>";					
					}					
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		
		
		html += "</select>";
		this.menuNameSelected=concateMenu;
		}
		return html;
	
	}
	
// function for getting deleted data
	public void getDeletedMenuName()
	{
		String html = "";
		String concateMenu="";
				try
				{			
					String query = "";
					query=" select b.GNUM_MENU_ID,initcap(a.GSTR_MENU_NAME)" +
							"  FROM gblt_menu_mst a,GBLT_BUTTON_PERMISSIONS b"+ 
							"  where " +
							"  a.GNUM_MENU_ID=b.GNUM_MENU_ID and " +
							"  b.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"'  and  "+
							"  a.GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' and  a.GNUM_ISVALID ='1' "+
							"  and b.GBL_ISVALID='0' "+
							"  and b.gnum_role_id = '"+this.role+"' "+
							" AND GDT_EFFECT_FRM <= SYSDATE "+
							" AND (TO_DATE (NVL (GDT_EFFECT_TO, SYSDATE),'DD-MM-YY' ) >= TO_DATE (SYSDATE, 'DD-MM-YY'))"+           
							" ORDER BY a.GSTR_MENU_NAME";
						
					System.out.println("Deleted Menu query .....>"+query);
						
					HisResultSet rs = null;
					if(query!="")
					rs = super.getRecord(query,true);
					String str1 = "";
					String str2 = "";
					while(rs.next())
					{
						str1 = rs.getString(1);
						str2 = rs.getString(2);
						concateMenu+=str1+"^";
					}					
				}
				catch(Exception ex)
				{
					System.out.println("Exception there in initizlize update mode "+ex);
				}
		
		
		this.previousMenuList=concateMenu;
//		return html;
	
	}
	
//For inserting record for Button Level Privilage
	public boolean newInsertRecord()
	{
		boolean retvalue = true;
		Connection conn = null;
		ResultSet rs3 = null;
		Statement stmt =  null;
		String query_fetchData=	"select to_char(sysdate,'dd-mon-yyyy') from dual";

	//system.out.println("query_fetchData----->"+query_fetchData);
	HisResultSet rs = null;
	String strDate = "";

	try
	{		
		rs = super.getRecord(query_fetchData,true);
		getDeletedMenuName();
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

	List lstOne = new ArrayList();////contains old selected menu
	List lstTwo = new ArrayList();//contains old unelected menu
	List lstThree = new ArrayList();////contains new selected menu
	List lstFour = new ArrayList();//contains new unselected menu
	List lstFive = new ArrayList();//contains deleted menu
	
	StringTokenizer stOne = new StringTokenizer(this.menuNameSelected,"^");
	while(stOne.hasMoreElements()){
		lstOne.add(stOne.nextToken().toString());}

	StringTokenizer stTwo = new StringTokenizer(this.menuNameUnSelected,"^");
	while(stTwo.hasMoreElements()){
		lstTwo.add(stTwo.nextToken().toString());}

	StringTokenizer stThree = new StringTokenizer(this.newMenuNameSelected,"^");
	while(stThree.hasMoreElements()){
		lstThree.add(stThree.nextToken().toString());}

	StringTokenizer stFour = new StringTokenizer(this.newMenuNameUnSelected,"^");
	while(stFour.hasMoreElements())	{
		lstFour.add(stFour.nextToken().toString());	}
	
	StringTokenizer stFive = new StringTokenizer(this.previousMenuList,"^");
	while(stFive.hasMoreElements())
	{
		lstFive.add(stFive.nextToken().toString());		

	}
System.out.println("lstFivelstFivelstFive"+lstFive);

	List recToInsert = new ArrayList();
	List recToDelete = new ArrayList();
	List recToModify = new ArrayList();
	List recToUpdate = new ArrayList();

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
	  recToInsert.add(lstTwo.get(i));
//	if(delFound){
//		System.out.println("delFound"+delFound);
//		recToUpdate.add(lstTwo.get(i));	}
	}
	//FINDING THE MENU IDS to be inserted and to be updated from deleted Menu ids/
	if(recToInsert!=null || recToInsert.size()!=0){
	for(i=0;i<lstFive.size();i++)
	{
		boolean bFlag = false;
		for(int k=0;k<recToInsert.size();k++)
		{
			if(lstFive.get(i).toString().equals(recToInsert.get(k).toString()))
			{
				bFlag = true;
				recToInsert.remove(k);
				break;
			}
		}
		if(bFlag)
		{
			
			System.out.println("rectoinsert"+recToInsert);
			recToUpdate.add(lstFive.get(i));
		}
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

// FINDING THE MENU IDS to be modified which was deleted previously
//	for( i=0;i<lstFive.size();i++)
//	{
//	
//	boolean found = false;
//	for(int t=0;t<lstThree.size();t++)			
//	{
//		if(lstFive.get(i).toString().equals(lstThree.get(t).toString()))
//		{
//			found = true;
//			break;
//		}
//		
//	}
//	
//	if(found)
//		{  recToModify.add(lstFive.get(i));	
//		   System.out.println("value to modify---->"+ lstFive.get(i));
//		}
//		
//	}
	
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
				strToDelete = recToDelete.get(i).toString();
				String deleteQuery = "";
			  deleteQuery = " UPDATE GBLT_BUTTON_PERMISSIONS "+
							"set GBL_ISVALID='0',GDT_EFFECT_TO=to_date('"+strDate+"','dd-mon-yyyy') "+
							"where GBL_ISVALID='"+this.isvalid+"' and " +
							" GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' and "+
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
					 String insertQuery = "";
					 
					strToInsert = recToInsert.get(i).toString();
				
				
			 
	insertQuery="insert into GBLT_BUTTON_PERMISSIONS "+
		"( GNUM_ROLE_ID, GNUM_MENU_ID, GNUM_ENTRY_SEAT_ID, GSTR_BUTTON_PERMISSION, GNUM_HOSPITAL_CODE," +
		" GDT_EFFECT_FRM,GNUM_SEAT_ID, GBL_ISVALID, GDT_ENTRY_DATE)"+
		" values ('"+this.role+"','"+strToInsert+"','"+this.seatId+"','"+this.buttonPerm+"','"+this.hospitalcode+"'," +
		"to_date('"+strDate+"','dd-mon-yyyy'),'"+this.seatId+"','"+this.isvalid+"',to_date('"+strDate+"','dd-mon-yyyy'))";

				System.out.println("insertQuery----->"+ insertQuery);
							stmt.addBatch( insertQuery);
							
						}
				}
			//For executing modify query
			if(recToModify.size()!=0)
			{
				for(i=0;i<recToModify.size();i++)
						{
					String strToInsert="";
					String updateQuery = "";
					int displayorder=0;
					
					strToInsert = recToModify.get(i).toString();				
			 
				updateQuery= " UPDATE GBLT_BUTTON_PERMISSIONS "+
				"set GSTR_BUTTON_PERMISSION='"+this.buttonPerm+"' "+
				"where GBL_ISVALID='"+this.isvalid+"' and " +
				" GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' and "+
				" GNUM_ROLE_ID='"+this.role+"'and " +					
				" GNUM_MENU_ID=("+strToInsert+")";
				System.out.println("updateQuery----->"+ updateQuery);
							stmt.addBatch( updateQuery);
							}
				}
			//For executing update 
			if(recToUpdate.size()!=0)
			{
				for(i=0;i<recToUpdate.size();i++)
						{
					String strToInsert="";
					String updateQuery = "";
					int displayorder=0;
					
					strToInsert = recToUpdate.get(i).toString();				
			 
				updateQuery= " UPDATE GBLT_BUTTON_PERMISSIONS "+
				"set GSTR_BUTTON_PERMISSION='"+this.buttonPerm+"',GBL_ISVALID='1' "+
				"where GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' and "+
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
			this.menuId_selected = t_menu;
		
			
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
				"and GNUM_ROLE_MENU_SLNO='"+t_menuSNo+"' and GNUM_hospitalcode='"+this.hospitalcode+"' "+
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
				
				
				
				this.roleName   = lst.get(1).toString();
				this.menuSelected	   = lst.get(2).toString();
				this.isvalid    = lst.get(4).toString();
				this.previousIsValid = lst.get(4).toString();
				this.menuId=lst.get(5).toString();
				
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in initizlize update mode "+ex);
		}
		
		
		return lst;
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
				"and  GNUM_hospitalcode='"+this.hospitalcode+"' ";
	
		
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
	
	
	
	
	
	
	
	

}
