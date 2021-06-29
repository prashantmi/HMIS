package usermgmt.masters;
import usermgmt.*;
import java.sql.*;
import java.util.*;
import HisGlobal.*;
import javax.servlet.http.HttpServletRequest;
import login.*;
public class UmgmtUserSeatMstBean extends FuncLib
{
	private String designation		=	"";
	private String emp				=	"";
	private String userId			=	"";
	private String combo1			=	"";
    boolean retValue;
	private String 	status			=	"";
	private String userName			=	"";
	private String groupName		=	"";
	private String userPwd			=	"";
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
		return ((userPwd!=null)? "" : userPwd);
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
	

	public String seatOptions()
	{
		String options	=	"";
		String query 	=	"";
		
		query	=	"	SELECT  A.GNUM_SEATID,INITCAP( A.GSTR_SEAT_NAME) FROM GBLT_SEAT_MST A "+
					"	WHERE  GNUM_ISVALID='1' and GNUM_GROUP_CODE='"+this.getGroupName()+"' order by initcap(a.gstr_seat_name)";
		//System.out.println("query in seat is"+query);
	try
		{
			options= populateCombo(query,userSeatNo);			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		//System.out.println("options"+options);
		return options;
	}
	
	public String userOptions()
	{
		String options	=	"";
		String query 	=	"";
		
		query	=	"	SELECT  A.GNUM_USERID,INITCAP( A.GSTR_USER_NAME) FROM GBLT_USER_MST A "+
					"	WHERE  A.GNUM_ISVALID='1'";

	try
		{
			options= populateCombo(query,userName);
			
		}
		
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		return options;
	}

	public String groupOptions()
	{
		String options	=	"";
		String query 	=	"";
		
		query	=	"	SELECT   GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST"+
					" where  GNUM_ISVALID ='1' order by initcap(GSTR_GROUP_NAME)";
	try
		{
			options= populateCombo(query,groupName);
			
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
		userSeatNo	=   "";
		hintQuet	=	"";
		effDate		=	"";
 		expDate    	=	"";
 		designation	=	"";
 		ufullName	=	"";
	}

	
	public String UserId()
	{
		String id	=	"";
		int seatID	=	0;
		String query	=	"";
		try
		{
			 query =	" SELECT UNIQUE GNUM_USERID "+
						" FROM GBLT_USER_MST "+
						" ORDER BY GNUM_USERID ";

			 HisResultSet rs = getRecord(query);
			 if(!rs.next())
			{
				seatID	=	 1001;
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

	public List initializeUpdateMode()throws Exception
	{
		String query = new String("");
		List AL_List = new ArrayList();

		this.userId = chk[0].substring(0,chk[0].length()-1);

		query 	=	"	SELECT  (select  gstr_user_name FROM gblt_user_mst WHERE GNUM_USERID = a.GNUM_USERID) u_name," +
 					"	(select GSTR_USR_NAME FROM gblt_user_mst WHERE GNUM_USERID = a.GNUM_USERID) user_n," +
 					"	(select GNUM_DESIGNATION FROM gblt_user_mst WHERE GNUM_USERID = a.GNUM_USERID) user_d," +
 					"	(SELECT INITCAP (gstr_seat_name) FROM gblt_seat_mst WHERE gnum_seatid = a.gnum_seatid) s_name," +
 					"   TO_CHAR(A.GDT_EFFECT_DATE,'DD-Mon-YYYY')," +
					"   TO_CHAR(A.GDT_EFFECT_TO_DATE,'DD-Mon-YYYY')" +
					" 	FROM gblt_user_seat_dtl A" +
					" 	WHERE  A.GNUM_ISVALID = '"+combo1+"' AND "+
					"   A.GNUM_USERID = '"+this.userId+"'";
					
		System.out.println("In Initilizeupdate detail function =  "+query);
		try
		{
			AL_List = getDetail(query);
			if((AL_List.get(2)==null)||(AL_List.get(2).equals("")))
				AL_List.set(2,"No Designation");
		}
		catch(Exception e)
		{
			System.out.println("Error in InitilizeUpdate function =  "+e);
		}
		userName	=	(String)AL_List.get(0);
		ufullName	=	(String)AL_List.get(1);
		designation	=	(String)AL_List.get(2);
		userSeatNo	=	(String)AL_List.get(3);
        effDate		=	(String)AL_List.get(4);
        expDate		=	(String)AL_List.get(5);
        //System.out.println("userSeatNo =  "+userSeatNo);
	        return AL_List;
    
	}
	
	public List initializeviewMode()throws Exception
	{
	  	String query = new String("");
		List AL_List = new ArrayList();

		query 	=	"	SELECT  A.GSTR_USR_NAME," +
					" 	GNUM_DESIGNATION," +
					"   TO_CHAR(A.GDT_EFFECT_DATE,'DD-Mon-YYYY')," +
					"   TO_CHAR(A.GDT_EXPIRY_DATE,'DD-Mon-YYYY')" +
					" 	FROM GBLT_USER_MST A," +
					"   GBLT_SEAT_MST B " +
					" 	WHERE "+
					"   A.GNUM_USER_SEATID = B.GNUM_SEATID AND "+
					"	A.GNUM_ISVALID='1' AND b.GNUM_ISVALID='1' AND"+
					"   A.GNUM_USERID = '"+this.getUserName()+"'";
		System.out.println("Initilizeview detail query =  "+query);
		try
		{
			AL_List = getDetail(query);
			if((AL_List.get(0)==null)||(AL_List.get(0).equals("")))
				AL_List.set(0,"No Employee Name");
			if((AL_List.get(1)==null)||(AL_List.get(1).equals("")))
				AL_List.set(1,"No Designation");
			if((AL_List.get(2)==null)||(AL_List.get(2).equals("")))
				AL_List.set(2,"No Effect Date");
			if((AL_List.get(3)==null)||(AL_List.get(3).equals("")))
				AL_List.set(3,"No Expiry Date");
			
		}
		catch(Exception e)
		{
			System.out.println("Error in Initilizeview detail function =  "+e);
		}

        	ufullName	=	(String)AL_List.get(0);
			designation	=	(String)AL_List.get(1);
	        effDate		=	(String)AL_List.get(2);
	        expDate		=	(String)AL_List.get(3);
        return AL_List;
    
	}

	public boolean updateRecord( HttpServletRequest request )throws Exception
	{
	  	 System.out.println("updateRecord...");
		String oldRecord		=	"";
		String oldVal			=	"";
		String query			=	"";
		String query1			=	"";
		String query2			=	"";
		String newVal			=	"";
		String pK				=	"";
		boolean flag 			=	false;
		boolean redundant    	= 	false;
	    boolean redundantname  	= 	false;
		boolean isRun			=	false;
	    Connection conn 		= 	null ; 
	    PreparedStatement ps1 	=	null;
	    Statement st 			= 	null ;
		int isValid;
		
		pK=	this.userId;
        oldRecord	=	"	select 'GSTR_USER_NAME'||'^'||GSTR_USER_NAME||'#'||'GSTR_PASSWORD'||'^'||GSTR_PASSWORD||'#'||'GDT_EFFECT_DATE'||'^'||GDT_EFFECT_DATE"+
		 				"	||'#'||'GDT_EXPIRY_DATE'||'^'||GDT_EXPIRY_DATE||'#'||'GNUM_ISVALID'||'^'||GNUM_ISVALID"+
						"	||'#'||'GSTR_USR_NAME'||'^'||GSTR_USR_NAME"+
		 				"  from GBLT_USER_MST where GNUM_USERID='"+this.userId+"'";
      
        System.out.println("oldRecord is"+oldRecord);
        
        oldVal 	= super.getField(oldRecord);
        System.out.println("oldVal is"+oldVal);
         
         newVal =	"	GSTR_USER_NAME^"+this.userName+"#GDT_EFFECT_DATE^"+this.effDate+"#GDT_EXPIRY_DATE^"+this.expDate+
		 			"	#GNUM_ISVALID^"+this.isValid+
					"	#GNUM_DESIGNATION^"+this.designation+"#GSTR_USR_NAME^"+this.ufullName;
         
         System.out.println("newVal is"+newVal);
         
         query1	=	" SELECT GNUM_ISVALID "+
					" FROM 	GBLT_USER_SEAT_DTL "+
					" WHERE	GNUM_ISVALID =1 AND "+
					" GNUM_USERID = "+this.userId;
										
         System.out.println("query1 is"+query1);
         
         redundantname=super.isRedundentName(query1);
         System.out.println("redundantname is"+redundantname);
         if(redundantname)
         {
        	 	query2	=	" SELECT GNUM_ISVALID "+
        	 				" FROM 	GBLT_USER_SEAT_DTL "+
        	 				" WHERE	GNUM_ISVALID =1 AND "+
        	 				" GNUM_USERID = "+this.userId;
        	 	 System.out.println("query2 is"+query2);
        	 	
        	 	 isValid	=	Integer.parseInt(this.isValid);
        	 	 System.out.println("isValid is"+isValid);
        	 	redundant	= super.isRecordExist(query2,isValid);
        	 	 System.out.println("redundant is"+redundant);
        	 	
        	 	 if(redundant)
        	 	{
        	 		//this.status=" User Id Already Exist,Please Enter Other User Id.......!";
        	 		query	=	"	UPDATE GBLT_USER_SEAT_DTL SET GNUM_ISVALID='0'"+
        	 					" 	WHERE GNUM_ISVALID=1 and GNUM_USERID='"+this.userId+"'";
        	
				 		System.out.println("update query is"+query);
				 		isRun	=	true;
				 		//insertRecordMod();
        	 	}
        	 	else
        	 	{       	 		
        	 		System.out.println("in else ");
        	 		
        	 		isRun	=	true;
        	 	}
         }
         else
         {
        	 
        	 System.out.println("in outer else  is");
        	 	isRun	=	true;
         }
		 
         if(isRun)
         {
        	 conn = new HisMethods().getConnection();
     		 conn.setAutoCommit(false); 
     		 st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
     		 try
     		 {
     			 st.addBatch(query);
     			 st.executeBatch();
     			insertRecordMod();
     			 ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_USER_SEAT_DTL").updateAuditLog(request,conn);
     			 conn.commit();
     			 flag=true;
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
        	 this.status=	" User Id Already Exist,Please Enter Other User Id.......!";
         }
         return flag;
	}

    public boolean insertRecord()throws Exception
	{
    	usermgmt.FuncLib f=new usermgmt.FuncLib(); 
    	
    	String values = new String();
		status = "Record Succesfully Inserted !";
		String query  = new String();
		entryDate	=	getSysdate();
		String seqno;
		boolean redundant    	= 	false;
		//for sequence number.....
	
		query=" select nvl(max( GNUM_SEAT_SLNO +1),1) from GBLT_USER_SEAT_DTL WHERE	GNUM_USERID = '"+userName+"'";
		
			seqno 			= f.getField(query);
			
	     System.out.println("query of max seq"+query);
		
        values  	   = userName + ",'";
      //  System.out.println("1..."+userName);
		
        values 		  += userSeatNo + "','";
	//	System.out.println("2..."+userSeatNo);
		
		values 		  += 108 + "','";
		
		values 		  += effDate + "','";
	//	System.out.println("3..."+effDate);
		
		values 		  += expDate + "','";
	//	System.out.println("4..."+expDate);
		
		values		  += entryDate + "','";
	//	System.out.println("5..."+entryDate);
		
		values 		  += isValid + "','";
	//	System.out.println("6..."+isValid);
		
		values        += seatId + "','";
		//System.out.println("7..."+seatId);
		
		values        += seqno + "'";
	//	System.out.println("8..."+seqno);
		
        query 		=   " INSERT INTO GBLT_USER_SEAT_DTL  "+
						" ( "+
						" 	GNUM_USERID,      "+
						" 	GNUM_SEATID,   "+
						" 	GNUM_HOSPITAL_CODE,    "+
						" 	GDT_EFFECT_DATE,    "+
						" 	GDT_EFFECT_TO_DATE,"+
						" 	GDT_ENTRY_DATE ,"+
						" 	GNUM_ISVALID,  "+
						" 	GNUM_SEAT_ID,  "+
						"	GNUM_SEAT_SLNO   "+
                        " ) VALUES ( ";
		query		+= values + ")";
		
		System.out.println("query of insert Inserting Record: "+query);
		try
		{

			if (!updateRecord(query))
			{
				status = "Error while inserting the record";
				retValue = false;
			}
		}
		catch(Exception e)
		{
			status = String.valueOf(e);
			System.out.println("Error While Inserting Record: "+e);
			retValue = false;
		}
			
		return retValue;
	
	}
    
    public boolean insertRecordMod()throws Exception
	{
    	usermgmt.FuncLib f=new usermgmt.FuncLib(); 
    	
    	String values = new String();
		status = "Record Succesfully Inserted !";
		String query  = new String();
		entryDate	=	getSysdate();
		String seqno;
		boolean redundant    	= 	false;
		//for sequence number.....
	
		query=" select nvl(max( GNUM_SEAT_SLNO +1),1) from GBLT_USER_SEAT_DTL WHERE	GNUM_USERID = '"+userId+"'";

			seqno 			= f.getField(query);
			
	    // System.out.println("query of max seq"+query);
		
        values  	   = userId + ",'";
       // System.out.println("1..."+userId);
		
        values 		  += userSeatNo + "','";
	//	System.out.println("2..."+userSeatNo);
		
		values 		  += 108 + "','";
		
		values 		  += effDate + "','";
	//	System.out.println("3..."+effDate);
		
		values 		  += expDate + "','";
	//	System.out.println("4..."+expDate);
		
		values		  += entryDate + "','";
	//	System.out.println("5..."+entryDate);
		
		values 		  += 1 + "','";
		System.out.println("6..."+isValid);
		
		values        += seatId + "','";
		//System.out.println("7..."+seatId);
		
		values        += seqno + "'";
	//	System.out.println("8..."+seqno);
		
        query 		=   " INSERT INTO GBLT_USER_SEAT_DTL  "+
						" ( "+
						" 	GNUM_USERID,      "+
						" 	GNUM_SEATID,   "+
						" 	GNUM_HOSPITAL_CODE,    "+
						" 	GDT_EFFECT_DATE,    "+
						" 	GDT_EFFECT_TO_DATE,"+
						" 	GDT_ENTRY_DATE ,"+
						" 	GNUM_ISVALID,  "+
						" 	GNUM_SEAT_ID,  "+
						"	GNUM_SEAT_SLNO   "+
                        " ) VALUES ( ";
		query		+= values + ")";
		
		System.out.println("query of Inserting in mod Record: "+query);
		try
		{

			if (!updateRecord(query))
			{
				status = "Error while inserting the record";
				retValue = false;
			}
		}
		catch(Exception e)
		{
			status = String.valueOf(e);
			System.out.println("Error While Inserting Record: "+e);
			retValue = false;
		}
			
		return retValue;
	
	}
    
	public String deleteRecord()throws Exception
	{
	  	// System.out.println("delete Record...");
	  	 
		status	=	"Successfully deleted !";
		int i	=	0;
		
		String query=  " UPDATE gblt_user_seat_dtl"+
						" SET"+
						" GNUM_ISVALID= 0"+
						" WHERE GNUM_USERID=?";
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
				//System.out.println("chk delete: "+chk[i]);
				//userId="1123";
			//	System.out.println("userId delete: "+userId);
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
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
