

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">
<script type="text/javascript">
function submitToclose()
{
	// alert("window")
	window.self.close();
}
</script>

<head>


</head>
<body>
<his:TitleTag key="progressNoteOnly" >
<table>
<logic:present name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
			<logic:notEmpty name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
			<logic:iterate indexId="i" id="docRoundVO" name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" type="hisglobal.vo.DoctorRoundDtlVO">
			
			<logic:equal parameter="progressNotesIndex" value="<%=i.toString()%>"> 
    	   		    		<tr>
								<td width="70%"  >
								<%=docRoundVO.getEntryDate()%>
								</td>
							</tr>
			</logic:equal>		
			</logic:iterate>
			</logic:notEmpty>
			</logic:present>	

	</table>
</his:TitleTag>

       			<logic:present name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
			<logic:notEmpty name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
			<logic:iterate indexId="i" id="docRoundVO" name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" type="hisglobal.vo.DoctorRoundDtlVO">
			
			<logic:equal parameter="progressNotesIndex" value="<%=i.toString()%>"> 
       		        <his:ContentTag>
						<table width="100%" cellpadding="0" cellspacing="1">
							<tr>
								<td width="100%">
									<div align="center">
										<html:textarea name="docRoundVO" property="progressNote" rows="6" cols="135" readonly="true"></html:textarea>
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>
			</logic:equal>		
			</logic:iterate>
			</logic:notEmpty>
			</logic:present>	
			<his:ButtonToolBarTag>
 				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToclose();" onkeypress="if(event.keyCode==13) submitToclose();">
			</his:ButtonToolBarTag>

</body>
</html:form>