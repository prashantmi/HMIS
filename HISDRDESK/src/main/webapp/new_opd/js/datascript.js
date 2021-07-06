 

var tmpAutoId="";var tmpAuto="";var availableTags="";var el_autoc_data="";


function alS(strError)
{
	swal("", strError, "success");
	$('.modal-backdrop').remove();
}
function alW(strError)
{
	swal("", strError, "warnRing");
	$('.modal-backdrop').remove();
}
function alert(strError)
{
	swal("", strError, "");
	$('.modal-backdrop').remove();
}


function confirmBS(msg,obj,values,postMsg,type)
{
	swal({
		  text: msg,
		  icon: type,
		  buttons: true,
		  dangerMode: true,
		})
		.then((val) => {
		  if (val) {
			  if (typeof window.confirmTrue === 'undefined') {
				  console.log('********confirmFalse not defined********');
		    	  console.log('see confirmBS in --ValidationBootstrap--,true and false will not be returned from here since by default function is called anonymously');
			  }else
				  confirmTrue(obj,values,postMsg);
		  } else {
			  if (typeof window.confirmFalse === 'undefined') {
			    	  console.log('********confirmFalse not defined********');
			    	  console.log('if you want to do something on cancel of confirmBox then define confirmFalse in your js file');
			  }else
				  confirmFalse();
		  }
		});
}


var placeHold=`	<label class="placeHold" style="color:grey;margin-left:35%;margin-top:15%;">Drop Here</label>`;

 var el_prevMod=`<div class="modal fade" id="previewModal"  >
 
	<div class="modal-dialog modal-xl" style="max-width:95vw">
		<div class="modal-content" >
			<div class="modal-header">
				<h5 class="header-cont">Template</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	 				<span aria-hidden="true">&times;</span>
	 			</button>
			</div>
			<div class="modal-body"  style="min-height:15rem;">
				<div id="prevCont"  class="container-fluid"></div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-success btn-sl float-right" id="prescSaveBtnId"  >save</button>
			</div>
		</div>
	</div>
 
 </div>`;

 var subContDivHeader=`
		<div  id="subContDivHeader" style="margin-bottom:0.3rem;display:none" class="row">

			
				<button type="button" id="addRow" style="margin-right:20%" class="btn btn-info btn-sm">Row <i class="fas fa-plus"></i></button>
				
				<button type="button" id="delRow"   class="btn btn-danger btn-sm">Row  <i class="far fa-trash-alt"></i> </button>
			
		</div>`;
 
 
//63.8vh
 
 const elVarList = [
	 	{ k: 'Clear Cell', v: '<i class="fas fa-pencil-alt" style="margin-right:26px;"></i>' },
	 	{ k: 'Button', v: '<button type="button" class="btn btn-primary" style="margin-right:7px;">' },
	 	{ k: 'Label', v: '<i class="fas fa-paragraph" style="margin-right:26px;"></i>' },
	    { k: 'Header', v: '<i class="fas fa-heading" style="margin-right:26px;"></i>' },
	    { k: 'Paragraph', v: '<i class="fas fa-paragraph" style="margin-right:26px;"></i>' },
	    { k: 'Select', v: '<i class="fas fa-bars" style="margin-right:26px;"></i>' },
	    { k: 'Unit Based', v: '<i class="fas fa-balance-scale-right" style="margin-right:26px;"></i>' },
	    { k: 'Dependent Field', v: '<i class="fas fa-bars" style="margin-right:26px;"></i>' },
	    { k: 'Autocomplete', v: '<i class="fas fa-paragraph" style="margin-right:26px;"></i>' },
	    { k: 'Predictive List', v: '<i class="fas fa-paragraph" style="margin-right:26px;"></i>' },
	    { k: 'Radio Group', v: '<i class="fas fa-dot-circle" style="margin-right:26px;"></i>' },
	    { k: 'Check Box Group', v: '<span style="margin-left:41px;">Check Box Group</span>' },
	    { k: 'Text Field', v: '<i class="far fa-comment-alt" style="margin-right:26px;"></i>' },
	    { k: 'Text Area', v: '<i class="far fa-keyboard" style="margin-right:26px;"></i>' },
	    { k: 'Number', v: '<i class="fas fa-sort-numeric-down" style="margin-right:26px;"></i>' },
	    { k: 'Date Field', v: '<i class="fas fa-calendar-alt" style="margin-right:26px;"></i>' },
	    { k: 'Img Upload', v: '<i class="fas fa-file-upload" style="margin-right:26px;"></i>' },
	    { k: 'Hidden Input', v: '<i class="fas fa-eye-slash" style="margin-right:26px;"></i>' }	
	    
	];
 const aut=Array(elVarList.length).join(0).split(0).map((item, i) => `
    <li class="list-group-item draggable">
    ${elVarList[i].k=="Button"?'<button type="button" class="btn btn-primary" style="margin-right:7px;"></button>':
    elVarList[i].k=="Check Box Group"?'<span class="chkBoxLabel"><span style="margin-left:41px;">Check Box Group</span>':
    elVarList[i].v}
    <span>${elVarList[i].k=="Check Box Group"?'':elVarList[i].k}</span></li>
  `).join('');
 
 var subContDivRight=`<div id="subContDivRight" class="col-sm-2">${subContDivHeader} <div class="row">
	  <ul class="list-group container-fluid" style="height:63.6vh;overflow-y:auto;font-size:0.8rem;padding-right:0">
		${aut}
	  </ul>	  
	  </div>
	 </div>
`; 



var rmColInLabel=`<label style="float:right;padding:0" onclick="rmColIn(this)" class="rmEl"><i class="fas fa-times"></i></label>
						<div class="modal  contDragModal rmEL">
							<div class="modal-dialog">
								<div class="modal-content right">
									<div class="modal-header">
									</div>
									<div class="modal-body el-dtl"></div>  <!-- elLite goes here -->
								</div>
							</div>
						</div>
						<div class="el-info"><input type="hidden" value="" class="paraMap" ></div>
				  `;

var el_columns=`
					<div   ondblclick="openContDragModal(this)"  class="contDrag col tmpB" >
						${placeHold}	${rmColInLabel}
					</div>
					
`;

var subContDivLeft=`<div id="subContDivLeft" class="col-sm-10 tmpA">
	
		<div id="cont-row-0"  class="row" style="min-height:4rem;display:none;margin:0;">
			
			
			<div class="col-sm-11 actualRow tmpC" >
				<div   class="row contDragCont"  style="min-height:4rem;">
					<div   ondblclick="openContDragModal(this)" id="row-0-col-1" class="contDrag col tmpB" >
					${placeHold}
					${rmColInLabel}
								
						
					</div>
					<div  ondblclick="openContDragModal(this)" id="row-0-col-2" class="contDrag col tmpB" >
							
							${placeHold}
							${rmColInLabel}
						
					</div>
					<div  ondblclick="openContDragModal(this)" id="row-0-col-3" class="contDrag left col tmpB" >
							
							${placeHold}
							${rmColInLabel}	
					</div>
					
					<div  ondblclick="openContDragModal(this)" id="row-0-col-4" class="contDrag col tmpB" >
							
							${placeHold}
							${rmColInLabel}
					</div>
					
				</div>
				
				
			
			</div>
			
			<div class="rmEl col-sm-1 addCell">
					
					
							 <div onclick="addElCol(this);" data-toggle="tooltip" class="btn" data-placement="top" title="Add Cell">
								 
								  <i   style="color:grey" class="fas fa-plus fa-2x"></i> 
								  
							</div>
							
					
			</div>
			
		</div>
	
	
</div>`
;



/*var subContDivHeader=`<div id="subContDivHeader" align="left" class="container-fluid">
	<div class="row">
	<div class="col-md-6">
			<button type="button" id="preview" data-toggle="tooltip" data-placement="top" title="preview"  class="btn btn-primary">
						preview &nbsp;<i class="fas fa-camera-retro"></i>
			</button>
			
			<button type="button" id="clear" data-toggle="tooltip" data-placement="top" title="clear" class="btn btn-secondary">
					<i class="far fa-trash-alt"></i>
			</button>
			
				<button type="button" id="TempColor" data-toggle="tooltip" data-placement="top" title="template color" class="btn btn-danger">
						<i class="fas fa-palette"></i>
				</button>
			
			<div  style="display:none" id="TempColorDiv">
					<button type="button" id="col1" class="btn" style="background:#FFF;border:1px solid black;"></button>
				
					<button type="button" id="col2" class="btn" style="background:#e2f6a2"></button>
					
					<button type="button" id="col3" class="btn" style="background:#c7e8f3"></button>
					
					<button type="button" id="col4" class="btn" style="background:#b6e817"></button>
					
			
			</div>
	</div>
	<div class="col-md-3" style="text-align:right;padding:0">
		<button type="button" id="addRow" class="btn btn-info">Add Rows</button>
		
		<button type="button" id="delRow" class="btn btn-danger">Delete Rows</button>
	</div>
	
	<div class="col-md-3">
		
	</div>
	
 </div>
</div>`;

*/


var el_LabelName=`<div class="el_Label" >
											<label>Label</label>
												<input type="text" name="el-Label" value="" onkeyup=onLabelNameChange(this); class="form-control">
										</div>` ;

var el_Id=`<div class="el_Id" >
											<label>Row ID</label>
											<div class="input-group">

													<input type="text" id="currRowId" name="el_Id" value="" class="form-control">
													<div class="input-group-append">
														<button type="button" class="input-group-btn btn btn-primary" onclick="cpy(this);">
														<i class="far fa-copy"></i>
														</button>
													</div>
												</div>
										</div>` ;
										
										
var el_required=`<div class="el_Req" >
											<label>Required <font color='red'>*</font></label>
												<input type="checkbox" name="el-Req" value="0" onclick=onReqClick(this); >
										</div>` ;

var el_inLabel=`<div class="el_inLabel" >
	<label>Wrap Label</label>
		<input type="checkbox" name="el_inLabel" value="0" onclick=onInLabelClick(this); >
</div>` ;

var el_depShowHide=`<div class="el_depShowHide" >
						<label>Show Hide Function</label>
						
						
						
						<div class="input-group">

						      <input type="text" value="" class="form-control el_depShow" placeholder="To Show">
						      <input type="text" value="" class="form-control el_depHide" placeholder="To Hide">
						      <div class="input-group-append">
						        <span class="input-group-text" onclick="depShowHide(this);">Apply</span>
						      </div>
						</div>
    
						
					</div>` ;


var el_Others=`<div class="el_Others" >
	<label>Others Option 
		<input type="checkbox" name="el_Others" value="0" onclick=onOthersClick(this); >
</div>` ;

var el_inline=`<div class="el_Req" >
	<label>Inline 
		<input type="checkbox" name="el-inline" value="0" onclick=onInlineClick(this); >
</div>` ;

var el_Revert=`<div class="el_Revert" >
	<label>Revert Option Funtionality</label>
		<input type="checkbox" name="el-Revert" value="0" onclick=onRevertClick(this); >
</div>` ;

var el_bold=`<div class="el_bold" ><br>
											
						<label class="card" onclick=onBoldClick(this); style="min-width:2rem;text-align:center;background:#CCC;float:left"><b>B</b>
											
							<input style="display:none" id="bChk" type="hidden"  value="0"  >
						</label>
						<label class="card" onclick=onItalicClick(this); style="min-width:2rem;text-align:center;background:#CCC;float:left"><i>i</i>
							<input style="display:none" id="iChk" type="hidden"  value="0"  >
						</label>
						<label class="card" onclick=onUnderClick(this); style="max-width:2rem;text-align:center;background:#CCC;"><u>U</u>
							<input style="display:none" id="uChk" type="hidden"  value="0" >
						</label>
												
	</div>` ;
