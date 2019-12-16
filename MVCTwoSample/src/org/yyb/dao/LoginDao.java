package org.yyb.dao;

//bbbb+aaaa
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.yyb.entity.Login;

//模型层：用于处理登录
public class LoginDao {
	public static int login(Login login) {
		int flag = -1;//-1:系统异常    0：用户名或密码有误  1：登录成功
		int result = -1;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_student","root","123456");
			
			String sql = "select count(*) from cesi where name=? and psw=?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, login.getName());
			pstmt.setString(2, login.getPsw());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			if (result>0) {
				flag = 1;
				return flag;
			}else {
				flag = 0;
				return flag;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			flag = -1;
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = -1;
			return flag;
		}catch (Exception e) {
			e.printStackTrace();		
			flag = -1;
			return flag;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(connection!=null) connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
	}
}
