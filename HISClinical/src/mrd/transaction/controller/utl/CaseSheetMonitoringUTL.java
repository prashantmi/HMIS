package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CaseSheetLostFoundVO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mrd.MrdConfig;
import mrd.transaction.controller.data.CaseSheetMonitoringDATA;
import mrd.transaction.controller.fb.CaseSheetMonitoringFB;

public class CaseSheetMonitoringUTL extends ControllerUTIL
{

	public static boolean getDeptUnitList(CaseSheetMonitoringFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;

		try
		{
			setSysdate(request);
			String sys = (String) request.getSession().getAttribute(Config.SYSDATE);
			String time = sys.split(" ")[1];
			fb.setHiddenTimeHr(time.split(":")[0]);
			fb.setHiddenTimeMin(time.split(":")[1]);
			List lstUnit = CaseSheetMonitoringDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_DEPT_UNIT_LIST, lstUnit);

			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");

		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}

	public static boolean getWardOnBasisOfUnitCode(CaseSheetMonitoringFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		// HttpSession session = request.getSession();

		try
		{

			String unitCode = fb.getDepartmentUnitCode();

			listWard = CaseSheetMonitoringDATA.getWardOnBasisOfUnitCode(unitCode, getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			fb.setWardCode("-1");

			objStatus.add(Status.INPROCESS);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request, MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}

	/**
	 * get the patient case sheet status by crno who are either admitted or discharged
	 * 
	 * @param fb
	 * @param request
	 */
	public static void getPatientCaseSheetDtlByCrNo(CaseSheetMonitoringFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<CaseSheetDtlVO> caseSheetDtlVOList = null;
		try
		{
			// String unitCodeWithDiagCodeType = fb.getDepartmentUnitCode();
			// String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
			caseSheetDtlVOList = CaseSheetMonitoringDATA.getPatientCaseSheetDtlByCrNo(fb.getPatCrNo(), getUserVO(request));
			int i = 0;
			String arrCreateDate[] =new String[caseSheetDtlVOList.size()+1];
			
			if(caseSheetDtlVOList.size()>0 && caseSheetDtlVOList!=null)
			{
				for (CaseSheetDtlVO vo : caseSheetDtlVOList)
				{
					arrCreateDate[i] = vo.getCreationDate();
					i++;
				}
			}

			WebUTIL.setAttributeInSession(request, MrdConfig.CASE_SHEET_DETAIL_VO_LIST, caseSheetDtlVOList);
			objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.RECORDFOUND, "", e.getMessage());
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request, MrdConfig.CASE_SHEET_DETAIL_VO_LIST, null);
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}

	}

	/**
	 * get the list of the admit patient and their case sheet status
	 * 
	 * @param fb
	 * @param request
	 */

