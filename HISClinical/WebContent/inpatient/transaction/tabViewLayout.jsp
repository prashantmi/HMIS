<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<script> 
window.history.forward()
function onClickNurTab(mode)
{
	if(mode==1)
	{
		document.getElementById("div1").style.display = "block";
		document.getElementById("div2").style.display = "none";
	}
	else if(mode==2)
	{
		document.getElementById("div2").style.display = "block";
		document.getElementById("div1").style.display = "none";
	}
	
	var frmBody=parent.document.getElementById("frmNursingDesksBody");
/*	var formDocBody= null;	
	
	if(window.XMLHttpRequest) // Mozilla
	{
		formDocBody=frmBody.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 		formDocBody=frmBody.Document;
	}*/
	
	//if(formDocBody!=null && formDocBody.forms[0])
	{
		if(mode==1)
		{
			frmBody.src="/HISClinical/inpatient/nursingStationLogin.cnt";
		}
		else if(mode==2)
		{
			frmBody.src="/HISClinical/inpatient/nursingDeskLogin.cnt";
		}
	}
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<div class="tabGroup"><span class="tabGroupTop">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%" nowrap="nowrap">
							<div id="div1" style="display: block;">
							<ul id="tabs">
								<li id="tabNursingStat" class="current"><span>Nursing Acceptance Station</span></li>
								<li id="tabNursingDesk">
									<a href="javascript:onClickNurTab(2);">Nursing Desk</a></li>
							</ul>
							</div>
							<div id="div2" style="display: none;">
							<ul id="tabs">
								<li id="tabNursingDesk">
									<a href="javascript:onClickNurTab(1);">Nursing Acceptance Station</a></li>
								<li id="tabNursingStat" class="current"><span>Nursing Desk</span></li>
							</ul>
							</div>
						</td>
					</tr>
				</table>
			</span></div>
		</td>
	</tr>
</table>