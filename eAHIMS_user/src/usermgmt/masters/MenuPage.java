/*
 Project		:	HIS
 FileName	:	Page.java
 Version		:	1.0
 Developer	:	HIS Team
 */

/*
 FUNCTIONS DEFINED IN THIS CLASS FILE

 1>String getTable(int page_no,List l1,List primary,List headlist,Map mymap,Map searchmap,String[] combolist)
 2>int getNoOfPages(int total_elements)
 3>String paginationTable(int no_of_pages,String var1,String var2,int block,int totRec,String[] combolist)
 4>String getSortColumnlist(List headlist,Map mymap,String[] combolist)
 5>String getSortColumnlist(List headlist,Map mymap)
 6>String getDataRows(int page_no,List l1,List primary)
 7>String toolbar(String previouspage,String dataPage_width)
 8>String tableHeading(String table_heading,String paginationtable)
 9>String getFooter(Map searchmap,List headlist)
 10>void setColor(String topColor, String hdrColor, String rowColor,String font)
 11>void setVariables(int noFields,int noPrimary,int recordPage,String pageName,int pageBlock,String sFld,String sVal)
 12>String getViewPage(List AL_List,List viewHdr,Map viewConTrol,int no_of_column,String mstName)
 13>String getHtmlStr()
 14>String getViewRows(List AL_List,List viewHdr,Map viewControl)
 15>String getViewHeader(String mstName)
 16>String getViewToolBar()
 17>String getControl(String fieldVal,String fieldName,Map viewControl)
 18>void getPrintHtml(String rptName,List rptHeader, List rptWidth,
 String query,List cmbHeader,List combo,List colIndex,int totColumn)throws Exception
 19>String getPrintHeader(String rptName,List rptHeader,List rptWidth,List cmbHeader,List combo)
 20>String getPrintData(List AL_List,List rptWidth,int noFields,List colIndex,int totColumn)
 21>String getPrintFooter(int colspan)

 */

package usermgmt.masters;

import java.util.*;
import java.sql.*;
import java.text.*;

import HisGlobal.*;

public class MenuPage extends usermgmt.FuncLib
{
	private String htmlStr = "";
	private String currentStatus = "";
	private int total_elements = 0;
	private int no_of_pages = 0;
	private int start_element = 0;
	private int start_primary = 0;
	private int end_element = 0;
	private String topcolor = "";
	private String hdrcolor = "";
	private String rowcolor = "";
	private String fontUsed = "";
	private int record_per_page = 0;
	private int no_of_fields = 0;
	private int no_of_primary_keys = 0;
	private String pagename = "";
	private int page_per_block = 0;
	private String searchFld = "";
	private String searchVal = "";
	private String isValid = "";
	private String[] combolist;
	private int no_of_combo = 0;
	private String hsptName = "";

	public String getTable(int page_no, List l1, List primary, List headlist, Map mymap, Map searchmap)
	{
		String html = "";
		total_elements = l1.size();
		no_of_pages = getNoOfPages(l1.size());
		start_element = record_per_page * no_of_fields * (page_no - 1);
		start_primary = record_per_page * no_of_primary_keys * (page_no - 1);
		end_element = start_element + (record_per_page * no_of_fields) - 1;
		html = "<tr bgcolor='" + hdrcolor + "'>\n";
//		html = "<tr class='ShadedTitleTagImage'>\n";
		
		// this function creates header
		// html += getSortColumnlist(headlist,mymap,combolist);
		html += getSortColumnlist(headlist, mymap);
		html += "</tr>\n";

		// this function creates the data rows
		html += getDataRows(page_no, l1, primary);

		// this block generates the footer
		html += "<tr bgcolor='" + topcolor + "'>\n";
		html += "<td height='19' colspan='" + (no_of_fields + 1) + "' class='ShadedSubTitleTagImage'>\n";
		html += getFooter(searchmap, headlist);
		html += "</td>\n";
		html += "</tr>\n";

		return html;

	}// 1

	public int getNoOfPages(int total_elements)
	{
		int temp = total_elements / no_of_fields;
		int no_of_pages = 0;
		int temp1 = temp % record_per_page;

		if (temp1 == 0) no_of_pages = temp / record_per_page;
		else no_of_pages = (temp / record_per_page) + 1;

		return no_of_pages;
	}// 2

