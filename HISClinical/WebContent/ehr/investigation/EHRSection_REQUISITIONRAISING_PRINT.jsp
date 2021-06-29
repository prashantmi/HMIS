<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>DIAGNOSIS_PRN</title>      
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!--  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/combined.css" rel="stylesheet">  -->
       <link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
           <script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>

       
      <script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
      
  

   <script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>
   
 </head>
<style>
fieldset.scheduler-border {
    border: 1px groove black !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0px 5px 10px;
        border-bottom:none;
        color:
    }
</style>


<body style="margin:0%">
<form>
<div class="vcontainer">

<div id="diagnosis">	
	<div class="row">
		<div class="col-xs-12">
			<h5><b>Patient Diagnosis Details</b></h5>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			[Disease (Diagnosis Code Type)]; (Diagnosis Type);
		</div>
		
	</div>
	
	
</div>
<script>

</script>
</div>
</form>
</body>

</html>