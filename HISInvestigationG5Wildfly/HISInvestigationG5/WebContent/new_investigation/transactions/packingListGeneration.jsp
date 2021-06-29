<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.SampleAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_SampleCollectionVO"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="new_investigation.transactions.controller.fb.PackingListGenerationFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>


<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 <his:css src="/hisglobal/css/drop.css" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	 
	 
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />


<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>



<script type="text/javascript">
        var isChecked = false;
        function allSelected() 
        {
        	
        	// this line is for toggle the check
            isChecked = !isChecked;
        	
        	if(isChecked)
        		 document.getElementById("nextDiv").style.display="";
        	else
        		 document.getElementById("nextDiv").style.display="none";
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').attr('checked',isChecked);
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        }
    </script>
    
    
<style>
.textBoxCss {
    background: white;
    color: #135d8c;
    width: 180px;
    padding: 4px 10px 4px 15px;
    border-radius: 20px;
    box-shadow: 0 1px 0 #ccc inset;
    transition: 500ms all ease;
    outline: 0;
}

 

</style>


<% String strdivage="\"\"";
String strdivdob="\"\""; %>

 <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              
              
              String frDate="";
              String tDate="";
               
         %>


<body onload="showSearchDivOnLoad();dateValueCheck();">

<html:form action="/packingListGeneration">


  
 <his:TitleTag name="Packing List Generation"> 	 
 <%--  <his:insertDateTag/> --%>
  </his:TitleTag> 
