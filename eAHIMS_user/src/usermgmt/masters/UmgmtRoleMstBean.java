package usermgmt.masters;
import usermgmt.*;

import java.util.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;


import HisGlobal.*;

public class UmgmtRoleMstBean	extends UserFunc
{
	private String combo1		="";
	private String role_id 		="";
	private String role_name	="";
	private String module_id	="";
	private String seat_id 		="";
	private String effect_date	="";
	private String entry_date	="";
	private String isvalid 		="1";
	private String role_type	="";
	private String moduleName="";
	private String[] chk;
	private String 	status;
	boolean retValue;
	private String hospitalCode="";
	
	public String getModuleName() 
	{
		return moduleName;
	}

	public void setModuleName(String moduleName) 
	{
		this.moduleName = moduleName;
	}

	public UmgmtRoleMstBean()
	{
	}

	public String getRole_id()
	{
		return ((role_id==null)?" ":role_id);
	}

	 public String getStatus()
	 {
		 return status;
	 }
	 
	 public String getCombo1() 
	 {
		return combo1;
	 }

	public void setCombo1(String combo1) 
	{
		this.combo1 = combo1;
	}

	public String getRole_name()
	{
		return ((role_name==null)?" ":role_name);
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

	public String getIsvalid()
	{
		return ((isvalid==null)?" ":isvalid);
	}

	public String getRole_type()
	{
		return ((role_type==null)?" ":role_type);
	}

	public void setRole_id(String ROLE_ID)
	{
		this.role_id=ROLE_ID;
	}

	public void setRole_name(String ROLE_NAME)
	{
		this.role_name=ROLE_NAME;
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

	public void setIsvalid(String ISVALID)
	{
		this.isvalid=ISVALID;
	}

	public void setRole_type(String ROLE_TYPE)
	{
		this.role_type=ROLE_TYPE;
	}

	public void setStatus(String sName)
	{
		status = sName;
	}

	public void setChk(String[] chk0)
	{
		chk = chk0;
	}
	
	public void initializeNewMode()
	{
		role_id			= "";
	   	role_name		= "";
	  	module_id		= "";
	  	effect_date		= "";
	  	entry_date		= "";
	 	role_type		= "";
	}

	public boolean insertRecord() throws Exception
	{
		
		String rolename		="";
		String query  = new String();
		List masterList = new ArrayList();
        List lst = new ArrayList();
		usermgmt.FuncLib f=new usermgmt.FuncLib(); 
		
		query= " select  GSTR_ROLE_NAME from GBLT_ROLE_MST WHERE " +
				" UPPER(GSTR_ROLE_NAME)=UPPER('"+this.role_name+"')" +
				"   and  gnum_hospital_code='"+this.hospitalCode+"' "+
				" and GNUM_MODULE_ID=initcap('"+this.module_id+"')and GBL_ISVALID>0 and GBL_ISVALID<3";
		
		boolean redundant = isRedundentName(query);
		if(!redundant)
		{
		
			if(this.role_name!=null)
			{	if((this.role_name.length())<2)
				rolename	=this.role_name.substring(0,1).toUpperCase();
				else
				rolename=this.role_name.substring(0,1).toUpperCase()+this.role_name.substring(1).toLowerCase();
			}
			else
			{
				System.out.println("Your Role name is null inside Role Bean");
				rolename ="";
			}
			
			query=" select nvl(max( GNUM_ROLE_ID +1),1001) from GBLT_ROLE_MST where gnum_hospital_code="+this.hospitalCode;
		    
		role_id 			= f.getField(query);
		retValue 			= true;
		entry_date 			= this.getSysDate();

		
	  	query =		" INSERT INTO GBLT_ROLE_MST "+
					" (GNUM_ROLE_ID,GSTR_ROLE_NAME,"+
					" GNUM_MODULE_ID, "+
					" GNUM_SEAT_ID,GDT_EFFECT_DATE,"+
					" GBL_ISVALID,GNUM_HOSPITAL_CODE) " +
					" values(?,?,?,?,to_date(?,'dd-mon-yyyy'),?,?)";
	  	System.out.println("effect_date"+effect_date);
		System.out.println("query insert"+query);           
	  
	  	
	  	lst.add(String.valueOf(role_id));
		lst.add(rolename.trim());
		lst.add(String.valueOf(module_id));
		lst.add(String.valueOf(seat_id));
		lst.add(effect_date);
		lst.add(String.valueOf(isvalid));
		lst.add(hospitalCode);
          
        masterList.add(lst);
        
        
        retValue= new HisGlobal.HisMethods().insertQuery(query,masterList);
        }
        else 
        {
        	retValue=false;
        	status="Role Name Already Exist,Please Enter Other Role Name";
        }
        
       
       return retValue;
     }  

	public List initializeUpdateMode()throws Exception
	{
    	String query ="";
    	String code = new String("");
    	List l1 = new ArrayList();
		code 	= chk[0].substring(0,chk[0].length()-1);
		int pos = code.indexOf('^');
		this.role_id = code.substring(0,pos);
		this.module_id = code.substring(pos+1);
		
		query=	"	SELECT GNUM_ROLE_ID,initcap(GSTR_ROLE_NAME),(select GSTR_MODULE_NAME from GBLT_METATABLE_TYPE_MST a where " +
				//"	a.GNUM_MODULE_ID =b.GNUM_MODULE_ID and a.gnum_hospital_code=100 AND a.GBL_ISVALID=1) Mname,to_char(GDT_EFFECT_DATE,'DD-Mon-YYYY')"+ 
		"	a.GNUM_MODULE_ID =b.GNUM_MODULE_ID " +
		//" and a.gnum_hospital_code='"+this.hospitalCode+"' " +
				" AND a.GBL_ISVALID=1) Mname,to_char(GDT_EFFECT_DATE,'DD-Mon-YYYY')"+		
		"	FROM GBLT_ROLE_MST b WHERE GNUM_ROLE_ID='"+role_id+"' and b.gnum_hospital_code='"+this.hospitalCode+"' and b.GNUM_MODULE_ID='"+this.module_id+"'  and  b.GBL_ISVALID > 0 and   b.GBL_ISVALID < 3  ";
		
		System.out.println("query initialize update "+query);
		HisResultSet rst = getRecord(query);
		if(rst.next())
		{
			System.out.println("i an herer");
			this.role_name  = rst.getString(2);
			System.out.println("i an herer1");
			this.moduleName = rst.getString(3);
			System.out.println("i an herer2");
			//if(rst.getString(4).equals("A"))
			//this.role_type="Application";	
			//else if(rst.getString(4).equals("D"))
			//this.role_type="Database";	
			this.effect_date=rst.getString(4);
			System.out.println("i an herer4");
		}
		System.out.println("i an herer5");
		l1.add(role_name);
		l1.add(moduleName);
		//l1.add(role_type);
		l1.add(effect_date);
		System.out.println("at the end of the initialize record");
		return l1;
	}
    	
    public boolean updateRecord( HttpServletRequest request )throws Exception
	{
		
    	String pK				=	"";
    	String query			=	"";
    	String query1			=	"";
    	String query2			=	"";
    	String oldRecord 		=	"";
    	String oldVal			=	"";
    	String newVal			=	"";
    	boolean flag 			=	false;
    	boolean	redundant		=	false;
		boolean	redundantname	=	false;
    	boolean isRun			=	false;
		Connection conn 		= 	null ;
    	Statement st 			= 	null ;
    	PreparedStatement ps1 	=	null;
    	int isValid;
    	
    	pK			=	this.role_id+"^"+this.module_id ;
    	oldRecord	= 	"	select 'GSTR_ROLE_NAME'||'^'||GSTR_ROLE_NAME||'#'||'GNUM_SEAT_ID'||'^'||GNUM_SEAT_ID ||'#'||'GBL_ISVALID'||'^'||GBL_ISVALID from GBLT_ROLE_MST where GNUM_ROLE_ID='"+this.role_id+"' and GNUM_MODULE_ID='"+this.module_id+"' ";           
    	oldVal		= 	super.getField(oldRecord); 
    	newVal		= 	"	GSTR_ROLE_NAME^"+this.role_name+"#GNUM_SEAT_ID^"+this.seat_id;
         
		query1		=	"	select  initcap(GSTR_ROLE_NAME) from GBLT_ROLE_MST WHERE UPPER(GSTR_ROLE_NAME)=UPPER('"+this.role_name+"')" +
						"   and  gnum_hospital_code='"+this.hospitalCode+"' "+
						" 	and GNUM_MODULE_ID="+this.module_id+" and GBL_ISVALID>0 and GBL_ISVALID<3 AND  GNUM_ROLE_ID !='"+this.role_id+"' ";
		 
		redundantname=	super.isRedundentName(query1);
		if(redundantname)
		{
			this.status=	"	Role Name Already Exist , Please Write Other Role Name......!";
			
			/*query2	=	"	select  GBL_ISVALID from GBLT_ROLE_MST WHERE initcap(GSTR_ROLE_NAME)=initcap('"+this.role_name+"')" +
						"   and  gnum_hospital_code='"+this.hospitalCode+"' "+
						" 	and GNUM_MODULE_ID="+this.module_id+" and GBL_ISVALID>0 and GBL_ISVALID<3";
			
			
			isValid	=	Integer.parseInt(this.isvalid);
			redundant	=	super.isRecordExist(query2,isValid);
			if(redundant)
			{
				this.status=	"	Role Name Already Exist , Please Write Other Role Name......!";
			}
			else
			{
				query	=	"	UPDATE GBLT_ROLE_MST SET GSTR_ROLE_NAME='"+this.role_name+"', GNUM_SEAT_ID='"+this.seat_id+
	 						"', GBL_ISVALID ='"+this.isvalid+"' WHERE GNUM_ROLE_ID ='" + this.role_id +"' and GNUM_MODULE_ID='"+this.module_id+"'"+
	 						"   and  gnum_hospital_code='"+this.hospitalCode+"' ";
				isRun=true;
			}
			*/
			
		}
		else
		{
			query	=	" UPDATE GBLT_ROLE_MST SET GSTR_ROLE_NAME='"+this.role_name+"', GNUM_SEAT_ID='"+this.seat_id+
 						"', GBL_ISVALID ='"+this.isvalid+"' WHERE GNUM_ROLE_ID ='" + this.role_id +"' and GNUM_MODULE_ID='"+this.module_id+"'" +
 						"   and  gnum_hospital_code='"+this.hospitalCode+"' ";
		
			System.out.println("Query_update_role----->"+query);
			isRun=true;
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
				ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_ROLE_MST").updateAuditLog(request,conn);
				conn.commit(); 
				flag=true;
			}
			catch( Exception e ) // it handles user defined exception throws by AuditLog.java file
			{   
				this.status = "Error while Updating Record(s) "+e;
				System.out.println("exception in excution ="+e);
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
					if(ps1!= null) 
						ps1.close();
					if(st!= null)
						st.close(); 
					if(conn!=null)
						conn.close();
				}
				catch(SQLException se1 ) 
				{ 
					System.out.println("Exception in finally block"+se1);
				}
			}
		}
		else
		{
			this.status	="	Role Name Already Exist , Please Write Other Role Name......!";
		}
		
        return flag;
	}     
            

