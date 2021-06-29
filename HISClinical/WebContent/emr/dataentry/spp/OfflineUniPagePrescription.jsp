<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<%@page import="ehr.*"%>
<%@page import="emr.*"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
<%@page import="registration.RegistrationConfig"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Offline UniPagePrescription</title>     
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
<!-- <script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> -->
<his:javascript src="/registration/js/popup.js"/>
<script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>


<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />



</head>
<style>

input, select, textarea{
border: 1px solid #bbb;
}
.panel-heading{
 background:linear-gradient(to bottom, #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%);
 
}
.panel-heading .accordion-toggle:after {
    /* symbol for "opening" panels */
    font-family: 'Glyphicons Halflings';  /* essential for enabling glyphicon */
    content: "\e114";    /* adjust as needed, taken from bootstrap.css */
    float: right;        /* adjust as needed */
    color: grey;         /* adjust as needed */
   
}
.panel-heading .accordion-toggle.collapsed:after {
    /* symbol for "collapsed" panels */
    content: "\e080";    /* adjust as needed, taken from bootstrap.css */
}
fieldset.scheduler-border {
    border: 1px groove blue !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0px 5px 10px;
        border-bottom:none;
        color:
    }
    #mynav {
        top: 0;
        position: fixed;
        left: 0;
        right: 0;
        margin: 0 auto;
        z-index: 1030;
        height:5px;
        color:#aaa;
       /*  background-color:#222; */
        background-color:#ffff;
       
      }
      
     .modal-dialog
     {
     	width:1100px;
     }
     
     .textAlign
     {
     	text-align:left;
     }
     .panelHeadingPadding
     {
     	padding:1px;
     }
     .aPanelBody
     {
     	color:white;
     	font-weight:bold;
     }
     
      .success {
  -webkit-animation: seconds 1.0s forwards;
  -webkit-animation-iteration-count: 1;
  -webkit-animation-delay: 10s;
  animation: seconds 1.0s forwards;
  animation-iteration-count: 1;
  animation-delay: 10s;
  position: relative;
    
}
@-webkit-keyframes seconds {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    left: -9999px; 
    position: absolute;   
  }
}
@keyframes seconds {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    left: -9999px;
    position: absolute;     
  }
}

  </style>

<script>


function getClinicalSummaryEntryForm_IPD()
{
document.forms[0].action="/HISClinical/emr/uniPagePrescription.cnt?hmode=NEW"; 
document.forms[0].submit();
}

</script>


<body>
<%-- <his:TransactionContainer> --%>
	<html:form action="/clinicalSummaryEntry">
			<his:TitleTag name="Offline UniPagePrescription">
			</his:TitleTag>
		<his:InputCrNoTag name="UniPagePrescriptionFB"></his:InputCrNoTag>
		
			
				<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
				
					<his:SubTitleTag name="Episode Details">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="5%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="10%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="visitType"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="episode"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="department"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="unit"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="admissionNo"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="ward"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:present name="<%= RegistrationConfig.ARR_OPEN_EPISODE_VO %>">
							<%-- <logic:iterate name="<%=RegistrationConfig.ARR_OPEN_EPISODE_VO  %>" id="episodeVO" type="hisglobal.vo.EpisodeVO" indexId="index">
								<tr>
									<td width="5%" class="tdfont">
										<div align="center">
										 <input type="radio" name="UniPagePrescriptionFB" tabindex="1"  value="1" onclick='submitForm("GETUNIPAGEPRESCRIPTIONOPD");'/> 
										</div>
									</td>
									<td  width="10%"  class="tdfont">
									<div align="center">
										OPD
										</div>
										
									</td>
									<td  width="15%"  class="tdfont">
									<div align="center">
											<bean:write name="episodeVO" property="episodeCode"/>
										</div>
									
									</td>
									<td  width="15%" class="tdfont">
										<div align="center">
											<bean:write name="episodeVO" property="department"/>
										</div>
									</td>
									<td  width="15%" class="tdfont">
										<div align="center">
										 <bean:write name="episodeVO" property="departmentUnit"/>	 
										
										</div>
									</td>
									
									<td  width="15%" class="tdfont">
										<div align="center">
											-
										</div>
									</td>
									
									<td  width="15%" class="tdfont">
										<div align="center">
										-
										</div>
									</td>
									
										
								</tr>
							</logic:iterate> --%></logic:present>
							<logic:present name="<%= RegistrationConfig.ADMITTED_PATIENT_VO %>">
							<bean:define id="admittedVO" name="<%=RegistrationConfig.ADMITTED_PATIENT_VO%>" type="hisglobal.vo.EpisodeVO"></bean:define>
							
								<tr>
									<td width="5%" class="tdfont">
										<div align="center">
										 <input type="radio" name="UniPagePrescriptionFB" tabindex="1"  value="1" onclick='getClinicalSummaryEntryForm_IPD()'/>  <!--  submitForm("GETUNIPAGEPRESCRIPTIONIPD"); -->
										</div>
									</td>
									<td  width="10%"  class="tdfont">
									<div align="center">
										IPD
										</div>
										
									</td>
									<td  width="15%"  class="tdfont">
									<div align="center">
											<bean:write name="admittedVO" property="episodeCode"/>
										</div>
									
									</td>
									<td  width="15%" class="tdfont">
										<div align="center">
											<bean:write name="admittedVO" property="department"/>
										</div>
									</td>
									<td  width="15%" class="tdfont">
										<div align="center">
										 <bean:write name="admittedVO" property="departmentUnit"/>	 
										
										</div>
									</td>
									
									<td  width="15%" class="tdfont">
										<div align="center">
											<bean:write name="admittedVO" property="admissionNo"/>
										</div>
									</td>
									
									<td  width="15%" class="tdfont">
										<div align="center">
											<bean:write name="admittedVO" property="ward"/>
										</div>
									</td>
									
										
								</tr>
							</logic:present>
						</table>		
					</his:ContentTag>
				
			
	
		<html:hidden name="UniPagePrescriptionFB" property="hmode" />
		<html:hidden name="UniPagePrescriptionFB" property="patCrNo"/>
		
	
	
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
	</his:ButtonToolBarTag>
	</html:form>
	
	<his:status/>
<%-- </his:TransactionContainer> --%>
</body>

</html>