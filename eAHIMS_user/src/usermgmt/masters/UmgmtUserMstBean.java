

package usermgmt.masters;
import usermgmt.*;
import java.sql.*;
import java.util.*;
import HisGlobal.*;

import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import login.*;
public class UmgmtUserMstBean extends FuncLib
{
	private String designation		=	"";
	private String emp				=	"";
	private String emp_new				=	"";
	private String userId			=	"";
	private String combo1			=	"";
    boolean retValue=false;
	private String 	status			=	"";
	private String userName			=	"";
	private String userPwd			=	"";
	private String conPwd			=	"";
	private String empNo			=	"";
	private String userSeatNo		=	"";
	private String effDate			=	""; 
	private String expDate			=	"";
	private String userLevel		=	"";
	private String statusCode		=	"1";
	private String isValid			=	"";
	private String entryDate		=	"";
	private String oldPwd			=	"";
	private String seatId			=	"";
	private String hintQuet     	=   "";
	private String ufullName    	=  	"";
	private String[] chk;
	private String mobileNo			=	"";
	private String emailId			=	"";
	private String groupNo			=	"";
	private String smartCardNo		=	"";
    private String hospitalCode=	"";  
	private String oldUserSeatNo=	"";
	private String userIdUserSeatDtl=	"";
	private String slNoUserSeatDtl=	"";
	private String[] menuIdArray;
	private String myService="0";
	private String hmode="";
	private String menuIdCollection="";;
	private String menuNameCollection="";
	private String menuIdSelected="";
	private String menuIdArrayCheck="";
	private String EmpIdNew="";
	private String hospitalName      ="";
	private String prevHospitalCode = "";
	private String scanUser="1";
	
	
	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSmartCardNo() {
		return smartCardNo;
	}

