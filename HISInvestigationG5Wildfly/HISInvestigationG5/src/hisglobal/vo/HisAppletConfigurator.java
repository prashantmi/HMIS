package hisglobal.vo;

import java.io.Serializable;

public class HisAppletConfigurator implements Serializable {
	public String applicationserver;
	public String savingserver;
	public String loadingprotocol;
	public String ftpserver;
	public String resultprintingFTPAddress;
	public String resultprintingusername;
	public String resultprintinguserpassword;
	public String ftpservermode="1"; // 0 for Windows 1 for Linux
	public String databaseusername;
	public String databasepassword;
	public  String patientretrievefileftpaddress;
	public  String patientcreatefileftpaddress;
	public  String patientfileftpusername;
	public  String patientfileftppassword;
	public String checkingforsecureprinting;
	public String sampleCollectionPrinting;
	public String sampleCollectionPrinter;
	
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
	public String getResultprintingusername() {
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
	}
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
	
	
}
