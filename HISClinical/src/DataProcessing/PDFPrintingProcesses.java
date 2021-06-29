/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

/**
 * *************************Start of program***************************** ##
 * Copyright Information	: C-DAC, Noida ## Project Name	: CCD SDK ## Name of
 * Developer	: Siddharth Srivastava ## Module Name	: Health Standards ##
 * Process/Database Object Name	: ## Purpose : ## Date of Creation	: ##
 * Modification Log	: ##	Modify Date	: ##	Reason	(CR/PRS)	: ##	Modify By	:
 */
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
import DataHelper.Config;
import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import Logging.ServiceLogger;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

public class PDFPrintingProcesses {

    String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + Config.hospitalnameatreport + "</b></font></td></tr><tr><td align='center' width='100%' colspan='6'><font color='black' size='4'><b>" + Config.hospitaladdressatreport + "</b></font></td></tr>";
   // final static String lineString = "<tr><td bgcolor='black' colspan='6'><img src='grid.png' width='1' height='1' border='0'/></td></tr>";
    final static String lineString = "<tr><td bgcolor='black' colspan='13'></td></tr>";
   // final static String standardRangesString = "<tr><td colspan='13' width='100%'><table width='100%'><tr><td width='50%'><font color='black' size='3'><b>Investigation</b></font></td><td width='25%'><img src='grid.png' width='1' height='1' border='0'/></td><td align='center' width='9%'><div align='right'><font color='black' size='3'><b>Result</b></font></div></td><td width='16%'><div align='center'><font color='black' size='3'><b>Unit</b></font></div></td><td width='24%'><div align='center' ><font color='black' size='3'><b>Standard Ranges</b></font></div></td></tr></table></td></tr>";
    //final static String standardRangesString = "<tr><td colspan='13' width='100%'><table width='100%'><tr><td width='50%'><font color='black' size='3'><b>Investigation</b></font></td><td width='25%'></td><td align='center' width='9%'><div align='right'><font color='black' size='3'><b>Result</b></font></div></td><td width='16%'><div align='center'><font color='black' size='3'><b>Unit</b></font></div></td><td width='24%'><div align='center' ><font color='black' size='3'><b>Standard Ranges</b></font></div></td></tr></table></td></tr>";
   //making changes in unit and ref range as prs received july 7, 2016
    final static String standardRangesString = "<tr><td colspan='13' width='100%'><table width='100%'><tr><td width='30%'><font color='black' size='3'><b>Investigation</b></font><td colspan='1' width='40%' align='left' ><div align='left' style=' margin-left: 25px'><font color='black' size='3'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Result</b></font></div></td><td width='35%'><div align='left'><font color='black' size='3'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit</b></font></div></td><td width='15%'><div align='center' ><font color='black' size='3'><b>Ref. Range</b></font></div></td></tr></table></td></tr>";

/*    final static String standardRangesString = "<tr><td colspan='13' width='100%'><table width='100%'><tr><td width='30%'><font color='black' size='3'><b>Investigation</b></font><td colspan='1' width='42%' align='left' ><div align='left'><font color='black' size='3'><b>Result</b></font></div></td><td width='11%'><div align='left'><font color='black' size='3'><b>Unit</b></font></div></td><td width='15%'><div align='center' ><font color='black' size='3'><b>Ref. Range</b></font></div></td></tr></table></td></tr>";
*/
    /*this process creates the new header for the group if work orders
     * and assign default header length
     * and assign default page size
     * 
     */
    public static synchronized void getDefaultHeaderRegisteredPatient(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        StringBuffer headerString = new StringBuffer();
        



        //String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + Config.hospitalnameatreport + "</b></font></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + Config.hospitaladdressatreport + "</b></font></td></tr>";
       // String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + resultEntryVOGroupByValidatedBy.getHospitalName() + "</b></font></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + resultEntryVOGroupByValidatedBy.getHospitalCity() + "</b></font></td></tr>";
       // String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><table><tr><td width='10%' rowspan='2'><img src='nims.gif'/></td><td width='90%' align='center'><font color='black' size='5'><b>" + resultEntryVOGroupByValidatedBy.getHospitalName() + "</b></font></td></tr></table></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + resultEntryVOGroupByValidatedBy.getHospitalCity() + "</b></font></td></tr>";
       int noLinesPresent=0;// to subtract from header height if hos add not present
        String reportLogoPath = PropertiesHelper.getReportLogoPath();
        String reportHeaderPath = "";
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
         
         String deptName = "<tr align='center'><td colspan='13' width='100%'><font color='black' size='4'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>";
       
         if(((resultEntryVOGroupByValidatedBy.getHosAdd2().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd2().equals(" "))))
        	 	noLinesPresent++;
         if(((resultEntryVOGroupByValidatedBy.getHosAdd1().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd1().equals(" "))))
        	 noLinesPresent++;
     
        //comment by ashu
         
        /* String isPortal = PGDataHelper.getInstance().getPortalVal(resultEntryVOGroupByValidatedBy.getHospitalCode());
         
         if(isPortal.equalsIgnoreCase("0")){
        	 reportHeaderPath = PropertiesHelper.getReportHeaderPath();
         }*/
        /* else{
        	 addressMetaData = (((resultEntryVOGroupByValidatedBy.getHosAdd1().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd1().equals(" "))) ? (" ") :" " ) +
            		 (((resultEntryVOGroupByValidatedBy.getHosAdd2().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd2().equals(" "))) ? (" ") :" " ) +
             		(((resultEntryVOGroupByValidatedBy.getHospitalCity().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalCity().equals(" "))) ? (" ") :" " ) +
             		(((resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(" "))) ? ("<br>") :"<br>" )+
             		(((resultEntryVOGroupByValidatedBy.getHosFax().equals(""))||(resultEntryVOGroupByValidatedBy.getHosFax().equals(" "))) ? ("<br>") :"<br>" )+
             		(((resultEntryVOGroupByValidatedBy.getHosEmail().equals(""))||(resultEntryVOGroupByValidatedBy.getHosEmail().equals(" "))) ? (" ") :" " );
         }*/
        	 
         System.out.println("report==xscscsxsscs===11");
      /*   addressMetaData = (((resultEntryVOGroupByValidatedBy.getHosAdd1().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd1().equals(" "))) ? (" ") :" " ) +
        		 (((resultEntryVOGroupByValidatedBy.getHosAdd2().equals(""))||(resultEntryVOGroupByValidatedBy.getHosAdd2().equals(" "))) ? (" ") :" " ) +
         		(((resultEntryVOGroupByValidatedBy.getHospitalCity().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalCity().equals(" "))) ? (" ") :" " ) +
         		(((resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(""))||(resultEntryVOGroupByValidatedBy.getHospitalPhone().equals(" "))) ? ("<br>") :"<br>" )+
         		(((resultEntryVOGroupByValidatedBy.getHosFax().equals(""))||(resultEntryVOGroupByValidatedBy.getHosFax().equals(" "))) ? ("<br>") :"<br>" )+
         		(((resultEntryVOGroupByValidatedBy.getHosEmail().equals(""))||(resultEntryVOGroupByValidatedBy.getHosEmail().equals(" "))) ? (" ") :" " );
         */
        //System.out.println(" isPortal : "+isPortal+ " reportHeaderPath : "+reportHeaderPath);
        
//         
//         if(reportHeaderPath != null && reportHeaderPath != "")
//         {
        	 hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'>" + 
             		"<table><tr><td width='10%' rowspan='2'><img src='" + reportLogoPath + 
             		"' height='50' width='50'/></td>"+
             		"<td width='90%' align='center'><font color='black' size='5'><b>" 
             		+ resultEntryVOGroupByValidatedBy.getHospitalName() +             		
             		"</b>"+
             		"</font>"+
             		"<br>" + 
             		"<font color='black' size='3'><b>" + 
             		addressMetaData +
             	    "</b></font></td></tr>"
             	   
             	    + "</table></td></tr>";
        	 
        	 
            // headerString.append("<table><tr><td><img src='" + reportHeaderPath + "' width='530' height='60'></td></tr></table>");
             System.out.println("Initial headerString0  : "+headerString.toString());
        	 
//        	 hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'>" + 
//            		"<table><tr>"+
//            		"<td width='90%' align='center'><font color='black' size='5'><b>" 
//            		+ " " +             		
//            		"</b>"+
//            		"</font>"+
//            		"<br>" + 
//            		"<font color='black' size='3'><b>" + 
//            		addressMetaData +
//            		" " +
//            	    "</b></font></td></tr>"
//            	   
//            	    + "</table></td></tr>";
            
           /* if(resultEntryVOGroupByValidatedBy.getIsNablAccritedTest()!=null && resultEntryVOGroupByValidatedBy.getIsNablAccritedTest().equals("1"))
    		{
            	hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'>" + 
                		"<table><tr><td width='10%' rowspan='2'></td>"+
                		"<td width='90%' align='center'><font color='black' size='5'><b>" 
                		+ " " +             		
                		"</b>"+
                		"</font>"+
                		"<br>" + 
                		"<font color='black' size='3'><b>" + 
                		addressMetaData +
                		" " +
                	    "</b></font></td><td width='10%' rowspan='2'></td></tr>"
                	   
                	    + "</table></td></tr>";
            	
    		}*/
            	    
//         }
//         else {
//            /* hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" +
//            		 " " + "</b></font></td></tr>" + 
//            		"<tr><td align='center'width='100%' colspan='13'><font color='black' size='3'><b>" + 
//            		addressMetaData
//            		+
//            		" " +
//            		"</b></font></td></tr>" ;*/
//        	 
//        	 
//        	 hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'>" + 
//             		"<table><tr><td width='10%' rowspan='2'></td>"+
//             		"<td width='90%' align='center'><font color='black' size='5'><b>" 
//             		+ " " +             		
//             		"</b>"+
//             		"</font>"+
//             		"<br>" + 
//             		"<font color='black' size='3'><b>" + 
//             		addressMetaData +
//             		" " +
//             	    "</b></font></td></tr>"
//             	   
//             	    + "</table></td></tr>";
//         }
       
        //System.out.println("Commented Header Part in PDFPrintingProcesses-Test3");
        
        headerString.append(hospitalString);
         
        // System.out.println("Commented Header Part in PDFPrintingProcesses-10011");

         
        /*if (resultEntryVOGroupByValidatedBy.getHospitalPhone() != null) {
         hospitalString += "<tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + resultEntryVOGroupByValidatedBy.getHospitalPhone() + "</b></font></td></tr>";
         }*/
       
       // headerString.append(deptName);
//        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='4'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>");
        
        //headerString.append("<tr align='right'><td colspan='13' width='100%'><img src='"+ reportHeaderPath +"' height='50' width='100%'/></td></tr>");
        //headerString.append("<tr><td align='left' colspan='1'><div align='left'  ><font color='black' size='2'><b></b></font></div></td><td align='right' colspan='12'><div align='right' > <img src='" + reportHeaderPath + "' height='50' width='100%'/></div></td></tr>");
        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='4'>Department of " + resultEntryVOGroupByValidatedBy.getLabDeptName() + "</font></td></tr>");
        //headerString.append("<tr><td  colspan='6'><font color='black' size='3'><b>Laboratory :</b>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
        headerString.append("<tr><td  colspan='6'><font color='black' size='3'>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
        
        /*Add Sample Name for Changed Request Recived on 29 dec 2010*/
/*        if (resultEntryVOGroupByValidatedBy.getSampleNameList() != null && resultEntryVOGroupByValidatedBy.getSampleNameList().size() != 0) {
            String sampleNameString = "";
            for (int i = 0; i < resultEntryVOGroupByValidatedBy.getSampleNameList().size(); i++) {
                if (i == 0) {
                    sampleNameString = resultEntryVOGroupByValidatedBy.getSampleNameList().get(i);
                } else {
                    sampleNameString += "," + resultEntryVOGroupByValidatedBy.getSampleNameList().get(i);
                }
            }

            headerString.append("<td align='right'  colspan='1'><font color='black' size='3'><b>Sample :</b>" + sampleNameString + "</font></td></tr>");
        } else {
            headerString.append("<td align='right'colspan='1'></td></tr>");
        }*/
       // System.out.println("value of change is "+resultEntryVOGroupByValidatedBy.getChangeCount());
        
        if(resultEntryVOGroupByValidatedBy.getIsDeptEntry().equals("1"))
       	 headerString.append("<td align='right' colspan='7'><font color='black' size='3'><b>Department Report</b>");
      
        else  if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1"))
        	 headerString.append("<td align='right' colspan='7'><font color='black' size='3'><b>Status :</b>"+ resultEntryVOGroupByValidatedBy.getChangeCount() +" Amendment/Addendum Report</td></tr>");
       else
        	 headerString.append("<td align='right' colspan='7'></td></tr>");
        
        
        
        /*Add Sample Name for Changed Request Recived on 29 dec 2010*/
        setPageHeaderHeight(resultEntryVOGroupByValidatedBy);
        headerString.append(lineString);

        //subtract from header height according to the hos lines not present
        resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - (noLinesPresent*15)));
        //System.out.println(noLinesPresent + "no liness");
        
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

