  function MonographDoc() {

  /* var data1=$(this).parent().parent().find('.checkedInput').val();*/
   var checkedValue = $('.checkedInput').val();
  console.log(checkedValue); 
  strXML = '<Request>'+
  '<Interaction>'+
  '<Prescribing>'+
  '<GGPI reference="{488F9F61-5D37-4989-925E-1742FFFDAA9E}"/>'+
  '<GGPI reference="{B3E6B75E-9519-6AE7-E034-080020E1DD8C}"/>'+
  '<GGPI reference="{BF33752F-6062-0589-E034-0003BA299378}"/>'+
  '<GGPI reference="{B3E696CF-71F8-6518-E034-080020E1DD8C}"/>'+
  '</Prescribing>'+
  '<References/>'+
  '</Interaction>'+
  '</Request>';
  $.ajax({
  	url: "/HISDRDESK/services/restful/cims/getMonographCimsData",
  	dataType: "text",
  	type: "POST",
  	async: false,
  	 crossDomain:true,
  	data: {
  		xml: strXML
  	},
  	success: function(data) {
  		console.log(data);
  		$('#MonographResponse').html(data);
  		$("#CimsMonographId").modal();
  	},
  	error: function(XMLHttpRequest, textStatus, errorThrown) {
  		alert(XMLHttpRequest.response);
  	}
  });
  }
  
 
  function loadDoc() {
	  var DrugCodeCat = [] ;
	  var ICDCode = [] ;
	  var j=0;
	  var xmlreq=''
	 var xmlreq1=''	 
	 var val5="true";
     var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	  var PregnancyIdvalue= $('#PregnancyIdvalue').html().replace("Week","");
	  if(PregnancyIdvalue != '')
		  PregnancyIdvalue=PregnancyIdvalue;
	  else
		  PregnancyIdvalue='0';
	  
	 var patGender= patGeneralDtl[3].split('/')[0];
	 var patAge= patGeneralDtl[3].split('/')[1].replace("Yr"," ");
	// var vitalchart=strVitalsChartId;
	 console.log("PATTTT:"+patGender,patAge  ,PregnancyIdvalue);
	 
	  $('input[name=drugsAdvices]:checked').each(function()
				{
					//console.log('name=drugsAdvices::::>>>'+$(this).val());
					if($(this).val().trim().split('&&')[1]!=100) 
					{
						//DrugCodeCat[j]=(($(this).val()).split('#')[3]).split('!')[0];
						var val1=(($(this).val()).split('#')[3]).split('!')[0];
						var data1=$(this).val();
						if((data1.split('#')[5]).split('&&')[0] ==  '1')
						xmlreq = xmlreq+"<GGPI reference="+'"' + val1 +'"'+"/>\r\n" 
						
						if((data1.split('#')[5]).split('&&')[0] ==  '2')
							xmlreq = xmlreq+"<GenericItem reference="+'"' + val1 +'"'+"/>\r\n" 
							
						if((data1.split('#')[5]).split('&&')[0] ==  '3')
								xmlreq = xmlreq+"<Product reference="+'"' + val1 +'"'+"/>\r\n" 	
						
						
						j++;
					}
					
					
				});
	  console.log(xmlreq);
	 // console.log(DrugCodeCat);
	  $('input[name=diagnosisAdded]:checked').each(function()
				{
					//console.log('name=drugsAdvices::::>>>'+$(this).val());
					if($(this).val().trim().split('&&')[1]!=100) 
					{
						//ICDCode[j]=($(this).val()).split('#')[0];
						var val2=($(this).val()).split('#')[0];
						var val3="ICD10";
						var chk=(($(this).val()).split('#')[2]).split('^')[0];
						if(chk==0)
						xmlreq1 = xmlreq1+"<HealthIssueCode code="+'"'+$.trim(val2)+'"'+" codeType="+'"' + val3 +'"'+"/>\r\n"   
						
					
						
						
						//j++;
					}
					
					
				});
	  
	//  console.log(ICDCode);
	 /*  $.ajax({url:'/HISDRDESK/services/restful/DrDesk/saveVital/',type:'POST',data:{JsonData:data},success:function(result)
	    	{ */
  strXML = "<Request>\r\n" + "<Interaction>\r\n" + "<Prescribing>\r\n" +
 /* "<Product reference=\"{35051B69-F816-49D2-8A1A-1DA953855A0E}\"/>\r\n" +
  "<Product reference=\"{59C38920-831D-42AB-B77D-09B8D0C88711}\"/>\r\n" +
  "<Product reference=\"{35051B69-F816-49D2-8A1A-1DA953855A0E}\"/>\r\n" +
  "<Product reference=\"{4AE068E3-F727-4EEE-9F8A-AA360427931A}\"/>\r\n" +
  "<Product reference=\"{DAABD513-72A6-41F2-ABEC-DDAEABBC18D9}\"/>\r\n" +
  "<Product reference=\"{9AEDF043-39EC-468F-A94B-0FCFC81DE1FF}\"/>\r\n" +
  "<Product reference=\"{96048B6A-575C-4D10-AE5B-D45DD410BAA5}\"/>\r\n" +*/
  xmlreq+
  "</Prescribing>\r\n" + 
  /*"<Allergies>\r\n" +
  "<Molecule reference=\"{DAABD513-72A6-41F2-ABEC-DDAEABBC18D9}\"/>\r\n" +
  "<Molecule reference=\"{47150CDB-494D-4967-ADA7-F3F343865765}\"/>\r\n" +
  "<SubstanceClass reference=\"{8B9ECB52-CDC9-4460-AB40-6E3C7DF38B1E}\"/>\r\n"
  +
  "<SubstanceClass reference=\"{9E775009-231D-728D-E034-080020E1DD8C}\"/>\r\n"
  + "</Allergies>\r\n" + */
  "<HealthIssueCodes>\r\n" +
  /*"	<HealthIssueCode code=\"J45\" codeType=\"ICD10\"/>\r\n" +
  "	<HealthIssueCode code=\"K26.0\" codeType=\"ICD10\"/>\r\n" +
  "	<HealthIssueCode code=\"I49.5\" codeType=\"ICD10\"/>\r\n" +*/
   xmlreq1+
  "</HealthIssueCodes>\r\n" + 
  "<DuplicateTherapy checkSameDrug="+'"'+val5+'"'+" />\r\n" +
  "<DuplicateIngredient checkSameDrug="+'"'+val5+'"'+" />\r\n" +
  "<References/>\r\n" +
  "</Interaction>\r\n" +
  "<PatientProfile>\r\n"+
  "<Gender>"+patGender+"</Gender>\r\n"+
  "<Age><Year>"+patAge+"</Year></Age>\r\n"+
  "<Pregnancy><Week>"+PregnancyIdvalue+"</Week></Pregnancy>\r\n"+
  "<Nursing>true</Nursing>\r\n"+
  "</PatientProfile>\r\n"+
  "</Request>";
  console.log(strXML);
  $.ajax({
  	url: "/HISDRDESK/services/restful/cims/getCimsData",
  	dataType: "text",
  	type: "POST",
  	async: false,
  	 crossDomain:true,
  	data: {
  		xml: strXML
  	},
  	success: function(data) {
  		console.log(data);
  		$('#CimsResponse').html(data);
  		$("#CimsIntegrationId").modal();
  	},
  	error: function(XMLHttpRequest, textStatus, errorThrown) {
  		alert(XMLHttpRequest.response);
  	}
  });
  }
 

  function CimsAlleryDoc() {

	
	  var DrugCodeCat = [] ;
	  var ICDCode = [] ;
	  var j=0;
	  var xmlreq=''
	 var xmlreq1=''	  
     var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	  
	 var patGender= patGeneralDtl[3].split('/')[0];
	 var patAge= patGeneralDtl[3].split('/')[1].replace("Yr"," ");
	 console.log("PATTTT:"+patGender,patAge);
	 
	  $('input[name=drugsAdvices]:checked').each(function()
				{
					//console.log('name=drugsAdvices::::>>>'+$(this).val());
					if($(this).val().trim().split('&&')[1]!=100) 
					{
						//DrugCodeCat[j]=(($(this).val()).split('#')[3]).split('!')[0];
						var val1=(($(this).val()).split('#')[3]).split('!')[0];
						var data1=$(this).val();
						if((data1.split('#')[5]).split('&&')[0] ==  '1')
						xmlreq = xmlreq+"<GGPI reference="+'"' + val1 +'"'+"/>\r\n" 
						
						if((data1.split('#')[5]).split('&&')[0] ==  '2')
							xmlreq = xmlreq+"<GenericItem reference="+'"' + val1 +'"'+"/>\r\n" 
							
						if((data1.split('#')[5]).split('&&')[0] ==  '3')
								xmlreq = xmlreq+"<Product reference="+'"' + val1 +'"'+"/>\r\n" 	
						
						
						j++;
					}
					
					
				});
	  console.log(xmlreq);
	 // console.log(DrugCodeCat);
	
	  
	//  console.log(ICDCode);
	 /*  $.ajax({url:'/HISDRDESK/services/restful/DrDesk/saveVital/',type:'POST',data:{JsonData:data},success:function(result)
	    	{ */
  strXML = "<Request>\r\n" + "<Interaction>\r\n" + "<Prescribing>\r\n" +
 /* "<Product reference=\"{35051B69-F816-49D2-8A1A-1DA953855A0E}\"/>\r\n" +
  "<Product reference=\"{59C38920-831D-42AB-B77D-09B8D0C88711}\"/>\r\n" +
  "<Product reference=\"{35051B69-F816-49D2-8A1A-1DA953855A0E}\"/>\r\n" +
  "<Product reference=\"{4AE068E3-F727-4EEE-9F8A-AA360427931A}\"/>\r\n" +
  "<Product reference=\"{DAABD513-72A6-41F2-ABEC-DDAEABBC18D9}\"/>\r\n" +
  "<Product reference=\"{9AEDF043-39EC-468F-A94B-0FCFC81DE1FF}\"/>\r\n" +
  "<Product reference=\"{96048B6A-575C-4D10-AE5B-D45DD410BAA5}\"/>\r\n" +*/
  xmlreq+
  "</Prescribing>\r\n" + "<References/>\r\n" +
  /*"<Allergies>\r\n" +
  "<Molecule reference=\"{DAABD513-72A6-41F2-ABEC-DDAEABBC18D9}\"/>\r\n" +
  "<Molecule reference=\"{47150CDB-494D-4967-ADA7-F3F343865765}\"/>\r\n" +
  "<SubstanceClass reference=\"{8B9ECB52-CDC9-4460-AB40-6E3C7DF38B1E}\"/>\r\n"
  +
  "<SubstanceClass reference=\"{9E775009-231D-728D-E034-080020E1DD8C}\"/>\r\n"
  + "</Allergies>\r\n" + */
   "</Interaction>\r\n" +
  "</Request>";
  console.log(strXML);
  $.ajax({
  	url: "/HISDRDESK/services/restful/cims/getAlleryCimsData",
  	dataType: "text",
  	type: "POST",
  	async: false,
  	 crossDomain:true,
  	data: {
  		xml: strXML
  	},
  	success: function(data) {
  		console.log(data);
  		$('#AllergyResponse').html(data);
  		$("#CimsAlleryId").modal();
  	},
  	error: function(XMLHttpRequest, textStatus, errorThrown) {
  		alert(errorThrown);
  	}
  });
  }

  function GenericMonogramDoc() {

  $.ajax({
  	url: "http://aiimsmanglagiri.uat.dcservices.in/dis/generic/134463001",
  	dataType: "json",
  	type: "GET",
  	async: false,
  	 crossDomain:true, 
  	success: function(data) {
  		console.log(data);
  		/* var myJSON = JSON.stringify(data); */
      
    		for (var i=0; i<data.length; i++) {
        var html = $('<div class="row"><div class="col-sm-12 genericDesc">' + data[i].genericDescriptions[1].genericName+ '</div></div><div class="row"><div class="col-sm-12 drugTypeName">' + data[i].drugTypes[0].typeName + '&nbsp; | &nbsp;' +data[i].drugTypes[1].typeName 
                + '</div></div>'+'<br />'+'<div class="row"><div class="col-sm-6">' +'<strong style="color:blue">'+'Dose Form:&nbsp;&nbsp;'+'</strong>'+ data[i].doseForm + '</div><div class="col-sm-6">'+'<strong style="color:blue">'+'Route Of Administration:&nbsp;&nbsp;'+'</strong>'+data[i].routeOfAdministration + '</div></div>'
               +'<br />'+ '<div class="row"><div class="col-sm-12">'+'<strong style="color:blue">'+'ContraIndications:&nbsp;&nbsp;'+'</strong>'+ data[i].contraIndications + '</div></div>'+'<br />'+'<div class="row"><div class="col-sm-12">'+'<strong style="color:blue">'+'Indications:&nbsp;&nbsp;'+'</strong>'+ data[i].indications + '</div></div>');
         
        $('#GenericMonogramResponse').append(html);
    }
    console.log(html);

    
  		$('#GenericMonogramResponse').html(html);  
  		$("#GenericMonogramId").modal();
  		
  	},
  	error: function(XMLHttpRequest, textStatus, errorThrown) {
  		alert(errorThrown);
  	}
  });
  }


  function BrandMonogramDoc() {

  $.ajax({
  	url: "http://aiimsmanglagiri.uat.dcservices.in/dis/medicine/389581000189107",
  	dataType: "json",
  	type: "GET",
  	async: false,
  	 crossDomain:true, 
  	success: function(data) {
  		console.log(data);
  		/* var myJSON = JSON.stringify(data); */
      
    for (var i=0; i<data.length; i++) {
        var html = $('<div class="row"><div class="col-sm-12 genericDesc">' + data[i].medicineName+ '</div></div>' +'<div class="row"><div class="col-sm-12">'+'<strong style="color:blue">'+'Manufacturer Name:&nbsp;&nbsp;'+'</strong>'+ data[i].manufacturer.manufacturerName + '</div></div>'
                +'<div class="row"><div class="col-sm-6">' +'<strong style="color:blue">'+'License Number:&nbsp;&nbsp;'+'</strong>'+ data[i].licenseNumber + '</div><div class="col-sm-6">'+'<strong style="color:blue">'+'Packaging:&nbsp;&nbsp;'+'</strong>'+data[i].packaging + '</div></div>'
               +'<br />'+ '<div class="row"><div class="col-sm-12">'+'<strong style="color:blue">'+'Brand Name:&nbsp;&nbsp;'+'</strong>'+ data[i].brandName + '</div></div>'+'<br />'
               +'<div class="row"><div class="col-sm-12">'+'<strong style="color:blue">'+'Generic Names:&nbsp;&nbsp;'+'</strong>'+ data[i].genericNames + '</div></div>');
         
        $('#BrandMonogramResponse').append(html);
    }
    console.log(html);

    
  		$('#BrandMonogramResponse').html(html);  
  		$("#BrandMonogramId").modal();
  		
  	},
  	error: function(XMLHttpRequest, textStatus, errorThrown) {
  		alert(errorThrown);
  	}
  });
  }
 
 