<%
		 String mobileNo="";
  		 String emailId="";
  		String patAddress="";
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%>  
 
 
 
  
 	<logic:equal name="PackingListGenerationFB" property="showStatus" value="0">
		  <his:SubTitleTag name="Search Criteria Details"
		  
		  
          
		  >
		  
		          <div align="right" style="margin-right: 35px;">
           
            
            <input type="radio"  name="areaa" onclick="showdata(this,'area1','machine1')">Area Wise        
            <input type="radio"  name="areaa" onclick="showdata(this,'machine1','area1')">Machine Wise        
               
                  </div>
		  
		  
		  </
		  </his:SubTitleTag>
 		<his:ContentTag>
	 		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
				 		<table width="100%" >
				 			
				 			<logic:equal name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
				 			<tr>
					 			<td class="tdfont" width="25%" colspan="4">
					 				<div align="center">
					 				<logic:notEqual name="PackingListGenerationFB" property="packingListGenerationType" value="<%=InvestigationConfig.PACKING_LIST_GENERATION_TYPE_DUPLICATE%>">
					 					<input type="radio" name="packingListGenerationType" value="0" onclick="onClickGenerationType(this)" checked="checked"><bean:message key="generatePackListSamples"/>
					 					<input type="radio" name="packingListGenerationType" value="1" onclick="onClickGenerationType(this)"><bean:message key="generateDuplicatePackList"/>
					 				</logic:notEqual>
					 				<logic:equal name="PackingListGenerationFB" property="packingListGenerationType" value="<%=InvestigationConfig.PACKING_LIST_GENERATION_TYPE_DUPLICATE%>">
					 					<input type="radio" name="packingListGenerationType" value="0" onclick="onClickGenerationType(this)"><bean:message key="generatePackListSamples"/>
					 					<input type="radio" name="packingListGenerationType" value="1" onclick="onClickGenerationType(this)" checked="checked"><bean:message key="generateDuplicatePackList"/>
					 				</logic:equal>
					 				</div>
					 			</td>
				 			 </tr>	
				 			 </logic:equal>
				 			<tr id="area1">
					 			<td class="tdfont" width="25%">
					 				<div align="right">
					 				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:message key="sampleColl"/>
													 </font>
					 					
					 				</div>
					 			</td>
					 			<td class="tdfonthead" width="25%" >
					 			<div align="left">
					 				<logic:notEqual name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
						 				
						 				  <span class="custom-dropdown small">
						 				<html:select name="PackingListGenerationFB" property="sampleAreaCode" tabindex="1" onchange="showSearchDiv(this)">					
											<bean:define id="patPakList" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
										
										<%if(patPakList.size()>1){ %>
	<html:option value="-1">Select Value</html:option>									<%} %>
										
										
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label"  />
										</html:select> 
										</span>
									</logic:notEqual>
									 <logic:equal name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
									 	<div align="left">
					 					  <bean:write name="PackingListGenerationFB" property="sampleAreaName"/>
					 					  <html:hidden name="PackingListGenerationFB" property="sampleAreaCode"/>
					 					</div>
									 </logic:equal>
									 </div>
					 			</td>
					 			<td class="tdfont" width="25%"></td>
					 			<td class="tdfonthead" width="25%"></td>
				 			 </tr>	
				 			
				 			 <tr id="machine1" >
					 			<td class="tdfont" width="25%">
					 				<div align="right">
					 				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
															Machine Wise
													 </font>
					 					
					 				</div>
					 			</td>
					 			<td class="tdfonthead" width="25%" >
					 			<div align="left">
					 				<logic:notEqual name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
						 				
						 				  <span class="custom-dropdown small">
						 				<html:select name="PackingListGenerationFB" property="machineCode" tabindex="1" onchange="showSearchDiv(this)">					
											<bean:define id="patPakList" type="java.util.List" name="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>"></bean:define>
										
										<%if(patPakList.size()>1){ %>
										<html:option value="-1">Select Value</html:option>	
										<html:options collection="<%=InvestigationConfig.MACHINE_LIST_ACCEPTANCE%>" property="value" labelProperty="label"  />								
										<%} else{%>
									
										<html:option value="-1">Select Value</html:option>	
																	
										<%} %>
										</html:select> 
										</span>
										
									</logic:notEqual>
									 <logic:equal name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
									 	<div align="left">
					 					 <%--  <bean:write name="PackingListGenerationFB" property="sampleAreaName"/> --%>
					 					  <bean:write name="PackingListGenerationFB" property="machineCodeName"/>
					 					  <html:hidden name="PackingListGenerationFB" property="machineCode"/>
					 					</div>
									 </logic:equal>
									 </div>
					 			</td>
					 			<td class="tdfont" width="25%"></td>
					 			<td class="tdfonthead" width="25%"></td>
				 			 </tr>	
				 			  <logic:notEqual name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
				 			<his:ButtonToolBarTag>
						<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick ="cancelFunc();">
						</his:ButtonToolBarTag>
						</logic:notEqual>
				 			 </table>
				 			 
				 			 <logic:equal name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
						 			 <table width="100%" >
						 			 
						 			  		<tr>
										    <td width="25%" class="tdfont">
										        <div align="right">
													 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:message key="LaboratoryName"/>
													 </font>
											     </div>
										      </td>
										     <td class="tdfonthead">
										 			<div align="left">
										 			  <span class="custom-dropdown small">
						 								<html:select name="PackingListGenerationFB" property="labCode"  tabindex="1"  >
						       								<html:option value="%">All</html:option>
												 				 <logic:present name="<%=InvestigationConfig.LIST_LABORATORY_COMBO%>">
											 	   								<html:options collection="<%=InvestigationConfig.LIST_LABORATORY_COMBO%>" property="value" labelProperty="label"/>
											  					</logic:present>
											  					
						      							</html:select>
										 		</span>
										 			</div>
										 			
										 			</td>
										     <td width="25%" class="tdfont">
										         
										      </td>
										      <td width="25%" class="tdfonthead">
										       
										     </td>
										     </tr>
						 			 
											<tr>            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="fromDate"/>
														</font>
									        		</div>
									        		
												</td>
												<td class="tdfonthead" width="25%">
										    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
														<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
													</div>
											 		
												</td>
									 		            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="toDate"/>
														</font>
									        		</div>
												</td>
												
												<td class="tdfonthead" width="25%">
										    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
														<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
													</div>
											 		
												</td>
								 		</tr>
								 		<tr>
			   <td   align="center" colspan="4" width="25%">
				 
				  <div align="center" >
				 	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gobtn1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) showPatList()" onclick="showPatList()" tabindex="1">
				 		
				 
				 	<!--start-- back button when multiple sample collection areas present and you have to go back to combo list -->
				 		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
				 		<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
						<%if(patSampleCollection.size()>1){ %>
									<img class="button" src='<his:path src="/hisglobal/images/back_tab.png"/>' id="saveDiv"  tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
						<%} %>
						</logic:present>
					<!--end--   back button when multiple sample collection areas present and you have to go back to combo list -->
			
			 </div>
				
			
			  </td>
			 </tr>  
								 		 
								 		
								</table>
						</logic:equal>
				</logic:present>
		</his:ContentTag>
			</logic:equal>
	<his:statusTransactionInProcess>
	<his:ContentTag>
	
	
	
		<logic:equal name="PackingListGenerationFB" property="packingListGenerationType" value="0">
	
	<logic:equal name="PackingListGenerationFB" property="showStatus" value="0">
					<logic:present name="<%=InvestigationConfig.LIST_PACKINGLIST_PATIENT_VO %>">
		
			 <%
					//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((PackingListGenerationFB)request.getAttribute("PackingListGenerationFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.SAMPLEBASED_LIST_PACKINGLIST_PATIENT_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
		 %>
		 
		 
		  <his:PaginationTag name="fbPagination"></his:PaginationTag>
			
		
			<his:SubTitleTag name="Packing List Details"></</his:SubTitleTag>
  			
  			
  			
		 
	 		
	 		
	 		
			<table   width= "100%" bgcolor="#EBEBEB"  >
				<tr>
			
					<td width="10%" align="left">	
						<b><font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="select"/>&nbsp;</font></b>
					</td>
					<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="labName"/>&nbsp;</font></b>
					</td>
				<%-- 	<td width="10%" align="left">
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="reqNo"/>&nbsp; </b></font>
					</td> --%>
					<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="TestName"/>&nbsp;</font></b>
					</td>
					<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="SampleName"/>&nbsp;
