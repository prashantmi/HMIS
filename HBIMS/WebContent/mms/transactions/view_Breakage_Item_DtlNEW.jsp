<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>


<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css"
	rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css"
	rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css"
	rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet"
	type="text/css">
<link
	href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css"
	rel="stylesheet" type="text/css">
	
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>






<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>

<script language="JavaScript" src="../js/BreakageItemDtlTransNEW.js"></script>
<script>
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>
<style type="text/css">
.col-sm-1half, .col-sm-8half, .col-sm-half {
    position: relative;
    min-height: 1px;
    padding-right: 15px;
    padding-left: 15px;
}
.custom-radio .custom-control-label::before {
    background-color: white;  /* orange */
}

.col-md-1half, .col-md-8half, .col-md-half {
    position: relative;
    min-height: 1px;
    padding-right: 15px;
    padding-left: 15px;
}
@media (min-width: 768px) {
    .col-sm-1half,.col-md-1half, .col-sm-8half {
        float: left;
    }
    .col-sm-1half ,.col-md-1half{
        width: 12.495%;
    }
    .col-sm-half,.col-md-half {
        width: 4.165%;
    }
    .col-sm-2half,.col-sm-2half {
        width: 20.495%;
    }
}
.table
{
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-weight: 400;
	color: #212529;
}
textarea
{
width:100%;

}
</style>

<script type="text/javascript">
	function controlToMainPage()
	{
		document.forms[0].hmode.value="unspecified";
		document.forms[0].submit();
	}
</script>



</head>
<body>
<html:form action="/transactions/BreakageItemDtlTransCNTNEW.cnt"  name="bkgItemDtlTransBean" type="mms.transactions.controller.fb.BreakageItemDtlTransFB" method="post">



<div class="viewport" id="nonPrintable">
			<div class="container-fluid">

				 
			<div class="row justify-content-center">
					<div class="col-sm-12">
						<br>
						<div class="prescriptionTile">
						<div class="row rowFlex reFlex">
						<div class="col-md-12" align="center">
						<div class="errMsg"     id="errMsg"><bean:write name="bkgItemDtlTransBean" property="strErr"/></div>
				<div class="warningMsg" id="warningMsg"><bean:write name="bkgItemDtlTransBean" property="strWarning"/></div>
				<div class="normalMsg"  id="normalMsg"><bean:write name="bkgItemDtlTransBean" property="strMsg"/></div>
						</div></div>
							<div class="row rowFlex reFlex">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancelFunc();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle"
										onclick="document.forms[0].reset();"
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									
								</div>
							</div>

					<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-random iround" title="Cancel"></i>
										</button>
										&nbsp;Breakage Item Transaction
									</p>
							
								</div>
							</div>


	


 
 	<div class="row rowFlex reFlex">
  		<div class="col-md-2 col-xs-12 px-4"><font color="red">*</font><label>Store Name</label></div>
 		<div class="col-md-3 col-xs-12"><div id="storeComboID">
			     <select name="strStoreName" class="browser-default custom-select"   onChange="getItemCategoryComboViewPage();">
                        <bean:write name="bkgItemDtlTransBean" property="strStoreName" filter="false"/>
                 </select>
                 </div><div id="storeComboNameID" style="display:none; color:blue;"></div></div>
         <div class="col-md-1"></div>
 		<div class="col-md-2 col-xs-12 px-4"><font color="red">*</font><label>Item Category</label></div>
 		<div class="col-md-3 col-xs-12"><div id="ItemCatgViewId" >
			     <select class='browser-default custom-select' name='strItemCatgCombo'> 
		              <bean:write name="bkgItemDtlTransBean" property="strItemCatgCombo" filter="false"/><option value="0">Select Value</option>  
		         </select>
		          </div>  
		      <div id="itemCategViewNameID" style="display: none"></div>
          	</div>
 		
 	</div>
 	<div id="dateDivId" style="display:">
								<div class="row rowFlex reFlex">
									<div class="col-sm-2 px-4">
										<label><font color="red">*</font>From Date</label>
									</div>
									<div class="col-sm-3">
										<input  name="strFromDate"
											class="form-control datepicker"
											value="${bkgItemDtlTransBean.strCurrentDate}"
											style="color: rgba(113, 111, 111, 0.87);">
									</div>
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<label><font color="red">*</font>To Date</label>
									</div>
									<div class="col-sm-3">
										<input  name="strToDate"
											class="form-control datepicker"
											value="${bkgItemDtlTransBean.strCurrentDate}"
											style="color: rgba(113, 111, 111, 0.87);">
									</div>
									<div class="col-md-1 col-xs-12"> <a class="btn btn-sm btn-success" href="#" onclick="getViewItemDtl();" style="font-size:1rem;">GO <i class="fas fa-angle-double-right"></i></a></div>
								</div>
							</div>
 	
 <div class="row rowFlex reFlex">
 		<div class="col-md-12 col-xs-12 px-4" align="center">
 			<div id="breakageItemDtlId" align="center" style="display: none"></div>
 		</div>
 	</div>
 	</div>
 
 	
    <input type="hidden" name="hmode"/>
    <input type="hidden" name="strCurrentDate" value="${bkgItemDtlTransBean.strCurrentDate}"/>
</div>
</div>
</div>
</div>
</div>

  </html:form>
<tag:autoIndex></tag:autoIndex>
<script type="text/javascript">

$('.datepicker').each(function(){
    $(this).datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
});
var today=new Date();
var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
var mmm=arr[today.getMonth()];
var hrs=today.getHours();
var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
$('.datepicker').val(dd);

</script>
</body>
</html>