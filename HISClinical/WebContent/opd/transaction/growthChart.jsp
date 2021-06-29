<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.min.js"></script> -->
<%-- <his:javascript src="/hisglobal/js/validation.js"/>


<html>
<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>  --%>
<his:javascript src="/ehr/js/Chart.min.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

<div id="printable">
	<div class="col-sm-10 col-xs-10 col-md-10 col-lg-10 col-xl-10"><centre><h3>Growth Chart - Weight, Head Circumference & Height</h3></centre> </div>
	<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2 col-xl-2" id="printbtndiv">
	<button type="button" class="btn btn-sm btn-success" style="margin-top:0px;line-height:0px;align:right;" id="printGrowthChartId" onclick="printGrowthChart()">PRINT</button> 
	</div>
	<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12">
	<centre><h4>Growth Chart (Age in Years)</h4></centre></div>
	<canvas id="growthChartYears" width="400" height="199"></canvas>
	
	<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12">
	<centre><h4>Growth Chart (Age in Months)</h4></centre></div>
	<canvas id="growthChartMonths" width="400" height="199"></canvas>
	<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12">
	<centre><h4>Growth Chart (Age in Weeks)</h4></centre></div>
	<canvas id="growthChartWeeks" width="400" height="199"></canvas>
</div>
</body>

<script type="text/javascript">

loadGC();

$(document).ready(function(){
	//loadGC();

});

function loadGC()
{
	//alert('a');

		clearChartdata();
		 var form = $('#patInfo');
	  var patCrNo = $("input[name='patCrNo']").val();
	 // alert(patCrNo);
		  var str='';
	 
		 $.ajax({
			 type: "GET",
	         url: "/HISClinical/opd/opdTemplateTab.cnt?hmode=GrowthChartEssential&patCrNo="+patCrNo ,
	         contentType: "application/json",
	         dataType: "json",
	         success: function (data) {
		      // alert(data);
	        	 for (var i = 0; i < data.length; i++) {
					var y=data[i].ageYears,m=data[i].ageMonths,w=data[i].ageWeeks,d=data[i].ageDays;
					var wt=-1,ht=-1,hc=-1;
					//alert(y+' '+m+' '+w+' '+ht+' '+wt+' '+hc);
					
					if(data[i].vitalId=='1110001') // Weight
					{
						wt=	data[i].vitalValue;
						if(data.length>(i+1) && data[i].ageDays==data[i+1].ageDays && data[i+1].vitalId=='1110002')
						{
							i=i+1;
							ht = data[i].vitalValue;
						}
						else
							ht = null;
						if(data.length>(i+1) && data[i].ageDays==data[i+1].ageDays && data[i+1].vitalId=='1111342')
						{
							i=i+1;
							hc = data[i].vitalValue;
						}
						else
							hc = null;

						//alert("wt--"+y+' '+m+' '+w+' '+ht+' '+wt+' '+hc);
						
					}
					else if(data[i].vitalId=='1110002') // Height
					{
						wt=null;
						ht=data[i].vitalValue;
						if(data.length>(i+1) && data[i].ageDays==data[i+1].ageDays && data[i+1].vitalId=='1111342')
						{
							i=i+1;
							hc = data[i].vitalValue;
						}
						else
							hc = null;
						//alert("ht--"+y+' '+m+' '+w+' '+ht+' '+wt+' '+hc);
						
					}
					else if(data[i].vitalId=='1111342') // Head Circumference
					{
						wt=null;
						ht=null;
						hc = data[i].vitalValue;
						//alert("hc--"+y+' '+m+' '+w+' '+ht+' '+wt+' '+hc);
						
					}
					//alert(y+' '+m+' '+w+' '+ht+' '+wt+' '+hc);
					addInYear(y,wt,ht, hc);
					addInMonth(m,wt,ht, hc);
					addInWeeks(w,wt,ht, hc);
					//alert('a');
							

		        	 
		        	 //alert(data[i].vitalId);
		        	  // var imageName=data[i].imageName;
							//alert(imageName);
			                 /* str+='<div class="item active row"><a data-toggle="tooltip"  title="Click to Edit!"><img src="'+data[i].imageFileName+'" width="50" class="img-responsive zoom" data-toggle="modal"  data-target="#myModal_IMG" height="30"  alt="'+imageName+'" style="align:center;" name="'+imageName+'" onclick="openEditor(event,\''+imageName+'\')"></a>'
	        					+'</div>'; */
	        				//	str+='	<div class="item active"><div class="col-md-2 col-sm-6 col-xs-12"><a data-toggle="tooltip"  title="Click to Edit!"><img src="'+data[i].imageFileName+'" width="50" class="img-responsive zoom" data-toggle="modal"  data-target="#myModal_IMG" height="30"  alt="'+imageName+'" style="align:center;" name="'+imageName+'" onclick="openEditor(event,\''+imageName+'\')"></a></div></div>';
	                 
	             } 
	            // alert(ageY,heightY,heightM);
	            
	            setChart();
	             
	        //	$('#img12').append(str);
	             }, 
		  error: function(data)
	      {
		   
	    	}
	      });  
}

