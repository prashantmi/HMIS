/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentdetail.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.dao.DrugAdminDtlDAO;
import inpatient.transaction.dao.DrugAdminDtlDAOi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import opd.OpdConfig;
import opd.dao.DrugScheduleDtlDAO;

public class EHRSection_TreatmentBO {

	
	
	/**
	 * Getting Patient Treatment Detail Essentials
	 * @param _patCrNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO 
	 * @return Map of Essentials
	 */
	public Map getPatTreatmentDetailEssential(String _patCrNo,String adviceType, String _episodeCode, String depUnitCode ,String genderType,  PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List<Entry> lstDosageFreq=null;
		List<Entry> lstDrugs=null;
		List<EHRSection_TreatmentVO> lstPrevDrugDetail = null;
		DrugFrequencyMstVO[] drugFrequencyMstVO;
		List serachDrugList=null;
		List drugProfileDrugDetailLst=null;
		List otherInstListByGender=null;
		List lstPrevDrugDetailForLog=null;
		List prevDrugSchedule=null;
		List lstDrugAdminFlags=null;
		List lstDrugDoses=null;
		List lstDrugRoutes=null;
		
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("EHR", "EHRSection_TreatmentBO");
		try
		{
			tx.begin();
			
			EHRSection_TreatmentDAO dao = new EHRSection_TreatmentDAO(tx);
			
			lstDosageFreq = dao.getDosageFrequecy(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY, lstDosageFreq);
			
			drugFrequencyMstVO = dao.getDrugFrequencyVOList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY, drugFrequencyMstVO);
			
			//lstDrugs = dao.getDrugList(_userVO);
			
			PatientDetailVO patVO = new PatientDetailVO();
			patVO.setPatCrNo(_patCrNo);
			patVO.setEpisodeCode(_episodeCode);
			patVO.setPatGenderType(genderType);
			patVO.setDepartmentUnitCode(depUnitCode);
			
			lstDrugs = dao.getGenericDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);   //for Generic Drug List
			
	     // lstDrugs = dao.getDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);      // for Drug Brand List 
			
			
			
