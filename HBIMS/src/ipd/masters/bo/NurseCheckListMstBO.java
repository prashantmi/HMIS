package ipd.masters.bo;

import ipd.masters.dao.NurseCheckListMstDAO;
import ipd.masters.vo.NurseCheckListMstVO;
import hisglobal.exceptions.HisException;

public class NurseCheckListMstBO {

	/**
	 * invokes DAONurseCheckListMst insertQuery Method.
	 * 
	 * @param formBean -
	 *            Form Object of the Current Master
	 * 
	 */
	public void insert(NurseCheckListMstVO formBean) {
		
		boolean fReturnValue;

		try {
			 fReturnValue = NurseCheckListMstDAO.isInsertExists(formBean);

			 if (!fReturnValue) {

			fReturnValue = NurseCheckListMstDAO.insertQuery(formBean);

			if (fReturnValue) {
				formBean.setStrMsg("Data Inserted Successfully");
			} else {
				formBean.setStrErr("Record Not Inserted");
			}
			
			 } else { formBean.setStrWarning("Check List Name '" +
				formBean.getStrCheckListName() + "' Already Exists"); }
			 

		} catch (Exception e) {
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD--Nurse Check List", "BONurseCheckListMst-->insert()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}

	/**
	 * used to concatenate department and unit with <code>@</code> and set of
	 * department, units with <code>#</code>.
	 * @param formBean-
	 *            Form Object of the Current Master
	 * @return department and unit concatenated String
	 */
	public String appendDeptUnits(NurseCheckListMstVO formBean) {

		StringBuffer strDeptUnits = new StringBuffer("");
		if (formBean.getStrDepartment() != null
				&& !formBean.getStrCheckListType().equals(1)) {
			int nLen = formBean.getStrDepartment().length;
			if (nLen != 0) {

				for (int ni = 0; ni < nLen; ni++) {

					if (ni != 0)
						strDeptUnits.append("#");
/*
					strDeptUnits.append(formBean.getStrDepartment()[ni])
							.append("@").append(formBean.getStrUnit()[ni]);
	*/				
					strDeptUnits.append(formBean.getStrUnit()[ni]);

				}

			}
		}
		return strDeptUnits.toString();
	}

	/**
	 * returns array of departments or units based on index.
	 * @param formBean -  Form Object of the Current Master.
	 * @param index  0 - represents Departments. <br> 1 - represents Units.
	 * @return
	 */
	public String[] splitDeptUnits(String strTempDeptAndUnit, int index) {
		
		String[] strDeptUnits = null;
		
		if(strTempDeptAndUnit != null){
			String strTempDeptUnit[] = strTempDeptAndUnit.split("#");

			 strDeptUnits = new String[strTempDeptUnit.length];
			
				for(int ni = 0 ; ni < strTempDeptUnit.length; ni++){

					//String[] strTemp = strTempDeptUnit[ni].replace("@", "#").split("#");
					//String[] strTemp = strTempDeptUnit[ni].substring(0,3);
					System.out.println("strTempDeptUnit[ni].substring(0,3) = " + strTempDeptUnit[ni].substring(0,3));
					if(index == 0)
						strDeptUnits[ni] = strTempDeptUnit[ni].substring(0,3);
					else
						strDeptUnits[ni] = strTempDeptUnit[ni];
				}
			}
			
		return strDeptUnits;
	}
	
	/**
	 * invokes DAONurseCheckListMst modifyQuery Method.
	 * @param strChk - Primary Keys Concatenated with '@'. 
	 * @param formBean - Form Object of the Current Master
	 */
	public void modify(String strChk, NurseCheckListMstVO formBean) {

		String strChecklistData=""; 
		String strTemp1[]=null;
		String strTemp2[][]=null;
		String strDept[]=null;
		String strDeptUnit[]=null;
		try {
			NurseCheckListMstDAO.modifyQuery(strChk, formBean);
			if(!(formBean.getStrCheckListType().equals("1")))
			{
				strChecklistData=formBean.getStrCheckListFor();
				/**
				 * If strChecklistData contains It means nurseChecklist Data exist more than one department 
				 */
				if(strChecklistData.contains("#"))
				{
					
					strTemp1=strChecklistData.split("#");
					strDept=new String [strTemp1.length];
					strDeptUnit=new String[strTemp1.length];
					strTemp2=new String [strTemp1.length][];
					
					for(int i=0;i<strTemp1.length;i++)
					{
						strTemp2[i]=strTemp1[i].replace('@', '#').split("#");
						
					}
					for(int i=0;i<strTemp1.length;i++)
					{
						strDept[i]=strTemp2[i][0];
						strDeptUnit[i]=strTemp2[i][1];
					}
					formBean.setStrDepartment(strDept);
					formBean.setStrUnit(strDeptUnit);
				}
				else
				{
					strDept=new String [1];
					strDeptUnit=new String[1];
					strChecklistData=formBean.getStrCheckListFor();
					strTemp1=strChecklistData.replace('@', '#').split("#");
					strDept[0]=strTemp1[0];
					strDeptUnit[0]=strTemp1[1];
					formBean.setStrDepartment(strDept);
					formBean.setStrUnit(strDeptUnit);
				}
		
			}
		} catch (Exception e) {
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD--Nurse Check List", "BONurseCheckListMst-->modify()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
	
	/**
	 * Update method returns true if Record Updated Successfully false if Record
	 * Not Updated Successfully
	 */
	public boolean update(String strChk, NurseCheckListMstVO formBean) {

		boolean fReturnValue = false;

		try {
			 fReturnValue = NurseCheckListMstDAO.isUpdateExists(formBean, strChk);

			 if (!fReturnValue) {

			fReturnValue = NurseCheckListMstDAO.updateQuery(strChk,formBean);

			if (fReturnValue) {
				formBean.setStrMsg("Record Updated Successfully");
			} else {
				formBean.setStrErr("Record Not Updated");
			}
			
			 } else { 
				 fReturnValue = false;
				 formBean.setStrWarning("Check List Name '" +
				formBean.getStrCheckListName() + "' Already Exists"); }
			 

		} catch (Exception e) {
			formBean.setStrErr("Record Not Updated");
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD--Nurse Check List", "BONurseCheckListMst-->update()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		
		return fReturnValue;
	}
	
}
