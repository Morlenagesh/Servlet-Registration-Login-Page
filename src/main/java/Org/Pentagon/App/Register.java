package Org.Pentagon.App;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
	String Uname=req.getParameter("name");
	String Umail=req.getParameter("mail");
	String Upassword=req.getParameter("password");
	String Ugender=req.getParameter("gender");
	String Ucity=req.getParameter("city");
	/*PrintWriter out=resp.getWriter();
	
		out.println("<html><body bgcolor='blue'><h3>"+Uname+" "+"registration is successfull</h3></body></html>");
		RequestDispatcher rd=req.getRequestDispatcher("/Login.html");
		rd.include(req,resp);
	
	out.flush();
    out.close(); */
    resp.setContentType("text/html");
	Connection con=null;
	PreparedStatement pstmt=null;
	String iQuery="insert into  User_Database.User_Details value(?,?,?,?,?)";
	try {
		PrintWriter out=resp.getWriter();
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306? user=root&password=Morle21@");
		pstmt=con.prepareStatement(iQuery);
		pstmt.setString(1,Uname);
		pstmt.setString(2,Umail);
		pstmt.setString(3,Upassword);
		pstmt.setString(4,Ugender);
		pstmt.setString(5,Ucity);
		 int  i = pstmt.executeUpdate();
		/* out.println("<h3 style='color:'cyan'>"+Uname+""+" registration successfully</h3>");*/
		   if(i>0) {
				
				out.println("<html><body bgcolor='Yellow'><h3>"+Uname+""+" registration successfullyr</h3></body></html>");
				RequestDispatcher rd=req.getRequestDispatcher("/Login_Page.html");
				rd.include(req,resp);
			}
			else
			{
				
				out.println("<html><body bgcolor='cyan'><h3 style='color:blue'>"+Uname+""+" not registration due to some error</h3></body></html>");
				RequestDispatcher rd=req.getRequestDispatcher("/Register.html");
				rd.include(req,resp);	
			}
		
	}
	catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
	
	finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(con!=null) {
	
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	
	
	
}
}
