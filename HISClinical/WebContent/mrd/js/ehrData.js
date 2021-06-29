/** Created to use JSON Object for getting the Data in EMR
 * Created By Shruti Shail on 6 Feb 2017
 */
function setEMRTabsActiveStatus(mode, jsonObject,key,deskFrame,req)
{
	var flagActive = 0
	var doc='';
	
	if(req==1)
		doc=deskFrame.contentDocument;
	else if (req==2)
		doc=deskFrame.Document;
	var frm=doc.forms[0]; 
	var selectedVisit=frm.episodeVisitNo.value;
	var selectedUnit=frm.selectedUnit.value;
	var selectionCriteria=frm.selectionCriteria.value;
	var selectEpisodeCode=frm.episodeCode.value;
	var selectedPatAdmNo=frm.selectedPatAdmNo.value;
	var hosCode=frm.hosCode.value;
	
	
	/*
	// alert("selectedUnit "+selectedUnit)
 // 	alert("selectedVisit "+selectedVisit)
	var emrDoc='';
	if(req==1)
		emrDoc=deskFrameEMRDetails.contentDocument;
	else if (req==2)
		emrDoc=deskFrameEMRDetails.Document;
		
		var emrFrm=emrDoc.forms[0];
		
		emrFrm.sendVisitDetail.value=selectedVisit;
		emrFrm.episodeCode.value=selectEpisodeCode;
		emrFrm.episodeVisitNo.value=selectedVisit;
		emrFrm.selectedUnit.value=selectedUnit;
		emrFrm.selectedPatAdmNo.value=selectedPatAdmNo;
		emrFrm.hosCode.value=hosCode;
		// alert("makeNodeIpdWise  "+emrFrm.selectedPatAdmNo.value)*/
		
		
		
	//alert("selectedVisit:"+selectedVisit+" selectedUnit:"+selectedUnit+" selectEpisodeCode:"+selectEpisodeCode+" selectedPatAdmNo:"+selectedPatAdmNo+" selectionCriteria:"+selectionCriteria+" hosCode:"+hosCode);
	
	if(selectionCriteria=="All")
	{
		if(jsonObject[key] && jsonObject[key].present)
		{
			flagActive = 1;			
		}
	} 
	else if(selectionCriteria=="H")
	{
		if(jsonObject[key] && jsonObject[key]["H".concat(hosCode)] && jsonObject[key]["H".concat(hosCode)].present)
		{
			flagActive = 1;	
		}
	}
	else if(selectionCriteria=="O")
	{
		if(jsonObject[key] && jsonObject[key]["H".concat(hosCode)] && jsonObject[key]["H".concat(hosCode)]["O"] && jsonObject[key]["H".concat(hosCode)]["O"].present)
		{
			flagActive = 1;	
		}
	}
	else if(selectionCriteria=="U")
	{
		if(jsonObject[key] && jsonObject[key]["H".concat(hosCode)] && jsonObject[key]["H".concat(hosCode)]["O"] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)].present)
		{
			flagActive = 1;	
		}
	}
	else if(selectionCriteria=="E")
	{
		if(jsonObject[key] && jsonObject[key]["H".concat(hosCode)] && jsonObject[key]["H".concat(hosCode)]["O"] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)]["E".concat(selectEpisodeCode)] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)]["E".concat(selectEpisodeCode)].present)
		{
			flagActive = 1;	
		}
	}
	else if(selectionCriteria=="V")
	{
		if(jsonObject[key] && jsonObject[key]["H".concat(hosCode)] && jsonObject[key]["H".concat(hosCode)]["O"] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)]["E".concat(selectEpisodeCode)] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)]["E".concat(selectEpisodeCode)]["V".concat(selectedVisit)] && jsonObject[key]["H".concat(hosCode)]["O"]["U".concat(selectedUnit)]["E".concat(selectEpisodeCode)]["V".concat(selectedVisit)].present)
		{
			flagActive = 1;	
		}
	}
	else if(selectionCriteria=="I")
	{
		if(jsonObject[key] && jsonObject[key]["H".concat(hosCode)] && jsonObject[key]["H".concat(hosCode)]["I"] && jsonObject[key]["H".concat(hosCode)]["I"].present)
		{
			flagActive = 1;	
		}
	}
	else if(selectionCriteria=="A")
	{
		if(jsonObject[key] && jsonObject[key]["H".concat(hosCode)] && jsonObject[key]["H".concat(hosCode)]["I"] && jsonObject[key]["H".concat(hosCode)]["I"]["A".concat(selectedPatAdmNo)] && jsonObject[key]["H".concat(hosCode)]["I"]["A".concat(selectedPatAdmNo)].present)
		{
			flagActive = 1;	
		}
	}
	return flagActive;
}
