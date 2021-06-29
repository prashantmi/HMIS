
<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script>


function moveUP(listNo)
{
	var frm=document.getElementsByName("DeskWiseDefaultProfileMstFB");
	var list;	

	if(listNo=="1")
		list = document.forms[0].menuListDefault;	
	
	var len=list.length;
	for(var i=0;i<len;i++)
	{
		if(list.options[i].selected)
		{
			if(i==0) return;
			
			var temp;
			temp=list.options[i-1].value;
			list.options[i-1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i-1].text;
			list.options[i-1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i-1].selected=true;;
			list.options[i].selected=false;
		}
	}
}

function moveDOWN(listNo)
{
	var frm=document.getElementsByName("DeskWiseDefaultProfileMstFB");
	var list;	

	if(listNo=="1")
		list = document.forms[0].menuListDefault;	
	
	var len=list.length;
	for(var i=len-1;i>=0;i--)
	{
		if(list.options[i].selected)
		{
			if(i==(len-1)) return;
			
			var temp;
			temp=list.options[i+1].value;
			list.options[i+1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i+1].text;
			list.options[i+1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i+1].selected=true;;
			list.options[i].selected=false;
		}
	}
}


function moveRightSingle(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		target  = document.forms[0].menuListDefault;
		source = document.forms[0].menuListNonDefault;	
	}
		var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		target  = document.forms[0].menuListDefault;
		source = document.forms[0].menuListNonDefault;	
	}
	
	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="2")
	{
		target  = document.forms[0].menuListNonDefault;
		source = document.forms[0].menuListDefault;	
	}

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="2")
	{
		target  = document.forms[0].menuListNonDefault;
		source = document.forms[0].menuListDefault;	
	}
		var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}

function MoveToSelected()
{
	
	
	// Select All Default Menus
	if(document.forms[0].menuListDefault)
	{
		for(var i=0;i<document.forms[0].menuListDefault.options.length;i++)
			document.forms[0].menuListDefault.options[i].selected=true;
	}
	
	// Select All Non Default menus
	if(document.forms[0].menuListNonDefault)
	{
		for(var i=0;i<document.forms[0].menuListNonDefault.options.length;i++)
			document.forms[0].menuListNonDefault.options[i].selected=true;
	}
	
}


function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	return true;
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}


</script>

<body>
		<html:form action="/master/DeskWiseDefaultProfile.cnt">
			<html:hidden name="DeskWiseDefaultProfileMstFB" property="hmode"/>
	
			<his:TransactionContainer>

			<his:TitleTag name="Desk Wise default Profile Master">
			</his:TitleTag>
		
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="deskType"/></b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="DeskWiseDefaultProfileMstFB" property="deskTypeId"   onkeypress="if(event.keyCOde==13)submitForm('DESKNAME');" onchange="submitForm('DESKNAME');" style="width:150;" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.DESK_TYPE_LIST%>" >
									<html:options collection="<%=OpdConfig.DESK_TYPE_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="deskName"/> </b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="DeskWiseDefaultProfileMstFB" property="deskId"  onkeypress="if(event.keyCOde==13)submitForm('MENUNAME');" onchange="submitForm('MENUNAME');" style="width:150;" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.DESK_NAME_LIST%>" >
									<html:options collection="<%=OpdConfig.DESK_NAME_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="nonDefaultProfileMenu"/>
								</b>
								</font>
							</div>
						</td>
								
						<td width=25%" class="tdfont">
							
						</td>
								
						<td width=25%" class="tdfonthead">
						<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="defaultProfileMenu"/></b>
								</font>
							</div>		
						</td>
						</tr>
						<tr>						
							<td width="25%"  class="tdfonthead">
							<div align="center" >
								<html:select name="DeskWiseDefaultProfileMstFB" property="menuListNonDefault" multiple="true" size="6" >
									<logic:present name="<%=OpdConfig.NON_DEFAULT_MENU_NAME_LIST%>" >
										<html:options collection="<%=OpdConfig.NON_DEFAULT_MENU_NAME_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						
						</td>
						<td width="25%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("2");'/> 	
							</div>
						</td>
						
				<td width="15%"  class="tdfonthead">
							<div align="center"  >
												<table>
													<tr>
														<td class="tdfonthead" valign="middle">
								
								<html:select name="DeskWiseDefaultProfileMstFB" property="menuListDefault" multiple="true" size="6" >
									
								<logic:present name="<%=OpdConfig.DEFAULT_MENU_NAME_LIST%>" >
										<html:options collection="<%=OpdConfig.DEFAULT_MENU_NAME_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
														</td>
														<td class="tdfonthead" valign="middle">
															<div align="center">
								&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP("1");'/>
							<br>
							<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN("1");'/>
															</div>
														</td>
													</tr>
												</table>
								
							</div>
							
						</td>
						
					</tr>
					<tr>
					
					
						<td width="25%"  class="tdfonthead"></td>
						<td width="25%"  class="tdfont"></td>
						<td width="25%"  class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
			
	
	<his:ButtonToolBarTag>
								 	 
		<logic:equal name="DeskWiseDefaultProfileMstFB" property="hmode" value="MENUNAME">
			<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" tabindex="1" onclick="finalSubmit('SAVE')">
		</logic:equal>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('MODIFY')" onkeypress="if(event.keyCode==13) submitPage('MODIFY')">
			
	</his:ButtonToolBarTag>	

	<html:hidden name="DeskWiseDefaultProfileMstFB" property="deskId"/>
	<html:hidden name="DeskWiseDefaultProfileMstFB" property="deskTypeId"/>
			</his:TransactionContainer>
		</html:form>
		<center><b><his:status/></b></center>
	</body>
</html>