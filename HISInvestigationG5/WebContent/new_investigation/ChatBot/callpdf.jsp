<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/hisglobal/js/pdf.js" />
<his:javascript src="/hisglobal/js/pdf.worker.js" />
<html>
<head>
<meta charset="utf-8"> 
<link rel="shortcut icon" href="facivon.ico">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>    
  
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="facivon.ico">
<link rel="stylesheet" type="text/css" href="scripts/inv_cb_style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <script src="//mozilla.github.io/pdf.js/build/pdf.js"></script>
 -->
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
<body>


</body>

<script>
//alert("In jsp 2");

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

var BASE64_MARKER = ';base64,';

function convertDataURIToBinary(dataURI) {
  var base64Index = dataURI.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
  var base64 = dataURI.substring(base64Index);
  var raw = window.atob(base64);
  var rawLength = raw.length;
  var array = new Uint8Array(new ArrayBuffer(rawLength));

  for(var i = 0; i < rawLength; i++) {
    array[i] = raw.charCodeAt(i);
  }
  return array;
}

//function s() {
var reporturl=getUrlParameter("reporturl")
alert("In jsp 2 "+reporturl);
var _mode = "AJX_GET_REPORT";
var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationChatBot.cnt?hmode="+_mode+"&reportUrl="+reporturl, sync:true, postData: "", handleAs: "text",
load: function(data) 
{
	alert(data);
//	uintData = convertDataURIToBinary(data);
	var pdf= <%= request.getAttribute("pdf") %>
	var pdfData = atob(data);
	PDFJS.getDocument(pdfData);
					
	function handlePages(page)
	{
	    var viewport = page.getViewport( 1 );
	    var canvas = document.createElement( "canvas" );
	    //var canvas = window.open( thePDF,'window','width=600,height=400' );
	    canvas.style.display = "block";
	    var context = canvas.getContext('2d');
	    canvas.height = viewport.height;
	    canvas.width = viewport.width;
	    page.render({canvasContext: context, viewport: viewport});
	    document.body.appendChild( canvas );
	    currPage++;
	    if ( thePDF !== null && currPage <= numPages )
	    {
	        thePDF.getPage( currPage ).then( handlePages );
	    }

} 
	},
      error: function(error)
      {
      	alert("In Error function");
      	alert(error);
      }};
var objDojoAjax = dojo.xhrPost(objXHR);
//}
/* 
 */
 
/* var pdf= request.getAttribute("pdf");
var pdfData = atob(pdf);
alert("In jsp 2");
PDFJS.getDocument({data: pdfData}).then(function(pdf) {
	thePDF = pdfData;
	numPages = thePDF.numPages;
	thePDF.getPage( 1 ).then( handlePages );
});
				
function handlePages(page)
{
    var viewport = page.getViewport( 1 );
    var canvas = document.createElement( "canvas" );
    //var canvas = window.open( thePDF,'window','width=600,height=400' );
    canvas.style.display = "block";
    var context = canvas.getContext('2d');
    canvas.height = viewport.height;
    canvas.width = viewport.width;
    page.render({canvasContext: context, viewport: viewport});
    document.body.appendChild( canvas );
    currPage++;
    if ( thePDF !== null && currPage <= numPages )
    {
        thePDF.getPage( currPage ).then( handlePages );
    
}} */	 
</script>


</html>