	public void setSmartCardNo(String smartCardNo) {
		this.smartCardNo = smartCardNo;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDesignation()
	{
		return this.designation;
	}
	
	public void setDesignation(String designation )
	{
		this.designation = designation;
	}
	
	public String getSeatId()
	{
		return this.seatId;
	}

	public String getUserName()
	{
		return this.userName;
	}
	public String getUserPwd()
	{
		return ((userPwd==null)? "" : userPwd);
	}
	public String getOldPwd()
	{
		return  oldPwd;
	}
	public String getEmpNo()
	{
		return this.empNo;
	}
	public String getUserSeatNo()
	{
		return this.userSeatNo;
	}
	public String getEffDate()
	{
		return this.effDate;
	}
	public String getExpDate()
	{
		return this.expDate;
	}
	public String getStatus()
	{
		return status;
	}
	public String getUserLevel()
	{
		return userLevel;
	}
	public void setEmp(String emp)
	{
		this.emp=emp;
	}
	public String getEmp()
	{
		return emp;
	}
	public void setUfullName(String ufullName)
	{
		this.ufullName=ufullName;
	}
	
	public String getUfullName()
	{
		return ufullName;
	}
	
	public void setUserId( String userId )
	{
		this.userId = userId;
	}

	public java.lang.String getUserId()
	{
		return userId;
	}
	
	public void setIsValid( String isValid ) 
	{
	    this.isValid = isValid;
	}

	public java.lang.String getIsValid( ) 
	{
	    return isValid;
	}

	public void setHintQuet( java.lang.String hintQuet ) 
	{
	    this.hintQuet = hintQuet;
	}

	public java.lang.String getHintQuet( ) 
	{
	    return hintQuet;
	}

	public void setCombo1( java.lang.String combo1 ) 
	{
	    this.combo1 = combo1;
	}

	public java.lang.String getCombo1( ) 
	{
	    return combo1;
	}
	
	public void setChk(String[] chk0)
	{
		chk = chk0;
	}

	public void setSeatId(String seatId)
	{
		this.seatId=seatId;
	}
	public void setUserName(String name)
	{
		this.userName	=	name;
	}
	public void setUserPwd(String pwd)
	{
		this.userPwd	=	pwd;
	}
	public void setOldPwd(String pwd)
	{
		this.oldPwd	=	pwd;
	}
	public void setEmpNo(String empNo)
	{
		this.empNo	=	empNo;
	}
	public void setUserSeatNo(String userSeatNo)
	{
		this.userSeatNo	=	userSeatNo;
	}
	
	public void setEffDate(String effDate)
	{
		this.effDate	=	effDate;
	}
	
	public void setExpDate(String expDate)
	{
		this.expDate	=	expDate;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	public void setUserLevel(String userLevel)
	{
		this.userLevel = userLevel;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getOldUserSeatNo() {
		return oldUserSeatNo;
	}

	public void setOldUserSeatNo(String oldUserSeatNo) {
		this.oldUserSeatNo = oldUserSeatNo;
	}

	public String getUserIdUserSeatDtl() {
		return userIdUserSeatDtl;
	}

	public void setUserIdUserSeatDtl(String userIdUserSeatDtl) {
		this.userIdUserSeatDtl = userIdUserSeatDtl;
	}

	public String getSlNoUserSeatDtl() {
		return slNoUserSeatDtl;
	}

	public void setSlNoUserSeatDtl(String slNoUserSeatDtl) {
		this.slNoUserSeatDtl = slNoUserSeatDtl;
	}

	public String[] getMenuIdArray() {
		return menuIdArray;
	}

	public void setMenuIdArray(String[] menuIdArray) {
		this.menuIdArray = menuIdArray;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getMyService() {
		return myService;
	}

	public void setMyService(String myService) {
		this.myService = myService;
	}

	public String getMenuIdCollection() {
		return menuIdCollection;
	}

	public void setMenuIdCollection(String menuIdCollection) {
		this.menuIdCollection = menuIdCollection;
	}
	public String getMenuNameCollection() {
		return menuNameCollection;
	}

	public void setMenuNameCollection(String menuNameCollection) {
		this.menuNameCollection = menuNameCollection;
	}
	public String getMenuIdSelected() {
		return menuIdSelected;
	}

	public void setMenuIdSelected(String menuIdSelected) {
		this.menuIdSelected = menuIdSelected;
	}

	public String getConPwd() {
		return conPwd;
	}

	public void setConPwd(String conPwd) {
		this.conPwd = conPwd;
	}
	
	
	
	public String getEmp_new() {
		return emp_new;
	}

	public void setEmp_new(String emp_new) {
		this.emp_new = emp_new;
	}
	public String getMenuIdArrayCheck() {
		return menuIdArrayCheck;
	}

	public void setMenuIdArrayCheck(String menuIdArrayCheck) {
		this.menuIdArrayCheck = menuIdArrayCheck;
	}

	
	public String getEmpIdNew() {
		return EmpIdNew;
	}

	public void setEmpIdNew(String empIdNew) {
		EmpIdNew = empIdNew;
	}
	
	public String getPrevHospitalCode() {
		return prevHospitalCode;
	}

	public void setPrevHospitalCode(String prevHospitalCode) {
		this.prevHospitalCode = prevHospitalCode;
	}

	//getters and setters completed
	
	public String hospitalNameOptions()
	{
		String options	=	"";
		String query 	=	"";
	
		query	=	"	SELECT  distinct GNUM_HOSPITAL_CODE, initcap(GSTR_HOSPITAL_NAME) from GBLT_HOSPITAL_MST"+
					" where  GNUM_ISVALID ='1' AND GNUM_HOSPITAL_CODE!='"+this.hospitalCode+"' AND "+
					" GNUM_HOSPITAL_CODE!='100' order by initcap(GSTR_HOSPITAL_NAME)  ";
	try
		{
			options= populateCombo(query,hospitalCode);
			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		return options;
	}
	
	public void hospitalName()
	{
		String hospitalName	=	"";
		String query 	=	"";
	
		query	=	"	SELECT  distinct initcap(GSTR_HOSPITAL_NAME) from GBLT_HOSPITAL_MST"+
					" where  GNUM_ISVALID ='1' AND GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' order by initcap(GSTR_HOSPITAL_NAME)  ";
		
		try{
			HisResultSet rst = getRecord(query);
			if(rst.next())
			{
				this.hospitalName=rst.getString(1);
				//hospitalName=rst.getString(1);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		
//		return hospitalName;
	}
	
	
	
	// used for calling groups from GBLT_GROUP_MST
	public String groupOptions()
	{
		String options	=	"";
		String query 	=	"";
	
		query	=	"	SELECT   GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST"+
					" where  GNUM_ISVALID ='1' AND GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and  TRUNC(GDT_EFFECT_DATE)<=TRUNC(SYSDATE) order by initcap(GSTR_GROUP_NAME)  ";
	try
		{
			options= populateCombo(query,groupNo);
			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		return options;
	}
	
	// call onchange of group.
	public String seatOptions()
	{
		String options	=	"";
		String query 	=	"";
		String groupNo ="";
		
		if(this.getGroupNo().equals("")|| this.getGroupNo().equals(null))
			groupNo="0";
		else
			groupNo=this.getGroupNo();
		
		query	=	"	SELECT  A.GNUM_SEATID,INITCAP( A.GSTR_SEAT_NAME) FROM GBLT_SEAT_MST A "+
		            "	WHERE  GNUM_ISVALID='1' and GNUM_GROUP_CODE='"+groupNo+"' AND GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and TRUNC(GDT_EFFECT_DATE)<=TRUNC(SYSDATE) order by initcap(a.gstr_seat_name) ";


		System.out.println(query);
		try
		{
			options= populateCombo(query,userSeatNo);
			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		return options;
	}

	
	public void initializeNewMode()
	{	
		empNo		=	"";
		emp			=   "";
		userName	=	"";
		userPwd  	=	"";
		conPwd  	=	"";
		userSeatNo	=   "";
		hintQuet	=	"";
		effDate		=	"";
 		expDate    	=	"";
 		designation	=	"";
 		ufullName	=	"";
 		smartCardNo =   "";
 		mobileNo	=	"";
 	    emailId	    =	"";
 	    userId		=	"";
 	    scanUser="1";
 	    
	}
	
/*	public String desigCombo()
	{
		String combo="";
	    try
	    {
	    	String query="SELECT GNUM_DESIG_CODE,GSTR_DESIG_NAME from GBLT_DESIGNATION_MST WHERE GNUM_ISVALID='1'";
	    	combo = populateCombo(query,desigCode);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("EXCEPTION IS"+e);
	    }
	    	return combo;
	   }*/
	//for automatic generation of UserId
	public String UserId()
	{
		String id	=	"";
		int seatID	=	0;
		String query	=	"";
		try
		{
			 query =	" SELECT nvl(max(GNUM_USERID)+1,10001) "+
						" FROM GBLT_USER_MST ";
						// " WHERE GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'  ";

			 HisResultSet rs = getRecord(query);
			 if(!rs.next())
			{
				seatID	=	 10001; //if their is no record in table than first Record will be 1001
			}
			else
			{
				//rs.last();
				id	=	rs.getString(1);
				seatID	=	Integer.parseInt(id);
				//seatID	+=	1; // every time Records add userId increas by 1.  
			}
		}
		catch(Exception e)
		{
			System.out.println("Error while fetching records in Popup Window"+e);
		}
		return ""+seatID;
	}
	public String CheckValue()
	{
		String checkQuery="";
		/*checkQuery=" select distinct a.GNUM_MENU_ID "+
	               " from GBLT_MYSERVICES_USERMENU_MST a where a.GNUM_USERID='"+this.userId+"'  "+
	                "and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and GNUM_ISVALID='1' ";
		*/
		
		checkQuery=" select distinct a.GNUM_MENU_ID "+
        " from GBLT_MYSERVICES_USERMENU_MST a where a.GNUM_USERID='"+this.userId+"'  "+
         "and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and GNUM_ISVALID='1' ";
		System.out.println("checkQuery-------------->"+checkQuery);
		String concateMenuIds="";
	
		String query	=	"";
		try
		{
		     if(!this.userId.equals(""))
		     {
			 HisResultSet rs = getRecord(checkQuery);
			 while(rs.next())
			 {
				
				 concateMenuIds	+=	rs.getString(1)+"^";
			 }
		     }
		}
		catch(Exception e)
		{
			System.out.println("Error while fetching records in Popup Window"+e);
		}
		return concateMenuIds;
	}
	
	
	public 	void Popup(HttpServletRequest request) throws Exception
	{
			HttpSession session=request.getSession();
			String id="";
			String query="";
			String concateMenuId="";
			String concateMenuName="";
			
			try
			{
				//query=   "SELECT GNUM_MENU_ID, GSTR_MENU_NAME FROM GBLT_MYSERVICES_MENU_MST"+
                        // " WHERE GNUM_ISVALID='1' AND GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'";
				
				query=   "SELECT GNUM_MENU_ID, GSTR_MENU_NAME FROM GBLT_MYSERVICES_MENU_MST"+
                " WHERE GNUM_ISVALID='1'";
				
				 System.out.println("Query My Services"+query);
				 HisResultSet rs = getRecord(query);
				
				 
				
					 while(rs.next())
					 { concateMenuId +=rs.getString(1)+"^";
					   concateMenuName +=rs.getString(2)+"^";
					 }
				 
					 this.menuIdCollection=concateMenuId ;
					 this.menuNameCollection=concateMenuName ;
					 request.getSession().setAttribute("MENU_IDS", this.menuIdCollection);
					 request.getSession().setAttribute("MENU_NAMES", this.menuNameCollection);
				 
					 System.out.println("MenuId.............."+this.menuIdCollection); 
					 System.out.println("MenuName.............."+this.menuNameCollection);
				 

									 
			}catch(Exception e)
			{
	    System.out.println("Error in getting and concating menuid's......."+e); 
			}
			
			
	}
		
	
	
	// takes selected values from table to modify page.
	public List initializeUpdateMode()throws Exception
	{
		String query = new String("");
		String query1 =  new String("");
		String seatId =  "";
		List AL_List = new ArrayList();
		List AL_SEAT_LIST = new ArrayList();

		this.userId = chk[0].substring(0,chk[0].length()-1);
		this.prevHospitalCode= this.hospitalCode;
		System.out.println("==================="+this.prevHospitalCode);;
		
		query1  =   "SELECT GNUM_USER_SEATID FROM GBLT_USER_MST WHERE GNUM_USERID = '"+this.userId+" 'AND GNUM_ISVALID = '"+combo1+" ' AND GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' ";
		
		System.out.println("query1------->"+query1);
		try
		{
			AL_SEAT_LIST = getDetail(query1);
			
		}
		catch(Exception e)
		{
			System.out.println("Error in getting seat =  "+e);
		}
		seatId =(String)AL_SEAT_LIST.get(0);
		if(seatId.equals("0")||seatId.equals(""))
		{
		
			query 	=	"	SELECT  A.PSRSTR_EMP_NO," +
            "   GNUM_SWAPCARD_NUMBER," +
            "   GSTR_USER_NAME," +
			    "	GNUM_USERLEVEL," +
				"   TO_CHAR(A.GDT_EFFECT_DATE,'DD-Mon-YYYY')," +
			"   TO_CHAR(A.GDT_EXPIRY_DATE,'DD-Mon-YYYY')," +
			" 	GNUM_DESIGNATION," +
			"	GSTR_USR_NAME," +
			//"	GSTR_PASSWORD,"+
           // "   GNUM_MOBILE_NUMBER,"+
            "   DECODE(GNUM_MOBILE_NUMBER,0,'',GNUM_MOBILE_NUMBER),"+
            "   GSTR_EMAIL_ID,"+
            "   GNUM_USER_TYPE ,"+
             "   (SELECT ghm.GSTR_HOSPITAL_NAME" +
				"	FROM    GBLT_HOSPITAL_MST ghm " +
				"   WHERE ghm.GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "+
				"   AND GNUM_ISVALID = 1 )gstr_hospital_name  "+
				 ",GSTR_STATUS_CODE"+
			" 	FROM GBLT_USER_MST A" +
			" 	WHERE  A.GNUM_ISVALID = '"+combo1+"'  and "+
			"   A.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND "+
			"   A.GNUM_USERID = '"+this.userId+"' ";
            
			System.out.println("Query in InitilizeUpdate function =  "+query);
			try
			{
				AL_List = getDetail(query);
				if((AL_List.get(0)==null)||(AL_List.get(0).equals("")))
					AL_List.set(0,"Non-Employee");
				//	else if(!(AL_List.get(0)==null)||!(AL_List.get(0).equals("")))
				//	AL_List.set(0,"Employee");
				if((AL_List.get(6)==null)||(AL_List.get(6).equals("")))
					AL_List.set(6,"No Designation");
			}
			catch(Exception e)
			{
				System.out.println("Error in InitilizeUpdate function =  "+e);
			}

			hospitalName =  (String)AL_List.get(11);
			empNo		=	(String)AL_List.get(0);
			smartCardNo     =(String)AL_List.get(1);
			userName	    =	(String)AL_List.get(2);
			userLevel	=	(String)AL_List.get(3);
			//groupNo	    =	(String)AL_List.get(4);
			//userSeatNo	=	(String)AL_List.get(5);
			effDate		=	(String)AL_List.get(4);
			expDate		=	(String)AL_List.get(5);
			designation	=	(String)AL_List.get(6);
			ufullName	=	(String)AL_List.get(7);
			mobileNo        =(String)AL_List.get(8);
			emailId         =(String)AL_List.get(9);
			emp         =(String)AL_List.get(10);
			scanUser=   (String)AL_List.get(12);
	       
		}
		else
		{
			query 	=	"	SELECT  A.PSRSTR_EMP_NO," +
            "   GNUM_SWAPCARD_NUMBER," +
            "   GSTR_USER_NAME," +
			    "	GNUM_USERLEVEL," +
				//"   GNUM_QUESTION_ID,"+	
			    "	INITCAP(C.GNUM_GROUP_CODE)," +
				"	INITCAP(B.GNUM_SEATID)," +
				"   TO_CHAR(A.GDT_EFFECT_DATE,'DD-Mon-YYYY')," +
			"   TO_CHAR(A.GDT_EXPIRY_DATE,'DD-Mon-YYYY')," +
			" 	GNUM_DESIGNATION," +
			"	GSTR_USR_NAME," +
			//"	GSTR_PASSWORD,"+
            //"   GNUM_MOBILE_NUMBER,"+
            "   DECODE(GNUM_MOBILE_NUMBER,0,'',GNUM_MOBILE_NUMBER),"+
            "   GSTR_EMAIL_ID,"+
            "   GNUM_USER_TYPE ,"+
            "   (SELECT ghm.GSTR_HOSPITAL_NAME" +
				"	FROM    GBLT_HOSPITAL_MST ghm " +
				"   WHERE ghm.GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "+
				"   AND GNUM_ISVALID = 1 )gstr_hospital_name  "+
				 ",GSTR_STATUS_CODE"+
			" 	FROM GBLT_USER_MST A," +
			"   GBLT_SEAT_MST B," +
			"   GBLT_GROUP_MST C" +
			" 	WHERE  A.GNUM_ISVALID = '"+combo1+"'  and "+
			"   A.GNUM_USER_SEATID = B.GNUM_SEATID AND "+
			"   B.GNUM_GROUP_CODE=C.GNUM_GROUP_CODE and "+
			"   A.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND "+
			"   b.GNUM_HOSPITAL_CODE=a.gnum_hospital_code and  "+
            "   c.gnum_hospital_code=a.gnum_hospital_code and "+
			"   A.GNUM_USERID = '"+this.userId+"' ";
            
			System.out.println("Query in InitilizeUpdate function else =  "+query);
			try
			{
				AL_List = getDetail(query);
				if((AL_List.get(0)==null)||(AL_List.get(0).equals("")))
					AL_List.set(0,"Non-Employee");
				//else if(!(AL_List.get(0)==null)||!(AL_List.get(0).equals("")))
				//	AL_List.set(0,"Employee");
				if((AL_List.get(7)==null)||(AL_List.get(7).equals("")))
					AL_List.set(7,"No Designation");
			}
			catch(Exception e)
			{
				System.out.println("Error in InitilizeUpdate function =  "+e);
			}
			
			hospitalName =  (String)AL_List.get(13);
			empNo		=	(String)AL_List.get(0);
			smartCardNo     =(String)AL_List.get(1);
			userName	    =	(String)AL_List.get(2);
			userLevel	=	(String)AL_List.get(3);
			groupNo	    =	(String)AL_List.get(4);
			userSeatNo	=	(String)AL_List.get(5);
			effDate		=	(String)AL_List.get(6);
			expDate		=	(String)AL_List.get(7);
			//	hintQuet	=	(String)AL_List.get(8);
			designation	=	(String)AL_List.get(8);
			ufullName	=	(String)AL_List.get(9);
			//oldPwd		= 	SecurityUtil.decrypt((String)AL_List.get(8));
			//oldPwd		=	(String)AL_List.get(9);
			mobileNo        =(String)AL_List.get(10);
			emailId         =(String)AL_List.get(11);
			emp         =(String)AL_List.get(12);
			scanUser=   (String)AL_List.get(14);
		}
		 return AL_List;
	}
	
	
	 // takes selected values from table to View page.
	public List initializeUpdateModeView()throws Exception
	{
		String query = new String("");
		String query1 = new String("");
		List AL_List = new ArrayList();
		List AL_Seat_List = new ArrayList();
		

		this.userId = chk[0].substring(0,chk[0].length()-1);

	/*	old query changed by ankur 
	 * 
	 * query 	=	"	SELECT  A.PSRSTR_EMP_NO," +
		         
		            "   GSTR_USER_NAME," +
 				    "	GNUM_USERLEVEL," +
 					//"   GNUM_QUESTION_ID,"+	
 				    "	INITCAP(C.GSTR_GROUP_NAME)," +
 					"	INITCAP(B.GSTR_SEAT_NAME)," +
 					"   TO_CHAR(A.GDT_EFFECT_DATE,'DD-Mon-YYYY')," +
					"   TO_CHAR(A.GDT_EXPIRY_DATE,'DD-Mon-YYYY')," +
					
					" 	GNUM_DESIGNATION," +
					"	GSTR_USR_NAME," +
					
					//"	GSTR_PASSWORD,"+
					
		            "   GNUM_MOBILE_NUMBER,"+
		            "   GSTR_EMAIL_ID,"+
		            "   GNUM_SWAPCARD_NUMBER" +
		            
					" 	FROM GBLT_USER_MST A," +
					"   GBLT_SEAT_MST B," +
					"   GBLT_GROUP_MST C" +
					" 	WHERE  A.GNUM_ISVALID = '"+combo1+"' AND "+
					"   A.GNUM_USER_SEATID = B.GNUM_SEATID AND "+
					"   B.GNUM_GROUP_CODE=C.GNUM_GROUP_CODE and "+
					"   A.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND "+
					"   b.gnum_hospital_code = a.gnum_hospital_code  and "+
					"   c.gnum_hospital_code = a.gnum_hospital_code and  "+
					"   A.GNUM_USERID = '"+this.userId+"' ";
					*/
		
//	query1  =   "SELECT GNUM_USER_SEATID FROM GBLT_USER_MST WHERE GNUM_USERID = '"+this.userId+" 'AND GNUM_ISVALID = '"+combo1+" ' AND GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' ";
//		
//		System.out.println("query1------->"+query1);
//		try
//		{
//			AL_Seat_List = getDetail(query1);
//			
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error in getting seat =  "+e);
//		}
//		seatId =(String)AL_Seat_List.get(0);
//		if(seatId.equals("0"))
//		{
//			query 	=	"	SELECT (SELECT ghm.GSTR_HOSPITAL_NAME" +
// 			"	FROM    GBLT_HOSPITAL_MST ghm " +
// 			"   WHERE ghm.GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "+
// 			"   AND GNUM_ISVALID = 1 )gstr_hospital_name , "+
// 			"  A.PSRSTR_EMP_NO," +   
// 			"  A.GSTR_USER_NAME," +
// 			"  A.GNUM_USERLEVEL," +
//			"   TO_CHAR(A.GDT_EFFECT_DATE,'DD-Mon-YYYY')," +
//			"   TO_CHAR(A.GDT_EXPIRY_DATE,'DD-Mon-YYYY')," +
//			" 	A.GNUM_DESIGNATION," +
//			"	A.GSTR_USR_NAME," +
//			"   A.GNUM_MOBILE_NUMBER,"+
//            "   A.GSTR_EMAIL_ID,"+
//            "   A.GNUM_SWAPCARD_NUMBER" +
//         	" 	FROM GBLT_USER_MST A" +
//			" 	WHERE  A.GNUM_ISVALID = '"+combo1+"' AND "+
//			"   A.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND "+
//			"   A.GNUM_USERID = '"+this.userId+"' ";
//		
//		System.out.println("Query in Initilize View function =  "+query);
//		try
//		{
//			AL_List = getDetail(query);
//			if((AL_List.get(1)==null)||(AL_List.get(1).equals("")))
//				AL_List.set(1,"Non Employee");
//			else if(!(AL_List.get(1)==null)||!(AL_List.get(1).equals("")))
//				AL_List.set(1,"Employee");
//			if((AL_List.get(6)==null)||(AL_List.get(6).equals("")))
//				AL_List.set(6,"No Designation");
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error in InitilizeUpdate function =  "+e);
//		}
//
//		hospitalCode   =    (String)AL_List.get(0);
//			empNo		=	(String)AL_List.get(1);
//			userName	=	(String)AL_List.get(2);
//			userLevel	=	(String)AL_List.get(3);
//			groupNo	    =	"No Group";
//			userSeatNo	=	"No Seat Associated";
//	        effDate		=	(String)AL_List.get(4);
//	        expDate		=	(String)AL_List.get(5);
//	        designation	=	(String)AL_List.get(6);
//	        ufullName	=	(String)AL_List.get(7);
//	        mobileNo        =(String)AL_List.get(8);
//	        emailId         =(String)AL_List.get(9);
//	        smartCardNo     =(String)AL_List.get(10);
//		}
//		else
//		{
		query 	=	"	SELECT (SELECT ghm.GSTR_HOSPITAL_NAME" +
 			"	FROM    GBLT_HOSPITAL_MST ghm " +
 			"   WHERE ghm.GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "+
 			"   AND GNUM_ISVALID = 1 )gstr_hospital_name , "+
 			"  A.PSRSTR_EMP_NO," +   
 			"   GSTR_USER_NAME," +
 			"	GNUM_USERLEVEL," +
 			" NVL ((SELECT INITCAP (GSTR_GROUP_NAME) "+
            " FROM gblt_group_mst "+ 
            " WHERE gnum_seat_id = a.gnum_user_seatid "+
            " AND gnum_hospital_code = a.gnum_hospital_code "+
            "      AND gnum_isvalid = 1  "+
         //   "	  AND gnum_group_code = c.gnum_group_code  "+
			"  ),'Group Inactive/Deleted') gstr_group_name, "+ 
			" NVL ((SELECT INITCAP (gstr_seat_name) "+
            " FROM gblt_seat_mst "+
            " WHERE gnum_seatid = a.gnum_user_seatid "+
            " AND gnum_hospital_code = a.gnum_hospital_code "+
            " AND gnum_isvalid = 1),'Seat Inactive/Deleted') gstr_seat_name, "+
			"   TO_CHAR(A.GDT_EFFECT_DATE,'DD-Mon-YYYY')," +
			"   TO_CHAR(A.GDT_EXPIRY_DATE,'DD-Mon-YYYY')," +
			" 	GNUM_DESIGNATION," +
			"	GSTR_USR_NAME," +
			"   GNUM_MOBILE_NUMBER,"+
            "   GSTR_EMAIL_ID,"+
            "   GNUM_SWAPCARD_NUMBER" +
         	" 	FROM GBLT_USER_MST A" +
//			"   GBLT_SEAT_MST B," +
//			"   GBLT_GROUP_MST C" +
			" 	WHERE  A.GNUM_ISVALID = '"+combo1+"' AND "+
//			"   A.GNUM_USER_SEATID = B.GNUM_SEATID AND "+
//			"   B.GNUM_GROUP_CODE=C.GNUM_GROUP_CODE and "+
			"   A.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND "+
//			"   b.gnum_hospital_code = a.gnum_hospital_code  and "+
//			"   c.gnum_hospital_code = a.gnum_hospital_code and  "+
			"   A.GNUM_USERID = '"+this.userId+"' ";
		
		
		
		System.out.println("Query in Initilize View function =  "+query);
		try
		{
			AL_List = getDetail(query);
			if((AL_List.get(1)==null)||(AL_List.get(1).equals("")))
				AL_List.set(1,"Non Employee");
			else if(!(AL_List.get(1)==null)||!(AL_List.get(1).equals("")))
				AL_List.set(1,"Employee");
			if((AL_List.get(8)==null)||(AL_List.get(8).equals("")))
				AL_List.set(8,"No Designation");
		}
		catch(Exception e)
		{
			System.out.println("Error in InitilizeUpdate function =  "+e);
		}

		hospitalCode   =    (String)AL_List.get(0);
			empNo		=	(String)AL_List.get(1);
			userName	=	(String)AL_List.get(2);
			userLevel	=	(String)AL_List.get(3);
			groupNo	    =	(String)AL_List.get(4);
			userSeatNo	=	(String)AL_List.get(5);
	        effDate		=	(String)AL_List.get(6);
	        expDate		=	(String)AL_List.get(7);
	        //hintQuet	=	(String)AL_List.get(8);
	        designation	=	(String)AL_List.get(8);
	        ufullName	=	(String)AL_List.get(9);
	        //oldPwd		= 	SecurityUtil.decrypt((String)AL_List.get(8));
	        //oldPwd		=	(String)AL_List.get(9);
	        mobileNo        =(String)AL_List.get(10);
	        emailId         =(String)AL_List.get(11);
	        smartCardNo     =(String)AL_List.get(12);
	        return AL_List;
    
	}
	
 // Update New Records in Table On Modify Page.
	
	

	// for Automatic generation of slno in GBLT_USER_SEAT_DTL
	public String slNoInUserSeatDtl()
	{
		String id	=	"";
		int seatID	=	0;
		String query	=	"";
		try
		{
			 query =	" SELECT UNIQUE GNUM_SEAT_SLNO "+
						" FROM GBLT_USER_SEAT_DTL "+
						" WHERE GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and GNUM_USERID="+this.userId ;

			 HisResultSet rs = getRecord(query);
			 if(!rs.next())
			{
				seatID	=	 1;
			}
			else
			{
				rs.last();
				id	=	rs.getString(1);
				seatID	=	Integer.parseInt(id);
				seatID	+=	1;
			}
		}
		catch(Exception e)
		{
			System.out.println("Error while fetching records in Popup Window"+e);
		}
		return ""+seatID;
	}
	
	// insertion of Record in GBLT_USER_SEAT_DTL
	public boolean insertRecordInUserSeatDtl()throws Exception
	{
		
    	this.slNoUserSeatDtl=slNoInUserSeatDtl();
    	String values = new String();
		status = "Record Added Successfully !";
		String query  = new String();
		retValue = true;
	   
        
		entryDate	=	getSysdate();
         
        values  	  += this.userId+ ",'";
        values  	  += slNoUserSeatDtl + "','";
    	values        += this.hospitalCode + "','";
    	values 		  += effDate + "','";
    	values 		  += entryDate + "','";
    	values		  += entryDate + "','";
    	values 		  += isValid + "','";
    	values 		  += this.seatId + "','";
		values 		  += this.oldUserSeatNo + "'";
		
		System.out.println("hospital code save"+this.hospitalCode);
      
		query 		=   " INSERT INTO GBLT_USER_SEAT_DTL  "+
						" ( "+
						" 	GNUM_USERID,      "+
						" 	GNUM_SEAT_SLNO,   "+
						" 	GNUM_HOSPITAL_CODE, "+
						"   GDT_EFFECT_FROM_DATE,"+
						"   GDT_EFFECT_TO_DATE,"+
						"   GDT_ENTRY_DATE ,"+
						" 	GNUM_ISVALID,     "+
						" 	GNUM_SEAT_ID,     "+
						"   GNUM_SEATID  "+
						
                        " ) VALUES ( ";
		query		+= values + ")";
		
		try
		{
			System.out.println("insert Query: "+query);
			if (!updateRecord(query))
			{
				status = "Error while inserting the record";
				System.out.println("insert Query: "+query);
				retValue = false;
			}
		}
		catch(Exception e)
		{
			status = String.valueOf(e);
			System.out.println("Error While Inserting Record: "+e);
			System.out.println("insert Query: "+query);
			retValue = false;
		}
		return retValue;
	}
	
	
	//insert Record in GBLT_USER_MST
    public boolean insertRecord()throws Exception
	{
		
    	System.out.println("into save function");
    	
    	
    	
    	this.userId=UserId();
    	String values = new String();
		String query  = new String();
		boolean retvalue1 = true;
		//String userPassward= SecurityUtil.encrypt(this.userPwd);
		String userPasswardHashed= this.userPwd;
System.out.println(userPasswardHashed.length());
		String insertMenuId="";
		
		Connection conn = null;
		Statement stmt=null;
		conn = super.getConnection();	
		conn.setAutoCommit(false);	
		stmt= conn.createStatement();
		
		System.out.println("this.menuIdCollection----"+this.menuIdCollection);
		StringTokenizer st = new StringTokenizer(this.menuIdCollection,"^");
		
		 query= " select GSTR_USER_NAME from GBLT_USER_MST WHERE " +
 		" GSTR_USER_NAME=('"+this.userName.trim()+"')and GNUM_ISVALID>0 and " +
// 		"GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE='" +this.hospitalCode +"' " ;
		"GNUM_ISVALID<3 " ;
		 
		 //Comment  by Ankur on 20-04-2009 ,
		 //if multiple users have to be checked across the hospitals
		 //then we have to remove the hospital code check from the query,
		 //inorder to prevent conflict of user id 
		 //in case of multiple hospitals  data will be merged in a centralised database

		 //if(this.userSeatNo=="0")
		// {
			// this.userSeatNo="";
			// System.out.println("hiiiiiiiiiiii----------");
		// }
 
		 
		 System.out.println("insert duplicatse check "+query);
 
		 boolean redundant = isRedundentName(query);
		 
		 if(!redundant)
	        {

		
		
		//if(IsDuplicate())
			//return false;
        
		
		entryDate	=	getSysdate();
		//if(empNo=="" || empNo==null)
		//	empNo="0";

       /* values  	   = userId + ",'";
		values 		  += this.userName.trim() + "','";
		values 		  += userPassward + "','";
		values 		  += empNo + "','";
		//values 		  += oldPwd + "','";
		values 		  += userSeatNo + "','";
		values 		  += effDate + "','";
		values 		  += expDate + "','";
		values 		  += emp+ "','";
		values 		  += userLevel + "','";
		values 		  += this.scanUser + "','";
		//values 		  += '1' + "','";
		values 		  += this.seatId + "','";
		values		  += entryDate + "','";
		values 		  += isValid + "','";
		//values        += this.hintQuet + "','";
		values        += this.designation + "','";
		values        += this.ufullName + "','";
		values        += this.smartCardNo + "','";
		values        += this.mobileNo + "','";
		values        += this.emailId + "','";
		values        += this.hospitalCode + "'";*/
		
		   values  	   = "cast("+userId+" as numeric)"+ ",'";
			values 		  += this.userName.trim() + "','";
			values 		  += userPasswardHashed + "',NVL('";
			values 		  += empNo + "',NULL),";
			//values 		  += oldPwd + "','";
			values 		  += "cast("+userSeatNo+" as numeric)" + ",";
			values 		  += "to_date('"+effDate+"','dd-mon-yyyy')" + ",";
			values 		  += "to_date('"+expDate+"','dd-mon-yyyy')" + ",";
			values 		  += "cast("+emp+" as numeric)"+ ",";
			values 		  += "cast("+userLevel+" as numeric)" + ",";
			values 		  += this.scanUser + ",";
			//values 		  += '1' + "','";
			values 		  += "cast("+this.seatId+" as numeric)" + ",";
			values		  += "to_date('"+entryDate+"','dd-mon-yyyy')" + ",";
			values 		  += "cast("+isValid+" as numeric)" + ",'";
			//values        += this.hintQuet + "','";
			values        += this.designation + "','";
			values        += this.ufullName + "','";
			values        += this.smartCardNo + "',";
			if(this.mobileNo.equals("") || this.mobileNo==null)
			{
				values        += "cast(0 as numeric)" + ",'";
			}
			else
			{
				values        += "cast("+this.mobileNo+" as numeric)" + ",'";
			}
			
			values        += this.emailId + "',";
			values        += "cast("+this.hospitalCode+" as numeric)" ;
	
		
        query 		=   " INSERT INTO GBLT_USER_MST  "+
						" ( "+
						" 	GNUM_USERID,      "+
						" 	GSTR_USER_NAME,   "+
						" 	GSTR_PASSWORD,    "+
						" 	PSRSTR_EMP_NO,    "+
					//	" 	GSTR_OLD_PASSWORD,"+
						" 	GNUM_USER_SEATID ,"+
						" 	GDT_EFFECT_DATE,  "+
						" 	GDT_EXPIRY_DATE,  "+
						"	GNUM_USER_TYPE,   "+
						"	GNUM_USERLEVEL,   "+
						" 	GSTR_STATUS_CODE, "+
						" 	GNUM_SEAT_ID,     "+
						" 	GDT_ENTRY_DATE,   "+
						" 	GNUM_ISVALID,       "+
                       // "   GSTR_HINT_QUESTIONS, "+
                        " 	GNUM_DESIGNATION, "+
                        "	GSTR_USR_NAME, "+
                        " 	GNUM_SWAPCARD_NUMBER, "+
                        " 	GNUM_MOBILE_NUMBER, "+
                        " 	GSTR_EMAIL_ID, "+
                        " 	GNUM_HOSPITAL_CODE "+
                        
                        " ) VALUES ( ";
		query		+= values + ")";
		
		
		System.out.println("save Query1----"+query);
		
		String insertUsername="";
		insertUsername=query;
		stmt.addBatch(insertUsername);
		
		
		
		
		
		//getting menuId's
		int displayorder=1;
		
		
		//case1 when the  user selects some checkboxes 
		
		 System.out.println("this.myService---->"+this.myService);
		
		if(menuIdArray!=null)  
    	{
			System.out.println("into if ----->");
    	  for(int i=0; i <menuIdArray.length ;i++)
    	  {
    		  
    		  String menuId=menuIdArray[i];//getting MenuIds
  			
  			System.out.println("menuId----->"+menuId);
  			
  			//getting the serial No. for corresponding composite key combination
  			String query_fetchslno=	"select max(a.GNUM_SLNO) from "+
  	        " GBLT_MYSERVICES_USERMENU_MST a "+
  	        " where "+
  	        " a.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'  and "+       
  	        " a. GNUM_MENU_ID='"+menuId+"' and"+
  	        " a.GNUM_USERID='"+this.userId+"' "; 
  	 

  System.out.println("query_fetchslno----->"+query_fetchslno);
  			HisResultSet rs1 = null;
  			String slno = "";
  			int iSlno=0;
  			

  			try
  			{		
  			rs1 = super.getRecord(query_fetchslno,true);
  			while(rs1.next())
  			  {
  				 slno = rs1.getString(1);
  			  }				

  			}
  			catch(Exception ex)
  			{
  			System.out.println("Exception there in getting data from database "+ex);
  			}
  			
  		System.out.println("Before slno1----->"+slno);
  			
  			if(slno==null || slno=="")
  				iSlno=1;
  			else
  				iSlno=Integer.parseInt(slno)+1;
  			System.out.println("After  slno2----->"+iSlno);

  			
  //for inserting the menuid's			
  			
   insertMenuId=" insert into gblt_myservices_usermenu_mst " +
  				" (GNUM_MENU_ID, GNUM_USERID, GNUM_DISPLAY_ORDER, GNUM_ISVALID, " +
  				" GNUM_SEAT_ID, GDT_ENTRY_DATE, GNUM_HOSPITAL_CODE, GNUM_SLNO) " +
  				" values ('"+menuId+"','"+this.userId+"','"+displayorder+"','"+this.isValid+"', " +
  				" '"+this.seatId+"','"+this.entryDate+"','"+this.hospitalCode+"','"+iSlno+"')";
  		
  		stmt.addBatch(insertMenuId);

  		displayorder++;
  		
  
 		  
    	    }//for closed
    		  
    		  
    	}//if closed   
		else 
		if(this.myService.equals("on"))	//case2 when the  user selects all checkboxes & 
			{	                        //all menuids present are supposed to be saved
			System.out.println("into else----->");
		
		
		while (st.hasMoreElements())
		{
			
			
			
			String menuId=st.nextToken().toString();//getting MenuIds
			
			System.out.println("menuId----->"+menuId);
			
			//getting the serial No. for corresponding composite key combination
			String query_fetchslno=	"select max(a.GNUM_SLNO) from "+
	        " GBLT_MYSERVICES_USERMENU_MST a "+
	        " where "+
	        " a.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'  and "+       
	        " a. GNUM_MENU_ID='"+menuId+"' and"+
	        " a.GNUM_USERID='"+this.userId+"' "; 
	 

System.out.println("query_fetchslno----->"+query_fetchslno);
			HisResultSet rs1 = null;
			String slno = "";
			int iSlno=0;
			

			try
			{		
			rs1 = super.getRecord(query_fetchslno,true);
			while(rs1.next())
			  {
				 slno = rs1.getString(1);
			  }				

			}
			catch(Exception ex)
			{
			System.out.println("Exception there in getting data from database "+ex);
			}
			
		System.out.println("Before slno1----->"+slno);
			
			if(slno==null || slno=="")
				iSlno=1;
			else
				iSlno=Integer.parseInt(slno)+1;
			System.out.println("After  slno2----->"+iSlno);

			
//for inserting the menuid's			
			
 insertMenuId=" insert into gblt_myservices_usermenu_mst " +
				" (GNUM_MENU_ID, GNUM_USERID, GNUM_DISPLAY_ORDER, GNUM_ISVALID, " +
				" GNUM_SEAT_ID, GDT_ENTRY_DATE, GNUM_HOSPITAL_CODE, GNUM_SLNO) " +
				" values ('"+menuId+"','"+this.userId+"','"+displayorder+"','"+this.isValid+"', " +
				" '"+this.seatId+"','"+this.entryDate+"','"+this.hospitalCode+"','"+iSlno+"')";
		
 
 System.out.println(" insertMenuId----->"+ insertMenuId);
		stmt.addBatch(insertMenuId);

		displayorder++;
		
		}//while closed
		
  }//else closed
		
		
		try
		{

			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			this.status = "Record Successfully Inserted !";
		}
		catch(Exception e)
		{
			System.out.println("Exception in insert Record "+e);
			retvalue1 = false;
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
	        }
		 
		else
		{
			this.status="User Name Already Exist,Please Enter Other User Name ";
	    	   retvalue1=false;
		}
		return retvalue1;

	}
   //for updating the records 
    
    public boolean updateRecord( HttpServletRequest request )throws Exception
	{
		
		System.out.println("inside update function userId....."+this.userId);
		System.out.println("inside update function myService....."+this.myService);
		System.out.println("inside update function menuid array check if avilable or not...."+menuIdArrayCheck);
		
		if(this.empNo.equals("Non-Employee"))
			this.empNo="";
		
		String oldRecord		=	"";
		String oldVal			=	"";
		String query			=	"";
		String query1			=	"";
		String query2			=	"";
		String userPassward		=	"";
		String newVal			=	"";
		String pK				=	"";
		String defaultMobileNo="0";
		boolean flag 			=	false;
		boolean isRun			=	false;
	    Connection conn 		= 	null ; 
	    PreparedStatement ps1 	=	null;
	    Statement st 			= 	null ;
		int isValid;
		status = "Record  Successfully Updated!";
		pK=	this.userId;
		
		
		conn = new HisMethods().getConnection();
		 conn.setAutoCommit(false); 
		 st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 StringTokenizer tokenizer = new StringTokenizer(this.menuIdCollection,"^");
		
		
		
		
		
        oldRecord	=	"	select 'GSTR_USER_NAME'||'^'||GSTR_USER_NAME||'#'||'GDT_EFFECT_DATE'||'^'||GDT_EFFECT_DATE"+
		 				"	||'#'||'GDT_EXPIRY_DATE'||'^'||GDT_EXPIRY_DATE||'#'||'GNUM_USERLEVEL'||'^'||GNUM_USERLEVEL||'#'||'GNUM_ISVALID'||'^'||GNUM_ISVALID"+
						"	||'#'||'GNUM_SWAPCARD_NUMBER'||'^'||GNUM_SWAPCARD_NUMBER||'#'||'GNUM_DESIGNATION'||'^'||GNUM_DESIGNATION||'#'||'GSTR_USR_NAME'||'^'||GSTR_USR_NAME"+
					    "   ||'#'||'GNUM_MOBILE_NUMBER'||'^'||GNUM_MOBILE_NUMBER||'#'||'GSTR_EMAIL_ID'||'^'||GSTR_EMAIL_ID"+
					    "   ||'#'||'GNUM_USER_SEATID'||'^'||GNUM_USER_SEATID"+
		 				"   from GBLT_USER_MST where GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and GNUM_USERID='"+this.userId+"'";

        System.out.println("oldrecord....."+oldRecord);
         oldVal 	= super.getField(oldRecord);
        
         
         newVal =	"	GSTR_USER_NAME^"+this.userName+"#GDT_EFFECT_DATE^"+this.effDate+"#GDT_EXPIRY_DATE^"+this.expDate+
		 			"	#GNUM_USERLEVEL^"+this.userLevel+"#GNUM_ISVALID^"+this.isValid+"#GNUM_SWAPCARD_NUMBER^"+this.smartCardNo+"#GSTR_EMAIL_ID^"+this.emailId+"#GNUM_MOBILE_NUMBER^"+this.mobileNo+
					"	#GNUM_DESIGNATION^"+this.designation+"#GSTR_USR_NAME^"+this.ufullName+"#GNUM_USER_SEATID^"+this.userSeatNo;
         
        String oldSeatAssociate=this.oldUserSeatNo;
        String seatAssociate=this.userSeatNo;
        
        System.out.println("oldSeatAssociat...."+oldSeatAssociate);
        System.out.println("seatAssociate...."+seatAssociate);
        
        System.out.println("prevHospitalCode...."+this.prevHospitalCode);
        System.out.println("HospitalCode...."+this.hospitalCode);
        
       if(!(this.prevHospitalCode.equals(this.hospitalCode)))
       {
    	   this.userSeatNo="";
    	   System.out.println("rhis====="+this.userSeatNo);
       }
     
        // if Seat id changed on modify page than one record wii be inserted in GBLT_USER_SEAT_DTL
         if( seatAssociate.equals(oldSeatAssociate) )
         {
        	 if(this.mobileNo.equals("") || this.mobileNo==null)
        	 {
        		 this.mobileNo=defaultMobileNo;
        	 }
        	 query	=	"	UPDATE GBLT_USER_MST SET  PSRSTR_EMP_NO='"+this.empNo+"',GSTR_USER_NAME='"+this.userName.trim()+"',GDT_ENTRY_DATE='"+getSysdate()+"',"+
	 							"	GDT_EXPIRY_DATE='"+this.expDate+"',GNUM_USERLEVEL='"+this.userLevel+"',GSTR_STATUS_CODE='"+this.scanUser+"',"+
	 							"	GNUM_ISVALID='"+this.isValid+"',GNUM_SWAPCARD_NUMBER='"+this.smartCardNo+"'," +
	 							"   GSTR_EMAIL_ID='"+this.emailId+"',GNUM_MOBILE_NUMBER='"+this.mobileNo+"'," +
	 							"	GNUM_DESIGNATION='"+this.designation+"',GSTR_USR_NAME='"+this.ufullName+"', "+
	 							"	GNUM_USER_SEATID='"+this.userSeatNo+"',GNUM_LSTMOD_SEATID='"+this.seatId+"',GDT_LSTMOD_DATE=sysdate,  "+
	 							"   GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'"+
	 							" 	WHERE "+
//	 							" GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'
	 							" GNUM_USERID="+this.userId;
        	 		st.addBatch(query); 
        	 		isRun	=	true;
        	 		
        	 		 System.out.println("if query...."+query);
         }
         else
         {
        	 if(this.mobileNo.equals("") || this.mobileNo==null)
        	 {
        		 this.mobileNo=defaultMobileNo;
        	 }   
        	 
        	 query	=	"	UPDATE GBLT_USER_MST SET  PSRSTR_EMP_NO='"+this.empNo+"',GSTR_USER_NAME='"+this.userName.trim()+"',GDT_ENTRY_DATE='"+getSysdate()+"',"+
						"	GDT_EXPIRY_DATE='"+this.expDate+"',GNUM_USERLEVEL='"+this.userLevel+"',GSTR_STATUS_CODE='"+this.scanUser+"',"+
						"	GNUM_ISVALID='"+this.isValid+"',GNUM_SWAPCARD_NUMBER='"+this.smartCardNo+"'," +
						"   GSTR_EMAIL_ID='"+this.emailId+"',GNUM_MOBILE_NUMBER='"+this.mobileNo+"'," +
						"	GNUM_DESIGNATION='"+this.designation+"',GSTR_USR_NAME='"+this.ufullName+"',"+
						"	GNUM_USER_SEATID='"+this.userSeatNo+"' ,GNUM_LSTMOD_SEATID='"+this.seatId+"',GDT_LSTMOD_DATE=sysdate ,  "+
						"    GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'"+
						" 	WHERE" +
						"  GNUM_USERID="+this.userId;
        
        	        	 
        	 System.out.println("else query...."+query);
        	 st.addBatch(query);
        	 isRun	=	true;
        	 
        	//isRun=   insertRecordInUserSeatDtl();    //insert Record in GBLT_USER_SEAT_DTL 
        	
        	 //System.out.println("isRun...."+isRun);
        	 
        	 	
         }
         
         
         //GETTING THE OLD MENUIDS   
         String checkvalue=this.CheckValue();
       
         List lstOne = new ArrayList();////contains old selected menu
         List lstTwo = new ArrayList();////contains new selected menu
         List recToDelete = new ArrayList();//contains deleted menus
         List recToInsert = new ArrayList();////contains new inserting menu
         List recToModify = new ArrayList();//contains new modified menu
         
         StringTokenizer stOne = new StringTokenizer(checkvalue,"^");
         while(stOne.hasMoreElements())
         {
         	lstOne.add(stOne.nextToken().toString());		

         }
      
         
         System.out.println("MY SERVICES CHECKBOX..."+this.getMyService());    
   if(menuIdArray!=null)      
   {
	   System.out.println("into case 1 of menuidarray----->");
	 
	   for(int j=0; j < menuIdArray.length ;j++)
	   {
		   System.out.println("menuIdArray[j]...."+menuIdArray[j]);
		   lstTwo.add(menuIdArray[j]); 
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
         //getting data to be deleted
  for(int i=0; i <lstOne.size() ;i++)
  {
	  boolean found = false;
    for(int j=0; j <lstTwo.size() ;j++)
   	  {
    	if(lstOne.get(i).toString().equals(lstTwo.get(j).toString()))
		{
			found = true;
			break;
		}	 
    	
   	  }
    if(!found)
	   {
		 System.out.println("values to delete-------->"+lstOne.get(i));
		recToDelete.add(lstOne.get(i));
 	  }   
  }      
         
         

  //getting data to be inserted
for(int j=0; j <lstTwo.size() ;j++)
{
boolean found = false;
for(int i=0; i <lstOne.size() ;i++)
  {
	if(lstTwo.get(j).toString().equals(lstOne.get(i).toString()))
	{
		found = true;
		break;
	}	 
	
  }
if(!found)
  {
	recToInsert.add(lstTwo.get(j).toString());
	System.out.println("values to inserted------->"+lstTwo.get(j).toString());
  }
}      
   

//getting data to be modified
for(int j=0; j <lstTwo.size() ;j++)
{
boolean found = false;
for(int i=0; i <lstOne.size() ;i++)
  {
	if(lstTwo.get(j).toString().equals(lstOne.get(i).toString()))
	{
		found = true;
		break;
	}	 
	
  }
if(found)
{
	recToModify.add(lstTwo.get(j).toString());
	System.out.println("values to modified------->"+lstTwo.get(j).toString());
}

}  //for closed        
         
 


//deleting old menuids from  table  GBLT_MYSERVICES_USERMENU_MST
 //For executing delete query
	if(recToDelete.size()!=0)
	{
		for(int i=0;i<recToDelete.size();i++)
				{
			String strToDelete="";
		strToDelete = recToDelete.get(i).toString();
		String deleteQuery = "";
	  deleteQuery = " UPDATE GBLT_MYSERVICES_USERMENU_MST "+
					"set GNUM_ISVALID='2' "+
					"where GNUM_ISVALID='"+this.isValid+"' and " +
					" GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and "+
					"  GNUM_USERID='"+this.userId+"'and " +					
					" GNUM_MENU_ID='"+strToDelete+"' ";
	System.out.println("delete_query----->"+ deleteQuery);
					st.addBatch( deleteQuery);
				}
		}
		
   
	//inserting old  menuids from  table  GBLT_MYSERVICES_USERMENU_MST
	 //For executing insert query 
	if(recToInsert.size()!=0)
	{
		for(int i=0;i<recToInsert.size();i++)
				{
			
			String strToInsert="";
			 String insertQuery = "";
			 int displayorder=1;
			 
			strToInsert = recToInsert.get(i).toString();
			displayorder=lstTwo.indexOf(strToInsert)+1;
			
			System.out.println("strToInsert---->"+strToInsert);
		System.out.println("displayorder----->"+displayorder);
	
	
	
	//getting the serial No. for corresponding composite key combination
	String query_fetchslno=	"select max(a.GNUM_SLNO) from "+
    " GBLT_MYSERVICES_USERMENU_MST a "+
    " where "+
    " a.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'  and "+       
    " a. GNUM_MENU_ID='"+strToInsert+"' and"+
    " a.GNUM_USERID='"+this.userId+"' "; 


System.out.println("query_fetchslno----->"+query_fetchslno);
	HisResultSet rs1 = null;
	String slno = "";
	int iSlno=0;
	

	try
	{		
	rs1 = super.getRecord(query_fetchslno,true);
	while(rs1.next())
	  {
		 slno = rs1.getString(1);
	  }				

	}
	catch(Exception ex)
	{
	System.out.println("Exception there in getting data from database "+ex);
	}
	
System.out.println("Before slno1----->"+slno);
	
	if(slno==null || slno=="")
		iSlno=1;
	else
		iSlno=Integer.parseInt(slno)+1;
	System.out.println("After  slno2----->"+iSlno);

	
//for inserting the menuid's			
	
	String insertMenuId="";	
	entryDate	=	getSysdate();
	System.out.println("Entry Date"+entryDate);
	System.out.println("this.entryDate"+this.entryDate);
insertMenuId=" insert into gblt_myservices_usermenu_mst " +
		" (GNUM_MENU_ID, GNUM_USERID, GNUM_DISPLAY_ORDER, GNUM_ISVALID, " +
		" GNUM_SEAT_ID, GDT_ENTRY_DATE, GNUM_HOSPITAL_CODE, GNUM_SLNO) " +
		" values ('"+strToInsert+"','"+this.userId+"','"+displayorder+"','"+this.isValid+"', " +
		" '"+this.seatId+"','"+this.entryDate+"','"+this.hospitalCode+"','"+iSlno+"')";

System.out.println("insert_Query---->"+insertMenuId);

st.addBatch(insertMenuId);
   
   
   
				}//for closed
		
	}//if for insert  closed
   
   
   
//for updating the menuid's			
	
	
	if(recToModify.size()!=0)
	{
		for(int i=0;i<recToModify.size();i++)
				{
			
			String valueToModify="";
			 String modifyQuery = "";
			 int displayorder=1;
			 
			 valueToModify =recToModify.get(i).toString();
			displayorder=lstTwo.indexOf( valueToModify)+1;
	
	
	
	

	modifyQuery=" UPDATE GBLT_MYSERVICES_USERMENU_MST "+
"set GNUM_DISPLAY_ORDER='"+displayorder+"' "+
"where GNUM_ISVALID='"+this.isValid+"' and " +
" GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and "+
"  GNUM_USERID='"+this.userId+"'and " +					
" GNUM_MENU_ID='"+valueToModify+"' ";
	
	System.out.println("modify_Query---->"+modifyQuery);

st.addBatch(modifyQuery);
   
   
   
				}//for closed
		
	}//if for modify closed
	
   }else//if closed	
if(menuIdArrayCheck.equals("false") || this.getMyService().equals("0") )//if no menu is selected from the pop up or the check box is unchecked all old menus are to be deleted   		
	{
			 System.out.println("into case 2 of false----->");
		//deleting old menuids from  table  GBLT_MYSERVICES_USERMENU_MST
		 //For executing delete query
			if(lstOne.size()!=0)
			{
				for(int i=0;i<lstOne.size();i++)
						{
					String strToDelete="";
				strToDelete =lstOne.get(i).toString();
				String deleteQuery = "";
			  deleteQuery = " UPDATE GBLT_MYSERVICES_USERMENU_MST "+
							"set GNUM_ISVALID='2' "+
							"where GNUM_ISVALID='"+this.isValid+"' and " +
							" GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and "+
							"  GNUM_USERID='"+this.userId+"'and " +					
							" GNUM_MENU_ID='"+strToDelete+"' ";
			System.out.println("delete_query_all----->"+ deleteQuery);
							st.addBatch( deleteQuery);
						}
				}
	
		
	} else//if n all old menus are to be selected while updating
	 if(this.myService.equals("on"))   		
		{
				 System.out.println("into case 3 of on ----->");
			int displayorder=1;		
					
					while (tokenizer.hasMoreElements())
					{
						
						
						
						String menuId=tokenizer.nextToken().toString();//getting MenuIds
						
						System.out.println("menuId----->"+menuId);
						
						//getting the serial No. for corresponding composite key combination
						String query_fetchslno=	"select max(a.GNUM_SLNO) from "+
				        " GBLT_MYSERVICES_USERMENU_MST a "+
				        " where "+
				        " a.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'  and "+       
				        " a. GNUM_MENU_ID='"+menuId+"' and"+
				        " a.GNUM_USERID='"+this.userId+"' "; 
				 

			System.out.println("query_fetchslno----->"+query_fetchslno);
						HisResultSet rs1 = null;
						String slno = "";
						int iSlno=0;
						

						try
						{		
						rs1 = super.getRecord(query_fetchslno,true);
						while(rs1.next())
						  {
							 slno = rs1.getString(1);
						  }				

						}
						catch(Exception ex)
						{
						System.out.println("Exception there in getting data from database "+ex);
						}
						
					System.out.println("Before slno1----->"+slno);
						
						if(slno==null || slno=="")
							iSlno=1;
						else
							iSlno=Integer.parseInt(slno)+1;
						System.out.println("After  slno2----->"+iSlno);

						
			//for inserting the menuid's	
						String insertMenuId1="";
						entryDate	=	getSysdate();
						System.out.println("Entry Date"+entryDate);
						System.out.println("this.entryDate"+this.entryDate);
						
			 insertMenuId1=" insert into gblt_myservices_usermenu_mst " +
							" (GNUM_MENU_ID, GNUM_USERID, GNUM_DISPLAY_ORDER, GNUM_ISVALID, " +
							" GNUM_SEAT_ID, GDT_ENTRY_DATE, GNUM_HOSPITAL_CODE, GNUM_SLNO) " +
							" values ('"+menuId+"','"+this.userId+"','"+displayorder+"','"+this.isValid+"', " +
							" '"+this.seatId+"','"+this.entryDate+"','"+this.hospitalCode+"','"+iSlno+"')";
					
			 
			 System.out.println(" insertMenuId1----->"+ insertMenuId1);
					st.addBatch(insertMenuId1);

					displayorder++;
					
					}//while closed
		
			
		}//last if else closed
   
   
         if(isRun)
         {
        	 
     		 try
     		 {
     			
     			 st.executeBatch();
     			 ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_USER_MST").updateAuditLog(request,conn);
     			 conn.commit();
     			 flag=true;
     			status = "Record Modified Successfully!";
     		 }
     		 catch( Exception e )
     		 {
     			 status="Error while Updating Record(s)";
     			 try
     			 { 		    
     				 conn.rollback();  
     			 } 
     			 catch(Exception se) 
     			 { 
     				 System.out.println("error in try block..."+se);
     			 }
    		}
     		finally
     		{
     			try
     			{
	     			if(conn!=null)
	     				conn.close();
	     			if(ps1!=null)
	     				ps1.close();
	     			if(st!=null)
	     				st.close();
     			}
     			catch(Exception e)
     			{
     				System.out.println("Exception in finally block"+e);
     			}
         
     		}
         
         }
         else
         {
        	 this.status=	" Record is not Updated.......!";
         }
         
         return flag;
	}
    
	public String deleteRecord()throws Exception
	{
		status	=	"Successfully deleted !";
		int i	=	0;
		
		String query=  " UPDATE GBLT_USER_MST"+
						" SET"+
						" GNUM_ISVALID= 0"+
						" WHERE  GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND GNUM_USERID=?";
		String status="";
			
		
		Connection conn	=	super.getConnection();
		PreparedStatement 	ps = conn.prepareStatement(query);
		StringTokenizer 	st = null;
		try
		{
			for(i=0; i<chk.length; i++)
			{
				st=new StringTokenizer(chk[i],"^");
				userId=st.nextToken();
				ps.setString(1,userId);
				ps.execute();
			}
			status = i + " Record(s) Deleted Successfully.";

		}
		catch(Exception e)
		{
			status = "Error in deleting Record(s): " + e;
		}
		finally
		{
			super.closeConnection(conn);	
		}
		return status;

	}//11

	//for checking duplicate record
	private boolean IsDuplicate()throws Exception
	{
		boolean retVal 	=	false;
		List AL_List 	= 	new ArrayList();
		String query 	=	"";
		//String userPassward= SecurityUtil.encrypt(this.userPwd);

		query	=	" SELECT GSTR_USER_NAME "+
					" FROM 	GBLT_USER_MST "+
					" WHERE	GNUM_ISVALID >0 AND GNUM_ISVALID <3 "+
//					" and  GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND UPPER(GSTR_USER_NAME) = '"+userName.toUpperCase().trim()+"' "   ;
					" and  UPPER(GSTR_USER_NAME) = '"+userName.toUpperCase().trim()+"' "   ;

System.out.println("query is"+query);

		AL_List = getDetail(query);
		if(AL_List.size() > 0)
		{
			status = "User name already exist";
			retVal = true;
		}

		return retVal;
	}

	public String getScanUser() {
		return scanUser;
	}

	public void setScanUser(String scanUser) {
		this.scanUser = scanUser;
	}

	

	

	
	

	
	
}


