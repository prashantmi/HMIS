package usermgmt;
//import usermgmt;


import java.sql.*;
import java.util.*;
import java.io.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public  class Tab extends TagSupport
{
	protected LinkedHashMap tabList=null;
	protected String selectedTab	=null;
	protected String onclick		="";
	protected String tabLabel		="";
	protected String width			="100%";
	protected String align			="center";
	
	private String str				="";
	
	public void setTabList(LinkedHashMap tabList)
	{
		this.tabList=tabList;
	}
	
	public void setSelectedTab(String  selectedTab)
	{
		this.selectedTab=selectedTab;
	}
	public void setTabLabel(String  tabLabel)
	{
		this.tabLabel=tabLabel;
	}
	public void setOnclick(String  onclick)
	{
		this.onclick=onclick;
	}
	public void setWidth(String  width)
	{
		this.width=width;
	}
	public void setAlign(String  align)
	{
		this.align=align;
	}
	public int doStartTag()//throws JspTagException
	{
		try
		{
			JspWriter out =pageContext.getOut();
						
			String label		="";
			String pagename		="";
			boolean FLAG=false;
			
			if(tabList!=null)
			{
				if(selectedTab == null)
				{
					Set s		=tabList.keySet();
					Iterator it	=s.iterator();
					selectedTab	=(String)it.next();
					selectedTab	=(String)tabList.get(selectedTab);
				}
			
				int i=0;

				Set s		=tabList.keySet();
				Iterator it	=s.iterator();
			
				str += "<table cellspacing=0 cellpadding=0 width=\""+width+"\" border=0 align=\""+align+"\" valign=top>\n";
				str += "  <tr colspan=2>\n";
				str += "	<td >\n";
				str += "      <table cellspacing=0 cellpadding=0 width=\"100%\" border=0 align=\"center\">\n";
				str += "       <tr >\n";
	         	str += "		 <td colspan=3>\n";
				
				str += "		   <table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n";
				str += "			<tbody>\n";
				str += "			<tr>\n";

				while(it.hasNext())
				{
					label		=(String)it.next();
					pagename	=(String)tabList.get(label);

					str += "		<td valign=bottom align=BASELINE colspan=3>\n";
					str += "		 <table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n";
					str += "		 <tbody>\n";
					str += "		 <tr>\n";
					str += "			<td width=1 height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
					str += "			<td width=\"269\" height=1 class=DIVIDER><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
					str += "		</tr>\n";
					str += "		<tr>\n";
					str += "		<td class=DIVIDER><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";

					if(selectedTab.equals(pagename))
					{
						str += "	<td id='cid' class=SELECTEDTAB noWrap height=21 >\n";
						str += "	<b>&nbsp;" + label + "&nbsp;</b>\n";
						str += "	</td>\n";

						if(tabList.size()-i == 1)
						{
							str += "<td class=DIVIDER height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
						}
					}
					else
					{

						str += "	<td id='cid' class=TAB style=cursor:hand nowrap tabindex=1  height=20 " ;
						str += "		onClick=\"onClickTab('" + pagename + "');\" onKeyPress=\"onEnterTab(event,'" + pagename + "')\" ";
						str += "		onMouseOver=\"setColor('" + i + "');\" onMouseOut=\"resetColor('" + i + "');\" ";
						str += "		onFocus=\"setColor('" + i + "');\" onBlur=\"resetColor('" + i + "');\" >\n";
						str += "		<img height=1 src=\"/images/tppppp.gif\" width=1 border=0>\n";
						str += "		<a> &nbsp;" + label + "&nbsp; </a><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
						str += "	<td width=\"10\" class=DIVIDER><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
						str += "	</tr>\n";
						str += "	<tr>\n";
						str += "	<td class=DIVIDER width=1 height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
						str += "	<td class=DIVIDER height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
					}

					str += "</tr>\n";
					str += "</tbody>\n";
					str += "</table>\n";
					str += "</td>\n";
					i++;
				}
			}
			else
			{
				str += "<table cellspacing=0 cellpadding=0 width=\""+width+"\" border=0 align=\""+align+"\" valign=top>\n";
				str += "  <tr colspan=2>\n";
				str += "	<td >\n";
				str += "      <table cellspacing=0 cellpadding=0 width=\"100%\" border=0 align=\"center\">\n";
				str += "       <tr >\n";
	         	str += "		 <td colspan=3>\n";
				
				str += "		   <table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n";
				str += "			<tbody>\n";
				str += "			<tr>\n";


				str += "		<td valign=bottom align=BASELINE colspan=3>\n";
				str += "		 <table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n";
				str += "		 <tbody>\n";
				str += "		 <tr>\n";
				str += "			<td width=1 height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
				str += "			<td width=\"269\" height=1 class=DIVIDER><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
				str += "		</tr>\n";
				str += "		<tr>\n";
				str += "		<td class=DIVIDER><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
				
				str += "	<td id='cid' class=SELECTEDTAB noWrap height=21 >\n";
				str += "	<b>&nbsp;" + tabLabel + "&nbsp;</b>\n";
				str += "	</td>\n";

				str += "<td class=DIVIDER height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
				
				
				str += "</tr>\n";
				str += "</tbody>\n";
				str += "</table>\n";
				str += "</td>\n";		
			}
			str += "<td valign=bottom width=\"100%\">\n";
			str += "<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n";
			str += "<tr>\n";
			str += "<td class=DIVIDER height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
			str += "</tr>\n";
			str += "</table>\n";
			str += "</td>\n";
			str += "</tr>\n";
			str += "</tbody>\n";
			str += "</table>\n";
			
			str += " </td>\n";
			str += "	 <td valign=bottom width=490 colspan=2>\n";
			str += "     <table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n";
			str += "	 <tr>\n";
			str += "	 <td class=DIVIDER height=1><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
			str += "	 </tr>\n";
			str += "	 </table>\n";
			str += "	 </td>\n";
			str += "     </tr>\n";
			str += "     <tr>\n";
			str += "	 <td colspan=5>\n";
			str += "	<table cellspacing=0 cellpadding=0 width=\"100%\" border=0 align=\"center\" valign=top>\n";
			str += "	<tr>\n";
			str += "	<td class=DIVIDER width=1 rowspan=5> <img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
			str += "	 <td width=10 rowspan=5><img height=8 src=\"/images/tppppp.gif\" idth=10 border=0 width=8></td>\n";
			str += "	<td height=10 width=\"1140\"><img height=1 src=\"/images/tppppp.gif\" width=1 border=0></td>\n";
			str += "	</tr>\n";
			str += "<td width=\"100%\" >\n";
			out.print(str);

		}
		catch(Exception e)
		{
			System.out.println("exception in doStartTag() "+e);
		}

		return 	EVAL_BODY_INCLUDE;
	}//end of dostart


	public int doEndTag()throws JspException
	{
		try
		{
			str="";
			JspWriter out =pageContext.getOut();
		
		str += "</table></td></tr></table></td></tr></table>\n";
		str += "<input type='hidden' name='selectedTab' value=''>";
		out.print(str);
		}
		catch(Exception e)
		{
			System.out.println("exception in doStartTag() "+e);
		}

		return EVAL_PAGE;

	}//end of do end tag
}