var el_sizes=`<div class="el_sizes btn-group" ><br>
	
	<button class="btn btn-secondary" type="button" onclick=onSmClick(this);>small
						
		<input style="display:none" id="smChk" type="hidden"  value="0"  >
	</button>
	<button class="btn btn-secondary" type="button" onclick=onMdClick(this); ><b>Medium</b>
		<input style="display:none" id="mdChk" type="hidden"  value="0"  >
	</button>
	<button class="btn btn-secondary" type="button" onclick=onLgClick(this); ><h5><b>Large</b></h5>
		<input style="display:none" id="lgChk" type="hidden"  value="0" >
	</button>
							
</div>` ;
var el_align=`<div class="el_align btn-group" style="margin-bottom:1rem;"><br>
	
	<button class="btn btn-secondary" type="button" onclick=onLeftClick(this);>
			<i class="fas fa-align-left"></i>			
		<input style="display:none" id="smChk" type="hidden"  value="0"  >
	</button>
	<button class="btn btn-secondary" type="button" onclick=onCenterClick(this); >
			<i class="fas fa-align-center"></i>
		<input style="display:none" id="mdChk" type="hidden"  value="0"  >
	</button>
	<button class="btn btn-secondary" type="button" onclick=onRightClick(this); >
		<i class="fas fa-align-right"></i>
		<input style="display:none" id="lgChk" type="hidden"  value="0" >
	</button>
							
</div>` ;
										
var el_Value=`<div class="el_Label" >
<label>value</label>
	<input type="text" name="el-Label" value="" onblur=onValueChange(this); class="form-control">
</div>` ;


var el_TextArea=`<div class="el_Label" >
<label>Containt</label>
	<textarea type="text" name="el-Label" onblur=onTextAreaChange(this); class="form-control"></textarea>
</div>` ;



var el_Label=`<div class="el_Label" >
											<label>Label</label>
												<input type="text" value="" name="el-Label" onblur=onLabelChange(this); class="form-control">
										</div>` ;
var el_LabelOfLabel=`<div class="el_LabelOfLabel" >
	<label>Label</label>
		<input type="text" value="" name="el-el_LabelOfLabel" onblur=onLabelOfLabelChange(this); class="form-control">
</div>` ;

var el_Min=`<div class="el_Min" >
	<label>Min</label>
		<input type="number" name="el_Min" onChange=onMinChange(this); placeholder="0" value="0" class="form-control">
</div>` ;

var el_Max=`<div class="el_Max" >
<label>Max</label>
<input type="number" name="el_Max" onChange=onMaxChange(this); placeholder="0"  value="0" class="form-control">
</div>` ;

var el_MinUnit=`<div class="el_MinUnit" >
	<label>Min</label>
		<input type="number" name="el_MinUnit" onChange=onMinChangeUnit(this); placeholder="0" value="0" class="form-control">
</div>` ;

var el_MaxUnit=`<div class="el_MaxUnit" >
<label>Max</label>
<input type="number" name="el_MaxUnit" onChange=onMaxChangeUnit(this); placeholder="0"  value="0" class="form-control">
</div>` ;


var el_MaxTextArea=`<div class="el_Max" >
	<label>Word Count</label>
	<input type="number" name="el_Max" onChange=onMaxTextAreaChange(this); placeholder="0" class="form-control">
	</div>` ;

var el_Step=`<div class="el_Step" >
	<label>Step</label>
		<input type="number" name="el_Step" onChange=onStepChange(this); placeholder="0" value="0" class="form-control">
</div>` ;


var el_PrintFlag=`<div class="PrintFlag" style="margin-top:0.8rem" >
	<label>Print Flag</label>
		<input type="checkbox" value="1" checked  name="el_PrintFlag">
</div>` ;
										
var el_LabelGroup=`<div class="el_Label" >
											<label>Label</label>
												<input type="text" name="el-Label" value="" onkeyup=onLabelGoupChange(this); class="form-control">
										</div>` ;										
										

var el_Style=`							<div style="margin-bottom:0.8rem;"><label>Style</label><div class="el_Style" >
											
												<div class="btn-group" name="el_Style"  role="group">
													<button value="info" type="button" onclick="onStyleChange(this,'0')" class="btn-xs btn btn-info btn-sm">Info</button>
													<button value="danger" type="button" onclick="onStyleChange(this,'1')" class="btn-xs btn btn-danger btn-sm">Danger</button>				
													<button value="default" type="button" onclick="onStyleChange(this,'2')" class="btn-xs btn btn-default btn-sm">Default</button>
													<button value="primary" type="button" onclick="onStyleChange(this,'3')" class="btn-xs btn btn-primary btn-sm">Primary</button>
													<button value="success" type="button" onclick="onStyleChange(this,'4')" class="btn-xs btn btn-success btn-sm">Success</button>
													<button value="warning" type="button" onclick="onStyleChange(this,'5')" class="btn-xs btn btn-warning btn-sm">Warning</button>
												</div>
										</div></div>
		`;


var el_Color=`
										<div style="margin-bottom:0.8rem;"><label>Color</label><div class="el_Color">
											
												<div class="btn-group" name="el_Color"  role="group">
													<button value="Black" type="button" onclick="onColorChange(this,'0')" class="btn-xs btn btn-secondary">Black</button>
													<button value="White" type="button" onclick="onColorChange(this,'1')" class="btn-xs btn btn-default">White</button>
													<button value="Red" type="button" onclick="onColorChange(this,'2')" class="btn-xs btn btn-danger">Red</button>
													<button value="Cyan" type="button" onclick="onColorChange(this,'3')" class="btn-xs btn btn-info">Cyan</button>
													<button value="Blue" type="button" onclick="onColorChange(this,'4')" class="btn-xs btn btn-primary">Blue</button>
													<button value="Green" type="button" onclick="onColorChange(this,'5')" class="btn-xs btn btn-success">Green</button>
													<button value="Orange" type="button" onclick="onColorChange(this,'6')" class="btn-xs btn btn-warning">Orange</button>
												</div>
										</div></div>

`;		


var el_Type_Button=`
										<div class="el_Type" >	
											<label>Type</label>
												<select  name="el-Type" onchange="onTypeButtonChange(this);" class="form-control">
														<option label="Button" value="button">Button</option>
														<option label="submit" value="submit">submit</option>
														<option label="reset" value="reset">reset</option>
												</select>
										</div>
			`;

var el_Type_TextArea=`
	<div class="el_Type" >	
		<label>Type</label>
			<select  name="el_Type_TextArea" onchange="onTypeTextAreaChange(this);" class="form-control">
					<option label="General" value="0">General</option>
					<option label="With Editor" value="1">With Editor</option>					
			</select>
	</div>
`;
var el_Type_Header=`
										<div class="el_Type" >	
											<label>Type</label>
												<select  name="el-Type" onchange="onTypeHeaderChange(this);" class="form-control">
														<option label="h1" value="h1">h1</option>
														<option label="h2" value="h2">h2</option>
														<option label="h3" value="h3">h3</option>
														<option label="h4" value="h4">h4</option>
														<option label="h5" value="h5">h5</option>
														<option label="h6" value="h6">h6</option>
												</select>
										</div>
			`;
var el_Type_Label=`
	<div class="el_Type" >	
		<label>size</label>
			<select  name="el-Type" onchange="onTypeLabelChange(this);" class="form-control">
					<option label="size 1" value="h1">size 1</option>
					<option label="size 2" value="h2">size 2</option>
					<option label="size 3" value="h3">size 3</option>
					<option label="size 4"  value="h4">size 4</option>
					<option label="size 5" value="h5">size 5</option>
					<option label="size 6" selected value="h6">size 6</option>
			</select>
	</div>
`;
			
			
var el_Name=
`
										<div class="el_Name" >
											<label>Parameter Mapping</label>
												<input type="text" value="" name="el_Name" onkeyup="onNameChange(this)" class="form-control">
										</div>
`; 


var el_MultiSelect=
	`
											<div class="el_MultiSelect" >
												<label>multiple selection</label>
													<input type="checkbox" name="el_MultiSelect" value="0" onclick="onMultiSelectChange(this)" >
											</div>
	`; 
var el_PlaceHolder=
	`
											<div class="el_PlaceHolder" >
												<label>Placeholder</label>
													<input type="text" value="" name="el_PlaceHolder" onblur="onPlaceholdChange(this)" class="form-control">
											</div>
	`; 

var el_helpText=
	`
											<div class="el_helpText" >
												<label>Help text</label>
													<input type="text" value="" name="el_helpText" onblur="onhelpTextChange(this)" class="form-control">
											</div>
	`;

var el_NameRadio=
`
										<div class="el_Name" >
											<label>Parameter Mapping</label>
												<input type="text" name="el_Name" value="" onkeyup="onNameChangeRadio(this)" class="form-control">
										</div>
`; 
			
var el_Position=`

										<div class="el_Position" >
											<label>Position</label>
											<button type="button" onclick="seekMinus(this)" style="border-radius:28px;" class="btn btn-default"><i class="fas fa-minus"></i></button>
												<button type="button" onclick="seekPlus(this)" style="border-radius:28px;" class="btn btn-default"><i class="fas fa-plus"></i></button>
										</div>
`;

var el_dataType=`						<div class="el_Type" >	
											<label>Type</label>
												<select  name="el-Type" onchange="onDataTypeChange(this);" class="form-control">
														<option label="Select Value" value="0">Select Value</option>
														<option label="data1" value="data1">data1</option>
														<option label="data2" value="data2">data2</option>
														
												</select>
										</div>
`;

var el_optionsIn=`								<div class="col-sm-1" style="padding-right:1px">
													<input type="radio" onclick="defaultCheckRad(this)" value="" name="defaultCheck" class="form-control">
												</div>												
												<div class="col-sm-6" style="padding:0">
													<input onkeyup="addOptName(this)" placeholder="option" value="" class="form-control"  name="option" type="text" value="">
												</div>
												<div class="col-sm-3" style="padding:0">
													<input onkeyup="addOptValue(this)"  placeholder="value" value="" class="form-control"  name="optvalue" type="text" value="">
												</div>
												<div class="col-sm-1" style="">
													<button type="button"  onclick="addOpt(this)" style="border-radius:28px;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"  onClick="rmOptDiv(this)"  style="border-radius:28px;display:none;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
												</div>
											
`;

var el_options_predict=`																			
<div class="col-sm-7" style="padding-right:1px">
	<input onblur="addOptNamePredict(this)" placeholder="option" class="form-control"  name="option" type="text" value="">
</div>
<div class="col-sm-3" style="padding:0">
	<input onblur="addOptvalPredict(this)" placeholder="value" class="form-control"  name="option" type="text" value="">
</div>

<div class="col-sm-1">
	<button type="button"  onclick="addOptPredict(this)" style="border-radius:28px;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
	<button type="button"  onClick="rmOptDiv(this)"  style="border-radius:28px;display:none;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
</div>

`;

var el_DeptOptionsIn=`
										
												<div class="col-sm-9">
													<input onblur="addDepId(this)" style="background:lightyellow" placeholder="Enter RowID to make dependent" class="form-control"  name="option" type="text" value="">
												</div>
												
												<div class="col-sm-1" style="display:none">
													<button type="button"   onclick="addOptDept(this,'')" style="border-radius:28px;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"  onclick="rmOptDiv(this)" style="border-radius:28px;display:none;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
												</div>
											
`;
var el_UnitIn=`
	
	<div class="col-sm-9">
		<input onblur="addUnit(this)"  class="form-control"  name="option" type="text" value="">
	</div>
	
	<div class="col-sm-1">
		<button type="button"   onclick="addOptDept(this,'unit')" style="border-radius:28px;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
		<button type="button"  onclick="rmOptDivUnit(this)" style="border-radius:28px;display:none;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
	</div>

`;

