package hisglobal;

import hisglobal.utility.HisUtil;
import hisglobal.vo.HospitalMstVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.ReportEngine;

public class ReportUtil {


	private static final String pathFileName = "hisglobal.hisconfig.hisReport";
	
//	private String path = "";

	private String storagePath = "";
	
	/**
	 * Saves the Specified Report in the Configured Location based on Modules.
	 * Displays the Saved Report if required.
	 * 
	 * @param request  HttpServletRequest to get the Report Details.
	 * @param response HttpServletResponse to display the Report.
	 * @param reportPath partially specified Report Path.
	 * @param strModuleName Current Module.
	 * @param fileName Name of the File for the Report at the Storage Location.
	 * @param reportFormat pdf or html (other formats wont support)
	 * @param params a Map which contains Key, Value Parameters. (ds_conn_path property is set by default, no need to set this property)
	 * @param shouldDisplay true - should Display after saving the file. false - should not Display after saving the file.
	 * @throws Exception if any of the required parameter is <code>null</code> or empty.
	 */
	public void saveReport(HttpServletRequest request,
			HttpServletResponse response, String reportPath, String strModuleName,
			String fileName, String reportFormat, Map<String, Object> params,
			boolean shouldDisplay) throws Exception {

		EngineConfig conf = null;
		ReportEngine eng = null;
		IReportRunnable design = null;
		IRunAndRenderTask task = null;
		HTMLRenderOption options = null;
		ByteArrayOutputStream out = null;

		String storageLocation = null;

		boolean bRptFormat = true;
		
		String strFileNameTemp = fileName;
		
		File f = null;
		FileOutputStream fos = null;

		String strReportRealPath = "";

		try {

			out = new ByteArrayOutputStream();

			conf = new EngineConfig();

			eng = new ReportEngine(conf);

			if (strModuleName == null || strModuleName.equals("")) {
				throw new Exception("Invalid Module Name");
			}

			storageLocation = HisUtil.getParameterFromHisPathXML(strModuleName.toUpperCase()
					+ "_PATH");

			if (reportPath == null || reportPath.equals("")) {
				throw new Exception("Report Path is Blank");
			}

			strReportRealPath = request.getSession().getServletContext()
					.getRealPath(reportPath);

			design = eng.openReportDesign(strReportRealPath);

			
			
			task = eng.createRunAndRenderTask(design);

			options = new HTMLRenderOption();
	
			/*options.setImageHandler(new HTMLServerImageHandler());
			options.setImageDirectory(request.getSession().getServletContext().getRealPath("/images")); 
			options.setBaseImageURL(request.getContextPath()+"/images");
			*/
			if (reportFormat.equalsIgnoreCase("pdf")) {
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);

				strFileNameTemp = strFileNameTemp + ".pdf";

			} else {
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
				strFileNameTemp = strFileNameTemp + ".html";
			}
			options.setOutputStream(out);

			task.setRenderOption(options);

			if (params == null || params.isEmpty()) {

				params = new HashMap<String, Object>();

				params.put("jndi_ds_conn", this.getPath());
				params.put("report_Header", this.getStoragePath("REPORT_HEADER"));
				params.put("report_Address", this.getStoragePath("REPORT_ADDRESS"));
				params.put("report_Contact", this.getStoragePath("REPORT_CONTACT"));
				params.put("report_Footer", this.getStoragePath("REPORT_FOOTER"));
				
			} else {
				params.put("jndi_ds_conn", this.getPath());
				params.put("report_Header", this.getStoragePath("REPORT_HEADER"));
				params.put("report_Address", this.getStoragePath("REPORT_ADDRESS"));
				params.put("report_Contact", this.getStoragePath("REPORT_CONTACT"));
				params.put("report_Footer", this.getStoragePath("REPORT_FOOTER"));
			}

			
			if(!reportFormat.equals("pdf"))
				bRptFormat = false;
						
			params.put("rpt_format", bRptFormat);
			
			
			task.setParameterValues(params);

			// task.setParameter("fromDate",
			// request.getParameter("fromDate"),"fromDate");
			// task.setParameterValue("toDate", request.getParameter("toDate"));

			task.run();

			f = new File(storageLocation + File.separator + strFileNameTemp);

			fos = new FileOutputStream(f.getAbsolutePath());

			fos.write(out.toByteArray());

			if (shouldDisplay) {

				displayReportFromFile(response, strModuleName, fileName,
						reportFormat);
			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (fos != null) {
				fos.close();
				fos = null;
			}

			if (out != null)
				out = null;

			if (task != null) {
				task.close();
				task = null;
			}

			if (design != null)
				design = null;

			if (eng != null) {
				eng.destroy();
				eng = null;
			}

			if (conf != null)
				conf = null;
		}
	}

