 package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDaoImpl;
import pojos.Candidate;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin_page")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()) {
			
			AdminDaoImpl adminDao=new AdminDaoImpl();
			List<Candidate> topTwo=adminDao.getTopTwoCandidates();
			pw.print("<h1> Top Two Candidates </h1><table border=2px>");
			for(Candidate c: topTwo)
			{
				pw.write("<tr><td> Candidate Name :"+c.getName()+"</td> <td> &emsp;Party Name : "+c.getParty()+"</td><td> &emsp; No. of Votes :  "+c.getVotes()+"</td></tr>");
			}
			pw.print("</table></h3>");
			
			List<Candidate> partyList=adminDao.partyVoteList();
			
			pw.print("<h1> Party List with sum of votes </h1><table border=2px>");
			for(Candidate c: partyList)
			{
				pw.write("<tr><td>"+"Party Name :  &emsp; "+c.getParty()+"</td><td> &emsp;Total Votes gain by Party :  "+c.getVotes()+"</td></tr>");
			}
			pw.print("</table></h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
