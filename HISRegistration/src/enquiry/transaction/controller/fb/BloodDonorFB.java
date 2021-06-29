
package enquiry.transaction.controller.fb;



import org.apache.struts.action.ActionForm;


public class BloodDonorFB extends ActionForm {
	
	
	private String bloodGroup;
	private String emergencyCall;
	private String hmode;
	
	
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getEmergencyCall() {
		return emergencyCall;
	}
	public void setEmergencyCall(String emergencyCall) {
		this.emergencyCall = emergencyCall;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
		
	
		
}
