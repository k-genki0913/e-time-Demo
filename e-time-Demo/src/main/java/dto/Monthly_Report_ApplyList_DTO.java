package dto;

import java.sql.Date;

public class Monthly_Report_ApplyList_DTO {
	private Integer monthly_report_id;
	private Integer approve_level;
	private String approve_user_id_1;
	private String approve_role_1;
	private Integer approve_1;
	private String comment_1;
	private Date approve_date_1;
	private String approve_user_id_2;
	private String approve_role_2;
	private Integer approve_2;
	private String comment_2;
	private Date approve_date_2;
	private Date updated_date;
	
	public Monthly_Report_ApplyList_DTO() {}
	
	public Monthly_Report_ApplyList_DTO(Integer monthly_report_id, Integer approve_level, String approve_user_id_1, String approve_role_1, 
			Integer approve_1, String comment_1, Date approve_date_1, String approve_user_id_2, String approve_role_2, Integer approve_2, 
			String comment_2, Date approve_date_2, Date updated_date) {
		this.monthly_report_id = monthly_report_id;
		this.approve_level = approve_level;
		this.approve_user_id_1 = approve_user_id_1;
		this.approve_role_1 = approve_role_1;
		this.approve_1 = approve_1;
		this.comment_1 = comment_1;
		this.approve_date_1 = approve_date_1;
		this.approve_user_id_2 = approve_user_id_2;
		this.approve_role_2 = approve_role_2;
		this.comment_2 = comment_2;
		this.approve_date_2 = approve_date_2;
		this.updated_date = updated_date;
	}
	
	public void setMonthly_report_id(Integer monthly_report_id) {
		this.monthly_report_id = monthly_report_id;
	}
	
	public Integer getMonthly_report_id() {
		return this.monthly_report_id;
	}
	
	public void setApprove_level(Integer approve_level) {
		this.approve_level = approve_level;
	}
	
	public Integer getApprove_level() {
		return this.approve_level;
	}
	
	public void setApprove_user_id_1(String approve_user_id_1) {
		this.approve_user_id_1 = approve_user_id_1;
	}
	
	public String getApprove_user_id_1() {
		return this.approve_user_id_1;
	}
	
	public void setApprove_role_1(String approve_role_1) {
		this.approve_role_1 = approve_role_1;
	}
	
	public String getApprove_role_1() {
		return this.approve_role_1;
	}
	
	public void setApprove_1(Integer approve_1) {
		this.approve_1 = approve_1;
	}
	
	public Integer getApprove_1() {
		return this.approve_1;
	}
	
	public void setComment_1(String comment_1) {
		this.comment_1 = comment_1;
	}
	
	public String getComment_1() {
		return this.comment_1;
	}
	
	public void setApprove_date_1(Date approve_date_1) {
		this.approve_date_1 = approve_date_1;
	}
	
	public Date getApprove_date_1() {
		return this.approve_date_1;
	}
	
	public void setApprove_user_id_2(String approve_user_id_2) {
		this.approve_user_id_2 = approve_user_id_2;
	}
	
	public String getApprove_user_id_2() {
		return this.approve_user_id_2;
	}
	
	public void setApprove_role_2(String approve_role_2) {
		this.approve_role_2 = approve_role_2;
	}
	
	public String getApprove_role_2() {
		return this.approve_role_2;
	}
	
	public void setApprove_2(Integer approve_2) {
		this.approve_2 = approve_2;
	}
	
	public Integer getApprove_2() {
		return this.approve_2;
	}
	
	public void setComment_2(String comment_2) {
		this.comment_2 = comment_2;
	}
	
	public String getComment_2() {
		return this.comment_2;
	}
	
	public void setApprove_date_2(Date approve_date_2) {
		this.approve_date_2 = approve_date_2;
	}
	
	public Date getApprove_date_2() {
		return this.approve_date_2;
	}
	
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	
	public Date getUpdated_date() {
		return this.updated_date;
	}
	
	
}