var el_optionsInRadio1=`
										
												<div class="col-sm-7" style="padding-right:1px">
													<label>Option</label><input onblur="addOptNameRadio(this)" class="form-control"  name="option" type="text" value="">
												</div>
												<div class="col-sm-3" style="padding:0">
													<label>Value</label><input onblur="addOptValueRadio(this)" class="form-control"  name="value" type="text" value="">
												</div>
												<div class="col-sm-1" style="margin:2rem 0 0 0;">
													<button type="button"   onclick="addOptRadio(this)" style="border-radius:28px;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"   onclick="rmOptDivRadio(this)" style="border-radius:28px;display:none;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
													
												</div>
											
`;
var el_optionsInRadio=`
										
												<div class="col-sm-7" style="padding-right:1px">
													<label>Option</label><input onblur="addOptNameRadio(this)" class="form-control"  name="option" type="text" value="">
												</div>
												<div class="col-sm-3" style="padding:0">
													<label>Value</label><input onblur="addOptValueRadio(this)" class="form-control"  name="value" type="text" value="">
												</div>
												<div class="col-sm-1" style="margin:2rem 0 0 0;">
													<button type="button"    onclick="addOptRadio(this)" style="border-radius:28px;display:none;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"    onclick="rmOptDivRadio(this)" style="border-radius:28px;display:none" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
												</div>
											
`;

el_optionsInRadio2=`
										
												<div class="col-sm-7" style="padding-right:1px">
													<label>Option</label><input onblur="addOptNameRadio(this)" class="form-control"  name="option" type="text" value="">
												</div>
												<div class="col-sm-3" style="padding:0">
													<label>Value</label><input onblur="addOptValueRadio(this)" class="form-control"  name="value" type="text" value="">
												</div>
												<div class="col-sm-1" style="margin:2rem 0 0 0;">
													<button type="button"   onclick="addOptRadio(this)" style="border-radius:28px;display:none;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"   onclick="rmOptDivRadio(this)" style="border-radius:28px;" class="btn btn-default"><i class="fas fa-minus rmOptDiv"></i></button>
												</div>
											
`;

var el_optionsInCheck1=`
										
												<div class="col-sm-7" style="padding-right:1px">
													<label>Option</label><input onblur="addOptNameRadio(this)" class="form-control"  name="option" type="text" value="">
												</div>
												<div class="col-sm-3" style="padding:0">
													<label>Value</label><input onblur="addOptValueRadio(this)" class="form-control"  name="value" type="text" value="">
												</div>
												<div class="col-sm-1" style="margin:2rem 0 0 0;">
													<button type="button"   onclick="addOptRadio(this)" style="border-radius:28px;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"    onclick="rmOptDivRadio(this)" style="border-radius:28px;display:none;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
												</div>
											
`;

var el_optionsInCheck=`
										
												<div class="col-sm-7" style="padding-right:1px">
													<label>Option</label><input onblur="addOptNameRadio(this)" class="form-control"  name="option" type="text" value="">
												</div>
												<div class="col-sm-3" style="padding:0">
													<label>Value</label><input onblur="addOptValueRadio(this)" class="form-control"  name="value" type="text" value="">
												</div>
												<div class="col-sm-1" style="margin:2rem 0 0 0;">
													<button type="button"   onclick="addOptRadio(this)" style="border-radius:28px;display:none;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"    onclick="rmOptDivRadio(this)" style="border-radius:28px;display:none;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
												</div>
											
`;

var el_optionsInCheck2=`
										
												<div class="col-sm-7">
													<label>Option</label><input onblur="addOptNameRadio(this)" class="form-control"  name="option" type="text" value="">
												</div>
												<div class="col-sm-3">
													<label>Value</label><input onblur="addOptValueRadio(this)" class="form-control"  name="value" type="text" value="">
												</div>
												<div class="col-sm-1" style="margin:2rem 0 0 0;">
													<button type="button"   onclick="addOptRadio(this)" style="border-radius:28px;display:none;" class="btn btn-default addOptDiv"><i class="fas fa-plus"></i></button>
													<button type="button"   onclick="rmOptDivRadio(this)" style="border-radius:28px;" class="btn btn-default rmOptDiv"><i class="fas fa-minus"></i></button>
												</div>
											
`;


var el_options=
`
										<div class="el_Options" >
										<label>Options</label>
											<div class="row" id="el_OptionsIn-1" style="margin-bottom:1%">
												${el_optionsIn}
											</div>
										</div>
`; 

var el_DepOptions=
`
										<div class="el_Options" >
										<label>Mapping Row Id</label>
											<div class="row" id="el_OptionsIn-1" style="margin-bottom:1%;">
												${el_DeptOptionsIn}
											</div>
										</div>
`; 

var el_Unit=
	`
											<div class="el_Options" >
											<label>Add Unit</label>
												<div class="row" id="el_OptionsIn-1" style="margin-bottom:1%;">
													${el_UnitIn}
												</div>
											</div>
	`; 

var el_optPredict=
	`
											<div class="el_Options" >
											<label>Add to List</label>
												<div class="row" id="el_OptionsIn-1" style="margin-bottom:1%">
													${el_options_predict}
												</div>
											</div>
	`; 

var el_option_radio=
`
										<div class="el_Options" >
										<label>Options</label>
											<div class="row" id="el_OptionsIn-1">
												${el_optionsInRadio1}
											</div>
											<div class="row" id="el_OptionsIn-2">
												${el_optionsInRadio}
											</div>
											<div class="row" id="el_OptionsIn-3">
												${el_optionsInRadio2}
											</div>
										</div>
`; 

var el_option_check=
`
										<div class="el_Options" >
										<label>Options</label>
											<div class="row" id="el_OptionsIn-1">
												${el_optionsInCheck1}
											</div>
											<div class="row" id="el_OptionsIn-2">
												${el_optionsInCheck}
											</div>
											<div class="row" id="el_OptionsIn-3">
												${el_optionsInCheck2}
											</div>
										</div>
`; 
var Unit_inner=`<input type="number" step="1" value="" class="form-control" aria-label="Text input with dropdown button">
 
 <div class="input-group-append">
    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" value="unit" aria-expanded="false">Select</button>
    
    <div class="dropdown-menu"></div></div>`;

//  ${subContDivHeader}  removed as per requirement given
  const el_cont=` <div class="row">${subContDivLeft} ${subContDivRight} ${el_prevMod} </div>`;
										
  const elLit_Button=` ${el_Label}  ${el_Style} ${el_align} ${el_Type_Button}  ${el_Id}     <hr> ${el_Position} ` ;
  
  const elLit_Header=` ${el_Label}   ${el_Color} ${el_align} ${el_Type_Header}  ${el_PrintFlag} ${el_Id}  <hr> ${el_Position} ` ;
  
  const elLit_Label=` ${el_LabelOfLabel}   ${el_Color} ${el_align} ${el_Type_Label}  ${el_PrintFlag} ${el_Id}  <hr> ${el_Position} ` ;
  
  const elLit_Para=` ${el_Label}    ${el_Color} ${el_align} ${el_bold}   ${el_PrintFlag} ${el_Id}  <hr> ${el_Position} ` ;

  const elLit_Autocomplete=` ${el_LabelName} ${el_required}  ${el_bold}  ${el_align}  ${el_inLabel} ${el_Name} ${el_helpText} ${el_PlaceHolder} ${el_dataType}  ${el_PrintFlag} ${el_Id} <hr> ${el_Position} ` ;
  
  const elLit_Predict=` ${el_LabelName} ${el_bold} ${el_required} ${el_bold} ${el_align} ${el_inLabel}  ${el_Name} ${el_helpText} ${el_optPredict} ${el_PlaceHolder}   ${el_PrintFlag} ${el_Id} <hr> ${el_Position} ` ;

  const elLit_Select=` ${el_LabelName} ${el_bold} ${el_align} ${el_sizes} ${el_required} ${el_inLabel} ${el_MultiSelect} ${el_Name} ${el_helpText}  ${el_options}   ${el_PrintFlag} ${el_Id}  <hr> ${el_Position} ` ;
  
  const elLit_Unit=` ${el_LabelName} ${el_bold} ${el_align} ${el_required} ${el_inLabel} ${el_MultiSelect} ${el_Name} ${el_helpText} ${el_MinUnit} ${el_MaxUnit}  ${el_Unit}   ${el_PrintFlag} ${el_Id}  <hr> ${el_Position} ` ;
  
  const elLit_dept_field=` ${el_LabelName} ${el_bold} ${el_required} ${el_inLabel} ${el_align} ${el_Revert} ${el_depShowHide} ${el_Name} ${el_helpText}  ${el_DepOptions} ${el_Id} ${el_PrintFlag}   <hr> ${el_Position} ` ;
  
  const elLit_Radio=` ${el_LabelGroup}  ${el_bold} ${el_required}  ${el_inline}  ${el_Others}   ${el_NameRadio}  ${el_helpText} ${el_option_radio}  ${el_PrintFlag} ${el_Id}   <hr> ${el_Position} ` ;
  
  const elLit_Check=` ${el_LabelGroup} ${el_bold} ${el_required} ${el_inline} ${el_Name} ${el_helpText}  ${el_option_check}  ${el_PrintFlag} ${el_Id}   <hr> ${el_Position} ` ;
  
  const elLit_Text=` ${el_LabelName}  ${el_bold} ${el_required}  ${el_align} ${el_Name} ${el_MaxTextArea} ${el_helpText} ${el_PlaceHolder}  ${el_PrintFlag} ${el_Id}    <hr> ${el_Position} ` ;
   
  const elLit_TextArea=` ${el_LabelName}  ${el_bold} ${el_required} ${el_align} ${el_inLabel} ${el_Name}  ${el_Type_TextArea} ${el_MaxTextArea} ${el_helpText} ${el_PlaceHolder}  ${el_PrintFlag} ${el_Id}  <hr> ${el_Position} ` ;
  
  const elLit_image=` ${el_LabelName}  ${el_bold}  ${el_Name}  ${el_helpText}  ${el_PrintFlag} ${el_Id}  <hr> ${el_Position} ` ;
  
  const elLit_Number=` ${el_LabelName} ${el_bold} ${el_required} ${el_align} ${el_inLabel} ${el_helpText}  ${el_Name} ${el_Min} ${el_Max} ${el_Step}   ${el_PrintFlag} ${el_PlaceHolder} ${el_Id}   <hr> ${el_Position} ` ;
  
  const elLit_Date=` ${el_LabelName} ${el_bold} ${el_required} ${el_align}  ${el_inLabel} ${el_helpText}  ${el_Name}    ${el_PrintFlag} ${el_PlaceHolder} ${el_Id}   <hr> ${el_Position} ` ;

  const elLit_Hidden=` ${el_Name} ${el_PrintFlag}  ${el_Id}  <hr> ${el_Position} ` ;




										
										
								
										
 

  
  const elVars={		"Button":{
						"tagName":"button",
						"label":"Button",
						"inner":"button",
						"className":"btn",
						"colorClass":["btn-info","btn-danger","btn-default","btn-primary","btn-success","btn-warning","btn-link"] ,	
						"modal":elLit_Button,
						"type":["button","submit","reset"],
						"tagType":"",
						"attrb":{
								
								}
					},
					"Paragraph":{
						"tagName":"p",
						"label":"Paragraph",
						"inner":"your text",
						"className":"",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Para,
						"tagType":"",
						"type":[""],
						"attrb":{
										"contenteditable":true
								}
					},
					"Header":{
						"tagName":"h1",
						"label":"Header",
						"inner":"Header",
						"className":"",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Header,
						"type":[""],
						"tagType":["h1","h2","h3","h4","h5","h6"],
						"attrb":{
									"contenteditable":true
								}
					},
					"Label":{
						"tagName":"h6",
						"label":"Label",
						"inner":"Label",
						"className":"mainLabel",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Label,
						"type":[""],
						"tagType":["h1","h2","h3","h4","h5","h6"],
						"attrb":{
									
								}
					},
					"Select":{
						"tagName":"select",
						"label":"Select",
						"inner":"<option value='0'>Select Value</option>",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Select,
						"type":[""],
						"tagType":[""],
						"attrb":{
								}
					},
					"Unit Based":{
						"tagName":"div",
						"label":"Unit Based",
						"inner":Unit_inner,
						"className":"input-group",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Unit,
						"type":[""],
						"tagType":[""],
						"attrb":{
								}
					},
					"Dependent Field":{
						"tagName":"select",
						"label":"DependentField",
						"inner":"<option value='-1'>Select Value</option><option value='Yes'>Yes</option><option value='No'>No</option>",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_dept_field,
						"type":[""],
						"tagType":[""],
						"attrb":{
								}
					},
					"Autocomplete":{
						"tagName":"input",
						"label":"Autocomplete",
						"inner":"",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Autocomplete	,
						"dataType":["Select Value","data1","data2"],
						"tagType":[""],
						"type":["text"],
						"attrb":{
								}
						
					},
					"Predictive List":{
						"tagName":"input",
						"label":"PredictList",
						"inner":"",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Predict	,
						"dataType":["Select Value","data1","data2"],
						"tagType":[""],
						"type":["text"],
						"attrb":{
								}
						
					},
					"Text Field":{
						"tagName":"input",
						"label":"TextField",
						"inner":"",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Text,
						"type":["text"],
						"tagType":["h1","h2","h3","h4","h5","h6"],
						"attrb":{
								}
					},
					"Number":{
						"tagName":"input",
						"label":"Number",
						"inner":"",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Number,
						"type":["number"],
						"tagType":["h1","h2","h3","h4","h5","h6"],
						"attrb":{
								"placeholder":"0"
								}
					},
					"Text Area":{
						"tagName":"textarea",
						"label":"textarea",
						"inner":"",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_TextArea,
						"type":["text"],
						"tagType":[""],
						"attrb":{
								}
					},
					"Radio Group":{
						"tagName":"div",
						"label":"radio",
						"inner":"optradio",
						"className":"radio",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Radio,
						"type":[""],
						"tagType":[""],
						"attrb":{
								}
					},
					"Check Box Group":{
						"tagName":"div",
						"label":"checkbox",
						"inner":"optcheck",
						"className":"checkbox",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Check,
						"type":[""],
						"tagType":[""],
						"attrb":{
								}
					},
					"Date Field":{
						"tagName":"input",
						"label":"date",
						"inner":"",
						"className":"form-control",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Date,
						"type":["date"],
						"tagType":[""],
						"attrb":{
								}
					},
					"Hidden Input":{
						"tagName":"input",
						"label":"hidden",
						"inner":"",
						"className":"",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_Hidden,
						"type":["text"],
						"tagType":[""],
						"attrb":{
								}
					},
					"Img Upload":{
						"tagName":"input",
						"label":"Image",
						"inner":"",
						"className":"actPic",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":elLit_image,
						"type":["file"],
						"tagType":[""],
						"attrb":{
								}
					},
					"Clear Cell":{
						"tagName":"",
						"label":"Drop Here",
						"inner":"Drop Here",
						"className":"",
						"colorClass":["Black","White","Red","Cyan","Blue","Green","Orange"], 
						"modal":"",
						"type":[""],
						"tagType":[""],
						"attrb":{
								}
					},
					
				
  
					
  };
	  
