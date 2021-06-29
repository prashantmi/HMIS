<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="ehr.treatmentdetail.dataentry.EHRSection_TreatmentFB"%>
<%@page import="ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenFB" %>
<%@page import ="ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO" %>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="java.util.List"%>
<%@page import="ehr.treatmentdetail.vo.EHRSection_TreatmentVO"%>
<his:javascript src="/ehr/js/EHR_spp_treatment.js" />
<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />
<his:javascript src="/ehr/js/EHR_spp_treatmentgiven.js" />   
<his:javascript src="/hisglobal/js/wz_tooltip.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>

<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

    
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js" type="text/javascript"></script>
<script type="text/javascript" src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css"/> -->
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
 var j = jQuery.noConflict();

 j(document).ready(function(){

              j('#datetimepicker1').datetimepicker();

 })});
</script>
<STYLE TYPE="text/css">
	.highlight {
		line-height: 16px;
		font-weight: bold;
		font-family: arial;
		font-size: 11px;
		padding-top: 1px;
		vertical-align: middle;
		padding-left: 6px;
   		/*background:#FFEBD5;*/
   		background:#96BAEA;
   		   		
   		border-style:solid;
		border-width:1px;
		
	
   		}
</STYLE>
<STYLE TYPE="text/css">
	.dehighlite {
	font-family: verdana, arial, sans-serif;
    font-size:12px;
    text-decoration: none;
    color: black;
   /* background-color: #FFEBD5;*/
    background-color: #E0EBEB;
    white-space: nowrap;
    background:#E0EBEB;
    border-style:solid;
	border-width:1px;
   		}
