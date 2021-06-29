/**
 * 
 */
package registration.dao;


import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationDaoConfig;
import vo.registration.PoliceStationMstVO;

/**
 * @author s.singaravelan
 *
 */
public class PoliceStationDAO
{

	//To Save the Police Station in the Police Station Mst
	public static String savePoliceStationDetails(PoliceStationMstVO objModelPoliceStation,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_POLICE_STATION_DML;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelPoliceStation);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_stationcode",objModelPoliceStation.getStrPSCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_stationshortname",objModelPoliceStation.getStrPSShortName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_stationfullname",objModelPoliceStation.getStrPSFullName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_addressline1",objModelPoliceStation.getStrAddressLine1(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_sublocality1",objModelPoliceStation.getStrSubLocality1(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_sublocality2",objModelPoliceStation.getStrSubLocality2(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_city",objModelPoliceStation.getStrCity(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_cityloc",objModelPoliceStation.getStrCityLoc(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_district",objModelPoliceStation.getStrDistrict(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_districtcode",objModelPoliceStation.getStrDistrictCode(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_statecode",objModelPoliceStation.getStrStateCode(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_countrycode",(objModelPoliceStation.getStrCountryCode().equals("")||objModelPoliceStation.getStrCountryCode().equals(null))?"IND":objModelPoliceStation.getStrCountryCode(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_pincode",objModelPoliceStation.getStrPinCode(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_phoneno",objModelPoliceStation.getStrPhoneNo(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_email",objModelPoliceStation.getStrEmailId(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_inchargename",objModelPoliceStation.getStrPSInchargeName(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_inchargedesig",objModelPoliceStation.getStrPSConstableDesignation(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_inchargebadge",objModelPoliceStation.getStrPSConstableBadgeNo(),19);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mobileno",objModelPoliceStation.getStrMobileNo(),20);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ipaddress",(objModelPoliceStation.getStrIpAddress().equals("")||objModelPoliceStation.getStrIpAddress().equals(null))?"127.0.0.1":objModelPoliceStation.getStrIpAddress(),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isdefault",objModelPoliceStation.getStrIsDefault(),22);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,23);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),24);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),25);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,26); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Inserted Successussfully");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}			
		}

		catch (Exception e)
		{
			e.printStackTrace();
			objModelPoliceStation.setStrMsgString("PoliceStationDAO.savePoliceStationDetails() --> "	+ e.getMessage());
			objModelPoliceStation.setStrMsgType("1");
		}
		return strReuestNo;
	}


	//To get PoliceStation Details in Modify
	public static PoliceStationMstVO modifyDetails(PoliceStationMstVO objModelPoliceStation,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_POLICE_STATION_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelPoliceStation);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_stationcode",objModelPoliceStation.getStrPSCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_stationfullname",objModelPoliceStation.getStrPSFullName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelPoliceStation.getStrHospitalCode(),5);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7);

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objModelPoliceStation.setStrMsgString("PoliceStationDAO.modifyDetails() --> "	+ e.getMessage());
			objModelPoliceStation.setStrMsgType("1");
		}


		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelPoliceStation.setStrPSCode(webRowSet.getString(1));
			objModelPoliceStation.setStrPSFullName(webRowSet.getString(3));
			objModelPoliceStation.setStrPSShortName(webRowSet.getString(2));
			objModelPoliceStation.setStrAddressLine1(webRowSet.getString(4));
			objModelPoliceStation.setStrSubLocality1(webRowSet.getString(5));	
			objModelPoliceStation.setStrSubLocality2(webRowSet.getString(6));				
			objModelPoliceStation.setStrCity(webRowSet.getString(7));				
			objModelPoliceStation.setStrDistrict(webRowSet.getString(9));				
			objModelPoliceStation.setStrDistrictCode(webRowSet.getString(10));				
			objModelPoliceStation.setStrStateCode(webRowSet.getString(11));				
			objModelPoliceStation.setStrPinCode(webRowSet.getString(13));				
			objModelPoliceStation.setStrPhoneNo(webRowSet.getString(14));				
			objModelPoliceStation.setStrEmailId(webRowSet.getString(15));				
			objModelPoliceStation.setStrPSInchargeName(webRowSet.getString(16));				
			objModelPoliceStation.setStrPSConstableDesignation(webRowSet.getString(17));				
			objModelPoliceStation.setStrPSConstableBadgeNo(webRowSet.getString(18));		
			objModelPoliceStation.setStrMobileNo(webRowSet.getString(19));				
			objModelPoliceStation.setStrIsDefault(webRowSet.getString(21));			

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PoliceStationDAO.modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelPoliceStation;

	}

	//To Update PoliceStation Details
	public static void updatePoliceStationDetails(PoliceStationMstVO objModelPoliceStation,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_POLICE_STATION_DML;
		final int nProcedureIndex;
		final String strDbErr;



		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelPoliceStation);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_stationcode",objModelPoliceStation.getStrPSCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_stationshortname",objModelPoliceStation.getStrPSShortName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_stationfullname",objModelPoliceStation.getStrPSFullName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_addressline1",objModelPoliceStation.getStrAddressLine1(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_sublocality1",objModelPoliceStation.getStrSubLocality1(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_sublocality2",objModelPoliceStation.getStrSubLocality2(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_city",objModelPoliceStation.getStrCity(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_cityloc",objModelPoliceStation.getStrCityLoc(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_district",objModelPoliceStation.getStrDistrict(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_districtcode",objModelPoliceStation.getStrDistrictCode(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_statecode",objModelPoliceStation.getStrStateCode(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_countrycode",(objModelPoliceStation.getStrCountryCode().equals("")||objModelPoliceStation.getStrCountryCode().equals(null))?"IND":objModelPoliceStation.getStrCountryCode(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_pincode",objModelPoliceStation.getStrPinCode(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_phoneno",objModelPoliceStation.getStrPhoneNo(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_email",objModelPoliceStation.getStrEmailId(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_inchargename",objModelPoliceStation.getStrPSInchargeName(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_inchargedesig",objModelPoliceStation.getStrPSConstableDesignation(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_inchargebadge",objModelPoliceStation.getStrPSConstableBadgeNo(),19);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_mobileno",objModelPoliceStation.getStrMobileNo(),20);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_ipaddress",(objModelPoliceStation.getStrIpAddress().equals("")||objModelPoliceStation.getStrIpAddress().equals(null))?"127.0.0.1":objModelPoliceStation.getStrIpAddress(),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isdefault",objModelPoliceStation.getStrIsDefault(),22);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,23);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),24);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),25);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,26); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");			

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException(e.getMessage());

		}

	}

	public static boolean chkPoliceStationDuplicate(PoliceStationMstVO objModelPoliceStation,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_POLICE_STATION_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelPoliceStation);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_stationcode",objModelPoliceStation.getStrPSCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_stationfullname",objModelPoliceStation.getStrPSFullName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),5);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}catch (Exception e) 
		{
			e.printStackTrace();
			objModelPoliceStation.setStrMsgString("PoliceStationDAO.chkPoliceStationDuplicate() --> " + e.getMessage());
			objModelPoliceStation.setStrMsgType("1");

		} 

		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			ncount=Integer.parseInt(webRowSet.getString(1));
			System.out.println("------"+ncount+"-----");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("PoliceStationDAO():HelperMethodsDAO.chkPoliceStationDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) 
		{
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2"))
		{
			if(objModelPoliceStation.getStrOldPSFullName().equalsIgnoreCase(objModelPoliceStation.getStrPSFullName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelPoliceStation.getStrOldPSFullName().equalsIgnoreCase(objModelPoliceStation.getStrPSFullName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else 
		{
			bExistStatus=false;
		}
		return bExistStatus;
	}	
	
	
}
