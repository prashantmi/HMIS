<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.WebUTIL" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%
boolean varIsNewStatus=false;	
String varStatus="";
%>
<script>
function broughtBy(obj)
{
// alert(document.getElementsByName("isRelative")[0].value)
	if(obj.checked==true)
	{
	// alert("inside brought by if")
		document.getElementById("broughtById").style.display="";
	}
	else
	{
	// 	alert("inside brought by else")
		document.getElementById("broughtById").style.display="none";
	}
}

function enableRelation(obj)
{	
	 
	
	if(obj.checked==true)
	{
			document.getElementsByName("broughtByRelationCode")[0].disabled=false;
	}
	else 
	{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
	}
}

function submit4image(){
	submitTile("REFRESHFORIMAGE");
} 
	function submitTile(mode){
		//alert("In submitTitle:  "+mode);
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
}

	function callThisOnload(){
	   if(document.getElementsByName('cmoType')[0]!=null){
	   document.getElementsByName('cmoType')[0].checked=true;
	 	showdivEmployee(document.getElementsByName('cmoType')[0]);
		}
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0])
		focusCrNo();		



}

function populate(e){ 


document.getElementsByName('crNoToRetrieve')[0].value=e;

submitForm("DGNDETAIL"); 

}


function validateMLC()

{ 
// alert("called");
var valid=true;
 // alert("validateMLC");
	 if(validateObjects(document.forms[0].mlcNo,"MLC Number")
	 	&& validateMlcDate(document.forms[0].mlcDate,"MLC Date")&&
    	 validateObjects(document.forms[0].patCondition,"Patient Condition")&&
    	 validateObjects(document.forms[0].caseNo,"Case Number")&&
     	 validateObjects(document.forms[0].policeStation,"Police Station")&&
    	 validateObjects(document.forms[0].officerIncharge,"Officer In-Charge")&& 
    	 validateObjects(document.forms[0].policeName,"Police Officer Name")&&  
		 validateObjects(document.forms[0].policeDesignation,"Police Officer Designation")&&
		 validateObjects(document.forms[0].batchNo,"Batch Number") &&
	 	 validateObjects(document.forms[0].mlcRemark,"Case Remark")
		 )	 
		 {
		 valid=true;
		 }
	else
	{
        valid=false;
     }
//alert(valid);
return valid;
//

} 

</script>

<his:statusNew>
<%varIsNewStatus=true;

%>	

</his:statusNew>
<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>

<his:TitleTag name="MLC Details">

<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
<b>

</b>
</font>
</his:TitleTag>

