<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<%@page import="ehr.*"%>
<%@page import="emr.*"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>

<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="ehr.followup.vo.EHRSection_FollowupVO"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>One Page Prescription</title>      

<script>

function submitPdf()
{
	window.submitForm('HEADER');
}



</script>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/popup.js"/>
</head>
<style>


/* #accordion a {
	background-image: linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0% !important);
	background-image: -o-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
	background-image: -moz-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
	background-image: -webkit-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
	background-image: -ms-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
    background-color: transparent !important;
    -webkit-border-radius: 0px !important;
    -moz-border-radius: 0px !important;
    border-radius: 0px !important;
    -webkit-box-shadow: 0px rgba(180, 180, 180, 0.1) !important;
    -moz-box-shadow: 0px rgba(180, 180, 180, 0.1) !important;
    box-shadow: 0px 0px 0px 0px rgba(180, 180, 180, 0.1) !important; 
    display: inline !important;    
    border: 0px solid #DDDDDD !important;
    text-shadow: 0 0px 0px #FFFFFF !important; 
} */
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

function getUploadedeDoc(event,visitNo)
{
	//alert(patCrNo);
	//openDependentPopup(path,event,300,600,'yes');	
	var left=(screen.width-600)/2;
	var top=(screen.height-400)/2;
	window.open(createFHashAjaxQuery("/HISClinical/emr/uniPagePrescription.cnt?hmode=VIEWPRESCRIPTION&visitNo="+visitNo),"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
}



function getPDF()
{
	//alert("21");
	}
</script>

<body style="margin:0%">



<div>
	
	<logic:notEmpty name="<%=EHRConfig.PREVIOUS_PRESCRIPTIONS%>" >
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					
				<td width="30%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<%-- <bean:message key="disease" /> --%>
								Visit No.
								</b>
						</font>
					</div>
				</td>
				
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<%-- <bean:message key="diagonosisType" /> --%>
								Visit Date
							</b>
						</font>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<%-- <bean:message key="diagnosisSite" /> --%>
								File Name 
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font id="fntStar" color="#FF0000"></font>
								<%-- <bean:message key="remark" /> --%>
								Prescribed By
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font id="fntStar" color="#FF0000"></font>
								<%-- <bean:message key="remark" /> --%>
								Prescription
							</b>
						</font>
					</div>
				</td>
			</tr>
		
		
		<logic:iterate id="PatientClinicalDocDetailVO" name="<%=EHRConfig.PREVIOUS_PRESCRIPTIONS%>" indexId="id" type="emr.vo.PatientClinicalDocDetailVO" >
		<tr>
				 
					<td width="15%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="PatientClinicalDocDetailVO" property="serialNo" /> 
								 
							</font>
						</div>
					</td>   
					
					<td width="15%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="PatientClinicalDocDetailVO" property="visitDate" /> 
								  
							</font>
						</div>
					</td>   
				
					<td width="27%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="PatientClinicalDocDetailVO" property="fileName" />
								
							</font>
						</div>
					</td>   
					<td width="27%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="PatientClinicalDocDetailVO" property="seatId" />
								
							</font>
						</div>
					</td>
					
					<td width="27%"  class="">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<!-- <a onclick = "getPDF()">get PDF</a> -->
								<a style="cursor:pointer" onclick="getUploadedeDoc(event,'<%=PatientClinicalDocDetailVO.getSerialNo()%>')">View PDF</a>
							</font>
						</div>
					</td>   
				
				</tr>
				
		</logic:iterate>
		</table>
	</his:ContentTag>
</logic:notEmpty>
	
	
	
</div>


</form>
</body>
</html>