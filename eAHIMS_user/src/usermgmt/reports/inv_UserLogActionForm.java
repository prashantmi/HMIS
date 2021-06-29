package usermgmt.reports;

import org.apache.struts.action.ActionForm;

public class inv_UserLogActionForm extends ActionForm
{
	private String userid="";
	private String user	="";
	private String fromDate	="";
	private String toDate	="";
	private String hmode	="";
	private String user_form="";
	private String Hospital_Code="";
	private String SeatId="";

	public String getHospital_Code() {
		return Hospital_Code;
	}

	public void setHospital_Code(String hospital_Code) {
		Hospital_Code = hospital_Code;
	}

	public String getSeatId() {
		return SeatId;
	}

	public void setSeatId(String seatId) {
		SeatId = seatId;
	}

	public void setUserid( java.lang.String userid )
	{
		this.userid = userid;
	}

	public java.lang.String getUserid( )
	{
		return userid;
	}

	public void setHmode( java.lang.String hmode )
	{
		this.hmode = hmode;
	}

	public java.lang.String getHmode( )
	{
		return hmode;
	}

	public java.lang.String getUser_form( )
	{
		return user_form;
	}

	public void setUser_form( java.lang.String user_form )
	{
		this.user_form = user_form;
	}
	
	public void setUser( java.lang.String user )
	{
		this.user = user;
	}

	public java.lang.String getUser( )
	{
		return user;
	}

	public void setToDate( java.lang.String toDate )
	{
		this.toDate = toDate;
	}

	public java.lang.String getToDate( )
	{
		return toDate;
	}

	public void setFromDate( java.lang.String fromDate )
	{
		this.fromDate = fromDate;
	}
	public java.lang.String getFromDate( )
	{
		return fromDate;
	}
		
	
}//end	of	form	bean  
