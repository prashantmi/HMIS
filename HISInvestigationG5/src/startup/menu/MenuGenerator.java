package startup.menu;

import hisglobal.backutil.HisDAO;
import javax.sql.rowset.WebRowSet;
import startup.qryHandler_startup;

public class MenuGenerator 
{
	private String		seatId	=null;
	private String		empId	=null;
	private String		roleId	=null;
	private String		level	="1";
	private	String		hyper	="";
	private String[]	bgColor	={"","BGCOLOR='#F3F5FD'","BGCOLOR='#E7EAF7'","BGCOLOR='#DEE1F2'","BGCOLOR='#DAEDF8'"};
    private String userName = null ;
	private String userId   = null ;

	/*
	setter method
	*/
	
	public void setUserId(String userId)
	{
		this.userId=userId;
	}
		
	public void setUserName(String userName)
	{
		this.userName=userName;
	}

	public void setSeatId(String seatId)
	{
		this.seatId=seatId;
	}

	public void setEmpId(String empId)
	{
		this.empId=empId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId=roleId;
	}

	public void setLevel(String level)
	{
		this.level=level;
	}

	/*
	getter method
	*/

	public String getSeatId()
	{
		return this.seatId;
	}

	public String getEmpId()
	{
		return this.empId;
	}

	public String getRoleId()
	{
		return this.roleId;
	}

	public String getLevel()
	{
		return this.level;
	}

	public String getHyper()
	{
		return this.hyper;
	}

    public String getUserId()
	{
		return this.userId;
	}
		
	public String getUserName()
	{
		return this.userName;
	}
    
	/*
	Method Used
	*/

	public void setRoleIDBySeatId(String seatId)
	{
//		String query	=	"SELECT GNUM_ROLE_ID FROM GBLT_SEAT_DTL "+
//							"WHERE GNUM_SEATID='"+seatId+"' ORDER BY "+
//							"GNUM_ROLE_ID DESC";
		String query = qryHandler_startup.getQuery("select.GBLT_SEAT_DTL.0");
		HisDAO obj = new HisDAO("menu","MenuGenerator.java");
		WebRowSet rs = null; 
		try
		{
			//rs=super.getRecord(query);//new Conn().getRecord(query);
			int index = obj.setQuery(query);
			obj.setQryValue(index,1,seatId);
			rs = obj.executeQry(index);
			
			if(rs.next())
				this.setRoleId(rs.getString(1));
		}
		catch(Exception e)
		{
		   System.out.println("Exception at setRoleIDBySeatId "+e);
		}
		finally{
			obj.free();
			rs = null;
		}
	}//END OF setRoleIDBySeatId

	public String getBgColor(int tableLevel,int currentLevel)
	{
		try
		{
			return this.bgColor[currentLevel-tableLevel];
		}
		catch(Exception e)
		{
		   return "#FFFFFF";
		}
	}//END OF getBgColor

	public String getWidth(int tableLevel,int currentLevel)
	{
		if(currentLevel!=tableLevel)
			return "width='1'";
		else
			return "";
	}
	public String getTopLevelMenuRow(String selectedTopMenu)
	{
//		String query	=	"SELECT GNUM_MENU_ID, INITCAP(GSTR_MENU_NAME),GSTR_URL FROM GBLT_MENU_MST "+
//							"WHERE  GNUM_PARENT_ID IS NULL AND GBL_ISVALID='1'";
		
		String query    = qryHandler_startup.getQuery("select.GBLT_MENU_MST.0");
		String tableRow	=	"";
		WebRowSet rs= null;
		HisDAO obj = null;
		try
		{
			obj =new HisDAO("menu","MenuGenerator.java");
			int index = obj.setQuery(query);
			
			rs=obj.executeQry(index);//new Conn().getRecord(query);

			while(rs.next())
				if(!this.level.equals("1"))
					tableRow	+=	this.getLevelRow(rs.getString(1),rs.getString(2),rs.getString(3),selectedTopMenu,"1");//(id,name,url,selected,level)
				else
					tableRow	+=	this.getNewLevelRow(rs.getString(1),rs.getString(2),rs.getString(3),"1");//(id,name,url,level)
		}
		catch(Exception e)
		{
		   System.out.println("Exception at getTopLevelMenuRow "+e);
		}
		finally{
			obj.free();
			//rs.close();
			rs = null;
		}
		

		this.hyper =selectedTopMenu+",";

		return tableRow;
	}


