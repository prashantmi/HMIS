
package enquiry.transaction.controller.fb;



import org.apache.struts.action.ActionForm;


public class BloodStockEnquiryFB extends ActionForm {
	
	
	private String hmode;
	private String choice;

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
		
}
