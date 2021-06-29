package hisglobal.utility.EmailSender.config;

/**
 * 
 * @author s.singaravelan
 *
 */

public class EmailConfig {
	
	public String SMTP_HOST="smtp.cdac.in";
	public String SMTP_PORT="587";
	public String SMTP_FROM_MAILI_ID="from-cdacnoida@cdac.in";
	public String SMTP_USERNAME="garimagotra@cdac.in";
	public String SMTP_PASSWORD="cdac@123#";	
	
	public void setEmailConfigDetais(String hostId,String port,String frommailId,String userName,String password)
	{	
		this.SMTP_HOST=hostId;
		this.SMTP_PORT=port;
		this.SMTP_FROM_MAILI_ID=hostId;
		this.SMTP_USERNAME=hostId;
		this.SMTP_PASSWORD=hostId;
	
	}
}
