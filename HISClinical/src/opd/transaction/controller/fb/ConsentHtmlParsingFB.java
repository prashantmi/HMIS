package opd.transaction.controller.fb;

/**
 * @author  CDAC
 */

import org.apache.struts.action.ActionForm;

public class ConsentHtmlParsingFB extends ActionForm 
{
	private String hmode;
    private String consentHtmlToPrint;
    private String templateId;

    public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getConsentHtmlToPrint() {
		return consentHtmlToPrint;
	}
	public void setConsentHtmlToPrint(String consentHtmlToPrint) {
		this.consentHtmlToPrint = consentHtmlToPrint;
	}
}
