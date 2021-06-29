
package usermgmt.masters;
import usermgmt.*;
import java.sql.*;
import java.util.*;
import HisGlobal.*;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

public class UmgmtGroupMstBean extends FuncLib
{
	private String combo1		  =	"";
	private int     group_code;
	//private String 	hospitalcode  =	"";
	private String  group_name    =	"";
	private String  effect_date   =	"";
	private int 	isvalid=1;
	private String 	seatId        =	"";
	private String 	status        =	"";
	private String hospitalcode="";
	boolean retValue;
	private String[] chk;

    public int 		getGroup_code()
    {
        return group_code;
    }
	public String 	getGroup_name()
    {
        return (group_name == null)?"":group_name;
    }

	public String 	getEffect_date()
    {
        return (effect_date == null)?"":effect_date;
    }
	public int 		getIsvalid()
    {
        return isvalid;
    }

	public String 	getSeatId()
    {
        return seatId;
    }

    public String getStatus()
	{
		return status;
	}

    public void setCombo1( java.lang.String combo1 )
 	{
 		 this.combo1 = combo1;
  	}

    public void setChk(String[] chk0)
	{
		chk = chk0;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public void setSeatId(String seatId)
	{
		this.seatId=seatId;
	}

    public void setGroup_code(int gCode)
    {
        group_code = gCode;
    }

    public void  setGroup_name(String gName)
    {
        group_name = gName;
    }

    public void 	setEffect_date(String effDate)
    {
        effect_date = effDate;
    }

    public void 	setIsvalid(int valid)
    {
        isvalid = valid;
    }

	public java.lang.String getCombo1( )
 	{
  		return combo1;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}

    /**
		other methods
	*/

	public boolean  insertRecord(HttpServletRequest request) throws Exception
	{
		String groupname	="";
		String query="";
        List masterList = new ArrayList();
        List lst = new ArrayList();
        query= " select GSTR_GROUP_NAME from GBLT_GROUP_MST WHERE " +
        		" UPPER(GSTR_GROUP_NAME)=UPPER('"+this.group_name+"')and GNUM_ISVALID>0 and " +
        		"GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE='" +this.hospitalcode+"' " ;
        
        
      //  System.out.println("insert duplicatse check "+query);
        
        boolean redundant = isRedundentName(query);
        //status="Record Successfully Inserted";
        if(!redundant)
        {
        	if(this.group_name!=null)
			{	if((this.group_name.length())<2)
				groupname	=this.group_name.substring(0,1).toUpperCase();
				else
				groupname=this.group_name.substring(0,1).toUpperCase()+this.group_name.substring(1).toLowerCase();
			}
			else
			{
				System.out.println("Your Group name is null inside Group Bean");
				groupname ="";
				
			}
        	System.out.println("Hospitalcode is   "+request.getSession().getAttribute("HOSPITAL_CODE"));
        	group_code 	= Integer.parseInt(getNextNo("GNUM_GROUP_CODE","GBLT_GROUP_MST","101",(String)request.getSession().getAttribute("HOSPITAL_CODE")));
        	query		=	"	INSERT INTO GBLT_GROUP_MST ("+
    						" GNUM_GROUP_CODE,GSTR_GROUP_NAME,GDT_EFFECT_DATE,GNUM_ISVALID ,GNUM_SEAT_ID ,GDT_ENTRY_DATE,GNUM_HOSPITAL_CODE )"+
    						" VALUES(?,?,to_date(?,'dd-mon-yyyy'),?,?,sysdate,'"+ this.hospitalcode +"')";
        	
        	/*
        	  query		=	"	INSERT INTO GBLT_GROUP_MST ("+
    						" GNUM_GROUP_CODE,GSTR_GROUP_NAME,GDT_EFFECT_DATE,GNUM_ISVALID ,GNUM_SEAT_ID ,GDT_ENTRY_DATE,GNUM_HOSPITAL_CODE )"+
    						" VALUES(?,?,?,?,request.getSession().getAttribute("SEATID")+"',sysdate,'"+ request.getSession().getAttribute("HOSPITALCODE")+"')";
        	 */
        		
        	
        	lst.add(String.valueOf(group_code));
        	lst.add(groupname);
        	lst.add(String.valueOf(effect_date));
        	lst.add(String.valueOf(isvalid));
        	lst.add(seatId);
     
        	masterList.add(lst);

           retValue= new HisGlobal.HisMethods().insertQuery(query,masterList);
           System.out.println("retvalue"+retValue);
        }
        else
        {
    	   status="Group Name Already Exist,Please Enter Other Group Name ";
    	   retValue=false;
        }
       return retValue;
     }


	public boolean updateRecord(HttpServletRequest request )throws Exception
	 { 
		String query    		=	"";
		String query1    		=	"";
		String pK      			=	"";
	    String oldRecord    	= 	"";
	    String oldVal     		= 	"";
	    String newVal     		= 	"";
		boolean flag     		= false;
		boolean redundant    	= false;
	    boolean redundantname  	= false;
	    boolean isRun     		= false;
	    PreparedStatement ps1 	= null;
	    Connection conn    		=  null ;
	    String hospiatlCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
	   
	   
	   pK    		= String.valueOf(this.group_code);
	   oldRecord   	=	"	select 'GSTR_GROUP_NAME'||'^'||GSTR_GROUP_NAME||'#'||'GDT_EFFECT_DATE'||'^'||GDT_EFFECT_DATE ||'#'||'GNUM_ISVALID'||'^'||GNUM_ISVALID from GBLT_GROUP_MST where GNUM_GROUP_CODE='"+this.group_code+"'"+
	              		"	and GNUM_ISVALID='1' and  GNUM_HOSPITAL_CODE='" + this.hospitalcode +"' ";
	   oldVal   	= super.getField(oldRecord);
	   newVal   	= "'GSTR_GROUP_NAME^"+this.group_name+"#GDT_EFFECT_DATE^"+this.effect_date+"#GNUM_ISVALID^"+this.isvalid;
	   
	   query1   	= " select GSTR_GROUP_NAME from GBLT_GROUP_MST WHERE " +
	   	              " UPPER(GSTR_GROUP_NAME)=UPPER('"+this.group_name+"')and GNUM_ISVALID>0 and " +
	   	              " GNUM_ISVALID<3 and GNUM_GROUP_CODE<>'"+this.group_code+"' and  " +
	   	              " GNUM_HOSPITAL_CODE='" + this.hospitalcode+"' ";
	   

	   System.out.println("query 1"+query1);
	   
	   
	   redundantname = isRedundentName(query1);
	   if(redundantname)
	   {
		   this.status ="Group Name Already Exist,Please Enter Other Group Name.......! ";
		   // if Record  Alredy Exist 
		   /*query1 =  "	select GNUM_ISVALID from GBLT_GROUP_MST WHERE " +
		   			" GSTR_GROUP_NAME=initcap('"+this.group_name+"')and GNUM_ISVALID>0 and " +
		   			" GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE='" + this.hospitalcode +"' ";
		   
		   System.out.println("query 2"+query1);
		   redundant =  super.isRecordExist(query1,this.isvalid);// Calling Funclib Method
		   
	       if(redundant) 
	       {
	    	   this.status ="Group Name Already Exist,Please Enter Other Group Name.......! ";
	       }
	       else
	       {
	    	   query	=	" UPDATE GBLT_GROUP_MST SET GSTR_GROUP_NAME=initcap('"+this.group_name+"'), GDT_EFFECT_DATE='"+this.effect_date+"',GNUM_ISVALID='"+this.isvalid+"'"+

	    	   				"  WHERE GNUM_GROUP_CODE ='"+this.group_code+"' and GNUM_HOSPITAL_CODE='"+this.hospitalcode+"'";

	    	   isRun = true;
	        }*/
	      }
	   else
	   {
		   query	=	" UPDATE GBLT_GROUP_MST SET GSTR_GROUP_NAME=initcap('"+this.group_name+"'), GDT_EFFECT_DATE='"+this.effect_date+"',GNUM_ISVALID='"+this.isvalid+"'"+

		   				"  WHERE GNUM_GROUP_CODE =" + this.group_code + "and GNUM_HOSPITAL_CODE=" +this.hospitalcode;

		   				

		   isRun = true; 
	   }
	  if(isRun)
	  {  
		  conn     =  new HisMethods().getConnection();
		  conn.setAutoCommit(false); 
		  try
		  {
			  if(!super.updateRecord(query))
			  {
				  System.out.println(" Updation Failed ......! ");  
	          }
			  ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_GROUP_MST").updateAuditLog(request,conn);
			  conn.commit();
			  flag=true;
		  }
		  catch( Exception e )
		  {
			  System.out.println("exception in excution ="+e);
			  this.status = "Record cannot be updated "+e;
			  try
			  { 
				  conn.rollback();  
			  } 
			  catch(Exception se ) 
			  {
				  System.out.println("error in try block..."+se);
			  }
			  
		  }
		  finally 
		  {
			  try
			  {
				  if (ps1!=null) 
					  	ps1.close();
				  if(conn!=null)
					  conn.close();
	       
			  }
			  catch(Exception se1) 
			  { 
				  System.out.println("Exception in finally block"+se1);
			  }
		  }
	  }
	  else
	  {
		  System.out.println("No need to open connection");
		  this.status = " Group Name Already Exist,Please Write Other Group Name......! ";
	  }
	     return flag;
	 }
	 
	public void initializeNewMode()
	{
		group_name	=	"";
	}
    
    public List initializeUpdateMode() throws Exception
	{
		String query ="";
		List l1=new ArrayList();
		group_code = Integer.parseInt(chk[0].substring(0,chk[0].length()-1));

        query =	"	SELECT GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME),"+
			   	"	to_char(GBLT_GROUP_MST.GDT_EFFECT_DATE,'DD-Mon-YYYY'),GNUM_SEAT_ID"+
			   	" 	FROM GBLT_GROUP_MST"+
				" 	where GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' and  GNUM_GROUP_CODE="+group_code;

		HisResultSet rst = getRecord(query);
		if(rst.next())
		{
			group_name  = rst.getString(2);
			effect_date = rst.getString(3);
		    seatId=rst.getString(4);
        }
		l1.add(group_name);
		l1.add(effect_date);
		
		return l1;
	}


	public boolean deleteRecord(String delStatus)
    {
		
		int i;
	   	StringTokenizer 	st = null;
	    Connection con = null;
	    boolean rt =false ;
		CallableStatement cls = null ;
		try
    	{
	        for(i=0; i<chk.length; i++)
			{
	            st=new StringTokenizer(chk[i],"^");
	            group_code=Integer.parseInt(st.nextToken());
	            con  = super.getConnection();
                con.setAutoCommit(false);
	            String sql="{call ahis_user.grp_priv_change(?,?,?)}";
	            cls = con.prepareCall(sql);
	            cls.setString(1,String.valueOf(this.group_code));
	            cls.setString(2,String.valueOf(delStatus));
	    	    cls.setString(3,String.valueOf(this.hospitalcode));
	           
	            cls.execute();
	            con.commit();
	            rt=true;
	            }
	         
		}
	  	catch(Exception e)
	 	{  
	  		rt = false;
			System.out.println("exception is = "+e);
		  	try
		  	{
			 	if ( con !=null )
			 		con.rollback();
		   	}
		   catch(SQLException se )
		   {
			 System.out.println("error in try block..."+se);
		   }
		  
		}
		finally
	  	{
			try
			{
				if (cls !=null)
				cls.close();
				super.closeConnection(con);
			}
			catch(SQLException se1 )
			{
				System.out.println("error in finally block"+se1);
			}
		}
     	return rt;
	}
	/*public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}*/
	
}
