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

function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	
	if(listNo=="1")
	{
		source  = document.forms[0].menuIdLst;
		target = document.forms[0].selmenuIdLst;	
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
		source  = document.forms[0].menuIdLst;
		target = document.forms[0].selmenuIdLst;	
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

	if(listNo=="1")
	{
		target  = document.forms[0].menuIdLst;
		source = document.forms[0].selmenuIdLst;	
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

	if(listNo=="1")
	{
		target  = document.forms[0].menuIdLst;
		source = document.forms[0].selmenuIdLst;	
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

function FormValidate(mode)
{
	if(document.forms[0].selmenuIdLst && document.forms[0].selmenuIdLst.options.length==0)
	{
		alert("Please Select Menu");
		document.forms[0].menuIdLst.focus();
		return false;
	}
	else
	{
		submitPage(mode);
	}
	
}

function MoveToSelected()
{
	
	 //Unselect Remaining Symptoms
	if(document.forms[0].menuIdLst)
	{	
		for(var i=0;i<document.forms[0].menuIdLst.options.length;i++)
			document.forms[0].menuIdLst.options[i].selected=false;
	}
	
	
	// Select All Symptoms in Selected
	if(document.forms[0].selmenuIdLst)
	{
		for(var i=0;i<document.forms[0].selmenuIdLst.options.length;i++)
			document.forms[0].selmenuIdLst.options[i].selected=true;
	}
		
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}

function submitPage(mode)
{
	MoveToSelected();	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

</script>
			<body>
				<html:form action="/master/DeskTypeMenuMappingMaster.cnt">
				
				<his:TitleTag name="Desk Type Menu Mapping Master">
				</his:TitleTag>
			 <table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%"  class="tdfonthead">
						<div align="right">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="deskType"/>
								</b>
							</font>
						</div>
			  		</td>
			  	  <td width="50%" class="tdfont" >
			  		<bean:write name="DeskTypeMenuMappingMasterFB" property="deskTypeDesc"/>
			  	  </td>
			  	</tr>
			 </table>				
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
			  		<td width="100%"  class="tdfonthead">
						<div align="center">	           
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="menu"/></b>
							</font>
						</div>
			  		</td>
			 	 </tr>	
			 </table>		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				 <tr>
			  	 	<td width="35%"  class="tdfonthead">
						<div align="center">
							<html:select name="DeskTypeMenuMappingMasterFB" property="menuIdLst" multiple="true" size="10" style="width: 200px;" >
								<logic:present name="<%=OpdConfig.ALL_MENU_LIST%>">
							  	<html:options collection="<%=OpdConfig.ALL_MENU_LIST%>" property="value" labelProperty="label" />
							  	</logic:present>
							</html:select>
						</div>
					</td>
					<td width="15%"  class="tdfont" >
						<div align="center">
							<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
							<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
							<br><br>
							<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
							<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
						</div>
					</td>
					<td width="35%"  class="tdfonthead">
						<div align="center">
							<html:select name="DeskTypeMenuMappingMasterFB" property="selmenuIdLst" multiple="true" size="10" style="width: 200px;" >
							</html:select>
						</div>
					</td>
			  	</tr>
			</table>
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="FormValidate('SAVE')" onkeypress="if(event.keyCode==13) FormValidate('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="MoveToSelected(); submitForm21('CANCEL');" onkeypress="if(event.keyCode==13){ MoveToSelected(); submitForm21('CANCEL');}">
		    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="MoveToSelected(); submitForm21('ADD');" onkeypress="if(event.keyCode==13){ MoveToSelected(); submitForm21('ADD');}">
		    </his:ButtonToolBarTag>		
			<center><b><his:status/></b></center>
		<html:hidden name="DeskTypeMenuMappingMasterFB" property="hmode"/>
		<html:hidden name="DeskTypeMenuMappingMasterFB" property="deskType"/>
		<html:hidden name="DeskTypeMenuMappingMasterFB" property="deskTypeDesc"/>
			
			</html:form>
		 </body>
		</html>