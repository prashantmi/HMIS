// Check For On Select Desk List Item 
function checkForOnSelectDeskListItem(obj, evnt, listKey)
{
	//Added by Akash
	var arr = listKey.split("@");
	var detailData = "?selListItemKey="+listKey+"&departmentUnitCode="+arr[3];
	//alert(detailData);
	showDeskDetailFrame(detailData);
	
	/*//alert("inside checkForOnSelectDeskListItem");
	var deskFrame=parent.document.getElementById("frmDynamicDeskCenter");
	var doc="";
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	var frm=doc.forms[0];
	var selectedCRNo = null;
	if(frm.crNoSelected.value!="")	selectedCRNo = frm.crNoSelected.value;
	
	if(obj.checked)
	{
		if(selectedCRNo!=null)
		{	
			var patArray=document.getElementsByName("patCrNo");
			var i=0;
			var prevCRNoElement=null;
			while(i<patArray.length)
			{
				if(patArray[i].value==selectedCRNo)
				{
					prevCRNoElement=patArray[i];
					break;
				}
				i=i+1;
			}
			if(prevCRNoElement!=null)
			{
				// Episosode Close Option Call on Click of Patient Row Radio Button 
				var isConfirmed = document.getElementsByName(prevCRNoElement.value)[0].value;
				if(selectedCRNo!=obj.value && isConfirmed == "false")
				{
					//alert("Please Give Next Visit Date & Visit Summary to the Patient of CR No. "+prevCRNoElement.value);
					//if(confirm("Please Give Next Visit Date & Visit Summary to the Patient of CR No. "+prevCRNoElement.value))
					//{
					//	prevCRNoElement.checked=true;
					//	submitFormOnValidateButton(evnt,"OPDNEXTVISITDETAIL","","0",null);
					//}
					//else
						activateSelectedCRNO(obj,frm, true);
				}
				else
					activateSelectedCRNO(obj,frm, true);
			}
			else
				activateSelectedCRNO(obj,frm, true);
		}
		else
			activateSelectedCRNO(obj,frm, true);
	}*/
}	


// On Change of Page Choice
function submitOnChangePageChoice(_hmode)
{
	var frm=document.forms[0];
	submitForm(_hmode);
}


/***************  Functions for doctorDeskDashboard.jsp STARTED    @@# added by Akash #@@ ****************/

/*  onLoad Functions */
function deskMenuTileOnLoad(event)
{
  	var frm=document.getElementsByTagName('*');
	for(var i in frm)
	{
		if(frm[i] && frm[i].id && frm[i].id.substr(0,11)=='tabSummary#')
		{
			var obj=document.getElementById(frm[i].id);
			var arr = obj.id.split('#');
			
			var tabSummary = getTabSummary(arr[1],arr[2]);
			if(tabSummary && tabSummary!=null && tabSummary.data && tabSummary.data.length!=0)
			{
				//alert("tabSummary length :"+tabSummary.data.length);
				//alert("tabSummary"+tabSummary.header +" "+tabSummary.data[0].header);
				var divId= arr[1];
				document.getElementById(divId).style.display = 'block';
				var summHTML = "";
				for(var i=0;i<tabSummary.data.length;i++)
				{
					summHTML += tabSummary.data[i].header+": "+tabSummary.data[i].value;
					if(i!=tabSummary.data.length-1)	summHTML +="<br>";
				}			
				obj.innerHTML = summHTML;
			}
		}
	}
	//$( "#deskTabs" ).resize();	
}

/*  AJAX Functions */
function getTabSummary(deskMenuURL,deskMenuId)
{
	var flg = false;
	var objTabSummary = null;
		urlNew = "/HISClinical/hisglobal/utility/dynamicdesk/deskSummary.cnt?mode="+deskMenuURL+"&deskMenuId="+deskMenuId+"&hmode=AJX_G_SUMMARY"+getDeskPatInfoStr();
	var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			//alert("Data"+data.header +" "+data.data[0].header);
			objTabSummary = data;
			flg = true;
		},
        error: function(error)
        {
        	//objTabSummary = {header:'Error',data:[{header:'no data found',value:''}]};;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return objTabSummary;
}


function goToDashBoard()
{
	//alert("going to dashboard..");
	if(document.getElementById("DeskPatDetailId")){
		document.getElementById("DeskPatDetailId").style.display = 'none';
		}
	else if(parent.document.getElementById("DeskPatDetailId")){
		parent.document.getElementById("DeskPatDetailId").style.display = 'none';
		}
	if(document.getElementById("mainDeskDiv11")){
		document.getElementById("mainDeskDiv11").style.display = 'block';
		}
	else if(parent.document.getElementById("mainDeskDiv11")){
		parent.document.getElementById("mainDeskDiv11").style.display = 'block';
		}
	
	if(deskMenuTileOnLoad){
		deskMenuTileOnLoad();
		}
	else if(parent.deskMenuTileOnLoad){
		parent.deskMenuTileOnLoad();
		}
		
	
}


// AJAX Functions
function patVisitSummary()
{	//alert("AJAX calling");
		var flg = false;
		var objPatientInfo = null;
		var _mode = "AJX_G_PATIENTS_VISIT_SUMMARY";
		var _episodeVisitNo = document.getElementsByName("episodeVisitNo")[0].value
		var _patCrNo = document.getElementsByName("patCrNo")[0].value
		var _episodeCode = document.getElementsByName("episodeCode")[0].value
		//alert("D"+_episodeVisitNo+"P"+_patCrNo);
		var urlNew= "/HISClinical/opd/casulatyDeskDashboard.cnt?hmode="+_mode+"&patCrNo="+_patCrNo+"&episodeVisitNo="+_episodeVisitNo+"&episodeCode="+_episodeCode;
		//alert(urlNew);
		var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				//alert("Data"+data[0].strHeader);
				objPatientInfo = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information");
	            objPatientInfo = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);
		return objPatientInfo;
}

/***************  Functions for doctorDeskDashboard.jsp ENDED   @@# added by Akash #@@ ****************/
