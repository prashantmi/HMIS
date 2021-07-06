package HisWeb.util;


import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Stream;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import hisglobal.utility.HisUtil;

public class PDFCreator {
//static String jsonData ="{'Patient_Name':'Dekho','CR_No':'379132000005962','EpisodeCode':'21611001','EpisodeVisitNo':'1','CurrentVisitDate':'27/10/2020','PatVisitType':'OPD General','LastVisitDate':'First Visit','PatientGender':'M','PatientAge':' 22 Yr','PatientCat':'GEN','PatientQueueNo':'9797978978','hosp_code':'37913','hrgnum_is_docuploaded':0,'ConsultantName':'Alok','PatientDept':'C F M/C F M Unit 1','patGaurdianName':'Qwertyui','PatCompleteGeneralDtl':'--#C F M ( C F M Unit 1) #202#Qwertyui#1#21611#216#27-Oct-2020 #C F M#C F M Unit 1#11#1#2020/10/27#2020/10/27#9797978978#0#0#-#0#0#^^^null','seatId':'12175','HistoryOfPresentIllNess':' History Of Present Illness','DiagnosisNote':' Diagnosis Note','InvestgationNote':' Investigation Note','OtherAllergies':'','ReasonOfVisitJsonArray':[{'IsExternalVisit':'1','VisitReasonName':'Fever seizure','VisitReasonCode':'41497008','VisitReasonSideCode':'1','VisitReasonSideName':'NR','VisitReasonNoOfDays':'12','VisitComplaintDurationCode':'2','VisitComplaintDurationName':'Week/s','VisitReasonRemarks':'remsks'},{'IsExternalVisit':'1','VisitReasonName':'Fever of unknown origin','VisitReasonCode':'7520000','VisitReasonSideCode':'3','VisitReasonSideName':'Right','VisitReasonNoOfDays':'16','VisitComplaintDurationCode':'1','VisitComplaintDurationName':'Day/s','VisitReasonRemarks':'remkds'}],'DiagnosisJsonArray':[{'IsSnomed':'1','DiagnosisName':'Catu fever','DiagnosisCode':'89805002','DiagnosisSideCode':'2','DiagnosisSideName':'Left','DiagnosisTypeCode':'12','DiagnosisTypeNamee':'Differential','DiagnosisRemarks':'remks'},{'IsSnomed':'2','DiagnosisName':'Thoracic, thoracolumbar and lumbosacral disc disorders','DiagnosisCode':'M51   ','DiagnosisSideCode':'0','DiagnosisSideName':'Side','DiagnosisTypeCode':'14','DiagnosisTypeNamee':'Final'}],'InvestigationJsonArray':[{'IsExternal':'0','TestName':'Anti MUSK body titre (AMBT) ','TestCode':'13567','LabCode':'10087','SampleCode':'1047','SampleName':'FLUID','LabName':'Others-General Medicine','IsTestGroup':'0','SideCode':'1','SideName':'NR','SideRemarks':'rewv'},{'IsExternal':'0','TestName':'CBC (CPL-CBC) ','TestCode':'13631','LabCode':'10001','SampleCode':'1001','SampleName':'BLD','LabName':'Clinical Pathology Lab-General Medicine','IsTestGroup':'0','SideCode':'3','SideName':'Right','SideRemarks':'reas'}],'CompleteHistoryJaonArray':{'strpastHistory':' Past History','strpersonalHistory':'Personal History','strfamilyHistory':'Family History','strtreatmentHistory':'Treatment History','strsurgicalHistory':'Surgical History'},'SystematicExamniationArray':{'strcvs':' CVS','strrs':'RS','strcns':'CNS','strpA':'PA','strotherExamn':'General Examination','strmuscularExamn':'Muscular Examination','strLocalExamn':'Local Examination'},'ChronicDiseaseArray':[{'CronicDiseaseName':'Fever;','CronicDiseaseId':'386661006','CronicDiseaseDuration':'22','CronicDiseaseRemarks':'remaks'}],'PiccleArray':{'strpallor':'1','stricterus':'1','strcyanosis':'1','strclubbing':'1','striymphadenopathyId':'1','stredema':'1'},'ClinicalProcedureJsonArray':[{'IsExternal':'1','ProceduresName':'procedure','ProcedureCode':'0^0','ProcedureSideCode':'1','ProcedureSideName':'NR','ProcedureSideRemarks':'remarks'}],'DrugJsonArray':[{'IsExterNal':'0','DrugName':'Atecard 100 mg oral tablet Dabur India Limited','DrugCode':'10010354#101016##N#10128425','DoaseName':'--','DoaseCode':'0','FrequencyName':'TDS(1-1-1)','FrequencyCode':'13','StartDate':'2020-10-27','DrugDays':'12','DrugQuantity':'1','DrugInstruction':'Before Breakfast'}],'PatientRefrel':[{'strReffralDeptCmb':'191','strReffralDepttext':'E N T','strreferralType':'2','strreferralTypeName':' Emergency','strReffralReason':'remsks','strReffralDeptDone':'191#19111#1012#0#0##-1#'}],'strpiccle':{'strpallor':'1','stricterus':'1','strcyanosis':'1','strclubbing':'1','striymphadenopathyId':'1','stredema':'1'},'strtreatmentAdvice':' Treatment Advice','strConfidentialsInfo':'confidential info','strVitalsChart':'','FOLLOW_UP':[{'endTreatment':'0','Planned_Visit':[{'plannedVisitSos':'','plannedVisitDays':'11','plannedVisitDate':''}],'progressNote':'\\n\\nClinical Notes\\n'}],'strDeptIdflg':'0','strAllDeptIdflg':'1','strPresCriptionBookmarkNameval':'','strPresCriptionBookmarkDescVal':''}";
static Font f = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK );	
static Font fontHeading = FontFactory.getFont(FontFactory.COURIER_BOLD, 11, BaseColor.BLACK );	
public static Document createPdf(String jsonData , String vitalJsonData , String hospitalHeader , String UserName, String DataSaveTime) throws Exception {
		Thread.sleep(5 * 1000);
		Document document = new Document();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		 PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(HisUtil.getParameterFromHisPathXML("CREDIT_BILL_PATH")+"/print.pdf"));
		 writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
		 
		document.open();
		
		 org.json.JSONObject object = new  org.json.JSONObject(jsonData);
		
		 Path path = Paths.get(HisUtil.getParameterFromHisPathXML("LOGO_IMAGE"));
		 Image img = Image.getInstance(path.toAbsolutePath().toString());
		 //img.setAbsolutePosition(200f, 200f);
		 System.out.println(img.getHeight());
		 img.scaleAbsolute(50f, 50f);
		 
		 Chunk investigation=new Chunk("" ,f);
		 
		 LineSeparator ls = new LineSeparator();
		 LineSeparator ls1 = new LineSeparator();
		 
		 List drugstl=new List(List.ORDERED);
		 List procudure=new List(List.ORDERED);
		 List patReffer=new List(List.ORDERED);
		
		 Chunk chunk = new Chunk("" ,f);
		 
		Chunk hospitalName = new Chunk("All India Medical Sicenes" ,f);
		
		Chunk chkdiagnosis = new Chunk("" ,f);
		Chunk exanimation = new Chunk("" ,f);
		Chunk completehistorychunks = new Chunk("",f);
		Chunk cronicchunk = new Chunk("" ,f);
		Chunk clinicalnotes = new Chunk("" ,f);
		Chunk followup = new Chunk("" ,f);
		Chunk vitalchunks = new Chunk("" ,f);
		
		
		//document.add(img);
		JSONObject jshospitalHeader=new JSONObject(hospitalHeader);
		
		PdfPTable table = new PdfPTable(2);
	    table.setWidthPercentage(100);
	    
	    table.setWidths(new int[]{1, 6});
	    table.addCell(createImageCell(path.toString()));
	    table.addCell(createTextCell(jshospitalHeader.getString("GSTR_HOSPITAL_NAME")+"\n"+ jshospitalHeader.getString("GSTR_HOSPITAL_ADD1") ));
	    document.add(table);
	
		
		PdfPTable tablePatinfo = new PdfPTable(4);
		
		tablePatinfo.setWidthPercentage(98);
		
		patientInfo(tablePatinfo, jsonData);
		
		
		vitalChunks(vitalchunks , vitalJsonData);
		
		document.add( new Phrase("\n"));
		
		resonofVisit(chunk, jsonData);
		
		cgeifComplaint(chkdiagnosis, jsonData);
		
		
		examination(exanimation, jsonData);
		
		CompleteHistory(completehistorychunks, jsonData);
		
		cronicsDiseses(cronicchunk, jsonData);
		
		cinicalnotes(clinicalnotes, jsonData);
		followup(followup, jsonData);
		
		
		
		Investigation(investigation, jsonData);
		
		DrugDtl(drugstl, jsonData);
		
		procedures(procudure, jsonData);
		
		patientRefferTo(patReffer, jsonData);
		
		document.add(new Chunk(new LineSeparator()));
		
		document.add(tablePatinfo);
		
		document.add(new Chunk(new LineSeparator()));
		if(!(chunk.isEmpty())) {
			document.add( new Phrase("\n") );
		document.add( new Phrase("\n CHIEF COMPLAINT:" ,fontHeading) );
		document.add(new Phrase(chunk));
		}
		
		
		
		if(!(object.getString("HistoryOfPresentIllNess").trim().equalsIgnoreCase(""))) {
			document.add( new Phrase("\n") );
		//	document.add( new Phrase("\n CHIEF COMPLAINT :-") );
			document.add(new Phrase("\n HISTORY OF PRESENT ILLNESS : " ,fontHeading));
			document.add(new Phrase(object.getString("HistoryOfPresentIllNess").trim(),f));
			}
		
		
		  if(!(vitalchunks.isEmpty())) { StringBuffer sb =new
		  StringBuffer(vitalchunks.toString()); document.add( new Phrase("\n") );
		  document.add( new Phrase("\n VITALS/GE :" ,fontHeading) ); 
		  document.add(new Phrase(sb.deleteCharAt(sb.length()-1).toString() ,f)); }
		 
		
		if(!(exanimation.isEmpty())) {
			document.add( new Phrase("\n") );
			document.add( new Phrase("\n EXAMINATION: \n" ,fontHeading) );
			document.add(new Phrase(exanimation));
			}
		
		
		if(!(completehistorychunks.isEmpty())) {
			document.add( new Phrase("\n") );
			document.add( new Phrase("\n COMPLETE HISTORY:" ,fontHeading) );
			document.add(new Phrase(completehistorychunks));
			}
		
		if(!(cronicchunk.isEmpty())) {
			document.add( new Phrase("\n") );
			document.add( new Phrase("\n CHRONIC DISEASE :" ,fontHeading) );
			document.add(new Phrase(cronicchunk));
			}
		
		if(!(chkdiagnosis.isEmpty())) {
			document.add( new Phrase("\n") );
			document.add( new Phrase("\n DIAGNOSIS :" ,fontHeading) );
			document.add(chkdiagnosis);
			}
		
		if(!(object.getString("DiagnosisNote").trim().equalsIgnoreCase(""))) {
				document.add( new Phrase("\n") );
				document.add( new Phrase("\n") );
			//	document.add(new Phrase("\n HISTORY OF PRESENT ILLNESS : "));
				document.add(new Phrase(object.getString("DiagnosisNote").trim(),f));
				}
		
		if(!(investigation.isEmpty())) {
		document.add( new Phrase("\n"));
		document.add( new Phrase("\n INVESTIGATIONS ADVISED :",fontHeading) );
		
		document.add(new Phrase(investigation.toString() ,f));
		}
		
		if(!(object.getString("InvestgationNote").trim().equalsIgnoreCase(""))) {
				document.add( new Phrase("\n") );
				document.add( new Phrase("\n") );
				document.add(new Phrase(object.getString("InvestgationNote").trim(),f));
				}
		
		if(!(object.getString("strtreatmentAdvice").trim().equalsIgnoreCase(""))) {
			//	document.add( new Phrase("\n CHIEF COMPLAINT :-") );
			document.add( new Phrase("\n") );
				document.add(new Phrase("\n TREATMENT ADVICE : \n" ,fontHeading));
				document.add(new Phrase(object.getString("strtreatmentAdvice").trim(),f));
				}
		
		
		if(procudure.size() > 0) {
			document.add( new Phrase("\n") );
			document.add( new Phrase("\n PROCEDURES :",fontHeading) );
			document.add((Element) procudure);
			}
		
		System.out.println("drugstl.size()"+drugstl.size());
		if(drugstl.size() > 0) {
		
		document.add( new Phrase("\n"));
		document.add( new Phrase("\n Rx :" , fontHeading) );
		document.add((Element) drugstl);
		}
		
		
		if(!(clinicalnotes.isEmpty())) {
			document.add( new Phrase("\n") );
			document.add( new Phrase("\n CLINICAL NOTE :",fontHeading) );
			document.add(clinicalnotes);
			}
		document.add( new Phrase("\n FOLLOW UP :",fontHeading) );
		if(!(followup.isEmpty())) {
			document.add( new Phrase("\n") );
			if(!followup.toString().equalsIgnoreCase("Days")) {
			
			document.add(followup);
			}
		}
		if(patReffer.size() > 0) {
			document.add( new Phrase("\n") );
		document.add( new Phrase("\n REFER TO :",fontHeading) );
		document.add((Element) patReffer);
		}
		
		if(!(object.getString("ConsultantName").trim().equalsIgnoreCase(""))) {
			//	document.add( new Phrase("\n CHIEF COMPLAINT :-") );
				document.add(new Phrase("\n Signature of Consultant / Resident : \n" ,fontHeading));
				document.add(new Phrase(UserName,f));
				document.add(new Phrase("\n"+DataSaveTime,f));
		   }
		document.add( new Phrase("\n") );
		BarcodeQRCode my_code = new BarcodeQRCode(object.getString("CR_No").trim(), 1, 1, null);
		Image qr_image = my_code.getImage();
	//	document.add(new Phrase("\n") );
		qr_image.setAlignment(Element.ALIGN_RIGHT);
		qr_image.scaleAbsolute(90f, 90f);
		document.add(qr_image );
		/*
		 * qr_image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight()); float x
		 * = (PageSize.A4.getWidth() - qr_image.getScaledWidth()) / 2; float y =
		 * (PageSize.A4.getHeight() - qr_image.getScaledHeight()) / 2;
		 * qr_image.setAbsolutePosition(x, y);
		 */
	
		document.close();
		
		return document;
	}
	
