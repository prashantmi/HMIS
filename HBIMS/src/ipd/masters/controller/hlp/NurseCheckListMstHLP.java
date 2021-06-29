package ipd.masters.controller.hlp;

import ipd.masters.vo.NurseCheckListMstVO;

public class NurseCheckListMstHLP {

	/**
	 * returns Nurse Check List MultiRow Html Contents based on the index passed.
	 * @param formBean
	 * @param index
	 * @return Emergency MultiRow Contests.
	 */
	public String getMultiRow(NurseCheckListMstVO formBean, int index){
		
			StringBuffer sBuffer = new StringBuffer("");
			int gLen = 0;
			if(formBean.getStrDepartment() == null){
				
				gLen = 0;
				
			}else{
				gLen = formBean.getStrDepartment().length;
				
			}
			formBean.setNTotalRows(gLen);
			
			for (int i = 1; i <= formBean.getNTotalRows(); i++) {
				
				formBean.setStrDeptTempName(formBean.getStrDepartment()[i -1]);
				formBean.setStrUnitTempName(formBean.getStrUnit()[i-1]);
				
				
				//sBuffer.append("<div id=\"id"+index+"-"+i+"\" >");
				sBuffer.append(" <table class='TABLEWIDTH' align='center'> <tr><td width='50%' class='multiControl'><select name='strDepartment' class='comboMax'");
				sBuffer.append(" id='strDepartment"+index+"-"+i+"' onChange=\"myFunc('1',this,'"+index+"-"+i+"');\">");
				sBuffer.append(formBean.getStrDeptmentValues());
				sBuffer.append("</select></td>");
				sBuffer.append("<td width='44%' class='multiControl'>");	
				sBuffer.append("<div id='unitId"+index+"-"+i+"'> <select name='strUnit' class='comboMax' id='strUnit"+index+"-"+i+"'>");
				sBuffer.append(formBean.getStrUnitValues());
				sBuffer.append("<td width='6%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
				sBuffer.append(" onClick=\"deleteRow('"+index+"-"+i+"','"+index+"','1');\"></td></tr></table>");
				//sBuffer.append("</div>");
				
			}
			
			formBean.setStrDeptTempName("0");
			formBean.setStrUnitTempName("0");
			
		return sBuffer.toString();
	}
	
}
