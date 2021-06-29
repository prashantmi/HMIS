<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.*,hisglobal.vo.*,hisglobal.vo.RosterMasterVO"  %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Sequence Number</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language ="javascript">

function validateSequenceCombos(){
    var elmt =document.getElementsByName('newSequenceNo');
    var len=elmt.length;
    alert("length"+len);
     for(i=0;i<len;i++ )
		{
		  var elmt =document.getElementsByName('newSequenceNo')[i];
		  alert(typeof(elmt));
		  var selindex= elmt.selectedIndex;
		  alert(""+selindex);
		  if(selindex=="0")
			  {
			   alert("inside");
			    msgStr ="Please select new sequence value!";
				alert(msgStr);
				return false;									
			  }	
		alert("i:::::::::"+i);	  		  			 			  			  				
		}
		return true;		
}

function validateDept(){
 			  var elmt =document.getElementsByName('departmentCode')[0];
 			  var selindex= elmt.selectedIndex;
			  alert(""+selindex);
			  if(selindex=="0")
			  {
			    alert("inside");
			    msgStr ="Please select Department code!";
				alert(msgStr);
				return false;									
			  }
			  else
			  return true;
}	

function validateWeekDay(){
 			  var elmt =document.getElementsByName('weekDayCode')[0];
 			  var selindex= elmt.selectedIndex;
			  alert(""+selindex);
			  if(selindex=="0")
			  {
			    alert("inside");
			    msgStr ="Please select weekDay!";
				alert(msgStr);
				return false;									
			  }
			  else
			  return true;
}	
	

function validateDeptCombo(mode){	
          if(validateWeekDay){
			 if(validateDept()){			     
				 submitpage(mode);	
				 }
		 }				 
}

function validateWeekDayCombo(mode){	
			 if(validateWeekDay())			     
				 submitpage(mode);	
}

function validateSequence(mode){

	   	 if(validateDept()){
		     if(validateDept()){
			        submitpage(mode);	 			 
			      }				 
			 }
}


function validateHosp(){
 			  var elmt =document.getElementsByName('patRefGnctdHospitalCode')[0];
 			  var selindex= elmt.selectedIndex;
			  alert("hosp sel index"+selindex);
			  if(selindex=="0")
			  {
			   alert("inside");
			    msgStr ="Please select Hospital Code!";
				alert(msgStr);
				return false;									
			  }
			   else
			  return true;
			    				  			 			  			  				
}

function validateUnit(){
 			  var elmt =document.getElementsByName('unitCode')[0];
 			  var selindex= elmt.selectedIndex;
			  alert(""+selindex);
			  if(selindex=="-1"){
			   alert("inside");
			    msgStr ="Please select values from combo!";
				alert(msgStr);
				return false;									
			  }
			  else
			  return true;
}

function submitpage(mode){
		document.getElementsByName("sequenceMode")[0].value=mode;
		document.forms[0].submit();
		}	
		
function finalSave(mode)
{
	   	 if(validateWeekDay()){
		     if(validateDept())
			      {
    			     if(validateSequenceCombos()){
  	    			     submitpage(mode);	 			     			     
    			     }			        
			      }
			      }				 
	
}

</script>
<link href="../hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/Color.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/master.css" rel="stylesheet" type="text/css">
</head>
<body>
<html:form action ="/masterWorkshop/rosterSequence">
<table width="80%" align="center">
       <tr>
	   <td align="right">
	   <img src='<his:path src="/hisglobal/images/e_sushrut_header.gif"/>'> 
	   </td>		
			<td height="19" valign="bottom">
				<div align="right">
					<a href="/startup/cnt_login.jsp?loginMode=LOGOUT&uid=null">
						<img src='<his:path src="/hisglobal/images/btn-logout.png"/>' border="0">
					</a>
				</div>
			</td>
		</tr>
	</table>
