/**
 * 
 */
package registration.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;


import registration.config.RegistrationDaoConfig;
import vo.registration.DepartmentVO;

/**
 * @author s.singaravelan
 *
 */
public class DepartmentDAO
{

	//To List the Department Locations in the Department Master
	public static List getLocation(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_LOCATION_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",Config.SUPER_HOSPITAL_CODE,3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);

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
				throw new HisRecordNotFoundException("Department Location Details Not Found");
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
			} 
			else
				throw new HisDataAccessException("DepartmentDAO:getLocation:HelperMethods :: " + e);
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

	//To List the Global Departments from the Department Master
	public static DepartmentVO[] getGlobalDept(HisDAO hisDAO_p,UserVO uservo)
	{

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPARTMENT_VIEW;
		final int nProcedureIndex;
		final String strDbErr;		
		ValueObject vo[]={};
		DepartmentVO[] deptVO=null;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode","",2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptname","",3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7);

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
			if (!rs.next()){
				deptVO = new DepartmentVO[0];
				throw new HisRecordNotFoundException("Global Department Not Found");			
			}
			else
			{
				rs.beforeFirst();
				//System.out.println("-------"+rs.getInt(1)+"-------");
				//alRecord=HelperMethodsDAO.getAlOfEntryObjects(rs);
				vo =  HelperMethods.populateVOfrmRS(DepartmentVO.class, rs);					
				deptVO = new DepartmentVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{						
					deptVO[i] =(DepartmentVO)vo[i];						
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				e.printStackTrace();
			} else
				throw new HisDataAccessException("DepartmentDAO:getGlobalDepartment:HelperMethods :: " + e);
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();hisDAO_p = null;
			}
		}

		return deptVO;
	}

	//To List the Head Of Departments in the Department Master
	public static List getHeadOfDept(String desig_id,HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_HEADOFDEPARTMENT_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_desigid",desig_id,4);
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
				//throw new HisRecordNotFoundException("Head Of Department Not Found");
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
			} 
			else
				throw new HisDataAccessException("DepartmentDAO:getHeadOfDept:HelperMethods :: " + e);
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

	//To List the Department Type in the Department Master
	public static List getDepartmentType(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPARTMENT_TYPE_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4);

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
				throw new HisRecordNotFoundException("Department Type Not Found");
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
				throw new HisDataAccessException("DepartmentDAO:getDepartmentType:HelperMethods :: " + e);
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
	
	//To List the Department in the Department Master
	public static List getDepartmentList(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPARTMENT_LIST;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",uservo.getHospitalCode(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);

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
				throw new HisRecordNotFoundException("Department List Not Found");
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
				throw new HisDataAccessException("DepartmentDAO:getDepartment:HelperMethods :: " + e);
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


	//To Save the Department in the Department Mst
	public static String saveDepartmentDetails(DepartmentVO objModelDept,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPARTMENT_DML;
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		Boolean flag = null;
		String strReuestNo="";
		int funcIndex = 0;
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelDept);

			if(objModelDept.getStrHospitalCode().equalsIgnoreCase(Config.SUPER_HOSPITAL_CODE))
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			else
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","3",1);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDept.getStrDeptCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptname",objModelDept.getStrDeptName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptshort",objModelDept.getStrDeptShortName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_depttype",objModelDept.getStrDeptType(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_depthod",objModelDept.getStrHodCode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptlocation",objModelDept.getStrDeptLocCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_agelimit",objModelDept.getStrAgeLimit(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_gendercode",objModelDept.getStrGenderCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDept.getStrHospitalCode(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_lower_age_limit",objModelDept.getStrLowerAgeLimit(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_walkin",objModelDept.getStrMaxWalkinReg(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_walkin",objModelDept.getStrMaxWalkinFolloUp(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_portal",objModelDept.getStrMaxWalkinPortReg(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_portal",objModelDept.getStrMaxWalkinPortFollowUP(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_is_capping_allowed",objModelDept.getStrIsCappingAllowed(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelDept.getStrRemarks(),19);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,20); 
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
			objModelDept.setStrMsgString("DepartmentDAO.saveDepartmentDetails() --> "	+ e.getMessage());
			objModelDept.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		return strReuestNo;
	}


	//To get Department Details in Modify
	public static DepartmentVO modifyDetails(DepartmentVO objModelDept,HisDAO hisDAO_p) 
	{
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPARTMENT_VIEW;
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelDept);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDept.getStrDeptCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptname",objModelDept.getStrDeptName(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDept.getStrHospitalCode(),4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

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
			objModelDept.setStrMsgString("DepartmentVO.modifyDetails() --> "	+ e.getMessage());
			objModelDept.setStrMsgType("1");
		}


		try
		{
			//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
			webRowSet.beforeFirst();
			webRowSet.next();
			objModelDept.setStrDeptCode(webRowSet.getString(1));
			objModelDept.setStrDeptName(webRowSet.getString(2));
			objModelDept.setStrDeptType(webRowSet.getString(3));
			objModelDept.setStrHodCode(webRowSet.getString(4));
			objModelDept.setStrDeptShortName(webRowSet.getString(5));
			objModelDept.setStrDeptLocCode(webRowSet.getString(6));
			objModelDept.setStrAgeLimit(webRowSet.getString(7));
			objModelDept.setStrGenderCode(webRowSet.getString(8));
			objModelDept.setStrLowerAgeLimit(webRowSet.getString(10));
			objModelDept.setStrRemarks(webRowSet.getString(11));
			objModelDept.setStrDeptNameGlobal(webRowSet.getString(13));
			
			objModelDept.setStrMaxWalkinReg(webRowSet.getString(14));
			objModelDept.setStrMaxWalkinFolloUp(webRowSet.getString(15));
			objModelDept.setStrMaxWalkinPortReg(webRowSet.getString(16));
			objModelDept.setStrMaxWalkinPortFollowUP(webRowSet.getString(17));
			objModelDept.setStrIsCappingAllowed(webRowSet.getString(18));//by rajkumar
			
		

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DepartmentVO:modifyDetails():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return objModelDept;

	}

	//To Update Department Details
	public static void updateDepartmentDetails(DepartmentVO objModelDept,HisDAO hisDAO_p,UserVO uservo ) {


		final String strProcName =  RegistrationDaoConfig.PROCEDURE_DEPARTMENT_DML;
		final int nProcedureIndex;
		final String strDbErr;

		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelDept);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDept.getStrDeptCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptname",objModelDept.getStrDeptName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptshort",objModelDept.getStrDeptShortName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_depttype",objModelDept.getStrDeptType(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_depthod",objModelDept.getStrHodCode(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptlocation",objModelDept.getStrDeptLocCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_agelimit",objModelDept.getStrAgeLimit(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_gendercode",objModelDept.getStrGenderCode(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDept.getStrHospitalCode(),12);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_lower_age_limit",objModelDept.getStrLowerAgeLimit(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_walkin",objModelDept.getStrMaxWalkinReg(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_walkin",objModelDept.getStrMaxWalkinFolloUp(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_new_pat_portal",objModelDept.getStrMaxWalkinPortReg(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_old_pat_portal",objModelDept.getStrMaxWalkinPortFollowUP(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_is_capping_allowed",objModelDept.getStrIsCappingAllowed(),18);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_remarks",objModelDept.getStrRemarks(),19);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,20); 

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

	public static boolean chkDepartmentDuplicate(DepartmentVO objModelDept,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_DEPARTMENT_VIEW;
		final int nProcedureIndex;
		int ncount=0;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelDept);
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_deptcode",objModelDept.getStrDeptCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_deptname",objModelDept.getStrDeptName().trim(),3);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hoscode",objModelDept.getStrHospitalCode(),4);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,5);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			objModelDept.setStrMsgString("DepartmentDAO.chkDepartmentDuplicate() --> " + e.getMessage());
			objModelDept.setStrMsgType("1");

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
			throw new HisDataAccessException("DepartmentDAO():HelperMethodsDAO.chkDepartmentDuplicate(rs)" + e);
		}
		if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
			bExistStatus=true;
		} 
		else if(strMode_p.equalsIgnoreCase("2")) {
			if(objModelDept.getStrOldDeptName().equalsIgnoreCase(objModelDept.getStrDeptName()) && (ncount == 1))
			{
				bExistStatus=true;
			}
			if(!(objModelDept.getStrOldDeptName().equalsIgnoreCase(objModelDept.getStrDeptName())) && (ncount == 0))
			{
				bExistStatus=true;
			}
		} else {
			bExistStatus=false;
		}
		return bExistStatus;
	}


	//To List the Department Locations in the Department Master
	public static List getGenderList(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = RegistrationDaoConfig.PROCEDURE_GET_GENDER_COMBO;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,2); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,3);
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
				throw new HisRecordNotFoundException("getGenderList  Not Found");
			}else
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
				throw new HisDataAccessException("DepartmentDAO:getGenderList:HelperMethods :: " + e);
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
