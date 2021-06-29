<%@page import="new_investigation.MachineInterfaceDatabaseObject"%>
<%@page import="hisglobal.persistence.JDBCTransactionContext"%>

 
	
   <%
   JDBCTransactionContext tx = new JDBCTransactionContext();
   String ret = "";
   String type = request.getParameter("type");
   try
   {
		
		tx.begin();
		
   		MachineInterfaceDatabaseObject objMachineInterfaceDatabaseObject = new MachineInterfaceDatabaseObject(tx);
   		if(type != null && type.equals("1"))
   		{
   	    	ret = objMachineInterfaceDatabaseObject.insertData(request, response);
   		}//out.write(ret);
   		else if(type != null && type.equals("2"))
   		{
   			ret = objMachineInterfaceDatabaseObject.SelectData(request, response)	;
   		}
   		else if(type != null && type.equals("3"))
   		{
   			ret = objMachineInterfaceDatabaseObject.SelectDataTYPE3(request, response)	;
   		}
   		else if(type != null && type.equals("5"))
   		{
   		/* Thread t=	new Thread(new Runnable() {
   		      public void run() { */
   		    	ret = objMachineInterfaceDatabaseObject.insertMultipleData(request, response)	;  
   		    /*   }
   			});
   		t.start();	 */
   		}
   		
	}

    catch(Exception e)
    {
     e.printStackTrace(new java.io.PrintWriter(out));
	 }
		
	finally
	{
		tx.close();
	}
   response.reset();
	response.flushBuffer();
	response.setContentType("application/json");	
	response.setHeader("Content-Disposition", "inline");
	response.getWriter().write(ret);
   %>
