package registration.masters.controller.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.connector.Request;

import registration.masters.controller.actionsupport.RenewalConfigSUP;
import registration.masters.controller.util.RenewalConfigMstUTIL;
import vo.registration.RenewalConfigVO;


/**
 * @author   Deepika Gaba
 */
public class RenewalConfigMstACTION extends RenewalConfigSUP{  
	/**
	 * 
	 */
	 private String message;


	private static final long serialVersionUID = 1L;

	
	public String execute()
	 {
		message="";
		return this.LIST();
	 }
	public String LIST()
	 {
		super.clear();
		RenewalConfigMstUTIL.getRenewalConfigDtl(this, "1", super.getObjRequest());
		return "success";
	 }
	
	
	//in report page, cancel is clicked
	 public String cancel()
	 {
		// super.LIST();
		try{
		}catch(Exception e){
			e.printStackTrace();
		}
			 
		 return execute();
	 }
		
	
	 public String save()
	 {
		 if(RenewalConfigMstUTIL.saveRenewalConfigDtl(this, "1", super.getObjRequest()))
		 {
			 message="Data Saved Successfully";
			 return LIST();
		 }
		 else
		 {
			 return "success";
		 }
	 }
	 /*public String update()
	 {
		 if(RenewalConfigMstUTIL.saveRenewalConfigDtl(this, "2"))
		 {
			 message="Data Updated Successfully";
			 return execute();
		 }
		 else
		 {
			 return execute();
		 }
	 }*/
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/*public void validateUpdate() 
	{
		//if(chkCommonCode.trim().equals("") && "".equals(chkCommonCode.trim()))
		if(chkCommonCode != null && !("".equals(chkCommonCode.trim())))
		{
			if(chkCommonCode.equals("on"))
			{
				//Renewal mode should not be null
				//If not null then 2 condition
				//IF 1 then
				//No of days should not be null or 0. Else error
				//If 2 then Is Multiple Month should not be -1
				//No - 1 day and month
				//No-2 
				//No-3
				if(strRenewalMode == null || strRenewalMode.equals(""))
				{
					addFieldError("strRenewalMode", "Please Enter Renewal Mode");
					return ;
				}
				if(strRenewalMode.equals("1"))
				{
					if(strNoOfDays == null || "".equals(strNoOfDays.trim()))
					{
						addFieldError("strNoOfDays", "Please Enter No. Of Days For Renewal");
					}
					else if(!strNoOfDays.matches("^[1-9][0-9]{0,2}$"))
					{
						addFieldError("strNoOfDays", "Please Enter No. Of Days For Renewal. It Excepts Numbers Only");
					}
				}
				else if(strRenewalMode.equals("2"))
				{
					if(strIsMultipleMonth.equals("0"))
					{
						monthRenewalModeValidation("strDay1","strMonth1",strDay1,strMonth1);
					}
					else if(strIsMultipleMonth.equals("1"))
					{
						monthRenewalModeValidation("strDay1","strMonth1",strDay1,strMonth1);
						monthRenewalModeValidation("strDay2","strMonth2",strDay2,strMonth2);
					}
					else if(strIsMultipleMonth.equals("2"))
					{
						monthRenewalModeValidation("strDay1","strMonth1",strDay1,strMonth1);
						monthRenewalModeValidation("strDay2","strMonth2",strDay2,strMonth2);
						monthRenewalModeValidation("strDay3","strMonth3",strDay3,strMonth3);
					}
				}
			}
		}
		else
		{
			if(strRenewalTypeGen.equals("2") || strRenewalTypeGen.equals("3"))
			{
				if(strRenewalModeGen == null || strRenewalModeGen.equals(""))
				{
					addFieldError("strRenewalModeGen", "Please Enter Renewal Mode");
					return ;
				}
				else if(strRenewalModeGen.equals("1"))
				{
					if("".equals(strNoOfDaysGen.trim()))
					{
						addFieldError("strNoOfDaysGen", "Please Enter No. Of Days For General Renewal");
					}
					else if(!strNoOfDaysGen.matches("^[1-9][0-9]{0,2}$"))
					{
						addFieldError("strNoOfDaysGen", "Please Enter No. Of Days For General Renewal. It Excepts Numbers Only");
					}
				}
				else if(strRenewalModeGen.equals("2"))
				{
					if(strIsMultipleMonthGen.equals("0"))
					{
						monthRenewalModeValidation("strDay1Gen","strMonth1Gen",strDay1Gen,strMonth1Gen);
					}
					else if(strIsMultipleMonthGen.equals("1"))
					{
						monthRenewalModeValidation("strDay1Gen","strMonth1Gen",strDay1Gen,strMonth1Gen);
						monthRenewalModeValidation("strDay2Gen","strMonth2Gen",strDay2Gen,strMonth2Gen);
					}
					else if(strIsMultipleMonthGen.equals("2"))
					{
						monthRenewalModeValidation("strDay1Gen","strMonth1Gen",strDay1Gen,strMonth1Gen);
						monthRenewalModeValidation("strDay2Gen","strMonth2Gen",strDay2Gen,strMonth2Gen);
						monthRenewalModeValidation("strDay3Gen","strMonth3Gen",strDay3Gen,strMonth3Gen);
					}
				}
			}	
			else if(strRenewalTypeSpl.equals("2") || strRenewalTypeSpl.equals("3"))
			{
				if(strRenewalModeSpl == null || strRenewalModeSpl.equals(""))
				{
					addFieldError("strRenewalModeSpl", "Please Enter Renewal Mode");
					return ;
				}
				else if(strRenewalModeSpl.equals("1"))
				{
					if("".equals(strNoOfDaysSpl.trim()))
					{
						addFieldError("strNoOfDaysSpl", "Please Enter No. Of Days For Special Renewal");
					}
					else if(!strNoOfDaysSpl.matches("^[1-9][0-9]{0,2}$"))
					{
						addFieldError("strNoOfDaysSpl", "Please Enter No. Of Days For Special Renewal. It Excepts Numbers Only");
					}
				}
				else if(strRenewalModeSpl.equals("2"))
				{
					if(strIsMultipleMonthSpl.equals("0"))
					{
						monthRenewalModeValidation("strDay1Spl","strMonth1Spl",strDay1Spl,strMonth1Spl);
					}
					else if(strIsMultipleMonthSpl.equals("1"))
					{
						monthRenewalModeValidation("strDay1Spl","strMonth1Spl",strDay1Spl,strMonth1Spl);
						monthRenewalModeValidation("strDay2Spl","strMonth2Spl",strDay2Spl,strMonth2Spl);
					}
					else if(strIsMultipleMonthSpl.equals("2"))
					{
						monthRenewalModeValidation("strDay1Spl","strMonth1Spl",strDay1Spl,strMonth1Spl);
						monthRenewalModeValidation("strDay2Spl","strMonth2Spl",strDay2Spl,strMonth2Spl);
						monthRenewalModeValidation("strDay3Spl","strMonth3Spl",strDay3Spl,strMonth3Spl);
					}
				}
			}
			else if(strRenewalTypeEmg.equals("2") || strRenewalTypeEmg.equals("3"))
			{
				if(strRenewalTypeEmg == null || strRenewalTypeEmg.equals(""))
				{
					addFieldError("strRenewalTypeEmg", "Please Enter Renewal Mode");
					return ;
				}
				else if(strRenewalTypeEmg.equals("1"))
				{
					if("".equals(strNoOfDaysEmg.trim()))
					{
						addFieldError("strNoOfDaysEmg", "Please Enter No. Of Days For Causality Renewal");
					}
					else if(!strNoOfDaysEmg.matches("^[1-9][0-9]{0,2}$"))
					{
						addFieldError("strNoOfDaysEmg", "Please Enter No. Of Days For Causality Renewal. It Excepts Numbers Only");
					}
				}
				else if(strRenewalTypeEmg.equals("2"))
				{
					if(strIsMultipleMonthSpl.equals("0"))
					{
						monthRenewalModeValidation("strDay1Emg","strMonth1Emg",strDay1Emg,strMonth1Emg);
					}
					else if(strIsMultipleMonthSpl.equals("1"))
					{
						monthRenewalModeValidation("strDay1Emg","strMonth1Emg",strDay1Emg,strMonth1Emg);
						monthRenewalModeValidation("strDay2Emg","strMonth2Emg",strDay2Emg,strMonth2Emg);
					}
					else if(strIsMultipleMonthSpl.equals("2"))
					{
						monthRenewalModeValidation("strDay1Emg","strMonth1Emg",strDay1Emg,strMonth1Emg);
						monthRenewalModeValidation("strDay2Emg","strMonth2Emg",strDay2Emg,strMonth2Emg);
						monthRenewalModeValidation("strDay3Emg","strMonth3Emg",strDay3Emg,strMonth3Emg);
					}
				}
			}
		}
	}*/
	
