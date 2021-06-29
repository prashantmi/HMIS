<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.URL" %>
<jsp:useBean id="inv" class="usermgmt.reports.errorPopupBean" scope="request"/>
<script>
function closepopup(){
                   self.close();
                    }

</script> 
<HTML>
	<HEAD>
		<TITLE>
			Welcome to errorPopup.jsp
		</TITLE>
		<link href="../../css/color.css" 	 rel="stylesheet" type="text/css">
		<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
		<link href="../../css/master.css" 	 rel="stylesheet" type="text/css">
	</HEAD>
	<BODY>
		
	<%
	String filePath = request.getParameter("fullpath");
	String osName = System.getProperties().getProperty("os.name");
	char sepChar = '\\';
	if(osName.startsWith("Win"))
		sepChar = '\\';
	else if(osName.startsWith("Lin"))
		sepChar = '/';
	String file1= filePath.replace('*',sepChar);
	System.out.println("filePath in auditlogpopup.jsp"+file1);
	
	byte[] b = new byte[1024];
		
		
		File f = new File(file1);
		FileInputStream istream = new FileInputStream(f);
		OutputStream os = response.getOutputStream();
		
		while(istream.read(b) != -1){
			
			os.write(b);
			
		}
		os.flush();
	
	%>
	
		
		<FORM METHOD='POST'>
		<table width="100%" align="center">
		<tr>
		<td align="right">
		  
		   <a style=cursor:hand><img src="src='../../images/Cancel.gif'" tabindex="1" class="addtoolbar" onClick="closepopup();" /></a>
		</td>
		</tr>	
			
		<tr><td class="header"></td></tr>	
		</table>
		</FORM>
	</BODY>
</HTML>
