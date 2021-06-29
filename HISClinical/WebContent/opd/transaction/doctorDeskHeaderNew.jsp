
<%--##		Modify Date				: 	16-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Modified By				:	AKASH SINGH
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
 <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/all.css">
 <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/fontawesome.min.css">

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<script type="text/javascript">
window.onload= function()
{
	setPatStats();
	setDeskHeader();
}

function setPatStats()
{	var jsonArray = updatePatientCounts();
	var arrayLength = jsonArray.length;
	//alert(arrayLength);
	var htmlStats = "";
	for (var j=0; j<arrayLength; j++) 
	{	
		var dataStats = jsonArray[j];
		//alert(dataStats);
		if(dataStats!=null)
		{
			htmlStats += "<b><font color='#fffff' style='font-size:12px;' face='Verdana, Arial, Helvetica, sans-serif'>";
			htmlStats += dataStats.header + "&nbsp;&nbsp;";
			//alert(htmlStats);
			for(var i=0; i<dataStats.counts.length; i++)
			{
				
				htmlStats += "</b></font><b><font color='#fffff' style='font-size: 12px;' face='Verdana, Arial, Helvetica, sans-serif' >";
				htmlStats += dataStats.counts[i].header + ":" ;
				if(i==0)
				{htmlStats += "<style= 'font-size: 10px;vertical-align:bottom;' face='Verdana, Arial, Helvetica, sans-serif'>";}
				if(i==1)
				{htmlStats += "<font color='#FF0000' style='font-size: 10px;vertical-align:bottom;' face='Verdana, Arial, Helvetica, sans-serif'>";}
				if(i==2)
				{htmlStats += "<font color='#0000FF' style='font-size: 10px;vertical-align:bottom;' face='Verdana, Arial, Helvetica, sans-serif'>";}				
				htmlStats += "<span class='badge' style='background-color: #33cc33'>"+dataStats.counts[i].counts+"</span>" + "&nbsp;&nbsp;";
			}
			htmlStats += "</b></font>";				
			if(j!=(arrayLength-1)){htmlStats += "<br>";}
			//alert(htmlStats);
		}
		document.getElementById("divPatDeskStatistics").innerHTML = htmlStats;
		break;
	}
}

function setDeskHeader()
{	
	var htmlHeader = "";
	var _deskType = document.getElementsByName("deskType")[0].value
}


// AJAX Functions
function updatePatientCounts()
{
		var flg = false;
		var objPatientCount = null;
		var _mode = "AJX_G_PATIENTS_COUNT";
		var _deskType = document.getElementsByName("deskType")[0].value
		var urlNew= "/HISClinical/opd/doctorDeskHeaderNew.cnt?hmode="+_mode+'&deskType='+_deskType;
// alert(urlNew);
		var objXHR = {url: createFHashAjaxQuery(urlNew), sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				//alert("Data"+data.header +" "+data.counts);
				objPatientCount = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information..in--> [ docterDeskHeader.jsp ]");
	            objPatientCount = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);
		//alert(objPatientCount);
		return objPatientCount;
}

function changeDesk(obj)
{
	var url = "/HISClinical/hisglobal/utility/dynamicdesk/enterNew.cnt?deskType=" + obj.value;
	var url1=createFHashAjaxQuery(url);	
	//parent.parent.document.getElementById("f2").src
	//var obj=parent.parent.parent.document.getElementById("frmMain");
	//alert(parent.frameElement);
	obj = parent.frameElement;
	obj.src= url1;
	obj.refresh();
	//alert("hello");
}



</script>
<style type="text/css">
#doctorDeskHeader {
		
		font-weight: bold;
		font-family: arial;
		font-size: 14px;
		color: #FFFFFF;
		padding-top:2px;
		text-align: left;
		vertical-align:bottom;
		padding-left: 6px;
}

</style>

</head>

<body>
<html:form action="/doctorDeskHeaderNew">
	<div class="container-fluid"  style="background:linear-gradient(to bottom,#3277b3,#88b5dd);border-radius:5px;">
		
		<div  id="doctorDeskHeader" class="col-xl-8 col-md-8 col-sm-8" >
		<i class='fas fa-user-md' style='font-size:20px'></i>
			<bean:define id="deskType" name="DoctorDeskFB" property="deskType" type="java.lang.String"></bean:define>	
			
			<%=DynamicDeskConfig.DESK_TYPES[Integer.parseInt(deskType)]%>
			
	
		</div>	
		<div align="right" class="col-xl-4 col-md-4 col-sm-4" id="divPatDeskStatistics">
		
		</div>
		
	</div>
	
	
<%-- 	<table class="table table-bordered table-condensed">
		<tr><td class="ShadedTitleTagImage">
			<table cellpadding="2" cellspacing="0" width="100%" height="100%">
				<tr><td class="TitleTagFontStyle">
					<table width="100%" cellpadding="0" cellspacing="0" height="100%">
						<tr>
							<td width="60%">
								<div id="doctorDeskHeader" >
								<bean:define id="deskType" name="DoctorDeskFB" property="deskType" type="java.lang.String"></bean:define>	
									<%=DynamicDeskConfig.DESK_TYPES[Integer.parseInt(deskType)]%>
								</div>
							</td>
							<td width="40%">
								<div align="right" id="divPatDeskStatistics">
								</div>
							</td>	
						</tr>
					</table>
				</td></tr>
			</table>
		</td></tr>
	</table> --%>

<html:hidden name="DoctorDeskFB" property="hmode"/>
<html:hidden name="DoctorDeskFB" property="deskType"/>
</html:form>
</body>
</html>