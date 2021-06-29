/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.chronicdisease.data;
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
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;



public class EHRSection_ChronicDiseaseDAO extends DataAccessObject 
{

	
		public EHRSection_ChronicDiseaseDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}
		
		
        //Added by Vasu on 08.May.2019
		public List<EHRSection_ChronicDiseaseVO> getChronicDiseaseList(UserVO userVO, PatientDetailVO selectedPatientVO)
		{
			
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.HPMRT_PAT_ALERTS_DTL.PAT_PROFILE_BY_CRNO";
						
			try
			{
				
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("All VISIT"+"  QUERY= "+query);		
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			try
			{
				
					populateMAP.put(sq.next(), selectedPatientVO.getPatCrNo());
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
				
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("EpisodeDiagnosisDAO.populateMAP::" + e);
			}

			List<EHRSection_ChronicDiseaseVO> prescriptionDtl = new ArrayList<EHRSection_ChronicDiseaseVO>();
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
					valueObjects = HelperMethods.populateVOfrmRS(EHRSection_ChronicDiseaseVO.class, rs);
					for (int i = 0; i < valueObjects.length; i++)
						prescriptionDtl.add((EHRSection_ChronicDiseaseVO) valueObjects[i]);
				}
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











