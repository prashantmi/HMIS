var flag="1";
var persistRowFlag="1"; //previous;y it was 0, made 1 on 15/12/2014

function showPatientList()
{	 
	var pageWid= Math.round($(parent.window).innerWidth() - 172) + "px";
	var pageHeight= (window.innerHeight-115)+'px';
	document.getElementById("divPatientListId").style.width=pageWid;	
	document.getElementById("divPatientListId").style.height=pageHeight;
	//alert("All Patient List For Toaday ");
	var pageWidth= $("#list1").parent().width(); 
	var _mode= "AJX_G_PATIENTLIST"; 
	var departmentUnitCode = "0";
	var visitType = "0";	
    var height = $(document).height();	
    //var height = $(window).height();
    //alert("window height  :-"+height);
    var count=1;
    $("#list1").jqGrid({
    url:createFHashAjaxQuery('/HISClinical/opd/casualtyDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&visitType="+visitType), 
    	datatype: "json",
         colNames:['','','Que No', 'Patient Name', 'CR Number', 'Gender/Age', 'Category', 'Visit Reason', 'Department/Unit','Room','Visit Date'],
	     colModel:[
	              	{name:'listId',index:'listId',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
	              	{name:'listKey',index:'listKey',width:(pageWidth*(0/100)), align:"center", hidden:true, formatter:highlight},
	              	{name:'queNo',index:'queNo',width:(pageWidth*(6/100)),search:false,  align:'center'},
	              	{name:'patName',index:'patName',width:(pageWidth*(19/100)), search:true, align:"left", formatter:highlight},
	              	{name:'patCrNo',index:'patCrNo',width:(pageWidth*(12/100)), search:false, align:"center", sorttype: 'int', formatter:highlight},
	              	{name:'patGender',index:'patGender', width:(pageWidth*(9/100)), search:false, align:"center", formatter:highlight},
	              	{name:'patPrimaryCatCode',index:'patPrimaryCatCode', width:(pageWidth*(9/100)),search:false, align:"center", formatter:highlight},
	              	{name:'reasonOfVisit',index:'reasonOfVisit',width:(pageWidth*(13/100)), search:false, align:"center", formatter:highlight},
	              	{name:'departmentUnitCode',index:'departmentUnitCode',width:(pageWidth*(20/100)), sorttype:'string', search:true, align:"center", formatter:highlight},
	              	{name:'room',index:'room', width:(pageWidth*(5/100)), search:false, align:"center", formatter:highlight},
	              	{name:'visitdate',index:'visitdate', width:(pageWidth*(15/100)), search:false, align:"center", formatter:highlight}
	            ],
         rowNum:10,
         rowList:[10,25,50,75,100],
         pager:'#pager1',
         multiSort: true,
         sortname: 'visitdate desc',
         viewrecords: true,
         height: (height-115),
         autowidth: true,
         autoheight: true,
         gridview: true,
         onSelectRow: function(ids) {
         	
            	 checkForOnSelectDeskListItem(ids); 
            	  //$('table tr').find('.ui-state-highlight').css('background', '#ffffff');
            	 // $(this).find('.ui-state-highlight').css('background', '#80BFFF');
             },   
             onSortCol: function(index,iCol,sortorder)
             {
            	 //alert(iCol);
            	 if(iCol==2 || index=="queNo")
            	 {
            		//alert("k");
            		jQuery("#list1").setGridParam({sortname:'to_number(replace(queNo,\'C-\',\'\'))'});
            	}
             },
        // loadComplete: function(){
		  //       	var ret;
		  //       	alert("This function is executed immediately after\n data is loaded. We try to update data in row 13.");
		  //       	ret = jQuery("#list1").jqGrid('getRowData',"1011400017510@10111001@1@10111@1005");
		   //      	if(ret.id == "1011400017510@10111001@1@10111@1005"){
		   //      		jQuery("#list1").jqGrid('setRowData',ret.id,{queNo:"<font color='red'>0</font>"})
		   //      	}
	       //  	} , 
	    loadComplete:  function() {
	    	setPersistRow();
            },
			
     });
    jQuery("#list1").jqGrid('gridResize');
}

var color; //color for attented patient row
var attended="1";
function highlight (cellValue, options, rowObject) {
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
			 color = "blue";			 
		}
		else{color = "black";}
	}
	else if(cellValue.charAt(1)=="@")
	{ //var color = (parseInt(cellValue) < 10000) ? "black" : "red";
		var cellId=new Array();
		cellId = cellValue.toString().split("@");
		//alert(cellId[5]);
		if(cellId[0]=="2")
		{
			 attended="2";			 
		}
		else{attended="1";}
	}
	//else{var color = (parseInt(cellValue) < 10000) ? "black" : "black";}
	if(color=="blue")
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

function filterPatientList()
{
	// fetch filter data
	//alert("called..")
	parent.document.getElementById("frmDynamicDeskHeader").contentWindow.setPatStats(); // For Rfreshing Desk Statics
	var tempdepartmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var departmentUnitCode=new Array();
	departmentUnitCode = tempdepartmentUnitCode.toString().split("^");
	var visitType = "0";
	var roomCode = document.getElementsByName("roomCode")[0].value;
	//alert("departmentUnitCode :"+departmentUnitCode[0]+"  visitType  :"+visitType+"roomCode"+roomCode);
	//jQuery("#list1").jqGrid().setGridParam({url : '/HISClinical/opd/casualtyDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&visitType="+visitType+"&roomCode="+roomCode}).trigger("reloadGrid")
		
	
	if((persistRowFlag != "0") && (jQuery("#list1").jqGrid().setGridParam({url : createFHashAjaxQuery('/HISClinical/opd/casualtyDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&visitType="+visitType+"&roomCode="+roomCode)}).trigger("reloadGrid"))>0){	    	
		//alert("loadComplete..");
		jQuery("#list1").setSelection(persistRowFlag);
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

 
function getRoomListByUnit()
{
	flag="2" // for refreshing list
	var unit = document.getElementsByName("departmentUnitCode")[0].value;
	var room = document.getElementsByName("roomCode")[0].value;
	if(unit == "0")
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
		var unit = document.getElementsByName("departmentUnitCode")[0].value;
		var urlNew= "/HISClinical/opd/casualtyDeskPatientList.cnt?hmode="+_mode+'&unit='+unit;
		//var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
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
