package HisWeb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import HisWeb.dao.opdDrDeskDao;

public class opdDrDeskUtil {

	public static void SaveDrDesk(String JsonData) throws JSONException, ParseException
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		 JSONObject object = new JSONObject(JsonData);
		 Date date1=sdf.parse((String)object.getString("PatCompleteGeneralDtl").split("#")[12]);
		 Date date2=sdf.parse((String)object.getString("PatCompleteGeneralDtl").split("#")[13]);
		 
		 //System.out.println("JsonData"+JsonData);
		 //System.out.println("date1::::::"+date1);
		// System.out.println("date2::::::"+date2);
		 // if(sdf.format(date1).compareTo(sdf.format(date2)) == 0)
		  //{
			  opdDrDeskDao.SaveDrugAdviceData(JsonData);
			  opdDrDeskDao.SaveInvData(JsonData);
		  //}
		  String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
 			
		opdDrDeskDao.SaveGenralData(JsonData);
		opdDrDeskDao.SaveVisitReasonData(JsonData);
		opdDrDeskDao.FollowUpDTL(JsonData);
		opdDrDeskDao.SaveEHRData(JsonData);
		
		opdDrDeskDao.saveProcdureData(JsonData);
		//System.out.println("PatCompleteGeneralDtl.split('0')[18]"+PatCompleteGeneralDtl.split("#")[18]);
		if(PatCompleteGeneralDtl.split("#")[18].equals("1")){
		opdDrDeskDao.SaveEConsultancyData(JsonData);
		}
		//System.out.println(":::::::::::::::"+((JSONArray) object.get("strReferal")).length());
		if(((JSONArray) object.get("strReferal")).length() > 0){
			opdDrDeskDao.saveReferPatientDetails(JsonData);
		}
		
		//System.out.println(":::::::::::::::"+((JSONArray) object.get("strReferal")).length());
		if(((JSONArray) object.get("strDrugAllergy")).length() > 0){
			opdDrDeskDao.SaveAllergyData(JsonData);
		}
		
		if(((JSONArray) object.get("strChronicDisease")).length() > 0){
			opdDrDeskDao.SaveChronicData(JsonData);
		}
		
		opdDrDeskDao.SaveHistoryOfPresentIllNess(JsonData);
		
		opdDrDeskDao.SaveCompleteHistoryData(JsonData);
		
		opdDrDeskDao.SaveExamniationData(JsonData);

		
		
	}
	
	public static void referPatient(String JsonData) throws JSONException, ParseException
	{
		//opdDrDeskDao.saveReferPatientDetails(JsonData);
	}
	
	public static String saveVitalData(String JsonData) throws JSONException, ParseException
	{
		
		String RetValue=opdDrDeskDao.SaveVitalData(JsonData);
		String RetValue1=opdDrDeskDao.SaveEMRVitalData(JsonData);
		return RetValue;
	}
	
	public static String savePrescriptionProfileData(String JsonData) throws JSONException, ParseException
	{
		
		String RetValue=opdDrDeskDao.savePrecriptionProfileData(JsonData);
		//String RetValue1=opdDrDeskDao.SaveEMRVitalData(JsonData);
		return RetValue;
	}
	
	public static String getModifyVitalData(String JsonData) throws JSONException, ParseException
	{
		
		String RetValue=opdDrDeskDao.getModifyVitalData(JsonData);
		return RetValue;
	}


	public static void SaveDrDeskFormattedData(String jsonData) throws JSONException {
		 JSONObject object = new JSONObject(jsonData);
		 JSONObject js = new JSONObject(jsonData);
		 String deptflg=  object.getString("strDeptIdflg");
		 String AllDeptFgl=  object.getString("strAllDeptIdflg");
		 String strPresCriptionBookmarkNameval=  object.getString("strPresCriptionBookmarkNameval");
		 
		 js.remove("Patient_Name");
		 js.remove("CR_No");
		 js.remove("EpisodeCode");
		 js.remove("EpisodeVisitNo");
		 js.remove("CurrentVisitDate");
		 js.remove("PatVisitType");
		 js.remove("LastVisitDate");
		 //js.remove("PatientGender");
		// js.remove("PatientAge");
		 
		 js.remove("PatientCat");
		 js.remove("PatientQueueNo");
		 js.remove("hrgnum_is_docuploaded");
		 js.remove("patGaurdianName");		 
		 
		 
		 /*System.out.println("After Remove All Data");*/
		 //System.out.println(js.toString());
		 
		 
		opdDrDeskDao.SaveGenralDataFormattedData(jsonData);
		if(deptflg.equals("1"))
		opdDrDeskDao.SavePrescriptionBookMarkData(js.toString());
		
	}
	public static void SaveDrDeskFormattedData1(String jsonData) throws JSONException {
		 JSONObject object = new JSONObject(jsonData);
		 JSONObject js = new JSONObject(jsonData);
		 String deptflg=  object.getString("strDeptIdflg");
		 String AllDeptFgl=  object.getString("strAllDeptIdflg");
		 String strPresCriptionBookmarkNameval=  object.getString("strPresCriptionBookmarkNameval");
		 
		 js.remove("Patient_Name");
		 js.remove("CR_No");
		 js.remove("EpisodeCode");
		 js.remove("EpisodeVisitNo");
		 js.remove("CurrentVisitDate");
		 js.remove("PatVisitType");
		 js.remove("LastVisitDate");
		 //js.remove("PatientGender");
		// js.remove("PatientAge");
		 
		 js.remove("PatientCat");
		 js.remove("PatientQueueNo");
		 js.remove("hrgnum_is_docuploaded");
		 js.remove("patGaurdianName");		 
		 
		 
		 /*System.out.println("After Remove All Data");*/
		 //System.out.println(js.toString());
		 
		 
		opdDrDeskDao.SavePrescriptionBookMarkData(js.toString());
		
	}
}
