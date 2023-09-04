package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCheck {
	//入力されたuser_idが数字10桁か確認
		public boolean inputUserIDCheck(String user_id) {
			String regex_num = "^\\d{10}";
			
			Pattern pattern = Pattern.compile(regex_num);
			Matcher matcher = pattern.matcher(user_id);
			boolean result = matcher.matches();
			
			return result;
		}
		
		//入力されたパスワードがローマ字大文字小文字、数字、記号(-_)のうち３種類を含み、かつ8文字以上か確認
		public boolean inputPasswordCheck(String password) {
			String regex_pass = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])|(?=.*[a-z])(?=.*[A-Z])(?=.*[-_])|(?=.*[A-Z])(?=.*[0-9])(?=.*[-_])|(?=.*[a-z])(?=.*[0-9])(?=.*[-_]))([a-zA-Z0-9-_]){8,}$";
			
			Pattern pattern = Pattern.compile(regex_pass);
			Matcher matcher = pattern.matcher(password);
			boolean result = matcher.matches();
			
			return result;
		}
}
