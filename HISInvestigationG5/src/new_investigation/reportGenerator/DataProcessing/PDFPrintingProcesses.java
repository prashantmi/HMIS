 
package new_investigation.reportGenerator.DataProcessing;

 
 
import new_investigation.reportGenerator.DataHelper.Config;
import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.imageio.ImageIO;

public class PDFPrintingProcesses {

    String hospitalString = "<table  cellpadding='0' border='0' width='100%' style='border-spacing: 0px'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + Config.hospitalnameatreport + "</b></font></td></tr><tr><td align='center' width='100%' colspan='6'><font color='black' size='4'><b>" + Config.hospitaladdressatreport + "</b></font></td></tr>";
   // final static String lineString = "<tr><td bgcolor='black' colspan='6'><img src='grid.png' width='1' height='1' border='0'/></td></tr>";
    final static String lineString = "<tr><td bgcolor='black' colspan='13'></td></tr>";
   // final static String standardRangesString = "<tr><td colspan='13' width='100%'><table width='100%'><tr><td width='50%'><font color='black' size='3'><b>Investigation</b></font></td><td width='25%'><img src='grid.png' width='1' height='1' border='0'/></td><td align='center' width='9%'><div align='right'><font color='black' size='3'><b>Result</b></font></div></td><td width='16%'><div align='center'><font color='black' size='3'><b>Unit</b></font></div></td><td width='24%'><div align='center' ><font color='black' size='3'><b>Standard Ranges</b></font></div></td></tr></table></td></tr>";
    //final static String standardRangesString = "<tr><td colspan='13' width='100%'><table width='100%'><tr><td width='50%'><font color='black' size='3'><b>Investigation</b></font></td><td width='25%'></td><td align='center' width='9%'><div align='right'><font color='black' size='3'><b>Result</b></font></div></td><td width='16%'><div align='center'><font color='black' size='3'><b>Unit</b></font></div></td><td width='24%'><div align='center' ><font color='black' size='3'><b>Standard Ranges</b></font></div></td></tr></table></td></tr>";
   //making changes in unit and ref range as prs received july 7, 2016
    final static String standardRangesString = "<tr><td colspan='13' width='100%'><table width='100%'><tr><td width='30%'><font color='black' size='10pt'><b>Investigation</b></font><td colspan='1' width='40%' align='left' ><div align='left' style=' margin-left: 25px'><font color='black' size='10pt'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Result</b></font></div></td><td width='35%'><div align='left'><font color='black' size='10pt'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit</b></font></div></td><td width='15%'><div align='center' ><font color='black' size='10pt'><b>Ref. Range</b></font></div></td></tr></table></td></tr>";

 
     