           /* headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>CR No</b></font></div></td>");
            headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
           */
            
            //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
            /*headerString.append("</tr>");*/
        	
        	 headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>CR No</b></font></div></td>");
             headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatCRNo() + (resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() != null ? "(" + resultEntryVOGroupByValidatedBy.getSampleCollectionOfflineRemarks() + ")" : "") + "</font></div></td>");
             //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
             
             
             headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Lab/Study No.</b></font></div></td>");
             headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getLabNo() + "</font></div></td>");
             
             headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Requisition Date</b></font></div></td>");
             String reqDate = resultEntryVOGroupByValidatedBy.getRequisitionDate();
             if (reqDate == null || reqDate == "null") {
                 reqDate = "--";
             }
             headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + reqDate + "</font></div></td>");
 			
             
             headerString.append("</tr>");
        }
            
        //added by chandan
        headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Patient Name</b></font></div></td>");
        headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatName() + "</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        headerString.append("<td colspan='2''><div align='left'><font size='2'><b>Age/Sex</b></font></div></td>");
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatAge() + "/" + resultEntryVOGroupByValidatedBy.getPatGenderShortName() + "</font></div></td>");
       
        headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Coll./Study Date</b></font></div></td>");
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
        
        //comment by chandan
        /*if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1"))
        {
        	headerString.append("<tr><td colspan='1'><div align='left'><font size='2'><b>Lab/Sample No</b></font></div></td>");
            headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getConcatLabNo() + "</font></div></td>");
            headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Change Date</b></font></div></td>");
            headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
            headerString.append("</tr>");
        }
        else if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2"))
        {
        	headerString.append("<tr><td colspan='1'><div align='left'><font size='2'><b>Lab/Sample No</b></font></div></td>");
            headerString.append("<td  colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getConcatLabNo() + "</font></div></td>");
            headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
            headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
            headerString.append("</tr>");
        }
        else if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
        {
            headerString.append("<tr><td colspan='1'><div align='left'><font size='2'><b>Lab/Sample NO.</b></font></div></td>");
            headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getConcatLabNo() + "</font></div></td>");
            headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Amendment Date</b></font></div></td>");
            headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
            headerString.append("</tr>");
        }
        else
        {
        	
        	 headerString.append("<tr><td colspan='1'><div align='left'><font size='2'><b>Lab/Sample NO.</b></font></div></td>");
             headerString.append("<td  colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getConcatLabNo() + "</font></div></td>");
          
             System.out.println(resultEntryVOGroupByValidatedBy.getCollDate() + "coll date");
             //start
             if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {

            	 
           	
                headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Ward</b></font></div></td>");
                headerString.append("<td  colspan='2'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
                headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
                headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
                headerString.append("<td  colspan='1'><div align='left'><font size='2'><b>Room/Bed</b></font></div></td>");
                headerString.append("<td  colspan='2'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
             
                
                headerString.append("<tr><td  width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
                
                headerString.append("<td  width='90%' colspan='5'><div align='left'>: <font size='2'>" + clinicianName + "</font></div></td>");
                //headerString.append("</tr>");
                
           	// end testing header
           	
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 20));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 80));
           } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
             //  headerString.append("<tr>");
               headerString.append("<td  width='10%' colspan='1'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
               headerString.append("<td  width='10%' colspan='2'><div align='left'>: <font size='2'>OPD</font></div></td>");
               headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
               headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
              
              // headerString.append("</tr>");
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));
           } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
              // headerString.append("<tr>");//emergency case. modify as reqd.
               headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
               headerString.append("<td  width='10%'><div align='left'>: <font size='2'>OPD</font></div></td>");
               headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
               headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
               
             //  headerString.append("</tr>");
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

           }  else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
              // headerString.append("<tr>");//special case. modify as reqd.
               headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
               headerString.append("<td  width='10%'><div align='left'>: <font size='2'>OPD</font></div></td>");
               headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
               headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
             
            //   headerString.append("</tr>");
//               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
               resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

           }
             //end
             
             headerString.append("</tr>");
        	
        }*/
        
        
        
      /*  headerString.append("<tr><td colspan='1'><div align='left'><font size='2'><b>Patient Name</b></font></div></td>");
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatName() + "</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        headerString.append("<td colspan='1''><div align='left'><font size='2'><b>Age/Sex</b></font></div></td>");
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatAge() + "/" + resultEntryVOGroupByValidatedBy.getPatGenderShortName() + "</font></div></td>");
        headerString.append("</tr>");*/

        
        headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Sample Type/No</b></font></div></td>");
        if(resultEntryVOGroupByValidatedBy.getUserSampleNo() != null)
        	headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getSampleName() + "/" + resultEntryVOGroupByValidatedBy.getUserSampleNo() + "</font></div></td>");
        else
        	headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getSampleName() + "</font></div></td>");
        	
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        headerString.append("<td colspan='2''><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
        String clinicianName = resultEntryVOGroupByValidatedBy.getClinicianName();
        if (clinicianName == null || clinicianName == "null") {
            clinicianName = "--";
        }
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + clinicianName + "</font></div></td>");
       
        headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Reporting Date</b></font></div></td>");
        String reportDate = resultEntryVOGroupByValidatedBy.getResultValidationDate();
        if (reportDate == null || reportDate == "null") {
        	reportDate = "--";
        }
        headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + reportDate + "</font></div></td>");
        
       /* headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Res Entry Date</b></font></div></td>");
        String resultEntryDate = resultEntryVOGroupByValidatedBy.getResultEntryDate();
        if (resultEntryDate == null || resultEntryDate == "null") {
        	resultEntryDate = "--";
        }
        headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + resultEntryDate + "</font></div></td>");*/
        
        headerString.append("</tr>");
        
        
        
    
        
        headerString.append("<tr><td colspan='2'><div align='left'><font size='2'><b>Dept/Unit</b></font></div></td>");
        headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getPatDeptUnit() + "</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        //comment by chandan
       /* String clinicianName = resultEntryVOGroupByValidatedBy.getClinicianName();
        if (clinicianName == null || clinicianName == "null") {
            clinicianName = "--";
            resultEntryVOGroupByValidatedBy.setClinicianName(clinicianName);
        }
        
        headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + clinicianName + "</font></div></td>");
        
          headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Req. Date</b></font></div></td>");
        String reqDate = resultEntryVOGroupByValidatedBy.getRequisitionDate();
        if (reqDate == null || reqDate == "null") {
            reqDate = "--";
        }
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + reqDate + "</font></div></td>");
        headerString.append("</tr>");*/

        
     
        
        
        
        
        
        
        
        
        /*String clinicianName = resultEntryVOGroupByValidatedBy.getClinicianName();
        if (clinicianName == null || clinicianName == "null") {
            clinicianName = "--";
            resultEntryVOGroupByValidatedBy.setClinicianName(clinicianName);
        }*/
        //headerString.append("<tr>");
        //headerString.append("<td width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
        //headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>"+resultEntryVOGroupByValidatedBy.getClinicianName()+"</font></div></td>");	
        //headerString.append("</tr>");
        /*if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {
//            headerString.append("<tr>");
//            headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Ward</b></font></div></td>");
//            headerString.append("<td  width='57%' colspan='5'><table width='100%'><tr><td width='50%'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName()) + "</font></div></td>");            
//            headerString.append("<td  width='15%'><div align='left'><font size='2'><b>Room/Bed</b></font></div></td>");
//            headerString.append("<td  width='18%'><div align='left'>: <font size='2'> " + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td></tr></table></td>");
//            headerString.append("</tr><tr>");
//            headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
//
//            headerString.append("<td  width='90%' colspan='5'><div align='left'>: <font size='2'>" + clinicianName + "</font></div></td>");
//            headerString.append("</tr>");
        	
        	// Testing header
        	 headerString.append("<tr>");
             headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Ward</b></font></div></td>");
             headerString.append("<td  colspan='2'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
             headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
             headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
             headerString.append("<td  colspan='1'><div align='left'><font size='2'><b>Room/Bed</b></font></div></td>");
             headerString.append("<td  colspan='2'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
             headerString.append("</tr>");
             
             headerString.append("<tr><td  width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
             
             headerString.append("<td  width='90%' colspan='5'><div align='left'>: <font size='2'>" + clinicianName + "</font></div></td>");
             headerString.append("</tr>");
             
        	// end testing header
        	
//            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 20));
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 80));
        } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
            headerString.append("<tr>");
            headerString.append("<td  width='10%' colspan='1'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
            headerString.append("<td  width='10%' colspan='2'><div align='left'>: <font size='2'>OPD</font></div></td>");
            headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td  width='10%' colspan='1'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
            headerString.append("<td  width='20%'colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getClinicianName() + "</font></div></td>");
            headerString.append("</tr>");
//            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));
        } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
            headerString.append("<tr>");//emergency case. modify as reqd.
            headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
            headerString.append("<td  width='10%'><div align='left'>: <font size='2'>OPD</font></div></td>");
            headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
            headerString.append("<td  width='20%'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getClinicianName() + "</font></div></td>");
            headerString.append("</tr>");
//            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

        }  else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
            headerString.append("<tr>");//special case. modify as reqd.
            headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
            headerString.append("<td  width='10%'><div align='left'>: <font size='2'>OPD</font></div></td>");
            headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
            headerString.append("<td  width='20%'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getClinicianName() + "</font></div></td>");
            headerString.append("</tr>");
//            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

        }*/

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

        /*if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() != null && resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().size() == 1) {
         headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Test</b></div></td>");
         headerString.append("<td width='60%' colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getTestName() + "</font></div></td>");
         headerString.append("<td  width='10%'><div align='left'><font size='2'><b>Lab Sample No.</b></font></div></td>");
         headerString.append("<td  width='20%'><div align='left'>: <font size='2'>" + labNoString + "</font></div></td>");
         headerString.append("</tr>");
         resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
         } else {
         headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Lab Sample No.</b></font></div></td>");
         headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>" + labNoString + "</font></div></td>");
         headerString.append("</tr>");
         resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
         }*/
        //Siddharth
      /*  if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() != null && resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().size() == 1) {
         headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Test</b></div></td>");
         headerString.append("<td width='60%' colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getTestName() + "</font></div></td>");
         headerString.append("<tr><td  width='10%'><div align='left'><font size='2'><b>Lab Sample No.</b></font></div></td>");
         headerString.append("<td  width='20%'><div align='left'>: <font size='2'>" + labNoString + "</font></div></td>");
         headerString.append("</tr>");
         resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
         } else {*/
        /* headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Lab Sample No.</b></font></div></td>");
         headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>" + labNoString + "</font></div></td>");
         headerString.append("</tr>");
         resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
         */
        //}
        if (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis() != null) {
            int multiplFactor = (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis().length() / 84) + ((resultEntryVOGroupByValidatedBy.getCurrentDiagnosis().length() % 84 == 0) ? 0 : 1);
   /*         headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Diagnosis</b></font></div></td><td colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getCurrentDiagnosisName() + "</font></div></td>");*/
            
            headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Diagnosis</b></font></div></td><td colspan='2'><div align='left'>: <font size='2'></font></div></td>");
            
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

            /*headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Reporting Date</b></font></div></td>");
            String reportDate = resultEntryVOGroupByValidatedBy.getReportDate();
            if (reportDate == null || reportDate == "null") {
            	reportDate = "--";
            }
            headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + reportDate + "</font></div></td>");*/
            
            /* headerString.append("<td colspan='6'><div align='left'><font size='2'><b>Requisition Date</b></font></div></td>");*/
            /*String reqDate = resultEntryVOGroupByValidatedBy.getRequisitionDate();
            if (reqDate == null || reqDate == "null") {
                reqDate = "--";
            }
            headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + reqDate + "</font></div></td>");
            */headerString.append("</tr>");
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + (10 * multiplFactor))); //added this line whenever add new tr
        } else {
            headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Diagnosis</b></font></div></td><td  colspan='1'><div align='left'>: <font size='2'></font></div></td>");
           
            
            if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
            {
            	headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
                headerString.append("<td colspan='3'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
            }
            else
            {
            headerString.append("<td colspan='2'></td>");
            headerString.append("<td colspan='3'></td>");
            }

            /*headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Reporting Date</b></font></div></td>");
            String reportDate = resultEntryVOGroupByValidatedBy.getReportDate();
            if (reportDate == null || reportDate == "null") {
            	reportDate = "--";
            }
            headerString.append("<td colspan='2'><div align='left'>: <font size='1'>" + reportDate + "</font></div></td>");
            
            headerString.append("<td colspan='2'></td>");
            headerString.append("<td colspan='3'></td>"); */
            headerString.append("</tr>");
            
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10)); //added this line whenever add new tr
        }
        
        // comment by chandan
        
       /* if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3") || resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1"))
        {
        	
        	 headerString.append("<tr><td colspan='1' width='10%'><div align='left'><font size='2'><b>Sample Name</b></font></div></td>");
             headerString.append("<td width='90%' colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getSampleName() + "</font></div></td>");
             //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
             
             headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Collection Date</b></font></div></td>");
             String collDate = resultEntryVOGroupByValidatedBy.getCollDate();
             if (collDate == null || collDate == "null") {
             	collDate = "--";
             }
             headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + collDate + "</font></div></td>");
             
             headerString.append("</tr>");
             
             headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Reporting Date</b></font></div></td>");
             
             //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
             
             String reportDate = resultEntryVOGroupByValidatedBy.getReportDate();
             if (reportDate == null || reportDate == "null") {
             	reportDate = "--";
             }
             headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>" + reportDate + "</font></div></td></tr>");
            
             
        	
        }
        else
        {
        headerString.append("<tr><td colspan='1' width='10%'><div align='left'><font size='2'><b>Sample Name</b></font></div></td>");
        headerString.append("<td width='90%' colspan='2'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getSampleName() + "</font></div></td>");
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        headerString.append("<td colspan='1'><div align='left'><font size='2'><b>Collection Date</b></font></div></td>");
        String collDate = resultEntryVOGroupByValidatedBy.getCollDate();
        if (collDate == null || collDate == "null") {
        	collDate = "--";
        }
        headerString.append("<td colspan='2'><div align='left'>: <font size='2'>" + collDate + "</font></div></td>");
        
        headerString.append("</tr>");
        
        
        headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Reporting Date</b></font></div></td>");
        
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        String reportDate = resultEntryVOGroupByValidatedBy.getReportDate();
        if (reportDate == null || reportDate == "null") {
        	reportDate = "--";
        }
        headerString.append("<td width='90%' colspan='5'><div align='left'>: <font size='2'>" + reportDate + "</font></div></td>");
       
        }
        
        headerString.append("</tr>");*/
        //headerString.append("<td width="17%"><div align='left'>:&nbsp;<font style="text-transform: capitalize;"><bean:write name="workOrderGroup"  property="visitDate"/></font></div></td>-->
        
        headerString.append("<tr>");
        if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("2")) {

       	 
        	
            headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Ward</b></font></div></td>");
            headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getWardName() == null) ? "IPD" : resultEntryVOGroupByValidatedBy.getWardName())  + "</font></div></td>");
            /*headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
            headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");*/
            headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Room/Bed</b></font></div></td>");
            headerString.append("<td  colspan='5'><div align='left'>: <font size='2'>" + ((resultEntryVOGroupByValidatedBy.getRoomName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getRoomName().trim()) +"/"+((resultEntryVOGroupByValidatedBy.getBedName() == null) ? "-" : resultEntryVOGroupByValidatedBy.getBedName().trim()) + "</font></div></td>");
            headerString.append("<td colspan='1'></td></tr>");
           /* headerString.append("<td colspan='2'></td></tr>");*/
            
          /*  headerString.append("<tr><td  width='10%'><div align='left'><font size='2'><b>Clinician</b></font></div></td>");
            
            headerString.append("<td  width='90%' colspan='5'><div align='left'>: <font size='2'>" + clinicianName + "</font></div></td>");
*/            //headerString.append("</tr>");
            
       	// end testing header
       	
//           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 20));
           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 80));
       } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("1")) {
         //  headerString.append("<tr>");
           headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
           headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td></tr>");
           /*headerString.append("<td  ><div align='left'><font size='2'></font></div></td>");
           headerString.append("<td  ><div align='left'><font size='2'></font></div></td>");*/
          
          /* if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
           {
           	headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
               headerString.append("<td colspan='3'><div align='left'>: <font size='1'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
           }
           else
           {
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='3'></td>");
           }*/
           
          headerString.append("</tr>");
//           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));
       } else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3")) {
          // headerString.append("<tr>");//emergency case. modify as reqd.
           headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
           headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td></tr>");
           /*headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
           headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");*/
           
           /*if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
           {
           	headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
               headerString.append("<td colspan='3'><div align='left'>: <font size='1'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
           }
           else
           {
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='3'></td>");
           }*/
           
           headerString.append("</tr>");
