package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {
//throws는 현재메서드에서 해당예외를 처리하지않고 , 이 메서드를 호출한 자에게 떠넘기는것이다. 
	public void insert() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		pstmt = con.prepareStatement("");
		pstmt.executeUpdate();

	}

	public static void main(String[] args)throws SQLException {
		ExApp app = new ExApp();
		app.insert();

	}
}
