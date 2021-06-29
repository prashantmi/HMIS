
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@page import="java.util.*"%>
<%
	ArrayList temp=new ArrayList();
	ArrayList temp1=new ArrayList();
	ArrayList postdata=new ArrayList();
	String treename="";
	//ArrayList predata=new ArrayList();
	int i=0;
	
%>

<his:javascript src="/hisglobal/js/stack.js"/>
<his:javascript src="/hisglobal/js/queue.js"/>
<his:javascript src="/hisglobal/js/tree.js"/>
<his:javascript src="/registration/js/popup.js"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function changeMode(){
	document.form1.submit();
}

function reflectDiagnosis(){
//alert("sdsdsdSDSD");
arrSelection=new Array();
elem = document.getElementsByName("checkControl");
//alert("length..."+elem.length);

for(i=0;i<elem.length;i++){
	if(elem[i].checked){	    
	  arrSelection[arrSelection.length]=elem[i].value;					
		}
}

//alert("after setting array values");
if(arrSelection.length!=0){
self.close();
callToPopulate(arrSelection);
}
else
alert("Select Provisional Diagnosis");
}


</script>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>


<%try{	%>	
<his:ContentTag>
<table border='0' cellpadding='0' cellspacing='1' width ='97%' >	
	
	<tr>
		<td class="tdfont">
		<his:selectTree propertyName="checkControl" beanName="EpisodePopupFB">
		    <his:renderTree />
		</his:selectTree>
	</tr>
	<tr>
		<td class='tdfont'>
		<div align="center">
		<img src="<his:path src='/../HIS/hisglobal/images/buttons/btn-sv.png'/>" onclick="javascript:reflectDiagnosis();"/>
		</div>
		</td>
	</tr>
	<tr>
		<td class='tdfonthead'></td>
	</tr>
</table>			
	
</his:ContentTag>
<%
}catch(Exception e){
	e.printStackTrace();
	System.out.println("exception in popup.jsp: "+e);	
}
%>