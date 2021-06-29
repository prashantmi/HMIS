/* global MAIN, POP, LAYER, EXIF, HELPER, IMAGE, GUI, EDIT, DRAW, EVENTS */
/* global SAVE_TYPES, canvas_active */

var FILE = new FILE_CLASS();

/** 
 * manages files actions
 * 
 * @author ViliusL
 */
function FILE_CLASS() {

	/**
	 * file info: exif, general data 
	 */
	this.file_info = {
		general: [],
		exif: [],
	};
	
	/**
	 * default name used for saving file
	 */
	this.SAVE_NAME = 'example';			//default save name
	
	/**
	 * save types config
	 */
	this.SAVE_TYPES = [
		"PNG - Portable Network Graphics",	//default
		"JPG - JPG/JPEG Format",		//autodetect on photos where png useless?
		"JSON - Full layers data",		//aka PSD
		"BMP - Windows Bitmap",			//firefox only, useless?
		"WEBP - Weppy File Format",		//chrome only
		];
	//new
	this.file_new = function () {
		var w = WIDTH;
		var h = HEIGHT;
		var resolutions = ['Custom'];
		for(var i in GUI.common_dimensions){
			resolutions.push(GUI.common_dimensions[i][0]+'x'+GUI.common_dimensions[i][1]+' - '+GUI.common_dimensions[i][2]);
		}
		
		var save_resolution_cookie = HELPER.getCookie('save_resolution');
		if(save_resolution_cookie == '')
			save_resolution = 'No';
		else{
			save_resolution = 'Yes';
			var last_resolution = JSON.parse(save_resolution_cookie);
			w = parseInt(last_resolution[0]);
			h = parseInt(last_resolution[1]);
		}
		
		POP.add({name: "width", title: "Width:", value: w});
		POP.add({name: "height", title: "Height:", value: h});
		POP.add({name: "resolution", title: "Resolution:", values: resolutions});
		POP.add({name: "transparency", title: "Transparent:", values: ['Yes', 'No']});
		POP.add({name: "save_resolution", title: "Save resolution:", value: save_resolution, values: ['Yes', 'No']});
		POP.show(
			'New file...', 
			function (response) {
				var width = parseInt(response.width);
				var height = parseInt(response.height);
				var resolution = response.resolution;
				var save_resolution = response.save_resolution;
				
				if(resolution != 'Custom'){
					var dim = resolution.split(" ");
					dim = dim[0].split("x");
					width = dim[0];
					height = dim[1];
				}
				if (response.transparency == 'Yes')
					GUI.TRANSPARENCY = true;
				else
					GUI.TRANSPARENCY = false;

				GUI.ZOOM = 100;
				WIDTH = width;
				HEIGHT = height;
				MAIN.init();
				
				if(save_resolution == 'No')
					save_resolution = '';
				else {
					save_resolution = JSON.stringify([WIDTH, HEIGHT]);
				}				
				HELPER.setCookie('save_resolution', save_resolution);
			}
		);
	};

	//open
	this.file_open = function () {
		EDIT.save_state();
		this.open();
	};
	
	//open url
	this.file_open_url = function(){
		var im= $('#imgToBringId').val();
		var vr = $('[name="imgCdToBring"]').val();
		var cr = $('[name="crNoToBring"]').val();
		var ep = $('[name="episodeCodeToBring"]').val();
		
		
		//alert(vr);
		//alert(cr);
		//alert(ep);
		//alert(im);
		
		//POP.add({name: "url", title: "URL:", value: "/HISClinical/hisglobal/ImgForImgEdtr/"+vr+".jpg"});
		POP.add({name: "url", title: "URL:", value: im});
		POP.show('Open URL', [FILE, 'file_open_url_handler']);
		POP.save();
	};
	
	//handler for open url
	this.file_open_url_handler = function(user_response){
		var url = user_response.url;
		//alert(url);
		if(url == '')
			return;
		
		//var layer_name = url.replace(/^.*[\\\/]/, '');
		var  layer_name  = dataURItoBlob(url, 'image/jpg');

		const blobUrl = URL.createObjectURL(layer_name);
		
		//alert("layer_name"+layer_name);
		
		var img = new Image();
		img.crossOrigin = "Anonymous";
		img.src=blobUrl;
		//img.src=layer_name;
		//document.body.appendChild(img);
		img.onload = function () {
			EDIT.save_state();
			LAYER.layer_add(blobUrl);
             			
			if (img.width > WIDTH)
				WIDTH = img.width;
			if (img.height > HEIGHT)
				HEIGHT = img.height;
			LAYER.set_canvas_size();
			
			canvas_active().drawImage(img, 0, 0);
			if(EVENTS.autosize == true)
				IMAGE.trim();
			GUI.zoom_auto(true);
			GUI.redraw_preview();
			call_menu(LAYER,'layer_new');
		};
		img.onerror = function (ex) {
			POP.add({html: 'Sorry, image could not be loaded. Try copy image and paste it.'});
			POP.show('Error', '.');
		};
		//img.src = url;
	};

	
	
	function dataURItoBlob(dataURI, type) {
	    // convert base64 to raw binary data held in a string
	    var byteString = atob(dataURI);
	   // alert(byteString);
	    // separate out the mime component
	    var mimeString = dataURI;

	    // write the bytes of the string to an ArrayBuffer
	    var ab = new ArrayBuffer(byteString.length);
	    var ia = new Uint8Array(ab);
	    for (var i = 0; i < byteString.length; i++) {
	        ia[i] = byteString.charCodeAt(i);
	    }

	    // write the ArrayBuffer to a blob, and you're done
	    var bb = new Blob([ab], { type: type });
	    //alert(bb);
	    return bb;
	    
	}
	
	
	
	
	//save
	this.file_save = function (user_response) {
		
		//this.save_dialog();

		//alert("inside save");
		/*var fname = "A.png";
		//alert(fname);
		var tempCanvas = document.createElement("canvas");
		var tempCtx = tempCanvas.getContext("2d");
		tempCanvas.width = WIDTH;
		tempCanvas.height = HEIGHT;
		HELPER.setCookie('save_default', 'png');
		var parts = ["PNG","-","Portable","Network","Graphics"];
		//alert("parts:"+parts);
		var layers = "All";
		var type = 'PNG';
		var trim="No";*/

		// Added by VASU on 30-AUG-2017
		var fname = "A.PNG";
		var tempCanvas = document.createElement("canvas");
		var tempCtx = tempCanvas.getContext("2d");
		tempCanvas.width = WIDTH;
		tempCanvas.height = HEIGHT;
		HELPER.setCookie('save_default', 'png');
		var parts = ["PNG","-","Portable","Network","Graphics"];
		//alert("parts:"+parts);
		var layers = "All";
		var type = 'PNG';
		var trim="No";
  // End Vasu
		
		//take data
		for(var i = LAYER.layers.length-1; i >=0; i--){
			if (LAYER.layers[i].visible == false)
				continue;
			if (layers == 'Selected' && type != 'JSON' && i != LAYER.layer_active)
				continue;
			   tempCtx.drawImage(document.getElementById(LAYER.layers[i].name), 0, 0, WIDTH, HEIGHT);
		}

		if (trim == 'Yes' && type != 'JSON') {
			//trim
			var trim_info = IMAGE.trim_info(tempCanvas);
			tmp_data = tempCtx.getImageData(0, 0, WIDTH, HEIGHT);
			tempCtx.clearRect(0, 0, WIDTH, HEIGHT);
			tempCanvas.width = WIDTH - trim_info.right - trim_info.left;
			tempCanvas.height = HEIGHT - trim_info.bottom - trim_info.top;
			tempCtx.putImageData(tmp_data, -trim_info.left, -trim_info.top);
		}

		if (type == 'PNG') {
			
			if (HELPER.strpos(fname, '.png') == false)
				fname = fname + ".png";
			 //alert(fname);
			tempCanvas.toBlob(function(blob) {
				
				//alert("inside tempCanvas function" +blob+"  "+fname);
				
				/*var Pic = tempCanvas.toDataURL("image/png");
				
				var base64ImageContent = Pic.replace(/^data:image\/(png|jpg);base64,/, "");
				alert(base64ImageContent);
				var blob = base64ToBlob(base64ImageContent, 'image/png');                
				alert(blob);
				
				document.getElementsByName("cMode")[0].value="SAVEIMAGE";
				document.getElementsByName("outFile")[0].value=fname;
				document.getElementsByName("outFilePath")[0].value="";
				document.getElementsByName("imageData")[0].value=blob;
				alert(document.getElementsByName("imageData")[0].value);
				
				alert(document.getElementsByName("cMode")[0].value);
				
				
				var url ="/HISClinical/servlets/ImageEditorServlet";
				var formData = new FormData();
				formData.append('picture', blob);
				
				formData.append('cMode', "SAVEIMAGE");
				formData.append('outFile', fname);
				formData.append('outFilePath', "outStandardPath");
				
				var ajax = new XMLHttpRequest();
				ajax.open("POST",'/HISClinical/servlets/ImageEditorServlet',false);
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				ajax.send(imageData);
				
				$.ajax({
						url: url, 
					    type: "POST", 
					    //data: $("#frmImg").serialize(), // serializes the form's elements.
				        //dataType: 'image/text',
					    cache: false,
					    contentType: false,
					    processData: false,
					    data: formData,
					    //data:{imgBase64:pic},
					    //dataType:JSON,
					    success: function(data)
			           {
			               alert("data"+data);
			           },
			           error: function(text) {
		                   alert("error"+text);
		               }
			         });*/
				
				// By VASU 
				
				var vr = $('[name="imgCdToBring"]').val();
				var cr = $('[name="crNoToBring"]').val();
				var ep = $('[name="episodeCodeToBring"]').val();
				var flag=$('[name="flag"]').val();
				//var imageData = tempCanvas.toDataURL("canvas")+"$%^delimeter$%^"+vr+"$%^delimeter$%^"+cr+"$%^delimeter$%^"+ep;
				var imageData = tempCanvas.toDataURL("image/jpg");
				//alert("imageData: "+imageData);  
				var json= {"ep":ep,"vr":vr,"cr":cr, "imgDt":imageData};
				var result="";
				for( key in json){
				  result+= json[key];
				  //result+= "%^delimiter^%";
				  result+= "%";
				}
				 // alert("result: "+result);
				var url1="/HISClinical/ehr/ImageExam/EHRSection_EditedImages.jsp";
				$.ajax({
				    type: "POST",
				    url: "/HISClinical/servlets/ImageEditorServlet",
				    dataType : "text",
				    contentType: "application/json",
				    data: result,
				    success: function(msg)
				    {                       
				            alert(msg);
				            //url1.load();
				          //  alert(json.imgDt);
				           // $('#editdiv').load("/HISClinical/ehr/ImageExam/EHRSection_ImageExam.jsp"," #editdiv");
				           // window.opener.location.reload();
				           // window.close();
				            
				           // parent.find('#myModal_EMR').modal('toggle');
				            if(flag=='1'){
				            	//alert(flag);
				            	parent.srcReplace(json);
				            }
				            else
				            parent.editedImage(json);
				            //opener.('#editdiv').show();
				           
				    },
				    error: function()
				    {
				        alert("Error connecting to server!");
				    }
				});
				
				// End VASU
				/*var xhr = new XMLHttpRequest();
				xhr.open("post", "/HISClinical/servlets/ImageEditorServlet", false);
				var boundary = Math.random().toString().substr(2);
				xhr.setRequestHeader("content-type", 
				    "multipart/form-data; charset=utf-8; boundary=" + boundary);
				var multipart = "--" + boundary + "" +
				    "Content-Disposition: form-data; name=myImg" +
				    "Content-type: image/png" +
				    tempCanvas.toDataURL("image/png") + "" +
				    "--" + boundary + "";
				alert(multipart);
				xhr.send(multipart);*/
				
				/*$.post('/HISClinical/servlets/ImageEditorServlet',
				        {
				            uid : cr,
				            img : imageData
				        },
				        function(data) { alert(data);});*/
				
				/*$.ajax({
				    type: "POST",
				    url: "/HISClinical/servlets/ImageEditorServlet",
				    data: {imgCode:vr,
				    	crNo:cr,
				    	epCode:ep},
				    success: function(msg)
				    {                       
				            alert(msg);
				    },
				    error: function()
				    {
				        alert("Error connecting to server!");
				    }
				}); */
				
			});
		}

		//document.getElementsByName("frmImg")[0].submit(window.event);
		
		 //this.save_dialog();
	//}
	};
	//print
	this.file_print = function () {
		window.print();
	};

	this.open = function () {
		var self = this;
		
		document.getElementById("tmp").innerHTML = '';
		var a = document.createElement('input');
		a.setAttribute("id", "file_open");
		a.type = 'file';
		a.multiple = 'multiple ';
		document.getElementById("tmp").appendChild(a);
		document.getElementById('file_open').addEventListener('change', function (e) {
			self.open_handler(e);
		}, false);

		//force click
		document.querySelector('#file_open').click();
	};
	
	this.open_image = function(image_id) {
		var img = document.getElementById(image_id);
		
		//set name
		var name = img.src.replace(/^.*[\\\/]/, '');
		LAYER.layer_add(name);
		LAYER.layer_remove(1);
		
		//draw canvas
		canvas_active().drawImage(img, 0, 0);
		IMAGE.trim();
	};
	
	this.open_handler = function (e) {
		var files = e.target.files;
		var self = this;
		if(files == undefined){
			//drag and drop
			files = e.dataTransfer.files;
		}
		
		for (var i = 0, f; i < files.length; i++) {
			f = files[i];
			if (!f.type.match('image.*') && !f.name.match('.json')){
				console.log('Wrong file type, must be image or json.');
				continue;
			}
			if (files.length == 1)
				this.SAVE_NAME = f.name.split('.')[f.name.split('.').length - 2];

			var FR = new FileReader();
			FR.file = files[i];

			FR.onload = function (event) {
				if (this.file.type.match('image.*')) {
					//image
					LAYER.layer_add(this.file.name, event.target.result, this.file.type);
					self.save_file_info(this.file);
				}
				else {
					//json
					var responce = self.load_json(event.target.result);
					if (responce === true)
						return false;
				}
			};
			if (f.type == "text/plain")
				FR.readAsText(f);
			else if (f.name.match('.json'))
				FR.readAsText(f);
			else
				FR.readAsDataURL(f);
		}
	};

	this.save_dialog = function (e) {
		alert("inside save_dialog()");
		//find default format
		var save_default = this.SAVE_TYPES[0];	//png
		if (HELPER.getCookie('save_default') == 'jpg')
			save_default = this.SAVE_TYPES[1]; //jpg

		POP.add({name: "name", title: "File name:", value: this.SAVE_NAME});
		POP.add({name: "type", title: "Save as type:", values: this.SAVE_TYPES, value: save_default});
		POP.add({name: "quality", title: "Quality (jpeg):", value: 90, range: [1, 100]});
		POP.add({name: "layers", title: "Save layers:", values: ['All', 'Selected']});
		POP.add({name: "trim", title: "Trim:", values: ['No', 'Yes']});
		POP.show('Save as', [FILE, 'save']);
		document.getElementById("pop_data_name").select();
		if (e != undefined)
			e.preventDefault();
	};

	this.save = function (user_response) {
		alert("inside save");
		fname = user_response.name;
		//alert(fname);
		var tempCanvas = document.createElement("canvas");
		//alert(tempCanvas);
		var tempCtx = tempCanvas.getContext("2d");
		//alert(tempCtx);
		tempCanvas.width = WIDTH;
		tempCanvas.height = HEIGHT;
        
		//save choosen type
		var save_default = this.SAVE_TYPES[0];	//png
		if (HELPER.getCookie('save_default') == 'jpg')
			save_default = this.SAVE_TYPES[1]; //jpg
		if (user_response.type != save_default && user_response.type == this.SAVE_TYPES[0])
			HELPER.setCookie('save_default', 'png');
		else if (user_response.type != save_default && user_response.type == this.SAVE_TYPES[1])
			HELPER.setCookie('save_default', 'jpg');

		//detect type
		var parts = user_response.type.split(" ");
		alert("parts:"+parts);
		
		
		//document.getElementsByName("frmImg")[0].submitAjax();
		
		//return;
		
		user_response.type = parts[0];

		if (HELPER.strpos(fname, '.png') !== false)
			user_response.type = 'PNG';
		else if (HELPER.strpos(fname, '.jpg') !== false)
			user_response.type = 'JPG';
		else if (HELPER.strpos(fname, '.json') !== false)
			user_response.type = 'JSON';
		else if (HELPER.strpos(fname, '.bmp') !== false)
			user_response.type = 'BMP';
		else if (HELPER.strpos(fname, '.webp') !== false)
			user_response.type = 'WEBP';

		//handle transparency
		if (GUI.TRANSPARENCY == false || user_response.type == 'JPG') {
			alert("inside if statement of save function");
			tempCtx.beginPath();
			tempCtx.rect(0, 0, WIDTH, HEIGHT);
			tempCtx.fillStyle = "#ffffff";
			tempCtx.fill();
		}

		//take data
		for(var i = LAYER.layers.length-1; i >=0; i--){
			if (LAYER.layers[i].visible == false)
				continue;
			if (user_response.layers == 'Selected' && user_response.type != 'JSON' && i != LAYER.layer_active)
				continue;
			   tempCtx.drawImage(document.getElementById(LAYER.layers[i].name), 0, 0, WIDTH, HEIGHT);
		}

		if (user_response.trim == 'Yes' && user_response.type != 'JSON') {
			//trim
			var trim_info = IMAGE.trim_info(tempCanvas);
			tmp_data = tempCtx.getImageData(0, 0, WIDTH, HEIGHT);
			tempCtx.clearRect(0, 0, WIDTH, HEIGHT);
			tempCanvas.width = WIDTH - trim_info.right - trim_info.left;
			tempCanvas.height = HEIGHT - trim_info.bottom - trim_info.top;
			tempCtx.putImageData(tmp_data, -trim_info.left, -trim_info.top);
		}

		if (user_response.type == 'PNG') {
			//png - default format
			if (HELPER.strpos(fname, '.png') == false)
				fname = fname + ".png";
			
			tempCanvas.toBlob(function(blob) {
				
				alert("inside tempCanvas function" +blob+"  "+fname);	
				//for(var v in tempCanvas)
					//alert(tempCanvas[v])
				
				var Pic = tempCanvas.toDataURL("image/png");
				alert(pic);
				$.ajax({
			           type: "POST",
			           url:  createFHashAjaxQuery("/HISClinical/servlets/ImageEditorServlet"),
			           data: $("#frmImg").serialize(), // serializes the form's elements.
			           success: function(data)
			           {
			               alert(data); // show response from the php script.
			           },
			           error: function(text) {
		                   alert(text);
		               }
			         });
				// Method-2
				
				/*var Pic = tempCanvas.toDataURL("image/png");
				var Vasu = "Vasu";
				var kum = $('[name="Kumar"]')[0].value;
				alert(kum);
			    Pic = Pic.replace(/^data:image\/(png|jpg);base64,/, "")
			    //document.getElementsByName("Kumar")[0].value=Pic;
			    alert(Pic);
			    
			    alert(document.getElementsByName("Kumar")[0].value);
			    
			    document.getElementsByName("frmImg")[0].submit();
			    */
			    
				 
			    //Method-3
			    
				/*var xhr = new XMLHttpRequest();
				xhr.open("post", "/HISClinical/servlets/ImageEditorServlet", false);
				var boundary = Math.random().toString().substr(2);
				alert(tempCanvas.toDataURL("image/png"));
				xhr.setRequestHeader("content-type", "multipart/form-data; charset=utf-8; boundary=" + boundary);
				var multipart = "--" + boundary +"\n"+"Content-Disposition: form-data; name=Mukund\r\n"+"Content-type: image/png\r\n\r\n"+ tempCanvas.toDataURL("image/png")+"\r\n"+"--" +boundary+"--\r\n";
				xhr.send(multipart);*/
				
				//POP.add({name: "url", title: "URL:", value: "/HIS/hisglobal/images"});
				//POP.show('Open URL',[FILE, 'saveAs(blob, fname)']);
				//POP.saveAS(blob, fname);
				//saveAs(blob, fname);
			    
	             // Method-4                  
			    /*var imageDataURL = tempCanvas.toDataURL('image/png');
			    //imageDataURL = imageDataURL.replace("image/png", "image/octet-stream");
			    //imageDataURL = imageDataURL.replace(/^data:image\/(png|jpeg);base64,/,"");
                  alert(imageDataURL);
                  
			    
			    */
			});
		}
		else if (user_response.type == 'JPG') {
			//jpg
			if (HELPER.strpos(fname, '.jpg') == false)
				fname = fname + ".jpg";
			
			var quality = parseInt(user_response.quality);
			if (quality > 100 || quality < 1 || isNaN(quality) == true)
				quality = 90;
			quality = quality / 100;
			
			tempCanvas.toBlob(function (blob) {
				saveAs(blob, fname);
			}, "image/jpeg", quality);
		}
		else if (user_response.type == 'WEBP') {
			//WEBP - new format for chrome only
			if (HELPER.strpos(fname, '.webp') == false)
				fname = fname + ".webp";
			var data_header = "image/webp";
			
			//check support
			if(this.check_format_support(tempCanvas, data_header) == false)
				return false;		
			
			tempCanvas.toBlob(function (blob) {
				saveAs(blob, fname);
			}, data_header);
		}
		else if (user_response.type == 'BMP') {
			//bmp
			if (HELPER.strpos(fname, '.bmp') == false)
				fname = fname + ".bmp";
			var data_header = "image/bmp";
			
			//check support
			if(this.check_format_support(tempCanvas, data_header) == false)
				return false;
			
			tempCanvas.toBlob(function (blob) {
				saveAs(blob, fname);
			}, data_header);
		}
		else if (user_response.type == 'JSON') {
			//json - full data with layers
			if (HELPER.strpos(fname, '.json') == false)
				fname = fname + ".json";
			
			var data_json = this.export_as_json();
			
			var blob = new Blob([data_json], {type: "text/plain"});
			//var data = window.URL.createObjectURL(blob); //html5
			saveAs(blob, fname);
		}
	};
	
	this.check_format_support = function(canvas, data_header){
		var data = canvas.toDataURL(data_header);
		var actualType = data.replace(/^data:([^;]*).*/, '$1');
		
		if (data_header != actualType && data_header != "text/plain") {
			//error - no support
			POP.add({title: "Error:", value: 'Your browser does not support this format.'});
			POP.show('Sorry', '');
			delete data;
			return false;
		}
		delete data;
		return true;
	};
	
	this.save_cleanup = function (a) {
		a.textContent = 'Downloaded';
		setTimeout(function () {
			a.href = '';
			var element = document.getElementById("save_data");
			element.parentNode.removeChild(element);
		}, 1500);
	};
	
	this.save_file_info = function (object) {
		this.file_info = {
			general: [],
			exif: [],
		};
		//exif data
		EXIF.getData(object, function() {
			FILE.file_info.exif = this.exifdata;
		});
		
		//general
		if(object.name != undefined)
			FILE.file_info.general.Name = object.name;
		if(object.size != undefined)
			FILE.file_info.general.Size = HELPER.number_format(object.size/1000, 2)+' KB';
		if(object.type != undefined)
			FILE.file_info.general.Type = object.type;
		if(object.lastModified != undefined)
			FILE.file_info.general['Last modified'] = '___'+new Date(object.lastModified);
	};
	
	this.export_as_json = function(){
		var export_data = {};
		
		//get date
		var today = new Date();
		var yyyy = today.getFullYear();
		var mm = today.getMonth()+1; //January is 0!
		var dd = today.getDate();
		if(dd < 10)
			dd = '0'+dd;
		if(mm < 10)
			mm = '0'+mm;
		var today = yyyy+'-'+mm+'-'+dd;

		//basic info
		export_data.info = {
			width: WIDTH,
			height: HEIGHT,
			about: 'Image data with multi-layers. Can be opened using miniPaint, https://github.com/viliusle/miniPaint',
			date: today,
		};

		//layers
		export_data.layers = [];
		for(var i = LAYER.layers.length-1; i >=0; i--){
			var layer = {
				name:LAYER.layers[i].name,
				title:LAYER.layers[i].title, 
				visible: 1,
				opacity: LAYER.layers[i].opacity,
			};
			if (LAYER.layers[i].visible == false)
				layer.visible = 0;
			export_data.layers.push(layer);
		}

		//image data
		export_data.image_data = [];
		for(var i = LAYER.layers.length-1; i >=0; i--){
			var data_tmp = document.getElementById(LAYER.layers[i].name).toDataURL("image/png");
			export_data.image_data.push({name: LAYER.layers[i].name, data: data_tmp});
		}

		var data_json = JSON.stringify(export_data, null, 6);
		delete export_data;

		return data_json;	
	};
	
	this.load_json = function (data) {
		var json = JSON.parse(data);

		//init new file
		GUI.ZOOM = 100;
		MAIN.init();
		
		LAYER.remove_all_layers();

		//set attributes
		WIDTH = parseInt(json.info.width);
		HEIGHT = parseInt(json.info.height);
		LAYER.set_canvas_size();

		//add layers
		for(var i in json.layers){
			var layer = json.layers[i];
			var name = layer.name.replace(/[^0-9a-zA-Z-_\. ]/g, "");
			var title = layer.title;
			var visible = parseInt(layer.visible);
			var opacity = parseInt(layer.opacity);

			LAYER.layer_add(name);
			//update attributes
			LAYER.layers[LAYER.layer_active].title = title;
			if (visible == 0)
				LAYER.layer_visibility(LAYER.layer_active);
			LAYER.layers[LAYER.layer_active].opacity = opacity;
		}
		LAYER.layer_renew();
	
		for(var i in json.image_data){
			var layer = json.image_data[i];
			var name = layer.name.replace(/[^0-9a-zA-Z-_\. ]/g, "");
			var data = layer.data;

			var img = new Image();
			img.onload = (function(name, value){
				return function(){
					document.getElementById(name).getContext('2d').drawImage(value, 0, 0);

					LAYER.layer_renew();
					GUI.zoom();
				};
			})(name, img);
			img.src = data;
		}
	};
	
	this.file_quicksave = function(){
		//save image data
		var data_json = this.export_as_json();
		if(data_json.length > 5000000){
			POP.add({html: 'Sorry, image is too big, max 5 MB.'});
			POP.show('Error', '');
			return false;
		}
		localStorage.setItem('quicksave_data', data_json);
		
		//save settings
		settings = {
			color: COLOR,
			active_tool: DRAW.active_tool,
			zoom: GUI.ZOOM,
		};
		settings = JSON.stringify(settings);
		localStorage.setItem('quicksave_settings', settings);
	};
	
	this.file_quickload = function(){
		//load image data
		var json = localStorage.getItem('quicksave_data');
		if(json == '' || json == null){
			//nothing was found
			return false;
		}
		this.load_json(json);
		GUI.zoom_auto(true);
		
		//load settings
		var settings = localStorage.getItem('quicksave_settings');
		if(settings == '' || settings == null){
			//nothing was found
			return false;
		}
		settings = JSON.parse(settings);
		
		//load color
		COLOR = settings.color;
		GUI.sync_colors();
		
		//load active tool
		GUI.action(settings.active_tool);
		
		//load zoom
		GUI.zoom(settings.zoom, false);
	};

}


function base64ToBlob(base64, mime) 
{
    mime = mime || '';
    var sliceSize = 1024;
    var byteChars = window.atob(base64);
    var byteArrays = [];

    for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
        var slice = byteChars.slice(offset, offset + sliceSize);

        var byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        var byteArray = new Uint8Array(byteNumbers);

        byteArrays.push(byteArray);
    }
return byteArrays;
    //return new Blob(byteArrays, {type: mime});
}