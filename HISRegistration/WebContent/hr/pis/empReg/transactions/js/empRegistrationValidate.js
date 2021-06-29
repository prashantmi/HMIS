//alert("inside emp validate js");
var departmentJSONArray=[];

var empregvalidate={
		
		fetchDefaultValues:function (_m)
		{
			//Date Fields
			$("#validateDateId").datepicker({changeMonth: true, changeYear: true, maxDate: '-d', dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
			  
			//Date Fields
			$("#validateDateId").validatebox({required: true, validType: ['date[\'dtValidateDate\',\'dd-mmm-yyyy\']']});
			$('[name="VOEmpReg.strValidatorName"]').validatebox({required : true, validType: 'alphaWithSpace'});
			$('[name="strValidatorRemarks"]').validatebox({validType: 'alphaWithSpace'});
			  
	  },
	  validateEmployeeRegDtl:function()
	  {
		  //alert($('[name="strValidateStatus"]')[0].value);
		  //For Submission
		  var action 	= "/HISPis/pis/pr/transactions/saveValidateEmployeeRegistration.action";
		 sortandEncodebase64($("#EmployeeRegistration"));
		  $.ajax({url: action,type:"POST",async:true,dataType:"xml" ,data:$("#EmployeeRegistration").serialize(), success:function(data){
			    var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				
				var elementNode=rootNode.childNodes[0];
				var elementName=elementNode.nodeName;
				if(elementName=='savedMsgDtl')
				{
					empregvalidate.initializeAfterSave(elementName,elementNode);
				}
				
			},error: function(errorMsg,textstatus,errorthrown) {
			   // alert('validateEmployeeRegDtl '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
				showtamperErrorPage("saveEmployeeDtl " + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
			    
			}});
	  },
	  initializeAfterSave:function(saveMsgElementName,saveMsgElementNode)
	  {
		  //$('#fatherorSpouseError').hide();
		  var isSavedSuccussful	   = saveMsgElementNode.getAttribute("isSavedSuccussful");
		  var msg				   = saveMsgElementNode.getAttribute("savedMessage");
		  var isFormFieldTobeReset = saveMsgElementNode.getAttribute("isFormFieldTobeReset");
		  var isPrintFlag	   	   = saveMsgElementNode.getAttribute("isPrintFlag");
		  var printDivContent	   = saveMsgElementNode.getAttribute("printDivContent");
		  
		  if(isSavedSuccussful=="1")
		  {
			  $("#divNormalMsgId").html(msg);
			  $("#divErrorMsgId").html("");
			  if(isPrintFlag=="1"){
				  $("#divPrintId").html(printDivContent);
				  get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
				  window.print();
			  }else{
				  $("#divPrintId").html("");
			  }
			  
			  if(isFormFieldTobeReset=="1"){
				  //if(confirm(msg+"\nDo you Want To Reset The Form"))
				  empregvalidate.setFormFieldsAfterSave();
			  }
		  }
		  else{
			  $("#divErrorMsgId").html(msg);
			  $("#divNormalMsgId").html("");
			  $("#divPrintId").html("");
			 // if(confirm(msg+"\nDo you Want To Reset The Form"))
				 // opdregistration.setFormFieldsAfterSave();
		  }
		  
	  },
	  setFormFieldsAfterSave:function()
	  {
		  $('.validatebox-text').removeClass('validatebox-invalid');
		  $('[name="strValidatorRemarks"]')[0].value="";
		  
		  reloadList();		  
		  empregvalidate.fetchDefaultValues("2");
	  },
	};

function showPendingList()
{
	//alert("hello Grid 1");
	//var deptOption = ':All;Finance & Accounts:Finance & Accounts;Administration:Administration;General Medicine:General Medicine;Gynacology:Gynacology';
	//var desigOption = ':All;Assistant Engineer:Assistant Engineer;Assistant Director:Assistant Director;Accounts Assistant:Accounts Assistant;Accounts Manager:Accounts Manager;Basic Health Worker:Basic Health Worker';
	var pageWidth= $("#list1").parent().width();  
	var myGrid_List = $("#list1").jqGrid({
	//var mygrid = jQuery("#list1").jqGrid({	
    	url:'/HISPis/pis/pr/transactions/pendingListEmployeeRegistration.action?q=2', 
         //data: { selectedEmpNo: selectedEmpNo },
    	
    	datatype: "json",
         colNames:['Emp. No.', 'Emp. Name', 'Emp. Name in Regional Language', 'Emp. DOB', 'Department', 'Designation', 'Actions'],
         /*colModel:[
                   	{name:'strEmployeeNumber',index:'empNumber',width:(pageWidth*(11/100)), align:"center", sorttype: 'int', colnameview:false, searchoptions:{sopt:['eq','ne','le','lt','gt','ge','nu','in','bw','ew','cn']}},
                   	{name:'strEmployeeFullName',index:'empName',width:(pageWidth*(20/100)), align:'left', searchoptions:{sopt:['eq','ne','le','lt','gt','ge','nu','in','bw','ew','cn']}},
                   	{name:'strEmployeeFullRegionalLangName',index:'empNameRegLang',width:(pageWidth*(20/100)), search:false, align:"left" },
                   	{name:'dtEmployeeDOB',index:'empDoB', width:(pageWidth*(12/100)), align:"center", searchoptions:{sopt:['eq','ne','le','lt','gt','ge','nu','in','bw','ew','cn']} },
                   	{name:'intDepartmentCode',index:'empDept', width:(pageWidth*(12/100)), align:"left", searchoptions:{sopt:['eq','ne','le','lt','gt','ge','nu','in','bw','ew','cn']}, stype:'select', searchoptions:{value:deptOption} },
                   	{name:'intDesignationCode',index:'empdesig', width:(pageWidth*(15/100)), align:"left", searchoptions:{sopt:['eq','ne','le','lt','gt','ge','nu','in','bw','ew','cn']}, stype:'select', searchoptions:{value:desigOption} },
                   	{name:'actions',index:'actions',width:(pageWidth*(10/100)), sortable:false, search:false, resize:false, align:"center"}
                 ],*/
	     colModel:[
	              	{name:'strEmployeeNumber',index:'empNumber',width:(pageWidth*(15/100)), align:"right", sorttype: 'int', searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list1")[0]; sgrid.triggerToolbar(); }, 1);}}]} },
	              	{name:'strEmployeeFullName',index:'empName',width:(pageWidth*(30/100)), align:'left', searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list1")[0]; sgrid.triggerToolbar(); }, 1);}}]} },
	              	{name:'strEmployeeFullRegionalLangName',index:'empNameRegLang', hidden: true, hidedlg: true,search:false, align:"left" },
	              	{name:'dtEmployeeDOB',index:'empDoB', width:(pageWidth*(15/100)), align:"center", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list1")[0]; sgrid.triggerToolbar(); }, 1);}}]} },
	              	{name:'intDepartmentCode',index:'empDept', width:(pageWidth*(15/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list1")[0]; sgrid.triggerToolbar(); }, 1);}}]} },
	              	{name:'intDesignationCode',index:'empdesig', width:(pageWidth*(15/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list1")[0]; sgrid.triggerToolbar(); }, 1);}}]} },
	              	{name:'actions',index:'actions',width:(pageWidth*(10/100)), sortable:false, search:false, resize:false, align:"center"}
	            ],
         rowNum:10,
         rowList:[5,10,20,30,40,50],
         pager:'#pager1',
         sortname: 'empNumber',
         viewrecords: true,
         sortorder: "asc",
         caption:"",
         height: "100%",
         //autowidth:true,
         //forceFit:true,
         //rownumbers: true, 
         autowidth: true,
         //gridview : true,
         //hiddengrid: true,
         subGrid: true,
         subGridOptions: { "plusicon" : "ui-icon-triangle-1-e", "minusicon" : "ui-icon-triangle-1-s", "openicon" : "ui-icon-arrowreturn-1-e", //expand all rows on load "expandOnLoad" : true },
         },
         subGridRowExpanded: function(subgrid_id, row_id) {
             
        	 var html = getEmployeeDetails('SubGrid','Pending', row_id);
        	 //alert("3 ="+html);
             $("#" + subgrid_id).append(html);
         },
         multiselect: true,
         gridComplete: function(){ 
         	var ids = jQuery("#list1").jqGrid('getDataIDs'); 
         	for(var i=0;i < ids.length;i++){ 
         		var cl = ids[i]; 
         		//ve = "<img src='/HIS/hisglobal/images/nonclinical/JqGridValidationDetails4.png' width='18' height='18' style='vertical-align: text-bottom;' title='Validation Details' onclick='toggleValidationDetails();'/>&nbsp;&nbsp;&nbsp;&nbsp;";
         		ae = "<a href='#myPopup' data-rel='popup' class='ui-btn ui-btn-inline ui-corner-all'><img src='/HIS/hisglobal/images/nonclinical/nc_view.png' width='20' height='20' style='vertical-align: text-bottom;cursor: pointer;' title='Registation Slip' onclick=\"openEmployeeDetailsPopup('View','Pending','"+cl+"');\" /></a>&nbsp;&nbsp;&nbsp;&nbsp;";
         		be = "<img src='/HIS/hisglobal/images/nonclinical/nc_right.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Validate' onclick=\"validateRecord('"+cl+"');\" />&nbsp;&nbsp;&nbsp;&nbsp;";
         		se = "<img src='/HIS/hisglobal/images/nonclinical/nc_close.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Reject' onclick=\"rejectRecord('"+cl+"');\" />";
         		ne = "<img src='/HIS/hisglobal/images/nonclinical/nc_pending.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Pending' />";
         		jQuery("#list1").jqGrid('setRowData',ids[i],{actions:ae+be+se});
         		jQuery("#list1").jqGrid('setRowData',ids[i],{status:ne}); 
         		} 
         	}
         
     }).jqGrid('navGrid','#pager1',{add:false,edit:false,del:false,searchtitle:'Search',refreshtitle:'Clear Search'},{}, {}, {}, {multipleSearch:true, multipleGroup:true} );
    
	$("#list1").jqGrid("filterToolbar", {searchOperators : true, stringResult:true,  searchOnEnter:false, afterSearch:function(){ filteredIDs = $("#list1").getDataIDs(); } }); 
 	$("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s', onClickButton:function(){ myGrid_List[0].toggleToolbar(); } }); 
 	$("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"",title:"Reload", buttonicon :'ui-icon-arrowrefresh-1-s', onClickButton:function(){clearEssentialDivs(); $("#list1").trigger("reloadGrid"); } });
 	 jQuery("#list1").jqGrid('navButtonAdd','#pager1',{ caption: "", title: "Reorder Columns", buttonicon : 'ui-icon-folder-open', onClickButton : function (){ jQuery("#list1").jqGrid('columnChooser'); } });
 	//jQuery("#list1").jqGrid('navButtonAdd','#pager1',{caption:"Columns", title: "Reorder Columns", onClickButton : function (){ jQuery("#list1").jqGrid('columnChooser'); } });
    jQuery("#list1").jqGrid('navButtonAdd','#pager1',{caption:"Validate",title:"Validate",buttonicon :'ui-icon-circle-check', onClickButton:function(){ var gsr = jQuery("#list1").jqGrid('getGridParam','selarrrow'); if(gsr!=''){ validateRecord(gsr); } else { /*alert("Please select Row");*/ showAlert ("1", "Please Select Atleast One Row(s)."); } } });
    jQuery("#list1").jqGrid('navButtonAdd','#pager1',{caption:"Reject",title:"Reject",buttonicon :'ui-icon-circle-close', onClickButton:function(){ var gsr = jQuery("#list1").jqGrid('getGridParam','selarrrow'); if(gsr!=''){ rejectRecord(gsr); } else { /*alert("Please select Row");*/ showAlert ("1", "Please Select Atleast One Row(s)."); } } });
   
    jQuery("#list1").jqGrid('gridResize');
    
    //$("#list1").jqGrid("filterToolbar", {/*searchOperators : true,*/ stringResult:true,  searchOnEnter:false, afterSearch:function(){ filteredIDs = $("#list1").getDataIDs(); } }); 
    //jQuery("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"Multiple Search",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s', onClickButton:function(){ mygrid[0].toggleToolbar() } }); 
    //jQuery("#list1").jqGrid('navButtonAdd',"#pager1",{caption:"Multiple Search Clear",title:"Clear Search",buttonicon :'ui-icon-refresh', onClickButton:function(){ mygrid[0].clearToolbar() } }); 
    
}

function showValidatedList()
{
 	//alert("hello Grid 2");
	var pageWidth= $("#list1").parent().width()-53;        
	//var pageWidth= $("#list1").parent().width()-57;        
	var myGrid_List2 = $("#list2").jqGrid({
         url:'/HISPis/pis/pr/transactions/validatedListEmployeeRegistration.action?q=2', 
         //data: { selectedEmpNo: selectedEmpNo },
         datatype: "json",
         colNames:['Emp. No.', 'Emp. Name', 'Emp. Name in Regional Language', 'Emp. DOB', 'Department', 'Designation', 'Actions'],
         colModel:[
                   
                   	{name:'strEmployeeNumber',index:'empNumber',width:(pageWidth*(15/100)), align:"right", sorttype: 'int', searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list2")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                   	{name:'strEmployeeFullName',index:'empName',width:(pageWidth*(30/100)), align:'left', searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list2")[0]; sgrid.triggerToolbar(); }, 1);}}]} },
                   	{name:'strEmployeeFullRegionalLangName',index:'empNameRegLang', hidden: true, hidedlg: true, search:false, align:"left" },
                   	{name:'dtEmployeeDOB',index:'empDoB', width:(pageWidth*(15/100)), align:"center", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list2")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                   	{name:'intDepartmentCode',index:'empDept', width:(pageWidth*(15/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list2")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                   	{name:'intDesignationCode',index:'empdesig', width:(pageWidth*(15/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list2")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                   	//{name:'status',index:'status',width:(pageWidth*(5/100)), sortable:false, search:false, resize:false, align:"center"},
                   	{name:'actions',index:'actions',width:(pageWidth*(10/100)), sortable:false, search:false, resize:false, align:"center"}
                 ],
         rowNum:10,
         rowList:[5,10,20,30,40,50],
         pager:'#pager2',
         sortname: 'empNumber',
         viewrecords: true,
         sortorder: "asc",
         caption:"",
         height: "100%",
         //autowidth:true,
         //forceFit:true,
         //width:pageWidth,
         //hiddengrid: true,
         subGrid: true,
         subGridOptions: { "plusicon" : "ui-icon-triangle-1-e", "minusicon" : "ui-icon-triangle-1-s", "openicon" : "ui-icon-arrowreturn-1-e", //expand all rows on load "expandOnLoad" : true },
         },
         subGridRowExpanded: function(subgrid_id, row_id) {
        	 var html = getEmployeeDetails('SubGrid','Validated', row_id); 
        	 $("#" + subgrid_id).append(html);
         },
         //multiselect: true,
         gridComplete: function(){ 
         	var ids = jQuery("#list2").jqGrid('getDataIDs'); 
         	for(var i=0;i < ids.length;i++){ 
         		var cl = ids[i]; 
         		ae = "<img src='/HIS/hisglobal/images/nonclinical/nc_view.png' width='20' height='20' style='vertical-align: text-bottom;cursor: pointer;' title='Registration Slip' onclick=\"openEmployeeDetailsPopup('View','Validated','"+cl+"');\" />";
         		//be = "<img src='/HISPis/hisglobal/images/JqGridUp.png' width='18' height='18' style='vertical-align: text-bottom;' title='Validated' />";
         		be = "<img src='/HIS/hisglobal/images/nonclinical/nc_right.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Validated' />";
         		jQuery("#list2").jqGrid('setRowData',ids[i],{actions:ae}); 
         		jQuery("#list2").jqGrid('setRowData',ids[i],{status:be}); 
         		} 
         	}
         
     }).jqGrid('navGrid','#pager2',{add:false,edit:false,del:false,searchtitle:'Search',refreshtitle:'Clear Search'},{}, {}, {}, {multipleSearch:true, multipleGroup:true} );
    
	 
	$("#list2").jqGrid("filterToolbar", {searchOperators : true, stringResult:true,  searchOnEnter:false, afterSearch:function(){ filteredIDs = $("#list2").getDataIDs(); } }); 
 	$("#list2").jqGrid('navButtonAdd',"#pager2",{caption:"",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s', onClickButton:function(){ myGrid_List2[0].toggleToolbar(); } }); 
 	$("#list2").jqGrid('navButtonAdd',"#pager2",{caption:"",title:"Reload", buttonicon :'ui-icon-arrowrefresh-1-s', onClickButton:function(){clearEssentialDivs(); $("#list2").trigger("reloadGrid"); } });
 	jQuery("#list2").jqGrid('navButtonAdd','#pager2',{ caption: "", title: "Reorder Columns", buttonicon : 'ui-icon-folder-open', onClickButton : function (){ jQuery("#list2").jqGrid('columnChooser'); } });
 	jQuery("#list2").jqGrid('gridResize');
}

function showRejectedList()
{
	//alert("3-"+$("#list1").parent().width());
	var pageWidth= $("#list1").parent().width()-53; 
	
	var myGrid_List3 = $("#list3").jqGrid({
        url:'/HISPis/pis/pr/transactions/rejectedListEmployeeRegistration.action?q=2', 
        //data: { selectedEmpNo: selectedEmpNo },
        datatype: "json",
        colNames:['Emp. No.', 'Emp. Name', 'Emp. Name in Regional Language', 'Emp. DOB', 'Department', 'Designation', 'Actions'],
        colModel:[
                  	{name:'strEmployeeNumber',index:'empNumber',width:(pageWidth*(15/100)), align:"right", sorttype: 'int', searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list3")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                  	{name:'strEmployeeFullName',index:'empName',width:(pageWidth*(30/100)), align:'left', searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list3")[0]; sgrid.triggerToolbar(); }, 1);}}]} },
                  	{name:'strEmployeeFullRegionalLangName',index:'empNameRegLang', hidden: true, hidedlg: true,search:false, align:"left" },
                  	{name:'dtEmployeeDOB',index:'empDoB', width:(pageWidth*(15/100)), align:"center", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list3")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                  	{name:'intDepartmentCode',index:'empDept', width:(pageWidth*(15/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list3")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                   	{name:'intDesignationCode',index:'empdesig', width:(pageWidth*(15/100)), align:"left", searchoptions:{sopt:['cn','eq','ne','le','lt','gt','ge','nu','in','bw','ew'], dataEvents: [{ type: 'paste', fn: function(e) { setTimeout(function() { var sgrid = $("#list3")[0]; sgrid.triggerToolbar(); }, 1);}}]}  },
                  	//{name:'status',index:'status',width:(pageWidth*(5/100)), sortable:false, search:false, resize:false, align:"center"},
                  	{name:'actions',index:'actions',width:(pageWidth*(10/100)), sortable:false, search:false, resize:false, align:"center"}
                ],
        rowNum:10,
        rowList:[5,10,20,30,40,50],
        pager:'#pager3',
        sortname: 'empNumber',
        viewrecords: true,
        sortorder: "asc",
        caption:"",
        height: "100%",
        //autowidth:true,
        //forceFit:true,
        //width:pageWidth,
        //hiddengrid: true,
        subGrid: true,
        subGridOptions: { "plusicon" : "ui-icon-triangle-1-e", "minusicon" : "ui-icon-triangle-1-s", "openicon" : "ui-icon-arrowreturn-1-e", //expand all rows on load "expandOnLoad" : true },
        },
        subGridRowExpanded: function(subgrid_id, row_id) {
        	var html = getEmployeeDetails('SubGrid','Rejected', row_id); 
        	$("#" + subgrid_id).append(html);
        },
        //multiselect: true,
        gridComplete: function(){ 
        	var ids = jQuery("#list3").jqGrid('getDataIDs'); 
        	for(var i=0;i < ids.length;i++){ 
        		var cl = ids[i]; 
        		ae = "<img src='/HIS/hisglobal/images/nonclinical/nc_view.png' width='20' height='20' style='vertical-align: text-bottom;cursor: pointer;' title='Registration Slip' onclick=\"openEmployeeDetailsPopup('View','Rejected','"+cl+"');\" />";
        		//se = "<img src='/HISPis/hisglobal/images/JqGridDown.png' width='18' height='18' style='vertical-align: text-bottom;'title='Rejected' />";
        		se = "<img src='/HIS/hisglobal/images/nonclinical/nc_close.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Rejected' />";
        		jQuery("#list3").jqGrid('setRowData',ids[i],{actions:ae}); 
        		jQuery("#list3").jqGrid('setRowData',ids[i],{status:se}); 
        		} 
        	}
        
    }).jqGrid('navGrid','#pager3',{add:false,edit:false,del:false,searchtitle:'Search',refreshtitle:'Clear Search'},{}, {}, {}, {multipleSearch:true, multipleGroup:true} );
	$("#list3").jqGrid("filterToolbar", {searchOperators : true, stringResult:true,  searchOnEnter:false, afterSearch:function(){ filteredIDs = $("#list3").getDataIDs(); } });
	$("#list3").jqGrid('navButtonAdd',"#pager3",{caption:"",title:"Toggle Search Toolbar", buttonicon :'ui-icon-pin-s', onClickButton:function(){ myGrid_List3[0].toggleToolbar(); } }); 
 	$("#list3").jqGrid('navButtonAdd',"#pager3",{caption:"",title:"Reload", buttonicon :'ui-icon-arrowrefresh-1-s', onClickButton:function(){clearEssentialDivs(); $("#list3").trigger("reloadGrid"); } });
 	//jQuery("#list3").jqGrid('navButtonAdd','#pager3',{caption: "Columns", title: "Reorder Columns", onClickButton : function (){ jQuery("#list3").jqGrid('columnChooser'); } });
 	jQuery("#list3").jqGrid('navButtonAdd','#pager3',{ caption: "", title: "Reorder Columns", buttonicon : 'ui-icon-folder-open', onClickButton : function (){ jQuery("#list3").jqGrid('columnChooser'); } });
	jQuery("#list3").jqGrid('gridResize');
}


function toggleValidationDetails()
{
	$("#divValidationDetailsId").fadeToggle('slow');
	
}


function viewListReport()
{
	//clearEssentialDivs();
	
var empValidationStatus="", rptheading="";
	
	var currentTab=$('[name="strCurrentTab"]')[0].value;
	//alert("current tab=="+currentTab);
	if(currentTab=='tab1'){
		empValidationStatus='Pending';
		rptheading=$("#divReportHeading1Id").html();
	}
	if(currentTab=='tab2'){
		empValidationStatus='Validated';
		rptheading=$("#divReportHeading2Id").html();
	}
	if(currentTab=='tab3'){
		empValidationStatus='Rejected';
		rptheading=$("#divReportHeading3Id").html();
	} 
	
	var globalRptHeadingOrTitle=rptheading;
	var rptContent=generateReport(empValidationStatus, globalRptHeadingOrTitle);
	openReport("1","divPrintId1", getRptHeading("1",globalRptHeadingOrTitle), rptContent);	
}


function generateReport(empValidationStatus, rptHeadingOrTitle)
{			  
	var headerRow="", dtlRow="", footerRow="";
	var globalRptHeadingOrTitle=rptHeadingOrTitle;
	var globalRptTableOpen=getRptTableOpen("1");
	var globalRptPrintRow=getRptPrintOption("1");
	var globalRptHeaderRow=getRptHeader("1");
	var globalRptDateAndTimeRow="";
	var globalRptHeadingRow=getRptHeading("2",globalRptHeadingOrTitle);
	var globalRptFooterRow=getRptFooter("1");
	var globalRptTableClose=getRptTableClose("1");

		   	
	var action 	= "/HISPis/pis/pr/transactions/getReportDataEmployeeRegistration.action?"+"&empValidationStatus="+empValidationStatus;
	sortandEncodebase64($("#EmployeeRegistration"));
		
	$.ajax({url: action,type:"POST",async:false,dataType:"json",data:$("#EmployeeRegistration").serialize() ,success:function(data)
	{
		var arrJsonObj=data;
		if(arrJsonObj==null ||arrJsonObj==""){
			//alert("Error");
			showAlert ("1", "Error");
		}
		else{
				
			//alert(data['Date']);
			//alert(data['Time']);
			//alert(data['Rows']);
			
			globalRptDateAndTimeRow=getRptDateAndTime("1",data['Date'],data['Time'] );
				var rows=data['Rows'];
			
				headerRow +=" <div class='div-table' style='text-align: center;' align='center' > "+
				globalRptTableOpen+	 
				globalRptPrintRow+
				globalRptHeaderRow+
				" <br> "+
				globalRptDateAndTimeRow+
				" <div class='div-table-row' align='center'> "+
				" &nbsp; "+
				" </div> "+
				globalRptHeadingRow+
				" <br> ";
				
				//===========
				dtlRow += "<div  class='div-table'><div class='div-table-row width100'>" 
				+"<div class='div-table-col control' style='width: 50%;'>" 
				+"<b>&nbsp;Total - "+rows.length+" Record(s)</b>"
				+"</div>"
				+"<div class='div-table-col label' style='width: 50%;'>";
				

				if(rows!="")
				{
					dtlRow += "Report Data Filtered - "+rows[0]['filteredData'];
				}
				
				dtlRow += "&nbsp;&nbsp;</div></div>";
				
				dtlRow += "<div class='div-table-row width100'>"
				+"<div class='div-table-col label' style='width: 100%'><hr size='2' color='#000'></div>"
				+"</div></div>";
				//===================
				
				dtlRow+=	"<div  class='div-table'>"+
	     					
				"<div class='div-table-row'>" +
				"<div class='div-table-col label' style='width: 5%'><center>S.No.</center></div>"+
				"<div class='div-table-col label' style='width: 16%'><center>Emp. No.</center></div>"+
				"<div class='div-table-col label' style='width: 16%; text-align:left;'>Emp. Name</div>"+
//				"<div class='div-table-col label' style='width: 16%; text-align:left;'>Emp. Name in Regional Language</div>"+
				"<div class='div-table-col label' style='width: 15%'><center>Employe DoB</center></div>"+
				"<div class='div-table-col label' style='width: 16%; text-align:left;'>Department</div>"+
				"<div class='div-table-col label' style='width: 16%; text-align:left;'>Designation</div>" +
				//"<div class='div-table-col label' style='width: 14%'><center>Old Employment Reference No.</center></div>" +
				"</div>"+
				"<div class='div-table-row width100'>"+
				"<div class='div-table-col label' style='width: 100%'><hr size='2' color='#000'></div>"+
				"</div>";
			
			var loopFlag=0;
			for (i in rows)
			{				
				dtlRow+="<div class='div-table-row'>" +
				"<div class='div-table-col label' style='width: 5%'><center>"+(parseInt(i)+1)+"</center></div>"+
				"<div class='div-table-col control' style='width: 16%'><center>"+rows[i]['empNumber']+"</center></div>"+
				"<div class='div-table-col control' style='width: 16%'>"+rows[i]['empName']+"</div>"+
//				"<div class='div-table-col control' style='width: 16%'>"+rows[i]['empNameRegLang']+"</div>"+
				"<div class='div-table-col control' style='width: 15%'><center>"+rows[i]['empDoB']+"</center></div>"+
				"<div class='div-table-col control' style='width: 16%'>"+rows[i]['empDept']+"</div>"+
				"<div class='div-table-col control' style='width: 16%'>"+rows[i]['empdesig']+"</div>" +
				//"<div class='div-table-col control' style='width: 14%'><center>"+rows[i]['strOldEmployeeNumber']+"</center></div>" +
				"</div>";
				loopFlag=1;
			}
			
			if(loopFlag==0) {dtlRow +=	"<div class='div-table-row'><div class='div-table-col control' style='width: 100%;text-align: center;'><center><b>No Record Found</b></center></div></div>";}
							
		}
	},error: function(errorMsg,textstatus,errorthrown) 
	{
		//alert('getReportDtls '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		//showAjaxError("saveEmployeeDtl " + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
		showtamperErrorPage("saveEmployeeDtl " + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
	}
	});
		 
	dtlRow+="<div class='div-table-row width100'>"+
	"<div class='div-table-col label' style='width: 100%'><hr size='2' color='#000'></div>"+
	"</div>"+		
	"</div>";
	
footerRow+= " <br> "+globalRptFooterRow  + globalRptTableClose + " </div>";
	
	return headerRow+dtlRow+footerRow;
  
}


function openEmployeeDetailsPopup(reportOpenLocation, empValidationStatus, empNumber)
{
	var globalRptHeadingOrTitle=$("#divReportHeading4Id").html();
	if(reportOpenLocation=='RegSlip')
		{globalRptHeadingOrTitle="EMPLOYEE REGISTRATION SLIP";}
	else
		{globalRptHeadingOrTitle=$("#divReportHeading4Id").html();}
	var rptContent=getEmployeeDetails(reportOpenLocation,empValidationStatus,empNumber);
	openReport("1","divPrintId1", getRptHeading("1",globalRptHeadingOrTitle), rptContent);		
		
}

function getEmployeeDetails(reportOpenLocation, empValidationStatus, empNumber)
{
	//alert("Emp. No. = "+empNumber);
	//alert("Emp. Validation Status = "+empValidationStatus);
	var arrJsonObj;
	var result="";
	
	if(empNumber=="" ||empValidationStatus==""){
		//alert("Please Enter Any One Criteria..!");
		showAlert ("1", "Please Enter Any One Criteria..!");
		return;
	}

	var action 	= "/HISPis/pis/pr/transactions/getEmpDtlForValidationEmployeeRegistration.action?"+"&empNumber="+empNumber+"&empValidationStatus="+empValidationStatus;

	$.ajax({url: action,type:"POST",async:false,dataType:"json" ,success:function(data)
	{
		arrJsonObj=data;
		if(arrJsonObj==null ||arrJsonObj==""){
			//alert("Error");
			showAlert ("1", "Error");
		}
		else{
			result= createEmployeeRow(data, reportOpenLocation);			
		}
		
		
	},error: function(errorMsg,textstatus,errorthrown) 
	{
		alert('getEmployeeDetails '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	}
	});
	
	
	/*
	if(reportOpenLocation=='View')
	{
		//alert("reportOpenLocation=view");		
		$("#rptPrintImageDivId").show();
		$("#divHospitalDtlId").show();
		$("#divDateTimeId").show();
		$("#divReportHeadingId").show();		
		
	}
	else if(reportOpenLocation=='RegSlip')
	{
		
		$("#rptPrintImageDivId").show();
		$("#divHospitalDtlId").show();
		$("#divDateTimeId").show();
		$("#divReportHeadingId").show();	
		
		var headerRow="", dtlRow="", footerRow="";
		
		headerRow +=" <div class='div-table' style='text-align: center;' align='center' > "+
		" <center> "+
		" <div id='reportForm' style='display:table;width: 95%;background-color: white;font-size: 10px; text-align: center;' align='center'> "+	 
		" <div class='div-table-row' id='rptPrintImageDivId'> "+
		" <div class='div-table-col label' style='width: 100%; right:12px; '> "+
		" <img src='/HIS/hisglobal/images/nonclinical/nc_printer.png' width='25' height='25' onclick='printReport();' style='vertical-align: middle;cursor:pointer;' title='Print' /> "+
		" </div> "+
		" </div> "+
		" <div class='div-table-row' align='center'>&nbsp; </div> "+			
		" <div id='divHospitalName' class='div-table-row' align='center'> "+$('#hospitalName').val()+" </div> "+
		" <div id='divHospitalAdd1' class='div-table-row' align='center'> "+$('#address1').val()+" </div> "+
		" <div id='divHospitalAdd2' class='div-table-row' align='center'> "+$('#address2').val()+" </div> "+
		" <div id='divHospitalCity' class='div-table-row' align='center'> "+$('#city').val()+", "+$('#state').val() +
		" </div> "+
		" <br> "+
		" <div class='div-table-row' align='right'> "+
		$('#strDateTimeLabel').val()+"&nbsp;:&nbsp;<div id='divRptDateId' style='font-style: normal;font-weight:normal;display: inline;'>"+arrJsonObj['Date']+"</div>/<div id='divRptTimeId' style='font-style: normal;font-weight:normal;display: inline;'>"+arrJsonObj['Time']+"</div>&nbsp;&nbsp;&nbsp;&nbsp; "+
		" </div> "+
		" <div class='div-table-row' align='center'> "+
		" &nbsp; "+
		" </div> "+
		" <div id='divheadingId' class='div-table-row' align='center'> "+
		" <u> EMPLOYEE REGISTRATION SLIP </u> "+
		" </div> "+
		" <div  class='div-table-row' align='center'> "+
		" &nbsp; "+
		" </div> "+
		" <br><br> ";
		
			
		dtlRow +=	"<div class='div-table-row'>" +
					result+
					"</div>";
		
		
		footerRow+= " <br> "+
		" <div class='div-table-row' align='center'> "+
		$('#strRptFooter').val()+"<br>"+$('#strRptFooterCGF').val()+
		" </div> ";
		
		result = headerRow+dtlRow+footerRow;
		
	}
	else
	{
		$("#rptPrintImageDivId").hide();
		$("#divHospitalDtlId").hide();
		$("#divDateTimeId").hide();
		$("#divReportHeadingId").hide();		
		result=" <div class='wrapper rounded' > " +result+" </div> ";
	}
	*/
	
	if(reportOpenLocation!='View' && reportOpenLocation!='RegSlip' ) { result=" <div class='wrapper rounded' > " +result+" </div> ";}
	
	return result;
}


function createEmployeeRow(data, reportOpenLocation)
{
	var headerRow="", dtlRow="", footerRow="";
	var arrEmpJsonObj=data['Rows'];
	var globalRptHeadingOrTitle=$("#divReportHeading4Id").html();
	if(reportOpenLocation=='RegSlip')
		{globalRptHeadingOrTitle="EMPLOYEE REGISTRATION SLIP";}
	else
		{globalRptHeadingOrTitle=$("#divReportHeading4Id").html();}
	var globalRptTableOpen=getRptTableOpen("1");
	var globalRptPrintRow=getRptPrintOption("1");
	var globalRptHeaderRow=getRptHeader("1");
	var globalRptDateAndTimeRow=getRptDateAndTime("1",data['Date'],data['Time'] );
	var globalRptHeadingRow=getRptHeading("2",globalRptHeadingOrTitle);
	var globalRptFooterRow=getRptFooter("1");
	var globalRptTableClose=getRptTableClose("1");

		headerRow +=" <div class='div-table' style='text-align: center;' align='center' > "+
		globalRptTableOpen; 
		if(reportOpenLocation=='View' || reportOpenLocation=='RegSlip')
		{
			headerRow +=globalRptPrintRow+
			globalRptHeaderRow+
			" <br> "+
			globalRptDateAndTimeRow+
			" <div class='div-table-row' align='center'> "+
			" &nbsp; "+
			" </div> "+
			globalRptHeadingRow+
			" <br> ";
		}
		else {
			headerRow +=" <br> ";
		}
		
		
	
	if(arrEmpJsonObj!="")
	{
		for (i in arrEmpJsonObj)
		{
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Emp. No.</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['empNumber']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Is Existing Emp. ?</div>"+
								"<div class='div-table-col label alignCenter' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strIsExistingEmployee']+"</div>" +
							"</div>";
	
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Last Employment Type</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strLastEmploymentType']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Nature Of Job</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['intNatureOfJobId']+"</div>" +
							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Appellation 1</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['intAppellationCode1']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Appellation 2</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['intAppellationCode2']+"</div>" +
							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Emp. Full Name</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['empName']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Emp. Short Name</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strEmployeeShortName']+"</div>"+
							"</div>";
//			
//			dtlRow +=	"<div class='div-table-row'>" +
//								"<div class='div-table-col label' style='width: 24%'>Emp. Short Name</div>"+
//								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
//								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strEmployeeShortName']+"</div>"+
//								"<div class='div-table-col label' style='width: 24%'>Emp. Short Name in Regional Language</div>"+
//								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
//								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strEmployeeShortRegionalLangName']+"</div>" +
//							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								/*"<div class='div-table-col label' style='width: 24%'>Suffix</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['intSuffixCode']+"</div>"+*/
								"<div class='div-table-col label' style='width: 24%'>Date of Birth</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['empDoB']+"</div>" +
								"<div class='div-table-col label' style='width: 24%'></div>"+
								"<div class='div-table-col label' style='width: 2%'><center></center></div>"+
								"<div class='div-table-col control' style='width: 24%'></div>" +
							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Gender</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strGenderCode']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Nationality</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['intNationalityId']+"</div>" +
							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Department</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['empDept']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Designation</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['empdesig']+"</div>" +
							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Old Employment Reference No.</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strOldEmployeeNumber']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Emp. Final Status</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strEmployeeFinalStatus']+"</div>" +
							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>Mobile No.</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['intMobileNumber']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'>Email Id</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strPersonalEmailId']+"</div>" +
							"</div>";
			
			dtlRow +=	"<div class='div-table-row'>" +
								"<div class='div-table-col label' style='width: 24%'>PAN Card No.</div>"+
								"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
								"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strPANNumber']+"</div>"+
								"<div class='div-table-col label' style='width: 24%'></div>"+
								"<div class='div-table-col label' style='width: 2%'><center></center></div>"+
								"<div class='div-table-col control' style='width: 24%'></div>" +
							"</div>";
			
			if(reportOpenLocation!='RegSlip')
			{			
				if(arrEmpJsonObj[i]['strValidateStatus']=='Pending')
				{
					dtlRow +=	"<div id='divValidatorDtls1Id' class='div-table-row'>" +
										"<div class='div-table-col label' style='width: 24%'>Validation Status</div>"+
										"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
										"<div class='div-table-col control' style='width: 24%'><img src='/HIS/hisglobal/images/nonclinical/nc_pending.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Pending'/></div>"+
										"<div class='div-table-col label' style='width: 24%'>Validation Date</div>"+ 
										"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
										"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['dtValidateDate']+"</div>" +
									"</div>";
				}
				else if(arrEmpJsonObj[i]['strValidateStatus']=='Validated')
				{
					dtlRow +=	"<div id='divValidatorDtls1Id' class='div-table-row'>" +
										"<div class='div-table-col label' style='width: 24%'>Validation Status</div>"+
										"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
										"<div class='div-table-col control' style='width: 24%'><img src='/HIS/hisglobal/images/nonclinical/nc_right.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Validated' /></div>"+
										"<div class='div-table-col label' style='width: 24%'>Validation Date</div>"+ 
										"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
										"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['dtValidateDate']+"</div>" +
									"</div>";
				}
				else if(arrEmpJsonObj[i]['strValidateStatus']=='Rejected')
				{
					dtlRow +=	"<div id='divValidatorDtls1Id' class='div-table-row'>" +
										"<div class='div-table-col label' style='width: 24%'>Validation Status</div>"+
										"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
										"<div class='div-table-col control' style='width: 24%'><img src='/HIS/hisglobal/images/nonclinical/nc_close.png' width='18' height='18' style='vertical-align: text-bottom;cursor: pointer;' title='Rejected' /></div>"+
										"<div class='div-table-col label' style='width: 24%'>Validation Date</div>"+ 
										"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
										"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['dtValidateDate']+"</div>" +
									"</div>";
				}
				
				dtlRow +=	"<div id='divValidatorDtls2Id' class='div-table-row'>" +
									"<div class='div-table-col label' style='width: 24%'>Validated By</div>"+
									"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
									"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strValidatorName']+"</div>"+
									"<div class='div-table-col label' style='width: 24%'>Validation Remarks</div>"+ 
									"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
									"<div class='div-table-col control' style='width: 24%'>"+arrEmpJsonObj[i]['strValidatorRemarks']+"</div>" +
								"</div>";
			}	
		}
	}
	else{
		dtlRow +=	"<div class='div-table-row'><div class='div-table-col control' style='width: 100%'><center><b>No Record Found</b></center></div></div>";
	}
	
	footerRow+= " <br> "+globalRptFooterRow  + globalRptTableClose + " </div>";
	
	return headerRow+dtlRow+footerRow;
	
}

function getValidatorDetails()
{
	//alert("Inside getValidatorDetails function");
	var arrEmpJsonObj="";
	var action 	= "/HISPis/pis/common/transactions/getValidatorDetailsUtilityFunc.action?";

	$.ajax({url: action,type:"POST",async:false,dataType:"json" ,success:function(data)
	{
		arrEmpJsonObj=data;

	},error: function(errorMsg,textstatus,errorthrown) 
	{
		alert('getValidatorDetails '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	}
	});
	
	if(arrEmpJsonObj!="")
	{
		for (i in arrEmpJsonObj)
		{
			$('[name="strValidateId"]')[0].value=arrEmpJsonObj[i]['validatorId'];
			$('[name="strValidatorName"]')[0].value=arrEmpJsonObj[i]['validatorName'];
		}
	}
	else
	{
		alert('getValidatorDetails - No Validator Details Found');
	}
}

function clearEssentialDivs()
{
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
}


//Updated by Ashwini Mishra on 20-04-2015
function reloadList() 
{	
	$('#list1').trigger('reloadGrid');	
	$('#list2').trigger('reloadGrid');	
	$('#list3').trigger('reloadGrid');	
		
}

function validateRecord(empNo)
{
	$('[name="strEmployeeNumber"]')[0].value=empNo;
	
	if($('[name="strEmployeeNumber"]')[0].value!="")
	{
		//var didConfirm = confirm("Are you sure to validate registration details of following Emp. No.(s) - "+$('[name="strEmployeeNumber"]')[0].value);
		//if (didConfirm == true)
		showConfirmDialog("1", "Are You Sure to Validate Registration Details of following Employee Number(s) - "+$('[name="strEmployeeNumber"]')[0].value, "", function()
		{
			$('[name="strValidateStatus"]')[0].value="Y";
			empregvalidate.validateEmployeeRegDtl();
		});
	}
	else
	{
		//alert("Emp. No.(s) is mandatory for validation!");
		showAlert ("1", "Emp. No.(s) is mandatory for Validation");
	}
}

function rejectRecord(empNo)
{
	//alert("Inside rejectRecord with Emp No (s) -"+empNo);
	$('[name="strEmployeeNumber"]')[0].value=empNo;
	
	if($('[name="strEmployeeNumber"]')[0].value!="")
	{
		//var didConfirm = confirm("Are you sure to reject registration details of following Emp. No.(s) - "+$('[name="strEmployeeNumber"]')[0].value);
		//if (didConfirm == true)
		showConfirmDialog("1", "Are You Sure to Reject Registration Details of following Employee Number(s) - "+$('[name="strEmployeeNumber"]')[0].value, "", function()
		{
			$('[name="strValidateStatus"]')[0].value="N";
			empregvalidate.validateEmployeeRegDtl();
		});
	}
	else
	{
		//alert("Emp. No.(s) is mandatory for rejection!");
		showAlert ("1", "Emp. No.(s) is mandatory for Rejection");
	}
}

