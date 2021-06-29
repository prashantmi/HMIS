<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<html>
<head>
<meta charset=utf-8>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" Type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">
 <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"  id="style1">


<%-- <link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" Type="text/css">
<link href="../css/basic.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

 <link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet"/>
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet"/>

<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.min.css" rel="stylesheet"/>

<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet"/> 
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script> 
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>--%>
 
<style type='text/css'>

.bedbutton{
border          : 3px #B5B5B5 outset;
padding         : 25 20px;
color           : black;
cursor          : pointer ;
cursor          : hand;
text-align      : center;
text-decoration : none;
font            : normal 13px Verdana;
}


.well {
    min-height: 20px;
    padding: 19px;
    margin-bottom: 20px;
    background-color: #f5f5f5;
    border: 1px solid #e3e3e3;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.05);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.05);
   
}

.bedImages{
margin-left:22%;
height: 25px;
width: 50px;
cursor: pointer;
vertical-align: middle;

}
.bedImagesFocus{
height: 30px;
width: 60px;
cursor: pointer;
vertical-align: middle;

}
.bedImagesSmall{
height: 20px;
width: 50px;
cursor: pointer;
vertical-align: middle;

}
.aval
{
color: #004573;
font-weight: 700;
display: inline-block;
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 9px;
}
.bedbutton:visited, .bedbutton:hover, .bedbutton:active{
color: grey;
}
</style>
<title>Bed Status Popup</title>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/toolTip.js"></script>


<script type="text/javascript">

/* function getAjaxResponse(res,mode)
{
	if(mode==101)
	{
		document.getElementById("allData").innerHTML=res;
	}
} */ 
 
 function hideDetailsH(obj)
{
 document.getElementById("minusonLineIdH").style.display="none";
 document.getElementById("plusonLineIdH").style.display="block";
 document.getElementById("bedStatHelpDtl").style.display="none";
}
function showDetailsH()
{
 document.getElementById("plusonLineIdH").style.display="none";
 document.getElementById("minusonLineIdH").style.display="block";
 document.getElementById("bedStatHelpDtl").style.display="block";
}

function resize_popUp()
{
 var newHeight=document.forms[0].strWinResize.value;
 window.resizeTo('600',newHeight);
} 

/****** function for setting selected bed in parent window Bed combo******/
function selBed(obj,bedCode)
{
 if(confirm("You have selected Bed:"+obj.id+" .Are you sure ?"))
 {
  // alert("Selected Bed Id->"+obj.id);
   document.forms[0].strSelBed.value=obj.id;
  // alert("bed->"+document.forms[0].strSelBed.value);
 
   var indx;
   var objBed;
   var lenBedCmb;
   if(typeof(window.opener.document.forms[0].strbed)=="undefined")
   {
     objBed=window.opener.document.getElementsByName("strBed");
   }
   else
   {
     objBed=window.opener.document.getElementsByName("strbed");
   }
   for(var i=0;i<objBed.length;i++)
   {
    if(objBed[i].disabled==false)
    indx=i;
   }
   //[objBed[indx].selectedIndex]
   objBed[indx].value=bedCode;
 //  alert("bedParent->"+objBed[indx].value);
   window.close();
   return true;
  }
  else
    return false; 
} 
<%--  function getBedProperty()
{
	var hmode = "BEDPROPERTIES"; 
	var url="IpdCNT.cnt?hmode="+hmode+"&modPopUp=<%=request.getParameter("modPopUp")%>";
	ajaxFunction(url,"101");
}  --%> 
</script>
</head>
<body onLoad="resize_popUp()" onUnload="">
<html:form 	action="/transactions/IpdCNT" method="post">

<div class="normalMsg" id="normalMsg"></div>
	


<div id="allData" style="z-index: 1000">
	<bean:write name="ipdBean" property="strBedProperty" filter="false" />
<input type="hidden" name="strWinResize" value="${ipdBean.strWinResize}">
</div>

</html:form>
</body>
</html>