	public static void getPatientCaseSheetDtl(CaseSheetMonitoringFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<CaseSheetDtlVO> caseSheetDtlVOList = null;
		try
		{
			String deptUnitCode = fb.getDepartmentUnitCode();
			String wardCode = fb.getWardCode();
			caseSheetDtlVOList = CaseSheetMonitoringDATA.getPatientCaseSheetDtl(deptUnitCode, wardCode, getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.CASE_SHEET_DETAIL_VO_LIST, caseSheetDtlVOList);
			objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(request, MrdConfig.CASE_SHEET_DETAIL_VO_LIST, null);
			objStatus.add(Status.RECORDFOUND, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}

	}

	/**
	 * Update the case sheet status
	 * 
	 * @param fb
	 * @param request
	 */

	public static void updatePatientCaseSheetStatus(CaseSheetMonitoringFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		CaseSheetDtlVO caseSheetDtlVO = new CaseSheetDtlVO();
		List<CaseSheetDtlVO> caseSheetDtlVOList = new ArrayList<CaseSheetDtlVO>();
		CaseSheetLostFoundVO caseSheetLostFoundVO = new CaseSheetLostFoundVO();
		try
		{
			//String deptUnitCode = fb.getDepartmentUnitCode().replace("^", "#").split("#")[0];
			//String wardCode = fb.getWardCode();
			String primaryKey[] = fb.getSelectCRNo().split("#");
			caseSheetDtlVOList = (List) request.getSession().getAttribute(MrdConfig.CASE_SHEET_DETAIL_VO_LIST);
			HelperMethods.populate(caseSheetDtlVO, fb);
			HelperMethods.populate(caseSheetLostFoundVO, fb);

			for (int i = 0; i < caseSheetDtlVOList.size(); i++)
			{
				if (caseSheetDtlVOList.get(i).getPatAdmNo().equals(primaryKey[3]))
				{
					caseSheetDtlVO.setDepartmentCode(caseSheetDtlVOList.get(i).getDepartmentCode());
					caseSheetDtlVO.setDepartmentUnitCode(caseSheetDtlVOList.get(i).getDepartmentUnitCode());
					caseSheetDtlVO.setWardCode(caseSheetDtlVOList.get(i).getWardCode());
				}
			}

			caseSheetDtlVO.setPatAdmNo(primaryKey[3]);
			caseSheetDtlVO.setCaseSheetId(primaryKey[4]);
			caseSheetLostFoundVO.setPatAdmNo(primaryKey[3]);
			caseSheetLostFoundVO.setCaseSheetId(primaryKey[4]);
			// caseSheetLostFoundVO.setDepartmentUnitCode(deptUnitCode);

			// if case sheet is lost
			if (fb.getUpdateType().equals("0"))
			{
				String lastSeenDateTime = "";
				String lostDate = fb.getLostDate() + " " + fb.getLostTimeHr() + ":" + fb.getLostTimeMin();
				if (!fb.getLastSeenDate().equals("")) lastSeenDateTime = fb.getLastSeenDate() + " " + fb.getLastSeenTimeHr() + ":"
						+ fb.getLastSeenTimeMin();
				caseSheetLostFoundVO.setLostDate(lostDate);
				caseSheetLostFoundVO.setLastSeenDateTime(lastSeenDateTime);
				caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_LOST);
			}
			// if case sheet is found
			if (fb.getUpdateType().equals("1"))
			{
				String foundDate = "";
				if (!fb.getFoundDate().equals("")) foundDate = fb.getFoundDate() + " " + fb.getFoundTimeHr() + ":" + fb.getFoundTimeMin();
				caseSheetLostFoundVO.setFoundDate(foundDate);
			}
			// if duplicate case sheet is created
			if (fb.getUpdateType().equals("2"))
			{
				caseSheetDtlVO.setIsDuplicate(MrdConfig.CASE_SHEET_DUPLICATE);
			}
			caseSheetLostFoundVO.setDepartmentUnitCode(caseSheetDtlVO.getDepartmentUnitCode());
			caseSheetLostFoundVO.setWardCode(caseSheetDtlVO.getWardCode());

			CaseSheetMonitoringDATA.updatePatientCaseSheetStatus(caseSheetDtlVO, caseSheetLostFoundVO, fb.getUpdateType(), getUserVO(request));
			objStatus.add(Status.DONE, "", "Case Sheet Updated Successfully");
			objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.NEW);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}

	}

	/**
	 * get the detail of the lost or found of the case sheet of selected patient
	 * 
	 * @param fb
	 * @param request
	 */
	public static void getPatientCaseSheetLostFoundDtl(CaseSheetMonitoringFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		CaseSheetLostFoundVO caseSheetLostFoundVO = new CaseSheetLostFoundVO();
		List<CaseSheetDtlVO> caseSheetDtlVOList = new ArrayList<CaseSheetDtlVO>();
		try
		{
			caseSheetDtlVOList = (List) request.getSession().getAttribute(MrdConfig.CASE_SHEET_DETAIL_VO_LIST);
			String primaryKey[] = fb.getSelectCRNo().split("#");
			for(CaseSheetDtlVO caseSheetDtlVO : caseSheetDtlVOList)
			{
				fb.setHideDuplicateLabel("false");
				if (caseSheetDtlVO.getPatAdmNo().equals(primaryKey[3]) && caseSheetDtlVO.getIsDuplicateValue().equals("1")
						&& !caseSheetDtlVO.getCaseSheetStatus().equals(MrdConfig.CASE_SHEET_LOST))
				{
					fb.setHideDuplicateLabel("true");
					break;
				}
				if(caseSheetDtlVO.getPatAdmNo().equals(primaryKey[3]) && caseSheetDtlVO.getCaseSheetId().equals(primaryKey[4]) )
				{
					fb.setCreationDate(caseSheetDtlVO.getCreationDate());
				}
			}
			
			caseSheetLostFoundVO.setPatAdmNo(primaryKey[3]);
			caseSheetLostFoundVO.setCaseSheetId(primaryKey[4]);
			caseSheetLostFoundVO = CaseSheetMonitoringDATA.getPatientCaseSheetLostFoundDtl(caseSheetLostFoundVO, getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.CASE_SHEET_LOST_FOUND_VO, caseSheetLostFoundVO);
			objStatus.add(Status.TRANSINPROCESS);

		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(request, MrdConfig.CASE_SHEET_LOST_FOUND_VO, null);
			objStatus.add(Status.TRANSINPROCESS);
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

}
