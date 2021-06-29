<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="ehr.EHRConfig"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="ehr.diagnosis.vo.EHRSection_DiagnosisVO"%>
<%@page import="ehr.diagnosis.presentation.EHRSection_DiagnosisFB"%>
<%@page import="opd.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet">
<his:javascript src="/registration/js/registration.js" />
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>

<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">

<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />  
<his:javascript src="/ehr/js/EHR_spp_diagnosis.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/map.js"></script>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

</head>
 
<script type="text/javascript">

$(document).ready(function(){ 
	//alert("1");	
	document.getElementsByName("diagonisticTypeCode")[0].value = "14";
	 document.getElementById("snorad").checked = true;
	ShowOtherSnomedDiv('1');
	diagnosisOnload();
	
});



</script>
 <style>
 
 .ui-autocomplete {
       z-index:2147483647;
     } 
     

  </style>
  
<script>

function validate_ENC_DIAGNOSIS()
{
//alert("validate diagnosis");
   var diagnosisName = document.getElementsByName("txt-snomed-ct-search_1")[0].value;	 
   if(diagnosisName!="")
	  {
       alert("Please click add button to add Diagnosis");
       document.getElementById("addButtonDiagnosis").focus();
       return false;
	  }
else
	 {
		
    return true;
 }

    
 
}

function snomedtoICDMapping(conceptID)
{
	var flg="NOTFOUND";
	//alert(conceptID);
	var elemicdCombo = document.getElementById("selMappedICD");
	//alert(elemSiteCombo);
	elemicdCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select Value";
	elemicdCombo.appendChild(op);

	 $.ajax({
		 
			url : "/AHIMSG5/snomedct/csnoserv/api/map/icdmap?id="+conceptID ,
			type : 'GET' ,
			
			datatype : "json" ,
			 async : true ,
			  success : function(data) {
				var obj =  jQuery.parseJSON(data); 
			//	alert(obj);
		   		if(obj.mapStatus == 'MAP_FOUND' || obj.mapStatus== 'INDETERMINATE_MAP')
	 			  { 
		     	   	document.getElementById("divMappedICD").style.display="none";
					document.getElementById("divMappedICD").style.display="";
					flg="FOUND";
		     	   // alert(obj.mapGroup.length);
		   			for(var i=0;i<obj.mapGroup.length;i++)
					{
		     		  	op=document.createElement("option");
	 					op.value=obj.mapGroup[i].mappedICDCode;
	 					op.innerHTML=obj.mapGroup[i].mappedICDCode;
	 					/**Modified by Vasu on 02.April.2019 */
	 					/* elemicdCombo.appendChild(op); */
	 					$("#selMappedICD").html('<option value="' + op.innerHTML + '">' + op.innerHTML + '</option>');	
	 					document.getElementsByName('snomedCTICDCode')[0].value = op.innerHTML;
	 					//alert(prefTermArray);
	 					}
	 				}
					else
						{
						
						document.getElementById("divMappedICD").style.display="";
						document.getElementById("divMappedICD").style.display="none";
						} 
	 			
	 		},

error: function(data)
{
    alert('request failed :');
}

});

if(flg=="FOUND")
	setDefaultICDCode();

//findingSiteForDisease(conceptID);
}






function mapICD(icdCode,icdName)
{
	//alert("hello");
	var elemDiv = document.getElementById("divMappedICD"); 
	var htmlCode = "<table id='tblmapICD#"+icdCode+"'cellpadding='0' cellspacing='1' align='right' width='100%'><tr><td><td></<tr><tr><td width='100%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>"+icdCode+"</b></font></td></tr></table>";
	elemDiv.innerHTML = htmlCode;
	//alert(htmlCode);
}

function setSnomedICDName()
{
	var obj=document.getElementById("selMappedICD");
//	alert(obj.options[obj.selectedIndex].text);
	//alert(obj.options[obj.selectedIndex].value);
	//document.getElementsByName("selSnomedCTICDName")[0].value=obj.options[obj.selectedIndex].text;
	document.getElementsByName("snomedCTICDCode")[0].value=obj.options[obj.selectedIndex].value;

}


function  setDefaultICDCode()
{
	var elemSiteCombo = document.getElementById("selMappedICD");
	elemSiteCombo.selectedIndex=1;
	setSnomedICDName();
    
    
}







</script>

<body>


	<html:hidden name="EHRSection_DiagnosisFB" property="icdNHospitalFlagValue"/>
<%--	 <html:hidden name="EHRSection_DiagnosisFB" property="icdNHospitalFlagValue" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>"/> --%>
	<table class="table table-condensed table-boderless">
	
<tr>
	<td style="font-size:1.2em;font-weight:bold;"width="100%" >
Diagnosis&nbsp;: &nbsp;&nbsp;&nbsp;
<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 



					 <html:radio name="EHRSection_DiagnosisFB" property="icdNHospitalFlagValue" value="1" onclick="ShowOtherSnomedDiv('1');" styleId="snorad" ></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									SNOMED-CT
								</b>							</font>
							
							
							<html:radio name="EHRSection_DiagnosisFB" property="icdNHospitalFlagValue" value="2" onclick="ShowOtherSnomedDiv('2');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									Other
								</b>
							</font> 
							 </td>