	public String deleteRecord()throws Exception
	{
		status	=	"Successfully deleted !";
		String code =null;
        java.sql.CallableStatement cls = null;
		Connection conn = null; int i = 0 ;
        try
		{
			conn = super.getConnection();
			String sql="{call ahis_user.role_priv_change(?,?,?)}";
			cls = conn.prepareCall(sql);


   		
			//*****************  piyush Aug 17 2006 *************************
		
			for(  i=0; i<chk.length; i++)
			{
				code 	= chk[i].substring(0,chk[i].length()-1);
                int pos = code.indexOf('^');
				this.role_id = code.substring(0,pos);
				int id1 = Integer.parseInt(role_id);
				cls.setString(1,hospitalCode);
				cls.setInt(2,id1);
				cls.setInt(3,0);// status  0 - deleted  
				cls.executeQuery();
			}	
			//*****************  piyush Aug 17 2006 *************************
            status = i + " Record(s) Deleted Successfully.";
   		}
   		catch(Exception e)
   		{
   			status = "Error in deleting Record(s): " + e;
			System.out.println(status);
   		}
        finally
        {
        	cls.clearBatch();
			cls.close();
			super.closeConnection(conn);
			cls = null;
			conn = null;
        } 
		return status;
	}

	public String getHospitalCode() {
		System.out.println("hos code--"+hospitalCode);
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	


}//End of Class