private static void patientRefferTo(List list, String json) throws JSONException {
	
		JSONObject object = new JSONObject(json);
		JSONArray ja = new JSONArray(object.get("PatientRefrel").toString());
		//chunk.append("\n");
		for(int i=0;i<ja.length();i++) {
			Chunk ch=new Chunk();
			ch.append(((JSONObject) (ja.get(i))).getString("strReffralDepttext"));
			if(!(((JSONObject) (ja.get(i))).getString("strreferralType")).equalsIgnoreCase("0")) {
				ch.append("["+((JSONObject) (ja.get(i))).getString("strreferralTypeName")+"]");
			}
			if(!(((JSONObject) (ja.get(i))).getString("strReffralReason")).equalsIgnoreCase("")) {
				ch.append("("+((JSONObject) (ja.get(i))).getString("strReffralReason")+")");
			}
			list.add( new ListItem(ch +" " ,f))	;
		}
		
	}
	
private static void procedures(List list, String json) throws JSONException {
	
		JSONObject object = new JSONObject(json);
		JSONArray ja = new JSONArray(object.get("ClinicalProcedureJsonArray").toString());
		//chunk.append("\n");
		
		for(int i=0;i<ja.length();i++) {
			Chunk ch=new Chunk();
			String ProceduresName =((JSONObject) (ja.get(i))).getString("ProceduresName") ;
			String ServiceAreaName = "--";
			
			if(((JSONObject) (ja.get(i))).has("ServiceAreaName"))
				ServiceAreaName =((JSONObject) (ja.get(i))).getString("ServiceAreaName") ;
			
			ch.append(ServiceAreaName);
			ch.append("["+ProceduresName+"]");
			if(!(((JSONObject) (ja.get(i))).getString("ProcedureSideCode")).equalsIgnoreCase("0")) {
				
				ch.append("("+((JSONObject) (ja.get(i))).getString("ProcedureSideName")+")");
			}
			if((((JSONObject) (ja.get(i))).getString("ProcedureSideRemarks")).length() > 0) {
				ch.append("("+((JSONObject) (ja.get(i))).getString("ProcedureSideRemarks")+")");
			}
			
			list.add( new ListItem(ch  +" " ,f))	;
		}
		
	}

