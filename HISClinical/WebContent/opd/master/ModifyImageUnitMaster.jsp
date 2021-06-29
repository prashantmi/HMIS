<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
	
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import ="java.util.*,registration.*,opd.*,hisglobal.vo.*,hisglobal.presentation.*" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
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
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>


function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}


function submitMyForm(flag,mode)
{
	if(flag)
	{
		document.getElementsByName('hmode')[0].value=mode;
		submitFormOnValidate(flag,mode);
	}
}



function validateSaveForm()
{
	if(document.forms[0].deptUnitCode.value=="-1")
	{
		alert("Select Unit ...")
		document.forms[0].deptUnitCode.focus();
		return false;
	}
	
	if(document.forms[0].selectedImages.options.length==0)
	{
		alert("Choose at Least One Image");
		document.forms[0].selectedImages.focus();
		return false;
	}
	MoveToSelected();
	return true;
}




function MoveToSelected()
{
	// Unselect Remaining Images
	for(var i=0;i<document.getElementsByName("unselectedImages")[0].length;i++)
		document.getElementsByName("unselectedImages")[0].options[i].selected=false;
	
	// Select All Image in Selected
	for(var i=0;i<document.getElementsByName("selectedImages")[0].length;i++)
		document.getElementsByName("selectedImages")[0].options[i].selected=true;
}




function moveRightSingle()
{
	var source;
	var target;

	
	source  = document.getElementsByName("unselectedImages")[0];
	target = document.getElementsByName("selectedImages")[0];	
	
	

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




function moveLeftSingle()
{
	var source;
	var target;

		target  = document.forms[0].unselectedImages;
		source = document.forms[0].selectedImages;	


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




function moveLeftAll()
{
	var source;
	var target;

	
		target  = document.forms[0].unselectedImages;
		source = document.forms[0].selectedImages;	
	

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






function moveRightAll()
{
	var source;
	var target;
	
	
		source  = document.forms[0].unselectedImages;
		target = document.forms[0].selectedImages;	
	

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

function getUploadedImage(event,path)
{
	openDependentPopup(path,event,600,700,'yes');
}
</script>

	
	
	
	<body>
		<html:form action="/master/AddOPDUnitImageMaster.cnt">
			<html:hidden  name="OPDUnitImageMasterFB" property="hmode"/>
			<his:TransactionContainer>
			<%
				boolean varIsNewStatus=false;
				String varStatus="";
			%>
			<his:statusNew>
			<%
				varIsNewStatus=true;
				varStatus="New";
			%>
			</his:statusNew>
	
		<logic:equal name="OPDUnitImageMasterFB" property="hmode" value="MODIFY">
			<his:TitleTag name="MODIFY UNIT WISE IMAGES">
			</his:TitleTag>
			
			
	
	
	<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%"  class="tdfonthead"></td>
					<td width="50%"  class="tdfont"></td>
				</tr>
				<tr>
					<td width="50%"  class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="sel"/> <bean:message key="unit"/>
							</font>
						</div>
						
				    </td>
					<td width="50%"  class="tdfont">
						<div align="left">
							<html:hidden name="OPDUnitImageMasterFB" property="deptUnitCode"/>
							&nbsp;<b><bean:write name="OPDUnitImageMasterFB" property="deptUnitName"/></b>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%"  class="tdfonthead"></td>
					<td width="50%"  class="tdfont"></td>
				</tr>
			</table>
				
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%"  class="tdfonthead"></td>
					<td width="20%"  class="tdfont"></td>
					<td width="40%"  class="tdfonthead"></td>
				</tr>
				<tr>
					<td width="40%"  class="tdfonthead">
						<div align="center">
							<html:select name="OPDUnitImageMasterFB" property="unselectedImages" multiple="true" size="6" >
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_UNSELECTED_IMAGE_LIST%>" >
								<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_UNSELECTED_IMAGE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					 </td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll();'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll();'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="OPDUnitImageMasterFB" property="selectedImages" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_SELECTED_IMAGE_LIST%>" >
								<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_SELECTED_IMAGE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>				
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitMyForm(validateSaveForm(),'MODIFYSAVE')" onclick="submitMyForm(validateSaveForm(),'MODIFYSAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="MoveToSelected(); submitPage('CANCEL');" onkeypress="if(event.keyCode==13){MoveToSelected(); submitPage('CANCEL');}">
				</span>
			</his:ButtonToolBarTag>	
		</logic:equal>
		
		
		
		<logic:equal name="OPDUnitImageMasterFB" property="hmode" value="VIEW">
			<his:TitleTag name="VIEW UNIT WISE IMAGES">
			</his:TitleTag>
			
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0" align="center">
				<tr>
					<td width="50%"  class="tdfonthead"  align="center"></td>
					<td width="50%"  class="tdfont"  align="center"></td>
					
				</tr>
				<tr>
					<td width="50%"  class="tdfonthead">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 <bean:message key="unit"/>&nbsp;&nbsp;
						</font>
				    </td>
				    
					<td width="50%"  class="tdfont">
						&nbsp;&nbsp;<b><bean:write name="OPDUnitImageMasterFB" property="deptUnitName" /></b>
					</td>
				</tr>
				<%int i=0; %>
				<logic:iterate name="<%=OpdConfig.ARR_VIEW_IMAGE_UNIT_WISE_VO%>" id="tempBean" type="hisglobal.vo.OPDUnitImageMasterVO">
				<% i++; %>
				<%String path=ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
				+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS + "&" 
				+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.IMAGE_EXAMINATION_IMAGE_PATH_LINUX + "&" 
				+ ServletsUtilityConfig.FILE_NAME +"="+tempBean.getFileName(); %>
				
				<tr>
					<td width="50%"  class="tdfonthead">
						<% if(i==1){%>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="images"/>&nbsp;&nbsp;
						</font>
						<%}%>
				    </td>
					<td width="50%"  class="tdfont">&nbsp;&nbsp;
					<a  style="cursor:pointer" onclick="getUploadedImage(event,'<%=path%>')" >
						<b><bean:write name="tempBean" property="imageName"/></b></a>
					</td>
				</tr>
				</logic:iterate>
			  	<tr>
			    	<td width="50%"  class="tdfonthead"></td>
					<td width="50%"  class="tdfont"></td>
				</tr>
			</table>
		</his:ContentTag>
						
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				</span>
			</his:ButtonToolBarTag>	
			</logic:equal>
			
			<html:hidden name="OPDUnitImageMasterFB" property="slNo"/>
	</his:TransactionContainer>
	</html:form>
	<center><b><his:status/></b></center>
	</body>
	