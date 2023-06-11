package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/RegisterVoter", loadOnStartup = 1)
public class ResisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
		} catch (Exception e) {
			System.out.println("err in destroy of " + getClass() + " " + e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			
			String firstName = request.getParameter("fn");	
			String lastName = request.getParameter("ln");
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			String dob = request.getParameter("dob");
			boolean result = userDao.authenticateNewUser(firstName,lastName,email,password,dob);

             if(result)
             {
            	// response.sendRedirect("http://localhost:8080/day2.1/login");
            	 pw.print("<h2> registered succesful! <a href='login.html'>click here</a> to login!</h2>");
             }
             else
             {
            	 pw.print("registration unsuccesful user must be 21 years old");
             }
		} 
		catch (Exception e) {
			throw new ServletException("err in do-post " + getClass(), e);
		}
	}

}
