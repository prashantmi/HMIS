
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="hisglobal.vo.*"  %>
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

<script language="JavaScript" type="text/JavaScript" src="/hisglobal/js/Validation.js"></script>

<script language="JavaScript" src="/hisglobal/js/functionality.js"></script>
<script language ="javascript"><!--

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

function validateDeptCombo(mode){
//	 if(validateHosp())
	//   {
			 if(validateDept()){			     
				 submitpage(mode);	 			 
		//	 }
		 } 
}

function validateSequence(mode){

//if(validateHosp())
	//   {
	   	 if(validateDept()){
		     if(validateUnit())
			      {
			        submitpage(mode);	 			 
			      }
				 
			 }
		// } 	 
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
			  if(selindex=="-1")
			  {
			   alert("inside");
			    msgStr ="Please select values from combo!";
				alert(msgStr);
				return false;									
			  }
			  else
			  return true;
}

function submitpage(mode){
alert("sdfsdf");
		document.getElementsByName("sequenceMode")[0].value=mode;
		alert("sdfsdf");
		document.forms[0].submit();
		}	
		
/*function validateNewSequenceList(){		
 
	var elmt =document.getElementsByName('newSequenceNo');
    var len=elmt.length;
    alert("length"+len);
    arrSelection=new Array();
     for(i=0;i<len;i++ )
		{
		  var elemt =document.getElementsByName('newSequenceNo')[i];
		  alert(typeof(elmt));
		  var val= elmt.value;
		  alert("val"+val);
		  for(i=0;i<arrSelection.length;i++){
		  if(val=arrSelection[i])
		    return false;		  
		  }		  		  	  					
		}	
		return true;		
}*/
		
function finalSave(mode)
{
 
	   	 if(validateDept()){
		     if(validateUnit())
			      {
    			     if(validateSequenceCombos()){	    			    
  	    			     submitpage(mode);	 			     			     
    			     }			        
			      }				 
			 }
		
}

--></script>
<link href="../hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/Color.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/master.css" rel="stylesheet" type="text/css">
</head>
<body>
<html:form action ="/masterWorkshop/sequence">
<table width="80%" align="center">
  <tr>
    <td colspan="4"  class = "header"> <div align="left">Department Unit Room Master>>> Sequence </div></td>
  </tr>  
<tr> 
		     <td width="15%" class="tdfonthead">
		       <div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
           			 <bean:message key="department"/></font>
           	   </div>           			
		     </td> 
		     
		     <td width = "25%" class="tdfont" >
		        	<html:select name="GenrateSequenceFB"  property="departmentCode"  tabindex="0"  styleClass ="regcbo" onchange="validateDeptCombo('GETUNIT')">   
			       	    <html:option value="-1">Select Value</html:option>
					    <html:options collection ="<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
        	  		</html:select>	
    		</td>
		     <td width="15%" class="tdfonthead">
		       <div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       
           			 <bean:message key="unit"/></font></div>
		     </td> 

		     <td  width="25%" class="tdfont"> 
		    		<html:select name="GenrateSequenceFB"  property="unitCode"  tabindex="0"  styleClass ="regcbo" onchange="validateSequence('GETSEQUENCE')">   
			       	<html:option value="-1">Select Value</html:option>
					<html:options collection ="<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT%>" property = "value" labelProperty = "label"/>
        	  </html:select>	
</td>
</tr>
</table>
<table width="80%" align="center">

 <logic:notEmpty name="<%=Config.SEQUENCE_DETAILS_ARR%>">
<tr>
    <td colspan="3"  class = "header"> <div align="left">Sequence Details</div></td>
</tr>

<tr>
		<td class ="tdfonthead"><b>Room No</b></td>
		<td class ="tdfonthead"><b>Existing Sequence Number</b></td>
		<td class ="tdfonthead"><b>New Sequence Number</b></td>	
</tr>     
  <%
		RoomMasterVO[] rmMasterVO=  (RoomMasterVO[])session.getAttribute(Config.SEQUENCE_DETAILS_ARR);

    for(int i=0;i<rmMasterVO.length;i++){    	  
  %>	  
	<tr> 
    <td class="tdfonthead">
     <div align="left">
    	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">    	     	         
    	  <%=rmMasterVO[i].getRoomCode()%>
    	  </font>
	 </div>    
    </td>	  
   <td  class="tdfonthead">   
    <font color="#000000" size="2" face="Verdana, Arial,Helvetica, sans-serif">    	     	         
    	  <%=rmMasterVO[i].getSequenceNo() %>
   	  </font>
   </td>
    <td class="tdfonthead"> 
   		<html:select name="GenrateSequenceFB"  property="newSequenceNo"  tabindex="0"  styleClass ="regcbo">   
	       	<html:option value="-1">Select Value</html:option>
            <span class="tdfonthead">
            
            
            </span>	
            
            <html:options collection ="<%=Config.SEQUENCE_LIST%>" property = "value" labelProperty = "label"/>
       </html:select>	
   </td>
 </tr>
 <%
  }
 %>
 
 </logic:notEmpty>
  <tr>
      <td  colspan="3" class="addtoolbar" style="border-top:outset 2px black; border-bottom:inset 2px black">	
          <div align="center">
          <logic:notEmpty name="<%=Config.SEQUENCE_DETAILS_ARR%>">
		  	 <a style=cursor:hand><img src="<his:path src='/hisglobal/images/btn-sv.png'/>"   class="link"  tabindex="1"  onKeyPress="if(window.event.keyCode==13)finalSave('SAVE');" onClick="finalSave('SAVE')"></a>
       	  </logic:notEmpty>
	      <a style=cursor:hand><img src="<his:path src='/hisglobal/images/btn-ccl.png'/>" class="link"  tabindex="1"  onKeyPress="if(window.event.keyCode==13)submitpage('CANCEL');" onClick='submitpage("CANCEL");'/>
	      </a>
	      </div>
	  </td>          
	 
 </tr> 
</table>
<input type ="hidden" name = "sequenceMode" value = "">
</html:form>
<center><b><his:status/></b></center>
</body>
</html>