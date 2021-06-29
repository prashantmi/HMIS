<table>  
  <tr>
      <td width="20%"  class="tdfonthead">
	           <div align="center">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <b><bean:message key="unit"/></b>
	              </font>
	            </div>
      </td>      
      <td width="20%"  class="tdfont">
	         <div align="center">	           
	         	<html:text name="AddUnitFB" property="unitName"/>	         
	         </div>
      </td>                                           
  </tr>	   
  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveDate"/></b>
		              </font>
		            </div>
	   </td>      
	   <td>    
	    	<his:date name='<%="effectiveDate"%>' dateFormate="%d-%b-%Y" />
	   </td>
  </tr>
  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="inactiveFrom"/></b>
		              </font>
		            </div>
	   </td>      
	   <td>    
	    	<his:date name='<%="inactiveFromDate"%>' dateFormate="%d-%b-%Y" />
	   </td>
  </tr>
    
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="inactiveTill"/></b>
		              </font>
		            </div>
	   </td>      
	   <td>    
	    	<his:date name='<%="inactiveTillDate"%>' dateFormate="%d-%b-%Y" />
	   </td>
  </tr>  
</table>