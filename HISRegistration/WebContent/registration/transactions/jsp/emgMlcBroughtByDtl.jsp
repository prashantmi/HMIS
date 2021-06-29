<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="div-table" >
		<div class="div-table-row title">
			<div class="div-table-col width80"><s:text name="brought"/>&nbsp;<s:text name="by"/>&nbsp;<s:text name="global.detail"/>  </div>
			<div class="div-table-col width20 alignRight">
			 	<s:text name="global.is"/>&nbsp;<s:text name="brought"/>&nbsp;<s:text name="by"/>  <input type="checkbox" name="isBroughtBy" value="0" onclick="emgMlcDtlJsObj.broughtBy(this)" tabindex="2">
			</div>
		</div>
	</div>

	<div class="div-table" id="divBroughtById" >
		<div class="div-table-row ">
			<div class="div-table-col width100" id="divBroughtById2" style="display: none;">	
			
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col width25 label">	
    					   <font color="#FF0000">*</font><s:text name="brought"/>&nbsp;<s:text name="by"/> 
               			</div>
						<div class="div-table-col width25 control">	
				        	<s:select name="isRelative" id="isRelativeId" cssClass="select61prcnt" tabindex="1"
				        			  list="#{'-1':'Select Value','1':'Relative','2':'Police','3':'Other'}" 
									 value="isRelative" onchange="emgMlcDtlJsObj.enableRelation(this)" />
						</div>
						<div class="div-table-col width25 label">	
     						<font color="#FF0000">*</font><s:text name="relationship"/> 
                		</div>
						<div class="div-table-col width25 control">	
							<s:set name="optionsRelativeList" value="#session[@registration.config.RegistrationConfig@ESSENTIALBO_OPTION_RELATION_DTL]"></s:set>
							<s:if test="%{#optionsRelativeList!= null}">
								<s:select 	name="broughtByRelationCode" id="broughtByRelationCodeId"
										cssClass="select61prcnt" list="optionsRelativeList"  tabindex="1"
										 listKey="value" listValue="label" 
									   headerKey="-1"  headerValue="Select Value"
										 value="broughtByRelationCode"  disabled="disabled" />
							</s:if>
							<s:else>
								<select id="broughtByRelationCodeId" name="broughtByRelationCode" class="select70prcnt" tabindex="1">
									<option value="-1">Select Value</option>
								</select>
							</s:else>
						</div>
					</div>
					<div class="div-table-row">
						<div class="div-table-col width25 label">	
 							<div id="divNameBroughtById">
    						   	<font color="#FF0000">*</font><s:text name="global.name"/> 
   							</div>      
                		</div>
						<div class="div-table-col width25 control">	
							<s:textfield cssClass="input60prcnt" name="broughtByName" tabindex="1" maxlength="90"></s:textfield>
						</div>
						<div class="div-table-col width25 label">
							<font color="#FF0000">*</font><s:text name="brought"/>&nbsp;<s:text name="fromLocation"/>   
						</div>
						<div class="div-table-col width25 control">
							<s:textfield cssClass="input60prcnt"  name="broughtByLocation" tabindex="1" maxlength="100"></s:textfield>
						</div>
               		</div>
      			</div>
         		
		    	<div class="div-table" id="divPoliceDetailId">
		    		<div class="div-table-row ">
						<div class="div-table-col width25 label">	
  							<div id="divNameBroughtById">
  								<font color="#FF0000">*</font><s:text name="designation"/>
   							</div>      
                		</div>
						<div class="div-table-col width25 control">	
							<s:textfield cssClass="input60prcnt"  name="constableDesig" tabindex="1" maxlength="50"></s:textfield>
						</div>
						<div class="div-table-col width25 label">	       	
						   <font color="#FF0000">*</font><s:text name="badgeno"/></div>
						<div class="div-table-col width25 control">	
							<s:textfield cssClass="input60prcnt"  name="constableBadgeNo" tabindex="1" maxlength="50"></s:textfield>
						</div>	
					</div>
				</div>		
			
		    	<div class="div-table" id="divPoliceStnId" style="display: none;">
		    		<div class="div-table-row ">
						<div class="div-table-col width25 label"><s:text name="policestation"/> </div>
						<div class="div-table-col width25 control">	
							<input name="policeStation" maxlength="50" type="text" class="input60prcnt" tabindex="2">
						</div>
						<div class="div-table-col width25 label"><s:text name="pcrvanno"/></div>
						<div class="div-table-col width25 control">	
							<input name="pcrVanNo" maxlength="50" type="text" class="input60prcnt" tabindex="2">
						</div>
					</div>
				</div>		
				
				<div class="div-table" id="divPhoneBroughtById">
		    		<div class="div-table-row width100">
						<div class="div-table-col width25 label">	
	    					<font color="#FF0000">*</font><s:text name="mobileNo"/>
	               		</div>
						<div class="div-table-col width25 control">	
							<s:textfield cssClass="input60prcnt"  name="broughtByPhone" maxlength="10" tabindex="1"></s:textfield>	
						</div>
						<div class="div-table-col width25 label">	
	    					<font color="#FF0000">*</font><s:text name="address"/>
	               		</div>
						<div class="div-table-col width25 control">	
							<s:textarea name="broughtByAddress" cssClass="textarea60prcnt" tabindex="1" />
						</div>	
					</div>
				</div>	
		
			</div>
		</div>
	</div>
</body>
</html>