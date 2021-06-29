<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

var executionFlag=false;

function selectAllCheckboxes()
  {
  	var selectAllCheckbox = document.getElementsByName("selectedPatCrNo");
  	
  	
  	if(document.getElementsByName('selectCheck')[0].checked)
  	{
  	for(var i=0;i<selectAllCheckbox.length;i++)
	{
		//alert("inside check")
		document.getElementsByName('selectedPatCrNo')[i].checked=true;
		
	}
	}
	else
	{
	for(var i=0;i<selectAllCheckbox.length;i++)
	{
		//alert("inside check")
		document.getElementsByName('selectedPatCrNo')[i].checked=false;
		
	}
	}
  
  } 
  
  function callThisOnload()
{
	alert("inside onload")
		if(document.getElementsByName("onCallDetailFlag")[0].value==<%=InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_FLAG_NO%>)
		{
			window.close();
		}
}
  
  function validateSave()
{
	var chks = document.getElementsByName('selectedPatCrNo');
	var flag = false;
	for(var i=0;i<chks.length;i++)
		if(chks[i].checked)
		{
			flag = true;
			break;
		}
	if(!flag)
	{
		alert("Please Select At least One Record ");
		chks[i].focus();
		return false;
	} 
	else
	{
		//submitForm("SAVECALLDETAILS");
		document.getElementsByName("hmode")[0].value="SAVECALLDETAILS";
		// alert("hmode"+document.getElementsByName('hmode')[0].value);
		executionFlag=true;
		document.forms[0].submit();
		
		window.close();
	}
}

function submitCancel()
{
	// document.getElementsByName("roundBy")[0].value="-1";
	// alert("inside submit")
	// opener.document.getElementsByName("roundType")[0].value="-1";
	window.close();
}

function beforeOnLoad()
{
	// alert("inside onload")
	if(executionFlag==false)
	{
	opener.document.getElementsByName("roundType")[0].value="-1";
	}

}

</script>

<body onunload="beforeOnLoad()">
<html:form action="/doctorWardRoundDetail">
<his:TitleTag>
	<his:name>
		<bean:message key="doctorCallDetail" />
	</his:name>
</his:TitleTag>

 
	<his:ContentTag>
      <table width="100%" cellpadding="0" cellspacing="1">
                  
                <tr>
                   
                   <td width="5%" class="tdfonthead">
                   		 <div align="center">
                   		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                   		<b>
                   		<html:checkbox  name="DoctorWardRoundDetail" property="selectCheck" onclick="selectAllCheckboxes()"></html:checkbox>
                   		</b>
                    	</font>
                    	</div>
                    </td>
					<td width="15%" class="tdfonthead">
                   		 <div align="center">
                   		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                   		 <b>
                   		 <bean:message key="callRaisedDate" />
                   		 </b> 
                    	</font>
                    	</div>
                    </td>
                                     
                  	<td width="10%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="callRaiseTime" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   		
                   			<td width="15%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="crNo" />
                        </b> 
                        </font>
              	        </div>
               	    </td>  
                   
                   	<td width="15%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="admNo" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   	
                   		
                   		<td width="15%" class="tdfonthead">
                     	<div align="center">
                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                        <b>
                        <bean:message key="name" />
                        </b> 
                        </font>
                        </div>
                   		</td>
                   		
                   		  
             </tr>
                	<logic:present name="<%=InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_VOARRAY%>">
                 <logic:iterate id="callDtlVOs" name="<%=InpatientConfig.DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_VOARRAY%>" indexId="id" type="hisglobal.vo.DoctorCallBookVO" >
                 <tr>
					
					
					<td width="5%" class="tdfont">
						<div align="center">
						<html:checkbox name="DoctorWardRoundDetail" property="selectedPatCrNo" value="<%=id.toString() %>"></html:checkbox>
						</div>
					</td>
					
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					  
					        <%=callDtlVOs.getCallDate() %>
						  </font>
					     </div>   
					</td>
					
										
					<td width="10%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					         <%=callDtlVOs.getCallRaiseTime() %>
					 
                      	 </font>
					     </div>   
					</td>
					
					
					
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getPatCrNo() %>
                      	 </font>
					     </div>   
					</td>
					
					<td width="15%" class="tdfont">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <%=callDtlVOs.getPatAdmnNo() %>
                      	 </font>
					     </div>   
					</td>
					
				
					<td width="15%" class="tdfont" nowrap="nowrap">
					  	 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      <%=callDtlVOs.getPatientName() %>
					     </font>
					     </div>   
					</td>
				</tr>
                </logic:iterate>
               </logic:present>
          
          
      </table>
      </his:ContentTag>
  
	
	
	
	<his:ButtonToolBarTag>
		<img class='button' src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validateSave();" onkeypress="if(event.keyCode==13)validateSave();")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitCancel()" onkeypress="if(event.keyCode==13) submitCancel()">
        
	</his:ButtonToolBarTag>
	
	
<his:status/>
<html:hidden name="DoctorWardRoundDetail" property="hmode"/>
<html:hidden name="DoctorWardRoundDetail" property="patCrNo"/>
<html:hidden name="DoctorWardRoundDetail" property="wardCode"/>
<html:hidden name="DoctorWardRoundDetail" property="roundBy"/>
<html:hidden name="DoctorWardRoundDetail" property="onCallDetailFlag"/>

</html:form>

</body>