package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.ConstractErrorMsg;

public class ConstractInputCheck {
	public ConstractErrorMsg checkError(String workplace, String start_time, String end_time, String break_time) {
		ConstractErrorMsg constractErrorMsg = new ConstractErrorMsg();
		
		if(workplace == null || workplace.length() == 0) {
			constractErrorMsg.setWorkplace_errorMsg("勤務先を入力してください");
		}
		
		String regex_num = "^\\d{4}";
		
		Pattern pattern = Pattern.compile(regex_num);
		Matcher start_time_matcher = pattern.matcher(start_time);
		if(!start_time_matcher.matches()) {
			constractErrorMsg.setStart_time_errorMsg("開始時間の値が間違っております。数字4桁で入力してください");
		}
		
		Matcher end_time_matcher = pattern.matcher(end_time);
		if(!end_time_matcher.matches()) {
			constractErrorMsg.setStart_time_errorMsg("終了時間の値が間違っております。数字4桁で入力してください");
		}
		
		String regex_num1 = "^\\d{1,2}";
		Pattern pattern1 = Pattern.compile(regex_num1);
		Matcher break_time_matcher = pattern1.matcher(break_time);
		
		if(!break_time_matcher.matches()) {
			constractErrorMsg.setBreak_time_errorMsg("休憩時間の値が間違っております。0〜99の値で入力してください");
		}
		
		return constractErrorMsg;
	}
}