</tr>
</table>
	

	<div align="center" id="somectIddata" style="display:none">
	</div>				

		

		<table id="diagnosisTable" class="table table-boderless table-condensed ">
			<tr style="background: linear-gradient(to bottom, #74a9d8 , white);">
				<td class="col-lg-3 col-xl-3 col-md-3 col-sm-3">
					<div align="center">
						
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="disease" />
							</b>
						
					</div>
				</td >
				 <td class="col-lg-2 col-xl-2 col-md-2 col-sm-2">
					<div align="center">
						
							<b>
								<font color="#FF0000">*</font>
								<%-- <bean:message key="icdCode" /> --%>
								ICD-10
							</b>
						
					</div>
				</td> 
				<td class="col-lg-2 col-xl-2 col-md-2 col-sm-2">
					<div align="center">
						
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="diagonosisType" />
							</b>
				
					</div>
				</td>
			
				<td class="col-lg-4 col-xl-4 col-md-4 col-sm-4">
					<div align="center">
						
							<b> <bean:message key="remark" /></b>
				
					</div>
				</td>
				<td class="col-lg-1 col-xl-1 col-md-1 col-sm-1"></td>
			</tr>
			
			<tr >
				 <td  >
				 	<html:hidden name="EHRSection_DiagnosisFB" property="diagnosisRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED %>" ></html:hidden>
				 	<div align="center" id="divsnomedCTDiagnosisName">
						<html:hidden name="EHRSection_DiagnosisFB" property="snomedCTDiagnosisName" value="" ></html:hidden>
					</div>
					<div align="center" id="divsnomedCTDiagnosisCode">
						<html:hidden name="EHRSection_DiagnosisFB" property="snomedCTDiagnosisCode" value="" ></html:hidden>
					</div>
					<div align="center"  >
					    <div id="dialog-form_1" >
							<div id="snomed-ct-search" >
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<input id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input form-control" name="txt-snomed-ct-search_1" autocomplete="off" placeholder="Enter 3 characters to search..." onfocus="load1('1','DISORDER');"    style="width:95%;color:#000000;" type="text">
							</div>
                         </div>
                         </div>
                         
                         <div align="center" id="otherDiagDiv" style="display: none;">
							<input id="txt-other" name="txt-other"  type="text" value="" class="form-control">
                        </div> 
                         
                          
                         
                         
					
				</td>
				<html:hidden name="EHRSection_DiagnosisFB" property="snomedCTICDCode" value="" ></html:hidden>
				<td>
					<div align="center" id="divMappedICD">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<html:select name="EHRSection_DiagnosisFB" tabindex="1" styleId="selMappedICD"  property="selSnomedCTICDCode" onchange="setSnomedICDName()" styleClass="form-control">
						<html:option value="-1">Select Value</html:option>
						</html:select>
							</b>
						</font>
					</div>
					
					<div align="center" id="divMappedOtherICD" style="display: none;">
						<input id="txt-other" name="txt-other-icd"  type="text" value="" class="form-control">
					</div>
					
				</td> 
				<td >
					<%
						EHRSection_DiagnosisFB dd = (EHRSection_DiagnosisFB) pageContext.findAttribute("EHRSection_DiagnosisFB");
						System.out.println("fb============" + dd);
						System.out.println("getDiagonisticTypeCode=================" + dd.getDiagonisticTypeCode());
					%>
					<div align="center" id="divdiagnostictype">
						<html:hidden name="EHRSection_DiagnosisFB" property="diagnosticTypeName" value="Provisional"></html:hidden>
						<html:select name="EHRSection_DiagnosisFB" tabindex="1" styleId="diagnostictypeId"  property="diagonisticTypeCode" onchange="setDiagnosticTypeName()" styleClass="form-control" >
							<logic:present name="<%=RegistrationConfig.DIAGNOSIS_LIST%>" >
							<html:options collection="<%=RegistrationConfig.DIAGNOSIS_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
						
						
					</div>
				</td>
			
				<td  style="vertical-align: middle;">	
					<html:hidden name="EHRSection_DiagnosisFB" property="snomedPTRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_DiagnosisFB" property="snomedCIdRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_DiagnosisFB" property="remarks" value="" ></html:hidden>
					
					<div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="clearable ui-autocomplete-input x onX pull-right form-control" id="txt-snomed-ct-search_4" name="txt-snomed-ct-search_4" maxlength="2000" onkeypress="return validateText(event)" onchange="setPresentHistory();"onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);" style="width:80%;height:40px;" autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#txt-snomed-ct-search_4').val('');" class="btn btn-sm btn-default BtnCleanDiagnosis"><i class="fa fa-times"></i></button>
							</div>
                        </div>
                        
					<!-- <textarea name="txt-snomed-ct-search_4" rows="1"  id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX pull-right form-control" value="" onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);" style="width:80%;" autocomplete="off"></textarea> -->
				</td>
				<td >
					<div align="center">
					 <button type="button"  id="addButtonDiagnosis"  class="btn btn-info btn-sm" onkeypress="if(event.keyCode==13) submitDiagnosisOnValidate();" onclick="submitDiagnosisOnValidate(); ">Add</button>
											</div>
				</td>
			</tr>
			
			
			
		
			<%
			EHRSection_DiagnosisVO[] latestDiag = (EHRSection_DiagnosisVO[]) session.getAttribute(OpdConfig.Latest_DIAGNOSIS_VO);
			//System.out.print(latestDiag.length);
				if(latestDiag!=null && latestDiag.length !=0)
				{
			%>
			<logic:iterate id="prevDiagVO" indexId="idx" name="<%=OpdConfig.Latest_DIAGNOSIS_VO%>" type="ehr.diagnosis.vo.EHRSection_DiagnosisVO">
					
				<tr id="diagnosisRow#<%=idx.intValue()+2%>" >
				<td >
						<div align="center">
							<input name='snomedCTDiagnosisCode' value='<%=(prevDiagVO.getDiagnosticCode()==null)?"":prevDiagVO.getDiagnosticCode()%>' type='hidden'></input>
							<input name='snomedCTDiagnosisName' value='<%=(prevDiagVO.getDignosisName()==null)?"":prevDiagVO.getDignosisName()%>' type='hidden'></input>
							<html:hidden name="EHRSection_DiagnosisFB" property="diagnosisRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED%>" ></html:hidden>
							<%=(prevDiagVO.getDignosisName()==null)?"":prevDiagVO.getDignosisName()%>
						</div>
					</td>
										
					 <td >
						<div align="center">
							<input name='snomedCTICDCode' value='<%=(prevDiagVO.getSnomedCTICDCode()==null)?"":prevDiagVO.getSnomedCTICDCode()%>' type='hidden'></input>
							<%-- <html:hidden name="EHRSection_DiagnosisFB" property="snomedCTICDCode" value="<%=prevDiagVO.getSnomedCTICDCode() %>" ></html:hidden> --%>
							<%=(prevDiagVO.getSnomedCTICDCode()==null)?"":prevDiagVO.getSnomedCTICDCode()%>
						</div>
					</td> 
					
					<td >
						<div align="center">
							<input name='diagonisticTypeCode' value='<%=(prevDiagVO.getDiagnosticTypeCode()==null)?"":prevDiagVO.getDiagnosticTypeCode()%>' type='hidden'></input>
							<html:hidden name="EHRSection_DiagnosisFB" property="diagnosticTypeName" value="<%=prevDiagVO.getDiagnosticTypeName()%>" ></html:hidden>
							
							<%=(prevDiagVO.getDiagnosticTypeName()==null)?"":prevDiagVO.getDiagnosticTypeName()%>
						</div>
					</td> 

					

					<td >
						<div align="center">
							<input name='remarks' value='<%=(prevDiagVO.getRemarks()==null)?"":prevDiagVO.getRemarks() %>' type='hidden'></input>
							<%=(prevDiagVO.getRemarks()==null)?"":prevDiagVO.getRemarks() %>
						</div>
					</td>
					<td >
						<div align="center">
						 <button type="button"  id="revokeButton"  class="btn btn-info btn-sm"  onclick="hideDiagRow('<%=idx.toString()%>')">Delete</button>
					
							<%-- <img class="button" id="revokeButton" style="cursor:pointer" src="/HIS/hisglobal/images/avai/minus.gif" title="Cancel Diagnosis"  onclick="hideDiagRow('<%=idx.toString()%>')">
					 --%>	</div>
					</td>	
				</tr>
			</logic:iterate>
			<%
				}
			%>
			
		</table>

</body>

<html:hidden name="EHRSection_DiagnosisFB" property="hmode" />
<html:hidden name="EHRSection_DiagnosisFB" property="patCrNo" />
<html:hidden name="EHRSection_DiagnosisFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_DiagnosisFB" property="episodeCode" />
<html:hidden name="EHRSection_DiagnosisFB" property="numberOfRow"/> <%-- <%=Integer.toString(count)%>" />  --%>
<html:hidden name="EHRSection_DiagnosisFB" property="unitDiagnosisCodeType" />
<html:hidden name="EHRSection_DiagnosisFB" property="diagnosisCodeType" />
<html:hidden name="EHRSection_DiagnosisFB" property="comboOptionString" />
<html:hidden name="EHRSection_DiagnosisFB" property="episodeVisitNo" />
<html:hidden name="EHRSection_DiagnosisFB" property="comboDiagnosisSite" />
<html:hidden name="EHRSection_DiagnosisFB" property="isSetDIGANOSIS"/>

<input type="hidden" name="targetId" />



<script type="text/javascript">
DIAGSetDataExist();
</script>

</html>