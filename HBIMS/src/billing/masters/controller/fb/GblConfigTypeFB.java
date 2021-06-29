package billing.masters.controller.fb;
import org.apache.struts.action.ActionForm;



public class GblConfigTypeFB extends ActionForm{
	
	private String strHtmlString;
	private String hmode;
	private String hospCode;

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getStrHtmlString() {
		return strHtmlString;
	}

	public void setStrHtmlString(String strHtmlString) {
		this.strHtmlString = strHtmlString;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}
	

}