	public String getFirstLevelMenuRow(String parentID,String selectedMenu)
	{
//		String query	=	"SELECT GNUM_MENU_ID, INITCAP(GSTR_MENU_NAME),GSTR_URL FROM GBLT_MENU_MST "+
//							"WHERE GBL_ISVALID='1' AND GNUM_PARENT_ID='"+parentID+"' "+
//							"AND GNUM_MENU_ID IN "+
//							"(SELECT GNUM_MENU_ID FROM GBLT_ROLE_DTL "+
//							"WHERE GNUM_ROLE_ID='"+this.getRoleId()+"' AND GBL_ISVALID='1') ORDER BY GNUM_DISPLAY_ORDER ";
		String query  = qryHandler_startup.getQuery("select.GBLT_MENU_MST.1");
		String tableRow	=	"";
		//System.out.println("pradeep : getting menu url");
		//System.out.println("Query "+query);
		WebRowSet rs = null;
		HisDAO obj = new HisDAO("menu","MenuGenerator.java");
		try
		{
			//rs= super.getRecord(query);//new Conn().getRecord(query);
			int index = obj.setQuery(query);
			obj.setQryValue(index,1,parentID);
			obj.setQryValue(index,2,this.getRoleId());
			obj.executeQry(index);
			while(rs.next())
				if(!this.level.equals("2"))
					tableRow	+=	this.getLevelRow(rs.getString(1),rs.getString(2),rs.getString(3),selectedMenu,"2");//(id,name,url,selected,level)
				else
					tableRow	+=	this.getNewLevelRow(rs.getString(1),rs.getString(2),rs.getString(3),"2");//(id,name,url,level)

		}
		catch(Exception e)
		{
		   System.out.println("Exception at getFirstLevelMenuRow "+e);
		}
		finally{
			obj.free();
			rs = null;
		}

		this.hyper +=selectedMenu+",";
		return tableRow;
	}


	public String getSecondLevelMenuRow(String parentID,String selectedMenu)
	{
//		String query	=	"SELECT GNUM_MENU_ID, INITCAP(GSTR_MENU_NAME),GSTR_URL FROM GBLT_MENU_MST "+
//							"WHERE GBL_ISVALID='1' AND GNUM_PARENT_ID='"+parentID+"' "+
//							"AND GNUM_MENU_ID IN "+
//							"(SELECT GNUM_MENU_ID FROM GBLT_ROLE_DTL "+
//							"WHERE GNUM_ROLE_ID='"+this.getRoleId()+"' AND GBL_ISVALID='1') ORDER BY GNUM_DISPLAY_ORDER";
		String query  = qryHandler_startup.getQuery("select.GBLT_MENU_MST.1");
		String tableRow	=	"";

		WebRowSet rs = null;
		HisDAO obj = null;
		try
		{
			obj = new HisDAO("menu","MenuGenerator.java");
			int index = obj.setQuery(query);
			obj.setQryValue(index,1,parentID);
			obj.setQryValue(index,2,this.getRoleId());
			obj.executeQry(index);

			while(rs.next())
				if(!this.level.equals("3"))
					tableRow	+=	this.getLevelRow(rs.getString(1),rs.getString(2),rs.getString(3),selectedMenu,"3");//(id,name,url,selected,level)
				else
					tableRow	+=	this.getNewLevelRow(rs.getString(1),rs.getString(2),rs.getString(3),"3");//(id,name,url,level)
		}
		catch(Exception e)
		{
		   System.out.println("Exception at getSecondLevelMenuRow "+e);
		}
		finally{
			obj.free();
			rs = null;
		}
		this.hyper +=selectedMenu+",";
		return tableRow;
	}


	public String getThirdLevelMenuRow(String parentID)
	{
//		String query	=	"SELECT GNUM_MENU_ID, INITCAP(GSTR_MENU_NAME),GSTR_URL FROM GBLT_MENU_MST "+
//							"WHERE GBL_ISVALID='1' AND GNUM_PARENT_ID='"+parentID+"' "+
//							"AND GNUM_MENU_ID IN "+
//							"(SELECT GNUM_MENU_ID FROM GBLT_ROLE_DTL "+
//							"WHERE GNUM_ROLE_ID='"+this.getRoleId()+"' AND GBL_ISVALID='1') ORDER BY GNUM_DISPLAY_ORDER";

		String query  = qryHandler_startup.getQuery("select.GBLT_MENU_MST.1");
		String tableRow	=	"";

		HisDAO obj = null;
		WebRowSet rs = null;
		try
		{
			obj = new HisDAO("menu","MenuGenerator.java");
			int index = obj.setQuery(query);
			obj.setQryValue(index,1,parentID);
			obj.setQryValue(index,2,this.getRoleId());
			obj.executeQry(index);
			//rs=super.getRecord(query);//new Conn().getRecord(query);

			while(rs.next())
				tableRow	+=	this.getNewLevelRow(rs.getString(1),rs.getString(2),rs.getString(3),"4");//(id,name,url,level)
		}
		catch(Exception e)
		{
		   System.out.println("Exception at getThirdLevelMenuRow "+e);
		}

		return tableRow;
	}



