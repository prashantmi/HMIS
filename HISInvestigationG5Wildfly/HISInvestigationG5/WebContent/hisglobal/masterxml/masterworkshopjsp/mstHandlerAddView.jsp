<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page language="java" import="hisglobal.masterxml.masterworkshop.tools.*,java.util.*,hisglobal.masterxml.masterworkshop.*;"%>

<bean:define name="mstHandlerAddForm" property="staticPage" id="pag" type="java.lang.String" />

<logic:notEmpty name="mstHandlerAddForm" property="staticPage">
	<jsp:forward page="<%=pag%>" />
</logic:notEmpty>

<html>
	<head>
		<his:javascript src="/hisglobal/js/calendar.js" />
		<his:javascript src="/hisglobal/js/MastersAddModifyValidations.js" />
		<his:javascript src="/hisglobal/js/time.js" />
		<his:javascript src="/hisglobal/js/validationCommon.js" />
		<his:javascript src="/hisglobal/js/validationCalls.js" />
		<his:javascript src="/hisglobal/js/utilityFunctions.js" />
		<his:javascript src="/hisglobal/js/commonFunctions.js" />
		<his:javascript src="/hisglobal/js/dateFunctions.js" />

		<his:css src="/hisglobal/css/masterXml.css" />
		<his:css src="/hisglobal/css/Color.css" />
		<his:css src="/hisglobal/css/calendar-blue2.css" />
		
		<%!
		private String getConstraintString(Map constraintMap)
		{
			String strConstraints="";
			Set setConstraints= constraintMap.keySet();
			Iterator itr=setConstraints.iterator();
			while (itr.hasNext())
			{
				String key=(String)itr.next();
				key=key.trim();
				//System.out.println("keykey:::::"+key);
				if(key.equalsIgnoreCase("inputType"))
				{
					String value=(String)constraintMap.get(key);
					if(value.equalsIgnoreCase("numeric"))
					{
						strConstraints=strConstraints+"onkeypress=\"return validateNumeric(event)\""+" ";
					}
					if(value.equalsIgnoreCase("alphabets"))
					{
						strConstraints=strConstraints+"onkeypress=\"return validateAlphabetsOnly(event,this)\""+" ";
					}
					if(value.equalsIgnoreCase("alphanumeric"))
					{
						
						strConstraints=strConstraints+"onkeypress=\"return validateAlphaNumericOnly(event,this)\""+" ";
					}
				}
				if(key.equalsIgnoreCase("mandatory"))
				{}
				if(key.equalsIgnoreCase("datepicker"))
				{}
				if(key.equalsIgnoreCase("methodstring"))
				{
					strConstraints=strConstraints+(String)constraintMap.get(key)+" ";
				}
				if(key.trim().equalsIgnoreCase("styleString"))
				{
					//System.out.println("inside styleString");
					strConstraints=strConstraints+(String)constraintMap.get(key)+" ";
				}
				else
				{
					strConstraints=strConstraints+key+"="+"'"+(String)constraintMap.get(key)+"'"+" ";
					//System.out.println("strConstraints::::::"+strConstraints);
				}
			}
			//System.out.println("strConstraintsfinal::"+strConstraints);
			return strConstraints;
		}
			
		private boolean isDatePicker(Map constraintMap)
		{
			boolean dtpick=false;
			Set setConstraints= constraintMap.keySet();
			Iterator itr=setConstraints.iterator();
			while (itr.hasNext())
			{
				String key=(String)itr.next();
				if(key.equalsIgnoreCase("datepicker"))
				{
					dtpick=true;
					return dtpick;
				}
			}
			return dtpick;
		}
		
		private boolean isMandatory(Map constraintMap)
		{
			boolean mandatory=false;
			Set setConstratints= constraintMap.keySet();
			Iterator itr = setConstratints.iterator();
			while(itr.hasNext())
			{
				String key=(String)itr.next();
				if(key.trim().equalsIgnoreCase("mandatory"))
				{
					String val = (String)constraintMap.get(key);
					if(val.trim().equalsIgnoreCase("true"))
						mandatory = true;
					break;
				}
			}
			return mandatory;
		}
		
		private String getReadonly(boolean rdOnly)
		{
			String rd="";
			if(rdOnly)
			{
				rd="readonly";
			}
			return rd;
		}
		%>
		
		<%
			//System.out.println("inside add view 1");
			//System.out.println("inside add view 3");
			//System.out.println("inside add view 4");
		%>
		
		<title>
			<%//System.out.println("inside add view 4"); %> 
			<bean:write name="TOLstObject" property="title" />[Add]
		</title>
	
		<script Language=javascript>
		window.history.forward()
		


function submitpage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}	

