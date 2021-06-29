/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name						: NIMS
 ## Name of Developer		 			: Siddharth Srivastava
 ## Module Name							: Investigation
 ## Process/Database Object Name		: 
 ## Purpose								: Test VO
 ## Date of Creation					: 14/04/2015
 ## Modification Log					:				
 ##		Modify Date						:  
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
*/

package new_investigation.vo.template;
import new_investigation.InvestigationConfig;


import new_investigation.Helper.DateFormatter;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;



import org.w3c.dom.Document;

import hisglobal.utility.Entry;
import hisglobal.vo.SampleGroupDetail;
import hisglobal.vo.TemplateRepresentationClass;
import hisglobal.vo.ValueObject;

public class LaboratoryTestVO extends ValueObject {
	/**
	 * This class is cached in the memory through JCS
	 */
	private static final long serialVersionUID = 1L;
	private String   testType;
	private String   testName;
	private String   testCode;
	private String   laboratoryName;
	private String	 laboratoryCode;
	private String   isMandatoryAssociated="0";
	
	private String   mandatoryInfoPresent="0";
	private String   isAppointmentBased="0"; 
	private String   isMultiSessionTest="0";
	private String   defaultNoOfSession;
	private String   defaultDuration;
	private String   isProcedure;
	private String   testDay;
	
	private String   isRequisitionFormrequired;
	private String   labRequisitionFormRequired;
	private List<Entry> specimenList;// contains both specimen and specimen group
	private String   labTestRowString;
	private String   multisessionRegrouped;
	private Map<Integer,SampleGroupDetail>  specimenGroupDetailList;//contains slide Types
	private String   testRemarks="ok";
	private String sampleCollectionOfflineRemarks;
	private String   proposedTestDate;
	
	private String   defaultRequisitionFormString;
	private Document defaultRequisitionFormDocument;
	private String 	 defaultSampleCode;
	
	/*offline process bean add*/
	private String isGrossingRequired;
	
	private String isPartialTansmission;
	private String isconfidential;
	private String isSampleCollectionFormRequired="";
	private String printedWithTemplateID;
	private String printingType;
	private String isFullColspanRequired;
	
	private String isPatientAccepted;
	private String isFilmRequired;
	private String tariffCode;
	private String defaultMachineCode;
	private List<Entry> machineList;
	private String machineStringforCombo; 
	
	public String getIsGrossingRequired() {
		return isGrossingRequired;
	}
	public void setIsGrossingRequired(String isGrossingRequired) {
		this.isGrossingRequired = isGrossingRequired;
	}
	public String getDefaultRequisitionFormString() {
		return defaultRequisitionFormString;
	}
	public void setDefaultRequisitionFormString(String defaultRequisitionFormString) {
		this.defaultRequisitionFormString = defaultRequisitionFormString;
	}
	public Document getDefaultRequisitionFormDocument() {
		return defaultRequisitionFormDocument;
	}
	public void setDefaultRequisitionFormDocument(
			Document defaultRequisitionFormDocument) {
		this.defaultRequisitionFormDocument = defaultRequisitionFormDocument;
	}

	
	


	public String getDefaultSampleCode() {
		return defaultSampleCode;
	}
	public void setDefaultSampleCode(String defaultSampleCode) {
		this.defaultSampleCode = defaultSampleCode;
	}





