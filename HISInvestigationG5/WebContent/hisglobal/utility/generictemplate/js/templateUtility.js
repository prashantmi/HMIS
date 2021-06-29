//  ******************************************************************************
// *** Consent Printing Related Functions

function PrintConset(tbl)
{
	var id=tbl.split("#")[1];
	document.getElementsByName('consentHtmlToPrint')[0].value=id;
	
	document.getElementsByName('hmode')[0].value='PRINTCONSENT';
	document.forms[0].submit();
}


function convertConsetToPdf(tbl,e)
{
	//var h=document.getElementById(tbl).innerHTML;
	//h="<html><body><table>"+h+"</table></body></html>";
	var id=tbl.split("#")[1];
	document.getElementsByName('consentHtmlToPrint')[0].value=id;
	
	var url="/HISInvestigationG5/opd/consentPrint?templateId="+id;
	openPopup(url,e,700,600)
}

// *** End Validation Functions
//  ******************************************************************************


//  ******************************************************************************

	// Rendering Template For Modification 
function renderTemplateForModification()
{
	//alert("In Render Template");
	
	TemplateProp.templateType=document.getElementsByName('templateType')[0].value;
	TemplateProp.totalRows=parseInt(document.getElementsByName('noOfRows')[0].value);
	TemplateProp.templateCode="";
	
	var l=parseInt(TemplateProp.totalRows);
	TemplateProp.paraArray=new Array(l);
	for(var i=0;i<l;i++)
	{
		var temp = new Array(9);
		for(var j=0;j<9;j++)
		{
			temp[j]=new Parameter();
			temp[j].row=i+1;
			temp[j].col=j+1;
		}
		TemplateProp.paraArray[i]=temp;
	}
	
	var target=document.getElementsByName('parameterValuesList')[0].value;
	var rowData=target.split("%#%");
	
	for(var i=0;i<rowData.length;i++)
	{
		var paraData=rowData[i].split("%");
		
		var pObj=TemplateProp.paraArray[paraData[3]-1][paraData[4]-1];
		
		pObj.paraId=paraData[0].split("#")[0];
		pObj.paraName=paraData[0].split("#")[1];
		pObj.paraParent=paraData[1];
		pObj.locationId=paraData[2];
		pObj.paraType=paraData[5];

		var cParams=getValueWiseProp(pObj.paraType);
		
		var t="";
		var k=0;
		if(cParams!=null)
		{
			for ( var F in cParams )
			{
				pObj[F]=paraData[6].split("&")[k];
				k++;
			}
		}

		pObj.colspan=paraData[7];
		pObj.paraValue=paraData[8].split("@")[0];
		pObj.paraOptions=paraData[8].split("@")[1];
		pObj.isCompulsory=paraData[9];
		pObj.isRange=paraData[10];
		pObj.sourceFlag=paraData[11];
		pObj.tableQuery=paraData[12];
	}
	
	for(var i=0;i<TemplateProp.totalRows;i++)
	{
		for(var j=0;j<9;j++)
		{
			var pObj=TemplateProp.paraArray[i][j];
			for(var k=0;k<(pObj.colspan-1);k++)
			{
				TemplateProp.paraArray[i][j+1].paraType="-1";
				j++;
			} 
		}
	}
	var h="\n<table width='100%' id='tblTemplate' border='0' cellspacing='1' cellpadding='0'>\n</table>";
	document.getElementById('htmlModifyTarget').innerHTML=h;
	// Generate Template 
	generateTemplate();
	
}


//  ******************************************************************************
