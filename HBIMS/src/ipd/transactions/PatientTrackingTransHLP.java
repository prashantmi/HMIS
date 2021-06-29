package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;

public class PatientTrackingTransHLP {
	
	public static String getMovementDetail(PatientTrackingTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      WebRowSet ws=vo.getMovementDetailsWS();
	      String temp="";
	      String[] tempArr=null;
	      int var=0;
	      try
	      {
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");   
	              sBuffer.append("<tr><td  width='100%' colspan='8' class='TITLE'><div align='left'>Patient Movement Details</div></td></tr>");
	              sBuffer.append("<tr>");
	              sBuffer.append("<td  width='20%' class='LABEL'><div align='center'>Department Name</div></td>");
	              sBuffer.append("<td  width='20%' class='LABEL'><div align='center'>Ward Name</div></td>");
	              sBuffer.append("<td  width='10%' class='LABEL'><div align='center'>Bed Name</div></td>");
	              sBuffer.append("<td  width='10%' class='LABEL'><div align='center'>In Date</div></td>");
	              sBuffer.append("<td  width='10%' class='LABEL'><div align='center'>Out Date</div></td>");
	              sBuffer.append("<td  width='15%' class='LABEL'><div align='center'>In Status</div></td>");
	              sBuffer.append("<td  width='15%' class='LABEL'><div align='center'>Out Status</div></td>");
	              sBuffer.append("</tr></table>");
	              while (ws.next()) 
	              {
	            	  sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
	            	  sBuffer.append("<tr>");
		              sBuffer.append("<td  width='20%' class='CONTROL'><div align='left'>"+ws.getString(1)+"</div></td>");
		              sBuffer.append("<td  width='20%' class='CONTROL'><div align='left'>"+ws.getString(2)+"</div></td>");
		              sBuffer.append("<td  width='10%' class='CONTROL'><div align='left'>"+ws.getString(3)+"</div></td>");
		              sBuffer.append("<td  width='10%' class='CONTROL'><div align='center'>"+ws.getString(4)+"</div></td>");
		              sBuffer.append("<td  width='10%' class='CONTROL'><div align='center'>"+ws.getString(5)+"</div></td>");
		              sBuffer.append("<td  width='15%' class='CONTROL'><div align='left'><font color='blue'><a style='cursor: pointer;' onclick=\"getData('1','statusInDivId"+var+"','1')\">"+ws.getString(6)+"</font></a></div></td>");
		              sBuffer.append("<td  width='15%' class='CONTROL'><div align='left'><font color='blue'><a style='cursor: pointer;' onclick=\"getData('1','statusOutDivId"+var+"','1')\">"+ws.getString(7)+"</font></a></div></td>");
		              sBuffer.append("</tr></table>");
		              
		              if(ws.getString(8)!=null)
		              {
			              temp=ws.getString(8);
			              tempArr=temp.replace("^","#").split("#");
			              sBuffer.append("<div class='popUpDiv' id='statusInDivId"+var+"' style='display: none; top: 250px; left: 250px;'>");
			              sBuffer.append("<table bgcolor='white'><tbody><tr><td>");
			              sBuffer.append("<div class='popup' style='display: block;'>");		              
			              sBuffer.append("<table width='400'>");
			              sBuffer.append("<tbody><tr class='HEADER'>");
			              sBuffer.append("<th align='left'>&nbsp;Status Details &gt;&gt;</th>");
			              sBuffer.append("<th align='right'><img style='cursor: pointer;' src='../../hisglobal/images/FrStopAutoHide.png' tabindex='1' onclick=\"getData('1','statusInDivId"+var+"','2')\"></th></tr>");
			              for(int i=0;i<=tempArr.length-1;i=i+2)
			              {
			            	  sBuffer.append("<tr>");
				              sBuffer.append("<td class='LABEL'>"+tempArr[i]+"</td>");
				              sBuffer.append("<td class='CONTROL'>"+tempArr[i+1]+"</td>");
				              sBuffer.append("</tr>");
			              }
			              sBuffer.append("</tbody></table>");
			              sBuffer.append("<center><table border='0' cellpadding='0' cellspacing='0' width='300'>");
			              sBuffer.append("<tbody><tr>");
			              sBuffer.append("<td><center><img style='cursor: pointer;' src='../../hisglobal/images/btn-ok.png' tabindex='1' onclick=\"getData('1','statusInDivId"+var+"','2')\"></center>");
			              sBuffer.append("</div></td></tr></tbody></table>");
			              sBuffer.append("</td></tr></tbody></table></center></div>");
		              }
		              
		              if(ws.getString(9)!=null)
		              {
			              temp=ws.getString(9);
			              tempArr=temp.replace("^","#").split("#");
			              sBuffer.append("<div class='popUpDiv' id='statusOutDivId"+var+"' style='display: none; top: 250px; left: 250px;'>");
			              sBuffer.append("<table bgcolor='white'><tbody><tr><td>");
			              sBuffer.append("<div class='popup' style='display: block;'>");		              
			              sBuffer.append("<table width='400'>");
			              sBuffer.append("<tbody><tr class='HEADER'>");
			              sBuffer.append("<th align='left'>&nbsp;Status Details &gt;&gt;</th>");
			              sBuffer.append("<th align='right'><img style='cursor: pointer;' src='../../hisglobal/images/FrStopAutoHide.png' tabindex='1' onclick=\"getData('1','statusOutDivId"+var+"','2')\"></th></tr>");
			              for(int i=0;i<=tempArr.length-1;i=i+2)
			              {
			            	  sBuffer.append("<tr>");
				              sBuffer.append("<td class='LABEL'>"+tempArr[i]+"</td>");
				              sBuffer.append("<td class='CONTROL'>"+tempArr[i+1]+"</td>");
				              sBuffer.append("</tr>");
			              }
			              sBuffer.append("</tbody></table>");
			              sBuffer.append("<center><table border='0' cellpadding='0' cellspacing='0' width='300'>");
			              sBuffer.append("<tbody><tr>");
			              sBuffer.append("<td><center><img style='cursor: pointer;' src='../../hisglobal/images/btn-ok.png' tabindex='1' onclick=\"getData('1','statusOutDivId"+var+"','2')\"></center>");
			              sBuffer.append("</div></td></tr></tbody></table>");
			              sBuffer.append("</td></tr></tbody></table></center></div>");
		              }
		              var++;
	              }
		     }
			 catch(Exception e)
			 {
				 new HisException("Patient Tracking","PatientTrackingTransHLP.getMovementDetail()-->",e.getMessage());
		     }
		     return sBuffer.toString();
		 }
	
	
	public static String getMovementDetail_BS(PatientTrackingTransVO vo)

	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      WebRowSet ws=vo.getMovementDetailsWS();
	      String temp="";
	      String[] tempArr=null;
	      int var=0;
	      try
	      {
	    	  sBuffer.append("<div class='modal fade' id='movDetailId' role='dialog'>");
	    	  sBuffer.append("<div class='modal-dialog modal-lg'>");
	    	    
	    		sBuffer.append(" <!-- Modal content-->");
	    		sBuffer.append("<div class='modal-content'>");
	    		sBuffer.append("<div class='modal-header'>");
	    		sBuffer.append("<h4 class='modal-title'>Patient Movement Details</h4>");
	    		sBuffer.append("</div>");
	    		sBuffer.append("<div class='modal-body'>");
	    		
	    		
	    	        //sBuffer.append("<div class='card new'>");
		    	    //sBuffer.append("<h5 style='color: black;'><i class='fas fa-procedures'></i>&nbsp&nbsp<span class='middle'>Patient Movement Details</span></h5><hr>");
					sBuffer.append("<div id='mov' class='carousel slide alert alert-primary' data-ride='carousel'>");
					sBuffer.append("<div class='carousel-inner'>");
	    	   while (ws.next()) 
	              {
	    	    
	    		/*   if(var>0)
	    			   	sBuffer.append("<div class='card new'>");*/
	    		   
	    		   


				
				if(var<1)
				{
					sBuffer.append("<div class='carousel-item active'> <p class='subHeaders'><i class='fas fa-procedures' style='font-size: 26px;'>&nbsp;</i>MOVMENT NO: 1</p>");
					sBuffer.append("<div class='row rowFlex reFlex' >");
					sBuffer.append("<div class='col-sm-2'>");
					sBuffer.append("</div>");
					sBuffer.append("<div class='col-sm-3'>");
					
					sBuffer.append("<div class='row rowFlex reFlex border' >");
		            sBuffer.append("<b align='right'>Department Name</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Ward Name</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Bed Name</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>In Date</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Out Date</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex'>");
		            sBuffer.append("<b>In Status</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Out Status</b></div>");
		            
		            sBuffer.append("</div>");
		    	  
		           
		                sBuffer.append("<div class='col-sm-7'>");
		                
		                sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(1)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(2)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(3)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(4)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(5)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append("<a  style='color: #ff8000;' data-toggle='modal' data-target='#searchModel'   \">"+ws.getString(6)+"</font></a></div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append("<a  style='color: #ff8000;' data-toggle='modal' data-target='#searchModel1' \">"+ws.getString(7)+"</font></a></div>");
		  				sBuffer.append("</div>");
		  				
		  				
		  	            sBuffer.append("</div>");
		  	            sBuffer.append("</div>");
				}else {
					
					sBuffer.append("<div class='carousel-item'><p class='subHeaders'><i class='fas fa-procedures' style='font-size: 26px;'>&nbsp;</i>MOVMENT NO:"+(var+1)+"</p>");
					sBuffer.append("<div class='row rowFlex reFlex' >");
					sBuffer.append("<div class='col-sm-2'>");
					sBuffer.append("</div>");
					sBuffer.append("<div class='col-sm-3'>");
					
					sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b align='right'>Department Name</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Ward Name</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Bed Name</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>In Date</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Out Date</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex'>");
		            sBuffer.append("<b>In Status</b></div>");
		            sBuffer.append("<div class='row rowFlex reFlex' >");
		            sBuffer.append("<b>Out Status</b></div>");
		            
		            sBuffer.append("</div>");
		    	  
		           
		                sBuffer.append("<div class='col-sm-7'>");
		                
		                sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(1)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(2)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(3)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(4)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append(""+ws.getString(5)+"</div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append("<a  style='color: #ff8000;' data-toggle='modal' data-target='#searchModel'   \">"+ws.getString(6)+"</font></a></div>");
		  				sBuffer.append("<div class='row rowFlex reFlex' >");
		  				sBuffer.append("<a  style='color: #ff8000;' data-toggle='modal' data-target='#searchModel1' \">"+ws.getString(7)+"</font></a></div>");
		  				sBuffer.append("</div>");
		  				
		  				
		  	            sBuffer.append("</div>");
		  	            sBuffer.append("</div>");
				}
			
				
			
				/*sBuffer.append("<div class='profile-info-name col-sm-2'><b>Department Name</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-2'><b>Ward Name</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-1'><b>Bed Name</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-1'><b>In Date</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-2'><b>Out Date</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-2'><b>In Status</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-2'><b>Out Status</b></div>");*/
	    		   
	    		

		              if(ws.getString(8)!=null)
		              {
			              temp=ws.getString(8);
			              tempArr=temp.replace("^","#").split("#");
			             
			              
			              
			              
			              
					    sBuffer.append("<div id='mainDiv1' style=''>");
						sBuffer.append("<div class='modal fade' id='searchModel'   tabindex='-1' role='dialog' aria-labelledby='validateModalLabel' aria-hidden='true'>");
						sBuffer.append("<div class='modal-dialog' role='document'>");
						sBuffer.append("<div class='modal-content'>");
						sBuffer.append("<div class='modal-header'>");
						sBuffer.append("<h4 class='modal-title'>Status Details</h4>");
						sBuffer.append("</div>");
						sBuffer.append("<div  class='modal-body' id ='fetchRecordDivId'>");
						    
			             

			              for(int i=0;i<=tempArr.length-1;i=i+2)
			              {
			            	  sBuffer.append("<div class='row rowFlex reFlex' >");
				              sBuffer.append("<div class='col-sm-5' style='border-right:1px dotted black; text-align: right;'>"+tempArr[i]+"</div>");
				              sBuffer.append("<div class='col-sm-7'>"+tempArr[i+1]+"</div>");
				              sBuffer.append("</div>");
			              }
		            	  
							sBuffer.append("</div>");
							sBuffer.append("</div>");
							sBuffer.append("</div>");
							sBuffer.append("</div>");
						    sBuffer.append("</div> ");
		              }
		              
		              if(ws.getString(9)!=null)
		              {
			              temp=ws.getString(9);
			              tempArr=temp.replace("^","#").split("#");
			              /*sBuffer.append("<div class='popUpDiv' id='statusOutDivId"+var+"' style='display: none; top: 250px; left: 250px;'>");
			              sBuffer.append("<table bgcolor='white'><tbody><tr><td>");
			              sBuffer.append("<div class='popup' style='display: block;'>");		              
			              sBuffer.append("<table width='400'>");
			              sBuffer.append("<tbody><tr class='HEADER'>");
			              sBuffer.append("<th align='left'>&nbsp;Status Details &gt;&gt;</th>");
			              sBuffer.append("<th align='right'><img style='cursor: pointer;' src='../../hisglobal/images/FrStopAutoHide.png' tabindex='1' onclick=\"getData('1','statusOutDivId"+var+"','2')\"></th></tr>");
			              for(int i=0;i<=tempArr.length-1;i=i+2)
			              {
			            	  sBuffer.append("<tr>");
				              sBuffer.append("<td class='LABEL'>"+tempArr[i]+"</td>");
				              sBuffer.append("<td class='CONTROL'>"+tempArr[i+1]+"</td>");
				              sBuffer.append("</tr>");
			              }
			              sBuffer.append("</tbody></table>");
			              sBuffer.append("<center><table border='0' cellpadding='0' cellspacing='0' width='300'>");
			              sBuffer.append("<tbody><tr>");
			              sBuffer.append("<td><center><img style='cursor: pointer;' src='../../hisglobal/images/btn-ok.png' tabindex='1' onclick=\"getData('1','statusOutDivId"+var+"','2')\"></center>");
			              sBuffer.append("</div></td></tr></tbody></table>");
			              sBuffer.append("</td></tr></tbody></table></center></div>");*/
			              
			              sBuffer.append("<div id='mainDiv1' style=''>");
							sBuffer.append("<div class='modal fade' id='searchModel1'   tabindex='-1' role='dialog' aria-labelledby='validateModalLabel' aria-hidden='true'>");
							sBuffer.append("<div class='modal-dialog' role='document'>");
							sBuffer.append("<div class='modal-content'>");
							sBuffer.append("<div class='modal-header'>");
							sBuffer.append("<h4 class='modal-title'>Status Details</h4>");
							sBuffer.append("</div>");
							sBuffer.append("<div  class='modal-body' id ='fetchRecordDivId'  >");
							    
				              
				              for(int i=0;i<=tempArr.length-1;i=i+2)
				              {
				            	  sBuffer.append("<div class='row rowFlex reFlex' >");
					              sBuffer.append("<div class='col-sm-5' style='border-right:1px dotted black; text-align: right;'>"+tempArr[i]+"</div>");
					              sBuffer.append("<div class='col-sm-7'>"+tempArr[i+1]+"</div>");
					              sBuffer.append("</div>");
				              }
			            	  
								sBuffer.append("</div>");
								sBuffer.append("</div>");
								sBuffer.append("</div>");
								sBuffer.append("</div>");
							    sBuffer.append("</div> ");
							  /*  if(var>0)
							    	 sBuffer.append("</div> ");*/
		              }
		              var++;
		            
	              }
	    	   
	    	   
	   		sBuffer.append("</div>");

			sBuffer.append("<a class='carousel-control-prev' href='#mov' data-slide='prev'>");
			sBuffer.append("<i class='fas fa-chevron-circle-left fa-w-16 fa-4x'></i>");
			sBuffer.append("</a>");
			sBuffer.append("<a class='carousel-control-next' href='#mov' data-slide='next'>");
			sBuffer.append("<i class='fas fa-chevron-circle-right fa-w-16 fa-4x'></i>");
			sBuffer.append("</a>");
			sBuffer.append("</div>");
			//sBuffer.append("</div> ");
			sBuffer.append("</div>");
    		sBuffer.append("</div>");
    		sBuffer.append("</div>");
    		sBuffer.append("</div>");
    		
		     }
			 catch(Exception e)
			 {
				 new HisException("Patient Tracking","PatientTrackingTransHLP.getMovementDetail()-->",e.getMessage());
		     }
		     return sBuffer.toString();
		 }
	
	public static String admissionList(PatientTrackingTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      WebRowSet ws=vo.getAdmissionListWS();
	      try
	      {
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");   
	              sBuffer.append("<tr><td  width='100%' colspan='4' class='TITLE'><div align='left'>Patient Admission List</div></td></tr>");
	              sBuffer.append("<tr>");
	              sBuffer.append("<td  width='5%' class='LABEL'><div align='center'></div></td>");
	              sBuffer.append("<td  width='25%' class='LABEL'><div align='center'>Admission No.</div></td>");
	              sBuffer.append("<td  width='25%' class='LABEL'><div align='center'>Admission Date/Time</div></td>");
	              sBuffer.append("<td  width='45%' class='LABEL'><div align='center'>Discharge Date/Time</div></td>");
	              sBuffer.append("</tr></table>");
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
	              if(ws.size()>0)
	              {
		              while (ws.next()) 
		              {
		            	 
		            	  sBuffer.append("<tr>");
			              sBuffer.append("<td  width='5%' class='CONTROL'><div align='left'><input type='radio' name='chk' value='"+ws.getString(1)+"' onclick='getMovDetails(this);'></div></td>");
			              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'>"+ws.getString(1)+"</div></td>");
			              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'>"+ws.getString(2)+"</div></td>");
			              sBuffer.append("<td  width='45%' class='CONTROL'><div align='center'>"+ws.getString(3)+"</div></td>");
			              sBuffer.append("</tr>");		              
		              }
	              }
	              else
	              {
	            	  sBuffer.append("<tr>");
		              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'><font color='red'>No Record Found</font></div></td>");
		              sBuffer.append("</tr>");	
	              }
	              sBuffer.append("</table>");
		     }
			 catch(Exception e)
			 {
				 new HisException("ADT","PatientTrackingTransHLP.admissionList()-->",e.getMessage());
		     }
		     return sBuffer.toString();
		 }
	
	public static String admissionList_BS(PatientTrackingTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      WebRowSet ws=vo.getAdmissionListWS();
	      try
	      {
	    	  
	    	  
	    	    /*sBuffer.append("<div class='card new'>");
	    	    sBuffer.append(" <h5 style='color: black;'><i class='fa fa-users'></i>&nbsp&nbsp<span class='middle'>Patient Admission List</span></h5>");
				sBuffer.append("<div class='row col-sm-12' style='background-color: #c4deff;'>");
				sBuffer.append("<div class='profile-info-name col-sm-1'><b>#</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-3'><b>Admission No.</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-4'><b>Admission Date/Time</b></div>");
				sBuffer.append("<div class='profile-info-name col-sm-4'><b>Discharge Date/Time</b></div>");
	            sBuffer.append("</div>");*/
	    	      sBuffer.append("<p class='subHeaders'><i class='fa fa-users' style='font-size: 26px;'>&nbsp;</i>Patient Admissions Details</p>");
	    	      sBuffer.append("<table id='dtable' class='table tdfont' style='line-height: 0.8;'>");
		          sBuffer.append("<thead>");
	              sBuffer.append("<tr>");
	              sBuffer.append("<th>#</th>");
	              sBuffer.append("<th>Admission No.</th>");
	              sBuffer.append("<th>Admission Date/Time</th>");
	              sBuffer.append("<th>Discharge Date/Time</th>");
	              sBuffer.append("</tr></thead>");
	              sBuffer.append("<tbody>");
	              if(ws.size()>0)
	              {
		              while (ws.next()) 
		              {		            	 
		            	  sBuffer.append("<tr>");
			              sBuffer.append("<td><input type='radio' name='chk' value='"+ws.getString(1)+"' onclick='getMovDetails_BS(this);'></td>");
			              sBuffer.append("<td>"+ws.getString(1)+"</td>");
			              sBuffer.append("<td>"+ws.getString(2)+"</td>");
			              sBuffer.append("<td>"+ws.getString(3)+"</td>");
			              sBuffer.append("</tr>");	
			             
			              
			              
			              /*sBuffer.append("<div class='row col-sm-12'>");
							sBuffer.append("<div class='profile-info-name col-sm-1'><input type='radio' name='chk'   value='"+ws.getString(1)+"' onclick='getMovDetails(this);'></div>");
							sBuffer.append("<div class='profile-info-name col-sm-3'>"+ws.getString(1)+"</div>");
							sBuffer.append("<div class='profile-info-name col-sm-4'>"+ws.getString(2)+"</div>");
							sBuffer.append("<div class='profile-info-name col-sm-4'>"+ws.getString(3)+"</div>");
				            sBuffer.append("</div>");*/

		              }
	              }
	              else
	              {
	            	  
	            	  sBuffer.append("<tr>");
		              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'><font color='red'>No Record Found</font></div></td>");
		              sBuffer.append("</tr>");	
	              }
	              sBuffer.append("</body>");
	              sBuffer.append("</table>");
	              
		            sBuffer.append("</div>");

		     }
			 catch(Exception e)
			 {
				 new HisException("ADT","PatientTrackingTransHLP.admissionList()-->",e.getMessage());
		     }
		     return sBuffer.toString();
		 }
}