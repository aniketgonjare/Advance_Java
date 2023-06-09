package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(value="/test2",loadOnStartup = 1)
public class servlet2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doget");
		resp.setContentType("text/html");
		try(PrintWriter pw=resp.getWriter())
		{
			pw.write("<h1>hi this is ganesh</h1>");
		}
	}

	@Override
	public void destroy() {
		System.out.println("in destroy");
	}

	@Override
	public void init() throws ServletException {
		
		System.out.println("in init of servlet 2");
		
	}
	

}