	public String paginationTable(int no_of_pages, String var1, String var2, int block, int totRec, int page_no)
	{
		int i = 0;
		int j = 0;
		String html = "";
		String combostr = "";

		for (i = 0; i < no_of_combo; i++)
		{
			if (!combolist[i].equals("")) combostr += "combo" + (i + 1) + "=" + combolist[i] + "&";
		}

		html += "<td  colspan = '" + (no_of_fields + 1) + "'>\n";
		html += "<table name=pagination width = '100%' border=0 cellPadding=0 cellSpacing=0>\n";
		html += "<tr bgcolor = '" + topcolor + "'>\n";
//		html += "<tr class ='ShadedSubTitleTagImage' >\n";
		html += "<td height='16' align = 'left' class='ShadedSubTitleTagImage'>\n<font size = '-1' face = '";
		html += fontUsed + "'>Total - <strong>" + totRec + "</strong>record(s)</font>\n</td>\n";

		if (no_of_pages > 0)
		{
			html += "<td height='16' align = 'right' class='ShadedSubTitleTagImage'>\n<strong><font size = '-2' face='";
			html += fontUsed + "' color = '#005B88'>\n<";

			j = (block == 0 ? 1 : (block - 1) * page_per_block + 1);

			for (i = j; i <= (page_per_block + j - 1); i++)
			{
				if (i > no_of_pages) break;
				if (i == j && block > 1)
				{
					html += "<a tabindex='1' style='cursor:hand' onClick=\"pagination(event,'" + pagename + "','" + (i - page_per_block) + "','"
							+ (block - 1) + "');\">prev</a>\n";

				}
				// By Preeti

				if (i == page_no) 
					html += "<font color='#653232'>" + i + "</font>\n";
				else 
					html += "<a tabindex='1' style='cursor:hand' onClick=\"pagination(event,'" + pagename + "','" + i + "','"
					+ (block == 0 ? 1 : block) + "');\">" + i + "</a>\n";
			}

			if (no_of_pages >= i)
			{
				// By Preeti
				html += "<a tabindex='1' style='cursor:hand' onClick=\"pagination(event,'" + pagename + "','" + i + "','" + (block == 0 ? 2 : block + 1)
						+ "');\">next</a>\n";
			}

			html += "></font></strong>\n</td>\n";
		}

		html += "</tr>\n";
		html += "</table>\n";
		html += "</td>\n";

		return html;
	}// 3

	public String getComboList(String[] combohdr, String[] combohtml)
	{
		String html = "";

		if (no_of_combo > 0)
		{
			html = "<tr >\n";
			html += "<td class='ShadedSubTitleTagImage' colspan='" + (no_of_fields + 1) + "' align = 'right' >\n";
			html += "<font size='-1' face='" + fontUsed + "'>\n";
			for (int i = 0; i < no_of_combo; i++)
			{
				html += combohdr[i] + "\n";
				html += combohtml[i] + "\n";
			}
			html += "</font></td>\n";
			html += "</tr>";
		}
		return html;

	}// 4

	public String getSortColumnlist(List headlist, Map mymap)
	{
		int i = 0;
		String html = "";
		String combostr = "";

		for (i = 0; i < no_of_combo; i++)
		{
			combostr += "combo" + (i + 1) + "=" + combolist[i] + "&";
		}

		for (i = 0; i < headlist.size(); i++)
		{
			if (i == 0)
			{
				html += "<td align = 'center'  >\n<strong>";
				html += "<input type='checkbox' name='chkSelect' value='1' tabindex='1' onClick = 'selectAll(this);'></strong>\n</td>\n";
			}

			//
			html += "<td  align = 'left'>\n<strong><font size='2' color='#653232' face='" + fontUsed + "'>";
			String strtemp1 = String.valueOf(headlist.get(i));
			html += strtemp1 + "\n";
			String strtemp2 = "";
			//
			if (mymap.containsKey(strtemp1))
			{
				strtemp2 = String.valueOf(mymap.get(strtemp1));

				// *******SORT ARROWS*****************

				// Commented by Preeti on 6/10/05

				/*
				 * html += "<a href='" + pagename + "?" + combostr + "page_no=1&var1="+strtemp2; //html += "<a href='" +
				 * pagename + "?" + "page_no=1&var1="+strtemp2; html += "&var3=0&cboSearch=" + searchFld + "&txtSearch=" +
				 * searchVal + "'>"; html += "<img src='../../images/arrow_up.gif' width='10' tabindex='1' height='10'
				 * border=0 alt='ascending order'>"; html += "</a>\n";
				 * 
				 * html += "<a href='" + pagename + "?" + combostr + "page_no=1&var1=" + strtemp2; //html += "<a href='" +
				 * pagename + "?" + "page_no=1&var1=" + strtemp2; html += "&var2=desc&var3=0&cboSearch=" + searchFld +
				 * "&txtSearch=" + searchVal + "'>"; html += "<img src='../../images/arrow_down.gif' tabindex='1' width='10'
				 * height='10' border=0 alt='descending order'>"; html += "</a>\n";
				 */

				// new addition
				html += "<img src='../../images/arrow_up.gif' width='10' tabindex='1' height='10' border=0 alt='ascending order'" + " onClick=sort('"
						+ pagename + "','" + strtemp2 + "','asc');>";
				html += "</a>\n";

				html += "<img src='../../images/arrow_down.gif' tabindex='1' width='10' height='10' border=0 alt='descending order'"
						+ " onClick=sort('" + pagename + "','" + strtemp2 + "','desc');>";
				html += "</a>\n";

			}
			html += "</font></strong>\n</td>\n";

		}

		return html;

	}// 5

