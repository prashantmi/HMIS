<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page language="java" import="hisglobal.masterxml.masterworkshop.tools.*,java.util.*"%>

<%
	MasterTO objMasterTo = (MasterTO) session.getAttribute("TOLstObject");
	List alData = (List) ((MasterListTO) objMasterTo).getRows();
	ArrayList alControls = ((MasterListTO) objMasterTo).getObjMstLstDtl().getAlControls();
	for (int i = 0; i < alControls.size(); i++)
	{
		ComboDtl combo = (ComboDtl) alControls.get(i);
		combo.getSelectionMandatory();
	}
%>

<html>
	<head>
		<title><%=((MasterListTO) objMasterTo).getMasterName()%></title>
		<his:javascript src="/hisglobal/js/masters.js" />
		<his:javascript src="/hisglobal/js/time.js" />
		<his:css src="/hisglobal/css/masterXml.css" />
		<his:css src="/hisglobal/css/Color.css" />
		<script>
		window.history.forward()
function setSortOn()
{
	document.getElementsByName('sortOn')[0].value="1";
	return true;
}

function add(e)
{
	<%
		for(int i=0;i<alControls.size();i++)
		{
			ComboDtl combo=(ComboDtl)alControls.get(i);
			String mandatory= combo.getSelectionMandatory();
			//System.out.println("mandatory:::"+mandatory);
			if(mandatory!=null&& mandatory.equalsIgnoreCase("true"))
			{
	%>
	
	elem=document.getElementsByName("controls[<%=i%>]")[0];
	if(elem.value==-1)
	{
		alert(""+"<%=combo.getLabel()%>"+"::::Please select valid value");
		elem.focus();
		return false;
	}
	
	<%
			}
		}
	%>
	submitpage("ADD");
}

function edit(e)
{
 if(e.type=="click"||e.keyCode==13)
	{	
		len = document.forms[0].hcounter.value;
		if(len > 0)
		{
		if(len>1)
	    	getmsgStr(3);
	   	else{
		<%
		for(int i=0;i<alControls.size();i++)
		{
			ComboDtl combo=(ComboDtl)alControls.get(i);
			String mandatory= combo.getSelectionMandatory();
			//System.out.println("mandatory:::"+mandatory);
			if(mandatory!=null&& mandatory.equalsIgnoreCase("true"))
			{
	%>
	
	elem=document.getElementsByName("controls[<%=i%>]")[0];
	if(elem.value==-1)
	{
		alert(""+"<%=combo.getLabel()%>"+"::::Please select valid value");
		elem.focus();
		return false;
	}
	
	<%
			}
		}
	%>
	submitpage("MODIFY");
			}
	}
	
		else
		{
		getmsgStr(1);

	}	
     
}  

}

</script>
	</head>
<body>
<%
	int noOfPages;
	//System.out.println("inside list page");
%>

