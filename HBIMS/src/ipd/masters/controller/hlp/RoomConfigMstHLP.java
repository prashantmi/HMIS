/**
 * 
 */
package ipd.masters.controller.hlp;

import ipd.masters.vo.RoomConfigMstVO;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.HisUtil;

/**
 * @author Administrator
 *
 */
public class RoomConfigMstHLP {
	
	public static String getSelectedPropertyComboValues(RoomConfigMstVO vo)
	throws Exception{        
		   
		String cmb = "";
		HisUtil util = null;
		WebRowSet ws=null;
        StringBuffer sBuffer = new StringBuffer("");
        try{
        	ws=vo.getPropertyComboWs();
        	util = new HisUtil("IPD","HLPRoomConfigMst");
        	
        	if(vo.getStrPropertyId()!=null || !vo.getStrPropertyId().equals("")){        	
    	       
        		
        	int length=vo.getStrPropertyId().length;
        	 sBuffer.append("<input name='persistPropertyLength' type='hidden' value='"+length+"' />");
    	        for (int i = 1; i<= length; i++) {              
    	    
    	        	                
    	              sBuffer.append("<div id=\"id1-"+i+"\" >");
    	             
    	             
    	              sBuffer.append("<table width='300' cellspacing='1'>" +
    	              		" <tr><td class='multiControl' width='98%'><select name='strPropertyId' id='strPropertyId1-"+i+"'>");
    	              
    	      			cmb = util.getOptionValue(ws,vo.getStrPropertyId()[i-1], "0^Select Value", false);
    	      		
    	      		vo.setStrPropertyComboValues(cmb);
    	              sBuffer.append(vo.getStrPropertyComboValues());
    	              sBuffer.append("</select></td>");
			

    	              sBuffer.append("<td width='2%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
    	              sBuffer.append(" onClick=\"deleteRow('"+1+"-"+i+"','"+1+"','0');\"></td>");
    	              sBuffer.append("<TD><input type='hidden' name='strMultiRowMode' id='strMultiRowMode1-"+i+"' ></TD></tr></table>");
    	              sBuffer.append("</div>");
    	              ws.beforeFirst();
    	        }
            }
        	      		
             	
        } catch(Exception e){
        	e.printStackTrace();
        }
       
        
        return sBuffer.toString();
	}

}