    public static synchronized void getDefaultHeaderRegisteredPatient(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        StringBuffer headerString = new StringBuffer();
        Map<String,String> islabelprintindoubleline=new HashMap<String,String>();

        Map<String,String> mp=new HashMap<String,String>(); // map sample no wise append
        Map<String,String> mplabno=new HashMap<String,String>();

        
        for(ResultEntryVO resultEntryVO: resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy())
		{
        	 Map<String,String> mpgrp=new HashMap<String,String>(); // map sample no wise append
             
        	if(mp!=null && mp.size()>0)
                        {
                        	String key=resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getSampleCode()+"#"+resultEntryVO.getLaboratoryCode();
                            
                        	
                        	
                        	if(mp.containsKey(key))
                        	{
                        	      String sampleno=	mp.get(key);
                        	      
                        	    if(sampleno!=null &&  !sampleno.contains(resultEntryVO.getUserSampleNo()))
                        	      {
                        	    	
                        	    	  sampleno=sampleno+", "+resultEntryVO.getUserSampleNo(); 	
                        	      
                        	    	  if(!mpgrp.containsKey(resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getGroupCode()))
                                 	     mp.put(key, sampleno);

                        	      }
                        	    
                        	      
                        	}
                        	else
                        	   {
                        		if(!mpgrp.containsKey(resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getGroupCode()))
                            	mp.put(key, resultEntryVO.getUserSampleNo());
                        	
                        	   }
                        	
                        }
                        else
                        {
                        	
                        	String key=resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getSampleCode()+"#"+resultEntryVO.getLaboratoryCode();
                         
                        	if(!mpgrp.containsKey(resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getGroupCode()))
                           	 mp.put(key, resultEntryVO.getUserSampleNo());
                        
                        }
                        
                        
        	
                        if(mplabno!=null && mplabno.size()>0)
                        {
                        	String key=resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getSampleCode()+"#"+resultEntryVO.getLaboratoryCode();
                            
                        	if(mplabno.containsKey(key))
                        	{
                        	      String labno=	mplabno.get(key);
                        	      
                        	    if(!labno.contains(resultEntryVO.getLabNo()))
                        	      {
                        	    	
                        	    	  labno=labno+", "+resultEntryVO.getLabNo(); 	
                        	      
                        	    	  if(!mpgrp.containsKey(resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getGroupCode()))
                                        mplabno.put(key, labno);

                        	      }
                        	    
                        	      
                        	}
                        	else
                        	   {
                        		
                        		if(!mpgrp.containsKey(resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getGroupCode()))
                                 	mplabno.put(key, resultEntryVO.getLabNo());
                        	   
                        	   }
                        	
                        }
                        else
                        {
                        	
                        	String key=resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getSampleCode()+"#"+resultEntryVO.getLaboratoryCode();
                         
                        	
                        	if(!mpgrp.containsKey(resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getGroupCode()))
                           	   mplabno.put(key, resultEntryVO.getLabNo());
                        
                        }
                        
                        
                        
                        
                        if(resultEntryVO.getGroupCode()==null)
                    	{}
                    	else
                    	{
                    			  mpgrp.put(resultEntryVO.getRequisitionNo()+"#"+resultEntryVO.getGroupCode(), "1");
                    	}
                        
        	
		}

        //String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + Config.hospitalnameatreport + "</b></font></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + Config.hospitaladdressatreport + "</b></font></td></tr>";
       // String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + resultEntryVOGroupByValidatedBy.getHospitalName() + "</b></font></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + resultEntryVOGroupByValidatedBy.getHospitalCity() + "</b></font></td></tr>";
       // String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><table><tr><td width='10%' rowspan='2'><img src='nims.gif'/></td><td width='90%' align='center'><font color='black' size='5'><b>" + resultEntryVOGroupByValidatedBy.getHospitalName() + "</b></font></td></tr></table></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + resultEntryVOGroupByValidatedBy.getHospitalCity() + "</b></font></td></tr>";
       int noLinesPresent=0;// to subtract from header height if hos add not present
        String reportLogoPath = PropertiesHelper.getReportLogoPath();
        String reportHeaderPath = PropertiesHelper.getReportHeaderPath();
    	String NablLogo = PropertiesHelper.getNablLogoPath();
         String hospitalString = "";
         String addressMetaData = (((resultEntryVOGroupByValidatedBy.getHosAdd1().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd1().equals(" "))) ? "":(resultEntryVOGroupByValidatedBy.getHosAdd1()+"<br>")) +
        		 (((resultEntryVOGroupByValidatedBy.getHosAdd2().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd2().equals(" "))) ? "":(resultEntryVOGroupByValidatedBy.getHosAdd2()+"<br>")) +
         		(((resultEntryVOGroupByValidatedBy.getHospitalCity().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalCity().equals(" "))) ? "":(resultEntryVOGroupByValidatedBy.getHospitalCity()+","))+
         		resultEntryVOGroupByValidatedBy.getHosDistrict()+
         		((resultEntryVOGroupByValidatedBy.getHosPin().equals("")) ? "":("-" + resultEntryVOGroupByValidatedBy.getHosPin()+","))+
         		((resultEntryVOGroupByValidatedBy.getHosState().equals("")) ? "":(resultEntryVOGroupByValidatedBy.getHosState()+","))+
         		" INDIA"+
         		"<br>" +
         		(((resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(" "))) ? "":("Phone :"+resultEntryVOGroupByValidatedBy.getHospitalPhone()+" "))+
         		(((resultEntryVOGroupByValidatedBy.getHosFax().equals(""))||(resultEntryVOGroupByValidatedBy.getHosFax().equals(" "))) ? "":("Fax :"+resultEntryVOGroupByValidatedBy.getHosFax()+" "))+
         		(((resultEntryVOGroupByValidatedBy.getHosEmail().equals(""))||(resultEntryVOGroupByValidatedBy.getHosEmail().equals(" "))) ? "":("Email :"+resultEntryVOGroupByValidatedBy.getHosEmail()));
         
         String deptName = "<tr align='center'><td colspan='13' width='100%'><font color='black' size='10pt'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>";
       
         if(((resultEntryVOGroupByValidatedBy.getHosAdd2().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd2().equals(" "))))
        	 	noLinesPresent++;
         if(((resultEntryVOGroupByValidatedBy.getHosAdd1().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd1().equals(" "))))
        	 noLinesPresent++;
     
        //comment by ashu
         
         
         
        	 
         
         addressMetaData = (((resultEntryVOGroupByValidatedBy.getHosAdd1().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd1().equals(" "))) ? (" ") :" " ) +
        		 (((resultEntryVOGroupByValidatedBy.getHosAdd2().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd2().equals(" "))) ? (" ") :" " ) +
         		(((resultEntryVOGroupByValidatedBy.getHospitalCity().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalCity().equals(" "))) ? (" ") :" " ) +
         		(((resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(" "))) ? ("<br>") :"<br>" )+
         		(((resultEntryVOGroupByValidatedBy.getHosFax().equals(""))||(resultEntryVOGroupByValidatedBy.getHosFax().equals(" "))) ? ("<br>") :"<br>" )+
         		(((resultEntryVOGroupByValidatedBy.getHosEmail().equals(""))||(resultEntryVOGroupByValidatedBy.getHosEmail().equals(" "))) ? (" ") :" " );
         
        //Log(Level.INFO, " isPortal : "+isPortal+ " reportHeaderPath : "+reportHeaderPath);
        
         
         
         
         
         ///////////////////chanduuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuunewwwwwwwwwwwwwwww radiology header 
         
         if(resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged()!=null && !resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged().equals("0"))
         {
        	 
         if(reportHeaderPath != null && reportHeaderPath != "")
         {
        	  
        	 
        	 
             headerString.append("<table><tr><td><img src='" + reportHeaderPath + "' width='530' height='70'></td></tr></table>");
      //       Log(Level.INFO, "Initial headerString0  : "+headerString.toString());
        	 
        	 hospitalString = "<table  cellpadding='0' border='0' width='100%' style='border-spacing: 0px'><tr><td align='center' width='100%' colspan='13'>" + 
            		"<table><tr>"+
            		"<td width='90%' align='center'><font color='black' size='5'><b>" 
            		+ " " +             		
            		"</b>"+
            		"</font>"+
            		"<br>" + 
            		"<font color='black' size='3'><b>" + 
            		addressMetaData +
            		" " +
            	    "</b></font></td></tr>"
            	   
            	    + "</table></td></tr>";
            
            
            	    
         }
         else {
             
        	 
        	 
        	 hospitalString = "<table  cellpadding='0' border='0' width='100%' style='border-spacing: 0px'><tr><td align='center' width='100%' colspan='13'>" + 
             		"<table><tr><td width='10%' rowspan='2'></td>"+
             		"<td width='90%' align='center'><font color='black' size='5'><b>" 
             		+ " " +             		
             		"</b>"+
             		"</font>"+
             		"<br>" + 
             		"<font color='black' size='3'><b>" + 
             		addressMetaData +
             		" " +
             	    "</b></font></td></tr>"
             	   
             	    + "</table></td></tr>";
         }
       
        //Log(Level.INFO, "Commented Header Part in PDFPrintingProcesses-Test3");
        
        headerString.append(hospitalString);
         
        // Log(Level.INFO, "Commented Header Part in PDFPrintingProcesses-10011");

         
         
       
       // headerString.append(deptName);
//        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='4'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>");
        
        //headerString.append("<tr align='right'><td colspan='13' width='100%'><img src='"+ reportHeaderPath +"' height='50' width='100%'/></td></tr>");
        //headerString.append("<tr><td align='left' colspan='1'><div align='left'  ><font color='black' size='2'><b></b></font></div></td><td align='right' colspan='12'><div align='right' > <img src='" + reportHeaderPath + "' height='50' width='100%'/></div></td></tr>");
        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='10pt'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>");
        //headerString.append("<tr><td  colspan='6'><font color='black' size='3'><b>Laboratory :</b>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
        headerString.append("<tr><td  colspan='6'><font color='black' size='9pt'>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
        
         
 
       // Log(Level.INFO, "value of change is "+resultEntryVOGroupByValidatedBy.getChangeCount());
        
        if(resultEntryVOGroupByValidatedBy.getIsDeptEntry().equals("1"))
       	 headerString.append("<td align='right' colspan='7'><font color='black' size='3'><b>Department Report</b>");
      
        else  if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1"))
        	 headerString.append("<td align='right' colspan='7'><font color='black' size='3'><b>Status :</b>"+ resultEntryVOGroupByValidatedBy.getChangeCount() +" Amendment/Addendum Report</td></tr>");
       else
        	 headerString.append("<td align='right' colspan='7'></td></tr>");
        
        
        
         
        setPageHeaderHeight(resultEntryVOGroupByValidatedBy);
        headerString.append(lineString);

        //subtract from header height according to the hos lines not present
        resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - (noLinesPresent*15)));
        //Log(Level.INFO, noLinesPresent + "no liness");
        
        //headerString.append("<tr><td width='100%' colspan='13'><table width='100%'><tr>");
        //headerString.append("<td width='10%'><div align='left'><font size='2'><b>CR No</b></font></div></td>");
        //headerString.append("<td width='20%'><div align='left'>:&nbsp;<font size='2'>"+resultEntryVOGroupByValidatedBy.getPatCRNo()+"</font></div></td>");
        //headerString.append("<td width='10%'><div align='left'><font size='2'><b>Admission No</b></font></div></td>");
        //headerString.append("<td width='60%'><div align='left'>:&nbsp;<font size='2'>"+resultEntryVOGroupByValidatedBy.getPatAdmissionNo()+"</font></div></td>");
        //headerString.append("</tr></table></td></tr>");
        if (resultEntryVOGroupByValidatedBy.getPatAdmissionNo() != null) {
            headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>CR No</b></font></div></td>");
            headerString.append("<td width='57%' colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
            headerString.append("<td width='15%'><div align='left'><font size='2'><b>Admission No</b></font></div></td>");
            headerString.append("<td width='18%'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatAdmissionNo() + "</font></div></td>");
            headerString.append("</tr>");
        } else {

            
            
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
             
        	
        	 headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>CR No</b></font></div></td>");
             headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
             //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
             
             if(resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged()==null || resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged().equals("0"))
             headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Lab/Study No.</b></font></div></td>");
             else
            	 headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Accession No.</b></font></div></td>");	 
             
             
         	String key=resultEntryVOGroupByValidatedBy.getRequisitionNo()+"#"+resultEntryVOGroupByValidatedBy.getSampleCode()+"#"+resultEntryVOGroupByValidatedBy.getLaboratoryCode();

      	   
      	   
         	 if(mplabno!=null && mplabno.containsKey(key))
      	   {
    	      String labno=	mplabno.get(key);
    	      if(labno!=null)
    	     resultEntryVOGroupByValidatedBy.setLabNo(labno);
    	      else
    	      {
    	    	 if(mp!=null && mp.containsKey(key))
        	   {
    	    		String labno1=	mp.get(key);
          	      if(labno1!=null)
          	     resultEntryVOGroupByValidatedBy.setLabNo(labno1); 
        	   }
    	      }
    	      
      	   }
         	 else if(mp!=null && mp.containsKey(key))
      	   {
  	    		String labno1=	mp.get(key);
        	      if(labno1!=null)
        	     resultEntryVOGroupByValidatedBy.setLabNo(labno1); 
      	   }
         	 
             headerString.append("<td colspan='2'><div align='left'> <font size='2'>:&nbsp;" + resultEntryVOGroupByValidatedBy.getLabNo() + "</font></div></td>");
             
             if(resultEntryVOGroupByValidatedBy.getLabNo()!=null && resultEntryVOGroupByValidatedBy.getLabNo()!=null && resultEntryVOGroupByValidatedBy.getLabNo().length()>16)
             {    islabelprintindoubleline.put("lab/accessno", "1");
                islabelprintindoubleline.put("lab/accessno1", "1");
             }
             
             headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Procedure Date</b></font></div></td>");
             
             String collDate = resultEntryVOGroupByValidatedBy.getCollDate();
             if (collDate == null || collDate == "null") {
             	if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
             		collDate = "--";
             	else if(resultEntryVOGroupByValidatedBy.getPatAcceptanceDate() != null)
             		collDate = resultEntryVOGroupByValidatedBy.getPatAcceptanceDate();
             	else
             		collDate = "--";
             }
             
            
             headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + collDate + "</font></div></td>");
 			
             
             headerString.append("</tr>");
        }
            
        //added by chandan
        headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Patient Name</b></font></div></td>");
        headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatName() + "</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
     

        if(resultEntryVOGroupByValidatedBy.getPatName().trim().length()>21)
        {
        	islabelprintindoubleline.put("patname", "1");
            islabelprintindoubleline.put("patname1", "1");
        }
        
        headerString.append("<td colspan='2''><div align='left'><font size='2'><b>Age/Sex</b></font></div></td>");
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatAge() + "/" + resultEntryVOGroupByValidatedBy.getPatGenderShortName() + "</font></div></td>");
      
        headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Reporting Date</b></font></div></td>");
      
        String reportDate = resultEntryVOGroupByValidatedBy.getResultValidationDate();
        if (reportDate == null || reportDate == "null") {
        	reportDate = "--";
        }
        
        headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + reportDate + "</font></div></td>");
        headerString.append("</tr>");
        
        //comment by chandan
         
        
        
        
       

