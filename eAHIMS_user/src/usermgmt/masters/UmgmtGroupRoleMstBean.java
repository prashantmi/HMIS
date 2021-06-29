/*
 *//***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2007-2008 
 ## Client's Name					: PGI
 ## Project Name					: HIMS
 ## Phase						: Development
 ## Name of Developer		 		: Mr. Nitin Vohra
 ## Module Name					: User Managment
 ## Date of creation					: 12/11/2008
 ## Purpose						: Bean File
 ## Previous Form(Calling)				: umgmtGroupRole_cntMst.jsp
 ## Functions Used					:insertRecord(),deleteRecord(),initializeUpdateMode(),updateRecord()
 ## Name of Tables used for reference 	            : GBLT_GROUP_MST,GBLT_ROLE_MST,GBLT_METATABLE_TYPE_MST,GBLT_GROUP_ROLE_MST
 ## Name of Tables used for data updation/insertion	: Dynamic
 ## Next Form	(Called)				: NO
 ## Date of Modification				: No
 ## Unit Tested By	& Date				: 17/11/2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :
 				if No then Detail   	:
     7). Name of Objects Used		            	:
 		i). Object Name		 	:
 		ii) Purpose				:
 		iii). No. of times called		:
     8). Any suggestion					:
     9) Other Deviation					:
 ## Remark						:
 ## Finalization Date					:
 ## Future Alteration (1)				:
 ## Any major change
 	1) Reason					:
 	2) Time in days (Hour)			:
 	3) Change Raised By				:
 	4) Tested(Y/N)				:
 	5) Remark					:
 




*/



package usermgmt.masters;
import usermgmt.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Date;
import java.sql.*;



import HisGlobal.*;

public class UmgmtGroupRoleMstBean	extends UserFunc 
{
	private String groupName=	"";
	private String roleName=	"";
	private String moduleName=	"";
	private String combo1		="";
	private String groupCode	="";
	private String role_id  	="";
	private String module_id	="";
	private String seat_id 		="";
	private String effect_date	="";
	//new changes
	private String effective_to_date	="";
	private String effective_from_date	="";
	//new changes
	
	private String entry_date	="";
	//private String isvalid 		="";
	private String[] chk;
	private String 	status;
	  private String HOSPITAL_CODE="";
	  
	  
	  
	  private String role_IdPrevious="";
	
	  
	  boolean retValue;

	public UmgmtGroupRoleMstBean() 
	{}
	
	
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public String getCombo1() 
	{
		return combo1;
	}

	public void setCombo1(String combo1) 
	{
		this.combo1 = combo1;
	}

	public java.lang.String getGroupCode( )
	{
		return groupCode;
	}

	public void setGroupCode( java.lang.String groupCode )
	{
		this.groupCode = groupCode;
	}

	public String getRole_id()
	{
		return ((role_id==null)?" ":role_id);
	}

	public String getStatus()
	{
		 return status;
	}

	public String getModule_id()
	{
		return ((module_id==null)?" ":module_id);
	}


	public String getSeat_id()
	{
		return ((seat_id==null)?" ":seat_id);
	}


	public String getEffect_date()
	{
		return ((effect_date==null)?" ":effect_date);
	}


	public String getEntry_date()
	{
		return ((entry_date==null)?" ":entry_date);
    }

	public void setRole_id(String ROLE_ID)
	{
		this.role_id=ROLE_ID;
	}

	public void setModule_id(String MODULE_ID)
	{
		this.module_id=MODULE_ID;
	}

	public void setSeat_id(String SEAT_ID)
	{
		this.seat_id=SEAT_ID;
	}

	public void setEffect_date(String EFFECT_DATE)
	{
		this.effect_date=EFFECT_DATE;
	}
	
	

	public void setEntry_date(String ENTRY_DATE)
	{
		this.entry_date=ENTRY_DATE;
	}

	public void setStatus(String sName)
	{
		this.status = sName;
	}

	public void setChk(String[] chk0)
	{
		chk = chk0;
	}
	
