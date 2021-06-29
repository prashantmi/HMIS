package ipd.masters.controller.hlp;

import ipd.masters.vo.WardCriteriaMstVO;

public class WardMstCriteriaHLP {

	/**
	 * By this method user gets the value of gender,age(from-to) and unit(months,days and years).
	 * @param vo - Form Bean Object
	 * @param index - it is an integer value
	 * @return - it returns gender value, age from and age to and unit value(months,days and years).
	 * @throws Exception
	 */
	public String getMultiRow(WardCriteriaMstVO vo, int index) throws Exception
	{   
        StringBuffer sBuffer = new StringBuffer("");
        try
        {
        	if(!vo.getStrGender().equals(null) || vo.getStrGender() != null)
        	{
        		vo.setNtotalRows(vo.getStrGender().length);
    	        for (int i = 1; i <= vo.getNtotalRows(); i++)
    	        {    	        	       	
    	        	  vo.setStrtempGender(vo.getStrGender()[i -1]);                       
    	              sBuffer.append("<div id=\"id"+index+"-"+i+"\" >");
    	              sBuffer.append("<table class='TABLEWIDTH' align='center'" + " border='0'> <tr><td width='35%' class='multiControl'><select name='strGender' class='comboNormal'");
    	              sBuffer.append("id='strGender"+index+"-"+i+"'>");
    	              sBuffer.append(vo.getComboGender());
    	              sBuffer.append("</select></td>");
    	              sBuffer.append("<td width='15%' class='multiControl'>");    
    	              sBuffer.append("<input type='text' name='strFromAge' class='txtFldSmall' maxlength ='3' id='strFromAge"+index+"-"+i+"' value='"+vo.getStrFromAge()[i-1]+"' onkeypress ='return validateData(event,5);'");
    	              sBuffer.append(" onfocusout='ageValidationModification(this,\""+index+"-"+i+"\");'>") ;
    	              sBuffer.append("</td>");
       	              sBuffer.append("<td width='15%' class='multiControl'>");              
    	              sBuffer.append("<input type='text' name='strToAge' class='txtFldSmall' maxlength ='3' id='strToAge"+index+"-"+i+"' value='"+vo.getStrToAge()[i-1]+"' onkeypress ='return validateData(event,5);'");
    	              sBuffer.append(" onfocusout='ageValidationModification(this,\""+index+"-"+i+"\");'>") ;
      	              sBuffer.append("<td class='multiControl'><select name ='strFunit' width ='25%' class='comboNormal' id = 'strFunit"+index+"-"+i+"'");
      	              sBuffer.append(" onfocusout='ageValidation(this,\""+index+"-"+i+"\");'>") ;		
      	              sBuffer.append("<option value ='0'> Select Unit</option>");
      	              if(vo.getStrFunit()[i-1].equals("1"))
      	              {
      	            	sBuffer.append("<option value ='1' selected='selected'>Days</option>");
      	            	sBuffer.append("<option value ='2' >Months</option>");
      	            	sBuffer.append("<option value ='3' >Year</option>");      	              
      	              }
      	              else if(vo.getStrFunit()[i-1].equals("2"))
      	              {      	         
      	            	sBuffer.append("<option value ='1' >Days</option>");
      	            	sBuffer.append("<option value ='2'selected='selected' >Months</option>");
      	            	sBuffer.append("<option value ='3' >Year</option>");      	            	  
      	              }
      	              else if(vo.getStrFunit()[i-1].equals("3"))
      	              {
      	            	sBuffer.append("<option value ='1' >Days</option>");
      	            	sBuffer.append("<option value ='2' >Months</option>");
      	            	sBuffer.append("<option value ='3' selected='selected'>Year</option>");
      	              }      	                 
      	              sBuffer.append("</select>");
      	             // sBuffer.append(vo.getStrFunit());
     	              sBuffer.append("</select></td>");
    	              sBuffer.append("<td width='10%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
    	              sBuffer.append(" onClick=\"deleteRow('"+index+"-"+i+"','"+index+"','0');\"></td></tr></table>");
    	              sBuffer.append("</div>");
    	        }
            }             	
        } 
        catch(Exception e)
        {
        	//e.printStackTrace();
        }
        vo.setStrtempGender("0");        
        return sBuffer.toString();
	}
}