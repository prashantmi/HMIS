<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
	<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
	<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

	
	<%! 
	  
		private String getConstraintString(Map constraintMap)
		{
			System.out.println("map"+constraintMap);
			String strConstraints="";
			Set setConstraints= constraintMap.keySet();
			Iterator itr=setConstraints.iterator();
			while (itr.hasNext())
			{
			  String key=(String)itr.next();
			  if(!key.equals("inputType"))
			  {
			   strConstraints=strConstraints+key+"="+"'"+(String)constraintMap.get(key)+"'"+" ";
			  }	  		
			}
			System.out.println("strConstraints"+strConstraints);
			return strConstraints;	  
		}
		private String getReadonly(boolean rdOnly)
		{
			String rd="";
			if(rdOnly)
			{
			  rd="readonly"; 				
			}
			return rd;	
		}
 		
		
	%>

	<%
	 try{
	%>
	<HTML>
		<HEAD>
		
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<TITLE>
				<bean:write name = "TOLstObject" property ="title"/>
			</TITLE>
	<link href="../hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../hisglobal/css/Color.css" rel="stylesheet" type="text/css">
	<link href="../hisglobal/css/master.css" rel="stylesheet" type="text/css">
	
		</HEAD>
		<BODY>
<!-------------->
<bean:define name = "mstHandlerModifyForm" id="frm"/>
<%
	//Enumeration arrParam = request.getParameterNames();
	Class cls=	frm.getClass();
	
	for(int i=0; i<cls.getMethods().length; i++)
		System.out.println(cls.getMethods()[i].getName());
%>

			
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>	<bean:write name = "TOLstObject" property ="title"/>[Modify]</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script Language=javascript>
	function submitpage(mode)
		{
		  document.forms[0].hmode.value=mode;
		  document.forms[0].submit()
		}	

	</script>	


</head>
<body  topmargin="0" leftmargin="0">

<table width="100%">
       <tr>
	   <td align="center">	   
       		<img src="../hisglobal/images/e_sushrut_header.gif">
	   </td>		
       <td height="19" valign="bottom"><div align="right">
	   <a href="/startup/cnt_login.jsp?loginMode=LOGOUT&uid=null"></a>
	   </td>
	   </tr> 
</table>
   <html:form action="/masterWorkshop/mstHandlerModifyAction" method="get">	

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><div align="center">
          <table width="60%" border="0" cellspacing="1" cellpadding="0">
            <tr bgcolor="#FFFFFF"> 
              <td colspan="3" class="pagetitle"><bean:write name = "TOLstObject" property ="title"/>&gt;&gt;Modify</td>
            </tr>
            <tr bgcolor="#dee1f2"> 
              <td class="addheader" colspan="3"> &nbsp;</td>
            </tr>			
			   
               <bean:define id="controls" name="mstHandlerModifyForm" property="ctrl" type="java.lang.Object[]" />
	           <logic:iterate name = "TOModObject" property ="controls" id = "control" indexId ="idx">
			   <tr>			  			
               <td height="20" colspan="2"  bgcolor="#F5F3F3" class="adddatalabel"> 
               <div align="right"><font size="2" face="Verdana, Arial, Helvetica, sans-serif">  
                 <bean:write name = "control" property="label"/></font> </div>
               </td>
            
			   <td  height="17" bgcolor="#F5F3F3" width="50%" class="adddatavalue"> 
			   <%if(control.getClass()==MasterConfig.CLASS_COMBO){
				  System.out.println("MasterConfig.CLASS_COMBO: "+idx);
				   boolean submitRequired=((Combo)control).getSubmitRequired();
				if(submitRequired)
				{			
			%>
			    <html:select name = "mstHandlerModifyForm" property = '<%="cntrl["+idx+"]"%>' onchange="submitpage('RESUBMIT');">
				 <html:option value = "-1">Select Value</html:option>
		          <html:optionsCollection name = "control" property="options" label="label" value="value"/>
				</html:select>		
				<%}
				else
				{%>
					<html:select name = "mstHandlerModifyForm" property ='<%="cntrl["+idx+"]"%>'>
					<html:option value = "-1">Select Value</html:option>
		                <html:optionsCollection name = "control" property="options" label="label" value="value"/>
				    </html:select>		
						
				<%
				}
				%>			
			  	
				<%
			 	 String labelSuffix =((Combo)control).getLabelSuffix();
				  %>
				 <%=labelSuffix%>			
						
				
		 <%
		 }
		 
		 else if(control.getClass()==MasterConfig.CLASS_TEXT){
			System.out.println("MasterConfig.CLASS_TEXT: "+idx);
			%>
		
		<%
			String strConstraint="";
			Map constraintsMap=((TextBox)control).getConstraints();			 	
		%>
		


	<input type="text" name="cntrl[<%=idx%>]" <%=getReadonly(((TextBox)control).getReadOnly())%> value="<bean:write name= "mstHandlerModifyForm" property='<%="cntrl["+idx+"]"%>'/>"  <%=getConstraintString(constraintsMap)%>/>
	<%
			 	 String labelSuffix =((TextBox)control).getLabelSuffix();
				  %>
				 <%=labelSuffix%> 
			 	
		
		 <%}
		 else if(control.getClass()==MasterConfig.CLASS_TEXTAREA)		
		 {
			
			String strConstraint="";
			Map constraintsMap=((TextArea)control).getConstraints();	 				
		%>		
			<input type="textarea" name="cntrl[<%=idx%>]"  value="<bean:write name= "mstHandlerModifyForm" property='<%="cntrl["+idx+"]"%>'/>"  <%=getConstraintString(constraintsMap)%>/>
			
		<%	
		 } 
		 else if(control.getClass()==MasterConfig.CLASS_RADIOBUTTON)		
		 {
			 
			 //String strConstraint=getConstraintString(constraints);	
			 ArrayList alText =((RadioButton)control).getText();		
			 ArrayList alValue =((RadioButton)control).getValue();
			 //String [] controls=addFormBean.getControls();
			 
			 //int ff= idx.intValue();
			 System.out.println("controls"+controls[idx.intValue()]);
			 for(int i=0;i<alText.size();i++)
			  {
				String s="";
				String val=(String)alValue.get(i);
				if(controls[idx.intValue()].equals(val))
				{
				  	s="checked";
				}
				%>
				<%=alText.get(i)%>
				<input type="radio" name="controls[<%=idx%>]" value="<%=alValue.get(i)%>" <%=s%>/>			
			   	<%		
			  }		 
		 %> 	 
			
		<%	
		 }
	 	else if(control.getClass()==MasterConfig.CLASS_CHECKBOX)		
		 {
			 //String strConstraint=getConstraintString(constraints);	
			 ArrayList alText =((CheckBox)control).getText();		
			 ArrayList alValue =((CheckBox)control).getValue();
		%>
		
	    <bean:define name="mstHandlerModifyForm" property='<%="multiSelect["+idx+"].data"%>' id="multiSelected" type="java.lang.String[]"/>
		<% 
			 for(int i=0;i<alText.size();i++)
			 {
				%> <%=alText.get(i)%>
				<input type="checkbox" name="multiSelect[<%=idx%>].data"  value="<%=alValue.get(i)%>" 
					<%	
						System.out.println("alValue.get(i):  "+alValue.get(i)+"  multiSelected.length:  "+multiSelected.length);
						boolean checked = false;
						for(int j=0; j<multiSelected.length; j++){
							System.out.println("j:"+j+" alValue.get(i): " +alValue.get(i)+" multiSelected[j]:  "+multiSelected[j] );
							if(multiSelected[j].trim().equals(((String)alValue.get(i)).trim())){
								checked = true;
								break;
							}
						}  
						if(checked){ 
							System.out.println("Checked:  "+alValue.get(i));
	      			%>	
					 	checked
					<%}%>
				/>			
			   	<%		
			 }		 
		 %> 
			
		<%	
		 } 
		 %>	 
		 </td>
		       </tr>  
	</logic:iterate>      
      
          <tr bgcolor="#dee1f2"> 
              <td class="addheader" colspan="3"> &nbsp;</td>
            </tr>	  		
            
          </table>
        </div></td>
    </tr>
  </table>

  <div align="center">
    <table width="60%" border="0" cellspacing="1" cellpadding="0">
      <tr> 
	    <td width="80%" class="addtoolbar" style="border-top:outset 2px black; border-bottom:inset 2px black">
		<img style=cursor:hand src="../hisglobal/images/Save.gif" width="60" height="20" tabindex="0" onKeyPress="if(window.event.keyCode==13) submitpage('MODIFYSAVE')"  onClick='submitpage("MODIFYSAVE");'/>                
        <a style=cursor:hand><input type='image' src="../hisglobal/images/Cancel.gif" width="60" height="20" tabindex="0" onKeyPress="if(window.event.keyCode==13)submitpage('CANCEL');" onClick='submitpage("CANCEL");'></a></td>
      </tr>
    </table>
    <input type="hidden" name="hmode">
    <input type = "hidden" name = "seat_id" value = '<%=session.getAttribute("SEATID")%>'>	
	
  </div>
</html:form>
</body>
</html>

		
		<!-------------->
		
	   
	<%
	    }catch(Exception e){System.out.println("Exception in mstHandleModView..."+e);
	    e.printStackTrace();}
	%>