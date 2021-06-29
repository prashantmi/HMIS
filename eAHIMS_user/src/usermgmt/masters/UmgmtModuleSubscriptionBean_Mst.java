/*
Added by Anshul vaid 
Process Name :Module Subscription
Dated: 16 july 2012.
Project  Ayush
*/
package usermgmt.masters;
import usermgmt.FuncLib;
import HisGlobal.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class UmgmtModuleSubscriptionBean_Mst extends FuncLib
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
	
	
	//Added by Anshul Vaid.
	private String strHospCombo = "";
	private String[] strFromDate;
	private String strToDate;
	private String strCurrentDate;
	private String strCheckedList;
	private String strUnCheckedList;
	private String[] strEffectFrom;
	private String[] strEffectTo;
	
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
	private String strSelectedHosp ="";
	
	private String strModuleId = "";
	
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
	
	//Function for getting the hospital name.
	
	public String getHospitalCombo()
	{
		System.out.println("hi---->");	
		String html = "";
		String query = "select GNUM_HOSPITAL_CODE,initcap(GSTR_HOSPITAL_NAME) as GSTR_HOSPITAL_NAME from GBLT_HOSPITAL_MST where GNUM_HOSPITAL_CODE!=100 and gnum_isvalid=1";
		
		System.out.println("queryModule---->"+query);		
		try
		{
			html = super.getCombo("",query,
					this.strHospCombo,"",0);
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in getHospitalCombo() "+ex);
		}
		return html;
	}
	public String getStrHospCombo() {
		return strHospCombo;
	}
	public void setStrHospCombo(String strHospCombo) {
		this.strHospCombo = strHospCombo;
	}
	
	
	//to get the listof modulespublic List initializeListOnLoad() throws Exception
	public String getListOfModules(HttpServletRequest request)
	{
		System.out.println("hospital code logged in user  "+this.hospital_code);
		System.out.println("hospital code in combo is  "+this.strSelectedHosp);
		ArrayList lstModules=new ArrayList();
		HttpSession session=request.getSession();
		String query="";
		String html = "";
		Calendar c = null;
		SimpleDateFormat dateFormat = null;
		int i=0;
		
if(!this.getStrSelectedHosp().equals(""))
{
            query =" select GNUM_HOSPITAL_CODE||'#'||GNUM_MODULE_ID||'@'||GSTR_IS_MANDATORY,GSTR_MODULE_NAME,GSTR_IS_MANDATORY,to_char(GDT_EFFECTIVE_FROM,'dd-Mon-YYYY')GDT_EFFECTIVE_FROM,to_char(GDT_EFFECTIVE_TO,'dd-Mon-YYYY')GDT_EFFECTIVE_TO,GNUM_HOSPITAL_CODE "+
							" from GBLT_METATABLE_TYPE_MST where GBL_ISVALID=1 and GNUM_HOSPITAL_CODE=100 "+
							" and GNUM_MODULE_ID not in  (select GNUM_MODULE_ID "+
							" from GBLT_METATABLE_TYPE_MST where GBL_ISVALID=1 and GNUM_HOSPITAL_CODE= "+ this.getStrSelectedHosp()+ ") "+  
							" union "+
							" select GNUM_HOSPITAL_CODE||'#'||GNUM_MODULE_ID||'@'||GSTR_IS_MANDATORY,GSTR_MODULE_NAME,GSTR_IS_MANDATORY,to_char(GDT_EFFECTIVE_FROM,'dd-Mon-YYYY')GDT_EFFECTIVE_FROM,to_char(GDT_EFFECTIVE_TO,'dd-Mon-YYYY')GDT_EFFECTIVE_TO,GNUM_HOSPITAL_CODE "+
							" from GBLT_METATABLE_TYPE_MST where GBL_ISVALID=1 and GNUM_HOSPITAL_CODE=" +this.getStrSelectedHosp()+ " order by GSTR_MODULE_NAME ";
}
else
{
	 query =" select GNUM_HOSPITAL_CODE||'#'||GNUM_MODULE_ID||'@'||GSTR_IS_MANDATORY,GSTR_MODULE_NAME,GSTR_IS_MANDATORY,to_char(GDT_EFFECTIVE_FROM,'dd-Mon-YYYY')GDT_EFFECTIVE_FROM,to_char(GDT_EFFECTIVE_TO,'dd-Mon-YYYY')GDT_EFFECTIVE_TO,GNUM_HOSPITAL_CODE "+
	" from GBLT_METATABLE_TYPE_MST where GBL_ISVALID=1 and GNUM_HOSPITAL_CODE=100 "+
	" and GNUM_MODULE_ID not in  (select GNUM_MODULE_ID "+
	" from GBLT_METATABLE_TYPE_MST where GBL_ISVALID=1 and GNUM_HOSPITAL_CODE= "+ this.hospital_code+ ") "+  
	" union "+
	" select GNUM_HOSPITAL_CODE||'#'||GNUM_MODULE_ID||'@'||GSTR_IS_MANDATORY,GSTR_MODULE_NAME,GSTR_IS_MANDATORY,to_char(GDT_EFFECTIVE_FROM,'dd-Mon-YYYY')GDT_EFFECTIVE_FROM,to_char(GDT_EFFECTIVE_TO,'dd-Mon-YYYY')GDT_EFFECTIVE_TO,GNUM_HOSPITAL_CODE "+
	" from GBLT_METATABLE_TYPE_MST where GBL_ISVALID=1 and GNUM_HOSPITAL_CODE=" +this.hospital_code+ " order by GSTR_MODULE_NAME ";
}
			HisResultSet rs = null;
			
			
			System.out.println("getListOfModules query---->"+query);
			try
			{
				String defFormat = "dd/MM/yyyy";
				c = Calendar.getInstance();
				dateFormat = new SimpleDateFormat(defFormat);
				String dtStr = dateFormat.format(c.getTime());
				this.strCurrentDate=dtStr;
				System.out.println("Current date:"+this.strCurrentDate);
				rs = super.getRecord(query,true);
			
					
				
				
				String strModuleName = "";
				String strIsMandatory = "";
				String strFromDate = "";
				String strToDate = "";
				String strRSHosCode="";
				String strIsChecked="";
				
				String effectFromdate="";
				String effectTodate="";
				String effect_Fromdate="";
				String effect_Todate="";
				String checkBoxName="";
				
				
				
				String tempRoleId[]=null;
			if(rs!=null)
			{	i=1;
				
				while(rs.next())
				{
					
					
					strIsMandatory=""		;
					effectFromdate="unSelectedFromDate"+i;
					effectTodate="unSelectedToDate"+i;
					effect_Fromdate="unSelected_FromDate"+i;
					effect_Todate="unSelected_ToDate"+i;
					checkBoxName="unSelectedCheckBox"+i;
					
					
					strModuleId = rs.getString(1);
					strModuleName = rs.getString(2);
					strIsMandatory = rs.getString(3);
					strFromDate = rs.getString(4);
					strToDate=rs.getString(5);
					strRSHosCode=rs.getString(6);
				
					
					html+="<tr>";		
					html+="<td class='adddatalabel' width='20%'>"+strModuleName+"</td>";
					if(strIsMandatory.matches("1"))
					{
						strIsChecked="checked";
					html+="<td class='adddatalabel' width='10%'><input type=\"checkbox\" name =\"strCheckbox\" id=\""+strModuleId+"\" onchange=\"checkIfmandatory(this);\" value=\""+strModuleId+" \"checked=\" "+strIsChecked+"\"></td>";              
					}
					else if(!strRSHosCode.equalsIgnoreCase("100"))
					{
						strIsChecked="checked";
						html+="<td class='adddatalabel' width='10%'><input type=\"checkbox\" name =\"strCheckbox\" id=\""+strModuleId+"\" onchange=\"checkIfmandatory(this);\" value=\""+strModuleId+" \"checked=\" "+strIsChecked+"\"></td>";
					}
					else
					{
						html+="<td class='adddatalabel' width='10%'><input type=\"checkbox\" name =\"strCheckbox\" id=\""+strModuleId+"\" onchange=\"checkIfmandatory(this);\" value=\""+strModuleId+"\"></td>";   
					}


					html+="<td class='adddatalabel' width='20%'><input type=\"text\" name=\"strEffectFrom\" id=\""+effectFromdate+"\" readonly value='"+strFromDate+"'> 	<img src=\"../../images/iconPicDate.gif\" id=\""+effect_Fromdate+"\" style=\"cursor: pointer; border: 1px solid red;\"  	title=\"Date selector\"  	onmouseover=\"this.style.background='red';\"  	 onmouseout=\"this.style.background=''\"> 	<script language=\"JavaScript\" > Calendar.setup( { inputField     :    \""+effectFromdate+"\", ifFormat       :    \"%d-%b-%Y\", button         :    \""+effect_Fromdate+"\", singleClick    :    true });	</script></td>";
					html+="<td class='adddatalabel' width='20%'><input type=\"text\" name=\"strEffectTo\" id=\""+effectTodate+"\" readonly value='"+strToDate+"'> 	<img src=\"../../images/iconPicDate.gif\" id=\""+effect_Todate+"\" style=\"cursor: pointer; border: 1px solid red;\"  	title=\"Date selector\"  	onmouseover=\"this.style.background='red';\"  	 onmouseout=\"this.style.background=''\"> 	<script language=\"JavaScript\" > Calendar.setup( { inputField     :    \""+effectTodate+"\", ifFormat       :    \"%d-%b-%Y\", button         :    \""+effect_Todate+"\", singleClick    :    true });	</script></td>";
					html+="</tr>";
										
										i++;


										lstModules.add(strModuleId);	
                  
				}
				
				session.setAttribute("old_list", lstModules);
				
			  }	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in getFirstSeatNames "+e);
			}
			


	
		
	
		
		return html;	
		
	}
	
	
	
	
	
	public boolean insertRecord(HttpServletRequest req)
	{
		
	
		String seatid="";
	//	int it=0;
	   seatid=this.getSeatId();
	
	
	   Connection conn = null;
		Statement stmt=null;
		conn = super.getConnection();	
	
		HisResultSet rsUnChk = null;
		
		
		String strHospitalCode="";
		strHospitalCode=this.strHospCombo;
		
		
		System.out.println("the Hospital Code is  ----------------"+strHospCombo);
		String strInsertQuery="";
		String strUpdateQuery="";
		String strUpdateUnChkQuery="";
		boolean retVal = true;
		
		ResultSet rs3 = null;
		PreparedStatement ps =  null;
		PreparedStatement ps1 =  null;
		PreparedStatement ps2=  null;
		PreparedStatement ps3=  null;
		
		List lstOldModules=new ArrayList();
		List lstNewModules=new ArrayList();
		List effectDate=new ArrayList();
		List effectTo=new ArrayList();
	
		 try
		 {
		HttpSession session=req.getSession();
		lstOldModules=(List)session.getAttribute("old_list");
		conn.setAutoCommit(false);	
		stmt= conn.createStatement();
		
		
		String Test=lstOldModules.toString();
	
		
		String[] strAllModulesIds=Test.replace("^", ",").split(",");
	
		System.out.println("test values are+++++++++"+Test);
		
		
		
		System.out.println("selectedValues+++++++++"+lstOldModules);
		
		
		System.out.println("Checked List+++++++++"+this.getStrCheckedList());
		
		System.out.println("Un Checked List+++++++++"+this.getStrUnCheckedList());
		
		String[] strModuleIds= getStrCheckedList().replace("^", ",").split(",");
		System.out.println("List+++++++++"+strModuleIds);	
		for (int _iLen=0;_iLen<strModuleIds.length;_iLen++)
		{				
	
			
			String[] strString=strModuleIds[_iLen].split("#");
			String strHospCode=strString[0].replace("^", ""); 
			System.out.println("strHospCode+++++++++"+strHospCode);
			String[] strModuleIdWithDate=strString[1].split("@");
			System.out.println("With Date+++++++++"+strModuleIdWithDate);
			String strModuleId=strModuleIdWithDate[0];
			System.out.println("strModuleId+++++++++"+strModuleId);
			

			String strPosition=strModuleIdWithDate[2] ;
			int _iPos=Integer.parseInt(strPosition);
			System.out.println("Position+++++++++"+strPosition);
			
			String strIsMandatory=strModuleIdWithDate[1].trim() ;
			int _iMandatory=Integer.parseInt(strIsMandatory);
			System.out.println("Position+++++++++"+_iMandatory);
			
			
			
			String strFromDateNew1=this.getStrEffectFrom()[_iPos];
			String strToDateNew1=this.getStrEffectTo()[_iPos];
			System.out.println("From date now is +++++++++"+strFromDateNew1);
			System.out.println("To date now is +++++++++"+strToDateNew1);
			
			if(strHospCode.equals("100"))
			{
				//New entry in the table
				strInsertQuery="insert into GBLT_METATABLE_TYPE_MST"+
                                " (GNUM_MODULE_ID, GSTR_MODULE_NAME,GBL_ISVALID,GNUM_SEAT_ID,GDT_ENTRY_DATE,"+			
                                " GNUM_HOSPITAL_CODE,GDT_EFFECTIVE_FROM,GDT_EFFECTIVE_TO,GSTR_IS_MANDATORY, "+
                               " GSTR_PARENT_MODULE,GNUM_MAX_USER_LICENSES)values ("+strModuleId+","+"(select GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID="+strModuleId+ " and GBL_ISVALID=1 and GNUM_HOSPITAL_CODE=100),1,"+seatid+",sysdate,"+strHospitalCode+",to_date('"+strFromDateNew1+"','dd-Mon-YYYY'),to_date('"+strToDateNew1+"','dd-Mon-YYYY'),"+_iMandatory+"::numeric,0,(select GNUM_USR_LICENCE_ALLOWED from  GBLT_HOSPITAL_MST where GNUM_HOSPITAL_CODE="+strHospitalCode+ "))";

                               System.out.println("Insert Query+++++++++"+strInsertQuery);
                               
                               stmt.addBatch(strInsertQuery);                
                               



			}
			else
			{//updating the query
				strUpdateQuery="update GBLT_METATABLE_TYPE_MST set GBL_ISVALID =1,GDT_EFFECTIVE_FROM=to_date('"+strFromDateNew1+"','dd-Mon-YYYY'),"+
								" GDT_EFFECTIVE_TO=to_date('"+strToDateNew1+"','dd-Mon-YYYY') where GNUM_MODULE_ID= "+strModuleId +"  and GNUM_HOSPITAL_CODE ="+strHospitalCode;

				
			      System.out.println("Update Query+++++++++"+strUpdateQuery);
			      stmt.addBatch(strUpdateQuery);   
				
			}
			}
		
		
			//for unchecked list...........

			String[] strUnCheckedModuleIds= getStrUnCheckedList().replace("^", ",").split(",");
			
			for (int _iUnChkLen=0;_iUnChkLen<strUnCheckedModuleIds.length;_iUnChkLen++)
			{
				
				String[] strUnChkString=strUnCheckedModuleIds[_iUnChkLen].split("#");
				String strUnChkHospCode=strUnChkString[0].replace("^", "");
				
				System.out.println("strHospCode+++++++++"+strUnChkHospCode);
				
					
		
			
				String[] strUnChkModuleIdWithDate=strUnChkString[1].split("@");
				System.out.println("With Date+++++++++"+strUnChkModuleIdWithDate);
				String strUnChkModuleId=strUnChkModuleIdWithDate[0];
				System.out.println("strModuleId+++++++++"+strUnChkModuleId);
				
				String strPosition=strUnChkModuleIdWithDate[2] ;
				int _iPos=Integer.parseInt(strPosition);
				System.out.println("Position+++++++++"+strPosition);
				
				String strUnChkFromDateNew=this.getStrEffectFrom()[_iPos];
				String strUnChkToDateNew=this.getStrEffectTo()[_iPos];
				System.out.println("From date now is +++++++++"+strUnChkFromDateNew);
				System.out.println("To date now is +++++++++"+strUnChkToDateNew);
				
				
				System.out.println("From Date+++++++++"+strUnChkFromDateNew);
				System.out.println("From Date+++++++++"+strUnChkToDateNew);
				
				
										strUpdateUnChkQuery="update GBLT_METATABLE_TYPE_MST set GBL_ISVALID =0,GDT_EFFECTIVE_FROM= to_date('"+strUnChkFromDateNew+"','dd-Mon-YYYY'),"+
						" GDT_EFFECTIVE_TO= to_date('"+strUnChkToDateNew+"','dd-Mon-YYYY') where GNUM_MODULE_ID= "+strUnChkModuleId +"  and GNUM_HOSPITAL_CODE ="+strHospitalCode;

		
	                  System.out.println("Update Query+++++++++"+strUpdateUnChkQuery);
	                    stmt.addBatch(strUpdateUnChkQuery);   
					}
					
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			this.status = "Record Successfully Inserted !";
		
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
			this.status = "Error while Inserting : "+e;
		}
		finally
		{
			try
			{
				
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
		
		
		
		return retVal;
		
		
	}
	

	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrSelectedHosp() {
		return strSelectedHosp;
	}
	public void setStrSelectedHosp(String strSelectedHosp) {
		this.strSelectedHosp = strSelectedHosp;
	}
	public String getStrCheckedList() {
		return strCheckedList;
	}
	public void setStrCheckedList(String strCheckedList) {
		this.strCheckedList = strCheckedList;
	}
	public String getStrUnCheckedList() {
		return strUnCheckedList;
	}
	public void setStrUnCheckedList(String strUnCheckedList) {
		this.strUnCheckedList = strUnCheckedList;
	}
	public void setStrFromDate(String[] strFromDate) {
		this.strFromDate = strFromDate;
	}
	public String[] getStrFromDate() {
		return strFromDate;
	}
	public String[] getStrEffectFrom() {
		return strEffectFrom;
	}
	public void setStrEffectFrom(String[] strEffectFrom) {
		this.strEffectFrom = strEffectFrom;
	}
	public String[] getStrEffectTo() {
		return strEffectTo;
	}
	public void setStrEffectTo(String[] strEffectTo) {
		this.strEffectTo = strEffectTo;
	}

	
}