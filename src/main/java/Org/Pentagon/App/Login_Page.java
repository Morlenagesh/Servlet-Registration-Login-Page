package Org.Pentagon.App;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_Page extends HttpServlet{
	
protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException  {
	String Umail=req.getParameter("mail");
	String Upassword=req.getParameter("password");
	
	Connection con=null;
	PreparedStatement pstmt=null;
	/*PrintWriter out=resp.getWriter();
	RequestDispatcher rd=req.getRequestDispatcher("/Profile.html");
	rd.include(req, resp);*/
	try {
		PrintWriter out=resp.getWriter();
		Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306? user=root&password=Morle21@");
		 pstmt=con.prepareStatement("Select * from User_Database.User_Details where Umail=?,Upassword=?");
		pstmt.setString(1,Umail);
		pstmt.setString(2, Upassword);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			resp.setContentType("text/html");
			out.println("<html><body bgcolor='cyan'><h1 style color:Blue> Login Page is successfull</h1></body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("/Profile.html");
			rd.forward(req, resp);
		}
		else {
			resp.setContentType("text/html");
			out.println("<html><body bgcolor='cyan'><h1 style color:Blue> Email and Password didn't matched</h1></body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("/Login_Page.html");
			rd.include(req, resp);
		}
	}
	catch (Exception e){
		e.printStackTrace ();
	}
	finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace ();
			}
		}
		if(con!=null) {
			try {
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
}
