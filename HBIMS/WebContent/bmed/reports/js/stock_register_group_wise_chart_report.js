$(document).ready(function()
	{
	var strGroupName = document.getElementById("strGroupName").value.split("@");
	
	var bars = [[]];
	bars[0] = document.getElementById("strInstalPend").value.split("@");
	bars[1] = document.getElementById("strNotInUse").value.split("@");
	bars[2] = document.getElementById("strNotFuncRepairable").value.split("@");
	bars[3] = document.getElementById("strNotfuncNotRepairable").value.split("@");
	bars[4] = document.getElementById("strFunctional").value.split("@");
	bars[5] = document.getElementById("strNonFunctional").value.split("@");
	
	if(strGroupName.length>8)
	{
		var height = strGroupName.length * 100;
		document.getElementById("barDiv").style.height = height+"px";
	}
	var dataset = [[],[],[],[],[],[]];
	for(j=0;j < bars.length;j++)
		{
			for(var k=0;k< strGroupName.length-1;k++)
			{
				dataset[j][k] = [bars[j][k+1],strGroupName[k+1]];;
			}			
		}
	var dataset1 = [
			[[2,"Cardio Vascular Surgery Equipments and Instruments"], [4,"Laboratory Equipments and Instruments"], [6,"Dentiest Equipments and Instruments"], [7,"Surgery Equipments and Instruments"],[2,"Operation Equipments and Instruments"]],
			[[5,"Cardio Vascular Surgery Equipments and Instruments"], [6,"Laboratory Equipments and Instruments"], [12,"Dentiest Equipments and Instruments"], [1,"Surgery Equipments and Instruments"],[15,"Operation Equipments and Instruments"]],
			[[1,"Cardio Vascular Surgery Equipments and Instruments"], [7,"Laboratory Equipments and Instruments"], [8,"Dentiest Equipments and Instruments"], [3,"Surgery Equipments and Instruments"],[9,"Operation Equipments and Instruments"]],
			[[8,"Cardio Vascular Surgery Equipments and Instruments"], [15,"Laboratory Equipments and Instruments"], [1,"Dentiest Equipments and Instruments"], [13,"Surgery Equipments and Instruments"],[6,"Operation Equipments and Instruments"]],
			[[3,"Cardio Vascular Surgery Equipments and Instruments"], [2,"Laboratory Equipments and Instruments"], [5,"Dentiest Equipments and Instruments"], [6,"Surgery Equipments and Instruments"],[1,"Operation Equipments and Instruments"]],
			[[7,"Cardio Vascular Surgery Equipments and Instruments"], [11,"Laboratory Equipments and Instruments"], [10,"Dentiest Equipments and Instruments"], [9,"Surgery Equipments and Instruments"],[5,"Operation Equipments and Instruments"]],
		];
		var plot2 = $.jqplot('barPlaceHolder',dataset, 
		{
			series:[
	            {label:'Installation Pending'},
	            {label:'Not In Use'},
	            {label:'Non Functional Repairable'},
	            {label:'Non Functional Not Repairable'},
	            {label:'Functional'},
	            {label:'Non Functional'},
        	],
        	legend: {
	            show: true,
	            placement: 'outsideGrid'
	        },
			seriesDefaults: {
				renderer:$.jqplot.BarRenderer,
					pointLabels: { show: true, location: 'e', edgeTolerance: -15 },
					shadowAngle: 15,
					rendererOptions: {
						barDirection: 'horizontal'
					}
				},
		    axes: {
		    	xaxis:{
          			label:'Quantity (In numbers)'
        		},
				yaxis: {
					renderer: $.jqplot.CategoryAxisRenderer,
					tickOptions: {
                        showGridline: true,
                        markSize: 0,
                        label:'Group Name'
                        }
					}
				}
			});
	});