function getControl(key)
{
		
   for (let k in Object.keys(elVars))
		{
			if(key == Object.keys(elVars)[k])
				return Object.values(elVars)[k];	
		}
	return -1;
}

function getActObj(obj){return $(obj).closest(".contDrag").find(".addedEl")}


function getActObjLabelDiv(obj){return $(obj).closest(".contDrag").find(".labelElDiv")}

function getActObjLabel(obj){return $(obj).closest(".contDrag").find(".mainLabel").last();}

function getActObjLabelGroup(obj){return $(getActObj(obj)).find("label").first();}



  
  function openContDragModal(obj){
	  if(  $(obj).find(".modal-body").html().trim() != "" && ($($(obj).children()[0]).attr("class").split("-")[0] !="Dep"))
		$(obj).find(".contDragModal").modal("show"); 
	}
  
  function remDefault(e){ e.preventDefault(); }
  
  function rmEl(obj)
	{
				if(confirm("Delete this Row"))
				{
					$(obj).closest(".rmEl").parent().css("display","none");
					$(obj).closest(".rmEl").parent().addClass("rmEl");
					//$(obj).parent().css("display","none");
					//$(obj).parent().addClass("rmEl");
				}
	}
 
 
  $(()=>{
		 $("#contDiv").html(el_cont); 
		 if($("#capturedModifyHtml").html()!=undefined){
			 let bg=$("#capturedModifyHtml #subContDivLeft").css("background-color");
			 $("#contDiv #subContDivLeft").html($("#capturedModifyHtml #subContDivLeft").html());
			 $("#contDiv #subContDivLeft").css("background-color",bg);
			 callDropable($("#contDiv #subContDivLeft .contDrag"),"1");
			 
		 }
		 $( ".draggable" ).draggable({	
			 helper:'clone',
			 start: function(event, ui) { $(this).css("z-index", 999); }
			 
		 });
});
  
   
  function attachElement(thisObj,dragedObj){
  let elName=dragedObj[0].innerText.trim();
  let id="-"+$(thisObj).prop("id");
  let i=getControl(elName);
	  if(i!=-1){
		  	  
		  if(elName=="Check Box Group" || elName == "Radio Group"){
			   $(thisObj).find(".el-dtl").append(i.modal);	
			   
			  let div=$('<div/>', {
					id: i.label+id,
					"class": 'addedEl addedElGrp',
				}).appendTo(thisObj);
				
				 let label=$('<label/>', {
					 "class": 'mainLabel'
					//for: i.label+id+'-'+1
				}).appendTo(div);
				 
				
				$(label).html(i.label+ "<font style='display:none'  color='red' id='req"+id+"'>*</font>&nbsp;");
				$(label).css({"display":"none","text-align":"right"});
				
			  
			  for(let j=0;j<3;j++){
				  
		  	  el=document.createElement(i.tagName);  
			  $(el).html("<label><input type='"+i.label+"' name="+i.inner+id+" value=''>Option </label>"); 
			  $(el).addClass(i.className);
			  $(el).addClass("addedElGrpIn");
			   
			  $(el).addClass(i.colorClass[0]);
			 	  
			  $(el).attr("type",i.type);
			  $(el).attr("id",i.label+id+"-opt-"+j);
			  $(div).append(el);
			  }
			  
			  let labelhelp=$('<span/>', {
					 "class": 'helptextDiv col-sm-1',
					 "data-toggle":"tooltip",
					 "title":"help test"
				}).appendTo(div);
				
				 $(labelhelp).html('<i class="fas fa-info-circle" style="color:#0070ff"></i> ');
				 $(labelhelp).css({"margin-left":"2%","height":"1.2rem","display":"none"});
			  
			  
		  }else{
			  //addedElDiv
			  let elDiv=$('<div/>', {
					"class": 'row mainElDiv no-gutters',
				}).appendTo(thisObj);

			  

			  if((elName.trim() !="Header") && (elName.trim() !="Paragraph") && (elName.trim() !="Button") && (elName.trim() !="Hidden Input") && (elName.trim() !="Label")){
		   
				  let labelElDiv=$('<div/>', {
						"class": 'col labelElDiv',
					}).appendTo(elDiv);
				   let label=$('<label/>', {
						"for": i.label+id,
						"class":'mainLabel'
					}).appendTo(labelElDiv);
			   
				 $(labelElDiv).css({"display":"none","text-align":"center"});
				 $(label).css({"margin-top":"1.2rem","text-align":"left"});
				 
				// $(labelhelp).tooltip();
				// $(labelhelp).popover(); 
				
				$(label).html(i.label+ "<font style='display:none' color='red' id='req"+id+"'>*</font>");
			  }
			  let addedElDiv=$('<div/>', {
					"class": 'col addedElDiv',
				}).appendTo(elDiv);
			  
			  //+"  "+ Object.values(i.attrb)[0]);
				
			  el=document.createElement(i.tagName);  
			 $(el).html(i.inner); 
			  $(el).addClass(i.className);
			  $(el).addClass(i.colorClass[0]);
			  $(thisObj).find(".el-dtl").append(i.modal);	
			  
			  $(el).attr("type",i.type[0]);
			  $(el).addClass("addedEl");
			  //$(el).addClass("col");
			  
			  for(let k=0;k<Object.keys(i.attrb).length;k++)
			  {
				  $(el).attr( Object.keys(i.attrb)[k],Object.values(i.attrb)[k] );
			  }
			  
			  $(el).attr("id",i.label+id);
			  $(addedElDiv).append(el);
			  
			   let labelhelp=$('<span/>', {
					 "class": 'helptextDiv col-sm-1',
					 "data-toggle":"tooltip",
					 "title":"help test"
				}).appendTo(elDiv);
			   
				 $(labelhelp).html('<i class="fas fa-info-circle" style="color:#0070ff"></i> ');
				 $(labelhelp).css({"margin-top":"5%","height":"1.2rem","display":"none"});
			  
			 
		  }
		  if((elName.trim() =="Predictive List")){
			  let pred=$('<div/>', {
					"class": 'row predictVal',
				}).appendTo(thisObj);
		  }
		  
		  if((elName.trim() =="Autocomplete")){
			  
			  let div=$('<div/>', {
					"class": 'addedElHelper',
				}).appendTo(thisObj);
			 
			  let inpEl=document.createElement("input");  
			  $(inpEl).attr("type","hidden");
			  $(inpEl).attr("class","autoCompHelper");
			  $(inpEl).attr("id","hidden-"+i.label+id);
			  let inpEl1=document.createElement("input");  
			  $(inpEl1).attr("type","hidden");
			  $(inpEl1).attr("class","autoUrl");
			  $(div).append(inpEl);
			  $(div).append(inpEl1);
			  
			 
			  
			  $(thisObj).find("select[name='el-Type']").html(el_autoc_data);
		  }
		  
		    if((elName.trim() =="Img Upload"))
			{
			 
			  let div=$('<div/>', {
					"class": 'ImgUpDiv addedElHelper',
					"id":"ImgUpDiv"+id
				}).appendTo(thisObj);
				
				
				
				$(div).html(`<script>
					$('#ImgUpDiv${id}').on("click",()=>{$("#${i.label+id}").trigger("click")})
					$("#${i.label+id}").on("change",function(event){
						$('#ImgUpDiv${id}').find(".tempLabel").remove();
						var reader = new FileReader();
						reader.onload = function (e) {$('#ImgUpDiv${id} img').attr('src',e.target.result);}
						reader.readAsDataURL(event.target.files[0]);
						});
						console.log($('#ImgUpDiv${id}').css("width").split("px")[0]);
						//console.log($('#ImgUpDiv${id}').resizable());
					 $('#ImgUpDiv${id}').resizable();
				</script>`);
				
				
				 let label=$('<label/>', {
					"for": i.label+id,
					"class":'tempLabel'
				}).appendTo(div);
				
				$(label).html("click to upload");
				
				let img=$('<img/>', {
					"src": '',
					"id":"Img"+id
				}).appendTo(div);
				
				
				
			}
		    
		   
		  
		  
		  
		  
		  if((elName.trim() =="Dependent Field")){
				$(thisObj).addClass("Dep-"+$(thisObj).parent().parent().parent().parent().find("select[id^='DependentField']").length);
 
			 let div=$('<div/>', {
					"class": 'depIdMapDiv addedElHelper'
				}).appendTo(thisObj);
			$(div).html(`<script>
				function depChange(obj){
					
				//	let hidden_dep=$(obj).closest(".contDrag").find(".depIdMapDiv").find(".hidden_Dep");
					//let len= $(hidden_dep).length;
					let dep=$(obj).closest(".contDrag").attr("class").split(" ").pop();
				//	if(len > 0 )
				//	{
					
						if( $(obj).val() == "No" || $(obj).val() == "-1" )
						{
							//for(let i=0;i<len;i++){
								$(obj).closest("#subContDivLeft").find(".Dep-"+dep).css("display","none");
								$(obj).closest("#subContDivLeft").find(".Depl-"+dep).css("display","");
								//$(obj).closest("#subContDivLeft").find("#" + $( $(hidden_dep)[i] ).val() ).css("visibility","hidden");
							//}
								
						}else{
								$(obj).closest("#subContDivLeft").find(".Dep-"+dep).css("display","");
								$(obj).closest("#subContDivLeft").find(".Depl-"+dep).css("display","none");
							//for(let i=0;i<len;i++)
								//$(obj).closest("#subContDivLeft").find("#" + $( $(hidden_dep)[i] ).val() ).css("visibility","");
						}
							
					//}
					
				} 
				
				$("#${i.label+id}").on("change",function(){ depChange(this); });
			</script>`);
		  }
			  $(thisObj).find("#currRowId").val( $(thisObj).prop("id"));
			  $(thisObj).find("#currRowId").prop('readonly', true);
		  
		  
		  $(thisObj).find(".contDragModal .modal-header").html("<b>"+elName+"</b>");
		
	  }
	
	 
	 
  }
  

  $().ready(()=>{
		$("#addRow").on("click",()=>{
				
				let currId=$("#contDiv").find('[id*=cont-row]').last().attr('id');
				//alert(currId);
				let nextid=currId.split("-");
				let i=parseInt(nextid[2])+1;
				nextid="-"+nextid[1]+"-"+i;
				
				
				let $div=$("#cont-row-0").clone().prop('id',"cont"+nextid);
				$div.css("display","");
				$div.find('[id*=row-0-col-1]').attr("id","row-"+i+"-col-1");
				$div.find('[id*=row-0-col-2]').attr("id","row-"+i+"-col-2");
				$div.find('[id*=row-0-col-3]').attr("id","row-"+i+"-col-3");
				$div.find('[id*=row-0-col-4]').attr("id","row-"+i+"-col-4");
	
		$("#subContDivLeft").append(($div));
		
		callDropable( $("#contDiv #cont"+nextid).find(".contDrag"),nextid );
				
		
									
									
	
	
		///$("#cont"+nextid).find(".contDrag").resizable({
		///aspectRatio: true,
			
		
			/*start: function(event, ui)
				{
						
				},
			stop: function(event, ui)
				{     
				
				//let divHeight=ui.size.height;
				//let divFiwdth=ui.size.width;
				console.log(ui.size.width  );
				console.log(ui.originalSize.width);	
				ui.size.width = ui.originalSize.width+34; 				
				},*/
				
			///resize: function(event, ui) 
			///	{
				/*$("#cont-row-0").css("display","");
				let wid=$("#cont-row-0").find(".contDrag").width();
				$("#cont-row-0").css("display","none");
					//ui.size.width = ui.originalSize.width;  
					ui.size.width=wid;*/
					
				///	$("#cont"+nextid).find(".contDrag").width("100%");
			///}
		///});
				
				
	});
});
  
										
										
									//	$(".el-dtl").append(elLit);
									
									
									

										