        if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
        {
        headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Sample Type/No</b></font></div></td>");
        if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
        {
        	
        	String key=resultEntryVOGroupByValidatedBy.getRequisitionNo()+"#"+resultEntryVOGroupByValidatedBy.getSampleCode()+"#"+resultEntryVOGroupByValidatedBy.getLaboratoryCode();

     	   if(mp!=null && mp.containsKey(key))
       	   {
     	      String sampleno=	mp.get(key);
     	     resultEntryVOGroupByValidatedBy.setUserSampleNo(sampleno);
       	   }
     	   
        	headerString.append("<td colspan='3'><div align='left'> <font size='2'>:&nbsp;" + resultEntryVOGroupByValidatedBy.getSampleName() + "/" + resultEntryVOGroupByValidatedBy.getUserSampleNo() + "</font></div></td>");
        

            if((resultEntryVOGroupByValidatedBy.getSampleName()+resultEntryVOGroupByValidatedBy.getUserSampleNo()).length()>21)
            {
            	islabelprintindoubleline.put("sampletype_ward", "1");
            	islabelprintindoubleline.put("sampletype_ward1", "1");
            }
        }else
        {
        	headerString.append("<td colspan='3'><div align='left'> <font size='2'>:&nbsp;" + resultEntryVOGroupByValidatedBy.getSampleName() + "</font></div></td>");
        

            if((resultEntryVOGroupByValidatedBy.getSampleName()).length()>21)
            {
            	islabelprintindoubleline.put("sampletype_ward", "1");
            	islabelprintindoubleline.put("sampletype_ward1", "1");
            }
        }

        
        
        }
        else
        {
        	if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
        	
            	headerString.append("<tr><td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
                headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
                
        	}
        	else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {
        		
        		headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Ward</b></font></div></td>");
                headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
                
              
                
        	}

        	if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
            	
            	headerString.append("<tr><td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
                headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
                
        	}if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
        	
            	headerString.append("<tr><td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
                headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
                
        	}
        	
        }
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
       
        
        