	/**
	 * Displays the Report from the Specified Report Path by retrieving the parameters. 
	 * 
	 * @param request  HttpServletRequest to get the Report Details.
	 * @param response HttpServletResponse to display the Report.
	 * @param strReportPath Fully Qualified Report Path.
	 * @param strReportFormat pdf or html (other formats wont support)
	 * @param params a Map which contains Key, Value Parameters.(ds_conn_path property is set by default, no need to set this property)
	 * @throws Exception if any of the required parameter is <code>null</code> or empty.
	 */
	public void displayReport(HttpServletRequest request,
			HttpServletResponse response, String strReportPath,
			String strReportFormat, Map<String, Object> params,String strHosCode) throws Exception {

		EngineConfig conf = null;
		ReportEngine eng = null;
		IReportRunnable design = null;
		IRunAndRenderTask task = null;
		HTMLRenderOption options = null;
		ByteArrayOutputStream out = null;

	//	HTMLRenderContext renderContext = null; 
	//	HashMap contextMap = null;
		String strFixHeader ="";
		boolean bRptFormat = true;
		
		String strReportRealPath = "";

		try {

			out = new ByteArrayOutputStream();

			conf = new EngineConfig();

			eng = new ReportEngine(conf);

			strReportRealPath = request.getSession().getServletContext()
					.getRealPath(strReportPath);

			design = eng.openReportDesign(strReportRealPath);

			HisUtil hisUtil=new HisUtil("Global","ReportUtil");
			
			HospitalMstVO hospitalVO=hisUtil.getHospitalDetail(strHosCode);
						
						
			task = eng.createRunAndRenderTask(design);

						
			options = new HTMLRenderOption();
			
			
			
			options.setImageDirectory("image");
			
			options.setImageHandler(new HTMLServerImageHandler());
			options.setImageDirectory(request.getSession().getServletContext().getRealPath("/images")); 
			options.setBaseImageURL(request.getContextPath()+"/images");
			
/*
			renderContext = new HTMLRenderContext();
			renderContext.setImageDirectory("C:\\image");
			contextMap = new HashMap();
			contextMap.put( EngineConstants.APPCONTEXT_HTML_RENDER_CONTEXT, renderContext );
			task.setAppContext( contextMap );
*/
			//options.setImageHandler( new HTMLServerImageHandler());

		
			
			if (strReportFormat.equalsIgnoreCase("pdf")) {
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition",
						"inline; filename=output.pdf");
					
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
				
				//fileName = fileName + ".pdf";

			} else if (strReportFormat.equalsIgnoreCase("xls")) {
				
				response.setContentType("application/xls");
				response.setHeader("Content-Disposition",
						"inline; filename=output.xls");
					
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
				
				//fileName = fileName + ".pdf";

			} else  {
				options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);

				response.setHeader("Content-Disposition",
				"inline; filename=output.html");

			}
			options.setOutputStream(out);

			task.setRenderOption(options);
			//	Start:Shivani Goel
			strFixHeader = "<script> var heightW=window.innerHeight-210; var tdForNavigator = document.createElement('td'); tdForNavigator.setAttribute('colspan', '3'); var divForNavigator = document.createElement('div'); divForNavigator.style.cssText ='height:'+heightW+'px;overflow:auto; border-bottom: 7px solid #1274B0;'; tdForNavigator.appendChild(divForNavigator); divForNavigator.className = 'x-panel-header'; divForNavigator.setAttribute('id', 'fixedtable'); var navigatorCell = document.getElementById('myElementId'); var navigatorMain = document.getElementById('myTable'); navigatorMain.style.cssText='border-collapse:collapse;empty-cells: show;width: 100%;'; navigatorCell.appendChild(tdForNavigator);  var theadForNavigator = document.createElement('thead'); theadForNavigator.style.cssText='display:table-header-group;border-top-style: solid;        border-top-width: 3px;';   var tdForHeader = document.createElement('td'); tdForHeader.setAttribute('colspan', '3'); var divForHeader = document.createElement('div'); var tableForHeader = document.createElement('table'); tableForHeader.setAttribute('id', 'tabheader'); tableForHeader.setAttribute('cellspacing', '0'); tableForHeader.style.cssText ='width:100%;'; divForHeader.appendChild(tableForHeader); tdForHeader.appendChild(divForHeader); var headerCell = document.getElementById('headerElementId'); var colgroup=document.getElementsByTagName('colgroup'); var navigator1 = document.getElementById('tableHeader'); if(navigatorMain.offsetHeight > heightW) { var thForScroll=document.createElement('th'); thForScroll.setAttribute('id', 'fixedtableThlast'); thForScroll.style.cssText ='padding: 0 14px 0 3px;width:1%;'; navigator1.appendChild(thForScroll); }  headerCell.appendChild(tdForHeader); tableForHeader.appendChild(navigator1.cloneNode(true)); tableForHeader.appendChild(colgroup[4].cloneNode(true)); theadForNavigator.appendChild(navigator1); navigatorMain.appendChild(theadForNavigator); divForNavigator.appendChild(navigatorMain); headerCell.deleteCell(0); navigatorCell.deleteCell(0); var navigatorHeader = document.getElementById('tableHeader'); navigatorHeader.setAttribute('id', 'firstHeader'); var navigatorHeaderHide = document.getElementById('tableHeader'); navigatorHeaderHide.style.cssText ='display:none;'; </script> ";
			
			
			String hospContact="",address="";
			
