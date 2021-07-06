package hisglobal.presentation;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.UserVO;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class ReportUTIL extends ControllerUTIL
{

	public static byte[] generateTextReport(ReportFB _fb, HttpServletRequest _request, ServletContext _contx, HttpServletResponse _response) throws HisReportDataNotFoundException
	{

		StringBuffer bf = new StringBuffer();
		String url=_request.getScheme()+"://"+_request.getServerName()+":"+_request.getServerPort();
		System.out.println("URL:= "+url);
		
		setModeInSession(_fb, _request, _response);
		UserVO userVO = getUserVO(_request);
		try
		{
			ReportVO _reportVO = new ReportVO();
			HelperMethods.populate(_reportVO, _fb);
			HospitalMstVO hospitalVO=getHospitalVO(_request);
			
			_reportVO.setHospitalCode(userVO.getHospitalCode());
			_reportVO.setPoorFree(Config.PRIMARY_CATEGORY_POOR_FREE_CODE);
			
			_reportVO.setHospitalName(hospitalVO.getHospitalName());
			_reportVO.setHospitalAdd(hospitalVO.getAddress1()+hospitalVO.getAddress2()+","+hospitalVO.getCity()+","+hospitalVO.getStateName()+"-"+ hospitalVO.getPinCode()+" (INDIA)");
			_reportVO.setHospitalContact("Phone:-"+hospitalVO.getPhone()+",Fax:-"+hospitalVO.getFax()+",Email-id:"+hospitalVO.getEmail());
			/*_reportVO.setHospitalAdd(hospitalVO.getCity()+"-"+ hospitalVO.getPinCode()+" (INDIA)");*/
			/*_reportVO.setHospitalContact("");*/
			/*_reportVO.setHospitalId(hospitalVO.getHospitalShortName());*/
			_reportVO.setImageURL(url);
			/**Added by Vasu on 5.July.2018 to set hospital logo*/
			//String hospitalLogoUrl =_request.getScheme()+"://"+_request.getServerName()+":"+_request.getServerPort()+"/HISClinical/hisglobal/images/NIMSLOGO.png";
			//_reportVO.setHospitalLogo(hospitalLogoUrl);
			/**End Vasu*/
			JasperPrint jPrint = ReportDATA.generateReport(_reportVO);

		
		if (_fb.getReportType().equalsIgnoreCase(Config.PDF))
		{
			byte[] byteArrayAspdf = JasperExportManager.exportReportToPdf(jPrint);
			_response.setContentType("application/pdf");
			return byteArrayAspdf;
		}
		
			/*if (_fb.getReportType().equalsIgnoreCase(Config.EXCEL))
			{
				JExcelApiExporter xlsExporter = new JExcelApiExporter();
				xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
				xlsExporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,bf);
				//xlsExporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,"c:/report.xls");
				//xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,new String['a']);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
				xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{"a"});
				xlsExporter.exportReport();
				JRXlsExporter xlsExporter = new JRXlsExporter();
				xlsExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jPrint);
				xlsExporter.setParameter(JRXlsExporterParameter.OUTPUT_STRING_BUFFER,bf);
				xlsExporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,"c:/report.xls");
				//xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,new String['a']);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
				xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{"a"});
				xlsExporter.exportReport();
				_response.setContentType("application/ms-excel");
				

				return bf.toString().getBytes();
				
			}*/
		/*	if (_fb.getReportType().equalsIgnoreCase(Config.RTF))
			{
				
				JRRtfExporter exporter = new JRRtfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, bf);

				exporter.exportReport();
				_response.setContentType("application/rtf");
				
				

				return bf.toString().getBytes();
				
			}*/
			
			String header="<html>\n <head>\n <title></title>\n <meta http-equiv=\"Content-Type\" " +
						"content=\"text/html;\"/>\n  <style type=\"text/css\">\n " +
						"@media print { div { display: none; }} </style>\n <script Language=javascript>" +
						"function printPage() \n {\n //var frameElement = parent.document.getElementById(\"f2\");  " +
						"\n //var win = frameElement.contentWindow ;\n " +
						" \n window.print(); \n " +
						"  \n } \n\n " +
						"function backButton() \n { \n  window.frameElement.src=window.frameElement.src; \n } \n " +
						"</script> </head>\n <body text=\"#000000\" link=\"#000000\" " +
						"alink=\"#000000\" vlink=\"#000000\">\n <table width=\"100%\" cellpadding=\"0\" " +
						"cellspacing=\"0\" border=\"0\">\n <tr><td width=\"50%\">&nbsp;</td><td align=\"center\">\n \n "
						+ "<div id=\"divOPDCardLogo\"><img src='/HISClinical/hisglobal/images/NIMSLOGO.png' style='width:120px;height:120px;'></div>";
			//"cellspacing=\"0\" border=\"0\">\n <tr><td width=\"50%\" align=\"center\"></td><td align=\"center\">\n \n";
			
			//String buttonHtml="<div id=\"divOPDCardLogo\"><img src='/HISClinical/hisglobal/images/Card-Logo.PNG'></div>";
			//JRHtmlExporter htmlExporter = new JRHtmlExporter();
			//htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, header);

			if (_fb.getReportType().equalsIgnoreCase(Config.RTF))
			{

				/////////Html for pritn and cancel//////
				//String html="<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>";
				String buttonHtml=" <div id=\"noPrint\" align=\"right\">"
						+ "<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-pnt.png' tabindex=\"1\" " +
						"style=cursor:pointer onclick=\"printPage()\" onkeypress=\"if(event.keyCode==13)" +
						" printPage()\"><img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-ccl.png' tabindex=\"1\" " +
						"style=cursor:pointer onclick=\"backButton()\" onkeypress=\"if(event.keyCode==13)" +
						" backButton()\"></div>" ;
				String footer="</td><td width=\"50%\">&nbsp;</td></tr>\n </table>\n </body>\n </html>\n";
				header=header+buttonHtml;
				HashMap fontMap = new HashMap();
				/*fontMap.put("sansserif", "Arial");
				fontMap.put("TextAttribute.SIZE", "10");
	*/			

				
				  JRHtmlExporter htmlExporter = new JRHtmlExporter();
				  htmlExporter.setParameter(JRHtmlExporterParameter. JASPER_PRINT, jPrint);
				  htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, header);
				  htmlExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, footer);
				 // htmlExporter.setParameter(JRHtmlExporterParameter.FONT_MAP, fontMap);
				  htmlExporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT,JRHtmlExporterParameter.SIZE_UNIT_POINT);
				 
				  htmlExporter.setParameter(JRHtmlExporterParameter.OUTPUT_STRING_BUFFER,bf);
				  htmlExporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,false);
				  htmlExporter.exportReport();

				//_response.setContentType("text/html");

				
				_response.setContentType("text/html");
				return bf.toString().getBytes();
			}
		}
		catch (HisReportDataNotFoundException e)
		{
			
			
			
								
		throw new HisReportDataNotFoundException();
		
		
		}
		catch (JRException e)
		{

			e.printStackTrace();
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		return null;

	}

	public static void setModeInSession(ReportFB _fb, HttpServletRequest _request, HttpServletResponse _response)
	{
		WebUTIL.setAttributeInSession(_request, "mode", _request.getParameter("mode"));

	}

	public static void generateGraph(ReportFB _fb, HttpServletRequest _request, HttpServletResponse _response)
	{

		//StringBuffer bf =new StringBuffer();
		OutputStream os;
		try
		{
			os = _response.getOutputStream();

			ReportVO _reportVO = new ReportVO();
			HelperMethods.populate(_reportVO, _fb);
			//_reportVO.setJrxmlName(jrxmlName);
			JFreeChart jFreeChart = ReportDATA.generateGraph(_reportVO);

			BufferedImage image = jFreeChart.createBufferedImage(590, 430);
			byte[] byteArray = ChartUtilities.encodeAsPNG(image);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			_response.setContentType("image/png");

			if (byteArray != null)
			{
				_response.setHeader("Pragma", "no-cache");
				bos.write(byteArray, 0, byteArray.length);
				_response.getOutputStream().flush();
				bos.close();

			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