$().ready(()=>{
	
	$("#preview").on("click",()=>{
		
	
	 let $subContDivLeft=$("#subContDivLeft").clone(true);
	
		$("#prevCont").empty();
		$("#prevCont").append($subContDivLeft);	
		$("#prevCont div .rmEl").remove();
		$("#prevCont div #cont-row-0").remove();
		$("#prevCont .actualRow").removeClass("col-sm-11").addClass("col-sm-12");
		$("#prevCont #subContDivLeft").removeClass("col-sm-10");
		$("#prevCont *").removeClass("tmpA");
		$("#prevCont *").removeClass("tmpC");
		$("#prevCont *").removeClass("tmpB");
		
		$("#prevCont").find(".placeHold").remove();
		$("#prevCont").find("input[id^='hidden-row']").attr("type","hidden");
		
		
		$("#prevCont").find('[data-toggle="tooltip"]').tooltip();   
		  
		$("#prevCont").find('[data-toggle="popover"]').popover();
		$("#prevCont").find("[id^=PredictList]").val("");
		$("#prevCont").find("[id^=PredictList]").autocomplete({
		      source: PredictTags,
		      appendTo : "#prevCont"
		    });
		
		$("#prevCont").find("[id^=Autocomplete]").val("");
		$("#prevCont").find("[id^=Autocomplete]").attr("onkeyup","callAutoComp(this)");
		
		$("#previewModal .header-cont").html( "<h5>"+$("#strTemplateNameId").val()+"</h5>");
		$("#previewModal").modal("show"); 
		
		var jsonData = []; 
		$("#prevCont .addedEl").each((i,a)=>{
			let name=a.id.split("-")[0];
			jsonData[i]={};
			jsonData[i].element=name;
			jsonData[i].id=a.id;
			jsonData[i].paraMap=$(a).closest(".contDrag").find(".paraMap").val().split("_attach_")[0];
			jsonData[i].paraMapName=$(a).closest(".contDrag").find(".paraMap").val().split("_attach_")[1];
			
			if(name=="Autocomplete"){
				if($(a).closest(".contDrag").find(".addedElHelper").val()!=""){
					jsonData[i].autoMapVal=$(a).closest(".contDrag").find(".addedElHelper").val().split("_attach_")[0];
					jsonData[i].autoMapName=$(a).closest(".contDrag").find(".addedElHelper").val().split("_attach_")[1];
				}else{
					jsonData[i].autoMapVal="0";
					jsonData[i].autoMapName="Not Mapped";
				}
				
			}	
			
				
			
			if(!$(a).hasClass("addedElGrp"))
					if(name=="Unit Based"){
						jsonData[i].value=$(a).find("input").val()+"#"+$(a).find("button").html();
						//jsonData[i].unit_value=$(a).find("button").html();
					}else{
						jsonData[i].value=a.value;
					}
			
			if(name=="Image"){
				jsonData[i].value=$(a).closest(".contDrag").find(".ImgUpDiv img").attr("src");
			}
			
				
			else{
				jsonData[i].values={};
				
					
						$(a).find(".addedElGrpIn").each((j,b)=>{
							
							jsonData[i].values[j]={};
							jsonData[i].values[j].name=$(b).find('input').attr("name");
							jsonData[i].values[j].optionName=$(b).find('label').text();
							jsonData[i].values[j].val=$(b).find('input').val();
						});
						//	jsonData[i].value=$(a).find('input[name="optradio"]:checked').val();
						
		
				//	jsonData[i].value=$(a).find('input[type="checkbox"]:checked').val();
			}
			var strDeptCode=[];
	    	var strDeptName=[];
	    	var i=0 ,j=0;
	    	$("#strDeptCodeId option:selected").each(function () {
				   var $this = $(this);
				   if ($this.length) {
				   var t1 = $this.text();
				   /*  console.log(t1);
				    console.log('vvvvvvvvvv  '+$this.val()); */
				    strDeptName[i]= $this.text();
				    strDeptCode[j]=$this.val();
				    i++;
				    j++;
				   }
				});
	    	var edemaID ='';
	    	if($('#defChk').is(":checked"))
				edemaID = '1';
				else
				edemaID = '0';
	    	
			console.log(strDeptCode);
			console.log(strDeptName);
			var Json={
					"DeptCode"  		: strDeptCode ,
					"DeptName"			: strDeptName ,
					"TemplateName"		: $('#strTemplateNameId').val(),
					"TemplateCat"		: $('#strTempCatId').val() ,
					"TemplateType"		: $('#strTemplateType').val() ,
					"IsTemplateActive"	: edemaID
					}
			var myJSON = JSON.stringify(Json);
			console.log(JSON.stringify(jsonData));
			$("input[name='strJsonParaMetereIdString']").val(JSON.stringify(jsonData));
			$("input[name='strHtmlString']").val($("#prevCont").html());
		//	console.log($("#prevCont").html());
			$("input[name='strModifyData']").val($("#contDiv").html());
			$("input[name='strEssentilaJson']").val(myJSON);
			});
	});
});

function seekPlus(obj){
		//let $val =$(obj).closest(".contDrag").find("div[class^='addedEl-']").css("margin-left");
	let curPer=0;
	if($(obj).closest(".contDrag").find(".addedElDiv").width() != undefined){
		 curPer=pxToPer( $(getActObj(obj)).css("margin-left"),$(obj).closest(".contDrag").find(".addedElDiv").width() );
	}		
	else{
		curPer=pxToPer( $(getActObj(obj)).css("margin-left"),$(obj).closest(".contDrag").width() );
	}
			
		if(curPer==0)
			$(getActObj(obj)).css("margin-left","10%");
		else if(curPer<20){
			curPer +=5;
			$(getActObj(obj)).css("margin-left",curPer+"%");
		}
		else
			alert("max position");
			
}
function seekMinus(obj){
		//let $val =$(obj).closest(".contDrag").find("div[class^='addedEl-']").css("margin-left");
			
	let curPer=0;
	if($(obj).closest(".contDrag").find(".addedElDiv").width() != undefined){
		 curPer=pxToPer( $(getActObj(obj)).css("margin-left"),$(obj).closest(".contDrag").find(".addedElDiv").width() );
	}		
	else{
		curPer=pxToPer( $(getActObj(obj)).css("margin-left"),$(obj).closest(".contDrag").width() );
	}
		
		
			
										
			if(curPer>-10){
				curPer-=5;
			$(getActObj(obj)).css("margin-left",curPer+"%");
			}else
				alert("minimum position");

			
}

function pxToPer(curWidth,parWidth){ return (Math.trunc(parseInt(curWidth))/Math.trunc(parseInt(parWidth)))*100 }


$().ready(()=>{
	$("#clear").on("click",()=>{confirmBS("All Template Data Will Be Lost","","2","","warning");});
});


function onLabelChange(obj){ $(getActObj(obj)).html( $(obj).val() ); $(obj).attr("value",$(obj).val());}

function onLabelOfLabelChange(obj){ 
	if($(getActObj(obj))[0].firstElementChild == null)
			$(getActObj(obj)).html( $(obj).val() );
	else
		$(getActObj(obj)).html( "<u>"+$(obj).val()+"</u>" );
	}

function onPlaceholdChange(obj){$(getActObj(obj)).attr("placeholder",$(obj).val());$(obj).attr("value",$(obj).val());}

function onhelpTextChange(obj){
	if($(obj).val().length >1){
		$(obj).closest(".contDrag").find(".helptextDiv").css("display","");
		$(obj).closest(".contDrag").find(".helptextDiv").attr("title",$(obj).val());
		$(obj).attr("value",$(obj).val());

	}else
		$(obj).closest(".contDrag").find(".helptextDiv").css("display","none");
	}


