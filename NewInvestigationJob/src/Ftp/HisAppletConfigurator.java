package Ftp;

import java.io.Serializable;

public class HisAppletConfigurator implements Serializable {
	public String applicationserver;
	public String savingserver;
	public String loadingprotocol;
	public String ftpserver;
	public String resultprintingFTPAddress;
	//public String resultprintingusername;
	//public String resultprintinguserpassword;
	
	/*public String resultprintingusername="ftp_raol";
	public String resultprintinguserpassword="Raol12345";*/
	
	/*public String resultprintingusername="prdnims";
	public String resultprintinguserpassword="prdnims123";*/
	
	public String ftpservermode; // 0 for Windows 1 for Linux
	public String databaseusername;
	public String databasepassword;
	public  String patientretrievefileftpaddress;
	public  String patientcreatefileftpaddress;
	public  String patientfileftpusername;
	public  String patientfileftppassword;
	public String checkingforsecureprinting;
	public String sampleCollectionPrinting;
	public String sampleCollectionPrinter;
	public String sampleCollectionPrinterUserName;
	public String sampleCollectionPrinterPassword;
	public String sampleCollectionPrinterShareName;
	public String noOfCopiesOfBarcodePrint_SampleCollection="2";
	public String noOfCopiesOfBarcodePrint_duplicateSampleLabel="1";
	public String fetchfilepath;
	public  String authoriseUserName="rabindranath";
	public  String authorisePassword="ravas";
	public String hospitalnameatreport="Guru Gobind Singh Govt. Hospital";
	public String hospitaladdressatreport="Raghubir Nagar,New Delhi-110027";
	public String specialstringatreport="This is computer generated report. Signature not required";
	public  String pagewidthprinting="595.0";
	public  String pagewidthheight="842.0";
	public  String scanningftpurl="ftp://172.0.5.152/scannedDocs";
	public  String scanningftpuser="ggs123";
	public  String scanningftppassword="ggs123";
	public String getSampleCollectionPrinting() {
		return sampleCollectionPrinting;
	}
	public void setSampleCollectionPrinting(String sampleCollectionPrinting) {
		this.sampleCollectionPrinting = sampleCollectionPrinting;
	}
	public String getSampleCollectionPrinter() {
		return sampleCollectionPrinter;
	}
	public void setSampleCollectionPrinter(String sampleCollectionPrinter) {
		this.sampleCollectionPrinter = sampleCollectionPrinter;
	}
	public String getCheckingforsecureprinting() {
		return checkingforsecureprinting;
	}
	public void setCheckingforsecureprinting(String checkingforsecureprinting) {
		this.checkingforsecureprinting = checkingforsecureprinting;
	}
	public String getFtpservermode() {
		return ftpservermode;
	}
	public void setFtpservermode(String ftpservermode) {
		this.ftpservermode = ftpservermode;
	}
	public String getApplicationserver() {
		return applicationserver;
	}
	public void setApplicationserver(String applicationserver) {
		this.applicationserver = applicationserver;
	}
	public String getSavingserver() {
		return savingserver;
	}
	public void setSavingserver(String savingserver) {
		this.savingserver = savingserver;
	}
	public String getLoadingprotocol() {
		return loadingprotocol;
	}
	public void setLoadingprotocol(String loadingprotocol) {
		this.loadingprotocol = loadingprotocol;
	}
	public String getFtpserver() {
		return ftpserver;
	}
	public void setFtpserver(String ftpserver) {
		this.ftpserver = ftpserver;
	}
	public String getResultprintingFTPAddress() {
		return resultprintingFTPAddress;
	}
	public void setResultprintingFTPAddress(String resultprintingFTPAddress) {
		this.resultprintingFTPAddress = resultprintingFTPAddress;
	}
	
	/*public String getResultprintingusername() {
		return resultprintingusername;
	}
	public void setResultprintingusername(String resultprintingusername) {
		this.resultprintingusername = resultprintingusername;
	}
	public String getResultprintinguserpassword() {
		return resultprintinguserpassword;
	}
	public void setResultprintinguserpassword(String resultprintinguserpassword) {
		this.resultprintinguserpassword = resultprintinguserpassword;
	}*/
	
