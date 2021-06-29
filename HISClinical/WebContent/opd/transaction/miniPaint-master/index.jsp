<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!-- 
/**
 * @author CDAC
 */
-->


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page import="opd.OpdConfig"%>
<%@page import="ehr.ImageExam.EHRSection_ImageExamVO"%>
<%@page import="hisglobal.tools.imageeditor.HisImageEditorConfig"%>
<%@page import="hisglobal.tools.imageeditor.servlets.ImageEditorServlet"%> <!-- Added by VASU on 8-AUG-2017 -->
<%-- <%@page import="hisglobal.tools.imageeditor.applets.ImageEditorApplet"%> --%>

<html>
<head>
<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery-migrate.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/menu/menu.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/imagefilters/imagefilters.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/glfx/glfx.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/exif/exif.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/harmony/harmony.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/libs/vintage.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/libs/sift.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/elements.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/libs/helpers.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/libs/popup.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/file-saver/canvas-toBlob.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/file-saver/FileSaver.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/hqx/hqx.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/translate/jquery.translate.min.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/hermite-resize/hermite.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>e-Shushrut Image Editor</title>

<link rel="stylesheet" type="text/css" href="styles/stylesNew.css" />
<link rel="shortcut icon" href="img/favicon.png" />
<!-- <style>
#canvas_front
{
pointer-events: none;
}
</style> -->
<script type="text/javascript">
//Added by VASU on 28-JULY-2017 

window.onload = function()
{
	<%String imgName=request.getParameter("imgsrc413");
	String imgSrc123=null;
	//String imgSrc321=null;
	String imgAltName=null;
	String flag=null;
	String flag1=null;
	if((List)session.getAttribute(OpdConfig.IMAGE_EXAM_LIST)!=null)
		{
		List ImageVO=new ArrayList();
	ImageVO=(List)session.getAttribute(OpdConfig.IMAGE_EXAM_LIST);
	System.out.println(ImageVO.size());
	
	
	System.out.println(imgName);
	if(ImageVO!=null)
	{
		if(ImageVO.size()==0) {
			
			flag=request.getParameter("flag");
			//imgSrc123=imgName;
			System.out.println(flag);
			//imgSrc321=imgAltName;
			//System.out.println(imgSrc321);
			flag1=flag;
			System.out.println(flag1);
			//imgSrc123=(String)request.getSession().getAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_64);
		}
		//String prevClinicalSectionCode="", newClinicalSectionCode="";
		for(int i=0;i<ImageVO.size();i++)
		{
			//String viewurl="", exturl="", selecturl="";
			EHRSection_ImageExamVO vo  = (EHRSection_ImageExamVO)ImageVO.get(i);
			if(imgName!=null && imgName.equals(vo.getImageName())){
				imgSrc123=vo.getImageFileName();
				//imgName=imgName;
				//System.out.println(imgSrc123);
			}
			
			else {
				
				flag=request.getParameter("flag");
				//imgSrc123=imgName;
				System.out.println(flag);
				//imgSrc321=imgAltName;
				//System.out.println(imgSrc321);
				flag1=flag;
				System.out.println(flag1);
				//imgSrc123=(String)request.getSession().getAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_64);
			}
				
		}
		
	} 
	
		
		}
	
	
	//System.out.println(imgSrc123);
		//imgSrc123=imgName;
	%>
	   
	    <%-- var canvas = document.getElementById("c");
	    var ctx = canvas.getContext("2d");
	$('#canvas_front').append(<%=imgSrc123%>) --%>
	//var img123=
	//var imgCode = window.opener.$('#imgCdFrIndxId').val();
	//var crNo = window.opener.$('#patCrNoForImgUploadId').val();
	//var episodeCode = window.opener.$('#patEpisodeCodeForImgUploadId').val();
	//var imgSrc=window.opener.$('#imgSrc').val();
	//alert($('#imgName').val());
	$("#imgCdToBringId").val($('#imgName').val());
	//if(imgSrc=='undefined'){
		//alert("inseide single Page");
		//alert(document.getElementsByName("imgSrc123")[0].value);
		var canvas = document.getElementById("canvas_front");
		var ctx = canvas.getContext("2d");
		
		var imgproc=$('#imgSrc123').val();
		//alert(imgproc.length);
		var image = new Image();
		if(imgproc.length==4 || imgproc==''){
			//alert("Inside null image");
			   var imgSrc312=window.parent.document.getElementById('editedimgSrc123').value;
			  // alert(imgSrc312);
			  // alert($('#flag').val());
			//alert(imgSrc312);
			image.src=imgSrc312;   
			//alert(image.src);
		
		}
		else{
			//alert("inside else121");
			image.src=$('#imgSrc123').val();
			//image.src=json.imgDt;
		}
		//console.log(image.src);
		image.onload = function () {
			EDIT.save_state();
			//LAYER.layer_add(blobUrl);
             	//	alert("12")	;
			if (image.width > WIDTH)
				WIDTH = image.width;
			//alert(WIDTH);
			if (image.height > HEIGHT)
				HEIGHT = image.height;
			LAYER.set_canvas_size();
			//alert("123")	;
			canvas_active().drawImage(image, 0, 0);
			if(EVENTS.autosize == true)
				IMAGE.trim();
			GUI.zoom_auto(true);
			GUI.redraw_preview();
			call_menu(LAYER,'layer_new');
			//alert("123434")	;
		};
		 //ctx.drawImage(image, 0, 0); 
<%-- //$('#imgToBringId').val(<%=imgSrc123%>); --%>
	/* }
	else
	$('#imgToBringId').val(imgSrc);
	$("#crNoToBringId").val(crNo);
	$("#episodeCodeToBringId").val(episodeCode);
	//alert("Cr Num:"+crNo);
	//alert("Episode Code:"+episodeCode);
	//alert(imgCode);
	$("#imgCdToBringId").val(imgCode);
	call_menu(FILE, 'file_open_url'); */
	//alert(window.parent.location);

}
	//alert(document.getElementById('canvas_front').value);
	//var canvas = document.getElementById("canvas_front");
	//var ctx = canvas.getContext("2d");
	
	//ctx.fillStyle = "#FF0000";
	//ctx.fillRect(0, 0, 80, 80);

	/* var canvas = document.getElementById('canvas_front');
    var context = canvas.getContext('2d');
    var imageObj = new Image();
    imageObj.onload = function() {
    context.drawImage(imageObj, 69, 50);
    };
    // imageObj.src = 'http://www.html5canvastutorials.com/demos/assets/darth-vader.jpg';
     //imageObj.src = 'file://C:/Users/Public/Pictures/Sample Pictures/Penguins.jpg'
     imageObj.src = 'https://upload.wikimedia.org/wikipedia/commons/c/c7/GoogleLogoSept12015.png'; */

