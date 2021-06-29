<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld"  prefix="his"%>
<his:css src="/hisglobal/css/hisStyleExt.css" />
<html>
<script type="text/javascript">
function callThisOnload()
{
blinkColor(); 
}
function callMe()   //what is trId
{

	parent.document.getElementById("frmMain").src= "/AHIMS/alertmanagement/alertDesk.cnt";		//set the required page src.
}
function showTitle(these)
{
these.title="Alert Desk";
}

function blinkColor()
{
  document.getElementById("alertInboxId").style.background="red"
  setTimeout("setblinkColor()",500)
}

function setblinkColor()
{
  document.getElementById("alertInboxId").style.background="green"
  setTimeout("blinkColor()",500)
}



</script>
<head>
<title>Copyright File</title>
</head>
<body topmargin="0" leftmargin="0" bottommargin="0" >  
 <table align="center" cellpadding="0">
 <tr>
 <td>
 <div align="left" style="position: absolute;left: 10%;top: 5%;">
 <img src="../hisglobal/images/AlertDeskFlat.png" onclick="callMe()" onmouseover="showTitle(this)" onmousemove="showTitle(this)" >
</div>

 <%--This Alert Tag is Commented To improve the Performance at the time of the login --%>
<his:AlertTag>
</his:AlertTag>



 </td>
 </tr>
 </table>
</body>
</html>
