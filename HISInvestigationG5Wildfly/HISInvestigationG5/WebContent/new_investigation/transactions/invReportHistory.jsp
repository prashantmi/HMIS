<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : REPORT HISTORY
 ## Purpose						        : 
 ## Date of Creation					: 28/12/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

  -->
  <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.vo.InvValueAuditVO"%>
<%@page
	import="new_investigation.transactions.controller.fb.invReportHistoryFB"%>
 <%@page import="new_investigation.vo.invReportHistoryVO"%>
	
<%@page import="new_investigation.vo.LabTestVO"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
							
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
 <his:css src="/hisglobal/css/Cannedstyle.css" />
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/commonUtility.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	 
	 
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvValueAuditFB"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>
<script>

function enbalebtn()
{

	document.getElementById('btn1').style.display="";


		}

function enbalebtn11()
{

	document.getElementById('btn111').style.display="";


		}
function enbalebtn1()
{

	document.getElementById('btn11').style.display="";


		}
		

function startservice()
{

   if(document.getElementsByName('resumeservice')[1].value="2")
	   {

	   document.getElementsByName('hmode')[0].value='RESUMESERVICE';
		document.forms[0].submit()
		
	   }
	
}



function startservice11()
{

   if(document.getElementsByName('resumeservice')[1].value="2")
	   {

	   document.getElementsByName('hmode')[0].value='SETDATAREFRANGE';
		document.forms[0].submit()
		
	   }
	
}


function startservice1()
{

   if(document.getElementsByName('resumeservice')[0].value="2")
	   {

	   document.getElementsByName('hmode')[0].value='FORCEGENERATE';
		document.forms[0].submit()
		
	   }
	
}


function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function showLegends1(){
	  document.getElementById("divLegends1").style.display="block"; 
}
function showLegendsNone1(){
document.getElementById("divLegends1").style.display="none";
}

function showLegends2(){
	  document.getElementById("divLegends2").style.display="block"; 
}
function showLegendsNone2(){
document.getElementById("divLegends2").style.display="none";
}
function showPatList()
{

	if(!validateTodayOrDate())
	 {return false;}
	 
	 var flag=0;
	 var value=-1;
	for(var a=0;a<document.getElementsByName('active').length;a++)
		{
             
		 if(document.getElementsByName('active')[a].checked==true)
			 {
           flag=1;
           value=document.getElementsByName('active')[a].value;
			 }

		}

	 if(flag==0)
	 {
	 alert("Please Choose Data");
	 return null;
	 }
		
                 if(value=="0")
                     {
                	 document.getElementsByName('hmode')[0].value='SHOWREPORTDATA';
                		document.forms[0].submit();
                     }
                 
                 if(value=="1")
                     {
                	 document.getElementsByName('hmode')[0].value='SHOWREPORTDATAALL';
             		document.forms[0].submit();
                     }

                 if(value=="2")
                 {
            	 document.getElementsByName('hmode')[0].value='SHOWREPORTDATAALLINACTIVE';
         		document.forms[0].submit();
                 }
		
	
}
 
</script>

<style type="text/css">
 a:hover {
  cursor:pointer;
 }
</style>
   
<style>
.NewTextBox {
	border:2px solid #456879;
	border-radius:10px;
	height: 22px;
	width: 330px;
}
.textBoxCss {
    background: white;
    color: #135d8c;
    width: 180px;
    padding: 4px 10px 4px 15px;
    border-radius: 20px;
    box-shadow: 0 1px 0 #ccc inset;
    transition: 500ms all ease;
    outline: 0;
}
  .ui-autocomplete {
    max-height: 100px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
  }
  
  
</style>

<style>
 .setAdvisedBy {
 
    position: fixed;
    top: 180px;
    right: 50px;
   z-index: 100;
}
</style>


