package model;

import dao.Clock_in_DAO;
import dto.Clock_in_DTO;

public class ReportRegist {
	public String reportUpdate(Clock_in_DTO dto) {
		Clock_in_DAO clock_in_dao = new Clock_in_DAO();
		
		boolean result = clock_in_dao.updateClock_in_DAO(dto);
		
		if(!result) {
			return "勤怠の更新に失敗しました";
		} else {
			return "";
		}
	}
	
	public String reportInsert(Clock_in_DTO dto) {
		Clock_in_DAO clock_in_dao = new Clock_in_DAO();
		
		boolean result = clock_in_dao.insertClock_in_DAO(dto);
		
		if(!result) {
			return "勤怠の追加に失敗しました";
		} else {
			return "";
		}
	}
}
