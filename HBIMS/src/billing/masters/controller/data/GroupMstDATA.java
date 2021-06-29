package billing.masters.controller.data;
/* Group Master DATA
 * author: Debashis Sardar
 * Created on : 09-Sep-2011
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.controller.hlp.*;
import billing.masters.bo.GroupMstBO;
import billing.masters.controller.fb.GroupMstFB;
import billing.masters.vo.GroupMstVO;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
	

	public class GroupMstDATA{
			
		/**
		 * Initializes the BO and VO and transfers control to BO for insertion
		 * 
		 * @param fb
		 * @return int count
		 */
		public int InsertRecord(GroupMstFB fb) 
		{
			int count=-1;
			GroupMstBO bo = null;
			GroupMstVO voObj = null;
			try 
			{
				bo = new GroupMstBO();
				voObj = new GroupMstVO();
				voObj = (GroupMstVO) TransferObjectFactory.populateData("billing.masters.vo.GroupMstVO", fb);
				voObj.sethospitalServiceValues(fb.getHospServiceValue());
				HelperMethods.populate(fb,voObj);
				count=bo.InsertRecord(voObj);
			} 
			catch (Exception e) 
			{
				String strmsgText = "billing.masters.controller.data.GroupMstDATA.insertRecord(fb) --> "+ e.getMessage();
				HisException eObj = new HisException("Billing","GroupMst->insertRecord()", strmsgText);
			    fb.setStrMsgType("1");
			    fb.setStrerrmsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			    eObj = null;
			} 
			finally
			{
				bo = null;
				voObj = null;
			}
			return count;
		}
		/**
		 * Initializes the BO and VO and transfers control to BO for Modification
		 * 
		 * @param fb
		 * @param chk
		 */
		
		public void addRecord(GroupMstFB fb)
		{
			GroupMstBO bo = null;
		    GroupMstVO voObj = null;
		    GroupMstHLP hlp=null;
		    String strhospitalServiceCheckbox=null;
			try {
				bo = new GroupMstBO();
				voObj = new GroupMstVO();
				hlp=new GroupMstHLP();
				voObj = (GroupMstVO) TransferObjectFactory.populateData("billing.masters.vo.GroupMstVO", fb);
				String superHospCode=BillConfigUtil.SUPER_HOSPITAL_CODE ;
				bo.hospitalServiceAdd(superHospCode,voObj);
				strhospitalServiceCheckbox =hlp.getHospitalServiceDetails(voObj);
				fb.setStrHospitalServiceCheckbox(strhospitalServiceCheckbox);
			}
			catch (Exception e)
			{
				String strmsgText = "billing.masters.controller.data.GroupMstDATA.addRecord(chk,fb) --> "+ e.getMessage();
				HisException eObj = new HisException("Billing","GroupMst->addRecord()", strmsgText);
				fb.setStrMsgType("1");
				fb.setStrerrmsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
				eObj = null;
			} 
			finally 
			{
				bo = null;
				voObj = null;
			}
		}
		
		public void modifyRecord(String chk,GroupMstFB fb) {
			
			GroupMstBO bo = null;
		    GroupMstVO voObj = null;
		    GroupMstHLP hlp=null;
		    String strhospitalServiceCheckbox=null;
		    String strtemp[] = null;
			String strtemp2[] = null;
			try {
				bo = new GroupMstBO();
				voObj = new GroupMstVO();
				hlp=new GroupMstHLP();
				voObj = (GroupMstVO) TransferObjectFactory.populateData("billing.masters.vo.GroupMstVO", fb);
				String superHospCode=BillConfigUtil.SUPER_HOSPITAL_CODE ;
				strtemp = chk.replace('@', '#').split("#");
				strtemp2 = strtemp[1].replace("$", "#").split("#");
				strtemp[1] = strtemp2[0];
				bo.hospitalServiceCheckbox(superHospCode,voObj,strtemp);
				strhospitalServiceCheckbox =hlp.getHospitalServiceValues(voObj);
				bo.modifyRecord(chk,voObj);
				HelperMethods.populate(fb,voObj);
				fb.setStrgroupName(voObj.getStrgroupName());
				fb.setStrisPackage(voObj.getStrisPackage());
				fb.setStrEffectiveFrom(voObj.getStrEffectiveFrom());
				fb.setStrisValid(voObj.getStrisValid());
				fb.setStrremark(voObj.getStrremark());
				fb.setStrisVisible(voObj.getStrisVisible());
				fb.setStrlastSeatId(voObj.getStrlastSeatId());	
				fb.setStrconsumeableCharge(voObj.getStrconsumeableCharge());
				fb.setStrHospitalServiceCheckbox(strhospitalServiceCheckbox);
			}
			catch (Exception e)
			{
				String strmsgText = "billing.masters.controller.data.GroupMstDATA.modifyRecord(chk,fb) --> "+ e.getMessage();
				HisException eObj = new HisException("Billing","GroupMst->modifyRecord()", strmsgText);
				fb.setStrMsgType("1");
				fb.setStrerrmsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
				eObj = null;
			} 
			finally
			{
				bo = null;
				voObj = null;
			}
		}
		/**
		 * Initializes the BO and VO and transfers control to BO for updation
		 * 
		 * @param chk1
		 * @param fb
		 * @return int count
		 */
		public int updateRecord(String chk1,GroupMstFB fb) 
		{
			int count=-1;
			GroupMstBO bo = null;
			GroupMstVO voObj = null;
			try 
			{			
				bo = new GroupMstBO();
				voObj = new GroupMstVO();
				voObj = (GroupMstVO) TransferObjectFactory.populateData("billing.masters.vo.GroupMstVO", fb);
				if(fb.getHospServiceValue()!=null)
			    	 voObj.sethospitalServiceValues(fb.getHospServiceValue());
				voObj.setStrEffectiveFrom(fb.getStrEffectiveFrom());
				voObj.setStrremark(fb.getStrremark());
				count=bo.updateRecord(chk1,voObj);
			}	
			catch (Exception e) 
			{
				String strmsgText = "billing.masters.controller.data.GroupMstDATA.updateRecord(chk,fb) --> "+ e.getMessage();
			    HisException eObj = new HisException("Billing","GroupMst->updateRecord()", strmsgText);
			    fb.setStrMsgType("1");
			    fb.setStrerrmsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			    eObj = null;
			} 
			finally 
			{
				bo = null;
				voObj = null;
			}
			return count ;
		}
		/**
		 * for global group combo on add page
		 * @param fb
		 * @return string
		 */
		
		public String getGlobalGroupCombo(String chk1,GroupMstFB bean) 
		{
			String tempStr = "";
			HisUtil hisutil = new HisUtil("Billing", "GroupMstDATA");
			HisDAO dao = new HisDAO("billing", "GroupMstDATA");
			WebRowSet wb = null;
			
			int index = 0;
			try 
			{
				
				String Query = billing.qryHandler_billing.getQuery(1,"select.grp.5");
				index = dao.setQuery(Query);
				dao.setQryValue(index, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
				dao.setQryValue(index,2,chk1);
				wb = dao.executeQry(index);
				tempStr = hisutil.getOptionValue(wb, "0", "0^Select Value", false);
				
			} 
			catch (Exception e)
			{
				new HisException("Billing","packservMstDATA.getGlobalGroupCombo()", e.getMessage());
			}
			
			return tempStr;
		}	
	}

	

