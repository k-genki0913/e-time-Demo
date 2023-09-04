package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.ReportErrorMsg;

public class ReportInputCheck {
	public ReportErrorMsg checkError(String start_time, String end_time, String break_time) {
		ReportErrorMsg reportErrorMsg = new ReportErrorMsg();
		
		String regex_num = "^\\d{4}";
		
		Pattern pattern = Pattern.compile(regex_num);
		Matcher start_time_matcher = pattern.matcher(start_time);
		if(!start_time_matcher.matches()) {
			reportErrorMsg.setStart_time_errorMsg("開始時間の値が間違っております。数字4桁で入力してください");
		}
		
		Matcher end_time_matcher = pattern.matcher(end_time);
		if(!end_time_matcher.matches()) {
			reportErrorMsg.setEnd_time_errorMsg("終了時間の値が間違っております。数字4桁で入力してください");
		}
		
		String regex_num1 = "^\\d{1,3}";
		Pattern pattern1 = Pattern.compile(regex_num1);
		Matcher break_time_matcher = pattern1.matcher(break_time);

		if(!break_time_matcher.matches()) {
			reportErrorMsg.setBreak_time_errorMsg("休憩時間の値に誤りがある可能性があります。0〜999の値で入力してください");
		}
		return reportErrorMsg;
	}
}