//           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
           resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 40));

       }  else if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode() != null && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
          // headerString.append("<tr>");//special case. modify as reqd.
           headerString.append("<td  colspan='2'><div align='left'><font size='2'><b>Ward/OPD</b></font></div></td>");
           headerString.append("<td  colspan='3'><div align='left'>: <font size='2'>OPD</font></div></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='2'></td>");
           /*headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");
           headerString.append("<td  width='25%'><div align='left'><font size='2'></font></div></td>");*/
           /*if(resultEntryVOGroupByValidatedBy.getIsReportChange().equals("1")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("2")||resultEntryVOGroupByValidatedBy.getIsReportChange().equals("3"))
           {
           	headerString.append("<td colspan='2'><div align='left'><font size='2'><b>Addendum Date</b></font></div></td>");
               headerString.append("<td colspan='3'><div align='left'>: <font size='1'>" + resultEntryVOGroupByValidatedBy.getAddendumDate()+ "</font></div></td>");
           }
           else
           {
           headerString.append("<td colspan='2'></td>");
           headerString.append("<td colspan='3'></td>");
           }*/
           
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
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 25));
        } else {
            resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
        }
        }
        else
        {
            if (resultEntryVOGroupByValidatedBy.getPrintingType() != null && resultEntryVOGroupByValidatedBy.getPrintingType().equals("1")) {
                headerString.append(lineString);
                headerString.append(standardRangesString);
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
            } else {
                resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
            }
        	
        	
        	
        }
        }
        //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 2));
        resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 12));
        headerString.append(lineString);
        headerString.append("</table>");
        //setPageHeaderHeight(resultEntryVOGroupByValidatedBy);

        // Log(Level.INFO, "headerString------->" + headerString.toString());
        resultEntryVOGroupByValidatedBy.setHeader(headerString.toString());

    }

    public static synchronized void getDefaultHeaderUnRegisteredPatient(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        StringBuffer headerString = new StringBuffer();
        String hospitalString = "<table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%' colspan='13'><font color='black' size='5'><b>" + Config.hospitalnameatreport + "</b></font></td></tr><tr><td align='center'width='100%' colspan='13'><font color='black' size='4'><b>" + Config.hospitaladdressatreport + "</b></font></td></tr>";
        headerString.append(hospitalString);

        headerString.append("<tr align='center'><td colspan='13' width='100%'><font color='black' size='2'>Dept. of " + resultEntryVOGroupByValidatedBy.getDepartmentName() + "</font></td></tr>");

        headerString.append("<tr><td width='50%' colspan='3'><font color='black' size='2'><b>Laboratory :</b>" + resultEntryVOGroupByValidatedBy.getLaboratoryName() + "</font></td>");
        /*change reuqest 29 dec 2010*/
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
        /*change reuqest 29 dec 2010*/
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

        /*if(resultEntryVOGroupByValidatedBy.getSampleNameList()!=null && resultEntryVOGroupByValidatedBy.getSampleNameList().size()!=0)  
         {
         String sampleNameString=""; 
         for(int i=0;i<resultEntryVOGroupByValidatedBy.getSampleNameList().size();i++)
         {
         if(i==0)
         {
         sampleNameString=resultEntryVOGroupByValidatedBy.getSampleNameList().get(i);
         }
         else
         {
         sampleNameString=","+resultEntryVOGroupByValidatedBy.getSampleNameList().get(i);
         }
         }
			
         headerString.append("<td width='16%'><div align='left'><b>Sample </b></div></td>");
         headerString.append("<td width='17%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>"+sampleNameString+"</font></div></td>");
         }*/
        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().size() != 1) {
            headerString.append("<td width='15%'><div align='left'><b>&nbsp;</b></div></td>");
            headerString.append("<td width='18%'><div align='left'>&nbsp;</div></td>");
        } else {
            headerString.append("<td width='15%'><div align='left'><b>Test</b></div></td>");
            headerString.append("<td width='18%'><div align='left'>:&nbsp;<font style='text-transform: capitalize;'>" + resultEntryVOGroupByValidatedBy.getTestName() + "</font></div></td>");
        }
        headerString.append("<tr>");

        if (resultEntryVOGroupByValidatedBy.getCurrentDiagnosis() != null) {
           /* headerString.append("<tr><td width='10%'><div align='left'><font size='2'><b>Diagnosis</b></font></div></td><td width='90%' colspan='5'><div align='left'>: <font size='2'>" + resultEntryVOGroupByValidatedBy.getCurrentDiagnosisName() + "</font></div></td></tr>");*/
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
        headerString.append("<div ><table width='100%' height='5%' cellspacing='0' cellpadding='0' border='0' '>");
      //  headerString.append(lineString);
        
        if(resultEntryVOGroupByValidatedBy.getIsReportChange()!=null && !resultEntryVOGroupByValidatedBy.getIsReportChange().equals("0") )
        {
        	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='1'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        	headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'><b>Report Modified By: </b>" + resultEntryVOGroupByValidatedBy.getReportChangedBy() + "</font></div></td></tr>");
        	
        }
        else{
    /*    if (resultEntryVOGroupByValidatedBy.getLaboratoryCode().equals("10401")) {

            headerString.append("<tr><td align='right' colspan='3'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='3'><div align='right'><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getResultEnteredBy() + "</font></div><div align='right'><font color='black' size='1'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "(" + resultEntryVOGroupByValidatedBy.getValidatedDate() + ")</font></div></td></tr>");
        } else {*/
        	
        	if(resultEntryVOGroupByValidatedBy.getIsDeptEntry().equals("1"))
        	{
        		if(resultEntryVOGroupByValidatedBy.getIsNablAccritedTest()!=null && resultEntryVOGroupByValidatedBy.getIsNablAccritedTest().equals("1"))
        		{
        			String 	testcodes=PGDataHelper.getInstance().getTestCodesAccrebited(resultEntryVOGroupByValidatedBy.getHospitalCode());
                  	String 	TestNames=PGDataHelper.getInstance().getTestNameAccrebited(testcodes);
                  	String NablLogo = PropertiesHelper.getNablLogoPath();
                  	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='2'><b>" +"\"*"+ TestNames + " Tests are NABL accredited.\" </b></font></div></td></tr>");
                  	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='1'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
                  	//headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy() + "</font></div><div align='right'><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "</font></div></td></tr>");
                  	headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "</font></div></td></tr>");
        		}
        		else{
        			headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='1'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        			//headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy() + "</font></div><div align='right'><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "</font></div></td></tr>");
        			headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='2'><b>Validated By: </b>" + ((resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()==null || resultEntryVOGroupByValidatedBy.getDeptResultModifyBy().equals("")) ?resultEntryVOGroupByValidatedBy.getDeptResultEnteredBy():resultEntryVOGroupByValidatedBy.getDeptResultModifyBy()) + "</font></div></td></tr>");
        		}
        		
        	
        	}	/*else  if(resultEntryVOGroupByValidatedBy.getIsNablAccritedTest()!=null && resultEntryVOGroupByValidatedBy.getIsNablAccritedTest().equals("1"))
          		
          	{
          		String 	testcodes=PGDataHelper.getInstance().getTestCodesAccrebited(resultEntryVOGroupByValidatedBy.getHospitalCode());
          	String 	TestNames=PGDataHelper.getInstance().getTestNameAccrebited(testcodes);
          	String NablLogo = PropertiesHelper.getNablLogoPath();
          	headerString.append("<tr><td align='left' colspan='12'><div align='left'  ><font color='black' size='2'><b>" +"** "+ TestNames + " Tests are NABL accredited. </b></font></div></td><td align='right' colspan='1'><div align='right' > <img src='" + NablLogo + "' height='40' width='50'/></div></td></tr>");
          	headerString.append("<tr ><td align='right' colspan='6'><div align='left' ><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right' ><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getResultEnteredBy() + "</font></div><div align='right'  ><font color='black' size='1'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "</font></div></td></tr>");
          	
          	}*/
        		else {
        			
        			if(resultEntryVOGroupByValidatedBy.getIsNablAccritedTest()!=null && resultEntryVOGroupByValidatedBy.getIsNablAccritedTest().equals("1"))
        			{
        				String 	testcodes=PGDataHelper.getInstance().getTestCodesAccrebited(resultEntryVOGroupByValidatedBy.getHospitalCode());
        	          	String 	TestNames=PGDataHelper.getInstance().getTestNameAccrebited(testcodes);
        	          	String NablLogo = PropertiesHelper.getNablLogoPath();
        	          	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='2'><b>" +"\"*"+ TestNames + " Tests are NABL accredited.\" </b></font></div></td></tr>");
        	          	headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='1'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        	          	//headerString.append("<tr ><td align='right' colspan='6'><div align='left' ><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right' ><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getResultEnteredBy() + "</font></div><div align='right'  ><font color='black' size='2'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "</font></div></td></tr>");
        	          	headerString.append("<tr ><td align='right' colspan='6'><div align='left' ><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'  ><font color='black' size='2'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "</font></div></td></tr>");
        				
        			}
        			else{
        				headerString.append("<tr><td align='left' colspan='13'><div align='left'  ><font color='black' size='1'><b>" + Config.disclaimeratreport + " </b></font></div></td></tr>");
        				//headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='1'><b>Entered By: </b>" + resultEntryVOGroupByValidatedBy.getResultEnteredBy() + "</font></div><div align='right'><font color='black' size='2'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "</font></div></td></tr>");
        				headerString.append("<tr><td align='right' colspan='6'><div align='left'><font color='black' size='1'><b>" + Config.specialstringatreport + " </b></font></div></td><td align='right' colspan='7'><div align='right'><font color='black' size='2'><b>Validated By: </b>" + resultEntryVOGroupByValidatedBy.getValidatedBy() + "</font></div></td></tr>");
        			}
                      
        		
        		
        		}
            //  }
            
        }
        
       
        //headerString.append("<tr><td>" + ". "+ "<br></td></tr>");
        headerString.append(lineString);
        headerString.append("</table></div>");
        //Log(Level.INFO,"getDefaultFooter "+headerString.toString());
        resultEntryVOGroupByValidatedBy.setFooter(headerString.toString());
        System.out.println("footerrr:"+headerString);
    }

    private static void Log(Level level, String msg) {
        ServiceLogger.Log(PDFPrintingProcesses.class.getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(PDFPrintingProcesses.class.getName(), level, e);
    }

}
