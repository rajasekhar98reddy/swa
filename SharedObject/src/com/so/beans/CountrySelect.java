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
@WebServlet("/countries")
public class CountrySelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest req,HttpServletResponse res) {
		List<String> countries=null;
		Cache cache=Cache.getInstance();
		try {
			if(cache.containsKey("countries")==false) {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			countries= new ArrayList<String>();
			ResultSet rs=st.executeQuery("select * from country");
			while (rs.next()) {
				countries.add(rs.getString("country_name"));
			}
			cache.put("countries", countries);
			System.out.println("Coming from db");
			}
			else {
				countries=(List<String>)cache.get("countries");
				System.out.println("coming from cache");
			}
			RequestDispatcher rds=req.getRequestDispatcher("");
			req.setAttribute("countries", countries);
			rds.include(req, res);
			System.out.println(countries);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