<his:InputCrNoTag name="EmgMlcDtlFB"> </his:InputCrNoTag>	


 
<div id='mlcdetails'>
<bean:define id="crNo" name="EmgMlcDtlFB" property="patCrNo" type="java.lang.String"/>
<%if(!crNo.trim().equals("")){%>
	
	<jsp:include page="/registration/patientDetail.cnt" flush="true" />	
	
	
<his:statusTransactionInProcess>
	
<bean:define name="EmgMlcDtlFB" property="mlcDate" id ="mlcDate" type="java.lang.String"/>		
	<his:SubTitleTag name="MLC Details"> </his:SubTitleTag>	
	<his:ContentTag>
	
   <table width="100% cellspacing="1" cellpadding="0">
      	<%varStatus="InProcess";%>
        <tr>
        <td width="25%" class="tdfonthead">
	    <div align="right">
	    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	    <bean:message key="mlcnumber"/>
        </font>
        </div>
        </td>         
        
        <td width="25%" class="tdfont"><!-- 16 -->        
        <div align="left">
        <html:text name="EmgMlcDtlFB" property="mlcNo" tabindex="1" maxlength="10" onkeypress="return validateAlphaNumericOnly(event)"/>
        </div>
        </td>


       <td class="tdfonthead" width="20%">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="isTranferCase"/>
        </font>
     	</td>
     	
     	 <%         
         boolean refer=true;
           System.out.println("getting case resolved");           
           EpisodeVO epvo= (EpisodeVO)session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);
           if(epvo!=null){
           String refered= epvo.getEpisodeReferAccept();
           if(refered!=null){
           if(refered.equalsIgnoreCase(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
        	   refer= false;
           else
        	   refer= true;
            }
           }
         %>
              
		<td width="30%">
 		<html:checkbox name="EmgMlcDtlFB" property="isTransfer" value="<%=RegistrationConfig.IS_TRANSFER_TRUE%>" disabled="<%=refer%>"/>
        </td>     
        
        </tr>




        <%
 if(mlcDate==null||mlcDate.equalsIgnoreCase("")){  
         	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	System.out.println("dt:::::::::"+dt);        	
        	mlcDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
%>
       <tr> 
       <td width="25%" class="tdfonthead" tabindex="1">
        <div align="right">
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="mlcDate"/>
		</font>
		</div>
        </td>                
               
        <td width="25%" class="tdfont"><!-- 16 -->   
        <div align="left">
        <his:date name='mlcDate' dateFormate="%d-%b-%Y" value="<%=mlcDate%>" />         
        </div>
        </td>       
        
          <td width="20%" valign="top" class="tdfonthead">
             <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
             <bean:message key="patientcondition"/>       
             </font>
          </td>
          
       <td width="30%" class="tdfont">
       <html:textarea name="EmgMlcDtlFB" tabindex="1" property="patCondition" onkeypress="return validateTextArea(event,this,100)" styleClass="textarea2"/>             
       </td>
       
        
       </tr>
       </table>
       </his:ContentTag>
       
        <his:SubTitleTag name="Police Verification Details"> </his:SubTitleTag>
        
<his:ContentTag>
   <table width="100%" cellspacing="1" cellpadding="0">
      
      <tr> 
        <td width="16%" nowrap class="tdfonthead" >
	        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
   	        <bean:message key="policecaseno"/>
	        </font>
        </td>
      
        <td with="17%" nowrap class="tdfont">
            <html:text name="EmgMlcDtlFB" tabindex="1" maxlength="20" styleClass="textboxBig" property="caseNo"/>     
         </td>
         
        <td width="17%" nowrap class="tdfonthead">
	        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="policestation"/>   
	        </font>        
        </td>
        
        <td width="16%" nowrap class="tdfont">
            <html:text name="EmgMlcDtlFB" tabindex="1" property="policeStation" maxlength="100" styleClass="textboxBig"/>     
          </td>

        <td class="tdfonthead" nowrap  width="17%">
        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="officerincharge"/>
	        </font>
        </td>
        
        <td class="tdfont" nowrap  width="17%">
            <html:text name="EmgMlcDtlFB" tabindex="1" property="officerIncharge" maxlength ="60" styleClass="textboxBig" onblur="isAlpha(this,'Officer Incharge')" onkeypress="return validateAlphabetsWithDotsOnly(event)"/> 
         </td>
      </tr>
      <tr> 
        <td class="tdfonthead"  nowrap width="17%">
        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="policeofficername"/>
	        </font>
        </td>
        
        <td class="tdfont" nowrap  width="17%">
            <html:text name="EmgMlcDtlFB" property="policeName" tabindex="1" styleClass="textboxBig" maxlength ="60" onblur="isAlpha(this,'Police Officer Name')" onkeypress="return validateAlphabetsWithDotsOnly(event)"/> 
         </td>
         
        <td width="17%" nowrap class="tdfonthead">
	        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	
	        <bean:message key="policeDesignation"/>
	        </font>
         </td>
          
        <td  width="16%" nowrap class="tdfont">
          <html:text name="EmgMlcDtlFB" tabindex="1" property="policeDesignation" styleClass="textboxBig" maxlength ="50" onblur="isAlpha(this,'Police Designation')" onkeypress="return validateAlphabetsWithDotsOnly(event)"/> 
        </td>

        <td width="17%" nowrap class="tdfonthead">
        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
           <bean:message key="batchno"/>
           
           </font>
        </td>
                
        <td class="tdfont" nowrap  width="17%">
        <html:text name="EmgMlcDtlFB" property="batchNo" maxlength="15" tabindex="1" styleClass="textboxBig"/>       
        </td>
      </tr>
      
      <tr> 
        
        <td class="tdfonthead" nowrap valign="top" width="15%">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	         <bean:message key="isbroughtdead"/>
	        </font>
        </td>                        
		
		<td class="tdfont" nowrap width="17%"  valign="top">
	    	<html:checkbox name="EmgMlcDtlFB" tabindex="1" property="isBroughtDead" value="<%=RegistrationConfig.PATIENT_BROUGHT_DEAD_TRUE%>"/>		
		</td>
		
		<td valign="top" nowrap class="tdfonthead" width="25%">
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="caseremarks"/>
        </font>
        </td>
        
        <td colspan="2" nowrap width="50%" class="tdfont">
            <html:textarea name="EmgMlcDtlFB" tabindex="1" property="mlcRemark" styleClass="textarea2" onkeydown="return CheckMaxLength(event,this,300)" />             
        </td>
        
	  </tr>
      
    </table> 			
	</his:ContentTag>
	
	 <bean:define id="confFlagId" name="<%=Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG%>" ></bean:define>
	 <logic:equal name="confFlagId" value="<%=Config.EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE_TRUE%>">
	<his:SubTitleTag name="Brought By Details"> </his:SubTitleTag>
	
 <his:ContentTag>

  <table width="100%" cellspacing="1" cellpadding="1">
	  <tr>
	    	 <td width="20%" nowrap>	
	    	 <div align="left">
	         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	         <bean:message key="isBroughtBy"/>
	         </font>       
	         <html:checkbox name="EmgMlcDtlFB" tabindex="1" value="<%=RegistrationConfig.IS_BROUGHT_BY_TRUE%>" property="isBroughtBy" onclick="broughtBy(this)" />
	         </div>
	         </td>
	  </tr>
  </table>
</his:ContentTag>
     <div id="broughtById" style="display: none;" >     
       <his:ContentTag>
       <table width="100%" cellspacing="1" cellpadding="0">
       <tr>       
        <td width="22%" class="tdfonthead"><!-- 16 -->
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="name"/>      
	        </font>
        </td>        
        <td width="22%" class="tdfont" > <!-- 18 -->
             <html:text name="EmgMlcDtlFB" tabindex="1" property="broughtByName" onkeypress="return validateAlphabetsWithDotsOnly(event)" tabindex="1" styleClass="textbox" onblur="isAlpha(this,'Middle Name')" maxlength="60"/>
        </td>
        
        <td width="17%" class="tdfonthead"><!-- 16 -->
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="gender"/>      
	        </font>
        </td>        
        <td width="31%" class="tdfont">
       <html:select name="EmgMlcDtlFB" property="broughtByGenderCode" tabindex="1" styleClass="regcbo">
       <html:option value="-1">Select Value</html:option>
         <html:option value="1">Male</html:option>
           <html:option value="2">Female</html:option>
             <html:option value="3">Other</html:option>
	   </html:select>
       </td>
      </tr>
     
      <tr>
      	 <td width="22%" class="tdfonthead"><!-- 16 -->
      	 <div align="right" >
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="isRelative"/>      
	        </font>
	       </div>
        </td>        
        <td width="22%" class="tdfont" > <!-- 18 -->
        <div align="left" >
             <html:checkbox name="EmgMlcDtlFB" tabindex="1" value="<%=RegistrationConfig.IS_RELATIVE_TRUE%>" property="isRelative" onclick="enableRelation(this)" />
        </div>
        </td>
      	 
      	  <td width="17"  class="tdfonthead" >
       <div align="right">
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
       <bean:message key="realtionship"/>
       </font>
       </div></td>
       
       <td width="31%" class="tdfont"  >
       <html:select name="EmgMlcDtlFB" property="broughtByRelationCode" tabindex="1" styleClass="regcbo" disabled="true">
       <html:option value="-1">Select Value</html:option>
	   <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL %>" property  = "value" labelProperty = "label"/>
	   </html:select>
       </td>
      	 
      </tr>
       </table>
      
       <table width="100%" cellspacing="1" cellpadding="0">
      <tr> 
      
       <td width="25%"  class="tdfonthead" >
        <div align="right">
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="broughtFromLoacation"/>         
	        </font>
       </div>
       <div align="right">
      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="phone"/>  <bean:message key="no"/>        
	        </font>
       </div>
       
       </td>
       
       <td width="24%" class="tdfont"  >
       
        <div>
          <html:text name="EmgMlcDtlFB" tabindex="1" property="broughtByLocation"  maxlength="30" styleClass="textbox" onkeypress="return validateAlphabetsWithDotsOnly(event)"/>
         </div>
       <div>
         <html:text name="EmgMlcDtlFB" tabindex="1" property="broughtByPhone"  maxlength="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/>
         </div>
        
       </td>
       
       
       
       <td width="19%" valign="top" rowspan="2" class="tdfonthead"><!-- 18 --> 
          	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             <bean:message key="broughtbyaddress"/>
       		 </font>
       	</td>
      
       	<td colspan="2" width="32%" rowspan="2" class="tdfont" valign="top" ><!-- 3 -->
            <html:textarea styleClass="textarea2" onkeypress="return validateTextArea(event,this,100)"  tabindex="1" name="EmgMlcDtlFB" property="broughtByAddress"/>
        </td>
        
      </tr>
</table>

</his:ContentTag>
</div>
</logic:equal>
        
	
	
	<%--
	 <his:SubTitleTag name="CMO Details"> </his:SubTitleTag>
	 <his:ContentTag>
        <table width="100%">
        <tr>
           <td class="tdfonthead" nowrap width="13%">       
	       	  <div align="right">
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      <bean:message key="employee"/>
		      </font>
		      <html:radio name="EmgMlcDtlFB" property="cmoType" tabindex="1" value="E" onclick="showdivEmployee(this)"/> 
		         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      <bean:message key="nonemployee"/></font> 
		        <html:radio name="EmgMlcDtlFB" property="cmoType" tabindex="1" value="N" onclick="showdivnonemployee(this)"/>
			</div>
          </td>               
       	<td class="tdfont" width="18%">
        <div id='divLabelcmoName' style='display:none' align="left">
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="cmoName"/></font>
         </div>
        </td>
        
        <td class="tdfonthead" width="18%">
	    <div id='divCmoName' style='display:none'align="left">	            
		<html:text name="EmgMlcDtlFB" tabindex="1" property="doctorName" styleClass="textboxBig" maxlength ="20" onblur="isAlpha(this,'CMO Name')" onkeypress="return validateAlphabetsOnly(event)"/>
		</div>
		<div id='divemployeeCmoCode' style='display:none'align="left">	            
		<html:select
					name="EmgMlcDtlFB" property="cmoCode"
					tabindex="1" styleClass="regcbo" >
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>"
						property="value" labelProperty="label" />
				</html:select>
		</div>
		</td>
		
		<td class="tdfont" width="18%"> -- %>
	<%--	 <div id='divemployeeLabelcmoCode' style='display:none' align="left">
         <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <bean:message key="cmoCode"/>
		 </font>
         </div>
		</td> --%>
		
	<%--	<td class="tdfont" width="18%">
		 <div id='divemployeeCmoCode' style='display:none' align="left">         
     <html:text  name="EmgMlcDtlFB" tabindex="1" property="cmoCode" styleClass="textboxBig" maxlength="10"/> 
    	 </div> 
		</td>
       </tr>
      </table>
         </his:ContentTag>      --%>
           <input type="hidden" name="sysDate" value="<%=mlcDate%>"/> 
</his:statusTransactionInProcess>
  <%
  }

%>
</div>

<his:ButtonToolBarTag>


     <%if(varStatus.equals("InProcess")){%>
		   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateMLC(),'SAVE');" onclick =  "submitFormOnValidate(validateMLC(),'SAVE');" >
	       <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">      

      <%} else{ %>
	  	

        	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
    	    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">

	  <%} %>

	     
</his:ButtonToolBarTag>
<his:status/>


  <html:hidden name="EmgMlcDtlFB" property="hmode" value="unspecified"/>

  