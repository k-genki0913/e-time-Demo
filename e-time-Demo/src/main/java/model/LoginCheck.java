package model;

import dao.User_Password_DAO;
import dto.User_Password_DTO;

public class LoginCheck {
	//User_Passwordテーブルへの接続、SQL実行を行うインスタンスを生成
		User_Password_DAO user_Password_DAO = new User_Password_DAO();
		
		//User_Passwordテーブルからpasswordなどを取得し、入力値とデータベースの情報を比較しログイン可能か判定する
		public User_Password_DTO loginCheck(String input_user_id, String input_password) {
			//User_Password_DAOからの戻り値を受け取り、メソッド呼び出し元へ戻すインスタンスを生成
			User_Password_DTO userLoginDTO = new User_Password_DTO();
			
			//user_idを元にUser_Passwordテーブルからpassword、invalid_countを受け取る
			userLoginDTO = user_Password_DAO.getUser_Password(input_user_id);
			
			if(userLoginDTO.getUser_id().equals("0000000000")) {
				userLoginDTO.setLogin_result(false);
				return userLoginDTO;
			}
			
			//ログイン成功、失敗を格納する
			boolean loginResult;
			//ログインの失敗回数を格納
			int invalid_count = userLoginDTO.getInvalid_count();
			
			//ログイン失敗の回数がアカウントロック回数(6回以上)になっているか、パスワードが入力と一致しているか確認
			if(invalid_count < 6 && userLoginDTO.getPassword().equals(input_password)) {
				loginResult = true;
				invalid_count = 0;
			} else {
				loginResult = false;
				invalid_count++;
			}
			//invalid_countの更新
			invalidUpdate(input_user_id, invalid_count);
			
			//if文の判定結果をuserLoginDTOへ格納
			userLoginDTO.setLogin_result(loginResult);
			userLoginDTO.setInvalid_count(invalid_count);
			
			return userLoginDTO;
		}
		
		private void invalidUpdate(String user_id, int count) {
			user_Password_DAO.setInvalid_Count(user_id, count);
		}
}
