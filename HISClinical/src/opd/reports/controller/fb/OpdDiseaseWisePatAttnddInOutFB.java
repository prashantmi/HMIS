package opd.reports.controller.fb;
import hisglobal.presentation.ReportFB;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class OpdDiseaseWisePatAttnddInOutFB  extends ReportFB{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String allHospitalCode;
	private String[] multipleHospitalCode;
	
	
	public String getAllHospitalCode() {
		return allHospitalCode;
	}
	public void setAllHospitalCode(String allHospitalCode) {
		this.allHospitalCode = allHospitalCode;
	}
	public String[] getMultipleHospitalCode() {
		return multipleHospitalCode;
	}
	public void setMultipleHospitalCode(String[] multipleHospitalCode) {
		this.multipleHospitalCode = multipleHospitalCode;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping, request);
	}
}
