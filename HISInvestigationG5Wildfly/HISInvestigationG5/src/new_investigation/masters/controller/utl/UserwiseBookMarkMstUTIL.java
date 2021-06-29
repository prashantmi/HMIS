package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import hisglobal.utility.Entry;
import new_investigation.masters.controller.data.LabConfigratorMstDATA;
import new_investigation.masters.controller.data.LabTestSampleGlobalMstDATA;
import new_investigation.masters.controller.data.UserwiseBookMarkMstDATA;
import new_investigation.masters.controller.fb.LabConfigratorMstFB;
import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.masters.controller.fb.UserwiseBookMarkMstFB;
import new_investigation.vo.LabConfigratorMstVO;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.UserwiseBookMarkMstVO;
import new_investigation.InvestigationConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class UserwiseBookMarkMstUTIL extends ControllerUTIL implements MasterInterface {

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}

	public String getButtons() {
		StringBuilder br = new StringBuilder();
		br.append(
				"<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record'  border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		//br.append(
			//	"<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;'; title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append(
				"<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;'; title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append(
				"<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append(
				"<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		br.append(
				"<img src='../../hisglobal/images/btn-ccl.png' style='cursor:pointer;' title='cancel page'  border=0  tabindex='1' onKeyPress='cancelFunc();'  onClick='cancelFunc();'>");
		return br.toString();
	}

	public String[] getColsWidth() {
		return null;
	}

	public String[] getColumnHeader() {
		String[] columnHdr = { "Bookmark Name", "Bookmark Type", "User Name", "DeptUnit Name", "Lab Name", "Test Name",
				"Test Group Name" };
		return columnHdr;
	}

	public String[] getComboHeader() {
		String[] strComboHdr = { "1", "Record Status" };
		return strComboHdr;
	}

	public String[] getComboQuery() {
		String comboQuery[] = new String[1];
		comboQuery[0] = "1^Active";
		// #2^InActive";
		return comboQuery;
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1, "DELETE.UserBookMark_Mst").replace("?",
				seatId);

		// by prashant
		if (httpSession.getAttribute("chk") != null && !httpSession.getAttribute("chk").equals(null)) {
			String chk = (String) httpSession.getAttribute("chk");
			// String qw[] = chk.replace('$', '@').split("@");
			String qw[] = chk.replace('$', '#').split("#");
			String istestgroup[] = new String[qw.length - 1];

			for (int i = 0; i < qw.length - 1; i++) {
				String qw1[] = qw[i].split("@");
				for (int j = 0; j < qw1.length; j++) {
					if (qw1[j].equals("0")) {
						istestgroup[i] = "0";
					} else if (qw1[j].equals("1")) {
						istestgroup[i] = "1";
					}
				}
			}
			int count1 = 0;
			int count2 = 0;
			for (int i = 0; i < istestgroup.length; i++) {
				if (istestgroup[i].equals("0")) {
					count1++;
				} else if (istestgroup[i].equals("1")) {
					count2++;
				}
			}

			// check if istestgroup = 0 or 1 and write query for sequence or groupcode
			if (count1 == 0 || count2 == 0) {
				if (count2 == 0) {
					deleteQuery[0] = deleteQuery[0].concat(" where "
							+ new_investigation.qryHandler_investigation.getQuery(1, "DELETE.UserBookMark_Mst.COND"));
				} else {
					deleteQuery[0] = deleteQuery[0].concat(" where " + new_investigation.qryHandler_investigation
							.getQuery(1, "DELETE.UserBookMark_Mst.COND.Group"));
				}
			} else {
				String deleteQuery1[] = new String[2];
				String seatId1 = httpSession.getAttribute("SEATID").toString();

				deleteQuery1[0] = new_investigation.qryHandler_investigation.getQuery(1, "DELETE.UserBookMark_Mst")
						.replace("?", seatId);
				deleteQuery1[0] = deleteQuery[0].concat(" where "
						+ new_investigation.qryHandler_investigation.getQuery(1, "DELETE.UserBookMark_Mst.COND"));
				deleteQuery1[1] = deleteQuery[0].concat(" where "
						+ new_investigation.qryHandler_investigation.getQuery(1, "DELETE.UserBookMark_Mst.COND.Group"));
				return deleteQuery1;
			}
		}
		return deleteQuery;
	}

	public String getJsFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMainQuery(String[] cmb) {

		// UserVO userVO;
		StringBuffer brMainQuery = new StringBuffer();
		List<String> list = new ArrayList<String>();
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String seatId = httpSession.getAttribute("SEATID").toString();
		list.add(Config.IS_VALID_ACTIVE);
		list.add(hosCode);
		list.add(seatId);

		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1, "UserwiseBookMarkMst.main"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
			}
		}

		return brMainQuery.toString();
	}

	public String getMasterName() {
		String masterName = "User Bookmark Master";
		return masterName;
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "a.hivtstr_bookmark_name" };
		return orderBy;
	}

	public int getPage_per_block() {
		return 10;
	}

	public int getRecord_per_page() {
		return 10;
	}

	public String[] getSearchField() {
		String strSearchField[] = { "1", " UPPER(a.hivtstr_bookmark_name)", "5", "c.gstr_lab_name" };
		return strSearchField;
	}

	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Bookmark Name");
		viewHdr.add("D");

		viewHdr.add("Bookmark Type");
		viewHdr.add("D");

		viewHdr.add("User Name");
		viewHdr.add("D");

		viewHdr.add("Dept Unit Name");
		viewHdr.add("D");

		viewHdr.add("Lab Name");
		viewHdr.add("D");

		viewHdr.add("Test/Test Group Wise");
		viewHdr.add("D");

		viewHdr.add("Test Name");
		viewHdr.add("D");

		viewHdr.add("Test Group Name");
		viewHdr.add("D");

		return viewHdr;
	}

	public String getViewQuery() {
		return new_investigation.qryHandler_investigation.getQuery(1, "VIEW.UserBookMark_Mst");
	}

	public String isGlobal() {
		return "null";

	}

	public boolean reportInterFaceRequired() {
		return false;
	}

	public static boolean getEssential(UserwiseBookMarkMstFB fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp = new HashMap();
			mp = UserwiseBookMarkMstDATA.getEssential(UserwiseBookMarkMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, UserwiseBookMarkMstVO);

			objStatus.add(Status.NEW);
		} catch (HisRecordNotFoundException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		} catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}

		finally {
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static StringBuffer getUnit(UserwiseBookMarkMstFB objFB, HttpServletRequest request, String deptCode) {
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		List lstUnit = new ArrayList();
		boolean flag = true;
		try {
			UserVO userVO = ControllerUTIL.getUserVO(request);
			System.out.println("departmentUnitCode :" + deptCode);
			lstUnit = UserwiseBookMarkMstDATA.getUnit(deptCode, userVO);
			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_UNIT_COMBO, lstUnit);
			System.out.println("lstunit 0 :" + lstUnit.get(0));
			if (lstUnit != null && lstUnit.size() > 0) {
				sbAjaxRes.append("[");
				for (int i = 0; i < lstUnit.size(); i++) {
					String unit = lstUnit.get(i).toString();
					String[] unitDetail = unit.split(",");
					String deptUnitCode = unitDetail[0].substring(1, unitDetail[0].length());
					String deptUnitName = unitDetail[1].substring(0, (unitDetail[1].length() - 1));
					System.out.println("deptUnitCode :" + deptUnitCode + "  deptUnitName :" + deptUnitName);
					sbAjaxRes.append("{");
					sbAjaxRes.append("deptUnitCode");
					sbAjaxRes.append(":");
					sbAjaxRes.append("\'");
					sbAjaxRes.append(deptUnitName);
					sbAjaxRes.append("\'");
					sbAjaxRes.append(",");
					sbAjaxRes.append("deptUnitName");
					sbAjaxRes.append(":");
					sbAjaxRes.append("\'");
					sbAjaxRes.append(deptUnitCode);
					sbAjaxRes.append("\'");
					sbAjaxRes.append("}");
					sbAjaxRes.append(",");
				}
				if (lstUnit.size() > 0)
					sbAjaxRes.delete(sbAjaxRes.length() - 1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		} catch (HisRecordNotFoundException e) {
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_UNIT_COMBO, lstUnit);
			// objFB.setShowRommList("0");
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
		return sbAjaxRes;
	}

	public static StringBuffer getTest(UserwiseBookMarkMstFB objFB, HttpServletRequest request, String labCode,
			String bookmarkCode, String userId) {
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		List lstUnit = new ArrayList();
		boolean flag = true;
		try {
			UserVO userVO = ControllerUTIL.getUserVO(request);
			UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
			ControllerUTIL.setSysdate(request);
			HelperMethods.populate(UserwiseBookMarkMstVO, objFB);
			System.out.println("labCode :" + labCode);
			UserwiseBookMarkMstVO.setLabCode(labCode);
			UserwiseBookMarkMstVO.setBookmarkCode(bookmarkCode);
			UserwiseBookMarkMstVO.setUserId(userId);
			lstUnit = UserwiseBookMarkMstDATA.getTest(UserwiseBookMarkMstVO, userVO);
			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_TEST_COMBO, lstUnit);
			// System.out.println("lstunit 0 :"+lstUnit.get(0));
			if (lstUnit != null && lstUnit.size() > 0) {
				sbAjaxRes.append("[");
				for (int i = 0; i < lstUnit.size(); i++) {

					/*
					 * //commented by prashant String unit=lstUnit.get(i).toString(); String []
					 * unitDetail= unit.split(","); String testName= unitDetail[0].substring(1,
					 * unitDetail[0].length()); String testCode= unitDetail[1].substring(0,
					 * (unitDetail[1].length()-1));
					 */

					// added by prashant
					String testName = ((Entry) lstUnit.get(i)).getLabel();
					String testCode = ((Entry) lstUnit.get(i)).getValue();
					System.out.println("testCode :" + testCode + "  testName :" + testName);

					sbAjaxRes.append("{");
					sbAjaxRes.append("testName");
					sbAjaxRes.append(":");
					sbAjaxRes.append("\'");
					sbAjaxRes.append(testName);
					sbAjaxRes.append("\'");
					sbAjaxRes.append(",");
					sbAjaxRes.append("testCode");
					sbAjaxRes.append(":");
					sbAjaxRes.append("\'");
					sbAjaxRes.append(testCode);
					sbAjaxRes.append("\'");
					sbAjaxRes.append("}");
					sbAjaxRes.append(",");
				}
				if (lstUnit.size() > 0)
					sbAjaxRes.delete(sbAjaxRes.length() - 1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		} catch (HisRecordNotFoundException e) {
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_UOM_COMBO, lstUnit);
			// objFB.setShowRommList("0");
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
		return sbAjaxRes;
	}

	public static void addOpinionRow(UserwiseBookMarkMstFB fb, HttpServletRequest request) {
		Status objStatus = new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
		HttpSession session = request.getSession();
		UserwiseBookMarkMstVO[] opinionReqVO1 = null;
		UserwiseBookMarkMstVO[] opinionReqVO2 = null;
		UserwiseBookMarkMstVO[] opinionReqVO3 = null;
		List lstOpinion = null;
		List lstTest = null;
		List testGroup = null;
		String labName = "";
		String testName = "";
		String testGroupName = "";
		String testCode = fb.getTestCode();

		// by prashant
		List<UserwiseBookMarkMstVO> lstTestByGroup = new ArrayList<UserwiseBookMarkMstVO>();
		try {
			lstOpinion = (List) session.getAttribute(InvestigationConfig.LIST_LAB_COMBO);
			lstTest = (List) session.getAttribute(InvestigationConfig.LIST_TEST_COMBO);

			if (lstOpinion == null && lstTest == null) {
				if (fb.getIsTestGroup().equals("0")) {
					UserwiseBookMarkMstVO.setTestCode(testCode);
					UserwiseBookMarkMstDATA.getTestName(UserwiseBookMarkMstVO, userVO);
					testName = UserwiseBookMarkMstVO.getTestName();
				}
			}
			if (lstOpinion != null && lstTest != null) {
				for (int i = 0; i < lstOpinion.size(); i++) {
					Entry ent = (Entry) lstOpinion.get(i);
					if (ent.getValue().equalsIgnoreCase(fb.getLabCode())) {
						labName = ent.getLabel();
						break;
					}
				}
				if (fb.getIsTestGroup().equals("0")) {
					/*
					 * for(int i=0;i<lstTest.size();i++) { Entry ent=(Entry)lstOpinion.get(i); if(
					 * ent.getValue().equalsIgnoreCase(fb.getTestCode())) { testName=ent.getLabel();
					 * break; } }
					 */
					UserwiseBookMarkMstVO.setTestCode(testCode);
					UserwiseBookMarkMstDATA.getTestName(UserwiseBookMarkMstVO, userVO);
					testName = UserwiseBookMarkMstVO.getTestName();
				}
				if (fb.getIsTestGroup().equals("1")) {
					testGroup = (List) session.getAttribute(InvestigationConfig.LIST_TEST_GROUP_COMBO);

					for (int i = 0; i < testGroup.size(); i++) {
						Entry ent = (Entry) testGroup.get(i);
						if (ent.getValue().equalsIgnoreCase(fb.getGroupCode().trim())) {
							testGroupName = ent.getLabel();
							break;
						}
					}
				}
			}

			// by prashant to get all the test corresponding to group and also check if any
			// test exist in bookmark already
			if (fb.getIsTestGroup().equals("1")) {
				UserwiseBookMarkMstVO.setGroupCode(fb.getGroupCode());
				lstTestByGroup = UserwiseBookMarkMstDATA.getTestByGroup(UserwiseBookMarkMstVO, userVO);
				if (lstTestByGroup.isEmpty()) {
					objStatus.add(Status.ERROR_AE, "", "The Group \"" + testGroupName + "\" Contains No Test");
					return;
				}
				if (lstTestByGroup.get(0).getIsDuplicate() != null
						&& lstTestByGroup.get(0).getIsDuplicate().equals("1")) {
					String duplicateTest = " ";
					for (int i = 0; i < lstTestByGroup.size(); i++) {
						duplicateTest = duplicateTest.concat(lstTestByGroup.get(i).getTestName() + "  ");
					}
					objStatus.add(Status.ERROR_AE, "", "The Group \"" + testGroupName + "\" Contains Test \""
							+ duplicateTest
							+ "\" Which is Already Added in the Bookmark <br> Please Remove These Tests And Try Again");
					return;
				}
			}

			opinionReqVO1 = (UserwiseBookMarkMstVO[]) session.getAttribute(InvestigationConfig.ESSENTIAL_ALL_TEST_VO);

			// by prashant
			if (opinionReqVO1 == null) {

				if (fb.getIsTestGroup().equals("0")) {

					opinionReqVO2 = new UserwiseBookMarkMstVO[1];
					opinionReqVO2[0] = new UserwiseBookMarkMstVO();
					opinionReqVO2[0].setLabCode(fb.getLabCode());
					opinionReqVO2[0].setLabName(labName);
					opinionReqVO2[0].setIsTestGroup("0");
					opinionReqVO2[0].setGroupCode("-1");

					opinionReqVO2[0].setTestCode(fb.getTestCode());
					opinionReqVO2[0].setTestName(testName);

				}
				if (fb.getIsTestGroup().equals("1")) {
					opinionReqVO2 = new UserwiseBookMarkMstVO[lstTestByGroup.size()];
					for (int i = 0; i < lstTestByGroup.size(); i++) {

						opinionReqVO2[i] = new UserwiseBookMarkMstVO();
						opinionReqVO2[i].setLabCode(fb.getLabCode());
						opinionReqVO2[i].setLabName(labName);
						opinionReqVO2[i].setIsTestGroup("1");

						opinionReqVO2[i].setTestCode(lstTestByGroup.get(i).getTestCode());
						opinionReqVO2[i].setTestName(lstTestByGroup.get(i).getTestName());
						opinionReqVO2[i].setGroupCode(fb.getGroupCode());
						opinionReqVO2[i].setGroupName(testGroupName);
					}
				}
				WebUTIL.setAttributeInSession(request, InvestigationConfig.ESSENTIAL_ALL_TEST_VO, opinionReqVO2);
			} else {

				if (fb.getIsTestGroup().equals("0")) {
					opinionReqVO3 = new UserwiseBookMarkMstVO[opinionReqVO1.length + 1];

					int i = 0;
					for (; i < opinionReqVO1.length; i++) {
						opinionReqVO3[i] = opinionReqVO1[i];
					}
					opinionReqVO3[i] = new UserwiseBookMarkMstVO();
					opinionReqVO3[i].setLabCode(fb.getLabCode());
					opinionReqVO3[i].setLabName(labName);
					opinionReqVO3[i].setIsTestGroup("0");
					opinionReqVO3[i].setGroupCode("-1");

					opinionReqVO3[i].setTestCode(fb.getTestCode());
					opinionReqVO3[i].setTestName(testName);
				}
				if (fb.getIsTestGroup().equals("1")) {

					int commoncount = 0;
					String common = "";
					for (int i = 0; i < opinionReqVO1.length; i++) {
						for (int j = 0; j < lstTestByGroup.size(); j++) {
							if (opinionReqVO1[i].getTestCode().equals(lstTestByGroup.get(j).getTestCode())) {
								commoncount++;
								common += opinionReqVO1[i].getTestName() + ", ";
							}
						}
					}
					if (commoncount > 0) {
						objStatus.add(Status.ERROR_AE, "", "The Group \"" + testGroupName + "\" Contains Test \""
								+ common
								+ "\" Which You Have Already Selected <br> Please Remove This Test And Try Again");
						return;
					}

					opinionReqVO3 = new UserwiseBookMarkMstVO[opinionReqVO1.length + lstTestByGroup.size()];

					for (int i = 0; i < opinionReqVO1.length; i++) {
						opinionReqVO3[i] = opinionReqVO1[i];
					}

					for (int i = opinionReqVO1.length; i < opinionReqVO3.length; i++) {
						int j = i - opinionReqVO1.length;
						opinionReqVO3[i] = new UserwiseBookMarkMstVO();
						opinionReqVO3[i].setLabCode(fb.getLabCode());
						opinionReqVO3[i].setLabName(labName);
						opinionReqVO3[i].setIsTestGroup("1");

						opinionReqVO3[i].setTestCode(lstTestByGroup.get(j).getTestCode());
						opinionReqVO3[i].setTestName(lstTestByGroup.get(j).getTestName());
						opinionReqVO3[i].setGroupCode(fb.getGroupCode());
						opinionReqVO3[i].setGroupName(testGroupName);
					}
				}
				WebUTIL.setAttributeInSession(request, InvestigationConfig.ESSENTIAL_ALL_TEST_VO, opinionReqVO3);
			}

			if (fb.getIsTestGroup().equals("0")) {
				List newList1 = new ArrayList(lstTest);
				newList1 = (List) WebUTIL.removeEntriesfromOptions(newList1, fb.getTestCode());
				WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_TEST_COMBO, newList1);
			}

			// added by prashant to remove test and group from dropdown list
			if (fb.getIsTestGroup().equals("1")) {
				List newList2 = new ArrayList(testGroup);
				newList2 = (List) WebUTIL.removeEntriesfromOptions(newList2, fb.getGroupCode());
				WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_TEST_GROUP_COMBO, newList2);

				Collection newEntryCollection = new ArrayList(lstTest);
				Collection toRemove = new ArrayList();
				for (int i = 0; i < lstTestByGroup.size(); i++) {
					Entry objEntry = new Entry(null, lstTestByGroup.get(i).getTestCode());
					toRemove.add(objEntry);
				}

				newEntryCollection.removeAll(toRemove);
				List newList1 = new ArrayList(newEntryCollection);
				WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_TEST_COMBO, newList1);
			}

			List newList = new ArrayList(lstOpinion);
			List lstTest1 = (List) session.getAttribute(InvestigationConfig.LIST_TEST_COMBO);
			List testGroup1 = (List) session.getAttribute(InvestigationConfig.LIST_TEST_GROUP_COMBO);
			if (lstTest1 == null || testGroup1 == null) {
				newList = (List) WebUTIL.removeEntriesfromOptions(newList, fb.getLabCode());
				WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_LAB_COMBO, newList);
			}

			objStatus.add(Status.TRANSINPROCESS);
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		/*
		 * catch(HisException e) { e.printStackTrace();
		 * objStatus.add(Status.ERROR,"","Error"); }
		 */
		catch (Exception e) {
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		} finally {
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void deleteOpinionRow(UserwiseBookMarkMstFB fb, HttpServletRequest request) {
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserwiseBookMarkMstVO[] opinionReqVO1 = null;
		UserwiseBookMarkMstVO[] opinionReqVO2 = null;

		try {
			// commented by prashant
			// List
			// lstOpinion=(List)session.getAttribute(InvestigationConfig.LIST_LAB_COMBO);
			// List newList = new ArrayList(lstOpinion);
			// newList=(List)
			// WebUTIL.addEntryToOptions(newList,fb.getHiddenLabCode(),fb.getHiddenLabName());
			// WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_LAB_COMBO,newList);

			opinionReqVO1 = (UserwiseBookMarkMstVO[]) session.getAttribute(InvestigationConfig.ESSENTIAL_ALL_TEST_VO);

			int j = 0;

			// added by prashant
			if (fb.getHiddenIsTestGroup().equals("1")) {
				int count = 0;
				for (int i = 0; i < opinionReqVO1.length; i++) {
					if (!opinionReqVO1[i].getGroupCode().equals("") && opinionReqVO1[i].getGroupCode() != null
							&& !opinionReqVO1[i].getGroupCode().equals("-1")
							&& opinionReqVO1[i].getGroupCode().equals(fb.getHiddenGroupCode())) {
						count++;
					}
				}
				opinionReqVO2 = new UserwiseBookMarkMstVO[opinionReqVO1.length - count];
			} else if (fb.getHiddenIsTestGroup().equals("0")) {
				opinionReqVO2 = new UserwiseBookMarkMstVO[opinionReqVO1.length - 1];
			}

			for (int i = 0; i < opinionReqVO1.length; i++) {
				if (fb.getHiddenIsTestGroup().equals("1")) {
					if (fb.getHiddenGroupCode().equals(opinionReqVO1[i].getGroupCode())) {

					} else {
						opinionReqVO2[j] = opinionReqVO1[i];
						j++;
					}

				} else if (fb.getHiddenIsTestGroup().equals("0")) {
					if (fb.getHiddenLabCode().equals(opinionReqVO1[i].getLabCode())
							&& fb.getHiddenTestCode().equals(opinionReqVO1[i].getTestCode())) {

					} else {
						opinionReqVO2[j] = opinionReqVO1[i];
						j++;
					}
				}

			}
			WebUTIL.setAttributeInSession(request, InvestigationConfig.ESSENTIAL_ALL_TEST_VO, opinionReqVO2);
			// commented by prashant
			// request.getSession().removeAttribute(InvestigationConfig.LIST_TEST_COMBO);
			objStatus.add(Status.TRANSINPROCESS);
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		/*
		 * catch(HisException e) { e.printStackTrace();
		 * objStatus.add(Status.ERROR,"","Error"); }
		 */
		catch (Exception e) {
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		} finally {
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static boolean saveUserBookMark(UserwiseBookMarkMstFB fb, HttpServletRequest request) {
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		List<UserwiseBookMarkMstVO> lstUserwiseBookMarkVO = new ArrayList<UserwiseBookMarkMstVO>();
		boolean saveFlag = true;
		try {
			UserVO userVO = ControllerUTIL.getUserVO(request);
			UserwiseBookMarkMstVO[] UserwiseBookMarkMstVO = null;
			UserwiseBookMarkMstVO UserwiseBookMarkMstVO1 = new UserwiseBookMarkMstVO();
			ControllerUTIL.setSysdate(request);
			HelperMethods.populate(UserwiseBookMarkMstVO1, fb);
			UserwiseBookMarkMstVO = (UserwiseBookMarkMstVO[]) session
					.getAttribute(InvestigationConfig.ESSENTIAL_ALL_TEST_VO);

			/*
			 * if (fb.getLabCode() != null && fb.getTestCode() != null && fb.getGroupCode()
			 * != null && (!fb.getGroupCode().equals("-1") ||
			 * !fb.getTestCode().equals("-1"))) {
			 * UserwiseBookMarkMstVO UserwiseBookmarkVO=new UserwiseBookMarkMstVO();
			 * UserwiseBookmarkVO.setLabCode(fb.getLabCode());
			 * if(fb.getIsTestGroup().equals("0")) {
			 * UserwiseBookmarkVO.setTestCode(fb.getTestCode()); }
			 * if(fb.getIsTestGroup().equals("1")) { //more code need to be added
			 * UserwiseBookmarkVO.setGroupCode(fb.getGroupCode()); }
			 * lstUserwiseBookMarkVO.add(UserwiseBookmarkVO);
			 * }
			 */
			if (UserwiseBookMarkMstVO != null) {
				for (int i = 0; i < UserwiseBookMarkMstVO.length; i++) {
					UserwiseBookMarkMstVO UserwiseBookmarkVO = new UserwiseBookMarkMstVO();
					UserwiseBookmarkVO.setLabCode(UserwiseBookMarkMstVO[i].getLabCode());
					UserwiseBookmarkVO.setTestCode(UserwiseBookMarkMstVO[i].getTestCode());
					UserwiseBookmarkVO.setGroupCode(UserwiseBookMarkMstVO[i].getGroupCode());
					UserwiseBookmarkVO.setIsTestGroup(UserwiseBookMarkMstVO[i].getIsTestGroup());

					// commented by prashant
					/*
					 * if(fb.getIsTestGroup().equals("0")) {
					 * UserwiseBookmarkVO.setTestCode(UserwiseBookMarkMstVO[i].getTestCode()); }
					 * if(fb.getIsTestGroup().equals("1")) {
					 * UserwiseBookmarkVO.setTestCode(UserwiseBookMarkMstVO[i].getTestCode());
					 * UserwiseBookmarkVO.setGroupCode(UserwiseBookMarkMstVO[i].getGroupCode()); }
					 */

					lstUserwiseBookMarkVO.add(UserwiseBookmarkVO);
				}
			}
			UserwiseBookMarkMstDATA.saveUserBookMark(UserwiseBookMarkMstVO1, lstUserwiseBookMarkVO, userVO);
			UserwiseBookMarkMstVO = null;
			session.setAttribute(InvestigationConfig.ESSENTIAL_ALL_TEST_VO, null); // set list to null after saving
			objStatus.add(Status.DONE, "User BookMark  Data Saved Successfully", "");
		} catch (HisDuplicateRecordException e) {
			saveFlag = false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			System.out.println(e.getMessage());
		} catch (HisDataAccessException e) {
			saveFlag = false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Already Exist");
		} catch (HisApplicationExecutionException e) {
			saveFlag = false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		/*
		 * catch (HisException e) { saveFlag=false; System.out.println(e.getMessage());
		 * objStatus.add(Status.ERROR, e.getMessage(),""); }
		 */
		finally {

			WebUTIL.setStatus(request, objStatus);
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return saveFlag;
	}

	public static boolean fetchModifyUserBookMark(UserwiseBookMarkMstFB fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp = new HashMap();
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String bookmarkCode = concatid[0];
			String seqNo = concatid[2].replace("$", "#").split("#")[0];
			fb.setBookmarkCode(bookmarkCode);
			fb.setSeqNo(seqNo);
			UserwiseBookMarkMstVO.setBookmarkCode(bookmarkCode);
			UserwiseBookMarkMstVO.setSeqNo(seqNo);
			mp = UserwiseBookMarkMstDATA.fetchModifyUserBookMark(UserwiseBookMarkMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, UserwiseBookMarkMstVO);

			objStatus.add(Status.NEW);
		} catch (HisRecordNotFoundException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		} catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		/*
		 * catch (HisException e) { System.out.println(e.getMessage());
		 * objStatus.add(Status.ERROR, "", "Error"); }
		 */
		finally {
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static boolean getBookMarkType(UserwiseBookMarkMstFB fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp = new HashMap();

			UserwiseBookMarkMstVO.setBookmarkCode(fb.getBookmarkCode());
			UserwiseBookMarkMstVO.setSeqNo(fb.getSeqNo());
			mp = UserwiseBookMarkMstDATA.getBookMarkType(UserwiseBookMarkMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, UserwiseBookMarkMstVO);

			objStatus.add(Status.NEW);
		} catch (HisRecordNotFoundException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		} catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		/*
		 * catch (HisException e) { System.out.println(e.getMessage());
		 * objStatus.add(Status.ERROR, "", "Error"); }
		 */
		finally {
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static boolean modifySaveUserwiseBookmark(UserwiseBookMarkMstFB fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		boolean hasFlag = true;
		try {
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
			HelperMethods.populate(UserwiseBookMarkMstVO, fb);
			UserwiseBookMarkMstDATA.modifySaveUserBookmark(UserwiseBookMarkMstVO, userVO);

			objStatus.add(Status.DONE, "Data Modified Successfully", "");

		} catch (HisDuplicateRecordException e) {
			hasFlag = false;
			System.out.println("Default Sample Already Exist");
			objStatus.add(Status.UNSUCESSFULL, "Data Already Exist", "");
		} catch (HisRecordNotFoundException e) {
			hasFlag = false;
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		} catch (HisDataAccessException e) {
			hasFlag = false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			hasFlag = false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e) {
			hasFlag = false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		} finally {
			WebUTIL.setStatus(_request, objStatus);
		}
		return hasFlag;
	}

	public static boolean reFetchModify(UserwiseBookMarkMstFB fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
		try {
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp = new HashMap();
			UserwiseBookMarkMstVO.setBookmarkCode(fb.getBookmarkCode());
			UserwiseBookMarkMstVO.setSeqNo(fb.getSeqNo());
			mp = UserwiseBookMarkMstDATA.fetchModifyUserBookMark(UserwiseBookMarkMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, UserwiseBookMarkMstVO);
			objStatus.add(Status.NEW);

		} catch (HisRecordNotFoundException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		} catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		} finally {
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static StringBuffer getTestgroup(UserwiseBookMarkMstFB objFB, HttpServletRequest request, String labCode,
			String bookmarkCode, String userId) {
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		List lstUnit = new ArrayList();
		boolean flag = true;
		try {
			UserVO userVO = ControllerUTIL.getUserVO(request);
			UserwiseBookMarkMstVO UserwiseBookMarkMstVO = new UserwiseBookMarkMstVO();
			ControllerUTIL.setSysdate(request);
			HelperMethods.populate(UserwiseBookMarkMstVO, objFB);
			System.out.println("labCode :" + labCode);
			UserwiseBookMarkMstVO.setLabCode(labCode);
			UserwiseBookMarkMstVO.setBookmarkCode(bookmarkCode);
			UserwiseBookMarkMstVO.setUserId(userId);
			lstUnit = UserwiseBookMarkMstDATA.getTestgroup(UserwiseBookMarkMstVO, userVO);

			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_TEST_GROUP_COMBO, lstUnit);
			// System.out.println("lstunit 0 :"+lstUnit.get(0));

			if (lstUnit != null && lstUnit.size() > 0) {
				sbAjaxRes.append("[");
				for (int i = 0; i < lstUnit.size(); i++) {

					/*
					 * //commented by prashant String unit=lstUnit.get(i).toString(); String []
					 * unitDetail= unit.split(","); String groupName= unitDetail[0].substring(1,
					 * unitDetail[0].length()); String groupCode= unitDetail[1].substring(0,
					 * (unitDetail[1].length()-1));
					 */

					// added by prashant
					String groupName = ((Entry) lstUnit.get(i)).getLabel();
					String groupCode = ((Entry) lstUnit.get(i)).getValue();
					System.out.println("groupCode :" + groupCode + "  groupName :" + groupName);

					sbAjaxRes.append("{");
					sbAjaxRes.append("groupName");
					sbAjaxRes.append(":");
					sbAjaxRes.append("\'");
					sbAjaxRes.append(groupName);
					sbAjaxRes.append("\'");
					sbAjaxRes.append(",");
					sbAjaxRes.append("groupCode");
					sbAjaxRes.append(":");
					sbAjaxRes.append("\'");
					sbAjaxRes.append(groupCode);
					sbAjaxRes.append("\'");
					sbAjaxRes.append("}");
					sbAjaxRes.append(",");
				}
				if (lstUnit.size() > 0)
					sbAjaxRes.delete(sbAjaxRes.length() - 1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		} catch (HisRecordNotFoundException e) {
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_UOM_COMBO, lstUnit);
			// objFB.setShowRommList("0");
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
		return sbAjaxRes;
	}

}
