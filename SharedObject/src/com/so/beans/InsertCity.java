package com.so.beans;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.so.helper.ConnectionFactory;

@WebServlet("/insertcity")
public class InsertCity extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String city=req.getParameter("city");
		Connection con=null;
		PreparedStatement pstmt=null;
		PrintWriter out=res.getWriter();
		Cache cache=Cache.getInstance();
		try {
			con=ConnectionFactory.getConnection();
			String sql="insert into city(city_name) values(?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,city);
			int count=pstmt.executeUpdate();
			if(count>0) {
				out.println("Inserted");
				cache.clear();
			}else {
				out.println("not inserted");
			}		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
