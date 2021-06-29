package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddImageDescriptionFB extends ActionForm
{
	private String imageDesc;
	private String transactionMode;
	private String imageDescID;

	
	
	public String getImageDesc() {
		return imageDesc;
	}
	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public String getImageDescID() {
		return imageDescID;
	}
	public void setImageDescID(String imageDescID) {
		this.imageDescID = imageDescID;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		
	}
}
