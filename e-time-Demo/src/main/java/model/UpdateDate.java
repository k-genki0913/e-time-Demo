package model;

import java.text.SimpleDateFormat;

public class UpdateDate {
	public java.sql.Date getUpdateDate() {
		java.util.Date utilDate = new java.util.Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String formatDate = sdf.format(utilDate);
		java.sql.Date sqlDate = java.sql.Date.valueOf(formatDate);
		
		return sqlDate;
	}
}