</font></b>
					</td>	
						<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="dailySampleNo"/></font></b>
					</td>
					<td width="15%" align="left">
					<b> 
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="sampleCollDateTime"/></font></b>
					</td>
			
				</tr>
			
				<logic:notEmpty name="<%=InvestigationConfig.LIST_PACKINGLIST_PATIENT_VO %>">
			
			<%
					 List<Inv_SampleCollectionVO> lstSamCVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.SAMPLEBASED_LIST_PACKINGLIST_PATIENT_VO);
			 		String sampleNo="";
			 		
			 		 List chksampleNo=new ArrayList();
			 		 
			 		String group="";
			 		
			 		 List chkgroupno=new ArrayList();
			 		 
			 		 boolean flag=true;
					 int  size=0;
			 		
			 		if(lstSamCVO!=null && lstSamCVO.size()>0 )
			 			size=lstSamCVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
				//	int startIndex=0;
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
				//	int endIndex=13;
										for(int j=startIndex;j<=endIndex;j++)
					{
											 String[] valuearr=new String[lstSamCVO.size()];
											 String[] testName=new String[lstSamCVO.size()];
	
						
						  //int i=j-startIndex;
						String chkvalue1="";
					if(j<size)
									{ 
						Inv_SampleCollectionVO voSam=lstSamCVO.get(j);
						int val=0;
						String test1="";
						String test2="";
						String [] vall=new String[lstSamCVO.size()];
					/* 	chandan test concat
					
					for(int kk=0;kk<lstSamCVO.size();kk++)
						{
							Inv_SampleCollectionVO voSamm=lstSamCVO.get(kk);
							 test1=voSamm.getTestName();
							 StringBuffer testname=new StringBuffer();
							 StringBuffer sb=new StringBuffer();
							 for(int k=kk+1;k<lstSamCVO.size();k++)
						{
							
							
							Inv_SampleCollectionVO voSamTemp=lstSamCVO.get(k);
						
							if(voSamTemp.getTempSampleNo().equals(voSamm.getTempSampleNo())  && voSamTemp.getLabCode().equals(voSamm.getLabCode()))
									{
										sb.append(k+"#");
								        vall[kk]=sb.toString();
									//	System.out.println(sb.toString());
										  testname.append(","); 
								         testname.append(voSamTemp.getTestName()); 
								    
								
								         testName[kk]=testname.toString();	
										
									}
							
								
						}
						} */
						
						
						
						
						/*
						for(int j5=startIndex;j5<=endIndex;j5++)
						{int k=0;
							List<String> chksampleNo1=new ArrayList();
						for(int j1=startIndex;j1<=endIndex;j1++)
						{
							String sampleNo1="";
					 		
					 		Inv_SampleCollectionVO voSam1=lstSamCVO.get(j1);
					 		 sampleNo1=voSam1.getTempSampleNo();
					 		System.out.println(sampleNo1+"chandan2");
					 		 if(!chksampleNo1.contains(sampleNo1))
					 		 {
					 			 chksampleNo1.add(sampleNo1);
					 			
					 		 }
					 		 else 
					 		 {
					 			StringBuffer sb=new StringBuffer();
					 			
					 			for(int j4=startIndex;j4<=endIndex;j4++)
								{
					 				
									//String sample=lstSamCVO.get(j).getTempSampleNo();
									if(lstSamCVO.get(j4).getTempSampleNo()==sampleNo1)
									{
										
						 			     val=j1;
							 			System.out.println(val+"chandan4");
							 			 String val1=Integer.toString(val);
							 			 
							 			System.out.println(val1+"chandan5");
							 			//int jj=j1-1;
							 			 sb.append(val1);
							 			 sb.append("#");
							 			 
							 			valuearr[k]=sb.toString();
							 			k++;
									}
								}		 
					 			break;
					 			}
								
						}	 
					 		 
						}*/
						
						//String sbToString=sb.toString();
						//System.out.println(sbToString+"chandan");
						
						
						sampleNo=voSam.getSampleNo();
						group=voSam.getGroupCode();
						
					if(group==null)
					{
						if(!chksampleNo.contains(sampleNo))
						{
							chksampleNo.add(sampleNo);
							flag=true;
							 //value=j;
						}
						else
						{
							flag=false;
							//value1=j;
						}
					}
					else
					{
						
						if(chksampleNo.contains(sampleNo) && chkgroupno.contains(group))
						{
							flag=false;
							//value=j;
						}
						else
						{
							chksampleNo.add(sampleNo);
							chkgroupno.add(group);

							flag=true;

							
							
							//value1=j;
						}
					}
						String chkvalue=Integer.toString(j);
						//String  valueToString=Integer.toString(value1);
						
						
						
						
						
						String chkVal=voSam.getSampleNo()+"#"+voSam.getRequisitionNo()+"#"+voSam.getLabCode()+"#"+voSam.getTestCode()+"#"+j;
				
						if(flag)
						{
							/* chandan code comment
							
							if(vall[j]!=null)
							 chkvalue1=chkvalue+"#"+vall[j];
							else
								chkvalue1=chkvalue+"#"; */
								
								chkvalue1=voSam.getChkval();	
								
					%>
					<tr>
					
						<td width="10%" align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<html:checkbox  name="PackingListGenerationFB" property="chkSamplePatient" value='<%=chkvalue1%>' onclick="onClickSave(this);"></html:checkbox>
							</font>
						</td>
						<td width="10%" align="left">
				  		<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><%=voSam.getLabName() %></font>	
				  		</td>
				  	<%-- 	<td width="10%" align="left">
						<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="arrSamplePatient" property="requisitionNo"/></font>
				  		</td> --%>
				  		
				  		<td width="10%" align="left">
				  		<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
						<%=voSam.getTestNamee()+(testName[j]==null?"":testName[j])  %>
														</font>
				  		</td>
				  		<td width="10%" align="left">
				  		<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><%=voSam.getSampleName() %> </font>
				  		</td>
				  		<td width="10%" align="left">
				  		<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><%=voSam.getTempSampleNo() %></font>	
				  		</td>
				  		<td width="15%" align="left">
				  		<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"><%=voSam.getSampleCollectionDate() %></font>
				  		</td>
					</tr>
					<%}}} %>
					</logic:notEmpty>
			
			</table>
			</logic:present>
			</logic:equal>
			</logic:equal>
			</his:ContentTag>
			
			
			
			
														<!-- for duplicate generation -->
			<his:ContentTag>
				<logic:equal name="PackingListGenerationFB" property="packingListGenerationType" value="1">
			
			
			 <%
					//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((PackingListGenerationFB)request.getAttribute("PackingListGenerationFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.MAP_PACK_LIST_DETAILS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
		 %>
			
			  <his:PaginationTag name="fbPagination"></his:PaginationTag>
			<logic:present name="<%=InvestigationConfig.MAP_PACK_LIST_DETAILS_VO %>">
			
		
			<his:SubTitleTag name="Packing List Details"></</his:SubTitleTag>
  			
  			
  			
  				<table   width= "100%" bgcolor="#EBEBEB"  >
				<tr>
			
					<td width="10%" align="left">	
						<b><font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">	<input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" />&nbsp;</font></b>
					</td>
					
					<td width="30%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="PackingListNo"/>&nbsp;</font></b>
					</td>
				
					<td width="30%" align="left">
					<b> 
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="GenerationDateTime"/></font></b>
					</td>
			
				<td width="30%" align="left">
					<b> 
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="labName"/></font></b>
					</td>
				</tr>
				
								<logic:notEmpty name="<%=InvestigationConfig.MAP_PACK_LIST_DETAILS_VO %>">
				
			<%
					Map<String,List<SampleAcceptanceVO>> _mp=(Map<String,List<SampleAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_PACK_LIST_DETAILS_VO);
						 int i,size=0;
						
						 Iterator itr=_mp.keySet().iterator();
						while(itr.hasNext())
						{
					boolean firstTimeTravesal=true;
					boolean samePackingListNo=false;
						String packingListNo=(String)itr.next();
						List<SampleAcceptanceVO> lstSampleAcceptanceVO=_mp.get(packingListNo);
					 		if(lstSampleAcceptanceVO!=null && lstSampleAcceptanceVO.size()>0 )
					 			size=lstSampleAcceptanceVO.size();
					 		if(size>1)
					 			samePackingListNo=true;
					 		for(int k=0;k<size;k++)
					 		{
					 			
					 			if(firstTimeTravesal)
					 			{
								SampleAcceptanceVO voSam=lstSampleAcceptanceVO.get(k);
									String chkVal=voSam.getPackingListNO();   %>
						 		<tr>
										<td width="10%" align="left">
							<input type="checkbox"  name="chkPackListData" class="jpCheckbox"  value='<%=chkVal%>' onclick="onClickSave(this);"></input>
										</td>
										<td width="30%" align="left">
										 <div align="left">
										 	<%=voSam.getPackingListNO() %> 
										 </div>
								  		</td>
								  		<td width="30%" align="left">
								  		<div align="left">
								  		<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
										 	<%=voSam.getPackingListDate() %></font>
										 </div>
								  		</td>
							  		   <td width="30%" align="left">
								  		<div align="left">
								  		<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">
										 	<%=voSam.getLabName() %></font>
										 </div>
								  		</td>
									</tr>
								 <%}
					 			 //logic to hide the vo for same packing list no
					 			 if(samePackingListNo)firstTimeTravesal=false;  // making second iteration false; 
					 			%>
							<% }  
					 	} %>
					 	
					 	
					</logic:notEmpty>
					
					
					
					
					
					
				
				
				
				</table>
			
				 </logic:present>
 				</logic:equal>
 				</his:ContentTag>
 </his:statusTransactionInProcess>
 
 
			<html:hidden name="PackingListGenerationFB" property="hmode" />
						<html:hidden name="PackingListGenerationFB" property="flagforipddesk" />
			
			
			<html:hidden name="PackingListGenerationFB" property="flag" />
			<html:hidden name="PackingListGenerationFB" property="selectedCheckbox" />
			 <html:hidden name="PackingListGenerationFB" property="showStatus" />
			<html:hidden name="PackingListGenerationFB" property="sampleAreaName" />
			<html:hidden name="PackingListGenerationFB" property="machineCodeName" />
			<html:hidden name="PackingListGenerationFB" property="sampleAreaCode" />
						 <html:hidden name="PackingListGenerationFB" property="currentPage" />
			
			<html:hidden name="PackingListGenerationFB" property="isSampleAreaSelected" />
			<html:hidden name="PackingListGenerationFB" property="tempToDate" />
			<html:hidden name="PackingListGenerationFB" property="tempFromDate" />
			<html:hidden name="PackingListGenerationFB" property="packingListGenerationType" />
						<html:hidden name="PackingListGenerationFB" property="sysDate" />
			


				  <his:ButtonToolBarTag>
				
				   <his:statusNew>
				     <logic:equal name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
				     	<logic:notEqual name="PackingListGenerationFB" property="showStatus" value="1">
				    	
				    	<logic:notEqual name="PackingListGenerationFB" property="packingListGenerationType" value="1">
				    		<img class="button" src='<his:path src="/hisglobal/images/btn-gen-all.png"/>' id="gobtn1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) generateAllPackingList();" onclick="generateAllPackingList();" tabindex="1">
				    	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor1();">
				    
				    	</logic:notEqual>
				    	</logic:notEqual>
				    </logic:equal>
				     <logic:notEqual name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
				    	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gobtn1" style="cursor: pointer;display:none" onkeypress="if(event.keyCode==13) showPatList()" onclick="showPatList()" tabindex="1">
				   
				    </logic:notEqual>
				    </his:statusNew>
				    <his:statusTransactionInProcess>
				    <logic:equal name="PackingListGenerationFB" property="isSampleAreaSelected" value="1">
				     	<logic:notEqual name="PackingListGenerationFB" property="packingListGenerationType" value="1">
				     		<img class="button" src='<his:path src="/hisglobal/images/btn-gen-all.png"/>' id="gobtn1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) generateAllPackingList();" onclick="generateAllPackingList();" tabindex="1">
				     	
				     	</logic:notEqual>
				     	<img class="button" src='<his:path src="/hisglobal/images/btn-generate.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1"  onkeypress="if(event.keyCode==13) generatePackingList();" onclick="generatePackingList();" >
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    
				     </logic:equal>
				    </his:statusTransactionInProcess>
				   
				</his:ButtonToolBarTag>
				
				<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="show"/> </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
    <div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="blue"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  	 <bean:message key="normalReq"/>
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="red"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 <bean:message key="urgentReq"/>
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="brown" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="brown"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 <bean:message key="Confidential"/>
					</div>
					</font>
				</td>				
			</tr>
			
		</table>
	</his:ContentTag>
	</div>
       