<html:form action="/masterWorkshop/mstHandlerListAction">
	<div align="center">
		<table cellpadding="0" cellspacing="1" width="80%">
			<tr>
				<td class="MasterXMLHeader">
				<%
					String masterTitle = ((MasterListTO) objMasterTo).getTitle();
					int ind = masterTitle.lastIndexOf("M");
					if (ind == -1)
					{
						ind = masterTitle.lastIndexOf("m");
					}
					String name = masterTitle.substring(0, ind);
				%>
					<b><%=name%> Master&gt;&gt;</b>
				</td>
			</tr>
			<tr>
				<td class="ShadedTitleTagImage">
					<table width="100%" cellpadding="0"	cellspacing="0">
						<tr>
							<td align="left">
								<span class="TitleTagFontStyle">				
								<%
									//calculation for no. of pages should be based on the list type based on mode 	   
									// first check the mode  weather seacrch or normal:
									int mode = ((MasterListTO) objMasterTo).getSearchmode();
									if (mode == 0)
									{
										int alSize = ((MasterListTO) objMasterTo).getAlData().size();
										//System.out.println("alSize" + alSize);
										int noOfCol = ((MasterListTO) objMasterTo).getAlColHeading().size() + 1;
										int rows = alSize / noOfCol;
										//System.out.println("rows::" + rows);
										ArrayList pageList = new ArrayList();//list of no. of pages		
										if (rows == 0)
										{
											hisglobal.masterxml.masterworkshop.Entry objEntry = new hisglobal.masterxml.masterworkshop.Entry("0", "0");
											pageList.add(objEntry);
										}
										noOfPages = rows / 10;
										int mod = rows % 10;
										//System.out.println("noOfPages" + noOfPages);
										//System.out.println("");
										if (mod != 0)	noOfPages++;
										for (int h = 0; h < noOfPages; h++)
										{
											hisglobal.masterxml.masterworkshop.Entry objEntry = new hisglobal.masterxml.masterworkshop.Entry(
											String.valueOf(h + 1), String.valueOf(h));
											pageList.add(objEntry);
										}	
										request.setAttribute("pageoptions", pageList);
								%>
									Total - <b><%=rows%></b>&nbsp;&nbsp; Records
								<%
									}
									else //search view use search list to display records
									{
										//System.out.println("search mode:::" + mode);		
										List alDataSearchlst = (List) ((MasterListTO) objMasterTo).getListSearch();
										
										//calculate no. of pages to be used for searching
										noOfPages = alDataSearchlst.size() / 10;
										ArrayList pageList = new ArrayList();//list of no. of pages	
										if (noOfPages == 0)
										{
											hisglobal.masterxml.masterworkshop.Entry objEntry = new hisglobal.masterxml.masterworkshop.Entry("0", "0");
											pageList.add(objEntry);
										}		
										//System.out.println("alDataSearchlst size"+ alDataSearchlst.size());
										int mod = alDataSearchlst.size() % 10;
										//System.out.println("mod" + mod);
										//System.out.println("noOfPages" + noOfPages);		
										if (mod != 0)	noOfPages++;		
										for (int h = 0; h < noOfPages; h++)
										{
											hisglobal.masterxml.masterworkshop.Entry objEntry = new hisglobal.masterxml.masterworkshop.Entry(
											String.valueOf(h + 1), String.valueOf(h));
											pageList.add(objEntry);
										}
										request.setAttribute("pageoptions", pageList);
								%>
									Total - <b><%=((MasterListTO) objMasterTo).getListSearch().size()%>Records</b>
								<%
									}
								%>
								</span>
							</td>
							<td align="right">
								<span class="TitleTagFontStyle">
									<b>Page</b>
									<html:select property='pageno' onchange="displayPage(this)" tabindex="1">
										<html:optionsCollection name="pageoptions" label="label" value="value" />
									</html:select>
									<b>of <%=noOfPages%></b>
								</span>
							</td>
						</tr>
					</table>
				</td>				
			</tr>
			<tr>
				<td class="ShadedSubTitleTagImage">
					<div align="right" class="TitleTagFontStyle">
						Is Active 
						<select name="isActive" onChange="submitpage('LIST')" tabindex="1">
							<option value="1" <% if(((MasterListTO)objMasterTo).getIsActive().equals("1")){%> selected<%}%>>Active</option>
							<option value="2" <% if(((MasterListTO)objMasterTo).getIsActive().equals("2")){%> selected <%}%>>InActive</option>
						</select>
					</div>
				</td>
			</tr>
			<%
				if (((MasterListTO) objMasterTo).getControls().size() > 0)
				{
			%>
			<logic:iterate name="TOLstObject" property="controls" id="control" type="hisglobal.masterxml.masterworkshop.tools.Combo" indexId="idx">
			<tr>
				<td class="ShadedSubTitleTagImage">
					<div align="right" class="TitleTagFontStyle">
							<logic:notEmpty name="mstHandlerListForm" property='controls'>
								<bean:write name="control" property="label" />
								<html:select name="mstHandlerListForm" property='<%="controls["+idx+"]"%>' onchange="(setSortOn() && submitpage('LIST')); " tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:optionsCollection name="control" property="options" label="label" value="value" />
								</html:select>
							</logic:notEmpty>
							<logic:empty name="mstHandlerListForm" property='controls'>
								<bean:write name="control" property="label" />
								<html:select name="mstHandlerListForm" property='controls' onchange="setSortOn() && submitpage('LIST'));" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<html:optionsCollection name="control" property="options" label="label" value="value" />
								</html:select>
							</logic:empty>
					</div>
				</td>
			</tr>
			</logic:iterate>
			<%
			}
			%>
		</table>
		<table width=80%>
			<tr>
				<td class="MasterXMLDataLabel">
					<input name="chk" type="checkbox" onClick="selectAll(this)" tabindex="1">
				</td>
				<%
					int colno = 0;		
					for (int i = 0; i < ((MasterListTO) objMasterTo).getAlColHeading().size(); i++)
					{
						colno++;
				%>
				<td class="MasterXMLDataLabel">
					<%=(String) ((MasterListTO) objMasterTo).getAlColHeading().get(i)%>
					<a style="cursor: hand">
						<img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer" width='10' height='10' border=0 alt='Ascending order' onkeypress="if(event.keyCode==13) sortOnColasc('<%=colno%>'); " onClick="sortOnColasc('<%=colno%>')" tabindex="1" >
					</a>
					<a style="cursor: hand">
						<img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer" width='10' height='10' border=0 alt='Desccending order' onkeypress="if(event.keyCode==13) sortOnColdsc('<%=colno%>'); " onClick="sortOnColdsc('<%=colno%>')" tabindex="1">
					</a>
				</td>
				<%
					}
				%>
			</tr>
			<%
				//System.out.println("till here");
				mode = ((MasterListTO) objMasterTo).getSearchmode();
				if (mode == 0)
					alData = (List) ((MasterListTO) objMasterTo).getRows();
				else
					alData = (List) ((MasterListTO) objMasterTo).getRows();
	
				//System.out.println("aldata" + alData);
				//System.out.println("alData.size()" + alData.size());
				for (int i = 0; i < alData.size();)
				{
			%>
			<tr>
			<%
				//System.out.println("i::::" + i);
				//System.out.println("sdkhf");
				//System.out.println("");
				List alrow = (List) alData.get(i);				
				//System.out.println("alrow" + alrow);
			%>
				<td class="MasterXMLDataValue">
					<input name="chk" type="checkbox" value='<%=(String)alrow.get(0)%>' onClick=checkCounter(this) tabindex="1">
				</td>
				<%
					for (int j = 1; j < alrow.size(); j++)
					{
				%>
				<td class="MasterXMLDataValue">
					<%=(String) alrow.get(j)%>
				</td>
				<%
					}
				%>
			</tr>
			<%
					i++;
					//System.out.println("i::::zx" + i);
				}
			%>
		</table>
		<table width="80%" cellpadding="0" cellspacing="1">
			<tr>
				<td class="MasterXMLDataLabel" style="vertical-align: middle">
					<div align="right" style="vertical-align: middle">
						<html:select name="mstHandlerListForm" property='comboSearch' tabindex="1">
							<logic:iterate name="TOLstObject" property="alColHeading" id="colName" indexId="idx" type="java.lang.String">
								<html:option value="<%=String.valueOf(idx.intValue()+1)%>">
									<bean:write name="colName" />
								</html:option>
							</logic:iterate>
						</html:select>
						<html:text name="mstHandlerListForm" property="txtSearch" tabindex="1" onkeypress="if(event.keyCode!=13) return validateSpecialCharacter(event); else submitpage('SEARCH');" />
						<img src='<his:path src="/hisglobal/images/btn-search.png"/>' style="cursor: pointer;" onKeyPress="if(window.event.keyCode==13) submitpage('SEARCH');" onClick="submitpage('SEARCH');" tabindex="1">
					</div>
				</td>
			</tr>
		</table>

		<table width="80%" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<his:ButtonToolBarTag>
						<logic:equal name="mstHandlerListForm" property="isActive" value="1">
							<img src='<his:path src="/hisglobal/images/btn-add.png"/>' style="cursor: pointer;" onKeyPress="if(event.keyCode==13) add(event);" onClick='add(event);' tabindex="1">
						</logic:equal>
						<img src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer;" onKeyPress="if(event.keyCode==13) edit(event);" onClick='edit(event);' tabindex="1">
						<img src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor: pointer;" onKeyPress="if(event.keyCode==13) del(event);" onClick='del(event);' tabindex="1">
						<img src='<his:path src="/hisglobal/images/btn-view.png"/>' style="cursor: pointer;" onKeyPress="if(event.keyCode==13) view(event);" onClick='view(event);' tabindex="1">
						<img src='<his:path src="/hisglobal/images/btn-rpt.png"/>' style="cursor: pointer;" onKeyPress="if(event.keyCode==13) report(event);" onClick='report(event);' tabindex="1">
						<%
							if (objMasterTo.getHasSequence() != null && objMasterTo.getHasSequence().equalsIgnoreCase("true"))
							{
						%>
						<a href="javascript:sequence(event);">
							Sequence
						</a>
						<%
							}
							if (objMasterTo.getHasRosterSequence() != null && objMasterTo.getHasRosterSequence().equalsIgnoreCase("true"))
							{
						%>
						<a href="javascript:rosterSequence(event);">
							Sequence
						</a>
						<%
							}
						%>
					</his:ButtonToolBarTag>
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="hmode" value="LIST">
		<input type="hidden" name="hcounter">
		<input type="hidden" name="hprimary">
		<input type="hidden" name="sortOn">
		<input type="hidden" name="order">
		<input type="hidden" name="startIndex">
		
	</div>
</html:form>
<div align="center"><b><his:status /></b></div>
</body>

</html>