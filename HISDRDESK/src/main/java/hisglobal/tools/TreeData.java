package hisglobal.tools;

public class TreeData extends hisglobal.utility.HisMethods
{
	private String query = "";

	public hisglobal.persistence.HisResultSet execute() throws Exception
	{
		query = "SELECT      a.hgbstr_icd_group_code" + "  || ' '" + "  || INITCAP (a.hgbstr_icd_group) icdgroup," + "     b.hgbstr_icd_subgrp_code "
				+ "  || ' ' " + "  || INITCAP (b.hgbstr_icd_subgroup) icdsubgroup," + "  c.hgbstr_disease_code || ' ' "
				+ "  || INITCAP (c.hgbstr_disease) icddisease " + "  FROM hgbt_icd_disease_mst c, hgbt_icd_group_mst a,"
				+ "  hgbt_icd_subgroup_mst b " + "	 WHERE a.hgbstr_icd_group_code = b.hgbstr_icd_group_code "
				+ "	AND b.hgbstr_icd_subgrp_code = c.hgbstr_icd_subgrp_code " + "	ORDER BY icdgroup, icdsubgroup, icddisease";
		System.out.println("the query is:" + query);
		return (getRecord(query));
	}

}
