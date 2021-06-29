package emr.dataview;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HTMLToPDFUTIL;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;









//import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPageEventHelper;
//import com.itextpdf.text.pdf.PdfWriter;









import ehr.EHRConfig;
import ehr.vo.EHRVO;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
//import hisglobal.utility.HttpServletRequest;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import com.itextpdf.text.Document;

public class FTLHelloWorld extends PdfPageEventHelper
{
	
	
	
    private static Configuration prepareConfiguration()
    {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //configuration.setLogTemplateExceptions(false);
        return configuration;
    }
   
    //Added by Dheeraj to return baos Object on 15-Nov-2018
    public static ByteArrayOutputStream getByteArray(HttpServletRequest request, EHRVO voEHR)
    {
    	String strHtmlCode="";
		ByteArrayOutputStream byteArray = null;
    	Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(EHRSection_HOSPITALHEADER_UTL.class, "/emr/dataview/spp/ftl");
		
		try
		{
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);

	//----department of general medicines------//		
			
			//	Load template from source folder DOC_HDR_HOS_DTL_1
			Template template = cfg.getTemplate("DOC_HDR_HOS_DTL_1.ftl");
			// String Output
			StringWriter out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
   
			//Load template from source folder DOC_FRM_HOR_LINE_2
			 template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			

			//Load template from source folder DOC_HDR_DEPT_DTL_1
			 template = cfg.getTemplate("DOC_HDR_DEPT_DTL_1.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder DOC_HDR_DOC_DTL_1
			template = cfg.getTemplate("DOC_HDR_DOC_DTL_1.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder PAT_DTL_PRESCIPTION
			template = cfg.getTemplate("PAT_DTL_PRESCRIPTION.ftl");
//			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			
//			
//			
			//Load template from source folder DOC_FRM_HOR_LINE_2
			template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
//			//Load template from source folder ENC_DTL_DIAG_DTL_1
		template = cfg.getTemplate("ENC_DTL_DIAG_DTL_1.ftl");
//			// String Output
		out = new StringWriter();
		template.process(data, out);
		out.flush();
		strHtmlCode += out.toString();
//			//System.out.println(strHtmlCode);
//			
			//Load template from source folder DOC_FOLLOWUP_DTL
			template = cfg.getTemplate("DOC_FOLLOWUP_DTL.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			
			//Load template from source folder DOC_FTR_SIGNS_2
			template = cfg.getTemplate("DOC_FTR_SIGNS_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			System.out.println(strHtmlCode);
			// File output
			/*Writer file = new FileWriter (new File("F:/Image/spp_view_HDR_HOSPITAL_1.html"));
			file.write(strHtmlCode);
			//template.process(data, file);
			file.flush();
			file.close();*/
			//-----htmltopdf-----
			ByteArrayOutputStream baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(request,strHtmlCode);
			byteArray = baosPDF;
			/*HisFileControlUtil hfcu = new HisFileControlUtil("abcTrial.pdf", "H:/", "/root/");
		    hfcu.setFileContent(baosPDF.toByteArray());
		    hfcu.saveFile();*/
                   
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	return byteArray;
    }
    
    
    /**Added by Vasu on 19.Nov.2018*/
    public static String getHtmlContent(HttpServletRequest request, EHRVO voEHR)
    {
    	String strHtmlCode="";
		Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(EHRSection_HOSPITALHEADER_UTL.class, "/emr/dataview/spp/ftl");
		
		try
		{
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);

			//	----department of general medicines------//		
			//	Load template from source folder DOC_HDR_HOS_DTL_1
			Template template = cfg.getTemplate("DOC_HDR_HOS_DTL_1.ftl");
			// String Output
			StringWriter out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
   
			//Load template from source folder DOC_FRM_HOR_LINE_2
			 template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			

			//Load template from source folder DOC_HDR_DEPT_DTL_1
			 template = cfg.getTemplate("DOC_HDR_DEPT_DTL_1.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder DOC_HDR_DOC_DTL_1
			template = cfg.getTemplate("DOC_HDR_DOC_DTL_1.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder PAT_DTL_PRESCIPTION
			template = cfg.getTemplate("PAT_DTL_PRESCRIPTION.ftl");
//			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			
//			
//			
			//Load template from source folder DOC_FRM_HOR_LINE_2
			template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			
//			//Load template from source folder ENC_DTL_DIAG_DTL_1
		     template = cfg.getTemplate("ENC_DTL_DIAG_DTL_1.ftl");
//			// String Output
		     out = new StringWriter();
		     template.process(data, out);
		     out.flush();
		     strHtmlCode += out.toString();
			
		     //Treatment
		     template = cfg.getTemplate("ENC_DTL_TREATMENT_DTL.ftl");
//		  // String Output
	         out = new StringWriter();
	         template.process(data, out);
	         out.flush();
	         strHtmlCode += out.toString();
		
		   //End Treatment
	     
	         //Investigation
			 template = cfg.getTemplate("PAT_INVEST_DTL_1.ftl");
//			// String Output
		     out = new StringWriter();
		     template.process(data, out);
		     out.flush();
		     strHtmlCode += out.toString();
			
			//End Treatment
//			//System.out.println(strHtmlCode);
//			
			//Load template from source folder DOC_FOLLOWUP_DTL
			template = cfg.getTemplate("DOC_FOLLOWUP_DTL.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			
			//Load template from source folder DOC_FTR_SIGNS_2
			template = cfg.getTemplate("DOC_FTR_SIGNS_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			System.out.println(strHtmlCode);
			
                   
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	return strHtmlCode;
    }
    
    
    /**Added by Vasu on 29.Nov.2018*/
    public static String getHtmlContentForOrthopaedicsDischargeSummary(HttpServletRequest request, EHRVO voEHR)
    {
    	String strHtmlCode="";
		Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(EHRSection_HOSPITALHEADER_UTL.class, "/emr/dataview/spp/ftl");
		
		try
		{
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);

			//	----department of general medicines------//		
			//	Load template from source folder DOC_HDR_HOS_DTL_1
			Template template = cfg.getTemplate("DOC_HDR_HOS_DTL_1.ftl");
			// String Output
			StringWriter out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
   
			//Load template from source folder DOC_FRM_HOR_LINE_2
			 template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			

			//Load template from source folder DOC_HDR_DEPT_DTL_1
			 template = cfg.getTemplate("DOC_HDR_DEPT_DTL_1.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder DOC_HDR_DOC_DTL_1
			template = cfg.getTemplate("DOC_HDR_DOC_DTL_1.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder PAT_DTL_PRESCIPTION
			template = cfg.getTemplate("PAT_DTL_PRESCRIPTION.ftl");
//			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			
//			
//			
			//Load template from source folder DOC_FRM_HOR_LINE_2
			template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			
//			//Load template from source folder ENC_DTL_DIAG_DTL_1
		     template = cfg.getTemplate("cheifComplaints.ftl");
//			// String Output
		     out = new StringWriter();
		     template.process(data, out);
		     out.flush();
		     strHtmlCode += out.toString();
			
		     //Treatment
		     template = cfg.getTemplate("clinicalHistory.ftl");
//		  // String Output
	         out = new StringWriter();
	         template.process(data, out);
	         out.flush();
	         strHtmlCode += out.toString();
		
		   //End Treatment
	     
	         //Investigation
			 template = cfg.getTemplate("physicalExamination.ftl");
//			// String Output
		     out = new StringWriter();
		     template.process(data, out);
		     out.flush();
		     strHtmlCode += out.toString();
			
			
			//Load template from source folder DOC_FTR_SIGNS_2
			template = cfg.getTemplate("DOC_FTR_SIGNS_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			System.out.println(strHtmlCode);
			
                   
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	return strHtmlCode;
    }
    
    
    /**Added by Vasu on 29.Nov.2018*/
    public static String getHtmlContentForDefaultDischargeSummary(HttpServletRequest request, EHRVO voEHR)
    {
    	String strHtmlCode="";
		Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(EHRSection_HOSPITALHEADER_UTL.class, "/emr/dataview/spp/singlePageFtl");	
		//List lstClinicalSectionEssentials = null;
		List<PatientClinicalDocDetailVO> lstSectionWiseClinicalDocTemplatePrint= new ArrayList<PatientClinicalDocDetailVO>();
		try
		{
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);   		
			//Template iteration logic starts here Added by Vasu on 06.Dec.2018
			List lstTemplates = voEHR.getListClinicalDocEssentials();//(List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
			if(lstTemplates!=null)
			{
				
				for(int i=0;i<lstTemplates.size();i++)
				{
					PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstTemplates.get(i);
					String templateKey = vo.getClinicalSecTemplateKey();
					Template template = null;
					if(vo.getClinicalSecTemplateContent()!=null && !vo.getClinicalSecTemplateContent().trim().equals(""))
					{
						StringTemplateLoader stringLoader = new StringTemplateLoader();
						String firstTemplate = "";
						System.out.println(vo.getClinicalSecTemplateContent());
						stringLoader.putTemplate(firstTemplate, vo.getClinicalSecTemplateContent());
						Configuration cfg1 = new Configuration();
						cfg1.setTemplateLoader(stringLoader);
						System.out.println(stringLoader);
						template = cfg1.getTemplate(firstTemplate);
					}
					else
					{
						System.out.println(templateKey);
						template = cfg.getTemplate(templateKey+".ftl");
					}
				    StringWriter out = new StringWriter();
					template.process(data, out);
					out.flush();
					vo.setHtmlString(out.toString());
				    lstSectionWiseClinicalDocTemplatePrint.add(vo);
				    WebUTIL.setAttributeInSession(request, EHRConfig.CLINICAL_SECTION_COMP_SECTIONS_PRINT_LIST, lstSectionWiseClinicalDocTemplatePrint);
				    
					/*lstClinicalSectionEssentials.add(out.toString());
					Iterator listIterator=lstTemplates.iterator();
					while(listIterator.hasNext())
					{
						List list=(List)listIterator.next();
						Entry entry=new Entry();
						entry.setValue((String)list.get(2));
						entry.setLabel((String)list.get(1));
						lstClinicalSectionEssentials.add(entry);
						Entry entry1=new Entry();
						entry1.setValue((String)list.get(2));
						entry1.setLabel((String)list.get(0));
						lstClinicalSectionEssentials.add(out.toString());
					}*/
					strHtmlCode += out.toString();
			    }
			}

			System.out.println(strHtmlCode);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	return strHtmlCode;
    }
    
    
    /**Added by Vasu on 29.Nov.2018*/
    public static String getHtmlContentForGeneralMedicineDischargeSummary(HttpServletRequest request, EHRVO voEHR)
    {
    	String strHtmlCode="";
		Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(EHRSection_HOSPITALHEADER_UTL.class, "/emr/dataview/spp/ftl");
		
		try
		{
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);

			//	----department of general medicines------//		
			//	Load template from source folder DOC_HDR_HOS_DTL_1
			Template template = cfg.getTemplate("DOC_HDR_HOS_DTL_1.ftl");
			// String Output
			StringWriter out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
   
			//Load template from source folder DOC_FRM_HOR_LINE_2
			 template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			

			//Load template from source folder DOC_HDR_DEPT_DTL_1
			 template = cfg.getTemplate("DOC_HDR_DEPT_DTL_1.ftl");
			// String Output
			 out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder DOC_HDR_DOC_DTL_1
			template = cfg.getTemplate("DOC_HDR_DOC_DTL_1.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			//Load template from source folder PAT_DTL_PRESCIPTION
			template = cfg.getTemplate("PAT_DTL_PRESCRIPTION.ftl");
//			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			
//			
//			
			//Load template from source folder DOC_FRM_HOR_LINE_2
			template = cfg.getTemplate("DOC_FRM_HOR_LINE_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			
//			//Load template from source folder ENC_DTL_DIAG_DTL_1
		     template = cfg.getTemplate("ENC_DTL_DIAG_DTL_1.ftl");
//			// String Output
		     out = new StringWriter();
		     template.process(data, out);
		     out.flush();
		     strHtmlCode += out.toString();
			
		     //Treatment
		     template = cfg.getTemplate("ENC_DTL_TREATMENT_DTL.ftl");
//		  // String Output
	         out = new StringWriter();
	         template.process(data, out);
	         out.flush();
	         strHtmlCode += out.toString();
		
		   //End Treatment
	     
	         //Investigation
			 template = cfg.getTemplate("PAT_INVEST_DTL_1.ftl");
//			// String Output
		     out = new StringWriter();
		     template.process(data, out);
		     out.flush();
		     strHtmlCode += out.toString();
			
			//End Treatment
//			//System.out.println(strHtmlCode);
//			
			//Load template from source folder DOC_FOLLOWUP_DTL
			template = cfg.getTemplate("DOC_FOLLOWUP_DTL.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			
			//Load template from source folder DOC_FTR_SIGNS_2
			template = cfg.getTemplate("DOC_FTR_SIGNS_2.ftl");
			// String Output
			out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			//System.out.println(strHtmlCode);
			
			System.out.println(strHtmlCode);
			
                   
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	return strHtmlCode;
    }
    
    //Added by Vasu on 04.Feb.2019
    public static String getHtmlContentForDischargeSummaryPrint(UniPagePrescriptionFB fb,HttpServletRequest request, EHRVO voEHR)
    {
    	String sectionCode = "";
    	String templateCode = ""; 
    	String templateData = "";
    	String[] chk = fb.getChkSelectedSections().split("\\#");
    
    	String strHtmlCode="";
		Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(EHRSection_HOSPITALHEADER_UTL.class, "/emr/dataview/spp/singlePageFtl");	
		try
		{
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);   		
			List lstTemplates = voEHR.getListClinicalDocEssentials();
			Template template = null;
			if(lstTemplates!=null)
			{
			for(int i=0;i<lstTemplates.size();i++)
			{
				
				PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstTemplates.get(i);
			
				for(int j = 0;j<chk.length;j++)
		    	{
		    		sectionCode = chk[j].split("\\^")[0];	
		    		templateCode = chk[j].split("\\^")[1];
		    		if(vo.getClinicalSectionCode().equals(sectionCode) && vo.getClinicalSecCompMappingVnd().equals(templateCode))
		    		{
		    			 templateData = templateData+vo.getClinicalSecTemplateContent();
		    		}
		    	}
				
			}   
			}
			if(templateData!=null && !templateData.trim().equals(""))
			{
				StringTemplateLoader stringLoader = new StringTemplateLoader();
				String firstTemplate = "";
				System.out.println(templateData);
				stringLoader.putTemplate(firstTemplate, templateData);
				Configuration cfg1 = new Configuration();
				cfg1.setTemplateLoader(stringLoader);
				System.out.println(stringLoader);
				template = cfg1.getTemplate(firstTemplate);
			}
			StringWriter out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode += out.toString();
			
			System.out.println(strHtmlCode);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	return strHtmlCode;
    }
    
}//End of Class