/* 	var canvas=document.getElementById("canvas_front");
	var ctx=canvas.getContext("2d");
	var cw=canvas.width;
	var ch=canvas.height;

	function reOffset(){
      //alert("inside reOffset");
	  var BB=canvas.getBoundingClientRect();
	  offsetX=BB.left;
	  offsetY=BB.top;        
	}
	var offsetX,offsetY;
	reOffset();
	window.onscroll=function(e)
	{
	 reOffset();
    }
    var isDown=false;
	var startX,startY;

	var img=new Image();
	img.onload=start;
	img.src="http://www.html5canvastutorials.com/demos/assets/darth-vader.jpg";
	function start(){
		//alert("inside start");
	  cw=canvas.width=img.width;
	  ch=canvas.height=img.height;

	  ctx.fillStyle='gold';

	  ctx.drawImage(img,0,0);

	  ctx.globalCompositeOperation='destination-over';

	  $("#canvas_front").mousedown(function(e){handleMouseDown(e);});
	  $("#canvas_front").mousemove(function(e){handleMouseMove(e);});
	  $("#canvas_front").mouseup(function(e){handleMouseUp(e);});
	  $("#canvas_front").mouseout(function(e){handleMouseOut(e);});
    }
	function handleMouseDown(e){
	  //alert("inside handleMouseDown");
	  e.preventDefault();
	  e.stopPropagation();

	  isDown=false;
	}

	function handleMouseUp(e){
	 // alert("inside handleMouseUp");
	  e.preventDefault();
	  e.stopPropagation();
	  isDown=false;
	}

	function handleMouseOut(e){
	 // alert("inside handleMouseOut");	
	  e.preventDefault();
	  e.stopPropagation();

	  isDown=false;
	}

	function handleMouseMove(e){
	  if(!isDown){
		  return;
		  }
	  e.preventDefault();
	  e.stopPropagation();

	  mouseX=parseInt(e.clientX-offsetX);
	  mouseY=parseInt(e.clientY-offsetY);

	  ctx.beginPath();
	  ctx.arc(mouseX,mouseY,10,0,Math.PI*2);
	  ctx.closePath();
	  ctx.fill();

	}
     
}; */