	private String getLevelRow(String menuID,String menuName,String URL,String selectedTopMenu,String level)
	{
		
		if(menuID.equals(selectedTopMenu))
			return getLevelRowSelected(menuID,menuName,URL,selectedTopMenu,level);
		else
			return getLevelRowUnselected(menuID,menuName,URL,selectedTopMenu,level);
	}

	
	private String getURLString(String menuID,String URL,String level)
	{
		//if(URL==null | URL.equals(""))	//pradeep
		if(URL==null)
		{	
			//System.out.println("menu userId ="+this.getUserId());
			//System.out.println("menu userName ="+this.getUserName());	
			return	"./cnt_Menu.jsp?level="+(Integer.parseInt(level)+1)+"&hyper="+this.getHyper()+menuID+",&roleId="+this.getRoleId()+
					"&seatId="+this.getSeatId()+"&SEATID="+this.getSeatId()+"&empId="+this.getEmpId()+"&userId="+this.getUserId()+"&userName="+this.getUserName();


		}
		else
		{
			//System.out.println("menu userId ="+this.getUserId());
			//System.out.println("menu userName ="+this.getUserName());
			return  URL+"?&level=PAGE&seatId="+this.getSeatId()+"&empId="+this.getEmpId()+
					"&roleId="+this.getRoleId()+"&SEATID="+this.getSeatId()+"&hyper="+this.getHyper()+"&userId="+this.getUserId()+"&userName="+this.getUserName();//+"&URL="+URL;
		}

	}


	public String getPageUrl(String hyperString)
	{
		
		String[] sequence	=	hyperString.split(",");
		String pageURL	=	"";

		for(int i=0;i<sequence.length;i++)
		{
			String partName=this.getURLPartName(sequence[i]);
			pageURL	+=	this.getPartURL(partName,i,sequence);
			if(i!=sequence.length-1)
				pageURL += "&nbsp;&gt;&nbsp;";
				
		}
		return pageURL;
	}


	private String getURLPartName(String menuId) 
	{
//		String query	=	"SELECT INITCAP(GSTR_MENU_NAME) FROM GBLT_MENU_MST "+
//							"WHERE GBL_ISVALID='1' AND GNUM_MENU_ID='"+menuId+"' ";
String query = qryHandler_startup.getQuery("select.GBLT_MENU_MST.2");
		HisDAO obj = null;
		WebRowSet rs = null;
		try	{
			obj = new HisDAO("menu","MenuGenerator.java");
			int index = obj.setQuery(query);
			obj.setQryValue(index,1,menuId);
			obj.executeQry(index);
			//HisResultSet rs=super.getRecord(query);//new Conn().getRecord(query);
			if(rs.next())
				return rs.getString(1);
		}
		catch(Exception e){
		   //System.out.println("Exception at getURLPartName "+e);
		}

		return "";
	}


	private String getPartURL(String partName,int i,String[] hyper)
	{
		String partURL	=	"<a href=\"../../startup/cnt_Menu.jsp?level="+(i+2)+"&hyper=";

		for(int j=0;j<=i;j++)
			partURL += hyper[j]+",";

		partURL += "&seatId="+this.getSeatId()+"&empId="+this.getEmpId()+"&roleId="+this.getRoleId()+"&userId="+this.getUserId()+"&userName="+this.getUserName()+"\" >"+partName+"</a>";

		return partURL;
	}



	/*-----------------------------------------------Different Level Rows ***********************************/

	private String getLevelRowUnselected(String menuID,String menuName,String URL,String selectedTopMenu,String level)
	{
		return "<TR><!-- Start Sample Unselected Row -->\n"+
				"\t<TD WIDTH=\'10\' HEIGHT='5' VALIGN='top' >"+
				"<IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='10' HEIGHT='6' HSPACE='0' VSPACE='0' BORDER='0'></TD>"+
				"\n\t<TD colspan='2' width='174' ALIGN='LEFT' VALIGN='top'><A HREF='"+this.getURLString(menuID,URL,level)+"' CLASS='PTMENULINK' ACCESSKEY=1>"+
				( (URL==null)?"<IMG SRC='../images/PT_PORTAL_IC_PLUS_ENG_1.gif' BORDER='0' hspace='0' VSPACE='2' HEIGHT='10' WIDTH='10' Alt='Expand' align='left'>":"" )+
				"&nbsp;"+menuName+"</A></TD>"+
				"\n\t<TD "+this.getBgColor(Integer.parseInt(level),Integer.parseInt(this.level))+" WIDTH='1'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='1' BORDER='0' HSPACE='0' VSPACE='0'></TD>"+
				"\n</TR>"+
				"<TR>\n\t"+
				"<TD WIDTH='10'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='10' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD WIDTH='15'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='15' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD WIDTH='159'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='159' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD "+this.getBgColor(Integer.parseInt(level),Integer.parseInt(this.level))+" WIDTH='1'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n</TR><!-- End Sample Unselected Row -->";
	}