	public String getDataRows(int page_no, List l1, List primary)
	{
		int i, j, k = 0;
		int l = start_primary;
		int temp = start_element;
		String waste = "";
		String html = "";

		if (no_of_pages == 0)
			record_per_page = 0;

		if(record_per_page==0)
		{
			html += "<tr bgcolor='" + rowcolor + "'>\n";
			html += "<td align = 'center' colspan="+no_of_fields+1+"> <font size=2 color='RED' face = '" + fontUsed + "'>\n";
			html += "No Record Exists" + "</font>\n</td>\n";
			html += "</tr>\n";
		}
		for (i = 0; i < record_per_page; i++)
		{
			html += "<tr bgcolor='" + rowcolor + "'>\n";
			for (k = 0; k < no_of_primary_keys; k++)
			{
				waste = waste + primary.get(l++) + "^";
			}
			
	  		
	  		//menu_id = arraymenu[0].substring(0,arraymenu[0].length());
            if(no_of_primary_keys>1)
            {
            	String[] arraymenu=waste.replace("^", "#").split("#");
            	String menuLevel = arraymenu[1];
            	if(menuLevel.equals("1"))
			    html += "<td align = 'center'>\n<input type=checkbox name=chk value=" + waste + " disabled='true' tabindex='1' onClick=checkCounter(this);>\n</td>\n";
            	else
                	html += "<td align = 'center'>\n<input type=checkbox name=chk value=" + waste + " tabindex='1' onClick=checkCounter(this);>\n</td>\n";
            }
            else
            	html += "<td align = 'center'>\n<input type=checkbox name=chk value=" + waste + " tabindex='1' onClick=checkCounter(this);>\n</td>\n";
			waste = "";

			for (j = 0; j < no_of_fields; j++)
			{
				html += "<td align = 'left' nowrap> <font size=2 color='#990000' face = '" + fontUsed + "'>\n";
				html += l1.get(temp++) + "</font>\n</td>\n";
			}

			html += "</tr>\n";
			if (temp >= total_elements) break;
		}

		return html;
	}// 6

