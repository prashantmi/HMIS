<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

window.onload=function ()
{
	disabledField();
}

function disabledField()
{
	if(document.getElementsByName("complexion")[0].value!="")
		document.getElementsByName("complexion")[0].readOnly=true;	
	if(document.getElementsByName("hairColorLength")[0].value!="")
		document.getElementsByName("hairColorLength")[0].readOnly=true;
	if(document.getElementsByName("eyeColor")[0].value!="")
		document.getElementsByName("eyeColor")[0].readOnly=true;	
	if(document.getElementsByName("length")[0].value!="")
		document.getElementsByName("length")[0].readOnly=true;
	if(document.getElementsByName("weight")[0].value!="")
		document.getElementsByName("weight")[0].readOnly=true;
	if(document.getElementsByName("bodyLooks")[0].value!="-1")
		document.getElementsByName("bodyLooks")[0].disabled=true;
	if(document.getElementsByName("clothDetails")[0].value!="")
		document.getElementsByName("clothDetails")[0].readOnly=true;
	
	
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateDeceasedNo()
{
	var valid=false;
	if(document.getElementsByName("deceasedNo")[0].value=="")
	{
		alert("Please Enter The Deceased No");
		document.getElementsByName("deceasedNo")[0].focus();
		valid=false;
	}
	else
	{
		if(document.getElementsByName("deceasedNo")[0].value.length==14)
			valid=true;
		else
		{
			alert("Invalid Deceased No");
			document.getElementsByName("deceasedNo")[0].focus();
			valid=false;
		}
	}
	return valid;
}

function validateSave()
{
	var valid=false;
	var weight=parseFloat(document.getElementsByName("weight")[0].value);
	if(document.getElementsByName("weight")[0].value!="")
	{	
		if(weight > parseFloat(999.9) || weight <= parseFloat(0.0))
		{	
			alert("Please Enter The Valid Weight");
			document.getElementsByName("weight")[0].focus();
		}
		else
			valid=true;
	}
	else
		valid=true;
	//	alert(valid)		
	return valid;
	
}

/*function validateDecimalPoint(obj,e,len)
{
	if(obj.value.length==len)
		obj.value=obj.value+".";
}*/

function clearDeceasedNo()
{
	document.getElementsByName("deceasedNo")[0].value="";
}

function clearForm()
{
	if(!document.getElementsByName("complexion")[0].readOnly)
		document.getElementsByName("complexion")[0].value="";
	if(!document.getElementsByName("hairColorLength")[0].readOnly)
		document.getElementsByName("hairColorLength")[0].value="";
	if(!document.getElementsByName("eyeColor")[0].readOnly)
		document.getElementsByName("eyeColor")[0].value="";
	if(!document.getElementsByName("length")[0].readOnly)
		document.getElementsByName("length")[0].value="";
	if(!document.getElementsByName("weight")[0].readOnly)
		document.getElementsByName("weight")[0].value="";
	if(!document.getElementsByName("bodyLooks")[0].disabled)
		document.getElementsByName("bodyLooks")[0].value="-1";
	if(!document.getElementsByName("clothDetails")[0].readOnly)
		document.getElementsByName("clothDetails")[0].value="";							
}

function validateDecimalPoint(obj,e,len)
{
	if(obj.value.length==len)
		obj.value=obj.value+".";
}
function validateNumericNOneDotOnly(obj,e)
{	
	
	// Ascii Code 0 - 48 To 9 - 57 , for . - 46
    var k=e.charCode;
    var pattern=/\./;
    if( e.keyCode!=0 || ( !pattern.test(obj.value) && k==46)||(obj.value.length==0 && k==45) || (k>=48 && k<= 57))
        return true;
    else
        return false;
}

function validateWeight(obj,e)
{
//	alert(">>>>"+getCursorIdex(obj));
	if(e.charCode!=0)
	{
		var count=0;
		var dot=".";
		for(var i=0;i<obj.value.length;i++)
		{
			if(obj.value.charAt(i)==dot.charAt(0))count++;
		}
		
//		alert("len"+obj.value.length)
		if(getCursorIdex(obj)==0)
			return validateNumeric(e);
			
		if(getCursorIdex(obj)==1 || getCursorIdex(obj)==2)
			return validateNumericNOneDotOnly(obj,e);
		
		if(getCursorIdex(obj)==3) //&& count==0 && e.charCode!=46)
		{
			if(count==0) 
			{
				if(e.charCode==46)
					return true;
				else	
					return validateDot(obj,e);
			}
			else
			{
				return validateNumeric(e);
			}
		//	obj.value=obj.value+".";
		//	return true;
		}	
		else
			return validateNumericNOneDotOnly(obj,e);	
		
		if(getCursorIdex(obj)==4)
		{
			if(e.charCode==46)
			{	
				
				return true;
			}	
			else	
				return validateNumeric(e);
		}
			
	}
	else
		return true;
}

function validateDot(obj,e)
{
	var len=obj.value.length;
//	alert(len);
	var str="";
	if(len<5)
	{	
		for(var i=0;i<len;i++)
		{//alert(">>>"+i)
			if(i==2)
			{
				str=str+obj.value.charAt(i);
				str=str+".";
			//	alert("if>>"+str);
			}	
			else
				str=str+obj.value.charAt(i);	
		//	alert(str);	
		}
		obj.value=str;
		return true;
	}	
	else
		return true;	
}


function openSearchPopup(event)
{
	var path='/HISClinical/mortuary/deceasedGeneralAppearance.cnt?hmode=SEARCHPOPUP';
	openPrintPopup(path,300,700);
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
}

function printGeneralAppearance()
{
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=NONE&modePrint=ONLYHTML";
		//document.forms[0].action=url;
		openPrintPopup(url,300,700);
		//document.forms[0].submit();
}
</script>

<body>
	<html:form action="/deceasedGeneralAppearance">
		<his:TransactionContainer>
		<his:TitleTag name="Deceased General Appearance">
		</his:TitleTag>
		
		<his:statusDone> 
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="deceasedNo"/>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="left">
								<html:text name="DeceasedGeneralAppearanceFB" property="deceasedNo" maxlength="14" onkeypress= "return validateNumeric(event,this) " tabindex="1"></html:text>
							</div>
						</td>
						<td width="40%" class="tdfont">
							<div align="left">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateDeceasedNo()) submitPage('DECEASEDDTL')" onkeypress="if(event.keyCode==13){if(validateDeceasedNo()) submitPage('DECEASEDDTL')}">
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' style=cursor:pointer tabindex="1" onclick="openSearchPopup(event)" onkeypress="if(event.keyCode==13){openSearchPopup(event)}">
							</div>
						</td>
					</tr>	
				</table>
			</his:ContentTag>
		</his:statusDone>
		<his:statusTransactionInProcess>
			
			<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
		
			<his:SubTitleTag name="General Appearance">
			</his:SubTitleTag>
			
			<his:ContentTag>
			<div id="pdfPrintingHTMLData" align="center">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="60%" colspan="2" height="100%">
							<table width="100%" border="0" cellspacing="1" cellpadding="0" height="100%">
								<tr>
									<td width="50%"  class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="complexion"/>
											</font>
										</div>
									</td>
									<td width="50%"  class="tdfont">
										<div align="left">
											<html:text name="DeceasedGeneralAppearanceFB" tabindex="1" property="complexion" size="33" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
										</div>
									</td>
								</tr>
								<tr>
									<td width="50%"  class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												 <bean:message key="hairColorLength"/>
											</font>
										</div>
									</td>
									<td width="50%"  class="tdfont">
										<div align="left">
											<html:text name="DeceasedGeneralAppearanceFB" tabindex="1" property="hairColorLength" size="33" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
										</div>
									</td>
								</tr>
								<tr>
									<td width="50%"  class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="eyeColor"/>
											</font>
										</div>
									</td>
									<td width="50%"  class="tdfont">
										<div align="left">
											<html:text name="DeceasedGeneralAppearanceFB" tabindex="1" property="eyeColor" size="33" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" />
										</div>
									</td>									
								</tr>
								<tr>
									<td width="50%"  class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												 
												<bean:message key="bodyLooks"/>
											</font>
										</div>
									</td>
									<td width="50%"  class="tdfont">
										<div align="left">
											<html:select name="DeceasedGeneralAppearanceFB" property="bodyLooks" tabindex="1" >
												<html:option value="-1">Select value</html:option>
												<html:option value="<%=MortuaryConfig.DECEASED_BODY_LOOKS_FLAT%>">Flat</html:option>
												<html:option value="<%=MortuaryConfig.DECEASED_BODY_LOOKS_PLUMP%>">Plump</html:option>
												<html:option value="<%=MortuaryConfig.DECEASED_BODY_LOOKS_LEAN%>">Lean</html:option>
											</html:select>
											<logic:notEqual name="DeceasedGeneralAppearanceFB" property="bodyLooks" value="-1">
												<html:hidden name="DeceasedGeneralAppearanceFB" property="bodyLooks"/>
											</logic:notEqual>
										</div>
									</td>
								</tr>
								<tr>
									<td width="50%"  class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												 
												<bean:message key="length"/>
											</font>
										</div>
									</td>
									<td width="50%"  class="tdfont">
										<div align="left">
											<html:text name="DeceasedGeneralAppearanceFB" tabindex="1" property="length" maxlength="3" size="6" onkeypress="return validateNumeric(event)" />
											
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;&nbsp;Cm</font>
										</div>
									</td>									
								</tr>
								<tr>
									<td width="50%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">												 
												<bean:message key="weight"/>
											</font>
										</div>
									</td>
									<td width="50%"  class="tdfont">
										<div align="left">
											<html:text name="DeceasedGeneralAppearanceFB" tabindex="1" property="weight" maxlength="5" size="6" onkeypress="return  validateWeight(this,event)"  />											
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">&nbsp;&nbsp;Kg</font>
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td width="40%" colspan="1" class="tdfont">
							<div align="center">
								<table width="50%" border="1">
									<tr>
										<td>
											<bean:define name="DeceasedGeneralAppearanceFB" property="imageFilePath" id="filePath" type="java.lang.String"></bean:define>
											<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; %>
											<%String imagepath="/image/showImage?"+ imageIndex+"=0"; %>
											<img id="<%=filePath%>" src='<his:path src="<%=imagepath%>"/>' height="100%" width="100%" alt="No Image Found" />											
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" colspan="1" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">									 
									<bean:message key="clothDetails"/>
								</font>
							</div>
						</td>
						<td width="70%" colspan="2" class="tdfont">
							<div align="left">
								<html:textarea name="DeceasedGeneralAppearanceFB" property="clothDetails" rows="1" cols="96" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
							</div>
						</td>
					</tr>					
					<tr>
						<td width="30%" colspan="1" height="100%"></td>
						<td width="30%" colspan="1" height="100%"></td>
						<td width="40%" colspan="1" height="100%"></td>
					</tr>
				</table>
			</div>
			</his:ContentTag>
			
		</his:statusTransactionInProcess>
		
		<his:ButtonToolBarTag>
			<his:statusDone>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearDeceasedNo()" onkeypress="if(event.keyCode==13)clearDeceasedNo()">
			</his:statusDone>
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  style=cursor:pointer tabindex="1" onclick =" printGeneralAppearance()" onkeypress="if(event.keyCode==13)printGeneralAppearance()">				
			</his:statusTransactionInProcess>
		
		</his:ButtonToolBarTag>
		
		</his:TransactionContainer>
		
		<html:hidden name="DeceasedGeneralAppearanceFB" property="hmode"/>
		<html:hidden name="DeceasedGeneralAppearanceFB" property="patCrNo" />
		<html:hidden name="DeceasedGeneralAppearanceFB" property="deceasedNo" />
	</html:form>	
	
	<his:status/>
</body>
</html>