<style>
.scroll_div {
	height: 50px;
	overflow-y: hidden;
	overflow-x: scroll;
	text-align: justify;
	margin: 0;
	border-style: groove;
	padding: 5px 5px 5px 5px;
	scrollbar-face-color: #666669;
	scrollbar-highlight-color: #030000;
	scrollbar-3dlight-color: #030000;
	scrollbar-darkshadow-color: #030000;
	scrollbar-shadow-color: #030000;
	scrollbar-arrow-color: #030000;
	scrollbar-track-color: #030000;
}
</style>
<style>
#colorCycle {
	background-color: #8C8984;
	border: medium solid #1277b5;
	padding-top: 5px;
	padding-right: 7px;
	padding-bottom: 7px;
	padding-left: 7px;
	color: #FFF;
	text-align: left;
	animation-name: homeCycle;
	animation-duration: 6s;
	animation-direction: alternate;
	animation-iteration-count: infinite;
	-webkit-animation-name: homeCycle;
	-webkit-animation-duration: 6s;
	-webkit-animation-direction: alternate;
	-webkit-animation-iteration-count: infinite;
}

@
keyframes homeCycle { 0% {
	background-color: #3366CC;
}

25%
{
background-color
 

:

 


#003399








;
}
50%
{
background-color








:








#6699FF








;
}
75%
{
background-color








:








#3366CC








;
}
}
@
-webkit-keyframes homeCycle { 0% {
	background-color: #3366CC;
}
25%
{
background-color








:




 




#003399








;
}
50%
{
background-color








:








#6699FF








;
}
75%
{
background-color








:








#3366CC








;
}
}
</style>
<style>
.textBoxCss {
	background: white;
	color: #135d8c;
	width: 180px;
	padding: 4px 10px 4px 15px;
	border-radius: 20px;
	box-shadow: 0 1px 0 #ccc inset;
	transition: 500ms all ease;
	outline: 0;
}
</style>


<style>
.NewTextBox {
	border:2px solid #456879;
	border-radius:10px;
	height: 22px;
	width: 330px;
}
</style>

