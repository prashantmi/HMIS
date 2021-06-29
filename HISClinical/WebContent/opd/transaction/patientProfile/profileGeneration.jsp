<%--##		Modification Date		: 	14-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Modified By				:	AKASH SINGH
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

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%-- <his:css src="/hisglobal/css/Color.css"/>
 --%><his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<%
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	List lstProMenus = (List)session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
	if(proforma!=null)
	{
		List lstMenus = (List)proforma.getOptionsOrderList();
%>


<script type="text/javascript">
function generateProfile()
{	
	opener.document.getElementsByName('profileHTML')[0].value = document.getElementById('profileHtml').innerHTML;
	//opener.document.getElementsByName('hmode')[0].value = "GENRATEPROFILE";
	//opener.document.forms[0].hmode.value = "GENRATEPROFILE";
	//opener.submit();
	// Added by Vasu on 5.March.18 for base 64 conversion of HTML Data
	var htmlData = opener.document.getElementsByName('profileHTML')[0].value;
	//alert(htmlData);
	var enc = window.btoa(htmlData);
	//alert(enc);
	opener.document.getElementsByName('profileHTML')[0].value = enc;
	opener.submitForm('PROFILEGENERATION');
	
	window.close();
}
</script>

<div id="profileHtml">
<html>
<head>
	<title><%=proforma.patientTitle%></title>	
</head>
<body>
	<jsp:include page="/opd/transaction/patientProfile/profileHospitalHEADER.jsp" flush="true"></jsp:include>
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
			System.out.print("All jsp urls :"+filePath);
	%>
	
	<jsp:include page="<%=filePath%>" flush="true">
		<jsp:param name="DeskMenuID" value="<%=key%>"/>
	</jsp:include>
	<br>
<%
		}
	}
%>
</body>

</html>
</div>

<center>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) generateProfile();" onclick="generateProfile();" tabindex="1" />
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) window.close();" onclick="window.close();" tabindex="1" />
</center>
<%}catch(Exception e){e.printStackTrace();}%>