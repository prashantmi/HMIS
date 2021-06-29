	package usermgmt.masters;
	import usermgmt.*;
	import java.sql.*;
	import java.util.*;
	import HisGlobal.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
	
	public class UmgmtMenuMstBean extends FuncLib
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
		private String applicationtype_id  ="";
		private String menu_level 	 ="";
		private String display_order ="0";
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
	    private String portalDisplay="0";
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
		
		public String getApplicationtype_id()
		{
			return ((applicationtype_id==null)?" ":applicationtype_id);
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
		public void setApplicationtype_id(String applicationtype_ID)
		{
			this.applicationtype_id=applicationtype_ID;
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
				query=	"	select NVL(MAX (GNUM_DISPLAY_ORDER),'0')from gblt_menu_mst " +
						"	where GNUM_MENU_LEVEL='"+menu_level+"' and GNUM_PARENT_ID='"+parent+"'";
								
						//" GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' ";
						//" and GNUM_HOSPITAL_CODE='100' ";
				
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
			
			
			String maxSlnoQuery="";
			
			
			System.out.println("entry_dateIS----- "+entry_date);
			
			if(this.menuclass_id.equals("R")&&(this.menuclass_id.equals("I")))
				this.url="";
			
			if(this.menu_level.equals("1"))			
			{
				menu_name=this.getMenuName();
				parent_id=this.initLevel;
				moduleIdd=this.moduleId;
				menu_id 	= 	getMenuId(menu_level,moduleId,parent_id);
				displayInPortal=this.displayInPortal;
				
			
			}	
			
			else if(this.menu_level.equals("2"))			
			{		
				menu_name=this.menuName1;
				parent_id = this.moduleId;
				moduleIdd=this.moduleId;
				moduleIdd=moduleIdd.substring(1,3);
				menu_id 	= 	getMenuId(menu_level,this.moduleId.substring(1,3),parent_id);
				displayInPortal=this.displayInPortal;
				
				
			}
			
			else if(this.menu_level.equals("3"))
			{
			
				parent_id=this.menuIds;
				menu_name=this.menuName2;
				moduleIdd=this.moduleId;
				moduleIdd=moduleIdd.substring(1,3);
				menu_id 	= 	getMenuId(menu_level,this.moduleId.substring(1,3),parent_id);
				displayInPortal=this.displayInPortal;
				
				
			}
			else if(this.menu_level.equals("4"))
			{
			
				parent_id=this.menuIds;
				menu_name=this.menuName3;
				moduleIdd=this.moduleId;
				moduleIdd=moduleIdd.substring(1,3).trim();
				System.out.println("moduleIdddddd=--------"+moduleIdd);
				
				menu_id 	= 	getMenuId(menu_level,this.moduleId.substring(1,3),parent_id);
				displayInPortal=this.displayInPortal;
				
			}
			//moduleIdd = moduleIdd.substring(0,2);
			query	=	"	SELECT initcap(gstr_menu_name) FROM gblt_menu_mst a " + 	
						//"	WHERE a.gnum_parent_id ='"+parent_id+"' AND a.gnum_hospital_code='"+this.hospitalCode+"' " +
						"	WHERE a.gnum_parent_id ='"+parent_id+"' " +
								//"AND a.gnum_hospital_code='100' " +
						"   AND UPPER(gstr_menu_name)=UPPER('"+ menu_name +"') and GNUM_ISVALID >0 and " +
						"   GNUM_ISVALID<3 ";
			boolean redundant = isRedundentName(query);
			
			if(!redundant)
			{
				
				if(this.menu_name!=null)
				{	if((this.menu_name.length())<2)
						mname	=this.menu_name.substring(0,1).toUpperCase();
					else
					 mname=this.menu_name.substring(0,1).toUpperCase()+this.menu_name.substring(1).toLowerCase();
				}
				else
				{
					System.out.println("Your Menu name is null inside UmgmtMenuMst Bean");
					mname ="";
					
				}
					
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
				
				if(url=="")
				{
				values =	"'"+ parent_id + "','"  +
			    menu_id   + "','" +
			    mname +"', '"+
			    isvalid   +"', '"+
			    menuclass_id +"', '"+
			  	menu_level   + "', '" +
				display_order+"', '"+
				seatId   +"',NULL, '"+
			    entry_date +"', NULL::numeric, '"+
			   // moduleIdd +"','"+
			    moduleIdd +"','0','0'";
			    //100+"','"+
			   // displayInPortal+"',' "+
				//applicationtype_id+"'";
				}
				else
				{
					values =	"'"+ parent_id + "','"  +
				    menu_id   + "','" +
				    mname +"', '"+
				    isvalid   +"', '"+
				    menuclass_id +"', '"+
				  	menu_level   + "', '" +
					display_order+"', '"+
					seatId   +"', '"+
				    url   +"', '"+
				    entry_date +"', NULL::numeric, '"+
				   // moduleIdd +"','"+
				    moduleIdd +"','0','0'";
				   // 100+"','"+
				    //displayInPortal+"',' "+
					//applicationtype_id+"'";
					// displayInPortal+"',' "+
						//applicationtype_id+"'";
					
				}
						    
						    
				//maxSlnoQuery=" ( SELECT NVL(MAX(GNUM_SLNO)+1,1) FROM GBLT_MENU_MST   WHERE GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' AND GNUM_MENU_ID="+menu_id+")";
				
								
				//maxSlnoQuery=" ( SELECT NVL(MAX(GNUM_SLNO)+1,1) FROM GBLT_MENU_MST   WHERE GNUM_HOSPITAL_CODE='100' AND GNUM_MENU_ID="+menu_id+")";
				//values+=maxSlnoQuery;
			
				//query = " INSERT INTO GBLT_MENU_MST ("+
				//" GNUM_PARENT_ID, GNUM_MENU_ID,GSTR_MENU_NAME, GNUM_ISVALID, GSTR_MENUCLASS_ID, GNUM_MENU_LEVEL, "+
				//" GNUM_DISPLAY_ORDER, GNUM_SEAT_ID,GSTR_URL, GDT_ENTRY_DATE,GNUM_HL7_CODE,GNUM_MODULE_ID,GNUM_HOSPITAL_CODE,GNUM_ISPORTAL,GNUM_APPLICATION_TYPE,GNUM_SLNO)"+
				//" values(" + values + ")";
			
				query = " INSERT INTO GBLT_MENU_MST ("+
					" GNUM_PARENT_ID, GNUM_MENU_ID,GSTR_MENU_NAME, GNUM_ISVALID, GSTR_MENUCLASS_ID, GNUM_MENU_LEVEL, "+
					" GNUM_DISPLAY_ORDER, GNUM_SEAT_ID,GSTR_URL, GDT_ENTRY_DATE,GNUM_HL7_CODE,GNUM_MODULE_ID,GNUM_ISPORTAL,GNUM_APPLICATION_TYPE)"+
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
			}
			else
			{
				retValue=false;
	        	status="Menu Name Already Exist For This Parent,Please Enter Other Menu Name !";
			}
			
			
			return retValue;
		}
		
		public String viewMenuIds()
		{
			String htmlStr=null;
			String query=null;
			try
			{
				if(menu_level.equals("3")){
				/*query=	"	Select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST " +
						"	where GNUM_PARENT_ID='"+this.moduleId+"'"+
						" 	AND GSTR_MENUCLASS_ID='I'order by initcap(GSTR_MENU_NAME) ";Cheema 11Dec08*/
					
					
					query=	"	Select GNUM_MENU_ID, initcap(GSTR_MENU_NAME) from GBLT_MENU_MST " +
					"	where GNUM_PARENT_ID='"+this.moduleId+"' AND " +
				    //"   gnum_hospital_code='"+this.hospitalCode+"' "+
				   // "   gnum_hospital_code='100' "+
				    "    GNUM_ISVALID=1 "+
					" 	AND GSTR_URL is null order by initcap(GSTR_MENU_NAME) ";
					
					
				System.out.println("query for level 3 is equal to======"+query);
				}
				else if(menu_level.equals("4"))
				{
					/*query=	"select distinct GNUM_MENU_ID,GSTR_MENU_NAME from GBLT_MENU_MST where "+
							" GNUM_PARENT_ID in( SELECT GNUM_MENU_ID from GBLT_MENU_MST where"+
							"  GNUM_PARENT_ID='"+this.moduleId+"') AND GSTR_MENUCLASS_ID='I' order"+
							" by initcap(GSTR_MENU_NAME) ";Cheema 11Dec 08*/
					
					
					query=	"select distinct GNUM_MENU_ID,GSTR_MENU_NAME from GBLT_MENU_MST where "+
					"  GNUM_PARENT_ID in( SELECT GNUM_MENU_ID from GBLT_MENU_MST where"+
					"  GNUM_PARENT_ID='"+this.moduleId+"') AND GSTR_URL is null " +
					//"  AND gnum_hospital_code='"+this.hospitalCode+"' "+
					//"  AND gnum_hospital_code='100' "+
					"   and  GNUM_ISVALID=1 "+
					"  order by initcap(GSTR_MENU_NAME) ";
					System.out.println("query for level 4 is equal to======"+query);
				}
				
				
			
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
			/*int i;
			HisResultSet   rs	=	null;
			String cMenu		=	"";
			String query		=	"";
			String nextMenu_id	=	"";
			String root			=	"";
			System.out.println("menu_level ="+menu_level);
			try
			{
				if(this.menu_level.equals("1"))
				{
					root	=	String.valueOf(this.initLevel.charAt(0));//Fetching one char from MenuId
					System.out.println("rootinside getMenuId="+root);
					if(moduleId.length()==1)// width of nextMenu_id is 8
					{
						nextMenu_id=root+"0"+moduleId+this.initLevel.substring(3);
						System.out.println("nextMenu_id in if part ="+nextMenu_id);
					}			
					else
					{
						nextMenu_id=root+moduleId+this.initLevel.substring(3);
						System.out.println("nextMenu_id in else part ="+nextMenu_id);
					}
					
				}// End of menu level 1
				
			
				if(this.menu_level.equals("2"))
				{
					query=	"	SELECT NVL(MAX(TO_NUMBER(GNUM_MENU_ID)),'0')FROM GBLT_MENU_MST WHERE GNUM_MENU_LEVEL='"+menu_level+"'"+
							"  AND gnum_parent_id='"+this.moduleId+"'"	;
					
					rs = getRecord(query);
		      	  		if(rs.next())
		      	  		{
				      	  		cMenu=rs.getString(1);	
				      	  		if(cMenu.equals("0"))
				      	  		i=1;
				      	  		else{     	  				
				  	  				i=Integer.parseInt(String.valueOf(cMenu.substring(3,5)));
				  	  				i=i+1;
				  	  			}
				      	  		if(String.valueOf(i).length()==1)
				      	  			nextMenu_id=this.moduleId.substring(0,3)+"0"+i+this.moduleId.substring(5);
				      	  		
				      	  		else
				      	  			nextMenu_id=this.moduleId.substring(0,3)+i+this.moduleId.substring(5);
		      	  		}	
	      	  	}// End of menu level 2
				
		
			
				if(this.menu_level.equals("3"))
				{
					System.out.println("inside level 3");
					query=	"	SELECT NVL(MAX(TO_NUMBER(GNUM_MENU_ID)),'0')FROM GBLT_MENU_MST WHERE GNUM_MENU_LEVEL='"+menu_level+"'"+
							"  AND gnum_parent_id='"+this.menuIds+"'";
				
					rs = getRecord(query);
	      	  		
					if(rs.next())
	      	  		{
		      	  		cMenu=rs.getString(1);	
		      	  		
		      	  		System.out.println("cMenu is"+cMenu);
		      	  		if(cMenu.equals("0"))
		      	  			i=1;
		      	  		
		      	  		else
		      	  		{     	  				
		      	  			i=Integer.parseInt(String.valueOf(cMenu.substring(5,7)));
		      	  			i=i+1;
		      	  		System.out.println("i is"+i);
		      	  		}
		      	  	
		      	  		if(String.valueOf(i).length()==1)
		      	  			nextMenu_id=this.menuIds.substring(0,5)+"0"+i+this.moduleId.substring(7);
	      	  		
		      	  		else
		      	  		{
		      	  			
		      	  			nextMenu_id=this.menuIds.substring(0,5)+i+this.moduleId.substring(7);
		      	  			
		      	  			System.out.println("nextMenu_id"+nextMenu_id);
		      	  		}
		      	  		}
			
				}// End of menu level 3
			
			if(this.menu_level.equals("4"))
			{
				System.out.println("inside level 4 in getMenuId() method");
				query=	"	SELECT NVL(MAX(TO_NUMBER(GNUM_MENU_ID)),'0')FROM GBLT_MENU_MST WHERE GNUM_MENU_LEVEL='"+menu_level+"'"+
						"  AND gnum_parent_id='"+this.menuIds+"'";
			
				rs = getRecord(query);
      	  		
				if(rs.next())
      	  		{
	      	  		cMenu=rs.getString(1);	
	      	  		
	      	  		System.out.println("cMenu is"+cMenu);
	      	  		if(cMenu.equals("0"))
	      	  			i=1;
	      	  		
	      	  		else
	      	  		{     	  				
	      	  			i=Integer.parseInt(String.valueOf(cMenu.substring(5,7)));
	      	  			i=i+1;
	      	  		System.out.println("i is"+i);
	      	  		}
	      	  	
	      	  		if(String.valueOf(i).length()==1)
	      	  			nextMenu_id=this.menuIds.substring(0,5)+"0"+i+this.moduleId.substring(7);
      	  		
	      	  		else
	      	  		{
	      	  			
	      	  			nextMenu_id=this.menuIds.substring(0,5)+i+this.moduleId.substring(7);
	      	  			
	      	  			System.out.println("nextMenu_id"+nextMenu_id);
	      	  		}
	      	  		}
		
			}// End of menu level 3
		}
			catch(Exception e)
			{
				System.out.println("Exception is"+e);
			}	
			return nextMenu_id;		  
	      
		*/}
		
		public List initializeUpdateMode()throws Exception
		{
			List AL_List = new ArrayList();
	  		String query = new String("");
	  		System.out.println("chk[0] is...."+chk[0]);
	  		//String arraymenu[]=chk[0].split("^");
	  		String[] arraymenu=chk[0].replace("^", "#").split("#");
	  		System.out.println("arraymenu[0] is...."+arraymenu[0]);
	  		menu_id = arraymenu[0].substring(0,arraymenu[0].length());
	  		hospitalCode=this.getHospitalCode();
	  		
	  		query=	"	SELECT INITCAP (gstr_menu_name)," +
	  				"	gnum_menu_level,"+ 
	  				"	gnum_display_order," +
	  				"	gstr_url,"+ 
	  				"	gstr_menuclass_id, " +
	  				"	(select initcap(GSTR_MENU_NAME) from GBLT_MENU_MST x where x.GNUM_MENU_ID = m.gnum_parent_id " +
	  				//"   AND x.gnum_hospital_code =m.gnum_hospital_code" +
	  				"    AND gnum_isvalid = 1)parent_name,"+ 
	  				"	gnum_hl7_code, 	" +
	  				"	gnum_module_id, " +
	  				"	to_char(gdt_entry_date,'DD-Mon-YYYY')," +
	  				"	gnum_menu_id," +
	  				"	gnum_parent_id, GNUM_APPLICATION_TYPE"+
	  				"	FROM gblt_menu_mst m WHERE gnum_menu_id ="+this.menu_id+ " " +
	  				//"AND gnum_hospital_code='100' " +
	  				" AND gnum_isvalid ="+this.combo1;
	  				//"	FROM gblt_menu_mst m WHERE gnum_menu_id ="+this.menu_id+ " AND gnum_hospital_code='"+this.hospitalCode+"' AND gnum_isvalid ="+this.combo1;
	  		
	  		System.out.println("query in initialization"+query);
	  		try
	  		{
	  			AL_List = getDetail(query);
	  		}
	  		catch(Exception e)
	  		{
	  		}
	
	  		menu_name 		=(String)AL_List.get(0);
	  		menu_level  	=(String)AL_List.get(1);
	  		display_order 	=(String)AL_List.get(2);
	  		url				=(String)AL_List.get(3);
	        menuclass_id 	=(String)AL_List.get(4);
	        parent_name    	=(String)AL_List.get(5);
	        hl7Code 		=(String)AL_List.get(6);
	    	moduleId   		=(String)AL_List.get(7);
	    	entry_date 		=(String)AL_List.get(8);
	    	menu_id			=(String)AL_List.get(9);
	    	parent_id		=(String)AL_List.get(10);	
	    	applicationtype_id=(String)AL_List.get(11);	
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
				
				//String query1 = "UPDATE GBLT_ROLE_SEAT_MENU_DTL SET GNUM_ISVALID ='0' WHERE GNUM_MENU_ID = ? AND GNUM_HOSPITAL_CODE='"+hospitalCode+"'";
				//String query2 = "UPDATE GBLT_MENU_MST SET GNUM_ISVALID ='0' WHERE GNUM_MENU_ID = ? AND GNUM_HOSPITAL_CODE='"+this.hospitalCode+"'";
				//query commented by HMIS Maharshtra for Menu Masters changes for Global
				//String query2 = "UPDATE GBLT_MENU_MST SET GNUM_ISVALID ='0' WHERE GNUM_MENU_ID = ? AND GNUM_HOSPITAL_CODE='100'";
				
				String query2 = "UPDATE GBLT_MENU_MST SET GNUM_ISVALID ='0' WHERE GNUM_MENU_ID = ? ";
				
				//ps1 = conn.prepareStatement(query1);
				ps2 = conn.prepareStatement(query2);
				int counter = 0;
				
				for(int i=0;i<this.chk.length;i++)
				{
					String[] arraymenu=this.chk[i].replace("^", "#").split("#");
					String code = arraymenu[0].substring(0,arraymenu[0].length());
					//String code = this.chk[i].substring(0,this.chk[i].length()-1);
					
					//ps1.setString(1,code);
					ps2.setString(1,code);
					
					//ps1.execute();
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
			
			pK	= String.valueOf(this.menu_id);
			//oldRecord	= "select 'GSTR_URL'||'^'||GSTR_URL ||'#'||'GNUM_HL7_CODE'||'^'||GNUM_HL7_CODE ||'#'||'GNUM_ISVALID'||'^'||GNUM_ISVALID from GBLT_MENU_MST where gnum_hospital_code='"+this.hospitalCode+"' and GNUM_MENU_ID='"+this.menu_id+"'";
			//oldRecord	= "select 'GSTR_URL'||'^'||GSTR_URL ||'#'||'GNUM_HL7_CODE'||'^'||GNUM_HL7_CODE ||'#'||'GNUM_ISVALID'||'^'||GNUM_ISVALID from GBLT_MENU_MST where gnum_hospital_code='100' and GNUM_MENU_ID='"+this.menu_id+"'";
			oldRecord	= "select 'GSTR_URL'||'^'||GSTR_URL ||'#'||'GNUM_HL7_CODE'||'^'||GNUM_HL7_CODE ||'#'||'GNUM_ISVALID'||'^'||GNUM_ISVALID from GBLT_MENU_MST where GNUM_MENU_ID='"+this.menu_id+"'";
			oldVal		= super.getField(oldRecord);
			
			newVal		= "	GSTR_URL^"+this.url+"#GNUM_HL7_CODE^"+this.hl7Code+"#GNUM_ISVALID^"+this.isvalid;
			
			query1	=	"	SELECT gstr_menu_name FROM gblt_menu_mst a " + 	
						//"	WHERE gnum_hospital_code='"+this.hospitalCode+"' and " +
						"	WHERE " +
						//"gnum_hospital_code='100' and " +
						"   a.gnum_parent_id ='"+this.parent_id+"' AND " +
						"   lower(gstr_menu_name)=lower('"+this.menu_name+"') AND  a.GNUM_MENU_ID !="+this.menu_id;
						//+" AND  GNUM_APPLICATION_TYPE !="+this.applicationtype_id ;
			
			System.out.println(pK +"query1  "+query1);
			redundantname=super.isRedundentName(query1);
			if(redundantname)
			{
				query3	=	"	SELECT GNUM_ISVALID FROM gblt_menu_mst a " + 	
							//"	WHERE gnum_hospital_code='"+this.hospitalCode+"' and " +
				           "	WHERE " +
				           //" gnum_hospital_code='100' and " +
							"   a.gnum_parent_id ='"+this.parent_id+"' AND " +
							"   lower(gstr_menu_name)=lower('"+this.menu_name+"')";
			
				System.out.println("query 3 "+query3);
				isValid	=Integer.parseInt(this.isvalid);
				redundant=super.isRecordExist(query3,isValid);
				if(redundant)
				{
					this.status=" Menu Already Exits, Please Write Other Menu.....!";
				}
				else
				{
					System.out.println("isvalid is different");
					//query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"',GSTR_URL='"+this.url+"', GNUM_HL7_CODE='"+this.hl7Code+"',GNUM_ISVALID='"+this.isvalid+"'"+
								//" WHERE GNUM_MENU_ID =  '"+this.menu_id+"' and gnum_hospital_code='"+this.hospitalCode+"' and  GNUM_ISVALID in (1,2) ";	
					
					//query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"',GSTR_URL='"+this.url+"', GNUM_HL7_CODE='"+this.hl7Code+"',GNUM_ISVALID='"+this.isvalid+"'"+
					//" WHERE GNUM_MENU_ID =  '"+this.menu_id+"' and gnum_hospital_code='100' and  GNUM_ISVALID in (1,2) ";	
		
					//query changed for Maharashtra Project changes
					//query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"',GSTR_URL='"+this.url+"', GNUM_HL7_CODE=NULL::numeric,GNUM_ISVALID='"+this.isvalid+"'"+
					//" WHERE GNUM_MENU_ID =  '"+this.menu_id+"' and gnum_hospital_code='100' and  GNUM_ISVALID in (1,2) ";	
		            //query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"',GSTR_URL='"+this.url+"', GNUM_HL7_CODE=NULL::numeric,GNUM_APPLICATION_TYPE ='"+this.applicationtype_id+"' ,GNUM_ISVALID='"+this.isvalid+"'"+
					query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"',GSTR_URL=NVL('"+this.url+"',NULL), GNUM_HL7_CODE=NULL::numeric,GNUM_ISVALID='"+this.isvalid+"'"+
					" WHERE GNUM_MENU_ID =  '"+this.menu_id+"' and  GNUM_ISVALID in (1,2) ";	
					
					query2	=	" UPDATE GBLT_ROLE_SEAT_MENU_DTL SET GNUM_ISVALID='"+this.isvalid+"' WHERE GNUM_MENU_ID =" + this.menu_id;
					isRun	=	true;
				}
			}
			else
			{
				//query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"', GSTR_URL='"+this.url+"', GNUM_HL7_CODE='"+this.hl7Code+"',GNUM_ISVALID='"+this.isvalid+"'"+
							//" WHERE GNUM_MENU_ID ='"+ this.menu_id+"' and gnum_hospital_code='"+this.hospitalCode+"' and  GNUM_ISVALID in (1,2)  ";

				//query changed for Maharashtra Project changes
				//query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"', GSTR_URL='"+this.url+"',GNUM_HL7_CODE=NULL::numeric,GNUM_ISVALID='"+this.isvalid+"'"+
				//" WHERE GNUM_MENU_ID ='"+ this.menu_id+"' and gnum_hospital_code='100' and  GNUM_ISVALID in (1,2)  ";

				//query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"', GSTR_URL='"+this.url+"',GNUM_HL7_CODE=NULL::numeric,GNUM_APPLICATION_TYPE ='"+this.applicationtype_id+"' ,GNUM_ISVALID='"+this.isvalid+"'"+
				query	=	" UPDATE GBLT_MENU_MST SET GSTR_MENU_NAME='"+this.menu_name+"', GSTR_URL=NVL('"+this.url+"',NULL),GNUM_HL7_CODE=NULL::numeric ,GNUM_ISVALID='"+this.isvalid+"'"+
				" WHERE GNUM_MENU_ID ='"+ this.menu_id+"' and  GNUM_ISVALID in (1,2)  ";

				query2	=	" UPDATE GBLT_ROLE_SEAT_MENU_DTL SET GNUM_ISVALID='"+this.isvalid+"' WHERE GNUM_MENU_ID ="+ this.menu_id;
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
					//st.addBatch(query2);
					st.executeBatch();
					ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_MENU_MST").updateAuditLog(request,conn);
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
			else
			{
				this.status=" Menu Already Exits, Please Write Other Menu.....!";
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
			
				String query	=	"	select GNUM_MENU_ID,initcap(GSTR_MENU_NAME) from GBLT_MENU_MST where " +
						//"gnum_hospital_code='100' and " +
						" GNUM_ISVALID='1'"+
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
		 	 if(!(this.initLevel==null) && !(this.initLevel.equals("")))
			 {
				 if(this.menu_level.equals("1"))	
				 {
		 		 	query=		"	SELECT a.gnum_module_id, INITCAP (a.gstr_module_name)"+	
				 				"	FROM gblt_metatable_type_mst a WHERE a.gnum_module_id   NOT IN"+  	  		
					    	  	"	("+
					    	  	"      SELECT NVL(b.gnum_module_id,0)FROM gblt_menu_mst b"+
					    	  	"	   WHERE  GNUM_PARENT_ID ='"+this.initLevel+"' " +
					    	  		//	"and gnum_hospital_code='"+this.hospitalCode+"' " +
					    	  	"and gnum_isvalid = '1' "+ //gnum_isvalid = '1' AND
					    	  	"    )"+
					    	  	"	AND gbl_isvalid = '1' " +
					    	  	//"and gnum_hospital_code='100' " +
					    	  	//"  gnum_hospital_code='"+this.hospitalCode+"' "+
					    	 // "gnum_hospital_code='100' "+
					    	  	"   order by initcap(gstr_module_name)";			 		
		 			 System.out.println("query11111..."+query);	 
		 		 	
				 }
		 		 	
				 else if(this.menu_level.equals("2"))
		 		 {
					  query		=	"	SELECT GNUM_MENU_ID, INITCAP(GSTR_MENU_NAME)"+	
				 					"	FROM GBLT_MENU_MST WHERE  GNUM_ISVALID='1' and  " +
				 					//"   gnum_hospital_code='"+this.hospitalCode+"' "+
				 					//"  gnum_hospital_code='100' "+
				 					" GNUM_PARENT_ID='"+this.initLevel+"' order by INITCAP(GSTR_MENU_NAME)";
					  System.out.println("query2222..."+query);	 
		 		 }
				 
				 else if(this.menu_level.equals("3"))
				 {
					 query	=	"	SELECT GNUM_MENU_ID, INITCAP(GSTR_MENU_NAME) "+	
			 					"	FROM GBLT_MENU_MST WHERE  GNUM_ISVALID='1' "+
			 					//"   and  gnum_hospital_code='"+this.hospitalCode+"' "+
			 					//"  and gnum_hospital_code='100' "+
			 					"	and GNUM_PARENT_ID='"+this.initLevel+"' order by INITCAP(GSTR_MENU_NAME)";
					 System.out.println("query3333..."+query);	 
				 }
				 else if(this.menu_level.equals("4"))
				 {
					 System.out.println("inside viewModuleId() and level 4 menu level"); 
					 query	=	"	SELECT GNUM_MENU_ID, INITCAP(GSTR_MENU_NAME) "+	
			 					"	FROM GBLT_MENU_MST WHERE  GNUM_ISVALID='1' "+
			 					//"   and  gnum_hospital_code='"+this.hospitalCode+"' "+
			 					//"  and gnum_hospital_code='100' "+
			 					"	and GNUM_PARENT_ID='"+this.initLevel+"' order by INITCAP(GSTR_MENU_NAME)";
					 System.out.println("query44444..."+query);	 
				 }
				 
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
				  }		 
		 	 	else
		 	 	{
		 	 		htmlStr += "<option value='0'>Select Module</option>";
		    	}
		 	return htmlStr;
		}	    
		
	   public String getMenuName()throws Exception
	   {
		   String moduleName="";
		   System.out.println("module id is"+this.moduleId);
		   String query=" SELECT GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST WHERE " +
		                //" gnum_hospital_code='"+this.hospitalCode+"' "+
		                //"gnum_hospital_code='100'"+
		   	          	"  GNUM_MODULE_ID="+this.moduleId;
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
	public String getPortalDisplay() {
		return portalDisplay;
	}
	public void setPortalDisplay(String portalDisplay) {
		this.portalDisplay = portalDisplay;
	}
	public String[] getChk() {
		return chk;
	}
	}//end of menu mst