	private List<TemplateRepresentationClass> mandatoryInfoDtl;
	
	
	public String getIsMandatoryAssociated() {
		return isMandatoryAssociated;
	}
	public void setMandatoryAssociated(String mandatoryAssociated) {
		this.isMandatoryAssociated = mandatoryAssociated;
	}
	
	
	public List<TemplateRepresentationClass> getMandatoryInfoDtl() {
		return mandatoryInfoDtl;
	}
	public void setMandatoryInfoDtl(
			List<TemplateRepresentationClass> mandatoryInfoDtl) {
		this.mandatoryInfoDtl = mandatoryInfoDtl;
	}
	public String getMultisessionRegrouped() {
		return multisessionRegrouped;
	}
	public void setMultisessionRegrouped(String multisessionRegrouped) {
		this.multisessionRegrouped = multisessionRegrouped;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLaboratoryName() {
		return laboratoryName;
	}
	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	public String getLaboratoryCode() {
		return laboratoryCode;
	}
	public void setLaboratoryCode(String laboratoryCode) {
		this.laboratoryCode = laboratoryCode;
	}
	public String getMandatoryInfoPresent() {
		return mandatoryInfoPresent;
	}
	public void setMandatoryInfoPresent(String mandatoryInfoPresent) {
		this.mandatoryInfoPresent = mandatoryInfoPresent;
	}
	public String getIsAppointmentBased() {
		return isAppointmentBased;
	}
	public void setIsAppointmentBased(String isAppointmentBased) {
		this.isAppointmentBased = isAppointmentBased;
	}
	public String getIsMultiSessionTest() {
		return isMultiSessionTest;
	}
	public void setIsMultiSessionTest(String isMultiSessionTest) {
		this.isMultiSessionTest = isMultiSessionTest;
	}
	public String getDefaultNoOfSession() {
		return defaultNoOfSession;
	}
	public void setDefaultNoOfSession(String defaultNoOfSession) {
		this.defaultNoOfSession = defaultNoOfSession;
	}
	public String getDefaultDuration() {
		return defaultDuration;
	}
	public void setDefaultDuration(String defaultDuration) {
		this.defaultDuration = defaultDuration;
	}
	public String getIsProcedure() {
		return isProcedure;
	}
	public void setIsProcedure(String isProcedure) {
		this.isProcedure = isProcedure;
	}
	public String getTestDay() {
		return testDay;
	}
	public void setTestDay(String testDay) {
		this.testDay = testDay;
	}
	
	
	public String getIsRequisitionFormrequired() {
		return isRequisitionFormrequired;
	}
	public void setIsRequisitionFormrequired(String isRequisitionFormrequired) {
		this.isRequisitionFormrequired = isRequisitionFormrequired;
	}
	public String getLabRequisitionFormRequired() {
		return labRequisitionFormRequired;
	}
	public void setLabRequisitionFormRequired(String labRequisitionFormRequired) {
		this.labRequisitionFormRequired = labRequisitionFormRequired;
	}
	public List<Entry> getSpecimenList() {
		return specimenList;
	}
	public void setSpecimenList(List<Entry> specimenList) {
		this.specimenList = specimenList;
	}
	public final String getLabTestRowString(int k,String groupCode,String groupName,String groupTypeCode,String groupTypeName,String raisedGroupNo,String defaultGroupSampleCode) {
		try
		{
			String classStr=null;
			String defaultSampleCodetemp=null;
		//	LOGGER_INV.log(Level.INFO,"defaultSampleCode  "+defaultGroupSampleCode);
			if(this.testType.equals("S"))
				classStr="tdblue";
			else if(this.testType.equals("I"))
			{
				classStr="tdfonthead";
			}
			else
			{
				classStr="tdfont";
			}
					
			labTestRowString="<tr class='"+classStr+"' id='requestTestTr"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"'>\n";
			labTestRowString+="<td class='"+classStr+"' width='15%'><div align='left'>"+this.laboratoryName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='18%'><div align='left'>"+this.testName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='23%'><div align='center' nowrap='nowrap'>\n";
			
			
			if(this.isMultiSessionTest.equals("0"))		
			{	
			 if(this.isAppointmentBased.equals("0"))
			 {
				 String datePickerId="proposedTestDateSlide"+groupCode+groupTypeCode+laboratoryCode+testCode;
				 labTestRowString+=getDateTagString("proposedTestDate", datePickerId, this.proposedTestDate, InvestigationConfig.datePickerDateFormate, null, null, "iconPickerId"+datePickerId, null, "checkTestDate(this)");
				 labTestRowString+="<input type='hidden' name='testDay' value='"+this.testDay+"'/>\n";
			 }
			 else if(this.isAppointmentBased.equals("1"))
			 {
				 labTestRowString+="<img value='GET APPOINTMENT' src='/AHIMS/hisglobal/images/getAppointment.png'></img>";
				 labTestRowString+="<input type='hidden' name='proposedTestDate'/>";
				 labTestRowString+="<input type='hidden' name='appointmentSlotReferenceId'/>";
				 labTestRowString+="<input type='hidden' name='appointmentSlotStartTime'/>";
				 labTestRowString+="<input type='hidden' name=appointmentSlotEndTime'/>\n";
			 }	 
			 else		
			 {
				
			     if(Integer.parseInt(this.isAppointmentBased)>=1)
			     {
			    	 labTestRowString+="<input type=hidden name='proposedTestDate' value='"+((this.proposedTestDate==null)?"":this.proposedTestDate)+"'/>\n";
			    	 if(Integer.parseInt(this.isAppointmentBased)==2)
			    	 {
			    		 labTestRowString+="After Billing";			    		 
			    	 }	 
			    	if(Integer.parseInt(this.isAppointmentBased)==3)
			    	{
			    		labTestRowString+="By Lab";
			       	}	
			    	if(Integer.parseInt(this.isAppointmentBased)==2)
			    	{
			    		labTestRowString+="By Lab After Billing";
			    	
			    	}
			    	
			     }
			 }
			
			}			
			else
			{
				labTestRowString+="<img value='Multi Session Details' src='AHIMS/hisglobal/images/multisession.png' onclick=\"openpopup('/AHIMS/investigation/transaction/multisessionalDetailCollection.cnt?index="+k+"/>&listName=PARENTSLIDEBASEDTESTSESSIONLIST','600','410')\"></img>";
			
			}
						
			labTestRowString+="</div>\n";
			labTestRowString+="</td>\n";
				
				
			labTestRowString+="<td class='"+classStr +"' width='12%'>\n";
				if(this.testType.equals("I"))
				{
				labTestRowString+="<div align='left'><select name='testComponentType' class='comboClass'><option value='0'>Specimen</option><option value='1'>Slide</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>\n";
				}
			
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='16%' >\n";
							
				if(this.testType.equals("P")==false)
				{	
					labTestRowString+="<div align='left'><select name='selectedSpecimenCode' id='spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"' class='comboClass'><option value='-1'>Select</option>" ;
					
					if(defaultGroupSampleCode!=null && defaultGroupSampleCode.equals("null")==false && defaultGroupSampleCode.equals("")==false && defaultGroupSampleCode.equals("-1")==false)
					{
						defaultSampleCodetemp=defaultGroupSampleCode;
					}
					else if(defaultSampleCode!=null)
					{
						defaultSampleCodetemp=defaultSampleCode.substring(4);
					}
					else
					{
						defaultSampleCodetemp=null;
					}
						
					//LOGGER_INV.log(Level.INFO,"step2-->"+defaultSampleCodetemp);
					for(Entry e:this.specimenList)
					{
								
								if(defaultSampleCodetemp!=null)
								{
									if((defaultSampleCodetemp.equals(e.getValue().substring(4))) || (this.specimenList.size()==1))
											labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
								else
								{
									if((this.specimenList.size()==1))
										labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
					}
							
							
							
							
							
							labTestRowString+="<option value='999'>Unspecified</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>";
				
				}
				
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='9%'><div align='left'>\n<select name='priority' Class='comboClass'>"+
				"<option value='0'>Normal</option><option value='1'>Urgent</option></select>\n</div>\n";
				labTestRowString+="</td>";
				labTestRowString+="<td class='"+classStr+"' colspan='2'><div align='center' style='display: none'>\n";
				//<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+"'/><input type='text' name='testCode' value='"+this.testCode+"'></input><input type='label' name='k'></input>r' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/R.png'></div>
				//<div align='center' id='<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>r' style="border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>
				labTestRowString+="<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>\n";
				
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Name</td><td class='tdfont'>"+groupName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Type</td><td class='tdfont' >"+groupTypeName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Remarks</td><td class='tdfont'><textarea name='testRemarks'  rows='4' cols='50'>"+((this.testRemarks==null)?"":this.testRemarks)+"</textarea></td></tr>";
				labTestRowString+="\n<tr>\n<td colspan='2'>\n<div align='center'>\n<img onClick=\"Popup.hide(\'"+laboratoryCode+this.testCode+k+"r\')\" src='/AHIMS/hisglobal/images/Ok.png' ></img>\n</div>\n</td>\n</tr>\n";
				labTestRowString+="\n</table>\n";
				
				labTestRowString+="</div>\n</td>\n";
				
						
				
				labTestRowString+="<td class='"+classStr+"' width='7%'>\n<div align='center'>\n<table width='100%'>\n";
				labTestRowString+="\n<tr>";
				if(this.labRequisitionFormRequired.equals("0"))
				{
					if(this.isRequisitionFormrequired.equals("1"))
					{
					labTestRowString+="<td class='"+classStr+"'>\n<div align='center'>\n"+
					"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo='"+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png')></img>\n</div>\n"
					+"\n</td>";
					}
				}
					
				if(this.labRequisitionFormRequired.equals("1"))
					{
						if(this.isRequisitionFormrequired.equals("0"))
						{	
							//<logic:equal name="test" property="isRequisitionFormrequired" value="0">
							labTestRowString+="\n<td class='"+classStr+"'>" +
									"\n<div align='center'>" +
									"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img></div>\n";
							labTestRowString+="\n</td>";
						}
					
					if(this.isRequisitionFormrequired.equals("1"))
						{
							
						labTestRowString+="\n<td class='"+classStr+"'><div align='center'><img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img> \n</div>\n</td>";
						}
					
					}
				
				
				
				if(this.testType.equals("S"))
				{
				labTestRowString+="\n<td width='50%'><img style='cursor: pointer' width='20px' height='20px' alt='AssociateSampleNo' name='associateSampleNo' onclick=\"openpopupAssociatedSampleNo('/AHIMS/investigation/transaction/sampleReqAssocition.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+((groupCode==null ||groupCode.equals(""))?"":"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo)+"&listName=PARENTSLIDEBASEDTESTSESSIONLIST&hmode=GETSAMPLENUMBERSPEED','600','410','spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"')\" src='/AHIMS/hisglobal/images/A.png' ></img></td>";				
				labTestRowString+="\n<td width='50%'>";
				if(this.isMandatoryAssociated.equals("0"))
					labTestRowString+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='"+this.laboratoryCode+this.testCode+k+"Mand' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/Mandatory.png' ></img>";
				}
				
				labTestRowString+="</td>\n";
				labTestRowString+="\n<td width='50%'><div align='right'><img src='/AHIMS/hisglobal/images/Minus.png' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></div>";
				labTestRowString+="\n</td>\n";
				labTestRowString+="\n</tr>";
				labTestRowString+="\n</table>";
				labTestRowString+="\n</div>";
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="\n<div id='"+this.laboratoryCode+this.testCode+k+"Mand' style='border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>";
					labTestRowString+="\n<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>";
					labTestRowString+="\n<tr><td colspan='2' class='HEADER'><div align='left'>Enter the Mandatories</div></td></tr>\n";
					//<Investigation:mandatorytemplate name="test" associatedDivId='<%=(((hisglobal.vo.Test)pageContext.findAttribute("test")).getLaboratoryCode())+(((hisglobal.vo.Test)pageContext.findAttribute("test")).getTestCode())+((Integer)pageContext.findAttribute("k")) %>'/><tr><td colspan='2' class='HEADER'>&nbsp;</td></tr><tr><td colspan='2'><div align="center"><img onClick="Popup.hide('<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>')" src="/AHIMS/hisglobal/images/Ok.png" ></div></td></tr></table></div>";
					labTestRowString+=getMandatoryTemplateHtmlString(""+k)+"\n</table>\n</div>";
				}
				
				labTestRowString+="\n</td>";
				labTestRowString+="\n</tr>";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	return labTestRowString;
	}
	public void setLabTestRowString(String labTestRowString) {
		this.labTestRowString = labTestRowString;
	}
	
	public Map<Integer, SampleGroupDetail> getSpecimenGroupDetailList() {
		return specimenGroupDetailList;
	}
	public void setSpecimenGroupDetailList(
			Map<Integer, SampleGroupDetail> specimenGroupDetailList) {
		this.specimenGroupDetailList = specimenGroupDetailList;
	}
	public String getTestRemarks() {
		return testRemarks;
	}
	public void setTestRemarks(String testRemarks) {
		this.testRemarks = testRemarks;
	}
	public String getProposedTestDate() {
		return proposedTestDate;
	}
	public void setProposedTestDate(String proposedTestDate) {
		this.proposedTestDate = proposedTestDate;
	}
	public void setIsMandatoryAssociated(String isMandatoryAssociated) {
		this.isMandatoryAssociated = isMandatoryAssociated;
	}
	
	public  String getMandatoryTemplateHtmlString(String k) {

			System.out.println("calliong getMandatoryTemplateHtmlString");
		//value is the mandatory Name and name is the mandatory code
		int rowNo=0;
		String htmlString="";
		if(this.getMandatoryInfoDtl()!=null)
		{
			System.out.println("calliong getMandatoryTemplateHtmlString");
		for(TemplateRepresentationClass templateRepresentationClass:this.getMandatoryInfoDtl())
		{
			if(rowNo==0||rowNo==templateRepresentationClass.getRowNo())
			{
				if(rowNo==0)
					htmlString+="<tr>";
				else
					htmlString+="</tr><tr>";
			}
			
			if(templateRepresentationClass.getHtmlObjectType().equals("textBox"))
				htmlString+="<td class='tdfont' width='50%'><div align='right'><b>"+templateRepresentationClass.getValue()+"</b></div></td><td class='tdfonthead' width='50%'><div align='left'><input type='text' name='mandatoryInfo' value='"+((templateRepresentationClass.getHtmlObjectValue()==null)?"":templateRepresentationClass.getHtmlObjectValue())+"' title='"+this.laboratoryCode+this.testCode+k+"Mand'></div></td>";
			else
				{
				htmlString+="<td class='tdfont' width='50%'><div align='right'><b>"+templateRepresentationClass.getValue()+"</b></div></td><td class='tdfonthead' width='50%'><div align='left'><select name='mandatoryInfo' title='"+this.laboratoryCode+this.testCode+k+"Mand'><option value='-1' selected>select</option>";
				for(Entry entryObj:templateRepresentationClass.getOptions())
				{
					if(entryObj.getValue().equals(templateRepresentationClass.getHtmlObjectValue()))
						htmlString+="<option value='"+entryObj.getValue()+"' selected>"+entryObj.getLabel()+"</option>";
					else
						htmlString+="<option value='"+entryObj.getValue()+"'>"+entryObj.getLabel()+"</option>";
				}
				htmlString+="</select></div></td>";
				}
		}
		htmlString+="</tr>" +
				"<tr>" +
				"<td colspan='2'>" +
				"<div align='center'>" +
				"<img onClick=\"Popup.hide('"+laboratoryCode+testCode+k+"Mand')\" src=\"/AHIMS/hisglobal/images/ok.gif\" >" +
						"</div>" +
						"</td>" +
						"</tr>";		
		}
		
		System.out.println(htmlString);
		return htmlString;
	}
	
	public String getDateTagString(String name,String id,String propertyValue,String dateFormate,String onClick, String size,String idIconPicDate,String readOnly,String onChange)
	{
		String dateString = "";
		String onChangeString = "";
		if (onChange != null && !onChange.equalsIgnoreCase(""))
		{
			onChangeString = "onChange='" + onChange + "'";
		}
		String onClickStr = "";
		if (onClick != null && !onClick.equalsIgnoreCase(""))
		{
			onClickStr = "onClick='" + onClick + "'";
		}

		if (dateFormate == null || dateFormate.equals("")) dateFormate = InvestigationConfig.datePickerDateFormate;

		if (id == null || id.equals("")) id = name;

		String value = "";
		if (propertyValue != null && !propertyValue.equals("")) value = "value='" +  (DateFormatter.getDate(propertyValue,InvestigationConfig.databaseDateFormate, InvestigationConfig.formDateFormate)) + "'";

		String sizer = "";
		if (size != null) sizer += "size ='" + size + "'";

		String readOnlys = "";
		if (readOnly != null && readOnly.equals("true")) readOnlys += "readOnly";

		dateString += "<input type='text' name='" + name + "' id='" + id + "'  " +value + " " + onClickStr + " " + onChangeString
				+ " " + sizer + " " + readOnlys + "/> " + "	<img src='/AHIMS/hisglobal/images/imgDatepicker.png' name='" + idIconPicDate + "100' id='"
				+ ( (idIconPicDate!=null)?idIconPicDate:(name+"100")) + "'style='cursor: pointer; border: 1px solid;position: absolute;'  " + "	title='Date selector' "
				+ "	onmouseover='this.style.background=\"#FF0000\";'  " + "	 onmouseout='this.style.background=\"#FFFFFF\";'/> ";
				
				
				/*
				+ "	<script language='JavaScript'> " + "Calendar.setup" + "(" + " {" + " inputField     :    '" + id + "'," +

				" ifFormat       :   '" + dateFormate + "'," + " button         :   '" +( (idIconPicDate!=null)?idIconPicDate:name+"100")+ "'," + " singleClick    :    true" +

				" }" + ");	</script>";
				*/
		
		return dateString;
	}
	public void clear() {
	
		
	}
	//for investigation offline
	public String getLabTestRowStringOffline(int k, String groupCode,
			String groupName, String groupTypeCode, String groupTypeName,
			String raisedGroupNo, String machineString,String billno,String defaultGroupSampleCode) {
		try
		{
			String classStr=null;
			String defaultSampleCodetemp=null;
		//	LOGGER_INV.log(Level.INFO,"defaultSampleCode  "+defaultSampleCode);
			if(this.testType.equals("S"))
				classStr="tdblue";
			else if(this.testType.equals("I"))
			{
				classStr="tdfonthead";
			}
			else
			{
				classStr="tdfont";
			}
					
			labTestRowString="<tr class='"+classStr+"' id='requestTestTr"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"'>\n";
			labTestRowString+="<td class='"+classStr+"' width='4%'><input type='checkbox' name='isTestSelected' value='on' checked id='requestTestCheck"+this.laboratoryCode+groupCode+raisedGroupNo+"$"+billno+"#"+k+"'  alt='"+this.testCode+"#"+groupCode+"' onclick='enabledisablethis(this,\""+groupTypeCode+"\",\""+groupName+"\");'/></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='15%' style='display:none'><div align='left'>"+this.laboratoryName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='18%'><div align='left'>"+this.testName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='23%' style='display:none'><div align='center' nowrap='nowrap'>\n";
			
			
			if(this.isMultiSessionTest.equals("0"))		
			{	
			 if(this.isAppointmentBased.equals("0"))
			 {
				 String datePickerId="proposedTestDateSlide"+groupCode+groupTypeCode+laboratoryCode+testCode;
				 labTestRowString+=getDateTagString("proposedTestDate", datePickerId, this.proposedTestDate, InvestigationConfig.datePickerDateFormate, null, null, "iconPickerId"+datePickerId, null, "checkTestDate(this)");
				 labTestRowString+="<input type='hidden' name='testDay' value='"+this.testDay+"'/>\n";
			 }
			 else if(this.isAppointmentBased.equals("1"))
			 {
				 labTestRowString+="<img value='GET APPOINTMENT' src='/AHIMS/hisglobal/images/getAppointment.png'></img>";
				 labTestRowString+="<input type='hidden' name='proposedTestDate' />";
				 labTestRowString+="<input type='hidden' name='appointmentSlotReferenceId'/>";
				 labTestRowString+="<input type='hidden' name='appointmentSlotStartTime'/>";
				 labTestRowString+="<input type='hidden' name=appointmentSlotEndTime'/>\n";
			 }	 
			 else		
			 {
				
			     if(Integer.parseInt(this.isAppointmentBased)>=1)
			     {
			    	 labTestRowString+="<input type=hidden name='proposedTestDate' value='"+((this.proposedTestDate==null)?"":this.proposedTestDate)+"'/>\n";
			    	 if(Integer.parseInt(this.isAppointmentBased)==2)
			    	 {
			    		 labTestRowString+="After Billing";			    		 
			    	 }	 
			    	if(Integer.parseInt(this.isAppointmentBased)==3)
			    	{
			    		labTestRowString+="By Lab";
			       	}	
			    	if(Integer.parseInt(this.isAppointmentBased)==2)
			    	{
			    		labTestRowString+="By Lab After Billing";
			    	
			    	}
			    	
			     }
			 }
			
			}			
			else
			{
				labTestRowString+="<img value='Multi Session Details' src='AHIMS/hisglobal/images/multisession.png' onclick=\"openpopup('/AHIMS/investigation/transaction/multisessionalDetailCollection.cnt?index="+k+"/>&listName=PARENTSLIDEBASEDTESTSESSIONLIST','600','410')\"></img>";
			
			}
						
			labTestRowString+="</div>\n";
			labTestRowString+="</td>\n";
				
				
			labTestRowString+="<td class='"+classStr +"' width='12%'>\n";
				if(this.testType.equals("I"))
				{
				labTestRowString+="<div align='left'><select name='testComponentType' class='comboClass'><option value='0'>Specimen</option><option value='1'>Slide</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>\n";
				}
			
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='16%' >\n";
							
				if(this.testType.equals("P")==false)
				{	
					labTestRowString+="<div align='left'><select name='selectedSpecimenCode' id='spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"' class='comboClass'  onchange='onchange_selectedSpecimenCode(this)'><option value='-1'>Select</option>" ;
							
					if(defaultGroupSampleCode!=null && defaultGroupSampleCode.equals("null")==false && defaultGroupSampleCode.equals("")==false && defaultGroupSampleCode.equals("-1")==false)
					{
						defaultSampleCodetemp=defaultGroupSampleCode.substring(0,3);
					}
					else if(defaultSampleCode!=null)
					{
						defaultSampleCodetemp=defaultSampleCode.substring(4);
					}
					else
					{
						defaultSampleCodetemp="";
					}
						
					for(Entry e:this.specimenList)
					{
								
								if(defaultSampleCodetemp!=null)
								{
									if((defaultSampleCodetemp.equals(e.getValue().substring(4))) || (this.specimenList.size()==1))
											labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
								else
								{
									if((this.specimenList.size()==1))
										labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
					}
							
							
							
							
							
							labTestRowString+="<option value='999'>Unspecified</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>";
				
				}
				
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='9%' style='display:none'><div align='left'>\n<select name='priority' Class='comboClass'>"+
				"<option value='0'>Normal</option><option value='1'>Urgent</option></select>\n</div>\n";
				labTestRowString+="</td>";
				labTestRowString+="<td class='"+classStr+"' colspan='2' style='display:none'><div align='center' style='display: none'>\n";
				//<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+"'/><input type='text' name='testCode' value='"+this.testCode+"'></input><input type='label' name='k'></input>r' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/R.png'></div>
				//<div align='center' id='<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>r' style="border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>
				labTestRowString+="<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>\n";
				
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Name</td><td class='tdfont'>"+groupName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Type</td><td class='tdfont' >"+groupTypeName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Remarks</td><td class='tdfont'><textarea name='testRemarks'  rows='4' cols='50'>"+((this.testRemarks==null)?"":this.testRemarks)+"</textarea></td></tr>";
				labTestRowString+="\n<tr>\n<td colspan='2'>\n<div align='center'>\n<img onClick=\"Popup.hide(\'"+laboratoryCode+this.testCode+k+"r\')\" src='/AHIMS/hisglobal/images/Ok.png' ></img>\n</div>\n</td>\n</tr>\n";
				labTestRowString+="\n</table>\n";
				
				labTestRowString+="</div>\n</td>\n";
				
						
				
				labTestRowString+="<td class='"+classStr+"' width='7%'>\n<div align='center'>\n<table width='100%'>\n";
				labTestRowString+="\n<tr>";
				if(this.labRequisitionFormRequired.equals("0"))
				{
					if(this.isRequisitionFormrequired.equals("1"))
					{
					labTestRowString+="<td class='"+classStr+"'>\n<div align='center' style='display:none'>\n"+
					"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo='"+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png')></img>\n</div>\n"
					+"\n</td>";
					}
				}
					
				if(this.labRequisitionFormRequired.equals("1"))
					{
						if(this.isRequisitionFormrequired.equals("0"))
						{	
							//<logic:equal name="test" property="isRequisitionFormrequired" value="0">
							labTestRowString+="\n<td class='"+classStr+"' style='display:none'>" +
									"\n<div align='center'>" +
									"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img></div>\n";
							labTestRowString+="\n</td>";
						}
					
					if(this.isRequisitionFormrequired.equals("1"))
						{
							
						labTestRowString+="\n<td class='"+classStr+"' style='display:none'><div align='center'><img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img> \n</div>\n</td>";
						}
					
					}
				
				
				
				if(this.testType.equals("S"))
				{
				labTestRowString+="\n<td width='50%'><img style='cursor: pointer' width='20px' height='20px' alt='AssociateSampleNo' name='associateSampleNo' onclick=\"openpopupAssociatedSampleNo('/AHIMS/investigation/transaction/sampleReqAssocition.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+((groupCode==null ||groupCode.equals(""))?"":"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo)+"&listName=PARENTSLIDEBASEDTESTSESSIONLIST&hmode=GETSAMPLENUMBERSPEED&checkRaisedGroupNo=A','600','410','spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"')\" src='/AHIMS/hisglobal/images/A.png' ></img></td>";				
				labTestRowString+="\n<td width='50%'>";
				if(this.isMandatoryAssociated.equals("0"))
					labTestRowString+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+this.testCode+k+"' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/Mandatory.png' ></img>";
				}
				
				labTestRowString+="</td>\n";
				labTestRowString+="\n<td width='50%' style='display:none'><div align='right'><img src='/AHIMS/hisglobal/images/Minus.png' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></div>";
				labTestRowString+="\n</td>\n";
				labTestRowString+="\n</tr>";
				labTestRowString+="\n</table>";
				labTestRowString+="\n</div>";
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="\n<div id='"+this.laboratoryCode+this.testCode+k+"' style='border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>";
					labTestRowString+="\n<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>";
					labTestRowString+="\n<tr><td colspan='2' class='HEADER'><div align='left'>Enter the Mandatories</div></td></tr>";
					//<Investigation:mandatorytemplate name="test" associatedDivId='<%=(((hisglobal.vo.Test)pageContext.findAttribute("test")).getLaboratoryCode())+(((hisglobal.vo.Test)pageContext.findAttribute("test")).getTestCode())+((Integer)pageContext.findAttribute("k")) %>'/><tr><td colspan='2' class='HEADER'>&nbsp;</td></tr><tr><td colspan='2'><div align="center"><img onClick="Popup.hide('<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>')" src="/AHIMS/hisglobal/images/Ok.png" ></div></td></tr></table></div>";
					labTestRowString+=getMandatoryTemplateHtmlString(""+k);
				}
				
				labTestRowString+="\n</td>";
				
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='userSampleNo' onchange='userSampleNo_onChange(this)'/><img src='/AHIMS/hisglobal/images/Minus.png' style='display:none' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></td>";				
				
				if(this.testType.equals("P"))
				{
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><select type='text' name='machineNo' style='display:none' class='comboClass' onchange='machineNo_onChange(this)'><option value='0'>No Machine</option>"+this.getMachineStringforCombo()+"</select></td>";
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='rackNo' style='display:none' "+((this.getDefaultMachineCode()==null)?((this.machineList.size()!=1)?"readonly='readonly'":""):"readonly='readonly'")+"/></td>";	//readonly='readonly'
				}
				else
				{
					labTestRowString+="\n<td width='10%' class='"+classStr+"'><select type='text' name='machineNo' class='comboClass' onchange='machineNo_onChange(this)'><option value='0'>No Machine</option>"+this.getMachineStringforCombo()+"</select></td>";
					labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='rackNo' "+((this.getDefaultMachineCode()==null)?((this.machineList.size()!=1)?"readonly='readonly'":""):"readonly='readonly'")+ "/></td>";	//readonly='readonly'
						
				}
				
				labTestRowString+="<td width='10%' class='"+classStr+"' ><input type='text' name='sampleCollectionOfflineRemarks'  value='"+((this.sampleCollectionOfflineRemarks==null)?"":this.sampleCollectionOfflineRemarks)+ "' onchange ='transmitAll(this)'/></td>";
				labTestRowString+="\n</tr> ";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	return labTestRowString;
	}
	public String getLabTestRowStringOfflineWithLaboratory(int k, String groupCode,
			String groupName, String groupTypeCode, String groupTypeName,
			String raisedGroupNo, String machineString,String billno,String defaultGroupSampleCode) {
		try
		{
			//LOGGER_INV.log(Level.INFO,"<!-------------------getLabTestRowStringOfflineWithLaboratory----->");
			String classStr=null;
			String defaultSampleCodetemp=null;
			//LOGGER_INV.log(Level.INFO,"defaultSampleCode  "+defaultSampleCode);
			if(this.testType.equals("S"))
				classStr="tdblue";
			else if(this.testType.equals("I"))
			{
				classStr="tdfonthead";
			}
			else
			{
				classStr="tdfont";
			}
					
			labTestRowString="<tr class='"+classStr+"' id='requestTestTr"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"'>\n";
			labTestRowString+="<td class='"+classStr+"' width='4%'><input type='checkbox' name='isTestSelected' value='on' checked id='requestTestCheck"+this.laboratoryCode+groupCode+raisedGroupNo+"$"+billno+"#"+k+"'  alt='"+this.testCode+"#"+groupCode+"' onclick='enabledisablethis(this,\""+groupTypeCode+"\",\""+groupName+"\");'/></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='15%' style=''><div align='left'>"+this.laboratoryName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='18%'><div align='left'>"+this.testName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='23%' style='display:none'><div align='center' nowrap='nowrap'>\n";
			
			
			if(this.isMultiSessionTest.equals("0"))		
			{	
			 if(this.isAppointmentBased.equals("0"))
			 {
				 String datePickerId="proposedTestDateSlide"+groupCode+groupTypeCode+laboratoryCode+testCode;
				 labTestRowString+=getDateTagString("proposedTestDate", datePickerId, this.proposedTestDate, InvestigationConfig.datePickerDateFormate, null, null, "iconPickerId"+datePickerId, null, "checkTestDate(this)");
				 labTestRowString+="<input type='hidden' name='testDay' value='"+this.testDay+"'/>\n";
			 }
			 else if(this.isAppointmentBased.equals("1"))
			 {
				 labTestRowString+="<img value='GET APPOINTMENT' src='/AHIMS/hisglobal/images/getAppointment.png'></img>";
				 labTestRowString+="<input type='hidden' name='proposedTestDate' />";
				 labTestRowString+="<input type='hidden' name='appointmentSlotReferenceId'/>";
				 labTestRowString+="<input type='hidden' name='appointmentSlotStartTime'/>";
				 labTestRowString+="<input type='hidden' name=appointmentSlotEndTime'/>\n";
			 }	 
			 else		
			 {
				
			     if(Integer.parseInt(this.isAppointmentBased)>=1)
			     {
			    	 labTestRowString+="<input type=hidden name='proposedTestDate' value='"+((this.proposedTestDate==null)?"":this.proposedTestDate)+"'/>\n";
			    	 if(Integer.parseInt(this.isAppointmentBased)==2)
			    	 {
			    		 labTestRowString+="After Billing";			    		 
			    	 }	 
			    	if(Integer.parseInt(this.isAppointmentBased)==3)
			    	{
			    		labTestRowString+="By Lab";
			       	}	
			    	if(Integer.parseInt(this.isAppointmentBased)==2)
			    	{
			    		labTestRowString+="By Lab After Billing";
			    	
			    	}
			    	
			     }
			 }
			
			}			
			else
			{
				labTestRowString+="<img value='Multi Session Details' src='AHIMS/hisglobal/images/multisession.png' onclick=\"openpopup('/AHIMS/investigation/transaction/multisessionalDetailCollection.cnt?index="+k+"/>&listName=PARENTSLIDEBASEDTESTSESSIONLIST','600','410')\"></img>";
			
			}
						
			labTestRowString+="</div>\n";
			labTestRowString+="</td>\n";
				
				
			labTestRowString+="<td class='"+classStr +"' width='12%'>\n";
				if(this.testType.equals("I"))
				{
				labTestRowString+="<div align='left'><select name='testComponentType' class='comboClass'><option value='0'>Specimen</option><option value='1'>Slide</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>\n";
				}
			
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='16%' >\n";
							
				if(this.testType.equals("P")==false)
				{	
					labTestRowString+="<div align='left'><select name='selectedSpecimenCode' id='spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"' class='comboClass' onchange='onchange_selectedSpecimenCode(this)'><option value='-1'>Select</option>" ;
							
					if(defaultGroupSampleCode!=null && defaultGroupSampleCode.equals("null")==false && defaultGroupSampleCode.equals("")==false && defaultGroupSampleCode.equals("-1")==false)
					{
						defaultSampleCodetemp=defaultGroupSampleCode.substring(0,3);
					}
					else if(defaultSampleCode!=null)
					{
						defaultSampleCodetemp=defaultSampleCode.substring(4);
					}
					else
					{
						defaultSampleCodetemp="";
					}
						
					for(Entry e:this.specimenList)
					{
								
								if(defaultSampleCodetemp!=null)
								{
									if((defaultSampleCodetemp.equals(e.getValue().substring(4))) || (this.specimenList.size()==1))
											labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
								else
								{
									if((this.specimenList.size()==1))
										labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
					}
							
							
							
							
							
							labTestRowString+="<option value='999'>Unspecified</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>";
				
				}
				
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='9%' style='display:none'><div align='left'>\n<select name='priority' Class='comboClass'>"+
				"<option value='0'>Normal</option><option value='1'>Urgent</option></select>\n</div>\n";
				labTestRowString+="</td>";
				labTestRowString+="<td class='"+classStr+"' colspan='2' style='display:none'><div align='center' style='display: none'>\n";
				//<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+"'/><input type='text' name='testCode' value='"+this.testCode+"'></input><input type='label' name='k'></input>r' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/R.png'></div>
				//<div align='center' id='<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>r' style="border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>
				labTestRowString+="<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>\n";
				
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Name</td><td class='tdfont'>"+groupName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Type</td><td class='tdfont' >"+groupTypeName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Remarks</td><td class='tdfont'><textarea name='testRemarks'  rows='4' cols='50'>"+((this.testRemarks==null)?"":this.testRemarks)+"</textarea></td></tr>";
				labTestRowString+="\n<tr>\n<td colspan='2'>\n<div align='center'>\n<img onClick=\"Popup.hide(\'"+laboratoryCode+this.testCode+k+"r\')\" src='/AHIMS/hisglobal/images/Ok.png' ></img>\n</div>\n</td>\n</tr>\n";
				labTestRowString+="\n</table>\n";
				
				labTestRowString+="</div>\n</td>\n";
				
						
				
				labTestRowString+="<td class='"+classStr+"' width='7%'>\n<div align='center'>\n<table width='100%'>\n";
				labTestRowString+="\n<tr>";
				if(this.labRequisitionFormRequired.equals("0"))
				{
					if(this.isRequisitionFormrequired.equals("1"))
					{
					labTestRowString+="<td class='"+classStr+"'>\n<div align='center' style='display:none'>\n"+
					"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo='"+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png')></img>\n</div>\n"
					+"\n</td>";
					}
				}
					
				if(this.labRequisitionFormRequired.equals("1"))
					{
						if(this.isRequisitionFormrequired.equals("0"))
						{	
							//<logic:equal name="test" property="isRequisitionFormrequired" value="0">
							labTestRowString+="\n<td class='"+classStr+"' style='display:none'>" +
									"\n<div align='center'>" +
									"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img></div>\n";
							labTestRowString+="\n</td>";
						}
					
					if(this.isRequisitionFormrequired.equals("1"))
						{
							
						labTestRowString+="\n<td class='"+classStr+"' style='display:none'><div align='center'><img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img> \n</div>\n</td>";
						}
					
					}
				
				
				
				if(this.testType.equals("S"))
				{
				labTestRowString+="\n<td width='50%'><img style='cursor: pointer' width='20px' height='20px' alt='AssociateSampleNo' name='associateSampleNo' onclick=\"openpopupAssociatedSampleNo('/AHIMS/investigation/transaction/sampleReqAssocition.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+((groupCode==null ||groupCode.equals(""))?"":"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo)+"&listName=PARENTSLIDEBASEDTESTSESSIONLIST&hmode=GETSAMPLENUMBERSPEED&checkRaisedGroupNo=A','600','410','spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"')\" src='/AHIMS/hisglobal/images/A.png' ></img></td>";				
				labTestRowString+="\n<td width='50%'>";
				if(this.isMandatoryAssociated.equals("0"))
					labTestRowString+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+this.testCode+k+"' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/Mandatory.png' ></img>";
				}
				
				labTestRowString+="</td>\n";
				labTestRowString+="\n<td width='50%' style='display:none'><div align='right'><img src='/AHIMS/hisglobal/images/Minus.png' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></div>";
				labTestRowString+="\n</td>\n";
				labTestRowString+="\n</tr>";
				labTestRowString+="\n</table>";
				labTestRowString+="\n</div>";
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="\n<div id='"+this.laboratoryCode+this.testCode+k+"' style='border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>";
					labTestRowString+="\n<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>";
					labTestRowString+="\n<tr><td colspan='2' class='HEADER'><div align='left'>Enter the Mandatories</div></td></tr>";
					//<Investigation:mandatorytemplate name="test" associatedDivId='<%=(((hisglobal.vo.Test)pageContext.findAttribute("test")).getLaboratoryCode())+(((hisglobal.vo.Test)pageContext.findAttribute("test")).getTestCode())+((Integer)pageContext.findAttribute("k")) %>'/><tr><td colspan='2' class='HEADER'>&nbsp;</td></tr><tr><td colspan='2'><div align="center"><img onClick="Popup.hide('<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>')" src="/AHIMS/hisglobal/images/Ok.png" ></div></td></tr></table></div>";
					labTestRowString+=getMandatoryTemplateHtmlString(""+k);
				}
				
				labTestRowString+="\n</td>";
				
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='userSampleNo' onchange='userSampleNo_onChange(this)'/><img src='/AHIMS/hisglobal/images/Minus.png' style='display:none' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></td>";				
				
				if(this.testType.equals("P"))
				{
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><select type='text' name='machineNo' style='display:none' class='comboClass' onchange='machineNo_onChange(this)'><option value='0'>No Machine</option>"+this.getMachineStringforCombo()+"</select></td>";
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='rackNo' style='display:none' "+((this.getDefaultMachineCode()==null)?((this.machineList.size()!=1)?"readonly='readonly'":""):"readonly='readonly'")+ "'/></td>";	//readonly='readonly'
				}
				else
				{
					labTestRowString+="\n<td width='10%' class='"+classStr+"'><select type='text' name='machineNo' class='comboClass' onchange='machineNo_onChange(this)'><option value='0'>No Machine</option>"+this.getMachineStringforCombo()+"</select></td>";
					labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='rackNo' "+((this.getDefaultMachineCode()==null)?((this.machineList.size()!=1)?"readonly='readonly'":""):"readonly='readonly'")+ "'/></td>";	//readonly='readonly'
						
				}
				
				labTestRowString+="<td width='10%' class='"+classStr+"' ><input type='text' name='sampleCollectionOfflineRemarks'  value='"+((this.sampleCollectionOfflineRemarks==null)?"":this.sampleCollectionOfflineRemarks)+ "' onchange ='transmitAll(this)'/></td>";
				labTestRowString+="\n</tr>";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	return labTestRowString;
	}
	
	
	public String getLabTestRowSampleCollectStringOfflineWithLaboratory(int k, String groupCode,
			String groupName, String groupTypeCode, String groupTypeName,
			String raisedGroupNo, String machineString,String billno,String defaultGroupSampleCode) {
		try
		{
		//	LOGGER_INV.log(Level.INFO,"<!----------------------getLabTestRowSampleCollectStringOfflineWithLaboratory--------------->");
			String classStr=null;
			String defaultSampleCodetemp=null;
		//	LOGGER_INV.log(Level.INFO,"groupCode ::"+groupCode+"testcode::"+testCode+" defaultGroupSampleCode::"+defaultGroupSampleCode+"::defaultSampleCode  "+defaultSampleCode);
			if(this.testType.equals("S"))
				classStr="tdblue";
			else if(this.testType.equals("I"))
			{
				classStr="tdfonthead";
			}
			else
			{
				classStr="tdfont";
			}
					
			labTestRowString="<tr class='"+classStr+"' id='requestTestTr"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"'>\n";
			labTestRowString+="<td class='"+classStr+"' width='4%'><input type='checkbox' name='isTestSelected' value='on' checked id='requestTestCheck"+this.laboratoryCode+groupCode+raisedGroupNo+"$"+billno+"#"+k+"'  alt='"+this.testCode+"#"+groupCode+"' onclick='enabledisablethis(this,\""+groupTypeCode+"\",\""+groupName+"\");'/></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='15%' style='display:'><div align='left'>"+this.laboratoryName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='18%'><div align='left'>"+this.testName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='23%' style='display:none'><div align='center' nowrap='nowrap'>\n";
			
			
			if(this.isMultiSessionTest.equals("0"))		
			{	
			 if(this.isAppointmentBased.equals("0"))
			 {
				 String datePickerId="proposedTestDateSlide"+groupCode+groupTypeCode+laboratoryCode+testCode;
				 labTestRowString+=getDateTagString("proposedTestDate", datePickerId, this.proposedTestDate, InvestigationConfig.datePickerDateFormate, null, null, "iconPickerId"+datePickerId, null, "checkTestDate(this)");
				 labTestRowString+="<input type='hidden' name='testDay' value='"+this.testDay+"'/>\n";
			 }
			 else if(this.isAppointmentBased.equals("1"))
			 {
				 labTestRowString+="<img value='GET APPOINTMENT' src='/AHIMS/hisglobal/images/getAppointment.png'></img>";
				 labTestRowString+="<input type='hidden' name='proposedTestDate' />";
				 labTestRowString+="<input type='hidden' name='appointmentSlotReferenceId'/>";
				 labTestRowString+="<input type='hidden' name='appointmentSlotStartTime'/>";
				 labTestRowString+="<input type='hidden' name=appointmentSlotEndTime'/>\n";
			 }	 
			 else		
			 {
				
			     if(Integer.parseInt(this.isAppointmentBased)>=1)
			     {
			    	 labTestRowString+="<input type=hidden name='proposedTestDate' value='"+((this.proposedTestDate==null)?"":this.proposedTestDate)+"'/>\n";
			    	 if(Integer.parseInt(this.isAppointmentBased)==2)
			    	 {
			    		 labTestRowString+="After Billing";			    		 
			    	 }	 
			    	if(Integer.parseInt(this.isAppointmentBased)==3)
			    	{
			    		labTestRowString+="By Lab";
			       	}	
			    	if(Integer.parseInt(this.isAppointmentBased)==2)
			    	{
			    		labTestRowString+="By Lab After Billing";
			    	
			    	}
			    	
			     }
			 }
			
			}			
			else
			{
				labTestRowString+="<img value='Multi Session Details' src='AHIMS/hisglobal/images/multisession.png' onclick=\"openpopup('/AHIMS/investigation/transaction/multisessionalDetailCollection.cnt?index="+k+"/>&listName=PARENTSLIDEBASEDTESTSESSIONLIST','600','410')\"></img>";
			
			}
						
			labTestRowString+="</div>\n";
			labTestRowString+="</td>\n";
				
				
			labTestRowString+="<td class='"+classStr +"' width='12%'>\n";
				if(this.testType.equals("I"))
				{
				labTestRowString+="<div align='left'><select name='testComponentType' class='comboClass'><option value='0'>Specimen</option><option value='1'>Slide</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>\n";
				}
			
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='16%' >\n";
							
				if(this.testType.equals("P")==false)
				{	
					labTestRowString+="<div align='left'><select name='selectedSpecimenCode' id='spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"' class='comboClass'  onchange='onchange_selectedSpecimenCode(this)'><option value='-1'>Select</option>" ;
							
					if(defaultGroupSampleCode!=null && defaultGroupSampleCode.equals("null")==false && defaultGroupSampleCode.equals("")==false && defaultGroupSampleCode.equals("-1")==false)
					{
						defaultSampleCodetemp=defaultGroupSampleCode.substring(0,3);
					}
					else if(defaultSampleCode!=null)
					{
						defaultSampleCodetemp=defaultSampleCode.substring(4);
					}
					else
					{
						defaultSampleCodetemp="";
					}
						
					for(Entry e:this.specimenList)
					{
								
								if(defaultSampleCodetemp!=null)
								{
									 if(defaultSampleCodetemp.equals(e.getValue().substring(4)) || (this.specimenList.size()==1))
											labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
								else
								{
									if((this.specimenList.size()==1))
										labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
					}
						labTestRowString+="<option value='999'>Unspecified</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>";
				
				}
				
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='9%' style='display:none'><div align='left'>\n<select name='priority' Class='comboClass'>"+
				"<option value='0'>Normal</option><option value='1'>Urgent</option></select>\n</div>\n";
				labTestRowString+="</td>";
				labTestRowString+="<td class='"+classStr+"' colspan='2' style='display:none'><div align='center' style='display: none'>\n";
				//<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+"'/><input type='text' name='testCode' value='"+this.testCode+"'></input><input type='label' name='k'></input>r' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/R.png'></div>
				//<div align='center' id='<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>r' style="border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>
				labTestRowString+="<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>\n";
				
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Name</td><td class='tdfont'>"+groupName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Type</td><td class='tdfont' >"+groupTypeName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Remarks</td><td class='tdfont'><textarea name='testRemarks'  rows='4' cols='50'>"+((this.testRemarks==null)?"":this.testRemarks)+"</textarea></td></tr>";
				labTestRowString+="\n<tr>\n<td colspan='2'>\n<div align='center'>\n<img onClick=\"Popup.hide(\'"+laboratoryCode+this.testCode+k+"r\')\" src='/AHIMS/hisglobal/images/Ok.png' ></img>\n</div>\n</td>\n</tr>\n";
				labTestRowString+="\n</table>\n";
				
				labTestRowString+="</div>\n</td>\n";
				
						
				
				labTestRowString+="<td class='"+classStr+"' width='7%'>\n<div align='center'>\n<table width='100%'>\n";
				labTestRowString+="\n<tr>";
				if(this.labRequisitionFormRequired.equals("0"))
				{
					if(this.isRequisitionFormrequired.equals("1"))
					{
					labTestRowString+="<td class='"+classStr+"'>\n<div align='center' style='display:none'>\n"+
					"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo='"+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png')></img>\n</div>\n"
					+"\n</td>";
					}
				}
					
				if(this.labRequisitionFormRequired.equals("1"))
					{
						if(this.isRequisitionFormrequired.equals("0"))
						{	
							//<logic:equal name="test" property="isRequisitionFormrequired" value="0">
							labTestRowString+="\n<td class='"+classStr+"' style='display:none'>" +
									"\n<div align='center'>" +
									"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img></div>\n";
							labTestRowString+="\n</td>";
						}
					
					if(this.isRequisitionFormrequired.equals("1"))
						{
							
						labTestRowString+="\n<td class='"+classStr+"' style='display:none'><div align='center'><img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img> \n</div>\n</td>";
						}
					
					}
				
				
				
				if(this.testType.equals("S"))
				{
				labTestRowString+="\n<td width='50%'><img style='cursor: pointer' width='20px' height='20px' alt='AssociateSampleNo' name='associateSampleNo' onclick=\"openpopupAssociatedSampleNo('/AHIMS/investigation/transaction/sampleReqAssocition.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+((groupCode==null ||groupCode.equals(""))?"":"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo)+"&listName=PARENTSLIDEBASEDTESTSESSIONLIST&hmode=GETSAMPLENUMBERSPEED&checkRaisedGroupNo=A','600','410','spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"')\" src='/AHIMS/hisglobal/images/A.png' ></img></td>";				
				labTestRowString+="\n<td width='50%'>";
				if(this.isMandatoryAssociated.equals("0"))
					labTestRowString+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+this.testCode+k+"' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/Mandatory.png' ></img>";
				}
				
				labTestRowString+="</td>\n";
				labTestRowString+="\n<td width='50%' style='display:none'><div align='right'><img src='/AHIMS/hisglobal/images/Minus.png' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></div>";
				labTestRowString+="\n</td>\n";
				labTestRowString+="\n</tr>";
				labTestRowString+="\n</table>";
				labTestRowString+="\n</div>";
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="\n<div id='"+this.laboratoryCode+this.testCode+k+"' style='border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>";
					labTestRowString+="\n<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>";
					labTestRowString+="\n<tr><td colspan='2' class='HEADER'><div align='left'>Enter the Mandatories</div></td></tr>";
					//<Investigation:mandatorytemplate name="test" associatedDivId='<%=(((hisglobal.vo.Test)pageContext.findAttribute("test")).getLaboratoryCode())+(((hisglobal.vo.Test)pageContext.findAttribute("test")).getTestCode())+((Integer)pageContext.findAttribute("k")) %>'/><tr><td colspan='2' class='HEADER'>&nbsp;</td></tr><tr><td colspan='2'><div align="center"><img onClick="Popup.hide('<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>')" src="/AHIMS/hisglobal/images/Ok.png" ></div></td></tr></table></div>";
					labTestRowString+=getMandatoryTemplateHtmlString(""+k);
				}
				
				labTestRowString+="\n</td>";
				
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='userSampleNo' onchange='userSampleNo_onChange(this)'/></td>" ;
				labTestRowString+="<td width='10%' class='"+classStr+"' ><input type='text' name='sampleCollectionOfflineRemarks'  value='"+((this.sampleCollectionOfflineRemarks==null)?"":this.sampleCollectionOfflineRemarks)+ "' onchange='transmitAll(this)'/><img src='/AHIMS/hisglobal/images/Minus.png' style='display:none' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></td>";				
				labTestRowString+="\n</tr>";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	return labTestRowString;
	}
	public String getLabTestRowSampleCollectStringOffline(int k, String groupCode,
			String groupName, String groupTypeCode, String groupTypeName,
			String raisedGroupNo, String machineString,String billno,String defaultGroupSampleCode) {
		try
		{
			String classStr=null;
			String defaultSampleCodetemp=null;
		//	LOGGER_INV.log(Level.INFO,"groupCode ::"+groupCode+"testcode::"+testCode+" defaultGroupSampleCode::"+defaultGroupSampleCode+"::defaultSampleCode  "+defaultSampleCode);
			if(this.testType.equals("S"))
				classStr="tdblue";
			else if(this.testType.equals("I"))
			{
				classStr="tdfonthead";
			}
			else
			{
				classStr="tdfont";
			}
					
			labTestRowString="<tr class='"+classStr+"' id='requestTestTr"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"'>\n";
			labTestRowString+="<td class='"+classStr+"' width='4%'><input type='checkbox' name='isTestSelected' value='on' checked id='requestTestCheck"+this.laboratoryCode+groupCode+raisedGroupNo+"$"+billno+"#"+k+"' alt='"+this.testCode+"#"+groupCode+"' onclick='enabledisablethis(this,\""+groupTypeCode+"\",\""+groupName+"\");'/></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='15%' style='display:none'><div align='left'>"+this.laboratoryName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='18%'><div align='left'>"+this.testName+"</div></td>\n";
			labTestRowString+="<td class='"+classStr+"' width='23%' style='display:none'><div align='center' nowrap='nowrap'>\n";
			
			
			if(this.isMultiSessionTest.equals("0"))		
			{	
			 if(this.isAppointmentBased.equals("0"))
			 {
				 String datePickerId="proposedTestDateSlide"+groupCode+groupTypeCode+laboratoryCode+testCode;
				 labTestRowString+=getDateTagString("proposedTestDate", datePickerId, this.proposedTestDate, InvestigationConfig.datePickerDateFormate, null, null, "iconPickerId"+datePickerId, null, "checkTestDate(this)");
				 labTestRowString+="<input type='hidden' name='testDay' value='"+this.testDay+"'/>\n";
			 }
			 else if(this.isAppointmentBased.equals("1"))
			 {
				 labTestRowString+="<img value='GET APPOINTMENT' src='/AHIMS/hisglobal/images/getAppointment.png'></img>";
				 labTestRowString+="<input type='hidden' name='proposedTestDate' />";
				 labTestRowString+="<input type='hidden' name='appointmentSlotReferenceId'/>";
				 labTestRowString+="<input type='hidden' name='appointmentSlotStartTime'/>";
				 labTestRowString+="<input type='hidden' name=appointmentSlotEndTime'/>\n";
			 }	 
			 else		
			 {
				
			     if(Integer.parseInt(this.isAppointmentBased)>=1)
			     {
			    	 labTestRowString+="<input type=hidden name='proposedTestDate' value='"+((this.proposedTestDate==null)?"":this.proposedTestDate)+"'/>\n";
			    	 if(Integer.parseInt(this.isAppointmentBased)==2)
			    	 {
			    		 labTestRowString+="After Billing";			    		 
			    	 }	 
			    	if(Integer.parseInt(this.isAppointmentBased)==3)
			    	{
			    		labTestRowString+="By Lab";
			       	}	
			    	if(Integer.parseInt(this.isAppointmentBased)==2)
			    	{
			    		labTestRowString+="By Lab After Billing";
			    	
			    	}
			    	
			     }
			 }
			
			}			
			else
			{
				labTestRowString+="<img value='Multi Session Details' src='AHIMS/hisglobal/images/multisession.png' onclick=\"openpopup('/AHIMS/investigation/transaction/multisessionalDetailCollection.cnt?index="+k+"/>&listName=PARENTSLIDEBASEDTESTSESSIONLIST','600','410')\"></img>";
			
			}
						
			labTestRowString+="</div>\n";
			labTestRowString+="</td>\n";
				
				
			labTestRowString+="<td class='"+classStr +"' width='12%'>\n";
				if(this.testType.equals("I"))
				{
				labTestRowString+="<div align='left'><select name='testComponentType' class='comboClass'><option value='0'>Specimen</option><option value='1'>Slide</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>\n";
				}
			
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='16%' >\n";
							
				if(this.testType.equals("P")==false)
				{	
					labTestRowString+="<div align='left'><select name='selectedSpecimenCode' id='spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"' class='comboClass'  onchange='onchange_selectedSpecimenCode(this)'><option value='-1'>Select</option>" ;
							
					if(defaultGroupSampleCode!=null && defaultGroupSampleCode.equals("null")==false && defaultGroupSampleCode.equals("")==false && defaultGroupSampleCode.equals("-1")==false)
					{
						defaultSampleCodetemp=defaultGroupSampleCode.substring(0,3);
					}
					else if(defaultSampleCode!=null)
					{
						defaultSampleCodetemp=defaultSampleCode.substring(4);
					}
					else
					{
						defaultSampleCodetemp="";
					}
						
					for(Entry e:this.specimenList)
					{
								
								if(defaultSampleCodetemp!=null)
								{
									 if(defaultSampleCodetemp.equals(e.getValue().substring(4)) || (this.specimenList.size()==1))
											labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
								else
								{
									if((this.specimenList.size()==1))
										labTestRowString+="<option value='"+e.getValue()+"' selected>"+e.getLabel()+" </option>";
									else
										labTestRowString+="<option value='"+e.getValue()+"'>"+e.getLabel()+" </option>";
								}
					}
						labTestRowString+="<option value='999'>Unspecified</option></select></div>";
				}
				else
				{
					labTestRowString+="<div align='center'>NA</div>";
				
				}
				
				labTestRowString+="</td>\n";
				
				labTestRowString+="<td class='"+classStr+"' width='9%' style='display:none'><div align='left'>\n<select name='priority' Class='comboClass'>"+
				"<option value='0'>Normal</option><option value='1'>Urgent</option></select>\n</div>\n";
				labTestRowString+="</td>";
				labTestRowString+="<td class='"+classStr+"' colspan='2' style='display:none'><div align='center' style='display: none'>\n";
				//<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+"'/><input type='text' name='testCode' value='"+this.testCode+"'></input><input type='label' name='k'></input>r' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/R.png'></div>
				//<div align='center' id='<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>r' style="border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>
				labTestRowString+="<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>\n";
				
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Name</td><td class='tdfont'>"+groupName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Group Type</td><td class='tdfont' >"+groupTypeName+"</td>\n</tr>\n";
				labTestRowString+="\n<tr>\n<td class='tdfonthead'>Remarks</td><td class='tdfont'><textarea name='testRemarks'  rows='4' cols='50'>"+((this.testRemarks==null)?"":this.testRemarks)+"</textarea></td></tr>";
				labTestRowString+="\n<tr>\n<td colspan='2'>\n<div align='center'>\n<img onClick=\"Popup.hide(\'"+laboratoryCode+this.testCode+k+"r\')\" src='/AHIMS/hisglobal/images/Ok.png' ></img>\n</div>\n</td>\n</tr>\n";
				labTestRowString+="\n</table>\n";
				
				labTestRowString+="</div>\n</td>\n";
				
						
				
				labTestRowString+="<td class='"+classStr+"' width='7%'>\n<div align='center'>\n<table width='100%'>\n";
				labTestRowString+="\n<tr>";
				if(this.labRequisitionFormRequired.equals("0"))
				{
					if(this.isRequisitionFormrequired.equals("1"))
					{
					labTestRowString+="<td class='"+classStr+"'>\n<div align='center' style='display:none'>\n"+
					"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo='"+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png')></img>\n</div>\n"
					+"\n</td>";
					}
				}
					
				if(this.labRequisitionFormRequired.equals("1"))
					{
						if(this.isRequisitionFormrequired.equals("0"))
						{	
							//<logic:equal name="test" property="isRequisitionFormrequired" value="0">
							labTestRowString+="\n<td class='"+classStr+"' style='display:none'>" +
									"\n<div align='center'>" +
									"\n<img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img></div>\n";
							labTestRowString+="\n</td>";
						}
					
					if(this.isRequisitionFormrequired.equals("1"))
						{
							
						labTestRowString+="\n<td class='"+classStr+"' style='display:none'><div align='center'><img style='cursor: pointer' width='20px' height='20px' alt='Requisition Form' name='requisitionImg' onclick=\"openRequisitionFormpopup('/AHIMS/investigation/transaction/requisitionFormAct.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo+"&hmode=REQUISITIONFORMSPEED','800','600')\" src='/AHIMS/hisglobal/images/RF.png'></img> \n</div>\n</td>";
						}
					
					}
				
				
				
				if(this.testType.equals("S"))
				{
				labTestRowString+="\n<td width='50%'><img style='cursor: pointer' width='20px' height='20px' alt='AssociateSampleNo' name='associateSampleNo' onclick=\"openpopupAssociatedSampleNo('/AHIMS/investigation/transaction/sampleReqAssocition.cnt?laboratoryCode="+this.laboratoryCode+"&testCode="+this.testCode+"&k="+k+((groupCode==null ||groupCode.equals(""))?"":"&groupCode="+groupCode+"&raisedGroupNo="+raisedGroupNo)+"&listName=PARENTSLIDEBASEDTESTSESSIONLIST&hmode=GETSAMPLENUMBERSPEED&checkRaisedGroupNo=A','600','410','spc_"+this.laboratoryCode+groupCode+this.testCode+k+raisedGroupNo+"')\" src='/AHIMS/hisglobal/images/A.png' ></img></td>";				
				labTestRowString+="\n<td width='50%'>";
				if(this.isMandatoryAssociated.equals("0"))
					labTestRowString+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="<img style='cursor: pointer' width='20px' height='20px' alt='Mandatory' name='laboratoryCode' value='"+this.laboratoryCode+this.testCode+k+"' onclick='showMandatoryForTest(this)' src='/AHIMS/hisglobal/images/Mandatory.png' ></img>";
				}
				
				labTestRowString+="</td>\n";
				labTestRowString+="\n<td width='50%' style='display:none'><div align='right'><img src='/AHIMS/hisglobal/images/Minus.png' title='0' name='requestedMinus' id='requestedTestId_"+"L"+this.laboratoryCode+(((groupCode==null||groupCode.equals(""))?"":"G"+groupCode+"GT"+groupTypeCode+"RNO"+raisedGroupNo)+"T"+this.testCode)+"' alt='Delete This Test' style='cursor: pointer' value='"+this.testCode+"' onClick=\"deleteTestOrGroup('REMOVEFROMPARENTSLIDEBASEDTESTSESSIONLIST','"+this.laboratoryCode+this.testCode+"','"+k+"','"+groupCode+"','"+raisedGroupNo+"','"+groupTypeCode+"',this.title)\" ></img></div>";
				labTestRowString+="\n</td>\n";
				labTestRowString+="\n</tr>";
				labTestRowString+="\n</table>";
				labTestRowString+="\n</div>";
				if(this.isMandatoryAssociated.equals("1"))
				{
					labTestRowString+="\n<div id='"+this.laboratoryCode+this.testCode+k+"' style='border:3px solid black; background-color:#ffffff; padding:25px; font-size:150%; text-align:center; display:none;'>";
					labTestRowString+="\n<table width='100%' bgcolor='#ffffff' border='1' bordercolor='#000000'>";
					labTestRowString+="\n<tr><td colspan='2' class='HEADER'><div align='left'>Enter the Mandatories</div></td></tr>";
					//<Investigation:mandatorytemplate name="test" associatedDivId='<%=(((hisglobal.vo.Test)pageContext.findAttribute("test")).getLaboratoryCode())+(((hisglobal.vo.Test)pageContext.findAttribute("test")).getTestCode())+((Integer)pageContext.findAttribute("k")) %>'/><tr><td colspan='2' class='HEADER'>&nbsp;</td></tr><tr><td colspan='2'><div align="center"><img onClick="Popup.hide('<bean:write name="test" property="laboratoryCode"/><bean:write name="test" property="testCode"/><bean:write name="k"/>')" src="/AHIMS/hisglobal/images/Ok.png" ></div></td></tr></table></div>";
					labTestRowString+=getMandatoryTemplateHtmlString(""+k);
				}
				
				labTestRowString+="\n</td>";
				
				labTestRowString+="\n<td width='10%' class='"+classStr+"'><input type='text' name='userSampleNo' onchange='userSampleNo_onChange(this)'/></td>";
				labTestRowString+="<td width='10%' class='"+classStr+"' ><input type='text' name='sampleCollectionOfflineRemarks'  value='"+((this.sampleCollectionOfflineRemarks==null)?"":this.sampleCollectionOfflineRemarks)+ "' onchange='transmitAll(this)'/></td>";
				labTestRowString+="\n</tr>";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	return labTestRowString;
	}
	
	public String getIsPartialTansmission() {
		return isPartialTansmission;
	}
	public void setIsPartialTansmission(String isPartialTansmission) {
		this.isPartialTansmission = isPartialTansmission;
	}
	public String getIsSampleCollectionFormRequired() {
		return isSampleCollectionFormRequired;
	}
	public void setIsSampleCollectionFormRequired(
			String isSampleCollectionFormRequired) {
		this.isSampleCollectionFormRequired = isSampleCollectionFormRequired;
	}
	public String getLabTestRowString() {
		return labTestRowString;
	}
	public String getPrintedWithTemplateID() {
		return printedWithTemplateID;
	}
	public void setPrintedWithTemplateID(String printedWithTemplateID) {
		this.printedWithTemplateID = printedWithTemplateID;
	}
	public String getPrintingType() {
		return printingType;
	}
	public void setPrintingType(String printingType) {
		this.printingType = printingType;
	}
	public String getIsconfidential() {
		return isconfidential;
	}
	public void setIsconfidential(String isconfidential) {
		this.isconfidential = isconfidential;
	}
	public String getIsPatientAccepted() {
		return isPatientAccepted;
	}
	public void setIsPatientAccepted(String isPatientAccepted) {
		this.isPatientAccepted = isPatientAccepted;
	}
	public String getIsFilmRequired() {
		return isFilmRequired;
	}
	public void setIsFilmRequired(String isFilmRequired) {
		this.isFilmRequired = isFilmRequired;
	}
	public String getSampleCollectionOfflineRemarks() {
		return sampleCollectionOfflineRemarks;
	}
	public void setSampleCollectionOfflineRemarks(
			String sampleCollectionOfflineRemarks) {
		this.sampleCollectionOfflineRemarks = sampleCollectionOfflineRemarks;
	}
	public String getTariffCode() {
		return tariffCode;
	}
	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}
	public String getDefaultMachineCode() {
		
		System.out.println("Default Machine code"+defaultMachineCode);
		
		return defaultMachineCode;
	}
	public void setDefaultMachineCode(String defaultMachineCode) {
		this.defaultMachineCode = defaultMachineCode;
	}
	public List<Entry> getMachineList() {
		return machineList;
	}
	public void setMachineList(List<Entry> machineList) {
		this.machineList = machineList;
	}
	public String getMachineStringforCombo() {
		return machineStringforCombo;
	}
	public void setMachineStringforCombo(String machineStringforCombo) {
		this.machineStringforCombo = machineStringforCombo;
	}
	public String getIsFullColspanRequired() {
		return isFullColspanRequired;
	}
	public void setIsFullColspanRequired(String isFullColspanRequired) {
		this.isFullColspanRequired = isFullColspanRequired;
	}
	
	
	
	
	
	
}