</STYLE>

 <%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
 <html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentDate" value="<%=sysDate%>"/>

 <his:statusTransactionInProcess> 
		
 		<%-- <html:hidden name="EHRSection_TreatmentFB" property="drugDetailRows" /> --%>
		<table id="treatmentGivenTable" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<!-- <td width="5%"  class="tdfonthead">
					<div align="center">
					<input type ="checkbox" name = "selectChk" checked>	
 				</div>
				</td> -->
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>Type</b>
						</font>
					</div>
				</td>
				<td width="27%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <b><bean:message key="diagnosisName"/></b> --%>
							<b><font color="#FF0000">*</font>Treatment</b>
						</font>
					</div>
				</td>
				<td width="27%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <b><bean:message key="diagonosisType"/></b> --%>
							<b>Given On</b>
						</font>
					</div>
				</td>
				<td width="22%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>
						</font>
					</div>
				</td>
				<td width="4%" class="tdfonthead">
				   <div align="center">
				   </div>
				</td>
			</tr>
			<%-- <logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>">
			  <logic:iterate id="treatmentGivenDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST%>" type="ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO">   --%>
			<tr id = treatmentRow#>
			   <!-- <td width="5%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center">
			      </div>
			   </td> -->
			   <td width="20%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center" id="divTreatmentType">
			      	<html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentGivenRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED %>" ></html:hidden>
			        <%--  <html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentType" value=""></html:hidden> --%>
			      </div>
			      <div align="center">
			         <html:select  name="EHRSection_TreatmentGivenFB" property="treatmentType" value=""> <%-- value="<%=treatmentGivenDtlVO.getTreatmentType()%>" --%>
                        <html:option value="">Select Value</html:option>
                        <html:option value="Drug">Drug</html:option>
                        <html:option value="Procedure">Procedure</html:option>
                        <html:option value="Intake/Outtake">Intake/Outtake</html:option>
                     </html:select>
			      </div>
			   </td>
			   <td width="27%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center" id="divsnomedCTTreatmentName">
						<html:hidden name="EHRSection_TreatmentGivenFB" property="snomedCTTreatmentName"></html:hidden>
					</div>
					<div align="center" id="divsnomedCTTreatmentCode">
						<html:hidden name="EHRSection_TreatmentGivenFB" property="snomedCTTreatmentCode"></html:hidden>
					</div>
			      <div align="center" >
					    <div id="dialog-form_1" >
							<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<input id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1" autocomplete="off" placeholder="Enter 3 characters to search..." onfocus="load1('1','DISORDER');"  style="width:95%;color:#000000;" type="text">
							</div>
                         </div>
					</div>
			   </td>
			   <td width="27%" class="tdfonthead" style="vertical-align: middle;">
			      <%-- <div align="center" id="divTreatmentDate">
			         <html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentDate" value="" ></html:hidden>
			      </div> --%>
			      <div class="form-group" align="center">
			    <!--  <div class='input-group date' id='datetimepicker1'> -->
			       <input type='text' class="form-control" id="datetimepicker1"/>
			         <!--  <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span> -->
			       <!-- <input type="text" name="treatmentDate" id="treatmentDateId"> -->
			        <%-- <html:text name="EHRSection_TreatmentGivenFB" property="treatmentDate" value="<%=sysDate%>"></html:text>  --%>
			     </div>
			     </div>
			   </td>
			   <td width="22%" class="tdfonthead" style="vertical-align: middle;">
			        <html:hidden name="EHRSection_TreatmentGivenFB" property="snomedPTRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_TreatmentGivenFB" property="snomedCIdRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_TreatmentGivenFB" property="remarks" value="" ></html:hidden>
			      <div align="center">
			          <textarea name="txt-snomed-ct-search_4" rows="1"  id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX pull-right" value="" onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);" style="width:80%;" autocomplete="off"></textarea> 
			      </div>
			   </td>
			   <td width="4%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center">
			         <img class="button"  id="addButton" style="cursor: pointer;" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' tabindex='1' alt="Add givenTreatment" title="Add givenTreatment" onkeypress="if(event.keyCode==13) submitgivenTreatmentOnValidate();" onclick="submitgivenTreatmentOnValidate(); ">
			      </div>
			   </td>
			</tr>
 		 	<%--  </logic:iterate>
 		 	</logic:notEmpty>	 --%> 
 		<!-- </table> -->
 		<%-- <table>
 		   <logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>">
 		         <logic:iterate id="treatmentGivenVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>" type="ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO">
 		              <tr id ="treatmentRow">
 		                  <td width="5%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center">
			      </div>
			   </td>
			   <td width="15%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center" id="divTreatmentType">
			         <html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentType" value=""></html:hidden>
			      </div>
			      <div align="center">
                     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						 <b>
						     <html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentType" value="<%=treatmentGivenVO.getTreatmentType() %>"/>
						    <bean:write name="treatmentGivenVO" property="treatmentType"/>
						</b>
					</font>
			      </div>
			   </td>
			   <td width="27%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center" id="divsnomedCTTreatmentName">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						 <b>
						     <html:hidden name="EHRSection_TreatmentGivenFB" property="snomedCTTreatmentName" value="<%=treatmentGivenVO.getSnomedCTTreatmentName() %>"/>
						    <bean:write name="treatmentGivenVO" property="snomedCTTreatmentName"/>
						</b>
					</font>
					</div>
			   </td>
			   <td width="27%" class="tdfonthead" style="vertical-align: middle;">
			     <div align="center">
			       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						 <b>
						     <html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentDate" value="<%=treatmentGivenVO.getTreatmentDate() %>"/>
						    <bean:write name="treatmentGivenVO" property="treatmentDate"/>
						</b>
					</font>
			     </div>
			   </td>
			   <td width="22%" class="tdfonthead" style="vertical-align: middle;">
			        <html:hidden name="EHRSection_TreatmentGivenFB" property="snomedPTRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_TreatmentGivenFB" property="snomedCIdRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_TreatmentGivenFB" property="remarks" value="" ></html:hidden>
			      <div align="center">
			          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						 <b>
						     <html:hidden name="EHRSection_TreatmentGivenFB" property="remarks" value="<%=treatmentGivenVO.getRemarks() %>"/>
						    <bean:write name="treatmentGivenVO" property="remarks"/>
						</b>
					</font>
			      </div>
			   </td>
			   <td width="4%" class="tdfonthead" style="vertical-align: middle;">
			      <div align="center">
			         <img class="button"  id="addButton" style="cursor: pointer;" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' tabindex='1' alt="Add givenTreatment" title="Add givenTreatment" onkeypress="if(event.keyCode==13) submitgivenTreatmentOnValidate();" onclick="submitgivenTreatmentOnValidate(); ">
			      </div>
			   </td>
 		              </tr>
 		         </logic:iterate>
 		   </logic:notEmpty>
 		</table> --%>
 		
 		          <%-- <%
			EHRSection_TreatmentGivenVO[] latestTreatmentGiven = (EHRSection_TreatmentGivenVO[]) session.getAttribute(EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS);
			System.out.print(latestTreatmentGiven.length);
				if(latestTreatmentGiven!=null && latestTreatmentGiven.length !=0)
				{
			%> --%>
			  <%-- <logic:iterate id="prevTreatmentGivenVO" indexId="idx" name="<%=EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS%>" type="ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO">
			    <tr id="treatmentGivenRow#<%=idx.intValue()+2%>" >
			      <td class="tdfont">
						<div align="center">
							<input name='treatmentType' value='<%=(prevTreatmentGivenVO.getTreatmentType()==null)?"":prevTreatmentGivenVO.getTreatmentType()%>' type='hidden'></input>
							<html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentGivenRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED%>" ></html:hidden>
							<html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentType" value="<%=prevTreatmentGivenVO.getTreatmentType()%>" ></html:hidden>
							<%=(prevTreatmentGivenVO.getTreatmentType()==null)?"":prevTreatmentGivenVO.getTreatmentType()%>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<input name='snomedCTTreatmentName' value='<%=(prevTreatmentGivenVO.getSnomedCTTreatmentName()==null)?"":prevTreatmentGivenVO.getSnomedCTTreatmentName()%>' type='hidden'></input>
							<html:hidden name="EHRSection_TreatmentGivenFB" property="snomedCTTreatmentName" value="<%=prevTreatmentGivenVO.getSnomedCTTreatmentName()%>" ></html:hidden>
							<%=(prevTreatmentGivenVO.getSnomedCTTreatmentName()==null)?"":prevTreatmentGivenVO.getSnomedCTTreatmentName()%>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<input name='treatmentDate' value='<%=(prevTreatmentGivenVO.getTreatmentDate()==null)?"":prevTreatmentGivenVO.getTreatmentDate()%>' type='hidden'></input>
							<html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentDate" value="<%=prevTreatmentGivenVO.getTreatmentDate()%>" ></html:hidden>
							<%=(prevTreatmentGivenVO.getTreatmentDate()==null)?"":prevTreatmentGivenVO.getTreatmentDate()%>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<input name='remarks' value='<%=(prevTreatmentGivenVO.getRemarks()==null)?"":prevTreatmentGivenVO.getRemarks()%>' type='hidden'></input>
							<html:hidden name="EHRSection_TreatmentGivenFB" property="remarks" value="<%=prevTreatmentGivenVO.getRemarks()%>" ></html:hidden>
							<%=(prevTreatmentGivenVO.getRemarks()==null)?"":prevTreatmentGivenVO.getRemarks()%>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<img class="button" id="revokeButton" style="cursor:pointer" src="/HIS/hisglobal/images/avai/minus.gif" title="Cancel Treatment"  onclick="hideTreatmentGivenRow('<%=idx.toString()%>')">
						</div>
					</td>
				</tr>	 
			  </logic:iterate> --%>
			 <%--  <%
				}
			%>  --%>
 		</table>
</his:statusTransactionInProcess>




