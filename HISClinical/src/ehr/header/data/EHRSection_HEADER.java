package ehr.header.data;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.DocumentException;

import vo.registration.PatientVO;
import ehr.EHRConfig;
import ehr.followup.vo.EHRSection_FollowupVO;
import ehr.header.HtmlToPdfConvertor;
import ehr.header.SamplePrescriptionToPDFA2;


public class EHRSection_HEADER extends ControllerUTIL
{
	/**
	##		Creation Date			: 	09-Oct-2018
	##		Reason	(CR/PRS)		: 	To create PDF file for Digital Sign
	##		Created By				: 	Dheeraj Solanki
	*/
	
	//public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			//throws Exception
	//{
		//return this.NEW(mapping, form, request, response);
		//return this.NEW(mapping, form, request, response);
		//getHospitalDetails(EHRSection_HEADERFB fb, HttpServletRequest request);
	//}
	
	public static String getHeader(HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		PatientVO objPatientVo=new PatientVO();

		HospitalMstVO hospVO = (HospitalMstVO)session.getAttribute(EHRConfig.HOSPITAL_DEATILS);
		EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS);
		HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
		userVO =ControllerUTIL.getUserVO(request);
		String opdlocal=userVO.getUsrName();

		StringBuffer str = new StringBuffer("");
		//WebRowSet ws = null;
		int count = 0, j = 0;
		String strHiddenVal="";
		String file = "pdf";
		String path = "H:\\";
		File tempFile=null;
		
		try
		{
			
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
		}
		
		catch (Exception e)
		{

			e.printStackTrace();
		}
		
		String strHtmlCode = str.toString();
		
		
/*		strHtmlCode = strHtmlCode.replace("&", "and");
		String strOrderDetails = "<html><head></head><body>" + strHtmlCode + "</body></html>";
		//System.out.println("strSampleSentLabel "+strOrderDetails);
		String strDate = new SimpleDateFormat("dd-MMM-yyyyHH-mm-SS").format(new Date());
		String pdfInputFileName = opdlocal + "_" + strDate + ".pdf";
		String strTempfileName = "temp" + strDate;
		try {
			tempFile = File.createTempFile(strTempfileName, ".pdf");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String strDirector = tempFile.getParentFile().getAbsolutePath();
		String strTempFilePath = strDirector + File.separator + strTempfileName + ".pdf";
		tempFile.delete();
		HtmlToPdfConvertor.convertHtmlToPDFAndSave(strOrderDetails, file, path);
		//HtmlToPdfConvertor.convertHtmlToPDFAndSave(response, strOrderDetails, strTempfileName, strDirector);
		//System.out.println("strTempFilePath "+strTempFilePath);
		
		File originalFile = new File(strTempFilePath);
	    String encodedBase64 = null;
	    try {
	        FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
	        byte[] bytes = new byte[(int) originalFile.length()];
	        try {
				fileInputStreamReader.read(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        encodedBase64 = hisglobal.utility.Base64Utils.base64Encode(bytes);
	       // System.out.println("strTempFilePath "+strTempFilePath);
	        System.out.println("encodedBase6423423423423423 "+encodedBase64);
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		*/
		/*try {
			SamplePrescriptionToPDFA2.convertPrescriptionToPDFA2(strHtmlCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//return str.toString();
	    return strHtmlCode;
		
	}	
	
	
	
}
