package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.Users_DAO;
import dto.ConstractErrorMsg;
import dto.Users_DTO;

public class ConstractInputCheck {
	public ConstractErrorMsg checkError(String workplace, String constract_start, String constract_end, String start_time, String end_time, String break_time, String user_id) {
		ConstractErrorMsg constractErrorMsg = new ConstractErrorMsg();
		
		if(workplace == null || workplace.length() == 0) {
			constractErrorMsg.setWorkplace_errorMsg("勤務先を入力してください");
		}
		
		String regex = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
		
		Pattern pattern_1 = Pattern.compile(regex);
		Matcher constract_start_matcher = pattern_1.matcher(constract_start);
		if(!constract_start_matcher.matches()) {
			constractErrorMsg.setConstract_start_errorMsg("契約開始日の値が誤っております。正しく入力してください");
		}
		
		Matcher constract_end_matcher = pattern_1.matcher(constract_end);
		if(!constract_end_matcher.matches()) {
			constractErrorMsg.setConstract_end_errorMsg("契約終了日の値が誤っております。正しく入力してください");
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
		
		boolean user_id_check = new InputCheck().inputUserIDCheck(user_id);
		Users_DTO users_DTO = new Users_DAO().getUser(user_id);
		if(!user_id_check) {
			constractErrorMsg.setUser_id_errorMsg("ユーザーIDは数字10桁で入力してください");
		} else if(users_DTO.getUser_id() == null) {
			constractErrorMsg.setUser_id_errorMsg("ユーザーが存在しません");
		}
		
		
		return constractErrorMsg;
	}
}
