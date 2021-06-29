package billing.masters.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.masters.controller.fb.VOHospServiceGroupMst;

public class DAOHospServiceGroupMst {

	public static boolean insertQuery(VOHospServiceGroupMst vo)
			throws Exception {
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = false;
		String rgroupName[] = vo.getRgroupName();
		String query = new String();
		dao = new HisDAO("Billing", "DAOHospServiceGroupMst");
		query = billing.qryHandler_billing
				.getQuery(1, "insert.hservicegroup.0");
		// System.out.println("insert query"+query );

		try {
			qryIndex = dao.setQuery(query);

			for (int i = 0; i < rgroupName.length; i++) {

				dao.setQryValue(qryIndex, 1, vo.getHserviceName());
				dao.setQryValue(qryIndex, 2, rgroupName[i].toUpperCase().trim());
				dao.setQryValue(qryIndex, 3, vo.getStrHospitalCode());
				dao.setQryValue(qryIndex, 4, vo.getHserviceName());
				dao.setQryValue(qryIndex, 5, rgroupName[i].toUpperCase().trim());
				dao.setQryValue(qryIndex, 6, vo.getStrHospitalCode());
				dao.setQryValue(qryIndex, 7, vo.getEffectiveFrom());
				dao.setQryValue(qryIndex, 8, vo.getSeatId());
				dao.setQryValue(qryIndex, 9, vo.getRemark());
				dao.setQryValue(qryIndex, 10, vo.getEffectiveTo());

				dao.execute(0, qryIndex);

			}

			synchronized (dao) {
				dao.fire();
				retvalue = true;
			}

		} catch (Exception e) {
			retvalue = false;
			throw new Exception("billing.DAOHospServiceGroupMst.insertQuery"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void modifyQuery(String chk1, VOHospServiceGroupMst vo)
			throws Exception {
		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();

		// System.out.println("chk1"+chk1);
		temp = chk1.replace('$', '#').split("#");
		try {
			dao = new HisDAO("Billing", "DAOHospServiceGroupMst");
			query = billing.qryHandler_billing.getQuery(1,
					"select.hservicegroup.4");
			// System.out.println("modify
			// QUERY"+billing.qryHandler_billing.getQuery(1,"select.hservicegroup.4"));
			qryIndex = dao.setQuery(query);
			// System.out.println("qryIndex "+qryIndex );
			String[] temp1 = temp[0].replaceAll("@", "#").split("#");
			for (int i = 1; i <= temp1.length; i++) {

				dao.setQryValue(qryIndex, i, temp1[i - 1]);

			}

			WebRowSet web = dao.executeQry(qryIndex);
			while (web.next()) {
				vo.setHserviceName(web.getString(1));
				String[] arrtemp = { web.getString(2) };
				vo.setRgroupName(arrtemp);
				vo.setEffectiveFrom(web.getString(3));
				vo.setRemark(web.getString(4));
				vo.setSeatId(web.getString(5));
				vo.setIsValid(web.getString(6));

			}
		} catch (Exception e) {

			throw new Exception("billing.DAOHospServiceGroupMst.modifyQuery"
					+ e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}

	}

	public static boolean updateQuery(String chk, VOHospServiceGroupMst vo)
			throws Exception {
		boolean retvalue = false;
		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();
		try {
			dao = new HisDAO("Billing", "DAOHospServiceGroupMst");
			query = billing.qryHandler_billing.getQuery(1,
					"update.hservicegroup.1");
			// System.out.println("QUERY"+billing.qryHandler_billing.getQuery(1,"update.hservicegroup.1"));

			// System.out.println("chk"+chk);
			temp = chk.replace('$', '#').split("#");
			// System.out.println("temp"+ temp[0]);
			String[] temp1 = temp[0].replaceAll("@", "#").split("#");

			qryIndex = dao.setQuery(query);

			// System.out.println("EffectiveFrom()-->"+vo.getEffectiveFrom());
			// System.out.println("seat id-->"+vo.getSeatId());
			// System.out.println("getRemark()-->"+vo.getRemark());
			// System.out.println("seat id-->"+vo.getSeatId());
			// System.out.println("IsValid-->"+vo.getIsValid());
			// System.out.println("temp-0->"+temp1[0]);
			// System.out.println("temp-1->"+temp1[1]);
			// System.out.println("temp-2->"+temp1[2]);
			// System.out.println("temp2-0->"+temp1[3]);

			dao.setQryValue(qryIndex, 1, vo.getEffectiveFrom());
			dao.setQryValue(qryIndex, 2, vo.getSeatId());
			dao.setQryValue(qryIndex, 3, vo.getRemark());
			dao.setQryValue(qryIndex, 4, vo.getSeatId());
			dao.setQryValue(qryIndex, 5, vo.getIsValid());
			dao.setQryValue(qryIndex, 6, temp1[0]);
			dao.setQryValue(qryIndex, 7, temp1[1]);
			dao.setQryValue(qryIndex, 8, temp1[2]);
			dao.setQryValue(qryIndex, 9, temp1[3]);

			dao.execute(0, qryIndex);

			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e) {
			retvalue = false;
			throw new Exception("billing.DAOHospServiceGroupMst.updateQuery"
					+ e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}
		// System.out.println("retvalue in modify record :"+retvalue);
		return retvalue;
	}
}