<his:SubTitleTag name="REPORT DETAIL">
<body onload="showdataonload()">
</his:SubTitleTag>
<html:form action="/invReportHisotry">
	<html:hidden name="invReportHistoryFB" property="hmode" />  
	<html:hidden name="invReportHistoryFB" property="currentPage" />
	<html:hidden name="invReportHistoryFB" property="showStatus" />
	<html:hidden name="invReportHistoryFB" property="sysDate" />
	
	  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
        
            <%
			      UserVO uservo=ControllerUTIL.getUserVO(request);
			      Date todayDateobj = new Date();
					SimpleDateFormat dateob = new SimpleDateFormat("yy");
					String strDate= dateob.format(todayDateobj);
			      String hospitalCode=uservo.getHospitalCode();
			      String val=uservo.getHospitalCode()+strDate;
			      %>
			      
      <bean:define name="invReportHistoryFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="invReportHistoryFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%> 
	
	<table   width="100%" bgcolor="#EBEBEB"   >
	
	 <tr>            
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
			</td>
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
 		</tr>
	
	 <tr>            
 			<td class="tdfont" width="25%">
        		
			</td>
			<td class="tdfont" width="25%">
	    	<input type="radio" name="active" value="0" ><b>Active</b>	
			</td>
 			
			<td class="tdfont" width="25%">
	    		<input type="radio" name="active" value="1" ><b>Closed </b>
			</td>
			<td class="tdfont" width="25%">
        		<input type="radio" name="active" value="2" ><b>Not Active </b>
				&nbsp;	&nbsp;	&nbsp;
				 <input type="radio" name="resumeservice" value="1" onclick="enbalebtn()"><b>Resume Service </b>
				 				 <input type="radio" name="resumeservice" value="2" onclick="enbalebtn11()"><b>SET Ref Range </b>
				 
				<!-- <input type="radio" name="resumeservice" value="2" onclick="enbalebtn1()"><b>Force Generate </b> -->	
			</td>
 		</tr>
	<tr>
	</table>
	<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="goButton" style="cursor: pointer;" onkeypress="if(event.keyCode==13) showPatList()" onclick="showPatList()" tabindex="1">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunctn() " onclick="cancelFunctn()" tabindex="1">
	<button type="button" id="btn1" onclick="startservice()" style="display:none">Resume Service</button>
		<button type="button" id="btn111" onclick="startservice11()" style="display:none">SET RANGE</button>
	
	<button type="button" id="btn11" onclick="startservice1()" style="display:none">Resume Service</button>
	</his:ButtonToolBarTag>
	
	
	
	
				 		
			
			
			
			 <logic:equal name="invReportHistoryFB" property="showStatus" value="1">
			
			  <%boolean flag=false; %>
  	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((invReportHistoryFB)request.getAttribute("invReportHistoryFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.DATA_FOR_REPORT_HISTORY);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
				 
			
		<his:PaginationTag name="fbPagination"></his:PaginationTag>	
		<his:SubTitleTag name=" Active List"></</his:SubTitleTag>	
			<logic:notEmpty name="<%=InvestigationConfig.DATA_FOR_REPORT_HISTORY%>">
			
				  <table   width="100%" bgcolor="#EBEBEB"   >
				<tr>
					 
					<td width="5%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Service ID</font></b>
					</td>
					<td width="10%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Previous Date </font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Start Date
					</font></b>
					</td>
					
					
					
					<td width="5%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Frequency
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">MongoDb Name
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Mongo Uri
					</font></b>
					</td>
					
					<td width="15%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Pg Uri
					</font></b>
					</td>
					
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Pg UserName
					</font></b>
					</td>
					
					<td width="15%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Jar Path
					</font></b>
					</td>
					
						<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Active Time
					</font></b>
					</td>
					
				</tr>
			
					<%
					 List<invReportHistoryVO> lstPatVO=(List<invReportHistoryVO>)session.getAttribute(InvestigationConfig.DATA_FOR_REPORT_HISTORY);
					 int i,size=0;
					 
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
						for(int j=startIndex;j<=endIndex;j++)
					{
						
                            
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
					if(j<size)
									{
						invReportHistoryVO voPat=lstPatVO.get(j);
					
						//String labCode=voPat.getLabCode();

					 if(firstTimeTravesall)
			 			{
						   String color="";
					int activetime=Integer.parseInt(voPat.getActiveTime());
					int frequency=Integer.parseInt(voPat.getFrequency());	 
						 if(activetime<=frequency)
						 {color="green";}
						 else
						 {color="blue";}
						 
					%>
					 <% if(voPat.getIsValid().equals("1") && activetime<=frequency){%>
					
					<tr>
				  		
				  		
				  		<td width="5%" align="center">
				  		 
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getServiceId() %></font>
						  
				  		</td>
				  		<td width="10%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getEntryDate() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getInsertDate() %></font>
						 
				  		</td>
				  		
				  		
				  		
				  		<td width="5%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getFrequency() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMongoDbNmae() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMongoUri() %></font>
						 
				  		</td>
				  		
				  		<td width="15%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPgUri() %></font>
						 
				  		</td>
				  		
				  		
				  		
				  		<td width="10%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPgUsername() %></font>
						 
				  		</td>
				  		
				  		
				  			<td width="15%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getReportPath() %></font>
						 
				  		</td>
				  		
				  			<td width="10%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getActiveReportTime() %></font>
						 
				  		</td>
					</tr>
				
				<%}}}} %>
							
                                  </table>


	 

					
					 <his:ButtonToolBarTag>
     		 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW') " onclick="submitForm('NEW')" tabindex="1">  
	          
	             </his:ButtonToolBarTag>
		
			</logic:notEmpty>
			<logic:empty name="<%=InvestigationConfig.DATA_FOR_REPORT_HISTORY%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
		</logic:equal>	
		
		
		
		<!-- // closed all datta -->
		 <logic:equal name="invReportHistoryFB" property="showStatus" value="2">
		 
		 
		  <%boolean flag=false; %>
  	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((invReportHistoryFB)request.getAttribute("invReportHistoryFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
		<his:PaginationTag name="fbPagination"></his:PaginationTag>		
		<his:SubTitleTag name="Closed List"></</his:SubTitleTag>
			<logic:notEmpty name="<%=InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL%>">
			
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					 
					<td width="5%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Service ID</font></b>
					</td>
					<td width="10%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Previous Date </font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Start Date
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">End Date
					</font></b>
					</td>
					
					<td width="5%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Frequency
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">MongoDb Name
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Mongo Uri
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Pg Uri
					</font></b>
					</td>
					
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Pg UserName
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Jar Path
					</font></b>
					</td>
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Active Time
					</font></b>
					</td>
					
				</tr>
			
					<%
					 List<invReportHistoryVO> lstPatVO=(List<invReportHistoryVO>)session.getAttribute(InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL);
					 int i,size=0;
					 
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
						for(int j=startIndex;j<=endIndex;j++)
					{
						
                            
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
					if(j<size)
									{
						invReportHistoryVO voPat=lstPatVO.get(j);
					
						//String labCode=voPat.getLabCode();

					 if(firstTimeTravesall)
			 			{
						   String color="";
					int activetime=Integer.parseInt(voPat.getActiveTime());
					int frequency=Integer.parseInt(voPat.getFrequency());	 
						

						  if(voPat.getIsValid().equals("0") )
						 {
							 color="red";
						 }
						 else 
						 {color="blue";}
						 
					%>
					 <% if(voPat.getIsValid().equals("1") && activetime<=frequency  )
				  		{}
				  			else if(voPat.getIsValid().equals("0") && activetime>frequency ){ %>
					
					<tr>
				  		
				  		
				  		<td width="5%" align="left">
				  		 
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getServiceId() %></font>
						  
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getEntryDate() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getInsertDate() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		 <%
				  		 String exitdate="";
				  		 if(voPat.getLastDate()==null)
				  		 {exitdate="-";}
				  		 else
				  		 {exitdate=voPat.getLastDate();}
				  		 %>
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=exitdate %></font>
						 
				  		</td>
				  		
				  		
				  		
				  		
				  		<td width="5%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getFrequency() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMongoDbNmae() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMongoUri() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPgUri() %></font>
						 
				  		</td>
				  		
				  		
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPgUsername() %></font>
						 
				  		</td>
				  		
				  		
				  			<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getReportPath() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getActiveReportTime() %></font>
						 
				  		</td>
					</tr>
				  		
				  		
				  	
				
				<%}}}} %>
							
                                  </table>


	 
	

					
					 <his:ButtonToolBarTag>
     		 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW') " onclick="submitForm('NEW')" tabindex="1">  
	         
	             </his:ButtonToolBarTag>
		
			</logic:notEmpty>
			<logic:empty name="<%=InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
		</logic:equal>	
		
		 
	 <!-- // inactive data -->
	 
		
		
	
		 <logic:equal name="invReportHistoryFB" property="showStatus" value="3">
		 
		 
		  <%boolean flag1=false; %>
  	 <%
				//Pagination Logic
					PaginationFB fbPage1= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage1);
					fbPage1.setCurrentPage(((invReportHistoryFB)request.getAttribute("invReportHistoryFB")).getCurrentPage());
					fbPage1.setObjArrName(InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL_INACTIVE);
					fbPage1.setAppendInTitle("List ");
					int maxRecord1=10;
					fbPage1.setMaxRecords(maxRecord1);
				 
				 %>
				 
		<his:PaginationTag name="fbPagination"></his:PaginationTag>		
		<his:SubTitleTag name=" Not Active List"></</his:SubTitleTag>
			<logic:notEmpty name="<%=InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL_INACTIVE%>">
			
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					 
					<td width="5%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
					Service ID</font></b>
					</td>
					<td width="10%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						Previous Date </font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Start Date
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">End Date
					</font></b>
					</td>
					
					<td width="5%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Frequency
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">MongoDb Name
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Mongo Uri
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Pg Uri
					</font></b>
					</td>
					
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Pg UserName
					</font></b>
					</td>
					
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Jar Path
					</font></b>
					</td>
					<td width="10%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Active Time
					</font></b>
					</td>
					
				</tr>
							<%
					 List<invReportHistoryVO> lstPatVO1=(List<invReportHistoryVO>)session.getAttribute(InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL_INACTIVE);
					 int i1,size1=0;
					 
			 		if(lstPatVO1!=null && lstPatVO1.size()>0 )
			 			size1=lstPatVO1.size();
					int startIndex1=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex1=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
						for(int j=startIndex1;j<=endIndex1;j++)
					{
						
                            
						//int i=j-startIndex;
						boolean firstTimeTravesall=true;
					if(j<size1)
									{
						invReportHistoryVO voPat=lstPatVO1.get(j);
					
						//String labCode=voPat.getLabCode();

					 if(firstTimeTravesall)
			 			{
						   String color="";
					int activetime=Integer.parseInt(voPat.getActiveTime());
					int frequency=Integer.parseInt(voPat.getFrequency());	 
						

						  if(voPat.getIsValid().equals("0") )
						 {
							 color="red";
						 }
						 else 
						 {color="blue";}
						 
					%>
					<% if(voPat.getIsValid().equals("0") && activetime<=frequency  )
				  		{}
				  			else if(voPat.getIsValid().equals("1") && activetime>frequency ){ %>
					
					<tr>
				  		
				  		
				  		<td width="5%" align="left">
				  		 
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getServiceId() %></font>
						  
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getEntryDate() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getInsertDate() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		 <%
				  		 String exitdate="";
				  		 if(voPat.getLastDate()==null)
				  		 {exitdate="-";}
				  		 else
				  		 {exitdate=voPat.getLastDate();}
				  		 %>
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=exitdate %></font>
						 
				  		</td>
				  		
				  		
				  		
				  		
				  		<td width="5%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getFrequency() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMongoDbNmae() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getMongoUri() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPgUri() %></font>
						 
				  		</td>
				  		
				  		
				  		
				  		<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPgUsername() %></font>
						 
				  		</td>
				  		
				  		
				  			<td width="10%" align="left">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getReportPath() %></font>
						 
				  		</td>
				  		
				  		<td width="10%" align="center">
				  		 
				  		<font color="<%=color%>" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getActiveReportTime() %></font>
						 
				  		</td>
					</tr>
				  		
				  		
				  	
				
				<%}}}} %>
							
                                  </table>
								  
								  	 <his:ButtonToolBarTag>
     		 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW') " onclick="submitForm('NEW')" tabindex="1">  
	          
	             </his:ButtonToolBarTag>
		
			</logic:notEmpty>
			<logic:empty name="<%=InvestigationConfig.DATA_FOR_REPORT_HISTORY_ALL_INACTIVE%>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			
		</logic:equal>	
		
		
		<div id="divLegends1" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0"
					style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					
					
					<tr>
						<td width="20%"><font color="green" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Active</div>
						</font></td>
						
					</tr>
					

				</table>
			</his:ContentTag>
		</div>
		
		<div id="divLegends2" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0"
					style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					<tr>
						<td width="20%"><font color="blue" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Not Active</div>
						</font></td>
						
					</tr>
					<tr>
						<td width="20%"><font color="red" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Closed</div>
						</font></td>
						
					</tr>
					
					
					

				</table>
			</his:ContentTag>
		</div>
					
	<logic:equal name="invReportHistoryFB" property="showStatus" value="1">
		<his:SubTitleTag>
			<his:name>
				<bean:message key="legends" />
			</his:name>
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"></td>
					<td width="30%">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Show </font><img
								src='<his:path src="/hisglobal/images/arrow_down.gif"/>'
								tabindex="1" onclick="showLegends1();"
								onkeypress="if(event.keyCode==13) showLegends1();"> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
								src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
								tabindex="1" onclick="showLegendsNone1();"
								onkeypress="if(event.keyCode==13) showLegendsNone1();">
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
		</logic:equal>
		
		
		
	<%-- 	<logic:equal name="invReportHistoryFB" property="showStatus" value="2">
		<his:SubTitleTag>
			<his:name>
				<bean:message key="legends" />
			</his:name>
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"></td>
					<td width="30%">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Show </font><img
								src='<his:path src="/hisglobal/images/arrow_down.gif"/>'
								tabindex="1" onclick="showLegends2();"
								onkeypress="if(event.keyCode==13) showLegends2();"> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
								src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
								tabindex="1" onclick="showLegendsNone2();"
								onkeypress="if(event.keyCode==13) showLegendsNone2();">
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
		</logic:equal> --%>
	
</html:form>
</body>
</html>  