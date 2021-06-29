<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="ehr.EHRConfig"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="ehr.treatmentdetail.vo.EHRSection_TreatmentVO"%>
<%@page import="emr.vo.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


      <title>TREATMENT_PRN</title>      
    
 </head>
<style>
fieldset.scheduler-border {
    border: 1px groove black !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0px 5px 10px;
        border-bottom:none;
        color:
    }
</style>


<body style="margin:0%">
<form>
<div class="">

 <%  // List <EHRSection_TreatmentVO> listdrugDtlVO = new ArrayList <EHRSection_TreatmentVO>();
 // List <EHRSection_TreatmentVO>  listdrugDtlVO =  (List <EHRSection_TreatmentVO>) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
// Map mpEssentials = (HashMap) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
//List<EHRSection_TreatmentVO> listdrugDtlVO = (List<EHRSection_TreatmentVO>) mpEssentials.get(EHRConfig.EHR_TREATMENT_SAVE);
//if(listdrugDtlVO == null || listdrugDtlVO.size() ==0)
// listdrugDtlVO = (List<EHRSection_TreatmentVO>) mpEssentials.get(EHRConfig.EHR_TREATMENT_ESSENTIAL_SAVE);
 
	EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
	List<EHRSection_TreatmentVO> listdrugDtlVO= patencountervo.getListSaveAllTreatment();
				
			

 %>
<div id="treatmentprint">	
	<div class="row">
		<div class="col-xs-12">
		<%	  
				if(listdrugDtlVO!=null && listdrugDtlVO.size() !=0)
				{
			 %>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b> TREATMENT </b></font>
			
			<table width="100%" cellpadding="10"><tr>
			<td width="2%"></td>
			<td width="45%" ><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Medicine</b></font></td>
			<td width="5%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Dose</b></font></td>
			<td width="10%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Frequency</b></font></td>
			<td width="5%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Days</b></font></td>
			<td width="35%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Instructions</b></font></td>
			</tr></table>
		</div>
	</div>

	<%-- 	<logic:iterate id="drugDtlVO" indexId="idx" name="<%=EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO%>" type="ehr.treatmentdetail.vo.EHRSection_TreatmentVO">
			
	 --%>	
	
		
		<% if(listdrugDtlVO!=null && listdrugDtlVO.size() !=0)
		{
			int count=1;
		for(Iterator<EHRSection_TreatmentVO> i = listdrugDtlVO.iterator(); i.hasNext(); )
		{
		EHRSection_TreatmentVO drugDtlVO=i.next(); %>
	 	<div class="row">
		<div class="col-sm-12">
			<table  width="100%">
			
			<tr>
			 <td width="2%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=count%>.</font></td> 
			<td width="45%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=drugDtlVO.getDrugName()==null ? "" :drugDtlVO.getDrugName()%><%if(drugDtlVO.getDrugAdminName()!=null) {%> (<%=drugDtlVO.getDrugAdminName()%>)<%} %></font></td>
			<td width="5%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=drugDtlVO.getDoseName()==null ? "" :drugDtlVO.getDoseName()%></font></td>
			<td width="10%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=drugDtlVO.getFrequencyName()==null ? "" :drugDtlVO.getFrequencyName()%></font></td>
			<td width="5%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=drugDtlVO.getDays()==null ? "" :drugDtlVO.getDays()%></font></td>
			<td width="35%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=drugDtlVO.getRemarks()==null ? "" :drugDtlVO.getRemarks()%></font></td>
			</tr> </table>
		</div>
		
	</div> 
	<% count++;}}
		else{
			%>	
	-
	<%}	%>
			
	<%-- <div class="row">
		<div class="col-sm-12">
		<b><%=idx+1%>. </b>Drug Name : (<%=drugDtlVO.getDrugName()%>),
			Dosage : (<%=drugDtlVO.getDoseName() %>),
			Frequency : (<%=drugDtlVO.getFrequencyName() %>),
			Days : (<%=drugDtlVO.getDays() %>),
			Start Date :(<%=drugDtlVO.getStartDate() %>),
			Instructions :(<%=drugDtlVO.getRemarks() %>) ,
		</div>
		
	</div> --%>
	<%-- </logic:iterate> --%>
	
	<%}%>
</div>
<script>

</script>
</div>
</form>
</body>

</html>