	public String getDatabaseusername() {
		return databaseusername;
	}
	public void setDatabaseusername(String databaseusername) {
		this.databaseusername = databaseusername;
	}
	public String getDatabasepassword() {
		return databasepassword;
	}
	public void setDatabasepassword(String databasepassword) {
		this.databasepassword = databasepassword;
	}
	public  String getPatientretrievefileftpaddress() {
		return patientretrievefileftpaddress;
	}
	public  void setPatientretrievefileftpaddress(
			String patientretrievefileftpaddress) {
		this.patientretrievefileftpaddress = patientretrievefileftpaddress;
	}
	public  String getPatientcreatefileftpaddress() {
		return patientcreatefileftpaddress;
	}
	public  void setPatientcreatefileftpaddress(
			String patientcreatefileftpaddress) {
		this.patientcreatefileftpaddress = patientcreatefileftpaddress;
	}
	public  String getPatientfileftpusername() {
		return patientfileftpusername;
	}
	public  void setPatientfileftpusername(String patientfileftpusername) {
		this.patientfileftpusername = patientfileftpusername;
	}
	public  String getPatientfileftppassword() {
		return patientfileftppassword;
	}
	public  void setPatientfileftppassword(String patientfileftppassword) {
		this.patientfileftppassword = patientfileftppassword;
	}
	public String getSampleCollectionPrinterUserName() {
		return sampleCollectionPrinterUserName;
	}
	public void setSampleCollectionPrinterUserName(
			String sampleCollectionPrinterUserName) {
		this.sampleCollectionPrinterUserName = sampleCollectionPrinterUserName;
	}
	public String getSampleCollectionPrinterPassword() {
		return sampleCollectionPrinterPassword;
	}
	public void setSampleCollectionPrinterPassword(
			String sampleCollectionPrinterPassword) {
		this.sampleCollectionPrinterPassword = sampleCollectionPrinterPassword;
	}
	public String getSampleCollectionPrinterShareName() {
		return sampleCollectionPrinterShareName;
	}
	public void setSampleCollectionPrinterShareName(
			String sampleCollectionPrinterShareName) {
		this.sampleCollectionPrinterShareName = sampleCollectionPrinterShareName;
	}
	public String getNoOfCopiesOfBarcodePrint_SampleCollection() {
		return noOfCopiesOfBarcodePrint_SampleCollection;
	}
	public void setNoOfCopiesOfBarcodePrint_SampleCollection(
			String noOfCopiesOfBarcodePrintSampleCollection) {
		noOfCopiesOfBarcodePrint_SampleCollection = noOfCopiesOfBarcodePrintSampleCollection;
	}
	public String getNoOfCopiesOfBarcodePrint_duplicateSampleLabel() {
		return noOfCopiesOfBarcodePrint_duplicateSampleLabel;
	}
	public void setNoOfCopiesOfBarcodePrint_duplicateSampleLabel(
			String noOfCopiesOfBarcodePrintDuplicateSampleLabel) {
		noOfCopiesOfBarcodePrint_duplicateSampleLabel = noOfCopiesOfBarcodePrintDuplicateSampleLabel;
	}
	public String getFetchfilepath() {
		return fetchfilepath;
	}
	public void setFetchfilepath(String fetchfilepath) {
		this.fetchfilepath = fetchfilepath;
	}
	public  String getAuthoriseUserName() {
		return authoriseUserName;
	}
	public  void setAuthoriseUserName(String authoriseUserName) {
		this.authoriseUserName = authoriseUserName;
	}
	public  String getAuthorisePassword() {
		return authorisePassword;
	}
	public  void setAuthorisePassword(String authorisePassword) {
		this.authorisePassword = authorisePassword;
	}
	public String getHospitalnameatreport() {
		return hospitalnameatreport;
	}
	public void setHospitalnameatreport(String hospitalnameatreport) {
		this.hospitalnameatreport = hospitalnameatreport;
	}
	public String getHospitaladdressatreport() {
		return hospitaladdressatreport;
	}
	public void setHospitaladdressatreport(String hospitaladdressatreport) {
		this.hospitaladdressatreport = hospitaladdressatreport;
	}
	public String getSpecialstringatreport() {
		return specialstringatreport;
	}
	public void setSpecialstringatreport(String specialstringatreport) {
		this.specialstringatreport = specialstringatreport;
	}
	public String getPagewidthprinting() {
		return pagewidthprinting;
	}
	public void setPagewidthprinting(String pagewidthprinting) {
		this.pagewidthprinting = pagewidthprinting;
	}
	public String getPagewidthheight() {
		return pagewidthheight;
	}
	public void setPagewidthheight(String pagewidthheight) {
		this.pagewidthheight = pagewidthheight;
	}
	public String getScanningftpurl() {
		return scanningftpurl;
	}
	public void setScanningftpurl(String scanningftpurl) {
		this.scanningftpurl = scanningftpurl;
	}
	public String getScanningftpuser() {
		return scanningftpuser;
	}
	public void setScanningftpuser(String scanningftpuser) {
		this.scanningftpuser = scanningftpuser;
	}
	public String getScanningftppassword() {
		return scanningftppassword;
	}
	public void setScanningftppassword(String scanningftppassword) {
		this.scanningftppassword = scanningftppassword;
	}
	
	
	
}

