package ipd.masters.bo;

import ipd.masters.dao.DeptDocumentMstDAO;
import ipd.masters.vo.DeptDocumentMstVO;
import hisglobal.exceptions.HisException;

public class DeptDocumentMstBO {

	/**
	 * First it will check duplicate record is there or not,<br>
	 * If duplicate record is there then it display a message "Record Not Saved!Data Already Exist!!"<br>
	 * If there is no duplicate record then it saved the record<br>
	 * And get back to the list page,<br>
	 * But if some error is occurs then it will show the message "Record Not Saved!!!"<br>
	 * 
	 * @param vo - FormBean Object
	 * @return - String
	 */
	public String InsertRecord(DeptDocumentMstVO vo) {
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";
	try {
	    	 fretValue = true;
			 if (fretValue) 
			   {
				fretValue = DeptDocumentMstDAO.insertQuery(vo);
				if (fretValue) {
					strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
				}
			}
		   else
		   {
			   strWarning = "Record Not Saved!Data Already Exist!!";
			   vo.setStrWarning(strWarning); 
		   }
		   
		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Department Document Master", "BODeptDocumentMst-->InsertRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			fretValue = false;
			strmsgText = "ipd.masters.BODeptDocumentMst.InsertRecord(vo) --> "+ e.getMessage();
			new HisException("IPD", "Department Document Master.Add", strmsgText);
			strErrmsg = "Record Not Saved!!!";
			vo.setStrErrorMsg(strErrmsg); 
		}
		return strTarget;
	}
	
	/**
	 * invokes modifyQuery Method.
	 * 
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param formBean - FormBean Object
	 */
	 public void modify(String strChk, DeptDocumentMstVO formBean) {
		   	try 
		   	{
		   		DeptDocumentMstDAO.modifyQuery(strChk, formBean);
			} catch (Exception e) {
				formBean.setStrErrorMsg(e.getMessage());
				   HisException eObj = new HisException("IPD-->Department Document Master", "BODeptDocumentMst-->modify()", formBean.getStrErrorMsg());
				   formBean.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				   eObj = null;
				}
	 		}
	 
	 
	 /**
	  * Update method returns true if Record Updated Successfully false if Record<br>
	  * Not Updated Successfully
	  * @param strChk - Primary Keys Concatenated with '@'.
	  * @param formBean -FormBean Object
	  * @return
	  */
	 public String update(String strChk, DeptDocumentMstVO formBean) {

		 String target = "list";
			boolean fretvalue;
			
			String msgText = "";

			try 
			{
				fretvalue = DeptDocumentMstDAO.updateQuery(strChk,formBean);
					 if(fretvalue==true)
					 formBean.setStrMsg("Data Updated");
					 else
						 formBean.setStrErrorMsg("Data is not saved");
			}
			catch (Exception e) {
				formBean.setStrErrorMsg(e.getMessage());
				   HisException eObj = new HisException("IPD-->Department Document Master", "BODeptDocumentMst-->update()", formBean.getStrErrorMsg());
				   formBean.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				   eObj = null;
				fretvalue = false;
				msgText = "ipd.masters.BODeptDocumentMst.UpdateRecord(chk,formBean) --> "
						+ e.getMessage();
				new HisException("IPD", "Department Document Master.Update",
						msgText);
			}
			return target;
		}
	 
	 /**
	  * To view the Record Selected from list page.
	  * @param chk1 - Primary Keys Concatenated with '@'.
	  * @param vo - FormBean Object
	  */
	 public void viewRecord(String chk1, DeptDocumentMstVO vo)  {
			try{
				DeptDocumentMstDAO.viewQuery(chk1, vo);
			}catch(Exception e){
				vo.setStrErrorMsg(e.getMessage());
				   HisException eObj = new HisException("IPD-->Department Document Master", "BODeptDocumentMst-->viewRecord()", vo.getStrErrorMsg());
				   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				   eObj = null;
		    }		
	 	}
}