	public String toolbar(String previouspage, String dataPage_width)
	{
		String page_html = "";

//		page_html += "<td colspan='" + (no_of_fields + 1) + "'>\n";
//		page_html += "<table width='" + dataPage_width + "' cellspacing='0' cellpadding='0' align = 'center'>\n";
//		page_html += "<tr>\n";
//		page_html += "<td height='23' align = 'center'>\n";
//
//		page_html += "<a style=cursor:hand><img src='../../images/Add.png'  tabindex='1' onKeyPress='add(event);' onClick='add(event);'></a>\n";
//		page_html += "<a style=cursor:hand><img src='../../images/Modify_.png'   tabindex='1' onKeyPress='edit(event);' onClick='edit(event);' ></a>\n";
//		page_html += "<a style=cursor:hand><img src='../../images/Delete.png'    tabindex='1' onKeyPress='del(event);' onClick='del(event);'></a>\n";
//		page_html += "<a style=cursor:hand><img src='../../images/View.png'   tabindex='1' onKeyPress='view(event);' onClick='view(event);'></a>\n";
//		page_html += "<a style=cursor:hand><img src='../../images/Report.png'   tabindex='1' onKeyPress='report(event);' onClick='report(event);'></a>\n";

		page_html += "<td colspan='" + (no_of_fields + 1) + "'>\n";
		page_html += "<table width='" + dataPage_width + "' cellspacing='0' cellpadding='0' align = 'center'>\n";
		page_html += "<tr class='FOOTER'>\n";
		page_html += "<td height='23' align = 'center'>\n";
		page_html += "<div class='control_button2' align='center'>\n";
		page_html += "<a style=cursor:hand class='button' tabindex='1' onKeyPress='add(event);' onClick='add(event);'><span class='add'> Add</span></a>\n";
		page_html += "<a style=cursor:hand class='button' tabindex='1' onKeyPress='edit(event);' onClick='edit(event);' ><span class='modify'> Modify</span></a>\n";
		page_html += "<a style=cursor:hand class='button' tabindex='1' onKeyPress='del(event);' onClick='del(event);'><span class='delete'> Delete</span></a>\n";
		page_html += "<a style=cursor:hand class='button' tabindex='1' onKeyPress='view(event);' onClick='view(event);'><span class='view'> View</span></a>\n";
		page_html += "<a style=cursor:hand class='button' tabindex='1' onKeyPress='report(event);' onClick='report(event);'><span class='report'> Report</span></a>\n";

		page_html += "</div>\n";
		page_html += "</td>\n";
		page_html += "</tr>\n";
		page_html += "</table>\n";
		page_html += "</td>\n";

		return page_html;
	}// 7
	
	
	public String HospMsttoolbar(String previouspage, String dataPage_width)
	{
		String page_html = "";

		page_html += "<td colspan='" + (no_of_fields + 1) + "'>\n";
		page_html += "<table width='" + dataPage_width + "' cellspacing='0' cellpadding='0' align = 'center'>\n";
		page_html += "<tr>\n";
		page_html += "<td height='23' align = 'center'>\n";

		page_html += "<a style=cursor:hand><img src='../../images/Add.png'  tabindex='1' onKeyPress='add(event);' onClick='add(event);'></a>\n";
		page_html += "<a style=cursor:hand><img src='../../images/Modify_.png'   tabindex='1' onKeyPress='edit(event);' onClick='edit(event);' ></a>\n";
		page_html += "<a style=cursor:hand><img src='../../images/View.png'   tabindex='1' onKeyPress='view(event);' onClick='view(event);'></a>\n";
		page_html += "<a style=cursor:hand><img src='../../images/Report.png'   tabindex='1' onKeyPress='report(event);' onClick='report(event);'></a>\n";

		page_html += "</td>\n";
		page_html += "</tr>\n";
		page_html += "</table>\n";
		page_html += "</td>\n";

		return page_html;
	}// 7

	public String tableHeading(String table_heading, String paginationtable, String filter)
	{
		String page_html = "";

		page_html += "<tr>\n";
		page_html += "<td  colspan='" + (no_of_fields + 1) + "'>\n";
		page_html += "<font color='#653232' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>\n<strong>";
		page_html += table_heading + " >>";
		page_html += "</strong></font>\n";
		page_html += "</td>\n";
		page_html += "</tr>\n";

		page_html += "<tr>\n";
		page_html += paginationtable;
		page_html += "</tr>\n";
		page_html += filter + "\n";
		return page_html;
	}// 8

	public String getFooter(Map searchmap, List headlist)
	{
		int i = 0;
		String html = "";

		html = "<table  width='100%'  cellspacing='0' cellpadding='0'>\n";
		html += "<tr>\n";
		html += "<td align='right' class='ShadedSubTitleImage' style='vertical-align: middle' >\n";

		if (!searchmap.isEmpty())
		{
			html += "<select name='cboSearch' tabindex='1' valign = 'top' class='selectCob'>\n";
			html += "<option value = '-1' selected>Select field</option>\n";

			for (i = 0; i < headlist.size(); i++)
			{
				String strtemp1 = String.valueOf(headlist.get(i));
				String strtemp2 = "";

				if (searchmap.containsKey(strtemp1))
				{
					strtemp2 = String.valueOf(searchmap.get(strtemp1));
					if (strtemp2.equalsIgnoreCase(searchFld)) html += "<option value = '" + strtemp2 + "' selected>" + strtemp1 + "</option>\n";
					else html += "<option value = '" + strtemp2 + "'>" + strtemp1 + "</option>\n";
				}
			}

			html += "</select>\n";
			html += "<input type='text' name='txtSearch' align = 'top'  class='textbox' value = '" + searchVal;
			html += "' tabindex='1' onKeyPress=\" pagination(event,'" + pagename + "','1','1');\">\n"; // change by Pradeep 24-04-2006
																							// :: for tracking Enter key

			html += "<a style=cursor:hand><img src='../../images/Search.png' tabindex='1' ";
			html += "   border=0  align = 'top' tabindex='1' onClick=pagination(event,'" + pagename + "',1,1);  onKeyPress=pagination(event,'" + pagename + "',1,1);>";
			html += "</a>\n";

			html += "</td>\n";
			html += "</tr>\n";
			html += "</table>\n";
		}

		return html;

	}// 9

