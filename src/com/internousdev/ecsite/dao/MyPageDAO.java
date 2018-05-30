package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.MyPageDTO;
import com.internousdev.ecsite.util.DBConnector;

public class MyPageDAO {

	public ArrayList<MyPageDTO> getMyPageUserInfo(String item_transaction_id, String user_master_id) {
		ArrayList<MyPageDTO> myPageDTOList = new ArrayList<MyPageDTO>();
		Connection con = null;
		DBConnector db = new DBConnector();
		con = db.getConnection();

		String sql = "SELECT ubit.id, iit.item_name, ubit.total_price, ubit.total_count, ubit.pay, ubit.insert_date FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction iit ON ubit.item_transaction_id = iit.id WHERE ubit.item_transaction_id = ? AND ubit.user_master_id = ? ORDER BY insert_date DESC";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item_transaction_id);
			ps.setString(2, user_master_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MyPageDTO myPageDTO = new MyPageDTO();
				myPageDTO.setId(rs.getString("id"));
				myPageDTO.setItemName(rs.getString("item_name"));
				myPageDTO.setTotalPrice(rs.getString("total_price"));
				myPageDTO.setTotalCount(rs.getString("total_count"));
				myPageDTO.setPayment(rs.getString("pay"));
				myPageDTO.setInsert_date(rs.getString("insert_date"));
				myPageDTOList.add(myPageDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return myPageDTOList;
	}
	
	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id){
		Connection con = null;
		DBConnector db = new DBConnector();
		con = db.getConnection();
		
		String sql = "DELETE FROM user_buy_item_transaction WHERE item_transaction_id = ? AND user_master_id = ?";
		int result = 0;
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item_transaction_id);
			ps.setString(2, user_master_id);
			result = ps.executeUpdate();
			
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
		return result;
		
	}
}
