/**
 * 
 */
package loinc.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import loinc.vo.LoincVO;
import registration.config.RegistrationDaoConfig;
import vo.registration.LocationVO;

/**
 * @author sheeldarshi
 *
 */
public class LoincDAO
{

	
	//Lionc
	static String	 PROCEDURE_TEST_NAME_VIEW="{call pkg_loinc_view.gettestnamecombo(?,?,?,?)}";
	static String	 PROCEDURE_TEST_PARA_NAME_VIEW="{call pkg_loinc_view.gettestparamanamecombo(?,?,?,?,?)}";
	static String	 PROCEDURE_TEST_SAMPLE_VIEW="{call pkg_loinc_view.gettestsamplecombo(?,?,?,?,?,?)}";

	public static String	 PROCEDURE_GET_UOM_COMBO_VIEW="{call pkg_loinc_view.getuomcombo(?,?,?,?,?)}";

	static String	 PROCEDURE_GET_SUGGESTIVE_LOINC="{call pkg_loinc_view.gettestsamplecombo(?,?,?,?,?,?)}";
	static String	 PROCEDURE_IS_DUPLICATE_LOINC="{call pkg_loinc_view.chkloincAlreadyPresent(?,?,?,?,?,?,?)}";
	static String	 PROCEDURE_DML_LOINC="{call pkg_loinc_view.insertloincMappingMst(?,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?)}";
	static String	 PROCEDURE_UPDATE_LOINC="{call pkg_loinc_view.updateloincmappingmst(?,?,?::numeric,?,?,?)}";
	static String PROCEDURE_GET_CHECKBOX_VALUE="{call pkg_loinc_view.getvaluesforselectedcheckbox(?,?::numeric,?,?)}";
	
	
	
	
	
	
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