private static void DrugDtl(List list, String json) throws JSONException {
	
	JSONObject object = new JSONObject(json);
	JSONArray ja = new JSONArray(object.get("DrugJsonArray").toString());
	//chunk.append("\n");
	
	for(int i=0;i<ja.length();i++) {
		Chunk ch=new Chunk();
//		/String drug=((JSONObject) (ja.get(i))).getString("DrugName") +" ,";
		ch.append(((JSONObject) (ja.get(i))).getString("DrugName")+",");
		if(!(((JSONObject) (ja.get(i))).getString("DoaseName")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("DoaseName")+",");
		}
		if(!(((JSONObject) (ja.get(i))).getString("FrequencyName")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("FrequencyName")+",");
		}
		if(!(((JSONObject) (ja.get(i))).getString("DrugDays")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("DrugDays")+" Days,");
		}
		
		if(!(((JSONObject) (ja.get(i))).getString("StartDate")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("StartDate")+",");
		}
		
		if(!(((JSONObject) (ja.get(i))).getString("DrugInstruction")).equals("")) {
			ch.append("("+((JSONObject) (ja.get(i))).getString("DrugInstruction")+")");
		}
		
		list.add(new ListItem(ch +" ",f))	;
		
	}
	
}


private static void Investigation(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONArray ja = new JSONArray(object.get("InvestigationJsonArray").toString());
		
		for(int i=0;i<ja.length();i++) {
		//	chunk.append("\n");
			chunk.append(( i+1)+")"+((JSONObject) (ja.get(i))).getString("TestName") +" ,")	;
		}
		
	}

	private static void cgeifComplaint(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONArray ja = new JSONArray(object.get("DiagnosisJsonArray").toString());
		//chunk.append("\n");
		for(int i=0;i<ja.length();i++) {
			String DiagnosisName=((JSONObject) (ja.get(i))).getString("DiagnosisName") ;
			String DiagnosisTypeNamee=((JSONObject) (ja.get(i))).getString("DiagnosisTypeNamee") ;
			chunk.append("" + DiagnosisName+"("+ DiagnosisTypeNamee+")")	;
			if(!(((JSONObject) (ja.get(i))).getString("DiagnosisSideCode")).equalsIgnoreCase("0")) {
				chunk.append("("+ ((JSONObject) (ja.get(i))).getString("DiagnosisSideName")+")")	;	
			}
			/*
			 * if((((JSONObject) (ja.get(i))).getString("DiagnosisRemarks")).length()>0) {
			 * chunk.append("("+ ((JSONObject)
			 * (ja.get(i))).getString("DiagnosisRemarks")+")") ; }
			 */
			
			chunk.append(",");
		}
		
		
		
	}
	private static void cronicsDiseses(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONArray ja = new JSONArray(object.get("ChronicDiseaseArray").toString());
		//chunk.append("\n");
		for(int i=0;i<ja.length();i++) {
			String strCronicDeasesName=((JSONObject) (ja.get(i))).getString("CronicDiseaseName") ;
			chunk.append( strCronicDeasesName) ;
			if(!(((JSONObject) (ja.get(i))).getString("CronicDiseaseDuration")).equalsIgnoreCase("")) {
				chunk.append(" (" + ((JSONObject) (ja.get(i))).getString("CronicDiseaseDuration") +"yrs)")	;	
			}
			if((((JSONObject) (ja.get(i))).getString("CronicDiseaseRemarks")).equalsIgnoreCase("")) {
				chunk.append("(" + ((JSONObject) (ja.get(i))).getString("CronicDiseaseRemarks") +")")	;	
			}
			chunk.append(",");
		}
		
	}
	
	
	