			if(hospitalVO.getPhone()!=null && !hospitalVO.getPhone().trim().equals(""))
				 hospContact+="Phone : "+hospitalVO.getPhone();
	  		if(hospitalVO.getFax()!=null && !hospitalVO.getFax().trim().equals(""))
	  			hospContact+=" Fax : "+hospitalVO.getFax().toUpperCase();
	  		if(hospitalVO.getEmail()!=null && !hospitalVO.getEmail().trim().equals(""))
	  			hospContact+=" Email : "+hospitalVO.getEmail();  
	  		  
	  		if(hospitalVO.getCity()!=null && !hospitalVO.getCity().trim().equals(""))
	  			address+=hospitalVO.getCity()+",";
	  		if(hospitalVO.getDistrictName()!=null && !hospitalVO.getDistrictName().trim().equals(""))
	  			address+=hospitalVO.getDistrictName();
	  		System.out.println("district name"+hospitalVO.getDistrictName());
	  		if(hospitalVO.getPinCode()!=null && !hospitalVO.getPinCode().trim().equals(""))
	  			address+="-"+hospitalVO.getPinCode()+",";
	  		if(hospitalVO.getState()!=null && !hospitalVO.getState().trim().equals(""))
		  		address+=hospitalVO.getState()+","+" INDIA "; 

			if (params == null || params.isEmpty()) {

				params = new HashMap<String, Object>();

				params.put("jndi_ds_conn", this.getPath());
				//params.put("report_Header", this.getStoragePath("REPORT_HEADER"));
				params.put("report_Header", hospitalVO.getHospitalName());
				params.put("report_Address1", ((hospitalVO.getAddress1()==""||hospitalVO.getAddress1()==null)?" ":(hospitalVO.getAddress1())));
				params.put("report_Address2", ((hospitalVO.getAddress2()==""||hospitalVO.getAddress2()==null)?" ":(hospitalVO.getAddress2())));
				//params.put("report_Address", this.getStoragePath("REPORT_ADDRESS"));
				//params.put("report_Address", ((hospitalVO.getAddress1()==""||hospitalVO.getAddress1()==null)?" ":(hospitalVO.getAddress1()+","))+((hospitalVO.getAddress2()==""||hospitalVO.getAddress2()==null)?" ":(hospitalVO.getAddress2()+","))+ ((hospitalVO.getCity()==""||hospitalVO.getCity()==null)?" ":(hospitalVO.getCity()+","))+((hospitalVO.getPinCode()==""||hospitalVO.getPinCode()==null)?" ":(hospitalVO.getPinCode()))+"");
				params.put("report_Address",address);
				//params.put("report_Contact", this.getStoragePath("REPORT_CONTACT"));
				
				params.put("report_Contact", (hospContact==""||hospContact==null)?" ":hospContact);				
				//params.put("report_Contact", "-----------------------------------------------------------------------");
				params.put("report_Footer", this.getStoragePath("REPORT_FOOTER"));
				params.put("report_Fix_Header", strFixHeader);
								
			} else {
				params.put("jndi_ds_conn", this.getPath());
				//params.put("report_Header", this.getStoragePath("REPORT_HEADER"));
				params.put("report_Header", hospitalVO.getHospitalName());
				
				params.put("report_Address1", ((hospitalVO.getAddress1()==""||hospitalVO.getAddress1()==null)?" ":(hospitalVO.getAddress1())));
				params.put("report_Address2", ((hospitalVO.getAddress2()==""||hospitalVO.getAddress2()==null)?" ":(hospitalVO.getAddress2())));
				//params.put("report_Address", this.getStoragePath("REPORT_ADDRESS"));
				//params.put("report_Address", ((hospitalVO.getAddress1()==""||hospitalVO.getAddress1()==null)?" ":(hospitalVO.getAddress1()+","))+((hospitalVO.getAddress2()==""||hospitalVO.getAddress2()==null)?" ":(hospitalVO.getAddress2()+","))+ ((hospitalVO.getCity()==""||hospitalVO.getCity()==null)?" ":(hospitalVO.getCity()+","))+((hospitalVO.getPinCode()==""||hospitalVO.getPinCode()==null)?" ":(hospitalVO.getPinCode()))+"");
				//params.put("report_Address", ((hospitalVO.getCity()==""||hospitalVO.getCity()==null)?" ":(hospitalVO.getCity()))+((hospitalVO.getDistrictName()==""||hospitalVO.getDistrictName()==null)?" ":(","+hospitalVO.getDistrictName()))+((hospitalVO.getPinCode()==""||hospitalVO.getPinCode()==null)?" ":"-"+(hospitalVO.getPinCode()))+" "+((hospitalVO.getState()==""||hospitalVO.getState()==null)?" ":""+(hospitalVO.getState()))+" INDIA ");
				params.put("report_Address",address);	
				//params.put("report_Contact", this.getStoragePath("REPORT_CONTACT"));
				params.put("report_Contact", hospContact);				
				//params.put("report_Contact","-----------------------------------------------------------------------");
				params.put("report_Footer", this.getStoragePath("REPORT_FOOTER"));
				params.put("report_Fix_Header", strFixHeader);
			}
			//End

						
			if(!strReportFormat.equals("pdf"))
				bRptFormat = false;
						