function validateOnSave(mode)
{
	var sysDate=new Date();	
	var	effectivefrom="";
	document.forms[0].currentDate.value=sysDate.getDate()+'-'+sysDate.toDateString().substr(4,3)+'-'+sysDate.getFullYear();
	<%
		MasterAddTO addTO= (MasterAddTO)session.getAttribute("TOAddObject");	
		//System.out.println("inside add view 4");
		ArrayList alControls=addTO.getControls();
		//System.out.println("inside add view size"+alControls.size());
		for(int i=0;i<alControls.size();i++)
		{
			Control ctrl=(Control)alControls.get(i);
			//System.out.println("inside add view size"+ctrl.getClass());
			if(ctrl.getClass()==MasterConfig.CLASS_TEXT||ctrl.getClass()==MasterConfig.CLASS_TEXTAREA)
			{
				Map mp =(Map)ctrl.getConstraints();
				String mandatory=(String)mp.get("mandatory");
				String datePicker=(String)mp.get("datepicker");
				//System.out.println("datePicker   ="+datePicker);
				if(mandatory!=null&& mandatory.equalsIgnoreCase("true"))
				{
	%>
	elem=document.getElementsByName("control[<%=i%>]")[0];
	if(elem.value=="")
	{
		alert(""+"<%=ctrl.getLabel()%>"+":::Can not be left blank");
		elem.focus();
		return false;
	}
	<%
					if(datePicker!=null&&datePicker.equals("true"))
					{
						if(ctrl.getLabel().replaceAll(" ","").toLowerCase().equals("effectivefrom"))
						{
	%>
	effectivefrom=elem;
	<%
						}
	%>
	if(!compareDateCall(document.forms[0].currentDate,elem,2,'current Date' ,'<%=ctrl.getLabel()%>'))
	{
		return false;
	}
	<%
					}
				}
				if( ctrl.getLabel().replaceAll(" ","").toLowerCase().equals("effectiveto"))
				{
	%>
	elem=document.getElementsByName("control[<%=i%>]")[0];
	if(elem.value!=null && elem.value!="")
		if(!compareDateCall(effectivefrom,elem,2,'Effective From','<%=ctrl.getLabel()%>'))
		{
			return false;
		}
	<%
				}
			}
			if(ctrl.getClass()==MasterConfig.CLASS_COMBO)
			{
				String mandatory=((Combo)ctrl).getSelectionMandatory();
				//System.out.println("mandatory:::"+mandatory);
				if(mandatory!=null&& mandatory.equalsIgnoreCase("true"))
				{
	%>
	elem=document.getElementsByName("control[<%=i%>]")[0];
	if(elem.value==-1)
	{
		alert(""+"<%=ctrl.getLabel()%>"+"::::Please select valid value");
		elem.focus();
		return false;
	}
	<%
				}
			}
		}
	%>
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}
		</script>
	</head>

	<body topmargin="0" leftmargin="0">

