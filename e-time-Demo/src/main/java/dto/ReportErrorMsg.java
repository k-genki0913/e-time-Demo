package dto;

public class ReportErrorMsg {
	private String start_time_errorMsg;
	private String end_time_errorMsg;
	private String break_time_errorMsg;
	
	public ReportErrorMsg() {}
	
	public String getStart_time_errorMsg() {
		return this.start_time_errorMsg;
	}
	
	public void setStart_time_errorMsg(String errorMsg) {
		this.start_time_errorMsg = errorMsg;
	}
	
	public String getEnd_time_errorMsg() {
		return this.end_time_errorMsg;
	}
	
	public void setEnd_time_errorMsg(String errorMsg) {
		this.end_time_errorMsg = errorMsg;
	}
	
	public String getBreak_time_errorMsg() {
		return this.break_time_errorMsg;
	}
	
	public void setBreak_time_errorMsg(String errorMsg) {
		this.break_time_errorMsg = errorMsg;
	}
}
