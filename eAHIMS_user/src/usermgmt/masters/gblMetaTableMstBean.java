

package usermgmt.masters;

import usermgmt.*;

import java.sql.*;
import java.util.*;

import HisGlobal.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class gblMetaTableMstBean extends FuncLib
{
	
    private String combo1		  ="";
	private String  moduleName    ="";
	private String  moduleId      ="";
    private int 	isvalid       =1;
    private String 	hospitalCode  ="";
	private String 	seatId        ="";
	private String 	status        ="";
	boolean retValue;
	private String[] chk;
	HttpSession session= null;
	
	
    public java.lang.String getCombo1( ){return combo1;}
    public String		getModuleId()	{return moduleId;}
	public String 	getModuleName()	{return (moduleName == null)?"":moduleName;		}
	public int 		getIsvalid()	{return isvalid;}
	public String 	getSeatId()	{return seatId;}
    
	public String getHospitalCode() {
		
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
		System.out.println("hos code--"+hospitalCode);
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
    public void 	setModuleId(String moduleId)		
    { 
        this.moduleId = moduleId; 
    }

    public void 	setModuleName(String moduleName)
    {
        this.moduleName = moduleName; 
    }

	public void 	setIsvalid(int valid)			 { isvalid = valid; }

	public boolean  insertRecord() throws Exception
	{
		 String modulename	="";
		String query="";
		List masterList = new ArrayList();
		List lst = new ArrayList();
		query= "select  GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST WHERE UPPER(GSTR_MODULE_NAME) =UPPER('"+this.moduleName.trim()+"')"+
				"and GBL_ISVALID>0 and GBL_ISVALID<3 and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'"; 
		
		
		boolean redundant=isRedundentName(query);
		try{
		 if(!redundant)
	     {
			 
			 if(this.moduleName!=null)
				{	if((this.moduleName.length())<2)
					modulename	=this.moduleName.substring(0,1).toUpperCase();
					else
					modulename=this.moduleName.substring(0,1).toUpperCase()+this.moduleName.substring(1).toLowerCase();
				}
				else
				{
					System.out.println("Your Module name is null inside Module Bean");
					modulename ="";
					
				}
				
			 
			 moduleId = getNextNo("GNUM_MODULE_ID","GBLT_METATABLE_TYPE_MST","11",this.hospitalCode);
			 
			 query=	"	INSERT INTO GBLT_METATABLE_TYPE_MST ("+
				"	GNUM_MODULE_ID ,GSTR_MODULE_NAME,GBL_ISVALID ,GNUM_SEAT_ID ,GDT_ENTRY_DATE , GNUM_HOSPITAL_CODE )"+        
				" 	VALUES(?,?,?,?,sysdate, ?)";
				
			 System.out.println("Module insert"+query);
			 System.out.println("module id --"+moduleId);
			 System.out.println("module id length--"+moduleId.length());
			 lst.add(String.valueOf(moduleId));
			 lst.add(modulename.trim());
			 lst.add(String.valueOf(isvalid));
			 lst.add(seatId);
			 lst.add(this.hospitalCode);
			 masterList.add(lst);
			 try{
			 retValue= new HisGlobal.HisMethods().insertQuery(query,masterList);
			 }
			 catch (Exception e) {
				System.out.println("exception in insert--"+e);
			}
			 if(retValue==false)
			 {
				// System.out.println("retValue-"+retValue);
				 this.status = "Record cannot be Inserted !";
			 }
			 else{
				// System.out.println("retValue-"+retValue);
				 this.status = "Record Successfully Inserted !";
			 }
			
	     }
	       else 
	        {
	        	retValue=false;
	        	status="Module Name Already Exist,Please Enter Other Module Name !";
	        }
	        
		}
		catch (Exception e) {
			this.status = "Record can not be Inserted !";
			System.out.println("Exception in insertRecord()-->"+e);
		}
		return retValue;
     }  
        
	 public void initializeNewMode()
		{
			this.moduleName	=	"";
		}
	    
	public boolean updateRecord( HttpServletRequest request )throws Exception
	{
		 
		String query			=	"";
		String	query1			=	"";
		String query2			=	"";
		String oldRecord 		=	"";
		String oldVal			=	"";
		String newVal 			=	"";
		String pK 				=	"";	
		Connection conn			=	null; 
		PreparedStatement ps1	=	null;
		 Statement st			=	null;
		boolean flag			=	false;
		boolean	redundant		=	false;
		boolean	redundantname	=	false;
		boolean isRun			=	false;
		
		pK=this.moduleId;
		
        oldRecord		=	"select 'GSTR_MODULE_NAME'||'^'||GSTR_MODULE_NAME||'#'||'GBL_ISVALID'||'^'||GBL_ISVALID from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID='"+this.moduleId+"' and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'";           
        oldVal			= 	super.getField(oldRecord); 
        newVal	 		= 	"'GSTR_MODULE_NAME^"+this.moduleName+"#GBL_ISVALID^"+this.isvalid;
        
        query1			=	"select  GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST WHERE UPPER(GSTR_MODULE_NAME) =UPPER('"+this.moduleName.trim()+"')"+
							"and GBL_ISVALID>0 and GBL_ISVALID<3 and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and  GNUM_MODULE_ID!='"+this.moduleId+"' ";
        
      
        redundantname	=	super.isRedundentName(query1);	
        if(redundantname)
        {
        	query2		=	"	select GBL_ISVALID from GBLT_METATABLE_TYPE_MST WHERE UPPER(GSTR_MODULE_NAME) =UPPER('"+this.moduleName.trim()+"')"+
							"	and GBL_ISVALID>0 and GBL_ISVALID<3 and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and  GNUM_MODULE_ID!='"+this.moduleId+"' ";
        	
        	redundant	=	super.isRecordExist(query2,this.isvalid);
        	
        	if(redundant)
        	{
        		this.status	=	" Module Name Already Exist , Please  Write  Other Module Name......!";	
        	}
        	else
        	{
        		query	=	"	UPDATE GBLT_METATABLE_TYPE_MST SET GSTR_MODULE_NAME='"+this.moduleName.trim()+"',GBL_ISVALID='"+this.isvalid+"'"+
							"	WHERE GNUM_MODULE_ID =" + this.moduleId +" and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'";
        		isRun	=		true;
        	}
        }
        else
        {
        	query	=	"	UPDATE GBLT_METATABLE_TYPE_MST SET GSTR_MODULE_NAME='"+this.moduleName.trim()+"',GBL_ISVALID='"+this.isvalid+"'"+
	 					"	WHERE GNUM_MODULE_ID =" + this.moduleId +" and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'";
        	isRun	=		true;
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
   		   		ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_METATABLE_TYPE_MST").updateAuditLog(request,conn);
   		   		conn.commit(); 
   		   		conn.setAutoCommit(true);
   		   		flag=true;
   		   	this.status = "Record Successfully Updated !";
   		   	}
   		   	catch(Exception e)
   		   	{
   		   		this.status = "Record can not be Updated !";
   		   		System.out.println("Exception in update record "+e);
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
   		   			if(conn!=null)
   		   				conn.close();
   		   			if(st!=null)
   		   				st.close();
   		   			if(ps1!=null)
   		   				ps1.close();
   		   			
   		   		}
   		   		catch(Exception e)
   		   		{
   		   		System.out.println("Exception in finally block"+e);
   		   		}
   		   	
   		   	}
        }
   		else
        {
        	this.status	=	"	Module Name Already Exist , Please  Write  Other Module Name......!";
        }
        return flag;
	}     
            
    public List initializeUpdateMode() throws Exception
	{
        String query ="";
        List l1=new ArrayList();

        moduleId =chk[0].substring(0,chk[0].length()-1);

        query = " SELECT GNUM_MODULE_ID, initcap(GSTR_MODULE_NAME),"+
                " GNUM_SEAT_ID"+
                " FROM GBLT_METATABLE_TYPE_MST "+
                " where GNUM_MODULE_ID="+moduleId+" and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' " ;
        try{
        HisResultSet rst = getRecord(query);
        if(rst.next())
        {
            moduleId	= rst.getString(1);
            moduleName  = rst.getString(2);
            seatId=rst.getString(3);
        }

        l1.add(String.valueOf(moduleId));
        l1.add(moduleName);
        l1.add(seatId);
        }
        catch (Exception e) {
			 this.status = "Exception while updating--> "+e;
		}
		return l1;
    }

	public String deleteRecord()throws Exception
		{
			/*status	=	"You are not allowed to delete the Module...!!";
			return status;*/
		status	=	"Successfully deleted !";
		int i	=	0;
		
		String query=  " UPDATE GBLT_METATABLE_TYPE_MST"+
						" SET"+
						" GBL_ISVALID= 0"+
						" WHERE GNUM_MODULE_ID=? and GNUM_HOSPITAL_CODE=?";
		String status="";
			
		
		Connection conn	=	super.getConnection();
		PreparedStatement 	ps = conn.prepareStatement(query);
		StringTokenizer 	st = null;
		try
		{
			for(i=0; i<chk.length; i++)
			{
				st=new StringTokenizer(chk[i],"^");
				moduleId=st.nextToken();
				ps.setString(1,moduleId);
				ps.setString(2,this.hospitalCode);
				ps.execute();
			}
			status = " Record(s) Deleted Successfully.";

		}
		catch(Exception e)
		{
			status = "Record(s) can not be Deleted " ;
			System.out.println("Exception in deleteRecord()-->"+e);
		}
		finally
		{
			super.closeConnection(conn);	
		}
		return status;

		}
	
	}
	
	
	


