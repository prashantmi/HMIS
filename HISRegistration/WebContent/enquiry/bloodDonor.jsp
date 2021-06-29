<%@page import="enquiry.vo.BloodDonorEnquriyVO"%>
<%@page import="enquiry.enquiryConfig"%>



<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="registration.RegistrationConfig"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>



<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<script>
function submitPage(e){
if(e.keyCode==13 || e.type=="click")
   {
	document.forms[0].hmode.value="ENQUIRY";
	document.forms[0].submit();
   }	
}




</script>

<body>
<%
 	String  currentDate="";
    currentDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy HH:mm");
 %>
<html:form action="/bloodDonor">
<%@page import="java.util.*,enquiry.*"%>
<his:TransactionContainer>


<his:TitleTag>
		<his:name>
			<bean:message key="voluntaryDonorEnquiry" />
		</his:name>
		<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	         <bean:message key="date"/>
	         <bean:message key="and"/>
	         <bean:message key="time"/>
         <%=currentDate %>      
     </font></b>
</his:TitleTag>

<his:ContentTag>
<table width="100%">
	<tr>
	
	<td class="tdfonthead" width="25%">
	<div align="right">
	<b><font  size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	<bean:message key="bloodgroup" /> </font></b>
	</div> 
	</td>
	
	<td class="tdfonthead" width="25%">
	<div align="left">
					<html:select name="BloodDonorFB" tabindex="1" property="bloodGroup" styleClass="regCbo" >
			 		<html:option value="'%'">All</html:option>
			 		<logic:present  name="<%=enquiryConfig.ALL_BLOOD_GROUPS_LIST%>">
  			 		<html:options collection="<%=enquiryConfig.ALL_BLOOD_GROUPS_LIST%>" property="value" labelProperty="label" />
	   		 		</logic:present>
	   		 		</html:select>
	</div>
	</td>
	
	<td class="tdfonthead" width="25%">
	<div align="right">
	<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif">  
	<bean:message key="emergencyCall" /></font></b>
	</div> 
	</td>
	
	<td class="tdfonthead" width="25%">
	<div align="left">
	<html:checkbox name="BloodDonorFB" tabindex="1"  property="emergencyCall" value="1" ></html:checkbox>
	</div>
	</td>
	
	</tr>
	
	
</table>

</his:ContentTag>
<his:ButtonToolBarTag>
	<div align="center">
	<img src="../hisglobal/images/GoFront.png" tabindex="1" width="58" height="30" onkeypress="submitPage(event);" onclick="submitPage(event);">
	 </div>	 
         
</his:ButtonToolBarTag>	

<logic:present name="<%=enquiryConfig.VOLUNTARY_BLOOD_DONORS%>">
<his:SubTitleTag name="Voluntary Donors">
</his:SubTitleTag>
  <his:ContentTag>
  
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <bean:message key="name"/>
	  </b>
	  </font>
	  </div>
      </td>  
     
      <td width="10%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <bean:message key="age"/>
	  <bean:message key="slash"/>
	  <bean:message key="gender"/>
	  </b>
	  </font>
	  </div>
      </td>  
     
     
      <td width="10%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <bean:message key="bloodgroup"/>
	  </b>
	  </font>
	  </div>
      </td>  
     
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <bean:message key="contactInfo"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
       <td width="15%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <bean:message key="emergencyDonation"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
       <td width="10%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <bean:message key="lastDonationDate"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
       <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <bean:message key="lastDonationStatus"/>
	  </b>
	  </font>
	  </div>
      </td>  
           
     </tr>
     
     <logic:iterate id="donorEnquiryVO" name="<%=enquiryConfig.VOLUNTARY_BLOOD_DONORS %>" type="enquiry.vo.BloodDonorEnquriyVO">
     <tr> 
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   <bean:write name="donorEnquiryVO" property="donorFirstName"/>  
	   <bean:write name="donorEnquiryVO" property="donorMiddleName"/>  
	   <bean:write name="donorEnquiryVO" property="donorLastName"/>  
	     
	  </div>
      
       </td>
       
       <td width="10%" class="tdfont">	  
	  <div align="left">
	   <bean:write name="donorEnquiryVO" property="donorAge"/>
	    <bean:message key="slash"/>  
	   <bean:write name="donorEnquiryVO" property="donorGender"/>  
	   
	     
	  </div>
      
       </td>
       <td width="10%" class="tdfont">	  
	  <div align="left">
	   <bean:write name="donorEnquiryVO" property="bloodGroupDesc"/>  
	        
	  </div>
      
       </td>
       <td width="20%" class="tdfont">	  
	  <div align="left">
	   <bean:write name="donorEnquiryVO" property="donorMobileNo"/>  
	     <bean:message key="slash"/> 
	   <bean:write name="donorEnquiryVO" property="donorEmailId"/>  
	   	     
	  </div>
      
       </td>
       <td width="15%" class="tdfont">	  
	  <div align="left">
	   <bean:write name="donorEnquiryVO" property="donationEmergencydesc"/>  
	        
	  </div>
      
       </td>
       <td width="10%" class="tdfont">	  
	  <div align="left">
	   <bean:write name="donorEnquiryVO" property="donationLastDate"/>  
	   	     
	  </div>
      
       </td>
       
       
        <td width="20%" class="tdfont">	  
	  <div align="left">
	   <bean:write name="donorEnquiryVO" property="donationLastStatusCode"/>  
	   	     
	  </div>      
       </td>
  </tr>
  </logic:iterate>
 
   	   
</table>
 
</his:ContentTag>
</logic:present>
</his:TransactionContainer>

<html:hidden name="BloodDonorFB" property="hmode"/>

</html:form>
<his:status/>
</body>
</html>