	private void monthRenewalModeValidation(String strDayFBName, String strMonthFBName, String strDay, String strMont)
	{
		
		if("".equals(strDay.trim()))
		{
			addFieldError(strDayFBName, "Please Enter Days");
			//return;
		}
		else if("".equals(strMont.trim()))
		{
			addFieldError(strMonthFBName, "Please Enter Month");
			//return;
		}
		else if(strMont.equals("Jan") || strMont.equals("Mar") || strMont.equals("May") || strMont.equals("Jul") || strMont.equals("Aug") || strMont.equals("Oct") || strMont.equals("Dec"))
		{
			int i = Integer.parseInt(strDay);
			
			if(i>31)
			{
				addFieldError("strDayFBName", "Days For "+strMont+" Should Be Less Than Equal To 31");
			}
		}
		else if(strMonth1.equals("Apr") || strMonth1.equals("Jun") || strMonth1.equals("Sep") || strMonth1.equals("Nov"))
		{
			int i = Integer.parseInt(strDay);
			if(i>30)
			{
				addFieldError("strDayFBName", "Days For "+strMont+" Should Be Less Than Equal To 30");
			}
		}
		else if(strMonth1.equals("Feb"))
		{
			int i = Integer.parseInt(strDay);
			if(i>28)
			{
				addFieldError("strDayFBName", "Days For "+strMont+" Should Be Less Than Equal To 28");
			}
		}
		else
		{
			addFieldError("strDayFBName", "Not A Valid Month");
		}
	}
	 
}
