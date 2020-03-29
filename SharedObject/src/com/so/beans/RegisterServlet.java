package com.so.beans;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.so.helper.ConnectionFactory;
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,HttpServletResponse res) {
		try {
			Connection con=ConnectionFactory.getConnection();
			
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			String email=req.getParameter("email");
			String city=req.getParameter("city");
			String state=req.getParameter("state");
			String country=req.getParameter("country");
			System.out.println(username+" "+password+" "+email+" "+city+" "+state+" "+country);
			
			String sql="insert into users(username,password,email,city,state,country) values(?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, city);
			pstmt.setString(5, state);
			pstmt.setString(6, country);
			int inserted=pstmt.executeUpdate();
			PrintWriter out=res.getWriter();
			if(inserted>0) {
				out.println("Registered successfully");
			}
			else {
				out.println("Registeration failed");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
