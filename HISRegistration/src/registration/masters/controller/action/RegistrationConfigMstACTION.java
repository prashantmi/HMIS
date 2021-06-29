/**
 * 
 */
package registration.masters.controller.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;








import hisglobal.masterutil.GenericController;
import registration.masters.controller.actionsupport.RegistrationConfigSUP;
import registration.masters.controller.util.RegistrationConfigMstUTIL;
import vo.registration.RegistrationConfigMstVO;


/**
 * @author s.singaravelan
 *
 */
public class RegistrationConfigMstACTION extends RegistrationConfigSUP 
{
	private String message;
	private RegistrationConfigMstVO registrationConfigModel;
	public HttpServletRequest request = null;
	//List<String> list=new ArrayList<String>();
	
	public String execute()
	 {
		message="";
		return this.LIST();
	 }
	public String LIST()
	{
	//	RegistrationConfigMstUTIL.getRegistrationConfigDtl(this, "1", super.getObjRequest());
		// RegistrationConfigMstUTIL obj_configMst= new RegistrationConfigMstUTIL();
		this.setStrduplicacyChkOpd("0");
		this.setStrduplicacyChkSpl("0");
		this.setStrduplicacyChkEmg("0");
		this.setStremgRenewalOpd("0");
		this.setStremgRenewalSpl("0");
		this.setStremgRenewalEmg("0");
		this.setStrmodechoiceOpd("0");
		this.setStrmodechoiceSpl("0");
		this.setStrmodechoiceEmg("0");
		this.setStrappointmentbsOpd("0");
		this.setStrappointmentbsSpl("0");
		this.setStrappointmentbsEmg("0");
		this.setStradharintegrationOpd("0");
		this.setStradharintegrationSpl("0");
		this.setStradharintegrationEmg("0");
		this.setStrmobileServiceOpd("0");
		this.setStrmobileServiceSpl("0");
		this.setStrmobileServiceEmg("0");
		 RegistrationConfigMstUTIL.fetchRecord(this, super.getObjRequest());

		return "success";
	 }
	
	
	
	 
	 public String save()
	 {
		 if(RegistrationConfigMstUTIL.saveRegistrationConfigDtl(this, "1", super.getObjRequest()))
		 {
			 message="Data Saved Successfully";
			 return LIST();
		 }
		 else
		 {
			 return "success";
		 }
	 }
	

	

	




}