	public String getEffective_to_date() {
		return ((effective_to_date==null)?" ":effective_to_date);
		
	}
	public void setEffective_to_date(String effective_to_date) {
		this.effective_to_date = effective_to_date;
	}
	public String getEffective_from_date() {
		return ((effective_from_date==null)?" ":effective_from_date);
		
	}
	public void setEffective_from_date(String effective_from_date) {
		this.effective_from_date = effective_from_date;
	}
	public String getHOSPITAL_CODE() {
		return HOSPITAL_CODE;
	}
	public void setHOSPITAL_CODE(String hospital_code) {
		HOSPITAL_CODE = hospital_code;
	}
	public String getRole_IdPrevious() {
		return role_IdPrevious;
	}
	public void setRole_IdPrevious(String role_IdPrevious) {
		this.role_IdPrevious = role_IdPrevious;
	}
	public void initializeNewMode()
	{
		this.groupCode	  ="";
		this.role_id  	  ="";
		this.module_id	  ="";
		this.seat_id 	  ="";
		this.effect_date  ="";
		this.entry_date	  ="";
		this.status 	  ="1";
	}
	public String  insertRecord() throws Exception
	{
	String 	status1	=	"Group Name Role id  Already Exist,Please choose Other Group Name ,Module name and Role ID !";
		String groupname	="";
		String query="";
		String query1="";
        List masterList = new ArrayList();
        List lst = new ArrayList();
     
        
        try
		{
        query= "  select  t.GNUM_GROUP_CODE  from GBLT_GROUP_ROLE_MST t where " +
        		" t.GNUM_GROUP_CODE='"+this.groupCode+"' and t.GNUM_MODULE_ID='"+this.module_id+"' and  " +
        		" t.GNUM_ROLE_ID='"+this.role_id+"' "+
        		"   and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' ";
        boolean redundant = isRedundentName(query);
        System.out.println("value of redundant"+redundant);
        if(!redundant)
        {
        	System.out.println("inside if111111111111111111111111111");
        	
        	System.out.println("inside if222222222222222");
        	
        	
        query		=	"	INSERT INTO GBLT_GROUP_ROLE_MST ("+
    						" GNUM_GROUP_CODE, GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEAT_ID, GNUM_ISVALID, GNUM_GROUP_ROLE_SLNO, GDT_EFFECTIVE_TO, GNUM_HOSPITAL_CODE, GDT_ENTRY_DATE, GDT_EFFECTIVE_FRM)"+
    						" VALUES(?,?,?,?,?,?,NULL::timestamp,?,to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'),to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'))";
        	
        	System.out.println("query********"+query);
        		
        	System.out.println("values 1********"+this.groupCode);
        	System.out.println("value2*****"+this.role_id);
        	System.out.println("value3*****"+this.module_id);
        	System.out.println("isvalid&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*****"+this.status);
        	System.out.println("value5*****"+this.HOSPITAL_CODE);
        

        	lst.add(this.groupCode);
        	lst.add(this.role_id);
        	lst.add(this.module_id);
        	lst.add(this.seat_id);
        	lst.add(1);
        	lst.add(1);
        	//lst.add("");
        	lst.add(this.HOSPITAL_CODE);
        	masterList.add(lst);

        
           retValue= new HisGlobal.HisMethods().insertQuery(query,masterList);
        
        
        	status1	=	"Record Successfully Inserted !";
        
        }
        else
        {
        	
        	 query1= " select  t.GNUM_GROUP_CODE  from GBLT_GROUP_ROLE_MST t where " +
        	 		" t.GNUM_GROUP_CODE='"+this.groupCode+"' " +
        	 		" and t.GNUM_MODULE_ID='"+this.module_id+"' and " +
        	 		" t.GNUM_ROLE_ID='"+this.role_id+"' and t.GNUM_ISVALID!='0'"+
        	 		"   and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' ";
         	boolean redundant1 = isRedundentName(query1);
         	
         	
         	  if(redundant1)
               {
         		  
         		 System.out.println("inside else");
          	   status1="Group Name Role id  Already Exist,Please choose Other Group Name ,Module name and Role ID";
               }
         	  
         	  else {
         		  
         		  
         		 query		=	"	INSERT INTO GBLT_GROUP_ROLE_MST ("+
					" GNUM_GROUP_CODE, GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEAT_ID, GNUM_ISVALID, GDT_EFFECTIVE_TO, GNUM_HOSPITAL_CODE, GDT_ENTRY_DATE, GDT_EFFECTIVE_FRM, GNUM_GROUP_ROLE_SLNO)"+
					" VALUES(?,?,?,?,?,NULL::timestamp,?,to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'),to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'),(select max(t.GNUM_GROUP_ROLE_SLNO) from GBLT_GROUP_ROLE_MST t where t.GNUM_GROUP_CODE='"+this.groupCode+"' and t.GNUM_MODULE_ID='"+this.module_id+"' and  t.GNUM_ROLE_ID='"+this.role_id+"')+1)";
		
	
				lst.add(this.groupCode);
				lst.add(this.role_id);
				lst.add(this.module_id);
				lst.add(this.seat_id);
				lst.add(this.status);
				//lst.add("");
				lst.add(Integer.parseInt(this.HOSPITAL_CODE));
				masterList.add(lst);

					retValue= new HisGlobal.HisMethods().insertQuery(query,masterList);
         		  
				status1	=	"Record Successfully Inserted !";
         		  
         		  
         	  }
        	
    	 
        }
        
		}
        catch(Exception e)
   		{   
        	
        	status1 = "Error in inserting a record : " + e;
			System.out.println(status1);
   		}
       return status1;
     }
	
	
	// method used earlier***********************************************************************************
	/*
	public String insertRecord()
	{
		status	=	"Record Successfully inserted !";
		java.sql.CallableStatement cls = null;
		Connection conn = null; 
        try
		{
			conn = super.getConnection();
			String sql="{call ahis_user.grp_role_ins(?,?,?,?,?,?,?)}";
			System.out.println("calling procedure ahis_user.grp_role_ins");
			
			cls = conn.prepareCall(sql);
			cls.setInt(1,Integer.parseInt(this.groupCode));
			cls.setInt(2,Integer.parseInt(this.role_id));
			cls.setInt(3,Integer.parseInt(this.module_id));
			cls.setInt(4,Integer.parseInt(this.seat_id));
			cls.setString(5,this.getSysDate());
			cls.setInt(6,Integer.parseInt(this.isvalid));
			cls.setString(7,this.effect_date);

			cls.execute();
            conn.commit();
		}
        catch(Exception e)
   		{   
        	try { conn.rollback(); } catch(SQLException se) {}
   			status = "Error in inserting a record : " + e;
			System.out.println(status);
   		}
        finally
        {   try{
	        	if ( cls != null ) cls.close();
				super.closeConnection(conn);
            }
	        catch(SQLException sa) {}

	        conn = null;
        } 
		return status;
	}
	   */
	
	
	/*public String deleteRecord()throws Exception
	{
		status	=	"Deletion Not Allowed...!!";
		return status;
		
		
		
		
		
		
	} */
	
	
	// method used earlier***********************************************************************************
	

