package model;

public class UserRegistCheck {
	public String inputCheck(String user_id, String password) {
		
		InputCheck inputCheck = new InputCheck();
		ExistCheck existCheck = new ExistCheck();
		String userRegistErrorMsg = "";
		String lineCd = System.getProperty("line.separator");
		
		if(!inputCheck.inputUserIDCheck(user_id)){
			userRegistErrorMsg = "ユーザーIDは数字10桁で入力してください" + lineCd;
		}
		if(existCheck.checkUser_id(user_id)) {
			userRegistErrorMsg = "入力されたユーザーIDは既に存在します" + lineCd;
		}
		if(!inputCheck.inputPasswordCheck(password)) {
			userRegistErrorMsg = "パスワードがローマ字大文字小文字、数字、記号(-_)のうち３種類含み、8文字以上か確認してください" + lineCd;
		}
		return userRegistErrorMsg;
	}
	
	public String updateCheck(String password) {
		String userUpdateErrorMsg = "";
		InputCheck inputCheck = new InputCheck();
		
		if(!inputCheck.inputPasswordCheck(password)) {
			userUpdateErrorMsg = "パスワードがローマ字大文字小文字、数字、記号(-_)のうち３種類含み、8文字以上か確認してください";
		}
		return userUpdateErrorMsg;
	}
}
