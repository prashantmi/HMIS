
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/time.js"/>

<%@ page import ="registration.*,hisglobal.vo.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function checkMaxLenTextArea(e,elem,maxLen){
	key = e.keyCode;

        //alert(key);
		var valid=true;
		if(key==8 || key==46) //backspace || del
			return true;
		if(key==13)	//return not allowed
			return true;
		val = elem.value; 
		if(val.length+1>maxLen)
			return false;

}


 function showdivhandovertorelative(){
   //alert("showdivhandovertorelative");
    <% EpisodeVO epVO=(EpisodeVO)session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);
       if(epVO.getEpisodeTypeCode().equalsIgnoreCase(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)){
    	   %>
    	   alert("MLC CASE::: Body cannot be handed over to relative");     	   
   	       document.getElementsByName("bodyHandOverTo")[1].checked=true;    	       	   
    	  <%
       }
       else
       {
    %>    
    document.getElementById("divhandovertorelative").style.display="";  
	//document.getElementsByName("relativeCode")[0].selectedIndex=-1;
    document.getElementsByName("patrelativename")[0].value="";
    document.getElementsByName("patRelativeAddress")[0].value="";    
    <%
    }
       %>
 }
 
function  hideivhandovertorelative(){
	//alert("hideivhandovertorelative");
    document.getElementById("divhandovertorelative").style.display="none";  
}
</script> 
 <his:SubTitleTag name="Death Details"> </his:SubTitleTag>
 
 <his:ContentTag>
 <table width="100%" cellpadding="1" cellspacing="1">
     
     <tr>     
      <td width="28%"  class="tdfonthead">      
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font>
		 <bean:message key="deathdate"/>
		</font>
		</div>
		</td>
        
        <td width="22%" class="tdfont">        
        <his:date name='<%="deathDate"%>' dateFormate="%d-%b-%Y" />
         </td>               
        
        <td width="25%" class="tdfonthead">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <font color="#FF0000">*</font>
        <bean:message key="deathtime"/>
        </font>
        </div>
        </td>
        
        <td width="25%" class="tdfont">
        <html:text name="EpisodeDeathFB" property="deathTime" onkeypress="checkTime(event,this);" onblur="if(this.value.length!=0 && this.value.length!=5) {alert('Use Time Format HH:MM 24Hr');this.focus();}" maxlength="5" tabindex="1" />
        <font color="#FF0000"> Time Format HH:MM 24Hr</font></td>
      </tr>
	
	  <tr> 
        <td class="tdfonthead" width = "28%">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <font color="#FF0000">*</font>
        <bean:message key="deathcause"/>
        </font>
        </div>
        </td>
        
        <td width="22%" class="tdfont"%>
        <html:textarea styleClass="textarea3" name="EpisodeDeathFB" property="deathCause" tabindex="1" onkeypress=" return checkMaxLenTextArea(event,this,500)"/>                 
        </td>      
        
        <td class="tdfonthead" width = "25%">
        <div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <bean:message key="remarks"/>
        </font>
        </div></td>
        <td class="tdfont" width = "25%">
        <html:textarea styleClass="textarea2" name="EpisodeDeathFB" property="consultantRemark" tabindex="1" onkeypress="return checkMaxLenTextArea(event,this,500)"/>
        
        </td>

      </tr>
      	 
     	 <tr>
	    <td width="28%"  class="tdfonthead">      
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<font color="#FF0000">*</font> <bean:message key="deathManner"/>		
		</font></div>
		</td>       
        <td width="22%" class="tdfont" >         
         	<html:select name="EpisodeDeathFB"  property="mannerOfDeath" tabindex="1" styleClass="regCbo">	
	           <html:option value="">Select Value</html:option>
		       <html:options collection = "<%=RegistrationConfig.ESSENTIAL_BO_DEATH_MANNER%>" property  = "value" labelProperty = "label"/>
		 	</html:select>
       </td>          
      <td width="22%" class="tdfonthead" ></td>
      <td width="22%" class="tdfont" ></td>    
    </tr>
    <tr>
    <td width="28%"  class="tdfonthead">      
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<font color="#FF0000">*</font> <bean:message key="deathverifiedBy"/>
		</font></div>
		</td>
		    
     <td width="22%" class="tdfont" >
        	<html:text name="EpisodeDeathFB" property="attendConsultantId" maxlength="10" tabindex="1"/>
     </td>      
     
     <td width="25%" nowrap  class="tdfonthead">      
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<font color="#FF0000">*</font> <bean:message key="deathverificationDate"/>
		</font>
		</div>
		</td>       
     
        <td width="25%" class="tdfont" >
				 <his:date name='<%="deathverificationDate"%>' dateFormate="%d-%b-%Y" />     
	    </td>      
        </tr>
                 
      </table>
      </his:ContentTag>
      
      <his:SubTitleTag name="Hand Over Detail"> </his:SubTitleTag>

    <his:ContentTag>

          <table width="100%" cellpadding="1" cellspacing="1">   
          <tr> 
		        <td width="25%" height="22" class="tdfonthead" nowrap> <div align="right">
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font>
		          <bean:message key="handoverdate"/>
		        </font></div></td>
		        <td width="25%" class="tdfont">
      		        <his:date name='<%="bodyHandOverDate"%>' dateFormate="%d-%b-%Y" />      		        		        
		        <td width="25%" class="tdfonthead" nowrap><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		        <font color="#FF0000">*</font> <bean:message key="handovertime"/>
		        </font></div></td>
		        <td width="25%" class="tdfont" nowrap>
		        <html:text  name="EpisodeDeathFB" property="bodyHandOverTime" onkeypress="checkTime(event,this);" onblur="if(this.value.length!=0 && this.value.length!=5) {alert('Use Time Format HH:MM 24Hr');this.focus();}" maxlength="5" tabindex="1" /><font color="#FF0000"> Time Format HH:MM 24Hr</font>
		        </td>
     	 </tr>
     
     	 
         <tr>
         <td class="tdfont" width="25%">           
	          <div id="divbodyhandoverdetail">          
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		           <bean:message key="bodyhanoverrelative"/>
		      </font> 
		      <html:radio name="EpisodeDeathFB" property="bodyHandOverTo"  value="<%=RegistrationConfig.BODY_HANDOVER_TO_RELATIVE%>" onclick="showdivhandovertorelative()" tabindex="1"/> 
		         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      <bean:message key="mortuary"/></font> 
		         <html:radio name="EpisodeDeathFB" property="bodyHandOverTo" value="<%=RegistrationConfig.BODY_HANDOVER_TO_MORTUARY%>" onclick="hideivhandovertorelative()" tabindex="1"/>
	         </div>
         </td>           
       
         <td class="tdfont" colspan="3"></td>
    </tr> 
    
  </table>

    <div id="divhandovertorelative" style='display:none'>  
    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <td width="25%" class="tdfonthead">    
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <font color="#FF0000">*</font><bean:message key="relativename"/>
		</font></div>
		</td>
        <td width="25%" class="tdfont">
        	<html:text name="EpisodeDeathFB" property="patRelativeName" styleClass="textboxBig" tabindex="1" maxlength="80"/>
        </td>
        <td width="25%" class="tdfonthead">
        	<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <font color="#FF0000">*</font>	<bean:message key="realtionship"/></font></div>
        </td>
        <td width="25%" class="tdfont">			
		<html:select name="EpisodeDeathFB"  property="relativeCode" tabindex="1" styleClass="regCbo">	
           <html:option value="">Select Value</html:option>
	       <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_RELATIVE_CODE%>" property  = "value" labelProperty = "label"/>
		 </html:select>
        </td>
    </tr>
    <tr>
    <td width="25%"  class="tdfonthead">      
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<font color="#FF0000">*</font> <bean:message key="relativeaddress"/>
		</font></div>
		</td>       
         <td width="25%" class="tdfont" >


        	<html:textarea name="EpisodeDeathFB" property="patRelativeAddress" styleClass="textArea2" tabindex="1" onkeypress="return checkMaxLenTextArea(event,this,200)"/>


       </td>      
                 <td width="25%" class="tdfonthead"></td>
                 <td width="25%" class="tdfont"></td>
    </tr>    
    </table>    
   </div> 
   

 </his:ContentTag>
 
  <html:hidden  name='EpisodeDeathFB' property='hmode' value="unspecified"/>
  <div id="divDiagnosisList" style=display:none>              
 	Empty div
 </div>

  <html:hidden  name='EpisodeDeathFB' property='removeRow' value=""/>