<his:status/>
  
</html:form>
</body>
<script>
function showSearchDiv(obj)
{

	if(document.getElementsByName('areaa')[0].checked==true)
	{
		document.getElementsByName('flag')[0].value='0';
	}
    
	
	if(obj.value==-1)
	{
	//	document.getElementById('gobtn1').style.display="none";
	
	}
	else
	{
		//document.getElementById('gobtn1').style.display="block";
		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("machineCodeName")[0].value=document.getElementsByName("machineCode")[0].options[document.getElementsByName("machineCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		
		
		document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	}
	
	
	//var sampleAreaCode=document.getElementByName("sampleAreaCode")[0].value;
}


function showSearchDivOnLoad()
{
   //   alert(document.getElementsByName('flag')[0].value);
    if(document.getElementsByName('flag')[0].value=='0')
    	document.getElementsByName("areaa")[1].disabled=true;

    if(document.getElementsByName('flag')[0].value=='1')
    	document.getElementsByName("areaa")[0].disabled=true;

        
        
	 if(document.getElementsByName("machineCode")[0].value=="-1")
		 {
			document.getElementById('machine1').style.display="none";
			document.getElementsByName("areaa")[0].checked=true;
			//document.getElementsByName("areaa")[1].disabled=true;
			document.getElementsByName("areaa")[1].checked=false;
			 }else
	 {
	document.getElementById('area1').style.display="none";
//	document.getElementsByName("areaa")[0].disabled=true;
	document.getElementsByName("areaa")[0].checked=false;
	document.getElementsByName("areaa")[1].checked=true;
	
	//document.getElementById('machine1').checked = false;
	//document.getElementById('area1').checked = true;
	 }
	
 
	var check=document.getElementsByName("sampleAreaCode")[0].length;
	//alert(check)
	if(check!=1)
	{
	//	document.getElementById('gobtn1').style.display="";
    }
	else
	{
		//document.getElementById('gobtn1').style.display="";
	document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
	document.getElementsByName("machineCodeName")[0].value=document.getElementsByName("machineCode")[0].options[document.getElementsByName("machineCode")[0].selectedIndex].text;
	document.getElementsByName("isSampleAreaSelected")[0].value="1";
		
		document.getElementsByName('hmode')[0].value='NEW';
		//document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	}
}
	
	
</script>


<script type="text/javascript" >

function showdata(obj,id,idd)
{
//alert(obj.id);
	//obj.checked=true;
	if(id=="area1")
		{
		document.getElementsByName('flag')[0].value=0;
	//document.getElementsByName('areaa')[0].disabled=true;
		}

		if(id=="machine1")
			{

			document.getElementsByName('flag')[0].value=1;
			
			
		//document.getElementsByName('areaa')[1].disabled=true;
			}
		document.getElementById(idd).style.display="none";

	document.getElementById(id).style.display="";
	

}


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


function httpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		initReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			 request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		{
			initReq(reqType,url,asynch);
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
		}
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function initReq(reqType,url,isAsynch)
{
	//alert("inside init request");
 /* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleResponse;
	request.open(reqType,url,isAsynch);
 /* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	var queryString;
	request.send(queryString);
}

function ValidateSameCrNo(obj)
{
	
 	//document.getElementById('gobtn1').style.display="none";
	
	document.getElementById('nextDiv').style.display="";
	
	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{
		
		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    	alert("Please Select Same CR No.");
			    	//document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	} 
				}
		}
	}
	//displaySamplePatDetails();
}


function showPatList ()
{
	
	  if(!validateTodayOrDate())
 	 {return false;}
  
	  
	  
	//document.getElementById('gobtn').style.display="none";
	document.getElementsByName('showStatus')[0].value="0";
	document.getElementsByName('hmode')[0].value='GETPATLIST';
	document.forms[0].submit();
	}
	
function submitFor()
{
	document.getElementsByName('showStatus')[0].value="0";
	document.getElementsByName('isSampleAreaSelected')[0].value="0";
	document.getElementsByName('sampleAreaCode')[0].value="-1";
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}	
	
	
	
function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}


function displaySamplePatDetails()
{	
	var count=0;
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	if(cbs[i].checked)
    	{
    		
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 }
  	//}
	  
 
      }
	
	if(count==0)
   	{
   	alert("please select a record");
   	return false;
   	}
	
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	document.forms[0].submit();
	}

function handleResponse()
{
	//alert("inside Response");
	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
			//alert("response sucessfull");
		}
		else
			alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	}
}



function onClickSave(obj)
{
	if(obj.checked)document.getElementById("nextDiv").style.display=""; 
	else document.getElementById("nextDiv").style.display="none";
}

function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

//AJax Functions for checking Duplicacy
function chkSampleNoDuplicacyThroughAjax(obj,event)
{
	var sampleNo=obj.value;
	var sampleAreaCode=document.getElementsByName("sampleAreaCode")[0].value;
	
	if(sampleNo.length<3)
		return true;
	
		var isSampleNoPresent = chkSampleNo(sampleNo,sampleAreaCode);
		
		isSampleNoPresent=isSampleNoPresent=="true"?true:false;
		
		if(isSampleNoPresent)
		{
			alert("Sample Number already present");
			obj.value="";
			obj.focus();
			return false;
		}
	return true;
}

function chkSampleNo(sampleNo,sampleAreaCode)
{
	var flg = false;
	var isSampleNoPresent = false;
	var _mode = "AJX_DUPLICACY_SAMPLENO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&strSampleNo="+sampleNo+"&sampleAreaCode="+sampleAreaCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isSampleNoPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isSampleNoPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isSampleNoPresent;
}



//End AjaxFunctions

function validateSampleCollection()
{
		var chkBoxSample=document.getElementsByName("chkSamplePatient");
		if(chkBoxSample==null || chkBoxSample.length<1)
			{ 
				alert("Please select atleast one billed Patient");
				return false;
			}
		for(var i=0;i<chkBoxSample.length;i++)
			{
				var sampleNo=document.getElementsByName("sampleNo")[i].value;
				var sampleQnty=document.getElementsByName("sampleQnty")[i].value;
				var defaultUOMCode=document.getElementsByName("defaultUOMCode")[i].value;
				var defaultContainerCode=document.getElementsByName("defaultContainerCode")[i].value;
				
				if(defaultUOMCode=="-1")
					{
						alert("Please select Unit of Measurement");
						return false;
					}
				else if(defaultContainerCode=="-1")
				{
					alert("Please select Container");
					return false;
				}
			}
		return true;
}


function generatePackingList()
{
	//alert("Inside Save");
	document.getElementsByName('hmode')[0].value='SAVE';
	document.forms[0].submit();
}

function generateAllPackingList()
{
	//alert("Inside Save");
	document.getElementsByName('hmode')[0].value='GENERATEALL';
	document.forms[0].submit();
}

function onClickGenerationType(obj)
{
	if(obj.value==0) // Original Printing
	{ 	
	document.getElementsByName('packingListGenerationType')[0].value=0;
	document.getElementsByName('showStatus')[0].value="0";
	document.getElementsByName('hmode')[0].value='GETPATLIST';
	document.forms[0].submit();
	}
	if(obj.value==1) //Duplicate Printing
		{
		document.getElementsByName('packingListGenerationType')[0].value=1;
		document.getElementsByName('showStatus')[0].value="0";
		document.getElementsByName('hmode')[0].value='GETPATLIST';
		
		document.forms[0].submit();
		}
		
	}
	


function cancelFunc()
{

	if(document.getElementsByName("flagforipddesk")[0].value=="15")
	{
		     if(parent.gotToMainTab)
		 		parent.gotToMainTab("Patient Dashboard", true);
		 	else if(parent.parent.gotToMainTab)
		 		parent.parent.gotToMainTab("Patient Dashboard", false);
		 		
			}
	else
		{
	window.parent.closeTab();
		}
	
		}



function dateValueCheck()
{

	
/* 	alert("temp from date   "+document.getElementsByName("tempFromDate")[0].value);
 */	
 
 
 if(document.getElementsByName("tempFromDate")[0].value!=null && document.getElementsByName("tempFromDate")[0].value!="")
 document.getElementsByName("fromDate")[0].value=document.getElementsByName("tempFromDate")[0].value;
	
 if(document.getElementsByName("tempToDate")[0].value!=null && document.getElementsByName("tempToDate")[0].value!="")
 document.getElementsByName("toDate")[0].value=document.getElementsByName("tempToDate")[0].value;

	}
	
</script>