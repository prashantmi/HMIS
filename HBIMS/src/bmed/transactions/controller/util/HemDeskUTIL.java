package bmed.transactions.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.transactions.controller.data.HemDeskDATA;
import bmed.transactions.controller.fb.HemDeskFB;

public class HemDeskUTIL 
{

	public static String initializeGrievances(HemDeskFB HemDeskFB_p,HttpServletRequest request_p) 
	{
		return HemDeskDATA.initializeGrievances(HemDeskFB_p, request_p);
	}
	
	public static void getEnggItemSubType(HemDeskFB formBean_p,HttpServletRequest request_p, HttpServletResponse response_p)
	{
		HemDeskDATA.getEnggItemSubType(formBean_p, request_p, response_p);
	}

	
	public static void getServiceEnggName(HemDeskFB formBean_p,HttpServletRequest request_p, HttpServletResponse response_p)
	{
		HemDeskDATA.getServiceEnggName(formBean_p, request_p, response_p);
	}
	
	public static void getEscLevel(HemDeskFB formBean_p,HttpServletRequest request_p, HttpServletResponse response_p)
	{
		HemDeskDATA.getEscLevel(formBean_p, request_p, response_p);
	}
	
	public static void getDetails(HemDeskFB formBean_p,HttpServletRequest request_p, HttpServletResponse response_p)
	{
		HemDeskDATA.getDetails(formBean_p, request_p, response_p);
	}
	
	public static void save(HemDeskFB formBean_p,HttpServletRequest request_p)
	{
		
		HemDeskDATA.save(formBean_p, request_p);
	}
}
