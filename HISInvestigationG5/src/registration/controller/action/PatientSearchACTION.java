package registration.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import registration.controller.actionsupport.PatientSearchSUP;
import registration.controller.util.PatientSearchUTIL;
import registration.controller.util.PatDetailUTIL;

import javax.servlet.ServletContext;




public class PatientSearchACTION extends PatientSearchSUP {
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	
	
	public String execute(){
		return NEW();
	}
	
	
	public String NEW(){
		
		WebUTIL.refreshTransState(super.getObjRequest(),"PatientSearchACTION");
	 	PatientSearchUTIL.setSysdate(super.getObjRequest());
	 	PatientSearchUTIL.getEssentials(this, objRequest);
	 	Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.setStatus(super.getObjRequest(), status);
	 	return "NEW";
	 	}
	
	public void getState()
	 {
		   
		 System.out.println("PatientSearchACTION :: getState()");
		 message = "Inside getState() method";
			
		 PatientSearchUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("PatientSearchACTION :: getDistrict()");
		 message = "Inside getDistrict() method";
			
		 PatientSearchUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	
	
	
	public void save(){
		
		String patSearchId= (String)objRequest.getParameter("patSearchById");
		String patUniqueIdType= (String)objRequest.getParameter("patUniqueIdType");
		String patDocType= (String)objRequest.getParameter("patDocType");
		String hospitalCode= (String)objRequest.getParameter("hospitalCode");

		
		
		System.out.println("patUniqueIdType......"+patUniqueIdType);
		System.out.println("patUniqueIdType......"+patDocType);
		if(patUniqueIdType!=null && patUniqueIdType!="")
		{
			//Setting Search Type 1 for Unique ID base Search
			this.setSearchType("1");
			this.setUniqueIdType(patUniqueIdType);
			if(patUniqueIdType.equals("3"))
			{
				this.setDocType(patDocType);
			}
		}
		else
		{
			//Setting Search Type 2 for Demographic Search
			this.setSearchType("2");
			this.setUniqueIdType("2");

		}
		
		this.setPatIdNo(patSearchId);
		this.setHospitalCode(hospitalCode);
		PatDetailUTIL.searchPatientDetailForSearcTile(this, objRequest,objResponse);
		//return "NEW";
	 	}
	
	public void search(){
		
		this.reset();		
		this.setPatFirstName((String)objRequest.getParameter("patFirstName"));
		this.setPatMiddleName((String)objRequest.getParameter("patMiddleName"));
		this.setPatLastName((String)objRequest.getParameter("patLastName"));
		this.setPatGender((String)objRequest.getParameter("patGender"));
		this.setPatGuardianName((String)objRequest.getParameter("patFatherName"));
		this.setPatHusbandName((String)objRequest.getParameter("patSpouseName"));
		this.setPatMotherName((String)objRequest.getParameter("patMotherName"));
		
		this.setPatAddHNo((String)objRequest.getParameter("patAddHNo"));
		this.setPatAddStreet((String)objRequest.getParameter("patAddStreet"));
		this.setPatAddCityLoc((String)objRequest.getParameter("patAddCityLoc"));
		this.setPatAddCity((String)objRequest.getParameter("patAddCity"));
		this.setPatAddPIN((String)objRequest.getParameter("patAddPIN"));
		this.setPatAge((String)objRequest.getParameter("patAge"));
		this.setPatAddCountryCode((String)objRequest.getParameter("patAddCountryCode"));
		this.setPatAddStateCode((String)objRequest.getParameter("patAddStateCode"));
		this.setPatAddDistrictCode((String)objRequest.getParameter("patAddDistrictCode"));
		this.setPatAddStateName((String)objRequest.getParameter("patAddState"));
		this.setPatAddDistrict((String)objRequest.getParameter("patAddDistrict"));
		/*  ## 		Modification Log							
			##		Modify Date				:18thDec'14 
			##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
			##		Modify By				:Sheeldarshi */
		this.setFromDate((String)objRequest.getParameter("fromDate"));
		this.setToDate((String)objRequest.getParameter("toDate"));
		this.setIsUnknown((String)objRequest.getParameter("isUnknown"));
		//End:Sheeldarshi
		PatDetailUTIL.searchPatientDetailByDemographicDetailsForSearcTile(this, objRequest,objResponse);

		
	}
	
	public String cancel() throws Exception
	 {
		return "NEW";
	 }
	
	
	public void setServletContext(ServletContext context) {
		this.objContext=context;
		
	}
	
}
