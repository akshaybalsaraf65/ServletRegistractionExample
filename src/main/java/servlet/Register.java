package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
//@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("userName");
		String pass = request.getParameter("userPass");
		String email = request.getParameter("userEmail");
		String country = request.getParameter("userCountry");
		
		String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
        String dbUser = "root";
        String dbPassword = "4222";
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        	PreparedStatement ps = con.prepareStatement("insert into registeruser values(?,?,?,?)");
        	ps.setString(0, name);
        	ps.setString(1, pass);
        	ps.setString(2, email);
        	ps.setString(3, country);
        	
        	int i = ps.executeUpdate();
        	if(i > 0) {
        		out.print("You are successfully registered...");  
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
        out.close();
	}

}
