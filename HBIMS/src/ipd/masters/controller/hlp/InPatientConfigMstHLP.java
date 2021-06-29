package ipd.masters.controller.hlp;

import ipd.masters.vo.InPatientConfigMstVO;

public class InPatientConfigMstHLP {

	public String getGenWardMultiRow(InPatientConfigMstVO formBean, int index){
		
		StringBuffer sBuffer = new StringBuffer("");
		int gLen = 0;
		if(formBean.getStrGenWardApprover() == null){
			
			gLen = 0;
			
		}else{
			gLen = formBean.getStrGenWardApprover().length;
			
		}
		formBean.setNTotalRows(gLen);
		
		for (int i = 1; i <= formBean.getNTotalRows(); i++) {
			
			formBean.setStrGenWardTempName(formBean.getStrGenWardApprover()[i -1]);
			
			
			sBuffer.append("<div id=\"id"+index+"-"+i+"\" >");
			sBuffer.append("<table align='center' class='TABLEWIDTH' cellpadding='1px' cellspacing='1px'> <tr><td width='94%' class='multiControl'><select name='strGenWardApprover' class='comboBig' onChange='showName(this)'");
			sBuffer.append("id='strGenWardApprover"+index+"-"+i+"'>");
			sBuffer.append("<option value='0'>Select Value</option>");
		//	sBuffer.append(formBean.getStrGenWardApproverValues());
			sBuffer.append("</select></td>");
			sBuffer.append("<td width='6%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
			sBuffer.append(" onClick=\"deleteRow('"+index+"-"+i+"','"+index+"','1');\"></td></tr></table>");
			sBuffer.append("</div>");
			
		}
		
		formBean.setStrGenWardTempName("0");
		
		
	return sBuffer.toString();
}
	
public String getPrivateWardMultiRow(InPatientConfigMstVO formBean, int index){
		
		StringBuffer sBuffer = new StringBuffer("");
		int gLen = 0;
		if(formBean.getStrPrivateWardApprover() == null){
			
			gLen = 0;
			
		}else{
			gLen = formBean.getStrPrivateWardApprover().length;
			
		}
		formBean.setNTotalRows(gLen);
		
		for (int i = 1; i <= formBean.getNTotalRows(); i++) {
			
			formBean.setStrPrivateWardTempName(formBean.getStrPrivateWardApprover()[i -1]);
			
			
			sBuffer.append("<div id=\"id"+index+"-"+i+"\" >");
			sBuffer.append("<table align='center' class='TABLEWIDTH' cellpadding='1px' cellspacing='1px'> <tr><td width='94%' class='multiControl'><select name='strPrivateWardApprover' class='comboBig' onChange='showName(this)'");
			sBuffer.append("id='strPrivateWardApprover"+index+"-"+i+"'>");
			sBuffer.append("<option value='0'>Select Value</option>");
			//sBuffer.append(formBean.getStrPrivateWardApproverValues());
			sBuffer.append("</select></td>");
			sBuffer.append("<td width='6%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
			sBuffer.append(" onClick=\"deleteRow('"+index+"-"+i+"','"+index+"','1');\"></td></tr></table>");
			sBuffer.append("</div>");
			
		}
		
		formBean.setStrPrivateWardTempName("0");
		
		
	return sBuffer.toString();
}
	
}
