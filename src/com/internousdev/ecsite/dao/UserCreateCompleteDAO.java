package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;

public class UserCreateCompleteDAO {
	private DateUtil dateUtil = new DateUtil();
	
	public void createUser(String loginUserId, String loginUserPassword, String userName) {
		Connection con = null;
		DBConnector db = new DBConnector();
		con = db.getConnection();
		
		String sql ="INSERT INTO login_user_transaction (login_id,login_pass,user_name,insert_date) VALUES (?,?,?,?)";
		
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUserId);
			ps.setString(2,loginUserPassword);
			ps.setString(3, userName);
			ps.setString(4, dateUtil.getDate());
			
			ps.execute();
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

}
