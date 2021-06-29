<!-- 
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE GLOBAL MASTER
 ## Purpose						        : 
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
 -->


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/labRequisition.js" />
<his:javascript src="/new_investigation/js/labTestSampleMaster.js" />


<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>


<script type="text/javascript">

function fun()
{
var testType = document.getElementsByName('testType')[0].value;
if(testType=="S"||testType=="I")
	{
	document.getElementById("cont").style.display="";
	document.getElementById("uom").style.display="";
	document.getElementById("sampQty").style.display="";
	document.getElementById("isDefaultSamp").style.display="";	
	}
	if(testType=="P")
	{
	document.getElementById("cont").style.display="none";
	document.getElementById("uom").style.display="none";
	document.getElementById("sampQty").style.display="none";
	document.getElementById("isDefaultSamp").style.display="none";	
	}
}
	function setContainerValuesAJAX()
{
		//alert("Local Lab Test Sample Master" );
		var flg = false;
		var strUOMCodeHashVOlume = null;
		var _mode = "AJX_G_UOM";
		var containerCode = document.getElementsByName("containerCode")[0].value;
		var urlNew= "/HISInvestigationG5/new_investigation/masters/LabTestSampleLocalMstACT.cnt?hmode="+_mode+'&containerCode='+containerCode;
		var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				
				strUOMCodeHashVOlume = data;
				flg = true;
			},
	        error: function(error)
	        {
	            alert(error+"Error while populating Information");
	            objUomList = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);		
		strUOMCodeHashVOlume=strUOMCodeHashVOlume==null?"-1#0":strUOMCodeHashVOlume;
		//alert(strUOMCodeHashVOlume)
		var uomCode=strUOMCodeHashVOlume.split("#")[0];
		var containerVolume=strUOMCodeHashVOlume.split("#")[1];
		document.getElementsByName("uomCode")[0].value=uomCode;
		document.getElementsByName("sampleQty")[0].value=containerVolume;
		//return objUomList;
}
	
function populatePage()

{
      var code = document.getElementsByName("sampleCode")[0].value;
      if (code != -1) {
            submitPage('POPULATE');
      } else {
            submitPage('ADD');
      }    
}
</script>
<body onload="fun();">
<html:form action="/masters/LabTestSampleLocalMstACT">
 
<his:TitleTag name="Lab Test Sample/System Local Master">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="2" cellpadding="1">
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LaboratoryName"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left">
				 <html:text name="LabTestSampleGlobalMstFB" property="labName" style="width:35%" readonly="true" tabindex="1" ></html:text>
				  </div>
			     </td>
			     </tr>
			     <tr>
			     <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left"> 
				       <html:text name="LabTestSampleGlobalMstFB" property="testName" style="width:35%" readonly="true" tabindex="1" />
				  </div>
			     </td>
			</tr>
			<tr>
			<td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						<logic:notEqual name="LabTestSampleGlobalMstFB" property="testType" value="P">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="SampleName"/>&nbsp;
						 </font>
						 </logic:notEqual>
						 <logic:equal name="LabTestSampleGlobalMstFB" property="testType" value="P">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="SampleAndSystemName"/>&nbsp;
						 </font>
						 </logic:equal>
				     </div>
			      </td>
			       <logic:equal name="LabTestSampleGlobalMstFB" property="hmode" value="ADD">
		         <td width="50%" class="tdfont">
			      <div align="left"> 
				 <html:select name="LabTestSampleGlobalMstFB" property="sampleCode" onchange="populatePage();" style="width:35%"  tabindex="1" >
				       					<html:option value="-1">Select Value</html:option>
				       					<logic:present name="<%= InvestigationConfig.LIST_SAMPLE_COMBO%>">
				 	   					<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO %>" property="value" labelProperty="label"/>
				 	   					 </logic:present>
				      				</html:select>
			      </div>
			     </td>
			     </logic:equal>
			     <logic:equal name="LabTestSampleGlobalMstFB" property="hmode" value="MODIFY">
			      <td width="50%" class="tdfont">
			      <div align="left"> 
			     <html:text name="LabTestSampleGlobalMstFB" property="sampleName" readonly="true" tabindex="1" style="width:35%" ></html:text>
			     </div>
			     </td>
			     </logic:equal>
			     </tr>
			     <tr id="cont" style="display:none">
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="containerName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left"> 
				 <html:select name="LabTestSampleGlobalMstFB" style="width:35%" property="containerCode" onchange="setContainerValuesAJAX();" tabindex="1" >
				       					<html:option value="-1">Select Value</html:option>
				       					<logic:present name="<%= InvestigationConfig.LIST_CONTAINER_COMBO%>">
				 	   					<html:options collection="<%=InvestigationConfig.LIST_CONTAINER_COMBO %>" property="value" labelProperty="label"/>
				 	   					 </logic:present>
				      				</html:select>			
				  </div>
			     </td>
			      </tr>
			      <tr id="uom" style="display:none">
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="UnitOfMeasurementName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			     
			      <div align="left"> 
				 <html:select name="LabTestSampleGlobalMstFB" property="uomCode" style="width:35%" tabindex="1" >
				       					<html:option value="-1">Select Value</html:option>
				       					<logic:present name="<%= InvestigationConfig.LIST_UOM_COMBO%>">
				 	   					<html:options collection="<%=InvestigationConfig.LIST_UOM_COMBO %>" property="value" labelProperty="label"/>
				 	   					</logic:present>
				      				</html:select>
				  </div>
			     </td>
			      </tr>
			      <tr id="sampQty" style="display:none">
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="sampleQuantity"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <html:text name="LabTestSampleGlobalMstFB" property="sampleQty"  maxlength="3" size="20" style="width:35%" onkeypress="return validateNumeric(event,this)" tabindex="1"/>
			     </td>
			      </tr>
			      <tr id="isDefaultSamp" style="display:none">
			      	    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isDefaultSample"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestSampleGlobalMstFB"  tabindex="1" property="isDefaultSample" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      
			      <html:radio name="LabTestSampleGlobalMstFB"  tabindex="1" property="isDefaultSample" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			     </tr>
			     <tr>
			     <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="remarks"/>&nbsp;
						 </font>
				     </div>
				     </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			       <html:text name="LabTestSampleGlobalMstFB" property="sampleCollRemarks"  maxlength="50" size="50" style="width:35%" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1"/>
			       </div>
			       </td>
			      </tr>   
			</table>
		
		
		</his:ContentTag>
	
		<his:ButtonToolBarTag>
		
				<span id="saveDiv">
			    <logic:notEqual name="LabTestSampleGlobalMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="LabTestSampleGlobalMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="LabTestSampleGlobalMstFB" property="hmode" value="MODIFY"> 
				   <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="modifyClear()" onkeypress="if(event.keyCode==13) modifyClear()" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_SAMPLE_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No Global Sample Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		<his:status/>
		<html:hidden name="LabTestSampleGlobalMstFB" property="hmode" />
        <html:hidden name="LabTestSampleGlobalMstFB" property="testCode" />
        <html:hidden name="LabTestSampleGlobalMstFB" property="labCode" />
        <html:hidden name="LabTestSampleGlobalMstFB" property="sampleCode" />
        <html:hidden name="LabTestSampleGlobalMstFB" property="testType" />
        <cmbPers:cmbPers />
        
		</html:form>
		
	</body>
	
</html>