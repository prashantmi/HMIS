<%--##		Creation Date			: 	16-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Created By				:	AKASH SINGH
--%>
<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.vo.DeskMenuMasterVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.List"%>


<%@page import="java.util.Iterator"%>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/opd/js/generic_patient_profile.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	List lstProMenus = (List)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_AUTO_MENU_LIST);
%>
<html>	
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=(proforma!=null)?proforma.patientTitle:""%></title>	


<script type="text/javascript">
function generateProfile(e)
{
	if(opener.document.getElementsByName('profileHTML')[0])
	{
		opener.document.getElementsByName('profileHTML')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
		//alert(opener.document.getElementsByName('profileHTML')[0].value);
		//opener.document.getElementsByName('hmode')[0].value = "GENRATEPROFILE";
		//opener.document.forms[0].hmode.value = "GENRATEPROFILE";
		//opener.submit();
		opener.submitForm('GENRATEAUTOMATICPROFILE');
		
		window.close();
	}
	else if(document.getElementsByName('hmode')[0].value != 'PRINTPROFILE')
	{
		printProfile(e);
	}
	else if(document.getElementsByName('hmode')[0].value == 'PRINTPROFILE')
	{
		var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=NONE";
		document.forms[0].action=url;
		document.forms[0].submit();
	}
}

function Cancel()
{
	window.close();
}

function submitToDesk(mode,hmode)
{
	//alert("A--"+" mode:- "+mode+" hmode:- "+hmode);
	if(goToDashBoard)	goToDashBoard();
		
	if(parent.gotToMainTab)
		parent.gotToMainTab("Patient Dashboard", true);
	else if(parent.parent.gotToMainTab)
		parent.parent.gotToMainTab("Patient Dashboard", false);
}
</script>
</head>
<body>
	<html:form action="/opdPatientProfile">
<%
	if(proforma!=null)
	{
		List lstMenus = (List)proforma.getOptionsOrderList();
%>

<div id="pdfPrintingHTMLData">
	<jsp:include page="/opd/transaction/patientProfile/profileHEADER.jsp" flush="true"></jsp:include>
	<br>
	<%
		for(int i=0;i<lstMenus.size();i++)
		{
			String key = (String)lstMenus.get(i);
			DeskMenuMasterVO vo =null;
			Iterator lstProMenusItr = lstProMenus.iterator();
			while(lstProMenusItr.hasNext())
			{
				DeskMenuMasterVO v = (DeskMenuMasterVO)lstProMenusItr.next();
				if(v.getDeskMenuId().equals(key))
				{
					vo=v;
					break;
				}
			}
			String url = vo.getDeskUrl();
			String filePath = "/opd/transaction/patientProfile/profile"+url+".jsp";
			if(url.equals("GENERICTEMPLATE"))
				filePath = "/opd/transaction/patientProfile/profileAUTOGENERICTEMPLATE.jsp";
			System.out.print("All jsp urls :"+filePath);
	%>
	
	<jsp:include page="<%=filePath%>" flush="true">
		<jsp:param name="DeskMenuID" value="<%=key%>"/>
	</jsp:include>
	<br>
<%
		}
	%>
</div>
<%
	}
%>
		<html:hidden property="profileHTML" name="GenericPatientProfileFB" />
		<html:hidden property="hmode" name="GenericPatientProfileFB" />
		<html:hidden property="patCrNo" name="GenericPatientProfileFB" />
	</html:form>

<center>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" onclick="submitToDesk('NEW','NEW');" tabindex="1" />
</center>
</body>


</html>

<%}catch(Exception e){e.printStackTrace();}%>