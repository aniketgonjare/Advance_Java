package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()) {
			pw.print("<h5>In logout page ....</h5>");
			HttpSession session=request.getSession();
			User voter=(User) session.getAttribute("user_details");
			if(voter!=null)
			{
				if(voter.isVotingStatus())
					pw.write("you already voted");
				else {		
					 int candidateId = Integer.parseInt(request.getParameter("cid"));
				UserDaoImpl userDao=(UserDaoImpl) session.getAttribute("user_dao");
				CandidateDaoImpl candidateDao=(CandidateDaoImpl) session.getAttribute("candidate_dao");
				pw.print("<h5>"+userDao.updateVotingStatus(voter.getId())+"</h5>");
				pw.print("<h5>"+candidateDao.incrementCandidateVotes(candidateId)+"</h5>");
			}
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}
	}


