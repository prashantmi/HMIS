function ajaxTestCode(obj)
{	
	//alert(obj.value);
	var action 			= "/HISRegistration/registration/TESTPARANAMELoinc.action?strAjaxParaTypeCode="+obj.value;
	
	var splitobj =obj.value.split('^');
	var time = splitobj[2];
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				
				for(var j=0;j<rootNode.childNodes.length;j++ )
				{
					var elementNode=rootNode.childNodes[j];
					var elementName=elementNode.nodeName;
					var optionText="<option value='-1'>Select Value</option>";
					
					if(elementNode!=null){
						for(var i=0;i<elementNode.childNodes.length;i++ )
						{
							var optionNode=elementNode.childNodes[i];
							optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
							
						}
					}
									
					if(document.getElementsByName(elementName).length!=0)
						if(elementNode.nodeName == "patAddTestParaCode"){
							document.getElementsByName(elementName)[0].innerHTML=optionText;
				
							$('[name="patAddTestParaCode"]')[0].value=$('[name="defaultpatAddTestParaCode"]')[0].value;
							document.getElementById ('Loinc_locModel_strTimeofMeasurement').value=time;
							
						}
					
					if(elementNode.nodeName == "patAddSampleCode"){
						document.getElementsByName(elementName)[0].innerHTML=optionText;
			
						$('[name="patAddSampleCode"]')[0].value=$('[name="defaultpatAddSampleCode"]')[0].value;
						
			}	
				}
					
		}});
  
}

function getScale()
{
	var TestParaValueSplit = document.getElementById('patAddTestParaCodeId').value.split('^')[1];
	document.getElementById ('Loinc_locModel_strScale').value = TestParaValueSplit;
}
function getProperty(obj)
{
	var SampleValueSplit1 = document.getElementById('patAddSampleCodeId').value.split('^')[1];
	
	var SampleValueSplit2 = document.getElementById('patAddSampleCodeId').value.split('^')[2];
	
	var SampleValueSplit=SampleValueSplit1.concat("^",SampleValueSplit2);
	
	document.getElementById ('Loinc_locModel_strUOMCode').value = SampleValueSplit;
}

function onSaveClick()
{
	
 	  $('[name="strTestNameValue"]')[0].value = $("#Loinc_locModel_strTestNameCode option:selected")[0].text;
 	   	 $('[name="strTestNameCode"]')[0].value = $("#Loinc_locModel_strTestNameCode option:selected")[0].value;
 	   
 	   	 
 	   	
	  /*$('[name="strTestParaNameCode"]')[0].value = $("#patAddTestParaCodeId option:selected")[0].value;
	  $('[name="strTestParaNameValue"]')[0].value = $("#patAddTestParaCodeId option:selected")[0].text;*/
 	   	$('[name="strTestParaNameCode"]')[0].value = $("#Loinc_locModel_strTestParaNameCode option:selected")[0].value;
 		  $('[name="strTestParaNameValue"]')[0].value = $("#Loinc_locModel_strTestParaNameCode option:selected")[0].text;
	  
	  /*$('[name="strTestSampleCode"]')[0].value = $("#patAddSampleCodeId option:selected")[0].value;
	  $('[name="strTestSampleValue"]')[0].value = $("#patAddSampleCodeId option:selected")[0].text;*/
 		  
 		 $('[name="strTestSampleCode"]')[0].value = $("#Loinc_locModel_strTestSampleCode option:selected")[0].value;
 		  $('[name="strTestSampleValue"]')[0].value = $("#Loinc_locModel_strTestSampleCode option:selected")[0].text;
 		  
 		 
}
 

function LoincRadioChange()
{
	
	
}
