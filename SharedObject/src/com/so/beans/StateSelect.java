package com.so.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.so.helper.ConnectionFactory;
@WebServlet("/states")
public class StateSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest req,HttpServletResponse res) {
		List<String> states=null;
		Cache cache=Cache.getInstance();
		try {
			if(cache.containsKey("states")==false) {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			states= new ArrayList<String>();
			ResultSet rs=st.executeQuery("select * from state");
			while (rs.next()) {
				states.add(rs.getString("state_name"));
			}
			cache.put("states",states);
			System.out.println("Coming from db");
			}
			else {
				states=(List<String>)cache.get("states");
				System.out.println("coming from cache");
			}
			RequestDispatcher rds=req.getRequestDispatcher("");
			req.setAttribute("states", states);
			rds.include(req, res);
			System.out.println(states);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
