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
<%@page import="opd.*"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- <link rel="stylesheet" type="text/css" href="/opd/transaction/miniPaint-master/styles/styles.css" /> -->
<link rel="shortcut icon" href="img/favicon.png" />
<link href="/HISClinical/opd/transaction/miniPaint-master/styles/stylesNew.css" rel="stylesheet">


<title>e-Shushrut Image Editor</title>
<script type="text/javascript">

function setAplletsize(h,w)
{
	var app=document.getElementsByName("imageApplet")[0];
	app.height=h;
	app.width=w;
	window.height=h;
	document.width=w;
} 

function saveImageinJS()
{
	var name = document.getElementsByName("outFileName")[0].value;
 	var path = document.getElementsByName("outFilePath")[0].value;
 	var canvas = document.getElementById("canvas_back");
 	var dataURL = canvas.toDataURL("image/png", 1.0);
 	alert(dataURL)
 	var ctx=canvas.getContext("2d");
 	alert(ctx)
 	var imgData=ctx.getImageData(10,10,50,50);
 	alert(imgData)
 	//alert(name)
 	
	  $.ajax({
		    url: createFHashAjaxQuery("/HISClinical/opd/imageEditorNew.cnt?hmode=SAVE&name="+name+"&path="+path+"&dataurl="+dataURL),
		    		
		    success: function(data) {
		    	//alert(data)
		    	if(data == "1")
		    		{
		    		
		    		reportOpener("SAVED");
		    		closeWindow();
		    			    
		    		}
		    
		    	else
		    		{
		    		//  alert("cannot save");
		    			//return false;
		    		}
		    }
		  }); 
	  
// 	var canvas_back = document.getElementById("canvas_back").getContext("2d");		//layer for grid/transparency
//  	var canvas_front = document.getElementById("canvas_front").getContext("2d");		//tmp layer
//  	var canvas_grid = document.getElementById("canvas_grid").getContext("2d");		//grid layer
//  	var canvas_preview = document.getElementById("canvas_preview").getContext("2d");	//mini preview
// 	alert(canvas_back)
// 	alert(canvas_front)
// 	alert(canvas_grid)
// 	alert(canvas_preview)
// 	var name = document.getElementsByName("inFileName")[0].value;
// 	var path = document.getElementsByName("inFilePath")[0].value;
	//alert(name)
// 	alert(path)
	//alert(document.forms[0].hmode)
	// document.forms[0].hmode="SAVEEE";
	//alert(document.forms[0].hmode)
			//  document.forms[0].submit();
	//submitFormOnValidate(true,'SAVE');
	//alert(document.getElementById('Layer #1').html)
	//document.getElementById("canvas_front").getContext("2d").saveImage();
	//document.getElementById("canvas_front").saveImage();
//	document.applets[0].saveImage();
	
	
	//closeWindow();
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
	alert(document.getElementById("status").value)
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

		var canvas=document.getElementById("canvas_front");
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
	     
	};
		
	//alert('Please Wait !!!! \n Until Applet Loading is Completed .....');
	
	//imageApplet.setActive();
//}

</script>

</head>
<body onunload="WhenClosed()" onload="Welcome()">
<html:form action="/imageEditorNew">
<jsp:include page="/opd/opdImageExamTab.cnt" flush="true" />
<div id="wrapper">
	<div id="sidebar_left">
		<a id="logo" href="">OPD Image Editor</a>
		<input type="hidden" name="status" id="status" value="NOTHING" >
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
		<canvas id="canvas_front">
		 <bean:write name="OpdImageEditorFB" property="inFileName"/>
		<bean:write name="OpdImageEditorFB" property="inFilePath"/> 
		</canvas>
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
		<div id="layers_base">
			<b class="trn">Layers</b> <a title="Add new layer" class="layer_add" onclick="EDIT.save_state();LAYER.layer_add();return false;" href="#">+</a>
			<a title="Move down" onclick="EDIT.save_state();LAYER.move_layer('down');return false;" class="layers_arrow" href="#">&darr;</a>
			<a title="Move up" onclick="EDIT.save_state();LAYER.move_layer('up');return false;" class="layers_arrow" href="#">&uarr;</a>
			<div style="margin-top:5px;" id="layers"></div>
			<img id="Save" class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' tabindex="1" style="cursor: pointer;" onclick="saveImageinJS()" onkeypress="if(event.keyCode==13) saveImageinJS()" >		
				<img id="Cancel" class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelImageinJS()" onkeypress="if(event.keyCode==13) cancelImageinJS()">
			
		</div>
	</div>
</div>
<div id="mobile_menu">
	<a class="left_mobile_menu" onclick="GUI.toggle('#sidebar_left');return false;" href="#"></a>
	<a class="right_mobile_menu" onclick="GUI.toggle('#sidebar_right');return false;" href="#"></a>
</div>
<div id="main_menu" class="ddsmoothmenu">
	<ul>
	<li>
		<a class="icon file trn" href="#">File</a>
		<ul>
			<li><a class="trn" onclick="call_menu(FILE, 'file_new');" href="#">New</a></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_open');" href="#">Open</a></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_save');" href="#">Save as</a></li>
			<li><a class="trn dots" onclick="call_menu(FILE, 'file_print');" href="#">Print</a></li>
	
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
			

		</ul>
	</li>
	</ul>
</div>
<div style="display:none;" id="tmp"></div>
<div id="popup"></div>
<%-- <his:javascript src="/opd/js/imageEditorFunctions.js"/> --%>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/jquery/jquery.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/jquery/jquery-migrate.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/menu/menu.min.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/imagefilters/imagefilters.min.js"/>

<his:javascript src="/opd/transaction/miniPaint-master/vendor/glfx/glfx.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/exif/exif.min.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/harmony/harmony.min.js"/>


<his:javascript src="/opd/transaction/miniPaint-master/libs/vintage.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/libs/sift.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/elements.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/libs/helpers.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/libs/popup.js"/>

<his:javascript src="/opd/transaction/miniPaint-master/vendor/file-saver/canvas-toBlob.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/file-saver/FileSaver.min.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/hqx/hqx.min.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/vendor/translate/jquery.translate.min.js"/>

<his:javascript src="opd/transaction/miniPaint-master/vendor/hermite-resize/hermite.js"/>


<his:javascript src="/opd/transaction/miniPaint-master/languages/de.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/es.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/fr.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/it.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/ja.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/ko.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/lt.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/pt.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/ru.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/tr.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/languages/zh.js"/>




<his:javascript src="/opd/transaction/miniPaint-master/js/events.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/file.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/edit.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/image.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/layers.js"/>

<his:javascript src="/opd/transaction/miniPaint-master/js/effects.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/tools.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/draw_tools.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/gui.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/help.js"/>


<his:javascript src="/opd/transaction/miniPaint-master/config.js"/>
<his:javascript src="/opd/transaction/miniPaint-master/js/main.js"/>
</html:form>
</body>
 <html:hidden name="OpdImageEditorFB" property="hmode"/>
 <html:hidden name="OpdImageEditorFB" property="inFileName"/>
<html:hidden name="OpdImageEditorFB" property="inFilePath"/>
 <html:hidden name="OpdImageEditorFB" property="outFileName"/>
<html:hidden name="OpdImageEditorFB" property="outFilePath"/>
</html>