	public void setColor(String topColor, String hdrColor, String rowColor, String font)
	{
		topcolor = topColor;
		hdrcolor = hdrColor;
		rowcolor = rowColor;
		fontUsed = font;
	}// 10

	public void setVariables(int noFields, int noPrimary, int recordPage, String pageName, int pageBlock, String sFld, String sVal, int noCombo,
			String[] strCombo)
	{
		no_of_fields = noFields;
		no_of_primary_keys = noPrimary;
		record_per_page = recordPage;
		pagename = pageName;
		page_per_block = pageBlock;
		searchFld = sFld;
		searchVal = sVal;
		// combo
		no_of_combo = noCombo;
		combolist = strCombo;
	}// 11

	public void setVariables(int noFields, int noPrimary, int recordPage, String pageName, int pageBlock, String sFld, String sVal, int noCombo,
			String[] strCombo, String validFld)
	{
		no_of_fields = noFields;
		no_of_primary_keys = noPrimary;
		record_per_page = recordPage;
		pagename = pageName;
		page_per_block = pageBlock;
		searchFld = sFld;
		searchVal = sVal;
		// combo
		no_of_combo = noCombo;
		combolist = strCombo;
		isValid = validFld;
	}// 11

	/*
	 * this function generates the view page
	 */
	public void getViewPage(List AL_List, List viewHdr, Map viewConTrol, String mstName)
	{

		htmlStr = getViewHeader(mstName);
		htmlStr += getViewRows(AL_List, viewHdr, viewConTrol);
		htmlStr += getViewToolBar();
	}// 12

	public String getHtmlStr()
	{
		return htmlStr;
	}// 13

	public String getViewRows(List AL_List, List viewHdr, Map viewControl)
	{
		String html = "";
		String strtemp1 = "";
		String strtemp2 = "";
		int index, i, fields, flag;

		fields = viewHdr.size();

		for (i = 0; i < fields; i++)
		{
			flag = 0;
			html += "<tr>\n";

			strtemp1 = String.valueOf(viewHdr.get(i));
			index = strtemp1.indexOf('^');

			while (index != -1)
			{
				if (flag == 0)
				{
					html += "<td height='20' colspan = '2' bgcolor='#FFEBD5' align = 'center'>\n";
					html += "<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>\n";
					flag = 1;
				}

				strtemp1 = strtemp1.substring(0, index);
				html += strtemp1;
				// this function gives the control with value
				html += getControl(String.valueOf(AL_List.get(i)), strtemp1, viewControl, i);

				if (++i < fields)
				{
					strtemp1 = String.valueOf(viewHdr.get(i));
					index = strtemp1.indexOf('^');
				}
				else break;
			}

			if (flag == 1)
			{
				html += "</td>\n";
				html += "</tr>\n";
			}

			if (i < fields)
			{
				html += "<tr>";
				html += "<td height='20' bgcolor='#FFEBD5' align = 'right'>\n";
				html += "<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>\n";
				html += strtemp1 + "\n";
				html += "</font>\n";
				html += "</td>\n";

				html += "<td bgcolor='#F1ECE2' align = 'left'>\n";
				html += getControl(String.valueOf(AL_List.get(i)), strtemp1, viewControl, i);
				html += "</td>\n";
				html += "</tr>\n";
			}
		}

		html += "<tr bgcolor='#FFB468'>\n";
		html += "<td height='20' colspan='2' class='addHeaderNew'></td>\n";
		html += "</tr>\n";
		html += "</table>\n";

		return html;

	}// 14

