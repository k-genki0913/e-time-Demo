package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	//接続先の情報をフィールドに定数としてて式
		private static final String RDB_DRIVER = "org.h2.Driver";
		private static final String URL = "jdbc:h2:tcp://localhost/~/DemoCyncs";
		private static final String USER = "k.genki0913";
		private static final String PASSWORD = "1234";
				
		//データベース接続を行うメソッド
		//データベース接続用定義を基にデータベースへ接続し、戻り値としてコネクション情報を返す
		protected static Connection getConnection() {
			Connection con = null;
					
			try {
				Class.forName(RDB_DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return con;
		}
}