function onLabelNameChange(obj)  {
	//console.log($(obj).val());
									if($(obj).val()=="")
										$(getActObjLabelDiv(obj)).css("display","none"); 
									else{
										$(obj).attr("value",$(obj).val());
										$(getActObjLabelDiv(obj)).css("display",""); 	
										$($(getActObjLabel(obj))[0].firstChild)[0].data  = $(obj).val();
									//	$(getActObjLabel(obj)).text( $(obj).val() );  
									}

								 }

function onStyleChange(obj,val){
let attachedEl=getActObj(obj);
let alAttachedClass=$(attachedEl).attr("class");
alAttachedClass=alAttachedClass.split(" ");
let classvar='\\b'+"btn-"+'\\b'; 
let j=$(attachedEl).attr("id").split("-")[0];
j=getControl("Button");


	for(let i=0;i<alAttachedClass.length;i++)
	{
		if(alAttachedClass[i].match(new RegExp(classvar,'g')))
								{
									$(attachedEl).removeClass(alAttachedClass[i]);
									$(attachedEl).addClass(j.colorClass[parseInt(val)]);
									break;
								}
	}

//$(obj).closest(".contDrag").find(".addedEl-button").html( $(obj).val() );


 }
 
 function onReqClick(obj){
	let id="#req-"+$(obj).closest(".contDrag").prop("id");
	 if(obj.checked){
		 $(obj).val(1);
		
		  console.log(id);
		  $(id).css("display","")
		 
	 }else{
		 $(obj).val(0);
		  $(id).css("display","none")
	 }
 }
 function onInLabelClick(obj){
	
	 if(obj.checked){
		 $(obj).val(1);
		 $(getActObj(obj)).closest(".mainElDiv").removeClass("row");
		 
	 }else{
		 $(obj).val(0);
		 $(getActObj(obj)).closest(".mainElDiv").addClass("row");
	 }
	 }
 
 function onInlineClick(obj){
	
		 if(obj.checked){
			 $(obj).val(1);
			
			 $(getActObj(obj)).find(".addedElGrpIn").addClass("form-check-inline");
			 
		 }else{
			 $(obj).val(0);
			 $(getActObj(obj)).find(".addedElGrpIn").removeClass("form-check-inline");
		 }
	 }
 
 function onOthersClick(obj){
		
	 if(obj.checked){
		 $(obj).val(1);
		 $('<div class="Others addedElGrpIn" type="" ><label><input type="radio" name="optradio-'+$(obj).closest(".contDrag").prop("id")+'" value="">Others<input type="text" value="" class="form-control" id="OthersRadio"> </label></div>').insertAfter(getActObj(obj));
		
		 
	 }else{
		 $(obj).val(0);
		 $(obj).closest(".contDrag").find(".Others").remove();
		
	 }
 }
 
 function onRevertClick(obj){
		 if(obj.checked){
			 $(obj).val("Yes");
			 $($(getActObj(obj))[0][1]).val("Yes");
			 $($(getActObj(obj))[0][2]).val("No");
			$(obj).closest(".el-dtl").find(".el_depShow").attr("placeholder","To Hide");
			$(obj).closest(".el-dtl").find(".el_depHide").attr("placeholder","To Show"); 

		 }else{
			 $(obj).val("No");
			 $($(getActObj(obj))[0][1]).val("No");
			 $($(getActObj(obj))[0][2]).val("Yes");
				$(obj).closest(".el-dtl").find(".el_depHide").attr("placeholder","To Hide"); 
				$(obj).closest(".el-dtl").find(".el_depShow").attr("placeholder","To Show"); 

		 }
	 }
 function onMultiSelectChange(obj){
		 if(obj.checked){
			 $(obj).val(1);
			 $(getActObj(obj)).attr("multiple","multiple");
			 
		 }else{
			 $(obj).val(0);
			 $(getActObj(obj)).removeAttr('multiple');
			 
		 }
	 }
 
 
 function onBoldClick(obj){
	 
	 if($(obj).find("#bChk").val() == "0")
	 {
		 		// $( $(obj).closest(".contDrag").find(".mainLabel") ).wrapInner("<b></b>");

		  $(obj).closest(".contDrag").find(".mainLabel").css("font-weight","bold")
		 $(obj).css("background-color","rgba(52, 98, 147, 0.69)");
		  $(obj).find("#bChk").val("1");
		  
	 }
	 else{
		 
		  $(obj).closest(".contDrag").find(".mainLabel").css("font-weight","")
		  $(obj).css("background-color","#ccc");
		  $(obj).find("#bChk").val("0");
		 
	 }
	 
	
 }
 
 function onItalicClick(obj){
	 if($(obj).find("#iChk").val() == "0")
	 {
		 		// $( $(obj).closest(".contDrag").find(".mainLabel") ).wrapInner("<b></b>");

		  $(obj).closest(".contDrag").find(".mainLabel").css("font-style","italic")
		 $(obj).css("background-color","rgba(52, 98, 147, 0.69)");
		  $(obj).find("#iChk").val("1");
		  
	 }
	 else{
		 
		 $(obj).closest(".contDrag").find(".mainLabel").css("font-style","")
		  $(obj).css("background-color","#ccc");
		  $(obj).find("#iChk").val("0");
		 
	 }
 }
 
 function onUnderClick(obj){
	 
	  if($(obj).find("#uChk").val() == "0")
	 {
		 		

		 $( $(obj).closest(".contDrag").find(".mainLabel")[0].childNodes[0] ).wrap("<u></u>");
		 $(obj).css("background-color","rgba(52, 98, 147, 0.69)");
		  $(obj).find("#uChk").val("1");
		  
	 }
	 else{
		 
		 $( $(obj).closest(".contDrag").find(".mainLabel")[0].childNodes[0].childNodes[0] ).unwrap();
		  $(obj).css("background-color","#ccc");
		  $(obj).find("#uChk").val("0");
		 
	 }
 }
 
 function onLeftClick(obj){
	 
	 if($(obj).find("#smChk").val() == "0")
	 {
		   if($(getActObj(obj)).closest(".mainElDiv").find(".labelElDiv").length !="0")
			$(getActObj(obj)).closest(".mainElDiv").find(".labelElDiv").css({"text-align":"left","margin-right":"0"});	
		else
		 $(getActObj(obj)).closest(".mainElDiv").find(".addedElDiv").css({"text-align":"left","margin-right":"0"});	
		
		  $(obj).removeClass("btn-secondary");
		  $(obj).addClass("btn-primary");
		  $(obj).next().addClass("btn-secondary");
		  $(obj).prev().addClass("btn-secondary");
		  
		  $(obj).find("#smChk").val("1");
		  $(obj).parent().find("#mdChk").val("0");
		  $(obj).parent().find("#lgChk").val("0");
		  
	 }
		 
	
 }
 
 function onCenterClick(obj){
	 
	 if($(obj).find("#mdChk").val() == "0")
	 {
		  
		 
		 if($(getActObj(obj)).closest(".mainElDiv").find(".labelElDiv").length !="0")
			$(getActObj(obj)).closest(".mainElDiv").find(".labelElDiv").css({"text-align":"center","margin-right":"0"});	
		else
			$(getActObj(obj)).closest(".mainElDiv").find(".addedElDiv").css({"text-align":"center","margin-right":"0"});	
		
		 
		
		  $(obj).removeClass("btn-secondary");
		  $(obj).addClass("btn-primary");
		  $(obj).next().addClass("btn-secondary");
		  $(obj).prev().addClass("btn-secondary");
		  $(obj).find("#mdChk").val("1");
		  $(obj).parent().find("#smChk").val("0");
		  $(obj).parent().find("#lgChk").val("0");
		  
	 }
		 
	
 }
 
 function onRightClick(obj){
	 
	 if($(obj).find("#lgChk").val() == "0")
	 {
		  if($(getActObj(obj)).closest(".mainElDiv").find(".labelElDiv").length !="0")
			$(getActObj(obj)).closest(".mainElDiv").find(".labelElDiv").css({"text-align":"right","margin-right":"0"});	
		 else
			$(getActObj(obj)).closest(".mainElDiv").find(".addedElDiv").css({"text-align":"right","margin-right":"0"});	
		
		  
		 
		
		 $(obj).removeClass("btn-secondary");
		  $(obj).addClass("btn-primary");
		  $(obj).next().addClass("btn-secondary");
		  $(obj).prev().addClass("btn-secondary");
		  $(obj).find("#lgChk").val("1");
		  $(obj).parent().find("#smChk").val("0");
		  $(obj).parent().find("#mdChk").val("0");
		  
	 }
		 
	
 }
 
 
 function onSmClick(obj){
	 
	 if($(obj).find("#smChk").val() == "0")
	 {
		 if($(getActObj(obj)).hasClass("form-control-lg")) 
				$(getActObj(obj)).removeClass("form-control-lg");
		 
		 $(getActObj(obj)).addClass("form-control-sm");		 
		  $(obj).removeClass("btn-secondary");
		  $(obj).addClass("btn-primary");
		  $(obj).next().addClass("btn-secondary");
		  $(obj).prev().addClass("btn-secondary");
		  $(obj).find("#smChk").val("1");
		  $(obj).parent().find("#mdChk").val("0");
		  $(obj).parent().find("#lgChk").val("0");
		  
	 }
		 
	
 }
 
 function onMdClick(obj){
	 
	 if($(obj).find("#mdChk").val() == "0")
	 {
		if($(getActObj(obj)).hasClass("form-control-sm")) 
			$(getActObj(obj)).removeClass("form-control-sm");
		if($(getActObj(obj)).hasClass("form-control-lg")) 
			$(getActObj(obj)).removeClass("form-control-lg");
		 
		 
		   $(obj).removeClass("btn-secondary");
		  $(obj).addClass("btn-primary");
		  $(obj).next().addClass("btn-secondary");
		  $(obj).prev().addClass("btn-secondary");
		  $(obj).find("#mdChk").val("1");
		  $(obj).parent().find("#smChk").val("0");
		  $(obj).parent().find("#lgChk").val("0");
		  
	 }
		 
	
 }