	public String getViewHeader(String mstName)
	{

		String html = "";

		html = "<table width='100%' border='0' cellspacing='1' cellpadding='0' align = 'center'>\n";
		html += "<tr>\n";
		html += "<td colspan='2' class='addHeaderNew'>\n";
		html += "<font color='#653232' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>\n";
		html += "<strong>" + mstName + " >>View</strong\n>";
		html += "</font>\n";
		html += "<td>\n";
		html += "<tr>\n";
		html += "<tr bgcolor='#FFB468'>\n";
		html += "<td  colspan='2' height='20' class='addHeaderNew'></td>\n";
		html += "</tr>\n";

		return html;
	}// 15

	public String getViewToolBar()
	{

		String html = "";

		html = "<table width='60%' border='0' cellspacing='0' cellpadding='0' align = 'center'>\n";
		html += "<tr>\n";
		html += "<td colspan = '2' height='23' align='center'>\n";
		html += "<a style=cursor:hand>\n";
		html += "<img src='../../images/btn-ccl.png' tabindex='1'  onKeyPress='submitpage2(event,form1,\"DEFAULT\");' onClick='submitpage2(event,form1,\"DEFAULT\");'>\n";
		html += "</a>\n";
		html += "</td>\n";
		html += "</tr>\n";
		html += "</table>\n";

		return html;
	}// 16

	public String getControl(String fieldVal, String fieldName, Map viewControl, int conIndex)
	{
		String strtemp = "";
		String html = "";
		char ch = 'd'; // default

		if (viewControl.containsKey(fieldName))
		{
			strtemp = String.valueOf(viewControl.get(fieldName));
			ch = strtemp.charAt(0);
		}

		switch (ch)
		{
			case 'T': // TextArea
				html = "<textarea name='" + fieldName + "' rows='2' class='textarea' readonly>\n";
				html += fieldVal + "\n";
				html += "</textarea>\n";
				break;

			case 'C': // CheckBox
				if (fieldVal.equals("1")) html = "<input name='" + fieldName + "' type='checkbox' checked disabled tabindex='1'>\n";
				else html = "<input name='" + fieldName + "' type='checkbox' disabled tabindex='1'>\n";
				break;

			case 'O': // Option
				if (fieldVal.equals("1")) html = "<input name='radioButton" + String.valueOf(conIndex) + "' type='radio' checked disabled>\n";
				else html = "<input name='radioButton " + String.valueOf(conIndex) + "' type='radio' disabled>\n";
				break;

			default: // TextBox
				html = "<input name='" + fieldName + "' type='text' value='" + fieldVal + "' readonly>\n";
				break;
		}

		return html;

	}// 17

	/*
	 * For master page's printout. it does not return html string. call getHtmlStr() for Report's page
	 */
	public void getPrintHtml(String rptName, List rptHeader, List rptWidth, String query, List cmbHeader, List combo, List colIndex, int totColumn,
			String currentStatusValue) throws Exception
	{

		List AL_List = new ArrayList();
		int noFields = rptHeader.size();

		AL_List = getDetails(query, totColumn);
		if (AL_List.size() <= 0) htmlStr = "NO DATA FOUND";
		else
		{
			// toolbar
			htmlStr = "<table width='100%' border='0' cellspacing='1' cellpadding='0'>";
			htmlStr += "<tr><td><div  id = 'id1' align='right'>";
			htmlStr += "<a style=cursor:hand><img src='../../images/btn-ccl.png' tabindex='1' onKeyPress='submitpage1(event,\"DEFAULT\");' border=0 onClick='submitpage1(event,\"DEFAULT\");'></a>\n";
			htmlStr += "<a style=cursor:hand><img src='../../images/btn-pnt.png' border=0 tabindex='1' onKeyPress='callMe1(event)' onClick='callMe1(event);'></a>\n";
			htmlStr += "</div></td></tr></table>\n";

			// report view
			htmlStr += "<table width='100%' border='0' cellspacing='1' cellpadding='0'>\n";
			htmlStr += getPrintHeader(rptName, rptHeader, rptWidth, cmbHeader, combo, currentStatusValue);

			htmlStr += getPrintData(AL_List, rptWidth, noFields, colIndex, totColumn);
			htmlStr += getPrintFooter(noFields);
			htmlStr += "</table>";
		}
	}// 18

