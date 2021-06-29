	package usermgmt.masters;
	import usermgmt.*;
	import java.sql.*;
	import java.util.*;
	import HisGlobal.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
	
	public class UmgmtMenuAllowedURL_MstBean extends FuncLib
	{
		private String initLevel	="";
		private String combo1		 ="";
	    private String hl7Code       ="";
	    private String menuName2	="";
	    private String menuIds		="";
	    private String menu_id 		 ="";
		private String menu_name 	 ="";
		private String isvalid 		 ="";
		private String menuclass_id  ="";
		private String menu_level 	 ="";
		private String display_order ="";
		private String seatId 		 ="";
		private String seat_id		 ="";
		private String entry_date 	 ="";
		private String parent_name	="";
		private String 	url			 ="";
		boolean retValue             = true;
		private String status		="";
		private String[] chk;
	    private String moduleId    ="";
	    private String menuName1	="";
	    private String parent_id	="";
	    private String hospitalCode="";
	    private String displayInPortal="";
	    private String menuName3="";
			     
	    public String getMenu_name() 
	    {
	    	return this.menu_name;
	    }
	    public String getParent_id() 
	    {
	    	return this.parent_id;
	    }
	
	    public void setParent_id(String parent_id) {
	    	this.parent_id =parent_id ;
	    }
	    public String getMenuIds() {
	    	return menuIds;
	    }
	
	    public void setMenuIds(String menuIds) {
	    	this.menuIds = menuIds;
	    }
	     public String getMenuName1() {
			return menuName1;
		}
	
		public void setMenuName1(String menuName1) {
			this.menuName1 = menuName1;
		}
	
		public String getInitLevel() 
	    {
			return initLevel;
		}
	
		public void setInitLevel(String initLevel) 
		{
			this.initLevel = initLevel;
		}
		
	    public java.lang.String getCombo1( )
		{
		    return combo1;
		}
	
		public void setCombo1( java.lang.String combo1 )
		{
		    this.combo1 = combo1;
		}
		public java.lang.String getModuleId( )
		{
		    return moduleId;
		}
	
		public void setModuleId( java.lang.String moduleId )
		{
		    this.moduleId = moduleId;
		}
	
		public java.lang.String getHl7Code( )
		{
		    return hl7Code;
		}
	
	     public String getStatus()
		{
				return (status == null)?"":status;
		}
	
		public String getMenu_id()
		{
			return ((menu_id==null)?" ":menu_id);
		}
		
		public String getIsvalid()
		{
			return ((isvalid==null)?" ":isvalid);
		}
		public String getMenuclass_id()
		{
			return ((menuclass_id==null)?" ":menuclass_id);
		}
		public String getMenu_level()
		{
			return ((menu_level==null)?" ":menu_level);
		}
		public String getDisplay_order()
		{
			return ((display_order==null)?" ":display_order);
		}
		public String getSeatId()
		{
			return ((seatId==null)?" ":seatId);
		}
		public String getEntry_date()
		{
			return ((entry_date==null)?" ":entry_date);
		}
		public String getUrl()
		{
			return url;
		}
	
		public void setHl7Code( java.lang.String hl7Code )
		{
		    this.hl7Code = hl7Code;
		}
		
		public void setMenu_id(String MENU_ID)
		{
			this.menu_id=MENU_ID;
		}
		public void setMenu_name(String MENU_NAME)
		{
			this.menu_name=MENU_NAME;
		}
		public void setIsvalid(String ISVALID)
		{
			this.isvalid=ISVALID;
		}
		public void setMenuclass_id(String MENUCLASS_ID)
		{
			this.menuclass_id=MENUCLASS_ID;
		}
		public void setMenu_level(String MENU_LEVEL)
		{
			this.menu_level=MENU_LEVEL;
		}
		public void setDisplay_order(String DISPLAY_ORDER)
		{
			this.display_order=DISPLAY_ORDER;
		}
		public void setSeatId(String SEAT_ID)
		{
			this.seatId=SEAT_ID;
		}
		public void setEntry_date(String ENTRY_DATE)
		{
			this.entry_date=ENTRY_DATE;
		}
		public void setUrl(String url)
		{
			this.url=url;
		}
	
		public void	setChk(String[] chk0)
		{
			chk = chk0;
	    }
	
	    public void setStatus(String sName)
		{
			status = sName;
		}
	
	    public String getMenuName2() {
	    	return menuName2;
	    }
	
	    public void setMenuName2(String menuName2) {
	    	this.menuName2 = menuName2;
	    }
	   
	    public String displayOrderValue(String menu_level,String parent)
		{
			String value="";
			try
			{
				String query="";
				/*query=	"	select NVL(MAX (GNUM_DISPLAY_ORDER),'0')from gblt_menu_mst " +
						"	where GNUM_MENU_LEVEL='"+menu_level+"' and GNUM_PARENT_ID='"+parent+"' and " +
						//" GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' ";
						" GNUM_HOSPITAL_CODE='100' ";*/
				
				query=	"	select NVL(MAX (GNUM_DISPLAY_ORDER),'0')from gblt_menu_mst " +
						"	where GNUM_MENU_LEVEL='"+menu_level+"' and GNUM_PARENT_ID='"+parent+"'";
				
				HisResultSet   rs = getRecord(query);
				if (rs.next())
				{
					rs.previous();
					value=String.valueOf(Integer.parseInt(rs.getString(1))+1);
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception is"+e);
			}
			return value;
		} 
	    
	    public boolean insertRecord()throws Exception
		{
			System.out.println("seat id is"+this.seatId);
	    	String moduleIdd	=	"";
	    	String mname		=	"";
			String values		=	"";
			String query  		= 	new String();
			entry_date 			=  	getSysdate() ;
			String  menu_id 	= 	"";
			String hospitalCode=this.getHospitalCode();
			String displayInPortal="";
			String menu_IId=this.getMenuName2();
			String seatId=this.getSeat_id();
			
			if(this.moduleId.equals(""))
				this.moduleId="0";
	//	String maxSlnoQuery="";
			
			
		//	System.out.println("entry_dateIS----- "+entry_date);
			
		//	if(this.menuclass_id.equals("R")&&(this.menuclass_id.equals("I")))
			//	this.url=" ";
			
		
			//moduleIdd = moduleIdd.substring(0,2);
//			query	=	"	SELECT initcap(gstr_menu_name) FROM gblt_menu_mst a " + 	
//						//"	WHERE a.gnum_parent_id ='"+parent_id+"' AND a.gnum_hospital_code='"+this.hospitalCode+"' " +
//						"	WHERE a.gnum_parent_id ='"+parent_id+"' AND a.gnum_hospital_code='100' " +
//						"   AND UPPER(gstr_menu_name)=UPPER('"+ menu_name +"') and GNUM_ISVALID >0 and " +
//						"   GNUM_ISVALID<3 ";
		
					
				/*values =	"'"+ parent_id + "','"  +
						    menu_id   + "','" +
						    mname +"', '"+
						    isvalid   +"', '"+
						    menuclass_id +"', '"+
						  	menu_level   + "', '" +
							display_order+"', '"+
							seatId   +"', '"+
						    url   +"', '"+
						    entry_date +"', '"+
						    hl7Code  +"', '"+
						    moduleIdd +"','"+
						    hospitalCode+"','"+
						    displayInPortal+"', ";*/
				
				values =	"'"+ 
			    menuIds   + "','" +
			    url   +"', '"+
			    moduleId +"',sysdate, '1', '100', (select GSTR_URL from gblt_menu_mst where GNUM_MENU_ID ="+ menuIds+ ")";
			   
						    
						    
				//maxSlnoQuery=" ( SELECT NVL(MAX(GNUM_SLNO)+1,1) FROM GBLT_MENU_MST   WHERE GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND GNUM_MENU_ID="+menu_id+")";
			//	maxSlnoQuery=" ( SELECT NVL(MAX(GNUM_SLNO)+1,1) FROM GBLT_MENU_MST   WHERE GNUM_HOSPITAL_CODE='100' AND GNUM_MENU_ID="+menu_id+")";
			//	values+=maxSlnoQuery;
			
				query = " INSERT INTO GBLT_MENU_ALLOWEDURL_MST ("+
					"  GNUM_MENU_ID,GSTR_ALLOWEDURL, GNUM_MODULE_ID,GDT_ENTRY_DATE, GNUM_ISVALID,"+
					"  GNUM_HOSPITAL_CODE,GSTR_MENUURL )"+
					" values(" + values + ")";
					
			System.out.println("QUERY IS "+query);
				try
					{
			        	if (!updateRecord(query))
			        	{
			        		status = "Error while inserting the record !";
			        		retValue = false;
			        	}
			        	else
			        	{
			        		status = "Record  Successfully Inserted !";
			        	}
					}
					catch(Exception e)
					{
						status = String.valueOf(e);
						System.out.println("Error While Inserting Record:"+e);
						retValue = false;
					}	
		  
//		{
//			retValue=false;
//        	status="Menu Name Already Exist For This Parent,Please Enter Other Menu Name !";
//		}
		
		
		return retValue;
	}
		
		public String viewMenuIds()
		{
			String htmlStr=null;
			String query=null;
			if(this.moduleId.equals(""))
				this.moduleId="0";
			try
			{
					
					/*query=	"	Select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST " +
					"	WHERE  GNUM_ISVALID='1'   and gnum_hospital_code='100' and GNUM_MODULE_ID='"+this.moduleId
					+"' and GSTR_URL is not null  and RPAD(SUBSTR (gnum_menu_id, 1, 1),12,0)=  '"+this.initLevel+"' order by INITCAP(GSTR_MENU_NAME)";*/
				query=	"	Select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST " +
						"	WHERE  GNUM_ISVALID='1'   and GNUM_MODULE_ID='"+this.moduleId
						+"' and GSTR_URL is not null  and RPAD(SUBSTR (gnum_menu_id, 1, 1),12,0)=  '"+this.initLevel+"' order by INITCAP(GSTR_MENU_NAME)";
					
					
				System.out.println("query for menu is equal to======"+query);
			
				 HisResultSet rst =  getRecord(query);			 		
			 		String text="Select Menus";
			 		String value="0";
			 					 		
			 		htmlStr = htmlStr+"<option value='0'>Select Menus</option>";
			 		
			 		String menuids = this.menuIds;
			 		if(menuids==null)
			 			menuids = "";
			 		
		 			while(rst.next())		 			
			    	{
		 				value	=	(rst.getString(1)).trim();
			    		text	=	(rst.getString(2)).trim();	
			    		
		 				if(value.equals(menuids))
		 					htmlStr = 	htmlStr+"<option value='"+value+"' selected>"+text+"</option>";
		 				
		 				else
		 					htmlStr = 	htmlStr+"<option value='"+value+"'>"+text+"</option>";
		 								   		 
			    		
			    	} 		
			}
			catch(Exception e)
			{
				System.out.println("Exception is"+e);
			}
			return htmlStr;
		
		}
		
		
		public String getMenuId(String menu_level, String moduleId, String parentId)
		{
			System.out.println("inside get menu id");
			HisResultSet   rs	=	null;
			String menuIDdd="";
			String query="select pkg_usermgmt.generatemenuid('"+menu_level+"','"+this.hospitalCode+"','"+moduleId+"','"+parentId+"') from dual";
			try{
			rs = getRecord(query);
			while(rs.next())
			{
				 menuIDdd=rs.getString(1);
				 System.out.println("value of the function generated menu id is equal to====-----"+menuIDdd);
			}
			}catch(Exception e){System.out.println("error in the get menuId function"+e);}
			return menuIDdd;
		
	      
		}
		
		public List initializeUpdateMode()throws Exception
		{
			List AL_List = new ArrayList();
	  		String query = new String("");
	  		menu_id = chk[0].split("\\^")[0];
	  		url = chk[0].split("\\^")[1];
	  		hospitalCode=this.getHospitalCode();
	  		
	  		/*query=	"	SELECT GNUM_MENU_ID, pkg_usermgmt.fun_menu_name(GNUM_MENU_ID,GNUM_HOSPITAL_CODE), GSTR_ALLOWEDURL, " +
	  				"   GNUM_MODULE_ID, NVL((SELECT GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST WHERE GNUM_MODULE_ID = m.GNUM_MODULE_ID and gnum_hospital_code =100 and gnum_isvalid=1),' '), " +
			  		"	to_char(gdt_entry_date,'DD-Mon-YYYY') "+
	  				"	FROM GBLT_MENU_ALLOWEDURL_MST m WHERE gnum_menu_id ="+this.menu_id+ " AND LOWER(GSTR_ALLOWEDURL) = LOWER('"+ this.url +"') AND gnum_hospital_code='100' AND gnum_isvalid ="+this.combo1;*/
	  				
	  		query=	"	SELECT GNUM_MENU_ID, pkg_usermgmt.fun_menu_name(GNUM_MENU_ID,GNUM_HOSPITAL_CODE), GSTR_ALLOWEDURL, " +
	  				"   GNUM_MODULE_ID, NVL((SELECT GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST WHERE GNUM_MODULE_ID = m.GNUM_MODULE_ID and gnum_isvalid=1),' '), " +
			  		"	to_char(gdt_entry_date,'DD-Mon-YYYY') "+
	  				"	FROM GBLT_MENU_ALLOWEDURL_MST m WHERE gnum_menu_id ="+this.menu_id+ " AND LOWER(GSTR_ALLOWEDURL) = LOWER('"+ this.url +"') AND gnum_hospital_code='100' AND gnum_isvalid ="+this.combo1;
	  		
	  		System.out.println("query in initialization"+query);
	  		try
	  		{
	  			AL_List = getDetail(query);
	  		}
	  		catch(Exception e)
	  		{
	  		}
	
	    	menu_id			=(String)AL_List.get(0);
	  		menu_name 		=(String)AL_List.get(1);
	  		url				=(String)AL_List.get(2);
	    	moduleId   		=(String)AL_List.get(3);
	    	parent_name   	=(String)AL_List.get(4);
	    	entry_date 		=(String)AL_List.get(5);
	    	return AL_List;
	  	}
		
		public String deleteRecord()throws Exception
		{
			Connection conn = null;
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			hospitalCode=this.getHospitalCode();
			String message = null;
			try
			{
				conn = super.getConnection();
				conn.setAutoCommit(false);
				
				/*String query2 = "UPDATE GBLT_MENU_ALLOWEDURL_MST SET GNUM_ISVALID ='0' WHERE GNUM_MENU_ID = ? " +
						"and LOWER(GSTR_ALLOWEDURL)= LOWER(?) AND GNUM_HOSPITAL_CODE='100'";*/
				
				String query2 = "DELETE FROM GBLT_MENU_ALLOWEDURL_MST WHERE GNUM_MENU_ID = ? " +
						"and LOWER(GSTR_ALLOWEDURL)= LOWER(?) AND GNUM_HOSPITAL_CODE='100'";
				
				ps2 = conn.prepareStatement(query2);
				int counter = 0;
				
				for(int i=0;i<this.chk.length;i++)
				{
			  		String menu_id = chk[i].split("\\^")[0];
			  		String url = chk[i].split("\\^")[1];

					ps2.setString(1,menu_id);
					ps2.setString(2,url);
					
					ps2.execute();
					
					counter++;
					
				}
				conn.commit();
				conn.setAutoCommit(true);
				message = counter+" Record(s) Successfully Deleted";
			}
			catch(Exception e)
			{
				this.status = "Error while Deleting Record(s) "+e;
				System.out.println("Exception in update record "+e);
				retValue = false;
				try
				{
					conn.rollback();
				}
				catch(Exception e2)
				{
					System.out.println("Exception in rollback "+e2);
				}
				message = "Error while Deleting Record(s)";
			}
			finally
			{
				try
				{
					if(ps1!=null)
						ps1.close();
						
					if(ps2!=null)
						ps2.close();
					
					if(conn!=null)
						super.closeConnection(conn);
				
						
				}
				catch(Exception e2)
				{
					System.out.println("Exception in rollback "+e2);
				}			
			}		
			return message;
		}
		
		public void initializeNewMode()
		{
			this.menu_name	=	"";
			this.hl7Code	=	"";
			this.url		=	"";
		}
	
		public boolean updateRecord( HttpServletRequest request )throws Exception
		{
			
			String query			=	"";
			String query1			=	"";
			String query2			=	"";
			String query3			=	"";
			String pK				=	"";
			String oldRecord		=	"";
			String oldVal			=	"";
			String newVal			=	"";
			boolean flag 			=	false;
			boolean redundant    	= 	false;
		    boolean redundantname  	= 	false;
		    boolean isRun			=	false;
		    Connection conn 		= 	null ;
			Statement st 			=	null ;
			PreparedStatement ps1 	=	null;
			int isValid;
			
			query	=	" UPDATE GBLT_MENU_ALLOWEDURL_MST SET GSTR_ALLOWEDURL='"+this.url+"'"+
				" WHERE GNUM_MENU_ID =  '"+this.menuIds+"' and lower(GSTR_ALLOWEDURL)=lower('"+this.parent_id+"') and gnum_hospital_code='100' and  GNUM_ISVALID in (1,2) ";	
			
			System.out.println("Update >> "+ query);
			isRun	=	true;
			
			if(isRun)
			{
				conn = new HisMethods().getConnection();
				conn.setAutoCommit(false);
				st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				try
				{
					st.addBatch(query);
					//st.addBatch(query2);
					st.executeBatch();
					ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_MENU_ALLOWEDURL_MST").updateAuditLog(request,conn);
					conn.commit();
					flag=true;
					status="Record Successfully Updated !";
				}
				catch(Exception e )
				{
					status="Error while Updating Record(s)!";
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
						if(ps1!=null) 
							ps1.close();
						if(st!=null)
							st.close();
						if(conn!=null)
							conn.close();
					
					}
					catch(Exception se1 ) 
					{ 
						System.out.println("Exception in finally block"+se1);
					}
				}	
			}
			return flag;
		}
		public String viewInitLevel()
		{
			hospitalCode=this.getHospitalCode();
			String htmlStr=null;
			hospitalCode=this.getHospitalCode();
			System.out.println("inside viewInitLevel");
			try
			{
				//String query	=	"	select GNUM_MENU_ID,initcap(GSTR_MENU_NAME) from GBLT_MENU_MST where gnum_hospital_code='"+this.hospitalCode+"' and GNUM_ISVALID='1'"+
									//"	and GNUM_MENU_LEVEL='0'order by initcap(GSTR_MENU_NAME) ";
			
				/*String query	=	"	select GNUM_MENU_ID,initcap(GSTR_MENU_NAME) from GBLT_MENU_MST where gnum_hospital_code='100' and GNUM_ISVALID='1'"+
				"	and GNUM_MENU_LEVEL='0'order by initcap(GSTR_MENU_NAME) ";*/
				
				String query	=	"	select GNUM_MENU_ID,initcap(GSTR_MENU_NAME) from GBLT_MENU_MST where GNUM_ISVALID='1'"+
						"	and GNUM_MENU_LEVEL='0'order by initcap(GSTR_MENU_NAME) ";

				 HisResultSet rst =  getRecord(query);			 		
			 	String text="Select Root Menu";
			 	String value="0";
			 					 		
			 		htmlStr = htmlStr+"<option value='0'>Select Root Menu</option>";
			 		
			 		String rootMenu = this.initLevel;
			 		if(rootMenu==null)
			 			rootMenu = "";
			 		
		 			while(rst.next())		 			
			    	{
		 				value	=	(rst.getString(1)).trim();
			    		text	=	(rst.getString(2)).trim();	
			    		
		 				if(value.equals(rootMenu))
		 					htmlStr = 	htmlStr+"<option value='"+value+"' selected>"+text+"</option>";
		 			
		 				else
		 					htmlStr = 	htmlStr+"<option value='"+value+"'>"+text+"</option>";
			    	} 		
			}
			catch(Exception e)
			{
				System.out.println("Exception e");
			}
			return htmlStr;
		}
		
			public String viewLevel() throws Exception
			{
				System.out.println("inside view level");
				String temp=getMenu_level();
				String html="";
				if (temp=="")
					temp="0";
				
				try
				{
					if(temp.equals("0"))
					{
						html +=	"<option value="+0+" selected>Level Of Hierarchy</option>"+
	                   		"<option value="+1+">Level 1</option>"+
	                   		"<option value="+2+">Level 2</option>"+
	                   		"<option value="+3+">Level 3</option>"+
	                   		"<option value="+4+">Level 4</option>";
	                   		
					}
	 			
	 			if(temp.equals("1"))
	 			{
	 				html +=	"<option value="+0+">Level Of Hierarchy</option>"+
	                   		"<option value="+1+" selected>Level 1</option>"+
	                   		"<option value="+2+">Level 2</option>"+
	                   		"<option value="+3+">Level 3</option>"+
	                   		"<option value="+4+">Level 4</option>";
	                   		
	 			}
	 			
	 			if(temp.equals("2"))
	 			{
	 				html +=	"<option value="+0+">Level Of Hierarchy</option>"+
	                   		"<option value="+1+">Level 1</option>"+
	                   		"<option value="+2+" selected>Level 2</option>"+
	                   		"<option value="+3+">Level 3</option>"+
	                   		"<option value="+4+">Level 4</option>";
	                   		
	 			}
	 			
	 			if(temp.equals("3"))
	 			{
	 				html +=	"<option value="+0+">Level Of Hierarchy</option>"+
	                   		"<option value="+1+">Level 1</option>"+
	                   		"<option value="+2+">Level 2</option>"+
	                   		"<option value="+3+" selected>Level 3</option>"+
	                   		"<option value="+4+">Level 4</option>";
	                   		
	 			}
	 			if(temp.equals("4"))
	 			{
	 				System.out.println("inside viewLevel function"+temp);
	 				html +=	"<option value="+0+">Level Of Hierarchy</option>"+
	                   		"<option value="+1+">Level 1</option>"+
	                   		"<option value="+2+">Level 2</option>"+
	                   		"<option value="+3+">Level 3</option>"+
	                   		"<option value="+4+" selected>Level 4</option>";	                   		
	 			}
	 		}
	       catch(Exception e)
	       {
	            System.out.println("Exception is "+e);
	       }
	       return html;
	    }
	
	   public String viewModuleId() throws Exception
	   {
		 
		   String query ="";
		   String htmlStr ="";
		  System.out.println("inside viewModuleId function");
		 	// if(!(this.initLevel==null) && !(this.initLevel.equals("")))
			// {
		 		 	/*query=		"	SELECT a.gnum_module_id, INITCAP (a.gstr_module_name)"+	
				 				"	FROM gblt_metatable_type_mst a WHERE gbl_isvalid = '1' and " +
					    	  	//"  gnum_hospital_code='"+this.hospitalCode+"' "+
					    	  "gnum_hospital_code='100' "+
					    	  	"   order by initcap(gstr_module_name)";	*/
		  query=		"	SELECT a.gnum_module_id, INITCAP (a.gstr_module_name)"+	
	 				"	FROM gblt_metatable_type_mst a WHERE gbl_isvalid = '1'"+
		    	  	"   order by initcap(gstr_module_name)";	
		 			 System.out.println("query11111..."+query);	 
		 		 	
				 
				 System.out.println("Final query..."+query);	 
				 HisResultSet rst =  getRecord(query);			 		
				 		String text="Select Module";
				 		String value="0";
				 					 		
				 		htmlStr = htmlStr+"<option value='0'>Select Module</option>";
				 		
				 		String modId = this.moduleId;
				 		if(modId==null)
				 			modId = "";
				 		
			 			while(rst.next())		 			
				    	{
			 				value	=	(rst.getString(1)).trim();
				    		text	=	(rst.getString(2)).trim();	
				    		
			 				if(value.equals(modId))
			 					htmlStr = 	htmlStr+"<option value='"+value+"' selected>"+text+"</option>";
			 				
			 				else
			 					htmlStr = 	htmlStr+"<option value='"+value+"'>"+text+"</option>";
				    	} 		
				 // }		 
		 	 	//else
		 	 	//{
		 	 		//htmlStr += "<option value='0'>Select Module</option>";
		    	//}
		 	return htmlStr;
		}	    
		
	   public String getMenuName()throws Exception
	   {
		   String moduleName="";
		   System.out.println("module id is"+this.moduleId);
		   /*String query=" SELECT GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST WHERE " +
		                //" gnum_hospital_code='"+this.hospitalCode+"' "+
		                "gnum_hospital_code='100'"+
		   	          	" AND GNUM_MODULE_ID="+this.moduleId;*/
		   String query=" SELECT GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST WHERE " +
	                //" gnum_hospital_code='"+this.hospitalCode+"' "+
	                    " GNUM_MODULE_ID="+this.moduleId;
		   System.out.println("query is"+query);
		   HisResultSet rst = getRecord(query);
		   while(rst.next())
		   {
			   moduleName=rst.getString(1);
		   }
		   return moduleName;
	   }
	
	
	public String viewClassId(String menuclassid) throws Exception
		{
	        String query ="";
			String htmlStr ="";
	
			query = "	SELECT GSTR_MENUCLASS_ID, initcap(GSTR_MENU_CLASS) FROM GBLT_MENU_CLASS_MST " +
					"	WHERE GSTR_MENUCLASS_ID !='R' and GBL_ISVALID=1 "+
					"	ORDER BY GSTR_MENUCLASS_ID ";
	
			System.out.println("ClassId ---->"+query);
			HisResultSet rst = getRecord(query);
	
			String value ="" ;
			String text  ="" ;
	
			while(rst.next())
			{
				value = (rst.getString(1)).trim();
				text  = (rst.getString(2)).trim();
	
				if(menuclassid.equals(value))//if equal
					htmlStr = htmlStr+"<option selected value='"+value+"'>"+text+"</option>";
				else
					htmlStr = htmlStr+"<option value='"+value+"'>"+text+"</option>";
			}
			return htmlStr;
		}
	public String getHospitalCode() { 
		return hospitalCode;
		}
	
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDisplayInPortal() {
		return displayInPortal;
	}
	public void setDisplayInPortal(String displayInPortal) {
		this.displayInPortal = displayInPortal;
	}
	
	
	public String getMenuName3() {
		return menuName3;
	}
	public void setMenuName3(String menuName3) {
		this.menuName3 = menuName3;
	}
	public String getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	}//end of menu mst