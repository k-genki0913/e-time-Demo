package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.Department_DTO;

public class Department_DAO {
	public List<Department_DTO> getDepartmentList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		List<Department_DTO> departmentList = new ArrayList<>();
		
		String sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME FROM DEPARTMENT";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Department_DTO department_DTO = new Department_DTO();
				department_DTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
				department_DTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
				departmentList.add(department_DTO);
			}
		} catch(SQLException e) {
			System.out.println("Departmentテーブルからの情報取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {}
			}
			if(con != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {}
			}
		}
		Collections.sort(departmentList);
		return departmentList;
	}
}