	public boolean deleteRecord(String delStatus)
    {
		
		int i;
		
		int GroupCode;
		int Role_id;
		int Module_id;
	   	StringTokenizer 	st = null;
	    Connection con = null;
	    
	    String query="";
		String query1="";
        List masterList = new ArrayList();
        List lst = new ArrayList();
	    boolean rt =false ;
		CallableStatement cls = null ;
		try
    	{
			
			System.out.println("value of chk legth************"+chk.length);
	        for(i=0; i<chk.length; i++)
			{
	            st=new StringTokenizer(chk[i],"^");
	            GroupCode=Integer.parseInt(st.nextToken());
	            Role_id= Integer.parseInt(st.nextToken());
	            Module_id= Integer.parseInt(st.nextToken());
	           
	            
	            query		=" UPDATE GBLT_GROUP_ROLE_MST SET GNUM_ISVALID =?  where " +
	            		     " GNUM_GROUP_CODE=? and GNUM_ROLE_ID=? and GNUM_MODULE_ID=? "+
	            		     "   and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' ";




	        lst.add(delStatus);
			lst.add(GroupCode);
			lst.add(Role_id);
			lst.add(Module_id);
			
			masterList.add(lst);

			rt= new HisGlobal.HisMethods().insertQuery(query,masterList);
			lst.clear();
			masterList.clear();
			deleteChildRecord(delStatus);
			System.out.println("value of status"+rt);
	        }
	        
    	}
	        
	        catch(Exception e)
	   		{   
	        	
	        	rt=false;
				System.out.println("e**************"+e.getMessage());
	   		}
	       return rt;
	        
	         
	        
		}
	
	
	public boolean deleteChildRecord(String delStatus)
    {
		
		int i;
		int GroupCode;
		int Role_id;
		int Module_id;
	   	StringTokenizer 	st = null;
	    Connection con = null;
	    
	    String query="";
		String query1="";
        List masterList = new ArrayList();
        List lst = new ArrayList();
	    boolean rt =false ;
		CallableStatement cls = null ;
		try
    	{
			
			System.out.println("value of chk legth************"+chk.length);
	        for(i=0; i<chk.length; i++)
			{
	            st=new StringTokenizer(chk[i],"^");
	            GroupCode=Integer.parseInt(st.nextToken());
	            Role_id= Integer.parseInt(st.nextToken());
	            Module_id= Integer.parseInt(st.nextToken());
	           
	            
	            query		=" UPDATE gblt_seat_role_mst SET GBL_ISVALID =?  where " +
	            		     " GNUM_ROLE_ID=? and GNUM_MODULE_ID=? "+
	            		     "   and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' " +
	            		     "and GNUM_SEATID in(select GNUM_SEATID from "+
	            		     "gblt_seat_mst where GNUM_GROUP_CODE=? AND GNUM_HOSPITAL_CODE='"+this.HOSPITAL_CODE+"' )";
	         
	        lst.add(delStatus);
	        lst.add(Role_id);
	        lst.add(Module_id);
			lst.add(GroupCode);
			masterList.add(lst);

			rt= new HisGlobal.HisMethods().insertQuery(query,masterList);
			lst.clear();
			masterList.clear();
			System.out.println("value of status"+rt);
	        }
	        
    	}
	        
	        catch(Exception e)
	   		{   
	        	
	        	rt=false;
				System.out.println("e**************"+e.getMessage());
	   		}
	       return rt;
	        
	         
	        
		}
	
	
	
	
	