	//To List the Location Type in the Location Master
	public static List getLoincTestName(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = PROCEDURE_TEST_NAME_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",uservo.getHospitalCode(),2);
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
			//	throw new HisRecordNotFoundException("Location Type Not Found");
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
				throw new HisDataAccessException("LocationDAO:getLocationType:HelperMethods :: " + e);
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
	public static List GetPropertyCombo(HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = PROCEDURE_GET_UOM_COMBO_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",uservo.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "samplecode","1",3);
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
			//	throw new HisRecordNotFoundException("Location Type Not Found");
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
				throw new HisDataAccessException("LocationDAO:getLocationType:HelperMethods :: " + e);
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
	
	public static List getLoincTestParaName(LoincVO locModel ,HisDAO hisDAO_p,UserVO uservo)
	{
		List alRecord = new ArrayList(); 
		int seq=0;
		ResultSet rs = null;
		final String strProcName = PROCEDURE_TEST_PARA_NAME_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval","1",++seq);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",uservo.getHospitalCode(),++seq);
			if(locModel.getStrTestParaName()!= null )
			{
				hisDAO_p.setProcInValue(nProcedureIndex, "testcode",locModel.getStrTestParaName(),++seq);
						
			}
			else
			{
				hisDAO_p.setProcInValue(nProcedureIndex, "testcode","-1",++seq);		
			}
		
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,++seq); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,++seq);

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
				//throw new HisRecordNotFoundException("Location Type Not Found");
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
				throw new HisDataAccessException("LocationDAO:getLocationType:HelperMethods :: " + e);
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
	
	
	public static List getLoincTestParaNameNew(UserVO uservo,HisDAO hisDAO_p, String TestCode)
	{
		List alRecord = new ArrayList(); 
		int seq=0;
		ResultSet rs = null;
		final String strProcName = PROCEDURE_TEST_PARA_NAME_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval","1",++seq);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",uservo.getHospitalCode(),++seq);
			if(TestCode!= null )
		{
				hisDAO_p.setProcInValue(nProcedureIndex, "testcode",TestCode,++seq);
						
			}
			else
			{
				hisDAO_p.setProcInValue(nProcedureIndex, "testcode","-1",++seq);		
			}
		
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,++seq); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,++seq);

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
				//throw new HisRecordNotFoundException("Location Type Not Found");
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
				throw new HisDataAccessException("LocationDAO:getLocationType:HelperMethods :: " + e);
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

	public static List getLoincTestSample(HisDAO hisDAO_p,UserVO uservo,String strTestCode, String strTestType)
	{
		List alRecord = new ArrayList(); 

		ResultSet rs = null;
		final String strProcName = PROCEDURE_TEST_SAMPLE_VIEW;
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "hospitalcode",uservo.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "testtype",strTestType,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "testcode",strTestCode,3);
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
			//	throw new HisRecordNotFoundException("Location Type Not Found");
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
				throw new HisDataAccessException("LocationDAO:getLocationType:HelperMethods :: " + e);
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
	
	
	
	public static LoincVO[]  searchSuggestiveLoinc(LoincVO loincVO, UserVO userVO) 
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_loinc_view.getsuggestiveloinc(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("LOINC","LOINCDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
		//daoObj.setProcInValue(nProcIndex, "testName", loincVO.getStrTestName(),2);
		daoObj.setProcInValue(nProcIndex, "testName", loincVO.getStrTestNameValue(),2);
		//daoObj.setProcInValue(nProcIndex, "testParaName",loincVO.getStrTestParaName(),3);
		daoObj.setProcInValue(nProcIndex, "testParaName",loincVO.getStrTestNameValue(),3);
		daoObj.setProcInValue(nProcIndex, "sampleName",loincVO.getStrSystem(),4);
		daoObj.setProcInValue(nProcIndex, "strScale", loincVO.getStrScale(),5);
		daoObj.setProcInValue(nProcIndex, "strProperty",loincVO.getStrProperty(),6);
		daoObj.setProcInValue(nProcIndex, "strTimeOfMeasurement", loincVO.getStrTimeofMeasurement(),7);

		daoObj.setProcOutValue(nProcIndex, "err", 1,8);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		LoincVO[] objLOINCVO={};
		ValueObject[] vo = {};
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Loinc code found");
			}
			else
			{
				System.out.println(loincVO.getStrProperty());
				loincVO.setStrProperty(loincVO.getStrProperty());
				
				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(LoincVO.class, rs);
				objLOINCVO = new LoincVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				
					objLOINCVO[i] = (LoincVO) vo[i];
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException(" :: " + e);
		}
		return objLOINCVO;
	}
	
	public static LoincVO[]  searchLoinc(LoincVO loincVO, UserVO userVO) 
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_loinc_view.getSearchLoinc(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("LOINC","LOINCDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_modeval", loincVO.getIsLoincName(),1);
		//daoObj.setProcInValue(nProcIndex, "testName", loincVO.getStrTestName(),2);
		daoObj.setProcInValue(nProcIndex, "strSearchParam", loincVO.getStrLoincSearchParameter(),2);
	
		daoObj.setProcOutValue(nProcIndex, "err", 1,3);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		LoincVO[] objLOINCVO={};
		ValueObject[] vo = {};
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Loinc code found");
			}
			else
			{
				System.out.println(loincVO.getStrProperty());
				loincVO.setStrProperty(loincVO.getStrProperty());
				
				
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(LoincVO.class, rs);
				objLOINCVO = new LoincVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				
					objLOINCVO[i] = (LoincVO) vo[i];
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException(" :: " + e);
		}
		return objLOINCVO;
	}
	
	//For Duplicacy Check
	public static boolean chkLoincPresent(LoincVO objModelLoc,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=true;
		final String strProcName = PROCEDURE_IS_DUPLICATE_LOINC;;
		final int nProcedureIndex;
		int ncount=0;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelLoc);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval", "1",1);
			//hisDAO_p.setProcInValue(nProcedureIndex, "testCode",objModelLoc.getStrTestName(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "testCode",objModelLoc.getStrTestNameCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "testparaCode",objModelLoc.getstrTestParaNameCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "samplecode",objModelLoc.getstrTestSampleCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "unitOfMeasurementCode",objModelLoc.getStrUOMValue(),5);
			//hisDAO_p.setProcInValue(nProcedureIndex, "strUnitOfMeasurementCode",objModelLoc.getStrProperty(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,7); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDbErr != null && !strDbErr.equals("")) 
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			objModelLoc.setStrMsgString("LocationDAO.chkLocationDuplicate() --> " + e.getMessage());
			objModelLoc.setStrMsgType("1");

		} 
		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			ncount=Integer.parseInt(webRowSet.getString(1));
			if(ncount==0)
			{
				bExistStatus=false;
			}
				System.out.println("------"+ncount+"-----");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO():HelperMethodsDAO.chkLocationDuplicate(rs)" + e);
		}
	
		
		
		return bExistStatus;
	}
	
	
	
	//To Save the Location in the Location Mst
	public static String saveLoincDetails(LoincVO objModelLoc,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = PROCEDURE_DML_LOINC;
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		//
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objModelLoc);			

			hisDAO_p.setProcInValue(nProcedureIndex, "p_modeval","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "loincString",objModelLoc.getStrLoincCode(),2);
			//hisDAO_p.setProcInValue(nProcedureIndex, "testcode",objModelLoc.getStrTestName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "testcode",objModelLoc.getStrTestNameCode(),3);
			//hisDAO_p.setProcInValue(nProcedureIndex, "testParaCode",objModelLoc.getStrTestParaName(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "testParaCode",objModelLoc.getstrTestParaNameCode(),4);
			//hisDAO_p.setProcInValue(nProcedureIndex, "strSampleCode",objModelLoc.getStrTestSample(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "strSampleCode",objModelLoc.getstrTestSampleCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "strOranismCode","0",6);
			//hisDAO_p.setProcInValue(nProcedureIndex, "strUOMCode",objModelLoc.getStrUOMCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "strUOMCode",objModelLoc.getStrUOMValue(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "strMethodCode","0",8);
			hisDAO_p.setProcInValue(nProcedureIndex, "gnum_seatid",uservo.getSeatId(),9);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,10); 

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Inserted Successussfully");

			// If Database Error Occurs, No farther processing is required. 
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}

		catch (Exception e)
		{
			e.printStackTrace();
			//objModelLoc.setStrMsgString("LocationDAO.saveLocationDetails() --> "	+ e.getMessage());
			//objModelLoc.setStrMsgType("1");
			//throw new HisDataAccessException(e.getMessage());
		}
		finally 
		{
			if (hisDAO_p != null) 
			{
				hisDAO_p.free();
				hisDAO_p = null;
			}
		}
		return strReuestNo;
	}
	
		//To Update Loinc Details
		public static void updateLoincDetails(LoincVO objModelLoc,HisDAO hisDAO_p,UserVO uservo ) {


			final String strProcName =  PROCEDURE_UPDATE_LOINC;
			final int nProcedureIndex;
			final String strDbErr;
			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);
				HisUtil.replaceNullValueWithEmptyString(objModelLoc);

				/* Setting and Registering In and Out Parameters */
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
				hisDAO_p.setProcInValue(nProcedureIndex, "loincstring",objModelLoc.getStrLoincCode(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "seqno",objModelLoc.getStrSeqNo(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "oldloincstring",objModelLoc.getStrOldLoincCode(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "gnum_seatid",uservo.getSeatId(),5);
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); 

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
	
		public static List  GetValuesForSelectedCheckbox(LoincVO objModelLoc,HisDAO hisDAO_p,UserVO uservo)
		{
			WebRowSet webRowSet = null;
			final String strProcName = PROCEDURE_GET_CHECKBOX_VALUE;
			List alRecord = new ArrayList(); 
			final int nProcedureIndex;
			final String strDbErr;
			//String strReuestNo="";
			//String Values="";
			//int funcIndex=0;
			
			//
			try
			{
				nProcedureIndex = hisDAO_p.setProcedure(strProcName);
				//HisUtil.replaceNullValueWithEmptyString(objModelLoc);			

				hisDAO_p.setProcInValue(nProcedureIndex, "loincString",objModelLoc.getStrOldLoincCode(),1);
				hisDAO_p.setProcInValue(nProcedureIndex, "seqno",objModelLoc.getStrSeqNo(),2);

				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3);
				hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4);
				
				hisDAO_p.executeProcedureByPosition(nProcedureIndex);
				
				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

				
			/*	funcIndex = hisDAO_p.setFunction("{? = call pkg_loinc_view.GetValuesForSelectedCheckbox(?,?::numeric)}");
				hisDAO_p.setFuncInValue(funcIndex, 1, objModelLoc.getStrOldLoincCode());
				hisDAO_p.setFuncInValue(funcIndex, 2,objModelLoc.getStrSeqNo());
				hisDAO_p.setFuncOutValue(funcIndex,1);
				
				hisDAO_p.executeFunction(funcIndex);
				Values = hisDAO_p.getFuncString(funcIndex);*/
				
				
				// Getting out parameters 
				//strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				//System.out.println("Data Inserted Successussfully");

				// If Database Error Occurs, No farther processing is required. 
				/*if (strDbErr != null && !strDbErr.equals("")) {
					throw new Exception("Data Base Error:" + strDbErr);
				}*/

			}

			catch (Exception e) 
			{
				e.printStackTrace();
				throw new HisDataAccessException("PatientDAO.generateCRno()" + e);
			}
			try 
			{
				if (!webRowSet.next()) 
				{
				//	throw new HisRecordNotFoundException("Location Type Not Found");
				}
				else
				{
					webRowSet.beforeFirst();
					alRecord=HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
				}
			} 
			catch (Exception e) 
			{
				if (e.getClass() == HisRecordNotFoundException.class) 
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else
					throw new HisDataAccessException("LocationDAO:getLocationType:HelperMethods :: " + e);
			}
			finally 
			{
				/*if (hisDAO_p != null) 
				{
					hisDAO_p.free();
					hisDAO_p = null;
				}*/
			}
			return alRecord;
		}
}