	private String getLevelRowSelected(String menuID,String menuName,String URL,String selectedTopMenu,String level)
	{
		return "<TR><!-- Start Selected Menu Row -->"+
				"\n\t<TD COLSPAN='4' HEIGHT='6' WIDTH='185' BGCOLOR='#ffffff'><IMG SRC='../images/PT_PORTAL_H_HILITE_TOP_"+(Integer.parseInt(this.getLevel())-Integer.parseInt(level))+"_ENG_1.gif' WIDTH='185' HEIGHT='6' BORDER='0' HSPACE='0' VSPACE='0'></TD>"+
				"\n</TR>"+
				"\n<TR>"+
				"\n\t<TD  STYLE = 'background-image: url(../images/PT_PORTAL_H_HILITE_LEFT_1_ENG_1.gif)' CLASS='hileft1' WIDTH='6' BGCOLOR='#ffffff'>"+
				"<IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='1' HSPACE='0' VSPACE='0' BORDER='0' ALT=''></TD>"+
				"<TD colspan='2' width='171' ALIGN='LEFT' VALIGN='top' BGCOLOR='#FFFFFF'>"+
				"<A HREF='"+this.getURLString(menuID,URL,level)+"' CLASS='PTMENULINK'>"+
				"<IMG SRC='../images/PT_PORTAL_IC_MINUS_ENG_1.gif' BORDER='0' hspace='0' VSPACE='2' HEIGHT='10' WIDTH='10' Alt='Collapse' align='left'>&nbsp;"+menuName+"</A></TD>"+
				"\n\t<TD BGCOLOR='#ffffff' WIDTH='1'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='1' HSPACE='0' VSPACE='0' ALT=''></TD>"+
				"\n</TR>"+
				"\n<TR>"+
				"\n\t<TD COLSPAN='4' HEIGHT='6' WIDTH='185' BGCOLOR='#ffffff'><IMG SRC='../images/PT_PORTAL_H_HILITE_BOT_"+(Integer.parseInt(this.getLevel())-Integer.parseInt(level))+"_ENG_1.gif' WIDTH='185' HEIGHT='6' BORDER='0' HSPACE='0' VSPACE='0'></TD>"+
				"\n</TR>"+
				"\n<TR>"+
				"\n\t<TD></TD>"+
				"\n\t<TD></TD>"+
				"<TD></TD>"+
				"\n\t<TD BGCOLOR='#999966' WIDTH='1'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='4' HSPACE='0' VSPACE='0' ALT=''></TD>"+
				"\n</TR>"+
				"\n<TR>"+
				"\n\t<TD WIDTH='10'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='10' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD WIDTH='15'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='15' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD WIDTH='159'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='159' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD BGCOLOR='#999966' WIDTH='1'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='4' HSPACE='0' VSPACE='0'></TD>"+
				"\n</TR><!-- End Selected Menu Row -->";

	}

	private String getNewLevelRow(String menuID,String menuName,String URL,String level)
	{
		return "\n<TR ALIGN='LEFT'>"+
				"\n\t<TD><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='1' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD colspan='2' VALIGN='top'><A HREF='"+this.getURLString(menuID,URL,level)+"' CLASS='PTMENULINK' NAME='H2L1R' ACCESSKEY=2>"+
				( (URL==null)?"<IMG SRC='../images/PT_PORTAL_IC_PLUS_ENG_1.gif' BORDER='0' hspace='0' VSPACE='2' HEIGHT='10' WIDTH='10' Alt='Expand' align='left'>":"" )+
				"&nbsp;"+menuName+"</a></TD>"+
				"\n\t<TD BGCOLOR='#FFFFFF' WIDTH='1'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='1' HSPACE='0' VSPACE='0'></TD>"+
				"\n</TR>"+
				"\n<TR ALIGN='LEFT'>"+
				"\n\t<TD><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='6' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD WIDTH='15'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='15' HEIGHT='6' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='6' HSPACE='0' VSPACE='0'></TD>"+
				"\n\t<TD BGCOLOR='#FFFFFF' WIDTH='1'><IMG SRC='../images/PT_PORTAL_CLEAR_DOT_ENG_1.gif' WIDTH='1' HEIGHT='6' HSPACE='0' VSPACE='0'></TD>"+
				"\n</TR>";
	}
/*********************************************************************************************************/
}

