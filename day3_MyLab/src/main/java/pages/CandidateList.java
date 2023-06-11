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
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import pojos.Candidate;
import pojos.User;

/**
 * Servlet implementation class CandidateListServlet
 */
@WebServlet("/candidate_list")
public class CandidateList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// get cookie/s from request header
			// API of HttpServletRequest : Cookie[] getCookies()
			HttpSession session = request.getSession();
			System.out.println("is new ?" + session.isNew());// f
			System.out.println("session id " + session.getId());// same session id :WC generated long string
			User userdetails = (User) session.getAttribute("user_details");
			if (userdetails != null) {
				pw.print("<h3> Hello ," + userdetails.getFirstName() + " " + userdetails.getLastName() + "</h3>");

			
			CandidateDaoImpl dao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
			List<Candidate> candidateList = dao.getAllCandidate();
			pw.print("<form action='logout'>");

			for (Candidate c : candidateList) {
				pw.print("<h5> <input type='radio' name='cid' value='" + c.getCandidateId() + "'/>" + c.getName()
						+ "</h5>");
			}
			pw.print("<h5><input type='submit' value='Cast A Vote'/></h5>");
			pw.print("</form>");
			}else {
				pw.print("<h5> Session Tracking Failed , No Cookies!!!!!!!!!!!!!</h5>");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
