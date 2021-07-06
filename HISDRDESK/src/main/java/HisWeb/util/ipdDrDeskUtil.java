package HisWeb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import HisWeb.dao.ipdDrDeskDao;

public class ipdDrDeskUtil {

	public static void SaveDrDesk(String JsonData) throws JSONException, ParseException
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		 JSONObject object = new JSONObject(JsonData);
		 Date date1=sdf.parse((String)object.getString("PatCompleteGeneralDtl").split("#")[12]);
		 Date date2=sdf.parse((String)object.getString("PatCompleteGeneralDtl").split("#")[13]);
		 
		 System.out.println("date1::::::"+date1);
		 System.out.println("date2::::::"+date2);
		 // if(sdf.format(date1).compareTo(sdf.format(date2)) == 0)
		  //{
			  ipdDrDeskDao.SaveDrugAdviceData(JsonData);
			  ipdDrDeskDao.SaveInvData(JsonData);
		  //}
		  String PatCompleteGeneralDtl=(String) object.get("PatCompleteGeneralDtl");
 			
		ipdDrDeskDao.SaveGenralData(JsonData);
		ipdDrDeskDao.SaveVisitReasonData(JsonData);
		ipdDrDeskDao.FollowUpDTL(JsonData);
		ipdDrDeskDao.SaveEHRData(JsonData);
		//System.out.println("PatCompleteGeneralDtl.split('0')[18]"+PatCompleteGeneralDtl.split("#")[18]);
		if(PatCompleteGeneralDtl.split("#")[18].equals("1")){
		ipdDrDeskDao.SaveEConsultancyData(JsonData);
		}
		System.out.println(":::::::::::::::"+((JSONArray) object.get("strReferal")).length());
		if(((JSONArray) object.get("strReferal")).length() > 0){
			ipdDrDeskDao.saveReferPatientDetails(JsonData);
		}
		
	}
	
	
	
	public static void referPatient(String JsonData) throws JSONException, ParseException
	{
		//ipdDrDeskDao.saveReferPatientDetails(JsonData);
	}
	
	public static String saveVitalData(String JsonData) throws JSONException, ParseException
	{
		
		String RetValue=ipdDrDeskDao.SaveVitalData(JsonData);
		String RetValue1=ipdDrDeskDao.SaveEMRVitalData(JsonData);
		return RetValue;
	}
	
	public static String savePrescriptionProfileData(String JsonData) throws JSONException, ParseException
	{
		
		String RetValue=ipdDrDeskDao.savePrecriptionProfileData(JsonData);
		//String RetValue1=ipdDrDeskDao.SaveEMRVitalData(JsonData);
		return RetValue;
	}
	
	public static String getModifyVitalData(String JsonData) throws JSONException, ParseException
	{
		
		String RetValue=ipdDrDeskDao.getModifyVitalData(JsonData);
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
		 System.out.println(js.toString());
		 
		 
		ipdDrDeskDao.SaveGenralDataFormattedData(jsonData);
		if(deptflg.equals("1"))
		ipdDrDeskDao.SavePrescriptionBookMarkData(js.toString());
		
	}
}