			params.put("rpt_format", bRptFormat);
			
			//System.out.println("Path :"+this.getPath());
			
			task.setParameterValues(params);

		
			
			task.run();

			
			
			response.getOutputStream().write(out.toByteArray());

		} catch (Exception e) {

			e.getMessage();
			e.printStackTrace();
			throw e;

			
			
		} finally {

			if (out != null)
				out = null;

			if (task != null) {
				task.close();
				task = null;
			}

			if (design != null)
				design = null;

			if (eng != null) {
				eng.destroy();
				eng = null;
			}

			if (conf != null)
				conf = null;
		}

	}

	/**
	 * Displays the Report by retrieving the parameters. 
	 * @param response HttpServletResponse to display the Report.
	 * @param strModuleName Current Module.
	 * @param strFileName Name of the File for the Report at the Storage Location.
	 * @param strReportFormat pdf or html (other formats wont support)
	 * @throws Exception if File Not Exists or any of the required parameter is <code>null</code> or empty.
	 */
	public void displayReportFromFile(HttpServletResponse response,
			String strModuleName, String strFileName, String strReportFormat)
			throws Exception {

		// declare a global var for byte range.
		byte[] bReportContent = new byte[1024];
		File f = null;
		FileInputStream fis = null;
		
		String strReportFullPath = null;

		String strReportLocation = HisUtil.getParameterFromHisPathXML(strModuleName.toUpperCase()
				+ "_PATH");

		if (strReportLocation == null || strReportLocation.equals("")) {
			throw new Exception("Invalid Module Name");
		}

		if (strFileName == null || strFileName.equals("")) {
			throw new Exception("Invalid File Name");
		}

		if (strReportFormat == null) {
			throw new Exception("Invalid Report Format");
		}

		strReportFullPath = strReportLocation + File.separator + strFileName ;

			
		if (strReportFormat.equals("")) {
			strReportFullPath = strReportFullPath+"."+ "pdf";
		} else {
			strReportFullPath = strReportFullPath+"." + strReportFormat;
		}

			
		String[] strTemp = strReportFullPath.replace(".", "#").split("#");
		String strExt = strTemp[strTemp.length - 1];

		try {

			if (strExt.equalsIgnoreCase("pdf")) {

				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"attachment; filename="+strFileName+".pdf");

			} else if (strExt.equalsIgnoreCase("html")
					|| strExt.equalsIgnoreCase("htm")) {
				response.setContentType("text/html;charset=utf-8");
				response.setHeader("cache-control", "no-cache");
			} else {

				throw new Exception(
						"UnSupportFile : Only PDF and HTML Supports");

			}
			
		
			f = new File(strReportFullPath);

			if (!f.isFile()) {

				throw new Exception("Invalid File Path");
			}

			fis = new FileInputStream(f);

			while (fis.read(bReportContent) != -1) {

				response.getOutputStream().write(bReportContent);
			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (fis != null) {
				fis.close();
				fis = null;
			}

			if (f != null)
				f = null;

			if (bReportContent != null)
				bReportContent = null;
		}

	}

	/**
	 * method used to return the path of the IPD_CONFIG file from resource
	 * bundle
	 * 
	 * @return - path of the REPORT_DS_PATH file in String
	 */
	private String getPath() {
		return HisUtil.getParameterFromHisPathXML("JNDI_LOOKUP");
	}

	/**
	 * method used to return the path of the IPD_CONFIG file from resource
	 * bundle
	 * 
	 * @return - path of the REPORT_DS_PATH file in String
	 */
	private String getStoragePath(String key) {

		ResourceBundle rsBundle = ResourceBundle.getBundle(pathFileName);
		storagePath = rsBundle.getString(key);
		return storagePath;
	}
}
