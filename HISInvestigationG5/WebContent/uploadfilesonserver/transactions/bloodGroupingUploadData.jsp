<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<%@page import="uploadfilesonserver.*"%>
<%@page import="hisglobal.presentation.Status"%>
<%-- <his:javascript src="/hisglobal/js/jquery1-1.10.2.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>

<script language="JavaScript" src="/BLDAHIMS/hisglobal/utility/generictemplate/js/commonDesigner.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-migrate-1.0.0.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.min.js"></script>
 --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<style type="text/css">_fnLengthChange
.tdfont  {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size:12;
	text-align:left;
	color: #000000;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: normal;
	text-align: left;
	text-transform: capitalize;
}
.tdfonthead  {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size:12px;
	text-align:right;
	background-color: #e0ebeb;
	background-image: none;
	color: #000000;
	font-family: Verdana,Arial,Helvetica,sans-serif;
	
	font-weight: normal;
	height: 22px;
}
</style>
<script>
function submitform()
{
		var fileName = document.getElementsByName("uploadedFileName")[0].value;
		var ext = fileName.substring(fileName.lastIndexOf("."),fileName.length).toUpperCase();
		//alert(ext);
		//alert(fileName);
		/* if (ext==".UPL")
			{}
		else
			{
			alert("Only File with extension UPL is Allowed");
			return;
			}
		if(fileName=="")
			alert('Please Select A File');	
		else{
			elem = document.getElementsByName('hmode')[0];
			//alert(elem.value);
			elem.value = "UPLOAD" ;
			document.forms[0].submit();
 	}*/		
 	elem = document.getElementsByName('hmode')[0];
 	elem.value = "UPLOAD" ;
 	document.forms[0].submit();
	}
function cancelFunc()
{
	window.parent.closeTab();
}
</script>
<body>
<html:form action="/bloodGroupingUpload" enctype="multipart/form-data" method="post">



	<%-- <his:ContentTag>
		<his:TitleTag name="BloodGroupingFileUpload">
		</his:TitleTag>
	</his:ContentTag> --%>
	
	<his:ContentTag>
	 			
					<table width="100%" cellspacing="1" cellpadding="0">
					
			  <tr>
	   				<td width="10%" height="25" class="tdfonthead">
	   				<div align="right">
	   				<B>
					FileName
					</B>
					</div>
					</td>
				<td widht="25%" class="tdfonthead">
				<div align="left">
						 <html:file name="BloodGroupingUploadFB" property="uploadedFileName"/>
				</div>	
	  			</td>
	  			<td width="17%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><label><b>CR.NO</b></label>  
							<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> </font></div>
						</td>						
						<td width="17%" class="tdfont">
						<div align="left"><html:text name="BloodGroupingUploadFB"
							property="crno" styleClass="textbox" tabindex="1"
							maxlength="50" onchange="isAlpha(this,'First Name')"
							onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></div>
						</td>
	  			</tr>
	  		
				</table>  
			</his:ContentTag>
			<logic:present name='errorMsg'>
        <logic:notEmpty name='errorMsg'>
            <logic:notEqual name='errorMsg' value='null'>
                <font color='red' size=3><%=(String)request.getAttribute("errorMsg")%></font>
            </logic:notEqual>
        </logic:notEmpty>
    </logic:present>		
 	
<his:ButtonToolBarTag> 
     <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style=cursor:pointer  onclick ="submitform()" onkeypress="if(event.keyCode==13) submitform()">
     <!-- <input type= "hidden" name="hmode"/>  -->             
     <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style=cursor:pointer  onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
        
</his:ButtonToolBarTag> 
	<html:hidden name="BloodGroupingUploadFB" property="hmode" />
</html:form>

</body>
</html>	
				
	
	
