function buttonLogicsOnClick1(modeNo, mode , display)
{
	try
	{
		if(document.getElementById("comboid0").value=="0")
		{
			alert("! Please Select Hospital Name.");
			return ;
		}
				if(mode=='StockInHand' || mode=='StockInHand')
		{
			add(mode);
		}		
		else if(mode=='InstallationStatistical' || mode=='InstallationStatistical')
		{
			add(mode);
		}
		else if(mode=='Down-UP' || mode=='Down-UP')
		{	
			add("DownUPTime");
		}
		else if(mode=='Inspection-Test' || mode=='Inspection-Test')
		{	
			add("InspectionTest");
		}
		else if(mode=='ComplaintsStatistical' || mode=='ComplaintsStatistical')
		{	
			add(mode);
		}
		else if(mode=='MaintananceStatistical' || mode=='MaintananceStatistical')
		{	
			add(mode);
		}
		else if(mode=='WarrantyStatistical' || mode=='WarrantyStatistical')
		{	
			add(mode);
		}
		else if(mode=='EstimationStatistical' || mode=='EstimationStatistical')
		{	
			add(mode);
		}
		else if(mode=='CostingStatistical' || mode=='CostingStatistical')
		{
	     	add(mode);
		}
		else if(mode=='PlanningStatistical' || mode=='PlanningStatistical')
		{
	     	add(mode);
		}
		else if(mode=='BudgetStatistical' || mode=='BudgetStatistical')
		{
	     	add(mode);
		}
		else if(mode=='ExpenditureStatistical' || mode=='ExpenditureStatistical')
		{
	     	add(mode);
		}
		else if(mode=='RevenueStatistical' || mode=='RevenueStatistical')
		{
	     	add(mode);
		}
		else if(mode=='AuctionStatistical' || mode=='AuctionStatistical')
		{
	     	add(mode);
		}

	}
	catch(Err)
	{
		alert(Err);
	}
}