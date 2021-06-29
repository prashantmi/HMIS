
$.datepicker.setDefaults( 
	    {	showOn: 'both',
	    	defaultDate: new Date(),
	    	buttonImageOnly: true, 
	    	buttonText : "Select Date",
	    	buttonImage: "/HIS/hisglobal/images/nonclinical/nc_calendar_icon.gif"
	    	});



function clearEssentialDivs()
{
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId2").html("");
}


$('#cancelId').click(function(e){	
	_func.setFormFieldsAfterSave();
	clearEssentialDivs();		
	parent.reloadList();	
	parent.closeModal();	
	return false;	
});


function showEmpList(p_mode)
{
//	alert("showEmpList  "+p_mode);
//	alert("Calling Seq:: "+$('[name="intCallingSeq"]')[0].value);
	var pageWidth= $("#list1").parent().width()-50;  
	var mygrid = jQuery("#list1").jqGrid({	
    	url:'/HISPis/pis/common/transactions/empListEmployeeInfo.action?q=2&pageFlag=list&listType='+$('[name="intListType"]')[0].value+'&rmTypeId='+$('[name="intRoleMgtTypeId"]')[0].value+
    		'&calSeq='+$('[name="intCallingSeq"]')[0].value+'&salTypeId='+$('[name="intSalaryTypeId"]')[0].value+'&nojId='+$('[name="intNojId"]')[0].value+
    		'&cadreId='+$('[name="intCadreId"]')[0].value+'&empOffId='+$('[name="intEmpOffId"]')[0].value+'&estbId='+$('[name="intEstbId"]')[0].value+
    		'&deptId='+$('[name="intDeptCode"]')[0].value+'&serGrpId='+$('[name="intSerGrpId"]')[0].value+'&desigId='+$('[name="intDesigCode"]')[0].value+
    		'&empStatusId='+$('[name="intStatusId"]')[0].value+'&empFinalStatusId='+$('[name="intFinalStatusId"]')[0].value+
    		'&condition='+$('[name="strCondition"]')[0].value+'&additionalData='+$('[name="strAdditionalData"]')[0].value,
    		 
    	datatype: "json",
        colNames:['Emp. No','Emp. Name','Designation Id','Designation','Department Id','Department'],
        
        colModel:[
	               	{name:'empNo',index:'empNo', width:(pageWidth*(15/100)), align:'right', sorttype: 'int',searchoptions:{sopt:['cn','eq','in','bw','ew']}},
	              	{name:'empName',index:'empName', width:(pageWidth*(35/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'desigId',index:'desigId',width:(pageWidth*(0/100)), align:"left", hidden:true, searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'desigName',index:'desigName',width:(pageWidth*(25/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'deptId',index:'deptId',width:(pageWidth*(0/100)), align:"left", hidden:true, searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'deptName',index:'deptName',width:(pageWidth*(25/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}}
	            ],
	    rowNum:10,
        rowList:[5,7,8,10,15,20,30,40,50],
        pager:'#pager1',
        sortname: 'empName',
        viewrecords: true,
        sortorder: "asc",
        caption:"Employee List",
        height: "100%",
        //autowidth: true,   
        gridComplete: function(){         	
         	},
        onSelectRow: function(id,status){
         		if (status) {
         		//alert("eMode"+eMode);
                var sel_row = jQuery('#list1').jqGrid('getGridParam', 'selrow');              
                var emp_no = jQuery('#list1').jqGrid('getCell', sel_row, 'empNo');
                var emp_name = jQuery('#list1').jqGrid('getCell', sel_row, 'empName');
                var emp_desig_id = jQuery('#list1').jqGrid('getCell', sel_row, 'desigId');
                var emp_desig = jQuery('#list1').jqGrid('getCell', sel_row, 'desigName');
                var emp_dept_id = jQuery('#list1').jqGrid('getCell', sel_row, 'deptId');
                var emp_dept = jQuery('#list1').jqGrid('getCell', sel_row, 'deptName');
              
               if($('[name="intCallingSeq"]')[0].value=="" || $('[name="intCallingSeq"]')[0].value==null) parent.setEmpInfo(id,emp_no,emp_name,emp_desig,emp_dept);
               else if($('[name="intCallingSeq"]')[0].value=="1"){parent.setEmpInfo1(emp_no,emp_name,emp_desig_id,emp_desig,emp_dept_id,emp_dept);}
               else if($('[name="intCallingSeq"]')[0].value=="2") parent.setEmpInfo2(emp_no,emp_name,emp_desig_id,emp_desig,emp_dept_id,emp_dept);
               else if($('[name="intCallingSeq"]')[0].value=="3") parent.setEmpInfo3(emp_no,emp_name,emp_desig_id,emp_desig,emp_dept_id,emp_dept);
               else if($('[name="intCallingSeq"]')[0].value=="4") parent.setEmpInfo4(emp_no,emp_name,emp_desig_id,emp_desig,emp_dept_id,emp_dept);
               else if($('[name="intCallingSeq"]')[0].value=="5") parent.setEmpInfo5(emp_no,emp_name,emp_desig_id,emp_desig,emp_dept_id,emp_dept);
               // alert("after 3");
         	
            }
         	}
         
     }).jqGrid('navGrid','#pager1',{add:false,edit:false,del:false,searchtitle:'Search',refreshtitle:'Clear Search'},{}, {}, {}, {multipleSearch:true, multipleGroup:true} );
     $("#list1").jqGrid("filterToolbar", {searchOperators : true, stringResult:true,  searchOnEnter:false, afterSearch:function(){ filteredIDs = $("#list1").getDataIDs(); } });  
     jQuery("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s', onClickButton:function(){ mygrid[0].toggleToolbar(); } }); 
     jQuery("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"",title:"Reload",buttonicon :'ui-icon-arrowrefresh-1-s', onClickButton:function(){ $("#list1").trigger("reloadGrid"); } }); 
    //document.getElementById("saveDiv").style.display="none";
    
}

$(window).bind('resize', function() {
    $("#list1").setGridWidth($(window).width()*0.99); 
}).trigger('resize');

function showEmpModifyList(eMode,year)
{
	//alert("showEmpList year "+year);
    //alert(searchEmpNo);
	var pageWidth= $("#list1").parent().width();  
	//alert("showEmpList");
	var mygrid = jQuery("#list1").jqGrid({	
    	url:'/HISPis/pis/transfer/transactions/empModifyListIncrementWithheld.action?q=2&pageFlag=empList&year='+year, 

    	datatype: "json",
        colNames:['Emp. No','Emp. Name','Designation','Department','Increment Due Date','Next Increment Date'],
        
        colModel:[
	               	{name:'strEmpNo',index:'empNo', width:(pageWidth*(10/100)), align:'right', sorttype: 'int',searchoptions:{sopt:['cn','eq','in','bw','ew']}},
	              	{name:'strEmpName',index:'empName', width:(pageWidth*(10/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'desigName',index:'desigName',width:(pageWidth*(10/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'deptName',index:'deptName',width:(pageWidth*(10/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'incDueDate',index:'incDueDate',width:(pageWidth*(10/100)), align:"center", searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	              	{name:'nextIncDate',index:'nextIncDate',width:(pageWidth*(10/100)), align:"center", hidden:true, searchoptions:{sopt:['cn','eq','ne','le','ge','nu','in','bw','ew']}},
	            ],
        rowNum:10,
        rowList:[5,10,15,20,30,40,50],
        pager:'#pager1',
        sortname: 'empName',
        viewrecords: true,
        sortorder: "asc",
        caption:"Employee List",
        height: "100%",
        autowidth: true,
        
         
         gridComplete: function(){ 
         	var ids = jQuery("#list1").jqGrid('getDataIDs');
         	//alert("Length > "+ids.length);
         	for(var i=0;i < ids.length;i++){ 
	         		var cl = ids[i]; 
	         		
	         		
	         	//alert("c1"+cl);
	         		var ae = "";
	         		be = "&nbsp;&nbsp;&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_view.png' width='18' height='20' style='vertical-align: middle;'title='View' onclick=\"viewRecord('"+cl+"');\" />&nbsp;&nbsp;&nbsp;&nbsp;";
	         		ce = "<img src='/HIS/hisglobal/images/nonclinical/nc_trash.gif' width='18' height='20' style='vertical-align: middle;'title='Delete' onclick=\"deleteRecord('"+cl+"');\" />&nbsp;&nbsp;&nbsp;&nbsp;";
	         	//alert("dddd");	
	         		//var rowData = jQuery("#list1").getRowData(cl);
	         		var rowData = jQuery('#list1').jqGrid ('getRowData', cl);
	         		//alert("ddddd");
	         		rowData[i]=rowData;
	         		if(rowData['intIsValid']=='1'){
	         			//alert("ddddd111");
	         			de = "<img src='/HIS/hisglobal/images/nonclinical/nc_invalid.png' width='18' height='20' style='vertical-align: middle;'title='Invalidate' onclick=\"makeRecordInValid('"+cl+"');\" />&nbsp;&nbsp;&nbsp;&nbsp;";
	         			rowData.intIsValid = "<img src='/HIS/hisglobal/images/nonclinical/nc_valid.png' width='18' height='20' style='vertical-align: middle;'title='Valid' />";
	         			//rowData.intIsValid = "Valid";
	         			ae = "<img src='/HIS/hisglobal/images/nonclinical/nc_edit.png' width='18' height='20' style='vertical-align: middle;' title='Update' onclick=\"editRecord('"+cl+"');\" />&nbsp;&nbsp;&nbsp;&nbsp;";
	         		}
	         		
	         		else
	     			{
	         			//alert("ddddd11111");
	         			de = "<img src='/HIS/hisglobal/images/nonclinical/nc_valid.png' width='18' height='20' style='vertical-align: middle;'title='Validate' onclick=\"makeRecordValid('"+cl+"');\" />&nbsp;&nbsp;&nbsp;&nbsp;";
	         			rowData.intIsValid = "<img src='/HIS/hisglobal/images/nonclinical/nc_invalid.png' width='18' height='20' style='vertical-align: middle;'title='Invalid' />";
	         			//rowData.intIsValid = "Invalid";
	         			ae = "";
	     			}
	         		//alert("ddddd22");
	         		$('#list1').jqGrid('setRowData', cl, rowData);
	         		//alert("ddddd22222");
	         		//jQuery("#list1").jqGrid('setRowData',ids[i],{actions:be+ce+de+ae});
	         		jQuery("#list1").jqGrid('setRowData',ids[i]);
	         		//alert("ddddd222222");
	         		
	         		/*jQuery('#list1').jqGrid('setSelection',ids[i]); 
	         		var selr = jQuery('#list1').jqGrid('getGridParam','selrow');
	         		cellValue = jQuery('#list1').jqGrid('getCell', selr, 'strSancEmpNo');
	         	 	//window.opener.setValues(strSancEmpNo, strSancEmpName, strSancEmpDept, strSancEmpDesig);
	         		//window.close
	         		alert(cellValue);*/
         		} 
         	},
         	onSelectRow: function(id,status){
         		if (status) {
         		//alert("eMode"+eMode);
                var sel_row = jQuery('#list1').jqGrid('getGridParam', 'selrow');
                //alert("selrow"+sel_row);
                var emp_no = jQuery('#list1').jqGrid('getCell', sel_row, 'strEmpNo');
                var emp_name = jQuery('#list1').jqGrid('getCell', sel_row, 'strEmpName');
                var emp_desig = jQuery('#list1').jqGrid('getCell', sel_row, 'desigName');
                var emp_dept = jQuery('#list1').jqGrid('getCell', sel_row, 'deptName');
                var inc_dueDate= jQuery('#list1').jqGrid('getCell', sel_row, 'incDueDate');
                var next_incDate= jQuery('#list1').jqGrid('getCell', sel_row, 'nextIncDate');
                
                
               // alert("before");
             //  alert("after     "+eMode);
               
               // alert("after 2");
                parent.setEmpInfo1(id,emp_no,emp_name,eMode,emp_desig,emp_dept,inc_dueDate,next_incDate);
               // alert("after 3");
         	
            }
         	}
         
     }).jqGrid('navGrid','#pager1',{add:false,edit:false,del:false,searchtitle:'Search',refreshtitle:'Clear Search'},{}, {}, {}, {multipleSearch:true, multipleGroup:true} );
     $("#list1").jqGrid("filterToolbar", {searchOperators : true, stringResult:true,  searchOnEnter:false, afterSearch:function(){ filteredIDs = $("#list1").getDataIDs(); } });  
     jQuery("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s', onClickButton:function(){ mygrid[0].toggleToolbar(); } }); 
     jQuery("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"",title:"Reload",buttonicon :'ui-icon-arrowrefresh-1-s', onClickButton:function(){ $("#list1").trigger("reloadGrid"); } }); 
    //document.getElementById("saveDiv").style.display="none";
    
}

