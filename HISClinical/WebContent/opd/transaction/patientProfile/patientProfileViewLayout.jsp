<%--##		Modification Date		: 	16-01-2015
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
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 --%>
<%@page import="hisglobal.vo.DeskMenuMasterVO"%>
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

<%-- <his:css src="/hisglobal/css/Color.css"/> --%>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%@page import="opd.OpdConfig"%>

<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.List"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function modifyOption(menuId, url)
{
	opener.document.getElementsByName('selectedMenuId')[0].value=menuId;
	opener.submitForm21(url);	
	window.close();
}

function moveOptionUp(menuId)
{
	document.getElementsByName('selectedMenuId')[0].value=menuId;
	submitForm('OPTIONUP');
}

function moveOptionDown(menuId)
{
	document.getElementsByName('selectedMenuId')[0].value=menuId;
	submitForm('OPTIONDOWN');
}

function removeOption(menuId)
{
	document.getElementsByName('selectedMenuId')[0].value=menuId;
	submitForm21('REMOVEOPTION');
}

function callThisOnload()
{
	if(document.getElementsByName('hmode')[0].value=="REMOVEOPTION")
		opener.submitForm('PROFILEOPTIONS');
}
</script>
<html:form action="/opdPatientProfile">

<%
	GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
	List lstProMenus = (List)session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
	if(proforma!=null)
	{
		//Map<String, StringBuilder> map = proforma.getAddedOptionsMap();
		List lstMenus = proforma.getOptionsOrderList();
%>
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
	%>
<center>	
	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) modifyOption('<%=key%>','<%=url%>');" onclick="modifyOption('<%=key%>','<%=url%>');" tabindex="1" />
	&nbsp;&nbsp;		
	<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) removeOption('<%=key%>');" onclick="removeOption('<%=key%>');" tabindex="1" /> 
	<%--<% if(i!=0) { %>
	&nbsp;&nbsp;		
	<img class="button" src='<his:path src="/hisglobal/images/arrdouble-up.png"/>' tabindex="1" style="cursor: pointer" onclick="moveOptionUp('<%=key%>')" onkeypress="if(event.keyCode==13) moveOptionUp('<%=key%>')">
	<% } %>
	<% if(i<lstMenus.size()-1) { %>
	&nbsp;&nbsp;
	<img class="button" src='<his:path src="/hisglobal/images/arrdouble-down.png"/>' tabindex="1" style="cursor: pointer" onclick="moveOptionDown('<%=key%>')" onkeypress="if(event.keyCode==13) moveOptionDown('<%=key%>')">
	<% } %>--%>
</center>
<%
	String filePath = "/opd/transaction/patientProfile/profile"+url+".jsp";
System.out.println("makkkk"+filePath);
%>

<jsp:include page="<%=filePath%>" flush="true">
	<jsp:param name="DeskMenuID" value="<%=key%>"/>
</jsp:include>
<br>
	<%			
		}
	%>
<%
	}
%>
<center>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) window.close();" onclick="window.close();" tabindex="1" />
</center>

<html:hidden name="GenericPatientProfileFB" property="hmode" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenuId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="reqDnoList" />
<html:hidden name="GenericPatientProfileFB" property="strHiddenPatDtl" />
<html:hidden name="GenericPatientProfileFB" property="requisitionDNo"/>

</html:form>
<%}catch(Exception e) {e.printStackTrace();} %>
