<!-- /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Canned Master
 ## Purpose								:	This master is used to define labs for investigation process
 ## Date of Creation					:   05-March-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/labMstAddMod.js" />
<his:javascript src="/new_investigation/js/labRequisition.js" />


<body>


<script type="text/javascript">

  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].LaboratoryName,"laboratoryName") )
     {
         valid=true ;
     }
  return valid;
}
  
  
  
  
  function setDay(obj)
  {
  
   // var workingDays="0000000";
	  var workingDays= document.getElementsByName('labWorkingDays')[0].value;
 	if(obj.checked)
 		{
 		
 		if(obj.name=="chkMon")
		{
 			//alert("check Mon");	
 			
		workingDays='1'+workingDays.substring(1, 7); //setting first value in the sting as 1
		//alert(workingDays);
		}
 		else  if(obj.name=="chkTue")
		
 		{
 			//alert("check Tue");
		workingDays=workingDays.substring(0, 1)+'1'+workingDays.substring(2, 7);
		//	alert(workingDays);
		 
 		}
 		else if(obj.name=="chkWed")
		
 		{
 			//alert("check Wed");
 			
		workingDays=workingDays.substring(0, 2)+'1'+workingDays.substring(3, 7);
		//alert(workingDays);
		 
		}
		
		else if(obj.name=="chkThu")
		
 		{
			//alert("check Thu");
		workingDays=workingDays.substring(0, 3)+'1'+workingDays.substring(4, 7);
		//	alert(workingDays);
		 
		}
		else if(obj.name=="chkFri")
		
 		{
			//alert("check Fri");
		workingDays=workingDays.substring(0, 4)+'1'+workingDays.substring(5, 7);
		//	alert(workingDays);
		 
 		}
		else if(obj.name=="chkSat")
		
 		{
			//alert("check Sat");
		workingDays=workingDays.substring(0, 5)+'1'+workingDays.substring(6, 7);
		//alert(workingDays);
		 
 		}
		else if(obj.name=="chkSun")
		
 		{
			//alert("check Sun");
		workingDays=workingDays.substring(0, 6)+'1';
		//	alert(workingDays);
		 
 		}
		
 		 
 		}
	else
	{
 		if(obj.name=="chkMon")
		{
 			//alert("uncheck Mon");
		workingDays='0'+workingDays.substring(1, 7);
		//	alert(workingDays);
		 
		}
		  if(obj.name=="chkTue")
		
 		{
			  //alert("uncheck Tue");
		workingDays=workingDays.substring(0, 1)+'0'+workingDays.substring(2, 7);
		//	alert(workingDays);
		 
		}
 		else if(obj.name=="chkWed")
		
 		{
 			//alert("uncheck Wed");
		workingDays=workingDays.substring(0, 2)+'0'+workingDays.substring(3, 7);
		//alert(workingDays);
 		}
		
		else if(obj.name=="chkThu")
		
 		{
			//alert("uncheck Thu");
		workingDays=workingDays.substring(0, 3)+'0'+workingDays.substring(4, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkFri")
		
 		{
			//alert("uncheck Fri");
		workingDays=workingDays.substring(0, 4)+'0'+workingDays.substring(5, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkSat")
		
 		{
			//alert("uncheck Sat");
		workingDays=workingDays.substring(0, 5)+'0'+workingDays.substring(6, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkSun")
		
 		{
			//alert("uncheck Sun");
		workingDays=workingDays.substring(0, 6)+'0';
		//alert(workingDays);
 		}
		
 		}
		
 	document.getElementsByName('labWorkingDays')[0].value=workingDays;
 	//alert("final value is");
 	//alert(document.getElementsByName('labWorkingDays')[0].value); 
 	//alert(workingDays);
 		}
  
  
  function clearForm()
  {
   
     submitPage('CLEAR');
  
  }
</script>

<html:form action="/masters/LabMstACT">


	
	<html:hidden name="LabMstFB" property="hmode" />
	<html:hidden name="LabMstFB" property="labCode" />
	<html:hidden name="LabMstFB" property="hospitalCode" />
	<html:hidden name="LabMstFB" property="labWorkingDays" />
	<html:hidden name="LabMstFB" property="selectedChk" />
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="LabMstFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
	
  
	<his:TransactionContainer>
		<his:TitleTag name="Global Laboratory Master">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			  <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LaboratoryName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					 <html:text name="LabMstFB" property="laboratoryName"  maxlength="60" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			     <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabShortShortName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					 <html:text name="LabMstFB" property="labShortSName"  maxlength="25" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			        <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="DepartmentCombo"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.DEPART_COMBO %>">
			      <div align="left">
					 
				 <html:select name="LabMstFB" property="department"  tabindex="1" style="width:41%" >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.DEPART_COMBO %>" property="value" labelProperty="label"/>
				      				</html:select>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
			     
			     

			     
			      <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="SampleNumberConfiguration"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="LabMstFB"   tabindex="1" property="sampleNumberConfig" value="1"  ></html:radio>
						
						<bean:message key="LabSampleNumberCofig"/>
						<html:radio name="LabMstFB" tabindex="1" property="sampleNumberConfig" value="2" ></html:radio>
						
						<bean:message key="SamplecollectionAreaConfig"/>
						
						<html:radio name="LabMstFB" tabindex="1" property="sampleNumberConfig" value="3" ></html:radio>
						
						<bean:message key="Manual"/>
					    
					    
				  </div>
			     </td>
			     </tr>
			     
			     
			     <!-- lab no configuration -->
			       <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabNumberConfiguration"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="LabMstFB"   tabindex="1" property="labNumberConfig" value="1"  ></html:radio>
						
						<bean:message key="Manual"/>
						<html:radio name="LabMstFB" tabindex="1" property="labNumberConfig" value="2" ></html:radio>
						
						<bean:message key="Automatic"/>
						
						
					    
					    
				  </div>
			     </td>
			     </tr>
			     
			     <!--lab type  -->
			     
			      <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="LabMstFB"  tabindex="1" property="labType" value="1"  ></html:radio>
						
						<bean:message key="Routine"/>
						<html:radio name="LabMstFB" tabindex="1" property="labType" value="2" ></html:radio>
						
						<bean:message key="Emergency"/>
					    
					    
				  </div>
			     </td>
			     </tr>
			     
			     <!-- display header for this laboratory -->
			     
			      <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="displayHeader"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="LabMstFB"  tabindex="1" property="displayHeader" value="1"  ></html:radio>
						
						<bean:message key="yes"/>
						<html:radio name="LabMstFB" tabindex="1" property="displayHeader" value="0" ></html:radio>
						
						<bean:message key="no"/>
					    
					    
				  </div>
			     </td>
			     </tr>
			     
			      <tr>
			    <td width="20%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabWorkingDays"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="80%" class="tdfont">
			     
				    <bean:define name="LabMstFB" property="labWorkingDays" id="labWorkingDays" type="java.lang.String" />
				    <%
				    // Logic to get the working days in a week .. True->Checked;  False-> unchecked
				    
				    	boolean bMondayWorking=false;
				        boolean bTuesdayWorking=false;
				        boolean bWednesdayWorking=false;
				        boolean bThursdayWorking=false;
				        boolean bFridayWorking=false;
				        boolean bSaturdayWorking=false;
				        boolean bSundayWorking=false;
				    if(labWorkingDays!=null && !labWorkingDays.equals("")){
				    	bMondayWorking=(labWorkingDays.charAt(0)=='1')?true:false;
				        bTuesdayWorking=(labWorkingDays.charAt(1)=='1')?true:false;
				        bWednesdayWorking=(labWorkingDays.charAt(2)=='1')?true:false;
				        bThursdayWorking=(labWorkingDays.charAt(3)=='1')?true:false;
				        bFridayWorking=(labWorkingDays.charAt(4)=='1')?true:false;
				        bSaturdayWorking=(labWorkingDays.charAt(5)=='1')?true:false;
				        bSundayWorking=(labWorkingDays.charAt(6)=='1')?true:false;
				    }
				    %>
				      <div align="left">
				      <%if(bMondayWorking){ %>
					         <input Type="checkbox" name="chkMon" onclick="setDay(this)" checked="checked"/>
					   <%}else{ %>
					   		<input Type="checkbox" name="chkMon" onclick="setDay(this)" />
					   	<%} %>
					       <bean:message key="Monday"/>
					       
					      <%if(bTuesdayWorking){ %>
						         <input Type="checkbox" name="chkTue" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkTue" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Tuesday"/>
					       
					       <%if(bWednesdayWorking){ %>
						         <input Type="checkbox" name="chkWed" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkWed" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Wednesday"/>
					       
					        <%if(bThursdayWorking){ %>
						         <input Type="checkbox" name="chkThu" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkThu" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Thursday"/>
					       
					        <%if(bFridayWorking){ %>
						         <input Type="checkbox" name="chkFri" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkFri" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Friday"/>
					       
					         <%if(bSaturdayWorking){ %>
						         <input Type="checkbox" name="chkSat" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkSat" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Saturday"/>
						   	
						   	 <%if(bSundayWorking){ %>
						         <input Type="checkbox" name="chkSun" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkSun" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Sunday"/>
						    
					  </div>
			     </td>
			     </tr>
			     
			     <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="NumberofTests"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					 <html:text name="LabMstFB" property="numberofTests"  maxlength="4" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateNumeric(event,this)" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			     <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="Headertext"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					 <html:text name="LabMstFB" property="headertext"  maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			     
			      <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="FooterText"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					 <html:text name="LabMstFB" property="footerText"  maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			     
			     
			     <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="remarks"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					 <html:textarea name="LabMstFB" property="remarks" cols="28"    tabindex="1"  readonly="<%=this.readOnly %>" onkeypress="return CheckMaxLength(event,this,50,3)" >
					 </html:textarea>
				  </div>
			     </td>
			     </tr>
			     
			       
			  
			     
			     </table>
			      </his:ContentTag>
			      
			      <his:ButtonToolBarTag>
				<span id="saveDiv">
			    <logic:notEqual name="LabMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="LabMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearAddForm()" onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="LabMstFB" property="hmode" value="MODIFY"> 
				   <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
	</his:TransactionContainer>
	
	
	
		</html:form>
	</body>
</html>