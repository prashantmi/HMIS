package hisglobal.utility.servlets;

public interface ServletsUtilityConfig
{

	// Display File Utility Servlet
	String SERVLET_DISPLAY_FILE_URL = "/HISClinical/DisplayFileServlet";
	String FILE_PATH_WINDOWS = "windowsFilePath";
	String FILE_PATH_LINUX = "linuxFilePath";
	String FILE_NAME = "fileName";
	String IS_EXTENSION = "isExtension";
	String IS_EXTENSION_TRUE = "1";/////////////////1=if extension required
	String IS_EXTENSION_FALSE = "0";//////////////////0=if extension not required
	String EXTENSION_NAME = "extensionName";
	
	// Display Byte Array Utility Servlet
	String SERVLET_DISPLAY_BYTE_ARRAY_URL = "/HISClinical/DisplayByteArrayServlet";
	String SERVLET_DISPLAY_BYTE_ARRAY = "ServletDisplayByteArray";							// In Session
	String SERVLET_DISPLAY_BYTE_ARRAY_CONTENT_TYPE = "ServletDisplayByteArrayContentType";	// In Session

	// HTML To PDF Conversion Utility Servlet
	String SERVLET_HTML_TO_PDF_URL = "/HISClinical/hisglobal/ConvertHTMLToPDF";
	String HTML_TO_PDF_UTIL_HTML_CODE = "htmlToPDFConversionUtilityCode";
	String WATERMARK_PDF_TEXT = "waterMarkPDFText";
	// HTML Parsing & PDF Conversion Images 
	String imgCheckBoxPath="\\hisglobal\\utility\\generictemplate\\images\\checkbox.png";
	String imgRadioPath="\\hisglobal\\utility\\generictemplate\\images\\radio.png";
	String imgCheckBoxCheckedPath="\\hisglobal\\utility\\generictemplate\\images\\checkboxSelected.png";
	String imgRadioCheckedPath="\\hisglobal\\utility\\generictemplate\\images\\radioSelected.png";
	// Added By anant Patel For MedicalExamIntiation on 20-10-2014
	String SERVLET_DISPLAY_FTP_FILE_URL = "/HISClinical/DisplayFTPFileServlet";
	String APP_SERVER_MAIN_FOLDER_WINDOWS = "appServerMainFolderWindows";
	String APP_SERVER_MAIN_FOLDER_LINUX = "appServerMainFolderLinux";
	String FTP_SERVER_MAIN_FOLDER = "ftpServerMainFolder"; // FTP Server URL for Reading if required along with userName:password
	String FILE_TARGET_FOLDER = "fileTargetFolder";
	String FILE_SUB_FOLDER = "fileSubFolder";
	
	String IMAGE_FILE = "imageFile";					// Added by VASU on 25-AUG-2017
	String FILE_NO = "fileNo";							// Added by Vasu on 28-AUG-2017
	String PDF_FILE_NAME_KEY = "pdfFileNameKey";		// Added by Vasu on 18.July.2018
	String PDF_FILE_CONTENT_KEY = "pdfFileContentKey";	// Added by Vasu on 18.July.2018
}
