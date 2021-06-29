<html>
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<title>HIS</title>


<script>
history.forward();


</script>
	<link href="../css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../css/Color.css" rel="stylesheet" type="text/css">
	<link href="../css/master.css" rel="stylesheet" type="text/css">
</head>


  	<frameset rows="35,*" id="reportFrame" cols="*" framespacing="0" frameborder="0" border="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
		
						<%System.out.println("IS_ACTIVE======="+request.getAttribute("IS_ACTIVE")); %>		
				<frame src="../hisglobal/masterxml/masterworkshopjsp/reportButtonFrame.jsp" name="reportButtonFrame" scrolling="no"  frameborder="no" bordercolor=" " id="reportButtonFrame" title="reportButtonFrame" border="0"> 
				<frame src="../hisglobal/masterxml/masterworkshopjsp/mstHandlerReport.jsp?isActive=<%=request.getAttribute("IS_ACTIVE") %>" scrolling="yes" name="reportPrintFrame" frameborder="no" bordercolor=" " title="reportPrintFrame" id="reportPrintFrame"  border="5">
  	</frameset>
  				


</html>