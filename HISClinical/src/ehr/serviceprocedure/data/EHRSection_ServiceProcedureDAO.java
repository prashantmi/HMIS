/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.serviceprocedure.data;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import ehr.EHRConfig;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import ehr.serviceprocedure.vo.EHRSection_ServiceProcedureVO;
import emr.vo.PatientClinicalDocDetailVO;



public class EHRSection_ServiceProcedureDAO extends DataAccessObject 
{

	
		public EHRSection_ServiceProcedureDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}

		//Added by Vasu on 21.May.2019
		public List<EHRSection_ServiceProcedureVO> getServiceProcedures(UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
		{
			
			ResultSet rs = null;
			String errorMsg="";
			
			try
			{
				Procedure strProc=new Procedure(EHRConfig.PROC_FOR_SERVICE_PROCEDURES);
				strProc.addInParameter(1,Types.VARCHAR,"1");
				strProc.addInParameter(2,Types.VARCHAR,selectedPatientVO.getDepartmentCode());
				strProc.addInParameter(3,Types.VARCHAR,selectedPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(4,Types.VARCHAR,selectedPatientVO.getWardCode());
				strProc.addInParameter(5,Types.VARCHAR,userVO.getSeatId());  
				
				strProc.addInParameter(6,Types.VARCHAR,userVO.getHospitalCode());
				strProc.addInParameter(7,Types.VARCHAR,selectedPatientVO.getPatCrNo());
				strProc.addInParameter(8,Types.VARCHAR,selectedPatientVO.getEpisodeCode());
				strProc.addInParameter(9,Types.VARCHAR,selectedPatientVO.getEpisodeVisitNo());
				strProc.addInParameter(10,Types.VARCHAR,selectedPatientVO.getAddmissionNo());
				
				strProc.addInParameter(11,Types.VARCHAR,clinicalDocVO.getDocumentType());
				strProc.addInParameter(12,Types.VARCHAR,clinicalDocVO.getClinicalSectionCode());
				
				strProc.addOutParameter(13,Types.VARCHAR);
				strProc.addOutParameter(14,Types.REF);//OracleTypes.CURSOR);
				
				
				strProc.execute(super.getTransactionContext().getConnection());
				
				errorMsg=(String) strProc.getParameterAt(13);
				rs=(ResultSet) strProc.getParameterAt(14);
			}
			catch(HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			catch (HisException e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			List<EHRSection_ServiceProcedureVO> lstServiceProceduresVO = new ArrayList<EHRSection_ServiceProcedureVO>();
			ValueObject[] valueObjects = null;
			try
			{
					if(rs.next())
					{
						rs.beforeFirst();
						
						/*while(rs.next()){
							System.out.println("rs.getString(1)=="+rs.getString(1));
							System.out.println(rs.getString(2));
							System.out.println(rs.getString(3));
						}*/
						
						
						
						valueObjects = HelperMethods.populateVOfrmRS(EHRSection_ServiceProcedureVO.class, rs);
						for (int i = 0; i < valueObjects.length; i++)
							lstServiceProceduresVO.add((EHRSection_ServiceProcedureVO) valueObjects[i]);
					}
				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					else throw new HisDataAccessException("PatientDocumentDetailDAO:getEpisodePatientDocuments::Patient Episode Documents:: " + e);
				}
			return lstServiceProceduresVO;
		}
			
}











