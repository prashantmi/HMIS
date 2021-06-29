package mrd.reports.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.reports.dao.MrdReportDAO;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;

public class MrdReportBO
{
	// Getting Pediatric Immunization report Data
	public List<MrdReportDataVO> getPediatricImmunReportData(MrdReportVO voReport_p, UserVO voUser_p)
	{
		List<MrdReportDataVO> reportData = null;
		HisDAO hisDAO = new HisDAO("MRD", "MrdReportBO");
		try
		{
			MrdReportDAO objDAO = new MrdReportDAO();
			// Fetching Pediatric Immunization report Data
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "0", voReport_p, voUser_p);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("MrdReportBO.getPediatricImmunReportData()::HisRecordNotFoundException -> " + e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("MrdReportBO.getPediatricImmunReportData()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("MrdReportBO.getPediatricImmunReportData()::HisApplicationExecutionException -> " + e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("MrdReportBO.getPediatricImmunReportData()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdReportBO.getPediatricImmunReportData()::HisApplicationExecutionException -> " + e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return reportData;
	}

	// Getting Deliveries and Cu-T Insertion Report Data
	public Map<String,List<MrdReportDataVO>> getDelivCuTInsertionReportData(MrdReportVO voReport_p, UserVO voUser_p,String strServiceType)
	{
		List<MrdReportDataVO> reportData = null;
		Map<String, List<MrdReportDataVO>> mapReport = new HashMap<String, List<MrdReportDataVO>>();
		HisDAO hisDAO = new HisDAO("MRD", "MrdReportBO");
		try
		{
			MrdReportDAO objDAO = new MrdReportDAO();
			if(strServiceType.equals("1"))  // For Service Type Delivery
			{
				// Fetching Deliveries Count
				reportData = objDAO.getAdmittedCountReportData(hisDAO, "0", voReport_p, voUser_p);
				mapReport.put("D", reportData);
			}
			else if(strServiceType.equals("2"))  // For Service Type Cu-T Insertion
			{
				// Fetching Cu-T Insertion Count
				reportData = objDAO.getServiceAreaCountReportData(hisDAO, "1", voReport_p, voUser_p);
				mapReport.put("C", reportData);
			}
			else // For All Service Type
			{
				// Fetching Deliveries Count
				reportData = objDAO.getAdmittedCountReportData(hisDAO, "0", voReport_p, voUser_p);
				mapReport.put("D", reportData);
				
				// Fetching Cu-T Insertion Count
				reportData = objDAO.getServiceAreaCountReportData(hisDAO, "1", voReport_p, voUser_p);
				mapReport.put("C", reportData);
				
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("MrdReportBO.getDelivCuTInsertionReportData()::HisRecordNotFoundException -> " + e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("MrdReportBO.getDelivCuTInsertionReportData()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("MrdReportBO.getDelivCuTInsertionReportData()::HisApplicationExecutionException -> " + e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("MrdReportBO.getDelivCuTInsertionReportData()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdReportBO.getDelivCuTInsertionReportData()::HisApplicationExecutionException -> " + e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return mapReport;
	}

	// Getting Hospital Performance Report Data
	public Map<String,List<MrdReportDataVO>> getHospitalPerformanceReportData(MrdReportVO voReport_p, UserVO voUser_p)
	{
		List<MrdReportDataVO> reportData = null;
		Map<String, List<MrdReportDataVO>> mapReport = new HashMap<String, List<MrdReportDataVO>>();
		HisDAO hisDAO = new HisDAO("MRD", "MrdReportBO");
		try
		{
			MrdReportDAO objDAO = new MrdReportDAO();
			// 1. Fetching OPD Counts
			reportData = objDAO.getOPDRegCountReportData(hisDAO, "0", voReport_p, voUser_p);
			mapReport.put("1", reportData);
			// 2. Fetching IPD Count
			reportData = objDAO.getAdmittedCountReportData(hisDAO, "1", voReport_p, voUser_p);
			mapReport.put("2", reportData);
			// 3. Fetching Dental Counts
			reportData = objDAO.getOPDRegCountReportData(hisDAO, "1", voReport_p, voUser_p);
			mapReport.put("3", reportData);
			// 4. Lab Tests
			reportData = objDAO.getInvestigationCountReportData(hisDAO, "0", voReport_p, voUser_p);
			mapReport.put("4", reportData);
			// 5. Radiology
			List<MrdReportDataVO>  lstReportData5 = new ArrayList<MrdReportDataVO>();
			MrdReportDataVO voXRayGeneral = new MrdReportDataVO();
			voXRayGeneral.setStrProcedureName("X-Ray (General)");
			reportData = objDAO.getInvestigationCountReportData(hisDAO, "1", voReport_p, voUser_p);
			voXRayGeneral.setStrCount(reportData.get(0).getStrCount());
			lstReportData5.add(voXRayGeneral);
			
			MrdReportDataVO voXRayDental = new MrdReportDataVO();
			voXRayDental.setStrProcedureName("X-Ray Dental");
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "8", voReport_p, voUser_p);
			voXRayDental.setStrCount(reportData.get(0).getStrCount());
			lstReportData5.add(voXRayDental);
			
			MrdReportDataVO voProcUSG = new MrdReportDataVO();
			voProcUSG.setStrProcedureName("USG");
			reportData = objDAO.getInvestigationCountReportData(hisDAO, "2", voReport_p, voUser_p);
			voProcUSG.setStrCount(reportData.get(0).getStrCount());
			lstReportData5.add(voProcUSG);
			
			MrdReportDataVO voSpecialInvestigation = new MrdReportDataVO();
			voSpecialInvestigation.setStrProcedureName("Special Investigation");
			reportData = objDAO.getInvestigationCountReportData(hisDAO, "3", voReport_p, voUser_p);
			voSpecialInvestigation.setStrCount(reportData.get(0).getStrCount());
			lstReportData5.add(voSpecialInvestigation);

			mapReport.put("5", lstReportData5);
			
			// 6. ECG
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "7", voReport_p, voUser_p);
			mapReport.put("6", reportData);
			// 7. Lab Tests
			reportData = objDAO.getOTCountReportData(hisDAO, "0", voReport_p, voUser_p);
			mapReport.put("7", reportData);
			// 8. Family Welfare Counts
			List<MrdReportDataVO>  lstReportData8 = new ArrayList<MrdReportDataVO>();
			MrdReportDataVO voDeliveries = new MrdReportDataVO();
			voDeliveries.setStrProcedureName("Deliveries");
			reportData = objDAO.getAdmittedCountReportData(hisDAO, "0", voReport_p, voUser_p);
			voDeliveries.setStrCount(reportData.get(0).getStrCount());
			lstReportData8.add(voDeliveries);
			
			MrdReportDataVO voProcLSCS = new MrdReportDataVO();
			voProcLSCS.setStrProcedureName("LSCS");
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "3", voReport_p, voUser_p);
			voProcLSCS.setStrCount(reportData.get(0).getStrCount());
			lstReportData8.add(voProcLSCS);

			MrdReportDataVO voProcSterilizationMale = new MrdReportDataVO();
			voProcSterilizationMale.setStrProcedureName("Sterilization Male");
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "4", voReport_p, voUser_p);
			voProcSterilizationMale.setStrCount(reportData.get(0).getStrCount());
			lstReportData8.add(voProcSterilizationMale);

			MrdReportDataVO voProcMTP = new MrdReportDataVO();
			voProcMTP.setStrProcedureName("MTP");
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "5", voReport_p, voUser_p);
			voProcMTP.setStrCount(reportData.get(0).getStrCount());
			lstReportData8.add(voProcMTP);

			MrdReportDataVO voProcCuTInsertion = new MrdReportDataVO();
			voProcCuTInsertion.setStrProcedureName("Cu T Insertion");
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "1", voReport_p, voUser_p);
			voProcCuTInsertion.setStrCount(reportData.get(0).getStrCount());
			lstReportData8.add(voProcCuTInsertion);
			
			MrdReportDataVO voProcAntenatalCheckup = new MrdReportDataVO();
			voProcAntenatalCheckup.setStrProcedureName("Antenatal Checkup");
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "6", voReport_p, voUser_p);
			voProcAntenatalCheckup.setStrCount(reportData.get(0).getStrCount());
			lstReportData8.add(voProcAntenatalCheckup);

			mapReport.put("8", lstReportData8);

			// 9. Immunizations
			reportData = objDAO.getServiceAreaCountReportData(hisDAO, "9", voReport_p, voUser_p);
			mapReport.put("9", reportData);

			// 10. Category
			// No Logic Given
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("MrdReportBO.getHospitalPerformanceReportData()::HisRecordNotFoundException -> " + e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("MrdReportBO.getHospitalPerformanceReportData()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("MrdReportBO.getHospitalPerformanceReportData()::HisApplicationExecutionException -> " + e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("MrdReportBO.getHospitalPerformanceReportData()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdReportBO.getHospitalPerformanceReportData()::HisApplicationExecutionException -> " + e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return mapReport;
	}
}