	// Header that will reflect on every page while printing
	private String getPrintHeader(String rptName, List rptHeader, List rptWidth, List cmbHeader, List combo, String currentStatusValue)
	{
		int i = 0;
		String html = "";
		int colspan;
		String strFormat = "MMM dd,yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		java.util.Date dt = new java.util.Date();
		if (currentStatusValue.equals("1")) currentStatus = "Active";
		else if (currentStatusValue.equals("2")) currentStatus = "InActive";

		colspan = rptHeader.size() + 1;
		html = "<thead style='DISPLAY:TABLE-HEADER-GROUP'>\n";
		html += "<tr>\n";
		html += "<td align = 'center' colspan = '" + colspan + "'><strong>\n";
		html += "<font size='3' face='Verdana, Arial, Helvetica, sans-serif'>\n";
		html += hsptName + "\n";
		html += "</font></strong>\n";
		html += "</td>\n";
		html += "</tr>\n";
		html += "<tr>\n";
		html += "<td align = 'center' colspan = '" + colspan + "'><strong>\n";
		html += "<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>\n";
		html += rptName + " Report , Status	: <u>" + currentStatus + "</u>\n";
		html += "</font></strong>\n";
		html += "</td>\n";
		html += "</tr>\n";
		html += "<tr>\n";
		html += "<td align = 'right' colspan = '" + colspan + "'>\n";
		html += "<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>\n";
		html += "<strong>Date of Report : </strong>\n";
		html += sdf.format(dt);
		html += "</font></td>\n";
		html += "</tr>";

		for (i = 0; i < cmbHeader.size(); i++)
		{
			html += "<tr>\n";
			html += "<td height='19' colspan = '" + colspan + "'>\n";
			html += "<Strong>" + cmbHeader.get(i) + " :</Strong>\n";
			html += combo.get(i) + "\n";
			html += "</td>";
			html += "</tr>\n";
		}

		html += "<tr>\n";
		html += "<td colspan = '" + colspan + "'><hr></td>\n";
		html += "<tr>\n";

		for (i = 0; i < colspan; i++)
		{
			if (i == 0)
			{
				html += "<td width = '10%' align = 'left'>\n";
				html += "<font size='3'>\n";
				html += "<strong>S.No</strong>\n";
				html += "</font></td>\n";
			}
			else
			{
				html += "<td width='" + rptWidth.get(i - 1) + "%' align = 'left'>\n";
				html += "<font size='3'>\n";
				html += "<strong>" + rptHeader.get(i - 1) + "</strong>\n";
				html += "</font>\n";
				html += "</td>";
			}
		}

		html += "</tr>\n";
		html += "<tr>\n";
		html += "<TD COLSPAN = '" + colspan + "'><HR></TD>\n";
		html += "</tr>\n";
		html += "</thead>\n";

		return html;
	}// 19

	// Data rows
	private String getPrintData(List AL_List, List rptWidth, int noFields, List colIndex, int totColumn)
	{
		String html = "";
		String tempVal = "";
		int i = 0, index = 0;
		int j = 0;
		int noRows = AL_List.size() / totColumn;

		html = "<tbody>\n";

		for (i = 0; i < noRows; i++)
		{
			html += "<tr>\n";
			html += "<td align = 'left' width = '5%'>" + (i + 1) + ".</td>\n";

			for (j = 0; j < noFields; j++)
			{
				index = Integer.parseInt((String) colIndex.get(j)) - 1;
				html += "<td align = 'left' width = '" + rptWidth.get(j) + "'>";
				tempVal = (String) AL_List.get(i * totColumn + index);
				html += tempVal.trim() + "</td>\n";
			}
			html += "</tr>\n";
		}

		html += "</tbody>";
		return html;
	}// 20

	// Footer that will reflect on every page while printing
	private String getPrintFooter(int colspan)
	{
		String html = "";

		html = "<tfoot style='DISPLAY:TABLE-FOOTER-GROUP'>\n";
		html += "<tr>\n";
		html += "<TD COLSPAN = '" + (colspan + 1) + "'><HR></TD>\n";
		html += "</tr>\n";
		html += "</tfoot>\n";

		return html;
	}// 21
	/* For Status Combo in list Page */

	public String getIsValidCombo(String selVal)
	{
		String[] text =
		{ "Active", "InActive" };
		String[] value =
		{ "1", "2" };
		int i;
		String combo = "";

		for (i = 0; i < text.length; i++)
		{
			if (selVal.equals(value[i])) combo += "<option value='" + value[i] + "' selected>" + text[i] + "</option>";
			else combo += "<option value='" + value[i] + "'>" + text[i] + "</option>";
		}

		return combo;
	}

}//end of class