function onLgClick(obj){
	 
	 if($(obj).find("#lgChk").val() == "0")
	 {
		 if($(getActObj(obj)).hasClass("form-control-sm")) 
				$(getActObj(obj)).removeClass("form-control-sm");
		 
		 $(getActObj(obj)).addClass("form-control-lg");
		   $(obj).removeClass("btn-secondary");
		  $(obj).addClass("btn-primary");
		  $(obj).next().addClass("btn-secondary");
		  $(obj).prev().addClass("btn-secondary");
		  $(obj).find("#lgChk").val("1");
		  $(obj).parent().find("#smChk").val("0");
		  $(obj).parent().find("#mdChk").val("0");
		  
	 }
		 
	
 }
 function onColorChange(obj,val){ 
	$(getActObj(obj)).css("color",getControl($(getActObj(obj)).attr("id").split("-")[0]).colorClass[parseInt(val)]);
 }
 
 
 function onNameChange(obj){ 
		 
	 if( $(obj).val().length >1  ){
			
			let mapUrl="OPDTemplateMstAction.cnt?hmode=GETPARAMETER&term="+$(obj).val();
			console.log(mapUrl);
			
			$.ajax({
				  url: mapUrl,
		          type: 'GET',
		          accept: 'json',
				    success: function( res ) {
				    	   let tags= JSON.parse(res);
				        tags=tags.arr;
				      
				       
				        $(obj).autocomplete({  source: tags ,
				        					   appendTo: $(obj).closest(".el-dtl"),
				        					   autoFocus:true,
				        					   select:function(event,ui){
				        						 
				        						   $(obj).closest(".contDrag").find(".paraMap").val(ui.item.id+"_attach_"+ui.item.value);
				        						//  $(getActObjLabel(obj)).html( ui.item.value );  
				        						   $(obj).closest(".contDrag").find(".addedEl").attr("name", ui.item.value ); 
				        						   $(obj).attr("value",ui.item.value);
				        					   }
				        					 });
				    }

				});
			
			
			
		}else{
			$(obj).closest(".contDrag").find(".addedEl").attr("name",$(obj).val());
			$(obj).attr("value",$(obj).val());
		}
		
		
		
 
 }
 

 function onTypeHeaderChange(obj){ 		let color =$(obj).closest(".contDrag").find(".addedEl").css("color");
										let txt =$(obj).closest(".contDrag").find(".addedEl").text();
										let id =$(obj).closest(".contDrag").find(".addedEl").attr("id");
										$(obj).closest(".contDrag").find(".addedEl").replaceWith(function(){
										return "<"+$(obj).val()+" id="+id+" class='addedEl' style='color:"+color+"'>"+txt+"<"+$(obj).val()+">" });}
 
 function onTypeLabelChange(obj){ 
	 
	 let color =$(getActObj(obj)).css("color");
	 let fnt =$(getActObj(obj)).css("font-weight");
	let txt =$(getActObj(obj)).text();

	let id =$(getActObj(obj)).attr("id");
	if($(getActObj(obj))[0].firstElementChild == null)
		$(obj).closest(".contDrag").find(".addedElDiv").html("<"+$(obj).val()+" id="+id+" class='addedEl mainLabel' style='color:"+color+";font-weight:"+fnt+";'>"+txt+"<"+$(obj).val()+">");
	else	
		$(obj).closest(".contDrag").find(".addedElDiv").html("<"+$(obj).val()+" id="+id+" class='addedEl mainLabel' style='color:"+color+";font-weight:"+fnt+";'><u>"+txt+"</u><"+$(obj).val()+">");
 }
 
 function onTypeButtonChange(obj){ $(obj).closest(".contDrag").find(".addedEl").attr("type",$(obj).val()); }

									
function onDataTypeChange(obj){
	
		$(obj).closest(".contDrag").find(".autoUrl").val($(obj).val());
	
		/*	if (typeof window.callAutoComplete === 'undefined') 
			    	  console.log('********callAutoComplete not defined********');
			  else
				  callAutoComplete(obj);*/
		
}		
  
  $(()=>{
		
		$("#TempColor").on("click",()=>{
			if($("#TempColorDiv").css("display")== "none")
				$("#TempColorDiv").css("display","");
			else
				$("#TempColorDiv").css("display","none");
		});
		
		for(let i=1;i<5;i++){
			 i<3?$("#addRow").trigger("click"):"";
			 $("#col"+i).on("click",()=>{
				$("#subContDivLeft").css("background",$(this).css("background-color"));
				$("#prevCont").css("background",$(this).css("background-color"));
			});
		}
		
	});								
 
 
function addOpt(obj){
let currId=$('[id*=el_OptionsIn]').last().attr('id');
let nxtId="el_OptionsIn-"+(parseInt(currId.split("-")[1])+1);
	
let $div=$(obj).closest("#el_OptionsIn-1").clone().prop("id",nxtId);
$div.html(el_optionsIn);
$div.find(".addOptDiv").css("display","none");

console.log("currId::::"+currId.split("-")[1]);

if(parseInt(currId.split("-")[1]) > 1)
	$("#el_OptionsIn-"+currId.split("-")[1]).find(".rmOptDiv").css("display","none");

$div.find(".rmOptDiv").css("display","");

$(obj).closest(".el_Options").append($div);

}

function addOptDept(obj,val){
	let currId=$(obj).closest(".el_Options").find('[id*=el_OptionsIn]').last().attr('id');
	let nxtId="el_OptionsIn-"+(parseInt(currId.split("-")[1])+1);
		
	let $div=$(obj).closest("#el_OptionsIn-1").clone().prop("id",nxtId);
	if(val=="unit")
			$div.html(el_UnitIn);
		else
			$div.html(el_DeptOptionsIn);
	$div.find(".addOptDiv").css("display","none");

	console.log("currId::::"+currId.split("-")[1]);

	if(parseInt(currId.split("-")[1]) > 1)
		$("#el_OptionsIn-"+currId.split("-")[1]).find(".rmOptDiv").css("display","none");

	$div.find(".rmOptDiv").css("display","");

	$(obj).closest(".el_Options").append($div);

	}



function addOptPredict(obj){
	let currId=$('[id*=el_OptionsIn]').last().attr('id');
	let nxtId="el_OptionsIn-"+(parseInt(currId.split("-")[1])+1);
		
	let $div=$(obj).closest("#el_OptionsIn-1").clone().prop("id",nxtId);
	$div.html(el_options_predict);
	$div.find(".addOptDiv").css("display","none");

	console.log("currId::::"+currId.split("-")[1]);

	if(parseInt(currId.split("-")[1]) > 1)
		$("#el_OptionsIn-"+currId.split("-")[1]).find(".rmOptDiv").css("display","none");

	$div.find(".rmOptDiv").css("display","");

	$(obj).closest(".el_Options").append($div);

	}


function addOptRadio(obj){
	
let currId=$('[id*=el_OptionsIn]').last().attr('id');
let nxtId="el_OptionsIn-"+(parseInt(currId.split("-")[1])+1);
	
let $div=$(obj).closest("#el_OptionsIn-1").clone().prop("id",nxtId);
$div.find(".addOptDiv").css("display","none");


if(parseInt(currId.split("-")[1]) > 1)
	$("#el_OptionsIn-"+currId.split("-")[1]).find(".rmOptDiv").css("display","none");

$div.find(".rmOptDiv").css("display","");





$(obj).closest(".el_Options").append($div);



let $opt= $($(obj).closest(".contDrag").find(".addedElGrpIn")[0]).clone() ;

$opt.prop("id",$opt.prop("id").split("opt-")[0]+"opt-"+(parseInt($(obj).closest(".contDrag").find(".addedElGrpIn").length)));

$(getActObj(obj)).append($opt);

	
}

function rmOptDiv(obj){

	let ob=$(obj).parent().parent();

	let id=parseInt($(ob).prop("id").split("-")[1] ) -1;

	if(id>1)
		$( $(ob).parent()[0].children[id] ).find(".rmOptDiv").css("display","");
	$(ob).remove();
}

function rmOptDivUnit(obj){

	let ob=$(obj).parent().parent();

	let id=parseInt($(ob).prop("id").split("-")[1] ) -1;
	console.log("--------------");
	$(getActObj(obj)).find("#dropdown-item-"+(id+1)).remove();

	if(id>1)
		$( $(ob).parent()[0].children[id] ).find(".rmOptDiv").css("display","");
	$(ob).remove();
	
//	$(getActObj(obj)).find("#dropdown-item-"+(id+1)).remove();
}

function rmOptDivRadio(obj){
	let ob=$(obj).parent().parent();

	let id=parseInt($(ob).prop("id").split("-")[1] ) -1;
	//alert(id);
	if(id>0){
		if($(ob).prop("id")!="el_OptionsIn-2" )
			$( $(ob).parent()[0].children[id] ).find(".rmOptDiv").css("display","");
	$($(obj).closest(".contDrag").find(".addedElGrpIn"))[id].remove();
	$(ob).remove();
	}
	
	
}
 
function addOptName(obj){
	let currId=$(obj).parent().parent().attr("id").split("-")[1];
	
	let curLen=$(getActObj(obj)).children('option').length;
	if(currId == curLen ){
		$(getActObj(obj)).append("<option value='"+$(obj).val()+"'>"+$(obj).val()+"</option>");
		$(obj).parent().find("input[name='optvalue']").val($(obj).val());
		//$(obj).parent().find("input[name='optvalue']").attr("disabled",false);

	}
	else{
		$(getActObj(obj)).children("option:eq("+currId+")").html($(obj).val());
		$(getActObj(obj)).children("option:eq("+currId+")").val($(obj).val());
		$(obj).parent().next().find("input").val($(obj).val());
		//$(obj).parent().next().find("input").attr("disabled",false);

	}
		
	
	
}

function addDepId(obj){
//depIdMapDiv
let id=parseInt($(obj).parent().parent().prop("id").split("-")[1]);


/*if($(obj).closest(".contDrag").find(".depIdMapDiv").find("#hidden_Dep-"+id).length > 0 )
	$($(obj).closest(".contDrag").find(".depIdMapDiv").find("#hidden_Dep-"+id)).val($(obj).val());
else{
	let inpEl=document.createElement("input");  
			  $(inpEl).attr("type","hidden");
			  $(inpEl).attr("id","hidden_Dep-"+id);
			   $(inpEl).attr("class","hidden_Dep");
			  $(inpEl).val($(obj).val());
			  $(obj).closest(".contDrag").find(".depIdMapDiv").append(inpEl);*/

let match=$($(obj).closest("#subContDivLeft").find("#"+$(obj).val()).children()[0]).attr("class");
	if(match != 'Dep-'+$(obj).closest(".contDrag").attr("class").split(" ").pop()){
		
	
			  $(obj).closest("#subContDivLeft").find("#"+$(obj).val()).wrapInner(
			  '<div class="Dep-'+$(obj).closest(".contDrag").attr("class").split(" ").pop()+'" style="display:none"></div>'
				);
				 $(obj).closest("#subContDivLeft").find("#"+$(obj).val()).append(
				 '<label class="rmEL Depl-'+$(obj).closest(".contDrag").attr("class").split(" ").pop()+'" style="color:blue;margin-left:35%;">Dependent</label>');
			 // $(obj).closest("#subContDivLeft").find("#"+$(obj).val()).css("visibility","hidden");
	}
	
	$(obj).val("");
//}
	
}

function addUnit(obj){
	let id=parseInt($(obj).parent().parent().prop("id").split("-")[1]);


	if($(obj).closest(".contDrag").find(".dropdown-menu").find("#dropdown-item-"+id).length > 0 )
		$($(obj).closest(".contDrag").find(".dropdown-menu").find("#dropdown-item-"+id)).html($(obj).val());
	else{
		let inpEl=document.createElement("a");  
				  $(inpEl).attr("href","#");
				  $(inpEl).attr("onclick","unitSelect(this)");
				  $(inpEl).attr("id","dropdown-item-"+id);
				   $(inpEl).attr("class","dropdown-item");
				  $(inpEl).html($(obj).val());
				  $(obj).closest(".contDrag").find(".dropdown-menu").append(inpEl);
	}
		
	}


function addOptValue(obj){
	let currId=$(obj).parent().parent().attr("id").split("-")[1];
	
	let curLen=$(getActObj(obj)).children('option').length;
	
	
	
	if(currId == curLen )
		$(getActObj(obj)).val(0);
	else
		$(getActObj(obj)).children("option:eq("+currId+")").val($(obj).val());
}

function addOptNameCheck(obj){
	let currId=$(obj).parent().parent().attr("id").split("-")[1];
	
	let curLen=$(getActObj(obj)).children('option').length;
	
	
	
	if(currId == curLen )
		$(getActObj(obj)).append("<option value='0'>"+$(obj).val()+"</option>");
	else
		$(getActObj(obj)).children("option:eq("+currId+")").html("<option value='1'>"+$(obj).val()+"</option>");
	
	
}

function onTextAreaChange(obj){
	$(getActObj).val($(obj).val());
	$(obj).attr("value",$(obj).val());
}

function onValueChange(obj){
	$(getActObj(obj)).val($(obj).val());
	$(obj).attr("value",$(obj).val());
	
}

