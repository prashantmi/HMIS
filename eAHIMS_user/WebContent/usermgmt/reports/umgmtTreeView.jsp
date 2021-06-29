<html>
<head>
<title>Tree</title>
<script>
window.history.forward();
</script>
		
<script language='javascript'>


function generalMethod(divId,imgObj)
{
	var levelOne = document.getElementById(divId);	
	if(	levelOne.style.display=='none')
	{
		imgObj.src='../../images/minus.gif';
		levelOne.style.display='block';
	}
	else if(levelOne.style.display=='block')
	{
		imgObj.src='../../images/plus.gif';		
		levelOne.style.display='none';
	}
	else
	{
		imgObj.src='../../images/minus.gif';	
		levelOne.style.display='none';
	}
}


function levelOneAction(imgObj)
{	
	generalMethod('levelOne',imgObj);
}


function levelTwoAction(divId,imgObj)
{
	generalMethod(divId,imgObj);	
}

function levelThreeAction(divId,imgObj)
{
	generalMethod(divId,imgObj);

}

function onLoadMethod()
{
	var levelOne = document.getElementById('levelOne');		
	levelOne.style.display='none';	

}

function passValues(groupId,seatId,roleId)
{
	//alert("  Group Id is "+groupId+"  Seat  Id is "+seatId+"  Role  Id is "+roleId);	
}
</script>
</head>
<!-- 
<body background="../../images/cdac1.psd.gif" onLoad='onLoadMethod();'> -->
<body  onLoad='onLoadMethod();'>

<%
String hospital_Code="";
hospital_Code=(String)session.getAttribute("HOSPITAL_CODE");

		
System.out.println("------------ user profile tree view --------------");
usermgmt.reports.umgmtTree ut = new usermgmt.reports.umgmtTree();
ut.setHospitalCode(hospital_Code);
ut.buildTree();
out.println(ut.displayTree());
%>
</body>
</html>