/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.history.data;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
/*import hisglobal.vo.FormAReportVO;
import hisglobal.vo.FormCReportVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.QualityControlMstVO;
import hisglobal.vo.QualityParameterMappingVO;
import hisglobal.vo.RefreshmentItemMstVO;
import hisglobal.vo.ScreeningOfOtherTTDReportVO;
import hisglobal.vo.TherapeuticPatientDtlVO;*/
//import hisglobal.vo.TherapeuticTypeMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
//import hisglobal.vo.VoluntaryCardDtlVO;






import java.sql.Types;

import org.apache.log4j.Logger;

import application.config.HISApplicationConfig;
import ehr.EHRConfig;
import ehr.history.vo.EHRSection_HistoryVO;
import opd.OpdConfig;
import opd.dao.OpdDaoConfig;

public class EHRSection_HistoryDAO extends DataAccessObject 
{

	
		public EHRSection_HistoryDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}
		
		Logger log;

		public List<Entry> getDeskMenuTemplateList(String _deskType, UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
		{
			ResultSet rs = null;
			String errorMsg="";
			
			try
			{
				Procedure strProc=new Procedure(EHRConfig.PROC_FOR_TEMPLATE_LIST);
			    strProc.addInParameter(1,Types.VARCHAR,_deskType);
				strProc.addInParameter(2,Types.VARCHAR,_userDeskTempVO.getDeptUnitCode());
				if(_userDeskTempVO.getWardCode()==null || _userDeskTempVO.getWardCode().equals("")) _userDeskTempVO.setWardCode("-1");
				strProc.addInParameter(3,Types.VARCHAR,_userDeskTempVO.getWardCode());
				//strProc.addInParameter(4,Types.VARCHAR,_userDeskTempVO.getUserSeatId());
				strProc.addInParameter(4,Types.VARCHAR,_userVO.getSeatId());  
				
				 //in case of calling outside desk,  setting default desk as IPD Doctor Desk: DeskId=6)
				if(_userDeskTempVO.getDeskId()==null || _userDeskTempVO.getDeskId().equals("")) _userDeskTempVO.setDeskId("6"); 
				strProc.addInParameter(5,Types.VARCHAR,_userDeskTempVO.getDeskId());
				
				strProc.addInParameter(6,Types.VARCHAR,_userDeskTempVO.getDeskMenuId());
				strProc.addInParameter(7,Types.VARCHAR,_userVO.getHospitalCode());
				strProc.addOutParameter(8,Types.VARCHAR);
				strProc.addOutParameter(9,HISApplicationConfig.REF_TYPE);//OracleTypes.CURSOR);
				
				//System.out.println("_deskType:"+ _deskType);
				//System.out.println(" _userVO.getSeatId():"+ _userVO.getSeatId());
				//System.out.println("_unitCode:"+_userDeskTempVO.getDeptUnitCode());
				//System.out.println("_deskMenuId:"+_userDeskTempVO.getDeskMenuId());
				//System.out.println("_userDeskTempVO.getDeskId():"+_userDeskTempVO.getDeskId());
				//System.out.println("_userVO.getHospitalCode():"+_userVO.getHospitalCode());
				
				strProc.execute(super.getTransactionContext().getConnection());
				
				errorMsg=(String) strProc.getParameterAt(8);
				rs=(ResultSet) strProc.getParameterAt(9) ;
			}
			catch(HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			catch (HisException e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			List<Entry> lstTemplates = new ArrayList<Entry>();
			try
			{
				while(rs.next())
				{
					Entry e = new Entry();
					e.setValue(rs.getString(1));
					e.setLabel(rs.getString(2));
					lstTemplates.add(e);
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("AlertDAO::getDeskMenuTemplateList"+e);
			}
			return lstTemplates;
		}
		
		
		
		public Map<String, Map<String,String>> getPatientClinicalData(EHRSection_HistoryVO _patClinicalVO, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.PATIENT_FINAL_CLINICAL_DATA.HPMRT.HRGT";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();
			try
			{
				String tempIds = "";
				for(int i=0;i<_patClinicalVO.getTemplateIds().length;i++)
					tempIds+=_patClinicalVO.getTemplateIds()[i]+",";
				if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);	
				query = query.replace("#", tempIds);
				
				populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
				populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patClinicalVO.getEpisodeVisitNo());
				populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"PatientClinicalDetailDAO.getPatientClinicalData::populateMAP " + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			
			Map<String, Map<String, String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
			ValueObject[] vo = {};
			GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
			try
			{
				if(rs.next())
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);				
					tempParas = new GenericTemplateUtility.TempParameter[vo.length];
					for (int i = 0; i < vo.length; i++)
						tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
					for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, String> map = null;
						if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
							map = mpParaTempValues.get(paraValVO.getTemplateId());
						else
							map = new HashMap<String, String>();
						map.put(paraValVO.getParaId(), paraValVO.getParaValue());
						mpParaTempValues.put(paraValVO.getTemplateId(), map);
					}
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			return mpParaTempValues;		
		}
		
		/*ResultSet rs = null;
		String errorMsg="";
		String pmode="1";
		try
		{
			String tempIds = "";
			for(int i=0;i<_patClinicalVO.getTemplateIds().length;i++)
				tempIds+=_patClinicalVO.getTemplateIds()[i]+",";
			if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
			query = query.replace("#", tempIds);
			
			Procedure strProc=new Procedure(EHRConfig.PROC_FOR_PATIENT_CLINICAL_DTL_HPMRT_HRGT);
		    strProc.addInParameter(1,Types.VARCHAR,pmode);
			strProc.addInParameter(2,Types.VARCHAR,_patClinicalVO.getPatCrNo());
			
			strProc.addInParameter(3,Types.VARCHAR,__patClinicalVO.getEpisodeCode());
			//strProc.addInParameter(4,Types.VARCHAR,_userDeskTempVO.getUserSeatId());
			strProc.addInParameter(4,Types.VARCHAR, _patClinicalVO.getEpisodeVisitNo());
			strProc.addInParameter(5,Types.VARCHAR,_patClinicalVO.getDeskMenuId());
			strProc.addInParameter(6,Types.VARCHAR,_userVO.getHospitalCode());
			strProc.addInParameter(7,Types.VARCHAR,Config.IS_VALID_ACTIVE);
			strProc.addOutParameter(8,Types.VARCHAR);
			strProc.addOutParameter(9,Types.REF);//OracleTypes.CURSOR);
			
			System.out.println("_deskType:"+ _deskType);
			System.out.println(" _userVO.getSeatId():"+ _userVO.getSeatId());
			System.out.println("_unitCode:"+_userDeskTempVO.getDeptUnitCode());
			System.out.println("_deskMenuId:"+_userDeskTempVO.getDeskMenuId());
			System.out.println("_userDeskTempVO.getDeskId():"+_userDeskTempVO.getDeskId());
			System.out.println("_userVO.getHospitalCode():"+_userVO.getHospitalCode());
			
			strProc.execute(super.getTransactionContext().getConnection());
			
			errorMsg=(String) strProc.getParameterAt(8);
			rs=(ResultSet) strProc.getParameterAt(9) ;*/
		
		
		public Map<String, Map<String,String>> getPatientCentricClinicalData(EHRSection_HistoryVO _patClinicalVO, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.PAT_CLI_DATA.HPMRT_PAT_CLINICAL_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();
			try
			{
				String tempIds = "";
				for(int i=0;i<_patClinicalVO.getTemplateIds().length;i++)
					tempIds+=_patClinicalVO.getTemplateIds()[i]+",";
				if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
				query = query.replace("#", tempIds);
				
				populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientCentricClinicalData::populateMAP " + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			
			Map<String, Map<String, String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
			ValueObject[] vo = {};
			GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
			try
			{
				if(rs.next())
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);				
					tempParas = new GenericTemplateUtility.TempParameter[vo.length];
					for (int i = 0; i < vo.length; i++)
						tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
					for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String, String> map = null;
						if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
							map = mpParaTempValues.get(paraValVO.getTemplateId());
						else
							map = new HashMap<String, String>();
						map.put(paraValVO.getParaId(), paraValVO.getParaValue());
						mpParaTempValues.put(paraValVO.getTemplateId(), map);
					}
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HisDataAccessException : getPatientCentricClinicalData" + e);
			}
			return mpParaTempValues;
		}
		
		
		
		public TemplateMasterVO getTemplateDataById(TemplateMasterVO _voTemp, UserVO _userVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();

			String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
			String queryKey = "SELECT.BY_ID.HGBT_TEMPLATE_MST";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			try
			{
				populateMAP.put(sq.next(), _voTemp.getTemplateId());
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("TemplateMasterDAO.populateMAP::" + e);
			}
			TemplateMasterVO vo = new TemplateMasterVO();
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (rs.next())
				{
					vo.setTemplateId(_voTemp.getTemplateId());
					vo.setTempSerialNo(_voTemp.getTempSerialNo());
					
					vo.setTemplateName(rs.getString(1));
					vo.setTemplateCategory(rs.getString(2));
					vo.setTemplateType(rs.getString(3));
					vo.setEffectiveFrom(rs.getString(4));
					if (rs.getString(5)== null)	vo.setEffectiveTo("");
					else	vo.setEffectiveTo(rs.getString(5));								
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			return vo;
		}

		
		
		// * Getting Previous Visit Report Dates List  //added by manisha gangwar date: 24.8.2017
		public List getPrevVisitReportDates(String _patCrNo, String _episodeCode, String deskMenuId,UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "SELECT.PREV_VISIT_REPORT_DATES.HRGT_EPISODE_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();
			try
			{
				populateMAP.put(sq.next(), _patCrNo);
				populateMAP.put(sq.next(), _episodeCode);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), deskMenuId);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = new ArrayList();
			try
			{
				if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HisDataAccessException  :getPrevVisitDates" + e);
			}
			return alRecord;
		}
		
		
		
		
		public List<Entry> getClinicalRecordDateList(String _patAdmNo,String deskMenuId, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.ALL_RECORD_DATES.HIPD_PAT_CLINICAL_DTL_DESKMENU";
			String queryKey1 = "SELECT.ALL_RECORD_DATES.HIPD_PAT_CLINICAL_DTL";

			try
			{
				if(! deskMenuId.isEmpty())
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				else
				query = HelperMethodsDAO.getQuery(filename, queryKey1);
					
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();
			try
			{
				if(! deskMenuId.isEmpty())
				{
				populateMAP.put(sq.next(), _patAdmNo);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), deskMenuId);
				}
				else
				{
				populateMAP.put(sq.next(), _patAdmNo);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientsOnlyCentricClinicalData::populateMAP" + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List<Entry> lstDates = null;
			try
			{
				if(rs.next())
				{
					lstDates = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			return lstDates;		
		}
		
		
		
		public GenericTemplateUtility.TempParameter[] getPatientEpisodeClinicalDataTempWise(EHRSection_HistoryVO _patClinicalVO, 
				List<Entry> _lstTemps, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.PAT_EPI_CLI_DATA_TEMP_WISE.HRGT_EPISODE_CLINICAL_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();
			try
			{
				String tempIds = "";
				for(Entry entObj : _lstTemps)
					tempIds+=entObj.getValue().split("#")[0]+",";   //entObj.getValue()+",";
				if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
				query = query.replace("#", tempIds);

				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), _patClinicalVO.getPatCrNo());
				populateMAP.put(sq.next(), _patClinicalVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patClinicalVO.getEpisodeVisitNo());
				populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientEpisodeClinicalDataTempWise::populateMAP" + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
					
			ValueObject[] vo = {};
			GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
			try
			{
				if(rs.next())
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
					tempParas = new GenericTemplateUtility.TempParameter[vo.length];
					for (int i = 0; i < vo.length; i++)
						tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
					
					/* Converting Array into "Map of temp Id by List of Entry Object with paraId/Value"
					Map<String, Map<String,String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
					for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String,String> map = null;
						if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
							map = mpParaTempValues.get(paraValVO.getTemplateId());
						else
							map = new HashMap<String, String>();
						map.put(paraValVO.getParaId(), paraValVO.getParaValue());
						mpParaTempValues.put(paraValVO.getTemplateId(), map);
					}*/
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			return tempParas;		
		}

		
		public GenericTemplateUtility.TempParameter[] getPatientClinicalRecordDataTempWise(EHRSection_HistoryVO _patClinicalVO, 
				List<Entry> _lstTemps, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.PAT_REC_CLI_DATA.HIPD_PAT_CLINICAL_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();
			try
			{
				String tempIds = "";
				for(Entry entObj : _lstTemps)
					tempIds+=entObj.getValue()+",";
				if(!tempIds.equals(""))	tempIds = tempIds.substring(0,tempIds.length()-1);
				query = query.replace("#", tempIds);

				populateMAP.put(sq.next(), _patClinicalVO.getAdmissionNo());
				populateMAP.put(sq.next(), _patClinicalVO.getRecordDate());
				//populateMAP.put(sq.next(), _patClinicalVO.getDeskMenuId());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("PatientClinicalDetailDAO.getPatientClinicalRecordDataTempWise::populateMAP" + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			
			ValueObject[] vo = {};
			GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
			try
			{
				if(rs.next())
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
					tempParas = new GenericTemplateUtility.TempParameter[vo.length];
					for (int i = 0; i < vo.length; i++)
						tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
					
					/* Converting Array into "Map of temp Id by List of Entry Object with paraId/Value"
					Map<String, Map<String,String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
					for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String,String> map = null;
						if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
							map = mpParaTempValues.get(paraValVO.getTemplateId());
						else
							map = new HashMap<String, String>();
						map.put(paraValVO.getParaId(), paraValVO.getParaValue());
						mpParaTempValues.put(paraValVO.getTemplateId(), map);
					}*/
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			return tempParas;		
		}
		
		
		public void savePtientClinicalDetail(String _deskType, PatientClinicalDetailVO _patClinDtlVO, UserVO _userVO)
		{
			String errorCode="";
			String errorMsg="";
			
			try
			{			
				if(_deskType.equals("12")){_deskType="1";}
				Procedure strProc=new Procedure(OpdDaoConfig.PROCEDURE_SAVE_PATIENT_EPISODE_CLINICAL_DATA);
				int i=1;
			    strProc.addInParameter(i++,Types.VARCHAR,_deskType);
			    strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getPatCrNo());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getEpisodeCode());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getEpisodeVisitNo());
				if(_patClinDtlVO.getAdmissionNo()==null) _patClinDtlVO.setAdmissionNo("");
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getAdmissionNo());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getRecordDate());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getDeskMenuId());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getTemplateId());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaId());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaValue());
				strProc.addInParameter(i++,Types.VARCHAR,_userVO.getSeatId());
				strProc.addInParameter(i++,Types.VARCHAR,_userVO.getHospitalCode());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaValueConcpetId());
				strProc.addInParameter(i++,Types.VARCHAR,_patClinDtlVO.getParaValuePreffereTerm());
				strProc.addOutParameter(i++,Types.VARCHAR);
				strProc.addOutParameter(i++,Types.VARCHAR);
				
				strProc.execute(super.getTransactionContext().getConnection());
				
				errorCode=(String) strProc.getParameterAt(15);
				errorMsg=(String) strProc.getParameterAt(16);
			}
			catch(HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			catch (HisException e)
			{
				throw new HisDataAccessException((errorMsg == null ? "" : errorCode+":"+errorMsg) + e);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException((errorMsg == null ? "" : errorCode+":"+errorMsg) + e);
			}
		}
		
		
		

	} //END Class






