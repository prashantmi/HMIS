package billing.masters.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.qryHandler_billing;
import billing.masters.vo.LocalTariffMstVO;

public class LocalTariffMstDAO {

	/*
	 * retrieve data for Hospital Services. This function is received by HLP
	 * class funtion.
	 */
	public static WebRowSet defaultValue(LocalTariffMstVO vo) throws Exception {
		HisDAO hisdao = new HisDAO("Billing", "LocalTariffMstDAO");
		WebRowSet wb = null;
		
	
		String query = billing.qryHandler_billing.getQuery(1,
				"select.tariffMst.6").replace("?",BillConfigUtil.SUPER_HOSPITAL_CODE);
		
		try {
			
			wb = hisdao.getQryResult(query);
			
			
		} catch (Exception e) {
		
			throw new Exception("billing.LocalTariffMstDAO.defaultValue() --> "
					+ e.getMessage());
		} finally {
			hisdao = null;
		}
		
		return wb;
	}

	/*
	 * to insert data on the add page.
	 */
	public static boolean insertData(LocalTariffMstVO vo) throws Exception 
	{
		boolean retValue = false;
		HisDAO hisdao = new HisDAO("billing", "LocalTariffMstDAO");
		int index, index1;//, index2, index3;
		try 
		{
			String query = billing.qryHandler_billing.getQuery(1, "select.tariffMst.9");
			String query1 = qryHandler_billing.getQuery(1, "insert.tariffMst.0");
			/*String query2 = qryHandler_billing.getQuery(1, "insert.tariffMst.1");
			String query3 = qryHandler_billing.getQuery(1, "insert.tariffMst.2");*/
			
			if (vo.getDepartment() == null || vo.getDepartment().equals("0"))
			{
				vo.setIsContribution("0");
			}
			else
			{
				vo.setIsContribution("1");				
			}
			
			index = hisdao.setQuery(query);
			hisdao.setQryValue(index, 1, vo.getGroupId());
			hisdao.setQryValue(index, 2, vo.getStrHospitalCode());
				
			
			WebRowSet wb = hisdao.executeQry(index); // executes query to retrieve primary key.
			if (wb.next()) 
			{
				vo.setTariffId(wb.getString(1));
			}
			try 
			{
				index1 = hisdao.setQuery(query1);
				
				hisdao.setQryValue(index1, 1, vo.getGlobalTariff());
				hisdao.setQryValue(index1, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(index1, 3, vo.getStrHospitalCode());
				hisdao.setQryValue(index1, 4, vo.getGroupId());
				hisdao.setQryValue(index1, 5, vo.getGlobalTariff());
				hisdao.setQryValue(index1, 6, vo.getTrfPkgName());
				if(vo.getIsPackage().equals("1"))
				{
					hisdao.setQryValue(index1, 7, vo.getDefaultPackUnit());
				}
				else
				{
				    hisdao.setQryValue(index1, 7, vo.getDefaultUnit());
				}
				hisdao.setQryValue(index1, 8, vo.getStrServiceId());
				hisdao.setQryValue(index1, 9, vo.getTariffName());
				hisdao.setQryValue(index1, 10, vo.getLengthOfStay());
				hisdao.setQryValue(index1, 11, vo.getIsPackage());
				hisdao.setQryValue(index1, 12, vo.getIsContribution());
				hisdao.setQryValue(index1, 13, vo.getEffectiveFromContbDet());
				hisdao.setQryValue(index1, 14,vo.getRemarks());
				hisdao.setQryValue(index1, 15,vo.getSeatId());
				hisdao.setQryValue(index1, 16, vo.getIsValid());
				hisdao.setQryValue(index1, 17, vo.getStrDiscountPrivilege());
				hisdao.setQryValue(index1, 18, vo.getStrDiscountLimit());
				hisdao.setQryValue(index1, 19, vo.getTariffCode());
				hisdao.setQryValue(index1, 20, vo.getStrIsDefaultForIpd());
				hisdao.setQryValue(index1, 21, vo.getStrUndefinedCharges());
				hisdao.setQryValue(index1, 22, vo.getStrCostType());
				hisdao.setQryValue(index1, 23, vo.getStrIsOpdVisible());
				hisdao.setQryValue(index1, 24, vo.getStrIsIpdVisible());
				hisdao.setQryValue(index1, 25, vo.getStrIsEmergVisible());
				hisdao.setQryValue(index1, 26, vo.getStrIsEstimationFlag());
				hisdao.setQryValue(index1, 27, vo.getGstrTariffName());
				
				hisdao.execute(index1, 0);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				throw new Exception("billing.LocalTariffMstDAO.insertData() --> "+ e.getMessage());
			}
			/*try 
			{
				// for second insert query.
				 String strChargeTypeId[] = null;
				  strChargeTypeId = vo.getChargeTypeId();
				   index2 = hisdao.setQuery(query2);
									 
					  
					  for (int i = 0; i < strChargeTypeId.length; i++) 
					  {
						 	hisdao.setQryValue(index2, 1, vo.getStrHospitalCode());
							hisdao.setQryValue(index2, 2, vo.getChargeTypeId()[i]);
							hisdao.setQryValue(index2, 3, vo.getTariffId());
							hisdao.setQryValue(index2, 4, vo.getTariffId());
							hisdao.setQryValue(index2, 5, vo.getStrChoiceNum());
							hisdao.setQryValue(index2, 6, vo.getStrHospitalCode());
							hisdao.setQryValue(index2, 7, vo.getRate()[i]);
							hisdao.setQryValue(index2, 8, vo.getActualCost()[i]);
							hisdao.setQryValue(index2, 9, vo.getServiceTax()[i]);
							hisdao.setQryValue(index2, 10,vo.getEffectiveFromContbDet());
							hisdao.setQryValue(index2, 11, vo.getRemarks());
							hisdao.setQryValue(index2, 12, vo.getSeatId());
							hisdao.setQryValue(index2, 13, vo.getIsValid());
							
							hisdao.execute(index2, 0);						
					}
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				throw new Exception("billing.LocalTariffMstDAO.insertData() --> "+ e.getMessage());
			}
			try 
			{
				// for third insert query.
			
				if (vo.getDepartment() != null )
				{
					index3 = hisdao.setQuery(query3);
					for (int i = 0; i < vo.getDepartment().length; i++) 
					{
						if (vo.getShare()[i] != null
								|| vo.getShare()[i] != "") 
						{
							hisdao.setQryValue(index3, 1, vo.getStrHospitalCode());
							hisdao.setQryValue(index3, 2, vo.getTariffId());
							hisdao.setQryValue(index3, 3, vo.getDepartment()[i]);
							hisdao.setQryValue(index3, 4, vo.getTariffId());
							hisdao.setQryValue(index3, 5, vo.getStrChoiceNum());
							hisdao.setQryValue(index3, 6, vo.getStrHospitalCode());
							hisdao.setQryValue(index3, 7, vo.getShare()[i]);
							hisdao.setQryValue(index3, 8, vo.getEffectiveFromContbDet());
							hisdao.setQryValue(index3, 9, vo.getRemarks());
							hisdao.setQryValue(index3, 10, vo.getSeatId());
							hisdao.setQryValue(index3, 11, vo.getIsValid());

							hisdao.execute(index3, 0);
						
						}
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				throw new Exception("billing.LocalTariffMstDAO.insertData() --> "+ e.getMessage());
			}*/
			synchronized (hisdao) 
			{
    			hisdao.fire();
				retValue = true;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			retValue = false;		
			throw new Exception("billing.LocalTariffMstDAO.insertData() --> "+ e.getMessage());
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	}

	/*
	 * to check uniqueness of tariff name corresponding to group name in add
	 * page.
	 */
	public static boolean chkAddUniqueData(LocalTariffMstVO vo) throws Exception {
		boolean chkValue = false;
		HisDAO hisdao = new HisDAO("Billing", "LocalTariffMstDAO.chkAddUniqueData");
		int index,index1,index2;
		int count = 0,count1=0,count2=0;
		WebRowSet wb = null;
		WebRowSet wb1 = null;
		WebRowSet wb2 = null;
		String query = billing.qryHandler_billing.getQuery(1,"select.tariffMst.11");
		
		String query1 = billing.qryHandler_billing.getQuery(1,"select.tariffMst.111");
		
		String query2 = billing.qryHandler_billing.getQuery(1,"select.tariffMst.1111");
		
		try 
		{
					
			index = hisdao.setQuery(query);
			hisdao.setQryValue(index, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(index, 2, vo.getTrfPkgName()); 
			
			wb = hisdao.executeQry(index);
			
			while (wb.next()) 
			{
				count = Integer.parseInt(wb.getString(1));
			}
			
			index1 = hisdao.setQuery(query1);
			hisdao.setQryValue(index1, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(index1, 2, vo.getTariffCode()); // Tariff Code Add by Amit on Date 20 - April - 2010 
			
			wb1 = hisdao.executeQry(index1);
			
			
			while (wb1.next()) 
			{
				count1 = Integer.parseInt(wb1.getString(1));
			}
			
			index2 = hisdao.setQuery(query2);
			hisdao.setQryValue(index2, 1, vo.getStrHospitalCode());
			hisdao.setQryValue(index2, 2, vo.getGlobalTariff()); // Tariff Code Add by Amit on Date 20 - April - 2010 
			
			wb2 = hisdao.executeQry(index2);
			
			while (wb2.next()) 
			{
				count2 = Integer.parseInt(wb2.getString(1));
			}
			if (count == 0 && count1 == 0 && count2==0) 
			{
				chkValue = true;
			} 
			else 
			{
				if(count!=0)
				{
					vo.setFlag("1");
				}
				else if(count1!=0)
				{
					vo.setFlag("2");
				}	
				else
				{
					vo.setFlag("3");
				}
				chkValue = false;
			}
		} catch (Exception e) 
		{
	 
			chkValue = false;
			
			throw new Exception("billing.LocalTariffMstDAO.chkAddUniqueData() --> "
					+ e.getMessage());
		} finally {

			hisdao.free();
			hisdao = null;
		}
	
		return chkValue;
	}

	/*
	 * to retrieve data from the database for modify page.
	 */
	public static boolean getData(LocalTariffMstVO vo, String chk) throws Exception 
	{
		boolean retValue = false;
		HisDAO hisdao = new HisDAO("billing", "LocalTariffMstDAO");
		int index1, index2, index3;
		
		try 
		{		
			String query1 = qryHandler_billing.getQuery(1,"select.localTariffMst.12");
			String query2 = qryHandler_billing.getQuery(1,"select.localTariffMst.13");
			String query3 = qryHandler_billing.getQuery(1,"select.localTariffMst.14");
			
			String[] temp = chk.replace('$', '#').split("#"); // splitting the
			String[] temp1 = temp[0].replace('@', '#').split("#"); 
			
			
			index1 = hisdao.setQuery(query1);
			hisdao.setQryValue(index1, 1, temp1[0]);
			hisdao.setQryValue(index1, 2, temp1[1].replace("|", "#").split("#")[0]);
			
			WebRowSet wb = hisdao.executeQry(index1);
			
		
			while(wb.next())
			{
				vo.setGroupId(wb.getString(1));
				vo.setStrGroupId(wb.getString(1)); 
				vo.setTrfPkgName(wb.getString(2));
				vo.setDefaultUnit(wb.getString(3));
				vo.setLengthOfStay(wb.getString(4));
				vo.setIsPackage(wb.getString(5));
				vo.setServiceName(wb.getString(6));
				vo.setTariffId(wb.getString(7));
				vo.setRemarks(wb.getString(8));
				vo.setSeatId(wb.getString(9));
				vo.setIsValid(wb.getString(10));
				vo.setStrDiscountPrivilege(wb.getString(11));
				vo.setStrDiscountLimit(wb.getString(12));
				vo.setTariffCode(wb.getString(13));
				vo.setStrIsDefaultForIpd(wb.getString(14));
				vo.setStrUndefinedCharges(wb.getString(15));
				vo.setStrCostType(wb.getString(16));
				vo.setStrIsOpdVisible(wb.getString(17));
				vo.setStrIsIpdVisible(wb.getString(18));
				vo.setStrIsEmergVisible(wb.getString(19));
				vo.setStrIsEstimationFlag(wb.getString(20));
				vo.setGstrTariffName(wb.getString(21));
			}
			wb = null;
			
			index2 = hisdao.setQuery(query2);
			hisdao.setQryValue(index2, 1, temp1[0]);
			hisdao.setQryValue(index2, 2, temp1[1].replace("|", "#").split("#")[0]);
			
			wb = hisdao.executeQry(index2);
			String[] chargeType = new String[wb.size()];
			String[] rate = new String[wb.size()];
			String[] cost = new String[wb.size()];
			String[] discount = new String[wb.size()];
			String[] serviceTax = new String[wb.size()];
			String effectiveFrm = "";
			
			
			int i = 0;
			while (wb.next()) {
				chargeType[i] = wb.getString(1);
				rate[i] = wb.getString(2);
				cost[i] = wb.getString(3);
				discount[i] = wb.getString(4);
				serviceTax[i] = wb.getString(5);
				effectiveFrm = wb.getString(6);
				i++;
			}
			vo.setChargeTypeId(chargeType);
			vo.setRate(rate);
			vo.setActualCost(cost);
			vo.setMaxDisc(discount);
			vo.setServiceTax(serviceTax);
			vo.setStrEffectiveFrom(effectiveFrm);
			i = 0;
			wb = null;
			
			index3 = hisdao.setQuery(query3);
			
			hisdao.setQryValue(index3, 1, temp1[0]);
			hisdao.setQryValue(index3, 2, temp1[1].replace("|", "#").split("#")[0]);
			
			wb = hisdao.executeQry(index3);
			String[] dept = new String[wb.size()];
			String[] share = new String[wb.size()];
		//	String effFrm = "";
			
			vo.setTotalRows(wb.size());
		
			vo.setStrTotalRows(Integer.toString(wb.size()));
			while (wb.next()) {
				dept[i] = wb.getString(1);
				share[i] = wb.getString(2);
			//	effFrm = wb.getString(3);
				i++;
			}
			vo.setDepartment(dept);
			vo.setShare(share);
			retValue = true;
			
			}	
		catch (Exception e) {
			e.printStackTrace();
			retValue = false;
			throw new Exception("billing.LocalTariffMstDAO.getData() --> "+ e.getMessage());
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	
	}
	/*
	 * to update data in modify page.
	 */
	public static boolean updateData(LocalTariffMstVO vo) throws Exception 
	{
		boolean retVal = false;
		HisDAO hisdao = new HisDAO("billing", "LocalTariffMstDAO");
		int index,index1,index6;// index2, index3, index4, index5  ;
		String temp[] = null;
		String temp2[] = null;
		String chk = "";
		String chkTrfId = "";
		
		try 
		{
		
			chk = vo.getStrChK();
			temp = chk.replace("@","#").split("#");
			vo.setStrHospitalCode(temp[0]);
			temp2 = temp[1].replace("|","#").split("#");
			chkTrfId = temp2[0];
			//localTariffMst
			String query  = qryHandler_billing.getQuery(1, "select.tariffMst.9");
			String query1 = qryHandler_billing.getQuery(1, "update.localTariffMst.0");
			//String query2 = qryHandler_billing.getQuery(1, "update.localTariffMst.1");
			//String query3 = qryHandler_billing.getQuery(1, "update.localTariffMst.2");
			//String query4 = qryHandler_billing.getQuery(1, "update.localTariffMst.3");
			//String query5 = qryHandler_billing.getQuery(1, "update.localTariffMst.4");
			String query6 = qryHandler_billing.getQuery(1, "update.localTariffMst.5");
		
			if (vo.getDepartment() == null || vo.getDepartment().equals("0")) 
			{
				vo.setIsContribution("0");
			} 
			else 
			{
				vo.setIsContribution("1");
			}
		
			index = hisdao.setQuery(query);
			hisdao.setQryValue(index, 1, vo.getGroupId());
			hisdao.setQryValue(index, 2, vo.getStrHospitalCode());		
		
			WebRowSet wb = hisdao.executeQry(index);
		
			if (wb.next()) 
			{
				vo.setTariffId(wb.getString(1));
			}
			try
			{		
				index1 = hisdao.setQuery(query1);
				hisdao.setQryValue(index1, 1, vo.getStrServiceId());
				hisdao.setQryValue(index1, 2, vo.getTrfPkgName());
				hisdao.setQryValue(index1, 3, vo.getStrGroupId());
				hisdao.setQryValue(index1, 4, vo.getTariffName());
				hisdao.setQryValue(index1, 5, vo.getDefaultUnit());
				hisdao.setQryValue(index1, 6, vo.getLengthOfStay());
				hisdao.setQryValue(index1, 7, vo.getIsPackage());
				hisdao.setQryValue(index1, 8, vo.getIsContribution());
				hisdao.setQryValue(index1, 9, vo.getSeatId());
				hisdao.setQryValue(index1, 10, vo.getRemarks());
				hisdao.setQryValue(index1, 11, vo.getIsValid());
				hisdao.setQryValue(index1, 12, vo.getStrDiscountPrivilege());
				hisdao.setQryValue(index1, 13, vo.getStrDiscountLimit());
				hisdao.setQryValue(index1, 14, vo.getTariffCode());
				hisdao.setQryValue(index1, 15, vo.getStrUndefinedCharges());
				hisdao.setQryValue(index1, 16, vo.getStrCostType());
				hisdao.setQryValue(index1, 17, vo.getStrIsDefaultForIpd());
				hisdao.setQryValue(index1, 18, vo.getStrIsOpdVisible());
				hisdao.setQryValue(index1, 19, vo.getStrIsIpdVisible());
				hisdao.setQryValue(index1, 20, vo.getStrIsEmergVisible());
				hisdao.setQryValue(index1, 21, vo.getGstrTariffName());
	
				hisdao.setQryValue(index1, 22, vo.getStrHospitalCode());	
				hisdao.setQryValue(index1, 23, chkTrfId);	
				
				hisdao.execute(index1, 0);			
			}
			catch (Exception e) 
			{			 
			}
			/*try
			{
				
				index2 = hisdao.setQuery(query2);
				hisdao.setQryValue(index2, 1, vo.getSeatId());
				hisdao.setQryValue(index2, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(index2, 3, chkTrfId);
				hisdao.execute(index2, 0);			
			}
			catch (Exception e) 
			{
			 
			}			
			try
			{			
				index3 = hisdao.setQuery(query3);
				hisdao.setQryValue(index3, 1, vo.getSeatId());
				hisdao.setQryValue(index3, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(index3, 3, chkTrfId);
				hisdao.execute(index3, 0);
			}
			catch (Exception e) 
			{			 
			}
			try
			{		 
				if (vo.getChargeTypeId() != null) 
				{				
					index4 = hisdao.setQuery(query4);
				
					for (int i = 0; i < vo.getChargeTypeId().length; i++)
					{
							hisdao.setQryValue(index4, 1, vo.getChargeTypeId()[i]);
							hisdao.setQryValue(index4, 2, chkTrfId);
							hisdao.setQryValue(index4, 3, chkTrfId);
							hisdao.setQryValue(index4, 4, vo.getStrChoiceNum());
							hisdao.setQryValue(index4, 5, vo.getStrHospitalCode());
					        hisdao.setQryValue(index4, 6, vo.getRate()[i]);
							hisdao.setQryValue(index4, 7, vo.getActualCost()[i]);
							hisdao.setQryValue(index4, 8, "0");
							hisdao.setQryValue(index4, 9, vo.getServiceTax()[i]);
							hisdao.setQryValue(index4, 10, vo.getEffectiveFromContbDet());
							hisdao.setQryValue(index4, 11, vo.getRemarks());
							hisdao.setQryValue(index4, 12, vo.getSeatId());
							hisdao.setQryValue(index4, 13, vo.getIsValid());
							hisdao.setQryValue(index4, 14, vo.getSeatId());
							hisdao.setQryValue(index4, 15, vo.getStrHospitalCode());
							hisdao.execute(index4, 0);
					}
				}
			}
			catch (Exception e) 
			{				 
			}
			
			try
			{
				if (vo.getDepartment() != null) 
				{				
					index5 = hisdao.setQuery(query5);					
					for (int i = 0; i < vo.getDepartment().length; i++) 
					{
						if (vo.getDepartment()[i] != null || vo.getDepartment().equals("0"))
						{					
							hisdao.setQryValue(index5, 1, chkTrfId);
							hisdao.setQryValue(index5, 2, vo.getDepartment()[i]);
							hisdao.setQryValue(index5, 3, chkTrfId);
							hisdao.setQryValue(index5, 4, vo.getStrChoiceNum());
							hisdao.setQryValue(index5, 5, vo.getStrHospitalCode());
							hisdao.setQryValue(index5, 6, vo.getShare()[i]);
							hisdao.setQryValue(index5, 7, vo.getEffectiveFromContbDet());
							hisdao.setQryValue(index5, 8, vo.getSeatId());
							hisdao.setQryValue(index5, 9, vo.getIsValid());
							hisdao.setQryValue(index5, 10, vo.getSeatId());
							hisdao.setQryValue(index5, 11, vo.getRemarks());
							hisdao.setQryValue(index5, 12, vo.getStrHospitalCode());
							
							hisdao.execute(index5, 0);
						}
					}
				}
			}
			catch (Exception e) 
			{
			    e.printStackTrace();
				throw new Exception("billing.LocalTariffMstDAO.updateData() --> "+ e.getMessage());
			}*/			
			
			if(vo.getStrUpdateChargeUnit().equals("1"))
			{				
				try
				{					
					index6 = hisdao.setQuery(query6);
					hisdao.setQryValue(index6, 1, vo.getDefaultUnit());
					hisdao.setQryValue(index6, 2, vo.getStrHospitalCode());
					hisdao.setQryValue(index6, 3, chkTrfId);
					hisdao.execute(index6, 0);
				}
				catch (Exception e) 
				{				 
				}				
			}			
			synchronized(hisdao)
			{
				hisdao.fire();
				retVal = true;
			
			}
		}	
		catch (Exception e) 
		{
			e.printStackTrace();
			retVal = false;		 
			throw new Exception("billing.LocalTariffMstDAO.updateData() --> "+ e.getMessage());
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return retVal;
	}
	
	/*
	 * to check uniqueness of tariff name corresponding to group name in add
	 * page.
	 */
	public static boolean chkModifyUniqueData(LocalTariffMstVO vo) throws Exception 
	{
		boolean chkValue = false;
		HisDAO hisdao = new HisDAO("Billing","LocalTariffMstDAO.chkModifyUniqueData");
		int index ;
		int count = 0;
		int index1 ;
		int count1 = 0;		
		WebRowSet wb = null;
		String[] temp=null;
		String chk="";
		String query = billing.qryHandler_billing.getQuery(1,"select.tariffMst.15");
		String query1 = billing.qryHandler_billing.getQuery(1,"select.tariffMst.115");
		
		try 
		{			
			 index = hisdao.setQuery(query);
			 chk = vo.getStrChK();	//vo.getStrChK() = 101@1230003|1$9
		     temp = chk.replace("@", "#").split("#");
			 vo.setStrHospitalCode(temp[0]);
		     temp=temp[1].replace("|", "#").split("#");
		     vo.setTariffId(temp[0]);
		 
			hisdao.setQryValue(index, 1, vo.getStrHospitalCode());
			//hisdao.setQryValue(index, 2, vo.getGroupId());
			
			hisdao.setQryValue(index, 2, vo.getTrfPkgName());
			hisdao.setQryValue(index, 3, vo.getTariffId());
			
			
			wb = hisdao.executeQry(index);
			while (wb.next()) 
			{
				count = Integer.parseInt(wb.getString(1));
			}			
			 	index1 = hisdao.setQuery(query1);			
				hisdao.setQryValue(index1, 1, vo.getStrHospitalCode());
				hisdao.setQryValue(index1, 2, vo.getTariffCode());
				hisdao.setQryValue(index1, 3, vo.getTariffId());			 

				wb = hisdao.executeQry(index1);
				while (wb.next()) 
				{
					count1 = Integer.parseInt(wb.getString(1));
				}			
			if (count < 1 && count1 < 1) 
			{
				chkValue = true;
			} 
			else 
			{
				chkValue = false;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			chkValue = false;	 
			throw new Exception("billing.LocalTariffMstDAO.chkModifyUniqueData() --> "+ e.getMessage());
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return chkValue;
}
	
}