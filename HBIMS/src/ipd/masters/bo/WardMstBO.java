package ipd.masters.bo;

import ipd.masters.dao.WardMstDAO;
import ipd.masters.vo.WardMstVO;
import hisglobal.exceptions.HisException;

public class WardMstBO {
	
	/**
	 * insert data into ward ,ward Room and ward criteria master
	 * @param vo - Ward Master Value Object 
	 * @return - target String for navigate to add page
	 */
	public String InsertRecord(WardMstVO vo) 
	{
		String target = "";
		boolean retvalue=false;
		boolean retvalue1=false;
        String msg ="";
        String strErrorMsg ="";
		try
		{
			String tempwardcode = WardMstDAO.getWardKey(vo);
			retvalue1=WardMstDAO.checkDuplicate(vo);//DUPLICATE CHECK IN SAME HOSPITAL ONLY
			
		 	if(retvalue1)
		 	{
		 		retvalue = WardMstDAO.insertData(vo,tempwardcode);
		 		if (!retvalue) 
		 		{
					strErrorMsg = "Record not saved successfully!";
					vo.setStrErrorMsg(strErrorMsg);
					target = "add";
		 		} 
		 		else 
		 		{
		 			msg ="Record Saved Successfully!";
		 			vo.setMsg(msg);
					target = "add";
		 		}
		 	}
		 	else
		 	{
		 		vo.setWarnings("Data is already exist!!!");
		 		target = "add";
		 	}       
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			vo.setStrErrorMsg(e.getMessage());
			HisException eObj = new HisException("IPD-->Ward Master", "BOWardMst-->InsertRecord()", vo.getStrErrorMsg());
			vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
	    }		
		return target;
	}
  
	/**
	 * This function is used to invoke DAOWardMst's  modifyQuery()
	 * @param chk1
	 * @param vo
	 */
	public void modifyRecord(String chk1, WardMstVO vo)  {
		try{
		   
			WardMstDAO.modifyQuery(chk1, vo);
		}catch(Exception e){
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Master", "BOWardMst-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	    }		
 	}
 
	public void viewRecord(String chk1, WardMstVO vo)  {
		try{
		  WardMstDAO.viewQuery(chk1, vo);
			
		}catch(Exception e){
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Master", "BOWardMst-->viewRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	    }		
 	}
	
	/**
	 * this function is used for updating records.there are two options correction and
	modification.
	 * @param chk1
	 * @param vo
	 * @return
	 */
	public boolean updateRecord(String chk1, WardMstVO vo)  
	{
		String strErrorMsg = "";
		boolean target = false;
		boolean retvalue = false;
		//boolean retvalue1 = false;// check in case of correction
		//boolean retvalue2 = false; // check for first modification condition
		//boolean retvalue3; // check for second modification condition
		boolean retvalue4=false;
		try
		{
			retvalue4=WardMstDAO.checkDuplicateModify(chk1, vo);			
					
			if(retvalue4)
			{
				 if (Integer.parseInt(vo.getUpdateMode()) == 0)// correction
				 {
					//retvalue1 = WardMstDAO.initialUpdateCorrection(chk1, vo);//check if record exists					
					//if (retvalue1)
					//{
						retvalue = WardMstDAO.updateCorrection(chk1, vo);						
						if(!retvalue)
						{
							target=false;
							return target;
						}
					/*}
					else 
					{
							strErrorMsg = "Effective To Date (" + vo.getStrEffectiveTo()
								+ ") exists between the "
								+ "effective From Date and effective To date of "
								+ "existing records";

							vo.setWarnings(strErrorMsg);
							target = false;
							return target;
					}*/
				} 
				else 
				{		
						//retvalue2 = WardMstDAO.initialUpdateModi1(chk1, vo);
						//if (retvalue2) 
						//{
							//retvalue3 = WardMstDAO.initialUpdateModi2(chk1, vo);
							//if (retvalue3) 
							//{
								retvalue = WardMstDAO.updateModification(chk1, vo);
							//} 
							/*else 
							{
										strErrorMsg = "Effective To Date ("
										+ vo.getStrEffectiveTo() + ") exists between the "
										+ "effective From Date and effective To date of "
										+ "existing records";
										vo.setWarnings(strErrorMsg);
										target = false;
										return target;
							}*/

						//} 
						/*else 
						  {
									strErrorMsg = "Effective FROM Date ("
									+ vo.getStrEffectiveFrom() + ") exists between the "
									+ "effective From Date and effective To date of "
									+ "existing records";
									vo.setWarnings(strErrorMsg);
									target = false;
									return target;
							}*/
					}		
					if (!retvalue) 
					{
							strErrorMsg = "Record not modified !";
							vo.setStrErrorMsg(strErrorMsg);
							target = false;
					} 
					else 
					{
								target = true;
					}
			}
			else
			{
				vo.setWarnings("Record already exist!!!");
				target = false;
			}
		}
		catch(Exception e)
		{
				vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("ADT", "WardMstBO-->updateRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	    }		
		return target;
	}	
}