        if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) 
        	{      
        	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()!=null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("1"))
            {
        	
        		 headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Room/Bed</b></font></div></td>");
        	        headerString.append("<td  colspan='6'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td></tr>");
        	       
        	        
            }
        	
        	else
        	{
        headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Room/Bed</b></font></div></td>");
        headerString.append("<td  colspan='2'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
        	}
        	
        	}
        	else
        	{
        		
        		  headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Dept/Unit</b></font></div></td>");
        	        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatDeptUnit() + "</font></div></td>");
        	       
        	        if(resultEntryVOGroupByValidatedBy.getPatDeptUnit().length()>16)
                        {
        	        	islabelprintindoubleline.put("deptunit", "1");
                        islabelprintindoubleline.put("deptunit1", "1");
                        }
        	        
        	        if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3") || resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4") || resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) 
                	{  
        	        if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
        	        {
        	        	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()!=null && resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("1"))
        	            {
        	        	headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
        	            headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
        	           
        	            headerString.append("</tr><tr><td colspan='9'></td>");
            	        
        	            }
        	        	
        	            }
        	        else
        	        {
        	        	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()!=null && resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("1"))
        	            {
        	        headerString.append("<td colspan='2'></td>");
        	        headerString.append("<td colspan='2'></td>");
        	        headerString.append("</tr><tr><td colspan='9'></td>");
        	                	        
        	            }
        	        	
        	            }
                	}
        	//	 headerString.append("<td colspan='2'></td>");
              //   headerString.append("<td colspan='2'></td>");
 //                headerString.append("<td colspan='2'></td>");	
        	}
 
      //  headerString.append("<td colspan='1'></td></tr>");
    	
		
        
        //headerString.append("<td colspan='2''><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
       // String clinicianName = resultEntryVOGroupByValidatedBy.getClinicianName();
        
      //  headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + clinicianName + "</font></div></td>");
      
        if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
        {
        	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
            {
        	headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
            headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
            }
        	
            }
        else
        {
        	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
            {
        headerString.append("<td colspan='2'></td>");
        headerString.append("<td colspan='2'></td>");
            }
        	
            }
        
        
       // headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Reporting Date</b></font></div></td>");
      //  String reportDate = resultEntryVOGroupByValidatedBy.getResultValidationDate();
     //   if (reportDate == null || reportDate == "null") {
    //    	reportDate = "--";
    //    }
    //    headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + reportDate + "</font></div></td>");
       
        
        if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
        {
        headerString.append("</tr>");
        }
        
        
        if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) 
    	{  
        	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
            {
        headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Dept/Unit</b></font></div></td>");
        headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatDeptUnit() + "</font></div></td>");
    	}
        	else
        	{
        		headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Dept/Unit</b></font></div></td>");
                headerString.append("<td colspan='7'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatDeptUnit() + "</font></div></td>");
            		
                if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
                {
                	headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
                    headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
                    
                	
                    }
                else
                {
                	
                	headerString.append("<td colspan='2'></td>");
                    headerString.append("<td colspan='2'></td>");
                    
                    
                }
                
        	}
    	}
        else
        {
        	headerString.append("<tr><td colspan='2'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td colspan='3'><div align='left'> <font size='2'></font></div></td>");
        		
        }
        
        if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
        {
        headerString.append("<td  colspan='2'><div align='left'><font size='2'></div></td>");
        headerString.append("<td  colspan='5'><div align='left'> <font size='2'></font></div></td>");
        headerString.append("<td colspan='1'></td></tr>");
        }
        
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        //comment by chandan
        

        
     
        
        
        
        
        
        
        
        
         
        //headerString.append("<tr>");
        //headerString.append("<td width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
        //headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>"+resultEntryVOGroupByValidatedBy.getClinicianName()+"</font></div></td>");	
        //headerString.append("</tr>");
         

        String labNoString = "";
        if (resultEntryVOGroupByValidatedBy.getLabNoList() != null && resultEntryVOGroupByValidatedBy.getLabNoList().size() != 0) {
            labNoString = "";
            for (int i = 0; i < resultEntryVOGroupByValidatedBy.getLabNoList().size(); i++) {
                if (i == 0) {
                    labNoString = resultEntryVOGroupByValidatedBy.getLabNoList().get(i);
                } else {
                    labNoString += "," + resultEntryVOGroupByValidatedBy.getLabNoList().get(i);
                }
            }

        }

         
        //Siddharth
       
         
        //}
      
        
        // comment by chandan
        
        
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        headerString.append("<tr>");
       
         //end
         
         
       
        


        //column header should be removed in case of dynamic Report template.Bug 12237.
        //not required for laboratory radiology
        //this condition needs to be replaced by the flag for particular lab
        if(resultEntryVOGroupByValidatedBy.getDisplayHeader().equals("0"))
        {
        	if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                headerString.append(lineString);
               
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
            } else {
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            }
        	
        	
        }
        else
        {
        if(resultEntryVOGroupByValidatedBy.getIsdynamicGroupTemplate().equals("0") && resultEntryVOGroupByValidatedBy.getIsdynamicTestTemplate().equals("0"))
        {
        if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
            headerString.append(lineString);
            headerString.append(standardRangesString);
            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 4));
            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 25));
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
        } else {
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
        }
        }
        else
        {
            if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                headerString.append(lineString);
                headerString.append(standardRangesString);
              //  resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            } else {
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            }
        	
        	
        	
        }
        }
        //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 2));
        resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 12));
        headerString.append(lineString);
        headerString.append("</table>");
    }
         else
        	 
         {
         
         
         
         ///chanduuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu
         
         if(reportHeaderPath != null && reportHeaderPath != "")
         {
        	  
        	 
        	 
             headerString.append("<table><tr><td><img src='" + reportHeaderPath + "' width='530' height='70'></td></tr></table>");
        //     Log(Level.INFO, "Initial headerString0  : "+headerString.toString());
        	 
        	 hospitalString = "<table  cellpadding='0' border='0' width='100%' style='border-spacing: 0px'><tr><td align='center' width='100%' colspan='13'>" + 
            		"<table><tr>"+
            		"<td width='90%' align='center'><font color='black' size='5'><b>" 
            		+ " " +             		
            		"</b>"+
            		"</font>"+
            		"<br>" + 
            		"<font color='black' size='3'><b>" + 
            		addressMetaData +
            		" " +
            	    "</b></font></td></tr>"
            	   
            	    + "</table></td></tr>";
            
            
            	    
         }
         else {
             
        	 
        	 
        	 hospitalString = "<table   cellpadding='0' border='0' width='100%' style='border-spacing: 0px'><tr><td align='center' width='100%' colspan='13'>" + 
             		"<table><tr><td width='10%' rowspan='2'></td>"+
             		"<td width='90%' align='center'><font color='black' size='5'><b>" 
             		+ " " +             		
             		"</b>"+
             		"</font>"+
             		"<br>" + 
             		"<font color='black' size='3'><b>" + 
             		addressMetaData +
             		" " +
             	    "</b></font></td></tr>"
             	   
             	    + "</table></td></tr>";
         }
       
        //Log(Level.INFO, "Commented Header Part in PDFPrintingProcesses-Test3");
        
        headerString.append(hospitalString);
         
        // Log(Level.INFO, "Commented Header Part in PDFPrintingProcesses-10011");

         
         
       
       // headerString.append(deptName);
//        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='4'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>");
        
        //headerString.append("<tr align='right'><td colspan='13' width='100%'><img src='"+ reportHeaderPath +"' height='50' width='100%'/></td></tr>");
        //headerString.append("<tr><td align='left' colspan='1'><div align='left'  ><font color='black' size='2'><b></b></font></div></td><td align='right' colspan='12'><div align='right' > <img src='" + reportHeaderPath + "' height='50' width='100%'/></div></td></tr>");
        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='12pt'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>");
        //headerString.append("<tr><td  colspan='6'><font color='black' size='3'><b>Laboratory :</b>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
        headerString.append("<tr><td  colspan='6'><font color='black' size='11pt'>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
        
         
 
       // Log(Level.INFO, "value of change is "+resultEntryVOGroupByValidatedBy.getChangeCount());
        
        if(resultEntryVOGroupByValidatedBy.getIsDeptEntry().equals("1"))
       	 headerString.append("<td align='right' colspan='7'><font color='black' size='11pt'><b>Department Report</b>");
      
        else  if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1"))
        	 headerString.append("<td align='right' colspan='7'><font color='black' size='11pt'><b>Status :</b>"+ resultEntryVOGroupByValidatedBy.getChangeCount() +" Amendment/Addendum Report</td></tr>");
       else
        	 headerString.append("<td align='right' colspan='7'></td></tr>");
        
        
        
         
        setPageHeaderHeight(resultEntryVOGroupByValidatedBy);
        headerString.append(lineString);

        //subtract from header height according to the hos lines not present
        resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - (noLinesPresent*15)));
        //Log(Level.INFO, noLinesPresent + "no liness");
        
        //headerString.append("<tr><td width='100%' colspan='13'><table width='100%'><tr>");
        //headerString.append("<td width='10%'><div align='left'><font size='2'><b>CR No</b></font></div></td>");
        //headerString.append("<td width='20%'><div align='left'>:&nbsp;<font size='2'>"+resultEntryVOGroupByValidatedBy.getPatCRNo()+"</font></div></td>");
        //headerString.append("<td width='10%'><div align='left'><font size='2'><b>Admission No</b></font></div></td>");
        //headerString.append("<td width='60%'><div align='left'>:&nbsp;<font size='2'>"+resultEntryVOGroupByValidatedBy.getPatAdmissionNo()+"</font></div></td>");
        //headerString.append("</tr></table></td></tr>");
        
        if( ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("37913") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) ) ) //10010,10010
       	{	
        	
//chanksssssssssssssssss
        	
        	
       	 if (resultEntryVOGroupByValidatedBy.getPatAdmissionNo() != null) {
                headerString.append("<tr><td width='10%'><div align='left'><font size='8.5pt'><b>CR No</b></font></div></td>");
                headerString.append("<td width='57%' colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
                //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
                headerString.append("<td width='15%'><div align='left'><font size='8.5pt'><b>Admission No</b></font></div></td>");
                headerString.append("<td width='18%'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatAdmissionNo() + "</font></div></td>");
                headerString.append("</tr>");
            } else {

                
                
                //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
                 
            	
            	 headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>CR No</b></font></div></td>");
                 headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
                 //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
                 
                 
                 headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Sample No.</b></font></div></td>");
                 if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
                 	{
                 	
                 	String key=resultEntryVOGroupByValidatedBy.getRequisitionNo()+"#"+resultEntryVOGroupByValidatedBy.getUserSampleNo()+"#"+resultEntryVOGroupByValidatedBy.getLaboratoryCode();

              	   if(mp!=null && mp.containsKey(key))
                	   {
              	      String sampleno=	mp.get(key);
              	     resultEntryVOGroupByValidatedBy.setUserSampleNo(sampleno);
                	   }
              	   
                 	headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getUserSampleNo()  + "</font></div></td>");
                 	

                     if((resultEntryVOGroupByValidatedBy.getUserSampleNo()).length()>15)
                     { 
                     	islabelprintindoubleline.put("sampletype_ward1no", "1");
                     islabelprintindoubleline.put("sampletype_ward11no", "1");
                     }
                     
                 	} else
                 { 	
                 	headerString.append("<td colspan='2'><div align='left'> <font size='8.5pt'>:" + "NA" + "</font></div></td>");
                 

                 
                 }	
                 
                 
               
                 
                 if(resultEntryVOGroupByValidatedBy.getIsreportcollectionlabelchanged()==null || resultEntryVOGroupByValidatedBy.getIsreportcollectionlabelchanged().equals("0"))
                     headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Collection Date</b></font></div></td>");
                     else
                     	headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Study Date</b></font></div></td>");
                 
                  
                 
                 String collDate = resultEntryVOGroupByValidatedBy.getCollDate();
                 if (collDate == null || collDate == "null") {
                 	if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
                 		collDate = "--";
                 	else if(resultEntryVOGroupByValidatedBy.getPatAcceptanceDate() != null)
                 		collDate = resultEntryVOGroupByValidatedBy.getPatAcceptanceDate();
                 	else
                 		collDate = "--";
                 }
                 
                 headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + collDate + "</font></div></td>");
     			
                 
                 headerString.append("</tr>");
            }
                
            //added by chandan
            headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Patient Name</b></font></div></td>");
            headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatName() + "</font></div></td>");
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
         
            if(resultEntryVOGroupByValidatedBy.getPatName().trim().length()>24)
                {
            	islabelprintindoubleline.put("patname", "1");
                islabelprintindoubleline.put("patname1", "1");

                }
            headerString.append("<td colspan='2''><div align='left'><font size='8.5pt'><b>Age/Sex</b></font></div></td>");
            headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatAge() + "/" + resultEntryVOGroupByValidatedBy.getPatGenderShortName() + "</font></div></td>");
       
            //if(resultEntryVOGroupByValidatedBy.getIsreportcollectionlabelchanged()==null || resultEntryVOGroupByValidatedBy.getIsreportcollectionlabelchanged().equals("0"))
            //headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Coll./Study Date</b></font></div></td>");
            //else
            	
            headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Acceptance Date</b></font></div></td>");
            
            String collDate = resultEntryVOGroupByValidatedBy.getAcceptanceDate();
            if (collDate == null || collDate == "null") {
            	if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
            		collDate = "--";
            	else if(resultEntryVOGroupByValidatedBy.getAcceptanceDate() != null)
            		collDate = resultEntryVOGroupByValidatedBy.getAcceptanceDate();
            	else
            		collDate = "--";
            }
            
            headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + collDate + "</font></div></td>");
            headerString.append("</tr>");
            
            //comment by chandan
             
            
            
            
           

            if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
            {
            headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Sample Type</b></font></div></td>");
            if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
            	{
            	
            	String key=resultEntryVOGroupByValidatedBy.getRequisitionNo()+"#"+resultEntryVOGroupByValidatedBy.getSampleName()+"#"+resultEntryVOGroupByValidatedBy.getLaboratoryCode();

         	   if(mp!=null && mp.containsKey(key))
           	   {
         	      String sampleno=	mp.get(key);
         	     resultEntryVOGroupByValidatedBy.setUserSampleNo(sampleno);
           	   }
         	   
            	headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getSampleName()  + "</font></div></td>");
            	

                if((resultEntryVOGroupByValidatedBy.getSampleName()).length()>24)
                { islabelprintindoubleline.put("sampletype_ward", "1");
                islabelprintindoubleline.put("sampletype_ward1", "1");
                }
                
            	} else
            { 	
            	headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getSampleName() + "</font></div></td>");
            

            if((resultEntryVOGroupByValidatedBy.getSampleName()).length()>24)
            {
            //    islabelprintindoubleline.put("sampletype_ward", "1");
           // islabelprintindoubleline.put("sampletype_ward1", "1");
            }
            
            }
            
            }
            else
            {
            	
           	 if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
            	
                	headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Ward/OPD</b></font></div></td>");
                    headerString.append("<td  colspan='3'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
                    
            	}
            	else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {
            		
            		headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Ward</b></font></div></td>");
                    headerString.append("<td  colspan='3'><div align='left'>: <font size='8.5pt'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
                    
                    if((resultEntryVOGroupByValidatedBy.getWardName()).length()>16)
                    {
                //    	islabelprintindoubleline.put("sampletype_ward", "1");
                //        islabelprintindoubleline.put("sampletype_ward1", "1");
                        
                    }
                    
            	}

            	
            }
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
           
            
            
            
            
            
            
            
            
            if(resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged()==null || resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged().equals("0"))
                headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Lab No.</b></font></div></td>");
                else
               	 headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Accession No.</b></font></div></td>");	
                
                String key=resultEntryVOGroupByValidatedBy.getRequisitionNo()+"#"+resultEntryVOGroupByValidatedBy.getSampleCode()+"#"+resultEntryVOGroupByValidatedBy.getLaboratoryCode();

           	   
           	   
                if(mplabno!=null && mplabno.containsKey(key))
         	   {
       	      String labno=	mplabno.get(key);
       	      if(labno!=null)
       	     resultEntryVOGroupByValidatedBy.setLabNo(labno);
       	      else
       	      {
       	    	 if(mp!=null && mp.containsKey(key))
           	   {
       	    		String labno1=	mp.get(key);
             	      if(labno1!=null)
             	     resultEntryVOGroupByValidatedBy.setLabNo(labno1); 
           	   }
       	      }
       	      
         	   }
                else if(mp!=null && mp.containsKey(key))
         	   {
     	    		String labno1=	mp.get(key);
           	      if(labno1!=null)
           	     resultEntryVOGroupByValidatedBy.setLabNo(labno1); 
         	   }
                
                
                headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getLabNo() + "</font></div></td>");
             
                if(resultEntryVOGroupByValidatedBy.getLabNo().length()>15)
                {  islabelprintindoubleline.put("lab/accessno", "1");
                islabelprintindoubleline.put("lab/accessno1", "1");
                }
                
            
            
            headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Reporting Date</b></font></div></td>");
            String reportDate = resultEntryVOGroupByValidatedBy.getResultValidationDate();
            if (reportDate == null || reportDate == "null") {
            	reportDate = "--";
            }
            headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + reportDate + "</font></div></td>");
           
            
            
            headerString.append("</tr>");
            
            
            
        
            
            //headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Dept/Unit</b></font></div></td>");
            //headerString.append("<td colspan='7'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatDeptUnit() + "</font></div></td>");
            
            String referred_dcotor=resultEntryVOGroupByValidatedBy.getAdvicedby()==null?"NA":resultEntryVOGroupByValidatedBy.getAdvicedby();
            
            headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Referred Doctor</b></font></div></td>");
            headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + referred_dcotor + "</font></div></td>");
            

            if((referred_dcotor!=null && !referred_dcotor.equals("") && !referred_dcotor.equals("-") && !referred_dcotor.equals("NA")))
            {
           	 
           	   if(referred_dcotor.length()>24)
                  {
           		   islabelprintindoubleline.put("sampletype_ward1ref", "1");
                      islabelprintindoubleline.put("sampletype_ward11ref", "1");

                  }
           	   
          
            }
            
            if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) 
            {

            	   headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Patient Type</b></font></div></td>");
                   headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>" + "IPD"  + "</font></div></td>");
                   
                   if((resultEntryVOGroupByValidatedBy.getWardName()).length()>16)
                       {
                	  //    	islabelprintindoubleline.put("sampletype_ward", "1");
                      //     islabelprintindoubleline.put("sampletype_ward1", "1");
                       }
                       
                   
            }
            else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
                
        		headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Patient Type</b></font></div></td>");
                   headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
            }
            else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
                // headerString.append("<tr>");//emergency case. modify as reqd.
                 headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Patient Type</b></font></div></td>");
                 headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
            }
            else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
                // headerString.append("<tr>");//special case. modify as reqd.
                 headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Patient Type</b></font></div></td>");
                 headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
            }
            
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
            
            //comment by chandan
            

            
         
            
            
            
            
            
            
            
            
             
            //headerString.append("<tr>");
            //headerString.append("<td width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
            //headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>"+resultEntryVOGroupByValidatedBy.getClinicianName()+"</font></div></td>");	
            //headerString.append("</tr>");
             

            String labNoString = "";
            if (resultEntryVOGroupByValidatedBy.getLabNoList() != null && resultEntryVOGroupByValidatedBy.getLabNoList().size() != 0) {
                labNoString = "";
                for (int i = 0; i < resultEntryVOGroupByValidatedBy.getLabNoList().size(); i++) {
                    if (i == 0) {
                        labNoString = resultEntryVOGroupByValidatedBy.getLabNoList().get(i);
                    } else {
                        labNoString += "," + resultEntryVOGroupByValidatedBy.getLabNoList().get(i);
                    }
                }

            }

             
            //Siddharth
           
             
            //}
            if (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis() != null) {
                int multiplFactor = (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis().length() / 84) + ((resultEntryVOGroupByValidatedBy.getCurrentDiagnosis().length() % 84 == 0) ? 0 : 1);
        
                
                 
                if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
                {
                	headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Addendum Date</b></font></div></td>");
                    headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
                }
                else
                {
                headerString.append("<td colspan='2'></td>");
                headerString.append("<td colspan='2'></td>");
                }

                 
                
                 
                 headerString.append("</tr>");
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + (10 * multiplFactor))); //added this line whenever add new tr
            } else {
                headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Diagnosis</b></font></div></td><td  colspan='1'><div align='left'>: <font size='2'></font></div></td>");
               
                
                if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
                {
                	headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Addendum Date</b></font></div></td>");
                    headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
                }
                else
                {
                headerString.append("<td colspan='2'></td>");
                headerString.append("<td colspan='3'></td>");
                }

                 
                headerString.append("</tr>");
                
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10)); //added this line whenever add new tr
            }
            
            // comment by chandan
            
            
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
            
         //   headerString.append("<tr>");
            if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {

           	 
            	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
            	{
             //   headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Ward</b></font></div></td>");
              //  headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
                 
            //    headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Room/Bed</b></font></div></td>");
           //     headerString.append("<td  colspan='5'><div align='left'>: <font size='8.5pt'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
                
            //    headerString.append("<td colspan='2'></td>");
             //   headerString.append("<td  colspan='3'></td>");
                  
                
             //   headerString.append("<td colspan='1'></td></tr>");
            	}
            	else
            	{
            		//  headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Room/Bed</b></font></div></td>");
               //       headerString.append("<td  colspan='5'><div align='left'>: <font size='8.5pt'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
                ///      headerString.append("<td colspan='6'></td></tr>");
                  		
            		
            	}
                 
                
                           //headerString.append("</tr>");
                
           	// end testing header
           	
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 20));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 80));
           } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
             //  headerString.append("<tr>");
        	   if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))

        		   {
        		   
        	//	   headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
             //  headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
        		   headerString.append("<td colspan='2'></td>");
                   headerString.append("<td  colspan='3'></td>");
                 
        		   headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td></tr>");
        		   }
        	   
        	    
              
               
               
              headerString.append("</tr>");
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));
           } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
              // headerString.append("<tr>");//emergency case. modify as reqd.
             //  headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
               //headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td  colspan='3'></td>");
             
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td></tr>");
                
               
                
               
               headerString.append("</tr>");
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

           }  else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
              // headerString.append("<tr>");//special case. modify as reqd.
             //  headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
              // headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
        	   headerString.append("<td colspan='2'></td>");
               headerString.append("<td  colspan='3'></td>");
             
        	   headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td>");
               headerString.append("<td colspan='2'></td>");
                
                
               
               headerString.append("</tr>");
            //   headerString.append("</tr>");
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

           }
             //end
             
             
           
            


            //column header should be removed in case of dynamic Report template.Bug 12237.
            //not required for laboratory radiology
            //this condition needs to be replaced by the flag for particular lab
            if(resultEntryVOGroupByValidatedBy.getDisplayHeader().equals("0"))
            {
            	if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                    headerString.append(lineString);
                   
                    resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
                } else {
                    resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                }
            	
            	
            }
            else
            {
            if(resultEntryVOGroupByValidatedBy.getIsdynamicGroupTemplate().equals("0") && resultEntryVOGroupByValidatedBy.getIsdynamicTestTemplate().equals("0"))
            {
            if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                headerString.append(lineString);
                headerString.append(standardRangesString);
                //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 4));
                //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 25));
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            } else {
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            }
            }
            else
            {
                if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                    headerString.append(lineString);
                    headerString.append(standardRangesString);
                  //  resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
                    resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                } else {
                    resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                }
            	
            	
            	
            }
            }
            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 2));
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 12));
            headerString.append(lineString);
            headerString.append("</table>");
       	
        }
        else
        {
        if (resultEntryVOGroupByValidatedBy.getPatAdmissionNo() != null) {
            headerString.append("<tr><td width='10%'><div align='left'><font size='8.5pt'><b>CR No</b></font></div></td>");
            headerString.append("<td width='57%' colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
            headerString.append("<td width='15%'><div align='left'><font size='8.5pt'><b>Admission No</b></font></div></td>");
            headerString.append("<td width='18%'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatAdmissionNo() + "</font></div></td>");
            headerString.append("</tr>");
        } else {

            
            
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
             
        	
        	 headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>CR No</b></font></div></td>");
             headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
             //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
             
             if(resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged()==null || resultEntryVOGroupByValidatedBy.getIsreportlablabelchanged().equals("0"))
             headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Lab/Study No.</b></font></div></td>");
             else
            	 headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Accession No.</b></font></div></td>");	
             
             String key=resultEntryVOGroupByValidatedBy.getRequisitionNo()+"#"+resultEntryVOGroupByValidatedBy.getSampleCode()+"#"+resultEntryVOGroupByValidatedBy.getLaboratoryCode();

        	   
        	   
             if(mplabno!=null && mplabno.containsKey(key))
      	   {
    	      String labno=	mplabno.get(key);
    	      if(labno!=null)
    	     resultEntryVOGroupByValidatedBy.setLabNo(labno);
    	      else
    	      {
    	    	 if(mp!=null && mp.containsKey(key))
        	   {
    	    		String labno1=	mp.get(key);
          	      if(labno1!=null)
          	     resultEntryVOGroupByValidatedBy.setLabNo(labno1); 
        	   }
    	      }
    	      
      	   }
             else if(mp!=null && mp.containsKey(key))
      	   {
  	    		String labno1=	mp.get(key);
        	      if(labno1!=null)
        	     resultEntryVOGroupByValidatedBy.setLabNo(labno1); 
      	   }
             
             
             headerString.append("<td colspan='2'><div align='left'> <font size='8.5pt'>:&nbsp;" + resultEntryVOGroupByValidatedBy.getLabNo() + "</font></div></td>");
          
             if(resultEntryVOGroupByValidatedBy.getLabNo().length()>16)
             {  islabelprintindoubleline.put("lab/accessno", "1");
             islabelprintindoubleline.put("lab/accessno1", "1");
             }
             headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Requisition Date</b></font></div></td>");
             String reqDate = resultEntryVOGroupByValidatedBy.getRequisitionDate();
             if (reqDate == null || reqDate == "null") {
                 reqDate = "--";
             }
             headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + reqDate + "</font></div></td>");
 			
             
             headerString.append("</tr>");
        }
            
        //added by chandan
        headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Patient Name</b></font></div></td>");
        headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatName() + "</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
     
        if(resultEntryVOGroupByValidatedBy.getPatName().trim().length()>24)
            {
        	islabelprintindoubleline.put("patname", "1");
            islabelprintindoubleline.put("patname1", "1");

            }
        headerString.append("<td colspan='2''><div align='left'><font size='8.5pt'><b>Age/Sex</b></font></div></td>");
        headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatAge() + "/" + resultEntryVOGroupByValidatedBy.getPatGenderShortName() + "</font></div></td>");
        if(resultEntryVOGroupByValidatedBy.getIsreportcollectionlabelchanged()==null || resultEntryVOGroupByValidatedBy.getIsreportcollectionlabelchanged().equals("0"))
        headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Coll./Study Date</b></font></div></td>");
        else
        	headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Study Date</b></font></div></td>");
        String collDate = resultEntryVOGroupByValidatedBy.getCollDate();
        if (collDate == null || collDate == "null") {
        	if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
        		collDate = "--";
        	else if(resultEntryVOGroupByValidatedBy.getPatAcceptanceDate() != null)
        		collDate = resultEntryVOGroupByValidatedBy.getPatAcceptanceDate();
        	else
        		collDate = "--";
        }
        
        headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + collDate + "</font></div></td>");
        headerString.append("</tr>");
        
        //comment by chandan
         
        
        
        
       

        if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
        {
        headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Sample Type/No</b></font></div></td>");
        if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
        	{
        	
        	String key=resultEntryVOGroupByValidatedBy.getRequisitionNo()+"#"+resultEntryVOGroupByValidatedBy.getSampleCode()+"#"+resultEntryVOGroupByValidatedBy.getLaboratoryCode();

     	   if(mp!=null && mp.containsKey(key))
       	   {
     	      String sampleno=	mp.get(key);
     	     resultEntryVOGroupByValidatedBy.setUserSampleNo(sampleno);
       	   }
     	   
        	headerString.append("<td colspan='3'><div align='left'> <font size='8.5pt'>:&nbsp;" + resultEntryVOGroupByValidatedBy.getSampleName() + "/" + resultEntryVOGroupByValidatedBy.getUserSampleNo() + "</font></div></td>");
        	

            if((resultEntryVOGroupByValidatedBy.getSampleName()+resultEntryVOGroupByValidatedBy.getUserSampleNo()).length()>24)
            { islabelprintindoubleline.put("sampletype_ward", "1");
            islabelprintindoubleline.put("sampletype_ward1", "1");
            }
            
        	} else
        { 	
        	headerString.append("<td colspan='3'><div align='left'> <font size='8.5pt'>:&nbsp;" + resultEntryVOGroupByValidatedBy.getSampleName() + "</font></div></td>");
        

        if((resultEntryVOGroupByValidatedBy.getSampleName()).length()>24)
        {
            islabelprintindoubleline.put("sampletype_ward", "1");
        islabelprintindoubleline.put("sampletype_ward1", "1");
        }
        
        }
        
        }
        else
        {
        	if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
        	
            	headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Ward/OPD</b></font></div></td>");
                headerString.append("<td  colspan='3'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
                
        	}
        	else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {
        		
        		headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Ward</b></font></div></td>");
                headerString.append("<td  colspan='3'><div align='left'>: <font size='8.5pt'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
                
                if((resultEntryVOGroupByValidatedBy.getWardName()).length()>16)
                {
                	islabelprintindoubleline.put("sampletype_ward", "1");
                    islabelprintindoubleline.put("sampletype_ward1", "1");
                    
                }
                
        	}

        	
        }
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
       
        
        
        if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) 
        {

        	   headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Ward</b></font></div></td>");
               headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
               
               if(resultEntryVOGroupByValidatedBy.getWardName()!=null && (resultEntryVOGroupByValidatedBy.getWardName()).length()>16)
                   {
            	      	islabelprintindoubleline.put("sampletype_ward", "1");
                       islabelprintindoubleline.put("sampletype_ward1", "1");
                   }
                   
               
        }
        else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
            
    		headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Ward/OPD</b></font></div></td>");
               headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
        }
        else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
            // headerString.append("<tr>");//emergency case. modify as reqd.
             headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Ward/OPD</b></font></div></td>");
             headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
        }
        else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
            // headerString.append("<tr>");//special case. modify as reqd.
             headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Ward/OPD</b></font></div></td>");
             headerString.append("<td  colspan='2'><div align='left'>: <font size='8.5pt'>OPD</font></div></td>");
        }
        
        
        
        headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Reporting Date</b></font></div></td>");
        String reportDate = resultEntryVOGroupByValidatedBy.getResultValidationDate();
        if (reportDate == null || reportDate == "null") {
        	reportDate = "--";
        }
        headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + reportDate + "</font></div></td>");
       
        
        
        headerString.append("</tr>");
        
        
        
    
        
        headerString.append("<tr><td colspan='2'><div align='left'><font size='8.5pt'><b>Dept/Unit</b></font></div></td>");
        headerString.append("<td colspan='7'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getPatDeptUnit() + "</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        //comment by chandan
        

        
     
        
        
        
        
        
        
        
        
         
        //headerString.append("<tr>");
        //headerString.append("<td width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
        //headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>"+resultEntryVOGroupByValidatedBy.getClinicianName()+"</font></div></td>");	
        //headerString.append("</tr>");
         

        String labNoString = "";
        if (resultEntryVOGroupByValidatedBy.getLabNoList() != null && resultEntryVOGroupByValidatedBy.getLabNoList().size() != 0) {
            labNoString = "";
            for (int i = 0; i < resultEntryVOGroupByValidatedBy.getLabNoList().size(); i++) {
                if (i == 0) {
                    labNoString = resultEntryVOGroupByValidatedBy.getLabNoList().get(i);
                } else {
                    labNoString += "," + resultEntryVOGroupByValidatedBy.getLabNoList().get(i);
                }
            }

        }

         
        //Siddharth
       
         
        //}
        if (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis() != null) {
            int multiplFactor = (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis().length() / 84) + ((resultEntryVOGroupByValidatedBy.getCurrentDiagnosis().length() % 84 == 0) ? 0 : 1);
    
            
             
            if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
            {
            	headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Addendum Date</b></font></div></td>");
                headerString.append("<td colspan='2'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
            }
            else
            {
            headerString.append("<td colspan='2'></td>");
            headerString.append("<td colspan='2'></td>");
            }

             
            
             
             headerString.append("</tr>");
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + (10 * multiplFactor))); //added this line whenever add new tr
        } else {
            headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Diagnosis</b></font></div></td><td  colspan='1'><div align='left'>: <font size='2'></font></div></td>");
           
            
            if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
            {
            	headerString.append("<td colspan='2'><div align='left'><font size='8.5pt'><b>Addendum Date</b></font></div></td>");
                headerString.append("<td colspan='3'><div align='left'>: <font size='8.5pt'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
            }
            else
            {
            headerString.append("<td colspan='2'></td>");
            headerString.append("<td colspan='3'></td>");
            }

             
            headerString.append("</tr>");
            
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10)); //added this line whenever add new tr
        }
        
        // comment by chandan
        
        
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        headerString.append("<tr>");
        if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {

       	 
        	if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))
        	{
         //   headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Ward</b></font></div></td>");
          //  headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
             
            headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Room/Bed</b></font></div></td>");
            headerString.append("<td  colspan='5'><div align='left'>: <font size='8.5pt'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
            
            headerString.append("<td colspan='2'></td>");
            headerString.append("<td  colspan='3'></td>");
              
            
            headerString.append("<td colspan='1'></td></tr>");
        	}
        	else
        	{
        		  headerString.append("<td  colspan='2'><div align='left'><font size='8.5pt'><b>Room/Bed</b></font></div></td>");
                  headerString.append("<td  colspan='5'><div align='left'>: <font size='8.5pt'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
                  headerString.append("<td colspan='6'></td></tr>");
              		
        		
        	}
             
            
                       //headerString.append("</tr>");
            
       	// end testing header
       	
//           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 20));
           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 80));
       } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
         //  headerString.append("<tr>");
    	   if(resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired()==null || resultEntryVOGroupByValidatedBy.getIsreportsamplelabelrequired().equals("0"))

    		   {
    		   
    	//	   headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
         //  headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
    		   headerString.append("<td colspan='2'></td>");
               headerString.append("<td  colspan='3'></td>");
             
    		   headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td></tr>");
    		   }
    	   
    	    
          
           
           
          headerString.append("</tr>");
