package new_investigation.reportGenerator.controler.util;

/**
 * @author Prashant Mishra <https://github.com/prashantmi>
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.Status;
import hisglobal.utility.HelperMethods;
import new_investigation.reportGenerator.DataHelper.JakartaFTPWrapper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.DataProcessing.PDFProcessing;
import new_investigation.reportGenerator.DataProcessing.PGTemplateProcessing;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.MongoHelper.MongoXmlHandler;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.vo.ReportGeneratorVO;
import new_investigation.reports.controller.fb.InvTrackingReportFB;

@SuppressWarnings({ "rawtypes" })
public class InvReportGeneratorUTL {

	public static JsonObject AjaxGetPatPDFReport(InvTrackingReportFB fb, HttpServletRequest request) {

		Status objStatus = new Status();
		ResultEntryVO resultEntryVO = new ResultEntryVO();
		
		JsonObject jsonResponse = new JsonObject();
		
		try {
			
			HelperMethods.populate(resultEntryVO, fb);
			resultEntryVO.setHospitalCode(fb.getHospitalCode()==null || fb.getHospitalCode().equals("")?fb.getCrNumber().substring(0, 5):fb.getHospitalCode());
			resultEntryVO.setPatCRNo(fb.getCrNumber());
			resultEntryVO.setLaboratoryCode(fb.getLabCode());
			resultEntryVO.setIsDeptEntry("0");
			resultEntryVO.setRequisitionNo(fb.getRequisitionNo());

			boolean pgFlag = true;
			//if (PGDataHelper.createPostgresConnection() == null) {
				//pgFlag = false;
				//Log(Level.INFO, "Cannot connect to Postgres");
			//}

			boolean mongoFlag = true;
			if (PropertiesHelper.getISFtporMongo()) {
				
				if (MongoXmlHandler.getInstance() == null) {
					mongoFlag = false;
					Log(Level.INFO, "Cannot connect to mongodb");
				}
			} else {

				JakartaFTPWrapper ftp = new JakartaFTPWrapper();
			
				String ftpUserName = PropertiesHelper.getFTPConnectionUsername();
				String ftpUserPassword = PropertiesHelper.getFTPConnectionPassword();
				//String ftpserver = PropertiesHelper.getFTPConnectionURI();

				if (ftp.connectAndLogin(PropertiesHelper.getFTPConnectionIP(), ftpUserName, ftpUserPassword)) {
					Log(Level.INFO, "FTP Connection Created: " + PropertiesHelper.getFTPConnectionURI());

					if (ftp.isConnected()) {
						try {

							ftp.logout();
							ftp.disconnect();

						} catch (IOException ioe) {
							Log(Level.INFO, "error... ftp not connected");
						}
					}

				} else {
					mongoFlag = false;
					Log(Level.INFO, "Cannot connect to FTP server");
					Log(Level.INFO, "Cannot connect to FTP server " + PropertiesHelper.getFTPConnectionURI());
				}
			}

			if (pgFlag && mongoFlag) {
				jsonResponse=startReportGeneration(resultEntryVO);
			} else {
				Log(Level.INFO, "Cannot start service due to unavailability of DB connection");
				System.exit(0);
			}
			
			/*
			 * if (MongoXmlHandler.getInstance() != null) {
			 * MongoXmlHandler.closeConnection(); }
			 * 
			 * PGDataHelper.closeConnection();
			 */

			objStatus.add(Status.TRANSINPROCESS);
		} catch (HisDataAccessException e) {
			Log(Level.INFO, e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			Log(Level.INFO, e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e) {
			Log(Level.INFO, e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// ServiceLogger.Log(QScheduler.class.getName(),Level.SEVERE, e);
			e.printStackTrace();
		}
		
		
		return jsonResponse;

	}

	private static void Log(Level level, String msg) {
		ServiceLogger.Log(MongoXmlHandler.class.getName(), level, msg);
	}

	private static JsonObject startReportGeneration(ResultEntryVO resultEntryVO) {
		ReportGeneratorVO reportGeneratorVO = new ReportGeneratorVO();
		//DataHandler.populate(reportGeneratorVO, resultEntryVO);

		Map xmlReportDoc= new HashMap();
		JsonObject jsonResponse = new JsonObject();
		/*----------------------------------------*/
		Log(Level.INFO, "Start XML Processing==========================================================================||");
		PGTemplateProcessing pgtp = new PGTemplateProcessing();
		xmlReportDoc = pgtp.processingData(resultEntryVO);
		Log(Level.INFO, "End XML Processing============================================================================||");
		/*----------------------------------------*/
		
		Log(Level.INFO, "Start PDF Processing==========================================================================||");
		PDFProcessing ppp = new PDFProcessing();
		
		reportGeneratorVO.setXmlReportDoc(xmlReportDoc);
		reportGeneratorVO.setPatCRNo(resultEntryVO.getPatCRNo());
		reportGeneratorVO.setHospitalCode(resultEntryVO.getHospitalCode());
		reportGeneratorVO.setLaboratoryCode(resultEntryVO.getLaboratoryCode());
		reportGeneratorVO.setRequisitionNo(resultEntryVO.getRequisitionNo());
		reportGeneratorVO.setIsDeptEntry(resultEntryVO.getIsDeptEntry());
		
		jsonResponse = ppp.processingData(reportGeneratorVO);
		Log(Level.INFO, "End PDF Processing============================================================================||");
		/*----------------------------------------*/

		/*
		 * MongoXmlHandler.closeConnection(); PGDataHelper.closeConnection();
		 */

		return jsonResponse;
	}

}
