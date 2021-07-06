<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/bs_45.css">
<link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/fa_5.css">
<link rel="stylesheet" href="../../hisglobal/template/js/swal.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="/HISDRDESK/hisglobal/template/js/bs_45.js"></script> 
<script src="/HISDRDESK/hisglobal/template/js/fa_5.min.js"></script>
<script src="../../hisglobal/template/js/swal.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<script type="text/javascript" src="/HISDRDESK/new_opd/js/fileUploas.js"></script> 
</head>
<body onload="getDatasave()">
<html:form action="/transaction/DoctorDeskAction.cnt"  name="DoctorDeskFB" type="new_opd.transaction.controller.fb.DoctorDeskFB" method="post" enctype="multipart/form-data"> 
<%-- <table>
<tr>
<td>Clinical Document Type:</td>
<td>
				<select name="strDocumenttype">
				<option value="0">Select</option>
				<option value="1">Patient Documents</option>
				<option value="2">External Investigation Reports</option>
				<option value="3">MLC Documents</option>
				<option value="4">Verification Documents</option>
				<option value="11">Medical Certificate</option>
				<option value="12">Fitness Certificate</option>
				<option value="13">Case Sheet</option>
				<option value="14">Death Report</option>
				<option value="15">Death Certificate (MCCD)</option>
				<option value="16">Birth Report</option>
				<option value="17">OPD File</option>
				<option value="18">Estimate Certificate</option>
				<option value="19">Certificate B</option>
				
</select>
</td>
</tr>
<tr>
<td class="LABEL" width="50%">Attach</td>
 <td>
 <html:file property="strLocation" />
  <input type="button" onclick="save_file()" value="Upload">
      <input type="hidden" name="hmode"/>
    </td>
    </tr>
    </table> --%>
     
    <div class="container-fluid">
	    <div class="row">
	    	<div class="col">Clinical Document Type:</div>
	    	<div class="col">
	    		<select name="strDocumenttype" class="form-control">
					<option value="0">Select</option>
					<option value="1">Patient Documents</option>
					<option value="2">External Investigation Reports</option>
					<option value="3">MLC Documents</option>
					<option value="4">Verification Documents</option>
					<option value="11">Medical Certificate</option>
					<option value="12">Fitness Certificate</option>
					<option value="13">Case Sheet</option>
					<option value="14">Death Report</option>
					<option value="15">Death Certificate (MCCD)</option>
					<option value="16">Birth Report</option>
					<option value="17">OPD File</option>
					<option value="18">Estimate Certificate</option>
					<option value="19">Certificate B</option>
				</select>
			</div>
	    	<div class="col">
	    			<button type="button" class="btn btn-success"  onclick="$('#uploadBtn').trigger('click');"><i class="fas fa-plus"></i> &nbsp;Add File</button>
					<html:file property="strLocation" accept="application/pdf"  onchange="addFiles()" styleId="uploadBtn" style="display:none"/>
					<input type="button" id="startUpload" style="display: none" onclick="save_file()"
						value="Upload"> <input type="hidden" name="hmode" />
				</div>
	    </div>
	    
	    <div class="row" style="margin-top:2rem;margin-left:2.5rem;">
	   	 	<div class="col-md-1">
		   	 		
	   	 	</div>
	   	 	<div class="col-md-11">
	   	 		<span style="color: red;">(only *.pdf files, size less than 2 MB will be uploaded.  Don't use any special characters in filename.)</span>
	   	 	</div>
	   	 </div>
	    
	    <div class="row" style="margin-top:2rem;margin-left:2.5rem;">
	    	<div class="col"></div>
	   	 	<div class="col">
	   	 	
	   	 		<button type="button" onclick="$('#startUpload').trigger('click')" class="btn btn-primary">Start Upload</button>
	   	 		
	   	 	</div>
	   	 	<div class="col">
	   	 		<span id="fileName"></span>
	   	 	</div>
	   	 </div>
    </div>
    
    
    
      <input type="hidden" name="strFileData" value="${DoctorDeskFB.strFileData}"/>
         <input type="hidden" id="strMsgTypeid" name="strMsgType" value="${DoctorDeskFB.strMsgType}"/>
  </html:form>

</body>

</html>