			if (lstDrugs==null || lstDrugs.size()==0 )
			{
				//lstDrugs = dao.getDrugList(_userVO);
				lstDrugs = dao.getGenericDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);
				
			}
				
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS, lstDrugs);
			
		
			serachDrugList=dao.getGenericDrugListDetail(hisDAO , OpdConfig.serachDrugList, patVO, _userVO);
			if(serachDrugList==null || serachDrugList.size()==0)
			{
				//serachDrugList=dao.getDrugListForSearch(hisDAO,_userVO);
				serachDrugList=dao.getGenericDrugListDetail(hisDAO , OpdConfig.serachDrugList, patVO, _userVO);
			}
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS_FOR_SEARCH, serachDrugList); 

			lstDrugAdminFlags = dao.getDrugAdministrationFlagsList(hisDAO , OpdConfig.lstDrugAdminFlag);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS, lstDrugAdminFlags);

			
			lstDrugRoutes = dao.getDrugRouteList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE, lstDrugRoutes);

			
			drugProfileDrugDetailLst=dao.getDefaultDrugProfileForUnit(depUnitCode, _userVO);
			essentialMap.put(OpdConfig.DEFAULT_DRUG_PROFILE_DRUG_LIST, drugProfileDrugDetailLst);
			
			lstDrugDoses = dao.getDrugDosesList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES, lstDrugDoses);
			
			if(adviceType.equalsIgnoreCase(Config.TREATMENT_ADVICE_TYPE_DEFUALT))
			{
			lstPrevDrugDetail = dao.getPrevPatDrugDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST, lstPrevDrugDetail);
			}
			if(adviceType.equalsIgnoreCase(Config.TREATMENT_ADVICE_TYPE_DISCHARGE))
			{
			lstPrevDrugDetail = dao.getPrevAdviceOnDischargeDrugDetail_(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST, lstPrevDrugDetail);
			}
			String maxEntryDate=dao.getMaxEntryDateFromDrugDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.MAX_ENTRY_DATE_BY_CRNO, maxEntryDate);
		
			otherInstListByGender=dao.getAllOtherInstructionList(genderType, depUnitCode, _userVO);
			essentialMap.put(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER, otherInstListByGender);
			
			
			lstPrevDrugDetailForLog=dao.getPrevPatDrugDetailForLog(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG, lstPrevDrugDetailForLog);
		
			prevDrugSchedule=dao.getPrevDrugSchedule(_patCrNo,  _episodeCode,_userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE, prevDrugSchedule);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if (hisDAO != null)
			{
				hisDAO.free();
				hisDAO = null;
			}
			tx.close();
		}
		return essentialMap;
	}
	
	
	
	
	
	public void savePatTreatmentDetail(List<EHRSection_TreatmentVO> _lstDrugDtl, List<EHRSection_TreatmentVO> revokedDrugTreatmentDtl,Map drugScheduleMap, List drugAdminLst, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			EHRSection_TreatmentDAO dao = new EHRSection_TreatmentDAO(tx);
			DrugScheduleDtlDAO drugScheduleDtlDAO = new DrugScheduleDtlDAO(tx);
			DrugAdminDtlDAOi drugAdminDtlDAO = new DrugAdminDtlDAO(tx);
			
			
			String maxSlNoTreat = "";
			Integer maxSlNoTreatInt = 0;

			if (_lstDrugDtl != null && _lstDrugDtl.size() != 0)
			{
				EHRSection_TreatmentVO patTreatVO = (EHRSection_TreatmentVO) _lstDrugDtl.get(0);

				maxSlNoTreat = dao.getMaxSlNo(patTreatVO, _userVO);
				maxSlNoTreatInt = new Integer(maxSlNoTreat);

			}

			for (EHRSection_TreatmentVO vo : _lstDrugDtl)
			{	
				String pmode="0";

					String consentStatus = dao.getConsentStatus(vo, _userVO);
					if (!consentStatus.equals(OpdConfig.CONSNET_REQUEST_STATUS))
					{
						dao.generatConsent(vo, _userVO);
					}

					vo.setRequirmentTime(vo.getSysDate());
					if(vo.getTreatmentType()==null || vo.getTreatmentType().equalsIgnoreCase(""))
					{
					dao.savePatDrugTreatmentDetail(vo,pmode,_userVO);
					}
					else
					{
					dao.savePatDrugTreatmentDetail(vo,"2",_userVO);
					}
					String mapKey = vo.getDrugId() + "#" + vo.getFrequencyId() + "#" + vo.getDoseId();
					List scheduleLst = (List) drugScheduleMap.get(mapKey);
					drugScheduleMap.remove(mapKey);
					if (scheduleLst != null)
					{
						for (int j = 0; j < scheduleLst.size(); j++)
						{
							DrugSheduleDtlVO drugSheduleDtlVO = (DrugSheduleDtlVO) scheduleLst.get(j);

							drugSheduleDtlVO.setSerialNo(maxSlNoTreatInt.toString());
							drugSheduleDtlVO.setSheduleSerialNo(new Integer(j).toString());
							drugSheduleDtlVO.setEpisodeCode(vo.getEpisodeCode());
							drugSheduleDtlVO.setEpisodeVisitNo(vo.getEpisodeVisitNo());
							drugSheduleDtlVO.setPatCrNo(vo.getPatCrNo());

							drugScheduleDtlDAO.save(drugSheduleDtlVO, _userVO);

						}
					}

					maxSlNoTreatInt++;
						
			}
				
			for (EHRSection_TreatmentVO rvo : revokedDrugTreatmentDtl)
				{
				String pmode="1";
				if (rvo.getTodayVisitFlag().equals("yes"))
					{
						dao.updateLastTodayVisitRecord(rvo, _userVO);
					}
					//dao.savePatDrugTreatmentDetail(rvo, pmode, _userVO);
					maxSlNoTreatInt++;
				}
				
				
				
			
			

		/*	// update drug administration

			for (int i = 0; i < _lstDrugDtl.size(); i++)
			{
				EHRSection_TreatmentVO treatVo = _lstDrugDtl.get(i);

				DrugAdminDtlVO vo = new DrugAdminDtlVO();

				vo.setPatCrNo(treatVo.getPatCrNo());
				vo.setAdviceDate(treatVo.getEntryDate());
				vo.setItemId(treatVo.getDrugId());
				vo.setPatAdmNo(treatVo.getAdmissionNo());
				vo.setAdminFlag(OpdConfig.SCHEDULE);
				vo.setDrugBrandName(treatVo.getDrugBrandId());
				vo.setBeforeTimeLimit(treatVo.getCutOffBefore());
				vo.setAfterTimeLimit(treatVo.getCutOffAfter());

				drugAdminDtlDAO.updateDrugAdminDetail(vo, _userVO);
			}

			// saving administration detail
			for (int i = 0; i < drugAdminLst.size(); i++)
			{
				DrugAdminDtlVO vo = (DrugAdminDtlVO) drugAdminLst.get(i);
				int days = 0;
				if (vo.getEndDate()==null || vo.getEndDate().equals(""))
				{
					days = Integer.parseInt(vo.getNoOfDays());
				}
				else
				{
					DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
					Calendar calendar1 = Calendar.getInstance();
					Calendar calendar2 = Calendar.getInstance();
					calendar1.setTime(sinmpledf.parse(vo.getSysdate()));
					calendar2.setTime(sinmpledf.parse(vo.getEndDate()));
					long noOfDays = ((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000)) + 1;
					String advDays = String.valueOf(noOfDays);
					days = Integer.parseInt(advDays);
				}

				// Integer noOfDays=Integer.parseInt(vo.getNoOfDays());
				for (int j = 0; j < days; j++)
				{
					// setting schedule date
					String startdate = vo.getStartDate();
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
					
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(startdate));
					c.add(Calendar.DATE, j);
					String actualStartDate = sdf.format(c.getTime());
					vo.setScheduleDate(actualStartDate);
					String[] doseTime=vo.getDoseTime().split(":");
					String[] cutOffBefore=vo.getBeforeTimeLimit().split(":");
					String[] cutOffAfter=vo.getAfterTimeLimit().split(":");
					String doseBefHr,doseBefMin,doseAftHr,doseAftMin,doseTimeBefore,doseTimeAfter;
					int doseTimeBefHr=Integer.parseInt(doseTime[0])-Integer.parseInt(cutOffBefore[0]);
					int dosetimeBefMin=Integer.parseInt(doseTime[1])-Integer.parseInt(cutOffBefore[1]);
					int doseTimeAftHr=Integer.parseInt(doseTime[0])+Integer.parseInt(cutOffAfter[0]);
					int dosetimeAftMin=Integer.parseInt(doseTime[1])+Integer.parseInt(cutOffAfter[1]);
					if(doseTimeBefHr<10)
						doseBefHr= "0"+Integer.toString(doseTimeBefHr);
					else
						doseBefHr=Integer.toString(doseTimeBefHr);
					if(dosetimeBefMin<10)
						doseBefMin= "0"+Integer.toString(dosetimeBefMin);
					else
						doseBefMin=Integer.toString(dosetimeBefMin);
					doseTimeBefore = doseBefHr+":"+doseBefMin;
					if(doseTimeAftHr<10)
						doseAftHr= "0"+Integer.toString(doseTimeAftHr);
					else
						doseAftHr=Integer.toString(doseTimeAftHr);
					if(dosetimeAftMin<10)
						doseAftMin= "0"+Integer.toString(dosetimeAftMin);
					else
						doseAftMin=Integer.toString(dosetimeAftMin);
					doseTimeAfter = doseAftHr+":"+doseAftMin;
					c.setTime(sdfDateTime.parse(actualStartDate+" "+doseTimeAfter));
					Calendar cDatTime = Calendar.getInstance();
					cDatTime.setTime(sdfDateTime.parse(vo.getAdviceDate()));
					System.out.println("Calender Set Time:"+c);
					System.out.println("advice Set Time:"+cDatTime);
					System.out.println("set time return"+c.before(cDatTime));
					if(!c.before(cDatTime))
					{
						String drugStatus = drugAdminDtlDAO.getdrugStatus(vo, _userVO);
						if (drugStatus.equals("0"))
						{
							drugAdminDtlDAO.save(vo, _userVO);
						}
					}

				}
			}*/

			

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
	
	
	
	
	
	public String getdrugAdviceAlerts(EHRSection_TreatmentVO patDrugDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status;
		try
		{
			String pmode="2";
			HisDAO hisDAO = new HisDAO("EHR", "EHRSection_TreatmentBO");
			EHRSection_TreatmentDAO dao = new EHRSection_TreatmentDAO(tx);
			tx.begin();
			status = dao.getdrugAdviceAlerts(pmode,hisDAO,patDrugDtlVO, _userVO);
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return status;

	}

	
	public List treatmentDetailList(String patcrNO,String episodeCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstParameter = null;
		try
		{
			tx.begin();
			EHRSection_TreatmentDAO dao = new EHRSection_TreatmentDAO(tx);
			lstParameter=dao.getPrevPatDrugDetail(patcrNO, episodeCode, userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstParameter;
	}
	
	
	public List getParticularDrugListDtl(String drugListId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstDrugListDetail = null;
		try
		{
			tx.begin();
			EHRSection_TreatmentDAO dao = new EHRSection_TreatmentDAO(tx);
			lstDrugListDetail=dao.getParticularDrugListDetail(drugListId, userVO);			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstDrugListDetail;
	}

	
	
	
	
	
}
