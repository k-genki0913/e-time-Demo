package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.Role_DTO;

public class Role_DAO {
	public List<Role_DTO> getRole_List(){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		List<Role_DTO> roleList = new ArrayList<>();
		
		String sql = "SELECT ROLE_ID, ROLE_NAME, APPROVE_LEVEL FROM ROLE";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Role_DTO role_DTO = new Role_DTO();
				role_DTO.setRole_id(rs.getString("ROLE_ID"));
				role_DTO.setRole_name(rs.getString("ROLE_NAME"));
				role_DTO.setApprove_level(rs.getInt("APPROVE_LEVEL"));
				roleList.add(role_DTO);
			}
		} catch(SQLException e) {
			System.out.println("Roleテーブルからの情報取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
		}
		Collections.sort(roleList);
		return roleList;
	}
}
