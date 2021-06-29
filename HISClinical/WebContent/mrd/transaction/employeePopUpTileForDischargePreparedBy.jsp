<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import ="registration.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="enquiry.enquiryConfig"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>



<script>


function clearForm(){
	
	document.getElementsByName("str_first_name")[0].value="";
	document.getElementsByName("str_middle_name")[0].value="";
	document.getElementsByName("str_last_name")[0].value="";
	document.getElementsByName("num_desig_id")[0].value="-1";

}	


function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}

function populateSelected(obj){

	var deptemployee = document.getElementsByName('gnum_dept_code')[0].value;
	var fieldName=document.getElementsByName('fieldToPopulate')[0].value
	var fieldNameArray=fieldName.split("@");
	var fieldIndex=document.getElementsByName('index')[0].value
	//alert(fieldIndex)
	//alert("fieldNameArray"+fieldNameArray)
	var objValueArray=(obj.value).split("#")
//	alert(objValueArray)
	var openerDocument;
	//alert("emp" + deptemployee);
	//alert("dept"+deptname);
/* 	if(deptname != null && deptname != "")
		{
			if(deptemployee == deptname)
				{
				alert("matched");
				 */
	//				alert("fieldNameArray.length="+fieldNameArray.length)
					for(i=0;i<fieldNameArray.length;i++)
					{
					//	alert("fieldNameArray"+[i]+fieldNameArray[i])
						openerDocument=opener.document.getElementsByName(fieldNameArray[i])[fieldIndex]
						//alert("objValueArray[i].value"+objValueArray[i])
						//alert(opener.document.getElementsByName(fieldNameArray[i])[fieldIndex])
						openerDocument.value=objValueArray[i]
						//alert("openerDocument.value="+openerDocument.value)
					}
					
					self.close();
				//}
	/* 		else
				{
					alert("department of requested by doesnot match with department of case sheets");
					return false;
				}
		} */
/* 	else
		{
			
			for(i=0;i<fieldNameArray.length;i++)
			{
			//	alert("fieldNameArray"+[i]+fieldNameArray[i])
				openerDocument=opener.document.getElementsByName(fieldNameArray[i])[fieldIndex]
				//alert("objValueArray[i].value"+objValueArray[i])
				//alert(opener.document.getElementsByName(fieldNameArray[i])[fieldIndex])
				openerDocument.value=objValueArray[i]
				//alert("openerDocument.value="+openerDocument.value)
			}
			
			self.close();
		} */
}
</script>

<html:form action="/employeePopUp">



<%
	boolean varIsNewStatus=false;	
	String varStatus="";
%>

<his:statusNew>
<%varIsNewStatus=true;
varStatus="New";%>	
</his:statusNew>
<%  String msg=""; %>
 <his:TitleTag>
    <his:name>
         <bean:message key="searchEmployee"/>
     </his:name>
       
 </his:TitleTag>


<his:ContentTag>
<table  width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
			<td  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="fname"/></b></font></div>
	  		</td>
	  		
	  		
	  		<td   class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="empCode"/></b></font></div>
	  		</td>
	  		
	  		<td   class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="oldEmpCode"/></b></font></div>
	  		</td>
	  		
	  		<td class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="designation"/></b></font></div>
	  		</td>
	  		
	  	</tr>
		<tr>
		<td  class="tdfont">
	  			<div align="center">
				<html:text name="employeePopUp"  maxlength="32" size="30" property="str_emp_full_name" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  			<div align="center">
				<html:text name="employeePopUp"  maxlength="32" size="30" property="str_emp_no" styleClass ="textbox" tabindex="1" />
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  			<div align="center">
				<html:text name="employeePopUp"  maxlength="32" size="30" property="str_old_emp_no" styleClass ="textbox" tabindex="1" />
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  			<div align="center">
	  			<html:select name="employeePopUp" tabindex="1" property="gnum_desig_code" styleClass="regcbo" >
					<html:option value="%">All</html:option>
					<logic:present name="<%=MrdConfig.ESSENTIAL_OPTION_DESIGNATION %>">
  					<html:options  collection="<%=MrdConfig.ESSENTIAL_OPTION_DESIGNATION%>" property="value" labelProperty="label" />
  					</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
		</tr>
	
	</table>
	</his:ContentTag>



<his:statusTransactionInProcess>
 <his:SubTitleTag name="Search Result">
 </his:SubTitleTag>
 <his:ContentTag>
 	<table width="100%"  cellspacing="1" cellpadding="0">                    
		 <tr>
		 	<td  class="tdfonthead" width="5%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	           	<b><bean:message key="select"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	           	<b><bean:message key="empNo"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="name"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="department"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="designation"/></b>
	            </font>
	            </div>
             </td>
                    
             <td  class="tdfonthead" width="5%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	           	 <b><bean:message key="gender/age"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="location"/></b>
	            </font>
	            </div>
             </td>
            </tr> 
 		 <logic:iterate id="staffEnqVO" name="<%=MrdConfig.EMPLOYEE_ENQUIRY_VO %>" type="hisglobal.vo.StaffDetailVO">
 			<tr>
 			<td class="tdfont">
	  			<div align="center">
	  				<html:radio name="employeePopUp" property="selectedEmployee" 
	  				value='<%=staffEnqVO.getStr_emp_no()+"#"+staffEnqVO.getStr_emp_full_name() +"#"+ staffEnqVO.getGnum_dept_code()%>'
	  				onclick="populateSelected(this)" tabindex="1"></html:radio>	
	  			</div>
	  		</td>
 			<td class="tdfont">
	  			<div align="center">
	  				<bean:write name="staffEnqVO" property="str_emp_no"/>	
	  			</div>
	  		</td>
	  		<td class="tdfont"> 
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="str_emp_full_name"/>	
	  			<%-- <bean:write name="staffEnqVO" property="str_middle_name"/>	
	  			<bean:write name="staffEnqVO" property="str_last_name"/>	 --%>
	  			</div>
	  		</td>
            <td class="tdfont">
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="gnum_dept_code"/>
	  			<input type="hidden" name="gnum_dept_code" value="<%=staffEnqVO.getGnum_dept_code()==null?"-":staffEnqVO.getGnum_dept_code()%>">
									
	  			</div>
	  		</td>
            <td class="tdfont">
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="gnum_desig_code"/>	
	  			</div>
	  		</td>
	  		 <td class="tdfont">
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="gstr_gender_code"/>/	
	  			<bean:write name="staffEnqVO" property="age"/>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  				<bean:write name="staffEnqVO" property="hgnum_location_code"/>	
	  			</div>
	  		</td>
	  	 </tr>
 		
 		</logic:iterate>
 		
 	</table>
 </his:ContentTag>
 
 </his:statusTransactionInProcess>


   <his:ButtonToolBarTag>         
         <div align="center">	

           	<img class="button" style="cursor:pointer" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-search.png"/>' alt="Search" title="Search" onclick="submitPage('SEARCH');" onkeypress="if(event.keyCode==13) submitPage('SEARCH');" tabindex="1">	 
     		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW');">
	        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
         </div>
   </his:ButtonToolBarTag>
  
<html:hidden name="employeePopUp" property="hmode" />
<html:hidden name="employeePopUp" property="fieldToPopulate" />
<html:hidden name="employeePopUp" property="index" />

<html:hidden name="employeePopUp" property="deptname" />
<his:status/>
  </html:form>
</html>