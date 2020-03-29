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
@WebServlet("/cities")
public class CitySelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest req,HttpServletResponse res) {
		List<String> cities=null;
		Cache cache=Cache.getInstance();
		try {
			if(cache.containsKey("cities")==false) {
				Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
				cities= new ArrayList<String>();
				ResultSet rs=st.executeQuery("select * from city");
				while (rs.next()) {
					cities.add(rs.getString("city_name"));
					}
				cache.put("cities", cities);
				System.out.println("Coming from db");
				}
			else {
				cities=(List<String>)cache.get("cities");
				System.out.println("coming from cache");
			}
				
				RequestDispatcher rds=req.getRequestDispatcher("");
				req.setAttribute("cities", cities);
				rds.include(req, res);
				System.out.println(cities);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
