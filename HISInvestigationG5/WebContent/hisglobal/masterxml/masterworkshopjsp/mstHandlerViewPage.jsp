<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page language="java" import="hisglobal.masterxml.masterworkshop.tools.*,java.util.*,hisglobal.masterxml.masterworkshop.*;"%>

<%
	try
	{
%>

<html>
	<head>
		<his:css src="/hisglobal/css/masterXml.css" />
		<his:css src="/hisglobal/css/Color.css" />
		<title><bean:write name="TOLstObject" property="title" />[View]</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script Language=javascript>
function submitpage(mode)
{
	  document.forms[0].hmode.value=mode;
	  document.forms[0].submit()
}	
		</script>
	</head>
	
	<body topmargin="0" leftmargin="0">

<html:form action="/masterWorkshop/mstHandlerModifyAction" method="post">
	<his:statusInProcessWithJsp>
		<div align="center">
			<table width="60%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="MasterXMLHeader">
						<b><bean:write name="TOLstObject" property="title" />&gt;&gt;View</b>
					</td>
				</tr>
				<tr>
					<td class="ShadedSubTitleTagImage">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
						
						<bean:define id="controls" name="mstHandlerModifyForm" property="controls" type="java.lang.Object[]" />
						<%System.out.println("1");%>
						<logic:iterate name="TOModObject" property="controls" id="control" indexId="idx">
							<logic:notEqual name="control" property="showOnView" value="false">
								<tr>
									<td width="50%" class="tdfonthead">
										<div align="right">
											<%System.out.println("2");%>
											<bean:write name="control" property="label" />
										</div>
									</td>
									<%System.out.println("3");%>
									<td width="50%" class="tdfont">
									<%
										if(control.getClass()==MasterConfig.CLASS_COMBO)
										{
											System.out.println("MasterConfig.CLASS_COMBO: "+idx);
											ArrayList alText =((Combo)control).getText();
											System.out.println("altext:::::::"+alText);
											System.out.println("altext::::::: size"+alText.size());
											ArrayList alValue =((Combo)control).getValue();
											String label="";									
											System.out.println("controls"+controls[idx.intValue()]);
											for(int i=0;i<alText.size();i++)
											{
												System.out.println("altext"+(String)alText.get(i));
												String val=(String)alValue.get(i);
												System.out.println("val::"+val);
												if(controls[idx.intValue()].equals(val))
												{
													label=(String)alText.get(i);
												}
											}
									%>
									<input type="text" readonly name="control[<%=idx%>]" value="<%=label%>" tabindex="1"/>
									<br>
									<%
										String labelSuffix =((Combo)control).getLabelSuffix();
									%>
									<%=labelSuffix%>
									<%
										}
										else if(control.getClass()==MasterConfig.CLASS_TEXT)
										{
											System.out.println("MasterConfig.CLASS_TEXT: "+idx);
									%>
									<input type="text" readonly name="control[<%=idx%>]" value="<bean:write name= "mstHandlerModifyForm" property='<%="control["+idx+"]"%>'/>" tabindex="1"/>
									<%
										String labelSuffix =((TextBox)control).getLabelSuffix();
									%>
									<%=labelSuffix%>
									<%
										}
										else if(control.getClass()==MasterConfig.CLASS_TEXTAREA)
										{
											System.out.println("MOd JSP...3");
									%>
									<input type="text" readonly="readonly" name="control[<%=idx%>]" value="<bean:write name= "mstHandlerModifyForm" property='<%="control["+idx+"]"%>' scope='request'/>" tabindex="1"/>
									<br>
									<%
										}
										else if(control.getClass()==MasterConfig.CLASS_RADIOBUTTON)
										{
											ArrayList alText =((RadioButton)control).getText();
											ArrayList alValue =((RadioButton)control).getValue();
											String label="";
											System.out.println("control"+controls[idx.intValue()]);
											for(int i=0;i<alText.size();i++)
											{
												String val=(String)alValue.get(i);
												if(controls[idx.intValue()].equals(val))
												{
													label=(String)alText.get(i);
												}
											}
									%>
									<b><%=label%></b>
									<%
										}
										else if(control.getClass()==MasterConfig.CLASS_CHECKBOX)
										{
											ArrayList alText =((CheckBox)control).getText();
											ArrayList alValue =((CheckBox)control).getValue();
									%>
									<bean:define name="mstHandlerModifyForm" property='<%="multiSelect["+idx+"].data"%>' id="multiSelected" type="java.lang.String[]" />
									<%
											for(int i=0;i<alText.size();i++)
											{
												System.out.println("alValue.get(i):  "+alValue.get(i)+"  multiSelected.length:  "+multiSelected.length);
												for(int j=0; j<multiSelected.length; j++)
												{
													System.out.println("j:"+j+" alValue.get(i): " +alValue.get(i)+" multiSelected[j]:  "+multiSelected[j] );
													if(multiSelected[j].trim().equals(((String)alValue.get(i)).trim()))
													{
									%>
									<%=alText.get(i)%>
									<input type="text" readonly name="control[<%=idx%>]" value="<%=alValue.get(i)%>" tabindex="1"/>
									<br>
									<%
														break;
													}
												}
											}
										}
									%>
									</td>
								</tr>
							</logic:notEqual>
						</logic:iterate>
						</table>
					</td>
				</tr>				
				<tr>
					<td class="ShadedSubTitleTagImage">&nbsp;</td>
				</tr>
			</table>
			<table width="60%" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<his:ButtonToolBarTag>
							<img style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' onKeyPress="if(event.keyCode==13)submitpage('CANCEL');" onClick='submitpage("CANCEL");' tabindex="1">
						</his:ButtonToolBarTag>
					</td>
				</tr>
			</table>
			<input type="hidden" name="hmode">
			<input type="hidden" name="seat_id" value='<%=session.getAttribute("SEATID")%>'>
		</div>
	</his:statusInProcessWithJsp>
</html:form>

<div align="center"><b><his:status /></b></div>

	</body>
</html>
<%
	}
	catch(Exception e)
	{
		System.out.println("Exception in mstHandleView..."+e);
		e.printStackTrace();
	}
%>