	public List initializeUpdateMode()throws Exception
	{
		
	 	StringTokenizer 	st = null;
	    String query ="";
	    
	    
	    String code = new String("");
	    List l1 = new ArrayList();
		code 	= chk[0].substring(0,chk[0].length()-1);
		
		  st=new StringTokenizer(code,"^");
		  this.groupCode=String.valueOf(Integer.parseInt(st.nextToken()));
		  this.role_id= String.valueOf(Integer.parseInt(st.nextToken()));
		  this.module_id= String.valueOf(Integer.parseInt(st.nextToken()));
		
		
		
		  /*		
		this.groupCode=code.substring(0,3);
		this.role_id = code.substring(4,8);
		this.module_id = code.substring(9);
		
		*/
		
		System.out.println("*******************"+code);
		System.out.println("1"+this.groupCode);
		System.out.println("2"+this.role_id);
		System.out.println("3"+this.module_id);
			
		query=		"	SELECT (select GSTR_GROUP_NAME from GBLT_GROUP_MST where GNUM_GROUP_CODE=A.GNUM_GROUP_CODE and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' )GROUP_NAME"+
					"	,(select GSTR_ROLE_NAME from GBLT_ROLE_MST where GNUM_ROLE_ID=A.GNUM_ROLE_ID and GNUM_MODULE_ID =a.GNUM_MODULE_ID and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' )ROLE_NAME,"+
					//"	(select GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID=A.GNUM_MODULE_ID and  gnum_hospital_code='100')MODULE_NAME,"+
					"	(select GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID=A.GNUM_MODULE_ID "+
					//"and  gnum_hospital_code='"+this.HOSPITAL_CODE+"'" +
					")MODULE_NAME,"+
					"	A.GDT_ENTRY_DATE,to_char(A.GDT_EFFECTIVE_TO,'DD-Mon-YYYY'),to_char(A.GDT_EFFECTIVE_FRM,'DD-Mon-YYYY') from GBLT_GROUP_ROLE_MST A"+
					"	WHERE A.GNUM_GROUP_CODE ='"+this.groupCode+"'AND A.GNUM_ROLE_ID='"+this.role_id+"' and A.GNUM_MODULE_ID='"+this.module_id+"' and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' ";
							
		System.out.println("initializeUpdateMode---->"+query);
		
		HisResultSet rst = getRecord(query);
			if(rst.next())
			{
				this.groupName		=	rst.getString(1);
				this.roleName  		= 	rst.getString(2);
				this.moduleName 	= 	rst.getString(3);
				this.entry_date		=	rst.getString(4);
				this.effective_to_date	=	rst.getString(5);
				this.effective_from_date	=	rst.getString(6);
			}
		
			//this.role_IdPrevious=this.role_id;
			
			System.out.println("44444444444444"+this.roleName);
		
			l1.add(groupName);
			l1.add(roleName);
			l1.add(moduleName);
	
			
			
			return l1;
		}
	
	
	// metod used earlier for updation*********************************************************************************
	
