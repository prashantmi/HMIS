package usermgmt.masters;

import usermgmt.*;

public class ConfigSetup_Bean extends FuncLib
{	
	private String hospitalcode="";
	private String url="";
	private String senderid="";
	private String username="";
	private String password="";
	private String message="";
	private String mobileno="";	
	private String seatId="";
	private String hmode="";
	boolean retValue = true;
	private String status		="";
	

	public boolean isRetValue() {
		return retValue;
	}

	public void setRetValue(boolean retValue) {
		this.retValue = retValue;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	
	
	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	
public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

public boolean insertRecord()throws Exception
{
	String query  		= 	new String();
	
	String url=this.url;
	System.out.println("url"+url);
	String senderid=this.getSenderid();
	String username=this.getUsername();
	String password=this.getPassword();
	String message=this.getMessage();
	String mobileno=this.getMobileno();
	String seatId=this.getSeatId();
	String hmode=this.getHmode();
	retValue = true;
		
		query = " INSERT INTO GBLT_SMS_CONFIG (GSTR_URL,GSTR_SENDERID,GSTR_USER,GSTR_PWD,GNUM_ISVALID)"+
				" VALUES('"+url+"','"+senderid+"','"+username+"','"+password+"',1)";
			
		System.out.println("QUERY IS "+query);
		try
			{
	        	if (!updateRecord(query))
	        	{
	        		status = "Error while inserting the record !";
	        		retValue = false;
	        	}
	        	else
	        	{
	        		status = "Record  Successfully Inserted !";
	        	}
			}
			catch(Exception e)
			{
				status = String.valueOf(e);
				System.out.println("Error While Inserting Record:"+e);
				retValue = false;
			}	
  
		return retValue;
	}
}