//END VASU

/* function setAplletsize(h,w)
{
	var app=document.getElementsByName("imageApplet")[0];
	app.height=h;
	app.width=w;
	window.height=h;
	document.width=w;
} 

function saveImageinJS()
{
	//var canvas = document.getElementById("canvas_front"); //To save Canvas as PNG Image
	//window.open(canvas.toDataURL("image/png"));
	var canvas=document.getElementById("canvas_front");
	var link = document.getElementById("Save");
    //link.innerHTML = 'download image';
    link.addEventListener('click', function(ev) {
    link.href = canvas.toDataURL();
    link.download = "mypainting.png";
}, false);
document.body.appendChild(link);
	
	//alert("save Image");
	alert(document.getElementById('canvas_front').value)
	document.getElementById("canvas_front").saveImage();
	document.getElementById("canvas_front").saveImage();
//	document.applets[0].saveImage();
	
	reportOpener("SAVED");
	closeWindow();
}

function cancelImageinJS()
{
	alert(" Inside   cancelImageinJS() .....");
	reportOpener("CANCELED");
	closeWindow();
}

function closeWindow()
{
	window.close();
}

function reportOpener(mode)
{
	document.getElementById("status").value=mode;
	opener.CallFromImageEditor(document.getElementById("status").value);
}

function WhenClosed()
{
	if(document.getElementById("status").value=="NOTHING")
		reportOpener("CANCELED");
}

function Welcome()
{
	alert('Welcome !!!! \n Enjoy the Image Editor .....');
	
	alert('Please Wait !!!! \n Until Applet Loading is Completed .....');
	
	imageApplet.setActive();
} */

$("#frmImg").submit(function(e) {
	e.preventDefault();

	alert("inside ajax");
    var url = "/HISClinical/servlets/ImageEditorServlet"; // the script where you handle the form input.

    $.ajax({
           type: "POST",
           url: url,
           data: $("#frmImg").serialize(), // serializes the form's elements.
           success: function(data)
           {
               alert(data); // show response from the php script.
           }
         });

    e.preventDefault(); // avoid to execute the actual submit of the form.
});


</script>
</head>

<body>