	/*
	public List initializeUpdateMode()throws Exception
	{
	    String query ="";
	    String code = new String("");
	    List l1 = new ArrayList();
		code 	= chk[0].substring(0,chk[0].length()-1);
		this.groupCode=code.substring(0,3);
		this.role_id = code.substring(4,8);
		this.module_id = code.substring(9);
		
		
		
		System.out.println("*******************"+code);
		System.out.println("1"+this.groupCode);
		System.out.println("2"+this.role_id);
		System.out.println("3"+this.module_id);
			
		query=		"	SELECT (select GSTR_GROUP_NAME from GBLT_GROUP_MST where GNUM_GROUP_CODE=A.GNUM_GROUP_CODE)GROUP_NAME"+
					"	,(select GSTR_ROLE_NAME from GBLT_ROLE_MST where GNUM_ROLE_ID=A.GNUM_ROLE_ID and GNUM_MODULE_ID =a.GNUM_MODULE_ID )ROLE_NAME,"+
					"	(select GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID=A.GNUM_MODULE_ID)MODULE_NAME,"+
					"	A.GDT_ENTRY_DATE,to_char(A.GDT_EFFECTIVE_TO,'DD-Mon-YYYY'),to_char(A.GDT_EFFECTIVE_FRM,'DD-Mon-YYYY') from GBLT_GROUP_ROLE_MST A"+
					"	WHERE A.GNUM_GROUP_CODE ='"+this.groupCode+"'AND A.GNUM_ROLE_ID='"+this.role_id+"' and A.GNUM_MODULE_ID='"+this.module_id+"'";
							
			HisResultSet rst = getRecord(query);
			if(rst.next())
			{
				this.groupName		=	rst.getString(1);
				this.roleName  		= 	rst.getString(2);
				this.moduleName 	= 	rst.getString(3);
				this.entry_date		=	rst.getString(4);
				this.effective_to_date	=	rst.getString(5);
				this.effective_from_date	=	rst.getString(6);
			}
		
			//this.role_IdPrevious=this.role_id;
			
			System.out.println("44444444444444"+this.roleName);
			//System.out.println("value if is valid**************"+this.isvalid);
			l1.add(groupName);
			l1.add(roleName);
			l1.add(moduleName);
		//	l1.add(entry_date);
		//	l1.add(effective_to_date);
		//	l1.add(effective_from_date);
			
			
			return l1;
		}
	*/ 

	
	// metod used earlier for updation*********************************************************************************
	
	
	