function clearChartdata()
{
	ageY=[];
	heightY=[];
	weightY=[];
	hcY=[];
	ageM=[];
	heightM=[];
	weightM=[];
	hcM=[];
	ageW=[];
	heightW=[];
	weightW=[];
	hcW=[];
}

function addInYear(y,wt,ht,hc)
{
	ageY[ageY.length]=y;
	heightY[heightY.length]=ht;
	weightY[weightY.length]=wt;
	hcY[hcY.length]=hc;
}

function addInMonth(m,wt,ht,hc)
{
	ageM[ageM.length]=m;
	heightM[heightM.length]=ht;
	weightM[weightM.length]=wt;
	hcM[hcM.length]=hc;
}

function addInWeeks(w,wt,ht,hc)
{
	ageW[ageW.length]=w;
	heightW[heightW.length]=ht;
	weightW[weightW.length]=wt;
	hcW[hcW.length]=hc;
}

var canvasY = document.getElementById('growthChartYears');
var canvasM = document.getElementById('growthChartMonths');
var canvasW = document.getElementById('growthChartWeeks');


var ageY=[];
var heightY=[];
var weightY=[];
var hcY=[];

var ageM=[];
var heightM=[];
var weightM=[];
var hcM=[];

var ageW=[];
var heightW=[];
var weightW=[];
var hcW=[];

function setChart()
{
var chartYears = {
	type: 'line',
    labels: ageY,
    datasets: [
        {
            label: "Weight",
            fill: false,
            borderColor: "rgba(75,192,192,1)",
            data: weightY,
        },
        {
            label: "Head Circumference",
            fill: false,
            borderColor: "green",
            data: hcY,
        },
        {
            label: "Height",
            fill: false,
            borderColor: "blue",
            data: heightY,
        }
    ]
};

/* function adddata(){
	myLineChart.data.datasets[0].data[7] = 60;
  myLineChart.data.labels[7] = "Newly Added";
  myLineChart.update();
} */

var optionYears = {
responsive: true,
	showLines: true,
	scales: {
		xAxes: [{
    	//type: 'number',
			ticks: {
	                beginAtZero: true
	            },
			scaleLabel: {
				display: true,
				labelString: 'Age (Years)'
			}
		}],
		yAxes: [{
   			 ticks: {
	                beginAtZero: true,
                  stepSize: 10
	            },
			scaleLabel: {
				display: true,
				labelString: 'Weight(kg)                       Head Circumference(cm)                           Height (cm)'
			}
		}]
	},
};

var chartM = {
	type: 'line',
    labels: ageM,
    datasets: [
        {
            label: "Weight",
            fill: false,
            borderColor: "rgba(75,192,192,1)",
            data: weightM,
        },
        {
            label: "Head Circumference",
            fill: false,
            borderColor: "green",
            data: hcM,
        },
        {
            label: "Height",
            fill: false,
            borderColor: "blue",
            data: heightM,
        }
    ]
};
//alert(ageM);
var optionM = {
responsive: true,
	showLines: true,
	scales: {
		xAxes: [{
    	//type: 'number',
			ticks: {
	                beginAtZero: true
	            },
			scaleLabel: {
				display: true,
				labelString: 'Age (Months)'
			}
		}],
		yAxes: [{
   			 ticks: {
	                beginAtZero: true,
                  stepSize: 10
	            },
			scaleLabel: {
				display: true,
				labelString: 'Weight(kg)                       Head Circumference(cm)                           Height (cm)'
			}
		}]
	},
};

var chartW = {
	type: 'line',
    labels: ageW,
    datasets: [
        {
            label: "Weight",
            fill: false,
            borderColor: "rgba(75,192,192,1)",
            data: weightW,
        },
        {
            label: "Head Circumference",
            fill: false,
            borderColor: "green",
            data: hcW,
        },
        {
            label: "Height",
            fill: false,
            borderColor: "blue",
            data: heightW,
        }
    ]
};

var optionW = {
responsive: true,
	showLines: true,
	scales: {
		xAxes: [{
    	//type: 'number',
			ticks: {
	                beginAtZero: true
	            },
			scaleLabel: {
				display: true,
				labelString: 'Age (Weeks)'
			}
		}],
		yAxes: [{
   			 ticks: {
	                beginAtZero: true,
                  stepSize: 10
	            },
			scaleLabel: {
				display: true,
				labelString: 'Weight(kg)                       Head Circumference(cm)                           Height (cm)'
			}
		}]
	},
};



myLineChartY= Chart.Line(canvasY,{
		data:chartYears,
  	options:optionYears
	});


myLineChartM= Chart.Line(canvasM,{
		data:chartM,
  	options:optionM
	});


myLineChartW= Chart.Line(canvasW,{
		data:chartW,
  	options:optionW
	});


}

function printGrowthChart(){
	
	$('#printbtndiv').hide();
	 window.print();
     return false;
	
}

</script>
<style>

@media print {
  body * {
    visibility: hidden;
  }
  #printable,
  #printable * {
    visibility: visible;
  }
  #printable {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: yellow;
  }
}
</style>
</html>