<table width="80%" align="center">
    <tr>
      <td colspan="4"  class = "header">Roster Master Sequence</td>
  </tr>  
  <tr>
       <td width="15%" class="tdfonthead">
		       <div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
           			 <bean:message key="weekday"/></font></div>           			
	   </td> 		     
	   <td width="25%" class="tdfont" >
	        	<html:select name="GenerateRosterSequenceFB"  property="weekDayCode"  tabindex="0"  styleClass ="regcbo" onchange="validateWeekDayCombo('GETDEPT')">   
			       	    <html:option value="-1">Select Value</html:option>
					    <html:options collection ="<%=Config.ESSENTIAL_BO_WEEKDAY%>" property = "value" labelProperty = "label"/>
       	  		</html:select>	
      </td> 

     <td width="15%" class="tdfonthead">
		       <div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
           			 <bean:message key="department"/></font></div>           			
		     </td> 		     
     <td width="25%" class="tdfont">
		        	<html:select name="GenerateRosterSequenceFB"  property="departmentCode"  tabindex="0"  styleClass ="regcbo" onchange="validateDeptCombo('GETUNITSEQUENCE')">   
			       	    <html:option value="-1">Select Value</html:option>
					    <html:options collection ="<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
        	  		</html:select>	
  	  </td>
</tr> 
</table>
<table width="80%" align="center">
 
<logic:notEmpty name="<%=Config.ROSTER_SEQUENCE_DETAILS_ARR%>"> 
 <tr>
      <td colspan="5"  class = "header">Sequence Details</td>
  </tr> 
<tr>
		<td class ="tdfonthead"><b>Unit Name</b></td>
		<td class ="tdfonthead"><b>Start Time</b></td>
		<td class ="tdfonthead"><b>End Time</b></td>
		<td class ="tdfonthead"><b>Old Sequence Number</b></td>
		<td class ="tdfonthead"><b>New Sequence Number</b></td>	
</tr>   

  <%
		RosterMasterVO[] rstMasterVO=  (RosterMasterVO[])session.getAttribute(Config.ROSTER_SEQUENCE_DETAILS_ARR);

    for(int i=0;i<rstMasterVO.length;i++){    	  
    	System.out.println("INSIDE JSP::::");
    	System.out.println("INSIDE "+i);
    	
  %>	  
	<tr> 
    <td  class="tdfonthead">
     <div align="center">
    	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">    	     	         
    	  <%=rstMasterVO[i].getDepartmentUnit()%>
    	  </font>
	 </div>    
    </td>	  
    
     <td  class="tdfonthead">
     <div align="center">
    	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">    	     	         
    	  <%=rstMasterVO[i].getInactiveFromDate()%>
    	  </font>
	 </div>    
    </td>	  
    
     <td class="tdfonthead">
     <div align="center">
    	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">    	     	         
    	  <%=rstMasterVO[i].getInactiveTillDate()%>
    	  </font>
	 </div>    
    </td>	  
    
   <td   class="tdfonthead">   
    <font color="#000000" size="2" face="Verdana, Arial,Helvetica, sans-serif">    	     	         
    	  <%=rstMasterVO[i].getUnitSequenceNo()  %>
    	  </font>
   </td>
    <td   class="tdfonthead"> 
   		<html:select name="GenerateRosterSequenceFB"  property="newSequenceNo"  tabindex="0"  styleClass ="regcbo">   
	       	<html:option value="-1">Select Value</html:option>
			<html:options collection ="<%=Config.ROSTER_SEQUENCE_LIST%>" property = "value" labelProperty = "label"/>
       </html:select>	
   </td>
 </tr>
 <%
  }
 %>
 
 </logic:notEmpty>
  <tr>
        <td  colspan="5" class="addtoolbar" style="border-top:outset 2px black; border-bottom:inset 2px black">	
          <div align="center">
          <logic:notEmpty name="<%=Config.ROSTER_SEQUENCE_DETAILS_ARR%>"> 
		        <a style=cursor:pointer><img src='<his:path src="/hisglobal/images/btn-sv.png"/>' class="link"  tabindex="1"  onClick="finalSave('SAVE')" /></a>
         </logic:notEmpty>
	            <a style=cursor:pointer><img src='<his:path src="/hisglobal/images/btn-ccl.png"/>' width="60" height="20" tabindex="0" onKeyPress="if(window.event.keyCode==13)submitpage('CANCEL');" onClick='submitpage("CANCEL");'/></a>
             </div>    
	     </td>      
	 
 </tr> 
</table>
<input type ="hidden" name = "sequenceMode" value = "">	
</html:form>
<center><b><his:status/></b></center>
</body>
</html>