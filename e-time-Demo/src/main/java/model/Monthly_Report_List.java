package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.Monthly_Report_ApplyList_DAO;
import dao.Monthly_Report_DAO;
import dto.Monthly_Report_ApplyList_DTO;
import dto.Monthly_Report_DTO;

public class Monthly_Report_List {
	public List<Monthly_Report_DTO> get(String user_id){
		List<Monthly_Report_ApplyList_DTO> list = new Monthly_Report_ApplyList_DAO().getList(user_id);
		List<Integer> monthly_report_id_list = new ArrayList<>();
		for(Monthly_Report_ApplyList_DTO dto: list) {
			monthly_report_id_list.add(dto.getMonthly_report_id());
		}
		List<Monthly_Report_DTO> monthly_report_list = new Monthly_Report_DAO().getList(monthly_report_id_list);
		
		Collections.sort(monthly_report_list, (x, y) -> (x.getMonthlyReport_id() - y.getMonthlyReport_id()));
		
		return monthly_report_list;
	}
}
