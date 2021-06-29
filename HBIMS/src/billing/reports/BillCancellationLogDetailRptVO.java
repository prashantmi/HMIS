/**
 * 
 */
package billing.reports;
import javax.sql.rowset.WebRowSet;

/**
 * @author Administrator
 *
 */
public class BillCancellationLogDetailRptVO {
	
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	private String strReportFormat = "0";
	private String strHospitalCode = "";
	private WebRowSet strHospitalWs = null; 
	private String strHospNameValues=""; 
	private String strHospitalName = "";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strSeatId="";
	private WebRowSet strClerkWs=null;
	/**
	 * @return the strMsgString
	 */
	public String getStrMsgString() {
		return strMsgString;
	}
	/**
	 * @param strMsgString the strMsgString to set
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	/**
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}
	/**
	 * @param strMsgType the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}
	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	/**
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	/**
	 * @return the strEffectiveTo
	 */
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	/**
	 * @param strEffectiveTo the strEffectiveTo to set
	 */
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public WebRowSet getStrHospitalWs() {
		return strHospitalWs;
	}
	public void setStrHospitalWs(WebRowSet strHospitalWs) {
		this.strHospitalWs = strHospitalWs;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}
	public String getStrHospitalName() {
		return strHospitalName;
	}
	public void setStrHospitalName(String strHospitalName) {
		this.strHospitalName = strHospitalName;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public WebRowSet getStrClerkWs() {
		return strClerkWs;
	}
	public void setStrClerkWs(WebRowSet strClerkWs) {
		this.strClerkWs = strClerkWs;
	}
	
	
	
}
