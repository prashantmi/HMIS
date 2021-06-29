
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/calendar.js" />

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function submitTile(mode){
var submit=true;
//alert ("inside submitTile");

<logic:equal name="ReferDtlModificationFB" property="isRefferInOut" value="2">
<logic:notEmpty name="ReferDtlModificationFB" property="patRefGnctdHospitalCode">
    var elmt=document.getElementsByName('patRefDoctor')[0];
    //alert("sdddffjhddddsd"+elmt.value+":::sfdfdf");
   if(document.getElementsByName('patRefDoctor')[0].value==""){
	   //alert ("inside patRefDoctor");
      submitt=false;
      alert("Refered by doctor::Please enter value");
      patRefDoctor.focus();
      return false;
      }
   if(document.getElementsByName('patRefGnctdHospitalCode')[0].selectedIndex=="0"){   
       submitt=false;
       alert("GNCTD Hospital::Please select valid value");
       patRefGnctdHospitalCode.focus();
       return false;
      }
   if(document.getElementsByName('patRefGnctdHospitalCrno')[0].value==""){
         alert("Ref CR NO::Cannot be left blank");
               submitt=false;
       patRefGnctdHospitalCrno.focus();       

      return false;
      }
   if(document.getElementsByName('patRefGnctdHospitalDept')[0].value==""){
		 alert("Refered Department::Cannot be left blank");
       	 submitt=false;
       	 patRefGnctdHospitalDept.focus();
         return false;
      }
   if(document.getElementsByName('patRefGnctdHospitalDeptUnit')[0].value==""){
       alert("Unit::Cannot be left blank");
       patRefGnctdHospitalDeptUnit.focus();
       submitt=false;
       return false;
      }
      </logic:notEmpty>
</logic:equal>

<logic:equal name="ReferDtlModificationFB" property="isRefferInOut" value="1">
   if(document.getElementsByName('patRefDoctor')[0].value==""){
     alert("Refered doctor::Cannot be left blank");
       patRefDoctor.focus();
       submitt=false;
       }
   if(document.getElementsByName('departmentCode')[0].value=="-1"){
      alert("Department::Please select valid value");
       departmentCode.focus();
       submitt=false;
      }
</logic:equal>
<logic:equal name="ReferDtlModificationFB" property="isRefferInOut" value="2">
<logic:empty name="ReferDtlModificationFB" property="patRefGnctdHospitalCode">   
   if(document.getElementsByName('patRefHospitalName')[0].value==""){   
      alert("RefHospitalName::Cannot be left blank");
       ppatRefHospitalName.focus();
       submitt=false;
      }
</logic:empty>
</logic:equal>

if(submit){
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value);
	document.forms[0].submit();
	}
}
function callThisOnload(){
	focusCrNo();
}

function populate(e){
	document.getElementsByName('crNoToRetrieve')[0].value=e;
	submitForm("DGNDETAIL"); 
}

function submit4ReferDtl(episodeNO){
//alert("inside submit4ReferDtl");
	var elmt=document.getElementsByName('recordInQuestion')[0];
	elmt.value=episodeNO;	
	elem = document.getElementsByName('hmode')[0];
	elem.value = "GETEPISODEDTL";
	//alert("inside submit4ReferDtl");
	document.forms[0].submit();
}
</script>
   
<%System.out.println("....2.1");%>  

<%boolean flagIsStatusNew = false;
			String varStatus = "";
			boolean varIsNewStatus = false;
			String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
			%>
<his:statusNew>
	<%flagIsStatusNew = true;
				varIsNewStatus = true;
				varStatus = "New";%>
</his:statusNew>
<his:TitleTag name="Refer Detail Modification">
		
		<!--<b>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<a onclick="openPopup('registration/searchByNamePopup.cnt',event)" style=cursor:pointer>
		<bean:message key="search"/>
		<bean:message key="by"/>
		<bean:message key="name"/>
		</a>
		</font>
		</b>
		-->
	<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              
     </font></b>
</his:TitleTag>  

<his:InputCrNoTag name="ReferDtlModificationFB">
	
