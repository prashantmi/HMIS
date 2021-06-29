package emr.dataview;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.registration.PatientVO;
import ehr.EHRConfig;
import ehr.followup.vo.EHRSection_FollowupVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class EHRSection_HOSPITALHEADER_UTL extends ControllerUTIL
{
	
    private static Configuration prepareConfiguration()
    {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //configuration.setLogTemplateExceptions(false);
        return configuration;
    }
    
    public static String getSectionPDFCompHTMLView(HttpServletRequest request)
	{
		Configuration cfg = prepareConfiguration();
		cfg.setClassForTemplateLoading(EHRSection_HOSPITALHEADER_UTL.class, "/emr/dataview/spp/ftl");
		String strHtmlCode = null;
		try
		{
			HttpSession session = request.getSession();
			//UserVO userVO = getUserVO(request);
			HospitalMstVO voHospital = (HospitalMstVO) session.getAttribute(EHRConfig.HOSPITAL_DEATILS);

			//Status objStatus = new Status();
			//PatientVO objPatientVo=new PatientVO();
			//EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS);
			//HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
			//userVO =ControllerUTIL.getUserVO(request);
			//String opdlocal=userVO.getUsrName();

			//	Load template from source folder
			Template template = cfg.getTemplate("spp_view_HOSPITALHEADER.ftl");
		
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("voHospital", voHospital);

			/*
			// Console output
			Writer out = new OutputStreamWriter(System.out);
			template.process(data, out);
			out.flush();
			// File output
			Writer file = new FileWriter (new File("E:\\FTL_helloworld.txt"));
			template.process(data, file);
			file.flush();
			file.close();
			*/

			// String Output
			StringWriter out = new StringWriter();
			template.process(data, out);
			out.flush();
			strHtmlCode = out.toString();
		
		
				/*StringBuffer str = new StringBuffer("");
				//WebRowSet ws = null;
				int count = 0, j = 0;
				String strHiddenVal="";
				//String file = "pdf";
				String path = "H:\\";
				File tempFile=null;
		
			
			str.append("<div class='container' id='patientProfile'>");
			str.append("<div class='row'>");
				str.append("<div class='col-xs-4'>");
					str.append("<img src='/HISClinical/hisglobal/images/logo_10911.jpg' alt='logo' height='100px;' />");
				str.append("</div>");
				str.append("<div class='col-xs-5'>");
					str.append("<div class='row' style='text-align:center;'>");
						str.append("<h4 ><font color='#000000'  face='Verdana, Arial, Helvetica, sans-serif'><b> ALL INDIA INSTITUTE OF MEDICAL SCIENCES PATNA </b></font></h4>");
						str.append("<h5><font color='#000000' face='Verdana, Arial, Helvetica, sans-serif'> Phulwarisharif </font></h5>");
						str.append("<h5><font color='#000000'  face='Verdana, Arial, Helvetica, sans-serif'>   </font></h5>");
					str.append("</div>");
				str.append("</div>");
			
				str.append("<div class='row'>");
					str.append("<div class='col-sm-4'>");
					str.append("</div>");
					str.append("<div class='col-sm-5' style='text-align:center;'>");
						str.append("<h4><font color='#000000' face='Verdana, Arial, Helvetica, sans-serif'><b>OPD Prescription</b></font></h4>");
		 			str.append("</div>");
				str.append("</div>");
			str.append("</div>");
		  str.append("</div>");
		//str.toString();
		String strHtmlCode = str.toString();
		 * */
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	    return strHtmlCode;
	}	
	
	
}