<%
	//System.out.println("in add view.jsp");
	try
	{
%>

<html:form action="/masterWorkshop/mstHandlerAddAction" method="post">
	<his:statusInProcessWithJsp>
		<div align="center">
			<table width="60%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="MasterXMLHeader">
						<b><bean:write name="TOLstObject" property="title" />&gt;&gt;Add</b>
					</td>
				</tr>
				<tr>
					<td class="ShadedSubTitleTagImage">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<bean:define id="controls" name="mstHandlerAddForm" property="controls" type="java.lang.Object[]" />
							<logic:iterate name="TOAddObject" property="controls" id="control" indexId="idx">
							<tr>
								<td width="50%" class="tdfonthead">
									<div align="right">
									<%
										if(control.getClass()==MasterConfig.CLASS_TEXT)
										{
											Map constraintsMap1=((TextBox)control).getConstraints();
											if(isMandatory(constraintsMap1))
											{
									%>
									<font color="#FF0000">*</font>
									<%
											}
										}
										if(control.getClass()==MasterConfig.CLASS_COMBO )
										{
											//System.out.println("value selection mandatory"+((Combo)control).getSelectionMandatory());
											if(((Combo)control).getSelectionMandatory()!=null && !((Combo)control).getSelectionMandatory().equalsIgnoreCase("false"))
							  				{
							  		%>
							  		<font color="#FF0000">*</font>
							  		<%
							  				}
										}
										if(control.getClass()==MasterConfig.CLASS_TEXTAREA)
										{
											Map constraintsMap1=((TextArea)control).getConstraints();
											if(isMandatory(constraintsMap1))
											{
									%>
									<font color="#FF0000">*</font>
									<%
											}
										}
										if(control.getClass()==MasterConfig.CLASS_CHECKBOX)
										{
											Map constraintsMap1=((CheckBox)control).getConstraints();
											if(isMandatory(constraintsMap1))
					            			{
									%>
									<font color="#FF0000">*</font>
									<%
											}
										}
									%>
									<bean:write name="control" property="label" />
									</div>
								</td>
								<td width="50%" class="tdfont">
								<%
									if(control.getClass()==MasterConfig.CLASS_COMBO)
									{
										//System.out.println("MasterConfig.CLASS_COMBO: "+idx);
										boolean submitRequired=((Combo)control).getSubmitRequired();
										if(submitRequired)
										{
								%>
									<html:select name="mstHandlerAddForm" property='<%="control["+idx+"]"%>' onchange="submitpage('RESUBMIT');" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:optionsCollection name="control" property="options" label="label" value="value" />
									</html:select>
								<%
										}
										else
										{
								%>
									<html:select name="mstHandlerAddForm" property='<%="control["+idx+"]"%>' tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:optionsCollection name="control" property="options" label="label" value="value" />
									</html:select>
								<%
										}
										String labelSuffix =((Combo)control).getLabelSuffix();
								%>
								<%=labelSuffix%>
								<%
									}
									else if(control.getClass()==MasterConfig.CLASS_TEXT)
									{
										//System.out.println("MasterConfig.CLASS_TEXT: "+idx);
										//System.out.println("inside Add view");
										Map constraintsMap=((TextBox)control).getConstraints();
										if(isDatePicker(constraintsMap))
										{
											int x=idx.intValue();
											//System.out.println("x:::::"+x);
											String c="control["+String.valueOf(x)+"]";
											String val=(String)controls[idx.intValue()];
								%>
								<his:date name="<%=c%>" dateFormate="%d-%b-%Y" value="<%=val%>" />
								<%
										}
										else
										{
								%>
								<input type="text" name="control[<%=idx%>]" <%=getReadonly(((TextBox)control).getReadOnly())%> value="<bean:write name= "mstHandlerAddForm" property='<%="controls["+idx+"]"%>'/>" <%=getConstraintString(constraintsMap)%> tabindex="1"/>
								<%
									String labelSuffix =((TextBox)control).getLabelSuffix();
								%>
								<%=labelSuffix%>
								<%
										}
									}
									else if(control.getClass()==MasterConfig.CLASS_TEXTAREA)
									{
										Map constraintsMap=((TextArea)control).getConstraints();
								%>
								<input type="textarea" name="control[<%=idx%>]" value="<bean:write name= "mstHandlerAddForm" property='<%="controls["+idx+"]"%>'/>" <%=getConstraintString(constraintsMap)%> tabindex="1"/>
								<%
									}
									else if(control.getClass()==MasterConfig.CLASS_RADIOBUTTON)
									{
										ArrayList alText =((RadioButton)control).getText();
										ArrayList alValue =((RadioButton)control).getValue();
										//System.out.println("controls"+controls[idx.intValue()]);
										for(int i=0;i<alText.size();i++)
										{
											String s="";
											String val=(String)alValue.get(i);
											if(controls[idx.intValue()].equals(val))
											{
												s="checked";
											}
								%>
								<%=alText.get(i)%>
								<input type="radio" name="control[<%=idx%>]" value="<%=alValue.get(i)%>" <%=s%> tabindex="1"/>
								<%
											}
										}
										else if(control.getClass()==MasterConfig.CLASS_CHECKBOX)
										{
											ArrayList alText =((CheckBox)control).getText();
											ArrayList alValue =((CheckBox)control).getValue();
								%>
								<bean:define name="mstHandlerAddForm" property='<%="multiSelect["+idx+"].data"%>' id="multiSelected" type="java.lang.String[]" />
								<%
											for(int i=0;i<alText.size();i++)
											{
								%>
								<%=alText.get(i)%>
								<input type="checkbox" name="multiSelect[<%=idx%>].data" value="<%=alValue.get(i)%>"
								<%
												//System.out.println("alValue.get(i):  "+alValue.get(i)+"  multiSelected.length:  "+multiSelected.length);
												boolean checked = false;
												for(int j=0; j<multiSelected.length; j++)
												{
													//System.out.println("j:"+j+" alValue.get(i): " +alValue.get(i)+" multiSelected[j]:  "+multiSelected[j] );
													if(multiSelected[j].trim().equals(((String)alValue.get(i)).trim()))
													{
														checked = true;
														break;
													}
												}
												if(checked)
												{
													//System.out.println("Checked:  "+alValue.get(i));
								%>checked<%}%> tabindex="1"/>
								<%
												}
											}
								%>
								</td>
							</tr>
							</logic:iterate>
						</table>
					</td>
				</tr>
				<tr>
					<td class="ShadedSubTitleTagImage">&nbsp;</td>
				</tr>
			</table>
		</div>
	</his:statusInProcessWithJsp>
	
	<div align="center">
		<table width="60%" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<his:ButtonToolBarTag>
						<img style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-sv.png"/>' onKeyPress="if(event.keyCode==13) validateOnSave('ADDSAVE')" onClick="validateOnSave('ADDSAVE');" tabindex="1" />
						<img style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' onKeyPress="if(event.keyCode==13) submitpage('CANCEL')" onClick='submitpage("CANCEL");' tabindex="1" />
					</his:ButtonToolBarTag>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="hmode">
	<input type="hidden" name="seat_id" value='<%=session.getAttribute("SEATID")%>'>
	<input type="hidden" name="currentDate" value="">
	
</html:form>
<%
	
	}
	catch(Exception e)
	{
		System.out.println("Exception in mstHandleAddView..."+e);
		e.printStackTrace();
	}
%>
<div align="center"><b><his:status /></b></div>

</body>
</html>
