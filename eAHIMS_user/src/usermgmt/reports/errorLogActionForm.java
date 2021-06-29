package usermgmt.reports;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import javax.servlet.http.*;
import javax.servlet.*;


public class errorLogActionForm extends ActionForm
{
	private String hmode = "";
	private String toDate = "";
	private String fromDate = "";
	private String errorLogPath = "";
	public java.lang.String getErrorLogPath( )
	{
		return errorLogPath;
	}

	public void setErrorLogPath( java.lang.String errorLogPath )
	{
		this.errorLogPath = errorLogPath;
	}

	public java.lang.String getHmode( )
	{
		return hmode;
	}

	public void setHmode( java.lang.String hmode )
	{
		this.hmode = hmode;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
