package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/test1",loadOnStartup = 2)
//web container read it only once at the web application deployment time
//web container populates URL map
//key : /test1
//value: pages.HttpServlet
public class Servlet1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in do-Get"+Thread.currentThread());
		//set resp content type(setting a resp header content-type:text/html)
		resp.setContentType("text/html");
		//to send the character , buffered data to client
		try(PrintWriter pw=resp.getWriter())
		{
			pw.print("<h2> welcome to servlet:"+getClass()+"</br></br>"+"TimeStamp :"+LocalDateTime.now()+"</h2>");
		}//web container : pw.flush()-->buffer content are sent to resp packet --> pw.close()
	}

	@Override
	public void destroy() {
		System.out.println("in Distroy"+Thread.currentThread());
	}

	@Override
	public void init() throws ServletException {
		System.out.println("in init"+Thread.currentThread());
	}

}
