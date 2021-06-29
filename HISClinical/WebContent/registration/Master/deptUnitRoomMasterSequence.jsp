<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import ="registration.*" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>

<script>

window.history.forward()

function submitTile(mode){
   document.getElementsByName("sequenceMode")[0].value=mode;   
   //alert("sequenceMode"+document.getElementsByName("sequenceMode")[0].value)
   document.forms[0].submit();
}

function submitCancel(){   
   val= document.getElementsByName("source")[0].value;   
  // alert("val:::::::"+val);
   if(val==null||val==""){
        submitTile('CANCEL');
   }
   else
   submitTile('CANCEL');
}

function submit4sequence(mode,idx){   
   document.getElementsByName("roomSequenceToChange")[0].value=idx; 
   document.getElementsByName("sequenceUpOrDown")[0].value=mode;    
   submitTile('REFRESH');  
   document.forms[0].submit();
}

</script>

<body>
<html:form action="/master/deptunitroomsequence.cnt"> 
<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>
<his:TitleTag name="Sequence allocation to Rooms">			
	<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          
     </font></b>
</his:TitleTag> 
<logic:notEmpty name="DeptUnitRoomMasterSequenceFB" property="source">
  <html:hidden name="DeptUnitRoomMasterSequenceFB" property="unitCode"/>
   <html:hidden name="DeptUnitRoomMasterSequenceFB" property="departmentName"/>
    <html:hidden name="DeptUnitRoomMasterSequenceFB" property="unitName"/>
   <html:hidden name="DeptUnitRoomMasterSequenceFB" property="departmentCode"/>
    <html:hidden name="DeptUnitRoomMasterSequenceFB" property="roomCode"/>
</logic:notEmpty>
<logic:empty name="DeptUnitRoomMasterSequenceFB" property="source">
<%//System.out.println("1111111111111111111"); %>
<his:ContentTag>
			 <table width="100%" border="0"  cellspacing="1" cellpadding="0">    
						  <tr>
						      <td width="20%" class="tdfonthead" nowrap>
							           <div align="right">	           
							              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							              <b><bean:message key="department"/></b>
							              </font>
							            </div>
						      </td>                        
						      
						      <td width="20%" class="tdfont" nowrap>
							         <div align="left">	           
							             <html:select name="DeptUnitRoomMasterSequenceFB" tabindex="1" property="departmentCode" styleClass="registrationCmb" onchange="if(this.value!='-1') submitTile('GETUNIT')">
									 			<html:option value="-1">Select Value</html:option>
						  			 			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
							   		 	</html:select>
							          </div>
						      </td>
						        
						  <%System.out.println("....4");%>
						      <td width="20%" class="tdfonthead" nowrap>
							           <div align="right">	           
							              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							              <b><bean:message key="unit"/></b>
							              </font>
							            </div>
						      </td>      
						      <td width="20%" class="tdfont" nowrap>
							         <div align="left">	           
							             <html:select name="DeptUnitRoomMasterSequenceFB" tabindex="1" property="unitCode" styleClass="registrationCmb" onchange="if(this.value!='-1') submitTile('GETROOMS')">
									 			<html:option value="-1">Select Value</html:option>
						  			 			<html:options collection="<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT%>" property="value" labelProperty="label" />
							   		 	</html:select>
							          </div>
						      </td>                                             
						 </tr>
			 </table>
</his:ContentTag>
</logic:empty>
<%//System.out.println("22222222222222222222222222"); %>
<his:statusInProcessWithJsp>
<his:ContentTag>

 <table width="100%" cellspacing="1" cellpadding="1"> 

  <%System.out.println("existing rooms found");%>  
 <tr>
 <td width="20%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomSequence"/></b>
		              </font>
		            </div>
 </td>       	  
 <td width="20%" class="tdfonthead" nowrap valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="roomName"/></b>
		              </font>
		            </div>
 </td>     
  
 
 <td width="20%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="capacity"/></b>
		              </font>
		            </div>
 </td> 
 <%-- 
 
 <td width="20%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveFrom"/></b>
		              </font>
		            </div>
 </td> 
 
 <td width="20%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveTo"/></b>
		              </font>
		            </div>
 </td>
 --%>
 
 </tr>
 <%//System.out.println("33333333333333333333333333"); %>
 <bean:define name="<%=RegistrationConfig.DEPT_UNIT_ROOM_MASTER_SEQUENCE %>" id="list" type="java.util.LinkedList"/>
 <logic:iterate name="<%=RegistrationConfig.DEPT_UNIT_ROOM_MASTER_SEQUENCE %>" id="sequenceList" indexId="idx">


 <tr> 
 <td width="20%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:write  name="sequenceList" property="sequenceNo"/></b>		              		         
		              </font>
		            </div>
 </td>

<td width="20%" class="tdfont" nowrap valign="top">
<logic:notEqual name="idx" value="0">
	<div align="left">
	<a style=cursor:hand>
		<img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor:pointer" tabindex='1' border=0 alt='up' onClick="submit4sequence('up','<bean:write name='idx'/>')"/>
	</a>
	</div>
</logic:notEqual>	
	<div align="center">	           
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		           
		<b><bean:write  name="sequenceList" property="roomName"/></b>		              
		</font>
	</div>
<logic:notEqual name="idx" value="<%=String.valueOf(list.size()-1)%>">
	<div align="right">
	<a style=cursor:hand>
		<img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor:pointer" tabindex='1' border=0 alt='down' onClick="submit4sequence('down','<bean:write name='idx'/>')">
	</a>	   
	</div>
</logic:notEqual>
</td>
  
  <%//System.out.println("44444444444444444444444444444444"); %>
 
  
 <td width="20%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:write  name="sequenceList" property="capacity"/>		              
		              </font>
		            </div>
 </td> 

 <%-- 
  
 <td width="20%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:write  name="sequenceList" property="effectiveFrom"/>		              
		              </font>
		            </div>
 </td>
  <%System.out.println("555555555555555555555555555555555"); %>
  
  
  <td width="20%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		             
		              <bean:write  name="sequenceList" property="effectiveTo"/>		              
		              </font>
		            </div>
 </td> 
 --%>
 
 </tr> 
 </logic:iterate>
 </table>
 </his:ContentTag>
 <%varStatus="Inprocess";%> 
 </his:statusInProcessWithJsp>
   <%System.out.println("666666666666666666666666666666"); %>
<html:hidden name="DeptUnitRoomMasterSequenceFB" property="roomSequenceToChange"/>
<html:hidden name="DeptUnitRoomMasterSequenceFB" property="sequenceUpOrDown"/>
<html:hidden name="DeptUnitRoomMasterSequenceFB" property="sequenceMode"/> 	
<html:hidden name="DeptUnitRoomMasterSequenceFB" property="source"/> 	
 
 <his:ButtonToolBarTag>
 <logic:equal name="DeptUnitRoomMasterSequenceFB" property="showSave"  value="save" >
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitTile('SAVE');" tabindex="1" onclick="submitTile('SAVE')" >
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitCancel()" onkeypress="if(event.keyCode==13) submitCancel()">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW');">
 </logic:equal> 
<logic:notEqual name="DeptUnitRoomMasterSequenceFB" property="showSave"  value="save" >  	                 
           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitCancel()" onkeypress="if(event.keyCode==13) submitCancel()">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('NEW')" onkeypress="if(event.keyCode==13) submitTile('NEW');">
</logic:notEqual>
	</his:ButtonToolBarTag>
</his:TransactionContainer>

</html:form>
<his:status/>
</body>
</html>