<input type="hidden" id="imgSrc123" value="<%=imgSrc123%>">
<%-- <input type="hidden" id="imgSrc321" value="<%=imgSrc321%>"> --%>
<input type="hidden" id="imgName" value="<%=imgName%>">
<input type="hidden" id="flag" name="flag" value="<%=flag1%>">
<!-- <form action="/HISClinical/servlets/ImageEditorServlet" method="post" id="frmImg" name="frmImg"> -->
<div id="wrapper">
	<div id="sidebar_left">
		<a id="logo" href="">Image Editor</a>
		<div id="menu_left_container">
			<noscript>
				Select object tool, Select area tool, Magic Wand Tool, 
				Erase, Fill, Pick Color, Pencil, Draw line, Draw letters, 
				Draw rectangle, Draw circle, Brush, Blur tool, Sharpen tool, 
				Clone tool, Gradient
			</noscript>
		</div>
		<div style="clear:both;"></div>
		<div id="main_color_container">
			<input type="color" id="main_color" value="#0000ff" onchange="GUI.set_color(this);" />
			<div style="display:none;" id="main_color_alt" onclick="GUI.toggle_color_select();"></div>
		</div>
		<div class="block" id="all_colors"></div>
		<div class="block">
			<input type="text" id="color_hex" value="#000000" /><br />
			<div id="main_color_rgb">
				<a class="red" href="#" title="Red" onclick="return false;">Red</a>
				<input id="rgb_r" onKeyUp="GUI.set_color_rgb(this, 'r')" onchange="GUI.set_color_rgb(this, 'r')" type="number" value="255" />
				<br />				
				<a class="green" href="#" title="Green" onclick="return false;">Green</a>
				<input id="rgb_g" onKeyUp="GUI.set_color_rgb(this, 'g')" onchange="GUI.set_color_rgb(this, 'g')" type="number" value="255" />
				<br />
				<a class="blue" href="#" title="Blue" onclick="return false;">Blue</a>
				<input id="rgb_b" onKeyUp="GUI.set_color_rgb(this, 'b')" onchange="GUI.set_color_rgb(this, 'b')" type="number" value="255" />
				<br />
				<a class="alpha" href="#" title="Alpha" onclick="return false;">Alpha</a>
				<input id="rgb_a" onKeyUp="GUI.set_color_rgb(this, 'a')" onchange="GUI.set_color_rgb(this, 'a')" type="number" value="255" />
				<br />
			</div>
		</div>
		<div class="block" id="mouse_info">
			<span class="trn mouse_info_title">Size:</span>
			<span id="mouse_info_size"></span><br />
			
			<span style="font-size:10px;" class="trn mouse_info_title">Mouse:</span>
			<span id="mouse_info_mouse"></span><br />
			
			<div style="display:none;" id="mouse_info_selected">
				<br />
				<span class="mouse_info_title">XY:</span>
				<span id="mouse_info_xy"></span><br />

				<span class="trn mouse_info_title">Area:</span>
				<span id="mouse_info_area"></span><br />
			</div>
		</div>
		<div class="block" id="action_attributes"></div>
	</div>
	<div id="canvas_wrapper">
		<canvas id="canvas_back"><div class="trn error">Your browser doesn't support canvas.</div></canvas>
		<div id="canvas_more"></div>
		<canvas id="canvas_front"><%-- <bean:write name="OpdImageEditorFB" property="outFileName"/>
		<bean:write name="OpdImageEditorFB" property="outFilePath"/> --%>
		</canvas>
		<input type="hidden" name="cMode" value="SAVEIMAGE" />
		<input type="hidden" name="outFile" value="" />
		<input type="hidden" name="outFilePath" value="" />
		<input type="hidden" name="imageData" value="" />
		<canvas id="canvas_grid"></canvas>

		<div id="resize-w"></div>
		<div id="resize-h"></div>
		<div id="resize-wh"></div>
				
	</div>
	<div id="sidebar_right">
		<div id="preview">
			<canvas id="canvas_preview"></canvas>
			<div style="margin-top:105px;padding-left:5px;">
				<input title="Zoom out" onclick="GUI.zoom(-1, true);" style="width:25px;" class="layer_add" type="button" value="-" />
				<input title="Reset zoom level" onclick="GUI.zoom(100, true);" class="layer_add" id="zoom_nr" type="button" value="100%" />
				<input title="Zoom in" onclick="GUI.zoom(+1, true);" style="width:25px;" class="layer_add" type="button" value="+" />
				<input title="Fit" onclick="GUI.zoom_auto();" class="layer_add" type="button" value="Fit" />

				<input style="width:95%;margin-top:5px;" id="zoom_range" type="range" value="100" min="50" max="1000" step="50" oninput="GUI.zoom(this.value, true);" /> 
			</div>
		</div>
		<div id="layers_base" style="display:none">
			<b class="trn">Layers</b> <a title="Add new layer" class="layer_add" onclick="EDIT.save_state();LAYER.layer_add();return false;" href="#">+</a>
			<a title="Move down" onclick="EDIT.save_state();LAYER.move_layer('down');return false;" class="layers_arrow" href="#">&darr;</a>
			<a title="Move up" onclick="EDIT.save_state();LAYER.move_layer('up');return false;" class="layers_arrow" href="#">&uarr;</a>
			<div style="margin-top:5px;" id="layers"></div>
			</div>
			<div>
