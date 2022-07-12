package services;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.msf.log.Logger;
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private static Logger log = Logger.getLogger(Object.class);
	PatternLayout layout = new PatternLayout();
	ConsoleAppender appender =new ConsoleAppender(layout);
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		String userName=req.getParameter("userName");
		String password= req.getParameter("password");
	
		
		PrintWriter out= res.getWriter();
		LoginValidation u= new LoginValidation();
		Connection c=null;
	    
		 try {
			 if(u.validate(userName, password)) {			
				Class.forName("com.mysql.jdbc.Driver");
				log.debug("UserLoginServlet::JDBC driver class is loaded");
				c=DriverManager.getConnection("jdbc:mysql://dev-ws.bajajfinservsecurities.in:8444/SESSION_DATABASE", "platformwrite", "bfslwrite");
				log.info("UserLoginServlet::JDBC connection is established");
				out.println("logged in successfully");
				HttpSession session=req.getSession();  
			    session.setAttribute("username",userName);  
			    session.setAttribute("password", password);
			    log.debug("UserLoginServlet::HttpSession created");
			 }else {out.println("please enter the currect username and password");
				 }}
			 catch(Exception e) {			
					e.printStackTrace();
					log.error("UserLoginServlet::unknown DB problem"+e.getMessage());
				}
				 finally{
					 log.debug("UserServlet::closing JDBC objects");
					 try {
						 if( c!= null)
						 c.close();
						 log.debug("UserLoginServlet::connection object is closed");
					 }
					 catch(SQLException se) {
						 se.printStackTrace();			 
					 }}
		 				log.debug("UserLoginServlet::end of the doPost method");
				 	}}

	
