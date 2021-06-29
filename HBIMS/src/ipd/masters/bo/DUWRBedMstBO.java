package ipd.masters.bo;

import ipd.masters.dao.DUWRBedMstDAO;
import ipd.masters.vo.DUWRBedMstVO;
import hisglobal.exceptions.HisException;

public class DUWRBedMstBO {
	
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
	public void insert(DUWRBedMstVO duwrBedMstVO_p) {

		boolean fReturnValue;
		
		try 
			 {
			DUWRBedMstDAO.updateQuerybeforeinsert(duwrBedMstVO_p); 
				fReturnValue = DUWRBedMstDAO.insertQuery(duwrBedMstVO_p); 
				 if(fReturnValue==true)
				 duwrBedMstVO_p.setStrMsg("Record Saved Successfully");
				 else
					 duwrBedMstVO_p.setStrErr("Record not saved!");
			 } catch (Exception e) {
				 duwrBedMstVO_p.setStrErr(e.getMessage());
				   HisException eObj = new HisException("IPD-->DUWR Bd Master", "DUWRBedMstBO-->insert()", duwrBedMstVO_p.getStrErr());
				   duwrBedMstVO_p.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				   eObj = null;
		}
	}
	
	/**
	  * To view the Record Selected from list page.
	  * @param chk1 - Primary Keys Concatenated with '@'.
	  * @param vo - FormBean Object
	  */
	public void viewRecord(String chk1, DUWRBedMstVO vo)  {
		try{
		   DUWRBedMstDAO.viewQuery(chk1, vo);
		}catch(Exception e){
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->DUWR Bd Master", "DUWRBedMstBO-->viewRecord()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	    }		
 	}

	/**
	 * invokes modifyQuery Method.
	 * 
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param duwrBedMstVO_p - FormBean Object
	 */			 
  public void modify(String strChk, DUWRBedMstVO duwrBedMstVO_p) {
	   	try 
	   	{
			DUWRBedMstDAO.modifyQuery(strChk, duwrBedMstVO_p);
		} catch (Exception e) {
			duwrBedMstVO_p.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->DUWR Bd Master", "DUWRBedMstBO-->modify()", duwrBedMstVO_p.getStrErr());
			   duwrBedMstVO_p.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
  }
	
  /**
	  * Update method returns true if Record Updated Successfully false if Record<br>
	  * Not Updated Successfully
	  * @param strChk - Primary Keys Concatenated with '@'.
	  * @param duwrBedMstVO_p -FormBean Object
	  * @return
	  */
	public boolean update(String strChk, DUWRBedMstVO duwrBedMstVO_p) {

		boolean fReturnValue = false;

		try 
		{
				 fReturnValue = DUWRBedMstDAO.updateQuery(strChk,duwrBedMstVO_p);
				 if(fReturnValue==true)
				 duwrBedMstVO_p.setStrMsg("Data Updated");
				 else
					 duwrBedMstVO_p.setStrErr("Data is not saved");
		}
		catch (Exception e) {
			duwrBedMstVO_p.setStrErr("Record Not Updated");
			duwrBedMstVO_p.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->DUWR Bd Master", "DUWRBedMstBO-->update()", duwrBedMstVO_p.getStrErr());
			   duwrBedMstVO_p.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fReturnValue;
	}
	


}
