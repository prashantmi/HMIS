package hisglobal.utility;

import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HisFileControlUtil;
//import hisglobal.utility.HttpServletRequest;
import hisglobal.vo.HospitalMstVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import ehr.vo.EHRVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class HisFTLToHTML
{
    public static Configuration prepareConfiguration()
    {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //configuration.setLogTemplateExceptions(false);
        return configuration;
    }
    
    public static Configuration prepareConfiguration(Class cls, String pathFTLSource)
    {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //configuration.setLogTemplateExceptions(false);
        configuration.setClassForTemplateLoading(cls, pathFTLSource);
        return configuration;
    }

    public static String generateHTMLFromFTL(Configuration cfg, String strNameFTLFile, String strNameObjData, Object objData)
    {
		String strHtmlCode = null;
    	try
    	{
			// Build the data-model
			Map<String, Object> mapData = new HashMap<String, Object>();
			mapData.put(strNameObjData, objData);
	
			//	Load template from source folder
			Template template = cfg.getTemplate(strNameFTLFile);
			StringWriter out = new StringWriter();
			template.process(mapData, out);
			out.flush();
			strHtmlCode = out.toString();
			System.out.println("FTLToHTML::"+strNameFTLFile+"->"+strHtmlCode);
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			strHtmlCode = null;
		}
        return strHtmlCode;
    }

    public static void main(String[] args)
	{
		Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(HisFTLToHTML.class, "/emr/dataview/spp/ftl");
		String strHtmlCode = null;
		try
		{
			EHRVO voEHR = new EHRVO();

			HospitalMstVO voHospital = new HospitalMstVO();
			//voHospital.setHospitalCode("10911");
			//voHospital.setAddress1("jdfkghdkjfhgd");
			voEHR.setVoHospital(voHospital);
		
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voEHR", voEHR);

			//	Load template from source folder
			Template template = cfg.getTemplate("DOC_HDR_HOS_DTL_1.ftl.ftl");
			/*
			// Console output
			Writer out = new OutputStreamWriter(System.out);
			template.process(data, out);
			out.flush();*/
			// File output
			Writer file = new FileWriter (new File("F:/Image/spp_view_HDR_HOSPITAL_1.html"));
			template.process(data, file);
			file.flush();
			file.close();
			// String Output
			StringWriter out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode = out.toString();
			System.out.println(strHtmlCode);

			//-----htmltopdf-----
			/*ByteArrayOutputStream baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(strHtmlCode);
			HisFileControlUtil hfcu = new HisFileControlUtil("abc.pdf", "F:\\Image\\", "/opt/AIIMSP");
		    hfcu.setFileContent(baosPDF.toByteArray());
		    hfcu.saveFile();*/
                   
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		try
		{
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
