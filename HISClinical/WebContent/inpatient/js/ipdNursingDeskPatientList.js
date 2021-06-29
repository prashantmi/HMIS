var flag="1";
var persistRowFlag="1";
var nursingFlag="0";

//Added by Vasu to get Sys Date & Time
function AddZero(num) {
    return (num >= 0 && num < 10) ? "0" + num : num + "";
}

function showPatientList()
{	 
	/*var currentdate = new Date(); 
	var datetime =  AddZero(currentdate.getDate()) + "-"
	                + AddZero((currentdate.getMonth()+1))  + "-" 
	                + currentdate.getFullYear() + " "  
	                + AddZero(currentdate.getHours()) + ":"  
	                + AddZero(currentdate.getMinutes()) + ":" 
	                + AddZero(currentdate.getSeconds());
	
	document.getElementsByName("currDateAndTime")[0].value = datetime;*/
	//alert(document.getElementsByName("currDateAndTime")[0].value);
	
	var pageWid= Math.round($(parent.window).innerWidth() - 172) + "px";
	var pageHeight= (window.innerHeight-115)+'px';
	document.getElementById("divPatientListId").style.width=pageWid;	
	document.getElementById("divPatientListId").style.height=pageHeight;
	var pageWidth= $("#list1").parent().width(); 
	var _mode= "AJX_G_PATIENTLIST";  	
	var tempdepartmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var departmentUnitCode=new Array();
	departmentUnitCode = tempdepartmentUnitCode.toString().split("^");
	var wardCode = document.getElementsByName("wardCode")[0].value;
	var roomCode = document.getElementsByName("roomCode")[0].value;
	var srchCriteria = document.getElementsByName("srchCriteria")[0].value;
	var srchValue = document.getElementsByName("srchValue")[0].value;
	var services = document.getElementsByName("services")[0].value;
	nursingFlag = document.getElementsByName("services")[0].value;
    var height = $(document).height();	
    var count=1;
		$("#list1").jqGrid({
			url:createFHashAjaxQuery('/HISClinical/inpatient/ipdNursingDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&wardCode="+wardCode+"&roomCode="+roomCode+"&services="+services),    	
	    	datatype: "json",
	         colNames:['','','Admission No', 'Patient Name', 'CR Number', 'Gender/Age','Category', 'Department/Unit', 'Admission Date', 'Ward', 'Room', 'Bed', 'Balance','recent'],
		     colModel:[
		               
		              	{name:'listId',index:'listId',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'listKey',index:'listKey',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'patAdmNo',index:'patAdmNo',width:(pageWidth*(10/100)),search:false,  align:'center', formatter:highlight},
		              	{name:'patName',index:'patName',width:(pageWidth*(15/100)), search:true, align:"left", formatter:highlight},
		              	{name:'patCrNo',index:'patCrNo',width:(pageWidth*(11/100)), search:false, align:"center", sorttype: 'int', formatter:highlight},
		              	{name:'patgenderage',index:'patgenderage', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		            	{name:'patprimarycat',index:'patprimarycat', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'departmentUnitName',index:'departmentUnitName', width:(pageWidth*(17/100)),search:false, align:"center", formatter:highlight},	              	
		              	{name:'admDateTime',index:'admDateTime',width:(pageWidth*(16/100)), search:false, align:"center", formatter:highlight},
		              	{name:'wardName',index:'wardName',width:(pageWidth*(8/100)), sorttype:'string', search:true, align:"center", formatter:highlight},
		              	{name:'roomName',index:'roomName', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'bedName',index:'bedName', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'patBalance',index:'patBalance', width:(pageWidth*(7/100)), search:false, align:"center", hidden:false, formatter:highlight},
		              	{name:'sort',index:'sort', width:(pageWidth*(0/100)),align:"center", hidden:true, formatter:highlight}
		            ],
				     rowNum:10,       
				     rowList:[10,25,50,75,100],
	         pager:'#pager1',
	         multiSort: true,
	         sortname:'sort',
	         viewrecords: true,
	         height: (height-115),
	         autowidth: true,
	         autoheight: true,
	         gridview: true,
	         onSelectRow: function(ids) {
	         	
	            	 checkForOnSelectDeskListItem(ids); 
	             },   
	             onSortCol: function(index,iCol,sortorder)
	             {
	            	//alert(iCol+sortorder);
	            	if(iCol==10 || index=="patBalance")
	            	{
	            		jQuery("#list1").setGridParam({sortname:'to_number(patBalance)'});
	            	}
	             },
		    loadComplete:  function() {
		    	//setPersistRow();
	            },
				
	     });
    jQuery("#list1").jqGrid('gridResize');
    //desc , patName , patCrNo , departmentUnitName , wardName , patgenderage 
}

function showNonAcceptancePatientList()
{	
	//alert("All Patient List For Toaday ");
	var pageWidth= $("#list1").parent().width(); 
	var _mode= "AJX_G_PATIENTLIST";  	
	//var departmentUnitCode = "0";
	var visitType = "0";	
	//var wardCode = "0";
	//var roomCode = "0";
	var tempdepartmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var departmentUnitCode=new Array();
	departmentUnitCode = tempdepartmentUnitCode.toString().split("^");
	var wardCode = document.getElementsByName("wardCode")[0].value;
	var roomCode = document.getElementsByName("roomCode")[0].value;
	var srchCriteria = document.getElementsByName("srchCriteria")[0].value;
	var srchValue = document.getElementsByName("srchValue")[0].value;
	var services = document.getElementsByName("services")[0].value;
    var height = $(document).height();
    var count=1;
		$("#list1").jqGrid({
			url:createFHashAjaxQuery('/HISClinical/inpatient/ipdNursingDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&wardCode="+wardCode+"&roomCode="+roomCode+"&services="+services),    	
	    	datatype: "json",
	         colNames:['','','Admission No', 'Patient Name', 'CR Number', 'Gender/Age','Category', 'Department/Unit', 'Admission Date', 'Ward', 'Transfer from', 'Balance','sort'],
		     colModel:[
		              	{name:'listId',index:'listId',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'listKey',index:'listKey',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'patAdmNo',index:'patAdmNo',width:(pageWidth*(9/100)),search:false,  align:'center', formatter:highlight},
		              	{name:'patName',index:'patName',width:(pageWidth*(17/100)), search:true, align:"left", formatter:highlight},
		              	{name:'patCrNo',index:'patCrNo',width:(pageWidth*(11/100)), search:false, align:"center", sorttype: 'int', formatter:highlight},
		              	{name:'patgenderage',index:'patgenderage', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'patprimarycat',index:'patprimarycat', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'departmentUnitName',index:'departmentUnitName', width:(pageWidth*(17/100)),search:false, align:"center", formatter:highlight},	              	
		              	{name:'admDateTime',index:'admDateTime',width:(pageWidth*(15/100)), search:false, align:"center", formatter:highlight},
		              	{name:'wardName',index:'wardName',width:(pageWidth*(8/100)), sorttype:'string', search:true, align:"center", formatter:highlight},
		              	{name:'transferFrom',index:'transferFrom', width:(pageWidth*(10/100)),search:false, align:"center", formatter:highlight},	              	
		              	{name:'patBalance',index:'patBalance', width:(pageWidth*(9/100)), search:false, align:"center", hidden:false, formatter:highlight},
		              	{name:'sort',index:'sort', width:(pageWidth*(0/100)),align:"center", hidden:true, formatter:highlight}
		            ],
				     rowNum:10,       
				     rowList:[10,25,50,75,100],
	         pager:'#pager1',
	         multiSort: true,
	         sortname: 'sort',
	         viewrecords: true,
	         height: (height-115),
	         autowidth: true,
	         autoheight: true,
	         gridview: true,
	         onSelectRow: function(ids) {
	         	
	            	 checkForOnSelectDeskListItem(ids); 
	             },    
	             onSortCol: function(index,iCol,sortorder)
	             {
	            	//alert(iCol+sortorder);
	            	if(iCol==10 || index=="patBalance")
	            	{
	            		jQuery("#list1").setGridParam({sortname:'to_number(patBalance)'});
	            	}
	             },
		    loadComplete:  function() {
		    	//setPersistRow();
	            },
				
	     });
    jQuery("#list1").jqGrid('gridResize');
}

function showLeavePatientList()
{	
	//alert("All Patient List For Toaday ");
	var pageWidth= $("#list1").parent().width(); 
	var _mode= "AJX_G_PATIENTLIST";
	var visitType = "0";	
	var tempdepartmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var departmentUnitCode=new Array();
	departmentUnitCode = tempdepartmentUnitCode.toString().split("^");
	var wardCode = document.getElementsByName("wardCode")[0].value;
	var roomCode = document.getElementsByName("roomCode")[0].value;
	var srchCriteria = document.getElementsByName("srchCriteria")[0].value;
	var srchValue = document.getElementsByName("srchValue")[0].value;
	var services = document.getElementsByName("services")[0].value;
    var height = $(document).height();
    var count=1;
		$("#list1").jqGrid({
			url:createFHashAjaxQuery('/HISClinical/inpatient/ipdNursingDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&wardCode="+wardCode+"&roomCode="+roomCode+"&services="+services),    	
	    	datatype: "json",
	         colNames:['','','Admission No', 'Patient Name', 'CR Number', 'Gender/Age','Category', 'Admission Date','Ward', 'Bed','Balance','sort'],
		     colModel:[
		              	{name:'listId',index:'listId',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'listKey',index:'listKey',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'patAdmNo',index:'patAdmNo',width:(pageWidth*(9/100)),search:false,  align:'center', formatter:highlight},
		              	{name:'patName',index:'patName',width:(pageWidth*(17/100)), search:true, align:"left", formatter:highlight},
		              	{name:'patCrNo',index:'patCrNo',width:(pageWidth*(11/100)), search:false, align:"center", sorttype: 'int', formatter:highlight},
		              	{name:'patgenderage',index:'patgenderage', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'patprimarycat',index:'patprimarycat', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'admDateTime',index:'admDateTime',width:(pageWidth*(8/100)), search:false, align:"center", formatter:highlight},
		              	{name:'wardName',index:'wardName', width:(pageWidth*(17/100)),search:false, align:"center", formatter:highlight},	              	
		              	{name:'bedName',index:'bedName', width:(pageWidth*(17/100)),search:false, align:"center", formatter:highlight},	              	
		              	{name:'patBalance',index:'patBalance', width:(pageWidth*(9/100)), search:false, align:"center", hidden:false, formatter:highlight},
		              	{name:'sort',index:'sort', width:(pageWidth*(0/100)),align:"center", hidden:true, formatter:highlight}
		            ],
				     rowNum:10,       
				     rowList:[10,25,50,75,100],
	         pager:'#pager1',
	         multiSort: true,
	         sortname: 'sort',
	         viewrecords: true,
	         height: (height-115),
	         autowidth: true,
	         autoheight: true,
	         gridview: true,
	         onSelectRow: function(ids) {
	         	
	            	 checkForOnSelectDeskListItem(ids); 
	             },    
	             onSortCol: function(index,iCol,sortorder)
	             {
	            	//alert(iCol+sortorder);
	            	if(iCol==10 || index=="patBalance")
	            	{
	            		jQuery("#list1").setGridParam({sortname:'to_number(patBalance)'});
	            	}
	             },
		    loadComplete:  function() {
		    	//setPersistRow();
	            },
				
	     });
    jQuery("#list1").jqGrid('gridResize');
}

function showInTransitPatientList()
{	 
	//alert("All Patient List For Toaday ");
	var pageWidth= $("#list1").parent().width(); 
	var _mode= "AJX_G_PATIENTLIST";
	var visitType = "0";
	var tempdepartmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var departmentUnitCode=new Array();
	departmentUnitCode = tempdepartmentUnitCode.toString().split("^");
	var wardCode = document.getElementsByName("wardCode")[0].value;
	var roomCode = document.getElementsByName("roomCode")[0].value;
	var srchCriteria = document.getElementsByName("srchCriteria")[0].value;
	var srchValue = document.getElementsByName("srchValue")[0].value;
	var services = document.getElementsByName("services")[0].value;
    var height = $(document).height();
    var count=1;
		$("#list1").jqGrid({
			url:createFHashAjaxQuery('/HISClinical/inpatient/ipdNursingDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&wardCode="+wardCode+"&roomCode="+roomCode+"&services="+services),    	
	    	datatype: "json",
	         colNames:['','','Admission No', 'Patient Name', 'CR Number', 'Gender/Age','Transfer To Dept/Unit', 'Ward', 'Room', 'Transfer Date Time', 'Balance','sort'],
		     colModel:[
		              	{name:'listId',index:'listId',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'listKey',index:'listKey',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
		              	{name:'patAdmNo',index:'patAdmNo',width:(pageWidth*(9/100)),search:false,  align:'center', formatter:highlight},
		              	{name:'patName',index:'patName',width:(pageWidth*(15/100)), search:true, align:"left", formatter:highlight},
		              	{name:'patCrNo',index:'patCrNo',width:(pageWidth*(11/100)), search:false, align:"center", sorttype: 'int', formatter:highlight},
		              	{name:'patgenderage',index:'patgenderage', width:(pageWidth*(7/100)), search:false, align:"center", formatter:highlight},
		              	{name:'departmentUnitName',index:'departmentUnitName', width:(pageWidth*(15/100)),search:false, align:"center", formatter:highlight},	              	
		              	{name:'warName',index:'warName',width:(pageWidth*(8/100)), sorttype:'string', search:true, align:"center", formatter:highlight},
		              	{name:'roomName',index:'roomName',width:(pageWidth*(7/100)), sorttype:'string', search:true, align:"center", formatter:highlight},
		              	{name:'transDateTime',index:'transDateTime',width:(pageWidth*(8/100)), search:false, align:"center", formatter:highlight},
		              	{name:'patBalance',index:'patBalance', width:(pageWidth*(9/100)), search:false, align:"center", hidden:false, formatter:highlight},
		              	{name:'sort',index:'sort', width:(pageWidth*(0/100)),align:"center", hidden:true, formatter:highlight}
		            ],
				     rowNum:10,       
				     rowList:[10,25,50,75,100],
	         pager:'#pager1',
	         multiSort: true,
	         sortname: 'sort',
	         viewrecords: true,
	         height: (height-115),
	         autowidth: true,
	         autoheight: true,
	         gridview: true,
	         onSelectRow: function(ids) {
	         	
	            	 checkForOnSelectDeskListItem(ids); 
	             },    
	             onSortCol: function(index,iCol,sortorder)
	             {
	            	//alert(iCol+sortorder);
	            	if(iCol==10 || index=="patBalance")
	            	{
	            		jQuery("#list1").setGridParam({sortname:'to_number(patBalance)'});
	            	}
	             },
		    loadComplete:  function() {
		    	//setPersistRow();
	            },
				
	     });
    jQuery("#list1").jqGrid('gridResize');
}

var color; //color for attented patient row
var discharge = "0"; // for discharged pateint
//added by manisha gangwr, date : 02.Nov.2015   reason: to highlight cell by pink color when balance is 0.0
//modified by Neha Rajgariya  dtae:- 22.April.2016  Purpose:- to show discharged pateint as bold and italic
//modified by Shruti Shail date:-26.Nov-2016	Purpose: To show low balance only when patient cateogry is paying type
//modified by Shruti Shail date:-26.Nov-2016	Purpose: to change the colour of discharge requested patients
function highlight (cellValue, options, rowObject) {
	
	if(cellValue)
	
	if(cellValue.charAt(15)=="@")
	{
		var cellId=new Array();
		cellId = cellValue.toString().split("@");
		if(cellId[9] == 0)
		{
			if(cellId[7] <= 0.0)
			{
				color = "hotpink";
			}
		}
		else
		{
				color = "black"; 
		}
		if(cellId[8] == 18 )
		{
			discharge = "1";
			color = "green";
		}
		else
		{
			discharge = "0";
		}
	}
	
	if(discharge == "1" )
	{
		var cellHtml = "<span style='font-style:" + "italic" +";"+"font-weight:"+"bold"+";"+";"+" color:"+color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	}
	else
	{
		var cellHtml = "<span style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";  
	}

   return cellHtml;

};


//added by akash
//var color; //color for attented patient row
//var attended="1";
/*function highlight (cellValue, options, rowObject) {
	if(cellValue)
	//var check=cellValue.charAt(13);
	//alert(cellValue.toString().split("@")[5]);
	//var color = (parseInt(cellValue) < 10000) ? "black" : "black";
	if(cellValue.charAt(13)=="@")
	{ //var color = (parseInt(cellValue) < 10000) ? "black" : "red";
		var cellId=new Array();
		cellId = cellValue.toString().split("@");
		//alert(cellId[5]);
		if(cellId[2]=="1")
		{
			 color = "xxx";
			if(cellId[5]=="2"){ color = "blue";	attended="2";}
			else{ color = "xxx"; attended="1";}			 
		}
		else if(cellId[5]=="2"){ color = "black";	attended="2";}
		else{color = "black"; attended="1";}
	}
	
	else if(cellValue.charAt(1)=="@")
	{ 			
		var cellId=new Array();
		cellId = cellValue.toString().split("@");
		//alert(cellId[5]);
		if(cellId[0]=="1")
		{
			 color = "black";//"red";		 
		}
	}
	//else{var color = (parseInt(cellValue) < 10000) ? "black" : "black";}
	if(color=="red")
	{
		if(attended=="2")
		{
			var cellHtml = "<span style='font-style:" + "italic" +";"+" color:"+color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		}
		else
		{
			var cellHtml = "<span style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		}
   	}	
	//if(color=="red")
	//{
		//var cellHtml = "<span style='font-style:" + "italic"  + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
	//	var cellHtml = "<span style='font-style:" + "italic" +";"+" color:"+color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		//var cellHtml = "<span style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
   		//var cellHtml = "<span style='background-color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
   		//color: aqua; font-style: italic; font: bold
   	//}
  	else
	{
		if(attended=="2")
		{
			var cellHtml = "<span style='font-style:" + "italic" +";"+" color:"+color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";
		}
		else
		{
			var cellHtml = "<span style='color:" + color + "' originalValue='" + cellValue + "'>" + cellValue + "</span>";			
		}  	
	}
   return cellHtml;

};
*/
function filterPatientList()
{
	// fetch filter data
	//alert("called..")
	parent.document.getElementById("frmDynamicDeskHeader").contentWindow.setPatStats(); // For Rfreshing Desk Statics
	var tempdepartmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var departmentUnitCode=new Array();
	departmentUnitCode = tempdepartmentUnitCode.toString().split("^");
	var wardCode = document.getElementsByName("wardCode")[0].value;
	var roomCode = document.getElementsByName("roomCode")[0].value;
	var srchCriteria = document.getElementsByName("srchCriteria")[0].value;
	var srchValue = document.getElementsByName("srchValue")[0].value;
	var services = document.getElementsByName("services")[0].value;

	//alert("Search Criteria:-"+ srchCriteria|"   Search Value:-"+srchValue);
	//alert("departmentUnitCode:-"+departmentUnitCode[0]+"  wardCode:-"+wardCode+"  roomCode:-"+roomCode+"  services:-"+services);
	//jQuery("#list1").jqGrid().setGridParam({url : '/HISClinical/opd/opdDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&visitType="+visitType+"&roomCode="+roomCode}).trigger("reloadGrid")
	if(nursingFlag == document.getElementsByName("services")[0].value)
	{
		if((persistRowFlag != "0") && (jQuery("#list1").jqGrid().setGridParam({url :createFHashAjaxQuery('/HISClinical/inpatient/ipdNursingDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&wardCode="+wardCode+"&roomCode="+roomCode+"&services="+services+"&srchCriteria="+srchCriteria+"&srchValue="+srchValue)}).trigger("reloadGrid"))>0){	    	
			//alert("loadComplete..");
			jQuery("#list1").setSelection(persistRowFlag);
		}	
	}
	else
	{
		if(document.getElementsByName("services")[0].value=="1")
		{
			$("#list1").jqGrid('GridUnload');
	        showPatientList();
		}
		else if(document.getElementsByName("services")[0].value=="2")
		{
			nursingFlag=document.getElementsByName("services")[0].value;
			$("#list1").jqGrid('GridUnload');
	        showNonAcceptancePatientList();
		} 
		else if(document.getElementsByName("services")[0].value=="3")
		{
			nursingFlag=document.getElementsByName("services")[0].value;
			$("#list1").jqGrid('GridUnload');
	        showLeavePatientList();
		} 
		else if(document.getElementsByName("services")[0].value=="4")
		{
			nursingFlag=document.getElementsByName("services")[0].value;
			$("#list1").jqGrid('GridUnload');
	        showInTransitPatientList();
		} 
	}
}
function checkForOnSelectDeskListItem(listKey)
{
	if(listKey!=persistRowFlag)
	{		
		//alert("persistRowFlag OLD "+persistRowFlag);
        persistRowFlag =listKey;
       // alert("persistRowFlag NEW "+persistRowFlag);
		//alert(listKey);
		var templst=listKey;
		
		var dtlLst=new Array();
		dtlLst = templst.toString().split("@");
		var detailLst = "?selListItemKey="+listKey+"&departmentUnitCode="+dtlLst[3];
		//alert(dtlLst);
		openPatientDetail(detailLst);
	}
}

function openPatientDetail(dtlData)
{
	//alert(dtlData);
	flag="1";
	parent.addTab("Patient Dashboard","DESKPATDASH", '0', "-1", true, dtlData);
}

 
function getWardListByUnit()
{
	flag="2" // for refreshing list
	var unit = document.getElementsByName("departmentUnitCode")[0].value;
	var ward = document.getElementsByName("wardCode")[0].value;
	/*if(unit == "0")
		{//var wardList = " "; //setWard(wardList);		
			setRoomAll();			
			var wardList = document.getElementsByName("wardCode")[0];
			wardList.innerHTML="";
			var opt = document.createElement("option");		
			opt.value = "0";
			opt.innerHTML = "All";
			wardList.appendChild(opt);
		}
	else*/ {
			setRoomAll();
			var wardList = getWard();
			setWard(wardList);
		}
}
function getWard()
{
		var flg = false;
		var objWardList = null;
		var _mode = "AJX_G_WARD";
		var departmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
		//alert(departmentUnitCode);
		var urlNew= "/HISClinical/inpatient/ipdNursingDeskPatientList.cnt?hmode="+_mode+'&departmentUnitCode='+departmentUnitCode;	
		var objXHR = {url: createFHashAjaxQuery(urlNew), sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				//alert("DATA= :"+data.length+data[0].wardCode);
				objWardList = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information");
	            objWardList = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);		
		return objWardList;
}

function setWard(objWardList)
{
	var wardList = document.getElementsByName("wardCode")[0];
	var options = '';
	if(objWardList !=null)
	{	
		wardList.innerHTML="";
		var opt = document.createElement("option");		
		opt.value = "0";
		opt.innerHTML = "All";
		options='<option value="0">' + opt.innerHTML + '</option>'
		wardList.appendChild(opt);
		for(var i=0;i<objWardList.length;i++)
			{
				opt.value = objWardList[i].wardName;
				opt.innerHTML = objWardList[i].wardCode;				
				options += '<option value="' + opt.value + '">' + opt.innerHTML + '</option>';
			}
			wardList.innerHTML=options;
	}
}
 
function getRoomListByUnit()
{
	flag="2" // for refreshing list
	var unit = document.getElementsByName("departmentUnitCode")[0].value;
	var ward = document.getElementsByName("wardCode")[0].value;
	var room = document.getElementsByName("roomCode")[0].value;
	if(ward == "0")
		{//var roomList = " "; //setRoom(roomList);
		var roomList = document.getElementsByName("roomCode")[0];
		roomList.innerHTML="";
		var opt = document.createElement("option");		
		opt.value = "0";
		opt.innerHTML = "All";
		roomList.appendChild(opt);}
	else {
		var roomList = getRoom();
		setRoom(roomList);}
}
function getRoom()
{
		var flg = false;
		var objRoomList = null;
		var _mode = "AJX_G_ROOM";
		var departmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
		var wardCode = document.getElementsByName("wardCode")[0].value;
		//alert("departmentUnitCode:-"+departmentUnitCode+" wardCode:-"+wardCode);
		var urlNew= "/HISClinical/inpatient/ipdNursingDeskPatientList.cnt?hmode="+_mode+'&departmentUnitCode='+departmentUnitCode+'&wardCode='+wardCode;	
		var objXHR = {url: createFHashAjaxQuery(urlNew), sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				//alert("DATA= :"+data.length+data[0].roomCode);
				objRoomList = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information");
	            objRoomList = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);		
		return objRoomList;
}

function setRoom(objRoomList)
{
	var roomList = document.getElementsByName("roomCode")[0];
	var options = '';
	if(objRoomList !=null)
	{	
		roomList.innerHTML="";
		var opt = document.createElement("option");		
		opt.value = "0";
		opt.innerHTML = "All";
		options='<option value="0">' + opt.innerHTML + '</option>'
		//roomList.appendChild(opt);
		for(var i=0;i<objRoomList.length;i++)
			{
				opt.value = objRoomList[i].roomName;
				opt.innerHTML = objRoomList[i].roomCode;				
				options += '<option value="' + opt.value + '">' + opt.innerHTML + '</option>';
				//roomList.appendChild(opt);
				//alert(objRoomList[i].roomCode);
			}
			roomList.innerHTML=options;
	}
}
function setRoomAll()
{	
		var roomList = document.getElementsByName("roomCode")[0];
		roomList.innerHTML="";
		var optRoom = document.createElement("option");		
		optRoom.value = "0";
		optRoom.innerHTML = "All";
		roomList.appendChild(optRoom);
}
function refreshList()
{
	
	if(flag=="100")	{flag ="1";}
	if(flag=="1")
	{
		filterPatientList();
	}	
		flag=++flag;
//	setInterval(function(){filterPatientList();},interval);
}
function setPersistRow()
{	
    if(persistRowFlag != "0"){	
		jQuery("#list1").setSelection(persistRowFlag);
    	//alert('load complete')
	}	
}