//           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));
       } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
          // headerString.append("<tr>");//emergency case. modify as reqd.
         //  headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
           //headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td  colspan='3'></td>");
         
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td></tr>");
            
           
            
           
           headerString.append("</tr>");
//           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

       }  else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
          // headerString.append("<tr>");//special case. modify as reqd.
         //  headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
          // headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
    	   headerString.append("<td colspan='2'></td>");
           headerString.append("<td  colspan='3'></td>");
         
    	   headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
            
            
           
           headerString.append("</tr>");
        //   headerString.append("</tr>");
//           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

       }
         //end
         
         
       
        


        //column header should be removed in case of dynamic Report template.Bug 12237.
        //not required for laboratory radiology
        //this condition needs to be replaced by the flag for particular lab
        if(resultEntryVOGroupByValidatedBy.getDisplayHeader().equals("0"))
        {
        	if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                headerString.append(lineString);
               
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
            } else {
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            }
        	
        	
        }
        else
        {
        if(resultEntryVOGroupByValidatedBy.getIsdynamicGroupTemplate().equals("0") && resultEntryVOGroupByValidatedBy.getIsdynamicTestTemplate().equals("0"))
        {
        if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
            headerString.append(lineString);
            headerString.append(standardRangesString);
            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 4));
            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 25));
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
        } else {
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
        }
        }
        else
        {
            if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                headerString.append(lineString);
                headerString.append(standardRangesString);
              //  resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            } else {
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            }
        	
        	
        	
        }
        }
        //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 2));
        resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 12));
        headerString.append(lineString);
        headerString.append("</table>");
        
         }
        
        
         
    }
         
        	 
        //setPageHeaderHeight(resultEntryVOGroupByValidatedBy);

        // Log(Level.INFO, "headerString------->" + headerString.toString());
        resultEntryVOGroupByValidatedBy.setHeader(headerString.toString());

	       
        if(islabelprintindoubleline!=null && islabelprintindoubleline.size()>0)
        	resultEntryVOGroupByValidatedBy.setIslabeldoubleline(islabelprintindoubleline.size());
        	else
        		resultEntryVOGroupByValidatedBy.setIslabeldoubleline(0);
    }

    public static synchronized void getDefaultHeaderUnRegisteredPatient(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        StringBuffer headerString = new StringBuffer();
        String hospitalString = "<table  cellpadding='0' border='0' width='100%' style='border-spacing: 0px'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + Config.hospitalnameatreport + "</b></font></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + Config.hospitaladdressatreport + "</b></font></td></tr>";
        headerString.append(hospitalString);

        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='2'>Dept. of " + resultEntryVOGroupByValidatedBy.getDepartmentName() + "</font></td></tr>");

        headerString.append("<tr><td width='50%' colspan='3'><font color='black' size='2'><b>Laboratory :</b>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
         
        if (resultEntryVOGroupByValidatedBy.getSampleNameList() != null && resultEntryVOGroupByValidatedBy.getSampleNameList().size() != 0) {
            String sampleNameString = "";
            for (int i = 0; i < resultEntryVOGroupByValidatedBy.getSampleNameList().size(); i++) {
                if (i == 0) {
                    sampleNameString = resultEntryVOGroupByValidatedBy.getSampleNameList().get(i);
                } else {
                    sampleNameString = "," + resultEntryVOGroupByValidatedBy.getSampleNameList().get(i);
                }
            }

            headerString.append("<td align='right' width='50%' colspan='3'><font color='black' size='3'><b>Sample :</b>" + sampleNameString + "</font></td></tr>");
        } else {
            headerString.append("<td align='right' width='50%' colspan='3'></td></tr>");
        }
         
        headerString.append(lineString);

        headerString.append("<tr>");
        headerString.append("<td width='5%'><div align='left'><b>CR No</b></div></td>");
        headerString.append("<td width='20%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>NA</font></div></td>");
        headerString.append("<td width='20%'><div align='left'><b>Patient Name</b></div></td>");
        headerString.append("<td width='32%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>" + resultEntryVOGroupByValidatedBy.getPatName() + "</font></div></td>");
        headerString.append("<td width='15%'><div align='left'><b>Age/Sex</b></div></td>");
        headerString.append("<td width='18%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>" + resultEntryVOGroupByValidatedBy.getPatAge() + "/" + resultEntryVOGroupByValidatedBy.getPatGenderShortName() + "</font></div></td>");
        headerString.append("</tr>");
        headerString.append("<tr>");
        headerString.append("<td width='5%'><div align='left'><b>Unit</b></div></td>");
        headerString.append("<td width='60%' colspan='3'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>NA</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        headerString.append("<td width='15%'><div align='left'><b>Req. Date</b></div></td>");
        String reqDate = resultEntryVOGroupByValidatedBy.getRequisitionDate();
        if (reqDate == null || reqDate == "null") {
            reqDate = "--";
        }
        headerString.append("<td width='20%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>" + reqDate + "</font></div></td>");
        headerString.append("</tr>");
        headerString.append("<tr>");
        headerString.append("<td width='5%'><div align='left'><b>Clinician</b></div></td>");
        String clinicianName = resultEntryVOGroupByValidatedBy.getClinicianName();
        if (clinicianName == null || clinicianName == "null") {
            clinicianName = "";
        }
        headerString.append("<td width='29%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>" + clinicianName + "</font></div></td>");

         
        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().size() != 1) {
            headerString.append("<td width='15%'><div align='left'><b>&nbsp;</b></div></td>");
            headerString.append("<td width='18%'><div align='left'>&nbsp;</div></td>");
        } else {
            headerString.append("<td width='15%'><div align='left'><b>Test</b></div></td>");
            headerString.append("<td width='18%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>" + resultEntryVOGroupByValidatedBy.getTestName() + "</font></div></td>");
        }
        headerString.append("<tr>");

        if (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis() != null) {
            
            headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Diagnosis</b></font></div></td><td width='90%' colspan='5'><div align='left'>: <font size='2'></font></div></td></tr>");        
        
        } else {
            headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Diagnosis</b></font></div></td><td width='90%' colspan='5'><div align='left'>: <font size='2'></font></div></td></tr>");
        }

        resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 35)); //added this line whenever add new tr

        if(resultEntryVOGroupByValidatedBy.getIsdynamicGroupTemplate().equals("0") && resultEntryVOGroupByValidatedBy.getIsdynamicTestTemplate().equals("0"))
        if (resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
            headerString.append(lineString);
            headerString.append(standardRangesString);

        }
        else
        {
        	  if (resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                  headerString.append(lineString);
                  

              }
        	
        	
        }

     //   headerString.append(lineString);

        //Log(Level.INFO,headerString.toString());
        resultEntryVOGroupByValidatedBy.setHeader(headerString.toString());

    }

    public static synchronized String setPageHeaderHeight(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        String pageHeaderHeight = "";
        if (resultEntryVOGroupByValidatedBy.getPageSize() == null || resultEntryVOGroupByValidatedBy.getPageSize().equals("") == false) {
            resultEntryVOGroupByValidatedBy.setPageSize(Config.pagewidthheight);
        }

        if (resultEntryVOGroupByValidatedBy.getPageSize().equals(Config.pagewidthheight)) {
            resultEntryVOGroupByValidatedBy.setHeaderHeight("198.0");

            if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                resultEntryVOGroupByValidatedBy.setHeaderHeight("200.0");
            }

        } else if (resultEntryVOGroupByValidatedBy.getPageSize().equals("A2")) {
            resultEntryVOGroupByValidatedBy.setHeaderHeight("120.0");
        } else {
            resultEntryVOGroupByValidatedBy.setHeaderHeight("200.0");
        }

        return pageHeaderHeight;
    }

    public static synchronized void getDefaultFooter(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        StringBuffer headerString = new StringBuffer();
        headerString.append("<div ><table width='100%' height='5%' cellspacing='0' cellpadding='0'  '>");
      //  headerString.append(lineString);
        
        String headerStringnew = "";
        
        //  headerString.append(lineString);
          
          String addfooter=resultEntryVOGroupByValidatedBy.getIsextrafooterreq() ;
          String addfooternote=resultEntryVOGroupByValidatedBy.getIsextrafooterreqval();
          
          
          if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) ) ) //10010,10010
        	{
        	  addfooternote=null;
        	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_name + " </b></font></div></td></tr>");
        	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_desig + " </b></font></div></td></tr>");
        	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_deg + " </b></font></div></td></tr>");
        	}
          
          if(addfooter!=null && addfooter.equals("1"))
      	{
      		if(addfooternote!=null && !addfooternote.equals(""))
      		{
      			headerStringnew="<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + addfooternote + " </b></font></div></td></tr>";
      			headerString.append(headerStringnew);
      		}
      	
      	}
          
          if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) ) ) //10010,10010
      	{
        	  Log(Level.INFO, "harcode value pathology");
        	  Config.disclaimeratreport="";
      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_name + " </b></font></div></td></tr>");
      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_desig + " </b></font></div></td></tr>");
      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_deg + " </b></font></div></td></tr>");
      	}
          else
          {
        	  Config.disclaimeratreport="All reports need clinical correlation. Kindly discuss if necessary. No part of the report can be reproduced without written permission of the department.";
          }
          
        if(resultEntryVOGroupByValidatedBy.getIsReportChange()!=null && !resultEntryVOGroupByValidatedBy.getIsReportChange().equals("0") )
        {
        	if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
        	{
        		 Log(Level.INFO, "harcode value pathology1");
        	}
        	else
        	{
        	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        	}
        	
        	if(resultEntryVOGroupByValidatedBy.getIsenteredbyrequired()!=null && resultEntryVOGroupByValidatedBy.getIsenteredbyrequired().equals("1"))
          	{
          	
          		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(",") )
  			{
			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +"<b>&nbsp;&nbsp;&nbsp;Result Modified By: </b>" + resultEntryVOGroupByValidatedBy.getReportChangedBy() +   "</font></div></td></tr>");
  			}else
  				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +"<b>&nbsp;&nbsp;&nbsp;Result Modified By: </b>" + resultEntryVOGroupByValidatedBy.getReportChangedBy() +    "</font></div></td></tr>");
          	
          	}
          	else
          	{
          	
          		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") &&  !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
      			{
    			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +"<b>&nbsp;&nbsp;&nbsp;Result Modified By: </b>" + resultEntryVOGroupByValidatedBy.getReportChangedBy() +   "</font></div></td></tr>");
      			}else
      				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +"<b>&nbsp;&nbsp;&nbsp;Result Modified By: </b>" + resultEntryVOGroupByValidatedBy.getReportChangedBy() +    "</font></div></td></tr>");
         
          	}
			
        	if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
	      	{
        		
        		
        		
	        	//  Config.disclaimeratreport="";
	      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_name + " </b></font></div></td></tr>");
	      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_desig + " </font></div></td></tr>");
	      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_deg + " </font></div></td></tr>");
	      	}
		
			//headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='1'><b>Report Modified By: </b>" + resultEntryVOGroupByValidatedBy.getReportChangedBy()  +    "</font></div></td></tr>");
        	//+"<b>&nbsp;&nbsp;&nbsp;Result Modified By: </b>" + resultEntryVOGroupByValidatedBy.getReportChangedBy() +
        	headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'></font></div></td></tr>");
        	
        	
        	
        }
        else{
     
        	
        	if(resultEntryVOGroupByValidatedBy.getIsDeptEntry().equals("1"))
        	{
        		if(resultEntryVOGroupByValidatedBy.getIsNablAccritedTest()!=null && resultEntryVOGroupByValidatedBy.getIsNablAccritedTest().equals("1"))
        		{
        			String 	testcodes=PGDataHelper.getInstance().getTestCodesAccrebited(resultEntryVOGroupByValidatedBy.getHospitalCode());
                  	String 	TestNames=PGDataHelper.getInstance().getTestNameAccrebited(testcodes);
                  	String NablLogo = PropertiesHelper.getNablLogoPath();
                  	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='2'><b>" +"\"*"+ TestNames + " Tests are NABL accredited.\" </b></font></div></td></tr>");
                 
                  	if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
    		      	{
    				
    		      	}
                  	else
                  	{
                  		headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
                  	}
                  	
                  //	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
                  	
                  	 
                  	if(resultEntryVOGroupByValidatedBy.getIsenteredbyrequired()!=null && resultEntryVOGroupByValidatedBy.getIsenteredbyrequired().equals("1"))
                  	{
                  	
                  		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(",") )
          			{
        			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
          			}else
          				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                  	
                  	}
                  	else
                  	{
                  	
                  		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") &&  !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
              			{
            			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
              			}else
              				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                 
                  	}
                  	
                  	
                  	if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
    		      	{
    		        	//  Config.disclaimeratreport="";
    		     // 		headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_name + " </b></font></div></td></tr>");
    		      //		headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_desig + " </font></div></td></tr>");
    		      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_deg + " </font></div></td></tr>");
    		      	}
                  	
                  	//headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy() + "</font></div><div align='right'><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "</font></div></td></tr>");
                  	if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
          			{
    	  				headerString.append("<tr><td align='right' colspan='13'><div align='left' ><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td></tr>");
    	  				
    	  			//	headerString.append("<tr><td align='right' colspan='13'><div align='right'  ><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "<b>&nbsp;&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() + "</font></div></td></tr>");
          			}
    	  			else
                  	headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'></td></tr>");
        		}
        		else{
        		
        			
        			if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
    		      	{
    				
    		      	}
        			else
        			{
        				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        			}
        			//headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        			
        			
        			if(resultEntryVOGroupByValidatedBy.getIsenteredbyrequired()!=null && resultEntryVOGroupByValidatedBy.getIsenteredbyrequired().equals("1"))
                  	{
                  	
                  		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
          			{
        			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
          			}else
          				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                  	
                  	}
                  	else
                  	{
                  	
                  		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
              			{
            			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
              			}else
              				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                 
                  	}
        			
        			
        			if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
    		      	{
    		        	//  Config.disclaimeratreport="";
    		      	
        			//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_name + " </b></font></div></td></tr>");
    		      //		headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_desig + " </font></div></td></tr>");
    		      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_deg + " </font></div></td></tr>");
    		      	}
        			
             //headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy() + "</font></div><div align='right'><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "</font></div></td></tr>");
        			if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
          			{
    	  			//	headerString.append("<tr><td align='right' colspan='13'><div align='left' ><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td></tr>");
    	  			//	headerString.append("<tr><td align='right' colspan='13'><div align='right'  ><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "<b>&nbsp;&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() + "</font></div></td></tr>");
          			}
    	  			else
        			headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'></td></tr>");
        		}
        		
        	
        	}	 
        		else {
        			
        			if(resultEntryVOGroupByValidatedBy.getIsNablAccritedTest()!=null && resultEntryVOGroupByValidatedBy.getIsNablAccritedTest().equals("1"))
        			{
        				 Log(Level.INFO, "nabl harcode value pathology");
        				String 	testcodes=PGDataHelper.getInstance().getTestCodesAccrebited(resultEntryVOGroupByValidatedBy.getHospitalCode());
        	          	String 	TestNames=PGDataHelper.getInstance().getTestNameAccrebited(testcodes);
        	          	String NablLogo = PropertiesHelper.getNablLogoPath();
        	          	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='2'><b>" +"\"*"+ TestNames + " Tests are NABL accredited.\" </b></font></div></td></tr>");
        	           
        	          	if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
        		      	{
        				
        		      	}
        	          	else
        	          	{
        	          		headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        	          	}
        	          //	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        				
        	          	if(resultEntryVOGroupByValidatedBy.getIsenteredbyrequired()!=null && resultEntryVOGroupByValidatedBy.getIsenteredbyrequired().equals("1"))
                      	{
                      	
                      		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
              			{
            			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
              			}else
              				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                      	
                      	}
                      	else
                      	{
                      	
                      		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
                  			{
                			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
                  			}else
                  				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                     
                      	}
        	          	
        	        	if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
        		      	{
        		        	//  Config.disclaimeratreport="";
        		      		//headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_name + " </b></font></div></td></tr>");
        		      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_desig + " </font></div></td></tr>");
        		      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_deg + " </font></div></td></tr>");
        		      	}
        	          	//headerString.append("<tr ><td align='right' colspan='6'><div align='left' ><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right' ><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getResultEnteredBy() + "</font></div><div align='right'  ><font color='black' size='2'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "</font></div></td></tr>");
        	          	
        				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        		      	
        	          	//headerString.append("<tr><td align='right' colspan='6'><div align='left' ><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'></td></tr>");
        				
        			}
        			else{
//        				
        				if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
        		      	{
        					 Log(Level.INFO, "harcode value pathology2");
        		      	}
        				else
        				{
            				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");

        				}
        				 
        				
        				if(resultEntryVOGroupByValidatedBy.getIsenteredbyrequired()!=null && resultEntryVOGroupByValidatedBy.getIsenteredbyrequired().equals("1"))
                      	{
                      	
                      		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
              			{
            			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
              			}else
              				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Prepared By: </b>" + resultEntryVOGroupByValidatedBy.getEnteredby()  +  "<b>&nbsp;&nbsp;&nbsp;Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                      	
                      	}
                      	else
                      	{
                      	
                      		if(resultEntryVOGroupByValidatedBy.getRevalidatedBy()!=null && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals("") && !resultEntryVOGroupByValidatedBy.getRevalidatedBy().equals(","))
                  			{
                			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  + "<b>&nbsp;&nbsp;&nbsp;Revalidated By: </b>" + resultEntryVOGroupByValidatedBy.getRevalidatedBy() +   "</font></div></td></tr>");
                  			}else
                  				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy()  +    "</font></div></td></tr>");
                     
                      	}
        				
        				
        				if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
        		      	{
        		        	//  Config.disclaimeratreport="";
        		      ///		headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + Config.disclaimeratreport_immuno_name + " </b></font></div></td></tr>");
        		      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_desig + " </font></div></td></tr>");
        		      	//	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'>" + Config.disclaimeratreport_immuno_deg + " </font></div></td></tr>");
        		      	}
        				//headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getResultEnteredBy() + "</font></div><div align='right'><font color='black' size='2'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "</font></div></td></tr>");
        			
        				headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'></td></tr>");
        			}
                      
        		
        		
        		}
            //  }
            
        }
        
       
       
        //headerString.append("<tr><td>" + ". "+ "<br></td></tr>");
        headerString.append(lineString);
        
        
        if(resultEntryVOGroupByValidatedBy.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10010") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10042")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10009")) && ((resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedBy.getHospitalCode().equals("22914") ) )) //10010,10010
      	{
    		
    		  String addfooternew=resultEntryVOGroupByValidatedBy.getIsextrafooterreq() ;
              String addfooternewnotenew=resultEntryVOGroupByValidatedBy.getIsextrafooterreqval();
              
              
    		   if(addfooternew!=null && addfooternew.equals("1"))
          	{
          		if(addfooternewnotenew!=null && !addfooternewnotenew.equals(""))
          		{
          			headerStringnew="<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='8.5pt'><b>" + addfooternewnotenew + " </b></font></div></td></tr>";
          			headerString.append(headerStringnew);
          		}
          	
          	}
    		
        	
      	}
        
        headerString.append("</table></div>");
        //Log(Level.INFO,"getDefaultFooter "+headerString.toString());
        resultEntryVOGroupByValidatedBy.setFooter(headerString.toString());
      //  Log(Level.INFO, "footerrr:"+headerString);
    }

    
    private static void Log(Level level, String msg) {
        ServiceLogger.Log(PDFPrintingProcesses.class.getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(PDFPrintingProcesses.class.getName(), level, e);
    }

}
