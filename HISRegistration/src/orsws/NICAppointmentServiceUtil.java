package orsws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//import PGIMERPortal.Connection.Connect;

public class NICAppointmentServiceUtil 
{
	public void insertIntoPreviousRegistration(String strData)
	{

		try
		{
			String sucess_status = "1";
			JSONParser objJsonParser = new JSONParser();
			JSONObject objJson = (JSONObject) objJsonParser.parse(strData);
			JSONObject obj= (JSONObject)objJson.get("details");
			String status = (String)obj.get("status");
			String status_code = obj.get("status_code").toString();
			String firstname="";
			String middlename="";
			String lastname="";
			if(status_code.equalsIgnoreCase(sucess_status))
			{
				JSONArray objArrData= (JSONArray) obj.get("data");
				Iterator i = objArrData.iterator();
				//Connection con=null;
				//Connect ob= new Connect(); 
				//PreparedStatement stmt = null;
				//con=ob.con();
				// take each value from the json array separately
				while (i.hasNext()) 
				{
					JSONObject innerObj = (JSONObject) i.next();
					String ors_ref_no = (String)innerObj.get("ors_ref_no");
					String dept_name = (String)innerObj.get("dept_name");
					//Street^location^City^State^Zip^policeStation^Country
					String address = (String)innerObj.get("address");
					String[] arrAddName = address.split("\\^");
					String street="";
					String location="";
					String city="";
					String state="";
					String zip="";
					String policeStation="";
					String country="";
					if(arrAddName != null)
					{
						street = arrAddName[0];
						location = arrAddName[1];
						city = arrAddName[2];
						state =  arrAddName[3];
						zip =  arrAddName[4];
						policeStation =  arrAddName[5];
						country = arrAddName[6];
					}
					if("91".equalsIgnoreCase(country))
					{
						country="";
						country="IND";
					}

					//Mr^firstname^middlename^lastname
					String name = (String)innerObj.get("name");

					//Splitname in first and lastname
					String[] arrName = name.split("\\^");
					if(arrName != null)
					{
						if(arrName.length>=2 && arrName[1]!=null && arrName[1].length()>0)
						firstname = arrName[1];
						if(arrName.length>=3 && arrName[2]!=null && arrName[2].length()>0)
						middlename = arrName[2];
						if(arrName.length>=4 && arrName[3]!=null && arrName[3].length()>0)
						lastname = arrName[3];


					}
					String dob = (String)innerObj.get("dob");
					java.sql.Date sqlDOB = null;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					if(dob != null)
					{
						java.util.Date dateofBirth = sdf.parse(dob);
						sqlDOB= new java.sql.Date(dateofBirth.getTime());
					}


					String gender = (String)innerObj.get("gender");
					String app_id = (String)innerObj.get("app_id");


					String app_date = (String)innerObj.get("app_date");
					java.sql.Date sqlappDate = null;
					if(app_date != null)
					{
						java.util.Date dateofApp = sdf.parse(app_date);
						sqlappDate= new java.sql.Date(dateofApp.getTime());
					}

					String mobile = (String)innerObj.get("mobile");
					
					System.out.println("ors_ref_no  "+ors_ref_no+" dept_name  "+dept_name+" street..."+street+" location...."+location+" city...."+city+" state...."+state+" zip..."+zip+" policeStation...."+policeStation+" country...."+country);
                     
					System.out.println(" firstname.."+firstname+" middlename...."+middlename+" lastname..."+lastname+" dob..."+dob+" sqlDOB.."+sqlDOB+" gender..."+gender+" app_id..."+app_id+" mobile..."+mobile);
					String iDepartmentCode = getDepartmentCodeFromName(dept_name);

					String strInsertPatientDtl = "INSERT INTO HRGT_PREV_PATIENT_DTL@HIS_LINK( HRGSTR_PREV_PUK,HRGDT_DOB,HRGBL_IS_ACTUAL_DOB,"
							+ " HRGSTR_FNAME, HRGSTR_MNAME, HRGSTR_LNAME,"
							+ " GNUM_GENDER_CODE, GNUM_DEPT_CODE,GNUM_ISVALID, "
							+ "HRGSTR_CONTACT_NO,  GNUM_NATIONALITY_CODE,   HRGDT_VISTING_DATE, "
							+ "HRGSTR_SEC_UHID, HRGNUM_APPOINTMENT_FROM, HRGNUM_NIC_REF_NUMBER)"+ 
							"VALUES ( ?, ?,? ,?,?,?,?,?,?,?,?,?,?,?,?)";
					/*String secondaryUHID=PreRegistrationUTIL.generateSecondaryHealthID(firstname, lastname, gender, mobile);
					System.out.println("patDetailQuery: "+ strInsertPatientDtl);
					stmt=con.prepareStatement(strInsertPatientDtl);
					stmt.setString(1, app_id);
					stmt.setDate(2, sqlDOB);
					stmt.setString(3, "1");
					stmt.setString(4,firstname);
					stmt.setString(5,middlename);
					stmt.setString(6,lastname);
					stmt.setString(7,gender);
					stmt.setString(8,iDepartmentCode);
					stmt.setString(9,"1");
					stmt.setString(10,mobile);
					stmt.setString(11,"101");
					stmt.setDate(12, sqlappDate);
					stmt.setString(13, secondaryUHID);
					stmt.setString(14, "2");
					stmt.setString(15, ors_ref_no);
					stmt.executeUpdate();

					
					String hmisStatecode= getStateCode(stateCodeMapper(),state);
					String addDetailQuery=null;
					addDetailQuery="INSERT INTO HRGT_PREV_PATIENT_ADD_DTL@HIS_LINK(HRGSTR_PREV_PUK,GNUM_ADD_TYPE_CODE,"
							+ "HRGSTR_CITY,GNUM_STATE_CODE,"
							+ "HRGNUM_PINCODE,GNUM_COUNTRY_CODE,GNUM_ISVALID,"
							+ "HRGSTR_STREET_NO,"
							+ "HRGNUM_IS_URBAN,HRGSTR_CONTACT_NO,"
							+ "HRGSTR_CITY_LOCATION) " +
							" VALUES(?,?,?,?,?,?,?,?,?,?,?)";
					stmt=con.prepareStatement(addDetailQuery);
					System.out.println("addDetailQuery: "+ addDetailQuery); 
					stmt.setString(1, app_id);
					stmt.setString(2, "1");
					stmt.setString(3,city);
					stmt.setString(4, hmisStatecode);
					stmt.setString(5, zip);
					stmt.setString(6,country);
					stmt.setString(7, "1");
					stmt.setString(8, street);
					stmt.setString(9, "0");
					stmt.setString(10, mobile);
					stmt.setString(11,location);
					stmt.executeUpdate();*/
				}
				System.out.println(obj.get("totalRecord"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private String getDepartmentCodeFromName(String dept_name) 
	{
		if("Medicine".equalsIgnoreCase(dept_name))
			return "146";
		if ("Orthopaedics".equalsIgnoreCase(dept_name))
			return "121";
		if("Surgical Disciplines".equalsIgnoreCase(dept_name))
			return "148";
		return "146";
	}

	/*

	  GNUM_STATECODE	State code nic	GSTR_STATENAME
		63	23	Madhya Pradesh
		64	27	Maharashtra
		65	14	Manipur
		66	17	Meghalaya
		67	15	Mizoram
		68	13	Nagaland
		69	21	Orissa
		70	34	Pondicherry
		71	3	Punjab
		72	8	Rajasthan
		73	11	Sikkim
		74	33	Tamil Nadu
		75	16	Tripura
		76	5	Uttarakhand
		47	9	Uttar Pradesh
		48	19	West Bengal
		52	22	Chhattisgarh
		53	26	Dadra And Nagar Haveli
		54	25	Daman And Diu
		55	7	Delhi
		56	30	Goa
		58	1	Jammu And Kashmir
		59	20	Jharkhand
		60	29	Karnataka
		61	32	Kerala
		62	31	Lakshadweep
		46	28	Andhra Pradesh
		12	18	Assam
		13	12	Arunachal Pradesh
		14	24	Gujrat
		15	10	Bihar
		16	6	Haryana
		17	2	Himachal Pradesh
		11	4	Chandigarh
		42	35	Andaman And Nicobar
	 */

	private HashMap<String,String> stateCodeMapper()
	{
		HashMap<String,String> str = new HashMap<String,String>();
		str.put("23","63");
		str.put("27","64");
		str.put("14","65");
		str.put("17","66");
		str.put("15","67");
		str.put("13","68");
		str.put("21","69");
		str.put("34","70");
		str.put("3","71");
		str.put("8","72");
		str.put("11","73");
		str.put("33","74");
		str.put("16","75");
		str.put("5","76");
		str.put("9","47");
		str.put("19","48");
		str.put("22","52");
		str.put("26","53");
		str.put("25","54");
		str.put("7","55");
		str.put("30","56");
		str.put("1","58");
		str.put("20","59");
		str.put("29","60");
		str.put("32","61");
		str.put("31","62");
		str.put("28","46");
		str.put("18","12");
		str.put("12","13");
		str.put("24","14");
		str.put("10","15");
		str.put("6","16");
		str.put("2","17");
		str.put("4","11");
		str.put("35","42");
		return str;
	}

	private String  getStateCode(HashMap<String,String> str, String nicStateCode)
	{
		return str.get(nicStateCode);
	}
}