private static void cinicalnotes(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONArray ja = new JSONArray(object.get("FOLLOW_UP").toString());
	
		for(int i=0;i<ja.length();i++) {
		
			if(!(((JSONObject) (ja.get(i))).getString("progressNote")).equalsIgnoreCase("")) {
				chunk.append( ((JSONObject) (ja.get(i))).getString("progressNote") )	;	
			}
			
		}
		
	}
	

private static void followup(Chunk chunk, String json) throws JSONException {
	
	JSONObject object = new JSONObject(json);
	JSONArray ja = new JSONArray(object.get("FOLLOW_UP").toString());
	
	for(int i=0;i<ja.length();i++) {
	
		JSONArray ja1=   (JSONArray) (((JSONObject) (ja.get(i))).get("Planned_Visit"));
		System.out.println(ja1.length());
		for(int j=0;j<ja1.length();j++) {
			chunk.append(((JSONObject) (ja1.get(i))).getString("plannedVisitDays")+"Days");
		}
			
	}
	
}

private static void CompleteHistory(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONObject js = new JSONObject(object.get("CompleteHistoryJaonArray").toString());
		if(js.getString("strpastHistory").length() > 0) {
			chunk.append("\n Past History:"+js.getString("strpastHistory"))	;
		}
		if(js.getString("strpersonalHistory").length() > 0) {
			chunk.append("\n Personal History:"+js.getString("strpersonalHistory"))	;
		}
		if(js.getString("strfamilyHistory").length() > 0) {
			chunk.append("\n Family History:"+js.getString("strfamilyHistory"))	;
		}
		if(js.getString("strtreatmentHistory").length() > 0) {
			chunk.append("\n Treatment History:"+js.getString("strtreatmentHistory"))	;
		}
		if(js.getString("strsurgicalHistory").length() > 0) {
			chunk.append("\n Surgical History:"+js.getString("strsurgicalHistory"))	;
		}
		
	}
	private static void examination(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONObject js = new JSONObject(object.get("PiccleArray").toString());
	
		if(js.getString("strpallor").equalsIgnoreCase("1")) {
			chunk.append("Pallor     ")	;
		}
		if(js.getString("stricterus").equalsIgnoreCase("1")) {
			chunk.append("Icterus     ")	;
		}
		if(js.getString("strcyanosis").equalsIgnoreCase("1")) {
			chunk.append("Cyanosis     ")	;
		}
		if(js.getString("strclubbing").equalsIgnoreCase("1")) {
			chunk.append("Clubbing     ")	;
		}
		if(js.getString("striymphadenopathyId").equalsIgnoreCase("1")) {
			chunk.append("Lymphadenopathy     ")	;
		}
		if(js.getString("stredema").equalsIgnoreCase("1")) {
			chunk.append("Edema     ")	;
		}
		
		
		JSONObject js1= new JSONObject(object.get("SystematicExamniationArray").toString());
		
		if(js1.getString("strcvs").length() > 0) {
			chunk.append("\n CVS:"+js1.getString("strcvs"))	;
		}
		if(js1.getString("strrs").length() > 0) {
			chunk.append("\n RS:"+js1.getString("strrs"))	;
		}
		if(js1.getString("strcns").length() > 0) {
			chunk.append("\n CNS:"+js1.getString("strcns"))	;
		}
		if(js1.getString("strpA").length() > 0) {
			chunk.append("\n P/A:"+js1.getString("strpA"))	;
		}
		if(js1.getString("strotherExamn").length() > 0) {
			chunk.append(new Chunk( "\n General Examination:")  +js1.getString("strotherExamn"))	;
		}
		if(js1.getString("strmuscularExamn").length() > 0) {
			chunk.append("\n Muscular Examination:"+js1.getString("strmuscularExamn"))	;
		}
		if(js1.getString("strLocalExamn").length() > 0) {
			chunk.append("\n Local Examination:"+js1.getString("strLocalExamn"))	;
		}
		
		
	}
	
	
	
