/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.allergies.data;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import ehr.allergies.vo.EHRSection_AllergiesVO;



public class EHRSection_AllergiesDAO extends DataAccessObject 
{

	
		public EHRSection_AllergiesDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}
		
		public List<EHRSection_AllergiesVO> getAllergyList(UserVO userVO,PatientDetailVO selectedPatientVO)
		{
		
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.HPMRT_PAT_ALLERGY_DTL.PAT_PROFILE_BY_CRNO";
						
			try
			{
				
				String orderBy = " ORDER BY A.GDT_ENTRY_DATE";
				
					query = HelperMethodsDAO.getQuery(filename, queryKey);
					query= query+orderBy;
					System.out.println("All VISIT Custom"+"  QUERY= "+query);
				
				
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			try
			{
				
					populateMAP.put(sq.next(), selectedPatientVO.getPatCrNo());
					//populateMAP.put(sq.next(), _userVO.getHospitalCode());
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
				
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
			}

			List<EHRSection_AllergiesVO> prescriptionDtl = new ArrayList<EHRSection_AllergiesVO>();
			ValueObject[] valueObjects = null;
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				/*if (!rs.next())
				{
					throw new HisRecordNotFoundException("");
				}*/
				if(rs.next())
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(EHRSection_AllergiesVO.class, rs);
					for (int i = 0; i < valueObjects.length; i++)
						prescriptionDtl.add((EHRSection_AllergiesVO) valueObjects[i]);
				}
				/*else
				{
					throw new HisRecordNotFoundException("");
				}*/
			}
			catch (Exception e)
			{
				/*if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("PatientProfileDetailDAO:retrieveByCrNo::Episode Details:: " + e);*/
				e.printStackTrace();
			}
			return prescriptionDtl;
		}
		
		
}











