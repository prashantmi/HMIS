package registration.dao;

import freemarker.ext.beans.HashAdapter;
import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.DataAccessObject;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.qryHandler_master;
import registration.config.RegistrationDaoConfig;
import vo.registration.DepartmentVO;
import vo.registration.ApptTypeVO;
import vo.registration.VerificationDocVO;

public class ApptTypeDAO {


//To Save the Appointment Type in the Appointment Type Mst
		public static String saveApptTypeDetails(ApptTypeVO objApptTypeModel,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
		{
			final String strProcName = RegistrationDaoConfig.PROCEDURE_APPT_TYPE_DML;
			final int nProcedureIndex;
			final String strDbErr;
			String strReuestNo="";
			//
			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);
				HisUtil.replaceNullValueWithEmptyString(objApptTypeModel);			

				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_id",objApptTypeModel.getStrApptTypeId(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_name",objApptTypeModel.getStrApptTypeName(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_isdefault",objApptTypeModel.getStrApptIsDefault(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hapstr_color",objApptTypeModel.getStrApptTypeColor(),5);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,6);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hapdt_entry_date",objApptTypeModel.getStrEntryDate(),7);
				
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,8); 

				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				 //Getting out parameters 
				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				System.out.println("Data Inserted Succesfully");

				// If Database Error Occurs, No farther processing is required. 
				if (strDbErr != null && !strDbErr.equals("")) {
					throw new Exception("Data Base Error:" + strDbErr);
				}

			}

			catch (Exception e)
			{
				e.printStackTrace();
				objApptTypeModel.setStrMsgString("ApptTypeDAO.SaveRoom() --> "	+ e.getMessage());
				objApptTypeModel.setStrMsgType("1");
				//throw new HisDataAccessException(e.getMessage());
			}
			return strReuestNo;
		}

		//To Save the Appointment Type in the Appointment Type Master
		public static ApptTypeVO modifyDetails(ApptTypeVO objApptTypeModel,HisDAO hisDAO_p) 
			{
				final String strProcName = RegistrationDaoConfig.PROCEDURE_APPT_TYPE_VIEW ;
				final int nProcedureIndex;

				final String strDbErr;
				WebRowSet webRowSet = null;

				try

				{
					nProcedureIndex = hisDAO_p.setProcedure(strProcName);

					HisUtil.replaceNullValueWithEmptyString(objApptTypeModel);
					//Setting and Registering In and Out Parameters 
					hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_id",objApptTypeModel.getStrApptTypeId(),2);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_name",objApptTypeModel.getStrApptTypeName(),3);			
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_isdefault",objApptTypeModel.getStrApptIsDefault(),4);	
					hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

					hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
					hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

					hisDAO_p.executeProcedureByPosition(nProcedureIndex);

					strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
					webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

					// If Database Error Occurs, No farther processing is required. 
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
					objApptTypeModel.setStrMsgString("ApptTypeDAO.getStrApptTypeName() --> "	+ e.getMessage());
					objApptTypeModel.setStrMsgType("1");
				}

				try
				{
					webRowSet.beforeFirst();
					webRowSet.next();
					//objApptTypeModel.setStrApptTypeId(webRowSet.getString(2));
					objApptTypeModel.setStrApptTypeName(webRowSet.getString(2));
					objApptTypeModel.setStrIsValid(webRowSet.getString(3));
					objApptTypeModel.setStrApptIsDefault(webRowSet.getString(4));								

				}
				catch (Exception e)
				{
					throw new HisDataAccessException("ApptTypeDAO:getStrApptTypeName():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
				}
				return objApptTypeModel;

			}

			//To Update Room Details
			public static void updateApptTypeDetails(ApptTypeVO objApptTypeModel,HisDAO hisDAO_p,UserVO uservo ) 
			{
				final String strProcName =  RegistrationDaoConfig.PROCEDURE_APPT_TYPE_DML;
				final int nProcedureIndex;
				final String strDbErr;
				
				try
				{
					nProcedureIndex = hisDAO_p.setProcedure(strProcName);
					HisUtil.replaceNullValueWithEmptyString(objApptTypeModel);

					 //Setting and Registering In and Out Parameters 
					hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_id",objApptTypeModel.getStrApptTypeId(),2);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_name",objApptTypeModel.getStrApptTypeName(),3);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_isdefault",objApptTypeModel.getStrApptIsDefault(),4);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapstr_color" ,objApptTypeModel.getStrApptTypeColor(),5);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapdt_entry_date",objApptTypeModel.getStrEntryDate(),6);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,7);
					hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,8);
					hisDAO_p.executeProcedureByPosition(nProcedureIndex);
					 //Getting out parameters 
					strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
					 //If Database Error Occurs, No farther processing is required. 
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

			//For Duplicacy check
			public static boolean chkApptTypeDuplicate(ApptTypeVO objApptTypeModel,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
			{
				ResultSet rs = null;
				WebRowSet webRowSet = null;
				String strDbErr = "";
				boolean bExistStatus=false;
				final String strProcName = RegistrationDaoConfig.PROCEDURE_APPT_TYPE_VIEW;
				final int nProcedureIndex;
				int ncount=0;

				try

				{
					nProcedureIndex = hisDAO_p.setProcedure(strProcName);

					HisUtil.replaceNullValueWithEmptyString(objApptTypeModel);

					 //Setting and Registering In and Out Parameters 
					hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_id",objApptTypeModel.getStrApptTypeId(),2);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_type_name",objApptTypeModel.getStrApptTypeName(),3);			
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hapnum_appt_isdefault",objApptTypeModel.getStrApptIsDefault(),4);			
					hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

					hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
					hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

					hisDAO_p.executeProcedureByPosition(nProcedureIndex);

					strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
					webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

					 //If Database Error Occurs, No farther processing is required. 
					if (strDbErr != null && !strDbErr.equals("")) 
					{
						throw new Exception("Data Base Error:" + strDbErr);
					}

				}catch (Exception e) 
				{
					e.printStackTrace();
					objApptTypeModel.setStrMsgString("ApptTypeDAO.chkApptTypeDuplicate() --> " + e.getMessage());
					objApptTypeModel.setStrMsgType("1");

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
					throw new HisDataAccessException("ApptTypeDAO():HelperMethodsDAO.chkApptTypeDuplicate(rs)" + e);
				}
				if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
					bExistStatus=true;
				} 
				
				else {
					bExistStatus=false;
				}
					
				return bExistStatus;
				}

			//To List the Appointment Type in the Appointment Type Master
			public static List getApptTypeList(String deptUnitCode,HisDAO hisDAO_p,UserVO uservo)
			{
				List alRecord = new ArrayList(); 

				ResultSet rs = null;
				final String strProcName = RegistrationDaoConfig.PROCEDURE_ROOMSLIST_VIEW;
				final int nProcedureIndex;
				final String strDbErr;
				try
				{
					nProcedureIndex = hisDAO_p.setProcedure(strProcName);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
					//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
					//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptunitcode",deptUnitCode,4);
					hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); 
					hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);

					hisDAO_p.executeProcedureByPosition(nProcedureIndex);

					strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
					rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");			

				}
				catch (Exception e) 
				{
					e.printStackTrace();
					throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
				}

				try 
				{
					if (!rs.next()) 
					{
						//throw new HisRecordNotFoundException("Room List Not Found");
					}
					else
					{
						rs.beforeFirst();
						alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
					}
				}
				catch (Exception e) 
				{
					if (e.getClass() == HisRecordNotFoundException.class) 
					{
						throw new HisRecordNotFoundException(e.getMessage());
					} else
						throw new HisDataAccessException("ApptTypeDAO:getApptTypeList:HelperMethods :: " + e);
				}
				finally 
				{
					if (hisDAO_p != null) 
					{
						hisDAO_p.free();hisDAO_p = null;
					}
				}

				return alRecord;
			}


							
}			