private static void resonofVisit(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONArray ja = new JSONArray(object.get("ReasonOfVisitJsonArray").toString());
		//chunk.append("\n");
		for(int i=0;i<ja.length();i++) {
			
			String ResonVisit=((JSONObject) (ja.get(i))).getString("VisitReasonName") ;
			chunk.append( ResonVisit ) ;
			
			if(!(((JSONObject) (ja.get(i))).getString("VisitReasonSideName")).equals("0")) {
				chunk.append( "(" +((JSONObject) (ja.get(i))).getString("VisitReasonSideName") +")")	;
			}
			
			
			
			if(!(((JSONObject) (ja.get(i))).getString("VisitReasonNoOfDays")).equals("")  ) {
				chunk.append( "(" +((JSONObject) (ja.get(i))).getString("VisitReasonNoOfDays") + "," + ((JSONObject) (ja.get(i))).getString("VisitComplaintDurationName") +")")	;
			}
			
			if(!(((JSONObject) (ja.get(i))).getString("VisitReasonRemarks")).equals("")) {
				chunk.append("" +((JSONObject) (ja.get(i))).getString("VisitReasonRemarks") +"")	;
			}
			chunk.append(",");
		}
		
	}



private static void vitalChunks(Chunk chunk, String json) throws JSONException {
		
	if(json!=null &&  (!json.equalsIgnoreCase("{}"))) {
		 org.json.JSONObject object = new  org.json.JSONObject(json);
		
		
		if(object.getLong("hoplnum_weight_val") > 0.0 ) {
			chunk.append("Weight : "+object.get("hoplnum_weight_val").toString()+"KG.,");
		}
		if(object.getLong("hoplnum_height_val") > 0.0 ) {
			chunk.append("Height : "+object.get("hoplnum_height_val").toString()+"CM.,");
		}
		if(object.getLong("hoplnum_bmi_val") > 0.0 ) {
			chunk.append("BMI : "+object.get("hoplnum_bmi_val").toString()+"CM.,");
		}
			
			/*
			 * if(object.getLong("hoplnum_bpsy_val") > 0.0 &&
			 * object.getLong("hoplnum_bpdiasy_val") > 0.0 ) {
			 * chunk.append("BP : "+object.get("hoplnum_bpsy_val").toString()+"/,"+object.
			 * get("hoplnum_bpdiasy_val").toString()+"mmHg ,");
			 * 
			 * }
			 */
			 
		if(object.getLong("hoplnum_rr_val") > 0.0 ) {
			chunk.append("Respiration Rate : "+object.get("hoplnum_rr_val").toString()+"breaths/min ,");
		}
		
		if(object.getLong("hoplnum_hb_val") > 0.0 ) {
			chunk.append("Haemoglobin : "+object.get("hoplnum_hb_val").toString()+"g/dl ,");
		}
		if(object.getLong("hoplnum_sugarpp_val") > 0.0 ) {
			chunk.append("B.S. PP : "+object.get("hoplnum_sugarpp_val").toString()+"mg/dl ,");
		}
		
		if(object.getLong("hoplnum_hba1c_val") > 0.0 ) {
			chunk.append("HBA1C : "+object.get("hoplnum_hba1c_val").toString()+"mg/dl ,");
		}
		
		if(object.getLong("hoplnum_pulse_rate") > 0.0 ) {
			chunk.append("Pulse Rate : "+object.get("hoplnum_pulse_rate").toString()+"be/m ,");
		}
		if(object.getString("hoplstr_blood_group").equalsIgnoreCase("") ) {
			chunk.append("Blood Group : "+object.get("hoplstr_blood_group").toString()+" ,");
		}
		if(object.getLong("hoplnum_curcumference_val") > 0.0 ) {
			chunk.append("Head Circumference : "+object.get("hoplnum_curcumference_val").toString()+"CM ,");
		}
		if(object.getLong("hoplnum_muac_val") > 0.0 ) {
			chunk.append("MUAC : "+object.get("hoplnum_muac_val").toString()+"CM ,");
		}
		if(object.getString("hoplstr_disability").equalsIgnoreCase("") ) {
			chunk.append("Disability : "+object.get("hoplstr_disability").toString()+" ,");
		}
		if(object.getString("hoplstr_smoking").equalsIgnoreCase("")) {
			chunk.append("Smoking : "+object.get("hoplstr_smoking").toString()+" ,");
		}
		if(object.getString("hoplstr_anemic").equalsIgnoreCase("")) {
			chunk.append("Anemic : "+object.get("hoplstr_anemic").toString()+" ,");
		}
		if(object.getString("hoplstr_pregnancy").equalsIgnoreCase("")) {
			chunk.append("Pregnancy : "+object.get("hoplstr_pregnancy").toString()+" ,");
		}
		
	}
		//chunk.append("\n");
		/*
		 * for(int i=0;i<ja.length();i++) {
		 * 
		 * String ResonVisit=((JSONObject) (ja.get(i))).getString("VisitReasonName") ;
		 * chunk.append( ResonVisit ) ;
		 * 
		 * if(!(((JSONObject)
		 * (ja.get(i))).getString("VisitReasonSideName")).equals("0")) { chunk.append(
		 * "(" +((JSONObject) (ja.get(i))).getString("VisitReasonSideName") +")") ; }
		 * 
		 * 
		 * 
		 * if(!(((JSONObject) (ja.get(i))).getString("VisitReasonNoOfDays")).equals("")
		 * ) { chunk.append( "(" +((JSONObject)
		 * (ja.get(i))).getString("VisitReasonNoOfDays") + "," + ((JSONObject)
		 * (ja.get(i))).getString("VisitComplaintDurationName") +")") ; }
		 * 
		 * if(!(((JSONObject) (ja.get(i))).getString("VisitReasonRemarks")).equals(""))
		 * { chunk.append("" +((JSONObject) (ja.get(i))).getString("VisitReasonRemarks")
		 * +"") ; } chunk.append(","); }
		 */
		
	}

	private static void patientInfo(PdfPTable table, String json) throws JSONException, DocumentException {
		PdfPCell defaultCell = table.getDefaultCell();
		defaultCell.setBorder(PdfPCell.NO_BORDER);
		JSONObject object = new JSONObject(json);
		Font font = f;
		//font.setColor(Color.BLACK);
		font.setFamily("Arial");
	    table.addCell(new Phrase("Name",fontHeading));
	    table.addCell(new Phrase(object.getString("Patient_Name"),font));
	    table.addCell(new Phrase("CR No.",fontHeading));
	    table.addCell(new Phrase(object.getString("CR_No"),font));
	    
	    table.addCell(new Phrase("Age/Gender",fontHeading));
	    table.addCell(new Phrase(object.getString("PatientAge").trim()+"/"+object.getString("PatientGender"),font));
	    table.addCell(new Phrase("Patient Category",fontHeading));
	    table.addCell(new Phrase(object.getString("PatientCat"),font));
	    
	    
	    table.addCell(new Phrase("Father/Spouse Name",fontHeading));
	    table.addCell(new Phrase(object.getString("PatCompleteGeneralDtl").split("#")[3].trim(),font));
	    table.addCell(new Phrase("Department(Unit/Consultant)",fontHeading));
	    table.addCell(new Phrase(object.getString("PatientDept"),font));
	    
	    table.addCell(new Phrase("Visit Date",fontHeading));
	    table.addCell(new Phrase(object.getString("CurrentVisitDate"),font));
	    table.addCell(new Phrase("Unit Head",fontHeading));
	    table.addCell(new Phrase(object.getString("ConsultantName"),font));
	    
	    table.addCell(new Phrase("Occupation",fontHeading));
	    table.addCell((new Phrase("-",font)));
	    table.addCell(new Phrase("Mobile No.",fontHeading));
	    table.addCell(new Phrase(object.getString("PatientQueueNo"),font));
	    
	    
	  
	    //table.DefaultCell.Border = Rectangle.NO_BORDER;
	   // table.set
	    float[] columnWidths = {2f, 2f,2f , 2f};

	    table.setWidths(columnWidths);

		
	}
	
	private static void addTableHeader(PdfPTable table) {
	    Stream.of("column header 1", "column header 2", "column header 3")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	
	private static void addCustomRows(PdfPTable table) 
			  throws URISyntaxException, BadElementException, IOException {
			    Path path = Paths.get("E:\\logo.jpg");
			    Image img = Image.getInstance(path.toAbsolutePath().toString());
			    img.scalePercent(10);
			 
			    PdfPCell imageCell = new PdfPCell(img);
			    table.addCell(imageCell);
			 
			    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
			    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    table.addCell(horizontalAlignCell);
			 
			    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
			    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			    table.addCell(verticalAlignCell);
			}
	

private static void getchunks(Chunk chunk, String json) throws JSONException {
		
	if(json!=null &&  (!json.equalsIgnoreCase("{}"))) {
		JSONObject object = new JSONObject(json);
		
		
		if(object.getLong("hoplnum_weight_val") > 0.0 ) {
			chunk.append("Weight : "+object.getString("hoplnum_weight_val")+"KG.,");
		}
		if(object.getLong("hoplnum_height_val") > 0.0 ) {
			chunk.append("Height : "+object.getString("hoplnum_height_val")+"CM.,");
		}
		if(object.getLong("hoplnum_bmi_val") > 0.0 ) {
			chunk.append("BMI : "+object.getString("hoplnum_bmi_val")+"CM.,");
		}
		if(object.getLong("hoplnum_bpsy_val") > 0.0  && object.getLong("hoplnum_bpdiasy_val") > 0.0 ) {
			chunk.append("BP : "+object.getString("hoplnum_bpsy_val")+"/,"+object.getString("hoplnum_bpdiasy_val")+"mmHg ,");
		}
		if(object.getLong("hoplnum_rr_val") > 0.0 ) {
			chunk.append("Respiration Rate : "+object.getString("hoplnum_rr_val")+"breaths/min ,");
		}
		
		if(object.getLong("hoplnum_hb_val") > 0.0 ) {
			chunk.append("Haemoglobin : "+object.getString("hoplnum_hb_val")+"g/dl ,");
		}
		if(object.getLong("hoplnum_sugarpp_val") > 0.0 ) {
			chunk.append("B.S. PP : "+object.getString("hoplnum_sugarpp_val")+"mg/dl ,");
		}
		
		if(object.getLong("hoplnum_hba1c_val") > 0.0 ) {
			chunk.append("HBA1C : "+object.getString("hoplnum_hba1c_val")+"mg/dl ,");
		}
		
		if(object.getLong("hoplnum_pulse_rate") > 0.0 ) {
			chunk.append("Pulse Rate : "+object.getString("hoplnum_pulse_rate")+"be/m ,");
		}
		if(object.getString("hoplstr_blood_group").equalsIgnoreCase("") ) {
			chunk.append("Blood Group : "+object.getString("hoplstr_blood_group")+" ,");
		}
		if(object.getLong("hoplnum_curcumference_val") > 0.0 ) {
			chunk.append("Head Circumference : "+object.getString("hoplnum_curcumference_val")+"CM ,");
		}
		if(object.getLong("hoplnum_muac_val") > 0.0 ) {
			chunk.append("MUAC : "+object.getString("hoplnum_muac_val")+"CM ,");
		}
		if(object.getString("hoplstr_disability").equalsIgnoreCase("") ) {
			chunk.append("Disability : "+object.getString("hoplstr_disability")+" ,");
		}
		if(object.getString("hoplstr_smoking").equalsIgnoreCase("")) {
			chunk.append("Smoking : "+object.getString("hoplstr_smoking")+" ,");
		}
		if(object.getString("hoplstr_anemic").equalsIgnoreCase("")) {
			chunk.append("Anemic : "+object.getString("hoplstr_anemic")+" ,");
		}
		if(object.getString("hoplstr_pregnancy").equalsIgnoreCase("")) {
			chunk.append("Pregnancy : "+object.getString("hoplstr_pregnancy")+" ,");
		}
		
	}
		//chunk.append("\n");
		/*
		 * for(int i=0;i<ja.length();i++) {
		 * 
		 * String ResonVisit=((JSONObject) (ja.get(i))).getString("VisitReasonName") ;
		 * chunk.append( ResonVisit ) ;
		 * 
		 * if(!(((JSONObject)
		 * (ja.get(i))).getString("VisitReasonSideName")).equals("0")) { chunk.append(
		 * "(" +((JSONObject) (ja.get(i))).getString("VisitReasonSideName") +")") ; }
		 * 
		 * 
		 * 
		 * if(!(((JSONObject) (ja.get(i))).getString("VisitReasonNoOfDays")).equals("")
		 * ) { chunk.append( "(" +((JSONObject)
		 * (ja.get(i))).getString("VisitReasonNoOfDays") + "," + ((JSONObject)
		 * (ja.get(i))).getString("VisitComplaintDurationName") +")") ; }
		 * 
		 * if(!(((JSONObject) (ja.get(i))).getString("VisitReasonRemarks")).equals(""))
		 * { chunk.append("" +((JSONObject) (ja.get(i))).getString("VisitReasonRemarks")
		 * +"") ; } chunk.append(","); }
		 */
		
	}
	public static void main(String ar[]) throws Exception {
		String jsonData ="{'Patient_Name':'Dekho','CR_No':'379132000005962','EpisodeCode':'21611001','EpisodeVisitNo':'1','CurrentVisitDate':'27/10/2020','PatVisitType':'OPD General','LastVisitDate':'First Visit','PatientGender':'M','PatientAge':' 22 Yr','PatientCat':'GEN','PatientQueueNo':'9797978978','hosp_code':'37913','hrgnum_is_docuploaded':0,'ConsultantName':'Alok','PatientDept':'C F M/C F M Unit 1','patGaurdianName':'Qwertyui','PatCompleteGeneralDtl':'--#C F M ( C F M Unit 1) #202#Qwertyui#1#21611#216#27-Oct-2020 #C F M#C F M Unit 1#11#1#2020/10/27#2020/10/27#9797978978#0#0#-#0#0#^^^null','seatId':'12175','HistoryOfPresentIllNess':' History Of Present Illness','DiagnosisNote':' Diagnosis Note','InvestgationNote':' Investigation Note','OtherAllergies':'','ReasonOfVisitJsonArray':[{'IsExternalVisit':'1','VisitReasonName':'Fever seizure','VisitReasonCode':'41497008','VisitReasonSideCode':'1','VisitReasonSideName':'NR','VisitReasonNoOfDays':'12','VisitComplaintDurationCode':'2','VisitComplaintDurationName':'Week/s','VisitReasonRemarks':'remsks'},{'IsExternalVisit':'1','VisitReasonName':'Fever of unknown origin','VisitReasonCode':'7520000','VisitReasonSideCode':'3','VisitReasonSideName':'Right','VisitReasonNoOfDays':'16','VisitComplaintDurationCode':'1','VisitComplaintDurationName':'Day/s','VisitReasonRemarks':'remkds'}],'DiagnosisJsonArray':[{'IsSnomed':'1','DiagnosisName':'Catu fever','DiagnosisCode':'89805002','DiagnosisSideCode':'2','DiagnosisSideName':'Left','DiagnosisTypeCode':'12','DiagnosisTypeNamee':'Differential','DiagnosisRemarks':'remks'},{'IsSnomed':'2','DiagnosisName':'Thoracic, thoracolumbar and lumbosacral disc disorders','DiagnosisCode':'M51   ','DiagnosisSideCode':'0','DiagnosisSideName':'Side','DiagnosisTypeCode':'14','DiagnosisTypeNamee':'Final'}],'InvestigationJsonArray':[{'IsExternal':'0','TestName':'Anti MUSK body titre (AMBT) ','TestCode':'13567','LabCode':'10087','SampleCode':'1047','SampleName':'FLUID','LabName':'Others-General Medicine','IsTestGroup':'0','SideCode':'1','SideName':'NR','SideRemarks':'rewv'},{'IsExternal':'0','TestName':'CBC (CPL-CBC) ','TestCode':'13631','LabCode':'10001','SampleCode':'1001','SampleName':'BLD','LabName':'Clinical Pathology Lab-General Medicine','IsTestGroup':'0','SideCode':'3','SideName':'Right','SideRemarks':'reas'}],'CompleteHistoryJaonArray':{'strpastHistory':' Past History','strpersonalHistory':'Personal History','strfamilyHistory':'Family History','strtreatmentHistory':'Treatment History','strsurgicalHistory':'Surgical History'},'SystematicExamniationArray':{'strcvs':' CVS','strrs':'RS','strcns':'CNS','strpA':'PA','strotherExamn':'General Examination','strmuscularExamn':'Muscular Examination','strLocalExamn':'Local Examination'},'ChronicDiseaseArray':[{'CronicDiseaseName':'Fever;','CronicDiseaseId':'386661006','CronicDiseaseDuration':'22','CronicDiseaseRemarks':'remaks'}],'PiccleArray':{'strpallor':'1','stricterus':'1','strcyanosis':'1','strclubbing':'1','striymphadenopathyId':'1','stredema':'1'},'ClinicalProcedureJsonArray':[{'IsExternal':'1','ProceduresName':'procedure','ProcedureCode':'0^0','ProcedureSideCode':'1','ProcedureSideName':'NR','ProcedureSideRemarks':'remarks'}],'DrugJsonArray':[{'IsExterNal':'0','DrugName':'Atecard 100 mg oral tablet Dabur India Limited','DrugCode':'10010354#101016##N#10128425','DoaseName':'--','DoaseCode':'0','FrequencyName':'TDS(1-1-1)','FrequencyCode':'13','StartDate':'2020-10-27','DrugDays':'12','DrugQuantity':'1','DrugInstruction':'Before Breakfast'}],'PatientRefrel':[{'strReffralDeptCmb':'191','strReffralDepttext':'E N T','strreferralType':'2','strreferralTypeName':' Emergency','strReffralReason':'remsks','strReffralDeptDone':'191#19111#1012#0#0##-1#'}],'strpiccle':{'strpallor':'1','stricterus':'1','strcyanosis':'1','strclubbing':'1','striymphadenopathyId':'1','stredema':'1'},'strtreatmentAdvice':' Treatment Advice','strConfidentialsInfo':'confidential info','strVitalsChart':'','FOLLOW_UP':[{'endTreatment':'0','Planned_Visit':[{'plannedVisitSos':'','plannedVisitDays':'11','plannedVisitDate':''}],'progressNote':'\\n\\nClinical Notes\\n'}],'strDeptIdflg':'0','strAllDeptIdflg':'1','strPresCriptionBookmarkNameval':'','strPresCriptionBookmarkDescVal':''}";
	//	PDFCreator.createPdf(jsonData);
	}

	private static void getchunksdtl(Chunk chunk, String json) throws JSONException {
		
		JSONObject object = new JSONObject(json);
		JSONObject js = new JSONObject(object.get("PiccleArray").toString());
	
		if(js.getString("strpallor").equalsIgnoreCase("1")) {
			chunk.append("Pallor     ")	;
		}
		if(js.getString("stricterus").equalsIgnoreCase("1")) {
			chunk.append("Icterus     ")	;
		}
		if(js.getString("strcyanosis").equalsIgnoreCase("1")) {
			chunk.append("Cyanosis     ")	;
		}
		if(js.getString("strclubbing").equalsIgnoreCase("1")) {
			chunk.append("Clubbing     ")	;
		}
		if(js.getString("striymphadenopathyId").equalsIgnoreCase("1")) {
			chunk.append("Lymphadenopathy     ")	;
		}
		if(js.getString("stredema").equalsIgnoreCase("1")) {
			chunk.append("Edema     ")	;
		}
		
		
		JSONObject js1= new JSONObject(object.get("SystematicExamniationArray").toString());
		
		if(js1.getString("strcvs").length() > 0) {
			chunk.append("\n CVS:"+js1.getString("strcvs"))	;
		}
		if(js1.getString("strrs").length() > 0) {
			chunk.append("\n RS:"+js1.getString("strrs"))	;
		}
		if(js1.getString("strcns").length() > 0) {
			chunk.append("\n CNS:"+js1.getString("strcns"))	;
		}
		if(js1.getString("strpA").length() > 0) {
			chunk.append("\n P/A:"+js1.getString("strpA"))	;
		}
		if(js1.getString("strotherExamn").length() > 0) {
			chunk.append(new Chunk( "\n General Examination:")  +js1.getString("strotherExamn"))	;
		}
		if(js1.getString("strmuscularExamn").length() > 0) {
			chunk.append("\n Muscular Examination:"+js1.getString("strmuscularExamn"))	;
		}
		if(js1.getString("strLocalExamn").length() > 0) {
			chunk.append("\n Local Examination:"+js1.getString("strLocalExamn"))	;
		}
		
		
	}
	

private static void getch(List list, String json) throws JSONException {
	
	JSONObject object = new JSONObject(json);
	JSONArray ja = new JSONArray(object.get("DrugJsonArray").toString());
	//chunk.append("\n");
	
	for(int i=0;i<ja.length();i++) {
		Chunk ch=new Chunk();
//		/String drug=((JSONObject) (ja.get(i))).getString("DrugName") +" ,";
		ch.append(((JSONObject) (ja.get(i))).getString("DrugName")+",");
		if(!(((JSONObject) (ja.get(i))).getString("DoaseName")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("DoaseName")+",");
		}
		if(!(((JSONObject) (ja.get(i))).getString("FrequencyName")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("FrequencyName")+",");
		}
		if(!(((JSONObject) (ja.get(i))).getString("DrugDays")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("DrugDays")+" Days,");
		}
		
		if(!(((JSONObject) (ja.get(i))).getString("StartDate")).equals("")) {
			ch.append(((JSONObject) (ja.get(i))).getString("StartDate")+",");
		}
		
		if(!(((JSONObject) (ja.get(i))).getString("DrugInstruction")).equals("")) {
			ch.append("("+((JSONObject) (ja.get(i))).getString("DrugInstruction")+")");
		}
		
		list.add(new ListItem(ch +" ",f))	;
		
	}
	
}

public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
    Image img = Image.getInstance(path);
    img.scaleAbsolute(50f, 50f);
	 
    PdfPCell cell = new PdfPCell(img, true);
    
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
}
public static PdfPCell createTextCell(String text) throws DocumentException, IOException {
    PdfPCell cell = new PdfPCell();
    
    Paragraph p = new Paragraph(text);
    p.setAlignment(Element.ALIGN_CENTER);
    cell.addElement(p);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
}

}
