package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.dto.LoginDTO;
import com.internousdev.ecsite.util.DBConnector;

public class LoginDAO {
	LoginDTO loginDTO = new LoginDTO();
	
	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword){
		Connection con = null;
		DBConnector db = new DBConnector();
		con = db.getConnection();
		
		String sql = "SELECT * FROM login_user_transaction WHERE login_id = ? AND login_pass = ?";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,loginUserId);
			ps.setString(2,loginPassword);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				loginDTO.setLoginId(rs.getString("login_id"));
				loginDTO.setLoginPassword(rs.getString("login_pass"));
				loginDTO.setUserName(rs.getString("user_name"));
				
				System.out.println("確認用"+rs.getString("login_id"));
				
				if(!(rs.getString("login_id").equals(null))){
					loginDTO.setLoginFlg(true);
				}
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		return loginDTO;
	}
	
	public LoginDTO getLoginDTO(){
		return loginDTO;
	}

}
