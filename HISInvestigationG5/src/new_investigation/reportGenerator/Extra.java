package new_investigation.reportGenerator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.QueryConfig;
import new_investigation.reportGenerator.DataProcessing.DataHandler;
import new_investigation.reportGenerator.DataProcessing.PDFProcessing;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

public class Extra {
	
	
	 
	
	private List<ResultEntryVOGroupByValidatedBy> processTheWorkOrdersToGroups(List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry) {

		List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
		Map<String, Integer> fileGroupMap = new HashMap<String, Integer>();

		for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : workOrderGroupListForresultEntry) {
			// if group belongs to a group code

			{
				Log(Level.INFO,
						"processTheWorkOrdersToGroups::9::work order group formed is not associated to a group");
				// if work order group formed is not associated to a group
				if (resultEntryVOGroupByValidatedBy.getGroupMap() == null) {
					resultEntryVOGroupByValidatedBy.setGroupMap(new HashMap<String, String>());
				}

				processingTheWorkOrdersWhichbelongstogrouporNot(fileGroupMap, resultEntryVOGroupByValidatedByList,
						resultEntryVOGroupByValidatedBy);
			}

		}
		return resultEntryVOGroupByValidatedByList;
	}

	
	private ResultEntryVOGroupByValidatedBy processingTheWorkOrdersWhichbelongstogrouporNot(
			Map<String, Integer> fileGroupMap,
			List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList,
			ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
		Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot");
		for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
			// Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::1");
			// if work order is already printed in a file
			// if work order is very new to the printing
			{
				// Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::4");
				// work order has a printing template associated with it
				if (resultEntryVO.getPrintedWithTemplateID() != null
						&& resultEntryVO.getPrintedWithTemplateID().equals("") == false) {
					// Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::5");
					// find the group which has printing template associated with it
					findThePrintingTemplateId(fileGroupMap, resultEntryVO, 1, resultEntryVOGroupByValidatedByList);

				} else {
					// Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::6");
					// populating for the printing templateId to the normal and non normal ranges
					findThePrintingTemplateId(fileGroupMap, resultEntryVO, 2, resultEntryVOGroupByValidatedByList);
				}

			}

		}

		return resultEntryVOGroupByValidatedBy;
	}

	
	private ResultEntryVOGroupByValidatedBy findThePrintingTemplateId(Map<String, Integer> fileGroupMap,
			ResultEntryVO resultEntryVO2, int i,
			List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList) {
		ResultEntryVOGroupByValidatedBy groupByValidatedBy = null;
		// finding the resultEntryVO : work order in the file group map
		// if work order is containable in one of group in the filegroupMap
		upper: for (String key : fileGroupMap.keySet()) {
			ResultEntryVOGroupByValidatedBy groupingVO = resultEntryVOGroupByValidatedByList.get(fileGroupMap.get(key));
			
			groupByValidatedBy = groupingTheGivenWorkOrderToTheRegiseredPatientGroup(groupingVO, resultEntryVO2, resultEntryVOGroupByValidatedByList, i);

			if (groupByValidatedBy != null) {
				break upper;
			}

		}

		if (groupByValidatedBy == null) {
			ResultEntryVOGroupByValidatedBy groupingVO = new ResultEntryVOGroupByValidatedBy();
			DataHandler.populate(groupingVO, resultEntryVO2);
			resultEntryVOGroupByValidatedByList.add(groupingVO);
			groupByValidatedBy = groupingVO;
			if (groupingVO.getGroupMap() == null) {
				groupingVO.setGroupMap(new HashMap<String, String>());
			}

			if (groupingVO.getGroupCode() != null) {
				groupingVO.getGroupMap().put(groupingVO.getGroupCode(), groupingVO.getGroupCode());
			}

			groupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
			if (i == 1) {
				
				fileGroupMap.put(
						(groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy() + groupingVO.getPatCRNo()
						+ groupingVO.getPatEpisodeCode() + groupingVO.getPatVisitNo() + "#"
						+ groupingVO.getPrintedWithTemplateID() + "#" + groupingVO.getPrintingType()),
						resultEntryVOGroupByValidatedByList.size() - 1);
				
			} else {
				
				fileGroupMap.put(
						(groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy() + groupingVO.getPatCRNo()
						+ groupingVO.getPatEpisodeCode() + groupingVO.getPatVisitNo() + "#"
						+ groupingVO.getPrintedWithTemplateID() + "#" + groupingVO.getPrintingType()),
						resultEntryVOGroupByValidatedByList.size() - 1);
			}
		}

		// adding the workorder to the final object constructed
		groupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO2);

		return groupByValidatedBy;
	}


	private ResultEntryVOGroupByValidatedBy groupingTheGivenWorkOrderToTheRegiseredPatientGroup(
			ResultEntryVOGroupByValidatedBy groupingVO, ResultEntryVO resultEntryVO2,
			List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList, int i) {
		Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup");
		ResultEntryVOGroupByValidatedBy groupByValidatedBy = null;
		if (i == 1) {
			
			if ((groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy() + groupingVO.getPatCRNo()
			+ groupingVO.getPatEpisodeCode() + groupingVO.getPatVisitNo() + "#"
			+ groupingVO.getPrintedWithTemplateID() + "#" + groupingVO.getPrintingType())
					.equals(resultEntryVO2.getPatAdvisedBy() + resultEntryVO2.getResultValidatedBy()
					+ resultEntryVO2.getPatCRNo() + resultEntryVO2.getPatEpisodeCode()
					+ resultEntryVO2.getPatVisitNo() + "#" + resultEntryVO2.getPrintedWithTemplateID()
					+ "#" + resultEntryVO2.getPrintingType())) {
				boolean isContainable = true;
				if (groupingVO.getResultEntryVOListValidatedBy() != null) {
					for (ResultEntryVO resultEntryVO : groupingVO.getResultEntryVOListValidatedBy()) {
						if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode()
						+ resultEntryVO.getSessionId())
								.equals((resultEntryVO2.getLaboratoryCode() + resultEntryVO2.getTestCode()
								+ resultEntryVO2.getSessionId()))) {
							isContainable = false;
						}

					}
				}

				if (isContainable == true) {
					
					groupByValidatedBy = groupingVO;
				} else // if this template is not containable in the mapping group find the other group
					// which represents same printing template id
				{
					
					searchingloop: for (ResultEntryVOGroupByValidatedBy newGroup : resultEntryVOGroupByValidatedByList) {
						boolean isNewContainable = true;
						if ((newGroup.getPatAdvisedBy() + newGroup.getResultValidatedBy() + newGroup.getPatCRNo()
						+ newGroup.getPatEpisodeCode() + newGroup.getPatVisitNo() + "#"
						+ newGroup.getPrintedWithTemplateID() + "#" + newGroup.getPrintingType())
								.equals(resultEntryVO2.getPatAdvisedBy() + resultEntryVO2.getResultValidatedBy()
								+ resultEntryVO2.getPatCRNo() + resultEntryVO2.getPatEpisodeCode()
								+ resultEntryVO2.getPatVisitNo() + "#"
								+ resultEntryVO2.getPrintedWithTemplateID() + "#"
								+ resultEntryVO2.getPrintingType())) {
							if (groupingVO.getResultEntryVOListValidatedBy() != null) {
								for (ResultEntryVO resultEntryVO : newGroup.getResultEntryVOListValidatedBy()) {
									if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode()
									+ resultEntryVO.getSessionId()).equals(
											(resultEntryVO2.getLaboratoryCode() + resultEntryVO2.getTestCode()
											+ resultEntryVO2.getSessionId()))) {
										isNewContainable = false;
									}
								}
							}

							if (isNewContainable == true) {
								groupByValidatedBy = newGroup;
								break searchingloop;
							}

						}
					}

				}

				if (groupByValidatedBy == null)// if no containable group can be found
				{
					// Log(Level.INFO, "no containable group can be found");
					groupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
					// Log(Level.INFO, "new.... groupByValidatedBy created....");
					DataHandler.populate(groupByValidatedBy, resultEntryVO2);
					// check this part::XXXXXXXXXXXXXXXXXXXXXX
					resultEntryVOGroupByValidatedByList.add(groupByValidatedBy);
					
					groupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
					if (groupingVO.getGroupMap() == null) {
						groupingVO.setGroupMap(new HashMap<String, String>());
					}

					if (groupingVO.getGroupCode() != null) {
						groupingVO.getGroupMap().put(groupingVO.getGroupCode(), groupingVO.getGroupCode());
					}
				}

			}

		} else {
			if ((groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy() + groupingVO.getPatCRNo()
			+ groupingVO.getPatEpisodeCode() + groupingVO.getPatVisitNo() + "#"
			+ groupingVO.getPrintedWithTemplateID() + "#" + groupingVO.getPrintingType())
					.equals(resultEntryVO2.getPatAdvisedBy() + resultEntryVO2.getResultValidatedBy()
					+ resultEntryVO2.getPatCRNo() + resultEntryVO2.getPatEpisodeCode()
					+ resultEntryVO2.getPatVisitNo() + "#" + resultEntryVO2.getPrintedWithTemplateID()
					+ "#" + resultEntryVO2.getPrintingType())) {

				boolean isContainable = true;
				for (ResultEntryVO resultEntryVO : groupingVO.getResultEntryVOListValidatedBy()) {
					if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode() + resultEntryVO.getSessionId())
							.equals((resultEntryVO2.getLaboratoryCode() + resultEntryVO2.getTestCode()
							+ resultEntryVO2.getSessionId()))) {
						isContainable = false;
					}

				}

				if (isContainable == true) {
					groupByValidatedBy = groupingVO;

				} else {
					searchingloop1: for (ResultEntryVOGroupByValidatedBy newGroup : resultEntryVOGroupByValidatedByList) {
						boolean isNewContainable = true;
						if ((newGroup.getPatAdvisedBy() + newGroup.getResultValidatedBy() + newGroup.getPatCRNo()
						+ newGroup.getPatEpisodeCode() + newGroup.getPatVisitNo() + "#"
						+ newGroup.getPrintedWithTemplateID() + "#" + newGroup.getPrintingType())
								.equals(resultEntryVO2.getPatAdvisedBy() + resultEntryVO2.getResultValidatedBy()
								+ resultEntryVO2.getPatCRNo() + resultEntryVO2.getPatEpisodeCode()
								+ resultEntryVO2.getPatVisitNo() + "#"
								+ resultEntryVO2.getPrintedWithTemplateID() + "#"
								+ resultEntryVO2.getPrintingType())) {

							for (ResultEntryVO resultEntryVO : newGroup.getResultEntryVOListValidatedBy()) {
								if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode()
								+ resultEntryVO.getSessionId()).equals(
										(resultEntryVO2.getLaboratoryCode() + resultEntryVO2.getTestCode()
										+ resultEntryVO2.getSessionId()))) {
									isNewContainable = false;
								}
							}

							if (isNewContainable == true) {
								groupByValidatedBy = newGroup;
								break searchingloop1;
							}

						}
					}
				}

				if (groupByValidatedBy == null)// if no containable group can be found
				{
					groupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
					DataHandler.populate(groupByValidatedBy, resultEntryVO2);
			
					resultEntryVOGroupByValidatedByList.add(groupByValidatedBy);

					groupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());

				}

			}
		}

		return groupByValidatedBy;
	}

	
	
	private ResultEntryVOGroupByValidatedBy populateTheGroupingVOWithWorkOrdersPrintedWith(
			ResultEntryVOGroupByValidatedBy groupingVO) {
		Connection conn = null;
		try {
			conn = PGDataHelper.getInstance().getConnection();
			// conn.setAutoCommit(false);
			CallableStatement cstmt = conn.prepareCall(QueryConfig.P_GET_PDF_FILE_WORKORDERLISTDATA);
			cstmt.setString(1, groupingVO.getRequisitionTypeCode());
			cstmt.setString(2, groupingVO.getPdfFtpUrl());
			cstmt.registerOutParameter(3, Types.REF);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.execute();
			ResultEntryVO resultEntryVO = null;
			ResultSet rs = (ResultSet) cstmt.getObject(4);
			while (rs.next()) {
				boolean found = false;
				for (ResultEntryVO resultEntryVO2 : groupingVO.getResultEntryVOListValidatedBy()) {
					if (rs.getString(1).equals(resultEntryVO2.getRequisitionDNo())
							&& rs.getString(2).equals(resultEntryVO2.getSessionId())) {
						found = true;
					}
				}
				if (found == false) {
					// resultEntryVO=new ResultEntryVO(); CACHING IMPLEMENTATION
					resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"),
							rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
					resultEntryVO.setRequisitionDNo(rs.getString(1));
					resultEntryVO.setSessionId(rs.getString(2));
					resultEntryVO.setLaboratoryCode(rs.getString(3));
					resultEntryVO.setTestCode(rs.getString(4));
					if (groupingVO.getResultEntryVOListValidatedBy() == null) {
						groupingVO.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
					}

					// add only if workorder is not present in the
					groupingVO.getResultEntryVOListValidatedBy().add(resultEntryVO);
				}

			}

		} catch (Exception e) {
			// e.printStackTrace();
			Log(Level.SEVERE, e);
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (Exception e) {
				Log(Level.SEVERE, e);
				// e.printStackTrace();
			}
		}

		return groupingVO;
	}

	
	private static List getPdfsrequisitiondtlrequisitionwisesamplewise(
			ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List finall = new ArrayList();
		List lsttest = new ArrayList();
		List lsttest1 = new ArrayList();
		String query = QueryConfig.FETCH_PDFS_rquisitionno_samplecodewise;

		Connection conn = PGDataHelper.getInstance().getConnection();
		String filename = null;
		String crno = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, resultEntryVOGroupByValidatedByObj.getRequisitionNo());
			pstmt.setString(2, resultEntryVOGroupByValidatedByObj.getSampleNo());
			int i = 1;
			rs = pstmt.executeQuery();
			while (rs.next()) {

				filename = rs.getString(1);
				lsttest.add(filename);

				i++;
			}

			if (lsttest.size() > 0) {
				finall.addAll(lsttest);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return finall;
	}

	
//	Dublicate methods to support above methods==============================
	
	private void Log(Level level, String msg) {
		ServiceLogger.Log(PDFProcessing.class.getName(), level, msg);
	}

	private void Log(Level level, Exception e) {
		ServiceLogger.Log(PDFProcessing.class.getName(), level, e);
	}

	
	
	
	
	
}