<%-- 			<img id="Save" class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' tabindex="1" style="cursor: pointer;" onclick="saveImageinJS()" onkeypress="if(event.keyCode==13) saveImageinJS()" >	 --%>
			<img id="Save" class="button close" src='<his:path src="/hisglobal/images/btn-ok.png"/>' data-dismiss="modal" tabindex="1" style="cursor: pointer;" onclick="call_menu(FILE, 'file_save');">		
				
				<img id="Cancel" class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelImage()" onkeypress="">
			   </div>
		</div>
	</div>
</div>
<div id="mobile_menu">
	<a class="left_mobile_menu" onclick="GUI.toggle('#sidebar_left');return false;" href="#"></a>
	<a class="right_mobile_menu" onclick="GUI.toggle('#sidebar_right');return false;" href="#"></a>
</div>
<div id="main_menu" class="ddsmoothmenu" style="display:none;">
	<ul>
	<li>
		<a class="icon file trn" href="#">File</a>
		<ul>
			<li><a class="trn" onclick="call_menu(FILE, 'file_new');" href="#">New</a></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_open');" href="#">Open</a></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_open_url');" href="#">Open URL</a></li>
			
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_save');" href="#">Save as</a></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_print');" href="#">Print</a></li>
			<!-- <li><div class="mid-line"></div></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_quicksave');" href="#">Quick save</a></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_quickload');" href="#">Quick load</a></li> -->
		</ul>
	</li>
	<li>
		<a class="icon edit trn" href="#">Edit</a>
		<ul>
			<li><a class="trn" onclick="call_menu(EDIT, 'edit_undo');" href="#">Undo</a></li>
			<li><div class="mid-line"></div></li>
			<li><a class="trn" onclick="call_menu(EDIT, 'edit_cut');" href="#">Cut selection</a></li>
			<li><a class="trn" onclick="call_menu(EDIT, 'edit_copy');" href="#">Copy selection</a></li>
			<li><a class="trn" onclick="call_menu(EDIT, 'edit_paste');" href="#">Paste selection</a></li>
			<li><div class="mid-line"></div></li>
			<li><a class="trn" onclick="call_menu(EDIT, 'edit_select');" href="#">Select all</a></li>
			<li><a class="trn" onclick="call_menu(EDIT, 'edit_clear');" href="#">Clear selection</a></li>
		</ul>
	</li>
	<li>
		<a class="icon image trn" href="#">Image</a>
		<ul>
			<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_information');" href="#">Information</a></li>
			<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_size');" href="#">Size</a></li>
			<li><a class="trn" onclick="call_menu(IMAGE, 'image_trim');" href="#">Trim</a>
			<li><a class="trn" onclick="call_menu(IMAGE, 'image_crop');" href="#">Crop Selection</a>
			<li class="more">
				<a class="trn" href="#">Zoom</a>
				<ul>
					<li><a class="trn" onclick="call_menu(IMAGE, 'zoom_in');" href="#">Zoom In</a></li>
					<li><a class="trn" onclick="call_menu(IMAGE, 'zoom_out');" href="#">Zoom Out</a></li>
					<li><div class="mid-line"></div></li>
					<li><a class="trn" onclick="call_menu(IMAGE, 'zoom_original');" href="#">Original size</a></li>
					<li><a class="trn" onclick="call_menu(IMAGE, 'zoom_auto');" href="#">Fit window</a></li>
				</ul>
			</li>
			<li><div class="mid-line"></div></li>
			<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_resize');" href="#">Resize</a></li>
			<li class="more">
				<a class="trn" href="#">Rotate</a>
				<ul>
					<li><a class="trn" onclick="call_menu(IMAGE, 'image_rotate_left');" href="#">Left</a></li>
					<li><a class="trn" onclick="call_menu(IMAGE, 'image_rotate_right');" href="#">Right</a></li>
					<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_rotate');" href="#">Rotation</a></li>
				</ul>
			</li>
			<li class="more">
				<a class="trn" href="#">Flip</a>
				<ul>
					<li><a class="trn" onclick="call_menu(IMAGE, 'image_vflip');" href="#">Vertical</a></li>
					<li><a class="trn" onclick="call_menu(IMAGE, 'image_hflip');" href="#">Horizontal</a></li>
				</ul>
			</li>
			<li><div class="mid-line"></div></li>
			<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_colors');" href="#">Color corrections</a></li>
			<li><a class="trn" onclick="call_menu(IMAGE, 'image_auto_adjust');" href="#">Auto adjust colors</a></li>
			<li><a class="trn" onclick="call_menu(IMAGE, 'image_GrayScale');" href="#">GrayScale</a>
			<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_decrease_colors');" href="#">Decrease color depth</a></li>
			<li><a class="trn" onclick="call_menu(IMAGE, 'image_negative');" href="#">Negative</a></li>
			<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_grid');" href="#">Grid</a></li>
			<li><div class="mid-line"></div></li>
			<li><a class="trn dots" onclick="call_menu(IMAGE, 'image_histogram');" href="#">Histogram</a></li>
		</ul>
	</li>
	<li>
		<a class="icon layer trn" href="#">Layer</a>
		<ul>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_new');" href="#">New</a></li>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_duplicate');" href="#">Duplicate</a></li>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_show_hide');" href="#">Show / Hide</a></li>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_crop');" href="#">Crop Selection</a>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_delete');" href="#">Delete</a></li>
			<li><div class="mid-line"></div></li>
			<li class="more">
				<a class="trn" href="#">Move</a>
				<ul>
					<li><a class="trn" onclick="call_menu(LAYER, 'layer_move_up');" href="#">Up</a></li>
					<li><a class="trn" onclick="call_menu(LAYER, 'layer_move_down');" href="#">Down</a></li>
				</ul>
			</li>
			<li><a class="trn dots" onclick="call_menu(LAYER, 'layer_opacity');" href="#">Opacity</a></li>
			<li><a class="trn dots" onclick="call_menu(LAYER, 'layer_rename');" href="#">Rename</a></li>
			<li><div class="mid-line"></div></li>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_trim');" href="#">Trim</a></li>
			<li><a class="trn dots" onclick="call_menu(LAYER, 'layer_resize');" href="#">Resize</a></li>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_clear');" href="#">Clear</a></li>
			<li><div class="mid-line"></div></li>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_differences');" href="#">Differences Down</a></li>
			<li><a class="trn dots" onclick="call_menu(LAYER, 'layer_merge_down');" href="#">Merge Down</a></li>
			<li><a class="trn" onclick="call_menu(LAYER, 'layer_flatten');" href="#">Flatten Image</a></li>
		</ul>
	</li>
	<li>
		<a class="icon effects trn" href="#">Effects</a>
		<ul id="effects_list">
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_bw');" href="#">Black and White</a>
			<li class="more">
				<a class="trn" href="#">Blur</a>
				<ul>
					<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_BoxBlur');" href="#">Blur-Box</a>
					<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_GaussianBlur');" href="#">Blur-Gaussian</a>
					<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_StackBlur');" href="#">Blur-Stack</a>
					<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_zoomblur');" href="#">Blur-Zoom</a>
				</ul>
			</li>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_bulge_pinch');" href="#">Bulge/Pinch</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_colorize');" href="#">Colorize</a></li>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_denoise');" href="#">Denoise</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Desaturate');" href="#">Desaturate</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Dither');" href="#">Dither</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_dot_screen');" href="#">Dot Screen</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Edge');" href="#">Edge</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Emboss');" href="#">Emboss</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Enrich');" href="#">Enrich</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Grains');" href="#">Grains</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_heatmap');" href="#">Heatmap</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_jpg_vintage');" href="#">JPG Compression</a></li>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Mosaic');" href="#">Mosaic</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Oil');" href="#">Oil</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_perspective');" href="#">Perspective</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Sepia');" href="#">Sepia</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Sharpen');" href="#">Sharpen</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_Solarize');" href="#">Solarize</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_tilt_shift');" href="#">Tilt Shift</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_vignette');" href="#">Vignette</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_vibrance');" href="#">Vibrance</a>
			<li><a class="trn dots" onclick="call_menu(EFFECTS, 'effects_vintage');" href="#">Vintage</a>
		</ul>
	</li>
	<li>
		<a class="icon tools trn" href="#">Tools</a>
		<ul>
			<li><a class="trn dots" onclick="call_menu(TOOLS, 'tools_borders');" href="#">Borders</a></li>
			<li><a class="trn" onclick="call_menu(TOOLS, 'tools_sprites');" href="#">Sprites</a></li>
			<li><a class="trn" onclick="call_menu(TOOLS, 'tools_keypoints');" href="#">Key-points</a></li>
			<li><a class="trn dots" onclick="call_menu(TOOLS, 'tools_color2alpha');" href="#">Color to Alpha</a></li>
			<li><a class="trn dots" onclick="call_menu(TOOLS, 'tools_color_zoom');" href="#">Color Zoom</a></li>
			<li><a class="trn dots" onclick="call_menu(TOOLS, 'tools_replace_color');" href="#">Replace Color</a></li>
			<li><a class="trn dots" onclick="call_menu(TOOLS, 'tools_restore_alpha');" href="#">Restore alpha</a></li>

		</ul>
	</li>
	<li>
		<a class="icon help trn" href="#">Help</a>
		<ul>
			<li><a class="trn dots" onclick="call_menu(HELP, 'help_shortcuts');" href="#">Keyboard Shortcuts</a></li>
			<!-- <li><a class="trn" onclick="window.location='http://viliusle.github.io/miniPaint/';" href="http://viliusle.github.io/miniPaint/">Website</a></li> -->
			<!-- <li class="more">
				<a class="trn" href="#">Language</a>
				<ul>
					<li><a onclick="call_menu(HELP, 'help_translate', 'en');" href="#">English</a>
					<li><div class="mid-line"></div></li>
					<li><a onclick="call_menu(HELP, 'help_translate', 'zh');" href="#">ç®ä½ä¸­æï¼</a>
					<li><a onclick="call_menu(HELP, 'help_translate', 'es');" href="#">EspaÃ±ol</a>
					<li><a onclick="call_menu(HELP, 'help_translate', 'fr');" href="#">French</a>	
					<li><a onclick="call_menu(HELP, 'help_translate', 'de');" href="#">German</a>	
					<li><a onclick="call_menu(HELP, 'help_translate', 'it');" href="#">Italiano</a>
					<li><a onclick="call_menu(HELP, 'help_translate', 'ja');" href="#">Japanese</a>
					<li><a onclick="call_menu(HELP, 'help_translate', 'ko');" href="#">Korean</a>		
					<li><a onclick="call_menu(HELP, 'help_translate', 'lt');" href="#">LietuviÅ³</a>
					<li><a onclick="call_menu(HELP, 'help_translate', 'pt');" href="#">Portuguese</a>	
					<li><a onclick="call_menu(HELP, 'help_translate', 'ru');" href="#">Russian</a>	
					<li><a onclick="call_menu(HELP, 'help_translate', 'tr');" href="#">Turkish</a>	
				</ul>
			</li> -->
			<!-- <li><div class="mid-line"></div></li>
			<li><a class="trn dots" onclick="call_menu(HELP, 'help_about');" href="#">About</a></li> -->
		</ul>
	</li>
	</ul>