	    public boolean updateRecord( HttpServletRequest request )throws Exception
		{
	    	//String 	status1	=	"Group Name Role id  Already Exist,Please choose Other Group Name ,Module name and Role ID !";
	    	String serial_number="";
	    	String serial_numberPrevious="";
	    	boolean flag=false;
	    	
	    	int newserial_number=1;
	    	
			String query="";
			String query1="";
			String query2="";
			String queryP="";
	        List masterList = new ArrayList();
	        List masterList1 = new ArrayList();
	        List masterList2 = new ArrayList();
	        List lst = new ArrayList();
	        List lst1 = new ArrayList();
	        List lst2 = new ArrayList();
	        
	        System.out.println("****************************"+this.groupCode);
	        System.out.println("****************************"+this.module_id);
	        System.out.println("****************************"+this.role_id);
	        
	        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&****************************"+this.role_IdPrevious);
	        try
			{
	        query= "select  max(t.GNUM_GROUP_ROLE_SLNO) from GBLT_GROUP_ROLE_MST t where t.GNUM_GROUP_CODE='"+this.groupCode+"' and t.GNUM_MODULE_ID='"+this.module_id+"' and  t.GNUM_ROLE_ID='"+this.role_id+"' and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' ";
	        
	        queryP= "select  max(t.GNUM_GROUP_ROLE_SLNO) from GBLT_GROUP_ROLE_MST t where t.GNUM_GROUP_CODE='"+this.groupCode+"' and t.GNUM_MODULE_ID='"+this.module_id+"' and  t.GNUM_ROLE_ID='"+this.role_IdPrevious+"' and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' ";
	      
	      
	        
	        HisResultSet rst = getRecord(query);
	        
	        if(rst.next())
			{
				 serial_number		=	rst.getString(1);
				
			}
	        
	        	
	        	if(serial_number==null)
	        		
	        	{
	        		
	        		 serial_number="1";
	        		 
	        		 newserial_number=Integer.parseInt(serial_number);
	        		
	        	}
	        	
	        	
	        	else{
	        		
	        		
	        		 newserial_number=Integer.parseInt(serial_number)+1;
	        		
	        		
	        	}
	        	
	        	 HisResultSet rst1 = getRecord(queryP);
	        	 
	        	 if(rst1.next())
	 			{
	 				 serial_numberPrevious		=	rst1.getString(1);
	 				
	 			}
	        
	        	System.out.println("  serial mumber previous****************************"+serial_numberPrevious);
	        	
	        	
	        	
	        	query		=	"	INSERT INTO GBLT_GROUP_ROLE_MST ("+
	    						" GNUM_GROUP_CODE, GNUM_ROLE_ID, GNUM_MODULE_ID, GNUM_SEAT_ID, GNUM_ISVALID, GNUM_GROUP_ROLE_SLNO, GDT_EFFECTIVE_TO, GNUM_HOSPITAL_CODE, GDT_ENTRY_DATE, GDT_EFFECTIVE_FRM)"+
	    						" VALUES(?,?,?,?,?,?,NULL::timestamp,?,to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'),to_date(to_char(sysdate,'dd-mon-yyyy'),'dd-mon-yyyy'))";
	        	
	        	System.out.println("query********"+query);
	        		
	        	System.out.println("values 1********"+this.groupCode);
	        	System.out.println("value2*****"+this.role_id);
	        	System.out.println("value3*****"+this.module_id);
	        		System.out.println("isvalid status*******************************************"+this.status);
	        	System.out.println("value5*****"+this.HOSPITAL_CODE);
	        

	        	lst.add(this.groupCode);
	        	lst.add(this.role_id);
	        	lst.add(this.module_id);
	        	lst.add(this.seat_id);
	        	lst.add(this.status);
	        	lst.add(newserial_number);
	        	//lst.add("");
	        	lst.add(this.HOSPITAL_CODE);
	        	
	        	masterList.add(lst);

	        	
	        	flag= new HisGlobal.HisMethods().insertQuery(query,masterList);
	        	System.out.println(" flag 11111111111****************************"+flag);
	        	
	        	System.out.println(" role id new****************************"+this.role_id);
	        	System.out.println(" role id previous****************************"+this.role_IdPrevious);
	        	
	        	
	        	
	         		
	        	 query1="UPDATE GBLT_GROUP_ROLE_MST t set t.GNUM_ISVALID=?,t.GDT_EFFECTIVE_TO=NULL::timestamp where t.GNUM_GROUP_CODE=? and t.GNUM_ROLE_ID=? and t.GNUM_MODULE_ID=? and t.gnum_group_role_slno=? and  gnum_hospital_code='"+this.HOSPITAL_CODE+"'";
	        	
	        	lst1.add(0);
	        	lst1.add(this.groupCode);
	        	lst1.add(this.role_IdPrevious);
	        	lst1.add(this.module_id);
	        	lst1.add(serial_numberPrevious);
	        	System.out.println("query********"+query1);
	        	
	        	masterList1.add(lst1);
	        	
	        	flag= new HisGlobal.HisMethods().insertQuery(query1, masterList1);
	        	System.out.println(" flag 22222222****************************"+flag);
	        	
	        	
	        	 query2		=" UPDATE gblt_seat_role_mst SET GBL_ISVALID =?  where " +
    		     " GNUM_ROLE_ID=? and GNUM_MODULE_ID=? "+
    		     "   and  gnum_hospital_code='"+this.HOSPITAL_CODE+"' " +
    		     "and GNUM_SEATID in(select GNUM_SEATID from "+
    		     "gblt_seat_mst where GNUM_GROUP_CODE=? AND GNUM_HOSPITAL_CODE='"+this.HOSPITAL_CODE+"' )";
 

		        	lst2.add(this.status);
		        	lst2.add(this.role_IdPrevious);
		        	lst2.add(this.module_id);
		        	lst2.add(this.groupCode);
		        	System.out.println("query********"+query2);
		        	
		        	masterList2.add(lst2);
		        	
		        	flag= new HisGlobal.HisMethods().insertQuery(query2, masterList2);
		        	System.out.println(" flag 333333333333****************************"+flag);
		        	
	        		 
	         		 
			}
	        catch(Exception e)
	   		{   
	        	flag=false;
	   		}
	       
	        
	        return flag;
		}
	
		
		
		
		
		
		
		

	

		
}// end of class