function onMinChange(obj){ $(getActObj(obj)).attr("min",$(obj).val()); }
function onMaxChange(obj){ $(getActObj(obj)).attr("max",$(obj).val()); }
function onMinChangeUnit(obj){ $(getActObj(obj)).find("input").attr("min",$(obj).val()); }
function onMaxChangeUnit(obj){ $(getActObj(obj)).find("input").attr("max",$(obj).val()); }
function onMaxTextAreaChange(obj){ $(getActObj(obj)).attr("maxlength",$(obj).val()); }
function onStepChange(obj){ $(getActObj(obj)).attr("step",$(obj).val()); }

function onNameChangeRadio(obj){
	
	 if( $(obj).val().length >1  ){
			
			let mapUrl="OPDTemplateMstAction.cnt?hmode=GETPARAMETER&term="+$(obj).val();
			console.log(mapUrl);
			
			$.ajax({
				  url: mapUrl,
		          type: 'GET',
		          accept: 'json',
				    success: function( res ) {
				    	   let tags= JSON.parse(res);
				        tags=tags.arr;
				      
				       
				        $(obj).autocomplete({  source: tags ,
				        					   appendTo: $(obj).closest(".el-dtl"),
				        					   autoFocus:true,
				        					   select:function(event,ui){
				        						 
				        						   $(obj).closest(".contDrag").find(".paraMap").val(ui.item.id);
				        						//  $(getActObjLabel(obj)).html( ui.item.value );  
				        					//	   $(getActObj(obj)).find("input[name^='optradio']").attr("name", ui.item.value ); 
				        					   }
				        					 });
				    }

				});
			
			
			
		}else{
			//$(getActObj(obj)).find("input[name^='optradio']").attr("name",$(obj).val());
		}
}


function onLabelGoupChange(obj){ $(getActObjLabelGroup(obj)).css("display","");//$(getActObjLabelGroup(obj)).html($(obj).val());
$($(getActObjLabelGroup(obj))[0].firstChild)[0].data  = $(obj).val();
 }
 
 
 function addOptNameRadio(obj){
	 
	 let i= parseInt($(obj).closest(".row").prop("id").split("-")[1])-1 ;
	 i="opt-"+i;
	 let radioObj=$(getActObj(obj)).find("div[id$='"+i+"']");
	 let txt=$(radioObj).find("input").detach();
	 $(radioObj).find("label").html($(obj).val());
	
	 $(radioObj).find("label").prepend(txt);
	 $(radioObj).find("label").find("input").val($(obj).val());
	 $(obj).parent().next().find("input[name='value']").val($(obj).val());
		 
 }
 
 function unitSelect(obj){
	 $(getActObj(obj)).find("button").html($(obj).html());
 }
 
 function addOptValueRadio(obj){
	  let i= parseInt($(obj).closest(".row").prop("id").split("-")[1])-1 ;
	  i="opt-"+i;
	  $(getActObj(obj)).find("div[id$='"+i+"']").find("input").val($(obj).val());
 }
  
  function addElCol(obj){
	  let mainRow=$(obj).closest("div[id^='cont-row']").find(".contDragCont");
	  let i=parseInt(mainRow[0].children.length)-1;
	  let id=	$("#contDiv").find($(mainRow[0].children[i])).prop("id");
	  id=id.split("col-")[0] +"col-" + ( parseInt(id.split("col-")[1])+1 );
	  //console.log(id);
	  if(i<11){
		  let nextid="-"+id.split("-col-")[0];
		  $(mainRow).append( $(el_columns).attr("id",id) );
		   callDropable( $("#contDiv #cont"+nextid).find(".contDrag") , nextid);
		
		
	  }
	  else
		  alert("max no. of columns already added");
	  
	  
  
  }
  
  function rmElCol(obj){
	  
	   let mainRow=$(obj).closest("div[id^='cont-row']").find(".contDragCont");
	  let i=parseInt(mainRow[0].children.length)-1;
	  let id=	$(mainRow[0].children[i]).prop("id");
	  
	  if(i>0)
		$("#"+id).remove();
	  else
		  alert("Add Columns To Delete");
	 
	  
	  
  }
  
  
  function callDropable( obj,nid){
	  
	  
	  $(obj).droppable({
										accept:".draggable",
										drop: function( event, ui ) 
										{
											console.log("dropped");
											let dragedObj=$(ui.draggable).clone();
											let thisObj=$(this);
											$(thisObj).find(".placeHold").remove();
																						
											
											if(dragedObj[0].innerText.trim() == "Clear Cell"){
												$(thisObj).empty();
												$(thisObj).append(rmColInLabel);
												$(thisObj).append(placeHold);
											}else{
												if( $(thisObj).find(".addedEl").length>0 ){
													$(thisObj).empty();
													$(thisObj).append(rmColInLabel);
													attachElement(thisObj,dragedObj);
												}										
												else
													attachElement(thisObj,dragedObj);
											}
											

											
											
												//ab.resizable({containment: "parent"});
											//$(this).append(ab);
										}
									});
	  
  }
  
  $().ready(()=>{
	  $("#delRow").on("click",()=>{
			$("#subContDivLeft")[0].lastChild.remove();
	  });
  });
  
  
function rmColIn(obj){ 
		let div=$(obj).parent();
		let match=$(div).attr("class").split(" ").pop();

		if($(div).parent()[0].childElementCount>1){
			if(match.split("-")[0]=="Dep"){
				confirmBS("All dependency will be removed",div,"3","cell deleted","");
			}else{
				confirmBS("delete this cell",div,"1","cell deleted","");			

			}
		}else{
			alert("min no. of columns");
		}
			
			
 }



$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	  
	  $('[data-toggle="popover"]').popover({
	        html : true,
	        trigger: 'focus'});   
	});


function confirmTrue(obj,values,msg){
	
	if(values == "1")
		{
		
			let id=$(obj).prop("id");
			let curid=parseInt( id.split("col-")[1] )-1;
			let nxtId="";		
			for(let j=curid;j<obj.parent()[0].childElementCount;j++)
			{
				
				nxtId=$(obj.parent()[0].children[j+1]).prop("id");
			    $(obj.parent()[0].children[j+1]).prop("id",id);
			    let innerEl=$(obj.parent()[0].children[j+1]).find(".addedEl");
				let innerElGrp=$(obj.parent()[0].children[j+1]).find(".addedElGrpIn");
			
				if( $(innerEl).prop("id") != undefined )
				{
					$(innerEl).prop("id", ( $(innerEl).prop("id").split("-")[0]+"-"+id ) );
				}
				
				if( $(innerEl).prop("id") != undefined )
				{
					
					for(let k=0;k< $(innerElGrp).length;k++)
					{
						 $($(innerElGrp)[k]).prop("id" ,  ( $($(innerElGrp)[k]).prop("id").split("-")[0]+"-"+id+"-opt-"+k )	  );
					}
						
				}
				id=nxtId;
			}
			$(obj).remove();
			
			if(msg !=""){
				//alS(msg);
			}
			
		
		}
	if(values == "2")
	{
		let $latentRow = $("#cont-row-0").detach();
		$("#subContDivLeft").empty();
		$("#subContDivLeft").append($latentRow);
	
	}
	if(values == "3")
	{
		let dep=$(obj).attr("class").split(" ").pop();
		
		$(obj).closest("#subContDivLeft").find(".Dep-"+dep).each(function(){
			let $elemP=$(this).parent();
			let $elem=$(this).children().detach();
			$elemP.empty();
			$elemP.append($elem);
		});
		confirmTrue(obj,"1",msg)
	
	}
	
}


function defaultCheckRad(obj){
	let currId=$(obj).parent().parent().attr("id").split("-")[1];
		
	$(getActObj(obj)).find("option:eq("+currId+")").attr('selected', 'selected');
	
	
}

function onTypeTextAreaChange(obj){$(getActObj(obj)).jqte();}

var PredictTags = [];
function addOptNamePredict(obj){
	PredictTags.push($(obj).val());
	 $(obj).parent().next().find("input[name='option']").val($(obj).val());

   // $(getActObj(obj)).autocomplete({
   //     source: PredictTags
        
    //  });
   
}
function addOptvalPredict(obj){
	$(obj).closest(".contDrag").find(".predictVal").append('<input type="hidden" val="'+$(obj).val()+'">');
}
function depShowHide(obj){
	
	let revChk=$(obj).closest(".el-dtl").find("input[name='el-Revert']").val();
	let el_depShow= $(obj).parent().parent().find(".el_depShow").val();
	let el_depHide= $(obj).parent().parent().find(".el_depHide").val();
	let inval="";
	
	if(el_depShow.trim()!="" && el_depHide.trim() !=""){
		if(revChk=="0")
			 inval='<option value="-1">Select Value</option><option value="Yes">'+el_depShow+'</option><option value="No">'+el_depHide+'</option>';
		else
			 inval='<option value="-1">Select Value</option><option value="Yes">'+el_depShow+'</option><option value="No">'+el_depHide+'</option>';
		 $(getActObj(obj)).html(inval);
	}else{
		if(revChk=="0")
			el_depShow.trim()==""?alert("Enter text To Show"):alert("Enter text To Hide");
		else
			el_depHide.trim()==""?alert("Enter text To Show"):alert("Enter text To Hide");
	}
	
	
}


$(document).ready(function(){
   $("#prescSaveBtnId").click(function(){
   	 // alert("The paragraph was clicked.");
   	  //$('#printPrescriptionModal').modal('show');
   	//  $(window.parent('#printPrescriptionModal .modal-header').find(".close")).click();
   	 document.forms[0].hmode.value="SAVE" ;
		 document.forms[0].submit(); 
   	}); 
});
function callAutoComp(obj){
	//alert('1');
/*	 if ($(obj).hasClass('ui-autocomplete-input')) {
		   $(obj).autocomplete("destroy");
	   }*/
	
	 if( $(obj).val().length >1  ){
			
			let mapUrl=$(obj).closest(".contDrag").find(".autoUrl").val();
			if(mapUrl=="")
				alert("Type of Autocomplete Data Not mapped");
			else{
				mapUrl=mapUrl.replace("_termReplace_",$(obj).val());
				console.log(mapUrl);
				$.ajax({
					  url: mapUrl,
			          type: 'GET',
			          accept: 'json',
					    success: function( res ) {
					    	console.log("res");
					    	console.log(res);
					    	   let tags= JSON.parse(res);
					    	   tags=tags.arr;
					    	
					    	   
					        $(obj).autocomplete({  source: tags ,
					        					   appendTo: "#prevCont",
					        					   autoFocus:true,
					        					   select:function(event,ui){
					        						 
					        						   $(obj).closest(".contDrag").find(".autoCompHelper").val(ui.item.id+"_attach_"+ui.item.value);
					        						   }
					        					 });
					    }

					});
			}

		}else{
			$(obj).closest(".contDrag").find(".addedEl").attr("name",$(obj).val()); 
		}
}

$(document).ready(function(){
	   $(".backbtn").click(function(){
	     	 document.forms[0].hmode.value="unspecified" ;
			 document.forms[0].submit(); 
	   	}); 
	});

function cpy(obj){
	let elem =$(obj).parent().parent().find("#currRowId");		
	 target = elem;
	 origSelectionStart = elem.selectionStart;
    origSelectionEnd = elem.selectionEnd;
	var currentFocus = document.activeElement;
target.focus();
target[0].setSelectionRange(0, $(target).val().length);
  let succeed;
try {
	  succeed = document.execCommand("copy");
} catch(e) {
    succeed = false;
}
if (currentFocus && typeof currentFocus.focus === "function") {
    currentFocus.focus();
} 

 elem[0].setSelectionRange(origSelectionStart, origSelectionEnd);
 
 return succeed;

}