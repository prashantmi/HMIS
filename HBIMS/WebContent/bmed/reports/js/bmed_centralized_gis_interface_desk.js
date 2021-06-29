var chkValue	=	"";
var equip = "";
var stock= "";


function imageMapAreaClicked(e)
{
	document.forms[0].strDistrictName.value = e.alt;
	document.forms[0].strDistrictId.value = e.id;
	document.forms[0].section.value = false;
	mode = 	document.getElementById("hMode").value;
	add(mode);
}
function hospitalTypeCmb()
{
	var flag = document.getElementById("isValid").value;
	if(flag=="0")
	{
		stockInHand();
		document.getElementById("showDataDiv").style.display="block";		
		$( "#disName" ).hover(
			function() 
			{
				$( this ).append( $( "<span>"+document.forms[0].strDistrictName.value+"</span>" ));
				
			});	
		document.getElementById('dataTab').focus();
	}

}
function stockInHand()
{
	equip = document.getElementById("Equipment").value.split("@");
	equipValue = document.getElementById("EquipmentValue").value.split("@");
	equipValue1 = document.getElementById("EquipmentValue1").value.split("@");
	var str,str1,str2;
	if(equip.length <= 1)
	{
		$("#dataTab").append("<tr><td  class='rowData' colspan='2'>No Record Found</td></tr>");
		return ;
	}
	if(equipValue1=="")
	{
		for(i=0;i<equip.length-1;i++)
		{		
			str = "<tr class='rowHeader'>";
			str = str + "<td>" + equip[i] + "</td>"  + "<td style='text-align:center'>" + equipValue[i] + "</td>";
			$("#dataTab").append(str);
		}
	}
	else
	{
		for(i=0;i<equip.length-1;i++)
		{		
			str = "<tr class='rowHeader'>";
			str = str + "<td>" + equip[i] + "</td>" + "<td>" + equipValue[i] + "/" + equipValue1[i] + "</td>";
			$("#dataTab").append(str);
		}		
	}
	str = str + "</tr>";
	str1 = str1 + "</tr>";
	
}

function buttonLogicsOnClick1(modeNo, mode , display)
{
	try
	{
		add(mode);
	}
	catch(Err)
	{
		alert(Err);
	}
}

function cancelPage()
{
    document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}