</div>
<div style="display:none;" id="tmp"></div>
<div id="popup"></div>
<input type="hidden" name="imgCdToBring" id="imgCdToBringId" />
<input type="hidden" name="crNoToBring" id="crNoToBringId">
<input type="hidden" name="episodeCodeToBring" id="episodeCodeToBringId">
<input type="hidden" name="imgToBring" id="imgToBringId">
<!-- </form> -->

<script src="/HISClinical/opd/transaction/miniPaint-master/languages/de.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/es.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/fr.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/it.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/ja.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/ko.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/lt.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/pt.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/ru.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/tr.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/languages/zh.js"></script>

<script src="/HISClinical/opd/transaction/miniPaint-master/js/events.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/file.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/edit.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/image.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/layers.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/effects.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/tools.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/draw_tools.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/gui.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/help.js"></script>

<script src="/HISClinical/opd/transaction/miniPaint-master/config.js"></script>
<script src="/HISClinical/opd/transaction/miniPaint-master/js/main.js"></script>

<script src="/HISClinical/opd/transaction/miniPaint-master/js/getImage.js"></script>
</body>
<%--  <html:hidden name="OpdImageEditorFB" property="outFileName"/>
<html:hidden name="OpdImageEditorFB" property="outFilePath"/>  --%>

</html>