</his:InputCrNoTag>

  <%if (!flagIsStatusNew) {

				%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />
<%System.out.println("Hi 146");

				%>


<%System.out.println("Hi 149");

				%>

<his:statusTransactionInProcess>

  <input type='hidden' name='crNoToRetrieve'/>
  <%
	System.out.println("record found inside jsp:::::::::::::");
	Status objStatus= (Status) request.getAttribute(Config.STATUS_OBJECT);
	if(objStatus!= null){
		HashSet statuslist= objStatus.getStatusList();
		System.out.println("statuslist"+statuslist);
		if (statuslist.contains(objStatus.RECORDFOUND)){ 
			System.out.println("record found inside jsp");
			 varStatus="InProcess";
		}		
	}   
  %>  
             
  <bean:define name="ReferDtlModificationFB" property="isRefferInOut" id="reftype" type="java.lang.String"/>       
  <logic:empty name="<%=RegistrationConfig.EPISODE_REFER_VO%>"> 
  <his:ContentTag>
  <table width="100%" border="0"  cellspacing="1" cellpadding="0">    
      <td width="20%"  class="tdfonthead">
	           <div align="center">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="episodeno"/></b>
	              </font>
	            </div>
       </td>                        
        <td width="20%"  class="tdfonthead">
	           <div align="center">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="department"/></b>
	              </font>
	            </div>
       </td>                      
       
       <td width="20%"  class="tdfonthead">
	           <div align="center">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="unit"/></b>
	              </font>
	            </div>
       </td>                      
       
       <td width="20%" class="tdfonthead">
	           <div align="center">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="referdetails"/></b>
	              </font>
	            </div>
       </td>                       
  
  
  <logic:iterate name="<%=RegistrationConfig.ARR_EPISODE_VO%>"  id ='episodeVO' indexId="idx"> 
  <tr> 
          <td width="20%" class="tdfont" >	            
          <div align="center">
          <bean:write name="episodeVO" property="episodeCode"/>
          </div>
          </td>
          
          <td width="20%" class="tdfont" >	            
          <div align="center">
          <bean:write name="episodeVO" property="department"/>
          </div>
          </td>
          
          <td width="20%" class="tdfont" >	            
          <div align="center">
          <bean:write name="episodeVO" property="departmentUnit"/>
          </div>
          </td>
          
          <td width="20%" class="tdfont">
    	  <div align="center">
    	  <input type="image" border="0" img class="button" src='/HISClinical/hisglobal/images/new-for.png' style=cursor:pointer onclick="submit4ReferDtl('<bean:write name="episodeVO" property="episodeCode"/>')" size = '7' tabindex="1">
	      </div>
	      </td>
  </tr>
  </logic:iterate>
  </table>
  </his:ContentTag>
  </logic:empty>      
  
   <logic:notEmpty name="reftype">
     <his:SubTitleTag  name="Refer Detail">
     <bean:message key="episodeno"/>
     <!--<bean:message key="colon"/>-->
     <bean:write name="ReferDtlModificationFB" property="episodeCode"/> 
     </his:SubTitleTag>   
   </logic:notEmpty>   
     
       
  <logic:equal name="reftype" value="1" >
	  <%
	  	System.out.println("inside case internal refer");  	  
	  %>  
	    <his:ContentTag>
  		<table width="100%" border="0"  cellspacing="1" cellpadding="0"> 
	 	 <tr>
	     <td width="17" colspan="6" class="tdfonthead" >
	     <div align="left">
	     <b><bean:message key="referInternal"/></b>
	     </div>
	     </td>
	     
         <td width="18%" class="tdfonthead" >
	     <div align="right">
	     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	     <b><bean:message key="referredBy"/></b>
	     </font>
	     </div>
         </td>           
         
         <td width="17%" class="tdfont" >
    	 <html:text  name="ReferDtlModificationFB" tabindex="1"  maxlength="60" property="patRefDoctor" onblur="isAlpha(this,'Referred By Doctor')" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" styleClass="textbox"/>
		 </td>
		  		  
		  <td width="17%" class="tdfonthead">
		  <div align="right">
		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
          <bean:message key="department"/>
          </font>
          </div>
		  </td>  
		  
		  <td width="32%" colspan="2" class="tdfont">		             
          <html:select name="ReferDtlModificationFB"  property="departmentCode"  tabindex="1"  styleClass="registrationCmb" >   
		  <html:option value="-1">Select Value</html:option>
		  <html:options collection ="<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
          </html:select>		          
          </td>         
     	  </tr>	 
     </table>  
 </his:ContentTag>	 	   
  </logic:equal>
    
  <logic:equal name="reftype" value="2" >
  
	  <%
	  	System.out.println("inside case external refer");    
	  %>   
	  <logic:notEmpty name="ReferDtlModificationFB" property="patRefGnctdHospitalCode">  
	
	<his:ContentTag>
  		<table width="100%" border="0" cellspacing="1" cellpadding="0"> 
		<tr>
		<td width="17" colspan="6" class="tdfonthead" >
        <div align="left">
        <font color="black" size="3" face="Verdana, Arial, Helvetica, sans-serif">
        <b><bean:message key="referExternal"/>
        <bean:message key="gnctdHospital"/></b>
	    </font>
	    </div>
	    </td>
    	</tr>
	   	<%
	   	   System.out.println("patient is ref by gnctd hospital");  	  
		%>
			<tr>
			<td width="16%" class="tdfonthead" >
		    <div align="right">
		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    <bean:message key="referredBy"/>
            </font>		             
            </div>
            </td>
            
	        <td width="18%" class="tdfont" >
	        <html:text  name="ReferDtlModificationFB" tabindex="1"  maxlength="60" property="patRefDoctor" onblur="isAlpha(this,'Referred By Doctor')" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" styleClass="textbox"/>
	        </td>
			 
			<td width="16%" class="tdfonthead">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="gnctdhosp"/>
	        </font>
	        </div>
		  	</td>
		  	
		  	<td width="35%" colspan="3" class="tdfont">
		  	<html:select name="ReferDtlModificationFB" property="patRefGnctdHospitalCode" tabindex="1" styleClass="registrationCmb" >   
			<html:option value="-1">Select Value</html:option>
			<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>" property = "value" labelProperty = "label"/>
         	</html:select>		          
            </td>		  
		    </tr>
		  	
 		   	<tr>
		    <td width="16%" class="tdfonthead" nowrap >
	        <div align="right">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="refhospcrno"/>
	        </font>
			</div>
            </td>           
            

            
            <td width="18%" class="tdfont" >
    	    <html:text name="ReferDtlModificationFB" tabindex="1"  maxlength="13" property="patRefGnctdHospitalCrno"  tabindex="1" onkeypress="return validateNumeric(event)" styleClass="textbox"/>
		    </td>		  
		    
		    <td width="16%"  class="tdfonthead" >
	        <div align="right">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	        <bean:message key="refhospdept"/>
            </font>
            </div>
            </td>           

            <td width="17%" class="tdfont" >
           <%-- <html:text name="ReferDtlModificationFB" tabindex="1"  maxlength="20" property="patRefGnctdHospitalDept"  tabindex="1" onkeypress="return validateAlphabetsOnly(event)" styleClass="textbox"/>--%>
            
            <html:select name="ReferDtlModificationFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="registrationCmb" >   
			<html:option value="-1">Select Value</html:option>
			<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>" property = "value" labelProperty = "label"/>
         	</html:select>	
		    </td>
		    
		    
		     
		    <td width="18%"  class="tdfonthead" nowrap >
	        <div align="right">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	     	<bean:message key="refhospdeptuntit"/>
	        </font>
	        </div>
            </td>           
            
            <td width="17%" class="tdfont" >
            <html:text  name="ReferDtlModificationFB" tabindex="1"  maxlength="15" property="patRefGnctdHospitalDeptUnit" tabindex="1"  styleClass="textbox"/>
  	        </td>	  
		 
		 			
			</tr>		
			</table>
			</his:ContentTag>
			</logic:notEmpty>
		
			<logic:empty name="ReferDtlModificationFB" property="patRefGnctdHospitalCode">
	<his:ContentTag>
  		<table  width="100%" border="0"  cellspacing="1" cellpadding="0"> 
		<tr>
		<td width="17" colspan="6"  class="tdfonthead" >
		     <b>
		         <bean:message key="referExternal"/> <bean:message key="otherHospital"/>
		     </b>
	     </td>
       </tr>
	
	   	<%
		    System.out.println("patient is ref by other hospital");	   	
		%>	
		<tr>	  
			  <td width="17%" class="tdfonthead">
			      <div align="right">
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		           	  		<bean:message key="hospname"/>
		           	  </font>
	           	  </div>
		  	  </td>		  	  
			  <td width="83%" class="tdfont" >	         
    	           <html:text  name="ReferDtlModificationFB" tabindex="1"  maxlength="100" property="patRefHospitalName" onblur="isAlpha(this,'Referred By Doctor')" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" styleClass="textbox"/>
  			  </td>
  		</tr>	
  		</table>  
 </his:ContentTag>		
	</logic:empty>	
	
  </logic:equal>    
  

</his:statusTransactionInProcess>
<%} %>
	<html:hidden name="ReferDtlModificationFB" property="recordInQuestion"/>
	 <his:ButtonToolBarTag>
     <%if(varStatus.equals("InProcess")){%>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitTile('SAVE')"  tabindex="1" onclick="submitTile('SAVE')" >
         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       <%} else{ %>
	         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	  <%} %>
</his:ButtonToolBarTag>
   <his:status/>
<input type= "hidden" name="hmode" value="unspecified"/> 